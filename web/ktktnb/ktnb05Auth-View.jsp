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
                                <th rowspan="3">Tổ chức nhận ủy thác cho vay</th>                 
                                <th rowspan="2" colspan="2">Tổng số</th>
                                <th rowspan="2" colspan="2">Tổ TK&VV đã được kiểm tra</th>
                                <th rowspan="1" colspan="8">Chất lượng hoạt động của tổ</th>                              

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
                                

                            </tr>
                            
                            <s:iterator value="ktnb05ModelList">
                                <tr class="cscontent">     
                                    <!-- CuongBM: Nếu KT_DN: Được nhập = N, người dùng không được phép nhập, thay đổi -->
                                                                    
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
                                        <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DUNO_KEM'/>" name="KT_DUNO_KEM" class="KT_DUNO_KEM number2" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly" /></td>
                                   
                             </tr>
                            </s:iterator>
                        </table>   
    </div>      
</div>