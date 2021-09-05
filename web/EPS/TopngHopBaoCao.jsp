<%-- 
    Document   : epsDetails
    Created on : Jul 12, 2021, 2:10:30 PM
    Author     : ITCVBSP56
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div></div>
        <table id="tblData">
            <tr>
                <th colspan="4">TỔNG HỢP BÁO CÁO TỔNG HỢP SỐ LIỆU TOÀN QUỐC CỦA CHI NHÁNH - PGD VỀ XÁC NHẬN SỐ LIỆU EPS</th>
            </tr>
            <tr>
                <th>STT</th>
                <th>Chi nhánh</th>
                <th>Phòng Giao dịch</th>
                <th>Trạng thái dữ liệu</th>
            </tr>
            <s:iterator value="lstDetail" status="idxRows">
                <tr>
                    <td><s:property value='KHOA'/></td>
                    <td><s:property value='MACN'/></td>
                    <td><s:property value='MAPGD'/></td>
                    <td><s:property value='MAKH'/></td>
                </tr>
            </s:iterator>
        </table>
    </body>
</html>
