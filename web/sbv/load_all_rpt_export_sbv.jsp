<%-- 
    Document   : exp_excel
    Created on : Jul 14, 2014, 4:17:12 PM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
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
            $(document).ready(function () {
                $("#mapGenReport").bind("click", function () {
//                    alert("1");
                    if ($("#exp_sbv input:checkbox:checked").length <=0)     
                    {
                        // none is checked
                        alert("Bạn phải chọn báo cáo để xuất số liệu!");
                        return;
                    }
                    $("#exportReport").trigger('click');    //Goi den su kien click cua nut that
                });                               
            });

            $.subscribe('beforeClick', function (event, data) {
                $("#divParams").empty();
            });
            function change_kybc()
            {
                $('#containTree').empty();
                var ky_bc = $("#idky_bc").val();
//                alert(ky_bc);
                //location.href ="/IMS_REPORTS/loadKyBC_Tree.action?ky_bc="+ky_bc;

                //$('#idma_bc').l $("#loadKhDetail").trigger("click");ad("loadKyBC_Tree.action?ky_bc=" + ky_bc);
                $.publish("reloadTree");
            }
            function loadbc()
            {
//                alert('vao onchange');
                 $("#loadDataReport").trigger("click");
            }
                        
        </script>

        <style>

            #container{
                width: 100%;
                height: 460px;
                border: 0px solid;
                padding-left: 0px;        
                /*background: #FFE6B0*/

            }

            #containTree{
                width: 18%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow: scroll;
                background: #DDFFDD;
                /*border: 1px solid;*/
            }

            #containParm{
                width: 100%;
                height: 200px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
                /*border: 1px solid;*/
                /*background: #d58512;*/
            }
            
            #containReport{
                width: 90%;
                height: 200px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
                border: 1px solid;
                /*background: #d58512;*/
            }

            #navParam{
                width: 81%;
                padding-left: 5px;
                height: 250px;
                float: right;
                /*border: 1px solid;*/
            }

        </style>
    </head>
    <body>
        <h4>Xuất báo cáo</h4>
        <hr/>    
        <!--<div id="container"  align="center">-->
        <s:form id="exp_sbv" action="export_report_sbv" theme="simple">
            <div id="navParam"  align="center">
                <table>
                    <tr>
                        <td width="4%">Ngày báo cáo: </td>
                        <td width="40%">   
                            <sj:datepicker name="ngay_bc" id="ngay_dcpt"
                                           value="%{new java.util.Date()}" 
                                           placeholder="DD/MM/YYYY" changeYear="true"  changeMonth="true" displayFormat="dd/mm/yy" 
                                           cssStyle="font-weight: bold;vertical-align: middle;"/>  
                        </td>
                    </tr>

                    <s:url id="remoteurl" action="loadParameters_sbv.action"></s:url>
                    <s:url id="loadtree_kybc" action="loadKyBC_Tree.action"></s:url>
                    <s:url id="reloadbc" action="loadallrpt_loadbc.action"></s:url>
                        <tr>
                            <td width="4%">Kỳ báo cáo: </td>
                            <td width="40%">       
                            <sj:select  href="%{remoteurl}" 
                                        id="idky_bc"
                                        name="ky_bc"
                                        list="lstKybc"                
                                        onchange="loadbc()"
                                        listKey="sKey"
                                        listValue="sDesc"
                                        headerKey="-1"
                                        headerValue="---Kỳ báo cáo---"
                                        onBeforeTopics="myBeforeTopics"
                                        onCompleteTopics="myCompleteTopics" cssStyle="font-weight: bold;vertical-align: middle;"></sj:select>                              
                            </td>
                        </tr>
                        <tr>
                            <td width="4%">Báo cáo: </td>
                            <td width="40%">                                 
                            <%--<sj:select  href="%{reloadbc}"
                                        formIds="exp_sbv"
                                        id="idmabc"
                                        name="mabc"
                                        list="lstObjRpt"                                             
                                        reloadTopics="reloadKybc"
                                        listKey="sKey"
                                        listValue="sDesc"
                                        value="1"
                                        headerKey="-1"
                                        headerValue="---Báo cáo---"
                                        onBeforeTopics="myBeforeTopics"
                                        onCompleteTopics="myCompleteTopics" 
                                        cssStyle="font-weight: bold;vertical-align: middle;width: 500px;">                                
                            </sj:select>       --%> 
                            <div id="containReport" align="center">
                                <div id="divListReport"></div>
                            </div>
                            </td>
                        </tr>
                        <tr>
                            <td width="4%">Loai báo cáo: </td>
                            <td width="40%">       
                            <sj:select  href="%{remoteurl}" 
                                        id="idloai_bc"
                                        name="loai_bc"
                                        list="lstLoaibc" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        cssStyle="font-weight: bold;vertical-align: middle;"></sj:select>                              
                            </td>
                        </tr>
                        <tr>
                            <td width="4%">Loại file: </td>
                            <td width="40%">       
                            <sj:select  href="%{remoteurl}" 
                                        id="idloai_file"
                                        reloadTopics="reloadGroupList" 
                                        name="loai_file"
                                        list="lstLoaifile" 
                                        listKey="sKey"
                                        listValue="sDesc"                                     
                                        onBeforeTopics="myBeforeTopics"
                                        onCompleteTopics="myCompleteTopics" cssStyle="font-weight: bold;vertical-align: middle;"></sj:select>                              
                            </td>
                        </tr>
                        <tr>
                            <td width="4%">Lần xuất: </td>
                            <td width="40%">       
                            <sj:select  href="%{remoteurl}" 
                                        id="idlan_xuat"
                                        name="lan_xuat"
                                        list="lstLanxuat" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        value="1"
                                        emptyOption="true" 
                                        headerKey="-1"
                                        headerValue="---Lần xuất---"                                       
                                        onBeforeTopics="myBeforeTopics"
                                        onCompleteTopics="myCompleteTopics" cssStyle="font-weight: bold;vertical-align: middle;"></sj:select>                              
                            </td>
                        </tr>

                    <tr>
                        <td>

                        </td>
                        <td>
                            <sj:submit id="exportReport" value="Tiếp theo" formIds="exp_sbv" targets="divParams" indicator="loadingImage-next" onBeforeTopics="beforeClick" style="display:none"/>
                        </td>                    
                    </tr>
                </table> 
                <hr/>
                <img id="loadingImage-next" src="img/loaderB32.gif" style="display:none"/>
                <div id="loadingImageDiv_rpt" style="display: none;">
                    <img id="loadingImage_rpt" src='img/loading.gif' border='0'>
                </div>
                <div align="right" id="link">
                    <a href="javascript:void(0);" id="mapGenReport">Xuất báo cáo</a>
                    <img id="loadingImage" src="img/loading.gif" style="display:none"/>
                </div>
                <div id="divParams"></div>
            </div>
        <div id="containTree">
            <sjt:tree href="%{loadtree_kybc}"                               
                      id="idpos_cd"                              
                      formIds="exp_sbv"
                      reloadTopics="reloadTree"
                      name="pos_cd"
                      jstreetheme="apple"                              
                      childCollectionProperty="children"
                      nodeTitleProperty="title"
                      nodeIdProperty="idtree_bc"
                      checkbox="true"
                      />
        </div>
        </s:form>  
        
        <sj:a id="loadDataReport" formIds="exp_sbv" targets="divListReport" 
                  indicator="loadingImage" href="%{reloadbc}" onBeforeTopics="before-next" 
                  onCompleteTopics="after-next"></sj:a>
    </body>
</html>
