<%-- 
    Document   : exp_excel
    Created on : Jul 14, 2014, 4:17:12 PM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <sj:head jqueryui="true" jquerytheme="smoothness"/> 
        <script src="js/JavaScriptUtil.js"></script>
        <script src="js/InputMask.js"></script>
        <script src="js/Parsers.js"></script>
        <script src="js/Checkdate.js"></script>
        <script>
            $.subscribe("myBeforeHandler", function (event, data) {
                $("#loadingImageDiv_rpt").show();
            });
            $.subscribe("myBeforeTopics", function (event, data) {
                $("#loadingImageDiv_rpt").show();
                $("#messageDiv").empty();
            });

            $.subscribe("myCompleteTopics", function (event, data) {
                $("#loadingImageDiv_rpt").hide();
            });
            //Desc: khi thay doi selectbox thi goi den su kien click
            $(function () {
                $('#group_id').change(function () {
                    //                    alert('da chon group khac ');
                    $("#divParams").empty();
                    $.subscribe("truockhiload", function (event, data) {
                        //                        alert('truoc khi load ');
                        $("#loading_groupid").show();
                    });

                    $.subscribe("hoanthanh", function (event, data) {
                        //                        alert('sau khi load ');
                        $("#loading_groupid").hide();
                    });
                });

                //Khi thay doi
                $('#save').change(function () {
                    $("#divParams").empty();
                    $("#idSubmit").trigger("click");
                });
            });
            $.subscribe('after-next', function (event, data) {
                //CuongBM: 17Jul14
                //Desc: Xu ly truong date
                //      1. Lay danh sach datetime picker
                //      2. Them input mask cho cac datetime pikcer nay

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

    </head>
    <body>
        <h4>Xuất báo cáo</h4>
        <hr/>    
        <div id="report_group_form" class="report_group_form">
            <s:form id="createexcel" action="LoadParameterExp" theme="simple">
                <table>
<!--                    <tr>
                        <td width="150">Chọn nhóm báo cáo: </td>
                        <td width="700">                    
                           
                            <sj:select  
                                href="%{remoteurl}" 
                                id="group_id"
                                name="group_id"
                                list="lstObjGroup" 
                                onChangeTopics="reloadGroupList"
                                listKey="sKey"
                                listValue="sDesc"
                                emptyOption="true" 
                                headerKey="-1"
                                headerValue="---Chọn nhóm báo cáo---"
                                onBeforeTopics="myBeforeHandler" 
                                onCompleteTopics="myCompleteTopics1"></sj:select>
                                <img id="loading_groupid" src="img/loaderB32.gif" style="display:none"/>
                            </td>
                        </tr>-->
                        
                        <tr>
                             <s:url id="remoteurl" action="loadallrpt_exp.action?module=SBV&group_id=06"></s:url>
                            <td width="150">Chọn báo cáo: </td>
                            <td width="700">        
                            <%--<s:url id="remoteurl" action="loadallrpt_exp"></s:url>--%>
                            <sj:select  href="%{remoteurl}" 
                                        id="save"
                                        formIds="createexcel" 
                                        reloadTopics="reloadGroupList" 
                                        name="fileTemplate"
                                        list="lstObjRpt" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        emptyOption="true" 
                                        headerKey="1"
                                        headerValue="---Chọn mẫu báo cáo---"
                                        onBeforeTopics="myBeforeTopics"
                                        onCompleteTopics="myCompleteTopics" ></sj:select>
                                <!--<img id="loadingImage_next" src="img/loaderB32.gif" style="display:none"/>-->
                                <img id="loadingImage-next" src="img/loaderB32.gif" style="display:none"/>
                                <div id="loadingImageDiv_rpt" style="display: none;">
                                    <img id="loadingImage_rpt" src='img/loading.gif' border='0'>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                            <sj:submit id="idSubmit" value="Tiếp theo" targets="divParams" indicator="loadingImage-next" onCompleteTopics="after-next" cssStyle="display: none;"/>
                        </td>                    
                    </tr>
                </table> 
            </s:form>  
        </div>
        <hr/>

        <div id="divParams"></div>
    </body>
</html>
