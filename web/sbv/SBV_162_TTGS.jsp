<%-- 
    Document   : SBV_162_TTGS
    Created on : Jun 7, 2016, 8:32:56 AM
    Author     : NguyetLM
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
         <%--<sj:head/>--%>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            $(document).ready(function () {
                $('input.number2').css({"text-align": "right"});
                $('input.number').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $('.number').number(true, 0);
            });
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <s:form id="id_sv_%{khoa_sbv}" action="SAVE_%{khoa_sbv}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:iterator value="poscd" status="row">
                <s:hidden name="poscd[%{#row.index}]" />
            </s:iterator>
            <div id="divTitle">
                BẢNG CÂN ĐỐI KẾ TOÁN (HỢP NHẤT, RIÊNG LẺ)
            </div>
            <s:hidden name="khoa_sbv"/>
            <div id="divDonvitinh">
                Đơn vị tính: Triệu VNĐ
            </div>
            <table border="1" class="editDelete" id="tablepl01" align="center">
                <tr>     
                    <th style="width: 20px;" class="TD_TEN_KH">Stt</th>
                    <th style="width: 20px;" class="TD_TEN_KH">Tên chỉ tiêu</th>
                    <th style="width: 20px;" class="TD_TEN_KH">Số cuối kỳ</th>
                    <th style="width: 20px;" class="TD_TEN_KH">Số đầu năm</th>
                </tr>

                <s:iterator value="lstDulieuNt" status="rowstatus">
                    <tr class="clss_lstdata">
                        <td align="center"><s:property  value="%{#rowstatus.index + 1}" /></td>
                    <input type="hidden" value="<s:property  value="MA" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                    <input type="hidden" value="<s:property  value="MAPGD" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>"/>        
                    <td><input type="text" value="<s:property  value="TEN" />" id="TEN" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly="readonly">
                    <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D1" />" id="D1" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/></td>
                    <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D2" />" id="D2" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2"/></td>
                </tr>
            </s:iterator>
        </table>
        <sj:submit id="%{khoa_sbv}_save" name="%{khoa_sbv}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                   onCompleteTopics="completediv_ss" cssStyle="display: none"/>
    </s:form>
    </body>
</html>
