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
                $(".TD_CHUCVU").css({"width": "40px"});
                $(".TD_SOTIEN").css({"width": "50px"});
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
                var table = document.getElementById("tablems04");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
            }

            function addRow(indx) {
//                sleep(1000);
                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("tablems04");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
                else
                {
                    max_row++;
                    rowCount = max_row;
                }
                var newTr = '<tr>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="" id="D2" name="lstDulieuNt[' + rowCount + '].D2" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_CHUCVU"><input type="text" value="" id="D3" name="lstDulieuNt[' + rowCount + '].D3" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_CHUCVU"><input type="text" value="" id="D4_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D4" class="D0 datepicker" placeholder="dd/MM/yyyy"  onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_SOTIEN"><input type="text" value="0" id="D5" name="lstDulieuNt[' + rowCount + '].D5" class="D5 number" onfocus="this.select();" onblur="autoEvaluate()"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="" id="D6" name="lstDulieuNt[' + rowCount + '].D6" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="" id="D7" name="lstDulieuNt[' + rowCount + '].D7" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_SOTIEN"><input type="text" value="0" id="D8" name="lstDulieuNt[' + rowCount + '].D8" class="D8 number" onfocus="this.select();" onblur="autoEvaluate()"/></td>\n\
                                <td align = "right" class="TD_SOTIEN"><input type="text" value="0" id="D9" name="lstDulieuNt[' + rowCount + '].D9" class="D9 number" onfocus="this.select();" readonly="readonly"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="" id="D10" name="lstDulieuNt[' + rowCount + '].D10" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td class="TD_THEMXOA"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                $($('table#tablems04 tr')[index]).before(newTr);
                $('.D0').css({"text-align": "center"});
                $(".TD_TEN_KH").css({"width": "80px"});
                $(".TD_CHUCVU").css({"width": "40px"});
                $(".TD_SOTIEN").css({"width": "50px"});
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
        <script>
            function autoEvaluate(){
//                alert('vao doClick');
                var arrCot = [".D5",".D8",".D9"]; //Luu cac cot cua du lieu can tinh toan
                
//                  Tinh toan cho 7 dong
                for(var i=0; i<99; i++){
                    //8=2+4-6
                    $(".D9").eq(i).val(parseFloat($(".D5").eq(i).val()) - parseFloat($(".D8").eq(i).val()));
                    
                }
//                              
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
                BÁO CÁO THIẾU MẤT QUỸ (TIỀN VNĐ)
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablems04" align="center">
                <tr>
                    <th style="width: 60px;" class="TD_TEN_KH">Đơn vị, người làm mất quỹ</th>
                    <th style="width: 50px;" class="TD_CHUCVU">Chức vụ</th>
                    <th style="width: 50px;" class="TD_CHUCVU">Ngày, tháng, năm mất tiền</th>
                    <th style="width: 50px;" class="TD_SOTIEN">Số tiền thiếu, mất quỹ</th>
                    <th style="width: 50px;" class="TD_TEN_KH">Nguyên nhân</th>
                    <th style="width: 50px;" class="TD_TEN_KH">Biện pháp xử lý</th>
                    <th style="width: 50px;" class="TD_SOTIEN">Số tiền đã thu hồi</th>
                    <th style="width: 50px;" class="TD_SOTIEN">Số tiền còn phải thu tiếp</th>
                    <th style="width: 50px;" class="TD_TEN_KH">Biện pháp xử lý tiếp theo</th>
                    <s:if test="Grade.equalsIgnoreCase('1')">
                        <th style="width: 30px;" class="TD_THEMXOA">Thêm/Xóa</th>
                    </s:if>
                    
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr> 
                        <td align = "right" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="D2" />" id="D2"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D3" />" id="D3"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "center" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D0 datepicker" placeholder="dd/MM/yyyy" />
                        </td>
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D5" />" id="D5"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number2" onfocus="this.select();"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="D6" />" id="D6"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="D7" />" id="D7"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D8" />" id="D8"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number2" onfocus="this.select();"
                                   onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D9" />" id="D9"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2" onfocus="this.select();"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="D10" />" id="D10"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <td class="TD_THEMXOA"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>
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
                        <td class="TD_THEMXOA"><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>
                    
                    
                </tr>
                </s:if>
            </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
    </body>
</html>
