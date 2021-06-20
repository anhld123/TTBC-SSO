<%-- 
    Document   : select_privileage
    Created on : Dec 15, 2014, 1:48:25 PM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Phân nhóm chỉ tiêu</title>
        <link rel="stylesheet" type="text/css" media="all" href="css/tooltip.css">
    </head>    
    <style type="text/css">        
        b { 
            font-weight: bold;
        }
        /*        *{
                    font: 12px Arial, Helvetica, sans-serif;
                }*/
        table{            
            border-style: solid;
            border-collapse: collapse;
            width: 100%;
            line-height: 19px;
        }
        .tblmain th{
            background-color: #5e5e55;
            font-weight: bold;
            color: #fff;
            text-align: center;
            padding: 5px;
        }        
        .tblmain tr td{
            font: 12px Arial, Helvetica, sans-serif;
            font-weight: normal;
            color: #018c3b;
        }

        input{
            font: 12px Arial, Helvetica, sans-serif;
            border: 0px;
        }

        .BOLD input[type="text"]
        {
            font-weight: bold;
            /*            font-size: 13px;*/
            width: 95%;
        }

        .ITALIC input[type="text"]
        {
            font-style: italic;
            /*            font-size: 12px;*/
            width: 95%;
        }

        input[type="text"]
        {
            width: 95%;
        }

        .right {
            font: 12px Arial, Helvetica, sans-serif;
            position: absolute;
            right: 0px;
            width: 120px;
            background-color: #b0e0e6;  
            margin-bottom: 10px;
            margin-right: 10px;
            text-align: center;            
        }

        .header {
            font-weight: bold;
            color:#5e5e55;
            font-size: 13pt;                     
        }       

        p.title {
            font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
            font-size: 12pt;            
            font-weight: bold;
            color: #018c3b;
            margin: 0 0 0 0;
        }

    </style>
    <script>
        function selectall(chk) {
            var js_mntotal = document.getElementById("js_mntotalid").value;
            for (i = 1; i <= js_mntotal; i++) {
                var js_chktiem_name = "js_mnchk_" + i.toString();
                var js_chkitem_obj = document.getElementById(js_chktiem_name);
                if (chk.checked === true) {
                    js_chkitem_obj.checked = true;
                } else {
                    js_chkitem_obj.checked = false;
                }
            }
        }
        function closeWindow() {
            if (navigator.userAgent.indexOf('Chrome') !== -1
                    && parseFloat(navigator.userAgent.substring(
                            navigator.userAgent.indexOf('Chrome') + 7
                            ).split(' ')[0]) >= 15)
            {
                window.open('', '_self', '');
                self.close();
                return false;
            } else {
                window.open('', '_parent', '');
                window.close();
            }
        }
        function initializeMainDiv() {
            var js_divcontent = "<p class='title'><u>Phân nhóm chỉ tiêu gửi đi:</u></p>";
            document.getElementById("mainDiv").innerHTML = js_divcontent;
        }
        function update_back(e) {
            var js_parent_id = window.opener.document.getElementById("ji_indigroup");
            var js_mntotal = parseInt(document.getElementById("js_mntotalid").value);
            var js_indigrp_str = "/";
            for (i = 1; i <= js_mntotal; i++) {
                var js_chktiem_name = "js_mnchk_" + i.toString();
                var js_chkitem_obj = document.getElementById(js_chktiem_name);
                var js_index = parseInt(js_chkitem_obj.name.toString().substr(9));
                if (js_chkitem_obj.checked === true) {
                    js_indigrp_str = js_indigrp_str + js_index.toString() + "/";
                }
            }
            if (js_indigrp_str === "/") {
                var r = confirm('(Msg)Bạn chưa chọn nhóm chỉ tiêu. Bạn có chắc chắn muốn thoát?');
                if (r === true) {
                    closeWindow();
                } else {
                    e.preventDefault();
                }
            } else {
                var r = confirm('(Msg)Bạn chắc chắn muốn lưu thay đổi?');
                if (r === true) {
                    js_parent_id.value = js_indigrp_str;
                    closeWindow();
                } else {
                    e.preventDefault();
                }
            }
        }
        function js_checkChild(chk) {
            var js_mntotal = document.getElementById("js_mntotalid").value;
            var js_index = parseInt(chk.id.toString().substr(9));
            var js_objchk_code = "js_code_" + js_index.toString();
//            alert(js_objchk_code);
            var js_chk_value = document.getElementById(js_objchk_code).value;
//            alert(js_chk_value);
            for (i = 1; i <= js_mntotal; i++) {
                var js_chktiem_name = "js_mnchk_" + i.toString();
                var js_obj_code = "js_code_" + i.toString();
                var js_obj_value = document.getElementById(js_obj_code).value;                
                if (js_obj_value.length > js_chk_value.length) {                  
//                    alert(js_chk_value.length);
//                    alert(js_obj_value.substr(1,5));
//alert(js_obj_value.substr(0,js_chk_value.length-1));
                    if (js_chk_value === js_obj_value.substr(0, js_chk_value.length)) {
                        var js_chkitem_obj = document.getElementById(js_chktiem_name);
                        if (chk.checked === true) {
                            js_chkitem_obj.checked = true;
                        } else {
                            js_chkitem_obj.checked = false;
                        }
                    }
                }
            }
        }

        function callComment(object) {
            var ht1 = 200;//screen.availHeight - 100;
            var wt1 = 600;
            var left1 = (screen.width / 2) - (wt1 / 2) + 20;
            var top1 = (screen.height / 3) -10;
                        var username = document.getElementById('username_ID').value;
            window.open('sbv_indicator_note.action?group_id=' + object+'&username='+username, 'IMS_REPORTS_0',
                    "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1
                    + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        }
    </script>
    <body onload="initializeMainDiv();">       
        <div id="mainDiv"></div>            
        <hr/>
        <div>                    
            <form name="form1">
                <table border="1" cellspacing="0" cellpading="0" height="100%" 
                       class="tblmain">
                    <tr>
                        <th>Id</th>
                        <th>Phân nhóm</th>                                            
                        <th>Mô tả</th>
                        <th><input type="checkbox" onclick="selectall(this);"></th>
                    </tr>
                    <s:set var="st_total" value="0"/>
                    <s:iterator value="sendIndiList" status="stat">
                        <tr>
                            <td style="width: 15px;" align="center">
                                <input type="text" readonly="true" value="<s:property value='id'/>" 
                                       style="text-align: center;"/>
                            </td>                            
                            <td style="width: 20px;">
                                <c:if test="${not empty group}">
                                    <a 
                                        id="js_code_<s:property value='#stat.count'/>"
                                        onclick="callComment('<s:property value='code'/>');"
                                        href="#"
                                        class="tooltip bottom"
                                        data-tool="Nhập thuyết minh"><u><s:property value='code'/></u></a>

                                </c:if>    
                                <c:if test="${empty group}">
                                <input type="text" readonly="true" value="<s:property value='code'/>" 
                                       id="js_code_<s:property value='#stat.count'/>"/>
                                </c:if>  

                            </td>                                           
                            <td style="width: 250px;">                                
                                <a href="#" class="tooltip animate"
                                   data-tool="<s:property value='name'/>"><s:property value='name'/></a>
                            </td>                                           
                            <td style="width: 10px;" align="center">
                                <input type="checkbox"  
                                       id="js_mnchk_<s:property value='#stat.count'/>"
                                       name="js_mnchk_<s:property value='gen_id'/>"
                                       onclick="js_checkChild(this);"/>
                            </td>
                        </tr>
                        <s:set var="st_total" value = "sendIndiList.size()" />                    
                    </s:iterator>
                    <input type="hidden" name="js_mntotal" 
                           value="<s:property value='%{#st_total}'/>" id="js_mntotalid"/>    
                </table>        
            </form>
        </div>   
        <hr/>        
        <div class="right">
            <a href="#" onclick="closeWindow();">
                Đóng</a>
            &nbsp; | &nbsp;
            <a href="#" onclick="update_back(this);">
                Đồng ý</a>
        </div>
        <s:hidden name="userName" id="username_ID"/>
    </body>    
</html>