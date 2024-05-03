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
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".SOKU").css({"width": "100%"});
                $(".TD_CHECKBOX").css({"width": "20px"});
                $(".TD_SOKU").css({"width": "80px"});
                $(".TD_TENKH123").css({"width": "110px"});
                $(".TD_TENTS").css({"width": "190px"});
                $(".TD_SOTK").css({"width": "105px"});
                $(".TD_MAKH").css({"width": "60px"});
                $(".TD_THOIGIAN").css({"width": "55px"});
                $(".TD_MAPGD").css({"width": "45px"});
                $(".TD_BUTTON1").css({"width": "40px"});
                $(".TD_SOTIEN").css({"width": "100px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>     

        <script>


            var max_row = 0;
            function initTable()
            {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    var matmp = getMabyNumber(i);//    
                    if (matmp == 1)
                    {
                        $('input:checkbox[id=' + i + ']').attr('checked', true);
                    }
                }
            }

            function getMabyNumber(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
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
                listing_table.rows[1].style.display = "";
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
            window.onload = function () {
                changePage(current_page);
            };
        </script>

        <style>                                                

            .hdtitle1 {
                z-index: 6;
                font-style: italic;
                font-size: xx-small;
                width: 99%;
            }
            #subTable {
                font-size: 16px;
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                border-spacing: 0;
                width: 95%;
            }


            #subTable th, #subTable td {
                border: 1px solid gray;
                width: auto;
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

    </head>
    <body style="font-family: ">
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="save_%{khoa_nhaptaycn}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            </br>
            <div id="divTitle">
                HUY ĐỘNG TIẾT KIỆM
                <BR>                    
            </div>

            <s:hidden name="khoa_nhaptaycn"/>
            Chọn trang <input style="border-top-style: hidden; border-left-style: hidden; border-right-style: hidden ;width: 50px" class="STT1" type="number" id="pageInput" min="1" max="numPages()"/>
            <a onclick="goToPage()" href='#' id ="btn_go">Go</a>
            <a onclick="prevPage()" href='#' id="btn_prev">&#8920;</a> 
            Trang <span id="page"></span>
            <a onclick="nextPage()" href='#' id="btn_next">&#8921;</a>
            <div id="divDonvitinh">
                Tra cứu: <input type="text" id="search" style="width: 400px" placeholder=" Tìm kiếm ...">
            </div>
            <div style="overflow:scroll; width: 99vw;">
                <table border="1" id="subTable" align="center">
                    <tr>
                        <th style="width: 20px;">&nbsp;</th>
                        <th>GL</th>
                        <th>Số sổ</th>
                        <th>Số TK</th>
                        <th style="width: 100px">Mã KH</th>
                        <th>Tên KH</th>
                        <th style="width: 100px">Sản phẩm</th>
                        <th>Ngày gán sổ</th>
                        <th>Số dư SK</th>
                        <th>Số dư HĐ</th>
                        <th>Kỳ hạn</th>
                        <th>Cán bộ</th>
                        <th>Ngày gửi</th>
                    </tr>
                    <tr style="font-style: italic;">
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
                            <td class="clsChon">
                                <s:if test="%{D10 != null}">
                                    <input type="checkbox" id="<s:property value="D3"/>" name="chkChon" value='<s:property value="D3"/>' checked>
                                </s:if>
                                <s:else>
                                    <input type="checkbox" id="<s:property value="D3"/>" name="chkChon" value='<s:property value="D3"/>'>
                                </s:else>
                            </td>
                            <td><input type="text" class='css_text' value='<s:property value="D1"/>' readonly="readonly" id ="D1_<s:property  value='%{#rowstatus.index}' />"></td>
                            <td><input type="text" class='css_text' value='<s:property value="D2"/>' readonly="readonly" id ="D2_<s:property  value='%{#rowstatus.index}' />"></td>
                            <td><input type="text" style="text-align: right" class='css_text' value='<s:property value="D3"/>' readonly="readonly" id ="D3_<s:property  value='%{#rowstatus.index}' />"></td>
                            <td><input type="text" style="text-align: right" class='css_text' value='<s:property value="D4"/>' readonly="readonly" id ="D4_<s:property  value='%{#rowstatus.index}' />"></td>
                            <td><input type="text" class='css_text' value='<s:property value="D5"/>' readonly="readonly" id ="D5_<s:property  value='%{#rowstatus.index}' />"></td>
                            <td><input type="text" style="text-align: right" class='css_text' value='<s:property value="D6"/>' readonly="readonly" id ="D6_<s:property  value='%{#rowstatus.index}' />"></td>
                            <td><input type="text" class='css_text' value='<s:property value="D11"/>' readonly="readonly" id ="D11_<s:property  value='%{#rowstatus.index}' />"></td>
                            <td><input type="text" class='css_text number' value='<s:property value="D7"/>' readonly="readonly" id ="D7_<s:property  value='%{#rowstatus.index}' />"></td>
                            <td><input type="text" class='css_text number' value='<s:property value="D8"/>' readonly="readonly" id ="D8_<s:property  value='%{#rowstatus.index}' />"></td>
                            <td><input type="text" class='css_text number' value='<s:property value="D9"/>' readonly="readonly" id ="D9_<s:property  value='%{#rowstatus.index}' />"></td>
                            <td><input type="text" class='css_text' value='<s:property value="D10"/>' readonly="readonly" id ="D10_<s:property  value='%{#rowstatus.index}' />"></td>
                            <td><input type="text" class='css_text' value='<s:property value="D12"/>' readonly="readonly" id ="D12_<s:property  value='%{#rowstatus.index}' />"></td>

                        </tr>                                                                                                 
                    </s:iterator>

                </table>  
                <div style="display: none;">
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">  
                        <s:if test="%{D10 != null}">
                            <input type="checkbox" id="<s:property value="D3"/>" name="chkChon" value='<s:property value="D3"/>' checked>
                        </s:if>
                        <s:else>
                            <input type="checkbox" id="<s:property value="D3"/>" name="chkChon" value='<s:property value="D3"/>'>
                        </s:else>
                        <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value='<s:property value="D1"/>' readonly="readonly">
                        <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value='<s:property value="D2"/>' readonly="readonly">
                        <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" value='<s:property value="D3"/>' readonly="readonly">
                        <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" value='<s:property value="D4"/>' readonly="readonly">
                        <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" value='<s:property value="D5"/>' readonly="readonly">
                        <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" value='<s:property value="D6"/>' readonly="readonly">
                        <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" value='<s:property value="D7"/>' readonly="readonly">
                        <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" value='<s:property value="D8"/>' readonly="readonly">
                        <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value='<s:property value="D9"/>' readonly="readonly">
                        <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" value='<s:property value="D10"/>' readonly="readonly">
                        <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" value='<s:property value="D12"/>' readonly="readonly">
                    </s:iterator>
                    <input type="text" value="<s:property value="displaNone"/>" name="chkDate" id="chkDate">        
                </div>
            </div>
            <sj:submit id="HUYDONG_2024_save" name="HUYDONG_2024_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>

            initTable();

            function initTable1()
            {
                nextPage();
                prevPage();
            }
            initTable1();

            function setChecked(id) {
                if ($('#' + id).prop('checked')) {
                    $('#' + id).prop('checked', false);
                } else {
                    $('#' + id).prop('checked', true);
                }
            }

            var $rows = $('#subTable tr').not(':first');
            $('#search').keyup(function () {
                var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();

                $rows.show().filter(function () {
                    var text = $(this).find('input[type="text"]').map(function () {
                        return $(this).val();
                    }).get().join(' ').toLowerCase();
                    return !~text.indexOf(val);
                }).hide();
            });
        </script>
    </body>


</html>
