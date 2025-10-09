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
                $(".STT5").css({"width": "300px"});
                $(".STT6").css({"width": "65px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "220px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            function initTable() {
                var table = document.getElementById("subTable");
                var rowcount = Math.max(table.rows.length, max_row);

                for (var i = 0; i < rowcount; i++) {
                }
            }

            var current_page = 1; // trang bắt đầu 
            var records_per_page = 20; // số dòng
            var l = document.getElementById("subTable").rows.length;
            function prevPage()
            {

                if (current_page > 1) {
                    current_page--;
                    changePage(current_page);
                }
            }

            function nextPage()
            {
                if (current_page < numPages()) {
                    current_page++;
                    changePage(current_page);
                }
            }
            function goToPage() {
                var inputPage = document.getElementById("pageInput").value;
                if (inputPage >= 1 && inputPage <= numPages()) {
                    current_page = inputPage;
                    changePage(current_page);
                } else {
                    // Xử lý khi trang không hợp lệ
                    alert("Trang không tồn tại");
                }
            }

            function changePage(page)
            {
                var btn_next = document.getElementById("btn_next");
                var btn_prev = document.getElementById("btn_prev");
                var listing_table = document.getElementById("subTable");
                var page_span = document.getElementById("page");
                // Validate page
                if (page < 1) {
                    page = 1;
                }
                if (page > numPages()) {
                    page = numPages();
                }

                [...listing_table.getElementsByTagName('tr')].forEach((tr) => {
                    tr.style.display = 'none'; // reset all to not display
                });
                listing_table.rows[0].style.display = "";
//                listing_table.rows[1].style.display = "";
//                listing_table.rows[2].style.display = "";
//                listing_table.rows[3].style.display = "";
                for (var i = (page - 1) * records_per_page + 1; i < (page * records_per_page) + 1; i++) {
                    if (listing_table.rows[i]) {
                        listing_table.rows[i].style.display = "";
                    } else {
                        continue;
                    }
                }

                page_span.innerHTML = page + "/" + numPages();
                if (page === 1) {
                    btn_prev.style.visibility = "hidden";
                } else {
                    btn_prev.style.visibility = "visible";
                }

                if (page === numPages()) {
                    btn_next.style.visibility = "hidden";
                } else {
                    btn_next.style.visibility = "visible";
                }
            }

            function numPages()
            {
                return Math.ceil((l - 1) / records_per_page);
            }
        </script>        
    </head>
    <body>
        <div class="custom-scroll">   
            <table border="1" class="editDelete" align="center" style="width: 50%" style="margin: 10px 0 10px 0">
                <tr>
                    <th>Cán bộ</th>
                    <th>Số sổ đã gắn</th>
                    <th>Dư TK sao kê</th>
                    <th>Dư TK hợp đồng</th>
                    <th>Số sổ còn hoạt động</th>
                    <th>Dư TK sao kê HĐ</th>
                    <th>Dư TK hợp đồng HĐ</th>
                </tr>
                <s:iterator value="#attr.lstData" var="modelView" status="rowstatus"> 
                    <tr> 
                        <td class="D0"><s:property  value="D1" /></td>
                        <td class="number"><s:property  value="D2" /></td>
                        <td class="number"><s:property  value="D3" /></td>
                        <td class="number"><s:property  value="D4" /></td>
                        <td class="number"><s:property  value="D7" /></td>
                        <td class="number"><s:property  value="D12" /></td>
                        <td class="number"><s:property  value="D13" /></td>
                    </tr>
                </s:iterator>

            </table>
            <div id="divTitle" style="margin: 10px 0; text-align: center; font-weight: bold; font-size: 16px;">
                HUY ĐỘNG TIẾT KIỆM
            </div>

            <table border="1" class="editDelete" id="subTable" align="center" style="width: 98%">
                <tr height="28">
                    <th><input type="checkbox" id ="select-all"/></th>
                    <th class="STT3">TT</th>
                    <th class="STT4">GL</th>
                    <th class="STT4">Số sổ</th> 
                    <th class="STT4">Số TK 0</th>
                    <th class="STT4">Số TK</th> 
                    <th class="STT4">Mã KH</th> 
                    <th class="STT5">Tên KH</th> 
                    <th class="STT4">Sản phẩm</th> 
                    <th class="STT4">SODU_SK</th> 
                    <th class="STT4">SODU_HD</th> 
                    <th class="STT4">Kỳ hạn</th> 
                    <th class="STT4">Mã cán bộ</th>                     
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
                    <tr height="22">   
                        <td class="D0"> <input type="checkbox" class="myCheckBox"
                                               name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D21"
                                               id="checkrow_<s:property value="%{#rowstatus.index}" />"
                                               <s:if test="!D13.equalsIgnoreCase('')">checked value="1" </s:if>
                                                   onclick="$(this).val(this.checked ? 1 : 0)"/>      
                                               <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                            <input type="hidden" value="<s:property value="%{#rowstatus.index + 1}" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                            <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>  
                            <input type="hidden" value="<s:property  value="MAPGD" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                            <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>
                            <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                            <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>
                            <input type="hidden" value="<s:property  value="D2" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2"/>
                            <input type="hidden" value="<s:property  value="D3" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                            <input type="hidden" value="<s:property  value="D4" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"/>
                            <input type="hidden" value="<s:property  value="D5" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"/>
                            <input type="hidden" value="<s:property  value="D6" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6"/>
                            <input type="hidden" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7"/>
                            <input type="hidden" value="<s:property  value="D8" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"/>
                            <input type="hidden" value="<s:property  value="D9" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/>
                            <input type="hidden" value="<s:property  value="D10" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10"/>
                            <input type="hidden" value="<s:property  value="D11" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11"/>
                            <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                            <input type="hidden" value="<s:property  value="D13" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13"/>
                            <input type="hidden" value="<s:property  value="D14" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14"/>
                            <input type="hidden" value="<s:property  value="D15" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/>
                            <input type="hidden" value="<s:property  value="D17" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17"/>
                            <input type="hidden" value="<s:property  value="D18" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18"/>
                            <input type="hidden" value="<s:property  value="D19" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19"/>
                            <input type="hidden" value="<s:property  value="D20" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20"/>

                        </td>   
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                        <td class="D0"><s:property  value="D1" /></td> 
                        <td class="D0"><s:property  value="D14" /></td> 
                        <td class="D00"><s:property  value="D15" /></td> 
                        <td class="D00"><s:property  value="D2" /></td> 
                        <td class="D00"><s:property  value="D3" /></td> 
                        <td class="D00"><s:property  value="D4" /></td> 
                        <td class="D0"><s:property  value="D5" /></td> 
                        <td class="number"><s:property  value="D6" /></td> 
                        <td class="number"><s:property  value="D7" /></td> 
                        <td class="D0"><s:property  value="D8" /></td> 
                        <td class="D0"><s:property  value="D13" /></td> 
                    </tr>    

                </s:iterator>

            </table>
        </div>
        <div id="luu_thanhcong"></div>
        <script>
            $(function () {
                $('#select-all').click(function () {
                    if (this.checked) {
                        let count = 0;
                        $('.myCheckBox').each(function () {
                            if (count < 100) {
                                this.checked = true;
                                this.value = '1';
                                count++;
                            } else {
                                this.checked = false;
                                this.value = '0';
                            }
                        });
                        if ($('.myCheckBox').length > 100) {
                            alert("Bạn chỉ được chọn tối đa 100 mục!");
                        }
                    } else {
                        $('.myCheckBox').prop('checked', false).val('0');
                    }
                });

                // Nếu user tick thủ công
                $('.myCheckBox').on('change', function () {
                    let selected = $('.myCheckBox:checked').length;
                    if (selected > 100) {
                        this.checked = false;
                        this.value = '0';
                        alert("Chỉ được chọn tối đa 100 mục!");
                    }
                });
            });

            function initTable1()
            {
                nextPage();
                prevPage();
            }
            initTable1();
            $(document).ready(function () {
                $("#page-header").show();
            }
            );
        </script>

    </body>
</html>
