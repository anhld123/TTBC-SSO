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
        <style>
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
        <s:form id="id_sv_NQ11CP_001" action="SAVE_NQ11CP_001" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            </br>
            <div id="divTitle">
                <s:if test="chotsl.equalsIgnoreCase('1')">                     
                    XÁC NHẬN <font color="red">SỐ TIỀN</font> ĐƯỢC HTLS CHO VAY CÁC CHƯƠNG TRÌNH TÍN DỤNG CHÍNH SÁCH THEO NGHỊ QUYẾT 11/NQ-CP CỦA THỦ TƯỚNG CHÍNH PHỦ 
                </s:if>              
                <s:else>
                     XÁC NHẬN <font color="red">MÓN VAY</font> ĐƯỢC HTLS CHO VAY CÁC CHƯƠNG TRÌNH TÍN DỤNG CHÍNH SÁCH THEO NGHỊ QUYẾT 11/NQ-CP CỦA THỦ TƯỚNG CHÍNH PHỦ 
                </s:else>    
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nghiquyet11cp"/>
            <s:hidden name="chotsl"/>
            <table id="tblTable12" class="tblTable" style="width: 88%;">
                <tr>      
                    <th rowspan="2" class="TD_TOTIEN">Tổng cộng</th> 
                    <th colspan="1"  class="TD_SOKU">Dư nợ</th> 
                    <th  class="TD_SOKU">Trong hạn</th>
                    <th  class="TD_SOKU">Quá hạn</th>
                    <th  class="TD_SOKU">Khoanh</th>                            

                    <th  class="TD_TOTIEN">Số tiền HTLS tháng 1</th>                                                                             

                    <th  class="TD_TOTIEN">Số tiền HTLS tháng 2</th>   
                    
                    <th  class="TD_TOTIEN">Số tiền HTLS tháng 3</th> 
                    <th  class="TD_TOTIEN">Số tiền HTLS tháng 4</th>
                    <th  class="TD_TOTIEN">Số tiền HTLS tháng 5</th>
                    <th  class="TD_TOTIEN">Số tiền HTLS tháng 6</th>


                    <th rowspan="1"  class="TD_TOTIEN">Số tiền hạch toán HTLS</th>                              

                </tr>         

               <s:iterator value="#attr.lstDulieuNt_tong" var="modelView" status="rowstatus">                             
                    <tr>      
                        <%--<s:if test="THUTU == 1">--%>
                        <td align = "right" class="TD_SOKU" >
                            <input type="text"   value="<s:property  value="D1" />" 
                                   name="lstDulieuNt_tong[<s:property  value="%{#rowstatus.index}" />].D1" class="number TEN_KH " onfocus="this.select();"
                                   readonly="true"/>                                        
                        </td>  


                        <td align = "right" class="TD_SOKU" >
                            <input type="text"   value="<s:property  value="D2" />" 
                                   name="lstDulieuNt_tong[<s:property  value="%{#rowstatus.index}" />].D2" class="number TEN_KH " onfocus="this.select();" 
                                   readonly="true"/> 
                        </td>
                        <td align = "right" class="TD_SOKU" >
                            <input type="text"   value="<s:property  value="D3" />" 
                                   name="lstDulieuNt_tong[<s:property  value="%{#rowstatus.index}" />].D3" class="number TEN_KH " onfocus="this.select();"
                                   readonly="true"/>
                        </td>

                        <td align = "right" class="TD_SOKU" >
                            <input type="text"   value="<s:property  value="D4" />" 
                                   name="lstDulieuNt_tong[<s:property  value="%{#rowstatus.index}" />].D4" class="number TEN_KH" onfocus="this.select();" 
                                   readonly="true"/>
                        </td>

                        <!--lai suat-->
                        <td align = "right" class="TD_TOTIEN" >
                            <input type="text"   value="<s:property  value="D5" />" 
                                   name="lstDulieuNt_tong[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();" 
                                   readonly="true"/>
                        </td>

                        <td align = "right" class="TD_TOTIEN" >
                            <input type="text"   value="<s:property  value="D6" />" 
                                   name="lstDulieuNt_tong[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();" 
                                   readonly="true"/>
                        </td>


                        <td align = "right" class="TD_TOTIEN" >
                            <input type="text"   value="<s:property  value="D7" />" 
                                   name="lstDulieuNt_tong[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number" onfocus="this.select();" 
                                   readonly="true"/>
                        </td>
                       
                        <td align = "right" class="TD_TOTIEN" >
                            <input type="text"   value="<s:property  value="D15" />" 
                                   name="lstDulieuNt_tong[<s:property  value="%{#rowstatus.index}" />].D15" class="TEN_KH number" onfocus="this.select();" 
                                   readonly="true"/>
                        </td>
                        <td align = "right" class="TD_TOTIEN" >
                            <input type="text"   value="<s:property  value="D16" />" 
                                   name="lstDulieuNt_tong[<s:property  value="%{#rowstatus.index}" />].D16" class="TEN_KH number" onfocus="this.select();" 
                                   readonly="true"/>
                        </td>
                        <td align = "right" class="TD_TOTIEN" >
                            <input type="text"   value="<s:property  value="D17" />" 
                                   name="lstDulieuNt_tong[<s:property  value="%{#rowstatus.index}" />].D17" class="TEN_KH number" onfocus="this.select();" 
                                   readonly="true"/>
                        </td>

                        <td align = "right" class="TD_TOTIEN" >
                            <input type="text"   value="<s:property  value="D11" />" 
                                   name="lstDulieuNt_tong[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH number" onfocus="this.select();" 
                                   readonly="true"/>
                        </td>                
                    </tr>                                                                                                                                                                                   
                </s:iterator>
            </table>     
            &nbsp;&nbsp;
            <!--                </div>
                        </div>
            -->
            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 98%; max-height:45vh">
                    <table id="tblTable">
                        <tr >      
                            <th rowspan="2" class="TD_STT">STT</th>                           
                            <!--<th rowspan="2" class="TD_TOTIEN">CIF</th>-->  
                            <th rowspan="2" class="TD_TENKH">Tên KH</th>  
                            <th rowspan="2" class="TD_SOKU">Mã khoản vay</th>    
                            <th rowspan="2"  class="TD_NGAY">Chương trình tín dụng</th>                             
                            <th rowspan="2"  class="TD_NGAY">Lãi suất</th>   
                            <th rowspan="2"  class="TD_NGAY">Trạng thái món vay</th>   
                            <th rowspan="2"  class="TD_NGAY">Kiểu định lịch trả nợ</th>  
                            <th colspan="3"  class="TD_MAKH">Dư nợ</th>                             
                            <th colspan="6"  class="TD_MAKH">Số tiền HTLS các tháng trong năm 2022</th>                                                                   

                            <!--<th rowspan="2"  class="TD_MAKH">Phân loại RPA hoặc Phải trả</th>-->  
                            <th rowspan="2"  class="TD_MAKH">Đơn vị xác nhận món vay được HTLS (Có/Không)</th>                              
                            <th rowspan="2"  class="TD_MAKH">Cập nhật</th>  
                            <!--<th rowspan="2"  class="TD_GHICHU">Ghi chú</th>-->  

                        </tr>         
                        <tr >
                            <th  class="TD_TOTIEN">Trong hạn</th>
                            <th  class="TD_TOTIEN">Quá hạn</th>
                            <th  class="TD_TOTIEN">Khoanh</th>
                            <th  class="TD_NGAY">Tháng 1</th>                                                        
                            <th  class="TD_NGAY">Tháng 2</th>                                                        
                            <th  class="TD_NGAY">Tháng 3</th>                                                        
                            <th  class="TD_NGAY">Tháng 4</th>   
                            <th  class="TD_NGAY">Tháng 5</th>   
                            <th  class="TD_NGAY">Tháng 6</th>   
                        </tr>
                        <tr style="font-style: italic;">
                            <td style="text-align: center">(1)</td>
                            <!--<td style="text-align: center">(2)</td>-->
                            <td style="text-align: center">(2)</td>
                            <td style="text-align: center">(3)</td>
                            <td style="text-align: center">(4)</td>
                            <td style="text-align: center">(5)</td>
                            <td style="text-align: center">(6)</td>
                            <td style="text-align: center">()</td>
                            <td style="text-align: center">(7)</td>
                            <td style="text-align: center">(8)</td>
                            <td style="text-align: center">(9)</td>
                            <td style="text-align: center">(10)</td>
                            <td style="text-align: center">(11)</td>
                            <td style="text-align: center">(12)</td>
                            <td style="text-align: center">(13)</td>
                            <td style="text-align: center">(14)</td>
                            <!--<td style="text-align: center">(13)</td>-->
                            <td style="text-align: center">(15)</td>
                            <th  class="TD_STT">
                                <s:if test="chotsl.equalsIgnoreCase('0')">
                                    <input type="checkbox" id ="allCheck_dat" name="allCheck_dat"  />
                                </s:if>
                                <s:else>
                                    
                                </s:else>
                                
                            </th>  
<!--                            <td style="text-align: center">(15)</td>
                            <td style="text-align: center">(16)</td>
                            <td style="text-align: center">(17)</td>
                            <td style="text-align: center">(19)</td>
                            <td style="text-align: center">(18)</td>
                            <td style="text-align: center">(19)</td>
                            <td style="text-align: center">(20)</td>-->
                            <td style="text-align: center">(21)</td>
                            <!--<td style="text-align: center">(22)</td>-->

                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>                               
                                <td align = "right" class="TD_STT" >
                                    <input type="text"   value="<s:property  value="THUTU" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="D0 TEN_KH " onfocus="this.select();"
                                           readonly="true"/>                                        
                                </td>  


                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"   value="<s:property  value="D50" />" style="background: #C0C0C0 !important;" title="<s:property  value="D50" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D50" class=" TEN_KH " onfocus="this.select();" 
                                           readonly="true"/> 

                                    <input type="hidden" value="<s:property  value="D25" />"  id="id9_<s:property  value="%{#rowstatus.index}" />" 
                                           value="<s:property  value="D25"/>"/>
                                </td>
                                <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D3" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D0 TEN_KH " onfocus="this.select();"
                                           readonly="true"/>
                                </td>

                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D9" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D0 TEN_KH" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>

                                <!--lai suat-->
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D8" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D0 TEN_KH " onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D12" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D0 TEN_KH " onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D59" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D59" class="D0 TEN_KH " onfocus="this.select();" 
                                           readonly="true"/>
                                </td>

                                <!--Du no: trong qua khoanh-->
                                <td align = "right" class="TD_TOTIEN" >
                                    <input type="text"   value="<s:property  value="D5" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TOTIEN" >
                                    <input type="text"   value="<s:property  value="D6" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TOTIEN" >
                                    <input type="text"   value="<s:property  value="D7" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <!--Lai giam cac tháng-->
                                <s:if test="chotsl.equalsIgnoreCase('0')">
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D18" />" style="background: #C0C0C0 !important;"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH number" onfocus="this.select();" id='D18<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D18<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" readonly="true"/>
                                        <input type="text"   value="<s:property  value="D18" />"
                                                class="DataHiden" id='D18<s:property  value="%{#rowstatus.index}" />BK'/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D19" />" style="background: #C0C0C0 !important;"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="TEN_KH number" onfocus="this.select();" id='D19<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D19<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" readonly="true"/>
                                        <input type="text"   value="<s:property  value="D19" />"
                                               class="DataHiden" id='D19<s:property  value="%{#rowstatus.index}" />BK'/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D20" />" style="background: #C0C0C0 !important;"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="TEN_KH number" onfocus="this.select();" id='D20<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D20<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" readonly="true"/>
                                        <input type="text"   value="<s:property  value="D20" />"
                                               class="DataHiden" id='D20<s:property  value="%{#rowstatus.index}" />BK'/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D55" />" style="background: #C0C0C0 !important;"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D55" class="TEN_KH number" onfocus="this.select();" id='D55<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D55<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" readonly="true"/>
                                        <input type="text"   value="<s:property  value="D55" />"
                                               class="DataHiden" id='D55<s:property  value="%{#rowstatus.index}" />BK'/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D39" />" style="background: #C0C0C0 !important;"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D39" class="TEN_KH number" onfocus="this.select();" id='D39<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D39<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" readonly="true"/>
                                        <input type="text"   value="<s:property  value="D39" />"
                                               class="DataHiden" id='D39<s:property  value="%{#rowstatus.index}" />BK'/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D40" />" style="background: #C0C0C0 !important;"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D40" class="TEN_KH number" onfocus="this.select();" id='D40<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D40<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" readonly="true"/>
                                        <input type="text"   value="<s:property  value="D40" />"
                                               class="DataHiden" id='D40<s:property  value="%{#rowstatus.index}" />BK'/>
                                    </td>
                                </s:if>
                                <s:else>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D18" />" <s:if test="MA.equalsIgnoreCase('1')">style="background: #C0C0C0 !important;"</s:if>
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH number" onfocus="this.select();" id='D18<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D18<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" />
                                        <input type="text"   value="<s:property  value="D18" />"
                                                class="DataHiden" id='D18<s:property  value="%{#rowstatus.index}" />BK'/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D19" />" <s:if test="MA.equalsIgnoreCase('1')">style="background: #C0C0C0 !important;"</s:if>
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="TEN_KH number" onfocus="this.select();" id='D19<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D19<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" />
                                        <input type="text"   value="<s:property  value="D19" />"
                                               class="DataHiden" id='D19<s:property  value="%{#rowstatus.index}" />BK'/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D20" />" <s:if test="MA.equalsIgnoreCase('1')">style="background: #C0C0C0 !important;"</s:if>
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="TEN_KH number" onfocus="this.select();" id='D20<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D20<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" />
                                        <input type="text"   value="<s:property  value="D20" />"
                                               class="DataHiden" id='D20<s:property  value="%{#rowstatus.index}" />BK'/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D55" />" <s:if test="MA.equalsIgnoreCase('1')">style="background: #C0C0C0 !important;"</s:if>
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D55" class="TEN_KH number" onfocus="this.select();" id='D55<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D55<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" />
                                        <input type="text"   value="<s:property  value="D55" />"
                                               class="DataHiden" id='D55<s:property  value="%{#rowstatus.index}" />BK'/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D39" />" <s:if test="MA.equalsIgnoreCase('1')">style="background: #C0C0C0 !important;"</s:if>
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D39" class="TEN_KH number" onfocus="this.select();" id='D39<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D39<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" />
                                        <input type="text"   value="<s:property  value="D39" />"
                                               class="DataHiden" id='D39<s:property  value="%{#rowstatus.index}" />BK'/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D40" />" <s:if test="MA.equalsIgnoreCase('1')">style="background: #C0C0C0 !important;"</s:if>
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D40" class="TEN_KH number" onfocus="this.select();" id='D40<s:property  value="%{#rowstatus.index}" />' onblur="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'D40<s:property  value="%{#rowstatus.index}" />', 'ChangeVal')" />
                                        <input type="text"   value="<s:property  value="D40" />"
                                               class="DataHiden" id='D40<s:property  value="%{#rowstatus.index}" />BK'/>
                                    </td>
                                </s:else>
                                    
                                 
                                

                                
                                <!--D25 xac nhận giảm-->
                                <!--                                 <td align = "center" class="TD_CHECKBOX"> 
                                <s:checkbox id ="%{#rowstatus.index}" cssClass="checkboxsp" name="lstsaveNT_SP[%{#rowstatus.index}].MA" fieldValue="%{MA}"/>
                            </td>      -->
                                <s:if test="MA.equalsIgnoreCase('1')">
                                    <td></td>
                                </s:if>    
                                <s:else>
                                    <s:if test="chotsl.equalsIgnoreCase('0')">
                                        <td  align="center" class="TD_CHECKBOX">    
                                            <input type="checkbox" id ="idc11<s:property  value="%{#rowstatus.index}" />"  class="checkboxdat TEN_KH D0" 
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25" value="<s:property  value="D25"/>" onclick="CheckUpdate('idchk<s:property  value="%{#rowstatus.index}" />', 'ClickCheck')"                                           
                                                   />
                                        </td> 
                                    </s:if>
                                    <s:else>
                                        <td  align="center" class="TD_CHECKBOX">    
                                            <input type="checkbox" disabled="disabled" checked="checked">
                                        </td> 
                                    </s:else>
                                    
                                </s:else>    

                                <s:if test="MA.equalsIgnoreCase('1')">
                                    <td></td>
                                </s:if>    
                                <s:else>
                                    <td  align="center" class="TD_CHECKBOX">    
                                        <input type="checkbox" id ='idchk<s:property  value="%{#rowstatus.index}" />' class="checkboxdat TEN_KH" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D33" value="<s:property  value="D33"/>"                                            
                                               />
                                    </td> 
                                </s:else>    
                                    
<!--                                <td align = "right" class="TD_GHICHU" >
                                    <input type="text"   value="<s:property  value="D47" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D47" class="TEN_KH" onfocus="this.select();" />
                                </td>    -->
                                
                            </tr>                                                                                                                                                                                   
                        </s:iterator>
                    </table>        
                </div>
            </div>

            <sj:submit id="NQ11CP_001_save" name="NQ11CP_001_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
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

