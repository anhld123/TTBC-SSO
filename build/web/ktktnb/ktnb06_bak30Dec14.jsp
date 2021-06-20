<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 06/KTNB</title>
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
                //Format cac truong input.number can le phai
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                
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
                $('.number2').number(true,2);  
            });
            
            //Xu ly tinh tong cho tung dong
            function autoEvaluate(){
                var arrCot = [".KT_TDDN_SV",".KT_TDDN_ST_G",
                    ".KT_TDDN_ST_L",".KT_PHTK_SV",".KT_PHTK_ST_G",".KT_PHTK_ST_L",
                    ".KT_DTH_SV",".KT_DTH_ST_G",".KT_DTH_ST_L",".KT_TDCK_SV",".KT_TDCK_ST_G",".KT_TDCK_ST_L"]; //Luu cac cot cua du lieu can tinh toan
                
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Cán bộ hội"
                    $(arrCot[i]).eq(1).val(parseFloat($(arrCot[i]).eq(2).val()) + parseFloat($(arrCot[i]).eq(3).val()) + 
                            parseFloat($(arrCot[i]).eq(4).val()) +
                            parseFloat($(arrCot[i]).eq(5).val()) + parseFloat($(arrCot[i]).eq(6).val()));
                    
                    //Tinh tong cho dong "Tổ trưởng TK&VV "
                    $(arrCot[i]).eq(7).val(parseFloat($(arrCot[i]).eq(8).val()) + parseFloat($(arrCot[i]).eq(9).val()) + 
                            parseFloat($(arrCot[i]).eq(10).val()) +
                            parseFloat($(arrCot[i]).eq(11).val()) + parseFloat($(arrCot[i]).eq(12).val()));
                }
            }
            
            //Check xem du lieu da ok chua
            //Neu ok roi thi goi su kien submit du lieu
            function fnCheckThenSubmit(){
                if(validateRequiredFields()){
                    $("#update").trigger('click');
                }
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
            <s:form name="frmdata" id="frmdata" action="save_data_ktnb06.action" theme="simple">
            <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                <tr>
                    <td colspan="2" style="font-size: 14px;">06/KTNB: Báo cáo các vụ việc sai phạm do chiếm dụng hoặc vay ké và kết quả xử lý<hr></td>                    
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
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                        <table border="1px" id="tableKtnb">
                            <tr class="tbhead">
                                <th rowspan="3">STT</th>
                                <th rowspan="3">Đối tượng</th>                 
                                <th colspan="3">Tồn đọng đầu năm</th>
                                <th colspan="3">Phát hiện trong kỳ</th>
                                <th colspan="3">Đã thu hồi</th>  
                                <th colspan="3">Tồn đọng cuối kỳ</th>  
                                <th rowspan="3">Ghi chú</th>   
                                
                                <th rowspan="3">Chức năng</th>   
                                <th rowspan="3" class="hideColumn">Được nhập</th>
                                <th rowspan="3" class="hideColumn">Cố định</th>
                                <th rowspan="3" class="hideColumn">Thêm</th>
                                <th rowspan="3" class="hideColumn">Xóa</th>
                                <th rowspan="3" class="hideColumn">Font</th>
                                <th rowspan="3" class="hideColumn">Cấp hiển thị</th>
                                <th rowspan="3" class="hideColumn">Số thứ tự</th>
                                <th rowspan="3" class="hideColumn">Ngày cập nhật</th>
                            </tr>
                            <tr class="tbhead">
                                <th rowspan="2">Số vụ</th>
                                <th colspan="2">Số tiền</th>
                                <th rowspan="2">Số vụ</th>
                                <th colspan="2">Số tiền</th>
                                <th rowspan="2">Số vụ</th>
                                <th colspan="2">Số tiền</th>
                                <th rowspan="2">Số vụ</th>
                                <th colspan="2">Số tiền</th>
                            </tr>
                            <tr class="tbhead">                                
                                <th>Gốc</th>
                                <th>Lãi</th>
                                <th>Gốc</th>
                                <th>Lãi</th>
                                <th>Gốc</th>
                                <th>Lãi</th>
                                <th>Gốc</th>
                                <th>Lãi</th>
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
                                <th class="hideColumn">(17)</th>
                                <th class="hideColumn">(18)</th>
                                <th class="hideColumn">(19)</th>
                                <th class="hideColumn">(20)</th>
                                <th class="hideColumn">(21)</th>
                                <th class="hideColumn">(22)</th>
                                <th class="hideColumn">(23)</th>
                                <th class="hideColumn">(24)</th>
                            </tr>
                            
                            <s:iterator value="ktnb06ModelList">
                                <tr class="cscontent">     
                                    <!-- CuongBM: Nếu KT_DN: Được nhập = N, người dùng không được phép nhập, thay đổi -->
                                    <s:if test="KT_DN.equalsIgnoreCase('N')">                                     
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT_HT'/>" name="KT_STT_HT" class="KT_STT_HT" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DT'/>" name="KT_DT" class="KT_DT" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDDN_SV'/>" name="KT_TDDN_SV" class="KT_TDDN_SV number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDDN_ST_G'/>" name="KT_TDDN_ST_G" class="KT_TDDN_ST_G number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDDN_ST_L'/>" name="KT_TDDN_ST_L" class="KT_TDDN_ST_L number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PHTK_SV'/>" name="KT_PHTK_SV" class="KT_PHTK_SV number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PHTK_ST_G'/>" name="KT_PHTK_ST_G" class="KT_PHTK_ST_G number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PHTK_ST_L'/>" name="KT_PHTK_ST_L" class="KT_PHTK_ST_L number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DTH_SV'/>" name="KT_DTH_SV" class="KT_DTH_SV number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DTH_ST_G'/>" name="KT_DTH_ST_G" class="KT_DTH_ST_G number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DTH_ST_L'/>" name="KT_DTH_ST_L" class="KT_DTH_ST_L number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDCK_SV'/>" name="KT_TDCK_SV" class="KT_TDCK_SV number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDCK_ST_G'/>" name="KT_TDCK_ST_G" class="KT_TDCK_ST_G number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDCK_ST_L'/>" name="KT_TDCK_ST_L" class="KT_TDCK_ST_L number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_GHICHU'/>" name="KT_GHICHU" class="KT_GHICHU" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                    </s:if>
                                        
                                    <!-- CuongBM: Nếu KT_DN: Được nhập = Y, người dùng được phép nhập, thay đổi -->
                                    <s:if test="KT_DN.equalsIgnoreCase('Y')">                                     
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT_HT'/>" name="KT_STT_HT" class="KT_STT_HT" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DT'/>" name="KT_DT" class="KT_DT" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDDN_SV'/>" name="KT_TDDN_SV" class="KT_TDDN_SV number" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDDN_ST_G'/>" name="KT_TDDN_ST_G" class="KT_TDDN_ST_G number2" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDDN_ST_L'/>" name="KT_TDDN_ST_L" class="KT_TDDN_ST_L number2" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PHTK_SV'/>" name="KT_PHTK_SV" class="KT_PHTK_SV number" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PHTK_ST_G'/>" name="KT_PHTK_ST_G" class="KT_PHTK_ST_G number2" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PHTK_ST_L'/>" name="KT_PHTK_ST_L" class="KT_PHTK_ST_L number2" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DTH_SV'/>" name="KT_DTH_SV" class="KT_DTH_SV number" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DTH_ST_G'/>" name="KT_DTH_ST_G" class="KT_DTH_ST_G number2" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DTH_ST_L'/>" name="KT_DTH_ST_L" class="KT_DTH_ST_L number2" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDCK_SV'/>" name="KT_TDCK_SV" class="KT_TDCK_SV number" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDCK_ST_G'/>" name="KT_TDCK_ST_G" class="KT_TDCK_ST_G number2" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDCK_ST_L'/>" name="KT_TDCK_ST_L" class="KT_TDCK_ST_L number2" onfocus="this.select()" onblur="autoEvaluate()" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_GHICHU'/>" name="KT_GHICHU" class="KT_GHICHU" onfocus="this.select()" onblur="autoEvaluate()"/></td>
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
                                </tr>
                            </s:iterator>
                        </table>
                </tr>
            </table>
                    
            </s:form>
        </div>
    </body>
</html>
