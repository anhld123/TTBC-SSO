<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:head/>
<!--<hr/>-->
<div class="report_group_form">
    <s:form  id="formdownload" name="formdownload" action="khnv/dk/download_file" method="post" theme="simple">
        <h4>
            <s:hidden name="downloadFilePath"/>
            Tải file báo cáo: 
            <a href="#" name="file1" onclick="document.forms['formdownload'].submit();"><s:property value="downloadFileName" /></a>
        </h4>
    </s:form>
</div>