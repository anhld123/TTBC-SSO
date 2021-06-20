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
                $(".TD_CHITIEU").css({"width": "150px"});
                $(".TD_SOTIEN").css({"width": "70px"});
                $(".TD_GHICHU").css({"width": "200px"});

            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        <!--        <script>
                    function autoEvaluate() {
                        //                alert('vao doClick');
                        var arrCot = [".D1", ".D2", ".D3", ".D4"]; //Luu cac cot cua du lieu can tinh toan
        
                        //                  Tinh toan cho 7 dong
                        for (var i = 0; i < 99; i++) {
                            //8=2+4-6
                            $(".D4").eq(i).val(parseFloat($(".D2").eq(i).val()) - parseFloat($(".D3").eq(i).val()));
        
                        }
                        //                              
                    }
                </script>-->
        <script>
            function autoEvaluate() {
//                alert(arrCot.length);
                var arrCot = [".D1", ".D2", ".D3", ".D4"]; //Luu cac cot cua du lieu can tinh toan
                //                  Tinh toan cho 7 dong
                for (var i = 0; i < 99; i++) {
                    //8=2+4-6
                    $(".D4").eq(i).val(parseFloat($(".D2").eq(i).val()) - parseFloat($(".D3").eq(i).val()));

                }
                // Tinh cho donG A200
                for (i = 0; i < 100; i++) {
                    
                    //Tinh tong cho dong "NGUON TW"
                    $(arrCot[i]).eq(1).val(parseFloat($(arrCot[i]).eq(2).val()) + parseFloat($(arrCot[i]).eq(3).val()) +
                            parseFloat($(arrCot[i]).eq(4).val()) + parseFloat($(arrCot[i]).eq(5).val()) +
                            parseFloat($(arrCot[i]).eq(6).val()) +
                            parseFloat($(arrCot[i]).eq(7).val()) + parseFloat($(arrCot[i]).eq(8).val())+
                            parseFloat($(arrCot[i]).eq(9).val()) + parseFloat($(arrCot[i]).eq(10).val()) +
                            parseFloat($(arrCot[i]).eq(11).val()) + parseFloat($(arrCot[i]).eq(12).val()) +
                            parseFloat($(arrCot[i]).eq(13).val()) + parseFloat($(arrCot[i]).eq(14).val()) +
                            parseFloat($(arrCot[i]).eq(15).val()) + parseFloat($(arrCot[i]).eq(16).val()) +
                            parseFloat($(arrCot[i]).eq(17).val()) + parseFloat($(arrCot[i]).eq(18).val()) +
                            parseFloat($(arrCot[i]).eq(19).val()) + parseFloat($(arrCot[i]).eq(20).val()) +
                            parseFloat($(arrCot[i]).eq(21).val()) + parseFloat($(arrCot[i]).eq(22).val()) +
                            parseFloat($(arrCot[i]).eq(23).val()) + parseFloat($(arrCot[i]).eq(24).val()) +
                            parseFloat($(arrCot[i]).eq(25).val()) + parseFloat($(arrCot[i]).eq(26).val()) +
                            parseFloat($(arrCot[i]).eq(27).val()) + parseFloat($(arrCot[i]).eq(28).val()) +
                            parseFloat($(arrCot[i]).eq(29).val()) + parseFloat($(arrCot[i]).eq(30).val()) +
                            parseFloat($(arrCot[i]).eq(31).val()) + parseFloat($(arrCot[i]).eq(32).val()) +
                            parseFloat($(arrCot[i]).eq(33).val()) 
                            );
                    //Tinh tong cho dong "NGUON DP"
                    $(arrCot[i]).eq(34).val(parseFloat($(arrCot[i]).eq(35).val()) +
                            parseFloat($(arrCot[i]).eq(36).val()) +
                            parseFloat($(arrCot[i]).eq(37).val()) + parseFloat($(arrCot[i]).eq(38).val())+
                            parseFloat($(arrCot[i]).eq(39).val()) + parseFloat($(arrCot[i]).eq(40).val()) +
                            parseFloat($(arrCot[i]).eq(41).val()) + parseFloat($(arrCot[i]).eq(42).val()) +
                            parseFloat($(arrCot[i]).eq(43).val()) + parseFloat($(arrCot[i]).eq(44).val()) +
                            parseFloat($(arrCot[i]).eq(45).val()) + parseFloat($(arrCot[i]).eq(46).val()) +
                            parseFloat($(arrCot[i]).eq(47).val()) + parseFloat($(arrCot[i]).eq(48).val()) +
                            parseFloat($(arrCot[i]).eq(49).val()) + parseFloat($(arrCot[i]).eq(50).val()) +
                            parseFloat($(arrCot[i]).eq(51).val()) + parseFloat($(arrCot[i]).eq(52).val()) +
                            parseFloat($(arrCot[i]).eq(53).val()) + parseFloat($(arrCot[i]).eq(54).val()) +
                            parseFloat($(arrCot[i]).eq(55).val()) + parseFloat($(arrCot[i]).eq(56).val()) +
                            parseFloat($(arrCot[i]).eq(57).val()) + parseFloat($(arrCot[i]).eq(58).val()) +
                            parseFloat($(arrCot[i]).eq(59).val()) + parseFloat($(arrCot[i]).eq(60).val()) +
                            parseFloat($(arrCot[i]).eq(61).val()) + parseFloat($(arrCot[i]).eq(62).val()) +
                            parseFloat($(arrCot[i]).eq(63).val()) + parseFloat($(arrCot[i]).eq(64).val()) +
                            parseFloat($(arrCot[i]).eq(65).val()) + parseFloat($(arrCot[i]).eq(66).val())
                            );
                     $(arrCot[i]).eq(67).val(parseFloat($(arrCot[i]).eq(68).val()) + parseFloat($(arrCot[i]).eq(69).val()));
                     
                     $(arrCot[i]).eq(70).val(parseFloat($(arrCot[i]).eq(71).val())
                             + parseFloat($(arrCot[i]).eq(72).val()) + parseFloat($(arrCot[i]).eq(73).val())
                             + parseFloat($(arrCot[i]).eq(74).val()) + parseFloat($(arrCot[i]).eq(75).val()));
                     
                     $(arrCot[i]).eq(76).val(parseFloat($(arrCot[i]).eq(77).val())
                             + parseFloat($(arrCot[i]).eq(78).val()) + parseFloat($(arrCot[i]).eq(79).val())
                             + parseFloat($(arrCot[i]).eq(80).val()));
                     $(arrCot[i]).eq(0).val(parseFloat($(arrCot[i]).eq(1).val()) + parseFloat($(arrCot[i]).eq(34).val()));
                }
                

            }
        </script>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_ktgs}" action="SAVE_%{khoa_ktgs}" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                BÁO CÁO KIỂM TOÁN HOẠT ĐỘNG TÍN DỤNG
            </div>
            <s:hidden name="khoa_ktgs"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablems02" align="center">
                <tr>
                    <th rowspan="2"  class="TD_THUTU">TT</th>
                    <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>
                    <th rowspan="2" class="TD_SOTIEN">Số dư đầu năm</th>    
                    <th colspan="2">Số dư cuối kỳ BC</th> 
                    <th rowspan="2" class="TD_SOTIEN">Chênh lệch</th>    
                </tr>
                <tr>                                  
                    <th   class="TD_SOTIEN">Số báo cáo</th>
                    <th   class="TD_SOTIEN">Số kiểm toán</th>                    

                </tr>               
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr height="22">   
                            <td  align="right" class="TD_THUTU">    
                                <input type="text" style="text-align:center"  value="<s:property  value="TT_HIENTHI" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="<s:property value='FONTFORMAT'/> TEN_KH"  onfocus="this.select()" onblur="autoEvaluate()"   readonly="readonly" />                                  
                                <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                                <input type="hidden" value="<s:property  value="THUTU" />"                               
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/> 
                            </td>
                            <td  align="right" class="TD_CHITIEU">    
                                <input type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"  class="<s:property value='FONTFORMAT'/> TEN_KH"  onfocus="this.select()"    readonly="readonly" />                                  
                            </td>              
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D1" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"
                                       readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/>TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"
                                       readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"
                                       readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D4" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"
                                       readonly="readonly"/>
                            </td>                                                                        
                        </tr>
                    </s:if>
                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr height="22">   
                            <td align ="center" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                                <input type="text" style="text-align:center" value="<s:property  value="TT_HIENTHI" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" readonly/>
                            </td>
                            <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_SOTIEN">
                                <input type="text" value="<s:property  value="TEN" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly/>
                            </td>
                            <s:if test="MA.equalsIgnoreCase('A53')||MA.equalsIgnoreCase('A54')">
                                <td  align="right" class= "<s:property value='FONTFORMAT'/> TD_SOTIEN" >
                                    <input type="text" value="<s:property  value="D1" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 number2 TEN_KH" onfocus="this.select()"   onblur="autoEvaluate()" />                                  

                                    <input type="hidden" value="<s:property  value="THUTU" />"                               
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/> 

                                    <input type="hidden" value="<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                                </td>
                                <td align = "right" class="TD_SOTIEN">
                                    <input type="text" value="<s:property  value="D2" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 TEN_KH" onfocus="this.select()"
                                           onblur="autoEvaluate()" />
                                </td>
                            </s:if>
                            <s:else>
                                <td  align="right" class= "<s:property value='FONTFORMAT'/> TD_SOTIEN" >
                                    <input type="text" value="<s:property  value="D1" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 number2 TEN_KH" onfocus="this.select()"   onblur="autoEvaluate()" readonly/>                                  

                                    <input type="hidden" value="<s:property  value="THUTU" />"                               
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/> 

                                    <input type="hidden" value="<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                                </td>
                                <td align = "right" class="TD_SOTIEN">
                                    <input type="text" value="<s:property  value="D2" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 TEN_KH" onfocus="this.select()"
                                           onblur="autoEvaluate()" readonly/>
                                </td>
                            </s:else>

                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <td align = "right" class="TD_SOTIEN">
                                    <input type="text" value="<s:property  value="D3" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 TEN_KH" onfocus="this.select()"
                                           onblur="autoEvaluate()"/>
                                </td>
                            </s:if>
                            <s:else>
                                <td align = "right" class="TD_SOTIEN">
                                    <input type="text" value="<s:property  value="D3" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 TEN_KH" onfocus="this.select()"
                                           onblur="autoEvaluate()" readonly/> 
                                </td>
                            </s:else>



                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D4" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()" readonly/>
                            </td>

                        </tr>
                    </s:if>

                </s:iterator>
            </table>
            <p></p>          

            <sj:submit id="%{khoa_ktgs}_save" name="%{khoa_ktgs}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
