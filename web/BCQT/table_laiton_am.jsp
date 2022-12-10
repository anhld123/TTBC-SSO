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
        .cls-over1{
            overflow-x: scroll;
            overflow-y: scroll;            
        }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $(".datepicker_month").datepicker({dateFormat: 'mm/yy'});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_STT").css({"width": "20px"});
                $(".TD_TRANGTHAI").css({"width": "40px"});
                $(".TD_TOTIEN").css({"width": "50px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "130px"});
                $(".TD_SOKU").css({"width": "90px"});
                $(".TD_GL").css({"width": "60px"});
                $(".TD_NGAY").css({"width": "40px"});
                $(".TD_CHITIEU").css({"width": "300px"});
                $(".TD_GHICHU").css({"width": "150px"});
                $(".TEN_KH").css({"width": "100%"});
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
        <s:form id="id_sv_%{khoa_bcqt}" action="SAVE_%{khoa_bcqt}" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                SAO KÊ CÁC MÓN VAY CÓ LÃI TỒN ÂM
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
                <div style="overflow: scroll;overflow-x: scroll;height:450px; width: 110%">
                    <table border="1" class="editDelete" id="tablems05" align="center">
                        <tr>      
                            <th rowspan="1" class="TD_STT">STT</th>                           
                            <!--<th rowspan="2" class="TD_TOTIEN">CIF</th>-->  
                            <th rowspan="1" class="TD_TENKH">Tên KH</th>  
<!--                            <th rowspan="1" class="TD_MAKH">Mã khách hàng</th> -->
                            <th rowspan="1" class="TD_SOKU">Mã khoản vay</th>    
                            <th rowspan="1"  class="TD_TOTIEN">Chương trình tín dụng</th>
                            <th rowspan="1" class="TD_TRANGTHAI">Trạng thái món vay</th> 
                            <th rowspan="1"  class="TD_GL">Tài khoản thu lãi</th>   
                            <th rowspan="1"  class="TD_TOTIEN">Dư nợ</th>   
                            <th rowspan="1"  class="TD_TOTIEN">Dư nợ Khoanh</th> 
                            <th rowspan="1"  class="TD_TOTIEN">Ngày BĐ khoanh</th>   
                            <th rowspan="1"  class="TD_TOTIEN">Ngày thực hiện nợ khoanh trên hệ thống</th>  
                            <th rowspan="1" class="TD_TOTIEN">Ngày hết hạn khoanh</th>  

                            <th rowspan="1" class="TD_TOTIEN">Tổng lãi tồn âm - trong hạn</th> 
                            <th rowspan="1" class="TD_TOTIEN">Tổng lãi tồn âm - quá hạn</th> 
                            <th rowspan="1"  class="TD_TOTIEN">Lãi âm hạch toán thu gốc</th>   
                            <th rowspan="1"  class="TD_TOTIEN">Lãi âm do khoanh nợ sai</th>   
                            <th rowspan="1"  class="TD_TOTIEN">Bút toán hạch toán thu gốc</th> 
                            <th rowspan="1"  class="TD_TOTIEN">Ngày hạch toán thu gốc</th>   
                            <th rowspan="1"  class="TD_GL">Bút toán hạch toán thu lãi</th>  
                            <th rowspan="1" class="TD_TOTIEN">Ngày hạch toán thu lãi</th>  

                        </tr>         

                        <tr style="font-style: italic;">
                            <td style="text-align: center">(1)</td>                            
                            <td style="text-align: center">(2)</td>
                            <td style="text-align: center">(3)</td>
                            <td style="text-align: center">(4)</td>
                            <td style="text-align: center">(5)</td>
                            <td style="text-align: center">(6)</td>
                            <td style="text-align: center">(7)</td>
                            <td style="text-align: center">(8)</td>
                            <td style="text-align: center">(9)</td>
                            <td style="text-align: center">(10)</td>
                            <td style="text-align: center">(11)</td>                                                        
                            <td style="text-align: center">(12)</td>    
                            <td style="text-align: center">(13)</td>   

                            <td style="text-align: center">(14)</td>
                            <td style="text-align: center">(15)</td>
                            <td style="text-align: center">(16)</td>
                            <td style="text-align: center">(17)</td>                                                        
                            <td style="text-align: center">(18)</td>                                   
                            <td style="text-align: center">(19)</td>  

                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">

                            <tr height="22">  
                                <td  align="right" class="TD_TEN_KH">    
                                    <input type="text" value="<s:property  value="THUTU" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                      <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                      <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>"/>
                                      <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN" value="<s:property  value="MACN"/>"/>
                                </td>
                                <td  align="right" class="TD_TEN_KH">    
                                    <input type="text" value="<s:property  value="D2" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                </td>
                                <td  align="right" class="TD_SOKU">    
                                    <input type="text" value="<s:property  value="D9" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" lass="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                                </td>

                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D27" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27" class="D0 TEN_KH" onfocus="this.select()"
                                           readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_TRANGTHAI">
                                    <input type="text" value="<s:property  value="D11" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D0  TEN_KH" onfocus="this.select()"                                           
                                           readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_GL">
                                    <input type="text" value="<s:property  value="D12" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D0  TEN_KH" onfocus="this.select()"
                                           
                                           readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" value="<s:property  value="D14" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D7 number TEN_KH" onfocus="this.select()"
                                           
                                           readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_TOTIEN">
                                    <input type="text" value="<s:property  value="D13" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D0 number TEN_KH" onfocus="this.select()"                                           
                                           readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_TOTIEN">
                                    <input type="text" value="<s:property  value="D18" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="D0 datepicker" placeholder="dd/MM/yyyy"  />
                                </td>
                                <td align = "right" class="TD_TOTIEN">
                                    <input type="text" value="<s:property  value="D15" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D0 datepicker" placeholder="dd/MM/yyyy"/>
                                </td> 
                                <td align = "right" class="TD_TOTIEN">
                                    <input type="text" value="<s:property  value="D37" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D37" class="D0 datepicker" placeholder="dd/MM/yyyy"/>
                                </td>                                                             
                                
                                <td align = "right" class="TD_TOTIEN">
                                    <input type="text" value="<s:property  value="D21" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" class="D11 number TEN_KH" onfocus="this.select()"                                           
                                           readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_TOTIEN">
                                    <input type="text" value="<s:property  value="D22" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" class="D11 number TEN_KH" onfocus="this.select()"
                                            readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_TOTIEN">
                                    <input type="text" value="<s:property  value="D30" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30" class="D11 number TEN_KH" onfocus="this.select()"/>
                                </td>
                                 <td align = "right" class="TD_TOTIEN">
                                    <input type="text" value="<s:property  value="D31" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D31" class="D11 number TEN_KH" onfocus="this.select()"/>
                                </td>
                                <td align = "right" class="TD_TOTIEN">
                                    <input type="text" value="<s:property  value="D32" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D32" class="D11 TEN_KH" onfocus="this.select()"/>
                                </td>
                                <td align = "right" class="TD_GL">
                                    <input type="text" value="<s:property  value="D33" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D33" class="D0 datepicker" placeholder="dd/MM/yyyy"/>
                                </td>
                                 <td align = "right" class="TD_TOTIEN">
                                    <input type="text" value="<s:property  value="D34" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D34" class="D9  TEN_KH" onfocus="this.select()"/>
                                </td>
                                <td align = "right" class="TD_TOTIEN">
                                    <input type="text" value="<s:property  value="D35" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D35" class="D0 datepicker" placeholder="dd/MM/yyyy"/>
                                </td>
                            </tr>


                        </s:iterator>
                    </table>
            </div>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
//            initTable();
        </script>
    </body>
</html>
