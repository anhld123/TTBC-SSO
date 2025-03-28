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
                $('input.number5').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
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
                if ('<s:property value="Grade"/>' === '2')
                {
                    evaluateSum('CHAMDIEMTT_001', 'D10');
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
    <body>
        <s:form id="id_sv_%{khoa_cdtt}" action="SAVE_%{khoa_cdtt}NHAP" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">  
                <font color="red">(Hội đồng CN đánh giá)</font>   <font>- CHỈ TIÊU ĐÁNH GIÁ THÁNG - ĐỐI VỚI CHI NHÁNH NHCSXH TỈNH/THÀNH PHỐ </font>    
            </div>    
            <s:hidden name="khoa_cdtt"/>
            <s:hidden name="tt_cdtt"/>

            <div>
                <p style="text-align: left;padding-left: 30px;">[x/y]: Chỉ tiêu được phép loại trừ, x: số được loại trừ, y: tổng số</p>            
                <div id="divDonvitinh"> Đơn vị tính: Triệu đồng, số điểm, tỷ lệ %, số lỗi
                </div>  
            </div>

            <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center" style="height: 60%;">
                <tr height="23">
                    <th rowspan="2" class="TD_THUTU">TT</th>
                    <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>                                         
                    <!--<th rowspan="2" class="TD_SOLUONG">Mã</th>-->  
                    <th rowspan="2"  class="TD_SOLUONG">Điểm tối đa</th>  
                    <th rowspan="2"  class="TD_SOLUONG">% điểm</th>                           
                    <th colspan="4" class="TD_NGUYENGIA">Chi nhánh</th>  
                </tr>                
                <tr height="21">
                    <th  class="TD_SOLUONG">Số kế hoạch</th>   
                    <th  class="TD_SOLUONG">Số Thực hiện, Điểm, Tỷ lệ %, Số lỗi</th>   
                    <!--<th  class="TD_SOLUONG">Tăng giảm kế hoạch</th>-->   
                    <th  class="TD_SOLUONG">Điểm</th> 
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                    <tr height="16" class="<s:property value='D30'/>">                      
                        <td align="center" class="TD_SOLUONG">
                            <a href="javascript:hienthichitiet('<s:property value="MA"/>',2,'<s:property value='khoa_cdtt'/>')" class="SOKU linkKh">
                                <s:property value='TT_HIENTHI'/>
                            </a>    
                            <input type="hidden" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                                                    
                            <input type="hidden" value="<s:property  value="THUTU" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/> 
                            <input type="hidden" value="<s:property  value="NGAYBC" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGAYBC"/> 

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
                            <input type="text" value="<s:property  value="D1" />" id="D1_<s:property value='MA'/>"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td  align="center" class="TD_SOLUONG"> 
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <s:if test="!months.equalsIgnoreCase('03') && !months.equalsIgnoreCase('06')&&
                              !months.equalsIgnoreCase('09')&&!months.equalsIgnoreCase('12')">
                            <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" id="D4_<s:property value='MA'/>" value="<s:property value='D4'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum('CHAMDIEMTT_001', 'D4')"   
                                           <s:if test="MA.equalsIgnoreCase('CDTT09')">
                                               class="number5 TEN_KH" 
                                           </s:if>     
                                           <s:else> 
                                               class="number2 TEN_KH"
                                           </s:else> 
                                           readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">                            
                                    <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum('CHAMDIEMTT_001', 'D5')"   
                                           <s:if test="MA.equalsIgnoreCase('CDTT09')">
                                               class="number5 TEN_KH" 
                                           </s:if>     
                                           <s:else> 
                                               class="number2 TEN_KH"
                                           </s:else> 
                                           <s:if test="MA.equalsIgnoreCase('CDTT08') && NGUOI_NHAP.equalsIgnoreCase('AAA')"></s:if>
                                           <s:else>readonly="readonly"</s:else>
                                               />
                                    </td>      
                                    <td align = "right" class="hideColumn">                            
                                        <input type="text" id="D6_<s:property value='MA'/>" value="<s:property value='D6'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum('CHAMDIEMTT_001', 'D6')"   class="number2 TEN_KH" readonly="readonly"/>
                                </td> 
                                <td align = "right" class="TD_NGUYENGIA">                            
                                    <input type="text" id="D10_<s:property value='MA'/>" value="<s:property value='D10'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   isInputMark('D1_<s:property value='MA'/>', 'D10_<s:property value='MA'/>');
                                                   evaluateSum('CHAMDIEMTT_001', 'D10')"   class="number2 TEN_KH" readonly="readonly"/>
                                </td>                                    
                            </s:if>
                            <s:else>

                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" id="D4_<s:property value='MA'/>" value="<s:property value='D4'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum('CHAMDIEMTT_001', 'D4')"  
                                           <s:if test="MA.equalsIgnoreCase('CDTT09')">
                                               class="number5 TEN_KH" 
                                           </s:if>     
                                           <s:else> 
                                               class="number2 TEN_KH"
                                           </s:else> 
                                           <s:if test="!MA.equalsIgnoreCase('CDTT01')&&!MA.equalsIgnoreCase('CDTT00')
                                                 &&!MA.equalsIgnoreCase('CDTT02')&&!MA.equalsIgnoreCase('CDTT02A')
                                                 &&!MA.equalsIgnoreCase('CDTT03')&&!MA.equalsIgnoreCase('CDTT08')
                                                 &&!MA.equalsIgnoreCase('CDTT03')">readonly="readonly"</s:if>
                                               />
                                    </td>
                                    <td align = "right" class="TD_NGUYENGIA">                                                        
                                        <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum('CHAMDIEMTT_001', 'D5')"   
                                           <s:if test="MA.equalsIgnoreCase('CDTT09')">
                                               class="number5 TEN_KH" 
                                           </s:if>     
                                           <s:else> 
                                               class="number2 TEN_KH"
                                           </s:else> 
                                           <%--<s:if test="!MA.equalsIgnoreCase('CDTT1101')&&!MA.equalsIgnoreCase('CDTT1102')&&!MA.equalsIgnoreCase('CDTT1103')&&!MA.equalsIgnoreCase('CDTT100101')&&!MA.equalsIgnoreCase('CDTT100102')&&!MA.equalsIgnoreCase('CDTT100103')&&!MA.equalsIgnoreCase('CDTT100104')&&!MA.equalsIgnoreCase('CDTT100105')&&!MA.equalsIgnoreCase('CDTT100106')&&!MA.equalsIgnoreCase('CDTT100107')&&!MA.equalsIgnoreCase('CDTT100108')&&!MA.equalsIgnoreCase('CDTT100201')&&!MA.equalsIgnoreCase('CDTT100202')&&!MA.equalsIgnoreCase('CDTT100203')&&!MA.equalsIgnoreCase('CDTT100204')&&!MA.equalsIgnoreCase('CDTT120101')&&!MA.equalsIgnoreCase('CDTT120103')&&!MA.equalsIgnoreCase('CDTT120201')&&!MA.equalsIgnoreCase('CDTT120202')">readonly="readonly"</s:if>--%>
                                           <s:if test="!MA.equalsIgnoreCase('CDTT1001')&&!MA.equalsIgnoreCase('CDTT1002')
                                                 &&!MA.equalsIgnoreCase('CDTT1003')&&!MA.equalsIgnoreCase('CDTT1004')
                                                 &&!MA.equalsIgnoreCase('CDTT1005')&&!MA.equalsIgnoreCase('CDTT1006')
                                                 &&!MA.equalsIgnoreCase('CDTT1301')&&!MA.equalsIgnoreCase('CDTT1302')
                                                 &&!MA.equalsIgnoreCase('CDTT1301')&&!MA.equalsIgnoreCase('CDTT1302')
                                                 &&!MA.equalsIgnoreCase('CDTT1303')&&!MA.equalsIgnoreCase('CDTT140201')
                                                 &&!MA.equalsIgnoreCase('CDTT140202')&&!MA.equalsIgnoreCase('CDTT140301')
                                                 &&!MA.equalsIgnoreCase('CDTT140302')">readonly="readonly"</s:if>
                                               />
                                    </td>  
                                    <td align = "right" class="hideColumn">                            
                                        <input type="text" id="D6_<s:property value='MA'/>" value="<s:property value='D6'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   evaluateSum('CHAMDIEMTT_001', 'D6')"   class="number2 TEN_KH"  
                                           readonly="readonly"
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
                                                   evaluateSum('CHAMDIEMTT_001', 'D10')"   class="number2 TEN_KH" 
                                           <s:if test="!MA.equalsIgnoreCase('CDTT1301')&&!MA.equalsIgnoreCase('CDTT1302')&&!MA.equalsIgnoreCase('CDTT1303')">readonly="readonly"</s:if>
                                           <%--<s:if test="!MA.equalsIgnoreCase('CDTT1101')&&!MA.equalsIgnoreCase('CDTT1102')&&!MA.equalsIgnoreCase('CDTT1103')">readonly="readonly"</s:if>--%>
                                           />
                                </td>                                   
                            </s:else>   
                        </s:if>
                        <!--bo sung ngay 21-03-2025-->
                        <s:else>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" id="D4_<s:property value='MA'/>" value="<s:property value='D4'/>" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               evaluateSum('CHAMDIEMTT_001', 'D4')"  
                                       <s:if test="MA.equalsIgnoreCase('CDTT09')">
                                           class="number5 TEN_KH" 
                                       </s:if>     
                                       <s:else> 
                                           class="number2 TEN_KH"
                                       </s:else> 

                                       <s:if test="!MA.equalsIgnoreCase('CDTT01')&&!MA.equalsIgnoreCase('CDTT00')
                                             &&!MA.equalsIgnoreCase('CDTT02')&&!MA.equalsIgnoreCase('CDTT02A')
                                             &&!MA.equalsIgnoreCase('CDTT03')&&!MA.equalsIgnoreCase('CDTT08')
                                             &&!MA.equalsIgnoreCase('CDTT09')">readonly="readonly"</s:if>/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">                                                        
                                    <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               evaluateSum('CHAMDIEMTT_001', 'D5')"   
                                       <s:if test="MA.equalsIgnoreCase('CDTT09')">
                                           class="number5 TEN_KH" 
                                       </s:if>     
                                       <s:else> 
                                           class="number2 TEN_KH"
                                       </s:else> 
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
                                             ||MA.equalsIgnoreCase('CDTT1403')||MA.equalsIgnoreCase('CDTT99')">readonly="readonly"</s:if>
                                       />
                                </td>  
                                <td align = "right" class="hideColumn">                            
                                    <input type="text" id="D6_<s:property value='MA'/>" value="<s:property value='D6'/>" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               evaluateSum('CHAMDIEMTT_001', 'D6')"   class="number2 TEN_KH"  
                                       readonly="readonly"
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
                                               evaluateSum('CHAMDIEMTT_001', 'D10')"   class="number2 TEN_KH" 
                                       <s:if test="!MA.equalsIgnoreCase('CDTT1301')&&!MA.equalsIgnoreCase('CDTT1302')&&!MA.equalsIgnoreCase('CDTT1303')">readonly="readonly"</s:if>
                                           />
                                </td>
                        </s:else>
                        <td class="hideColumn"><input type="text" value="<s:property value='D3'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                        <td class="hideColumn"><input type="text" value="<s:property value='D15'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                        <td class="hideColumn"><input type="text" value="<s:property value='MA'/>" name="MA_CT" class="MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                    </tr>                    

                </s:iterator>
            </table>

            <br/>

            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>        
    </body>
</html>
