<%-- 
    Document   : mainhoidap
    Created on : 29-Sep-2014, 08:19:42
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html style="height: 100%;">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    <s:iterator value="getxem">
        <b>Tiêu đề:</b>
        <hr>
        <s:property value="TIEUDE"/>
        <hr>
        <b>Câu hỏi:</b>
        <hr>
        <s:property value="CAUHOI"/>
        <hr>
        <b>Trả lời:</b>
        <hr>
        <s:property value="TRALOI"/>
        <hr>
    </s:iterator>
</body>
</html>
