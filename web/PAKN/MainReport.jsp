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
                        <td colspan="2" style="font-size: 14px; font-weight: bold; padding-bottom: 10px; text-transform: uppercase; color: red;" id="tenbc"></td>                    
                    </tr>
                    <tr>
                        <td>
                            <b style="padding-right: 3px;">Ngày báo cáo</b><input type="text" name="txtNgaybc" id="datepicker" readonly="readonly"/>    
                        </td>
                        <td style="text-align: right;">
                            <div style="display: inline-flex;">
                                <input type="button" id="idLoad" value="Xem dữ liệu" style="width:122px;height:25px;color: red; font-size: 12px;"/>
                                &nbsp;&nbsp;
                                <input type="button" id="idSave" value="Cập nhật" style="width:122px;height:25px;color: red; font-size: 12px; display: none;"/>
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
                $("#datepicker").datepicker({dateFormat: 'dd/mm/yy', showOn: "button",
                    buttonImage: "img/icon-ui_datepicker.png",
                    buttonImageOnly: true,
                    buttonText: "icono",
                    showOn: "both"}).val(new Date().toLocaleDateString("zh-HK", {year: 'numeric', month: '2-digit', day: '2-digit'}));
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
                    },
                    error: function (request) {
                        alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                    }
                });
            });
            //Lưu dữ liệu
            $("#idSave").click(function () {
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
                            $("#idLoad").trigger( "click" );
                            alert("Thành công: Lưu dữ liệu.");
                        } else {
                            alert("Lỗi: Lưu dữ liệu.");
                        }
                    },
                    error: function (request) {
                        alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                    }
                });
            });
        </script>
    </body>
</html>
