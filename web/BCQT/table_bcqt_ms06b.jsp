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
                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TD_DONVITINH").css({"width": "50px"});
                $(".TD_SOLUONG").css({"width": "50px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "190px"});
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
            function autoEvaluate(){
//                alert('vao doClick');
                var arrCot = [".D1",".D2",".D3",".D4",".D5",
                    ".D6",".D7",".D8",".D9",
                    ".D10",".D11",".D12"]; //Luu cac cot cua du lieu can tinh toan                  
                // Tinh cho donG A200
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(2).val( parseFloat($(arrCot[i]).eq(3).val()) + 
                            parseFloat($(arrCot[i]).eq(4).val()) + parseFloat($(arrCot[i]).eq(5).val()) + 
                             parseFloat($(arrCot[i]).eq(6).val()) + 
                            parseFloat($(arrCot[i]).eq(7).val()));
                }
                // Tinh cho dong A300
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(8).val( parseFloat($(arrCot[i]).eq(9).val()) + 
                            parseFloat($(arrCot[i]).eq(10).val()) + parseFloat($(arrCot[i]).eq(11).val()) + 
                             parseFloat($(arrCot[i]).eq(12).val()));
                }
                // Tinh cho dong B200
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(16).val( parseFloat($(arrCot[i]).eq(17).val()) + 
                            parseFloat($(arrCot[i]).eq(18).val()) + parseFloat($(arrCot[i]).eq(19).val()) + 
                             parseFloat($(arrCot[i]).eq(20).val()));
                }
                // Tinh cho dong B300
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(21).val( parseFloat($(arrCot[i]).eq(22).val()) + 
                            parseFloat($(arrCot[i]).eq(23).val()) + parseFloat($(arrCot[i]).eq(24).val()) + 
                             parseFloat($(arrCot[i]).eq(25).val()));
                }
                  
                // Tinh cho dong C000
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
//                    $(arrCot[i]).eq(27).val( parseFloat($(arrCot[i]).eq(28).val()) + 
//                            parseFloat($(arrCot[i]).eq(29).val()) );
                    $(arrCot[i]).eq(13).val( parseFloat($(arrCot[i]).eq(1).val()) + 
                            parseFloat($(arrCot[i]).eq(2).val()) - parseFloat($(arrCot[i]).eq(8).val()))
                    $(arrCot[i]).eq(26).val( parseFloat($(arrCot[i]).eq(15).val()) + 
                            parseFloat($(arrCot[i]).eq(16).val()) - parseFloat($(arrCot[i]).eq(21).val()))
                    
                    $(arrCot[i]).eq(28).val( parseFloat($(arrCot[i]).eq(1).val()) + 
                             - parseFloat($(arrCot[i]).eq(15).val()))
                     $(arrCot[i]).eq(29).val( parseFloat($(arrCot[i]).eq(13).val()) + 
                             - parseFloat($(arrCot[i]).eq(26).val()))
                }
                
            }
            
            function hienthichitiet1() {
                var poscd1 = getposfromtreecheck();
                var ht1 = screen.availHeight - 350;
                var wt1 = 1050;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 10;

                var ngay_bc = $("#ngay_bc_DATE").val();
                var khoa_bcqt = $("#khoa_bcqt").val();

                var url = "getBcqt06B_1.action?bcqt=" + "&ngay_bc_DATE=" + ngay_bc +
                         "&khoa_bcqt=" + khoa_bcqt+"1" +
                         "&poscd=" + poscd1;
                //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
                var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        }       
        
        function hienthichitiet2() {
            var poscd1 = getposfromtreecheck();
                var ht1 = screen.availHeight - 350;
                var wt1 = 1050;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 10;

                var ngay_bc = $("#ngay_bc_DATE").val();
                var khoa_bcqt = $("#khoa_bcqt").val();

                var url = "getBcqt06B_2.action?bcqt=" + "&ngay_bc_DATE=" + ngay_bc +
                         "&khoa_bcqt=" + khoa_bcqt+"2"+
                          "&poscd=" + poscd1;
                //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
                var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

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
                BÁO CÁO TÌNH HÌNH TÀI SẢN CỐ ĐỊNH VÔ HÌNH
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablems06a" align="center">
                <tr>
                    <th rowspan="2"  class="TD_THUTU">Mã chỉ tiêu</th>
                    <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>
                    <th colspan="3">VỐN TW</th>    
                    <th colspan="3">VỐN ĐỊA PHƯƠNG, CHO, TẶNG; VỐN KHÁC</th> 
                    <!--<th colspan="3">TỔNG</th>--> 
                   
                </tr>
                <tr>                                  
                    <th   class="TD_NGUYENGIA">QSD đất</th>
                    <th   class="TD_NGUYENGIA">Phần mềm tin học</th>
                    <th   class="TD_NGUYENGIA">Khác</th>
                    <th   class="TD_NGUYENGIA">QSD đất</th>
                    <th   class="TD_NGUYENGIA">Phần mềm tin học</th>
                    <th   class="TD_NGUYENGIA">Khác</th>  
<!--                    <th   class="TD_NGUYENGIA">QSD đất</th>
                    <th   class="TD_NGUYENGIA">Phần mềm tin học</th>
                    <th   class="TD_NGUYENGIA">Khác</th> -->
                </tr>
                <tr>         
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THUTU">A</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THUTU">B</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">1</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">2</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">3</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">4</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">5</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">6</th>
<!--                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">7</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">8</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">9</th>-->

                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr height="22">   
                        <td  align="right" class="TD_TEN_KH">    
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"  onblur="autoEvaluate()"  readonly="readonly" />                                  
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                        </td>
                        <td  align="right" class="TD_TEN_KH">    
                            <input type="text" value="<s:property  value="TEN" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"  onfocus="this.select()"    readonly="readonly" />                                  
                        </td>              
                        <td align = "right" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="D1" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
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
<!--                        <td align = "right" class="TD_NGUYENGIA">
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
                        </td>                                                                    -->
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
                        <td  align="right" class="TD_SOLUONG">    
                        <input type="text" value="<s:property  value="D1" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 number2 TEN_KH" onfocus="this.select()"   onblur="autoEvaluate()"/>                                  
                        <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
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
                                   onblur="autoEvaluate()"/>
                        </td>
<!--                        <td align = "right" class="TD_NGUYENGIA">
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
                        </td>-->
                                                                   
                    </tr>
                    </s:if>
                    
                </s:iterator>
            </table>
            <p></p>
            <!--<a href="javascript:hienthichitiet('test' )" >-->
                <div align = "left" id="divTitle">
                    <a id="myLink" href="#" onclick="javascript:hienthichitiet1();return false;">&nbsp;&nbsp;&nbsp;1.1. Điều chuyển nội bộ TSCĐ với TW, NHCSXH khác tỉnh, TP hoặc ngược lại.</a>
                    <p></p>
                <a align = "left" id="myLink" href="#" onclick="javascript:hienthichitiet2();return false;">&nbsp;&nbsp;&nbsp;1.2. Tăng khác, giảm khác nguyên giá TSCĐ, hao mòn TSCĐ vô hình.        </a>
                </div>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
