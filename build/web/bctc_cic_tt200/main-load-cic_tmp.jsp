<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
             $(document).ready(function () {
                 $("#datacho_cic").load('/IMS_REPORTS/loadModuleCicTT200.action');
            });
            
        </script>
    </head>
    <BODY>
        <div id="datacho_cic"/>
    </body>
</html>
