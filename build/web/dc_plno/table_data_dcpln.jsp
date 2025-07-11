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
        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 98%;height: 400px;">    
            <div style="height: 10px"></div>
            <div id="divTitle">
                SỐ LIỆU TỔNG HỢP 
            </div>
            <!--<div style="height: 10px"></div>-->
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" align="center">
                <tr>
                    <th rowspan="2">Tổng số KH</th>
                    <th rowspan="2">Tổng số món vay</th>
                    <th rowspan="2">Tổng dư nợ</th>
                    <th colspan="3">Dư nợ</th>
                    <th rowspan="2">Nợ lãi</th>
                </tr>
                <tr>                   
                    <th>Nợ trong hạn</th>
                    <th>Nợ quá hạn</th>
                    <th>Nợ khoanh</th>
                </tr>
            </table>
            <br>
            <div id="divTitle">
                SỐ LIỆU ĐỐI CHIẾU, PHÂN LOẠI NỢ
            </div>
            <br>
            <table border="1" class="editDelete" align="center">
                <tr>
                    <th rowspan="3"><input type="checkbox" id ="select-all"/></th>
                    <th rowspan="3">Tên khách hàng</th>
                    <th rowspan="3">Mã món vay</th>
                    <th rowspan="3">Chương trình</th>
                    <th colspan="5">Số liệu tại NHCSXH</th> 
                    <th colspan="4">Phân loại khả năng trả nợ</th> 
                    <th rowspan="3">Nguyên nhân nợ khoanh (Không có khả năng trả nợ)</th>
                </tr>
                <tr>
                    <th colspan="4">Nợ gốc</th> 
                    <th rowspan="2">Nợ lãi</th> 
                    <th rowspan="2">Có khả năng trả nợ</th> 
                    <th colspan="3">Không có khả năng trả nợ</th>
                </tr>
                <tr>
                    <th>Tổng số</th>
                    <th>Nợ trong hạn</th>
                    <th>Nợ quá hạn</th>
                    <th>Nợ khoanh</th>
                    <th>Số tiền</th>
                    <th>Nguyên nhân cấp 1</th>
                    <th>Nguyên nhân cấp 2</th>
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
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>
                        <td class="D0"> <input type="checkbox" class="myCheckBox"
                                               name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D9"
                                               value="0" onclick="$(this).val(this.checked ? 1 : 0)"/>      
                        </td>
                        <td><s:property value="D3"/>
                        </td>
                        <td> 
                            <a href="javascript:hienthichitiet('<s:property value="D2"/>','<s:property  value="%{#rowstatus.index}" />' )" class="SOKU linkKh">
                                <s:property value='D2'/>
                            </a>
                        </td>
                        <td align = "left" class="TD_CHTRINH" style="font-size: 10px!important"> 
                            <input type="text" value="<s:property  value='TEN' />" 
                                   name="TEN" class="TD_CHTRINH" onfocus="this.select()" readonly="true"/>
                        </td>
                        <td align = "right" class="TD_DU_NO" style="font-size: 10px!important">
                            <input type="text" value="<s:property value='D1'/>" name="sTongDN" class="DU_NO number2"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true" id="id_TongDN_<s:property  value="%{#rowstatus.index}" />"/>
                        </td>
                        <td align = "right" class="TD_DU_NO" style="font-size: 10px!important">
                            <input type="text" value="<s:property value='D11'/>" name="sDnothan" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true" id="id_Dnohan_<s:property  value="%{#rowstatus.index}" />"/>
                        </td>

                        <td align = "right" class="TD_DU_NO" style="font-size: 10px!important">
                            <input type="text" value="<s:property value='D12'/>" name="sDnoqhan" class="DU_NO number2"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true" id="id_Dnoqhan_<s:property  value="%{#rowstatus.index}" />"/>
                        </td>
                        <td align = "right" class="TD_DU_NO" style="font-size: 10px!important">
                            <input type="text" value="<s:property value='D13'/>" name="sDnokhoanh" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true" id="id_Dnokhoanh_<s:property  value="%{#rowstatus.index}" />"/>
                        </td>
                        <td align = "right" class="TD_LAITON" style="font-size: 10px!important">
                            <input type="text" value="<s:property value='D19'/>" name="sTonglaiton" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true" id="id_Tonglaiton_<s:property  value="%{#rowstatus.index}" />"/>
                        </td>

                        <!--chi tieu nhap tay tu day--> 
                        <td align = "right" class="TD_DU_NO" style="font-size: 10px!important">
                            <input type="text" value="<s:property value='sC_Kntn_Sodu'/>" name="lstSavePln[<s:property  value="%{#rowstatus.index}" />].bC_Kntn_Sodu" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           isNumber(this.value);
                                           on_valib(<s:property  value="%{#rowstatus.index}" />, this)" onfocus="this.select()" style="background-color: #FFCCBA"
                                   id="duco_kntn_<s:property  value="%{#rowstatus.index}" />" onclick="ChangeValue_CKNTN(<s:property  value="%{#rowstatus.index}" />, this);
                                           on_valib(<s:property  value="%{#rowstatus.index}" />, this)"/>
                        </td>

                        <td align = "right" class="TD_DU_NO" style="font-size: 10px!important">
                            <input type="text" value="<s:property value='sK_Kntn_Sodu'/>" name="lstSavePln[<s:property  value="%{#rowstatus.index}" />].bK_Kntn_Sodu" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           isNumber(this.value);
                                           on_valib(<s:property  value="%{#rowstatus.index}" />, this)" onfocus="this.select()" style="background-color: #FFCCBA"
                                   id="dukhong_kntn_<s:property  value="%{#rowstatus.index}" />" onclick="ChangeValue_KCKNTN(<s:property  value="%{#rowstatus.index}" />, this); on_valib(<s:property  value="%{#rowstatus.index}" />, this)"/>
                        </td>

                        <td align = "left" class="TD_NGUYEN_NHAN_KHOANH" style="font-size: 10px!important">
                            <input type="text" value="<s:property value='sK_Ngnhan_Kh'/>" name="lstSavePln[<s:property  value="%{#rowstatus.index}" />].sK_Ngnhan_Kh" class="DU_NO" 
                                   onfocus="this.select()" style="background-color: #FFCCBA" 
                                   id="ngnhan_kh_<s:property  value="%{#rowstatus.index}" />"/>
                        </td>
                    </tr>

                </s:iterator>

            </table>
        </div>
        <div id="luu_thanhcong"></div>
        <script>
            $(function () {
                $('#select-all').click(function (event) {
                    if (this.checked) {
                        // Iterate each checkbox
                        $('.myCheckBox').each(function () {
                            this.checked = true;
                            this.value = '1';
                        });
                    } else {
                        $('.myCheckBox').each(function () {
                            this.checked = false;
                            this.value = '0';
                        });
                    }
                });
            });
            function hienthichitiet(soku, stt) {
                var ht1 = screen.availHeight - 260;
                var wt1 = 1024;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 100;
                var ngay_dcpln = $("#ngay_dcpln").val();
                var totruong_dcpln = $("#totruong_dcpln").val();
                var dvut_dcpln = $("#dvut_dcpln").val();
                var url = "getDetialLoanDcPLN.action?soku_dcpln=" + soku + "&ngay_dcpln=" + ngay_dcpln +
                        "&dvut_dcpln=" + dvut_dcpln + "&totruong_dcpln=" + totruong_dcpln;
                popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }
        </script>
    </body>
</html>
