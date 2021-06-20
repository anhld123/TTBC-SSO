<%-- 
    Document   : Nhập liệu BCQT Mẫu 01 - Báo cáo kiểm kê tiền mặt VNĐ thuộc quỹ nghiệp vụ
    Created on : Nov 10, 2015, 4:02:47 PM
    Author     : Sr. Chữ
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<sj:head jqueryui="false" jquerytheme="simple"/>
<s:head/>
<sj:head/>

<html>
    <head>
        <script>
           function onReturn()
            {
                $("#container").empty();
                $("#container").text('');
            } 
        </script>
    </head>
    <style>
        .metroButtonStyle {
            font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
            display: block;
            color: rgb(255, 255, 255);
            text-decoration: none;
            text-align: center;
            width: 90px;
            height: 26px;
            padding: 5px;
            margin: 5px 0px 0px 5px;
            font-size: 12px;
            background: none repeat scroll 0 0 #808080;
            color: #FFF;
            border: 0px none;
            border-radius: 1px 1px 1px 1px;
            outline: 0px none;
        }
        .metroButtonStyle:hover {
            background: #018c3b;
        }
        .metroButtonStyle:active {
            background: #DCDCDC;
        }
        .ui-dialog{
            font-size: 12px;
        }
        th{
            background-color: #DCDCDC;
            border-color: #999;
            height: 18px;
        }
        td{
            border-color: #999;
            height: 20px;
        }
        table.editDelete{
            border-collapse: collapse;
            width: 100%;
            border-color: #999;
        }
        table.editDelete tr:focus{
            background-color:#FFE47A;
            /*cursor: pointer; hover*/
        }
    </style>
    
    
    <body width = "100%">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td>
                        <center><span style="text-align: center; font-weight:bold; font-size: 20px; font-family: Times New Roman">BÁO CÁO KIỂM KÊ TIỀN MẶT VIỆT NAM ĐỒNG THUỘC QUỸ NGHIỆP VỤ</span></center>
                    </td>
                </tr>
                <tr><td><hr  width="30%" align="center" /></td></tr>
                <br>
            </table>
            <table width="100%" cellpadding="0" cellspacing="0">
                <tr>
                    <td style="width: 20%;">Ngày báo cáo: <sj:datepicker name="reportDate" value=""  
                                       onblur="validatedate(this.value)"
                                       placeholder="DD/MM/YYYY" changeYear="true" 
                                       changeMonth="true" displayFormat="dd/mm/yy"
                                       id="selectedrptDate" size="15"/>
                        <script>
                            var lj_curDate = new Date();
                            var lj_setDate = (lj_curDate.getDate() - 1) + "/" +
                                    (lj_curDate.getMonth() + 1) + "/" + lj_curDate.getFullYear();
                            document.getElementById("selectedrptDate").value = lj_setDate;
                        </script>
                    </td>
                    <td>
                        <input type="button" id="idLuudltmp" name="nameLuudltmp" onclick="submitloadData()" value="Hiển thị"/>
                        <input type="button" id="idLuudltmp" name="nameLuudltmp" onclick="submitloadData()" value="Lưu dữ liệu"/>
                        <input type="button" id="idReturn" name="nameReturn" onclick="onReturn()" value="Quay ra"/>
                    </td>
                </tr>
            </table>
            <br></br>
        </table>
        <table border="1" id="tableNT_QT01" class="editDelete" align="center">        
            <tr>
                <th class="MENH_GIA" rowspan="2">Mệnh giá</th>
                <th class="TM_TCLT_DU" colspan="2" >Tiền mặt đủ tiêu chuẩn lưu thông</th>     
                <th class="TM_TCLT_KO" colspan="2">Tiền mặt không đủ tiêu chuẩn lưu thông</th> 
                <th class="MENH_GIA" rowspan="2">Tổng tiền</th>
            </tr>
            <tr> 
                <th class="TM_TCLT_DU">Số lượng</th> 
                <th class="TM_TCLT_DU">Thành tiền</th> 
                <th class="TM_TCLT_KO">Số lượng</th> 
                <th class="TM_TCLT_KO">Thành tiền</th>
            </tr>
        </table>
    </body>
</html>
