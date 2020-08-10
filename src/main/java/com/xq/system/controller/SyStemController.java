package com.xq.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @auther: xq2580z
 * @date: 2020/3/12 16:22
 * @description: 系统控制器 控制跳转界面
 */
@Controller
@RequestMapping("sys")
public class SyStemController {
    /**
     * 跳转菜单管理
     */
    @RequestMapping("toMenuManager")
    public String toMenuManager() {
        return "system/menu/menuManager";
    }

    /**
     * 跳转菜单管理左边的的菜单树页面
     */
    @RequestMapping("toMenuLeft")
    public String toMenuLeft() {
        return "system/menu/menuLeft";
    }

    /**
     * 跳转菜单管理右边的菜单列表
     */
    @RequestMapping("toMenuRight")
    public String toMenuRight() {
        return "system/menu/menuRight";
    }

    /**
     * 跳转角色管理页面
     */
    @RequestMapping("toRoleManager")
    public String toRoleManager() {
        return "system/role/roleManager";
    }

    /**
     * 跳转用户管理页面
     */
    @RequestMapping("toUserManager")
    public String toUserManager() {
        return "system/user/userManager";
    }

    /**
     * 跳转日志管理页面
     */
    @RequestMapping("toLogInfoManager")
    public String toLogInfoManager() {
        return "system/logInfo/logInfoManager";
    }

    /**
     * 跳转公告管理页面
     */
    @RequestMapping("toNewsManager")
    public String toNewsManager() {
        return "system/news/newsManager";
    }
}
