package com.example.demo.utils.dingding;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiAuthScopesRequest;
import com.dingtalk.api.request.OapiDepartmentListRequest;
import com.dingtalk.api.response.OapiAuthScopesResponse;
import com.dingtalk.api.response.OapiDepartmentListResponse;
import com.example.demo.utils.json.JsonFormater;
import com.taobao.api.ApiException;

public class TongXunLun {

    public void getScopes(String accessToken) throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/auth/scopes");
        OapiAuthScopesRequest request = new OapiAuthScopesRequest();
        request.setHttpMethod("GET");
        OapiAuthScopesResponse response = client.execute(request, accessToken);

        System.out.println(response.getBody());

        OapiAuthScopesResponse.AuthOrgScopes authOrgScopes = response.getAuthOrgScopes();

        System.out.println(authOrgScopes.getAuthedDept());

        System.out.println(authOrgScopes.getAuthedUser());
    }

    public void getDepartments(String accessToken, String department_id){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
        OapiDepartmentListRequest request = new OapiDepartmentListRequest();
        request.setId(department_id);
        request.setHttpMethod("GET");
        try {
            OapiDepartmentListResponse response = client.execute(request, accessToken);
            String out = JsonFormater.format(response.getBody());
            System.out.println(out);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

}
