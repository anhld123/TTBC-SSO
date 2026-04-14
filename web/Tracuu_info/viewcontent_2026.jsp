
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<button id="btnExport" onclick="fnExcelReport();" style="margin: 5px;"> Xuất excel </button>

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
        </td>
</table>

<script src="Tracuu_info/sorttable.js" type="text/javascript"></script>
<script src="https://cdn.jsdelivr.net/npm/xlsx-js-style@1.2.0/dist/xlsx.bundle.min.js"></script>
<script type="text/javascript">

    function fnExcelReport() {
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
    }
    function download(filename, text) {
        var element = document.createElement('a');
        element.setAttribute('href', 'data:application/vnd.ms-excel;charset=utf-8,' + encodeURIComponent(text));
        element.setAttribute('download', filename);

        element.style.display = 'none';
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);
    }

</script>