<%-- 
    Document   : main-input-branch
    Created on : Nov 20, 2018, 1:49:03 PM
    Author     : BAOANH
--%>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>
        <sj:head jqueryui="true" jquerytheme="smoothness"/>
        <script src="js/sweetalert.min.js"></script>
        <!--<script type="text/javascript" src="js/jquery-1.10.0.min.js"></script>-->
        <script type="text/javascript" src="input-branch/js/inputbranch.js"></script>
        <!--input-branch/-->
        <link  rel="stylesheet" type="text/css" href="input-branch/css/inputbranch.css"/>

    </head>
    <style>
    </style>

    <script>
        //Desc: Lan dau tien load trang, goi den su kien click load all bc
        $(document).ready(function () {
            //alert('Load form');
            //onReloadChonmau();
            //$('#chonmaushow').show();
            //cleardiv();
        });
         $.subscribe("beforediv", function (event, data) {
            $("#loadingImageDiv").show();
        });
        $.subscribe("completediv", function (event, data) {
            $("#loadingImageDiv").hide();
        });
    </script>
    <body >
        <div id="menuinputbranch"  align="center">
            <s:form id="formid_themchitieu" theme="simple" action="LoadDanhmucchitieu.action">
                <s:url id="LoadAddNewform" action="LoadAddNewForm.action?loaimau_daluu=NEW" />
                <%--<s:url id="Themchitieumoi" action="Themmoichitieu.action" includeParams="post"/>--%>
                <s:url id="layMauNhaplieuId" action="LoadAllMauNhaplieu.action"/>
                <s:url id="del_edit_maubc" action="LoadGroupRpt.action" />

                <table border="0" cellspacing="0px" cellpadding="0px" style="width: 98%;margin: 3px 0px;">
                    <tr>
                        <td style="width: 10%">
                            <div id="loadingImageDiv" style="margin: 0px 0px;display: none">  
                                <img id="loadingImage" src='img/loading.gif' border='0' >
                            </div>
                        </td>
                        <td style="width: 55%">
                            <div id="chonmaushow" style="display: none">
                                <table>
                                    <tr>
                                        <td style="width: 50%">
                                            <s:label value="Nhóm BC: "/>
                                            <sj:select  href="%{layMauNhaplieuId}"
                                                        id="grouprpt_id" 
                                                        name="grouprpt_id"
                                                        list="lstGroupRpt" 
                                                        listKey="sKey"
                                                        listValue="sDesc"           
                                                        headerKey="-1"
                                                        headerValue="-- Chọn --" 
                                                        cssStyle="font-weight: bold;vertical-align: middle;width: 200px;"
                                                        onBeforeTopics="beforediv" 
                                                        onCompleteTopics="completediv"
                                                        onChangeTopics="reloadGroupList">                    
                                            </sj:select>

                                        </td>
                                        <td style="width: 50%">
                                            <s:label value="Mẫu BC: "/>
                                            <sj:select  href="%{layMauNhaplieuId}"
                                                        formIds="formid_themchitieu"
                                                        id="themmau_id" 
                                                        name="khoa"
                                                        list="lstAllMau" 
                                                        listKey="sKey"
                                                        listValue="sDesc"           
                                                        headerKey="-1"
                                                        headerValue="-- Chọn --" 
                                                        cssStyle="font-weight: bold;vertical-align: middle;width: 250px;"
                                                        onBeforeTopics="beforediv" 
                                                        onCompleteTopics="completediv"
                                                        reloadTopics="reloadGroupList" 
                                                        onchange=" $('#containBcttv').empty();$('#loadChitieu_ID')[0].click();">                    
                                            </sj:select>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </td>
                        <td style="width: 10%">
                            <sj:submit id="addMaumoi" targets="containBcttv"  href="%{LoadAddNewform}"  value="Thêm mẫu mới" 
                                       cssClass="metroButtonStyle" onclick="cleardiv();$('#chonmaushow').hide();" onBeforeTopics="beforediv" onCompleteTopics="completediv"></sj:submit>
                            </td>
                            <td style="width: 10%">
                                <input type="button" id="id_showAllMau" name="nameReloaddata" 
                                       onclick="cleardiv();$('#chonmaushow').show();" value="Thêm chỉ tiêu" class="metroButtonStyle"/>
                                
<!--                                <input type="button" id="id_showAllMau" name="nameReloaddata" 
                                       onclick="onReloadChonmau();cleardiv(); $('#chonmaushow').show();" value="Thêm chỉ tiêu" class="metroButtonStyle"/>-->
                            </td>
                            <td style="width: 10%">

                            <sj:submit id="idEditDelQuery" targets="containBcttv"  href="%{del_edit_maubc}" indicator="loadingImage" value="Sửa/Xóa mẫu" cssClass="metroButtonStyle" 
                                       onclick="cleardiv();$('#chonmaushow').hide();" onBeforeTopics="beforediv" onCompleteTopics="completediv"></sj:submit>

                            <%--<sj:submit id="loadFormName" targets="containBcttv"  href="%{LoadAddNewform}" cssStyle="display: none;" value="Load BC"></sj:submit>--%>
                        </td>
                    </tr>
                </table>

                <sj:submit id="loadChitieu_ID" name="loadParameter" value="Tải dữ liệu" targets="containBcttv" onBeforeTopics="beforediv"
                           onCompleteTopics="completediv" cssStyle="display: none"/>
            </s:form>
        </div>
        <div id="containBcttv" >

        </div>

    </body>
</html>
