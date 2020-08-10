package com.xq.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther: xq2580z
 * @date: 2020/3/11 15:53
 * @description: 桌面的控制
 */
@Controller
public class DesktopController {

    /**
     * 跳转到桌面
     */
    @RequestMapping("toDesktopManager")
    public String toDesktop() {
        return "system/main/deskManager";
    }


}
