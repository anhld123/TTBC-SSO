<%-- 
    Document   : error
    Created on : Nov 22, 2018, 3:08:27 PM
    Author     : BAOANH
--%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    request.setAttribute("decorator", "none");
    response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0
    response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<s:if test="hasActionErrors()">
       <s:actionerror />
</s:if>
