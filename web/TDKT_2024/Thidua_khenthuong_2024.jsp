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
            function initTable()
            {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++) {
                    try {
                        var row = table.rows[i];

                        // Apply styles only to the first row (index 0)
                        if (i === 3) {
                            var cells = row.cells;
                            for (var j = 0; j < cells.length; j++) {
                                var cell = cells[j];
                                cell.style.backgroundColor = '#ffcccb';
                                cell.style.fontWeight = 'bold';
                                cell.style.color = '#0000ff';
                            }
                        }
                    } catch (e) {
                    }
                }
            }
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
                $('.number2').number(true, 2);
                $(".STT1").css({"width": "30px"});
                $(".STT2").css({"width": "90px"});
                $(".STT3").css({"width": "120"});
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
            function cancelAssign(sngaybc, stype, chotsl) {
                if (chotsl === '1')
                {
                    alert("Chốt dữ liệu thất bại. Ban Thi đua khen thưởng đã khoá nhập dữ liệu!");
                    return;
                }
                $.ajax({
                    type: "GET",
                    url: "lock_TDKT_2024.action?" + "ssngaybc=" + sngaybc + "&stype=" + stype + "&schotsl=" + chotsl,
                    success: function (res) {
                        var status = parseInt(res.status);
                        //alert(status);
                        if (status === 1) {
                            if (stype === "49") {
                                alert('Khóa dữ liệu thành công!');
                            } else if (stype.length === 2 && stype !== "49") {
                                alert('Chốt dữ liệu thành công!');
                            } else {
                                alert('Mở dữ liệu thành công!');
                            }
                            onLoadData();
                        } else {
                            if (stype === "49") {
                                alert('Khóa dữ liệu lỗi: ' + res.message);
                            } else if (stype.length === 2 && stype !== "49") {
                                alert('Chốt dữ liệu lỗi: ' + res.message);
                            } else {
                                alert('Mở khoá dữ liệu lỗi: ' + res.message);
                            }
                        }
                    },
                    error: function (res) {
                        if (stype === "49") {
                            alert("Khóa dữ liệu lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                        } else {
                            alert("Lỗi vui lòng liên hệ quản trị viên để được hỗ trợ!");
                        }
                    }
                });
            }
        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;height: 400px;"> 
            <div id="divTitle">
                DANH SÁCH CÁC PHÒNG BAN LIÊN QUAN ĐÃ CHỐT DỮ LIỆU
            </div>       
            <table border="1" class="editDelete" align="center" style="width: 50%">               
                <tr> 
                    <th class="STT1" rowspan="4">Trạng thái</th>                           
                    <th colspan="7">Phòng ban</th>  
                </tr> 
                <tr> 
                    <th>TDNN</th>                           
                    <th>QL&XLNRR</th>      
                    <th>KHNV</th>    
                    <th>KT&QLTC</th>      
                    <th>TCCB</th>      
                    <th>HTQT</th>      
                    <th>TĐKT</th>    

                </tr> 
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>
                </tr>
                <s:iterator value="#attr.lstData" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0">
                            <s:if test="check_Username.equalsIgnoreCase('USRGRP21')">
                                <s:if test="check_D1.equalsIgnoreCase('1')"> 
                                    <h4 style="color: #3dc21b; margin: auto" >Đã chốt</h4>
                                </s:if>
                                <s:else>
                                    <a style="text-decoration: underline; color: red" href="#" 
                                       onclick="cancelAssign('<s:property value="sngaybc"/>', '21', '<s:property value="chotsl"/>')"> Chốt dữ liệu
                                    </a>
                                </s:else>
                            </s:if>
                            <s:elseif test="check_Username.equalsIgnoreCase('USRGRP49')">
                                <s:if test="check_D1.equalsIgnoreCase('1')">
                                    <a style="text-decoration: underline; color: #0000FF" href="#" 
                                       onclick="cancelAssign('<s:property value="sngaybc"/>', '211', '<s:property value="chotsl"/>')"> Mở dữ liệu
                                    </a>
                                </s:if>
                                <s:else> Chưa chốt </s:else>
                            </s:elseif>
                            <s:else>
                                <s:if test="check_D1.equalsIgnoreCase('1')"> <h4 style="color: #3dc21b; margin: auto" >Đã chốt</h4></s:if>
                                <s:else>Chưa chốt</s:else>
                            </s:else>
                        </td>
                        <td class="D0">
                            <s:if test="check_Username.equalsIgnoreCase('USRGRP23')">
                                <s:if test="check_D2.equalsIgnoreCase('1')"> 
                                    <h4 style="color: #3dc21b; margin: auto" >Đã chốt</h4>
                                </s:if>
                                <s:else>
                                    <a style="text-decoration: underline; color: red" href="#" 
                                       onclick="cancelAssign('<s:property value="sngaybc"/>', '23', '<s:property value="chotsl"/>')"> Chốt dữ liệu
                                    </a>
                                </s:else>
                            </s:if>
                            <s:elseif test="check_Username.equalsIgnoreCase('USRGRP49')">
                                <s:if test="check_D2.equalsIgnoreCase('1')">
                                    <a style="text-decoration: underline; color: #0000FF" href="#" 
                                       onclick="cancelAssign('<s:property value="sngaybc"/>', '233', '<s:property value="chotsl"/>')"> Mở dữ liệu
                                    </a>
                                </s:if>
                                <s:else> Chưa chốt </s:else>
                            </s:elseif>
                            <s:else>
                                <s:if test="check_D2.equalsIgnoreCase('1')"><h4 style="color: #3dc21b; margin: auto" >Đã chốt</h4></s:if>
                                <s:else>Chưa chốt</s:else>
                            </s:else>
                        </td>

                        <td class="D0">
                            <s:if test="check_Username.equalsIgnoreCase('USRGRP24')">
                                <s:if test="check_D3.equalsIgnoreCase('1')">
                                    <h4 style="color: #3dc21b; margin: auto" >Đã chốt</h4>
                                </s:if>
                                <s:else>
                                    <a style="text-decoration: underline; color: red" href="#" 
                                       onclick="cancelAssign('<s:property value="sngaybc"/>', '24', '<s:property value="chotsl"/>')"> Chốt dữ liệu
                                    </a>
                                </s:else>
                            </s:if>
                            <s:elseif test="check_Username.equalsIgnoreCase('USRGRP49')">
                                <s:if test="check_D3.equalsIgnoreCase('1')">
                                    <a style="text-decoration: underline; color: #0000FF" href="#" 
                                       onclick="cancelAssign('<s:property value="sngaybc"/>', '244', '<s:property value="chotsl"/>')"> Mở dữ liệu
                                    </a>
                                </s:if>
                                <s:else> Chưa chốt </s:else>
                            </s:elseif>
                            <s:else>
                                <s:if test="check_D3.equalsIgnoreCase('1')"><h4 style="color: #3dc21b; margin: auto" >Đã chốt</h4> </s:if>
                                <s:else>Chưa chốt</s:else>
                            </s:else>
                        </td>

                        <td class="D0">
                            <s:if test="check_Username.equalsIgnoreCase('USRGRP19')">
                                <s:if test="check_D4.equalsIgnoreCase('1')">
                                    <h4 style="color: #3dc21b; margin: auto" >Đã chốt</h4>
                                </s:if>
                                <s:else>
                                    <a style="text-decoration: underline; color: red" href="#" 
                                       onclick="cancelAssign('<s:property value="sngaybc"/>', '19', '<s:property value="chotsl"/>')"> Chốt dữ liệu
                                    </a>
                                </s:else>
                            </s:if>
                            <s:elseif test="check_Username.equalsIgnoreCase('USRGRP49')">
                                <s:if test="check_D4.equalsIgnoreCase('1')">
                                    <a style="text-decoration: underline; color: #0000FF" href="#" 
                                       onclick="cancelAssign('<s:property value="sngaybc"/>', '199', '<s:property value="chotsl"/>')"> Mở dữ liệu
                                    </a>
                                </s:if>
                                <s:else> Chưa chốt </s:else>
                            </s:elseif>
                            <s:else>
                                <s:if test="check_D4.equalsIgnoreCase('1')"> <h4 style="color: #3dc21b; margin: auto" >Đã chốt</h4></s:if>
                                <s:else>Chưa chốt</s:else>
                            </s:else>
                        </td>

                        <td class="D0">
                            <s:if test="check_Username.equalsIgnoreCase('USRGRP15')">
                                <s:if test="check_D5.equalsIgnoreCase('1')">
                                    <h4 style="color: #3dc21b; margin: auto" >Đã chốt</h4>
                                </s:if>
                                <s:else>
                                    <a style="text-decoration: underline; color: red" href="#" 
                                       onclick="cancelAssign('<s:property value="sngaybc"/>', '15', '<s:property value="chotsl"/>')"> Chốt dữ liệu
                                    </a>
                                </s:else>
                            </s:if>
                            <s:elseif test="check_Username.equalsIgnoreCase('USRGRP49')">
                                <s:if test="check_D5.equalsIgnoreCase('1')">
                                    <a style="text-decoration: underline; color: #0000FF" href="#" 
                                       onclick="cancelAssign('<s:property value="sngaybc"/>', '155', '<s:property value="chotsl"/>')"> Mở dữ liệu
                                    </a>
                                </s:if>
                                <s:else> Chưa chốt </s:else>
                            </s:elseif>
                            <s:else>
                                <s:if test="check_D5.equalsIgnoreCase('1')"> <h4 style="color: #3dc21b; margin: auto" >Đã chốt</h4></s:if>
                                <s:else>Chưa chốt</s:else>
                            </s:else>
                        </td>

                        <td class="D0">
                            <s:if test="check_Username.equalsIgnoreCase('USRGRP18')">
                                <s:if test="check_D6.equalsIgnoreCase('1')">
                                    <h4 style="color: #3dc21b; margin: auto" >Đã chốt</h4>
                                </s:if>
                                <s:else>
                                    <a style="text-decoration: underline; color: red" href="#" 
                                       onclick="cancelAssign('<s:property value="sngaybc"/>', '18', '<s:property value="chotsl"/>')"> Chốt dữ liệu
                                    </a>
                                </s:else>
                            </s:if>
                            <s:elseif test="check_Username.equalsIgnoreCase('USRGRP49')">
                                <s:if test="check_D6.equalsIgnoreCase('1')">
                                    <a style="text-decoration: underline; color: #0000FF" href="#" 
                                       onclick="cancelAssign('<s:property value="sngaybc"/>', '188', '<s:property value="chotsl"/>')"> Mở dữ liệu
                                    </a>
                                </s:if>
                                <s:else> Chưa chốt </s:else>
                            </s:elseif>
                            <s:else>
                                <s:if test="check_D6.equalsIgnoreCase('1')"> <h4 style="color: #3dc21b; margin: auto" >Đã chốt</h4></s:if>
                                <s:else>Chưa chốt</s:else>
                            </s:else>
                        </td>

                        <td class="D0"><s:if test="check_Username.equalsIgnoreCase('USRGRP49')">
                                <a style="color: red" href="#" onclick="cancelAssign('<s:property value="sngaybc"/>', '49', '<s:property value="chotsl"/>')">
                                    <s:if test="chotsl.equalsIgnoreCase('0')"><u>Khóa nhập dữ liệu</u></s:if>
                                    <s:else><u>Mở nhập dữ liệu</u></s:else></a>
                                    </s:if>
                                    <s:else>
                                        <s:if test="chotsl.equalsIgnoreCase('1')"> <h4 style="color: #3dc21b; margin: auto" >Đã khóa</h4></s:if>
                                <s:else>Chưa khóa</s:else>
                                    </s:else></td>
                        </tr>
                </s:iterator>
            </table>
            <br>
            <div id="divTitle">
                BIỂU TỔNG HỢP MỘT SỐ CHỈ TIÊU ĐÁNH GIÁ HOẠT ĐỘNG CỦA CÁC CHI NHÁNH NHCSXH<br>
                <input type="hidden" value="<s:property value="sngaybc"/>" name="sngaybc" id="sngaybc"/> 
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
            </div>
            <div id="divDonvitinh">
                Đơn vị tính: Triệu đồng, %, điểm
            </div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th rowspan="2" class="STT1">STT</th>                           
                    <th rowspan="2"class="STT3">Đơn vị</th>  
                    <th rowspan="2"class="STT2">Tổng dư nợ</th>
                    <th rowspan="2">Tỷ lệ hoàn thành tăng trưởng dư nợ TW+ĐP</th>
                    <th colspan="3">Kết quả huy động nguồn vốn nhận UTĐP</th>
                    <th rowspan="2">Tỷ lệ HTKH NV huy động tiền gửi của Tổ chức, cá nhân và huy động tiết kiệm thông qua tổ TK&VV</th>
                    <th rowspan="2">Tỷ lệ NQH</th>
                    <th rowspan="2">Tỷ lệ thu nợ đến hạn</th>
                    <th rowspan="2">Tỷ lệ thu lãi</th>
                    <th rowspan="2">Chất lượng hoạt động GDX</th>
                    <th rowspan="2">Chất lượng hoạt động Tổ TKVV</th>
                    <th rowspan="2">Kết quả đánh giá mức độ hoàn thành nhiệm vụ theo CV 9759</th>
                    <th rowspan="2">Số chỉ tiêu đạt được</th>
                    <th rowspan="2" style="width: 100px">Dự kiến Khen thưởng</th>
                    <th rowspan="2">Số tiền KT Quý 1</th>
                    <th rowspan="2">Số tiền KT 6 tháng đầu năm</th>
                    <th rowspan="2">Công tác truyền thông</th>
                    <th rowspan="2">Số tiền KT 9 tháng</th>
                    <th rowspan="2">Số tiền KT những ngày đầu năm</th>
                </tr>   
                <tr>
                    <th>Tỷ lệ HTKH so với KH giao</th>
                    <th>Số tăng trưởng tuyệt đối so với 31/12 của năm trước</th>
                    <th>Kế hoạch</th>
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
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(20)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(21)</th>
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
                            <input type="hidden" value="<s:property  value="MAPGD" />" id="macn_<s:property  value="%{#rowstatus.index}" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                            <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>
                            <input type="hidden" value="<s:property  value="D18" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18"/>
                            <input type="hidden" value="<s:property  value="D19" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19"/>
                            <input type="hidden" value="<s:property  value="D20" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20"/>
                            <input type="hidden" value="<s:property  value="D21" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21"/>
                            <input type="hidden" value="<s:property  value="D22" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22"/>
                            <input type="hidden" value="<s:property  value="D23" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23"/>
                            <input type="hidden" value="<s:property  value="D24" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24"/>
                            <input type="hidden" value="<s:property  value="D25" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25"/>
                            <input type="hidden" value="<s:property  value="D26" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D26"/>
                            <input type="hidden" value="<s:property  value="D27" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27"/>
                            <input type="hidden" value="<s:property  value="D28" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28"/>
                            <input type="hidden" value="<s:property  value="D29" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D29"/>
                            <input type="hidden" value="<s:property  value="D30" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30"/>
                            <input type="hidden" value="<s:property  value="D31" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D31"/>
                            <input type="hidden" value="<s:property  value="D32" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D32"/>
                            <input type="hidden" value="<s:property  value="D33" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D33"/>
                            <input type="hidden" value="<s:property  value="D34" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D34"/>
                            <input type="hidden" value="<s:property  value="D35" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D35"/>
                            <input type="hidden" value="<s:property  value="D36" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D36"/>
                            <input type="hidden" value="<s:property  value="D37" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D37"/>
                            <input type="hidden" value="<s:property  value="D38" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D38"/>
                            <input type="hidden" value="<s:property  value="D39" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D39"/>
                            <input type="hidden" value="<s:property  value="D40" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D40"/>
                            <input type="hidden" value="<s:property  value="D41" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D41"/>
                            <input type="hidden" value="<s:property  value="D42" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D42"/>
                            <input type="hidden" value="<s:property  value="D43" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D43"/>
                            <input type="hidden" value="<s:property  value="D44" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D44"/>
                            <input type="hidden" value="<s:property  value="D45" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D45"/>
                            <input type="hidden" value="<s:property  value="D46" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D46"/>
                            <input type="hidden" value="<s:property  value="D47" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D47"/>
                            <input type="hidden" value="<s:property  value="D48" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D48"/>
                            <input type="hidden" value="<s:property  value="D49" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D49"/>
                            <input type="hidden" value="<s:property  value="D50" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D50"/>
                        </td>
                        <td><s:property  value="TEN"/></td>
                        <td><input type="text" value="<s:property  value="D1" />"
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP23')|| check_D2.equalsIgnoreCase('1')">readonly="true"</s:if>
                                   <s:if test="!D35.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="number"/></td>
                        <td><input type="text" value="<s:property  value="D2" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP24') || check_D3.equalsIgnoreCase('1')">readonly="true"</s:if>
                                   <s:if test="!D36.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="number2"/></td>
                        <td><input type="text" value="<s:property  value="D3" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP24') || check_D3.equalsIgnoreCase('1')">readonly="true"</s:if>
                                   <s:if test="!D37.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="number2"/></td>
                        <!--bo sung 2025-->
                        <td><input type="text" value="<s:property  value="D19" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP24') || check_D3.equalsIgnoreCase('1')">readonly="true"</s:if>
                                   
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="number2"/></td> 
                        <td><input type="text" value="<s:property  value="D20" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP24') || check_D3.equalsIgnoreCase('1')">readonly="true"</s:if>
                                  
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="number2"/></td> 
                        <td><input type="text" value="<s:property  value="D4" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP24') || check_D3.equalsIgnoreCase('1')">readonly="true"</s:if>
                                   <s:if test="!D38.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="number2"/></td>
                        
                        <td><input type="text" value="<s:property  value="D5" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP23') || check_D2.equalsIgnoreCase('1')">readonly="true"</s:if>
                                   <s:if test="!D39.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number2"/></td>
                        <td><input type="text" value="<s:property  value="D6" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP23')|| check_D2.equalsIgnoreCase('1')">readonly="true"</s:if>
                                   <s:if test="!D40.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number2"/></td>
                        <td><input type="text" value="<s:property  value="D7" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP23') || check_D2.equalsIgnoreCase('1')">readonly="true"</s:if>
                                   <s:if test="!D41.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="number2"/></td>
                        <td><input type="text" value="<s:property  value="D8" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP21') || check_D1.equalsIgnoreCase('1')">readonly="true"</s:if>
                                   <s:if test="!D42.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="number2"/></td>
                        <td><input type="text" value="<s:property  value="D9" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP21') || check_D1.equalsIgnoreCase('1')">readonly="true"</s:if>
                                   <s:if test="!D43.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="number2"/></td>
                        <td><input type="text" value="<s:property  value="D12" />" 
                                   <s:if test="!D46.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" placeholder="Ví dụ 1A2B3C"/></td>
                        <td><input type="text" value="<s:property  value="D11" />" 
                                   <s:if test="!D45.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="number"/></td>
                        
                        <td><input type="text" value="<s:property  value="D10" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP15') || check_D5.equalsIgnoreCase('1')">readonly="true"</s:if>
                                   <s:if test="!D44.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number"/></td>
                        
                        <td><input type="text" value="<s:property  value="D13" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP49')">readonly="true"</s:if>
                                   <s:if test="!D47.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="number"/></td>
                        <td><input type="text" value="<s:property  value="D14" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP49')">readonly="true"</s:if>
                                   <s:if test="!D48.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="number"/></td>
                        <td><input type="text" value="<s:property  value="D15" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP18') || check_D6.equalsIgnoreCase('1')">readonly="true"</s:if>
                                   <s:if test="!D49.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="number"/></td>
                        <td><input type="text" value="<s:property  value="D16" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP49')">readonly="true"</s:if>
                                   <s:if test="!D50.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="number"/></td>
                        <td><input type="text" value="<s:property  value="D17" />" 
                                   <s:if test="!check_Username.equalsIgnoreCase('USRGRP49')">readonly="true"</s:if>
                                   <s:if test="!D18.equalsIgnoreCase('1')">style="background: blanchedalmond"</s:if>
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="number"/></td>
                    </tr>
                </s:iterator>
            </table>
        </div>
        <div id="luu_thanhcong"></div>
    </body>
</html>
