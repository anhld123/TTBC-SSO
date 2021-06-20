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
        <script src="BCQT/javascript/congcap_m20.js"></script>
        <script>
            $(document).ready(function() {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function() {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function() {
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
                BÁO CÁO TÌNH HÌNH TĂNG GIẢM VỐN, QUỸ
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablepl01" align="center">
                <colgroup>
                    <col style="mso-width-source:userset;mso-width-alt:8832;width:207pt" width="276" />
                    <col style="mso-width-source:userset;mso-width-alt:2144;width:50pt" width="67" />
                    <col style="mso-width-source:userset;mso-width-alt:3520;width:83pt" width="110" />
                    <col span="2" style="mso-width-source:userset;mso-width-alt:2144;
                         width:50pt" width="67" />
                    <col style="mso-width-source:userset;mso-width-alt:2752;width:65pt" width="86" />
                    <col style="mso-width-source:userset;mso-width-alt:2688;width:63pt" width="84" />
                    <col style="mso-width-source:userset;mso-width-alt:2144;width:50pt" width="67" />
                    <col style="mso-width-source:userset;mso-width-alt:2624;width:62pt" width="82" />
                    <col style="mso-width-source:userset;mso-width-alt:3904;width:92pt" width="122" />
                </colgroup>
                <tr height="19" style="height:14.25pt">
                    <th class="TD_TEN_KH" height="69" rowspan="2" style="height: 51.75pt; width: 207pt" width="276">
                        Diễn giải</th>
                    <th class="TD_TEN_KH" colspan="4" style="width: 233pt" width="311">VỐN 
                        CHỦ SỞ HỮU</th>
                    <th class="TD_TEN_KH" colspan="4" style="width: 240pt" width="319">QUỸ</th>
                    <th class="TD_TEN_KH" rowspan="2" style="width: 92pt" width="122">LỢI 
                        NHUẬN CHƯA PHÂN PHỐI</th>
                </tr>
                <tr height="50" style="mso-height-source:userset;height:37.5pt">
                    <th class="TD_TEN_KH" height="50" style="height: 37.5pt; width: 50pt" width="67">
                        Vốn điều lệ</th>
                    <th class="TD_TEN_KH" style="width: 83pt" width="110">Vốn XDCB, mua 
                        sắm TSCĐ</th>
                    <th class="TD_TEN_KH" style="width: 50pt" width="67">Vốn khác</th>
                    <th class="TD_TEN_KH" style="width: 50pt" width="67">Tổng vốn CSH</th>
                    <th class="TD_TEN_KH" style="width: 65pt" width="86">Quỹ bổ sung vốn</th>
                    <th class="TD_TEN_KH" style="width: 63pt" width="84">Quỹ khen thưởng</th>
                    <th class="TD_TEN_KH" style="width: 50pt" width="67">Quỹ phúc lợi</th>
                    <th class="TD_TEN_KH" style="width: 62pt" width="82">Tổng Quỹ</th>
                </tr>
                <tr height="22" style="mso-height-source:userset;height:16.5pt">
                    <th class="TD_TEN_KH" height="22" style="height: 16.5pt;">1</th>
                    <th class="TD_TEN_KH">2</th>
                    <th class="TD_TEN_KH" style="width: 83pt" width="110">3</th>
                    <th class="TD_TEN_KH">4</th>
                    <th class="TD_TEN_KH">5=2+3+4</th>
                    <th class="TD_TEN_KH">6</th>
                    <th class="TD_TEN_KH" style="width: 63pt" width="84">7</th>
                    <th class="TD_TEN_KH">8</th>
                    <th class="TD_TEN_KH">9=6+7+8</th>
                    <th class="TD_TEN_KH">10</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>                                                                                                  
                        <td>
                            <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" 
                                   class="TEN_KH" onfocus="this.select()" 
                                   <s:if test='!TT_HIENTHI.isEmpty() '> readonly="true" </s:if>
                                       />
                            </td>
                            <td>
                                <input type="text" id="D1_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D1" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                   class="TEN_KH number2" onfocus="this.select();"
                                   onblur="sumColumn_byRow('D1');"
                                   <s:if test='!TT_HIENTHI.isEmpty() '> readonly="true" </s:if>/>
                            </td>
                            <td>
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D2" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"
                                   onblur="sumColumn_byRow('D2');"
                                   <s:if test='!TT_HIENTHI.isEmpty() '> readonly="true" </s:if>/>
                            </td>
                            <td>
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D3" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"
                                   onblur="sumColumn_byRow('D3');"
                                   <s:if test='!TT_HIENTHI.isEmpty() '> readonly="true" </s:if>/>
                            </td>
                            <td>
                                <input type="text" id="D4_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D4" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"
                                   onblur="sumColumn_byRow('D4');"
                                   <s:if test='!TT_HIENTHI.isEmpty() '> readonly="true" </s:if>/>                        
                            </td>
                            <td>
                                <input type="text" id="D5_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D5" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"
                                   onblur="sumColumn_byRow('D5');"
                                   <s:if test='!TT_HIENTHI.isEmpty() '> readonly="true" </s:if>/>
                            </td>                           
                            <td>
                                <input type="text" id="D6_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D6" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"                                   
                                   onblur="sumColumn_byRow('D6');"
                                   <s:if test='!TT_HIENTHI.isEmpty() '> readonly="true" </s:if>/>
                            </td>
                            <td>
                                <input type="text" id="D7_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D7" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"   
                                   onblur="sumColumn_byRow('D7');"
                                   <s:if test='!TT_HIENTHI.isEmpty() '> readonly="true" </s:if>/>
                            </td>       

                            <td>
                                <input type="text" id="D8_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D8" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"           
                                   onblur="sumColumn_byRow('D8');"
                                   <s:if test='!TT_HIENTHI.isEmpty() '> readonly="true" </s:if>/>
                            </td>       

                            <td>
                                <input type="text" id="D9_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D9" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"        
                                   onblur="sumColumn_byRow('D9');"
                                   <s:if test='!TT_HIENTHI.isEmpty() '> readonly="true" </s:if>/>
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

        </table>
        <input type="hidden" name="row_total" 
               value="<s:property value='%{#st_total}'/>" id="row_total_ID"/>    

        <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                   onCompleteTopics="completediv_ss" cssStyle="display: none"/>
    </s:form>
</body>
</html>
