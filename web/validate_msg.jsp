<%-- 
    Document   : message
    Created on : Jul 1, 2014, 10:04:08 AM
    Author     : Trung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>    
<%@ taglib prefix="s" uri="/struts-tags" %>


<s:if test="%{#error_msg != ''}">
	<p style="color: red;"><s:property value="error_msg" /></p>
</s:if>
<s:if test="%{#success_msg != ''}">
	<p style="color: blue;"><s:property value="success_msg" /></p>
</s:if>
        <input type="hidden" id="js_suggess_value_id" value="<s:property value='suggess_val'/>"/>

