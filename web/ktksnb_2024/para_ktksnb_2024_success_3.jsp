<%-- 
    Document   : input_main
    Created on : Oct 26, 2015, 1:45:53 PM
    Author     : LION
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%--<s:head/>
<sj:head/>--%>

<!DOCTYPE html>
<html>
    <head>
        <style>
            #menuBcttv_para{
                width: 100%;
                height: 25px;                
                border: 1px solid; 
                padding-bottom: 0px;
                padding-top: 0px;
            }

            #containBcttv_para{
                width: 100%;
                min-height:390px;
                border: 1px solid;
                margin-top: 2px;
            }

            .metroButtonStyle {
                font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
                display: block;
                color: rgb(255, 255, 255);
                text-decoration: none;
                text-align: center;
                width: 70px;
                height: 20px;
                padding: 5px;
                margin: 5px 0px 0px 5px;
                font-size: 12px;
                background: none repeat scroll 0 0 #808080;
                color: #FFF;
                border: 0px none;
                border-radius: 1px 1px 1px 1px;
                outline: 0px none;
            }
            .metroButtonStyle:hover {
                background: #018c3b;
            }
            .metroButtonStyle:active {
                background: #DCDCDC;
            }

            #container{
                width: 100%;
                height: 500px;
                border: 0px solid;
                padding-left: 0px;        
                /*color: #FFE6B0*/
            }

            #containTree{
                width: 15%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow-x: scroll;
            }

            #containTreeQD23_2{
                width: 7%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow-x: scroll;
            }
            #containTreeQD23_3{
                width: 12%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow-x: scroll;
            }


            #containParm{
                width: 84%;
                height: 450px;
                padding-left: 5px;
                float: left;
                overflow-x: scroll;
            }

            #containParmQD23_2{
                width: 92%;
                height: 450px;
                padding-left: 5px;
                float: left;
                /*overflow-x: scroll;*/
            }
            #containParmQD23_3{
                width: 87%;
                height: 450px;
                padding-left: 5px;
                float: left;
                /*overflow-x: scroll;*/
            }

            #containParm_full{
                width: 100%;
                /*height: 450px;*/
                /*padding-left: 5px;*/
                float: left;
                /*overflow: scroll;*/
            }
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: 12px;
            }

            .report_group_form{
                width: 100%;
            }

            #navParamUp{
                height: 35px;
                padding:0px;
                padding-bottom: 0px;
                padding-top: 0px;
                /*margin:5px;*/
                /*border-radius: 10px; //bo tron goc*/
                border: 1px solid;                
                /*                height: 50px;
                                border: 1px solid;  
                                border-radius: 10px; //bo tron goc
                                -moz-border-radius: 10px;
                                margin:5px;
                                padding:5px;*/
            }
            #navParam{
                height: 35px;
                padding:0px;
                padding-bottom: 0px;
                padding-top: 0px;
                /*margin:5px;*/
                /*border-radius: 10px; //bo tron goc*/
                border: 1px solid;                
                /*                height: 50px;
                                border: 1px solid;  
                                border-radius: 10px; //bo tron goc
                                -moz-border-radius: 10px;
                                margin:5px;
                                padding:5px;*/
            }
            #navParam3{
                height: 35px;
                border: 0px solid;
                margin-left: 10px;
                font-weight: bold;
                border-left: 40px;
                float: left;
                padding-bottom: 0px;
                padding-top: 0px;
            }
            #message_suc_err
            {
                height: 30px;
                border: 0px solid;
                padding-bottom: 0px;
                padding-top: 0px;
            }

            .button-container {
                position: relative;
                left: 1140px; /* Điều chỉnh khoảng cách theo nhu cầu */
            }

            #idSaveLock {
                display: block; 
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
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>

            var bsubmit = false;
            $(document).ready(function () {
                $(".NGAY_SL").css({"width": "80px"});
            });

            function onLoadData() {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                $('#divExportReportLink').empty();
                $("#loadData")[0].click();
                btnDisabled(0);
                bsubmit = true;
            }

            $("#idSave").click(function () {
                $('#message_suc_err, #divExportReportLink').empty();

                showConfirmationDialog("Thông báo", "Bạn chắc chắn muốn lưu số liệu báo cáo?", function () {
                    const chot = $("#chotsl").val();
                    const chotsl_th = $("#chotsl_th").val();
                    const check_kehoach = $("#check_kehoach").val();
                    const check_dieuchinh = $("#check_dieuchinh").val();
                    if (chot === "4") {
                        return showErrorDialog("Cảnh báo!", "Kế hoạch đã nhập thực hiện!, không thể thay đổi!",onLoadData);
                    }
                    if (chotsl_th !== "0") {
                        return showWarningDialog("Cảnh báo!", "Kế hoạch đã được thực hiện!, không thể bổ sung!",onLoadData);
                    }
                    if (check_kehoach !== "0" || check_dieuchinh !== "0") {
                        showConfirmationDialog("Cảnh báo!", "Kế hoạch đã tồn tại! Bạn có muốn tiếp tục không?", saveData, onLoadData);
                    } else {
                        saveData();
                    }
                });
            });


            function saveData() {
                const url = "save_KTKSNB_04_2025.action";
                const sdata = $("#frmdata").serialize();

                $("#viewData").html('<img src="img/loading.gif"/>');
                btnDisabled(1);

                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        showSuccessDialog("Thông báo", data === "200" ? "Thành công: Lưu dữ liệu." : "Lỗi: Lưu dữ liệu.");
                        onLoadData();
                    },
                    complete: function () {
                        btnDisabled(0);
                    },
                    error: function () {
                        showWarningDialog("Lỗi", "Vui lòng liên hệ với quản trị viên.");
                        btnDisabled(0);
                    }
                });
            }

            // Hàm hiển thị cảnh báo
            function showWarningDialog(title, text, callback) {
                Swal.fire({
                    title: title,
                    text: text,
                    icon: "warning",
                    confirmButtonColor: "#d33",
                    confirmButtonText: "OK"
                }).then(() => {
                    if (callback)
                        callback();
                });
            }

            // Hàm hiển thị cảnh báo lỗi
            function showErrorDialog(title, text, callback) {
                Swal.fire({
                    title: title,
                    text: text,
                    icon: "error",
                    confirmButtonColor: "#red",
                    confirmButtonText: "OK"
                }).then(() => {
                    if (callback)
                        callback();
                });
            }
// Hàm hiển thị thông báo thành công với icon chữ V màu xanh
            function showSuccessDialog(title, text) {
                Swal.fire({
                    title: title,
                    text: text,
                    icon: "success", // Biểu tượng dấu ✔ màu xanh
                    confirmButtonColor: "#28a745", // Xanh lá cây
                    confirmButtonText: "OK"
                });
            }

// Hàm chung hiển thị hộp thoại xác nhận
            function showConfirmationDialog(title, text, confirmCallback, cancelCallback) {
                Swal.fire({
                    title: title,
                    text: text,
                    icon: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#d33",
                    cancelButtonColor: "#3085d6",
                    confirmButtonText: "Tiếp tục",
                    cancelButtonText: "Dừng lại"
                }).then((result) => {
                    if (result.isConfirmed && confirmCallback) {
                        confirmCallback();
                    } else if (cancelCallback) {
                        cancelCallback();
                    }
                });
            }



            function btnDisabled(status) {
                if (status === 1) {
                    $("#loadDatatmp").prop('disabled', true);
                    $("#idPheduyet").prop('disabled', true);
                    $("#idSave").prop('disabled', true);
                    $("#idSaveLock").prop('disabled', true);
                    $("#idDelete").prop('disabled', true);
                    $("#idSeach").prop('disabled', true);
                    $("#idSend").prop('disabled', true);
                } else if (status === 2) {
                    $("#loadDatatmp").prop('disabled', false);
                    $("#idSend").prop('disabled', true);
                    $("#idSave").prop('disabled', true);
                    $("#idDelete").prop('disabled', true);
                } else {
                    $("#idPheduyet").prop('disabled', false);
                    $("#idSave").prop('disabled', false);
                    $("#loadDatatmp").prop('disabled', false);
                    $("#idSaveLock").prop('disabled', false);
                    $("#idDelete").prop('disabled', false);
                    $("#idSeach").prop('disabled', false);
                    $("#idSend").prop('disabled', false);
                }
            }
            ;

            function countCheckedItem() {
                let counter = 0;
                $('.myCheckBox').each(function () {
                    if (this.checked === true)
                        counter++;
                });
                return counter;
            }
            function wait(ms) {
                var start = new Date().getTime();
                var end = start;
                while (end < start + ms) {
                    end = new Date().getTime();
                }
            }

            function onUpExcel()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                $("#idUpExcel")[0].click();
                bsubmit = true;
            }

            // TRUNG BO SUNG PHAN THUYET MINH

            $.subscribe("beforediv_data", function (event, data) {
                $("#loadingImageDiv_data").show();
            });
            $.subscribe("completediv_data", function (event, data) {
                $("#loadingImageDiv_data").hide();
            });
            $.subscribe("beforediv_ss", function (event, data) {
                $("#loadingImageDiv_data").show();
            });
            $.subscribe("completediv_ss", function (event, data) {
                $("#loadingImageDiv_data").hide();
            });
            $.subscribe("beforediv_send", function (event, data) {
                $("#loadingImageDiv_data").show();
            });
            $.subscribe("completediv_send", function (event, data) {
                $("#loadingImageDiv_data").hide();
            });
            //Disable enter key form submit
            function stopRKey(evt) {
                var evt = (evt) ? evt : ((event) ? event : null);
                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
                if ((evt.keyCode === 13) && (node.type === "text")) {
                    return false;
                }
            }

            //Disable enter key form submit            
            document.onkeypress = stopRKey;
            function getposfromtreecheck()
            {

                var pos_cd = '';
                var idform = 'id_' + '<s:property value="khoa_nhaptaycn"/>';
                var element = document.forms[idform].elements;
//                 alert('bat dau goi submit idform='+idform);
                var i = element.length;
                for (var k = 0; k < i; k++)
                {
                    if (element[k].name === 'poscd')
                    {
                        if (element[k].checked === true)
                        {
                            if (element[k].value !== '999999')
//                            alert(document.loadFormRisk.elements[k].value);
                                pos_cd = pos_cd + element[k].value + ',';
                        }
                    }
                }
//                alert('bat dau goi submit pos_cd='+pos_cd);
                return pos_cd;
            }

            function openClick()
            {
                var khoa = $("#khoa").val() + "_open";
                var idform = 'idform_open_' + '<s:property value="khoa_nhaptaycn"/>';
                if ($('#' + idform + ' input:checkbox:checked').length > 0)
                {
                    $("#" + khoa)[0].click();
                } else
                {
                    // none is checked
                    alert("Bạn phải chọn phòng giao dịch cần mở khóa !");
//                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn phòng giao dịch cần mở khóa !</h2>");
                }
            }
            $("#idSend").click(function () {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();

                let aCheck = confirm("Bạn chắc chắn muốn gửi số liệu báo cáo ?");
                if (aCheck) {
                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var chot = document.getElementById("chotsl").value;
                    var chot_tw = document.getElementById("chotsl_tw").value;
                    var chotsl_th = document.getElementById("chotsl_th").value;
                    var isValid = true;
                    if (chotsl_th !== "0") {
                        alert("Cảnh báo: Kế hoạch đã được thực hiện!, không thể bổ sung!");
                        isValid = false;
                        onLoadData();
                    }
                    if (chot === "2") {
                        alert("Cảnh báo: Không thể lưu dữ liệu, Chi nhánh đã chốt dữ liệu lên Tw!");
//                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Không thể lưu dữ liệu, Chi nhánh đã chốt dữ liệu lên Tw!</h>");
                        isValid = false;
                        onLoadData();
                    } else if (chot === "1") {
                        alert("Cảnh báo: Dữ liệu đã được gửi. Không thể thực hiện thay đổi!");
//                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Dữ liệu đã được gửi. Không thể thực hiện thay đổi!</h>");
                        isValid = false;
                        onLoadData();
                    } else if (chot_tw === "0") {
                        alert("Cảnh báo: Lưu dữ liệu trước khi gửi!");
//                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Lưu dữ liệu trước khi gửi!</h>");
                        isValid = false;
                        onLoadData();
                    }
                    for (var i = 0; i < rowcount; i++) {
                        try {
                        } catch (e) {
                        }
                    }
                    if (isValid) {
                        var url, sdata;
                        url = "send_KTKSNB_04_2025.action";
                        sdata = jQuery("#frmdata").serialize();
                        $("#viewData").html('<img src="img/loading.gif"/>');
                        btnDisabled(1);
                        $.ajax({
                            type: "POST",
                            url: url,
                            data: sdata,
                            success: function (data) {
                                if (data === "200") {
//                                    alert("Thành công: Gửi dữ liệu.");
                                    $('#message_suc_err').html("<h style='color: green; font-size: 13px ; font-weight: bold'>Bạn đã gửi dữ liệu thành công!</h>");
                                    onLoadData();
                                } else {
                                    alert("Lỗi: Gửi dữ liệu.");
                                    onLoadData();
                                }
                            },
                            complete: function () {
                                btnDisabled(0);
                            },
                            error: function (request) {
                                alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                                onLoadData();
                            }
                        });
                    }
                }

            });
        </script>
    </head>
    <body>

        <s:form id="frmdata" name="frmdata" action="%{khoa_nhaptaycn}" theme="simple">
            <s:hidden name="khoa_nhaptaycn" id="khoa"/>
            <s:hidden name="ReportDate" id="ReportDate" value=""/>
            <s:hidden name="Grade" id="Grade"/>
            <s:hidden name="UserName" id="UserName"/>               
            <fieldset>
                <legend><b>Tìm kiếm dữ liệu</b></legend> 
                <table>
                    <label id="title5">&nbsp; Bổ sung tháng: </label>
                    <select id="monthSelect" name="monthSelect">
                        <option value="0">---Chọn---</option>
                        <option value="1">Tháng 1</option>
                        <option value="2">Tháng 2</option>
                        <option value="3">Tháng 3</option>
                        <option value="4">Tháng 4</option>
                        <option value="5">Tháng 5</option>
                        <option value="6">Tháng 6</option>
                        <option value="7">Tháng 7</option>
                        <option value="8">Tháng 8</option>
                        <option value="9">Tháng 9</option>
                        <option value="10">Tháng 10</option>
                        <option value="11">Tháng 11</option>
                        <option value="12">Tháng 12</option>
                    </select>  
                    <label style="font-weight: bold">&nbsp; Năm </label>
                    <select id="yearSelect" name="yearSelect"></select>
                    <s:if test="!Grade.equalsIgnoreCase('3')">
                        <label id="title22">&nbsp; PGD kiểm tra :</label>
                        <select id="lstPGD" name="lstPGD" style="width: 100px">
                            <option value="000000">---Chọn PGD---</option>
                            <s:iterator value="lstPGD_API">                                    
                                <option value="<s:property value='posCode'/>|<s:property value='posName'/>"><s:property value="posCode"/> - <s:property value="posName"/></option>                                         
                            </s:iterator>   
                        </select>
                        <label id="title3">&nbsp; Kế hoạch :</label>
                        <select name="txtKehoach" id="txtKehoach" style="width: 300px" onchange="toggleSelect()">                                                    
                            <option value="1">1. Đoàn kiểm tra của NHCSXH cấp huyện đối với cấp xã</option>   
                            <s:if test="!pos_cd.equalsIgnoreCase(main_pos)">
                                <option value="2">2. Cán bộ chuyên trách KTKSNB cấp huyện</option></s:if>
                            </select> 
                            <label id="title4">&nbsp; Xã kiểm tra: </label>
                            <select id="lstXa" name="lstXa" style="width: 100px">
                                <option value="000000">---Chọn xã---</option>
                            <s:iterator value="lstXa_API">                                    
                                <option value="<s:property value="communeCode"/>|<s:property value="communeName"/>"><s:property value="communeCode"/> - <s:property value="communeName"/></option>                                         
                            </s:iterator>   
                        </select>

                        <%--</s:if>--%>

                        <label id="title2">&nbsp; Cán bộ kiểm tra :</label>
                        <select name="txtCanbo" id="txtCanbo" style="width: 100px">   
                            <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                                <option value='<s:property value="D1"/>'><s:property value="D2"/> - <s:property value="D3"/></option>      
                            </s:iterator>
                            <option value="00000">---Chọn cán bộ---</option>
                        </select> 
                    </s:if>
                    <s:if test="Grade.equalsIgnoreCase('3')">
                        <label id="title31">&nbsp; Mã chi nhánh: </label>
                        <select id="lstCN"  name="lstCN">
                            <option value="000000">----Chọn mã chi nhánh----</option>
                            <s:iterator value="lstCN_API">
                                <option value="<s:property value="branchCode"/>"><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>               
                            </s:iterator>
                        </select> 
                    </s:if>
                    &nbsp;<sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                               onBeforeTopics="beforediv_data"
                               onCompleteTopics="completediv_data" cssStyle="display:none"/>
                    <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                    <s:if test="!Grade.equalsIgnoreCase('3')">
                        &nbsp;<input type="button" id="idSave" value="Lưu dữ liệu"/>  
                        &nbsp;<input type="button" id="idDelete" value="Xóa dữ liệu" style="color: red"/>  
                        <!--&nbsp;<input type="button" id="idSend" value="Chốt dữ liệu" style="color: red"/>-->
                        &nbsp;<input type="button" id="idSeach" value="Danh sách bổ sung" style="color: #0000FF"/>
                    </s:if>
                </table>    
            </fieldset>
            <div id="loadingImageDiv_data" style="margin-left: 20px;display: none;" >
                <img id="loadingImage" src='img/loading.gif' border='0' >                  
            </div>   
            <div id="message_suc_err" style="height: 10px"></div>
            <div id="containParm_full" align="center">
                <div id="divExportReport"></div>
                <div align="right"  id="divExportReportLink"></div>
            </div>
        </s:form>

        <script>
            const yearSelect = document.getElementById("yearSelect");
            const currentYear = new Date().getFullYear();
            const startYear = 2025; // Năm bắt đầu
            const yearsToShow = 10; // Số năm cần hiển thị

            // Lặp từ năm bắt đầu đến số năm muốn hiển thị
            for (let i = 0; i < yearsToShow; i++) {
                let option = document.createElement("option");
                let year = startYear + i; // Tính toán năm
                option.value = year;
                option.text = year;

                // Đặt năm hiện tại là mặc định
                if (year === currentYear) {
                    option.selected = true;
                }

                yearSelect.appendChild(option);
            }
            function toggleButton() {
            <s:if test="Grade.equalsIgnoreCase('2')">
                var selectedValue = document.getElementById("txtGetData").value;
                if (selectedValue === "1") {
                    document.getElementById("idSave").style.display = "inline";
                    document.getElementById("idSend").style.display = "inline";
                    document.getElementById("idDelete").style.display = "inline";
                    document.getElementById("lstPGD").style.display = "inline";
                    document.getElementById("title22").style.display = "inline";
                    document.getElementById("lstPGD").style.display = "inline";
                    document.getElementById("title5").style.display = "inline";
                    document.getElementById("monthSelect").style.display = "inline";
                    onLoadData();
                } else {
                    document.getElementById("idSave").style.display = "none";
                    document.getElementById("idDelete").style.display = "none";
                    document.getElementById("idSend").style.display = "none";
                    document.getElementById("title22").style.display = "none";
                    document.getElementById("lstPGD").style.display = "none";
                    document.getElementById("title5").style.display = "none";
                    document.getElementById("monthSelect").style.display = "none";
                    onLoadData();
                }
            </s:if>
            }
            function toggleSelect() {
            <s:if test="Grade.equalsIgnoreCase('1')">
                var selectedValue = document.getElementById("txtKehoach").value;
                if (selectedValue === "2") {
                    document.getElementById("title2").style.display = "inline";
                    document.getElementById("txtCanbo").style.display = "inline";
                    onLoadData();
                } else {
                    document.getElementById("title2").style.display = "none";
                    document.getElementById("txtCanbo").style.display = "none";
                    onLoadData();
                }
            </s:if>
            }
            function initTable()
            {
            <s:if test="Grade.equalsIgnoreCase('1')">
                document.getElementById("title2").style.display = "inline";
                document.getElementById("txtCanbo").style.display = "inline";
                document.getElementById("title3").style.display = "inline";
                document.getElementById("txtKehoach").style.display = "inline";
                document.getElementById("title4").style.display = "inline";
                document.getElementById("lstXa").style.display = "inline";
//                document.getElementById("title21").style.display = "none";
//                document.getElementById("txtGetData").style.display = "none";
                document.getElementById("title22").style.display = "none";
                document.getElementById("lstPGD").style.display = "none";
                var D1 = document.getElementById("txtKehoach").value;
                if (D1 === "1")
                {
                    document.getElementById("title2").style.display = "none";
                    document.getElementById("txtCanbo").style.display = "none";
                } else
                {
                    document.getElementById("title2").style.display = "inline";
                    document.getElementById("txtCanbo").style.display = "inline";
                }
            </s:if>
            <s:if test="Grade.equalsIgnoreCase('2')">
//                document.getElementById("title21").style.display = "inline";
//                document.getElementById("txtGetData").style.display = "inline";
                document.getElementById("title22").style.display = "inline";
                document.getElementById("lstPGD").style.display = "inline";
                document.getElementById("title2").style.display = "none";
                document.getElementById("txtCanbo").style.display = "none";
                document.getElementById("title3").style.display = "none";
                document.getElementById("txtKehoach").style.display = "none";
                document.getElementById("title4").style.display = "none";
                document.getElementById("lstXa").style.display = "none";
            </s:if>
            }

            initTable();

            $("#idDelete").click(function () {
                $('#message_suc_err, #divExportReportLink').empty();

                showConfirmationDialog("Thông báo", "Bạn chắc chắn muốn xóa số liệu báo cáo?", function () {
                    const chot = $("#chotsl").val();
                    const chotsl_th = $("#chotsl_th").val();
                    const check_kehoach = $("#check_kehoach").val();
                    const check_dieuchinh = $("#check_dieuchinh").val();
                    if (chot === "4") {
                        showConfirmationDialog("Cảnh báo!", "Kế hoạch đã nhập thực hiện, bạn chắc chắn muốn xóa dữ liệu!", function () {
                            // Nếu xác nhận lần 2
                            DeleteData();
                        }, "Dữ liệu sẽ bị xóa cả bên thực hiện và bổ sung");
                        return; // Dừng ở đây nếu chờ xác nhận lần 2
                    }
                    if (chotsl_th !== "0") {
                        return showWarningDialog("Cảnh báo!", "Kế hoạch đã được thực hiện!, không thể xóa!");
                        onLoadData();
                    }
                    if (chot === "0") {
                        return showWarningDialog("", "Chưa có kế hoạch bổ sung!");
                        onLoadData();
                    } else {
                        DeleteData(); // Nếu không có cảnh báo, lưu dữ liệu ngay
                    }
                });
            });


            function DeleteData() {
                const url = "delete_KTKSNB_2025.action";
                const sdata = $("#frmdata").serialize();

                $("#viewData").html('<img src="img/loading.gif"/>');
                btnDisabled(1);

                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        showSuccessDialog("Thông báo", data === "200" ? "Thành công: Xóa dữ liệu." : "Lỗi: Xóa dữ liệu.");
                        onLoadData();
                    },
                    complete: function () {
                        btnDisabled(0);
                    },
                    error: function () {
                        showWarningDialog("Lỗi", "Vui lòng liên hệ với quản trị viên.");
                        btnDisabled(0);
                    }
                });
            }

            $("#idSeach").click(function () {
                var url, sdata;
                url = "load_data_bsung_2024.action";
                sdata = jQuery("#frmdata").serialize();
                $("#divExportReport").html('<img src="img/loading.gif"/>');
                btnDisabled(1);
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        $("#divExportReport").html(data);
                    },
                    complete: function () {
                        btnDisabled(2);
                    },
                    error: function (request) {
                        console.log(request);
                        alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                    }
                });
            }
            );
        </script>
    </body>
</html>
