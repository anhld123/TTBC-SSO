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
                $(".TD_CHITIEU").css({"width": "140px"});
                $(".TD_CAPKT").css({"width": "100px"});
                $(".TD_SOTIEN").css({"width": "75px"});                                
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            
            $("#allCheck_dat").change(function () {
                $(".checkboxdat").prop('checked', $(this).prop("checked"));
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
            <table border="1" class="editDelete" id="tablems01" align="center">
                <tr height="30">
                    <th rowspan="2" align = "center" class="TD_THUTU">
                       <input type="checkbox" id ="allCheck_dat" name="allCheck_dat"  />
                    </th>
                    <th rowspan="2" class="TD_CHITIEU">Họ và tên</th>
                    <th rowspan="2" class="TD_DAT_KODAT">Chức vụ</th>                                            
                    <th rowspan="2" class="TD_SOTIEN">CMT</th>                       
                    <th rowspan="2" class="TD_SOTIEN">Mã số thuế</th>
                    <th colspan="6"  class="TD_GHICHU">Thu nhập (TN)</th>
                    <th colspan="4" class="TD_GHICHU">Thu nhập được miễn thuế</th>
                </tr>  
                <tr>
                    <th class="TD_SOTIEN">Lương tháng</th>
                    <th class="TD_SOTIEN">Ngoài giờ</th>
                    <th class="TD_SOTIEN">Lương phép</th>
                    <th class="TD_SOTIEN">Lương bổ sung</th>
                    <th class="TD_SOTIEN">Thu nhập khác</th>
                    <th class="TD_SOTIEN">Tổng thu nhập</th>
                    <th class="TD_SOTIEN">Ngoài giờ được miễn thuế</th>
                    <th class="TD_SOTIEN">Lương phép được miễn thuế</th>
                    <th class="TD_SOTIEN">Phụ cấp độc hại</th>
                    <th class="TD_SOTIEN">Tổng TN được miễn thuế</th>
                    
                    
                </tr>
                 
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">     
                <tr>
                    <td  align="center" class="TD_THUTU">    
                            <input type="checkbox" id ="<s:property  value="%{#rowstatus.index}" />"  class="checkboxdat" name="lstsaveNT_DAT[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA" />" 
                                   class="D0"/>
                        </td>                          
                    <td align = "right" class="TD_CHITIEU">
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly/>
                    </td>     
<!--                        <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_CHITIEU">
                            <input type="text" value="<s:property  value="TEN" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly/>
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                            <input type="hidden" value="<s:property  value="D3" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D3"/>"/>
                            
                            <input type="hidden" value="<s:property  value="D5" />"  id="id5_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" value="<s:property  value="D5"/>"/>
                            <input type="hidden" value="<s:property  value="D6" />"  id="id6_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" value="<s:property  value="D6"/>"/>
                        </td>-->
                                                                      
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly/>
                        </td>                         
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly/>
                        </td>   
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D5" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly/>
                            </td> 
                        </s:if>
                        
                        <s:if test="!Grade.equalsIgnoreCase('1')">
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D5" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"/>
                            </td> 
                        </s:if>
                            
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D6" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"/>
                        </td> 
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D7" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"/>
                        </td> 
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D8" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"/>
                        </td> 
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D9" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"/>
                        </td> 
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D10" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"/>
                        </td> 
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D11" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"/>
                        </td> 
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D12" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"/>
                        </td> 
                        
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D13" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"/>
                        </td> 
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D14" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"/>
                        </td> 
                        
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D15" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"/>
                        </td> 
                    </tr>                    
                    
                </s:iterator>
            </table>
            <p></p>                        
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
