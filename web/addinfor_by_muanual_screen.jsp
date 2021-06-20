<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
        <script type="text/javascript">            
            function refresh()
            {
                window.location.href = '/IMS_REPORTS/list_add_infor_by_manual.action?proc='+'view'+',random='+Math.round((Math.random() * 1000) + 1);
            }            
        </script>
    </head>
    <body onLoad="refresh()">
    </body>
</html>
