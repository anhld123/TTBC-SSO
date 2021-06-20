<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head/>
<style >   
</style>
<div id="tt49_addinfor_main" style="padding-left: 3px;">
    <p style="text-decoration: underline;font-size: 12pt;font-weight: bold;"> 
        Thông tin HĐQT và Ban lãnh đạo
    </p>   
    <div id="contentDiv">
        <jsp:include page="/WEB-INF/tt49_addinfor-main-grid.jsp"/>
    </div>
</div>