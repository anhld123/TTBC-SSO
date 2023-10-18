<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 03</title>
        <sx:head/>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>

        <style type="text/css">
            *{
                font: 12px Arial, Helvetica, sans-serif;
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
                margin-left: 3px;
            }

            .parameter{
                border: 1px solid black;
                width: 50%;
            }

            #posCD, #quyBc, #namBc, #maCn, #userId{
                width: 70px;
            }
        </style>

        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".SOKU").css({"width": "97%"});
                $(".SOKU2").css({"width": "98%"});
                $(".TD_CHECKBOX").css({"width": "39px"});
                $(".TD_SOKU").css({"width": "80px"});
                $(".TD_TENKH123").css({"width": "110px"});
                $(".TD_TENKH1234").css({"width": "70px"});
                $(".TD_TENTS").css({"width": "190px"});
                $(".TD_SOTK").css({"width": "105px"});
                $(".TD_MAKH").css({"width": "60px"});
                $(".TD_THOIGIAN").css({"width": "auto"});
                $(".TD_MAPGD").css({"width": "45px"});
                $(".TD_BUTTON1").css({"width": "40px"});
                $(".TD_SOTIEN").css({"width": "100px"});
                $(".TEN_KH").css({"width": "50%"});
                $(".SOKU1").css({"width": "90%"});
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


                //CSS truong so thu tu (cot 1) cho width
//                $('#tableKtnb td:nth-child(1),#tableKtnb th:nth-child(1)').css({"width" : "35px"});

                //CSS truong so thu tu (cot 1) cho width
//                $('#tableKtnb td:nth-child(2),#tableKtnb th:nth-child(2)').css({"width" : "250px"});
                $(".KT_STT_HT").css({"width": "35px"});
                $(".KT_DT").css({"width": "240px"});

                //An di cac cot chuc nang
                $('.hideColumn').hide();

                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);

                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
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
//                window.location.reload();
            }

            function tai_lai_trang() {
                location.reload();
            }

            function sleep(milliSeconds) {
                var startTime = new Date().getTime(); // get the current time
                while (new Date().getTime() < startTime + milliSeconds)
                    ; // hog cpu
            }


            function validateRequiredFields() {
                var result = true; //Luu ket qua kiem tra kieu so co dung khong             
//                var table = document.getElementById("tableKtnb");
//                var rowCount = table.rows.length - 5;
//                max_row = rowCount;
//                $(".number2").each(function (index) {
//                    //Kiem tra xem co nhap kieu so khong
//                    if (isNaN(parseFloat($(this).val())) || parseFloat($(this).val()) == 0) {
//                        result = false;
//                        alert('Thứ tự lớn hơn 0 và không được để trống')
//                        return false;
//                    }
//                });

                if (result == false) {
                    //Neu nguoi dung khong nhap dung kieu du lieu
                    //Dua ra canh bao
                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                }

                return result;
            }
            function deleteRow(indx) {
                var table = document.getElementById("tableKtnb");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
            }
            function addRow(indx) {
//                sleep(1000);
                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("tableKtnb");
                var rowCount = table.rows.length - 3; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
                else
                {
                    max_row++;
                    rowCount = max_row;
                }
                var newTr = '<tr>\n\
                                <td ><input type="text" value="' + max_row + '" id="TT_HIENTHI" name="lstDulieuNt[' + rowCount + '].TT_HIENTHI" class="SOKU1 D0 number2" onfocus="this.select();" /></td>\n\
                                <td ><input type="text" value="" placeholder="Không phát sinh" id="D1" name="lstDulieuNt[' + rowCount + '].D1"  class="SOKU" onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="" placeholder = "Không phát sinh" id="D2" name="lstDulieuNt[' + rowCount + '].D2" class="SOKU" onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="" placeholder = "Không phát sinh" id="D3" name="lstDulieuNt[' + rowCount + '].D3" class="SOKU" onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="" placeholder = "Không phát sinh" id="D4" name="lstDulieuNt[' + rowCount + '].D4" class="SOKU" onfocus="this.select();"/></td>\n\
\n\                             <td ><input type="text" value="" placeholder = "Không phát sinh" id="D5" name="lstDulieuNt[' + rowCount + '].D5" class="SOKU" onfocus="this.select();"/></td>\n\
\n\                             <td ><input type="text" value="" placeholder = "Không phát sinh" id="D6" name="lstDulieuNt[' + rowCount + '].D6" class="SOKU" onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="" placeholder = "Không phát sinh" id="D10" name="lstDulieuNt[' + rowCount + '].D10" class="SOKU" onfocus="this.select();"/></td>\n\
                                <td><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="D0 SOKU"/></td>\n\
                                </tr>';
                $($('table#tableKtnb tr')[index]).before(newTr);
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".SOKU").css({"width": "97%"});
                $('.number').number(true, 0);
                $(".SOKU1").css({"width": "90%"});
                $('.number2').number(true, 0);
            }
            $.subscribe("beforediv_para", function (event, data) {
                $("#loadingImageDiv_para").show();
            });
            $.subscribe("completediv_para", function (event, data) {
                $("#loadingImageDiv_para").hide();
            });

            function onReloadPara()
            {
                $('#result').empty();
                $("#update")[0].click();
            }
            $.subscribe('beforediv1', function (event, data) {
                var allDate = $(".hasDatepicker").map(function () {
                    return $(this).attr("name");
                }).get();

                //2. Them input mask
                for (var i = 0; i < allDate.length; i++) {
                    new DateMask("dd/MM/yyyy", allDate[i].toString());
                }
            });
        </script>

    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdataKtnb06A" id="frmdataKtnb06A" action="save_data_ktnb_bieu03.action" theme="simple">
                <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain" >
                    <tr>
                        <td colspan="3" style="font-size: 14px;">Biểu số 03: Kết quả phát hiện, khắc phục các văn bản còn sơ hở, dễ bị lợi dụng để tham nhũng <font color="red"> (TT duyệt: <s:property value="statusAuthor"/>)</font><hr></td>                    
                    </tr>
                    <tr>
                        <td width="70%" >
                            <b>Phòng giao dịch: </b><input class="TD_TENKH1234" type="text" name="posCD" id="posCD" value="<s:property value="pos_cd_username"/>" readonly="readonly"/>
                            <b>Chi nhánh: </b><input class="TD_TENKH1234" type="text" name="maCn" id="maCn" value="<s:property value="main_pos_username"/>" readonly="readonly"/>
                            <!--<b>Quý báo cáo: </b><input class="TD_TENKH1234" type="text" name="quyBc" id="quyBc" value="<s:property value="quyBc"/>" readonly="readonly"/>-->
                            <b>Tháng báo cáo: </b><input class="TD_TENKH1234" type="text" name="month" id="month" value="<s:property value="month"/>" readonly="readonly"/>
                            <b>Người dùng: </b><input class="TD_TENKH1234" type="text" name="userName" id="userName" value="<s:property value="userName"/>" readonly="readonly"/>
                            <b>Ngày báo cáo: </b><input type="text" class="TD_TENKH123" name="ngayBC" id="ngayBC" value="<s:property value="ngayBC"/>" readonly="readonly"/>
                            <b>Ngày thực hiện: </b><input type="text" class="TD_TENKH123" name="ngayTT" id="ngayTT" value="<s:property value="ngayTT"/>" readonly="readonly"/>
                    
                        </td>
                        <td align="right">     
                            <div id="result" style="color: red">                            
                            </div>
                            <div id="loadingImageDiv_para"  style="display: none;">
                                <img id="loadingImage" src='img/loading.gif' border='0' >
                            </div>
                            <input type="button" id="checkThenSubmit" value="Lưu dữ liệu" onclick="this.disabled = true; fnCheckThenSubmit()"
                                   style="width:122px;height:25px;color: blue; font-weight: bold ;"/>
                            <sj:submit id="update" name="update"  targets="result" onBeforeTopics="beforediv_para"
                                       onCompleteTopics="completediv_para" cssStyle="display: none"/>
                        </td>      
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr>
                            <table border="1px" id="tableKtnb">
                                <tr class="tbhead">
                                    <th rowspan="2"class="TD_BUTTON1">STT</th>
                                    <th rowspan="2">Tên, số, ngày, tháng, năm</th>
                                    <th rowspan="2">Cơ quan ban hành văn bản</th>
                                    <th rowspan="2">Nội dung sơ hở, dễ bị lợi dụng để tham nhũng</th>
                                    <th colspan="3">Kết quả khắc phục</th>
                                    <th rowspan="2">Ghi chú</th>
                                    <th rowspan="2">Trạng thái</th>

                                </tr>
                                <tr class="tbhead">
                                    <th>Đã được khắc phục theo thẩm quyền</th>
                                    <th>Chưa khắc phục xong</th>
                                    <th>Nguyên nhân của việc chưa khắc phục xong</th>
                                </tr>
                                <tr class="tbhead">
                                    <th>(1)</th>
                                    <th>(2)</th>
                                    <th>(3)</th>
                                    <th>(4)</th>
                                    <th>(5)</th>
                                    <th>(6)</th>
                                    <th>(7)</th>
                                    <th>(8)</th>
                                    <th>(9)</th>

                                </tr>

                                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                                    <tr height="cscontent"> 
                                        <td>
                                            <input type="text" value="<s:property  value="TT_HIENTHI" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="SOKU1 D0 number2" onfocus="this.select()"
                                                   onblur="if (this.value == '')
                                                               ;"/>
                                            <input type="hidden" value="<s:property  value="THUTU" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/> 
                                            <input type="hidden" value="<s:property  value="MA" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                                            <input type="hidden" value="<s:property  value="FONTFORMAT" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].FONTFORMAT" value="<s:property  value="FONTFORMAT"/>"/> 
                                            <input type="hidden" value="<s:property  value="CO_TONGHOP" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP" value="<s:property  value="CO_TONGHOP"/>"/> 
                                              <input type="hidden" value="<s:property  value="TEN" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" value="<s:property  value="TEN"/>"/> 
                                              <input type="hidden" value="<s:property  value="D50" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D50" value="<s:property  value="D50"/>"/>

                                        </td>
                                        <td>                                 
                                           <input type="text" value="<s:property  value="D1" />"  placeholder = "Không phát sinh"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="SOKU" onfocus="this.select()"
                                                  onfocus="this.select()" onblur="if(this.value == '') { this.value= 'Không phát sinh'};"/>
                                        </td>                          
                                        <td>
                                            <input type="text" value="<s:property  value="D2" />"  placeholder = "Không phát sinh"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="SOKU" onfocus="this.select()"
                                                   onfocus="this.select()" onblur="if(this.value == '') { this.value= 'Không phát sinh'};"/>
                                        </td>                                  
                                        <td>
                                            <input type="text" value="<s:property  value="D3" />"  placeholder = "Không phát sinh"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="SOKU" onfocus="this.select()"
                                                   onfocus="this.select()" onblur="if(this.value == '') { this.value= 'Không phát sinh'};"/>
                                        </td>                                                                                                

                                        <td>
                                            <input type="text" value="<s:property  value="D4" />"  placeholder = "Không phát sinh"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="SOKU" onfocus="this.select()"
                                                   onfocus="this.select()" onblur="if(this.value == '') { this.value= 'Không phát sinh'};"/>
                                        </td>
                                        <td>
                                            <input type="text" value="<s:property  value="D5" />"  placeholder = "Không phát sinh"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="SOKU" onfocus="this.select()"
                                                   onfocus="this.select()" onblur="if(this.value == '') { this.value= 'Không phát sinh'};"/>
                                        </td>
                                        <td>
                                            <input type="text" value="<s:property  value="D6" />"  placeholder = "Không phát sinh"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="SOKU" onfocus="this.select()"
                                                   onfocus="this.select()" onblur="if(this.value == '') { this.value= 'Không phát sinh'};"/>
                                        </td>
                                        <td>
                                            <input type="text" value="<s:property  value="D10" />"  placeholder = "Không phát sinh"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                                                   onfocus="this.select()" onblur="if(this.value == '') { this.value= ''};"/>
                                        </td>  
                                        <s:if test="THUTU.toString().equalsIgnoreCase('1')"> 
                                            <td style="background: #E7DCDA !important;"></td>
                                        </s:if>
                                        <s:if test="!THUTU.toString().equalsIgnoreCase('1')"> 
                                            <td><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="SOKU"/></td>

                                        </s:if>                               
                                    </tr>

                                </s:iterator>
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="D0 SOKU"/></td>
                                </tr>    
                            </table>
                    </tr>
                </table>

            </s:form>
        </div>
    </body>
</html>
