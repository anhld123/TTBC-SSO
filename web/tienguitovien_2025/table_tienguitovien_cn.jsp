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

            function funcTableFile(D1, D7, type) {
                var w = 900, h = 500;
                var left = (screen.width / 2) - (w / 2);
                var top = (screen.height / 2) - (h / 2);
                var urlParam = "madiemgd=" + D1 + "&ngaybc=" + D7 + "&type=" + type;
                var url = "/IMS_REPORTS/popuptienguitovien.action?" + urlParam;
                popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }
        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 80%;">     
            <div id="divTitle">
                DANH SÁCH GỬI DỮ LIỆU 
                <s:if test="Grade.equalsIgnoreCase('3')">
                    &nbsp;|&nbsp;
                    <button type="button"
                            onclick="idSend($('#lstCN').val(), $('#ngay_bc_DATE').val(), '<s:property value="chotsl"/>', '1')">
                        <s:if test="!chotsl.equalsIgnoreCase('2')">Khóa nhập dữ liệu CN</s:if>
                        <s:else>Mở nhập dữ liệu</s:else>
                        </button>

                </s:if>
                <s:if test="chotsl.equalsIgnoreCase('2')&& Grade.equalsIgnoreCase('2')"><a class="color_11">(TW đã khóa nhập dữ liệu)</a></s:if>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
            </div>
            <div style="height:20px"></div>  
            <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px;width: 80%">   
                <tr>
                    <th style="width: 30px">STT</th>
                    <th style="width: 60px">Mã PGD</th>
                    <th style="width: 100px">Tên PGD</th>
                    <th style="width: 100px">Hoàn thành cập nhật hồ sơ</th>
                    <th style="width: 100px">Chưa hoàn thành cập nhật hồ sơ</th>
                    <th style="width: 100px">Tổng cộng</th>
                    <th style="width: 120px">Chốt dữ liệu gửi TW</th>
                        <s:if test="Grade.equalsIgnoreCase('2')">
                        <th style="width: 120px">Chốt dữ liệu CN</th>
                        </s:if>
                </tr>  
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>
                        <s:if test="Grade.equalsIgnoreCase('2')">
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(8)</th>
                        </s:if>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>
                        <td class="D0" ><s:property value="%{#rowstatus.index + 1}" />
                        <td class="D0" ><s:property value="D1" /></td>
                        <td ><s:property value="D2" /></td>
                        <td class="D0"><a style="text-decoration: underline" 
                                          href="javascript:funcTableFile('<s:property value="D1"/>', '<s:property value="D7"/>', '1')">
                                <s:property value="D10" /></a>
                        </td> 
                        <td class="D0"><a style="text-decoration: underline; color: #ff0000" 
                                          href="javascript:funcTableFile('<s:property value="D1"/>', '<s:property value="D7"/>', '0')">
                                <s:property value="D11" /></a></td> 
                        <td style="color: #ff0000" class="D0"><s:property value="D12" /></td> 
                        <s:if test="Grade.equalsIgnoreCase('2')">
                            <td class="D0">
                                <s:if test="chotsl.equalsIgnoreCase('2')">
                                    <a style="color: orange">TW khóa nhập dữ liệu</a>
                                </s:if>
                                <s:elseif test="D8.equalsIgnoreCase('1')">
                                    <a style="text-decoration: underline" href="#" onclick="sendData('<s:property value="D1"/>', '<s:property value="D3"/>', '<s:property value="D5"/>', '<s:property value="D7"/>');">Chốt dữ liệu TW</a>
                                </s:elseif>
                                <s:elseif test="D8.equalsIgnoreCase('0') && !chotsl.equalsIgnoreCase('2')">
                                    <a style="color: red">Chưa chốt dữ liệu</a>
                                </s:elseif>
                                <s:else>
                                    <a style="color: #009900">Đã chốt dữ liệu TW</a>
                                </s:else>
                            </td>
                            <td class="D0">
                                <s:if test="chotsl.equalsIgnoreCase('2')">
                                    <a style="color: orange">TW khóa nhập dữ liệu</a>
                                </s:if>
                                <s:elseif test="D8.equalsIgnoreCase('1')">
                                    <a style="text-decoration: underline" href="#" onclick="idUnlock('<s:property value="D1"/>', $('#ngay_bc_DATE').val(), '<s:property value="D8"/>', '3');">Mở chốt dữ liệu PGD</a>
                                </s:elseif> 
                                <s:elseif test="D8.equalsIgnoreCase('0')">
                                    <a style="color: red">Chưa chốt dữ liệu</a>
                                </s:elseif>
                                <s:else>
                                    <a style="color: #009900">Đã chốt dữ liệu TW</a>
                                </s:else>
                            </td>
                        </s:if>
                        <s:if test="Grade.equalsIgnoreCase('3')">
                            <td class="D0">
                                <s:if test="D8.equalsIgnoreCase('2')">
                                    <a style="text-decoration: underline" href="#" onclick="idSend('<s:property value="D1"/>', $('#ngay_bc_DATE').val(), '<s:property value="D8"/>', '2');">Mở chốt dữ liệu</a>
                                </s:if>
                                <s:else>
                                    <a style="color: #ff0000">Chưa gửi dữ liệu</a>
                                </s:else>
                            </td>
                        </s:if>
                    </tr>
                </s:iterator>
            </table>
        </div>      
        <div id="luu_thanhcong"></div>

    </body>
    <script>
        function sendData(D1, D3, D5, D7) {
            var table = document.getElementById("subTable");
            // Chọn tất cả các liên kết chỉ trong bảng con
            var rows = table.querySelectorAll("td a");

            // Khóa các liên kết trong bảng
            rows.forEach(function (row) {
                row.style.pointerEvents = "none"; // Vô hiệu hóa click
                row.style.color = "gray";         // Thay đổi màu để trông như bị khóa
            });

            var url, sdata;
            url = "senCn_TGTV_2025.action?" + "mapgd=" + D1 + "&macn=" + D3 + "&ngayss=" + D5 + "&ngaybc=" + D7;
            sdata = jQuery("#frmdata").serialize();
            $("#loadingImageDiv_data").show();
            $("#viewData").html('<img src="img/loading.gif"/>');

            $.ajax({
                type: "POST",
                url: url,
                data: sdata,
                success: function (data) {
                    if (data === "200") {
                        alert("Chốt dữ liệu thành công!");
                        $('#message_suc_err').html("<h style='color: green; font-size: 13px ; font-weight: bold'>Bạn đã chốt dữ liệu thành công!</h>");
                        onLoadData();
                    } else {
                        alert("Lỗi: Chốt dữ liệu.");
                        onLoadData();
                    }
                },
                error: function (request) {
                    alert("Lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                    onLoadData();
                }
            });
        }

        function idSend(D1, D2, D3, D4) {
            var table = document.getElementById("subTable");
            var rows = table.querySelectorAll("td a");

            rows.forEach(function (row) {
                row.style.pointerEvents = "none";
                row.style.color = "gray";
            });

            var url, sdata;
            url = "lock_TGTV_2025.action?" + "macn=" + D1 + "&ngayss=" + D2 + "&schotsl=" + D3 + "&sstype=" + D4;
            sdata = jQuery("#frmdata").serialize();
            $("#loadingImageDiv_data").show();
            $("#viewData").html('<img src="img/loading.gif"/>');

            $.ajax({
                type: "POST",
                url: url,
                data: sdata,
                success: function (data) {
                    if (data === "200") {
                        if (D3 !== '2') {
                            alert("Khóa dữ liệu thành công!");
                        } else {
                            alert("Mở khóa dữ liệu thành công!");
                        }
                        onLoadData();
                    } else {
                        alert("Lỗi: Khóa/Mở dữ liệu.");
                        onLoadData();
                    }
                },
                error: function (request) {
                    alert("Lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                    onLoadData();
                }
            });
        }

        function idUnlock(D1, D2, D3, D4) {
            var table = document.getElementById("subTable");
            var rows = table.querySelectorAll("td a");

            rows.forEach(function (row) {
                row.style.pointerEvents = "none";
                row.style.color = "gray";
            });

            var url, sdata;
            url = "unlock_TGTV_2025_c1.action?" + "macn=" + D1 + "&ngayss=" + D2 + "&schotsl=" + D3 + "&sstype=" + D4;
            sdata = jQuery("#frmdata").serialize();
            $("#loadingImageDiv_data").show();
            $("#viewData").html('<img src="img/loading.gif"/>');

            $.ajax({
                type: "POST",
                url: url,
                data: sdata,
                success: function (data) {
                    if (data === "200") {
                        alert("Mở khóa dữ liệu thành công!");
                        onLoadData();
                    } else {
                        alert("Lỗi: Mở dữ liệu.");
                        onLoadData();
                    }
                },
                error: function (request) {
                    alert("Lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                    onLoadData();
                }
            });
        }
    </script>
</html>
