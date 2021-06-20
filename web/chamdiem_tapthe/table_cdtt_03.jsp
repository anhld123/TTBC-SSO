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
                $(".TD_DONVITINH").css({"width": "50px"});
                $(".TD_SOLUONG").css({"width": "6%"});
                $(".TD_NOIDUNG").css({"width": "12%"});
                $(".TD_NGUYENGIA").css({"width": "5%"});
                $(".TD_THUTU").css({"width": "2.5%"});
                $(".TD_CHITIEU").css({"width": "40%"});
                
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
                Lịch sử chốt số liệu
            </div>
            <s:hidden name="khoa_cdtt"/>
            <s:hidden name="tt_cdtt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
                        
            
            <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                <tr height="23">
                    <th class="TD_THUTU">TT</th>
                    <th class="TD_NGUYENGIA">Mã PGD</th>                                         
                    <th class="TD_SOLUONG">Cấp thực hiện</th>  
                    <th class="TD_SOLUONG">Người thực hiện</th>  
                    <th class="TD_NOIDUNG">Ngày thực hiện</th>                       
                    <th class="TD_NOIDUNG">Nội dung</th>  
                    <th class="TD_CHITIEU">Ghi chú</th>  
                </tr>                                
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                <tr height="16">                      
                        <td  align="right" class="TD_THUTU">    
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                                                    
                        </td>
                        <td  align="left" class="TD_NGUYENGIA">                              
                            <input type="text" value="<s:property  value="MAPGD" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td align="center" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="CO_TONGHOP" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td  align="center" class="TD_SOLUONG"> 
                            <input type="text" value="<s:property  value="NGUOI_NHAP" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_NHAP" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        
                        <td  align="center" class="TD_NOIDUNG"> 
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        
                        <td  align="left" class="TD_NOIDUNG"> 
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        
                        <td  align="center" class="TD_CHITIEU"> 
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>                        
                    </tr>                    
                    
                </s:iterator>
            </table>
            
            <p></p>            
                <div align = "left" id="divTitle">
                    <a id="myLink" href="#" onclick="javascript:hienthichitiet1();return false;"> &nbsp;&nbsp; &nbsp;CN-PL01 -> CN-PL01</a>
                    <p></p>
                    <a align = "left" id="myLink" href="#" onclick="javascript:hienthichitiet2();return false;"> &nbsp;&nbsp;&nbsp; SGD-PL02 -> SGD-PL02</a>
                    <p></p>
                    <a align = "left" id="myLink" href="#" onclick="javascript:hienthichitiet2();return false;"> &nbsp;&nbsp;&nbsp; TTĐT-PL04 -> TTĐT-PL04</a>
               
                </div>
            
            
            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>        
    </body>
</html>
