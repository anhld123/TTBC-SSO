<%-- 
    Document   : indexBCN
    Created on : Jul 7, 2014, 9:37:15 AM
    Author     : Nguyễn Phú Vinh
--%>

<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Báo cáo nhanh</title>
    </head>
    <style type="text/css">
        th{
            background-color: #DCDCDC;
            border-color: #999;
        }
        td{
            border-color: #999;
        }
        table{
            border-collapse: collapse;
            width: 100%;
            border-color: #999;
        }
        body,div{
            font-family: Arial;
            font-size: 13px;
        }
        a.button{
            font-weight: bold;
            text-decoration:none;
            color: #018c3b;
        }
        a{
            text-decoration:none;
            color: #018c3b;
        }
        table.bctct tr:hover{
            background-color:#FFE47A;
            cursor: pointer;
        }
    </style>
    <body>
        <form name="frmindex" id="frmindex" method="POST" action="indexbctct.action">
            <div>
                <div>
                    Ngày BC:
                    <input type="text" id="txtngaybc" name="txtngaybc" value="12/12/2014">
                    &nbsp;&nbsp;
                    Lọc nguồn BC:
                    <%
                        String Paravl = request.getParameter("txtnguonbc");
                        if ("01".equals(Paravl)) {
                            out.print("<select name=\"txtnguonbc\" onchange=\"document.getElementById('frmindex').submit();\">");
                            out.print("<option value=\"ALL\">--Tất cả--</option>");
                            out.print("<option value=\"01\" selected>Chỉ tiêu</option>");
                            out.print("<option value=\"02\">Cân đối</option>");
                            out.print("<select>");
                        } else if ("02".equals(Paravl)) {
                            out.print("<select name=\"txtnguonbc\" onchange=\"javascript:document.getElementById('frmindex').submit();\">");
                            out.print("<option value=\"ALL\">--Tất cả--</option>");
                            out.print("<option value=\"01\">Chỉ tiêu</option>");
                            out.print("<option value=\"02\" selected>Cân đối</option>");
                            out.print("<select>");
                        } else {
                            out.print("<select name=\"txtnguonbc\" onchange=\"javascript:document.getElementById('frmindex').submit();\">");
                            out.print("<option value=\"ALL\" selected>--Tất cả--</option>");
                            out.print("<option value=\"01\">Chỉ tiêu</option>");
                            out.print("<option value=\"02\">Cân đối</option>");
                            out.print("<select>");
                        }
                    %>
                    &nbsp;&nbsp;
                    <a href="calledit.action" class="button">Thêm mới</a> &nbsp;&nbsp;| &nbsp;&nbsp; <a href="javascript:window.history.back();" class="button">Quay lại</a>
                </div>
                <hr width="100%" style="border: thin 1px;">
                <div>
                    <table cellpadding="2" cellspacing="0" border="1px" class="bctct">
                        <tr>
                            <th>Số TT</th>
                            <th>ID BC</th>
                            <th>Tên BC</th>
                            <th>Loại BC</th>
                            <th>Nguồn BC</th>
                            <th>Kỳ BC</th>
                            <th>In BC</th>
                            <th>Xuất Excel</th>
                            <th>Sửa BC</th>
                            <th>Xóa BC</th>
                        </tr>
                        <s:iterator value="lstdanhsach">
                            <tr>
                                <td>${sott}</td>
                                <td>${mabc}</td>
                                <td>${tenbc}</td>
                                <td>${loaibc}</td>
                                <td>${nguonbc}</td>
                                <td>${kybc}</td>
                                <td><a href="002505BC001.pdf" target="_blank">Thực hiện</a></td>
                                <td><a href="002505BC001.xlsx">Thực hiện</a></td>
                                <td><a href="calledit.action?txtmabc=${mabc}">Thực hiện</a></td>
                                <td><a href="calldelete.action?txtmabc=${mabc}">Thực hiện</a></td>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
            </div>
        </form>
    </body>
</html>
