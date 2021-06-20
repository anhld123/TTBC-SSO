<%-- 
    Document   : newjsp
    Created on : Nov 19, 2015, 9:49:28 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            *{
                font: 12px Arial, Helvetica, sans-serif;
            }
            table{
                border-style: solid;
                border-collapse: collapse;
                width: 100%;
                line-height: 19px;
            }
            th{
                background-color: #5e5e55;
                font-weight: bold;
                color: #fff;
                text-align: center;
                padding: 5px;
            }
        </style>
    </head>
    <body>
        <Table border="1">
            <s:iterator value="lstviewcontrol">
                <s:property value="DINHNGHIA" escape="false"/>
            </s:iterator>
        </table>
    </body>
</html>
