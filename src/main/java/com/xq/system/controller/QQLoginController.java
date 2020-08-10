package com.xq.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.xq.oauth.OAuth;
import com.xq.oauth.impl.QQOAuth;
import com.xq.system.constant.MyConstant;
import com.xq.system.pojo.QQUser;
import com.xq.system.service.QQUerService;
import com.xq.system.utils.IPToolUtil;
import com.xq.system.utils.TokenToolUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @auther: xq2580z
 * @date: 2020/3/12 14:29
 * @description: qq认证
 */
@Controller
public class QQLoginController {

    private static final Logger log = Logger.getLogger(QQLoginController.class);

    //注入QQUserService
    @Autowired
    private QQUerService qqUerService;

    //第三方登陆 qq
    @RequestMapping("qqlogin")
    public String qqindex(HttpServletRequest request) {
        //随机产生一个 str
        String s = TokenToolUtil.randomState();
        String authorizeURL = null;
        try {
            //获取授权页面地址 authorizeUrl
            authorizeURL = QQOAuth.me().getAuthorizeUrl(s);
        } catch (UnsupportedEncodingException e) {
            log.error("QQ登陆错误:", e);
        }
        authorizeURL = new StringBuffer("redirect:").append(authorizeURL).toString();
        log.info("请求QQ登陆地址:{}" + authorizeURL);
        //跳到qq登陆授权界面
        return authorizeURL;
    }


    /**
     * 对应在 QQ互联管理中心的网站回调域
     *
     * @param request
     * @param model
     * @return 登陆成功 则进入系统主页 否则跳到失败处理界面
     */
    @RequestMapping("/qqcallback")
    public String callback(HttpServletRequest request, Model model) {
        log.info("进入qqcallback");
        HttpSession session = request.getSession();

        String code = request.getParameter("code");
        String state = request.getParameter("state");
        // 取消了授权
        if (StringUtils.isBlank(state)||StringUtils.isBlank(code)){
            return "取消了授权";
        }
        //清除state以防下次登录授权失败
        session.removeAttribute(MyConstant.SESSION_STATE);

        //获取用户信息
        try {
            log.info("进入用户第一次登陆");
            //用户第一次登陆
            // 第一次合法登陆
            log.info("第一次合法登陆");
            JSONObject userInfo = QQOAuth.me().getUserInfoByCode(code);

            String accessToken =userInfo.getString("access_token");
            log.info("accessToken: "+accessToken);
            String openid = userInfo.getString("openid");
            log.info("openid: "+openid);

            //将accessToken openId存入 session
            session.setAttribute("accessToken", accessToken);
            session.setAttribute("openid", openid);

            log.info("------------------------------");

            String nickname = userInfo.getString("nickname");
            log.info(nickname);
            String gender = userInfo.getString("gender");
            String msg = userInfo.getString("msg");
            Integer level = userInfo.getInteger("level");

            //将信息给前端Model展示
            log.info("将信息给前端Model展示");
            UserInfo qquserInfo = new UserInfo(accessToken, openid);
            log.info("qquserInfo: "+qquserInfo);
            UserInfoBean userInfoBean = qquserInfo.getUserInfo();
            log.info("userInfoBean: "+userInfoBean);
            model.addAttribute("userInfoBean", userInfoBean);
            log.info("将信息给前端Model展示完成");

            log.info("将信息存入数据库");
            // 将相关信息存储数据库...
            QQUser qqUser = new QQUser();
            qqUser.setNickname(nickname);
            qqUser.setGender(gender);
            qqUser.setMsg(msg);
            qqUser.setLevel(level);

            log.info("qqUser: "+qqUser);
            int insert = qqUerService.insert(qqUser);
            log.info(insert);
            log.info("存入数据库完成 ，返回到 qqindex.jsp");


            return "system/main/qqindex";
        }catch(Exception e){
            e.printStackTrace();
        }
        //跳转失败
        return "system/main/login";
    }
}
