<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="css/css.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link rel="stylesheet" type="text/css" href="css/menu.css" />
    <title>点餐菜单</title>
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script type="text/javascript" src="js/jquery.fly.min.js" ></script>
    <!-- 兼容IE10 -->
    <script type="text/javascript" src="js/requestAnimationFrame.js" ></script>
    <script src="js/main.js" type="text/javascript"></script>
    <script type="text/javascript">


        $(document).ready(function () {
            $(".m-main li").click(function () {
                $(".m-main li").removeClass("on");
                $(this).addClass("on");
            });
        });


        //添加餐品特效
        function productFly(t) {
            let cartLeft = $('#cart_image').offset().left - $(document).scrollTop(); // 获取a标签距离屏幕顶端的距离(因为fly插件的start开始位置是根据屏幕可视区域x，y来计算的，而不是根据整个文档的x，y来计算的)
            let cartTop = $('#cart_image').offset().top; // 获取a标签的y坐标
            let btnLeft = $(t).parent().find('img').offset().left - $(document).scrollTop() + 20;
            let btnTop = $(t).parent().find('img').offset().top + 20;

            let img = $(t).parent().find('img').attr('src');
            let flyer = $('<img class="u-flyer" src="' + img + '">');
            flyer.fly({
                start: {
                    left: btnLeft,
                    top: btnTop
                },
                end: {
                    left: cartLeft, //结束位置（必填）
                    top: cartTop, //结束位置（必填）
                    width: 0, //结束时宽度
                    height: 0 //结束时高度
                },
                onEnd: function () { //结束回调
                    this.destory(); //移除dom
                }
            });

        }

        function addCart(productId,userId,t) {
            let a = $("#"+productId).val().trim();
            if(userId === ""){
                show("",550,550,"login.jsp");
            }else{
                $.post("CartServlet?m=addCart",{"productId":productId,"userId":userId},function (data) {
                    if(data === "yes"){
                        productFly(t);
                        getCart();
                    }else{
                        layer.msg("添加失败");
                    }
                });
            }

        }

    </script>

</head>
<body style="background: #efeee9;">
<input type="hidden" id="pageName" value="customerCenter" />
<input type="hidden" id="morePrivilege" value="false" />
<form id="j-main-form" action="">

    <%@include file="top.jsp"%>
    <div class="m-main">
        <div class="m-menu fl">
            <ul>
                <!-- 菜单 -->
                <li class="${requestScope.categoryId == null?"on":""}" onclick="change(this)">
                    <a href="MenuServlet?m=menuList" > 当季特选</a>
                </li>
                <c:forEach items="${requestScope.categories}" var="category">
                    <li class="${requestScope.categoryId != null && requestScope.categoryId == category.categoryId ? "on" : ""}" id="extId2" cn="${category.categoryName}" en="" onclick="change(this)">
                        <a href="MenuServlet?m=menuList&categoryId=${category.categoryId}"> ${category.categoryName}</a>
                    </li>
                </c:forEach>
                <!-- /菜单 -->
            </ul>
        </div>
        <div class="m-menu-content fr" style="position: relative; top: 70px">
            <!-- 产品列表 -->
            <div style="height: 450px; display: none;"></div>
            <div style="overflow-y: auto;height: 230px">
            <div class="m-product-list">

                <c:forEach items="${requestScope.productDtos}" var="product">
                    <div class="product" style="background: #FFF">
                        <div class="img cursor">
                            <img src="upload/${product.productPic}" />
                        </div>
                        <div class="title">
                                ${product.productName}
                        </div>
                        <div class="desc grey"></div>
                        <div class="order j-menu-order" onclick="addCart('${product.productId}','${sessionScope.user.userId}',this)">
                            <div id="${product.productId}" class="start ui-bgbtn-green">
                                +
                            </div>
                        </div>
                    </div>
                </c:forEach>

            </div>
            </div>


            <!-- /产品列表 -->
        </div>
    </div>
    </div>
    </div>

    <div class="m-foot">
        <div class="content">
            <div class="content-logo">

                <a href="#"><div class="logo"></div> </a>

                <div class="phone strong-color">
                    4008-123-123
                </div>
            </div>
            <div class="content-legal">
                必胜客宅急送不同城市或不同餐厅的送餐菜单和价格有所不同。
                不同时段产品品项及价格有所不同。工作日特惠午餐及下午茶产品只在部分时段供应。详情以输入送餐地址后显示的实际供应的菜单为准。
            </div>

            <div class="contont-one">
                <div class="link">
                    <ul>
                        <li class="menu">
                            <a href="#">当季特选</a>
                        </li>
                        <li>
                            <a href="#">优惠套餐</a>
                        </li>
                        <li>
                            |
                        </li>
                        <li>
                            <a href="#">比萨</a>
                        </li>
                        <li>
                            |
                        </li>
                        <li>
                            <a href="#">意面</a>
                        </li>
                        <li>
                            |
                        </li>
                        <li>
                            <a href="#">饭食</a>
                        </li>
                        <li>
                            |
                        </li>
                        <li>
                            <a href="#">米线</a>
                        </li>
                        <li>
                            |
                        </li>
                        <li>
                            <a href="#">小吃</a>
                        </li>
                        <li>
                            |
                        </li>
                        <li>
                            <a href="#">饮料</a>
                        </li>
                        <li>
                            |
                        </li>
                        <li>
                            <a href="#">汤</a>
                        </li>
                        <li>
                            |
                        </li>
                        <li>
                            <a href="#">沙拉和鲜蔬</a>
                        </li>
                        <li>
                            |
                        </li>
                        <li>
                            <a href="#">甜点</a>
                        </li>
                        <li class="margin-right">
                            <a href="#" target="_blank">帮助中心</a>
                        </li>
                        <li class="margin-right">
                            <a href="#">会员中心</a>
                        </li>
                    </ul>
                </div>
            </div>



            <div class="content-two clear">

                <div class="middle clear">
                    <div class="fl yum-name">
                        版权所有&nbsp;百胜咨询（上海）有限公司
                    </div>
                    <div class="fl">
                        <ul>
                            <li>
                                <a href="#" target="_blank">法律条款</a>
                            </li>
                            <li>
                                |
                            </li>
                            <li>
                                <a href="#" target="_blank">经营公示</a>
                            </li>
                            <li>
                                |
                            </li>
                            <li>
                                <a href="#" target="_blank">隐私条款</a>
                            </li>
                            <li>
                                |
                            </li>
                            <li>
                                <a href="#" target="_blank">联系我们</a>
                            </li>
                            <li>
                                |
                            </li>
                            <li>
                                <a href="#" target="_blank">加入我们</a>
                            </li>
                        </ul>
                    </div>
                    <div class="other fr">
                        <a href="http://www.miitbeian.gov.cn" target="_blank">沪ICP备&nbsp;17029211-1号</a>
                    </div>
                </div>
            </div>
        </div>
    </div>




</form>
<!-- 购物车-->

<c:if test="${sessionScope.user != null}">
    <%@include file="cart.jsp"%>
</c:if>

</body>
</html>