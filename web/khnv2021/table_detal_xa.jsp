
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<style>
    #subTable {
        font-size: 12px; /* 👈 chữ to hơn */
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        border-collapse: collapse;
        width: 99%;
        margin: auto;
        background-color: #fff;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        border: #000;
        border-radius: 10px;
        overflow: hidden;
    }

    #subTable th {
        background-color: #f0f4f8;
        color: #2a3f54;
        padding: 14px;
        text-align: center;
        font-weight: bold;
        font-size: 10px;
    }

    #subTable td {
        padding: 12px;
        border-bottom: 1px solid #ccc;
        /*text-align: center;*/
        font-size: 10.5px;
    }
    #subTable tbody tr:nth-child(odd) {
        background-color: #ffffff; /* trắng */
    }

    #subTable tbody tr:nth-child(even) {
        background-color: #f3f8ff; /* xanh nhạt */
    }

    #subTable tbody tr:hover {
        background-color: #dbeafe;
        transition: background-color 0.2s ease;
    }


</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            function initSubForm() {
                $('.D0').css({"text-align": "center"});
                $('.Bold_1').css({"font-weight": "bold"});
                $('.Italic_1').css({"font-style": "italic"});
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
                $('.number, .number2').css({
                    'text-align': 'right'
                });
                $('.number2').each(function () {
                    var number = parseFloat($(this).text().trim());
                    if (!isNaN(number)) {
                        var formattedNumber = number.toLocaleString('en-US', {
                            minimumFractionDigits: 2,
                            maximumFractionDigits: 2
                        });
                        $(this).text(formattedNumber);
                    }
                });
            }
            initSubForm();

        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;">             
            <div id="divTitle"
                 style="margin: 10px 0; text-align: center; font-weight: bold; font-size: 16px; text-transform: uppercase;">
                KẾ HOẠCH TÍN DỤNG <s:property value="ten_thon" /> GIAI ĐOẠN <s:property value="namBc" /> - <s:property value="namBc_4" />
            </div>
            <!--<div style="height:10px"></div>-->  
            <div id="divDonvitinh">
                Đơn vị: triệu đồng.
            </div>
            <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px"> 

                <tr>
                    <th rowspan="3" class="TD_STT D0">STT</th>
                    <th rowspan="3" class="TD_CHITIEU D0">CHỈ TIÊU</th>
                    <th rowspan="3" class="TD_GIATRI D0">Ước thực hiện đến 31/12/<s:property value="namBc_2pre"/></th>
                    <th rowspan="3" class="TD_GIATRI D0">Ước thực hiện đến 31/12/<s:property value="namBc_pre"/></th>
                    <th colspan="3" class="D0">Kế hoạch tín dụng năm <s:property value="namBc"/></th>
                    <th colspan="3" class="D0">Kế hoạch tín dụng năm <s:property value="namBc_2"/></th>
                    <th colspan="3" class="D0">Kế hoạch tín dụng năm <s:property value="namBc_3"/></th>
                    <th colspan="5" class="D0">Kế hoạch tín dụng năm <s:property value="namBc_4"/></th>
                </tr>
                <tr>
                    <th rowspan="2" class="D0">Tổng số</th>
                    <th colspan="2" class="D0">Tăng, giảm so với 31/12/<s:property value="namBc_pre"/></th>
                    <th rowspan="2" class="D0">Tổng số</th>
                    <th colspan="2" class="D0">Tăng, giảm so với 31/12/<s:property value="namBc"/></th>
                    <th rowspan="2" class="D0">Tổng số</th>
                    <th colspan="2" class="D0">Tăng, giảm so với 31/12/<s:property value="namBc_2"/></th>
                    <th rowspan="2" class="D0">Tổng số</th>
                    <th colspan="2" class="D0">Tăng, giảm so với 31/12/<s:property value="namBc_3"/></th>
                    <th colspan="2" class="D0">Tăng, giảm so với 31/12/<s:property value="namBc_2pre"/></th>

                </tr>
                <tr>
                    <th class="D0">Số tuyệt đối (+/-)</th>
                    <th class="D0">Số tương đối (%)</th>
                    <th class="D0">Số tuyệt đối (+/-)</th>
                    <th class="D0">Số tương đối (%)</th>
                    <th class="D0">Số tuyệt đối (+/-)</th>
                    <th class="D0">Số tương đối (%)</th>
                    <th class="D0">Số tuyệt đối (+/-)</th>
                    <th class="D0">Số tương đối (%)</th>
                    <th class="D0">Số tuyệt đối (+/-)</th>
                    <th class="D0">Số tương đối (%)</th>
                </tr>

                <tr>
                    <s:iterator begin="1" end="18" status="st">
                        <th style="color:#000;font-style:italic;font-size:xx-small;padding:2px 0;line-height:12px;">
                            (<s:property value="#st.count"/>)
                        </th>
                    </s:iterator>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr <s:if test="D20.equalsIgnoreCase('1')">style="font-weight:bold;"</s:if><s:elseif test="D20.equalsIgnoreCase('3')"> style="font-style:italic;"</s:elseif>>

                                <td><s:property  value="TT_HIENTHI" /></td>
                        <td><s:property  value="TEN" /></td>
                        <td class="number"> <s:property value="D1"/></td>
                        <td class="number"> <s:property value="D2"/></td>
                        <td class="number"> <s:property value="D3"/></td>
                        <td class="number2"> <s:property value="D4"/></td>
                        <td class="number2"> <s:property value="D5"/></td>
                        <td class="number"> <s:property value="D6"/></td>
                        <td class="number2"> <s:property value="D7"/></td>
                        <td class="number2"> <s:property value="D8"/></td> 
                        <td class="number"> <s:property value="D9"/></td> 
                        <td class="number2"> <s:property value="D10"/></td> 
                        <td class="number2"> <s:property value="D11"/></td> 
                        <td class="number"> <s:property value="D12"/></td> 
                        <td class="number2"> <s:property value="D13"/></td> 
                        <td class="number2"> <s:property value="D14"/></td> 
                        <td class="number2"> <s:property value="D15"/></td> 
                        <td class="number2"> <s:property value="D16"/></td> 
                    </tr>
                </s:iterator>
            </table>
        </div>      
        <div id="luu_thanhcong"></div>
    </body>

</html>
