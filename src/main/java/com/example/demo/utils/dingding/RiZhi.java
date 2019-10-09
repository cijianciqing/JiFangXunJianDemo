package com.example.demo.utils.dingding;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiReportTemplateListbyuseridRequest;
import com.dingtalk.api.response.OapiReportTemplateListbyuseridResponse;
import com.example.demo.utils.json.JsonFormater;
import com.taobao.api.ApiException;

public class RiZhi {



    public void getMuBan(String userID) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/report/template/listbyuserid");
        OapiReportTemplateListbyuseridRequest req = new OapiReportTemplateListbyuseridRequest();
        req.setOffset(0L);
        req.setSize(100L);
        OapiReportTemplateListbyuseridResponse rsp = client.execute(req, GetToken.getToken());
        String out = JsonFormater.format(rsp.getBody());
        System.out.println(out);
    }



}
