package com.xq.system.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @auther: xq2580z
 * @date: 2020/3/2 16:18
 * @description: token的工具类  参考自 qq sdk
 */
public class TokenToolUtil {
    private static final String STR_S = "abcdefghijklmnopqrstuvwxyz0123456789";

    private static final Pattern p1 = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)&refresh_token=(\\w+)$");
    private static final Pattern p2 = Pattern.compile("^access_token=(\\w+)&expires_in=(\\w+)$");
    private static final Pattern p3 = Pattern.compile("\"openid\"\\s*:\\s*\"(\\w+)\"");

    /**
     * @param @param  string
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getAccessToken(String string) {
        String accessToken = "";
        try {
            JSONObject json = JSONObject.parseObject(string);
            if (null != json) {
                accessToken = json.getString("access_token");
            }
        } catch (Exception e) {
            Matcher m = p1.matcher(string);
            if (m.find()) {
                accessToken = m.group(1);
            } else {
                Matcher m2 = p2.matcher(string);
                if (m2.find()) {
                    accessToken = m2.group(1);
                }
            }
        }
        return accessToken;
    }

    /**
     * 匹配openid
     *
     * @param @param  string
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     */
    public static String getOpenId(String string) {
        String openid = null;
        Matcher m = p3.matcher(string);
        if (m.find()) {
            openid = m.group(1);
        }
        return openid;
    }

    /**
     * sina uid于qq分离
     *
     * @param @param  string
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: getUid
     */
    public static String getUid(String string) {
        JSONObject json = JSONObject.parseObject(string);
        return json.getString("uid");
    }

    /**
     * 生成一个随机数
     *
     * @return
     */
    public static String randomState() {
        return RandomStringUtils.random(24, STR_S);
    }
}
