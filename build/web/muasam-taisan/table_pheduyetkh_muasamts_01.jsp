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
                $(".TD_15").css({"width": "13%"});
                
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
                function autoEvaluate(){
    //                alert('vao doClick');
                    var arrCot = [".D2",".D3",".D4"]; //Luu cac cot cua du lieu can tinh toan

    //                  Tinh toan cho 7 dong
                    for(var i=0; i<99; i++){
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
                KẾT QUẢ PHÊ DUYỆT KẾ HOẠCH MUA SẮM TÀI SẢN <s:if test="Grade.equalsIgnoreCase('1')"><font color="red">(Trạng thái: <s:property  value="StatusInput"/>)</s:if>   
            </div>
            <s:hidden name="khoa_muasamts"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablemuasamts01" align="center">
                <tr>     
                    <s:if test="Grade.equalsIgnoreCase('2')">
                        <th rowspan="2"  class="TD_5">Mã PGD</th>
                    </s:if> 
                    <th rowspan="2"  class="TD_5">Mã nhóm TSCĐ</th>
                    <th rowspan="2" class="TD_15">Tên tài sản</th>  
                    <th colspan="3" class="TD_15">Kế hoạch</th>  
                    <th colspan="3" class="TD_15">Phê duyệt kế hoạch</th>  
                </tr>  
                <tr>
                    <!--<th  class="TD_10">Số lượng hiện có <font color="red"> *</th>--> 
                    <!--<th  class="TD_10">Giá trị còn lại <font color="red"> *</th>--> 
                    <!--<th  class="TD_10">Hiện trạng tài sản <font color="red"> *</th>--> 
                    <th  class="TD_10">Số lượng 
                    <!--<th  class="TD_10">Mục đích, nơi sử dụng <font color="red"> *</th>--> 
                    <!--<th  class="TD_10">Quy cách, cấu hình kỹ thuật <font color="red"> *</th>--> 
                    <th  class="TD_10">Thành tiền 
                    <th  class="TD_10">Nguồn vốn 
                    <!--<th  class="TD_10">Trạng thái--> 
                    <th  class="TD_10">Số lượng <font color="red"> *</th> 
                    <th  class="TD_10">Tổng tiền <font color="red"> *</th> 
                    <th  class="TD_10">Nguồn vốn <font color="red"> *</th>
                </tr>

                <tr>
                    <s:if test="Grade.equalsIgnoreCase('2')">
                        <th  class="TD_6"></th>
                    </s:if> 
                    <th  class="TD_5">(1)</th>
                    <th  class="TD_15">(2)</th> 
                    <th  class="TD_10">(8)</th> 
                    <th  class="TD_10">(11)</th> 
                    <th  class="TD_10">(12)</th> 
                    <!--<th  class="TD_10">(11)</th>--> 
                    <th  class="TD_10">(15)</th> 
                    <th  class="TD_10">(16)</th> 
                    <th  class="TD_10">(17)</th> 
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                        <tr height="22">   
                         <s:if test="Grade.equalsIgnoreCase('2')">
                            <td align = "right" class="TD_6">
                                <input type="text" style="color: red" <s:if test="MA.equalsIgnoreCase('NC11')"> value="<s:property  value="MAPGD" />"  </s:if> 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" class="<s:property value='FONTFORMAT'/> D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                   readonly="readonly"/>
                        </td> 
                        </s:if>     
                        <td align = "right" class="TD_5">
                            <input type="text" value="<s:property  value="MA" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" class="<s:property value='FONTFORMAT'/> D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                   readonly="readonly"/>
                        </td>    
                        <td align = "right" class="TD_15">
                            <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class=" <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   readonly="readonly"/>
                        </td>
<!--                        <td align = "right" class="TD_10">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="number <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td>
                        <td align = "right" class="TD_10">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="number <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                   <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td>
                        
                        <td align = "right" class="TD_15">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                   <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td>-->
                        <td align = "center" class="TD_10">
                            <input type="text" value="<s:property  value="D8" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   readonly="readonly"/>
                        </td>

                        <td align = "center" class="TD_10">
                            <input type="text" value="<s:property  value="D11" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "center" class="TD_10">
                            <input type="text" value="<s:property  value="D12" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class=" <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   readonly="readonly"/>
                        </td>
                        
                        <td align = "center" class="TD_10">
                            <input type="text" value="<s:property  value="D15" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td> 
                        <td align = "center" class="TD_10">
                            <input type="text" value="<s:property  value="D16" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                        </td> 
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <td align = "left" class="TD_10">                                        
                                    <s:select  
                                        id="lstDulieuNt[%{#rowstatus.index}].D17"
                                        name="lstDulieuNt[%{#rowstatus.index}].D17"
                                        list="lstCBNguonVon" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        headerKey="-1"
                                        headerValue="--- Chọn ---"                                    
                                        cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA;">
                                    </s:select>
                                </td> 
                        </s:if>
                        <s:else>
                            <td align = "center" class="TD_10">
                                <input type="text" value="<s:property  value="D17" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class=" <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                       <s:if test="NHAPTAY.equalsIgnoreCase('1')"> readonly="readonly" </s:if>/>
                            </td> 
                        </s:else>        
                    </tr>                    
                    
                </s:iterator>                
            </table>
            
<!--            <div id="divTitle">
                2. Thuyết minh
            </div>
            
            <s:textarea label="thuyetminh" name="thuyetminh"  style="width: 97%" rows="3"/>
            -->
            
            <p></p>          
            
            <sj:submit id="%{khoa_muasamts}_save" name="%{khoa_muasamts}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
            
  
        <div id="luu_thanhcong"></div>
    </body>
</html>
