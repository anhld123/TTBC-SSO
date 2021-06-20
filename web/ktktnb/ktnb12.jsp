<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 05/KNTC</title>
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
                //1=2+3
//                $(".KT_DKN_TS").eq(0).val(parseFloat($(".KT_DKN_TD_TK").eq(0).val()) + 
//                        parseFloat($(".KT_DKN_TD_KT").eq(0).val()));
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
               $(".KT_SO_VB_NEW").val('0');
               $(".KT_SO_VB_BS").val('0');
               $(".KT_SO_LOP_TH").val('0');
               $(".KT_SO_NG_TH").val('0');
               
               $(".KT_SO_CUOC").val('0');
               $(".KT_SO_DV").val('0');
               $(".KT_SO_DV_VP").val('0');
               $(".KT_SO_TOCHUC1").val('');
               
               $(".KT_SO_CANHAN1").val('0');
               $(".KT_SO_TOCHUC2").val('0');
               $(".KT_SO_CANHAN2").val('0');
               $(".KT_TONG_KLTT").val('0');
               
               
                $(".KT_SO_TOCHUC3").val('0');
               $(".KT_SO_CANHAN3").val('0');
               $(".KT_SO_TOCHUC4").val('0');
               $(".KT_SO_CANHAN4").val('0');
               
               
               $(".KT_GHICHU").val('');

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
            <s:form name="frmdata" id="frmdata" action="save_data_ktnb12.action" theme="simple">
            <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                <tr>
                    <td colspan="3" style="font-size: 14px;">05/KNTC: Công tác quản lý nhà nước về khiếu nại tố cáo<hr></td>                    
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
                     <td align="right" style="width:9%">    
                        <s:url id="idurlReset12" action="ResetDataInput12.action"></s:url>
                            <sj:submit id="idReset12" name="nameReset12" href="%{idurlReset12}" 
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
                                <th rowspan="2" colspan="2">Ban hành văn bản quản lý, chỉ đạo về công tác KNTC</th>
                                <th rowspan="2" colspan="2">Tập huấn, tuyên truyền, giáo dục pháp luật về KNTC cho cán bộ, công chức, viên chức, nhân dân</th>
                                <th colspan="7">Thanh tra kiểm tra trách nhiệm</th>
                                <th colspan="5">Kiểm tra việc thực hiện kết luận thanh tra trách nhiệm, quyết định xử lý</th>
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
                                <th colspan="2">Thực hiện pháp luật về KNTC</th>
                                <th rowspan="3">Số đơn vị vi phạm</th>
                                <th colspan="4">Kiến nghị xử lý</th>
                                <th rowspan="3">Tổng số KLTT và QĐ xử lý đã kiểm tra</th>
                                <th colspan="4">Kết quả kiểm tra</th>
                            </tr>
                            <tr class="tbhead">
                                
                                <th rowspan="2">Số văn bản ban hành mới</th>
                                <th rowspan="2">Số văn bản ban được sửa đổi bổ sung</th>
                                <th colspan="2">Pháp luật về KNTC</th>
                                
                                <th rowspan="2">Số cuộc</th>
                                <th rowspan="2">Số đơn vị</th>
                                <th colspan="2">Kiểm điểm, rút kinh nghiệm</th>
                                <th colspan="2">Hành chính</th>
                                <th colspan="2">Đã kiểm điểm, rút kinh nghiệm</th>
                                <th colspan="2">Đã xử lý hành chính</th>
                            </tr>
                            <tr class="tbhead"> 
                                <th>Lớp</th>
                                <th>Người</th>
                                <th>Tổ chức1</th>
                                <th>Cá nhân</th>
                                <th>Tổ chức2</th>
                                <th>Cá nhân</th>
                                <th>Tổ chức3</th>
                                <th>Cá nhân</th>
                                <th>Tổ chức4</th>
                                <th>Cá nhân</th>
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
                                <th class="hideColumn">(19)</th>
                                <th class="hideColumn">(20)</th>
                                <th class="hideColumn">(21)</th>
                                <th class="hideColumn">(22)</th>
                                <th class="hideColumn">(23)</th>
                                <th class="hideColumn">(24)</th>
                                <th class="hideColumn">(25)</th>
                                <th class="hideColumn">(26)</th>
                            </tr>
                            
                            
                            <s:iterator value="ktnb12ModelList">
                                <tr class="cscontent">
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DV'/>" name="KT_DV" class="KT_DV" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_VB_NEW'/>" name="KT_SO_VB_NEW" class="KT_SO_VB_NEW number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_VB_BS'/>" name="KT_SO_VB_BS" class="KT_SO_VB_BS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_LOP_TH'/>" name="KT_SO_LOP_TH" class="KT_SO_LOP_TH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_NG_TH'/>" name="KT_SO_NG_TH" class="KT_SO_NG_TH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_CUOC'/>" name="KT_SO_CUOC" class="KT_SO_CUOC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_DV'/>" name="KT_SO_DV" class="KT_SO_DV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_DV_VP'/>" name="KT_SO_DV_VP" class="KT_SO_DV_VP number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TOCHUC1'/>" name="KT_SO_TOCHUC1" class="KT_SO_TOCHUC1 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_CANHAN1'/>" name="KT_SO_CANHAN1" class="KT_SO_CANHAN1 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TOCHUC2'/>" name="KT_SO_TOCHUC2" class="KT_SO_TOCHUC2 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_CANHAN2'/>" name="KT_SO_CANHAN2" class="KT_SO_CANHAN2 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TONG_KLTT'/>" name="KT_TONG_KLTT" class="KT_TONG_KLTT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TOCHUC3'/>" name="KT_SO_TOCHUC3" class="KT_SO_TOCHUC3 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_CANHAN3'/>" name="KT_SO_CANHAN3" class="KT_SO_CANHAN3 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TOCHUC4'/>" name="KT_SO_TOCHUC4" class="KT_SO_TOCHUC4 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_CANHAN4'/>" name="KT_SO_CANHAN4" class="KT_SO_CANHAN4 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
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
