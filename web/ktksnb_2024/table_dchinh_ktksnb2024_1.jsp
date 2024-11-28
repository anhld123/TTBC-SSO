<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Điều chỉnh kế hoạch</title>
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
                initTable();
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
                    <tr >
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
                <tr>
                <div id="divTitle" style="text-align: center; font: 16px Arial, Helvetica, sans-serif; font-weight: bold; color: #0000FF">
                    ĐIỀU CHỈNH KẾ HOẠCH<br>
                    <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Chi nhánh đã chốt số liệu)</a></s:if>
                    <s:elseif test="chotsl.equalsIgnoreCase('1')" ><a class="color_11">(Phòng giao dịch đã gửi dữ liệu)</a></s:elseif>
                        <div style="height:10px"></div>
                        <div style="color: red; background: yellow; text-align: left; font-weight: bold; width: 99%; font-size: 13px; 
                             display: flex; margin: auto;">
                        <s:property value="title1" /> 
                        <s:if test="check_cn.equalsIgnoreCase('0')">
                            <s:iterator value="#attr.lstDulieuNt" status="rowStatus">
                                <s:if test="#rowStatus.first">
                                    &raquo;&raquo;&nbsp; <a id="deletePlanLink" style="text-decoration: underline" href="#" 
                                                            onclick="cancelAssign('<s:property value="chotsl"/>', '<s:property value="MAPGD"/>', '<s:property value="D3"/>', '<s:property value="D4"/>', '<s:property value="D5"/>', '<s:property value="NGAYBC"/>', '<s:property value="KHOA"/>', '<s:property value="D5"/>', '1');">
                                        Xóa điều chỉnh tháng <s:property value="D5"/>
                                    </a></s:if></s:iterator>
                                    &nbsp;-/- Đổi tháng k.tra &nbsp;
                                    <select id="select1" name="select1" onchange="updateMonthText()">
                                        <option value="0">--Chọn--</option>
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

                            <s:iterator value="#attr.lstDulieuNt" status="rowStatus">
                                <s:if test="#rowStatus.first">

                                    &nbsp; &raquo;&raquo;&nbsp; 
                                    <a id="selectLink" style="text-decoration: underline" href="#" 
                                       onclick="cancelAssign('<s:property value="chotsl"/>', '<s:property value="MAPGD"/>', '<s:property value="D3"/>', '<s:property value="D4"/>', document.getElementById('select1').value, '<s:property value="NGAYBC"/>', '<s:property value="KHOA"/>', '<s:property value="D5"/>', '2');">

                                    </a>
                                </s:if>

                            </div>
                            <s:if test="!D4.equalsIgnoreCase('99999') && #rowStatus.first">
                                <div style="color: red; background: yellow; text-align: left; font-weight: bold; width: 99%; font-size: 13px; 
                                     display: flex; margin: auto;">
                                    Đổi cán bộ nếu thay đổi về nhân sự &nbsp;
                                    <select id="cboCanBo" name="cboCanBo" onchange="updateCb()">
                                        <option value="000000">----Chọn cán bộ----</option>
                                        <s:iterator value="lstCanBo">
                                            <option value='<s:property value="MaCB"/>'><s:property value="TenCB"/></option>
                                        </s:iterator>
                                    </select>
                                    <s:if test="#rowStatus.first">
                                        &nbsp;&raquo;&raquo;&nbsp;
                                        <a id="selectLink1" style="text-decoration: underline" href="#" 
                                           onclick="cancelAssign('<s:property value="chotsl"/>', '<s:property value="MAPGD"/>', '<s:property value="D3"/>', '<s:property value="D4"/>', '<s:property value="D5"/>', '<s:property value="NGAYBC"/>', '<s:property value="KHOA"/>-<s:property value="D5"/>', document.getElementById('cboCanBo').value, '3');">
                                        </a>
                                    </s:if>
                                </s:if>
                            </s:iterator>
                        </s:if>
                    </div>
                    <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
                    <input type="hidden" id="stoday" value="<s:property  value="sngay_sys" />" name="stoday"/> 
                    <input type="hidden" id="sthang" value="<s:property  value="ssthang" />" name="ssthang"/> 
                    <input type="hidden" id="snam" value="<s:property  value="title4" />" name="title4"/> 

                    <div style="height:5px">
                    </div></div>
            </tr>
            <table  id="subTable" border="1" style="width: 98%;" align="center">
                <tr> 
                    <th class="STT1" >STT</th>                           
                    <th class="STT4" >Nội dung</th>  
                    <th class="STT2" >Đơn vị</th>  
                    <th class="STT6" >Kế hoạch kiểm tra</th>
                    <th class="STT6" style="color: red">Kế hoạch điều chỉnh</th>
                </tr>
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr height="cscontent"> 
                    <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>    
                    <input type="hidden" value="<s:property  value="KHOA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KHOA"/>  
                    <input type="hidden" value="<s:property value="TT_HIENTHI" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                    <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
                    <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                    <input type="hidden" value="<s:property  value="CO_TONGHOP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/>
                    <input type="hidden" value="<s:property  value="NGUOI_NHAP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_NHAP"/>
                    <input type="hidden" value="<s:property  value="NAMBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NAMBC"/>
                    <input type="hidden" value="<s:property  value="NGAYBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGAYBC"/>
                    <input type="hidden" value="<s:property  value="MAPGD" />"name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                    <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>
                    <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>
                    <input type="hidden" value="<s:property  value="D3" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                    <input type="hidden" value="<s:property  value="D4" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"/>
                    <input type="hidden" value="<s:property  value="D5" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"/>
                    <input type="hidden" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7"/>
                    <input type="hidden" value="<s:property  value="D8" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"/>
                    <input type="hidden" value="<s:property  value="D9" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/>
                    <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                    <input type="hidden" value="<s:property  value="KIEUIN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KIEUIN" id="KIEUIN_<s:property  value='%{#rowstatus.index}' />"/>
                    <input type="hidden" value="<s:property  value="NHAPTAY" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/>
                    <s:if test="scapbc.equalsIgnoreCase('1')">
                        <td class="D0" <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="TT_HIENTHI" /></td>
                        <td <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="TEN" /></td>
                        <td class="D0" <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="D1" /></td>
                            <td style="background:  #E5E5E5">
                                <input type="text" value="<s:property  value="D2" />"
                                   id="D2_<s:property  value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="number" readonly
                                   <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold"</s:if>/>
                            </td>    
                            <td style="background:  #E5E5E5">
                                <input type="text" value="<s:property  value="D6" />"
                                   id="D6_<s:property  value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number"
                                   <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold" readonly</s:if>/>
                            </td>
                    </s:if>
                    <s:else>
                        <td class="D0" <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="TT_HIENTHI" /></td>
                        <td <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="TEN" /></td>
                        <td class="D0" <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="D1" /></td>
                            <td style="background:  #E5E5E5">
                                <input type="text" value="<s:property  value="D2" />"
                                   id="D2_<s:property  value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="number" readonly
                                   <s:if test="(THUTU.toString().equalsIgnoreCase('1')
                                         || THUTU.toString().equalsIgnoreCase('2') || THUTU.toString().equalsIgnoreCase('10')
                                         || THUTU.toString().equalsIgnoreCase('17'))"> style="font-weight: bold" </s:if>/>
                            </td>
                            <td style="background:  #E5E5E5">
                                <input type="text"
                                       id="D6_<s:property value='%{#rowstatus.index}' />"
                                name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D6" class="number"
                                <s:if test="(THUTU.toString().equalsIgnoreCase('1')
                                      || THUTU.toString().equalsIgnoreCase('2') || THUTU.toString().equalsIgnoreCase('10')
                                      || THUTU.toString().equalsIgnoreCase('17'))"> 
                                    style="font-weight: bold" readonly
                                </s:if>
                                <s:else> value="<s:property value="D6 != null ? D6 : 0" />"</s:else>
                                    />
                            </td>
                    </s:else>
                    </tr>
                </s:iterator>

            </table>

        </s:form>
    </div>
    <script>
        function initTable()
        {
            var table = document.getElementById("subTable");
            var rowcount = table.rows.length;
            rowcount = rowcount > max_row ? rowcount : max_row;
            for (var i = 0; i < rowcount; i++)
            {
                try {
                    var D6 = document.getElementById("D6_" + i).value;
                    var KIEUIN = document.getElementById("KIEUIN_" + i).value;
                    if (D6 === "" && KIEUIN === "3")
                    {
                        document.getElementById("D6_" + i).value = 0;
                    }
                } catch (e) {
                }
            }

        }
        function updateMonthText() {
            var monthSelect = document.getElementById("select1").value;
            var selectLink = document.getElementById("selectLink");

            if (monthSelect.value !== "0") {
                selectLink.innerText = "Chuyển sang tháng " + monthSelect;
            } else {
                selectLink.innerText = "Chuyển sang tháng ";
            }
        }

        function updateCb() {
            var cboCanBo = document.getElementById("cboCanBo");
            var selectedValue = cboCanBo.value; // Lấy giá trị MaCB
            var selectedText = cboCanBo.options[cboCanBo.selectedIndex].text; // Lấy tên TenCB
            var selectLink = document.getElementById("selectLink1");

            if (selectedValue !== "000000") {
                selectLink.innerText = "Chuyển sang " + selectedText;
            } else {
                selectLink.innerText = "Chuyển sang ";
            }
        }


        function cancelAssign(chotsl, mapgd, D3, D4, monthSelect, nambc, skhoa, D5, type) {
            var url, sdata;
//                alert(monthSelect + " " + skhoa + " " + D5 + " " + type);
            var sthangbc = document.getElementById("sthang").value.padStart(2, '0');
            var snambc = document.getElementById("snam").value;
            var stoday = document.getElementById("stoday").value;
            var sparts = stoday.split('/');
            var currentYear = sparts[2];
            var currentMonth = sparts[1];
//            if (snambc.toString() !== currentYear.toString()) {
//                alert("Cảnh báo: Chức năng chỉ lưu tại năm hiện tại " + currentYear);
//                return;
//            }
//            if (snambc.toString() === currentYear.toString() && sthangbc.toString() !== currentMonth.toString()) {
//                alert("Cảnh báo: Chỉ được phép chỉnh sửa dữ liệu tháng hiện tại là tháng " + currentMonth);
//                return;
//            }
//            
            url = "status_KTKSNB_02_C1.action?" + "chotsl" + chotsl + "&madiemgd=" + mapgd + "&smaxa=" + D3 + "&sCanbo=" + D4 + "&sThang=" + monthSelect + "&sNam=" + nambc + "&skhoa=" + skhoa + "&ssThang=" + D5 + "&type=" + type,
                    sdata = jQuery("#frmdata").serialize();
            $("#viewData").html('<img src="img/loading.gif"/>');
            $.ajax({
                type: "POST",
                url: url,
                data: sdata,
                success: function (data) {
                    if (data === "200") {
                        if (type === "2") {
                            alert("Điều chỉnh tháng kiểm tra từ tháng " + D5 + " sang tháng " + monthSelect + " thành công!");
                        } else if (type === "3") {
                            alert("Điều chỉnh sang cán bộ " + cboCanBo.options[cboCanBo.selectedIndex].text + " thành công!");
                        } else {
                            alert("Xóa điều chỉnh tháng " + D5 + " thành công!");
                        }
                        idEnd();
                    } else if (data === "1") {
                        alert("Lỗi: Xã " + D3 + " - Tháng " + monthSelect + " đã có kế hoạch kiểm tra, không thể điều chỉnh tiếp!");
                        tai_lai_trang();
                    } else if (data === "100") {
                        alert("Lỗi: Đơn vị đã gửi dữ liệu không thể thao tác!");
                        tai_lai_trang();
                    } else {
                        alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                        tai_lai_trang();
                    }
                },
                error: function (request) {
                    alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                    tai_lai_trang();
                }
            });
        }
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
//                if (snambc.toString() < currentYear.toString()) {
//                    alert("Cảnh báo: Chức năng chỉ lưu tại năm hiện tại " + currentYear);
//                    isValid = false;
//                }
//                if (snambc.toString() === currentYear.toString() && sthangbc.toString() !== currentMonth.toString()) {
//                    alert("Cảnh báo: Chỉ được phép chỉnh sửa dữ liệu tháng hiện tại là tháng " + currentMonth);
//                    isValid = false;
//                }
                if (chot === "2") {
                    alert("Chi nhánh đã chốt dữ liệu lên Tw!");
                    isValid = false;
                }
                if (chot === "1") {
                    alert("Dữ liệu đã gửi, không thể lưu!");
                    isValid = false;
                }
                if (isValid) {
                    var url, sdata;
                    url = "save_KTKSNB_02_2024.action";
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
            // Thực hiện hành động reset bảng trước khi đóng
            window.opener.document.getElementById('loadDatatmp').click();
        };
    </script>
</body>
</html>
