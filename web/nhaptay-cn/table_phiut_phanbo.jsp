<%-- 
    Document   : exp_excel
    Created on : Jul 14, 2014, 4:17:12 PM
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
            .underline {
                text-decoration: underline;
            }
            
            h3 {
                text-align: center;
            }
            
            #divTitlePhts{
                color: blue; 
                font-weight: bolder; 
                font-size:large;
                text-align: center;
            }
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
                $('.number').number(true, 3);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_CHECKBOX").css({"width": "20px"});
                $(".TD_MACHUNG").css({"width": "50px"});
                $(".TD_TENCHUNG").css({"width": "100px"});
                $(".TD_SOTK").css({"width": "150px"});
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
        <s:if test="!Grade.equalsIgnoreCase('3')">
            <div style="width:100%;height:200px;overflow-y: scroll;">
        </s:if>
        
        <%--<s:form id="main_phiut_result" action="main_report_phiut_result" theme="simple">--%> 
        <s:form id="id_SAVE_PHIUT_001" action="SAVE_PHIUT_001" theme="simple">   
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:hidden name="khoa_nhaptaycn"/> 
            <s:if test="!Grade.equalsIgnoreCase('3')">
            <div  id="divTitlePhts">
                Chi tiết mức phí và tỷ lệ phân bổ
            </div>            
            <table border="1" class="editDelete" style="width: 80%; height: 30%" id="tablepl01" align="center">
                        <tr>
                            <th  rowspan="2" class="TD_MACHUNG">Mã PGD</th>
                            <th  rowspan="2" class="TD_MACHUNG">Sản phẩm</th>
                            <th  rowspan="2" class="TD_TENCHUNG">Nhà ĐT</th>
                            <th  rowspan="2" class="TD_MACHUNG">Mức phí</th>
                            <th colspan="3" class="TD_SOTK">Tỷ lệ phân bổ</th>                                                                                       
                        </tr>    
                        <tr>
                            <th class="TD_MACHUNG">Xã</th>   
                            <th class="TD_MACHUNG">Huyện</th>
                            <th class="TD_MACHUNG">Tỉnh</th>                            
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt_chitiet" var="modelView" status="rowstatus">                           
                                <tr style="text-align: center; color: #0000FF; font-weight: bold;" onmouseover="mover(this);"  onmouseout="mout(this);">                                   
                                    <td align = "right" class="TD_CHITIEU">
                                        <input type="text" value="<s:property  value="D1" />" 
                                               name="lstDulieuNt_chitiet[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                               readonly="readonly"/>
                                    </td>
                                    <td align = "right" class="TD_CHITIEU">
                                        <input type="text" value="<s:property  value="D2" />" 
                                               name="lstDulieuNt_chitiet[<s:property  value="%{#rowstatus.index}" />].D2" class="D1 <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                               readonly="readonly"/>
                                    </td>
                                    <td align = "right" class="TD_CHITIEU">
                                        <input type="text" value="<s:property  value="D3" />" 
                                               name="lstDulieuNt_chitiet[<s:property  value="%{#rowstatus.index}" />].D3" class="D1 <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                               readonly="readonly"/>
                                    </td>
                                    <td align = "right" class="TD_CHITIEU">
                                        <input type="text" value="<s:property  value="D4" />" 
                                               name="lstDulieuNt_chitiet[<s:property  value="%{#rowstatus.index}" />].D4" class="D1 <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                               readonly="readonly"/>
                                    </td>
                                    <td align = "right" class="TD_CHITIEU">
                                        <input type="text" value="<s:property  value="D5" />" 
                                               name="lstDulieuNt_chitiet[<s:property  value="%{#rowstatus.index}" />].D5" class="D1 <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                               readonly="readonly"/>
                                    </td>
                                    <td align = "right" class="TD_CHITIEU">
                                        <input type="text" value="<s:property  value="D6" />" 
                                               name="lstDulieuNt_chitiet[<s:property  value="%{#rowstatus.index}" />].D6" class="D1 <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                               readonly="readonly"/>
                                    </td>
                                    <td align = "right" class="TD_CHITIEU">
                                        <input type="text" value="<s:property  value="D7" />" 
                                               name="lstDulieuNt_chitiet[<s:property  value="%{#rowstatus.index}" />].D7" class="D1 <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                               readonly="readonly"/>
                                    </td>
                                </tr>
                        </s:iterator>
                    </table>  
            </s:if>
            <s:if test="Grade.equalsIgnoreCase('3')">
            <div id="divTitle">
                        TRẠNG THÁI GỬI SỐ LIỆU CỦA CHI NHÁNH
                    </div>
                    <p></p>
                    <table border="1" class="editDelete" id="tablepl01" align="center">
                        <tr>
<!--                            <th align = "center"  style="width: 30px;">
                                <s:checkbox id ="allCheck" name="allCheck"/></th>-->
                            <th align = "center"  style="width: 50px;">Mã PGD</th>
                            <th style="width: 100px;">Tên PGD</th>
                            <th style="width: 60px;">Ngày gửi</th>
                            <th style="width: 50px;">User gửi</th>
                            <th style="width: 90px;">Trạng thái xử lý</th>
                            <th style="width: 90px;">Trạng thái gửi</th>
                        </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                
                        <s:if test="D1.equalsIgnoreCase('true')">
                            <tr style="text-align: center; color: #0000FF" onmouseover="mover(this);"  onmouseout="mout(this);">
                                <!--<td align = "center"  style="width: 20px;"><s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="poscd" fieldValue="%{D3}"/></td>-->
                                <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                <td style="width: 60px;"><s:property  value="D8" /></td>
                                <td style="width: 50px;"><s:property  value="D7" /></td>
                                <td style="width: 90px;"><s:property  value="D9" /></td>
                                <td style="width: 90px;"><s:property  value="D11" /></td>
                            </tr>
                        </s:if>
                        <s:else>
                            <tr style="text-align: center; color: red" onmouseover="mover(this);"  onmouseout="mout(this);">
                                <!--<td align = "center"  style="width: 20px;"><s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="poscd" fieldValue="%{D3}"/></td>-->
                                <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                <td style="width: 60px;"><s:property  value="D8" /></td>
                                <td style="width: 50px;"><s:property  value="D7" /></td>
                                <td style="width: 90px;"><s:property  value="D9" /></td>
                                <td style="width: 90px;"><s:property  value="D11" /></td>
                            </tr>
                        </s:else>
                    </s:iterator>
        </s:if>  
            <sj:submit id="PHIUT_001_save" name="PHIUT_001_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>          
            </div>
    </body>
</html>
