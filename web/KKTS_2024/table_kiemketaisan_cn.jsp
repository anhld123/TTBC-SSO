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
            function addRow(indx) {
                var index = parseInt(indx);
                var table = document.getElementById("subTable");
                var rowCount = table.rows.length - 5;
                if (max_row < rowCount) {
                    max_row = rowCount + 1;
                } else {
                    max_row++;
                    rowCount = max_row;
                }
                var idPGD = "lstPGD_" + max_row;
                var idTenTb = "lstDm111_" + max_row;
                var idNsd = "lstDm112_" + max_row;
                var idNhomTb = "lstDm113_" + max_row;
                var idDvt = "lstDm114_" + max_row;
                var idThuctrang = "lstDm116_" + max_row;
                var newTr = '<tr>' +
                        '<td ><input type="text" value="' + (max_row + 1) + '" id="TT_HIENTHI" name="lstDulieuNt[' + max_row + '].TT_HIENTHI" class="D0 number" onfocus="this.select();" /></td>' +
                        '<td><select style="width: 150px;border: hidden" name="lstDulieuNt[' + max_row + '].D1" id="' + idPGD + '"></select></td>' +
                        '<td><select style="width: 150px;border: hidden" name="lstDulieuNt[' + max_row + '].D2" id="' + idTenTb + '"></select></td>' +
                        '<td><select style="width: 150px;border: hidden" name="lstDulieuNt[' + max_row + '].D3" id="' + idNhomTb + '"></select></td>' +
                        '<td><select style="width: 150px;border: hidden" name="lstDulieuNt[' + max_row + '].D4" id="' + idNsd + '"></select></td>' +
                        '<td><select style="width: 80px;border: hidden" name="lstDulieuNt[' + max_row + '].D5" id="' + idDvt + '"></select></td>' +
                        '<td><input type="text" value="0" id="D6' + max_row + '" name="lstDulieuNt[' + max_row + '].D6" class="number" onfocus="this.select();" onchange="calc(this);"/></td>' +
                        '<td><input type="text" value="0" id="D7' + max_row + '" name="lstDulieuNt[' + max_row + '].D7" class="number" onfocus="this.select();" onchange="calc(this);"/></td>' +
                        '<td><input readonly type="text" value="0" id="D8' + max_row + '" name="lstDulieuNt[' + max_row + '].D8" class="number" onfocus="this.select();"/></td>' +
                        '<td class="D0"><textarea type="text" value="" id="D9' + max_row + '" name="lstDulieuNt[' + max_row + '].D9" placeholder="Nhập tối đa 500 ký tự" maxlength="500" onfocus="this.select();" style="width: 98%"></textarea></td>' +
                        '<td class="D0"><textarea type="text" value="" id="D10' + max_row + '" name="lstDulieuNt[' + max_row + '].D10" placeholder="Nhập tối đa 500 ký tự" maxlength="500" onfocus="this.select();" style="width: 98%"></textarea></td>' +
                        '<td class="D0"><select style="width: 150px;border: hidden" name="lstDulieuNt[' + max_row + '].D5" id="' + idThuctrang + '"></select></td>' +
                        '<td class="D0"><input type="button" style="color: red" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)"/></td>' +
                        '</tr>';

                $(newTr).insertBefore($('table#subTable tr').eq(index));


                // Thiết lập lại các kiểu CSS
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');

                // Cài đặt định dạng số
                $('.number').number(true, 0);
                $('.number2').number(true, 0);

                $(".STT1").css({"width": "50px"});
                $(".STT2").css({"width": "80px"});
                $(".STT3").css({"width": "150px"});
                $(".STT4").css({"width": "200px"});
                $(".STT5").css({"width": "70px"});
                $(".STT6").css({"width": "65px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "220px"});
                $(".TEN_KH").css({"width": "100%"});

                // Fetch data and populate the new select elements
                $.getJSON('loadDmKhac111', {
                    Message: 'fileTemplate',
                    khoa_nhaptaycn: 'KKTS_01'
                }, function (jsonResponse) {
                    try {
                        var dm_khac = '<option value="000000">---Tên thiết bị---</option>';
                        $.each(jsonResponse.lstDmKhac111, function () {
                            dm_khac += '<option value="' + this.description + '">' + this.description + ' - ' + this.value + '</option>';
                        });
                        $('#' + idTenTb).html(dm_khac);

                        if (jsonResponse.msgError !== null) {
                            $('#message_suc_err').text(jsonResponse.msgError);
                        }
                    } catch (e) {
                        alert(e.toString());
                    }
                });
                $.getJSON('loadDmKhac112', {
                    Message: 'fileTemplate',
                    khoa_nhaptaycn: 'KKTS_01'
                }, function (jsonResponse) {
                    try {
                        var dm_khac = '<option value="000000">---Nhóm thiết bị---</option>';
                        $.each(jsonResponse.lstDmKhac112, function () {
                            dm_khac += '<option value="' + this.description + '">' + this.description + ' - ' + this.value + '</option>';
                        });
                        $('#' + idNhomTb).html(dm_khac);

                        if (jsonResponse.msgError !== null) {
                            $('#message_suc_err').text(jsonResponse.msgError);
                        }
                    } catch (e) {
                        alert(e.toString());
                    }
                });
                $.getJSON('loadPGD', {
                    Message: 'fileTemplate',
                    khoa_nhaptaycn: 'KKTS_01'
                }, function (jsonResponse) {
                    try {
                        var dm_pgd = '<option value="000000">---Chọn đơn vị---</option>';
                        $.each(jsonResponse.lstPGD_API, function () {
                            dm_pgd += '<option value="' + this.posCode + '">' + this.posCode + ' - ' + this.posName + '</option>';
                        });
                        $('#' + idPGD).html(dm_pgd);

                        if (jsonResponse.msgError !== null) {
                            $('#message_suc_err').text(jsonResponse.msgError);
                        }
                    } catch (e) {
                        alert(e.toString());
                    }
                });
                $.getJSON('loadDmKhac113', {
                    Message: 'fileTemplate',
                    khoa_nhaptaycn: 'KKTS_01'
                }, function (jsonResponse) {
                    try {
                        var dm_khac = '<option value="000000">---Nơi sử dụng---</option>';
                        $.each(jsonResponse.lstDmKhac113, function () {
                            dm_khac += '<option value="' + this.code + '">' + this.code + ' - ' + this.value + '</option>';
                        });
                        $('#' + idNsd).html(dm_khac);

                        if (jsonResponse.msgError !== null) {
                            $('#message_suc_err').text(jsonResponse.msgError);
                        }
                    } catch (e) {
                        alert(e.toString());
                    }
                });
                $.getJSON('loadDmKhac114', {
                    Message: 'fileTemplate',
                    khoa_nhaptaycn: 'KKTS_01'
                }, function (jsonResponse) {
                    try {
                        var dm_khac = '<option value="000000">---ĐVT---</option>';
                        $.each(jsonResponse.lstDmKhac114, function () {
                            dm_khac += '<option value="' + this.code + '">' + this.code + ' - ' + this.value + '</option>';
                        });
                        $('#' + idDvt).html(dm_khac);

                        if (jsonResponse.msgError !== null) {
                            $('#message_suc_err').text(jsonResponse.msgError);
                        }
                    } catch (e) {
                        alert(e.toString());
                    }
                });
                $.getJSON('loadDmKhac116', {
                    Message: 'fileTemplate',
                    khoa_nhaptaycn: 'KKTS_01'
                }, function (jsonResponse) {
                    try {
                        var dm_khac = '<option value="000000">---Tình trạng---</option>';
                        $.each(jsonResponse.lstDmKhac116, function () {
                            dm_khac += '<option value="' + this.code + '">' + this.code + ' - ' + this.value + '</option>';
                        });
                        $('#' + idThuctrang).html(dm_khac);

                        if (jsonResponse.msgError !== null) {
                            $('#message_suc_err').text(jsonResponse.msgError);
                        }
                    } catch (e) {
                        alert(e.toString());
                    }
                });
            }

            function deleteRow(indx) {
                var table = document.getElementById("subTable");
                table.deleteRow(indx);
                onLoadData();
            }
        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 98%;height: 400px;">    
            <div id="divTitle" style="text-align: left">
                        <a style="text-decoration: underline; color: #3dc21b" 
                           href="javascript:funcTableFile('KKTS_01_HDKT_<s:property value="ssduan1"/>','<s:property value="pos_cd_username"/>','<s:property value="main_pos_username"/>','<s:property value="ssduan1"/>','<s:property value="ssngay1"/>')">DANH SÁCH HỘI ĐỒNG KIỂM TRA </a>
            
                </div>
                <div id="divTitle">
                    DANH SÁCH TÀI SẢN KIỂM KÊ<br> 
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Đơn vị đã gửi dữ liệu)</a></s:if>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
            </div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th class="STT1" rowspan="2">STT</th>                           
                    <th class="STT3" rowspan="2">Đơn vị</th>  
                    <th class="STT2" rowspan="2">Tên thiết bị</th>  
                    <th class="STT2" rowspan="2">Nhóm thiết bị</th>
                    <th class="STT2" rowspan="2">Nơi sử dụng</th>
                    <th class="STT2" rowspan="2">ĐVT</th>
                    <th class="STT3" colspan="3">Số lượng</th>
                    <th class="STT4" rowspan="2">Ký hiệu hàng hóa</th>   
                    <th class="STT4" rowspan="2">Serial Number</th>
                    <th class="STT4" rowspan="2">Tình trạng tài sản</th> 
                    <th class="STT2" rowspan="2">Trạng thái</th>  
                </tr>
                <tr>
                    <th class="STT2">Sổ sách</th>      
                    <th class="STT2">Kiểm kê</th> 
                    <th class="STT2">Chênh lệch</th> 
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
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" />
                            <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                            <input type="hidden" value="<s:property value="%{#rowstatus.index + 1}" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                            <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
                            <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                            <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                            <input type="hidden" value="<s:property  value="D13" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13"/>

                        </td>
                        <td class="D0">
                            <s:if test="%{#rowstatus.index == 0 || lstDulieuNt[#rowstatus.index].D1 != lstDulieuNt[#rowstatus.index - 1].D1}">
                                <!-- Dropdown for visible PGD selection -->
                                <select id="lstPGD_<s:property value='%{#rowstatus.index}' />" style="width: 150px;border: hidden; background: khaki"
                                        name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D1">
                                    <option value="000000">----Chọn đơn vị----</option>
                                    <s:iterator value="lstPGD_API" status="ideRows" var="language">
                                        <option value="<s:property value='posCode'/>"
                                                <s:if test='%{#language.posCode == D1}'>selected</s:if>>
                                            <s:property value="posCode"/> - <s:property value="posName"/>
                                        </option>
                                    </s:iterator>
                                </select>
                            </s:if>
                            <s:else>
                                <!-- Hidden input to save PGD value when dropdown is not displayed -->
                                <input type="hidden" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D1"
                                       value="<s:property value='D1'/>" />
                            </s:else>
                        </td>
                        <td class="D0">
                            <select  id="lstDm111_<s:property  value='%{#rowstatus.index}' />" style="width: 150px;border: hidden"
                                     name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                     onchange="updateD3Selection(this)">
                                <option value="000000">---Tên thiết bị---</option>
                                <s:iterator value="lstDmKhac111" status="ideRows" var="language">
                                    <option value="<s:property value="description"/>" 
                                            <s:if test='%{#language.description == D2}'>selected</s:if>>
                                        <s:property value="description"/> - <s:property value="value"/>
                                    </option>        
                                </s:iterator>
                            </select>
                        </td>

                        <td class="D0">
                            <select  id="lstDm112_<s:property  value='%{#rowstatus.index}' />" style="width: 150px;border: hidden"
                                     name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3">
                                <option value="000000">---Nhóm thiết bị---</option>
                                <s:iterator value="lstDmKhac112" status="ideRows" var="language">
                                    <option value="<s:property value="description"/>" 
                                            <s:if test='%{#language.description == D3}'>selected</s:if>>
                                        <s:property value="description"/> - <s:property value="value"/>
                                    </option>        
                                </s:iterator>
                            </select>
                        </td>

                        <td class="D0">
                            <select  id="lstDm113_<s:property  value='%{#rowstatus.index}' />" style="width: 150px;border: hidden"
                                     name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4">
                                <option value="000000">---Nơi sử dụng---</option>
                                <s:iterator value="lstDmKhac113" status="ideRows" var="language">
                                    <option value="<s:property value="code"/>" 
                                            <s:if test='%{#language.code == D4}'>selected</s:if>>
                                        <s:property value="code"/> - <s:property value="value"/>
                                    </option>        
                                </s:iterator>
                            </select>
                        </td>

                        <td class="D0">
                            <select  id="lstDm114_<s:property  value='%{#rowstatus.index}' />" style="width: 80px;border: hidden"
                                     name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5">
                                <option value="000000">---ĐVT---</option>
                                <s:iterator value="lstDmKhac114" status="ideRows" var="language">
                                    <option value="<s:property value="code"/>" 
                                            <s:if test='%{#language.code == D5}'>selected</s:if>>
                                        <s:property value="code"/> - <s:property value="value"/>
                                    </option>        
                                </s:iterator>
                            </select>
                        </td>

                        <td> <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value='%{#rowstatus.index}' />" 
                                    name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number" onchange="calc(this);"/>
                        </td> 
                        <td> <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value='%{#rowstatus.index}' />" 
                                    name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="number" onchange="calc(this);"/>
                        </td> 
                        <td> <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value='%{#rowstatus.index}' />" 
                                    name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="number" readonly/>
                        </td> 
                        <td class="D0"><textarea style="width: 98%" placeholder="Nhập tối đa 500 ký tự" id="D9_<s:property  value='%{#rowstatus.index}' />" 
                                                 name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D9" maxlength="500"><s:property value='D9'/></textarea>
                        </td>
                        <td class="D0"><textarea style="width: 98%" placeholder="Nhập tối đa 500 ký tự" id="D10_<s:property  value='%{#rowstatus.index}' />" 
                                                 name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D10" maxlength="500"><s:property value='D10'/></textarea>
                        </td>
                        <td class="D0">
                            <select  id="lstDm116_<s:property  value='%{#rowstatus.index}' />" style="width: 150px;border: hidden"
                                     name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11">
                                <option value="000000">---Tình trạng---</option>
                                <s:iterator value="lstDmKhac116" status="ideRows" var="language">
                                    <option value="<s:property value="code"/>" 
                                            <s:if test='%{#language.code == D11}'>selected</s:if>>
                                        <s:property value="code"/> - <s:property value="value"/>
                                    </option>        
                                </s:iterator>
                            </select>
                        </td>
                        <td class="D0"><input type="button" style="color: red;width: 50px"
                                              onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D13"/>', '<s:property value="D12"/>');" value="Xóa"/>

                        </td>
                    </s:iterator>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td class="D0"><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="D0 SOKU"/></td>
                </tr>  
            </table>
        </div>
        <div id="luu_thanhcong"></div>
        <script>
            function cancelAssign(D1, D13, D12) {
                var url, sdata;

                url = "delete_KKTS_2024.action?" + "sD1" + D1 + "&sMA=" + D13 + "&sD12=" + D12,
                        sdata = jQuery("#frmdata").serialize();
                $("#viewData").html('<img src="img/loading.gif"/>');
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        if (data === "200") {
                            alert("Xóa dữ liệu thành công!");
                        } else if (data === "100") {
                            alert("Lỗi: Đơn vị đã gửi dữ liệu không thể thao tác!");
                        } else {
                            alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                        }
                        onLoadData();
                    },
                    error: function (request) {
                        alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                        onLoadData();
                    }
                });
            }
            function calc(id) {
                var row = id.parentNode.parentNode;
                var CT_D3 = row.cells[6].getElementsByTagName('input')[0].value;
                var CT_D9 = row.cells[7].getElementsByTagName('input')[0].value;
                var res = parseFloat(CT_D3.replace(/,/g, '')) - parseFloat(CT_D9.replace(/,/g, ''));
                row.cells[8].getElementsByTagName('input')[0].value = res.toLocaleString('en-US');
                row.cells[8].getElementsByTagName('input')[0].style.color = 'red';
            }
            function updateD3Selection(d2Select) {
                const selectedDescription = d2Select.value;
                const d3Select = d2Select.closest('td').nextElementSibling.querySelector('select');

                for (let option of d3Select.options) {
                    if (option.value === selectedDescription) {
                        option.selected = true;
                        break;
                    }
                }
            }

            function funcTableFile(skhoa, smapgd, smacn, sduan, sngaybc) {
                var screenWidth = screen.width, screenHeight = screen.height;
                var w = screenWidth / 1.5;
                var h = screenHeight/2;
                var left = (screenWidth - w) / 2;
                var top = (screenHeight - h) / 2;
                var urlParam = "skhoa=" + skhoa + "&smapgd=" + smapgd + "&smacn=" + smacn + "&sduan=" + sduan + "& sngaybc=" + sngaybc;
                var url = "/IMS_REPORTS/popupTableHoidong.action?" + urlParam;
                popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ", directories=no, status=no, menubar=no, personalbar=no, resizable=yes, location=no, scrollbars=yes, toolbar=no, border=no");
                popWindow.focus();
            }
        </script>
    </body>
</html>
