<%-- 
    Document   : input_main
    Created on : Oct 26, 2015, 1:45:53 PM
    Author     : LION
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />

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
                overflow: scroll;
            }

            #containParm{
                width: 84%;
                height: 450px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
            }
            #containParm_full{
                width: 84%;
                height: 9%;
                /*padding-left: 5px;*/
                float: left;
                
                background: #f2f2f2
                /*overflow: scroll;*/
            }
            
            #containParm_full_2{
                width: 84%;
                /*height: 52%;*/
                /*padding-left: 5px;*/
                float: left;
                /*background: #DCDCDC*/
                /*overflow: scroll;*/
            }
            
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: 12px;
            }

            .report_group_form{
                width: 100%;
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
            
            #divMuc{
            font: 12px Arial, Helvetica, sans-serif;
            text-align: left;
            color: red;
            /*padding-right: 700px;*/            
        }
        
        #containReport{
                width: 100%;
                height: 132px;
                padding-left: 5px;
                float: left;
                /*overflow: scroll;*/
                overflow-x: hidden;
            }
        .cn {
                display: table-cell;
                width: 500px;
                height: 500px;
                vertical-align: middle;
                text-align: center;
              }

              .inner {
                display: inline-block;
                width: 200px; height: 200px;
              }  
            
        </style>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        </style>
        
        <script>
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});                
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 3);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_THUTU").css({"width": "20px"});
                $(".TD_CHECKBOX").css({"width": "20px"});
                $(".TD_MACHUNG").css({"width": "30px"});
                $(".TD_TENCHUNG").css({"width": "150px"});
                $(".TD_PHIAD").css({"width": "80px"});
                $(".TD_CHITIEU").css({"width": "50px"});
                $(".TD_SOTK").css({"width": "50px"});
                $(".TD_TENKH").css({"width": "320px"});
                $(".TD_SP").css({"width": "70px"});
                $(".TD_SOTIEN").css({"width": "70px"});                
                $(".TD_GHICHU").css({"width": "200px"});

            });
            
            $(document).ready(function () {
            $("#allCheck_pgd").change(function () {
                $(".checkboxpgd").prop('checked', $(this).prop("checked"));
            });
            
            $("#allCheck_sp").change(function () {
                $(".checkboxsp").prop('checked', $(this).prop("checked"));
            });
        });
        
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        
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
                var khoa_nhaptaycn = $("#khoa_nhaptaycn").val();
//                alert(khoa_nhaptaycn);

                $("#loadData")[0].click();
                bsubmit = true;
//                return true;
            }            
            function onSaveData()
            {                
                $('#message_suc_err').empty();               
                var khoa = $("#khoa_nhaptaycn").val() + "_save";
//                alert(khoa)
                if (!bsubmit)
                {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !');
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !</h2>");
                    return;
                }
                
//                if (validateRequiredFields())
                    $("#" + khoa)[0].click();                
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
            
            function onSentData()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_ktgs = $("#khoa").val();

                var poscd = getposfromtreecheck();
//                alert(khoa_ktgs);
                if(khoa_ktgs != 'PHIUT_001')
                {
                    if (poscd == null || poscd == '')
                    {
                        $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn phòng giao dịch cần gửi số liệu ! </h2>");
    //                    alert('Bạn phải chọn phòng giao dịch cần gửi số liệu !');
                        return;
                    }
                }
                
                $("#idSend")[0].click();
                bsubmit = false;
            }
            
            function loadbc()
            {
                var nha_dt = $("#nha_dt").val();
                 $("#tt_nha_dt").val(nha_dt);
                //alert(nha_dt);
//                alert('vao onchange');
                  $('#divExportReport').empty();
                  $('#divExportReport1').empty();
                 $("#loadPosSp").trigger("click");
            }
            
//            function loadbc(value){
//                alert(value);
//                window.location="load_pos_sp?ValueToSubmit="+value; //or you can submit a form from here or make an ajax call
//              }

        </script>
    </head>

    <body>
        <div id="container" >
            <s:form id="id_%{khoa_nhaptaycn}" name="name_%{khoa_nhaptaycn}" action="%{khoa_nhaptaycn}" theme="simple">
                <s:hidden name="khoa_nhaptaycn" id="khoa"/>
                <s:hidden name="nha_dt" id="nhadt"/>
                <div id="navParam" >
                    <div id="navParam3">     
                        <table>
                            <tr style="height: 30px;">
                                <s:iterator value="lstNhaptaycnParams">
                                    <td ><s:property value="label"></s:property>:</td>
                                        <td >
                                        <s:if test="type.equalsIgnoreCase('T')">                                     
                                            <%--<s:textfield  name="%{fieldName}_TEXT"></s:textfield>--%>
                                            <input type="text" value="" id="D_<s:property  value="%{#rowstatus.index}"/>" name="<s:property value="fieldName"/>_TEXT" placeholder="<s:property value="label"/>"/>
                                        </s:if>
                                        <s:url id="reloadbc" action="load_loaibc.action">
                                            
                                        </s:url>    
                                        <!-- Tungnv Neu: la L thi gen List -->
                                        <s:if test="!Grade.equalsIgnoreCase('3')">
                                            <s:if test="type.equalsIgnoreCase('L')">
                                                <s:select  list="comboList" name="%{fieldName}_LIST" listKey="key" listValue="value" id="%{fieldName}" onchange="loadbc()"></s:select>
                                            </s:if>
                                        </s:if>
                                        
                                        <!-- Tungnv: Neu la D thi gen Date -->
                                        <s:if test="type.equalsIgnoreCase('D')">                                             
                                            <sj:datepicker name="%{fieldName}_DATE" value="%{new java.util.Date()}" 
                                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/>

                                            <!--                                            <sj:datepicker name="%{fieldName}" id="%{fieldName}"
                                                           value="%{new java.util.Date()}" onblur="validatedate(this.value)" cssClass="NGAY_SL"
                                                           placeholder="DD/MM/YYYY" changeYear="true"  changeMonth="true" displayFormat="dd/mm/yy" 
                                                           cssStyle="font-weight: bold;vertical-align: middle;"/> -->
                                        </s:if>
                                    </td>

                                </s:iterator>     
                                    <td >
                                    &nbsp;&nbsp;&nbsp;
                                    <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                               onBeforeTopics="beforediv_data"
                                               onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                    <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                                    &nbsp;&nbsp;&nbsp;
                                     <s:if test="!Grade.equalsIgnoreCase('3')">
                                           <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>                                           
                                        </s:if>  
                                    
                                    &nbsp;&nbsp;&nbsp;
                                        <s:if test="Grade.equalsIgnoreCase('2')">                                       
                                            <s:url id="idSendData" action="sendPhiUT.action"></s:url>                                      
                                            <sj:submit id="idSend" name="nameSend" href="%{idSendData}" value="Gửi dữ liệu" targets="divExportReport"
                                                       onBeforeTopics="beforediv_send"
                                                       onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                            <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu"/>
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
                        </table>
                    </div>                                  
                </div>
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
                <div id="containParm_full"  align="center">                    
                        <div id="divExportReport1"></div>
                    </div>
                    <div id="containParm_full_2" align="center">                    
                        <div id="divExportReport"></div>
                    </div>                      
            </s:form>
         </div>
        <s:form id="exp_sbv">
            <input type="hidden" value="123456" id="tt_nha_dt" name="tt_nha_dt"/>
        </s:form>
            <sj:a id="loadPosSp" formIds="exp_sbv" targets="divExportReport1" 
                  indicator="loadingImage" href="%{reloadbc}" 
                  onBeforeTopics="beforediv_send"
                  onCompleteTopics="completediv_send"
                  ></sj:a>
    </body>
</html>