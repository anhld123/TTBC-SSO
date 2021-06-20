<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 06a/KTNB</title>
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
            th{
                background-color: #DCDCDC;
                border-color: #999;
                height: 18px;
            }
            td{
                border-color: #999;
                height: 20px;
            }
            table.editDelete{
                border-collapse: collapse;
                width: 97%;
                border-color: #999;
            }
            table.editDelete tr:focus{
                background-color:#FFE47A;
                /*cursor: pointer; hover*/
            }
            .highlight_row {
                background-color: #FFB951; 
                color:#000;
            }
            #divDonvitinh{
                text-align: right;
                padding-right: 30px;
                color: red; 
                font-weight: initial;
            }
            #divTitle{
                color: blue; 
                font-weight: bolder; 
                font-size: larger;
            }
            input[type="text"]
            {
                width: 100%;
                border: 0px;

                /*color: #000000*/
                border-color: #18ab29;
                /*background: #F9F9F9;*/
                color:#666666;
            }
            input[type=text]:focus, textarea:focus {
                box-shadow: 0 0 5px fuchsia;
                border: 1px solid fuchsia;
            }
            input:read-only {
                background-color: #E5E5E5;
            }
            input:read-only {
            background-color: #E5E5E5;
            height: 22px;
        }
        </style>
        
        <script>
            $(document).ready(function(){
                $("#update").click(function () {
                document.getElementById("update").disabled = true;                
                sleep(1000);
                document.getElementById("update").disabled = false; 
            });
                //Format cac truong input.number can le phai
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".TD_GOC").css({"width": "70px"});
                $('.D0').css({"text-align": "center"});
                $(".TD_LAI").css({"width": "55px"});
                $(".TD_SOVU").css({"width": "35px"});
                $(".TD_STT").css({"width": "30px"});
                $(".TD_CHITEU").css({"width": "130px"});
                $(".TEN_KH").css({"width": "100%"});
                
                $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
                
                //CSS truong so thu tu (cot 1) cho width
//                $('#tableKtnb td:nth-child(1),#tableKtnb th:nth-child(1)').css({"width" : "35px"});
                
                //CSS truong so thu tu (cot 1) cho width
//                $('#tableKtnb td:nth-child(2),#tableKtnb th:nth-child(2)').css({"width" : "250px"});
                $(".KT_STT_HT").css({"width" : "35px"});
                $(".KT_DT").css({"width" : "240px"});                
                
                //An di cac cot chuc nang
                $('.hideColumn').hide();
                
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true,0);  
                
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true,0);  
            });
            
            //Xu ly tinh tong cho tung dong
            
            
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
            
            //Check xem du lieu da ok chua
            //Neu ok roi thi goi su kien submit du lieu
            function fnCheckThenSubmit(){
                $("#update").click(function () {
                   sleep(1000);  
                });
                if(validateRequiredFields()){
                    $("#update").trigger('click');
                }
            }
            
            function sleep(milliSeconds){
                var startTime = new Date().getTime(); // get the current time
                while (new Date().getTime() < startTime + milliSeconds); // hog cpu
            }
            
            function fnResetVal(){
               $(".KT_TDDN_SV").val('0');
               $(".KT_TDDN_ST_G").val('0');
               $(".KT_TDDN_ST_L").val('0');
               $(".KT_TDDN_ST_TK").val('0');
               
               $(".KT_PHTK_SV").val('0');
               $(".KT_PHTK_ST_G").val('0');
               $(".KT_PHTK_ST_L").val('0');
               $(".KT_PHTK_ST_TK").val('');
               
               $(".KT_DTH_SV").val('0');
               $(".KT_DTH_ST_G").val('0');
               $(".KT_DTH_ST_L").val('0');
               $(".KT_DTH_ST_TK").val('0');
               
               $(".KT_TDCK_SV").val('0');
               $(".KT_TDCK_ST_G").val('0');
               $(".KT_TDCK_ST_L").val('0');
               $(".KT_TDCK_ST_TK").val('0');
            }
            
            function validateRequiredFields(){
                var result = true; //Luu ket qua kiem tra kieu so co dung khong
                
                //Cac class number phai nhap kieu so
                $(".number").each(function(index){
                    //Kiem tra xem co nhap kieu so khong
                    if(isNaN(parseFloat($(this).val()))){
                        result = false; 
                        return false;
                    }
                });
                
                //Cac class nubmer2 phai nhap kieu so
                $(".number2").each(function(index){
                    //Kiem tra xem co nhap kieu so khong
                    if(isNaN(parseFloat($(this).val()))){
                        result = false;
                        return false;
                    }
                });
                
                if(result == false){
                    //Neu nguoi dung khong nhap dung kieu du lieu
                    //Dua ra canh bao
                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                }
                
                return result;
            }
        </script>
        
        <script>
            function initTable()
            {
                autoEvaluate();
            };
            function autoEvaluate(){
//                alert('vao doClick');
                var arrCot = [".D3",".D4",".D5",
                    ".D6",".D7",".D8",".D9",
                    ".D10",".D11",".D12",".D13",".D14",".D15",".D16",".D17",".D18"]; //Luu cac cot cua du lieu can tinh toa                
                
                
                // Tinh cho dong 1
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(1).val( 
                            parseFloat($(arrCot[i]).eq(2).val()) + parseFloat($(arrCot[i]).eq(3).val()) + 
                             parseFloat($(arrCot[i]).eq(4).val()) + 
                            parseFloat($(arrCot[i]).eq(5).val()) + parseFloat($(arrCot[i]).eq(6).val()));
                }                
                // Tinh cho dong 2.1
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(7).val( parseFloat($(arrCot[i]).eq(8).val()) + 
                            parseFloat($(arrCot[i]).eq(9).val()) + parseFloat($(arrCot[i]).eq(10).val()) + 
                             parseFloat($(arrCot[i]).eq(11).val()) + 
                            parseFloat($(arrCot[i]).eq(12).val()));
                }
                // Tinh cho dong 2.2
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(15).val( parseFloat($(arrCot[i]).eq(0).val()) + 
                            parseFloat($(arrCot[i]).eq(1).val()) +
                            parseFloat($(arrCot[i]).eq(7).val()) +
                            parseFloat($(arrCot[i]).eq(13).val()) +
                            parseFloat($(arrCot[i]).eq(14).val()));
                }                                
                
                for(var i=0; i<30; i++){
                    //8=2+4-6
                    $(".D15").eq(i).val(parseFloat($(".D3").eq(i).val()) + parseFloat($(".D7").eq(i).val())
                            - parseFloat($(".D11").eq(i).val()));
                    $(".D16").eq(i).val(parseFloat($(".D4").eq(i).val()) + parseFloat($(".D8").eq(i).val())
                            - parseFloat($(".D12").eq(i).val()));
                    $(".D17").eq(i).val(parseFloat($(".D5").eq(i).val()) + parseFloat($(".D9").eq(i).val())
                            - parseFloat($(".D13").eq(i).val()));
                    $(".D18").eq(i).val(parseFloat($(".D6").eq(i).val()) + parseFloat($(".D10").eq(i).val())
                            - parseFloat($(".D14").eq(i).val()));
                    
                }
            };
                </script>
        
    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdataKtnb06A" id="frmdataKtnb06A" action="save_data_ktnb06A.action" theme="simple">
            <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                <tr>
                    <td colspan="3" style="font-size: 14px;">06A/KTNB: BÁO CÁO CÁC VỤ VIỆC DO CHIẾM DỤNG, THAM Ô VÀ KẾT QUẢ THU HỒI<hr></td>                    
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
                        <s:url id="idurlReset6" action="ResetDataInput6.action"></s:url>
                            <sj:submit id="idReset6" name="nameReset6" href="%{idurlReset6}" 
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
                                <th rowspan="3">STT</th>
                                <th rowspan="3">Đối tượng</th>                 
                                <th colspan="4">Tồn đọng đầu năm</th>
                                <th colspan="4">Phát hiện trong kỳ</th>
                                <th colspan="4">Đã thu hồi</th>  
                                <th colspan="4">Tồn đọng cuối kỳ</th>  
                                  
                                <th rowspan="3" class="hideColumn">Được nhập</th>
                                <th rowspan="3" class="hideColumn">Cố định</th>
                                <th rowspan="3" class="hideColumn">Thêm</th>
                                <th rowspan="3" class="hideColumn">Xóa</th>
                                <th rowspan="3" class="hideColumn">Font</th>
                                <th rowspan="3" class="hideColumn">Cấp hiển thị</th>
                                <th rowspan="3" class="hideColumn">Số thứ tự</th>
                                <th rowspan="3" class="hideColumn">Ngày cập nhật</th>
                                <th rowspan="3" class="hideColumn">Khóa</th>
                            </tr>
                            <tr class="tbhead">
                                <th rowspan="2" class="TD_SOVU">Số vụ</th>
                                <th colspan="3">Số tiền</th>
                                <th rowspan="2" class="TD_SOVU">Số vụ</th>
                                <th colspan="3">Số tiền</th>
                                <th rowspan="2" class="TD_SOVU">Số vụ</th>
                                <th colspan="3">Số tiền</th>
                                <th rowspan="2" class="TD_SOVU">Số vụ</th>
                                <th colspan="3">Số tiền</th>
                            </tr>
                            <tr class="tbhead">                                
                                <th class="TD_GOC">Gốc</th>
                                <th class="TD_LAI">Lãi</th>
                                <th class="TD_LAI">T.Gửi</th>
                                <th class="TD_GOC">Gốc</th>
                                <th class="TD_LAI">Lãi</th>                               
                                <th class="TD_LAI">T.Gửi</th>
                                <th class="TD_GOC">Gốc</th>
                                <th class="TD_LAI">Lãi</th>
                                <th class="TD_LAI">T.Gửi</th>
                                <th class="TD_GOC">Gốc</th>
                                <th class="TD_LAI">Lãi</th>
                                <th class="TD_LAI">T.Gửi</th>
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
                                <th>(16)</th>
                                
                                <th>(17)</th>
                                <th>(18)</th>
                                
                                <th class="hideColumn">(20)</th>
                                <th class="hideColumn">(21)</th>
                                <th class="hideColumn">(22)</th>
                                <th class="hideColumn">(23)</th>
                                <th class="hideColumn">(24)</th>
                                <th class="hideColumn">(25)</th>
                                <th class="hideColumn">(26)</th>
                                <th class="hideColumn">(27)</th>
                                <th class="hideColumn">(28)</th>
                            </tr>
                            
                            <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                                <tr height="22">    
                                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">                                        
                                            <td align = "right" class="TD_STT">
                                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                                                <input type="hidden" value="<s:property  value="THUTU" />"
                                                 name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>  
                                                <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                                 name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/> 
                                            </td>
                                            <td align = "right" class="TD_CHITIEU">
                                                <input type="text" value="<s:property  value="TEN" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                                            </td>                                                                          

                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D3" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                                                </td>                                                                                                

                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D4" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                                               </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D5" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D6" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td>
                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D7" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D8" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D9" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D10" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td>  
                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D11" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D12" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D12 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D13" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D14" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D15" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td>    
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D16" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D16 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td>   
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D17" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="D17 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td>   
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D18" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="D18 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td>   
                                        
                                    </s:if>   
                                    <s:else>
                                            <td align = "right" class="TD_STT">
                                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                                                <input type="hidden" value="<s:property  value="THUTU" />"
                                                 name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>  
                                                <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                                 name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>  
                                            
                                            </td>
                                            <td align = "right" class="TD_CHITIEU">
                                                <input type="text" value="<s:property  value="TEN" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                                            </td>                              

                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D3" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                                                </td>                                                                                                

                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D4" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                                                <input type="hidden" value="<s:property  value="MA" />"
                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D5" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D6" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                                       />
                                            </td>
                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D7" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D8" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                                       />
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D9" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D10" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                                       />
                                            </td>  
                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D11" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D12" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D12 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D13" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D14" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D15" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td>    
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D16" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D16 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td>   
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D17" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="D17 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td>   
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D18" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="D18 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"
                                                       />
                                            </td>                          
                                    </s:else>    
                                </tr>
                    
                </s:iterator>
                        </table>
                </tr>
            </table>
                    
            </s:form>
            <script>
                initTable();
            </script>
        </div>
    </body>
</html>
