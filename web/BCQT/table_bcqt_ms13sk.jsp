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
        <script type="text/javascript" src="js/pagination.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
//                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
//                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });

            function deleteRow(indx) {
                var table = document.getElementById("table12a");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
            }

            function addRow(indx) {
//                sleep(1000);
                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("table12a");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
                else
                {
                    max_row++;
                    rowCount = max_row;
                }
                var newTr = '<tr>\n\
                                <td ><input type="text" value="" id="D1" name="lstDulieuNt[' + rowCount + '].TEN" class="TEN_KH" onfocus="this.select();" />\n\
                                <input type="hidden" value="88888888" id="D2" name="lstDulieuNt[' + rowCount + '].D1" class="TEN_KH" onfocus="this.select();" </td>\n\
                                <td ><input type="text" value="" id="D3" name="lstDulieuNt[' + rowCount + '].D2" class="TEN_KH" onfocus="this.select();" /></td>\n\\n\
                                <td ><input type="text" value="" id="D4" name="lstDulieuNt[' + rowCount + '].D3" class="TEN_KH" onfocus="this.select();" /></td>\n\
                                <td ><input type="text" value="" id="D5" name="lstDulieuNt[' + rowCount + '].D4" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="0" id="D6" name="lstDulieuNt[' + rowCount + '].D5" class="TEN_KH number2" onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="0" id="D7" name="lstDulieuNt[' + rowCount + '].D6" class="TEN_KH number2" onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="0" id="D8" name="lstDulieuNt[' + rowCount + '].D7" class="TEN_KH number2" onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="0" id="D9" name="lstDulieuNt[' + rowCount + '].D8" class="TEN_KH number2" onfocus="this.select();"/></td>\n\
                                <td><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                $($('table#table12a tr')[index]).before(newTr);

//                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TEN_KH").css({"width": "100%"});
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.TEN_KH').focus(function () {
                    $(this).closest('tr').addClass('highlight_row');
                });
                $('.TEN_KH').blur(function () {
                    $(this).closest('tr').removeClass('highlight_row');
                });
//                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
            }
        </script>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_bcqt}" action="SAVE_%{khoa_bcqt}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                <s:if test="Grade.equalsIgnoreCase('1')">
                    SAO KÊ CÁC HỘ VAY  VỐN HSSV TRẢ NỢ TRƯỚC HẠN ĐƯỢC GIẢM LÃI
                </s:if>
                <s:else>
                    TỔNG HỢP SAO KÊ CÁC HỘ VAY HSSV TRẢ NỢ TRƯỚC HẠN
                </s:else>
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <s:if test="Grade.equalsIgnoreCase('1')">
                <table border="1" class="editDelete" id="table12a" align="center">
                    <tr>          
                        <th style="width: 50px;" class="TD_SOKU">Họ và tên Khách hàng</th>
                        <th style="width: 50px;" class="TD_SOKU">Số khế ước</th>
                        <th style="width: 50px;" class="TD_SOKU">Họ và tên tổ trưởng</th>
                        <th style="width: 20px;" class="TD_SOKU">Mã tổ</th>
                        <th style="width: 50px;" >Tổng số tiền gốc trả nợ trước hạn các món vay tất toán trong năm</th>
                        <th style="width: 50px;" class="TD_TEN_KH">Số tiền gốc trả nợ trước hạn trong năm các món vay mới ghi nhận giảm lãi</th>
                        <th style="width: 50px;" class="TD_SOKU">Số tiền giảm lãi đã hạch toán trong năm</th>
                        <th style="width: 50px;" class="TD_SOKU">Tổng số tiền giảm lãi mới ghi nhận trong năm</th>
                    </tr>
                    <s:iterator value="#attr.lstms13sk" var="modelView" status="rowstatus">
                        <tr>  
                            <td>
                                <input type="text" value="<s:property  value="TENKH" />" id="D1"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                                <input type="hidden" value="<s:property  value="ROWID" />" id="D2"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH" onfocus="this.select();"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="SOKU" />" id="D3"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="TEN_TOT" />" id="D4"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="MA_TOT" />" id="D5"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="SOTIEN_GLTTOAN" />" id="D6"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="SOTIEN_GLGNHAN" />" id="D7"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="LAIGIAM_TTOAN" />" id="D8"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="LAIGIAM_GHINHAN" />" id="D9"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <s:if test="TT_ROW.equalsIgnoreCase('I')">
                                <!--<td><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>-->
                            </s:if>

                        </tr>
                    </s:iterator>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>
                    </tr>
                </table>
                <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
            </s:if>

            <s:else>
                <table border="1" class="editDelete" id="table12a" align="center">
                    <tr>
                        <th rowspan="2" style="width: 50px;" class="TD_SOKU">Tên PGD</th>
                        <th colspan="2" style="width: 70px;" class="TD_SOKU">Tổng số món</th>
                        <th colspan="2" style="width: 70px;" class="TD_SOKU">Số tiền gốc trả nợ trước hạn</th>
                        <th colspan="2" style="width: 70px;" class="TD_SOKU">Số tiền giảm lãi</th>
                    </tr>
                    <tr>          
                        <th style="width: 50px;" class="TD_SOKU">Giảm lãi đã hạch toán</th>
                        <th style="width: 50px;" class="TD_SOKU">Giảm lãi mới ghi nhận</th>
                        <th style="width: 50px;" class="TD_SOKU">Các món vay đã tất toán (đã giảm lãi)</th>
                        <th style="width: 20px;" class="TD_SOKU">Các món vay đang ghi nhận giảm lãi</th>
                        <th style="width: 50px;" class="TD_SOKU">Đã hạch toán trong năm</th>
                        <th style="width: 50px;" class="TD_SOKU">Mới ghi nhận</th>
                    </tr>
                    <s:iterator value="#attr.lstms13sk" var="modelView" status="rowstatus">
                        <tr>  
                            <td>
                                <input type="text" value="<s:property  value="TENKH" />" id="D1"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="SOKU" />" id="D3"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH number2" onfocus="this.select();" readonly="true"/>
                            </td>

                            <td>
                                <input type="text" value="<s:property  value="MA_TOT" />" id="D5"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH number2" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="SOTIEN_GLTTOAN" />" id="D6"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number2" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="SOTIEN_GLGNHAN" />" id="D7"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number2" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="LAIGIAM_TTOAN" />" id="D8"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number2" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="LAIGIAM_GHINHAN" />" id="D9"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH number2" onfocus="this.select();" readonly="true"/>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
            </s:else>
        </s:form>
        <s:if test="Grade.equalsIgnoreCase('1')">
            <s:form action="%{khoa_bcqt}.action" id="paginationForm">
                <s:hidden name="khoa_bcqt"/>
                <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                    <input type="hidden" id="<s:property  value="sKey" />" 
                           name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
                </s:iterator>
                <%@ include file="/dcpt_no/pagination.jsp" %>
                <sj:submit value="submit" id="idSubmit" name="idSubmit" targets="divExportReport" cssStyle="display: none" 
                           onBeforeTopics="batdauloaddata" onCompleteTopics="hoanthanhloaddata"/>
            </s:form>
        </s:if>
    </body>
</html>
