
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<style>
    #subTable {
        font-size: 12px; /* 👈 chữ to hơn */
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        border-collapse: collapse;
        width: 90%;
        margin: auto;
        background-color: #fff;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        border: 2px solid black; /* 👈 viền ngoài màu đen */
        border-radius: 10px;
        overflow: hidden;
    }

    #subTable th {
        background-color: #f0f4f8;
        color: #2a3f54;
        padding: 14px;
        text-align: center;
        font-weight: bold;
        font-size: 12px;
    }

    #subTable td {
        padding: 12px;
        border-bottom: 1px solid #ccc;
        /*text-align: center;*/
        font-size: 12px;
    }

    #subTable tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    #subTable tr:hover {
        background-color: #eef6ff;
        transition: background-color 0.3s ease;
    }


</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var popWindow;
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                $(".STT1").css({"width": "5%"});
                $(".STT2").css({"width": "30%"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0).css({"text-align": "right"});
                //            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);

            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });


        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;">             
            <div id="divTitle">
                DANH SÁCH PGD ĐÃ GỬI DỮ LIỆU ĐỐI CHIẾU PHÂN LOẠI NỢ
            </div>
            <div style="height:10px"></div>  
            <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px">   
                <tr>
                    <th style="width: 50px" rowspan="2">STT</th>
                    <th style="width: 100px" rowspan="2">Mã PGD</th>
                    <th style="width: 150px" rowspan="2">Tên PGD</th>
                    <th rowspan="2">Tổng số KH</th>
                    <th rowspan="2">Tổng số món vay</th>
                    <th rowspan="2">Tổng dư nợ</th>
                    <th colspan="3">Dư nợ</th>
                    <th rowspan="2">Nợ lãi</th>
                    <th style="width: 100px" colspan="2">Danh sách món vay</th>
                </tr> 
                <tr>
                    <th>Nợ trong hạn</th>
                    <th>Nợ quá hạn</th>
                    <th>Nợ khoanh</th>
                    <th style="width: 100px">Món vay đã chốt</th>
                    <th style="width: 100px">Món vay chưa chốt</th>
                </tr>
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(8)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(9)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(10)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(11)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(12)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr> 
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                        <td class="D0"><s:property  value="D1" /></td>
                        <td> <s:property value="D2"/></td>
                        <td class="number"> <s:property value="D3"/></td>
                        <td class="number"> <s:property value="D4"/></td>
                        <td class="number"> <s:property value="D5"/></td>
                        <td class="number"> <s:property value="D6"/></td>
                        <td class="number"> <s:property value="D7"/></td>
                        <td class="number"> <s:property value="D8"/></td> 
                        <td class="number"> <s:property value="D9"/></td> 
                        <td style="text-align: center" > <a class="number2" href="javascript:hienthichitiet('<s:property value="D1"/>','<s:property  value="D12" />')" class="SOKU linkKh">
                                <s:property value='D10'/>
                            </a>
                        </td>
                        <td style="text-align: center" > <a class="number2" style="color: red" href="javascript:hienthichitiet('<s:property value="D1"/>','<s:property  value="D12" />')" class="SOKU linkKh">
                                <s:property value='D11'/>
                            </a>
                        </td>
                    </tr>
                </s:iterator>
            </table>
        </div>      
        <div id="luu_thanhcong"></div>
    </body>
</html>
