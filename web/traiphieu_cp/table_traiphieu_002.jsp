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
                $(".TD_TENKH").css({"width": "250px"});
                $(".TD_SOKU").css({"width": "80px"});
                $(".TD_TIDE").css({"width": "100px"});
                $(".TD_NGAY").css({"width": "55px"});
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
        <s:form id="id_sv_TRAIPHIEU_002" action="SAVE_TRAIPHIEU_002" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
                
            </br>
            <div id="divTitle">
                KẾT QUẢ PHÁT HÀNH TRÁI PHIẾU ĐƯỢC CHÍNH PHỦ BẢO LÃNH - CÁC LOẠI PHÍ PHẢI TRẢ
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nghiquyet11cp"/>
            <!--            <div class="cls-over">
                            <div id="scrolling_table_1"  style="width: 98%; max-height:45vh">-->       
            <div id="divDonvitinh">
                Đơn vị tính: Đồng      
            </div>
            <!--<div class="cls-over">-->
            <div id="scrolling_table_1"   class="editDelete" style="width: 98%; max-height:60vh">
                <table id="tblTable">
                    <tr >                                                                                  
                        <th rowspan="1" class="TD_STT">STT</th>   
                        <th colspan="1" class="TD_SOKU">Số CIF</th>   
                        <th colspan="1" class="TD_TENKH">Tên chủ sở hữu</th>
                        <th rowspan="1" class="TD_TIDE">Số TK</th>  
                        <th colspan="1" class="TD_NGAY">Sản phẩm</th> 
                        <th colspan="1" class="TD_NGAY">Ngày phát hành</th> 
                        <th colspan="1" class="TD_NGAY">Ngày đến hạn</th> 
                        
                        <th colspan="1" class="TD_SOKU">Số dư đầu kỳ</th>                                                      
                        <th colspan="1" class="TD_SOKU">Số dư cuối kỳ</th>   
                        <th colspan="1" class="TD_SOKU">Phí đấu thầu</th>   
                        <th colspan="1" class="TD_SOKU">Phí thanh toán</th>  
                        <th colspan="1" class="TD_SOKU">Phí bảo lãnh</th>                             
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

                            <td align = "right" class="TD_STT" >
                                <input type="text"   value="<s:property  value="TT_HIENTHI" />" style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" 
                                       class=" TEN_KH D0 " 
                                       onfocus="this.select();"
                                       readonly="true"/>                                                                                                                 
                                <input type="hidden" value="<s:property  value="D2" />" id="id_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2"/>
                            </td>  

                            <td align = "right" class="TD_SOKU" >
                                <input type="text"   value="<s:property  value="D17" />" style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" 
                                       class=" TEN_KH D0" 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="TD_TENKH" >
                                <input type="text"   value="<s:property  value="D15" />" style="background: #E7DCDA !important;" title="<s:property  value="D15" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" 
                                       class=" TEN_KH  " 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="TD_TIDE" >
                                <input type="text"   value="<s:property  value="D1" />"  style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                       class=" TEN_KH D0" 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="TD_NGAY" >
                                <input type="text"   value="<s:property  value="D16" />" style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" 
                                       class=" TEN_KH D0" 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="TD_NGAY" >
                                <input type="text"   value="<s:property  value="D18" />" style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" 
                                       class=" TEN_KH D0" 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="TD_NGAY" >
                                <input type="text"   value="<s:property  value="D19" />" style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" 
                                       class=" TEN_KH D0" 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="TD_SOKU" >
                                <input type="text"   value="<s:property  value="D3" />"  style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                       class=" TEN_KH  number" 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="TD_SOKU" >
                                <input type="text"   value="<s:property  value="D4" />" style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                       class=" TEN_KH  number" 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="TD_SOKU" >
                                <input type="text"   value="<s:property  value="D9" />"  
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" 
                                       class=" TEN_KH  number" 
                                       onfocus="this.select();"/> 
                            </td>
                            <td align = "right" class="TD_SOKU" >
                                <input type="text"   value="<s:property  value="D10" />"  
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                       class=" TEN_KH number" 
                                       onfocus="this.select();"/> 
                            </td>
                            <td align = "right" class="TD_SOKU" >
                                <input type="text"   value="<s:property  value="D11" />"  
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                       class=" TEN_KH number" 
                                       onfocus="this.select();"/> 
                            </td>
                            

                        </tr>                                                                                                                                                                                   
                    </s:iterator>
                </table>        
            </div>
            <!--</div>-->

            <sj:submit id="TRAIPHIEU_002_save" name="TRAIPHIEU_002_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
           

        <div id="luu_thanhcong"></div>

        <script>
            initTable();
        </script>
    </body>
</html>

