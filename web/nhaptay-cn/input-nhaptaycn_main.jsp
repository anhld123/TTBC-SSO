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
        <!--<script src="js/google-chart.js"></script>-->  
        <script>
            $.subscribe("beforediv_para", function(event, data) {
                $("#loadingImageDiv_para").show();
            });
            $.subscribe("completediv_para", function(event, data) {
                $("#loadingImageDiv_para").hide();
            });

            function onReloadPara()
            {
                $('#containBcttv').empty();                
                $("#loadParameter")[0].click();
            }
            $.subscribe('beforediv1', function(event, data) {
                var allDate = $(".hasDatepicker").map(function() {
                    return $(this).attr("name");
                }).get();

                //2. Them input mask
                for (var i = 0; i < allDate.length; i++) {
                    new DateMask("dd/MM/yyyy", allDate[i].toString());
                }
            });
        </script>
        <style>
            body {
                background-image: url('img/backgroud_logo.jpg');
                background-size: 40% auto;
                background-repeat: no-repeat;
                background-position: center center;
                background-attachment: fixed;
                background-blend-mode: multiply;
                background-position: center 120px;
            }
            #menuBcttv{
                width: 100%;
                height: 30px;                
                border: 1px solid; 
                /*padding-top: 10px;*/
                padding-bottom: 0px;
                background: #FFCCBA;
            }

            /*        #containBcttv{
                        width: 100%;
                        min-height:390px;
                        border: 1px solid;
                        margin-top: 2px;
                        background: gold;
                    }*/

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


        </style>
    </head>

    <body>
        <div id="menuBcttv" align="center">
            <s:form id="loadAllnhaptaycn" action="LoadParaNhaptaycn" theme="simple">
                <s:url id="reloadData" action="loadAllNhaptaycn" includeParams="post"></s:url>
                    <table >
                        <tr>
                            <td>
                            <s:label value="Chọn báo cáo:" cssStyle="color: #029c44;" />
                        </td>
                        <td>
                            <sj:select href="%{reloadData}" 
                                       onChangeTopics="reloadTotruong"    
                                       onchange="onReloadPara()"
                                       id="khoa_nhaptaycn" 
                                       name="khoa_nhaptaycn"
                                       list="lstAllNhaptaycn" 
                                       listKey="sKey"
                                       listValue="sDesc"           
                                       headerKey="-1"
                                       headerValue="-- Chọn --" 
                                       cssStyle="font-weight: bold;vertical-align: middle;width: 500px;"
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
        <div id="containBcttv">
        </div>
    </body>
</html>

