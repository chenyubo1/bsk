<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">

    //菜单切换
    function changeMenu() {
        $("#menu").toggle();
    }

    /**
     * 判断用户是不是登录了
     * @param f
     */
    function checkUser(f) {
        if (f) {
            location.href = "menu.jsp";
        } else {
            show('', 550, 550, 'login.jsp');
        }
    }
</script>
<div class="m-top">
    <div class="box" id="j-top-nav">
        <a href="main.jsp">
            <div class="logo fl"></div>
        </a>
        <a href="MenuServlet?m=menuList">
            <div class="menu fl" style="width:100px">
                菜单
            </div>
        </a>

        <c:if test="${sessionScope.user != null}">
            <div class="separator fl"></div>
            <a href="member.jsp">
                <div class="menu fl" style="width:100px">
                    会员中心
                </div>
            </a>
            <input type="hidden" id="j-is-login" value="false"/>
        </c:if>
        <div class="clien fl">
            <a href="#" class="menu" onclick="changeMenu()">${user.userName}</a>
            <dl style="display: none; background-color: white;" id="menu"> <!-- 二级菜单 -->
                <dd><a href="UserServlet?m=out">安全退出</a></dd>
            </dl>
        </div>
        <div class="start fr" id="j-start-order"
             onclick="checkUser(${sessionScope.user == null?false:true})">
            立即点餐
        </div>
        <input type="hidden" id="isMemberLogin" value=""/>
        <input type="hidden" id="isNewLogin" value=""/>
        <input type="hidden" id="j-is-index" value="true"/>
        <input type="hidden" id="j-has-order" value="false"/>
        <input type="hidden" id="j-order-type" name="orderType" value="null"/>
        <input type="hidden" id="j-defaultClassHtmlName" value="Special.htm"/>
        <input type="hidden" id="j-username-afterlogin" value=""/>

    </div>
</div>