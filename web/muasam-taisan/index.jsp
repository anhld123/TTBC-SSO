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
            <s:form name="frmdata" id="frmdata" theme="simple">
                <input type="hidden" name="gradeAuthor1" id="gradeAuthor1" value="<s:property value='gradeAuthor1'/>">
                <fieldset style="display: flex; align-content: space-between;justify-content: space-between;">                    
                    <legend><b>Tìm kiếm dữ liệu</b></legend>
                    
                        <div>
                            Đơn vị:
                            <select name="txtMapgd" id="txtMapgd">
                                <s:iterator value="lstDonvi">
                                    <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>
                                </s:iterator>
                            </select>                        
                            
                            Ngày báo cáo: <input type="text" name="txtNgayBc" id="txtNgayBc" readonly="readonly"/>
                            
                            
                        </div>
                        <div>
                            <input type="button" id="idSearch" value="Tải dữ liệu" style="height: 25px; padding: 0px 20px 0px 20px;">                            
                            <input type="button" id="idSave" value="Lưu dữ liệu" style="height: 25px;padding: 0px 20px 0px 20px;" >
                            <input type="button" id="idDelete" value="Xóa dữ liệu" style="height: 25px;padding: 0px 20px 0px 20px;" >
                        </div>
                                     
                </fieldset>
                <div>
                    <div id="viewData" ></div>
                </div>
            </s:form>
        </div>
        <script>            
            
            function getLastDateOfYear() {

                //const date = new Date();
                const _lastDayOfYear = new Date(new Date().getFullYear(), 11, 31);

                const yyyy = _lastDayOfYear.getFullYear();
                const mm = String(_lastDayOfYear.getMonth() + 1).padStart(2,'0');
                const dd = String(_lastDayOfYear.getDate()).padStart(2,'0');

                return dd + "/" + mm + "/" + yyyy;
            }
            
            $(function () {                
                var _lastDayOfYear = getLastDateOfYear();                
                $("#txtNgayBc").datepicker(
                {
                    dateFormat: 'dd/mm/yy',
                    showOn: "button",
                    buttonImage: "img/icon-ui_datepicker.png",
                    buttonImageOnly: true,
                    // dateFormat: 'dd/mm/yy',
                    showButtonPanel: true,
                    buttonText: "icono",
                    changeMonth: true,
                    changeYear: true,
                    defaultDate: new Date(new Date().getFullYear(), 11, 31)
                }).val(_lastDayOfYear);
                });
            
            //Tải dữ liệu
            $("#idSearch").click(function () {
                var url, sdata;                
                url = "KTTC_MUASAM_01_LoadData.action";
                sdata = jQuery("#frmdata").serialize();
                $("#viewData").html('<img src="img/loading.gif"/>');
                btnDisabled(1);
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        $("#viewData").html(data);                        
                        $("#idSave").prop('disabled', false);                        
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
                    url = "KTTC_MUASAM_01_SaveData.action";
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

            function countCheckedItem() {
                let counter = 0;
                $('.myCheckBox').each(function () {
                    if (this.checked === true)
                        counter++;
                });
                return counter;
            }

            $("#idDelete").click(function () {
                let checkedCount = countCheckedItem();
                if (checkedCount === 0 || checkedCount > 1) {
                    alert('Bạn chưa chọn bản ghi để xóa hoặc mỗi lần bạn chỉ được phép xóa tối đa 1 bản ghi!');
                } else {
                    let aCheck = confirm("Bạn chắc chắn muốn xóa dữ liệu ?");
                    if (aCheck) {
                        var url, sdata;
                        url = "KTTC_MUASAM_01_DeleteData.action";
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

            function PopupCenter(pageURL, title, w, h) {
                var left = (screen.width / 2) - (w / 2);
                var top = (screen.height / 2) - (h / 2);
                var targetWin = window.open(pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
                return targetWin;
            }
           
            function btnDisabled(status) {
                if (status === 1) {
                    $("#idSearch").prop('disabled', true);
                } else {
                    $("#idSearch").prop('disabled', false);
                }
            }
        </script>
    </body>
</html>
