<%-- 
    Document   : viewcontent
    Created on : Oct 8, 2015, 10:00:23 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.18.5/xlsx.full.min.js"></script>-->
<style>
    #subTable {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 100%;
        margin-left: 5px;
    }

    #subTable td, #subTable th {
        border: 1px solid #ddd;
        padding: 8px;
        font-size: 9px;
    }

    #subTable tr:nth-child(even){background-color: #f2f2f2;}

    #subTable tr:hover {background-color: #ddd;}

    #subTable th {
        padding-top: 12px;
        padding-bottom: 12px;
        text-align: left;
        background-color: #4CAF50;
        color: white;
    }
</style>
<button type="button" style="margin: 5px;"
        onclick="this.form.action = 'exportExcel_DTTN.action'; this.form.submit();">
    Xuất Excel
</button>
<table border="1" class="editDelete" id="subTable">  

    <tr class="D0">
        <th style="text-align:center;font-weight:bold">HỌ TÊN</th>
        <th style="text-align:center;font-weight:bold">NGÀY SINH</th>
        <th style="text-align:center;font-weight:bold">NƠI ĐK HKTT</th>
        <th style="text-align:center;font-weight:bold">CCCD</th>
        <th style="text-align:center;font-weight:bold">HỘ CHIẾU</th>
        <th style="text-align:center;font-weight:bold">TỘI DANH</th>
        <th style="text-align:center;font-weight:bold">TÊN BỐ</th>
        <th style="text-align:center;font-weight:bold">TÊN MẸ</th>
        <th style="text-align:center;font-weight:bold">SỐ QĐ</th>
        <th style="text-align:center;font-weight:bold">NGÀY QĐ</th>
        <th style="text-align:center;font-weight:bold">ĐƠN VỊ</th>
        <th style="text-align:center;font-weight:bold">LOẠI TN</th>
        <th style="text-align:center;font-weight:bold">TÊN KO DẤU</th>
        <th style="text-align:center;font-weight:bold">BÍ DANH</th>
        <th style="text-align:center;font-weight:bold">GIỚI TÍNH</th>
        <th style="text-align:center;font-weight:bold">DÂN TỘC</th>
        <th style="text-align:center;font-weight:bold">TÔN GIÁO</th>
        <th style="text-align:center;font-weight:bold">QUỐC TỊCH</th>
        <th style="text-align:center;font-weight:bold">NƠI SINH</th>
        <th style="text-align:center;font-weight:bold">QUÊ QUÁN</th>
        <th style="text-align:center;font-weight:bold">CHỨC VỤ</th>
        <th style="text-align:center;font-weight:bold">THÔNG TIN KHÁC</th>
        <th style="text-align:center;font-weight:bold">TỔ CHỨC KHỦNG BỐ</th>
        <th style="text-align:center;font-weight:bold">CHỨC VỤ 2</th>
        <th style="text-align:center;font-weight:bold">CHỨC VỤ 3</th>
        <th style="text-align:center;font-weight:bold">POB BLOCK</th>
        <th style="text-align:center;font-weight:bold">THÔNG TIN NHẬP</th>
        <th style="text-align:center;font-weight:bold">NGUỒN</th>
        <th style="text-align:center;font-weight:bold">CMT NGÀY CẤP</th>
        <th style="text-align:center;font-weight:bold">CMT NƠI CẤP</th>
        <th style="text-align:center;font-weight:bold">HC NGÀY CẤP</th>
        <th style="text-align:center;font-weight:bold">HC NƠI CẤP</th>
    </tr>    
    <s:iterator value="#attr.lstCustomerBlackList" var="modelView" status="rowstatus">
        <tr>
            <td><s:property value="hoVaTen"/></td>
            <td><s:property value="ngaySinhh"/></td>
            <td><s:property value="noiDkyHktt"/></td>
            <td><s:property value="cccdHoChieu"/></td>
            <td><s:property value="hcNoiCap"/></td>
            <td><s:property value="toiDanh"/></td>
            <td><s:property value="hoTenBo"/></td>
            <td><s:property value="hoTenMe"/></td>
            <td><s:property value="soQdtn"/></td>
            <td><s:property value="ngayRaQdtn"/></td>
            <td><s:property value="dviCap2RaQdtn"/></td>
            <td><s:property value="loaiTn"/></td>
            <td><s:property value="hoTenNoAccent"/></td>
            <td><s:property value="biDanh"/></td>
            <td><s:property value="gioiTinh"/></td>
            <td><s:property value="danToc"/></td>
            <td><s:property value="tonGiao"/></td>
            <td><s:property value="quocTich"/></td>
            <td><s:property value="noiSinh"/></td>
            <td><s:property value="queQuan"/></td>
            <td><s:property value="chucVu"/></td>
            <td><s:property value="thongTinKhac"/></td>
            <td><s:property value="toChucKhungBo"/></td>
            <td><s:property value="chucVu2"/></td>
            <td><s:property value="chucVu3"/></td>
            <td><s:property value="pobBlock"/></td>
            <td><s:property value="thongTinNhap"/></td>
            <td><s:property value="nguon"/></td>
            <td><s:property value="cmtNgayCap"/></td>
            <td><s:property value="cmtNoiCap"/></td>
            <td><s:property value="hcNgayCap"/></td>
            <td><s:property value="hcNoiCap"/></td>
        </tr>
    </s:iterator>

</table>
<script>
//    function exportExcel() {
//        var table = document.getElementById("subTable");
//        var wb = XLSX.utils.table_to_book(table, {
//            sheet: "BlackList",
//            raw: true
//        });
//
//        // Ép tất cả cell về string
//        wb.Sheets["BlackList"] = forceString(wb.Sheets["BlackList"]);
//
//        XLSX.writeFile(wb, "customer_blacklist.xlsx");
//    }
//
//    function forceString(sheet) {
//        Object.keys(sheet).forEach(function (cell) {
//            if (cell[0] !== '!') {
//                sheet[cell].t = 's'; // string
//            }
//        });
//        return sheet;
//    }

    $("#exportExcel").click(function () {
        var sdata = $("#id_khnv2021").serialize();
        var url = "exportExcel_DTTN.action?" + sdata;
        document.getElementById("downloadFrame").src = url;
    });
</script>
