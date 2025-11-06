<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>  
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${param.css}" type="text/css" rel="stylesheet" />
    <title>${param.title}</title>
    <sx:head/>
    <sj:head/>

    <script type="text/javascript">
        $(document).ready(function () {
            $.ajaxSetup({ cache: false });

            // refresh dữ liệu nhưng giữ nguyên vị trí scroll
            setInterval(function () {
                var scrollPos = $(window).scrollTop(); // lưu vị trí hiện tại
                $("#vbspnews").load("vbsp-news.jsp", function () {
                    $(window).scrollTop(scrollPos); // khôi phục sau khi load
                }).fadeIn("slow");
            }, 5000);
        });

        function CheckKey(e) {
            var code = e.keyCode ? e.keyCode : e.which;
            if (code === 13) {
                document.getElementById("loginform").submit();
            }
        }

        function ngaythang() {
            var today = new Date();
            var dd = today.getDate();
            var mm = today.getMonth() + 1;
            var yyyy = today.getFullYear();
            var x = document.getElementById("ngaythang");
            x.innerHTML = "Ngày " + dd + " tháng " + mm + " năm " + yyyy + "&nbsp";
        }
    </script>
</head>
<body style="height:100%;" topmargin="0" leftmargin="0">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" height="65">
    <tr>
        <td colspan="3" height="60"
            style="background-image: url('img/baner11_2025.png'); 
                   background-size: cover; 
                   background-position: left; 
                   background-repeat: no-repeat; 
                   position: relative; 
                   padding: 0;">
            <!-- Chỗ hiển thị ngày -->
           <div style="position: absolute; bottom: 5px; right: 10px; background-color: rgba(0, 100, 0, 0.8); padding: 5px 10px; border-radius: 5px;">
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
                        <!--                </td>                
                                        <td width="25%" bgcolor="" background="img/bgr3.gif" align="right" valign="bottom"
                                            >-->
                        <s:url id="userProfile" value="User_profile.action">
                            <s:param name="userCode" value="#session.username"/>
                        </s:url>
                        <s:url id="changePwd" value="User_changepwd.action">
                            <s:param name="userCode" value="#session.username"/>
                        </s:url>
                        <s:a href="%{userProfile}" style="font-style: italic; font-weight: bold; font-family: Verdana; font-size: 12px; margin-left:5; margin-right:15; margin-top:15; margin-bottom:5">
                            <font color="#FFFFFF">Xin chào, <%= session.getAttribute("username")%> &nbsp;</font>
                        </s:a>   
                            <p align="center" style="margin-top:0; margin-bottom:3px">
                <br>
                <a href="MainPageBlank.action" style="font-family: tahoma; font-size: 12px; color:#FFFFFF;">
                    <img src="img/trangchu.ico" border="0"/> <b>Trang chủ</b>
                </a> |    
                <s:a href="%{changePwd}" style="font-weight:bold; font-family:tahoma; font-size:12px;">
                    <font color="#FFFFFF"><img src="img/lock_2.ico" border="0" width="14" height="14"/> Đổi mật khẩu</font>
                </s:a> | 
                <s:a href="User_logout.action" style="font-weight:bold; font-family:tahoma; font-size:12px;">
                    <font color="#FFFFFF"><img src="img/logout_1.ico" border="0"/> Đăng xuất</font>
                </s:a>
            </div>
        </td>
    </tr>
</table>
<!--    <div id="vbspnews" style="padding:10px;">
         vbsp-news.jsp sẽ load vào đây mà không làm nhảy trang -->
    <!--</div>-->
</body>
</html>
