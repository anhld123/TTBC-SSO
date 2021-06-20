<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<style>
    input[readonly] {
        /*styling info here*/
        background-color: #99ffff;
    }
</style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="BCQT/javascript/congcap_m17.js"></script>
        <script>
            $(document).ready(function() {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $('input.TOTAL_ROW').css({"text-align": "right"});
                $('input.TOTAL_ROW').css({"font-weight": "bold"});
                $('.TOTAL_ROW').number(true, 0);
                $(".TD_HEADER").css({"width": "30px"});
                $(".TEXT_COL").css({"width": "100%"});
                set_total_Row();
            });
            $('.TEXT_COL').focus(function() {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEXT_COL').blur(function() {
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
                TỔNG HỢP SAO KÊ SỐ DƯ TIỀN GỬI KHÁCH HÀNG
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                <a href="table_bcqt_pl01.jsp"></a>
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablepl01" align="center">
                <colgroup>
                    <col style="mso-width-source:userset;mso-width-alt:704;width:17pt" width="22" />
                    <col style="mso-width-source:userset;mso-width-alt:9920;width:233pt" width="310" />
                    <col span="3" style="mso-width-source:userset;mso-width-alt:2592;
                         width:61pt" width="81" />
                    <col span="2" style="mso-width-source:userset;mso-width-alt:2080;
                         width:49pt" width="65" />
                    <col span="2" style="mso-width-source:userset;mso-width-alt:2048;
                         width:48pt" width="64" />
                </colgroup>
                <tr height="21" style="mso-height-source:userset;height:15.75pt">
                    <th class="TD_HEADER" height="56" rowspan="2" style="height: 42.0pt; width: 17pt" width="22">
                        Stt</th>
                    <th class="TD_HEADER" rowspan="2" style="width: 233pt" width="310" >Chỉ 
                        tiêu</th>
                    <th class="TD_HEADER" rowspan="2" style="width: 61pt" width="81">Tổng 
                        số sổ, số tài khoản tiền gửi</td>
                    <th class="TD_HEADER" rowspan="2" style="width: 61pt" width="81">Số dư 
                        trên sao kê</th>
                    <th class="TD_HEADER" rowspan="2" style="width: 61pt" width="81">Số dư 
                        trên sổ kế toán</th>
                    <th class="TD_HEADER" colspan="2" style="width: 98pt" width="130">
                        Chênh lệch</t>
                    <th class="TD_HEADER" rowspan="2" style="width: 48pt" width="64">Lãi 
                        dự trả</th>
                    <th class="TD_HEADER" rowspan="2" style="width: 48pt" width="64">Lãi 
                        đã trả</th>                    
                </tr>
                <tr height="35" style="mso-height-source:userset;height:26.25pt">
                    <th class="TD_HEADER" height="35" style="height: 26.25pt;">Thừa<span style="mso-spacerun:yes">&nbsp;</span></th>
                    <th class="TD_HEADER">Thiếu</th>
                </tr>
                <tr height="19" style="mso-height-source:userset;height:14.25pt">
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_HEADER" height="19" style="height: 14.25pt;">1</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_HEADER">2</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_HEADER">3</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_HEADER">4</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_HEADER">5</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_HEADER">6=4-5</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_HEADER">7=5-4</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_HEADER">8</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_HEADER">9</th>                    
                </tr>


                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>  
                        <td align="center" class="TD_HEADER">                                                
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" 
                                   class="TEXT_COL" onfocus="this.select()" 
                                   style="text-align: center;"
                                   <s:if test='!TT_HIENTHI.isEmpty() && !TT_HIENTHI.equals("4") && !TT_HIENTHI.equals("8") && !TT_HIENTHI.equals("10")'> readonly="true" </s:if>/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" 
                                   class="TEXT_COL" onfocus="this.select()" 
                                   <s:if test='!TT_HIENTHI.isEmpty() && !TT_HIENTHI.equals("4") && !TT_HIENTHI.equals("8") && !TT_HIENTHI.equals("10")'> readonly="true" </s:if>
                                       />
                            </td>
                            <td>
                                <input type="text" id="D1_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D1" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                   class="NUM_COL number2" onfocus="this.select();"
                                   onblur="sumColumn_byRow('D1');"
                                   <s:if test='!TT_HIENTHI.isEmpty() && !TT_HIENTHI.equals("4") && !TT_HIENTHI.equals("8") && !TT_HIENTHI.equals("10")'> readonly="true" </s:if>/>
                            </td>
                            <td>
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D2" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                   class="NUM_COL number2" 
                                   onfocus="this.select();"
                                   onblur="sumColumn_byRow('D2');"
                                   <s:if test='!TT_HIENTHI.isEmpty() && !TT_HIENTHI.equals("4") && !TT_HIENTHI.equals("8") && !TT_HIENTHI.equals("10")'> readonly="true" </s:if>/>
                            </td>
                            <td>
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D3" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                   class="NUM_COL number2" 
                                   onfocus="this.select();"
                                   onblur="sumColumn_byRow('D3');"
                                   <s:if test='!TT_HIENTHI.isEmpty() && !TT_HIENTHI.equals("4") && !TT_HIENTHI.equals("8") && !TT_HIENTHI.equals("10")'> readonly="true" </s:if>/>
                            </td>
                            <td>
                                <input type="text" id="D4_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D4" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                   class="NUM_COL number2" 
                                   onfocus="this.select();"
                                   onblur="sumColumn_byRow('D4');"
                                   <s:if test='!TT_HIENTHI.isEmpty() && !TT_HIENTHI.equals("4") && !TT_HIENTHI.equals("8") && !TT_HIENTHI.equals("10")'> readonly="true" </s:if>/>                        
                            </td>
                            <td>
                                <input type="text" id="D5_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D5" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                   class="NUM_COL number2" 
                                   onfocus="this.select();"
                                   onblur="sumColumn_byRow('D5');"
                                   <s:if test='!TT_HIENTHI.isEmpty() && !TT_HIENTHI.equals("4") && !TT_HIENTHI.equals("8") && !TT_HIENTHI.equals("10")'> readonly="true" </s:if>/>
                            </td>
                            <td>
                                <input type="text" id="D6_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D6" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                   class="NUM_COL number2" 
                                   onfocus="this.select();"
                                   onblur="sumColumn_byRow('D6');"
                                   <s:if test='!TT_HIENTHI.isEmpty() && !TT_HIENTHI.equals("4") && !TT_HIENTHI.equals("8") && !TT_HIENTHI.equals("10")'> readonly="true" </s:if>/>
                            </td>
                            <td>
                                <input type="text" id="D7_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D7" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                   class="NUM_COL number2" 
                                   onfocus="this.select();"
                                   onblur="sumColumn_byRow('D7');"
                                   <s:if test='!TT_HIENTHI.isEmpty() && !TT_HIENTHI.equals("4") && !TT_HIENTHI.equals("8") && !TT_HIENTHI.equals("10")'> readonly="true" </s:if>/>
                            </td>       

                            <!-- CAC TRUONG HIDDEN DUNG DE CAP NHAT    -->
                        <input type="hidden" id="CAP_<s:property  value="%{#rowstatus.index}" />"      
                           value="<s:property  value="CAP" />"/>
                    <input type="hidden" id="CO_CONGCAP_<s:property  value="%{#rowstatus.index}" />"      
                           value="<s:property  value="CO_CONGCAP" />"/>
                    <input type="hidden" value="<s:property  value="MA" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" />                        
                </tr>
                <s:set var="st_total" value = "lstDulieuNt.size()" />

            </s:iterator>
            <tr>                
                <td class="TD_HEADER" bgcolor="#99ffff" colspan="2" align="center"><b>TỔNG CỘNG</b></td>
                <th class="TD_HEADER">
                    <input type="text" value="" 
                           name="TONG_D1" 
                           id="TONG_D1" 
                           class="TOTAL_ROW" 
                           onfocus="this.select()"   
                           readonly="true"
                           />
                </th>
                <th class="TD_HEADER">
                    <input type="text" value="" 
                           name="TONG_D2" 
                           id="TONG_D2" 
                           class="TOTAL_ROW"
                           onfocus="this.select()" 
                           readonly="true"
                           />
                </th>
                <th class="TD_HEADER">
                    <input type="text" value="" 
                           name="TONG_D3" 
                           id="TONG_D3" 
                           class="TOTAL_ROW" 
                           onfocus="this.select()" 
                           readonly="true"
                           />
                </th>
                <th class="TD_HEADER">
                    <input type="text" value="" 
                           name="TONG_D4" 
                           id="TONG_D4" 
                           class="TOTAL_ROW" 
                           onfocus="this.select()" 
                           readonly="true"
                           />
                </th>
                <th class="TD_HEADER">
                    <input type="text" value="" 
                           name="TONG_D5" 
                           id="TONG_D5" 
                           class="TOTAL_ROW" 
                           onfocus="this.select()" 
                           readonly="true"
                           />
                </th>
                <th class="TD_HEADER">
                    <input type="text" value="" 
                           name="TONG_D6" 
                           id="TONG_D6" 
                           class="TOTAL_ROW" 
                           onfocus="this.select()" 
                           readonly="true"
                           />
                </th>
                <th class="TD_HEADER">
                    <input type="text" value="" 
                           name="TONG_D7" 
                           id="TONG_D7" 
                           class="TOTAL_ROW" 
                           onfocus="this.select()" 
                           readonly="true"
                           />
                </th>
            </tr>

        </table>
        <input type="hidden" name="row_total" 
               value="<s:property value='%{#st_total}'/>" id="row_total_ID"/>    

        <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                   onCompleteTopics="completediv_ss" cssStyle="display: none"/>
    </s:form>
</body>
</html>
