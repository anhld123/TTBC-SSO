<%-- 
    Document   : message
    Created on : Jul 1, 2014, 10:04:08 AM
    Author     : Trung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div style="width: 75%;height: 400px;">
    <s:if test="%{filesList!= null && filesList.size > 0}">
        <table border="1" cellspacing="1" cellpadding="1" width="100%">
            <tr>
                <th>Stt</th>
                <th>Tên file </th>
                <th>Kích thước </th>
                <th>Tải tập tin</th>
            </tr>
            <s:iterator value="filesList" status="rownum">
                <tr height=25>
                    <td align="center"><s:property value="#rownum.count"/></td>
                    <td align="left" style="font-family: verdana; font-size: 9pt" nowrap>
                        <s:property value="fileName" />                     
                    </td>
                    <td align="center">
                        <span style="font-family: verdana; font-size: 9pt"><s:property value="fileSize" /></span>
                    </td>
                    <td align="center">
                        <s:url id="fileDownload" action="text2SbvDownload.action">
                                <s:param name="downloadFileName" value="{fileName}" />
                        </s:url> 
                        <s:a href="%{fileDownload}">Download</s:a>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </s:if>
</div>