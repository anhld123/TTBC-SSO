<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${param.css}" type="text/css" rel="stylesheet" />
        <link href="../css/css/style.css" rel="stylesheet" type="text/css"/>
        <title> ${param.title}</title>
        <sx:head/>
        <sj:head/>
<!--        <script type="text/javascript" src="js/jquery-1.4.3.js"></script>-->
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
                x.innerHTML = "Ngày " + dd + " tháng " + mm + " năm " + yyyy + "&nbsp";
            }
        </script>
    </head>
    <body style="height:100%;"  topmargin="0" leftmargin="0">
        <table width="100%" border="0" cellspacing="0" cellpadding="0" height = "60">
            <tr>
                <td width="50%" height="60" bgcolor="#017230" align="left" valign="bottom">
                    <img border="0" src="img/banner.gif" width="444" height="60"></td>
                <td width="5%" bgcolor="#017230" valign="bottom" height="60">
                    <p align="right" style="margin-top: 0; margin-bottom: 0" border="0" >
                    <img border="0" src="img/bgr1.gif" width="50" height="60"></td>
                <td width="20%" bgcolor="#017230" valign="bottom" background="img/bgr2.gif">
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
                            document.write("<i><font face='Tahoma' style='font-size: 9pt;' color='#FFFFFF'>" + date + '</i>&nbsp;');
                        </script>
                </td>                
                <td width="25%" bgcolor="" background="img/bgr3.gif" align="right" valign="bottom"
                    >
                    <s:url id="userProfile" value="User_profile.action">
                        <s:param name="userCode" value="#session.username"/>
                    </s:url>
                    <s:url id="changePwd" value="User_changepwd.action">
                        <s:param name="userCode" value="#session.username"/>
                    </s:url>
                    <s:a href="%{userProfile}" style="font-style: italic; font-weight: bold; font-family: Verdana; font-size: 12px; margin-left:5; margin-right:15; margin-top:15; margin-bottom:5">
                        <font color="#000000">Xin chào, <%= session.getAttribute("username")%> &nbsp;</font>
                    </s:a>                   
                    <p align="right" style="margin-left:5; margin-right:5; margin-top:15; margin-bottom:0">   
                        <s:url id="userProfile" value="User_profile.action">
                            <s:param name="userCode" value="#session.username"/>
                        </s:url> 
                    <a href="MainPageBlank.action" style="font-family: tahoma; font-size: 12px;"><font color="#FFFFFF"><img src="img/trangchu.ico" border="0"/><b> Trang chủ </b></font></a>  |    
                    <s:a href="%{changePwd}" style="font-weight: bold;font-family: tahoma; font-size: 12px;"><font color="#FFFFFF"><img src="img/lock_2.ico" border="0" width="14" height="14"/>&nbsp; Đổi mật khẩu </s:a></font> | 
                    <s:a href="User_logout.action" style="font-weight: bold;font-family: tahoma; font-size: 12px;"><font color="#FFFFFF"><img src="img/logout_1.ico" border="0"/>&nbsp; Đăng xuất &nbsp;</s:a></font>
                    <s:url id="userProfile" value="User_profile.action">
                        <s:param name="userCode" value="#session.username"/>
                    </s:url> 
                </td>                                     
            </tr>  
        </table>
    </body>
</html>

