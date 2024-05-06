<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>

<style>
    .css_text{
        background-color: transparent;
        border: 0px;
        text-align: left;
        width: 100%;
        outline: none;
    }
    .css_text.number{
        text-align: right;
    }
    .dataFillter{
        display: none;
    }
</style>
<div class="clsBody">

    <table id="tblTable">
        <thead>
            <tr>
                <th style="width: 20px;">&nbsp;</th>
                <th>GL</th>
                <th>Số sổ</th>
                <th style="width: 100px;">Số TK</th>
                <th>Mã KH</th>
                <th style="width: 200px;">Tên KH</th>
                <th>Sản phẩm</th>
                <th>Ngày gán sổ</th>
                <th>Số dư SK</th>
                <th>Số dư HĐ</th>
                <th>Kỳ hạn</th>
                <th>Cán bộ</th>
                <th>Ngày gửi</th>
                <th class="dataFillter"></th>
                <th class="dataFillter"></th>
                <th class="dataFillter"></th>
                <th class="dataFillter"></th>
                <th class="dataFillter"></th>
                <th class="dataFillter"></th>
                <th class="dataFillter"></th>
                <th class="dataFillter"></th>
                <th class="dataFillter"></th>
                <th class="dataFillter"></th>
            </tr>
        </thead>
        <tbody>
            <s:iterator value="lstData">
                <tr>
                    <%--<s:if test="checkNgaybc.equals(checkNgaygs)">--%>
                    <s:if test="checkNgaybc < 2 ">
                        <td>
                            <s:if test="%{D10 != null}">
                                <input type="checkbox" id="1_chkChonSh" name="chkChonSh" value='<s:property value="D3"/>' onclick="setChecked('<s:property value="D3"/>')" class="chkChonSh" checked>
                            </s:if>
                            <s:else>
                                <input type="checkbox" id="2_chkChonSh" name="chkChonSh" value='<s:property value="D3"/>' onclick="setChecked('<s:property value="D3"/>')" class="chkChonSh">
                            </s:else>
                        </td>
                    </s:if>
                    <s:else>
                        <td>
                            <s:if test="%{D10 != null}">
                                <input type="checkbox" id="1_chkChonSh"  class="chkChonSh" name="chkChonSh" value='<s:property value="D3"/>' onclick="setChecked('<s:property value="D3"/>')"  checked >
                            </s:if>
                            <s:else>
                                <input type="hidden" disabled id="2_chkChonSh" class="chkChonSh" name="chkChonSh" value='<s:property value="D3"/>' onclick="setChecked('<s:property value="D3"/>')" >
                            </s:else>
                        </td>
                    </s:else>

                    <td><input type="text" class='css_text' value='<s:property value="D1"/>' readonly="readonly"></td>
                    <td><input type="text" class='css_text' value='<s:property value="D2"/>' readonly="readonly"></td>
                    <td><input type="text" class='css_text' value='<s:property value="D3"/>' readonly="readonly"></td>
                    <td><input type="text" class='css_text' value='<s:property value="D4"/>' readonly="readonly"></td>
                    <td><input type="text" class='css_text' value='<s:property value="D5"/>' readonly="readonly"></td>
                    <td><input type="text" class='css_text' value='<s:property value="D6"/>' readonly="readonly"></td>
                    <td><input type="text" class='css_text' value='<s:property value="D11"/>' readonly="readonly"></td>
                    <td><input type="text" class='css_text number' value='<s:property value="D7"/>' readonly="readonly"></td>
                    <td><input type="text" class='css_text number' value='<s:property value="D8"/>' readonly="readonly"></td>
                    <td><input type="text" class='css_text number' value='<s:property value="D9"/>' readonly="readonly"></td>
                    <td><input type="text" class='css_text' value='<s:property value="D10"/>' readonly="readonly"></td>
                    <td><input type="text" class='css_text' value='<s:property value="D12"/>' readonly="readonly"></td>
                    <td class="dataFillter"><s:property value="D1"/></td>
                    <td class="dataFillter"><s:property value="D2"/></td>
                    <td class="dataFillter"><s:property value="D3"/></td>
                    <td class="dataFillter"><s:property value="D4"/></td>
                    <td class="dataFillter"><s:property value="D5"/></td>
                    <td class="dataFillter"><s:property value="D6"/></td>
                    <td class="dataFillter"><s:property value="D7"/></td>
                    <td class="dataFillter"><s:property value="D8"/></td>
                    <td class="dataFillter"><s:property value="D9"/></td>
                    <td class="dataFillter"><s:property value="D10"/></td>
                </tr>
            </s:iterator>
        </tbody>
    </table>
    <div style="margin-top: 10px;">
        <table border ="1" cellspacing="0" cellpadding="5" style="border-collapse: collapse;">
            <tr style="background-color: coral;">
                <th>Số đã gán mã CB</th>
                <th>Sổ chưa gắn mã CB</th>
                <th>Dư TK sao kê đã gắn mã CB</th>
                <th>Dư TK sao kê chưa gắn mã CB</th>
                <th>Dư TK hợp đồng đã gắn mã CB</th>
                <th>Dư TK hợp đồng chưa gắn mã CB</th>
            </tr>
            <tr style="text-align: right;">
                <td id="sum01"></td>
                <td id="sum02"></td>
                <td id="sum03"></td>
                <td id="sum04"></td>
                <td id="sum05"></td>
                <td id="sum06"></td>
            </tr>
        </table>
    </div>
    <div style="display: none;">
        <s:iterator value="lstData" status="rowstatus">
            <s:if test="%{D10 != null}">
                <input type="checkbox" id="<s:property value="D3"/>" name="chkChon" value='<s:property value="D3"/>' checked >
            </s:if>
            <s:else>
                <input type="checkbox" id="<s:property value="D3"/>" name="chkChon" value='<s:property value="D3"/>'>
            </s:else>
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value='<s:property value="D1"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value='<s:property value="D2"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" value='<s:property value="D3"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" value='<s:property value="D4"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" value='<s:property value="D5"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" value='<s:property value="D6"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="DuTKSK" value='<s:property value="D7"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="DuTKHD" value='<s:property value="D8"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value='<s:property value="D9"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" value='<s:property value="D10"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" value='<s:property value="D12"/>' readonly="readonly">
        </s:iterator>
        <input type="text" value="<s:property value="displaNone"/>" name="chkDate" id="chkDate">        
    </div>
</div>
<script>
    var sum01 = sum02 = sum03 = sum04 = sum05 = sum06 = 0;
    function setChecked(id) {
        if ($('#' + id).prop('checked')) {
            $('#' + id).prop('checked', false);
        } else {
            $('#' + id).prop('checked', true);
        }
        sum01 = sum02 = sum03 = sum04 = sum05 = sum06 = 0;
        $(".chkChonSh").each(function (index) {
            if (this.checked === true) {
                sum01++;
                sum03 = sum03 + parseFloat($('.DuTKSK').eq(index).val());
                sum05 = sum05 + parseFloat($('.DuTKHD').eq(index).val());
            } else {
                sum02++;
                sum04 = sum04 + parseFloat($('.DuTKSK').eq(index).val());
                sum06 = sum06 + parseFloat($('.DuTKHD').eq(index).val());
            }
        });
        $("#sum01").html(number_format(sum01, 0, ',', '.'));
        $("#sum02").html(number_format(sum02, 0, ',', '.'));
        $("#sum03").html(number_format(sum03, 0, ',', '.') + ' VNĐ');
        $("#sum04").html(number_format(sum04, 0, ',', '.') + ' VNĐ');
        $("#sum05").html(number_format(sum05, 0, ',', '.') + ' VNĐ');
        $("#sum06").html(number_format(sum06, 0, ',', '.') + ' VNĐ');
    }

    var table = $('#tblTable').DataTable({
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        "pageLength": 10,
        "language": {
            "lengthMenu": "Hiện _MENU_ dòng / trang",
            "zeroRecords": "Không có dữ liệu",
            "info": "Trang số _PAGE_ của _PAGES_",
            "infoEmpty": "Không có dữ liệu",
            "infoFiltered": "(Tìm kiếm từ _MAX_ tổng số dòng)"
        },
        "sPaginationType": "full_numbers",
        "oLanguage": {
            "oPaginate": {
                "sFirst": "Đầu",
                "sPrevious": "Trước",
                "sLast": "Cuối",
                "sNext": "Sau"
            },
            "sSearch": "<span>Tìm kiếm:</span> _INPUT_" //search
        }
    });


    $(document).on('click', '.paginate_button', function (e) {
        e.preventDefault();
        $('.number').number(true, 0);
        if ($("#chkDate").val() === '200') {
            $("input.chkChonSh").removeAttr("disabled");
        } else {
            $("input.chkChonSh").prop("disabled", true);
        }
    });
    $("select[name='tblTable_length']").change(function (e) {
        e.preventDefault();
        $('.number').number(true, 0);
        if ($("#chkDate").val() === '200') {
            $("input.chkChonSh").removeAttr("disabled");
        } else {
            $("input.chkChonSh").attr("disabled", true);
        }
    });

    $(".chkChonSh").each(function (index) {
        if (this.checked === true) {
            sum01++;
            sum03 = sum03 + parseFloat($('.DuTKSK').eq(index).val());
            sum05 = sum05 + parseFloat($('.DuTKHD').eq(index).val());
        } else {
            sum02++;
            sum04 = sum04 + parseFloat($('.DuTKSK').eq(index).val());
            sum06 = sum06 + parseFloat($('.DuTKHD').eq(index).val());
        }
    });
    $("#sum01").html(number_format(sum01, 0, ',', '.'));
    $("#sum02").html(number_format(sum02, 0, ',', '.'));
    $("#sum03").html(number_format(sum03, 0, ',', '.') + ' VNĐ');
    $("#sum04").html(number_format(sum04, 0, ',', '.') + ' VNĐ');
    $("#sum05").html(number_format(sum05, 0, ',', '.') + ' VNĐ');
    $("#sum06").html(number_format(sum06, 0, ',', '.') + ' VNĐ');

    $('.number').number(true, 0);
</script>
