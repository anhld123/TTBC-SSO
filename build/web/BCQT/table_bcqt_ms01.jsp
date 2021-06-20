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
            $(document).ready(function() {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TD_TEN_KH").css({"height": "22px"});
                $(".TEN_KH").css({"width": "100%"});
                $(".TEN_KH").css({"height": "22px"});
            });
            $('.TEN_KH').focus(function() {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function() {
                $(this).closest('tr').removeClass('highlight_row');
            });</script>
        <script>
            function getMabyNumber(idx) {
                var ma = '';
                try {
                    var ma_id = 'id_' + idx;
                    ma = document.getElementById(ma_id).value;
                }
                catch (e) {
                    ma = '999999';
                }
                return ma;
            }

            function getValue(id)
            {
                var value = 0;
                try {
                    value = document.getElementById(id).value;
                    value = value.replace(/,/g, "");
                    if (value == '-1' || value == null || value == '' || value == ' ')
                        value = 0.0;
                }
                catch (e) {
                    value = 0.0;
                }
                return parseFloat(value);
            }

            function setValue(id, value) {
                try {
                    document.getElementById(id).value = value;
                }
                catch (e) {
                }
            }

            function getNum(id) {
                var value = 0;
                try {
                    value = document.getElementById(id).value;
                    value = value.replace(/,/g, "");
                    if (value == '-1'
                            || value.length == 0)
                        value = 0.0;
                }
                catch (e) {
                    value = 0.0;
                }
                return parseFloat(value);
            }

            // HAM LAY ANH XA TU TEN COT THANH VI TRI COT TRONG MANG
            function getMappingCol_byName(column_name) {
                var index = column_name.substr(1, 2);
                //                alert('getMappingCol_byName'+index);
                return parseInt(index);
            }
            // HAM LAY ANH XA TU TEN COT THANH VI TRI COT TRONG MANG
            function getMappingCol_byId(id) {
                return 'D' + id.toString();
            }
            function initTable()
            {
                try {
                    var table = document.getElementById("tablems01");
                    var rowcount = table.rows.length;
                    for (var i = 0; i < rowcount; i++)
                    {
                        sumColumn(i);
                    }
                }
                catch (e) {
                    alert('Khởi tạo bảng mẫu biểu quyết toán 01/QT lỗi: ' + e);
                }
            }

            function sumColumn(mainput_tmp) {
                var i = mainput_tmp;
                var D98 = 0, D100 = 0, D99=0;
                try
                {
                    var table = document.getElementById("tablems01");
                    var rowcount = table.rows.length;
                    rowcount = rowcount > max_row ? rowcount : max_row;
                    var D1 = 0, D2 = 0, D3 = 0, D4 = 0, D5 = 0, D6 = 0;
                    //Thuc hien cong Theo hang Ngang va dua Gia tri tong tung cot vao cac Bien tu D2->D6
                    var matmp = getMabyNumber(i);
                    if (matmp.substr(0, 7) != 'M010099') {
                        D1 = getValue('D1_' + i);
                        D2 = getValue('D2_' + i);
                        D4 = getValue('D4_' + i);
                        //Lay gia tri cot D3 = Menhgia*SoLuong = D2*TEN
                        D3 = getValue('D1_' + i) * getValue('D2_' + i);
                        D5 = getValue('D1_' + i) * getValue('D4_' + i);
                        D6 = D3 + D5;
                        setValue('D3_' + i, D3);
                        setValue('D5_' + i, D5);
                        setValue('D6_' + i, D6);

                        $(".D6").each(function() {
                            var value = $(this).val();
                            value = value.replace(/,/g, "");
                            if (value == null || value == '' || value == ' ')
                                value = 0;
                            D98 = D98 + parseFloat(value);
                        });
                        D99 = getValue('D6_99');
                    }
                    setValue('D6_98', D98);
//                    setValue('D6_99', D99);
                    setValue('D6_100', D98 - D99);
                    $('.number2').number(true, 0);
                }
                catch (e) {
                    alert(e);
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
                BÁO CÁO KIỂM KÊ TIỀN MẶT VIỆT NAM ĐỒNG THUỘC QUỸ NGHIỆP VỤ
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: đồng
            </div>
            <table border="1" class="editDelete" id="tablems01" align="center">
                <tr>
                    <th rowspan="2" style="width: 50px;">Mệnh giá</th>
                    <th colspan="2">Tiền mặt đủ tiêu chuẩn lưu thông</th>
                    <th colspan="2">Tiền mặt không đủ tiêu chuẩn lưu thông</th>
                    <th rowspan="2">Tổng cộng</th>
                </tr>
                <tr>                                  
                    <th  style="width: 20px;">Số tờ</th>
                    <th  style="width: 40px;">Thành tiền</th>
                    <th  style="width: 20px;">Số tờ</th>
                    <th  style="width: 40px;">Thành tiền</th>
                </tr>
                <tr>         
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(1)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(2)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(3)=(2)x(1)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(4)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(5)=(4)x(1)</th>
                    <th style="width: 50px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(6)=(5)+(3)</th>
                </tr>

                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr> 
                        <td align = "right">
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" value="<s:property  value="TT_HIENTHI"/>"/>
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP" value="<s:property  value="CO_TONGHOP"/>"/>
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KIEUIN" value="<s:property  value="KIEUIN"/>"/>
                            <input type="text" id="D1_<s:property value="%{#rowstatus.index}" />"
                                   value="<s:property  value="TEN" />"
                                   name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].TEN"
                                   class="D1 number2" onfocus="this.select()" readonly="true"/>
                        </td>
                        <td align = "right">
                            <input type="text" id="D2_<s:property value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D2" />"
                                   name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D2" 
                                   class="D2 number2" onfocus="this.select();"
                                   onblur="sumColumn(<s:property value="%{#rowstatus.index}" />);"
                                   />
                        </td>
                        <td align = "right">
                            <input type="text" id="D3_<s:property value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D3" 
                                   class="D3 number2" onfocus="this.select();
                                           sumColumn(<s:property value="%{#rowstatus.index}" />);" readonly="true"/>
                        </td>
                        <td align = "right">
                            <input type="text" id="D4_<s:property value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D4" />"
                                   name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D4" 
                                   class="D4 number2" onfocus="this.select();"
                                   onblur="sumColumn(<s:property value="%{#rowstatus.index}" />);"
                                   />
                        </td>
                        <td align = "right">
                            <input type="text" id="D5_<s:property value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D5" 
                                   class="D5 number2" onfocus="this.select();
                                           sumColumn(<s:property value="%{#rowstatus.index}" />);" readonly="true"/>
                        </td>
                        <td align = "right">
                            <input type="text" id="D6_<s:property value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D6" />" 
                                   name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D6" 
                                   class="D6 number2" onfocus="this.select();
                                           sumColumn(<s:property value="%{#rowstatus.index}" />);" readonly="true"/>
                        </td>
                    </tr>
                </s:iterator>
                <tr height="25px" >
                    <th colspan="5"  align = "left">I. Cộng tồn quỹ thực tế</th>
                    <th rowspan="1" align = "right">
                        <input type="text" id="D6_98" 
                               value="<s:property  value="D6" />" 
                               name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D6" 
                               class="D7 number2" onfocus="this.select();
                                       sumColumn(<s:property value="%{#rowstatus.index}" />);" readonly="true"/>
                    </th>
                </tr>
                <tr height="25px" >
                    <th colspan="5"  align = "left">II. Số tiền tồn quỹ theo sổ sách</th>
                    <th rowspan="1" align = "right">
                        <input type="text" id="D6_99" 
                               value="<s:property  value="valTonQuySS" />" 
                               name="valTonQuySS" 
                               class="D7 number2" onfocus="this.select();
                                       sumColumn(<s:property value="%{#rowstatus.index}" />);" readonly="true"/>
                    </th>
                </tr>
                <tr height="25px" >
                    <th colspan="5"  align = "left">(III=I-II). Chênh lệch: Thừa(+), thiếu (-)</th>
                    <th rowspan="1" align = "right">
                        <input type="text" id="D6_100" 
                               value="<s:property  value="D6" />" 
                               name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D6" 
                               class="D9 number2" onfocus="this.select();
                                       sumColumn(<s:property value="%{#rowstatus.index}" />);" readonly="true"/>
                    </th>
                </tr>
            </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initTable();
        </script>
    </body>
</html>
