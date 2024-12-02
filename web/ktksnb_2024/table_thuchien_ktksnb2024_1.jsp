<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thực hiện kế hoạch</title>
        <sx:head/>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>

        <style type="text/css">
            *{
                font: 13px Arial, Helvetica, sans-serif;
            }
            table{
                border-style: solid;
                border-collapse: collapse;
                width: 100%;
                line-height: 19px;
            }
            .tbhead th{
                background-color: #5e5e55;
                font-weight: bold;
                color: #fff;
                text-align: center;
                padding: 5px;
            }
            .cscontent td{
                padding-left:5px;
            }
            .cscontent:hover{
                background-color: #ffff99;
            }

            .cscontent:hover input[type="text"]{
                background-color: #ffff99;
            }
            .tblmain tr td{
                font-weight: bold;
                color: #018c3b;
                word-wrap: break-word;
            }

            input{
                border: 0px;
            }

            .BOLD input[type="text"]
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }

            .ITALIC input[type="text"]
            {
                font-style: italic;
                font-size: 12px;
                width: 95%;
            }

            input[type="text"]
            {
                width: 95%;
            }

            input[type="button"]
            {
                border: 2px solid black;
                border-radius: 5px;
                margin-left: 3px;
            }

            .parameter{
                border: 1px solid black;
                width: 50%;
            }

            #posCD, #quyBc, #namBc, #maCn, #userId{
                width: 70px;
            }
            input[readonly] {
                background-color: #cccccc;
                color: #666;
                cursor: not-allowed;
            }
            #subTable {
                font-size: 16px;
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                border-spacing: 0;
                width: 98%;
            }
            #subTable th{
                background-color: #ddd;
                color: #0000FF;
            }

            #subTable th, #subTable td {
                border: 1px solid gray;
            }

            #subTable tr:nth-child(even){background-color: #f2f2f2;}

            #subTable tr:hover {background-color: #ddd;}

            .txtPublic{
                width: 85px;
            }
            .ui-datepicker-trigger{
                height: 100%;
            }
            .txtBody{
                text-align: center;
            }
            .txtBody > .ui-datepicker-trigger{
                display: none;
            }
            td.hdtitle {
                position: static;
                top: 0;
                z-index: 10;
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
            var max_row = 0;
            $(document).ready(function () {
//                initTable();
            });
            $(document).ready(function () {
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('input.number2').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".STT1").css({"width": "30px"});
                $(".STT2").css({"width": "90px"});
                $(".STT3").css({"width": "150"});
                $(".STT4").css({"width": "70%"});
                $(".STT5").css({"width": "70px"});
                $(".STT6").css({"width": "80px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "220px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });

            $(document).ready(function () {
                $("#update").click(function () {
                    document.getElementById("update").disabled = true;
                    sleep(1000);
                    document.getElementById("update").disabled = false;
                });

                $('.hideColumn').hide();

                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);

                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
            });

            //Xu ly tinh tong cho tung dong


            function doClick(id, e)
            {
//                alert('vao doClick');

                var key;

                if (window.event)
                    key = window.event.keyCode;     //IE
                else
                    key = e.which;     //firefox

                if (key == 13)
                {
                    //Get the button the user wants to have clicked
                    e.preventDefault();
                    e.stopPropagation();
                }
            }
            function fnResetVal() {

            }
            //Check xem du lieu da ok chua
            //Neu ok roi thi goi su kien submit du lieu
            function fnCheckThenSubmit() {
                document.getElementById('loadingImageDiv_para').style.display = "block";
                $("#update").click(function () {
                });
                if (validateRequiredFields()) {
                    $("#update").trigger('click');
                }
            }


            function tai_lai_trang() {
                location.reload();
            }

            function sleep(milliSeconds) {
                var startTime = new Date().getTime(); // get the current time
                while (new Date().getTime() < startTime + milliSeconds)
                    ; // hog cpu
            }


            document.addEventListener('DOMContentLoaded', function () {
                document.getElementById('subTable').addEventListener('keydown', function (event) {
                    if (event.key === 'Enter') {
                        event.preventDefault();
//                        alert('Phím Enter đã bị khóa!');
                    }
                });
            });
        </script>

    </head>
    <body style="background-image: url('img/backgroud_logo.jpg');background-size: cover;">
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdata" id="frmdata" >
                <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain" style="background-image: url('img/baner11.jpg');background-size: cover;">              
                    <tr>
                        <td width="70%" style="color: black; font-family: Comic Sans MS; text-shadow: 1px 1px 0 white, -1px -1px 0 white, 1px -1px 0 white, -1px 1px 0 white;">
                            <s:property value="title3" />
                        </td>
                        <td align="right">     
                            <div id="result" style="color: red">                            
                            </div>
                            <div id="loadingImageDiv_para"  style="display: none;">
                                <img id="loadingImage" src='img/loading.gif' border='0' >
                            </div>
                            &nbsp;<input type="button" id="idSave" value="Lưu dữ liệu"/> 
                            &nbsp;<input type="button" id="cmdEnd" value="Thoát"/> 
                            <sj:submit id="update" name="update"  targets="result" onBeforeTopics="beforediv_para"
                                       onCompleteTopics="completediv_para" cssStyle="display: none"/>
                        </td>       
                    </tr>
                </table>
                <hr>

                <table   border="1" style="width: 98%" align="center">
                    <tr>
                    <div id="divTitle" style="text-align: center; font: 16px Arial, Helvetica, sans-serif; font-weight: bold; color: #0000FF">
                        THỰC HIỆN KẾ HOẠCH<br>
                        <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Chi nhánh đã chốt số liệu)</a></s:if>
                        <s:elseif test="chotsl.equalsIgnoreCase('1')" ><a class="color_11">(Phòng giao dịch đã gửi dữ liệu)</a></s:elseif>
                            <div style="height:10px"></div>
                            <div style="color: red; background: yellow; text-align: left; font-weight: bold; width: 98%; font-size: 14px; 
                                 display: flex; margin: auto;"><s:property value="title1" />
                        </div>
                        <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
                        <input type="hidden" id="stoday" value="<s:property  value="sngay_sys" />" name="stoday"/> 
                        <input type="hidden" id="sthang" value="<s:property  value="ssthang" />" name="ssthang"/> 
                        <input type="hidden" id="snam" value="<s:property  value="title4" />" name="title4"/> 
                    </div>
                    </tr>
                    <table  id="subTable" border="1" style="width: 98%" align="center">
                        <tr> 
                            <th class="STT1" >STT</th>                           
                            <th class="STT4" >Nội dung</th>  
                            <th class="STT2" >Đơn vị</th> 
                            <th class="STT6" >Kế hoạch gốc</th>
                            <th class="STT6" >Kế hoạch điều chỉnh</th>
                            <th class="STT6" >Thực hiện kế hoạch</th>
                        </tr>
                        <tr>
                            <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                            <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                            <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                            <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                            <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                            <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>

                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                            <tr id="tablefix"> 
                            <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                            <input type="hidden" value="<s:property value="TT_HIENTHI" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                            <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
                            <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                            <input type="hidden" value="<s:property  value="CO_TONGHOP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/>
                            <input type="hidden" value="<s:property  value="NGUOI_NHAP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_NHAP"/>
                            <input type="hidden" value="<s:property  value="NAMBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NAMBC"/>
                            <input type="hidden" value="<s:property  value="MAPGD" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                            <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>
                            <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>
                            <input type="hidden" value="<s:property  value="D3" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                            <input type="hidden" value="<s:property  value="D4" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"/>
                            <input type="hidden" value="<s:property  value="D5" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"/>
                            <input type="hidden" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7"/>
                            <input type="hidden" value="<s:property  value="D9" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/>
                            <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                            <input type="hidden" value="<s:property  value="KIEUIN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KIEUIN"/>
                            <input type="hidden" value="<s:property  value="NHAPTAY" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/>
                            <s:if test="scapbc.equalsIgnoreCase('1')">
                                <td class="D0" <s:if test="THUTU.toString().equalsIgnoreCase('1')
                                      || THUTU.toString().equalsIgnoreCase('8')"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="TT_HIENTHI" /></td>
                                <td <s:if test="THUTU.toString().equalsIgnoreCase('1')
                                      || THUTU.toString().equalsIgnoreCase('8')"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="TEN" /></td>
                                <td class="D0" <s:if test="THUTU.toString().equalsIgnoreCase('1')
                                      || THUTU.toString().equalsIgnoreCase('8')"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="D1" /></td>
                                    <td style="background:  #E5E5E5">
                                        <input type="text" value="<s:property  value="D2" />"
                                           id="D2_<s:property  value='%{#rowstatus.index}' />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="number" readonly/>
                                </td>
                                <td style="background:  #E5E5E5">
                                    <input type="text" value="<s:property  value="D6" />"
                                           id="D6_<s:property  value='%{#rowstatus.index}' />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number" readonly/>
                                </td> 
                                <td style="background:  #E5E5E5">
                                    <input type="text" 
                                           id="D8_<s:property  value='%{#rowstatus.index}' />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="number"
                                           <s:if test="THUTU.toString().equalsIgnoreCase('1')
                                                 || THUTU.toString().equalsIgnoreCase('8')"> style="font-weight: bold" readonly</s:if>
                                                 <s:else> value="<s:property value='%{D8 != null ? D8 : D6}' />"</s:else>
                                                 />
                                    </td>
                            </s:if> 
                            <s:else>
                                <td class="D0" <s:if test="(THUTU.toString().equalsIgnoreCase('1')
                                      || THUTU.toString().equalsIgnoreCase('2') || THUTU.toString().equalsIgnoreCase('10')
                                      || THUTU.toString().equalsIgnoreCase('17'))"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="TT_HIENTHI" /></td>
                                <td <s:if test="(THUTU.toString().equalsIgnoreCase('1')
                                      || THUTU.toString().equalsIgnoreCase('2') || THUTU.toString().equalsIgnoreCase('10')
                                      || THUTU.toString().equalsIgnoreCase('17'))"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="TEN" /></td>
                                <td class="D0" <s:if test="(THUTU.toString().equalsIgnoreCase('1')
                                      || THUTU.toString().equalsIgnoreCase('2') || THUTU.toString().equalsIgnoreCase('10')
                                      || THUTU.toString().equalsIgnoreCase('17'))"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="D1" /></td>
                                <td <s:if test="(THUTU.toString().equalsIgnoreCase('1')
                                      || THUTU.toString().equalsIgnoreCase('2') || THUTU.toString().equalsIgnoreCase('10')
                                      || THUTU.toString().equalsIgnoreCase('17'))"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property  value="D2" /></td> 
                                <td <s:if test="(THUTU.toString().equalsIgnoreCase('1')
                                      || THUTU.toString().equalsIgnoreCase('2') || THUTU.toString().equalsIgnoreCase('10')
                                      || THUTU.toString().equalsIgnoreCase('17'))"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property  value="D6" /></td>     
                                    <td style="background:  #E5E5E5">
                                        <input type="text" id="D8_<s:property value='%{#rowstatus.index}' />"
                                           name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D8" class="number"
                                           <s:if test="(THUTU.toString().equalsIgnoreCase('1')
                                                 || THUTU.toString().equalsIgnoreCase('2') || THUTU.toString().equalsIgnoreCase('10')
                                                 || THUTU.toString().equalsIgnoreCase('17'))"> 
                                               style="font-weight: bold" readonly
                                           </s:if>
                                           <s:else> value="<s:property value='%{D8 != null ? D8 : D6}' />"</s:else>
                                               />
                                    </td>                              
                            </s:else>
                            </tr>
                        </s:iterator>
                    </table>
                </s:form>
        </div>
        <script>
            $("#idSave").click(function () {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();

                let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                if (aCheck) {
                    var table = document.getElementById("subTable");
                    var rowcount = table.rows.length;
                    var isValid = true;
                    var chot = document.getElementById("chotsl").value;
                    var sthangbc = document.getElementById("sthang").value.padStart(2, '0');
                    var snambc = document.getElementById("snam").value;
                    var stoday = document.getElementById("stoday").value;
                    var sparts = stoday.split('/');
                    var currentYear = sparts[2];
                    var currentMonth = sparts[1];
                    var isValid = true;
                    var chot = document.getElementById("chotsl").value;
//                    if (snambc.toString() < currentYear.toString()) {
//                        alert("Cảnh báo: Chức năng chỉ lưu tại năm hiện tại " + currentYear);
//                        isValid = false;
//                    }
//                    if (snambc.toString() === currentYear.toString() && sthangbc.toString() !== currentMonth.toString()) {
//                        alert("Cảnh báo: Chỉ được phép chỉnh sửa dữ liệu tháng hiện tại là tháng " + currentMonth);
//                        isValid = false;
//                    }
//                    if (chot === "2") {
//                        alert("Chi nhánh đã chốt dữ liệu lên Tw!");
//                        isValid = false;
//                    }
                    if (chot === "1") {
                        alert("Dữ liệu đã gửi, không thể lưu!");
                        isValid = false;
                    }
                    if (isValid) {
                        var url, sdata;
                        url = "save_KTKSNB_03_2024.action";
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
                                    idEnd();
                                } else {
                                    alert("Lỗi: Lưu dữ liệu.");
                                    tai_lai_trang();
                                }
                            },
                            complete: function () {
                                btnDisabled(0);
                            },
                            error: function (request) {
                                alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                                tai_lai_trang();
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

            $("#cmdEnd").click(function () {
                window.opener.document.getElementById('loadDatatmp').click();
                window.close();
            });

            function idEnd() {
                window.opener.document.getElementById('loadDatatmp').click();
                window.close();
            }
            window.onbeforeunload = function () {
                window.opener.document.getElementById('loadDatatmp').click();
            };
        </script>
    </body>
</html>
