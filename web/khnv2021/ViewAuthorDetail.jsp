<%-- 
    Document   : ViewAuthorDetail
    Created on : Jun 18, 2021, 9:44:51 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<style>
    *{
        font-family: tahoma;
        font-size: 13px;
    }

    table {
        width : 100%;
        border-top: 1px solid orange;
        border-left: 1px solid #c2c2c2;
        border-right: 1px solid #c2c2c2;
        border-bottom: 1px solid #c2c2c2;
        text-align : center;
        border-collapse : collapse;
    }
    table tr th, table tr td {
        border : 1px solid #c2c2c2;
    }


    table thead th {
        position: -webkit-sticky;
        position : sticky;
        top : 0;
        color: white;
        background-color : #04AA6D;
    }

    /* here is the trick */
    table tbody:nth-of-type(1) tr:nth-of-type(1) td {
        border-top: none !important;
    }
    table thead th {
        border-top: none !important;
        border-bottom: none !important;
        box-shadow: inset 0 0px 0 #c2c2c2,
            inset 0 -1px 0 #c2c2c2;
    }

    table thead th {
        background-clip: padding-box
    }

    table thead { position: sticky; top: 0; z-index: 1; }

    th, td {
        text-align: left;
        border: 1px solid #c2c2c2;
        text-align: center;
        padding: 3px;
    }

    th{
        padding: 8px;
    }
    .sttCol>td{
        font-style: italic;
    }
    .clss-body-ngnhan{
        box-sizing: content-box;
        padding: 5px;
    }
    textarea
    {
        border:1px solid #000;
        width:100%;
        height: 100px;
    }
    .clss-lable{
        font-weight: bold;
    }
    .cls-over{
        overflow-y: scroll;
        height: 67vh;
    }
    .cmd{
        padding: 5px;
        background-image: linear-gradient(#f2f2f2,#c2c2c2);
        border: 1px solid #c2c2c2;
        border-radius: 2px;
        z-index: 99;
        margin-left: 5px;
    }
    hr{
        border-bottom: 0px;
        border-top: 1px solid lightgray;
    }
    .item {
        padding: 5px;
        text-align: right;
        border: 0px !important;
        outline: none;
    }
    .cls {
        background-color: orange;
    }
</style>

<table>
    <thead>
        <tr>
            <td colspan="3" style="text-align: left; border: 0px; font-weight: bold; background-color: orange ;"><span id="strHeader" style="text-transform: uppercase; color: white;"></span></td>
            <td colspan="4" style="text-align: right; border: 0px;font-style: italic;background-color: orange; color: white;">Đơn vị: triệu đồng, %, hộ, người</td>
        </tr>
        <tr>
            <th rowspan="3">STT</th>
            <th rowspan="3">CHỈ TIÊU</th>
            <th rowspan="3">Thực hiện đến 31/12/<span id="lbNamTH"></span></th>
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
            <tr class="cls<s:property value='D48'/> <s:property value='D50'/>">
                <td><s:property value='TT_HIENTHI'/></td>
                <td style="text-align: left; padding-left: 3px;"><s:property value='TEN'/></td>
                <td style="text-align: right; padding-right: 3px;"><input type="text" class="<s:property value='D50'/> item number cls<s:property value='D48'/>" style="width: 120px;" id="lstData[<s:property  value='%{#idxRows.index}' />].D13" name="lstData[<s:property  value='%{#idxRows.index}' />].D13" value="<s:property value='D13'/>" <s:property value='D48'/> onblur="autoPlus(<s:property value='%{#idxRows.index}'/>);"/></td>
                <td style="text-align: right; padding-right: 3px;"><input type="text" class="<s:property value='D50'/> item number cls<s:property value='D48'/>" style="width: 120px;" id="lstData[<s:property  value='%{#idxRows.index}' />].D14" name="lstData[<s:property  value='%{#idxRows.index}' />].D14" value="<s:property value='D14'/>" <s:property value='D48'/> onblur="autoPlus(<s:property value='%{#idxRows.index}'/>);"/></td>
                <td style="text-align: right; padding-right: 3px;"><input type="text" class="<s:property value='D50'/> item number cls<s:property value='D48'/>" style="width: 120px;" id="lstData[<s:property  value='%{#idxRows.index}' />].D15" name="lstData[<s:property  value='%{#idxRows.index}' />].D15" value="<s:property value='D15'/>" <s:property value='D48'/> onblur="autoPlus(<s:property value='%{#idxRows.index}'/>);"/></td>
                <td style="text-align: right; padding-right: 3px;"><input type="text" class="<s:property value='D50'/> item number cls<s:property value='D48'/>" style="width: 120px;" id="lstData[<s:property  value='%{#idxRows.index}' />].D16" name="lstData[<s:property  value='%{#idxRows.index}' />].D16" value="<s:property value='D16'/>" <s:property value='D48'/> onblur="autoPlus(<s:property value='%{#idxRows.index}'/>);"/></td>
                <td style="text-align: right; padding-right: 3px;"><input type="text" class="<s:property value='D50'/> item number2 cls<s:property value='D48'/>" style="width: 120px;" id="lstData[<s:property  value='%{#idxRows.index}' />].D17" name="lstData[<s:property  value='%{#idxRows.index}' />].D17" value="<s:property value='D17'/>" <s:property value='D48'/> onblur="autoPlus(<s:property value='%{#idxRows.index}'/>);"/></td>
                <!--Những trường dữ liệu cần lấy-->
                <td style="display: none;"><input type="text" class="item" id="lstData[<s:property  value='%{#idxRows.index}' />].TT_HIENTHI" name="lstData[<s:property  value='%{#idxRows.index}' />].TT_HIENTHI" value="<s:property value='TT_HIENTHI'/>" readonly/></td>
                <td style="display: none;"><input type="text" id="lstData[<s:property  value="%{#idxRows.index}" />].MA" name="lstData[<s:property  value="%{#idxRows.index}" />].MA" value="<s:property value='MA'/>" name="MA" readonly="readonly"/></td>
                <td style="display: none;"><span id="ngaybc"><s:property value='D49'/></span></td>
                <td style="display: none;"><span id="nguyennhan"></span></td>
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
        $("#lbNamTH").html($("#cboNam option:selected").val() - 2);
        $("#strNguyennhan").html($("#nguyennhan").text());
        $("#lbNamUoc").html($("#cboNam option:selected").val() - 1);
        $("#lbNamTD").html(parseInt($("#cboNam option:selected").val()));
        $("#lbTangGiam").html($("#cboNam option:selected").val() - 1);
    });
//Hàm xử lý tính toán cho 2 chỉ tiêu nguông kế hoạch B
    function autoPlus(idx) {
        var D139, D149, D159, D169, D179;
        var D13, D14, D15, D16, D17, indi;
        indi = document.getElementById("lstData[" + idx + "].MA").value;

        D13 = 0;
        D14 = 0;
        D15 = 0;
        D16 = 0;
        D17 = 0;
        if (["XD00110", "XD00111", "XD00038", "XD00039", "XD00040", "XD00041", "XD00042", "XD00043", "XD00044", "XD00045", "XD00046", "XD00047",
            "XD00048", "XD00049", "XD00050", "XD00051", "XD00052", "XD00053", "XD00054", "XD00055", "XD00056", "XD00057", "XD00058", "XD00059", "XD00087",
            "XD00060", "XD00061", "XD00062", "XD00063"].includes(indi)) {
            D13 = document.getElementById("lstData[" + idx + "].D13").value.replace(',', '');
            D14 = document.getElementById("lstData[" + idx + "].D14").value.replace(',', '');
            D16 = document.getElementById("lstData[" + idx + "].D16").value.replace(',', '');
            D15 = parseFloat(D14) + parseFloat(D16);
            document.getElementById("lstData[" + idx + "].D15").value = D15;
            D17 = (D16 / D14) * 100;
            document.getElementById("lstData[" + idx + "].D17").value = D17;
        }
        D139 = 0;
        D149 = 0;
        D159 = 0;
        D169 = 0;
        D179 = 0;
        if (["XD00110", "XD00111"].includes(indi)) {
            D139 = parseFloat(document.getElementById("lstData[10].D13").value.replaceAll(',', '')) + parseFloat(document.getElementById("lstData[11].D13").value.replaceAll(',', ''));
            D149 = parseFloat(document.getElementById("lstData[10].D14").value.replaceAll(',', '')) + parseFloat(document.getElementById("lstData[11].D14").value.replaceAll(',', ''));
            D169 = parseFloat(document.getElementById("lstData[10].D16").value.replaceAll(',', '')) + parseFloat(document.getElementById("lstData[11].D16").value.replaceAll(',', ''));
            D159 = D149 + D169;
            D179 = (D169 / D149) * 100;
            document.getElementById("lstData[9].D13").value = D139;
            document.getElementById("lstData[9].D14").value = D149;
            document.getElementById("lstData[9].D15").value = D159;
            document.getElementById("lstData[9].D16").value = D169;
            document.getElementById("lstData[9].D17").value = D179;
        }


        D139 = 0;
        D149 = 0;
        D159 = 0;
        D169 = 0;
        D179 = 0;
        if (["XD00038", "XD00039", "XD00040", "XD00041", "XD00042", "XD00043", "XD00044", "XD00045", "XD00046", "XD00047", "XD00048", "XD00049", "XD00050", "XD00051", "XD00052", "XD00053", "XD00054", "XD00055", "XD00056", "XD00057", "XD00058", "XD00059", "XD00087", "XD00060", "XD00061", "XD00062", "XD00063"].includes(indi)) {
            for (var i = 51; i < 78; i++) {
                D139 += parseFloat(document.getElementById("lstData[" + i + "].D13").value.replaceAll(',', ''));
                D149 += parseFloat(document.getElementById("lstData[" + i + "].D14").value.replaceAll(',', ''));
                D169 += parseFloat(document.getElementById("lstData[" + i + "].D16").value.replaceAll(',', ''));
            }
            D159 = D149 + D169;
            D179 = (D169 / D149) * 100;
            document.getElementById("lstData[50].D13").value = D139;
            document.getElementById("lstData[50].D14").value = D149;
            document.getElementById("lstData[50].D15").value = D159;
            document.getElementById("lstData[50].D16").value = D169;
            document.getElementById("lstData[50].D17").value = D179;
        }

        //Cập nhật lại cho chỉ tiêu II
        document.getElementById("lstData[12].D13").value = parseInt(document.getElementById("lstData[13].D13").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[50].D13").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[78].D13").value.replaceAll(',', ''));
        document.getElementById("lstData[12].D14").value = parseInt(document.getElementById("lstData[13].D14").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[50].D14").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[78].D14").value.replaceAll(',', ''));
        document.getElementById("lstData[12].D15").value = parseInt(document.getElementById("lstData[13].D15").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[50].D15").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[78].D15").value.replaceAll(',', ''));
        document.getElementById("lstData[12].D16").value = parseInt(document.getElementById("lstData[13].D16").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[50].D16").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[78].D16").value.replaceAll(',', ''));
        document.getElementById("lstData[12].D17").value = parseInt(document.getElementById("lstData[13].D17").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[50].D17").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[78].D17").value.replaceAll(',', ''));

        //Cập nhật các chỉ tiêu theo công thức mới ban KHNV gửi ngày 20/07/2021
        document.getElementById("lstData[3].D13").value = parseInt(document.getElementById("lstData[4].D13").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[8].D13").value.replaceAll(',', ''));
        document.getElementById("lstData[3].D14").value = parseInt(document.getElementById("lstData[4].D14").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[8].D14").value.replaceAll(',', ''));
        document.getElementById("lstData[3].D15").value = parseInt(document.getElementById("lstData[4].D15").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[8].D15").value.replaceAll(',', ''));
        document.getElementById("lstData[3].D16").value = parseInt(document.getElementById("lstData[4].D16").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[8].D16").value.replaceAll(',', ''))
        document.getElementById("lstData[3].D17").value = parseInt(document.getElementById("lstData[4].D17").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[8].D17").value.replaceAll(',', ''));

        document.getElementById("lstData[2].D13").value = parseInt(document.getElementById("lstData[12].D13").value.replaceAll(',', '')) - parseInt(document.getElementById("lstData[9].D13").value.replaceAll(',', '')) - parseInt(document.getElementById("lstData[3].D13").value.replaceAll(',', ''));
        document.getElementById("lstData[2].D14").value = parseInt(document.getElementById("lstData[12].D14").value.replaceAll(',', '')) - parseInt(document.getElementById("lstData[9].D14").value.replaceAll(',', '')) - parseInt(document.getElementById("lstData[3].D14").value.replaceAll(',', ''));
        document.getElementById("lstData[2].D15").value = parseInt(document.getElementById("lstData[12].D15").value.replaceAll(',', '')) - parseInt(document.getElementById("lstData[9].D15").value.replaceAll(',', '')) - parseInt(document.getElementById("lstData[3].D15").value.replaceAll(',', ''));
        document.getElementById("lstData[2].D16").value = parseInt(document.getElementById("lstData[12].D16").value.replaceAll(',', '')) - parseInt(document.getElementById("lstData[9].D16").value.replaceAll(',', '')) - parseInt(document.getElementById("lstData[3].D16").value.replaceAll(',', ''));
        document.getElementById("lstData[2].D17").value = parseInt(document.getElementById("lstData[12].D17").value.replaceAll(',', '')) - parseInt(document.getElementById("lstData[9].D17").value.replaceAll(',', '')) - parseInt(document.getElementById("lstData[3].D17").value.replaceAll(',', ''));

        document.getElementById("lstData[1].D13").value = parseInt(document.getElementById("lstData[2].D13").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[3].D13").value.replaceAll(',', ''));
        document.getElementById("lstData[1].D14").value = parseInt(document.getElementById("lstData[2].D14").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[3].D14").value.replaceAll(',', ''));
        document.getElementById("lstData[1].D15").value = parseInt(document.getElementById("lstData[2].D15").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[3].D15").value.replaceAll(',', ''));
        document.getElementById("lstData[1].D16").value = parseInt(document.getElementById("lstData[2].D16").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[3].D16").value.replaceAll(',', ''))
        document.getElementById("lstData[1].D17").value = parseInt(document.getElementById("lstData[2].D17").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[3].D17").value.replaceAll(',', ''));


        //Cập nhật lại cho chỉ tiêu I
        document.getElementById("lstData[0].D13").value = parseInt(document.getElementById("lstData[1].D13").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[9].D13").value.replaceAll(',', ''));
        document.getElementById("lstData[0].D14").value = parseInt(document.getElementById("lstData[1].D14").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[9].D14").value.replaceAll(',', ''));
        document.getElementById("lstData[0].D15").value = parseInt(document.getElementById("lstData[1].D15").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[9].D15").value.replaceAll(',', ''));
        document.getElementById("lstData[0].D16").value = parseInt(document.getElementById("lstData[1].D16").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[9].D16").value.replaceAll(',', ''));
        document.getElementById("lstData[0].D17").value = parseInt(document.getElementById("lstData[1].D17").value.replaceAll(',', '')) + parseInt(document.getElementById("lstData[9].D17").value.replaceAll(',', ''));

        $('.number').number(true, 0);
        $('.number2').number(true, 2);
        
        //Cập nhật lại toàn bộ phần tính % ước D17
        for (var i = 0; i < $(".number ").size(); i++) {
            let mauso, element;
            element = document.getElementById("lstData[" + i + "].D14");
            if (element != null) {
                document.getElementById("lstData[" + i + "].D17").value = (parseInt(document.getElementById("lstData[" + i + "].D16").value.replaceAll(',', '')) / parseInt(document.getElementById("lstData[" + i + "].D14").value.replaceAll(',', ''))) * 100;
            }
        }
    }
    autoPlus(11);
</script>