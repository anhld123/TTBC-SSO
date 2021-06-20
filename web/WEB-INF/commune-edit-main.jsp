<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<%--<sj:head/>--%>
<sj:head jqueryui="true" jquerytheme="flick"/>

<style>
    body,td,th,font{ font-family:Tahoma; font-size:12px; }
    .readonly {
        background: whitesmoke;
    }
</style>
<div id="maindiv">
    <s:form id="commune_edit_form" theme="simple" action="sync_dmxa_table.action">
        <p style="font-family: Arial;font-size: 12pt;
           text-decoration: underline;padding-left: 5px;font-size: 12pt;font-weight: bold;"> 
            Quản lý Danh mục xã tại đơn vị
        </p>
        <s:url id="viewurl" action="view_dmxa_table.action" />
        <s:url id="editurl" action="edit_dmxa_table.action" />
        <s:url id="selecteusersurl" action="dmxa_dropdown_user.action" />
        <s:url id="selecteyesnosurl" action="dmxa_dropdown_yesno.action" />

        <sjg:grid id="gridtable"
                  caption="Danh mục xã ..."
                  dataType="json"
                  href="%{viewurl}" 
                  editurl="%{editurl}"
                  pager="true" 
                  gridModel="communes"
                  rowNum="30" 
                  navigator="true"
                  navigatorAdd="false"
                  navigatorDelete="false"
                  navigatorEdit="true"
                  navigatorEditOptions="{height:150, width:425,closeAfterEdit:true}"
                  navigatorRefresh="true"                  
                  navigatorSearch="false"
                  navigatorView="true"          
                  rowList="15,30,45"                   
                  autowidth="true"                 
                  multiselect="true"
                  editinline="false"
                  rownumbers="true"  
                  cellEdit="false"
                  cellurl="%{editurl}"
                  shrinkToFit="false"                  
                  onSelectRowTopics="rowselect"                         
                  viewrecords="true"                            
                  >    
            <sjg:gridColumn name="commune_id" index="commune_id" title="Mã xã" 
                            sortable="true" 
                            cssClass="readonly"
                            width="60"/>
            <sjg:gridColumn name="commune_name" index="commune_name" title="Tên xã" 
                            sortable="false" editable="false"    
                            cssClass="readonly"
                            width="300"
                            edittype="textarea"/>            
            <sjg:gridColumn name="xa135_flg" index="xa135_flg" title="Xã 135"                             
                            cssClass="readonly"
                            width="60"
                            align="center"/>
            <sjg:gridColumn name="gdx_flg" index="gdx_flg" title="Giao dịch xã"                             
                            cssClass="readonly"
                            width="60"
                            align="center"/>
            <sjg:gridColumn name="ngaygdx" index="ngaygdx" title="Ngày GDX"                             
                            cssClass="readonly"
                            width="60"
                            align="center"/>
            <sjg:gridColumn name="nongthonmoi_flg" 
                            index="nongthonmoi_flg" 
                            title="Nông thôn mới" 
                            sortable="false"                              
                            width="90"
                            editable="true"
                            edittype="select"
                            surl="%{selecteyesnosurl}"
                            editoptions="{ dataUrl : '%{selecteyesnosurl}' }"
                            align="center"
                            />                             
            <sjg:gridColumn name="status" index="status" title="Trạng thái"                             
                            cssClass="readonly"
                            width="60"
                            align="center"/>
            <sjg:gridColumn name="canbotdpt" 
                            index="canbotdpt" 
                            title="Cán bộ TD" 
                            sortable="false"                              
                            width="100"
                            editable="true"
                            edittype="select"
                            surl="%{selecteusersurl}"
                            editoptions="{ dataUrl : '%{selecteusersurl}' }"
                            align="center"
                            />           
        </sjg:grid>        

        
        
        <c:set var="permit_str" value="${sessionScope.permit}"/>        
        <c:set var="permit_index" value="${fn:substring(permit_str, 1, 2)}" />                

        <c:if test="${permit_index == '1' }">
            <div id="save_buttom_div" 
                 style='float:right; padding-top: 5px;'>            
                <sj:submit id="save_button_id"  href="#" 
                           value="Cập nhật" targets="content_div"
                           formIds="commune_edit_form"                                                    
                           button="true"                                                   
                           theme="simple"/>

            </div>
        </c:if>
        <div id="content_div"></div>    
    </s:form>

</div>