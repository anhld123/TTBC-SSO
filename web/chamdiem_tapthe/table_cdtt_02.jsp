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
                $(".TD_SOLUONG").css({"width": "5%"});
                $(".TD_NGUYENGIA").css({"width": "8%"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "30%"});
                $(".TEN_KH").css({"width": "100%"});
                $(".TEN_KH").css({"height": "100%"});
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
            };
                </script>
    </head>
    <style>

    </style>
    <body>
        <s:form id="id_sv_%{khoa_bcqt}" action="SAVE_%{khoa_bcqt}" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                CN-PL01 -> CN-PL01
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablems05" align="center">
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
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr height="22">  
                        <td  align="right" class="TD_TEN_KH">    
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                                                    
                        </td>
                        <td  align="right" class="TD_TEN_KH">    
                        <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="D TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td  align="right" class="TD_SOLUONG">    
                        <input type="text" value="<s:property  value="MA" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td align="center" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="D1" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td  align="center" class="TD_SOLUONG"> 
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        
<!--                        <td align="center" class="TD_CHITIEU">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" onfocus="this.select()"    readonly="readonly" />                                  -->
                        </td>
                        
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D7" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D8" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>                        
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
                        
                        <td align="center" class="TD_SOLUONG">
                            <s:property  value="MA" /> 
                        </td>
                        <td align="center" class="TD_SOLUONG">
                            <s:property  value="D1" /> 
                        </td>
                        
                        <td align="center" class="TD_SOLUONG">
                            <s:property  value="D2" /> 
                        </td>
<!--                        <td align="center" class="TD_CHITIEU">
                            <s:property  value="D3" /> 
                        </td>-->
                                         
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number2 TEN_KH" onfocus="this.select()"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
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
