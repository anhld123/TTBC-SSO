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
                            Từ ngày:  <input type="text" name="txtFromDate" id="txtFromDate" readonly="readonly"/>
                            đến: <input type="text" name="txtToDate" id="txtToDate" readonly="readonly"/>
                            <input type="hidden" name="txtNgayBc" id="txtNgayBc" readonly="readonly" value="31/12/2050"/>                          
                            Loại phê duyệt: <select style="width: auto;" name="typeAuth" id="typeAuth">                                                    
                                <option value="3">Xóa</option>                                                    
                                <option value="4">Đề nghị cung cấp thông tin</option>
                                <option value="5">Đề nghị hỗ trợ</option></select>                                         
                        </div>
                        <div>
                            <input type="button" id="idSearch" value="Tìm kiếm" style="height: 25px; padding: 0px 20px 0px 20px;">
                            <!--<input type="button" id="idPheduyet" value="Phê duyệt" style="height: 25px;padding: 0px 20px 0px 20px;" >-->
                            <input type="button" id="idSave" value="Phê duyệt" style="height: 25px;padding: 0px 20px 0px 20px;" >
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
                            Mã KH/ CMND/CCCD: <input type="text" name="txtMakh" id="txtMakh" placeholder="Nhập mã KH/ CMND/CCCD" value="">                        
                            Từ ngày:  <input type="text" name="txtFromDate" id="txtFromDate" readonly="readonly" style="width: 80px"/>
                            đến: <input type="text" name="txtToDate" id="txtToDate" readonly="readonly" style="width: 80px">
                            <input type="hidden" name="txtNgayBc" id="txtNgayBc" readonly="readonly" value="31/12/2050"/>
                        </div>
                        <div>
                            <s:if test="Grade.equalsIgnoreCase('3')">
                                <font style="color: red"> Bạn đã chốt số liệu lần cung cấp thông tin lần 0
                            </s:if>
                            <input type="button" id="idSearch" value="Tìm kiếm" style="height: 25px; padding: 0px 20px 0px 20px;">
                            <s:if test="Grade.equalsIgnoreCase('3')">
                                <input type="button" id="idSave" value="Chốt cung cấp TT" style="height: 25px;padding: 0px 20px 0px 20px;">   
                            </s:if>
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
            function changValSeclect() {
                if ($("#typeAuth").val() == 1) {
                    $("#idPheduyet").val("Phê duyệt").prop('disabled', true);
                } else {
                    $("#idPheduyet").val("Phê duyệt").prop('disabled', false).click(function () {
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
                }
            }
            changValSeclect();
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
                        var table = document.getElementById("subTable");
                        var rowcount = table.rows.length;
                        var isValid = true; // Tạo biến để kiểm tra tính hợp lệ của dữ liệu

                        for (var i = 0; i < rowcount; i++) {
                            try {
                                var check_box = document.getElementById('STT_1' + i).checked; // bắt check
                                //Bắt thời điểm đi
                                var lstData_D21 = document.getElementById('lstData_D21' + i).value;


                                if (lstData_D21.length < 5 && check_box !== false) {
                                    alert('Vui lòng nhập thông tin cột 12.');
                                    document.getElementById("lstData_D21" + i).style.backgroundColor = "#EEAFA6";
                                    return;
                                }

                                // bắt nhập cột 16

//                                alert(check_box);
                                var lstData_D22 = document.getElementById('lstData_D22' + i).value;
                                var lstData42 = document.getElementById('lstData42' + i).value;
                                var lstSubData31 = document.getElementById('lstSubData31' + i).value;
//                                alert(lstData42);
                                var lstDataD27 = document.getElementById('lstDataD27' + i).value;
                                if (lstData42 === '0' && lstDataD27 === "" && lstData_D22 === '02' && lstSubData31 === '1' && check_box !== false) {
                                    alert("Vui lòng nhập dữ liệu cho cột 16 trước khi lưu");
                                    document.getElementById("lstDataD27" + i).style.backgroundColor = "#EEAFA6";
                                    isValid = false;
                                    break;
                                }

                                // bắt check 19                    
                                var check_var19 = document.getElementById('countDisplay' + i).innerText;
                                var check_var20 = document.getElementById('countDisplay1' + i).innerText;
                                var check_var21 = document.getElementById('countDisplay2' + i).innerText;
//                                alert(check_var19);
                                if (check_var19 === "0" && lstData42 === '0' && lstData_D22 === '02' && lstSubData31 === '1' && check_box !== false)
                                {
                                    alert("Vui lòng rà soát lại cột 19 ( Chi nhánh hộ vay chuyển đến ) trước khi lưu");
                                    document.getElementById("lstData_D30" + i).style.backgroundColor = "#EEAFA6";
                                    isValid = false;
                                    break;
                                }
                                // bắt check 20
                                if (check_var20 === "0" && lstData_D22 === '02' && lstSubData31 === '1' && lstData42 === '0' && check_box !== false)
                                {
                                    alert("Vui lòng rà soát lại cột 20 ( PGD hộ vay chuyển đến ) trước khi lưu");
                                    document.getElementById("lstData_D32" + i).style.backgroundColor = "#EEAFA6";
                                    isValid = false;
                                    break;
                                }

                                // bắt check 21
                                if (check_var21 === "0" && lstData_D22 === '02' && lstSubData31 === '1' && lstData42 === '0' && check_box !== false)
                                {
                                    alert("Vui lòng rà soát lại cột 21 ( Xã hộ vay chuyển đến ) trước khi lưu");
                                    document.getElementById("lstData_D32" + i).style.backgroundColor = "#EEAFA6";
                                    isValid = false;
                                    break;
                                }
//                                Check 23,24
                                var lstSubData34 = document.getElementById('lstSubData34' + i).value;
                                var lstData41 = document.getElementById('lstData41' + i).value;
                                alert(lstSubData34+"  "+ lstData41);
                                if (lstData42 === '0' && lstData_D22 === '02' && check_box !== false && lstSubData34 === '5' && lstData41.length < 1)
                                {
                                    alert("Vui lòng điền thông tin vào cột 24!");
                                    document.getElementById("lstData41" + i).style.backgroundColor = "#EEAFA6";
                                    isValid = false;
                                    break;
                                }
                                //Bắt số đt
                                var lstData_D16 = document.getElementById('lstData_D16' + i).value;
                                if (lstData_D16.length != 10 && lstData_D16.length != 0 && check_box !== false)
                                {
                                    alert('Vui lòng nhập thông số điện thoại 10 số.')
                                    document.getElementById("lstData_D16" + i).style.backgroundColor = "#EEAFA6";
                                    isValid = false;
                                    break;
                                }

                                var D22 = $('#lstData_D22' + i).find(":selected").val();
                                if (D22 == '01') {
                                    var lstDataD23 = document.getElementById('lstDataD23' + i).value;
                                    if (lstDataD23.length < 5 && check_box !== false) {
                                        alert('Vui lòng nhập thông tin cột 18 (Tối thiểu 5 ký tự)');
                                        document.getElementById("lstDataD23" + i).style.backgroundColor = "#EEAFA6";
                                        isValid = false;
                                        break;
                                    }
                                }
                            } catch (e) {
                            }
                        }

                        if (isValid) { // Nếu dữ liệu hợp lệ, tiến hành gửi request AJAX
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
                                    } else if (data === "01") {
                                        alert("Lỗi: Bạn chưa nhập thông tin cột 15");
                                        $("#idSearch").trigger("click");
                                    } else {
                                        alert("Lỗi: Lưu dữ liệu.");
                                        $("#idSearch").trigger("click");
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
                                }
//                                else if(data === "1") {
//                                    alert("Khi chọn cột 13 là 01 bạn không được để trống thông tin cột 16, 17, 18.");
////                                    $("#viewData").html('<h2 style="color:red;">Gửi dữ liệu thành công!</h2>');
//                                }
                                else {
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
                    let aCheck = confirm("Bạn chắc chắn muốn đề nghị xóa dữ liệu ?");
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
                                    alert("Thành công: Đề nghị xóa dữ liệu.");
                                    $("#idSearch").trigger("click");
                                } else {
                                    alert("Lỗi: Đề nghị xóa dữ liệu.");
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
