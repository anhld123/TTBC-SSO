<%-- 
    Document   : dmquyettoan
    Created on : 16-Sep-2014, 15:04:13
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh mục quyết toán</title>
    </head>
    <style type="text/css">
        *{
            margin: 0px;
        }
        table{
            border-style: solid;
            border-collapse: collapse;
            width: 100%;
            font: 12px Arial, Helvetica, sans-serif; 
            line-height: 19px;
        }
        .tbhead{
            background-color: #5e5e55;
            font-weight: bold;
            color: #fff;
            text-align: center;
        }
        .cscontent td{
            padding-left:5px;
        }
        .cscontent:hover{
            background-color: #ffff99;
        }
    </style>
    <body>
        <div style="margin-left: 7px;margin-right: 7px; margin-bottom:10px;">
            <table border="1px">
                <tr class="tbhead">
                    <td>Mã mẫu QT</td>
                    <td>Tên mẫu QT - Nhấn vào tên mẫu để nhập liệu cho biểu Quyết toán</td>
                    <td>Loại</td>
                    <td>Chức năng</td>
                </tr>
                <s:iterator value="danhmucqt">
                    <tr class="cscontent">
                        <td><s:property value="DM_TENVT"/></td>
                        <td><a href="<s:property value="DM_LINKBC"/>" style="text-decoration:none;"><s:property value="DM_MOTA"/></a></td>
                        <td><s:property value="DM_INPUT"/></td>
                        <td><a href="#">Xem</a>&nbsp;|&nbsp;<a href="#">Tải về</a></td>
                    </tr>
                </s:iterator>
            </table>
        </div>
    </body>
</html>
