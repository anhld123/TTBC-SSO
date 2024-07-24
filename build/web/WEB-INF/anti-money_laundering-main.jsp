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
    function cancelDefaultAction(e) {
        var evt = e ? e : window.event;
        if (evt.preventDefault)
            evt.preventDefault();
        evt.returnValue = false;
        return false;
    }
    function search(e) {
        if (e.keyCode === 13) {
            return cancelDefaultAction(evt);
        }
    }
    ;
    function clk_glkhtd() {
    }
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
        border: 1px solid black;
        border-collapse: collapse;
        padding: 0 0 0 5px;
    }
    .metroButtonStyle {
        font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
        display: block;
        color: rgb(255, 255, 255);
        text-decoration: none;
        text-align: center;
        width: 90px;
        height: 26px;
        padding: 5px;
        margin: 5px 0px 0px 5px;
        font-size: 12px;
        background: none repeat scroll 0 0 #808080;
        color: #FFF;
        border: 0px none;
        border-radius: 1px 1px 1px 1px;
        outline: 0px none;
    }
    .metroButtonStyle:hover {
        background: #018c3b;
    }
    .metroButtonStyle:active {
        background: #DCDCDC;
    }
    .ui-dialog{
        font-size: 12px;
    }     
</style> 
<div id="maindiv">
    <s:form theme="simple" id="anti-money_laundering_form">
        <div  style="
              float: top;
              height:62px;
              z-index:1;">
            <table width="100%" border="1">
                <tr>                
                    <td style="width: 18.3%">
                        Ngày báo cáo: 
                        <sj:datepicker name="reportDate" id="reportDate" value=""  
                                       placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"
                                       size="12"/>
                        <script>
                            var Dnow = new Date();
                            var giatri = (Dnow.getDate() - 1) + "/" + (Dnow.getMonth()+1) + "/" + Dnow.getFullYear();
                            document.getElementById("reportDate").value = giatri;
                        </script>
                    </td>                            
                    <td rowspan="2" align="right" valign="middle">
                        <table class="viewcontrolTbl">
                            <tr>
                                <td>
                                    <div style="float: left;">                                                        
                                        <input type="radio" name="launderingType" value="01" checked>Báo cáo giao dịch có giá trị lớn
                                        <input type="radio" name="launderingType" value="02">Báo cáo giao dịch chuyển tiền điện tử 
<!--                                        <input type="radio" name="launderingType" value="03">Tất cả dữ liệu trong tháng-->
                                    </div>
                                    <div style="float:right;">
                                        <s:url id="reexportUrl" action="AML_reexportAction.action">
                                            <s:param name="buttonId" value="1"></s:param>
                                        </s:url>
                                        <sj:submit id="addService1"  href="%{reexportUrl}" 
                                                   value="Xuất lại BC" targets="contentDiv"
                                                   formIds="anti-money_laundering_form" 
                                                   onclick="clk_glkhtd()" button="true"
                                                   onBeforeTopics="before-next"
                                                   onCompleteTopics="after-next"
                                                   cssClass="metroButtonStyle" theme="simple"/>
                                    </div>
                                    <div style="float:right;">
                                        <s:url id="exportUrl" action="AML_exportAction.action">
                                            <s:param name="buttonId" value="2"></s:param>
                                        </s:url>
                                        <sj:submit value="Xuất BC" 
                                                   onClickTopics="showDialog" button="true"                                               
                                                   cssClass="metroButtonStyle" theme="simple"
                                                   targets="contentDiv"
                                                   formIds="anti-money_laundering_form" 
                                                   onBeforeTopics="before-next"
                                                   onCompleteTopics="after-next"
                                                   href="%{exportUrl}"/>
                                    </div>                                    
                                    <div style="float:right;">
                                        <s:url id="viewUrl" action="AML_viewAction.action">
                                            <s:param name="buttonId" value="4"></s:param>
                                        </s:url>
                                        <sj:submit id="addService4"  href="%{viewUrl}" 
                                                   value="Xem BC" targets="contentDiv"
                                                   formIds="anti-money_laundering_form" 
                                                   onclick="clk_glkhtd();" 
                                                   button="true"
                                                   cssClass="metroButtonStyle" 
                                                   theme="simple"/>
                                    </div>
                                    <div style="float:right;">
                                        <s:url id="searchUrl" action="AML_searchAction.action">
                                            <s:param name="buttonId" value="3"></s:param>
                                        </s:url>
                                        <sj:submit id="addService3"  href="%{searchUrl}" 
                                                   value="Tìm kiếm" 
                                                   targets="contentDiv"
                                                   formIds="anti-money_laundering_form" 
                                                   onclick="clk_glkhtd();" 
                                                   button="true"
                                                   cssClass="metroButtonStyle" 
                                                   theme="simple"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div style="float:left; ">
                                        Tìm theo:
                                        <input type="radio" name="searchType" value="04" checked>Tất cả GD trong tháng
                                        <input type="radio" name="searchType" value="01">Mã giao dịch
                                        <input type="radio" name="searchType" value="02">Tài khoản
                                        <input type="radio" name="searchType" value="03">Tên khách hàng 
                                        &nbsp;&nbsp;
                                        <s:textfield id="accountSearchTxt" name="searchKey" placeholder="Tìm kiếm..." size="25"/>                                    
                                    </div>
                                </td>
                            </tr>
                            <tr><td></td></tr>
                        </table>
                    </td>
                </tr>                         
            </table>
        </div>
        <hr/>
<!--        <div  style="
              float: left;
              height:600px;
              width: 20%;
              z-index:1;
              overflow:scroll;"
              id="balancemainviewdiv">                                                    
        </div>                      -->
        <div  style="float: right;height:600px; width: 100%; z-index:2;overflow:scroll;" >          
            <div id="loadingImageDiv" style="display: none;">
                <img id="loadingImage" src='img/loading.gif' border='0' >
            </div>
            <div id="contentDiv"/>        
        </div>
    </s:form>           
</div>

