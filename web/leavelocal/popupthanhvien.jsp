<%-- 
    Document   : ViewData
    Created on : May 17, 2022, 9:56:22 AM
    Author     : NGUYEN PHU VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
    <script src="js/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="js/3.6.0/jquery-ui.css">
    <script src="js/3.6.0/jquery-ui.js"></script>
    <script src="js/jquery.number.js"></script>
    <script src="js/format_num.js"></script>
</head>
<form id="frmAddThanhVien">
    <input type="hidden" name="vsbpMakh" value="<s:property value='vsbpMakh'/>">
    <input type="hidden" name="vsbpNgayBC" value="<s:property value='vsbpNgayBC'/>">
    <table>
        <tr>
            <th colspan="5">Mã khách hàng: <s:property value='vsbpMakh'/></th>
        </tr>
        <tr>
            <th>STT</th>
            <th>Tên thành viên</th>
            <th>Quan hệ</th>
            <th>Ngày sinh</th>
            <th>CCCD/CMT</th>
            <th>Số điện thoại</th>
        </tr>
        <s:iterator value="lstData" status="idxRows">
            <tr class="tr_clone">
                <td class="txtBody">
                    <s:property  value='%{#idxRows.index + 1}' />
                </td>
                <td class="txtBody">
                    <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D12" value="<s:property value='D12'/>">
                </td>
                <td>
                    <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D15" value="<s:property value='D15'/>">                           
                </td>
                <td>
                    <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D13" value="<s:property value='D13'/>">                           
                </td>
                <td>
                    <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D14" value="<s:property value='D14'/>">                           
                </td>
                <td>
                    <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D16" value="<s:property value='D16'/>">                           
                </td>
            </tr>
        </s:iterator>
        <tr>
            <th colspan="6" style="text-align: right;">
                <input type="button" value="Lưu dữ liệu" name="cmdLuu" id="cmdLuu"/>
            </th>
        </tr>
    </table>
</form>
<script>
    //Tìm dữ liệu
    $("#cmdLuu").click(function () {
        let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
        if (aCheck) {
            var url, sdata;
            url = "addRemoveTV.action";
            sdata = jQuery("#frmAddThanhVien").serialize();
            $.ajax({
                type: "POST",
                url: url,
                data: sdata,
                success: function (data) {
                    if (data === "200") {
                        alert("Thành công: Lưu dữ liệu.");
                        window.opener.document.getElementById('idSearch').click();
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
