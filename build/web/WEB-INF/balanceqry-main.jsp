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
//            alert('abc');
            return cancelDefaultAction(evt);
        }
    }
    ;
    function clk_glkhtd() {
        var lstPos = "";
        $('#treeView').jstree("get_checked", null, true).each(
                function() {
                    lstPos = lstPos + this.id + ',';
                });
        document.getElementById("selectedPos").value = lstPos;
        var lj_searchType = document.getElementById('searchTypeChk').checked;
        if (lj_searchType === true) {
            document.getElementById('searchTypeHdd').value = '1';
        } else {
            document.getElementById('searchTypeHdd').value = '0';
        }
//        alert(lj_searchType);
    }
    $.subscribe('before-next', function(event, data) {
        $("#contentDiv").empty();
        $("#contentDiv").hide();
//        alert('abc');
        $("#loadingImageDiv").show();
//        document.getElementById('loadingImage').innerHTML = "<img src='img/loading.gif' border='0'>";        
    });

    $.subscribe('after-next', function(event, data) {
//        alert('xyz');
//        $("#loadingImageDiv").empty();    
        $("#loadingImageDiv").hide();
        $("#contentDiv").show(); //.slideDown('slow');
    });
    $.subscribe('showDialog', function showDialog() {
        var lstPos = "";
        $('#treeView').jstree("get_checked", null, true).each(
                function() {
                    lstPos = lstPos + this.id + ',';
                });
        document.getElementById("selectedPos").value = lstPos;
//        alert("lstPos1");
        $('#printPreviewDialog').dialog('open');
//        alert("lstPos2");
    });

    //CuongBM: 18Jul14
    //Desc: Xu ly truong datatime picker
    //      Them mask khi nhap date
    $(function() {
        new DateMask("dd/MM/yyyy", "reportDate");
    });

    function formatDate(value)
    {
        return value.getDate() + "/" + (value.getMonth() + 1) + "/" + value.getFullYear();
    }

    $.subscribe('onPeriodChange', function(event, data) {
//        alert('ls_periodCode');
        var ls_selectObj = document.getElementById('selectedPeriod');
        var ls_periodCode = ls_selectObj.value;
        var ls_today = new Date();
        switch (ls_periodCode) {
            case '/1D/':
                var lj_setDate = (ls_today.getDate() - 1) + "/" +
                        (ls_today.getMonth() + 1) + "/" + ls_today.getFullYear();
                document.getElementById("selectedrptDate").value = lj_setDate;
                break;
            case '/1M/':
                var lastDayOfMonth = new Date(ls_today.getFullYear(), ls_today.getMonth(), 0);
                var lastDayOfMonth_fstr = formatDate(lastDayOfMonth);//lastDayOfMonth.toLocaleDateString();
//            alert(lastDayOfMonth_fstr);
                document.getElementById("selectedrptDate").value = lastDayOfMonth_fstr;
                break;
            case '/1Q/':
                var quarter = Math.floor((ls_today.getMonth() / 3));
                var firstDate = new Date(ls_today.getFullYear(), quarter * 3 - 3, 1);
                var lastDayOfQuater = new Date(firstDate.getFullYear(), firstDate.getMonth() + 3, 0);
                var lastDayOfQuater_fstr = formatDate(lastDayOfQuater); //lastDayOfQuater.toLocaleDateString();
//                alert(lastDayOfQuater_fstr);
                document.getElementById("selectedrptDate").value = lastDayOfQuater_fstr;
                break;
            case '/1Y/':
//            alert('lastDayOfYear_fstr');
                var lastDayOfYear = new Date(ls_today.getFullYear() - 1, 12, 0);
                var lastDayOfYear_fstr = formatDate(lastDayOfYear);//lastDayOfYear.toLocaleDateString();
//            alert(lastDayOfYear_fstr);
                document.getElementById("selectedrptDate").value = lastDayOfYear_fstr;
                break;
        }
    });</script>
<style>
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
<%
    session.setAttribute("startTreeGLKHTDRecursive", null);
%>   

<div id="maindiv">
    <s:form theme="simple" id="balanceForm">
        <div  style="
              float: top;
              height:62px;
              z-index:1;">
            <table width="100%" border="0">
                <tr>
                    <td style="width: 20%">
                        Ngày báo cáo: 
                        <sj:datepicker name="reportDate" value=""  
                                       onblur="validatedate(this.value)"
                                       placeholder="DD/MM/YYYY" changeYear="true" 
                                       changeMonth="true" displayFormat="dd/mm/yy"
                                       id="selectedrptDate" size="15"/>
                        <script>
                            var lj_curDate = new Date();
                            var lj_setDate = (lj_curDate.getDate() - 1) + "/" +
                                    (lj_curDate.getMonth() + 1) + "/" + lj_curDate.getFullYear();
                            document.getElementById("selectedrptDate").value = lj_setDate;
                        </script>
                    </td>            
                    <td rowspan="2" align="right" valign="middle">
                        <table style="width: 100%;">
                            <tr>
                                <td>
                                    <div style="float: left;">                                                        
                                        <input type="radio" name="balanceSheetType" value="01">TK Cấp 3 Nội bảng
                                        <input type="radio" name="balanceSheetType" value="02">TK Cấp 3 Ngoại bảng
                                        <input type="radio" name="balanceSheetType" value="03" checked>TK GL Nội bảng
                                        <input type="radio" name="balanceSheetType" value="04">TK GL Ngoại bảng
                                    </div>
                                    <div style="float:right;">
                                        <s:url id="viewUrl" action="viewBalanceSheet.action">
                                            <s:param name="buttonId" value="1"></s:param>
                                        </s:url>
                                        <sj:submit id="addService1"  href="%{viewUrl}" value="Xem cân đối" targets="contentDiv"
                                                   formIds="balanceForm" onclick="clk_glkhtd()" button="true"
                                                   onBeforeTopics="before-next"
                                                   onCompleteTopics="after-next"
                                                   cssClass="metroButtonStyle" theme="simple"/>
                                    </div>
                                    <div style="float:right;">
                                        <s:url id="printUrl" action="printBalanceSheet.action">
                                            <s:param name="buttonId" value="2"></s:param>
                                        </s:url>
                                        <sj:submit value="In cân đối" 
                                                   onClickTopics="showDialog" button="true"                                               
                                                   cssClass="metroButtonStyle" theme="simple"/>
                                        <sj:submit id="addService2"  href="%{printUrl}" 
                                                   value="In cân đối" targets="contentDiv"
                                                   formIds="balanceForm" 
                                                   onBeforeTopics="before-next" button="true"                                               
                                                   onCompleteTopics="after-next"
                                                   cssClass="metroButtonStyle" theme="simple" 
                                                   cssStyle="display:none;"/>
                                    </div>
                                    <div style="float:right;">
                                        <s:url id="export2ExcelUrl" action="exportBalance2Excel.action">
                                            <s:param name="buttonId" value="5"></s:param>
                                        </s:url>
                                        <sj:submit id="addService5"  href="%{export2ExcelUrl}" 
                                                   value="Xuất Excel" targets="contentDiv"
                                                   formIds="balanceForm"  
                                                   button="true"
                                                   onclick="clk_glkhtd()"
                                                   onBeforeTopics="before-next"
                                                   onCompleteTopics="after-next"
                                                   cssClass="metroButtonStyle" theme="simple"/>
                                    </div>
                                    <div style="float:right;">
                                        <s:url id="checkUrl" action="checkAccountProperty.action">
                                            <s:param name="buttonId" value="4"></s:param>
                                        </s:url>
                                        <sj:submit id="addService4"  href="%{checkUrl}" value="Kiểm tra N/C" targets="contentDiv"
                                                   formIds="balanceForm" onclick="clk_glkhtd();" button="true"
                                                   cssClass="metroButtonStyle" theme="simple"/>
                                    </div>
                                    <div style="float:right;">
                                        <s:url id="searchUrl" action="searchBalanceSheet.action">
                                            <s:param name="buttonId" value="3"></s:param>
                                        </s:url>
                                        <sj:submit id="addService3"  href="%{searchUrl}" value="Tìm kiếm" targets="contentDiv"
                                                   formIds="balanceForm" 
                                                   onclick="clk_glkhtd();" 
                                                   button="true"
                                                   onBeforeTopics="before-next"
                                                   onCompleteTopics="after-next"
                                                   cssClass="metroButtonStyle" theme="simple"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div style="float:left; ">
                                        <s:textfield id="accountSearchTxt" name="searchAccount" placeholder="Tìm kiếm..." size="28"/>  
                                        &nbsp;&nbsp;
                                        <input type="checkbox" name="searchTypeChk" id="searchTypeChk"/><b>trên Dữ liệu đã duyệt</b>
                                        <input type="hidden" name="searchType" id="searchTypeHdd" value=""/>
                                    </div>
                                    <div style="float:right; margin-top: 5px; padding-right: 300px;">   
                                        
                                        <input type="hidden" id="username_ID" value="${sessionScope.username}" />
                                        
                                        <a href="#"
                                           onclick="callDirectLink('CD_dieuchinh_tk?');"><u>Điều chỉnh TK</u></a>
                                        &nbsp;&nbsp;
                                        <a href="#"
                                           onclick="callDirectLink('CD_upload_file?');"><u>Upload File</u></a>
                                    </div>

                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td style="width: 20%">
                        Kỳ báo cáo: &nbsp;&nbsp;&nbsp;
                        <s:url var="buildComboUrl" action="buildPeriodCombo"></s:url>
                        <sj:select href="%{buildComboUrl}" 
                                   name="selectedPeriod"
                                   id="selectedPeriod"
                                   list="periodList" 
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false"   
                                   onChangeTopics="onPeriodChange"
                                   theme="simple"     
                                   ></sj:select>                        
                            <!--                        headerKey="-1"
                                                    headerValue="---Chọn kỳ báo cáo---" theme="simple"                                   -->
                        </td>                        
                    </tr>            
                </table>
            </div>
            <hr/>
            <div  style="
                  float: left;
                  height:600px;
                  width: 20%;
                  z-index:1;
                  overflow:scroll;"
                  id="balancemainviewdiv">
            <s:url var="echoO" action="buildPosTreeView"/>
            <sjt:tree  
                id="treeView"
                jstreetheme="default"
                rootNode="nodes"
                nodeIdProperty="id"
                nodeTitleProperty="name"
                href="%{echoO}"
                childCollectionProperty="children"
                checkbox="true"
                />        
        </div>  
        <s:hidden name="selectedPos" id="selectedPos" value=""/>
        <s:hidden name="printType" id="printType" value=""/>    
        <s:hidden name="printSize" id="printSize" value=""/>    
        <div  style="float: right;height:600px; width: 80%; z-index:2;overflow:scroll;" >          
            <div id="loadingImageDiv" style="display: none;">
                <img id="loadingImage" src='img/loading.gif' border='0' >
            </div>
            <div id="contentDiv"/>        
        </div>
    </s:form>           
</div>
<sj:dialog 
    id="printPreviewDialog" 
    buttons="{ 
    'In':function() {          
    document.getElementById('printType').value = $('#printTypeRadio:checked').val();
    document.getElementById('printSize').value = $('#pageSizeRadio:checked').val();
    $('#addService2').trigger('click');   
    $( this ).dialog( 'close' ); 
    },
    'Huỷ bỏ':function() { 
    document.getElementById('printType').value = $('#printTypeRadio:checked').val();        
    $( this ).dialog( 'close' ); } 
    }"     
    autoOpen="false" 
    modal="true" 
    title="In..."
    position="{my:'top', at:'center', of:$('#maindiv')}"
    >    
    <div>
        <table style="width: 100%;border: 1pt;">
            <tr>
                <td>
                    <input type="radio" id="pageSizeRadio" name="pageSizeRadio" value="01" checked>Khổ giấy A4
                </td>            
                <td>
                    <input type="radio" id="pageSizeRadio" name="pageSizeRadio" value="02">Khổ giấy A3
                </td>
            </tr>
            <tr>
                <td colspan="2">      
                    <br/>
                    <hr/>            
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="radio" id="printTypeRadio" name="printTypeRadio" value="01">Máy in                                
                </td>            
            </tr>
            <tr>
                <td colspan="2">      
                    <br/>
                    <!--                    <hr/>            -->
                </td>
            </tr>
            <tr>
                <td style="width: 50%;">
                    <input type="radio" id="printTypeRadio" name="printTypeRadio" value="02" checked>PDF
                </td>
                <td>                
                    <input type="radio" id="printTypeRadio" name="printTypeRadio" value="03">Excel
                </td>               
            </tr>                                 
        </table>
    </div>
</sj:dialog>


<script>
    function callDirectLink(link) {
        var ht = screen.availHeight / 3;
        var wt = screen.availWidth / 3 + 50;

        var userName = $("#username_ID").val();
        
//        alert(userName);

        var resize = window.open(link + "userName=" + userName + ""
                + "&random=" + Math.random(),
                "IMS_REPORTS0", "height=" + (ht) + ",width=" + (wt)
                + ",left=0,top=0,directories=no,status=no,menubar=no,\n\personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        if (navigator.userAgent.indexOf('Chrome') !== -1
                && parseFloat(
                        navigator.userAgent.substring(
                                navigator.userAgent.indexOf('Chrome') + 7
                                ).split(' ')[0]) >= 15) {
            resize.resizeBy(wt, ht);
        } else {
            resize.resizeTo(wt, ht);
        }
        resize.moveTo(150, 150);
        resize.focus();
    }

</script>
