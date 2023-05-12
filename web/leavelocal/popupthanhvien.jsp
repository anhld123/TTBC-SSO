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
<script>
    var max_row = 0;
</script>
<style>
    .cssDate {
        text-align: right;
    }
</style>
<form id="frmAddThanhVien">
    <input type="hidden" name="vsbpMakh" value="<s:property value='vsbpMakh'/>">
    <input type="hidden" name="vsbpNgayBC" value="<s:property value='vsbpNgayBC'/>">
    <table id="tblThanhVien">
        <tr style="text-align: center;">
            <th colspan="6" style="text-align: center; color: #07B200;">Khách hàng: <s:property value='vsbpTenKh'/> (<s:property value='vsbpMakh'/>)</th>
        </tr>
        <tr>
<!--            <th>STT</th>-->
            <th>Tên thành viên</th>
            <th>Quan hệ</th>
            <th>Ngày sinh</th>
            <th>CCCD/CMT</th>
            <th>Số điện thoại</th>
            <th>Chỉnh sửa</th>
        </tr>
        <s:iterator value="lstData" status="idxRows">
            <tr class="tr_clone">
                <td class="txtBody">
                    <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D12" value="<s:property value='D12'/>">
                </td>
                <td>
                    <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D15" value="<s:property value='D15'/>">                           
                </td>
                <td>
                    <input type="text" class="cssDate" name="lstData[<s:property  value='%{#idxRows.index}' />].D13" value="<s:property value='D13'/>">                           
                </td>
                <td>
                    <input type="text" style="text-align: right;" name="lstData[<s:property  value='%{#idxRows.index}' />].D14" value="<s:property value='D14'/>">                           
                </td>
                <td>
                    <input type="text" style="text-align: right;" name="lstData[<s:property  value='%{#idxRows.index}' />].D16" value="<s:property value='D16'/>">                           
                </td>
                <td align = "center" class="TD_TEN_KH">
                    <s:if test="#idxRows.index == 0">
                        <input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex,<s:property  value="MA"/>)" class="TEN_KH"/>
                    </s:if>
                    <s:else>
                        <input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                    </s:else> 
                </td>
            </tr>
        </s:iterator>
        <tr style="text-align: center;">            
            <th colspan="6" style="text-align: center;">
                <p id="lblTotal" style="color: #ff3333;">Tổng số:</p>
                <input type="button" value="Lưu dữ liệu" name="cmdLuu" id="cmdLuu"/>
            </th>
        </tr>
    </table>
</form>
<script>
    
    function deleteRow(indx) {
        var table = document.getElementById("tblThanhVien");
        var rowCount = table.rows.length - 3; //Dem so dong cua bang
        if (max_row < rowCount)
        {
            max_row = rowCount;
        }
        max_row--;
        table.deleteRow(indx);
        $("#lblTotal").text("Tổng số thành viên: " + (table.rows.length - 3));
    }

    function addRow(indx, ma) {
        var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
        var table = document.getElementById("tblThanhVien");
        var rowCount = table.rows.length - 3; //Dem so dong cua bang
        if (max_row < rowCount)
        {
            max_row = rowCount;
        }
        else
        {
            max_row++;
            rowCount = max_row;
        }       
        var newTr = '<tr class="tr_clone">\n\
                        <td class="txtBody"><input type="text" name="lstData['+max_row+'].D12" ></td>\n\
                        <td class="txtBody"><input type="text" name="lstData['+max_row+'].D15" ></td>\n\
                        <td class="txtBody"><input type="text" class="cssDate" name="lstData['+max_row+'].D13" ></td>\n\
                        <td class="txtBody"><input type="text" style="text-align: right;" name="lstData['+max_row+'].D14" ></td>\n\
                        <td class="txtBody"><input type="text" style="text-align: right;" name="lstData['+max_row+'].D16" ></td>\n\
                        <td align = "center" class="TD_TEN_KH"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                    </tr>';        
       $($('table#tblThanhVien tr')[table.rows.length - 2]).after(newTr);
       $("#lblTotal").text("Tổng số thành viên: " + (table.rows.length - 3));
       setCssStyle();
    }
    
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
    
    $(function () {
       var table = document.getElementById("tblThanhVien");
       $("#lblTotal").text("Tổng số thành viên: " + (table.rows.length - 3));
    });
    
    
    $(function () {
      setCssStyle();                 
    });
    
    function setCssStyle(){
        $(".cssDate").datepicker(
        {
            dateFormat: 'dd/mm/yy', 
            showOn: "button",
            buttonImage: "img/icon-ui_datepicker.png",
            buttonImageOnly: true,
           // dateFormat: 'dd/mm/yy',
            showButtonPanel: true,
            buttonText: "icono",
            changeMonth: true,
            changeYear: true                    
        }); 
    }
</script>
