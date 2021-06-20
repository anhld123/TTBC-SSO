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
            
            function onTranDataInput()
            {   
                var sContentInput = $.trim($("#sContentInput").val()).length;                
                if (sContentInput <1)
                {
//                    alert('Bạn đã nhập dữ liệu nguyên nhân chênh lệch nên không thể nhập dữ liệu cho trường này');
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải nhập phản hồi trước khi chuyển tiếp! </h2>");                    
                    return;
                }
                //$('#message_suc_err').empty();
                $('#divExportReport').empty();                
                $("#idTranPhtsInput")[0].click(); 
//                setTimeout(window.location.reload, 5000);
                setTimeout(location.reload.bind(location), 5000);
            }
            
            function sleep(delay) {
                alert('refresh');
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
        <div id="divTitlePhts">&nbsp;&nbsp; <s:property  value="macn" /> - Phản hồi khác</div>
        <hr/>            
        
        <s:form id="nhapthucong_phts" action="nhapthucong_report_phts" theme="simple">
            <!--<div ><s:property  value="ngay_bc" /></div>-->
            <input type="hidden" value="<s:property  value="ngay_bc" />"  name="ngay_bc" />
            <input type="hidden" value="<s:property  value="macn" />"  name="macn" />
            <div  id="divExportReport"></div>
            <s:if test="!tonghop.equalsIgnoreCase('1') && Grade.equalsIgnoreCase('2')">
                
            </s:if>
            <s:else>
                <table class="editDelete" style="width: 96%" id="tablepl01" align="center">
                    <tr>
                                <td >
                                    <s:if test="Grade.equalsIgnoreCase('1')">
                                        <span id="idTitle">Nội dung PGD gửi chi nhánh</span>
                                    </s:if>  
                                    <s:elseif test="Grade.equalsIgnoreCase('2')">
                                        <span id="idTitle">Nội dung CN gửi TW</span>
                                    </s:elseif>    
                                    <s:else>
                                        <span id="idTitle">Nội dung TW gửi CN</span>
                                    </s:else>    
                                </td>
                            </tr>

                            <tr >
                                <td  colspan="2" align="center">
                                    <textarea id="sContentInput"
                                              name="cnContent"
                                              style="width: 100%;background-color: #DDFFDD;" 
                                              rows="6"></textarea>
                                </td>
                            </tr>
                </table>
                &nbsp;
                <div align="right" id="link" >
                    <s:url id="idTranInput" action="tranPhtsInput.action"></s:url>                                      
                    <sj:submit id="idTranPhtsInput" name="nameSend" href="%{idTranInput}" value="Chuyển" targets="divExportReport"
                               onBeforeTopics="beforediv_send"
                               onCompleteTopics="completediv_send" cssStyle="display:none"/>
                    <input type="button" id="idTranPhtsInputTmp" name="nameidSendtmp"  onclick="onTranDataInput()" value=" Chuyển "/>
                    &nbsp;&nbsp;                   
                </div>
                     <hr/> 
            </s:else>
                <h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Lịch sử</h4>                    
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div  id="divExportReport"></div>    
            <table border="1" class="editDelete" style="width: 96%" id="tablepl01" align="center">
                        <tr>
                            <th style="width: 40px;">STT</th>
                            <th style="width: 2000px;">Nội dung</th>
                            <th style="width: 150px;">Nơi gửi</th>
                            <th style="width: 150px;">Nơi nhận</th>
                            <th style="width: 150px;">Ngày gửi</th>  
                            <th style="width: 200px;">Trạng thái</th>  
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                           
                                <tr style="text-align: center; color: #0000FF; font-weight: bold;" onmouseover="mover(this);"  onmouseout="mout(this);">
                                    
                                    <td align = "center" style="width: 60px;"><s:property  value="THUTU" /></td>  
                                    <td align = "left" style="width: 60px;"><s:property  value="D7" /></td>  
                                    <td align = "center" style="width: 60px;"><s:property  value="MAPGD" /></td>                                      
                                    <td align = "center" style="width: 60px;"><s:property  value="D9" /></td>   
                                    <td align = "center" style="width: 60px;"><s:property  value="NGAY_NHAP" /></td> 
                                    <td align = "center" style="width: 60px;"><s:property  value="D8" /></td>   
                                </tr>                            
                        </s:iterator>
                        
                    </table>  
                
            <p></p>  
        </s:form>  

    </body>
</html>
