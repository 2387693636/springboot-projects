package com.xq.system.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.date.DateTime;
import com.xq.system.constant.MyConstant;
import com.xq.system.pojo.LogInfo;
import com.xq.system.pojo.User;
import com.xq.system.service.LogInfoService;
import com.xq.system.service.UserService;
import com.xq.system.utils.WebToolsUtil;
import com.xq.system.vo.UserVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @auther: xq2580z
 * @date: 2020/2/28 14:53
 * @description: 登陆相关
 */
@Controller
public class LoginController {


    private static final Logger log = Logger.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private LogInfoService logInfoService;

    /**
     * 跳转到登陆
     */
    @RequestMapping("tologin")
    public String toLogin() {
        System.out.println("进入Login");
        return "system/main/login";
    }

    /**
     * 做登陆
     */
    @RequestMapping("login")
    public String login(HttpServletRequest request, UserVO userVO, Model model) {
        //先判断验证码 是否则正确
        String code = WebToolsUtil.getSession().getAttribute("code").toString();
        if (code.equals(userVO.getCode())) {
            //通过userVO从数据库中查 是否存在相应的user
            User user = userService.login(userVO);
            if (null != user) {
                //不为空 放入session  使用工具类放入session
                WebToolsUtil.getSession().setAttribute("user", user);
                //然后做日志 写进日志表 sys_log
                LogInfo logInfo = new LogInfo();
                //登陆名
                String loginname = user.getLoginname() + ":" + user.getRealname();
                String ip = WebToolsUtil.getIpAddress(request);
                System.out.println("ip " + ip);
//                String ip_addr = WebToolsUtil.getRequest().getRemoteAddr(); //ip
                Date logintime = new DateTime();
                System.out.println("loginname " + loginname);
                System.out.println("logintime " + logintime);

                logInfo.setLoginname(loginname);
                logInfo.setLoginip(ip);
                logInfo.setLogintime(logintime);

                //插入loginfo 表
                int insert = logInfoService.insert(logInfo);
                String result = (insert == 1) ? "是" : "否";
                System.out.println("是否已经插入：" + result);
                // 跳转到登陆
                return "/system/main/index";
            } else {
                //返回密码或者用户名错误信息
                model.addAttribute("error", MyConstant.LOGIN_ERR_INFO);
                //否则去登陆
                return "system/main/login";
            }
        } else {
            //返回验证码错误信息
            model.addAttribute("error", MyConstant.LOGIN_CODE_ERR_INFO);
            //刷新登陆界面
            return "system/main/login";
        }
    }

    /**
     * 做验证码 Captcha CAPTCHA
     */
    @RequestMapping("getCaptcha")
    public void getCaptcha(HttpServletResponse response, HttpSession session) throws IOException {
        // 定义图形验证码的长和宽 使用hutool工具
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(110, 36, 4, 5);
        session.setAttribute("code", lineCaptcha.getCode());
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(lineCaptcha.getImage(), "JPEG", outputStream);
    }


}
