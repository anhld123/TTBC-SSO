<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            tr th{
                font-family: tahoma;
                font-size: 11px;
            }
        </style>
    </head>
    <body>
            <table border="1" cellspacing="0" cellpading="0" style="width: 100%;border-collapse:collapse;">
                <tr bgcolor="#336699" style="font-weight: bold; color: #fff;">
                    <td>Đơn vị</td>
                    <td>Ghi chú</td>
                    <td>Giá trị 1</td>
                    <td>Giá trị 2</td>
                    <td>Chênh lệc</td>
                </tr>
                <s:iterator value="lst">
                    <tr>
                        <td><s:property value="POS_CD"/></td>
                        <td><s:property value="DESCRIPT"/></td>
                        <td><s:property value="VALUE_1"/></td>
                        <td><s:property value="VALUE_2"/></td>
                        <td><s:property value="DIFFER_AMT"/></td>
                    </tr>
                </s:iterator>
            </table>
        </body>
</html>
