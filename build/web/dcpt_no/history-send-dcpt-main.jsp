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
    <s:head/>
    <sj:head/>
    <head>           
        <script>
            var bsubmit = false;
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

            function onclear()
            {
                $("#divMessage").empty();
                
//                $("#idButtondonvi").click();
            }

            

            function onReturn()
            {
                $("#container").empty();
                $("#container").text('');
            }

            function onclickBlockAll()
            {
                $("#divMessage").empty();
//                alert('Vao khoa toan bo chi nhanh');
                if ($("#formviewHistoryDcpt input:checkbox:checked").length > 0)
                {
                  

                        $("#idBockAllDcpt")[0].click();
                }
                else
                {
                    // none is checked
                    alert("Bạn phải chọn chi nhánh cần khóa!");
                }
            }
            
             function onclickOpenAll()
            {
                $("#divMessage").empty();
//                alert('Vao khoa toan bo chi nhanh');
                if ($("#formviewHistoryDcpt input:checkbox:checked").length > 0)
                {
                  

                        $("#idOpenAllDcpt")[0].click();
                }
                else
                {
                    // none is checked
                    alert("Bạn phải chọn chi nhánh cần mở!");
                }
            }
            function stopRKey(evt) {
                var evt = (evt) ? evt : ((event) ? event : null);
                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
                if ((evt.keyCode == 13) && (node.type == "text")) {
                    return false;
                }
            }

            //Disable enter key form submit            
            document.onkeypress = stopRKey;
        </script>

        <style>
            body,td,th,font{ font-family:Tahoma; font-size:12px; }

            #container{
                width: 100%;
                height: 500px;
                border: 0px solid;
                padding-left: 0px;     
                /*background:green;*/
                /*text-align:center;*/
            }

            #containTree{
                width: 18%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 500px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 90%;
                height: 450px;
                /*background: brown;*/
                padding-left: 20px;
                /*float: left;*/
                overflow: scroll;
                /*border: 1px solid;*/
                /*text-align:center*/
/*                margin-left: auto;
                margin-right: auto;
                width: 6em*/
            }

            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: 12px;
            }

            .report_group_form{
                width: 100%;
            }

            #navParam{
/*                width: 90%;*/
                height: 26px;
                padding:8px;

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
                width: 97%;
                height: 50px;
                border: 0px solid;
                margin-left: 10px;
                font-weight: bold;
            }
            #Input{
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                padding: 3px 0px 3px 3px;
                margin: 5px 1px 3px 0px;
                border: 1px solid rgba(81, 203, 238, 1);

            }
            #divMessage
            {
                height: 28px;
                /*border: 1px solid;*/     
                /*width: 40%;*/
                /*float: right;*/
/*                padding:3px; 
                border: 1px solid;
                position: fixed;*/
                /*background: brown;*/
                 /*height: 28px;*/
                /*border: 1px solid;*/     
                width: 40%;
                float: right;
            }
            #divbutton
            {
                height: 28px;
                /*border: 1px solid;*/     
                width: 100px;
                float: right;
            }
            input[type="text"]
            {
                width: 100%;
                /*border: 0px;*/
                /*color: #000000*/
                border-color: #18ab29;
                background: #F9F9F9;
                color:#666666;
            }
            input[type=text]:focus, textarea:focus {
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                padding: 3px 0px 3px 3px;
                margin: 5px 1px 3px 0px;
                border: 1px solid rgba(81, 203, 238, 1);
            }
        </style>

    </head>
    <body topmargin="0" leftmargin="5">        
        <div id="container"  align="center">
            <!--thu nhe-->
            <%--<s:url id="loadDataRisk" action="loadDataRisk" includeContext="false">--%>
            <%--<s:param name="nam_xlrr" value="nam_xlrr"/> cssStyle="text-align:center" --%>
            <%--</s:url>--%>
            <s:form id="idloadHistoryView"  name="nameloadHistoryView" var="test" action="loadHistoryViewdcpt" theme="simple">
                <div id="navParam" align="left">
                    <div id="navParam2">
                        <!--</p>-->
                        <s:url id="reloadData" action="reloadPGD" includeParams="post"></s:url>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <s:label value="Mã CN:" cssStyle="color: #029c44;" />
                        <sj:select 
                                href="%{reloadData}" 
                                onChangeTopics="reloadTotruong"      
                                id="macn_dcpt" 
                              name="macn_dcpt"
                              list="lstMaCN" 
                              listKey="sKey"
                              listValue="sDesc" 
                              headerKey="-1"
                                  headerValue="-- Chọn --"  cssStyle="color: red;vertical-align: middle;">                    
                        </sj:select>
                        <s:label value="Mã PGD:" cssStyle="color: #029c44;" />
                        <sj:select 
                            href="%{reloadData}" 
                            reloadTopics="reloadTotruong"
                            onChangeTopics="reloadMaPGD" 
                            id="mapgd_dcpt" 
                                  name="mapgd_dcpt"
                                  list="lstMaPGD" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  headerKey="-1"
                                  headerValue="-- Chọn --" cssStyle="color: red;vertical-align: middle;width: 160px;">                    
                        </sj:select>
                        <!--&nbsp;-->
                        <s:label value="Mã xã:" cssStyle="color: #029c44;" />
                        <sj:select 
                                href="%{reloadData}" 
                                reloadTopics="reloadMaPGD"
                                id="maxa_dcpt" 
                                  name="maxa_dcpt"
                                  list="lstMaXa" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  headerKey="-1"
                                  headerValue="-- Chọn --" cssStyle="color: red;vertical-align: middle;width: 160px;">                    
                        </sj:select>

                        <!--&nbsp; value="defaultNguonvon"-->                     
                        <sj:submit id="idSubmit" name="loadsubmitform" value="Xem dữ liệu" targets="divExportReport" onclick="onclear()"
                                   onBeforeTopics="beforediv1"
                                   onCompleteTopics="completediv1" />
                        
                        <input type="button" id="idButton" name="idButton" onclick="onclickBlockAll()" value="Khóa chi nhánh"/>
                         <input type="button" id="idOpenButton" name="idOpenButton" onclick="onclickOpenAll()" value="Mở chi nhánh"/>
                        <div id="divbutton">
                              <input type="button" id="idReturn" name="nameReturn" 
                               onclick="onReturn()" value="Quay ra" style="float: right; height:28px;width:95px;"/>
                        </div>
                        <div id="divMessage" >
                          
                        </div>
                    </div>

                </div>
                <div id="loadingImageDiv" style="display: none;">
                    <img id="loadingImage" src='img/loading.gif' border='0' >
                </div>
                <div id="containParm" align="center">
                    <div align="center" id="divExportReport"></div>
                </div>
            </s:form>

        </div>
    </p>
</body>
</html>
