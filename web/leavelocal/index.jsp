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
            #ui-datepicker-div{
                z-index: 99 !important;
            }
            .ui-datepicker-trigger{
                height: auto !important;
            }
        </style>
    </head>
    <body>
        <div style="margin: 5px;">
            <h3>DANH SÁCH HỘ VAY CHUYỂN KHỎI ĐỊA BÀN</h3>
            <s:form name="frmdata" id="frmdata" theme="simple">
                <fieldset style="display: flex; align-content: space-between;justify-content: space-between;">
                    <legend><b>Tìm kiếm dữ liệu</b></legend>
                    <div>
                        Đơn vị:
                        <select name="txtsMadv" id="txtsMadv">
                            <s:iterator value="lstDonvi">
                                <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>
                            </s:iterator>
                        </select>
                        Ngày báo cáo: <input type="text" name="txtNgayBc" id="txtNgaybc" readonly="readonly"/>
                        Mã khách hàng: <input type="text" name="txtMakh" id="txtMakh" placeholder="Nhập mã khách hàng" value="">
                    </div>
                    <div>
                        <input type="button" id="idSearch" value="Tìm kiếm" style="background-color: gray; color: white; height: 25px; padding: 0px 20px 0px 20px;">
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <input type="button" id="idSave" value="Lưu số liệu" style="background-color: orange; color: white;height: 25px;padding: 0px 20px 0px 20px;">
                            <input type="button" id="idSend" value="Gửi số liệu" style="background-color: green; color: white;height: 25px;padding: 0px 20px 0px 20px;">
                        </s:if>
                    </div>
                </fieldset>
                <div id="viewData" style="margin-top: 5px;"></div>                    
            </s:form>
        </div>
        <script>
            $(function () {
                $("#txtNgaybc").datepicker({dateFormat: 'dd/mm/yy', showOn: "button",
                    buttonImage: "img/icon-ui_datepicker.png",
                    buttonImageOnly: true,
                    dateFormat: 'dd/mm/yy',
                    showButtonPanel: true,
                    buttonText: "icono",
                    changeMonth: true,
                    changeYear: true,
                    // showOn: "both"}).val('31/10/2022');
                    showOn: "both"}).val(new Date(new Date().getFullYear(), new Date().getMonth(), 1).toLocaleDateString("zh-HK", {year: 'numeric', month: '2-digit', day: '2-digit'}));
            });

            //Tải dữ liệu
            $("#idSearch").click(function () {
                var url, sdata;
                $("#txtNghiepVu").val("TRACUU");
                url = "getLeaveLocal.action";
                sdata = jQuery("#frmdata").serialize();
                $("#viewData").html('<img src="img/loading.gif"/>');
                btnDisabled(1);
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        $("#viewData").html(data);
                    },
                    complete: function () {
                        btnDisabled(0);
                    },
                    error: function (request) {
                        console.log(request);
                        alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                    }
                });
            });


            //Lưu dữ liệu
            $("#idSave").click(function () {

                let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                if (aCheck) {
                    var url, sdata;
                    url = "saveLeaveLocal.action";
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
                                $("#idSearch").trigger("click");
                            } else {
                                alert("Lỗi: Lưu dữ liệu.");
                            }
                        },
                        complete: function () {
                            btnDisabled(0);
                        },
                        error: function (request) {
                            alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                        }
                    });
                }
            });

            //Gửi dữ liệu
            $("#idSend").click(function () {
                let aCheck = confirm("Bạn chắc chắn muốn gửi số liệu báo cáo ?");
                if (aCheck) {
                    var url, sdata;
                    url = "sendLeaveLocal.action";
                    sdata = jQuery("#frmdata").serialize();
                    $("#viewData").html('<img src="img/loading.gif"/>');
                    btnDisabled(1);
                    $.ajax({
                        type: "POST",
                        url: url,
                        data: sdata,
                        success: function (data) {
                            if (data === "200") {
                                alert("Thành công: Gửi dữ liệu.");
                                $("#viewData").html('<h2 style="color:red;">Gửi dữ liệu thành công!</h2>');
                            } else {
                                alert("Lỗi: Gửi dữ liệu.");
                            }
                        },
                        complete: function () {
                            btnDisabled(0);
                        },
                        error: function (request) {
                            alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                        }
                    });
                }
            });

            function btnDisabled(status) {
                if (status == 1) {
                    $("#idSearch").prop('disabled', true);
                    $("#idSend").prop('disabled', true);
                    $("#idSave").prop('disabled', true);
                } else {
                    $("#idSearch").prop('disabled', false);
                    $("#idSend").prop('disabled', false);
                    $("#idSave").prop('disabled', false);
                }
                ;
            }
        </script>
    </body>
</html>
