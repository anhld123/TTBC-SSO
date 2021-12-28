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
            /*height: 1000px;*/
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
        .cmd, input[type="submit"]{
            padding: 5px;
            background-image: linear-gradient(#f2f2f2,#c2c2c2);
            border: 1px solid #c2c2c2;
            border-radius: 2px;
        }
        
        .CLS-BOLD{
                font-weight: bold;
            }
            #divTitle{
    font: 14px Arial, Helvetica, sans-serif;
    font-weight: bold;
    color: #0077b3;
    text-align: center;

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
                $(".TD_STT").css({"width": "5%"});
                $(".TD_GIATRI").css({"width": "8%"});
                $(".TD_TEN").css({"width": "12%"});
                $(".TD_CHITIEU").css({"width": "30%"});

            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
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
                KẾT QUẢ THỰC HIỆN  KẾ HOẠCH TÍN DỤNG
                <BR>
                <s:if test="!reasonReject.equalsIgnoreCase('AAA')">
                        <font color="red">Nguyên nhân từ chối/TT chốt số liệu: <s:property value="reasonReject"/></font>           
                    </s:if> 
                <!--<font color="red">(Nếu mã KH và tên KH null sẽ chỉ hiện thị các KH đã từng đăng ký nhận tin nhắn)</font>-->                    
            </div>
            <s:hidden name="khoa_nhaptaycn"/>
            <!--                <div id="divDonvitinh">
                                Đơn vị tính: Đồng
                            </div>-->
            </br>
            <div class="cls-over">
                <table >
                    <thead>
                        <!--                    <tr>
                                                <td colspan="3" style="text-align: left; border: 0px; font-weight: bold;">KẾ HOẠCH TÍN DỤNG NĂM 2022</td>
                                                <td colspan="4" style="text-align: right; border: 0px;font-style: italic;">Đơn vị: triệu đồng, %, hộ, người</td>
                                            </tr>-->
                        <tr>
                            <th rowspan="2" class="TD_STT" align="center">STT</th>
                            <th rowspan="2" class="TD_CHITIEU">CHỈ TIÊU</th>
                            <!--<th rowspan="3" class="TD_GIATRI">Thực hiện đến 31/12/<s:property value="namBc_pre"/></th>-->
                            <th rowspan="2" class="TD_GIATRI D0">Số thực hiện đến 31/12/<s:property value="namBc_pre"/></th>
                            <th colspan="2" >Năm báo cáo</th>
                            <th rowspan="2" >Tăng/giảm so với năm <s:property value="namBc"/></th>
                            <th rowspan="2" >Tỷ lệ hoàn thành kế hoạch giao tăng trưởng (%)</th>
                        </tr>
<!--                        <tr>
                            <th rowspan="2" class="TD_GIATRI">Tổng số</th>
                            <th colspan="2" class="TD_GIATRI">Tăng, giảm so với 31/12/<s:property value="namBc_pre"/></th>
                        </tr>-->
                        <tr>
                            <th class="TD_GIATRI">Kế hoạch giao tăng trưởng năm <s:property value="namBc"/></th>
                            <th class="TD_GIATRI">Số thực hiện đến 31/12/<s:property value="namBc_pre"/></th>
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
                        <tr style="height:20px"> 
                            <td style="text-align:center"  class="TD_SOKU <s:property value="D19"/>">
                                    <s:property value="THUTU"/>
                                </td>
                                
                            <td style="text-align:left"  class="TD_SOKU <s:property value="D19"/>">
                                    <s:property value="TEN"/>
                                </td>
                                
                            <td style="text-align:right"  class="number TD_GIATRI <s:property value="D19"/>">
                                    <s:property value="D1"/>
                                </td>

                           <td style="text-align:right"  class="TD_GIATRI number <s:property value="D19"/>">
                                    <s:property value="D2"/>
                                </td>
                             <td style="text-align:right"  class="TD_GIATRI number <s:property value="D19"/>">
                                    <s:property value="D3"/>
                                </td>
                                 <td style="text-align:right"  class="TD_GIATRI number <s:property value="D19"/>">
                                    <s:property value="D4"/>
                                </td>
                                 <td style="text-align:right"  class="TD_GIATRI number2 <s:property value="D19"/>">
                                    <s:property value="D5"/>
                                </td>

                        </tr>                                                                                                       
                    </s:iterator>
                </table>                    
            </s:form>
            <div id="luu_thanhcong"></div>
    </body>


</html>
