<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:head/>
<sj:head/>
<style>    
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
<div style="padding-left: 5px;">
    <div id="banner" style="float: top;">
        <table style="width: 100%;">
            <tr>
                <td style="width: 10%;">
                    <s:url id="queryUrl" action="ReportGroup_list.action"/>    
                    <s:a id="groupQuerySubmit"  href="%{queryUrl}" button="true" theme="simple"
                         cssClass="clicklink">
                        <u> &gt;&gt;Truy vấn </u> </s:a>                                        
                </td>                              
            </tr>
        </table>
    </div>     
    <hr/>
    <div id="add_reportgroup_form" class="usergroup_form"> 
        <s:form action="ReportGroup_create" theme="simple">
            <table style="width: 100%;" cellspacing="5px">
                <tr>
                    <td style="width: 35%;"><s:label value="Nhóm báo cáo"/></td>
                    <td style="width: 65%;"><s:textfield key="priGroupCode"
                                 cssClass="fullcell"/></td>
                </tr>
                <tr>
                    <td><s:label value="Mô tả"/></td>
                    <td>
                        <s:textfield key="priGroupDesc" 
                                     cssClass="fullcell"/>
                    </td>
                </tr>
                <tr>
                    <td><s:label value="Tên viết tắt" /></td>
                    <td><s:textfield key="priGroupAlias" 
                                 cssClass="fullcell"/></td>
                </tr>
                <tr>
                    <td><s:label value="Phân loại BC" /></td>
                    <td><s:textfield key="priGroupType" 
                                 cssClass="fullcell"/></td>
                </tr>
                <tr>
                    <td>
                        <s:label value="Hiệu lực"/>
                    </td>
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
                    <td>
                        <s:label value="Menu Id"/>
                    </td>
                    <td>                        
                        <s:url var="buildMenuComboUrl" action="reportBuildCombo"></s:url>
                        <sj:select href="%{buildMenuComboUrl}" 
                                   name="priMenuId"
                                   id="priMenuId"
                                   list="menuIdList"        
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
                        <td colspan="2"></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="right">
                        <s:submit label="Thêm mới" value="Thêm mới"/>
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
                <td>Nhóm báo cáo</td>
                <td>Mô tả</td>
                <td>Tên viết tắt</td>
                <td>Phân loại nhóm</td>
                <td>Hiệu lực</td>
                <td>Menu Id</td>
                <td>Sửa</td>
                <td>Xoá</td>
            </tr>
            <s:iterator value="reportGroups" status="stat">
                <tr>
                    <td style="width: 3%; text-align: center;"><s:property value="#stat.count"/></td>
                    <td><s:textfield name="priGroupCode" theme="simple" cssClass="fullcell" readonly="true"/></td>
                    <td><s:textfield name="priGroupDesc" theme="simple" cssClass="fullcell" readonly="true"/></td>
                    <td><s:property value="priGroupAlias"/></td>
                    <td><s:property value="priGroupType"/></td>
                    <td><s:property value="priGroupStatus"/></td>
                    <td><s:property value="priMenuId"/></td>
                    <td style="text-align: center;">
                        <s:url id="editUrl" value="ReportGroup_edit.action">
                            <s:param name="reportGroupCode" value="priGroupCode"/>
                        </s:url>
                        <s:a href="%{editUrl}"><u>Sửa</u></s:a>
                        </td>                    
                        <td style="text-align: center;">
                        <s:url id="deleteUrl" value="ReportGroup_delete.action">
                            <s:param name="reportGroupCode" value="priGroupCode"/>
                        </s:url>
                        <s:a href="%{deleteUrl}"><u>Xoá</u></s:a>
                        </td>    
                    </tr>
            </s:iterator>
        </table>        
    </div>        
</div>
