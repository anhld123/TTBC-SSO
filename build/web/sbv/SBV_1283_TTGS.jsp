<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
//                $('.datepicker').each(function(){
//                    $(this).datepicker();
//                });
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_TEN_KH").css({"width": "80px"});
                $(".TD_CHUCVU").css({"width": "150px"});
                $(".TD_SOTIEN").css({"width": "60px"});
                $(".TD_THEMXOA").css({"width": "10px"});
                $(".TEN_KH").css({"width": "100%"});
            });

            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            function deleteRow(indx) {
                var table = document.getElementById("table1283");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
            }

            function addRow(indx) {
//                sleep(1000);
                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("table1283");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
                else
                {
                    max_row++;
                    rowCount = max_row;
                }
                var newTr = '<tr>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="" id="D1" name="lstDulieuNt[' + rowCount + '].D1" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_CHUCVU"><input type="text" value="" id="D2" name="lstDulieuNt[' + rowCount + '].D2" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="" id="D3" name="lstDulieuNt[' + rowCount + '].D3" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_SOTIEN"><input type="text" value="0" id="D4" name="lstDulieuNt[' + rowCount + '].D4" class="number TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_SOTIEN"><input type="text" value="0" id="D5" name="lstDulieuNt[' + rowCount + '].D5" class="D5 number TEN_KH" onfocus="this.select();" onblur="autoEvaluate()"/></td>\n\
                                <td align = "right" class="TD_SOTIEN"><input type="text" value="0" id="D6" name="lstDulieuNt[' + rowCount + '].D6" class="number TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_SOTIEN"><input type="text" value="0" id="D7" name="lstDulieuNt[' + rowCount + '].D7" class="number TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_SOTIEN"><input type="text" value="0" id="D8" name="lstDulieuNt[' + rowCount + '].D8" class="D8 number TEN_KH" onfocus="this.select();" onblur="autoEvaluate()"/></td>\n\
                                <td align = "right" class="TD_SOTIEN"><input type="text" value="0" id="D9" name="lstDulieuNt[' + rowCount + '].D9" class="number TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_SOTIEN"><input type="text" value="0" id="D10" name="lstDulieuNt[' + rowCount + '].D10" class="number TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_SOTIEN"><input type="text" value="0" id="D11" name="lstDulieuNt[' + rowCount + '].D11" class="number TEN_KH" onfocus="this.select();"/></td>\n\
\n\                             <td align = "right" class="TD_SOTIEN"><input type="text" value="0" id="D12" name="lstDulieuNt[' + rowCount + '].D12" class="number TEN_KH" onfocus="this.select();"/></td>\n\
                                <td class="TD_THEMXOA"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                $($('table#table1283 tr')[index]).before(newTr);
                $('.D0').css({"text-align": "center"});
                $(".TD_TEN_KH").css({"width": "80px"});
                $(".TD_CHUCVU").css({"width": "150px"});
                $(".TD_SOTIEN").css({"width": "60px"});
                $(".TD_THEMXOA").css({"width": "10px"});
                $(".TEN_KH").css({"width": "100%"});
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('.TEN_KH').focus(function () {
                    $(this).closest('tr').addClass('highlight_row');
                });
                $('.TEN_KH').blur(function () {
                    $(this).closest('tr').removeClass('highlight_row');
                });
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
            }
        </script>

    </head>
    <body>
        <s:form id="id_sv_%{khoa_sbv}" action="SAVE_%{khoa_sbv}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:iterator value="poscd" status="row">
                <s:hidden name="poscd[%{#row.index}]" />
            </s:iterator>
            <div id="divTitle">
                BÁO CÁO TÌNH HÌNH XỬ LÝ TÀI SẢN BẢO ĐẢM 
            </div>
            <s:hidden name="khoa_sbv"/>
            <div id="divDonvitinh">
                Đơn vị tính: Triệu VNĐ
            </div>
            <table border="1" class="editDelete" id="table1283" align="center">
                <tr>
                    <th rowspan="2"  class="TD_SOTIEN">Mã CIF khách hàng</th>
                    <th rowspan="2"  class="TD_CHUCVU">Tên khách hàng</th>
                    <th rowspan="2"  class="TD_SOTIEN">MST/CMND</th>
                    <th rowspan="2"  class="TD_SOTIEN">Dư nợ tại thời điểm  bàn giao TSBĐ cho TCTD</th>
                    <th rowspan="2"  class="TD_SOTIEN">Lãi chưa thu đến thời điểm bàn giao TSBĐ cho TCTD</th>
                    <th colspan="6"  class="TD_SOTIEN">Trong tháng báo cáo</th>
                    <th rowspan="2"  class="TD_SOTIEN">Số dư nợ/ số tiền còn phải thu hồi của bên đi vay sau khi xử lý TSBĐ</th>
                    <s:if test="Grade.equalsIgnoreCase('1')">
                    <th rowspan="2"  class="TD_THEMXOA">Thêm/Xóa</th>
                    </s:if>
                    
                    
                </tr>
                <tr>
                    <th  class="TD_SOTIEN">Giá trị TSBĐ  được định giá tại thời điểm bàn giao cho TCTD</th>
                    <th  class="TD_SOTIEN">Giá trị thu hồi được từ thanh lý TSBĐ</th>
                    <th  class="TD_SOTIEN">Số tiền thanh lý thu được</th>
                    <th  class="TD_SOTIEN">Số tiền hạch toán thu nợ gốc</th>
                    <th  class="TD_SOTIEN">Số tiền hạch toán thu nợ lãi</th>
                    <th  class="TD_SOTIEN">Số tiền trả lại cho bên bảo đảm</th>
                </tr>   
                    
                    
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr> 
                        <td align = "right" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="D1" />" id="D1"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D2" />" id="D2"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="D3" />" id="D3"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                                                
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D4" />" id="D4"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="number TEN_KH" onfocus="this.select();"/>
                        </td>
                        
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D5" />" id="D5"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D6" />" id="D6"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D7" />" id="D7"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="number TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D8" />" id="D8"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="number TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D9" />" id="D9"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="number TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D10" />" id="D10"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D11" />" id="D11"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="number TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D12" />" id="D12"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number TEN_KH" onfocus="this.select();"/>
                        </td>
                   
                            <s:if test="Grade.equalsIgnoreCase('1')">
                            <td class="TD_THEMXOA"><input type="button" value="   Xóa    " onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>
                            </s:if>
                      
                        
                        
                    </tr>
                </s:iterator>
                <s:if test="Grade.equalsIgnoreCase('1')">
                <tr>
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
                    

                        <td class="TD_THEMXOA"><input type="button" value="  Thêm  " onclick="addRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>
                    
                    
                </tr>
                </s:if>
        </table>
        <sj:submit id="%{khoa_sbv}_save" name="%{khoa_sbv}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                   onCompleteTopics="completediv_ss" cssStyle="display: none"/>
    </s:form>
</body>
</html>
