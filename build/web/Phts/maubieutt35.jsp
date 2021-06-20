<%-- 
    Document   : exp_excel
    Created on : Jul 14, 2014, 4:17:12 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <sj:head/>
        <script>
            
            var change_color = '#FFB951';
            function mover(aa) {
                bgcolor = aa.style.backgroundColor;
                aa.style.backgroundColor = change_color;
            }
            function mout(aa) {
                aa.style.backgroundColor = bgcolor;
            }      
            
            function onTranDataInputTT35()
            {   
                var sContentInput = $.trim($("#idContentTT35").val()).length;   
//                alert(sContentInput)
                if (sContentInput <1)
                {
//                    alert('Bạn đã nhập dữ liệu nguyên nhân chênh lệch nên không thể nhập dữ liệu cho trường này');
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải nhập phản hồi trước khi chuyển tiếp! </h2>");                    
                    return;
                }
                //$('#message_suc_err').empty();
                $('#divExportReport').empty();                
                $("#idTranPhtsInputTT35")[0].click(); 
//                setTimeout(window.location.reload, 5000);
//                setTimeout(location.reload.bind(location), 4000);
            }
            
            function sleep(delay) {
                var start = new Date().getTime();
                while (new Date().getTime() < start + delay);
            }

        </script>      
        <style>
            .underline {
                text-decoration: underline;
            }
            
            h3 {
                text-align: center;
            }
            
            #divTitlePhts{
                color: blue; 
                font-weight: bolder; 
                font-size: x-large;
            }
        </style>
    </head>
    <body>
        <div id="divTitlePhts">&nbsp;&nbsp; <s:property  value="macn" /> - Mẫu biểu thông tư 35</div>
        <hr/>            
        
        <s:form id="tt35_phts" action="tt35_report_phts" theme="simple">
            <!--<div ><s:property  value="ngay_bc" /></div>-->
            <input type="hidden" value="<s:property  value="ngay_bc" />"  name="ngay_bc" />
            <input type="hidden" value="<s:property  value="macn" />"  name="macn" />
            <input type="hidden" value="<s:property  value="tonghop" />"  name="tonghop" />
            <input type="hidden" value="<s:property  value="tencn" />"  name="tencn" />                      
            <table border="1" class="editDelete" style="width: 96%" id="tablepl01" align="center">
                <tr>
                    <th style="width: 7000px;">Mẫu biểu thông tư 35</th>                            
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                           
                <tr style="color: #0000FF; font-weight: bold;" onmouseover="mover(this);"  onmouseout="mout(this);">                                    
                    <td style="width: 60px;"><s:property  value="D1" /></td>   
                </tr>                            
                </s:iterator>                        
            </table>   
            &nbsp;
            <div align="center"  id="divExportReport"></div>   
            <table class="editDelete" style="width: 96%" id="tablepl01" align="center">
                    <tr>
                        <s:if test="Grade.equalsIgnoreCase('3')">
                            <td >
                            <span id="idTitlePhts">Nội dung phản hồi của chi nhánh</span>
                            </td>
                        </s:if>
                        <s:if test="Grade.equalsIgnoreCase('2')">
                                <td>
                                <span id="idTitlePhts">Nội dung phản hồi gửi Tư</span>
                                </td> 
                        </s:if>
                    </tr>
                    <s:if test="Grade.equalsIgnoreCase('3')">
                        <tr >
                            <td  colspan="2" align="center" >
                                <textarea id="idContentTT35"
                                          name="ContentTT35"
                                          style="width: 100%;background-color: #DDFFDD;"                                           
                                          rows="6" readonly="readonly"><s:property value='contentPhanhoiTT35'/></textarea>
                            </td>
                        </tr>
                    </s:if>   
                    <s:if test="Grade.equalsIgnoreCase('2')">
                        <tr >
                            <td  colspan="2" align="center" >
                                <textarea id="idContentTT35"
                                          name="ContentTT35"
                                          style="width: 100%;background-color: #faebcc;"                                           
                                          rows="6"><s:property value='contentPhanhoiTT35'/></textarea>
                            </td>
                        </tr>
                    </s:if>      
                </table>
                <s:if test="tonghop.equalsIgnoreCase('1') || Grade.equalsIgnoreCase('1')">
                   <hr/>                    
                   </br>
                <div align="right" id="link" >
                    <s:url id="idTranInputTT35" action="tranPhtsInputTT35.action"></s:url>                                      
                    <sj:submit id="idTranPhtsInputTT35" name="nameSend" href="%{idTranInputTT35}" value="Chuyển" targets="divExportReport"
                               onBeforeTopics="beforediv_send"
                               onCompleteTopics="completediv_send" cssStyle="display:none"/>
                    <input type="button" id="idTranPhtsInputTmp" name="nameidSendtmp"  onclick="onTranDataInputTT35()" value=" Chuyển "/>
                    &nbsp;&nbsp;                   
                </div>   
               </s:if>
                
        </s:form>  

    </body>
</html>
