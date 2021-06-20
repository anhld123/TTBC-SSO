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
        <!--<script src="chamdiem_canhan/js/chamdiem_canhan.js"></script>-->  
        <script>

            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_THUTU").css({"width": "1.5%"});
                $(".TD_SOLUONG").css({"width": "4%"});
                $(".TD_DIEM").css({"width": "3%"});
                $(".TD_TYLE").css({"width": "4%"});
                $(".TD_THEMXOA").css({"width": "5%"});
                $(".TD_CBTH").css({"width": "12%"});
                $(".TD_CHITIEU").css({"width": "20%"});
                $(".TD_NOIDUNG").css({"width": "12%"});
                $(".TD_NOIDUNG06A").css({"width": "35%"});
                $(".hideColumn").hide();
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
        <s:form id="id_SaveConfig" name="name_SaveConfig" action="SaveConfig" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />"  
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>

            <div id="divTitle">
                CÀI ĐẶT BỘ CHỈ TIÊU
            </div>                        
            <s:hidden name="khoa_cdtt"/>            

            <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">                          
                <tr height="27">
                    <th rowspan="1" class="TD_THUTU">TT</th>
                    <th rowspan="1" class="TD_THUTU">TT Hiển thị</th>
                    <th rowspan="1"  class="TD_SOLUONG">Mã chỉ tiêu</th> 
                    <th rowspan="1" class="TD_CHITIEU">Chỉ tiêu</th>  
                    <th rowspan="1"  class="TD_DIEM">Điểm tối đa</th>  
                    <th rowspan="1"  class="TD_DIEM">% điểm</th> 
                    <th rowspan="1"  class="TD_DIEM">Đơn vị tính</th> 
                    <th rowspan="1"  class="TD_SOLUONG">Công thức</th>                                          
                    <th rowspan="1"  class="TD_DIEM">Nhập tay</th> 
                    <th rowspan="1"  class="TD_DIEM">Hiển thị</th> 
                </tr>                  
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                    <tr height="16" class="<s:property  value="D30" />"> 

                        <td align = "center" class="TD_THUTU">
                            <input type="text" value="<s:property  value="THUTU" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="TEN_KH D0" readonly="true"/>
                        </td>
                        <td align = "center" class="TD_THUTU">
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0"/>
                        </td>
                        <td align = "right" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="MA" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" class="TEN_KH" readonly="true"/>
                        </td>
                        <td align = "left" class="TD_CHITIEU">
                            <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH"
                                   <s:if test="TEN.equalsIgnoreCase('HTXS') || TEN.equalsIgnoreCase('HTT') || TEN.equalsIgnoreCase('HT') || TEN.equalsIgnoreCase('KHT')">readonly="true"</s:if>    />
                        </td>
                        <td align = "right" class="TD_DIEM">
                            <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property  value="MA" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0"
                                   <s:if test="TEN.equalsIgnoreCase('HTXS') || TEN.equalsIgnoreCase('HTT') || TEN.equalsIgnoreCase('HT') || TEN.equalsIgnoreCase('KHT')">readonly="true"</s:if>/>
                        </td>
                        <td align = "right" class="TD_DIEM">
                            <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0"/>
                        </td>  
                        
                        <td align = "right" class="TD_DIEM">
                            <input type="text" value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0"/>
                        </td>
                        <td align = "right" class="TD_SOLUONG">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH" />
                        </td>

                        <td align = "left" class="TD_DIEM">                                        
                                <s:select 
                                    id="lstDulieuNt[%{#rowstatus.index}].NHAPTAY"
                                    name="lstDulieuNt[%{#rowstatus.index}].NHAPTAY"
                                    list="lstUser" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="vertical-align: middle;width:100%;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td>
                            
                        <td align = "left" class="TD_DIEM">                                        
                                <s:select 
                                    id="lstDulieuNt[%{#rowstatus.index}].D17"
                                    name="lstDulieuNt[%{#rowstatus.index}].D17"
                                    list="lstUser" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="vertical-align: middle;width:100%;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td>    



                    </tr>        
                </s:iterator>
            </table>                                                                    

            <sj:submit id="ID_SAVE_CONFIG" name="Name_save" value="save" formIds="id_SaveConfig" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss"/>
             <!--cssStyle="display: none"-->
        </s:form>                    
        <div id="luu_thanhcong"></div>
    </body>
</html>
