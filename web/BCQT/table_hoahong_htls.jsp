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
        .cls-over1{
            overflow-x: scroll;
            overflow-y: scroll;            
        }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
             var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('input.number3').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $(".datepicker_month").datepicker({dateFormat: 'mm/yy'});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $('.number3').number(true, 3);
                $(".TD_STT").css({"width": "20px"});
                $(".TD_TRANGTHAI").css({"width": "40px"});
                $(".TD_TOTIEN").css({"width": "70px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "130px"});
                $(".TD_SOKU").css({"width": "100px"});
                $(".TD_GL").css({"width": "60px"});
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
             function hienthichitiet(mapgd, soku) {
                try
                {                    
                    var ht1 = screen.height -500;
                    var wt1 = screen.width - 200;
                    var left1 = 50;//(screen.width / 2) - (wt1 / 2);
                    var top1 = 50;
                    var ngay_bc = $("#ngay_bc_DATE").val();
                    var url = "loadChitietHoahong.action?mapgd=" + mapgd + "&ngay_bc=" + ngay_bc + "&soku=" + soku;

                    //$.post(url,param,function(data){});
                    popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

                    window.refreshData = function () {
                        //alert('aaaa');
                        $("#loadDatatmp").trigger("click");
                    };
                } catch (e)
                {
                    alert('Lỗi', 'Lỗi: ' + e.toString(), 'error');
                }
            }
            function initTable()
            {
                var table = document.getElementById("tablehoahong");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    //cho combox 1
                    var matmp1 = getMabyNumber1(i);//                       
                    if (matmp1 == 1)
                    {
                        $('input:checkbox[id=idhh' + i + ']').attr('checked', true);
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
        
    </head>
    <body>
        <s:form id="id_sv_%{khoa_bcqt}" action="SAVE_%{khoa_bcqt}" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:if test="Grade.equalsIgnoreCase('2')">
                <div id="divTitle">
                    XÁC NHẬN SỐ LIỆU HẠCH TOÁN HOA HỒNG BỔ SUNG MÓN VAY HTLS
                    <div id="luu_thanhcong_del"></div>
                </div>
                <s:hidden name="khoa_bcqt"/>

                <div style="overflow: scroll;overflow-x: scroll;height:450px; width: 99%">
                    <table border="1" class="editDelete" id="tablehoahong" align="center">
                        <tr>      
                            <th rowspan="1" class="TD_STT">STT</th>                           
                            <th rowspan="1" class="TD_SOKU">Mã PGD</th>  
                            <th rowspan="1" class="TD_TENKH">Tên PGD</th>   
                             <th rowspan="1"  class="TD_TOTIEN">Số món vay</th>
                            <th rowspan="1"  class="TD_TOTIEN">Dư nợ</th>
                            <th rowspan="1" class="TD_TOTIEN">Tổng số tiền hoa hồng</th> 
                            <th rowspan="1"  class="TD_TOTIEN">Số tiền hoa hồng tổ TK&VV</th>   
                            <th rowspan="1"  class="TD_TOTIEN">Số tiền hoa hồng cho cấp huyện</th>
                            <th rowspan="1" class="TD_TOTIEN">Số tiền hoa hồng cho cấp tỉnh</th> 
                            <!--<th rowspan="1"  class="TD_TOTIEN">Lãi âm do khoanh nợ sai</th>-->   
                             <th rowspan="1"  class="TD_TOTIEN">Chốt/ Mở chốt</th>   

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
                            <td style="text-align: center">(9</td>
                            <td style="text-align: center">(10)</td>

                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">

                            <tr height="22">  
                                <td align = "right" class="TD_STT" >
                                    <input type="text"   value="<s:property  value="THUTU" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="D0 TEN_KH " onfocus="this.select();"
                                           readonly="true"/>                                        
                                </td>  


                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="MAPGD" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" class="D0 TEN_KH " onfocus="this.select();" 
                                           readonly="true"/> 
                                    
                                    <input type="hidden" value="<s:property  value="D25" />"  id="id9_<s:property  value="%{#rowstatus.index}" />" 
                                        value="<s:property  value="D25"/>"/>
                                     <input type="hidden" value="<s:property  value="MACN" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN" value="<s:property  value="MACN"/>" />
                                </td>
                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"   value="<s:property  value="TEN" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class=" TEN_KH " onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D1" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="number TEN_KH " onfocus="this.select();"
                                           readonly="true"/>
                                </td>

                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D2" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="number TEN_KH" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D3" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="number TEN_KH" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D4" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="number TEN_KH " onfocus="this.select();"
                                           readonly="true"/>
                                </td>

                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D5" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number TEN_KH" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D6" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number TEN_KH" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                               
                                 <td  align="center" class="TD_CHECKBOX">    
                                    <input type="checkbox" id ="idhh<s:property  value="%{#rowstatus.index}" />"  class="checkboxdat TEN_KH D0" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25" value="<s:property  value="D25"/>"                                            
                                           />
                                </td> 
                               
                            </tr>


                        </s:iterator>
                    </table>
                </div>
            </s:if>
            <s:else>
                
                <div id="divTitle">
                    THÔNG TIN HẠCH TOÁN HOA HỒNG BỔ SUNG MÓN VAY HTLS
                </div>
                <s:hidden name="khoa_bcqt"/>
                <div id="divDonvitinh">
                    Đơn vị tính: Đồng
                </div>
                    <div style="overflow: scroll;overflow-x: scroll;height:450px; width: 99%">
                        <table border="1" class="editDelete" id="tablehoahong" align="center">
                            <tr>      
                                <!--<th rowspan="2" class="TD_STT">STT</th>-->                           
                                <th rowspan="2" class="TD_TENKH">Tổ TK&VV</th>  
                                <th rowspan="2" class="TD_TENKH">Tên</th>  
                                <th rowspan="2" class="TD_SOKU">Mã khoản vay</th>    
                                <th rowspan="2"  class="TD_TOTIEN">Dư nợ</th>
                                <th rowspan="2" class="TD_TRANGTHAI">Lãi suất khoản vay (%/năm)</th> 
                                <th rowspan="2"  class="TD_TOTIEN">Số tiền HTLS trong kỳ</th>   
                                <th rowspan="2"  class="TD_TOTIEN">Tỷ lệ chi hoa hồng (%/tháng)</th>                               
                                <th rowspan="2"  class="TD_TOTIEN">Số tiền chi hoa hồng bổ sung</th>   
                                <th rowspan="2"  class="TD_SOKU">Mã nhà đầu tư</th>  
                                <th colspan="3" class="TD_TOTIEN">Phân bổ theo cấp</th>  
                                <th rowspan="2" class="TD_TRANGTHAI"></th>  

                                <th rowspan="2" class="TD_TRANGTHAI">Xác nhận</th>                            
                            </tr>   
                            <tr>
                                 <th class="TD_TOTIEN">Tổ</th>  
                                 <th class="TD_TOTIEN">Huyện</th>  
                                 <th class="TD_TOTIEN">Tỉnh</th>  
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
                                <!--<td style="text-align: center">(15)</td>-->
                                <!--<td style="text-align: center">(16)</td>-->
    <!--                            <td style="text-align: center">(17)</td>                                                        
                                <td style="text-align: center">(18)</td>                                   
                                <td style="text-align: center">(19)</td>  -->

                            </tr>
                            <s:iterator value="#attr.lstDulieuHoahong" var="modelView" status="rowstatus">

                                <tr height="22">  
                                    <td align = "right" class="TD_TENKH">
                                        <input type="text" value="<s:property  value="groupLeaderName" />" 
                                               name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].groupLeaderName" class="TEN_KH"  readonly="readonly"/>
                                         <input type="hidden" value="<s:property  value="posCode" />"
                                             name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].posCode" /> 
                                         <input type="hidden" value="<s:property  value="mainPos" />"
                                             name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].mainPos"/> 
                                         
                                    </td>  
                                    
                                    <td align = "right" class="TD_TENKH">
                                        <input type="text" value="<s:property  value="customerName" />" 
                                               name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].customerName" class="TEN_KH"  readonly="readonly"/>
                                    </td> 
                                    <td align = "right" class="TD_SOKU">
                                        <input type="text" value="<s:property  value="loanId" />" 
                                               name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].loanId" class="TEN_KH"  readonly="readonly"/>
                                    </td> 
                                    
                                    <td align = "right" class="TD_TOTIEN">
                                        <input type="text" value="<s:property  value="prinTotal" />" 
                                               name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].prinTotal" class="number TEN_KH"  readonly="readonly"/>
                                    </td> 
                                    
                                    <td align = "right" class="TD_TRANGTHAI">
                                        <input type="text" value="<s:property  value="interestRate" />" 
                                               name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].interestRate" class="TEN_KH number2 "  readonly="readonly"/>
                                    </td> 
                                    
                                    <td align = "right" class="TD_TOTIEN">
                                        <input type="text" value="<s:property  value="subsidyTotalAmount" />" 
                                               name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].subsidyTotalAmount" class="number TEN_KH"  readonly="readonly"/>
                                    </td> 
                                    <s:if test="capitalSourceCode.equalsIgnoreCase('2')">
                                        <td align = "right" class="TD_TRANGTHAI">
                                            <input type="text" value="<s:property  value="commisionRate" />" 
                                                   name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].commisionRate" class="number2 TEN_KH"  readonly="readonly"                                                   
                                                   />
                                        </td> 

                                        <td align = "right" class="TD_TOTIEN">
                                            <input type="text" value="<s:property  value="commisionTotalAmount" />" 
                                                   name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].commisionTotalAmount" class="number TEN_KH"  readonly="readonly"                                                   
                                                   />
                                        </td> 
                                    </s:if>
                                    <s:else>
                                        <td align = "right" class="TD_TRANGTHAI">
                                            <input type="text" value="<s:property  value="commisionRate" />" 
                                                   name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].commisionRate" class="number3 TEN_KH" 
                                                   onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'commisionTotalAmount<s:property  value="%{#rowstatus.index}" />', 'nono','')"/>
                                        </td> 

                                        <td align = "right" class="TD_TOTIEN">
                                            <input type="text" value="<s:property  value="commisionTotalAmount" />" id="commisionTotalAmount<s:property  value="%{#rowstatus.index}" />"
                                                   name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].commisionTotalAmount" class="number TEN_KH" 
                                                   onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'commisionTotalAmount<s:property  value="%{#rowstatus.index}" />', 'ChangeVal', 'commisionGroupAmount<s:property  value="%{#rowstatus.index}" />')"/>
                                        </td> 
                                    </s:else>
                                    
                                    
                                     <td align = "right" class="TD_SOKU">
                                        <input type="text" value="<s:property  value="investorCode" />" 
                                               name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].investorCode" class="D0"  readonly="readonly"/>
                                    </td> 
                                   
                                    
                                    <td align = "right" class="TD_TOTIEN">
                                        <input type="text" value="<s:property  value="commisionGroupAmount" />" id="commisionGroupAmount<s:property  value="%{#rowstatus.index}" />"
                                               name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].commisionGroupAmount" class="number TEN_KH"  readonly="readonly"/>
                                    </td> 
                                    
                                     <td align = "right" class="TD_TOTIEN">
                                        <input type="text" value="<s:property  value="commisionDistrictAmount" />" 
                                               name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].commisionDistrictAmount" class="number TEN_KH"  readonly="readonly"/>
                                    </td> 
                                    
                                     <td align = "right" class="TD_TOTIEN">
                                        <input type="text" value="<s:property  value="commisionProvinceAmount" />" 
                                               name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].commisionProvinceAmount" class="number TEN_KH"  readonly="readonly"/>
                                    </td> 
                                    <td align = "right" class="TD_TRANGTHAI">
                                        <s:if test="capitalSourceCode.equalsIgnoreCase('2')">
                                            <a href="javascript:hienthichitiet('<s:property  value="posCode" />','<s:property value="loanId"/>')" class="D0 SOKU linkKh">
                                                Chi tiết&nbsp;&nbsp;
                                            </a>
                                        </s:if>
                                    </td>
                                    
                                    <s:if test="capitalSourceCode.equalsIgnoreCase('2')">
                                         <td>
                                             </td>
                                     </s:if>
                                     <s:else>
                                          <td align = "right" class="TD_TRANGTHAI">
                                            <input type="checkbox" id ='idchk<s:property  value="%{#rowstatus.index}" />' class="checkboxdat TEN_KH" 
                                                       name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].D33" value="<s:property  value="D33"/>"                                            
                                                       />
                                        </td>
                                     </s:else>
                                        
<!--                                        <td align = "right" class="TD_TOTIEN">
                                        <input type="text" value="<s:property  value="capitalSourceCode" />" 
                                               name="lstDulieuHoahong[<s:property  value="%{#rowstatus.index}" />].capitalSourceCode" class="number TEN_KH"  readonly="readonly"/>
                                    </td> -->
                                    
                                </tr>


                            </s:iterator>
                        </table>
                </div>
                </s:else>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initTable();
            function CheckUpdate(idchk, iddata, status, idSet) {
//                alert ('idchk=' + idchk + 'iddata='+ iddata+ 'status=' + status)
                if (status === "ChangeVal") {
                    var val = $('#' + iddata).val();
                    var valbk = $('#' + iddata + 'BK').val();
                    if (val === valbk) {
                        $('#' + idchk).attr('checked', true);
                    } else {
                        $('#' + idchk).attr('checked', true);
                    }
                    $('#' + idSet).val($('#' + iddata).val());
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
