<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023/3/10
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>地址管理</title>
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

        function del(addressId) {
            layer.confirm('确定要删除吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.post("AddressServlet?m=del", {"addressId": addressId}, function (data) {
                    if (data == "1") {
                        layer.msg("删除成功")
                        window.location.reload();  //顶层页面刷新
                    }
                })
            });
        }

        function save() {

            let addressProvince = $("#a").val().trim();
            let addressCity = $("#b").val().trim();
            let addressDistrict = $("#c").val().trim();
            let addressDescribe = $("#d").val().trim();
            if (addressProvince.length >0 && addressCity.length >0 && addressDistrict.length >0) {
                if (addressDescribe.length >= 3 && addressDescribe.length <= 20) {

                    $.post("AddressServlet?m=save", {
                        "addressProvince": addressProvince,
                        "addressCity": addressCity,
                        "addressDistrict": addressDistrict,
                        "addressDescribe": addressDescribe
                    }, function (data) {
                        if (data == "1") {
                            layer.msg("新增成功")
                            window.location.reload();  //顶层页面刷新
                        } else {
                            layer.msg("该地址已存在");
                        }
                    })
                } else {
                    layer.msg("地址描述长度在3-20个字符");
                }
            } else {
                layer.msg("地址省市区不能为空");
            }

        }

        function updateAddress(addressId) {
            let addressProvince = $("#addressProvince" + addressId).val().trim();
            let addressCity = $("#addressCity" + addressId).val().trim();
            let addressDistrict = $("#addressDistrict" + addressId).val().trim();
            let addressDescribe = $("#addressDescribe" + addressId).val().trim();
            if (addressProvince.length >0 && addressCity.length >0 && addressDistrict.length >0) {
                if (addressDescribe.length >= 3 && addressDescribe.length <= 20) {
                    $.post("AddressServlet?m=update", {
                        "addressProvince": addressProvince,
                        "addressCity": addressCity,
                        "addressDistrict": addressDistrict,
                        "addressDescribe": addressDescribe,
                        "addressId": addressId
                    }, function (data) {
                        if (data == "1") {
                            layer.msg("修改成功")
                            window.location.reload();  //顶层页面刷新
                        } else {
                            layer.msg("该地址已存在");
                        }
                    })
                } else {
                    layer.msg("地址描述长度在3-20个字符");
                }
            } else {
                layer.msg("地址省市区不能为空");
            }
        }

    </script>
    <script src="js/distpicker.js"></script>
</head>
<body>
<div class="m-main">
    <div class="m-food">
        <div class="mf-top border-t">
            <div>
                地址管理
            </div>
        </div>
        <c:forEach items="${addressList}" var="a">
            <div class="mf-menu border-t"
                 style="height: auto; line-height:7px; padding: 30px 0">

                <div class="fl">
                    <span class="m-wt"></span>
                    <span> ${a.addressProvince}${a.addressCity}${a.addressDistrict}${a.addressDescribe}</span>
                </div>
                <div class="fr">
                    <button class="xiugai dingwei" onclick="change('address${a.addressId}',1)">
                        修改
                    </button>
                    <button class="del dingwei" onclick="del('${a.addressId}')">
                        删除
                    </button>
                </div>

                <div style="display: none;" class="change" id="update_address${a.addressId}">
                    <div style="padding-top: 20px" class="clear">
                        <span class="m-wt" style="padding: 0 30px; width: 70px"></span>
                        <div data-toggle="distpicker" style="margin-left: 135px">
                            <select id="addressProvince${a.addressId}" style="height: 32px" data-province="${a.addressProvince}"></select>
                            <select id="addressCity${a.addressId}" style="height: 32px" data-city="${a.addressCity}"></select>
                            <select id="addressDistrict${a.addressId}" style="height: 32px" data-district="${a.addressDistrict}"></select>
                            <input id="addressDescribe${a.addressId}" type="text" style="height: 26px" value="${a.addressDescribe}"/>
                        </div>

                    </div>

                    <div class="act-botton clear"
                         style="margin: 10px 0 10px 15px; padding: 10px 0">
                        <div class="save-button">
                            <a href="javascript:updateAddress(${a.addressId})" class="radius">保存</a>
                        </div>
                        <div class="cancel-button">
                            <a href="javascript:" class="radius"
                               onclick="change('address${a.addressId}',2)">取消</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="mf-top" style="margin-top: 30px">
        <div id="addaddress">
            <div style="line-height: 40px">
                <span class="m-wt" style="padding: 0 30px"></span><a href="#" class=" rb-red"
                                                                     onclick="change('addaddress',3)">+使用新地址</a>
            </div>
        </div>
        <div style="display: none;" id="insert_addaddress" class="change">
            <div style="margin-top: 20px">
                <span class="m-wt" style="padding: 0 30px"></span>
                <div data-toggle="distpicker" style="margin-left: 160px">
                    <select  id ="a" style="height: 32px" data-province="---- 选择省 ----"></select>
                    <select id ="b" style="height: 32px" data-city="---- 选择市 ----"></select>
                    <select id ="c" style="height: 32px" data-district="---- 选择区 ----"></select>
                    <input id ="d" type="text" style="height: 26px" placeholder="描述" />
                </div>
            </div>


            <div class="act-botton clear"
                 style="margin: 20px 40px; padding: 20px 0">
                <div class="save-button">
                    <a href="javascript:save()" class="radius">保存</a>
                </div>
                <div class="cancel-button">
                    <a href="javascript:" class="radius"
                       onclick="change('addaddress',4)">取消</a>
                </div>
            </div>
        </div>
        <div class="area clear"
             style="margin-top: 60px; font-size: 14px; color: #999">
            <span class="m-wt" style="padding: 0 30px"></span> 友情提示：
            <br/>
            <span class="m-wt" style="padding: 0 30px"></span>如果您选择不设置密码，您送餐信息的主要内容会以*号遮蔽，如：虹桥路2号，会显示为“虹﹡……﹡2号”。
            <br/>
            <span class="m-wt" style="padding: 0 30px"></span>该显示信息可能不受保护，建议您设置密码。
        </div>
    </div>
</div>
</body>
</html>