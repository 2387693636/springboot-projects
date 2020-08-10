<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="loginHtml">
<head>
    <meta charset="utf-8">
    <title>登录-自行车租赁系统</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="icon" href="${xq2580z}/static/favicon.ico">
    <link rel="stylesheet" href="${xq2580z}/static/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${xq2580z}/static/css/public.css" media="all"/>
</head>
<body class="loginBody">
<form class="layui-form" id="loginform" method="post" action="${xq2580z}/login">
    <div class="login_face"><img src="${xq2580z}/static/images/face.jpg" class="userAvatar"></div>
    <div class="layui-form-item input-item">
        <label for="userName">用户名</label>
        <input type="text" placeholder="请输入用户名" autocomplete="off" name="loginname" class="layui-input"
               lay-verify="required">
    </div>
    <div class="layui-form-item input-item">
        <label for="password">密码</label>
        <input type="password" placeholder="请输入密码" autocomplete="off" name="pwd" class="layui-input"
               lay-verify="required">
    </div>
    <div class="layui-form-item input-item" id="imgCode">
        <label for="code">验证码</label>
        <input type="text" placeholder="请输入验证码" autocomplete="off" name="code" id="code"
               class="layui-input">
        <img src="${xq2580z}/getCaptcha" onclick="this.src=this.src+'?'">
        <%--<img src="${xq2580z}/static/images/code.jpg"/>--%>
    </div>
    <div class="layui-form-item">
        <button class="layui-btn layui-block" lay-filter="login" lay-submit>登录</button>
    </div>
    <div class="layui-form-item layui-row" style="text-align: center;color: red;">
        <%--<a>${error }</a>--%>
        <%--第三方登陆--%>
        <%--<a href="javascript:;" class="seraph icon-qq layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>--%>
        <a href="${xq2580z}/qqlogin"
           class="seraph icon-qq layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>
        <a href="javascript:;" class="seraph icon-wechat layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>
        <a href="javascript:;" class="seraph icon-sina layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4"></a>

    </div>
</form>
<script type="text/javascript" src="${xq2580z}/static/layui/layui.js"></script>
<script type="text/javascript" src="${xq2580z}/static/js/cache.js"></script>
<script type="text/javascript">
    layui.use(['form', 'layer', 'jquery'], function () {
        var form = layui.form,
            layer = parent.layer === undefined ? layui.layer : top.layer
        $ = layui.jquery;
        // 2020.3.3 xq2580z 发现这里弹窗显示error信息
        // 在刚开始加载界面的时候就会执行  error原来为‘’ 空 后面有值会重新显示一次 未解决
        <%--layer.msg("${error}", { icon: 1, offset: "auto", time:2000 })--%>

        //第三方
        $(".loginBody .seraph").click(function () {
            layer.msg("敬请期待第三方登陆上线!", {
                time: 5000
            });
        })


        //登录按钮
        form.on("submit(login)", function (data) {
            $(this).text("登录中...").attr("disabled", "disabled").addClass("layui-disabled");
            setTimeout(function () {
                $("#loginform").submit();  //提交form表单信息
            }, 1000);
            return false;
        })

        //表单输入效果
        $(".loginBody .input-item").click(function (e) {
            e.stopPropagation();
            $(this).addClass("layui-input-focus").find(".layui-input").focus();
        })
        $(".loginBody .layui-form-item .layui-input").focus(function () {
            $(this).parent().addClass("layui-input-focus");
        })
        $(".loginBody .layui-form-item .layui-input").blur(function () {
            $(this).parent().removeClass("layui-input-focus");
            if ($(this).val() != '') {
                $(this).parent().addClass("layui-input-active");
            } else {
                $(this).parent().removeClass("layui-input-active");
            }
        })
    })

</script>
</body>
</html>