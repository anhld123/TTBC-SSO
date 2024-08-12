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
    .styled-button {
        height: auto; /* Let the height adjust automatically */
        padding: 0;
        background-color: transparent; /* Remove background color */
        color: #003eff;
        border: none; /* Remove border */
        cursor: pointer;
        font-size: 14px;
        text-decoration: underline; /* Add underline to resemble a link */
    }

    .styled-button:hover {
        color: red; /* Change color on hover */
        text-decoration: none; /* Remove underline on hover */
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
                $('.number').number(true, 2);
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
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            function addRow(indx) {
                var index = parseInt(indx);
                var table = document.getElementById("subTable");
                var rowCount = table.rows.length - 5;

                if (max_row < rowCount) {
                    max_row = rowCount;
                } else {
                    max_row++;
                    rowCount = max_row;
                    rownew = rowCount + 1;
                }

                var idTaisan = "idTaisan_" + rowCount;
                var idMaTaisan = "idMaTaisan_" + rowCount;
                var idmaPgd = document.getElementById("maPgd_temp").value;
                var idtenPgd = document.getElementById("tenPgd_temp").value;

                var newTr = '<tr>' +
                        '<td><input type="text" value="' + rownew + '" id="TT_HIENTHI' + rowCount + '" name="lstDulieuNt[' + rowCount + '].TT_HIENTHI" class="D0" onfocus="this.select();" /></td>' +
                        '<td><input readonly="true" type="text" value="' + idmaPgd + '" id="MAPGD' + rowCount + '" name="lstDulieuNt[' + rowCount + '].MAPGD"  onfocus="this.select();"/></td>' +
                        '<td><input readonly="true" type="text" value="' + idtenPgd + '" id="D1' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D1"  onfocus="this.select();"/></td>' +
                        '<td><select name="lstDulieuNt[' + rowCount + '].D2" id="' + idTaisan + '" onchange="updateD6(this, ' + rowCount + ')"></select></td>' +
                        '<td><input type="text" value="0" id="D3' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D3" class="number" onfocus="this.select();"/></td>' +
                        '<td><input type="text" value="0" id="D4' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D4" class="number" onfocus="this.select();"/></td>' +
                        '<td class="D0"><textarea type="text" value="" id="D5' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D5" placeholder="Nhập tối đa 200 ký tự" maxlength="200" onfocus="this.select();" style="width: 98%"></textarea></td>' +
                        '<td><select name="lstDulieuNt[' + rowCount + '].D6" id="' + idMaTaisan + '"></select></td>' +
                        '<td class="D0"><textarea type="text" value="" id="D7' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D7" placeholder="Nhập tối đa 200 ký tự" maxlength="200" onfocus="this.select();" style="width: 98%"></textarea></td>' +
                        '<td class="D0"><textarea type="text" value="" id="D8' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D8" placeholder="Nhập tối đa 200 ký tự" maxlength="200" onfocus="this.select();" style="width: 98%"></textarea></td>' +
                        '<td><input type="text" value="0" id="D9' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D9" class="number" onfocus="this.select();"/></td>' +
                        '<td><input type="text" value="0" id="D10' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D10" class="number" onfocus="this.select();"/></td>' +
                        '<td><input type="text" value="0" id="D11' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D11" class="number"  onfocus="this.select();"/></td>' +
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
                $('.number').number(true, 2);
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
                $.getJSON('loadDMChtrinh', {
                    Message: 'fileTemplate',
                    khoa_nhaptaycn: 'KTTC_MUASAM_01'
                }, function (jsonResponse) {
                    try {
                        var chtrinh = '<option value="000000">----Tên tài sản cần bổ sung thay thế----</option>';
                        $.each(jsonResponse.lstTaisan, function () {
                            chtrinh += '<option value="' + this.description + '">' + this.value + '</option>';
                        });
                        $('#' + idTaisan).html(chtrinh);

                        var dm_chiemdung = '<option value="000000">----Mã tài sản----</option>';
                        $.each(jsonResponse.lstTaisan, function () {
                            dm_chiemdung += '<option value="' + this.description + '">' + this.description + '</option>';
                        });
                        $('#' + idMaTaisan).html(dm_chiemdung);

                        if (jsonResponse.msgError !== null) {
                            $('#message_suc_err').text(jsonResponse.msgError);
                        }
                    } catch (e) {
                        alert(e.toString());
                    }
                });
            }

// Hàm cập nhật D6 khi chọn D2
            function updateD6(selectElement, rowCount) {
                var selectedValue = selectElement.value;
                var d6Select = document.getElementById('idMaTaisan_' + rowCount);

                for (var i = 0; i < d6Select.options.length; i++) {
                    if (d6Select.options[i].value === selectedValue) {
                        d6Select.value = selectedValue;
                        break;
                    }
                }
            }


            function deleteRow(indx) {
                var table = document.getElementById("subTable");
                var rowCount = table.rows.length - 4; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
            }
        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;height: 400px;">    
            <div id="divTitle">
                KẾ HOẠCH MUA SẮM TÀI SẢN CỐ ĐỊNH<br>Năm <s:property  value="namBc" />
                <s:if test="lock.equalsIgnoreCase('1')" ><a class="color_11">(Phòng giao dịch đã gửi dữ liệu)</a></s:if>
                <input type="hidden" id ="maPgd_temp" value="<s:property  value="maPgd" />">
                <input type="hidden" id ="tenPgd_temp" value="<s:property  value="tenPgd" />">
                <input type="hidden" id ="lock_temp" value="<s:property  value="lock" />">
            </div>
            <div id="divDonvitinh">
                Đơn vị tính: Triệu đồng
            </div>
            <table border="1" class="editDelete" id="subTable" align="center"> 
                <tr>
                    <th rowspan="2" class="STT1" >STT</th>  
                    <th rowspan="2" class="STT2">Mã pos</th>
                    <th rowspan="2" class="STT2">Tên đơn vị</th> 
                    <th rowspan="2" class="STT2">Tên tài sản<br>cần bổ sung thay thế</th>
                    <th rowspan="2" class="STT2">Tổng số lượng hiện có</th> 
                    <th rowspan="2" class="STT2">Tổng giá trị còn lại</th> 
                    <th rowspan="2" style="width: 15%">Hiện trạng tài sản <br><a style="color: red">(Ghi rõ trang bị năm nào, hiện trạng của TSCĐ tương đương cần thay thế)</a></th> 
                    <th colspan="6" >TSCĐ đề nghị trang bị năm <s:property  value="nambc_next" /></th>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                        <th rowspan="2" class="STT2" style="color: red">Thêm/Xóa</th> </s:if>
                    </tr>
                    <tr>
                        <th>Mã nhóm TSCĐ- Theo VB 2858</th> 
                        <th style="width: 15%">Mục đích, nơi sử dụng<br><a style="color: red">(Ghi rõ bộ phận sử dụng; đề nghị thay thế hay trang bị thêm)</a></th>
                        <th style="width: 20%">Quy cách, cấu hình kỹ thuật<br><a style="color: red">(Ghi rõ công suất đối với các TSCĐ có chỉ số công suất, đối với ô tô ghi rõ 1 cầu hay 2 cầu; bàn ghế ghi rõ kích thước; bàn quầy giao dịch ghi rõ số m dài ...)</a></th> 
                        <th>Số lượng</th>
                        <th>Thành tiền</th>
                        <th>Nguồn vốn</th>
                    </tr>
                    <tr style="font-style: italic;">
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
                        <s:if test="Grade.equalsIgnoreCase('1')">
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(14)</th>
                        </s:if>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0 STT1"> 
                            <input type="text" value="<s:property value="%{#rowstatus.index + 1}" />"
                                   id="TT_HIENTHI_<s:property  value='%{#rowstatus.index}' />" readonly="true"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="STT1 D0"/>
                            <s:if test="!Grade.equalsIgnoreCase('1')">
                                <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>                             
                                <input type="hidden" value="<s:property  value="MAPGD" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>                             
                                <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>
                                <input type="hidden" value="<s:property  value="NGUOI_NHAP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_NHAP"/>                             
                                <input type="hidden" value="<s:property  value="NGUOI_DUYET" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_DUYET"/>
                            </s:if>
                        </td>                                     
                        <td>
                            <input type="text" value="<s:property  value="MAPGD" />"
                                   id="MAPGD_<s:property  value='%{#rowstatus.index}' />" readonly="true"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" class="STT1"/>
                        </td>  
                        <td>
                            <input type="text" value="<s:property  value="D1" />"
                                   id="D1_<s:property  value='%{#rowstatus.index}' />" readonly="true"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="STT3"/>
                        </td>  
                        <td>              
                            <select id="D2_<s:property value='%{#rowstatus.index}' />" 
                                    <s:if test="!Grade.equalsIgnoreCase('1')"> onmousedown="return false"</s:if>
                                    name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D2"
                                    onchange="updateD66(this, <s:property value='%{#rowstatus.index}' />)">
                                <option value="000000">----Tên tài sản cần bổ sung thay thế----</option>
                                <s:iterator value="lstTaisan" status="ideRows" var="language">
                                    <option value="<s:property value='description' />" 
                                            <s:if test='%{#language.description == D2}'>selected</s:if>>
                                        <s:property value="value" />
                                    </option>
                                </s:iterator>
                            </select>

                        </td>  
                        <td>              
                            <input type="text" value="<s:property  value="D3" />"
                                   <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>
                                   id="D3_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="number STT2"/>
                        </td>  
                        <td>              
                            <input type="text" value="<s:property  value="D4" />" 
                                   <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>
                                   id="D4_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="number STT2"/>
                        </td>  
                        <td class="D0">
                            <textarea style="width: 98%" placeholder="Nhập tối đa 200 ký tự" id="D5_<s:property  value='%{#rowstatus.index}' />" 
                                      name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D5" maxlength="200"
                                      <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>
                                      ><s:property value='D5'/></textarea>
                        </td> 
                        <td>            
                            <select  id="D6_<s:property  value='%{#rowstatus.index}' />" 
                                     <s:if test="!Grade.equalsIgnoreCase('1')"> onmousedown="return false"</s:if>
                                     name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6">
                                <option value="000000">----Mã tài sản----</option>
                                <s:iterator value="lstTaisan" status="ideRows" var="language">
                                    <option value="<s:property value='description' />" 
                                            <s:if test='%{#language.description == D6}'>selected</s:if>>
                                        <s:property value="description" />
                                    </option>        
                                </s:iterator>
                            </select>
                        </td>  
                        <td class="D0">
                            <textarea style="width: 98%" placeholder="Nhập tối đa 200 ký tự" id="D7_<s:property  value='%{#rowstatus.index}' />" 
                                      name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D7" maxlength="200"
                                      <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>><s:property value='D7'/></textarea>
                            </td> 
                            <td class="D0">
                                <textarea style="width: 98%"  placeholder="Nhập tối đa 200 ký tự" id="D8_<s:property  value='%{#rowstatus.index}' />" 
                                      name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D8" maxlength="200"
                                      <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>><s:property value='D8'/></textarea>
                            </td> 
                            <td>           
                                <input type="text" value="<s:property  value="D9" />" 
                                   id="D9_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="number STT2"
                                   <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/>
                            </td>   
                            <td>              
                                <input type="text" value="<s:property  value="D10" />"
                                   id="D10_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number STT2"
                                   <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/>
                            </td> 
                            <td>              
                                <input type="text" value="<s:property  value="D11" />"
                                   id="D11_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="number STT2"
                                   <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/>
                            </td>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <td class="D0">
                                <s:if test="(#rowstatus.index + 1) == 1">
                                </s:if>
                                <s:else>
                                    <input style="color: red" type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="SOKU"/>
                                </s:else>
                            </td>
                        </s:if>
                    </tr>
                </s:iterator>
                <s:if test="Grade.equalsIgnoreCase('1')">
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
                        <td></td>
                        <td class="D0"><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="D0 SOKU"/></td>
                    </tr>  
                </s:if>
            </table>
        </div>
        <div id="luu_thanhcong"></div>
    </body>
    <script>
        function updateD66(selectElement, index) {
            var selectedValue = selectElement.value;
            var d6Select = document.getElementById('D6_' + index);

            for (var i = 0; i < d6Select.options.length; i++) {
                if (d6Select.options[i].value === selectedValue) {
                    d6Select.value = selectedValue;
                    break;
                }
            }
        }
    </script>
</html>
