<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<% session.setAttribute("permit", request.getParameter("permit")); %>

<jsp:include page="/WEB-INF/commune-edit-main.jsp"/>    




