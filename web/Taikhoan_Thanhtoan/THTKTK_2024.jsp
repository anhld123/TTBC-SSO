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
        width: 98%;
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
    @-webkit-keyframes my {
        0% { color: red; } 
        50% { color: #fff;  } 
        100% { color: red;  } 
    }
    @-moz-keyframes my { 
        0% { color: red;  } 
        50% { color: #fff;  }
        100% { color: red;  } 
    }
    @-o-keyframes my { 
        0% { color: red; } 
        50% { color: #fff; } 
        100% { color: red;  } 
    }
    @keyframes my { 
        0% { color: red;  } 
        50% { color: #fff;  }
        100% { color: red;  } 
    } 
    .color_11 {
        background:#fff;
        font-size:14px;
        font-weight:bold;
        -webkit-animation: my 700ms infinite;
        -moz-animation: my 700ms infinite; 
        -o-animation: my 700ms infinite; 
        animation: my 700ms infinite;
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
                $(".STT1").css({"width": "30px"});
                $(".STT2").css({"width": "90px"});
                $(".STT3").css({"width": "150"});
                $(".STT4").css({"width": "200px"});
                $(".STT5").css({"width": "70px"});
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

        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;height: 400px;">             
            <div id="divTitle">
                BÁO CÁO TÌNH HÌNH TÀI KHOẢN THANH TOÁN
                <s:if test="txtGetData.equalsIgnoreCase('1')"><a style="color: #009900"> (Kỳ báo cáo tuần)</a></s:if>
                <s:elseif test="txtGetData.equalsIgnoreCase('2')"><a style="color: #009900"> (Kỳ báo cáo tháng)</a></s:elseif>
                <s:elseif test="txtGetData.equalsIgnoreCase('3')"><a style="color: #009900"> (Kỳ báo cáo năm)</a></s:elseif>
                <br>
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Chi nhánh đã chốt số liệu)</a></s:if>
                <s:elseif test="chotsl.equalsIgnoreCase('1')" ><a class="color_11">(Phòng giao dịch đã gửi dữ liệu)</a></s:elseif>
                <input type="hidden" id ="chotsl_temp" value="<s:property  value="chotsl" />">
            </div>
            <div id="divDonvitinh">
                Đơn vị tính: Khách hàng; Tài khoản
            </div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th class="STT1">STT</th>                           
                    <th class="STT4">Loại tài khoản</th>  
                    <th class="STT2">Tổng số khách hàng mở tài khoản</th>  
                    <th class="STT2">Số lượng tài khoản đang hoạt động</th>
                    <th class="STT2">Số lượng khách hàng thu thập sinh trắc học</th>
                    <th class="STT2">Số lượng tài khoản đã thu thập sinh trắc học tương ứng với cột (5)</th>      
                    <th class="STT2">Số lượng khách hàng thu thập sinh trắc học trong kỳ</th> 
                    <th class="STT2">Số lượng tài khoản đã thu thập sinh trắc học trong kỳ tương ứng với cột (7)</th> 
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
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0"><s:property  value="TT_HIENTHI"/>
                            <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                            <input type="hidden" value="<s:property  value="TT_HIENTHI" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                            <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
                            <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                            <input type="hidden" value="<s:property  value="CO_TONGHOP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/>
                            <input type="hidden" value="<s:property  value="NGUOI_NHAP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_NHAP"/>
                            <input type="hidden" value="<s:property  value="NAMBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NAMBC"/>
                            <input type="hidden" value="<s:property  value="MAPGD" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                            <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>

                        </td>
                        <td><s:property  value="TEN"/></td>
                        <td><input type="text" value="<s:property  value="D1" />" 
                                   <s:if test="THUTU.toString().equalsIgnoreCase('1') || THUTU.toString().equalsIgnoreCase('3') ||THUTU.toString().equalsIgnoreCase('5')">
                                       readonly="true"</s:if>
                                   id="D1_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="number"/></td>
                        <td><input type="text" value="<s:property  value="D2" />"
                                   <s:if test="THUTU.toString().equalsIgnoreCase('1') || THUTU.toString().equalsIgnoreCase('3') ||THUTU.toString().equalsIgnoreCase('5')">
                                       readonly="true"</s:if>
                                   id="D2_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="number"/></td>
                        <td><input type="text" value="<s:property  value="D3" />"
                                   <s:if test="THUTU.toString().equalsIgnoreCase('1') || THUTU.toString().equalsIgnoreCase('3') ||THUTU.toString().equalsIgnoreCase('5')">
                                       readonly="true"</s:if>
                                   id="D3_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="number"/></td>
                        <td><input type="text" value="<s:property  value="D4" />"
                                   <s:if test="THUTU.toString().equalsIgnoreCase('1') || THUTU.toString().equalsIgnoreCase('3') ||THUTU.toString().equalsIgnoreCase('5')">
                                       readonly="true"</s:if>
                                   id="D4_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="number"/></td>
                        <td><input type="text" value="<s:property  value="D5" />" 
                                   <s:if test="THUTU.toString().equalsIgnoreCase('1') || THUTU.toString().equalsIgnoreCase('3') ||THUTU.toString().equalsIgnoreCase('5')">
                                       readonly="true"</s:if>
                                   id="D5_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number"/></td>
                        <td><input type="text" value="<s:property  value="D6" />" 
                                   <s:if test="THUTU.toString().equalsIgnoreCase('1') || THUTU.toString().equalsIgnoreCase('3') ||THUTU.toString().equalsIgnoreCase('5')">
                                       readonly="true"</s:if>
                                   id="D6_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number"/></td>


                    </tr>
                </s:iterator>
            </table>
        </div>
        <div id="luu_thanhcong"></div>
    </body>
</html>
