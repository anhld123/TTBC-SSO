<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/css2025.css" />
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('.number5').css("text-align", "right").number(true, 0);
                $(".D99").css({"text-align": "center", "color": "#000", "font-style": "italic", "font-size": "xx-small"});
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
    </head>
    <body style="font-family: ">
        <s:form id="htl_id_sv_%{khoa_nhaptaycn}" action="HTL_SAVE_%{khoa_nhaptaycn}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            </br>
            <div id="divTitle">
                CẬP NHẬT THÔNG TIN SỐ TIỀN LÃI GIẢM THEO QUYẾT ĐỊNH SỐ 2654/QĐ-TTg
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nhaptaycn"/>            
            <table id="tblTable12" class="subTable" style="width: 88%;">
                <tr>      
                    <th rowspan="3" class="TD_TOTIEN">Tổng cộng</th> 
                    <th colspan="1"  class="TD_SOKU">Dư nợ</th> 
                    <th  class="TD_SOKU">Trong hạn</th>
                    <th  class="TD_SOKU">Quá hạn</th>
                    <th  class="TD_SOKU">Khoanh</th>                            

                    <th  class="TD_TOTIEN">Lãi giảm T10</th>                                                        
                    <th  class="TD_TOTIEN">Lãi giảm T11</th>   
                    <th  class="TD_TOTIEN">Lãi giảm T12</th> 

                    <th rowspan="1"  class="TD_TOTIEN">Hệ thống đã hạch toán giảm lãi</th>                              
                    <th rowspan="1"  class="TD_TOTIEN">Chuyển vào RPA</th>  
                    <th rowspan="1"  class="TD_TOTIEN">Chuyển CASA</th>  
                    <th rowspan="1"  class="TD_TOTIEN">Tài khoản phải trả bên ngoài</th> 
                    <th rowspan="1"  class="TD_TOTIEN">Số tiền GL điều chỉnh ngày 31/12 kéo dài</th> 
                    <th rowspan="1"  class="TD_TOTIEN">Tổng số tiền giảm lãi sau điều chỉnh ngày 31/12 kéo dài</th> 
                    <th rowspan="1"  class="TD_TOTIEN">Số tiền hạch toán bổ sung</th> 
                </tr> 
                <tr> <% for (int i = 1; i <= 14; i++) {%>
                    <th class="D99">(<%= i%>)</th>
                        <% }%>
                </tr>
                <s:iterator value="#attr.lstDulieuNt_tong" var="modelView" status="rowstatus">                             
                    <tr>      
                        <%--<s:if test="THUTU == 1">--%>
                        <td class="number5" style="text-align: right"><s:property  value="D1" /> </td>  
                        <td class="number5" style="text-align: right"><s:property  value="D2" /> </td>  
                        <td class="number5" style="text-align: right"><s:property  value="D3" /> </td>  
                        <td class="number5" style="text-align: right"><s:property  value="D4" /> </td>  
                        <td class="number5" style="text-align: right"><s:property  value="D5" /> </td>  
                        <td class="number5" style="text-align: right"><s:property  value="D6" /> </td>  
                        <td class="number5" style="text-align: right"><s:property  value="D7" /> </td>  
                        <!--2025-->
                        <td class="number5" style="text-align: right"><s:property  value="D16" /> </td> 
                        <td class="number5" style="text-align: right"><s:property  value="D17" /> </td>  
                        <td class="number5" style="text-align: right"><s:property  value="D18" /> </td>  
                        <td class="number5" style="text-align: right"><s:property  value="D19" /> </td>  
                        <td class="number5" style="text-align: right"><s:property  value="D20" /> </td>  
                        <td class="number5" style="text-align: right"><s:property  value="D21" /> </td>  
                        <td class="number5" style="text-align: right"><s:property  value="D22" /> </td>  
                    </tr>                                                                                                                                                                                   
                </s:iterator>
            </table>     
            &nbsp;&nbsp;
            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 98vw; max-height:45vh">
                    <table id="tblTable" class="subTable">
                        <tr>      
                            <th rowspan="3" class="TD_STT">STT</th>            
                            <th rowspan="3" class="TD_TENKH">Tên KH</th>  
                            <th rowspan="3" class="TD_TENKH">Mã khoản vay</th>    
                            <th rowspan="3"  class="TD_NGAY">Chương trình tín dụng</th>                             
                            <th rowspan="3"  class="TD_NGAY">Lãi suất</th>   
                            <th rowspan="3"  class="TD_NGAY">Trạng thái món vay</th>   
                            <th colspan="3" class="TD_MAKH">Dư nợ</th>                             
                            <th colspan="3"  class="TD_MAKH">Lãi giảm các tháng</th>                                                         
                            <th colspan="4"  class="TD_MAKH">Số tiền GL hệ thống đã hạch toán tự động</th>   
                            <th colspan="5"  class="TD_MAKH" style="color: red">Số tiền GL điều chỉnh ngày 31/12 kéo dài</th>
                            <th rowspan="3"  class="TD_MAKH">Tổng số tiền giảm lãi sau điều chỉnh ngày 31/12 kéo dài</th>
                            <th rowspan="3"  class="TD_MAKH" title="Xác nhận để chốt lại số GL sau điều chỉnh thủ công ngày 31 kéo dài" >Cập nhật(*)</th>

                        </tr>         
                        <tr>
                            <th  class="TD_TOTIEN" rowspan="2">Trong hạn</th>
                            <th  class="TD_TOTIEN" rowspan="2">Quá hạn</th>
                            <th  class="TD_TOTIEN" rowspan="2">Khoanh</th>                                                       
                            <th  class="TD_NGAY" rowspan="2">Tháng 10</th>                                                        
                            <th  class="TD_NGAY" rowspan="2">Tháng 11</th>                                                        
                            <th  class="TD_NGAY" rowspan="2">Tháng 12</th>  
                            <th  class="TD_NGAY" rowspan="2">Tổng số tiền</th>
                            <th  colspan="3" class="TD_NGAY">Trong đó</th>
                            <th  class="TD_NGAY" rowspan="2" style="color: red">Tổng số tiền</th>
                            <th  colspan="4" class="TD_NGAY" style="color: red">Trong đó</th>
                        </tr>         
                        <tr>
                            <th  class="TD_NGAY" title="Hạch toán vào khoản trả trước của khách hàng">RPA(*)</th>  
                            <th  class="TD_NGAY">Hạch toán CASA</th>  
                            <th  class="TD_NGAY">Tài khoản phải trả bên ngoài</th>   
                            <th  class="TD_NGAY" style="color: red">Số tiền hạch toán bổ sung</th> 
                            <th  class="TD_NGAY" style="color: red">Hạch toán CASA</th>  
                            <th  class="TD_NGAY" style="color: red">Tài khoản phải trả bên ngoài</th>  
                            <th  class="TD_NGAY" style="color: red">Số bút toán điều chỉnh</th>  

                        </tr>
                        <tr> <% for (int i = 1; i <= 23; i++) {%>
                            <th class="D99">(<%= i%>)</th>
                                <% }%>
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>                               
                                <td class="D0"><s:property value="%{#rowstatus.index + 1}" />
                                    <input type="hidden" value="<s:property  value="D3" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D3"/>"/>
                                    <input type="hidden" value="<s:property  value="MACN" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN" value="<s:property  value="MACN"/>"/>
                                    <input type="hidden" value="<s:property  value="MAPGD" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>"/>
                                </td>
                                <td><s:property value="D51" /></td>
                                <td class="D0"><s:property value="D3" /></td>
                                <td class="D0"><s:property value="D9" /></td>
                                <td class="D0"><s:property value="D8" /></td>
                                <td class="D0"><s:property value="D12" /></td>

                                <!--Du no: trong qua khoanh-->
                                <td class="number5"><s:property value="D5" /></td>
                                <td class="number5"><s:property value="D6" /></td>
                                <td class="number5"><s:property value="D7" /></td>
                                <!--Lai giam cac tháng-->
                                <td class="number5"><s:property value="D18" /></td>
                                <td class="number5"><s:property value="D19" /></td>
                                <td class="number5"><s:property value="D20" /></td>


                                <td class="number5" id="D55<s:property value='%{#rowstatus.index}'/>">
                                    <s:property value="D55"/>
                                </td>
                                <td class="number5"><s:property value="D28" /></td>
                                <td class="number5"><s:property value="D53" /></td>
                                <td class="number5"><s:property value="D54" /></td>

                                <td class="number5" id="D56<s:property value='%{#rowstatus.index}'/>">
                                    <s:property value="D56"/>
                                </td>

                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D58" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D58" class="TEN_KH number" 
                                           id  ='D52<s:property  value="%{#rowstatus.index}" />'
                                           <s:if test="ngay3112.equalsIgnoreCase('1')">readonly </s:if>
                                           <s:else>style="background: #ffe6f2 !important;" </s:else>
                                           />
                                </td>

                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D52" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D52" class="TEN_KH number" 
                                           id  ='D52<s:property  value="%{#rowstatus.index}" />'
                                           <s:if test="ngay3112.equalsIgnoreCase('1')">readonly </s:if>
                                           <s:else>style="background: #ffe6f2 !important;" </s:else>
                                           onblur="calD56(<s:property value='%{#rowstatus.index}'/>)"
                                           />
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D31" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D31" class="TEN_KH number" 
                                           id  ='D31<s:property  value="%{#rowstatus.index}" />'
                                           <s:if test="ngay3112.equalsIgnoreCase('1')">readonly </s:if>
                                           <s:else>style="background: #ffe6f2 !important;" </s:else>
                                           onblur="calD56(<s:property value='%{#rowstatus.index}'/>)"
                                           />
                                </td>

                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D32" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D32" class="TEN_KH number" 
                                           id  ='D32<s:property  value="%{#rowstatus.index}" />'
                                           <s:if test="ngay3112.equalsIgnoreCase('1')">readonly </s:if>
                                           <s:else>style="background: #ffe6f2 !important;" </s:else>
                                               />
                                    </td>

                                    <td class="number5" id="D57<s:property value='%{#rowstatus.index}'/>">
                                    <s:property value="D57" /></td>

                                <td  align="center" class="TD_CHECKBOX">    
                                    <input type="checkbox" id ='idchk<s:property  value="%{#rowstatus.index}" />' class="checkboxdat TEN_KH" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D33" value="<s:property  value="D33"/>"                                            
                                           />
                                </td> 
                            </tr>                                                                                                                                                                                   
                        </s:iterator>
                    </table>        
                </div>
            </div>

            <sj:submit id="%{khoa_nhaptaycn}_save_htlai" name="%{khoa_nhaptaycn}_save_htlai" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>

        <script>

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
            function fncSetVal(obj, index) {
                var D28 = D30 = D31 = 0;
                D28 = $('#D28' + index).val();
                D30 = $('#D30' + index).val();
                D31 = $('#D31' + index).val();
//                 $('#D28' + index).val(0);
                $('#D30' + index).val(0);
                $('#D31' + index).val(0);
                $('#' + obj + index).val(D28);
            }

            function initTable()
            {
                var table = document.getElementById("tblTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    //cho combox 1
                    var matmp1 = getMabyNumber1(i); //                       
                    if (matmp1 === 1)
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
                $(".TD_MAKH").css({"width": "70px"});
                $(".TD_TOTIEN").css({"width": "90px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "100px"});
                $(".TD_SOKU").css({"width": "100px"});
                $(".TD_NGAY").css({"width": "40px"});
                $(".TD_CHITIEU").css({"width": "100px"});
                $(".TEN_KH").css({"width": "100%"});
                $("#idsaveDatatmp1").hide();
                $("#idsaveDatatmp2").show();
                initTable();
            });

            function calD56(index) {
                function unformat(n) {
                    return (n || "").toString().replace(/\./g, "");
                }

                function fmt(n) {
                    return n.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
                }

                // lấy giá trị
                let d52 = parseFloat(unformat($("#D52" + index).val())) || 0;
                let d31 = parseFloat(unformat($("#D31" + index).val())) || 0;

                let d55 = parseFloat(unformat($("#D55" + index).text())) || 0;
                // tính
                let d56 = d52 + d31;
                let d57 = d56 + d55;
                $("#D52" + index).val(fmt(d52));
                $("#D31" + index).val(fmt(d31));

                $("#D56" + index).text(fmt(d56));
                $("#D57" + index).text(fmt(d57));
            }

        </script>
    </body>
</html>

