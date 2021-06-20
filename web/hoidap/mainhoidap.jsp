<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jquery-1.10.0.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            function Callquantri(mpath) {
                var ht = screen.availHeight;
                var wt = screen.availWidth;
                var resize = window.open(mpath, "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                    resize.resizeBy(wt, ht);
                } else {
                    resize.resizeTo(wt, ht);
                }
                resize.focus();
            }
            function Callxem(mpath) {
                var ht = screen.availHeight;
                var wt = screen.availWidth;
                var resize = window.open("xemaction.action?idbv=" + mpath, "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                    resize.resizeBy(wt, ht);
                } else {
                    resize.resizeTo(wt, ht);
                }
                resize.focus();
            }
        </script>
        <style type="text/css">
            table{
                border-style: solid;
                border-collapse: collapse;
                width: 100%;
                font: 12px Arial, Helvetica, sans-serif; 
                line-height: 19px;
            }
            .td{
                padding-left:5px;
            }
            .content:hover{
                background-color: #ffff99;
            }
            hr{
                border: 0;
                height: 1px;
                background: #333;
            }
        </style>
    </head>
    <body>
        <div style="width:100%;float: left;">
            <input type="button" value="Hỏi đáp nhanh của ${tendn}" name="cmdguich" onclick="Callquantri('quantri.action')">
            <hr style="border-style: solid;">
        </div>
        <table border="1" width="100%" cellspacing="0" cellpadding="2" style="border-collapse:collapse;">
            <tr bgcolor="#696969">
                <td width="50%" style="font-weight: bold;color:#fff;width:10%;">ID câu hỏi</td>
                <td style="font-weight: bold;color:#fff;">Tiêu đề</td>
                <td align="center" style="font-weight: bold;color:#fff;;width:6%;">Chức năng</td>
            </tr>
            <s:iterator value="getall" var="chk">
                <tr id="<s:property value="ID"/>">
                    <td><s:property value="ID"/></td>
                    <td><s:property value="TIEUDE"/></td>
                    <td align="center">
                        <input type="button" value="Xem" name="cmdguitrl" onclick="Callxem(<s:property value="ID"/>)">
                    </td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>
