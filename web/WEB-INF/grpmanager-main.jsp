<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>

<s:head/>
<sj:head/>

<style>
    .metroButtonStyle {
        font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
        display: block;
        color: rgb(255, 255, 255);
        text-decoration: none;
        text-align: center;
        width: 90px;
        height: 20px;
        padding: 5px;
        margin: 5px 0px 0px 5px;
        font-size: 12px;
        background: none repeat scroll 0 0 #808080;
        color: #FFF;
        border: 0px none;
        border-radius: 1px 1px 1px 1px;
        outline: 0px none;
    }
    .metroButtonStyle:hover {
        background: #018c3b;
    }
    .metroButtonStyle:active {
        background: #DCDCDC;
    }
    .alignCenter{
        text-align: center;
    }
    .clicklink {
        width: 80px;
        background-color: #b0e0e6;          
        text-align: center;                
    }
    .fullcell {
        width: 100%;
        box-sizing: border-box;
    }
</style>
<script>
    var js_ischeck = false;
    function js_isChecked() {
        js_ischeck = true;
    }
    function js_groupChange() {
        js_ischeck = false;
    }
    function js_confirmDelete() {
        var r = confirm('(Msg)Bạn chắc chắn muốn xoá nhóm người dùng này?');
        if (r === false) {
            event.preventDefault();
        }
    }
    function js_beforeSubmit() {
        if (!js_ischeck) {
            alert('(Msg)Bạn phải click kiểm tra trước khi khởi tạo nhóm.');
            event.preventDefault();
        } else {
            var js_groupcode = document.getElementById("groupcode_id").value;
            var js_privileage = document.getElementById("privileage_id").value;
            if (js_groupcode === '' || js_privileage === '') {
                alert('(Msg)Bạn phải nhập thông tin mã nhóm và phân quyền trước khi khởi tạo');
                event.preventDefault();
            }
        }
    }
    function openSelectWindow() {
        var ht1 = screen.availHeight - 100;
        var wt1 = 600;
        var left1 = (screen.width / 2) - (wt1 / 2);
        var top1 = 10;
        var js_privileage_str = document.getElementById("privileage_id").value;
        window.open('selectPrivi4UserGroup.action?privileageStr=' + js_privileage_str, 'IMS_REPORTS',
                "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1
                + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
    }

    $.subscribe('js_beforeCheck', function (event, data) {
        $("#js_error_msg").hide();
        $("#loadingImageDiv").show();
    });

    $.subscribe('js_afterCheck', function (event, data) {
        $("#js_error_msg").show();
        $("#loadingImageDiv").hide();
        var js_suggess_str = document.getElementById("js_suggess_value_id").value;
        var js_user_obj = document.getElementById("groupcode_id");
        js_user_obj.value = js_suggess_str;

    });
    
    function openOwnerReports() {
        var ht1 = screen.availHeight - 100;
        var wt1 = 600;
        var left1 = (screen.width / 2) - (wt1 / 2);
        var top1 = 10;
        var privileage_id = document.getElementById('groupcode_id');
        if (isEmpty(privileage_id) || js_ischeck === false) {
            alert('Bạn chưa nhập mã nhóm người dùng hoặc mã chưa hợp lệ. Hãy kiểm tra lại.')
        } else {
        window.open('selectOwnerReports.action?userGroupCode=' + privileage_id.value, 'IMS_REPORTS',
                "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1
                + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        }
    }
    
    function isEmpty(str) {
        return (!str || 0 === str.length);
    }
    
</script>

<div style="padding-left: 5px;">
    <div id="banner" style="float: top;">
        <table style="width: 100%;">
            <tr>
                <td style="width: 10%;">
                    <s:url id="queryUrl" action="UserGroup_list.action"/>    
                    <s:a id="groupQuerySubmit"  href="%{queryUrl}" button="true" theme="simple"
                         cssClass="clicklink">
                        <u> &gt;&gt;Truy vấn </u> </s:a>                                        
                </td>                              
            </tr>
        </table>
    </div>          
    <hr/>
    <div id="add_usergroup_form" class="usergroup_form"> 
        <s:form action="UserGroup_create" theme="simple" id="js_groupform">
            <table cellspacing="5px" width="100%">
                <tr>
                    <td style="width:20%"><s:label value="Nhóm người dùng"/></td>
                    <td style="width:60%">
                        <s:textfield key="priGroupCode" theme="simple" cssClass="fullcell"
                                     id="groupcode_id" onchange="js_groupChange();"/>                        
                    </td>
                    <td style="width:20%">
                        <div style="float: left;">
                        <s:url id="js_checkurl" action="checkNewUsrGroup.action"/>
                        <sj:a id="js_checknewgrp"  href="%{js_checkurl}"
                              theme="simple" targets="js_error_msg"
                              formIds="js_groupform"
                              onclick="js_isChecked();"
                              onchange="js_groupChange();"
                              onBeforeTopics="js_beforeCheck"
                              onCompleteTopics="js_afterCheck">
                            <u> &gt;&gt;Kiểm tra </u>
                        </sj:a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><s:label value="Mô tả"/></td>
                    <td>
                        <s:textfield key="priGroupDesc"  theme="simple" cssClass="fullcell"/>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><s:label value="Tên viết tắt" /></td>
                    <td><s:textfield key="priGroupAlias"  theme="simple" cssClass="fullcell"/></td>
                    <td></td>
                </tr>
                <tr>
                    <td><s:label value="Phân quyền" /></td>
                    <td>
                        <s:textfield key="priPrivilege"  theme="simple"
                                     id="privileage_id" cssClass="fullcell"/>                        
                    </td>                
                    <td>
                        <div style="float: left;">
                        <a href="#" onclick="javascript:openSelectWindow();">
                            <u>&gt;&gt;Chi tiết</u></a>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td><s:label value="Trạng thái"/></td>
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
                    <td colspan="5" align="right">
                        <s:submit label="Thêm mới" value="Thêm mới"
                                  id="js_addbutton" onclick="js_beforeSubmit(this);"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="5" align="left">
                        <div id="loadingImageDiv" style="display: none;">
                            <img id="loadingImage" src='img/loading_1.gif' border='0' >
                        </div>
                        <div id="js_error_msg"></div>
                    </td>
                </tr>                
            </table>
        </s:form>
    </div>
    <hr/>
    <div style="height:300px;overflow:scroll;">                    
        <table class="tblstyle">
            <tr>
                <td>STT</td>
                <td>Nhóm người dùng</td>
                <td>Mô tả</td>
                <td>Tên viết tắt</td>
                <td>Phân quyền</td>
                <td>Trạng thái</td>
                <td>Hiển thị DM_POS</td>
                <td>Sửa</td>
                <td>Xoá</td>
            </tr>
            <s:iterator value="userGroups" status="stat">
                <tr>
                    <td style="width: 10px; text-align: center;">
                        <label> <s:property value='#stat.count'/> </label>
                    </td>
                    <td><s:property value="priGroupCode"/></td>
                    <td><s:property value="priGroupDesc"/></td>
                    <td><s:property value="priGroupAlias"/></td>
                    <td style="width: 40%;"><s:textfield name="priPrivilege" theme="simple" cssClass="fullcell" readonly="true"/></td>
                    <td style="text-align: center;"><s:property value="priGroupStatus"/></td>
                    <td style="text-align: center;"><s:property value="priviewType"/></td>
                    <td style="text-align: center;">
                        <s:url id="editUrl" value="UserGroup_edit.action">
                            <s:param name="userGroupCode" value="priGroupCode"/>
                        </s:url>
                        <s:a href="%{editUrl}"><u>Sửa</u></s:a>
                    </td>                    
                    <td style="text-align: center;">
                        <s:url id="deleteUrl" value="UserGroup_delete.action">
                            <s:param name="userGroupCode" value="priGroupCode"/>
                        </s:url>
                        <s:a href="%{deleteUrl}" onclick="js_confirmDelete();"><u>Xoá</u></s:a>
                    </td>    
                </tr>
            </s:iterator>
        </table>        
    </div>        
</div>
