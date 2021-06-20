<%-- 
    Document   : table-chuongtrinh-loaitru
    Created on : Feb 21, 2020, 3:28:37 PM
    Author     : BAOANH
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
        <script src="chamdiem_tapthe/js/chamdiem_tapthe.js"></script>  
        <script>
            $(document).ready(function () {

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
                <font color="red">CẤU HÌNH CHƯƠNG TRÌNH LOẠI TRỪ KHI CHẠY SỐ LIỆU CHO PGD VÀ CHI NHÁNH</font>
            </div>    
            <!--Đối với cấp chi nhánh-->
            <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                <tr height="23">
                    <th class="TD_THUTU">Mã CT</th>
                    <th class="TD_CHITIEU">Tên chương trình</th>                 
                    <th class="TD_SOLUONG">Tháng áp dụng</th>  
                    <th><s:checkbox name="Select All" id="select_all1" theme="simple" /></th>
                    <th><s:checkbox name="Select All" id="select_all2" theme="simple" /></th>
                    <th><s:checkbox name="Select All" id="select_all3" theme="simple" /></th>
                    <th><s:checkbox name="Select All" id="select_all4" theme="simple" /></th>
                    <th><s:checkbox name="Select All" id="select_all5" theme="simple" /></th>
                    <th><s:checkbox name="Select All" id="select_all6" theme="simple" /></th>
                    <th><s:checkbox name="Select All" id="select_all7" theme="simple" /></th>
                    <th><s:checkbox name="Select All" id="select_all8" theme="simple" /></th>
                    <th><s:checkbox name="Select All" id="select_all9" theme="simple" /></th>
                    <th><s:checkbox name="Select All" id="select_all10" theme="simple" /></th>
                    <th><s:checkbox name="Select All" id="select_all11" theme="simple" /></th>
                    <th><s:checkbox name="Select All" id="select_all12" theme="simple" /></th>
                </tr>                
                <s:iterator value="#attr.lstChtrinhLoaitru" var="modelView" status="rowstatus">                    
                    <tr height="16">                      
                        <td align="center" class="TD_CHITIEU">
                            <s:property  value="mact" />
                            <input type="text" value="<s:property  value="mact" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" class="TEN_KH" onfocus="this.select()" style="display: none"/>                                  
                        </td>
                        <td align="left" class="TD_CHITIEU">
                            <s:property  value="tenct" />
                            <input type="text" value="<s:property  value="tenct" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()" style="display: none"/>                                  
                        </td>

                        <td>
                            <s:property  value="T1" />
                            <s:checkbox name="T1" fieldValue="true"  ></s:checkbox>
                            <!--<s:checkboxlist label="Tháng áp dụng" list="monthList" name="yourColor" value="monthDefault" />                            
                            <s:checkboxlist list="thangApdung" value="monthDefault" listKey="sKey" listValue="sKey" name="rptGrade"></s:checkboxlist>   -->
                            </td>
                        </tr>       
                </s:iterator>
            </table>


            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>        
    </body>
</html>
