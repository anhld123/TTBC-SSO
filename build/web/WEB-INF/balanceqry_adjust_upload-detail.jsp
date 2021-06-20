<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head/>


<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    </head>
    
    <s:form id="poor_upload_form_ID" action="#">
        <p style="color: red; font-family: Arial; font-size: 13px;"
           id="message_ID"
           ><s:property value="message" /> &nbsp;            
        </p>
        <s:hidden name="fileName"/>            
    </s:form>
        
</html>