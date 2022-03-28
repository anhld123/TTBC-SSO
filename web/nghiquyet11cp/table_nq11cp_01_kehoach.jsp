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
                $(".TD_STT").css({"width": "20px"});
                $(".TD_MAKH").css({"width": "50px"});
                $(".TD_TOTIEN").css({"width": "70px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "130px"});
                $(".TD_SOKU").css({"width": "80px"});
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
            
            function sumColumn(mainput_tmp)
            {
                var mainput = $.trim(mainput_tmp.toString());                
                try {
                    var table = document.getElementById("tblTable");
                    var rowcount = table.rows.length;
                    rowcount = rowcount > max_row ? rowcount : max_row;
                    var D21 = 0,D22 = 0;
                    var pos = -1;
                    
//                    alert('D8_300001');
                    alert(rowcount);
                    for (var i = 0; i < rowcount; i++)
                    {
                        var matmp = getMabyNumber(i);
//                        alert(matmp);
       
                        if (mainput.substr(0, 3) != '6000000000000')
                        {
//                            alert(mainput + '-' + matmp);
//                            alert(getValue('D5_' + i));
//Lay gia tri cho cac truong tu D2->d6
                            //neu ky tu cuoi cung cua ma la '1' Vi du voi ma '100001,100030... thi se chi lam voi ma khac '1' o cuoi
                            if (matmp.substr(matmp.length - 2, matmp.length) != '01')
                            {
                                //lay ra gia tri cua truong D7,D8,D9
                                D21 = D21 + getValue('D21_' + i);
                                D22 = D22 + getValue('D22_' + i);
                            } 
                            if (matmp.substr(matmp.length - 3, matmp.length) == '000')
                            {
//                                alert(pos);
                                pos = i;
                            }
                            
                        }                          
                        
                    }                    
//                    //set gia tri
////                    alert('D8_' + pos)
                    setValue('D21_' + pos, D21);
                    setValue('D22_' + pos, D22);                   
//                    
//                    var data73=$('.D7_300001').val();
//                    var data74=$('.D7_400001').val();
//                    
//                    var data83=$('.D8_300001').val();
//                    var data84=$('.D8_400001').val();
//                    
//                    var data93=$('.D9_300001').val();
//                    var data94=$('.D9_400001').val();
//                    
//                    var data103=$('.D10_300001').val();
//                    var data104=$('.D10_400001').val();
//                    $('.D7_200001').val(parseFloat(data73) + parseFloat(data74));
//                    $('.D8_200001').val(parseFloat(data83) + parseFloat(data84));
//                    $('.D9_200001').val(parseFloat(data93) + parseFloat(data94));
//                    $('.D10_200001').val(parseFloat(data103) + parseFloat(data104));
                }
                catch (e)
                {
                    alert(e);
                    console.log(e.toString());
                }
                  $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
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
    </head>
    <body style="font-family: ">
        <s:form id="id_sv_NQ11CP_01KH" action="SAVE_NQ11CP_01KH" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            </br>
            <div id="divTitle">
                KẾ HOẠCH HỖ TRỢ LÃI SUẤT CHO KHÁCH HÀNG VAY VỐN        
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nghiquyet11cp"/>
            <!--            <div class="cls-over">
                            <div id="scrolling_table_1"  style="width: 98%; max-height:45vh">-->            
            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 88%; max-height:45vh">
                    <table id="tblTable">
                        <tr>      
                            <th rowspan="1" class="TD_STT">STT</th>                                                       
                            <th rowspan="1" class="TD_TENKH">Chỉ tiêu</th>  
                            <th rowspan="1" class="TD_SOKU">Dư nợ cho vay được hỗ trợ lãi suất</th>    
                            <th rowspan="1"  class="TD_SOKU">Số tiền hỗ trợ lãi suất</th>                                                        
                        </tr>         
                        
                        <tr style="font-style: italic;">
                            <td style="text-align: center">(1)</td>                            
                            <td style="text-align: center">(2)</td>
                            <td style="text-align: center">(3)</td>
                            <td style="text-align: center">(4)</td>                            
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>                               
                                <td align = "right" class="TD_STT" >
                                    <input type="text"   value="<s:property  value="THUTU" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="D0 TEN_KH " onfocus="this.select();"
                                           readonly="true"/>        
                                     <input type="hidden" value="<s:property  value="D3" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           value="<s:property  value="D3"/>" />
                                </td>  


                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"   value="<s:property  value="D50" />" style="background: #C0C0C0 !important;" title="<s:property  value="D50" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D50" class=" TEN_KH " onfocus="this.select();" 
                                           readonly="true"/> 
                                </td>
                                <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D21" />" id="D21_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" class="number TEN_KH " 
                                           onfocus="this.select(); sumColumn('<s:property  value="D3"/>');"/>
                                </td>

                               <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D22" />" id="D22_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" class="number TEN_KH " 
                                           onfocus="this.select(); sumColumn('<s:property  value="D3"/>');"/>
                                </td>
                                
                            </tr>                                                                                                                                                                                   
                        </s:iterator>
                    </table>        
                </div>
            </div>

            <sj:submit id="NQ11CP_01KH_save" name="NQ11CP_01KH_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>

        <script>
            initTable();
            function CheckUpdate(idchk, iddata, status) {
                if (status === "ChangeVal") {
                    var val = $('#' + iddata).val();
                    var valbk = $('#' + iddata + 'BK').val();
                    if (val === valbk) {
                        $('#' + idchk).attr('checked', true);
                    } else {
                        $('#' + idchk).attr('checked', true);
                    }
                } else {
                    if ($('#' + idchk).prop('checked')) {
                        $('#' + idchk).prop('checked', false);
                    } else {
                        $('#' + idchk).prop('checked', true);
                    }
                }
            }
        </script>
    </body>
</html>

