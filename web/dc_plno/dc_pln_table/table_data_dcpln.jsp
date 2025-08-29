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
            });
            function initTable() {
                var table = document.getElementById("subTable");
                var rowcount = Math.max(table.rows.length, max_row);

                for (var i = 0; i < rowcount; i++) {
                    var D1 = document.getElementById('D1_' + i);
                    var D2 = document.getElementById('D2_' + i);
                    var D3 = document.getElementById('D3_' + i);
                    var D5 = document.getElementById('D5_' + i);
                    var D6 = document.getElementById('D6_' + i);
                    var D7 = document.getElementById('D7_' + i);
                    var checkbox = document.getElementById('checkrow_' + i);
                    var DnokhoanhEl = document.getElementById('Dnokhoanh_' + i);
                    var dnokhoanhVal = "";
                    if (DnokhoanhEl) {
                        // Nếu là <td>, lấy text
                        dnokhoanhVal = DnokhoanhEl.textContent ? DnokhoanhEl.textContent.trim() : "";
                    }

// Khóa D3 khi dnokhoanh khác "0"
                    if (dnokhoanhVal !== "0" && D3) {
                        D3.disabled = true;
                        continue; // tránh bị xử lý tiếp theo override
                    }

                    // 1. Khóa checkbox, D3, D5 nếu D6 hoặc D7 thỏa điều kiện
                    if (D6 && D3 && (D6.value === "1")) {
                        if (checkbox) {
                            checkbox.disabled = true;
                            checkbox.checked = true;
                            checkbox.value = 2;
                            checkbox.title = 'Món vay đã chốt';
                        }
                        if (D1)
                            D1.disabled = true;
                        if (D2)
                            D2.disabled = true;
                        if (D3)
                            D3.disabled = true;
                        if (D5)
                            D5.disabled = true;

                        // Bỏ qua xử lý tiếp theo để tránh bị mở khóa ở dưới
                        continue;
                    }
                    if (D7 && D7.value !== "2") {
                        // 4. Điều khiển D3 dựa vào D1 & D2
                        if (D1 && D2 && D3) {
                            var val1 = D1.value.trim();
                            var val2 = D2.value.trim();
                            D3.disabled = (val1 === "0" && val2 === "0") || (val1 !== "0");
                        }

                        // 5. Khóa D5 nếu D3 không thuộc 06, 07, 08
                        if (D3 && D5) {
                            if (D2 && D2.value === "")
                                D2.value = "0";
                            D5.disabled = !(D3.value === "06" || D3.value === "07" || D3.value === "08");
                        }
                    }
                    if (D7 && D7.value === "2") {
                        // 4. Điều khiển D3 dựa vào D1 & D2
                        if (D1 && D2 && D3) {
                            var val1 = D1.value.trim();
                            var val2 = D2.value.trim();
                            D3.disabled = (val1 === "0" && val2 === "0") || (val1 !== "0");
                        }

                        // 5. Khóa D5 nếu D3 không thuộc 06, 07, 08
                        if (D3 && D5) {
                            if (D2 && D2.value === "")
                                D2.value = "0";
                            D5.disabled = !(D3.value === "02" || D3.value === "04");
                        }
                    }
                }
            }
            var current_page = 1; // trang bắt đầu 
            var records_per_page = 15; // số dòng
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
                listing_table.rows[1].style.display = "";
                listing_table.rows[2].style.display = "";
                listing_table.rows[3].style.display = "";
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
            <div id="divTitle" style="margin: 10px 0 10px 0">
                SỐ LIỆU TỔNG HỢP TỔ <s:property value="mato_to"/>
            </div>

            <table border="1" class="editDelete" align="center" style="width: 60%">
                <!--                <div id="divDonvitinh">
                                Đơn vị tính: Đồng
                            </div>-->
                <tr>
                    <th rowspan="2">Tổng số KH</th>
                    <th rowspan="2">Tổng số món vay</th>
                    <th rowspan="2">Tổng dư nợ</th>
                    <th colspan="3">Dư nợ</th>
                    <th rowspan="2">Nợ lãi</th>
                </tr>
                <tr>                   
                    <th>Nợ trong hạn</th>
                    <th>Nợ quá hạn</th>
                    <th>Nợ khoanh</th>
                </tr>
                <tr>   
                    <td class="number STT2"><s:property value="tong_kh"/> </td>
                    <td class="number STT2"><s:property value="tong_monvay"/> </td>
                    <td class="number STT3 style_h"><s:property value="tong_duno"/> </td>
                    <td class="number STT3 style_h"><s:property value="tong_than"/> </td>
                    <td class="number STT3 style_h"><s:property value="tong_qhan"/> </td>
                    <td class="number STT3"><s:property value="tong_khoanh"/> </td>
                    <td class="number STT3"><s:property value="tong_nlai"/> </td>
                </tr>
            </table>
            <div id="divTitle" style="margin: 10px 0 10px 0">
                SỐ LIỆU ĐỐI CHIẾU, PHÂN LOẠI NỢ
            </div> 
            <table border="1" class="editDelete" id="subTable" align="center" style="width: 98%">
                <tr>
                    <th rowspan="3"><input type="checkbox" id ="select-all"/></th>
                    <th rowspan="3" class="STT4">Tên khách hàng</th>
                    <th rowspan="3" class="STT4">Mã món vay</th>
                    <th rowspan="3" class="STT3">Chương trình</th>
                    <th colspan="5">Số liệu tại NHCSXH</th> 
                    <th colspan="4">Phân loại khả năng trả nợ</th> 
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
                    <th class="STT3">Cụ thể nguyên nhân 6,7,8</th>

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
                <s:iterator value="#attr.lstDulieuNtPLN_T" var="modelView" status="rowstatus">
                    <tr>
                        <td class="D0"> <input type="checkbox" class="myCheckBox"
                                               name="lstDulieuNtPLN_T[<s:property  value='%{#rowstatus.index}' />].checkrow"
                                               id="checkrow_<s:property value="%{#rowstatus.index}" />"
                                               value="0" onclick="$(this).val(this.checked ? 1 : 0)"/>      
                            <input type="hidden" value="<s:property  value="plnSoku" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnSoku"/>                             
                            <input type="hidden" value="<s:property  value="plnMapgd" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnMapgd"/>  
                            <input type="hidden" value="<s:property  value="plnTenkh" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnTenkh"/>  
                            <input type="hidden" value="<s:property  value="plnMakh" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnMakh"/>  
                            <input type="hidden" value="<s:property  value="plnMato" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnMato"/>  
                            <input type="hidden" value="<s:property  value="plnTongDno" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnTongDno"/>  
                            <input type="hidden" value="<s:property  value="plnDnothan" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnDnothan"/>  
                            <input type="hidden" value="<s:property  value="plnDnoqhan" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnDnoqhan"/>  
                            <input type="hidden" value="<s:property  value="plnDnokhoanh" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnDnokhoanh"/>
                            <input type="hidden" value="<s:property  value="plnTrangthai" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnTrangthai"/>  
                            <input type="hidden" value="<s:property  value="plnTonglaiton" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnTonglaiton"/>
                            <input type="hidden" value="<s:property  value="plnNogocClech" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnNogocClech"/> 
                            <input type="hidden" value="<s:property  value="plnNolaiClech" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnNolaiClech"/>
                            <input type="hidden" value="<s:property  value="plnNgnhanClech" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnNgnhanClech"/> 
                            <input type="hidden" value="<s:property  value="plnMacn" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnMacn"/> 
                            <input type="hidden" value="<s:property  value="plnQuanheKh" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnQuanheKh"/> 
                            <input type="hidden" value="<s:property  value="plnNguoiPln" />" 
                                   id="plnNguoiPln_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnNguoiPln"/>  
                            <input type="hidden" value="<s:property  value="D6 != null ? D6 : 0" />" 
                                   id="D6_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].D6"/>  
                            <input type="hidden" value="<s:property  value="D7 != null ? D7 : 0" />" 
                                   id="D7_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].D7"/>  
                        </td>
                        <td <s:if test="D7.equalsIgnoreCase('2')">style="color: blue" title="Món vay đã hỗ trợ"</s:if>
                                                                  ><s:property value="plnTenkh"/>
                        </td>
                        <td> 
                            <a href="javascript:hienthichitiet('<s:property value="plnSoku"/>','<s:property  value="plnNgaybc" />' ,'<s:property  value="plnMapgd" />' ,'<s:property  value="D6" />')" class="SOKU linkKh">
                                <s:property value='plnSoku'/>
                            </a>
                        </td>
                        <td><s:property value="plnChtrinhTenvt"/> </td>
                        <td class="number style_h" id="TongDno_<s:property value='%{#rowstatus.index}' />">
                            <s:property value="plnTongDno" /></td>
                        <td class="number style_h"><s:property value="plnDnothan"/> </td>
                        <td class="number style_h"><s:property value="plnDnoqhan"/> </td>
                        <td class="number style_h" id="Dnokhoanh_<s:property value='%{#rowstatus.index}' />">
                            <s:property value="plnDnokhoanh"/> </td>
                        <td class="number style_h"><s:property value="plnTonglaiton"/> </td>
                        <!--chi tieu nhap tay tu day--> 
                        <td>
                            <input type="text" 
                                   value="<s:property value="plnCKntnSodu" />" 
                                   id="D1_<s:property value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNtPLN_T[<s:property value="%{#rowstatus.index}" />].plnCKntnSodu" 
                                   class="number2" 
                                   style="background: #FFCCBA"
                                   onblur="setTongDuNoTo('D1', <s:property value='%{#rowstatus.index}' />)"/>
                        </td>
                        <td>
                            <input type="text" 
                                   value="<s:property value="D3" />" 
                                   id="D2_<s:property value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNtPLN_T[<s:property value="%{#rowstatus.index}" />].D3" 
                                   class="number2" 
                                   style="background: #FFCCBA"
                                   onblur="setTongDuNoTo('D2', <s:property value='%{#rowstatus.index}' />)"/>
                        </td>
                        <s:if test="D7.equalsIgnoreCase('2')">
                            <td class="D0">    
                                <select id='D3_<s:property value="%{#rowstatus.index}" />' style="width: 150px;"
                                        name='lstDulieuNtPLN_T[<s:property value="%{#rowstatus.index}" />].D10'
                                        onchange="ngnhanD4('D4', <s:property value='%{#rowstatus.index}' />)">
                                    <option value="0" style="text-align: center">----Chọn----</option>
                                    <s:iterator value="lstDmKhac106" status="ideRows" var="language">
                                        <s:if test="%{#language.code == '01'}">
                                            <option value="<s:property value="code" />" disabled style="font-weight:bold; color:#333;">
                                                <s:property value="code" /> - <s:property value="value" />
                                            </option>
                                        </s:if>
                                        <s:else>
                                            <option value="<s:property value="code" />"
                                                    <s:if test="%{#language.code == D10}">selected</s:if>>
                                                <s:property value="code" /> - <s:property value="value" />
                                            </option>
                                        </s:else>
                                    </s:iterator>   
                                </select>
                            </td>
                        </s:if>
                        <s:else>
                            <td class="D0">    
                                <select id='D3_<s:property value="%{#rowstatus.index}" />' style="width: 150px"
                                        name='lstDulieuNtPLN_T[<s:property value="%{#rowstatus.index}" />].D4'
                                        onchange="ngnhanKntn('D3', <s:property value='%{#rowstatus.index}' />)">
                                    <option value="0" style="text-align: center">----Chọn----</option>
                                    <s:iterator value="lstDmKhac57" status="ideRows" var="language">
                                        <s:if test="%{#language.code == '01' || #language.code == '02'}">
                                            <!-- Mục cần khóa -->
                                            <option value="<s:property value="code" />" disabled style="font-weight:bold; color:#333;">
                                                <s:property value="code" /> - <s:property value="value" />
                                            </option>
                                        </s:if>
                                        <s:else>
                                            <!-- Mục được chọn -->
                                            <option value="<s:property value="code" />"
                                                    <s:if test="%{#language.code == D4}">selected</s:if>>
                                                <s:property value="code" /> - <s:property value="value" />
                                            </option>
                                        </s:else>
                                    </s:iterator>

                                </select>
                            </td>

                        </s:else>

                        <td>
                            <textarea style="width: 250px" placeholder="Nhập tối đa 500 ký tự" id="D5_<s:property  value='%{#rowstatus.index}' />" 
                                      name="lstDulieuNtPLN_T[<s:property  value='%{#rowstatus.index}' />].D5" maxlength="500"><s:property value='D5'/></textarea>

                        </td>
                    </tr>

                </s:iterator>

            </table>
        </div>
        <div id="luu_thanhcong"></div>
        <script>
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
            function hienthichitiet(soku, ngay_bc, poscd, lock) {
                var ht1 = screen.availHeight - 200;
                var wt1 = 1024;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 100;
                var url = "getDetialLoanDcPLN.action?soku=" + soku + "&ngay_bc=" + ngay_bc + "&poscd=" + poscd + "&lock=" + lock;
                popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }

            function setTongDuNoTo(field, index) {
                const d1 = document.getElementById('D1_' + index);
                const d2 = document.getElementById('D2_' + index);
                const d3 = document.getElementById('D3_' + index);
                const d5 = document.getElementById('D5_' + index);
                const tongdunoText = document.getElementById('TongDno_' + index).innerText;
                const sDnokhoanh = document.getElementById('Dnokhoanh_' + index).innerText;

                // Loại bỏ dấu phẩy hoặc chấm, chuyển sang số
                const tongduno = parseFloat(tongdunoText.replace(/,/g, '').replace(/\./g, '')) || 0;
                const Dnokhoanh = parseFloat(sDnokhoanh.replace(/,/g, '').replace(/\./g, '')) || 0;
//                console.log("Dnokhoanh1 = " + Dnokhoanh);
                // Định dạng số có dấu phẩy
                const formatted = tongduno.toLocaleString('en-US');

                if (field === 'D1') {
                    d1.value = formatted;
                    d2.value = '0';
                    d3.value = '0';
                    d5.value = '';
                    d5.disabled = true;
                } else if (field === 'D2') {
                    d2.value = formatted;
                    d1.value = '0';
                }

                // Kiểm tra điều kiện để enable/disable D3
                const d1Val = parseFloat(d1.value.replace(/,/g, '')) || 0;
                const d2Val = parseFloat(d2.value.replace(/,/g, '')) || 0;

                if (d2Val !== 0 && Dnokhoanh === 0) {
                    d3.disabled = false; // Nếu D2 có giá trị khác 0 -> mở D3
                } else if (d1Val !== 0) {
                    d3.disabled = true;  // Nếu D1 khác 0 -> khóa D3
                } else {
                    d3.disabled = true;  // Nếu cả hai = 0 -> cũng khóa
                }
            }

            function ngnhanKntn(field, index) {
                const d3 = document.getElementById('D3_' + index);
                const d5 = document.getElementById('D5_' + index);

                const valD3 = d3.value.trim();

                if (["06", "07", "08"].includes(valD3)) {
                    d5.disabled = false;
                    d5.style.backgroundColor = "#ffffcc";
                } else {
                    d5.disabled = true;
                    d5.value = "";
                }
            }

            function ngnhanD4(field, index) {
                const d3 = document.getElementById('D3_' + index);
                const d5 = document.getElementById('D5_' + index);

                const valD3 = d3.value.trim();

                if (["02", "04"].includes(valD3)) {
                    d5.disabled = false;
                    d5.style.backgroundColor = "#ffffcc";
                } else {
                    d5.disabled = true;
                    d5.value = "";
                }
            }

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
