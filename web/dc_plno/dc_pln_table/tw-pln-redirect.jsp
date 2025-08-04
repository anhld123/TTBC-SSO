<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<META HTTP-EQUIV="Refresh" CONTENT='0;URL=/IMS_REPORTS/loadXuLyRuiRo.action?random=this.random()'>-->
        <script type="text/javascript">
            function Refresher()
            {
                window.location.href = '/IMS_REPORTS/loadTwFormMainPLN.action?random='+Math.round((Math.random() * 1000) + 1);
            }
        </script>
    </head>
    <BODY onLoad="Refresher()">
    </body>
</html>
