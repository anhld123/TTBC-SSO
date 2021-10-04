<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<hr/>
<style>
    th{
        background-color: #DCDCDC;
        border-color: #999;
        height: 26px;
    }
    td{
        border-color: #999;
        height: 24px;
    }
    table.editDelete{
        border-collapse: collapse;
        width: 70%;
        border-color: #999;
    }
    table.editDelete tr:hover{
        background-color:#FFE47A;
        cursor: pointer;
    }
</style>
<body>
    <!--<h3 style="color: red">-->
    <%--<s:property value="message" escape="false"/>--%>
    <!--</h3>-->
     <s:if test="sendData.equalsIgnoreCase('OK')">
        <h2 style="color: red;">Danh sách chi tiết Phòng giao dịch thành công/lỗi</h2> </br>
      </s:if>
      <s:elseif test="sendData.equalsIgnoreCase('VIEW')">
        <h2 style="color: blue;">Chi tiết dữ liệu cần gửi lên Trung ương theo từng Phòng giao dịch</h2> </br>
      </s:elseif>
    <s:else>
        <s:if test="trangthai_xlrr.equalsIgnoreCase('W')">
            <h2 style="color: red;">Danh sách khách hàng chờ phê duyệt</h2> </br>
        </s:if>
        <s:if test="trangthai_xlrr.equalsIgnoreCase('R')">
            <h2 style="color: blue;">Danh sách khách hàng đã từ chối</h2> </br>
        </s:if>
        <s:if test="trangthai_xlrr.equalsIgnoreCase('A')">
            <h2 style="color: black;">Danh sách khách hàng đã phê duyệt</h2> </br>
        </s:if>
        <s:if test="trangthai_xlrr.equalsIgnoreCase('P')">
            <h2 style="color: #029c44;">Danh sách khách hàng đã hạch toán</h2> </br>
        </s:if>
     </s:else>  
    <form id="formview62" action="viewdata62">
        <table border="1" class="editDelete"  style="width: 99%">
            <tr>
                <th rowspan="3">STT</th>
                <th rowspan="3">Mã Đơn vị</th>
                <th rowspan="3">Tên Đơn vị</th>  
                <th colspan="4">Phê duyệt</th>  
                <th colspan="4">Từ chối</th>  
            </tr>
            <tr>
                <th rowspan="2">Tổng số món</th>
                <th rowspan="2"> Tổng tiền </th>
                <th colspan="2">Trong đó</th> 
                 <th rowspan="2">Tổng số món</th>
                <th rowspan="2"> Tổng tiền </th>
                <th colspan="2">Trong đó</th> 
            </tr>
            <tr>
                <th>Tổng gốc</th>
                <th>Tổng lãi</th>
                <th>Tổng gốc</th>
                <th>Tổng lãi</th>
            </tr>
            <s:iterator value="#attr.lstBrowerView" var="modelRiskView" status="rowstatus">
                <tr>
                    <!--<td>aaaaaaaaaaaaaaaaaaa</td>-->
                    <s:if test="sPoscd.equalsIgnoreCase('999999')">
                        <td colspan="3" style="text-align: center; color: #007fff; font-weight: bold;"><s:property  value="sPosDesc" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sSoKh" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sTongtien" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sTongDuno" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sTongLai" /></td>
                        <s:if test="sendData.equalsIgnoreCase('OK')">
                            <td style="text-align: right; color: #007fff; font-weight: bold;"></td>
                        </s:if>
                    </s:if>
                    <s:else>
                        <s:if test="sendData.equalsIgnoreCase('OK')">
                            <s:if test="bSuccess == true ">
                                <td style="text-align: center;"><s:property  value="nStt" /></td>
                                <td style="text-align: center;"><s:property  value="sPoscd" /></td>
                                <td><s:property  value="sPosDesc" /></td>
                                <td style="text-align: right;"><s:property  value="sSoKh" /></td>
                                <td style="text-align: right;"><s:property  value="sTongtien" /></td>
                                <td style="text-align: right;"><s:property  value="sTongDuno" /></td>
                                <td style="text-align: right;"><s:property  value="sTongLai" /></td>
                                <td style="text-align: center;">Thành công</td>
                            </s:if>
                            <s:else>
                                <td style="text-align: center; color: red"><s:property  value="nStt" /></td>
                                <td style="text-align: center; color: red"><s:property  value="sPoscd" /></td>
                                <td style=" color: red"><s:property  value="sPosDesc" /></td>
                                <td style="text-align: right; color: red"><s:property  value="sSoKh" /></td>
                                <td style="text-align: right; color: red"><s:property  value="sTongtien" /></td>
                                <td style="text-align: right; color: red"><s:property  value="sTongDuno" /></td>
                                <td style="text-align: right; color: red"><s:property  value="sTongLai" /></td>
                                <td style="text-align: center; color: red">Lỗi</td>
                            </s:else>
                        </s:if>
                        <s:else>
                            <td style="text-align: center;"><s:property  value="nStt" /></td>
                            <td style="text-align: center;"><s:property  value="sPoscd" /></td>
                            <td><s:property  value="sPosDesc" /></td>
                            <td style="text-align: right;"><s:property  value="sSoKh" /></td>
                            <td style="text-align: right;"><s:property  value="sTongtien" /></td>
                            <td style="text-align: right;"><s:property  value="sTongDuno" /></td>
                            <td style="text-align: right;"><s:property  value="sTongLai" /></td>
                            <td style="text-align: right;"><s:property  value="sSoKh" /></td>
                            <td style="text-align: right;"><s:property  value="sTongtien" /></td>
                            <td style="text-align: right;"><s:property  value="sTongDuno" /></td>
                            <td style="text-align: right;"><s:property  value="sTongLai" /></td>
                        </s:else>
                    </s:else>
                </tr>
            </s:iterator>
        </table>
    </form>
</body>
