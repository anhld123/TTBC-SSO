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
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <style>
            *{
                font-family: tahoma;
                font-size: 12px;
            }
            table {
                border-collapse: collapse;
                width: 150%;
                /*height: 1000px;*/
            }

            table thead { position: sticky; top: 0; z-index: 1; }

            th, td {
                text-align: center;
                padding: 8px;
                border: 1PX solid #f;
            }

            tr:nth-child(even){background-color: #f2f2f2}

            th {
                background-color: #04AA6D;
                color: white;
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
                height: 530px;
            }
            .cmd, input[type="submit"]{
                padding: 5px;
                background-image: linear-gradient(#f2f2f2,#c2c2c2);
                border: 1px solid #c2c2c2;
                border-radius: 2px;
            }

            .CLS-BOLD{
                font-weight: bold;
            }
            #divTitle{
                font: 14px Arial, Helvetica, sans-serif;
                font-weight: bold;
                color: #0077b3;
                text-align: center;
            }
        </style>  
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
                $('.Bold_1').css({"font-weight": "bold"});
                $('.Italic_1').css({"font-style": "italic"});
                $('.number2').number(true, 2);
                $(".TD_STT").css({"width": "30px"});
                $(".TD_GIATRI").css({"width": "100px"});
                $(".TD_TEN").css({"width": "80px"});
                $(".TD_CHITIEU").css({"width": "20%"});

            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>     


        <style>                                                
            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }            
        </style>

    </head>
    <body style="font-family: ">
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            <br>
            <s:hidden name="namBc_pre"/>
            <div id="divTitle">
                KẾ HOẠCH TÍN DỤNG CỦA PGD                   
            </div>
            <s:hidden name="khoa_nhaptaycn"/>
            </br>
            <div style=" width: 98vw;height: 400px;">
                <table>
                    <thead>
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
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                                    
                        <tr> 
                            <td style="text-align: center"
                                class="<s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> Bold_1</s:if>
                                <s:elseif test="KIEUIN.toString().equalsIgnoreCase('2')"> Italic_1</s:elseif>
                                <s:else></s:else>">
                                <s:property value="TT_HIENTHI"/>
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

                </table> 
            </div>
        </s:form>

        <div id="luu_thanhcong"></div>
    </body>


</html>
