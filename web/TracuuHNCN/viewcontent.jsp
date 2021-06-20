<%-- 
    Document   : viewcontent
    Created on : Oct 8, 2015, 10:00:23 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table border="1" cellspacing="0" cellpadding="0" width="100%" height="100%" style="border-collapse:collapse;">
            <s:iterator value="lstviewcontrol">
                <s:property value="GIATRI" escape="false"/>
            </s:iterator>
        </table>
        <table border="1" cellspacing="0" cellpadding="0" width="100%" height="100%" style="border-collapse:collapse;">
            <tr><td align="right">Trang hiện hành:
                    <select name="pgtrang" id="pgtrang" style="width:120px;" onchange="submitvalue(this.value)">
                        <s:iterator begin="1" end="%{totalpage}" status="status">
                            <s:if test="%{#status.count == pagenum}">
                                <option value="<s:property value="%{#status.count}" />" selected disabled> &raquo; Trang: <s:property value="%{#status.count}" /></option>
                            </s:if>
                            <s:else>
                                <option value="<s:property value="%{#status.count}" />">Trang: <s:property value="%{#status.count}" /></option>
                            </s:else>
                        </s:iterator>
                    </select>
                </td></tr>
        </table>
    </body>
</html>
