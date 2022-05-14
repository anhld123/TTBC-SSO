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
            $('input.number51').css({"text-align": "right"});
            $('.D0').css({"text-align": "center"});
            $('input.number2').css({"text-align": "right"});
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            $('#ui-datepicker-div').css('clip', 'auto');
            //Cac truong bang so --> se co so truong = 0
            $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
            $('.number2').number(true, 2);
            $('.number51').number(true, 6);
            $(".TD_NHOM").css({"width": "10px"});
            $(".TD_THUTU").css({"width": "40px"});
            $(".TD_CHITIEU").css({"width": "130px"});
            $(".TD_SOTIEN").css({"width": "70px"});
            $(".TD_GHICHU").css({"width": "170px"});

        });
        $('.TEN_KH').focus(function () {
            $(this).closest('tr').addClass('highlight_row');
        });
        $('.TEN_KH').blur(function () {
            $(this).closest('tr').removeClass('highlight_row');
        });
//            autoEvaluate();
    </script>    

    <script>
        function autoEvaluate() {
//                alert('vao doClick');
            var arrCot = [".D9",".D10"]; //Luu cac cot cua du lieu can tinh toan

            // Tinh cho donG A1
            for (i = 0; i < arrCot.length; i++) {
                //A1	A1	CT0002	Các khoản thu nội bảng  
//                1. Các khoản thu nội bảng
                $(arrCot[i]).eq(1).val(parseFloat($(arrCot[i]).eq(2).val()) + parseFloat($(arrCot[i]).eq(3).val()) +
                        parseFloat($(arrCot[i]).eq(4).val()) + parseFloat($(arrCot[i]).eq(5).val()) +
                        parseFloat($(arrCot[i]).eq(6).val()));
            }
            // Các khoản thu ngoại bảng khác
            for (i = 0; i < arrCot.length; i++) {
                //A20013	CT0016	Các khoản thu ngoại bảng khác
                $(arrCot[i]).eq(12).val(parseFloat($(arrCot[i]).eq(13).val()) +
                        parseFloat($(arrCot[i]).eq(14).val()));
            }

            // Tinh cho dong A2001
            for (i = 0; i < arrCot.length; i++) {
                //A2001	CT0009	Các khoản được cộng
                $(arrCot[i]).eq(8).val(parseFloat($(arrCot[i]).eq(9).val()) + parseFloat($(arrCot[i]).eq(10).val()) + parseFloat($(arrCot[i]).eq(11).val()) + 
                        parseFloat($(arrCot[i]).eq(12).val()));
            }
            
//            for (i = 0; i < arrCot.length; i++) {
//                //A2001	CT0009	Các khoản được cộng
//                $(arrCot[i]).eq(8).val(parseFloat($(arrCot[i]).eq(9).val()) + parseFloat($(arrCot[i]).eq(10).val()) + parseFloat($(arrCot[i]).eq(11).val()) + 
//                        parseFloat($(arrCot[i]).eq(12).val()));
//            }

            // Tinh cho dong A2002
            for (i = 0; i < arrCot.length; i++) {
                //2.2 Các khoản phải trừ
                $(arrCot[i]).eq(15).val(parseFloat($(arrCot[i]).eq(16).val()) + parseFloat($(arrCot[i]).eq(17).val()) + parseFloat($(arrCot[i]).eq(18).val()) +
                        parseFloat($(arrCot[i]).eq(19).val()));
            }
            // Tinh cho dong A2
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các T/c Chính trị xã hội"
                $(arrCot[i]).eq(7).val(parseFloat($(arrCot[i]).eq(8).val()) -
                        parseFloat($(arrCot[i]).eq(15).val()));
            }

            // A -  TỔNG THU
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các T/c Chính trị xã hội"
                $(arrCot[i]).eq(0).val(parseFloat($(arrCot[i]).eq(1).val()) +
                        parseFloat($(arrCot[i]).eq(7).val()));
            }

            // Tinh cho dong  B1 21=22+23+24+26+27+28+29
            for (i = 0; i < arrCot.length; i++) {
                $(arrCot[i]).eq(21).val(parseFloat($(arrCot[i]).eq(22).val()) +
                        parseFloat($(arrCot[i]).eq(23).val()) + parseFloat($(arrCot[i]).eq(24).val()) + parseFloat($(arrCot[i]).eq(25).val()) +
                        parseFloat($(arrCot[i]).eq(26).val()) + parseFloat($(arrCot[i]).eq(27).val()) +
                        parseFloat($(arrCot[i]).eq(28).val()) + parseFloat($(arrCot[i]).eq(29).val()));
            }

            // Tinh cho dong B20011  32=33+34+35+36+37  
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các T/c Chính trị xã hội"
                $(arrCot[i]).eq(31).valparseFloat($(arrCot[i]).eq(32).val() + parseFloat($(arrCot[i]).eq(33).val()) + parseFloat($(arrCot[i]).eq(34).val()) +
                        parseFloat($(arrCot[i]).eq(35).val()))
            }

            // Tinh cho dong B20012  38=39+40  
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các T/c Chính trị xã hội"
                $(arrCot[i]).eq(36).val(parseFloat($(arrCot[i]).eq(37).val()) + parseFloat($(arrCot[i]).eq(38).val()) +
                        parseFloat($(arrCot[i]).eq(39).val()) + parseFloat($(arrCot[i]).eq(40).val()))
            }

            // Tinh cho dong B2001  31=32+38+41  
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các T/c Chính trị xã hội"
                $(arrCot[i]).eq(30).val(parseFloat($(arrCot[i]).eq(31).val()) - parseFloat($(arrCot[i]).eq(36).val()))
            }

            // Tinh cho dong B2002  42=43+44+45  
//            for (i = 0; i < arrCot.length; i++) {
//                //Tinh tong cho dong "Các T/c Chính trị xã hội"
//                $(arrCot[i]).eq(42).val(parseFloat($(arrCot[i]).eq(43).val()) + parseFloat($(arrCot[i]).eq(44).val())
//                        + parseFloat($(arrCot[i]).eq(45).val()))
//            }
//
//            // Tinh cho dong B2  30=31+42  
//            for (i = 0; i < arrCot.length; i++) {
//                //Tinh tong cho dong "Các T/c Chính trị xã hội"
//                $(arrCot[i]).eq(30).val(parseFloat($(arrCot[i]).eq(31).val()) + parseFloat($(arrCot[i]).eq(42).val()))
//            }

            // Tinh cho dong B  20=21+30 
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các T/c Chính trị xã hội"
                $(arrCot[i]).eq(20).val(parseFloat($(arrCot[i]).eq(21).val()) + parseFloat($(arrCot[i]).eq(30).val()))
            }

            // Tinh cho dong C  46=0-20
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các T/c Chính trị xã hội"
                $(arrCot[i]).eq(41).val(parseFloat($(arrCot[i]).eq(0).val()) - parseFloat($(arrCot[i]).eq(20).val()))
            }

            // Tinh cho dong E  48=49+50
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các T/c Chính trị xã hội"
                $(arrCot[i]).eq(42).val(parseFloat($(arrCot[i]).eq(53).val()) + parseFloat($(arrCot[i]).eq(54).val()))
            }

            // Tinh cho dong H  52=53+54+55+56
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các T/c Chính trị xã hội"
                $(arrCot[i]).eq(50).val(parseFloat($(arrCot[i]).eq(51).val()) + parseFloat($(arrCot[i]).eq(52).val()) +
                        parseFloat($(arrCot[i]).eq(53).val()) + parseFloat($(arrCot[i]).eq(54).val()))
            }

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
            KHOÁN TÀI CHÍNH
        </div>
        <s:hidden name="khoa_bcqt"/>
        <div id="divDonvitinh">
            Đơn vị tính: Triệu đồng
        </div>
        <table border="1" class="editDelete" id="tablems01" align="center">
            <tr height="32" >
                <th  class="TD_NHOM">Phân nhóm</th>
                <th   class="TD_THUTU">Mã chỉ tiêu</th>
                <th  class="TD_CHITIEU">Mô tả</th>
                <th  class="TD_GHICHU">Công thức</th>                         
                <th  class="TD_SOTIEN">Số kế hoạch</th>  
                <th  class="TD_SOTIEN">Số thực hiện</th>  
            </tr>                              
            <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                    <tr height="22">  
                        <td  align="left" class= "<s:property value='FONTFORMAT'/> TD_NHOM">    
                            <input type="text" value="<s:property  value="D1" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                   class="D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"  readonly="readonly" />                                  
                        </td>    
                        <td  align="left" class= "<s:property value='FONTFORMAT'/> TD_THUTU">    
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" onblur="autoEvaluate()"   readonly="readonly" />                                  
                            <input type="hidden" value="<s:property  value="MA" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                            <input type="hidden" value="<s:property  value="CO_TONGHOP" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP" value="<s:property  value="CO_TONGHOP"/>"/> 
                            <input type="hidden" value="<s:property  value="D2" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>"/> 
                            <input type="hidden" value="<s:property  value="D11" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" value="<s:property  value="D11"/>"/> 
                            <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/> 

                        </td>
                        <td  align="right" class= "<s:property value='FONTFORMAT'/> TD_CHITIEU">    
                            <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"  class="<s:property value='FONTFORMAT'/>" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>              

                        <td align = "right" class="TD_GHICHU">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D2 <s:property value='FONTFORMAT'/>  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                   readonly="readonly"/>
                        </td> 

                        <s:if test="TT_HIENTHI.equalsIgnoreCase('B200115')">
                            <s:if test="D2.equalsIgnoreCase('02')">
                                <td align = "right" class="TD_SOTIEN">
                                    <input type="text" value="<s:property  value="D9" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number51 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" onblur="autoEvaluate()"                                   
                                           readonly="readonly"/>
                                </td>
                            </s:if>
                            <s:else>
                                <td align = "right" class="TD_SOTIEN">
                                    <input type="text" value="<s:property  value="D9" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number51 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"     onblur="autoEvaluate()"                              
                                </td>
                            </s:else>

                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D10" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number51 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"   onblur="autoEvaluate()"                                
                                       readonly="readonly"/>
                            </td>
                        </s:if>
                        <s:else>
                            <s:if test="D2.equalsIgnoreCase('02')">
                                <td align = "right" class="TD_SOTIEN">
                                    <input type="text" value="<s:property  value="D9" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"    onblur="autoEvaluate()"                               
                                           readonly="readonly"/>
                                </td>
                            </s:if>
                            <s:else>
                                <td align = "right" class="TD_SOTIEN">
                                    <input type="text" value="<s:property  value="D9" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"     onblur="autoEvaluate()"                              
                                </td>
                            </s:else>

                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D10" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"       onblur="autoEvaluate()"                            
                                       readonly="readonly"/>
                            </td>
                        </s:else>
                    </tr>
                </s:if>
                <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                    <tr height="22">      
                        <td align ="left" class= "<s:property value='FONTFORMAT'/> TD_NHOM">
                            <input type="text" style="text-align:center" value="<s:property  value="D1" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" readonly/>
                        </td>

                        <td align ="left" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                            <input type="text"  value="<s:property  value="TT_HIENTHI" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" readonly/>
                            <input type="hidden" value="<s:property  value="MA" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                            <input type="hidden" value="<s:property  value="CO_TONGHOP" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP" value="<s:property  value="CO_TONGHOP"/>"/> 
                            <input type="hidden" value="<s:property  value="D2" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>"/> 
                            <input type="hidden" value="<s:property  value="D11" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" value="<s:property  value="D11"/>"/> 
                            <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/> 

                        </td>

                        <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_SOTIEN">
                            <input type="text" value="<s:property  value="TEN" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly/>
                        </td>


                        <td align = "right" class="TD_GHICHU">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D2   <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   readonly/>
                        </td>
                        <s:if test="TT_HIENTHI.equalsIgnoreCase('B200115')">
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D9" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number51 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" onblur="autoEvaluate()"/>
                            </td>

                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D10" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number51 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" onblur="autoEvaluate()"                                  
                                       />
                            </td>
                        </s:if>
                        <s:else>
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D9" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" onblur="autoEvaluate()"/>
                            </td>
                            <s:if test="TT_HIENTHI.equalsIgnoreCase('D')">
                                <td align = "right" class="TD_SOTIEN">
                                    <input type="text" value="<s:property  value="D10" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"     onblur="autoEvaluate()"                                                                     
                                           />
                                </td>
                            </s:if>
                            <s:else>
                                <td align = "right" class="TD_SOTIEN">
                                    <input type="text" value="<s:property  value="D10" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"  onblur="autoEvaluate()"                                 
                                           />
                                </td>
                            </s:else>

                        </s:else>




                    </tr>
                </s:if>

            </s:iterator>
        </table>
        <p></p>          

        <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                   onCompleteTopics="completediv_ss" cssStyle="display: none"/>
    </s:form>
    <div id="luu_thanhcong"></div>
    <s:form action="KHOANTC001.action" id="paginationForm1">
        <s:iterator value="poscd" status="row">
            <s:hidden name="poscd[%{#row.index}]" />
        </s:iterator>
        <s:hidden name="ngay_bc" id="ngay_bc"/>        
        <sj:submit value="submit" id="idSubmit" name="idSubmit" targets="divExportReport" cssStyle="display: none" 
                   onBeforeTopics="batdauloaddata" onCompleteTopics="hoanthanhloaddata"/>
    </s:form>
    <div id="divBrowseRisk"></div>
    <!--        <script>
                autoEvaluate();
            </script>-->
</body>
</html>
