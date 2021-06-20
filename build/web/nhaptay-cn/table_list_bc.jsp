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

       
        <%--<sj:head/>--%>      
        <!--<script type="text/javascript" src="js/jquery-1.10.2.js"></script>-->
        <script>                             
                        
            
            function onTranData()
            {                                     
                $('#divExportReport').empty();  
                $("#idTranPhts")[0].click();                
            }  ;
            
            function onTranData2()
            {                                      
                $('#divExportReport').empty();  
                $("#idTranPhts2")[0].click();                
            }  ;
            
            function onTranData3()
            {                                      
                $('#divExportReport').empty();  
                $("#idTranPhts3")[0].click();                
            }  ;
            function onTranData4()
            {                                      
                $('#divExportReport').empty();  
                $("#idTranPhts4")[0].click();                
            }  ;
        </script>
        <style>
            
        </style>

        
    </head>
    <body>        
        <s:form id="exp_phts_input_2" action="export_report_phts_input_2" theme="simple">            
            <div   align="center" >                     
                <table border="0" id="tablems01" style="width:100%;height: 100% " cellspacing="0" >
                                    <s:iterator value="#attr.lstDulieuNt_pgd" var="modelView" status="rowstatus">                    
                                    <tr height="22">                                            
                                            <td align = "center" >
                                                <s:if test="D1.equalsIgnoreCase('01')">
                                                    <div align="center" id="link">
                                                        <s:url id="idTran" action="chart001.action"></s:url>                                      
                                                        <sj:submit id="idTranPhts" name="nameSend" href="%{idTran}" value="Biểu đồ dạng 1" targets="divExportReport"
                                                                   onBeforeTopics="beforediv_send"
                                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                        <input type="button" class="btn btn-link" id="idTranPhtsTmp" name="nameidSendtmp"  onclick="onTranData()" value="Biểu đồ dạng 1"/>
                                                        &nbsp;&nbsp;                    
                                                    </div>  
                                                </s:if>                                                
                                            </td>    
                                            <td align = "center" >
                                                <s:if test="D2.equalsIgnoreCase('02')">
                                                    <div align="center" id="link">
                                                        <s:url id="idTran2" action="chart002.action"></s:url>                                      
                                                        <sj:submit id="idTranPhts2" name="nameSend2" href="%{idTran2}" value="Biểu đồ dạng 2" targets="divExportReport"
                                                                   onBeforeTopics="beforediv_send"
                                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                        <input type="button" class="btn btn-link" id="idTranPhtsTmp2" name="nameidSendtmp2"  onclick="onTranData2()" value="Biểu đồ dạng 2"/>
                                                        &nbsp;&nbsp;                    
                                                    </div>  
                                                </s:if>                                                
                                            </td>  
                                            <td align = "center" >
                                                <s:if test="D3.equalsIgnoreCase('03')">
                                                    <div align="center" id="link">
                                                        <s:url id="idTran3" action="chart003.action"></s:url>                                      
                                                        <sj:submit id="idTranPhts3" name="nameSend3" href="%{idTran3}" value="Biểu đồ dạng 3" targets="divExportReport"
                                                                   onBeforeTopics="beforediv_send"
                                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                        <input type="button" class="btn btn-link" id="idTranPhtsTmp3" name="nameidSendtmp3"  onclick="onTranData3()" value="Biểu đồ dạng 3"/>
                                                        &nbsp;&nbsp;                    
                                                    </div> 
                                                </s:if>                                                
                                            </td>  
                                            <td align = "center" >
                                                <s:if test="D4.equalsIgnoreCase('04')">
                                                    <div align="center" id="link">
                                                        <s:url id="idTran4" action="chart004.action"></s:url>                                      
                                                        <sj:submit id="idTranPhts4" name="nameSend4" href="%{idTran4}" value="Biểu đồ dạng 4" targets="divExportReport"
                                                                   onBeforeTopics="beforediv_send"
                                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                        <input type="button" class="btn btn-link" id="idTranPhtsTmp4" name="nameidSendtmp4"  onclick="onTranData4()" value="Biểu đồ dạng 4"/>
                                                        &nbsp;&nbsp;                    
                                                    </div> 
                                                </s:if>                                                
                                            </td> 
                                        
                                    </tr>
                                    </s:iterator>
                                            
                                </table>    
                
<!--                <div id="loadingImageDiv_data" style="margin-left: 20px;display: none;" >
                                        <img id="loadingImage" src='img/loading.gif' border='0' >
                </div>-->
                 &nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;
                 <div id="divExportReporttt" align="center" ></div>
            </div>                    
        </s:form>     
        
    </body>
</html>
