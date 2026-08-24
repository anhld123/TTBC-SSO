<%-- 
    Document   : viewcontent anhld
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<style>
    #subTable {
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        width: 100%;
        margin-left: 5px;
    }
    #subTable td, #subTable th {
        border: 1px solid #ddd;
        padding: 6px 8px;
        font-size: 9px;
    }
    #subTable tr:nth-child(even) { background-color: #f2f2f2; }
    #subTable tr:hover { background-color: #ddd; }
    #subTable th {
        padding-top: 8px;
        padding-bottom: 8px;
        text-align: left;
        background-color: #4CAF50;
        color: white;
    }
    .pagination-wrapper {
        margin: 8px 5px;
        font-family: tahoma;
        font-size: 12px;
        display: flex;
        align-items: center;
        gap: 6px;
        user-select: none;
    }
    .pg-btn {
        background-color: #f8f9fa;
        border: 1px solid #ddd;
        color: #333;
        padding: 3px 8px;
        cursor: pointer;
        border-radius: 3px;
        font-size: 11px;
    }
    .pg-btn:hover { background-color: #e9ecef; border-color: #ccc; }
    .pg-btn:active { background-color: #dee2e6; }
    .pg-info { font-weight: bold; color: #2e7d32; padding: 0 2px; }
</style>
<div style="margin: 5px; display: flex; align-items: center;">
    <button type="button" id="btnExport" style="margin-right: 10px;" onclick="exportExcelWithLoading();">
        Xuất Excel
    </button>
    <span id="exportLoading" style="display: none; color: #d32f2f; font-weight: bold; font-size: 12px;">
        <img src='imgs/Preloader_3.gif' style="width: 14px; height: 14px; vertical-align: middle;" /> Đang chuẩn bị file Excel, vui lòng chờ...
    </span>
</div>
<div class="pagination-wrapper" id="customPagerNav">
    <button type="button" class="pg-btn" onclick="customPager.prev()">&laquo; Trước</button>
    <span>Trang</span>
    <span id="currentPgText" class="pg-info">1</span>
    <span>/</span>
    <span id="totalPgText" class="pg-info">1</span>
    <button type="button" class="pg-btn" onclick="customPager.next()">Sau &raquo;</button>
</div>
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
<iframe id="downloadFrame" style="display:none;"></iframe>
<script type="text/javascript">
    function CustomPager(tableName, itemsPerPage) {
        this.tableName = tableName;
        this.itemsPerPage = itemsPerPage;
        this.currentPage = 1;
        this.pages = 0;
        this.inited = false;
        this.showRecords = function (from, to) {
            var rows = document.getElementById(tableName).rows;
            for (var i = 1; i < rows.length; i++) {
                if (i < from || i > to) {
                    rows[i].style.display = 'none';
                } else {
                    rows[i].style.display = '';
                }
            }
        };
        this.showPage = function (pageNumber) {
            if (!this.inited) return;
            if (pageNumber < 1) pageNumber = 1;
            if (pageNumber > this.pages) pageNumber = this.pages;
            this.currentPage = pageNumber;
            var from = (pageNumber - 1) * this.itemsPerPage + 1;
            var to = from + this.itemsPerPage - 1;
            this.showRecords(from, to);
            document.getElementById("currentPgText").innerText = this.currentPage;
            document.getElementById("totalPgText").innerText = this.pages;
        };
        this.prev = function () {
            if (this.currentPage > 1) { this.showPage(this.currentPage - 1); }
        };
        this.next = function () {
            if (this.currentPage < this.pages) { this.showPage(this.currentPage + 1); }
        };
        this.init = function () {
            var rows = document.getElementById(tableName).rows;
            var records = rows.length - 1;
            this.pages = Math.ceil(records / this.itemsPerPage);
            if (this.pages < 1) this.pages = 1;
            this.inited = true;
            this.showPage(1);
        };
    }
    var customPager = new CustomPager('subTable', 20);
    customPager.init();
    function exportExcelWithLoading() {
        var $loading = $("#exportLoading");
        var $btn = $("#btnExport");
        $loading.show();
        $btn.prop("disabled", true);
        var sdata = $("#frmmain").serialize();
        var url = "exportExcel_DTTN.action?" + sdata;
        document.getElementById("downloadFrame").src = url;
        setTimeout(function() {
            $loading.hide();
            $btn.prop("disabled", false);
        }, 3000);
    }
</script>