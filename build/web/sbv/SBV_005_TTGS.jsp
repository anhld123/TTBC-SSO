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
                var arrCot = [".D3"]; 
                // Tinh cho dong 1
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(0).val( parseFloat($(arrCot[i]).eq(1).val()) + 
                            parseFloat($(arrCot[i]).eq(2).val()) + parseFloat($(arrCot[i]).eq(3).val()) + 
                             parseFloat($(arrCot[i]).eq(4).val()) + 
                            parseFloat($(arrCot[i]).eq(5).val()) );
                }                
                // Tinh cho dong 2
                for (i = 0; i < arrCot.length; i++) { 
                    //Tinh tong cho dong "Các T/c Chính trị xã hội"
                    $(arrCot[i]).eq(6).val( parseFloat($(arrCot[i]).eq(7).val()) + 
                            parseFloat($(arrCot[i]).eq(8).val()) + parseFloat($(arrCot[i]).eq(9).val()) + 
                             parseFloat($(arrCot[i]).eq(10).val()) + 
                            parseFloat($(arrCot[i]).eq(11).val()) + parseFloat($(arrCot[i]).eq(12).val()));
                }                
                
            };
                </script>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_sbv}" action="SAVE_%{khoa_sbv}" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                KẾ HOẠCH XỬ LÝ NỢ XẤU XÁC ĐỊNH THEO NGHỊ QUYẾT SỐ 42/2017/QH14 TẠI TCTD
            </div>
            <s:hidden name="khoa_sbv"/>
            <div id="divDonvitinh">
                Đơn vị tính: triệu đồng, %
            </div>
            <table border="1" class="editDelete" style="width: 80%"  id="tablems05ttgs" align="center">
                <tr>
                    <th class="TD_THUTU">TT</th>
                    <th class="TD_CHITIEU">Chỉ tiêu</th>
                    <th class="TD_DONVITINH"> Nợ xấu xác định theo Nghị quyết số 42 dự kiến tại TCTD</th>  
                </tr>                
                <tr>         
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_SOLUONG">(1)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(2)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(3)</th>                    
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr height="22">  
                        <td  align="right" class="TD_TEN_KH">    
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
                        </td>
                        <td  align="right" class="TD_TEN_KH">    
                            <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>                        

                        <td align = "right" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>                        
                    </tr>
                    </s:if>
                    
                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr height="22">                              
                        <td  align="right" class="TD_TEN_KH">    
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
                            
                        </td>
                        <td  align="right" class="TD_TEN_KH">    
                            <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>                       
                                                
                        <td align = "right" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                                   
                        </td>                        
                    </tr>
                    </s:if>
                    
                </s:iterator>
            </table>
            <sj:submit id="%{khoa_sbv}_save" name="%{khoa_sbv}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initTable();
        </script>
    </body>
</html>
