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
        <sj:head/> 
        <script src="js/JavaScriptUtil.js"></script>
        <script src="js/InputMask.js"></script>
        <script src="js/Parsers.js"></script>
        <script src="js/Checkdate.js"></script>
        <script>
            $(function () {
                $("#mapGenReport").bind("click", function () {
                    $("#exportReport").trigger('click');    //Goi den su kien click cua nut that
                });
            });

            $.subscribe('beforeClick', function (event, data) {
                $("#divParams").empty();
            });
        </script>

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
                background: #FFE6B0
                
            }

            #containTree{
                width: 39%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow: scroll;
                background: #DDFFDD;
            }

            #containParm{
                width: 60%;
                height: 450px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
                /*background: #d58512;*/
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
                height: 0px;
                padding:0px;
                padding-bottom: 0px;
                padding-top: 0px;
                /*background:  #018c3b;*/
                /*margin:5px;*/
                /*border-radius: 10px; //bo tron goc*/
                border: 1px solid;                
                /*                height: 50px;
                                border: 1px solid;  
                                border-radius: 10px; //bo tron goc
                                -moz-border-radius: 10px;
                                margin:5px;
                                padding:5px;*/
            }
            #navParam2{
                height: 2px;
                border: 0px solid;
                margin-left: 10px;
                font-weight: bold;
                border-left: 40px;
                float: left;
                padding-bottom: 0px;
                padding-top: 0px;
            }
            #message_suc_err
            {
                height: 30px;
                border: 0px solid;
                padding-bottom: 0px;
                padding-top: 0px;
            }
        </style>
    </head>
    <body>
        <h4>Xuất báo cáo</h4>
        <hr/>    
        <div id="container"  align="center">
            <s:form id="exp_sbv" action="export_report_sbv" theme="simple">
                <div id="navParam" >
                        <table border="1">
                            <tr>
                                <td width="20%">Ngày báo cáo: </td>
                                <td width="40%">   
                                    <sj:datepicker name="ngay_bc" id="ngay_dcpt"
                                                   value="%{new java.util.Date()}" 
                                                   placeholder="DD/MM/YYYY" changeYear="true"  changeMonth="true" displayFormat="dd/mm/yy" 
                                                   cssStyle="font-weight: bold;vertical-align: middle;"/>  
                                </td>
                            </tr>

                            <s:url id="remoteurl" action="loadParameters_sbv.action"></s:url>
                                <tr>
                                    <td width="20%">Kỳ báo cáo: </td>
                                    <td width="40%">       
                                    <sj:select  href="%{remoteurl}" 
                                                id="idky_bc"
                                                name="ky_bc"
                                                list="lstKybc" 
                                                listKey="sKey"
                                                listValue="sDesc"
                                                value="1"
                                                headerKey="-1"
                                                headerValue="---Kỳ báo cáo---"
                                                onBeforeTopics="myBeforeTopics"
                                                onCompleteTopics="myCompleteTopics" cssStyle="font-weight: bold;vertical-align: middle;"></sj:select>                              
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">Loai báo cáo: </td>
                                    <td width="40%">       
                                    <sj:select  href="%{remoteurl}" 
                                                id="idloai_bc"
                                                name="loai_bc"
                                                list="lstLoaibc" 
                                                listKey="sKey"
                                                listValue="sDesc"
                                                value="B"
                                                headerKey="-1"                                       
                                                headerValue="---Loại báo cáo---" 
                                                cssStyle="font-weight: bold;vertical-align: middle;"></sj:select>                              
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">Loại file: </td>
                                    <td width="40%">       
                                    <sj:select  href="%{remoteurl}" 
                                                id="idloai_file"
                                                reloadTopics="reloadGroupList" 
                                                name="loai_file"
                                                list="lstLoaifile" 
                                                listKey="sKey"
                                                listValue="sDesc"
                                                value="N"
                                                emptyOption="true" 
                                                headerKey="1"
                                                headerValue="---Loại file---"                                       
                                                onBeforeTopics="myBeforeTopics"
                                                onCompleteTopics="myCompleteTopics" cssStyle="font-weight: bold;vertical-align: middle;"></sj:select>                              
                                    </td>
                                </tr>
                                <tr>
                                    <td width="20%">Chọn chi nhánh: </td>
                                    <td width="40%">       
                                    <sj:select  href="%{remoteurl}" 
                                                id="idmacn"
                                                reloadTopics="reloadGroupList" 
                                                name="macn"
                                                list="lstMacn" 
                                                listKey="sKey"
                                                listValue="sDesc"
                                                value="000100"
                                                emptyOption="true" 
                                                headerKey="1"
                                                headerValue="---Mã chi nhánh--"
                                                onBeforeTopics="myBeforeTopics"
                                                onCompleteTopics="myCompleteTopics" cssStyle="font-weight: bold;vertical-align: middle;"></sj:select>                              
                                    </td>
                                </tr>
                                <img id="loadingImage-next" src="img/loaderB32.gif" style="display:none"/>
                                <div id="loadingImageDiv_rpt" style="display: none;">
                                    <img id="loadingImage_rpt" src='img/loading.gif' border='0'>
                                </div>
                                <tr>
                                    <td></td>
                                    <td>
                                    <sj:submit id="exportReport" value="Tiếp theo" targets="divParams" indicator="loadingImage-next" onBeforeTopics="beforeClick" style="display:none"/>
                                </td>                    
                            </tr>
                        </table> 
                </div>
                <div id="containTree">
                    <sjt:tree
                        name="poscd"
                        id="treeDynamicCheckboxes"
                        jstreetheme="apple"
                        rootNode="nodes_pos"
                        childCollectionProperty="children"
                        nodeTitleProperty="title"
                        nodeIdProperty="id"
                        openAllOnLoad="true"
                        checkbox="true"
                        showThemeDots="false"
                        showThemeIcons="true" 
                        />
                </div>
            </s:form>  

   
        <hr/>
        <div align="right" id="link">
            <a href="javascript:void(0);" id="mapGenReport">Xuất báo cáo</a>
            <img id="loadingImage" src="img/loading.gif" style="display:none"/>
            <!--<img id="loadingImage" src="img/loaderB32.gif" style="display:none"/>-->
        </div>
        <div id="containParm" align="center">
            <div id="divParams"></div>
        </div>
             </div>
    </body>
</html>
