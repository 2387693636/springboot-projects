package com.xq.oauth.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xq.oauth.OAuth;
import com.xq.system.utils.OAuthConfig;
import com.xq.system.utils.TokenToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther: xq2580z
 * @date: 2020/3/2 16:21
 * @description:
 */
public class QQOAuth extends OAuth {
    private static final Logger LOGGER = Logger.getLogger(OAuth.class);

    private static final String AUTH_URL = "https://graph.qq.com/oauth2.0/authorize";
    private static final String TOKEN_URL = "https://graph.qq.com/oauth2.0/token";
    private static final String TOKEN_INFO_URL = "https://graph.qq.com/oauth2.0/me";
    private static final String USER_INFO_URL = "https://graph.qq.com/user/get_user_info";

    private static QQOAuth QQOAuth = new QQOAuth();

    /**
     * 用于链式操作
     *
     * @return
     */
    public static QQOAuth me() {
        return QQOAuth;
    }

    public QQOAuth() {
        setClientId(OAuthConfig.getValue("openid_qq"));
        setClientSecret(OAuthConfig.getValue("openkey_qq"));
        setRedirectUri(OAuthConfig.getValue("redirect_qq"));
    }

    /**
     * 获取授权url
     * DOC：http://wiki.connect.qq.com/%E4%BD%BF%E7%94%A8authorization_code%E8%8E%B7%E5%8F%96access_token
     *
     * @param @return 设定文件
     * @return String    返回类型
     * @throws UnsupportedEncodingException
     */
    public String getAuthorizeUrl(String state) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("response_type", "code");
        params.put("client_id", getClientId());
        params.put("redirect_uri", getRedirectUri());
        //OAuth2.0标准协议建议，利用state参数来防止CSRF攻击。可存储于session或其他cache中
        if (StringUtils.isNotBlank(state)) {
            params.put("state", state);
        }
        return super.getAuthorizeUrl(AUTH_URL, params);
    }

    /**
     * @param @param  code
     * @param @return 设定文件
     * @return String    返回类型
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException   获取token
     * @throws
     */
    public String getTokenByCode(String code) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("code", code);
        params.put("client_id", getClientId());
        params.put("client_secret", getClientSecret());
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", getRedirectUri());
        // access_token=FE04************************CCE2&expires_in=7776000
        String token = TokenToolUtil.getAccessToken(super.doGet(TOKEN_URL, params));
        LOGGER.debug(token);
        return token;
    }

    /**
     * @return String    返回类型
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException              获取TokenInfo
     * @throws
     */
    public String getTokenInfo(String accessToken) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        // callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        String openid = TokenToolUtil.getOpenId(super.doGet(TOKEN_INFO_URL, params));
        LOGGER.debug(openid);
        return openid;
    }

    /**
     * 获取用户信息
     * DOC：http://wiki.connect.qq.com/get_user_info
     *
     * @param accessToken
     * @param uid
     * @return String    返回类型
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     */
    public String getUserInfo(String accessToken, String uid) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        params.put("oauth_consumer_key", getClientId());
        params.put("openid", uid);
        params.put("format", "json");
        // // {"ret":0,"msg":"","nickname":"YOUR_NICK_NAME",...}
        String userinfo = super.doGet(USER_INFO_URL, params);
        LOGGER.debug(userinfo);
        return userinfo;
    }

    /**
     * @param @param args    设定文件
     * @return void    返回类型
     * @throws NoSuchProviderException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException   根据code一步获取用户信息
     * @throws
     */
    public JSONObject getUserInfoByCode(String code) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException {
        String accessToken = getTokenByCode(code);
        if (StringUtils.isBlank(accessToken)) {
            return null;
        }
        String openId = getTokenInfo(accessToken);
        if (StringUtils.isBlank(openId)) {
            return null;
        }
        JSONObject dataMap = JSON.parseObject(getUserInfo(accessToken, openId));
        dataMap.put("openid", openId);
        dataMap.put("access_token", accessToken);
        LOGGER.debug(dataMap);
        return dataMap;
    }
}
