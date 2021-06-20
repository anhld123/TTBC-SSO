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
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_THUTU").css({"width": "70px"});
                $(".TD_CHITIEU").css({"width": "120px"});
                $(".TD_SOTK").css({"width": "210px"});
                $(".TD_TENKH").css({"width": "320px"});
                $(".TD_SP").css({"width": "70px"});
                $(".TD_SOTIEN").css({"width": "70px"});                
                $(".TD_GHICHU").css({"width": "200px"});

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
                    var arrCot = [".D5",".D6",".D7"]; //Luu cac cot cua du lieu can tinh toan
                    for(var i=0; i<199; i++){
                        //8=2+4-6
                        $(".D6").eq(i).val(parseFloat($(".D5").eq(i).val()) * parseFloat($(".D7").eq(i).val()));
                    }         
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
                THEO DÕI THỰC HIỆN KẾ HOẠCH HUY ĐỘNG TIẾT KIỆM QUA TỔ TK&VV
            </div>
            <s:hidden name="khoa_nhaptaycn"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablems01" style="width: 85%"  align="center">
                <tr height="25">                    
                    <th  class="TD_CHITIEU">Mã tổ</th>
                    <th  class="TD_SOTK">Tên tổ trưởng</th> 
                    <th  class="TD_THUTU">Đơn vị ủy thác</th>
                    <th  class="TD_THUTU">Số KH dư nợ</th>
                    <th  class="TD_SOTK">Số tiền gửi TK hàng tháng/ hộ quy ước</th> 
                    <th  class="TD_SOTK">Kế hoạch giao tăng 2018</th>                                        
                </tr>                             
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                        <tr height="22">                             
                        <td align = "right" class="TD_THUTU">
                            <input type="text" value="<s:property  value="D1" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                   readonly="readonly"/>
                        </td>    
                        <td align = "right" class="TD_CHITIEU">
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class=" <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_THUTU">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D0 <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()" readonly="readonly"/>
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" value="<s:property  value="D4"/>"/>                                    
                        </td>
                        <td align = "right" class="TD_SOTK">
                            <input type="text" value="<s:property  value="D7" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()"/>
                        </td> 
                        
                        <td align = "right" class="TD_SOTK">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()"/>
                        </td> 
                        <td align = "right" class="TD_SOTK">
                            <input type="text" value="<s:property  value="D6" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D6 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()"/>
                        </td> 
                    </tr>                    
                    
                </s:iterator>
            </table>
            <p></p>          
            
            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
            
        <s:form action="id_%{khoa_nhaptaycn}.action" id="paginationForm">
        <s:iterator value="poscd" status="row">
            <s:hidden name="poscd[%{#row.index}]" />
        </s:iterator>
        
        <%--<%@ include file="/nhaptay-cn/pagination.jsp" %>--%>
        <sj:submit value="submit" id="idSubmit" name="idSubmit" targets="divExportReport" cssStyle="display: none" 
                   onBeforeTopics="batdauloaddata" onCompleteTopics="hoanthanhloaddata"/>
    </s:form>    
        <div id="luu_thanhcong"></div>
    </body>
</html>
