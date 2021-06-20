<%-- 
    Document   : VNP01-01BDD
    Created on : Jun 13, 2016, 9:10:14 AM
    Author     : Administrator
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VNP01-01BDD</title>
        <script src="../js/jquery.number.js"></script>
        <script src="../js/format_num.js"></script>
        <script src="../js/new_js_bcqt.js"></script>
        <style>
            .css_text{
                border: 0px;
                background-color: transparent;
                width: 100%;
            }
        </style>
    </head>
    <body style="font-family: tahoma; font-size: 12px;">
        <div style="width: 99%; text-align: center; font-weight: bold;">BÁO CÁO KẾT QUẢ HOẠT ĐỘNG CỦA BẠN ĐẠI DIỆN HĐQT CÁC CẤP</div>
        <div style="width: 99%; text-align: right; font-style: italic; padding-bottom: 8px;">Đơn vị tính: Người, kỳ họp, % triệu đồng</div>
        <div style="width: 99%;">
            <table id="tblmain" name="tbmain" style="border-collapse: collapse;width: 100%;" border="1px" cellspacing="0" cellpadding="0">
                <tr align="center">
                    <td rowspan="4">TT</td>
                    <td rowspan="4">BAN ĐẠI DIỆN HĐQT</td>
                    <td rowspan="4">Tổng số Ban ĐD huyện</td>
                    <td colspan="5">CỦNG CỐ KIỆN TOÀN TV BAN ĐẠI DIỆN</td>
                    <td colspan="3">TỔ CHỨC HỌP</td>
                    <td colspan="12">CÔNG TÁC KIỂM TRA GIÁM SÁT</td>
                    <td colspan="6" rowspan="2">CÔNG TÁC THAM MƯU CHO CẤP ỦY, CHÍNH QUYỀN ĐỊA PHƯƠNG</td>
                </tr>
                <tr align="center">
                    <td rowspan="3">Số thành viên theo QĐ</td>
                    <td colspan="2">Số TV kỳ BC</td>
                    <td rowspan="3">Số TV được kiện toàn trong tháng</td>
                    <td rowspan="3">Lũy kế số TV kiện toàn từ đầu năm</td>
                    <td rowspan="3">BĐD họp định kỳ</td>
                    <td rowspan="3">BĐD chưa họp</td>
                    <td rowspan="3">Lũy kế số kỳ họp đến kỳ BC</td>
                    <td rowspan="3">Số TV được phân công kiểm tra</td>
                    <td colspan="5">Kết quả kiểm tra trong tháng</td>
                    <td colspan="6">Lũy kế kết quả kiểm tra từ đầu năm</td>
                </tr>
                <tr align="center">
                    <td rowspan="2">Tổng số TV</td>
                    <td rowspan="2">Trong đó TV là Chủ tịch UBND cấp xã</td>
                    <td rowspan="2">Số TV thực hiện kiểm tra</td>
                    <td rowspan="2">Số huyện kiểm tra</td>
                    <td rowspan="2">Số xã kiểm tra</td>
                    <td rowspan="2">Số tổ kiểm tra</td>
                    <td rowspan="2">Số hộ kiểm tra</td>
                    <td rowspan="2">Số TV thực hiện kiểm trả</td>
                    <td rowspan="2">Số lượt huyện kiểm tra</td>
                    <td rowspan="2">Số lượt xã kiểm tra</td>
                    <td rowspan="2">Số lượt tổ kiểm tra</td>
                    <td rowspan="2">Số lượt hộ kiểm tra</td>
                    <td rowspan="2">tỷ lệ % TV hoàn thiện kiểm tra</td>
                    <td colspan="4">Chuyển nguồn vốn NSĐP</td>
                    <td colspan="2">Hỗ trợ CSVC (giá Trị)</td>
                </tr>
                <tr align="center">
                    <td>Số dư đầu năm</td>
                    <td>Số dư đến ngày BC</td>
                    <td>Tăng giảm so với tháng trước</td>
                    <td>Tăng giảm so với đầu năm</td>
                    <td>Số được hỗ trợ trong tháng</td>
                    <td>Lũy kế số được hỗ trợ từ đầu năm</td>
                </tr>
                <tr style="font-style: italic; text-align: center;">
                    <td>1</td>
                    <td>2</td>
                    <td style="width: 10%;">3</td>
                    <td>4</td>
                    <td>5</td>
                    <td>6</td>
                    <td>7</td>
                    <td>8</td>
                    <td>9</td>
                    <td>10</td>
                    <td>11</td>
                    <td>12</td>
                    <td>13</td>
                    <td>14</td>
                    <td>15</td>
                    <td>16</td>
                    <td>17</td>
                    <td>18</td>
                    <td>19</td>
                    <td>20</td>
                    <td>21</td>
                    <td>22</td>
                    <td>23</td>
                    <td>24</td>
                    <td>25</td>
                    <td>26</td>
                    <td>27</td>
                    <td>28</td>
                    <td>29</td>
                </tr>
                <s:iterator value="lstData" status="rowstatus">
                    <s:if test='%{D28 == "Y"}'>
                        <tr>
                            <td>
                                <input type="text" style="text-align: center;" class="css_text" value="<s:property  value="TT_HIENTHI" />" id="TT_HIENTHI" name="lstData[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" readonly="readonly">
                                <input type="hidden" style="text-align: center;" class="css_text" value="<s:property  value="THUTU" />" id="THUTU" name="lstData[<s:property  value="%{#rowstatus.index}" />].THUTU" readonly="readonly">
                            </td>
                            <s:if test='%{TT_HIENTHI == "I" || TT_HIENTHI == "II"}'>
                                <td colspan="2">
                                    <input type="hidden" style="text-align: center;" class="css_text" value="<s:property  value="MA" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA" readonly="readonly">
                                    <input type="text" style="padding-left: 2px;"  class="css_text" value="<s:property  value="TEN" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN" readonly="readonly">
                                </td>
                            </s:if>
                            <s:else>
                                <td><input type="text" style="text-align: center;" class="css_text" value="<s:property  value="MA" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA" readonly="readonly"></td>
                                <td><input type="text" style="padding-left: 2px;"  class="css_text" value="<s:property  value="TEN" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN" readonly="readonly"></td>
                            </s:else>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D1" />" id="D1" name="lstData[<s:property  value="%{#rowstatus.index}" />].D1"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D2" />" id="D2" name="lstData[<s:property  value="%{#rowstatus.index}" />].D2"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D3" />" id="D3" name="lstData[<s:property  value="%{#rowstatus.index}" />].D3"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D4" />" id="D4" name="lstData[<s:property  value="%{#rowstatus.index}" />].D4"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D5" />" id="D5" name="lstData[<s:property  value="%{#rowstatus.index}" />].D5"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D6" />" id="D6" name="lstData[<s:property  value="%{#rowstatus.index}" />].D6"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D7" />" id="D7" name="lstData[<s:property  value="%{#rowstatus.index}" />].D7"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D8" />" id="D8" name="lstData[<s:property  value="%{#rowstatus.index}" />].D8"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D9" />" id="D9" name="lstData[<s:property  value="%{#rowstatus.index}" />].D9"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D10" />" id="D10" name="lstData[<s:property  value="%{#rowstatus.index}" />].D10"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D11" />" id="D11" name="lstData[<s:property  value="%{#rowstatus.index}" />].D11"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D12" />" id="D12" name="lstData[<s:property  value="%{#rowstatus.index}" />].D12"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D13" />" id="D13" name="lstData[<s:property  value="%{#rowstatus.index}" />].D13"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D14" />" id="D14" name="lstData[<s:property  value="%{#rowstatus.index}" />].D14"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D15" />" id="D15" name="lstData[<s:property  value="%{#rowstatus.index}" />].D15"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D16" />" id="D16" name="lstData[<s:property  value="%{#rowstatus.index}" />].D16"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D17" />" id="D17" name="lstData[<s:property  value="%{#rowstatus.index}" />].D17"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D18" />" id="D18" name="lstData[<s:property  value="%{#rowstatus.index}" />].D18"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D19" />" id="D19" name="lstData[<s:property  value="%{#rowstatus.index}" />].D19"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D20" />" id="D20" name="lstData[<s:property  value="%{#rowstatus.index}" />].D20"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D21" />" id="D21" name="lstData[<s:property  value="%{#rowstatus.index}" />].D21"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D22" />" id="D22" name="lstData[<s:property  value="%{#rowstatus.index}" />].D22"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D23" />" id="D23" name="lstData[<s:property  value="%{#rowstatus.index}" />].D23"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D24" />" id="D24" name="lstData[<s:property  value="%{#rowstatus.index}" />].D24"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D25" />" id="D25" name="lstData[<s:property  value="%{#rowstatus.index}" />].D25"></td>
                            <td>
                                <input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D26" />" id="D26" name="lstData[<s:property  value="%{#rowstatus.index}" />].D26">
                                <input type="hidden" style="text-align: right;" class="css_text" value="<s:property  value="D28" />" id="D28" name="lstData[<s:property  value="%{#rowstatus.index}" />].D28" readonly="readonly">
                                <input type="hidden" style="text-align: right;" class="css_text" value="<s:property  value="D29" />" id="D29" name="lstData[<s:property  value="%{#rowstatus.index}" />].D29" readonly="readonly">
                            </td>
                        </tr>
                    </s:if>
                    <s:else>
                        <tr style="background-color: #f2f2f2;">
                            <td>
                                <input type="text" style="text-align: center;" class="css_text" value="<s:property  value="TT_HIENTHI" />" id="TT_HIENTHI" name="lstData[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" readonly="readonly">
                                <input type="hidden" style="text-align: center;" class="css_text" value="<s:property  value="THUTU" />" id="THUTU" name="lstData[<s:property  value="%{#rowstatus.index}" />].THUTU" readonly="readonly">
                            </td>
                            <s:if test='%{TT_HIENTHI == "I" || TT_HIENTHI == "II"}'>
                                <td colspan="2">
                                    <input type="hidden" style="text-align: center;" class="css_text" value="<s:property  value="MA" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA" readonly="readonly">
                                    <input type="text" style="padding-left: 2px;"  class="css_text" value="<s:property  value="TEN" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN" readonly="readonly">
                                </td>
                            </s:if>
                            <s:else>
                                <td><input type="text" style="text-align: center;" class="css_text" value="<s:property  value="MA" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA" readonly="readonly"></td>
                                <td><input type="text" style="padding-left: 2px;"  class="css_text" value="<s:property  value="TEN" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN" readonly="readonly"></td>
                            </s:else>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D1" />" id="D1" name="lstData[<s:property  value="%{#rowstatus.index}" />].D1" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D2" />" id="D2" name="lstData[<s:property  value="%{#rowstatus.index}" />].D2" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D3" />" id="D3" name="lstData[<s:property  value="%{#rowstatus.index}" />].D3" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D4" />" id="D4" name="lstData[<s:property  value="%{#rowstatus.index}" />].D4" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D5" />" id="D5" name="lstData[<s:property  value="%{#rowstatus.index}" />].D5" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D6" />" id="D6" name="lstData[<s:property  value="%{#rowstatus.index}" />].D6" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D7" />" id="D7" name="lstData[<s:property  value="%{#rowstatus.index}" />].D7" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D8" />" id="D8" name="lstData[<s:property  value="%{#rowstatus.index}" />].D8" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D9" />" id="D9" name="lstData[<s:property  value="%{#rowstatus.index}" />].D9" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D10" />" id="D10" name="lstData[<s:property  value="%{#rowstatus.index}" />].D10" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D11" />" id="D11" name="lstData[<s:property  value="%{#rowstatus.index}" />].D11" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D12" />" id="D12" name="lstData[<s:property  value="%{#rowstatus.index}" />].D12" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D13" />" id="D13" name="lstData[<s:property  value="%{#rowstatus.index}" />].D13" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D14" />" id="D14" name="lstData[<s:property  value="%{#rowstatus.index}" />].D14" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D15" />" id="D15" name="lstData[<s:property  value="%{#rowstatus.index}" />].D15" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D16" />" id="D16" name="lstData[<s:property  value="%{#rowstatus.index}" />].D16" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D17" />" id="D17" name="lstData[<s:property  value="%{#rowstatus.index}" />].D17" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D18" />" id="D18" name="lstData[<s:property  value="%{#rowstatus.index}" />].D18" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D19" />" id="D19" name="lstData[<s:property  value="%{#rowstatus.index}" />].D19" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D20" />" id="D20" name="lstData[<s:property  value="%{#rowstatus.index}" />].D20" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D21" />" id="D21" name="lstData[<s:property  value="%{#rowstatus.index}" />].D21" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D22" />" id="D22" name="lstData[<s:property  value="%{#rowstatus.index}" />].D22" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D23" />" id="D23" name="lstData[<s:property  value="%{#rowstatus.index}" />].D23" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D24" />" id="D24" name="lstData[<s:property  value="%{#rowstatus.index}" />].D24" readonly="readonly"></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D25" />" id="D25" name="lstData[<s:property  value="%{#rowstatus.index}" />].D25" readonly="readonly"></td>
                            <td>
                                <input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D26" />" id="D26" name="lstData[<s:property  value="%{#rowstatus.index}" />].D26" readonly="readonly">
                                <input type="hidden" style="text-align: right;" class="css_text" value="<s:property  value="D28" />" id="D28" name="lstData[<s:property  value="%{#rowstatus.index}" />].D28" readonly="readonly">
                                <input type="hidden" style="text-align: right;" class="css_text" value="<s:property  value="D29" />" id="D29" name="lstData[<s:property  value="%{#rowstatus.index}" />].D29" readonly="readonly">
                            </td>
                        </tr>
                    </s:else>
                </s:iterator>
            </table>
        </div>
    </body>
</html>
