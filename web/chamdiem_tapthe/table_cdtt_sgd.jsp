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
                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TD_DONVITINH").css({"width": "50px"});
                $(".TD_SOLUONG").css({"width": "5%"});
                $(".TD_NGUYENGIA").css({"width": "6%"});
                $(".TD_THUTU").css({"width": "3%"});
                $(".TD_CHITIEU").css({"width": "20%"});

                $(".TEN_KH").css({"width": "100%"});
//                $(".TEN_KH").css({"height": "100%"});
                $(".hideColumn").hide();
                if ('<s:property value="Grade"/>' == '1' && '<s:property value="RULEUSER"/>' != '9')
                {
                    evaluateSum('CHAMDIEMTT_001', 'D10');
                    evaluateSum('CHAMDIEMTT_001', 'D12');
//                    evaluateSum('CHAMDIEMTT_001', 'D9');
//                    evaluateSum('CHAMDIEMTT_001', 'D4');
//                    evaluateSum('CHAMDIEMTT_001', 'D5');
                } else if ('<s:property value="Grade"/>' == '1')
                {
                    evaluateSum('CHAMDIEMTT_001', 'D10');
                    evaluateSum('CHAMDIEMTT_001', 'D12');
                } else if ('<s:property value="Grade"/>' == '3')
                {
//                    evaluateSum('CHAMDIEMTT_001', 'D12');                    
                    evaluateSum('CHAMDIEMTT_001', 'D10');
                    evaluateSum('CHAMDIEMTT_001', 'D12');
                }
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>

        <!--        <script>
                    function hienthichitiet(ma, stt) {
                        var ht1 = screen.availHeight - 360;
                        var wt1 = 900;
                        var left1 = (screen.width / 2) - (wt1 / 2);
                        var top1 = 100;
                        var ngay_bc = $("#ngay_bc_DATE").val();
                        var khoa_cdtt = $("#khoa_cdtt").val();
                        var url = "ChitietChamdiem.action?MACT=" + ma + "&ngay_bc=" + ngay_bc +
                                "&khoa_cdtt=" + khoa_cdtt + "&addedit=" + stt;
                        popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                    }
                </script>-->

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
                ĐÁNH GIÁ, XẾP LOẠI MỨC ĐỘ HTNV TẬP THỂ SỞ GIAO DỊCH <font color="red">(Trạng thái: <s:property  value="TT_DUYET"/>)</font> 
            </div>
            <s:hidden name="khoa_cdtt"/>
            <s:hidden name="tt_cdtt"/>
            <div id="divDonvitinh">
                <!--Đơn vị tính: Đồng-->
            </div>  
            <!--Đơn vị tự chấm-->
            <s:if test="Grade.equalsIgnoreCase('1')">
                <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                    <tr height="23">
                        <th rowspan="2" class="TD_THUTU">TT</th>
                        <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>                                         
                        <th rowspan="2" class="TD_SOLUONG">Mã</th>  
                        <th rowspan="2" class="TD_SOLUONG">Điểm tối đa</th>  
                        <th rowspan="2" class="TD_SOLUONG">% điểm</th>                                                  
                        <th colspan="3" class="TD_SOLUONG">Đơn vị tự chấm</th> 
                        <th colspan="3" class="TD_SOLUONG">Lãnh đạo phụ trách chấm</th> 
                    </tr>                
                    <tr>
                        <th class="TD_SOLUONG">Số kế hoạch</th>  
                        <th class="TD_SOLUONG">Số thực hiện, thực hiện, tỷ lệ %, số lỗi</th>                           
                        <th class="TD_NGUYENGIA">Điểm</th>  
                        <th class="TD_SOLUONG">Số kế hoạch, thực hiện, tỷ lệ %, số lỗi</th>                           
                        <th class="TD_NGUYENGIA">Điểm</th>                          
                        <th class="TD_CHITIEU">Ý kiến của lãnh đạo phụ trách</th> 
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                        <tr height="16">                      
                            <td  align="right" class="TD_TEN_KH">    
                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                                                    
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
                            <td align="center" class="TD_SOLUONG">
                                        <!--<input type="text" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />-->                                  
                                <a href="javascript:hienthichitiet('<s:property value="MA"/>',2,'<s:property value='khoa_cdtt'/>')" class="SOKU linkKh">
                                    <s:property  value="MA" />
                                </a>  
                            </td>                                

                            <td align="center" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property value='MA'/>"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                            <td  align="center" class="TD_SOLUONG"> 
                                <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>                            
                            <s:if test="NHAPTAY.equalsIgnoreCase('N')"> 
                                <td  align="center" class="TD_SOLUONG"> 
                                    <input type="text" value="<s:property  value="D4" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="number2  TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                </td>
                                <td  align="center" class="TD_SOLUONG"> 
                                    <input type="text" value="<s:property  value="D5" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number2  TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                </td> 

                                <td align = "right" class="TD_NGUYENGIA">                            
                                    <input type="text" id="D10_<s:property value='MA'/>" value="<s:property value='D10'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum('CHAMDIEMTT_001', 'D10');
                                                   isInputMark('D1_<s:property value='MA'/>', 'D10_<s:property value='MA'/>');"   class="number2 TEN_KH" readonly="readonly"/>
                                </td>  
                                <td  align="center" class="TD_SOLUONG"> 
                                    <input type="text" value="<s:property  value="D11" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="number2  TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                </td> 

                                <td align = "right" class="TD_NGUYENGIA">                            
                                    <input type="text" id="D12_<s:property value='MA'/>" value="<s:property value='D12'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum('CHAMDIEMTT_001', 'D12');isInputMark('D1_<s:property value='MA'/>', 'D12_<s:property value='MA'/>');"   class="number2 TEN_KH" readonly="readonly"/>
                                </td> 
                                <td  align="left" class="TD_TEN_KH">                              
                                    <input type="text" value="<s:property  value="D13" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                </td>  
                            </s:if>
                            <s:else>      
                                <td  align="center" class="TD_SOLUONG"> 
                                    <input type="text" value="<s:property  value="D4" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="number2  TEN_KH" onfocus="this.select()" 
                                           <s:if test="!MA.equalsIgnoreCase('CDTT0101') && !MA.equalsIgnoreCase('CDTT0102')"> readonly="readonly" </s:if>
                                               />                                  
                                    </td>
                                    <td  align="center" class="TD_SOLUONG"> 
                                        <input type="text" value="<s:property  value="D5" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number2  TEN_KH" onfocus="this.select()"
                                           <s:if test="MA.equalsIgnoreCase('CDTT0101') || MA.equalsIgnoreCase('CDTT0102')||MA.equalsIgnoreCase('CDTT0103')
                                                 ||MA.equalsIgnoreCase('CDTT0201')||MA.equalsIgnoreCase('CDTT0202')||MA.equalsIgnoreCase('CDTT0203')"> readonly="readonly" </s:if>/>                                  
                                    </td> 
                                    <td align = "right" class="TD_NGUYENGIA">                            
                                        <input type="text" id="D10_<s:property value='MA'/>" value="<s:property value='D10'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum('CHAMDIEMTT_001', 'D10'); isInputMark('D1_<s:property value='MA'/>', 'D10_<s:property value='MA'/>');"   class="number2 TEN_KH" 
                                           <s:if test="!MA.equalsIgnoreCase('CDTT0201')&& !MA.equalsIgnoreCase('CDTT0202')&&!MA.equalsIgnoreCase('CDTT0203')"> readonly="readonly" </s:if>/>
                                    </td> 
                                    <td  align="center" class="TD_SOLUONG"> 
                                        <input type="text" value="<s:property  value="D11" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="number2  TEN_KH" onfocus="this.select()"
                                           <s:if test="MA.equalsIgnoreCase('CDTT0201')||MA.equalsIgnoreCase('CDTT0202')||MA.equalsIgnoreCase('CDTT0203') ||
                                                 (Grade.equalsIgnoreCase('1') && (MA.equalsIgnoreCase('CDTT0105') || MA.equalsIgnoreCase('CDTT010501') || MA.equalsIgnoreCase('CDTT010502')||  MA.equalsIgnoreCase('CDTT010503')))                
                                                 "> readonly="readonly" </s:if>/>                                  
                                    </td> 

                                    <td align = "right" class="TD_NGUYENGIA">                            
                                        <input type="text" id="D12_<s:property value='MA'/>" value="<s:property value='D12'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum('CHAMDIEMTT_001', 'D12');isInputMark('D1_<s:property value='MA'/>', 'D12_<s:property value='MA'/>');"   class="number2 TEN_KH" 
                                                   <s:if test="!MA.equalsIgnoreCase('CDTT0201') && !MA.equalsIgnoreCase('CDTT0202') &&
                                                                 !MA.equalsIgnoreCase('CDTT0203')"> readonly="readonly" </s:if>/>
                                </td>
                                <td  align="left" class="TD_TEN_KH">                              
                                    <input type="text" value="<s:property  value="D13" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH " onfocus="this.select()"  />                                  
                                </td>
                            </s:else>                                    
                            <td class="hideColumn"><input type="text" value="<s:property value='D3'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                            <td class="hideColumn"><input type="text" value="<s:property value='D15'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                            <td class="hideColumn"><input type="text" value="<s:property value='MA'/>" name="MA_CT" class="MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                        </tr>                    

                    </s:iterator>
                </table>
            </s:if>  
            <s:if test="Grade.equalsIgnoreCase('3')">
                <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                    <tr height="23">
                        <th rowspan="2" class="TD_THUTU">TT</th>
                        <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>                                         
                        <!--<th rowspan="2" class="TD_SOLUONG">Mã</th>-->  
                        <th rowspan="2" class="TD_SOLUONG">Điểm tối đa</th>  
                        <th rowspan="2" class="TD_SOLUONG">% điểm</th>                                                  
                        <th colspan="2" class="TD_SOLUONG">Đơn vị tự chấm</th> 
                        <th colspan="3" class="TD_SOLUONG">Lãnh đạo phụ trách chấm</th> 
                    </tr>                
                    <tr>
                        <th class="TD_SOLUONG">Số kế hoạch, thực hiện, tỷ lệ %, số lỗi</th>                           
                        <th class="TD_NGUYENGIA">Điểm</th>  
                        <th class="TD_SOLUONG">Số kế hoạch, thực hiện, tỷ lệ %, số lỗi</th>                           
                        <th class="TD_NGUYENGIA">Điểm</th>                          
                        <th class="TD_CHITIEU">Ý kiến của lãnh đạo phụ trách</th> 
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                        <tr height="16">                      
                            <td  align="right" class="TD_TEN_KH">    
                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                                                    
                                <input type="hidden" value="<s:property  value="THUTU" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                                 
                                <input type="hidden" value="<s:property  value="MAPGD" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/> 
                                <input type="hidden" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/> 
                                <input type="hidden" value="<s:property  value="MA" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/> 
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
                            <!--                            <td align="center" class="TD_SOLUONG">
                                                                    <input type="text" value="<s:property  value="MA" />" 
                                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                                                </td>                                -->

                            <td align="center" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property value='MA'/>"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                            <td  align="center" class="TD_SOLUONG"> 
                                <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                            <td  align="center" class="TD_SOLUONG"> 
                                <input type="text" value="<s:property  value="D5" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number  TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                            </td>                                                           
                            <td align = "right" class="TD_NGUYENGIA">                            
                                <input type="text" id="D10_<s:property value='MA'/>" value="<s:property value='D10'/>" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               evaluateSum('CHAMDIEMTT_001', 'D10');
                                               isInputMark('D1_<s:property value='MA'/>', 'D10_<s:property value='MA'/>');"   class="number2 TEN_KH" readonly="readonly"/>
                            </td> 
                            <td  align="center" class="TD_SOLUONG"> 
                                <input type="text" value="<s:property  value="D11" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="number  TEN_KH" onfocus="this.select()" 
                                       <s:if test="D14.equalsIgnoreCase('0')"> readonly="readonly" </s:if>/>                                                                  
                            </td>    
                            <td align = "right" class="TD_NGUYENGIA">                            
                                <input type="text" id="D12_<s:property value='MA'/>" value="<s:property value='D12'/>" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               evaluateSum('CHAMDIEMTT_001', 'D12');isInputMark('D1_<s:property value='MA'/>', 'D12_<s:property value='MA'/>');"   class="number2 TEN_KH" readonly="readonly"/>
                            </td>    
                            <td  align="center" class="TD_CHITIEU"> 
                                <input type="text" value="<s:property  value="D13" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>                                                                                                    
                            <td class="hideColumn"><input type="text" value="<s:property value='D3'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                            <td class="hideColumn"><input type="text" value="<s:property value='D15'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                            <td class="hideColumn"><input type="text" value="<s:property value='MA'/>" name="MA_CT" class="MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                        </tr>                    

                    </s:iterator>
                </table>
            </s:if>  


            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>        
    </body>
</html>
