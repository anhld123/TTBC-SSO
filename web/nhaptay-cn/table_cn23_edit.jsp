<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<s:head/>
<sj:head/>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<script src="js/jquery.number.js"></script>
<!DOCTYPE html>
<html>
    <head>
        <style>
    .readonly {
        background: #FFFFC0;        
    }
    .pos_edit_form {
        padding:0px;
        width:30%;    
        background:#f9f9f9;
        border:1px solid #ccc;
        text-align:left;   
        font-family: Arial;
        font-size: 12pt;
    }    

    .metroButtonStyle {
        font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
        display: block;
        color: rgb(255, 255, 255);
        text-decoration: none;
        text-align: center;
        width: 90px;
        height: 20px;
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
    .metroButtonStyle:disabled {
        background: #DCDCDC;
    }
    
    #divTitle{
    font: 14px Arial, Helvetica, sans-serif;
    font-weight: bold;
    color: #0077b3;
    text-align: center;
}
#idTitle{
                font-family: Cambria,Verdana,Arial,Tahoma,Helvetica;
                font-size: 11pt;
                font-weight: bold;
                color: blue;
            }
</style>
        
<script>
            var max_row = 0;
            $(document).ready(function () {
                 $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $('.D0').css({"text-align": "center"});
                $(".TD_TEN_KH").css({"width": "280px"});
                $(".TD_CHUCVU").css({"width": "40px"});
                $(".TD_CMND").css({"width": "80px"});
                $(".TD_MAIL").css({"width": "110px"});
                $(".TD_M").css({"width": "140px"});
                $(".TD_TVTT").css({"width": "1110px"});
                $(".TD_THEMXOA").css({"width": "10px"});
                $(".TEN_KH").css({"width": "100%"});
            });

            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            
            
        </script>
        
        <script>
            window.onunload = function (e) {
                opener.refreshData();
            };
        </script>
        
        <script>
            function closeSelf() {
                window.close();
                return true;
            }
        </script>
        
    </head>
    <body>
        <!--<div id="container_popup">-->
            <s:form name="saveEditCN23" id="saveEditCN23"  theme="simple">              
                <div id="divChiTieu" style="text-align: center;">   
                    <s:hidden name="khoa_ktgs"/>
                    <s:hidden name="ngay_bc"/>                    
                    
                   <span id="idTitle" >CẬP NHẬT THÔNG TIN HỖ TRỢ LÃI SUẤT</span>
                    
                    
                    <hr/>
                    <s:iterator value="#attr.lstDulieuNt" var="modelDcpt" status="rowstatus">                        
                                   
                        <table border="1" class="editDelete" id="tableCN2301" style="width: 95%"  align="center">
                    <tr height="50px">                              
                        <th  class="TD_MAKH">Mã khách hàng</th>    
                        <th  class="TD_TENTS">Tên KH</th>  
                        <th  class="TD_MAKH">Mã món vay</th>   
                        <th  class="TD_MAKH">Lãi suất cho vay</th> 
                        <th  class="TD_MAKH">Lãi suất hỗ trợ (x100)</th> 
                        <th  class="TD_MAKH">Ngày hết hạn  (MM/DD/YYYY)</th>
                       
                    </tr>                                    
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>      
                                </td>  

                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D6" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH D0" readonly="true"/>
                                </td> 
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D7" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH" readonly="true"/>
                                </td>
                                   
                                 <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D2" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" readonly="true"/>
                                </td>  
                                
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D8" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH D0" readonly="true"/>
                                </td> 
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D14" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="TEN_KH number"/>
                                </td> 
                                
                              
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D4" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0"/>
                                </td> 
                                    
                            </tr>                                                                                                                                                                                   
                    </s:iterator>
                </table>                               
                            <table  border="0" class="editDelete" align="center"> 
                                <tr align="center" height="3px">                                    
                                </tr>
                                <tr align="center">                                       
                                
                                    <td width="35%">
                                        <div id="content_div"></div>
                                    </td>   
                                                                     
                                        <td  align="center">
                                        <div id="button_div" <s:property value="disabled" /> >
                                            <s:url id="edit_url" action="saveEditCN23" escapeAmp="false"
                                                   var="update_url">
                                                <s:param name="proc">update</s:param>  
                                            </s:url>                        
                                            <sj:a id="update_button_id"  href="%{#update_url}" 
                                                  targets="content_div"
                                                  formIds="saveEditCN23"    
                                                  onBeforeTopics="before-next"
                                                  button="false"
                                                  cssClass="metroButtonStyle"   
                                                  >     
                                                Cập nhật
                                            </sj:a>
                                        </div>
                                    </td>                                     
                                    
                                
                                <td align="center">
                                        <sj:submit id="idClose" cssClass="metroButtonStyle"  name="nameClose" value="Thoát" onclick="closeSelf()"
                                                   cssStyle="height:31px;width:95px"></sj:submit>
                                </td>
                                
                                <td width="35%"></td>                                    
                                
                                
                            </tr>
                            </table>              
                        
                    </s:iterator>                                        
                </div>

            </s:form>
        <!--</div>-->
    </body>
</html>
