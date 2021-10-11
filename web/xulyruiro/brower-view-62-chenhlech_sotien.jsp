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
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $(".datepicker_month").datepicker({dateFormat: 'mm/yy'});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_STT").css({"width": "30px"});
                $(".TD_MAKH").css({"width": "70px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "130px"});
                $(".TD_SOKU").css({"width": "120px"});
                $(".TD_SOTIEN").css({"width": "90px"});
                $(".TD_CHITIEU").css({"width": "300px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>     

     
        <style>     

            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }       
            .cls-over{
                overflow-y: scroll;
                height: 70vh;
                overflow-x: scroll;
            }
            .editDelete{
                border: 1px solid #999;
            }

            .editDelete td,th{
                border: 1px solid #999;
            }
            .editDelete           input:readonly {
                background-color: red;
            }



        </style>
        <link href="css/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body style="font-family: ">
        <s:form id="id_sv_risk_chenhlech" action="SAVE_risk_chenhlech" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            </br>
            <div id="divTitle">
                DANH SÁCH MÓN VAY CHÊNH LỆCH SỐ ĐỀ NGHỊ VÀ SỐ HIỆN TẠI
                </br>
            </div>
            </br>
            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 99%; max-height:45vh">
                    <table class="editDelete cls-table" >
                        <tr height="50px">                               
                            <th rowspan="2" class="TD_TENKH">Tên đơn vị</th>  
                            <th rowspan="2" class="TD_TENKH">Mã món vay</th>  
                            <th rowspan="2" class="TD_TENKH">Tên khách hàng</th>
                            <th rowspan="2" class="TD_NGAY">Chương trình</th>
                            <th colspan="2" class="TD_SOTIEN">Đề nghị</th>                            
                            <th colspan="2" class="TD_SOTIEN">Hiện tại</th> 
                                                    
                        </tr>   
                         <tr>
                            <th  class="TD_NGAY">Gốc</th>
                            <th  class="TD_NGAY">Lãi</th> 
                            <th  class="TD_NGAY">Gốc</th>
                            <th  class="TD_NGAY">Lãi</th> 
                        </tr>
                       
                        <tr>
                            <td style="text-align: center">1</td>
                            <td style="text-align: center">2</td>
                            <td style="text-align: center">3</td>
                            <td style="text-align: center">4</td>
                            <td style="text-align: center">5</td>
                            <td style="text-align: center">6</td>
                            <td style="text-align: center">7</td>                              
                            <td style="text-align: center">8</td>   
                           
                            

                        </tr>
                        <s:iterator value="#attr.lstDulieuNt50" var="modelView" status="rowstatus">                             
                            <tr> 
                               

                                    <td align = "right" class="TD_TENKH" >
                                        <input type="text"   value="<s:property  value="TEN" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select();" 
                                               readonly="true"/>                                       
                                    </td>                                
                                    <td align = "right" class="TD_TENKH" >
                                        <input type="text"   value="<s:property  value="D1" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();" 
                                               readonly="readonly"/>
                                    </td>
                                    <td align = "right" class="TD_TENKH" >
                                        <input type="text"   value="<s:property  value="D3" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH" onfocus="this.select();" 
                                               readonly="true"/>
                                    </td>
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D4" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" onfocus="this.select();" 
                                               readonly="true"/>
                                    </td>
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D5" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();" 
                                               readonly="true"/>                                                                        
                                    </td>
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D6" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();" 
                                               readonly="true"/>                                                                        
                                    </td>

                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D11" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" onfocus="this.select();" 
                                               readonly="true"/>                                                                        
                                    </td>
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D12" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D12" title="<s:property  value="D7" />" class="TEN_KH D0" onfocus="this.select();" 
                                               readonly="true"/>                                                                        
                                    </td>


                                   
                            </tr>                                                                                                                                                                                   
                        </s:iterator>
                    </table>        
                </div>
            </div>

            <sj:submit id="risk_chenhlech_save" name="risk_chenhlech_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>


</html>
