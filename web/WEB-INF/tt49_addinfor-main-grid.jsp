<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head/>
<style >   
</style>
<script>
    function message(){
        alert('Dữ liệu đã được cập nhật thành công.');
    }
</script>
<div style="padding-left: 3px;">    
    <div id="mygridfilter"></div>
    <s:url id="remoteurl" action="listAddInfor.action" />
    <s:url var="editurl" action="editAddInfor.action"/>    
    <sjg:grid id="mygrid"
              caption="Thông tin ..."
              dataType="json"
              href="%{remoteurl}"    
              editurl="%{editurl}"              
              editinline="false"
              pager="true" 
              gridModel="addInforViewList"
              rowNum="15" 
              navigator="true"
              navigatorAdd="false"
              navigatorDelete="false"
              navigatorEdit="true"
              navigatorEditOptions="{height:150,width:500,
              reloadAfterSubmit:true,
              editCaption:'Thay đổi thông tin',
              bSubmit: 'Cập nhật',
              bCancel: 'Huỷ bỏ',                
              closeAfterEdit:true,
              closeOnEscape:true,
              afterComplete: message}"              
              navigatorRefresh="false"
              navigatorSearch="false"
              navigatorView="false"          
              rowList="15,30,45"                   
              autowidth="true"                 
              multiselect="true"
              rownumbers="true"                   
              >    
        <sjg:gridColumn name="code" title="Mã" width="60" key="true"
                        editable="false"/>
        <sjg:gridColumn name="description" index="description" title="Mô tả" 
                        width="200"
                        editable="false"/>
        <sjg:gridColumn name="d1" index="d1" title="STT" sortable="false" 
                        search="false" width="40"
                        editable="false"/>
        <sjg:gridColumn name="d2" index="d2" title="Tên" sortable="false" 
                        search="false" width="100"
                        editable="true"/>
        <sjg:gridColumn name="d3" index="d3" title="Chức danh" sortable="false" 
                        search="false" width="150"
                        editable="true"/>
        <sjg:gridColumn name="d4" index="d4" title="Thành viên" sortable="false" 
                        search="false" width="100"
                        editable="true"/>    
    </sjg:grid>
    <script type="text/javascript">
        $(document).ready(function() {
            $("#mygridfilter").jqGrid('filterGrid', '#mygrid', {
                autosearch: false,
                gridNames: true,
                formtype: 'vertical',
                enableSearch: true,
                enableClear: true,
                gridModel: true,
                buttonclass: 'ui-state-default ui-corner-all'
            });
        });

    </script>
</div>