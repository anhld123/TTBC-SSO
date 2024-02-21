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
                var txtGetData = document.getElementById('txtGetData').value;
                var gradeAuthor1 = document.getElementById('gradeAuthor1').value;
                let checkedCount = countCheckedItem();
                if (checkedCount === 0) {
                    $('#message_suc_err').html("<h style='color: red; font-size: 13px ; font-weight: bold'>Bạn chưa chọn bản ghi để lưu!</h>");
                    return;
                } else {
                    let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                    if (aCheck) {
                        var table = document.getElementById("subTable");
                        var rowcount = table.rows.length;
                        var isValid = true;
                        for (var i = 0; i < rowcount; i++) {
                            try {
                                var check_box = document.getElementById('check' + i).checked;
                                var D10 = document.getElementById('D10_' + i).value;
                                var D19 = document.getElementById('D19_' + i).value;
                                if (gradeAuthor1 === "1" && txtGetData === "0" && D19 !== "02" && D19 !== "06" && D19 !== "07")
                                {
                                    if (check_box !== false && D10.length < 5) {
//                                        alert("check " + check_box + " ma " + D1 + " d10 " + D10 + "d19 " + D19);
                                        alert('Vui lòng nhập thông tin cột 12.');
                                        document.getElementById("D10_" + i).style.backgroundColor = "#EEAFA6";
                                        return;
                                    }
                                }
                            } catch (e) {
                            }
                        }
                        if (isValid) {
                            var url, sdata;
                            url = "SAVE_QLNK_2023.action";
                            sdata = jQuery("#frmdata").serialize();
                            $("#viewData").html('<img src="img/loading.gif"/>');
                            btnDisabled(1);
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: sdata,
                                success: function (data) {
                                    if (data.length !== null) {
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
                                    onLoadData();
                                }
                            });
                        }
                    }
                }
            });

//            function onSaveData() {
//                $('#message_suc_err').empty();
//                $('#divExportReportLink').empty();
//                var poscd = getposfromtreecheck();
//                var txtGetData = document.getElementById('txtGetData').value;
//                var gradeAuthor1 = document.getElementById('gradeAuthor1').value;
//                var khoa = "QLNK_2023_save";
//                let checkedCount = countCheckedItem();
//                if (checkedCount === 0) {
//                    $('#message_suc_err').html("<h style='color: red; font-size: 13px ; font-weight: bold'>Bạn chưa chọn bản ghi để lưu!</h>");
//                    return;
//                } else {
//                    let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
//                    if (aCheck) {
//                        var table = document.getElementById("subTable");
//                        var rowcount = table.rows.length;
//                        for (var i = 0; i < rowcount; i++) {
//                            try {
//                                var check_box = document.getElementById('check' + i).checked;
//                                var D10 = document.getElementById('D10_' + i).value;
//                                var D19 = document.getElementById('D19_' + i).value;
//                                if (gradeAuthor1 === "1" && txtGetData === "0" && D19 !== "02" && D19 !== "06" && D19 !== "07")
//                                {
//                                    if (check_box !== false && D10.length < 5) {
////                                        alert("check " + check_box + " ma " + D1 + " d10 " + D10 + "d19 " + D19);
//                                        alert('Vui lòng nhập thông tin cột 12.');
//                                        document.getElementById("D10_" + i).style.backgroundColor = "#EEAFA6";
//                                        return;
//                                    }
//                                }
//                            } catch (e) {
//                            }
//                        }
//                    }
//                }
//                if (typeof validateData !== 'undefined' && typeof validateData === 'function') {
//                    if (!validateData()) {
//                        alert('vao day');
//                        return false;
//                    }
//                }
//                if (validateRequiredFields()) {
//                    $("#QLNK_2023_save")[0].click();
//                }
//                if (khoa === 'QLNK_2023_save')
//                {
//                    function saveData() {
//                        return new Promise((resolve, reject) => {
//                            bsubmit = true;
//                            setTimeout(resolve, 0, 1);
//
//                        });
//                    }
//                    saveData().then(onLoadData);
//                    alert("Bạn đã lưu dữ liệu thành công!");
//
//                }
//            }

            $("#idDelete").click(function () {
                let checkedCount = countCheckedItem();
                if (checkedCount === 0 || checkedCount > 1) {
                    alert('Bạn chưa chọn bản ghi để xóa hoặc mỗi lần bạn chỉ được phép xóa tối đa 1 bản ghi!');
                } else {
                    let aCheck = confirm("Bạn chắc chắn muốn đề nghị xóa dữ liệu ?");
                    if (aCheck) {
                        var url, sdata;
                        url = "DELETE_QLNK_2023.action";
                        sdata = jQuery("#frmdata").serialize();
                        $("#viewData").html('<img src="img/loading.gif"/>');
                        btnDisabled(1);
                        $.ajax({
                            type: "POST",
                            url: url,
                            data: sdata,
                            success: function (data) {
                                if (data.length !== null) {
                                    alert("Thành công: Đề nghị xóa dữ liệu.");
                                    onLoadData();
                                } else {
                                    alert("Lỗi: Đề nghị xóa dữ liệu.");
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
                    $("#idSearch").prop('disabled', true);
//                    $("#idSend").prop('disabled', true);
//                    $("#idSave").prop('disabled', true);
//                    $("#idDelete").prop('disabled', true);
                    $("#idUpload").prop('disabled', true);
                    $("#idFetch").prop('disabled', true);
                } else {
                    $("#idSearch").prop('disabled', false);
//                    $("#idSend").prop('disabled', false);
//                    $("#idSave").prop('disabled', false);
//                    $("#idDelete").prop('disabled', false);
                    $("#idUpload").prop('disabled', false);
                    $("#idFetch").prop('disabled', false);
                }
                ;
            }

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
        </script>
    </head>
    <!--new java.util.Date()-->
    <body>

        <s:form id="frmdata" name="frmdata" action="%{khoa_nhaptaycn}" theme="simple">
            <s:hidden name="khoa_bcqt" id="khoa"/>
            <s:hidden name="ReportDate" id="ReportDate" value=""/>
            <s:hidden name="Grade" id="Grade"/>
            <s:hidden name="UserName" id="UserName"/>               
            <fieldset style="display: flex; align-content: space-between;justify-content: space-between;" class="navParam3">
                <legend><b>Tìm kiếm dữ liệu</b></legend>  
                <table>
                    <tr>
                    <input type="hidden" name="gradeAuthor1" id="gradeAuthor1" value="<s:property value='gradeAuthor1'/>">
                    <s:if test="gradeAuthor1.equalsIgnoreCase('2')">

                        <td>Ngày BC</td>
                        <td>
                            <sj:datepicker name="ngay_bc_DATE" value="%{'31/12/2023'}"  id="ngay_bc_DATE" 
                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL" onChangeTopics="changeTopic"/> 
                        </td>
                        <td >&nbsp;&nbsp;&nbsp;Món vay:</td>
                        <td><input id="txtSoku" name="txtSoku" placeholder="Nhập mã món vay"/>
                        </td>
                        <td >&nbsp;&nbsp;&nbsp;Mã xã:</td>
                        <td  >                                               
                            <s:select  style="width: 180px;"  list="lstMaxa" id="maxa" name="maxa" listKey="sKey" listValue="sDesc"
                                       onchange="reLoadValue(this.value)"></s:select>  &nbsp;&nbsp;&nbsp;
                            </td>
                            <td >&nbsp;&nbsp;&nbsp;Mã tổ:</td>
                            <td>
                            <s:select  style="width: 180px;"  list="lstMato" id="mato" name="mato" listKey="sKey" listValue="sDesc" onchange="reLoadValueMaTo(this.value)"></s:select>
                            <s:select  
                                id="mato_data"
                                list="lstMato" 
                                listKey="sKey"
                                listValue="sDesc"
                                headerKey="-1"
                                headerValue="--- Chọn ---"                                        
                                cssStyle="display:none;">
                            </s:select>
                        </td>
                        <td colspan="2" style="text-align: right">                                        
                            <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                       onBeforeTopics="beforediv_data"
                                       onCompleteTopics="completediv_data" cssStyle="display:none"/>
                            <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <input type="button" id="idSave"  value="Lưu dữ liệu"/> 
                            </s:if>
                        </td> 
                        <td><input type="hidden" name="txtGetData" id="txtGetData" value="0"> </td>
                        </s:if>
                        <s:else>
                        <td>Ngày BC</td>
                        <td>
                            <sj:datepicker name="ngay_bc_DATE" value="%{'31/12/2023'}"  id="ngay_bc_DATE" 
                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL" onChangeTopics="changeTopic"/> 
                        </td>
                        <td >&nbsp;&nbsp;&nbsp;Món vay:</td>
                        <td><input id="txtSoku" name="txtSoku" placeholder="Nhập mã món vay"/>
                        </td>
                        <td >&nbsp;&nbsp;&nbsp;Mã xã:</td>
                        <td  >                                               
                            <s:select  style="width: 180px;"  list="lstMaxa" id="maxa" name="maxa" listKey="sKey" listValue="sDesc"
                                       onchange="reLoadValue(this.value)"></s:select>  &nbsp;&nbsp;&nbsp;
                            </td>
                            <td >&nbsp;&nbsp;&nbsp;Mã tổ:</td>
                            <td>
                            <s:select  style="width: 180px;"  list="lstMato" id="mato" name="mato" listKey="sKey" listValue="sDesc" onchange="reLoadValueMaTo(this.value)"></s:select>
                            <s:select  
                                id="mato_data"
                                list="lstMato" 
                                listKey="sKey"
                                listValue="sDesc"
                                headerKey="-1"
                                headerValue="--- Chọn ---"                                        
                                cssStyle="display:none;">
                            </s:select>
                        </td>
                        <td>&nbsp;&nbsp;&nbsp;Loại phê duyệt:</td>
                        <td><select name="txtGetData" id="txtGetData">                                                    
                                <option value="1">Danh sách nợ khoanh</option>                                                    
                                <option value="0">Nhập thông tin kiểm tra</option>
                            </select>
                        </td>
                        <td colspan="2" style="text-align: right">                                        
                            <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                       onBeforeTopics="beforediv_data"
                                       onCompleteTopics="completediv_data" cssStyle="display:none"/>
                            <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <s:if test="chotCic.equalsIgnoreCase('1')">
                                    <font style="color: red"> PGD đã được chốt số liệu
                                </s:if>
                                <s:else>
                                    <input type="button" id="idSave" value="Lưu dữ liệu"/> 
                                    <input type="button" id="idDelete" style="color: red" value="Xóa dữ liệu"/> 
                                </s:else>    

                            </s:if>
                            <s:else>
                                <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Chốt số liệu"/>
                            </s:else>    
                        </td>   
                    </s:else>
                    </tr>  
                </table>    
            </fieldset>
            <div id="loadingImageDiv_data" style="margin-left: 20px;display: none;" >
                <img id="loadingImage" src='img/loading.gif' border='0' >                  
            </div>   
            <div id="message_suc_err"></div>
            <s:if test="khoa_nhaptaycn.equalsIgnoreCase('QLNK_2023')">
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
                document.getElementById('ngay_bc_DATE').value = "31/12/2023";
            });
//            $('#ngay_bc_DATE').datepicker('disable');
//            document.getElementById("labelPageNumber").style.visibility = "hidden";
//            document.getElementById("pageNumber").style.visibility = "hidden";

            function hideElement() {
                var gradeAuthor1 = document.getElementById('gradeAuthor1').value;
                if (gradeAuthor1 === "1")
                {
                    document.getElementById('idDelete').style.display = 'none';
                }
                document.getElementById('txtGetData').addEventListener('change', function () {
                    var value = this.value;
                    if (value === "0") {
                        document.getElementById('idDelete').style.display = 'inline';
                    } else {
                        document.getElementById('idDelete').style.display = 'none';
                    }
                });
            }
            hideElement();
        </script>
    </body>
</html>
