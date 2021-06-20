<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%--<sj:head jqueryui="false" jquerytheme="simple"/>
<s:head/>
<sj:head/>--%>
<!DOCTYPE html>

      
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
            
            .tdtest {
                width: 20%;
            }
        </style>
        
     <SCRIPT language="javascript">
         $(document).ready(function(){
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

//            function clk_glkhtd() {
//                var lstPos = "";
//                $('#treeView').jstree("get_checked", null, true).each(
//                        function() {
//                            lstPos = lstPos + this.id + ',';
//                        });
//                document.getElementById("selectedPos").value = lstPos;
//            }
            
            //Xu ly tinh tong cho tung dong
          
            
            //Check xem du lieu da ok chua
            //Neu ok roi thi goi su kien submit du lieu

           
            
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
        
      
    <div id="TEST">
    <div id="valueview_div" class="BalanceSheetStyle" >                        
        <table border="1px" id="tableKtnb">
                            <tr class="tbhead">
                                <th rowspan="3">Số TT</th>
                                <th rowspan="3">Đối tượng vay vốn</th>
                                <th rowspan="2" colspan="2">Tổng số hồ sơ đến cuối kỳ báo cáo</th>
                                <th colspan="4">Số hồ sơ kiểm tra</th>
                                <th colspan="4">Trong đó</th>
                                
                                <th rowspan="3" class="hideColumn">Hide NQH1</th>
                                <th rowspan="3" class="hideColumn">Hide NQH2</th>
                                <th rowspan="3" class="hideColumn">Hide NQH3</th>
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
                                <th colspan="2">Tổng số</th>   
                                <th colspan="2">Tỷ lệ kiểm tra %</th>  
                                <th colspan="2">Hồ sơ sai</th>   
                                <th colspan="2">Tỷ lệ sai %</th>                                   
                            </tr>
                            <tr class="tbhead">
                                <th>Số hồ sơ</th>
                                <th>Số tiền</th>
                                <th>Số hồ sơ</th>
                                <th>Số tiền</th>
                                <th>Số hồ sơ</th>
                                <th>Số tiền</th>
                                <th>Số hồ sơ</th>
                                <th>Số tiền</th>
                                <th>Số hồ sơ</th>
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
                                <th class="hideColumn">(13)</th>
                                <th class="hideColumn">(14)</th>
                                <th class="hideColumn">(15)</th>
                                
                                <th>(13)</th>
                                <th class="hideColumn">(17)</th>
                                <th class="hideColumn">(18)</th>
                                <th class="hideColumn">(19)</th>
                                <th class="hideColumn">(20)</th>
                                <th class="hideColumn">(21)</th>
                                <th class="hideColumn">(22)</th>
                                <th class="hideColumn">(23)</th>
                                <th class="hideColumn">(24)</th>
                                <th class="hideColumn">(25)</th>
                            </tr>
                            
                            <s:iterator value="ktnb02ModelList">
                                <tr class="cscontent">                                                                        
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT_HT'/>" name="KT_STT_HT" class="KT_STT_HT" onfocus="this.select()" readonly="readonly" onblur="autoEvaluate()"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DTVV'/>" name="KT_DTVV" class="KT_DTVV" onfocus="this.select()" readonly="readonly" onblur="autoEvaluate()"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_HS_THS_SHS'/>" name="KT_HS_THS_SHS" class="KT_HS_THS_SHS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_HS_THS_ST'/>" name="KT_HS_THS_ST" class="KT_HS_THS_ST number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_HS_KT_TS_SHS'/>" name="KT_HS_KT_TS_SHS" class="KT_HS_KT_TS_SHS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_HS_KT_TS_ST'/>" name="KT_HS_KT_TS_ST" class="KT_HS_KT_TS_ST number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_HS_KT_TL_SHS'/>" name="KT_HS_KT_TL_SHS" class="KT_HS_KT_TL_SHS number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_HS_KT_TL_ST'/>" name="KT_HS_KT_TL_ST" class="KT_HS_KT_TL_ST number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_HS_HSS_SHS'/>" name="KT_HS_HSS_SHS" class="KT_HS_HSS_SHS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_HS_HSS_ST'/>" name="KT_HS_HSS_ST" class="KT_HS_HSS_ST number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_HS_TLS_SHS'/>" name="KT_HS_TLS_SHS" class="KT_HS_TLS_SHS number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_HS_TLS_ST'/>" name="KT_HS_TLS_ST" class="KT_HS_TLS_ST number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_NQH_CC_SHS'/>" name="KT_NQH_CC_SHS" class="KT_NQH_CC_SHS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_NQH_CC_ST'/>" name="KT_NQH_CC_ST" class="KT_NQH_CC_ST number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_NQH_CD'/>" name="KT_NQH_CD" class="KT_NQH_CD number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)"/></td>

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
    </div>      
</div>