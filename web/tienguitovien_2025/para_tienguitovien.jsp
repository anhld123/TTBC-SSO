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
            #containTree{
                width: 15%;
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
            .buttons {
                display: flex;
                gap: 10px;
                margin-top: 15px;
            }
            .buttons input[type="button"], .buttons input[type="submit"] {
                padding: 8px 16px;
                border: none;
                background: #029c44;
                color: white;
                border-radius: 6px;
                cursor: pointer;
                transition: background 0.3s;
            }
            .buttons input:hover {
                background: #027d36;
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
                    var isValid = true;
                    var ngaybc = document.getElementById('ngay_bc_DATE').value.trim();
                    var ngaybcParts = ngaybc.split('/');

                    var ngaybcDay = parseInt(ngaybcParts[0], 10);
                    var ngaybcMonth = parseInt(ngaybcParts[1], 10);
                    var ngaybcYear = parseInt(ngaybcParts[2], 10);

                    var currentDate = new Date();
                    var currentDay = currentDate.getDate();
                    var currentMonth = currentDate.getMonth() + 1; // JS month = 0-11
                    var currentYear = currentDate.getFullYear();

// Lấy ngày cuối cùng của tháng hiện tại
                    var lastDayOfCurrentMonth = new Date(currentYear, currentMonth, 0).getDate();

// Nếu hôm nay < ngày cuối tháng => cho nhập ngày cuối tháng trước
                    if (currentDay < lastDayOfCurrentMonth) {
                        var allowedMonth = currentMonth;
                        var allowedYear = currentYear;
                        if (allowedMonth === 0) {
                            allowedMonth = 12;
                            allowedYear--;
                        }
                        var lastDayOfAllowedMonth = new Date(allowedYear, allowedMonth, 0).getDate();

                        if (!(ngaybcDay === lastDayOfAllowedMonth && ngaybcMonth === allowedMonth && ngaybcYear === allowedYear)) {
                            alert("Hết hạn nhập dữ liệu, chọn tháng " + currentDate.getMonth() + " để thao tác!");
                            return;
                        }
                    } else {
                        // Hôm nay >= ngày cuối tháng => chỉ được nhập ngày cuối tháng kế tiếp - 1
                        var nextMonth = currentMonth + 1;
                        var nextYear = currentYear;
                        if (nextMonth > 12) {
                            nextMonth = 1;
                            nextYear++;
                        }

                        var lastDayOfNextMonth = new Date(nextYear, nextMonth, 0).getDate();
                        var allowedDay = lastDayOfNextMonth - 1;

                        if (!(ngaybcDay === allowedDay && ngaybcMonth === nextMonth && ngaybcYear === nextYear)) {
                            alert("Chỉ được nhập ngày trước ngày cuối của tháng kế tiếp!");
                            return;
                        }
                    }
                    var chot = document.getElementById("chotsl").value;
                    if (chot === "2" || chot === "1") {
                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px ; font-weight: bold'> Cảnh báo: TW đã khóa nhập dữ liệu!</h>");
                        return;
                    }
                    var chotcic = document.getElementById("chotcic").value;
                    if (chotcic === "2" || chotcic === "1") {
                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px ; font-weight: bold'> Cảnh báo: Dữ liệu đã chốt, không thể thao tác!</h>");
                        return;
                    }
                    if (isValid) {
                        var url, sdata;
                        url = "save_TGTV_2025.action";
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
                let aCheck = confirm("Bạn chắc chắn muốn chốt số liệu báo cáo của PGD?");
                if (aCheck) {
                    var chot = document.getElementById("chotsl").value;
                    if (chot === "2" || chot === "1") {
                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px ; font-weight: bold'> Cảnh báo: TW đã khóa nhập dữ liệu!</h>");
                        return;
                    }
                    var chotcic = document.getElementById("chotcic").value;
                    if (chotcic === "2" || chotcic === "1") {
                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px ; font-weight: bold'> Cảnh báo: Dữ liệu đã chốt, khoogn thể thao tác!</h>");
                        return;
                    }
                    var isValid = true;
                    if (isValid) {
                        var url, sdata;
                        url = "lock_TGTV_2025_c1.action";
                        sdata = jQuery("#frmdata").serialize();
                        $("#viewData").html('<img src="img/loading.gif"/>');
                        btnDisabled(1);
                        $.ajax({
                            type: "POST",
                            url: url,
                            data: sdata,
                            success: function (data) {
                                if (data === "200") {
                                    alert("Thành công: Chốt dữ liệu.");
                                    onLoadData();
                                } else {
                                    alert("Lỗi: Chốt dữ liệu.");
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

                    <s:if test="Grade.equalsIgnoreCase('1')">
                        &nbsp;Mã xã:
                        <s:select  style="width: 180px;"  list="lstMaxa" id="maxa" name="maxa" listKey="sKey" listValue="sDesc"
                                   onchange="reLoadValue(this.value)"></s:select>
                            &nbsp;Mã tổ:
                        <s:select  style="width: 180px;"  list="lstMato" id="mato" name="mato" listKey="sKey" listValue="sDesc"></s:select>
                        <s:select  id="mato_data" list="lstMato" listKey="sKey" listValue="sDesc" headerKey="-1"  headerValue="--- Chọn ---"                                        
                                   cssStyle="display:none;">
                        </s:select>
                    </s:if>
                    <s:if test="Grade.equalsIgnoreCase('3')">
                        &nbsp; Mã chi nhánh: 
                        <select id="lstCN"  name="lstCN">
                            <option value="000000">----Chọn mã chi nhánh----</option>
                            <s:iterator value="lstCN_API">
                                <option value="<s:property value="branchCode"/>"><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>               
                            </s:iterator>
                        </select> 
                    </s:if>
                    <s:if test="Grade.equalsIgnoreCase('1')">
                        &nbsp;<a style="color: #0000FF; font-weight: bold">Danh sách KH đã hoàn thành</a>
                        <input type="hidden" name="txtGetData" value="0" />
                        <input type="checkbox" onclick="$(this).val(this.checked ? 1 : 0)" 
                               oninput="onSelectChange_dnht1(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                               id="txtGetData"  name="txtGetData"/>      
                    </s:if>
                    &nbsp;<input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                    <s:if test="Grade.equalsIgnoreCase('1')">
                        &nbsp;<input type="button" id="idSave" value="Lưu dữ liệu"/> 
                        &nbsp;|&nbsp;<input style="color: red" type="button" id="idSend" value="Chốt dữ liệu PGD"/> 
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
            <s:if test="khoa_nhaptaycn.equalsIgnoreCase('TGTV_2025')">
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
                function updateDatepicker() {
                    var datepicker = $('#ngay_bc_DATE');
                    var currentDate = new Date();
                    var year = currentDate.getFullYear();
                    var month = currentDate.getMonth(); // Tháng hiện tại (0-based)

                    // Tính ngày cuối cùng của tháng trước
                    var lastDayOfPreviousMonth = new Date(year, month, 0);
                    var formattedDate = ('0' + lastDayOfPreviousMonth.getDate()).slice(-2) + '/' +
                            ('0' + (lastDayOfPreviousMonth.getMonth() + 1)).slice(-2) + '/' +
                            lastDayOfPreviousMonth.getFullYear();

                    // Đặt giá trị mặc định cho datepicker
                    datepicker.val(formattedDate);

                    // Cập nhật cấu hình datepicker để chỉ cho phép chọn ngày cuối cùng của các tháng
                    datepicker.datepicker("option", {
                        beforeShowDay: function (date) {
                            var lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0);
                            var isLastDay = date.getTime() === lastDay.getTime();
                            return [isLastDay, ""];
                        }
                    });
                }

                // Khởi tạo datepicker
                $('#ngay_bc_DATE').datepicker({
                    dateFormat: 'dd/mm/yy'
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
            $(document).ready(function () {
                $('#mato').on('change', function () {
                    const mato = $("#mato").val();
                    if (mato === '000000_0000000') {
                        $('#idSaveLock').show();
                    } else {
                        $('#idSaveLock').hide();
                    }
                });

                // Kiểm tra giá trị ban đầu nếu đã được chọn sẵn
                if ($('#mato').val() === '000000_0000000') {
                    $('#idSaveLock').show();
                }
            });

            $(document).ready(function () {
                $('#txtGetData').on('change', function () {
                    const txtGetData = $("#txtGetData").val();
                    if (txtGetData === '0') {
                        $('#idSave').show();
                    } else {
                        $('#idSave').hide();
                    }
                });
                // Kiểm tra giá trị ban đầu nếu đã được chọn sẵn
                if ($('#txtGetData').val() === '0') {
                    $('#idSave').show();
                }
            });
        </script>         
    </body>
</html>
