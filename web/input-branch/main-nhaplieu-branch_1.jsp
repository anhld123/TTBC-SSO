<%-- 
    Document   : main-nhaplieu-branch
    Created on : Dec 20, 2018, 3:31:05 PM
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
        <sj:head/>
        <script src="js/sweetalert.min.js"></script>
        <script type="text/javascript" src="input-branch/js/inputbranch.js"></script>

        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>

        <link  rel="stylesheet" type="text/css" href="input-branch/css/inputbranch.css"/>
    </head>
    <style>
        #parameter_branch{
            width: 100%;
            height: 100%;                
            border: 1px solid; 
            padding-bottom: 0px;
            background: #fbeed5;
        }

        #menuinputbranch{
            width: 100%;
            height: 55px;                
            border: 1px solid; 
            padding-bottom: 2px;
            background: #F0FFFF;
        }
    </style>

    <script>
        //Desc: Lan dau tien load trang, goi den su kien click load all bc

    </script>
    <body>
        <div id="menuinputbranch"  align="center">
            <s:form id="formidDanhmucbc" action="loadThamsochomau.action"  theme="simple">
                <s:url id="layMauNhaplieuId" action="LoadAllMauNhaplieu.action"/>
                <table border="1" cellspacing="0px" cellpadding="0px" style="width: 98%;margin: 3px 0px; ">
                    <tr align="center">
                        <!--style="display: none"-->
                        <td>
                            <div id="chonmaushow" >
                                <s:label value="Nhóm báo cáo: "/>
                                <sj:select  href="%{layMauNhaplieuId}"
                                            id="grouprpt_id" 
                                            name="grouprpt_id"
                                            list="lstGroupRpt" 
                                            listKey="sKey"
                                            listValue="sDesc"           
                                            headerKey="-1"
                                            headerValue="-- Chọn --" 
                                            cssStyle="font-weight: bold;vertical-align: middle;width: 500px;"
                                            onBeforeTopics="beforediv" 
                                            onCompleteTopics="completediv"
                                            onChangeTopics="reloadGroupList">                    
                                </sj:select>
                                
                            </div>
                        </td>
                        <td >
                            <div id="loadingImageDiv" style="margin: 0px 0px;display: none">  
                                <img id="loadingImage" src='img/loading.gif' border='0' >
                            </div>
                        </td>
                    </tr>
                    <tr align="center">
                        <td>
                            <div id="chonmaushow" >
                                <s:label value="   Mẫu báo cáo: "/>
                                <sj:select  href="%{layMauNhaplieuId}"
                                            formIds="formidDanhmucbc"
                                            id="themmau_id" 
                                            name="khoa"
                                            list="lstAllMau" 
                                            listKey="sKey"
                                            listValue="sDesc"           
                                            headerKey="-1"
                                            headerValue="-- Chọn --" 
                                            cssStyle="font-weight: bold;vertical-align: middle;width: 500px;"
                                            onBeforeTopics="beforediv" 
                                            onCompleteTopics="completediv"
                                            reloadTopics="reloadGroupList" 
                                            onchange=" $('#Divformnhaplieu').empty();$('#loadPara_id')[0].click();">                    
                                </sj:select>
                            </div>
                        </td>
                        <td >
                            <div id="loadingImageDiv" style="margin: 0px 0px;display: none">  
                                <!--<img id="loadingImage" src='img/loading.gif' border='0' >-->
                            </div>
                        </td>
                    </tr>
                </table>
                <!--</div>-->
                <sj:submit id="loadPara_id" name="loadParameter" value="Tải dữ liệu" targets="Divformnhaplieu" onBeforeTopics="beforediv"
                           onCompleteTopics="completediv" cssStyle="display: none"/>
            </s:form>
        </div>
        <div id="Divformnhaplieu">

        </div>
    </body>
</html>
