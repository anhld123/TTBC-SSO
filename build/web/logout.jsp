<%-- 
    Document   : logout
    Created on : Apr 14, 2014, 1:00:06 PM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hệ thống thông tin báo cáo</title>
        <link rel="shortcut icon" type="image/x-icon" href="img/Logo_VBSP.ico"/>
        <script language="javascript" type="text/javascript">
            function closeWindow() {
            <% 
                session.removeAttribute("username");
                session.removeAttribute("reportGrade");
            %>
                if (navigator.userAgent.indexOf('Chrome') !== -1
                        && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                    window.open('', '_self', '');
                    self.close();
                    return false;
                } else {
                    window.open('', '_parent', '');
                    window.close();
                }
            }
        </script>
        <style >
            .box {
                width:400px;
                height:30px;                
                position:fixed;
                margin-left:-150px; /* half of width */
                margin-top:-150px;  /* half of height */
                top:50%;
                left:45%;                
            }
        </style>
    </head>
    <body>        
        <table border="2"  class="box" style="border-collapse:collapse;border-color: #CCC;" cellspacing="10">            
            <tbody>
                <tr>
                    <td style="border-right: 0px;"><p align="center"><img src="img/tick_ico.ico" /></p></td>
                    <td style="border-left: 0px;">
                        <p align="center">Cám ơn đã sử dụng chương trình.&nbsp;&nbsp;</p>
                    </td>                    
                </tr>
                <tr>
                    <td colspan="2" align="right">
                        <p>
                            <a href="before-login_process.jsp">Đăng nhập bằng user khác</a>&nbsp;                            
                            <a href="javascript:closeWindow();">Đóng</a>&nbsp;                            
                        </p>
                    </td>
                </tr>
            </tbody>
        </table>            
    </body>
</html>
