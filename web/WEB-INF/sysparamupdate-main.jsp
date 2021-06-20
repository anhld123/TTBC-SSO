<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:head/>
<sj:head/>
<style>    
    .param_view_link {        
        width: 400px;
        /*        background-color: #b0e0e6;          */
/*        text-align: center;            */
    }

</style>
<script>
    $.subscribe('before-next',
            function(event, data) {
                $("#contentDiv").empty();
                $("#contentDiv").hide();
                $("#loadingImageDiv").show();
            });
    $.subscribe('after-next',
            function(event, data) {
                $("#loadingImageDiv").hide();
                $("#contentDiv").show();
            });
</script>
<div id="sysParamUpdate_div" style="padding-left: 3px;">
    <p style="text-decoration: underline;font-size: 12pt;font-weight: bold;"> 
        Cập nhật danh mục LOV
    </p>
    <div class="param_view_link">
        <s:url id="queryUrl" action="listSysParam.action"/>    
        <sj:a id="userQuerySubmit"  href="%{queryUrl}"
              button="false" targets="contentDiv" theme="simple"
              onBeforeTopics="before-next"
              onCompleteTopics="after-next">
            <b><u> &gt; CSDL Corebank </u> </b>
                </sj:a>        
            &nbsp;&nbsp;&nbsp;&nbsp;
            <script>
            function openSelectWindow() {
                var ht1 = screen.availHeight - 100;
                var wt1 = 600;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 10;                
                window.open('MSSQLImportDataPopupLink.action?menuId=' , 'IMS_REPORTS',
                        "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1
                        + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }
        </script>
        <a href="#" onclick="javascript:openSelectWindow();"
           id="js_select_id">
            <u><b>&gt; CSDL MSSQL</b></u>
        </a>
        </div>
    <hr/>     
    <div id="loadingImageDiv" style="display: none;">
        <img id="loadingImage" src='img/loading.gif' border='0' >
    </div>
    <div id="contentDiv"/>     
</div>
<script>
//    $(function() {
//        //Khi thay doi                
//        $("#userQuerySubmit").trigger("click");
//    });
</script>
