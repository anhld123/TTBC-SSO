<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 03/KTNB</title>
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
//                $('#tableKtnb td:nth-child(1),#tableKtnb th:nth-child(1)').css({"width" : "50px"});
                
                //An di cac cot chuc nang
                $('.hideColumn').hide();
                
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true,0);  
                
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true,2);  
            });
            
            //Xu ly tinh tong cho tung dong
            function autoEvaluate(){
                //Tinh toan cho 2 dong
                for(var i=0; i<2; i++){
                    //7=5:3
                    $(".KT_TLDC_SHVV").eq(i).val($(".KT_SDDC_SHVV").eq(i).val()/$(".KT_SDCC_SHVV").eq(i).val()*100);

                    //8=6:4
                    $(".KT_TLDC_ST").eq(i).val($(".KT_SDDC_ST").eq(i).val()/$(".KT_SDCC_ST").eq(i).val()*100);

                    //10=9:5
                    //$(".KT_SSDC_ST").eq(i).val($(".KT_SSDC_SKH").eq(i).val()/$(".KT_SDDC_SHVV").eq(i).val());

                    //11=10:6
                    $(".KT_SSDC_TLST").eq(i).val($(".KT_SSDC_ST").eq(i).val()/$(".KT_SDDC_ST").eq(i).val()*100);
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
//               alert('vao doClick');
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
               $(".KT_SDDC_SHVV").val('0');
               $(".KT_SDDC_ST").val('0');
               $(".KT_TLDC_SHVV").val('0');
               $(".KT_TLDC_ST").val('0');
               
               $(".KT_SSDC_SKH").val('0');
               $(".KT_SSDC_ST").val('0');
               $(".KT_SSDC_TLST").val('0');
               $(".KT_SSDC_GC").val('');
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
            <s:form name="frmdata" id="frmdata" action="save_data_ktnb03.action" theme="simple">
            <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                <tr>
                    <td colspan="3" style="font-size: 14px;">03/KTNB: Báo cáo kết quả đối chiếu công khai<hr></td>                    
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
                        <s:url id="idurlReset3" action="ResetDataInput3.action"></s:url>
                            <sj:submit id="idReset3" name="nameReset3" href="%{idurlReset3}" 
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
                                <th rowspan="2">Số TT</th>
                                <th rowspan="2">Tiêu chí</th>
                                <th colspan="2">Số dư cuối kỳ báo cáo</th>
                                <th colspan="2">Số đã đối chiếu</th>
                                <th colspan="2">Tỷ lệ đối chiếu %</th>
                                <th colspan="3">Sai sót với số đối chiếu</th>
                                <th rowspan="2">Ghi chú</th>
                                
                                <th rowspan="2">Chức năng</th>                                
                                <th rowspan="2" class="hideColumn">Được nhập</th>
                                <th rowspan="2" class="hideColumn">Cố định</th>
                                <th rowspan="2" class="hideColumn">Thêm</th>
                                <th rowspan="2" class="hideColumn">Xóa</th>
                                <th rowspan="2" class="hideColumn">Font</th>
                                <th rowspan="2" class="hideColumn">Cấp hiển thị</th>
                                <th rowspan="2" class="hideColumn">Số thứ tự</th>
                                <th rowspan="2" class="hideColumn">Ngày cập nhật</th>
                                <th rowspan="2" class="hideColumn">Khóa</th>
                            </tr>
                            <tr class="tbhead">
                                <th>Số khách hàng</th>
                                <th>Số tiền</th>
                                <th>Số khách hàng</th>
                                <th>Số tiền</th>
                                <th>Số khách hàng</th>
                                <th>Số tiền</th>
                                <th>Số hộ vay vốn</th>
                                <th>Số tiền</th>
                                <th>Tỷ lệ (Số tiền)</th>
                            </tr>
                            
                            <tr class="tbhead">
                                <th>1</th>
                                <th>2</th>
                                <th>3</th>
                                <th>4</th>
                                <th>5</th>
                                <th>6</th>
                                <th>7=5:3</th>                                
                                <th>8=6:4</th>
                                <th>9</th>
                                <th>10</th>
                                <th>11=10:6</th>
                                <th>12</th>
                                
                                <th>13</th>
                                <th class="hideColumn">14</th>
                                <th class="hideColumn">15</th>
                                <th class="hideColumn">16</th>
                                <th class="hideColumn">17</th>
                                <th class="hideColumn">18</th>
                                <th class="hideColumn">19</th>
                                <th class="hideColumn">20</th>
                                <th class="hideColumn">21</th>
                                <th class="hideColumn">22</th>
                            </tr>
                            
                            <s:iterator value="ktnb03ModelList">
                                <tr class="cscontent">                                                                        
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT_HT'/>" name="KT_STT_HT" class="KT_STT_HT" onfocus="this.select()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TIEUCHI'/>" name="KT_TIEUCHI" class="KT_TIEUCHI" onfocus="this.select()" readonly="readonly"/></td>
                                    
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SDCC_SHVV'/>" name="KT_SDCC_SHVV" class="KT_SDCC_SHVV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SDCC_ST'/>" name="KT_SDCC_ST" class="KT_SDCC_ST number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SDDC_SHVV'/>" name="KT_SDDC_SHVV" class="KT_SDDC_SHVV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SDDC_ST'/>" name="KT_SDDC_ST" class="KT_SDDC_ST number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TLDC_SHVV'/>" name="KT_TLDC_SHVV" class="KT_TLDC_SHVV number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TLDC_ST'/>" name="KT_TLDC_ST" class="KT_TLDC_ST number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SSDC_SKH'/>" name="KT_SSDC_SKH" class="KT_SSDC_SKH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SSDC_ST'/>" name="KT_SSDC_ST" class="KT_SSDC_ST number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SSDC_TLST'/>" name="KT_SSDC_TLST" class="KT_SSDC_TLST number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SSDC_GC'/>" name="KT_SSDC_GC" class="KT_SSDC_GC" onfocus="this.select()"/></td>
                                    
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
