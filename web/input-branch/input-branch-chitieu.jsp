<%-- 
    Document   : input-branch-chitieu
    Created on : Dec 13, 2018, 1:48:41 PM
    Author     : BAOANH
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    .class_hr{ 
        height: 2px;
        color: red;
        background-color: red;
        border: none;
    }
</style>
<table id="id_table_ct" border="0">
    <s:if test="lstDulieu.empty">
        <tr style="background: #E2E8C9" align="center">
            <td style="width: 20%">
                <label>Mã chỉ tiêu: </label>
                <input type="text" name="lstDulieu[0].MA" id="id_machitieu" size="15" placeholder="MACTxxxxx"/> &nbsp;&nbsp;
                <hr class="class_hr">
            </td>
            <td style="width: 45%">
                <label>Tên chỉ tiêu: </label>
                <input type="text" name="lstDulieu[0].TEN" id="id_tenchitieu" size="60" placeholder="Nhập tên mô tả chỉ tiêu"/> &nbsp;&nbsp;
                <hr class="class_hr">
            </td>
            <td style="width: 20%">
                <label>Loại dữ liệu: </label>
                <select name="lstDulieu[0].KIEUDULIEU" id="id_kieudulieu">
                    <option value="T">T -&gt; Kiểu text</option>
                    <option value="N">N -&gt; Kiểu số</option>
                    <option value="D">D -&gt; Ngày tháng năm</option>
                </select>
                <hr class="class_hr">
            </td>     
            <td style="width: 7%">
                <input type="button" value="Xóa chỉ tiêu" style="margin: 3px;" onclick="deleteChitieuTable(this.parentNode.parentNode.rowIndex)" class="metroButtonStyle"/>
                <hr class="class_hr">
            </td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="#attr.lstDulieu" var="modelView" status="rowstatus">
            <tr style="background: #E2E8C9" align="center">
                <td style="width: 20%">
                    <label>Mã chỉ tiêu: </label>
                    <input type="text" id="id_machitieu_<s:property  value="%{#rowstatus.index}" />"  value="<s:property  value="MA" />"
                           name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].MA"  size="15" placeholder="MACTxxxxx"/> &nbsp;&nbsp;
                    <hr class="class_hr">
                </td>
                <td style="width: 45%">
                    <label>Tên chỉ tiêu: </label>
                    <input type="text" name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].TEN" id="id_tenchitieu" value="<s:property  value="TEN" />"
                           size="60" placeholder="Nhập tên mô tả chỉ tiêu"/> &nbsp;&nbsp;
                    <hr class="class_hr">
                </td>
                <td style="width: 20%">
                    <label>Loại dữ liệu: </label>
                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].KIEUDULIEU" id="id_kieudulieu">
                        <option value="T" <s:if test="KIEUDULIEU.equalsIgnoreCase('T')"> selected </s:if>>T -&gt; Kiểu text</option>
                        <option value="N" <s:elseif test="KIEUDULIEU.equalsIgnoreCase('N')"> selected </s:elseif>>N -&gt; Kiểu số</option>
                        <option value="D" <s:elseif test="KIEUDULIEU.equalsIgnoreCase('D')"> selected </s:elseif>>D -&gt; Ngày tháng năm</option>                    
                    </select>
                    <hr class="class_hr">
                </td>     
                <td style="width: 7%">
                    <input type="button" value="Xóa chỉ tiêu" style="margin: 3px;" onclick="deleteChitieuTable(this.parentNode.parentNode.rowIndex)" class="metroButtonStyle"/>
                    <hr class="class_hr">
                </td>
            </tr> 
        </s:iterator>
    </s:else>
    <tr   style="background: #c5dbec">
        <td colspan="3" align="right">
            <input type="button" value="Thêm chỉ tiêu" onclick="addChitieuTable(this.parentNode.parentNode.rowIndex)" class="metroButtonStyle"/>
        </td>
        <td></td>
    </tr>
</table>