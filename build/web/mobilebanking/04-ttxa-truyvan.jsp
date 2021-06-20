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
            a {
                color: #0000FF;
            }
            .BOLD
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }
        </style>
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="js/jquery.number.js"></script>
    <script src="js/format_num.js"></script>
    <script>
        $(document).ready(function () {
            $('input.number').css({"text-align": "right"});
            $('.D0').css({"text-align": "center"});
            $('input.number2').css({"text-align": "right"});
//                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
//                $('#ui-datepicker-div').css('clip', 'auto');
            //Cac truong bang so --> se co so truong = 0
            $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
            $('.number2').number(true, 0);
            $(".TD_5").css({"width": "3%"});
            $(".TD_15").css({"width": "12%"});
            $(".TD_6").css({"width": "4%"});
            $(".TD_10").css({"width": "7%"});


        });

        $(document).ready(function () {
            $("#allCheck").change(function () {
                $(".checkbox1").prop('checked', $(this).prop("checked"));
            });
        });

        $('.TEN_KH').focus(function () {
            $(this).closest('tr').addClass('highlight_row');
        });
        $('.TEN_KH').blur(function () {
            $(this).closest('tr').removeClass('highlight_row');
        });
    </script>

    <script>
        function autoEvaluate() {
            //                alert('vao doClick');
            var arrCot = [".D2", ".D3", ".D4"]; //Luu cac cot cua du lieu can tinh toan

            //                  Tinh toan cho 7 dong
            for (var i = 0; i < 99; i++) {
                //8=2+4-6
                $(".D4").eq(i).val(parseFloat($(".D2").eq(i).val()) - parseFloat($(".D3").eq(i).val()));

            }
            //                              
        }
    </script>        
</head>
<body>
    <s:form id="id_sv_%{khoa_muasamts}" action="SAVE_%{khoa_muasamts}" theme="simple">  
        <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
            <input type="hidden" id="<s:property  value="sKey" />" 
                   name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
        </s:iterator>
        <div id="divTitle">
            THÔNG TIN CHI TIẾT VỀ ĐỊA PHƯƠNG XÃ/PHƯỜNG                
        </div>
        <s:hidden name="khoa_muasamts"/>
        <div id="divDonvitinh">
            Đơn vị tính: Đồng
        </div>

        <table border="1" class="editDelete" id="tablemuasamts01" align="center">
<!--            <tr>
                <s:if test="Grade.equalsIgnoreCase('2')">
                    <th  class="TD_5">Mã PGD</th>
                    </s:if> 

                <th rowspan="2"  class="TD_6">Mã nhóm TSCĐ</th>
                <th  rowspan="2" class="TD_15">Tên tài sản</th> 
                <th colspan="3"  class="TD_6">Hiện trạng</th> 
                <th colspan="6"  class="TD_10">Kế hoạch mua sắm</th> 


            </tr>  -->
            <tr>
                <th  class="TD_6">Mã xã</th> 
                <th  class="TD_10">Tên xã</th> 
                <th class="TD_5" >Tổng dân số theo địa bàn cập nhật theo xã/phường</th>
                <th class="TD_5" style="font-weight:bold;">Số lượng khách hàng của NHCSXH theo xã/phường</th>   
                <th class="TD_5" style="font-weight:bold;">Số hộ nghèo của xã/phường</th>   
                <th class="TD_5" style="font-weight:bold;">Số hộ cận nghèo của xã/phường</th>   
                <th class="TD_5" style="font-weight:bold;">Số hộ mới thoát nghèo của xã/phường</th>   
                <th class="TD_5" style="font-weight:bold;">Số hộ dân tộc thiểu số xã/phường</th>   
                <th class="TD_5" style="font-weight:bold;">Tỷ lệ hộ nghèo của xã/phường</th>   

                <th class="TD_5" style="font-weight:bold;">Tỷ lệ hộ cận nghèo của xã/phường</th> 
                <th class="TD_5" style="font-weight:bold;">Tỷ lệ hộ mới thoát nghèo của xã/phường</th> 
                <th class="TD_5" style="font-weight:bold;">Tỷ lệ hộ dân tộc thiểu số xã/phường</th> 
                <th class="TD_5" style="font-weight:bold;">Thông tin ngành kinh tế của xã/phường</th> 
                <th class="TD_11" style="font-weight:bold;">Thông tin khác của xã/phường</th>
                <th  class="TD_10">Trạng thái</th> 
            </tr>
            <tr>
                <th  class="TD_5">(1)</th>
                <th  class="TD_15">(2)</th> 
                <th  class="TD_6">(5)</th> 
                <th  class="TD_10">(6)</th> 
                <th  class="TD_10">(7)</th> 
                <th  class="TD_6">(8)</th> 
                <th  class="TD_10">(9)</th> 
                <th  class="TD_10">(10)</th> 
                <th  class="TD_10">(11)</th> 
                <th  class="TD_10">(12)</th> 
                <th  class="TD_10">(13)</th> 
                <th  class="TD_10">(13)</th> 
                <th  class="TD_10">(13)</th> 
                <th  class="TD_10">(13)</th> 
                <th  class="TD_10">(13)</th> 
                
            </tr>
            <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                <tr height="22">    
                    <td align = "center" class="TD_SOKU">
                                    <a href="javascript:hienthichitiet('<s:property value="D8"/>','<s:property  value="%{#rowstatus.index}" />' )" class="linkKh">
                                        <s:property value='D8'/> 
                                    </a>
                                </td>
                       
                    <td align = "right" class="TD_15">
                        <input type="text" value="<s:property  value="D9" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class=" <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                               readonly="readonly"/>
                        <input type="hidden" value="<s:property  value="D1" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1"/>"/>
                        <input type="hidden" value="<s:property  value="THUTU" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>
                    </td>
                    <td align = "right" class="TD_6">
                        <input type="text" value="<s:property  value="D5" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                               <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>
                               <s:elseif test="D2.equalsIgnoreCase('3')">
                                   readonly="readonly"
                               </s:elseif>
                               />            
                    </td>
                    <td align = "right" class="TD_10">
                        <input type="text" value="<s:property  value="D6" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                               <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>
                               <s:elseif test="D2.equalsIgnoreCase('3')">
                                   readonly="readonly"
                               </s:elseif>/>
                    </td>

                    <td align = "right" class="TD_15">
                        <input type="text" value="<s:property  value="D7" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                               <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>
                               <s:elseif test="D2.equalsIgnoreCase('3')">
                                   readonly="readonly"
                               </s:elseif>/>
                    </td>
                    <td align = "center" class="TD_6">
                        <input type="text" value="<s:property  value="D7" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                               <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td>
                        <td align = "right" class="TD_15">
                            <input type="text" value="<s:property  value="D7" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class=" <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                               <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td>
                        <td align = "right" class="TD_15">
                            <input type="text" value="<s:property  value="D10" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                               <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td>
                        <td align = "right" class="TD_15">
                            <input type="text" value="<s:property  value="D10" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                               <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td>
                        <td align = "right" class="TD_15">
                            <input type="text" value="<s:property  value="D10" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                               <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td>
                        
                        
                        <td align = "right" class="TD_15">
                            <input type="text" value="<s:property  value="D10" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                               <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td>
                        <td align = "right" class="TD_15">
                            <input type="text" value="<s:property  value="D10" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                               <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td>
                        <td align = "center" class="TD_10">
                            <input type="text" value="<s:property  value="D11" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                               <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td>

                    <s:else>
                        <td align = "center" class="TD_10">
                            <input type="text" value="<s:property  value="D12" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class=" <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                            </td>
                    </s:else>


                    <td align = "right" class="TD_15">
                        <input type="text" value="<s:property  value="D13" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                               <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td>
                    </tr>   
                    

            </s:iterator>                
        </table>

        <p></p>          

        <sj:submit id="%{khoa_muasamts}_save" name="%{khoa_muasamts}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                   onCompleteTopics="completediv_ss" cssStyle="display: none"/>
    </s:form>


    <div id="luu_thanhcong"></div>
</body>
</html>

