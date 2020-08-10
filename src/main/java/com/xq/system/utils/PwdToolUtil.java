package com.xq.system.utils;

import cn.hutool.crypto.digest.MD5;

/**
 * @auther: xq2580z
 * @date: 2020/3/12 14:46
 * @description: 密码加密 MD5加密
 */
public class PwdToolUtil {

    public static String setMD5(String string) {
        MD5 m = new MD5();
        String s = m.digestHex(string);
        return s;
    }
}