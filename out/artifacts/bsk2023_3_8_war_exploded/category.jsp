
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

    <title>分类管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="css/css.css"/>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <link rel="stylesheet" type="text/css" href="css/menu.css"/>
    <link rel="stylesheet" type="text/css" href="css/address.css"/>
    <script type="text/javascript"
            src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="layer/layer.js" type="text/javascript"></script>
    <script src="js/main.js" type="text/javascript"></script>
    <script type="text/javascript">


        function del(categoryId){
            layer.confirm('确定要删除吗？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                $.post("CategoryServlet?m=del", {"categoryId": categoryId}, function (data) {
                    if (data == "1") {
                        layer.msg("删除成功")
                        window.location.reload();  //顶层页面刷新
                    }else {
                        layer.msg("删除失败，该分类下有物品")
                    }
                })
            });
        }

        function save() {
                let categoryName = $("#categoryName").val().trim();
                if (categoryName.length>=1&& categoryName.length<20){
                    $.post("CategoryServlet?m=save",{"categoryName":categoryName},function (data) {
                        if (data == "1") {
                            layer.msg("新增成功")
                            window.location.reload();  //顶层页面刷新
                        } else {
                          layer.msg("添加分类不能重复")
                        }
                    })
                }else {
                    layer.msg('请输入分类名称字数大于一');
                    return;
                }
        }
        function updateCategory(categoryId) {

            let categoryName=$("#ca"+categoryId).val().trim();
            if (categoryName.length>=1&& categoryName.length<20){
                $.post("CategoryServlet?m=update",{"categoryId":categoryId,"categoryName":categoryName},function (data) {
                    if (data == "1") {
                        layer.msg("修改成功")
                        window.location.reload();  //顶层页面刷新
                    } else {
                        layer.msg("修改分类不能重复")
                    }
                })
            }else {
                layer.msg('请输入分类名称字数大于一');
                return;
            }
        }
    </script>
</head>
<body>
<div class="m-main">
    <div class="m-food">
        <div class="mf-top border-t">
            <div>
                分类管理
            </div>
        </div>
        <c:forEach items="${categoryList}" var="c">
        <div class="mf-menu border-t"
             style="height: auto; line-height: normal; padding: 30px 0">

            <div class="fl">
                <span class="m-wt"></span>
                <span>${c.categoryName}</span>
            </div>
            <div class="fr dingwei">
                <button class="xiugai" onclick="change('category${c.categoryId}',1)">
                    修改
                </button>
                <button class="del" onclick="del(${c.categoryId})">
                    删除
                </button>
            </div>

            <div id="update_category${c.categoryId}" style="display: none;" class="change">
                <div style="padding-top: 20px" class="clear">
                    <span class="m-wt" style="padding: 0 30px; width: 70px"></span>
                    <input type="text" id="ca${c.categoryId}" class="t-ad" style="width: 150px"
                           placeholder="${c.categoryName}" />
                </div>

                <div class="act-botton clear"
                     style="margin: 10px 0 10px 15px; padding: 10px 0">
                    <div class="save-button">
                        <a href="javascript:updateCategory(${c.categoryId})">保存</a>
                    </div>
                    <div class="cancel-button">
                        <a href="javascript:" class="radius"
                           onclick="change('category${c.categoryId}',2)">取消</a>
                    </div>
                </div>
            </div>
        </div>
        </c:forEach>
        <div class="mf-top" style="margin-top: 30px">
            <div id="addcategory">
                <div style="line-height: 40px">
                    <span class="m-wt" style="padding: 0 20px"></span><a href="#" class=" rb-red" onclick="change('addcategory',3)">+添加新分类</a>
                </div>
            </div>
            <div id="insert_addcategory" style="display: none;" class="change">
                <div style="padding-top: 20px" class="clear">
                    <span class="m-wt" style="padding: 0 30px; width: 70px"></span>
                    <input type="text" id="categoryName" class="t-ad" style="width: 150px"
                           placeholder="请输入分类名称" />
                </div>

                <div class="act-botton clear"
                     style="margin: 10px 0 10px 15px; padding: 10px 0">
                    <div class="save-button">
                        <a href="javascript:save()" class="radius">保存</a>
                    </div>
                    <div class="cancel-button">
                        <a href="javascript:" class="radius"
                           onclick="change('addcategory',4)">取消</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<SCRIPT Language=VBScript><!--

//--></SCRIPT>