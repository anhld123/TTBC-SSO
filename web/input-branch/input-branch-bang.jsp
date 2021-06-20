<%-- 
    Document   : input-branch-bang
    Created on : Dec 13, 2018, 1:48:53 PM
    Author     : BAOANH
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script type="text/javascript" src="js/sweetalert.min.js"></script>
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
<script type="text/javascript">
    function calldilog(index)
    {
        $("#remoteformdialog").dialog({
            open: function (event, ui) {
                // setting additional parameters to dialog
                var khoa = $('#id_khoamau').val();
                
                var querystring=$('#PARAMETER_LIST_'+index).val();
                
                //console.log(querystring);
                //console.log(encodeURIComponent(querystring));
//                alert(khoa);
                var dialogUrl = "showdialog.action?khoa="+khoa+"&index=" + index+"&queryString="+encodeURIComponent(querystring);
                 //console.log(dialogUrl);
//            if (lcDialog == "country")
//                dialogUrl += "?cod_zone=" + $("#input_zone_cod_selected").val();
//            if (dialog == "city")
//                dialogUrl += "?cod_country=" + $("#input_country_cod_selected").val();
                $("#remoteformdialog").load(dialogUrl);
            }
        });
        $("#remoteformdialog").dialog('open');

    }
    //cho select option khi select kieu list se hien thi button
    function showButtonList(index)
    {
//        'id_showparameter_','id_kieudulieu_0'
        var kieudulieu = $("#id_kieudulieu_" + index).val();
        if (kieudulieu == 'L')
        {
            $("#id_showparameter_" + index).show();
            calldilog(index);
        } else
        {
            $("#id_showparameter_" + index).hide();
        }

    }
</script>
<link  rel="stylesheet" type="text/css" href="input-branch/css/inputbranch.css"/>

<style>
    .landing {
        height: 350px;
        /*margin: 5px 5px 10px 10px;*/
        width: 99%;
        position: absolute;
        background-color: #FBC2C4;
        /*        overflow: scroll;*/
        overflow: auto;
        display: flex;
    }

    .landing-wrapper {
        height: 220px;
    }
    .article
    {
        height: 50px;
        margin: 15px 15px;
    }
    .chitieu {
        float: right;
        /*        float: left;
                width: 50%;
                height: 100%;*/
        /*background-color: brown;*/
        flex: 30%;
        /*padding: 10px;*/
        height: 300px;
        /*align-items: flex-end;*/
    }

    .cotdulieu {
        float: left;
        /*        float: right;
                width: 47%;
                height: 100%;*/
        /*background-color: #1faadb;*/
        flex: 70%;
        /*padding: 10px;*/
        height: 300px;
        /*align-items: flex-start;*/
    }

    table.id_table_bang {
        border-collapse: collapse;
        /*        border: 1px solid #00f;
                background-color: #EEEEEE;*/
    }
    table.id_table_bang td, table.id_table_bang th {
        border: 1px solid #ab59a6;
        /*color: #e17009;eea236*/
    }
</style>
<!--<input type="button" value="Show dialog" style="margin: 0px;" onclick="calldilog('qbcc');" class="metroButtonStyle">-->
<s:url var="form1_url" action="showdialog"/>
<sj:dialog 
    id="remoteformdialog" 
    autoOpen="false"
    href="%{form1_url}" 
    modal="true"
    width="500"
    height="400" 
    title="Cấu hình tham số dạng danh mục cho cột dữ liệu">
    <input type="hidden" name="giatri_value" value="1" id="index_value">
     <img id="indicator" src="img/loading.gif" alt="Đang tải dữ liệu..."/>
</sj:dialog>

<div class="landing-wrapper" align="center">
    <div class="landing" id="landing_id">
        <!--        Cho phần chỉ tiêu-->
        <div class="chitieu" align="right">
            <table id="id_table_ct" border="1" class="id_table_bang">
                <tr style="margin: 2px 2px; height: 80px; width: 150px; background: #E2E8C9;">
                    <td align="center">
                        <label>Mã chỉ tiêu</label></td>
                    <td align="center"> <label>Tên chỉ tiêu</label></td>
                    <td align="center"> <label>Thêm/Xóa</label></td>
                </tr>
                <s:if test="lstDulieuChitieu.empty">
                    <tr style="background: #E2E8C9; height: 30px;" align="center">
                        <td>
                            <input type="text" name="lstDulieuChitieu[0].MA" id="id_machitieu" size="10" placeholder="MACTxxxxx"/>
                        </td>
                        <td>
                            <input type="text" name="lstDulieuChitieu[0].TEN" id="id_tenchitieu0" size="40" placeholder="Nhập tên mô tả chỉ tiêu"/>
                        </td>    
                        <td>
                            <input type="button" value="Xóa chỉ tiêu" onclick="deleteChitieuTable(this.parentNode.parentNode.rowIndex)" class="metroButtonStyle"/>

                        </td>
                    </tr>
                </s:if>
                <s:else>
                    <s:iterator value="#attr.lstDulieuChitieu" var="modelView" status="rowstatus">
                        <tr style="background: #E2E8C9; height: 30px;" align="center">
                            <td>
                                <input type="text" id="id_machitieu_<s:property  value="%{#rowstatus.index}" />"  value="<s:property  value="MA" />"
                                       name="lstDulieuChitieu[<s:property  value="%{#rowstatus.index}" />].MA"  size="10" placeholder="MACTxxxxx"/>
                            </td>
                            <td style="width: 45%">
                                <input type="text" name="lstDulieuChitieu[<s:property  value="%{#rowstatus.index}" />].TEN" id="id_tenchitieu<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                       size="40" placeholder="Nhập tên mô tả chỉ tiêu"/>
                            </td>
                            <td >
                                <input type="button" value="Xóa chỉ tiêu" style="margin: 0px;" onclick="deleteChitieuTable(this.parentNode.parentNode.rowIndex)" class="metroButtonStyle"/>
                            </td>
                        </tr> 
                    </s:iterator> 
                </s:else>
                <tr   style="background: #c5dbec; height: 53px;">
                    <td colspan="3" align="right">
                        <input type="button" value="Thêm chỉ tiêu" onclick="addChitieuTable(this.parentNode.parentNode.rowIndex)" class="metroButtonStyle"/>
                    </td>
                </tr>
            </table>
        </div>

        <!--        Cho phần cột dữ liệu-->
        <div class="cotdulieu" align="left">
            <table id="id_table_bang" border="1" class="id_table_bang">
                <s:if test="lstDulieuCot.empty">
                    <tr style="background: #E2E8C9" align="center">
                        <td >
                            <label class="Classcotdulieu">Cột 1 </label><br>
                            <textarea name="lstDulieuCot[0].TEN" id="id_tenCotchitieu_0" rows="4" cols="20" style="margin: 2px 2px; height: 60px; width: 100px;"
                                      placeholder="Nhập tên cột dữ liệu"><s:property  value="TEN" /></textarea>

                        </td>
                        <td rowspan="4">
                            <input type="button" value="Thêm cột dữ liệu" onclick="addColumn()" class="metroButtonStyle">
                        </td>
                    </tr>          
                    <tr style="background: #E2E8C9; height: 50px;" align="center">
                        <td>
                            <label>Loại dữ liệu: </label><br>
                            <select name="lstDulieuCot[0].KIEUDULIEU" id="id_kieudulieu_0" style="width: 100px" onchange="showButtonList(0);">
                                <option value="T">T -&gt; Kiểu text</option>
                                <option value="N">N -&gt; Kiểu số</option>
                                <option value="D">D -&gt; Ngày tháng năm</option>
                                <option value="L">L -&gt; Danh mục</option>
                            </select>

                        </td>
                    </tr>
                    <tr style="background: #E2E8C9;" align="center">
                        <td>
                            <input type="button" id="id_showparameter_0" value="Tham số List" style="margin: 0px;display: none;" 
                                   onclick="calldilog(0);" class="metroButtonStyle">
                            <input type="hidden" name="lstDulieuCot[0].MA" value="" id="PARAMETER_LIST_0">
                        </td>
                    </tr>
                    <tr style="background: #E2E8C9; height: 50px;" align="center">
                        <td align="center">
                            <input type="button" value="Xóa cột dữ liệu" onclick="deleteCColumnTable(this.parentNode)" class="metroButtonStyle">
                        </td>
                    </tr>
                </s:if>
                <s:else>
                    <tr style="background: #E2E8C9;" align="center">
                        <s:iterator value="#attr.lstDulieuCot" var="modelView" status="rowstatus">
                            <%--<s:if test="%{#rowstatus.index == 0}"> <s:if test="%{#rowstatus.first == true}"> </s:if>--%>
                            <%--<s:elseif test="#rowstatus.last==true"> </s:elseif>--%>
                            <td >
                                <label class="Classcotdulieu">Cột <s:property  value="%{#rowstatus.count}" /> </label><br>
                                <textarea name="lstDulieuCot[<s:property  value="%{#rowstatus.index}" />].TEN" id="id_tenCotchitieu_<s:property  value="%{#rowstatus.count}" />" rows="4" cols="20" style="margin: 2px 2px; height: 60px; width: 100px;"><s:property  value="TEN"/></textarea>
                            </td>
                        </s:iterator>
                        <td rowspan="4">
                            <input id="themcotduliu_add" type="button" value="Thêm cột dữ liệu" onclick="addColumn()" class="metroButtonStyle">
                        </td>
                    </tr>
                    <tr style="background: #E2E8C9;  height: 50px;" align="center" >
                        <s:iterator value="#attr.lstDulieuCot" var="modelView" status="rowstatus">
                            <td style="width: 100px;">
                                <label>Loại dữ liệu: </label>
                                <select name="lstDulieuCot[<s:property  value="%{#rowstatus.index}" />].KIEUDULIEU" id="id_kieudulieu_<s:property  value="%{#rowstatus.index}" />" style="width: 100px;" onchange="showButtonList(<s:property  value="%{#rowstatus.index}" />);"> 
                                    <option value="T" <s:if test="KIEUDULIEU.equalsIgnoreCase('T')"> selected </s:if> >T -&gt; Kiểu text</option>
                                    <option value="N" <s:elseif test="KIEUDULIEU.equalsIgnoreCase('N')"> selected </s:elseif>>N -&gt; Kiểu số</option>
                                    <option value="D" <s:elseif test="KIEUDULIEU.equalsIgnoreCase('D')"> selected </s:elseif>>D -&gt; Ngày tháng năm</option>      
                                    <option value="L" <s:elseif test="KIEUDULIEU.equalsIgnoreCase('L')"> selected </s:elseif>>L -&gt; Danh mục</option>    
                                    </select>
                                </td>                            
                        </s:iterator>
                    </tr>
                    <tr style="background: #E2E8C9;" align="center">
                        <s:iterator value="#attr.lstDulieuCot" var="modelView" status="rowstatus">
                            <td>
                                <input type="button" id="id_showparameter_<s:property  value="%{#rowstatus.index}" />" value="Tham số List" 
                                    <s:if test="KIEUDULIEU.equalsIgnoreCase('L')"> 
                                       style="margin: 0px;" 
                                    </s:if>
                                    <s:else> style="margin: 0px;display: none;"  </s:else>
                                       onclick="calldilog(<s:property  value="%{#rowstatus.index}" />);" class="metroButtonStyle">
                                <input type="hidden" name="lstDulieuCot[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA" escapeJavaScript="false" />" id="PARAMETER_LIST_<s:property  value="%{#rowstatus.index}" />">
                            </td>
                        </s:iterator>
                    </tr>
                    <tr style="background: #E2E8C9; height: 50px;" align="center">
                        <s:iterator value="#attr.lstDulieuCot" var="modelView" status="rowstatus">
                            <td align="center">
                                <input type="button" value="Xóa cột dữ liệu" style="margin: 0px;" onclick="deleteCColumnTable(this.parentNode)" class="metroButtonStyle">
                            </td>                            
                        </s:iterator>
                    </tr>
                </s:else>
            </table>

        </div>

    </div>
</div>