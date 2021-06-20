<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <sj:head/>
        <title>Truy vấn báo cáo động</title>
    </head>
    <body>
        <h4>Truy vấn!</h4>
        <hr/>    
        <div id="idDynamicQuery" class="report_group_form">
            <s:form id="dynamicQuery" action="dynamicQuery" theme="simple">
                <table>
                    <tr>
                        <td width="150">Tiêu đề: </td>
                        <td width="400">
                            <s:textfield id="title" name="title" cssStyle="width: 550px"></s:textfield>
                            <!--<input type="text" style="width: 550px; height: 300"/>-->
                        </td>        
                    </tr>
                    <tr>
                        <td width="150">Câu truy vấn: </td>
                        <td width="400">
                        <s:textarea id="query" name="query" cssStyle="width: 550px" rows="15"></s:textarea>
                       </td>        
                    </tr>
                    <tr></tr>
                </table>
            </s:form>
        </div>
        <hr/>
        <div align="right" >
            <img id="loadingImage_next" src="img/loaderB32.gif" style="display:none"/>
            <sj:a formIds="dynamicQuery" targets="divResult" indicator="loadingImage_next" href="#">Tiếp theo</sj:a>
        </div>
        
        <div id="divResult"></div>
    </body>
</html>
