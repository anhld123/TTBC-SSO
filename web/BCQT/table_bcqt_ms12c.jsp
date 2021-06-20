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
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TEN_KH").css({"width": "100%"});
            });

            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            function deleteRow(indx) {
                var table = document.getElementById("table12c");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
            }

            function addRow(indx) {
//                sleep(1000);
                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("table12c");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
                else
                {
                    max_row++;
                    rowCount = max_row;
                }
                var newTr = '<tr>\n\
                                <td class="TD_TEN_KH"><input type="text" value="" id="D1" name="lstDulieuNt[' + rowCount + '].TEN" class="TEN_KH" onfocus="this.select();" /></td>\n\
                                <td class="TD_TEN_KH"><input type="text" value="" id="D2" name="lstDulieuNt[' + rowCount + '].D1" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D3" name="lstDulieuNt[' + rowCount + '].D2" class="TEN_KH number2" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D4" name="lstDulieuNt[' + rowCount + '].D3" class="TEN_KH number2" onfocus="this.select();"/></td>\n\\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D5" name="lstDulieuNt[' + rowCount + '].D4" class="TEN_KH number2" onfocus="this.select();"/></td>\n\
                                <td><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                $($('table#table12c tr')[index]).before(newTr);

                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TEN_KH").css({"width": "100%"});
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
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
        <s:form id="id_sv_%{khoa_bcqt}" action="SAVE_%{khoa_bcqt}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                BẢNG KÊ CHI TIẾT CÁC KHOẢN VAY HỖ TRỢ LÃI SUẤT SAI QUY ĐỊNH PHẢI THU HỒI SAU QUYẾT TOÁN HTLS
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="table12c" align="center">
                <tr>
                    <th style="width: 60px;" class="TD_TEN_KH">Họ và tên</th>
                    <th style="width: 50px;" class="TD_TEN_KH">Mã món vay</th>
                    <th style="width: 50px;" class="TD_TEN_KH">Dư nợ được hỗ trợ lãi suất</th>
                    <th style="width: 50px;" class="TD_TEN_KH">Số lãi tiền vay hỗ trợ sai quy định phải giảm trừ</th>
                    <th style="width: 50px;" class="TD_TEN_KH">Số lãi tiền vay hỗ trợ sai quy định đã thu hồi </th>
                    <th style="width: 50px;" class="TD_TEN_KH">Thêm/Xóa</th>
                </tr>
                <s:iterator value="#attr.lstms12c" var="modelView" status="rowstatus">
                    <tr>  
                        <td>
                            <input type="text" value="<s:property  value="TENKH" />" id="D1"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="SOKU" />" id="D2"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right">
                            <input type="text" value="<s:property  value="D1" />" id="D3"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH number2" onfocus="this.select();"/>
                        </td>
                        <td align = "right">
                            <input type="text" value="<s:property  value="D2" />" id="D4"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH number2" onfocus="this.select();"/>
                        </td>
                        <td align = "right">
                            <input type="text" value="<s:property  value="D3" />" id="D5"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH number2" onfocus="this.select();"/>
                        </td>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <td><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>
                            </s:if>
                            <s:else><td></td></s:else>
                        </tr>
                </s:iterator>
                <s:if test="Grade.equalsIgnoreCase('1')">
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>
                    </tr>
                </s:if>
            </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
    </body>
</html>
