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
            .CLS-BOLD{
                font-weight: bold;
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
                $(".TD_TEN_KH").css({"width": "130px"});
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
    
                </script>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_bcqt}" action="SAVE_%{khoa_bcqt}" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                MS05A/QT - BÁO CÁO KIỂM KÊ CHI TIẾT TÀI SẢN CỐ ĐỊNH
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablems05" align="center">
                <tr>
                    <th rowspan="2"  class="TD_THUTU">TT</th>
                    <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>
                    <th  rowspan="2" class="TD_TEN_KH">Mã tài sản</th>
                      <th  rowspan="2" class="TD_CHITIEU">Đặc điểm tài sản</th>
                    <th rowspan="2"  class="TD_SOLUONG">Đơn vị tính</th>
                    <th  colspan="3">TỔNG CỘNG</th>
                    <th colspan="2">TR.ĐÓ: VỐN ĐP, CHO, TẶNG; VỐN KHÁC</th> 
                </tr>
                <tr>                                  
                    <th   class="TD_SOLUONG">Số lượng</th>
                    <th   class="TD_NGUYENGIA">Nguyên giá</th>
                    <th   class="TD_NGUYENGIA">GTCL</th>
                    <th   class="TD_NGUYENGIA">Nguyên giá</th>
                    <th   class="TD_NGUYENGIA">GTCL</th>                   
                </tr>
                <tr>         
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THUTU">(A)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_CHITIEU">(B)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(C)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_CHITIEU">(1)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_SOLUONG">(2)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_SOLUONG">(3)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(4)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(5)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(6)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">(7)</th>
                    
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    
                        <tr height="22">  
                        <td  align="right" class="TD_THUTU">    
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()"    readonly="readonly" />                                                                   
                            <input type="hidden" value="<s:property  value="THUTU" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/> 
                          
                        </td>
                        <td  align="right" class="TD_TEN_KH">    
                            <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td  align="right" class="TD_NGUYENGIA">    
                            <input type="text" value="<s:property  value="D10" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <s:if test="NHAPTAY.equalsIgnoreCase('CLS-BOLD')">
                                <td  align="right" class="TD_TEN_KH">    
                                <input type="text" value="<s:property  value="D1" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()"  readonly="readonly"/>                                  
                                </td>

                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D2" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class=" TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()" readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D3" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D5 number2 TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()" readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_DONVITINH">
                                    <input type="text" value="<s:property  value="D4" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D6 number2 TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()" readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" value="<s:property  value="D5" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D7 number2 TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()" readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" value="<s:property  value="D6" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D8 number2 TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()" readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" value="<s:property  value="D7" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D9 number2 TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()" readonly="readonly"/>
                                </td>
                                
                        </s:if>
                        <s:else>
                                <td  align="right" class="TD_TEN_KH">    
                                <input type="text" value="<s:property  value="D1" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if> />                                  
                                </td>

                                <td align = "right" class="TD_DONVITINH">
                                    <input type="text" value="<s:property  value="D2" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class=" TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if>/>
                                </td>
                                <td align = "right" class="TD_DONVITINH">
                                    <input type="text" value="<s:property  value="D3" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D5 number2 TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if>/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" value="<s:property  value="D4" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D6 number2 TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if>/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" value="<s:property  value="D5" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D7 number2 TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if>/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" value="<s:property  value="D6" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D8 number2 TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if>/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" value="<s:property  value="D7" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D9 number2 TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if>/>
                                </td>
                                
                        </s:else>
                        
                    </tr>
                   
                    
                </s:iterator>
            </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
//            initTable();
        </script>
    </body>
</html>
