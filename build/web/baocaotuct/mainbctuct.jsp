<%-- 
    Document   : mainbctuct
    Created on : 24-May-2014, 16:14:14
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="sj" uri  ="/struts-jquery-tags" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html style="height:100%;">
    <head>
        <sj:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Báo cáo từ chỉ tiêu</title>
        <script src="js/JavaScriptUtil.js"></script>
        <script src="js/Parsers.js"></script>
        <script src="js/InputMask.js"></script>
        <script src="js/Checkdate.js"></script>
        <script src="js/format_num.js"></script>
        <script src="js/xuly2.js"></script>
        <script language="JavaScript">
            function setup() {
                var dateMask1 = new DateMask("dd/MM/yyyy", "P_Ngaybc");
            }
        </script>
        <script type="text/javascript">
            var checkflag = "false";
            function jscheckall(idpara) {
                var field = document.getElementById(idpara);
                if (checkflag == "false") {
                    for (i = 0; i < field.length; i++) {
                        field[i].checked = true;
                    }
                    checkflag = "true";
                } else {
                    for (i = 0; i < field.length; i++) {
                        field[i].checked = false;
                    }
                    checkflag = "false";
                }
            }
        </script>
        <style type="text/css">
            .fixsize {width: 250px;}
            .ui-datepicker { width: 12em; padding: .2em .2em 0; display: none; }
            .ui-datepicker table {width: 100%; font-size: .7em; border-collapse: collapse; margin:0 0 .4em; }
            body table td tr{
                font-family: Arial;
                font-size: 13px;
            }
        </style>
    </head>
    <body onload="setup();">
        <table border="1" width="100%" style="height: 100%;" >
            <tr>
                <td colspan="2" height="20px" valign="middle">
                    <b>Mẫu BC:</b>
                    <s:select
                        id="idbaocao"
                        name="P_Mabc"
                        list="danhsachbaocao"
                        listKey="MABC"
                        listValue="TENBC"
                        headerKey="-1"
                        headerValue="Chọn báo cáo"
                        theme="simple"
                        cssClass="fixsize"
                        onchange="javascript:getLoaddata(this.value);"
                        />
                    &nbsp;&nbsp;
                    <b>Đơn vị: </b>
                    <s:select
                        id="idmapgd"
                        name="P_Mapgd"
                        list="danhsachdonvi"
                        listKey="MAPGD"
                        listValue="TENPGD"
                        headerKey="-1"
                        headerValue="Chọn đơn vị"
                        theme="simple"
                        />
                    &nbsp;&nbsp;
                    <b>Ngày báo cáo:</b> <sj:datepicker id="idngbaocao" name="P_Ngaybc" label="Ngày báo cáo" theme="simple" placeholder="DD/MM/YYYY" maxlength="10" changeYear="true" changeMonth="true" onblur="validatedate(this.value)" displayFormat="dd/mm/yy" cssClass="cssdate"/>
                    &nbsp;&nbsp;
                    <b>
                        <a href="javascript:genandview();" id="genview">Xem báo cáo</a>&nbsp;|&nbsp;
                        <a href="javascript:download();" id="iddownload">Download Excel File</a>
                        <input type="hidden" id="collect" name="collect" value="">
                    </b>
                </td>
            </tr>
            <tr>
                <td valign="top" align="center">
                    <div id="loaddata" style="float: left; width:100%; height: 100%"><center>Vui lòng chọn báo báo.</center></div>
                </td>
            </tr>
        </table>
    </body>
</html>
