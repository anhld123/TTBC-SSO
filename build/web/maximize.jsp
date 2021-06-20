<%-- 
    Tên file    : maximize
    Ngày tạo    : Jul 29, 2014, 11:06:03 AM
    Tác giả     : Nguyễn Phú Vinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS_REPORTS</title>
        <link href="img/Logo_VBSP.ico" rel="icon" type="image/x-icon" />
        <link href="img/Logo_VBSP.ico" rel="shortcut icon" type="image/x-icon" />
        <script>
            window.onload = function Maximize() {
                var ht = screen.availHeight;
                var wt = screen.availWidth;
                var resize = window.open("index.jsp", "IMSREPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                    resize.resizeBy(wt, ht);
                } else {
                    resize.resizeTo(wt, ht);
                }
                resize.focus();
                window.open('', '_parent', '');
                window.close();
                window.open('', '_self', '');
                _self.close();
            }
        </script>
    </head>
    <body>
    </body>
</html>
