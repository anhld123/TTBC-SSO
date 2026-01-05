<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
                autoEvaluate(indx);
            }
            function addRow(indx) {
                var index = parseInt(indx);
                var table = document.getElementById("tblmain");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                var LisYN = ["[id=D10]"];
                var Listt = ["[id=THUTU]"];
                var gtcap = "";
                var gttt = "";
                for (m = 0; m < LisYN.length; m++) {
                    gtcap = $(LisYN[m]).eq(index - 2).val() + 1;
                    gttt = parseInt($(Listt[m]).eq(index - 2).val()) + 1;
                }
                var newTr = '<tr>\n\
                                <td><input type="text" value="-" id="TT_HIENTHI" name="lstDulieuNt[' + rowCount + '].TT_HIENTHI" \n\
                                class="css_text" readonly="readonly" style="text-align: center;"\n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="outme(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="" id="TEN" name="lstDulieuNt[' + rowCount + '].TEN" \n\
                                class="css_text"\n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="outme(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="' + rand(12).toUpperCase() + '" id="D2" name="lstDulieuNt[' + rowCount + '].D2" \n\
                                class="css_text" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="outme(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="0" id="D5" name="lstDulieuNt[' + rowCount + '].D5" \n\
                                class="css_text number" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="0" id="D6" name="lstDulieuNt[' + rowCount + '].D6" \n\
                                class="css_text number"\n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="0" id="D7" name="lstDulieuNt[' + rowCount + '].D7" \n\
                                class="css_text number" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="autoEvaluate(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <td><input type="text" value="" id="D8" name="lstDulieuNt[' + rowCount + '].D8" \n\
                                class="css_text number" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="outme(this.parentNode.parentNode.rowIndex);" /></td>\n\
                                <input type="hidden" value="N" id="D9" name="lstDulieuNt[' + rowCount + '].D9" \n\
                                class="css_text" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="outme(this.parentNode.parentNode.rowIndex);" />\n\
                                <input type="hidden" value="' + gtcap + '" id="D10" name="lstDulieuNt[' + rowCount + '].D10" \n\
                                class="css_text" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="outme(this.parentNode.parentNode.rowIndex);"/>\n\
                                <input type="hidden" value="' + gttt + '" id="THUTU" name="lstDulieuNt[' + rowCount + '].THUTU" \n\
                                class="css_text" \n\
                                onfocus="clickme(this.parentNode.parentNode.rowIndex);" \n\
                                onblur="outme(this.parentNode.parentNode.rowIndex);"/>\n\
                                <input type="hidden" value="D11" id="D11" name="lstDulieuNt[' + rowCount + '].D11" \n\
                                class="css_text" ></td>\n\
                                <td><input type="button" class="css_themxoa" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)"/></td>\n\
                            </tr>';
                $($('table#tblmain tr')[index + 1]).before(newTr);
                $('.number').number(true, 0);
                $('.number2').number(true, 2);
            }
            function rand(length, current) {
                current = current ? current : '';
                return length ? rand(--length, "0123456789ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz".charAt(Math.floor(Math.random() * 60)) + current) : current;
            }
        </script>
        <script>
            function autoEvaluate(inde) {
                //Định dang dòng đang được chọn
                outme(inde);
                // Mr.Vinh lam hàm cộng cấp
                //Định dang dòng đang được chọn
                var sumtotal = 0;
                var LisYN = ["[id=D9]"];
                var LisCAP = ["[id=D10]"];
                var LisVAL = ["[id=D5]", "[id=D6]", "[id=D7]"];

                for (i = 0; i < LisYN.length; i++) {
                    for (j = 0; j < $(LisYN[i]).size(); j++) {
                        var Mcap = "";
                        if ($(LisYN[i]).eq(j).val().trim() === "Y") {
                            Mcap = $(LisCAP[i]).eq(j).val();
                        }
                        if (Mcap.length > 0) {
                            for (m = 0; m < LisVAL.length; m++) {
                                sumtotal = 0;
                                for (k = 0; k < $(LisYN[i]).size(); k++) {
                                    if ($(LisYN[i]).eq(k).val().trim() === "N" && $(LisCAP[i]).eq(k).val().trim().substr(0, Mcap.length) === Mcap) {
                                        sumtotal += parseFloat($(LisVAL[m]).eq(k).val());
                                    }
                                }
                                $(LisVAL[m]).eq(j).val(sumtotal);
                            }
                        }
                    }
                    ;
                }
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
                BÁO CÁO QUYẾT TOÁN SỬA CHỮA TÀI SẢN CỐ ĐỊNH
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="tblmain" id="tblmain">
                <tr>
                    <th>Stt</th>
                    <th>Tài sản cố định</th>
                    <th>Mã TSCĐ</th>
                    <th>Dự toán được duyệt</th>  
                    <th>Chi phí sửa chữa lớn</th>
                    <th>Chi phí sửa chữa thường xuyên</th>   
                    <th>Ghi chú</th>    
					<% if (session.getAttribute("reportGrade").equals("1")) {%>
                    <th>Chức năng</th> 
					<%}%>
                </tr>

                <tr style="font-style: italic;">     
                    <th class="css_3" name="head">1</th>
                    <th class="css_30" name="head">2</th>
                    <th class="css_13" name="head">3</th>
                    <th class="css_13" name="head">4</th>
                    <th class="css_13" name="head">5</th>
                    <th class="css_13" name="head">6</th>
                    <th class="css_14" name="head">7</th>
					<% if (session.getAttribute("reportGrade").equals("1")) {%>
                    <th class="css_14" name="head">8</th>
					<%}%>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr class="thread">  
                            <td>    
                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" readonly="readonly" 
                                       class="css_text title" style="text-align: center;"
                                       />    
                            </td>
                            <td>    
                                <input type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly="readonly" 
                                       class="css_text title"
                                       />          
                            </td>
                            <td style="background-color: #f0f0f0">    

                                <s:if test="D2 == 0">
                                    <input type="text" value=""
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>"
                                           class="css_text title"
                                           />       
                                </s:if>
                                <s:else>
                                    <input type="text" value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>"
                                           class="css_text title"
                                           /> 
                                </s:else>
                            </td>   
                            <td>
                                <input type="text" value="<s:property  value="D5" />"  ID="D5"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"
                                       class="css_text title number"
                                       readonly="readonly"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="D6" />"  ID="D6"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6"
                                       class="css_text title number"
                                       readonly="readonly"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="D7" />"  ID="D7"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                       class="css_text title number"
                                       readonly="readonly"/>
                            </td>
                            <td>
                                <input type="text" value=""  ID="D8"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"
                                       class="css_text title"
                                       readonly="readonly"/>
                                <input type="hidden" value="<s:property  value="D9" />"  ID="D9"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"
                                       class="css_text"
                                       />
                                <input type="hidden" value="<s:property  value="D10" />"  ID="D10"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10"
                                       class="css_text"
                                       />
                                <input type="hidden" value="<s:property  value="THUTU" />"  ID="THUTU"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"
                                       class="css_text"
                                       />
                                <input type="hidden" value="<s:property  value="D11" />"  ID="D11"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11"
                                       class="css_text"
                                       />
                                <% if (session.getAttribute("reportGrade").equals("1")) {%>
                                <s:if test="TT_HIENTHI.equalsIgnoreCase('i') || TT_HIENTHI.equalsIgnoreCase('1')">   
                                <td><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="css_themxoa"/></td>
                                </s:if>
                                <s:else>
                                <td>&nbsp;</td>
                            </s:else>
                            <%}%>
                        </tr>
                    </s:if>
                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr height="22">  
                            <td>    
                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" readonly="readonly" 
                                       class="css_text subtitle" style="text-align: center;"
                                       />          
                            </td>
                            <td>    
                                <input type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" 
                                       class="css_text subtitle"
                                       readonly="readonly" />          
                            </td>
                            <td style="background-color: #f0f0f0">    
                                <s:if test="D2 == 0">
                                    <input type="text" value="" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                           class="css_text"
<!--                                           readonly="readonly" -->
                                           />      
                                </s:if>
                                <s:else>
                                    <input type="text" value="<s:property  value="D2" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                           class="css_text"
<!--                                           readonly="readonly" -->
                                           />
                                </s:else>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="D5" />"  ID="D5"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"
                                       onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                       class="css_text number"
                                       onblur="autoEvaluate(this.parentNode.parentNode.rowIndex)"
                                       />
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="D6" />"  ID="D6"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6"
                                       onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                       class="css_text number"
                                       onblur="autoEvaluate(this.parentNode.parentNode.rowIndex)"
                                       />
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="D7" />"  ID="D7"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7"
                                       onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                       class="css_text number"
                                       onblur="autoEvaluate(this.parentNode.parentNode.rowIndex)"
                                       />
                            </td>
                            <td>
                                <s:if test="D8 == 0">
                                    <input type="text" value=""  ID="D8"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"
                                           onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                           class="css_text"
                                           onblur="autoEvaluate(this.parentNode.parentNode.rowIndex)"
                                           />
                                </s:if>
                                <s:else>
                                    <input type="text" value="<s:property  value="D8" />"  ID="D8"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"
                                           onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                           class="css_text"
                                           onblur="autoEvaluate(this.parentNode.parentNode.rowIndex)"
                                           />
                                </s:else>
                                <input type="hidden" value="<s:property  value="D9" />"  ID="D9"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"
                                       onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                       class="css_text"
                                       onblur="autoEvaluate(this.parentNode.parentNode.rowIndex)"
                                       />
                                <input type="hidden" value="<s:property  value="D10" />"  ID="D10"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10"
                                       onfocus="clickme(this.parentNode.parentNode.rowIndex);"
                                       class="css_text"
                                       onblur="autoEvaluate(this.parentNode.parentNode.rowIndex)"
                                       />
                                <input type="hidden" value="<s:property  value="THUTU" />"  ID="THUTU"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"
                                       class="css_text"
                                       />
                                <input type="hidden" value="<s:property  value="D11" />"  ID="D11"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11"
                                       class="css_text"
                                       />
                                <% if (session.getAttribute("reportGrade").equals("1")) {%>
                                <s:if test="D11.equals('D11')">   
                                <td><input type="button" class="css_themxoa" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)"/></td>
                                </s:if>
                                <s:else>
                                <td>&nbsp;</td>
                            </s:else>
                            <%}%>
                        </tr>
                    </s:if>
                </s:iterator>
            </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <% if (!session.getAttribute("reportGrade").equals("1")) {%>
        <script>
            $.each($('form').serializeArray(), function (index, value) {
                $('[name="' + value.name + '"]').attr('readonly', 'readonly');
            });
        </script>
        <%}%>
    </body>
</html>
