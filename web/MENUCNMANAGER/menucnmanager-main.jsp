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
</style>

<%
    session.setAttribute("startTreeGLKHTDRecursive", null);
%>  


<div style="float: left;width: 45%; font-family:Arial;font-size: 12pt; ">
    <s:form theme="simple" id="menuForm">
        <p class="normal_style">Quản lý menu tại chi nhánh:</p>    
        <div>
            <table style="border: 1px;width: 100%;" cellspacing="5">
                <tr class="normal_style">
                    <td>ID</td>
                    <td>
                        <div id="code_div">
                            <input type="text" name="menu.menuId" 
                                   placeholder="Chọn kiểm tra để tạo mã MN mới..." 
                                   size="60" id="menuId"
                                   readonly="true"
                                   class="readonly"
                                   value="<s:property value='menu.menuId'/>"/>                                                                                              
                            &nbsp;
                        </div>                      
                    </td>
                    <td align="right"> 
                        <s:url id="checkcode_Url" action="CHECK_ID_MENUCNMANAGER"></s:url>                                    
                        <sj:a href="%{checkcode_Url}"
                              formIds="menuForm"
                              targets="code_div"     
                              id="checkcodeBtn_Id"                                  
                              >                    
                        </sj:a>      
                        <a href="#" onclick="onCheck();"><u>Kiểm tra</u></a> 
                        <input type="hidden" name="action_type" id="action_type_ID" value="UPDATE"/>
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
                        $('#urlId').val('#');
                        $('#textId').val('');
                        $('#parentId').val('0');
                        // CAP NHAT THONG TIN
                        $('#action_type_ID').val('CREATE');
                        $('#checkcodeBtn_Id').click();
                    }
                </script>
                </tr>
                <tr class="normal_style">
                    <td>Text</td>
                    <td colspan="2"><s:textfield name="menu.text" 
                                 label="TEXT" size="60"
                                 id="textId"/></td>
                </tr>
                <tr class="normal_style">
                    <td>Url</td>
                    <td colspan="2"><s:textfield name="menu.navigateUrl" label="TEXT" size="60"
                                 id="urlId"
                                 onchange="check_url(this);"/>
                        <script>
                            function check_url(obj) {
//                                alert(obj.value);      
                                var url = obj.value;

                                if (!isValid(url)) {
                                    alert('Url là chuỗi ký tự [a-z,0-9] không chứa các ký tự đặc biệt.');
                                    $("#urlId").css('background-color', 'red');
                                    $('#urlId').val("");
                                    $('#urlId').focus();
                                    return false;
                                } else {
                                    $("#urlId").css('background-color', 'white');
                                }

                                if (url.toUpperCase() === 'RPTMANAGER') {
                                    $('#selectedGroup').prop('disabled', false);
                                } else {
                                    $('#selectedGroup').prop('disabled', 'disabled');
                                }
                            }

                            function isValid(str) {
                                if (str === '#')
                                    return true;
                                else {
                                    var iChars = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?.";

                                    for (var i = 0; i < str.length; i++) {
                                        if (iChars.indexOf(str.charAt(i)) !== -1) {
                                            return false;
                                        }
                                    }
                                    return true;
                                }
                            }
                        </script>
                    </td>
                </tr>
                <tr class="normal_style">
                    <td>Parent ID</td>
                    <td colspan="2" ><s:textfield name="menu.parentId" label="Parent ID" size="30"
                                 id="parentId"/></td>
                </tr>
                <tr class="normal_style">
                    <td>Gắn Nhóm BC</td>
                    <td colspan="2" >
                        <s:url var="buildGroupComboUrl" action="buildGroupCombo"></s:url>
                        <sj:select href="%{buildGroupComboUrl}" 
                                   name="selectedGroup"
                                   id="selectedGroup"
                                   list="rptGroupList"    
                                   onChangeTopics="reloadModuleList"
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false" 
                                   headerKey=""
                                   headerValue="--- Chọn nhóm báo cáo ---" 
                                   theme="simple"
                                   value="D"
                                   ></sj:select>    
                        </td>
                    </tr>
                    <tr class="normal_style">
                        <td colspan="3" align="right">
                            <script>
                                function openSelectWindow() {
                                    var ht1 = screen.availHeight - 100;
                                    var wt1 = 600;
                                    var left1 = (screen.width / 2) - (wt1 / 2);
                                    var top1 = 10;
                                    var menuid = $('#menuId').val();
                                    window.open('GET_MENUCN_PRI.action?menuId=' + menuid, 'IMS_REPORTS',
                                            "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1
                                            + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                                }
                            </script>
                            <a href="#" onclick="javascript:openSelectWindow();"
                               id="js_select_id">
                                <u><b>&gt;&gt;Phân quyền</b></u>
                            </a>
                        </td>                    
                    </tr>
                    <tr>
                        <td colspan="3"><hr/></td>
                    </tr>
                    <tr>
                        <td align="right" colspan="3">
                        <s:url id="updateUrl" action="UPDATE_ID_MENUCNMANAGER.action">                                            
                        </s:url>
                        <sj:a id="updateUrl_id"  
                              href="%{updateUrl}"                                                                                                
                              formIds="menuForm"                                
                              targets="messageDiv"
                              button="false"                                                                                 
                              theme="simple"></sj:a>
                            <input type="button" value="Lưu trữ" onclick="update_click();"></input>
                            <input type="button" value="Xoá menu" onclick="delete_click();"></input>
                            <script>
                                function update_click() {
                                    var menuId = $('#menuId').val();
                                    var text = $('#textId').val();
                                    if (menuId === '' || text === '') {
                                        alert('Bạn không thể lưu dữ liệu khi menuId hoặc Text đang trống.');
                                        return false;
                                    }
                                    $("#messageDiv").empty();
                                    $('#updateUrl_id').click();
                                    $("#userQuerySubmit").trigger("click");
                                }

                                function delete_click() {
                                    var menuId = $('#menuId').val();
                                    if (menuId === '') {
                                        alert('Bạn không thể xoá khi menuId đang trống.');
                                        return false;
                                    }
                                    var r = confirm('(Msg)Bạn có chắc chắn muốn xoá menuId?');
                                    if (r === true) {
                                        $('#action_type_ID').val('DELETE');
                                        $("#messageDiv").empty();
                                        $('#updateUrl_id').click();
                                        $("#userQuerySubmit").trigger("click");
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
                        <td>
                        <s:url id="queryUrl" action="listMenu.action"/>    
                        <sj:a id="userQuerySubmit"  
                              href="%{queryUrl}"
                              button="false" 
                              theme="simple"
                              formIds="menuForm"  
                              onBeforeTopics="before-next"
                              onCompleteTopics="after-next" 
                              targets="contentDiv"> 
                    <u>Truy vấn</u>
                    </sj:a> 
                &nbsp; <a href="#" onclick="tree_refresh();"><u>Refresh Tree</u></a>
                <script>
                    function tree_refresh() {
//                        $('#treeview_div').load(
//                                                location.href + ' #treeview_div'
//                                                );
                        document.location.reload(true);
//                        $('#treeview_div').load('/MENUCNMANAGER/treeview.jsp',1000);
//                        $('#treeView').jstree().refresh(true);
//                        settings = jQuery("#treeView").jstree('get_settings'); 
//                        //// to get jstree settings object, #data_tree is a yours jstree object//then :
//                        settings.json_data.ajax.url = '/MENUCNMANAGER/treeview.jsp'; //and to save new settings:
//                        jQuery("#treeView").jstree('set_settings', settings);//you must reload ex :
//                        jQuery.jstree._reference("#treeView").load_node_json( - 1, false, false); 
//                        //first parameter is node to reload, to reload main root node use - 1
                    }
                </script>
                </td>
                </tr>
                <tr>
                    <td>
                        <div  style="float: right; width: 100%;" >          
                            <div id="loadingImageDiv" style="display: none;">
                                <img id="loadingImage" src='img/loading.gif' border='0' >
                            </div>
                            <div id="contentDiv"></div>
                            <div id="messageDiv"></div>      
                            <input type="hidden" id="group_Arr" value="" name="groupsStr"/>
                        </div>
                    </td>
                </tr>
            </table>
        </div>                
    </s:form>
</div>
<div  style="
      float: left;
      height:100%;
      width: 20%;              
      background: #eeeeee;"
      id="treeview_div"
      >    
    <jsp:include page="/MENUCNMANAGER/treeview.jsp" />      
</div>    



<script>
    $(function() {
        //Khi thay doi        
        $('#selectedGroup').prop('disabled', 'disabled');
        $("#userQuerySubmit").trigger("click"); 
    });
    
//    $('#selectedGroup').prop('disabled', 'disabled');
</script>
