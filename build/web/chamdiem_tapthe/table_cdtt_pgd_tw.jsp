<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>  
        <script src="chamdiem_tapthe/js/chamdiem_tapthe.js"></script>  
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
//                $('.number2').number(true, 0);
                $(".TD_TEN_KH").css({"width": "20%"});
                $(".TD_DONVITINH").css({"width": "50px"});
                $(".TD_SOLUONG").css({"width": "5%"});
                $(".TD_NGUYENGIA").css({"width": "5%"});
                $(".TD_THUTU").css({"width": "3%"});
                $(".TD_CHITIEU").css({"width": "20%"});

                $(".TEN_KH").css({"width": "100%"});
//                $(".TEN_KH").css({"height": "100%"});
                $(".hideColumn").hide();
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>

    </head>
    <style>

    </style>
    <body>
        <s:form id="id_sv_%{khoa_cdtt}" action="SAVE_%{khoa_cdtt}" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                PL05 - tw 
            </div>
            <s:hidden name="khoa_cdtt"/>
            <s:hidden name="tt_cdtt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>            
                <table border="1" width="4000px" class="editDelete" id="CHAMDIEMTT_001A" align="center">
                    <tr height="23">
                        <th rowspan="2" class="TD_THUTU">TT</th>
                        <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>                                         
                        <th rowspan="2" class="TD_SOLUONG">Mã</th> 
                        <th colspan="2" class="TD_SOLUONG">PGD</th> 
                        <th colspan="3" >Phòng CMNV</th> 
                        <th colspan="3" >TT Hội đồng</th>                                                                    
                    </tr>  
                    <tr height="22">
                        <th class="TD_SOLUONG">Tỷ lệ</th>
                        <th class="TD_SOLUONG">Điểm</th>
                        <th class="TD_SOLUONG">Tỷ lệ</th>
                        <th class="TD_SOLUONG">Điểm</th>
                        <th class="TD_CHITIEU">Ghi chú</th>                          
                        <th class="TD_SOLUONG">Tỷ lệ</th>
                        <th class="TD_SOLUONG">Điểm</th>  
                        <th class="TD_CHITIEU">Ghi chú</th>
                    </tr> 
                    
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                        <tr height="16">                      
                            <td  align="right" class="TD_SOLUONG">    
                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                                                    
                                <input type="hidden" value="<s:property  value="THUTU" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/> 
                                <input type="hidden" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/> 
                                <input type="hidden" value="<s:property  value="D15" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/> 
                               <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/> 
                                <input type="hidden" value="<s:property  value="CO_TONGHOP" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/> 
                                <input type="hidden" value="<s:property  value="MA" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/> 
                            </td>
                            <td  align="left" class="TD_TEN_KH">                              
                                <input type="text" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="D TEN_KH break" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                            <s:if test="Grade.equalsIgnoreCase('2')">
                                <td align="center" class="TD_SOLUONG">
                                    <a href="javascript:hienthichitiet('<s:property value="MA"/>',2)" class="SOKU linkKh">
                                        <s:property value='MA'/>
                                    </a>
                                </td>
                            </s:if>  
                            <s:else>
                                    <td align="center" class="TD_SOLUONG">
                                        <input type="text" value="<s:property  value="MA" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                    </td>  
                            </s:else>   
                            <td align = "right" class="TD_SOLUONG">
                                        <input type="text" id="D9_<s:property value='MA'/>" value="<s:property value='D9'/>" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" 
                                               onblur="if (this.value == '') {
                                                           this.value = 0
                                                       }
                                                       ;"   class="number2 TEN_KH" readonly="readonly"/>
                                    </td>

                                    <td align = "right" class="TD_SOLUONG">
                                        <input type="text" id="D10_<s:property value='MA'/>" value="<s:property value='D10'/>" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                               onblur="if (this.value == '') {
                                                           this.value = 0
                                                       }
                                                       ;"   class="number2 TEN_KH" readonly="readonly"/>
                                    </td>        
                            <s:if test="NHAPTAY.equalsIgnoreCase('N')">                                
                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" id="D11_<s:property value='MA'/>" value="<s:property value='D11'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;"   class="number2 TEN_KH" readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" id="D12_<s:property value='MA'/>" value="<s:property value='D12'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ; evaluateSum('CHAMDIEMTT_001A', 'D12')"   class="number2 TEN_KH" readonly="readonly"/>
                                </td>
                                <td  align="center" class="TD_CHITIEU"> 
                                    <input type="text" value="<s:property  value="D13" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>   
                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" id="D16_<s:property value='MA'/>" value="<s:property value='D16'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ; evaluateSum('CHAMDIEMTT_001A', 'D16')"   class="number2 TEN_KH" readonly="readonly"/>
                                </td>
                                
                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" id="D17_<s:property value='MA'/>" value="<s:property value='D17'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ; evaluateSum('CHAMDIEMTT_001A', 'D17')"   class="number2 TEN_KH" readonly="readonly"/>
                                </td>
                                
                                <td  align="center" class="TD_CHITIEU"> 
                                    <input type="text" value="<s:property  value="D18" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>   
                               
                            </s:if>   
                            <s:else>
                                <s:if test="RULEUSER.equalsIgnoreCase('9')">                                    
                                    <td align = "right" class="TD_SOLUONG">
                                        <input type="text" id="D11_<s:property value='MA'/>" value="<s:property value='D11'/>" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                               onblur="if (this.value == '') {
                                                           this.value = 0
                                                       }
                                                       ;"   class="number2 TEN_KH" readonly="readonly"/>
                                    </td>
                                    <td align = "right" class="TD_SOLUONG">
                                        <input type="text" id="D12_<s:property value='MA'/>" value="<s:property value='D12'/>" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                               onblur="if (this.value == '') {
                                                           this.value = 0
                                                       }
                                                       ; evaluateSum('CHAMDIEMTT_001A', 'D12')"   class="number2 TEN_KH" readonly="readonly"/>
                                    </td>
                                    <td  align="center" class="TD_CHITIEU"> 
                                        <input type="text" value="<s:property  value="D13" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                    </td>   
                                    <td align = "right" class="TD_SOLUONG">
                                        <input type="text" id="D16_<s:property value='MA'/>" value="<s:property value='D16'/>" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" 
                                               onblur="if (this.value == '') {
                                                           this.value = 0
                                                       }
                                                       ; evaluateSum('CHAMDIEMTT_001A', 'D16')"   class="number2 TEN_KH"/>
                                    </td>

                                    <td align = "right" class="TD_SOLUONG">
                                        <input type="text" id="D17_<s:property value='MA'/>" value="<s:property value='D17'/>" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" 
                                               onblur="if (this.value == '') {
                                                           this.value = 0
                                                       }
                                                       ; evaluateSum('CHAMDIEMTT_001A', 'D17')"   class="number2 TEN_KH"/>
                                    </td>

                                    <td  align="center" class="TD_CHITIEU"> 
                                        <input type="text" value="<s:property  value="D18" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH" onfocus="this.select()" />                                  
                                    </td> 
                                </s:if>
                                <s:else>
                                     <td align = "right" class="TD_SOLUONG">
                                            <input type="text" id="D11_<s:property value='MA'/>" value="<s:property value='D11'/>" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                                   onblur="if (this.value == '') {
                                                               this.value = 0
                                                           }
                                                           ; evaluateSum('CHAMDIEMTT_001A', 'D11')"   class="number2 TEN_KH"/>
                                        </td>
                                        <td align = "right" class="TD_SOLUONG">
                                            <input type="text" id="D12_<s:property value='MA'/>" value="<s:property value='D12'/>" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                                   onblur="if (this.value == '') {
                                                               this.value = 0
                                                           }
                                                           ; evaluateSum('CHAMDIEMTT_001A', 'D12')"   class="number2 TEN_KH"/>
                                        </td>
                                        <td  align="center" class="TD_CHITIEU"> 
                                            <input type="text" value="<s:property  value="D13" />" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select()"/>                                  
                                        </td>   
                                        <td align = "right" class="TD_SOLUONG">
                                            <input type="text" id="D16_<s:property value='MA'/>" value="<s:property value='D16'/>" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" 
                                                   onblur="if (this.value == '') {
                                                               this.value = 0
                                                           }
                                                           ; evaluateSum('CHAMDIEMTT_001A', 'D16')"   class="number2 TEN_KH" readonly="readonly"/>
                                        </td>

                                        <td align = "right" class="TD_SOLUONG">
                                            <input type="text" id="D17_<s:property value='MA'/>" value="<s:property value='D17'/>" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" 
                                                   onblur="if (this.value == '') {
                                                               this.value = 0
                                                           }
                                                           ; evaluateSum('CHAMDIEMTT_001A', 'D17')"   class="number2 TEN_KH" readonly="readonly"/>
                                        </td>

                                        <td  align="center" class="TD_CHITIEU"> 
                                            <input type="text" value="<s:property  value="D18" />" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                        </td>    
                                </s:else>    
                                     
                            </s:else>    
                              
                            <td class="hideColumn"><input type="text" value="<s:property value='D3'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                            <td class="hideColumn"><input type="text" value="<s:property value='D15'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                            <td class="hideColumn"><input type="text" value="<s:property value='MA'/>" name="MA_CT" class="MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                        </tr>                    

                    </s:iterator>                                
            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>        
    </body>
</html>
