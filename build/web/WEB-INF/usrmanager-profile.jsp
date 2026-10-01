<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style type="text/css">
    /* Style cho các ô nhập liệu chỉnh sửa */
    input[type="text"] {
        background-color: #ffffff;
        border: 1px solid #80bdff;
        padding: 5px 8px;
        border-radius: 4px;
        width: 100%;
        box-sizing: border-box;
    }
    input[type="text"]:focus {
        border-color: #0056b3;
        outline: none;
        box-shadow: 0 0 5px rgba(0, 123, 255, 0.3);
    }
    .user_form table td {
        padding: 6px;
        vertical-align: middle;
    }
</style>

<p style="text-decoration: underline; padding-left: 5px; font-size: 12pt; font-weight: bold;">
    Thông tin người dùng
</p>
<hr/>

<div id="edit_usergroup_form" class="user_form">    
    <!-- Action trỏ đến API lưu lại thông tin -->
    <s:form action="User_profile_update" method="POST" theme="simple">
        <table style="width: 100%; max-width: 850px;" cellspacing="0" cellpadding="0">            
            <tr>
                <td style="width: 15%;"><s:label value="Mã đăng nhập"/></td>
                <td style="width: 35%;">
                    <!-- Mã định danh thường giữ nguyên (readonly) để định danh user, hoặc bỏ readonly nếu bạn cho phép đổi mã -->
                    <s:textfield name="username" value="%{#session.USER_SESSION.preferred_username}" readonly="true"/>
                </td>
                <td style="width: 15%;"><s:label value="Họ và tên"/></td>
                <td style="width: 35%;">
                    <s:textfield name="fullName" value="%{#session.USER_SESSION.name}" placeholder="Nhập họ tên..." readonly="true"/>
                </td>
            </tr>
            <tr>
                <td><s:label value="Mobile"/></td>
                <td>
                    <s:textfield name="phoneNumber" value="%{#session.USER_SESSION.phone_number}" placeholder="Nhập số điện thoại..." readonly="true"/>
                </td>
            </tr>
            <tr>
                <td><s:label value="Chức vụ"/></td>
                <td>
                    <!-- Cho phép thay đổi chức vụ -->
                    <s:textfield name="positionName" value="%{#session.USER_SESSION.position_name}" placeholder="Nhập chức vụ..." readonly="true"/>
                </td>
                <td><s:label value="Nhóm người dùng"/></td>
                <td>
                    <!-- Cho phép thay đổi nhóm người dùng -->
                    <s:textfield name="role" value="%{#session.USER_SESSION.role}" placeholder="Nhập nhóm người dùng..." readonly="true"/>
                </td>
            </tr>
            <tr>
                <td><s:label value="Cấp báo cáo"/></td>
                <td>
                    <!-- Cho phép thay đổi cấp báo cáo -->
                    <s:textfield name="reportLevel" value="%{#session.USER_SESSION.user_level}" placeholder="Nhập cấp báo cáo..." readonly="true" />
                </td>
                <td><s:label value="Mã đơn vị"/></td>
                <td>
                    <!-- Cho phép thay đổi mã đơn vị -->
                    <s:textfield name="posCode" value="%{#session.USER_SESSION.pos_code}" placeholder="Nhập mã đơn vị..." readonly="true" />
                </td>
            </tr>
            <tr>
                <td colspan="4" align="right" style="padding-top: 20px;">
                    <!-- Nút submit gọi API lưu -->
                    <%--<s:submit value="Lưu thay đổi" cssStyle="background-color: #007bff; color: white; border: none; padding: 7px 18px; border-radius: 4px; cursor: pointer; font-weight: bold;"/>--%>
                    &nbsp;
                    <input type="button" value="Quay lại" onclick="window.location.href='homeAction.action';" style="padding: 7px 15px; border-radius: 4px; border: 1px solid #ccc; background-color: #f8f9fa; cursor: pointer;"/>
                </td>
            </tr>            
        </table>
        <!-- Hidden field phân loại loại cập nhật -->
        <s:hidden name="updateType" value="2" />
    </s:form>
</div>