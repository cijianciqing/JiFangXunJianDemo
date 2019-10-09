package com.example.demo.utils.dingding;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.example.demo.config.properties.DingDingConstants;
import com.taobao.api.ApiException;

public class GetToken {
    public static String getToken(){
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(DingDingConstants.APPLET_KEY);
        request.setAppsecret(DingDingConstants.APPLET_SECRET);
        request.setHttpMethod("GET");
        OapiGettokenResponse response = null;
        try {
            response = client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(response.getErrcode());
        System.out.println(response.getErrmsg());
        System.out.println(response.getAccessToken());
        return response.getAccessToken();
    }
}
