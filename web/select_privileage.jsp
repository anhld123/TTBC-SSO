<%-- 
    Document   : select_privileage
    Created on : Dec 15, 2014, 1:48:25 PM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Phân quyền nhóm người dùng</title>
    </head>    
    <style type="text/css">        
        b { 
            font-weight: bold;
        }
        *{
            font: 12px Arial, Helvetica, sans-serif;
        }
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
            font-weight: normal;
            color: #018c3b;
        }

        input{
            border: 0px;
        }

        .BOLD input[type="text"]
        {
            font-weight: bold;
            font-size: 13px;
            width: 95%;
        }

        .ITALIC input[type="text"]
        {
            font-style: italic;
            font-size: 12px;
            width: 95%;
        }

        input[type="text"]
        {
            width: 95%;
        }

        .right {
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
    </style>
    <script>
        function selectall(chk) {
            var js_mntotal = document.getElementById("js_mn_total_id").value;
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
            var js_divcontent = "<u><b>Phân quyền theo chức năng:</b></u> "
                    + "<input type='hidden' value='"
                    + window.opener.document.getElementById("privileage_id").value.toString()
                    + "' readonly='true' id='new_privileage_id'/>";
            document.getElementById("mainDiv").innerHTML = js_divcontent;
        }
        function replaceAt(s, n, t) {
            return s.substring(0, n - 1) + t + s.substring(n);
        }
        ;
        function update_back_parent(e) {
                
            var js_parent_id = window.opener.document.getElementById("privileage_id");
            var js_parent_str = document.getElementById("js_administrator_pri_id").value.toString();
            var js_new_str = document.getElementById("new_privileage_id").value.toString();
            var js_mntotal = document.getElementById("js_mn_total_id").value;

            for (i = 1; i <= js_mntotal; i++) {

                var js_chktiem_name = "js_mnchk_" + i.toString();
                var js_chkitem_obj = document.getElementById(js_chktiem_name);
                var js_index = parseInt(js_chkitem_obj.name.toString().substr(9));

                if (js_chkitem_obj.checked === true) {
                    js_new_str = replaceAt(js_new_str, js_index, '1');
//                    if (js_index === 33)
//                    {
//                        alert('[1]' + js_new_str);
//                    }
                } else {
//                    if (js_index === 33)
//                    {
//                        alert('[2]' + js_new_str);
//                    }
                    js_new_str = replaceAt(js_new_str, js_index, '0');
                }
            }

            

//            if (js_new_str.localeCompare(js_parent_str) === 1) {
//                alert('Bạn không thể cấp quyền cao hơn cho người dùng');
//                e.preventDefault();
//            } else {
                
                var r = confirm('(Msg)Bạn chắc chắn muốn lưu thay đổi?');
                
                if (r === true) {
                    js_parent_id.value = js_new_str;
                    alert(js_new_str);
                } else {
                    e.preventDefault();
                }
                
                closeWindow();
//            }
        }
    </script>
    <body onload="initializeMainDiv();">
        <div>
            <div id="mainDiv"></div>    
        </div>    
        <hr/>
        <div>                    
            <form name="form1">
                <table border="1" cellspacing="0" cellpading="0" height="100%" 
                       class="tblmain">
                    <tr>
                        <th>Id</th>
                        <th>Mô tả</th>                    
                        <th><input type="checkbox" onclick="selectall(this);"></th>
                    </tr>
                    <s:set var="st_total" value="0"/>
                    <s:iterator value="menuItems" status="stat">
                        <tr>
                            <td style="width: 20px;" align="center">
                                <input type="text" readonly="true" value="<s:property value='menuId'/>" 
                                       style="text-align: center;"/>
                            </td>
                            <td style="width: 200px;">
                                <input type="text" readonly="true" value="<s:property value='text'/>" />
                            </td>                                           
                            <td style="width: 10px;" align="center">
                                <input type="checkbox" <s:property value='getStatus(isDisplay)'/> 
                                       id="js_mnchk_<s:property value='#stat.count'/>"
                                       name="js_mnchk_<s:property value='menuId'/>"/>
                            </td>
                        </tr>
                        <s:set var="st_total" value = "menuItems.size()" />                    
                    </s:iterator>
                    <input type="hidden" name="js_mntotal" 
                           value="<s:property value='%{#st_total}'/>" id="js_mn_total_id"/>    
                    <input type="hidden" name="js_administrator_pri_na" 
                           value="<s:property value='administrator_pri'/>" id="js_administrator_pri_id"/>
                </table>        
            </form>
        </div>   
        <hr/>        
        <div class="right">
            <a href="#" onclick="closeWindow();">
                Đóng</a>
            &nbsp; | &nbsp;
            <a href="#" onclick="update_back_parent(this);">
                Đồng ý</a></div>
    </body>    
</html>