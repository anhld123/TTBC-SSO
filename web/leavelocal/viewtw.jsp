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
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('.style_h').css({"width:": "99%", "background-color": "rgba(255, 255, 255, 0.3)", "border": "1px solid #ccc"});
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('input.number2').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0).css({"text-align": "right"});
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".STT1").css({"width": "50px"});
                $(".STT2").css({"width": "200px"});
                $(".STT3").css({"width": "100"});
                $(".STT4").css({"width": "150px"});
                $(".STT5").css({"width": "80px"});
                $(".STT6").css({"width": "65px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "220px"});
                $(".TEN_KH").css({"width": "100%"});
            });
        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;height: 400px;"> 
            <div id="divTitle">
                PHÊ DUYỆT TRẠNG THÁI KHÁCH HÀNG RỜI KHỎI ĐỊA PHƯƠNG<br>
                <input type="hidden" value="<s:property value="sngaybc"/>" name="sngaybc" id="sngaybc"/> 
            </div>
            <div id="divDonvitinh">
                Đơn vị tính: Triệu đồng, %, điểm
            </div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th class="D0">STT</th>                           
                    <th class="STT2">Chi nhánh</th>  
                    <th class="STT2">Phòng giao dịch</th>
                    <th class="STT2">Địa phương</th>
                    <th class="STT2">Mã khách hàng</th>
                    <th class="STT2">Tên khách hàng</th>
                    <th class="STT2">Trạng thái</th>
                </tr>   

                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                        <td class="STT2"><s:property  value="D3"/> - <s:property  value="D4"/></td>
                        <td class="STT2"><s:property  value="D5"/> - <s:property  value="D6"/></td>
                        <td class="STT2"><s:property  value="D7"/> - <s:property  value="D8"/></td>
                        <td class="STT5"><s:property  value="D11"/></td>
                        <td class="STT5"><s:property  value="D12"/></td>
                        <td class="D0"><a style="text-decoration: underline" href="#" onclick="idUnlock('<s:property value="D11"/>', '<s:property value="D3"/>', '<s:property value="D5"/>', '<s:property value="D7"/>');">Xoá bản ghi</a></td> 
                    </tr>
                </s:iterator>
            </table>
        </div>
        <div id="luu_thanhcong"></div>
    </body>
    <script>
        function idUnlock(makh, macn, mapgd, madp) {
            var table = document.getElementById("subTable");
            // Chọn tất cả các liên kết chỉ trong bảng con
            var rows = table.querySelectorAll("td a");
            // Khóa các liên kết trong bảng
            rows.forEach(function (row) {
                row.style.pointerEvents = "none"; // Vô hiệu hóa click
                row.style.color = "gray";         // Thay đổi màu để trông như bị khóa
            });
            $.ajax({
                type: "GET",
                url: "unlock_leavelocal.action?" + "makh=" + makh + "&macn=" + macn + "&mapgd=" + mapgd + "&madp=" + madp,
                success: function (res) {
                    var status = parseInt(res.status);
                    //alert(status);
                    if (status === 1) {
                        alert("Xoá bản ghi thành công!");
                        onLoadData();
                    } else {
                        alert("Lỗi: Xoá bản ghi.");
                        onLoadData();
                    }
                },
                error: function (res) {
                    alert("Xoá bản ghi bị lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                }
            });
        }</script>
</html>
