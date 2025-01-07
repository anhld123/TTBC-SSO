<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<style>
    #subTable {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 80%;
    }
    #subTable th{
        background-color: #ddd;
        color: #0000FF;
    }

    #subTable th, #subTable td {
        border: 1px solid gray;
    }

    #subTable tr:nth-child(even){background-color: #f2f2f2;}

    #subTable tr:hover {background-color: #ddd;}

    .txtPublic{
        width: 85px;
    }
    .ui-datepicker-trigger{
        height: 100%;
    }
    .txtBody{
        text-align: center;
    }
    .txtBody > .ui-datepicker-trigger{
        display: none;
    }
    td.hdtitle {
        position: static;
        top: 0;
        z-index: 10;
    }
    #subTable_tmp {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 550px;
    }
    .table11 {
        float: left;
        width:80%;
        margin: 0 auto;
        margin-left: 8%; 
    }

    .table22 {
        float:right;
        width:80%;
        margin: 0 auto;
        margin-right:  8%; 
    }
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var popWindow;
            var max_row = 0;
        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;">             
            <div id="divTitle" style="text-align: center">
                DANH SÁCH CN ĐÃ GỬI DỮ LIỆU
            </div>
            <div style="height:10px"></div>   
            <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px">   
                <tr>
                    <th style="width: 50px;text-align: center">STT</th>
                    <th style="width: 80px;text-align: center"  >Mã CN</th>
                    <th style="width: 100px;text-align: center"  >Tên chi nhánh</th>
                    <th style="width: 100px;text-align: center"  >Người gửi dữ liệu</th>
                    <th style="width: 100px;text-align: center"  >Ngày gửi dữ liệu</th>
                    <th style="width: 100px;text-align: center"  >Trạng thái</th>
                    <th style="width: 100px;text-align: center"  >Mở khóa</th>
                </tr>  
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;text-align: center">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;text-align: center">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;text-align: center">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;text-align: center">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;text-align: center">(5)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;text-align: center">(7)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;text-align: center">(8)</th>
                </tr>

                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr style="font-weight: bold"> 
                        <s:if test="D4.equalsIgnoreCase('1')">
                            <td style="color: #003eff;text-align: center"><s:property value="%{#rowstatus.index + 1}" /></td>
                            <td style="color: #003eff;text-align: center"><s:property  value="D1" /></td>
                            <td style="color: #003eff"><s:property value="D2"/></td>
                            <td style="color: #003eff"><s:property value="D8"/></td>
                            <td style="color: #003eff;text-align: center"><s:property value="D9"/></td>
                            <td style="color: #003eff;text-align: center"><s:property value="D3"/></td>
                            <td style="color: #003eff;text-align: center"> 
                                <!--<a style="text-decoration: underline"  href="#" onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D5"/>', '<s:property value="D6"/>', '<s:property value="D7"/>', '<s:property value="D10"/>');">Mở dữ liệu</a>-->
                            </td>
                        </s:if>
                        <s:else>
                            <td style="text-align: center"><s:property value="%{#rowstatus.index + 1}" /></td>
                            <td style="text-align: center"><s:property  value="D1" /></td>
                            <td><s:property  value="D2" /></td>
                            <td><s:property  value="D8" /></td>
                            <td style="text-align: center"><s:property  value="D9" /></td>
                            <td style="text-align: center"><s:property  value="D3" /></td>
                            <td></td>
                        </s:else>

                    </s:iterator>
            </table>
        </div>      
        <div id="luu_thanhcong"></div>
        <script>
            function cancelAssign(D1, D5, D6, D7, D10) {
                var table = document.getElementById("subTable");
                // Chọn tất cả các liên kết chỉ trong bảng con
                var rows = table.querySelectorAll("td a");
                function unlockLinks() {
                    rows.forEach(function (row) {
                        row.style.pointerEvents = "auto"; // Kích hoạt lại sự kiện chuột
                        row.style.color = "red"; // Trả về màu mặc định
                    });
                }
                // Khóa các liên kết trong bảng
                rows.forEach(function (row) {
                    row.style.pointerEvents = "none";
                    row.style.color = "gray";
                });
                $.ajax({
                    type: "GET",
                    url: "unlock_QTNV_2024.action?" + "madiemgd=" + D1 + "&ngaybc=" + D5 + "&pos_flag=" + D6 + "&key_lock=" + D7 + "&sc3khoa=" + D10,
                    success: function (res) {
                        var status = parseInt(res.status);
                        //alert(status);
                        if (status === 1) {
                            alert('Mở phê duyệt thành công!');
                            $("#idSearch").click();
                        } else {
                            alert('Mở phê duyệt lỗi: ' + res.message);
                            unlockLinks();
                        }
                    },
                    error: function (res) {
                        alert("Mở phê duyệt lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                        unlockLinks();
                    }
                });
            }
        </script>
    </body>
</html>
