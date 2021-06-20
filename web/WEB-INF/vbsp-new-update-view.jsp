<%-- 
    Document   : balance-sheet-view
    Created on : Jun 3, 2014, 10:01:58 AM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="DMChitieu/js/jquery-1.4.4.min.js"></script>
        <script src="ckeditor/ckeditor.js" language="javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $("#newsUpdate").click(function (e) {
                    var strs = CKEDITOR.instances.newContent.getData();
                    document.getElementById('newContent').value = strs;
                    var url, sdata;
                    url = "updateVbspNews.action";
                    sdata = jQuery("form").serialize();
                    $.post(url, sdata, function (data) {
                        $("#contentDiv").html(data);
                    });
                });
            });
        </script>
    </head>
    <body>
        <div>    
            <form id="viewVbspNewsForm">
                <table style="width: 85%;">
                    <tr>
                        <td style="width: 20%;">Tiêu đề bản tin: </td>
                        <td style="width: 80%;">
                            <s:textfield name="title" id="title" cssStyle="width:100%;"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <td>Nội dung bản tin:</td>
                        <td>
                            <textarea name="newContent" id="newContent" rows="10" cols="40"><s:property value="newContent"/></textarea>
                        </td>
                    </tr>
                    <script type="text/javascript" language="javascript">
                        CKEDITOR.replace('newContent');
                    </script>  
                    <tr>
                        <td>
                            <div id="updateVbspNewsDiv"/> 
                        </td>
                        <td align="right">
                            <input type="button" id="newsUpdate" value="Cập nhật" onClick="ValidationEvent();"/>
                        </td>
                    </tr>            
                </table>
            </form>
        </div>  
    </body>
</html>