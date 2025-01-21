<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<style>
    #subTable {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 130%;
    }
    #subTable th{
        background-color: #ddd;
        color: #0000FF;
    }

    #subTable th, #subTable td {
        border: 1px solid gray;
    }

    #subTable tr:nth-child(even){background-color: #f2f2f2;}

    #subTable tr:hover {background-color: #ddd;}

    .txtPublic{
        width: 85px;
    }
    .ui-datepicker-trigger{
        height: 100%;
    }
    .txtBody{
        text-align: center;
    }
    .txtBody > .ui-datepicker-trigger{
        display: none;
    }
    td.hdtitle {
        position: static;
        top: 0;
        z-index: 10;
    }
    @-webkit-keyframes my {
        0% { color: red; } 
        50% { color: #fff;  } 
        100% { color: red;  } 
    }
    @-moz-keyframes my { 
        0% { color: red;  } 
        50% { color: #fff;  }
        100% { color: red;  } 
    }
    @-o-keyframes my { 
        0% { color: red; } 
        50% { color: #fff; } 
        100% { color: red;  } 
    }
    @keyframes my { 
        0% { color: red;  } 
        50% { color: #fff;  }
        100% { color: red;  } 
    } 
    .color_11 {
        background:#fff;
        font-size:14px;
        font-weight:bold;
        -webkit-animation: my 700ms infinite;
        -moz-animation: my 700ms infinite; 
        -o-animation: my 700ms infinite; 
        animation: my 700ms infinite;
    }
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".SOKU").css({"width": "100%"});
                $(".TD_CHECKBOX").css({"width": "20px"});
                $(".TD_SOKU").css({"width": "80px"});
                $(".TD_TENKH123").css({"width": "110px"});
                $(".TD_TENTS").css({"width": "190px"});
                $(".TD_SOTK").css({"width": "105px"});
                $(".TD_MAKH").css({"width": "60px"});
                $(".TD_THOIGIAN").css({"width": "55px"});
                $(".TD_MAPGD").css({"width": "45px"});
                $(".TD_BUTTON1").css({"width": "40px"});
                $(".TD_SOTIEN").css({"width": "200px"});
                $(".TEN_KH").css({"width": "100px"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });

            function initTable()
            {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    var matmp = getMabyNumber(i);//    
                    if (matmp === 1)
                    {
                        $('input:checkbox[id=' + i + ']').attr('checked', true);
                    }
                }
                autoEvaluate();
            }

            function getMabyNumber(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }

        </script>

    </head>
    <body>
        <div style="overflow:scroll; width: 98%;height: 400px;">    

            <div id="divTitle">
                BÁO CÁO 10% SỐ TIỀN LÃI THU ĐƯỢC ĐỂ BỔ SUNG VÀO NGUỒN VỐN QUỸ QUỐC GIA VỀ VIỆC LÀM<br> 
                <s:if test="chotsl.equalsIgnoreCase('1')" ><a class="color_11">(Đơn vị đã gửi dữ liệu)</a></s:if>
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(CN đã gửi dữ liệu)</a></s:if>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
            </div>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr>      
                    <th rowspan="2" class="TD_CHECKBOX">TT</th>
                        <s:if test="!Grade.equalsIgnoreCase('3')">
                        <th rowspan="2" class="TD_SOTIEN">Tên PGD</th>    
                        </s:if>
                        <s:if test="Grade.equalsIgnoreCase('3')">
                        <th rowspan="2" class="TD_SOTIEN">Tên chi nhánh</th>    
                        </s:if>
                    <th rowspan="2" class="TEN_KH">Hạch toán</th>
                    <th colspan="2" class="hdtitle">Ủy ban nhân dân tỉnh quản lý</th>
                    <th colspan="2" class="hdtitle">Tổng LĐ lao động Việt Nam</th>
                    <th colspan="2" class="hdtitle">TW Đoàn TNCS Hồ Chí Minh</th>
                    <th colspan="2" class="hdtitle">TW Hội Liên hiệp Phụ nữ VN</th>
                    <th colspan="2" class="hdtitle">Hội Nông dân Việt Nam</th>
                    <th colspan="2" class="hdtitle">Hội Cựu chiến binh Việt Nam</th>
                    <th colspan="2" class="hdtitle">Liên minh Hợp tác xã Việt Nam</th>
                    <th colspan="2" class="hdtitle">Hội Người mù Việt Nam</th>
                </tr>  
                <tr>
                    <th class="TEN_KH">Lãi thu được trong tháng</th>
                    <th class="TEN_KH">Số trích 10% trong tháng</th>

                    <th class="TEN_KH">Lãi thu được trong tháng</th>
                    <th class="TEN_KH">Số trích 10% trong tháng</th>

                    <th class="TEN_KH">Lãi thu được trong tháng</th>
                    <th class="TEN_KH">Số trích 10% trong tháng</th>

                    <th class="TEN_KH">Lãi thu được trong tháng</th>
                    <th class="TEN_KH">Số trích 10% trong tháng</th>

                    <th class="TEN_KH">Lãi thu được trong tháng</th>
                    <th class="TEN_KH">Số trích 10% trong tháng</th>

                    <th class="TEN_KH">Lãi thu được trong tháng</th>
                    <th class="TEN_KH">Số trích 10% trong tháng</th>

                    <th class="TEN_KH">Lãi thu được trong tháng</th>
                    <th class="TEN_KH">Số trích 10% trong tháng</th>

                    <th class="TEN_KH">Lãi thu được trong tháng</th>
                    <th class="TEN_KH">Số trích 10% trong tháng</th>


                </tr>      

                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(8)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(9)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(10)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(11)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(12)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(13)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(14)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(15)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(16)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(17)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(18)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(19)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr> 
                        <td class="D0"> <s:property value="%{#rowstatus.index + 1}" /> </td>
                        <s:if test="macn.equalsIgnoreCase('000000')">
                            <s:if test="D35.equalsIgnoreCase('2')">
                                <td>
                                    <a style="text-decoration: underline" href="#" onclick="cancelAssign('<s:property value="MACN"/>', '', '');"><s:property  value="TEN" /> - Mở dữ liệu</a>
                                </td>
                            </s:if>
                            <s:else>
                                <td style="color: red"><s:property  value="TEN" /> - Chưa chốt DL</td>
                            </s:else>
                        </s:if>
                        <s:else>
                            <s:if test="D35.equalsIgnoreCase('2')"><td style="color: #0000FF"><s:property  value="TEN" /> - Đã chốt DL</td></s:if>
                            <s:else>
                                <td style="color: red"><s:property  value="TEN" /> - Chưa chốt DL</td></s:else>
                        </s:else>
<!--                            <input type="text"   value="<s:property  value="TEN" />" style="text-align: right"
                               class="D00 txtBody" readonly="true" id="TEN_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" 
                               onfocus="this.select();" /> -->
                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" value="<s:property  value="TEN"/>"/>
                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KHOA" value="<s:property  value="KHOA"/>"/>

                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>
                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" value="<s:property  value="TT_HIENTHI"/>"/>
                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>

                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>"/>
                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN" value="<s:property  value="MACN"/>"/>
<!--                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1"/>"/>-->
                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>"/>
                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D3"/>"/>
                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" value="<s:property  value="D4"/>"/>
                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" value="<s:property  value="D5"/>"/>

                    <td class="txtBody" >
                        <input type="text"   value="<s:property  value="D1" />" style="text-align: right"
                               class="number txtBody" id="D1_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                               onfocus="this.select();" readonly="true" /> 
                    </td>
                    <td class="txtBody" >
                        <input type="text"   value="<s:property  value="D6" />" style="text-align: right"
                               class="number txtBody" id="D6_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" onchange="calc(this)" 
                               onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                        </td>
                        <td class="txtBody" >
                            <input type="text"   value="<s:property  value="D7" />" style="text-align: right"
                               class="number txtBody" id="D7_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                               onfocus="this.select();"  readonly="true"/> 
                    </td>
                    <td class="txtBody" >
                        <input type="text"   value="<s:property  value="D9" />" style="text-align: right"
                               class="number txtBody" id="D9_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" onchange="calc(this)"
                               onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                        </td>
                        <td class="txtBody" >
                            <input type="text"   value="<s:property  value="D10" />" style="text-align: right"
                               class="number txtBody" id="D10_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                               onfocus="this.select();" readonly="true"/> 
                    </td>

                    <td class="txtBody" >
                        <input type="text"   value="<s:property  value="D12" />" style="text-align: right"
                               class="number txtBody" id="D12_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" onchange="calc(this)"
                               onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                        </td>
                        <td class="txtBody" >
                            <input type="text"   value="<s:property  value="D13" />" style="text-align: right"
                               class="number txtBody" id="D13_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" 
                               onfocus="this.select();" readonly="true"/> 
                    </td>

                    <td class="txtBody" >
                        <input type="text"   value="<s:property  value="D15" />" style="text-align: right"
                               class="number txtBody" id="D15_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" onchange="calc(this)"
                               onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                        </td>
                        <td class="txtBody" >
                            <input type="text"   value="<s:property  value="D16" />" style="text-align: right"
                               class="number txtBody" id="D16_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" 
                               onfocus="this.select();" readonly="true"/> 
                    </td>

                    <td class="txtBody" >
                        <input type="text"   value="<s:property  value="D18" />" style="text-align: right"
                               class="number txtBody" id="D18_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" onchange="calc(this)"
                               onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                        </td>
                        <td class="txtBody" >
                            <input type="text"   value="<s:property  value="D19" />" style="text-align: right"
                               class="number txtBody" id="D19_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" 
                               onfocus="this.select();" readonly="true"/> 
                    </td>

                    <td class="txtBody" >
                        <input type="text"   value="<s:property  value="D21" />" style="text-align: right"
                               class="number txtBody" id="D21_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" onchange="calc(this)"
                               onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                        </td>
                        <td class="txtBody" >
                            <input type="text"   value="<s:property  value="D22" />" style="text-align: right"
                               class="number txtBody" id="D22_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" 
                               onfocus="this.select();" readonly="true"/> 
                    </td>

                    <td class="txtBody" >
                        <input type="text"   value="<s:property  value="D24" />" style="text-align: right"
                               class="number txtBody" id="D24_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24" onchange="calc(this)"
                               onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                        </td>
                        <td class="txtBody" >
                            <input type="text"   value="<s:property  value="D25" />" style="text-align: right"
                               class="number txtBody" id="D25_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25" 
                               onfocus="this.select();" readonly="true"/> 
                    </td>

                    <td class="txtBody" >
                        <input type="text"   value="<s:property  value="D27" />" style="text-align: right"
                               class="number txtBody" id="D27_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27" onchange="calc(this)"
                               onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                        </td>
                        <td class="txtBody" >
                            <input type="text"   value="<s:property  value="D28" />" style="text-align: right"
                               class="number txtBody" id="D28_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" 
                               onfocus="this.select();" readonly="true"/> 
                    </td>

                    </tr>      
                </s:iterator>
                <tr>
                    <td style="text-align: center; background: #9ad717" ></td>
                    <td style="text-align: center; background: #9ad717;font-weight: bold" >TỔNG CỘNG</td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD1" id="SumD1" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>

                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD6" id="SumD6" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD7" id="SumD7" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD9" id="SumD9" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD10" id="SumD10" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD12" id="SumD12" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD13" id="SumD13" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD15" id="SumD15" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD16" id="SumD16" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD18" id="SumD18" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD19" id="SumD19" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD21" id="SumD21" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD22" id="SumD22" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD24" id="SumD24" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD25" id="SumD25" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD27" id="SumD27" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                    <td style="background: #9ad717;font-weight: bold" >
                        <input type="text" value="0" name="SumD28" id="SumD28" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>

                </tr>
            </table>
        </div>
        <div id="luu_thanhcong"></div>
        <script>
            function autoEvaluate() {
                var arrCot = [".D1", ".D6", ".D7", ".D9", ".D10", ".D12", ".D13", ".D15",
                    ".D16", ".D18", ".D19", ".D21", ".D22", ".D24", ".D25",
                    ".D27", ".D28"]; // Lưu các cột cần tính toán

                // Khai báo các biến tổng cho mỗi cột
                var SumD1 = 0, SumD6 = 0, SumD7 = 0, SumD9 = 0, SumD10 = 0, SumD12 = 0,
                        SumD13 = 0, SumD15 = 0, SumD16 = 0, SumD18 = 0, SumD19 = 0, SumD21 = 0,
                        SumD22 = 0, SumD24 = 0, SumD25 = 0, SumD27 = 0, SumD28 = 0;

                // Duyệt qua các chỉ số để tính tổng
                for (var i = 0; i < 100; i++) {
                    let element;
                    element = document.getElementById("D1_" + i);
                    if (element !== null) {
                        // Cộng hoặc trừ cho các cột tùy vào giá trị
                        SumD1 += processValue(document.getElementById("D1_" + i).value);
                        SumD6 += processValue(document.getElementById("D6_" + i).value);
                        SumD7 += processValue(document.getElementById("D7_" + i).value);
                        SumD9 += processValue(document.getElementById("D9_" + i).value);
                        SumD10 += processValue(document.getElementById("D10_" + i).value);
                        SumD12 += processValue(document.getElementById("D12_" + i).value);
                        SumD13 += processValue(document.getElementById("D13_" + i).value);
                        SumD15 += processValue(document.getElementById("D15_" + i).value);
                        SumD16 += processValue(document.getElementById("D16_" + i).value);
                        SumD18 += processValue(document.getElementById("D18_" + i).value);
                        SumD19 += processValue(document.getElementById("D19_" + i).value);
                        SumD21 += processValue(document.getElementById("D21_" + i).value);
                        SumD22 += processValue(document.getElementById("D22_" + i).value);
                        SumD24 += processValue(document.getElementById("D24_" + i).value);
                        SumD25 += processValue(document.getElementById("D25_" + i).value);
                        SumD27 += processValue(document.getElementById("D27_" + i).value);
                        SumD28 += processValue(document.getElementById("D28_" + i).value);
                    }
                }

                // Gán kết quả vào các ô input
                document.getElementById("SumD1").value = SumD1;
                document.getElementById("SumD6").value = SumD6;
                document.getElementById("SumD7").value = SumD7;
                document.getElementById("SumD9").value = SumD9;
                document.getElementById("SumD10").value = SumD10;
                document.getElementById("SumD12").value = SumD12;
                document.getElementById("SumD13").value = SumD13;
                document.getElementById("SumD15").value = SumD15;
                document.getElementById("SumD16").value = SumD16;
                document.getElementById("SumD18").value = SumD18;
                document.getElementById("SumD19").value = SumD19;
                document.getElementById("SumD21").value = SumD21;
                document.getElementById("SumD22").value = SumD22;
                document.getElementById("SumD24").value = SumD24;
                document.getElementById("SumD25").value = SumD25;
                document.getElementById("SumD27").value = SumD27;
                document.getElementById("SumD28").value = SumD28;

                // Định dạng lại số
                $('.number').number(true, 0);
            }

            function processValue(value) {
                var num = parseInt(value.replaceAll(',', ''));
                if (isNaN(num)) {
                    return 0;
                }
                return num; // Trả về số đã xử lý (âm hay dương đều sẽ được xử lý đúng)
            }


            initTable();

            function calc(id) {
                var row = id.parentNode.parentNode;
                var CT_D6 = row.cells[3].getElementsByTagName('input')[0].value;
                var CT_D9 = row.cells[5].getElementsByTagName('input')[0].value;
                var CT_D12 = row.cells[7].getElementsByTagName('input')[0].value;
                var CT_D15 = row.cells[9].getElementsByTagName('input')[0].value;
                var CT_D18 = row.cells[11].getElementsByTagName('input')[0].value;
                var CT_D21 = row.cells[13].getElementsByTagName('input')[0].value;
                var CT_D24 = row.cells[15].getElementsByTagName('input')[0].value;
                var CT_D27 = row.cells[17].getElementsByTagName('input')[0].value;

//                var CT_D7 = row.cells[4].getElementsByTagName('input')[0].value;
//                var CT_D10 = row.cells[6].getElementsByTagName('input')[0].value;
//                var CT_D13 = row.cells[8].getElementsByTagName('input')[0].value;
//                var CT_D16 = row.cells[10].getElementsByTagName('input')[0].value;
//                var CT_D19 = row.cells[12].getElementsByTagName('input')[0].value;
//                var CT_D22 = row.cells[14].getElementsByTagName('input')[0].value;
//                var CT_D25 = row.cells[16].getElementsByTagName('input')[0].value;
//                var CT_D28 = row.cells[18].getElementsByTagName('input')[0].value;

                var res4 = Math.round(parseFloat(CT_D6.replace(/,/g, '')) * 10 / 100);
                row.cells[4].getElementsByTagName('input')[0].value = res4.toLocaleString('en-US');
                var res6 = Math.round(parseFloat(CT_D9.replace(/,/g, '')) * 10 / 100);
                row.cells[6].getElementsByTagName('input')[0].value = res6.toLocaleString('en-US');
                var res8 = Math.round(parseFloat(CT_D12.replace(/,/g, '')) * 10 / 100);
                row.cells[8].getElementsByTagName('input')[0].value = res8.toLocaleString('en-US');
                var res10 = Math.round(parseFloat(CT_D15.replace(/,/g, '')) * 10 / 100);
                row.cells[10].getElementsByTagName('input')[0].value = res10.toLocaleString('en-US');
                var res12 = Math.round(parseFloat(CT_D18.replace(/,/g, '')) * 10 / 100);
                row.cells[12].getElementsByTagName('input')[0].value = res12.toLocaleString('en-US');
                var res14 = Math.round(parseFloat(CT_D21.replace(/,/g, '')) * 10 / 100);
                row.cells[14].getElementsByTagName('input')[0].value = res14.toLocaleString('en-US');
                var res16 = Math.round(parseFloat(CT_D24.replace(/,/g, '')) * 10 / 100);
                row.cells[16].getElementsByTagName('input')[0].value = res16.toLocaleString('en-US');
                var res18 = Math.round(parseFloat(CT_D27.replace(/,/g, '')) * 10 / 100);
                row.cells[18].getElementsByTagName('input')[0].value = res18.toLocaleString('en-US');
                autoEvaluate();
            }
                 function cancelAssign(D2, D5, D6) {
//                alert(D2 + ' ' + D5);
                $.ajax({
                    type: "GET",
                    url: "unlock_GQVL_01_c3?" + "madiemgd=" + D2 + "&ssngaybc=" + D5 + "&skhoa=" + D6,
                    success: function (res) {
                        var status = parseInt(res.status);
                        //alert(status);
                        if (status === 1) {
                            alert('Mở phê duyệt thành công!');
                            onLoadData();
                        } else {
                            alert('Mở phê duyệt lỗi: ' + res.message);
                        }
                    },
                    error: function (res) {
                        alert("Mở phê duyệt lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                    }
                });
            }
        </script>
    </body>
</html>
