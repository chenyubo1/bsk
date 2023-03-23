<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/3/15
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/css.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/menu.css"/>
    <title>餐品管理</title>
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <script type="text/javascript">

        function del(id) {
            layer.confirm('确定要删除吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.post("ProductServlet?m=detele", {"productId": id}, function (data) {
                    if (data == "Y") {
                        layer.msg('删除成功');
                         window.location.reload()
                        
                    } else {
                        layer.msg(msg + '删除失败');
                    }
                });
            });
        }

        function update(id, type) {
            var msg;
            if (type == 'N') {
                msg = "下架"
            } else {
                msg = "上架"
            }
            layer.confirm('确定要' + msg + '吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.post("ProductServlet?m=updateStatus", {"productId": id, "productStatus": type}, function (data) {
                    if (data == "Y") {
                        layer.msg(msg + '成功');
                        window.location.reload();
                    } else {
                        layer.msg(msg + '失败');
                    }
                });
            });
        }

        /**
         * 上传图片
         */
        function upload(id) {
            if ($("#photoFile" + id).val() == '') {
                return;
            }
            let formData = new FormData();
            formData.append('photoFile', $('#photoFile' + id)[0].files[0]);
            $.ajax({
                url: "ProductServlet?m=upload",
                type: "post",
                data: formData,
                contentType: false,
                processData: false,
                success: function (data) {
                    $("#productPic" + id).val(data);
                }
            });
        }

    </script>
    <style type="text/css">
        #a {
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
            width: 138px;
            height: 24px;
            display: inline-block;
        }
    </style>
</head>
<body>
<div class="m-main">
    <div class="m-food" style="overflow-y: auto">
        <div class="mf-top border-t">
            <div class="fl">
                餐品管理
            </div>
            <div class="fr">
                <form action="ProductServlet?m=productList" method="post">
                    <select name="categoryId" style="height: 28px">
                        <option value="">请选择</option>
                        <c:forEach items="${categores}" var="ca">
                            <option ${param.categoryId == ca.categoryId?"selected":""}
                                    value="${ca.categoryId}">${ca.categoryName}</option>
                        </c:forEach>
                    </select>
                    <input type="text" name="productName" value="${param.productName}"/>
                    <button>
                        搜索
                    </button>
                </form>
            </div>
        </div>
        <c:forEach items="${requestScope.productDtos}" var="p">
            <div class="mf-menu clear"
                 style="line-height: normal; padding: 30px 0; width:900px; height:auto;
                    ">
                <div class="fl" style="width: 600px;height: 100px">
                    <img src="upload/${p.productPic}"
                         width="100" align="center"/>
                    <span>${p.productName}</span>
                    <span>${p.productPrice}</span>
                    <span id="a">${p.productDescribe}</span>
                    <span>${p.categoryName}</span>
                </div>
                <div class="fr weizhi" style="width:300px">
                    <button class="${p.productStatus == "Y"?"shangjia":"xiajia"}"
                            onclick="update(${p.productId},'${p.productStatus == "Y"?"N":"Y"}')">
                            ${p.productStatus == "Y"?"下架":"上架"}
                    </button>
                    <button class="xiugai" onclick="change('product${p.productId}',1)">
                        修改
                    </button>
                    <button class="del" onclick="del('${p.productId}')">
                        删除
                    </button>
                </div>
                <div id="update_product${p.productId}" style="display: none;" class="change">
                    <div class="new-food clear">
                        <div>
                            <input type="file" id="photoFile${p.productId}" onchange="upload(${p.productId})"/>
                            <input type="hidden" name="productPic" id="productPic${p.productId}"/>
                            <input placeholder="餐品名" type="text" id="productName${p.productId}" value="${p.productName}"/>
                            <input placeholder="单价" type="text" id="productPrice${p.productId}" value="${p.productPrice}"/>
                        </div>
                        <div>
                            <input placeholder="描述" type="text" style="width: 390px"
                                   id="productDescribe${p.productId}" value="${p.productDescribe}"/>
                            <select id="categoryId${p.productId}" style="height: 28px">
                                <option value="${ca.categoryName}">请选择</option>
                                <c:forEach items="${categores}" var="ca">
                                    <option value="${ca.categoryId}">${ca.categoryName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <input type="submit" onclick="updatep(${p.productId})" class="nw-btn xiugai m-submit"
                                   style="border: none; height: 40px"/>
                            <input type="reset" value="取消" onclick="change('product${p.productId}',2)"
                                   class="del" style="height: 40px; border-radius: 5px"/>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
        <div class="mf-top clear">
            <div class="fl" style="line-height: 40px; margin-top:40px">
                <a href="#" class=" rb-red" onclick="change('addproduct',3)">+添加新餐品</a>
            </div>
            <div id="insert_addproduct" style="display: none;" class="change">
                <div class="new-food clear">
                    <div>
                        <input type="file" id="photoFile0" onchange="upload(0)"/>
                        <input type="hidden" name="productPic" id="productPic0"/>
                        <input value="${p.productName}" type="text" id="productName"/>
                        <input value="${p.productPrice}" type="text" id="productPrice"/>
                    </div>
                    <div>
                        <input value="${p.productDescribe}" type="text" style="width: 390px" id="productDescribe"/>
                        <select id="categoryId" style="height: 28px">
                            <option value="">请选择</option>
                            <c:forEach items="${categores}" var="ca">
                                <option value="${ca.categoryId}">${ca.categoryName}</option>
                            </c:forEach>
                        </select>
                        <a class="img-down"><img src="images/detail/show-more.png"/>
                        </a>
                    </div>
                    <div>
                        <input onclick="addProduct()" type="submit" class="nw-btn xiugai m-submit"
                               style="border: none; height: 40px"/>
                        <input type="reset" value="取消" onclick="change('addproduct',4)"
                               class="del" style="height: 40px; border-radius: 5px"/>
                    </div>
                </div>
            </div>

        </div>


    </div>
    <div>
        <ul class="pagination fr" style="margin-right: 350px; margin-top:40px">
            <c:if test="${requestScope.pageNum > 1}">
                <a href="ProductServlet?m=productList&pageNum=${requestScope.pageNum-1}&productName=${param.productName}&categoryName=${param.categoryName}">上一页</a> |
            </c:if>
            <c:forEach begin="1" end="${requestScope.pages}" var="u">
                <li>
                    <a href="ProductServlet?m=productList&pageNum=${u}&productName=${param.productName}&categoryName=${param.categoryName}"
                       class="${requestScope.pageNum==u?'active':""}">${u}</a></li>
            </c:forEach>
            <c:if test="${requestScope.pageNum < requestScope.pages}">
                <a href="ProductServlet?m=productList&pageNum=${requestScope.pageNum+1}&productName=${param.productName}&categoryName=${param.categoryName}">下一页</a> |
                <a href="ProductServlet?m=productList&pageNum=${requestScope.pages}&productName=${param.productName}&categoryName=${param.categoryName}">尾页</a>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>
<script type="text/javascript">
    function addProduct() {


        let productPic = $("#productPic0").val().trim();
        let productName = $("#productName").val().trim();
        let productPrice = $("#productPrice").val().trim();
        let productDescribe = $("#productDescribe").val().trim();
        let categoryId = $("#categoryId").val().trim();

        if (productPic != "") {
            if (productName.length >= 2 && productName.length <= 20) {
                if (/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(productPrice)) {
                    if (productDescribe.length >= 2 && productDescribe.length <= 20) {
                        if (categoryId != "") {
                            $.post("ProductServlet?m=addProduct", {
                                "productPic": productPic,
                                "productName": productName,
                                "productPrice": productPrice,
                                "productDescribe": productDescribe,
                                "categoryId": categoryId
                            }, function (data) {
                                if (data == "Y") {
                                    window.location.reload()
                                } else if (data == "N") {
                                    layer.msg("添加商品重复")
                                } else {
                                    layer.msg("添加商品失败")
                                }
                            })
                        } else {
                            layer.msg("请选择餐品分类");
                        }
                    } else {
                        layer.msg("请入2~20个字符的描述");
                    }
                } else {
                    layer.msg("请入有效的单价");
                }
            } else {
                layer.msg("请输入有效的名称");
            }
        } else {
            layer.msg("请选择餐品图片");
        }
    }

    function updatep(id) {
        let productPic = $("#productPic" + id).val().trim();
        let productName = $("#productName" + id).val().trim();
        let productPrice = $("#productPrice" + id).val().trim();
        let productDescribe = $("#productDescribe" + id).val().trim();
        let categoryId = $("#categoryId" + id).val().trim();

        if (productPic != "") {
            if (productName.length >= 2 && productName.length <= 20) {
                if (/^(([1-9][0-9]*)|(([0]\.\d{1,2}|[1-9][0-9]*\.\d{1,2})))$/.test(productPrice)) {
                    if (productDescribe.length >= 2 && productDescribe.length <= 20) {
                        if (categoryId != "") {
                            $.post("ProductServlet?m=updateProduct", {
                                "productPic": productPic,
                                "productName": productName,
                                "productPrice": productPrice,
                                "productDescribe": productDescribe,
                                "categoryId": categoryId,
                                "productId": id
                            }, function (data) {
                                if (data == "Y") {
                                    window.location.reload()
                                } else if (data == "N") {
                                    layer.msg("修改商品重复")
                                } else {
                                    layer.msg("修改商品失败")
                                }
                            })
                        } else {
                            layer.msg("请选择餐品分类");
                        }
                    } else {
                        layer.msg("请入2~20个字符的描述");
                    }
                } else {
                    layer.msg("请入有效的单价");
                }
            } else {
                layer.msg("请输入有效的名称");
            }
        } else {
            layer.msg("请选择餐品图片");
        }
    }
</script>