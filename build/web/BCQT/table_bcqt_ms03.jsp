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
        <style>
            .BOLD
            {
                font-weight: bolder;
                background: #d59392;
            }
        </style>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TD_SOTO").css({"width": "25px"});
                $(".TD_THANHTIEN").css({"width": "60px"});
                $(".TD_LOAITIEN").css({"width": "50px"});
                $(".TEN_KH").css({"width": "100%"});
//                $(".BOLD").css();
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        <script>
            function autoEvaluate(){
//                alert('vao doClick');
                var arrCot = [".D2",".D3",".D4",".D5",
                    ".D6",".D7",".D8",".D9",
                    ".D10",".D11",".D12",".D13",".D14",".D15",".D16",".D17",".TEN"]; //Luu cac cot cua du lieu can tinh toan
                
//                  Tinh toan cho 7 dong
                for(var i=0; i<12; i++){
                    //8=2+4-6
                    
                    
                    $(".D3").eq(i).val(parseFloat($(".D2").eq(i).val())*parseFloat($(".TEN").eq(i).val()) );
                    $(".D5").eq(i).val(parseFloat($(".D4").eq(i).val())*parseFloat($(".TEN").eq(i).val()) );
                    $(".D7").eq(i).val(parseFloat($(".D6").eq(i).val())*parseFloat($(".TEN").eq(i).val()) );
                    
                    
                    $(".D8").eq(i).val(parseFloat($(".D2").eq(i).val()) + parseFloat($(".D4").eq(i).val()) -
                            parseFloat($(".D6").eq(i).val()) );
                    //9=3+5-7
                    $(".D9").eq(i).val(parseFloat($(".D3").eq(i).val()) + parseFloat($(".D5").eq(i).val()) -
                            parseFloat($(".D7").eq(i).val()));
                    
                    $(".D11").eq(i).val(parseFloat($(".D10").eq(i).val())*parseFloat($(".TEN").eq(i).val()) );
                    $(".D13").eq(i).val(parseFloat($(".D12").eq(i).val())*parseFloat($(".TEN").eq(i).val()) );
                    $(".D15").eq(i).val(parseFloat($(".D14").eq(i).val())*parseFloat($(".TEN").eq(i).val()) );
                    
                    //16=10+12-14
                    $(".D16").eq(i).val(parseFloat($(".D10").eq(i).val()) + parseFloat($(".D12").eq(i).val()) -
                            parseFloat($(".D14").eq(i).val()));
                    //17=11+13-15
                    $(".D17").eq(i).val(parseFloat($(".D11").eq(i).val()) + parseFloat($(".D13").eq(i).val()) -
                            parseFloat($(".D15").eq(i).val()));
                }
//                
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(12).val(parseFloat($(arrCot[i]).eq(0).val()) + parseFloat($(arrCot[i]).eq(1).val()) + 
                            parseFloat($(arrCot[i]).eq(2).val()) + parseFloat($(arrCot[i]).eq(3).val()) + 
                             parseFloat($(arrCot[i]).eq(4).val()) + 
                            parseFloat($(arrCot[i]).eq(5).val()) + parseFloat($(arrCot[i]).eq(6).val()) + 
                            parseFloat($(arrCot[i]).eq(7).val()) + parseFloat($(arrCot[i]).eq(8).val()) + 
                            parseFloat($(arrCot[i]).eq(9).val()) + parseFloat($(arrCot[i]).eq(10).val()) + 
                            parseFloat($(arrCot[i]).eq(11).val()) );
                }
            }
                </script>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_bcqt}" action="SAVE_%{khoa_bcqt}" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                BÁO CÁO THIẾU MẤT QUỸ (TIỀN VNĐ)
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablems03" align="center">
                <tr>
                    <th rowspan="3" style="width: 50px;" class="TD_LOAITIEN">Loại tiền</th>
                    <th colspan="8">Tiền nghi giả - Tiền giả</th>
                    <th colspan="8">Tiền bị phá hoại</th>                    
                </tr>
                <tr>                                  
                    <th colspan="2" style="width: 20px;" class="TD_TEN_KH">Dư đầu kỳ</th>
                    <th colspan="2" style="width: 20px;" class="TD_TEN_KH">Thu trong kỳ</th>
                    <th colspan="2" style="width: 20px;" class="TD_TEN_KH">Nộp NHNN</th>
                    <th colspan="2" style="width: 40px;" class="TD_TEN_KH">Dư cuối kỳ</th>
                    <th colspan="2" style="width: 30px;" class="TD_TEN_KH">Dư đầu kỳ</th>
                    <th colspan="2" style="width: 40px;" class="TD_TEN_KH">Thu trong kỳ</th>
                    <th colspan="2" style="width: 40px;" class="TD_TEN_KH">Nộp NHNN</th>
                    <th colspan="2" style="width: 40px;" class="TD_TEN_KH">Dư cuối kỳ</th>
                </tr>
                <tr>                                  
                    <th  style="width: 20px;" class="TD_SOTO">Số tờ</th>
                    <th  style="width: 20px;" class="TD_THANHTIEN">Thành tiền</th>
                    <th  style="width: 20px;" class="TD_SOTO">Số tờ</th>
                    <th  style="width: 40px;" class="TD_THANHTIEN">Thành tiền</th>
                    <th  style="width: 30px;" class="TD_SOTO">Số tờ</th>
                    <th  style="width: 40px;" class="TD_THANHTIEN">Thành tiền</th>
                    <th  style="width: 40px;" class="TD_SOTO">Số tờ</th>
                    <th  style="width: 40px;" class="TD_THANHTIEN">Thành tiền</th>
                    <th  style="width: 20px;" class="TD_SOTO">Số tờ</th>
                    <th  style="width: 20px;" class="TD_THANHTIEN">Thành tiền</th>
                    <th  style="width: 20px;" class="TD_SOTO">Số tờ</th>
                    <th  style="width: 40px;" class="TD_THANHTIEN">Thành tiền</th>
                    <th  style="width: 30px;" class="TD_SOTO">Số tờ</th>
                    <th  style="width: 40px;" class="TD_THANHTIEN">Thành tiền</th>
                    <th  style="width: 40px;" class="TD_SOTO">Số tờ</th>
                    <th  style="width: 40px;" class="TD_THANHTIEN">Thành tiền</th>
                </tr>
                <tr>         
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_LOAITIEN">1</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_SOTO">2</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_THANHTIEN">3</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_SOTO">4</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THANHTIEN">5</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_SOTO">6</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THANHTIEN">7</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_SOTO">8=2+4-6</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THANHTIEN">9=3+5-7</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_SOTO">10</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THANHTIEN">11</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_SOTO">12</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THANHTIEN">13</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_SOTO">14</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THANHTIEN">15</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_SOTO">16=10+12-14</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THANHTIEN">17=11+13-15</th>
                    <!--<th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH"></th>-->
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')"> 
                          <tr height="22" class="<s:property  value="FONTFORMAT" />">   
                        <td  align="right" class="TD_LOAITIEN">
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" onfocus="this.select()"    readonly="readonly" />                              
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                             <input type="hidden" value="<s:property  value="THUTU" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>
                             <input type="hidden" value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN"/>
                             <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" />
                             <input type="hidden" value="<s:property  value="FONTFORMAT" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].FONTFORMAT" />
                        </td>

                        <td align = "right"  class= "TD_SOTO" >
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D6" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D7" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D8" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D9" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D10" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D11" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D12" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D12 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D13" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D14" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D15" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D16" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D16 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D17" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="D17 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>

                    </tr>
                    </s:if>   
                    
                    
                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">   
                         <tr height="22" class="<s:property  value="FONTFORMAT" />">   
                        <td  align="right" class="TD_LOAITIEN">
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" onfocus="this.select()"   readonly="readonly"  />                              
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                             <input type="hidden" value="<s:property  value="THUTU" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>
                             <input type="hidden" value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN"/>
                             <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" />
                             <input type="hidden" value="<s:property  value="FONTFORMAT" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].FONTFORMAT" />
                        </td>

                        <td align = "right"  class= "TD_SOTO" >
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D6" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D7" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D8" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D9" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D10" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D11" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D12" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D12 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D13" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D14" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D15" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_SOTO">
                            <input type="text" value="<s:property  value="D16" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D16 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_THANHTIEN">
                            <input type="text" value="<s:property  value="D17" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="D17 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly="readonly"/>
                        </td>

                    </tr>
                    </s:if>    
                    
                </s:iterator>
            </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
