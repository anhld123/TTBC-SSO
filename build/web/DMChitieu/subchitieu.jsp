<%-- 
    Document   : subchitieu
    Created on : Oct 23, 2014, 8:44:17 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            $(document).ready(function () {
                $("#sendtrang").change(function (e) {
                    document.getElementById("cbotrang").value = $("#sendtrang").val();
                    $('#showdata').html('<img src="img/loading.gif"/>');
                    var url, sdata;
                    url = "excloact.action";
                    sdata = jQuery("#mainchitieu").serialize();
                    $.post(url, sdata, function (data) {
                        $("#showdata").html(data);
                    });
                });
                $("#cmdexp").click(function (e) {
                    var url, sdata;
                    url = "exchitieu.action?";
                    sdata = jQuery("#mainchitieu").serialize();
                    location.href = url + sdata;
                });
            });
        </script>
    </head>
    <body style="font-family: tahoma; font-size: 12px;">
        <table name="tbchitieu" style="width:100%" border="1" style="border-collapse: collapse;">
            <tr>
                <td colspan="6">
                    <select name="sendtrang" id="sendtrang">
                        <s:iterator begin="1" end="%{pagecount}" status="status">
                            <s:if test="%{#status.count == cbotrang}">
                                <option value="<s:property value="%{#status.count}" />" selected disabled>Trang <s:property value="%{#status.count}" /></option>
                            </s:if>
                            <s:else>
                                <option value="<s:property value="%{#status.count}" />">Trang <s:property value="%{#status.count}" /></option>
                            </s:else>
                        </s:iterator>
                    </select>
                    <input type="button" name="cmdexp" id="cmdexp" value="Xuất Excel"/>
                    <b>Theo điều kiện lọc: Tổng số CT: <s:property value="%{getText('{0,number,#,###.##}',{tongct})}"/></b>
                    <b> - Tổng giá trị: <s:property value="%{getText('{0,number,#,###.##}',{tonggtri})}"/></b>
                </td>
            </tr>
            <tr bgcolor="#336699" style="color: #fff;font-weight: bold;">
                <td>Mã CT</td>
                <td>Tên CT</td>
                <td>Giá Trị</td>
                <td>Ngày BC</td>
                <td>Mã PGD</td>
                <td>Mã CN</td>
            </tr>
            <s:iterator value="lsdata">
                <tr>
                    <td><s:property value="CT_MACT"/></td>
                    <td><s:property value="TENCT"/></td>
                    <td align="right"><s:property value="%{getText('{0,number,#,###.##}',{CT_GIATRI})}"/></td>
                    <td><s:property value="CT_NGAYBC"/></td>
                    <td><s:property value="CT_MAPGD"/></td>
                    <td><s:property value="CT_MACN"/></td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>
