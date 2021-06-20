<%-- 
    Document   : mainhoidap
    Created on : 29-Sep-2014, 08:19:42
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height: 100%;">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="../editor/ckeditor.js" language="javascript"></script>
    </head>
    <body>
        <form name="frmmain">
            <div style="width:100%; border: 1px;height: 100%;">
                <table style="width:100%; height: 100%;">
                    <tr>
                        <td align="right" valign="top" style="width: 5%;">Tiêu đề:</td>
                        <td><input type="text" name="txttieude" style="width:99.5%;"/></td>
                    </tr>
                    <tr>
                        <td align="right" valign="top" style="width: 5%;">Nội dung</td>
                        <td><textarea style="width:100%;" name="txtcauhoi"></textarea></td>
                    </tr>
                </table>
            </div>
            <script type="text/javascript" language="javascript">
                CKEDITOR.replace('txtcauhoi');
            </script>  
            <hr>
            <input type="button" name="btncommit" value="Gửi">
            <input type="reset" name="btnreset" value="Quay lại" onclik="window.history.back();">
        </form>
    </div>
</body>
</html>
