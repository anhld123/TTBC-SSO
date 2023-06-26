<%-- 
    Document   : ViewData
    Created on : May 17, 2022, 9:56:22 AM
    Author     : NGUYEN PHU VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<style>
    #subTable {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
    }
    #subTable th{
        background-color: #ddd;
        color: black;
    }

    #subTable th, #subTable td {
        border: 1px solid gray;
    }

    #subTable tr:nth-child(even){background-color: #f2f2f2;}

    #subTable tr:hover {background-color: #ddd;}

    .txtPublic{
        width: 85px;
    }
    .ui-datepicker-trigger{
        height: 100%;
    }
    .txtBody{
        text-align: center;
        width: 100px;
    }
    .txtBody > .ui-datepicker-trigger{
        display: none;
    }
    td.hdtitle {
        position: sticky;
        top: 0;
        z-index: 10;
    }

    #divDonvitinh{
        font: 12px Arial, Helvetica, sans-serif;
        text-align: right;
        color: red;
        padding-right: 7px;
    }

    .hdtitle1 {
        z-index: 6;
        font-style: italic;
        font-size: xx-small;
    }

</style>
<script>
    var popWindow;

    $(function () {
        $(".cssDate").datepicker(
                {
                    dateFormat: 'dd/mm/yy', showOn: "button",
                    buttonImage: "img/icon-ui_datepicker.png",
                    buttonImageOnly: true,
                    buttonText: "icono",
                    //dateFormat: 'dd/mm/yy',
                    showButtonPanel: true,
                    changeMonth: true,
                    changeYear: true,
                    //showOn: "both"
                });
    });
    $('.autoHeight').each(function () {
        this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
    }).on('input', function () {
        this.style.height = 'auto';
        this.style.height = (this.scrollHeight) + 'px';
    });

    function onSelectChange(index) {

        let selectedValue = $('#lstData_D30' + index).find(":selected").val();
        let province = selectedValue.substring(0, 4);

        $('#lstData_D32' + index + ' option').each(function () {
            //if (!$(this).val().startsWith('0006') ) {
            $(this).remove();
            //}
        });

        $('#lstPGD_Temp option').each(function () {
            if ($(this).val().startsWith(province)) {
                //alert($(this).text() );
                $('#lstData_D32' + index).append($('<option>',
                        {
                            value: $(this).val(),
                            text: $(this).text()
                        }));
            }
        });
    }
</script>
</head>
<body>

    <div style="overflow:scroll; width: 99vw;">     
        <div style="display: none;">
            <select id="lstPGD_Temp">
                <option value="000000">Không xác định</option>
                <s:iterator value="lstPGD" status="ideRows" var="language">                                    
                    <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>                                    
                </s:iterator>
                <option value="999999">Nước ngoài</option>
            </select>
        </div>
        <div style="margin: 5px;">
            <div id="divDonViTinh">
                Đơn vị tính: Nghìn đồng
            </div>
            <table id="subTable" style="z-index: 1;">
                <thead>
                    <tr> 
                    <tr> 
                        <th rowspan="2" class="hdtitle">STT</th>
                        <th rowspan="2" class="hdtitle">Tên PGD</th>
                        <th rowspan="2" class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ QGVL</th>
                        <th rowspan="2" class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th rowspan="2" class="hdtitle">Tổng số lãi thu được</th>
                        <th rowspan="2" class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th colspan="3" class="hdtitle">UBND</th>
                        <th colspan="3" class="hdtitle">Hội Phụ nữ</th>
                        <th colspan="3" class="hdtitle">Hội Nông dân</th>
                        <th colspan="3" class="hdtitle">Hội Cứu chiến binh</th>
                        <th colspan="3" class="hdtitle">Đoàn thanh niên</th>
                        <th colspan="3" class="hdtitle">Liên minh Hợp tác xã</th>
                        <th colspan="3" class="hdtitle">Tổng Liên đoàn lao động</th>
                        <th colspan="3" class="hdtitle">Hội người mù</th>
                    </tr>  
                    <tr>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                    </tr>
                    <tr>
                        <th class="hdtitle1">(1)</th> 
                        <th class="hdtitle1">(2)</th> 
                        <th class="hdtitle1">(3)</th> 
                        <th class="hdtitle1">(4)</th> 
                        <th class="hdtitle1">(5)</th> 
                        <th class="hdtitle1">(6)</th> 
                        <th class="hdtitle1">(7)</th> 
                        <th class="hdtitle1">(8)</th> 
                        <th class="hdtitle1">(9)</th> 
                        <th class="hdtitle1">(10)</th> 
                        <th class="hdtitle1">(11)</th> 
                        <th class="hdtitle1">(12)</th> 
                        <th class="hdtitle1">(13)</th> 
                        <th class="hdtitle1">(14)</th> 
                        <th class="hdtitle1">(15)</th> 
                        <th class="hdtitle1">(16)</th> 
                        <th class="hdtitle1">(17)</th> 
                        <th class="hdtitle1">(18)</th> 
                        <th class="hdtitle1">(19)</th> 
                        <th class="hdtitle1">(20)</th>
                        <th class="hdtitle1">(21)</th> 
                        <th class="hdtitle1">(22)</th> 
                        <th class="hdtitle1">(23)</th> 
                        <th class="hdtitle1">(24)</th>
                        <th class="hdtitle1">(25)</th> 
                        <th class="hdtitle1">(26)</th>
                        <th class="hdtitle1">(27)</th> 
                        <th class="hdtitle1">(28)</th>
                        <th class="hdtitle1">(29)</th> 
                        <th class="hdtitle1">(30)</th>
                    </tr>
                </thead>
                <tbody>
                    <% int customerCount = 0;%>
                    <s:iterator value="lstData" status="idxRows">
                        <tr class="tr_clone">
                            <td class="txtBody" style="display: none;">                            
                                <s:property value="D3"/>
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].key" value="<s:property value='key'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].orderValue" value="<s:property value='orderValue'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].code" value="<s:property value='code'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].name" value="<s:property value='name'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].posCode" value="<s:property value='posCode'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].posFlag" value="<s:property value='posFlag'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].branchCode" value="<s:property value='branchCode'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].reportDate" value="<s:property value='reportDate'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].reportYear" value="<s:property value='reportYear'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].makerId" value="<s:property value='makerId'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].makerDate" value="<s:property value='makerDate'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].authoriseId" value="<s:property value='authoriseId'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].authoriseDate" value="<s:property value='authoriseDate'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d1" value="<s:property value='d1'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d2" value="<s:property value='d2'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d3" value="<s:property value='d3'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d4" value="<s:property value='d4'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d5" value="<s:property value='d5'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d6" value="<s:property value='d6'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d7" value="<s:property value='d7'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d8" value="<s:property value='d8'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d9" value="<s:property value='d9'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d10" value="<s:property value='d10'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d11" value="<s:property value='d11'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d12" value="<s:property value='d12'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d13" value="<s:property value='d13'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d14" value="<s:property value='d14'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d15" value="<s:property value='d15'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d17" value="<s:property value='d17'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d18" value="<s:property value='d18'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d19" value="<s:property value='d19'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d20" value="<s:property value='d20'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d21" value="<s:property value='d21'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d22" value="<s:property value='d22'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d23" value="<s:property value='d23'/>">                            
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d24" value="<s:property value='d24'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d25" value="<s:property value='d25'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d26" value="<s:property value='d26'/>">        
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d27" value="<s:property value='d27'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d28" value="<s:property value='d28'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d29" value="<s:property value='d29'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d30" value="<s:property value='d30'/>">                            
                            </td>
                            <td class="txtBody"><s:property value="d1"/></td>
                            <td class="txtBody"><s:property value="d2"/></td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D4" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D5" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D6" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D7" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D8" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D9" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D10" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D11" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D12" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D13" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D14" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D15" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D16" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D18" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D20" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D21" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D20" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D21" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D22" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D23" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D24" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D25" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D26" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D26" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D27" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D28" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D29" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D29" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="txtBody" >
                                <input type="text"   value="<s:property  value="D30" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30" 
                                       onfocus="this.select();" /> 
                            </td>

                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
</body>
<script>
    $(function () {
        $('#select-all').click(function (event) {
            if (this.checked) {
                // Iterate each checkbox
                $('.myCheckBox').each(function () {
                    this.checked = true;
                    this.value = '1';
                });
            } else {
                $('.myCheckBox').each(function () {
                    this.checked = false;
                    this.value = '0';
                });
            }
        });
    });
</script>