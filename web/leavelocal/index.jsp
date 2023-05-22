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

            .fixWidthDiv {
                /*                width: 1200px;*/
                /*                margin-top: 5px; 
                                margin-bottom: 5px;*/
                display: inline-block; 
                min-width: 100%;
            }


        </style>
    </head>
    <body>
        <div style="margin: 5px;">
            <h3>DANH SÁCH HỘ VAY CHUYỂN KHỎI ĐỊA BÀN</h3>
            <s:form name="frmdata" id="frmdata" theme="simple">
                <input type="hidden" name="gradeAuthor1" id="gradeAuthor1" value="<s:property value='gradeAuthor1'/>">
                <fieldset style="display: flex; align-content: space-between;justify-content: space-between;">
                    <!--align-content: space-between;justify-content: space-between;-->
                    <legend><b>Tìm kiếm dữ liệu</b></legend>
                    <!--Check cấp phê duyệt tại PGD-->
                    <s:if test="gradeAuthor1.equalsIgnoreCase('2')">
                        <div>
                            Đơn vị:
                            <select name="txtsMadv" id="txtsMadv">
                                <s:iterator value="lstDonvi">
                                    <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>
                                </s:iterator>
                            </select>                        
                            Mã khách hàng: <input type="text" name="txtMakh" id="txtMakh" placeholder="Nhập mã khách hàng" value="">                        
                            Ngày nhập: từ <input type="text" name="txtFromDate" id="txtFromDate" readonly="readonly"/>
                            đến <input type="text" name="txtToDate" id="txtToDate" readonly="readonly"/>
                            <input type="hidden" name="txtNgayBc" id="txtNgayBc" readonly="readonly" value="31/12/2050"/>
                            <s:if test="gradeAuthor1.equalsIgnoreCase('2')">
                                Loại phê duyệt:
                                            
                                                <select name="typeAuth" id="typeAuth">                                                    
                                                    <option value="1">Xư lý nợ</option>                                                    
                                                    <option value="3">Xóa</option>        
                                                </select>
                                             
                            </s:if>
                        </div>
                        <div>
                            <input type="button" id="idSearch" value="Tìm kiếm" style="height: 25px; padding: 0px 20px 0px 20px;">
                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <input type="button" id="idSave" value="Phê duyệt" style="height: 25px;padding: 0px 20px 0px 20px;">
<!--                                <input type="button" id="idSave" value="Lưu số liệu" style="height: 25px;padding: 0px 20px 0px 20px;" disabled="true">                            
                                <input type="button" id="idDelete" value="Đề nghị xóa" style="color: red;height: 25px;padding: 0px 20px 0px 20px;" disabled="true">

                                <input type="button" id="idUpload" value="Upload excel" onclick="callDirectLink('khvn_open_upload_qt_kh?');" style="height: 25px;padding: 0px 20px 0px 20px;">
                                <input type="button" id="idFetch" value="Tải dữ liệu upload" style="height: 25px;padding: 0px 20px 0px 20px;">
                                <input type="button" id="idSend" value="Gửi số liệu" style="height: 25px;padding: 0px 20px 0px 20px;" disabled="true">-->
                            </s:if>
                        </div>
                    </s:if>
                    <s:else>
                        <div>
                            Đơn vị:
                            <select name="txtsMadv" id="txtsMadv">
                                <s:iterator value="lstDonvi">
                                    <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>
                                </s:iterator>
                            </select>                        
                            Mã khách hàng: <input type="text" name="txtMakh" id="txtMakh" placeholder="Nhập mã khách hàng" value="">                        
                            Ngày nhập: từ <input type="text" name="txtFromDate" id="txtFromDate" readonly="readonly"/>
                            đến <input type="text" name="txtToDate" id="txtToDate" readonly="readonly"/>
                            <input type="hidden" name="txtNgayBc" id="txtNgayBc" readonly="readonly" value="31/12/2050"/>
                        </div>
                        <div>
                            <input type="button" id="idSearch" value="Tìm kiếm" style="height: 25px; padding: 0px 20px 0px 20px;">
                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <input type="button" id="idSave" value="Lưu số liệu" style="height: 25px;padding: 0px 20px 0px 20px;" disabled="true">                            
                                <input type="button" id="idDelete" value="Đề nghị xóa" style="color: red;height: 25px;padding: 0px 20px 0px 20px;" disabled="true">

                                <input type="button" id="idUpload" value="Upload excel" onclick="callDirectLink('khvn_open_upload_qt_kh?');" style="height: 25px;padding: 0px 20px 0px 20px;">
                                <input type="button" id="idFetch" value="Tải dữ liệu upload" style="height: 25px;padding: 0px 20px 0px 20px;">
                                <input type="button" id="idSend" value="Gửi số liệu" style="height: 25px;padding: 0px 20px 0px 20px;" disabled="true">
                            </s:if>
                        </div>
                    </s:else>

                </fieldset>

                <div>
                    <div id="viewData" ></div>
                </div>


            </s:form>
        </div>
        <script>
            $(function () {
                $("#txtFromDate").datepicker(
                        {
                            dateFormat: 'dd/mm/yy',
                            showOn: "button",
                            buttonImage: "img/icon-ui_datepicker.png",
                            buttonImageOnly: true,
                            // dateFormat: 'dd/mm/yy',
                            showButtonPanel: true,
                            buttonText: "icono",
                            changeMonth: true,
                            changeYear: true
                        }).val('31/12/2022');
            });

            $(function () {
                $("#txtToDate").datepicker(
                        {
                            dateFormat: 'dd/mm/yy',
                            showOn: "button",
                            buttonImage: "img/icon-ui_datepicker.png",
                            buttonImageOnly: true,
                            // dateFormat: 'dd/mm/yy',
                            showButtonPanel: true,
                            buttonText: "icono",
                            changeMonth: true,
                            changeYear: true
                        }).val('31/12/2050');
            });

//            $(function () {                
//                $("#txtNgayBc").datepicker(
//                {
//                    dateFormat: 'dd/mm/yy', 
//                    showOn: "button",
//                    buttonImage: "img/icon-ui_datepicker.png",
//                    buttonImageOnly: true,
//                   // dateFormat: 'dd/mm/yy',
//                    showButtonPanel: true,
//                    buttonText: "icono",
//                    changeMonth: true,
//                    changeYear: true                    
//                }).val('31/12/2050');
//            });

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
                        $("#idSend").prop('disabled', true);
                        $("#idSave").prop('disabled', false);
                        $("#idDelete").prop('disabled', false);
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

            //Fetch excel upload data
            $("#idFetch").click(function () {
                var url, sdata;
                $("#txtNghiepVu").val("TRACUU");
                url = "fetchUploadExcelLeaveLocal.action";
                sdata = jQuery("#frmdata").serialize();
                $("#viewData").html('<img src="img/loading.gif"/>');
                btnDisabled(1);
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        $("#viewData").html(data);
                        $("#idSend").prop('disabled', false);

                        $("#idSave").prop('disabled', true);
                        $("#idDelete").prop('disabled', true);
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
                let checkedCount = countCheckedItem();
                if (checkedCount === 0) {
                    alert('Bạn chưa chọn bản ghi để lưu!');
                } else {
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
                }

            });

            //Gửi dữ liệu
            $("#idSend").click(function () {
                let checkedCount = countCheckedItem();
                if (checkedCount === 0 || checkedCount > 200) {
                    alert('Bạn chưa chọn bản ghi để gửi hoặc gửi một lần tối đa 200 bản ghi!');
                } else {
                    let aCheck = confirm("Gửi số liệu chỉ áp dụng cho khách hàng upload excel. Bạn chắc chắn muốn gửi số liệu ?");
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
                }

            });


            $("#idDelete").click(function () {
                let checkedCount = countCheckedItem();
                if (checkedCount === 0 || checkedCount > 1) {
                    alert('Bạn chưa chọn bản ghi để xóa hoặc mỗi lần bạn chỉ được phép xóa tối đa 1 bản ghi!');
                } else {
                    let aCheck = confirm("Bạn chắc chắn muốn xóa dữ liệu ?");
                    if (aCheck) {
                        var url, sdata;
                        url = "suggestDeteleLocal.action";
                        sdata = jQuery("#frmdata").serialize();
                        $("#viewData").html('<img src="img/loading.gif"/>');
                        btnDisabled(1);
                        $.ajax({
                            type: "POST",
                            url: url,
                            data: sdata,
                            success: function (data) {
                                if (data === "200") {
                                    alert("Thành công: Xóa dữ liệu.");
                                    $("#idSearch").trigger("click");
                                } else {
                                    alert("Lỗi: Xóa dữ liệu.");
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
                }

            });


            function callDirectLink(link) {
                //var ht = screen.availHeight / 6;
                //var wt = screen.availWidth / 5;
                PopupCenter(link, 'Upload excel', 800, 400);

            }

            function PopupCenter(pageURL, title, w, h) {
                var left = (screen.width / 2) - (w / 2);
                var top = (screen.height / 2) - (h / 2);
                var targetWin = window.open(pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
                return targetWin;
            }

            function countCheckedItem() {
                let counter = 0;
                $('.myCheckBox').each(function () {
                    if (this.checked === true)
                        counter++;
                });
                return counter;
            }


            function btnDisabled(status) {
                if (status === 1) {
                    $("#idSearch").prop('disabled', true);
//                    $("#idSend").prop('disabled', true);
//                    $("#idSave").prop('disabled', true);
//                    $("#idDelete").prop('disabled', true);
                    $("#idUpload").prop('disabled', true);
                    $("#idFetch").prop('disabled', true);
                } else {
                    $("#idSearch").prop('disabled', false);
//                    $("#idSend").prop('disabled', false);
//                    $("#idSave").prop('disabled', false);
//                    $("#idDelete").prop('disabled', false);
                    $("#idUpload").prop('disabled', false);
                    $("#idFetch").prop('disabled', false);
                }
                ;
            }
        </script>
    </body>
</html>
