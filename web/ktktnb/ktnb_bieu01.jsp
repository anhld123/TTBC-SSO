<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 01</title>
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
                $(".SOKU").css({"width": "99%%"});
                $(".SOKU1").css({"width": "50px"});
                $(".TD_CHECKBOX").css({"width": "38px"});
                $(".TD_SOKU").css({"width": "80px"});
                $(".TD_TENKH123").css({"width": "150px"});
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
                $("#update").click(function () {
                    sleep(1000);
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

            function validateRequiredFields() {
                var result = true; //Luu ket qua kiem tra kieu so co dung khong

                $(".number2").each(function (index) {
                    //Kiem tra xem co nhap kieu so khong
                    if (isNaN(parseFloat($(this).val())) || parseFloat($(this).val()) === 0) {
                        result = false;
                        alert('Trường nhập bắt buộc khác 0')
                        return false;
                    }
                });

                if (result == false) {
                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                }
                return result;
            }

            function evaluateSum(input) {

                var arrCot = [".D5"]; //Luu cac cot cua du lieu can tinh toan
                for (i = 0; i < arrCot.length; i++) {
                    $(arrCot[i]).eq(51).val(parseInt($(arrCot[i]).eq(52).val()) + parseInt($(arrCot[i]).eq(53).val()));
                }

                $(input).css({"border": "1px"});

            }
        </script>

    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdataKtnb06A" id="frmdataKtnb06A" action="save_data_ktnb_bieu01.action" theme="simple">
                <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain" >
                    <tr>
                        <td colspan="3" style="font-size: 14px;">Biểu số 01: Tổng hợp kết quả về công tác phòng, chống tham nhũng<hr></td>                    
                    </tr>
                    <tr>
                        <td width="70%" >
                            <b>Phòng giao dịch: </b><input class="TD_THOIGIAN" type="text" name="posCD" id="posCD" value="<s:property value="pos_cd_username"/>" readonly="readonly"/>
                            <b>Chi nhánh: </b><input class="TD_THOIGIAN" type="text" name="maCn" id="maCn" value="<s:property value="main_pos_username"/>" readonly="readonly"/>
                            <b>Quý báo cáo: </b><input type="text" name="quyBc" id="quyBc" value="<s:property value="quyBc"/>" readonly="readonly"/>
                            <b>Năm báo cáo: </b><input type="text" name="namBc" id="namBc" value="<s:property value="namBc"/>" readonly="readonly"/>
                            <b>Ngày báo cáo: </b><input type="text" class="TD_THOIGIAN" name="ngayBC" id="ngayBC" value="<s:property value="ngayBC"/>" readonly="readonly"/>
                            
                            <b>Người dùng: </b><input class="TD_THOIGIAN" type="text" name="userName" id="userName" value="<s:property value="userName"/>" readonly="readonly"/>
                        </td>
                        <td align="right">     
                            <div id="result" style="color: red">                            
                            </div>
                            <input type="button" id="checkThenSubmit" value="Lưu dữ liệu" onclick="fnCheckThenSubmit()" style="width:122px;height:25px;color: red;"/>
                            <sj:submit targets="result" value="Cập nhật" name="update" id="update"  cssStyle="display: none;"/>
                            <input type="button" onclick="tai_lai_trang()" style="width:122px;height:25px;color: red;" value ="Reset"/>    
                        </td>                 

                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr>
                            <table border="1px" id="tableKtnb">
                                <tr class="tbhead">
                                    <th class="TD_BUTTON1">MS</th>
                                    <th class="TD_THOIGIAN">Nội dung</th>
                                    <th class="TD_TENKH123">Đơn vị tính</th>
                                    <th class="SOKU1">Kết quả</th>
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
                                        <s:if test="D7.equalsIgnoreCase('Y')">                                     

                                            <td>
                                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TD_MAPGD" onfocus="this.select()"
                                                       onblur="if (this.value == '')
                                                                   ;" readonly="true" style="background: #E7DCDA !important; font-weight: bold; text-align:left ;"/>
                                                <input type="hidden" value="<s:property  value="THUTU" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>  
                                                <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
                                                <input type="hidden" value="<s:property  value="D7" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" value="<s:property  value="D7"/>"/>  
                                                <input type="hidden" value="<s:property  value="D8" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" value="<s:property  value="D8"/>"/>
                                                <input type="hidden" value="<s:property  value="D9" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D9"/>">
                                            </td>
                                            <td>
                                                <input type="text"   value="<s:property  value="D1" />"  style="background: #E7DCDA !important;  font-weight: bold;" readonly="true"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                                       class="TD_MAPGD Bold"
                                                       onfocus="this.select();" /> 
                                            </td>                                  
                                            <td>
                                                <input type="text" value="<s:property  value="D2" />" style="background: #E7DCDA !important;"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D0 TD_MAPGD" onfocus="this.select()"
                                                       onblur="if (this.value == '')
                                                                   ;" readonly="true"/>
                                            </td>                                  
                                            <td>
                                                <input type="text" value="<s:property  value="D5" />" style="background: #E7DCDA !important;" readonly="true"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU number" onfocus="this.select()"
                                                       onblur="if (this.value == '')
                                                                   ;"/>
                                            </td>                                                                                                

                                            <td>
                                                <input type="text" value="<s:property  value="D6" />" style="background: #E7DCDA !important;" readonly="true"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="SOKU" onfocus="this.select()"
                                                       onblur="if (this.value == '')
                                                                   ;"/>
                                            </td>

                                        </tr>
                                    </s:if>
                                    <s:if test="D7.equalsIgnoreCase('N')">                                     

                                        <td>
                                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TD_MAPGD" onfocus="this.select()"
                                                   onblur="if (this.value == '')
                                                               ;" readonly="true"/>
                                            <input type="hidden" value="<s:property  value="THUTU" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>  
                                            <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
                                            <input type="hidden" value="<s:property  value="D7" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" value="<s:property  value="D7"/>"/>  
                                            <input type="hidden" value="<s:property  value="D8" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" value="<s:property  value="D8"/>"/>
                                            <input type="hidden" value="<s:property  value="D9" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D8"/>"/>

                                        </td>
                                        <td>
                                            <input type="text"   value="<s:property  value="D1" />"  readonly="true"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                                   class="TD_MAPGD"
                                                   onfocus="this.select();" /> 
                                        </td>                                  
                                        <td>
                                            <input type="text" value="<s:property  value="D2" />" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D0 TD_MAPGD" onfocus="this.select()"
                                                   onblur="if (this.value == '')
                                                               ;" readonly="true"/>
                                        </td> 
                                        <s:if test="D8.equalsIgnoreCase('Y') && D9.equalsIgnoreCase('N')">     
                                            <td>
                                                <input type="text" value="<s:property  value="D5" />" style="background: #df8505 !important;" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU number2" onfocus="this.select();
                                                       "/>
                                            </td>
                                        </s:if>
                                        <s:if test="D8.equalsIgnoreCase('N') && D9.equalsIgnoreCase('N')">     
                                            <td>
                                                <input type="text" value="<s:property  value="D5" />" id="TT_<s:property  value="D5" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU number" onfocus="this.select()"
                                                       onblur="if (this.value == '')
                                                                   ; evaluateSum(this)"/>
                                            </s:if>
                                        <s:if test="D8.equalsIgnoreCase('N') && D9.equalsIgnoreCase('Y')">     
                                        <td>
                                            <input type="text" value="<s:property  value="D5" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU" onfocus="this.select()"
                                                   onblur="if (this.value == 0)
                                                               ;" readonly="true"/> <%--D5 class="D0 SOKU" onblur="if (this.value == 0)--%>
                                        </s:if>
                         
                                        </td>                                                                                                
                                        <s:if test="D8.equalsIgnoreCase('Y')"> 
                                            <td>
                                                <input type="text" value="<s:property  value="D10" />" placeholder="Trường bắt buộc phải nhập/ không cho nhập số 0" readonly="true"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                                                       onblur="if (this.value == '')
                                                                   ;"/>
                                            </td>
                                        </s:if>
                                        <s:if test="D8.equalsIgnoreCase('N') && D9.equalsIgnoreCase('Y')">     
                                            <td>
                                                <input type="text" value="<s:property  value="D10" />" placeholder="Không nhập" readonly="true"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                                                       onblur="if (this.value == '')
                                                                   ;"/>
                                            </td>
                                        </s:if>
                                        <s:if test="D8.equalsIgnoreCase('N') && D9.equalsIgnoreCase('N')">
                                            <td>
                                                <input type="text" value="<s:property  value="D10" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                                                       onblur="if (this.value == '')
                                                                   ;"/>
                                            </td>
                                        </s:if>
                            </tr>
                        </s:if>
                    </s:iterator>

                </table>
            </tr>
        </table>

    </s:form>
</div>
</body>
</html>
