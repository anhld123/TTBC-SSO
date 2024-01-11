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
        width: 130%;
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
        position: sticky;
        top: 0;
        z-index: 10;
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
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".STT1").css({"width": "50px"});
                $(".STT2").css({"width": "100px"});
                $(".STT3").css({"width": "150"});
                $(".STT4").css({"width": "200px"});
                $(".STT5").css({"width": "85px"});
                $(".STT6").css({"width": "65px"});
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

            $(function () {
                setCssStyle();
            });

            function addMonths(date, months) {
                date.setMonth(date.getMonth() + months);
                return date;
            }

            function setCssStyle() {
                $(".cssDate").datepicker({
                    dateFormat: 'dd/mm/yy',
//                    showOn: "button",
//                    buttonImage: "img/icon-ui_datepicker.png",
//                    buttonImageOnly: true,
//                    showButtonPanel: true,
//                    buttonText: "icono",
                    changeMonth: true,
                    changeYear: true,
                    yearRange: "c-50:c+50",
                    beforeShow: function (input, inst) {
                        if ($(input).is(':disabled')) {
                            return false; // Ngăn chặn datepicker hiển thị nếu input bị disabled
                        }
                    },
                    // Thêm CSS cho ngày tháng
                    onSelect: function (dateText, inst) {
                        $(this).css({
                            'font-size': '13px', // Cỡ chữ
                            'color': 'red' // Màu chữ
                        });
                        var _freezeDateName = this.name;
                        var _expireDateName = _freezeDateName.replace('D11', 'D13');
                        var _freezeMonthName = _freezeDateName.replace('D11', 'D12');
                        var _freezeMonthValue = parseInt($("input[name='" + _freezeMonthName + "']").val());

                        var toDate = new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay);//Date one month after selected date
                        var oneDay = new addMonths(toDate, _freezeMonthValue);

                        $("input[name='" + _expireDateName + "']").val($.datepicker.formatDate('dd/mm/yy', oneDay));

                        $("input[name='" + _expireDateName + "']").css({
                            'font-size': '13px', // Cỡ chữ
                            'color': 'red' // Màu chữ
                        });
                    }
                }).on('change', function (event) {
                    event.preventDefault();
                    $(this).css({
                        'font-size': '13px', // Cỡ chữ
                        'color': 'red' // Màu chữ
                    });
                    var _freezeDateName = this.name;
                    var _expireDateName = _freezeDateName.replace('D11', 'D13');
                    var _freezeMonthName = _freezeDateName.replace('D11', 'D12');
                    var _freezeMonthValue = parseInt($("input[name='" + _freezeMonthName + "']").val());

                    let [day, month, year] = this.value.split('/');
                    const toDate = new Date(+year, +month - 1, +day);
                    var oneDay = new addMonths(toDate, _freezeMonthValue);
                    $("input[name='" + _expireDateName + "']").val($.datepicker.formatDate('dd/mm/yy', oneDay));
                    $("input[name='" + _expireDateName + "']").css({
                        'font-size': '13px', // Cỡ chữ
                        'color': 'red' // Màu chữ
                    });
                });

                $(".cssDate2").datepicker({
                    dateFormat: 'dd/mm/yy',
                    //showOn: "button",
                    //buttonImage: "img/icon-ui_datepicker.png",
                    //buttonImageOnly: false,
                    //showButtonPanel: false,
                    //buttonText: "icono",
                    changeMonth: true,
                    changeYear: true,
                    yearRange: "c-50:c+50",
                    beforeShow: function (input, inst) {
                        if ($(input).is(':disabled')) {
                            return false; // Ngăn chặn datepicker hiển thị nếu input bị disabled
                        }
                    },
                    // Thêm CSS cho ngày tháng
                    onSelect: function (dateText, inst) {
                        $(this).css({
                            'font-size': '13px', // Cỡ chữ
                            'color': 'red' // Màu chữ
                        });
                    }
                });
            }

            var current_page = 1;// trang bắt đầu 
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



            function initTable()
            {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    try {
                        var D26 = document.getElementById('D26_' + i).checked;
                        if (D26 === true)
                        {
                            document.getElementById("D26_" + i).disabled = true;
                            document.getElementById("check" + i).disabled = true;
                        } else
                        {
                            document.getElementById("D26_" + i).disabled = false;
                            document.getElementById("check" + i).disabled = false;
                        }
                        var D19 = document.getElementById('D19_' + i).value;
                        if (D19 === "06" || D19 === "07" || D19 === "02")
                        {
                            document.getElementById("D10_" + i).disabled = true;
                            document.getElementById("D10_" + i).style.color = "#ddd";
                            document.getElementById("D10_" + i).placeholder = "";
                        } else
                        {
                            document.getElementById("D10_" + i).disabled = false;
                        }
                    } catch (e) {
                    }
                }

            }

            window.onload = function () {
                changePage(current_page);
            };
        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;height: 400px;">  
            <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_QLNK_2023" theme="simple">
                <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                    <input type="hidden" id="<s:property  value="sKey" />" 
                           name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
                </s:iterator>
                <br>
                <div id="divTitle">
                    QUẢN LÝ NỢ KHOANH 

                </div>    
                Chọn trang <input style="border-top-style: hidden; border-left-style: hidden; border-right-style: hidden " class="STT1" type="number" id="pageInput" min="1" max="numPages()"/>
                <a onclick="goToPage()" href='#' id ="btn_go">Go</a>
                <a onclick="prevPage()" href='#' id="btn_prev">&#8920;</a> 
                Trang <span id="page"></span>
                <a onclick="nextPage()" href='#' id="btn_next">&#8921;</a>
                <div id="divDonvitinh">
                    Đơn vị tính: Đồng
                </div>
                <table border="1" class="editDelete" id="subTable" align="center">               
                    <tr> 
                        <th  rowspan="3" class="D0 STT1 ">
                            <input type="checkbox" id ="select-all"/>
                        </th> 
                        <th rowspan="3" class="STT1">Phê duyệt</th> 
                        <th rowspan="3" class="STT1">S<br>T<br>T</th>                           
                        <th rowspan="3" class="STT4">Họ và tên</th>  
                        <th rowspan="3" class="STT2">Mã món vay</th>  
                        <th colspan="5">PHẦN THEO DÕI TẠI NGÂN HÀNG</th>
                        <th colspan="8" style="color: #ff6600">PHẦN KIỂM TRA THỰC TẾ TẠI KHÁCH HÀNG</th>
                        <th rowspan="3" class="STT2">Nguyên nhân chênh lệch</th>      
                        <th rowspan="3" class="STT5">Ký xác nhận của khách hàng</th> 
                    </tr>         
                    <tr >
                        <th rowspan="2" class="STT5">Dư nợ gốc</th>  
                        <th rowspan="2" class="STT5">Dư gốc khoanh</th>    
                        <th rowspan="2" class="STT5">Số tiền lãi</th>                             
                        <th rowspan="2" class="STT5">Ngày <br>bắt đầu khoanh nợ</th>   
                        <th rowspan="2" class="STT5">Ngày <br>hết hạn khoanh nợ</th> 
                        <th rowspan="2" class="STT5" style="color: #ff6600">Dư nợ gốc</th>  
                        <th rowspan="2" class="STT5" style="color: #ff6600">Dư gốc khoanh</th>    
                        <th rowspan="2" class="STT5" style="color: #ff6600">Thực trạng dự án phương án vay vốn</th>                             
                        <th rowspan="2" class="STT5" style="color: #ff6600">Tình hình thực tế của khách hàng</th>   
                        <th rowspan="2" class="STT5" style="color: #ff6600">Khả năng trả nợ của khách hàng</th> 
                        <th rowspan="2" class="STT5" style="color: #ff6600">Khách hàng cam kết trả nợ</th>
                        <th colspan="2" class="STT5" style="color: #ff6600">Chênh lệch dư nợ</th>
                    </tr>
                    <tr>
                        <th rowspan="1" class="STT5" style="color: #ff6600">Dư nợ gốc</th>  
                        <th rowspan="1" class="STT5" style="color: #ff6600">Dư gốc khoanh</th> 
                    </tr>
                    <tr style="font-style: italic;">
                        <th style="color: #000; font-style: italic; font-size: xx-small;"></th>
                        <th><input type="checkbox" id ="select-all1"/></th> 
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
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(14)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(15)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(16)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(17)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(18)</th>
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                        <tr id="tablefix"> 
                            <td class="D0">
                                <input id="check<s:property  value='%{#rowstatus.index}' />" type="checkbox" class="myCheckBox sstyle"
                                       name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D18"/>       
                            </td>
                            <td class="D0">
                                <input id="D26_<s:property  value='%{#rowstatus.index}' />" type="checkbox" class="myCheckBox1 sstyle"
                                       name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D26" 
                                       <s:if test="D26.equalsIgnoreCase('1')"> checked title="Số liệu đã phê duyệt"</s:if> 
                                           onclick="$(this).val(this.checked ? 1 : 0)"/>
                                </td>
                                <td class="D0 STT1 sstyle" style="background: #ddd"> <s:property value="%{#rowstatus.index + 1}" /> 
                                <s:if test ="D26.equalsIgnoreCase('1')">
                                  <input type="hidden" value="<s:property  value="D26" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D26"
                                       id="D26_<s:property  value='%{#rowstatus.index}' />"/>
                                </s:if>
                                <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>
                                <input type="hidden" value="<s:property  value="D19" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19"
                                       id="D19_<s:property  value='%{#rowstatus.index}' />"/>
                                <input type="hidden" value="<s:property  value="D20" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20"/>
                                <input type="hidden" value="<s:property  value="D21" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21"/>
                                <input type="hidden" value="<s:property  value="D22" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22"/>
                                <input type="hidden" value="<s:property  value="D23" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23"/>
                                <input type="hidden" value="<s:property  value="D24" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24"/>
                                <input type="hidden" value="<s:property  value="D25" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25"/>
                                <input type="hidden" value="<s:property  value="D27" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27"/>
                                <input type="hidden" value="<s:property  value="D28" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28"/>
                                <input type="hidden" value="<s:property  value="D29" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D29"/>
                                <input type="hidden" value="<s:property  value="D30" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30"/>

                            </td>
                            <td style="background: #ddd; width: 100px"> <s:property  value="D1" />              
                                <input type="hidden" value="<s:property  value="D1" />" readonly="true"
                                       id="D1_<s:property  value='%{#rowstatus.index}' />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="SOKU sstyle"/>
                            </td>                                  
                            <td style="background: #ddd; width: 100px"> <s:property  value="D2" />   
                                <input type="hidden" value="<s:property  value="D2" />" readonly="true"
                                       id="D2_<s:property  value='%{#rowstatus.index}' />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D0 sstyle"/>
                            </td>                                  
                            <td style="background: #ddd">
                                <input type="text" value="<s:property  value="D3" />" readonly="true"
                                       id="D3_<s:property  value='%{#rowstatus.index}' />"
                                       style="width: 80px; background: #ddd"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="STT5 number sstyle"/>
                            </td>                                                                                                
                            <td style="background: #ddd">
                                <input type="text" value="<s:property  value="D4" />" readonly="true" style="background: #ddd"
                                       id="D4_<s:property  value='%{#rowstatus.index}' />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="STT5 number sstyle"/>
                            </td>
                            <td style="background: #ddd">
                                <input type="text" value="<s:property  value="D5" />" readonly="true" style="background: #ddd"
                                       id="D5_<s:property  value='%{#rowstatus.index}' />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="STT5 number sstyle"/>
                            </td>
                            <td class="D0" style="background: #ddd">
                                <input type="text" value="<s:property  value="D6" />" readonly="true" style="background: #ddd"
                                       id="D6_<s:property  value='%{#rowstatus.index}' />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="STT6 D0 sstyle"/>
                            </td>
                            <td class="D0" style="background: #ddd">
                                <input type="text" value="<s:property  value="D7" />" readonly="true" style="background: #ddd"
                                       id="D7_<s:property  value='%{#rowstatus.index}' />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="STT6 D0 sstyle"/>
                            </td>
                            <td class="D0">
                                <input type="text" value="<s:property  value="D8" />" style="width: 80px;"
                                       id="D8_<s:property  value='%{#rowstatus.index}' />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="number sstyle"/>
                            </td>
                            <td class="D0">
                                <input type="text" value="<s:property  value="D9" />" style="width: 80px;"
                                       id="D9_<s:property  value='%{#rowstatus.index}' />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="number sstyle"/>
                            </td>
                            <td class="D0">
                                <textarea  class="STT3 sstyle" placeholder="Nhập tối đa 200 ký tự" id="D10_<s:property  value='%{#rowstatus.index}' />" 
                                           name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D10"><s:property value='D10'/></textarea>
                            </td>
                            <td class="D0">
                                <textarea  class="STT3 sstyle" placeholder="Nhập tối đa 200 ký tự" id="D11_<s:property  value='%{#rowstatus.index}' />" 
                                           name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D11"><s:property value='D11'/></textarea>
                            </td>
                            <td class="D0">
                                <select  class="sstyle" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D12" id="D12_<s:property  value='%{#rowstatus.index}' />" > 
                                    <option value="1" <s:if test="D12.equalsIgnoreCase('1')"> selected </s:if>>1: Không có khả năng trả nợ</option>
                                    <option value="2" <s:if test="D12.equalsIgnoreCase('2')"> selected </s:if>>2: Có khả năng trả nợ</option>
                                    </select>
                                </td>
                                <td class="D0">
                                    <select class="sstyle" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D13" id="D13_<s:property  value='%{#rowstatus.index}' />" > 
                                    <option value="1" <s:if test="D13.equalsIgnoreCase('1')"> selected </s:if>>1: Không cam kết</option>
                                    <option value="2" <s:if test="D13.equalsIgnoreCase('2')"> selected </s:if>>2: Có cam kết</option>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" value="<s:property  value="D14" />" id="D14_<s:property  value='%{#rowstatus.index}' />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="number sstyle STT5"/>
                            </td>
                            <td >
                                <input type="text" value="<s:property  value="D15" />" id="D15_<s:property  value='%{#rowstatus.index}' />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="number sstyle STT5"/>
                            </td>
                            <td class="D0">
                                <textarea class="STT3 sstyle"  placeholder="Nhập tối đa 200 ký tự" id="D16_<s:property  value='%{#rowstatus.index}' />" 
                                          name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D16"><s:property value='D16'/></textarea>
                            </td>
                            <td class="D0">
                                <select class="sstyle" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D17" id="D17_<s:property  value='%{#rowstatus.index}' />" > 
                                    <option value="1" <s:if test="D17.equalsIgnoreCase('1')"> selected </s:if>>1: Không</option>
                                    <option value="2" <s:if test="D17.equalsIgnoreCase('2')"> selected </s:if>>2: Có</option>
                                    </select>
                                </td>  
                            </tr>
                    </s:iterator>
                </table>


            </div>
            <sj:submit id="QLNK_2023_save" name="QLNK_2023_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            $(function () {
                $('#select-all').click(function (event) {
                    // Iterate each checkbox
                    $('.myCheckBox').each(function () {
                        if (!this.disabled) {
                            this.checked = $('#select-all').prop('checked');
                            this.value = this.checked ? '1' : '0';
                        }
                    });
                });
            });

            $(function () {
                $('#select-all1').click(function (event) {
                    // Iterate each checkbox
                    $('.myCheckBox1').each(function () {
                        if (!this.disabled) {
                            this.checked = $('#select-all1').prop('checked');
                            this.value = this.checked ? '1' : '0';
                        }
                    });
                });
            });

            function initTable1()
            {
                nextPage();
                prevPage();
            }
            initTable1();
        </script>
    </body>
</html>
