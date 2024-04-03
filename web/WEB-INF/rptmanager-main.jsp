<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:head/>
<sj:head/>

<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/Checkdate.js"></script>

<script>
    //CuongBM: 15-May-14
    //Desc: khi thay doi selectbox thi goi den su kien click
    $(function () {
        //Khi thay doi
        $('#reportId').change(function () {
            $("#loadParameter").trigger("click");
        });
        //Khi load xong
        $('#reportId').ready(function () {
            $("#loadParameter").trigger("click");
        });
    });

    $.subscribe('before-next', function (event, data) {
        $("#divListParams").empty();
        $("#divListParams").hide();
    });

    $.subscribe('after-next', function (event, data) {
        // Effect cho the div
        $("#divListParams").slideDown("slow");

        //CuongBM: 17Jul14
        //Desc: Xu ly truong date
        //      1. Lay danh sach datetime picker
        //      2. Them input mask cho cac datetime picker nay

        //1. Lay danh sach cac truong datetimepicker
        var allDate = $(".hasDatepicker").map(function () {
            return $(this).attr("name");
        }).get();

        //2. Them input mask
        for (var i = 0; i < allDate.length; i++) {
            new DateMask("dd/MM/yyyy", allDate[i].toString());
        }
    });
</script>


<div style="padding-left: 5px;">
    <h4>Tạo báo cáo</h4>
    <hr/>    
    <div id="report_group_form" class="report_group_form">    
        <s:form id="rpt_form" theme="simple" action="loadParams">
            <table>
                <tr>
                    <td width="150">Nhóm báo cáo: </td>
                    <td>                    
                        <s:url var="buildGroupComboUrl" action="doGroupReportFilter.action"></s:url>
                        <sj:select href="%{buildGroupComboUrl}" 
                                   name="groupId"
                                   id="groupId"
                                   list="lstRptGroupObj"    
                                   onChangeTopics="reloadModuleList"
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false"                                                               
                                   theme="simple"  
                                   ></sj:select>
                        </td>        
                    </tr>
                    <tr>
                        <td width="150">Loại báo cáo: </td>
                        <td>
                        <%--<s:url var="buildGroupComboUrl" action="doGroupReportFilter.action"></s:url>--%>
                        <sj:select href="%{buildGroupComboUrl}" 
                                   name="reportId"
                                   id="reportId"
                                   list="lstReportObj"      
                                   reloadTopics="reloadModuleList"
                                   formIds="rpt_form" 
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false"                                    
                                   value="%{reportId}"
                                   theme="simple"
                                   headerKey="-1"
                                   headerValue="--- Chọn báo cáo ---"
                                   style="width: 80%;"
                                   ></sj:select>
                            </select>
                            <img id="loadingImage_next" src="img/loaderB32.gif" style="display:none"/>
                        </td>        
                    </tr>
                    <tr></tr>
                </table>       
        </s:form>            
    </div>
    <hr/>
    <div align="right" >
        <sj:a id="loadParameter" formIds="rpt_form" targets="divListParams" 
              indicator="loadingImage_next" href="#" onBeforeTopics="before-next" 
              onCompleteTopics="after-next"></sj:a>
        </div>
        <br/>
        <!-- CuongBM: 15-Apr-14 -->
        <!-- The div de load cac tham so -->
    <sj:div id="divListParams"></sj:div>
</div>
