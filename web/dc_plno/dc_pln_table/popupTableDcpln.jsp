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
                $('td.number, td.number2').css({"text-align": "right"}).number(true, 0);
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
                    if (index === 0 || index === 1 || index === 2 || index === 3) {
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
        <div id="loading" style="text-align: center; padding: 50px;">
            <img src="img/loading.gif" alt="Đang tải dữ liệu..." />
            <p>Đang tải dữ liệu, vui lòng chờ...</p>
        </div>
        <div id="mainContent" style="display: none;">
            <div style="overflow:scroll; width: 98vw">
                <div id="divTitle" style="text-align: center">
                    <s:if test="txtGetData.equalsIgnoreCase('1')">DANH SÁCH ĐÃ CHỐT</s:if>
                    <s:elseif test="txtGetData.equalsIgnoreCase('3')">DANH SÁCH ĐỀ NGHỊ HỖ TRỢ</s:elseif>
                    <s:else>DANH SÁCH CHƯA CHỐT</s:else>
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
                                <th rowspan="3">STT</th>
                                <th rowspan="3" class="STT4">Tên khách hàng</th>
                                <th rowspan="3" class="STT4">Mã món vay</th>
                                <th rowspan="3" class="STT3">Chương trình</th>
                                <th colspan="5">Số liệu tại NHCSXH</th> 
                                <th colspan="4">Phân loại khả năng trả nợ</th> 
                                <s:if test="!txtGetData.equalsIgnoreCase('2')">
                                <th class="STT2" rowspan="3">Mở lại món vay</th>
                                </s:if>
                        </tr>
                        <tr>
                            <th colspan="4" class="STT2">Nợ gốc</th> 
                            <th rowspan="2" class="STT2">Nợ lãi</th> 
                            <th rowspan="2" class="STT3">Có khả năng trả nợ</th> 
                            <th colspan="3">Không có khả năng trả nợ</th>

                        </tr>
                        <tr>
                            <th class="STT2">Tổng số</th>
                            <th class="STT2">Nợ trong hạn</th>
                            <th class="STT2">Nợ quá hạn</th>
                            <th class="STT2">Nợ khoanh</th>
                            <th class="STT3">Số tiền</th>
                            <th>Nguyên nhân</th>
                            <th class="STT4">Cụ thể nguyên nhân</th>

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
                                <s:if test="!txtGetData.equalsIgnoreCase('2')">
                                <th class="D99">(14)</th>
                                </s:if>
                        </tr>
                    </thead>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                        <tr id="tablefix"> 
                            <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                            <td><s:property  value="D1" /></td>
                            <td><s:property  value="D2" /></td>
                            <td class="number"><s:property  value="D3" /></td>
                            <td class="number"><s:property  value="D4" /></td>
                            <td class="number"><s:property  value="D5" /></td>
                            <td class="number"><s:property  value="D6" /></td>
                            <td class="number"><s:property  value="D7" /></td>
                            <td class="number"><s:property  value="D8" /></td>
                            <td class="number"><s:property  value="D9" /></td>
                            <td class="number"><s:property  value="D10" /></td>
                            <td><s:property  value="D11" /></td>
                            <td><s:property  value="D12" /></td>
                            <s:if test="!txtGetData.equalsIgnoreCase('2')">
                                <td class="D0"><a style="text-decoration: underline" href="#" onclick="idUnlockCif('<s:property value="D15"/>', '<s:property value="D2"/>', '<s:property value="D13"/>', '<s:property value="D14"/>', '<s:property value="txtGetData"/>');">Mở khóa</a>
                                </td> </s:if>
                            </tr>
                    </s:iterator>

                </table>
                <div style="text-align: center">
                    <br>
                    <input type="button" value="Thoát" name="cmdLuu" id="cmdLuu"/></div>
            </div>
        </div>
    </body>
    <script>
        $("#cmdLuu").click(function () {

            window.opener.document.getElementById('loadDatatmp').click();
            window.close();
        });

        function idUnlockCif(mapgd, makh, soku, ngaybc, lock) {
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
                url: "unlock_pLN.action?" + "mapgd=" + mapgd + "&makh=" + makh + "&soku=" + soku + "&ngaybc=" + ngaybc + "&lock=" + lock,
                success: function (res) {
                    var status = parseInt(res.status);
                    //alert(status);
                    if (status === 1) {
                        alert("Mở khóa dữ liệu thành công!");
                        location.reload();
                    } else {
                        alert("Lỗi: Mở dữ liệu.");
                        location.reload();
                    }
                },
                error: function (res) {
                    alert("Mở phê duyệt lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                }
            });
        }
        window.onload = function () {
            // Ẩn nội dung chính trước
            document.getElementById("mainContent").style.display = "none";

            // Đợi trình duyệt hoàn tất việc vẽ giao diện bằng requestAnimationFrame
            requestAnimationFrame(function () {
                requestAnimationFrame(function () {
                    // Sau 2 lần requestAnimationFrame -> browser đã render xong layout
                    document.getElementById("loading").style.display = "none";
                    document.getElementById("mainContent").style.display = "block";
                    changePage(current_page); // Phân trang sau khi đã hiển thị
                });
            });
        };


    </script>
</html>
