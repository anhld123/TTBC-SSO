<%-- 
    Document   : index
    Created on : Nov 4, 2021, 1:09:48 PM
    Author     : WELCOME
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<s:head/>
<sj:head/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="js/jquery.dataTables.min.css">
        <script src="js/jquery.dataTables.min.js"></script>
        <style>
            *{
                font-family: Tahoma, Arial, Helvetica, sans-serif;
                font-size: 13px;
            }
            #tblTable {

                border-collapse: collapse;
                width: 100%;
            }

            #tblTable td, #tblTable th {
                border: 1px solid #ddd;
                padding: 4px;
            }

            #tblTable tr:nth-child(even){background-color: #f2f2f2;}

            #tblTable tr:hover {background-color: #ddd;}

            #tblTable th {
                padding-top: 6px;
                padding-bottom: 6px;
                text-align: left;
                background-color: #06713F;
                color: white;
            }
            .clsTitle, .clsHeader{
                width: 100%;
                text-align: center;
            }
            .clsTitle{
                margin-bottom: 5px;
                padding: 8px;
                background-color: lightgray;
                width:98.8%
            }
            .clsSoso{
                width: 99%;
                margin: 0px 3px 0px 3px;
                height: 100%;
                border: 0px;
                background-color: transparent;
                outline: none;
            }
            select{
                min-width: 155px;
            }
            .clsChon{
                text-align: center;
            }
            #cmdLuuDL{
                display: none;
            }
            .dataTables_length select{
                padding: 2px !important;
            }
            .dataTables_filter input{
                padding: 3px !important;
                margin-bottom: 3px !important;
                width: 300px;
            }
        </style>
    </head>
    <body>
        <form id="HDTK_FrmMain" name="HDTK_FrmMain">
            <div style="margin: 12px;">
                <div class="clsHeader"><h1>KẾT QUẢ HUY ĐỘNG TIẾT KIỆM</h1></div>
                <div class="clsTitle">
                    Ngày báo cáo <input type="date" id="dtNgaybc" name="dtNgaybc" value="">
                    Cán bộ 
                    <select id="cboCanBo" name="cboCanBo">
                        <option value="000000">----Chọn cán bộ----</option>
                        <s:iterator value="lstCanBo">
                            <option value='<s:property value="MaCB"/>'><s:property value="TenCB"/></option>
                        </s:iterator>
                    </select>
                    Chỉ tiêu được giao <input type="text" style="text-align: right;" id="txtChitieu" name="txtChitieu" value=1000000000 class="number">
                    Chỉ hiện những số đã gắn cán bộ <input type="checkbox" checked="true" name="flgFilter" id="flgFilter">
                    Tra cứu <input type="text" style="text-align: right;" id="cSeach" name="cSeach" value="" placeholder="Tìm kiếm thông tin sổ ...">                
                    <input type="button" value="Tải dữ liệu" id="cmdTaiDL" name="cmdTaiDL">
                    <input type="button" value="Lưu dữ liệu" id="cmdLuuDL" name="cmdLuuDL">
                    <input type="button" value="1" id="cmdLuuDL_tmp" name="cmdLuuDL_tmp" style="display: none;">
                    <input type="button" value="2" id="cmdTaiDL_tmp" name="cmdTaiDL_tmp" style="display: none;">
                </div>
                <div id="viewData" style="width: 99%;"></div>
            </div>
        </form>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>

        <script>

            $(document).ready(function () {
                //Tải dữ liệu
                $("#cmdTaiDL").click(function () {
                    if ($("#cboCanBo").val() === '000000') {
                        alert("Vui lòng chọn cán bộ cần gán dữ liệu.");
                    } else {
                        $('#viewData').html('<img src="img/loading.gif"/>');
                        var url, sdata;
                        url = "viewdata.action";
                        sdata = jQuery("#HDTK_FrmMain").serialize() + "&cSeach=" + $("#cSeach").val();
                        ;
                        $.ajax({
                            type: "GET",
                            url: url,
                            data: sdata,
                            success: function (data) {
                                $("#viewData").html(data);
                                if ($("#chkDate").val() === '200') {
                                    $("#cmdLuuDL").css("display", "inline-block");
                                    $("input.chkChonSh").removeAttr("disabled");
                                    $("#cmdLuuDL_tmp").click();
                                } else {
                                    $("#cmdLuuDL").css("display", "none");
                                    $("input.chkChonSh").attr("disabled", true);
                                }
                            },
                            error: function (request) {
                                $("#viewData").html(request.responseText);
                            }
                        });
                    }
                    ;
                });

                //Lưu dữ liệu
                $("#cmdLuuDL").click(function () {
                    var chk = confirm("Bạn có chắc chắn muốn lưu dữ liệu");
                    if (chk) {
                        if ($('#txtChitieu').val() <= 0) {
                            alert('Số tiền giao chỉ tiêu phải > 0');
                        } else {
                            $("#cmdLuuDL").html("Đang thực hiện...");
                            $("#cmdLuuDL").prop('disabled', true);
                            var url, sdata;
                            url = "savedata.action";
                            sdata = jQuery("#HDTK_FrmMain").serialize();
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: sdata,
                                success: function (data) {
                                    if (data === "200") {
                                        $("#cmdLuuDL").prop('disabled', false);
                                        $("#cmdLuuDL").html("Lưu dữ liệu");
                                        alert("Lưu dữ liệu thành công.");
                                        $("#cmdTaiDL").click();
                                    } else if (data === "404")
                                    {
                                        $("#cmdLuuDL").prop('disabled', false);
                                        $("#cmdLuuDL").html("Lưu dữ liệu");
                                        alert("Dữ liệu quá lớn, vui lòng nhập thông tin sổ vào ô tra cứu, tìm kiếm lại để lưu!");
                                        $("#cmdTaiDL").click();
                                    } else {
                                        alert("Lỗi khi thực hiện lưu dữ liệu.");
                                        $("#cmdLuuDL").html("Lưu dữ liệu");
                                        $("#cmdLuuDL").prop('disabled', false);
                                    }
                                },
                                error: function (request) {
                                    $("#cmdLuuDL").prop('disabled', false);
                                    $("#cmdLuuDL").html("Lưu dữ liệu");
                                    $("#viewData").html(request.responseText);
                                }
                            });
                        }
                        ;
                    }
                    ;
                });

                //Lưu dữ liệu2
                $("#cmdLuuDL_tmp").click(function () {
                    $("#cmdLuuDL_tmp").html("Đang thực hiện...");
                    $("#cmdLuuDL_tmp").prop('disabled', true);
                    var url, sdata;
                    url = "savedata.action";
                    sdata = jQuery("#HDTK_FrmMain").serialize();
                    $.ajax({
                        type: "POST",
                        url: url,
                        data: sdata,
                        success: function (data) {
                            if (data === "200") {
                                $("#cmdLuuDL_tmp").prop('disabled', false);
                                $("#cmdLuuDL_tmp").html("Lưu dữ liệu");
                            } else {
                                $("#cmdLuuDL_tmp").html("Lưu dữ liệu");
                                $("#cmdLuuDL_tmp").prop('disabled', false);
                            }
                        },
                        error: function (request) {
                            $("#cmdLuuDL_tmp").prop('disabled', false);
                            $("#cmdLuuDL_tmp").html("Lưu dữ liệu");
                            $("#viewData").html(request.responseText);
                        }
                    });

                });

                //Tải dữ liệu2
                $("#cmdTaiDL_tmp").click(function () {
                    if ($("#cboCanBo").val() === '000000') {
                        alert("Vui lòng chọn cán bộ cần gán dữ liệu.");
                    } else {
                        $('#viewData').html('<img src="img/loading.gif"/>');
                        var url, sdata;
                        url = "viewdata.action";
                        sdata = jQuery("#HDTK_FrmMain").serialize() + "&cSeach=" + $("#cSeach").val();
                        ;
                        $.ajax({
                            type: "GET",
                            url: url,
                            data: sdata,
                            success: function (data) {
                                $("#viewData").html(data);
                                if ($("#chkDate").val() === '200') {
                                    $("#cmdLuuDL").css("display", "inline-block");
                                    $("input.chkChonSh").removeAttr("disabled");
                                } else {
                                    $("#cmdLuuDL").css("display", "none");
                                    $("input.chkChonSh").attr("disabled", true);
                                }
                            },
                            error: function (request) {
                                $("#viewData").html(request.responseText);
                            }
                        });
                    }
                    ;
                });
                
                function checkAll() {
                    var ChkAll = document.getElementById("chkChon").length();
                    alert(ChkAll);
                }
                ;
            });

            $('.number').number(true, 0);

            $("#cboCanBo").change(function () {
                $("#cmdLuuDL").css("display", "none");
            });

            // Lấy ngày hiện tại
            var today = new Date();

// Trừ đi 1 ngày
            var yesterday = new Date(today);
            yesterday.setDate(today.getDate() - 1);

// Format ngày thành yyyy-MM-dd
            var formattedDate = yesterday.toISOString().slice(0, 10);

// Gán giá trị mới vào trường input
            document.getElementById('dtNgaybc').value = formattedDate;

        </script>
    </body>
</html>