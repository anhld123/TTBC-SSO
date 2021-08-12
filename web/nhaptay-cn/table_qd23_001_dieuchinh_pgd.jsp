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

                var arrCot = [".D6", ".D7", ".D8"]; //Luu cac cot cua du lieu can tinh toan
                for (var i = 0; i < 50; i++) {
                    //8=2+4-6
                    $(".D8").eq(i).val(parseFloat($(".D6").eq(i).val()) - parseFloat($(".D7").eq(i).val()));

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
        <s:form name="frmDieuchinhKH" id="frmDieuchinhKH"  theme="simple">              
            <div id="divChiTieu" style="text-align: center;">   
                <s:hidden name="ngay_bc" id="ngay_bc"/>
                <s:hidden name="masothue" id="masothue"/>                   
                <s:hidden name="tendn" id="tendn"/>  

                <span id="idTitle" >ĐIỀU CHỈNH GIẢM KẾ HOẠCH</span>

                <hr/>
                <%--<s:iterator value="#attr.lstDulieuNt" var="modelDcpt" status="rowstatus">--%>                        
                    <div class="cls-over">
                        <div id="scrolling_table_1"  style="width: 1510px; max-height:45vh">
                            <table class="editDelete cls-table" >
                                <tr height="50px">      
                                    <th  class="TD_MAKH">Mã số thuế doanh nghiệp</th>                           
                                    <th  class="TD_TENKH">Tên doanh nghiệp</th>                           
                                    <th  class="TD_MAKH">Giấy đề nghị</th>
                                    <th  class="TD_NGAY">Ngày đề nghị</th>
                                    <th  class="TD_NGAY">Lần điều chỉnh</th>
                                    <!--<th  class="TD_NGAY">Tổng số lao động được đề nghị vay để trả lương</th>-->    
                                    <th  class="TD_MAKH">Tổng số tiền được phê duyệt cho vay</th>
                                    <th  class="TD_MAKH">Số tiền đã giải ngân</th>
                                    <th  class="TD_MAKH">Số tiền tồn không giải ngân hết</th>
                                    <th  class="TD_CHITIEU">Nguyên nhân không giải ngân hết</th>
                                    <th   class="TD_CHITIEU">Ghi chú</th>                                    
                                </tr>         
                               
                                <tr>

                                    <td style="text-align: center">1</td>
                                    <td style="text-align: center">2</td>
                                    <td style="text-align: center">3</td>
                                    <td style="text-align: center">4</td>
                                    <td style="text-align: center">5</td>
                                    <td style="text-align: center">6</td>
                                    <td style="text-align: center">7</td>
                                    <td style="text-align: center">8</td>
                                    <td style="text-align: center">9</td>
                                    <td style="text-align: center">10</td>
                            
                                     

                                </tr>
                                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                                    <tr> 
                                        <!--Doanh nghiệp đã được duyệt-->
                                        <s:if test="D30.equalsIgnoreCase(1)"> 

                                            <td align = "right" class="TD_MAKH" >
                                                <input type="text"   value="<s:property  value="D1" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();"
                                                       readonly="true"/>
                                            </td>                                
                                            <td align = "right" class="TD_TENKH" >
                                                <input type="text"   value="<s:property  value="D2" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                                            </td>
                                            <td align = "right" class="TD_MAKH" >
                                                <input type="text"   value="<s:property  value="D3" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
                                            </td>
                                            <td align = "right" class="TD_MAKH" >
                                                <input type="text"   value="<s:property  value="D4" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
                                            </td>
                                            <td align = "right" class="TD_TENKH" >
                                                <input type="text"   value="<s:property  value="D5" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH D0 number" onfocus="this.select();" readonly="true"/>                                                                        
                                            </td>
                                            <td align = "right" class="TD_MAKH" >
                                                <input type="text"   value="<s:property  value="D6" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();" readonly="true" />                                                                        
                                            </td>

                                            <td align = "right" class="TD_MAKH" >
                                                <input type="text"   value="<s:property  value="D7" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number" onfocus="this.select();" readonly="true"/>                                                                        
                                            </td>
                                            <td align = "right" class="TD_MAKH" >
                                                <input type="text"   value="<s:property  value="D8" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH number" onfocus="this.select();" readonly="true"/>
                                            </td> 
                                            <td align = "right" class="TD_CHITIEU" >
                                                <input type="text"   value="<s:property  value="D9" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH" onfocus="this.select();" readonly="true"/>                                                                        
                                            </td> 
                                             <td align = "right" class="TD_CHITIEU" >
                                                <input type="text"   value="<s:property  value="D10" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH" onfocus="this.select();" readonly="true"/>                                                                        
                                            </td> 
                                           
                                        </s:if>
                                        <!--Doanh nghiệp chưa duyệt-->
                                        <s:else>
                                            

                                            <td align = "right" class="TD_MAKH" >
                                                <input type="text"   value="<s:property  value="D1" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();"
                                                       readonly="true"/>
                                            </td>                                
                                            <td align = "right" class="TD_TENKH" >
                                                <input type="text"   value="<s:property  value="D2" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                                            </td>
                                            <td align = "right" class="TD_MAKH" >
                                                <input type="text"   value="<s:property  value="D3" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();"/>
                                            </td>
                                            <td align = "center" class="TD_NGAY">
                                                <input type="text" value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0 datepicker " placeholder="DD/MM/yyyy"/>
                                            </td> 
                                            <td align = "right" class="TD_MAKH" >
                                                <input type="text"   value="<s:property  value="D5" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>                                                                        
                                            </td>
                                            <td align = "center" class="TD_NGAY">
                                                <input type="text" value="<s:property  value="D6" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 TEN_KH number"  readonly="true"
                                                       onblur="autoEvaluate()"/>
                                            </td> 

                                            <td align = "center" class="TD_NGAY">
                                                <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 TEN_KH  number" 
                                                       onblur="autoEvaluate()"/>
                                            </td> 
                                            <td align = "right" class="TD_MAKH" >
                                                <input type="text"   value="<s:property  value="D8" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8 TEN_KH number" onfocus="this.select();" readonly="true"
                                                       onblur="autoEvaluate()"/>
                                            </td> 
                                            <td align = "center" class="TD_CHITIEU">
                                                <input type="text" value="<s:property  value="D9" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH" />
                                            </td>  
                                           <td align = "center" class="TD_CHITIEU">
                                                <input type="text" value="<s:property  value="D10" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH" />
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
                                            <s:url id="edit_url" action="saveDieuchinhKH" escapeAmp="false"
                                                   var="update_url">
                                                <s:param name="proc">update</s:param>  
                                            </s:url>                        
                                            <sj:a id="update_button_id"  href="%{#update_url}" 
                                                  targets="content_div"
                                                  formIds="frmDieuchinhKH"    
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
 
         