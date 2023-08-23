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
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_POS").css({"width": "90px"});
                $(".TD_DONVITINH").css({"width": "160px"});
                $(".TD_DONVITINH1").css({"width": "auto"});
                $(".TD_DONVITINH1").css({"width": "150px"});
                $(".TD_THUTU").css({"width": "15px"});
                $(".TD_SOLUONG").css({"width": "50px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "220px"});
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
            function initTable()
            {
                autoEvaluate();
            }
            ;

            function autoEvaluate() {
//                alert('vao doClick');
                var arrCot = [".D3", ".D4", ".D5", ".D6", ".D7", ".D8", ".D9", ".D10", ".D11", ".D12", ".D13"]; //Luu cac cot cua du lieu can tinh toan
//                alert($(".number").size());
                var totalD8 = 0;
                var totalD3 = 0;
                var totalD4 = 0;
                var totalD5 = 0;
                var totalD6 = 0;
                var totalD7 = 0;
                var totalD9 = 0;
                var totalD10 = 0;
                var totalD11 = 0;
                var totalD12 = 0;
                var totalD13 = 0;
                for (var i = 0; i < 100; i++) {
                    let element;
                    element = document.getElementById("D8" + i);
                    if (element !== null) {
                        totalD8 = totalD8 + parseInt(document.getElementById("D8" + i).value.replaceAll(',', ''));
                        totalD3 = totalD3 + parseInt(document.getElementById("D3" + i).value.replaceAll(',', ''));
                        totalD4 = totalD4 + parseInt(document.getElementById("D4" + i).value.replaceAll(',', ''));
                        totalD5 = totalD5 + parseInt(document.getElementById("D5" + i).value.replaceAll(',', ''));
                        totalD6 = totalD6 + parseInt(document.getElementById("D6" + i).value.replaceAll(',', ''));
                        totalD7 = totalD7 + parseInt(document.getElementById("D7" + i).value.replaceAll(',', ''));
                        totalD9 = totalD9 + parseInt(document.getElementById("D9" + i).value.replaceAll(',', ''));
                        totalD10 = totalD10 + parseInt(document.getElementById("D10" + i).value.replaceAll(',', ''));
                        totalD11 = totalD11 + parseInt(document.getElementById("D11" + i).value.replaceAll(',', ''));
                        totalD12 = totalD12 + parseInt(document.getElementById("D12" + i).value.replaceAll(',', ''));
                        totalD13 = totalD13 + parseInt(document.getElementById("D13" + i).value.replaceAll(',', ''));
                    }
                }
//                totalD8
                document.getElementById("totalD3").value = totalD3;
                document.getElementById("totalD4").value = totalD4;
                document.getElementById("totalD5").value = totalD5;
                document.getElementById("totalD6").value = totalD6;
                document.getElementById("totalD7").value = totalD7;
                document.getElementById("totalD8").value = totalD8;
                document.getElementById("totalD81").value = totalD8;
                document.getElementById("totalD9").value = totalD9;
                document.getElementById("totalD10").value = totalD10;
                document.getElementById("totalD11").value = totalD11;
                document.getElementById("totalD111").value = totalD11;
                document.getElementById("totalD12").value = totalD12;
                document.getElementById("totalD13").value = totalD13;
                $('.number').number(true, 0);
            }
            ;

            function calc(id) {
                var row = id.parentNode.parentNode;
                var CT_D5 = row.cells[5].getElementsByTagName('input')[0].value;
                var CT_D6 = row.cells[6].getElementsByTagName('input')[0].value;
                var CT_D7 = row.cells[7].getElementsByTagName('input')[0].value;
                var CT_D8 = row.cells[8].getElementsByTagName('input')[0].value;
                var CT_D9 = row.cells[9].getElementsByTagName('input')[0].value;
                var CT_D11 = row.cells[11].getElementsByTagName('input')[0].value;
//                console.log(quant +' - '+price +' parseFloat(quant)='+parseFloat(quant.replace(/,/g, '')));
                res = parseFloat(CT_D8.replace(/,/g, '')) + parseFloat(CT_D9.replace(/,/g, '')); // tính D10
                res1 = parseFloat(CT_D11.replace(/,/g, '')) - res - parseFloat(CT_D6.replace(/,/g, '')); // tính D12
                res2 = res1 / (parseFloat(CT_D5.replace(/,/g, '')) + parseFloat(CT_D7.replace(/,/g, ''))); // tính D13
                row.cells[10].getElementsByTagName('input')[0].value = res;
                row.cells[12].getElementsByTagName('input')[0].value = res1;
                row.cells[13].getElementsByTagName('input')[0].value = res2;
            }

        </script>
    </head>
    <body>

        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                THEO DÕI THỰC HIỆN HỖ TRỢ LÃI SUẤT CÁC ĐƠN VỊ                
            </div>
            <div>
                <table id="kkk">
                    <tr>
                        <th style="width: 130px; font: italic; font-size: xx-small;" >Số liệu Trung ương</th>
                        <th style="width: 150px; font: italic; font-size: xx-small;" >
                            <input type="text" value="<s:property  value="totalD8Tw"/>" name="totalD8Tw" id="totalD8Tw" style="width: 140px" class="number" readonly="readonly"></th>                  
                        <th style="width: 150px; font: italic; font-size: xx-small;">
                            <input type="text" value="<s:property  value="totalD11Tw"/>" name="totalD11Tw" id="totalD11Tw" style="width: 140px" class="number" readonly="readonly"></th>                                
                        </th>
                    </tr>
                    <tr>
                        <th style="width: 130px; font: italic; font-size: xx-small;" >Số liệu nhập tại CN</th>
                        <th style="width: 150px; font: italic; font-size: xx-small;" >
                            <input type="text" value="" name="totalD8" id="totalD8" style="width: 140px" class="number" readonly="readonly">
                        </th>                  
                        <th style="width: 150px; font: italic; font-size: xx-small;">
                            <input type="text" value="" name="totalD11" id="totalD11" style="width: 140px" class="number" readonly="readonly">
                        </th>
                    </tr>
                </table>
            </div>    
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
                        <table border="1" class="editDelete" id="tablems08" align="center" style="overflow: scroll;  width: 105%;" 
                               >
                <tr>
                    <th rowspan="1"  class="TD_THUTU">STT</th>
                        <s:if test="Grade.equalsIgnoreCase('2')">                                     
                        <th rowspan="1"  class="TD_POS">Mã PGD</th>
                        <th rowspan="1"  class="TD_CHITIEU">Tên PGD</th>
                        </s:if>
                        <s:if test="Grade.equalsIgnoreCase('3')">                                     
                        <th rowspan="1"  class="TD_POS">Mã CN</th>
                        <th rowspan="1"  class="TD_CHITIEU">Tên chi nhánh</th>
                        </s:if>
                    <th rowspan="1"  class="TD_DONVITINH">Số tiền HTLS ngày trước liền kề trên cân đối</th>
                    <th colspan="1"  class="TD_DONVITINH">Số tiền HTLS cùng ngày tháng trước trên cân đối</th>
                    <th colspan="1"  class="TD_DONVITINH">Số tiền HTLS ngày báo cáo trên cân đối</th>
                    <th colspan="1"  class="TD_DONVITINH">Tổng số tiền HTLS cho các khoản chưa đến ngày dự thu còn phải chi trả đến ngày tạo số liệu_theo hồ sơ khế ước</th> 
                    <th rowspan="1"  class="TD_DONVITINH">Số tiền HTLS bình quân 1 ngày cho các khoản chưa đến ngày dự thu còn phải chi trả đến ngày tạo số liệu_theo hồ sơ khế ước</th>
                    <th colspan="1"  class="TD_DONVITINH">Số tiền HTLS bình quân 1 ngày cho các khoản chưa đến ngày dự thu còn phải chi trả đến ngày tạo số liệu_theo hồ sơ khế ước</th>
                    <th colspan="1"  class="TD_DONVITINH2">Số tiền HTLS năm 2022 tạm quyết toán, TW đã chuyển về cho Chi nhánh</th>
                    <th colspan="1"  class="TD_DONVITINH">Lũy Kế số tiền đã thực hiện HTLS từ ngày 01/01/2022 đến ngày báo cáo</th> 
                    <th rowspan="1"  class="TD_DONVITINH">Số tiền HTLS năm 2022 + 2023 được giao theo kế hoạch</th>
                    <th rowspan="1"  class="TD_DONVITINH">Số tiền HTLS Thừa/thiếu</th>
                    <th rowspan="1"  class="TD_POS">Dự kiến số ngày còn được HTLS</th>                    
                </tr>                
                <tr>  
                    <th></th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_POS">1</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_CHITIEU">2</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_DONVITINH">3</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">4</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">5</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">6</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">7</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">8</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">9</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">10 = 8+9</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">11</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">12 = 11-10-6</th>
                    <th style="font: italic; font-size: xx-small;" class="TD_POS">13 = 12/(5+7)</th>
                </tr>
                
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">

                    <tr height="22">                              
                        <td align="center" class="TD_THUTU">
                            <input type="text" value="<s:property  value="THUTU" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="D0 TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"/>
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>"/>
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN" value="<s:property  value="MACN"/>"/>
                            <s:if test="Grade.equalsIgnoreCase('2')">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" value="3"/>
                            </s:if>
                            <s:elseif test="Grade.equalsIgnoreCase('3')">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" value="4"/>
                            </s:elseif>
                        </td>
                        <td align="left" class="TD_POS">
                            <input type="text" value="<s:property  value="MA" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" class="D0 TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"/>
                        </td>
                        <td align="center" class="TD_CHITIEU">
                            <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"/>
                        </td>

                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D3" />"  id="D3<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           ;
                                           autoEvaluate()"/>                                   
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D4" />" id="D4<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           ;
                                           autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D5" />" id="D5<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly" onkeyup="calc(this);"  onchange="calc(this);"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           ;
                                           autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D6" />" id="D6<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly" onkeyup="calc(this);"  onchange="calc(this);" onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           ;
                                           autoEvaluate()"
                                   />
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D7" />" id="D7<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number TEN_KH" onfocus="this.select()" 
                                   onkeyup="calc(this);"  onchange="calc(this);"
                                   readonly="readonly"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           ;
                                           autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D8" />"  id="D8<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number TEN_KH" onfocus="this.select()"
                                   onkeyup="calc(this);"  onchange="calc(this);"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           ;
                                           autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D9" />" id="D9<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number TEN_KH" onfocus="this.select()" 
                                   onkeyup="calc(this);"  onchange="calc(this);"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D10" />" id="D10<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number TEN_KH" onfocus="this.select()"
                                   onkeyup="calc(this);"  onchange="calc(this);"
                                   readonly="readonly"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           ;
                                           autoEvaluate()"/>
                        </td>       
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D11" />"  id="D11<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number TEN_KH" onfocus="this.select()"
                                   onkeyup="calc(this);"  onchange="calc(this);"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           ;
                                           autoEvaluate()"/>
                        </td>
                        <s:if test="D12 < 0">
                            <td align = "right" class="TD_DONVITINH">  
                                <input type="text" value="<s:property  value="D12" />" style="color: red" id="D12<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D12 number TEN_KH" onfocus="this.select()"                                       
                                       onkeyup="calc(this);"  onchange="calc(this);"
                                       readonly="readonly"
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               autoEvaluate()"/>
                            </td>
                        </s:if>
                        <s:else>
                            <td align = "right" class="TD_DONVITINH"> 
                                <input type="text" value="<s:property  value="D12" />"  id="D12<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D12 number TEN_KH" onfocus="this.select()"                                       
                                       onkeyup="calc(this);"  onchange="calc(this);"
                                       readonly="readonly"
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               autoEvaluate()"/>
                            </td>
                        </s:else>

                        <td align = "right" class="TD_POS"> 
                            <input type="text" value="<s:property  value="D13" />" id="D13<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TD_POS number TEN_KH" onfocus="this.select()"                                       
                                   onkeyup="calc(this);"  onchange="calc(this);"
                                   readonly="readonly"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           ;
                                           autoEvaluate()"/>
                        </td>
                    </tr>


                </s:iterator>
                    <tr height="22">  
                    <th></th>
                    <th></th>
                    <th class="TD_DONVITINH">TỔNG CỘNG</th> 
                    <th> <input type="text" value="" name="totalD3" id="totalD3" class="number" readonly="readonly" style="background: #E7DCDA !important;"/>
                    </th>
                    <th><input type="text" value="" name="totalD4" id="totalD4" class="number" readonly="readonly" style="background: #E7DCDA !important;"/>
                    </th>
                    <th><input type="text" value="" name="totalD5" id="totalD5" class="number" readonly="readonly" style="background: #E7DCDA !important;"/>
                    </th>
                    <th><input type="text" value="" name="totalD6" id="totalD6" class="number" readonly="readonly" style="background: #E7DCDA !important;"/>
                    </th>
                    <th><input type="text" value="" name="totalD7" id="totalD7" class="number" readonly="readonly" style="background: #E7DCDA !important;"/>
                    </th>
                    <th> <input type="text" value="" name="totalD8" id="totalD81" class="number" readonly="readonly" style="background: #E7DCDA !important;"/>
                    </th>
                    <th><input type="text" value="" name="totalD9" id="totalD9" class="number" readonly="readonly" style="background: #E7DCDA !important;"/>
                    </th>
                    <th><input type="text" value="" name="totalD10" id="totalD10" class="number" readonly="readonly" style="background: #E7DCDA !important;"/>
                    </th>
                    <th><input type="text" value="" name="totalD11" id="totalD111" class="number" readonly="readonly" style="background: #E7DCDA !important;"/>
                    </th>
                    <th><input type="text" value="" name="totalD12" id="totalD12" class="number" readonly="readonly" style="background: #E7DCDA !important;"/>
                    </th>
                    <th><input type="text" value="" name="totalD13" id="totalD13" class="number" readonly="readonly" style="background: #E7DCDA !important;"/>
                    </th>

                </tr>
            </table>
            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initTable();
        </script>
    </body>
</html>
