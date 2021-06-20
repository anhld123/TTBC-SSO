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

    .normal_style {
        font-family: Arial;font-size: 12pt;
        font-size: 12pt;
    }
</style>
<div id="maindiv">
    <s:form id="fastparam_config_form" theme="simple">
        <p style="font-family: Arial;font-size: 12pt;
           text-decoration: underline;padding-left: 5px;font-size: 12pt;font-weight: bold;"> 
            Quản lý Tham số Báo cáo nhanh
        </p>
        <div id="_control_div" style="padding-left: 5px;">
            <p class="normal_style"> Chọn phân hệ cấu hình: 
                <s:url var="buildComboUrl_0" 
                       action="FRCFG_Build_Combo"></s:url>
                <sj:select href="%{buildComboUrl_0}" 
                           name="module"
                           id="module_ID"
                           list="modules" 
                           listKey="sKey"
                           listValue="sDesc"
                           emptyOption="false"                                                          
                           theme="simple"     
                           ></sj:select>
                    &nbsp;&nbsp;
                <s:url var="displayFormUrl" 
                       action="FRCFG_Display_Form.action"></s:url>                                                                              
                <sj:a id="_displayLink_Id"  
                      href="%{displayFormUrl}" 
                      targets="contentDiv"
                      formIds="fastparam_config_form"
                      onBeforeTopics="before-next"
                      onCompleteTopics="after-next">
                <u><strong>Truy vấn</strong></u></sj:a> 
                <script>
                    $.subscribe('before-next', function(event, data) {
                        $("#contentDiv").empty();
                        $("#contentDiv").hide();
                        $("#loadingImageDiv").show();
                    });

                    $.subscribe('after-next', function(event, data) {
                        $("#loadingImageDiv").hide();
                        $("#contentDiv").show();
                    });
                </script>
                </p>
                <hr/>
            </div>

            <div>          
                <div id="loadingImageDiv" style="display: none;">
                    <img id="loadingImage" src='img/loading.gif' border='0' >
                </div>
                <div id="contentDiv"/>        
            </div>
    </s:form>

</div>