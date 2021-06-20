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
    <style>
        th{
            background-color: #DCDCDC;
            border-color: #999;
        }
        td{
            border-color: #999;
        }
        table.editDelete{
            border-collapse: collapse;
            width: 60%;
            border-color: #999;
        }
        table.editDelete tr:hover{
            background-color:#FFE47A;
            cursor: pointer;
        }
    </style>
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
            });
            $.subscribe('onClickDel', function () {
                var r = confirm("Bạn có thật sự muốn xóa báo cáo này không ? OK : Đồng ý, Cancel : Hủy bỏ");
                if (r == true) {
                    return true;
                }
                else
                    return false;
            });
        </script>

    </head>
    <body>

        <div id="report_group_form" align="center">
            <p>
                <span style="color:blue; font-weight: bolder; font-size: larger">Sửa xóa báo cáo</span>
            </p>
            <s:form id="idEditTemplate" action="LoadEditTemplate" theme="simple">
                <table border="1" class="editDelete">
                    <tr align="center">
                        <th>STT</th>
                        <th>Mô tả mẫu</th>
                        <th>Sửa file template excel</th>
                        <th>Sửa tham số/Truy vấn</th>
                        <th>Xóa</th>
                    </tr>
                    <s:iterator value="lstObjRpt">
                        <tr>
                            <td style="text-align: center;">
                                <s:property value="sStt"></s:property>
                                </td>
                                <td>
                                <s:property value="sDesc"></s:property>
                                </td>
                                <td style="text-align: center;">
                                <s:url id="Edit" value="editTemplateHeader.action"  escapeAmp="false">
                                    <s:param name="fileTemplate" value="sKey"/>
                                    <s:param name="module" value="module"/>
                                </s:url>
                                <sj:a targets="containBcttv"  href="%{Edit}">Sửa Template excel</sj:a>
                                </td>
                                <td style="text-align: center;">
                                <s:url id="Edit" value="LoadEditTemplate.action"  escapeAmp="false">
                                    <s:param name="fileTemplate" value="sKey"/>
                                    <%--<s:param name="module" value="module"/>--%>
                                    <s:param name="module"><s:property value="module"></s:property></s:param>
                                </s:url>
                                <sj:a targets="containBcttv"  href="%{Edit}">Sửa Parameter/Query</sj:a>
                                </td>
                                <td style="text-align: center;">
                                <s:url id="Delete" value="deleteExcelTemplate.action">
                                    <s:param name="fileTemplate" value="sKey"/>
                                </s:url>
                                <sj:a targets="containBcttv"  href="%{Delete}" onClickTopics="onClickDel">Xóa</sj:a>
                                </td>
                            </tr>
                    </s:iterator>

                </table> 
            </s:form>  
        </div>
        <!--<hr/>-->
        <div id="loadingImageDiv_rpt" style="display: none;">
            <img id="loadingImage_rpt" src='img/loading.gif' border='0'>
        </div>
        <div id="divParams"></div>
    </body>
</html>
