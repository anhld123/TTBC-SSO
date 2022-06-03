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
                $(".TD_SOKU").css({"width": "100px"});
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

        </script>
        
        <link href="css/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body style="font-family: ">
        <s:form id="id_sv_NQ11CP_02SK" action="SAVE_NQ11CP_02SK" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            </br>
            <div id="divTitle">
                SAO KÊ KHOẢN VAY ĐƯỢC HỖ TRỢ LÃI SUẤT
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nghiquyet11cp"/>         
            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 98%; max-height:45vh">
                    <table id="tblTable">
                        <tr>      
                            <th rowspan="1" class="TD_STT">STT</th>                           
                            <!--<th rowspan="2" class="TD_TOTIEN">CIF</th>-->  
                            <th rowspan="1" class="TD_TENKH">Tên KH</th>  
                            <th rowspan="1" class="TD_MAKH">Mã khách hàng</th> 
                            <th rowspan="1" class="TD_SOKU">Mã khoản vay</th>    
                            <th rowspan="1"  class="TD_MAKH">Tên chương trình cho vay</th>    
                            <th rowspan="1"  class="TD_MAKH">Dư nợ</th>   
                            <th rowspan="1"  class="TD_MAKH">Số tiền giải ngân trong tháng</th>   
                            <th rowspan="1"  class="TD_MAKH">Lãi suất hỗ trợ trong tháng</th> 
                            <th rowspan="1"  class="TD_MAKH">Số tiền phải thu hồi trong tháng</th>   
                            <th rowspan="1"  class="TD_MAKH">Số tiền đã thu hồi trong tháng</th> 
                            <th rowspan="1"  class="TD_NGAY">Cập nhật</th>                              
                            <th rowspan="1"  class="TD_TENKH">Lý do thu hồi</th>     
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
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>                               
                                <td align = "right" class="TD_STT" >
                                    <input type="text"   value="<s:property  value="THUTU" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="D0 TEN_KH " onfocus="this.select();"
                                           readonly="true"/>                                        
                                </td>  


                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"   value="<s:property  value="D2" />" style="background: #C0C0C0 !important;" title="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class=" TEN_KH " onfocus="this.select();" 
                                           readonly="true"/> 

                                    <input type="hidden" value="<s:property  value="D25" />"  id="id9_<s:property  value="%{#rowstatus.index}" />" 
                                           value="<s:property  value="D25"/>"/>
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D1" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 TEN_KH " onfocus="this.select();"
                                           readonly="true"/>
                                </td>

                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D3" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D0 TEN_KH" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                              
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D8" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D0 TEN_KH " onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D10" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D0 TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                
                                <td align = "right" class="TD_TOTIEN" >
                                    <input type="text"   value="<s:property  value="D11" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TOTIEN" >
                                    <input type="text"   value="<s:property  value="D12" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                 <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D15" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="TEN_KH number" onfocus="this.select();" id='D15<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D15<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" />
                                        <input type="text"   value="<s:property  value="D15" />"
                                               class="DataHiden" id='D15<s:property  value="%{#rowstatus.index}" />BK'/>
                                </td>                            
                                
                                 <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D16" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="TEN_KH number" onfocus="this.select();" id='D16<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D10<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" />
                                        <input type="text"   value="<s:property  value="D16" />"
                                               class="DataHiden" id='D16<s:property  value="%{#rowstatus.index}" />BK'/>
                                </td>
                                <td  align="center" class="TD_CHECKBOX">    
                                        <input type="checkbox" id ='idchk<s:property  value="%{#rowstatus.index}" />' class="checkboxdat TEN_KH" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" value="<s:property  value="D10"/>"                                            
                                               />
                                    </td>
                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"   value="<s:property  value="D17" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="TEN_KH" onfocus="this.select();" 
                                           />
                                </td>  
                                   
                                
                            </tr>                                                                                                                                                                                   
                        </s:iterator>
                    </table>        
                </div>
            </div>

            <sj:submit id="NQ11CP_02SK_save" name="NQ11CP_02SK_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
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

