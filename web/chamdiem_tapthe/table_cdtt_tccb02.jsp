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
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
//                $('.number').number(true, 0);
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_THUTU").css({"width": "3%"});
                $(".TD_TEN_KH").css({"width": "50px"});
                $(".TD_TENTS").css({"width": "120px"});
                $(".TD_MATS").css({"width": "85px"});
                $(".TD_NGUYENGIA").css({"width": "8%"});
                $(".TD_10").css({"width": "10%"});
                $(".TD_5").css({"width": "2%"});
                $(".TD_15").css({"width": "15%"});
                $(".TD_SOLUONG").css({"width": "4%"});
                $(".TD_TYLE").css({"width": "4%"});
                $(".TD_THEMXOA").css({"width": "8%"});
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
                TỔNG HỢP ĐÁNH GIÁ THỰC HIỆN NHIỆM VỤ CỦA TẬP THỂ
            </div>                        
            <s:hidden name="khoa_cdtt"/>            
            <table border="1" class="editDelete" id="tablecdtt99" align="center">                
                <table border="1" class="editDelete" id="tablecdtt99" align="center">
                    <tr height="45">
                        <th rowspan="2"  class="TD_THUTU">TT</th>
                        <th rowspan="2"  class="TD_THEMXOA">Tên chi nhánh</th>  
                        <th colspan="12" class="TD_5">KÊT QUẢ XẾP LOẠI HÀNG THÁNG</th>  
                        <th rowspan="2"  class="TD_5">Tổng cộng số tháng xếp loại A</th>                         
                        <th rowspan="2"  class="TD_5">Tổng cộng số tháng xếp loại B</th> 
                        <th rowspan="2"  class="TD_5">Tổng cộng số tháng xếp loại C</th> 
                        <th rowspan="2"  class="TD_5">Tổng cộng số tháng xếp loại D</th> 
                        <th colspan="7" class="TD_5">KẾT QUẢ THỰC HIỆN CÁC CHỈ TIÊU NĂM</th>   
                        <th rowspan="2"  class="TD_NGUYENGIA">Đơn thư, kỷ luật,vụ việc theo quy định VB 3936 Có (ghi cụ thể) /không</th>
                        <th rowspan="2"  class="TD_5">Kết quả xếp loại năm</th>                        
                        <th rowspan="2"  class="TD_10">Ghi chú</th> 
                    </tr>  
                    <tr height="35">
                         <th  class="TD_5">Tháng 01</th>  
                         <th  class="TD_5">Tháng 02</th>  
                         <th  class="TD_5">Tháng 03</th>  
                         <th  class="TD_5">Tháng 04</th>  
                         <th  class="TD_5">Tháng 05</th>  
                         <th  class="TD_5">Tháng 06</th>  
                         <th  class="TD_5">Tháng 07</th>  
                         <th  class="TD_5">Tháng 08</th>  
                         <th  class="TD_5">Tháng 09</th>  
                         <th  class="TD_5">Tháng 10</th>  
                         <th  class="TD_5">Tháng 11</th>  
                         <th  class="TD_5">Tháng 12</th>  
                         <th  class="TD_5">Tăng trưởng tín dụng (Nguồn vồn TW)(%/năm)</th>                              
                         <th  class="TD_5">Huy động vốn thị trường (%/năm)</th>  
                         <th  class="TD_5">Nguồn vốn ủy thác đại phương (%/năm)</th>  
                         <th  class="TD_5">Kế hoạch tài chính (%/năm)</th>  
                         <th  class="TD_5">Kế hoạch kiểm tra, giám sát của Ban đại diện HĐQT chi nhánh cấp tỉnh (%/năm)</th>  
                         <th  class="TD_5">Kế hoạch kiểm tra,  kiểm soát nội bộ chi nhánh cấp tỉnh (%/năm)</th>  
                         <th  class="TD_5">Tỷ lệ Nợ quá hạn(%)</th>  
                    </tr>
                    
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                            
                            <tr>  
                                <td align = "left" class="TD_5">
                                    <input type="text"  value="<s:property  value="THUTU" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="TEN_KH number"
                                           readonly="true"/>
                                </td>
                                <td align = "left" class="TD_THEMXOA">
                                    <input type="text"  value="<s:property  value="TEN" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH"
                                           readonly="true"/>
                                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                        name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN" value="<s:property  value="MACN"/>"/> 
                                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                        name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" value="<s:property  value="D28"/>"/> 
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D1" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D2" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D3" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH"
                                                   readonly="true"/>
                                </td>                                
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D4" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D5" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D6" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D7" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D8" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D9" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D10" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D11" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D12" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D13" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH number"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D14" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="TEN_KH number"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D15" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="TEN_KH number"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D16" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="TEN_KH number"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D17" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="TEN_KH number2"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D18" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH number2"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D19" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="TEN_KH number2"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D20" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="TEN_KH number2"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D21" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" class="TEN_KH number2"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D22" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" class="TEN_KH number2"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                        <input type="text"  value="<s:property  value="D23" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23" class="TEN_KH number2"
                                                   readonly="true"/>
                                </td>
                                <td align = "left" class="TD_NGUYENGIA">
                                        <input type="text"  value="<s:property  value="D24" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24" class="TEN_KH"
                                                   readonly="true"/>
                                </td>

                                <td align = "left" class="TD_5">                                        
                                    <s:select  
                                        id="lstDulieuNt[%{#rowstatus.index}].D25"
                                        name="lstDulieuNt[%{#rowstatus.index}].D25"
                                        list="lstPhongBan" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        headerKey="-1"
                                        headerValue="--- Chọn ---"
                                        cssStyle="width: 50px;vertical-align: middle;background-color: #FFCCBA;">
                                    </s:select>
                                </td>                                                                                         
                                <td align = "left" class="TD_10">
                                    <input type="text" value="<s:property  value="D26"/>"  id="D26_<s:property  value="MA"/>"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D26" class="TEN_KH"/>
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
