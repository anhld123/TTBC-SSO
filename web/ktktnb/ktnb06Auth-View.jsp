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
                                <th rowspan="3">STT</th>
                                <th rowspan="3">Đối tượng</th>                 
                                <th colspan="4">Tồn đọng đầu năm</th>
                                <th colspan="4">Phát hiện trong kỳ</th>
                                <th colspan="4">Đã thu hồi</th>  
                                <th colspan="4">Tồn đọng cuối kỳ</th>  
                                
                              
                            </tr>
                            <tr class="tbhead">
                                <th rowspan="2">Số vụ</th>
                                <th colspan="3">Số tiền</th>
                                <th rowspan="2">Số vụ</th>
                                <th colspan="3">Số tiền</th>
                                <th rowspan="2">Số vụ</th>
                                <th colspan="3">Số tiền</th>
                                <th rowspan="2">Số vụ</th>
                                <th colspan="3">Số tiền</th>
                            </tr>
                            <tr class="tbhead">                                
                                <th>Gốc</th>
                                <th>Lãi</th>
                                <th>T.Kiệm</th>
                                <th>Gốc</th>
                                <th>Lãi</th>                               
                                <th>T.Kiệm</th>
                                <th>Gốc</th>
                                <th>Lãi</th>
                                <th>T.Kiệm</th>
                                <th>Gốc</th>
                                <th>Lãi</th>
                                <th>T.Kiệm</th>
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
                                <!--<th>(19)</th>-->
                                
                                
                            </tr>
                            
                            <s:iterator value="ktnb06ModelList">
                                <tr class="cscontent">     
                                    <!-- CuongBM: Nếu KT_DN: Được nhập = N, người dùng không được phép nhập, thay đổi -->
                                                                       
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT_HT'/>" name="KT_STT_HT" class="KT_STT_HT" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DT'/>" name="KT_DT" class="KT_DT" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDDN_SV'/>" name="KT_TDDN_SV" class="KT_TDDN_SV number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDDN_ST_G'/>" name="KT_TDDN_ST_G" class="KT_TDDN_ST_G number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDDN_ST_L'/>" name="KT_TDDN_ST_L" class="KT_TDDN_ST_L number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDDN_ST_TK'/>" name="KT_TDDN_ST_TK" class="KT_TDDN_ST_TK number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PHTK_SV'/>" name="KT_PHTK_SV" class="KT_PHTK_SV number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PHTK_ST_G'/>" name="KT_PHTK_ST_G" class="KT_PHTK_ST_G number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PHTK_ST_L'/>" name="KT_PHTK_ST_L" class="KT_PHTK_ST_L number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PHTK_ST_TK'/>" name="KT_PHTK_ST_TK" class="KT_PHTK_ST_TK number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DTH_SV'/>" name="KT_DTH_SV" class="KT_DTH_SV number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DTH_ST_G'/>" name="KT_DTH_ST_G" class="KT_DTH_ST_G number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DTH_ST_L'/>" name="KT_DTH_ST_L" class="KT_DTH_ST_L number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DTH_ST_TK'/>" name="KT_DTH_ST_TK" class="KT_DTH_ST_TK number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDCK_SV'/>" name="KT_TDCK_SV" class="KT_TDCK_SV number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDCK_ST_G'/>" name="KT_TDCK_ST_G" class="KT_TDCK_ST_G number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDCK_ST_L'/>" name="KT_TDCK_ST_L" class="KT_TDCK_ST_L number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TDCK_ST_TK'/>" name="KT_TDCK_ST_TK" class="KT_TDCK_ST_TK number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                    </tr>
                            </s:iterator>
                        </table>   
    </div>      
</div>