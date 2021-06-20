<%-- 
    Document   : DMChitieu
    Created on : Apr 27, 2014, 10:12:11 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri  ="/struts-jquery-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html style="height: 100%;">
    <head>
        <sx:head />
        <sj:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/table.css" type="text/css"/>
        <script src="js/JavaScriptUtil.js"></script>
        <script src="js/Parsers.js"></script>
        <script src="js/InputMask.js"></script>
        <script src="js/Checkdate.js"></script>
        <script src="js/format_num.js"></script>
        <script language="JavaScript">
            function setup() {
                var dateMask1 = new DateMask("dd/MM/yyyy", "txtngaybc");
            }
            function getQueryParameter(parameterName) {
                var queryString = window.top.location.search.substring(1);
                var parameterName = parameterName + "=";
                if (queryString.length > 0) {
                    begin = queryString.indexOf(parameterName);
                    if (begin != -1) {
                        begin += parameterName.length;
                        end = queryString.indexOf("&", begin);
                        if (end == -1) {
                            end = queryString.length
                        }
                        return unescape(queryString.substring(begin, end));
                    }
                }
                return "null";
            }
        </script>
        <script>
            function Callbaocao() {
                var checkvalue = document.getElementById("checknhcs").checked;
                if(checkvalue){
                    var giatri1 = document.getElementById("idmapgd").value;
                    var giatri2 = document.getElementById("idngaybaocao").value;
                    if(giatri2=="" || giatri2==null){
                        document.getElementById("idngaybaocao").focus();
                        alert("Vui lòng chọn ngày báo cáo.");
                        return;
                    }
                    var thamso = "?mapgd="+giatri1+"&ngaybc="+giatri2;
                    var ht = screen.availHeight;
                    var wt = screen.availWidth;
                    var resize = window.open("checkct.action" + thamso, "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                    if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                        resize.resizeBy(wt, ht);
                    } else {
                        resize.resizeTo(wt, ht);
                    }
                    resize.focus();
                }else{
                    location.href="Menu_redirect.action?menuUrl=rptmanager&menuId=11";
                }
            }
        </script>
        <script src="js/xuly.js"></script>  
        <style type="text/css">
            .ui-datepicker { width: 12em; padding: .2em .2em 0; display: none; }
            .ui-datepicker table {width: 100%; font-size: .7em; border-collapse: collapse; margin:0 0 .4em; }
            body table td tr{
                font-family: Arial;
                font-size: 13px;
            }
        </style>
    </head>
    <body onload="setup();">
        <div>
            <table border ="1" width="100%" style="height: 100%; margin-top: 10px;" style="border-collapse:collapse;">
                <tr>
                    <td valign="middle" height="35px"><font style='font-family: tahoma;font-weight: bold;font-size: 12px;'>
                        <input type="hidden" id="phantrang">
                        <input type="radio" onclick="getCount('NHCSXH')" name="select" id="checknhcs" style="display: none;">
                        <input type="radio" onclick="getCount('NHNN')"  name="select"  id="checknhnn"  style="display: none;">
                <lable>Đơn vị:</lable>
                    <s:url id="remoteurl" action="danhsachPGD"></s:url>
                    <sj:select
                        href="%{remoteurl}"
                        id="idmapgd"
                        name="idmapgd"
                        list="danhsachpgd"
                        headerKey="-1"
                        listKey="MAPGD"
                        listValue="TENPGD"
                        headerValue="Chọn đơn vị"
                        />
                Nhóm chỉ tiêu: <input type="text" name="chitieugoc" value="" id="idmachitieu" style="width:65px;">&nbsp;&nbsp;
                Mã chỉ tiêu: <input type="text" name="chitieu2" value="" id="idsubmachitieu" style="width:85px;"> &nbsp;&nbsp;
                Ngày báo cáo: <sj:datepicker id="idngaybaocao" name="txtngaybc" label="Ngày báo cáo" theme="simple" placeholder="DD/MM/YYYY" maxlength="10" changeYear="true" changeMonth="true" onblur="validatedate(this.value)" displayFormat="dd/mm/yy" cssClass="cssdate"/>&nbsp;&nbsp;
                Kỳ báo cáo: 
                <sj:select
                    href="%{remoteurl}"
                    id="idkybaocao"
                    name="idkybaocao"
                    list="danhsachkybc"
                    headerKey="-1"
                    headerValue="Chọn kỳ BC"
                    listKey="MAKY"
                    listValue="TENKY"
                    />
                <script language="JavaScript">
                    var rpt = getQueryParameter("menuUrl");
                    var menuid = getQueryParameter("menuId");
                    if (rpt == "rpt-bcct" && menuid == "22") {
                        document.getElementById("idkybaocao").disabled = true;
                        document.getElementById("idkybaocao").style.display = "none";
                        document.write("Ngày");
                    }
                </script>
                &nbsp;&nbsp;
                <a href ="javascript:gettruyvan();" style="border: 0;font-family: Tahoma;font-size: 12px;font-weight: bold;">Truy vấn</a>
                <%
                    String reportGrade = (String) request.getSession().getAttribute("reportGrade");
                    String rpt = request.getParameter("menuUrl");
                    String menuid = request.getParameter("menuId");
                    if (!"1".equals(reportGrade) && "rpt-bcct".equals(rpt) && "10".equals(menuid)) {
                        out.print("&nbsp; | &nbsp;");
                        out.print("<a href =\"Menu_redirect.action?menuUrl=exporttext2sbv&menuId=65\" style=\"border: 0;font-family: Tahoma;font-size: 12px;font-weight: bold;\">Xuất Text</a>");
                    };
                %>
                &nbsp; | <a href="javascript:Callbaocao();">Kiểm tra CT</a>
                </font>
                </td>
                </tr>
                <tr>
                    <td valign="top" style="height: 220px;"><div style="height: 220px; overflow: scroll;"><div id="employee"><img src='img/loading.gif' border='0'></div></div></td>
                </tr>
                <tr>
                    <td valign="top" aling="center">
                        <div id="chitieu"></div>
                    </td>
                </tr>
            </table>
        </div>
    </body>
</html>
