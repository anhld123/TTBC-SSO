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
                width: 90px;
                height: 26px;
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
                height: 65px;
                padding:0px;
                padding-bottom: 0px;
                padding-top: 0px;
                border: 1px solid;
            }
            #navParam{
                height: 35px;
                padding:0px;
                padding-bottom: 0px;
                padding-top: 0px;
                border: 1px solid;
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
        </style>
        <script>
            var bsubmit = false;
            $(document).ready(function () {
                $(".NGAY_SL").css({"width": "80px"});
            });

            function isEndOfMonth(day, month, year) {
                // Tạo đối tượng ngày với ngày kế tiếp
                var nextDay = new Date(year, month - 1, day + 1);
                // Kiểm tra nếu ngày kế tiếp là ngày đầu tháng
                return nextDay.getDate() === 1;
            }

            function onLoadData() {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                $('#divExportReportLink').empty();
                $("#loadData")[0].click();
                bsubmit = true;
            }

            function onSaveData()
            {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();
                //var poscd = getposfromtreecheck();
                var khoa = $("#khoa_nhaptaycn").val() + "_save";
                if (!bsubmit)
                {
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !</h2>");
                    return;
                }
                // Trung bo sung phan validate data
                if (typeof validateData !== 'undefined' && typeof validateData === 'function') {
                    if (!validateData())
                    {
                        return false;
                    }
                }
                if (validateRequiredFields())
                {
                    $("#" + khoa)[0].click();
                }
                alert("Thao tác thành công!");
                $("#loadData")[0].click();
            }

            function onSaveDataHTLai()
            {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();
                //var poscd = getposfromtreecheck();
                var khoa = $("#khoa_nhaptaycn").val() + "_save_htlai";
                if (!bsubmit)
                {
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !</h2>");
                    return;
                }
                // Trung bo sung phan validate data
                if (typeof validateData !== 'undefined' && typeof validateData === 'function') {
                    if (!validateData())
                        return false;
                }
                if (validateRequiredFields())
                {
                    $("#" + khoa)[0].click();
                }
                $("#loadData")[0].click();
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
                var i = element.length;
                for (var k = 0; k < i; k++)
                {
                    if (element[k].name === 'poscd')
                    {
                        if (element[k].checked === true)
                        {
                            if (element[k].value !== '999999')
                                pos_cd = pos_cd + element[k].value + ',';
                        }
                    }
                }
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
                        if (parseFloat(value) > 99999999999999) {
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

            function onSentData()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_ktgs = $("#khoa").val();
                var poscd = getposfromtreecheck();
                if (khoa_ktgs !== 'PHIUT_001')
                {
                    if ((poscd === null || poscd === '') && khoa_ktgs !== 'QD23_004')
                    {
                        $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn phòng giao dịch cần gửi số liệu ! </h2>");
                        return;
                    }
                }

                $("#idSend")[0].click();
                bsubmit = false;
            }

            function ExpEcel()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();

                $("#idExpEceltmp")[0].click();
                bsubmit = false;
            }

            function ExpEcelTemp()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();

                $("#idExpEceltmpTemp")[0].click();
                bsubmit = false;
            }

            function getDaysOfMonth(month, year) {
                switch (month) {
                    case 1:
                        return 31;
                    case 2:
                        if (year % 4 === 0)
                            return 29;
                        else
                            return 28;
                    case 3:
                        return 31;
                    case 4:
                        return 30;
                    case 5:
                        return 31;
                    case 6:
                        return 30;
                    case 7:
                        return 31;
                    case 8:
                        return 31;
                    case 9:
                        return 30;
                    case 10:
                        return 31;
                    case 11:
                        return 30;
                    case 12:
                        return 31;
                }
            }
        </script>
    </head>

    <body>
        <s:form id="id_%{khoa_nhaptaycn}" name="name_%{khoa_nhaptaycn}" action="%{khoa_nhaptaycn}" theme="simple">
            <s:hidden name="khoa_nhaptaycn" id="khoa"/>
            <s:hidden name="ReportDate" id="ReportDate" value=""/>
            <s:hidden name="Grade" id="Grade"/>
            <s:hidden name="UserName" id="UserName"/>
            <s:hidden name="type_action" id="type_action"/>
            <s:hidden id="formRealId" value="id_%{khoa_nhaptaycn}"/>
            <div id="container" >            
                <div id="navParamUp" > 
                    <s:if test="Grade.equalsIgnoreCase('1')">
                        <table>
                            <tr style="height: 30px;">
                                <s:iterator value="lstNhaptaycnParams">                                            
                                    <td style="padding-left: 10px; padding-right: 10px;">
                                        <s:property value="label"></s:property>:
                                            &nbsp;&nbsp;
                                        <s:if test="type.equalsIgnoreCase('T')">                                      
                                            <input type="text" value="" id="D_<s:property  value="%{fieldName}"/>" name="<s:property value="%{fieldName}"/>_TEXT" placeholder="<s:property value="label"/>"/>                                    
                                        </s:if>                                              

                                        <!-- Tungnv Neu: la L thi gen List -->
                                        <s:if test="type.equalsIgnoreCase('L')">
                                            <s:select  list="comboList" name="%{fieldName}_LIST" listKey="key" listValue="value" id="%{fieldName}"></s:select>
                                        </s:if>
                                        <!-- Tungnv: Neu la D thi gen Date -->
                                        <s:if test="type.equalsIgnoreCase('D')">  
                                            <sj:datepicker name="%{fieldName}_DATE" value="%{new java.util.Date()}"  id="%{fieldName}_DATE"
                                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL" onChangeTopics="changeTopic"/>                                           
                                            <s:if test="Grade.equalsIgnoreCase('1')">
                                                <s:label value="Mã xã " cssStyle="color: #029c44;" />
                                                <s:select style="width: 200px;"
                                                          list="lstMaxa"
                                                          id="maxa"
                                                          name="maxa"
                                                          listKey="sKey"
                                                          listValue="sDesc"
                                                          headerKey=""
                                                          headerValue="-- Chọn xã --"
                                                          onchange="onXaChange(this.value)" />
                                                <s:label value="Mã hội " cssStyle="color: #029c44;" />
                                                <select id="mahoi" name="mahoi" style="width: 150px" disabled onchange="onHoiChange(this.value)" >
                                                    <option value="0" style="text-align: center">-- Chọn hội đoàn thể --</option>
                                                    <s:iterator value="lstDmKhac17">                                    
                                                        <option value="<s:property value="code"/>"><s:property value="code"/> - <s:property value="value"/></option>                                         
                                                    </s:iterator>
                                                </select>
                                                &nbsp;<s:label value="Mã tổ " cssStyle="color: #029c44;" />
                                                <s:select style="width: 200px;"
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
                                        </s:if>

                                    </td>
                                </s:iterator>     
                            </tr>
                        </table>
                    </s:if>
                    <table>
                        <tr>
                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <td style="padding-left: 10px; padding-right: 10px;">Giải ngân:
                                    &nbsp;&nbsp;
                                    <select name="giaingan" id="giaingan">
                                        <option value="-1">--Tất cả--</option>
                                        <option value="1">Giải ngân 12/2025</option>                                    
                                    </select>
                                </td> 
                            </s:if>
                            <s:else>
                                <td>Ngày báo cáo: 
                                    <sj:datepicker name="ngay_bc_DATE" value="%{'31/12/2023'}"  id="ngay_bc_DATE" 
                                                   placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL" onChangeTopics="changeTopic"/> 
                                </td>
                            </s:else>
                            <s:if test="Grade.equalsIgnoreCase('3')">
                                <td>   &nbsp; Mã chi nhánh: 
                                    <select id="lstCN"  name="lstCN">
                                        <option value="000000">----Chọn mã chi nhánh----</option>
                                        <s:iterator value="lstCN_API">
                                            <option value="<s:property value="branchCode"/>"><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>               
                                        </s:iterator>
                                    </select> 
                                </td>

                            </s:if>
                            <td style="padding-left: 10px; padding-right: 10px;">                                
                                <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                           onBeforeTopics="beforediv_data"
                                           onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>

                                <s:if test="Grade.equalsIgnoreCase('1')">
                                    &nbsp;&nbsp;&nbsp;<input type="button" id="idsaveDatatmp1" name="namesaveDatatmp"  onclick="onSaveData()" value="Xác nhận lãi giảm" style="display: none;"/> 
                                    &nbsp;&nbsp;&nbsp;<input type="button" id="idsaveDatatmp2" name="namesaveDatatmp"  onclick="onSaveDataHTLai()" value="Cập nhật hạch toán GL" style="display: none;"/>
                                </s:if>  
                                <s:elseif test="Grade.equalsIgnoreCase('2')">
                                    &nbsp;&nbsp;&nbsp;<input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Chốt số liệu"/> 
                                </s:elseif>
                                <s:elseif test="Grade.equalsIgnoreCase('3') 
                                          && type_action.equalsIgnoreCase('1') 
                                          && (UserName.equalsIgnoreCase('HANNM_KTTC')
                                          || UserName.equalsIgnoreCase('NGOCLM_KTTC'))">
                                    &nbsp;&nbsp;<input  type="button" id="idSend" style="color: blue" value="Mở nhập điều chỉnh ngày 31/12 kéo dài"/>
                                </s:elseif>
                                <s:elseif test="Grade.equalsIgnoreCase('3') && type_action.equalsIgnoreCase('2')
                                          && (UserName.equalsIgnoreCase('HANNM_KTTC')
                                          || UserName.equalsIgnoreCase('NGOCLM_KTTC'))">
                                    &nbsp;&nbsp;<input  type="button" id="idSend" style="color: red" value="Đóng điều chỉnh ngày 31/12 kéo dài"/>
                                </s:elseif>
                            </td>   
                            <td>
                                <div id="loadingImageDiv_data" style="margin-left: 20px;display: none;" >
                                    <img id="loadingImage" src='img/loading.gif' border='0' >
                                </div>
                            </td>
                            <td>
                                <div id="message_suc_err"> 
                                </div>
                            </td>
                        </tr>                        
                    </table>    
                </div>                                
                <div>                                
                    <s:if test="!Grade.equalsIgnoreCase('4')">
                        <div id="containParm_full" align="center">
                            <div id="divExportReport"></div>
                            <div align="right" id="divExportReportLink"></div>
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

                </div>   
            </div>

        </s:form>
        <script>
            $(function () {
                $("#dtNgayBC").datepicker({
                    dateFormat: 'dd/mm/yy',
                    showOn: "button",
                    buttonImage: "img/icon-ui_datepicker.png",
                    buttonImageOnly: true,
                    showButtonPanel: true,
                    buttonText: "icono",
                    changeMonth: true,
                    changeYear: true
                }).val(new Date(new Date().getFullYear(), new Date().getMonth(), 1).toLocaleDateString("zh-HK", {
                    year: 'numeric',
                    month: '2-digit',
                    day: '2-digit'
                }));
            });

            // Tính ngày cuối tháng hiện tại
            var currentDate = new Date();
            var lastDayOfMonth = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0);
            var formattedDate = lastDayOfMonth.toLocaleDateString("zh-HK", {
                year: 'numeric',
                month: '2-digit',
                day: '2-digit'
            });
            // Đặt giá trị cho #dtNgayBC
            $("#dtNgayBC_temp").val(formattedDate);
            document.getElementById('ngay_bc_DATE').value = formattedDate;
            <s:if test="Grade.equalsIgnoreCase('1')">
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

                $("#mato").html($("#mato option").sort(function (a, b) {
                    return a.text.localeCompare(b.text);
                }));

                $("#mato").val("10_000000_0000000");
                $("#mato").prop("disabled", false);
            }
            </s:if>

            $("#idSend").click(function () {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();
                let khoa = $("#formRealId").val();
                let aCheck = confirm("Bạn chắc chắn muốn thao tác này?");
                if (aCheck) {

                    var isValid = true;
                    if (isValid) {
                        var url, sdata;
                        url = "lock_htls_2025_tw.action";
                        sdata = jQuery("#" + khoa).serialize();
                        $("#divExportReport").html('<img src="img/loading.gif"/>');
//                        btnDisabled(1);
                        $.ajax({
                            type: "POST",
                            url: url,
                            data: sdata,
                            success: function (data) {
                                var status = parseInt(data.status);
                                if (status === 1) {
                                    alert("Thành công!");
                                    window.location.reload();
                                } else {
                                    alert("Lỗi!");
                                    $window.location.reload();
                                }
                            },
                            complete: function () {
//                                btnDisabled(0);
                            },
                            error: function (request) {
                                alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
//                                $('#idSearch').click();
                            }
                        });
                    }
                }

            });
        </script>
    </body>
</html>
