<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<script src="js/jquery.number.js"></script>
<script src="js/format_num.js"></script>
<style>
    .Mwidth{
        width: 155px;
    }
    .cssItem{
        border: 0px;
        outline: none;
        width: 100%;
        background-color: transparent;
    }
    .cssTong{
        font-weight: bold;
    }
    table{
        border-collapse: collapse;
    }
    #ts01, #ts02,#ts03, #ts04,#ts05{
        font-weight: bold;
    }
    .number2{
        text-align: right;
    }
</style>
<br>
<table style="width: 100%; border: 0px;" cellspacing="0" cellpadding="0">
    <tbody>
        <tr>
            <td>
                <table style="width: 98.7%; border-bottom: 0px; background-color: lightgray;" border="1px">
                    <tr>
                        <th colspan="7">ĐIỀU CHỈNH KẾ HOẠCH HỖ TRỢ LÃI SUẤT CHO KHÁCH HÀNG VAY VỐN NĂM <span id="idNamBC"/></th>
                    </tr>
                    <tr>
                        <th colspan="7" style="text-align: left; width: 100%;">Dư nợ được điều chỉnh: <input type="text" value="<s:property value='dieuchinh'/>" class="cssItem number2" style="width: 300px; font-weight: bold; color: red;" readonly="readonly"/></th>
                    </tr>
                    <tr>
                        <th rowspan="2" class="Mwidth">STT</th>
                        <th rowspan="2" class="Mwidth">Đơn vị</th>
                        <th colspan="2" class="Mwidth">Kế hoạch</th>
                        <th colspan="3" class="Mwidth">Điều chỉnh</th>
                    </tr>
                    <tr>
                        <th class="Mwidth">Dự kiến dư nợ cho vay được hỗ trợ lãi suất</th>
                        <th class="Mwidth">Nhu cầu hỗ trợ lãi suất</th>
                        <th class="Mwidth">Dư nợ</th>
                        <th class="Mwidth">Dư nợ điều chỉnh</th>
                        <th class="Mwidth">Lãi suất</th>
                    <tr style="font-style: italic; text-align: center;" class="lock">
                        <td class="Mwidth">(1)</td>
                        <td class="Mwidth">(2)</td>
                        <td class="Mwidth">(3)</td>
                        <td class="Mwidth">(4)=(3)*2%</td>
                        <td class="Mwidth">(5)</td>
                        <td class="Mwidth">(6)=(3)+(5)</td>
                        <td class="Mwidth">(7)=(6)*2%</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <div style="width: 100%; height: 50vh;overflow-y: scroll">
                    <table style="width: 100%;" border="1px">
                        <tbody>
                            <s:iterator value="ModelList" status="status">     
                                <tr>
                                    <td class="cssTdTEN Mwidth"><input type="text" value="<s:property value="%{#status.index + 1}"/>" name="ModelList[<s:property  value='%{#status.index}' />].TT_HIENTHI" class="cssItem cssSTT" readonly="readonly"/></td>
                                    <td class="cssTdDTEN Mwidth"><input type="text" value="<s:property value='TEN'/>" name="ModelList[<s:property  value='%{#status.index}' />].TEN" class="cssItem cssTEN" readonly="readonly"/></td>
                                    <td class="cssTdD2 Mwidth"><input type="text" value="<s:property value='D1'/>" name="ModelList[<s:property  value='%{#status.index}' />].D1" class="cssItem number2 cssD1" onblur="totalSum()" /></td>
                                    <td class="cssTdD3 Mwidth"><input type="text" value="<s:property value='D2'/>" name="ModelList[<s:property  value='%{#status.index}' />].D2" class="cssItem number2 cssD2" readonly="readonly"/></td>
                                    <td class="cssTdD4 Mwidth"><input type="text" value="<s:property value='D3'/>" name="ModelList[<s:property  value='%{#status.index}' />].D3" class="cssItem number2 cssD3" onblur="totalSum()"/></td>
                                    <td class="cssTdD5  Mwidth"><input type="text" value="<s:property value='D4'/>" name="ModelList[<s:property  value='%{#status.index}' />].D4" class="cssItem number2 cssD4" readonly="readonly"/></td>
                                    <td class="cssTdD6 Mwidth"><input type="text" value="<s:property value='D5'/>" name="ModelList[<s:property  value='%{#status.index}' />].D5" class="cssItem number2 cssD5"  readonly="readonly"/></td>
                                    <td style="display: none;"><input type="text" value="<s:property value='MA'/>" name="ModelList[<s:property  value='%{#status.index}' />].MA"/></td>
                                    <td style="display: none;"><input type="text" value="<s:property value='MAPGD'/>" name="ModelList[<s:property  value='%{#status.index}' />].MAPGD"/></td>
                                    <td style="display: none;"><input type="text" value="<s:property value='MACN'/>" name="ModelList[<s:property  value='%{#status.index}' />].MACN"/></td>
                                    <td style="display: none;"><input type="text" value="<s:property value='CO_TONGHOP'/>" name="ModelList[<s:property  value='%{#status.index}' />].CO_TONGHOP"/></td>
                                </tr>
                            </s:iterator>
                        </tbody> 
                        <tfoot id="tfoot">
                            <tr style="background-color:lightgray;">
                                <td class="cssTong Mwidth number2" colspan="2">Tổng</td>
                                <td class="cssTong Mwidth"><input type="text" value="" class = "number2 cssItem" id="ts01" readonly="readonly"/></td>
                                <td class="cssTong Mwidth"><input type="text" value="" class = "number2 cssItem" id="ts02" readonly="readonly"/></td>
                                <td class="cssTong Mwidth"><input type="text" value="" class = "number2 cssItem" id="ts03" readonly="readonly"/></td>
                                <td class="cssTong Mwidth"><input type="text" value="" class = "number2 cssItem" id="ts04" readonly="readonly"/></td>
                                <td class="cssTong Mwidth"><input type="text" value="" class = "number2 cssItem" id="ts05" readonly="readonly"/></td>
                            </tr>
                        </tfoot>
                    </table>
                </div>
            </td>
        </tr>
    </tbody>
</table>
<script>
    function totalSum() {
        let D1, D2, D3, D4, D5;
        D1 = D2 = D3 = D4 = D5 = 0;
        for (var j = 0; j < $(".cssD1").length; j++) {
            $('.cssD2').eq(j).val((parseInt($('.cssD1').eq(j).val()) * 0.02));
            $('.cssD4').eq(j).val((parseInt($('.cssD1').eq(j).val()) + parseInt($('.cssD3').eq(j).val())));
            $('.cssD5').eq(j).val((parseInt($('.cssD4').eq(j).val()) * 0.02));
            D1 += parseInt($('.cssD1').eq(j).val());
            D2 += parseInt($('.cssD2').eq(j).val());
            D3 += parseInt($('.cssD3').eq(j).val());
            D4 += parseInt($('.cssD4').eq(j).val());
            D5 += parseInt($('.cssD5').eq(j).val());
        }
        $('#ts01').val(D1);
        $('#ts02').val(D2);
        $('#ts03').val(D3);
        $('#ts04').val(D4);
        $('#ts05').val(D5);
    }
    $("#idNamBC").html($('#slNamBc').val());
    totalSum();
    $('.number2').number(true, 2);
</script>