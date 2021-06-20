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
        <style>
        a {
            color: #0000FF;
        }
        .BOLD
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }
        </style>
        </style>
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
                $('.number').number(true, 2);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "200px"});
                $(".TD_CAPKT").css({"width": "100px"});
                $(".TD_SOTIEN").css({"width": "55px"});                                
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
                    var arrCot = [".D2",".D3",".D4",".D5",".D6",".D7",".D8",".D9",".D10",".D11",".D12",".D13",".D14",".D15"]; //Luu cac cot cua du lieu can tinh toan                    
    //                  Tinh toan cho 7 dong
                    for(var i=0; i<30; i++){                        
                        //8=2+4-6
                        $(".D15").eq(i).val(parseFloat($(".D2").eq(i).val()) + parseFloat($(".D3").eq(i).val()) +
                                            parseFloat($(".D4").eq(i).val()) + parseFloat($(".D5").eq(i).val()) +
                                            parseFloat($(".D6").eq(i).val()) + parseFloat($(".D7").eq(i).val()) +   
                                            parseFloat($(".D8").eq(i).val()) + parseFloat($(".D9").eq(i).val()) +
                                            parseFloat($(".D10").eq(i).val()) + parseFloat($(".D11").eq(i).val()) +
                                            parseFloat($(".D12").eq(i).val()) + parseFloat($(".D13").eq(i).val()) +  
                                            parseFloat($(".D14").eq(i).val())        
                        );

                    }
    //                              
                }
                </script>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">                      
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                THEO DÕI MÃ TRÁI PHIẾU VÀ LÃI SUẤT TRÚNG THẦU
            </div>
                &nbsp;
            <s:hidden name="khoa_nhaptaycn"/>
<!--            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>-->
            <table border="1" class="editDelete" id="tablems01" style="width: 80%" align="center">
                <tr height="35">
                    <th class="TD_SOTIEN">Mã KH</th>
                    <th class="TD_CHITIEU">Tên KH</th>
                    <th class="TD_THUTU">Mã SP</th>
                    <th class="TD_THUTU">Số dư</th>
                    <th class="TD_THUTU">Lãi suất</th>
                    <th class="TD_THUTU">Ngày gửi</th>
                    <th class="TD_THUTU">Ngày đến hạn</th>                    
                    <th class="TD_THUTU">Kỳ hạn</th>                                            
                    <th class="TD_CAPKT">Mã trái phiếu</th>   
                    <th class="TD_THUTU">LS trúng thầu</th>                      
                </tr>
                            
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                                                 
                        
                        <td  align="center" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D1" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                        </td>                                               
                        <td  align="right" class="TD_CHITIEU">    
                            <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                            <input type="hidden" value="<s:property  value="D9" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class=" <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" />
                        </td> 
                        <td  align="right" class="TD_THUTU">    
                            <input type="text" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                        </td> 
                        <td  align="right" class="TD_CAPKT">    
                            <input type="text" value="<s:property  value="D4" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                        </td> 
                        <td  align="right" class="TD_THUTU">    
                            <input type="text" value="<s:property  value="D5" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                        </td> 
                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D6" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                        </td> 
                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D7" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                        </td> 
                        <td  align="right" class="TD_THUTU">    
                            <input type="text" value="<s:property  value="D8" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                        </td> 
                        
                        <td align = "right" class="TD_CAPKT">
                            <input type="text" value="<s:property  value="D10" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" />
                        </td>    
                        <td  align="right" class="TD_THUTU">    
                            <input type="text" value="<s:property  value="D11" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" />                                  
                        </td>
                                                                      
                    </tr>                    
                    
                </s:iterator>
            </table>
            <p></p>          
            
            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
                
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
