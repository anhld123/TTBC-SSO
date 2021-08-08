<%-- 
    Document   : newjsp
    Created on : Jul 30, 2021, 2:55:03 PM
    Author     : ITCVBSP56
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#fileInput').change(function (e) {
                    var fileName = e.target.files[0].name;
                    $('#idfilename').val(fileName);
                });
            });
        </script>
    </head>
    <body>
        <h2>  
            Struts2 File Upload & Save Example without Database  
        </h2>  
    <s:actionerror />  
    <form action="userImage" method="post" enctype="multipart/form-data">  
        <label>File</label>
        <input id="fileInput" name="userImage" type="file" style="display: none;" />
        <input type="text" name="filename" id="idfilename">
        <label for="fileInput" class="custom-file-upload">Chon File</label>
        <input type="submit" value="Upload"/>
    </form>
</body>
</html>
