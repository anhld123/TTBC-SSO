<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"  %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<!DOCTYPE html>
<%--<sj:head/>--%>
<script>
    //CuongBM: 13-May-14
    //Desc: disable xuất báo cáo sau lần click đầu tiên
    var ajaxGetting = false;
    function xuatbc()
    {
        if (ajaxGetting == false) {
//                alert("Xuat bc");
            ajaxGetting = true;
            $("#idGenJasperReport").trigger('click');
        }
//                else
//                {
//                    alert("Khong xuat bc");
//                }
    }

    $.subscribe('onBeforeLoading', function(event, data) {
        ajaxGetting = true;

        $("#createReport").empty();
        $("#genreport").show();
    });

    $.subscribe('onCompleteLoading', function(event, data) {
        ajaxGetting = false;
        $("#genreport").hide();
    });
</script>

<style>
    .ui-datepicker{
        font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
        font-size: .9em;
    }
</style>

<h3>Điền các tham số tạo báo cáo nhanh!</h3>
<hr/>

<div class="report_group_form">
    <s:form action="genReportFast" id="createReportId" theme="simple">
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
                        <!-- Tungnv Neu: la L thi gen List -->
                        <s:elseif test="type.equalsIgnoreCase('L')">
                            <s:select  list="comboList" name="%{fieldName}_LIST" listKey="key" listValue="value" id="%{fieldName}"
                                       cssStyle="width: 400px; vertical-align: middle;"></s:select>
                        </s:elseif>
                        <!-- Tungnv: Neu la D thi gen Date -->
                        <s:elseif test="type.equalsIgnoreCase('D')"> 
                            <sj:datepicker name="%{fieldName}_DATE" value="%{new java.util.Date()}" 
                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/>
                        </s:elseif>
                        <s:else>
                            <s:textfield  name="%{fieldName}_TEXT"></s:textfield>
                        </s:else>
                    </td>
                </tr>                        
            </s:iterator>       
        </table>
        <%--<s:iterator value="lstParaReportFastObj"> 
            <s:if test="sData_type.equals('DATE')">                                     
                <sj:datepicker name="%{sPara_where}_DATE" label="%{sColumn_desc}" onblur="validatedate(this.value)" value="%{new java.util.Date()}" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/>
            </s:if>
            <s:elseif test="sData_type.equals('VARCHAR2_MAPGD')">                                     
                <s:select label="%{sColumn_desc}" list="lstPoslist" name="%{sPara_where}_LIST" listKey="sKey" listValue="sDesc" id="%{sPara_where}"></s:select>
            </s:elseif>
            <s:else>
                <s:textfield label="%{sColumn_desc}" name="%{sPara_where}_TEXT"></s:textfield>
            </s:else>
        </s:iterator>--%>  

    </s:form>
</div>
<hr/>

<div align="right" >
    <table>
        <tr>
            <td>
                <div id="genreport" style="display:none"> <img id="loadingImage" src="img/loaderB32.gif" /> </div>
            </td>
            <td>
                <a href="javascript:void(0);" id="mapGenReport" onclick="xuatbc()">Tạo báo cáo</a> 
                <sj:a formIds="createReportId" id="idGenJasperReport" targets="createReport" 
                      href="#" onCompleteTopics="onCompleteLoading" onBeforeTopics="onBeforeLoading" >
                </sj:a>
            </td>
        </tr>
    </table>
</div>

<!-- CuongBM: 15-Apr-14 -->
<div id="createReport"></div>