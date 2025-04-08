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
                $('input.number4').css({"text-align": "right"});
                $('input.number505').css({"text-align": "right"});
//                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
//                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number4').number(true, 2);
                $('.number505').number(true, 5);
//                $('.number4').number(true, 0);
                $(".TD_TEN_KH").css({"width": "24%"});
                $(".TD_DONVITINH").css({"width": "50px"});
                $(".TD_SOLUONG").css({"width": "5%"});
                $(".TD_NGUYENGIA").css({"width": "5%"});
                $(".SOKEHOACH").css({"width": "5%"});
                $(".TD_THUTU").css({"width": "3%"});
                $(".TD_CHITIEU").css({"width": "14%"});
                $(".TD_MA").css({"width": "2%"});
                $(".TEN_KH").css({"width": "100%"});
                $(".TD_CT03").css({"width": "7%"});
                $(".hideColumn").hide();
                if ('<s:property value="Grade"/>' == '2' && '<s:property value="RULEUSER"/>' != '9')
                {
                    evaluateSum('CHAMDIEMTT_001', 'D10');
//                    evaluateSum('CHAMDIEMTT_001', 'D5');
//                    evaluateSum('CHAMDIEMTT_001', 'D11');
                    evaluateSum('CHAMDIEMTT_001', 'D12');
                } else if ('<s:property value="Grade"/>' == '2' && '<s:property value="RULEUSER"/>' == '9')
                {
//                    evaluateSum('CHAMDIEMTT_001', 'D17');
                    evaluateSum('CHAMDIEMTT_001', 'D12');
//                    evaluateSum('CHAMDIEMTT_001', 'D11');
                    evaluateSum('CHAMDIEMTT_001', 'D10');
//                    evaluateSum('CHAMDIEMTT_001', 'D9');
                } else if ('<s:property value="Grade"/>' == '1')
                {
                    evaluateSum('CHAMDIEMTT_001', 'D10');
//                    evaluateSum('CHAMDIEMTT_001', 'D4');
//                    evaluateSum('CHAMDIEMTT_001', 'D5');
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
        <s:form id="id_sv_%{khoa_cdtt}" action="SAVE_%{khoa_cdtt}" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">  
                <s:if test="!RULEUSER.equalsIgnoreCase('9') && Grade.equalsIgnoreCase('2')">
                    <font color="red">(Phòng CMNV đánh giá)</font>  - Chỉ tiêu đánh giá tháng - Đối với PGD NHCSXH cấp huyện 
                </s:if>               
                <s:elseif test="Grade.equalsIgnoreCase('1')">
                    <font color="red">(PGD tự đánh giá)</font>  - Chỉ tiêu đánh giá tháng - Đối với phòng giao dịch NHCSXH cấp huyện <font color="red">(Trạng thái: <s:property  value="TT_DUYET"/>)</font>    
                </s:elseif>    
                <s:else>
                    <font color="red">(Hội đồng CN đánh giá)</font>  - Chỉ tiêu đánh giá tháng - Đối với PGD NHCSXH cấp huyện <font color="red">(Trạng thái: <s:property  value="TT_DUYET"/>)</font>    
                </s:else>   

            </div>
            <s:hidden name="khoa_cdtt"/>
            <s:hidden name="tt_cdtt"/>
            <s:hidden name="RULEUSER"/>
            <div id="divDonvitinh">
                Đơn vị tính: Triệu đồng, số điểm, tỷ lệ %, số lỗi
            </div>
            <!----Cấp phòng ban chuyên môn nv chấm-->
            <s:if test="!RULEUSER.equalsIgnoreCase('9') && Grade.equalsIgnoreCase('2')">
                <table border="1" width="4000px" class="editDelete" id="CHAMDIEMTT_001" align="center">
                    <tr height="23">
                        <th rowspan="2" class="TD_THUTU"></th>
                        <th rowspan="2" class="TD_THUTU">TT</th>
                        <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>                                         
                        <th rowspan="2" class="TD_THUTU">Điểm tối đa</th> 
                        <th colspan="2" class="TD_SOLUONG">PGD</th> 
                        <th colspan="4" >Phòng CMNV</th>                                                                                           
                    </tr>  
                    <tr height="22">
                        <th class="TD_SOLUONG">Số Thực hiện, Điểm, Tỷ lệ %, Số lỗi</th>
                        <!--<th class="TD_SOLUONG">Điều chỉnh tăng giảm</th>-->
                        <th class="TD_SOLUONG">Điểm</th>
                        <th class="TD_SOLUONG">Số kế hoạch, Điểm, Tỷ lệ %, Số lỗi</th>
                        <!--<th class="TD_SOLUONG">Điều chỉnh tăng giảm</th>-->
                        <th class="TD_SOLUONG">Điểm</th>
                        <th class="TD_CHITIEU">Ghi chú</th>                                                 
                    </tr> 

                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                        <tr height="16" class="<s:property  value="D30" />"> 
                            <s:if test="D9.equalsIgnoreCase('1')">
                                <td  align="left" class="TD_SOLUONG">                              
                                    <input style="color: red; font-weight: bold;"  type="text" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                </td> 
                            </s:if> 
                            <s:else>
                                <td></td>
                            </s:else>  
                            <td align="center" class="TD_THUTU">
                                <a href="javascript:hienthichitiet('<s:property value="MA"/>',2,'<s:property value='khoa_cdtt'/>')" class="SOKU linkKh">
                                    <s:property value='TT_HIENTHI'/>
                                </a>                            
                                <input type="hidden" value="<s:property  value="TT_HIENTHI" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                                                    
                                <input type="hidden" value="<s:property  value="THUTU" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/> 
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
                                <input type="hidden" value="<s:property  value="MA" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/> 
                            </td>
                            <td  align="left" class="TD_TEN_KH">                              
                                <input type="text" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="D TEN_KH break" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>  
                            <td  align="left" class="TD_THUTU">                              
                                <input type="text" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH break" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                            <td align = "right" class="SOKEHOACH">
                                <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;"   
                                       <s:if test="MA.equalsIgnoreCase('CDTT09')"> class="number505 TEN_KH" </s:if>  <s:else> class="number4 TEN_KH" </s:else>
                                           readonly="readonly"/>
                                </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" id="D10_<s:property value='MA'/>" value="<s:property value='D10'/>" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;"   class="number4 TEN_KH" readonly="readonly"/>
                            </td>        
                            <s:if test="((NHAPTAY.equalsIgnoreCase('N') ) && (!MA.equalsIgnoreCase('CDTT1002')) &&!MA.equalsIgnoreCase('CDTT1001'))|| D16.equalsIgnoreCase('1')">                                  
                                <td align = "right" class="SOKEHOACH">
                                    <input type="text" id="D11_<s:property value='MA'/>" value="<s:property value='D11'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;"   <s:if test="MA.equalsIgnoreCase('CDTT09')"> class="number505 TEN_KH" </s:if>  <s:else> class="number4 TEN_KH" </s:else> readonly="readonly"/>

                                    </td>
                                    <td align = "right" class="TD_SOLUONG">
                                        <input type="text" id="D12_<s:property value='MA'/>" value="<s:property value='D12'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum('CHAMDIEMTT_001', 'D12')"   class="number4 TEN_KH" readonly="readonly"/>
                                </td>


                                <td  align="center" class="TD_CHITIEU"> 
                                    <input type="text" value="<s:property  value="D13" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>                                   

                            </s:if>   
                            <s:else>                                
                                <td align = "right" class="TD_CT03">
                                    <s:if test="MA.equalsIgnoreCase('CDTT03')">
                                        <%@include file="../chamdiem_tapthe/cdtt03.jsp" %>
                                    </s:if>
                                    <s:else>
                                        <input type="text" id="D11_<s:property value='MA'/>" value="<s:property value='D11'/>" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                               onblur="if (this.value == '') {
                                                           this.value = 0
                                                       }
                                                       ;"   <s:if test="MA.equalsIgnoreCase('CDTT09')"> class="number505 TEN_KH" </s:if>  <s:else> class="number4 TEN_KH" </s:else>
                                               <s:if test="MA.equalsIgnoreCase('CDTT11')||MA.equalsIgnoreCase('CDTT1201')|| MA.equalsIgnoreCase('CDTT1203')|| D16.equalsIgnoreCase('1')">readonly="readonly"</s:if>/>
                                    </s:else>
                                </td>
                                
                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" id="D12_<s:property value='MA'/>" value="<s:property value='D12'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   isInputMark('D1_<s:property value='MA'/>', 'D12_<s:property value='MA'/>');
                                                   evaluateSum('CHAMDIEMTT_001', 'D12')"   class="number4 TEN_KH"
                                          <s:if test="!MA.equalsIgnoreCase('CDTT1301')&&!MA.equalsIgnoreCase('CDTT1302')&&!MA.equalsIgnoreCase('CDTT1303')&&!MA.equalsIgnoreCase('CDTT1304')">readonly="readonly"</s:if>
                                                    </td>

                                    <td  align="center" class="TD_CHITIEU"> 
                                        <input type="text" value="<s:property  value="D13" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select()"/>                                  
                                </td> 
                            </s:else>    

                            <td class="hideColumn"><input type="text" value="<s:property value='D3'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                            <td class="hideColumn"><input type="text" value="<s:property value='D15'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                            <td class="hideColumn"><input type="text" value="<s:property value='MA'/>" name="MA_CT" class="MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                            <td class="hideColumn"><input type="text" value="<s:property  value="D1" />" id="D1_<s:property value='MA'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" /> </td>
                        </tr>                    

                    </s:iterator>
                </s:if>


                <!----Cấp hội đồng duyệt cuối cùng-->
                <s:if test="RULEUSER.equalsIgnoreCase('9') && Grade.equalsIgnoreCase('2')">
                    <table border="1" width="4000px" class="editDelete" id="CHAMDIEMTT_001" align="center">
                        <tr height="23">
                            <th rowspan="2" class="TD_THUTU"></th>
                            <th rowspan="2" class="TD_THUTU">TT</th>
                            <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>                                         
                            <th rowspan="2" class="TD_THUTU">Điểm tối đa</th> 
                            <th colspan="2" class="TD_SOLUONG">PGD</th> 
                            <th colspan="4" >Phòng CMNV</th>                                                                                           
                        </tr>  
                        <tr height="22">
                            <th class="TD_SOLUONG">Số Thực hiện, Điểm, Tỷ lệ %, Số lỗi</th>
                            <!--<th class="TD_SOLUONG">Điều chỉnh tăng giảm</th>-->
                            <th class="TD_SOLUONG">Điểm</th>
                            <th class="TD_SOLUONG">Số kế hoạch, Điểm, Tỷ lệ %, Số lỗi</th>
                            <!--<th class="TD_SOLUONG">Điều chỉnh tăng giảm</th>-->
                            <th class="TD_SOLUONG">Điểm</th>
                            <th class="TD_CHITIEU">Ghi chú</th>                                                 
                        </tr> 

                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                            <tr height="16" class="<s:property  value="D30" />">
                                <s:if test="MA.equalsIgnoreCase('CDTT01')">
                                    <td  align="left" class="TD_SOLUONG">                              
                                        <input style="color: red; font-weight: bold;" type="text" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                    </td> 
                                </s:if>    
                                <s:else>
                                    <td></td>
                                </s:else>
                                <td align="center" class="TD_THUTU">
                                    <a href="javascript:hienthichitiet('<s:property value="MA"/>',2,'<s:property value='khoa_cdtt'/>')" class="SOKU linkKh">
                                        <s:property value='TT_HIENTHI'/>
                                    </a>                                                             
                                </td>
                                <td  align="left" class="TD_TEN_KH">                              
                                    <input type="text" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="D TEN_KH break" onfocus="this.select()"    readonly="readonly" />                                  
                                    <input type="hidden" value="<s:property  value="MAPGD" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/> 
                                </td>  
                                <td  align="left" class="TD_THUTU">                              
                                    <input type="text" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH break" onfocus="this.select()"    readonly="readonly" />                                  
                                </td>
                                <td align = "right" class="SOKEHOACH">
                                    <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;"   
                                           <s:if test="MA.equalsIgnoreCase('CDTT09')"> class="number505 TEN_KH" </s:if>  <s:else> class="number4 TEN_KH" </s:else>
                                               readonly="readonly"/>
                                    </td>
                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" id="D10_<s:property value='MA'/>" value="<s:property value='D10'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;"   class="number4 TEN_KH" readonly="readonly"/>
                                </td>                                    
                                <td align = "right" class="SOKEHOACH">
                                    <input type="text" id="D11_<s:property value='MA'/>" value="<s:property value='D11'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;"   <s:if test="MA.equalsIgnoreCase('CDTT09')"> class="number505 TEN_KH" </s:if>  <s:else> class="number4 TEN_KH" </s:else> readonly="readonly"/>
                                    </td>
                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" id="D12_<s:property value='MA'/>" value="<s:property value='D12'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;"   class="number4 TEN_KH" readonly="readonly"/>
                                </td>
                                <td  align="center" class="TD_CHITIEU"> 
                                    <input type="text" value="<s:property  value="D13" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>   

                                <td class="hideColumn"><input type="text" value="<s:property value='D3'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                                <td class="hideColumn"><input type="text" value="<s:property value='D15'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                                <td class="hideColumn"><input type="text" value="<s:property value='MA'/>" name="MA_CT" class="MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                                <!--<td class="hideColumn"><input type="text" value="<s:property  value="D1" />" id="D1_<s:property value='MA'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" /> </td>-->

                            </tr>                    

                        </s:iterator>
                    </s:if> 
                    <!--Phong giao dich-->                     
                    <s:if test="Grade.equalsIgnoreCase('1')">
                        <s:if test="!tt_cdtt.equalsIgnoreCase('0')">                                
                            <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                                <tr height="23">
                                    <th class="TD_THUTU">TT</th>
                                    <th class="TD_TEN_KH">Chỉ tiêu</th>                                         
                                    <th class="TD_SOLUONG">Mã</th>  
                                    <th class="TD_SOLUONG">Điểm tối đa</th>  
                                    <th class="TD_SOLUONG">% điểm</th>  
                                    <!--<th class="TD_CHITIEU">Cộng cấp</th>-->  
                                    <th class="TD_NGUYENGIA">Số kế hoạch</th>  
                                    <th class="TD_NGUYENGIA">Số thực hiện, Điểm, Tỷ lệ %, Số lỗi</th>  
                                    <!--<th class="TD_NGUYENGIA">Điều chỉnh tăng giảm</th>-->  
                                    <th class="TD_NGUYENGIA">Điểm</th>         
                                </tr>                
                                <tr height="22">         
                                    <th style="font: italic; font-size: xx-small;" class="TD_SOLUONG">(1)</th>
                                    <th style="font: italic; font-size: xx-small;" class="TD_DONVITINH">(2)</th>
                                    <th style="font: italic; font-size: xx-small;" class="TD_SOLUONG">(3)</th>
                                    <th style="font: italic; font-size: xx-small;" class="TD_SOLUONG">(4)</th>
                                    <th style="font: italic; font-size: xx-small;" class="TD_SOLUONG">(5)</th>
                                    <!--<th style="font: italic; font-size: xx-small;" class="TD_DONVITINH">(6)</th>-->
                                    <th style="font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(7)</th>
                                    <th style="font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(8)</th>    
                                    <th style="font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(9)</th> 
                                    <!--<th style="font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(10)</th>--> 
                                </tr>
                                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                                    <tr height="16">                      
                                        <td  align="right" class="TD_SOLUONG">    
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

                                        <td align="center" class="TD_MA">
                                            <a href="javascript:hienthichitiet('<s:property value="MA"/>',2,'<s:property value='khoa_cdtt'/>')" class="SOKU linkKh">
                                                <s:property value='MA'/>
                                            </a>
                                        </td>                                                         

                                        <td align="center" class="TD_SOLUONG">
                                            <input type="text" value="<s:property  value="D1" />" id="D1_<s:property value='MA'/>"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                        </td>
                                        <td  align="center" class="TD_SOLUONG"> 
                                            <input type="text" value="<s:property  value="D2" />" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                        </td>
                                        <td align = "right" class="TD_CT03">
                                            <s:if test="MA.equalsIgnoreCase('CDTT03')">
                                                <%@include file="../chamdiem_tapthe/cdtt03.jsp" %>
                                            </s:if>
                                            <s:else>
                                                <input type="text" id="D4_<s:property value='MA'/>" value="<s:property value='D4'/>" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                                       onblur="if (this.value == '') {
                                                                   this.value = 0
                                                               }
                                                               ;"   <s:if test="MA.equalsIgnoreCase('CDTT09')"> class="number505 TEN_KH" </s:if>  <s:else> class="number4 TEN_KH" </s:else>  
                                                       <s:if test="MA.equalsIgnoreCase('CDTT11')||MA.equalsIgnoreCase('CDTT1101')
                                             ||MA.equalsIgnoreCase('CDTT110101')||MA.equalsIgnoreCase('CDTT110102')
                                             ||MA.equalsIgnoreCase('CDTT110103')||MA.equalsIgnoreCase('CDTT110104')
                                             ||MA.equalsIgnoreCase('CDTT110105')||MA.equalsIgnoreCase('CDTT110106')
                                             ||MA.equalsIgnoreCase('CDTT110107')||MA.equalsIgnoreCase('CDTT110108')
                                             ||MA.equalsIgnoreCase('CDTT110109')||MA.equalsIgnoreCase('CDTT110110')
                                             ||MA.equalsIgnoreCase('CDTT110111')||MA.equalsIgnoreCase('CDTT110112')
                                             ||MA.equalsIgnoreCase('CDTT110113')||MA.equalsIgnoreCase('CDTT110114')
                                             ||MA.equalsIgnoreCase('CDTT110115')||MA.equalsIgnoreCase('CDTT110116')
                                             ||MA.equalsIgnoreCase('CDTT110117')||MA.equalsIgnoreCase('CDTT110118')
                                             ||MA.equalsIgnoreCase('CDTT110119')||MA.equalsIgnoreCase('CDTT110120')
                                             ||MA.equalsIgnoreCase('CDTT110121')||MA.equalsIgnoreCase('CDTT110124')
                                             ||MA.equalsIgnoreCase('CDTT110125')||MA.equalsIgnoreCase('CDTT110130')
                                             ||MA.equalsIgnoreCase('CDTT110131')||MA.equalsIgnoreCase('CDTT1102')
                                             ||MA.equalsIgnoreCase('CDTT110201')||MA.equalsIgnoreCase('CDTT11020101')
                                             ||MA.equalsIgnoreCase('CDTT11020102')||MA.equalsIgnoreCase('CDTT11020103')
                                             ||MA.equalsIgnoreCase('CDTT11020104')||MA.equalsIgnoreCase('CDTT11020105')
                                             ||MA.equalsIgnoreCase('CDTT11020106')||MA.equalsIgnoreCase('CDTT110202')
                                             ||MA.equalsIgnoreCase('CDTT11020201')||MA.equalsIgnoreCase('CDTT11020202')
                                             ||MA.equalsIgnoreCase('CDTT110203')||MA.equalsIgnoreCase('CDTT110204')
                                             ||MA.equalsIgnoreCase('CDTT13')||MA.equalsIgnoreCase('CDTT14')
                                             ||MA.equalsIgnoreCase('CDTT1401')||MA.equalsIgnoreCase('CDTT140101')
                                             ||MA.equalsIgnoreCase('CDTT140102')||MA.equalsIgnoreCase('CDTT1402')
                                             ||MA.equalsIgnoreCase('CDTT1403')||MA.equalsIgnoreCase('CDTT99')
                                             ||MA.equalsIgnoreCase('CDTT10')||MA.equalsIgnoreCase('CDTT1001')
                                             ||MA.equalsIgnoreCase('CDTT1002')||MA.equalsIgnoreCase('CDTT1003')
                                             ||MA.equalsIgnoreCase('CDTT1004')||MA.equalsIgnoreCase('CDTT1005')
                                             ||MA.equalsIgnoreCase('CDTT1006')||MA.equalsIgnoreCase('CDTT1007')">readonly="readonly"</s:if>
                                       />				   
                                            </s:else>

                                        </td>
                                        <td align = "right" class="TD_NGUYENGIA">                                                        
                                            <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                                   onblur="if (this.value == '') {
                                                               this.value = 0
                                                           }
                                                           ;"   
                                                   <s:if test="MA.equalsIgnoreCase('CDTT09')"> class="number505 TEN_KH" </s:if>  <s:else> class="number4 TEN_KH" </s:else>               
                                                    <s:if test="!MA.equalsIgnoreCase('CDTT1301')&&!MA.equalsIgnoreCase('CDTT1302')&&!MA.equalsIgnoreCase('CDTT1303')&&!MA.equalsIgnoreCase('CDTT1304')">readonly="readonly"</s:if>
                                                       />
                                            </td>   

                                            <td align = "right" class="TD_NGUYENGIA">                            
                                                <input type="text" id="D10_<s:property value='MA'/>" value="<s:property value='D10'/>" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                                   onblur="if (this.value == '') {
                                                               this.value = 0
                                                           }
                                                           ;
                                                           isInputMark('D1_<s:property value='MA'/>', 'D10_<s:property value='MA'/>');
                                                           evaluateSum('CHAMDIEMTT_001', 'D10');"   class="number4 TEN_KH" 
                                                   <s:if test="!MA.equalsIgnoreCase('CDTT1301')&&!MA.equalsIgnoreCase('CDTT1302')&&!MA.equalsIgnoreCase('CDTT1303')&&!MA.equalsIgnoreCase('CDTT1304')">readonly="readonly"</s:if>
                                                       />
                                            </td>                            
                                            <td class="hideColumn"><input type="text" value="<s:property value='D3'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                                        <td class="hideColumn"><input type="text" value="<s:property value='D15'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                                        <td class="hideColumn"><input type="text" value="<s:property value='MA'/>" name="MA_CT" class="MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                                        <td align = "right" class="TD_NGUYENGIA hideColumn">                            
                                            <input type="text" id="D12_<s:property value='MA'/>" value="<s:property value='D12'/>" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                                   onblur="if (this.value == '') {
                                                               this.value = 0
                                                           }
                                                           ;
                                                           evaluateSum('CHAMDIEMTT_001', 'D12')"   class="number4 TEN_KH" readonly="readonly"/>
                                        </td>
                                        <td align = "right" class="TD_NGUYENGIA hideColumn">                            
                                            <input type="text" id="D17_<s:property value='MA'/>" value="<s:property value='D17'/>" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" 
                                                   onblur="if (this.value == '') {
                                                               this.value = 0
                                                           }
                                                           ;
                                                           evaluateSum('CHAMDIEMTT_001', 'D17')"   class="number4 TEN_KH" readonly="readonly"/>
                                        </td>        
                                    </tr>                    

                                </s:iterator>
                            </table>
                        </s:if>                          
                    </s:if>               
                    <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                               onCompleteTopics="completediv_ss" cssStyle="display: none"/>
                </s:form>
                <div id="luu_thanhcong"></div>        
                </body>
                </html>
