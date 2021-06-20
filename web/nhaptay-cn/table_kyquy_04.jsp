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
                $(".TD_SOTK").css({"width": "15%"});
                $(".TD_MAKH").css({"width": "10%"});
                $(".TD_THOIGIAN").css({"width": "55px"});
                $(".TD_MAPGD").css({"width": "45px"});
                $(".TD_BUTTON1").css({"width": "40px"});
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
                var table = document.getElementById("tablekyquy04");
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
                    KHÁCH HÀNG NHẬN THÔNG BÁO TẤT TOÁN TIỀN GỬI KÝ QUỸ
                </div>
                <s:hidden name="khoa_nhaptaycn"/>
<!--                <div id="divDonvitinh">
                    Đơn vị tính: Đồng
                </div>-->
                </br>
                <table border="1" class="editDelete" id="tablekyquy04" style="width: 75%"  align="center">
                    <tr >      
                        <th  class="TD_CHECKBOX"></th>  
                        <th  class="TD_MAKH">Mã KH</th>    
                        <th  class="TD_TENKH">Tên KH</th>    
                        <th  class="TD_SOTK">Số tài khoản</th>    
                        <th class="TD_MAKH">Số CMTND</th>
                        <th  class="TD_TENTS">Ngày nhận thông báo</th> 
                        <th class="TD_TENTS">Số tiền</th>                                  
                        <th class="TD_TENTS">Tình trạng (Đã tất toán/ chưa tất toán)</th>                                  
                    </tr>                                    
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">     
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <tr>     
                                <td  align="center" class="TD_CHECKBOX">    
                                    <input type="checkbox" id ="<s:property  value="%{#rowstatus.index}" />"  class="checkboxdat" name="lstsaveNT_DAT[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D3" />" 
                                           class="D0"/>
                                </td> 
                                
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D1" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TENKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                    
                                    <input type="hidden" value="<s:property  value="D19" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" value="<s:property  value="D19"/>"/>
                                    <input type="hidden" value="<s:property  value="D20" />"  id="id20_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" value="<s:property  value="D20"/>"/>
                                </td>
                                <td align = "right" class="TD_SOTK" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D3" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                    </td>  
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D15" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>                                
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D16" />" id="D16_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D0 datepicker" placeholder="dd/MM/yyyy" />
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D6" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D18" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                            </tr>
                        </s:if>
                            
                        <s:if test="Grade.equalsIgnoreCase('2')">
                            <tr>                                      
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="THUTU" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                
                                <td align = "right" class="TD_TENTS" >
                                    <input type="text"  value="<s:property  value="TEN" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                    <input type="hidden" value="<s:property  value="D1" />"  
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1"/>"/>
                                    <input type="hidden" value="<s:property  value="D2" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>"/>
                                </td>
                                
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>                                                                
                                                                
                                <td align = "left" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D3" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH " onfocus="this.select();"
                                           readonly="true"/>
                                </td> 
                                    
                                 <td align = "left" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D4" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH " onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                
                              
                            </tr>
                        </s:if>                                                                                                           
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
