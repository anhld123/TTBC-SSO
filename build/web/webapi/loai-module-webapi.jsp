<%-- 
    Document   : input_main
    Created on : Oct 26, 2015, 1:45:53 PM
    Author     : LION
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <sj:head/>
        <style>
            #menuBcttv_para{
                width: 100%;
                height: 25px;                
                border: 1px solid; 
                padding-bottom: 0px;
                padding-top: 0px;
            }

            #containBcttv_para{
                width: 100%;
                min-height:390px;
                border: 1px solid;
                margin-top: 2px;
            }

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

            #container{
                width: 100%;
                height: 500px;
                border: 0px solid;
                padding-left: 0px;        
                /*color: #FFE6B0*/
            }

            #containTree{
                width: 15%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 99%;
                height: 440px;
                padding-left: 5px;
                border: 1px solid;
                /*float: left;*/
                text-align: center;
                position: relative;
                overflow: scroll;
            }
            #containParm_full{
                width: 100%;
                height: 450px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
            }
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: 12px;
            }

            .report_group_form{
                width: 100%;
            }

            #navParam{
                height: 35px;
                padding:0px;
                padding-bottom: 0px;
                padding-top: 5px;
                vertical-align: middle;
                border: 1px solid;                

            }
            #navParam2{
                /*height: 35px;*/
                border: 0px solid;
                /*margin-left: 10px;*/
                font-weight: bold;
                /*border-left: 40px;*/
                /*position: relative;*/
                float: left;
                padding-bottom: 0px;
                padding-top: 0px;
                background: #FBE3E4;

            }
            #message_suc_err
            {
                height: 30px;
                border: 0px solid;
                padding-bottom: 0px;
                padding-top: 0px;
            }


            th{
                background-color: #DCDCDC;
                border-color: #999;
                height: 18px;
            }
            td{
                border-color: #999;
                height: 20px;
            }
            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }
            table.editDelete tr:focus{
                background-color:#FFE47A;
                /*cursor: pointer; hover*/
            }
            .highlight_row {
                background-color: #FFB951; 
                color:#000;
            }
            .DU_NO
            {
                width: 100%;
                border: 0px;
                color: #000000;
                border-color: #18ab29;
                background: #F9F9F9;
                color:#666666;
            }

        </style>
        <script src="js/webapi.js"></script>
        <script>
            $(document).ready(function () {
            <%
                String username = session.getAttribute("username").toString();
                String reportGrade = session.getAttribute("reportGrade").toString();
            %>
//                console.log('username= <%= username%> -> reportGrade=<%=reportGrade%>');
                if (<%=reportGrade%> == '1')
                    {
//                        console.log('dung cap =2');
                        $('#lbldv').hide();
                    }

            });
            function submittruyvan()
            {
                $("#table_data").empty();
                $("#table_data").text('');
                $("#idtruyvan")[0].click();
            }
            function onchange_ab()
            {
                try {
                    
                    $("#table_data").empty();
                    $("#table_data").text('');
                    var value = $("#loaiapi").val();

//                    alert("thay doi gia tri value=" + value);
                    if (value == '-1')
                    {
                        alert('Bạn phải chọn loại cần truy vấn !');
                        return;
                    }
                    if (value == '01')
                        $("#para_api").load("webapi/information-cust.jsp");
                    if (value == '02')
                        $("#para_api").load("webapi/history-loan.jsp");
                    if (value == '03')
                        $("#para_api").load("webapi/information-group.jsp");
                    if (value == '04')
                        $("#para_api").load("webapi/information-cust-group.jsp");
                    if (value == '05')
                        $("#para_api").load("webapi/information-tide.jsp");
                } catch (e) {
                    alert(e.toString());
                }


            }

            $.subscribe('beforediv', function (event, data) {
                $("#table_data").empty();
                $("#table_data").hide();
                $("#loadingImageDiv").show();
            });

            $.subscribe('completediv', function (event, data) {
                $("#loadingImageDiv").hide();
                $("#table_data").show();
            });
            
        
            
        </script>
    </head>

    <body>
        <div id="container" >
            <s:form id="id_api" name="name_api" action="apitruyvan" theme="simple">
                <div id="navParam" >
                    <table border="0">
                        <tr style="width: 100%">
                            <td style="width: 40%">
                                <div id="navParam2">                                     
                                    <s:url id="idloadModuleApi" action="loadModuleWebApi"></s:url>
                                        Loại truy vấn:
                                    <sj:select
                                        href="%{idloadModuleApi}" 
                                        onchange="onchange_ab()"
                                        id="loaiapi"
                                        name="loai_api"
                                        list="moduleList" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        emptyOption="true" 
                                        headerKey="-1"
                                        headerValue="---Chọn Module Web Api---"
                                        cssStyle="font-weight: bold;vertical-align: middle;width: 200px;"
                                        onBeforeTopics="BeforeHandler_loaibc" 
                                        onCompleteTopics="myCompleteTopics1"></sj:select> 
                                        <!--<div id="pgdtruyvan">-->
                                        <label id="lbldv" > Đơn vị:
                                        <sj:select
                                            href="%{idloadModuleApi}" 
                                            id="id_mapgd"
                                            name="posCode"
                                            list="poscdList" 
                                            listKey="sKey"
                                            listValue="sDesc"                              
                                            cssStyle="font-weight: bold;vertical-align: middle;width: 170px;"
                                            onBeforeTopics="BeforeHandler_loaibc" 
                                            onCompleteTopics="myCompleteTopics1"></sj:select> 
                                        </label> 
                                        <!--</div>-->
                                        <!--<input type="button" id="idload" name="nameloadap"  onclick="onchange_ab()" value="Lưu dữ liệu"/>-->
                                    <sj:submit id="idtruyvan" name="nametruyvan" value="Truy vấn" targets="table_data"  style="display: none;"
                                               onBeforeTopics="beforediv" onCompleteTopics="completediv"/>
                                </div>
                            </td>
                            <td style="width: 70%">
                                <div id="para_api"/>
                            </td>
                        </tr>
                    </table>
                </div>


                <div id="containParm" align="center">
                    <div id="loadingImageDiv" style="display: none;">
                        <h2 style='color: red'>Xin chờ đang tải dữ liệu!</h2>
                        </br>
                        <img id="loadingImage" src='img/loading.gif' border='0' >
                    </div>
                    <div id="table_data"></div>
                </div>
            </s:form>                    
        </div>
    </body>
</html>