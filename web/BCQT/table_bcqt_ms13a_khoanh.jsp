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
                $(".TD_THUTU").css({"width": "10px"});
                 $(".TD_CHECK").css({"width": "20px"});
                $(".TD_TEN").css({"width": "120px"});
                $(".TD_MAKH").css({"width": "63px"});
                $(".TD_SOTIEN").css({"width": "70px"});
                $(".TD_LAI").css({"width": "60px"});
                $(".TD_NGAY").css({"width": "58px"});
                $(".TD_THANG").css({"width": "35px"});
                $(".TD_SOKU").css({"width": "93px"});

                $(".TD_K").css({"width": "10px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});

//                $(".TD_TEN").css({"width": "200px"});
//                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        <script>

        </script>
    </head>
    <body>
        <div style="overflow:scroll; width: 110%;">  
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
                        <th rowspan="2" class="TD_THUTU">STT</th>                           
                        <th rowspan="2" class="TD_MAKH">Mã khách hàng</th>  
                        <th rowspan="2" class="TD_TEN">Tên người vay</th>  
                        <th rowspan="2" class="TD_TEN">Địa chỉ</th>  
                        <th rowspan="2" class="TD_SOKU">Mã món vay</th>    
                        <th rowspan="2"  class="TD_K">Chương trình vay vốn</th>                             
                        <th rowspan="2"  class="TD_NGAY">Ngày vay</th>   
                        <th rowspan="2"  class="TD_NGAY">Ngày đến hạn</th>   
                        <th rowspan="2"  class="TD_NGAY">Ngày hạch toán khoanh nợ</th>  
                        <th rowspan="2"  class="TD_K">Số QĐ khoanh nợ</th>  
                        <th rowspan="2"  class="TD_NGAY">Ngày được khoanh nợ</th>  
                        <th rowspan="2"  class="TD_THANG">Số tháng được khoanh</th>                             
                        <th rowspan="2"  class="TD_NGAY"> Ngày hết hạn khoanh</th> 

                        <th colspan="2"  class="TD_K">Dư nợ được khoanh</th> 
                        <th rowspan="2"  class="TD_K">Nợ khoanh đã thu</th>      
                        <th colspan="4"  class="TD_SOTIEN">Tiền lãi khoanh chưa thu</th> 
                        <th rowspan="2"  class="TD_NGAY">Cập nhật</th>  

                    </tr>         
                    <tr >
                        <th  class="TD_SOTIEN">Theo QĐ</th>
                        <th  class="TD_SOTIEN">Số cuối kỳ</th>
                        <th  class="TD_LAI">Số cuối kỳ</th>
                        <th  class="TD_LAI">Lãi tồn âm</th>
                        <th  class="TD_LAI">Chuyển sang thu gốc</th>
                        <th  class="TD_LAI">HT điều chỉnh</th>
                    </tr>
                    <tr style="font-style: italic;">
                        <td style="text-align: center">(1)</td>
                        <td style="text-align: center">(2)</td>
                        <td style="text-align: center">(3)</td>
                        <td style="text-align: center">(4)</td>
                        <td style="text-align: center">(5)</td>
                        <td style="text-align: center">(6)</td>
                        <td style="text-align: center">(7)</td>
                        <td style="text-align: center">(8)</td>
                        <td style="text-align: center">(9)</td>
                        <td style="text-align: center">(10)</td>
                        <td style="text-align: center">(11)</td>
                        <td style="text-align: center">(12)</td>
                        <td style="text-align: center">(13)</td>
                        <td style="text-align: center">(14)</td>
                        <td style="text-align: center">(15)</td>
                        <td style="text-align: center">(16)</td>
                        <td style="text-align: center">(17)</td>
                        <td style="text-align: center">(18)</td>
                        <td style="text-align: center">(19)</td>
                        <td style="text-align: center">(20)</td>
                        <td style="text-align: center">(21)</td>




                        <s:iterator value="#attr.lstDulieuNtMs13a" var="modelView" status="rowstatus">

                        <tr height="22">                              
             
                            <th class="TD_THUTU">
                                <input type="text" value="<s:property  value="D1" />" style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D1"  class="D0 TEN_KH" onfocus="this.select()" readonly="readonly"/>
                                <input type="hidden" 
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].posCode" value="<s:property  value="posCode"/>"/>   
                                <input type="hidden" name="lstDulieuNtMs13a[<s:property  value='%{#idxRows.index}' />].reportDate" value="<s:property value='reportDate'/>">
                            </th> 
                            <th class="TD_MAKH">
                                <input type="text" value="<s:property  value="D2" />" style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D2"  class="TEN_KH" onfocus="this.select()" readonly="readonly"/>
                            </th>  
                            <th class="TD_TEN">
                                <input type="text" value="<s:property  value="D3" />" style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D3"  class="TEN_KH" onfocus="this.select()" readonly="readonly"  /></th>  
                            <th class="TD_TEN">
                                <input type="text" value="<s:property  value="D4" />"  style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D4"  class="TEN_KH" onfocus="this.select()" readonly="readonly"/></th>  
                            <th class="TD_SOKU">
                                <input type="text" value="<s:property  value="D5" />"  style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D5"  class="TEN_KH" onfocus="this.select()" readonly="readonly"/></th>    
                            <th  class="TD_K">
                                <input type="text" value="<s:property  value="D6" />" style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D6"  class="TEN_KH " onfocus="this.select()" readonly="readonly"/></th>                             
                            <th  class="TD_NGAY">
                                <input type="text" value="<s:property  value="D7" />" style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D7"  class="TEN_KH datepicker" placeholder="dd/MM/yyyy" onfocus="this.select()"/></th>   
                            <th  class="TD_NGAY">
                                <input type="text" value="<s:property  value="D8" />" style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D8"  class="TEN_KH datepicker" placeholder="dd/MM/yyyy" onfocus="this.select()"/></th>   
                            <th  class="TD_NGAY">
                                <input type="text" value="<s:property  value="D9" />" style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D9"  class="TEN_KH datepicker" placeholder="dd/MM/yyyy" onfocus="this.select()"/></th>  
                            <th  class="TD_NGAY">
                                <input type="text" value="<s:property  value="D10" />" style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D10"  class="TEN_KH" onfocus="this.select()"/></th>  
                            <th  class="TD_NGAY">
                                <input type="text" value="<s:property  value="D11" />" style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D11"  class="TEN_KH datepicker" placeholder="dd/MM/yyyy" onfocus="this.select()"/></th>  
                            <th  class="TD_THANG">
                                <input type="text" value="<s:property  value="D12" />" style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D12"  class="D0 TEN_KH" onfocus="this.select()"/></th>                             
                            <th  class="TD_NGAY">
                                <input type="text" value="<s:property  value="D13" />" style="font-size: 100%"
                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D13"  class="TEN_KH datepicker" placeholder="dd/MM/yyyy" onfocus="this.select()"/></th> 

                            <th  class="TD_SOTIEN"><input type="text" value="<s:property  value="D14" />" style="font-size: 100%"
                                                          name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D14"  class="number TEN_KH" onfocus="this.select()"/></th> 
                            <th  class="TD_SOTIEN"><input type="text" value="<s:property  value="D15" />" style="font-size: 100%"
                                                          name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D15"  class="number TEN_KH" onfocus="this.select()"/></th>      
                            <th  class="TD_SOTIEN"><input type="text" value="<s:property  value="D16" />" style="font-size: 100%"
                                                          name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D16"  class="number TEN_KH" onfocus="this.select()"/></th> 
                            <th  class="TD_LAI"><input type="text" value="<s:property  value="D17" />" style="font-size: 100%"
                                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D17"  class="number TEN_KH" onfocus="this.select()"/></th> 
                            <th  class="TD_LAI"><input type="text" value="<s:property  value="D18" />" style="font-size: 100%"
                                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D18"  class="number TEN_KH" onfocus="this.select()"/></th> 
                            <th  class="TD_LAI"><input type="text" value="<s:property  value="D19" />" style="font-size: 100%"
                                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D19"  class="number TEN_KH" onfocus="this.select()"/></th> 
                            <th  class="TD_LAI">
                                <input type="text" value="<s:property  value="D20" />" style="font-size: 100%"
                                                       name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D20"  class="number TEN_KH" onfocus="this.select()"/></th> 
                           
                                
                            <td  align="center" class="TD_CHECK">    
                                        <input type="checkbox" id ="idc11<s:property  value="%{#rowstatus.index}" />"  class="checkboxdat TEN_KH D0" 
                                               name="lstDulieuNtMs13a[<s:property  value="%{#rowstatus.index}" />].D21"                                          
                                               />
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
