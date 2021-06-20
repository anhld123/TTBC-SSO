<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 05/KTNB</title>
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
                $(".KT_STT_HT").css({"width" : "35px"});
                $(".KT_TC_UT").css({"width" : "240px"});                
                
                //An di cac cot chuc nang
                $('.hideColumn').hide();
                
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true,0);  
                
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true,2);  
            });
            
            //Xu ly tinh tong cho tung dong
            function autoEvaluate(){
                var arrCot = [".KT_TONG_TO",".KT_TONG_DUNO",".KT_TKVV_ST",".KT_TKVV_DN",
                    ".KT_SO_TO_TOT",".KT_DUNO_TOT",".KT_SO_TO_KHA",".KT_DUNO_KHA",
                    ".KT_SO_TO_TB",".KT_DUNO_TB",".KT_SO_TO_KEM",".KT_DUNO_KEM"]; //Luu cac cot cua du lieu can tinh toan
                
                  //Tinh toan cho 7 dong
                for(var i=0; i<8; i++){
                    //5=7+9+11+13
                    $(".KT_TKVV_ST").eq(i).val(parseFloat($(".KT_SO_TO_TOT").eq(i).val()) + parseFloat($(".KT_SO_TO_KHA").eq(i).val()) +
                            parseFloat($(".KT_SO_TO_TB").eq(i).val()) + parseFloat($(".KT_SO_TO_KEM").eq(i).val()));

                    //6=8+10+12+14
                    $(".KT_TKVV_DN").eq(i).val(parseFloat($(".KT_DUNO_TOT").eq(i).val()) + parseFloat($(".KT_DUNO_KHA").eq(i).val()) + 
                            parseFloat($(".KT_DUNO_TB").eq(i).val()) + parseFloat($(".KT_DUNO_KEM").eq(i).val()) );
                }
                
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(0).val(parseFloat($(arrCot[i]).eq(1).val()) + parseFloat($(arrCot[i]).eq(2).val()) + 
                            parseFloat($(arrCot[i]).eq(3).val()) + parseFloat($(arrCot[i]).eq(4).val()));
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
               $(".KT_TONG_TO").val('0');
               $(".KT_TONG_DUNO").val('0');
               $(".KT_TKVV_ST").val('0');
               $(".KT_TKVV_DN").val('0');
               
               $(".KT_SO_TO_TOT").val('0');
               $(".KT_DUNO_TOT").val('0');
               $(".KT_SO_TO_KHA").val('0');
               $(".KT_DUNO_KHA").val('');
               
               $(".KT_SO_TO_TB").val('0');
               $(".KT_DUNO_TB").val('0');
               $(".KT_SO_TO_KEM").val('0');
               $(".KT_DUNO_KEM").val('0');
            }
            
            function sleep(milliSeconds){
                var startTime = new Date().getTime(); // get the current time
                while (new Date().getTime() < startTime + milliSeconds); // hog cpu
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
            <s:form name="frmdata" id="frmdata" action="save_data_ktnb05.action" theme="simple">
            <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                <tr>
                    <td colspan="3" style="font-size: 14px;">05/KTNB: Báo cáo kết quả kiểm tra hoạt động của tổ tiết kiệm & vay vốn<hr></td>                    
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
                        <s:url id="idurlReset5" action="ResetDataInput5.action"></s:url>
                            <sj:submit id="idReset5" name="nameReset5" href="%{idurlReset5}" 
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
                                <th rowspan="3">Tổ chức nhận ủy thác cho vay</th>                 
                                <th rowspan="2" colspan="2">Tổng số</th>
                                <th rowspan="2" colspan="2">Tổ TK&VV đã được kiểm tra</th>
                                <th rowspan="1" colspan="8">Chất lượng hoạt động của tổ</th>                              

                                <th rowspan="3">Chức năng</th>   
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
                                <th colspan="2">Loại tốt</th>                                 
                                <th colspan="2">Loại khá</th>                                    
                                <th colspan="2">Loại trung bình</th>                                   
                                <th colspan="2">Loại kém</th>    
                            </tr>
                            <tr class="tbhead">
                                <th colspan="1">Số tổ</th>
                                <th colspan="1">Dư nợ</th>                                
                                <th colspan="1">Số tổ</th>
                                <th colspan="1">Dư nợ</th>                        
                                <th colspan="1">Số tổ</th>
                                <th colspan="1">Dư nợ</th>                               
                                <th colspan="1">Số tổ</th>
                                <th colspan="1">Dư nợ</th>                                
                                <th colspan="1">Số tổ</th>
                                <th colspan="1">Dư nợ</th>
                                <th colspan="1">Số tổ</th>
                                <th colspan="1">Dư nợ</th>                                
                            </tr>
                            <tr class="tbhead">
                                <th>1</th>
                                <th>2</th>
                                <th>3</th>
                                <th>4</th>
                                <th>5=7+9+11+13</th>
                                <th>6=8+10+12+14</th>
                                <th>7</th>                                
                                <th>8</th>
                                <th>9</th>
                                <th>10</th>
                                <th>11</th>
                                <th>12</th>
                                <th>13</th>
                                <th>14</th>
                                
                                <th>15</th>
                                <th class="hideColumn">16</th>
                                <th class="hideColumn">17</th>
                                <th class="hideColumn">18</th>
                                <th class="hideColumn">19</th>
                                <th class="hideColumn">20</th>
                                <th class="hideColumn">21</th>
                                <th class="hideColumn">22</th>
                                <th class="hideColumn">23</th>
                                <th class="hideColumn">24</th>
                            </tr>
                            
                            <s:iterator value="ktnb05ModelList">
                                <tr class="cscontent">     
                                    <!-- CuongBM: Nếu KT_DN: Được nhập = N, người dùng không được phép nhập, thay đổi -->
                                    <s:if test="KT_DN.equalsIgnoreCase('N')">                                     
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT_HT'/>" name="KT_STT_HT" class="KT_STT_HT" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TC_UT'/>" name="KT_TC_UT" class="KT_TC_UT" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TONG_TO'/>" name="KT_TONG_TO" class="KT_TONG_TO number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TONG_DUNO'/>" name="KT_TONG_DUNO" class="KT_TONG_DUNO number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TKVV_ST'/>" name="KT_TKVV_ST" class="KT_TKVV_ST number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TKVV_DN'/>" name="KT_TKVV_DN" class="KT_TKVV_DN number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TO_TOT'/>" name="KT_SO_TO_TOT" class="KT_SO_TO_TOT number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DUNO_TOT'/>" name="KT_DUNO_TOT" class="KT_DUNO_TOT number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TO_KHA'/>" name="KT_SO_TO_KHA" class="KT_SO_TO_KHA number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DUNO_KHA'/>" name="KT_DUNO_KHA" class="KT_DUNO_KHA number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TO_TB'/>" name="KT_SO_TO_TB" class="KT_SO_TO_TB number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DUNO_TB'/>" name="KT_DUNO_TB" class="KT_DUNO_TB number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TO_KEM'/>" name="KT_SO_TO_KEM" class="KT_SO_TO_KEM number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>">
                                        <input type="text" value="<s:property value='KT_DUNO_KEM'/>" name="KT_DUNO_KEM" class="KT_DUNO_KEM number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                    </s:if>
                                        
                                    <!-- CuongBM: Nếu KT_DN: Được nhập = Y, người dùng được phép nhập, thay đổi -->
                                    <s:if test="KT_DN.equalsIgnoreCase('Y')">                                     
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT_HT'/>" name="KT_STT_HT" class="KT_STT_HT" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TC_UT'/>" name="KT_TC_UT" class="KT_TC_UT" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TONG_TO'/>" name="KT_TONG_TO" class="KT_TONG_TO number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TONG_DUNO'/>" name="KT_TONG_DUNO" class="KT_TONG_DUNO number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TKVV_ST'/>" name="KT_TKVV_ST" class="KT_TKVV_ST number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TKVV_DN'/>" name="KT_TKVV_DN" class="KT_TKVV_DN number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TO_TOT'/>" name="KT_SO_TO_TOT" class="KT_SO_TO_TOT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DUNO_TOT'/>" name="KT_DUNO_TOT" class="KT_DUNO_TOT number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TO_KHA'/>" name="KT_SO_TO_KHA" class="KT_SO_TO_KHA number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DUNO_KHA'/>" name="KT_DUNO_KHA" class="KT_DUNO_KHA number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TO_TB'/>" name="KT_SO_TO_TB" class="KT_SO_TO_TB number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DUNO_TB'/>" name="KT_DUNO_TB" class="KT_DUNO_TB number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TO_KEM'/>" name="KT_SO_TO_KEM" class="KT_SO_TO_KEM number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DUNO_KEM'/>" name="KT_DUNO_KEM" class="KT_DUNO_KEM number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
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
