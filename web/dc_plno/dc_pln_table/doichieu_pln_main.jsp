
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <style>

            .hidden-inline {
                display: none;
            }
            .inline-block {
                display: inline-block;
                vertical-align: middle;
            }
            body,td,th,font{ font-family:Tahoma; font-size:12px; }

            #container{
                width: 100%;
                height: 500px;
                border: 0px solid;
                padding-left: 0px;         
            }

            #containTree{
                width: 15%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 500px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 98%;
                height: 500px;
                /*padding-left: 20px;*/
                float: left;
                /*overflow: scroll;*/
            }
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: 11px;
            }
            .report_group_form{
                width: 100%;
            }

            #navParam{
                height: 65px;
                padding:3px;
                border: 1px solid;                
            }
            #navParam2{
                height: 65px;
                margin-left: 10px;
                font-weight: bold;
            }
            #Input{
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                padding: 3px 0px 3px 3px;
                margin: 5px 1px 3px 0px;
                border: 1px solid rgba(81, 203, 238, 1);
            }
            #divSearch
            {
                height: 22px;
                border: 1px solid;     
                /*width: 40%;*/
                float: left;
                padding:1px; 
                border: 1px solid;
                position: fixed;
            }
            input[type="text"]
            {
                width: 100%;
                border-color: #18ab29;
                background: #F9F9F9;
                color:#666666;
            }
            input[type=text]:focus, textarea:focus {
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                border: 1px solid rgba(81, 203, 238, 1);
            }

            .buttons {
                display: flex;
                padding: 5px;
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
            td s\:label,
            td label {
                display: inline-block;
                vertical-align: middle;
                line-height: 28px; /* hoặc khớp với chiều cao của input/select */
                margin-right: 5px;
            }
            .ui-datepicker-trigger {
                height: auto !important;
                vertical-align: middle;
                padding: 2px;
                margin-left: 4px;
                max-height: 20px; /* hoặc điều chỉnh nhỏ hơn nếu cần */
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
//                    console.log("vào 1");
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
                                var plnKKntnSodu = document.getElementById("D2_" + i).value;
                                var ngnhanKntn = document.getElementById("D3_" + i).value;
                                var checkrow = document.getElementById("checkrow_" + i).value;
                                if (checkrow === "1" && plnKKntnSodu !== "0" && ngnhanKntn === "0")
                                {
                                    alert("Bạn chưa chọn nguyên nhân!");
                                    document.getElementById("D3_" + i).style.backgroundColor = "#EEAFA6";
                                    isValid = false;
                                    break;
                                }
                            } catch (e) {
                            }
                        }

                        if (isValid) {
                            var url, sdata;
                            url = "saveDataDcPLN.action";
                            sdata = jQuery("#frmdata").serialize();
//                                 console.log("data = " + sdata);
                            $("#viewData").html('<img src="img/loading.gif"/>');
                            btnDisabled(1);
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: sdata,
                                success: function (data) {
                                    if (data === "200") {
                                        alert("Thành công: Lưu dữ liệu.");
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
            $("#idSend").click(function () {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();

                let aCheck = confirm("Bạn chắc chắn muốn gửi số liệu báo cáo ?");
                if (aCheck) {
                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var chot = document.getElementById("chotsl").value;
                    var chot_tw = document.getElementById("chotsl_tw").value;
                    var isValid = true;
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
                    if (chot_tw === "0") {
                        alert(chot_tw);
                        alert("Chưa có dữ liệu, đề nghị lưu dữ liệu trước khi gửi, ít nhất phải chọn 1 cán bộ chuyên trách!");
                        isValid = false; // Không cho phép lưu dữ liệu
                    }
                    for (var i = 0; i < rowcount; i++) {
                        try {
                        } catch (e) {
                        }
                    }
                    if (isValid) {
                        var url, sdata;
                        url = "send_KTKSNB_00_2024.action";
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
                    <tr>
                        <td>
                            <s:label value="Ngày báo cáo " cssStyle="color: #029c44;" />
                            <sj:datepicker name="ngay_bc_DATE" value="%{'31/12/2023'}"  id="ngay_bc_DATE" 
                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL" onChangeTopics="changeTopic"/> 

                            <s:if test="Grade.equalsIgnoreCase('1')">
                                &nbsp;<s:label value="Mã xã " cssStyle="color: #029c44;" />
                                <s:select  style="width: 120px;"  list="lstMaxa" id="maxa" name="maxa" listKey="sKey" listValue="sDesc"
                                           onchange="onXaChange(this.value)"></s:select>
                                &nbsp;<s:label value="Mã hội " cssStyle="color: #029c44;" />
                                <select id="mahoi" name="mahoi" style="width: 120px" disabled onchange="onHoiChange(this.value)" >
                                    <option value="0">-- Chọn hội đoàn thể --</option>
                                    <s:iterator value="lstDmKhac17">                                    
                                        <option value="<s:property value="code"/>"><s:property value="code"/> - <s:property value="value"/></option>                                         
                                    </s:iterator>   
                                </select>
                                &nbsp;<s:label value="Mã tổ " cssStyle="color: #029c44;" />
                                <s:select style="width: 120px;"
                                          list="lstMato"
                                          id="mato"
                                          name="mato"
                                          listKey="sKey"
                                          listValue="sDesc"
                                          disabled="true" />
                                <s:select id="mato_data"
                                          list="lstMato"
                                          listKey="sKey"
                                          listValue="sDesc"
                                          headerKey="-1"
                                          headerValue="--- Chọn ---"
                                          cssStyle="display:none;"
                                          disabled="true" />
                            </s:if>
                            &nbsp;
                            <s:label value="Trạng thái " cssStyle="color: #029c44;" />
                            <select id="trangthai" name="trangthai">
                                <option value="0">-- Tất cả --</option>
                                <option value="N">Chưa đối chiếu</option>
                                <option value="R">Không đối chiếu được</option>
                                <option value="S">Đã đối chiếu</option>
                            </select>

                            <s:label value="Chương trình " cssStyle="color: #029c44;" />
                            <select id="chtrinh" name="chtrinh" style="width: 150">
                                <option value="0">-- Tất cả --</option>
                                <s:iterator value="lstDmKhac197">                                    
                                    <option value="<s:property value="code"/>"><s:property value="code"/> - <s:property value="value"/></option>                                         
                                </s:iterator>   
                            </select>
                            &nbsp;
                            <s:label value="Nguồn vốn " cssStyle="color: #029c44;" />
                            <select id="nguonvon" name="nguonvon">
                                <option value="0">-- Tất cả --</option>
                                <option value="1">Nguồn TW</option>
                                <option value="2">Nguồn ĐP</option>
                            </select>
                            <br>
                            <s:label value="Tìm kiếm " cssStyle="color: #029c44;" />
                            <input type="text" name="soku" id="soku" placeholder="Mã KH hoặc món vay" value="" style="width: 150px">

                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                       onBeforeTopics="beforediv_data"
                                       onCompleteTopics="completediv_data" cssStyle="display:none"/>
                            <input type="button" id="loaddata" name="loaddata" onclick="onLoadData()" value="Tải dữ liệu"/>
                            <input type="button" id="idSave" value="Lưu dữ liệu"/>
                            <div id="page-header" style="display: none; margin-left: 30px;" class="hidden-inline">
                                Chọn trang
                                <input style="width: 40px;border-top-style: hidden; border-left-style: hidden; border-right-style: hidden" type="number" id="pageInput" min="1" />
                                <a onclick="goToPage()" href="#" id="btn_go">Go</a>
                                <a onclick="prevPage()" href="#" id="btn_prev">&#8920;</a>
                                Trang <span id="page"></span>
                                <a onclick="nextPage()" href="#" id="btn_next">&#8921;</a>
                            </div>
                        </td> 
                    </tr>
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
            $(document).ready(function () {
//            $("#ngay_dcpln").val("31/12/2021");
                document.getElementById('ngay_bc_DATE').value = "31/07/2025";
            })

            function onXaChange(maXa) {
                $("#mahoi").val("0");
                $("#mahoi").prop("disabled", false);

                $("#mato").children().remove();
                $("#mato").append("<option value=''>-- Chọn tổ --</option>");
                $("#mato").prop("disabled", true);
            }
            function onHoiChange(maHoi) {
                var maXa = $("#maxa").val();
                var prefix = maHoi + "_" + maXa;

                $("#mato").children().remove();

                // Thêm mặc định
                $("#mato").append("<option value='10_000000_0000000'> -- Tất cả -- </option>");
                $("#mato").append("<option value='1_000000_NOGROUP'> NOGROUP -> Trực tiếp</option>");

                // Lọc danh sách tổ theo hội + xã
                $("#mato_data option").each(function () {
                    var val = $(this).val();
                    if (val.indexOf(prefix) === 0) {
                        $("#mato").append($(this).clone());
                    }
                });

                // Sắp xếp
                $("#mato").html($("#mato option").sort(function (a, b) {
                    return a.text.localeCompare(b.text);
                }));

                $("#mato").val("10_000000_0000000");
                $("#mato").prop("disabled", false);
            }

            // Khi trang sẵn sàng
            $(function () {
                $("#soku").on("input", function () {
                    const hasValue = this.value.trim() !== "";   // đã nhập hay chưa

                    if (hasValue) {
                        // Đặt lại lựa chọn rồi khóa ngay
                        $("#maxa").prop("selectedIndex", 0).prop("disabled", true);
                        $("#trangthai").prop("selectedIndex", 0).prop("disabled", true);
                        $("#nguonvon").prop("selectedIndex", 0).prop("disabled", true);
                        $("#chtrinh").prop("selectedIndex", 0).prop("disabled", true);
                        $("#mato").prop("selectedIndex", 0).prop("disabled", true);
                        $("#mahoi").prop("selectedIndex", 0).prop("disabled", true);
                        $("#page-header").hide();
                    } else {
                        // Mở khóa nếu người dùng xoá sạch
                        $("#maxa").prop("disabled", false);
                        $("#trangthai").prop("disabled", false);
                        $("#nguonvon").prop("disabled", false);
                        $("#chtrinh").prop("disabled", false);
                    }
                });
            });

            const header = document.getElementById("page-header");
            header.classList.remove("hidden-inline");
            header.classList.add("inline-block");

        </script>
    </body>
</html>
