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
                $('input.number3').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number3').number(true, 3);
                $('input.number5').css({"text-align": "right"});
                $('.number5').number(true, 5);
//                $('.number2').number(true, 0);
                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TD_DONVITINH").css({"width": "50px"});
                $(".TD_SOLUONG").css({"width": "5%"});
                $(".TD_NGUYENGIA").css({"width": "8%"});
                $(".TD_THUTU").css({"width": "2%"});
                $(".TD_CHITIEU").css({"width": "20%"});

                $(".TEN_KH").css({"width": "100%"});
//                $(".TEN_KH").css({"height": "100%"});
                $(".hideColumn").hide();
                if ('<s:property value="Grade"/>' == '3' && '<s:property value="RULEUSER"/>' != '9')
                {

            <s:iterator value="poscd" status="row">
//                        alert('<s:property/>');
                    evaluateSum_Mapgd('CHAMDIEMTT_001', 'D10', '<s:property/>');
                    evaluateSum_Mapgd('CHAMDIEMTT_001', 'D12', '<s:property/>');
                    evaluateSum_Mapgd('CHAMDIEMTT_001', 'D11', '<s:property/>');
            </s:iterator>

                }
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
        <s:form id="id_sv_%{khoa_cdtt}" action="SAVE_%{khoa_cdtt}_CMNV_TW" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>

            <div id="divTitle">  
                <font color="red">(Ban CMNV đánh giá)</font>  - CHỈ TIÊU ĐÁNH GIÁ THÁNG - ĐỐI VỚI CHI NHÁNH NHCSXH TỈNH/THÀNH PHỐ  <font color="red">(Trạng thái: <s:property  value="TT_DUYET"/>)</font>      
            </div>    
            <s:hidden name="khoa_cdtt"/>
            <s:hidden name="tt_cdtt"/>
            <div>
                <p style="text-align: left;padding-left: 30px;">[x/y]: Chỉ tiêu được phép loại trừ, x: số được loại trừ, y: tổng số</p>            
                <div id="divDonvitinh">                
                    Đơn vị tính: Triệu đồng, số điểm, tỷ lệ %, số lỗi
                </div>  
            </div> 

            <!--Ban chuyên môn nghiệp vụ vào duyệt-->

            <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                <tr height="23">
                    <th rowspan="2" class="TD_THUTU">Đơn vị</th>
                    <th rowspan="2" class="TD_THUTU">TT</th>
                    <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>    
                    <th rowspan="2"  class="TD_SOLUONG">Điểm tối đa</th>                                            
                    <th colspan="2" class="TD_NGUYENGIA">Chi nhánh</th>  
                    <th colspan="3" class="TD_NGUYENGIA">Ban CMNV</th>                          
                </tr>                
                <tr height="21">  
                    <th  class="TD_SOLUONG">Số Thực hiện, Điểm, Tỷ lệ %, Số lỗi</th>   
                    <th  class="TD_SOLUONG">Điểm</th>  
                    <th  class="TD_SOLUONG">Số kế hoạch, Điểm, Tỷ lệ %, Số lỗi</th>   
                    <th  class="TD_SOLUONG">Điểm</th> 
                    <th  class="TD_SOLUONG">Ghi chú</th> 
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                    <tr height="16" class="<s:property  value="D30"/>"> 
                        <s:if test="D8.equalsIgnoreCase('1')">
                            <td  align="left" class="TD_SOLUONG">                              
                                <input style="color: red; font-weight: bold;"  type="text" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            </td> 
                        </s:if>    
                        <s:else>
                            <td></td>
                        </s:else>    
                        <td  align="center" class="TD_TEN_KH">    
                            <a href="javascript:hienthichitiet('<s:property value="MA"/>',2,'<s:property value='khoa_cdtt'/>')" class="SOKU linkKh">
                                <s:property value='TT_HIENTHI'/>
                            </a>
<!--                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                                                    -->
                            <input type="hidden" value="<s:property  value="THUTU" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/> 
                            <input type="hidden" value="<s:property  value="MA" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/> 
                            <input type="hidden" value="<s:property  value="MAPGD" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/> 
                            <input type="hidden" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/> 
                            <input type="hidden" value="<s:property  value="D15" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/> 
                            <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/> 
                            <input type="hidden" value="<s:property  value="CO_TONGHOP" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/> 
                        </td>
                        <td  align="left" class="TD_TEN_KH">                              
                            <input type="text" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="D TEN_KH break" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>                            
                        <td align="center" class="TD_SOLUONG hideColumn">
                            <a href="javascript:hienthichitiet('<s:property value="MA"/>',2,'<s:property value='khoa_cdtt'/>')" class="SOKU linkKh">
                                <s:property value='MA'/>
                            </a>
                        </td>                                                    

                        <td align="center" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property value='MA'/>"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <s:if test="!months.equalsIgnoreCase('03') && !months.equalsIgnoreCase('06')&&
                              !months.equalsIgnoreCase('09')&&!months.equalsIgnoreCase('12')">
                            <s:if test="NHAPTAY.equalsIgnoreCase('N')">                                                              
                                <td align = "right" class="TD_NGUYENGIA">                            
                                    <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;"  
                                           <s:if test="MA.equalsIgnoreCase('CDTT09')">
                                               class="number5 TEN_KH" 
                                           </s:if>     
                                           <s:else> 
                                               class="number2 TEN_KH"
                                           </s:else>                                        
                                           />
                                </td>  
                                <td align = "right" class="TD_NGUYENGIA">                            
                                    <input type="text" id="D10_<s:property value='MA'/>_<s:property value="MAPGD"/>" value="<s:property value='D10'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   isInputMark('D1_<s:property value='MA'/>', 'D10_<s:property value='MA'/>_<s:property value="MAPGD"/>');
                                                   evaluateSum_Mapgd('CHAMDIEMTT_001', 'D10', '<s:property value="MAPGD"/>')"   class="number2 TEN_KH" readonly="readonly"/>
                                </td>                                    
                                <td align = "right" class="TD_NGUYENGIA">                            
                                    <input type="text" id="D11_<s:property value='MA'/>_<s:property value="MAPGD"/>" value="<s:property value='D11'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum_Mapgd('CHAMDIEMTT_001', 'D11', '<s:property value="MAPGD"/>');"   
                                           <s:if test="MA.equalsIgnoreCase('CDTT09')">
                                               class="number5 TEN_KH" 
                                           </s:if>     
                                           <s:else> 
                                               class="TEN_KH number3"
                                           </s:else> 
                                           <s:if test="!MA.equalsIgnoreCase('CDTT04')&&!MA.equalsIgnoreCase('CDTT05')&&!MA.equalsIgnoreCase('CDTT06')&&!MA.equalsIgnoreCase('CDTT07')
                                                 &&!MA.equalsIgnoreCase('CDTT08')&&!MA.equalsIgnoreCase('CDTT120102')">readonly="readonly"</s:if>/>
                                    </td> 
                                    <td align = "right" class="TD_NGUYENGIA">                            
                                        <input type="text" id="D12_<s:property value='MA'/>_<s:property value="MAPGD"/>" value="<s:property value='D12'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   isInputMark('D1_<s:property value='MA'/>', 'D12_<s:property value='MA'/>_<s:property value="MAPGD"/>');
                                                   evaluateSum_Mapgd('CHAMDIEMTT_001', 'D12', '<s:property value="MAPGD"/>')"   class="number2 TEN_KH" readonly="readonly"/>
                                </td>
                                <td align="center" class="TD_CHITIEU">
                                    <input type="text" value="<s:property  value="D13" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select()"  
                                           <%--<s:if test="!MA.equalsIgnoreCase('CDTT01')&&!MA.equalsIgnoreCase('CDTT02')&&!MA.equalsIgnoreCase('CDTT03')">readonly="readonly"</s:if>--%>
                                           />                                  
                                </td>                                    
                            </s:if>
                            <s:else>                                                                                                
                                <td align = "right" class="TD_NGUYENGIA">                            
                                    <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;"   
                                           <s:if test="MA.equalsIgnoreCase('CDTT09')">
                                               class="number5 TEN_KH" 
                                           </s:if>     
                                           <s:else> 
                                               class="number2 TEN_KH"
                                           </s:else> readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">                            
                                    <input type="text" id="D10_<s:property value='MA'/>_<s:property value="MAPGD"/>" value="<s:property value='D10'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   isInputMark('D1_<s:property value='MA'/>', 'D10_<s:property value='MA'/>');
                                                   evaluateSum_Mapgd('CHAMDIEMTT_001', 'D10', '<s:property value="MAPGD"/>')"   class="number2 TEN_KH" readonly="readonly"/>
                                </td>                               

                                <td align = "right" class="TD_NGUYENGIA">                            
                                    <input type="text" id="D11_<s:property value='MA'/>_<s:property value="MAPGD"/>" value="<s:property value='D11'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum_Mapgd('CHAMDIEMTT_001', 'D11', '<s:property value="MAPGD"/>');"   
                                           <s:if test="MA.equalsIgnoreCase('CDTT09')">
                                               class="number5 TEN_KH" 
                                           </s:if>     
                                           <s:else> 
                                               class="TEN_KH number3"
                                           </s:else>   
                                           <%--<s:if test="MA.equalsIgnoreCase('CDTT11')">readonly="readonly"</s:if>--%>
                                           <s:if test="MA.equalsIgnoreCase('CDTT10')||MA.equalsIgnoreCase('CDTT11')
                                                 ||MA.equalsIgnoreCase('CDTT1101')||MA.equalsIgnoreCase('CDTT1102')
                                                 ||MA.equalsIgnoreCase('CDTT110201')||MA.equalsIgnoreCase('CDTT110202')
                                                 ||MA.equalsIgnoreCase('CDTT13')||MA.equalsIgnoreCase('CDTT14')
                                                 ||MA.equalsIgnoreCase('CDTT1401')||MA.equalsIgnoreCase('CDTT1402')
                                                 ||MA.equalsIgnoreCase('CDTT1403')||MA.equalsIgnoreCase('CDTT99')">readonly="readonly"</s:if>

                                    </td> 
                                    <td align = "right" class="TD_NGUYENGIA">                            
                                        <input type="text" id="D12_<s:property value='MA'/>_<s:property value="MAPGD"/>" value="<s:property value='D12'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   isInputMark('D1_<s:property value='MA'/>', 'D12_<s:property value='MA'/>_<s:property value="MAPGD"/>');
                                                   evaluateSum_Mapgd('CHAMDIEMTT_001', 'D12', '<s:property value="MAPGD"/>')"   class="number2 TEN_KH"
                                           <s:if test="!MA.equalsIgnoreCase('CDTT1301')&&!MA.equalsIgnoreCase('CDTT1302')
                                                 &&!MA.equalsIgnoreCase('CDTT1303')&&!MA.equalsIgnoreCase('CDTT1304')">readonly="readonly"</s:if>
                                               />
                                    </td>
                                    <td align="center" class="TD_CHITIEU">
                                        <input type="text" value="<s:property  value="D13" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select()"/>                                  
                                </td>                                    
                            </s:else> 
                        </s:if>
                        <!--bo sung ngày 27/03/2025-->
                        <s:else>
                            <td align = "right" class="TD_NGUYENGIA">                            
                                <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;"   
                                       <s:if test="MA.equalsIgnoreCase('CDTT09')">
                                           class="number5 TEN_KH" 
                                       </s:if>     
                                       <s:else> 
                                           class="number2 TEN_KH"
                                       </s:else>  readonly
                                       />
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">                            
                                <input type="text" id="D10_<s:property value='MA'/>_<s:property value="MAPGD"/>" value="<s:property value='D10'/>" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               isInputMark('D1_<s:property value='MA'/>', 'D10_<s:property value='MA'/>');
                                               evaluateSum_Mapgd('CHAMDIEMTT_001', 'D10', '<s:property value="MAPGD"/>')"   class="number2 TEN_KH" 
                                       readonly/>
                            </td>                               

                            <td align = "right" class="TD_NGUYENGIA">                            
                                <input type="text" id="D11_<s:property value='MA'/>_<s:property value="MAPGD"/>" value="<s:property value='D11'/>" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               evaluateSum_Mapgd('CHAMDIEMTT_001', 'D11', '<s:property value="MAPGD"/>');"   
                                       <s:if test="MA.equalsIgnoreCase('CDTT09')">
                                           class="number5 TEN_KH" 
                                       </s:if>     
                                       <s:else> 
                                           class="TEN_KH number"
                                       </s:else>   
                                       <s:if test="!MA.equalsIgnoreCase('CDTT01')&&!MA.equalsIgnoreCase('CDTT02')
                                             && !MA.equalsIgnoreCase('CDTT00')&&!MA.equalsIgnoreCase('CDTT02A')
                                             &&!MA.equalsIgnoreCase('CDTT03')&&!MA.equalsIgnoreCase('CDTT04')
                                             &&!MA.equalsIgnoreCase('CDTT05')&&!MA.equalsIgnoreCase('CDTT06')
                                             &&!MA.equalsIgnoreCase('CDTT06A')
                                             &&!MA.equalsIgnoreCase('CDTT07')&&!MA.equalsIgnoreCase('CDTT08')
                                             &&!MA.equalsIgnoreCase('CDTT09')&&!MA.equalsIgnoreCase('CDTT140102')&&!MA.equalsIgnoreCase('CDTT140201')
                                             &&!MA.equalsIgnoreCase('CDTT140202')&&!MA.equalsIgnoreCase('CDTT140301')
                                             &&!MA.equalsIgnoreCase('CDTT140302')&&!MA.equalsIgnoreCase('CDTT1301')
                                             &&!MA.equalsIgnoreCase('CDTT1302')&&!MA.equalsIgnoreCase('CDTT1303')
                                             &&!MA.equalsIgnoreCase('CDTT1304')&&!MA.equalsIgnoreCase('CDTT11020301')
                                             &&!MA.equalsIgnoreCase('CDTT11020302')&&!MA.equalsIgnoreCase('CDTT11020303')
                                             &&!MA.equalsIgnoreCase('CDTT11020304')&&!MA.equalsIgnoreCase('CDTT11020401')
                                             &&!MA.equalsIgnoreCase('CDTT11020402')&&!MA.equalsIgnoreCase('CDTT11020101')
                                             &&!MA.equalsIgnoreCase('CDTT11020102')
                                             &&!MA.equalsIgnoreCase('CDTT11020103')&&!MA.equalsIgnoreCase('CDTT11020104')
                                             &&!MA.equalsIgnoreCase('CDTT11020105')&&!MA.equalsIgnoreCase('CDTT11020106')
                                             &&!MA.equalsIgnoreCase('CDTT11020201')
                                             &&!MA.equalsIgnoreCase('CDTT11020202')">readonly="readonly"</s:if>
                                           />
                                </td> 
                                <td align = "right" class="TD_NGUYENGIA">                            
                                    <input type="text" id="D12_<s:property value='MA'/>_<s:property value="MAPGD"/>" 
                                       value="<s:property value='D12'/>" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               isInputMark('D1_<s:property value='MA'/>', 'D12_<s:property value='MA'/>_<s:property value="MAPGD"/>');
                                               evaluateSum_Mapgd('CHAMDIEMTT_001', 'D12', '<s:property value="MAPGD"/>')"  
                                       class="number2 TEN_KH"
                                       <s:if test="!MA.equalsIgnoreCase('CDTT140102')&&!MA.equalsIgnoreCase('CDTT140201')
                                             &&!MA.equalsIgnoreCase('CDTT140202')&&!MA.equalsIgnoreCase('CDTT140301')
                                             &&!MA.equalsIgnoreCase('CDTT140302')&&!MA.equalsIgnoreCase('CDTT1301')
                                             &&!MA.equalsIgnoreCase('CDTT1302')&&!MA.equalsIgnoreCase('CDTT1303')
                                             &&!MA.equalsIgnoreCase('CDTT1304')&&!MA.equalsIgnoreCase('CDTT110203')
                                             &&!MA.equalsIgnoreCase('CDTT110204')&&!MA.equalsIgnoreCase('CDTT11020101')&&!MA.equalsIgnoreCase('CDTT11020102')
                                             &&!MA.equalsIgnoreCase('CDTT11020103')&&!MA.equalsIgnoreCase('CDTT11020104')
                                             &&!MA.equalsIgnoreCase('CDTT11020105')&&!MA.equalsIgnoreCase('CDTT11020106')
                                             &&!MA.equalsIgnoreCase('CDTT11020201')
                                             &&!MA.equalsIgnoreCase('CDTT11020202')">readonly="readonly"</s:if>
                                           />
                                </td>
                                <td align="center" class="TD_CHITIEU">
                                    <input type="text" value="<s:property  value="D13" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select()"/>                                  
                            </td> 
                        </s:else>
                        <td class="hideColumn"><input type="text" value="<s:property value='D3'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                        <td class="hideColumn"><input type="text" value="<s:property value='D15'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                        <td class="hideColumn"><input type="text" value="<s:property value='MA'/>" name="MA_CT" class="MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                    </tr>                    

                </s:iterator>
            </table>

            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>        
    </body>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            document.querySelectorAll("input[id^='D12_']").forEach(function (input) {
                if (input.value.trim() === '') {
                    input.value = 0;

                    // Lấy lại các phần từ ID để gọi đúng hàm
                    const parts = input.id.split('_'); // vd: ["D12", "CDTT1234", "PGD01"]
                    const ma = parts[1];
                    const mapgd = parts[2];

                    isInputMark('D1_' + ma, input.id);
                    evaluateSum_Mapgd('CHAMDIEMTT_001', 'D12', mapgd);
                }
            });
        });
    </script>

</html>
