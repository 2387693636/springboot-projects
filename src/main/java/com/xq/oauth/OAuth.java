package com.xq.oauth;

import com.xq.system.utils.HttpToolUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

/**
 * @auther: xq2580z
 * @date: 2020/3/2 16:04
 * @description: 授权 针对第三方平台登陆的是授权
 */
public class OAuth {
    private String clientId;  //申请QQ登录成功后，分配给应用的appid。
    private String clientSecret; //申请QQ登录成功后，分配给网站的appkey。
    private String redirectUri;  //成功授权后的回调地址，必须是注册appid时填写的主域名下的地址，建议设置为网站首页或网站的用户中心。注意需要将url进行URLEncode。
    private String response_type = "code"; // 	授权类型，此值固定为“code”。

    public OAuth() {
    }

    protected String getAuthorizeUrl(String authorize, Map<String, String> params) throws UnsupportedEncodingException {
        return HttpToolUtil.initParams(authorize, params);
    }

    protected String doPost(String url, Map<String, String> params) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        return HttpToolUtil.post(url, params);
    }

    protected String doGet(String url, Map<String, String> params) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        return HttpToolUtil.get(url, params);
    }

    protected String doGetWithHeaders(String url, Map<String, String> headers) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        return HttpToolUtil.get(url, null, headers);
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
