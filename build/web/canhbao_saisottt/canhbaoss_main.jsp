<%-- 
    Document   : input_main
    Created on : Oct 26, 2015, 1:45:53 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%--<sj:head jqueryui="true" loadAtOnce="true" jquerytheme="south-street" />--%>
        <sj:head jqueryui="true" jquerytheme="smoothness"/> 
        <script src="js/JavaScriptUtil.js"></script>
        <script src="js/InputMask.js"></script>
        <script src="js/Parsers.js"></script>
        <script src="js/Checkdate.js"></script>
        <script src="js/sweetalert.min.js"></script>
        <script>
            $.subscribe("beforediv_para", function (event, data) {
                $("#loadingImageDiv_para").show();
            });
            $.subscribe("completediv_para", function (event, data) {
                $("#loadingImageDiv_para").hide();
            });

            function onReloadPara()
            {
                $('#containBcttv').empty();
                $("#loadParameter")[0].click();
            }
            $.subscribe('beforediv1', function (event, data) {
                var allDate = $(".hasDatepicker").map(function () {
                    return $(this).attr("name");
                }).get();

                //2. Them input mask
                for (var i = 0; i < allDate.length; i++) {
                    new DateMask("dd/MM/yyyy", allDate[i].toString());
                }
            });
        </script>

        <link  rel="stylesheet" type="text/css" href="chamdiem_tapthe/css/cdtt.css"/>
    </head>

    <body>
        <div id="menuBcttv" align="center">
            <s:form id="loadAllcanhbaoss" action="LoadParaCanhbaoss" theme="simple">
                <s:url id="reloadData" action="loadAllCanhbaoss" includeParams="post"></s:url>
                    <table >
                        <tr>
                            <td>
                            <s:label value="Chọn báo cáo:" cssStyle="color: #029c44;" />
                        </td>
                        <td>
                            <sj:select href="%{reloadData}" 
                                       onChangeTopics="reloadTotruong"    
                                       onchange="onReloadPara()"
                                       id="khoa_cbss" 
                                       name="khoa_cbss"
                                       list="lstAllCbss" 
                                       listKey="sKey"
                                       listValue="sDesc"           
                                       headerKey="-1"
                                       headerValue="-- Chọn mẫu biểu cảnh báo sai sót thông tin --" 
                                       cssStyle="font-weight: bold;vertical-align: middle;width: 500px;margin: 5px 0;"
                                       onBeforeTopics="myBeforeHandler" 
                                       onCompleteTopics="myCompleteTopics">                    
                            </sj:select>
                        </td>
                        <td>
                            <div id="loadingImageDiv_para"  style="display: none;">
                                <img id="loadingImage" src='img/loading.gif' border='0' >
                            </div>
                        </td>
                    </tr>
                </table>
                <!--<h2>Xin chọn file dữ liệu excel cần import dữ liệu vào</h2></div>-->
                <sj:submit id="loadParameter" name="loadParameter" value="Tải dữ liệu" targets="containBcttv" onBeforeTopics="beforediv_para"
                           onCompleteTopics="completediv_para" cssStyle="display: none"/>
            </s:form>
        </div>
        <div id="containBcttv"></div>
    </body>
</html>

