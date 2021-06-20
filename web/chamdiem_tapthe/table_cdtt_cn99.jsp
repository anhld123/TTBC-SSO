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
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
//                $('.number').number(true, 0);
//                $('input.number').css({"text-align": "right"});
//                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
//                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
//                $('.number2').number(true, 2);
                $(".TD_THUTU").css({"width": "2%"});
                $(".TD_TEN_KH").css({"width": "50px"});
                $(".TD_TENTS").css({"width": "120px"});
                $(".TD_MATS").css({"width": "85px"});
                $(".TD_NGUYENGIA").css({"width": "8%"});
                $(".TD_THOIGIAN").css({"width": "6%"});
                $(".TD_SOLUONG").css({"width": "4%"});
                $(".TD_TYLE").css({"width": "4%"});
                $(".TD_THEMXOA").css({"width": "5%"});
                $(".TD_CBTH").css({"width": "12%"});
                $(".TD_GHICHU").css({"width": "8%"});
                $(".TD_CHITIEU").css({"width": "20%"});
                $(".TD_NOIDUNG").css({"width": "10%"});
                $(".TD_NOIDUNG06A").css({"width": "35%"});
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
        <s:form id="id_sv_%{khoa_cdtt}" action="SAVE_%{khoa_cdtt}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />"  
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                PL99/ĐGXL - Khai báo thông tin cá nhân
            </div>                        
            <s:hidden name="khoa_cdtt"/>            
            <table border="1" class="editDelete" id="tablecdtt99" align="center">                
                <table border="1" class="editDelete" id="tablecdtt99" align="center">
                    <tr height="23">
                        <th  class="TD_THUTU">TT</th>
                        <th  class="TD_SOLUONG">Mã cán bộ</th>  
                        <th  class="TD_GHICHU">Tên cán bộ</th>  
                        <th   class="TD_THOIGIAN">Chức vụ</th>                         
                        <th   class="TD_THOIGIAN">Phòng/ban</th>  
                        <th  class="TD_THOIGIAN">Số CMND</th> 
                        <th  class="TD_GHICHU">User TTBC</th> 
                        <th  class="TD_THOIGIAN">Nhóm công việc</th> 
                    </tr>  
                    
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                            
                            <tr>  
                                <td align = "center" class="TD_THUTU">
                                    <input type="text"  value="<s:property  value="TT_HIENTHI" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0"
                                           readonly="true"/>
                                    <input type="hidden" value="<s:property  value="D9" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/> 
                                    <input type="hidden" value="<s:property  value="D10" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10"/> 
                                </td>
                                <td align = "left" class="TD_SOLUONG">
                                    <input type="text"  value="<s:property  value="D1" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH"
                                           readonly="true"/>
                                </td>
                                <td align = "left" class="TD_GHICHU">
                                    <input type="text" value="<s:property  value="D2" />"  
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH"
                                           readonly="true"/>
                                </td>
                                <td align = "center" class="TD_THOIGIAN">
                                    <input type="text" value="<s:property  value="D3" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" readonly="true"/>
                                </td>                            

                                <td align = "center" class="TD_THOIGIAN">
                                    <input type="text" value="<s:property  value="D4" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH"  readonly="true"/>
                                </td>
                                <td align = "center" class="TD_THOIGIAN">
                                    <input type="text" value="<s:property  value="D5" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH"  readonly="true"/>
                                </td>
                                <td align = "left" class="TD_GHICHU">                                        
                                    <s:select  
                                        id="lstDulieuNt[%{#rowstatus.index}].D6"
                                        name="lstDulieuNt[%{#rowstatus.index}].D6"
                                        list="lstUser" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        headerKey="-1"
                                        headerValue="--- Chọn ---"
                                        cssStyle="vertical-align: middle;width:100%;background-color: #FFCCBA; TEN_KH">
                                    </s:select>
                            </td>  
                            
                            <td align = "left" class="TD_THOIGIAN">                                        
                                    <s:select  
                                        id="lstDulieuNt[%{#rowstatus.index}].D7"
                                        name="lstDulieuNt[%{#rowstatus.index}].D7"
                                        list="lstFuncTTBC" 
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
            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
