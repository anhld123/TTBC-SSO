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
        width: 80%;
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
                $(".STT2").css({"width": "100px"});
                $(".STT3").css({"width": "250px"});
                $(".STT4").css({"width": "110px"});
                $(".STT5").css({"width": "150px"});
                $(".STT6").css({"width": "65px"});
                $(".STT7").css({"width": "80px"});
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
                THUYẾT MINH CHI TIẾT CÁC KHOẢN PHẢI THU (TỪ 365 NGÀY TRỞ LÊN)<br>
                <s:if test="chotsl.equalsIgnoreCase('1')" ><a class="color_11">(Đơn vị đã gửi dữ liệu)</a></s:if>
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Đã chốt dữ liệu lên TW)</a></s:if>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
            </div>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng.
            </div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th class="STT1">STT</th>                           
                    <th>DANH MỤC CÁC KHOẢN PHẢI THU TỪ 365 NGÀY TRỞ LÊN</th>  
                    <th class="STT2">TÀI KHOẢN HẠCH TOÁN (GL)</th>  
                    <th class="STT6">SỐ TIỀN</th>  
                    <th style="width: 30%">NGUYÊN NHÂN, BIỆN PHÁP XỬ LÝ</th>
                </tr>
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                    <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                    <input type="hidden" value="<s:property value="%{#rowstatus.index + 1}" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                    <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
                    <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                    <input type="hidden" value="<s:property  value="CO_TONGHOP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/>
                    <input type="hidden" value="<s:property  value="NGUOI_NHAP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_NHAP"/>
                    <input type="hidden" value="<s:property  value="NAMBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NAMBC"/>
                    <input type="hidden" value="<s:property  value="MAPGD" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                    <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>
                    <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>
                    <input type="hidden" value="<s:property  value="D10" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10"/>
                    <input type="hidden" value="<s:property  value="D2" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" id="D2_<s:property  value='%{#rowstatus.index}' />"/>
                    <input type="hidden" value="<s:property  value="KIEUIN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KIEUIN"/>
                    <s:if test="D10.equalsIgnoreCase('1')">
                        <td class="D0" style="font-weight: bold;background: #ddd"><s:property  value="D1" /></td>
                        <td style="font-weight: bold;background: #ddd"><s:property  value="TEN" /></td>
                    </s:if>
                    <s:elseif test="D10.equalsIgnoreCase('3')">
                        <td class="D0"><s:property  value="D1" /></td>
                        <td><s:property  value="TEN" /></td>
                    </s:elseif>
                    <s:elseif test="D10.equalsIgnoreCase('4')">
                        <td class="D0" style="font-style: italic"><s:property  value="D1" /></td>
                        <td style="font-style: italic"><s:property  value="TEN" /></td>
                    </s:elseif>
                    <s:if test="!D10.equalsIgnoreCase('1')">     
                        <td> <input type="text" value="<s:property  value="D3" />" id="D3_<s:property  value='%{#rowstatus.index}' />" 
                                    name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" />
                        </td> 
                        <td> <input type="text" value="<s:property  value="D4  != null ? D4 : 0" />" id="D4_<s:property  value='%{#rowstatus.index}' />" 
                                    name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="number" />
                        </td> 
                        <td class="D0"><textarea style="width: 98%" placeholder="Nhập tối đa 1000 ký tự" id="D11_<s:property  value='%{#rowstatus.index}' />" 
                                                 name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D11" maxlength="1000"><s:property value='D11'/></textarea>
                        </td>
                    </s:if>
                    <s:else><td style="background: #ddd"></td><td style="background: #ddd"></td><td style="background: #ddd"></td></s:else>
                    </s:iterator>

            </table>
        </div>
        <div id="luu_thanhcong"></div>
        <script>


        </script>
    </body>
</html>
