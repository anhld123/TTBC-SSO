<%-- 
    Document   : SBV_016N_TTGS
    Created on : OCT 15, 2016, 10:00:00 AM
    Author     : CHUDV
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SBV_016N_TTGS</title>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="js/new_js_bcqt.js"></script>
        <script>
            autoSum('');
            function autoSum(ctrname,index){
                var Sum2=0;
                for (m = 0; m < $("[id=D5]").length; m++) {
                    Sum2 = Sum2 + Number($("[id=D5]").eq(m).val());
                }
                $("#sum2").val(Sum2);
            }
        </script>
        <style>
            .clss_ttcot{
                font-style:italic;
                text-align: center;
            }
            .clss_lstdata input{
                width: 100%;
                border: 0px;
            }
            .clss_tongcong{
                font-weight: bold;
                background-color:silver;
            }
        </style>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_sbv}" action="SAVE_%{khoa_sbv}" theme="simple">
               <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                                   name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:hidden name="khoa_sbv"/>
        <h3>BÁO CÁO DUY TRÌ SỐ DƯ TIỀN GỬI CỦA TCTD NHÀ NƯỚC TẠI NHCSXH</h3>
        <br>
        <div style="font-style: italic; text-align: right;width: 95%;padding-bottom: 4px;">Đơn vị: Triệu đồng</div>
        <table name="SBV_016N_TTGS" class="SBV_016N_TTGS" cellspacing="0" cellspadding="0" border="1" style="border-collapse: collapse; width: 95%;">
            <tr style="background-color: #e7e7e7;">
                <th rowspan="2">STT</th>
                <th colspan="2">Tổ chức tín dụng</th>
                <th rowspan="2">Số dư tiền gửi phải duy trì trong năm</th>
            </tr>
            <tr align="center" style="background-color: #e7e7e7;">
                <th>Mã TCTD</th>
                <th>Tên TCTD</th>
            </tr>
            <tr align="center" class="clss_ttcot" style="background-color: #e7e7e7;">
                <td width="5%">(1)</td>
                <td width="5%">(2)</td>
                <td width="60%">(3)</td>
                <td width="30%">(4)</td>
            </tr>
            <s:iterator value="lstData" status="rowstatus">
                <tr class="clss_lstdata">
                    <td align="center"><s:property  value="%{#rowstatus.index + 1}" /></td>
                    <td><input type="text" style="text-align: center;" value="<s:property  value="MA" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA" readonly="readonly">
                    <td><input type="text" value="<s:property  value="TEN" />" id="TEN" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN" readonly="readonly">
                    <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D5" />" id="D5" name="lstData[<s:property  value="%{#rowstatus.index}" />].D5" onblur="autoSum('[id=D5]',<s:property  value="%{#rowstatus.index}" />);">
                </tr>
            </s:iterator>
                <tr class="clss_tongcong">
                    <td colspan="3" align="center">Tổng cộng:</td>
                    <td><input type="text" style="text-align: right; background-color: transparent;border: 0px; width: 100%; font-weight: bold;" class="css_text number" value="" id="sum2" readonly="readonly"/></td>
                </tr>
        </table>
        <sj:submit id="%{khoa_sbv}_save" name="%{khoa_sbv}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
    </body>
</html>
