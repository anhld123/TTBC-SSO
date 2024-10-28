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
        width: 98%;
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
            var popWindow;
            var max_row = 0;
            $(document).ready(function () {
                initTable();
            });
            $(document).ready(function () {
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".STT1").css({"width": "30px"});
                $(".STT2").css({"width": "90px"});
                $(".STT3").css({"width": "150"});
                $(".STT4").css({"width": "200px"});
                $(".STT5").css({"width": "70px"});
                $(".STT6").css({"width": "65px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "220px"});
                $(".TEN_KH").css({"width": "100%"});
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
                    try {
                        var D10 = document.getElementById("D10_" + i).value;
                        document.getElementById("D10_" + i).disabled = true;

                        document.getElementById("D11_" + i).disabled = true;
                        if (D10 === "1")
                        {
                            document.getElementById("D10_" + i).checked = true;
//                            document.getElementById("D11_" + i).disabled = false;
                            document.getElementById("D13_" + i).disabled = false;

                        } else
                        {
//                            document.getElementById("D11_" + i).disabled = true;
//                            document.getElementById("D13_" + i).disabled = true;
                        }
                        var D12 = document.getElementById("D13_" + i).value;
                        if (D12 === "1") {
                            document.getElementById("D13_" + i).checked = true;
                            document.getElementById("D14_" + i).disabled = false;
                        } else {
                            document.getElementById("D14_" + i).disabled = true
                        }
                    } catch (e) {
                    }
                }

            }
        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 90%;height: 400px;">             
            <div id="divTitle">
                DANH SÁCH KHÁCH HÀNG ĐƯỢC LOẠI TRỪ CHẤM ĐIỂM CHẤT LƯỢNG TÍN DỤNG DO NNKQ
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
                <input type="hidden" value="<s:property value="chotsl_tw"/>" name="chotsl_tw" id="chotsl_tw"/> 
            </div>
            <div style="color: red; background: yellow; text-align: left; font-weight: bold; width: 98%; font-size: 14px">
                <s:property value="title1" />
            </div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th class="STT1" rowspan="2">STT</th>                           
                    <th class="STT6" rowspan="2">Mã khách hàng</th>  
                    <th class="STT2" rowspan="2">Họ tên</th>  
                    <th class="STT6" rowspan="2">Ngày tháng năm sinh</th>
                    <th class="STT4" rowspan="2">Địa chỉ</th>
                    <th class="STT6" colspan="3">Thông tin CMTND/CCDC</th>
                    <th class="STT2" rowspan="1">Loại trừ</th>  
                    <th class="STT4" rowspan="2">Nguyên nhân loại trừ</th>  
                    <th class="STT2" rowspan="1">Phát sinh trả lãi</th>  
                    <th class="STT4" rowspan="2">Nguyên nhân giải trình</th>  
                </tr>
                <tr>
                    <th class="STT2">Số</th>      
                    <th class="STT2">Ngày cấp</th> 
                    <th class="STT2">Nơi cấp</th> 
                    <th><input type="checkbox" id ="select-all1"/></th>
                    <th><input type="checkbox" id ="select-all2"/></th>
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
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" />
                            <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                            <input type="hidden" value="<s:property value="%{#rowstatus.index + 1}" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                            <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
                            <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                            <input type="hidden" value="<s:property  value="CO_TONGHOP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/>
                            <input type="hidden" value="<s:property  value="NGUOI_NHAP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_NHAP"/>
                            <input type="hidden" value="<s:property  value="NAMBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NAMBC"/>
                            <input type="hidden" value="<s:property  value="MAPGD" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                            <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>
                            <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>
                            <input type="hidden" value="<s:property  value="D2" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2"/>
                            <input type="hidden" value="<s:property  value="D3" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                            <input type="hidden" value="<s:property  value="D4" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"/>
                            <input type="hidden" value="<s:property  value="D5" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"/>
                            <input type="hidden" value="<s:property  value="D6" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6"/>
                            <input type="hidden" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7"/>
                            <input type="hidden" value="<s:property  value="D8" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"/>
                            <input type="hidden" value="<s:property  value="D9" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/>
                            <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>


                        </td>
                        <td><s:property value="D1" /></td>
                        <td><s:property value="D2" /></td>
                        <td class="D0"><s:property value="D3" /></td>
                        <td><s:property value="D12" /></td>
                        <td class="D0"><s:property value="D4" /></td>
                        <td class="D0"><s:property value="D6" /></td>
                        <td><s:property value="D5" /></td>
                        <td class="D0">
                            <input type="checkbox" id ="D10_<s:property value="%{#rowstatus.index}" />" 
                                   onclick="$(this).val(this.checked ? 1 : 0)" class="myCheckBox1"
                                   oninput="onSelectChange_dnht2(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                   id="D10_<s:property  value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D10" value="<s:property  value="D10" />"/>      
                        </td>
                        <td class="D0">
                            <textarea style="width: 98%" placeholder="Nhập tối đa 500 ký tự" id="D11_<s:property  value='%{#rowstatus.index}' />" 
                                      name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D11" maxlength="500"><s:property value='D11'/></textarea>
                        </td>
                        <s:if test="D10.equalsIgnoreCase('1')">
                        <input type="hidden" value="<s:property  value="D10" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10"/>
                        <input type="hidden" value="<s:property  value="D11" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11"/>

                        <td class="D0">
                            <input type="checkbox" id ="D13_<s:property value="%{#rowstatus.index}" />" 
                                   onclick="$(this).val(this.checked ? 1 : 0)" class="myCheckBox2"
                                   oninput="onSelectChange_dnht3(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                   id="D13_<s:property  value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D13" value="<s:property  value="D13" />"/>      
                        </td>
                        <td class="D0">
                            <textarea style="width: 98%" placeholder="Nhập tối đa 500 ký tự" id="D14_<s:property  value='%{#rowstatus.index}' />" 
                                      name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D14" maxlength="500"><s:property value='D14'/></textarea>
                        </td>
                    </s:if>
                    <s:else><td></td><td></td></s:else>
                        </tr>
                </s:iterator>
            </table>
        </div>
        <div id="luu_thanhcong"></div>
        <script>

            function onSelectChange_dnht2(value, index) {
                if (value === '1')
                {
                    document.getElementById("D11_" + index).disabled = false;
                    document.getElementById("D11_" + index).value = "Ảnh hưởng bão số 3 (Yagi)";
                    document.getElementById("D13_" + index).disabled = false;
                } else
                {
                    document.getElementById("D11_" + index).disabled = true;
                    document.getElementById("D11_" + index).value = "";
                    document.getElementById("D13_" + index).disabled = true;
                    document.getElementById("D13_" + index).checked = false;
                    document.getElementById("D14_" + index).disabled = true;
                    document.getElementById("D14_" + index).value = "";
                }
            }

            $(function () {
                $('#select-all1').click(function () {
                    const isChecked = $('#select-all1').prop('checked');

                    // Lặp qua các checkbox và cập nhật trạng thái
                    $('.myCheckBox1').each(function (index) {
                        if (!this.disabled) {
                            this.checked = isChecked;
                            this.value = isChecked ? '1' : '0';
                            onSelectChange_dnht2(this.value, index);
                        }
                    });
                });
            });
            $(function () {
                $('#select-all2').click(function () {
                    const isChecked = $('#select-all2').prop('checked');

                    // Lặp qua các checkbox và cập nhật trạng thái
                    $('.myCheckBox2').each(function (index) {
                        if (!this.disabled) {
                            this.checked = isChecked;
                            this.value = isChecked ? '1' : '0';
                            onSelectChange_dnht3(this.value, index);
                        }
                    });
                });
            });
            function onSelectChange_dnht3(value, index) {
                if (value === '1')
                {
                    document.getElementById("D14_" + index).disabled = false;
                } else
                {
                    document.getElementById("D14_" + index).disabled = true;
                }
            }
        </script>
    </body>
</html>
