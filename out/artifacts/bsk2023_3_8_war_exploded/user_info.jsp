<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/3/10
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/css.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/address.css" />
    <title>个人信息</title>
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <script type="text/javascript">
        function update(userId) {
            let userName=$("#userName").val().trim();
            let userSex=$("#userSex").val().trim();

            if(userName.length >=2 && userName.length <= 20){
                $.post("UserServlet?m=update",{
                    "userId":userId,
                    "userName":userName,
                    "userSex":userSex
                },function (data) {
                    if(data=="Y"){
                        top.location.reload();
                    }else{
                        layer.msg("修改失败");
                    }
                })
            }else{
                layer.msg("姓名长度2~20个字符");
            }
        }
        
        function changeImg() {
            $("#codeImg").attr("src","UserServlet?m=codeImg&t="+Math.random())

        }
        
        function changePwd() {
            let oldPwd=$("#oldPwd").val().trim();
            let newPwd=$("#newPwd").val().trim();
            let code=$("#code").val().trim();
            if(oldPwd.length>= 6&&oldPwd.length<=16){
                if (newPwd.length>= 6&&newPwd.length<=16){
                    if (code.length==4){
                        $.post("UserServlet?m=changePwd",{"oldPwd":oldPwd, "newPwd":newPwd, "code":code},function (data) {
                            if (data=="1"){
                                layer.msg("原密码错误")
                            }else {
                                if (data=="F"){
                                    layer.msg("原密码和新密码相同")
                                }else {
                                    if (data =="Y"){
                                        top.location.href="main.jsp"
                                    }else {
                                        layer.msg("验证码错误")
                                    }
                                }
                            }
                        })
                    }else {
                        layer.msg("验证码长度错误");
                    }
                }else {
                    layer.msg("新密码长度6~16个字符");
                }
            }else {
                layer.msg("旧密码长度6~16个字符");
            }
        }
    </script>
</head>

<body>
<div class="m-address">
    <div class="wrapper">
        <div class="area" style="bottom:110px;">
            <div class="type border-bottom">
                <span class="left">个人信息</span>
            </div>
            <div class="title1 a-user">
                <div class="fl">
							<span><img src="images/member/desc-icon-name.png" />
								姓名/性别</span>
                    <span class="pad">${sessionScope.user.userName} ${sessionScope.user.userSex == 'M'?"先生":"女士"}</span>
                </div>
                <div class="fr">
                    <a href="#" class="login-redcolor" onclick="change('name_sex',1)">修改</a>
                </div>
            </div>
            <div style="display: none;" class="title1 a-user a-setuser change"
                 id="update_name_sex">
                <div class="fl userleft">
							<span><img src="images/member/desc-icon-name.png" />
								姓名/性别</span>
                </div>
                <div class="text-input07" style="width: 155px">
                    <input id="userName" id="j-new-city" type="text" value="${sessionScope.user.userName}"/>
                </div>
                <div>
                    <select id="userSex" class="text-input07">
                        <option value="M" ${sessionScope.user.userSex == 'M'?"selected":""} >先生</option>
                        <option value="F" ${sessionScope.user.userSex == 'F'?"selected":""}>女士</option>
                    </select>
                </div>
                <div class="act-botton clear">
                    <div class="save-button">
                        <a href="javascript:" class="radius" onclick="update('${sessionScope.user.userId}')">保存</a>
                    </div>
                    <div class="cancel-button">
                        <a href="javascript:" class="radius"
                           onclick="change('name_sex',2)">取消</a>
                    </div>
                </div>
            </div>
            <div class="border-bottom"></div>
            <div class="title1 a-user">
                <div class="fl">
							<span><img src="images/member/desc-icon-phone.png" />
								手机号码</span>
                    <span class="pad">${sessionScope.user.userTel}</span>
                </div>
            </div>
            <div class="border-bottom"></div>
            <div class="title1 a-user">
                <div class="fl">
                    <span><img src="images/login/icon-name.png" /> 登陆密码</span>
                    <input type="password" value="${sessionScope.user.userPwd}" style="border: none"
                           class="pad" />
                    <span class="add-icon cursor"></span>
                </div>
                <div class="fr">
                    <a href="#" class="login-redcolor" onclick="change('pwd',1)">修改</a>
                </div>
            </div>
            <div style="display: none;" class="title1 a-user change" id="update_pwd">
                <div class="fl">
                    <span><img src="images/login/icon-name.png" /> 登陆密码</span>
                </div>
                <div class="fl">
                    <div class="text-input07">
                        <input type="text" id="oldPwd" value="${param.userPwd}" placeholder="请输入旧密码"/>
                    </div>
                    <div class="fr">
                        <p style="line-height: 20px; margin-left: 10px">
                            请输入6-16位密码，可使用阿拉伯数字
                            <br />
                            英文字母或两者结合
                        </p>
                    </div>
                    <br />
                    <div class="text-input07">
                        <input type="text" id="newPwd" value="${param.userPwd}" placeholder="请输入新密码"/>
                    </div>
                    <br />
                    <div class="text-input07">
                        <input type="text" id="code" value="${param.code}"placeholder="请输入验证码" />
                    </div>
                    <div class="fl">
                        <p style="line-height: 30px; margin-left: 10px">
                            <img id="codeImg" src="UserServlet?m=codeImg" alt=""/>
                            看不清？
                            <a href="javascript:changeImg()" class="login-redcolor">换一张</a>
                        </p>
                    </div>
                </div>
                <div class="act-botton clear"
                     style="margin: 20px 0; padding: 20px 0">
                    <div class="save-button">
                        <a href="javascript:" class="radius" onclick="changePwd()">保存</a>
                    </div>
                    <div class="cancel-button">
                        <a href="javascript:" class="radius" onclick="change('pwd',2)">取消</a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
</div>
</body>
</html>