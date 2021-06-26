<%-- 
    Document   : ViewAuthorDetail
    Created on : Jun 18, 2021, 9:44:51 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<table>
    <thead>
        <tr>
            <td colspan="3" style="text-align: left; border: 0px; font-weight: bold; background-color: orange ;"><span id="strHeader" style="text-transform: uppercase; color: white;"></span></td>
            <td colspan="4" style="text-align: right; border: 0px;font-style: italic;background-color: orange; color: white;">Đơn vị: triệu đồng, %, hộ, người</td>
        </tr>
        <tr>
            <th rowspan="3">STT</th>
            <th rowspan="3">CHỈ TIÊU</th>
            <th rowspan="3">Thực hiện đến <span id="lbNamTH"></span></th>
            <th rowspan="3">Ước thực hiện đến 31/12/<span id="lbNamUoc"></span></th>
            <th colspan="3">Kế hoạch tín dụng năm <span id="lbNamTD"></span></th>
        </tr>
        <tr>
            <th rowspan="2">Tổng số</th>
            <th colspan="2">Tăng, giảm so với 31/12/<span id="lbTangGiam"></span></th>
        </tr>
        <tr>
            <th>Số tuyệt đối (+/-)</th>
            <th>Số tương đối (%)</th>
        </tr>
    </thead>
    <tbody>
        <s:iterator value="lstData" status="idxRows">
            <tr class="<s:property value='D50'/>">
                <td><s:property value='TT_HIENTHI'/></td>
                <td style="text-align: left; padding-left: 3px;"><s:property value='TEN'/></td>
                <td style="text-align: right; padding-right: 3px;" class="cls<s:property value='D48'/>"><input type="text" class="item" id="lstData[<s:property  value='%{#idxRows.index}' />].D13" name="lstData[<s:property  value='%{#idxRows.index}' />].D13" value="<s:property value='D13'/>" <s:property value='D48'/>/></td>
                <td style="text-align: right; padding-right: 3px;" class="cls<s:property value='D48'/>"><input type="text" class="item" id="lstData[<s:property  value='%{#idxRows.index}' />].D14" name="lstData[<s:property  value='%{#idxRows.index}' />].D14" value="<s:property value='D14'/>" <s:property value='D48'/>/></td>
                <td style="text-align: right; padding-right: 3px;" class="cls<s:property value='D48'/>"><input type="text" class="item" id="lstData[<s:property  value='%{#idxRows.index}' />].D15" name="lstData[<s:property  value='%{#idxRows.index}' />].D15" value="<s:property value='D15'/>" <s:property value='D48'/>/></td>
                <td style="text-align: right; padding-right: 3px;" class="cls<s:property value='D48'/>"><input type="text" class="item" id="lstData[<s:property  value='%{#idxRows.index}' />].D16" name="lstData[<s:property  value='%{#idxRows.index}' />].D16" value="<s:property value='D16'/>" <s:property value='D48'/>/></td>
                <td style="text-align: right; padding-right: 3px;" class="cls<s:property value='D48'/>"><input type="text" class="item" id="lstData[<s:property  value='%{#idxRows.index}' />].D17" name="lstData[<s:property  value='%{#idxRows.index}' />].D17" value="<s:property value='D17'/>" <s:property value='D48'/>/></td>
                <!--Những trường dữ liệu cần lấy-->
                <td style="display: none;"><input type="text" class="item" id="lstData[<s:property  value='%{#idxRows.index}' />].TT_HIENTHI" name="lstData[<s:property  value='%{#idxRows.index}' />].TT_HIENTHI" value="<s:property value='TT_HIENTHI'/>" readonly/></td>
                <td style="display: none;"><input type="text" id="lstData[<s:property  value="%{#idxRows.index}" />].MA" name="lstData[<s:property  value="%{#idxRows.index}" />].MA" value="<s:property value='MA'/>" name="MA" readonly="readonly"/></td>
                <td style="display: none;"><span id="ngaybc"><s:property value='D49'/></span></td>
                <td style="display: none;"><input type="text" class="item" id="lstData[<s:property  value='%{#idxRows.index}' />].TEN" name="lstData[<s:property  value='%{#idxRows.index}' />].TEN" value="<s:property value='TEN'/>" readonly/></td>
                <td style="display: none;"><input type="text" class="item" id="lstData[<s:property  value='%{#idxRows.index}' />].THUTU" name="lstData[<s:property  value='%{#idxRows.index}' />].THUTU" value="<s:property value='THUTU'/>" readonly/></td>
            </tr>
        </s:iterator>

    </tbody>
</table>
<script>
    $(document).ready(function () {
        var varDonvi = "";
        varDonvi = $("#cboDonvi").val();
        if (varDonvi.trim() === "all") {
            varDonvi = $("#cboTonghop option:selected").text();
        } else {
            varDonvi = $("#cboDonvi option:selected").text();
        }
        var strText = "KẾ HOẠCH TÍN DỤNG " + $("#cboNam option:selected").text() + " - " + $("#cboDot option:selected").text() + " - Đơn vị: " + varDonvi;
        $("#strHeader").html(strText);
        //Xử lý phần tiêu đề
         $("#lbNamTH").html($("#ngaybc").text());
        $("#lbNamUoc").html($("#cboNam option:selected").val());
        $("#lbNamTD").html(parseInt($("#cboNam option:selected").val()) + 1);
        $("#lbTangGiam").html($("#cboNam option:selected").val());
    });
</script>