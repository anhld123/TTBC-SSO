<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<script>
    function openSelectWindow() {
        var ht1 = screen.availHeight - 100;
        var wt1 = 600;
        var left1 = (screen.width / 2) - (wt1 / 2);
        var top1 = 10;
        var privileage_id = document.getElementById('privileage_id');
        window.open('selectPrivi4UserGroup.action?privileageStr=' + privileage_id.value, 'IMS_REPORTS',
                "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1
                + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
    }
    
    function openOwnerReports() {
        var ht1 = screen.availHeight - 100;
        var wt1 = 600;
        var left1 = (screen.width / 2) - (wt1 / 2);
        var top1 = 10;
        var privileage_id = document.getElementById('UserGroup_update_priGroupCode');
        window.open('selectOwnerReports.action?userGroupCode=' + privileage_id.value, 'IMS_REPORTS',
                "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1
                + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
    }
</script>
<style type="text/css">
    /*Phan xu ly text cho readonly*/
    input[type="text"][readonly],
    textarea[readonly] {
        background-color: #cfd1cf;
    }
    .fullcell {
        width: 100%;
        box-sizing: border-box;
    }
    .usergroup_form {
        padding-left: 5px;
    }
</style>
<div style="padding-left: 5px;">
    <p style="text-decoration: underline;font-size: 12pt;font-weight: bold;"> 
        Cập nhật thông tin Nhóm người dùng
    </p>
    <hr/>
    <div id="edit_usergroup_form" class="usergroup_form" > 
        <s:form action="UserGroup_update" theme="simple">
            <table cellspacing="5px" width="100%">
                <tr>
                    <td style="width:20%">
                        <s:label value="Nhóm báo cáo"/>
                    </td>
                    <td style="width:60%">
                        <s:textfield label="Nhóm" key="priGroupCode" readonly="true" cssClass="fullcell"/> 
                    </td> 
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <s:label value="Mô tả"/>
                    </td>
                    <td>
                        <s:textfield label="Mô tả" key="priGroupDesc" cssClass="fullcell"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <s:label value="Tên viết tắt"/>
                    </td>
                    <td>
                        <s:textfield label="Tên viết tắt" key="priGroupAlias" cssClass="fullcell"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td>
                        <s:label value="Phân quyền"/>
                    </td>
                    <td>
                        <s:textfield label="Phân quyền" key="priPrivilege" size="60"
                                     id="privileage_id" readonly="true"/>                    
                    </td>
                    <td style="width:20%">
                        <div style="float: left;">
                            <a href="#" onclick="javascript:openSelectWindow();">
                                <u>&gt;&gt;Chi tiết</u></a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <s:label value="Trạng thái"/></td>
                    <td>
                        <s:select headerKey="-1"
                                  list="statusList" 
                                  name="priGroupStatus" 
                                  listKey="sKey"
                                  listValue="sDesc"
                                  />
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><s:label value="Hiển thị DM_POS"/></td>
                    <td>
                        <s:select headerKey="-1"
                                  list="yesnoList" 
                                  name="priviewType" 
                                  listKey="sKey"
                                  listValue="sDesc"
                                  />                      
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>                    
                    <td><a href="#" onclick="javascript:openOwnerReports();">
                                <u>Phân quyền báo cáo</u></a></td>
                </tr>
                <tr>
                    <td colspan="3" align="right">
                        <s:submit label="Cập nhật" value="Cập nhật"/>
                        <input type="button" label="Quay lại" value="Quay lại" 
                               onclick="javascript:history.back();"/>
                    </td>
                </tr>
            </table>
        </s:form>
    </div>
</div>