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
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();

                let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                if (aCheck) {
                    var table = document.getElementById("subTable");
                    var sthangbc = document.getElementById("monthSelect").value.padStart(2, '0');
                    var snambc = document.getElementById("yearSelect").value;
                    var stoday = document.getElementById("stoday").value;
                    var sparts = stoday.split('/');
                    var currentYear = sparts[2];
                    var currentMonth = sparts[1];
                    var rowcount = table.rows.length;
                    var isValid = true;
                    var chot = document.getElementById("chotsl").value;
//                    var chot_tw = document.getElementById("chotsl_tw").value;
                    if (snambc.toString() < currentYear.toString())
                    {
                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Chức năng chỉ lưu tại năm hiện tại " + currentYear + "</h>");
                        return;
                    }
                    if (snambc.toString() === currentYear.toString() && sthangbc.toString() !== currentMonth.toString()) {
                        window.alert(snambc + " " + currentYear + " " + sthangbc + " " + currentMonth);
                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Chỉ được phép chỉnh sửa dữ liệu tháng " + currentMonth + "</h>");
                        return;
                    }
                    if (chot === "2") {
                        alert("Chi nhánh đã chốt dữ liệu lên Tw!");
                        isValid = false; // Không cho phép lưu dữ liệu
                        onLoadData();
                    }
                    if (chot === "1") {
                        alert("Dữ liệu đã gửi, không thể lưu!");
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
                        url = "save_KTKSNB_02_2024.action";
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
                    $("#idPheduyet").prop('disabled', true);
                    $("#idSave").prop('disabled', true);
                    $("#idSaveLock").prop('disabled', true);
                    $("#idDelete").prop('disabled', true);
                } else {
                    $("#idPheduyet").prop('disabled', false);
                    $("#idSave").prop('disabled', false);
                    $("#loadDatatmp").prop('disabled', false);
                    $("#idSaveLock").prop('disabled', false);
                    $("#idDelete").prop('disabled', false);
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
                    var khoa_nhaptaycn = $("#khoa_nhaptaycn").val();

                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var chot = document.getElementById("chotsl").value;
                    var sthangbc = document.getElementById("monthSelect").value.padStart(2, '0');
                    var snambc = document.getElementById("yearSelect").value;
                    var stoday = document.getElementById("stoday").value;
                    var sparts = stoday.split('/');
                    var currentYear = sparts[2];
                    var currentMonth = sparts[1];
                    var rowcount = table.rows.length;
                    var isValid = true;
                    var chot = document.getElementById("chotsl").value;
//                    var chot_tw = document.getElementById("chotsl_tw").value;
//                    if (snambc.toString() !== currentYear.toString())
//                    {
//                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Chức năng chỉ lưu tại năm hiện tại " + currentYear + "</h>");
//                        return;
//                    }
//                    if (snambc.toString() === currentYear.toString() && sthangbc.toString() !== currentMonth.toString()) {
////                        window.alert(snambc + " " + currentYear + " " + sthangbc + " " + currentMonth);
//                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Chỉ được phép chỉnh sửa dữ liệu tháng " + currentMonth + "</h>");
//                        return;
//                    }
                    if (chot === "2") {
                        alert("Chi nhánh đã chốt dữ liệu lên Tw!");
                        isValid = false; // Không cho phép lưu dữ liệu
                        onLoadData();
                    }
                    if (chot === "1") {
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
                        if (khoa_nhaptaycn === "KTKSNB_02")
                        {
                            url = "send_KTKSNB_02_2024.action";
                        } else {
                            url = "send_KTKSNB_03_2024.action";
                        }
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
                                } else if (data === "1") {
                                    alert("Lỗi: Không có kế hoạch để gửi!");

                                } else {
                                    alert("Lỗi: Gửi dữ liệu.");

                                }
                                onLoadData();
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

            $("#idSendTW").click(function () {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();

                let aCheck = confirm("Bạn chắc chắn muốn gửi số liệu báo cáo ?");
                if (aCheck) {
                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var chot_tw = document.getElementById("chotsl_tw").value;
                    var isValid = true;
                    if (chot_tw === "2") {
                        alert("Dữ liệu đã gửi, không thể tiếp tục gửi.");
                        isValid = false; // Không cho phép lưu dữ liệu
                        onLoadData();
                    }
//                    if (chot === "10") {
//                        alert("Lưu dữ liệu để gửi!.");
//                        isValid = false; // Không cho phép lưu dữ liệu
//                        onLoadData();
//                    }
                    for (var i = 0; i < rowcount; i++) {
                        try {
                        } catch (e) {
                        }
                    }
                    if (isValid) {
                        var url, sdata;
                        url = "send_KPBL_2024_C2.action";
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
                    <s:iterator value="lstDmKhac">      
                        <input type="hidden" id="stoday" value="<s:property  value="value" />" name="stoday"/>                                
                    </s:iterator>  
                    <label style="font-weight: bold">&nbsp; Tháng </label>
                    <select id="monthSelect" name="monthSelect">
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
                    <s:if test="Grade.equalsIgnoreCase('2')">
                        <label id="title21" style="font-weight: bold">&nbsp; Nghiệp vụ </label>
                        <select name="txtGetData" id="txtGetData" onchange="toggleButton()">                                                    
                            <option value="1">1. Nhập dữ liệu cấp tỉnh</option>                                                    
                            <option value="2">2. Tình trạng nhập dữ liệu PGD</option>
                        </select> 
                    </s:if>
                    <s:if test="Grade.equalsIgnoreCase('3')">
                        <label id="title31" style="font-weight: bold">&nbsp; Mã chi nhánh: </label>
                        <select id="lstCN"  name="lstCN">
                            <option value="000000">----Chọn mã chi nhánh----</option>
                            <s:iterator value="lstCN_API">
                                <option value="<s:property value="branchCode"/>"><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>               
                            </s:iterator>
                        </select> 
                    </s:if>
                    <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                               onBeforeTopics="beforediv_data"
                               onCompleteTopics="completediv_data" cssStyle="display:none"/>
                    &nbsp;<input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                    <s:if test="!Grade.equalsIgnoreCase('3')">
                        &nbsp;<input type="button" id="idSend" value="Chốt dữ liệu" style="color: red"/></s:if>
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
                    document.getElementById("idSend").style.display = "inline";
                    onLoadData();
                } else {
                    document.getElementById("idSend").style.display = "none";
                    onLoadData();
                }
            </s:if>
            }
        </script>
    </body>
</html>
