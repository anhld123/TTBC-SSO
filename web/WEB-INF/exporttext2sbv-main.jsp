<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<s:head/>
<sj:head/>

<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/Checkdate.js"></script>
<script>

    function js_changetopic() {
        var e = document.getElementById("selectedReport");
        var report = e.options[e.selectedIndex].value;
        if (report === "EX010001") {
            $("#js_select_id").show();
        } else {
            $("#js_select_id").hide();
        }
        if (report === "EX220002") {
            $("#to2sbvchkid").show();
        } else {
            $("#to2sbvchkid").hide();
        }
    }

    function openSelectWindow() {
        var ht1 = screen.availHeight - 100;
        var wt1 = 600;
        var left1 = (screen.width / 2) - (wt1 / 2);
        var top1 = 10;
        var e = document.getElementById("reportPeriod");
        var strPeriod = e.options[e.selectedIndex].value;
        var period = 4;
        if (strPeriod === "NULL")
        {
            alert('(Msg)Bạn chưa chọn kỳ báo cáo.');
            event.preventDefault();
        } else {
            if (strPeriod === "D")
                period = 1;
            else if (strPeriod === "10D")
                period = 2;
            else if (strPeriod === "15D")
                period = 3;
            else if (strPeriod === "M")
                period = 4;
            else if (strPeriod === "Q")
                period = 5;
            else if (strPeriod === "Y")
                period = 6;
            else
                period = 0;
            window.open('selectIndicator.action?reportPeriod=' + period, 'IMS_REPORTS',
                    "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1
                    + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        }
    }

    $.subscribe('beforediv1', function (event, data) {
        $("#contentDiv").empty();
        $("#contentDiv").hide();
        $("#loadingImageDiv").show();
    });

    $.subscribe('completediv1', function (event, data) {
        com.mudrick.onPeopleTableLoad();
        $("#loadingImageDiv").hide();
        $("#contentDiv").show();
        //$("#contentDiv").slideDown('slow');
    });

    function clk_glkhtd() {
        var lstPos = "";
        $('#treeView').jstree("get_checked", null, true).each(
                function () {
                    lstPos = lstPos + this.id + ',';
                });
        document.getElementById("selectedPos").value = lstPos;
    }
    $(function () {
        new DateMask("dd/MM/yyyy", "reportDate");
    });

    if (!com)
        var com = {};
    com.mudrick = {
        onPeopleTableLoad: function () {
            // Gets called when the data loads
            $("#studentTable th.sortable").each(function () {
                // Iterate over each column header containing the sortable class, so
                // we can setup overriding click handlers to load via ajax, rather than
                // allowing the browser to follow a normal link
                $(this).click(function () {
                    // "this" is scoped as the sortable th element
                    var link = $(this).find("a").attr("href");
                    $("#contentDiv").load(link, {}, com.mudrick.onPeopleTableLoad);
                    // Stop event propagation, i.e. tell browser not to follow the clicked link
                    return false;
                });
            });

            $("#contentDiv .pagelinks a").each(function () {
                // Iterate over the pagination-generated links to override also
                $(this).click(function () {
                    var link = $(this).attr("href");
                    var rplink = link.replace("gennew=Y", "gennew=N");
                    $("#contentDiv").load(rplink, {}, com.mudrick.onPeopleTableLoad);
                    return false;
                });
            });

            $("#contentDiv .pagelinks strong").each(function () {
                var htmlString = $(this).html();
                $(this).text("Trang " + htmlString);
            });
        }
    };

//    $(document).ready(function() {
//        // Load the initial rendering when the dom is ready.  Assuming you are injecting into a div
//        // with id "peopleData" that exists in the page.    
//        alert('a');
//        $("#contentDiv").load("http://localhost:8080/IMS_REPORTS/displayStudentList.action",{},com.mudrick.onPeopleTableLoad);
//    });
</script>

<%
    session.setAttribute("startTreeGLKHTDRecursive", null);
%>  
<div style="padding-left: 10px;">
    <div  style="
          float: top;          
          z-index:1;">    
        <p style="text-decoration: underline;padding-left: 5px;font-size: 12pt;font-weight: bold;"> 
            Chức năng xuất Text/Excel
        </p>                
    </div>
    <div  style="
          float: left;
          height:500px;
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
    <div  style="float: right;height:500px; width: 80%; z-index:2;" >          
        <s:form id="export2sbvform" theme="simple">
            <div>           
                <table style="border:solid 3px #cccccc;width: 75%; padding: 5px 5px 10px 5px;"
                       CELLSPACING="5px">
                    <tr>
                        <td>Báo cáo:</td>
                        <td>
                            <s:url var="buildComboUrl1" action="buildReportGroupCombo"></s:url>
                            <sj:select href="%{buildComboUrl1}" 
                                       name="selectedReport"
                                       id="selectedReport"
                                       list="lstRptGroupObj"    
                                       onChangeTopics="reloadModuleList"
                                       listKey="sKey"
                                       listValue="sDesc"
                                       emptyOption="false" 
                                       headerKey="NULL"
                                       onchange="js_changetopic(); toggleParameters(this.value);"
                                       headerValue="--- Chọn báo cáo ---" 
                                       theme="simple"></sj:select>
                            </td>
                        </tr>
                        <tr class="optional-parameter1">
                            <td>Ngày báo cáo:</td>
                            <td>                        
                            <sj:datepicker name="reportDate" value="%{new java.util.Date()}" onblur="validatedate(this.value)"
                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"
                                           id="selectedrptDate" size="15"/>
                        </td>                    
                    </tr>
                    <tr class="optional-parameter2">
                        <td>Kỳ báo cáo:</td>
                        <td>                        
                            <s:url var="buildComboUrl2" action="buildReportPeriodCombo"></s:url>
                            <sj:select href="%{buildComboUrl2}" 
                                       name="reportPeriod"
                                       id="reportPeriod"
                                       list="lstRptPeriod"      
                                       reloadTopics="reloadModuleList"
                                       listKey="sKey"
                                       listValue="sDesc"
                                       emptyOption="false" 
                                       headerKey="NULL"
                                       headerValue="--- Chọn kỳ báo cáo ---" 
                                       theme="simple"></sj:select>
                            </td>                    
                        </tr>

                        <tr class="optional-parameter3">
                            <td>Tên file:</td>
                            <td>                        
                            <s:url var="buildComboUrl3" action="buildPara"></s:url>
                            <sj:select href="%{buildComboUrl3}" 
                                       name="txtGetData"
                                       id="txtGetData"
                                       list="lstPara"
                                       reloadTopics="reloadModuleList"
                                       listKey="sKey"
                                       listValue="sDesc"
                                       emptyOption="false"
                                       headerKey="NULL"
                                       headerValue="--- Chọn file báo cáo ---"
                                       theme="simple" />
                            <!--                            <td>                        
                                                            <select name="txtGetData" id="txtGetData">                                                    
                                                                <option value="1">KYC_01</option>                                                    
                                                                <option value="2">KYC_02</option>
                                                                <option value="3">KYC_03</option>
                                                            </select> 
                                                        </td>                    -->
                    </tr>
                    <%--</s:else>--%>
                    <!--                        <tr>
                                                <td colspan="2"><br/></td>
                                            </tr>-->
                    <tr>
                        <td></td>
                        <td>
                            <a href="#" onclick="javascript:openSelectWindow();"
                               style="display:none;" id="js_select_id">
                                <u><b>&gt;&gt;Gửi lại PN</b></u>
                            </a>        
                            <p id="to2sbvchkid" style="display:none;">
                                <s:checkbox name="send2sbv"
                                            fieldValue="true" />
                                <b>Gửi NHNN</b>

                                <s:checkbox name="send9acc"
                                            fieldValue="true" />
                                <b>Gửi ngoại bảng</b>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr/>
                        </td>
                    </tr>                    
                    <tr>
                        <td></td>
                        <td align="left">
                            <div class="clicklink">
                                <s:url var="exportUrl" action="exportText2Sbv.action"></s:url>                                                                              
                                <sj:a id="export2Sbv"  href="%{exportUrl}" targets="contentDiv"
                                      formIds="export2sbvform" 
                                      onBeforeTopics="beforediv1"
                                      onclick="clk_glkhtd();"
                                      onCompleteTopics="completediv1"                                  
                                      ><u>&gt;&gt;Xuất File</u></sj:a> 
                                </div>
                            </td>
                        </tr>
                    </table> 
                <s:hidden name="selectedPos" id="selectedPos" value=""/> 
                <s:hidden name="gennew" id="gennew" value="Y"/> 
                <s:hidden name="sbvSendIndiGroup" id="ji_indigroup" value=""/>
                <div id="loadingImageDiv" style="display: none;">
                    <img id="loadingImage" src='img/loading.gif' border='0' >
                </div>
                <div id="contentDiv" style="width: 75%;"></div>                       
            </div>        
        </s:form>
    </div>    
</div>
<script>
    function toggleParameters(selectedValue) {
        const optionalRows1 = document.querySelectorAll('.optional-parameter1');
        const optionalRows2 = document.querySelectorAll('.optional-parameter2');
        const optionalRows3 = document.querySelectorAll('.optional-parameter3');

        if (selectedValue === 'EX050001') {
            // Ẩn các tham số
            optionalRows1.forEach(row => row.style.display = 'none');
            optionalRows2.forEach(row => row.style.display = 'none');
            optionalRows3.forEach(row => row.style.display = '');
        } else {
            // Hiển thị các tham số
            optionalRows1.forEach(row => row.style.display = '');
            optionalRows2.forEach(row => row.style.display = '');
            optionalRows3.forEach(row => row.style.display = 'none');
        }
    }
    ;
    toggleParameters();
</script>

