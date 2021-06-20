<%-- 
    Document   : SBV_089_SGD_INDEX
    Created on : May 29, 2016, 12:55:57 PM
    Author     : BAOANH
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SBV_089_SGD</title>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="js/new_js_bcqt.js"></script>
        <script>
            autoSum('');
            function autoSum(ctrname,index){
                if(index !== ''){
                    var Total = Number($("[id=D1]").eq(index).val());
                    var ChkSaum = Number($(ctrname).eq(index).val()) + Number($(ctrname).eq(index).val());
//                    if (Total < ChkSaum)
//                    {
//                        alert("Hạn mức phân bổ <= Tổng hạn mức phân bổ.");
//                        $(ctrname).eq(index).val(0);
//                        $(ctrname).eq(index).focus();
//                    }
                }
                //var Sum1=0;
                var Sum2=0;
                var Sum3=0;
                for (m = 0; m < $("[id=D2]").length; m++) {
                    //Sum1 = Sum1 + Number($("[id=D1]").eq(m).val());
                    Sum2 = Sum2 + Number($("[id=D2]").eq(m).val());
                    Sum3 = Sum3 + Number($("[id=D3]").eq(m).val());
                }
                //$("#sum1").val(Sum1);
                $("#sum2").val(Sum2);
                $("#sum3").val(Sum3);
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
        <h3>BÁO CÁO TỔNG HỢP PHÂN BỔ HẠN MỨC CHO VAY,GỬI TIỀN TRÊN THỊ TRƯỜNG LIÊN NGÂN HÀNG</h3>
        <br>
        <div style="font-style: italic; text-align: right;width: 95%;padding-bottom: 4px;">Đơn vị: Triệu đồng</div>
        <table name="SBV_089_SGD" class="SBV_089_SGD" cellspacing="0" cellspadding="0" border="1" style="border-collapse: collapse; width: 95%;">
            <tr style="background-color: #e7e7e7;">
                <th rowspan="2">STT</th>
                <th colspan="2">TCTD đối tác được phân bổ hạn mức</th>
                <!--<th rowspan="2">Tổng hạn mức phân bổ</th>-->
                <th colspan="2">Hạn mức phân bổ</th>
            </tr>
            <tr align="center" style="background-color: #e7e7e7;">
                <td>Mã TCTD</td>
                <td>Tên TCTD đối tác</td>
                <td>Có bảo đảm bằng tài sản</td>
                <td>Không có bảo đảm bằng tài sản</td>
            </tr>
            <tr align="center" class="clss_ttcot" style="background-color: #e7e7e7;">
                <td width="5%">(1)</td>
                <td width="5%">(2)</td>
                <td width="40%">(3)</td>
                <!--<td width="15%">(4)</td>-->
                <td width="15%">(5)</td>
                <td width="15%">(6)</td>
            </tr>
            <s:iterator value="lstData" status="rowstatus">
                <tr class="clss_lstdata">
                    <td align="center"><s:property  value="%{#rowstatus.index + 1}" /></td>
                    <td><input type="text" style="text-align: center;" value="<s:property  value="MA" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA" readonly="readonly">
                    <td><input type="text" value="<s:property  value="TEN" />" id="TEN" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN" readonly="readonly">
                    <!--<td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D1" />" id="D1" name="lstData[<s:property  value="%{#rowstatus.index}" />].D1" readonly="readonly"/></td>-->
                    <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D2" />" id="D2" name="lstData[<s:property  value="%{#rowstatus.index}" />].D2" onblur="autoSum('[id=D2]',<s:property  value="%{#rowstatus.index}" />);">
                    <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D3" />" id="D3" name="lstData[<s:property  value="%{#rowstatus.index}" />].D3" onblur="autoSum('[id=D3]',<s:property  value="%{#rowstatus.index}" />);">
                </tr>
            </s:iterator>
                <tr class="clss_tongcong">
                    <td colspan="3" align="center">Tổng cộng:</td>
                    <!--<td><input type="text" style="text-align: right; background-color: transparent;border: 0px; width: 100%; font-weight: bold;" class="css_text number" value="" id="sum1" readonly="readonly"/></td>-->
                    <td><input type="text" style="text-align: right; background-color: transparent;border: 0px; width: 100%; font-weight: bold;" class="css_text number" value="" id="sum2" readonly="readonly"/></td>
                    <td><input type="text" style="text-align: right; background-color: transparent;border: 0px; width: 100%; font-weight: bold;" class="css_text number" value="" id="sum3" readonly="readonly"/></td>
                </tr>
        </table>
        <sj:submit id="%{khoa_sbv}_save" name="%{khoa_sbv}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
    </body>
</html>
