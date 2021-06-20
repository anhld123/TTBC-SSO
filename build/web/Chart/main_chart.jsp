<%-- 
    Document   : main_chart
    Created on : Nov 22, 2018, 11:09:19 AM
    Author     : Trung Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
                    
            
    </head>
    <body>
        <div style="padding-left: 30px;">
        <h1>Main chart</h1>
        <a href="#" onclick="javascript:openWindow('pie_chart_demo');">
                                <u><b>&gt;&gt;Pie chart Example</b></u>
                            </a>
        <br/>
        <a href="#" onclick="javascript:openWindow('bar_chart_demo');">
                                <u><b>&gt;&gt;Bar chart Example</b></u>
                            </a>
        <br/>
        <a href="#" onclick="javascript:openWindow('bubble_chart_demo');">
                                <u><b>&gt;&gt;Bubble chart Example</b></u>
                            </a>
        </div>
    </body>
</html>

<script>
                                function openWindow(actionName) {
                                    var ht1 = screen.availHeight - 100;
                                    var wt1 = 800;
                                    var left1 = (screen.width / 2) - (wt1 / 2);
                                    var top1 = 10;
                                    var menuid = $('#menuId').val();
                                    window.open(actionName+'.action', 'IMS_REPORTS',
                                            "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1
                                            + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                                }
                            </script>
