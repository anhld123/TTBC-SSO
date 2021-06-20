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
    #table_bangdl1
    {
        /*        top: 10px;
                left: 10px;
                bottom: 10px;*/
        width: 90%;    
        height: 150px;
        overflow: scroll;
        /*display: block;*/
        position: absolute;
    }
    .landing {
        height: 270px;
        margin: 5px 5px 0px 0px;
        width: 99%;
        position: absolute;
        background-color: #FBC2C4;
        overflow: scroll;
    }

    .landing-wrapper {
        height: 270px;
    }
    .article
    {
        height: 50px;
        margin: 15px 15px;
    }


</style>

<div class="landing-wrapper" align="center">
    <div class="landing" >
        <table id="bangtruyvan" border="1" style="width: 90%">
            <tr>
                <td style="width: 8%">
                    <label for="truyvan">Truy vấn:</label>
                </td>
                <td style="width: 80%">
                    <div class="form-group green-border-focus" align="center">                        
                        <textarea class="form-control" id="id_query" rows="5" ></textarea>
                    </div>
                </td>
                <td style="width: 5%">
                    <input type="button" value="Tải dữ liệu" style="margin: 3px;" onclick="" class="metroButtonStyle">
                </td>
            </tr>
        </table>


        <table id="id_table_query" border="1" class="id_table_query">
            <!--            <tr style="background: #E2E8C9; width: 90%" align="center">
                            <td >-->

            <!--                </td>
                            <td>-->

            <!--                </td>
                        </tr>  -->
            <s:if test="lstDulieu.empty">
                <!--                <tr style="background: #E2E8C9" align="center">
                                    <td >
                                        <label class="Classcotdulieu">Cột 1 </label><br>
                                        <textarea name="lstDulieu[0].TEN" id="id_tenchitieu" rows="4" cols="20" style="margin: 5px 5px;"><s:property  value="TEN" /></textarea>
                
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
                                </tr>-->
            </s:if>
            <s:else>
                <!--                <tr style="background: #E2E8C9" align="center">
                <s:iterator value="#attr.lstDulieu" var="modelView" status="rowstatus">
                    <%--<s:if test="%{#rowstatus.index == 0}"> <s:if test="%{#rowstatus.first == true}"> </s:if>--%>
                    <%--<s:elseif test="#rowstatus.last==true"> </s:elseif>--%>
                    <td >
                        <label class="Classcotdulieu">Cột <s:property  value="%{#rowstatus.count}" /> </label><br>
                        <textarea name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].TEN" id="id_tenchitieu" rows="4" cols="20" style="margin: 5px 5px;"><s:property  value="TEN"/></textarea>
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
            </tr>-->
            </s:else>
        </table>
    </div>
</div>