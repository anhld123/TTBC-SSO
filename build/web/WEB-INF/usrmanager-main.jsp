<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<s:head/>
<sj:head/>
<style>    
    .clicklink {        
        width: 80px;
        background-color: #b0e0e6;          
        text-align: center;            
    }        
    #animate {        
      position: absolute;
      background: red;
    }
    
    #usertable td.validrow {color:#07B200;}
    #usertable td.invalidrow {color:#FF0000;}
    
    .fullcellvalid {
        width: 100%;
        box-sizing: border-box;
        color: #07B200;
    }
    
    .fullcellinvalid {
        width: 100%;
        box-sizing: border-box;
        color: #FF0000;
    }
</style>
<script>
    var js_ischeck = false;

    function js_isChecked() {
        js_ischeck = true;
    }

    function js_userchange() {
        js_ischeck = false;
    }

    function js_handleclick() {
        var checkstr = "/";
        var i = 1;
        for (i = 1; i <= 3; i++) {
            //var check_id = "chk_group_" + i.toString();
            //var chkgrp_item = document.getElementById(check_id);
            var status = $("#chk_group_" + i).is(":checked");
            if (status === true) {
                checkstr += $("#chk_group_" + i).val() + "/";
            }
        }
        $("#js_chkbox_id").val(checkstr);
    }
    function js_beforesubmit() {
        if (!js_ischeck) {
            alert('(Msg)Bạn phải click kiểm tra trước khi khởi tạo người dùng.');
            event.preventDefault();
            return false;
        } else {
            var js_usercode = $("#js_usercode_id").val();
            var js_username = $("#js_username_id").val();
            var js_checkgroup = $("#js_chkbox_id").val();

            var js_macanbo = $("#priMaCanBo").val();            
            
            if (js_usercode === '' || js_username === ''
                    || js_checkgroup === '/'
                    || isEmpty(js_macanbo)) {
                alert('(Msg)Bạn phải nhập mã người dùng, tên người dùng, cấp đăng nhập, mã cán bộ trước khi khởi tạo.');
                event.preventDefault();
                return false;
            }

            var js_ktmacanbo = parseInt($("#MaCanBo_Status").val());
            if (js_ktmacanbo === 0) {
                alert('(Msg)Bạn nhập mã cán bộ chưa đúng hoặc mã này đã được gắn cho user khác.');
                event.preventDefault();
                return false;
            }
        }
    }

    function js_confirmdelete() {
        var r = confirm('(Msg)Bạn chắc chắn muốn xoá người dùng này?');
        if (r === false) {
            event.preventDefault();
        }
    }

    function checkMaCanBo() {
        var macanbo = $("#priMaCanBo").val();
        var userName = $("#js_usercode_id").val();

        if (isEmpty(macanbo)) {
            $("#MaCanBo_Label").hide();
            $("#MaCanBo_Status").val("0");
        } else {

            $.ajax({
                type: "GET",
                url: "CheckMaCanBo?" + "Username=" + userName + "&MaCanBo=" + macanbo,
                success: function (res) {
                    var status = parseInt(res.dataList[0]);
                    //alert(status);
                    if (status === 0) {
                        $("#MaCanBo_Label").text("[OK]");
                        $("#MaCanBo_Label").css("color", 'blue');
                        $("#MaCanBo_Label").show();
                        $("#MaCanBo_Status").val("1");
                    } else {
                        $("#MaCanBo_Label").text("[X]");
                        $("#MaCanBo_Label").css("color", 'red');
                        $("#MaCanBo_Label").show();
                        $("#MaCanBo_Status").val("0");
                    }
                },
                error: function (res) {
                    alert("No values found..!!");
                }
            });
        }
    }
    
    function checkDangKy() {       
        var userName = $("#login_user_id").val();
        $.ajax({
            type: "GET",
            url: "CheckDangKyND?" + "Username=" + userName ,            
            success: function (res) {
                var status = parseInt(res.dataList[0]);
                //alert(status);
                if (status === 0) {
                    startMessage();                    
                }
            },
            error: function (res) {
                alert("No values found..!!");
            }
        });        
    }

    $.subscribe('js_beforeCheck', function (event, data) {
        $("#js_error_msg").hide();
        $("#loadingImageDiv").show();
    });

    $.subscribe('js_afterCheck', function (event, data) {
        $("#js_error_msg").show();
        $("#loadingImageDiv").hide();
        var js_suggess_str = $("#js_suggess_value_id").val();
        $("#js_usercode_id").val(js_suggess_str);
    });
    
    var message_baner_id;
    
    function startMessage() {
        var elem = document.getElementById("animate");   
        var pos = 200;
        message_baner_id = setInterval(frame, 30);
        function frame() {
          if (pos === 1060) {
            clearInterval(id);
          } else {
            pos++; 
            if (pos === 1050)
              pos = 200;
            elem.style.left = pos + "px"; 
          }
        }
    }
    
    function stopMessage() {
        clearInterval(message_baner_id);
        $("#container").hide();
    }
</script>

<s:hidden name="userCode" id="login_user_id"/>

<div style="padding-left: 5px; padding-top: 10px;">
    <div id="banner" style="float: top;">
        <table style="width: 100%;" >
            <tr>
                <td style="width: 10%;">
                    <s:url id="queryUrl" action="User_list.action"/>    
                    <s:a id="userQuerySubmit"  href="%{queryUrl}"
                         button="true" theme="simple" cssClass="clicklink"> 
                            <u>&gt;&gt;Truy vấn</u> 
                    </s:a>
                </td>
                <td style="width: 80%;">          
                    <div id="container" style="margin-left: 30%;">
                        <div id ="animate" style="color: #FFFFFF;">Có danh sách người dùng chưa được cập nhật nhóm chức năng!</div>   
                        <a href="#" onclick="stopMessage()" style="float: right;" >[X-Đóng]</a>
                    </div>
                </td>                
            </tr>
        </table>
    </div>

    <hr/>
    <div id="add_user_form" class="user_form"> 
        <s:form action="User_create" theme="simple" id="js_userform">
            <table cellspacing="5px">
                <tr>
                    <td style="width: 15%;"><s:label value="Mã"/></td>
                    <td style="width: 35%;"><s:textfield key="priUserCode" cssStyle="width:68%;"
                                 id="js_usercode_id" onchange="js_userchange();"/>
                        <s:url id="js_checkurl" action="checkNewUser.action"/>
                        <sj:a id="js_checknewuser"  href="%{js_checkurl}"
                              theme="simple" targets="js_error_msg"
                              formIds="js_userform"                              
                              onclick="js_isChecked();"
                              onBeforeTopics="js_beforeCheck"
                              onCompleteTopics="js_afterCheck"
                              >
                            <u> &gt;&gt;Kiểm tra </u>
                            </sj:a>
                    </td>
                    <td style="width: 15%;"><s:label value="Tên"/></td>
                    <td style="width: 35%;"><s:textfield key="priUserName" cssStyle="width:100%;"
                                 id="js_username_id"/></td>
                </tr>
                <tr>
                    <td><s:label value="Địa chỉ"/></td>
                    <td><s:textfield key="priAddress" cssStyle="width:100%;"/></td>
                    <td><s:label value="Mobile"/></td>
                    <td><s:textfield key="priMobile" cssStyle="width:100%;"/></td>
                </tr>
                <tr>
                    <td><s:label value="Phòng/Ban"/></td>
                    <td><s:textfield key="priOffice" cssStyle="width:100%;" /></td>
                    <td><s:label value="Mật khẩu"/></td>
                    <td><s:password key="priPassword" cssStyle="width:100%;"
                                readonly="true"  onfocus="$(this).removeAttr('readonly');"
                                id="js_password_id"/></td>
                </tr>
                <tr>
                    <td><s:label value="Khoá CK"/></td>
                    <td><s:textfield key="priPubKey" cssStyle="width:100%;"/></td>
                    <td><s:label value="Nhóm người dùng"/></td>
                    <td>                        
                        <s:url var="buildUserGroupComboUrl" action="buildUserGroupCombo"></s:url>
                        <sj:select href="%{buildUserGroupComboUrl}" 
                                   name="priUserGroup"
                                   id="priUserGroup"
                                   list="userGroupList"        
                                   onChangeTopics="reloadModuleList"
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false" 
                                   theme="simple"
                                   ></sj:select> 
                        </td>
                    </tr>
                    <tr>
                        <td><s:label value="Cấp báo cáo"/></td>
                    <td><input type="checkbox" name="chk_group_1" id="chk_group_1" value="1" checked 
                               onclick="js_handleclick();" />PGD &nbsp;
                        <input type="checkbox" name="chk_group_2" id="chk_group_2" value="2" 
                               onclick="js_handleclick();" />CN &nbsp;
                        <input type="checkbox" name="chk_group_3" id="chk_group_3" value="3" 
                               onclick="js_handleclick();"/>TQ

                        <s:hidden name="priRptGrade" id="js_chkbox_id"/>

                    </td>
                    <td><s:label value="Mã đơn vị"/></td>
                    <td>                        
                        <s:url var="buildPosComboUrl" action="buildPosListCombo"></s:url>
                        <sj:select href="%{buildPosComboUrl}" 
                                   name="priPosCode"
                                   id="priPosCode"
                                   list="posList"        
                                   onChangeTopics="reloadModuleList"
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false" 
                                   theme="simple"
                                   cssStyle="width:50%;" 
                                   ></sj:select> 
                        </td>
                    </tr>
                    <tr>
                        <td><s:label value="Trạng thái"/></td>
                        <td>
                            <s:select headerKey="-1"
                              list="statusList" 
                              name="priStatus" 
                              listKey="sKey"
                              listValue="sDesc"
                            />
                        </td>       
                    <td>
                        <s:label value="Mã cán bộ"/>
                    </td>
                    <td>
                        <s:textfield key="priMaCanBo" id="priMaCanBo" cssStyle="width:50%;" />
                        <label style="display: none;" id="MaCanBo_Label">OK </label>
                        <s:hidden name="MaCanBo_Status" id="MaCanBo_Status" value="0"></s:hidden>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td>
                        <s:label value="Nhóm công việc"/>
                    </td>
                    <td>
                        <s:url var="buildUserFuncComboUrl" action="buildUserFuncListCombo"></s:url>
                        <sj:select href="%{buildUserFuncComboUrl}" 
                                   name="priNhomCongViec"
                                   id="priNhomCongViec"
                                   list="userFuncList"                                           
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false" 
                                   theme="simple"
                                   cssStyle="width:100%;" 
                                   ></sj:select> 
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4" align="right">
                        <s:submit label="Thêm mới" value="Thêm mới"
                                  onclick="js_beforesubmit();"/>
                    </td>
                </tr>        
                <tr>                
                    <td colspan="4" align="left">
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
    <div style="height:300px;;overflow:scroll;">                    
        <table class="tblstyle" id="usertable">
            <tr>
                <td>STT</td>
                <td>Mã</td>
                <td>Tên</td>
                <td>Địa chỉ</td>
                <td>Mobile</td>
                <td>Phòng/Ban</td>
                <td>Nhóm ND</td>
                <td>Cấp báo cáo</td>
                <td>Đơn vị</td>
                <td>Trạng thái</td>
                <td>Mã cán bộ</td>
                <td>Nhóm công việc</td>
                <td>Sửa</td>
                <td>Xoá</td>
            </tr>
            <s:iterator value="users" status="stat">
                <s:if test="%{priValidFlag == 1}">
                    <tr>
                        <td style="width: 15px; text-align: center;">
                            <s:url id="userCloneUrl" value="User_clone.action">
                                <s:param name="userCode" value="priUserCode"/>
                            </s:url>
                            <s:a href="%{userCloneUrl}"><u><s:property value="#stat.count"/></u></s:a>
                        </td>
                        <td class="validrow"><s:textfield name="priUserCode" theme="simple" cssClass="fullcellvalid" readonly="true"/></td>
                        <td class="validrow"><s:property value="priUserName"/></td>
                        <td class="validrow"><s:textfield name="priAddress" theme="simple" cssClass="fullcellvalid" readonly="true"/></td>
                        <td class="validrow"><s:property value="priMobile"/></td>
                        <td class="validrow"><s:textfield name="priOffice" theme="simple" cssClass="fullcellvalid" readonly="true"/></td>    
                        <td class="validrow" style="text-align: center;"><s:property value="priUserGroup"/></td>
                        <td class="validrow"><s:property value="priRptGrade"/></td>
                        <td class="validrow" style="text-align: center;"><s:property value="priPosCode"/></td>
                        <td class="validrow" style="text-align: center;"><s:property value="priStatus"/></td>
                        <td class="validrow" style="text-align: center;"><s:property value="priMaCanBo"/></td>
                        <td class="validrow" style="text-align: center;"><s:property value="priNhomCongViec"/></td>
                        <td style="text-align: center;">
                            <s:url id="userEditUrl" value="User_edit.action">
                                <s:param name="userCode" value="priUserCode"/>
                            </s:url>
                            <s:a href="%{userEditUrl}"><u>Sửa</u></s:a>
                            </td>                    
                            <td style="text-align: center;">
                            <s:url id="userDeleteUrl" value="User_delete.action">
                                <s:param name="userCode" value="priUserCode"/>
                            </s:url>
                            <s:a href="%{userDeleteUrl}" onclick="js_confirmdelete();"><u>Xoá</u></s:a>
                        </td>    
                    </tr>
                </s:if>
                <s:else>
                    <tr >
                        <td style="width: 15px; text-align: center;">
                            <s:url id="userCloneUrl" value="User_clone.action">
                                <s:param name="userCode" value="priUserCode"/>
                            </s:url>
                            <s:a href="%{userCloneUrl}"><u><s:property value="#stat.count"/></u></s:a>
                        </td>
                        <td class="invalidrow"><s:textfield name="priUserCode" theme="simple" cssClass="fullcellinvalid" readonly="true"/></td>
                        <td class="invalidrow"><s:property value="priUserName"/></td>
                        <td class="invalidrow"><s:textfield name="priAddress" theme="simple" cssClass="fullcellinvalid" readonly="true"/></td>
                        <td class="invalidrow"><s:property value="priMobile"/></td>
                        <td class="invalidrow"><s:textfield name="priOffice" theme="simple" cssClass="fullcellinvalid" readonly="true"/></td>
                        <td class="invalidrow" style="text-align: center;"><s:property value="priUserGroup"/></td>
                        <td class="invalidrow"><s:property value="priRptGrade"/></td>
                        <td class="invalidrow" style="text-align: center;"><s:property value="priPosCode"/></td>
                        <td class="invalidrow" style="text-align: center;"><s:property value="priStatus"/></td>
                        <td class="invalidrow" style="text-align: center;"><s:property value="priMaCanBo"/></td>
                        <td class="invalidrow" style="text-align: center;"><s:property value="priNhomCongViec"/></td>
                        <td style="text-align: center;">
                            <s:url id="userEditUrl" value="User_edit.action">
                                <s:param name="userCode" value="priUserCode"/>
                            </s:url>
                            <s:a href="%{userEditUrl}"><u>Sửa</u></s:a>
                            </td>                    
                            <td style="text-align: center;">
                            <s:url id="userDeleteUrl" value="User_delete.action">
                                <s:param name="userCode" value="priUserCode"/>
                            </s:url>
                            <s:a href="%{userDeleteUrl}" onclick="js_confirmdelete();"><u>Xoá</u></s:a>
                        </td>    
                    </tr>
                </s:else>
            </s:iterator>
        </table>        
    </div>        
</div>


<script>
    $(document).ready(function () {
        var gradeListStr = $("#js_chkbox_id").val();

        if (!isEmpty(gradeListStr)) {
            var gradeList = gradeListStr.split('/');

            var i = 0;
            for (i = 0; i < gradeList.length; i++)
            {
                if (!isEmpty(gradeList[i])) {
                    var gradeItem = parseInt(gradeList[i]);
                    if (gradeItem === 1)
                        $("#chk_group_1").prop("checked", true);
                    if (gradeItem === 2)
                        $("#chk_group_2").prop("checked", true);
                    if (gradeItem === 3)
                        $("#chk_group_3").prop("checked", true);
                }
            }
        }
        
        checkDangKy() ;
    });

    function isEmpty(str) {
        return (!str || 0 === str.length);
    }

    $("#priMaCanBo").change(function () {
        checkMaCanBo();
    });
</script>