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
//                var khoa_nghiquyet11cp = $("#khoa_nghiquyet11cp").val();
//                var maxa = $("#maxa").val();
////                alert(maxa);
//                if(maxa === "000000")
//                {
//                    $('#message_suc_err').html("<h2 style='color: red'>Bạn cần chọn mã xã trước khi tải số liệu !</h2>");
//                    return;
//                }

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
                var khoa = $("#khoa_nghiquyet11cp").val() + "_save";
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
                var khoa = $("#khoa_nghiquyet11cp").val() + "_save_htlai";
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
                var idform = 'id_' + '<s:property value="khoa_nghiquyet11cp"/>';
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
//                $('#divExportReport').empty();
                var khoa = $("#khoa").val() + "_open";
//                alert()
//                if (!bsubmit)
//                {
//                    alert('Bạn phải tải dữ liệu và chọn PGD thì mới mở khóa được !');
////                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải tải dữ liệu và chọn PGD thì mới mở khóa được !</h2>");
//                    return;
//                }

                var idform = 'idform_open_' + '<s:property value="khoa_nghiquyet11cp"/>';
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
                    $("#mato_data > option").each(function () {
                        var2 = $(this).val().substr(0, 6);
                        if (val.trim() == var2.trim()) {
                            $(this).val() == vartxt ? selected = " selected" : selected = "";
                            $("#mato").prepend("<option value='" + $(this).val() + "' " + selected + "> " + $(this).text() + " </option>");
                        }
                    });
                    
                    $("#mato").html($("#mato option").sort(function (a, b) {
                        return a.text == b.text ? 0 : a.text < b.text ? -1 : 1;
                    }));
                };



        </script>
    </head>
<!--new java.util.Date()-->
    <body>
        <div id="container" >
            <s:form id="id_%{khoa_nghiquyet11cp}" name="name_%{khoa_nghiquyet11cp}" action="%{khoa_nghiquyet11cp}" theme="simple">
                <s:hidden name="khoa_nghiquyet11cp" id="khoa"/>
                <s:hidden name="ReportDate" id="ReportDate" value=""/>
                <s:hidden name="Grade" id="Grade"/>
                <s:hidden name="UserName" id="UserName"/>

                <div id="navParamUp" >     
                    <div id="navParam3">     
                        <table>
                            <tr style="height: 30px;">
                                <td>Ngày BC</td>
                                <td>
                                    <sj:datepicker name="ngay_bc_DATE" value="%{'30/06/2022'}"  id="ngay_bc_DATE"
                                                   placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL" onChangeTopics="changeTopic"/> 
                                </td>
                                <td>Chương trình:</td>
                                <td>                                               
                                    <s:select  style="width: 150px;"  list="lstChuongtrinh" id="chuongtrinh" name="chuongtrinh" listKey="sKey" listValue="sDesc"></s:select> &nbsp;&nbsp;&nbsp;
                                </td>
                                <td >Mã xã:</td>
                                <td  >                                               
                                    <s:select  style="width: 229px;"  list="lstMaxa" id="maxa" name="maxa" listKey="sKey" listValue="sDesc"
                                               onchange="reLoadValue(this.value)"></s:select>  &nbsp;&nbsp;&nbsp;
                                </td>
                                <td >Mã tổ:</td>
                                <td>
                                    <s:select  style="width: 220px;"  list="lstMato" id="mato" name="mato" listKey="sKey" listValue="sDesc"></s:select>
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
                                <!--  <td >Phân loại:</td>
                                <td  >                                               
                                    <s:select  style="width: 100px;"  list="lstPhanloai" id="phanloai" name="phanloai" listKey="sKey" listValue="sDesc"></s:select>
                                </td>-->
                            </tr>  
                            <tr>
<!--                                    <td >Giải ngân:</td>
                                    <td  >                                               
                                        <s:select  style="width: 100px;"  list="lstGiaiNgan" id="giaingan" name="giaingan" listKey="sKey" listValue="sDesc"></s:select>
                                    </td>-->
                                <td >Trạng thái:</td>
                                            <td>
                                                <select name="giaingan" id="giaingan">
                                                    <option value="-1">--Tất cả--</option>
                                                    <option value="1">Món vay tất toán</option>                                                    
                                                </select>
                                            </td>         

                                <td >Nhà đầu tư:</td>
                                <td colspan="3">                                               
                                    <s:select  style="width: 345px;"  list="lstNhadautu" id="nha_dt" name="nha_dt" listKey="sKey" listValue="sDesc"></s:select>
                                </td>     
                                <td colspan="2">                                        
                                    <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                               onBeforeTopics="beforediv_data"
                                               onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                    <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                                    <s:if test="Grade.equalsIgnoreCase('1')">
                                        <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Xác nhận HTLS"/>
                                    </s:if>
                                    <s:else>
                                        <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Chốt số liệu"/>
                                    </s:else>    
                                    
                                </td>
<!--                                <td colspan="2">
                                    <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Xác nhận lãi giảm"/>
                                </td>-->
                                <td></td>
<!--                                <td colspan="2">
                                    <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveDataHTLai()" value="Cập nhật hạch toán GL"/> </td>                                -->
                                <td  colspan="2">
                                    <div id="loadingImageDiv_data" style="margin-left: 20px;display: none;" >
                                        <img id="loadingImage" src='img/loading.gif' border='0' >
                                    </div>
                                </td>
                                <td colspan="2">
                                    <div id="message_suc_err"> 
                                    </div>
                                </td>
                            </tr>
                        </table>    
                    </div>
                </div>

                <s:if test="khoa_nghiquyet11cp.equalsIgnoreCase('NQ11CP_001')">
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
        </div>
        <script>
        $(document).ready(function () {            
            document.getElementById('ngay_bc_DATE').value = "30/06/2022";
        })
        </script>
    </div>
</body>
</html>
