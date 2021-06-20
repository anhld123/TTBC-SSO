<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<META HTTP-EQUIV="Refresh" CONTENT="0;URL=/IMS_REPORTS/get_data_dieuchinhkh.action">-->
        
        <script>
            var ht = screen.availHeight;
            var wt = screen.availWidth;
            var resize = window.open("/IMS_REPORTS/get_data_dieuchinhkh.action?vbsprandom="+Math.round((Math.random()*10000)+1), "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                resize.resizeBy(wt, ht);
            } else {
                resize.resizeTo(wt, ht);
            }
            resize.focus();
            
            window.history.back();
        </script>
    </head>
    <body>
    </body>
</html>
