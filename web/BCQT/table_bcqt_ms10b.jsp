<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="js/new_js_bcqt.js"></script>
        <link rel="stylesheet" type="text/css"  href="css/new_css_bcqt.css" />
        <script>
            var max_row = 0;
            function deleteRow(indx) {
                var table = document.getElementById("tblmain");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
                table.deleteRow(indx);
            }
            function addRow(indx) {
                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("tblmain");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
                else
                {
                    max_row++;
                    rowCount = max_row;
                }
                var newTr = '<tr>\n\
                                <td><input type="text" value="" id="D2" name="lstDulieuNt[' + rowCount + '].D2" \n\
                                class="css_text" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="outme(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td style="background-color: #f0f0f0"><input type="text" value="0" id="D3" name="lstDulieuNt[' + rowCount + '].D3" \n\
                                class="css_text number"  readonly="readonly"\n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="outme(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="0" id="D4" name="lstDulieuNt[' + rowCount + '].D4" \n\
                                class="css_text number" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="0" id="D5" name="lstDulieuNt[' + rowCount + '].D5" \n\
                                class="css_text number" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="0" id="D6" name="lstDulieuNt[' + rowCount + '].D6" \n\
                                class="css_text number" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td style="background-color: #f0f0f0"><input type="text" value="0" id="D7" name="lstDulieuNt[' + rowCount + '].D7" \n\
                                class="css_text number"  readonly="readonly"\n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="outme(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="0" id="D8" name="lstDulieuNt[' + rowCount + '].D8" \n\
                                class="css_text number" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="0" id="D9" name="lstDulieuNt[' + rowCount + '].D9" \n\
                                class="css_text number" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="0" id="D10" name="lstDulieuNt[' + rowCount + '].D10" \n\
                                class="css_text number" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="0" id="D11" name="lstDulieuNt[' + rowCount + '].D11" \n\
                                class="css_text number" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="outme(this.parentNode.parentNode.rowIndex);"/></td>\n\
                                <td><input type="text" value="" id="D12" name="lstDulieuNt[' + rowCount + '].D12" \n\
                                class="css_text" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="outme(this.parentNode.parentNode.rowIndex);"/></td>\n\
                                <td><input type="button" class="css_themxoa" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)"/></td>\n\
                            </tr>';
                $($('table#tblmain tr')[index]).before(newTr);
                $('.number').number(true, 0);
                $('.number2').number(true, 2);
            }
        </script>
        <script>
            function autoEvaluate(inde) {
                //Định dang dòng đang được chọn
                outme(inde);
                // D3 = D4 + D5 + D6
                $("[id=D3]").eq(inde - 5).val(parseFloat($("[id=D4]").eq(inde - 5).val()) + parseFloat($("[id=D5]").eq(inde - 5).val()) + parseFloat($("[id=D6]").eq(inde - 5).val()));
                // D7 = D8 + D9 + D10
                $("[id=D7]").eq(inde - 5).val(parseFloat($("[id=D8]").eq(inde - 5).val()) + parseFloat($("[id=D9]").eq(inde - 5).val()) + parseFloat($("[id=D10]").eq(inde - 5).val()));
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
                BÁO CÁO QUYẾT TOÁN  VỐN ĐẦU TƯ XDCB
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="tblmain" id="tblmain" name="tblmain">
                <tr>
                    <th rowspan="4">Tên công trình</th>
                    <th colspan="4">Kế hoạch vốn được thông báo</th>
                    <th colspan="4">Quyết toán đã phê duyệt</th>
                    <th rowspan="4">Giá trị<br>XDCB dở dang<br>đã thanh toán</th>
                    <th rowspan="4">Ghi chú</th>
                        <% if (session.getAttribute("reportGrade").equals("1")) {%>
                    <th rowspan="4">Thêm/Xóa</th>
                        <%}%>
                </tr>
                <tr>
                    <th rowspan="3">Tổng số</th>
                    <th colspan="3">Trong đó</th>
                    <th rowspan="3">Tổng số </th>
                    <th colspan="3">Trong đó</th>
                </tr>
                <tr>
                    <th rowspan="2">Địa phương</th>
                    <th colspan="2">Trung ương</th>
                    <th rowspan="2">Địa phương</th>
                    <th colspan="2">Trung ương</th>
                </tr>
                <tr>
                    <th>NSNN</th>
                    <th>Điều lệ hoặc khấu hao</th>
                    <th>NSNN</th>
                    <th>Điều lệ hoặc khấu hao</th>
                </tr>
                <tr>         
                    <th class="css_25" name="head">2</th>
                    <th class="css_8" name="head">3</th>
                    <th class="css_6" name="head">4</th>
                    <th class="css_6" name="head">5</th>
                    <th class="css_6" name="head">6</th>
                    <th class="css_8" name="head">7</th>
                    <th class="css_6" name="head">8</th>
                    <th class="css_6" name="head">9</th>
                    <th class="css_6" name="head">10</th>
                    <th class="css_9" name="head">11</th>
                    <th class="css_9" name="head">12</th>
                        <% if (session.getAttribute("reportGrade").equals("1")) {%>
                    <th class="css_5" name="head">13</th>
                        <%}%>
                </tr>

                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr> 
                        <td>
                            <input type="text" value="<s:property  value="D2" />" id="D2"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="outme(this.parentNode.parentNode.rowIndex);"
                                   class="css_text"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2"/>
                        </td>
                        <td style="background-color: #f0f0f0">
                            <input type="text" value="<s:property  value="D3" />" id="D3"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="outme(this.parentNode.parentNode.rowIndex);"
                                   class="css_text number"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" readonly="readonly"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D4" />" id="D4"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);"
                                   class="css_text number"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D5" />" id="D5"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);"
                                   class="css_text number"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D6" />" id="D6"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);"
                                   class="css_text number"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6"/>
                        </td>
                        <td style="background-color: #f0f0f0">
                            <input type="text" value="<s:property  value="D7" />" id="D7"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="outme(this.parentNode.parentNode.rowIndex);"
                                   class="css_text number"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" readonly="readonly"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D8" />" id="D8"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);"
                                   class="css_text number"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D9" />" id="D9"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);"
                                   class="css_text number"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"
                                   />
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D10" />" id="D10"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);"
                                   class="css_text number"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D11" />" id="D11"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="outme(this.parentNode.parentNode.rowIndex);"
                                   class="css_text number"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11"/>
                        </td>                      
                        <td>
                            <s:if test="D12 == 0">
                                <input type="text" value="" id="D12"
                                       onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                       onblur="outme(this.parentNode.parentNode.rowIndex);"
                                       class="css_text"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                            </s:if>
                            <s:else>
                                <input type="text" value="<s:property  value="D12" />" id="D12"
                                       onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                       onblur="outme(this.parentNode.parentNode.rowIndex);"
                                       class="css_text"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                            </s:else>
                        </td>
                        <% if (session.getAttribute("reportGrade").equals("1")) {%>
                        <td><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="css_themxoa"/></td>
                            <%}%>
                    </tr>
                </s:iterator>
                <% if (session.getAttribute("reportGrade").equals("1")) {%>
                <tr style="background-color: #dcdcdc">
                    <td colspan="11">Nhấn <b>"Thêm"</b> để thêm dòng mới.</td>
                    <td><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="css_themxoa"/></td>
                </tr>
                <%}%>
            </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <% if (!session.getAttribute("reportGrade").equals("1")) {%>
        <script>
            $.each($('form').serializeArray(), function (index, value) {
                $('[name="' + value.name + '"]').attr('readonly', 'readonly');
            });
        </script>
        <%}%>
    </body>
</html>
