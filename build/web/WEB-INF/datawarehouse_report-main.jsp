<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<sj:head jqueryui="false" jquerytheme="redmond"/>
<s:head/>

<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/Checkdate.js"></script>

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

<script>
    $(function () {
        new DateMask("dd/MM/yyyy", "reportDate");
    });

    function clk_glkhtd() {
        var lstPos = "";
        var eco_area_type = $("#eco_area_type").val();
        
        if (eco_area_type === '1')
            $('#treeView').jstree("get_checked", null, true).each(
                function () {
                    lstPos = lstPos + this.id + ',';
                });
        else 
            $('#treeView2').jstree("get_checked", null, true).each(
                function () {
                    lstPos = lstPos + this.id + ',';
                });
        //document.getElementById("selectedPos").value = lstPos;
        $("#selectedPos").val(lstPos);
    }
</script>

<!DOCTYPE html>
<html>

    <body>
        <div id="maindiv">
            <s:form theme="simple" id="bctlsl_reportForm_ID">
                <div  style="
                      float: top;
                      height:10%;
                      z-index:1;
                      background: #F7F7F7;">
                    <table width="100%" border="0" cellspacing="5">
                        <tr>
                            <td style="width: 30%">
                                Ngày báo cáo: 
                                <sj:datepicker name="reportDate" value=""  
                                               onblur="validatedate(this.value)"
                                               placeholder="DD/MM/YYYY" 
                                               changeYear="true" 
                                               changeMonth="true" 
                                               displayFormat="dd/mm/yy"
                                               id="selectedrptDate" size="15"/>                                
                                <script>
                                    var lj_curDate = new Date();
                                    var lj_setDate = lj_curDate.getDate() + "/" +
                                            (lj_curDate.getMonth() + 1) + "/" + lj_curDate.getFullYear();
                                    document.getElementById("selectedrptDate").value = lj_setDate;
                                </script>
                            </td>            
                            <td rowspan="2" align="right" valign="middle">
                                <table style="width: 100%;" cellspacing="5">
                                    
                                    <tr>                                           
                                        <td>      
                                                                                              
                                                <div style="float:left; ">
                                                    Đến ngày: <s:textfield id="todate_txt_ID" 
                                                             name="todate" 
                                                             placeholder="Đến ngày..." size="28"/>                                                                                                                                           
                                                &nbsp;&nbsp;&nbsp;
                                                Nhóm báo cáo: &nbsp;&nbsp;&nbsp;
                                                <s:url var="buildComboUrl_0" 
                                                       action="bctlsl_build_group_combo"></s:url>
                                                <sj:select href="%{buildComboUrl_0}" 
                                                           name="group"
                                                           id="selectedGroup_ID"
                                                           list="groups" 
                                                           listKey="sKey"
                                                           listValue="sDesc"
                                                           emptyOption="false"                                                          
                                                           theme="simple"     
                                                           ></sj:select>  
                                                <s:url id="loadReport_ID" action="bctlsl_list_report.action">   
                                                    <s:hidden id="call_trigger_ID" value=""/>
                                                </s:url>                                                           
                                                <sj:a id="loadReportUrl_ID"  
                                                      href="%{loadReport_ID}" 
                                                      targets="contentDiv"
                                                      formIds="bctlsl_reportForm_ID"                                                           
                                                      button="false"                                                           
                                                      onBeforeTopics="before-next"
                                                      onCompleteTopics="after-next"
                                                      theme="simple"></sj:a>
                                                    &nbsp;&nbsp;&nbsp;
                                                    Định dạng file: 
                                                    <input type="radio" name="export_format" value="PDF" checked>PDF  
                                                    <input type="radio" name="export_format" value="EXCEL">Excel


                                                </div>
                                                <div style="float:right; padding-right: 20px;">
                                                <s:url id="sumaryUrl_ID" action="bctlsl_generate_report.action">
                                                </s:url>
                                                <sj:a 
                                                    id="sumary_ID"
                                                    href="%{sumaryUrl_ID}"                                                       
                                                    targets="downloadlink_DIV"
                                                    formIds="bctlsl_reportForm_ID"                                                     
                                                    button="false"                                               
                                                    theme="simple"
                                                    disabled="true"></sj:a>    
                                                    <script>
                                                        $('#sumary_ID').attr("disabled", 'disabled');
                    //                                                        $('#sumary_ID').prop("disabled", true);
                                                    </script>
                                                <s:url id="printUrl_ID" action="bctlsl_generate_report.action">                                                              
                                                </s:url>
                                                &nbsp;&nbsp;                                                
                                                <sj:a id="print_ID"  
                                                      href="%{printUrl_ID}" 
                                                      onclick="clk_glkhtd()"             
                                                      onBeforeTopics="before-next-0"
                                                      onCompleteTopics="after-next-0"
                                                      targets="downloadlink_DIV"
                                                      formIds="bctlsl_reportForm_ID"                                                       
                                                      button="false"                                                      
                                                      theme="simple"> <b><u>In báo cáo</u></b></sj:a>
                                                </div> 
                                            </td>
                                        </tr>
                                        <tr>
                                        <td colspan="2" align="left">   
                                            Hình thức in: 
                                    &nbsp;&nbsp;&nbsp;                                                    
                                    <input type="radio" name="report_type" value="1" checked>
                                    <font color="blue"> Tổng hợp  </font>
                                    <input type="radio" name="report_type" value="2">Chi tiết
                                        </td>                                            
                                    </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td style="width: 30%">
                                    Kỳ báo cáo: &nbsp;&nbsp;&nbsp;
                                <s:url var="buildComboUrl" 
                                       action="bctlsl_build_period_combo"></s:url>
                                <sj:select href="%{buildComboUrl}" 
                                           name="selectedPeriod"
                                           id="period_id"
                                           list="periods" 
                                           listKey="sKey"
                                           listValue="sDesc"
                                           emptyOption="false"  
                                           headerKey="NULL"
                                           headerValue="--- Chọn ---"
                                           theme="simple"     
                                           ></sj:select>                        
                                </td>                        
                            </tr>
                            <tr>
                                <td style="width: 30%">
                                    Vùng kinh tế theo: 
                                    &nbsp;&nbsp;&nbsp;                                                    
                                    <input type="radio" name="eco_area" id="r1"  value="1" checked>
                                    <font color="blue"> TT35  </font>
                                    <input type="radio" name="eco_area" id="r2" value="2">VB96
                                </td>
                            </tr>
                        </table>
                    </div>
                    <hr/>
                    <div  style="
                          float: left;
                          height:600px;
                          width: 30%;
                          z-index:1;
                          overflow:scroll;
                          background:  #ffffff;"
                          id="treeview_div_tt35">
                    <s:url var="echoO" action="buildPosTreeView"/>
                    <sjt:tree  
                        id="treeView"
                        jstreetheme="apple"
                        rootNode="nodes"
                        nodeIdProperty="id"
                        nodeTitleProperty="name"
                        href="%{echoO}"
                        childCollectionProperty="children"
                        checkbox="true"
                        />        
                </div>  
                <div  style="
                          float: left;
                          height:600px;
                          width: 30%;
                          z-index:1;
                          overflow:scroll;
                                                 
                          background: #ffffff;
                          "
                          id="treeview_div_vb96"                          
                          >
                    <s:url var="echo1" action="buildPosTreeView_VB96"/>
                    <sjt:tree  
                        id="treeView2"
                        jstreetheme="apple"
                        rootNode="nodes"
                        nodeIdProperty="id"
                        nodeTitleProperty="name"
                        href="%{echo1}"
                        childCollectionProperty="children"
                        checkbox="true"
                        />        
                </div>
                <s:hidden name="eco_area_type" id="eco_area_type" value="1"/>
                <s:hidden name="selectedPos" id="selectedPos" value=""/>                
                <div  style="float: right;height:600px; width: 70%; " >          
                    <div id="loadingImageDiv" style="display: none;">
                        <img id="loadingImage" src='img/loading.gif' border='0' >
                    </div>
                    <div id="contentDiv"></div>

                    <div id="loadingImageDiv_0" style="display: none;">
                        <img id="loadingImage" src='img/loading.gif' border='0' >
                    </div>
                    <div id="downloadlink_DIV"></div>      


                </div>
            </s:form>           
        </div>

    </body>

</html>

<script>
    var lj_curDate = new Date();
    if (lj_curDate.getMonth() < 12) {
        var last_date = new Date(lj_curDate.getFullYear(),
                lj_curDate.getMonth() + 2, 0);
    } else {
        var last_date = new Date(lj_curDate.getFullYear() + 1,
                1, 0);
    }
    var lj_setDate = "01" + "/" +
            (lj_curDate.getMonth() + 1) + "/" + lj_curDate.getFullYear();

    var lj_setDate_0 = last_date.getDate() + "/" +
            last_date.getMonth() + "/" + last_date.getFullYear();

    document.getElementById("todate_txt_ID").value = lj_setDate_0;
</script>

<script>
    $.subscribe('before-next',
            function (event, data) {
                $("#contentDiv").empty();
                $("#contentDiv").hide();
                $("#loadingImageDiv").show();
            });

    $.subscribe('after-next',
            function (event, data) {
                com.trungnt.onTableLoad();
                $("#contentDiv").show();
                $("#loadingImageDiv").hide();
            });
    $.subscribe('before-next-0',
            function (event, data) {
                $("#downloadlink_DIV").empty();
                $("#downloadlink_DIV").hide();
                $("#loadingImageDiv_0").show();
            });

    $.subscribe('after-next-0',
            function (event, data) {
                $("#downloadlink_DIV").show();
                $("#loadingImageDiv_0").hide();
            });

    if (!com)
        var com = {};
    com.trungnt = {
        onTableLoad: function () {

            $("#contentDiv .pagelinks a").each(function () {
                $(this).click(function () {
                    var link = $(this).attr("href");
                    var rplink = link.replace("gennew=Y", "gennew=N");
                    $("#contentDiv").load(rplink, {},
                            com.trungnt.onTableLoad);
                    return false;
                });
            });
        }
    };
    
    
    

</script>

<script>

    $(function () {
        $("#loadReportUrl_ID").trigger("click");


        $('#period_id').change(function () {

            var lv_date_str = document.getElementById(
                    "selectedrptDate"
                    ).value.toString();
            var lv_dtcomp = lv_date_str.split("/");

            var lv_day = parseInt(lv_dtcomp[0]);
            var lv_month = parseInt(lv_dtcomp[1]);
            var lv_year = parseInt(lv_dtcomp[2]);

            var ls_selectObj = document.getElementById(
                    'period_id');
            var ls_periodCode = ls_selectObj.value;

            switch (ls_periodCode) {
                case '04': /// thang
                    var lastDayOfMonth =
                            new Date(lv_year, lv_month, 0);
                    var lastDayOfMonth_fstr = lastDayOfMonth.getDate()
                            + "/"
                            + (lastDayOfMonth.getMonth() + 1)
                            + "/"
                            + lastDayOfMonth.getFullYear();
                    var todate_TXT = document.getElementById("todate_txt_ID");
                    todate_TXT.value = lastDayOfMonth_fstr;
                    todate_TXT.readOnly = true;
                    todate_TXT.style.backgroundColor = "yellow";
                    break;
                case '05': /// quy       
                    var v_quater = 0;
                    if (lv_month % 3 === 0) {
                        v_quater = lv_month / 3;
                    } else {
                        v_quater = Math.floor(lv_month / 3) + 1;
                    }
                    var beginmonth = v_quater * 3;
                    var lastDayOfQuater =
                            new Date(lv_year, beginmonth, 0);

                    var lastDayOfQuater_fstr = lastDayOfQuater.getDate()
                            + "/"
                            + (lastDayOfQuater.getMonth() + 1)
                            + "/"
                            + lastDayOfQuater.getFullYear();
                    ;
                    var todate_TXT = document.getElementById("todate_txt_ID");
                    todate_TXT.value = lastDayOfQuater_fstr;
                    todate_TXT.readOnly = true;
                    todate_TXT.style.backgroundColor = "yellow";
                    break;
                case '06':
                    var lastDayOfHalfYear =
                            new Date(lv_year, lv_month + 7, 0);
                    var lastDayOfHalfYear_fstr = lastDayOfHalfYear.getDate()
                            + "/"
                            + (lastDayOfHalfYear.getMonth() + 1)
                            + "/"
                            + lastDayOfHalfYear.getFullYear();
                    var todate_TXT = document.getElementById("todate_txt_ID");
                    todate_TXT.value = lastDayOfHalfYear_fstr;
                    todate_TXT.readOnly = true;
                    todate_TXT.style.backgroundColor = "yellow";
                    break;
                case '07':
                    var lastDayOfYear = new Date(lv_year, 12, 0);
                    var lastDayOfYear_fstr = lastDayOfYear.getDate()
                            + "/"
                            + (lastDayOfYear.getMonth() + 1)
                            + "/"
                            + lastDayOfYear.getFullYear();
                    var todate_TXT = document.getElementById("todate_txt_ID");
                    todate_TXT.value = lastDayOfYear_fstr;
                    todate_TXT.readOnly = true;
                    todate_TXT.style.backgroundColor = "yellow";
                    break;
                case '10':

                    var todate_TXT = document.getElementById("todate_txt_ID");
                    todate_TXT.value = '';
                    todate_TXT.readOnly = false;
                    todate_TXT.style.backgroundColor = "white";
                    break;
            }
        });
    });

    

    $(document).ready(function () {
        
        $( "#treeview_div_vb96" ).hide();
        
        $("input[name=eco_area]:radio").change(function () {
            if ($(this).val() === '1') {
                $( "#treeview_div_vb96" ).hide();
                $( "#treeview_div_tt35" ).show();         
                $("#eco_area_type").val("1");
              } else if ($(this).val() === '2') {
                $( "#treeview_div_vb96" ).show();
                $( "#treeview_div_tt35" ).hide();                
                $("#eco_area_type").val("2");
              } 
        });
    });

</script>

