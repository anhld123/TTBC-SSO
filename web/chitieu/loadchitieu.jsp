<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <input type="hidden" value="${TotalPage}" id="topage">
        <table width="100%">
            <tr>
                <td style="font-family: tahoma; font-size: 12px;">
                    <b> Tổng số chỉ tiêu: ${TotalIndicator} &nbsp;&nbsp; Tổng giá trị: ${SumValue} </b>
                </td>
                <td style="font-family: tahoma; font-size: 12px;">
                    <b><a href="javascript:getfiletxt();">Xuất Excel</a></b>
                </td>
                <td align="right" style="font-family: tahoma; font-size: 11px;">
                    <a href="javascript:phantrang('Dau');" style="color: #018c3b;"><b>Đầu</b></a> &nbsp; | &nbsp;
                    <a href="javascript:phantrang('Truoc');" style="color: #018c3b;"><b>Trước</b></a> &nbsp; | &nbsp;
                    <a href="javascript:phantrang('Sau');" style="color: #018c3b;"><b>Sau</b></a> &nbsp; | &nbsp;  
                    <a href="javascript:phantrang('Cuoi');" style="color: #018c3b;"><b>Cuối</a> &nbsp; | Trang hiện tại: </b> &nbsp; 
                    <select name="pgtrang" id="pgtrang" style="width:120px;" onchange="phantrang('');">
                        <s:iterator begin="1" end="%{TotalPage}" status="status">
                            <s:if test="%{#status.count == numpage}">
                                <option value="<s:property value="%{#status.count}" />" selected disabled> &raquo; Trang: <s:property value="%{#status.count}" /></option>
                            </s:if>
                            <s:else>
                                <option value="<s:property value="%{#status.count}" />">Trang: <s:property value="%{#status.count}" /></option>
                            </s:else>
                        </s:iterator>
                    </select>
                    &nbsp;&nbsp;&nbsp;
                </td>
            </tr>
        </table>
        <div class="CSSTableGenerator" >
            <table id="sumchitieu">
                <tr style="font-family: tahoma; font-size: 11px;">
                    <td>Mã chỉ tiêu</td>
                    <td>Tên chỉ tiêu</td>
                    <td>Giá trị</td>
                    <td>Ngày báo cáo</td>
                    <td>Mã PGD</td>
                    <td>Mã CN</td>
                </tr>
                <s:iterator value="loadChiTieuList">
                    <tr>
                        <td><s:property value="maChiTieu"/></td>
                        <td><s:property value="kieuGiaTri"/></td>
                        <td class="giatrict"><s:property value="%{getText('{0,number,#,###.##}',{GiaTri})}"/></td>
                        <td><s:property value="ngayBaoCao"/></td>
                        <td><s:property value="maPGD"/></td>
                        <td><s:property value="maCN"/></td>
                    </tr>
                </s:iterator>
            </table>
        </div>
    </body>
</html>
