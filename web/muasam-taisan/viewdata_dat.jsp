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
    
    .orange {
        background-color: orange !important;
    }
    
    .blue {
        background-color: blue !important;
    }
</style>
<script src="js/jquery.number.js"></script>
<script src="js/format_num.js"></script>
<script>
    var max_row = 0;   
</script>
</head>
<body>
    <p style="text-align: right;">Đơn vị: đồng</p>
    <div style="overflow:scroll; width: 99vw; min-height: 250px;">          
        <table id="subTable" style="z-index: 1;">
            <thead>
                <tr>                      
                    <th  class="hdtitle" rowspan="3">
                        <input type="checkbox" id ="select-all"/>
                    </th>  
                    <th rowspan="3">Chỉnh sửa-HNN</th>
                    <th class="hdtitle" rowspan="3">PGD</th>   
                    <th class="hdtitle" rowspan="3">Thời gian sử dụng</th>
                    <th class="hdtitle" rowspan="3">Nhóm đất (theo quy định của địa phương)</th>
                    <th class="hdtitle" rowspan="3">Tổng giá trị QSĐ đánh giá theo NĐ 151</th>
                    <th class="hdtitle blue" colspan="12" >Các cơ sở đất xác định giá trị theo quy định tại khoản 1- Điều 102 NĐ 151/2017/NĐ-CP</th>
                    <th class="hdtitle orange" colspan="12" >Các cơ sở đất xác định giá trị theo quy định tại khoản 2- Điều 102 NĐ 151/2017/NĐ-CP</th>                                   
                    <th class="hdtitle" rowspan="3">Thuyết minh</th>       

                </tr>
                <tr>                      
                    <th class="hdtitle blue" colspan="3">Vị trí 1</th>
                    <th class="hdtitle blue" colspan="3">Vị trí 2</th>
                    <th class="hdtitle blue" colspan="3">Vị trí 3</th>
                    <th class="hdtitle blue" colspan="3">Vị trí 4</th>    
                    <th class="hdtitle orange" colspan="3">Vị trí 1</th>
                    <th class="hdtitle orange" colspan="3">Vị trí 2</th>
                    <th class="hdtitle orange" colspan="3">Vị trí 3</th>
                    <th class="hdtitle orange" colspan="3">Vị trí 4</th>
                </tr>
                 <tr>                      
                    <th class="hdtitle blue" >Diện tích đất</th>
                    <th class="hdtitle blue" >Đơn giá đất/m2</th>
                    <th class="hdtitle blue" >Hệ số điều chỉnh</th>
                    <th class="hdtitle blue" >Diện tích đất</th>
                    <th class="hdtitle blue" >Đơn giá đất/m2</th>
                    <th class="hdtitle blue" >Hệ số điều chỉnh</th>
                    <th class="hdtitle blue" >Diện tích đất</th>
                    <th class="hdtitle blue" >Đơn giá đất/m2</th>
                    <th class="hdtitle blue" >Hệ số điều chỉnh</th>
                    <th class="hdtitle blue" >Diện tích đất</th>
                    <th class="hdtitle blue" >Đơn giá đất/m2</th>
                    <th class="hdtitle blue" >Hệ số điều chỉnh</th>    
                    <th class="hdtitle orange" >Diện tích đất</th>
                    <th class="hdtitle orange" >Đơn giá đất/m2</th>
                    <th class="hdtitle orange" >Hệ số điều chỉnh</th>
                    <th class="hdtitle orange" >Diện tích đất</th>
                    <th class="hdtitle orange" >Đơn giá đất/m2</th>
                    <th class="hdtitle orange" >Hệ số điều chỉnh</th>
                    <th class="hdtitle orange" >Diện tích đất</th>
                    <th class="hdtitle orange" >Đơn giá đất/m2</th>
                    <th class="hdtitle orange" >Hệ số điều chỉnh</th>
                    <th class="hdtitle orange" >Diện tích đất</th>
                    <th class="hdtitle orange" >Đơn giá đất/m2</th>
                    <th class="hdtitle orange" >Hệ số điều chỉnh</th> 
                </tr>
            </thead>
            <tbody id="subTable_body">                       
                <tr style="display: none;" id="tmp_row"> 
                        <td style="display: none;">
                            <input type="text" name="lstData[max_row].Key" value="KTTC_QSDD_01" class="TEN_KH">
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
                        <td class="txtBody" style="width: 80px;">
                            <input type="text" name="lstData[max_row].d3" value="" class="TEN_KH number">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d4" value="" class="TEN_KH" onfocus="this.select()"/>
                        </td>                                                                                                
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d5" value="" class="TEN_KH number2" onfocus="this.select()"/>
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d6" value="" class="TEN_KH number2">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d7" value="" class="TEN_KH number2 ">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d8" value="" class="TEN_KH number2">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d9" value="" class="TEN_KH number2">
                        </td>   
                        
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d10" value="" class="TEN_KH number2">
                        </td>   
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d11" value="" class="TEN_KH number2">
                        </td>   
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d12" value="" class="TEN_KH number2">
                        </td>   
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d13" value="" class="TEN_KH number2">
                        </td>   
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d14" value="" class="TEN_KH number2">
                        </td>   
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d15" value="" class="TEN_KH number2">
                        </td>   
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d16" value="" class="TEN_KH number2">
                        </td>   
                        
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d17" value="" class="TEN_KH number2">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d18" value="" class="TEN_KH number2">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d19" value="" class="TEN_KH number2">
                        </td>                  
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d20" value="" class="TEN_KH number2">
                        </td>       
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d21" value="" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d22" value="" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d23" value="" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d24" value="" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d25" value="" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d26" value="" class="TEN_KH number2">
                        </td>  
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d27" value="" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d28" value="" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d29" value="" class="TEN_KH number2">
                        </td>       
                        <td class="txtBody">
                            <input type="text" name="lstData[max_row].d30" value="" class="TEN_KH">
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
                                <s:iterator value="lstPGD" status="posRows" var="posItem">                                    
                                    <option value="<s:property value="PosCode"/>" <s:if test="d1.equalsIgnoreCase(#posItem.PosCode)"> selected="true" </s:if>><s:property value="PosName"/></option>                                    
                                </s:iterator>                                
                            </select>
                        </td>
                        
                        <td class="txtBody" style="width: 80px;">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d3" value="<s:property value='d3'/>" class="TEN_KH number2">
                        </td>
                        <td class="txtBody">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstData[<s:property  value="%{#idxRows.index}" />].D4" class="TEN_KH" onfocus="this.select()"/>
                        </td>                                                                                                
                        <td class="txtBody">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstData[<s:property  value="%{#idxRows.index}" />].D5" class="TEN_KH number2" onfocus="this.select()"/>
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d6" value="<s:property value='d6'/>" class="TEN_KH number2">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d7" value="<s:property value='d7'/>" class="TEN_KH number2 ">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d8" value="<s:property value='d8'/>" class="TEN_KH number2">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d9" value="<s:property value='d9'/>" class="TEN_KH number2">
                        </td>   
                        
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d10" value="<s:property value='d10'/>" class="TEN_KH number2">
                        </td>   
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d11" value="<s:property value='d11'/>" class="TEN_KH number2">
                        </td>   
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d12" value="<s:property value='d12'/>" class="TEN_KH number2">
                        </td>   
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d13" value="<s:property value='d13'/>" class="TEN_KH number2">
                        </td>   
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d14" value="<s:property value='d14'/>" class="TEN_KH number2">
                        </td>   
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d15" value="<s:property value='d15'/>" class="TEN_KH number2">
                        </td>   
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d16" value="<s:property value='d16'/>" class="TEN_KH number2">
                        </td>   
                        
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d17" value="<s:property value='d17'/>" class="TEN_KH number2">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d18" value="<s:property value='d18'/>" class="TEN_KH number2">
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d19" value="<s:property value='d19'/>" class="TEN_KH number2">
                        </td>                  
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d20" value="<s:property value='d20'/>" class="TEN_KH number2">
                        </td>       
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d21" value="<s:property value='d21'/>" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d22" value="<s:property value='d22'/>" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d23" value="<s:property value='d23'/>" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d24" value="<s:property value='d24'/>" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d25" value="<s:property value='d25'/>" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d26" value="<s:property value='d26'/>" class="TEN_KH number2">
                        </td>  
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d27" value="<s:property value='d27'/>" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d28" value="<s:property value='d28'/>" class="TEN_KH number2">
                        </td>    
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d29" value="<s:property value='d29'/>" class="TEN_KH number2">
                        </td>       
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d30" value="<s:property value='d30'/>" class="TEN_KH">
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
        var rowCount = table.rows.length - 4; //Dem so dong cua bang
        if (max_row < rowCount)
        {
            max_row = rowCount;
        }
        max_row--;
        table.deleteRow(indx);        
    }

    function addRow() {                
        var table = document.getElementById("subTable_body"); // find table to append to        
        var rowCount = table.rows.length - 1; //Dem so dong cua bang
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
        
        var _title = $("#report_title");
        if (_title !== null) {
            var _year = 1900 + $("#txtNgayBc").datepicker('getDate').getYear();
            $("#report_title").text("TSCĐ đề nghị trang bị năm " + _year);
        }
        
        $('.TEN_KH').focus(function () {
            $(this).closest('tr').addClass('highlight_row');
        });
        $('.TEN_KH').blur(function () {
            $(this).closest('tr').removeClass('highlight_row');
        });

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
    
//    function sumColumn()
//    {       
//        try {
//            var table = document.getElementById("subTable");
//            var rowcount = table.rows.length;
//            rowcount = rowcount > max_row ? rowcount : max_row;
//            var d10 = 0, d11 = 0.00, d12 = 0.00;            
//            for (var i = 0; i < rowcount; i++)
//            {                
//                d10 = getValue('lstData_D10_' + i);
//                d11 = getValue('lstData_D11_' + i);
//                d12 = d10 * d11;
//                var _totalId = 'lstData_D12_'+ i;
//                setValue(_totalId, d12);
//            }  
//            $('.number').number(true, 0);
//            $('.number2').number(true, 2);
//        } catch (e){
//            alert(e);
//        }
//    }
    
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