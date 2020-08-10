package com.xq.system.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * @auther: xq2580z
 * @date: 2020/2/28 13:36
 * @description: 文件处理的工具类
 */
public class FileToolUtil {

    //上传路径 默认
    public static String UPLOAD_FILE_PATH = "";

    //下载路径
    public static String DOWNLOAD_FILE_PATH = "D:\\EXCEL\\";

    // 先得到file_hande.properties中的路径
    static {
        InputStream resourceAsStream = FileToolUtil.class.getClassLoader().getResourceAsStream("application.properties");
        Properties p = new Properties();
        try {
            p.load(resourceAsStream);
            UPLOAD_FILE_PATH = p.getProperty("upload-path");
            System.out.println(UPLOAD_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //2.文件下载 返回ResponseEntity
    public static ResponseEntity<Object> downFile(HttpServletResponse response, String file_name, String path) throws IOException {
        //使用绝对路径+相对路径去找到文件对象
        File file = new File(FileToolUtil.DOWNLOAD_FILE_PATH, path);
        //判断文件是否存在
        if (file.exists()) {
            //如果名字有中文 要处理编码
            try {
                file_name = URLEncoder.encode(file_name, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //把file转成一个bytes
            byte[] bytes = FileUtils.readFileToByteArray(file);
            HttpHeaders header = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            header.setContentDispositionFormData("attachment", file_name);
            //创建ResponseEntity对象
            ResponseEntity<Object> entity =
                    new ResponseEntity<Object>(bytes, header, HttpStatus.CREATED);
            return entity;
        } else {
            PrintWriter out;
            out = response.getWriter();
            out.write("文件不存在");
            out.flush();
            out.close();
            return null;
        }
    }

    //3.文件删除
    //根据相对路径删除硬盘上文件
    public static void deleteFileUsePath(String path) {
        String realPath = DOWNLOAD_FILE_PATH + path;
        System.out.println(realPath);
        //根据文件
        File file = new File(realPath);
        if (file.exists()) {
            file.delete();
            System.out.println(realPath + "删除成功");
        } else {
            System.out.println(realPath + "文件不存在");
        }
    }

    //4.文件更改名字
    public static String updateFileName(String carimg, String suffix) {
        //找到文件
        try {
            File file = new File(DOWNLOAD_FILE_PATH, carimg);
            if (file.exists()) {
                file.renameTo(new File(DOWNLOAD_FILE_PATH, carimg.replace(suffix, "")));
                return carimg.replace(suffix, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据路径 删除图片
     *
     * @param carimg
     */
    public static void removeFileByPath(String carimg) {
        try {
            File file = new File(DOWNLOAD_FILE_PATH, carimg);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
