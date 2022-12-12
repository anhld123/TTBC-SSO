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
                $(".TD_DONVITINH").css({"width": "60px"});
                $(".TD_THUTU").css({"width": "15px"});
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
                var arrCot = [".D4",".D5",
                    ".D6",".D7",".D8",".D9",
                    ".D10"]; //Luu cac cot cua du lieu can tinh toan
                
                  //Tinh toan cho 7 dong
                for(var i=0; i<18; i++){                       
//                    $(".D8").eq(i).val(parseFloat($(".D6").eq(i).val()) - parseFloat($(".D4").eq(i).val()) );
//                    $(".D9").eq(i).val(parseFloat($(".D7").eq(i).val())- parseFloat($(".D5").eq(i).val()) ); 
//                    $(".D10").eq(i).val(parseFloat($(".D4").eq(i).val()) -  parseFloat($(".D6").eq(i).val()) ); 
//                    $(".D11").eq(i).val(parseFloat($(".D5").eq(i).val())- parseFloat($(".D7").eq(i).val()) );      
                    if(parseFloat($(".D6").eq(i).val()) - parseFloat($(".D4").eq(i).val()) >0)
                        {
                            $(".D8").eq(i).val(parseFloat($(".D6").eq(i).val()) - parseFloat($(".D4").eq(i).val()) );
                            $(".D10").eq(i).val(0);
                        }
                        else
                        {
                            $(".D10").eq(i).val(parseFloat($(".D4").eq(i).val()) -  parseFloat($(".D6").eq(i).val()) );
                            $(".D8").eq(i).val(0);
                        }
                        if(parseFloat($(".D7").eq(i).val())- parseFloat($(".D5").eq(i).val()) >0)
                        {
                            $(".D9").eq(i).val(parseFloat($(".D7").eq(i).val())- parseFloat($(".D5").eq(i).val()) ); 
                             $(".D11").eq(i).val(0);
                        }
                        else
                        {
                            $(".D9").eq(i).val(0);
                            $(".D11").eq(i).val(parseFloat($(".D5").eq(i).val())- parseFloat($(".D7").eq(i).val()) ); 
                        }
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
                BÁO CÁO TỔNG HỢP KIỂM KÊ CÔNG CỤ, DỤNG CỤ
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablems08" align="center">
                <tr>
                    <th rowspan="3"  class="TD_THUTU">STT</th>
                    <th rowspan="3"  class="TD_CHITIEU">Tên Công cụ, dụng cụ</th>
                    <th rowspan="3" class="TD_DONVITINH">Đơn vị tính</th>
                    <th colspan="2"  class="TD_DONVITINH">Số liệu trên sổ theo dõi</th>
                    <th colspan="2" >Số liệu thực tế kiểm kê</th>
                    <th colspan="4">Chênh lệch giữa sổ theo dõi và kiểm kê</th> 
                    <th rowspan="3"  class="TD_THUTU">Mã nhóm CCDC</th>
                </tr>
                <tr>                                  
                    <th rowspan="2"  >Số lượng</th>
                    <th rowspan="2" >Thành tiền</th> 
                    <th rowspan="2"  >Số lượng</th>
                    <th rowspan="2" >Thành tiền</th> 
                    <th colspan="2">Thừa</th>        
                    <th colspan="2">Thừa </th>        
                </tr>
                <tr>                                  
                    <th  >Số lượng</th>
                    <th  >Thành tiền</th> 
                    <th   >Số lượng</th>
                    <th  >Thành tiền</th> 
                </tr>
                <tr>         
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THUTU">1</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_CHITIEU">2</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_DONVITINH">3</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_DONVITINH">4</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_SOLUONG">5</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">6</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">7</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">8=6-4</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">9=7-5</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">10=4-6</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">11=5-7</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_NGUYENGIA">12</th>
                    <!--<th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH"></th>-->
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr height="22">  
                        <td  align="right" class="TD_THUTU">    
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                        </td>
                        <td  align="right" class="TD_CHITIEU">    
                        <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td  align="right" class="TD_DONVITINH">    
                        <input type="text" value="<s:property  value="D1" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        

<!--                        <td align="center" class="TD_DONVITINH">
                            <s:property  value="D1" />  
                        </td>
                                                -->
                        <td align = "right" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D6" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D7" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D8" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D9" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D10" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>  
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D11" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td> 
                    </tr>
                    </s:if>
                    
                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr height="22">                              
                        <td align="center" class="TD_THUTU">
                            <s:property  value="TT_HIENTHI" /> 
                        </td>
                        <td align="left" class="TD_TEN_KH">
                            <s:property  value="TEN" /> 
                        </td>
                        <td align="center" class="TD_DONVITINH">
                            <s:property  value="D1" /> 
                        </td>
                                                
                        <td align = "right" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"/>
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>        
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D6" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   />
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D7" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D8" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D9" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D10" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>       
                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D11" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number2 TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()"
                                   readonly="readonly"/>
                        </td>
                         <td align = "center" class="TD_CHITIEU">
                            <input type="text" value="<s:property  value="D14" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="TEN_KH" readonly="readonly" style="border: 0px; text-align: center;"/>
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
