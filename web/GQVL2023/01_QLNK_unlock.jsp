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
        width: auto;
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
        position: static;
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

            function initTable()
            {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    try {
                        document.getElementById("D26_" + i).value = "1";
                        document.getElementById("D8_" + i).disabled = true;
                        document.getElementById("D9_" + i).disabled = true;
                        document.getElementById("D27_" + i).disabled = true;
                        document.getElementById("D10_" + i).disabled = true;
                        document.getElementById("D11_" + i).disabled = true;
                        document.getElementById("D12_" + i).disabled = true;
                        document.getElementById("D29_" + i).disabled = true;
                        document.getElementById("D14_" + i).disabled = true;
                        document.getElementById("D15_" + i).disabled = true;
                        document.getElementById("D28_" + i).disabled = true;
                        document.getElementById("D16_" + i).disabled = true;
                        document.getElementById("D17_" + i).disabled = true;

                    } catch (e) {
                    }
                }

            }

//            window.onload = function () {
//                changePage(current_page);
//            };

        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;height: 400px;">             
            <div id="divTitle">
                DANH SÁCH MÓN KHOANH NỢ ĐÃ PHÊ DUYỆT
            </div>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th  rowspan="2" class="D0 STT1 ">
                        <input type="checkbox" id ="select-all"/>
                    </th> 
                    <!--<th rowspan="3" class="STT1">Phê duyệt</th>--> 
                    <th rowspan="2" class="STT1">S<br>T<br>T</th>                           
                    <th rowspan="2 class="STT4">Họ và tên</th>  
                    <th rowspan="2" class="STT2">Mã món vay</th>  
                    <th colspan="5">PHẦN THEO DÕI TẠI NGÂN HÀNG</th>
                    <!--<th colspan="10" style="color: #ff6600">PHẦN KIỂM TRA THỰC TẾ TẠI KHÁCH HÀNG</th>-->
<!--                    <th rowspan="3" class="STT2">Nguyên nhân chênh lệch</th>      
                    <th rowspan="3" class="STT5">Ký xác nhận của khách hàng</th> -->
                </tr>         
<!--                <tr >
-->                    <th rowspan="1" class="STT5">Dư nợ gốc</th>  
                    <th rowspan="1" class="STT5">Dư gốc khoanh</th>    
                    <th rowspan="1" class="STT5">Số tiền lãi <br>còn nợ NH</th>                             
                    <th rowspan="1" class="STT5">Ngày <br>bắt đầu khoanh nợ</th>   
                    <th rowspan="1" class="STT5">Ngày <br>hết hạn khoanh nợ</th> <!--
                    <th rowspan="2" class="STT5" style="color: #ff6600">Dư nợ gốc</th>  
                    <th rowspan="2" class="STT5" style="color: #ff6600">Dư gốc khoanh</th>  
                    <th rowspan="2" class="STT5" style="color: #ff6600">Số tiền lãi <br>còn nợ NH</th>     
                    <th rowspan="2" class="STT5" style="color: #ff6600">Thực trạng dự án phương án vay vốn</th>                             
                    <th rowspan="2" class="STT5" style="color: #ff6600">Tình hình thực tế của khách hàng</th>   
                    <th rowspan="2" class="STT5" style="color: #ff6600">Khả năng trả nợ của khách hàng</th> 
                    <th rowspan="2" class="STT5" style="color: #ff6600">Khách hàng cam kết trả nợ</th>
                    <th colspan="3" class="STT5" style="color: #ff6600">Chênh lệch</th>
                </tr>
                <tr>
                    <th rowspan="1" class="STT5" style="color: #ff6600">Dư nợ gốc</th>  
                    <th rowspan="1" class="STT5" style="color: #ff6600">Dư gốc khoanh</th>
                    <th rowspan="1" class="STT5" style="color: #ff6600">Số tiền lãi <br>còn nợ NH</th> 
                </tr>-->
                <tr style="font-style: italic;">
                    <th style="color: #000; font-style: italic; font-size: xx-small;"></th>                    
                    <!--<th><input type="checkbox" id ="select-all1" checked/></th>-->
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(8)</th>
<!--                    <th style="color: #000; font-style: italic; font-size: xx-small;">(9)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(10)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(11)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(12)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(13)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(14)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(15)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(16)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(17)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(18)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(19)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(20)</th>-->
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0">
                            <input id="check<s:property  value='%{#rowstatus.index}' />" type="checkbox" class="myCheckBox sstyle"
                                   name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D18"/>       
                        </td>
<!--                        <td class="D0">
                            <input id="D26_<s:property  value='%{#rowstatus.index}' />" type="checkbox" class="myCheckBox1 sstyle"
                                   name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D26" 
                                   <s:if test="D26.equalsIgnoreCase('1')"> checked title="Số liệu đã phê duyệt"</s:if> 
                                       onclick="$(this).val(this.checked ? 1 : 0)"/>
                            </td>-->
                            <td class="D0 STT1 sstyle" style="background: #ddd"> <s:property value="%{#rowstatus.index + 1}" /> 
                            <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>                             
                            <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
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
                            <input type="hidden" value="<s:property  value="D8" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"/>
                            <input type="hidden" value="<s:property  value="D9" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/>
                            <input type="hidden" value="<s:property  value="D10" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10"/>
                            <input type="hidden" value="<s:property  value="D11" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11"/>
                            <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                            <input type="hidden" value="<s:property  value="D13" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13"/>
                            <input type="hidden" value="<s:property  value="D14" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14"/>
                            <input type="hidden" value="<s:property  value="D15" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/>
                            <input type="hidden" value="<s:property  value="D16" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16"/>
                            <input type="hidden" value="<s:property  value="D17" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17"/>
                            <input type="hidden" value="<s:property  value="NGAYBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGAYBC"/>
                   
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
<!--                        <td class="D0" style="background: #ddd">
                            <input type="text" value="<s:property  value="D8" />" style="width: 80px; " readonly="true"
                                   id="D8_<s:property  value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="number sstyle"/>
                        </td>
                        <td class="D0" style="background: #ddd">
                            <input type="text" value="<s:property  value="D9" />" style="width: 80px;" readonly="true"
                                   id="D9_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="number sstyle"/>
                        </td>
                        <td class="D0" style="background: #ddd">
                            <input type="text" value="<s:property  value="D27" />" style="width: 80px;" readonly="true"
                                   id="D27_<s:property  value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27" class="number sstyle STT5"/>
                        </td>
                        <td class="D0" style="background: #ddd">
                            <textarea  readonly="true" style="background: #ddd"
                                       class="STT3 sstyle" placeholder="Nhập tối đa 200 ký tự" id="D10_<s:property  value='%{#rowstatus.index}' />" 
                                       name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D10"><s:property value='D10'/></textarea>
                        </td>
                        <td class="D0" style="background: #ddd">
                            <textarea  readonly="true" style="background: #ddd"
                                       class="STT3 sstyle" placeholder="Nhập tối đa 200 ký tự" id="D11_<s:property  value='%{#rowstatus.index}' />" 
                                       name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D11"><s:property value='D11'/></textarea>
                        </td>
                        <td class="D0" style="background: #ddd">
                            <select style="border: hidden" readonly="true"
                                    class="sstyle" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D12" id="D12_<s:property  value='%{#rowstatus.index}' />" > 
                                <option value="0" style="text-align: center" <s:if test="D12.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="D12.equalsIgnoreCase('1')"> selected </s:if>>Không có khả năng trả nợ</option>
                                <option value="2" <s:if test="D12.equalsIgnoreCase('2')"> selected </s:if>>Chưa có khả năng trả nợ</option>                        
                                <option value="3" <s:if test="D12.equalsIgnoreCase('3')"> selected </s:if>>Có khả năng trả nợ</option>
                                </select>
                            </td>
                            <td class="D0" style="background: #ddd">
                                <select style="border: hidden" class="sstyle" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D29" id="D29_<s:property  value='%{#rowstatus.index}' />"
                                    <option value="0" style="text-align: center" <s:if test="D29.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="D29.equalsIgnoreCase('1')"> selected </s:if>>Không cam kết</option>
                                <option value="2" <s:if test="D29.equalsIgnoreCase('2')"> selected </s:if>>Thực hiện cam kết</option>
                                <option value="3" <s:if test="D29.equalsIgnoreCase('3')"> selected </s:if>>Không thực cam kết</option>
                                </select>
                                                            <select style="border: hidden"
                                                                    class="sstyle" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D29" id="D29_<s:property  value='%{#rowstatus.index}' />" > 
                                                            <option value="0" style="text-align: center" <s:if test="D29.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                                            <option value="1" <s:if test="D29.equalsIgnoreCase('1')"> selected </s:if>>Không cam kết</option>
                                                            <option value="2" <s:if test="D29.equalsIgnoreCase('2')"> selected </s:if>>Thực hiện cam kết</option>
                                                            <option value="3" <s:if test="D29.equalsIgnoreCase('3')"> selected </s:if>>Không thực hiện cam kết</option>
                                                            </select>
                            </td>
                            <td style="background: #ddd">
                                <input type="text" value="<s:property  value="D14" />" id="D14_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="number sstyle STT5"/>
                        </td>
                        <td style="background: #ddd">
                            <input type="text" value="<s:property  value="D15" />" id="D15_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="number sstyle STT5"/>
                        </td>
                        <td class="D0" style="background: #ddd">
                            <input type="text" value="<s:property  value="D28" />" style="width: 80px;"
                                   id="D28_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" class="number sstyle STT5"/>
                        </td>
                        <td class="D0" style="background: #ddd">
                            <textarea class="STT3 sstyle"  placeholder="Nhập tối đa 200 ký tự" id="D16_<s:property  value='%{#rowstatus.index}' />" 
                                      name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D16"><s:property value='D16'/></textarea>
                        </td>
                        <td class="D0" style="background: #ddd">
                            <select style="border: hidden" class="sstyle" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D17" id="D17_<s:property  value='%{#rowstatus.index}' />" > 
                                <option value="1" <s:if test="D17.equalsIgnoreCase('1')"> selected </s:if>>Không</option>
                                <option value="2" <s:if test="D17.equalsIgnoreCase('2')"> selected </s:if>>Có</option>
                                </select>
                            </td>  -->
                        </tr>
                </s:iterator>
            </table>


        </div>
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

        </script>
    </body>
</html>
