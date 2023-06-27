<%-- 
    Document   : ViewData
    Created on : May 17, 2022, 9:56:22 AM
    Author     : NGUYEN THANH TRUNG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->
<style>
    #subTable {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
    }
    #subTable th{
        background-color: #028e07;
        color: white;
    }

    #subTable th, #subTable td {
        border: 1px solid gray;
    }

    #subTable tr:nth-child(even){background-color: #f2f2f2;}
    #subTable tr:hover {background-color: #ddd;}


    .ui-datepicker-trigger{
        height: 100%;
    }
    .txtBody{
        text-align: center;
    }
    .txtBody > .ui-datepicker-trigger{
        display: none;
    }
    td.hdtitle {
        position: sticky;
        top: 0;
        z-index: 10;
    }
    .number {
        width: 80px;
    }
    .number2 {
        width: 80px;
    }
</style>
<script src="js/jquery.number.js"></script>
<script src="js/format_num.js"></script>
<script>
    var max_row = 0;   
</script>
</head>
<body>
    <p style="text-align: right;">Đơn vị: Triệu đồng</p>
    <div style="overflow:scroll; width: 99vw; min-height: 250px;">          
        <table id="subTable" style="z-index: 1;">
            <thead>
                <tr>                      
                    <th  class="hdtitle" rowspan="2">
                        <input type="checkbox" id ="select-all"/>
                    </th>  
                    <th rowspan="2">Chỉnh sửa</th>
                    <th class="hdtitle" rowspan="2">PGD</th>

                    <th class="hdtitle" rowspan="2">Tên tài sản cần bổ sung thay thế</th>
                    <th class="hdtitle" rowspan="2">Tổng số lượng hiện có</th>
                    <th class="hdtitle" rowspan="2">Tổng giá trị còn lại</th>
                    <th class="hdtitle" rowspan="2">Hiện trạng tài sản</th>
                    <th class="hdtitle" colspan="7">TSCĐ đề nghị trang bị năm 2023</th>                    
                    <th class="hdtitle" rowspan="2">Thuyết minh</th>       

                </tr>
                <tr>                      
                    <th class="hdtitle">Mã nhóm TSCĐ</th>
                    <th class="hdtitle">Mục đích, nơi sử dụng</th>
                    <th class="hdtitle">Quy cách, cấu hình kỹ thuật</th>
                    <th class="hdtitle">Số lượng</th>
                    <th class="hdtitle">Đơn giá</th>
                    <th class="hdtitle">Thành tiền</th>
                    <th class="hdtitle">Nguồn vốn</th>                    
                </tr>
            </thead>
            <tbody>                       
                <tr style="display: none;" id="tmp_row"> 
                        <td style="display: none;">
                            <input type="text" name="lstData[max_row].Key" value="KTTC_MUASAM_TS_001" class="TEN_KH">
                            <input type="text" name="lstData[max_row].Code" value="<s:property value='txtMapgd'/>_<s:property value='userId'/>_max_row" class="TEN_KH">
                            <input type="text" name="lstData[max_row].PosCode" value="<s:property value='txtMapgd'/>" class="TEN_KH">
                            <input type="text" name="lstData[max_row].PosFlag" value="<s:property value='posFlag'/>" class="TEN_KH">
                        </td>
                        <td></td>
                        <td align = "center" class="TD_TEN_KH">                            
                            <input type="button" value="Xóa dòng" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>                            
                        </td>
                        <td class="txtBody">
                            <select name="lstData[max_row].D1" id="lstData_D1max_row">                                
                                <s:iterator value="lstPGD" status="posRows" var="language">                                    
                                    <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>                                    
                                </s:iterator>                                
                            </select>
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d3" value="" class="TEN_KH">
                        </td>
                        <td class="txtBody" style="width: 80px;">
                            <input type="text" value="" 
                                   name="lstData[max_row].D4" class="TEN_KH number" onfocus="this.select()"/>
                        </td>                                                                                                
                        <td class="txtBody">
                            <input type="text"  name="lstData[max_row].D5" class="TEN_KH number2" onfocus="this.select()"/>
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d6" value="<s:property value='d6'/>" class="TEN_KH">
                        </td>
                        <td class="txtBody">
                            <select name="lstData[max_row].D7" id="lstData_D7max_row">                                
                                <s:iterator value="lstAssetGroup" status="assetGroupRows" var="assetGroupItem">                                    
                                    <option value="<s:property value="Code"/>"><s:property value="Description"/></option>                                    
                                </s:iterator>                                
                            </select>
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d8" class="TEN_KH">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d9" class="TEN_KH">
                        </td>   
                        <td class="txtBody">
                            <input type="text"  name="lstData[max_row].D10" class="TEN_KH number" id="lstData_D10_max_row" onfocus="this.select(); sumColumn();"/>
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].D11" class="TEN_KH number2" id="lstData_D11_max_row" onfocus="this.select(); sumColumn();"/>
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].D12" class="TEN_KH number2" id="lstData_D12_max_row" onfocus="this.select(); sumColumn();" readonly="true" style="background: #ddd"/>
                        </td>
                        <td class="txtBody">
                            <select name="lstData[max_row].D13" id="lstData_D13_max_row">
                                <option value="01" selected>01 - Trung ương</option>
                                <option value="02">02 - Địa phương</option>
                                <option value="03">03 - Khác</option>
                                </select>
                            </td>
                            <td class="txtBody"><input type="text" name="lstData[max_row].d14" class="TEN_KH">
                        </td>                           
                    </tr>
                <s:iterator value="lstData" status="idxRows">
                    <tr>          
                        <td style="display: none;">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].Key" value="<s:property value='Key'/>" class="TEN_KH">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].Code" value="<s:property value='Code'/>" class="TEN_KH">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].PosCode" value="<s:property value='PosCode'/>" class="TEN_KH">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].PosFlag" value="<s:property value='PosFlag'/>" class="TEN_KH">
                        </td>
                        <td class="txtBody">                            
                            <input type="checkbox" class="myCheckBox" name="lstData[<s:property  value='%{#idxRows.index}' />].manualFlag" value="0" onclick="$(this).val(this.checked ? 1 : 0)">                                                        
                        </td>  
                        <td align = "center" class="TD_TEN_KH">
                            <s:if test="#idxRows.index == 0">
                                <input type="button" value="Thêm dòng" onclick="addRow();" class="TEN_KH"/>
                            </s:if>
                            
                        </td>
                        <td class="txtBody">
                            <select name="lstData[<s:property  value='%{#idxRows.index}' />].D1" id="lstData_D1<s:property  value='%{#idxRows.index}' />">                                
                                <s:iterator value="lstPGD" status="posRows" var="language">                                    
                                    <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>                                    
                                </s:iterator>                                
                            </select>
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d3" value="<s:property value='d3'/>" class="TEN_KH">
                        </td>
                        <td class="txtBody" style="width: 80px;">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstData[<s:property  value="%{#idxRows.index}" />].D4" class="TEN_KH number" onfocus="this.select()"/>
                        </td>                                                                                                
                        <td class="txtBody">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstData[<s:property  value="%{#idxRows.index}" />].D5" class="TEN_KH number2" onfocus="this.select()"/>
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d6" value="<s:property value='d6'/>" class="TEN_KH">
                        </td>
                        <td class="txtBody">
                            <select name="lstData[<s:property  value='%{#idxRows.index}' />].D7" id="lstData_D7<s:property  value='%{#idxRows.index}' />">                                
                                <s:iterator value="lstAssetGroup" status="assetGroupRows" var="assetGroupItem">                                    
                                    <option value="<s:property value="Code"/>" <s:if test="d7.equalsIgnoreCase(#assetGroupItem.Code)"> selected="true" </s:if>><s:property value="Description"/></option>                                    
                                </s:iterator>                                
                            </select>
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d8" value="<s:property value='d8'/>" class="TEN_KH">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d9" value="<s:property value='d9'/>" class="TEN_KH">
                        </td>   
                        <td class="txtBody">
                            <input type="text" value="<s:property  value="D10" />" id="lstData_D10_<s:property  value='%{#idxRows.index}' />"
                                   name="lstData[<s:property  value="%{#idxRows.index}" />].D10" class="TEN_KH number" onfocus="this.select();sumColumn();"/>
                        </td>
                        <td class="txtBody">
                            <input type="text" value="<s:property  value="D11" />" id="lstData_D11_<s:property  value='%{#idxRows.index}' />"
                                   name="lstData[<s:property  value="%{#idxRows.index}" />].D11" class="TEN_KH number2" onfocus="this.select();sumColumn();"/>
                        </td>
                        <td class="txtBody">
                            <input type="text" value="<s:property  value="D12" />" id="lstData_D12_<s:property  value='%{#idxRows.index}' />"
                                   name="lstData[<s:property  value="%{#idxRows.index}" />].D12" class="TEN_KH number2" style="background: #ddd" onfocus="this.select();sumColumn();" readonly="true"/>
                        </td>
                        <td class="txtBody">
                            <select name="lstData[<s:property  value='%{#idxRows.index}' />].D13" id="lstData_D13_<s:property  value='%{#idxRows.index}' />">
                                <option value="01" <s:if test="d13.equalsIgnoreCase('01')"> selected </s:if> <s:else></s:else>>01 - Trung ương</option>
                                <option value="02" <s:if test="d13.equalsIgnoreCase('02')"> selected </s:if> <s:else></s:else>>02 - Địa phương</option>
                                <option value="03" <s:if test="d13.equalsIgnoreCase('03')"> selected </s:if> <s:else></s:else>>03 - Khác</option>
                                </select>
                            </td>
                            <td class="txtBody">
                                    <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d14" value="<s:property value='d14'/>" class="TEN_KH">
                        </td>                           
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </div>
</body>

<script>
    function deleteRow(indx) {
        var table = document.getElementById("subTable");
        var rowCount = table.rows.length - 3; //Dem so dong cua bang
        if (max_row < rowCount)
        {
            max_row = rowCount;
        }
        max_row--;
        table.deleteRow(indx);        
    }

    function addRow() {                
        var table = document.getElementById("subTable"); // find table to append to
        
        var rowCount = table.rows.length - 3; //Dem so dong cua bang
        if (max_row < rowCount)
        {
            max_row = rowCount;
        } else
        {
            max_row++;
            rowCount = max_row;
        }
        var row = document.getElementById("tmp_row"); // find row to copy      

        var clone = row.cloneNode(true); // copy children too      
        clone.removeAttribute('style');
        clone.removeAttribute('id');
        clone.id = "row_id_" + max_row; // change id or other attributes/contents
        var innerHTML = clone.innerHTML;        
        var newHTML = innerHTML.replaceAll("max_row", max_row);        
        clone.innerHTML = newHTML;        
        table.appendChild(clone); // add new row to end of table    
        
        $('input.number').css({"text-align": "right"});
        $('input.number2').css({"text-align": "right"});
        $('.number').number(true, 0);
        $('.number2').number(true, 2);
    }

    $(document).ready(function () {
        $('input.number').css({"text-align": "right"});
        $('input.number2').css({"text-align": "right"});
        $('.number').number(true, 0);
        $('.number2').number(true, 2);
    });
    $('.TEN_KH').focus(function () {
        $(this).closest('tr').addClass('highlight_row');
    });
    $('.TEN_KH').blur(function () {
        $(this).closest('tr').removeClass('highlight_row');
    });
   
    function getValue(id)
    {
        var value = 0;
        try {
            value = document.getElementById(id).value;
            value = value.replace(/,/g, "");
            if (value === '-1')
                value = 0.0;
        } catch (e)
        {
            value = 0.0;
        }
        return parseFloat(value);
    }
    function setValue(id, value)
    {
        try {
            document.getElementById(id).value = value;
        } catch (e)
        {
        }
    }
    
    function sumColumn()
    {       
        try {
            var table = document.getElementById("subTable");
            var rowcount = table.rows.length;
            rowcount = rowcount > max_row ? rowcount : max_row;
            var d10 = 0, d11 = 0.00, d12 = 0.00;            
            for (var i = 0; i < rowcount; i++)
            {                
                d10 = getValue('lstData_D10_' + i);
                d11 = getValue('lstData_D11_' + i);
                d12 = d10 * d11;
                var _totalId = 'lstData_D12_'+ i;
                setValue(_totalId, d12);
            }  
            $('.number').number(true, 0);
            $('.number2').number(true, 2);
        } catch (e){
            alert(e);
        }
    }
    
    $(function () {
       $('#select-all').click(function(event) {   
            if(this.checked) {
                // Iterate each checkbox
                $('.myCheckBox').each(function() {
                    this.checked = true; 
                    this.value = '1';
                });
            } else {
                $('.myCheckBox').each(function() {
                    this.checked = false;                       
                    this.value = '0';
                });
            }
        }); 
    });
</script>