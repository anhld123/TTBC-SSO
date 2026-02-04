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
        width: 98vw;
        margin-left: 5px;
    }

    #subTable td, #subTable th {
        border: 1px solid #ddd;
        padding: 8px;
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
        <th style="text-align:center;font-weight:bold">Họ tên</th>
        <th style="text-align:center;font-weight:bold">Ngày sinh</th>
        <th style="text-align:center;font-weight:bold">Nơi Đky HKTT</th>
        <th style="text-align:center;font-weight:bold">CCCD</th>
        <th style="text-align:center;font-weight:bold">Hộ chiếu</th>
        <th style="text-align:center;font-weight:bold">Tội danh</th>
        <th style="text-align:center;font-weight:bold">Tên bố</th>
        <th style="text-align:center;font-weight:bold">Tên mẹ</th>
        <th style="text-align:center;font-weight:bold">Số QĐ truy nã</th>
        <th style="text-align:center;font-weight:bold">Ngày ra QĐ truy nã</th>
        <th style="text-align:center;font-weight:bold">Đơn vị ra QĐ truy nã</th>
        <th style="text-align:center;font-weight:bold">Loại truy nã</th>
        <th style="text-align:center;font-weight:bold">Tên không dấu</th>
    </tr>    
    <s:iterator value="#attr.lstCustomerBlackList" var="modelView" status="rowstatus">
        <tr>
            <td><s:property value="fullName"/></td>
            <td><s:property value="birthDate"/>/<s:property value="birthMonth"/>/<s:property value="birthYear"/></td>
            <td><s:property value="permanentAddress"/></td>
            <td><s:property value="idNumber"/></td>
            <td><s:property value="passportId"/></td>
            <td><s:property value="criminalOffense"/></td>
            <td><s:property value="fatherName"/></td>
            <td><s:property value="motherName"/></td>
            <td><s:property value="decisionNo"/></td>
            <td><s:property value="decisionDate"/></td>
            <td><s:property value="decisionPlace"/></td>
            <td><s:property value="offenseType"/></td>
            <td><s:property value="fullNameNoAccent"/></td>
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
