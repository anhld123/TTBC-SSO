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
            
            $("#allCheck_1").change(function () {
                $(".checkbox1").prop('checked', $(this).prop("checked"));
            });
            
            $("#allCheck_2").change(function () {
                $(".checkbox2").prop('checked', $(this).prop("checked"));
            });
        </script>     
        
        <script>
    
        
        function initTable()
            {
                var table = document.getElementById("tableloaitru3502");
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
            
            function changeCheck1(b1,b2) {
                if (b2.checked) {
                  document.getElementById("idc2"+b1).checked = false;
                } else {
                }
              }
            function changeCheck2(b1,b2) {
                if (b2.checked) {
                  document.getElementById("idc1"+b1).checked = false;
                } else {
                }
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
                    THÔNG TIN XÃ KHÔNG THUỘC VÙNG KHÓ KHĂN NHƯNG CHO VAY CHƯƠNG TRÌNH CHO VAY VÙNG KHÓ KHĂN
                </div>
                <s:hidden name="khoa_nhaptaycn"/>
                </br>
                <s:if test="Grade.equalsIgnoreCase('1')"></s:if>
                <table border="1" class="editDelete" id="tableloaitru3502" style="width: 85%"  align="center">
                    <tr height="40px">      
                         
                        <th rowspan="2"  class="TD_MAKH">Mã xã</th>    
                        <th rowspan="2"  class="TD_TENTS">Tên xã</th>  
                        <th  class="TD_MAKH">Xã vùng KK</th>    
                        <th  class="TD_MAKH">Xã không thuộc vùng kk nhưng được vay chương trình vùng kk</th>                                                    
                       
                    </tr>         
                    <tr height="40px">   
                        <th  class="TD_CHECKBOX">
                            <input type="checkbox" id ="allCheck_1" name="allCheck_1"  />
                        </th> 
                        <th  class="TD_CHECKBOX">
                            <input type="checkbox" id ="allCheck_2" name="allCheck_2"  />
                        </th> 
                    </tr>        
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>  
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D1" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                    </td>  
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" readonly="true"/>
                                </td> 
                                <td  align="center" class="TD_CHECKBOX">    
                                    <input type="checkbox" id ="idc1<s:property  value="%{#rowstatus.index}" />"  class="checkbox1 TEN_KH D0" name="lstsaveNT_DAT[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D10" />" 
                                           onchange="changeCheck1(<s:property  value="%{#rowstatus.index}" />,this)"/>
                                </td>  
                                <td  align="center" class="TD_CHECKBOX">    
                                    <input type="checkbox" id ="idc2<s:property  value="%{#rowstatus.index}" />"  class="checkbox2 TEN_KH D0" name="lstsaveNT_DAT[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D10" />" 
                                            onchange="changeCheck2(<s:property  value="%{#rowstatus.index}" />,this)"/>
                                </td> 
<!--                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D4" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                    </td>  
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"  readonly="true"/>
                                </td> 
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D3" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"  readonly="true"/>
                                </td>
                                
                                <td align = "right" class="TD_TENTS" >
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D6" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                    <input type="hidden" value="<s:property  value="D8" />"  id="id8_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" value="<s:property  value="D8"/>"/>
                                    <input type="hidden" value="<s:property  value="D9" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D9"/>"/>
                                    <input type="hidden" value="<s:property  value="D10" />"  id="id10_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" value="<s:property  value="D10"/>"/>
                                    <input type="hidden" value="<s:property  value="MAPGD" />"  id="idmapgd_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>"/>
                                    <input type="hidden" value="<s:property  value="D1" />"  id="id1_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1"/>"/>
                                    <input type="hidden" value="<s:property  value="D2" />"  id="id2_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>"/>
                                </td>
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="number" readonly="readlonly" />
                                </td>                                     -->
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
