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
        <script src="BCQT/javascript/congcap_m21.js"></script>
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
                BÁO CÁO TRÍCH LẬP VÀ SỬ DỤNG DỰ PHÒNG RỦI RO
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablepl01" align="center">
                <colgroup>
                    <col style="mso-width-source:userset;mso-width-alt:1024;width:24pt" width="32" />
                    <col style="mso-width-source:userset;mso-width-alt:7360;width:173pt" width="230" />
                    <col style="mso-width-source:userset;mso-width-alt:3552;width:83pt" width="111" />
                    <col style="mso-width-source:userset;mso-width-alt:5120;width:120pt" width="160" />
                    <col style="mso-width-source:userset;mso-width-alt:6240;width:146pt" width="195" />
                    <col style="mso-width-source:userset;mso-width-alt:5088;width:119pt" width="159" />
                    <col style="mso-width-source:userset;mso-width-alt:5344;width:125pt" width="167" />
                </colgroup>
                <tr height="106" style="mso-height-source:userset;height:79.5pt">
                    <th class="TD_TEN_KH" height="106" style="height: 79.5pt; width: 24pt" width="32">
                        Stt</th>
                    <th class="TD_TEN_KH" style="width: 173pt" width="230">Chỉ tiêu</th>
                    <th class="TD_TEN_KH" style="width: 83pt" width="111">
                        <span style="mso-spacerun:yes">&nbsp;</span>Dự phòng tín dụng </th>
                    <th class="TD_TEN_KH" style="width: 120pt" width="160">
                        <span style="mso-spacerun:yes">&nbsp;</span>Dự phòng cho vay bằng vốn nhận trực tiếp TCQT<span style="mso-spacerun:yes">&nbsp;</span></th>
                    <th class="TD_TEN_KH" style="width: 146pt" width="195">
                        <span style="mso-spacerun:yes">&nbsp;</span>Dự phòng cho vay bằng vốn nhận của Chính phủ<span style="mso-spacerun:yes">&nbsp; &nbsp;</span></th>
                    <th class="TD_TEN_KH" style="width: 119pt" width="159">
                        <span style="mso-spacerun:yes">&nbsp;</span>Dự phòng cho vay bằng vốn nhận tổ chức khác<span style="mso-spacerun:yes">&nbsp;</span></th>
                    <th class="TD_TEN_KH" style="width: 125pt" width="167">
                        <span style="mso-spacerun:yes">&nbsp;</span>Dự phòng rủi ro tỷ giá<span style="mso-spacerun:yes">&nbsp;</span></th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>  
                        <td align="center" class="TD_TEN_KH">                                                
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" 
                                   class="TEN_KH" onfocus="this.select()" 
                                   style="text-align: center;"
                                   <s:if test='!TT_HIENTHI.isEmpty() '> readonly="true" </s:if>/>
                            </td>
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
