<%-- 
    Document   : viewPDF
    Created on : 17-Jun-2014, 14:57:56
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html  style="width: 100; height: 100%">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="width: 100; height: 100%">
        <iframe src="${PDFFile}" width="100%" height="100%">
            <p>Browser của bạn không hỗ trợ.</p>
        </iframe>
    </body>
</html>
