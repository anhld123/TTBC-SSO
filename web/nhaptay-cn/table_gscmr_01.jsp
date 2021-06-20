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
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "150px"});
                $(".TD_DAT_KODAT").css({"width": "60px"});
                $(".TD_GHICHU").css({"width": "355px"});                                
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            
            $("#allCheck_dat").change(function () {
                $(".checkboxdat").prop('checked', $(this).prop("checked"));
            });
            
        </script>
        
        <script>
            var max_row = 0;
            function initTable()
            {
//                if (<s:property value="Grade"/> == '3') //nếu cấp báo cáo là PGD: cấp 1
//                    return;
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
        <%--<s:if test="Grade.equalsIgnoreCase('2')">--%> 
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                ĐÁNH GIÁ PHIÊN GIAO DỊCH XÃ QUA CAMERA IP 
                <br>
                <i>${txn_detail} </i>
            </div>
                &nbsp;
            <s:hidden name="khoa_tdnn"/>
<!--            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>-->
            <table border="1" class="editDelete" id="tablems01" align="center">
                <tr height="30">
                    <th rowspan="2"class="TD_THUTU">TT</th>
                    <th rowspan="2"class="TD_CHITIEU">Tiêu chí</th>
                    <th class="TD_DAT_KODAT">Đạt/Không đạt</th>                                            
                    <th rowspan="2" class="TD_GHICHU">Ghi chú</th>                       
                </tr>           
                <tr>
                    <th align = "center" class="TD_DAT_KODAT">
                       <input type="checkbox" id ="allCheck_dat" name="allCheck_dat"  />
                    </th>
                </tr>    
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">     
                <tr>
                        <td align ="center" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                            <input type="text" style="text-align:center" value="<s:property  value="THUTU" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" readonly/>
                        </td>
                        
                        <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_CHITIEU">
                            <input type="text" value="<s:property  value="TEN" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly/>
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                            <input type="hidden" value="<s:property  value="D3" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D3"/>"/>
                            
                            <input type="hidden" value="<s:property  value="D5" />"  id="id5_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" value="<s:property  value="D5"/>"/>
                            <input type="hidden" value="<s:property  value="D6" />"  id="id6_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" value="<s:property  value="D6"/>"/>
                        </td>
                        <td  align="center" class="TD_DAT_KODAT">    
                            <input type="checkbox" id ="<s:property  value="%{#rowstatus.index}" />"  class="checkboxdat" name="lstsaveNT_DAT[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA" />" 
                                   class="D0"/>
                        </td>                                               
                                                
                        <td align = "right" class="TD_GHICHU">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"/>
                        </td>                                                
                    </tr>                    
                    
                </s:iterator>
            </table>
            <p></p>          
            
            <sj:submit id="%{khoa_tdnn}_save" name="%{khoa_tdnn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        
        <%--</s:if>--%>           
         
        </s:form>
        <div id="luu_thanhcong"></div>
<!--        <script>
            initTable();
        </script>-->
    </body>
</html>
