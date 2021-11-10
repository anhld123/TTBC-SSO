<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<div class="clsBody">
    <table id="tblTable">
        <tr>
            <th style="width: 20px;"><input type="checkbox" id="chkAll" name="chkAll" value="AA"></th>
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
        <s:iterator value="lstData">
            <tr>
                <td class="clsChon"><input type="checkbox" id="chkChon" name="chkChon" value='<s:property value="D3"/>'></td>
                <td><s:property value="D1"/></td>
                <td><s:property value="D2"/></td>
                <td><s:property value="D3"/></td>
                <td><s:property value="D4"/></td>
                <td><s:property value="D5"/></td>
                <td><s:property value="D6"/></td>
                <td><s:property value="D7"/></td>
                <td><s:property value="D8"/></td>
                <td><s:property value="D9"/></td>
                <td><s:property value="D10"/></td>
            </tr>
        </s:iterator>
    </table>
</div>

<script>
    $("#chkAll").click(function () {
        $('input[name="chkChon"]').not(this).prop('checked', this.checked);
    });
    
    $("td").click(function (e) {
        var chk = $(this).closest('tr').find('input:checkbox').get(0);
        if (e.target != chk)
        {
            chk.checked = !chk.checked;
        }
    });
</script>
