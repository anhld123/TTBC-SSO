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
                $(".TD_TENTS").css({"width": "10%"});
                $(".TD_MAKH").css({"width": "5%"});
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
                var table = document.getElementById("tablesnthon");
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
                    DANH SÁCH CHIA TÁCH/SÁT NHẬP - TỔNG HỢP GỬI SỐ LIỆU
                </div>
                <s:hidden name="khoa_nhaptaycn"/>    
                
<!--                <div id="divDonvitinh">
                    Đơn vị tính: Đồng
                </div>-->
                </br>
                <table border="1" class="editDelete" id="tablesnthon" style="width: 95%"  align="center">
                    <tr height="30px">                              
                        <th  rowspan="2" class="TD_MAKH">Đơn vị</th> 
                        <th  rowspan="2" class="TD_MAKH">Chia tách/ sát nhập</th>    
                        <th  rowspan="2" class="TD_MAKH">Địa giới hành chính</th>    
                        <th  colspan="5" class="TD_MAKH">Đơn vị hành chính sau chia tách (đơn vị mới)</th>    
                        <th  colspan="2"  class="TD_MAKH">Các đơn vị chia tách địa giới hành chính (đơn vị cũ)</th>                          
                    </tr>    
                    <tr height="25px">                                                        
                        <th  class="TD_MAKH">Mã thôn/xã/ tỉnh/huyên</th>  
                        <th  class="TD_TENTS">Tên thôn/xã/ tỉnh/huyên</th>  
                        <th  class="TD_MAKH">Ngày GDX hiệu lực </th> 
                        <th  class="TD_MAKH">Ngày hoàn thành nhận bàn giao </th> 
                        <th  class="TD_MAKH">Hình thức sát nhập</th> 
                        <th  class="TD_MAKH">Mã thôn</th> 
                        <th  class="TD_TENTS">Tên thôn</th> 
                    </tr> 
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>  
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="MAPGD" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td> 
                                
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D14" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td> 
                                
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D15" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td> 
                                                               
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D1" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td>  
                                
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td>  
                                                                   
                                        
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D3" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td>  
                                
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D4" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td>  
                                
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D5" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td>  
                                <td align = "right" class="TD_TENTS" >
                                    <input type="text"  value="<s:property  value="D6" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td>  
                                <td align = "right" class="TD_TENTS" >
                                    <input type="text"  value="<s:property  value="D7" />"
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
