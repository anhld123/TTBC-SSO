<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<s:head/>

<html style="height:100%;">
    <head>
        <link href="css/login.css" type="text/css" rel="stylesheet" />
        <link rel="shortcut icon" type="image/x-icon" href="img/Logo_VBSP.ico"/>
        <title>Ngân hàng chính sách xã hội VN</title>
        <script type="text/javascript" src="js/jquery-1.4.3.js"></script>
        <script type="text/javascript">
            $(document).ready(function()
            {
                $.ajaxSetup({
                    // Disable caching of AJAX responses */
                    cache: false
                });

                var refreshId = setInterval(function()
                {
                    $("#vbspnews").load('vbsp-news.jsp').fadeIn("slow");
                }, 5000);
            });
            function CheckKey(e)
            {
                var code = e.keyCode ? e.keyCode : e.which;
                if (code === 13) {
                    document.getElementById("loginform").submit();
                }
            }
            function ngaythang()
            {
                var today = new Date();
                var dd = today.getDate();
                var mm = today.getMonth() + 1;
                var yyyy = today.getFullYear();
                var x = document.getElementById("ngaythang");
                x.innerHTML = "Ngày " + dd + " Tháng " + mm + " năm " + yyyy + "&nbsp;";
            }
            function js_changeGrade() {
                var jo_user = document.getElementById("js_usernameid").value;
                var ji_usertotal = document.getElementById("js_totalid").value;
                for (i = 1; i <= ji_usertotal; i++) {
                    var js_hidden_id = "js_hd_" + i.toString();
                    var js_hidden_obj = document.getElementById(js_hidden_id);
                    var js_searchusr = js_hidden_obj.name.toString().substr(6);
                    var js_grade = parseInt(js_hidden_obj.value);
                    if (jo_user === js_searchusr){                        
                        if (js_grade === 1)
                            document.getElementById("js_r1").checked = true;
                        else if (js_grade === 2)
                            document.getElementById("js_r2").checked = true;
                        else
                            document.getElementById("js_r3").checked = true;                        
                    }
                }
//                alert('aaa');
            }
        </script>
    </head>
    <body style="height:100%;" topmargin="0" leftmargin="0">
        <s:form action="User_login" theme="simple" id="loginform">
            <table width="100%" border="0"  cellpadding="0" cellspacing="0" style="height:100%;">
                <td width="50%" height="60" bgcolor="#017230" align="left" valign="top"> <img border="0" src="img/banner.gif" width="444" height="60"></td>
                <td width="5%" bgcolor="#017230" valign="top" height="60" align="right">                    
                    <img border="0" src="img/bgr1.gif" width="50" height="60"></td>
                <td width="20%" bgcolor="#017230" valign="bottom" background="img/bgr2.gif" height="60">
                    <p align="center" style="margin-top:0; margin-bottom:0">&nbsp;
                    <p align="center" style="margin-top:0; margin-bottom:3px">
                        <script language="javascript">
                            var dt = new Date();
                            var strMonth = new Array(" 1", " 2", " 3", " 4", " 5", " 6", " 7", " 8", " 9", " 10", " 11", " 12");
                            var strDay = new Array("Chủ nhật", "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy");
                            var date = strDay[dt.getDay()] + ", ngày ";
                            var years = dt.getYear();
                            if (years < 1900)
                                years += 1900;
                            date += dt.getDate() + " tháng " + strMonth[dt.getMonth()] + " năm " + years;
                            document.write("<i><font face='Tahoma' style='font-size: 9pt' color='#FFFFFF'>" + date + '</i>&nbsp;');
                        </script>
                </td>                
                <td width="25%" bgcolor="#017230" background="img/bgr3.gif" align="right" valign="bottom" height="60">&nbsp;            
                </td>
                <tr>
                    <td height="26">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>

                <tr>
                    <td height="28" colspan="4">
                        <table width="100%" height="100%" border="0"  cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="5%" height="30"><img src="img/logo.jpg" width="27" height="27" /></td>
                                <td width="95%" align="left" style="text-transform: uppercase;font-size: 12px;font-weight: bold;">
                                    Thông báo mới <br><hr height="1px" width="90%" align="left"><br>
                                </td>

                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                                <td align="center">
                                    <table border="0"  cellpadding="0" cellspacing="0" width="325px">
                                        <tr>
                                            <td height="28" align="left"><img src="img/user.jpg" width="27" height="24" /></td>
                                            <td align="left" style="text-transform: uppercase;font-size: 12px;font-weight: bold;">Đăng nhập hệ thống <br><hr height="1px"></td>
                                        </tr>
                                    </table></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>                    
                    <td height="100%" valign="top"><table border="0"  cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="5%"></td>
                                <td align="left" style="font-size: 12px;" width="90%">
                                    <div id="vbspnews"></div>
                                </td>
                            </tr>
                        </table></td>
                    <td>&nbsp;</td>    
                    <td><img src="img/linelogin.jpg" width="188" height="330" /></td>
                    <td valign="top" align="center">
                        <table border="0"  cellspacing="10" cellpadding="0" width="310px">
                            <tr>
                                <td style="font-size: 12px;">
                                    <input type="radio" id="js_r1" name="reportGrade" value="1" > Ngân hàng
                                    <input type="radio" id="js_r2" name="reportGrade" value="2" checked> Chi nhánh
                                    <input type="radio" id="js_r3" name="reportGrade" value="3"> Toàn quốc
                                </td>
                            </tr>
                            <tr>
                                <td align="left" style="font-size: 12px;font-weight: bold;">
                                    <s:label value="Tên đăng nhập"/><br>
                                    <s:textfield name="username" cssClass="inputsuse" placeholder="Administrator@vbsp.org.vn"
                                                 onchange="js_changeGrade();" id="js_usernameid"/>
                                </td>
                            </tr>
                            <tr>
                                <td align="left" style="font-size: 12px;font-weight: bold;">
                                    <s:label value="Mật khẩu"/><br>
                                    <s:password name="password" cssClass="inputpass" placeholder="************"  onkeypress="CheckKey(event)"/></td>
                            </tr>
                            <tr>
                                <td>&nbsp;</td>
                            </tr>
                            <tr>
                                <td align="right"><a href='javascript:document.getElementById("loginform").submit();'><img src="img/dangnhap.jpg" border="0"/></a></td>
                            </tr>
                            <td>&nbsp;</td>
                </tr>
                <tr>
                <div style="color: red"><p style="color: red"><s:property value="message" /> </p></div>
            </tr>
        </table>
    </td>
</tr>

</table>
</s:form>

<!-- Phần bổ sung để tạo dữ liệu -->
<s:set var="st_total" value = "grade_static.size()" />                    
<s:iterator value="grade_static" status="stat">    
    <input type="hidden" value="<s:property value='grade_static' />" name="js_hd_<s:property value='code' />" 
           id="js_hd_<s:property value='%{#stat.index+1}' />"/>
</s:iterator>
<input type="hidden" name="js_mntotal" 
       value="<s:property value='%{#st_total}'/>" id="js_totalid"/> 
</body>
</html>