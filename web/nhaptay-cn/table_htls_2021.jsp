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
                $(".TD_STT").css({"width": "30px"});
                $(".TD_MAKH").css({"width": "70px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "130px"});
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

            function initTable()
            {
                var table = document.getElementById("tablekyquy04");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    var matmp = getMabyNumber(i);//   

                    if (matmp == 1)
                    {
                        $('input:checkbox[id=' + i + ']').attr('checked', true);
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

            function autoEvaluate() {

                var arrCot = [".D2", ".D3", ".D4"]; //Luu cac cot cua du lieu can tinh toan
                for (var i = 0; i < 50; i++) {
                    //8=2+4-6
                    $(".D2").eq(i).val(parseFloat($(".D3").eq(i).val()) + parseFloat($(".D4").eq(i).val()));

                }
                //                              
            }

            function js_confirmDelete() {
                var r = confirm('(Msg)Bạn chắc chắn muốn xoá nhóm người dùng này?');
                if (r === false) {
                    event.preventDefault();
                }
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
                BÁO CÁO GIẢM LÃI CHO VAY CÁC CHƯƠNG TRÌNH TÍN DỤNG CHÍNH SÁCH THEO QUYẾT ĐỊNH 1990/QQD-TTg CỦA THỦ TƯỚNG CHÍNH PHỦ
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nhaptaycn"/>
            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 98%; max-height:45vh">
                    <table id="tblTable">
                        <tr>      
                            <th rowspan="2" class="TD_MAKH">STT</th>                           
                            <th rowspan="2" class="TD_MAKH">CIF</th>  
                            <th rowspan="2" class="TD_MAKH">Mã khoản vay</th>    
                            <th rowspan="2"  class="TD_MAKH">Chương trình tín dụng</th>                             
                            <th colspan="2"  class="TD_MAKH">Dư nợ 31/12/2021</th>                             
                            <th colspan="3"  class="TD_MAKH">Lãi giảm các tháng trong năm 2021</th>                             
                            <th rowspan="2"  class="TD_MAKH">Tổng số tiền lãi được giảm</th>                             
                        </tr>         
                        <tr>
                            <th  class="TD_MAKH">Trong hạn/Quá hạn</th>
                            <th  class="TD_MAKH">Khoanh</th>
                            <th  class="TD_NGAY">Tháng 10</th>                                                        
                            <th  class="TD_NGAY">Tháng 11</th>                                                        
                            <th  class="TD_NGAY">Tháng 12</th>                                                        
                        </tr>
                        <tr style="font-style: italic;">
                            <td style="text-align: center">(1)</td>
                            <td style="text-align: center">(2)</td>
                            <td style="text-align: center">(3)</td>
                            <td style="text-align: center">(4)</td>
                            <td style="text-align: center">(5)</td>
                            <td style="text-align: center">(6)</td>
                            <td style="text-align: center">(7)</td>
                            <td style="text-align: center">(8)</td>
                            <td style="text-align: center">(9)</td>
                            <td style="text-align: center">(10)</td>
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>                               
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D1" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH number" onfocus="this.select();"/>                                        
                                </td>  
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 TEN_KH number" onfocus="this.select();"
                                           onblur="autoEvaluate()"/>                                        
                                </td>                                

                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D3" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 TEN_KH number" onfocus="this.select();" 
                                           onblur="autoEvaluate()"/> 
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D4" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 TEN_KH number" onfocus="this.select();"
                                           onblur="autoEvaluate()"/>
                                </td>

                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D5" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();" />
                                </td>


                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D6" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D6" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D6" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D6" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();" />
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D6" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();" />
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
