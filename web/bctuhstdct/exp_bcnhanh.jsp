<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<sj:head/> 

<!--Phải để đầu tiên thì sj mới chạy ok-->
<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script type="text/javascript">
        $.subscribe("myBeforeHandler", function(event, data) {
            $("#loadingImageDiv").show();
        });
        $.subscribe("myBeforeTopics", function(event, data) {
            $("#loadingImageDiv").show();
            $("#messageDiv").empty();
        });

        $.subscribe("myCompleteTopics", function(event, data) {
            $("#loadingImageDiv").hide();
        });
</script>
<script src="js/Checkdate.js"></script>

    <script>
        //CuongBM: 15-May-14
        //Desc: khi thay doi selectbox thi goi den su kien click
        $(function() {
            //Khi thay doi
            $('#save').change(function() {
                $("#loadParameter").trigger("click");
            });
        });
        
        $.subscribe('before-next', function(event,data) {
                $("#viewparameter").empty();
                $("#viewparameter").hide();
            });
            
        $.subscribe('after-next', function(event,data) {
            $("#viewparameter").slideDown("slow");

            //CuongBM: 17Jul14
            //Desc: Xu ly truong date
            //      1. Lay danh sach datetime picker
            //      2. Them input mask cho cac datetime pikcer nay

            //1. Lay danh sach cac truong datetimepicker
            var allDate = $(".hasDatepicker").map(function() {
                return $(this).attr("name");
            }).get();        

            //2. Them input mask
            for (var i = 0; i < allDate.length; i++) {
               new DateMask("dd/MM/yyyy", allDate[i].toString());
            }
            });
    </script>    
<h3>Chọn module và mẫu tạo báo cáo nhanh</h3>
<hr/>
<div class="report_group_form">    
    <s:form id="reloadform" theme="simple" action="loadParaExportRptFast">       
        <table>
            <tr>
                <td width="150">Chọn module báo cáo:</td>
                <td width="700">
                <s:url id="remoteurl" action="loadExportRpt"></s:url>
                <sj:select href="%{remoteurl}" 
                           id="module" 
                           name="module_id"
                           list="lstModuleObj" 
                           onChangeTopics="reloadModuleList"
                           listKey="sKey"
                           listValue="sDesc"
                           emptyOption="true" 
                           headerKey="-1"
                           headerValue="---Chọn Module tạo số liệu---" 
                           onBeforeTopics="myBeforeHandler" 
                           onCompleteTopics="myCompleteTopics1"></sj:select>
                </td>
            </tr>

            <tr>
                <td width="150">Chọn mẫu báo cáo:</td>
                <td width="700">
                <%--<s:url id="remoteurl" action="loadExportRpt"></s:url>--%>
                <sj:select     href="%{remoteurl}" 
                               id="save"
                               formIds="reloadform" 
                               reloadTopics="reloadModuleList" 
                               name="save_id"
                               list="lstSaveReportObj" 
                               listKey="sKey"
                               listValue="sDesc"
                               emptyOption="true" 
                               headerKey="1"
                               headerValue="---Chọn mẫu báo cáo---" 
                               onBeforeTopics="myBeforeTopics"
                               onCompleteTopics="myCompleteTopics" 
                               ></sj:select>
                
                <img id="loadingImage-next" src="img/loaderB32.gif" style="display:none"/>
                <div id="loadingImageDiv" style="display: none;">
                    <img id="loadingImage" src='img/loading.gif' border='0'>
                </div>
                </td>
            </tr>                
        </table>
    </s:form>
</div>

<hr/>
<div align="right" >
    <sj:a id="loadParameter" formIds="reloadform" targets="viewparameter" indicator="loadingImage-next" href="#" onBeforeTopics="before-next" onCompleteTopics="after-next"></sj:a>
</div>
<sj:div id="viewparameter"></sj:div>
