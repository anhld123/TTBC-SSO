<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head jqueryui="true" jquerytheme="flick"/>
<style >   
</style>
<div style="padding-left: 3px;">    
    <div id="mygridfilter"></div>
    <s:url id="remoteurl" action="listAccountInfor.action" />
    <sjg:grid id="mygrid"
              caption="Tài khoản ..."
              dataType="json"
              href="%{remoteurl}"           
              pager="true" 
              gridModel="acInforViewList"
              rowNum="15" 
              navigator="true"
              navigatorAdd="false"
              navigatorDelete="false"
              navigatorEdit="false"          
              navigatorRefresh="true"
              navigatorSearch="true"
              navigatorView="false"          
              rowList="15,30,45"                   
              autowidth="true"                 
              multiselect="false"
              rownumbers="true"                   
              >    
        <sjg:gridColumn name="account" frozen="true" index="account" title="Tài khoản" sortable="true" width="60"
                        search="true" searchoptions="{sopt:['eq','bw','ew','cn']}"/>
        <sjg:gridColumn name="name" index="name" title="Tên tài khoản" sortable="false"
                        search="false" width="400"/>
        <sjg:gridColumn name="sbv_gl" index="sbv_gl" title="SBV GL" sortable="false" 
                        search="false" width="40"/>
        <sjg:gridColumn name="gl_sl" index="gl_sl" title="GL SL" sortable="false" 
                        search="false" width="40"/>
        <sjg:gridColumn name="d_c_flg" index="d_c_flg" title="Tính chất N/C" sortable="false" 
                        search="false" width="40"/>
        <sjg:gridColumn name="ccy_cd" index="ccy_cd" title="Tiền tệ" sortable="false" 
                        search="false" width="30"/>    
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