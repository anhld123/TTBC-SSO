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
                $(".TD_TENKH").css({"width": "330px"});
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
                    var D1 = 0,D2 = 0;
                    var pos = -1;
                    
                    for (var i = 0; i < rowcount; i++)
                    {
                        var matmp = getMabyNumber(i);
//                        alert(matmp);
       
                        if (mainput.substr(1, 3) == matmp.substr(1,3))
                        {
//                            alert(matmp.substr(4,8));
                            if (matmp.substr(4,8) != '00000')
                            {
                                //lay ra gia tri cua truong D7,D8,D9
                                D1 = D1 + getValue('D1_' + i);
                                D2 = D2 + getValue('D2_' + i);
                            } 
                            if (matmp.substr(4,8) == '00000')
                            {
                                pos = i;
                            }
                            
                        }                          
                        
                    }                    
                    setValue('D1_' + pos, D1);
                    setValue('D2_' + pos, D2);                   
                }
                catch (e)
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
        </style>
    </head>
    <body style="font-family: ">
        <s:form id="id_sv_NQ11CP_01KH" action="SAVE_NQ11CP_01KH" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            </br>
            <div id="divTitle">
                KẾ HOẠCH TĂNG TRƯỞNG DƯ NỢ - CHƯƠNG TRÌNH ĐẶC THÙ    
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nghiquyet11cp"/>
            <!--            <div class="cls-over">
                            <div id="scrolling_table_1"  style="width: 98%; max-height:45vh">-->       
            <div id="divDonvitinh">
                Đơn vị tính: Triệu đồng              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <!--<div class="cls-over">-->
                <div id="scrolling_table_1"  style="width: 95%; max-height:45vh">
                    <table id="tblTable">
                        <tr >      
                            <th rowspan="1" class="TD_STT">STT</th>                                                       
                            <th rowspan="1" class="TD_TENKH">Chỉ tiêu</th>                              
                            <th colspan="1" class="TD_SOKU">Tăng trưởng dư nợ</th>                                                      
                        </tr>         
                       
                        
                        <tr style="font-style: italic;">
                            <td style="text-align: center">(1)</td>                            
                            <td style="text-align: center">(2)</td>
                            <td style="text-align: center">(3)</td>
                            
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>                               
                                <td align = "right" class="TD_STT" >
                                    <input type="text"   value="<s:property  value="TT_HIENTHI" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" 
                                           class=" TEN_KH D0 " 
                                           onfocus="this.select();"
                                           readonly="true"/>                                                                                 
                                    <input type="hidden" value="<s:property  value="MA" />" id="id_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/> 
                                </td>  


                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"   value="<s:property  value="TEN" />" style="background: #C0C0C0 !important;" title="<s:property  value="TEN" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" 
                                           class=" TEN_KH  " 
                                           onfocus="this.select();" readonly="true"/> 
                                </td>
                                <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D3" />" title="<s:property  value="D3" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                           class=" TEN_KH  number" 
                                           onfocus="this.select();"/> 
                                </td>
                                
                                
                            </tr>                                                                                                                                                                                   
                        </s:iterator>
                    </table>        
                </div>
            <!--</div>-->

            <sj:submit id="NQ11CP_01KH_save" name="NQ11CP_01KH_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>

        <script>
            initTable();
        </script>
    </body>
</html>

