<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->

<s:head/>
<sj:head/>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script type="text/javascript" src="BCQT/javascript/jquery-ui.min.js"></script>
        <style>
        *{
        font-family: tahoma;
        font-size: 13px;
    }

    table {
        /*width : 100%;*/
        border-top: 1px solid orange;
        border-left: 1px solid #c2c2c2;
        border-right: 1px solid #c2c2c2;
        border-bottom: 1px solid #c2c2c2;
        text-align : center;
        border-collapse : collapse;
    }
    table tr th, table tr td {
        border : 1px solid #c2c2c2;
    }


    table thead th {
        position: -webkit-sticky;
        position : sticky;
        top : 0;
        color: white;
        background-color : #04AA6D;
    }

    /* here is the trick */
    table tbody:nth-of-type(1) tr:nth-of-type(1) td {
        border-top: none !important;
    }
    table thead th {
        border-top: none !important;
        border-bottom: none !important;
        box-shadow: inset 0 0px 0 #c2c2c2,
            inset 0 -1px 0 #c2c2c2;
    }

    table thead th {
        background-clip: padding-box
    }

    table thead { position: sticky; top: 0; z-index: 1; }

    th, td {
        text-align: left;
        border: 1px solid #c2c2c2;
        text-align: center;
        padding: 3px;
    }

    th{
        padding: 8px;
    }
    .sttCol>td{
        font-style: italic;
    }
    .clss-body-ngnhan{
        box-sizing: content-box;
        padding: 5px;
    }
    textarea
    {
        border:1px solid #000;
        width:100%;
        height: 100px;
    }
    .clss-lable{
        font-weight: bold;
    }
    .cls-over{
        overflow-y: scroll;
        height: 70vh;
        overflow-x: scroll;
    }
    .cmd{
        padding: 5px;
        background-image: linear-gradient(#f2f2f2,#c2c2c2);
        border: 1px solid #c2c2c2;
        border-radius: 2px;
        z-index: 99;
        margin-left: 5px;
    }
    hr{
        border-bottom: 0px;
        border-top: 1px solid lightgray;
    }
    .item {
        padding: 5px;
        text-align: right;
        border: 0px !important;
        outline: none;
    }
    .cls {
        background-color: orange;
    }
            #divTitle{
            font: 14px Arial, Helvetica, sans-serif;
            font-weight: bold;
            color: #0077b3;
            text-align: center;
            }
/*            .scrolly_table {
            white-space: nowrap;
            overflow-wrap: inherit;
            overflow-x: scroll;
            
          }*/

    </style> 
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_STT").css({"width": "30px"});
                $(".TD_MAKH").css({"width": "70px"});
                $(".TD_TENKH").css({"width": "170px"});
                $(".TD_SOKU").css({"width": "120px"});
                $(".TD_SOTIEN").css({"width": "90px"});
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
    
        function hienthichitiet(commune_detai) {
            var ht1 = screen.availHeight - 100;
            var wt1 = screen.availWidth -100;
            var left1 = (screen.width / 2) - (wt1 / 2);
             var namBc = $('#namBc').val();
            var dotBc = $('#dotBc').val();
            var maBc = $('#maBc').val();
            var top1 = 100;           
            var url = "getDetailKhnvByAllSubCommune.action?commune_detai=" + commune_detai
            +"&dotBc=" + dotBc+"&namBc=" + namBc+"&maBc=" + maBc;
            popup = window.open(url, '_blank', "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        }
        
        
    function js_confirmdelete() {
//        $("#luu_thanhcong").hide();
        $('#luu_thanhcong').empty();
        var r = confirm('(Msg)Bạn chắc chắn muốn chốt/mở chốt số liệu xã này?');
        if (r === false) {
            event.preventDefault();
        }
    }
        function initTable()
            {
                var table = document.getElementById("scrolling_table_checkloan");
//                alert(table);
                var rowcount = table.rows.length;    
                rowcount = rowcount > max_row ? rowcount : max_row;                
                for (var i = 0; i < rowcount; i++)
                {                    
                    var matmp = getMabyNumber(i);//   
                    
                    if(matmp == 1)
                    {
                        $('input:checkbox[id='+i+']').attr('checked',true);
                    }
                }
            }
            
            function getMabyNumber(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
    
    
//        var max_row = 0;
            
        </script>
        
        <style>                                                
            table.editDelete{
                border-collapse: collapse;
                /*width: 100%;*/
                border-color: #999;
            }            
        </style>
    </head>
    <body style="font-family: ">
        <div id="luu_thanhcong"></div>
        <s:form id="id_ktdc_view_kiemtra_loan" action="saveDataKtdc" theme="simple">
                <s:hidden name="mabc" id="mabc"/>
                <s:hidden name="ngay_kt" id="ngay_kt"/>
                <s:hidden name="ngay_bc" id="ngay_bc"/>
                <s:hidden name="doituongkt" id="doituongkt"/>
                <s:hidden name="hinhthuckt" id="hinhthuckt"/>
                <s:hidden name="canbokt" id="canbokt"/>
                <s:hidden name="canboinfo" id="canboinfo"/>
                <s:hidden name="maxa" id="maxa"/>
                <s:hidden name="dvut" id="dvut"/>
                <s:hidden name="mato" id="mato"/>
                <s:hidden name="cust_search" id="cust_search"/>
                <div id="divTitle">
                    THÔNG TIN KIỂM TRA MÓN VAY
                    <br>   
                    <s:if test="!reasonReject.equalsIgnoreCase('AAA')">
                        <font color="red"><br>Nguyên nhân từ chối/TT chốt số liệu: <s:property value="reasonReject"/></font>       
                    </s:if> 
                </div>
                <!--</br>-->
                <!--<div class="cls-over">-->
                <div id="scrolling_table_1" class="scrolly_table" style="width: 3200px; max-height:50vh">
                <table id="scrolling_table_checkloan">
                    <thead>
<!--                        <tr>
                            <td colspan="3" style="text-align: left; border: 0px; font-weight: bold; background-color: orange ;"><span id="strHeader" style="text-transform: uppercase; color: white;"></span></td>
                            <td colspan="4" style="text-align: right; border: 0px;font-style: italic;background-color: orange; color: white;">Đơn vị: triệu đồng, %, hộ, người</td>
                        </tr>-->
                        <tr>
                            <th rowspan="3" class="TD_STT">STT</th>
                            <th rowspan="3" class="TD_STT">Duyệt</th>
                            <th colspan="6">Thông tin kiểm tra</th>
                            <th colspan="6">Thông tin hệ thống hỗ trợ xuất ra</th>
                            <th colspan="4">Nhập kết quả kiểm tra thực tế</th>
                            <th colspan="3">Biện pháp xử lý </th>                           
                        </tr>
                        <tr>
                            <th rowspan="2" class="TD_TENKH">Tổ TKVV</th>
                            <th rowspan="2" class="TD_MAKH">Ngày kiểm tra</th>
                            <th rowspan="2" class="TD_MAKH">Ngày số liệu</th>
                            <th rowspan="2" class="TD_TENKH">Đối tượng kiểm tra</th>
                            <th rowspan="2" class="TD_TENKH">Hình thức kiểm tra</th>
                            <th rowspan="2" class="TD_TENKH">Cán bộ kiểm tra</th>
                            
                            
                            <th rowspan="2" class="TD_TENKH">Họ tên khách hàng</th>
                            <th rowspan="2" class="TD_SOKU">Mã món vay</th>
                            <th rowspan="2" class="TD_SOKU">Tên chương trình vay</th>
                            <th rowspan="2" class="TD_MAKH">Ngày giải ngân</th>
                            <th rowspan="2" class="TD_SOTIEN">Số tiền vay</th>
                            <th rowspan="2" class="TD_CHITIEU">Mục đích sử dụng vốn vay</th>
                            
                            <th rowspan="2" class="TD_SOTIEN">Số tiền thực nhận</th>
                            <th colspan="2" class="TD_CHITIEU">Thực tế sử dụng</th>
                            <th rowspan="2" class="TD_SOTIEN">Số tiền sử dụng sai mục đích</th>
                            
                            <th rowspan="2" class="TD_CHITIEU">Nội dung</th>
                            <th rowspan="2" class="TD_SOTIEN">Thời hạn  hoàn thành</th>
                            <th rowspan="2" class="TD_SOTIEN">Kết quả hoàn thành </th>
                                                                                                                                            
                        </tr>
                        <tr>
                            <th class="TD_CHITIEU">Vào việc</th>
                            <th>Số tiền</th>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td style="text-align: center">11</td>
                            <td style="text-align: center">12</td>
                            <!--<td style="text-align: center">3</td>-->
                            <td style="text-align: center">14</td>
                            <td style="text-align: center">15</td>
                            <td style="text-align: center">16</td>
                            <!--<td style="text-align: center">17</td>-->
                            
                            <td style="text-align: center">18</td>
                            <td style="text-align: center">19</td>
                            <td style="text-align: center">20</td>
                            <td style="text-align: center">21</td>
                            <td style="text-align: center">22</td>
                            <td style="text-align: center">23</td>

                            <td style="text-align: center">24</td>     
                            <td style="text-align: center">25</td>    

                        </tr>
                    </thead> 
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                                    
                            <tr> 
                                <td class="TD_STT"></td>
                                <td  align="center" class="TD_DAT_KODAT">    
                                    <input type="checkbox" id ="<s:property  value="%{#rowstatus.index}" />"  class="checkboxdat" name="lstsaveNT_DAT[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D12" />" 
                                           class="D0"/>
                                </td> 
                                <td  align="right" class="TD_TENKH">    
                                    <input type="text" value="<s:property  value="D39" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D39" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>
                                <td  align="right" class="TD_MAKH">    
                                    <input type="text" value="<s:property  value="D1" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>
                                <td  align="right" class="TD_MAKH">    
                                    <input type="text" value="<s:property  value="D2" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>
                                <td  align="right" class="TD_TENKH">    
                                    <input type="text" value="<s:property  value="D33" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D33" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>
                                <td  align="right" class="TD_TENKH">    
                                    <input type="text" value="<s:property  value="D34" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D34" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>
                                <td  align="right" class="TD_TENKH">    
                                    <input type="text" value="<s:property  value="D35" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D35" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>
                                 
                                <td  align="right" class="TD_SOTIEN">    
                                    <input type="text" value="<s:property  value="D11" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>  
                                    <input type="hidden" value="<s:property  value="MA" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                    
                                    <input type="hidden" value="<s:property  value="D1" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1"/>" />
                                    <input type="hidden" value="<s:property  value="D2" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>" />
                                    <input type="hidden" value="<s:property  value="D3" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D3"/>" />
                                    <input type="hidden" value="<s:property  value="D4" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" value="<s:property  value="D4"/>" />
                                    <input type="hidden" value="<s:property  value="D5" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" value="<s:property  value="D5"/>" />
                                    <input type="hidden" value="<s:property  value="D6" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" value="<s:property  value="D6"/>" />
                                    
                                     <input type="hidden" value="<s:property  value="D7" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" value="<s:property  value="D7"/>" />
                                      <input type="hidden" value="<s:property  value="D9" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D9"/>" />
                                     <input type="hidden" value="<s:property  value="D13" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" value="<s:property  value="D13"/>" />  
                                </td> 
                                
                                <td  align="right" class="TD_SOKU">    
                                    <input type="text" value="<s:property  value="D12" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>
                                <td  align="right" class="TD_SOKU">    
                                    <input type="text" value="<s:property  value="D14" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>
                                <td  align="right" class="TD_MAKH">    
                                    <input type="text" value="<s:property  value="D15" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>
                                <td  align="right" class="TD_SOTIEN">    
                                    <input type="text" value="<s:property  value="D16" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="TEN_KH number" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>
                                
                                <td  align="right" class="TD_SOTIEN">    
                                    <input type="text" value="<s:property  value="D18" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class=" TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                                </td>
                                <td  align="right" class="TD_SOTIEN">    
                                    <input type="text" value="<s:property  value="D19" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="TEN_KH number" onfocus="this.select()"/>                                  
                                </td>
                                <td  align="right" class="TD_SOTIEN">    
                                    <input type="text" value="<s:property  value="D20" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="D10 TEN_KH" onfocus="this.select()"/>                                  
                                </td>
                                <td  align="right" class="TD_SOTIEN">    
                                    <input type="text" value="<s:property  value="D21" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" class="D11 TEN_KH number" onfocus="this.select()"/>                                  
                                </td>
                                <td align = "left" class="TD_SOTIEN">                                        
                                    <s:select  
                                        id="lstDulieuNt[%{#rowstatus.index}].D22"
                                        name="lstDulieuNt[%{#rowstatus.index}].D22"
                                        list="lstBienphapXuly" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        headerKey="-1"
                                        headerValue="--- Chọn ---"
                                        cssStyle="width: 150px;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                    </s:select>
                                </td>
                                <td  align="right" class="TD_CHITIEU">    
                                    <input type="text" value="<s:property  value="D23" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23" class="TEN_KH" onfocus="this.select()"/>                                  
                                </td>
                                <td  align="right" class="TD_SOTIEN">    
                                    <input type="text" value="<s:property  value="D24" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24" class="TEN_KH"  class="TEN_KH D0 datepicker" placeholder="dd/MM/yyyy"
                                               />                                  
                                </td>
                                <td align = "left" class="TD_SOTIEN">                                        
                                    <s:select  
                                        id="lstDulieuNt[%{#rowstatus.index}].D225"
                                        name="lstDulieuNt[%{#rowstatus.index}].D25"
                                        list="lstKetQuaHT" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        headerKey="-1"
                                        headerValue="--- Chọn ---"
                                        cssStyle="width: 100px;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                    </s:select>
                                </td>

                        </tr>                                                                                                       
                    </s:iterator>
                </table>   
                
                <br>
                
                
               
               </div><!--</div>-->
             <sj:submit id="ktdc_save" name="ktdc_save" value="save" targets="message_result" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>       
        </s:form>
        <script>
            initTable();
        </script>
    </body>
    
    
</html>
