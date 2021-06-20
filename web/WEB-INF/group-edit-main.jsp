<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:head/>
<sj:head jqueryui="true" jquerytheme="flick"/>

<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/Checkdate.js"></script>

<style>
    body,td,th,font{ font-family:Tahoma; font-size:12px; }
    .readonly {
        background: whitesmoke;
    }
</style>

<div id="maindiv">
    <s:form id="group_edit_form" theme="simple">
        <p style="font-family: Arial;font-size: 12pt;
           text-decoration: underline;padding-left: 5px;font-size: 12pt;font-weight: bold;"> 
            Quản lý Thông tin tổ tại đơn vị
        </p>
        <s:url id="viewurl" action="view_dmto_table.action" />
        <s:url id="editurl" action="edit_dmto_table.action" />

        <div id="mygridfilter"></div>
        <sjg:grid id="gridtable"
                  caption="Thông tin tổ/nhóm ..."
                  dataType="json"
                  href="%{viewurl}" 
                  editurl="%{editurl}"                  
                  pager="true" 
                  gridModel="groups"
                  rowNum="15" 
                  navigator="true"
                  navigatorAdd="false"
                  navigatorDelete="false"
                  navigatorEdit="false"
                  navigatorEditOptions="{height:150, width:425,closeAfterEdit:true}"
                  navigatorRefresh="false"                  
                  navigatorSearch="true"                  
                  navigatorView="true"          
                  rowList="15,30,45"                   
                  autowidth="true"                 
                  multiselect="false"
                  editinline="false"
                  rownumbers="true"  
                  cellEdit="true"
                  cellurl="%{editurl}"
                  shrinkToFit="false"                  
                  onSelectRowTopics="rowselect"                         
                  viewrecords="true"                            
                  >    
            <sjg:gridColumn name="group_id" index="group_id" title="Mã tổ" 
                            sortable="true" 
                            cssClass="readonly"
                            search="true" 
                            searchoptions="{sopt:['cn']}"
                            width="60"/>
            <sjg:gridColumn name="leader_group_name" index="leader_group_name" title="Tên tổ trưởng" 
                            sortable="false" editable="false"    
                            cssClass="readonly"
                            search="true" 
                            searchoptions="{sopt:['cn']}"
                            width="300"
                            edittype="textarea"/>            
            <sjg:gridColumn name="mass_org" 
                            index="mass_org" 
                            title="Tổ chức hội"                                                         
                            cssClass="readonly"
                            search="true"                            
                            searchoptions="{sopt:['cn']}"
                            align="right"
                            width="80"/>
            <sjg:gridColumn name="commune_id" index="commune_id" title="Mã thôn"                             
                            cssClass="readonly"
                            search="true" 
                            searchoptions="{sopt:['cn']}"
                            width="60"/>            
            <sjg:gridColumn name="standard_flg" index="standard_flg" title="Thành lập đúng QĐ" 
                            sortable="false"          
                            search="false"
                            width="90"
                            editable="true"
                            edittype="select"
                            editoptions="{value:'Y:Y;N:N'}"
                            align="center"
                            />        
            <sjg:gridColumn name="corrupt_flg" index="corrupt_flg" title="Tham ô,chiếm dụng" 
                            sortable="false"        
                            search="false"
                            width="90"
                            editable="true"
                            edittype="select"
                            editoptions="{value:'Y:Y;N:N'}"
                            align="center"
                            />        
             <sjg:gridColumn name="status" index="status" title="Trạng thái" 
                            sortable="false"        
                            search="false"
                            width="90"                            
                            align="center"                                                        
                            editable="false"       
                            cssClass="readonly"
                            />       
        </sjg:grid>        

<!--        <script type="text/javascript">
            $(document).ready(function() {
                $("#mygridfilter").jqGrid('filterGrid', '#gridtable', {
                    autosearch: false,
                    gridNames: true,
                    formtype: 'vertical',
                    enableSearch: true,
                    enableClear: true,
                    gridModel: true,
                    buttonclass: 'ui-state-default ui-corner-all'
                });
            });

        </script>-->

        <c:set var="permit_str" value="${sessionScope.permit}"/>        
        <c:set var="permit_index" value="${fn:substring(permit_str, 1, 2)}" />                

        <c:if test="${permit_index == '1' }">
            <s:url id="update_url" action="sync_dmto_table.action" />
            <div id="save_buttom_div" 
                 style='float:right; padding-top: 10px;'>            
                <s:url id="url_updateMainTbl" action="var_updateMainTable.action" />
                <sj:submit                            
                    id="sja_update02"
                    href="%{update_url}"     
                    targets="messageDiv"
                    formIds="group_edit_form"
                    onBeforeTopics="before-next"
                    onCompleteTopics="after-next"
                    button="true"
                    value="Cập nhật"
                    />                                        
            </div>
        </c:if>        
        <div style="padding-top: 10px;">          
            <div id="loadingImageDiv" style="display: none;">
                <img id="loadingImage" src='img/loading.gif' border='0' >
            </div>            
            <div id="messageDiv"/>                
        </div>
        <script>
            $.subscribe('before-next', function(event, data) {                
                $("#messageDiv").empty();
                $("#messageDiv").hide();
                $("#loadingImageDiv").show();
            });

            $.subscribe('after-next', function(event, data) {
                $("#loadingImageDiv").hide();
                $("#messageDiv").show();
            });
        </script> 
    </s:form>


</div>