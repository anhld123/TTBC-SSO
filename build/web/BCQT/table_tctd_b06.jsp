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
                var arrCot = [".D2",
                    ".D3",".D4",".D5"]; //Luu cac cot cua du lieu can tinh toan                                 
                
                // Tinh cho dong 1
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(2).val( parseFloat($(arrCot[i]).eq(3).val()) + 
                            parseFloat($(arrCot[i]).eq(4).val()));
                    
                    $(arrCot[i]).eq(5).val( parseFloat($(arrCot[i]).eq(3).val()) + 
                            parseFloat($(arrCot[i]).eq(4).val()));
                    
                    $(arrCot[i]).eq(6).val( parseFloat($(arrCot[i]).eq(1).val()) - 
                            parseFloat($(arrCot[i]).eq(5).val()));
                    $(arrCot[i]).eq(8).val( parseFloat($(arrCot[i]).eq(6).val()) + 
                            parseFloat($(arrCot[i]).eq(7).val()));
                }      
                 for(var i=0; i<9; i++){
                    //5=7+9+11+13
                    $(".D5").eq(i).val(parseFloat($(".D2").eq(i).val()) +
                            parseFloat($(".D3").eq(i).val())+parseFloat($(".D4").eq(i).val()) );
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
                Rủi ro tiền tệ
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Triệu đồng
            </div>
            <table border="1" class="editDelete" id="tableb05" align="center">                
                <tr>                                  
                    <th   class="TD_CHITIEU">Chỉ tiêu</th>
                    <th   class="TD_NGUYENGIA">EUR được quy đổi</th>
                    <th   class="TD_NGUYENGIA">USD được quy đổi</th>
                    <th   class="TD_NGUYENGIA">Các ngoại hối khác được quy đổi</th>
                    <th   class="TD_NGUYENGIA">Tổng</th>                  
                </tr>
                <tr>         
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_CHITIEU">(1)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(2)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(3)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(4)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(5)</th>
                    </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr height="22">  
                        <td  align="right" class="TD_TEN_KH">    
                            <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                        </td>

                        <td align = "right" class="TD_SOLUONG">
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
                      
                    </tr>
                    </s:if>
                    
                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr height="22">                              
                        <td align="left" class="TD_TEN_KH">
                             <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />  
                        </td>
                                                
                        <td align = "right" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                   />
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"
                                   readonly="readonly"/>
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
