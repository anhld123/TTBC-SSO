<%-- 
    Document   : sbv_indicator_note
    Created on : Nov 14, 2015, 11:40:32 AM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:head></s:head>
<sj:head/>

<style>

    .btn {
        background: #3498db;
        background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
        background-image: -moz-linear-gradient(top, #3498db, #2980b9);
        background-image: -ms-linear-gradient(top, #3498db, #2980b9);
        background-image: -o-linear-gradient(top, #3498db, #2980b9);
        background-image: linear-gradient(to bottom, #3498db, #2980b9);
        font-family: Arial;
        color: #ffffff;
        font-size: 12px;
        margin: 15px 0 0 5px;
        padding: 5px 6px 6px 5px;
        text-decoration: none;
    }

    .btn:hover {
        background: #3cb0fd;
        background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
        background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
        background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
        background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
        background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
        text-decoration: none;
    }
</style>

<s:form id="sbv_indicator_note_FORMID" 
        action="sbv_indicator_save.action"
        theme="simple">
    <h4>Nhập thuyết minh cho phân nhóm: <u><s:property value="group_id"/></u> </h4>
    <s:hidden name="group_id" />
    <s:hidden name="username"/>
    <table style="border: 0px; width: 200px;">
        <tr>
            <td >
                <s:textarea rows="4" cols="50"
                          name="note"/>
            </td>
        </tr>
        <tr></tr>
        <tr>
            <td align="right">
        <s:url id="save_data_URL" action="sbv_indicator_save.action"></s:url>
        <sj:a id="save_data_BUTTON"  href="%{save_data_URL}" 
              targets="update_result_DIV"
              formIds="sbv_indicator_note_FORMID"  button="false"                                                               
              theme="simple">Lưu dữ liệu</sj:a>                
        </td>
        </tr>
    </table>    
</s:form>
<div id="update_result_DIV"></div>