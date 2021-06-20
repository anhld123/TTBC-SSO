<%-- 
    Document   : View_data_table_query
    Created on : Jul 24, 2014, 2:05:07 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <sj:head/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <s:form align="center" id="ViewDataReport" name="ViewDataReport" action="SaveFormulaReport" theme="simple">
        <div align="center"><h1><s:property value="title"></s:property> </h1>
        </div>
        <div style="width: 100%;height:300px;overflow: scroll;">
            <table align="center" border="1" cellpadding="0" cellspacing="0" style="width: 75%;">
                <s:iterator value="lstObjQuery" var="test">               
                    <s:property value="sDesc" escape="false"></s:property>
                </s:iterator>
            </table>
        </div>
    </s:form>
</body>
</html>
