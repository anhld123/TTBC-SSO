<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 01/KTNB</title>
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
        
        <SCRIPT language="javascript">
            $(document).ready(function(){
                $("#update").click(function () {
                document.getElementById("update").disabled = true;                
                sleep(1000);
                document.getElementById("update").disabled = false; 
            });
                
                //Format cac cot thu 3 --> 6 can le ben phai
                $('#tableKtnb td:nth-child(3) input').css({"text-align": "right"});
                $('#tableKtnb td:nth-child(4) input').css({"text-align": "right"});
                $('#tableKtnb td:nth-child(5) input').css({"text-align": "right"});
                $('#tableKtnb td:nth-child(6) input').css({"text-align": "right"});
                
                //CSS truong so thu tu (cot 1) cho width
                $('#tableKtnb td:nth-child(1),#tableKtnb th:nth-child(1)').css({"width" : "50px"});
                
                //An di cac cot 8 --> 15
                $('#tableKtnb td:nth-child(8),#tableKtnb th:nth-child(8)').hide();
                $('#tableKtnb td:nth-child(9),#tableKtnb th:nth-child(9)').hide();
                $('#tableKtnb td:nth-child(10),#tableKtnb th:nth-child(10)').hide();
                $('#tableKtnb td:nth-child(11),#tableKtnb th:nth-child(11)').hide();
                $('#tableKtnb td:nth-child(12),#tableKtnb th:nth-child(12)').hide();
                $('#tableKtnb td:nth-child(13),#tableKtnb th:nth-child(13)').hide();
                $('#tableKtnb td:nth-child(14),#tableKtnb th:nth-child(14)').hide();
                $('#tableKtnb td:nth-child(15),#tableKtnb th:nth-child(15)').hide();
                 $('#tableKtnb td:nth-child(16),#tableKtnb th:nth-child(16)').hide();
                
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true,0);  
            });
            
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
            
            function addRow(indx) {
                var index = parseInt(indx); //CuongBM: ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("tableKtnb");
                var rowCount = table.rows.length; //Dem so dong cua bang
                var newRow = table.insertRow(index + 1);  //Them dong moi
                newRow.className = 'cscontent';    //Them class name cho row
                var colCount = table.rows[2].cells.length;  //Dem so cot cua bang
                for (var i = 0; i < colCount; i++) {
                    var newcell = newRow.insertCell(i); //Tao cell moi
                    newcell.className = 'NORMAL';   //Them class cho cell
                    newcell.innerHTML = table.rows[indx].cells[i].innerHTML; //Them du lieu data, gia tri nhu dong ben tren
                    
                    //Xu ly truong "STT"
                    if(i == 0){
                        newcell.innerHTML = '<input type="text" value="" name="KT_STT_HT" />';
                    }
                    
                    //Xu ly truong "Cac doan kiem tra"
                    if(i == 1){
                        newcell.innerHTML = '<input type="text" value="" name="KT_DKT" />';
                    }

                    //Them sua xoa
                    if(i == 6){
                        newcell.innerHTML = '<input type="button" onclick="addRow(this.parentNode.parentNode.rowIndex)" value="Them"/>' +
                                '<input type="button" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" value="Xoa"/>';                        
                    }
                    
                    //Thay đổi giá trị của trường "Xóa" = 'Y'
                    if(i == 10){
                        newcell.innerHTML = '<input type="text" value="Y" name="KT_XOA"/>';                        
                    }
                    
                    //Cac truong bang so --> se co so truong = 0
                    $('.number').number(true,0); 
                
                    //CuongBM: co the danh lai index truong "Số thứ tự", nhung h không cần thiết
                    //         xu ly van de nay tai lop action
                    // Cach lay gia tri alert($("#tableKtnb tr:eq(3) td:eq(11) input").val());
                }
                
                //An cac cot bo xung
                $('#tableKtnb td:nth-child(8),#tableKtnb th:nth-child(8)').hide();
                $('#tableKtnb td:nth-child(9),#tableKtnb th:nth-child(9)').hide();
                $('#tableKtnb td:nth-child(10),#tableKtnb th:nth-child(10)').hide();
                $('#tableKtnb td:nth-child(11),#tableKtnb th:nth-child(11)').hide();
                $('#tableKtnb td:nth-child(12),#tableKtnb th:nth-child(12)').hide();
                $('#tableKtnb td:nth-child(13),#tableKtnb th:nth-child(14)').hide();
                $('#tableKtnb td:nth-child(14),#tableKtnb th:nth-child(14)').hide();
                $('#tableKtnb td:nth-child(15),#tableKtnb th:nth-child(15)').hide();
                $('#tableKtnb td:nth-child(16),#tableKtnb th:nth-child(16)').hide();
            }

            function deleteRow(indx) {
                var table = document.getElementById("tableKtnb");
                table.deleteRow(indx);       
            }
            
            //Xu ly tinh tong cho tung dong
            function evaluateSum(input){                
                //Lap vong for voi cac gia tri
                //Ngon: nhu vay la da lap duoc va lay ra duoc cac gia tri
//                $(".KT_STT_HT").each(function(index){
//                    //Chuan khong can chinh
////                    console.log("intdex: " + index + "Gia tri: " + $(this).val());
//                });                
                
                var arrCot = [".KT_SLT", ".KT_SLH", ".KT_SL_DGD", ".KT_SL_TKVV"]; //Luu cac cot cua du lieu can tinh toan
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "HĐQT, BĐD HĐQT"
                    $(arrCot[i]).eq(0).val(parseInt($(arrCot[i]).eq(1).val()) + parseInt($(arrCot[i]).eq(2).val()) +
                            parseInt($(arrCot[i]).eq(3).val()));
                    //Tinh tong cho dong "NHCSXH"
                    $(arrCot[i]).eq(4).val(parseInt($(arrCot[i]).eq(5).val()) + parseInt($(arrCot[i]).eq(6).val()) +
                            parseInt($(arrCot[i]).eq(7).val()));
                    //Tinh tong cho dong "Hội Phụ nữ"
                    $(arrCot[i]).eq(9).val(parseInt($(arrCot[i]).eq(10).val()) + parseInt($(arrCot[i]).eq(11).val()));
                    //Tinh tong cho dong "Hội Nông dân"
                    $(arrCot[i]).eq(12).val(parseInt($(arrCot[i]).eq(13).val()) + parseInt($(arrCot[i]).eq(14).val()));
                    //Tinh tong cho dong "Đoàn Thanh niên"
                    $(arrCot[i]).eq(15).val(parseInt($(arrCot[i]).eq(16).val()) + parseInt($(arrCot[i]).eq(17).val()));
                    //Tinh tong cho dong "Hội Cựu chiến binh"
                    $(arrCot[i]).eq(18).val(parseInt($(arrCot[i]).eq(19).val()) + parseInt($(arrCot[i]).eq(20).val()));
                    //Tinh tong cho dong "Tổ chức chính trị XH"
                    $(arrCot[i]).eq(8).val(parseInt($(arrCot[i]).eq(9).val()) + parseInt($(arrCot[i]).eq(12).val()) +
                            parseInt($(arrCot[i]).eq(15).val()) + parseInt($(arrCot[i]).eq(18).val()));
                    //Tinh tong cho dong "Các ngành khác"
                    $(arrCot[i]).eq(21).val(parseInt($(arrCot[i]).eq(22).val()) + parseInt($(arrCot[i]).eq(23).val()));
                }
                
                $(input).css({"border":"1px"});
                //CSS lai bober
//                $(input).css("border":"1px");
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
            
            function fnResetVal(){
               $(".KT_SLT").val('0');
               $(".KT_SLH").val('0');
               $(".KT_SL_DGD").val('0');
               $(".KT_SL_TKVV").val('0');
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
        </SCRIPT>
    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdata" id="frmdata" action="save_data_ktnb01.action" theme="simple">
            <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                <tr>
                    <td colspan="2" style="font-size: 14px;">01/KTNB: Báo cáo thống kê số lượng kiểm tra giám sát<hr></td>                    
                </tr>
                <tr>
                    <td width="70%" >
                        <b>Phòng giao dịch1: </b><input type="text" name="posCD" id="posCD" value="<s:property value="posCD"/>" readonly="readonly"/>
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
                        <!--<input type="button" id="checkThenSubmit" value="Xem lại" onclick="fnCheckViewSubmit()" style="width:122px;height:25px;color: red;"/>-->
                        <%--<sj:submit targets="result" value="Xem lại" name="review" id="review"  cssStyle="display: none;"/>--%>
                        <s:url id="idurlReset1" action="ResetDataInput1.action"></s:url>
                            <sj:submit id="idReset1" name="nameReset1" href="%{idurlReset1}" 
                                       value="Reset" style="width:122px;height:25px;color: red;"
                                       targets="result"
                                       formIds="frmdata"
                                       onclick="fnResetVal()"
                                       />
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                        <table border="1px" id="tableKtnb">
                            <tr class="tbhead">
                                <th>STT</th>
                                <th>Các đoàn kiểm tra</th>
                                <th>Số lượt tỉnh</th>
                                <th>Số lượt huyện</th>
                                <th>Số lượt điểm giao dịch</th>
                                <th>Số lượt tổ TK&VV</th>
                                
                                <th>Chức năng</th>                                
                                <th>Được nhập</th>
                                <th>Cố định</th>
                                <th>Thêm</th>
                                <th>Xóa</th>
                                <th>Font</th>
                                <th>Cấp hiển thị</th>
                                <th>Số thứ tự</th>
                                <th>Ngày cập nhật</th>
                                <th>Khóa</th>
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
                            </tr>
                            
                            <s:iterator value="ktnb01ModelList">
                                <tr class="cscontent">
                                    <!-- CuongBM: Nếu KT_DN: Được nhập = N, người dùng không được phép nhập, thay đổi -->
                                    <s:if test="KT_DN.equalsIgnoreCase('N')">                                     
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT_HT'/>" name="KT_STT_HT" onfocus="this.select()" readonly="readonly"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DKT'/>" name="KT_DKT" onfocus="this.select()" readonly="readonly"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SLT'/>" name="KT_SLT" class="KT_SLT number" onfocus="this.select()" readonly="readonly"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SLH'/>" name="KT_SLH" class="KT_SLH number" onfocus="this.select()" readonly="readonly"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SL_DGD'/>" name="KT_SL_DGD" class="KT_SL_DGD number" onfocus="this.select()" readonly="readonly"/></td>                                    
                                        <td class="<s:property value='KT_FONTWEIGHT'/>" ><input type="text" value="<s:property value='KT_SL_TKVV'/>" name="KT_SL_TKVV" class="KT_SL_TKVV number" onfocus="this.select()" readonly="readonly"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"></td>
                                        
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DN'/>" name="KT_DN"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_CO_DINH'/>" name="KT_CO_DINH"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_THEM'/>" name="KT_THEM"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_XOA'/>" name="KT_XOA"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_FONTWEIGHT'/>" name="KT_FONTWEIGHT"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_CAPHT'/>" name="KT_CAPHT"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT'/>" name="KT_STT"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='NG_CAPNHAT'/>" name="NG_CAPNHAT"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KHOA'/>" name="KT_KHOA"/></td>
                                    </s:if>
                                    
                                    <!-- CuongBM: Nếu KT_DN: Được nhập = Y -->
                                    <s:if test="KT_DN.equalsIgnoreCase('Y')">                                     
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT_HT'/>" name="KT_STT_HT" onfocus="this.select()" readonly="readonly"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DKT'/>" name="KT_DKT" onfocus="this.select()" readonly="readonly"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SLT'/>" name="KT_SLT" class="KT_SLT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SLH'/>" name="KT_SLH" class="KT_SLH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0};evaluateSum(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SL_DGD'/>" name="KT_SL_DGD" class="KT_SL_DGD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0};evaluateSum(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>                                    
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SL_TKVV'/>" name="KT_SL_TKVV" class="KT_SL_TKVV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0};evaluateSum(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                        
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
                                        
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DN'/>" name="KT_DN"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_CO_DINH'/>" name="KT_CO_DINH"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_THEM'/>" name="KT_THEM"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_XOA'/>" name="KT_XOA"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_FONTWEIGHT'/>" name="KT_FONTWEIGHT"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_CAPHT'/>" name="KT_CAPHT"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT'/>" name="KT_STT"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='NG_CAPNHAT'/>" name="NG_CAPNHAT"/></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KHOA'/>" name="KT_KHOA"/></td>
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
