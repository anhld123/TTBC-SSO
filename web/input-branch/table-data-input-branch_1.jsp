<%-- 
    Document   : table-data-input-branch
    Created on : Dec 26, 2018, 4:43:45 PM
    Author     : BAOANH
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link  rel="stylesheet" type="text/css" href="css/inputbranch.css"/>-->
        <script>
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $("#idsaveDatatmp").show();

                if (<s:property value="lstMapCot.size"/> > 10)
                {
                    //console.log('lon hon 10');
                    $(".TEXT_CLASS").css({"width": "100px"});
                    $(".NUMB_CLASS").css({"width": "90px"});
                    $(".DATE_CLASS").css({"width": "115px"});
                } else if(<s:property value="lstMapCot.size"/> >5)
                {
                     //console.log('Nho hon 10');
                    $(".TEXT_CLASS").css({"width": "130px"});
                    $(".NUMB_CLASS").css({"width": "120px"});
                    $(".DATE_CLASS").css({"width": "130px"});
                }
                else
                {
                    $(".TEXT_CLASS").css({"width": "150px"});
                    $(".NUMB_CLASS").css({"width": "150px"});
                    $(".DATE_CLASS").css({"width": "150px"});
                }
                $("#containBcttv").css({"overflow": "scroll"});

                
            });

            $('.TEXT_CLASS').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEXT_CLASS').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            $('.NUMB_CLASS').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.NUMB_CLASS').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            $('.hasDatepicker').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.hasDatepicker').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        <style>
            .ContentTable {
                height: 350px;
                margin: 5px 5px 10px 10px;
                width: 100%;
                position: absolute;
                /*background-color: #FBC2C4;*/
                /*        overflow: scroll;*/
                overflow: auto;
                display: flex;
            }

            table.editDelete th{
                background-color: #DCDCDC;
                border-color: #999;
                height: 18px;
                border: 1px solid #00B83F;
                word-wrap:break-word;
            }
            table.editDelete td{
                border-color: #999;
                height: 18px;
                border: 1px solid #00B83F;
                word-wrap:break-word;
            }
            table.editDelete{
                border-collapse: collapse;
                width: 98%;
                border-color: #999;
                table-layout: fixed;
            }
            table.editDelete tr:focus{
                background-color:#FFE47A;
                /*cursor: pointer; hover*/
                table-layout: fixed;
            }
            #divSubTitle{    
                text-align: left;
                color: red;     
                font-size: large
            }
            #divTitle{
                color: blue; 
                font-weight: bolder; 
                font-size: larger;
            }

            .highlight_row {
                background-color: #FFB951; 
                color:#000;
            }
        </style>
    </head>
    <body>
        <p></p>
        <div id="divTitle" align="center">
            <s:property  value="tenmau" />
        </div>
        <br>
        <div class="ContentTable" id="id_ContentTable">
            <s:form action="saveDulieuNhaptay.action" theme="simple" id="formLuuDL">

                <input type="hidden" value="<s:property  value="khoa" />" 
                       name="khoa"/> 
                <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                    <tr height="23">
                        <th class="TD_THUTU" style="width: 60px;">Mã chỉ tiêu</th>
                        <th class="TD_THUTU"  style="width: 100px;">Tên chỉ tiêu</th>
                            <s:iterator value="#attr.lstMapCot" var="modelCot" status="rowstatus">
                                <s:if test="KIEUDULIEU.equalsIgnoreCase('T')">
                                <th class="TEXT_CLASS"> <s:property value='TENHIENTHI'/></th>
                                </s:if>
                                <s:elseif test="KIEUDULIEU.equalsIgnoreCase('N')">
                                <th class="NUMB_CLASS"> <s:property value='TENHIENTHI'/></th>
                                </s:elseif>
                                <s:elseif test="KIEUDULIEU.equalsIgnoreCase('D')">
                                <th class="DATE_CLASS"> <s:property value='TENHIENTHI'/></th>
                                </s:elseif>
                            </s:iterator>
                    </tr>

                    <s:iterator value="#attr.lstDuliewView" var="modelView" status="rowstatus">
                        <tr height="16" align="center">
                        <input type="hidden" value="<s:property  value="khoa" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].KHOA"/> 
                        <input type="hidden" value="<s:property  value="THUTU" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].THUTU"/> 
                        <input type="hidden" value="<s:property  value="MA" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].MA"/> 
                        <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].TEN"/> 
                        <input type="hidden" value="<s:property  value="THAMSO_1" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].THAMSO_1"/> 
                        <input type="hidden" value="<s:property  value="THAMSO_2" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].THAMSO_2"/> 
                        <input type="hidden" value="<s:property  value="THAMSO_3" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].THAMSO_3"/> 
                        <input type="hidden" value="<s:property  value="THAMSO_4" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].THAMSO_4"/> 
                        <input type="hidden" value="<s:property  value="THAMSO_5" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].THAMSO_5"/> 
                        <input type="hidden" value="<s:property  value="THAMSO_6" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].THAMSO_6"/> 
                        <input type="hidden" value="<s:property  value="THAMSO_7" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].THAMSO_7"/> 
                        <input type="hidden" value="<s:property  value="THAMSO_8" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].THAMSO_8"/> 
                        <input type="hidden" value="<s:property  value="THAMSO_9" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].THAMSO_9"/> 
                        <input type="hidden" value="<s:property  value="THAMSO_10" />"  name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].THAMSO_10"/> 

                        <td  style="width: 60px;">
                            <label><s:property  value="MA" /></label> 
                        </td>
                        <td style="width: 100px;" align="left">
                            <label> <s:property  value="TEN" /></label> 
                        </td>

                        <s:if test="DATATYPE_D1.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D1"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D1.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D1" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D1.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D1" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D2.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D2"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D2.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D2" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D2.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D2" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D3.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D3"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D3.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D3" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D3.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D3" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D4.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D4"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D4.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D4" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D4.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D4" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D5.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D5"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D5.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D5" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D5.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D5" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D6.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D6"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D6.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D6" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D6.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D6" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D7.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D7"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D7.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D7" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D7.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D7" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D8.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D8"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D8.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D8" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D8.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D8" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D9.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D9"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D9.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D9" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D9.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D9" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D10.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D10"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D10.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D10.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D10" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D11.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D11"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D11.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D11" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D11.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D11" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D12.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D12"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D12.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D12" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D12.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D12" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D13.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D13"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D13.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D13" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D13.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D13" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D14.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D14"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D14.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D14" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D14.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D14" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D15.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D15"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D15.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D15" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D15.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D15" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D16.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D16"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D16.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D16" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D16.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D16" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D17.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D17"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D17.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D17" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D17.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D17" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D18.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D18"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D18.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D18" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D18.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D18" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D19.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D19"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D19.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D19" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D19.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D19" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D20.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D20"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D20.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D20" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D20.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D20" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D21.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D21"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D21.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D21" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D21.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D21" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D22.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D22"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D22.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D22" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D22.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D22" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D23.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D23"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D23.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D23" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D23.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D23" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D24.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D24"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D24.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D24" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D24.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D24" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D25.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D25"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D25.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D25" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D25.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D25" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D26.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D26"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D26.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D26" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D26.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D26" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D27.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D27"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D27.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D27" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D27.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D27" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D28.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D28"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D28.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D28" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D28.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D28" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D29.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D29"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D29.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D29" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D29.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D29" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D30.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D30"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D30.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D30" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D30.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D30" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D31.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D31"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D31.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D31" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D31.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D31" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D32.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D32"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D32.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D32" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D32.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D32" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D33.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D33"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D33.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D33" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D33.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D33" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D34.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D34"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D34.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D34" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D34.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D34" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D35.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D35"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D35.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D35" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D35.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D35" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D36.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D36"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D36.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D36" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D36.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D36" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D37.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D37"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D37.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D37" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D37.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D37" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D38.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D38"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D38.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D38" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D38.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D38" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D39.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D39"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D39.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D39" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D39.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D39" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D40.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D40"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D40.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D40" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D40.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D40" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D41.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D41"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D41.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D41" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D41.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D41" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D42.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D42"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D42.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D42" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D42.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D42" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D43.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D43"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D43.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D43" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D43.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D43" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D44.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D44"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D44.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D44" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D44.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D44" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D45.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D45"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D45.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D45" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D45.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D45" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D46.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D46"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D46.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D46" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D46.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D46" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D47.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D47"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D47.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D47" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D47.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D47" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D48.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D48"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D48.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D48" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D48.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D48" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D49.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D49"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D49.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D49" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D49.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D49" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D50.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D50"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D50.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D50" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D50.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker name="lstDulieu[%{#rowstatus.index}].D50" value="%{new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>

                        </tr>
                    </s:iterator>

                </table>
                <sj:submit id="id_savedlNhaptay" name="DulieuNT_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_save"
                           onCompleteTopics="completediv_save" cssStyle="display: none"/>
            </s:form>
        </div>

    </body>
</html>
