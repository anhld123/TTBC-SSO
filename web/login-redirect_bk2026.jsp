<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html style="height:100%;">
    <head>
        <link href="css/login.css" type="text/css" rel="stylesheet" />
        <link rel="shortcut icon" type="image/x-icon" href="img/Logo_VBSP.ico"/>
        <title>Ngân hàng Chính sách Xã hội VN</title>
        <script type="text/javascript" src="js/jquery-1.4.3.js"></script>
        <style>
            body {
                font-family: Arial, sans-serif;
                text-align: center;
                margin: 0;
                padding: 0;
                background-size: cover;
                background-position: center;
                background-attachment: fixed;
                background-size: cover;
            }

            .header {
                background: url('img/baner11_2025.png') no-repeat left;
                background-size: cover;
                height: 65px;
                position: relative;
                border-bottom: 3px solid #2a9d8f;
                box-shadow: 0 3px 6px rgba(0,0,0,0.15);
            }
            .header-date {
                position: absolute;
                bottom: 5px; right: 10px;
                font: 9pt Tahoma;
                color: #fff;
            }

            .login-modal {
                position: fixed;
                top: 0; left: 0;
                width: 100%; height: 100%;
                display: flex;
                align-items: center;
                justify-content: center;
                z-index: 1000;
            }
            .login-box {
                position: relative;
                padding: 30px 35px;
                border: 2px solid #2a9d8f;
                border-radius: 6px;
                width: 340px;
                text-align: center;
                box-shadow: 0 4px 12px rgba(0,0,0,0.25);
                background: url('img/backgroud_logo.jpg') no-repeat center center;
                background-size: contain;
                background-color: #fff;
            }

            .login-box .login-logo {
                width: 60px;
                margin-bottom: 10px;
            }

            .login-box h3 {
                margin-bottom: 20px;
                font-size: 18px;
                font-weight: 700;
                text-transform: uppercase;
                color: #1e4d2b;
            }
            .form-group {
                text-align: left;
                margin-bottom: 18px;
                font-size: 13px;
                font-weight: bold;
                color: #333;
            }
            .inputsuse, .inputpass {
                width: 100%;
                padding: 10px 12px;
                border: 2px solid #555;
                border-radius: 4px;
                font-size: 14px;
                font-weight: 600;
                color: #222;
                outline: none;
            }
            .inputsuse:focus, .inputpass:focus {
                border-color: #2a9d8f;
            }
            input[type="radio"] {
                accent-color: green;
            }
            #snowCanvas_noel,#snowCanvas_tet {
                position: fixed;
                inset: 0;
                z-index: 20;           /* trên cây thông, dưới login */
                pointer-events: none;
            }

        </style>
        <script>
            const backgrounds = [
                "img/form20261.png"
            ];
            const randomBg = backgrounds[Math.floor(Math.random() * backgrounds.length)];
            document.body.style.backgroundImage = "url('" + randomBg + "')";

        </script>
    </head>

    <body>
        <div class="header">
            <div class="header-date">
                <script>

                    var dt = new Date();
                    var strMonth = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"];
                    var strDay = ["Chủ nhật", "Thứ hai", "Thứ ba", "Thứ tư", "Thứ năm", "Thứ sáu", "Thứ bảy"];
                    var years = dt.getFullYear();
                    var date = strDay[dt.getDay()] + ", ngày " + dt.getDate() +
                            " tháng " + strMonth[dt.getMonth()] + " năm " + years;
                    document.write("<i>" + date + "</i>");
                </script>
            </div>
        </div>
        <s:form action="User_login" theme="simple" id="loginform">
            <div class="login-modal">
                <div class="login-box">

                    <h3 style="display: flex; justify-content: center; align-items: center; gap: 8px; height: 28px;">
                        <img src="img/login2025.png" width="27" height="24" alt="Login Icon">
                        Đăng nhập hệ thống
                    </h3>
                    <div style="font-size:15px; margin-bottom:10px; text-align:center;">
                        <input type="radio" id="js_r1" name="reportGrade" value="1"> Ngân hàng
                        <input type="radio" id="js_r2" name="reportGrade" value="2" checked> Chi nhánh
                        <input type="radio" id="js_r3" name="reportGrade" value="3"> Toàn quốc
                    </div>

                    <div class="form-group">
                        <s:label value="Tên đăng nhập"/>
                        <s:textfield name="username" cssClass="inputsuse"
                                     placeholder="Administrator@vbsp.org.vn"
                                     onchange="js_changeGrade();" id="js_usernameid"/>
                    </div>

                    <div class="form-group">
                        <s:label value="Mật khẩu"/>
                        <s:password name="password" cssClass="inputpass"
                                    placeholder="************"
                                    onkeypress="CheckKey(event)"/>
                    </div>

                    <div style="text-align:right; margin-top:10px;">
                        <a href="javascript:document.getElementById('loginform').submit();">
                            <img src="img/dangnhap.jpg" border="0"/>
                        </a>
                    </div>

                    <div style="color:red; margin-top:10px;">
                        <s:property value="message"/>
                    </div>
                </div>
            </div>
        </s:form>

        <s:set var="st_total" value="grade_static.size()" />                    
        <s:iterator value="grade_static" status="stat">    
            <input type="hidden" value="<s:property value='grade_static' />" 
                   name="js_hd_<s:property value='code' />" 
                   id="js_hd_<s:property value='%{#stat.index+1}' />"/>
        </s:iterator>
        <input type="hidden" name="js_mntotal" 
               value="<s:property value='%{#st_total}'/>" id="js_totalid"/> 


    </body>
</html>
