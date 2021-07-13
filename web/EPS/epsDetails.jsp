<%-- 
    Document   : epsDetails
    Created on : Jul 12, 2021, 2:10:30 PM
    Author     : ITCVBSP56
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table id="tblData">
            <tr>
                <th class="clsSTT">STT</th>
                <th class="clsCN">Chi nhánh</th>
                <th class="clsPGD">Phòng Giao dịch</th>
                <th class="clsFILL">Trạng thái<br><span style="font-weight: normal; color: red;" id="status">(Chưa chốt số liệu)</span></th>
                <th class="clsNGN">Nguyên nhân</th>
            </tr>
            <s:iterator value="lstData">
                <tr>
                    <td class="clsSTT"><s:property value='KHOA'/></td>
                    <td><s:property value='MACN'/></td>
                    <td><s:property value='MAPGD'/></td>
                    <td><s:property value='D2'/></td>
                    <td><s:property value='D1'/>
                    </td>
                </tr>
            </s:iterator>
        </table>
        <script>
            $(document).ready(function () {
                $("#btnChuaChot").val("Chưa chốt số liệu: " + <s:property value='chuachot'/>).fadeIn();
                $("#btnDaChot").val("Đã chốt số liệu: " + <s:property value='dachot'/>).fadeIn();
                $("#btnChotSai").val("Chốt sai số liệu: " + <s:property value='chotsai'/>).fadeIn();
            });
        </script>
    </body>
</html>
