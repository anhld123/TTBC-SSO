<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>

<s:head/>
<sj:head/>

<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/Checkdate.js"></script>

<script>
    function handleReportChange() {
        const report = $("#selectedReport").val();
        $("#js_select_id").toggle(report === "EX010001");
        $("#to2sbvchkid").toggle(report === "EX220002");

        if (report === 'EX050001') {
            $("#title_1, #title_3").hide();
            $("#title_2").show();
        } else if (['EX010060', 'EX010061', 'EX010062'].includes(report)) {
            $("#title_2").hide();
            $("#title_1, #title_3").show();
        } else {
            $("#title_2, #title_3").hide();
            $("#title_1").show();
        }
    }

    function openSelectWindow() {
        const strPeriod = document.getElementById("reportPeriod").value;
        if (strPeriod === "NULL") {
            alert('(Msg)Bạn chưa chọn kỳ báo cáo.');
            if (window.event)
                window.event.preventDefault();
            return;
        }

        const periodMap = {"D": 1, "10D": 2, "15D": 3, "M": 4, "Q": 5, "Y": 6};
        const period = periodMap[strPeriod] !== undefined ? periodMap[strPeriod] : 0;

        const ht1 = screen.availHeight - 100;
        const wt1 = 600;
        const left1 = (screen.width / 2) - (wt1 / 2);

        window.open('selectIndicator.action?reportPeriod=' + period, 'IMS_REPORTS',
                `height=${ht1},width=${wt1},left=${left1},top=10,directories=no,status=no,menubar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no`);
    }

    // Đăng ký AJAX Topics
    $.subscribe('beforediv1', function () {
        $("#contentDiv").empty().hide();
        $("#loadingImageDiv").show();
    });

    $.subscribe('completediv1', function () {
        com.mudrick.onPeopleTableLoad();
        $("#loadingImageDiv").hide();
        $("#contentDiv").show();
    });

    function clk_glkhtd() {
        let lstPos = "";
        $('#treeView').jstree("get_checked", null, true).each(function () {
            lstPos += this.id + ',';
        });
        document.getElementById("selectedPos").value = lstPos;
    }

    // Khởi tạo Dom Ready
    $(function () {
        new DateMask("dd/MM/yyyy", "reportDate");

        // Gắn sự kiện change cho báo cáo
        $("#selectedReport").on("change", handleReportChange);
        handleReportChange(); // Chạy load ban đầu
    });

    // Đối tượng Quản lý Table Ajax
    var com = com || {};
    com.mudrick = {
        onPeopleTableLoad: function () {
            // Xử lý sự kiện click Header để Sort
            $("#studentTable th.sortable").off('click').click(function () {
                const link = $(this).find("a").attr("href");
                $("#contentDiv").load(link, {}, com.mudrick.onPeopleTableLoad);
                return false;
            });

            // Xử lý Phân trang
            $("#contentDiv .pagelinks a").off('click').click(function () {
                const link = $(this).attr("href");
                const rplink = link.replace("gennew=Y", "gennew=N");
                $("#contentDiv").load(rplink, {}, com.mudrick.onPeopleTableLoad);
                return false;
            });

            // Format text phân trang
            $("#contentDiv .pagelinks strong").each(function () {
                $(this).text("Trang " + $(this).html());
            });
        }
    };
</script>

<% session.setAttribute("startTreeGLKHTDRecursive", null);%>  

<div style="padding-left: 10px;">
    <div>    
        <p style="text-decoration: underline; padding-left: 5px; font-size: 12pt; font-weight: bold;"> 
            Chức năng xuất Text/Excel
        </p>                
    </div>

    <div style="float: left; height:500px; width: 20%; overflow:scroll;" id="balancemainviewdiv">
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

    <div style="float: right; height:500px; width: 80%;">          
        <s:form id="export2sbvform" theme="simple">
            <div>           
                <table style="border:solid 3px #cccccc; width: 75%; padding: 5px 5px 10px 5px;" cellspacing="5px">
                    <tr>
                        <td>Báo cáo: </td>
                        <td>
                            <s:url var="buildComboUrl1" action="buildReportGroupCombo"/>
                            <sj:select href="%{buildComboUrl1}" 
                                       name="selectedReport"
                                       id="selectedReport"
                                       list="lstRptGroupObj"    
                                       onChangeTopics="reloadModuleList"
                                       listKey="sKey"
                                       listValue="sDesc"
                                       emptyOption="false" 
                                       headerKey="NULL"
                                       headerValue="--- Chọn báo cáo ---" 
                                       />   
                        </td>
                    </tr>
                    <tr>
                        <td>Ngày báo cáo: </td>
                        <td>                        
                            <sj:datepicker name="reportDate" value="%{new java.util.Date()}" onblur="validatedate(this.value)"
                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"
                                           id="selectedrptDate" size="15"/>
                        </td>                    
                    </tr>
                    <tr id="title_1">
                        <td>Kỳ báo cáo: </td>
                        <td>                        
                            <sj:select href="%{buildComboUrl1}" 
                                       name="reportPeriod"
                                       id="reportPeriod"
                                       list="lstRptPeriod"      
                                       reloadTopics="reloadModuleList"
                                       listKey="sKey"
                                       listValue="sDesc"
                                       emptyOption="false" 
                                       headerKey="NULL"
                                       />
                        </td>                    
                    </tr>
                    <tr id="title_2">
                        <td>Tên file:</td>
                        <td>                        
                            <s:url var="buildComboUrl3" action="buildPara"/>
                            <sj:select href="%{buildComboUrl3}" 
                                       name="txtGetData"
                                       id="txtGetData"
                                       list="lstPara"
                                       reloadTopics="reloadFile"
                                       listKey="sKey"
                                       listValue="sDesc"
                                       emptyOption="false"
                                       headerKey="NULL"
                                       headerValue="--- Chọn file báo cáo ---" 
                                       />
                        </td>
                    </tr>
                    <tr id="title_3">
                        <td>Hình thức báo cáo:</td>
                        <td>  
                            <select name="sbvSendIndiGroup" id="sbvSendIndiGroup">                                                    
                                <option value="01">GLD - Gửi lần đầu</option>                                                    
                                <option value="02">GLA - Gửi lại</option>
                                <option value="03">GBS - Gửi bổ sung</option>
                            </select> </td>  
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <a href="#" onclick="openSelectWindow();" style="display:none;" id="js_select_id">
                                <u><b>&gt;&gt;Gửi lại PN</b></u>
                            </a>        
                            <p id="to2sbvchkid" style="display:none;">
                                <s:checkbox name="send2sbv" fieldValue="true" /> <b>Gửi NHNN</b>
                                <s:checkbox name="send9acc" fieldValue="true" /> <b>Gửi ngoại bảng</b>
                            </p>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><hr/></td>
                    </tr>                    
                    <tr>
                        <td></td>
                        <td align="left">
                            <div class="clicklink">
                                <s:url var="exportUrl" action="exportText2Sbv.action"/>                                                                                                                
                                <sj:a id="export2Sbv" href="%{exportUrl}" targets="contentDiv"
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
                    <img id="loadingImage" src='img/loading.gif' border='0' alt="loading">
                </div>
                <div id="contentDiv" style="width: 75%;"></div>                       
            </div>        
        </s:form>
    </div>    
</div>