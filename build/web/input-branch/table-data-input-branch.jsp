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
        <%--<sj:head/>--%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<link  rel="stylesheet" type="text/css" href="css/inputbranch.css"/>-->
        <script>
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                try {
                    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                    $('#ui-datepicker-div').css('clip', 'auto');
                } catch (e) {
                    console.log(e.toString());
                }
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
                    $(".LIST_CLASS").css({"width": "120px"});
                } else if (<s:property value="lstMapCot.size"/> > 5)
                {
                    //console.log('Nho hon 10');
                    $(".TEXT_CLASS").css({"width": "130px"});
                    $(".NUMB_CLASS").css({"width": "120px"});
                    $(".DATE_CLASS").css({"width": "130px"});
                    $(".LIST_CLASS").css({"width": "130px"});
                } else
                {
                    $(".TEXT_CLASS").css({"width": "220px"});
                    $(".NUMB_CLASS").css({"width": "220px"});
                    $(".DATE_CLASS").css({"width": "220px"});
                    $(".LIST_CLASS").css({"width": "220px"});
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
            $('.LIST_CLASS').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.LIST_CLASS').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            $('.hasDatepicker').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.hasDatepicker').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });

            $.subscribe("beforediv_save", function (event, data) {
                $("#loadingImageDiv_data").show();
            });
            $.subscribe("completediv_save", function (event, data) {
                $("#loadingImageDiv_data").hide();
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
            #divDonvitinh{
                text-align: right;
                padding-right: 30px;
                color: red; 
                font-weight: bold;
                /*background: #99ffff;*/
                animation: blinker 1s linear infinite;
            }
        </style>
    </head>
    <body>
        <p></p>
        <div id="divTitle" align="center">
            <s:property  value="tenmau" />
        </div>
        <div id="divDonvitinh">                
            Đơn vị tính: <s:property  value="donvitinh" />
        </div> 
        <br>
        <div class="ContentTable" id="id_ContentTable">
            <s:form action="saveDulieuNhaptay.action" theme="simple" id="formLuuDL">

                <input type="hidden" value="<s:property  value="khoa" />" 
                       name="khoa"/> 

                <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                    <tr height="23">
                        <th class="TD_THUTU" style="width: 60px;color: #18ab29">Mã chỉ tiêu</th>
                        <th class="TD_THUTU"  style="width: 100px;color: #18ab29">Tên chỉ tiêu</th>
                            <s:iterator value="#attr.lstMapCot" var="modelCot" status="rowstatus">
                                <s:if test="KIEUDULIEU.equalsIgnoreCase('T')">
                                <th class="TEXT_CLASS" style="color: #18ab29"> <s:property value='TENHIENTHI'/></th>
                                </s:if>
                                <s:elseif test="KIEUDULIEU.equalsIgnoreCase('N')">
                                <th class="NUMB_CLASS"  style="color: #18ab29"> <s:property value='TENHIENTHI'/></th>
                                </s:elseif>
                                <s:elseif test="KIEUDULIEU.equalsIgnoreCase('D')">
                                <th class="DATE_CLASS"  style="color: #18ab29"> <s:property value='TENHIENTHI'/></th>
                                </s:elseif>
                                <s:elseif test="KIEUDULIEU.equalsIgnoreCase('L')">
                                <th class="LIST_CLASS"  style="color: #18ab29"> <s:property value='TENHIENTHI'/></th>
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
                            <label style="color: #0000FF"><s:property  value="MA" /></label> 
                        </td>
                        <td style="width: 100px;" align="left">
                            <label style="color: #0000FF"> <s:property  value="TEN" /></label> 
                        </td>

                        <s:if test="DATATYPE_D1.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D1"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D1"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D1.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D1"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D1" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D1.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"> <s:property  value="D1"/>
                                <sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D1" value="%{D1 != null?D1:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D1.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD1" name="lstDulieu[%{#rowstatus.index}].D1" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D1}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D2.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D2"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D2"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D2.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D2"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D2" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D2.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D2" value="%{D2 != null?D2:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D2.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD2" name="lstDulieu[%{#rowstatus.index}].D2" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D2}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D3.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D3"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D3"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D3.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D3"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D3" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D3.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D3" value="%{D3 != null?D3:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D3.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD3" name="lstDulieu[%{#rowstatus.index}].D3" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D3}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D4.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D4"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D4"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D4.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D4"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D4" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D4.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D4" value="%{D4 != null?D4:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D4.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD4" name="lstDulieu[%{#rowstatus.index}].D4" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D4}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D5.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D5"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D5"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D5.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D5"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D5" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D5.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D5" value="%{D5 != null?D5:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D5.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD5" name="lstDulieu[%{#rowstatus.index}].D5" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D5}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D6.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D6"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D6"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D6.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D6"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D6" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D6.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D6" value="%{D6 != null?D6:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D6.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD6" name="lstDulieu[%{#rowstatus.index}].D6" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D6}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D7.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D7"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D7"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D7.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D7"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D7" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D7.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D7" value="%{D7 != null?D7:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D7.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD7" name="lstDulieu[%{#rowstatus.index}].D7" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D7}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D8.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D8"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D8"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D8.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D8"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D8" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D8.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D8" value="%{D8 != null?D8:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D8.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD8" name="lstDulieu[%{#rowstatus.index}].D8" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D8}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D9.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D9"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D9"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D9.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D9"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D9" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D9.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D9" value="%{D9 != null?D9:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D9.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD9" name="lstDulieu[%{#rowstatus.index}].D9" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D9}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D10.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D10"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D10"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D10.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D10"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D10.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D10" value="%{D10 != null?D10:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D10.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD10" name="lstDulieu[%{#rowstatus.index}].D10" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D10}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D11.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D11"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D11"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D11.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D11"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D11" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D11.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D11" value="%{D11 != null?D11:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D11.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD11" name="lstDulieu[%{#rowstatus.index}].D11" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D11}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D12.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D12"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D12"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D12.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D12"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D12" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D12.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D12" value="%{D12 != null?D12:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D12.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD12" name="lstDulieu[%{#rowstatus.index}].D12" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D12}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D13.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D13"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D13"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D13.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D13"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D13" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D13.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D13" value="%{D13 != null?D13:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D13.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD13" name="lstDulieu[%{#rowstatus.index}].D13" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D13}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D14.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D14"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D14"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D14.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D14"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D14" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D14.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D14" value="%{D14 != null?D14:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D14.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD14" name="lstDulieu[%{#rowstatus.index}].D14" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D14}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D15.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D15"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D15"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D15.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D15"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D15" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D15.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D15" value="%{D15 != null?D15:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D15.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD15" name="lstDulieu[%{#rowstatus.index}].D15" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D15}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D16.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D16"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D16"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D16.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D16"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D16" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D16.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D16" value="%{D16 != null?D16:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D16.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD16" name="lstDulieu[%{#rowstatus.index}].D16" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D16}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D17.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D17"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D17"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D17.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D17"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D17" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D17.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D17" value="%{D17 != null?D17:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D17.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD17" name="lstDulieu[%{#rowstatus.index}].D17" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D17}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D18.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D18"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D18"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D18.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D18"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D18" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D18.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D18" value="%{D18 != null?D18:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D18.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD18" name="lstDulieu[%{#rowstatus.index}].D18" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D18}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D19.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D19"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D19"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D19.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D19"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D19" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D19.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D19" value="%{D19 != null?D19:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D19.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD19" name="lstDulieu[%{#rowstatus.index}].D19" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D19}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D20.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D20"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D20"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D20.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D20"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D20" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D20.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D20" value="%{D20 != null?D20:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D20.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD20" name="lstDulieu[%{#rowstatus.index}].D20" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D20}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D21.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D21"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D21"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D21.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D21"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D21" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D21.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D21" value="%{D21 != null?D21:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D21.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD21" name="lstDulieu[%{#rowstatus.index}].D21" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D21}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D22.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D22"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D22"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D22.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D22"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D22" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D22.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D22" value="%{D22 != null?D22:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D22.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD22" name="lstDulieu[%{#rowstatus.index}].D22" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D22}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D23.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D23"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D23"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D23.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D23"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D23" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D23.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D23" value="%{D23 != null?D23:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D23.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD23" name="lstDulieu[%{#rowstatus.index}].D23" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D23}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D24.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D24"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D24"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D24.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D24"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D24" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D24.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D24" value="%{D24 != null?D24:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D24.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD24" name="lstDulieu[%{#rowstatus.index}].D24" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D24}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D25.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D25"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D25"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D25.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D25"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D25" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D25.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D25" value="%{D25 != null?D25:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D25.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD25" name="lstDulieu[%{#rowstatus.index}].D25" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D25}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D26.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D26"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D26"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D26.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D26"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D26" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D26.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D26" value="%{D26 != null?D26:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D26.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD26" name="lstDulieu[%{#rowstatus.index}].D26" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D26}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D27.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D27"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D27"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D27.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D27"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D27" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D27.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D27" value="%{D27 != null?D27:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D27.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD27" name="lstDulieu[%{#rowstatus.index}].D27" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D27}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D28.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D28"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D28"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D28.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D28"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D28" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D28.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D28" value="%{D28 != null?D28:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D28.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD28" name="lstDulieu[%{#rowstatus.index}].D28" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D28}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D29.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D29"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D29"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D29.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D29"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D29" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D29.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D29" value="%{D29 != null?D29:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D29.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD29" name="lstDulieu[%{#rowstatus.index}].D29" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D29}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D30.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D30"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D30"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D30.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D30"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D30" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D30.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D30" value="%{D30 != null?D30:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D30.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD30" name="lstDulieu[%{#rowstatus.index}].D30" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D30}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D31.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D31"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D31"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D31.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D31"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D31" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D31.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D31" value="%{D31 != null?D31:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D31.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD31" name="lstDulieu[%{#rowstatus.index}].D31" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D31}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D32.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D32"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D32"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D32.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D32"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D32" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D32.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D32" value="%{D32 != null?D32:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D32.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD32" name="lstDulieu[%{#rowstatus.index}].D32" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D32}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D33.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D33"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D33"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D33.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D33"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D33" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D33.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D33" value="%{D33 != null?D33:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D33.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD33" name="lstDulieu[%{#rowstatus.index}].D33" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D33}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D34.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D34"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D34"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D34.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D34"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D34" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D34.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D34" value="%{D34 != null?D34:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D34.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD34" name="lstDulieu[%{#rowstatus.index}].D34" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D34}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D35.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D35"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D35"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D35.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D35"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D35" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D35.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D35" value="%{D35 != null?D35:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D35.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD35" name="lstDulieu[%{#rowstatus.index}].D35" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D35}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D36.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D36"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D36"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D36.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D36"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D36" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D36.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D36" value="%{D36 != null?D36:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D36.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD36" name="lstDulieu[%{#rowstatus.index}].D36" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D36}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D37.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D37"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D37"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D37.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D37"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D37" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D37.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D37" value="%{D37 != null?D37:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D37.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD37" name="lstDulieu[%{#rowstatus.index}].D37" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D37}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D38.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D38"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D38"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D38.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D38"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D38" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D38.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D38" value="%{D38 != null?D38:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D38.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD38" name="lstDulieu[%{#rowstatus.index}].D38" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D38}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D39.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D39"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D39"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D39.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D39"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D39" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D39.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D39" value="%{D39 != null?D39:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D39.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD39" name="lstDulieu[%{#rowstatus.index}].D39" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D39}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D40.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D40"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D40"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D40.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D40"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D40" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D40.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D40" value="%{D40 != null?D40:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D40.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD40" name="lstDulieu[%{#rowstatus.index}].D40" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D40}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D41.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D41"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D41"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D41.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D41"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D41" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D41.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D41" value="%{D41 != null?D41:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D41.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD41" name="lstDulieu[%{#rowstatus.index}].D41" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D41}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D42.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D42"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D42"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D42.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D42"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D42" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D42.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D42" value="%{D42 != null?D42:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D42.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD42" name="lstDulieu[%{#rowstatus.index}].D42" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D42}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D43.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D43"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D43"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D43.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D43"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D43" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D43.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D43" value="%{D43 != null?D43:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D43.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD43" name="lstDulieu[%{#rowstatus.index}].D43" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D43}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D44.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D44"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D44"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D44.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D44"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D44" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D44.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D44" value="%{D44 != null?D44:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D44.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD44" name="lstDulieu[%{#rowstatus.index}].D44" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D44}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D45.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D45"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D45"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D45.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D45"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D45" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D45.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D45" value="%{D45 != null?D45:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D45.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD45" name="lstDulieu[%{#rowstatus.index}].D45" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D45}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D46.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D46"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D46"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D46.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D46"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D46" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D46.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D46" value="%{D46 != null?D46:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D46.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD46" name="lstDulieu[%{#rowstatus.index}].D46" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D46}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D47.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D47"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D47"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D47.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D47"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D47" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D47.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D47" value="%{D47 != null?D47:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D47.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD47" name="lstDulieu[%{#rowstatus.index}].D47" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D47}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D48.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D48"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D48"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D48.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D48"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D48" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D48.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D48" value="%{D48 != null?D48:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D48.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD48" name="lstDulieu[%{#rowstatus.index}].D48" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D48}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D49.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D49"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D49"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D49.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D49"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D49" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D49.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D49" value="%{D49 != null?D49:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D49.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD49" name="lstDulieu[%{#rowstatus.index}].D49" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D49}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>
                        <s:if test="DATATYPE_D50.equalsIgnoreCase('T')">                    
                            <td class="TEXT_CLASS"><input type="text" value="<s:property  value="D50"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D50"  cssStyle="width: 100%;" class="TEXT_CLASS"/></td>
                            </s:if>
                            <s:elseif test="DATATYPE_D50.equalsIgnoreCase('N')">                            
                            <td  class="NUMB_CLASS"><input type="text" value="<s:property  value="D50"/>" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D50" class="number2 NUMB_CLASS" cssStyle="width: 100%;"/></td>
                            </s:elseif>
                            <s:elseif test="DATATYPE_D50.equalsIgnoreCase('D')">                                             
                            <td  class="DATE_CLASS"><sj:datepicker yearRange="-100:+100" name="lstDulieu[%{#rowstatus.index}].D50" value="%{D50 != null?D50:new java.util.Date()}"  cssStyle="width: 82px;" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/></td>
                        </s:elseif>
                        <s:elseif test="DATATYPE_D50.equalsIgnoreCase('L')">                                             
                            <td  class="LIST_CLASS"><s:select  list="lstD50" name="lstDulieu[%{#rowstatus.index}].D50" listKey="sKey" listValue="sDesc" id="list_%{#rowstatus.index}" value="%{D50}" cssStyle="width: 160px;"></s:select></td>
                        </s:elseif>


                        </tr>
                    </s:iterator>

                </table>
                <sj:submit id="id_savedlNhaptay" name="DulieuNT_save" value="save" targets="message_suc_err"  onBeforeTopics="beforediv"
                           onCompleteTopics="completediv" cssStyle="display: none"/>
            </s:form>
        </div>

    </body>
</html>
