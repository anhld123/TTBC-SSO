<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thực hiện kế hoạch</title>
        <sx:head/>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>

        <style type="text/css">
            *{
                font: 13px Arial, Helvetica, sans-serif;
            }
            table{
                border-style: solid;
                border-collapse: collapse;
                width: 100%;
                line-height: 19px;
            }
            .tbhead th{
                background-color: #5e5e55;
                font-weight: bold;
                color: #fff;
                text-align: center;
                padding: 5px;
            }
            .cscontent td{
                padding-left:5px;
            }
            .cscontent:hover{
                background-color: #ffff99;
            }

            .cscontent:hover input[type="text"]{
                background-color: #ffff99;
            }
            .tblmain tr td{
                font-weight: bold;
                color: #018c3b;
                word-wrap: break-word;
            }

            input{
                border: 0px;
            }

            .BOLD input[type="text"]
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }

            .ITALIC input[type="text"]
            {
                font-style: italic;
                font-size: 12px;
                width: 95%;
            }

            input[type="text"]
            {
                width: 95%;
            }

            input[type="button"]
            {
                border: 2px solid black;
                border-radius: 5px;
                margin-left: 3px;
            }

            .parameter{
                border: 1px solid black;
                width: 50%;
            }

            #posCD, #quyBc, #namBc, #maCn, #userId{
                width: 70px;
            }
            input[readonly] {
                background-color: #cccccc;
                color: #666;
                cursor: not-allowed;
            }
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

        <script>
            var max_row = 0;
            $(document).ready(function () {
//                initTable();
            });
            $(document).ready(function () {
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('input.number2').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".STT1").css({"width": "30px"});
                $(".STT2").css({"width": "90px"});
                $(".STT3").css({"width": "150"});
                $(".STT4").css({"width": "70%"});
                $(".STT5").css({"width": "70px"});
                $(".STT6").css({"width": "80px"});
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

            $(document).ready(function () {
                $("#update").click(function () {
                    document.getElementById("update").disabled = true;
                    sleep(1000);
                    document.getElementById("update").disabled = false;
                });

                $('.hideColumn').hide();

                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);

                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
            });

            //Xu ly tinh tong cho tung dong


            function fnResetVal() {

            }
            //Check xem du lieu da ok chua
            //Neu ok roi thi goi su kien submit du lieu
            function fnCheckThenSubmit() {
                document.getElementById('loadingImageDiv_para').style.display = "block";
                $("#update").click(function () {
                });
                if (validateRequiredFields()) {
                    $("#update").trigger('click');
                }
            }


            function tai_lai_trang() {
                location.reload();
            }

            function sleep(milliSeconds) {
                var startTime = new Date().getTime(); // get the current time
                while (new Date().getTime() < startTime + milliSeconds)
                    ; // hog cpu
            }


            document.addEventListener('DOMContentLoaded', function () {
                document.getElementById('subTable').addEventListener('keydown', function (event) {
                    if (event.key === 'Enter') {
                        event.preventDefault();
//                        alert('Phím Enter đã bị khóa!');
                    }
                });
            });
        </script>

    </head>
    <body style="background-image: url('img/backgroud_logo.jpg');background-size: cover;">
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdata" id="frmdata" >
                <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain" style="background-image: url('img/baner11.jpg');background-size: cover;">              
                    <tr>
                        <td width="70%" style="color: black; font-family: Comic Sans MS; text-shadow: 1px 1px 0 white, -1px -1px 0 white, 1px -1px 0 white, -1px 1px 0 white;">
                            <div id="message_suc_err" style="height: 10px"></div>
                        </td>
                        <td align="right">     
                            <div id="result" style="color: red">                            
                            </div>
                            <div id="loadingImageDiv_para"  style="display: none;">
                                <img id="loadingImage" src='img/loading.gif' border='0' >
                            </div>
                            &nbsp;<input type="button" id="idSave_Popup" value="Lưu dữ liệu"/> 
                            &nbsp;<input type="button" id="cmdEnd" value="Thoát"/> 
                            <sj:submit id="update" name="update"  targets="result" onBeforeTopics="beforediv_para"
                                       onCompleteTopics="completediv_para" cssStyle="display: none"/>
                        </td>       
                    </tr>
                </table>
                <hr>

                <table   border="1" style="width: 98%" align="center">
                    <tr>
                    <div id="divTitle" style="text-align: center; font: 16px Arial, Helvetica, sans-serif; font-weight: bold; color: #0000FF">
                        DANH SÁCH HỘI ĐỒNG KIỂM TRA
                        <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Đơn vị đã gửi dữ liệu)</a></s:if>
                        <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
                        <div style="height:10px"></div>
                    </div>
                    </tr>
                    <table  id="subTable" border="1" style="width: 98%" align="center">
                        <tr> 
                            <th class="STT1">STT</th>                           
                            <th class="STT3">Họ và tên</th> 
                            <th class="STT3">Phòng ban</th> 
                            <th class="STT2">Chức vụ công tác</th>
                            <th class="STT2" >Chức vụ kiểm tra</th>
                            <th class="STT2">Trạng thái</th>

                        </tr>
                        <tr>
                            <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                            <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                            <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                            <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                            <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                            <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                            <tr id="tablefix"> 
                            <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                            <input type="hidden" value="<s:property value="%{#rowstatus.index + 1}" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                            <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
                            <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                            <input type="hidden" value="<s:property  value="CO_TONGHOP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/>
                            <input type="hidden" value="<s:property  value="NGUOI_NHAP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_NHAP"/>
                            <input type="hidden" value="<s:property  value="NAMBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NAMBC"/>
                            <input type="hidden" value="<s:property  value="MAPGD" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                            <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>
                            <input type="hidden" value="<s:property  value="D5" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"/>
                            <input type="hidden" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7"/>
                            <input type="hidden" value="<s:property  value="D9" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/>
                            <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                            <input type="hidden" value="<s:property  value="KIEUIN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KIEUIN"/>
                            <input type="hidden" value="<s:property  value="NHAPTAY" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/>
                            </td>
                            <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                            <td class="D0">   
                                <select  id="lstCanBo_<s:property  value='%{#rowstatus.index}' />" style="width: 200px;border: hidden"
                                         name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1">
                                    <option value="000000">---Mã cán bộ---</option>
                                    <s:iterator value="lstCanBo" status="ideRows" var="language">
                                        <option value="<s:property value="MaCB"/>" 
                                                <s:if test='%{#language.MaCB == D1}'>selected</s:if>>
                                            <s:property value="TenCB"/>
                                        </option>        
                                    </s:iterator>
                                    <option value="CNTT00000000042">03038 - Nguyễn Thị Hằng Nga</option>
                                    <option value="CNTT00000000054">03050 - Nguyễn Thanh Hoa</option>
                                </select>
                            </td>
                            <td class="D0">   
                                <select  id="lstDm119_<s:property  value='%{#rowstatus.index}' />" style="width: 200px;border: hidden"
                                         name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2">
                                    <option value="000000">---Phòng ban---</option>
                                    <s:iterator value="lstDmKhac119" status="ideRows" var="language">
                                        <option value="<s:property value="description"/>" 
                                                <s:if test='%{#language.description == D2}'>selected</s:if>>
                                            <s:property value="description"/> - <s:property value="value"/>
                                        </option>        
                                    </s:iterator>
                                </select>
                            </td>
                            <td class="D0">   
                                <select  id="lstDm118_<s:property  value='%{#rowstatus.index}' />" style="width: 200px;border: hidden"
                                         name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3">
                                    <option value="000000">---Chức vụ công tác---</option>
                                    <s:iterator value="lstDmKhac118" status="ideRows" var="language">
                                        <option value="<s:property value="description"/>" 
                                                <s:if test='%{#language.description == D3}'>selected</s:if>>
                                            <s:property value="description"/> - <s:property value="value"/>
                                        </option>        
                                    </s:iterator>
                                </select>
                            </td>
                            <td class="D0">   
                                <select  id="lstDm117_<s:property  value='%{#rowstatus.index}' />" style="width: 200px;border: hidden"
                                         name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4">
                                    <option value="000000">---Chức vụ kiểm tra--</option>
                                    <s:iterator value="lstDmKhac117" status="ideRows" var="language">
                                        <option value="<s:property value="description"/>" 
                                                <s:if test='%{#language.description == D4}'>selected</s:if>>
                                            <s:property value="description"/> - <s:property value="value"/>
                                        </option>        
                                    </s:iterator>
                                </select>
                            </td>

                            <td class="D0"><input type="button" style="color: red;width: 100px"
                                                  onclick="cancelAssign('<s:property value="KHOA"/>', '<s:property value="D13"/>', '<s:property value="D12"/>');" value="Xóa"/>

                            </td>
                        </s:iterator>
                        <tr>

                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td class="D0"><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="D0 SOKU" style="width: 100px"/></td>
                        </tr> 
                    </table>
                </s:form>
        </div>
        <script>
            $("#idSave_Popup").click(function () {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                if (aCheck) {
                    var isValid = true;
                    var chot = document.getElementById("chotsl").value;
                    if (chot === "2") {
                        alert("Dữ liệu đã gửi, không thể lưu!");
                        isValid = false;
                    }
                    for (var i = 0; i < rowcount; i++) {
                        try {
                            var element = document.getElementById("lstCanBo_" + i);
                            if (element) { // Check if the element exists
                                var posCd = element.value;
                                if (posCd === "000000") {
                                    $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px ; font-weight: bold'> Cảnh báo: Chưa nhập dữ liệu!</h>");
                                    element.style.backgroundColor = "#EEAFA6";
                                    return;
                                }
                            } else {
                            }
                            var lstDm111 = document.getElementById("lstDm87_" + i).value;
                            if (lstDm111 === "000000") {
                                $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px ; font-weight: bold'> Cảnh báo: Chưa nhập dữ liệu!</h>");
                                document.getElementById("lstDm87_" + i).style.backgroundColor = "#EEAFA6";
                                return;
                            }
                            var lstDm112 = document.getElementById("lstDm88_" + i).value;
                            if (lstDm112 === "000000") {
                                $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px ; font-weight: bold'> Cảnh báo: Chưa nhập dữ liệu!</h>");
                                document.getElementById("lstDm88_" + i).style.backgroundColor = "#EEAFA6";
                                return;
                            }
                            var lstDm113 = document.getElementById("lstDm117_" + i).value;
                            if (lstDm113 === "000000") {
                                $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px ; font-weight: bold'> Cảnh báo: Chưa nhập dữ liệu!</h>");
                                document.getElementById("lstDm117_" + i).style.backgroundColor = "#EEAFA6";
                                return;
                            }
                        } catch (e) {
                        }
                    }
                    if (isValid) {
                        var url, sdata;
                        url = "save_KKTS_HDKT_2024.action";
                        sdata = jQuery("#frmdata").serialize();
                        $("#viewData").html('<img src="img/loading.gif"/>');
                        btnDisabled(1);
                        $.ajax({
                            type: "POST",
                            url: url,
                            data: sdata,
                            success: function (data) {
                                if (data === "200") {
                                    alert("Thành công: Lưu dữ liệu.");
                                    idEnd();
                                } else {
                                    alert("Lỗi: Lưu dữ liệu.");
                                    idEnd();
                                }
                            },
                            complete: function () {
                                btnDisabled(0);
                            },
                            error: function (request) {
                                alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                                tai_lai_trang();
                            }
                        });
                    }
                }

            });
            function btnDisabled(status) {
                if (status === 1) {
                    $("#loadDatatmp").prop('disabled', true);
                    $("#idPheduyet").prop('disabled', true);
                    $("#idSave").prop('disabled', true);
                    $("#idSaveLock").prop('disabled', true);
                    $("#idDelete").prop('disabled', true);
                } else {
                    $("#idPheduyet").prop('disabled', false);
                    $("#idSave").prop('disabled', false);
                    $("#loadDatatmp").prop('disabled', false);
                    $("#idSaveLock").prop('disabled', false);
                    $("#idDelete").prop('disabled', false);
                }
            }
            ;

            function cancelAssign(skhoa, sma, sten) {
                var url, sdata;
                var isValid = true;
                var chot = document.getElementById("chotsl").value;
                if (chot === "2") {
                    alert("Dữ liệu đã gửi, không thể lưu!");
                    isValid = false;
                }
                if (isValid) {
                    url = "delete_KKTS_HDKT_2024.action?" + "skhoa=" + skhoa + "&sma=" + sma + "&sten=" + sten,
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
                            idEnd();
                        },
                        error: function (request) {
                            alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                            tai_lai_trang();
                        }
                    });
                }
            }
            function addRow(indx) {
                var index = parseInt(indx);
                var table = document.getElementById("subTable");
                var rowCount = table.rows.length - 4;
                if (max_row < rowCount) {
                    max_row = rowCount + 1;
                } else {
                    max_row++;
                    rowCount = max_row;
                }
                var idMacb = "lstCanBo_" + max_row;
                var idPban = "lstDm119_" + max_row;
                var idCvu1 = "lstDm118_" + max_row;
                var idCvu2 = "lstDm117_" + max_row;
                var newTr = '<tr>' +
                        '<td class="D0"><input type="text" value="' + (max_row + 1) + '" id="TT_HIENTHI" name="lstDulieuNt[' + max_row + '].TT_HIENTHI" class="D0 number" onfocus="this.select();" /></td>' +
                        '<td class="D0"><select style="width: 200px;border: hidden" name="lstDulieuNt[' + max_row + '].D1" id="' + idMacb + '"></select></td>' +
                        '<td class="D0"><select style="width: 200px;border: hidden" name="lstDulieuNt[' + max_row + '].D2" id="' + idPban + '"></select></td>' +
                        '<td class="D0"><select style="width: 200px;border: hidden" name="lstDulieuNt[' + max_row + '].D3" id="' + idCvu1 + '"></select></td>' +
                        '<td class="D0"><select style="width: 200px;border: hidden" name="lstDulieuNt[' + max_row + '].D4" id="' + idCvu2 + '"></select></td>' +
                        '<td class="D0"><input type="button" style="color: red;width: 100px" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)"/></td>' +
                        '</tr>';

                $(newTr).insertBefore($('table#subTable tr').eq(index));


                // Thiết lập lại các kiểu CSS
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('input.number2').css({"text-align": "right"});
                // Cài đặt định dạng số
                $('.number').number(true, 0);
                $('.number2').number(true, 0);

                // Fetch data and populate the new select elements
                $.getJSON('loadDmKhac119', {
                    Message: 'fileTemplate'
                }, function (jsonResponse) {
                    try {
                        var dm_khac = '<option value="000000">---Phòng ban---</option>';
                        $.each(jsonResponse.lstDmKhac119, function () {
                            dm_khac += '<option value="' + this.description + '">' + this.description + ' - ' + this.value + '</option>';
                        });
                        $('#' + idPban).html(dm_khac);

                        if (jsonResponse.msgError !== null) {
                            $('#message_suc_err').text(jsonResponse.msgError);
                        }
                    } catch (e) {
                        alert(e.toString());
                    }
                });
                $.getJSON('loadDmKhac118', {
                    Message: 'fileTemplate'
                }, function (jsonResponse) {
                    try {
                        var dm_khac = '<option value="000000">---Chức vụ công tác---</option>';
                        $.each(jsonResponse.lstDmKhac118, function () {
                            dm_khac += '<option value="' + this.description + '">' + this.description + ' - ' + this.value + '</option>';
                        });
                        $('#' + idCvu1).html(dm_khac);
                        if (jsonResponse.msgError !== null) {
                            $('#message_suc_err').text(jsonResponse.msgError);
                        }
                    } catch (e) {
                        alert(e.toString());
                    }
                });

                $.getJSON('loadcanbo', {
                    Message: 'fileTemplate'
                }, function (jsonResponse) {

                    try {
                        var dm_khac = '<option value="000000">---Mã cán bộ---</option>';
                        $.each(jsonResponse.lstCanBo, function () {
                            dm_khac += '<option value="' + this.maCB + '">' + this.tenCB + '</option>';
                        });
                        dm_khac += '<option value="CNTT00000000042">03038 - Nguyễn Thị Hằng Nga</option>';
                        dm_khac += '<option value="CNTT00000000054">03050 - Nguyễn Thanh Hoa</option>';
                        $('#' + idMacb).html(dm_khac);

                        if (jsonResponse.msgError !== null) {
                            $('#message_suc_err').text(jsonResponse.msgError);
                        }
                    } catch (e) {
                        alert(e.toString());
                    }
                });

                $.getJSON('loadDmKhac117', {
                    Message: 'fileTemplate'
                }, function (jsonResponse) {
                    try {
                        var dm_khac = '<option value="000000">---Chức vụ kiểm tra---</option>';
                        $.each(jsonResponse.lstDmKhac117, function () {
                            dm_khac += '<option value="' + this.code + '">' + this.code + ' - ' + this.value + '</option>';
                        });
                        $('#' + idCvu2).html(dm_khac);

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
                idEnd();
            }
            window.onbeforeunload = function () {
                // Thực hiện hành động reset bảng trước khi đóng
                window.opener.document.getElementById('loadDatatmp').click();
            };
            $("#cmdEnd").click(function () {
                window.opener.document.getElementById('loadDatatmp').click();
                window.close();
            });

            function idEnd() {
                window.opener.document.getElementById('loadDatatmp').click();
                window.close();
            }
        </script>
    </body>
</html>
