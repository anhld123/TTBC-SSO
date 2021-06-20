<%-- 
    Document   : main-input-branch
    Created on : Nov 20, 2018, 1:49:03 PM
    Author     : BAOANH
--%>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <sj:head/>
           <link  rel="stylesheet" type="text/css" href="input-branch/css/inputbranch.css"/>
           <script type="text/javascript" src="js/sweetalert.min.js"></script>
    </head>
    </head>
    <style>
       
    </style>

    <script>
        //Desc: Lan dau tien load trang, goi den su kien click load all bc
        function initPage() {
            $("#loadFormName")[0].click();
            $("#divMessage").empty();
        }
    </script>
    <body onload="initPage()">

        <s:form id="Query" theme="simple" action="Khonglamgi.action">
            <div id="menuBcttv" >
                <div style="float: right;width: 140px">
                    <s:url id="editinbrfr" action="EditInputBRFORM" />
                    <sj:submit id="idEditDelQuery" targets="containBcttv"  href="%{EditInputBRFORM}" indicator="loadingImage" value="Sửa/Xóa mẫu biểu" cssClass="metroButtonStyle"></sj:submit>
                    <s:url id="LoadAddNewform" action="LoadAddNewForm.action" />
                    <sj:submit id="loadFormName" targets="containBcttv"  href="%{LoadAddNewform}" cssStyle="display: none;" value="Load BC"></sj:submit>
                    </div>
                    <div style="float: right; width: 130px" >
                    <sj:submit id="addFastRpt" targets="containBcttv"  href="%{LoadAddNewform}" value="Thêm mới" cssClass="metroButtonStyle"></sj:submit>
                    </div>
                </div>

        </s:form>
        <div id="containBcttv" >
        </div>

    </body>
</html>
