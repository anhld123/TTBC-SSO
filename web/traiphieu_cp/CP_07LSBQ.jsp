<%@taglib prefix="s" uri="/struts-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="js/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" href="js/3.6.0/jquery-ui.css">
        <script src="js/3.6.0/jquery-ui.js"></script>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>

        <style>
            .styled-table {
                border-collapse: collapse;
                font-size: 0.9em;
                font-family: Tahoma;
                width: 97%;
                border: 1px solid #ffcdbb;
            }
            .styled-table thead tr {
                background-color: #ffcdbb;
                text-align: center;
            }
            .styled-table th,
            .styled-table td {
                border: 1px solid #b2b2b2;
                padding: 5px 15px;
            }
            .styled-table tbody tr:last-of-type {
                border-bottom: 3px solid #ffcdbb;
            }
            .noneBorder{
                border: 0px;
                outline: none;
                background: transparent;
                width: 100%;
            }
            .cssBorder{
                background-color: lightgrey;
            }
        </style>
    </head>
    <body>
        <form id="frmCPMain">
            <div style="width: 98.5%; padding: 10px 0px; text-align: right;"><input type="button" value="Lưu dữ liệu" name="idSave" id="idSave"></div>
            <div style="width: 100%; padding: 0px 20px;">
                <table class="styled-table">
                    <thead>
                        <tr>
                            <th colspan="6">KỲ HẠN VÀ LÃI SUẤT PHÁT HÀNH TRÁI PHIẾU NHCSXH</th>
                        </tr>
                        <tr>
                            <th rowspan="5">Sửa</th>
                            <th rowspan="5">Năm</th>
                            <th colspan="4">Các loại phí phả trả</th>
                        </tr>
                        <tr>
                            <th rowspan="2">Tổng số</th>
                            <th colspan="3">Trong đó</th>
                        </tr>
                        <tr>
                            <th>Phí đấu thầu</th>
                            <th>Phí thanh toán</th>
                            <th>Phí bảo lãnh</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator value="ModelList" status="status">     
                            <tr <s:if test="!D5.equalsIgnoreCase('1')"> class="cssBorder"</s:if> id="idRow<s:property  value='%{#status.index}' />">
                                <td style="display: none;"><input type="text" value="<s:property value='NAMBC'/>" name="loadListYear" readonly="readonly"/></td>
                                <td style="display: none;"><input type="text" value="<s:property value='KHOA'/>" name="ModelList[<s:property  value='%{#status.index}' />].KHOA" readonly="readonly"/></td>
                                <td style="display: none;"><input type="text" value="<s:property value='MACN'/>" name="ModelList[<s:property  value='%{#status.index}' />].MACN" readonly="readonly"/></td>
                                <td style="display: none;"><input type="text" value="<s:property value='MAPGD'/>" name="ModelList[<s:property  value='%{#status.index}' />].MAPGD" readonly="readonly"/></td>
                                <td style="display: none;"><input type="text" value="<s:property value='D5'/>" name="selectD5" readonly="readonly"/></td>
                                <td ><input type="checkbox" onclick="funCheck(this,<s:property  value='%{#status.index}'/>)" <s:if test="D5.equalsIgnoreCase('1')"> checked disabled</s:if>/></td>
                                <td id="lstSelect<s:property  value='%{#status.index}' />"></td>
                                <td><input type="text" id="D1<s:property  value='%{#status.index}' />" value="<s:property value='D1'/>" name="ModelList[<s:property  value='%{#status.index}' />].D1" class="noneBorder" <s:if test="!D5.equalsIgnoreCase('1')"> disabled></s:if></td>
                                <td><input type="text" id="D2<s:property  value='%{#status.index}' />" value="<s:property value='D2'/>" name="ModelList[<s:property  value='%{#status.index}' />].D2" class="noneBorder" <s:if test="!D5.equalsIgnoreCase('1')"> disabled></s:if></td>
                                <td><input type="text" id="D3<s:property  value='%{#status.index}' />" value="<s:property value='D3'/>" name="ModelList[<s:property  value='%{#status.index}' />].D3" class="noneBorder" <s:if test="!D5.equalsIgnoreCase('1')"> disabled></s:if></td>
                                <td><input type="text" id="D4<s:property  value='%{#status.index}' />" value="<s:property value='D4'/>" name="ModelList[<s:property  value='%{#status.index}' />].D4" class="noneBorder" <s:if test="!D5.equalsIgnoreCase('1')"> disabled></s:if></td>
                                </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div> 
        </form>
        <script>
            const curYear = new Date().getFullYear();
            const mSize = $("input[name='loadListYear']").length;
            for (var j = 0; j < mSize; j++) {
                let chkVal = $("input[name='loadListYear']").eq(j).val();
                let chkD5 = $("input[name='selectD5']").eq(j).val();
                let disa = "";
                if (chkD5 == '1') {
                    disa = "";
                } else {
                    disa = 'disabled';
                }
                let opt = "";
                for (var i = curYear; i >= 2003; i--) {
                    if (i == chkVal) {
                        opt += '<option value="' + i + '" selected>' + i + '</option>';
                    } else {
                        opt += '<option value="' + i + '">' + i + '</option>';
                    }
                }
                $("#lstSelect" + j).html('<select name="ModelList[' + j + '].NAMBC" class="noneBorder" ' + disa + ' id="namBC'+ j +'">' + opt + '</select>');
            }
            function funCheck(elm,index) {
                if (elm.checked == true){
                    $("#idRow" + index).removeClass('cssBorder');
                    $("#D1" + index).removeAttr('disabled');
                    $("#D2" + index).removeAttr('disabled');
                    $("#D3" + index).removeAttr('disabled');
                    $("#D4" + index).removeAttr('disabled');
                    $("#namBC" + index).removeAttr('disabled');
                }else{
                    $("#idRow" + index).addClass('cssBorder');   
                    $("#D1" + index).prop('disabled','true');
                    $("#D2" + index).prop('disabled','true');
                    $("#D3" + index).prop('disabled','true');
                    $("#D4" + index).prop('disabled','true');
                    $("#namBC" + index).prop('disabled','true');
                };
            }
            //Lưu dữ liệu
            $("#idSave").click(function () {
                let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                if (aCheck) {
                    var url, sdata;
                    url = "saveCP07LSBQ.action";
                    sdata = jQuery("#frmCPMain").serialize();
                    $.ajax({
                        type: "POST",
                        url: url,
                        data: sdata,
                        success: function (data) {
                            if (data === "200") {
                                alert("Thành công: Lưu dữ liệu.");
                            } else {
                                alert("Lỗi: Lưu dữ liệu.");
                            }
                        },
                        error: function (request) {
                            alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                        }
                    });
                }
            });
        </script>
    </body>
</html>

