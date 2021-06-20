<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 01/KNTC</title>
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
            $(document).ready(function(){
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
                $(".KT_DV").css({"width" : "50px"});
//                $(".KT_TC_UT").css({"width" : "240px"});                
                
                //An di cac cot chuc nang
                $('.hideColumn').hide();
                
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true,0);  
                
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true,2);  
            });
            
            //Xu ly tinh tong cho tung dong
            function autoEvaluate(){
//                var arrCot = [".KT_TONG_TO",".KT_TONG_DUNO",".KT_TKVV_ST",".KT_TKVV_DN",
//                    ".KT_SO_TO_TOT",".KT_DUNO_TOT",".KT_SO_TO_KHA",".KT_DUNO_KHA",
//                    ".KT_SO_TO_TB",".KT_DUNO_TB",".KT_SO_TO_KEM",".KT_DUNO_KEM"]; //Luu cac cot cua du lieu can tinh toan
//                
//                for (i = 0; i < arrCot.length; i++) { 
//                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
//                    $(arrCot[i]).eq(0).val(parseFloat($(arrCot[i]).eq(1).val()) + parseFloat($(arrCot[i]).eq(2).val()) + 
//                            parseFloat($(arrCot[i]).eq(3).val()) + parseFloat($(arrCot[i]).eq(4).val()));
//                }
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
            
            function sleep(milliSeconds){
                var startTime = new Date().getTime(); // get the current time
                while (new Date().getTime() < startTime + milliSeconds); // hog cpu
            }
            
            function fnResetVal(){
               $(".KT_TX_L").val('0');
               $(".KT_TX_N").val('0');
               $(".KT_TX_VV_C").val('0');
               $(".KT_TX_VV_M").val('0');
               
               $(".KT_TX_DDN_SD").val('0');
               $(".KT_TX_DDN_N").val('0');
               $(".KT_TX_DDN_VV_C").val('0');
               $(".KT_TX_DDN_VV_M").val('');
               
               $(".KT_DK_L").val('0');
               $(".KT_DK_N").val('0');
               $(".KT_DK_VV_C").val('0');
               $(".KT_DK_VV_M").val('0');
               
               
                $(".KT_DK_DDN_SD").val('0');
               $(".KT_DK_DDN_N").val('0');
               $(".KT_DK_DDN_VV_C").val('0');
               $(".KT_DK_DDN_VV_M").val('0');
               
               $(".KT_ND_KN_HC_TC").val('0');
               $(".KT_ND_KN_HC_CS").val('0');
               $(".KT_ND_KN_HC_NTS").val('0');
               $(".KT_ND_KN_HC_CD").val('');
               
               $(".KT_ND_KN_TP").val('0');
               $(".KT_ND_KN_CT").val('0');
               $(".KT_ND_TC_HC").val('0');
               $(".KT_ND_TC_TP").val('0');
               
               
               $(".KT_ND_TC_TN").val('0');
               $(".KT_ND_KHAC").val('0');
               $(".KT_KQ_CGQ").val('0');
               $(".KT_KQ_GQ_CCQD").val('');
               
               $(".KT_KQ_GQ_DCQD").val('0');
               $(".KT_KQ_GQ_DCBA").val('0');
               $(".KT_GHICHU").val('');

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
    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdata" id="frmdata" action="save_data_ktnb08.action" theme="simple">
            <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                <tr>
                    <td colspan="3" style="font-size: 14px;">01/KNTC: Tổng hợp kết quả tiếp công dân/khách hàng<hr></td>                    
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
                        <div id="result" style="color: red">                            
                        </div>
                        <input type="button" id="checkThenSubmit" value="Cập nhật" onclick="fnCheckThenSubmit()" style="width:122px;height:25px;color: red;"/>
                        <sj:submit targets="result" value="Cập nhật" name="update" id="update"  cssStyle="display: none;"/>
                    </td>
                    
                    <td align="right" style="width:125px">    
                        <s:url id="idurlReset8" action="ResetDataInput8.action"></s:url>
                            <sj:submit id="idReset8" name="nameReset8" href="%{idurlReset8}" 
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
                                <th rowspan="4">Đơn vị</th>
                                <th colspan="8">Tiếp thường xuyên</th>                 
                                <th colspan="8">Tiếp định kỳ và đột xuất của Lãnh đạo</th>
                                <th colspan="10">Nội dung tiếp công dân/khách hàng (số vụ việc)</th>
                                <th colspan="4">Kết quả tiếp công dân/khách hàng (số vụ việc)</th>
                                <th rowspan="4">Ghi chú</th>
                                
                                <th rowspan="4">Chức năng</th>   
                                <th rowspan="4" class="hideColumn">Được nhập</th>
                                <th rowspan="4" class="hideColumn">Cố định</th>
                                <th rowspan="4" class="hideColumn">Thêm</th>
                                <th rowspan="4" class="hideColumn">Xóa</th>
                                <th rowspan="4" class="hideColumn">Font</th>
                                <th rowspan="4" class="hideColumn">Cấp hiển thị</th>
                                <th rowspan="4" class="hideColumn">Số thứ tự</th>
                                <th rowspan="4" class="hideColumn">Ngày cập nhật</th>
                            </tr>
                            <tr class="tbhead">
                                <th rowspan="3">Lượt</th>
                                <th rowspan="3">Người</th>
                                <th colspan="2">Vụ việc</th>
                                <th colspan="4">Đoàn người</th>
                                <th rowspan="3">Lượt</th>
                                <th rowspan="3">Người</th>
                                <th colspan="2">Vụ việc</th>
                                <th colspan="4">Đoàn người</th>
                                <th colspan="6">Khiếu nại</th>
                                <th colspan="3">Tố cáo</th>
                                <th rowspan="3">Phản ánh, kiến nghị khác</th>
                                <th rowspan="3">Chưa được giải quyết</th>
                                <th colspan="3">Đã được giải quyết</th>
                            </tr>
                            <tr class="tbhead">
                                <th rowspan="2">Cũ</th>
                                <th rowspan="2">Mới phát sinh</th>
                                <th rowspan="2">Số đoàn</th>
                                <th rowspan="2">Người</th>
                                <th colspan="2">Vụ việc</th>
                                <th rowspan="2">Cũ</th>
                                <th rowspan="2">Mới phát sinh</th>
                                <th rowspan="2">Số đoàn</th>
                                <th rowspan="2">Người</th>
                                <th colspan="2">Vụ việc</th>
                                <th colspan="4">Lĩnh vực hành chính</th>
                                <th rowspan="2">Lĩnh vực tư pháp</th>
                                <th rowspan="2">Lĩnh vực CT, VH, XH khác</th>
                                <th rowspan="2">Lĩnh vực hành chính</th>
                                <th rowspan="2">Lĩnh vực tư pháp</th>
                                <th rowspan="2">Tham nhũng</th>
                                <th rowspan="2">Chưa có QĐ giải quyết</th>
                                <th rowspan="2">Đã có QĐ giải quyết (lần 1, lần 2, cuối cùng)</th>
                                <th rowspan="2">Đã có bản án của Tòa</th>
                            </tr>
                            <tr class="tbhead"> 
                                <th>Cũ</th>
                                <th>Mới phát sinh</th>
                                <th>Cũ</th>
                                <th>Mới phát sinh</th>
                                <th>Về tranh chấp, đòi đất cũ, đền bù, giải tỏa,...</th>
                                <th>Về chính sách</th>
                                <th>Về nhà, tài sản</th>
                                <th>Về chế độ CC, VC</th>
                            </tr>
                            <tr class="tbhead">
                                <th>MS</th>
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
                                <th>(19)</th>
                                <th>(20)</th>
                                <th>(21)</th>
                                <th>(22)</th>
                                <th>(23)</th>
                                <th>(24)</th>
                                <th>(25)</th>
                                <th>(26)</th>
                                <th>(27)</th>
                                <th>(28)</th>
                                <th>(29)</th>
                                <th>(30)</th>
                                <th>(31)</th>
                                
                                <th>(32)</th>
                                <th class="hideColumn">(33)</th>
                                <th class="hideColumn">(34)</th>
                                <th class="hideColumn">(35)</th>
                                <th class="hideColumn">(36)</th>
                                <th class="hideColumn">(37)</th>
                                <th class="hideColumn">(38)</th>
                                <th class="hideColumn">(39)</th>
                                <th class="hideColumn">(40)</th>
                            </tr>
                            
                            
                            <s:iterator value="ktnb08ModelList">
                                <tr class="cscontent">                                 
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DV'/>" name="KT_DV" class="KT_DV" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>

                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_L'/>" name="KT_TX_L" class="KT_TX_L number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_N'/>" name="KT_TX_N" class="KT_TX_N number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_VV_C'/>" name="KT_TX_VV_C" class="KT_TX_VV_C number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_VV_M'/>" name="KT_TX_VV_M" class="KT_TX_VV_M number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_DDN_SD'/>" name="KT_TX_DDN_SD" class="KT_TX_DDN_SD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_DDN_N'/>" name="KT_TX_DDN_N" class="KT_TX_DDN_N number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_DDN_VV_C'/>" name="KT_TX_DDN_VV_C" class="KT_TX_DDN_VV_C number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_DDN_VV_M'/>" name="KT_TX_DDN_VV_M" class="KT_TX_DDN_VV_M number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_L'/>" name="KT_DK_L" class="KT_DK_L number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_N'/>" name="KT_DK_N" class="KT_DK_N number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)" /></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_VV_C'/>" name="KT_DK_VV_C" class="KT_DK_VV_C number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_VV_M'/>" name="KT_DK_VV_M" class="KT_DK_VV_M number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_DDN_SD'/>" name="KT_DK_DDN_SD" class="KT_DK_DDN_SD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_DDN_N'/>" name="KT_DK_DDN_N" class="KT_DK_DDN_N number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_DDN_VV_C'/>" name="KT_DK_DDN_VV_C" class="KT_DK_DDN_VV_C number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_DDN_VV_M'/>" name="KT_DK_DDN_VV_M" class="KT_DK_DDN_VV_M number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KN_HC_TC'/>" name="KT_ND_KN_HC_TC" class="KT_ND_KN_HC_TC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KN_HC_CS'/>" name="KT_ND_KN_HC_CS" class="KT_ND_KN_HC_CS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KN_HC_NTS'/>" name="KT_ND_KN_HC_NTS" class="KT_ND_KN_HC_NTS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KN_HC_CD'/>" name="KT_ND_KN_HC_CD" class="KT_ND_KN_HC_CD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KN_TP'/>" name="KT_ND_KN_TP" class="KT_ND_KN_TP number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KN_CT'/>" name="KT_ND_KN_CT" class="KT_ND_KN_CT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_TC_HC'/>" name="KT_ND_TC_HC" class="KT_ND_TC_HC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_TC_TP'/>" name="KT_ND_TC_TP" class="KT_ND_TC_TP number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_TC_TN'/>" name="KT_ND_TC_TN" class="KT_ND_TC_TN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KHAC'/>" name="KT_ND_KHAC" class="KT_ND_KHAC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CGQ'/>" name="KT_KQ_CGQ" class="KT_KQ_CGQ number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_GQ_CCQD'/>" name="KT_KQ_GQ_CCQD" class="KT_KQ_GQ_CCQD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_GQ_DCQD'/>" name="KT_KQ_GQ_DCQD" class="KT_KQ_GQ_DCQD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_GQ_DCBA'/>" name="KT_KQ_GQ_DCBA" class="KT_KQ_GQ_DCBA number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_GHICHU'/>" name="KT_GHICHU" class="KT_GHICHU" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    
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
                                </tr>
                            </s:iterator>
                        </table>
                </tr>
            </table>
                    
            </s:form>
        </div>
    </body>
</html>
