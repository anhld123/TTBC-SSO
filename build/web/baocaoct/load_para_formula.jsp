<%-- 
    Document   : export_hstdct
    Created on : Jun 17, 2014, 7:35:57 PM
    Author     : LION
--%>
<%@page import="vbsp.ims.model.RptFormula"%>
<%@page import="vbsp.ims.model.ValueFormula"%>
<%@page import="vbsp.ims.dao.DaoRptFormula"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <style>
        #menuBcttv{
            width: 100%;
            height: 30px;                
            border: 1px solid; 
            padding-bottom: 5px;
        }
        
        #containBcttv{
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
        
        
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%--<s:head/>--%>
        <%--<sj:head/>--%>
        <script src="js/JavaScriptUtil.js"></script>
        <script src="js/InputMask.js"></script>
        <script src="js/Parsers.js"></script>
        <script src="js/Checkdate.js"></script>

        <style>
            #container{
                width: 100%;
                height: 400px;
                border: 1px solid;
            }

            #containTree{
                width: 25%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 400px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 70%;
                height: 400px;
                padding-left: 20px;
                float: left;
            }

            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: .9em;
            }

            .report_group_form{
                width: 100%;
            }
        </style>

        <script>
            $.subscribe('beforediv1', function (event, data) {
                $("#divExportReport").empty();
                $("#divExportReport").hide();
                $("#loadingImageDiv").show();
            });
            
            $.subscribe('completediv1', function (event, data) {
                $("#loadingImageDiv").hide();
                $("#divExportReport").show();
                //$("#contentDiv").slideDown('slow');
            });
            
//            $(function () {
//                new DateMask("dd/MM/yyyy", "export_date");
//            });
        </script>
    </head>
    <body>
        <div id="container">
            </br>
            <s:form id="loadTree" var="test" action="exportFileFormula" theme="simple">
                <s:hidden id="save_id" name="save_id"/>

                <% DaoRptFormula daoFormula = new DaoRptFormula();
                    String sSave_id = request.getAttribute("save_id").toString();
                    System.err.println("Save_id "+sSave_id);
                    RptFormula lstObjFormula = daoFormula.getLoadEditFormula(sSave_id);
                    System.err.println("Loai bao cao la " + lstObjFormula.getReport_type());
                    if (lstObjFormula.getReport_type().equals("01")) {
                %>
                <div id="containTree">
                    <sjt:tree
                        name="pos_cd"
                        id="treeDynamicCheckboxes"
                        jstreetheme="apple"
                        rootNode="nodes_pos"
                        childCollectionProperty="children"
                        nodeTitleProperty="title"
                        nodeIdProperty="id"
                        openAllOnLoad="true"
                        checkbox="true"
                        showThemeDots="false"
                        showThemeIcons="false"
                        />
                </div>
                <%}%>
                <div id="containParm">
                    <table>    
                        <tr>
                            <td width="190" style="text-align: right">Ngày xuất dữ liệu:</td>
                            <td width="500">
                                <%--<sj:datepicker name="export_date" label="Ngày xuất dữ liệu" onblur="validatedate(this.value)" value="%{new java.util.Date()}" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/>--%>
                            <sj:datepicker name="export_date" value="%{new java.util.Date()}" 
                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/>
                            </td>
                        </tr>

                        <tr>
                            <td width="190" ></td>
                            <td width="500">
                                <sj:submit id="submitform" value="Xuất dữ liệu" targets="divExportReport" 
                                           onBeforeTopics="beforediv1"
                                           onCompleteTopics="completediv1"    />
                            </td>
                        </tr>
                    </table>
                    <div id="loadingImageDiv" style="display: none;">
                        <hr/>
                        <img id="loadingImage" src='img/loading.gif' border='0' >
                    </div>

                    <div id="divExportReport"></div>
                </div>
            </s:form>
        </div>
    </body>
</html>
