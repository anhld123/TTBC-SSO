<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh sách cập nhật hồ sơ</title>
        <link rel="stylesheet" type="text/css" href="css/bcqt.css" />
        <link rel="stylesheet" href="js/3.6.0/jquery-ui.css">
        <style>
            #subTable {
                font-size: 16px;
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 98%;
            }
            #subTable th {
                background-color: #ddd;
                color: #0000FF;
            }
            #subTable th, #subTable td {
                border: 1px solid gray;
            }
            #subTable tr:nth-child(even) { background-color: #f2f2f2; }
            #subTable tr:hover { background-color: #ddd; }
            .txtPublic { width: 85px; }
            .ui-datepicker-trigger { height: 100%; }
            .txtBody { text-align: center; }
            .txtBody > .ui-datepicker-trigger { display: none; }
            td.hdtitle { position: static; top: 0; z-index: 10; }
            .color_11 {
                background: #fff;
                font-size: 14px;
                font-weight: bold;
                animation: blink 700ms infinite;
            }
            #divDonvitinh{
                font: 13px Arial, Helvetica, sans-serif;
                text-align: right;
                color: red;
                padding-right: 7px;
            }
            @keyframes blink {
                0%, 100% { color: red; }
                50% { color: #fff; }
            }
        </style>
        <script src="js/3.6.0/jquery.min.js"></script>
        <script src="js/3.6.0/jquery-ui.js"></script>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var current_page = 1;
            var records_per_page = 20;
            $(document).ready(function () {
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('input.number, input.number2').css({"text-align": "right"}).number(true, 0);
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('.D000').css({"text-align": "right"});
                $(".STT1").css({"width": "30px"});
                $(".STT6").css({"width": "150px"});
                $(".STT2").css({"width": "100px"});
                $('.D99').css({"text-align": "center", "color": "#000", "font-style": "italic", "font-size": "xx-small"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                initTable1();
            });

            function initTable1() {
                changePage(current_page);
            }

            function setChecked(id) {
                $('#' + id).prop('checked', function (i, val) {
                    return !val;
                });
            }

            function getMabyNumber(idx) {
                try {
                    return document.getElementById('id_' + idx).value;
                } catch (e) {
                    return '999999';
                }
            }

            function changePage(page) {
                var table = document.getElementById("subTable");
                var totalRows = table.rows.length;

                page = Math.max(1, Math.min(page, numPages()));

                Array.from(table.rows).forEach((row, index) => {
                    // Luôn hiển thị 2 dòng đầu (index 0 và 1)
                    if (index === 0 || index === 1) {
                        row.style.display = '';
                    } else {
                        row.style.display = (index > (page - 1) * records_per_page + 1 && index <= page * records_per_page + 1) ? '' : 'none';
                    }
                });

                document.getElementById("page").textContent = page + "/" + numPages();
                document.getElementById("btn_prev").style.visibility = page === 1 ? "hidden" : "visible";
                document.getElementById("btn_next").style.visibility = page === numPages() ? "hidden" : "visible";
            }


            function numPages() {
                return Math.ceil((document.getElementById("subTable").rows.length - 1) / records_per_page);
            }

            function prevPage() {
                if (current_page > 1)
                    changePage(--current_page);
            }
            function nextPage() {
                if (current_page < numPages())
                    changePage(++current_page);
            }
            function goToPage() {
                var inputPage = parseInt(document.getElementById("pageInput").value);
                if (inputPage >= 1 && inputPage <= numPages())
                    changePage(current_page = inputPage);
                else
                    alert("Trang không tồn tại");
            }

            $(function () {
                $('#search').on('keyup', function () {
                    var val = $(this).val().toLowerCase();

                    $('#subTable tbody tr').each(function () {
                        var rowText = $(this).text().toLowerCase();
                        $(this).toggle(rowText.includes(val));
                    });
                });
            });
            window.onload = function () {
                changePage(current_page);
            };
        </script>
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw">
            <div id="divTitle" style="text-align: center">
                <s:if test="txtGetData.equalsIgnoreCase('1')">DANH SÁCH HOÀN THÀNH CẬP NHẬT HỒ SƠ</s:if>
                <s:else>DANH SÁCH CHƯA HOÀN THÀNH CẬP NHẬT HỒ SƠ</s:else>
                </div>
                <div style="margin: 10px 0" id="divDonvitinh">
                    <!-- Phần phân trang -->
                    Chọn trang 
                    <input type="number" id="pageInput" min="1" style="width: 50px" />
                    <a href="#" onclick="goToPage()">Go</a>
                    <a href="#" id="btn_prev" onclick="prevPage()">&#8920;</a>
                    Trang <span id="page"></span>
                    <a href="#" id="btn_next" onclick="nextPage()">&#8921;</a>

                    <!-- Phần tìm kiếm -->
                    <span style="margin-left: 30px">Tra cứu: </span>
                    <input type="text" id="search" placeholder="Tìm kiếm ..." style="width: 200px" />
                </div>

                <table id="subTable" align="center">
                    <thead>
                        <tr>
                            <th class="STT1">STT</th>
                            <th class="STT6">Mã khách hàng</th>
                            <th class="STT6">Tên khách hàng</th>
                            <th class="STT6">CCCD/Thẻ căn cước</th>
                            <th class="STT6">Ngày tháng năm sinh</th>
                            <th class="STT6">Số điện thoại</th>
                            <th class="STT6">Số tài khoản tiền gửi tổ viên 105</th>
                            <s:if test="txtGetData.equalsIgnoreCase('1')">
                            <th class="STT2">Mở lại Cif</th>
                            </s:if>
                    </tr>
                    <tr>
                        <th class="D99">(1)</th>
                        <th class="D99">(2)</th>
                        <th class="D99">(3)</th>
                        <th class="D99">(4)</th>
                        <th class="D99">(5)</th>
                        <th class="D99">(6)</th>
                        <th class="D99">(7)</th>
                            <s:if test="txtGetData.equalsIgnoreCase('1')">
                            <th class="D99">(8)</th>
                            </s:if>
                    </tr>
                </thead>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                        <td class="D0"><s:property  value="D1" /></td>
                        <td><s:property  value="D2" /></td>
                        <td class="D0"><s:property  value="D3" /></td>
                        <td class="D0"><s:property  value="D4" /></td>
                        <td class="D0"><s:property  value="D5" /></td>
                        <td class="D0"><s:property  value="D6" /></td>
                        <s:if test="txtGetData.equalsIgnoreCase('1')">
                            <td class="D0"><a style="text-decoration: underline" href="#" onclick="idUnlockCif('<s:property value="D1"/>', '<s:property value="NGAYBC"/>', '<s:property value="D6"/>', '<s:property value="MAPGD"/>');">Mở Cif</a>
                            </td> </s:if>
                        </tr>
                </s:iterator>

            </table>
            <div style="text-align: center">
                <br>
                <input type="button" value="Thoát" name="cmdLuu" id="cmdLuu"/></div>
        </div>
    </body>
    <script>
        $("#cmdLuu").click(function () {

            window.opener.document.getElementById('loadDatatmp').click();
            window.close();
        });
        function idUnlockCif(D1, D2, D3, D4) {
            var parts = D2.split("/");
            var inputDay = parseInt(parts[0], 10);
            var inputMonth = parseInt(parts[1], 10) - 1; // tháng JS bắt đầu từ 0
            var inputYear = parseInt(parts[2], 10);
            var inputDate = new Date(inputYear, inputMonth, inputDay);

            var today = new Date();
            var currentYear = today.getFullYear();
            var currentMonth = today.getMonth(); // 0-based
            var lastDayOfCurrentMonth = new Date(currentYear, currentMonth + 1, 0);

            let allowedMonth, allowedYear;

            if (today.getDate() < lastDayOfCurrentMonth.getDate()) {
                // Chưa đến ngày cuối tháng → thao tác dữ liệu tháng trước
                if (currentMonth === 0) {
                    allowedMonth = 11;        // Tháng 12 năm trước
                    allowedYear = currentYear - 1;
                } else {
                    allowedMonth = currentMonth - 1;
                    allowedYear = currentYear;
                }
            } else {
                // Đúng hoặc sau ngày cuối tháng → thao tác tháng hiện tại
                allowedMonth = currentMonth;
                allowedYear = currentYear;
            }
//            alert(inputMonth + " " + currentMonth + " " + inputYear + " " + currentYear + " " + inputDate + " " + lastDayOfCurrentMonth);

            if (inputMonth !== allowedMonth || inputYear !== allowedYear) {
                alert("Chỉ được thao tác với dữ liệu tháng " + (allowedMonth + 1) + "/" + allowedYear);
                return;
            }

            var table = document.getElementById("subTable");
            var rows = table.querySelectorAll("td a");

            rows.forEach(function (row) {
                row.style.pointerEvents = "none";
                row.style.color = "gray";
            });

            var url, sdata;
            url = "unlockcif_TGTV_2025.action?" + "macif=" + D1 + "&ngayss=" + D2 + "&sotk=" + D3 + "&mapgd=" + D4;
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
                        location.reload();
                    } else {
                        alert("Lỗi: Mở dữ liệu.");
                        location.reload();
                    }
                },
                error: function (request) {
                    alert("Lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                    location.reload();
                }
            });
        }
    </script>
</html>
