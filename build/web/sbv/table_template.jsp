<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>

    </head>
    <body>
        <s:form id="id_sv_%{khoa_sbv}" action="SAVE_%{khoa_sbv}" theme="simple">
               <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                                   name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                Tiêu đề báo cáo
            </div>
            <s:hidden name="khoa_sbv"/>
            <div id="divDonvitinh">
                Đơn vị tính: Triệu đồng
            </div>
            <table border="1" class="editDelete" id="tablepl01" align="center">
                <tr>
                    <th rowspan="2" style="width: 30px;" class="TD_TEN_KH">Tháng</th>
                    <th colspan="6">Lương A (100%)</th>
                    <th colspan="3">Lương B (100%)</th>
                    <th rowspan="2" style="width: 30px;" class="TD_TEN_KH">Tổng quỹ tiền lương V1 100%</th>
                    <th rowspan="2" style="width: 30px;" class="TD_TEN_KH">Tổng quỹ tiền lương V1 thực tế đã chi</th>
                    <th rowspan="2" style="width: 30px;" class="TD_TEN_KH">Chênh lệch</th>
                    <th rowspan="2" style="width: 30px;" class="TD_TEN_KH">Thêm/Xóa</th>
                </tr>
                <tr>                   
                    <th style="width: 20px;" class="TD_TEN_KH">Hệ số lương cấp bậc</th>
                    <th style="width: 20px;" class="TD_TEN_KH">Hệ số phụ cấp các loại</th>
                    <th style="width: 20px;" class="TD_TEN_KH">Tổng HSL+PC</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Mức lương tối thiểu vùng</th>
                    <th style="width: 30px;" class="TD_TEN_KH">Hệ số K điều chỉnh</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Tổng quỹ lương A</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Số lao động</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Mức lương</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Tổng quỹ lương B</th>
                </tr>
                <tr>         
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(1)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(2)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(3)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(4)=(2)+(3)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(5)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(6)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(7)=(4)x(5)x(6)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(8)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(9)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(10)=(8)x(9)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(11)=(7)+(10)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(12)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(13)=(12)-(11)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH"></th>
                </tr>
            </table>
            <sj:submit id="%{khoa_sbv}_save" name="%{khoa_sbv}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
    </body>
</html>
