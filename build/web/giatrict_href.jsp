<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            function getURL(parameterName) {
                var queryString = window.top.location.search.substring(1);
                var parameterName = parameterName + "=";
                if (queryString.length > 0) {
                    begin = queryString.indexOf(parameterName);
                    if (begin != -1) {
                        begin += parameterName.length;
                        end = queryString.indexOf("&", begin);
                        if (end == -1) {
                            end = queryString.length
                        }
                        return unescape(queryString.substring(begin, end));
                    }
                }
                return "null";
            }
            function hrefchitieu() {
                var paraUrl = getURL("menuUrl");
                var paraId = getURL("menuId");
                if (paraUrl == "giatrict_href" && paraId == "22") {
                    location.href="mainchitieu.action?nganhang=NHCS";
                } else {
                    location.href="mainchitieu.action?nganhang=NHNN";
                }
            }
        </script>
    </head>
    <body onload="hrefchitieu()">
    </body>
</html>