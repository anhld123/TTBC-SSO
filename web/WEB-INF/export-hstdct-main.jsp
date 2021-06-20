<%-- 
    Document   : export_hstdct
    Created on : Jun 17, 2014, 7:35:57 PM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thu dua du lieu vao</title>
        <s:head/>
        <sj:head/>
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
            $.subscribe('beforediv1', function(event, data) {
                $("#divExportReport").empty();
                $("#divExportReport").hide();
                $("#loadingImageDiv").show();
            });

            $.subscribe('completediv1', function(event, data) {
                $("#loadingImageDiv").hide();
                $("#divExportReport").show();
                //$("#contentDiv").slideDown('slow');
            });
            
            $(function() {
                new DateMask("dd/MM/yyyy", "export_date");
            });
        </script>
    </head>
    <body>
        <div id="container">
            <s:form id="loadTree" var="test" action="exportFile" theme="simple">
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
                    showThemeIcons="false"
                    />
                </div>
                
                <div id="containParm">
                    <table>
                        <tr>
                            <td width="190" style="text-align: right">Chọn bảng dữ liệu cần xuất:</td>
                            <td width="500">
                                <s:select id="moduletable" 
                                          name="module_table"
                                          list="lstModuleObj" 
                                          listKey="sKey"
                                          listValue="sDesc"
                                          emptyOption="true" 
                                          headerKey="-1"
                                          headerValue="   ---   Xuất tất cả các hồ sơ   ---   " label="Chọn bảng dữ liệu cần xuất">                    
                                </s:select>
                            </td>
                        </tr>
                        
                        <tr>
                            <td width="190" style="text-align: right">Ngày xuất dữ liệu:</td>
                            <td width="500">
                                <sj:datepicker name="export_date" label="Ngày xuất dữ liệu" onblur="validatedate(this.value)" value="%{new java.util.Date()}" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/>
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
