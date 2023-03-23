<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/css.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/menu.css"/>
    <title>用户管理</title>
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <script type="text/javascript">
        function update(id, type) {
            var msg;
            if (type == 'N') {
                msg = "停用"
            } else {
                msg = "启用"
            }
            layer.confirm('确定要' + msg + '吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.post("UserServlet?m=updateStatus",{"userId":id,"userStatus":type},function (data) {
                    if(data == "Y"){
                        layer.msg(msg + '成功');
                       window.location.reload();
                    }else {
                        layer.msg(msg + '失败');
                    }
                });

            });
        }

        function remakePwd(id) {
            $.post("UserServlet?m=againPwd",{"userId":id,"type":0},function (data) {
                if(data == "Y"){
                    layer.msg("重置成功");
                }else {
                    layer.msg("重置失败");
                }
            });
        }

    </script>
</head>
<body>
<div class="m-main">
    <div class="m-food">
        <div class="mf-top border-t">
            <div class="fl">用户管理</div>
            <div class="fr">
                <form action="UserServlet?m=userList" method="post">
                    <input id="startTime" name="startTime" value="${requestScope.startTime}" type="datetime-local" placeholder="注册时间-始" style="width:170px;"/>
                    -
                    <input id="endTime" name="endTime" value="${requestScope.endTime}" type="datetime-local" placeholder="注册时间-终" style="width:170px;"/>
                    <input id="userTel" name="userTel" value="${requestScope.userTel}" type="text" placeholder="手机号码"/>
                    <input type="submit" value="查询" style="width: 62px;height: 29px;">
                </form>
            </div>
        </div>

        <c:forEach items="${requestScope.userList}" var="user">
            <div class="mf-menu border-t">
                <div class="fl">
                    <span class="m-wt1"></span>
                    <span>${user.userTel}</span>
                    <span>${user.userName}</span>
                    <span>${user.userSex == "M" ? "男" : "女"}</span>
                    <span>${user.addTime}</span>
                </div>
                <div class="fr">
                    <button class="${user.userStatus.equals("Y")?"xiugai":"xiajia"}" id="${user.userId}" onclick="update(${user.userId},'${user.userStatus.equals("Y")?"N":"Y"}')">${user.userStatus.equals("Y")?"停用":"启用"}</button>
                    <button class="xiugai"  onclick="remakePwd(${user.userId})">重置密码</button>
                </div>
            </div>
        </c:forEach>
        <c:if test="${requestScope.msg=='无权限'}">
            <p style="color: red;font-size: 30px">当前用户无权限</p>
        </c:if>

        <div id="for" class="mf-top" style="width: 960px;text-align: center;margin-top: 10px">

            <ul class="pagination" style="">

                <c:forEach begin="1" end="${requestScope.pages}" var="pageNum" >
                    <li><a class="${pageNum == requestScope.pageNum?"active":""}" href="UserServlet?m=userList&pageNum=${pageNum}&startTime=${requestScope.startTime}&endTime=${requestScope.endTime}&userTel=${requestScope.userTel}">${pageNum}</a></li>
                </c:forEach>

<%--                <li><a href="#">1</a></li>--%>
<%--                <li><a class="active" href="#">2</a></li>--%>
<%--                <li><a href="#">3</a></li>--%>
            </ul>
        </div>

    </div>
</div>
</body>
</html>
<SCRIPT Language=VBScript><!--

//-->
</SCRIPT>