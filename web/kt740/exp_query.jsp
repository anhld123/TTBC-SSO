<%-- 
    Document   : exp_query
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
        <sj:head/>
        <script src="js/JavaScriptUtil.js"></script>
        <script src="js/InputMask.js"></script>
        <script src="js/Parsers.js"></script>
        <script src="js/Checkdate.js"></script>
        <script>
            //CuongBM: 21Jul14
            //Desc: khi thay doi selectbox thi goi den su kien click
            $(function () {
                //Khi thay doi
                $('#save').change(function () {
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
            <s:form id="createquery" action="LoadParameterskt740" theme="simple">
                <table>
                    <tr>
                        <td width="150">Chọn nhóm báo cáo: </td>
                        <td width="700">                    
                            <%--<s:url id="remoteurl" action="loadselectexport"></s:url>--%>
                            <s:url id="remoteurl" action="loadselectexportkt740"></s:url>
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
                                <!--<img id="loading_groupid" src="img/loaderB32.gif" style="display:none"/>-->
                            </td>
                        </tr>
                        <tr>
                            <td width="150">Chọn báo cáo: </td>
                            <td width="700">                    
                          
                            <sj:select  href="%{remoteurl}" 
                                        id="save"
                                        reloadTopics="reloadGroupList" 
                                        formIds="createquery" 
                                        name="save_id"
                                        list="lstObjQuery" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        emptyOption="true" 
                                        headerKey="1"
                                        headerValue="---Chọn mẫu báo cáo---"
                                        onBeforeTopics="myBeforeTopics"
                                        onCompleteTopics="myCompleteTopics" ></sj:select>
                                <img id="loadingImage_next" src="img/loaderB32.gif" style="display:none"/>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                            <sj:submit id="idSubmit" value="Tiếp theo" targets="divParams" indicator="loadingImage_next" onCompleteTopics="after-next" cssStyle="display: none;"/>
                        </td>                    
                    </tr>
                </table> 
            </s:form>  
        </div>
        <hr/>

        <div id="divParams"></div>
    </body>
</html>
