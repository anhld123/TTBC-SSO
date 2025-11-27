<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="/IMS_REPORTS/css/css2025.css" />
<!DOCTYPE html>
<html>
    <head>
        <title>Danh sách cập nhật hồ sơ</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/3.6.0/jquery.min.js"></script>
        <script src="js/3.6.0/jquery-ui.js"></script>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            $(document).ready(function () {
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('.style_h').css({"width:": "99%", "background-color": "rgba(255, 255, 255, 0.3)", "border": "1px solid #ccc"});
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
                $('.D99').css({"text-align": "center", "color": "#000", "font-style": "italic", "font-size": "xx-small"});
            });

        </script>
        <title>Danh sách cập nhật hồ sơ</title>
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw">
            <div id="divTitle" style="text-align: center">
                DANH SÁCH PGD
            </div>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table id="subTable" class="editDelete" align="center">
                <thead>
                    <tr>
                        <th rowspan="2" style="width: 50px">STT</th>
                        <th rowspan="2" style="width: 80px">Mã PGD</th>
                        <th rowspan="2" style="width: 150px">Tên PGD</th>
                        <th rowspan="2">Khách hàng</th>
                        <th rowspan="2">Món vay</th>
                        <th rowspan="2">Trong hạn</th>
                        <th rowspan="2">Quá hạn</th>
                        <th rowspan="2">Khoanh</th>
                        <th colspan="2">Giảm lãi</th>
                        <th rowspan="2">Người gửi</th>
                        <th rowspan="2">Ngày gửi</th>
                        <th rowspan="2">Trạng thái</th>
                    </tr>  
                    <tr>
                        <th>Đã nhập</th>
                        <th>Xác nhận</th>
                    </tr>  
                    <tr>
                        <th class="D99">(1)</th>
                        <th class="D99">(2)</th>
                        <th class="D99">(3)</th>
                        <th class="D99">(4)</th>
                        <th class="D99">(5)</th>
                        <th class="D99">(6)</th>
                        <th class="D99">(7)</th>
                        <th class="D99">(8)</th>
                        <th class="D99">(9)</th>
                        <th class="D99">(10)</th>
                        <th class="D99">(11)</th>
                        <th class="D99">(12)</th>
                        <th class="D99">(13)</th>
                    </tr>
                </thead>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                        <td class="D0"><s:property value="D1" /></td> 
                        <td><s:property  value="D2"/></td>
                        <td class="number"><s:property  value="D3"/></td>
                        <td class="number"><s:property  value="D4"/></td>
                        <td class="number"><s:property  value="D5"/></td>
                        <td class="number"><s:property  value="D6"/></td>
                        <td class="number"><s:property  value="D7"/></td>
                        <td class="number"><s:property  value="D8"/></td>
                        <td class="number"><s:property  value="D9"/></td>
                        <td class="D0"><s:property  value="D11"/></td>
                        <td class="D0"><s:property  value="D12"/></td>
                        <td style="width: 80px; text-align: center;">
                            <s:if test="D10.equalsIgnoreCase('2')">
                                <a href="#" onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D13"/>', '1');">Mở dữ liệu</a>
                            </s:if>  
                            <s:else>

                            </s:else>     
                        </td>
                    </tr>
                </s:iterator>

            </table>
            <div style="text-align: center">
                <br>
                <input type="button" value="Thoát" name="cmdLuu" id="cmdLuu"/></div>
        </div>
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
                        window.location.reload();
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
        $("#cmdLuu").click(function () {
            window.opener.document.getElementById('loaddata').click();
            window.close();
        });
    </script>
</html>
