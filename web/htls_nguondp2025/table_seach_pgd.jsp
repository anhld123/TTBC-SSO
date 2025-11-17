<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/css2025.css" />
<link rel="stylesheet" type="text/css"  href="htls_nguondp2025/css.css" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script> 
    </head>
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
            $('.number2').number(true, 1);
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
            $(".D99").css({"text-align": "center", "color": "#000", "font-style": "italic", "font-size": "xx-small"});
        });
    </script>
    <body>
        <div style="overflow:scroll; width: 98vw;">             
            <div id="divTitle" style="text-align: center">
                DỮ LIỆU PHÒNG GIAO DỊCH THEO DANH MỤC XÃ
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(TW đã khóa nhập dữ liệu)</a></s:if>
                <s:elseif test="chotCic.equalsIgnoreCase('2')" ><a class="color_11">(CN đã gửi dữ liệu)</a></s:elseif>
                <s:elseif test="chotCic.equalsIgnoreCase('1')" ><a class="color_11">(PGD đã chốt dữ liệu)</a></s:elseif>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
                <input type="hidden" value="<s:property value="chotCic"/>" name="chotcic" id="chotcic"/> 
            </div>
            <div style="height:10px"></div>   
            <table border="1" class="editDelete" id="subTable" align="center">      
                <tr>
                    <th rowspan="2">STT</th>
                    <th rowspan="2">Mã xã</th>
                    <th rowspan="2">Tên xã</th>
                    <th rowspan="2">Tổng số KH</th>
                    <th rowspan="2">Tổng số món vay</th>
                    <th rowspan="2">Tổng dư nợ</th>
                    <th colspan="3">Dư nợ</th>
                    <th colspan="2">Đơn vị nhập lãi giảm</th>
                </tr>  
                <tr>
                    <th>Trong hạn</th>
                    <th>Quá hạn</th>
                    <th>Khoanh</th>
                    <th style="color: red">Số món chưa tích lãi giảm</th>
                    <th style="color: blue">Số món dã tích lãi giảm</th>
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
                </tr>

                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr> 
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                        <td class="D0"><s:property  value="D1" /></td>
                        <td><s:property value="D2"/></td>
                        <td class="number"><s:property value="D3"/></td>
                        <td class="number"><s:property value="D4"/></td>
                        <td class="number"><s:property value="D10"/></td>
                        <td class="number"><s:property value="D5"/></td>
                        <td class="number"><s:property value="D6"/></td>
                        <td class="number"><s:property value="D7"/></td>
                        <td class="number"><s:property value="D8"/></td>
                        <td class="number"><s:property value="D9"/></td>


                    </s:iterator>
            </table>
        </div>      
        <div id="luu_thanhcong"></div>
        <script>

        </script>
    </body>
</html>
