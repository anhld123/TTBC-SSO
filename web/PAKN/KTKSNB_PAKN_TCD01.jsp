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
</style>
<table border="1px" id="tableKtnb">
    <thead>
        <tr>
            <th rowspan="4">Đơn vị</th>
            <th rowspan="4">Tổng số lượt tiếp</th>
            <th rowspan="4">Tổng số người được tiếp</th>
            <th rowspan="4">Tổng số vụ được tiếp</th>
            <th colspan="8">Tiếp thường xuyên</th>
            <th colspan="18">Tiếp định kỳ và đột xuất của Thủ trưởng</th>
        </tr>
        <tr>
            <th rowspan="3">Số lượt tiếp</th>
            <th rowspan="3">Số người được tiếp</th>
            <th colspan="2">Số vụ việc</th>
            <th colspan="4">Trong đó đoàn đông người</th>
            <th colspan="9">Thủ trưởng tiếp</th>
            <th colspan="9">Uỷ quyền tiếp</th>
        </tr>
        <tr>
            <th rowspan="2">Tiếp lần đầu</th>
            <th rowspan="2">Tiếp nhiều lần</th>
            <th rowspan="2">Số đoàn được tiếp</th>
            <th rowspan="2">Số người được tiếp</th>
            <th rowspan="2">Tiếp lần đầu</th>
            <th rowspan="2">Tiếp nhiều lần</th>
            <th rowspan="2">Số kỳ tiếp</th>
            <th rowspan="2">Số lượt tiếp</th>
            <th rowspan="2">Số người được tiếp</th>
            <th colspan="2">Số vụ việc</th>
            <th colspan="4">Trong đó đoàn đông người</th>
            <th rowspan="2">Số kỳ tiếp</th>
            <th rowspan="2">Số lượt tiếp</th>
            <th rowspan="2">Số người được tiếp</th>
            <th colspan="2">Số vụ việc</th>
            <th colspan="4">Trong đó đoàn đông người</th>
        </tr>
        <tr>
            <th>Tiếp lần đầu</th>
            <th>Tiếp nhiều lần</th>
            <th>Số đoàn được tiếp</th>
            <th>Số người được tiếp</th>
            <th>Tiếp lần đầu</th>
            <th>Tiếp nhiều lần</th>
            <th>Tiếp lần đầu</th>
            <th>Tiếp nhiều lần</th>
            <th>Số đoàn được tiếp</th>
            <th>Số người được tiếp</th>
            <th>Tiếp lần đầu</th>
            <th>Tiếp nhiều lần</th>
        </tr>
        <tr style="font-style: italic; text-align: center;">
            <td>MS</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td><td>12</td><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td><td>18</td><td>19</td><td>20</td><td>21</td><td>22</td><td>23</td><td>24</td><td>25</td><td>26</td><td>27</td><td>28</td><td>29</td>
        </tr>
    </thead>
    <tbody id="tbody">
        <s:iterator value="ModelList" status="status">     
            <tr>

                <td class="cssTdTEN"><input type="text" value="<s:property value='TEN'/>" name="lstModelList[<s:property  value='%{#status.index}' />].TEN" class="cssItem cssTEN" readonly="readonly" /></td>
                <td class="cssTdD1"><input type="text" value="<s:property value='D1'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D1" class="cssItem cssD1" /></td>
                <td class="cssTdD2"><input type="text" value="<s:property value='D2'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D2" class="cssItem cssD2" /></td>
                <td class="cssTdD3"><input type="text" value="<s:property value='D3'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D3" class="cssItem cssD3" /></td>
                <td class="cssTdD4"><input type="text" value="<s:property value='D4'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D4" class="cssItem cssD4" /></td>
                <td class="cssTdD5"><input type="text" value="<s:property value='D5'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D5" class="cssItem cssD5" /></td>
                <td class="cssTdD6"><input type="text" value="<s:property value='D6'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D6" class="cssItem cssD6" /></td>
                <td class="cssTdD7"><input type="text" value="<s:property value='D7'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D7" class="cssItem cssD7" /></td>
                <td class="cssTdD8"><input type="text" value="<s:property value='D8'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D8" class="cssItem cssD8" /></td>
                <td class="cssTdD9"><input type="text" value="<s:property value='D9'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D9" class="cssItem cssD9" /></td>
                <td class="cssTdD10"><input type="text" value="<s:property value='D10'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D10" class="cssItem cssD10" /></td>
                <td class="cssTdD11"><input type="text" value="<s:property value='D11'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D11" class="cssItem cssD11" /></td>
                <td class="cssTdD12"><input type="text" value="<s:property value='D12'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D12" class="cssItem cssD12" /></td>
                <td class="cssTdD13"><input type="text" value="<s:property value='D13'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D13" class="cssItem cssD13" /></td>
                <td class="cssTdD14"><input type="text" value="<s:property value='D14'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D14" class="cssItem cssD14" /></td>
                <td class="cssTdD15"><input type="text" value="<s:property value='D15'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D15" class="cssItem cssD15" /></td>
                <td class="cssTdD16"><input type="text" value="<s:property value='D16'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D16" class="cssItem cssD16" /></td>
                <td class="cssTdD17"><input type="text" value="<s:property value='D17'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D17" class="cssItem cssD17" /></td>
                <td class="cssTdD18"><input type="text" value="<s:property value='D18'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D18" class="cssItem cssD18" /></td>
                <td class="cssTdD19"><input type="text" value="<s:property value='D19'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D19" class="cssItem cssD19" /></td>
                <td class="cssTdD20"><input type="text" value="<s:property value='D20'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D20" class="cssItem cssD20" /></td>
                <td class="cssTdD21"><input type="text" value="<s:property value='D21'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D21" class="cssItem cssD21" /></td>
                <td class="cssTdD22"><input type="text" value="<s:property value='D22'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D22" class="cssItem cssD22" /></td>
                <td class="cssTdD23"><input type="text" value="<s:property value='D23'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D23" class="cssItem cssD23" /></td>
                <td class="cssTdD24"><input type="text" value="<s:property value='D24'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D24" class="cssItem cssD24" /></td>
                <td class="cssTdD25"><input type="text" value="<s:property value='D25'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D25" class="cssItem cssD25" /></td>
                <td class="cssTdD26"><input type="text" value="<s:property value='D26'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D26" class="cssItem cssD26" /></td>
                <td class="cssTdD27"><input type="text" value="<s:property value='D27'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D27" class="cssItem cssD27" /></td>
                <td class="cssTdD28"><input type="text" value="<s:property value='D28'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D28" class="cssItem cssD28" /></td>
                <td class="cssTdD29"><input type="text" value="<s:property value='D29'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D29" class="cssItem cssD29" /></td>
                <td class="cssTdD30 colHiden"><input type="text" value="<s:property value='D30'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D30" class="cssItem cssD30" /></td>
                <td class="cssTdD31 colHiden"><input type="text" value="<s:property value='D31'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D31" class="cssItem cssD31" /></td>
                <td class="cssTdD32 colHiden"><input type="text" value="<s:property value='D32'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D32" class="cssItem cssD32" /></td>
                <td class="cssTdD33 colHiden"><input type="text" value="<s:property value='D33'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D33" class="cssItem cssD33" /></td>
                <td class="cssTdD34 colHiden"><input type="text" value="<s:property value='D34'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D34" class="cssItem cssD34" /></td>
                <td class="cssTdD35 colHiden"><input type="text" value="<s:property value='D35'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D35" class="cssItem cssD35" /></td>
                <td class="cssTdD36 colHiden"><input type="text" value="<s:property value='D36'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D36" class="cssItem cssD36" /></td>
                <td class="cssTdD37 colHiden"><input type="text" value="<s:property value='D37'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D37" class="cssItem cssD37" /></td>
                <td class="cssTdD38 colHiden"><input type="text" value="<s:property value='D38'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D38" class="cssItem cssD38" /></td>
                <td class="cssTdD39 colHiden"><input type="text" value="<s:property value='D39'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D39" class="cssItem cssD39" /></td>
                <td class="cssTdD40 colHiden"><input type="text" value="<s:property value='D40'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D40" class="cssItem cssD40" /></td>
                <td class="cssTdD41 colHiden"><input type="text" value="<s:property value='D41'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D41" class="cssItem cssD41" /></td>
                <td class="cssTdD42 colHiden"><input type="text" value="<s:property value='D42'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D42" class="cssItem cssD42" /></td>
                <td class="cssTdD43 colHiden"><input type="text" value="<s:property value='D43'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D43" class="cssItem cssD43" /></td>
                <td class="cssTdD44 colHiden"><input type="text" value="<s:property value='D44'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D44" class="cssItem cssD44" /></td>
                <td class="cssTdD45 colHiden"><input type="text" value="<s:property value='D45'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D45" class="cssItem cssD45" /></td>
                <td class="cssTdD46 colHiden"><input type="text" value="<s:property value='D46'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D46" class="cssItem cssD46" /></td>
                <td class="cssTdD47 colHiden"><input type="text" value="<s:property value='D47'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D47" class="cssItem cssD47" /></td>
                <td class="cssTdD48 colHiden"><input type="text" value="<s:property value='D48'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D48" class="cssItem cssD48" /></td>
                <td class="cssTdD49 colHiden"><input type="text" value="<s:property value='D49'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D49" class="cssItem cssD49" /></td>
                <td class="cssTdD50 colHiden"><input type="text" value="<s:property value='D50'/>" name="lstModelList[<s:property  value='%{#status.index}' />].D50" class="cssItem cssD50" /></td>
                <td class="cssTdKHOA colHiden"><input type="text" value="<s:property value='KHOA'/>" name="lstModelList[<s:property  value='%{#status.index}' />].KHOA" class="cssItem cssKHOA"  readonly="readonly"/></td>
                <td class="cssTdNGAYBC colHiden"><input type="text" value="<s:property value='NGAYBC'/>" name="lstModelList[<s:property  value='%{#status.index}' />].NGAYBC" class="cssItem cssNGAYBC"  readonly="readonly"/></td>
                <td class="cssTdMAPGD colHiden"><input type="text" value="<s:property value='MAPGD'/>" name="lstModelList[<s:property  value='%{#status.index}' />].MAPGD" class="cssItem cssMAPGD"  readonly="readonly"/></td>
                <td class="cssTdMACN colHiden"><input type="text" value="<s:property value='MACN'/>" name="lstModelList[<s:property  value='%{#status.index}' />].MACN" class="cssItem cssMACN"  readonly="readonly"/></td>

            </tr>
        </s:iterator>
    </tbody>
    <tfoot id="tfoot">
        <tr>
            <th id="tsms">Tổng</th><th id="ts1"></th><th id="ts2"></th><th id="ts3"></th><th id="ts4"></th><th id="ts5"></th><th id="ts6"></th><th id="ts7"></th><th id="ts8"></th><th id="ts9"></th><th id="ts10"></th><th id="ts11"></th><th id="ts12"></th><th id="ts13"></th><th id="ts14"></th><th id="ts15"></th><th id="ts16"></th><th id="ts17"></th><th id="ts18"></th><th id="ts19"></th><th id="ts20"></th><th id="ts21"></th><th id="ts22"></th><th id="ts23"></th><th id="ts24"></th><th id="ts25"></th><th id="ts26"></th><th id="ts27"></th><th id="ts28"></th><th id="ts29"></th>
        </tr>
        <tr>
            <td colspan="56"><span style="font-weight: bold; color: red; margin-bottom: 7px; margin-top: 7px;">Ghi chú: </span></td>
        </tr>
        <tr>
            <td colspan="56"><textarea style="width: 100%; height: 20vh; border: 0px; outline: none;"></textarea></td>
        </tr>
    </tfoot>
</table>

