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
        width: 98%;
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
    @-webkit-keyframes my {
        0% { color: red; } 
        50% { color: #fff;  } 
        100% { color: red;  } 
    }
    @-moz-keyframes my { 
        0% { color: red;  } 
        50% { color: #fff;  }
        100% { color: red;  } 
    }
    @-o-keyframes my { 
        0% { color: red; } 
        50% { color: #fff; } 
        100% { color: red;  } 
    }
    @keyframes my { 
        0% { color: red;  } 
        50% { color: #fff;  }
        100% { color: red;  } 
    } 
    .color_11 {
        background:#fff;
        font-size:14px;
        font-weight:bold;
        -webkit-animation: my 700ms infinite;
        -moz-animation: my 700ms infinite; 
        -o-animation: my 700ms infinite; 
        animation: my 700ms infinite;
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
            $(document).ready(function () {
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".STT1").css({"width": "30px"});
                $(".STT2").css({"width": "90px"});
                $(".STT3").css({"width": "150"});
                $(".STT4").css({"width": "70%"});
                $(".STT5").css({"width": "70px"});
                $(".STT6").css({"width": "80px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "220px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });


        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 90%;">     
            <div id="divTitle">
                DANH SÁCH PGD CHỐT/GỬI DỮ LIỆU
            </div>
            <div style="height:10px"></div>  
            <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px">   
                <tr>
                    <th style="width: 50px">STT</th>
                    <th style="width: 80px">Mã PGD</th>
                    <th style="width: 150px">Tên PGD</th>
                    <th style="width: 100px">Người gửi dữ liệu</th>
                    <th style="width: 100px">Ngày gửi dữ liệu</th>
                    <th style="width: 150px">Trạng thái</th>
                    <th style="width: 100px">Mở PGD</th>
                    <th style="width: 100px">Gửi TW</th>
                </tr>  
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(8)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>
                        <s:if test="D1.equalsIgnoreCase('1')">
                            <td class="D0" style="color: #003eff"><s:property value="%{#rowstatus.index + 1}" /></td>
                            <td class="D0" style="color: #003eff"><s:property value="MAPGD"/></td>
                            <td style="color: #003eff"><s:property value="D2"/></td>
                            <td class="D0" style="color: #003eff"><s:property value="NGUOI_NHAP"/></td>
                            <td class="D0" style="color: #003eff"><s:property value="D3"/></td>
                            <td class = "D0" style="color: #003eff">PGD đã gửi dữ liệu</td>
                            <td class="D0"> <a style="text-decoration: underline" href="#" onclick="cancelAssign('<s:property value="skhoa"/>', '<s:property value="NGAYBC"/>', '<s:property value="MAPGD"/>', '<s:property value="D2"/>', '4');">Mở dữ liệu</a></td>
                            <td class="D0"> <a style="text-decoration: underline" href="#" onclick="senTW('<s:property value="skhoa"/>', '<s:property value="NGAYBC"/>', '<s:property value="MAPGD"/>', '<s:property value="D2"/>', '5');">Chốt dữ liệu</a></td>

                        </s:if>
                        <s:elseif test="D1.equalsIgnoreCase('2')">
                            <td class="D0" style="color: #3dc21b"><s:property value="%{#rowstatus.index + 1}" /></td>
                            <td class="D0" style="color: #3dc21b"><s:property value="MAPGD"/></td>
                            <td style="color: #3dc21b"><s:property value="D2"/></td>
                            <td class="D0" style="color: #3dc21b"><s:property value="NGUOI_NHAP"/></td>
                            <td class="D0" style="color: #3dc21b"><s:property value="D3"/></td>
                            <td class = "D0" style="color: #3dc21b">CN đã gửi dữ liệu</td>
                            <td></td>
                            <td class = "D0" style="color: #3dc21b">Đã chốt</td>
                        </s:elseif>
                        <s:else>
                            <td class="D0" style="color: red"><s:property value="%{#rowstatus.index + 1}" /></td>
                            <td class="D0" style="color: red"><s:property value="MAPGD"/></td>
                            <td style="color: red"><s:property value="D2"/></td>
                            <td class="D0" style="color: red"><s:property value="NGUOI_NHAP"/></td>
                            <td class="D0" style="color: red"><s:property value="D3"/></td>
                            <td class = "D0" style="color: red">Chưa gửi dữ liệu</td><td></td><td></td>
                        </s:else>
                    </tr>
                </s:iterator>
            </table>
        </div>      
        <div id="luu_thanhcong"></div>
        <script>
            function cancelAssign(skhoa, sngaybc, smapgd, stenpgd, stype) {
                var table = document.getElementById("subTable");
                var rows = table.querySelectorAll("td a");
                // Khóa các liên kết trong bảng
                rows.forEach(function (row) {
                    row.style.pointerEvents = "none"; // Vô hiệu hóa click
                    row.style.color = "gray";         // Thay đổi màu để trông như bị khóa
                });
                $.ajax({
                    type: "GET",
                    url: "idAddline_" + skhoa + ".action?" + "sngaybc=" + sngaybc + "&smapgd=" + smapgd + "&stype=" + stype,
                    success: function (res) {
                        var status = parseInt(res.status);
                        //alert(status);
                        if (status === 1) {
                            if (stype === '4') {
                                alert('Mở phê duyệt Pos ' + smapgd + ' - ' + stenpgd + ' thành công!');
                            } else if (stype === '5')
                            {
                                alert('Chốt dữ liệu Pos' + smapgd + ' - ' + stenpgd + ' lên TW thành công!');
                            }
                            onLoadData();
                        } else {
                            alert('Mở phê duyệt lỗi: ' + res.message);
                        }
                    },
                    error: function (res) {
                        alert("Mở phê duyệt lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                    }
                });
            }

            function senTW(skhoa, sngaybc, smapgd, stenpgd, stype) {
                var table = document.getElementById("subTable");
                var rows = table.querySelectorAll("td a");
                rows.forEach(function (row) {
                    row.style.pointerEvents = "none"; // Vô hiệu hóa click
                    row.style.color = "gray";         // Thay đổi màu để trông như bị khóa
                });
                var url, sdata;
                url = "senTW_" + skhoa + ".action?" + "sngaybc=" + sngaybc + "&smapgd=" + smapgd + "&stype=" + stype,
                        sdata = jQuery("#frmdata").serialize();
                $("#viewData").html('<img src="img/loading.gif"/>');
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        if (data === "200") {
                            alert("Gửi dữ liệu thành công!");
                            onLoadData();
                        }  else if (data === "100") {
                            alert("Lỗi: Chưa mở khóa tại TW, liên hệ ban Kế toán để hỗ trợ mở lại!");
                            onLoadData();
                        } else {
                            alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                            onLoadData();
                        }
                    },
                    error: function (request) {
                        alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                        onLoadData();
                    }
                });
            }
        </script>
    </body>
</html>
