<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/jquery-1.10.0.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            function Callgui(mpath) {
                var ht = screen.availHeight;
                var wt = screen.availWidth;
                var resize = window.open("hoidap/"+mpath, "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
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
                var resize = window.open(mpath, "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                    resize.resizeBy(wt, ht);
                } else {
                    resize.resizeTo(wt, ht);
                }
                resize.focus();
            }
            function Callsua(mpath) {
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
            function Calltraloi(mpath) {
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
            function Callxoa(getid) {
                var url = "xoabai.action?idbv=" + getid;
                $.post(url, function () {
                    $("#"+getid).fadeOut(500);
                });
            };
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
        <div style="text-align:right;width:100%;float: left;">
            <s:if test="tendn != 'ADMIN'">
                <input type="button" value="Gửi câu hỏi" name="cmdguich" onclick="Callgui('gui.jsp')">
            </s:if>
            <hr style="border-style: solid;">
        </div>
        <table border="1" width="100%" cellspacing="0" cellpadding="2" style="border-collapse:collapse;">
            <tr bgcolor="#696969">
                <td width="50%" style="font-weight: bold;color:#fff;width:10%;">ID câu hỏi</td>
                <td style="font-weight: bold;color:#fff;">Tiêu đề</td>
                <td style="font-weight: bold;color:#fff;;width:15%;">Chức năng</td>
            </tr>
            <s:iterator value="getquantri" var="chk">
                <tr id="<s:property value="ID"/>">
                    <td><s:property value="ID"/></td>
                    <td><s:property value="TIEUDE"/></td>
                    <td align="right">
                        <input type="button" value="Xem" name="cmdguitrl" onclick="Callxem(<s:property value="ID"/>)">
                        <s:if test="#chk.TENDN == tendn">
                            <input type="button" value="Sửa" id="cmdsua" name="cmdsua" onclick="Callsua(<s:property value="ID"/>);">
                            <input type="button" value="Xóa" id="cmdxoa" name="cmdxoa" onclick="Callxoa(<s:property value="ID"/>);">
                        </s:if>
                        <s:if test="tendn == 'ADMIN'">
                            <input type="button" value="Sửa" id="cmdsua" name="cmdsua" onclick="Callsua('suaaction.action');">
                            <input type="button" value="Xóa" id="cmdxoa" name="cmdxoa" onclick="Callxoa(<s:property value="ID"/>);">
                            <input type="button" value="Trả lời" id="cmdtraloi" name="cmdtraloi" onclick="Calltraloi('traloiaction.action');">
                        </s:if>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>
