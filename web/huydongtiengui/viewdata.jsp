<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

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
                    <td class="clsChon">
                        <s:if test="%{D10 != null}">
                            <input type="checkbox" id="chkChonSh" name="chkChonSh" value='<s:property value="D3"/>' onclick="setChecked('<s:property value="D3"/>')" class="chkChonSh" checked>
                        </s:if>
                        <s:else>
                            <input type="checkbox" id="chkChonSh" name="chkChonSh" value='<s:property value="D3"/>' onclick="setChecked('<s:property value="D3"/>')" class="chkChonSh">
                        </s:else>
                    </td>
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
    <div style="display: none;">
        <s:iterator value="lstData" status="rowstatus">
            <s:if test="%{D10 != null}">
                <input type="checkbox" id="<s:property value="D3"/>" name="chkChon" value='<s:property value="D3"/>' checked>
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
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" value='<s:property value="D7"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" value='<s:property value="D8"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value='<s:property value="D9"/>' readonly="readonly">
            <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" value='<s:property value="D10"/>' readonly="readonly">
        </s:iterator>
        <input type="text" value="<s:property value="displaNone"/>" name="chkDate" id="chkDate">        
    </div>
</div>
<script>
    function setChecked(id) {
        if ($('#' + id).prop('checked')) {
            $('#' + id).prop('checked', false);
        } else {
            $('#' + id).prop('checked', true);
        }
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
                "sPrevious": "Sau",
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
            $("input.chkChonSh").attr("disabled", true);
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

    $('.number').number(true, 0);
</script>
