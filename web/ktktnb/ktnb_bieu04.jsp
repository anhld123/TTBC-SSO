<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 04</title>
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
                background-color: #f2f2f2;
                color: #666;
                cursor: not-allowed;
            }
        </style>

        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D1').css({"color": "#000", "font": "13px Arial, Helvetica, sans-serif"});
                $('.D2').css({"font-weight": "bold"});
                $('.D3').css({"font-style": "italic"});
                $('.D4').css({"background": "#ffff99"});
                $('.D5').css({"background": "#50D4FD"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".SOKU").css({"width": "99%%"});
                $(".SOKU1").css({"width": "50px"});
                $(".SOKU2").css({"width": "98%"});
                $(".TD_CHECKBOX").css({"width": "38px"});
                $(".TD_SOKU").css({"width": "80px"});
                $(".TD_TENKH123").css({"width": "110px"});
                $(".TD_TENKH1234").css({"width": "70px"});
                $(".TD_TENTS").css({"width": "190px"});
                $(".TD_SOTK").css({"width": "105px"});
                $(".TD_MAKH").css({"width": "60px"});
                $(".TD_THOIGIAN").css({"width": "auto"});
                $(".TD_MAPGD").css({"width": "99%"});
                $(".TD_BUTTON1").css({"width": "40px"});
                $(".TD_SOTIEN").css({"width": "100px"});
                $(".TEN_KH").css({"width": "50%"});
                $(".TEN_KH1").css({"width": "30%"});
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
//                location.reload();
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

//                $(".number2").each(function (index) {
//                    //Kiem tra xem co nhap kieu so khong
//                    if (isNaN(parseFloat($(this).val())) || parseFloat($(this).val()) === 0) {
//                        result = false;
//                        alert('Trường nhập bắt buộc khác 0')
//                        return false;
//                    }
//                });

                if (result == false) {
                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                }
                return result;
            }

            function findTotal() {
                var arr = document.getElementsByClassName('amount');
                var tot = 0;
                for (var i = 0; i < arr.length; i++) {
                    if (parseFloat(arr[i].value))
                        tot += parseFloat(arr[i].value);
                }
                document.getElementById('totalordercost').value = tot;
            }
        </script>

    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdataKtnb06A" id="frmdataKtnb06A" action="save_data_ktnb_bieu04.action" theme="simple">
                <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain" >
                    <tr>
                        <td colspan="3" style="font-size: 14px;">Biểu số 04:Thống kê số liệu chủ yếu về công tác phòng, chống tham nhũng của ngân hàng chính sách xã hội <font color="red"> (TT duyệt: <s:property value="statusAuthor"/>)</font><hr>   </td>                    
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
                            <table border="1px" id="tableKtnb" style="width: 90%; margin: auto">
                                <tr class="tbhead">
                                    <th class="TD_BUTTON1">MS</th>
                                    <th style="width: 50%">Nội dung</th>
                                    <th class="TD_TENKH123">Đơn vị tính</th>
                                    <th style="width: 10%">Kết quả</th>
                                    <th class="SOKU">Ghi chú</th>
                                </tr>

                                <tr class="tbhead">
                                    <th>(1)</th>
                                    <th>(2)</th>
                                    <th>(3)</th>
                                    <th>(4)</th>
                                    <th>(5)</th>
                                </tr>

                                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                                    <tr height="cscontent">    
                                    <input type="hidden" value="<s:property  value="TT_HIENTHI" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" value="<s:property  value="TT_HIENTHI"/>"/>
                                    <input type="hidden" value="<s:property  value="THUTU" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/> 
                                    <input type="hidden" value="<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                                    <input type="hidden" value="<s:property  value="FONTFORMAT" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].FONTFORMAT" value="<s:property  value="FONTFORMAT"/>"/> 
                                    <input type="hidden" value="<s:property  value="CO_TONGHOP" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP" value="<s:property  value="CO_TONGHOP"/>"/> 
                                    <input type="hidden" value="<s:property  value="D7" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" value="<s:property  value="D7"/>"/>  
                                    <input type="hidden" value="<s:property  value="D4" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" value="<s:property  value="D4"/>"/>  
                                    <input type="hidden" value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D4"/>"/>  

                                    <input type="hidden" value="<s:property  value="D8" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" value="<s:property  value="D8"/>"/>
                                    <input type="hidden" value="<s:property  value="D9" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D9"/>"/>
                                    <input type="hidden" value="<s:property  value="TEN" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" value="<s:property  value="TEN"/>"/> 
                                    <input type="hidden" value="<s:property  value="D50" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D50" value="<s:property  value="D50"/>"/>
                                    <s:if test="D4.equalsIgnoreCase('1')">
                                        <td class="D0 D1 D2 D4"><s:property  value="TT_HIENTHI" /></td>
                                        <td class="D1 D2 D4"><s:property  value="D1" /></td>
                                        <td class="D0 D1 D2 D4"><s:property  value="D2" /></td>
                                        <td style="height: 30px" class="D4"></td><td style="height: 30px" class="D4"></td>
                                        </s:if>
                                        <s:if test="D4.equalsIgnoreCase('2')">
                                        <td class="D0 D1 D3"><s:property  value="TT_HIENTHI" /></td>
                                        <td class="D1 D3"><s:property  value="D1" /></td>
                                        <td class="D0 D1 D3"><s:property  value="D2" /></td>
                                    </s:if>
                                    <s:if test="D4.equalsIgnoreCase('3')">
                                        <td class="D0 D1 "><s:property  value="TT_HIENTHI" /></td>
                                        <td class="D1"><s:property  value="D1" /></td>
                                        <td class="D0 D1"><s:property  value="D2" /></td>
                                    </s:if>
                                    <s:if test="D4.equalsIgnoreCase('3')||D4.equalsIgnoreCase('2')">
                                        <td>
                                            <input type="text" value="<s:property  value="D3" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"
                                                   class="number" onfocus="this.select()"
                                                   onfocus="this.select()" onblur="if (this.value == '') {
                                                               this.value = 0
                                                           }
                                                           ;" 
                                                   style="height: 30px;width: 95%"
                                                   <s:if test="TT_HIENTHI.equalsIgnoreCase('5') || TT_HIENTHI.equalsIgnoreCase('6')|| TT_HIENTHI.equalsIgnoreCase('7')"> readonly</s:if>
                                                       />

                                            </td>
                                        <s:if test="TT_HIENTHI.equalsIgnoreCase('5') || TT_HIENTHI.equalsIgnoreCase('6')|| TT_HIENTHI.equalsIgnoreCase('7')"> <td></td></s:if>
                                        <s:else>
                                            <td class="D0">
                                                <textarea  placeholder="Nhập tối đa 200 ký tự" id="D10_<s:property  value='%{#rowstatus.index}' />" 
                                                           name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D10" 
                                                           style="width: 98%;height: 98%" maxlength="200"><s:property value='D10'/></textarea>
                                            </td></s:else>      
                                    </s:if>
                        </tr>

                    </s:iterator>

                </table>
            </tr>
        </table>

    </s:form>
</div>
</body>
</html>
