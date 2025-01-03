<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <style>
            .CLS-BOLD{
                font-weight: bold;
            }

            .TEN_KH.NAME{
                width: 300px !important;
            }            

            .TEN_KH.CODE{
                width: 150px !important;
            }            

            .TEN_KH.TYPE{
                width: 150px !important;
            } 
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                $('.number').number(true, 0);
                $('.number2').number(true, 1);
                $(".TD_TEN_KH").css({"width": "80px"});
                $(".TD_DONVITINH").css({"width": "50px"});
                $(".TD_SOLUONG").css({"width": "50px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "200px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        <script>
            var max_row = 0;
            function getMabyNumber(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }

            function deleteRow(indx) {
                var table = document.getElementById("tablems05");
                var rowCount = table.rows.length - 2; //Dem so dong cua bang
                if (max_row < rowCount)
                {
                    max_row = rowCount;
                }
                table.deleteRow(indx);
            }

            function addRow(indx) {
                var flag = 0;
                var order = 0;
                $('.number3').removeClass();
                var index = parseInt(indx);
                var table = document.getElementById("tablems05");
                var rowCount = table.rows.length - 2; //Dem so dong cua bang
                var mapgd = document.getElementById("MAPGD_" + 0).value;
                var ma = document.getElementById("D1" + 0).value;
                var ten = document.getElementById("D2" + 0).value;
                if (max_row < rowCount)
                {
                    max_row = rowCount;
                } else
                {
                    max_row++;
                    rowCount = max_row;
                }
                for (var i = 0; i < rowCount;
                        i++
                        )
                {
                    if (getMabyNumber(i) === ma)
                    {
                        flag++;
                    }
                }
                if (flag > 1)
                {
                    index++;
                }
                //var code = (ma + indx).toString();
                order = rowCount + 1;
                max_row1 = rowCount + 1;
               
                var newTr = '<tr>\n\
                                <td ><input type="text" value="' + max_row1 + '" id="TT_HIENTHI" name="lstDulieuNt[' + rowCount + '].TT_HIENTHI" class="D0 number" readonly="readonly" onfocus="this.select();" /></td>\n\
                                <td ><input type="text" value="' + ten + '" id ="D2' + rowCount + '/>" name="lstDulieuNt[' + rowCount + '].D2" class="TD_CHITIEU" onfocus="this.select()" readonly="readonly"></td>\n\
                                <td ><input type="text" value="' + ma + '" id ="D1' + rowCount + '/>" name="lstDulieuNt[' + rowCount + '].D1" class="TD_CHITIEU" onfocus="this.select()"></td>\n\
                                <td ><input type="text" value="' + mapgd + '" name="lstDulieuNt[' + rowCount + '].MAPGD" id ="MAPGD_' + rowCount + '/>" class="D0" onfocus="this.select()" readonly="readonly" ></td>\n\
                                <td ><input type="text" value="0" id="D3" name="lstDulieuNt[' + rowCount + '].D3" class="number2" onfocus="this.select();"/></td>\n\
\n\<td ><input type="text" value="0" id="D13" name="lstDulieuNt[' + rowCount + '].D13" class="SOKU number" onfocus="this.select();"/></td>\n\
\n\<td ><input type="text" value="0" id="D5" name="lstDulieuNt[' + rowCount + '].D5" class="SOKU number2" onfocus="this.select();"/></td>\n\
\n\<td ><input type="text" value="0" id="D6" name="lstDulieuNt[' + rowCount + '].D6" class="SOKU number" onfocus="this.select();"/></td>\n\
                               \n\
                                <td ><input type="hidden"> <select name="lstDulieuNt[' + rowCount + '].D7"><option value="1">1. Có</option><option value="2">2. Chưa có</option><option value="3">3. Đang làm thủ tục</option></select></td>\n\
\n\ <td ><input type="text" value="0" id="D4" style="text-align:right" name="lstDulieuNt[' + rowCount + '].D4"  onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="0" id="D8" name="lstDulieuNt[' + rowCount + '].D8" class="SOKU" onfocus="this.select();"/></td>\n\
                                <td><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="D0 TD_TEN_KH"/></td>\n\
                                </tr>';
                $($('table#tablems05 tr')[index]).after(newTr);

                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                $(".TD_TEN_KH").css({"width": "80px"});
                $(".TEN_KH").css({"width": "100%"});
                $(".TEN_KH").css({"height": "22px"});
                $('.TEN_KH').focus(function () {
                    $(this).closest('tr').addClass('highlight_row');
                });
                $('.TEN_KH').blur(function () {
                    $(this).closest('tr').removeClass('highlight_row');
                });
                $(".TD_CHITIEU").css({"width": "200px"});
                $(".TD_CHITIEU1").css({"width": "100px"});
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
                $('.number2').number(true, 1);
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
                MS05B/QT - BÁO CÁO QUYỀN SỬ DỤNG ĐẤT
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablems05" align="center">
                <tr>                 
                    <th rowspan="1" style="width: 50px">TT</th>
                    <th rowspan="1" style="width: 200px">Chỉ tiêu</th>
                    <th rowspan="1" style="width: 200px">Mã tài sản</th>
                    <th rowspan="1" style="width: 150px">Mã Pos</th>
                    <th rowspan="1" style="width: 100px">Diện tích (m2)</th>
                    <th rowspan="1" style="width: 100px">Đơn giá đất</th>
                    <th rowspan="1" style="width: 100px">Hệ số điều chỉnh giá đất</th>
                    <th rowspan="1" style="width: 100px">Giá trị QSD đất đánh giá lại</th>
                    <th rowspan="1" style="width: 100px">Giấy CN QSD đất</th>
                    <th rowspan="1" style="width: 100px">Thời hạn sử dụng</th>
                    <!--<th rowspan="1" style="width: 100px">Thời hạn sử dụng</th>-->
                    <!--<th rowspan="1" style="width: 100px">Nguồn gốc sử dụng đất</th>-->
                    <!--<th rowspan="1" style="width: 150px">Giá trị QSD đất hạch toán</th>-->     
                    <!--<th rowspan="1" style="width: 100px">Tình trạng giấy CN QSD đất</th>-->
                    <th rowspan="1" style="width: 100px">Ghi chú</th>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                        <th rowspan="2" style="width: 30px;" class="TD_TEN_KH">Thêm/Xóa</th>
                        </s:if>
                </tr>                
                <tr>  
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;"></th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(8)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(9)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(10)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">

                    <tr height="22">  
                        <td>    
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>

                            <input type="text" value="<s:property value="%{#rowstatus.index + 1}" /> " 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0" onfocus="this.select()"    readonly="readonly" />                                                                                              

                        </td>
                        <td>    
                            <input type="text" value="<s:property  value="D2" />" id="D2<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TD_CHITIEU" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td>    
                            <input type="text" value="<s:property  value="D1" />" id ="D1<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TD_CHITIEU" onfocus="this.select()"/>                                  
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="MAPGD" />" id ="MAPGD_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" class="TEN_KH D0" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td>  
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH number2" onfocus="this.select()" />                                                  
                        </td>
                         <td>  
                            <input type="text" value="<s:property  value="D13" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH number" onfocus="this.select()" />                                                  
                        </td>
                        <td>  
                            <input type="text" value="<s:property  value="D5" />" style="text-align :right"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number2" onfocus="this.select()" />                                                  
                        </td>
                        <td>  
                            <input type="text" value="<s:property  value="D6" />" style="text-align :right"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select()" />                                                  
                        </td>
                        
                       

                        <td>
                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <select name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D7" id="D7<s:property value="%{#rowstatus.index}" />">
                                    <option value="1" <s:if test="D7.equalsIgnoreCase('1')"> selected </s:if>>1. Có</option>
                                    <option value="2" <s:if test="D7.equalsIgnoreCase('2')"> selected </s:if>>2. Chưa có</option>
                                    <option value="3" <s:if test="D7.equalsIgnoreCase('3')"> selected </s:if>>3. Đang làm thủ tục</option>
                                    </select>
                            </s:if>
                            <s:else>
                                <s:if test="D7.equalsIgnoreCase('1')"><a>1. Có</a></s:if>
                                <s:elseif test="D7.equalsIgnoreCase('2')"><a>2. Chưa có</a></s:elseif>
                                <s:elseif test="D7.equalsIgnoreCase('3')"><a>3. Đang làm thủ tục</a></s:elseif>

                            </s:else>
                        </td> 
                         <td>  
                            <input type="text" value="<s:property  value="D4" />"  style="text-align:right"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH" onfocus="this.select()" />                                                  
                        </td>
                        <td>  
                            <input type="text" value="<s:property  value="D8" />" style="text-align :right"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH" onfocus="this.select()" />                                                  
                        </td>
                        <s:if test="Grade.equalsIgnoreCase('1')"> 
                            <s:if test="%{#attr.lstDulieuNt.size > 1}">
                                <s:if test="%{#rowstatus.count == #attr.lstDulieuNt.size}">
                                    <td><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="D0 TEN_KH"/></td>
                                    </s:if>
                                    <s:else>
                                    <td><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="D0 TEN_KH"/></td>
                                    </s:else>
                                </s:if>
                                <s:else>
                                <td><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="D0 TEN_KH"/></td>
                                </s:else>
                            </s:if>
                    </tr>

                </s:iterator>
            </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            //            initTable();
        </script>
    </body>
</html>
