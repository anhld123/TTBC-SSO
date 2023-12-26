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
    #tablems13a {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 130%;
    }
    #tablems13a th{
        background-color: #ddd;
        color: #0000FF;
    }

    #tablems13a th, #tablems13a td {
        border: 1px solid gray;
    }

    #tablems13a tr:nth-child(even){background-color: #f2f2f2;}

    #tablems13a tr:hover {background-color: #ddd;}

</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".STT1").css({"width": "40px"});
                $(".STT2").css({"width": "100px"});
                $(".STT3").css({"width": "150px"});
                $(".STT4").css({"width": "200px"});
                $(".STT5").css({"width": "80px"});

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
            function setCssStyle() {
    $(".cssDate").datepicker({
        dateFormat: 'dd/mm/yy',
        showOn: "button",
        buttonImage: "img/icon-ui_datepicker.png",
        buttonImageOnly: true,
        showButtonPanel: true,
        buttonText: "icono",
        changeMonth: true,
        changeYear: true,
        yearRange: "c-100:c+0",
        beforeShow: function (input, inst) {
            if ($(input).is(':disabled')) {
                return false; // Ngăn chặn datepicker hiển thị nếu input bị disabled
            }
        },
        // Thêm CSS cho ngày tháng
        onSelect: function(dateText, inst) {
            $(this).css({
                'font-size': '13px', // Cỡ chữ
                'color': 'red' // Màu chữ
            });
        }
    });
}
        </script>
    </head>
    <body>
        <div style="overflow:scroll; width: 95vw;">  
            <s:form id="id_sv_QT_MS13_2023" action="SAVE_QT_MS13_2023" theme="simple">  
                <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                    <input type="hidden" id="<s:property  value="sKey" />" 
                           name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
                </s:iterator>
                <div id="divTitle">
                    <br>
                    SAO KÊ CHI TIẾT NỢ KHOANH
                </div>
                <s:hidden name="khoa_bcqt"/>
                <div id="divDonvitinh">
                    Đơn vị tính: Đồng
                </div>

                <table border="1" class="editDelete" id="tablems13a" align="center">
                    <tr >      
                        <th rowspan="2" class="STT1">STT</th>                           
                        <th rowspan="2" class="STT2">Mã khách hàng</th>  
                        <th rowspan="2" class="STT2">Tên người vay</th>  
                        <th rowspan="2" class="STT3">Địa chỉ</th>  
                        <th rowspan="2" class="STT2">Mã món vay</th>    
                        <th rowspan="2" class="STT2">Chương trình vay vốn</th>                             
                        <th rowspan="2" class="STT5">Ngày vay</th>   
                        <th rowspan="2" class="STT5">Ngày đến hạn</th>   
                        <th rowspan="2" class="STT2">Ngày hạch toán khoanh nợ</th>  
                        <th rowspan="2" class="STT3">Số QĐ khoanh nợ</th>  
                        <th rowspan="2" class="STT5">Ngày được khoanh nợ</th>  
                        <th rowspan="2" class="STT2">Số tháng được khoanh</th>                             
                        <th rowspan="2" class="STT5"> Ngày hết hạn khoanh</th> 

                        <th colspan="2" class="STT2">Dư nợ được khoanh</th> 
                        <th rowspan="2" class="STT2">Nợ khoanh đã thu</th>      
                        <th colspan="4" class="STT4">Tiền lãi khoanh chưa thu</th> 
                        <th rowspan="2" class="STT1">Cập nhật</th>  

                    </tr>         
                    <tr >
                        <th class="STT2">Theo QĐ</th>
                        <th class="STT2">Số cuối kỳ</th>
                        <th class="STT2">Số cuối kỳ</th>
                        <th class="STT2">Lãi tồn âm</th>
                        <th class="STT2">Chuyển sang thu gốc</th>
                        <th class="STT2">HT điều chỉnh</th>
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
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(14)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(15)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(16)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(17)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(18)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(19)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(20)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(21)</th>

                        <s:iterator value="#attr.lstDulieuNtMs13a" var="modelView" status="rowstatus">
                        <tr>                              
                            <td style="background: #f2f2f2; text-align: center; width: 40px;"><s:property value="D1"/> 
                                <input type="hidden" value="<s:property  value="D1" />" id="D1_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D1"/>
                                <input type="hidden" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].posCode" value="<s:property  value="posCode"/>"/>   
                                <input type="hidden" name="lstDulieuNtMs13a[<s:property  value='%{#rowstatus.index}' />].reportDate" value="<s:property value='reportDate'/>">
                            </td> 
                            <td style="background: #f2f2f2; text-align: center; width: 80px;"><s:property value="D2"/> 
                                <input type="hidden" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D2"/>
                            </td>  
                            <td style="background: #f2f2f2; width: 150px;"><s:property value="D3"/> 
                                <input type="hidden" value="<s:property  value="D3" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D3"/>
                            </td>  
                            <td style="background: #f2f2f2; text-align: left; width: 200px;"><s:property value="D4"/> 
                                <input type="hidden" value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D4"  
                                       class="STT3" onfocus="this.select()" readonly="readonly"/>
                            </td>  
                            <td style="background: #f2f2f2; text-align: center; width: 100px;"><s:property value="D5"/> 
                                <input type="hidden" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D5"/>
                            </td>    
                            <td style="background: #f2f2f2; text-align: center; width: 80px;"><s:property value="D6"/> 
                                <input type="hidden" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D6"/>
                            </td>                             
                            <td style="background: #f2f2f2; text-align: center; width:80px;"><s:property value="D7"/> 
                                <input type="hidden" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D7"/>
                            </td>   
                            <td style="background: #f2f2f2; text-align: center; width:80px;"><s:property value="D8"/>
                                <input type="hidden" value="<s:property  value="D8" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D8"/>
                            </td> 
                            <s:if test="D9 == null || D9.trim().isEmpty()">
                                <td style="width: 120px;background: #ffffff">
                                    <input type="text" value="<s:property  value="D9" />" style="font-size: 13px; width: 75px" 
                                           title="<s:property  value="D3" /> - <s:property  value="D5" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                           class="cssDate" name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D9"/>   
                                </td>
                            </s:if>
                            <s:else> 
                                <td style="background: #f2f2f2; text-align: center; width:120px;"><s:property value="D9"/>
                                    <input type="hidden" value="<s:property  value="D9" />"  
                                           name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D9"/>
                                </td> 
                            </s:else>
                            <s:if test="D10 == null || D10.trim().isEmpty()">
                                <td style="width: 80px;background: #ffffff">
                                    <input type="text" value="<s:property  value="D10" />" style="font-size: 13px" id="D10_<s:property  value="%{#rowstatus.index}" />"
                                           title="<s:property  value="D3" /> - <s:property  value="D5" />"
                                           name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D10"/>
                                </td>
                            </s:if>
                            <s:else>  
                                <td style="background: #f2f2f2; text-align: center; width:80px;"><s:property value="D10"/>
                                    <input type="hidden" value="<s:property  value="D10" />" 
                                           name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D10"/>
                                </td>  

                            </s:else>
                            <td style="background: #ffffff; text-align: center; width:120px;">
                                <input type="text" value="<s:property  value="D11" />" class="cssDate" style="font-size: 13px;width: 75px"
                                       title="<s:property  value="D3" /> - <s:property  value="D5" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D11"/>
                            </td>  
                            <td style="background: #f2f2f2; text-align: center; width:80px;"><s:property value="D12"/>
                                <input type="hidden" value="<s:property  value="D12" />" id="D12_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D12"/>
                            </td>                             
                             <td style="background: #f2f2f2; text-align: center; width:120px;"><s:property value="D13"/>
                                    <input type="hidden" value="<s:property  value="D13" />"  id="D13_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D13"/>
                                </td>  
                              <td style="width: 80px;background: #f2f2f2">
                                <input type="text" value="<s:property  value="D14" />" style="background: #f2f2f2; color: #000;font-size: 13px"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D14" readonly="true" id="D14_<s:property  value="%{#rowstatus.index}" />"
                                       class="number STT2" onfocus="this.select()"/>
                            <td style="width: 80px;background: #f2f2f2">
                                <input type="text" value="<s:property  value="D15" />" style="background: #f2f2f2; color: #000;font-size: 13px"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D15" readonly="true"
                                       id="D15_<s:property  value="%{#rowstatus.index}" />"
                                       class="number STT2" onfocus="this.select()"/>
                            </td>   
                              <td style="width: 80px;background: #f2f2f2">
                                <input type="text" value="<s:property  value="D16" />" style="background: #f2f2f2; color: #000;font-size: 13px"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D16" readonly="true" id="D16_<s:property  value="%{#rowstatus.index}" />"
                                       class="number STT2" onfocus="this.select()"/>
                            </td>  
                            <td style="width: 80px;background: #ffffff">
                                <input type="text" value="<s:property  value="D17" />" style="font-size: 13px" title="<s:property  value="D3" /> - <s:property  value="D5" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D17" id="D17_<s:property  value="%{#rowstatus.index}" />"
                                       class="number STT2" onfocus="this.select()"/>
                            </td>
                            <td style="width: 80px;background: #f2f2f2">
                                <input type="text" value="<s:property  value="D18" />" style="background: #f2f2f2; color: #000;font-size: 13px"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D18" readonly="true" id="D18_<s:property  value="%{#rowstatus.index}" />"
                                       class="number STT2" onfocus="this.select()"/>
                            </td>  
                            <td style="width: 80px;background: #ffffff">
                                <input type="text" value="<s:property  value="D19" />" style="font-size: 13px" title="<s:property  value="D3" /> - <s:property  value="D5" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D19" id="D19_<s:property  value="%{#rowstatus.index}" />"
                                       class="number STT2" onfocus="this.select()"/>
                            </td>
                            <td style="width: 80px;background: #ffffff">
                                <input type="text" value="<s:property  value="D20" />" style="font-size: 13px" title="<s:property  value="D3" /> - <s:property  value="D5" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D20" id="D20_<s:property  value="%{#rowstatus.index}" />"
                                       class="number STT2" onfocus="this.select()"/>
                            </td>
                            <td><input type="checkbox" id ="idc11<s:property  value="%{#rowstatus.index}" />"  title="<s:property  value="D3" /> - <s:property  value="D5" />"
                                       class="checkboxdat STT1 D0" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D21"/>
                            </td>     
                        </tr>

                    </s:iterator>
                </table>
            </div>
            <sj:submit id="QT_MS13_2023_save" name="QT_MS13_2023_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
//            initTable();
        </script>
    </body>
</html>
