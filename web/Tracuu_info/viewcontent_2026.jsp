<%-- 
    Document   : viewcontent anhld
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<style>
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
    <button type="button" id="btnExport" style="margin-right: 10px;" onclick="fnExcelReport();">
        Xuất excel
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

<table id="tblcontent" style="width: auto;">
    <input type="hidden" value="<s:property value="maBc"/>" name="maBc" id="maBc"/> 
    <input type="hidden" value="<s:property value="tenBc"/>" name="tenBc" id="tenBc"/> 
    <tr><td>
        <table id="customers" class="sortable">
            <s:iterator value="listgt">
                <s:property value="listgt" escape="false"/>
            </s:iterator>
        </table>
    </td></tr>
</table>

<table id="tblexpcontent" cellspacing="0" cellpadding="0" border="0" style="display: none;">
    <tr><td>
        <table id="customers" class="sortable">
            <s:iterator value="listgt">
                <s:property value="listgt" escape="false"/>
            </s:iterator>
        </table>
    </td></tr>
</table>

<script src="Tracuu_info/sorttable.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/xlsx-js-style@1.2.0/dist/xlsx.bundle.min.js"></script>
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
            var currElem = document.getElementById("currentPgText");
            var totElem = document.getElementById("totalPgText");
            if (currElem) currElem.innerText = this.currentPage;
            if (totElem) totElem.innerText = this.pages;
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

    var customPager = new CustomPager('customers', 20);
    customPager.init();

    function fnExcelReport() {
        var $loading = $("#exportLoading");
        var $btn = $("#btnExport");
        $loading.show();
        $btn.prop("disabled", true);

        setTimeout(function() {
            try {
                var maBc = document.getElementById("maBc").value;
                var tenBc = document.getElementById("tenBc").value;
                var table = document.getElementById("tblexpcontent");
                var wb = XLSX.utils.book_new();
                var ws = XLSX.utils.table_to_sheet(table, {
                    raw: true
                });
                var range = XLSX.utils.decode_range(ws["!ref"]);
                XLSX.utils.sheet_add_aoa(ws, [[tenBc]], {origin: "A1"});

                range.e.r += 1;
                ws["!ref"] = XLSX.utils.encode_range(range);
                ws["!merges"] = [{
                    s: {r: 0, c: 0},
                    e: {r: 0, c: range.e.c}
                }];

                for (let addr in ws) {
                    if (addr[0] === '!')
                        continue;
                    let cell = ws[addr];
                    if (cell && cell.v != null) {
                        cell.v = String(cell.v);
                        cell.t = "s";
                    }
                }

                ws["!cols"] = [];

                for (let c = 0; c <= range.e.c; c++) {
                    let maxLen = 10;
                    for (let r = 1; r <= range.e.r; r++) {
                        let addr = XLSX.utils.encode_cell({r, c});
                        let cell = ws[addr];
                        if (cell && cell.v != null) {
                            maxLen = Math.max(maxLen, String(cell.v).length);
                        }
                    }
                    ws["!cols"][c] = {
                        wch: Math.min(maxLen, 50)
                    };
                }

                for (let r = 0; r <= range.e.r; r++) {
                    for (let c = 0; c <= range.e.c; c++) {
                        let addr = XLSX.utils.encode_cell({r, c});
                        if (!ws[addr])
                            continue;
                        ws[addr].s = {
                            alignment: {
                                vertical: "center",
                                horizontal: "center",
                                wrapText: true
                            },
                            font: {
                                bold: (r === 0 || r === 1),
                                sz: (r === 0 ? 16 : 11)
                            },
                            border: {
                                top: {style: "thin"},
                                bottom: {style: "thin"},
                                left: {style: "thin"},
                                right: {style: "thin"}
                            }
                        };
                    }
                }

                XLSX.utils.book_append_sheet(wb, ws, "Sheet1");

                var today = new Date();
                var fileName =
                        maBc + "_" +
                        today.getFullYear() +
                        String(today.getMonth() + 1).padStart(2, "0") +
                        String(today.getDate()).padStart(2, "0") + "_" +
                        String(today.getHours()).padStart(2, "0") +
                        String(today.getMinutes()).padStart(2, "0") +
                        String(today.getSeconds()).padStart(2, "0") +
                        ".xlsx";

                XLSX.writeFile(wb, fileName);
            } catch (e) {
                console.error(e);
                alert("Có lỗi xảy ra khi xuất file Excel!");
            } finally {
                $loading.hide();
                $btn.prop("disabled", false);
            }
        }, 100);
    }
</script>