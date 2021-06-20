<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:head/>
<sj:head/>
<style type="text/css">
    /*Phan xu ly text cho readonly*/
    input[type="text"][readonly],
    textarea[readonly] {
        background-color: #cfd1cf;
    }
</style>
<script>
    function checkbeforesubmit(){
        var password_item01 = $("#password_item01").val();
        
        if (password_item01.value === ""){
            alert("Bạn chưa nhập mật khẩu !");
            event.preventDefault();
            return false;
        }
        
        //var js_usercode = $("#js_usercode_id").val();
        var js_username = $("#priUserName").val();
        var js_checkgroup = $("#priMaCanBo").val();
        var js_macanbo = $("#priMaCanBo").val();
        
        if (js_username === ''
                || js_checkgroup === '/'
                || isEmpty(js_macanbo)) {
            alert('(Msg)Bạn phải điền thông tin: Tên người dùng, Cấp đăng nhập, Mã cán bộ.');
            event.preventDefault();
            return false;
        }
        
        var js_ktmacanbo = parseInt( $("#MaCanBo_Status").val());
        if (js_ktmacanbo === 0) {
            alert('(Msg)Bạn nhập mã cán bộ chưa đúng hoặc mã này đã được gán cho user khác.');
            event.preventDefault();
            return false;
        }
    }
    function passwordchange_log(){        
        //var password_item02 = document.getElementById("password_item02");
        //password_item02.value = "Y";
        $("#password_item02").val("Y");
    }
    function handleClick(cb) {
        var checkstr = "/";
        var i = 1;
        for (i = 1; i <= 3; i++){           
           var status = $("#chk_group_" + i).is(":checked");           
           if (status === true){
              // alert($("#chk_group_" + i).val());
               checkstr += $("#chk_group_" + i).val() + "/";               
           }
        }        
        $("#js_chkbox_id").val(checkstr);        
    }
    
    function checkMaCanBo() {
        var macanbo = $("#priMaCanBo").val();  
        var userName = $("#priUserCode").val();
        if(isEmpty(macanbo)) {
            $("#MaCanBo_Label").hide();
            $("#MaCanBo_Status").val("0");
        } else {        
            //alert("CheckMaCanBo?" + "Username=" + userName + "&MaCanBo="+macanbo);
            $.ajax({
                    type : "GET",
                    url : "CheckMaCanBo?" + "Username=" + userName + "&MaCanBo="+macanbo,
                    success : function(res) {
                        var status = parseInt( res.dataList[0]);
                        //alert(status);
                        if (status === 0){
                            $("#MaCanBo_Label").text("[OK]");
                            $("#MaCanBo_Label").css("color", 'blue');
                            $("#MaCanBo_Label").show();
                            $("#MaCanBo_Status").val("1");
                        }
                        else{
                            $("#MaCanBo_Label").text("[X]");
                            $("#MaCanBo_Label").css("color", 'red');
                            $("#MaCanBo_Label").show();
                            $("#MaCanBo_Status").val("0");
                        }
                    },
                    error : function(res) {
                        alert("No values found..!!");
                    }
            });
        }
    }
</script>
<p style="text-decoration: underline;padding-left: 5px;font-size: 12pt;font-weight: bold;"> 
    Cập nhật thông tin Người dùng
</p>
<hr/>
<div id="edit_usergroup_form" class="user_form"> 
    <s:form action="User_update" theme="simple">
        <table style="padding: 5px;" cellspacing="5px">            
            <tr>
                <td style="width: 15%;"><s:label value="Mã"/></td>
                <td style="width: 35%;"><s:textfield key="priUserCode" readonly="true"  id="priUserCode"
                             cssStyle="width:100%;"/></td>
                <td style="width: 15%;"><s:label value="Tên"/></td>
                <td style="width: 35%;"><s:textfield key="priUserName" id="priUserName" cssStyle="width:100%;"/></td>
            </tr>
            <tr>
                <td><s:label value="Địa chỉ"/></td>
                <td><s:textfield key="priAddress" cssStyle="width:100%;"/></td>
                <td><s:label value="Mobile"/></td>
                <td><s:textfield key="priMobile" cssStyle="width:100%;"/></td>
            </tr>
            <tr>
                <td><s:label value="Chức vụ"/></td>
                <td><s:textfield key="priOffice" cssStyle="width:100%;"/></td>
                <td><s:label value="Mật khẩu"/></td>
                <td><s:textfield key="priPassword" cssStyle="width:100%;"
                             id="password_item01"
                             onchange="passwordchange_log();"/>
                    <input type="hidden" name="updatePassword" 
                           id="password_item02" value="N"/>
                </td>
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
                <td>
                    <input type="checkbox" name="chk_group_1" id="chk_group_1" value="1" checked 
                           onclick="handleClick(this);" />PGD &nbsp;
                    <input type="checkbox" name="chk_group_2" id="chk_group_2" value="2" 
                           onclick="handleClick(this);" />CN &nbsp;
                    <input type="checkbox" name="chk_group_3" id="chk_group_3" value="3" 
                           onclick="handleClick(this);"/>TQ
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
                    <td><s:select headerKey="-1"
                              list="statusList" 
                              name="priStatus" 
                              listKey="sKey"
                              listValue="sDesc"
                              /></td>       
                    <td>
                        <s:label value="Mã cán bộ"/>
                        </td>
                        <td>
                            <s:textfield key="priMaCanBo" id="priMaCanBo" cssStyle="width:50%;" />
                            
                            <label style="display: none;" id="MaCanBo_Label">OK </label>
                            <s:hidden name="MaCanBo_Status" id="MaCanBo_Status" value="1"></s:hidden>
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
                <td colspan="4" align="right"><s:submit label="Cập nhật" value="Cập nhật"
                          onclick="checkbeforesubmit();"/>
                    &nbsp;
                    <input type="button" label="Quay lại" value="Quay lại" onclick="javascript:history.back();"/></td>
            </tr>        
        </table>
        <s:hidden name="updateType" value="1" />
    </s:form>
</div>

<script>
    $( document ).ready(function() {
        var gradeListStr = $("#js_chkbox_id").val();

        if (!isEmpty(gradeListStr)){
        var gradeList = gradeListStr.split('/');

        var i = 0;
        for (i = 0; i < gradeList.length; i++)
        {
            if (!isEmpty(gradeList[i])){
                var gradeItem = parseInt( gradeList[i]);
                if (gradeItem === 1)
                    $("#chk_group_1").prop( "checked", true );
                if (gradeItem === 2)
                    $("#chk_group_2").prop( "checked", true );
                if (gradeItem === 3)
                    $("#chk_group_3").prop( "checked", true );
            }
        }
    }

    });

    function isEmpty(str) {
        return (!str || 0 === str.length);
    }

    $("#priMaCanBo").change(function(){
        checkMaCanBo();
    });
</script>