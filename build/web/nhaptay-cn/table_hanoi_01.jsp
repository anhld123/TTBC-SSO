<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <style>
        a {
            color: #0000FF;
        }
        .BOLD
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }
        </style>
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "110px"});
                $(".TD_CAPKT").css({"width": "100px"});
                $(".TD_SOTIEN").css({"width": "55px"});                                
                $(".TD_SOMON").css({"width": "35px"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        
                <script>
                function autoEvaluate(){
    //                alert('vao doClick');
                    var arrCot = [".D2",".D3",".D4",".D5",".D6",".D7",".D8",".D9",".D10",".D11",".D12",".D13",".D14",".D15"]; //Luu cac cot cua du lieu can tinh toan                    
    //                  Tinh toan cho 7 dong
                    for(var i=0; i<30; i++){                        
                        //8=2+4-6
                        $(".D15").eq(i).val(parseFloat($(".D2").eq(i).val()) + parseFloat($(".D3").eq(i).val()) +
                                            parseFloat($(".D4").eq(i).val()) + parseFloat($(".D5").eq(i).val()) +
                                            parseFloat($(".D6").eq(i).val()) + parseFloat($(".D7").eq(i).val()) +   
                                            parseFloat($(".D8").eq(i).val()) + parseFloat($(".D9").eq(i).val()) +
                                            parseFloat($(".D10").eq(i).val()) + parseFloat($(".D11").eq(i).val()) +
                                            parseFloat($(".D12").eq(i).val()) + parseFloat($(".D13").eq(i).val()) +  
                                            parseFloat($(".D14").eq(i).val())        
                        );

                    }
    //                              
                }
                </script>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">              
        <s:if test="Grade.equalsIgnoreCase('1')"> 
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                BÁO CÁO KẾT QuẢ KIỂM TRA BĐD LÀ CHỦ TỊCH UBND CẤP XÃ
            </div>
                &nbsp;
            <s:hidden name="khoa_nhaptaycn"/>
<!--            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>-->
            <table border="1" class="editDelete" id="tablems01" style="width: 90%" align="center">
                <tr height="35">
                    <th rowspan="2" class="TD_THUTU">TT</th>                    
                    <th rowspan="2" class="TD_CHITIEU">Ban Đại Diện (cấp xã)</th>                                            
                    <th rowspan="2" class="TD_SOTIEN">Số Thôn, Tổ dân phố</th>                        
                    <th rowspan="2" class="TD_SOTIEN">Số TV BĐD cấp xã</th>
                    <th rowspan="2" class="TD_SOTIEN">Số TV được kiện toàn trong tháng</th>
                    <th colspan="6" class="TD_SOTIEN">KẾT QUẢ KIỂM TRA TRONG THÁNG</th>
                </tr>
                <tr>
                    <th class="TD_SOMON">SỐ TV mới thực hiện kiểm tra</th>  
                    <th class="TD_SOTIEN">Tổng số TV Thực hiện kiêm tra</th>
                    <th class="TD_SOMON">Xã được kiểm tra</th>  
                    <th class="TD_SOTIEN">Số thôn được kiểm tra</th>
                    <th class="TD_SOMON">Số Tổ được kiểm tra</th>  
                    <th class="TD_SOTIEN">Số hộ được kiểm tra</th>                    
                </tr>
                            
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                        <td align ="center" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                            <input type="text" style="text-align:center" value="<s:property  value="THUTU" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" readonly/>
                        </td>                        
                        
                        <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_SOTIEN">
                            <input type="text" value="<s:property  value="TEN" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly/>
                            <input type="hidden" value="<s:property  value="D1" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="MA"/>"/> 
                        
                        </td>
                        <td  align="right" class="TD_SOMON">    
                            <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" />                                  
                        </td>                                                                                              
                        
                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" />                                  
                        </td> 
                        
                        <td  align="right" class="TD_SOMON">    
                            <input type="text" value="<s:property  value="D4" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" />                                  
                        </td> 
                        
                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D5" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" />                                  
                        </td> 
                        
                        <td  align="right" class="TD_SOMON">    
                            <input type="text" value="<s:property  value="D6" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" />                                  
                        </td> 
                        
                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D7" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" />                                  
                        </td> 
                        
                        <td  align="right" class="TD_SOMON">    
                            <input type="text" value="<s:property  value="D8" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" />                                  
                        </td> 
                        
                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D9" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" />                                  
                        </td> 
                        
                        <td  align="right" class="TD_SOMON">    
                            <input type="text" value="<s:property  value="D10" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" />                                  
                        </td> 
                                                
                                                                      
                    </tr>                    
                    
                </s:iterator>
            </table>
            <p></p>          
            
            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        
        </s:if>
        <s:if test="Grade.equalsIgnoreCase('2')">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                BIỂU TỔNG HỢP KẾT QUẢ CHO VAY TẠI VÙNG NÔNG THÔN MỚI
            </div>
                &nbsp;
            <s:hidden name="khoa_nhaptaycn"/>
<!--            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>-->
            <table border="1" class="editDelete" id="tablems01" style="width: 90%" align="center">
                <tr>
                    <th class="TD_THUTU">TT</th>
                    <th class="TD_CHITIEU">Đơn vị</th> 
                    <th class="TD_SOTIEN">Tổng số xã</th>                                                             
                    <th class="TD_SOTIEN">Số hộ thoát nghèo</th>   
                    <!--<th  class="TD_SOTIEN">Hiệu quả đầu tư KFW</th>-->   
                    <th  class="TD_SOTIEN">Số xã hoàn thành 19CT</th>      
                </tr>
                              
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                        <td align ="center" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                            <input type="text" style="text-align:center" value="<s:property  value="THUTU" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" readonly/>
                        </td>
                        
                        <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_CHITIEU">
                            <input type="text" value="<s:property  value="TEN" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly/>
                        </td>
                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D1" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"   
                                       readonly="readonly"/>                                  
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                        </td>                                               
                                                
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly/>
                        </td>                        
                        
<!--                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly/>
                        </td>                        -->
                        <td align = "center" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td>                                                                    
                    </tr>                    
                    
                </s:iterator>
            </table>
            <p></p>    
        </s:if>   
        <s:if test="Grade.equalsIgnoreCase('3')">
            <div id="divTitle">
                        TRẠNG THÁI GỬI SỐ LIỆU CỦA CHI NHÁNH
                    </div>
                    <p></p>
                    <table border="1" class="editDelete" id="tablepl01" align="center">
                        <tr>
<!--                            <th align = "center"  style="width: 30px;">
                                <s:checkbox id ="allCheck" name="allCheck"/></th>-->
                            <th align = "center"  style="width: 50px;">Mã PGD</th>
                            <th style="width: 100px;">Tên PGD</th>
                            <th style="width: 60px;">Ngày gửi</th>
                            <th style="width: 50px;">User gửi</th>
                            <th style="width: 90px;">Trạng thái xử lý</th>
                            <th style="width: 90px;">Trạng thái gửi</th>
                        </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                
                        <s:if test="D1.equalsIgnoreCase('true')">
                            <tr style="text-align: center; color: #0000FF" onmouseover="mover(this);"  onmouseout="mout(this);">
                                <!--<td align = "center"  style="width: 20px;"><s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="poscd" fieldValue="%{D3}"/></td>-->
                                <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                <td style="width: 60px;"><s:property  value="D8" /></td>
                                <td style="width: 50px;"><s:property  value="D7" /></td>
                                <td style="width: 90px;"><s:property  value="D9" /></td>
                                <td style="width: 90px;"><s:property  value="D11" /></td>
                            </tr>
                        </s:if>
                        <s:else>
                            <tr style="text-align: center; color: red" onmouseover="mover(this);"  onmouseout="mout(this);">
                                <!--<td align = "center"  style="width: 20px;"><s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="poscd" fieldValue="%{D3}"/></td>-->
                                <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                <td style="width: 60px;"><s:property  value="D8" /></td>
                                <td style="width: 50px;"><s:property  value="D7" /></td>
                                <td style="width: 90px;"><s:property  value="D9" /></td>
                                <td style="width: 90px;"><s:property  value="D11" /></td>
                            </tr>
                        </s:else>
                    </s:iterator>
        </s:if>    
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
