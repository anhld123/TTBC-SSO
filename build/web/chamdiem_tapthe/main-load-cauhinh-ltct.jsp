<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<META HTTP-EQUIV="Refresh" CONTENT='0;URL=/IMS_REPORTS/loadXuLyRuiRo.action?random=this.random()'>-->
        <script type="text/javascript">
             $(document).ready(function () {
                 $("#datacho_loaitru").load('/IMS_REPORTS/khoitaoLoaitru.action');
            });
            
        </script>
    </head>
    <BODY>
        <div id="datacho_loaitru"/>
    </body>
</html>
