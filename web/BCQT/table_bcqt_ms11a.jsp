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
                $(".datepick").datepicker({dateFormat: 'dd/mm/yy'});
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

            function deleteRow(indx,idRow_process) {
                var table = document.getElementById("table12a");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
               try {
                    $('#' + idRow_process).val('DELETE');
                    if(idRow_process!=null && idRow_process !='')
                        $($('table#table12a tr')[indx]).hide();
                    else table.deleteRow(indx);
                } catch (e) {
                    alert('Lỗi khi xóa dữ liệu: ' + e.toString());
                }
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
                
                                //<td ><input type="text" value="" id="D4" name="lstDulieuNt[' + rowCount + '].D4" class="TEN_KH" onfocus="this.select();" /></td>
                var idDate="date"+ rowCount;
                var idChtrinh = "Chtrinh_vay_" + rowCount;
                var newTr = '<tr>\n\
                                <td ><input type="text" value="" id="ma" name="lstDulieuNt[' + rowCount + '].MA" class="TEN_KH" onfocus="this.select();" />\n\
                                <td ><input type="text" value="" id="Ten" name="lstDulieuNt[' + rowCount + '].TEN" class="TEN_KH" onfocus="this.select();" />\n\
                                <td ><input type="text" value="" id="diachi" name="lstDulieuNt[' + rowCount + '].D11" class="TEN_KH" onfocus="this.select();" />\n\
                                <input type="hidden" value="88888888" id="D2" name="lstDulieuNt[' + rowCount + '].D1" class="TEN_KH" onfocus="this.select();" </td>\n\
                                <td ><input type="text" value="" id="D2" name="lstDulieuNt[' + rowCount + '].D2" class="TEN_KH" onfocus="this.select();" /></td>\n\\n\
                                <td><select name="lstDulieuNt[' + rowCount + '].D12" id="'+idChtrinh+'" style="width: 150px;vertical-align: middle;background-color: #FFCCBA;"></select></td>\n\
                                <td ><input type="text" name="lstDulieuNt[' + rowCount + '].D3"  class="datepick dates" id="date' + rowCount + '"/></td>\n\
                                <td ><input type="text" value="" id="D6" name="lstDulieuNt[' + rowCount + '].D4" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="0" id="D6" name="lstDulieuNt[' + rowCount + '].D5" class="TEN_KH number2" onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="0" id="D7" name="lstDulieuNt[' + rowCount + '].D6" class="TEN_KH number2" onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="0" id="D8" name="lstDulieuNt[' + rowCount + '].D7" class="TEN_KH number2" onfocus="this.select();"/></td>\n\
                                <td ><input type="text" value="0" id="D9" name="lstDulieuNt[' + rowCount + '].D8" class="TEN_KH number2" onfocus="this.select();"/></td>\n\
                                <td style="width: 30px;"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH" style="width: 30px;"/></td>\n\
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
                $('#'+idDate).datepicker({dateFormat: "dd/mm/yy"});
                $.getJSON('loadDMChtrinh', {
                    Message: 'fileTemplate',
                    khoa_bcqt: 'khoa_bcqt'
                }, function (jsonResponse) {
                    //reload lai du lieu cho select option
                    //alert('Vao goi json idChtrinh=' + idChtrinh);
                    try
                    {
                        //Đưa danh mục chương trình lấy từ csdl vào
                        var chtrinh = '<option value="-1" selected="selected">---Chương trình---</option>';
                        $.each(jsonResponse.lstCBChuongtrinh, function () {
                            chtrinh += '<option value="'+this.sKey+'">'+ this.sDesc + '</option>';
                        });
                        chtrinh += "";
                        $('#' + idChtrinh).html(chtrinh);
                        
                        //alert(jsonResponse.msgError);
                        if (jsonResponse.msgError != null)
                        {
                            alert(jsonResponse.msgError);
                            $('#message_suc_err').text(jsonResponse.msgError);
                        }
                    } catch (e) {
                        alert(e.toString());
                    }
                });
                    

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
                    SAO KÊ CHI TIẾT NỢ XÓA ĐANG TRONG THỜI GIAN THEO DÕI
                </s:if>
                <s:else>
                    TỔNG HỢP SAO KÊ NỢ XÓA ĐANG TRONG THỜI GIAN THEO DÕI
                </s:else>
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <s:if test="Grade.equalsIgnoreCase('1')">
                <table border="1" class="editDelete" id="table12a" align="center">
                    <tr>
                        <th style="width: 30px;" class="TD_SOKU" rowspan="2">Mã khách hàng</th>
                        <th style="width: 50px;" class="TD_SOKU" rowspan="2">Họ và tên Khách hàng</th>
                        <th style="width: 50px;" class="TD_SOKU" rowspan="2">Địa chỉ</th>
                        <th style="width: 50px;" class="TD_SOKU" rowspan="2">Số khế ước</th>
                        <th style="width: 50px;" class="TD_SOKU" rowspan="2">Chương trình vay</th>
                        <th style="width: 110px;" rowspan="2">Ngày hạch toán</th>
                        <th style="width: 100px;" rowspan="2">Mã sản phẩm</th>
                        <th style="width: 70px;" class="TD_SOKU" colspan="2">Sao kê</th>
                        <th style="width: 70px;" class="TD_SOKU" colspan="2">Cân đối</th>
                    </tr>
                    <tr>          
                        <th style="width: 50px;" >Tiền gốc</th>
                        <th style="width: 50px;" class="TD_TEN_KH">Tiền lãi</th>
                        <th style="width: 50px;" >Tiền gốc</th>
                        <th style="width: 50px;" class="TD_TEN_KH">Tiền lãi</th>
                    </tr>
                    <s:iterator value="#attr.lstMs11a" var="modelView" status="rowstatus">
                        <tr>  
                            <td>
                                <input type="text" value="<s:property  value="MAKH" />" id="D1"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" class="TEN_KH" onfocus="this.select();" />
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="TENKH" />" id="D2"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select();" />
                                <input type="hidden" value="<s:property  value="ROWID" />" id="idRow_process_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH" onfocus="this.select();"/>
                                <input type="hidden" value="<s:property  value="TT_ROW" />" id="idttHienthi_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH" onfocus="this.select();"/>
								<input type="hidden" value="<s:property  value="TTMONVAY" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" class="TEN_KH" onfocus="this.select();"/>
							</td>
                            <td>
                                <input type="text" value="<s:property  value="DIACHI" />" id="D11"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH" onfocus="this.select();" />
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="SOKU" />" id="D3"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();" />
                            </td>
                            <td>
                                <s:if test="TT_ROW.equalsIgnoreCase('I')">
                                    <s:select  
                                        id="lstDulieuNt[%{#rowstatus.index}].D12"
                                        name="lstDulieuNt[%{#rowstatus.index}].D12"
                                        list="lstCBChuongtrinh" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        headerKey="-1"
                                        headerValue="--- Chọn ---"    
                                        value="CHTRINH"
                                        cssStyle="width: 150px;vertical-align: middle;background-color: #FFCCBA;">
                                    </s:select>
                                </s:if>
                                <s:else>
                                    <s:select  
                                        id="lstDulieuNt[%{#rowstatus.index}].D12"
                                        name="lstDulieuNt[%{#rowstatus.index}].D12"
                                        list="lstCBChuongtrinh" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        headerKey="-1"
                                        headerValue="--- Chọn ---"    
                                        value="CHTRINH"
                                        disabled="true"                                      
                                        cssStyle="width: 150px;vertical-align: middle;background-color: #FFCCBA;">
                                    </s:select>
                                </s:else>
                            </td>
                         
                            <td>
                                <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"  class="datepick dates TEN_KH" id="date<s:property  value="%{#rowstatus.index}" />" value="<s:date name = "NGAY_HT" format = "dd/MM/yyyy" />"/>
                            </td>
                             <td>
                                 <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"  class="TEN_KH" id="sbt<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="MASP" />"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="SOTIEN_GOC_NT" />" id="D5"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="SOTIEN_LAI_NT" />" id="D6"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="GOC_HACHTOAN_NT" />" id="D7"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="LAI_HACHTOAN_NT" />" id="D8"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH number2" onfocus="this.select();"/>
                            </td>
                          
                                <td style="width: 40px;"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex,'idRow_process_<s:property  value="%{#rowstatus.index}" />')" class="TEN_KH" style="width: 40px;"/>
                           
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
                        <td></td>
                        <td></td>
                        <td></td>
                        <td style="width: 60px;"><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>
                    </tr>
                </table>
                <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
            </s:if>

            <s:else>
                <table border="1" class="editDelete" id="table12a" align="center">
                    <tr>
                        <th rowspan="2" style="width: 30px;" align="center" >Mã PGD</th>
                        <th rowspan="2" style="width: 100px;" class="TD_SOKU">Tên PGD</th>
                        <th rowspan="2" style="width: 40px;" class="TD_SOKU">Tổng số món</th>
                        <th colspan="2" style="width: 70px;" class="TD_SOKU">Sao kê</th>
                        <th colspan="2" style="width: 70px;" class="TD_SOKU">Cân đối</th>
                        <th colspan="2" style="width: 70px;" class="TD_SOKU">Chênh lệch (CĐ-SK)</th>
                    </tr>
                    <tr>          
                        <th style="width: 50px;" class="TD_SOKU">Tiền gốc</th>
                        <th style="width: 50px;" class="TD_SOKU">Tiền lãi</th>
                        <th style="width: 50px;" class="TD_SOKU">Tiền gốc</th>
                        <th style="width: 50px;" class="TD_SOKU">Tiền lãi</th>
                        <th style="width: 50px;" class="TD_SOKU">Tiền gốc</th>
                        <th style="width: 50px;" class="TD_SOKU">Tiền lãi</th>
                    </tr>
                    <s:iterator value="#attr.lstMs11a" var="modelView" status="rowstatus">
                        <tr>  
                            <td>
                                <input type="text" value="<s:property  value="MAPGD" />" id="D1"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" style="width: 50px;"  align="center" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="TENKH" />" id="D1"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" style="width: 180px;" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="SOKU" />" id="D3"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" style="width: 50px;" class="number2" onfocus="this.select();" readonly="true"/>
                            </td>

                            <td>
                                <input type="text" value="<s:property  value="SOTIEN_GOC_NT" />" id="D5"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH number2" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="SOTIEN_LAI_NT" />" id="D6"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number2" onfocus="this.select();" readonly="true"/>
                            </td>
                            
                            
                            <td>
                                <input type="text" value="<s:property  value="GOC_HACHTOAN_NT" />" id="D7"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number2" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="LAI_HACHTOAN_NT" />" id="D8"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number2" onfocus="this.select();" readonly="true"/>
                            </td>
                            
                            
                            <td>
                                <input type="text" value="<s:property  value="SOTIEN_GOC" />" id="D9"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH number2" onfocus="this.select();" readonly="true"/>
                            </td>
                              <td>
                                <input type="text" value="<s:property  value="SOTIEN_LAI" />" id="D9"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH number2" onfocus="this.select();" readonly="true"/>
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
