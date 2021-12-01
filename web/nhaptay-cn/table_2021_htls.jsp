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
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $(".datepicker_month").datepicker({dateFormat: 'mm/yy'});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_STT").css({"width": "20px"});
                $(".TD_MAKH").css({"width": "70px"});
                $(".TD_TOTIEN").css({"width": "90px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "130px"});
                $(".TD_SOKU").css({"width": "120px"});
                $(".TD_NGAY").css({"width": "40px"});
                $(".TD_CHITIEU").css({"width": "300px"});
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
              
            }

        </script>

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
            .editDelete input:readonly {
                background-color: red;
            }

            #divTitle{
                margin-bottom: 10px;
                color: #e67300;
            }


            *{
                font-family: Tahoma, Arial, Helvetica, sans-serif;
                font-size: 12px;
            }
            #tblTable {

                border-collapse: collapse;
                width: 100%;
            }

            #tblTable td, #tblTable th {
                border: 1px solid #8a8a5c;
                padding: 4px;
            }

            #tblTable tr:nth-child(even){background-color: #f2f2f2;}

            #tblTable tr:hover {background-color: #ddd;}

            #tblTable th {
                padding-top: 6px;
                padding-bottom: 6px;
                text-align: center;
                background-color: #FFCCBA;
            }

            .TEN_KH{
                border: 0px !important;
                outline: none;
            }

            #loadDatatmp, #idsaveDatatmp{
                cursor: pointer;
                display: inline-block;
                min-height: 1em;
                outline: none;
                border: none;
                vertical-align: baseline;
                background: #fafafa linear-gradient(rgba(0, 0, 0, 0), rgba(0, 0, 0, 0.09));
                color: rgba(0, 0, 0, 0.6);
                padding: 8px 24px 8px 24px;
                text-transform: none;
                text-shadow: none;
                font-weight: bold;
                line-height: 1em;
                font-style: normal;
                text-align: center;
                text-decoration: none;
                border-radius: 0.28571429rem;
                box-shadow: 0px 0px 0px 1px rgb(34 36 38 / 15%) inset, 0px 0em 0px 0px rgb(34 36 38 / 15%) inset;
            }

        </style>
        <link href="css/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body style="font-family: ">
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            </br>
            <div id="divTitle">
                XÁC NHẬN GIẢM LÃI CHO VAY CÁC CHƯƠNG TRÌNH TÍN DỤNG CHÍNH SÁCH THEO QUYẾT ĐỊNH 1990/QĐ-TTg CỦA THỦ TƯỚNG CHÍNH PHỦ
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nhaptaycn"/>
            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 98%; max-height:45vh">
                    <table id="tblTable">
                        <tr>      
                            <th rowspan="2" class="TD_STT">STT</th>                           
                            <!--<th rowspan="2" class="TD_TOTIEN">CIF</th>-->  
                            <th rowspan="2" class="TD_TENKH">Tên KH</th>  
                            <th rowspan="2" class="TD_TENKH">Mã khoản vay</th>    
                            <th rowspan="2"  class="TD_NGAY">Chương trình tín dụng</th>                             
                            <th rowspan="2"  class="TD_NGAY">Lãi suất</th>   
                            <th rowspan="2"  class="TD_NGAY">Trạng thái món vay</th>   
                            <th colspan="3"  class="TD_MAKH">Dư nợ</th>                             
                            <th colspan="3"  class="TD_MAKH">Lãi giảm các tháng trong năm 2021</th>                             
                            <!--<th rowspan="2"  class="TD_MAKH">Tổng số tiền lãi được giảm</th>-->            
                            
                            <th rowspan="2"  class="TD_MAKH">Phân loại RPA hoặc Phải trả</th>  
                            <th rowspan="2"  class="TD_MAKH">Đơn vị xác nhận số tiền giảm lãi</th>                              
                            <th rowspan="2"  class="TD_MAKH">Số tham chiếu giao dịch hạch toán giảm lãi trên Intellect</th>  
                            <th rowspan="2"  class="TD_MAKH">Ngày hạch toán giảm lãi</th>  
                            <th rowspan="2"  class="TD_MAKH">Số tiền hạch toán giảm lãi</th>  
                            <!--<th rowspan="2"  class="TD_MAKH">Số tiền lãi giảm chuyển vào RPA</th>-->  
                            <th rowspan="2"  class="TD_MAKH">Số tiền lãi giảm chuyển vào CASA</th>  
                            <th rowspan="2"  class="TD_MAKH">Số tiền lãi giảm chuyển vào CASH</th>  
                            
                            <th rowspan="2"  class="TD_MAKH">Số bút toán lãi giảm vào CASA hoặc CASH</th>  
                            
                            
                        </tr>         
                        <tr>
                            <th  class="TD_TOTIEN">Trong hạn</th>
                            <th  class="TD_TOTIEN">Quá hạn</th>
                            <th  class="TD_TOTIEN">Khoanh</th>
                            <th  class="TD_NGAY">Tháng 10</th>                                                        
                            <th  class="TD_NGAY">Tháng 11</th>                                                        
                            <th  class="TD_NGAY">Tháng 12</th>                                                        
                        </tr>
                        <tr style="font-style: italic;">
                            <td style="text-align: center">(1)</td>
                            <!--<td style="text-align: center">(2)</td>-->
                            <td style="text-align: center">(2)</td>
                            <td style="text-align: center">(3)</td>
                            <td style="text-align: center">(4)</td>
                            <td style="text-align: center">(5)</td>
                            <td style="text-align: center">(6)</td>
                            <td style="text-align: center">(7)</td>
                            <td style="text-align: center">(8)</td>
                            <td style="text-align: center">(9)</td>
                            <td style="text-align: center">(10)</td>
                            <td style="text-align: center">(11)</td>
                            <td style="text-align: center">(12)</td>
                            <!--<td style="text-align: center">(13)</td>-->
                            <td style="text-align: center">(14)</td>
                            <!--<td style="text-align: center">(15)</td>-->
                            <th width="25" class="TD_CHECKBOX" >
                                            <s:checkbox id ="allCheck_pgd" name="allCheck"/></th> 
                            <td style="text-align: center">(16)</td>
                            <td style="text-align: center">(17)</td>
                            <td style="text-align: center">(18)</td>
                            <!--<td style="text-align: center">(19)</td>-->
                            <td style="text-align: center">(20)</td>
                            <td style="text-align: center">(21)</td>
                            <td style="text-align: center">(22)</td>
                            
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>                               
                                <td align = "right" class="TD_STT" >
                                    <input type="text"   value="<s:property  value="THUTU" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="D0 TEN_KH " onfocus="this.select();"/>                                        
                                </td>  


                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"   value="<s:property  value="D50" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D50" class="D0 TEN_KH " onfocus="this.select();" 
                                           onblur="autoEvaluate()"/> 
                                </td>
                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"   value="<s:property  value="D3" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D0 TEN_KH " onfocus="this.select();"
                                           onblur="autoEvaluate()"/>
                                </td>

                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D9" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D0 TEN_KH" onfocus="this.select();" />
                                </td>

                                <!--lai suat-->
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D8" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D0 TEN_KH " onfocus="this.select();" />
                                </td>
                                 <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D12" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D0 TEN_KH " onfocus="this.select();" />
                                </td>
                                
                                <!--Du no: trong qua khoanh-->
                                <td align = "right" class="TD_TOTIEN" >
                                    <input type="text"   value="<s:property  value="D5" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                <td align = "right" class="TD_TOTIEN" >
                                    <input type="text"   value="<s:property  value="D6" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                <td align = "right" class="TD_TOTIEN" >
                                    <input type="text"   value="<s:property  value="D7" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                <!--Lai giam cac tháng-->
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D18" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D19" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D20" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D24" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24" class="TEN_KH D0" onfocus="this.select();" />
                                </td>
                                <!--D25 xac nhận giảm-->
                                 <td align = "center" class="TD_CHECKBOX"> 
                                                <s:checkbox id ="%{#rowstatus.index}" cssClass="checkboxsp" name="lstsaveNT_SP[%{#rowstatus.index}].MA" fieldValue="%{MA}"/>
                                            </td>      
                                <td align = "right" class="TD_SOKU" >
                                    <input type="text"   value="<s:property  value="D26" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D26" class="TEN_KH" onfocus="this.select();" />
                                </td>
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D27" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27" class="TEN_KH" onfocus="this.select();" />
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D28" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                 <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D30" />"  style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D31" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D31" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                 <td align = "right" class="TD_TENKH" >
                                    <input type="text"   value="<s:property  value="D32" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D32" class="TEN_KH " onfocus="this.select();" />
                                </td>
                            </tr>                                                                                                                                                                                   
                        </s:iterator>
                    </table>        
                </div>
            </div>

            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>


</html>

