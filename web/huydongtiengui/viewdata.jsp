<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<style>
    .css_text{
        background-color: transparent;
        border: 0px;
        text-align: right;
        width: 100%;
        outline: none;
    }
</style>
<div class="clsBody">
    <table id="tblTable">
        <thead>
            <tr>
                <th style="width: 20px;">&nbsp;</th>
                <th>GL</th>
                <th>Số sổ</th>
                <th>Số TK</th>
                <th>Mã KH</th>
                <th>Tên KH</th>
                <th>Sản phẩm</th>
                <th>Số dư SK</th>
                <th>Số dư HĐ</th>
                <th>Kỳ hạn</th>
                <th>Cán bộ</th>
            </tr>
        </thead>
        <tbody>
            <s:iterator value="lstData">
                <tr>
                    <td class="clsChon">
                        <s:if test="%{D10 != null}">
                            <input type="checkbox" id="chkChonSh" name="chkChonSh" value='<s:property value="D3"/>' onclick="setChecked('<s:property value="D3"/>')" checked>
                        </s:if>
                        <s:else>
                            <input type="checkbox" id="chkChonSh" name="chkChonSh" value='<s:property value="D3"/>' onclick="setChecked('<s:property value="D3"/>')">
                        </s:else>
                    </td>
                    <td><s:property value="D1"/></td>
                    <td><s:property value="D2"/></td>
                    <td><s:property value="D3"/></td>
                    <td><s:property value="D4"/></td>
                    <td><s:property value="D5"/></td>
                    <td><s:property value="D6"/></td>
                    <td><input type="text" class='css_text number' value='<s:property value="D7"/>' readonly="readonly"></td>
                    <td><input type="text" class='css_text number' value='<s:property value="D8"/>' readonly="readonly"></td>
                    <td><s:property value="D9"/></td>
                    <td><s:property value="D10"/></td>
                </tr>
            </s:iterator>
        </tbody>
    </table>
    <div style="display: none;">
        <s:iterator value="lstData">
            <s:if test="%{D10 != null}">
                <input type="checkbox" id="<s:property value="D3"/>" name="chkChon" value='<s:property value="D3"/>' checked>
            </s:if>
            <s:else>
                <input type="checkbox" id="<s:property value="D3"/>" name="chkChon" value='<s:property value="D3"/>'>
            </s:else>
        </s:iterator>
    </div>
</div>
<script>

    function setChecked(id) {
        if ($('#' + id).prop('checked')) {
            $('#' + id).prop('checked', false);
        } else {
            $('#' + id).prop('checked', true);
        }
        ;
    }

    $('#tblTable').DataTable({
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        "pageLength": 25,
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
    });

    $('.number').number(true, 0);

</script>
