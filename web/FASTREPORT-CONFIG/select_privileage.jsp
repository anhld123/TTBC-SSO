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

<s:head/>
<sj:head/>

<!DOCTYPE html>
<html>
    <head>
        <title>Phân quyền sử dụng nhóm báo cáo nhanh</title>
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
            var js_divcontent = "<p class='title'><u>Phân quyền sử dụng nhóm báo cáo nhanh:</u></p>";
            document.getElementById("mainDiv").innerHTML = js_divcontent;
        }

    </script>
    <body onload="initializeMainDiv();">       
        <s:form name="form1" id="QUERYGROUP_PRI"
                action="#" theme="simple">
            <div id="mainDiv"></div>            
            <hr/>
            <div>                                
                <table border="1" cellspacing="0" cellpading="0"  
                       class="tblmain">
                    <tr>
                        <th>Group Id</th>
                        <th>Mô tả</th>                                                                    
                        <th><input type="checkbox" onclick="selectall(this);"></th>
                    </tr>
                    <s:set var="st_total" value="0"/>
                    <s:iterator value="groups" status="stat">
                        <tr>
                            <td style="width: 30px;" align="center">
                                <input type="text" readonly="true" 
                                       value="<s:property value='groupId'/>" 
                                       style="text-align: center;"
                                       id="oGROUP_<s:property value='#stat.count'/>"/>
                            </td>                                                                                           
                            <td style="width: 250px;">                                
                                <a href="#" class="tooltip animate"
                                   data-tool="<s:property value='descript'/>"><s:property value='descript'/></a>
                            </td>                                           
                            <td style="width: 10px;" align="center">
                                <c:if test="${privileage.equals('Y')}">
                                    <input type="checkbox"  
                                           id="js_mnchk_<s:property value='#stat.count'/>"
                                           checked
                                           />
                                </c:if>
                                <c:if test="${privileage.equals('N')}">
                                    <input type="checkbox"  
                                           id="js_mnchk_<s:property value='#stat.count'/>"
                                           />
                                </c:if>
                            </td>
                        </tr>
                        <s:set var="st_total" value = "groups.size()" />                    
                    </s:iterator>
                    <input type="hidden" name="js_mntotal" 
                           value="<s:property value='%{#st_total}'/>" id="js_mntotalid"/>    
                </table>                    
            </div>   
            <hr/>        
            <div class="right">
                <a href="#" onclick="closeWindow();">
                    Đóng</a>
                &nbsp; | &nbsp;
                <s:url id="saveDataUrl" action="SAVE_DATA_TEMP_QUERYGROUP.action">                                            
                </s:url>
                <sj:a id="saveUrl_id"  
                      href="%{saveDataUrl}"                                                                                                
                      formIds="QUERYGROUP_PRI"                                
                      targets="messageDiv"
                      button="false"                                                                                 
                      theme="simple"></sj:a>
                    <input type="hidden" id="group_Arr" value="" name="groupsStr"/>
                    <a href="#" onclick="update_back(this);">
                        Lưu trữ</a>
                    <script>
                        function update_back(e) {
                            var js_parent_id = window.opener.document.getElementById("group_Arr");
                            var js_mntotal = parseInt(document.getElementById("js_mntotalid").value);
                            var js_indigrp_str = "/";
                            for (i = 1; i <= js_mntotal; i++) {
                                var js_chktiem_name = "js_mnchk_" + i.toString();
                                var js_chkitem_obj = document.getElementById(js_chktiem_name);
                                var name = 'oGROUP_' + i;
                                var js_index = document.getElementById(name).value;
                                if (js_chkitem_obj.checked === true) {
                                    js_indigrp_str = js_indigrp_str + js_index + "/";
                                }
                            }
                            if (js_indigrp_str === "/") {
                                var r = confirm('(Msg)Bạn chưa chọn nhóm người dùng. Bạn có chắc chắn muốn thoát?');
                                if (r === true) {
                                    closeWindow();
                                } else {
                                    e.preventDefault();
                                }
                            } else {
                                var r = confirm('(Msg)Bạn chắc chắn muốn lưu thay đổi?');
                                if (r === true) {
                                    js_parent_id.value = js_indigrp_str;
                                    document.getElementById("group_Arr").value = js_indigrp_str;
                                    $("#messageDiv").empty();
                                    $('#saveUrl_id').click();
                                    //                    closeWindow();
                                } else {
                                    e.preventDefault();
                                }
                            }
                        }
                    </script>
                </div>
            <s:hidden name="userName" id="username_ID"/>
            <s:hidden name="module" id="module"/>
            <s:hidden name="applyRegion" id="applyRegion"/>
            <s:hidden name="groupId" id="groupId"/>
        </s:form>
        <div  style="float: left; width: 100%; font-family: Arial;font-size: 8pt;" >          
            <div id="loadingImageDiv" style="display: none;">
                <img id="loadingImage" src='img/loading.gif' border='0' >
            </div>            
            <div id="messageDiv"></div>                  
        </div>
    </body>    
</html>