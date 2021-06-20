<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<s:select list="userList" 
          theme="simple"     
          headerKey=""
          headerValue="--Chọn CBTD--"
          listKey="sKey"
          listValue="sDesc"/>