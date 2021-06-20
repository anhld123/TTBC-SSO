<%-- 
    Document   : input-branch-bang
    Created on : Dec 13, 2018, 1:48:53 PM
    Author     : BAOANH
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script language="JavaScript" type="text/javascript">
//    function appendColumn() {
//        var tbl = document.getElementById('my-table');
//        for (var i = 0; i < tbl.rows.length; i++)
//            createCell(tbl.rows[i].insertCell(tbl.rows[i].cells.length), i, 'col');
//    }
//    function appendRow() {
//        var tbl = document.getElementById('my-table');
//        var row = tbl.insertRow(tbl.rows.length);
//        for (var i = 0; i < tbl.rows[0].cells.length; i++)
//            createCell(row.insertCell(i), i, 'row');
//    }
//    function createCell(cell, text, style) {
//        var div = document.createElement('div');
//        var txt = document.createTextNode(text);
//        div.appendChild(txt);
//        div.setAttribute('class', style);
//        div.setAttribute('className', style);
//        cell.appendChild(div);
//    }
//    function deleteRows() {
//        var tbl = document.getElementById('my-table');
//        var lastRow = tbl.rows.length - 1;
//        for (var i = lastRow; i > 0; i--)
//            tbl.deleteRow(i);
//    }
//    function deleteColumns() {
//        var tbl = document.getElementById('my-table');
//        var lastCol = tbl.rows[0].cells.length - 1;
//        for (var i = 0; i < tbl.rows.length; i++)
//            for (var j = lastCol; j > 0; j--)
//                tbl.rows[i].deleteCell(j);
//    }

</script>

<style>
    .landing {
        height: 220px;
        margin: 5px 5px 0px 0px;
        width: 99%;
        position: absolute;
        background-color: #FBC2C4;
        overflow: scroll;
    }

    .landing-wrapper {
        height: 220px;
    }
    .article
    {
        height: 50px;
        margin: 15px 15px;
    }


</style>

<div class="landing-wrapper" align="center">
    <div class="landing" >
        <table id="id_table_bang" border="1" class="id_table_bang">
            <s:if test="lstDulieu.empty">
                <tr style="background: #E2E8C9" align="center">
                    <td >
                        <label class="Classcotdulieu">Cột 1 </label><br>
                        <textarea name="lstDulieu[0].TEN" id="id_tenchitieu" rows="4" cols="10" style="margin: 2px 2px; height: 60px; width: 150px;"
                                  placeholder="Nhập tên cột dữ liệu"><s:property  value="TEN" /></textarea>

                    </td>
                    <td rowspan="3">
                        <input type="button" value="Thêm cột dữ liệu" onclick="addColumn()" class="metroButtonStyle">
                    </td>
                </tr>          
                <tr style="background: #E2E8C9" align="center">
                    <td>
                        <label>Loại dữ liệu: </label><br>
                        <select name="lstDulieu[0].KIEUDULIEU" id="id_kieudulieu">
                            <option value="T">T -&gt; Kiểu text</option>
                            <option value="N">N -&gt; Kiểu số</option>
                            <option value="D">D -&gt; Ngày tháng năm</option>
                        </select>
                    </td>
                    
                </tr>

                <tr style="background: #E2E8C9" align="center">
                    <td align="center">
                        <input type="button" value="Xóa cột dữ liệu" style="margin: 3px;" onclick="deleteCColumnTable(this.parentNode)" class="metroButtonStyle">
                    </td>
                </tr>
            </s:if>
            <s:else>
                <tr style="background: #E2E8C9" align="center">
                    <s:iterator value="#attr.lstDulieu" var="modelView" status="rowstatus">
                        <%--<s:if test="%{#rowstatus.index == 0}"> <s:if test="%{#rowstatus.first == true}"> </s:if>--%>
                        <%--<s:elseif test="#rowstatus.last==true"> </s:elseif>--%>
                        <td >
                            <label class="Classcotdulieu">Cột <s:property  value="%{#rowstatus.count}" /> </label><br>
                            <textarea name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].TEN" id="id_tenchitieu" rows="4" cols="20" style="margin: 2px 2px; height: 60px; width: 150px;"><s:property  value="TEN"/></textarea>
                        </td>
                    </s:iterator>
                </tr>
                <tr style="background: #E2E8C9" align="center">
                    <s:iterator value="#attr.lstDulieu" var="modelView" status="rowstatus">
                        <td>
                        <label>Loại dữ liệu: </label>
                            <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].KIEUDULIEU" id="id_kieudulieu">
                                <option value="T" <s:if test="KIEUDULIEU.equalsIgnoreCase('T')"> selected </s:if> >T -&gt; Kiểu text</option>
                                <option value="N" <s:elseif test="KIEUDULIEU.equalsIgnoreCase('N')"> selected </s:elseif>>N -&gt; Kiểu số</option>
                                <option value="D" <s:elseif test="KIEUDULIEU.equalsIgnoreCase('D')"> selected </s:elseif>>D -&gt; Ngày tháng năm</option>                    
                            </select>
                        </td>                            
                    </s:iterator>
                </tr>
                <tr style="background: #E2E8C9" align="center">
                    <s:iterator value="#attr.lstDulieu" var="modelView" status="rowstatus">
                        <td align="center">
                            <input type="button" value="Xóa cột dữ liệu" style="margin: 3px;" onclick="deleteCColumnTable(this.parentNode)" class="metroButtonStyle">
                        </td>                            
                    </s:iterator>
                </tr>
            </s:else>
<!--                <tr>
                    <td>
                        <input type="button" value="Thêm cột dữ liệu" onclick="addColumn()" class="metroButtonStyle">
                    </td>
                </tr>-->
        </table>
    </div>
</div>

<div class="article">
    <input type="button" value="Thêm cột dữ liệu" onclick="addColumn()" class="metroButtonStyle">
</div>