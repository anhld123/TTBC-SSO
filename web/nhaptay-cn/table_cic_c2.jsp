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
            $("#allCheck_dat").change(function () {
                $(".checkboxdat").prop('checked', $(this).prop("checked"));
            });
        </script>     

        <script>

            function initTable()
            {
                var table = document.getElementById("tblTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    //cho combox 1
                    var matmp1 = getMabyNumber1(i);//                       
                    if (matmp1 == 1)
                    {
                        $('input:checkbox[id=idc11' + i + ']').attr('checked', true);
                    }
                }
            }

            function getMabyNumber1(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id9_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
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
            
            #tblTable12 {

                    border-collapse: collapse;
                    width: 100%;
            }

            #tblTable12 td, #tblTable12 th {
                    border: 1px solid #8a8a5c;
                    padding: 4px;
            }

            #tblTable12 tr:nth-child(even){background-color: #f2f2f2;}

            #tblTable12 tr:hover {background-color: #ddd;}

            #tblTable12 th {
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
        <s:form id="id_sv_CIC_001" action="SAVE_CIC_001" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            </br>
            <div id="divTitle">
                CHỐT/ MỞ CHỐT SỐ LIỆU RÀ SOÁT THÔNG TIN CHUNG CỦA KHÁCH HÀNG
                
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nhaptaycn"/>

            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 78%; max-height:45vh">
                    <table id="tblTable">
                        <tr>      
                            <th rowspan="1" class="TD_STT">STT</th>                                                       
                            <th rowspan="1" class="TD_MAKH">Mã PGD</th>  
                            <th rowspan="1" class="TD_TENKH">Tên PGD</th>    
<!--                            <th rowspan="1"  class="TD_MAKH">Tổng số khách hàng cần rà soát</th>                             
                            <th rowspan="1"  class="TD_MAKH">Số khách hàng chưa rà soát</th>   -->
<!--                            <th rowspan="1"  class="TD_MAKH">Số khách hàng đã rà soát và cập nhật lên Intellect</th> 
                            <th rowspan="1"  class="TD_MAKH">Số khách hàng không làm rõ được</th>   -->
                            
                            
                            <th rowspan="1"  class="TD_MAKH">Chốt/ mở chốt số liệu</th>                              
                            
                        </tr>         
                        
                        <tr style="font-style: italic;">
                            <td style="text-align: center">(1)</td>
                            <!--<td style="text-align: center">(2)</td>-->
                            <td style="text-align: center">(2)</td>
                            <td style="text-align: center">(3)</td>
                            <td style="text-align: center">(4)</td>
<!--                            <td style="text-align: center">(5)</td>
                            <td style="text-align: center">(6)</td>-->
<!--                            <td style="text-align: center">(7)</td>
                            <td style="text-align: center">(8)</td>-->
                            
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>                               
                                <td align = "right" class="TD_STT" >
                                    <input type="text"   value="<s:property  value="THUTU" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="D0 TEN_KH " onfocus="this.select();"
                                           readonly="true"/>                                        
                                </td>  


                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="MAPGD" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" class="D0 TEN_KH " onfocus="this.select();" 
                                           readonly="true"/> 
                                    
                                    <input type="hidden" value="<s:property  value="D25" />"  id="id9_<s:property  value="%{#rowstatus.index}" />" 
                                        value="<s:property  value="D25"/>"/>
                                     <input type="hidden" value="<s:property  value="MACN" />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN" value="<s:property  value="MACN"/>" />
                                </td>
                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"   value="<s:property  value="TEN" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class=" TEN_KH " onfocus="this.select();"
                                           readonly="true"/>
                                </td>
<!--                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D1" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="number TEN_KH " onfocus="this.select();"
                                           readonly="true"/>
                                </td>

                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"   value="<s:property  value="D2" />" style="background: #C0C0C0 !important;"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="number TEN_KH" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>-->

                              
                                 
                              
                               
                                <td  align="center" class="TD_CHECKBOX">    
                                    <input type="checkbox" id ="idc11<s:property  value="%{#rowstatus.index}" />"  class="checkboxdat TEN_KH D0" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25" value="<s:property  value="D25"/>"                                            
                                           />
                                </td> 
                                
                               
                            </tr>                                                                                                                                                                                   
                        </s:iterator>
                    </table>        
                </div>
            </div>

            <sj:submit id="CIC_001_save" name="CIC_001_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        
        <script>
            initTable();
        </script>
    </body>


</html>

