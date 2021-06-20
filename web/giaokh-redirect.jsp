<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<META HTTP-EQUIV="Refresh" CONTENT="0;URL=/IMS_REPORTS/get_data_giaokh.action">-->
        <script>
//            var ht = screen.availHeight;
//            var wt = screen.availWidth;
//            var resize = window.open("/IMS_REPORTS/get_data_giaokh.action", "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
//            if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
//                resize.resizeBy(wt, ht);
//            } else {
//                resize.resizeTo(wt, ht);
//            }
//            resize.focus();

            var params = [
                'height='+screen.height,
                'width='+screen.width,
                'fullscreen=yes' // only works in IE, but here for completeness
            ].join(',');
                 // and any other options from
                 // https://developer.mozilla.org/en/DOM/window.open

            var popup = window.open('/IMS_REPORTS/get_data_giaokh.action?vbsprandom='+Math.round((Math.random()*1000)+1), 'popup_window', params); 
            popup.moveTo(0,0);
            
            window.history.back();
        </script>
    </head>
    <body>
    </body>
</html>
