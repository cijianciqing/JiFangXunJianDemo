package com.example.demo.service.dingding;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.StrSpliter;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiReportListRequest;
import com.dingtalk.api.response.OapiReportListResponse;
import com.example.demo.beans.dingding.rizhi.JiFangXunJian;
import com.example.demo.beans.dingding.rizhi.MyImage;
import com.example.demo.dao.dingding.JiFangXunJianDao;
import com.example.demo.dao.dingding.MyImageDao;
import com.example.demo.utils.dingding.GetToken;
import com.taobao.api.ApiException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RiZhiService {

    @Autowired
    JiFangXunJianDao jiFangXunJianDao;
    @Autowired
    MyImageDao myImageDao;

    //刘鲁杰的钉钉用户ID
    private final String LiuID = "100411100000000002EF";
    //设置获取report的时间，从now往前推
    private final int REPORT_DAYS = 1;
    //每次返回的report数量
    private final Long REPORT_SIZE = 10L;
    private static ArrayList<JiFangXunJian> GlobalJiFangXunJians = new ArrayList<>();
    private static Long hasNextCursor = 0L;


    //每天0点0分1秒执行一次
    @Scheduled(cron = "1 0 0 1-31 * *" )
    @Transactional
    public void jiFangXunJian(){
        System.out.println(DateUtil.date(System.currentTimeMillis())+" com.example.demo.service.dingding.RiZhiService.jiFangXunJian() runs!!!");
        ArrayList<JiFangXunJian> allReports = getReports01();
        for (JiFangXunJian jiFangXunJian: allReports) {
            jiFangXunJianDao.save(jiFangXunJian);
            if(jiFangXunJian.getImages()!=null){
                myImageDao.deleteByJID(jiFangXunJian.getID());
                List<MyImage> images = jiFangXunJian.getImages();
                for (MyImage myImage:images) {
                    myImage.setJiFangXunJian(jiFangXunJian);
                    myImageDao.save(myImage);
                }

            }
        }
    }

    // jiFangXunJian主程序
    public ArrayList<JiFangXunJian> getReports01() {
        Long firstReturnCursor = getReports02(0L);
        hasNextCursor = firstReturnCursor;
        while (hasNextCursor != 999999L) {
            getReports02(hasNextCursor);
        }
        return GlobalJiFangXunJians;
    }

    //获取ReportList
    // 如果没有下一个，返回999999L
    // 如果有返回下一个的索引
    public Long getReports02(Long cursor) {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/report/list");
        OapiReportListRequest request = new OapiReportListRequest();
        request.setTemplateName("网络机房巡检");
        request.setStartTime(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(REPORT_DAYS));
        request.setEndTime(System.currentTimeMillis());
        request.setCursor(cursor);
        request.setSize(REPORT_SIZE);
        try {
            OapiReportListResponse response = client.execute(request, GetToken.getToken());
            List<OapiReportListResponse.ReportOapiVo> dataList = response.getResult().getDataList();
            for (OapiReportListResponse.ReportOapiVo report : dataList) {

                if(!report.getTemplateName().equals("")){
                    JiFangXunJian jiFangXunJian = transferReport(report);
                    GlobalJiFangXunJians.add(jiFangXunJian);
                }
            }
            Boolean hasMore = response.getResult().getHasMore();
            if (hasMore) {
                hasNextCursor = response.getResult().getNextCursor();
            }else{
                hasNextCursor = 999999L;
            }
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return hasNextCursor;
    }

    //将Report转换为JiFangXunJian
    private JiFangXunJian transferReport(OapiReportListResponse.ReportOapiVo report) {

        JiFangXunJian jiFangXunJian = new JiFangXunJian();
        //templateName此时无需保存
        String templateName = report.getTemplateName();

        String jiFang;
        Date riQi;
        Double wenDu;
        Double shiDu;
        String ups;
        String kongTiao;
        String mieHuoQi;
        String weiSheng;
        String sheBei;
        String yiChang;

        String reportId = report.getReportId();
        jiFangXunJian.setID(reportId);

        Date createTime = DateUtil.date(report.getCreateTime());
        jiFangXunJian.setCreateTime(createTime);
        String creatorName = report.getCreatorName();
        jiFangXunJian.setCreator(creatorName);
        List<String> images = report.getImages();
        ArrayList<MyImage> myImages = new ArrayList<>();
        for (String url : images) {
            MyImage myImage = new MyImage();
            String newUrl = "https:"+ StrSpliter.split(url, ':', 0, true, true).get(2).replaceAll("\"", "").replaceAll("}","");;
            myImage.setUrl(newUrl);
            myImages.add(myImage);
        }
        jiFangXunJian.setImages(myImages);
        JSONArray json = JSONArray.fromObject(report.getContents());
        if (json.size() > 0) {
            for (int i = 0; i < json.size(); i++) {
                // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                JSONObject job = json.getJSONObject(i);
                String key = (String) job.get("key");
                switch (key) {
                    case "巡检机房":
                        jiFang = (String) job.get("value");
                        jiFangXunJian.setJiFang(jiFang);
                        break;
                    case "巡检日期":
                        riQi = DateUtil.parse((String) job.get("value"));
                        jiFangXunJian.setRiQi(riQi);
                        break;
                    case "温度（℃）":
                        wenDu = Double.parseDouble("".equals((String)job.get("value"))?"0.00":(String)job.get("value"));
                        jiFangXunJian.setWenDu(wenDu);
                        break;
                    case "湿度（%RH）":
                        shiDu = Double.parseDouble("".equals((String)job.get("value"))?"0.00":(String)job.get("value"));
                        jiFangXunJian.setShiDu(shiDu);
                        break;
                    case "ups是否正常":
                        ups = (String) job.get("value");
                        jiFangXunJian.setUps(ups);
                        break;
                    case "空调是否正常":
                        kongTiao = (String) job.get("value");
                        jiFangXunJian.setKongTiao(kongTiao);
                        break;
                    case "灭火器压力是否正常":
                        mieHuoQi = (String) job.get("value");
                        jiFangXunJian.setMieHuoQi(mieHuoQi);
                        break;
                    case "卫生是否打扫":
                        weiSheng = (String) job.get("value");
                        jiFangXunJian.setWeiSheng(weiSheng);
                        break;
                    case "设备是否正常":
                        sheBei = (String) job.get("value");
                        jiFangXunJian.setSheBei(sheBei);
                        break;
                    case "异常情况说明":
                        yiChang = (String) job.get("value");
                        jiFangXunJian.setYiChang(yiChang);
                        break;
                    default:
                        break;
                }

            }
        }

        return jiFangXunJian;
    }

    /*@Transactional
    public void testDelete(){
        Integer integer = myImageDao.deleteByJID("16d7045fe5771d32f94e43046a9b8153");
        System.out.println(integer);
    }*/
}
