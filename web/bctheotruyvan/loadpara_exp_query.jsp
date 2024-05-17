<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="js/js.reload.para.ver.1.1.js" type="text/javascript"></script>
        <style>
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: .9em;
            }
        </style>

        <script>
            $(function() {
                $("#mapGenReport").bind("click", function() {
                    $("#exportReport").trigger('click');    //Goi den su kien click cua nut that
                });
            });

            $.subscribe('beforeClick', function(event, data) {
                $("#divExportReport").empty();
            });
        </script>
    </head>
    <body>
        <strong>Nhập tham số</strong>
        <hr/>
        <div class="report_group_form" id="listParam">
            <s:form id="genReport" theme="simple" action="genReportExcelQuery">               
                <s:hidden name="save_id"/>
                <table>

                    <s:iterator value="reportParamsList">
                        <tr>
                            <td width="150"><s:property value="label"></s:property>:</td>
                                <td width="500">
                                    <!-- Tungnv Neu la T thi gen textfield -->
                                <s:if test="type.equalsIgnoreCase('T')">                                     
                                    <s:textfield  name="%{fieldName}_TEXT"></s:textfield>
                                </s:if>
                                <s:if test="type.equalsIgnoreCase('N')">                                     
                                    <s:textfield  name="%{fieldName}_NUMB"></s:textfield>
                                </s:if>
                                
								<!-- VinhNP xử lsy lại khi chọn selectbox -->
                                
                                <s:if test="fieldName.equals('PARA_MAXA') || fieldName.equals('PV_MAXA') || fieldName.equals('PV_MAXAD') || fieldName.equals('PARA_MATO') || fieldName.equals('PV_MATO') || fieldName.equals('PARA_MATHON') || fieldName.equals('PV_MATHON')">
                                    <select name="<s:property value="fieldName"/>_LIST" id="<s:property value="fieldName"/>">
                                        <option value='000000' selected='selected'>--Tất cả---</option>
                                    </select>
                                    <s:if test="type.equalsIgnoreCase('L')">
                                        <s:select  list="comboList" name="%{fieldName}_DATA" listKey="key" listValue="value" id="%{fieldName}_DATA" cssStyle="display:none"></s:select>
                                    </s:if>
                                </s:if>
                                <s:else>
                                    <s:if test="type.equalsIgnoreCase('L')">
                                        <s:select  list="comboList" name="%{fieldName}_LIST" listKey="key" listValue="value" id="%{fieldName}"></s:select>
                                    </s:if>
                                </s:else>
                                <!--VinhNP: End-->
								
                                <!-- Tungnv: Neu la D thi gen Date -->
                                <s:if test="type.equalsIgnoreCase('D')"> 
                                    <sj:datepicker name="%{fieldName}_DATE" value="%{new java.util.Date()}" 
                                                   placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/>
                                </s:if>
                            </td>
                        </tr>                        
                    </s:iterator>                     
                    <tr>
                        <td align="center" style="color: red">
                            <strong><s:property value="message" /> </strong>
                        </td>
                    </tr>            
                </table>
                <sj:submit id="exportReport" value="Xuất báo cáo" targets="divExportReport" indicator="loadingImage" onBeforeTopics="beforeClick" cssStyle="display: none;"/>
            </s:form>
        </div>
        <hr/>

        <div align="right" id="link">
            <a href="javascript:void(0);" id="mapGenReport">Xuất báo cáo</a>
            <img id="loadingImage" src="img/loading.gif" style="display:none"/>
            <!--<img id="loadingImage" src="img/loaderB32.gif" style="display:none"/>-->
        </div>

        <div id="divExportReport"></div>

    </body>
</html>
