<%-- 
    Document   : sbv_066_phkq_index
    Created on : Jun 7, 2016, 10:21:45 AM
    Author     : Administrator
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="js/new_js_bcqt.js"></script>
        <script>
            jQuery(function () {
                jQuery(document).trigger("enhance");
            });
        </script>              
        <style>
            .css_text_65{
                border: 0px;
                background-color: transparent;
                width: 100%;
            }
        </style>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_sbv}" action="SAVE_%{khoa_sbv}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" 
                       id="<s:property  value="sKey" />" name="1_<s:property  value="sKey" />" 
                       value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:hidden name="khoa_sbv"/>
            <h3 >BÁO CÁO THU, CHI CÁC LOẠI TIỀN THUỘC QUỸ NGHIỆP VỤ</h3>
            </br>
            <div style="font-style: italic; text-align: right;width: 95%;padding-bottom: 4px;">Đơn vị tính: Nghìn VND</div>
            
            <h4 align="left">I - Tiền mặt tại đơn vị:</h4>
            
            <table  id="SBV_065_PHKQ_TB1" 
                    name="SBV_065_PHKQ_TB1" 
                    class="SBV_066_PHKQ" 
                    cellspacing="0" 
                    cellspadding="0" border="1" 
                    style="border-collapse: collapse; width: 98%;">
                <tr>
                    <th rowspan="2">STT</th>
                    <th rowspan="2">Loại tiền</th>
                    <th rowspan="2">Tồn quỹ đầu kỳ</th>
                    <th colspan="5">Thu tiền mặt trong kỳ</th>
                    <th colspan="5">Chi tiền mặt trong kỳ</th>
                    <th rowspan="2">Tồn quỹ cuối kỳ</th>
                    <th colspan="2">Tỷ lệ thu chi</th>
                </tr>
                <tr align="center">			                    
                    <th>Thu từ NHNN</th>
                    <th>Thu từ TCTD khác</th>
                    <th>Thu từ khách hàng</th>
                    <th>Thu nội bộ TCTD</th>
                    <th>Cộng thu</th>

                    <th>Chi nộp NHNN</th>
                    <th>Chi cho TCTD khác</th>
                    <th>Chi cho khách hàng</th>
                    <th>Chi nội bộ TCTD</th>                    
                    <th>Cộng chi</th>

                    <th>Thu</th>
                    <th>Chi</th>                                        
                </tr>

                <!-- Thực hiện load dữ liệu tại đâu -->
                <s:iterator value="lstData" status="rowstatus">
                    <s:if test='%{MA.substring(4, 5) == "1"}'>
                    <tr <s:if test='%{KIEUIN == 1}'> style="background-color: silver;"</s:if>> 
                        <td><input type="text" value="<s:property  value="TT_HIENTHI" />" 
                               id="TT_HIENTHI" name="lstData[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"
                               readonly="readonly"
                               style="text-align:center; font-weight: bold;" class="css_text_65"/></td>
                        <td>
                            <input type="hidden" style="text-align: right; font-weight: bold;" class="css_text_65" 
                                   value="<s:property  value="MA" />" 
                                   id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA"  readonly="readonly"/>
                            <input type="text" style="text-align:left; font-weight: bold;" class="css_text_65" 
                                   value="<s:property  value="TEN" />" id="TEN" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN" 
                                   readonly="readonly"
                                   size="40"/>
                        </td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D1" />" id="D1" name="lstData[<s:property  value="%{#rowstatus.index}" />].D1" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D2" />" id="D2" name="lstData[<s:property  value="%{#rowstatus.index}" />].D2" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D3" />" id="D3" name="lstData[<s:property  value="%{#rowstatus.index}" />].D3" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D4" />" id="D4" name="lstData[<s:property  value="%{#rowstatus.index}" />].D4" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D5" />" id="D5" name="lstData[<s:property  value="%{#rowstatus.index}" />].D5" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D6" />" id="D6" name="lstData[<s:property  value="%{#rowstatus.index}" />].D6" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D7" />" id="D7" name="lstData[<s:property  value="%{#rowstatus.index}" />].D7" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D8" />" id="D8" name="lstData[<s:property  value="%{#rowstatus.index}" />].D8" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D9" />" id="D9" name="lstData[<s:property  value="%{#rowstatus.index}" />].D9" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D10" />" id="D10" name="lstData[<s:property  value="%{#rowstatus.index}" />].D10" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D11" />" id="D11" name="lstData[<s:property  value="%{#rowstatus.index}" />].D11" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D12" />" id="D12" name="lstData[<s:property  value="%{#rowstatus.index}" />].D12" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D13" />" id="D13" name="lstData[<s:property  value="%{#rowstatus.index}" />].D13" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D14" />" id="D14" name="lstData[<s:property  value="%{#rowstatus.index}" />].D14" /></td>
                        
                   </tr>
                   </s:if>
                </s:iterator>

            </table>
            
            <h4 align="left">II - Tiền không đủ tiêu chuẩn lưu thông:</h4>
            
            <table  id="SBV_065_PHKQ_TB2" 
                    name="SBV_065_PHKQ_TB2" 
                    class="SBV_066_PHKQ" 
                    cellspacing="0" 
                    cellspadding="0" border="1" 
                    style="border-collapse: collapse; width: 98%;">
                <tr>
                    <th rowspan="2">STT</th>
                    <th rowspan="2">Loại tiền</th>
                    <th rowspan="2">Tồn quỹ đầu kỳ</th>
                    <th colspan="2">Nhập trong kỳ</th>
                    <th colspan="2">Xuất trong kỳ</th>
                    <th rowspan="2">Tồn quỹ cuối kỳ</th>                    
                </tr>
                <tr align="center">			
                    
                    <th>Nhập từ lưu thông</th>
                    <th>Nhập nội bộ TCTD</th>                    

                    <th>Xuất nộp NHNN</th>                    
                    <th>Xuất nộp nội bộ TCTD</th>                    
                    
                </tr>

                <!-- Thực hiện load dữ liệu tại đâu -->
                <s:iterator value="lstData" status="rowstatus">
                    <s:if test='%{MA.substring(4, 5) == "2"}'>
                    <tr <s:if test='%{KIEUIN == 1}'> style="background-color: silver;"</s:if>> 
                        <td><input type="text" value="<s:property  value="TT_HIENTHI" />" 
                               id="TT_HIENTHI" name="lstData[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"
                               readonly="readonly"
                               style="text-align:center; font-weight: bold;" class="css_text_65"/></td>
                        <td>
                            <input type="hidden" style="text-align: right; font-weight: bold;" class="css_text_65" 
                                   value="<s:property  value="MA" />" 
                                   id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA"  readonly="readonly"/>
                            <input type="text" style="text-align:left; font-weight: bold;" class="css_text_65" 
                                   value="<s:property  value="TEN" />" id="TEN" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN" 
                                   readonly="readonly"
                                   size="40"/>
                        </td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D1" />" id="D1" name="lstData[<s:property  value="%{#rowstatus.index}" />].D1" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D2" />" id="D2" name="lstData[<s:property  value="%{#rowstatus.index}" />].D2" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D3" />" id="D3" name="lstData[<s:property  value="%{#rowstatus.index}" />].D3" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D4" />" id="D4" name="lstData[<s:property  value="%{#rowstatus.index}" />].D4" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D5" />" id="D5" name="lstData[<s:property  value="%{#rowstatus.index}" />].D5" /></td>
                        <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text_65 number" value="<s:property  value="D6" />" id="D6" name="lstData[<s:property  value="%{#rowstatus.index}" />].D6" /></td>                        
                   </tr>
                   </s:if>
                </s:iterator>

            </table>
            
            <sj:submit id="%{khoa_sbv}_save" name="%{khoa_sbv}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
    </body>
</html>	