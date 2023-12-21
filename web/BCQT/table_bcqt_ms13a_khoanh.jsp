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
        width: 110%;
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
        </script>
    </head>
    <body>
        <div style="overflow:scroll; width: 99vw;">  
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
                        <th rowspan="2"  class="STT2">Chương trình vay vốn</th>                             
                        <th rowspan="2"  class="STT5">Ngày vay</th>   
                        <th rowspan="2"  class="STT5">Ngày đến hạn</th>   
                        <th rowspan="2"  class="STT5">Ngày hạch toán khoanh nợ</th>  
                        <th rowspan="2"  class="STT3">Số QĐ khoanh nợ</th>  
                        <th rowspan="2"  class="STT5">Ngày được khoanh nợ</th>  
                        <th rowspan="2"  class="STT2">Số tháng được khoanh</th>                             
                        <th rowspan="2"  class="STT5"> Ngày hết hạn khoanh</th> 

                        <th colspan="2"  class="STT2">Dư nợ được khoanh</th> 
                        <th rowspan="2"  class="STT2">Nợ khoanh đã thu</th>      
                        <th colspan="4"  class="STT4">Tiền lãi khoanh chưa thu</th> 
                        <th rowspan="2"  class="STT1">Cập nhật</th>  

                    </tr>         
                    <tr >
                        <th  class="STT2">Theo QĐ</th>
                        <th  class="STT2">Số cuối kỳ</th>
                        <th  class="STT2">Số cuối kỳ</th>
                        <th  class="STT2">Lãi tồn âm</th>
                        <th  class="STT2">Chuyển sang thu gốc</th>
                        <th  class="STT2">HT điều chỉnh</th>
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
                            <td style="background: #ddd"><div class="STT1 D0"><s:property value="D1"/> </div>
                                <input type="hidden" value="<s:property  value="D1" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D1"/>
                                <input type="hidden" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].posCode" value="<s:property  value="posCode"/>"/>   
                                <input type="hidden" name="lstDulieuNtMs13a[<s:property  value='%{#rowstatus.index}' />].reportDate" value="<s:property value='reportDate'/>">
                            </td> 
                            <td style="background: #ddd"><div class="STT2 D0"><s:property value="D2"/> </div>
                                <input type="hidden" value="<s:property  value="D2" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D2"/>
                            </td>  
                            <td style="background: #ddd"><div style="margin: 3px" class="STT2"><s:property value="D3"/> </div>
                                <input type="hidden" value="<s:property  value="D3" />" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D3"/>
                            </td>  
                            <td style="background: #ddd"><div style="margin: 3px"  class="STT3"><s:property value="D4"/> </div>
                                <input type="hidden" value="<s:property  value="D4" />" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D4"  
                                       class="STT3" onfocus="this.select()" readonly="readonly"/>
                            </td>  
                            <td style="background: #ddd"><div class="STT3 D0"><s:property value="D5"/> </div>
                                <input type="hidden" value="<s:property  value="D5" />" 
                                        name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D5"/>
                            </td>    
                            <td style="background: #ddd"><div class="STT2 D0"><s:property value="D6"/> </div>
                                <input type="hidden" value="<s:property  value="D6" />" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D6"/>
                            </td>                             
                            <td><input type="text" value="<s:property  value="D7" />" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D7"  
                                       class="STT5 D0 datepicker" placeholder="dd/MM/yyyy" onfocus="this.select()"/>
                            </td>   
                            <td>
                                <input type="text" value="<s:property  value="D8" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D8"  
                                       class="STT5 D0 datepicker" placeholder="dd/MM/yyyy" onfocus="this.select()"/>
                            </td>   
                            <td> <input type="text" value="<s:property  value="D9" />" 
                                        name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D9" 
                                        class="STT5 D0 datepicker" placeholder="dd/MM/yyyy" onfocus="this.select()"/>
                            </td>  
                            <td> <input type="text" value="<s:property  value="D10" />" 
                                        name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D10" 
                                        class="STT2" onfocus="this.select()"/>
                            </td>  
                            <td>  <input type="text" value="<s:property  value="D11" />" 
                                         name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D11"  
                                         class="STT5 D0 datepicker" placeholder="dd/MM/yyyy" onfocus="this.select()"/>
                            </td>  
                            <td> <input type="text" value="<s:property  value="D12" />" 
                                        name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D12" 
                                        class="D0 STT2" onfocus="this.select()"/>
                            </td>                             
                            <td> <input type="text" value="<s:property  value="D13" />" 
                                        name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D13"  
                                        class="STT5 D0 datepicker" placeholder="dd/MM/yyyy" onfocus="this.select()"/>
                            </td> 
                            <td><input type="text" value="<s:property  value="D14" />" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D14"  
                                       class="number STT2" onfocus="this.select()"/>
                            </td> 
                            <td><input type="text" value="<s:property  value="D15" />"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D15" 
                                       class="number STT2" onfocus="this.select()"/>
                            </td>      
                            <td><input type="text" value="<s:property  value="D16" />" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D16"
                                       class="number STT2" onfocus="this.select()"/>
                            </td> 
                            <td><input type="text" value="<s:property  value="D17" />" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D17" 
                                       class="number STT2" onfocus="this.select()"/>
                            </td> 
                            <td><input type="text" value="<s:property  value="D18" />" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D18" 
                                       class="number STT2" onfocus="this.select()"/>
                            </td> 
                            <td><input type="text" value="<s:property  value="D19" />" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D19" 
                                       class="number STT2" onfocus="this.select()"/>
                            </td> 
                            <td><input type="text" value="<s:property  value="D20" />" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D20"
                                       class="number STT2" onfocus="this.select()"/>
                            </td> 
                            <td><input type="checkbox" id ="idc11<s:property  value="%{#rowstatus.index}" />" 
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
