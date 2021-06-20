<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 04/KTNB</title>
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
            $(document).ready(function () {

                $("#update").click(function () {
                    document.getElementById("update").disabled = true;
                    sleep(1000);
                    document.getElementById("update").disabled = false;
                });
                //Format cac truong input.number can le phai
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});

                //CSS truong so thu tu (cot 1) cho width
//                $('#tableKtnb td:nth-child(1),#tableKtnb th:nth-child(1)').css({"width" : "35px"});

                //CSS truong so thu tu (cot 1) cho width
//                $('#tableKtnb td:nth-child(2),#tableKtnb th:nth-child(2)').css({"width" : "250px"});
                $(".KT_STT_HT").css({"width": "35px"});
                $(".KT_LOAI_CT").css({"width": "260px"});

                //An di cac cot chuc nang
                $('.hideColumn').hide();

                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);

                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
            });

            //Xu ly tinh tong cho tung dong
            function autoEvaluate() {
                var arrCot = [".KT_KT_SCT", ".KT_KT_ST", ".KT_SS_TS_SCT", ".KT_SS_TS_ST",
                    ".KT_SS_TS_TLCT", ".KT_SS_TS_TLST", ".KT_TD_SPL_SCT", ".KT_TD_SPL_ST",
                    ".KT_TD_KTNV_SL", ".KT_TD_KTNV_ST", ".KT_TD_SK_SL", ".KT_TD_SK_ST"]; //Luu cac cot cua du lieu can tinh toan
                
                  //Tinh toan cho 9 dong
                for(var i=0; i<10; i++){
                    //5=9+11+13
                    $(".KT_SS_TS_SCT").eq(i).val(parseFloat($(".KT_TD_SPL_SCT").eq(i).val()) + parseFloat($(".KT_TD_KTNV_SL").eq(i).val()) +
                            parseFloat($(".KT_TD_SK_SL").eq(i).val()) );

                    //6=10+12+14
                    $(".KT_SS_TS_ST").eq(i).val(parseFloat($(".KT_TD_SPL_ST").eq(i).val()) + parseFloat($(".KT_TD_KTNV_ST").eq(i).val()) + 
                            parseFloat($(".KT_TD_SK_ST").eq(i).val()) );
                }
                
                
                for (i = 0; i < arrCot.length; i++) {
                    //Tinh tong cho dong "Chứng từ thanh toán"
                    $(arrCot[i]).eq(2).val(parseFloat($(arrCot[i]).eq(3).val()) + parseFloat($(arrCot[i]).eq(4).val()) +
                            parseFloat($(arrCot[i]).eq(5).val()) + parseFloat($(arrCot[i]).eq(6).val()) +
                            parseFloat($(arrCot[i]).eq(7).val()) + parseFloat($(arrCot[i]).eq(8).val()));
                    if(i === 4 || i === 5)
                    {
                        $(arrCot[i]).eq(2).val(0);
                    }
                }
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

//            function test(event) {
//                alert('x');
//                if (event.which == 13 || event.keyCode == 13) {
//                    //code to execute here
//                     alert('a');
//                     event.preventDefault();
//                     event.stopPropagation();
////                    return false;
//                }
////                return true;               
//            }

            function sleep(milliSeconds) {
                var startTime = new Date().getTime(); // get the current time
                while (new Date().getTime() < startTime + milliSeconds)
                    ; // hog cpu
            }
            function doClick(id, e)
            {
//                alert('vao doClick');
                //the purpose of this function is to allow the enter key to 
                //point to the correct button to click.
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
            
            function fnResetVal(){
               $(".KT_KT_SCT").val('0');
               $(".KT_KT_ST").val('0');
               $(".KT_SS_TS_SCT").val('0');
               $(".KT_SS_TS_ST").val('0');
               
               $(".KT_SS_TS_TLCT").val('0');
               $(".KT_SS_TS_TLST").val('0');
               $(".KT_TD_SPL_SCT").val('0');
               $(".KT_TD_SPL_ST").val('');
               
               $(".KT_TD_KTNV_SL").val('0');
               $(".KT_TD_KTNV_ST").val('0');
               $(".KT_TD_SK_SL").val('0');
               $(".KT_TD_SK_ST").val('0');
            }
            
            
            function validateRequiredFields() {
                var result = true; //Luu ket qua kiem tra kieu so co dung khong

                //Cac class number phai nhap kieu so
                $(".number").each(function (index) {
                    //Kiem tra xem co nhap kieu so khong
                    if (isNaN(parseFloat($(this).val()))) {
                        result = false;
                        return false;
                    }
                });

                //Cac class nubmer2 phai nhap kieu so
                $(".number2").each(function (index) {
                    //Kiem tra xem co nhap kieu so khong
                    if (isNaN(parseFloat($(this).val()))) {
                        result = false;
                        return false;
                    }
                });

                if (result == false) {
                    //Neu nguoi dung khong nhap dung kieu du lieu
                    //Dua ra canh bao
                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                }

                return result;
            }
        </script>
    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdata" id="frmdata" action="save_data_ktnb04.action" theme="simple">
                <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                    <tr>
                        <td colspan="3" style="font-size: 14px;">04/KTNB: Báo cáo kết quả kiểm tra chứng từ kế toán<hr></td>                    
                    </tr>
                    <tr>
                        <td width="70%" >
                            <b>Phòng giao dịch: </b><input type="text" name="posCD" id="posCD" value="<s:property value="posCD"/>" readonly="readonly"/>
                            <b>Chi nhánh: </b><input type="text" name="maCn" id="maCn" value="<s:property value="maCn"/>" readonly="readonly"/>
                            <b>Quý báo cáo: </b><input type="text" name="quyBc" id="quyBc" value="<s:property value="quyBc"/>" readonly="readonly"/>
                            <b>Năm báo cáo: </b><input type="text" name="namBc" id="namBc" value="<s:property value="namBc"/>" readonly="readonly"/>
                            <b>Người dùng: </b><input type="text" name="userId" id="userId" value="<s:property value="userId"/>" readonly="readonly"/>
                        </td>
                        <td align="right">     
                            <span id="result" style="color: red">                            
                            </span>
                            <input type="button" id="checkThenSubmit" value="Cập nhật" onclick="fnCheckThenSubmit()" style="width:122px;height:25px;color: red;"/>
                            <sj:submit targets="result" value="Cập nhật" name="update" id="update"  cssStyle="display: none;"/>
                        </td>
                        <td align="right" style="width:9%">    
                        <s:url id="idurlReset4" action="ResetDataInput4.action"></s:url>
                            <sj:submit id="idReset4" name="nameReset4" href="%{idurlReset4}" 
                                       value="Reset" style="width:122px;height:25px;color: red;"
                                       targets="result"
                                       formIds="frmdata"
                                       onclick="fnResetVal()"
                                       />
                    </td>
                        
                    </tr>
                    <tr>
                        <td colspan="3">
                            <hr>
                            <table border="1px" id="tableKtnb">
                                <tr class="tbhead">
                                    <th rowspan="4">STT</th>
                                    <th rowspan="4">Loại chứng từ</th>                 
                                    <th rowspan="2" colspan="2">Tổng số chứng từ kiểm tra</th>
                                    <th colspan="10">Số chứng từ sai sót</th>  

                                    <th rowspan="4">Chức năng</th>   
                                    <th rowspan="4" class="hideColumn">Được nhập</th>
                                    <th rowspan="4" class="hideColumn">Cố định</th>
                                    <th rowspan="4" class="hideColumn">Thêm</th>
                                    <th rowspan="4" class="hideColumn">Xóa</th>
                                    <th rowspan="4" class="hideColumn">Font</th>
                                    <th rowspan="4" class="hideColumn">Cấp hiển thị</th>
                                    <th rowspan="4" class="hideColumn">Số thứ tự</th>
                                    <th rowspan="4" class="hideColumn">Ngày cập nhật</th>
                                    <th rowspan="4" class="hideColumn">Khóa</th>
                                </tr>
                                <tr class="tbhead">
                                    <th colspan="4">Tổng số</th>
                                    <th colspan="6">Trong đó</th>
                                </tr>
                                <tr class="tbhead">
                                    <th rowspan="2">Số chứng từ</th>
                                    <th rowspan="2">Số tiền</th> 
                                    <th rowspan="2">Số chứng từ</th>
                                    <th rowspan="2">Số tiền</th>                                
                                    <th rowspan="2">tỷ lệ chứng từ</th>
                                    <th rowspan="2">Tỷ lệ số tiền</th>
                                    <th colspan="2">Sai yếu tố pháp lý</th>
                                    <th colspan="2">Sai kỹ thuật nghiệp vụ</th>
                                    <th colspan="2">Sai khác</th>
                                </tr>
                                <tr class="tbhead">                                
                                    <th>Số c.từ</th>
                                    <th>Số tiền</th>
                                    <th>S.lượng</th>
                                    <th>Số tiền</th>
                                    <th>S.lượng</th>
                                    <th>Số tiền</th>
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
                                    <th>(10)</th>
                                    <th>(11)</th>
                                    <th>(12)</th>
                                    <th>(13)</th>
                                    <th>(14)</th>

                                    <th>(15)</th>
                                    <th class="hideColumn">(16)</th>
                                    <th class="hideColumn">(17)</th>
                                    <th class="hideColumn">(18)</th>
                                    <th class="hideColumn">(19)</th>
                                    <th class="hideColumn">(20)</th>
                                    <th class="hideColumn">(21)</th>
                                    <th class="hideColumn">(22)</th>
                                    <th class="hideColumn">(23)</th>
                                    <th class="hideColumn">(24)</th>
                                </tr>

                                <s:iterator value="ktnb04ModelList">
                                    <tr class="cscontent">     
                                        <!-- CuongBM: Nếu KT_DN: Được nhập = N, người dùng không được phép nhập, thay đổi -->
                                        <s:if test="KT_DN.equalsIgnoreCase('N')">                                     
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT_HT'/>" name="KT_STT_HT" class="KT_STT_HT" onfocus="this.select()" onblur="autoEvaluate()" onkeyup="test(this)" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_LOAI_CT'/>" name="KT_LOAI_CT" class="KT_LOAI_CT" onfocus="this.select()" onblur="autoEvaluate()" onkeyup="test(this)" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KT_SCT'/>" name="KT_KT_SCT" class="KT_KT_SCT number" onfocus="this.select()" onblur="autoEvaluate()" onkeyup="test(this)" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KT_ST'/>" name="KT_KT_ST" class="KT_KT_ST number2" onfocus="this.select()" onblur="autoEvaluate()" onkeyup="test(this)" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SS_TS_SCT'/>" name="KT_SS_TS_SCT" class="KT_SS_TS_SCT number" onfocus="this.select()" onblur="autoEvaluate()" onkeyup="test(this)" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SS_TS_ST'/>" name="KT_SS_TS_ST" class="KT_SS_TS_ST number2" onfocus="this.select()" onblur="autoEvaluate()" onkeyup="test(this)" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SS_TS_TLCT'/>" name="KT_SS_TS_TLCT" class="KT_SS_TS_TLCT number2" onfocus="this.select()" onblur="autoEvaluate()" onkeyup="test(this)" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SS_TS_TLST'/>" name="KT_SS_TS_TLST" class="KT_SS_TS_TLST number2" onfocus="this.select()" onblur="autoEvaluate()" onkeyup="test(this)" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TD_SPL_SCT'/>" name="KT_TD_SPL_SCT" class="KT_TD_SPL_SCT number" onfocus="this.select()" onblur="autoEvaluate()" onkeyup="test(this)" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TD_SPL_ST'/>" name="KT_TD_SPL_ST" class="KT_TD_SPL_ST number2" onfocus="this.select()" onblur="autoEvaluate()" onkeyup="test(this)" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TD_KTNV_SL'/>" name="KT_TD_KTNV_SL" class="KT_TD_KTNV_SL number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TD_KTNV_ST'/>" name="KT_TD_KTNV_ST" class="KT_TD_KTNV_ST number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TD_SK_SL'/>" name="KT_TD_SK_SL" class="KT_TD_SK_SL number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TD_SK_ST'/>" name="KT_TD_SK_ST" class="KT_TD_SK_ST number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                            </s:if>

                                        <!-- CuongBM: Nếu KT_DN: Được nhập = Y, người dùng được phép nhập, thay đổi -->
                                        <s:if test="KT_DN.equalsIgnoreCase('Y')">                                     
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT_HT'/>" name="KT_STT_HT" class="KT_STT_HT" onfocus="this.select()" onblur="autoEvaluate()" onkeyup="test(this)" readonly="readonly"/></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_LOAI_CT'/>" name="KT_LOAI_CT" class="KT_LOAI_CT" onfocus="this.select()" onblur="autoEvaluate()"  readonly="readonly"/></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KT_SCT'/>" name="KT_KT_SCT" class="KT_KT_SCT number" onfocus="this.select()" onblur="if (this.value == '') {
                                                        this.value = 0
                                                    }
                                                    ;
                                                    autoEvaluate(this)"
                                                                                                   onkeypress="doClick('checkThenSubmit',event)"
                                                                                                   /></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KT_ST'/>" name="KT_KT_ST" class="KT_KT_ST number2" onfocus="this.select()" onblur="if (this.value == '') {
                                                        this.value = 0
                                                    }
                                                    ;
                                                    autoEvaluate(this)" 
                                                                                                   onkeypress="doClick('checkThenSubmit',event)""/></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SS_TS_SCT'/>" name="KT_SS_TS_SCT" class="KT_SS_TS_SCT number" onfocus="this.select()" onblur="if (this.value == '') {
                                                        this.value = 0
                                                    }
                                                    ;
                                                    autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)" readonly="readonly"/></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SS_TS_ST'/>" name="KT_SS_TS_ST" class="KT_SS_TS_ST number2" onfocus="this.select()" onblur="if (this.value == '') {
                                                        this.value = 0
                                                    }
                                                    ;
                                                    autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)" readonly="readonly"/></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SS_TS_TLCT'/>" name="KT_SS_TS_TLCT" class="KT_SS_TS_TLCT number2" onfocus="this.select()" onblur="if (this.value == '') {
                                                        this.value = 0
                                                    }
                                                    ;
                                                    autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SS_TS_TLST'/>" name="KT_SS_TS_TLST" class="KT_SS_TS_TLST number2" onfocus="this.select()" onblur="if (this.value == '') {
                                                        this.value = 0
                                                    }
                                                    ;
                                                    autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TD_SPL_SCT'/>" name="KT_TD_SPL_SCT" class="KT_TD_SPL_SCT number" onfocus="this.select()" onblur="if (this.value == '') {
                                                        this.value = 0
                                                    }
                                                    ;
                                                    autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TD_SPL_ST'/>" name="KT_TD_SPL_ST" class="KT_TD_SPL_ST number2" onfocus="this.select()" onblur="if (this.value == '') {
                                                        this.value = 0
                                                    }
                                                    ;
                                                    autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TD_KTNV_SL'/>" name="KT_TD_KTNV_SL" class="KT_TD_KTNV_SL number" onfocus="this.select()" onblur="if (this.value == '') {
                                                        this.value = 0
                                                    }
                                                    ;
                                                    autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TD_KTNV_ST'/>" name="KT_TD_KTNV_ST" class="KT_TD_KTNV_ST number2" onfocus="this.select()" onblur="if (this.value == '') {
                                                        this.value = 0
                                                    }
                                                    ;
                                                    autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TD_SK_SL'/>" name="KT_TD_SK_SL" class="KT_TD_SK_SL number" onfocus="this.select()" onblur="if (this.value == '') {
                                                        this.value = 0
                                                    }
                                                    ;
                                                    autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                            <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TD_SK_ST'/>" name="KT_TD_SK_ST" class="KT_TD_SK_ST number2" onfocus="this.select()" onblur="if (this.value == '') {
                                                        this.value = 0
                                                    }
                                                    ;
                                                    autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                            </s:if>


                                        <!-- CuongBM: Xử lý thêm xóa dòng-->
                                        <td class="<s:property value='KT_FONTWEIGHT'/>">
                                            <!-- CuongBM: Nếu được thêm dòng-->
                                            <s:if test="KT_THEM.equalsIgnoreCase('Y')">                                     
                                                <input type="button" onclick="addRow(this.parentNode.parentNode.rowIndex)" value="Them"/>                                            
                                            </s:if>

                                            <!-- CuongBM: Nếu được xóa dòng-->
                                            <s:if test="KT_XOA.equalsIgnoreCase('Y')">                                     
                                                <input type="button" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" value="Xoa"/>
                                            </s:if>
                                        </td> 

                                        <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_DN'/>" name="KT_DN"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_CO_DINH'/>" name="KT_CO_DINH"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_THEM'/>" name="KT_THEM"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_XOA'/>" name="KT_XOA"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_FONTWEIGHT'/>" name="KT_FONTWEIGHT"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_CAPHT'/>" name="KT_CAPHT"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_STT'/>" name="KT_STT"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='NG_CAPNHAT'/>" name="NG_CAPNHAT"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_KHOA'/>" name="KT_KHOA"/></td>
                                    </tr>
                                </s:iterator>
                            </table>
                    </tr>
                </table>

            </s:form>
        </div>
    </body>
</html>
