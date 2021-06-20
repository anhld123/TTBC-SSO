<%-- 
    Document   : select_privileage
    Created on : Dec 15, 2014, 1:48:25 PM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:head/>
<sj:head/>

<!DOCTYPE html>
<html>
    <head>
        <title>Phân quyền nhóm người dùng</title>
    </head>    
    <style type="text/css">        
/*        b { 
            font-weight: bold;
        }*/
        
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
/*            padding: 5px;*/
            padding-top: 5px;
            padding-right: 8px;
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
            font-family: Arial; 
            font-weight: bold;
            color:#5e5e55;
            font-size: 13pt;                     
        }            
        
        .mylink {
            font-family: Arial; 
            font-size: 11pt; 
            font-weight: bold;
            background-color: #67b168;
            color: white;
        }
    </style>
    <style>
        .control {
            font-family: arial;
            display: block;
            position: relative;
            padding-left: 5px;
            margin-bottom: 5px;
            padding-top: 3px;
            cursor: pointer;
            font-size: 16px;
        }
            .control input {
                position: absolute;
                z-index: -1;
                opacity: 0;
            }
        .control_indicator {
            position: absolute;
            top: 0;
            left: 25px;
            height: 20px;
            width: 20px;
            background: #e6e6e6;
            border: 0px solid #000000;
            border-radius: 0px;
        }
        .control:hover input ~ .control_indicator,
        .control input:focus ~ .control_indicator {
            background: #cccccc;
        }
        
        .control input:checked ~ .control_indicator {
            background: #2aa1c0;
        }
        .control:hover input:not([disabled]):checked ~ .control_indicator,
        .control input:checked:focus ~ .control_indicator {
            background: #0e6647d;
        }
        .control input:disabled ~ .control_indicator {
            background: #e6e6e6;
            opacity: 0.6;
            pointer-events: none;
        }
        .control_indicator:after {
            box-sizing: unset;
            content: '';
            position: absolute;
            display: none;
        }
        .control input:checked ~ .control_indicator:after {
            display: block;
        }
        .control-checkbox .control_indicator:after {
            left: 8px;
            top: 4px;
            width: 3px;
            height: 8px;
            border: solid #ffffff;
            border-width: 0 2px 2px 0;
            transform: rotate(45deg);
        }
        .control-checkbox input:disabled ~ .control_indicator:after {
            border-color: #7b7b7b;
        }
        .control-checkbox .control_indicator::before {
            content: '';
            display: block;
            position: absolute;
            left: 0;
            top: 0;
            width: 4.5rem;
            height: 4.5rem;
            margin-left: -1.3rem;
            margin-top: -1.3rem;
            background: #2aa1c0;
            border-radius: 3rem;
            opacity: 0.6;
            z-index: 99999;
            transform: scale(0);
        }
        @keyframes s-ripple {
            0% {
                transform: scale(0);
            }
            20% {
                transform: scale(1);
            }
            100% {
                opacity: 0;
                transform: scale(1);
            }
        }
        @keyframes s-ripple-dup {
           0% {
               transform: scale(0);
            }
           30% {
                transform: scale(1);
            }
            60% {
                transform: scale(1);
            }
            100% {
                opacity: 0;
                transform: scale(1);
            }
        }
        .control-checkbox input + .control_indicator::before {
            animation: s-ripple 250ms ease-out;
        }
        .control-checkbox input:checked + .control_indicator::before {
            animation-name: s-ripple-dup;
        }
    </style>
    <script>
        function selectall(chk) {
            var total = parseInt($("#id_report_total").val());            
            for (i = 1; i <= total; i++) {
                var chktiem_name = "id_check_" + i.toString();
                var chkitem_obj = document.getElementById(chktiem_name);
                if (chkitem_obj !== null && chkitem_obj.type && chkitem_obj.type === 'checkbox') {
                    if (chk.checked === true) {
                        chkitem_obj.checked = true;
                    } else {
                        chkitem_obj.checked = false;
                    }
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
            var js_divcontent = "<p class='header'><u><b>Phân quyền theo báo cáo:</b></u></p> ";
            document.getElementById("mainDiv").innerHTML = js_divcontent;
        }
        function replaceAt(s, n, t) {
            return s.substring(0, n - 1) + t + s.substring(n);
        };
        function saveData() {
            var userGroupCode = $("#userGroupCode").val();
            var reports=[];
            var total = parseInt($("#id_report_total").val());
            for (i = 1; i <= total; i++) {
                var chktiem_name = "id_check_" + i.toString();
                var chkitem_obj = document.getElementById(chktiem_name);     
                if (chkitem_obj !== null && chkitem_obj.type && chkitem_obj.type === 'checkbox') {
                    if (chkitem_obj.checked === true) {
                        reports.push(chkitem_obj.name.toString());
                    }
                }
            }       
            //alert(reports.length);
            $.ajax({
                method: "POST",
                url: "saveOwnerReports.action",
                data: { userGroupCode: userGroupCode, reports : reports },   
                dataType : "json",
                success:function(data)
                {
                    //var msg = data.error_msg;
                    alert("Lưu dữ liệu thành công");
                },
                error : function(data) {
                    alert("Có lỗi xảy ra");
                }
            });
        };        
    </script>
    <body onload="">
        <table style="width: 100%; border: 0;">
            <tr>
                <td><p class="header"><u><b>Phân quyền theo báo cáo:</b></u></p> </td>
            </tr>
            <tr>
                <td style="text-align: right;">
                    <a href="#" onclick="closeWindow();" class="mylink">Đóng</a>
                    &nbsp; | &nbsp;
                    <a href="#" onclick="saveData();" class="mylink">Lưu trữ</a>
                </td>
            </tr>                          
        </table>    
        <hr/>
        <div>                    
            <form name="form1">
                <s:hidden name="userGroupCode" />
                <table border="1" cellspacing="0" cellpading="0" height="100%" 
                       class="tblmain">
                    <tr>
                        <th style="width: 30px;" align="center">Thứ tự</th>
                        <th>Mã báo cáo</th>
                        <th>Mô tả</th>                    
                        <th style="width: 30px;" align="center">                            
                            <label class="control control-checkbox">                                        
                                        <input type="checkbox" onclick="selectall(this);"/>
                                        <div class="control_indicator"></div>
                                    </label>
                                        &nbsp;
                        </th>
                    </tr>
                    <s:set var="st_total" value="0"/>
                    <s:iterator value="ownerReports" status="stat">
                        <tr>
                            <td style="width: 30px;" align="center">
                                <input type="text" readonly="true" value="<s:property value='#stat.count'/>" 
                                       style="text-align: center;"/>
                            </td>
                            <td style="width: 100px;" align="center">
                                <input type="text" readonly="true" value="<s:property value='MaBC'/>" 
                                       style="text-align: center;"
                                       id="id_report_<s:property value='#stat.count'/>"/>
                            </td>
                            <td style="width: 250px;">
                                <s:if test="%{MaBC.trim() != null && MaBC.trim() != ''}">
                                    <input type="text" readonly="true" value="<s:property value='TenBC'/>" />
                                </s:if>
                                <s:else>
                                    <p style="font-weight: bold;"><s:property value='TenBC'/></p>                                    
                                </s:else>
                                
                            </td>                                           
                            <td style="width: 30px;" align="center">
                                <s:if test="%{MaBC.trim() != null && MaBC.trim() != ''}">
                                    <label class="control control-checkbox">                                        
                                        <input type="checkbox" <s:property value='getStatus(QuyenTC)'/>  
                                               id="id_check_<s:property value='#stat.count'/>"
                                           name="<s:property value='MaBC'/>"/>
                                        <div class="control_indicator"></div>
                                    </label>
                                        &nbsp;
                                </s:if>
                            </td>
                        </tr>
                        <s:set var="st_total" value = "ownerReports.size()" />                    
                    </s:iterator>
                    <input type="hidden" value="<s:property value='%{#st_total}'/>" id="id_report_total"/>                        
                </table>        
            </form>
        </div>                   
    </body>    
</html>