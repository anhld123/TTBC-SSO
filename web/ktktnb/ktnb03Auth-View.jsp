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
                                <th rowspan="2">Số TT</th>
                                <th rowspan="2">Tiêu chí</th>
                                <th colspan="2">Số dư cuối kỳ báo cáo</th>
                                <th colspan="2">Số đã đối chiếu</th>
                                <th colspan="2">Tỷ lệ đối chiếu %</th>
                                <th colspan="3">Sai sót với số đối chiếu</th>
                                <th rowspan="2">Ghi chú</th>
                                
                                
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
                                
                                
                            </tr>
                            
                            <s:iterator value="ktnb03ModelList">
                                <tr class="cscontent">                                                                        
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_STT_HT'/>" name="KT_STT_HT" class="KT_STT_HT" onfocus="this.select()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TIEUCHI'/>" name="KT_TIEUCHI" class="KT_TIEUCHI" onfocus="this.select()" readonly="readonly"/></td>
                                    
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SDCC_SHVV'/>" name="KT_SDCC_SHVV" class="KT_SDCC_SHVV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SDCC_ST'/>" name="KT_SDCC_ST" class="KT_SDCC_ST number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SDDC_SHVV'/>" name="KT_SDDC_SHVV" class="KT_SDDC_SHVV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SDDC_ST'/>" name="KT_SDDC_ST" class="KT_SDDC_ST number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TLDC_SHVV'/>" name="KT_TLDC_SHVV" class="KT_TLDC_SHVV number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TLDC_ST'/>" name="KT_TLDC_ST" class="KT_TLDC_ST number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SSDC_SKH'/>" name="KT_SSDC_SKH" class="KT_SSDC_SKH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SSDC_ST'/>" name="KT_SSDC_ST" class="KT_SSDC_ST number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; evaluateSum(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SSDC_TLST'/>" name="KT_SSDC_TLST" class="KT_SSDC_TLST number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SSDC_GC'/>" name="KT_SSDC_GC" class="KT_SSDC_GC" onfocus="this.select()" readonly="readonly"/></td>
                                  
                                </tr>
                            </s:iterator>
                        </table> 
    </div>      
</div>