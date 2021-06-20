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
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('.D0').css({"text-align": "center"});               
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".SOKU").css({"width": "100%"});
                $(".TD_CHECKBOX").css({"width": "4%"});
                $(".TD_SOKU").css({"width": "80px"});
                $(".TD_TENKH").css({"width": "20%"});
                $(".TD_TENTS").css({"width": "15%"});
                $(".TD_MAKH").css({"width": "10%"});
                $(".TD_THOIGIAN").css({"width": "55px"});
                $(".TD_MAPGD").css({"width": "45px"});
                $(".TD_BUTTON1").css({"width": "10%"});
                $(".TD_SOTIEN").css({"width": "100px"});
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
                var table = document.getElementById("tablesnxa");
                var rowcount = table.rows.length;    
                rowcount = rowcount > max_row ? rowcount : max_row;                
                for (var i = 0; i < rowcount; i++)
                {                    
                    var matmp = getMabyNumber(i);//   
                    
                    if(matmp == 1)
                    {
                        $('input:checkbox[id='+i+']').attr('checked',true);
                    }
                }
            }
            
            function getMabyNumber(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
            
        </script>
        
        <style>                                                
            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }            
        </style>

    </head>
    <body style="font-family: ">
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
                </br>
                <div id="divTitle">
                    DANH SÁCH SÁT NHẬP XÃ
                </div>
                <s:hidden name="khoa_nhaptaycn"/>    
                
<!--                <div id="divDonvitinh">
                    Đơn vị tính: Đồng
                </div>-->
                </br>
                <table border="1" class="editDelete" id="tablesnxa" style="width: 95%"  align="center">
                    <tr height="30px">      
                        <th  rowspan="2" class="TD_CHECKBOX"></th>  
                        <th  colspan="4" class="TD_MAKH">Đơn vị hành chính sau chia tách (đơn vị mới)</th>    
                        <th  colspan="2"  class="TD_MAKH">Các đơn vị chia tách địa giới hành chính (đơn vị cũ)</th>                          
                    </tr>    
                    <tr height="25px">                                                        
                        <th  class="TD_MAKH">Xã</th>     
                        <th  class="TD_MAKH">Ngày giao dich xã hiệu lực </th> 
                        <th  class="TD_MAKH">Ngày hoàn thành nhận bàn giao </th> 
                        <th  class="TD_MAKH">Hình thức sáp nhập</th> 
                        <th  class="TD_MAKH">Mã xã</th> 
                        <th  class="TD_MAKH">Tên xã</th> 
                    </tr> 
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>     
                                <td  align="center" class="TD_CHECKBOX">    
                                    <input type="checkbox" id ="<s:property  value="%{#rowstatus.index}" />"  class="checkboxdat" name="lstsaveNT_DAT[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D6" />" 
                                           class="D0"/>
                                </td> 
                                
                                <td align = "left" class="TD_BUTTON1">                                        
                                    <s:select  
                                        id="lstDulieuNt[%{#rowstatus.index}].D1"
                                        name="lstDulieuNt[%{#rowstatus.index}].D1"
                                        list="lstCBKetoan" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        headerKey="-1"
                                        headerValue="--- Chọn ---"                                    
                                        cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA;">
                                    </s:select>
                                </td> 
                                                                                                                                 
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D3" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D0 datepicker" placeholder="dd/MM/yyyy" />
                                </td>                                   
                                        
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D0 datepicker" placeholder="dd/MM/yyyy" />
                                </td>
                                
                                <td align = "left" class="TD_BUTTON1">                                        
                                    <s:select  
                                        id="lstDulieuNt[%{#rowstatus.index}].D5"
                                        name="lstDulieuNt[%{#rowstatus.index}].D5"
                                        list="lstCBTindung" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        headerKey="-1"
                                        headerValue="--- Chọn ---"                                    
                                        cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA;">
                                    </s:select>
                                </td> 
                                
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D6" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                    <input type="hidden" value="<s:property  value="D19" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" value="<s:property  value="D19"/>"/>
                                    <input type="hidden" value="<s:property  value="D14" />"  
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" value="<s:property  value="D14"/>"/>
                                    <input type="hidden" value="<s:property  value="D15" />"  
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" value="<s:property  value="D15"/>"/>
                                </td>
                                <td align = "right" class="TD_TENTS" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D7" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                
                            </tr>                                                                                                                                                                                   
                    </s:iterator>
                </table>                    
                
            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initTable();
        </script>
    </body>
    
    
</html>
