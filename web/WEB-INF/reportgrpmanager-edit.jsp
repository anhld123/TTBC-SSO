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
    .fullcell {
        width: 100%;
        box-sizing: border-box;
    }
</style>

<div style="padding-left: 5px;">
<p style="text-decoration: underline;font-size: 12pt;font-weight: bold;"> 
    Cập nhật thông tin Nhóm báo cáo
</p>
<hr/>
<div id="edit_reportgroup_form" class="usergroup_form" > 
    <s:form action="ReportGroup_update" theme="simple">
        <table style="width: 100%;" cellspacing="5px">
            <tr>
                <td style="width: 35%;">
                    <s:label value="Nhóm báo cáo"/>
                </td>
                <td style="width: 65%;">
                    <s:textfield label="Nhóm" key="priGroupCode" readonly="true" cssClass="fullcell"/> 
                </td> 
            </tr>
            <tr>
                <td><s:label value="Mô tả"/></td>
                <td><s:textfield label="Mô tả" key="priGroupDesc" cssClass="fullcell"/></td>
            </tr>
            <tr>
                <td><s:label value="Tên viết tắt"/></td>
                <td><s:textfield label="Tên viết tắt" key="priGroupAlias" cssClass="fullcell"/></td>
            </tr>
            <tr>
                <td><s:label value="Phân loại"/></td>
                <td><s:textfield label="Phân loại" key="priGroupType" cssClass="fullcell"/></td>
            </tr>
            <tr>
                <td><s:label value="Trạng thái"/></td>
                <td>
                    <s:select headerKey="-1"
                          list="yesnoList" 
                          name="priGroupStatus" 
                          listKey="sKey"
                          listValue="sDesc"
                        />  
                </td>
            </tr>
            <tr>
                <td><s:label value="Menu Id"/></td><td>                    
                    <s:url var="buildMenuComboUrl" action="reportBuildCombo"></s:url>
                    <sj:select href="%{buildMenuComboUrl}" 
                               name="priMenuId"
                               id="priMenuId"
                               list="menuIdList"            
                               value="%{priMenuId}"
                               onChangeTopics="reloadModuleList"
                               listKey="sKey"
                               listValue="sDesc"
                               emptyOption="true" 
                               headerKey="-1"
                               headerValue="--- Chọn Menu Id ---" theme="simple"
                               ></sj:select> 
                </td>
            </tr>
            <tr>
                <td colspan="4">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2" align="right"><s:submit label="Cập nhật" value="Cập nhật"/>
                <input type="button" label="Quay lại" value="Quay lại" onclick="javascript:history.back();"/>
                </td>
            </tr>
        </table>
    </s:form>
</div>
</div>