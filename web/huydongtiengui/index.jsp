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
        <link rel="stylesheet" href="js/jquery.dataTables.min.css">
        <script src="js/jquery.dataTables.min.js"></script>
    </head>
    <body>
        <form id="HDTK_FrmMain" name="HDTK_FrmMain">
            <div style="margin: 12px;">
                <div class="clsHeader"><h1>KẾT QUẢ HUY ĐỘNG TIẾT KIỆM</h1></div>
                <div class="clsTitle">
                    <b>Ngày báo cáo:</b> <input type="date" id="dtNgaybc" name="dtNgaybc" value="2020-06-30">
                    <b>Cán bộ:</b> 
                    <select id="cboCanBo" name="cboCanBo">
                        <option value="000000">----Chọn cán bộ----</option>
                        <s:iterator value="lstCanBo">
                            <option value='<s:property value="MaCB"/>'><s:property value="TenCB"/></option>
                        </s:iterator>
                    </select>
                    <b>Chỉ tiêu được giao:</b> <input type="text" style="text-align: right;" id="txtChitieu" name="txtChitieu" value=0 class="number">
                    <input type="button" value="Tải dữ liệu" id="cmdTaiDL" name="cmdTaiDL">
                    <input type="button" value="Lưu dữ liệu" id="cmdLuuDL" name="cmdLuuDL">
                </div>
                <div id="viewData"></div>
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
                        sdata = jQuery("#HDTK_FrmMain").serialize();
                        $.ajax({
                            type: "GET",
                            url: url,
                            data: sdata,
                            success: function (data) {
                                $("#viewData").html(data);
                                $("#cmdLuuDL").css("display", "inline-block");
                                /* khoa tạm đã
                                if ($("#chkDate").val() === '200') {
                                    $("#cmdLuuDL").css("display", "inline-block");
                                    $("input.chkChonSh").removeAttr("disabled");
                                } else {
                                    $("#cmdLuuDL").css("display", "none");
                                    $("input.chkChonSh").attr("disabled", true);
                                }
                                */
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
                            var url, sdata;
                            url = "savedata.action";
                            sdata = jQuery("#HDTK_FrmMain").serialize();
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: sdata,
                                success: function (data) {
                                    if (data === "200") {
                                        alert("Lưu dữ liệu thành công.");
                                        $("#cmdTaiDL").click();
                                    } else {
                                        alert("Lỗi khi thực hiện lưu dữ liệu.");
                                    }
                                },
                                error: function (request) {
                                    $("#viewData").html(request.responseText);
                                }
                            });
                        }
                        ;
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

        </script>
    </body>
</html>