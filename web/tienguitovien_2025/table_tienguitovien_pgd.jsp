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
                $(".STT1").css({"width": "50px"});
                $(".STT2").css({"width": "80px"});
                $(".STT3").css({"width": "150"});
                $(".STT4").css({"width": "200px"});
                $(".STT5").css({"width": "70px"});
                $(".STT6").css({"width": "65px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "220px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $(document).ready(function () {
                initTable();
            });
            function initTable()
            {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    try {
                        var D9 = document.getElementById("D9_" + i).value;
                        var D11 = document.getElementById("D11_" + i).value;
                        var D12 = document.getElementById("D12_" + i).value;
                        var D13 = document.getElementById("D13_" + i).value;
                        const txtGetData = $("#txtGetData").val();
                        if (txtGetData === "1")
                        {
                            document.getElementById("D11_" + i).disabled = true;
                            document.getElementById("D12_" + i).disabled = true;
                            document.getElementById("D13_" + i).disabled = true;
                            document.getElementById("D14_" + i).disabled = true;
                            document.getElementById("select-all1").disabled = true;
                            document.getElementById("select-all2").disabled = true;
                            document.getElementById("select-all3").disabled = true;
                            document.getElementById("select-all4").disabled = true;
                            document.getElementById("D9_" + i).disabled = true;
                            document.getElementById("D10_" + i).disabled = true;
                        } else {
                            document.getElementById("D10_" + i).value = "";
                        }
                        if (D9 === "1")
                        {
                            document.getElementById("D9_" + i).checked = true;
                        }
                        if (D11 === "1")
                        {
                            document.getElementById("D11_" + i).checked = true;
                            document.getElementById("D9_" + i).disabled = true;
                        }
                        if (D12 === "1")
                        {
                            document.getElementById("D12_" + i).checked = true;
                            document.getElementById("D9_" + i).disabled = true;
                        }
                        if (D13 === "1")
                        {
                            document.getElementById("D13_" + i).checked = true;
                            document.getElementById("D9_" + i).disabled = true;
                        }
                    } catch (e) {
                    }
                }

            }
        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 98%;height: 400px;">    
            <div style="height: 10px"></div>
            <div id="divTitle">
                DANH SÁCH KHÁCH HÀNG CẬP NHẬT BỔ SUNG HỒ SƠ MỞ TÀI KHOẢN TIỀN GỬI TỔ VIÊN
                <s:if test="txtGetData.equalsIgnoreCase('0')"><a style="color: red">(Chưa hoàn thành)</a></s:if>
                <s:else><a style="color: #009900">(Hoàn thành)</a></s:else>

                    <br> 
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(TW đã khóa nhập dữ liệu)</a></s:if>
                <s:elseif test="chotCic.equalsIgnoreCase('2')" ><a class="color_11">(CN đã gửi dữ liệu)</a></s:elseif>
                <s:elseif test="chotCic.equalsIgnoreCase('1')" ><a class="color_11">(PGD đã chốt dữ liệu)</a></s:elseif>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
                <input type="hidden" value="<s:property value="chotCic"/>" name="chotcic" id="chotcic"/> 
            </div>
            <div style="height: 10px"></div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th style="width: 50px" rowspan="4">STT</th>                           
                    <th style="width: 100px" rowspan="4">Mã khách hàng</th>  
                    <th style="width: 100px" rowspan="4">CCCD/Thẻ căn cước</th>  
                    <th style="width: 100px" rowspan="4">Ngày tháng năm sinh</th>  
                    <th style="width: 100px" rowspan="4">Số điện thoại</th>
                    <th style="width: 150px"rowspan="4">Số tài khoản tiền gửi tổ viên 105</th>
                    <th colspan="5">Kết quả cập nhật hồ sơ</th> 
                    <th  style="width: 200px" rowspan="4">Ghi chú (Nguyên nhân chi tiết chưa hoàn thành cập nhật hồ sơ, thông tin sai lệch,...)</th>  
                </tr>
                <tr>
                    <th class="D0" rowspan="2">Hoàn thành cập nhật hồ sơ</th>   
                    <th style="width: 100px" rowspan="3">Ngày hiệu lực tài khoản tổ viên</th> 
                    <th colspan="3">Chưa hoàn thành cập nhật hồ sơ</th> 
                </tr>         
                <tr>
                    <th class="D0">Khách hàng đi làm ăn xa</th>   
                    <th class="D0">Khách hàng đi khỏi nơi cư trú có thông tin địa chỉ cụ thể và nhận nợ</th> 
                    <th class="D0">Khách hàng đi khỏi nơi cú trú không có thông tin địa chỉ cụ thể/không nhận nợ</th>   

                </tr>
                <tr>
                    <th><input type="checkbox" id ="select-all1"/></th>
                    <th><input type="checkbox" id ="select-all2"/></th>  
                    <th><input type="checkbox" id ="select-all3"/></th>  
                    <th><input type="checkbox" id ="select-all4"/></th>  
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
                            <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>
                            <input type="hidden" value="<s:property  value="D2" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2"/>
                            <input type="hidden" value="<s:property  value="D3" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                            <input type="hidden" value="<s:property  value="D4" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"/>
                            <input type="hidden" value="<s:property  value="D5" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"/>
                            <input type="hidden" value="<s:property  value="D6" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6"/>
                            <input type="hidden" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7"/>
                            <input type="hidden" value="<s:property  value="D8" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"/>
<!--                            <input type="hidden" value="<s:property  value="D9" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/>
                            <input type="hidden" value="<s:property  value="D10" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10"/>
                            <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                            <input type="hidden" value="<s:property  value="D13" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13"/>
                            <input type="hidden" value="<s:property  value="D14" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14"/>-->
                            <input type="hidden" value="<s:property  value="D15" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/>
                            <input type="hidden" value="<s:property  value="D16" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16"/>
                            <input type="hidden" value="<s:property  value="D17" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17"/>
                            <input type="hidden" value="<s:property  value="D18" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18"/>
                            <input type="hidden" value="<s:property  value="D19" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19"/>
                            <input type="hidden" value="<s:property  value="D20" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20"/>
                            <input type="hidden" value="<s:property  value="D30" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30"/>
                        </td>
                        <td class="D0"><s:property  value="D1" /></td>
                        <td class="D0"><s:property  value="D3" /></td>
                        <td class="D0"><s:property  value="D4" /></td>
                        <td class="D0"><s:property  value="D5" /></td>
                        <td class="D0"><s:property  value="D6" /></td>
                        <td class="D0">
                            <input type="checkbox" id ="D9_<s:property value="%{#rowstatus.index}" />" 
                                   onclick="$(this).val(this.checked ? 1 : 0)" class="myCheckBox1"
                                   oninput="onSelectChange_dnht1(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                   name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D9" value="<s:property  value="D9" />"/>      
                        </td>
                        <td>
                            <input style="width: 75px; text-align: center" type="text" readonly="readonly" class="cssDate" 
                                   id="D10_<s:property  value='%{#rowstatus.index}' />"  
                                   name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D10" value="<s:property  value="D10" />"/>                            
                        </td>
                        <td class="D0" >
                            <input type="checkbox" id ="D11_<s:property value="%{#rowstatus.index}" />" 
                                   onclick="$(this).val(this.checked ? 1 : 0)" class="myCheckBox2"
                                   oninput="onSelectChange_dnht2(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                   name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D11" value="<s:property  value="D11" />"/>      
                        </td>
                        <td class="D0">
                            <input type="checkbox" id ="D12_<s:property value="%{#rowstatus.index}" />" 
                                   onclick="$(this).val(this.checked ? 1 : 0)" class="myCheckBox3"
                                   oninput="onSelectChange_dnht3(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                   name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D12" value="<s:property  value="D12" />"/>      
                        </td>
                        <td class="D0">
                            <input type="checkbox" id ="D13_<s:property value="%{#rowstatus.index}" />" 
                                   onclick="$(this).val(this.checked ? 1 : 0)" class="myCheckBox4"
                                   oninput="onSelectChange_dnht4(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                   name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D13" value="<s:property  value="D13" />"/>      
                        </td>
                        <td class="D0">
                            <textarea style="width: 98%" placeholder="Nhập tối đa 500 ký tự" id="D14_<s:property  value='%{#rowstatus.index}' />" 
                                      name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D14" maxlength="500"><s:property value='D14'/></textarea>
                        </td>
                    </s:iterator>

            </table>
        </div>
        <div id="luu_thanhcong"></div>
        <script>
            $(function () {
                setCssStyle();
            });
            function setCssStyle() {
                $(".cssDate").datepicker({
                    dateFormat: 'dd/mm/yy',
                    showOn: "button",
                    buttonImage: "img/icon-ui_datepicker.png",
                    buttonImageOnly: true,
                    showButtonPanel: true,
                    buttonText: "icono",
                    changeMonth: true,
                    changeYear: true,
                    yearRange: "c-30:c+10",
                    beforeShow: function (input, inst) {
                        if ($(input).is(':disabled')) {
                            return false; // Ngăn chặn datepicker hiển thị nếu input bị disabled
                        }
                    }
                });
            }
            let formattedDate1 = "";
            function setTodayForEmptyCssDate() {
                const today = new Date();
                const day = String(today.getDate()).padStart(2, '0');
                const month = String(today.getMonth() + 1).padStart(2, '0'); // Tháng bắt đầu từ 0
                const year = today.getFullYear();
                formattedDate1 = day + '/' + month + '/' + year;
            }
            $(function () {
                $('#select-all1').click(function () {
                    const isChecked = $('#select-all1').prop('checked');

                    // Lặp qua các checkbox và cập nhật trạng thái
                    $('.myCheckBox1').each(function (index) {
                        if (!this.disabled) {
                            this.checked = isChecked;
                            this.value = isChecked ? '1' : '0';
                            onSelectChange_dnht1(this.value, index);
                        }
                    });
                });
            });
            function onSelectChange_dnht1(value, index) {
                if (value === '1')
                {
                    ["D11_", "D12_", "D13_", "D14_"].forEach(id => {
                        document.getElementById(id + index).disabled = true;
                    });
                    document.getElementById("D14_" + index).value = "";
                    setTodayForEmptyCssDate();
                    var d10 = document.getElementById("D10_" + index).value;
                    if (d10 === "") {
                        document.getElementById("D10_" + index).value = formattedDate1;
                    }
                } else
                {
                    ["D11_", "D12_", "D13_", "D14_"].forEach(id => {
                        document.getElementById(id + index).disabled = false;
                    });
                }
            }
            $(function () {
                $('#select-all2').click(function () {
                    const isChecked = $('#select-all2').prop('checked');
                    // Lặp qua các checkbox và cập nhật trạng thái
                    $('.myCheckBox2').each(function (index) {
                        if (!this.disabled) {
                            this.checked = isChecked;
                            this.value = isChecked ? '1' : '0';
                            onSelectChange_dnht2(this.value, index);
                        }
                    });
                });
            });
            function onSelectChange_dnht2(value, index) {
                if (value === '1')
                {
                    ["D9_", "D12_", "D13_"].forEach(id => {
                        document.getElementById(id + index).disabled = true;
                    });
                    document.getElementById("D14_" + index).disabled = false;

                } else
                {
                    ["D9_", "D12_", "D13_"].forEach(id => {
                        document.getElementById(id + index).disabled = false;
                    });
                    document.getElementById("D14_" + index).disabled = true;
                    document.getElementById("D14_" + index).value = "";
                }
            }
            $(function () {
                $('#select-all3').click(function () {
                    const isChecked = $('#select-all3').prop('checked');

                    // Lặp qua các checkbox và cập nhật trạng thái
                    $('.myCheckBox3').each(function (index) {
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
                    ["D9_", "D11_", "D13_"].forEach(id => {
                        document.getElementById(id + index).disabled = true;
                    });
                    document.getElementById("D14_" + index).disabled = false;
                } else
                {
                    ["D9_", "D11_", "D13_"].forEach(id => {
                        document.getElementById(id + index).disabled = false;
                    });
                    document.getElementById("D14_" + index).disabled = true;
                    document.getElementById("D14_" + index).value = "";
                }
            }
            $(function () {
                $('#select-all4').click(function () {
                    const isChecked = $('#select-all4').prop('checked');

                    // Lặp qua các checkbox và cập nhật trạng thái
                    $('.myCheckBox4').each(function (index) {
                        if (!this.disabled) {
                            this.checked = isChecked;
                            this.value = isChecked ? '1' : '0';
                            onSelectChange_dnht4(this.value, index);
                        }
                    });
                });
            });
            function onSelectChange_dnht4(value, index) {
                if (value === '1')
                {
                    ["D9_", "D11_", "D12_"].forEach(id => {
                        document.getElementById(id + index).disabled = true;
                    });
                    document.getElementById("D14_" + index).disabled = false;
                } else
                {
                    ["D9_", "D11_", "D12_"].forEach(id => {
                        document.getElementById(id + index).disabled = false;
                    });
                    document.getElementById("D14_" + index).disabled = true;
                    document.getElementById("D14_" + index).value = "";
                }
            }

        </script>
    </body>
</html>
