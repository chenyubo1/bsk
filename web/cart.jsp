<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/3/17
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/handlebars@latest/dist/handlebars.js"></script>
<div class="m-shopping" id="cart">
    <div class="m-cart">
        <div id="close">
            <img src="images/common/close.png" class="m-img" />
        </div>
        <span> 我的购物盒</span> <a href="#" onclick="clearCart()">清空购物盒</a>
    </div>
    <div id="containerCart" style="overflow-y: auto">
        <script type="text/x-handlebars-template" id="templateCart">
            {{#each this}}
            <div class="border-bot eat">
                <div class=" eat-left fl">
                    <img src="upload/{{productPic}}" /> <span>{{productName}}</span>
                    <br /> <span class="login-redcolor">{{productPrice}}元</span>
                </div>
                <div class="fr  eat-right">
                    <button class="cursor">
                        <img src="images/common/minus-red.png" onclick="save({{productNum}}-1,{{cartId}})" />
                    </button>
                    <input type="text" placeholder="{{productNum}}" />
                    <button class="cursor">
                        <img  src="images/common/plus-green.png" onclick="save({{productNum}}+1,{{cartId}})" />
                    </button>
                </div>
            </div>
            {{/each}}
        </script>
    </div>
    <div class="login-bgrcolor eat-bot" id="cart_show">
        <img src="images/menu/box.png" class="e-img" /> <span
            class="e-top login-redcolor" id="num"> </span><strong class="e-title1">总计<span
            class="e-bigfont" id="cart_image"> </span><span>元</span>
    </strong>
        <button class="e-btn fr cu"
                onclick="a()">选好了 &gt;</button>
    </div>
</div>


<script>
    function a() {
        let total = $('#cart_image').text();
        location.href ="OrderServlet?m=orderList&money="+total;
    }
    //获取需要放数据的容器
    var containerCart;
    //获取我们定义的模板的dom对象。主要是想获取里面的内容
    var templateCart;

    $(document).ready(function () {
        containerCart = $('#containerCart');
        templateCart = $('#templateCart');
        getCart();

    });

    function clearCart() {
        layer.confirm('确定要清除吗？', {
            btn: ['确定','取消'] //按钮
        }, function() {
            $.post("CartServlet?m=delCart", {}, function (data) {
                if (data === "yes") {
                    getCart();
                    layer.msg("购物车已清空")
                } else {
                    layer.msg("清空失败");
                }
            });
        });
    }

    function save(productNum,cartId) {
        if (productNum == 0) {
            layer.confirm('确定要删除吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.post("CartServlet?m=updateCart", {"productNum": productNum, "cartId": cartId}, function (data) {
                    if (data === "yes") {
                        window.location.reload();
                        getCart();
                    } else {
                        layer.msg("修改失败");
                    }
                })
            });
        } else {
            $.post("CartServlet?m=updateCart", {"productNum": productNum, "cartId": cartId}, function (data) {
                if (data === "yes") {
                    getCart();
                } else {
                    layer.msg("修改失败");
                }
            })
        }
    }

    //获取购物车数据
    function getCart() {
        $.get("CartServlet?m=getCarts", function (data) {
            console.log(data);
            data = JSON.parse(data);
            let count = 0; //总金额
            let num = 0;  //餐品总数量

            for (let e of data) {
                count += e.productPrice * e.productNum;
                num += e.productNum;
            };


            count = Math.round(count * 100) / 100;

            $("#cart_image").html(count);
            $("#num").html(num);
            //编译模板的里的内容
            var template = Handlebars.compile(templateCart.html());
            //把后台获取到的数据渲染到页面
            containerCart.html(template(data));


        })
    }


</script>
