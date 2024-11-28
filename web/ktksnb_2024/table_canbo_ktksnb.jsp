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
        width: 90%;
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
                $(".STT1").css({"width": "50px"});
                $(".STT2").css({"width": "100px"});
                $(".STT3").css({"width": "150"});
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
                        var D10 = document.getElementById("D14_" + i).value;
                        if (D10 === "1")
                        {
                            document.getElementById("D14_" + i).checked = true;
                        } else
                        {
                            if (D10 === "2")
                            {
                                document.getElementById("D14_" + i).disabled = true;
                                document.getElementById("D14_" + i).checked = false;
                            }
                            document.getElementById("D14_" + i).checked = false;
                        }
                    } catch (e) {
                    }
                }

            }
        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 80%;height: 400px;">             
            <div id="divTitle" style="text-align: center">
                DANH SÁCH CÁN BỘ<br>
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Chi nhánh đã chốt số liệu)</a></s:if>
                <s:elseif test="chotsl.equalsIgnoreCase('1')" ><a class="color_11">(Phòng giao dịch đã gửi dữ liệu)</a></s:elseif>
                    <div style="height:10px"></div>

                    <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
                <input type="hidden" value="<s:property value="chotsl_tw"/>" name="chotsl_tw" id="chotsl_tw"/> 
            </div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th class="STT1" >STT</th>                           
                    <th class="STT2" >ID Cán bộ</th>  
                    <th class="STT2" >Mã cán bộ</th>  
                    <th class="STT2" >Họ và tên</th>
                    <th class="STT2" >Ngày sinh</th>  
                    <th class="STT2" >Giới tính</th>
                    <th class="STT2" >Số CMT</th>  
                    <th class="STT2" >Số điện thoại</th>
                    <th class="STT2" >Chức vụ</th>
                    <th class="STT2" >Hiệu lực đến ngày</th>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                        <th class="STT2" >Cờ cán bộ chuyên trách</th>
                        </s:if>
                        <s:elseif test="Grade.equalsIgnoreCase('3')">
                        <th class="STT2" >Đơn vị</th>
                        </s:elseif>
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
                        <s:if test="Grade.equalsIgnoreCase('1')">
                        <th><input type="checkbox" id ="select-all1"/></th>
                        </s:if>
                        <s:elseif test="Grade.equalsIgnoreCase('3')">
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(11)</th>
                        </s:elseif>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                    <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                    <input type="hidden" value="<s:property value="TT_HIENTHI" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                    <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
                    <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                    <input type="hidden" value="<s:property  value="CO_TONGHOP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/>
                    <input type="hidden" value="<s:property  value="NGUOI_NHAP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_NHAP"/>
                    <input type="hidden" value="<s:property  value="NAMBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NAMBC"/>
                    <input type="hidden" value="<s:property  value="MAPGD" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                    <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>
                    <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>
                    <input type="hidden" value="<s:property  value="D3" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                    <input type="hidden" value="<s:property  value="D2" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2"/>
                    <input type="hidden" value="<s:property  value="D4" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"/>
                    <input type="hidden" value="<s:property  value="D5" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"/>
                    <input type="hidden" value="<s:property  value="D6" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6"/>
                    <input type="hidden" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7"/>
                    <input type="hidden" value="<s:property  value="D8" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"/>
                    <input type="hidden" value="<s:property  value="D9" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/>
                    <input type="hidden" value="<s:property  value="D10" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10"/>
                    <input type="hidden" value="<s:property  value="D11" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11"/>
                    <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                    <input type="hidden" value="<s:property  value="D15" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/>
                    <input type="hidden" value="<s:property  value="KIEUIN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KIEUIN"/>
                    <input type="hidden" value="<s:property  value="NHAPTAY" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/>

                    <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                    <td><s:property  value="D1" /></td>
                    <td class="D0"><s:property  value="D2" /></td>
                    <td><s:property  value="D3" /></td>
                    <td class="D0"><s:property  value="D4" /></td>
                    <td class="D0"><s:property  value="D5" /></td> 
                    <td class="D0"><s:property  value="D9" /></td>
                    <td class="D0"><s:property  value="D12" /></td>
                    <td><s:property  value="D15" /></td>
                    <s:if test="Grade.equalsIgnoreCase('1')">
                        <td class="D0">
                            <input style="width: 75px; text-align: center" type="text" readonly="readonly" class="cssDate" 
                                   onchange="onSelectChange_dnht2(this.value, <s:property value='%{#rowstatus.index}'/>)"
                                   id="D16_<s:property value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D16" 
                                   value="<s:property value='D16'/>">   
                        </td>

                        <td class="D0">
                            <input type="checkbox" 
                                   onclick="$(this).val(this.checked ? 1 : 0)" 
                                   class="myCheckBox1"
                                   id="D14_<s:property value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D14" 
                                   value="<s:property value='D14' />"/>      
                        </td>
                    </s:if>
                    <s:else>
                        <td class="D0"><s:property value='D16' />
                            <input type="hidden" 
                                   value="<s:property value='D14' />" 
                                   name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D14"/> 
                        </td>
                    </s:else>
                    <s:if test="Grade.equalsIgnoreCase('3')">
                        <td class="D0"><s:property value='D17' />
                        </td>
                    </s:if>
                        </tr>
                </s:iterator>
            </table><div style="height:20px"></div> 
        </div>
        <div id="luu_thanhcong"></div>
        <script>
            $(function () {
                $('#select-all1').click(function () {
                    const isChecked = $('#select-all1').prop('checked');

                    // Lặp qua các checkbox và cập nhật trạng thái
                    $('.myCheckBox1').each(function (index) {
                        if (!this.disabled) {
                            this.checked = isChecked;
                            this.value = isChecked ? '1' : '0';
                        }
                    });
                });
            });
            $(function () {
                setCssStyle();
            });

            function setCssStyle() {
                $(".cssDate").datepicker(
                        {
                            dateFormat: 'dd/mm/yy',
                            showOn: "button",
                            buttonImage: "img/icon-ui_datepicker.png",
                            buttonImageOnly: true,
                            // dateFormat: 'dd/mm/yy',
                            showButtonPanel: true,
                            buttonText: "icono",
                            changeMonth: true,
                            changeYear: true,
                            yearRange: "c-10:c+10"
                        });
            }

            function onSelectChange_dnht2(value, index) {
                var currentDate = new Date();
                var day = currentDate.getDate();
                var month = currentDate.getMonth() + 1;
                var year = currentDate.getFullYear();

                if (day < 10) {
                    day = '0' + day;
                }
                if (month < 10) {
                    month = '0' + month;
                }

                var ngaybcParts = value.split('/');
                if (ngaybcParts.length === 3) {
                    var ngaybcDay = parseInt(ngaybcParts[0], 10);
                    var ngaybcMonth = parseInt(ngaybcParts[1], 10);
                    var ngaybcYear = parseInt(ngaybcParts[2], 10);

                    var checkbox = document.getElementById("D14_" + index);
                    if (ngaybcYear < year || (ngaybcYear === year && ngaybcMonth < month && ngaybcDay < day)) {
                        if (checkbox) {
                            checkbox.disabled = true;
                            checkbox.checked = false;
                        }
                    } else if (ngaybcYear > year || (ngaybcYear === year && ngaybcMonth >= month && ngaybcDay >= day)) {
                        if (checkbox) {
                            checkbox.disabled = false;
                        }
                    }
                }
            }


        </script>
    </body>
</html>
