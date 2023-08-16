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
                $(".TD_POS").css({"width": "40px"});
                $(".TD_DONVITINH").css({"width": "60px"});
                $(".TD_THUTU").css({"width": "15px"});
                $(".TD_SOLUONG").css({"width": "50px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "130px"});
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
                var arrCot = [".D8", ".D11"]; //Luu cac cot cua du lieu can tinh toan
//                alert($(".number").size());
                var totalD8 = 0;
                var totalD11 = 0;
                for (var i = 0; i < 33; i++) {
                    let element;
                    element = document.getElementById("D8" + i);
                    if (element !== null) {
                        totalD8 = totalD8 + parseInt(document.getElementById("D8" + i).value.replaceAll(',', ''));
                        totalD11 = totalD11 + parseInt(document.getElementById("D11" + i).value.replaceAll(',', ''));
                    }
                }
//                totalD8
                document.getElementById("totalD8").value = totalD8;
                document.getElementById("totalD11").value = totalD11;
                $('.number').number(true, 0);
            }
            ;


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
                        <th style="width: 60px; font: italic; font-size: xx-small;" >
                            <input type="text" value="<s:property  value="totalD8Tw"/>" name="totalD8Tw" id="totalD8Tw" style="width: 100px" class="number" readonly="readonly"></th>                  
                        <th style="width: 60px; font: italic; font-size: xx-small;">
                            <input type="text" value="<s:property  value="totalD11Tw"/>" name="totalD11Tw" id="totalD11Tw" style="width: 100px" class="number" readonly="readonly"></th>                                
                        </th>
                    </tr>
                    <tr>
                        <th style="width: 130px; font: italic; font-size: xx-small;" >Số liệu nhập tại CN</th>
                        <th style="width: 60px; font: italic; font-size: xx-small;" >
                            <input type="text" value="" name="totalD8" id="totalD8" style="width: 100px" class="number" readonly="readonly">
                        </th>                  
                        <th style="width: 60px; font: italic; font-size: xx-small;">
                            <input type="text" value="" name="totalD11" id="totalD11" style="width: 100px" class="number" readonly="readonly">
                        </th>
                    </tr>
                </table>
<!--                <font color="red">Số liệu Trung ương: (Cột 8: <s:property  value="totalD8"/> &nbsp;&nbsp; Cột 11: <s:property  value="totalD11"/>)</font> 
              </br>Số liệu nhập tại CN: (Cột 8:<input type="text" value="" name="totalD8" id="totalD8" style="width: 100px" class="number"> &nbsp;&nbsp; Cột 11:<input type="text" value="" name="totalD11" id="totalD11" style="width: 100px" class="number">)-->
            </div>    


            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablems08" align="center">
                <tr>
                    <th rowspan="1"  class="TD_THUTU">STT</th>
                    <th rowspan="1"  class="TD_POS">Mã PGD</th>
                    <th rowspan="1"  class="TD_CHITIEU">Tên PGD</th>
                    <th rowspan="1"  class="TD_DONVITINH">Số tiền HTLS ngày trước liền kề trên cân đối</th>
                    <th colspan="1"  class="TD_DONVITINH">Số tiền HTLS cùng ngày tháng trước trên cân đối</th>
                    <th colspan="1"  class="TD_DONVITINH">Số tiền HTLS ngày báo cáo trên cân đối</th>
                    <th colspan="1"  class="TD_DONVITINH">Tổng số tiền HTLS cho các khoản chưa đến ngày dự thu còn phải chi trả đến ngày tạo số liệu_theo hồ sơ khế ước</th> 
                    <th rowspan="1"  class="TD_DONVITINH">Số tiền HTLS bình quân 1 ngày cho các khoản chưa đến ngày dự thu còn phải chi trả đến ngày tạo số liệu_theo hồ sơ khế ước</th>
                    <th colspan="1"  class="TD_DONVITINH">Số tiền HTLS bình quân 1 ngày cho các khoản chưa đến ngày dự thu còn phải chi trả đến ngày tạo số liệu_theo hồ sơ khế ước</th>
                    <th colspan="1"  class="TD_DONVITINH">Số tiền HTLS năm 2022 tạm quyết toán, TW đã chuyển về cho Chi nhánh</th>
                    <th colspan="1"  class="TD_DONVITINH">Lũy Kế số tiền đã thực hiện HTLS từ ngày 01/01/2022 đến ngày báo cáo</th> 
                    <th rowspan="1"  class="TD_DONVITINH">Số tiền HTLS năm 2022 + 2023 được giao theo kế hoạch</th>
                    <th rowspan="1"  class="TD_DONVITINH">Số tiền HTLS Thừa/thiếu</th>
                    <th rowspan="1"  class="TD_DONVITINH">Dự kiến số ngày còn được HTLS</th>                    
                </tr>                
                <tr>  
                    <th></th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_POS">1</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_CHITIEU">2</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_DONVITINH">3</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">4</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">5</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">6</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">7</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">8=6-4</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">9=7-5</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">10 = 8+9</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">11=5-7</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">12 = 11-10-6</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_DONVITINH">13 = 12/(5+7)</th>
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
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"/>                                   
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 number TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D6" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 number TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"
                                   />
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D7" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 number TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D8" />"  id="D8<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 number TEN_KH" onfocus="this.select()"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           ;
                                           autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D9" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D10" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"/>
                        </td>       
                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D11" />"  id="D11<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D11 number TEN_KH" onfocus="this.select()"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           ;
                                           autoEvaluate()"/>
                        </td>
                        <s:if test="D12 < 0">
                            <td align = "right" class="TD_DONVITINH">
                                <input type="text" value="<s:property  value="D12" />" style="color: red"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D12 number TEN_KH" onfocus="this.select()"                                       
                                       readonly="readonly"/>
                            </td>
                        </s:if>
                        <s:else>
                            <td align = "right" class="TD_DONVITINH">
                                <input type="text" value="<s:property  value="D12" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D12 number TEN_KH" onfocus="this.select()"                                       
                                       readonly="readonly"/>
                            </td>
                        </s:else>

                        <td align = "right" class="TD_DONVITINH">
                            <input type="text" value="<s:property  value="D13" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 number TEN_KH" onfocus="this.select()"                                       
                                   readonly="readonly"/>
                        </td>
                    </tr>


                </s:iterator>
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
