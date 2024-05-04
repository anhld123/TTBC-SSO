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
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<s:head/>
<sj:head/>

<!DOCTYPE html>
<html>
    <head>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
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
            
            function onLoadData()
            {
//                var grade = $.session.get('reportGrade').toString();
//                $.session.get()
//                alert(grade);
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                $('#divExportReportLink').empty();
                var ngay_bc = $("#ngay_bc").val();
                var khoa_nhaptaycn = $("#khoa_nhaptaycn").val();
                var lv_day = (ngay_bc.substr(0, 2));
                var lv_month = (ngay_bc.substr(3, 2));

                $("#loadData")[0].click();
                bsubmit = true;
//                return true;
            }
            
            function onSearchData()
            {               
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                $('#divExportReportLink').empty();
                var ngay_bc = $("#ngay_bc").val();
                var khoa_nhaptaycn = $("#khoa_nhaptaycn").val();
                var lv_day = (ngay_bc.substr(0, 2));
                var lv_month = (ngay_bc.substr(3, 2));
                
                $("#searchData")[0].click();
                bsubmit = true;
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
                        //alert('vao day');
                        return false;
                    }
                }

                if (validateRequiredFields())
                {                    
                    $("#"+khoa)[0].click();
                }

//                if (khoa === 'HUYDONG_2024_save')
//                {
//                    wait(2000);
//                    onLoadData();
//                }
            }
                        
    
            $(document).ready(function () {
              $("#saveData").click(function () {
                  document.getElementById("idsaveDatatmp").disabled = true;        
                  setTimeout(function () {
                      document.getElementById("idsaveDatatmp").disabled = false;     
                  }, 3000);
              });
            });

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
                onLoadData();
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
                if (khoa_ktgs !== 'PHIUT_001')
                {
                    if ((poscd === null || poscd === '') && khoa_ktgs !== 'QD23_004')
                    {
                        $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn phòng giao dịch cần gửi số liệu ! </h2>");
                        //                    alert('Bạn phải chọn phòng giao dịch cần gửi số liệu !');
                        return;
                    }
                }

                $("#idSend")[0].click();
                bsubmit = false;
            };

            function ExpEcel()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();

                $("#idExpEceltmp")[0].click();
                bsubmit = false;
            };
            
            function ExpEcelTemp()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();

                $("#idExpEceltmpTemp")[0].click();
                bsubmit = false;
            };

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
            };
        </script>
    </head>

    <body>
        <s:form id="id_%{khoa_nhaptaycn}" name="name_%{khoa_nhaptaycn}" action="%{khoa_nhaptaycn}" theme="simple">
            <s:hidden name="khoa_nhaptaycn" id="khoa"/>
            <s:hidden name="ReportDate" id="ReportDate" value=""/>
            <s:hidden name="Grade" id="Grade"/>
            <s:hidden name="UserName" id="UserName"/>
            <fieldset>
                <legend><b>Tìm kiếm dữ liệu</b></legend> 
                <div style="display: flex;justify-content: center;">
                    <table style="align-self: center;">
                    <tr style="text-align: center;">
                        <td>Ngày báo cáo </td>
                    <td>
                        <sj:datepicker name="ngay_bc_DATE"                                        
                                       id="ngay_bc" 
                                       placeholder="DD/MM/YYYY" 
                                       changeYear="true" 
                                       changeMonth="true" 
                                       displayFormat="dd/mm/yy" 
                                       cssClass="NGAY_SL" 
                                       onChangeTopics="changeTopic"/>                                              
                    </td>
                    <td>Cán bộ</td> 
                    <td><select id="cboCanBo" name="cboCanBo">
                            <option value="000000">----Chọn cán bộ----</option>
                            <s:iterator value="lstCanBo">
                                <option value='<s:property value="MaCB"/>'><s:property value="TenCB"/></option>
                            </s:iterator>
                        </select></td>
                    <td>Chỉ tiêu được giao</td>
                    <td><input type="text" style="text-align: right; width: 150px;" id="txtChitieu" name="txtChitieu" value="1000000000" class="number">
                    </td>
                    <td style="color: red">Chỉ hiện những số đã gắn cán bộ 
                    </td>
                    <td>
                        <input type="checkbox" checked="true" name="flgFilter" id="flgFilter" disabled="true">
                    </td>      
                    
                    <td >
                        &nbsp;&nbsp;&nbsp;
                        <sj:submit id="loadData" name="loadData" value="Tải dữ liệu đã nhập" targets="divExportReport"
                                   onBeforeTopics="beforediv_data"
                                   onCompleteTopics="completediv_data" cssStyle="display:none"/>
                        <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Danh sách đã gắn"/>
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
                    
                    <tr style="text-align: center;">
                        <td colspan="12" style="text-align: center;">
                            <label>Tra cứu </label>
                            &nbsp;&nbsp;&nbsp;
                        <input type="text" placeholder="Nhập thông tin khách hàng, số tài khoản, số sổ" style="width: 300px;" name="searchKey"></input>                                             
                        &nbsp;&nbsp;&nbsp;    
                        <s:url id="urlSearchData" action="search_HUYDONG_2024.action"></s:url>
                        <sj:a id="searchData" 
                              name="searchData" 
                              href="%{urlSearchData}"
                              formIds="id_%{khoa_nhaptaycn}"
                                   value="Tải dữ liệu đã nhập" targets="divExportReport"
                                   onBeforeTopics="beforediv_data"
                                   onCompleteTopics="completediv_data" cssStyle="display:none"/>
                        <input type="button" id="searchDatatmp" name="nameloadDatatmp"  onclick="onSearchData()" value="Tìm kiếm"/>
                    &nbsp;&nbsp;&nbsp;                            
                    
                    <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Gắn sổ"/> </td>

                    </td>
                    </tr>

                </table> 
                </div>
                   
            </fieldset>       

            <s:if test="(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('HUYDONG_2024'))">
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
                                
                $('input.number').css({"text-align": "right"});                
                $('.number').number(true, 0);  
                
                
                var currentDate = new Date();
                var day = currentDate.getDate()-1;
                var month = currentDate.getMonth() + 1; // Note: January is 0
                var year = currentDate.getFullYear();
                var formattedDate = day + '/' + month + '/' + year;
                document.getElementById('ngay_bc').value = formattedDate;
                
                $(".NGAY_SL").css({"width": "80px"});                
                $('.number').number(true, 0);
            });                        
        </script>
    </body>
</html>
