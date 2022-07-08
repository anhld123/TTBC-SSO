<%-- 
    Document   : reload-news
    Created on : Apr 21, 2014, 3:24:50 PM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.Date,java.io.*,java.util.*" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
<%@ taglib prefix="s" uri="/struts-tags" %>

<!--TUNGNV: Thay doi chuoi connect o day bang config-->
<sql:setDataSource var="db" driver="oracle.jdbc.driver.OracleDriver"
                   url="jdbc:oracle:thin:@10.63.48.70:1521:IMSDEV"
                   user="intellect" password="intellect" />
<sql:query dataSource="${db}" var="newsObjs">
    SELECT MESSAGE FROM VBSP_NEWS
    WHERE ID = round (DBMS_RANDOM.VALUE (1, (select count(*) from vbsp_news ))) 
</sql:query>
    
<%
    Integer hitsCount = 
      (Integer)application.getAttribute("hitCounter");
    if( hitsCount ==null || hitsCount == 0 ){
       /* First visit */       
       hitsCount = 1;
    }else{
       /* return visit */       
       hitsCount += 1;
    }
    application.setAttribute("hitCounter", hitsCount);
%>
<font face="verdana" size="2">
Thời gian hiện tại :<%= new java.util.Date() %> 
<br/>
<c:forEach items="${newsObjs.rows}" var="vbspnews">        
    <a>${vbspnews.MESSAGE}</a>
</c:forEach>
</font>
