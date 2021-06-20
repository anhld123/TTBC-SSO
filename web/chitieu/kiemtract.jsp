<%-- 
    Document   : kiemtract
    Created on : Oct 16, 2014, 4:16:59 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jquery-1.10.0.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                //Load dữ liệu từ server về và tr
                $("#process").change(function (e) {
                    var pathname = jQuery(location).attr('href');
                    var url = "loaddatachk.action?numpage=" + $("#process").val()+"&"+pathname.substring(pathname.indexOf("?")+1);
                    $('#viewdata').html('loading...');
                    $.post(url, function (data) {
                        $('#viewdata').html(data);
                    });
                });
                $("#excheckct").click(function (e) {
                    var pathname = jQuery(location).attr('href');
                    location.href = "excheckct.action?"+pathname.substring(pathname.indexOf("?")+1);
                });
            });
        </script>
        <style type="text/css">
            tr th{
                font-family: tahoma;
                font-size: 11px;
            }
        </style>
    </head>
    <body>
        <h1>Kiểm tra chênh lệch chỉ tiêu (<% String ngaybc = request.getParameter("ngaybc");
            out.print(ngaybc);%>)</h1>
        <div id="viewdata" name="viewdata">
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
                        <td><s:property value="%{getText('{0,number,#,###.##}',{VALUE_1})}"/></td>
                        <td><s:property value="%{getText('{0,number,#,###.##}',{VALUE_2})}"/></td>
                        <td><s:property value="%{getText('{0,number,#,###.##}',{DIFFER_AMT})}"/></td>
                    </tr>
                </s:iterator>
            </table>
        </div>
        <br> 
        <b>Trang:</b>
        <select name="process" id="process">
            <s:iterator begin="1" end="%{pgtotal}" status="status">
                <option value="<s:property value="%{#status.count}" />">Trang: <s:property value="%{#status.count}" /></option>
            </s:iterator>
        </select>
        <input type="button" value="Xuất Excel" id="excheckct" name="excheckct">
    </body>
</html>
