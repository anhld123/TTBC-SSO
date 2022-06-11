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
//                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
//                $(".datepicker_month").datepicker({dateFormat: 'mm/yy'});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_STT").css({"width": "20px"});
                $(".TD_MAKH").css({"width": "30px"});
                $(".TD_TOTIEN").css({"width": "70px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "330px"});
                $(".TD_SOKU").css({"width": "60px"});
                $(".TD_NGAY").css({"width": "40px"});
                $(".TD_CHITIEU").css({"width": "300px"});
                $(".TD_GHICHU").css({"width": "150px"});
                $(".TEN_KH").css({"width": "100%"});
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

            function initTable()
            {
                var table = document.getElementById("tblTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    //cho combox 1
                    var matmp1 = getMabyNumber1(i);//                       
                    if (matmp1 == 1)
                    {
                        $('input:checkbox[id=idc11' + i + ']').attr('checked', true);
                    }
                }
            }

            function getMabyNumber1(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id9_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
//             function showChange(col, stt)
//            {
//                setValue('showChange', col+stt);
//                document.getElementById('showChange').innerHTML =col+stt
//            }

            function sumColumn(mainput_tmp)
            {
                var mainput = $.trim(mainput_tmp.toString());
                
                try {
                    var table = document.getElementById("tblTable");
                    var rowcount = table.rows.length;
                    rowcount = rowcount > max_row ? rowcount : max_row;
                    var D1 = 0, D2 = 0, D3 = 0, D4 = 0, D5 = 0,D6 = 0,D7 = 0,D8 = 0,D9 = 0,D10 = 0,D11 = 0,D12 = 0, D13 = 0, D14;
                    var pos = -1;

                    for (var i = 0; i < rowcount; i++)
                    {
                        var matmp = getMabyNumber(i);
//                       alert(matmp.substr(4, 8) + i);
                        if (matmp.substr(4, 8) == '0000')
                            {
                                D1 = 0, D2 = 0, D3 = 0, D4 = 0, D5 = 0,D6 = 0,D7 = 0,D8 = 0,D9 = 0,D10 = 0,D11 = 0,D12 = 0, D13=0, D14=0;
                                pos = i;
                            }                            
                        if (mainput.substr(1, 3) == matmp.substr(1, 3))
                        {                       
                            if (matmp.substr(4, 8) != '0000')
                            {                                                                
                                D1 = D1 + getValue('D1_' + i);                             
                                D2 = D2 + getValue('D2_' + i);
                                D3 = D3 + getValue('D3_' + i);
                                D4 = D4 + getValue('D4_' + i);
                                D5 = D5 + getValue('D5_' + i);
                                D6 = D6 + getValue('D6_' + i);
                                D7 = D7 + getValue('D7_' + i);
                                D8 = D8 + getValue('D8_' + i);
                                D9 = D9 + getValue('D9_' + i);
                                D10 = D10 + getValue('D10_' + i);
                                D11 = D11 + getValue('D11_' + i);
                                D12 = D12 + getValue('D12_' + i);
                                setValue('D13_' + i, getValue('D1_' + i) + getValue('D2_' + i) + getValue('D3_' + i) + getValue('D4_' + i) + getValue('D5_' + i) + getValue('D6_' + i) + 
                                getValue('D7_' + i) + getValue('D8_' + i) + getValue('D9_' + i) + getValue('D10_' + i) + getValue('D11_' + i) + getValue('D12_' + i) )
                                D13 = D13 + getValue('D13_' + i);
                                setValue('D14_' + i, Math.round(getValue('D13_' + i)*0.02).toFixed(2))
                            }                                                       
                        }
                        
                        
                        setValue('D1_' + pos, D1);
                        setValue('D2_' + pos, D2);
                        setValue('D3_' + pos, D3);
                        setValue('D4_' + pos, D4);
                        setValue('D5_' + pos, D5);
                        setValue('D6_' + pos, D6);
                        setValue('D7_' + pos, D7);
                        setValue('D8_' + pos, D8);
                        setValue('D9_' + pos, D9);
                        setValue('D10_' + pos, D10);
                        setValue('D11_' + pos, D11);
                        setValue('D12_' + pos, D12);
                        setValue('D13_' + pos, D13);
//                        alert(Math.round(getValue('D13_' + pos)*0.02).toFixed(2))
                        setValue('D14_' + pos, Math.round(getValue('D13_' + pos)*0.02).toFixed(2));
                        
                    }    
                    setValue('D1_46' , getValue('D1_0') + getValue('D1_30'));    
                    setValue('D2_46' , getValue('D2_0') + getValue('D2_30'));   
                    setValue('D3_46' , getValue('D3_0') + getValue('D3_30'));   
                    setValue('D4_46' , getValue('D4_0') + getValue('D4_30'));   
                    setValue('D5_46' , getValue('D5_0') + getValue('D5_30'));   
                    setValue('D6_46' , getValue('D6_0') + getValue('D6_30'));   
                    setValue('D7_46' , getValue('D7_0') + getValue('D7_30'));   
                    setValue('D8_46' , getValue('D8_0') + getValue('D8_30'));   
                    setValue('D9_46' , getValue('D9_0') + getValue('D9_30'));   
                    setValue('D10_46' , getValue('D10_0') + getValue('D10_30'));   
                    setValue('D11_46' , getValue('D11_0') + getValue('D11_30')); 
                    setValue('D12_46' , getValue('D12_0') + getValue('D12_30')); 
                    setValue('D13_46' , getValue('D13_0') + getValue('D13_30')); 
                    setValue('D14_46' , getValue('D14_0') + getValue('D14_30')); 
                } catch (e)
                {
                    alert(e);
                    console.log(e.toString());
                }
                $('.number').number(true, 0);
//                $('.number2').number(true, 2);
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
            function setValue(id, value)
            {
                try {
                    document.getElementById(id).value = value;
                } catch (e)
                {
//                    alert(e);
                }
            }
        </script>

        <link href="css/css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .CLS-BOLD{
                font-weight: bold;
            }
            .fix_th {
                position: -webkit-sticky;
                position: sticky;
                top: -1px;
                z-index: 1;
                background: #fff;
            }	
        </style>
    </head>
    <body style="font-family: ">
        <s:form id="id_sv_%{khoa_nghiquyet11cp}" action="SAVE_%{khoa_nghiquyet11cp}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
                
            </br>
            <div id="divTitle">
                <s:if test="khoa_nghiquyet11cp.equalsIgnoreCase('NQ11_DKKH')">
                    <font color="red"> ĐĂNG KÝ </font> KẾ HOẠCH HỖ TRỢ LÃI SUẤT CHO KHÁCH HÀNG VAY VỐN  
                </s:if>
                <s:else>
                    <font color="red"> ĐIỀU CHỈNH </font> KẾ HOẠCH HỖ TRỢ LÃI SUẤT CHO KHÁCH HÀNG VAY VỐN  
                </s:else>    
                              
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nghiquyet11cp"/>
            <!--            <div class="cls-over">
                            <div id="scrolling_table_1"  style="width: 98%; max-height:45vh">-->       
            <div id="divDonvitinh">
                Đơn vị tính: Triệu đồng              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
<!--            <label id="showChange"></label>-->
            <div class="cls-over">
            <div id="scrolling_table_1"   class="editDelete" style="width: 98%; max-height:45vh">
                <table id="tblTable">
                    <tr >                                                                                  
                        <th rowspan="2" class="TD_STT">STT</th>   
                        <!--<th rowspan="2" class="TD_SOKU">MA</th>-->   
                        <th rowspan="2" class="TD_TENKH">Chỉ tiêu</th>   
                        <th colspan="12" class="TD_TENKH">Dự kiến dư nợ cho vay được hỗ trợ lãi suất</th>                              
                        <th rowspan="2" class="TD_SOKU">Tổng cộng</th>                                                      
                        <th rowspan="2" class="TD_SOKU">Nhu cầu hỗ trợ lãi suất trong tháng</th>   
                    </tr>         

                    <tr>
                        <th  class="TD_STT">T1</th>   
                        <th  class="TD_STT">T2</th>   
                        <th  class="TD_STT">T3</th>   
                        <th  class="TD_STT">T4</th>   
                        <th  class="TD_STT">T5</th>   
                        <th  class="TD_STT">T6</th>   
                        <th  class="TD_STT">T7</th>   
                        <th  class="TD_STT">T8</th>   
                        <th  class="TD_STT">T9</th>   
                        <th  class="TD_STT">T10</th>   
                        <th  class="TD_STT">T11</th>   
                        <th  class="TD_STT">T12</th>   
                        
                    </tr>        
                    <tr style="font-style: italic;">                        
                        <td style="text-align: center">(1)</td>                            
                        <td style="text-align: center">(2)</td>
                        <td style="text-align: center">(3)</td>
                        <td style="text-align: center">(4)</td>
                        <td style="text-align: center">(5)</td> 
                        <td style="text-align: center">(6)</td>                            
                        <td style="text-align: center">(7)</td>
                        <td style="text-align: center">(8)</td>
                        <td style="text-align: center">(9)</td>
                        <td style="text-align: center">(10)</td> 
                        <td style="text-align: center">(11)</td>                            
                        <td style="text-align: center">(12)</td>
                        <td style="text-align: center">(13)</td>
                        <td style="text-align: center">(14)</td>
                        <td style="text-align: center">(15)</td>
                        <td style="text-align: center">(16)</td>
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                        <tr>  
                            <td align = "right" class="TD_STT" >
                                <input type="text"   value="<s:property  value="TT_HIENTHI" />" style="background: #C0C0C0 !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" 
                                       class=" TEN_KH D0 <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>" 
                                       onfocus="this.select();"
                                       readonly="true"/>   
                                <input type="hidden" value="<s:property  value="MA" />" id="id_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/> 
                                <input type="hidden" value="<s:property  value="MAPGD" />" id="id_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/> 
                            </td>  
<!--                            <td align = "right" class="TD_SOKU" >
                                <input type="text"   value="<s:property  value="MA" />" style="background: #C0C0C0 !important;" title="<s:property  value="MA" />" id="id_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" 
                                       class=" TEN_KH  <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>" 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>-->
                            <td align = "right" class="TD_TENKH" >
                                <input type="text"   value="<s:property  value="TEN" />" style="background: #C0C0C0 !important;" title="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" 
                                       class=" TEN_KH <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>" 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="TD_SOKU" >
                                <input type="text"   value="<s:property  value="D1" />"  id="D1_<s:property  value="%{#rowstatus.index}" />"
                                    style="<s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000')||MA.equalsIgnoreCase('3_S99999') "> background: #C0C0C0 !important; </s:if>"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                       class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>"                                        
                                       onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                        <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">readonly</s:if>/> 
                            </td>
                            <td align = "right" class="TD_SOKU" >
                                <input type="text"   value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                   style="<s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000')||MA.equalsIgnoreCase('3_S99999') "> background: #C0C0C0 !important; </s:if>"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                       class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>" 
                                       onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                       <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">readonly</s:if>/> 
                            </td>
                            <td align = "right" class="TD_SOKU" >
                                <input type="text"   value="<s:property  value="D3" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                   style="<s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000')||MA.equalsIgnoreCase('3_S99999') "> background: #C0C0C0 !important; </s:if>"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                       class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>" 
                                       onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                       <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">readonly</s:if>/> 
                            </td>
                            
                            <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D4" />"  id="D4_<s:property  value="%{#rowstatus.index}" />"
                                            style="<s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000')||MA.equalsIgnoreCase('3_S99999') "> background: #C0C0C0 !important; </s:if>"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                               class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>"                                        
                                               onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                                    <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">readonly</s:if>/> 
                            </td>
                            
                            <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D5" />"  id="D5_<s:property  value="%{#rowstatus.index}" />"
                                            style="<s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000')||MA.equalsIgnoreCase('3_S99999') "> background: #C0C0C0 !important; </s:if>"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                               class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>"                                        
                                               onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                                    <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">readonly</s:if>/> 
                            </td>

                            <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D6" />"  id="D6_<s:property  value="%{#rowstatus.index}" />"
                                            style="<s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000')||MA.equalsIgnoreCase('3_S99999') "> background: #C0C0C0 !important; </s:if>"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                               class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>"                                        
                                               onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                                    <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">readonly</s:if>/> 
                            </td>

                            <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D7" />"  id="D7_<s:property  value="%{#rowstatus.index}" />"
                                            style="<s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000')||MA.equalsIgnoreCase('3_S99999') "> background: #C0C0C0 !important; </s:if>"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                               class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>"                                        
                                               onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                                    <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">readonly</s:if>/> 
                            </td>

                            <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D8" />"  id="D8_<s:property  value="%{#rowstatus.index}" />"
                                            style="<s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000')||MA.equalsIgnoreCase('3_S99999') "> background: #C0C0C0 !important; </s:if>"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" 
                                               class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>"                                        
                                               onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                                    <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">readonly</s:if>/> 
                            </td>

                            <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D9" />"  id="D9_<s:property  value="%{#rowstatus.index}" />"
                                            style="<s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000')||MA.equalsIgnoreCase('3_S99999') "> background: #C0C0C0 !important; </s:if>"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" 
                                               class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>"                                        
                                               onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                                    <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">readonly</s:if>/> 
                            </td>

                            <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D10" />"  id="D10_<s:property  value="%{#rowstatus.index}" />"
                                            style="<s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000')||MA.equalsIgnoreCase('3_S99999') "> background: #C0C0C0 !important; </s:if>"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                               class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>"                                        
                                               onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                                    <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">readonly</s:if>/> 
                            </td>

                            <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D11" />"  id="D11_<s:property  value="%{#rowstatus.index}" />"
                                            style="<s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000')||MA.equalsIgnoreCase('3_S99999') "> background: #C0C0C0 !important; </s:if>"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                               class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>"                                        
                                               onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                                    <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">readonly</s:if>/> 
                            </td>

                            <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D12" />"  id="D12_<s:property  value="%{#rowstatus.index}" />"
                                            style="<s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000')||MA.equalsIgnoreCase('3_S99999') "> background: #C0C0C0 !important; </s:if>"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                               class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>"                                        
                                               onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                                    <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">readonly</s:if>/> 
                            </td>

                            <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D13" />"  id="D13_<s:property  value="%{#rowstatus.index}" />"
                                            style="background: #C0C0C0 !important;"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" 
                                               class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>"                                        
                                               onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                                    readonly="true"/> 
                            </td>

                            <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D14" />"  id="D14_<s:property  value="%{#rowstatus.index}" />"
                                            style="background: #C0C0C0 !important;"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" 
                                               class=" TEN_KH  number <s:if test="MA.equalsIgnoreCase('1_S00000') || MA.equalsIgnoreCase('2_S00000') || MA.equalsIgnoreCase('3_S99999')">CLS-BOLD</s:if>"                                        
                                               onblur="if (this.value == '') {this.value = 0}; sumColumn('<s:property  value="MA"/>');"
                                                    readonly="true"/> 
                            </td>
                                              
                        </tr>                                                                                                                                                                                   
                    </s:iterator>
                </table>        
            </div>
            </div>

            <sj:submit id="%{khoa_nghiquyet11cp}_save" name="%{khoa_nghiquyet11cp}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
           

        <div id="luu_thanhcong"></div>

        <script>
            initTable();
        </script>
    </body>
</html>

