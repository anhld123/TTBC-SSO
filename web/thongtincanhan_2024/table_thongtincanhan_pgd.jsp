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

        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 98%;height: 400px;">    

            <div id="divTitle">
                DANH SÁCH KHÁCH HÀNG TÀI KHOẢN THANH TOÁN CẦN CẬP NHẬT THÔNG TIN CÁ NHÂN<br> 
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Đơn vị đã gửi dữ liệu)</a></s:if>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
            </div>
            <div style="height: 10px"></div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th class="STT1" rowspan="2">STT</th>                           
                    <th class="STT2" rowspan="2">Mã khách hàng</th>  
                    <th class="STT3" rowspan="2">Số tài khoản</th>  
                    <th class="STT2" rowspan="2">Sản phẩm</th>
                    <th class="STT2" rowspan="2">Loại tài khoản</th>
                    <th class="STT2" rowspan="2">Đối tượng</th>
                    <th class="STT3" rowspan="2">Mã POS quản lý tài khoản</th>
                    <th class="STT3" rowspan="2">Số điện thoại</th>
                    <th class="STT4" colspan="4">DỮ LIỆU GTTT TRÊN INTELLECT</th> 
                    <th class="STT4" colspan="4">DỮ LIỆU GTTT TRÊN EKYC</th>
                    <th class="STT2" rowspan="2">Trạng thái rà soát</th>  
                </tr>
                <tr>
                    <th class="STT3">Tên khách hàng</th>      
                    <th class="STT3">Số GTTT</th> 
                    <th class="STT3">Ngày cấp</th> 
                    <th class="STT3">Ngày hết hạn</th> 
                    <th class="STT3">Tên khách hàng</th>      
                    <th class="STT3">Số GTTT</th> 
                    <th class="STT3">Ngày cấp</th> 
                    <th class="STT3">Ngày hết hạn</th>
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
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(14)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(15)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(16)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(17)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" />
                            <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                            <input type="hidden" value="<s:property value="%{#rowstatus.index + 1}" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                            <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
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
                            <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                            <input type="hidden" value="<s:property  value="D13" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13"/>
                            <input type="hidden" value="<s:property  value="D14" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14"/>
                            <input type="hidden" value="<s:property  value="D15" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/>
                            <input type="hidden" value="<s:property  value="D16" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16"/>
                            <input type="hidden" value="<s:property  value="D17" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17"/>
                            <input type="hidden" value="<s:property  value="D18" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18"/>
                            <input type="hidden" value="<s:property  value="D19" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19"/>
                            <input type="hidden" value="<s:property  value="D20" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20"/>
                            <input type="hidden" value="<s:property  value="D21" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21"/>
                            <input type="hidden" value="<s:property  value="D22" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22"/>
                            <input type="hidden" value="<s:property  value="D23" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23"/>
                        </td>
                        <td class="D0"><s:property  value="D1" /></td>
                        <td class="D0"><s:property  value="D4" /></td>
                        <td class="D0"><s:property  value="D5" /></td>
                        <td></td>
                        <td></td>
                        <td class="D0"><s:property  value="D7" /></td>
                        <td class="D0"><s:property  value="D3" /></td>
                        <td class="D0"><s:property  value="D16" /></td>
                        <td class="D0"><s:property  value="D17" /></td>
                        <td class="D0"><s:property  value="D18" /></td>
                        <td class="D0"><s:property  value="D19" /></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td class="D0">
                            <select style="border: hidden" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D11" id="D11_<s:property  value='%{#rowstatus.index}' />" > 
                                <option value="1" <s:if test="D11.equalsIgnoreCase('1')"> selected </s:if>>Chưa rà soát</option>
                                <option value="2" <s:if test="D11.equalsIgnoreCase('2')"> selected </s:if>>Đã rà soát</option>                        
                                <option value="3" <s:if test="D11.equalsIgnoreCase('3')"> selected </s:if>>Đã cập nhật trên CoreBanking</option>
                                </select>
                            </td>

                    </s:iterator>

            </table>
        </div>
        <div id="luu_thanhcong"></div>
        <script>

        </script>
    </body>
</html>
