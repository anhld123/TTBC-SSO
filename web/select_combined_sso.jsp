<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<%
    String code = request.getParameter("code");
    if (code != null && !code.trim().isEmpty()) {
        session.setAttribute("sso_code", code);
    }
%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Chọn Cấp và Vai Trò Làm Việc - VBSP</title>
        <script>
            var ssoCode = "<%= code != null ? code : ""%>";
            console.log("SSO Code:", ssoCode);
        </script>
        <style>
            * { box-sizing: border-box; margin: 0; padding: 0; }
            html, body { height: 100%; font-family: 'Segoe UI', Tahoma, sans-serif; }
            body {
                background: url('<s:url value="/img/giaodien1.png"/>') no-repeat center center / cover;
                display: flex; justify-content: center; align-items: center;
            }
            .login-card {
                background: rgba(255, 255, 255, 0.95);
                width: 420px; padding: 35px 25px; border-radius: 12px;
                box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15); text-align: center;
                border-top: 4px solid #00563b;
            }
            .card-title { color: #00563b; font-size: 18px; font-weight: 700; text-transform: uppercase; margin-bottom: 6px; }
            .card-subtitle { color: #64748b; font-size: 13px; margin-bottom: 24px; }
            .error-message {
                background: #f8d7da; color: #721c24; padding: 8px 12px;
                border-radius: 6px; font-size: 13px; margin-bottom: 16px; text-align: left;
            }
            .form-group { margin-bottom: 16px; text-align: left; }
            .form-group label { display: block; font-size: 13px; font-weight: 600; color: #334155; margin-bottom: 6px; }
            .select-box {
                width: 100%; padding: 10px 14px; font-size: 14px;
                border: 1px solid #cbd5e1; border-radius: 6px; background: #fff;
                outline: none; transition: 0.2s; cursor: pointer;
            }
            .select-box:focus { border-color: #00563b; box-shadow: 0 0 0 3px rgba(0, 86, 59, 0.1); }
            .btn-submit {
                display: block; width: 220px; margin: 15px auto 0;
                padding: 11px 15px; font-size: 14px; font-weight: 600;
                color: #fff; background: #2563eb; border: none; border-radius: 6px;
                cursor: pointer; transition: background 0.2s;
            }
            .btn-submit:hover { background: #1d4ed8; }
            .card-footer {
                margin-top: 25px; font-size: 11px; color: #94a3b8;
                border-top: 1px solid #f1f5f9; padding-top: 12px;
            }
            .title-sub {
                display: flex; justify-content: center; align-items: center;
                height: 28px; letter-spacing: 1px; color: #e91e63; font-weight: 600;
            }
        </style>
    </head>
    <body>

        <div class="login-card">
            <h3 class="title-sub" style="margin-bottom: 25px;">HỆ THỐNG THÔNG TIN BÁO CÁO</h3>

            <div class="card-title">Thiết Lập Phiên Làm Việc</div>
            <div class="card-subtitle">Vui lòng chọn cấp quản lý và vai trò phù hợp</div>

            <s:if test="hasActionErrors()">
                <div class="error-message"><s:actionerror/></div>
            </s:if>

            <s:form action="selectCombinedAction" method="post">

                <!-- KIỂM TRA ĐÚNG DANH SÁCH USER_LEVELS -->
                <s:if test="#session.USER_LEVELS != null && #session.USER_LEVELS.size() > 1">
                    <div class="form-group">
                        <label>Cấp quản lý (User Level):</label>
                        <select name="selectedUserLevel" class="select-box">                                        
                            <s:iterator value="#session.USER_LEVELS">
                                <option value="<s:property/>">
                                    <s:if test='top == "1"'>1 - Phòng giao dịch</s:if>
                                    <s:elseif test='top == "2"'>2 - Chi nhánh</s:elseif>
                                    <s:elseif test='top == "3"'>3 - Ngân hàng</s:elseif>
                                    <%--<s:else>Cấp <s:property/></s:else>--%>
                                </option>
                            </s:iterator>
                        </select>
                    </div>
                </s:if>
                <s:else>
                    <s:hidden name="selectedUserLevel" />
                </s:else>

                <!-- KIỂM TRA DANH SÁCH USER_ROLES -->
                <s:if test="#session.USER_ROLES != null && #session.USER_ROLES.size() > 1">
                    <div class="form-group">
                        <label>Vai trò hệ thống (Role):</label>
                        <select name="selectedRole" class="select-box">
                            <s:iterator value="#session.USER_ROLES">
                                <option value="<s:property/>"><s:property/></option>
                            </s:iterator>
                        </select>
                    </div>
                </s:if>
                <s:else>
                    <s:hidden name="selectedRole" />
                </s:else>

                <button type="submit" class="btn-submit">
                    <span>Xác nhận & Tiếp tục</span>
                </button>
            </s:form>

            <div style="padding-top: 15px">
                <a href="login-redirect-sso.jsp" style="color: #64748b; text-decoration: none; font-size: 13px;">← Thoát về trang đăng nhập</a>
            </div>

            <div class="card-footer">
                © 2026 Ngân hàng Chính sách xã hội Việt Nam · v1.0.0
            </div>
        </div>

    </body>
</html>