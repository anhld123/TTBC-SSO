<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:head/>
<sj:head/>
<style>
    .metroButtonStyle {
        font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
        display: block;
        color: rgb(255, 255, 255);
        text-decoration: none;
        text-align: center;        
        width: 90px;
        height: 25px;
        padding: 5px;
        margin: 0px 0px 0px 0px;
        font-size: 12px;
        background: none repeat scroll 0 0 #808080;
        color: #FFF;
        border: 0px none;
        border-radius: 1px 1px 1px 1px;        
    }
    .metroButtonStyle:hover {
        background: #018c3b;
    }
    .metroButtonStyle:active {
        background: #DCDCDC;
    }    

    .main_view_form {
        padding:0px;
        width:100%;    
        background:#f9f9f9;
        border:1px solid #ccc;
        font-family:  Arial;
        font-size: 12pt;
    }
</style>
<script>
    $.subscribe('before-next', function(event, data) {
        $("#contentDiv").empty();
        $("#contentDiv").hide();
        $("#loadingImageDiv").show();
    });

    $.subscribe('after-next', function(event, data) {
        $("#loadingImageDiv").hide();
        $("#contentDiv").slideDown('slow');
    });
</script>
<div style="padding-left: 5px; width: 100%; ">
    <div class="main_view_form">    
        <p style="text-decoration: underline;padding-left: 5px;font-size: 12pt;font-weight: bold;"> 
            Cấu hình báo cáo
        </p>
        <s:form theme="simple" id="reportGroupForm">        
            <table width="100%" border="2px" 
                   style="border-collapse: collapse; border-color: #DCDCDC;
                   border-style: solid;" >
                <tr style="line-height: 25px;">
                    <td style="width: 10%;">
                        <p style="font-family:  Arial;font-size: 11pt;">Nhóm báo cáo:</p>
                    </td>        
                    <td style="width: 30%;">
                        <s:url var="buildGroupComboUrl" action="buildGroupCombo"></s:url>
                        <sj:select href="%{buildGroupComboUrl}" 
                                   name="selectedGroup"
                                   id="selectedGroup"
                                   list="rptGroupList"    
                                   onChangeTopics="reloadModuleList"
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false" 
                                   headerKey="NULL"
                                   headerValue="--- Chọn nhóm báo cáo ---" theme="simple"
                                   value="D"
                                   ></sj:select>                       
                        </td>         
                        <td style="width: 10%;">                            
                            <p style="font-family:  Arial;font-size: 11pt;
                               padding-left: 3px;" 
                               >Mã báo cáo:</p>
                        </td>
                        <td style="width: 30%;">
                        <s:url var="buildGroupComboUrl" action="buildGroupCombo"></s:url>
                        <sj:select href="%{buildGroupComboUrl}" 
                                   name="selectedReport"
                                   id="selectedReport"
                                   list="rptReportList"      
                                   reloadTopics="reloadModuleList"
                                   formIds="reportGroupForm" 
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false" 
                                   headerKey="NULL"
                                   headerValue="--- Chọn báo cáo ---" theme="simple"
                                   ></sj:select>                       
                        </td> 
                        <td style="width: 320px;" align="center">                                 
                        <s:url var="viewUrl" action="viewReport.action"></s:url>                                                                              
                        <sj:a id="addService1"  href="%{viewUrl}" targets="contentDiv"
                              formIds="reportGroupForm"
                              onBeforeTopics="before-next"
                              onCompleteTopics="after-next">
                    <u><strong>&gt;&gt;Truy vấn</strong></u></sj:a>  
                    &nbsp;      
                <s:url var="disableUrl" action="disableReport.action"></s:url>                                                                              
                <sj:a id="addService2"  href="%{disableUrl}" targets="contentDiv"
                      formIds="reportGroupForm"
                      onBeforeTopics="before-next"
                      onCompleteTopics="after-next">
                    <u>&gt;&gt;Vô hiệu</u></sj:a>    
                    &nbsp;
                <s:url var="enableUrl" action="enableReport.action"></s:url>                                                                              
                <sj:a id="addService3"  href="%{enableUrl}" targets="contentDiv"
                      formIds="reportGroupForm"
                      onBeforeTopics="before-next"
                      onCompleteTopics="after-next">
                    <u>&gt;&gt;Kích hoạt</u></sj:a>                              
                    </td>
                    </tr>                     
                </table>
        </s:form>        
    </div>         
</div>
<div id="contentDiv" style="padding-left: 5px; width: 100%; "></div>


