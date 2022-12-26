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
                $('.number2').number(true, 2);
                $(".TD_TEN_KH").css({"width": "130px"});
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

            function addRow(indx, ma, ten) {
                var flag = 0;
                var order = 0;
                $('.number3').removeClass();
                var index = parseInt(indx);
                var table = document.getElementById("tablems05");
                var rowCount = table.rows.length - 2; //Dem so dong cua bang
                if (max_row < rowCount)
                {
                    max_row = rowCount;
                } else
                {
                    max_row++;
                    rowCount = max_row;
                }
                for (var i = 0; i < rowCount; i++)
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
                var newTr = '<tr height="22" class=""> <td align="right" class="TD_THUTU" style="width: 30px;"> <input type="text" value="'+order+'" name="lstDulieuNt[' + rowCount + '].TT_HIENTHI" class="D0 TEN_KH Y" onfocus="this.select()" readonly="readonly" style="text-align: center; width: 100%;"></td> <td align="right" class="TD_TEN_KH" style="width: 130px;"> <input type="text" value="' + ten + '" name="lstDulieuNt[' + rowCount + '].D2" class="TEN_KH Y" onfocus="this.select()" readonly="readonly" style="width: 100%;"> </td> <td align="right" class="TD_NGUYENGIA" style="width: 80px;"> <input type="text" value="' + ma +'" name="lstDulieuNt[' + rowCount + '].D1" class="TEN_KH Y" onfocus="this.select()" readonly="readonly" style="width: 100%;"> </td> <td align="right" class="TD_NGUYENGIA" style="width: 80px;"> <input type="hidden" id="TMP_D12_0" value="" class="TEN_KH" onfocus="this.select()" style="width: 100%;"> <select name="lstDulieuNt[' + rowCount + '].D12" class="TEN_KH" style="font-weight: bold; vertical-align: middle; width: 100%;" oncompletetopics="myCompleteTopics" onbeforetopics="myBeforeHandler"><option value="-1">-- Chọn --</option><option value="D1">D1 -&gt; Đất được giao</option><option value="D2">D2 -&gt; Đất được thuê</option><option value="D3">D3 -&gt; Đất khác</option></select></td> <td align="right" class="TD_NGUYENGIA" style="width: 80px;"> <input type="text" value="" name="lstDulieuNt[' + rowCount + '].D4" class=" TEN_KH number" onfocus="this.select()" style="text-align: right; width: 100%;"> </td> <td align="right" class="TD_NGUYENGIA" style="width: 80px;"> <input type="text" value="" name="lstDulieuNt[' + rowCount + '].D5" class="number TEN_KH" onfocus="this.select()" style="text-align: right; width: 100%;"> </td> <td align="right" class="TD_NGUYENGIA" style="width: 80px;"> <input type="text" value="" name="lstDulieuNt[' + rowCount + '].D6" class="D6 number2 TEN_KH " onfocus="this.select()" style="text-align: right; width: 100%;"> </td> <td align="right" class="TD_NGUYENGIA" style="width: 80px;"> <input type="text" value="" name="lstDulieuNt[' + rowCount + '].D7" class="D7 number2 TEN_KH " onfocus="this.select()" style="text-align: right; width: 100%;"> </td> <td align="right" class="TD_CHITIEU" style="width: 200px;"> <input type="text" value="" name="lstDulieuNt[' + rowCount + '].D8" class=" TEN_KH" onfocus="this.select()" style="width: 100%;"> </td> <td align="right" class="TD_CHITIEU" style="width: 200px;"> <input type="text" value="" name="lstDulieuNt[' + rowCount + '].D9" class="TEN_KH" onfocus="this.select()" style="width: 100%;"> </td> <td align="right" class="TD_CHITIEU" style="width: 200px;"> <input type="text" value="" name="lstDulieuNt[' + rowCount + '].D11" class="TEN_KH" onfocus="this.select()" style="width: 100%;"> </td> <td align="center" class="TD_TEN_KH" style="width: 130px;"> <input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td> </tr>';
                $($('table#tablems05 tr')[index]).after(newTr);

                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TD_TEN_KH").css({"height": "22px"});
                $(".TEN_KH").css({"width": "100%"});
                $(".TEN_KH").css({"height": "22px"});
                $('.TEN_KH').focus(function () {
                    $(this).closest('tr').addClass('highlight_row');
                });
                $('.TEN_KH').blur(function () {
                    $(this).closest('tr').removeClass('highlight_row');
                });
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                
                $('.number').number(true, 0);
                $('.number2').number(true, 2);
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
                    <th rowspan="1"  class="TD_THUTU">TT</th>
                    <th rowspan="1" class="TD_CHITIEU">Chỉ tiêu</th>
                    <th rowspan="1" class="TD_TEN_KH">Mã tài sản</th>
                    <th rowspan="1" class="TD_SOLUONG">Loại đất</th>
                    <th rowspan="1" class="TD_DONVITINH">Diện tích (m2)</th>
                    <th rowspan="1"  class="TD_SOLUONG">Đơn giá đất</th>                    
                    <th rowspan="1"  class="TD_SOLUONG">Hệ số điều chỉnh giá đất</th>
                    <th rowspan="1"  class="TD_SOLUONG">Giá trị QSD đất đánh giá lại</th>
                    <th rowspan="1"  class="TD_SOLUONG">Giấy CN QSD đất</th>
                    <th rowspan="1"  class="TD_SOLUONG">Thời hạn sử dụng</th>
                    <th rowspan="1"  class="TD_SOLUONG">Ghi chú</th>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                        <th rowspan="2" style="width: 30px;" class="TD_TEN_KH">Thêm/Xóa</th>
                        </s:if>
                </tr>                
                <tr>  
                    <th style="width: 30px; font-size: xx-small;" class="TD_THUTU">(1)</th>
                    <th style="width: 120px; font-size: xx-small;" class="TD_CHITIEU">(2)</th>
                    <th style="width: 50px; font-size: xx-small;" class="TD_TEN_KH">(3)</th>
                    <th style="width: 100px; font-size: xx-small;" class="TD_SOLUONG">(4)</th>
                    <th style="width: 40px; font-size: xx-small;" class="TD_SOLUONG">(5)</th>
                    <th style="width: 40px; font-size: xx-small;" class="TD_SOLUONG">(6)</th>
                    <th style="width: 40px; font-size: xx-small;" class="TD_NGUYENGIA">(7)</th>
                    <th style="width: 40px; font-size: xx-small;" class="TD_NGUYENGIA">(8)</th>
                    <th style="width: 40px; font-size: xx-small;" class="TD_CHITIEU">(9)</th>
                    <th style="width: 40px; font-size: xx-small;" class="TD_CHITIEU">(10)</th>
                    <th style="width: 120px; font-size: xx-small;" class="TD_CHITIEU">(11)</th>


                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">

                    <tr height="22">  
                        <td  align="right" class="TD_THUTU">    

                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>

                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH" onfocus="this.select()"    readonly="readonly" />                                                                                              

                        </td>
                        <td  align="right" class="TD_TEN_KH">    
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH NAME" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>
                        <td  align="right" class="TD_NGUYENGIA">    
                            <input type="text" value="<s:property  value="D1" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH CODE" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>

                        <s:if test="Grade.equalsIgnoreCase('1')">

                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="hidden" id="TMP_D12_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D12" />" 
                                       class="TEN_KH" onfocus="this.select()"/>
                                <s:select 
                                    id="D12_%{#rowstatus.index}"
                                    name="lstDulieuNt[%{#rowstatus.index}].D12"
                                    list="lstAllBcqt" 
                                    listKey="sKey"
                                    listValue="sDesc"           
                                    headerKey="-1"
                                    headerValue="-- Chọn --" 
                                    cssStyle="font-weight: bold;vertical-align: middle;width: 150px;"
                                    onBeforeTopics="myBeforeHandler" 
                                    onCompleteTopics="myCompleteTopics" cssClass="TEN_KH TYPE"
                                    >                    
                                </s:select>
                            </td>

                        </s:if>
                        <s:else>
                            <td  align="right" class="TD_NGUYENGIA">    
                                <input type="text" value="<s:property  value="D12" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH <s:property  value="NHAPTAY" />" onfocus="this.select()"    readonly="readonly" />                                  
                            </td>
                        </s:else>

                        <td align = "right" class="TD_NGUYENGIA">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class=" TEN_KH number" onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if>/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number TEN_KH" onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if>/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D6" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number2 TEN_KH " onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if>/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D7" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number2 TEN_KH " onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if> />
                            </td>
                            <td align = "right" class="TD_CHITIEU">
                                <input type="text" value="<s:property  value="D8" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class=" TEN_KH" onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if> />
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D9" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH" onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if>/>
                            </td>

                            <td align = "right" class="TD_CHITIEU">
                                <input type="text" value="<s:property  value="D11" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH" onfocus="this.select()"  <s:if test="Grade.equalsIgnoreCase('2')">readonly="readonly"</s:if>/>
                            </td>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <td align = "center" class="TD_TEN_KH">

                                <input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex,'<s:property  value="D1"/>','<s:property  value="D2"/>')" class="TEN_KH"/>                                    
                                
                                

                            </td>
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
