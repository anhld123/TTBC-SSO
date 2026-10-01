<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<html style="height:100%;">
    <head>
        <link href="css/login_1.css" type="text/css" rel="stylesheet" />
        <link rel="shortcut icon" type="image/x-icon" href="img/Logo_VBSP.ico"/>
        <title>Ngân hàng Chính sách Xã hội VN</title>
        <script type="text/javascript" src="js/jquery-1.4.3.js"></script>
        <script type="text/javascript" src="js/jquery-2.0.26.js"></script>
    </head>
    <body>

        <div class="container">

            <div class="left">

                <s:form theme="simple" id="loginform">
                    <div class="login-box" style="text-align: center; padding: 20px;height: 50%">
                        <img src="img/zzz1.png" class="right-logo" style="width: 30%; height: auto; display: block; margin: 0 auto 15px auto;">
                        <h2 class="title-main">NGÂN HÀNG CHÍNH SÁCH XÃ HỘI</h2>
                        <h3 class="title-sub" style="margin-bottom: 30px;">HỆ THỐNG THÔNG TIN BÁO CÁO</h3>

                        <div style="margin-top: 20px; text-align: center">
                            <a id="btnSsoLogin" href="#" 
                               style="display: inline-block; padding: 12px 28px; background-color: #28a745; 
                               color: white; text-decoration: none; border-radius: 25px; cursor: pointer; 
                               font-size: 16px; font-weight: bold; text-align: center; box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);">
                                <!-- Icon Loading (ban đầu ẩn) -->
                                <span id="loadingIcon" style="display: inline-block; width: 14px; height: 14px; border: 2px solid white; border-top-color: transparent; border-radius: 50%; animation: spin 0.8s linear infinite; margin-right: 8px; vertical-align: middle;"></span>
                                <span id="btnText">ĐĂNG NHẬP SSO</span>
                            </a>
                        </div>
                        <div style="width: 100%; border-top: 1px solid #eee; padding-top: 15px; text-align: left; font-size: 12px; color: #666;">
                            <p style="margin: 0 0 5px 0; font-weight: bold; color: #444;">Lưu ý khi đăng nhập:</p>
                            <ul style="margin: 0; padding-left: 18px; line-height: 1.5;">
                                <li>Sử dụng tài khoản nội bộ do Ngân hàng cấp để đăng nhập.</li>
                                <li>Liên hệ Trung tâm Công nghệ thông tin nếu gặp sự cố đăng nhập.</li>
                            </ul>
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
            </div>

            <div class="right">
                <div class="update-header">
                    <h2 style="color: green">THÔNG BÁO CẬP NHẬT</h2>
                    <div class="update-list" id="updateList"></div>
                </div>

            </div>
        </div>
    </body>
    <script>
        window.addEventListener("DOMContentLoaded", function () {
            const btn = document.getElementById('btnSsoLogin');
            const loadingIcon = document.getElementById('loadingIcon');

            // Trạng thái ban đầu: Đang tải -> Vô hiệu hóa click tạm thời
            btn.style.opacity = "0.8";
            btn.style.cursor = "wait";
            btn.addEventListener("click", preventClick);

            fetch('getSsoConfig.action?_t=' + new Date().getTime())
                    .then(response => response.json())
                    .then(data => {
                        if (data && data.url) {
                            btn.href = data.url;
                        } else {
                            console.error("Không lấy được URL SSO từ server.");
                        }
                    })
                    .catch(error => {
                        console.error('Lỗi gọi API cấu hình SSO:', error);
                    })
                    .finally(() => {
                        // Khi hoàn tất (dù thành công hay lỗi), ẩn icon loading và khôi phục nút
                        loadingIcon.style.display = 'none';
                        btn.style.opacity = "1";
                        btn.style.cursor = "pointer";
                        btn.removeEventListener("click", preventClick);
                    });
        });

        // Hàm ngăn chặn click khi chưa có URL
        function preventClick(e) {
            e.preventDefault();
        }
    </script>
<!--    <script>
        window.addEventListener("DOMContentLoaded", function () {
            const btn = document.getElementById('btnSsoLogin');
            const loadingIcon = document.getElementById('loadingIcon');

            // Trạng thái ban đầu: Đang tải -> Vô hiệu hóa click tạm thời
            btn.style.opacity = "0.8";
            btn.style.cursor = "wait";
            btn.addEventListener("click", preventClick);

            fetch('getSsoConfig.action?_t=' + new Date().getTime())
                    .then(response => response.json())
                    .then(data => {
                        if (data && data.url) {
                            btn.href = data.url;

                            // TỰ ĐỘNG CHUYỂN HƯỚNG SANG SSO NGAY KHI VÀO APP:
                            // Vì đã gỡ bỏ prompt=login, nếu người dùng đã đăng nhập SSO trước đó,
                            // hệ thống SSO sẽ tự động cấp code và trả về `beforeLogin_Proccess.action` mượt mà.
                            window.location.href = data.url;

                        } else {
                            console.error("Không lấy được URL SSO từ server.");
                        }
                    })
                    .catch(error => {
                        console.error('Lỗi gọi API cấu hình SSO:', error);
                    })
                    .finally(() => {
                        // Ẩn icon loading nếu chuyển hướng chưa kịp diễn ra ngay
                        loadingIcon.style.display = 'none';
                        btn.style.opacity = "1";
                        btn.style.cursor = "pointer";
                        btn.removeEventListener("click", preventClick);
                    });
        });

        // Hàm ngăn chặn click khi chưa có URL
        function preventClick(e) {
            e.preventDefault();
        }
    </script>-->
</html>