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

        </style>
        <script>
            var bsubmit = false;
            $(document).ready(function () {
                $(".NGAY_SL").css({"width": "80px"});
            });

            function onLoadData() {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                $('#divExportReportLink').empty();

                var ngay_bc = $("#ngay_bc_DATE").val();
                var lv_day = parseInt(ngay_bc.substr(0, 2));
                var lv_month = parseInt(ngay_bc.substr(3, 2));
                var lv_year = parseInt(ngay_bc.substr(6, 4));

                // Check if the selected date is the last day of the year
                if (lv_day !== 31 || lv_month !== 12 || lv_year !== new Date().getFullYear()) {
                    alert("Chọn ngày cuối năm để tải dữ liệu!");
                    return;
                }

                $("#loadData")[0].click();
                bsubmit = true;
            }


            $("#idSave").click(function () {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();

                let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                if (aCheck) {
                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var isValid = true;
            <s:if test="Grade.equalsIgnoreCase('1')">
                    var lock = document.getElementById("lock_temp").value;
                    var chot = document.getElementById("chotsl_temp").value;
                    if (lock === "1" || chot === "2") {
                        alert("Dữ liệu đã gửi, không thể lưu.");
                        isValid = false; // Không cho phép lưu dữ liệu
                        onLoadData();
                    }
            </s:if>
                    for (var i = 0; i < rowcount; i++) {
                        try {
                        } catch (e) {
                        }
                    }
                    if (isValid) {
                        var url, sdata;
                        url = "save_MSTS_2024.action";
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
                                    $('#message_suc_err').html("<h style='color: green; font-size: 13px ; font-weight: bold'>Bạn đã lưu dữ liệu thành công!</h>");
                                    onLoadData();
                                } else {
                                    alert("Lỗi: Lưu dữ liệu.");
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

            function btnDisabled(status) {
                if (status === 1) {
                    $("#loadDatatmp").prop('disabled', true);
                    $("#idSend").prop('disabled', true);
                    $("#idSave").prop('disabled', true);
                    $("#idUnlock").prop('disabled', true);
                    $("#idSeach").prop('disabled', true);
                    $("#lock_MSTS_2024tmp").prop('disabled', true);
                    $("#idDelete").prop('disabled', true);
                } else {
                    $("#loadDatatmp").prop('disabled', false);
                    $("#idSend").prop('disabled', false);
                    $("#idSave").prop('disabled', false);
                    $("#idUnlock").prop('disabled', false);
                    $("#idSeach").prop('disabled', false);
                    $("#lock_MSTS_2024tmp").prop('disabled', false);
                    $("#idDelete").prop('disabled', false);
                }
                ;
            }

            $("#idSend").click(function () {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();

                let aCheck = confirm("Bạn chắc chắn muốn gửi số liệu báo cáo ?");
                if (aCheck) {
                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var lock = document.getElementById("lock_temp").value;
                    var chot = document.getElementById("chotsl_temp").value;
                    var isValid = true;
                    if (lock === "1" || chot === "1") {
                        alert("Dữ liệu đã gửi, không thể tiếp tục gửi.");
                        isValid = false; // Không cho phép lưu dữ liệu
                        onLoadData();
                    }
                    for (var i = 0; i < rowcount; i++) {
                        try {
                        } catch (e) {
                        }
                    }
                    if (isValid) {
                        var url, sdata;
                        url = "send_MSTS_2024.action";
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
                                    $('#message_suc_err').html("<h style='color: green; font-size: 13px ; font-weight: bold'>Bạn đã gửi dữ liệu thành công!</h>");
                                    onLoadData();
                                } else {
                                    alert("Lỗi: Lưu dữ liệu.");
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

            $("#idUnlock").click(function () {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();

                let aCheck = confirm("Bạn chắc chắn muốn mở dữ liệu báo cáo ?");
                if (aCheck) {
                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var chot = document.getElementById("chotsl_temp").value;
                    var isValid = true;
                    if (chot === "2") {
                        alert("Dữ liệu đã gửi lên TW, không thể mở dữ liệu.");
                        isValid = false; // Không cho phép lưu dữ liệu
                        onLoadData();
                    }
                    for (var i = 0; i < rowcount; i++) {
                        try {
                        } catch (e) {
                        }
                    }
                    if (isValid) {
                        var url, sdata;
                        url = "save_MSTS_2024.action";
                        sdata = jQuery("#frmdata").serialize();
                        $("#viewData").html('<img src="img/loading.gif"/>');
                        btnDisabled(1);
                        $.ajax({
                            type: "POST",
                            url: url,
                            data: sdata,
                            success: function (data) {
                                if (data === "200") {
                                    alert("Thành công: Mở dữ liệu.");
                                    $('#message_suc_err').html("<h style='color: green; font-size: 13px ; font-weight: bold'>Bạn đã mở dữ liệu thành công!</h>");
                                    onLoadData();
                                } else {
                                    alert("Lỗi: Lưu dữ liệu.");
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

            $("#idDelete").click(function () {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();

                let aCheck = confirm("Bạn chắc chắn muốn xóa dữ liệu báo cáo ?");
                if (aCheck) {
                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var chot = document.getElementById("chotsl_temp").value;
                    var isValid = true;
            <s:if test="Grade.equalsIgnoreCase('1')">
                    var lock = document.getElementById("lock_temp").value;
                    var chot = document.getElementById("chotsl_temp").value;
                    if (lock === "1" || chot !== "0") {
                        alert("Dữ liệu đã gửi, không thể xóa.");
                        isValid = false; // Không cho phép lưu dữ liệu
                        onLoadData();
                    }
            </s:if>
                    for (var i = 0; i < rowcount; i++) {
                        try {
                        } catch (e) {
                        }
                    }
                    if (isValid) {
                        var url, sdata;
                        url = "delete_MSTS_2024.action";
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
                                    $('#message_suc_err').html("<h style='color: green; font-size: 13px ; font-weight: bold'>Bạn đã xóa dữ liệu thành công!</h>");
                                    onLoadData();
                                } else {
                                    alert("Lỗi: Xóa dữ liệu.");
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


            function validateRequiredFields() {
                var result = true; //Luu ket qua kiem tra kieu so co dung khong
                var arrCot = [".number", ".number2", ".number3"]; //Luu cac cot cua du lieu can tinh toan

                //Tinh toan tong cho ca 2 cot KH_UOC_TH, KH_KH_NAM
                for (k = 0; k < arrCot.length; k++) {
                    //Cac class nubmer2 phai nhap kieu so
                    $(arrCot[k]).each(function (index) {
                        if (!result)
                        {
                            return false;
                        }
                        var value = $(this).val();
                        value = value.replace(/,/g, "");
                        //value = '1.34.5';
                        if (isNaN(value)) {
                            result = false;
                            //Neu nguoi dung khong nhap dung kieu du lieu
                            //Dua ra canh bao
                            alert('Bạn nhập không đúng kiểu số xin nhập lại dữ liệu');
                            $("#message_suc_err").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn nhập không đúng kiểu số xin nhập lại dữ liệu!');
                            return false;
                        }
                        //Neu la kieu so --> Kiem tra xem kieu nhap co < 9999999999
                        if (parseFloat(value) > 999999999999) {
                            result = false;
                            //Dua ra canh bao
                            $("#message_suc_err").html('<span style="color:red"><h2><span style="font-weight: bold; color">Thông báo:</span>  Giá trị bạn nhập vượt quá giới hạn!</h2></span>');
                            alert('Giá trị bạn nhập vượt quá giới hạn!');
                            return false;
                        }
                        // }
                    });
                }
                return result;
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

            function reLoadValue(val) {
                var var2, vartxt, selected;
                $("#mato").children().remove().end();
                $("#mato").prepend("<option value='000000_0000000' " + selected + "> -- Tất cả -- </option>");
                $("#mato").prepend("<option value='000000_NOGROUP' " + selected + "> NOGROUP -> Trực tiếp</option>");
                $("#mato_data > option").each(function () {
                    var2 = $(this).val().substr(0, 6);
                    if (val.trim() === var2.trim()) {
                        $(this).val() === vartxt ? selected = " selected" : selected = "";
                        $("#mato").prepend("<option value='" + $(this).val() + "' " + selected + "> " + $(this).text() + " </option>");
                    }
                });
                $("#mato").html($("#mato option").sort(function (a, b) {
                    return a.text === b.text ? 0 : a.text < b.text ? -1 : 1;
                }));

            }
            ;

            $("#idSearch").click(function () {
                var url, sdata;
                $("#txtNghiepVu").val("TRACUU");
                url = "seach_MSTS_2024.action";
                sdata = jQuery("#frmdata").serialize();
                $("#divExportReport").html('<img src="img/loading.gif"/>');
                btnDisabled(1);
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        $("#divExportReport").html(data);
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

            $("#idLoad_c3").click(function () {
                // Get and parse the date from the input field
                var ngay_bc = $("#ngay_bc_DATE").val();
                var lv_day = parseInt(ngay_bc.substr(0, 2));
                var lv_month = parseInt(ngay_bc.substr(3, 2));
                var lv_year = parseInt(ngay_bc.substr(6, 4));

                // Check if the selected date is the last day of the year
                if (lv_day !== 31 || lv_month !== 12 || lv_year !== new Date().getFullYear()) {
                    alert("Chọn ngày cuối năm để tải dữ liệu!");
                    return; // Exit the function if the date is not the last day of the year
                }

                // Prepare for AJAX request
                var url, sdata;
                $("#txtNghiepVu").val("TRACUU");
                url = "loadc3_MSTS_2024.action";
                sdata = jQuery("#frmdata").serialize();
                $("#divExportReport").html('<img src="img/loading.gif"/>');
                btnDisabled(1);

                // Make AJAX request
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        $("#divExportReport").html(data);
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


        </script>
    </head>
    <!--new java.util.Date()-->
    <body>

        <s:form id="frmdata" name="frmdata" action="%{khoa_nhaptaycn}" theme="simple">
            <s:hidden name="khoa_bcqt" id="khoa"/>
            <s:hidden name="ReportDate" id="ReportDate" value=""/>
            <s:hidden name="Grade" id="Grade"/>
            <s:hidden name="UserName" id="UserName"/>               
            <fieldset>
                <legend><b>Tìm kiếm dữ liệu</b></legend> 
                <table>
                    <td>Ngày dữ liệu: </td>
                    <td>
                        <sj:datepicker 
                            name="ngay_bc_DATE"  
                            id="ngay_bc_DATE" 
                            placeholder="DD/MM/YYYY" 
                            changeYear="true" 
                            changeMonth="true" 
                            displayFormat="dd/mm/yy" 
                            cssClass="NGAY_SL" 
                            onChangeTopics="changeTopic"
                            />
                    </td>
                    <s:if test="Grade.equalsIgnoreCase('3')">
                        <td>Mã chi nhánh: </td>
                        <td>
                            <select id="lstCN"  name="lstCN">
                                <option value="000000">----Chọn mã chi nhánh----</option>
                                <s:iterator value="lstCN_API">
                                    <option value="<s:property value="branchCode"/>"><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>               
                                </s:iterator>
                            </select> </td>
                        </s:if>
                    <td colspan="2" style="text-align: right">   
                        <s:if test="!Grade.equalsIgnoreCase('3')">
                            <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                       onBeforeTopics="beforediv_data"
                                       onCompleteTopics="completediv_data" cssStyle="display:none"/>
                            <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                        </s:if>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            &nbsp;<input type="button" id="idSave" value="Lưu dữ liệu"/>
                            &nbsp;<input type="button" id="idDelete" value="Xóa dữ liệu" style="color: red"/>
                            &nbsp;|&nbsp;<input type="button" id="idSend" value="Gửi dữ liệu" style="color: red"/>    
                        </s:if>
                        <s:if test="Grade.equalsIgnoreCase('2')">
                            &nbsp;<input type="button" id="idUnlock" value="Mở dữ liệu"/>
                            &nbsp;|&nbsp;<input type="button" id="idSearch" value="Danh sách gửi dữ liệu" style="color: red">
                            <s:url id="lock_MSTS_2024" action="lock_MSTS_2024.action"></s:url>
                            &nbsp;<sj:submit id="lock_MSTS_2024tmp" name="nameLock" href="%{lock_MSTS_2024}" 
                                       value="Chốt dữ liệu" 
                                       targets="divExportReport"
                                       onBeforeTopics="beforediv_data"
                                       onCompleteTopics="completediv_data"/>

                        </s:if>
                        <s:if test="Grade.equalsIgnoreCase('3')">
                            <input type="button" id="idLoad_c3" value="Tải dữ liệu">
                        </s:if>
                    </td> 

                </table>    
            </fieldset>
            <div id="loadingImageDiv_data" style="margin-left: 20px;display: none;" >
                <img id="loadingImage" src='img/loading.gif' border='0' >                  
            </div>   
            <div id="message_suc_err"></div>
            <s:if test="khoa_nhaptaycn.equalsIgnoreCase('KTTC_MUASAM_01') && !Grade.equalsIgnoreCase('2')">
                <div id="containParm_full" align="center">
                    <div id="divExportReport"></div>
                    <div align="right"  id="divExportReportLink"></div>
                </div>
            </s:if>
            <s:else>
                <div id="containTree">
                    <sjt:tree
                        name="poscd"
                        id="treeDynamicCheckboxes"
                        jstreetheme="apple"
                        rootNode="nodes_pos"
                        childCollectionProperty="children"
                        nodeTitleProperty="title"
                        nodeIdProperty="id"
                        openAllOnLoad="true"
                        checkbox="true"
                        showThemeDots="false"
                        showThemeIcons="true" 
                        />
                </div>
                <div id="containParm" align="center">
                    <div id="divExportReport"></div>
                </div>
            </s:else>                     

        </s:form>

        <script>
            $(document).ready(function () {
                var currentYear = new Date().getFullYear();
                var lastDayOfYear = new Date(currentYear, 11, 31); // 11 corresponds to December

                function disableDates(date) {
                    // Only allow the 31st of December
                    if (date.getDate() === 31 && date.getMonth() === 11) {
                        return [true, "", "Available"];
                    }
                    return [false, "", "Unavailable"];
                }

                // Initialize the datepicker with the beforeShowDay function
                $("#ngay_bc_DATE").datepicker({
                    dateFormat: 'dd/mm/yy',
                    changeMonth: true,
                    changeYear: true,
                    beforeShowDay: disableDates
                });

                // Set the date to 31/12 of the current year
                var formattedDate = $.datepicker.formatDate('dd/mm/yy', lastDayOfYear);
                $("#ngay_bc_DATE").val(formattedDate);
            });

            function onLoadData_tmp() {
                $("#idLoad_c3").click(); // Trigger the click event
            }
        </script>
    </body>
</html>
