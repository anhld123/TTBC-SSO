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
        
      
    <div id="contentDiv">                     
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
                                    
                                </tr>
                            </s:iterator>
                        </table>    
</div>