package com.xq.system.utils;

import cn.hutool.core.lang.UUID;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @auther: xq2580z
 * @date: 2020/2/25 17:20
 * @description 随机工具类
 * 时间都采用s1  yyyy-MM-dd
 */
public class RandomToolsUtil {

    //时间格式
    private static SimpleDateFormat s1 = new SimpleDateFormat("yyyyMMdd");
    private static SimpleDateFormat s2 = new SimpleDateFormat("y-M-d h:m:s a E");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");

    private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
    private static SimpleDateFormat s3 = new SimpleDateFormat("yy-MM-dd hh:mm:ss a E");
    private static SimpleDateFormat s4 = new SimpleDateFormat("yyyy-MMM-ddd hhh:mmm:sss a E");
    private static SimpleDateFormat s5 = new SimpleDateFormat("yyyyy-MMMM-dddd hhhh:mmmm:ssss a EEEE");

    //随机
    private static Random random = new Random();

    //1.得到当前日期
    public static String getCurrentDate() {
        String format = s1.format(new Date());
        return format;
    }

    //2.生成文件名 这里我采用三种生成方式

    //a.使用时间+4位随机数
    public static String createFileNameByTime(String file_name) {
//        System.out.println("进入createFileNameByTime");
//        String substring = file_name.substring(file_name.lastIndexOf("."), file_name.length());
//        int i = random.nextInt(9000) + 1000;
//        String time = s1.format(new Date());
//        String result = time + "-" + i + substring;
//        return result;
        String fileSuffix = file_name.substring(file_name.lastIndexOf("."), file_name.length());
        String time = sdf2.format(new Date());
        Integer num = random.nextInt(9000) + 1000;
        System.out.println(time + num + fileSuffix);
        return time + num + fileSuffix;
    }

    //b.使用时间+4位随机数+临时后缀
    public static String createFileNameByTimeAndRandom(String file_name, String str) {
//        String substring = file_name.substring(file_name.lastIndexOf("."), file_name.length());
//        String time = s1.format(new Date()); //时间
//        int i = random.nextInt(9000) + 1000; //四位随机数
//        //str 临时后缀
//        return time + "-" + i + "-" + str + substring;

        String fileSuffix = file_name.substring(file_name.lastIndexOf("."), file_name.length());
        String time = sdf2.format(new Date());
        Integer num = random.nextInt(9000) + 1000;
        return time + num + fileSuffix + str;

    }

    //c.使用UUID
    public static String createFileNameByUUID(String file_name) {
//        String substring = file_name.substring(file_name.lastIndexOf("."), file_name.length());
//        String s = UUID.randomUUID().toString().replace("-", "").toUpperCase();
//        return s + substring;
        String fileSuffix = file_name.substring(file_name.lastIndexOf("."), file_name.length());
        return UUID.randomUUID().toString().replace("-", "").toUpperCase() + fileSuffix;
    }

    /**
     * 根据时间+五位随机数成字符串
     *
     * @param preffx
     * @return
     */
    public static String createRandomStringUseTime(String preffx) {
        String s = preffx + "_" + sdf3.format(new Date()) + "_" + (random.nextInt(90000) + 10000);

        System.out.println(s);
        return s;
    }


}
