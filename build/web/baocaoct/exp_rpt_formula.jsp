<%-- 
    Document   : exp_query
    Created on : Jul 14, 2014, 4:17:12 PM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    $(function() {
                //Khi thay doi
                $('#save').change(function() {
                    $("#next_exp").trigger("click");
                });
            });
</script>
<html>
    <head>
        <s:head/>
        <sj:head/>
        <script src="js/JavaScriptUtil.js"></script>
        <script src="js/InputMask.js"></script>
        <script src="js/Parsers.js"></script>
        <script src="js/Checkdate.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h4>Xuất báo cáo</h4>
        <hr/>    
        <div id="report_group_form" class="report_group_form">
            <s:form id="exportformula" action="LoadParametersFormula" theme="simple">
                <table>
                    <tr>
                        <td width="150">Chọn báo cáo: </td>
                        <td width="700">  
                            <s:url id="remoteurl" action="loadSelectExp"></s:url>
                            <sj:select     href="%{remoteurl}" 
                                           id="save"
                                           formIds="reloadform" 
                                           name="save_id"
                                           list="lstObjFormulaExp" 
                                           listKey="sKey"
                                           listValue="sDesc"
                                           emptyOption="true" 
                                           headerKey="-1"
                                           headerValue="---Chọn mẫu báo cáo---" ></sj:select>
                            <img id="loadingImage_next" src="img/loaderB32.gif" style="display:none"/>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                            <%--<sj:submit id="next_exp" name="next_exp" value="Tiếp theo" targets="divShowPage"></sj:submit>--%>
                            <sj:submit id="next_exp" name="next_exp" value="Tiếp theo" targets="divShowPage" indicator="loadingImage_next" onCompleteTopics="after-next" cssStyle="display: none;"/>
                        </td>                    
                        </tr>
                    </table> 
            </s:form>
        </div>
         <div id="divShowPage"></div>
    </body>
</html>

