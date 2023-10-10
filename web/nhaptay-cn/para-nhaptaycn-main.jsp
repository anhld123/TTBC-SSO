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

            function onLoadData()
            {
//                var grade = $.session.get('reportGrade').toString();
//                $.session.get()
//                alert(grade);
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                $('#divExportReportLink').empty();
                 var ngay_bc = $("#ngay_bc_DATE").val();
                var khoa_nhaptaycn = $("#khoa_nhaptaycn").val();
                var lv_day = (ngay_bc.substr(0, 2));
                var lv_month = (ngay_bc.substr(3, 2));
//                alert(khoa_nhaptaycn);
                if (khoa_nhaptaycn == 'GQVL_01' && (lv_day + lv_month != '3006' && lv_day + lv_month != '3112'))
                {
                    alert("Chọn định kỳ 30 tháng 6 hoặc 31 tháng 12 để tải dữ liệu!");
                 
                   return;
                    }               
                $("#loadData")[0].click();
                bsubmit = true;
//                return true;
            }
            function onSaveData()
            {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();
                var poscd = getposfromtreecheck();
//                alert(poscd);
//                if(poscd === '' || poscd.length ===0)
//                {
//                    $('#message_suc_err').html("<h2 style='color: red'>Bạn đã chốt số liệu, vui lòng chọn Cập nhật hạch toán GL !</h2>");
//                    return;
//                }
                var khoa = $("#khoa_nhaptaycn").val() + "_save";
//                alert(khoa);
                if (!bsubmit)
                {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !');
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !</h2>");
                    return;
                }

// Trung bo sung phan validate data
                if (typeof validateData !== 'undefined' && typeof validateData === 'function') {
                    if (!validateData())
                    {
                        alert('vao day');
                        return false;
                    }
                }


                if (validateRequiredFields())
                    $("#" + khoa)[0].click();

                if (khoa === 'COVID_03_save')
                {
                    wait(2000);
                    onLoadData();
                }
            }

            function onSaveDataHTLai()
            {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();
                var poscd = getposfromtreecheck();
//                if(poscd === '' || poscd.length ===0)
//                {
//                    $('#message_suc_err').html("<h2 style='color: red'>Bạn chưa chốt số liệu, vui lòng chọn Xác nhận giảm lãi !</h2>");
//                    return;
//                }
//                alert(poscd);
                var khoa = $("#khoa_nhaptaycn").val() + "_save_htlai";
                if (!bsubmit)
                {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !');
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !</h2>");
                    return;
                }

// Trung bo sung phan validate data
                if (typeof validateData !== 'undefined' && typeof validateData === 'function') {
                    if (!validateData())
                        return false;
                }


                if (validateRequiredFields())
                    $("#" + khoa)[0].click();


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
                if ((evt.keyCode == 13) && (node.type == "text")) {
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
                    if (element[k].name == 'poscd')
                    {
                        if (element[k].checked == true)
                        {
                            if (element[k].value != '999999')
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

            function openClick()
            {
//                $('#divExportReport').empty();
                var khoa = $("#khoa").val() + "_open";
//                alert()
//                if (!bsubmit)
//                {
//                    alert('Bạn phải tải dữ liệu và chọn PGD thì mới mở khóa được !');
////                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải tải dữ liệu và chọn PGD thì mới mở khóa được !</h2>");
//                    return;
//                }

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

//                alert(khoa);
//                $('#divExportReport').empty();
            }

            function onSentData()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_ktgs = $("#khoa").val();
//                var capbc = $("#Grade").val();
//                                alert(capbc);

                var poscd = getposfromtreecheck();
//                alert(khoa_ktgs);
                if (khoa_ktgs != 'PHIUT_001')
                {
                    if ((poscd == null || poscd == '') && khoa_ktgs != 'QD23_004')
                    {
                        $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn phòng giao dịch cần gửi số liệu ! </h2>");
                        //                    alert('Bạn phải chọn phòng giao dịch cần gửi số liệu !');
                        return;
                    }
                }

                $("#idSend")[0].click();
                bsubmit = false;
            }
            ;

            function ExpEcel()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();

                $("#idExpEceltmp")[0].click();
                bsubmit = false;
            }
            ;
            function ExpEcelTemp()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();

                $("#idExpEceltmpTemp")[0].click();
                bsubmit = false;
            }
            ;

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
            ;
        </script>
    </head>

    <body>
        <div id="container" >
            <s:form id="id_%{khoa_nhaptaycn}" name="name_%{khoa_nhaptaycn}" action="%{khoa_nhaptaycn}" theme="simple">
                <s:hidden name="khoa_nhaptaycn" id="khoa"/>
                <s:hidden name="ReportDate" id="ReportDate" value=""/>
                <s:hidden name="Grade" id="Grade"/>
                <s:hidden name="UserName" id="UserName"/>
                <s:if test="khoa_nhaptaycn.equalsIgnoreCase('HANOI_003') || khoa_nhaptaycn.equalsIgnoreCase('HTLS2021')">
                    <div id="navParamUp" >                       
                    </s:if>  
                    <s:elseif test="khoa_nhaptaycn.equalsIgnoreCase('QD23_001') && Grade.equalsIgnoreCase('3')">
                        <div id="navParamUp" > 
                        </s:elseif>        
                        <s:else>
                            <div id="navParam" >     
                            </s:else>         
                            <div id="navParam3">     
                                <table>
                                    <tr style="height: 30px;">
                                        <s:iterator value="lstNhaptaycnParams">
                                            <s:if test="label.equalsIgnoreCase('Mã số thuế/CMTND/CIF')">
                                                <s:if test="Grade.equalsIgnoreCase('3')">

                                                </s:if>
                                                <s:else>
                                                    <td >Tìm kiếm:</td>
                                                </s:else>

                                            </s:if>
                                            <s:else>
                                                <td ><s:property value="label"></s:property>:</td>
                                            </s:else>


                                            <s:if test="fieldName.equalsIgnoreCase('nha_dt') && Grade.equalsIgnoreCase('3')">
                                                <td style="width: 1px">
                                                </s:if>
                                                <s:else>
                                                <td >
                                                </s:else>


                                                <s:if test="type.equalsIgnoreCase('T')">  
                                                    <s:if test="khoa_nhaptaycn.equalsIgnoreCase('LOAITRU_3502')">
                                                        <input type="text" style="text-align:right;width: 100px" value="10" id="<s:property value="fieldName"/>" name="<s:property value="fieldName"/>" class="" placeholder="<s:property value="label"/>" readonly="readonly"/>
                                                    </s:if>

                                                    <s:else>
                                                        <s:if test="fieldName.equalsIgnoreCase('nha_dt') && Grade.equalsIgnoreCase('3')">
                                                            <input type="hidden" style="width: 1px" value="" id="D_<s:property  value="%{fieldName}"/>" name="<s:property value="%{fieldName}"/>_TEXT"/>
                                                        </s:if>
                                                        <s:else>
                                                            <input type="text" value="" id="D_<s:property  value="%{fieldName}"/>" name="<s:property value="%{fieldName}"/>_TEXT" placeholder="<s:property value="label"/>"/>
                                                        </s:else>

                                                    </s:else>    

                                                </s:if>                                              




                                                <!-- Tungnv Neu: la L thi gen List -->
                                                <s:if test="type.equalsIgnoreCase('L')">
                                                    <s:select  list="comboList" name="%{fieldName}_LIST" listKey="key" listValue="value" id="%{fieldName}"></s:select>
                                                </s:if>
                                                <!-- Tungnv: Neu la D thi gen Date -->
                                                <s:if test="type.equalsIgnoreCase('D')">  
                                                    <sj:datepicker name="%{fieldName}_DATE" value="%{new java.util.Date()}"  id="%{fieldName}_DATE"
                                                                   placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL" onChangeTopics="changeTopic"/>                                           

                                                </s:if>
                                            </td>

                                        </s:iterator>     
                                        <s:if test="khoa_nhaptaycn.equalsIgnoreCase('QD23_001') && Grade.equalsIgnoreCase('3')">
                                            <td>&nbsp;|&nbsp</td>


                                            <td >Số thông báo:</td>
                                            <td>
                                                <input type="text" style="text-align:right;width: 100px" value="AAA" id="soqd" name="soqd" class="" placeholder="Số duyết định" />
                                            </td>  
                                            <td >Ngày thông báo:</td>
                                            <td>
                                                <sj:datepicker name="ngay_qd_DATE" value="%{new java.util.Date()}"  id="ngay_qd_DATE"
                                                               placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/> 
                                            </td>
                                            <td >Thông báo lần:</td>
                                            <td >
                                                <input type="text" style="text-align:right;width: 50px" value="1" id="lanqd" name="lanqd" class=""  placeholder="Lần QĐ" />
                                            </td> 
                                            <td >Tính chất vốn:</td>
                                            <td>
                                                <select name="tc_von" id="tc_von">
                                                    <option value="0">--Chọn--</option>
                                                    <option value="1">Tái cấp vốn</option>
                                                    <option value="2">Điều chuyển</option>
                                                </select>
                                            </td> 
                                            <td>&nbsp;|&nbsp</td>




                                        </s:if>   


                                        <s:if test="khoa_nhaptaycn.equalsIgnoreCase('QD23_001') && Grade.equalsIgnoreCase('3')"> 
                                        </tr>
                                        <tr>
                                            <td >Số Tide:</td>
                                            <td  >
                                                <!--<input type="text" style="text-align:right;width: 150px"  id="sotide" name="sotide" class=""   placeholder="Số tài khoản"/>-->
                                                <s:select  style="width: 200px;"  list="lstTide" id="sotide" name="sotide" listKey="sKey" listValue="sDesc"></s:select>
                                                </td> 

                                                <td>&nbsp;|&nbsp</td>
                                                <td >Chốt kế hoạch:</td>
                                                <td>
                                                    <select name="chot_kh" id="chot_kh">
                                                        <option value="-1">--Chọn--</option>
                                                        <option value="0">Mở</option>
                                                        <option value="1">Chốt</option>
                                                        <!--<option value="2">Điều chuyển</option>-->
                                                    </select>
                                                </td> 
                                        </s:if>            
                                        <s:if test="khoa_nhaptaycn.equalsIgnoreCase('HTLS2021')">
                                        </tr>
                                        <tr>
                                            <td >Giải ngân:</td>
                                            <td>
                                                <select name="giaingan" id="giaingan">
                                                    <option value="-1">--Tất cả--</option>
                                                    <option value="1">Giải ngân 12/2021</option>
                                                    <!--<option value="2">Điều chuyển</option>-->
                                                </select>
                                            </td> 
                                            <td >Nhà đầu tư:</td>
                                            <td  >                                               
                                                <s:select  style="width: 200px;"  list="lstNhadautu" id="nha_dt" name="nha_dt" listKey="sKey" listValue="sDesc"></s:select>
                                                </td> 
                                        </s:if>         

                                        <td >
                                            &nbsp;&nbsp;&nbsp;
                                            <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                                       onBeforeTopics="beforediv_data"
                                                       onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                            <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                                        </td>

                                        <s:if test="(khoa_nhaptaycn.equalsIgnoreCase('QD23_007') || khoa_nhaptaycn.equalsIgnoreCase('QD23_008')) && Grade.equalsIgnoreCase('2')
                                                    || (!Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('GQVL_01'))">                                        
                                        </s:if>
                                        <s:else>
                                            <s:if test="khoa_nhaptaycn.equalsIgnoreCase('HTLS2021') && Grade.equalsIgnoreCase('1')">
                                                <td>&nbsp;&nbsp;&nbsp;<input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Xác nhận lãi giảm"/> </td>

                                                <td>&nbsp;&nbsp;&nbsp;<input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveDataHTLai()" value="Cập nhật hạch toán GL"/> </td>
                                                </s:if>     
                                                <s:elseif test="khoa_nhaptaycn.equalsIgnoreCase('HTLS2021') && Grade.equalsIgnoreCase('2')">
                                                <td>&nbsp;&nbsp;&nbsp;<input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Chốt số liệu"/> </td>
                                                </s:elseif>
                                                <s:else>
                                                <td>&nbsp;&nbsp;&nbsp;<input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/> </td>
                                                </s:else>     

                                        </s:else>







                                        <td >
                                            &nbsp;&nbsp;&nbsp;
                                            <s:if test="((khoa_nhaptaycn.equalsIgnoreCase('NTMOI_001') || khoa_nhaptaycn.equalsIgnoreCase('HSSV_001')
                                                  || khoa_nhaptaycn.equalsIgnoreCase('BDP_001')                                          
                                                  || khoa_nhaptaycn.equalsIgnoreCase('QLDB_001')
                                                  || khoa_nhaptaycn.equalsIgnoreCase('COVID_03')                                                  
                                                  || khoa_nhaptaycn.equalsIgnoreCase('NHAPTAYCN_01')) && 
                                                  Grade.equalsIgnoreCase('2')) or ( khoa_nhaptaycn.equalsIgnoreCase('CN23_01') && 
                                                  Grade.equalsIgnoreCase('1'))"> 
                                                <!--<input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>-->                                           
                                            </s:if>                                       
                                            <s:else>
                                                <s:if test="(!Grade.equalsIgnoreCase('3') && ||khoa_nhaptaycn.equalsIgnoreCase('QD23_001'))
                                                      ||(Grade.equalsIgnoreCase('3') && khoa_nhaptaycn.equalsIgnoreCase('LSTP_001'))||(Grade.equalsIgnoreCase('3') && khoa_nhaptaycn.equalsIgnoreCase('QD23_001'))">
                                                    <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>                                            
                                                </s:if> 
                                                <s:if test="Grade.equalsIgnoreCase('3') && (khoa_nhaptaycn.equalsIgnoreCase('LOAITRU_01') || khoa_nhaptaycn.equalsIgnoreCase('LOAITRU_3502'))">
                                                    <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>                                            
                                                </s:if>   
                                            </s:else>
                                            <!--                                        &nbsp;&nbsp;&nbsp;-->

                                            <s:if test="(Grade.equalsIgnoreCase('2') && !khoa_nhaptaycn.equalsIgnoreCase('SMS_001') && !khoa_nhaptaycn.equalsIgnoreCase('HTLS_2023')) 
                                                  || (Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('QD23_004'))">                                       
                                                <s:url id="idSendData" action="sendPhiUT.action"></s:url>                                      
                                                <sj:submit id="idSend" name="nameSend" href="%{idSendData}" value="Gửi dữ liệu" targets="divExportReport"
                                                           onBeforeTopics="beforediv_send"
                                                           onCompleteTopics="completediv_send" cssStyle="display:none"/>

                                                <s:if test="(Grade.equalsIgnoreCase('2') && khoa_nhaptaycn.equalsIgnoreCase('HTLS2021'))">
                                                </s:if> 
                                                <s:if test="(!Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('GQVL_01'))">
                                                </s:if>
                                                <s:else>
                                                    <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu"/>
                                                </s:else>

                                            </s:if>    
                                            <s:if test="(Grade.equalsIgnoreCase('3') && khoa_nhaptaycn.equalsIgnoreCase('QD23_004'))">                                       
                                                <input type="button" id="idUnlockDatatmp" name="namesaveDatatmp"  onclick="openClick()" value="Mở khóa"/>

                                            </s:if>      
                                            <!--                                    <s:if test="Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('COVID_GIAINGAN')">  
                                                <s:url id="idExpEcel" action="%{khoa_nhaptaycn}_ExpExcel.action"></s:url>                                      
                                                <sj:submit id="idExpEceltmp" name="nameSend" href="%{idExpEcel}" value="Xuất Excel" targets="divExportReport"
                                                           onBeforeTopics="beforediv_send"
                                                           onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                <input type="button" id="idReLoadtmp" name="nameidReLoadtmp"  onclick="ExpEcel()" value="Xuất Excel" style="width:122px;height:25px;color: red;"/>
        
                                            </s:if>      -->
                                            <s:if test="khoa_nhaptaycn.equalsIgnoreCase('QD23_001')">  
                                                <s:url id="idExpEcel" action="QD23_001_ExpExcel.action"></s:url>                                      
                                                <sj:submit id="idExpEceltmp" name="nameSend" href="%{idExpEcel}" value="Xuất 01.BCTK" targets="divExportReport"
                                                           onBeforeTopics="beforediv_send"
                                                           onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                <input type="button" id="idReLoadtmp" name="nameidReLoadtmp"  onclick="ExpEcel()" value="Xuất 01.BCTK" />
                                                &nbsp;&nbsp;&nbsp;
                                                <s:url id="idExpEcelTemp" action="QD23_001_ExpExcel_Temp.action"></s:url>                                      
                                                <sj:submit id="idExpEceltmpTemp" name="nameSendTemp" href="%{idExpEcelTemp}" value="Mẫu danh sách NLĐ" targets="divExportReport"
                                                           onBeforeTopics="beforediv_send"
                                                           onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                <input type="button" id="idReLoadtmpTemp" name="nameidReLoadtmpTemp"  onclick="ExpEcelTemp()" value="Mẫu danh sách NLĐ" />

                                            </s:if>      
                                                
                                            <s:if test="Grade.equalsIgnoreCase('3') && khoa_nhaptaycn.equalsIgnoreCase('HTLS_2023')">  
                                                <sj:submit class="cmd" href="#" onclick="callDirectLink('khvn_open_upload_qt_kh?');" value="Upload Excel"> </sj:submit>   
                                            </s:if>   
                                                
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
                                    <s:if test="khoa_nhaptaycn.equalsIgnoreCase('HANOI_003')">                               
                                        <tr>
                                            <td>
                                                <p class="normal_font">File báo cáo:</p>
                                            </td>
                                            <td colspan="2">
                                                <s:file label="File báo cáo" name="fileUpload" size="45" theme="simple"/>                        
                                            </td>
                                            <td colspan="2" align="right">

                                                &nbsp;&nbsp;
                                                <s:url id="idUpData" action="uploadExcelKyQuy.action"></s:url>                                      
                                                <sj:submit id="idUpExcel" name="nameTrans" href="%{idUpData}" value="Upload dữ liệu" targets="divExportReport"
                                                           onBeforeTopics="before-next"
                                                           onCompleteTopics="after-next" cssStyle="display:none"/>
                                                <input type="button" id="idUploadExcel" name="nameidTranstmp"  onclick="onUpExcel()" value="Upload dữ liệu"/>

                                            </td>
                                        </tr>    
                                    </s:if> 
                                </table>    
                            </div>
                        </div>
<!--Bắt đầu HANOI_004-->
                        <div>
                            <s:if test="khoa_nhaptaycn.equalsIgnoreCase('HANOI_004')"> 
                                <style>
                                    #navParam{
                                        display: none;
                                    }
                                </style>
                                <br>
                                <table>
                                    <tr>
                                        <td>Ngày báo cáo
                                            <input type="text" id="dtNgayBC" name="dtNgayBC">
                                            Mã xã
                                            <input type="text" id="strMaxa" name="strMaxa" value="">
                                            <input type="button" id="DownloadFile" name="DownloadFile" value="Tải File"/>&nbsp;&nbsp;
                                            Chọn file Excel:
                                            <input type = "file" name = "Mfile" id="Mfile" />
                                            <input type="button" id="UpLoadFile" name="UpLoadFile" value="Upload File"/>
                                            <a href="#" id="DownLF" style="display: none;"></a>

                                        </td>
                                    </tr>    
                                </table>
                            </s:if> 
                        </div>
                        <!--Kểt thúc đầu HANOI_004 (Nhớ đoạn dưới còn 1 đoạn HANOI_004)-->
                        <s:if test="(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('NTMOI_001')) ||
                              (Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('USER_001'))||
                              (Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('NHAPTAYCN_03'))
                              ||(Grade.equalsIgnoreCase('3') && khoa_nhaptaycn.equalsIgnoreCase('LSTP_001'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('BDP_001'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('LEAVELOCAL'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('SMS_001'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('HANOI_001'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('HANOI_002'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('HANOI_003'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('HANOI_004'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('CN25_KTNB'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('KYQUY_04'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('KYQUY_05'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('COVID_03'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('COVID_GIAINGAN'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('DGHC_01'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('COVID_04'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('VUNGKK_01'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('CN23_01'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('QD23_001'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('QD23_004'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('DIEUCHUYENTO_01'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('KSNB_01'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('QLDB_001'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('QD23_007'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('QD23_008'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('HTLS2021'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('GQVL_01'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('NDT2021'))
                              ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('QD23_004'))||
                              (khoa_nhaptaycn.equalsIgnoreCase('HTLS_2023') && Grade.equalsIgnoreCase('2')) ||
                              (khoa_nhaptaycn.equalsIgnoreCase('HTLS_2023') && Grade.equalsIgnoreCase('3'))"                              
                              >
                            <div id="containParm_full" align="center">
                                <div id="divExportReport"></div>
                                <div align="right"  id="divExportReportLink"></div>
                            </div>
                        </s:if>
                        <s:else>
                            <s:if test="khoa_nhaptaycn.equalsIgnoreCase('QD23_001')">
                                <s:if test="Grade.equalsIgnoreCase('2')">
                                    <div id="containTreeQD23_2">
                                    </s:if>
                                    <s:else>
                                        <div id="containTreeQD23_3">
                                        </s:else>

                            </s:if>                             
                            <s:else>
                                        <div id="containTree">
                                        </s:else>

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
                                    <s:if test="khoa_nhaptaycn.equalsIgnoreCase('QD23_001')">
                                        <s:if test="Grade.equalsIgnoreCase('2')">
                                            <div id="containParmQD23_2" align="center">
                                            </s:if>
                                            <s:else>
                                                <div id="containParmQD23_3" align="center">
                                                </s:else>

                                                <div id="divExportReport"></div>
                                                <div id="divExportReport"></div>
                                            </div>
                                        </s:if>
                                        <s:else>
                                            <div id="containParm" align="center">
                                                <div id="divExportReport"></div>
                                                <div id="divExportReport"></div>
                                            </div>
                                        </s:else>

                                    </s:else>                     

                                </s:form>
                            </div>
                            <!--                            <script>
                                                            $.subscribe('changeTopic', function (event, data) {
                                                                //alert('Date : '+event.originalEvent.dateText);
                                                                var strDate = event.originalEvent.dateText;
                                                                var year = strDate.substr(6, 4);
                                                                var month = strDate.substr(3, 2);
                                                                var day = strDate.substr(0, 2);
                                                                var iDate = year + "" + addZeroToLead(month) + "" + addZeroToLead(day);
                                                                //alert(iDate);
                                                                $("#ReportDate").val(iDate);
                                                                getLockStatus();
                                                                $("#loadDatatmp").click();
                                                            });
                            
                            
                                                            // TrungNT88 sua
                                                            function getLockStatus() {
                                                                var key = $("#khoa").val();
                                                                var reportDate = $("#ReportDate").val();
                                                                var grade = $("#Grade").val();
                                                                var userName = $("#UserName").val();
                                                                $.ajax({
                                                                    type: "GET",
                                                                    url: "GetLockStatus?" + "Key=" + key + "&ReportDate=" + reportDate + "&ReportGrade=" + grade + "&UserName=" + userName,
                                                                    success: function (res) {
                                                                        var status = res.lockStatus;
                                                                        //alert(status);
                                                                        if (status === 0) {
                                                                            //alert(status);
                                                                            $('#idsaveDatatmp').prop('disabled', false);
                                                                            $('#idSendtmp').prop('disabled', false);
                            
                                                                        } else {
                                                                            //alert(status);
                                                                            $('#idsaveDatatmp').prop('disabled', true);
                                                                            $('#idSendtmp').prop('disabled', true);
                                                                        }
                                                                    },
                                                                    error: function (res) {
                                                                        alert("No values found..!!");
                                                                    }
                                                                });
                            
                                                            }
                                                            function addZeroToLead(value) {
                                                                var strVal = value.toString();
                                                                if (strVal.length < 2) {
                                                                    return "0" + value;
                                                                } else {
                                                                    return strVal;
                                                                }
                                                            }
                                                            $(document).ready(function () {
                                                                var date = new Date();
                                                                var month = date.getMonth();
                                                                var year = date.getFullYear(); //nam
                                                                var day = getDaysOfMonth(month, year)
                                                                var daynow = day + "/" + month + "/" + year;
                                                                //
                                                                var today = new Date();
                                                                var dd = today.getDate();
                                                                var mm = today.getMonth() + 1;
                                                                var yyyy = today.getFullYear();
                                                                if (dd < 10) {
                                                                    dd = '0' + dd;
                                                                }
                                                                if (mm < 10) {
                                                                    mm = '0' + mm;
                                                                }
                                                                var today = dd + '/' + mm + '/' + yyyy;
                                                                if (document.getElementById('khoa_nhaptaycn').value == 'QD23_001' || document.getElementById('khoa_nhaptaycn').value == 'QD23_007') {
                                                                    document.getElementById('ngay_bc_DATE').value = today;
                                                                } else {
                                                                    document.getElementById('ngay_bc_DATE').value = daynow;//daynow;
                                                                }
                                                                var iDate = year + "" + addZeroToLead(month) + "" + addZeroToLead(day);
                                                                //Gan quy mac dinh
                                                                //            $("#ngay_bc_DATE").val(day + "/" + month + "/" + year);
                                                                $("#ReportDate").val(iDate);
                                                                getLockStatus();
                            //                                    $("#loadDatatmp").click();
                                                            });
                            
                                                        </script>-->
                            <script>

            document.getElementById('ngay_bc_DATE').value = '31/12/2023';
            //Gan quy mac dinh
//            $("#ngay_bc_DATE").val(day + "/" + month + "/" + year);
            
            
        
    function callDirectLink(link) {
            var ht = screen.availHeight / 5 + 35;
            var wt = screen.availWidth / 5 + 20;

            var resize = window.open(link
                    + "random=" + Math.random(),
                    "IMS_REPORTS_FRM2", "height=" + ht + ",width=" + wt
                    + ",left=0,top=0,directories=no,status=no,menubar=no,\n\
        personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

            if (navigator.userAgent.indexOf('Chrome') !== -1
                    && parseFloat(
                            navigator.userAgent.substring(
                                    navigator.userAgent.indexOf('Chrome') + 7
                                    ).split(' ')[0]) >= 15) {
                resize.resizeBy(wt, ht);
            } else {
                resize.resizeTo(wt, ht);
            }
            resize.moveTo(wt, ht);
            resize.focus();
        }
        </script>
        
                        </div>
                                         <!--Bắt đầu HANOI_004-->                
                        <script>
                            $(function () {
                                $("#dtNgayBC").datepicker({dateFormat: 'dd/mm/yy', showOn: "button",
                                    buttonImage: "img/icon-ui_datepicker.png",
                                    buttonImageOnly: true,
                                    dateFormat: 'dd/mm/yy',
                                    showButtonPanel: true,
                                    buttonText: "icono",
                                    changeMonth: true,
                                    changeYear: true,
                                    // showOn: "both"}).val('31/10/2022');
                                    showOn: "both"}).val(new Date(new Date().getFullYear(), new Date().getMonth(), 1).toLocaleDateString("zh-HK", {year: 'numeric', month: '2-digit', day: '2-digit'}));
                            });
                            $("#UpLoadFile").click(function () {
                                $('#Mfile').prop('disabled', true);
                                $('#UpLoadFile').prop('disabled', true);
                                $('#DownloadFile').prop('disabled', true);
                                $('#dtNgayBC').prop('disabled', true);
                                $('#strMaxa').prop('disabled', true);
                                var fd = new FormData();
                                var files = $('#Mfile')[0].files[0];
                                fd.append('Mfile', files);
                                $.ajax({
                                    url: 'HN04UploadFile.action',
                                    type: 'POST',
                                    data: fd,
                                    contentType: false,
                                    processData: false,
                                    success: function (response) {
                                        if (response != 0) {
                                            alert('Thành công');
                                            $('#Mfile').prop('disabled', false);
                                            $('#UpLoadFile').prop('disabled', false);
                                            $('#DownloadFile').prop('disabled', false);
                                            $('#dtNgayBC').prop('disabled', false);
                                            $('#strMaxa').prop('disabled', false);
                                        } else {
                                            alert('File đưa lên server thất bại');
                                            $('#Mfile').prop('disabled', false);
                                            $('#UpLoadFile').prop('disabled', false);
                                            $('#DownloadFile').prop('disabled', false);
                                            $('#dtNgayBC').prop('disabled', false);
                                            $('#strMaxa').prop('disabled', false);
                                        }
                                    }
                                });
                            });


                            $("#DownloadFile").click(function () {
                                var sDate, sMax;
                                sDate = $('#dtNgayBC').val();
                                sMax = $('#strMaxa').val();
                                $("#DownLF").attr("href", "HN04SaveFile.action?dtNgayBC=" + sDate + "&strMaxa=" + sMax);
                                location.href = $('#DownLF').attr('href');
                            });
                        </script>
                        <!--Kết thúc HANOI_004-->
                        </body>
                        </html>
