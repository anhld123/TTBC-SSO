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
                // Thực hiện lần click thứ hai sau 100ms
//                setTimeout(function () {
//                    $("#loadData")[0].click();
//                }, 0, 00001);
                bsubmit = true;
            }

            $("#idSave").click(function () {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();


                let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo?");
                if (aCheck) {
                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var isValid = true;
                    var chot = document.getElementById("chotsl").value;
                    var ngaybc = document.getElementById("ngay_bc_DATE").value;
                    var parts = ngaybc.split('/');
                    var snam = parts[2];
                   var stoday = document.getElementById("stoday").value;
                    var sparts = stoday.split('/');
                    var currentYear = sparts[2];
                    var currentMonth = sparts[1];
                    var currentDate = sparts[0];
//                    if (currentYear.toString() !== snam.toString()) {
//                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Chức năng chỉ lưu tại năm hiện tại " + currentYear + "</h>");
//                        return;
//                    } else if (currentMonth.toString() !== "12" || currentDate.toString() < 10) {
//                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Chức năng lưu chỉ được thực hiện từ ngày 10 đến 31 của tháng 12 năm " + currentYear + "</h>");
//                        return;
//                    } else 
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
                    }

                    for (var i = 0; i < rowcount; i++) {
                        try {
                            // Xử lý mỗi hàng ở đây nếu cần
                        } catch (e) {
                            // Xử lý lỗi nếu có
                        }
                    }

                    if (isValid) {
                        var url, sdata;
                        url = "save_KTKSNB_01_2024.action";
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
//                                    $('#message_suc_err').html("<h style='color: green; font-size: 13px; font-weight: bold'>Bạn đã lưu dữ liệu thành công!</h>");
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
                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var chot = document.getElementById("chotsl").value;
                    var chot_tw = document.getElementById("chotsl_tw").value;
                    var isValid = true;
                    var ngaybc = document.getElementById("ngay_bc_DATE").value;
                    var parts = ngaybc.split('/');
                    var snam = parts[2];
                    var stoday = document.getElementById("stoday").value;
                    var sparts = stoday.split('/');
                    var currentYear = sparts[2];
                    var currentMonth = sparts[1];
                    var currentDate = sparts[0];
//                    if (currentYear.toString() !== snam.toString()) {
//                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Chức năng chỉ lưu tại năm hiện tại " + currentYear + "</h>");
//                        return;
//                    } else if (currentMonth.toString() !== "12" || currentDate.toString() < 10) {
//                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Chức năng lưu chỉ được thực hiện từ ngày 10 đến 31 của tháng 12 năm " + currentYear + "</h>");
//                        return;
//                    }
//                    else 
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
                        url = "send_KTKSNB_01_2024.action";
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
                    Ngày báo cáo: 
                    <sj:datepicker name="ngay_bc_DATE" value="%{'31/12/2023'}"  id="ngay_bc_DATE" 
                                   placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL" onChangeTopics="changeTopic"/> 
                    <s:iterator value="lstDmKhac">      
                        <input type="hidden" id="stoday" value="<s:property  value="value" />" name="stoday"/>                                
                    </s:iterator>   
                    <s:if test="!Grade.equalsIgnoreCase('3')">
                        <%--<s:if test="Grade.equalsIgnoreCase('2')">--%>
                        <label id="title21">&nbsp; Nghiệp vụ :</label>
                        <select name="txtGetData" id="txtGetData" onchange="toggleButton()">                                                    
                            <option value="1">1. Nhập dữ liệu cấp tỉnh</option>                                                    
                            <option value="2">2. Tình trạng nhập dữ liệu PGD</option>
                        </select> 
                        <label id="title22">&nbsp; PGD kiểm tra :</label>
                        <select id="lstPGD" name="lstPGD" style="width: 100px">
                            <option value="000000">---Chọn PGD---</option>
                            <s:iterator value="lstPGD_API">                                    
                                <option value="<s:property value='posCode'/>|<s:property value='posName'/>"><s:property value="posCode"/> - <s:property value="posName"/></option>                                         
                            </s:iterator>   
                        </select>
                        <%--</s:if>--%>
                        <%--<s:if test="Grade.equalsIgnoreCase('1')">--%>
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
                        <label id="title5">&nbsp; Tháng kiểm tra: </label>
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
                        <label id="title2">&nbsp; Cán bộ kiểm tra :</label>
                        <select name="txtCanbo" id="txtCanbo" style="width: 100px">   
                            <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                                <option value='<s:property value="D1"/>'><s:property value="D2"/> - <s:property value="D3"/></option>      
                            </s:iterator>
                            <option value="00000">---Chọn cán bộ---</option>
                        </select> 
                    </s:if>
                    <s:if test="Grade.equalsIgnoreCase('3')">
                        <!--                        <label id="title31">&nbsp; Nghiệp vụ :</label>
                                                <select name="txtGetData3" id="txtGetData3" >
                                                    onchange="toggleCap3()">                                                    
                                                    <option value="1">1. Phê duyệt kế hoạch PGD</option>                                                    
                                                    <option value="2">2. Phê duyệt kế hoạch Tỉnh</option>
                                                </select> -->
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
                document.getElementById("title21").style.display = "none";
                document.getElementById("txtGetData").style.display = "none";
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
                document.getElementById("title21").style.display = "inline";
                document.getElementById("txtGetData").style.display = "inline";
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
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();

                let aCheck = confirm("Bạn chắc chắn muốn xóa dữ liệu báo cáo ?");
                if (aCheck) {
                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var chot = document.getElementById("chotsl").value;
                    var chot_tw = document.getElementById("chotsl_tw").value;
                    var isValid = true;
                    var ngaybc = document.getElementById("ngay_bc_DATE").value;
                    var parts = ngaybc.split('/');
                    var snam = parts[2];
                    var stoday = document.getElementById("stoday").value;
                    var sparts = stoday.split('/');
                    var currentYear = sparts[2];
                    var currentMonth = sparts[1];
                    var currentDate = sparts[0];
//                    if (currentYear.toString() !== snam.toString()) {
//                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Chức năng chỉ lưu tại năm hiện tại " + currentYear + "</h>");
//                        return;
//                    } else if (currentMonth.toString() !== "12" || currentDate.toString() < 10) {
//                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Chức năng lưu chỉ được thực hiện từ ngày 10 đến 31 của tháng 12 năm " + currentYear + "</h>");
//                        return;
//                    } else 
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
                        alert("Cảnh báo: Không có dữ liệu để xóa!");
//                        $('#message_suc_err').html("<h class='color_11' style='color: red; font-size: 13px; font-weight: bold'>Cảnh báo: Không có dữ liệu để xóa!</h>");
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
                        url = "delete_KTKSNB_2024.action";
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
            <%--<s:if test="Grade.equalsIgnoreCase('1')">--%>
            $(document).ready(function () {
                function updateDatepicker() {
                    var datepicker = $('#ngay_bc_DATE');
                    var currentDate = new Date();
                    var year = currentDate.getFullYear();
                    var lastDayOfYear = new Date(year, 11, 31);
                    var formattedDate = ('0' + lastDayOfYear.getDate()).slice(-2) + '/' +
                            ('0' + (lastDayOfYear.getMonth() + 1)).slice(-2) + '/' +
                            lastDayOfYear.getFullYear();

                    datepicker.val(formattedDate);

                    datepicker.datepicker("option", {
                        beforeShowDay: function (date) {
                            return [date.getDate() === 31 && date.getMonth() === 11, ""];
                        }
                    });

                }

                // Initialize the datepicker with the default settings
                updateDatepicker();
            });
            <%--
    </s:if>
    <s:if test="!Grade.equalsIgnoreCase('1')"> --%>
//            $(document).ready(function () {
//                function updateDatepicker() {
//                    var selectedPeriod = "2"; // Assuming period "2" corresponds to 'Kỳ tháng'
//                    var datepicker = $('#ngay_bc_DATE');
//                    var currentDate = new Date();
//                    var year = currentDate.getFullYear();
//
//                    if (selectedPeriod === "2") { // Kỳ tháng
//                        datepicker.datepicker("option", {
//                            beforeShowDay: function (date) {
//                                var month = date.getMonth(); // 0 = January, 11 = December
//                                var day = date.getDate();
//
//                                // Only allow from December 10 to December 31
//                                if (month === 11 && day >= 10 && day <= 31) {
//                                    return [true, ""]; // Enable the date
//                                } else {
//                                    return [false, ""]; // Disable the date
//                                }
//                            }
//                        });
//                    }
//
//                    // Automatically set date based on the selected period
//                    var newDate = selectedPeriod === "2" ? new Date(year, 11, 10) : currentDate;
//
//                    // Format the date as dd/mm/yyyy
//                    var formattedDate = ('0' + newDate.getDate()).slice(-2) + '/' +
//                            ('0' + (newDate.getMonth() + 1)).slice(-2) + '/' +
//                            newDate.getFullYear();
//
//                    datepicker.val(formattedDate);
//                }
//
//                // Initialize the datepicker with default settings
//                updateDatepicker();
//            });
            <%--</s:if>--%>
        </script>
    </body>
</html>
