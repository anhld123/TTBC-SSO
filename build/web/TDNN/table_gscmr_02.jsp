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
        <style>
        a {
            color: #0000FF;
        }
        .BOLD
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }
        </style>
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 2);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);                
                $(".TD_CHITIEU").css({"width": "150px"});               
                $(".TD_GHICHU").css({"width": "355px"});                                
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        
        <script>
            var max_row = 0;
            function initTable()
            {
                //SET GIA TRI CHO SELECT 
                var table = document.getElementById("tablems01");
                var rowcount = table.rows.length;                
                rowcount = rowcount > max_row ? rowcount : max_row;
                
                for (var i = 0; i < rowcount; i++)
                {                    
                    var matmp = getMabyNumber(i);//    
                    if(matmp == 1)
                    {
                        $('input:checkbox[id='+i+']').attr('checked',true);
                    }
//                    alert(matmp);
                }
            }
            
            function getValue(id)
            {
                var value = 0;
                try {
                    value = document.getElementById(id).value;
                    value = value.replace(/,/g, "");
                    if (value == '-1')
                        value = 0.0;
                } catch (e)
                {
                    value = 0.0;
                }
                return parseFloat(value);
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
    </head>
    <body>
        <s:form id="id_sv_%{khoa_tdnn}" action="SAVE_%{khoa_tdnn}" theme="simple">                      
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:if test="Grade.equalsIgnoreCase('2')">
            <div id="divTitle">
                TỔNG HỢP ĐÁNH GIÁ PHIÊN GIAO DỊCH XÃ QUA CAMERA IP 
            </div>
                &nbsp;
            <s:hidden name="khoa_tdnn"/>
<!--            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>-->
            <table border="1" class="editDelete" id="tablems01" style="width: 75%" align="center">
                <tr height="35">
                    <th class="TD_CHITIEU">Mã PGD</th>
                    <th class="TD_GHICHU">Tên PGD</th>
                    <th class="TD_CHITIEU">Tổng số ĐGD</th>                                            
                    <th class="TD_CHITIEU">Tổng số ĐGD kiểm tra</th>                       
                </tr>                           
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                                                                                                                                                 
                    <tr>
                        <td align = "right" class="TD_CHITIEU">
                            <input type="text" value="<s:property  value="D1" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>
                        </td>    
                        <td align = "right" class="TD_GHICHU">
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>
                        </td> 
                        <td align = "right" class="TD_CHITIEU">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>
                        </td> 
                        <td align = "right" class="TD_CHITIEU">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>
                        </td> 
                    </tr>                    
                    
                </s:iterator>
            </table>
            </s:if>
            
            <s:if test="Grade.equalsIgnoreCase('3')">
            <div id="divTitle">
                        TRẠNG THÁI GỬI SỐ LIỆU CỦA CHI NHÁNH
                    </div>
                    <p></p>
                    <table border="1" class="editDelete" id="tablepl012" align="center">
                        <tr>                                
                            <th align = "center"  style="width: 50px;">Mã PGD</th>
                            <th style="width: 100px;">Tên PGD</th>
                            <th style="width: 60px;">Ngày gửi</th>
                            <th style="width: 50px;">User gửi</th>
                            <th style="width: 90px;">Trạng thái xử lý</th>
                            <th style="width: 90px;">Trạng thái gửi</th>
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                
                        <s:if test="D1.equalsIgnoreCase('true')">
                            <tr style="text-align: center; color: #0000FF" onmouseover="mover(this);"  onmouseout="mout(this);">                                
                                <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                <td style="width: 60px;"><s:property  value="D8" /></td>
                                <td style="width: 50px;"><s:property  value="D7" /></td>
                                <td style="width: 90px;"><s:property  value="D9" /></td>
                                <td style="width: 90px;"><s:property  value="D11" /></td>
                            </tr>
                        </s:if>
                        <s:else>
                            <tr style="text-align: center; color: red" onmouseover="mover(this);"  onmouseout="mout(this);">                              
                                <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                <td style="width: 60px;"><s:property  value="D8" /></td>
                                <td style="width: 50px;"><s:property  value="D7" /></td>
                                <td style="width: 90px;"><s:property  value="D9" /></td>
                                <td style="width: 90px;"><s:property  value="D11" /></td>
                            </tr>
                        </s:else>
                    </s:iterator>
                    </table>
        </s:if>   
            <p></p>          
                        
        </s:form>
        <div id="luu_thanhcong"></div>
<!--        <script>
            initTable();
        </script>-->
    </body>
</html>
