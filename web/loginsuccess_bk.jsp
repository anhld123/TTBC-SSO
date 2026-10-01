<%@page import="vbsp.ims.log.CoreLogger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
    String name = request.getParameter("username");
    String reportGrade = request.getParameter("SELECTED_USER_LEVEL");    
    session.setAttribute("username", name);
//    CoreLogger.error("Gi thu du lieu tren loginsucess ---------------------------------------------------- "+session.getAttribute("username"));
    session.setAttribute("reportGrade", reportGrade);    
%>
<jsp:include page="/WEB-INF/templates/template.jsp">            
    <jsp:param name="body" value="/WEB-INF/home-main.jsp"/>            
</jsp:include>        