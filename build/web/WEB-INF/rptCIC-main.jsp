<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<sj:head jqueryui="false" jquerytheme="simple"/>
<s:head/>
<sj:head/>

<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/Checkdate.js"></script>

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

    $(function() {
        new DateMask("dd/MM/yyyy", "reportDate");
    });
</script>
<style>   
    .viewcontrolTbl {        
        width: 100%;
        border: 0px solid black;
        border-collapse: collapse;
        padding: 0 0 0 5px;
    }   
    .ui-dialog{
        font-size: 12px;
    }     
</style> 
<div id="maindiv">
    <s:form theme="simple" id="cic_report_form">
        <div  style="
              float: top;
              height:40px;
              z-index:1;">
            <table width="100%" border="1">
                <tr style="height: 30px;">                
                    <td style="width: 18.3%">
                        Ngày báo cáo: 
                        <sj:datepicker name="reportDate" id="reportDate" value="%{new java.util.Date()}"  
                                       placeholder="DD/MM/YYYY" 
                                       changeYear="true" 
                                       changeMonth="true" 
                                       displayFormat="dd/mm/yy"
                                       size="12"/>
                        <!--                        <script>
                                                    var Dnow = new Date();
                                                    var giatri = (Dnow.getDate() - 1) + "/" + (Dnow.getMonth() + 1) + "/" + Dnow.getFullYear();
                                                    document.getElementById("reportDate").value = giatri;
                                                </script>-->
                    </td>                            
                    <td rowspan="1" align="right" valign="middle">
                        <table class="viewcontrolTbl">
                            <tr>
                                <td>
                                    Tìm theo:
                                    <input type="radio" name="searchType" value="01" checked>Mã CN
                                    <input type="radio" name="searchType" value="02">Tên CN                                        
                                    <input type="radio" name="searchType" value="03">Tình Trạng
                                    &nbsp;&nbsp;
                                    <s:textfield id="accountSearchTxt" name="searchKey" placeholder="Tìm kiếm..." size="25"/>                                    
                                    &nbsp;&nbsp;
                                    tại:
                            <input type="radio" name="searchObj" value="01" checked> <u>Log</u>
                            <input type="radio" name="searchObj" value="02"> <u>Thống kê</u>
                            &nbsp;&nbsp;
                            <s:url id="searchUrl" action="CIC_searchAction.action"/>                                            
                            <sj:a id="cicBtn04"  href="%{searchUrl}" 
                                  targets="contentDiv"
                                  formIds="cic_report_form"                                                    
                                  onBeforeTopics="before-next"
                                  onCompleteTopics="after-next">
                                <strong><u>&gt;&gt;Tìm kiếm</u> </strong></sj:a>

                        </td>
                        <td align="right" style="padding-right: 20px;">         
                        <s:url id="genJobUrl" action="CIC_createjobAction.action"/>                                           
                        <sj:a id="cicBtn01"  href="%{genJobUrl}" 
                              targets="contentDiv"
                              formIds="cic_report_form"                                                    
                              onBeforeTopics="before-next"
                              onCompleteTopics="after-next"
                              ><strong>
                                <u>&gt;&gt;Tạo JOB(s)</u> </strong></sj:a>
                            &nbsp;&nbsp;                                        
                        <s:url id="viewLogUrl" action="CIC_viewlogAction.action"/>                                           
                        <sj:a id="cicBtn02"  href="%{viewLogUrl}" 
                              targets="contentDiv"
                              formIds="cic_report_form"                                                    
                              onBeforeTopics="before-next"
                              onCompleteTopics="after-next"
                              ><strong>
                                <u>&gt;&gt;Xem Log</u> </strong></sj:a>
                            &nbsp;&nbsp;
                        <s:url id="viewInforUrl" action="CIC_viewAction.action"/>                                           
                        <sj:a id="cicBtn03"  href="%{viewInforUrl}" 
                              targets="contentDiv"
                              formIds="cic_report_form"                                                    
                              onBeforeTopics="before-next"
                              onCompleteTopics="after-next"
                              ><strong>
                                <u>&gt;&gt;Thống kê</u> </strong></sj:a>


                        </td>
                    </tr>                           
                </table>
                </td>
                </tr>                         
                </table>
            </div>
            <hr/>
            <div  style="float: right;height:600px; width: 100%; z-index:2;overflow:scroll;" >          
                <div id="loadingImageDiv" style="display: none;">
                    <img id="loadingImage" src='img/loading.gif' border='0' >
                </div>
                <div id="contentDiv"/>        
            </div>
    </s:form>           
</div>

