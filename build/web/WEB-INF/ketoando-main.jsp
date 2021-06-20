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
<div id="ketoando_main" style="padding-left: 3px;">
    <p style="text-decoration: underline;font-size: 12pt;font-weight: bold;"> 
        Kế toán đồ
    </p>
    <s:form id="ketoando_form" theme="simple">        
        <p>
            Loại tài khoản:          
            <input type="radio" name="accountType" value="1" checked/> GL
            <input type="radio" name="accountType" value="2"/> SBV
            <s:url id="remoteurl" action="buildRadioButton.action" />
            &nbsp; &nbsp; <sj:a id="viewAccountInfor"  href="%{remoteurl}"
                  button="false" theme="simple"
                  formIds="ketoando_form"               
                  targets="contentDiv"> &#10162;Xem </sj:a>        
            </p>                      
    </s:form>
    <div id="contentDiv">
        <jsp:include page="/WEB-INF/ketoando-main-grid.jsp"/>
    </div>
</div>