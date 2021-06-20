<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>


<s:head/>
<sj:head/>

<style>
    .normal_style {
        font-family: Arial;
        font-size: 12pt;
        font-weight: bold;
    }
    input.readonly {
        background: #99ffff;
    }        

    .group-configure-table , .group-configure-table tr, .group-configure-table td{
        font-size: 11pt;
    }

</style>

<s:form theme="simple" id="fastreport_group_form">
    <table style="border: 1px;width: 100%;" cellspacing="5"
           class="group-configure-table">
        <tr class="normal_style">
            <td>Group ID</td>
            <td>
                <div id="code_div">
                    <input type="text" 
                           name="groupQuery.groupQueryPK.groupId" 
                           placeholder="Chọn kiểm tra để tạo nhóm mới..." 
                           size="60" 
                           id="groupId"
                           readonly="true"
                           class="readonly"
                           value="<s:property value='groupQuery.groupQueryPK.groupId'/>"/>                                                                                              
                    &nbsp;
                </div>                      
            </td>
            <td align="right"> 
                <s:url id="checkcode_Url" action="FRCFG_Get_NewId"></s:url>                                    
                <sj:a href="%{checkcode_Url}"
                      formIds="fastreport_group_form"
                      targets="code_div"     
                      id="checkcodeBtn_Id"                                  
                      >                    
                </sj:a>      
                <a href="#" onclick="onCheck();"><u>Kiểm tra</u></a>                 
            </td>
        <script>
            $.subscribe('before-next',
                    function(event, data) {
                        $("#loadingImageDiv").show();
                        $("#contentDiv").empty();
                        $("#contentDiv").hide();
                    });

            $.subscribe('after-next',
                    function(event, data) {
                        $("#loadingImageDiv").hide();
                        $("#contentDiv").show();
//                                $('#treeView').jstree().refresh();
                    });

            function onCheck() {
                // DAT LAI GIA TRI NULL                
                $('#textId').val('');
                // CAP NHAT THONG TIN
                $('#action_type_ID').val('CREATE');
                $('#checkcodeBtn_Id').click();
            }
        </script>
    </tr>
    <tr class="normal_style">
        <td>Mô tả</td>
        <td colspan="2"><s:textfield name="groupQuery.groupDesc" 
                     label="TEXT" size="60"
                     id="textId"/></td>
    </tr>
    <tr class="normal_style">
        <td>Module</td>
        <td colspan="2">
            <s:url var="buildComboUrl_0" 
                   action="FRCFG_Build_Group_Combo"></s:url>
            <sj:select href="%{buildComboUrl_0}" 
                       name="groupQuery.groupQueryPK.module"
                       id="group_module_ID"
                       list="group_modules" 
                       listKey="sKey"
                       listValue="sDesc"
                       emptyOption="false"                                                          
                       theme="simple"     
                       ></sj:select>              
            </td>
        </tr>
        <tr class="normal_style">
            <td>Áp dụng tại</td>
            <td colspan="2" >
            <s:url var="buildComboUrl_1" 
                   action="FRCFG_Build_Group_Combo"></s:url>
            <sj:select href="%{buildComboUrl_1}" 
                       name="groupQuery.groupQueryPK.applyRegion"
                       id="group_apply_region_ID"
                       list="group_apply_regions" 
                       listKey="sKey"
                       listValue="sDesc"
                       emptyOption="false"                                                          
                       theme="simple"     
                       ></sj:select>
            </td>
        </tr>    
        <tr class="normal_style">
            <td></td>
            <td colspan="2" align="left">
                <script>
                    function openSelectWindow() {
                        var ht1 = screen.availHeight - 100;
                        var wt1 = 600;
                        var left1 = (screen.width / 2) - (wt1 / 2);
                        var top1 = 10;
                        var groupId = $('#groupId').val();
                        var moduleId = $('#group_module_ID').val();
                        var applyRegion = $('#group_apply_region_ID').val();
                        if (groupId === null || groupId === '') {
                            alert('Bạn phải chọn tạo mới hoặc cập nhật groupId');
                            return false;
                        } else {
                            window.open('GET_QUERYGROUP_PRI.action?groupId=' + groupId + '&module=' + moduleId + '&applyRegion=' + applyRegion, 'IMS_REPORTS_0',
                                    "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1
                                    + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                        }
                    }
                </script>
                <a href="#" onclick="javascript:openSelectWindow();"
                   id="js_select_id">
                    <u><b>&gt;&gt;Phân quyền</b></u>
                </a>
                <input type="hidden" id="group_Arr" value="" name="groupsStr"/>
            </td>                    
        </tr>
        <tr>
            <td colspan="3"><hr/></td>
        </tr>
        <tr>
            <td>
                <div id="loadingImageDiv" style="display: none;">
                    <img id="loadingImage" src='img/loading.gif' border='0' >
                </div>
            </td>
            <td align="right" colspan="2">
            <s:url id="updateUrl" action="FRCFG_Update_Group.action">                                            
            </s:url>
            <sj:a id="updateUrl_id"  
                  href="%{updateUrl}"                                                                                                
                  formIds="fastreport_group_form"                                
                  targets="messageDiv"
                  button="false"                                                                                 
                  theme="simple"></sj:a>
                <input type="button" value="Lưu trữ" onclick="update_click();"></input>
                <input type="button" value="Xoá nhóm" onclick="delete_click();"></input>
                <input type="hidden" name="action_type" id="action_type_ID" value="UPDATE"/>
                <script>
                    function update_click() {
                        var menuId = $('#groupId').val();
                        var text = $('#textId').val();
                        if (menuId === '' || text === '') {
                            alert('Bạn không thể lưu dữ liệu khi menuId hoặc Text đang trống.');
                            return false;
                        }
                        $("#messageDiv").empty();
                        $('#updateUrl_id').click();
                        $("#loadTableBtn").trigger("click");
//                        $("#loadTableBtn").trigger("click");
                    }

                    function delete_click() {
                        var menuId = $('#menuId').val();
                        if (menuId === '') {
                            alert('Bạn không thể xoá khi menuId đang trống.');
                            return false;
                        }
                        var r = confirm('Bạn có chắc chắn muốn xoá menuId?');
                        if (r === true) {
                            $('#action_type_ID').val('DELETE');
                            $("#messageDiv").empty();
                            $('#updateUrl_id').click();
                            $("#loadTableBtn").trigger("click");
                            $('#action_type_ID').val('CREATE');
                        } else {
                            e.preventDefault();
                        }
                    }
                </script>
            </td>
        </tr>
    </table> 
    <table style="border: 1px;width: 100%;" cellspacing="5">
        <tr>
            <td style="font-size: 12pt;">
            <s:url id="loadTableUrl" 
                   action="FRCFG_Display_Query_Table"/>    
            <sj:a id="loadTableBtn"  
                  href="%{loadTableUrl}"
                  button="false" 
                  theme="simple"
                  formIds="fastreport_group_form"                    
                  targets="tableDiv"> 
        <u>Truy vấn</u>
        </sj:a> 
</td>
</tr>
<tr>
    <td>        
        <div id="tableDiv"></div>                
        <div id="messageDiv"></div>                
    </td>
</tr>
</table>
</s:form>

<script>
    $(function() {
        $("#loadTableBtn").trigger("click");
    });
</script>