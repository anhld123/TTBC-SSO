<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<s:head/>
<sj:head/>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <style>
            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }       
            .cls-over{
                overflow-y: scroll;
                height: 70vh;
                overflow-x: scroll;
            }
            .editDelete{
                border: 1px solid #999;
            }

            .editDelete td,th{
                border: 1px solid #999;
            }
             #idTitle{
                font: 14px Arial, Helvetica, sans-serif;
                font-weight: bold;
                color: #0077b3;
                text-align: center;
             }
        </style>
        <script src="js/jquery.number.js"></script>        
        <script type="text/javascript" src="BCQT/javascript/jquery-ui.min.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
//                $(".datepicker_month").datepicker({dateFormat: 'mm/yy'});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
//                $('.number2').number(true, 2);
                $(".TD_STT").css({"width": "30px"});
                $(".TD_MAKH").css({"width": "70px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "130px"});
                $(".TD_SOKU").css({"width": "120px"});
                $(".TD_SOTIEN").css({"width": "90px"});
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
            function closeSelf() {
                window.close();
                return true;
            }
            
             function autoEvaluate() {

                var arrCot = [".D13", ".D22", ".D23"]; //Luu cac cot cua du lieu can tinh toan
                for (var i = 0; i < 50; i++) {
                    //8=2+4-6
                    $(".D23").eq(i).val(parseFloat($(".D13").eq(i).val()) - parseFloat($(".D22").eq(i).val()));

                }
                //                              
            }
            
//            function autoPlus(idx) {
//                $('.number').number(true, 0);
//               $('.number2').number(true, 2);
//           }
//           autoPlus(11);

        </script>
        <!--<link href="css/css/style.css" rel="stylesheet" type="text/css"/>-->
        <link href="css/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!--<div id="container_popup">-->
        <s:form name="frmDieuchinhKH_%{khoadc}" id="frmDieuchinhKH_%{khoadc}"  theme="simple">              
            <div id="divChiTieu" style="text-align: center;">   
                <s:hidden name="ngay_bc" id="ngay_bc"/>
                <s:hidden name="masothue" id="masothue"/>                   
                <s:hidden name="tendn" id="tendn"/>  
                <s:hidden name="thangbc" id="thangbc"/>  
                <s:hidden name="khoadc" id="khoadc"/>  
                
                <div id="idTitle" >ĐIỀU CHỈNH ĐỀ NGHỊ CHO VAY</div>
                <hr/>
                <%--<s:iterator value="#attr.lstDulieuNt50" var="modelDcpt" status="rowstatus">--%>                        
                    <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 1730px; max-height:45vh">
                    <table class="editDelete cls-table" >
                        <tr height="50px">      
                            <!--<th  rowspan="2" class="TD_MAKH">Điều chỉnh phê duyệt cho vay</th>--> 
                            <!--<th  rowspan="2" class="TD_MAKH">DS người lao động</th>-->
                            <th rowspan="2" class="TD_MAKH">Mã doanh nghiệp</th>                           
                            <th rowspan="2" class="TD_TENKH">Tên doanh nghiệp</th>  
                            <th rowspan="2" class="TD_MAKH">Mã số thuế</th>    
                            
                            
                               
                            <th rowspan="2" class="TD_NGAY">Tháng vay</th>
                            <!--<th colspan="5">Đề nghị theo hồ sơ vay vốn</th>-->      
<!--                            <th  rowspan="2" class="TD_MAKH">Kế hoạch dư nợ sau giao chỉ tiêu</th>   
                            <th  rowspan="2" class="TD_MAKH">Ngày lương theo HĐ</th>    
                            <th  rowspan="2" class="TD_CHITIEU">Ghi chú</th>    
-->
                            <th colspan="3">Đề nghị theo hồ sơ vay vốn - Điều chỉnh <span style="color:red">+/-</span></th>   
                            <th rowspan="2" class="TD_MAKH">Giấy đề nghị</th>
                            <th rowspan="2" class="TD_NGAY">Ngày đề nghị</th>
                            <!--<th rowspan="2" class="TD_NGAY">Mức lương vùng</th>-->
                            <th rowspan="2" class="TD_NGAY">TT</th>
                        </tr>         
                        <tr>
<!--                            <th class="TD_NGAY">Tháng vay</th>
                            <th class="TD_MAKH">Đối tượng thụ hưởng</th>
                            <th  class="TD_NGAY">Tổng số lao động được đề nghị vay để trả lương</th>    
                            <th  class="TD_NGAY">Trong đó, số lao động mới (nếu có)</th>
                            <th class="TD_MAKH">Số tiền đề nghị vay</th>-->
                            
                            <!--<th class="TD_NGAY">Ngày phê duyệt</th>-->
                            <th  class="TD_NGAY">Tổng số lao động được đề nghị vay để trả lương <span style="color:red">*</span></th>    
                            <th  class="TD_NGAY">Trong đó, số lao động mới (nếu có) <span style="color:red">*</span></th>
                            <th class="TD_MAKH">Số tiền đề nghị vay <span style="color:red">*</span></th>                         
                        </tr>
                        <tr>
                            <!--<td style="text-align: center"></td>-->
                            <!--<td style="text-align: center"></td>--> 
                            <td style="text-align: center">1</td>
                            <td style="text-align: center">2</td>
                            <td style="text-align: center">3</td>
<!--                            <td style="text-align: center">4</td>
                            <td style="text-align: center">5</td>
                            <td style="text-align: center">6</td>
                            <td style="text-align: center">7</td>
                            <td style="text-align: center">20</td>
                            <td style="text-align: center">21</td>-->
                           
                            <!--<td style="text-align: center">10</td>-->
                            <td style="text-align: center">11</td>
                            <!--<td style="text-align: center">15</td>-->
                            <td style="text-align: center">12</td>
                            <td style="text-align: center">14</td>
                            <td style="text-align: center">13</td>
                            
<!--                            <td style="text-align: center">17</td>
                            <td style="text-align: center">16</td>
                            <td style="text-align: center">18</td>     -->
                            <!--<td style="text-align: center">22</td>-->
<!--                            <td style="text-align: center">23</td>
                            <td style="text-align: center">24</td>
                            <td style="text-align: center">25</td>-->
                             <td style="text-align: center">8</td>
                            <td style="text-align: center">9</td>
                            <td style="text-align: center"></td>
                           
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt50" var="modelView" status="rowstatus">                             
                            <tr> 
                                <!--Doanh nghiệp đã được duyệt-->
                                <s:if test="NHAPTAY.equalsIgnoreCase(1)"> 
                                  
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D1" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                        <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>" />
                                    </td>                                
                                    <td align = "right" class="TD_TENKH" >
                                        <input type="text"   value="<s:property  value="D2" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();" style="background: 	#C0C0C0 !important;" 
                                               readonly="readonly"/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D3" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>
                                     
<!--                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D10" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>-->
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D11" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>
                                    
<!--                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"  value="<s:property  value="D15" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 TEN_KH number" onfocus="this.select();" style="background: 	#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>-->
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D12" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number" onfocus="this.select();" style="background: 	#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td> 
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D14" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 TEN_KH number" onfocus="this.select();" style="background: 	#C0C0C0 !important;" 
                                               readonly="true"/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D13" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 TEN_KH number" onfocus="this.select();" style="background: 	#C0C0C0 !important;"
                                                readonly="true"/>
                                    </td> 
                                    
                                    
<!--                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D22" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D22" class="D13 TEN_KH number" onfocus="this.select();" style="background: 	#C0C0C0 !important;"
                                                readonly="true"/>
                                    </td> -->
<!--                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D23" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D23" class="D13 TEN_KH number" onfocus="this.select();" style="background: 	#C0C0C0 !important;"
                                                readonly="true"/>
                                    </td> 
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D24" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D24" class="D13 TEN_KH number" onfocus="this.select();" style="background: 	#C0C0C0 !important;"
                                                readonly="true"/>
                                    </td> 
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D25" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D25" class="D13 TEN_KH number" onfocus="this.select();" style="background: 	#C0C0C0 !important;"
                                                readonly="true"/>
                                    </td> -->
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D8" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>
                                     <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D9" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td> 
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D37" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D37" class="D14 TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"
                                               />
                                    </td>
                                </s:if>
                                    <!--Doanh nghiệp chưa duyệt-->
                                <s:else>
                                   
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D1" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();"
                                               readonly="readonly" style="background: 	#C0C0C0 !important;"/>
                                        <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>" />
                                        
                                    </td>                                
                                    <td align = "right" class="TD_TENKH" >
                                        <input type="text"   value="<s:property  value="D2" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();" style="background: 	#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D3" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();" style="background: 	#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>
                                   
<!--                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D10" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH D0" onfocus="this.select();" style="background: 	#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td> -->
                                    <td align = "center" class="TD_NGAY">
                                        <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0 "  style="background: 	#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td> 
<!--                                     <td align = "center" class="TD_NGAY">
                                        <input type="text" value="<s:property  value="D15" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D15" class="TEN_KH D0 "  style="background: 	#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td> -->
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D12" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number" onfocus="this.select();" />
                                    </td> 
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D14" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 TEN_KH number" onfocus="this.select();" />
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D13" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 TEN_KH number" onfocus="this.select();" />
                                    </td> 
                                    
                                   
<!--                                    <td align = "center" class="TD_NGAY">
                                        <input type="text" value="<s:property  value="D22" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" class="TEN_KH D0 datepicker" placeholder="dd/MM/yyyy" />
                                    </td>  -->
<!--                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D23" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D23" class="D13 TEN_KH number" onfocus="this.select();" 
                                                />
                                    </td> 
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D24" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D24" class="D13 TEN_KH number" onfocus="this.select();" 
                                                />
                                    </td> 
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D25" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D25" class="D13 TEN_KH number" onfocus="this.select();" 
                                                />
                                    </td> -->
                                     <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D8" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH D0" onfocus="this.select();" style="background: 	#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td> 
                                    <td align = "center" class="TD_NGAY">
                                        <input type="text" value="<s:property  value="D9" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH D0"  style="background: 	#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>  
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D37" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D37" class="D14 TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"
                                               />
                                    </td>
                                </s:else>
                                
                                
                                
                            </tr>                                                                                                                                                                                   
                        </s:iterator>
                    </table>        
                </div>
            </div>

                <%--</s:iterator>--%>                                        
            </div>
                
             <table  border="0px !important;" class="editDelete" align="center"> 
                                <tr align="center" height="3px">                                    
                                </tr>
                                <tr align="center">                                       
                                
                                    <td width="35%">
                                        <div id="content_div"></div>
                                    </td>   
                                                                     
                                        <td  align="center">
                                        <div id="button_div" <s:property value="disabled" /> >
                                            <s:url id="edit_url" action="saveDieuchinhPheduyet" escapeAmp="false"
                                                   var="update_url">
                                                <s:param name="proc">update</s:param>  
                                            </s:url>                        
                                            <sj:a id="update_button_id"  href="%{#update_url}" 
                                                  targets="content_div"
                                                  formIds="frmDieuchinhKH_%{khoadc}"    
                                                  onBeforeTopics="before-next"
                                                  button="false"
                                                  cssClass="metroButtonStyle"   
                                                  >     
                                                Cập nhật
                                            </sj:a>
                                        </div>
                                    </td>                                     
                                    
                                
                                <td align="center">
                                        <sj:submit id="idClose" cssClass="metroButtonStyle"  name="nameClose" value="Thoát" onclick="closeSelf()"
                                                   cssStyle="height:31px;width:95px"></sj:submit>
                                </td>
                                
                                <td width="35%"></td>                                    
                                
                                
                            </tr>
                            </table>      

        </s:form>
        <!--</div>-->
    </body>
   
</html>
 
         