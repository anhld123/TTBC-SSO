<%-- 
    Document   : ViewData
    Created on : May 17, 2022, 9:56:22 AM
    Author     : NGUYEN PHU VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<style>
    .colHiden{
        display: none;
    }
    .cssTEN{
        width: 155px;
    }
    .lock{
        background-color: lightgrey;
    }
</style>
<table border="1px" id="tableKtnb">
    <thead>
        <tr>
            <s:if test="txtsGrade.equalsIgnoreCase('3')">
                <th rowspan="4">Tỉnh</th>
                </s:if>
            <th rowspan="4">Đơn vị</th>
            <th colspan="3">Đơn khiếu nại thuộc thẩm quyền</th>
            <th rowspan="4">Tổng số vụ việc khiếu nại thuộc thẩm quyền</th>
            <th colspan="15">Kết quả giải quyết</th>
            <th colspan="7">Phân tích kết quả giải quyết (vụ việc)</th>
            <th rowspan="4">Ngày nhập liệu</th>
        </tr>
        <tr>
            <th rowspan="3">Tổng số</th>
            <th rowspan="3">Kỳ trước chuyển sang</th>
            <th rowspan="3">Tiếp nhận trong kỳ</th>
            <th colspan="2">Đã giải quyết</th>
            <th colspan="2">Kiến nghị thu hồi cho NN</th>
            <th colspan="6">Trả lại cho tổ chức, cá nhân</th>
            <th colspan="2">Kiến nghị xử lý hành chính</th>
            <th colspan="3">Chuyển cơ quan điều tra</th>
            <th colspan="3">Giải quyết lần đầu</th>
            <th colspan="2">Giải quyết lần 2</th>
            <th colspan="2">Chấp hành thời hạn giải quyết</th>
        </tr>
        <tr>
            <th rowspan="2">Số vụ việc giải quyết bằng QĐ hành chính</th>
            <th rowspan="2">Số vụ việc rút đơn thông qua giải thích, thuyết phục</th>
            <th rowspan="2">Tiền (Trđ)</th>
            <th rowspan="2">Đất (m2)</th>
            <th colspan="2">Tổ chức</th>
            <th colspan="2">Cá nhân</th>
            <th rowspan="2">Số tổ chức được trả lại quyền lợi</th>
            <th rowspan="2">Số cá nhân được trả lại quyền lợi </th>
            <th rowspan="2">Tổng số người bị kiến nghị xử lý </th>
            <th rowspan="2">Trong đó số cán bộ, công chức, viên chức</th>
            <th rowspan="2">Số vụ</th>
            <th rowspan="2">Tổng số người</th>
            <th rowspan="2">Trong đó số cán bộ, công chức, viên chức</th>
            <th rowspan="2">Khiếu nại đúng</th>
            <th rowspan="2">Khiếu nại sai</th>
            <th rowspan="2">Khiếu nại đúng một phần</th>
            <th rowspan="2">Công nhận QĐ g/q lần đẩu</th>
            <th rowspan="2">Hủy, sửa QĐ g/q lần đầu</th>
            <th rowspan="2">Đúng quy định</th>
            <th rowspan="2">Không đúng quy định</th>
        </tr>
        <tr>
            <th>Tiền (Trđ)</th>
            <th>Đất (m2)</th>
            <th>Tiền (Trđ)</th>
            <th>Đất (m2)</th>
        </tr>
        <tr style="font-style: italic; text-align: center;" class="lock">
            <s:if test="txtsGrade.equalsIgnoreCase('3')">
                <td>
                    <input type="text" id="idSearch1" onkeyup="FuncSearch(true)" placeholder="Tìm kiếm theo tên đơn vị" title="Nhập tên đơn vị" style="outline: none;">
                </td>
            </s:if>
            <td>
                <input type="text" id="idSearch2" onkeyup="FuncSearch(false)" placeholder="Tìm kiếm theo tên đơn vị" title="Nhập tên đơn vị" style="outline: none;">
            </td>
            <td>1</td>
            <td>2</td>
            <td>3</td>
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
            <td></td>
        </tr>
    </thead>
    <tbody id="tbody">
        <s:iterator value="ModelList" status="status">     
            <tr>
                <s:if test="txtsGrade.equalsIgnoreCase('3')">
                    <td class="cssTdD41 lock"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D41'/>" name="ModelList[<s:property  value='%{#status.index}' />].D41" class="cssItem cssD41"   /></td>
                </s:if>
                <td class="cssTdTEN lock"><input type="text" value="<s:property value='TEN'/>" name="ModelList[<s:property  value='%{#status.index}' />].TEN" class="cssItem cssTEN"   /></td>
                <td class="cssTdD1 lock"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D1'/>" name="ModelList[<s:property  value='%{#status.index}' />].D1" class="cssItem cssD1"   /></td>
                <td class="cssTdD2"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D2'/>" name="ModelList[<s:property  value='%{#status.index}' />].D2" class="cssItem cssD2" /></td>
                <td class="cssTdD3"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D3'/>" name="ModelList[<s:property  value='%{#status.index}' />].D3" class="cssItem cssD3" /></td>
                <td class="cssTdD4"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D4'/>" name="ModelList[<s:property  value='%{#status.index}' />].D4" class="cssItem cssD4"  /></td>
                <td class="cssTdD5 lock"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D5'/>" name="ModelList[<s:property  value='%{#status.index}' />].D5" class="cssItem cssD5"   /></td>
                <td class="cssTdD6"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D6'/>" name="ModelList[<s:property  value='%{#status.index}' />].D6" class="cssItem cssD6" /></td>
                <td class="cssTdD7"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D7'/>" name="ModelList[<s:property  value='%{#status.index}' />].D7" class="cssItem cssD7" /></td>
                <td class="cssTdD8"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D8'/>" name="ModelList[<s:property  value='%{#status.index}' />].D8" class="cssItem cssD8" /></td>
                <td class="cssTdD9"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D9'/>" name="ModelList[<s:property  value='%{#status.index}' />].D9" class="cssItem cssD9" /></td>
                <td class="cssTdD10"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D10'/>" name="ModelList[<s:property  value='%{#status.index}' />].D10" class="cssItem cssD10" /></td>
                <td class="cssTdD11"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D11'/>" name="ModelList[<s:property  value='%{#status.index}' />].D11" class="cssItem cssD11" /></td>
                <td class="cssTdD12"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D12'/>" name="ModelList[<s:property  value='%{#status.index}' />].D12" class="cssItem cssD12" /></td>
                <td class="cssTdD13"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D13'/>" name="ModelList[<s:property  value='%{#status.index}' />].D13" class="cssItem cssD13" /></td>
                <td class="cssTdD14"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D14'/>" name="ModelList[<s:property  value='%{#status.index}' />].D14" class="cssItem cssD14" /></td>
                <td class="cssTdD15"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D15'/>" name="ModelList[<s:property  value='%{#status.index}' />].D15" class="cssItem cssD15" /></td>
                <td class="cssTdD16"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D16'/>" name="ModelList[<s:property  value='%{#status.index}' />].D16" class="cssItem cssD16" /></td>
                <td class="cssTdD17"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D17'/>" name="ModelList[<s:property  value='%{#status.index}' />].D17" class="cssItem cssD17" /></td>
                <td class="cssTdD18"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D18'/>" name="ModelList[<s:property  value='%{#status.index}' />].D18" class="cssItem cssD18" /></td>
                <td class="cssTdD19"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D19'/>" name="ModelList[<s:property  value='%{#status.index}' />].D19" class="cssItem cssD19" /></td>
                <td class="cssTdD20"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D20'/>" name="ModelList[<s:property  value='%{#status.index}' />].D20" class="cssItem cssD20" /></td>
                <td class="cssTdD21"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D21'/>" name="ModelList[<s:property  value='%{#status.index}' />].D21" class="cssItem cssD21" /></td>
                <td class="cssTdD22"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D22'/>" name="ModelList[<s:property  value='%{#status.index}' />].D22" class="cssItem cssD22" /></td>
                <td class="cssTdD23"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D23'/>" name="ModelList[<s:property  value='%{#status.index}' />].D23" class="cssItem cssD23" /></td>
                <td class="cssTdD24"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D24'/>" name="ModelList[<s:property  value='%{#status.index}' />].D24" class="cssItem cssD24" /></td>
                <td class="cssTdD25"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D25'/>" name="ModelList[<s:property  value='%{#status.index}' />].D25" class="cssItem cssD25" /></td>
                <td class="cssTdD26"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D26'/>" name="ModelList[<s:property  value='%{#status.index}' />].D26" class="cssItem cssD26" /></td>
                <td class="cssTdNGAYBC lock"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='NGAYBC'/>" name="ModelList[<s:property  value='%{#status.index}' />].NGAYBC" class="cssItem cssNGAYBC" /></td>
                <td class="cssTdD27 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D27'/>" name="ModelList[<s:property  value='%{#status.index}' />].D27" class="cssItem cssD27" /></td>
                <td class="cssTdD28 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D28'/>" name="ModelList[<s:property  value='%{#status.index}' />].D28" class="cssItem cssD28" /></td>
                <td class="cssTdD29 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D29'/>" name="ModelList[<s:property  value='%{#status.index}' />].D29" class="cssItem cssD29" /></td>
                <td class="cssTdD30 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D30'/>" name="ModelList[<s:property  value='%{#status.index}' />].D30" class="cssItem cssD30" /></td>
                <td class="cssTdD31 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D31'/>" name="ModelList[<s:property  value='%{#status.index}' />].D31" class="cssItem cssD31" /></td>
                <td class="cssTdD32 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D32'/>" name="ModelList[<s:property  value='%{#status.index}' />].D32" class="cssItem cssD32" /></td>
                <td class="cssTdD33 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D33'/>" name="ModelList[<s:property  value='%{#status.index}' />].D33" class="cssItem cssD33" /></td>
                <td class="cssTdD34 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D34'/>" name="ModelList[<s:property  value='%{#status.index}' />].D34" class="cssItem cssD34" /></td>
                <td class="cssTdD35 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D35'/>" name="ModelList[<s:property  value='%{#status.index}' />].D35" class="cssItem cssD35" /></td>
                <td class="cssTdD36 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D36'/>" name="ModelList[<s:property  value='%{#status.index}' />].D36" class="cssItem cssD36" /></td>
                <td class="cssTdD37 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D37'/>" name="ModelList[<s:property  value='%{#status.index}' />].D37" class="cssItem cssD37" /></td>
                <td class="cssTdD38 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D38'/>" name="ModelList[<s:property  value='%{#status.index}' />].D38" class="cssItem cssD38" /></td>
                <td class="cssTdD39 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D39'/>" name="ModelList[<s:property  value='%{#status.index}' />].D39" class="cssItem cssD39" /></td>
                <td class="cssTdD40 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D40'/>" name="ModelList[<s:property  value='%{#status.index}' />].D40" class="cssItem cssD40" /></td>
                <td class="cssTdD42 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D42'/>" name="ModelList[<s:property  value='%{#status.index}' />].D42" class="cssItem cssD42" /></td>
                <td class="cssTdD43 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D43'/>" name="ModelList[<s:property  value='%{#status.index}' />].D43" class="cssItem cssD43" /></td>
                <td class="cssTdD44 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D44'/>" name="ModelList[<s:property  value='%{#status.index}' />].D44" class="cssItem cssD44" /></td>
                <td class="cssTdD45 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D45'/>" name="ModelList[<s:property  value='%{#status.index}' />].D45" class="cssItem cssD45" /></td>
                <td class="cssTdD46 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D46'/>" name="ModelList[<s:property  value='%{#status.index}' />].D46" class="cssItem cssD46" /></td>
                <td class="cssTdD47 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D47'/>" name="ModelList[<s:property  value='%{#status.index}' />].D47" class="cssItem cssD47" /></td>
                <td class="cssTdD48 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D48'/>" name="ModelList[<s:property  value='%{#status.index}' />].D48" class="cssItem cssD48" /></td>
                <td class="cssTdD49 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D49'/>" name="ModelList[<s:property  value='%{#status.index}' />].D49" class="cssItem cssD49" /></td>
                <td class="cssTdD50 colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='D50'/>" name="ModelList[<s:property  value='%{#status.index}' />].D50" class="cssItem cssD50" /></td>
                <td class="cssTdKHOA colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='KHOA'/>" name="ModelList[<s:property  value='%{#status.index}' />].KHOA" class="cssItem cssKHOA"    /></td>
                <td class="cssTdMAPGD colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='MAPGD'/>" name="ModelList[<s:property  value='%{#status.index}' />].MAPGD" class="cssItem cssMAPGD"    /></td>
                <td class="cssTdMACN colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='MACN'/>" name="ModelList[<s:property  value='%{#status.index}' />].MACN" class="cssItem cssMACN"    /></td>
                <td class="cssTdMA colHiden"><input type="number" min="0" oninput="this.value = Math.abs(this.value)" value="<s:property value='MA'/>" name="ModelList[<s:property  value='%{#status.index}' />].MA" class="cssItem cssMA"    /></td>
            </tr>
        </s:iterator>
    </tbody>
    <tfoot id="tfoot">
        <tr>
            <s:if test="txtsGrade.equalsIgnoreCase('3')"><td class="cssTong"/></s:if>
            <td class="cssTong" id="tsms">Tổng</td>
            <td class="cssTong" id="ts1"></td>
            <td class="cssTong" id="ts2"></td>
            <td class="cssTong" id="ts3"></td>
            <td class="cssTong" id="ts4"></td>
            <td class="cssTong" id="ts5"></td>
            <td class="cssTong" id="ts6"></td>
            <td class="cssTong" id="ts7"></td>
            <td class="cssTong" id="ts8"></td>
            <td class="cssTong" id="ts9"></td>
            <td class="cssTong" id="ts10"></td>
            <td class="cssTong" id="ts11"></td>
            <td class="cssTong" id="ts12"></td>
            <td class="cssTong" id="ts13"></td>
            <td class="cssTong" id="ts14"></td>
            <td class="cssTong" id="ts15"></td>
            <td class="cssTong" id="ts16"></td>
            <td class="cssTong" id="ts17"></td>
            <td class="cssTong" id="ts18"></td>
            <td class="cssTong" id="ts19"></td>
            <td class="cssTong" id="ts20"></td>
            <td class="cssTong" id="ts21"></td>
            <td class="cssTong" id="ts22"></td>
            <td class="cssTong" id="ts23"></td>
            <td class="cssTong" id="ts24"></td>
            <td class="cssTong" id="ts25"></td>
            <td class="cssTong" id="ts26"></td>
            <td class="cssTong"/>
        </tr>
        <tr>
            <td colspan="56"><span style="font-weight: bold; color: red; margin-bottom: 7px; margin-top: 7px;">Ghi chú: </span></td>
        </tr>
        <tr class="ShowNhaplieu">
                <td colspan="56"><textarea name = "txtGhiChu" style="width: 100%; height: 20vh; border: 0px; outline: none;" id="txtGhiChu"></textarea></td>
            </tr>
            <tr class="ShowGhichu">
                <td colspan="56" style="line-height: 17px;"><s:property value='txtGhiChu' escape="false"/></td>
        </tr>
    </tfoot>
</table>
<script>
    $("#txtGhiChu").val($(".cssD40").val());
    $(".cssD4").focus();

    //Xử lý hàm cộng các cột
    function SumCol() {
        for (var j = 0; j < $(".cssKHOA").length; j++) {
            $('.cssD1').eq(j).val(parseInt($('.cssD4').eq(j).val()) + parseInt($('.cssD13').eq(j).val()) + parseInt($('.cssD22').eq(j).val()));
            $('.cssD2').eq(j).val(parseInt($('.cssD5').eq(j).val()) + parseInt($('.cssD14').eq(j).val()) + parseInt($('.cssD23').eq(j).val()));
            $('.cssD3').eq(j).val(parseInt($('.cssD6').eq(j).val()) + parseInt($('.cssD7').eq(j).val()) + parseInt($('.cssD15').eq(j).val()) + parseInt($('.cssD16').eq(j).val()) + parseInt($('.cssD24').eq(j).val()) + parseInt($('.cssD25').eq(j).val()));
        }
        SumRow();
    }

    function SumRow() {
        let RowSum = 0;
        for (var i = 1; i <= 26; i++) {
            RowSum = 0;
            for (var j = 0; j < $(".cssKHOA").length; j++) {
                RowSum += parseInt($('.cssD' + i).eq(j).val());
            }
            $("#ts" + i).html(RowSum);
        }
    }
    SumRow();
    function FuncSearch(expr) {
        var input, filter, table, tr, loai, i, txtValue;
        if (expr) {
            loai = "cssD41";
            input = document.getElementById("idSearch1");
        } else {
            loai = "cssTEN";
            input = document.getElementById("idSearch2");
        }
        filter = input.value.toUpperCase();
        table = document.getElementById("tbody");
        tr = table.getElementsByTagName("tr");
        for (i = 0; i < tr.length; i++) {
            txtValue = $("." + loai).eq(i).val();
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
</script>

