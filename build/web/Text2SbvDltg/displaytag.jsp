<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<title></title>
<style type="text/css">

    /*        for hiding the page banner */
    .pagebanner 
    {
        display: none;
        padding-top: 5px; 
    }
    /* for customizing page links */
    .pagelinks 
    {
        color: maroon;
        margin: 20px 5px 20px 5px;
    }
    /*         for shifting all the Export options*/
    /*        .exportlinks
            {
                margin: 0px 0px 0px 0px;
            }*/
    /*         For changing the spaces between export link */
    /*        .export
            {
                margin-left: 0px;
            }*/
    /*         For Table css */
    table.DisplayTagTblStyle
    {
        border: 1px solid #666;
        width: 100%;
        margin: 10px 0 10px 0px;
    }
    /*         For odd and even row decoration */
    table.DisplayTagTblStyle tr.odd 
    {
        background-color: #fff
    }
    table.DisplayTagTblStyle tr.tableRowEven,
    table.DisplayTagTblStyle tr.even 
    {
        background-color: #CCCCCC
    }
    /*         Css for table elements */
    table.DisplayTagTblStyle th,
    table.DisplayTagTblStyle td
    {
        padding: 2px 4px 2px 4px;
        text-align: left;
        vertical-align: top;
    }
    table.DisplayTagTblStyle thead tr 
    {
        background-color: #999999;
    }
    /*         For changing the background colour while sorting */
    table.DisplayTagTblStyle th.sorted 
    {
        background-color: #CCCCCC;
    }
    table.DisplayTagTblStyle th.sorted a,
    table.DisplayTagTblStyle th.sortable a 
    {
        background-position: right;
        display: block;
        width: 100%;
    }
    table.DisplayTagTblStyle th a:hover 
    {
        text-decoration: underline;
        color: black;
    }
    table.DisplayTagTblStyle th a,
    table.DisplayTagTblStyle th a:visited 
    {
        color: black;
    }
</style>

<html>
    <body>        
        <div id="maintablediv">
        <display:table id="row" name="filesList" pagesize="10" cellpadding="5px;"
                       cellspacing="5px;" style="margin-left:0px;margin-top:10px;border-collapse: true;" 
                       requestURI="exportText2Sbv.action"
                       class="DisplayTagTblStyle">
            <display:column title="Stt" >
                <c:out value="${row_rowNum}"/>
            </display:column>
            <display:column property="fileName" title="Tên file"/>
            <display:column property="fileSize" title="Kích thước"/>    
            <display:column property="url" title="Tải tập tin" 
                            href="text2SbvDownload.action" 
                            paramId="downloadFileName" paramProperty="filePath">               
            </display:column>
        </display:table>
            </div>        
        <div id="downloadAllLinkDiv" class="link_download_form">
            <s:form  id="formdownload" name="formdownload" action="download" method="post" theme="simple">
                <h4>
                    <s:hidden name="fileNamelocal"/>
                    Tải file nén: 
                    <a href="#" name="file1" onclick="document.forms['formdownload'].submit();">
                        <s:property value="filereport" /></a>
                </h4>
            </s:form>
        </div>
    </body>
</html>