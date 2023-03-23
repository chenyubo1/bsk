<%--
  Created by IntelliJ IDEA.
  User: WZW
  Date: 2023/3/9
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/css.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <title>注册</title>
    <script type="text/javascript">
        let userSex = "M";
        function changeSex(s) {
            userSex = s;
            $("a").removeClass("login-redcolor");
            $("#"+s).addClass("login-redcolor");
        }

        function register() {
            let userTel = $("#userTel").val().trim();
            let userPwd = $("#userPwd").val().trim();
            let userName = $("#userName").val().trim();
            let userTel_test = /^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\d{8}$/;
            if (userTel_test.test(userTel)) {
                if(userPwd.length >= 6 && userPwd.length <= 16){
                    if (userName.length >= 2 && userName.length <= 5){
                        $.post("UserServlet?m=log",{"userTel":userTel},function (data) {
                            if(data == "Y"){
                                layer.msg("账户已存在");
                            }else{
                                $.post("UserServlet?m=register",{"userTel":userTel,"userPwd":userPwd,"userName":userName,"userSex":userSex},function (data) {
                                    if(data == "Y"){
                                        top.location.reload();  //顶层页面刷新
                                    }else{

                                        layer.msg("账户密码有误");
                                    }
                                })
                            }
                        })
                    }else {
                        layer.msg("姓名，长度2-5字符");
                    }
                }else{
                    layer.msg("密码，长度6-16字符");
                }
            }else{
                layer.msg("请输入有效的手机号码");
            }
        }

    </script>
</head>

<body>

<!--register begin-->
<div class="m-login">
    <div class="login-logo"><img src="images/common/logo.png" /></div>
    <div class="login-title border-bot">欢迎注册加入会员中心</div>
    <div class="input-box  border-bot"><img src="images/login/icon-phone.png" /><input id="userTel" onblur="checkUserTel(this.value)" type="text" placeholder="请输入手机号"  class="input-box1"/></div>
    <div class="input-box  border-bot"><img src="images/login/icon-password.png" /><input id="userPwd" onblur="checkUserPwd(this.value)" type="text" placeholder="密码，长度6-16字符"  class="input-box1" /></div>
    <div class="input-box  border-bot"><img src="images/login/icon-name.png" /><input id="userName" type="text"  placeholder="姓名，最多5个字" /> <span><a
            href="javascript:changeSex('M')" id="M" class="login-redcolor">先生</a> | <a
            href="javascript:changeSex('F')" id="F" style="margin:0">女士</a></span></div>
    <div class="input-box "></div>
    <div onclick="register()" class="login-me cursor"><button class="cursor">立即注册</button></div>
</div>
<!--register end-->
</body>
</html>
<SCRIPT Language=VBScript><!--

//--></SCRIPT>