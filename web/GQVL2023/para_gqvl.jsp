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
//                window.alert($("#khoa_nhaptaycn").val());
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
                    var rowcount = table.rows.length;
                    var isValid = true;
                    var chot = document.getElementById("chotsl").value;
                    if (chot === "2" || chot === "1") {
                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px ; font-weight: bold'> Cảnh báo: Dữ liệu đã gửi, không thể chỉnh sửa!</h>");
                        return;
                    }
                    for (var i = 0; i < rowcount; i++) {
                        try {
                            // Function to process value
                            function processValue(value) {
                                return parseFloat(value.replaceAll(',', '')) || 0; // Convert to number and handle empty values
                            }

                            // Retrieve and process values
                            var CT_D1 = processValue(document.getElementById("D1_" + i).value);
                            var CT_D5 = processValue(document.getElementById("D7_" + i).value);
                            var CT_D7 = processValue(document.getElementById("D10_" + i).value);
                            var CT_D9 = processValue(document.getElementById("D13_" + i).value);
                            var CT_D11 = processValue(document.getElementById("D16_" + i).value);
                            var CT_D13 = processValue(document.getElementById("D19_" + i).value);
                            var CT_D15 = processValue(document.getElementById("D22_" + i).value);
                            var CT_D17 = processValue(document.getElementById("D25_" + i).value);
                            var CT_D19 = processValue(document.getElementById("D28_" + i).value);

                            // Calculate the total
                            var total = CT_D5 + CT_D7 + CT_D9 + CT_D11 + CT_D13 + CT_D15 + CT_D17 + CT_D19;

                            // Format numbers with thousands separator
                            var t1 = new Intl.NumberFormat('en-US').format(CT_D1);
                            var t2 = new Intl.NumberFormat('en-US').format(total);

                            if (CT_D1 !== total) {
                                $('#message_suc_err').html("<h style='color: red; font-size: 13px ; font-weight: bold'> Thất bại: Tổng số trích 10% trong tháng là " + t2 + " không khớp số tiền Hạch toán là " + t1 + ")!</h>");
                                return;
                            }

                        } catch (e) {
                        }
                    }
                    if (isValid) {
                        var url, sdata;
                        url = "save_GQVL_01.action";
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

            $("#idSend").click(function () {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();
                let aCheck = confirm("Bạn chắc chắn muốn gửi số liệu báo cáo ?");
                if (aCheck) {
                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var chot = document.getElementById("chotsl").value;
                    var capbc = document.getElementById("Grade").value;
                    var isValid = true;
                    if (chot === "2" || chot === "1") {
                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px ; font-weight: bold'> Cảnh báo: Dữ liệu đã gửi, không thể tiếp tục gửi!</h>");
                        return;
                    }
                    for (var i = 0; i < rowcount; i++) {
                        try {
                            // Function to process value
                            function processValue(value) {
                                return parseFloat(value.replaceAll(',', '')) || 0; // Convert to number and handle empty values
                            }

                            // Retrieve and process values
                            var CT_D1 = processValue(document.getElementById("D1_" + i).value);
                            var CT_D5 = processValue(document.getElementById("D7_" + i).value);
                            var CT_D7 = processValue(document.getElementById("D10_" + i).value);
                            var CT_D9 = processValue(document.getElementById("D13_" + i).value);
                            var CT_D11 = processValue(document.getElementById("D16_" + i).value);
                            var CT_D13 = processValue(document.getElementById("D19_" + i).value);
                            var CT_D15 = processValue(document.getElementById("D22_" + i).value);
                            var CT_D17 = processValue(document.getElementById("D25_" + i).value);
                            var CT_D19 = processValue(document.getElementById("D28_" + i).value);
                            // Calculate the total
                            var total = CT_D5 + CT_D7 + CT_D9 + CT_D11 + CT_D13 + CT_D15 + CT_D17 + CT_D19;
                            // Format numbers with thousands separator
                            var t1 = new Intl.NumberFormat('en-US').format(CT_D1);
                            var t2 = new Intl.NumberFormat('en-US').format(total);
                            if (CT_D1 !== total && Grade === "1") {
                                $('#message_suc_err').html("<h style='color: red; font-size: 13px ; font-weight: bold'> Thất bại: Tổng số trích 10% trong tháng là " + t2 + " không khớp số tiền Hạch toán là " + t1 + ")!</h>");
                                return;
                            }

                        } catch (e) {
                        }
                    }

                    if (isValid) {
                        var url, sdata;
                        url = "send_GQVL_01.action";
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
                                } else if (data === "1") {
                                    $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px ; font-weight: bold'> Cảnh báo: Lưu dữ liệu trước khi gửi!</h>");
                                    return;
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
                    Ngày báo cáo: 
                    <sj:datepicker name="ngay_bc_DATE" value="%{'31/12/2023'}"  id="ngay_bc_DATE" 
                                   placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL" onChangeTopics="changeTopic"/> 
                    <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                               onBeforeTopics="beforediv_data"
                               onCompleteTopics="completediv_data" cssStyle="display:none"/>
                    <s:if test="Grade.equalsIgnoreCase('3')">
                        &nbsp; Mã chi nhánh: 
                        <select id="lstCN"  name="lstCN">
                            <option value="000000">----Chọn mã chi nhánh----</option>
                            <s:iterator value="lstCN_API">
                                <option value="<s:property value="branchCode"/>"><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>               
                            </s:iterator>
                        </select> 
                    </s:if>
                    &nbsp;<input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                    <s:if test="Grade.equalsIgnoreCase('1')">
                        &nbsp;<input type="button" id="idSave" value="Lưu dữ liệu"/> 
                    </s:if>
                    <s:if test="!Grade.equalsIgnoreCase('3')">
                        &nbsp;|&nbsp;<input type="button" id="idSend" value="Gửi dữ liệu" style="color: red"/>
                    </s:if>
                    <a id="message_suc_err"/>
                </table>     
            </fieldset>
            <div id="loadingImageDiv_data" style="margin-left: 20px;display: none;" >
                <img id="loadingImage" src='img/loading.gif' border='0' >                  
            </div>   

            <div id="containParm_full" align="center">
                <div id="divExportReport"></div>
                <div align="right"  id="divExportReportLink"></div>
            </div>
            <s:if test="khoa_nhaptaycn.equalsIgnoreCase('GQVL_01')">
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
//            $(document).ready(function () {
//                function updateDatepicker() {
//                    var datepicker = $('#ngay_bc_DATE');
//                    var currentDate = new Date();
//                    var year = currentDate.getFullYear();
//                    var lastDayOfYear = new Date(year, 11, 31);
//                    var formattedDate = ('0' + lastDayOfYear.getDate()).slice(-2) + '/' +
//                            ('0' + (lastDayOfYear.getMonth() + 1)).slice(-2) + '/' +
//                            lastDayOfYear.getFullYear();
//
//                    datepicker.val(formattedDate);
//
//                    datepicker.datepicker("option", {
//                        beforeShowDay: function (date) {
//                            return [date.getDate() === 31 && date.getMonth() === 11, ""];
//                        }
//                    });
//
//                }
//
//                // Initialize the datepicker with the default settings
//                updateDatepicker();
//            });
            $(document).ready(function () {
                function updateDatepicker() {
                    var datepicker = $('#ngay_bc_DATE');
                    var currentDate = new Date();
                    var year = currentDate.getFullYear();
                    var month = currentDate.getMonth(); // Tháng hiện tại (0-based)

                    // Tính ngày cuối cùng của tháng hiện tại
                    var lastDayOfMonth = new Date(year, month + 1, 0);
                    var formattedDate = ('0' + lastDayOfMonth.getDate()).slice(-2) + '/' +
                            ('0' + (lastDayOfMonth.getMonth() + 1)).slice(-2) + '/' +
                            lastDayOfMonth.getFullYear();
                    // Đặt giá trị mặc định cho datepicker
                    datepicker.val(formattedDate);
                    // Cập nhật cấu hình datepicker để chỉ cho phép chọn ngày cuối cùng của tháng
                    datepicker.datepicker("option", {
                        beforeShowDay: function (date) {
                            var lastDayOfShownMonth = new Date(date.getFullYear(), date.getMonth() + 1, 0);
                            return [date.getTime() === lastDayOfShownMonth.getTime(), ""];
                        }
                    });
                }

                // Khởi tạo datepicker với cấu hình mới
                $('#ngay_bc_DATE').datepicker({
                    dateFormat: 'dd/mm/yy' // Định dạng ngày
                });
                // Gọi hàm cập nhật datepicker
                updateDatepicker();
            });
            function callDirectLink(link) {
                PopupCenter(link, 'Upload excel', 800, 400);
            }
            function PopupCenter(pageURL, title, w, h) {
                var left = (screen.width / 2) - (w / 2);
                var top = (screen.height / 2) - (h / 2);
                var targetWin = window.open(pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
                return targetWin;
            }

        </script>
    </body>
</html>
