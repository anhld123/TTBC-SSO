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
        </style>
    </head>
    <body>
        <div style="margin: 5px;">
            <h3>DANH SÁCH HỘ VAY CHUYỂN KHỎI ĐỊA BÀN</h3>
            <s:form name="frmdata" id="frmdata" theme="simple">
                <fieldset>
                    <legend><b>Tìm kiếm dữ liệu</b></legend>
                    Ngày báo cáo<span style="color: red;">*</span>: <input type="text" name="txtNgayBc" id="txtNgaybc" readonly="readonly"/>
                    Mã khách hàng: <input type="text" name="txtMakh" id="txtMakh" placeholder="Nhập mã khách hàng" value="">
                    <input type="hidden" name="txtNghiepVu" id="txtNghiepVu">
                </fieldset>
                <div style="width: 100%; margin: 3px; text-align: right;display: flex; justify-content: space-between; justify-items: center;">
                    <div>
                        <span style="color: red;">Thêm thành viên: Nhấn chọn tên khách hàng để thêm thành viên</span>
                        <img id="loadingImage" src='img/loading.gif' border='0' style="display: none;" >
                    </div>
                    <div style="margin-right: 5px;">
                        <input type="button" id="idSearch" value="Tra cứu số liệu đã nhập" style="background-color: gray; color: white; height: 25px;">
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <input type="button" id="idFind" value="Tìm => Thêm mới" style="background-color: orange; color: white;height: 25px;">
                            <input type="button" id="idSave" value="Cập nhật" style="background-color: green; color: white;height: 25px;">
                        </s:if>
                    </div>

                </div>
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
                    showOn: "both"}).val(new Date(new Date().getFullYear(), new Date().getMonth(), 1).toLocaleDateString("zh-HK", {year: 'numeric', month: '2-digit', day: '2-digit'}));
            });

            //Tải dữ liệu
            $("#idSearch").click(function () {
                var url, sdata;
                $("#txtNghiepVu").val("TRACUU");
                url = "getLeaveLocal.action";
                sdata = jQuery("#frmdata").serialize();
                $("#loadingImage").css('display', 'block');
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        $("#viewData").html(data);
                    },
                    error: function (request) {
                        alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                    }
                });
            });

            //Tải dữ liệu
            $("#idFind").click(function () {
                if ($("#txtMakh").val().trim() === "") {
                    alert("Vui lòng cung cấp Mã khách hàng.");
                } else {
                    var url, sdata;
                    $("#txtNghiepVu").val("XXXXXX");
                    url = "getLeaveLocal.action";
                    sdata = jQuery("#frmdata").serialize();
                    $("#loadingImage").css('display', 'block');
                    $.ajax({
                        type: "POST",
                        url: url,
                        data: sdata,
                        success: function (data) {
                            $("#viewData").html(data);
                        },
                        error: function (request) {
                            alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                        }
                    });
                }
            });

            //Tìm dữ liệu
            $("#idSave").click(function () {
                let maxIdx = document.getElementById("customers").rows.length;
                for (let i = 0; i <= (maxIdx-2); i++) {
                    let valCheck = document.getElementById("lstData" + i.toString()).value;
                    if (valCheck=="01" || valCheck=="02"){
                        let valNote = document.getElementById("lstData[" + i.toString() + "].D23").value;
                        if(valNote == ""){
                            alert("Bạn cần nhập nôi dung trường thông tin và đơn vị chuyển đến");
                            return false;
                        }
                    };
                }
                let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                if (aCheck) {
                    var url, sdata;
                    url = "saveLeaveLocal.action";
                    sdata = jQuery("#frmdata").serialize();
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
                        error: function (request) {
                            alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                        }
                    });
                }
            });
        </script>
    </body>
</html>
