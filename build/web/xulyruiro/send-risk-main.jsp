<%-- 
    Document   : process_risk
    Created on : Jun 18, 2014, 9:21:53 PM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <head>
           <s:head/>
        <sj:head/>
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
             function onReturn()
            {
                $("#container").empty();
                $("#container").text('');
            }
            var bsubmit=false;
            function onclear()
            {
                bsubmit=true;
            }
            
            function submitloadData()
            {
                if(!bsubmit)
                {
                    alert('Bạn chưa xem dữ liệu nên không thể gửi dữ liệu đi được');
                     $("#divExportReport").html('<span style="color:red"><h2><span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa xem dữ liệu nên không thể gửi dữ liệu đi được!</br>\n\
                        Bạn nhấn vào <span style="color:blue"> Xem dữ liệu </span> để xem dữ liệu sau đó mới gửi dữ liệu đi được</h2></span>');
                    return;
                }
                else
                {
//                    var trangthai_xlrr=$("#trangthai_xlrr").val();
//                    alert('Bạn đã view dữ liệu thành công '+trangthai_xlrr);
                    $("#loadSendData")[0].click();          
                    bsubmit=false;
                }
            }
            
        </script>

        <style>
            body,td,th,font{ font-family:Tahoma; font-size:12px; }

            #container{
                width: 100%;
                height: 450px;
                border: 0px solid;
                padding-left: 0px;                
            }

            #containTree{
                width: 18%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 80%;
                height: 450px;
                padding-left: 20px;
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
                height: 26px;
                border: 1px solid;      
                padding:8px;
            }
            #navParam2{
                height: 28px;
                border: 0px solid;
                margin-left: 10px;
                font-weight: bold;
            }
        </style>
     
    </head>
    <body topmargin="0" leftmargin="5">        
        <div id="container" >
            <!--thu nhe-->
            <%--<s:url id="loadDataRisk" action="loadDataRisk" includeContext="false">--%>
            <%--<s:param name="nam_xlrr" value="nam_xlrr"/>--%>
            <%--</s:url>--%>
            <s:form id="loadFormSendRisk"  name="loadFormSendRisk" var="test" action="sendDataRisk" theme="simple">
                <div id="navParam" >
                    <div id="navParam2">
                         <s:label value="XL theo:" cssStyle="color: #029c44;" />
                        <s:select id="vb_xlrr" 
                                  name="vb_xlrr"
                                  list="lstVbXlrr" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  headerKey="-1"
                                  headerValue="-- Chọn --" cssStyle="color: red;vertical-align: middle;">                    
                        </s:select>
                        <s:label value="Năm xử lý:" cssStyle="color: #029c44;" />
                        <s:select id="nam_xlrr" 
                                  name="nam_xlrr"
                                  list="lstNamXlrr" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  value="defaultNamxlrr" cssStyle="color: red;vertical-align: middle;">                    
                        </s:select>
                        &nbsp;
                        <s:label value="Đợt xử lý:" cssStyle="color: #029c44;" />
                        <s:select id="dot_xlrr" 
                                  name="dot_xlrr"
                                  list="lstDotXlrr" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  headerKey="-1"
                                  headerValue="-- Chọn --" cssStyle="color: red;vertical-align: middle;width: 100px;">                    
                        </s:select>
                        &nbsp;
                        <s:label value="Nhóm nợ:" cssStyle="color: #029c44;" />
                        <s:select id="nhom_xlrr" 
                                  name="nhom_xlrr"
                                  list="lstNhomXlrr" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  headerKey="-1"
                                  headerValue="-- Chọn --" cssStyle="color: red;vertical-align: middle;width: 120px;">                    
                        </s:select>
                        &nbsp; 
                         <s:label value="Nguồn vốn:" cssStyle="color: #029c44;" />
                        <s:select id="nguon_von"
                                  name="nguon_von"
                                  list="lstNguonvon" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  value="defaultNguonvon"
                                  cssStyle="color: red;width: 120px; vertical-align: middle;">                    
                        </s:select>
                        &nbsp;        
                        <s:url id="viewDataSend" action="ViewDataSend.action"></s:url>
                        <sj:submit id="idViewData" name="nameViewData" href="%{viewDataSend}" value="Xem dữ liệu" targets="divExportReport"
                                   onBeforeTopics="beforediv1"
                                   onCompleteTopics="completediv1" onclick="onclear()" />
                        
                        <sj:submit id="loadSendData" name="loadSendData" value="Gửi dữ liệu" targets="divExportReport"
                                   onBeforeTopics="beforediv1"
                                   onCompleteTopics="completediv1" cssStyle="display: none"/>
                        <input type="button" id="loadSendDatatmp" name="loadSendDatatmp" onclick="submitloadData()" value="Gửi dữ liệu"/>
                        
                         <input type="button" id="idReturn" name="nameReturn" 
                                   onclick="onReturn()" value="Quay ra" style="float: right; height:28px;width:95px;"/>
                        <!--<input type="button" id="idButton" name="idButton" onclick="onclickBrowseRisk()" value="Phê Duyệt"/>-->
                    </div>
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

                <div id="loadingImageDiv" style="display: none;">
                    <img id="loadingImage" src='img/loading.gif' border='0' >
                </div>

                <div id="containParm" align="center">
                    <div id="divExportReport"></div>
                </div>
            </s:form>

        </div>
    </p>
</body>
</html>
