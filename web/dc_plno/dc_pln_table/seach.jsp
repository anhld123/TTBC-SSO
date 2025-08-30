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
    table.editDelete,
    table.subTable {
        border-collapse: separate;
        border-spacing: 0;
        width: auto;
        /*margin: 5px auto;*/
        font-family: Arial, sans-serif;
        border-radius: 5px;
        overflow: hidden;
        box-shadow: 0 1px 6px rgba(0, 0, 0, 0.05);
        /*font-size: 1px;*/
    }

    table.editDelete th,
    table.subTable th,
    table.editDelete td,
    table.subTable td {
        border: 1px solid #eee;
        background-color: #fff;
    }

    /* Gộp các th riêng */
    table.editDelete th,
    table.subTable th {
        background-color: #eef6ff;
        color: #000;
        font-weight: bold;
    }

    /* Gộp hàng chẵn */
    table.editDelete tr:nth-child(even),
    table.subTable tr:nth-child(even) {
        background-color: #fafafa;
    }

    table.editDelete tr:hover,
    table.subTable tr:hover {
        background-color: #eef6ff;
    }

    table.editDelete td.number,
    table.subTable td.number {
        color: #333;
        font-weight: 500;
    }
    .custom-scroll {
        overflow: scroll;
        width: auto;
        height: 500px;
        scrollbar-width: thin; /* Firefox */
        scrollbar-color: rgba(128, 128, 128, 0.3) transparent; /* Firefox */
    }

    /* Webkit (Chrome, Edge, Safari) */
    .custom-scroll::-webkit-scrollbar {
        width: 8px;
    }

    .custom-scroll::-webkit-scrollbar-track {
        background: transparent;
    }

    .custom-scroll::-webkit-scrollbar-thumb {
        background-color: rgba(128, 128, 128, 0.3);
        border-radius: 4px;
    }
    .custom-scroll::-webkit-scrollbar-thumb:hover {
        background-color: rgba(128, 128, 128, 0.5);
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
                initTable();
            });
            $(document).ready(function () {
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('.style_h').css({"width:": "99%", "background-color": "rgba(255, 255, 255, 0.3)", "border": "1px solid #ccc"});
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0).css({"text-align": "right"});
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".STT1").css({"width": "50px"});
                $(".STT2").css({"width": "70px"});
                $(".STT3").css({"width": "100"});
                $(".STT4").css({"width": "150px"});
                $(".STT5").css({"width": "70px"});
                $(".STT6").css({"width": "65px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "220px"});
                $(".TEN_KH").css({"width": "100%"});

                $("#idSave").hide();
                $("#page-header").hide();
                $("#idSendAll").show();

            });
            function initTable() {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;

                for (var i = 0; i < rowcount; i++) {
                    var check = document.getElementById('D8_' + i);
                    var checkbox = document.getElementById('checkrow_' + i);
                    if (check)
                    {
                        var value = check.value;
                        if (value === "2") {
                            checkbox.disabled = true;
                            checkbox.checked = true;
                            checkbox.value = 2;
                            checkbox.title = 'Tổ đã chốt';
                        }
                    }
                }
            }
        </script>      
    </head>

    <body>
        <div id="divTitle" style="width: 75%; text-align: center">  
            DANH SÁCH TỔ
        </div>
        <table border="1" class="editDelete" id="subTable"  style="padding-top: 10px">   
            <tr>
                <th rowspan="2" style="width: 30px"><input type="checkbox" id ="select-all"/></th>
                <th rowspan="2" style="width: 80px">Mã tổ</th>
                <th rowspan="2" style="width: 150px">Tên tổ trưởng</th>
                <th rowspan="2" style="width: 120px">Đơn vị ủy thác</th>
                <th rowspan="2">Tổng số KH</th>
                <th rowspan="2">Tổng số món vay</th>
                <th rowspan="2">Tổng dư nợ</th>
                <th colspan="3">Dư nợ</th>
                <th rowspan="2">Nợ lãi</th>
                <th rowspan="2" style="width: 80px">Người chốt</th>
                <th rowspan="2" style="width: 150px">Ngày chốt</th>
            </tr>  
            <tr>                   
                <th>Nợ trong hạn</th>
                <th>Nợ quá hạn</th>
                <th>Nợ khoanh</th>
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
                <th style="color: #000; font-style: italic; font-size: xx-small;">(9)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(10)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(11)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(12)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(13)</th>
            </tr>
            <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                <tr>
                    <td class="D0"> <input type="checkbox" class="myCheckBox"
                                           name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D23"
                                           id="checkrow_<s:property value="%{#rowstatus.index}" />"
                                           value="0" onclick="$(this).val(this.checked ? 1 : 0)"/> </td>
                <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>                             
                <input type="hidden" value="<s:property  value="D8" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"
                       id="D8_<s:property  value="%{#rowstatus.index}" />"/>                             

                <td class = "D0"> <s:property  value="D1"/></td>
                <td><s:property  value="D3"/></td>
                <td><s:property  value="D15"/></td>
                <td class="number STT2"><s:property  value="D16"/></td>
                <td class="number STT2"><s:property  value="D17"/></td>
                <td class="number STT2"><s:property  value="D18"/></td>
                <td class="number STT2"><s:property  value="D19"/></td>
                <td class="number STT2"><s:property  value="D20"/></td>
                <td class="number STT2"><s:property  value="D21"/></td>
                <td class="number STT2"><s:property  value="D22"/></td>
                <td class="D0"><s:property  value="D9"/></td>
                <td class="D0"><s:property  value="D10"/></td>
            </tr>
        </s:iterator>
    </table>

</div>      
<div id="luu_thanhcong"></div>
<script>

    function cancelAssign(D1, D2) {
        var table = document.getElementById("subTable");
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
        var url, sdata;
        url = "status_MSTS_C2.action?" + "madiemgd=" + D1 + "&ngaybc=" + D2,
                sdata = jQuery("#frmdata").serialize();
        $("#viewData").html('<img src="img/loading.gif"/>');
        $.ajax({
            type: "POST",
            url: url,
            data: sdata,
            success: function (data) {
                if (data === "200") {
                    alert("Mở phê duyệt thành công!");
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
    $(function () {
        $('#select-all').click(function (event) {
            if (this.checked) {
                // Iterate each checkbox
                $('.myCheckBox').each(function () {
                    this.checked = true;
                    this.value = '1';
                });
            } else {
                $('.myCheckBox').each(function () {
                    this.checked = false;
                    this.value = '0';
                });
            }
        });
    });
</script>
</body>
</html>
