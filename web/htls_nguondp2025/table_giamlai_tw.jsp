
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/css2025.css" />
<!DOCTYPE html>
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
                $(".STT1").css({"width": "50px"});
                $(".STT2").css({"width": "80px"});
                $(".STT3").css({"width": "150"});
                $(".STT4").css({"width": "200px"});
                $(".STT5").css({"width": "70px"});
                $(".STT6").css({"width": "65px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "220px"});
                $(".TEN_KH").css({"width": "100%"});
            });

            function funcTableFile(mapgd, ngaybc, type) {
                var w = 1500, h = 700;
                var left = (screen.width / 2) - (w / 2);
                var top = (screen.height / 2) - (h / 2);
                var urlParam = "mapgd=" + mapgd + "&ngaybc=" + ngaybc + "&type=" + type;
                var url = "/IMS_REPORTS/popupgiamlaipgd.action?" + urlParam;
                popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }
        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;">             
            <div id="divTitle">
                DANH SÁCH CHI NHÁNH
            </div>
            <div style="height:10px"></div>  
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px">   
                <tr>
                    <th rowspan="2" style="width: 50px">STT</th>
                    <th rowspan="2" style="width: 80px">Mã CN</th>
                    <th rowspan="2" style="width: 150px">Tên CN </th>
                    <th rowspan="2">Khách hàng</th>
                    <th rowspan="2">Món vay</th>
                    <th rowspan="2">Trong hạn</th>
                    <th rowspan="2">Quá hạn</th>
                    <th rowspan="2">Khoanh</th>
                    <th colspan="2">Số món thay đổi lãi suất</th>
                    <th rowspan="2" style="width: 80px">Trạng thái</th>
                </tr>  
                <tr>
                    <th>Không thay đổi</th>
                    <th>Có thay đổi</th>
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
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                        <td class="D0"><a style="text-decoration: underline" 
                                          href="javascript:funcTableFile('<s:property value="D1"/>', $('#ngay_bc_DATE').val(), '1')">
                                <s:property value="D1" /></a>
                        </td> 
                        <td><s:property  value="D2"/></td>
                        <td class="number"><s:property  value="D3"/></td>
                        <td class="number"><s:property  value="D4"/></td>
                        <td class="number"><s:property  value="D5"/></td>
                        <td class="number"><s:property  value="D6"/></td>
                        <td class="number"><s:property  value="D7"/></td>
                        <td class="number"><s:property  value="D8"/></td>
                        <td class="number"><s:property  value="D9"/></td>
                        <td style="width: 80px; text-align: center;">
                            <s:if test="D11.equalsIgnoreCase('1')">
                                <a href="#" style="text-decoration: underline;"
                                   onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D10"/>', '2');">Mở dữ liệu</a>
                            </s:if>  
                            <s:else>

                            </s:else>     
                        </td>
                    </tr>
                </s:iterator>

        </div>      
        <div id="luu_thanhcong"></div>
    </body>
    <script>
        function cancelAssign(mapgd, ngaybc, stype) {
            console.log("mapgd= " + mapgd + " ngaybc=" + ngaybc + " stype= " + stype);
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
            $.ajax({
                type: "GET",
                url: "unlock_htls_nguondp.action?" + "mapgd=" + mapgd + "&ngaybc=" + ngaybc + "&type=" + stype,
                success: function (res) {
                    var status = parseInt(res.status);
                    if (status === 1) {
                        alert('Mở phê duyệt thành công!');
                        onLoadData();
                    } else {
                        alert('Mở phê duyệt lỗi: ' + res.message);
                        onLoadData();
                    }
                },
                error: function (res) {
                    alert("Mở phê duyệt lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                    onLoadData();
                }
            });
        }
    </script>
</html>
