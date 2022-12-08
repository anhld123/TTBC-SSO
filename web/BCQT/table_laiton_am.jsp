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
                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TD_DONVITINH").css({"width": "50px"});
                $(".TD_SOLUONG").css({"width": "50px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "200px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        <script>
            function initTable()
            {
                autoEvaluate();
            };
            function autoEvaluate(){
//                alert('vao doClick');
                var arrCot = [".D4",".D5",
                    ".D6",".D7",".D8",".D9",
                    ".D10",".D11"]; //Luu cac cot cua du lieu can tinh toan
                
                  //Tinh toan cho 7 dong
//                for(var i=1; i<58; i++){   
//                    if(i == 12 || i ==13 || i ==20 || i ==25 || i ==32 || i ==38 || i==46 || i==50 || i ==51|| i ==54 )
//                    {
//                        i=i;
//                    }
//                    else
//                    {
//                        $(".D6").eq(i).val(parseFloat($(".D4").eq(i).val())* parseFloat($(".D5").eq(i).val()) );
//                        $(".D8").eq(i).val(parseFloat($(".D4").eq(i).val())* parseFloat($(".D7").eq(i).val()) ); 
//                        $(".D10").eq(i).val(parseFloat($(".D4").eq(i).val())* parseFloat($(".D9").eq(i).val()) ); 
//                    }      
//
//                }
                
                
                // Tinh cho dong 1
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(0).val( parseFloat($(arrCot[i]).eq(1).val()) + 
                            parseFloat($(arrCot[i]).eq(2).val()) + parseFloat($(arrCot[i]).eq(3).val()) + 
                             parseFloat($(arrCot[i]).eq(4).val()) + 
                            parseFloat($(arrCot[i]).eq(5).val()) + parseFloat($(arrCot[i]).eq(6).val()) + 
                            parseFloat($(arrCot[i]).eq(7).val()) + parseFloat($(arrCot[i]).eq(8).val()) + 
                            parseFloat($(arrCot[i]).eq(9).val())  );
                }                
                // Tinh cho dong 2.1
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(11).val( parseFloat($(arrCot[i]).eq(12).val()) + 
                            parseFloat($(arrCot[i]).eq(13).val()) + parseFloat($(arrCot[i]).eq(14).val()) + 
                             parseFloat($(arrCot[i]).eq(15).val()) + 
                            parseFloat($(arrCot[i]).eq(16).val()) + parseFloat($(arrCot[i]).eq(17).val()));
                }
                // Tinh cho dong 2.2
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(18).val( parseFloat($(arrCot[i]).eq(19).val()) + 
                            parseFloat($(arrCot[i]).eq(20).val()) );
                }
                
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(10).val( parseFloat($(arrCot[i]).eq(11).val()) + 
                            parseFloat($(arrCot[i]).eq(18).val()) +parseFloat($(arrCot[i]).eq(21).val())
                            + parseFloat($(arrCot[i]).eq(22).val()));
                }
                
                // Tinh cho dong 2.5
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(23).val( parseFloat($(arrCot[i]).eq(24).val()) + 
                            parseFloat($(arrCot[i]).eq(25).val()) + parseFloat($(arrCot[i]).eq(26).val()) +
                            parseFloat($(arrCot[i]).eq(27).val()) + parseFloat($(arrCot[i]).eq(28).val()) +
                            parseFloat($(arrCot[i]).eq(29).val()) + parseFloat($(arrCot[i]).eq(30).val()));
                }
                
//                // Tinh cho dong 2
//                 for (i = 0; i < arrCot.length; i++) { 
//                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
//                    $(arrCot[i]).eq(12).val( parseFloat($(arrCot[i]).eq(13).val()) + 
//                            parseFloat($(arrCot[i]).eq(20).val()) + parseFloat($(arrCot[i]).eq(23).val()) + 
//                             parseFloat($(arrCot[i]).eq(24).val()) + 
//                            parseFloat($(arrCot[i]).eq(25).val()));
//                }
                
                // Tinh cho dong 3.6
                 for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(37).val( parseFloat($(arrCot[i]).eq(38).val()) + 
                            parseFloat($(arrCot[i]).eq(39).val()) + parseFloat($(arrCot[i]).eq(40).val()) + 
                            parseFloat($(arrCot[i]).eq(41).val()) + parseFloat($(arrCot[i]).eq(42).val()) + 
                            parseFloat($(arrCot[i]).eq(43).val()) + parseFloat($(arrCot[i]).eq(44).val()) );
                }
                // Tinh cho dong 3
                 for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(31).val( parseFloat($(arrCot[i]).eq(32).val()) + 
                            parseFloat($(arrCot[i]).eq(33).val()) + parseFloat($(arrCot[i]).eq(34).val()) + 
                            parseFloat($(arrCot[i]).eq(35).val()) + parseFloat($(arrCot[i]).eq(36).val())
                            + parseFloat($(arrCot[i]).eq(37).val()));
                }
                // Tinh cho dong 4
                 for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(45).val( parseFloat($(arrCot[i]).eq(46).val()) + 
                            parseFloat($(arrCot[i]).eq(47).val()) + parseFloat($(arrCot[i]).eq(48).val()) );
                }
                
                // Tinh cho dong 5.1
                 for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(50).val( parseFloat($(arrCot[i]).eq(51).val()) + 
                            parseFloat($(arrCot[i]).eq(52).val()) );
                }
                // Tinh cho dong 5.2
                 for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(53).val( parseFloat($(arrCot[i]).eq(54).val()) + 
                            parseFloat($(arrCot[i]).eq(55).val()) );
                }
                
                // Tinh cho dong 5
                 for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(49).val( parseFloat($(arrCot[i]).eq(50).val()) + 
                            parseFloat($(arrCot[i]).eq(53).val()) +parseFloat($(arrCot[i]).eq(56).val()));
                }
                
            };
                </script>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_bcqt}" action="SAVE_%{khoa_bcqt}" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                SAO KÊ CÁC MÓN VAY CÓ LÃI TỒN ÂM
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablems05" align="center">
                <tr>      
                            <th rowspan="1" class="TD_STT">STT</th>                           
                            <!--<th rowspan="2" class="TD_TOTIEN">CIF</th>-->  
                            <th rowspan="1" class="TD_TENKH">Tên KH</th>  
                            <th rowspan="1" class="TD_MAKH">Mã khách hàng</th> 
                            <th rowspan="1" class="TD_SOKU">Mã khoản vay</th>    
                            <th rowspan="1"  class="TD_MAKH">Tên chương trình cho vay</th>
                            <th rowspan="1" class="TD_MAKH">Lãi suất cho vay</th> 
                            <th rowspan="1"  class="TD_MAKH">Dư nợ hỗ trợ lãi suất lũy kế</th>   
                            <th rowspan="1"  class="TD_MAKH">Lũy kế số tiền đã hỗ trợ lãi suất </th>   
                            <th rowspan="1"  class="TD_MAKH">Số tiền hỗ trợ lãi suất phải thu hồi</th> 
                            <th rowspan="1"  class="TD_MAKH">Số tiền hỗ trợ lãi suất đã thu hồi</th>   
                            <th rowspan="1"  class="TD_MAKH">Số tiền hỗ trợ lãi suất còn phải thu hồi</th>  
                            <th rowspan="1" class="TD_TOTIEN">Bút toán thu hồi</th>  
                                                        
                            <th rowspan="1"  class="TD_TENKH">Lý do thu hồi</th>  
                             
                        </tr>         
                        
                        <tr style="font-style: italic;">
                            <td style="text-align: center">(1)</td>                            
                            <td style="text-align: center">(2)</td>
                            <td style="text-align: center">(3)</td>
                            <td style="text-align: center">(4)</td>
                            <td style="text-align: center">(5)</td>
                            <td style="text-align: center">(6)</td>
                            <td style="text-align: center">(7)</td>
                            <td style="text-align: center">(8)</td>
                            <td style="text-align: center">(9)</td>
                            <td style="text-align: center">(10)</td>
                            <td style="text-align: center">(11)</td>                                                        
                            <td style="text-align: center">(12)</td>    
                            <td style="text-align: center">(13)</td>    
                             
                        </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr height="22">  
                        <td  align="right" class="TD_TEN_KH">    
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                        </td>
                        <td  align="right" class="TD_TEN_KH">    
                        <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td  align="right" class="TD_DONVITINH">    
                        <input type="text" value="<s:property  value="D1" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" lass="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>

                        <td align = "right" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D6" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D7" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D8" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D9" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D10" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td> 
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D11" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                    </tr>
                    </s:if>
                    
                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr height="22">                              
                        <td align="center" class="TD_TEN_KH">
                            <s:property  value="TT_HIENTHI" /> 
                        </td>
                        <td align="left" class="TD_TEN_KH">
                            <s:property  value="TEN" /> 
                        </td>
                        <td align="center" class="TD_DONVITINH">
                            <s:property  value="D1" /> 
                        </td>
                                                
                        <td align = "right" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D6" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                   />
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D7" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D8" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                   />
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D9" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D10" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                   />
                        </td>  
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D11" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                   />
                        </td>  
                    </tr>
                    </s:if>
                    
                </s:iterator>
            </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initTable();
        </script>
    </body>
</html>
