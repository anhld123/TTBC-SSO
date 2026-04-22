<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html style="height:100%;">
    <head>
        <link href="css/login_1.css" type="text/css" rel="stylesheet" />
        <link rel="shortcut icon" type="image/x-icon" href="img/Logo_VBSP.ico"/>
        <title>Ngân hàng Chính sách Xã hội VN</title>
        <script type="text/javascript" src="js/jquery-1.4.3.js"></script>
    </head>
    <body>

        <div class="container">

            <div class="left">
                
                <s:form action="User_login" theme="simple" id="loginform">
                    
                    <div class="login-box">
                        <img src="img/zzz1.png" class="right-logo" style="width: 30%; height: auto; display: block; margin: 0 auto;">
                        <h2 class="title-main"> 
                            NGÂN HÀNG CHÍNH SÁCH XÃ HỘI</h2>
                        <h3 class="title-sub"> 
                            HỆ THỐNG THÔNG TIN BÁO CÁO </h3>
                        <div class="radio-group">

                            <input type="radio" id="js_r1" name="reportGrade" value="1">
                            <label for="js_r1">Ngân hàng</label>

                            <input type="radio" id="js_r2" name="reportGrade" value="2" checked>
                            <label for="js_r2">Chi nhánh</label>

                            <input type="radio" id="js_r3" name="reportGrade" value="3">
                            <label for="js_r3">Toàn quốc</label>

                        </div>

                        <div class="form-group">
                            <label class="form-label">Tên đăng nhập</label>
                            <div class="input-wrapper">
                                <span class="input-icon">👤</span>
                                <s:textfield name="username"
                                             cssClass="form-input"
                                             placeholder="Administrator@vbsp.org.vn"
                                             onchange="js_changeGrade();"
                                             id="js_usernameid"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Mật khẩu</label>
                            <div class="input-wrapper">
                                <span class="input-icon">🔒</span>
                                <s:password name="password"
                                            cssClass="form-input"
                                            placeholder="Nhập mật khẩu"
                                            onkeypress="CheckKey(event)"/>
                            </div>
                        </div>

                        <div style="margin-top:20px;">
                            <button type="submit" class="btn-login">
                                ĐĂNG NHẬP
                            </button>
                        </div>

                        <s:if test="message != null && message != ''">
                            <div class="alert-error">
                                ⚠ <s:property value="message"/>
                            </div>
                        </s:if>
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
            </div>

            <div class="right">

            </div>

        </div>

    </body>
    <script>
        document.addEventListener("keydown", function (e) {
            if (e.key === "F12" ||
                    (e.ctrlKey && e.key === "u") ||
                    (e.ctrlKey && e.shiftKey && e.key === "I")) {
                e.preventDefault();
            }
        });

        document.addEventListener("contextmenu", function (e) {
            e.preventDefault();
        });
    </script>
</html>