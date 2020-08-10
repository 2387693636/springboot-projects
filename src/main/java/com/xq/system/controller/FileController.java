package com.xq.system.controller;

import com.xq.system.constant.MyConstant;
import com.xq.system.utils.FileToolUtil;
import com.xq.system.utils.RandomToolsUtil;
import com.xq.system.vo.TableDataVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @auther: xq2580z
 * @date: 2020/2/28 13:36
 * @description: 文件上传下载的控制器
 */
@Controller
@RequestMapping("file")
public class FileController {

    /**
     * 添加
     *
     * @throws IOException
     * @throws IllegalStateException
     */
    @RequestMapping("uploadFile")
    @ResponseBody
    public TableDataVO uploadFile(MultipartFile mf) throws IllegalStateException, IOException {
        // 文件上传的父目录
        String parentPath = FileToolUtil.UPLOAD_FILE_PATH;
        // 得到当前日期作为文件夹名称
        String dirName = RandomToolsUtil.getCurrentDate();
        // 构造文件夹对象
        File dirFile = new File(parentPath, dirName);
        if (!dirFile.exists()) {
            dirFile.mkdirs();// 创建文件夹
        }
        // 得到文件原名
        String oldName = mf.getOriginalFilename();
        // 根据文件原名得到新名
        String newName = RandomToolsUtil.createFileNameByTimeAndRandom(oldName, MyConstant.FILE_UPLOAD_TEMP);
        File dest = new File(dirFile, newName);
        mf.transferTo(dest);

        Map<String, Object> map = new HashMap<>();
        map.put("src", dirName + "/" + newName);
        return new TableDataVO(map);

    }

    /**
     * 不下载只显示
     */
    @RequestMapping("downloadShowFile")
    @ResponseBody
    public ResponseEntity<Object> downloadShowFile(String path, HttpServletResponse response) throws IOException {
        return FileToolUtil.downFile(response, path, "");
    }

    /**
     * 下载图片
     *
     * @param path
     * @param response
     * @return
     */
    @RequestMapping("downloadFile")
    @ResponseBody
    public ResponseEntity<Object> downloadFile(String path, HttpServletResponse response) throws IOException {
        String oldName = "";
        return FileToolUtil.downFile(response, path, oldName);
    }

}
