<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style type="text/css">
    /*Phan xu ly text cho readonly*/
    input[type="text"][readonly],
    textarea[readonly] {
        background-color: #cfd1cf;      
    }
</style> 
<p style="text-decoration: underline;padding-left: 5px;font-size: 12pt;font-weight: bold;"> 
    Thay đổi mật khẩu người dùng
</p>
<hr/>
<div id="change_userpwd_form" class="change_password_form"> 
    <s:form action="User_updatepwd" theme="simple" method="post" validate="true">
        <table style="padding: 5px;" cellspacing="5px"> 
            <tr>
                <td><s:label value="Mã người dùng"/></td>
                <td><s:textfield key="userCode" value="%{#session.username}" 
                             size="40" readonly="true"/></td>                
            </tr>
            <tr>
                <td><s:label value="Mật khẩu cũ"/></td>
                <td><s:password label="oldPassword" key="oldPassword" size="40" name="oldPassword"/></td>                
            </tr>
            <tr>
                <td><s:label value="Mật khẩu mới"/></td>
                <td><s:password label="newPassword" key="newPassword" size="40"/></td>
            </tr>
            <tr>
                <td><s:label value="Nhập lại mật khẩu mới"/></td>
                <td><s:password label="confirmPassword" key="confirmPassword" size="40"/></td>
            </tr>            
            <tr>
                <td colspan="2" align="right">
                    <s:submit label="Cập nhật" value="Cập nhật"/>               
                    <input type="button" label="Quay lại" value="Quay lại" onclick="javascript:history.back();"/>
                </td>                
            </tr>        
            <tr>
                <td colspan="2"><s:label key="message" cssStyle="font-family: arial;color: #00f;"/></td>
            </tr>
        </table>
    </s:form>
</div>
