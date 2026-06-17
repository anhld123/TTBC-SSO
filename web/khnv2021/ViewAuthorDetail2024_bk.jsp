<%-- 
    Document   : ViewAuthorDetail
    Created on : Jun 18, 2021, 9:44:51 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<style>
    *{
        font-family: tahoma;
        font-size: 13px;
    }

    table {
        width : 100%;
        border-top: 1px solid orange;
        border-left: 1px solid #c2c2c2;
        border-right: 1px solid #c2c2c2;
        border-bottom: 1px solid #c2c2c2;
        text-align : center;
        border-collapse : collapse;
    }
    table tr th, table tr td {
        border : 1px solid #c2c2c2;
    }


    table thead th {
        position: -webkit-sticky;
        position : sticky;
        top : 0;
        color: white;
        background-color : #04AA6D;
    }

    /* here is the trick */
    table tbody:nth-of-type(1) tr:nth-of-type(1) td {
        border-top: none !important;
    }
    table thead th {
        border-top: none !important;
        border-bottom: none !important;
        box-shadow: inset 0 0px 0 #c2c2c2,
            inset 0 -1px 0 #c2c2c2;
    }

    table thead th {
        background-clip: padding-box
    }

    table thead { position: sticky; top: 0; z-index: 1; }

    th, td {
        text-align: left;
        border: 1px solid #c2c2c2;
        text-align: center;
        padding: 3px;
    }

    th{
        padding: 8px;
    }
    .sttCol>td{
        font-style: italic;
    }
    .clss-body-ngnhan{
        box-sizing: content-box;
        padding: 5px;
    }
    textarea
    {
        border:1px solid #000;
        width:100%;
        height: 100px;
    }
    .clss-lable{
        font-weight: bold;
    }
    .cls-over{
        overflow-y: scroll;
        height: 67vh;
    }
    .cmd{
        padding: 5px;
        background-image: linear-gradient(#f2f2f2,#c2c2c2);
        border: 1px solid #c2c2c2;
        border-radius: 2px;
        z-index: 99;
        margin-left: 5px;
    }
    hr{
        border-bottom: 0px;
        border-top: 1px solid lightgray;
    }
    .item {
        padding: 5px;
        text-align: right;
        border: 0px !important;
        outline: none;
    }
    .cls {
        background-color: orange;
    }
</style>
<script>
    var max_row = 0;
    var max_row = 0;
    $(document).ready(function () {
//                $('input.number').css({"text-align": "right"});
//                $('input.number2').css({"text-align": "right"});
        $('.D0').css({"text-align": "center"});
//                $('.number').number(true, 0);
        $('.Bold_1').css({"font-weight": "bold"});
        $('.Italic_1').css({"font-style": "italic"});
//                $('.number2').number(true, 1);
        $(".TD_STT").css({"width": "30px"});
        $(".TD_GIATRI").css({"width": "100px"});
        $(".TD_TEN").css({"width": "80px"});
        $(".TD_CHITIEU").css({"width": "20%"});
        $('.number').each(function () {
            var number = parseFloat($(this).text().trim());
            if (!isNaN(number)) {
                var roundedNumber = Math.abs(Math.round(number));
                var formattedNumber = roundedNumber.toLocaleString('en-US'); // Sử dụng dấu phân tách hàng nghìn là ","
                if (number < 0) {
                    $(this).text("-" + formattedNumber);
                } else {
                    $(this).text(formattedNumber);
                }
            }
        });

        $('.number2').each(function () {
            var number = parseFloat($(this).text().trim());
            if (!isNaN(number)) {
                var formattedNumber = number.toLocaleString('en-US', {minimumFractionDigits: 1, maximumFractionDigits: 1});
                $(this).text(formattedNumber);
            }
        });
    });
    $('.TEN_KH').focus(function () {
        $(this).closest('tr').addClass('highlight_row');
    });
    $('.TEN_KH').blur(function () {
        $(this).closest('tr').removeClass('highlight_row');
    });
</script>
<table>
    <thead>
        <!--        <tr>
                    <td colspan="3" style="text-align: left; border: 0px; font-weight: bold; background-color: orange ;"><span id="strHeader" style="text-transform: uppercase; color: white;"></span></td>
                    <td colspan="4" style="text-align: right; border: 0px;font-style: italic;background-color: orange; color: white;">Đơn vị: triệu đồng, %, hộ, người</td>
                </tr>-->
        <tr>
            <th rowspan="3" class="TD_STT D0">STT</th>
            <th rowspan="3" class="TD_CHITIEU D0">CHỈ TIÊU</th>
            <th rowspan="3" class="TD_GIATRI D0">Ước thực hiện đến 31/12/<s:property value="namBc_2pre"/></th>
            <th rowspan="3" class="TD_GIATRI D0">Ước thực hiện đến 31/12/<s:property value="namBc_pre"/></th>
            <th colspan="3" class="D0">Kế hoạch tín dụng năm <s:property value="namBc"/></th>
            <th colspan="3" class="D0">Kế hoạch tín dụng năm <s:property value="namBc_1"/></th>
            <th colspan="3" class="D0">Kế hoạch tín dụng năm <s:property value="namBc_2"/></th>
            <th colspan="3" class="D0">Kế hoạch tín dụng năm <s:property value="namBc_3"/></th>
            <th colspan="5" class="D0">Kế hoạch tín dụng năm <s:property value="namBc_4"/></th>
        </tr>
        <tr>
            <th rowspan="2" class="TD_GIATRI D0">Tổng số</th>
            <th colspan="2" class="TD_GIATRI D0">Tăng, giảm so với 31/12/<s:property value="namBc_pre"/></th>
            <th rowspan="2" class="TD_GIATRI D0">Tổng số</th>
            <th colspan="2" class="TD_GIATRI D0">Tăng, giảm so với 31/12/<s:property value="namBc"/></th>
            <th rowspan="2" class="TD_GIATRI D0">Tổng số</th>
            <th colspan="2" class="TD_GIATRI D0">Tăng, giảm so với 31/12/<s:property value="namBc_1"/></th>
            <th rowspan="2" class="TD_GIATRI D0">Tổng số</th>
            <th colspan="2" class="TD_GIATRI D0">Tăng, giảm so với 31/12/<s:property value="namBc_2"/></th>
            <th rowspan="2" class="TD_GIATRI D0">Tổng số</th>
            <th colspan="2" class="TD_GIATRI D0">Tăng, giảm so với 31/12/<s:property value="namBc_3"/></th>
            <th colspan="2" class="TD_GIATRI D0">Tăng, giảm so với 31/12/<s:property value="namBc_pre"/></th>

        </tr>
        <tr>
            <th class="D0 TD_GIATRI">Số tuyệt đối (+/-)</th>
            <th class="D0 TD_GIATRI">Số tương đối (%)</th>
            <th class="D0 TD_GIATRI">Số tuyệt đối (+/-)</th>
            <th class="D0 TD_GIATRI">Số tương đối (%)</th>
            <th class="D0 TD_GIATRI">Số tuyệt đối (+/-)</th>
            <th class="D0 TD_GIATRI">Số tương đối (%)</th>
            <th class="D0 TD_GIATRI">Số tuyệt đối (+/-)</th>
            <th class="D0 TD_GIATRI">Số tương đối (%)</th>
            <th class="D0 TD_GIATRI">Số tuyệt đối (+/-)</th>
            <th class="D0 TD_GIATRI">Số tương đối (%)</th>
            <th class="D0 TD_GIATRI">Số tuyệt đối (+/-)</th>
            <th class="D0 TD_GIATRI">Số tương đối (%)</th>
        </tr>
        <tr class="sttCol">
            <th style="font-style: italic; font-size: xx-small;">(1)</th>
            <th style="font-style: italic; font-size: xx-small;">(2)</th>
            <th style="font-style: italic; font-size: xx-small;">(3)</th>
            <th style="font-style: italic; font-size: xx-small;">(4)</th>
            <th style="font-style: italic; font-size: xx-small;">(5)</th>
            <th style="font-style: italic; font-size: xx-small;">(6)</th>
            <th style="font-style: italic; font-size: xx-small;">(7)</th>
            <th style="font-style: italic; font-size: xx-small;">(8)</th>
            <th style="font-style: italic; font-size: xx-small;">(9)</th>
            <th style="font-style: italic; font-size: xx-small;">(10)</th>
            <th style="font-style: italic; font-size: xx-small;">(11)</th>
            <th style="font-style: italic; font-size: xx-small;">(12)</th>
            <th style="font-style: italic; font-size: xx-small;">(13)</th>
            <th style="font-style: italic; font-size: xx-small;">(14)</th>
            <th style="font-style: italic; font-size: xx-small;">(15)</th>
            <th style="font-style: italic; font-size: xx-small;">(16)</th>
            <th style="font-style: italic; font-size: xx-small;">(17)</th>
            <th style="font-style: italic; font-size: xx-small;">(18)</th>
            <th style="font-style: italic; font-size: xx-small;">(19)</th>
            <th style="font-style: italic; font-size: xx-small;">(20)</th>
            <th style="font-style: italic; font-size: xx-small;">(21)</th>
        </tr>
    </thead>                                  
    <s:iterator value="#attr.lstData" var="modelView" status="rowstatus">                                                    
        <tr> 
            <td style="text-align: center"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')"> Italic_1</s:elseif>
                <s:else></s:else>">
                <s:property value="TT_HIENTHI"/>
                <input type="hidden" value="<s:property  value="TT_HIENTHI" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>                             
                <input type="hidden" value="<s:property  value="TEN" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                <input type="hidden" value="<s:property  value="MA" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA"/>
                <input type="hidden" value="<s:property  value="D1" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D1"/> 
                <input type="hidden" value="<s:property  value="D2" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D2"/>                             
                <input type="hidden" value="<s:property  value="D3" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D3"/>                             
                <input type="hidden" value="<s:property  value="D4" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D4"/>                             
                <input type="hidden" value="<s:property  value="D5" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D5"/>                             
                <input type="hidden" value="<s:property  value="D6" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D6"/>                             
                <input type="hidden" value="<s:property  value="D7" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D7"/>                             
                <input type="hidden" value="<s:property  value="D8" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D8"/>                             
                <input type="hidden" value="<s:property  value="D9" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D9"/>                             
                <input type="hidden" value="<s:property  value="D10" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D10"/>                             
                <input type="hidden" value="<s:property  value="D11" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D11"/>                             
                <input type="hidden" value="<s:property  value="D12" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D12"/>                             
                <input type="hidden" value="<s:property  value="D13" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D13"/>                             
                <input type="hidden" value="<s:property  value="D14" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D14"/>                             
                <input type="hidden" value="<s:property  value="D15" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D15"/>                             
                <input type="hidden" value="<s:property  value="D16" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D16"/>                             
                <input type="hidden" value="<s:property  value="D17" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D17"/>                             
                <input type="hidden" value="<s:property  value="D18" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D18"/>                             
                <input type="hidden" value="<s:property  value="D19" />" name="lstData[<s:property  value="%{#rowstatus.index}" />].D19"/>                             

            </td>

            <td style="text-align: left"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')"> Italic_1</s:elseif>
                <s:else></s:else>">
                <s:property value="TEN"/>
            </td>

            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D1"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D2"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D3"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D4"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number2 Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number2 Italic_1</s:elseif>
                <s:else>number2</s:else>">
                <s:property value="D5"/>
            </td> 
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D6"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D7"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number2 Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number2 Italic_1</s:elseif>
                <s:else>number2</s:else>">
                <s:property value="D8"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D9"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D10"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number2 Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number2 Italic_1</s:elseif>
                <s:else>number2</s:else>">
                <s:property value="D11"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D12"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D13"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number2 Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number2 Italic_1</s:elseif>
                <s:else>number2</s:else>">
                <s:property value="D14"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D15"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D16"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number2 Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number2 Italic_1</s:elseif>
                <s:else>number2</s:else>">
                <s:property value="D17"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number Italic_1</s:elseif>
                <s:else>number</s:else>">
                <s:property value="D18"/>
            </td>
            <td style="text-align:right"
                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')">number2 Bold_1</s:if>
                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')">number2 Italic_1</s:elseif>
                <s:else>number2</s:else>">
                <s:property value="D19"/>
            </td>

        </tr>     
    </s:iterator>
</tbody>
</table>
<script>

</script>
