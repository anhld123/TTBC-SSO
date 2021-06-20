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
                $("#update").click(function () {
                document.getElementById("update").disabled = true;                
                sleep(1000);
                document.getElementById("update").disabled = false; 
            });
                //Format cac truong input.number can le phai
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".TD_GOC").css({"width": "50px"});
                $('.D0').css({"text-align": "center"});
                $(".TD_LAI").css({"width": "50px"});
                $(".TD_SOVU").css({"width": "30px"});
                $(".TD_STT").css({"width": "30px"});
                $(".TD_CHITEU").css({"width": "130px"});
                $(".TEN_KH").css({"width": "100%"});
                
                $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
                
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
                $('.number2').number(true,0);  
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
                                <th rowspan="3">STT</th>
                                <th rowspan="3" >Đối tượng</th>                 
                                <th colspan="4">Tồn đọng đầu năm</th>
                                <th colspan="4">Phát hiện trong kỳ</th>
                                <th colspan="4">Đã thu hồi</th>  
                                <th colspan="4">Tồn đọng cuối kỳ</th>  
                                  
<!--                                <th rowspan="3" class="hideColumn">Được nhập</th>
                                <th rowspan="3" class="hideColumn">Cố định</th>
                                <th rowspan="3" class="hideColumn">Thêm</th>
                                <th rowspan="3" class="hideColumn">Xóa</th>
                                <th rowspan="3" class="hideColumn">Font</th>
                                <th rowspan="3" class="hideColumn">Cấp hiển thị</th>
                                <th rowspan="3" class="hideColumn">Số thứ tự</th>
                                <th rowspan="3" class="hideColumn">Ngày cập nhật</th>
                                <th rowspan="3" class="hideColumn">Khóa</th>-->
                            </tr>
                            <tr class="tbhead">
                                <th rowspan="2" class="TD_SOVU">Số vụ</th>
                                <th colspan="3" >Số tiền</th>
                                <th rowspan="2" class="TD_SOVU">Số vụ</th>
                                <th colspan="3">Số tiền</th>
                                <th rowspan="2" class="TD_SOVU">Số vụ</th>
                                <th colspan="3">Số tiền</th>
                                <th rowspan="2" class="TD_SOVU">Số vụ</th>
                                <th colspan="3">Số tiền</th>
                            </tr>
                            <tr class="tbhead">                                
                                <th class="TD_GOC">Gốc</th>
                                <th class="TD_LAI">Lãi</th>
                                <th class="TD_LAI">T.Gửi</th>
                                <th class="TD_GOC">Gốc</th>
                                <th class="TD_LAI">Lãi</th>                               
                                <th class="TD_LAI">T.Gửi</th>
                                <th class="TD_GOC">Gốc</th>
                                <th class="TD_LAI">Lãi</th>
                                <th class="TD_LAI">T.Gửi</th>
                                <th class="TD_GOC">Gốc</th>
                                <th class="TD_LAI">Lãi</th>
                                <th class="TD_LAI">T.Gửi</th>
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
                                
<!--                                <th class="hideColumn">(20)</th>
                                <th class="hideColumn">(21)</th>
                                <th class="hideColumn">(22)</th>
                                <th class="hideColumn">(23)</th>
                                <th class="hideColumn">(24)</th>
                                <th class="hideColumn">(25)</th>
                                <th class="hideColumn">(26)</th>
                                <th class="hideColumn">(27)</th>
                                <th class="hideColumn">(28)</th>-->
                            </tr>
                            
                            <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                                <tr height="22">    
                                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">                                        
                                            <td align = "right" class="TD_STT">
                                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"/>
                                                <input type="hidden" value="<s:property  value="THUTU" />"
                                                 name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>  
                                                <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                                 name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/> 
                                            </td>
                                            <td align = "right" class="TD_CHITIEU">
                                                <input type="text" value="<s:property  value="TEN" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"/>
                                            </td>                                                                          

                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D3" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"/>
                                                </td>                                                                                                

                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D4" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"/>
                                               </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D5" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"/>
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D6" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td>
                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D7" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"/>
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D8" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D9" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"/>
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D10" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td>  
                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D11" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D12" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D12 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D13" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D14" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D15" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td>    
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D16" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D16 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td>   
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D17" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="D17 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td>   
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D18" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="D18 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td>   
                                        
                                    </s:if>   
                                    <s:else>
                                            <td align = "right" class="TD_STT">
                                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "/>
                                                <input type="hidden" value="<s:property  value="THUTU" />"
                                                 name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>  
                                                <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                                 name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>  
                                            
                                            </td>
                                            <td align = "right" class="TD_CHITIEU">
                                                <input type="text" value="<s:property  value="TEN" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "/>
                                            </td>                              

                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D3" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "/>
                                                </td>                                                                                                

                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D4" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "/>
                                                <input type="hidden" value="<s:property  value="MA" />"
                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D5" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "/>
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D6" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "
                                                       />
                                            </td>
                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D7" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "/>
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D8" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "
                                                       />
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D9" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "/>
                                            </td>
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D10" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "
                                                       />
                                            </td>  
                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D11" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D12" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D12 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D13" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D14" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; "
                                                       />
                                            </td> 
                                            <td align = "right" class="TD_SOVU">
                                                <input type="text" value="<s:property  value="D15" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 number TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td>    
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D16" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D16 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td>   
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D17" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="D17 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td>   
                                            <td align = "right" class="TD_LAI">
                                                <input type="text" value="<s:property  value="D18" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="D18 number2 TEN_KH" onfocus="this.select()"
                                                       onblur="if(this.value == '') { this.value=0}; " readonly="readonly"
                                                       />
                                            </td>                          
                                    </s:else>    
                                </tr>
                    
                </s:iterator>
                        </table> 
    </div>      
</div>