<%-- 
    Document   : ViewData
    Created on : May 17, 2022, 9:56:22 AM
    Author     : NGUYEN THANH TRUNG
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
<script>
    var max_row = 0;
</script>
<style>
    .cssDate {
        text-align: right;
    }
</style>
<form id="frmFeedback">
    <input type="hidden" name="vsbpMakh" value="<s:property value='vsbpMakh'/>">
    <input type="hidden" name="vsbpNgayBC" value="<s:property value='vsbpNgayBC'/>">
    <input type="hidden" name="vsbpMaPgd" value="<s:property value='vsbpMaPgd'/>">
    <table id="tblThanhVien" style="width: 100%; text-align: center;">
        <tr style="text-align: center;">
            <th colspan="6" style="text-align: center; color: #07B200;">Phản hồi hộ vay chuyển đến với khách hàng: <s:property value='vsbpTenKh'/> (<s:property value='vsbpMakh'/>)</th>
        </tr>
        <tr>
        <td>
            <label>Nội dung phản hồi:</label>
        </td>
        </tr>
        <tr>
            <td>
                <textarea  class="autoHeight" style="width: 500px; height: 100px;" readonly="true" ><s:property value='currentFeedback'/></textarea>
            </td>
        </tr>
        <s:if test="flag == 1">
            <tr>
            <td>
                <label>Thay đổi:</label>
            </td>
            </tr>
            <tr>
                <td>                                
                    <textarea maxlength="250" name="feedback" class="autoHeight" style="width: 500px; height: 100px;"> </textarea>
                </td>
            </tr>
            <tr style="text-align: center;">            
                <th colspan="6" style="text-align: center;">

                    <input type="button" value="Lưu dữ liệu" name="cmdLuu" id="cmdLuu"/>
                </th>
            </tr>
        </s:if>        
    </table>
</form>
<script>       
    //Tìm dữ liệu
    $("#cmdLuu").click(function () {
        let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
        if (aCheck) {
            var url, sdata;
            url = "clhUpdateFeedback.action";
            sdata = jQuery("#frmFeedback").serialize();
            $.ajax({
                type: "POST",
                url: url,
                data: sdata,
                success: function (data) {
                    if (data !== "0") {
                        alert("Thành công: Lưu dữ liệu.");
                        window.opener.document.getElementById('idSearch').click();
                        window.close();
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
    
    $("#cmdClear").click(function () {
        let aCheck = confirm("Bạn chắc chắn muốn xóa toàn bộ thành viên ?");
        if (aCheck) {
            var url, sdata;
            url = "clearClhMember.action";
            sdata = jQuery("#frmAddThanhVien").serialize();
            $.ajax({
                type: "POST",
                url: url,
                data: sdata,
                success: function (data) {
                    if (data === "200") {
                        alert("Thành công: Xóa dữ liệu.");
                        window.opener.document.getElementById('idSearch').click();
                        window.close();
                    } else {
                        alert("Lỗi: Xóa dữ liệu.");
                    }
                },
                error: function (request) {
                    alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                }
            });
        }
    });       
</script>
