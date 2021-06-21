<%-- 
    Document   : downfile_bcnhanh
    Created on : Apr 22, 2014, 11:21:55 AM
    Author     : LION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:head/>
<div class="report_group_form">
    <s:form  id="formdownload" name="formdownload" action="download" method="post" theme="simple">
        <h4>
            <s:hidden name="fileNamelocal"/>
            Tải file báo cáo: 
            <a href="#" name="file1" onclick="document.forms['formdownload'].submit();"><s:property value="filereport" /></a>
        </h4>
    </s:form>
</div>