<%-- 
    Document   : DMCtieu
    Created on : Oct 21, 2014, 3:46:01 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        ${FInclude}
        <link rel="stylesheet" href="DMChitieu/css/zTreeStyle.css" type="text/css">
        <link rel="stylesheet" href="DMChitieu/css/jquery-ui.css" type="text/css">
        <script type="text/javascript" src="DMChitieu/js/jquery-1.4.4.min.js"></script>
        <script type="text/javascript" src="DMChitieu/js/jquery.ztree.core-3.5.js"></script>
        <script type="text/javascript" src="DMChitieu/js/jquery-ui.js"></script>
        <script type="text/javascript" src="DMChitieu/js/jquery.session.js"></script>
        <script src="js/JavaScriptUtil.js"></script>
        <script src="js/Parsers.js"></script>
        <script src="js/InputMask.js"></script>
        <script src="js/Checkdate.js"></script>
        <script src="js/format_num.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                <%
                    if (session.getAttribute("reportGrade").equals("1")) {
                %>
                    document.getElementById("listdonvi").checked = true;
                    document.getElementById("listdonvi").disabled = true;
                    document.getElementById("chktonghop").disabled = true;
                    document.getElementById("cmdchonall").disabled = true;
                <% } %>
                $("#cmdquery").click(function (e) {
                    var chekdate = document.getElementById("txtngaybc").value;
                    if(chekdate.trim()!=""){
                        <%
                            if (session.getAttribute("reportGrade").equals("1")) {
                        %>
                            var checkedItemsAsString = $('input[name=listdonvi]:checked').map(function() { return $(this).val().toString(); } ).get().join(",");
                            document.getElementById("cbodonvi").value = checkedItemsAsString; 
                        <% } else { %>    
                        if(document.getElementById("chktonghop").checked){
                            document.getElementById("cbodonvi").value ="ALL";
                        }else{
                            var checkedItemsAsString = $('input[name=listdonvi]:checked').map(function() { return $(this).val().toString(); } ).get().join(",");
                            document.getElementById("cbodonvi").value = checkedItemsAsString;
                        }
                        <%}%>
                        $('#showdata').html('<img src="img/loading.gif"/>');
                        document.getElementById("cbotrang").value = 1;
                        var url, sdata;
                        url = "excloact.action";
                        sdata = jQuery("#mainchitieu").serialize();
                        $.post(url, sdata, function (data) {
                            $("#showdata").html(data);
                        });
                    }else{
                        alert("Vui lòng chọn ngày báo cáo.");
                        document.getElementById("txtngaybc").focus();
                        return
                    }
                });
            });
        </script>
        <script type="text/javascript">
            function setup() {
                var dateMask1 = new DateMask("dd/MM/yyyy", "txtngaybc");
            }
            function Callbaocao() {
                var checkvalue = document.getElementById("txtnganhang").value;
                if (checkvalue == "NHCS") {
                    var giatri1 = document.getElementById("cbodonvi").value;
                    var giatri2 = document.getElementById("txtngaybc").value;
                    if (giatri2 == "" || giatri2 == null) {
                        document.getElementById("txtngaybc").focus();
                        alert("Vui lòng chọn ngày báo cáo.");
                        return;
                    }
                    var thamso = "?mapgd=" + giatri1 + "&ngaybc=" + giatri2;
                    var ht = screen.availHeight;
                    var wt = screen.availWidth;
                    var resize = window.open("checkct.action" + thamso, "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                    if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                        resize.resizeBy(wt, ht);
                    } else {
                        resize.resizeTo(wt, ht);
                    }
                    resize.focus();
                } else {
                    location.href = "Menu_redirect.action?menuUrl=rptmanager&menuId=11";
                }
            }
            $(function () {
                $("#txtngaybc").datepicker({
                    dateFormat: 'dd/mm/yy',
                    changeMonth: true,
                    changeYear: true,
                    showOn: "button",
                    buttonImage: "DMChitieu/css/img/Calendar.gif",
                    buttonImageOnly: true,
                    buttonText: "Chọn ngày",
                    showAnim:"slideDown"
                });
            });
        </script>
        <script type="text/javascript">
            var checkflag = "false";
            function check(field) {
              if (checkflag == "false") {
                for (i = 0; i < field.length; i++) {
                  field[i].checked = true;
                }
                checkflag = "true";
                return "Bỏ chọn toàn bộ";
              } else {
                for (i = 0; i < field.length; i++) {
                  field[i].checked = false;
                }
                checkflag = "false";
                return "Chọn toàn bộ";
              }
            }
        </script>
    </head>
    <body onload="setup();">
        <form name="mainchitieu" id="mainchitieu">
            <table border="1" cellpacing="0" cellpading="0" style="width: 100%;">
                <tr style="height:50%; vertical-align: middle;">
                    <td>Tổng hợp: <input type="checkbox" name="chktonghop" id="chktonghop" checked> &nbsp;|&nbsp;<input type=button value="Chọn toàn bộ" onClick="this.value = check(this.form.listdonvi)" style="border: 0px;background-color: transparent;color: #900;font-weight: bold;" id="cmdchonall"></td>
                    <td>
                        Mã CT: <input type="text" name="txtmact" id="txtmact" value='1A'/>
                        Ngày BC: <input type="text" name="txtngaybc" id="txtngaybc" value=""/>
                        <script>
                            var Dnow = new Date();
                            var giatri = (Dnow.getDate() - 1) + "/" + (Dnow.getMonth()+1) + "/" + Dnow.getFullYear();
                            document.getElementById("txtngaybc").value = giatri;
                        </script>
                        Kỳ BC: 
                        <select name="cbokybc" id="cbokybc">
                            <s:iterator value="lskybc">
                                <option value="<s:property value="KYBC"/>"><s:property value="TENKYBC"/></option>
                            </s:iterator>
                        </select>
                        <input type="button" name="cmdquery" id="cmdquery" value="Truy vấn"/>
                        <input type="button" name="cmdkiemtra" id="cmdkiemtra" value="Kiểm tra CT" onclick="Callbaocao();"/>
                        <%
                            String loainh = (String) request.getAttribute("nganhang");
                            String loaicap = (String) session.getAttribute("reportGrade");
                            if ("NHNN".equals(loainh) && !"1".equals(loaicap)) {
                        %>
                        <input type="button" name="cmdextext" id="cmdextext" value="Xuất text" onclick="location.href = 'Menu_redirect.action?menuUrl=exporttext2sbv&menuId=65'"/>
                        <%}%>
                        <input type="hidden" name="txtnganhang" id="txtnganhang" value="<s:property value="nganhang"/>"/>
                        <input type="hidden" name="cbotrang" id="cbotrang" value="<s:property value="cbotrang"/>"/>
                    </td>
                </tr>
                <tr style="height:19%;">
                    <td>
                        <div style="overflow: scroll;overflow-x: hidden;;height:350px;">
                            <form name="listmapgd">
                            <table>
                              <tr><td>
                                <input type="hidden" name="cbodonvi" id="cbodonvi" value=""/>
                                <s:iterator value="lsdonvi">
                                    <input type="checkbox" name="listdonvi" id="listdonvi" value="<s:property value="MADV"/>"><s:property value="TENDV"/><br>
                                </s:iterator>
                              </td></tr>
                            </table>
                            </form>
                        </div>
                    </td>
                    <td>
                        <div style="overflow: scroll;height:350px;">
                            <ul id="treechitieu" class="ztree"></ul>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><div name="showdata" id="showdata"></div></td>
                </tr>
            </table>
        </form>
    </body>
</html>
