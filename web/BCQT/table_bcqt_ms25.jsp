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
                $('.number').number(true, 1);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TD_DONVITINH").css({"width": "40px"});
                $(".TD_SOLUONG").css({"width": "50px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "20px"});
                $(".TD_CHITIEU").css({"width": "300px"});
                $(".TD_MOTA").css({"width": "170px"});
                $(".TEN_KH").css({"width": "100%"});
            });
        </script> 
        <script>
            function initTable()
            {
                autoEvaluate();
            };
            function autoEvaluate(){
                var arrCot = [".D3"];
                
                for (i = 0; i < arrCot.length; i++) { 
                    $(arrCot[i]).eq(4).val( parseFloat($(arrCot[i]).eq(0).val()) * ($(arrCot[i]).eq(2).val()-$(arrCot[i]).eq(1).val()) * parseFloat($(arrCot[i]).eq(3).val()) );
                }                               
                
                for (i = 0; i < arrCot.length; i++) {
                    $(arrCot[i]).eq(7).val( parseFloat($(arrCot[i]).eq(4).val()) + parseFloat($(arrCot[i]).eq(5).val()) - parseFloat($(arrCot[i]).eq(6).val()));
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
                QUYẾT TOÁN CẤP BÙ CHÊNH LỆCH LÃI SUẤT CỦA NGÂN SÁCH ĐỊA PHƯƠNG ĐỐI VỚI CHƯƠNG TRÌNH CHO VAY HỘ NGHÈO VỀ 
                </br>
                NHÀ Ở GIAI ĐOẠN 2 THEO QĐ 33/2015/QĐ-TTG
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
               . <!--Đơn vị tính: Đồng-->
            </div>
            <table border="1" class="editDelete" id="tablems25" align="center">
                <tr height="35">
                    <th  class="TD_THUTU">Stt</th>
                    <th  class="TD_CHITIEU">Chỉ tiêu</th>
                    <th  class="TD_DONVITINH">Đơn vị tính</th>
                    <th  class="TD_MOTA">Nguồn số liệu/Công thức tính</th>
                    <th  class="TD_DONVITINH">Số tiền/(hoặc %)</th> 
                </tr>                               
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                        <tr height="24">  
                        <td  align="right" class="TD_THUTU">    
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                        </td>
                        <td  align="right" class="TD_CHITIEU">    
                        <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td  align="right" class="TD_DONVITINH">    
                        <input type="text" value="<s:property  value="D1" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>

                        <td align = "right" class="TD_MOTA">
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <s:if test="TT_HIENTHI.equalsIgnoreCase('5') || TT_HIENTHI.equalsIgnoreCase('8')">
                            <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                            </td>
                            </s:if>
                            <s:else>
                                <s:if test="TT_HIENTHI.equalsIgnoreCase('5') || TT_HIENTHI.equalsIgnoreCase('8')|| TT_HIENTHI.equalsIgnoreCase('2')|| TT_HIENTHI.equalsIgnoreCase('3')|| TT_HIENTHI.equalsIgnoreCase('4')">
                                    <td align = "right" class="TD_DONVITINH">
                                        <input type="text" value="<s:property  value="D3" />" id="D3_"<s:property  value="%{#rowstatus.index}" />
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number TEN_KH" onfocus="this.select()"
                                       onblur="autoEvaluate()"
                                       />
                                    </td>
                                </s:if>
                                <s:else>
                                    <td align = "right" class="TD_DONVITINH">
                                        <input type="text" value="<s:property  value="D3" />"  id="D3_"<s:property  value="%{#rowstatus.index}" />
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 TEN_KH" onblur="autoEvaluate()"
                                       />
                                    </td>
                                </s:else>

                            </s:else>
                        </s:if>
                        <s:else>
                                <td align = "right" class="TD_DONVITINH">
                                        <input type="text" value="<s:property  value="D3" />"  id="D3_"<s:property  value="%{#rowstatus.index}" />
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="number2 TEN_KH" onfocus="this.select()"                                      
                                       readonly="readonly" onblur="autoEvaluate()"/>
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
            initTable();
        </script>
    </body>
</html>
