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
                $("#loadData")[0].click();
                // Thực hiện lần click thứ hai sau 100ms
//                setTimeout(function () {
//                    $("#loadData")[0].click();
//                }, 0, 00001);
                bsubmit = true;
            }

            $("#idSave").click(function () {
                var testValue = $("s\\:if").attr("test");
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();
                var khoa = document.getElementById('khoa_tdnn').value;
                var macb = document.getElementById('cboCanBo').value;
                var checkD50 = document.getElementById('id_D50').value;
                var mapgd = document.getElementById('lstPGD').value;
                var ngaybc = document.getElementById('ngay_bc_DATE').value;
                var currentDate = new Date();
                var day = currentDate.getDate();
                var month = currentDate.getMonth() + 1;
                var year = currentDate.getFullYear();
                // Đảm bảo ngày và tháng hiển thị đúng format
                if (day < 10) {
                    day = '0' + day;
                }
                if (month < 10) {
                    month = '0' + month;
                }
                var formattedDate = day + '/' + month + '/' + year;
                var ngaybcParts = ngaybc.split('/');
                var ngaybcDay = parseInt(ngaybcParts[0], 10);
                var ngaybcMonth = parseInt(ngaybcParts[1], 10);
                var ngaybcYear = parseInt(ngaybcParts[2], 10);
//                alert(checkD50);
                let checkedCount = countCheckedItem();
                if (checkedCount === 0) {
                    $('#message_suc_err').html("<h style='color: red; font-size: 13px ; font-weight: bold'>Bạn chưa chọn bản ghi để lưu!</h>");
                    return;
                } else if (ngaybcYear < year || (ngaybcYear === year && ngaybcMonth < month))
                {
                    $('#message_suc_err').html("<h style='color: red; font-size: 13px ; font-weight: bold'>Hết thời gian lưu!</h>");
                    return;
                } else {
                    let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                    if (aCheck) {
                        var table = document.getElementById("subTable");
                        var rowcount = table.rows.length;
                        var isValid = true;
                        for (var i = 0; i < rowcount; i++) {
                            try {
                                if (macb === "000000")
                                {
                                    alert('Vui lòng chọn cán bộ kiểm tra');
                                    document.getElementById("cboCanBo").style.backgroundColor = "#EEAFA6";
                                    return;
                                }
                                if (checkD50 === "2")
                                {
                                    alert('Đơn vị đã gửi phê duyệt, không thể chỉnh sửa dữ liệu');
                                    return;
                                }
                                if (mapgd === "000000")
                                {
                                    alert('Vui lòng chọn phòng giao dịch');
                                    return;
                                }
                            } catch (e) {
                            }
                        }
                        if (isValid) {
                            var url, sdata;
                            url = "save_" + khoa + ".action";
                            sdata = jQuery("#frmdata").serialize();
                            $("#viewData").html('<img src="img/loading.gif"/>');
                            btnDisabled(1);
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: sdata,
                                success: function (data) {
//                                    if (data.length !== null) {
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
                }
            });

            $("#idUnlock").click(function () {
                var testValue = $("s\\:if").attr("test");
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();
                var khoa = document.getElementById('khoa_tdnn').value;
                var macb = document.getElementById('cboCanBo').value;
                var checkD50 = document.getElementById('id_D50').value;
                var mapgd = document.getElementById('lstPGD').value;
//                alert(checkD50);
                var ngaybc = document.getElementById('ngay_bc_DATE').value;
                var currentDate = new Date();
                var day = currentDate.getDate();
                var month = currentDate.getMonth() + 1;
                var year = currentDate.getFullYear();
                // Đảm bảo ngày và tháng hiển thị đúng format
                if (day < 10) {
                    day = '0' + day;
                }
                if (month < 10) {
                    month = '0' + month;
                }
                var formattedDate = day + '/' + month + '/' + year;
                var ngaybcParts = ngaybc.split('/');
                var ngaybcDay = parseInt(ngaybcParts[0], 10);
                var ngaybcMonth = parseInt(ngaybcParts[1], 10);
                var ngaybcYear = parseInt(ngaybcParts[2], 10);
                let checkedCount = countCheckedItem();
                if (checkedCount === 0) {
                    $('#message_suc_err').html("<h style='color: red; font-size: 13px ; font-weight: bold'>Bạn chưa chọn bản ghi để lưu!</h>");
                    return;
                } else if (ngaybcYear < year || (ngaybcYear === year && ngaybcMonth < month))
                {
                    $('#message_suc_err').html("<h style='color: red; font-size: 13px ; font-weight: bold'>Hết thời gian mở khóa!</h>");
                    return;
                } else {
                    let aCheck = confirm("Bạn chắc chắn muốn mở số liệu báo cáo ?");
                    if (aCheck) {
                        var table = document.getElementById("subTable");
                        var rowcount = table.rows.length;
                        var isValid = true;
                        for (var i = 0; i < rowcount; i++) {
                            try {
                                if (macb === "000000")
                                {
                                    alert('Vui lòng chọn cán bộ kiểm tra');
                                    document.getElementById("cboCanBo").style.backgroundColor = "#EEAFA6";
                                    return;
                                }
                                if (checkD50 === "1")
                                {
                                    alert('Đơn vị chưa phê duyệt, không thể chỉnh sửa dữ liệu');
                                    return;
                                }
                                if (mapgd === "000000")
                                {
                                    alert('Vui lòng chọn phòng giao dịch');
                                    return;
                                }
                            } catch (e) {
                            }
                        }
                        if (isValid) {
                            var url, sdata;
                            url = "unlock_" + khoa + ".action";
                            sdata = jQuery("#frmdata").serialize();
                            $("#viewData").html('<img src="img/loading.gif"/>');
                            btnDisabled(1);
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: sdata,
                                success: function (data) {
//                                    if (data.length !== null) {
                                    if (data === "200") {
                                        alert("Thành công: Mở khóa dữ liệu.");
                                        $('#message_suc_err').html("<h style='color: green; font-size: 13px ; font-weight: bold'>Bạn đã mở khóa dữ liệu thành công!</h>");
                                        onLoadData();
                                    } else {
                                        alert("Lỗi: Mở khóa dữ liệu.");
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
                }
            });

            $("#idPheduyet").click(function () {
                var testValue = $("s\\:if").attr("test");
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();
                var khoa = document.getElementById('khoa_tdnn').value;
                var macb = document.getElementById('cboCanBo').value;
                var checkD50 = document.getElementById('id_D50').value;
                var mapgd = document.getElementById('lstPGD').value;
                var ngaybc = document.getElementById('ngay_bc_DATE').value;
                var currentDate = new Date();
                var day = currentDate.getDate();
                var month = currentDate.getMonth() + 1;
                var year = currentDate.getFullYear();
                // Đảm bảo ngày và tháng hiển thị đúng format
                if (day < 10) {
                    day = '0' + day;
                }
                if (month < 10) {
                    month = '0' + month;
                }
                var formattedDate = day + '/' + month + '/' + year;
                var ngaybcParts = ngaybc.split('/');
                var ngaybcDay = parseInt(ngaybcParts[0], 10);
                var ngaybcMonth = parseInt(ngaybcParts[1], 10);
                var ngaybcYear = parseInt(ngaybcParts[2], 10);
//                alert(checkD50);
                let checkedCount = countCheckedItem();
                if (checkedCount === 0) {
                    $('#message_suc_err').html("<h style='color: red; font-size: 13px ; font-weight: bold'>Bạn chưa chọn bản ghi để lưu!</h>");
                    return;
                } else if (ngaybcYear < year || (ngaybcYear === year && ngaybcMonth < month))
                {
                    $('#message_suc_err').html("<h style='color: red; font-size: 13px ; font-weight: bold'>Hết thời gian phê duyệt!</h>");
                    return;
                } else {
                    let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                    if (aCheck) {
                        var table = document.getElementById("subTable");
                        var rowcount = table.rows.length;
                        var isValid = true;
                        for (var i = 0; i < rowcount; i++) {
                            try {
                                if (macb === "000000")
                                {
                                    alert('Vui lòng chọn cán bộ kiểm tra');
                                    document.getElementById("cboCanBo").style.backgroundColor = "#EEAFA6";
                                    return;
                                }
                                if (checkD50 === "2")
                                {
                                    alert('Đơn vị đã gửi phê duyệt, không thể chỉnh sửa dữ liệu');
                                    return;
                                }
                                if (mapgd === "000000")
                                {
                                    alert('Vui lòng chọn phòng giao dịch');
                                    return;
                                }
                            } catch (e) {
                            }
                        }
                        if (isValid) {
                            var url, sdata;
                            url = "send_" + khoa + ".action";
                            sdata = jQuery("#frmdata").serialize();
                            $("#viewData").html('<img src="img/loading.gif"/>');
                            btnDisabled(1);
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: sdata,
                                success: function (data) {
//                                    if (data.length !== null) {
                                    if (data === "200") {
                                        alert("Thành công: Gửi dữ liệu.");
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
                }
            });

            function btnDisabled(status) {
                if (status === 1) {
                    $("#loadDatatmp").prop('disabled', true);
                    $("#idPheduyet").prop('disabled', true);
                    $("#idSave").prop('disabled', true);
                } else {
                    $("#idPheduyet").prop('disabled', false);
                    $("#idSave").prop('disabled', false);
                    $("#loadDatatmp").prop('disabled', false);
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
                var idform = 'id_' + '<s:property value="khoa_tdnn"/>';
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
                var idform = 'idform_open_' + '<s:property value="khoa_tdnn"/>';
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
//                document.getElementById("labelPageNumber").style.visibility = "hidden";
//                document.getElementById("pageNumber").style.visibility = "hidden";
            }
            ;
            function reLoadValueMaTo(val) {
//                if (val == "000000_NOGROUP")
//                {
//                    document.getElementById("labelPageNumber").style.visibility = "visible";
//                    document.getElementById("pageNumber").style.visibility = "visible";
//                } else
//                {
//                    document.getElementById("labelPageNumber").style.visibility = "hidden";
//                    document.getElementById("pageNumber").style.visibility = "hidden";
//                }

            }
            ;
            var popWindow;
            var max_row = 0;
            $(document).ready(function () {
                initTable();
            });

            function subString(input) {
                var _index = input.indexOf("_");
                var _result = input.substring(_index + 1, input.length);
                return _result;
            }

            function onSelectChange() {
                let selectedValue = $('#lstCN').find(":selected").val();
                let province = selectedValue;//.substring(0, 4);
                //let province1 = selectedValue;//.substring(2, 4);

                $('#lstPGD' + ' option').each(function () {
                    $(this).remove();
                });
                $('#lstPGD_Temp option').each(function () {
                    if ($(this).val().startsWith(province)) {
                        $('#lstPGD').append($('<option>',
                                {
                                    value: subString($(this).val()),
                                    text: $(this).text()
                                }));
                    }
                });

                let province1 = $('#lstPGD').find(":selected").val();

                $('#lstXa' + ' option').each(function () {
                    $(this).remove();
                    //}
                });

                $('#lstXa_Temp option').each(function () {
                    if ($(this).val().startsWith(province1)) {
                        $('#lstXa').append($('<option>',
                                {
                                    value: subString($(this).val()),
                                    text: $(this).text()
                                }));
                    }
                });
                var selectElement = document.getElementById("lstPGD");
                selectElement.setAttribute("onmousedown", "return true;");
                document.getElementById("lstPGD").style.backgroundColor = "#ffffff";
                document.getElementById("lstPGD").disabled = false;
            }

            function onSelectChangeXa() {
                let selectedValue = $('#lstPGD').find(":selected").val();
                let province = selectedValue;//.substring(2, 6);
                $('#lstXa' + ' option').each(function () {
                    $(this).remove();
                });
                $('#lstXa_Temp option').each(function () {
                    if ($(this).val().startsWith(province)) {
                        $('#lstXa').append($('<option>',
                                {
                                    value: subString($(this).val()),
                                    text: $(this).text()
                                }));
                    }
                });
                var selectElement = document.getElementById("lstXa");
                selectElement.setAttribute("onmousedown", "return true;");
                document.getElementById("lstXa").style.backgroundColor = "#ffffff";
                document.getElementById("lstXa").disabled = false;
            }
            function initTable() {
                var Grade = document.getElementById("Grade").value;
                if (Grade !== "1") {
                    document.getElementById("lstPGD").disabled = true;
                    document.getElementById("lstXa").disabled = true;
//                } else if (Grade === "2") {
//                    document.getElementById("lstXa").disabled = true;
                }
            }
        </script>
    </head>
    <!--new java.util.Date()-->
    <body>

        <s:form id="frmdata" name="frmdata" action="%{khoa_tdnn}" theme="simple">
            <s:hidden name="khoa_tdnn" id="khoa"/>
            <s:hidden name="ReportDate" id="ReportDate" value=""/>
            <s:hidden name="Grade" id="Grade"/>
            <s:hidden name="UserName" id="UserName"/>               
            <fieldset>
                <legend><b>Tìm kiếm dữ liệu</b></legend> 
                <table>
                    <div style="display: none;">
                        <input style="height: 10px">
                        <select id="lstPGD_Temp">
                            <option value="000000">----Chọn phòng giao dịch----</option>
                            <s:iterator value="lstPGD_API" status="ideRows" var="language">                                    
                                <option value="<s:property value="MainPos"/>_<s:property value="PosCode"/>"><s:property value="PosCode"/> - <s:property value="PosName"/></option>                                    
                            </s:iterator>
                        </select>
                        <s:if test="!Grade.equalsIgnoreCase('3')">
                            <select id="lstXa_Temp">
                                <option value="000000">----Chọn điểm giao dịch xã----</option>
                                <s:iterator value="lstPoint_API" status="ideRows" var="language">                                    
                                    <option value="<s:property value="PosCode"/>_<s:property value="communeId"/>"><s:property value="transactionPoint"/> - <s:property value="communeName"/></option>                                    
                                </s:iterator>
                            </select>
                        </s:if>
                        <s:else>
                            <select id="lstXa_Temp">
                                <option value="000000">----Chọn điểm giao dịch xã----</option>
                                <s:iterator value="lstXa_API" status="ideRows" var="language">                                    
                                    <option value="<s:property value="PosCode"/>_<s:property value="communeCode"/>">TXN<s:property value="communeCode"/> - <s:property value="communeName"/></option>                                    
                                </s:iterator>
                            </select> 
                        </s:else>
                    </div>
                    <tr>
                        <td>
                            <s:if test="khoa_tdnn.equalsIgnoreCase('01_TDNN_2024') || khoa_tdnn.equalsIgnoreCase('01_TDNN_2024_CBCT')">
                                &nbsp;<label>Ngày kiểm tra: </label>
                            </s:if>
                            <s:else>
                                &nbsp;<label>Ngày giám sát: </label>
                            </s:else>
                            <sj:datepicker name="ngay_bc_DATE" value="%{'31/12/2023'}"  id="ngay_bc_DATE" 
                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL" onChangeTopics="changeTopic"/> 

                            &nbsp;&nbsp;<label>Mã chi nhánh: </label>
                            <select id="lstCN" onchange="onSelectChange()" name="lstCN">
                                <option value="000000">----Chọn mã chi nhánh----</option>
                                <s:iterator value="lstCN_API">
                                    <option value="<s:property value="branchCode"/>"><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>               
                                </s:iterator>
                            </select>
                            &nbsp;&nbsp;<label>Mã PGD : </label>
                            <select id="lstPGD" onchange="onSelectChangeXa()" name="lstPGD">
                                <option value="000000">----Chọn phòng giao dịch----</option>
                                <s:iterator value="lstPGD_API">                                    
                                    <option value="<s:property value="PosCode"/>"><s:property value="PosCode"/> - <s:property value="PosName"/></option>                                    
                                </s:iterator>
                            </select>
                            &nbsp;&nbsp;<label>Điểm giao dịch xã: </label>
                            <select id="lstXa" name="lstXa">
                                <option value="000000">----Chọn điểm giao dịch xã----</option>
                                <s:iterator value="lstPoint_API">                                    
                                    <option value="<s:property value="communeId"/>"><s:property value="transactionPoint"/> - <s:property value="communeName"/></option>                                         
                                </s:iterator>   
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            &nbsp;<label>Trạng thái: </label> 
                            <input id="check_1" type="checkbox" onchange="checkChange(event)" name="check_1" class="myCheckBox"
                                   <s:if test="Grade.equalsIgnoreCase('1')"> disabled</s:if>> Xem/ Mở phê duyệt dữ liệu điểm giao dịch cấp <s:if test="!Grade.equalsIgnoreCase('3')">PGD</s:if><s:else> CN</s:else>
                                   &nbsp;&nbsp;<input id="check_2" type="checkbox" onchange="checkChange(event)"  checked
                                                      class="myCheckBox" name="check_2" <s:if test="Grade.equalsIgnoreCase('1')"> style="pointer-events: none"</s:if>> Nhập dữ liệu cấp kiểm tra
                                   <!--                            </td>
                                                               <td >-->
                            <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                       onBeforeTopics="beforediv_data"
                                       onCompleteTopics="completediv_data" cssStyle="display:none"/>
                            &nbsp;<input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                            &nbsp;<input type="button" id="idSave" value="Lưu dữ liệu"/> 
                            <s:if test="!Grade.equalsIgnoreCase('3')">
                                &nbsp;<input type="button" id="idPheduyet" value="Gửi dữ liệu"/></s:if>
                            <s:if test="!Grade.equalsIgnoreCase('1')">
                                <input type="button" id="idUnlock" value="Mở phê duyệt"/></s:if>
                                <!--                        &nbsp;<input type="button" id="idSaveLock" value="Phê duyệt"/> 
                                                        &nbsp;<input type="button" id="idDelete" style="color: red" value="Xóa dữ liệu"/>  -->
                            </td>  </tr>
                    </table>    
                </fieldset>
                <div id="loadingImageDiv_data" style="margin-left: 20px;display: none;" >
                    <img id="loadingImage" src='img/loading.gif' border='0' >                  
                </div>   
                <div id="message_suc_err"></div>
            <s:if test="khoa_tdnn.equalsIgnoreCase('01_TDNN_2024') || khoa_tdnn.equalsIgnoreCase('04_TDNN_2024')
                  || khoa_tdnn.equalsIgnoreCase('01_TDNN_2024_CBCT')|| khoa_tdnn.equalsIgnoreCase('04_TDNN_2024_CBCT')">
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
                    <div id="divExportReport"></div>
                </div>
            </s:else>                     

        </s:form>

        <script>
            $(document).ready(function () {
                var currentDate = new Date();
                var day = currentDate.getDate();
                var month = currentDate.getMonth() + 1; // Note: January is 0
                var year = currentDate.getFullYear();
                var formattedDate = day + '/' + month + '/' + year;
                document.getElementById('ngay_bc_DATE').value = formattedDate;
            });

            function checkChange(event) {
                var Grade = document.getElementById('Grade').value;
                var check_1 = document.getElementById('check_1');
                var check_2 = document.getElementById('check_2');
                var idUnlock = document.getElementById('idUnlock');
                var idPheduyet = document.getElementById('idPheduyet');
                var idSave = document.getElementById('idSave');

                if (Grade !== "1") {
                    if (event.target.id === "check_1") {
                        if (check_1.checked) {
                            check_2.checked = false;
                            idSave.style.display = 'none';
                            idUnlock.style.display = 'inline';
                            if (Grade !== "3") {
                                idPheduyet.style.display = 'none';
                            }
                        } else {
                            check_2.checked = true;
                            idSave.style.display = 'inline';
                            idUnlock.style.display = 'none';
                            if (Grade !== "3") {
                                idPheduyet.style.display = 'inline';
                            }
                        }
                    } else if (event.target.id === "check_2") {
                        if (check_2.checked) {
                            check_1.checked = false;
                            idSave.style.display = 'inline';
                            if (Grade !== "3") {
                                idPheduyet.style.display = 'inline';
                            }
                            idUnlock.style.display = 'none';

                        } else {
                            check_1.checked = true;
                            idSave.style.display = 'none';
                            idUnlock.style.display = 'inline';
                            if (Grade !== "3") {
                                idPheduyet.style.display = 'none';
                            }

                        }
                    }
                }
            }
            $(function () {
                $('#select-all').click(function (event) {
                    // Iterate each checkbox
                    $('.myCheckBox').each(function () {
                        if (!this.disabled) {
                            this.checked = $('#select-all').prop('checked');
                            this.value = this.checked ? '1' : '2';
                        }
                    });
                });
            });


            function hideElement() {
                var Grade = document.getElementById('Grade').value;
//                alert(check_1);
                if (Grade !== "1") {
                    document.getElementById('idUnlock').style.display = 'none';
                }

            }
            hideElement();
        </script>
    </body>
</html>
