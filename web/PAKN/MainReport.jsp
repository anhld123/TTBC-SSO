<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" href="js/3.6.0/jquery-ui.css">
        <script src="js/3.6.0/jquery-ui.js"></script>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <style>
            *{
                font-family: Tahoma;
                font-size: 12px;
            }
            #tableKtnb {
                border-collapse: collapse;
                width: 98%;
            }

            #tableKtnb td, #tableKtnb th {
                border: 1px solid #ddd;
            }

            #tableKtnb th {
                padding: 5px;
                text-align: center;
                background-color: lightslategray;
                color: white;
            }
            #tableKtnb td{
                padding: 5px;
            }
            .cssItem{
                width: 75px;
                border: none;
                background-color: transparent;
                outline-style: none;
                text-align: right;
            }
            .cssTong{
                background-color: lightslategray;
                color: white;
                text-align: right;
                padding: 5px;
                font-weight: bold;
            }
            .cssTEN{
                text-align: left;
            }
            .cssD3{
                width: 98%;
            }
        </style>
    </head>
    <body>
        <s:form name="frmdata" id="frmdata" theme="simple">
            <input type="hidden" value="" name="txtMaBc" id="txtMaBc">
            <div style="padding: 3px 3px 3px 3px;">
                <table border="0" cellspacing="0" cellpading="0" height="100%" style="width: 100%;">
                    <tr>
                        <td colspan="2" style="font-size: 14px; font-weight: bold; padding-bottom: 10px; text-transform: uppercase; color: red; text-align: center;" id="tenbc">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div style="display: inline-flex;">
                                <div>
                                    <s:if test="type.equalsIgnoreCase('1')">
                                        <label for="sInsert">Nhập liệu: </label><input type="radio" value="sInsert" id="sInsert" name="group1">
                                    </s:if>
                                    <label for="sQuery">Truy vấn: </label>
                                    <input type="radio" value="sQuery" id="sQuery" name="group1" checked="checked">
                                </div>
                                <div style="margin-left: 10px; display: none;" class="sNhapLieu">
                                    <b style="padding-right: 3px;">Ngày báo cáo</b><input type="text" name="txtNgaybc" id="txtNgaybc" readonly="readonly"/>
                                </div>
                                <div style="margin-left: 10px;" class="sTruyVan">
                                    <b style="padding-right: 3px;">Từ ngày</b><input type="text" name="txtTuNgay" id="txtTungay" readonly="readonly"/>&nbsp;&nbsp;
                                    <b style="padding-right: 3px;">Đến ngày</b><input type="text" name="txtDenNgay" id="txtDenngay" readonly="readonly"/>&nbsp;&nbsp;
                                    <b style="padding-right: 3px;">Tổng hợp</b><input type="checkbox" name="chkTongHop" id="chkTongHop" checked/>&nbsp;&nbsp;
                                </div>
                            </div>
                        </td>
                        <td style="text-align: right">
                            <div class="sTruyVan">
                                <input type="button" id="idQuery" value="Tra cứu" style="width:122px;height:25px;color: red; font-size: 12px;"/>
                            </div>
                            <div style="display: none;" class="sNhapLieu">
                                <div style="display: inline-flex;">
                                    <input type="button" id="idLoad" value="Tải dữ liệu" style="width:122px;height:25px;color: red; font-size: 12px;"/>
                                    &nbsp;&nbsp;
                                    <input type="button" id="idSave" value="Cập nhật" style="width:122px;height:25px;color: red; font-size: 12px; display: none;"/>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
                <hr>
            </div>
            <div style="overflow: scroll; width: 100%; height: 83vh;" id="viewData"></div>
        </s:form>
        <script>
            //Lấy tham số lần đầu
            const queryString = window.location.search;
            const urlParams = new URLSearchParams(queryString);
            $("#tenbc").text(urlParams.get('textlink'));
            $("#txtMaBc").val(urlParams.get('action'));


            $(function () {
                $("#txtTungay").datepicker({dateFormat: 'dd/mm/yy', showOn: "button",
                    buttonImage: "img/icon-ui_datepicker.png",
                    buttonImageOnly: true,
                    buttonText: "icono",
                    showOn: "both"}).val(new Date(new Date().getFullYear(), new Date().getMonth(), 1).toLocaleDateString("zh-HK", {year: 'numeric', month: '2-digit', day: '2-digit'}));
            });

            $(function () {
                $("#txtDenngay").datepicker({dateFormat: 'dd/mm/yy', showOn: "button",
                    buttonImage: "img/icon-ui_datepicker.png",
                    buttonImageOnly: true,
                    buttonText: "icono",
                    showOn: "both"}).val(new Date(new Date().getFullYear(), new Date().getMonth() + 1, 0).toLocaleDateString("zh-HK", {year: 'numeric', month: '2-digit', day: '2-digit'}));
            });

            $(function () {
                $("#txtNgaybc").datepicker({dateFormat: 'dd/mm/yy', showOn: "button",
                    buttonImage: "img/icon-ui_datepicker.png",
                    buttonImageOnly: true,
                    buttonText: "icono",
                    showOn: "both"}).val(new Date(new Date().getFullYear(), new Date().getMonth() + 1, 0).toLocaleDateString("zh-HK", {year: 'numeric', month: '2-digit', day: '2-digit'}));
            });

            //Truy vấn số liệu
            $("#idQuery").click(function () {
                var url, sdata;
                url = "queryDataByTem.action";
                sdata = jQuery("#frmdata").serialize();
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        $("#viewData").html(data);
                        funcgCheck();
                        $(".cssItem").prop("readonly","true");
                    },
                    error: function (request) {
                        alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                    }
                });
            });

            //Tải dữ liệu
            $("#idLoad").click(function () {
                var url, sdata;
                url = "loadDataByTem.action";
                sdata = jQuery("#frmdata").serialize();
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        $("#viewData").html(data);
                        $("#idSave").css('display', 'block');
                        funcgCheck();
                    },
                    error: function (request) {
                        alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                    }
                });
            });

            //Lưu dữ liệu
            $("#idSave").click(function () {
                let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                if (aCheck) {
                    var url, sdata;
                    $(".cssD40").val($("#txtGhiChu").val());
                    url = "saveDataByTem.action";
                    sdata = jQuery("#frmdata").serialize();
                    $.ajax({
                        type: "POST",
                        url: url,
                        data: sdata,
                        success: function (data) {
                            if (data === "200") {
                                $("#idLoad").trigger("click");
                                alert("Thành công: Lưu dữ liệu.");
                            } else {
                                alert("Lỗi: Lưu dữ liệu.");
                            }
                        },
                        error: function (request) {
                            alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                        }
                    });
                }
            });
            $('input[type=radio][name=group1]').change(function () {
                $("#viewData").html("");
                if (this.value == 'sInsert') {
                    $(".sTruyVan").css("display", "none");
                    $(".sNhapLieu").css("display", "block");
                } else {
                    $(".sNhapLieu").css("display", "none");
                    $(".sTruyVan").css("display", "block");
                }
            });
            function funcgCheck() {
                let gCheck = $('input[name="group1"]:checked').val();
                if (gCheck === 'sQuery') {
                    $(".ShowNhaplieu").css("display", "none");
                    $(".ShowGhichu").css("display", "");
                } else {
                    $(".ShowGhichu").css("display", "none");
                    $(".ShowNhaplieu").css("display", "");
                }
            }
        </script>
    </body>
</html>
