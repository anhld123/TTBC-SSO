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
        <style>
            *{
                font-family: tahoma;
                font-size: 12px;
            }
            table {
                border-collapse: collapse;
                width: 100%;
                height: 1000px;
            }

            table thead { position: sticky; top: 0; z-index: 1; }

            th, td {
                text-align: left;
                padding: 8px;
                border: 1PX solid #f2f2f2;
                text-align: center;
            }

            tr:nth-child(even){background-color: #f2f2f2}

            th {
                background-color: #04AA6D;
                color: white;
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
                height: 76vh;
            }
            .cmd{
                padding: 5px;
                background-image: linear-gradient(#f2f2f2,#c2c2c2);
                border: 1px solid #c2c2c2;
                border-radius: 2px;
            }

            #divTitle{
                font: 14px Arial, Helvetica, sans-serif;
                font-weight: bold;
                color: #0077b3;
                text-align: center;
            }
        </style>    
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".SOKU").css({"width": "100%"});
                $(".TD_CHECKBOX").css({"width": "20px"});
                $(".TD_SOKU").css({"width": "80px"});
                $(".TD_TENKH123").css({"width": "110px"});
                $(".TD_TENTS").css({"width": "190px"});
                $(".TD_SOTK").css({"width": "105px"});
                $(".TD_MAKH").css({"width": "60px"});
                $(".TD_THOIGIAN").css({"width": "55px"});
                $(".TD_MAPGD").css({"width": "45px"});
                $(".TD_BUTTON1").css({"width": "40px"});
                $(".TD_SOTIEN").css({"width": "100px"});
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

            function hienthichitiet(soku) {
                var ht1 = screen.availHeight - 360;
                var wt1 = 500;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 100;
                var url = "getDetialTIDE.action?soku=" + soku;
                popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }

            var max_row = 0;
            function initTable()
            {
                var table = document.getElementById("tablesms01");
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

        </script>

        <style>                                                
            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }            
        </style>

    </head>
    <body style="font-family: ">
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            </br>
            <s:hidden name="namBc_pre"/>
            <div id="divTitle">
                NHU CẦU VAY VỐN TÍN DỤNG THEO THON
                <BR>
                <!--<font color="red">(Nếu mã KH và tên KH null sẽ chỉ hiện thị các KH đã từng đăng ký nhận tin nhắn)</font>-->                    
            </div>
            <s:hidden name="khoa_nhaptaycn"/>
            <!--                <div id="divDonvitinh">
                                Đơn vị tính: Đồng
                            </div>-->
            </br>
            <div class="cls-over">
                <table>
                    <thead>
                        <!--                    <tr>
                                                <td colspan="3" style="text-align: left; border: 0px; font-weight: bold;">KẾ HOẠCH TÍN DỤNG NĂM 2022</td>
                                                <td colspan="4" style="text-align: right; border: 0px;font-style: italic;">Đơn vị: triệu đồng, %, hộ, người</td>
                                            </tr>-->
                        <tr>
                            <th rowspan="3">STT</th>
                            <th rowspan="3">CHỈ TIÊU</th>
                            <th rowspan="3">Thực hiện đến 31/12/<s:property value="namBc_pre"/></th>
                            <th rowspan="3">Ước thực hiện đến 31/12/<s:property value="namBc"/></th>
                            <th colspan="3" >Kế hoạch tín dụng năm <s:property value="namBc"/></th>
                        </tr>
                        <tr>
                            <th rowspan="2">Tổng số</th>
                            <th colspan="2">Tăng, giảm so với 31/12/<s:property value="namBc_pre"/></th>
                        </tr>
                        <tr>
                            <th>Số tuyệt đối (+/-)</th>
                            <th>Số tương đối (%)</th>
                        </tr>
                        <tr class="sttCol">
                            <td>1</td>
                            <td>2</td>
                            <td>3</td>
                            <td>4</td>
                            <td>5</td>
                            <td>6</td>
                            <td>7</td>
                        </tr>
                    </thead>                                  
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                                    
                        <tr> 

                            <td align = "center" class="TD_SOKU">
                                <input type="text"  value="<s:property  value="THUTU" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="TEN_KH D0" onfocus="this.select();"
                                       />
                            </td>
                            <td align = "center" class="TD_SOKU">
                                <input type="text"  value="<s:property  value="D4" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH" onfocus="this.select();"
                                       />
                            </td>

                            <td align = "right" class="TD_TENKH123">
                                <input type="text"  value="<s:property  value="D5" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();"
                                       />
                            </td>
                            <td align = "center" class="TD_SOKU">
                                <input type="text"  value="<s:property  value="D5" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();"
                                       />
                            </td>
                            <td align = "center" class="TD_SOKU">
                                <input type="text"  value="<s:property  value="D5" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();"
                                       />
                            </td>
                            <td align = "center" class="TD_SOKU">
                                <input type="text"  value="<s:property  value="D5" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();"
                                       />
                            </td>
                            <td align = "center" class="TD_SOKU">
                                <input type="text"  value="<s:property  value="D5" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();"
                                       />
                            </td>

                        </tr>                                                                                                       
                    </s:iterator>
                </table>                    
            </s:form>
            <div id="luu_thanhcong"></div>
            <!--        <script>
                        initTable();
                    </script>-->
    </body>


</html>
