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
            function autoEvaluate(inde) {
                //Định dang dòng đang được chọn
                outme(inde);
                // 11 = 9 + 10
                var D7, D6;
                if (isNaN(parseFloat($("[id=D7]").eq(inde - 4).val()))) {
                    D7 = 0;
                } else {
                    D7 = parseFloat($("[id=D7]").eq(inde - 4).val())
                }
                if (isNaN(parseFloat($("[id=D6]").eq(inde - 4).val()))) {
                    D6 = 0;
                } else {
                    D6 = parseFloat($("[id=D6]").eq(inde - 4).val())
                }
                $("[id=D8]").eq(inde - 4).val(D7 + D6);
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
                BÁO CÁO QUYẾT TOÁN MUA SẮM TÀI SẢN CỐ ĐỊNH
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="tblmain" id="tblmain" name="tblmain">
                <tr>
                    <th rowspan="3">Tên tài sản cố định</th>
                    <th rowspan="3">Mã nhóm TSCĐ</th>
                    <th colspan="3">Kế hoạch vốn được thông báo</th>
                    <th colspan="4">Quyết toán đã phê duyệt</th>
                    <th rowspan="3">Ghi chú</th>
                </tr>
                <tr>
                    <th rowspan="2">Số lượng</th>
                    <th colspan="2">Thành tiền</th>
                    <th rowspan="2">Số lượng</th>
                    <th colspan="3">Thành tiền</th>
                </tr>
                <tr>
                    <th>Vốn TW</th>
                    <th>Vốn ĐP</th>
                    <th>Vốn TW</th>
                    <th>Vốn ĐP</th>
                    <th>Tổng cộng</th>
                </tr>
                <tr style="font-style: italic;">         
                    <th class="css_20" name="head">2</th>
                    <th class="css_8" name="head">3</th>
                    <th class="css_5" name="head">4</th>
                    <th class="css_10" name="head">5</th>
                    <th class="css_10" name="head">6</th>
                    <th class="css_5" name="head">7</th>
                    <th class="css_10" name="head">8</th>
                    <th class="css_10" name="head">9</th>
                    <th class="css_10" name="head">10=8+9</th>
                    <th class="css_20" name="head">11</th>
                </tr>

                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr> 
                        <td style="background-color: #f0f0f0">
                            <input type="text" value="<s:property  value="TEN" />" id="TEN" 
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="outme(this.parentNode.parentNode.rowIndex);"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="css_text" readonly="readonly"/>
                        </td>
                        <td style="background-color: #f0f0f0">
                            <input type="text" value="<s:property  value="MA" />" id="MA"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="outme(this.parentNode.parentNode.rowIndex);"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" class="css_text" readonly="readonly" style="text-align: center"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D2" />" id="D2"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="outme(this.parentNode.parentNode.rowIndex);"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="css_text number"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D3" />" id="D3"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="outme(this.parentNode.parentNode.rowIndex);"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="css_text number"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D4" />" id="D4"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="outme(this.parentNode.parentNode.rowIndex);"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="css_text number"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D5" />" id="D5"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="outme(this.parentNode.parentNode.rowIndex);"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="css_text number"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D6" />" id="D6"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="css_text number"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D7" />" id="D7" 
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="css_text number"/>
                        </td>
                        <td style="background-color: #f0f0f0">
                            <input type="text" value="<s:property  value="D8" />" id="D8" 
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="outme(this.parentNode.parentNode.rowIndex);"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="css_text number" readonly="readonly"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D9" />" id="D9"
                                   onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                   onblur="outme(this.parentNode.parentNode.rowIndex);"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="css_text"/>
                        </td>
                    </tr>
                </s:iterator>
            </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </script>
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
