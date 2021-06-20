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
                $(".TD_SOLUONG").css({"width": "5%"});
                $(".TD_NGUYENGIA").css({"width": "8%"});
                $(".TD_THUTU").css({"width": "3%"});
                $(".TD_CHITIEU").css({"width": "30%"});
                
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
        
        <script>
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
                <s:if test="grade.equalsIgnoreCase('1')">
                    CN-PL01 -> CN-PL01 <font color="red">(Trạng thái: <s:property  value="TT_DUYET"/>)</font>
                </s:if> 
                <s:else>
                    <s:if test="tt_cdtt.equalsIgnoreCase('0')">
                        CN-PL01 -> CN-PL01 <font color="red">(Trạng thái: <s:property  value="TT_DUYET"/>)</font>
                    </s:if>  
                    <s:else>
                        Tình trạng số liệu
                    </s:else>
                </s:else>    
            </div>
            <s:hidden name="khoa_cdtt"/>
            <s:hidden name="tt_cdtt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
                        
            <s:if test="tt_cdtt.equalsIgnoreCase('0')">                                
                <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                    <tr height="23">
                        <th class="TD_THUTU">TT</th>
                        <th class="TD_CHITIEU">Chỉ tiêu</th>                                         
                        <th class="TD_SOLUONG">Mã</th>  
                        <th class="TD_SOLUONG">Điểm tối đa</th>  
                        <th class="TD_SOLUONG">% điểm</th>  
                        <!--<th class="TD_CHITIEU">Cộng cấp</th>-->  
                        <th class="TD_NGUYENGIA">Kế hoạch</th>  
                        <th class="TD_NGUYENGIA">Thực hiện</th>  
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
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                    <tr height="16">                      
                            <td  align="right" class="TD_TEN_KH">    
                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                                                    
                                <input type="hidden" value="<s:property  value="THUTU" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/> 
                                <input type="hidden" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/> 
                                <input type="hidden" value="<s:property  value="D15" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/> 
                                <input type="hidden" value="<s:property  value="MA" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/> 
                                <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/> 
                                <input type="hidden" value="<s:property  value="CO_TONGHOP" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/> 
                            </td>
                            <td  align="left" class="TD_TEN_KH">                              
                            <input type="text" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="D TEN_KH break" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                            <s:if test="NHAPTAY.equalsIgnoreCase('Y') && MA.substring(0,6).equalsIgnoreCase('CDTT10')">
                                <td align="center" class="TD_SOLUONG">
                                    <a href="javascript:hienthichitiet('<s:property value="MA"/>',2)" class="SOKU linkKh">
                                        <s:property value='MA'/>
                                    </a>
                                </td>
                            </s:if>     
                            <s:else>
                                <td align="center" class="TD_SOLUONG">
                                    <s:property value='MA'/>                               
                                </td>
                            </s:else>    
                                
                            <td align="center" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                            <td  align="center" class="TD_SOLUONG"> 
                                <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                            <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" id="D4_<s:property value='MA'/>" value="<s:property value='D4'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                           onblur="congcapcongthuc('<s:property value='MA'/>', 'D4_')"   class="number2 TEN_KH" readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">                            
                                    <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                           onblur="congcapcongthuc('<s:property value='MA'/>', 'D5_')"   class="number2 TEN_KH" readonly="readonly"/>
                                </td> 
                            </s:if>
                            <s:else>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" id="D4_<s:property value='MA'/>" value="<s:property value='D4'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                           onblur="congcapcongthuc('<s:property value='MA'/>', 'D4_')"   class="number2 TEN_KH"/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">                                                        
                                    <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                           onblur="congcapcongthuc('<s:property value='MA'/>', 'D5_')"   class="number2 TEN_KH"/>
                                </td>   
                            </s:else>                         
                        </tr>                    

                    </s:iterator>
                </table>
            </s:if>
            <s:else> <%--Truong hop tong hop trang thai cua pgd--%>  
                <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                    <tr height="23">
                        <th class="TD_THUTU">TT</th>
                        <th class="TD_NGUYENGIA">Tên PGD</th>                                         
                        <th class="TD_NGUYENGIA">Trạng thái</th>  
                        <th class="TD_NGUYENGIA">Thời gian</th>  
                        <th class="TD_CHITIEU">Ghi chú</th>                         
                    </tr>                
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                    <tr height="16">                                                  
                            <td  align="left" class="TD_THUTU">                              
                                <input type="text" value="<s:property  value="TT_HIENTHI" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH break" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                            <td align="center" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                            <td align="center" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                            <td align="center" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D5" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                            <td  align="center" class="TD_CHITIEU"> 
                                <input type="text" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>                                                   
                        </tr>                    

                    </s:iterator>
                </table>
            </s:else>
            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>        
    </body>
</html>
