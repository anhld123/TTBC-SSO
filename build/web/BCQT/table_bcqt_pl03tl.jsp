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
                $('.number2').number(true, 2);
                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TD_DONVITINH").css({"width": "60px"});
                $(".TD_THUTU").css({"width": "15px"});
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
                var arrCot = [".D1",".D2",".D3",".D4",".D5",
                    ".D6",".D7",".D8",".D9",
                    ".D10",".D11",".D12",".D13"]; //Luu cac cot cua du lieu can tinh toan
                
////                  Tinh toan cho 7 dong
//                for(var i=0; i<33; i++){                    
//                    $(".D13").eq(i).val(parseFloat($(".D1").eq(i).val()) + parseFloat($(".D2").eq(i).val()) + 
//                                        parseFloat($(".D3").eq(i).val()) + parseFloat($(".D4").eq(i).val()) + 
//                                        parseFloat($(".D5").eq(i).val()) + parseFloat($(".D6").eq(i).val()) + 
//                                        parseFloat($(".D7").eq(i).val()) + parseFloat($(".D8").eq(i).val()) + 
//                                        parseFloat($(".D9").eq(i).val()) + parseFloat($(".D10").eq(i).val()) + 
//                                        parseFloat($(".D11").eq(i).val()) + parseFloat($(".D12").eq(i).val())                                         
//                    );
//                    
//                }
//                
//                alert(arrCot.length);
                for (i = 0; i < arrCot.length; i++) { 
                    //Cho dòng cuối
                    //Thang 31 ngay
                    if(i==0 ||i==2 ||i==4 ||i==6 ||i==7 ||i==9 ||i==11)
                    $(arrCot[i]).eq(31).val((parseFloat($(arrCot[i]).eq(0).val()) + parseFloat($(arrCot[i]).eq(1).val()) + 
                            parseFloat($(arrCot[i]).eq(2).val()) + parseFloat($(arrCot[i]).eq(3).val()) + 
                             parseFloat($(arrCot[i]).eq(4).val()) + 
                            parseFloat($(arrCot[i]).eq(5).val()) + parseFloat($(arrCot[i]).eq(6).val()) + 
                            parseFloat($(arrCot[i]).eq(7).val()) + parseFloat($(arrCot[i]).eq(8).val()) + 
                            parseFloat($(arrCot[i]).eq(9).val()) + parseFloat($(arrCot[i]).eq(10).val()) + 
                            parseFloat($(arrCot[i]).eq(11).val()) +
                            parseFloat($(arrCot[i]).eq(12).val()) + parseFloat($(arrCot[i]).eq(13).val()) + 
                            parseFloat($(arrCot[i]).eq(14).val()) + parseFloat($(arrCot[i]).eq(15).val()) + 
                            parseFloat($(arrCot[i]).eq(16).val()) + parseFloat($(arrCot[i]).eq(17).val()) + 
                            parseFloat($(arrCot[i]).eq(18).val()) + parseFloat($(arrCot[i]).eq(19).val()) + 
                            parseFloat($(arrCot[i]).eq(20).val()) + parseFloat($(arrCot[i]).eq(21).val()) + 
                            parseFloat($(arrCot[i]).eq(22).val()) + parseFloat($(arrCot[i]).eq(23).val()) + 
                            parseFloat($(arrCot[i]).eq(24).val()) + parseFloat($(arrCot[i]).eq(25).val()) + 
                            parseFloat($(arrCot[i]).eq(26).val()) + parseFloat($(arrCot[i]).eq(27).val()) + 
                            parseFloat($(arrCot[i]).eq(28).val()) + parseFloat($(arrCot[i]).eq(29).val()) + 
                            parseFloat($(arrCot[i]).eq(30).val())
                    )/31);
                    //thang 30 ngay
                    if(i==3 ||i==5 ||i==8 ||i==10)
                    $(arrCot[i]).eq(31).val((parseFloat($(arrCot[i]).eq(0).val()) + parseFloat($(arrCot[i]).eq(1).val()) + 
                            parseFloat($(arrCot[i]).eq(2).val()) + parseFloat($(arrCot[i]).eq(3).val()) + 
                             parseFloat($(arrCot[i]).eq(4).val()) + 
                            parseFloat($(arrCot[i]).eq(5).val()) + parseFloat($(arrCot[i]).eq(6).val()) + 
                            parseFloat($(arrCot[i]).eq(7).val()) + parseFloat($(arrCot[i]).eq(8).val()) + 
                            parseFloat($(arrCot[i]).eq(9).val()) + parseFloat($(arrCot[i]).eq(10).val()) + 
                            parseFloat($(arrCot[i]).eq(11).val()) +
                            parseFloat($(arrCot[i]).eq(12).val()) + parseFloat($(arrCot[i]).eq(13).val()) + 
                            parseFloat($(arrCot[i]).eq(14).val()) + parseFloat($(arrCot[i]).eq(15).val()) + 
                            parseFloat($(arrCot[i]).eq(16).val()) + parseFloat($(arrCot[i]).eq(17).val()) + 
                            parseFloat($(arrCot[i]).eq(18).val()) + parseFloat($(arrCot[i]).eq(19).val()) + 
                            parseFloat($(arrCot[i]).eq(20).val()) + parseFloat($(arrCot[i]).eq(21).val()) + 
                            parseFloat($(arrCot[i]).eq(22).val()) + parseFloat($(arrCot[i]).eq(23).val()) + 
                            parseFloat($(arrCot[i]).eq(24).val()) + parseFloat($(arrCot[i]).eq(25).val()) + 
                            parseFloat($(arrCot[i]).eq(26).val()) + parseFloat($(arrCot[i]).eq(27).val()) + 
                            parseFloat($(arrCot[i]).eq(28).val()) + parseFloat($(arrCot[i]).eq(29).val()) + 
                            parseFloat($(arrCot[i]).eq(30).val())
                    )/30);
                    //thang 2 - 28 ngay
                    if(i==1)
                    $(arrCot[i]).eq(31).val((parseFloat($(arrCot[i]).eq(0).val()) + parseFloat($(arrCot[i]).eq(1).val()) + 
                            parseFloat($(arrCot[i]).eq(2).val()) + parseFloat($(arrCot[i]).eq(3).val()) + 
                             parseFloat($(arrCot[i]).eq(4).val()) + 
                            parseFloat($(arrCot[i]).eq(5).val()) + parseFloat($(arrCot[i]).eq(6).val()) + 
                            parseFloat($(arrCot[i]).eq(7).val()) + parseFloat($(arrCot[i]).eq(8).val()) + 
                            parseFloat($(arrCot[i]).eq(9).val()) + parseFloat($(arrCot[i]).eq(10).val()) + 
                            parseFloat($(arrCot[i]).eq(11).val()) +
                            parseFloat($(arrCot[i]).eq(12).val()) + parseFloat($(arrCot[i]).eq(13).val()) + 
                            parseFloat($(arrCot[i]).eq(14).val()) + parseFloat($(arrCot[i]).eq(15).val()) + 
                            parseFloat($(arrCot[i]).eq(16).val()) + parseFloat($(arrCot[i]).eq(17).val()) + 
                            parseFloat($(arrCot[i]).eq(18).val()) + parseFloat($(arrCot[i]).eq(19).val()) + 
                            parseFloat($(arrCot[i]).eq(20).val()) + parseFloat($(arrCot[i]).eq(21).val()) + 
                            parseFloat($(arrCot[i]).eq(22).val()) + parseFloat($(arrCot[i]).eq(23).val()) + 
                            parseFloat($(arrCot[i]).eq(24).val()) + parseFloat($(arrCot[i]).eq(25).val()) + 
                            parseFloat($(arrCot[i]).eq(26).val()) + parseFloat($(arrCot[i]).eq(27).val()) + 
                            parseFloat($(arrCot[i]).eq(28).val()) + parseFloat($(arrCot[i]).eq(29).val()) + 
                            parseFloat($(arrCot[i]).eq(30).val())
                    )/28);
                }
//                $(".D13").eq(31).val(0);
            }
                </script>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_bcqt}" action="SAVE_%{khoa_bcqt}" theme="simple">       
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                BẢNG TÍNH TỒN NGÂN NGUỒN VỐN ỦY THÁC ĐỊA PHƯƠNG
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Triệu đồng
            </div>
            <table border="1" class="editDelete" id="tablems08" align="center">
                <tr height="22">
                    <th rowspan="2"  class="TD_NGUYENGIA">Ngày</th>
                    <th colspan="13" >SỐ DƯ TỒN NGÂN NGUỒN VỐN ỦY THÁC ĐỊA PHƯƠNG 
                        <br>(Dư Có GL 9243+924211+924212) - (Dư Nợ GL 9161 + GL 9162 + GL 9163 + GL 919x6 + GL 919x7 + GL 919x8)</th>           
                </tr>
                <tr height="22">                                  
                    <th TD_NGUYENGIA>Tháng 1</th>
                    <th TD_NGUYENGIA>Tháng 2</th>
                    <th TD_NGUYENGIA>Tháng 3</th>
                    <th TD_NGUYENGIA>Tháng 4</th>
                    <th TD_NGUYENGIA>Tháng 5</th>
                    <th TD_NGUYENGIA>Tháng 6</th>
                    <th TD_NGUYENGIA>Tháng 7</th>
                    <th TD_NGUYENGIA>Tháng 8</th>
                    <th TD_NGUYENGIA>Tháng 9</th>
                    <th TD_NGUYENGIA>Tháng 10</th>
                    <th TD_NGUYENGIA>Tháng 11</th>
                    <th TD_NGUYENGIA>Tháng 12</th>
                    <!--<th TD_NGUYENGIA>Tổng cả năm</th>-->
                </tr>                                
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                                            
                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr height="22">                              
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"
                                       readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D1" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"/>
                                <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>  
                                <input type="hidden" value="<s:property  value="D14" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" value="<s:property  value="D14"/>"/>   
                                <input type="hidden" value="<s:property  value="D13" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" value="<s:property  value="D13"/>"/>
                            </td>

                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D4" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D5" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D6" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"
                                       />
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D7" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D8" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D9" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D10" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"/>
                            </td>       
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D11" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"/>
                            </td> 

                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D12" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D12 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"/>
                            </td>

<!--                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D13" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
                            </td>-->
                        </tr> 
                    </s:if>   
                    
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr height="22">                              
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"
                                       readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D1" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
                                <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>   
                                <input type="hidden" value="<s:property  value="D14" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" value="<s:property  value="D14"/>"/>   
                                <input type="hidden" value="<s:property  value="D13" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" value="<s:property  value="D13"/>"/>
                            </td>

                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D4" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D5" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D6" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D7" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D8" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D9" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D10" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
                            </td>       
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D11" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
                            </td> 

                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D12" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D12 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly="readonly"/>
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
