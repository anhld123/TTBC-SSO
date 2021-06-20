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
                $(".TD_TEN_KH").css({"width": "350px"});
                $(".TD_CHUCVU").css({"width": "150px"});
                $(".TD_SOTIEN").css({"width": "70px"});
                $(".TD_TEN").css({"width": "90px"});
                $(".TD_THEMXOA").css({"width": "10px"});
                $(".TEN_KH").css({"width": "100%"});
                $(".TD_DTHOAI").css({"width": "60px"});
                $(".TD_TENFILE").css({"width": "150px"});
            });

            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            function deleteRow(indx) {
                var table = document.getElementById("tabletm");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
            }

            function addRow(indx) {
//                alert(indx);
//                sleep(1000);
                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("tabletm");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
                else
                {
                    max_row++;
                    rowCount = max_row;
                }
                var newTr = '<tr>\n\
                                <td align = "right" class="TD_THEMXOA"><input type="text" value="" id="D7" name="lstDulieuNt1[' + rowCount + '].D7" class="number TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_THEMXOA"><input type="text" value="" id="D8" name="lstDulieuNt1[' + rowCount + '].D8" class="D8  TEN_KH" onfocus="this.select();" onblur="autoEvaluate()"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="" id="D9" name="lstDulieuNt1[' + rowCount + '].D9" class=" TEN_KH" onfocus="this.select();"/></td>\n\
                                <td class="TD_THEMXOA"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                $($('table#tabletm tr')[index]).before(newTr);
                $('.D0').css({"text-align": "center"});
                $(".TD_TEN_KH").css({"width": "300px"});
                $(".TD_DTHOAI").css({"width": "30px"});
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
                1.Thông tin chung
            </div>
            <s:hidden name="khoa_sbv"/>
            <s:hidden name="ky_bc_LIST"/>

            </br>
            <table border="1" class="editDelete" align="center">
                <tr>
                    <th class="TD_TEN">Tên cán bộ TM</th>
                    <th class="TD_DTHOAI">Số điện thoại</th>
                    <th class="TD_SOTIEN">Phòng ban</th>
                    <th class="TD_TEN_KH">Nội dung thuyết minh, giải trình</th>
                    <th class="TD_THEMXOA">Số lượng file</th>
                    <th class="TD_TENFILE">Tên file gửi đính kèm</th>
                </tr>

                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr> 
                        <td align = "right" class="TD_TEN">
                            <input type="text" value="<s:property  value="D1" />" id="D1"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_DTHOAI">
                            <input type="text" value="<s:property  value="D2" />" id="D2"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D3" />" id="D3"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="D4" />" id="D4"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_THEMXOA">
                            <input type="text" value="<s:property  value="D5" />" id="D5"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_TENFILE">
                            <input type="text" value="<s:property  value="D6" />" id="D6"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                    </tr>
                </s:iterator>
            </table> 
            </br>
            <div id="divTitle">
                2.Thông tin chi tiết
            </div>
               </br>
            <table border="1" class="editDelete" id="tabletm" align="center">
                <tr>
                    <th   class="TD_THEMXOA">STT</th>
                    <th   class="TD_THEMXOA">Chỉ tiêu(dòng,cột) TM</th>
                    <th   class="TD_TEN_KH">Nội dung</th>
                        <s:if test="Grade.equalsIgnoreCase('3')">
                        <th   class="TD_THEMXOA">Thêm/Xóa</th>
                        </s:if>                                        
                </tr>



                <s:iterator value="#attr.lstDulieuNt1" var="modelView" status="rowstatus">
                    <tr> 
                        <td align = "right" class="TD_THEMXOA">
                            <input type="text" value="<s:property  value="D7" />" id="D7"
                                   name="lstDulieuNt1[<s:property  value="%{#rowstatus.index}" />].D7" class="number TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_THEMXOA">
                            <input type="text" value="<s:property  value="D8" />" id="D8"
                                   name="lstDulieuNt1[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <td align = "right" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="D9" />" id="D9"
                                   name="lstDulieuNt1[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH" onfocus="this.select();"/>
                        </td>
                        <s:if test="Grade.equalsIgnoreCase('3')">
                            <td class="TD_THEMXOA"><input type="button" value="   Xóa    " onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>
                            </s:if>                                                                      
                    </tr>
                </s:iterator>
                <s:if test="Grade.equalsIgnoreCase('3')">
                    <tr>
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
