<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
        <link href="quanly_nokhoanh/css-quanly-nokhoanh.css" type="text/css" rel="stylesheet" />
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var popWindow;
            var max_row = 0;
            $(document).ready(function () {
                initTable();
            });
            $(document).ready(function () {
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                
                $('.number').number(true, 0);
                $('.number2').number(true, 0);
                
                // Căn chỉnh lại độ rộng các cột cho đồng bộ
                $(".STT1").css({"width": "40px"});
                $(".STT2").css({"width": "90px"});
                $(".STT3").css({"width": "120px"});
                $(".STT4").css({"width": "150px"});
                $(".STT5").css({"width": "110px"});
                $(".STT6").css({"width": "85px"});
            });
            
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });

            function initTable() {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++) {
                    try {
                        document.getElementById("D26_" + i).value = "1";
                        document.getElementById("D8_" + i).disabled = true;
                        document.getElementById("D9_" + i).disabled = true;
                        document.getElementById("D27_" + i).disabled = true;
                        document.getElementById("D10_" + i).disabled = true;
                        document.getElementById("D11_" + i).disabled = true;
                        document.getElementById("D12_" + i).disabled = true;
                        document.getElementById("D29_" + i).disabled = true;
                        document.getElementById("D14_" + i).disabled = true;
                        document.getElementById("D15_" + i).disabled = true;
                        document.getElementById("D28_" + i).disabled = true;
                        document.getElementById("D16_" + i).disabled = true;
                        document.getElementById("D17_" + i).disabled = true;
                    } catch (e) {
                    }
                }
            }
        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw; height: 40vw;">            
            <div id="divTitle">
                DANH SÁCH MÓN KHOANH NỢ ĐÃ PHÊ DUYỆT
            </div>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="subTable" align="center" style="width: 95vw; border-collapse: collapse;">                
                <thead>
                    <tr>    
                        <th rowspan="2" class="D0 STT1">
                            <input type="checkbox" id="select-all"/>
                        </th>    
                        <th rowspan="2" class="STT1">S<br>T<br>T</th>                        
                        <th rowspan="2" class="STT4">Họ và tên</th>  
                        <th rowspan="2" class="STT2">Mã món vay</th>  
                        <th colspan="5">PHẦN THEO DÕI TẠI NGÂN HÀNG</th>
                    </tr>    
                    <tr>
                        <th class="STT5">Dư nợ gốc</th>  
                        <th class="STT5">Dư gốc khoanh</th>    
                        <th class="STT5">Số tiền lãi <br>còn nợ NH</th>                            
                        <th class="STT5">Ngày <br>bắt đầu khoanh nợ</th>    
                        <th class="STT5">Ngày <br>hết hạn khoanh nợ</th>
                    </tr>
                    <tr style="font-style: italic;">
                        <th style="color: #000; font-style: italic; font-size: xx-small;"></th>      
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(8)</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                        <tr id="tablefix">    
                            <td class="D0">
                                <input id="check<s:property value='%{#rowstatus.index}' />" type="checkbox" class="myCheckBox sstyle"
                                       name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D18"/>        
                            </td>

                            <td class="D0 STT1 sstyle"> 
                                <s:property value="%{#rowstatus.index + 1}" />  
                                <input type="hidden" value="<s:property value="MACN" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].MACN"/>                            
                                <input type="hidden" value="<s:property value="THUTU" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].THUTU"/>                            
                                <input type="hidden" value="<s:property value="MA" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].MA"/>
                                <input type="hidden" value="<s:property value="D19" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D19" id="D19_<s:property value='%{#rowstatus.index}' />"/>
                                <input type="hidden" value="<s:property value="D20" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D20"/>
                                <input type="hidden" value="<s:property value="D21" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D21"/>
                                <input type="hidden" value="<s:property value="D22" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D22"/>
                                <input type="hidden" value="<s:property value="D23" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D23"/>
                                <input type="hidden" value="<s:property value="D24" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D24"/>
                                <input type="hidden" value="<s:property value="D25" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D25"/>
                                <input type="hidden" value="<s:property value="D27" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D27"/>
                                <input type="hidden" value="<s:property value="D28" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D28"/>
                                <input type="hidden" value="<s:property value="D29" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D29"/>
                                <input type="hidden" value="<s:property value="D30" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D30"/>
                                <input type="hidden" value="<s:property value="D8" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D8"/>
                                <input type="hidden" value="<s:property value="D9" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D9"/>
                                <input type="hidden" value="<s:property value="D10" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D10"/>
                                <input type="hidden" value="<s:property value="D11" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D11"/>
                                <input type="hidden" value="<s:property value="D12" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D12"/>
                                <input type="hidden" value="<s:property value="D13" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D13"/>
                                <input type="hidden" value="<s:property value="D14" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D14"/>
                                <input type="hidden" value="<s:property value="D15" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D15"/>
                                <input type="hidden" value="<s:property value="D16" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D16"/>
                                <input type="hidden" value="<s:property value="D17" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D17"/>
                                <input type="hidden" value="<s:property value="NGAYBC" />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].NGAYBC"/>
                            </td>
                            
                            <td> 
                                <s:property value="D1" />            
                                <input type="hidden" value="<s:property value="D1" />" readonly="true"
                                       id="D1_<s:property value='%{#rowstatus.index}' />" 
                                       name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D1" class="SOKU sstyle"/>
                            </td>                                
                            
                            <td> 
                                <s:property value="D2" />    
                                <input type="hidden" value="<s:property value="D2" />" readonly="true"
                                       id="D2_<s:property value='%{#rowstatus.index}' />"
                                       name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D2" class="D0 sstyle"/>
                            </td>                                
                            
                            <td>
                                <input type="text" value="<s:property value="D3" />" readonly="true"
                                       id="D3_<s:property value='%{#rowstatus.index}' />"
                                       style="width: 100%; background: #ddd; box-sizing: border-box;"
                                       name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D3" class="number sstyle"/>
                            </td>                                                                                               
                            
                            <td>
                                <input type="text" value="<s:property value="D4" />" readonly="true" 
                                       id="D4_<s:property value='%{#rowstatus.index}' />"
                                       style="width: 100%; background: #ddd; box-sizing: border-box;"
                                       name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D4" class="number sstyle"/>
                            </td>
                            
                            <td>
                                <input type="text" value="<s:property value="D5" />" readonly="true" 
                                       id="D5_<s:property value='%{#rowstatus.index}' />"
                                       style="width: 100%; background: #ddd; box-sizing: border-box;"
                                       name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D5" class="number sstyle"/>
                            </td>
                            
                            <td class="D0">
                                <input type="text" value="<s:property value="D6" />" readonly="true" 
                                       id="D6_<s:property value='%{#rowstatus.index}' />" 
                                       style="width: 100%; background: #ddd; box-sizing: border-box;"
                                       name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D6" class="D0 sstyle"/>
                            </td>
                            
                            <td class="D0">
                                <input type="text" value="<s:property value="D7" />" readonly="true" 
                                       id="D7_<s:property value='%{#rowstatus.index}' />"
                                       style="width: 100%; background: #ddd; box-sizing: border-box;"
                                       name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D7" class="D0 sstyle"/>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>
        </div>
        <div id="luu_thanhcong"></div>
        <script>
            $(function () {
                $('#select-all').click(function (event) {
                    $('.myCheckBox').each(function () {
                        if (!this.disabled) {
                            this.checked = $('#select-all').prop('checked');
                            this.value = this.checked ? '1' : '0';
                        }
                    });
                });
            });
        </script>
    </body>
</html>