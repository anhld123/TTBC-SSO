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
                $(".datepicker_month").datepicker({dateFormat: 'mm/yy'});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".STT1").css({"width": "20px"});
                $(".STT2").css({"width": "20px"});
                $(".STT3").css({"width": "50px"});
                $(".STT4").css({"width": "55px"});
                $(".STT5").css({"width": "40px"});
                $(".STT6").css({"width": "100px"});
                $(".STT7").css({"width": "70px"});
                $(".STT9").css({"width": "170px"});
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

            function sumColumn(mainput_tmp)
            {
                var mainput = $.trim(mainput_tmp.toString());
                try {
                    var table = document.getElementById("tblTable");
                    var rowcount = table.rows.length;
                    rowcount = rowcount > max_row ? rowcount : max_row;
                    var D1 = 0, D2 = 0;
                    var pos = -1;

                    for (var i = 0; i < rowcount; i++)
                    {
                        var matmp = getMabyNumber(i);
//                        alert(matmp);

                        if (mainput.substr(1, 3) == matmp.substr(1, 3))
                        {
//                            alert(matmp.substr(4,8));
                            if (matmp.substr(4, 8) != '00000')
                            {
                                //lay ra gia tri cua truong D7,D8,D9
                                D1 = D1 + getValue('D1_' + i);
                                D2 = D2 + getValue('D2_' + i);
                            }
                            if (matmp.substr(4, 8) == '00000')
                            {
                                pos = i;
                            }

                        }

                    }
                    setValue('D1_' + pos, D1);
                    setValue('D2_' + pos, D2);
                } catch (e)
                {
                    alert(e);
                    console.log(e.toString());
                }
                $('.number').number(true, 0);
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
             #scrolling_table_1{
                overflow-x: scroll;
            }
        </style>
    </head>
    <body style="font-family: ">
        <s:form id="id_sv_TRAIPHIEU_001" action="SAVE_DUCANH_001" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
                
            </br>
            <div id="divTitle">
                THÔNG TIN CÁN BỘ
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nghiquyet11cp"/>
            <!--            <div class="cls-over">
                            <div id="scrolling_table_1"  style="width: 98%; max-height:45vh">-->       
            <!--<div class="cls-over">-->
            <div id="scrolling_table_1"   class="editDelete" style="width: 98%; max-height:60vh">
                <table id="tblTable">
                    <tr >                                                                                  
                        <th rowspan="1" class="STT1">STT</th>   
                        <th colspan="1" class="STT2">Mã cán bộ</th>   
                        <th colspan="1" class="STT6">Tên cán bộ</th>
                        <th rowspan="1" class="STT4">Ngày sinh</th>  
                        <th colspan="1" class="STT5">Mã chức vụ</th> 
                        <th colspan="1" class="STT6">Tên chức vụ</th>                                                      
                        <th colspan="1" class="STT5">Mã phòng ban</th>   
                        <th colspan="1" class="STT6">Tên phòng ban</th>   
                        <th colspan="1" class="STT7">CMT/CCCD</th>  
                        <th colspan="1" class="STT9">Nơi cấp</th>   
                        <th colspan="1" class="STT4">Ngày cấp</th>   
                        <th colspan="1" class="STT7">SĐT</th>    
                    </tr>       
                    <tr style="font-style: italic;">
                        <td style="text-align: center"></td> 
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
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                        <tr>  

                            <td align = "right" class="STT1" >
                                <input type="text"   value="<s:property  value="TT_HIENTHI" />" style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" 
                                       class=" TEN_KH D0 " 
                                       onfocus="this.select();"
                                       readonly="true"/>                                                                                                                 
                                <input type="hidden" value="<s:property  value="D4" />" id="id_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"/>
                            </td>  

                            <td align = "right" class="STT2" >
                                <input type="text"   value="<s:property  value="D1" />" style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                       class=" TEN_KH D0" 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="STT6" >
                                <input type="text"   value="<s:property  value="D2" />" style="background: #E7DCDA !important;" title="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                       class=" TEN_KH  " 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="STT4" >
                                <input type="text"   value="<s:property  value="D3" />"  style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                       class=" TEN_KH D0" 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="STT5" >
                                <input type="text"   value="<s:property  value="D5" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                       class=" TEN_KH D0" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td align = "right" class="STT6" >
                                <input type="text"   value="<s:property  value="D6" />"  style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                       class=" TEN_KH  " 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="STT5" >
                                <input type="text"   
                                       value="<s:property  value="D7" />"                                    
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                       class=" TEN_KH D0" 
                                       onfocus="this.select();"/> 
                            </td>
                            <td align = "right" class="STT6" >
                                <input type="text"  
                                       value="<s:property  value="D8" />" 
                                       style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" 
                                       class=" TEN_KH " 
                                       onfocus="this.select();" readonly="true"/>  
                            </td>
                            <td align = "right" class="STT7" >
                                <input type="text"   value="<s:property  value="D9" />"  style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" 
                                       class=" TEN_KH " 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="STT9" >
                                <input type="text"   value="<s:property  value="D10" />"  style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                       class=" TEN_KH " 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="STT4" >
                                <input type="text"   value="<s:property  value="D11" />"  style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                       class=" TEN_KH D0" 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="STT7" >
                                <input type="text"   value="<s:property  value="D12" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                       class=" TEN_KH D0" 
                                       onfocus="this.select();"/> 
                            </td>

                        </tr>                                                                                                                                                                                   
                    </s:iterator>
                 <!--     <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td align = "center" class="STT5" ><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class=" TEN_KH D0" style="width: 60px;"/></td>
                </tr> -->
                </table>        
            </div>
            <!--</div>-->

            <sj:submit id="DUCANH01_save" name="TRAIPHIEU_001_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
           

        <div id="luu_thanhcong"></div>

        <script>
            initTable();
        </script>
    </body>
</html>

