<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style type="text/css">
    /*Phan xu ly text cho readonly*/
    input[type="text"][readonly],
    textarea[readonly] {
        background-color: #aaffff;
    }
</style>
<p style="text-decoration: underline;padding-left: 5px;font-size: 12pt;font-weight: bold;"> 
    Cập nhật thông tin người dùng
</p>
<hr/>
<div id="edit_usergroup_form" class="user_form"> 
    <s:form action="User_profile_update" theme="simple">
        <table style="padding: 5px;" cellspacing="5px">            
            <tr>
                <td><s:label value="Mã"/></td><td>
                    <s:textfield key="priUserCode" readonly="true" size="35"/></td>
                <td><s:label value="Tên"/></td><td><s:textfield key="priUserName" size="35"/></td>
            </tr>
            <tr>
                <td><s:label value="Địa chỉ"/></td><td><s:textfield key="priAddress" size="35"/></td>
                <td><s:label value="Mobile"/></td><td><s:textfield key="priMobile" size="35"/></td>
            </tr>
            <tr>
                <td><s:label value="Chức vụ"/></td>
                <td><s:textfield key="priOffice" size="35"/></td>
            </tr>
            <tr>
                <td><s:label value="Nhóm người dùng"/>
                </td><td><s:textfield key="priUserGroup" size="35" readonly="true"/></td>
            </tr>
            <tr>
                <td><s:label value="Cấp báo cáo"/></td>
                <td><s:textfield key="priRptGrade"  readonly="true" size="35"/></td>
                <td><s:label value="Mã đơn vị"/></td>
                <td><s:textfield key="priPosCode" readonly="true" size="35"/></td>
            </tr>
            <tr>
                <td colspan="4" align="right"><s:submit label="Cập nhật" value="Cập nhật"/>
                    &nbsp;<input type="button" label="Quay lại" value="Quay lại" onclick="javascript:history.back();"/></td>               
            </tr>                    
        </table>
        <s:hidden name="updateType" value="2" />
    </s:form>
</div>
