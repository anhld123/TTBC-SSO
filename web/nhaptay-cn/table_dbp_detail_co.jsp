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
            text-decoration: underline;
        }
        .BOLD
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }
        #divMuc{
            font: 12px Arial, Helvetica, sans-serif;
            text-align: left;
            color: red;
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
                $(".TD_MONVAY").css({"width": "90px"});
                $(".TD_THUTU").css({"width": "140px"});
                $(".TD_SOKU").css({"width": "190px"});
                $(".TD_SOTK").css({"width": "210px"});
                $(".TD_TENKH").css({"width": "250px"});
                $(".TD_NGAYBC").css({"width": "70px"});
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
                    var arrCot = [".D2",".D3",".D4",".D5",".D13",".D14",".D15",".D16",".D17",".D18",".D19",".D20"]; //Luu cac cot cua du lieu can tinh toan
                    for(var i=0; i<2; i++){                        
                        //8=2+4-6
                        $(".D23").eq(i).val(parseFloat($(".D2").eq(i).val()) + parseFloat($(".D3").eq(i).val()) + parseFloat($(".D4").eq(i).val())
                                - (parseFloat($(".D13").eq(i).val()) + parseFloat($(".D15").eq(i).val()) + parseFloat($(".D17").eq(i).val())+
                                parseFloat($(".D18").eq(i).val()) + parseFloat($(".D19").eq(i).val()) + parseFloat($(".D21").eq(i).val())
                                ));
                        $(".D24").eq(i).val(parseFloat($(".D5").eq(i).val())
                                - (parseFloat($(".D14").eq(i).val()) + parseFloat($(".D16").eq(i).val())  + parseFloat($(".D20").eq(i).val()) + parseFloat($(".D22").eq(i).val())));
                    
                    }         
                }                        
                </script>       
    </head>
    <body>
        <s:form id="idBDP_CO">          
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                THÔNG TIN HỘ VAY BỎ ĐI KHỎI ĐỊA PHƯƠNG CÓ VB ĐỀ NGHỊ PHỐI HỢP
            </div>
            <s:hidden name="khoa_nhaptaycn"/> 
            <s:hidden name="ngay_bc"/>
            <div  id="divMuc" style="width: 85%"  align="center">
                                1. Thông tin chung
            </div>
            
             <s:iterator value="#attr.lstNt" var="modelView" status="rowstatus">                 
            
            <div  id="divMuc" style="width: 85%"  align="center">
                                2. Thông tin thu nợ
            </div>
            <table border="1" class="editDelete" id="tablems01" style="width: 90%"  align="center">
                <tr height="25">   
                    <th rowspan ="2" class="TD_THUTU">Nhóm nhận nợ</th> 
                    <th rowspan ="2" class="TD_SOKU">Pos chuyển đến</th> 
                    <th colspan ="2" class="TD_SOKU">Loại thu 01</th>
                    <th colspan ="2"  class="TD_SOKU">Loại thu 02</th> 
                    <th colspan ="4"  class="TD_SOKU">Bàn giao</th> 
                    <th colspan ="2" class="TD_SOKU">Xóa nợ</th>                    
                    <th colspan ="2" class="TD_SOKU">Số tiền chưa thu</th>                                                                                                                      
                </tr>  
                <tr>
                    <th class="TD_SOKU">Gốc</th>
                    <th class="TD_SOKU">Lãi</th>
                    <th class="TD_SOKU">Gốc</th>
                    <th class="TD_SOKU">Lãi</th>
                    <th class="TD_SOKU">Trong hạn</th>
                    <th class="TD_SOKU">Quá hạn</th>
                    <th class="TD_SOKU">Khoanh</th>
                    <th class="TD_SOKU">Lãi</th>
                    <th class="TD_SOKU">Gốc</th>
                    <th class="TD_SOKU">Lãi</th>
                    <th class="TD_SOKU">Gốc</th>
                    <th class="TD_SOKU">Lãi</th>
                </tr>
                        
                        <tr height="22">  
                        <td align = "right" class="TD_THUTU">
                            <s:select id="trangthai" name="lstNt[%{#rowstatus.index}].D11"
                                              list="#{'0':'--Nhóm nhận nợ--','1':'Nhận nợ và trả 1 phần','2':'Nhận nợ và tất toán','3':'Nhận nợ nhưng chưa trả nợ','4':'Không tìm được hộ vay','5':'Trường hợp khác'}"
                                              cssStyle="width: 140px; vertical-align: middle; background-color: #FFCCBA;" />
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #FFCCBA;" value="<s:property  value="D12" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D12"  onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #FFCCBA;" value="<s:property  value="D13" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td> 
                            
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #FFCCBA;" value="<s:property  value="D14" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 number2 <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #FFCCBA;" value="<s:property  value="D15" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 number2 <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>                        
                        
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #FFCCBA;" value="<s:property  value="D16" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D16 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text"  style="background-color: #FFCCBA;" value="<s:property  value="D17" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D17" class="D17 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" />
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text"  style="background-color: #FFCCBA;" value="<s:property  value="D18" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D18" class="D18 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" />
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #FFCCBA;" value="<s:property  value="D19" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D19" class="D19 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" />
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #FFCCBA;" value="<s:property  value="D20" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D20" class="D20 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" />
                        </td> 
                         <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #FFCCBA;" value="<s:property  value="D21" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D21" class="D21 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" />
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #FFCCBA;" value="<s:property  value="D22" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D22" class="D22 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" />
                        </td>    
                        <td align = "right" class="TD_SOKU">
                            <input type="text"  value="<s:property  value="D23" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D23" class="D23 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text"  value="<s:property  value="D24" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D24" class="D24 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                        </td>
                        
                    </tr>                                                        
            </table>
                                    <div style="height:5px;"></div>  
            <table border="1" class="editDelete" id="tablems01" style="width: 90%"  align="center">
                <tr height="25">                    
                    <th  class="TD_CHITIEU">Nguyên nhân chưa thu được</th>                                                                               
                </tr>                                            
                        <tr height="22">                             
                        <td align = "right" class="TD_THUTU">
                            <textarea id="sNgnhan_Clech" value="<s:property value='D25'/>"
                                              name="lstNt[<s:property  value="%{#rowstatus.index}" />].D25"
                                              style="width: 100%;background-color: #FFCCBA;" rows="3"><s:property value='D25'/></textarea>
                        </td>                                                    
                    </tr>                                                        
                <tr>
                <td colspan="2" align="right">
                    <div id="button_div" <s:property value="disabled" /> >
                        <s:url id="edit_url" action="saveBDPCO" escapeAmp="false"
                               var="update_url">
                            <s:param name="proc">update</s:param>  
                        </s:url>                        
                        <sj:a id="update_button_id"  href="%{#update_url}" 
                              targets="content_div"
                              formIds="branch_edit_formsms"    
                              onBeforeTopics="before-next"
                              button="false"
                              cssClass="metroButtonStyle"   
                              >     

                            Cập nhật
                        </sj:a>
                    </div>
                </td>       

            </tr>            
            </table>     
                        
                        
            </s:iterator>            
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
