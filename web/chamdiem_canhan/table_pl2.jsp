<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<style>
    table.editDelete,
    table.subTable {
        border-collapse: separate;
        border-spacing: 0;
        width: auto;
        /*margin: 5px auto;*/
        font-family: Arial, sans-serif;
        border-radius: 5px;
        overflow: hidden;
        box-shadow: 0 1px 6px rgba(0, 0, 0, 0.05);
        /*font-size: 1px;*/
    }

    table.editDelete th,
    table.subTable th,
    table.editDelete td,
    table.subTable td {
        border: 1px solid #eee;
        background-color: #fff;
    }

    /* Gộp các th riêng */
    table.editDelete th,
    table.subTable th {
        background-color: #eef6ff;
        color: #000;
        font-weight: bold;
    }

    /* Gộp hàng chẵn */
    table.editDelete tr:nth-child(even),
    table.subTable tr:nth-child(even) {
        background-color: #fafafa;
    }

    table.editDelete tr:hover,
    table.subTable tr:hover {
        background-color: #eef6ff;
    }

    /* Hover toàn bộ hàng (trừ header) */
    table.editDelete tr:hover td {
        background-color: #fff3cd; /* vàng nhạt */
    }

    table.editDelete td.number,
    table.subTable td.number {
        color: #333;
        font-weight: 500;
    }
    .custom-scroll {
        overflow: scroll;
        width: auto;
        height: 500px;
        scrollbar-width: thin; /* Firefox */
        scrollbar-color: rgba(128, 128, 128, 0.3) transparent; /* Firefox */
    }

    /* Webkit (Chrome, Edge, Safari) */
    .custom-scroll::-webkit-scrollbar {
        width: 8px;
    }

    .custom-scroll::-webkit-scrollbar-track {
        background: transparent;
    }

    .custom-scroll::-webkit-scrollbar-thumb {
        background-color: rgba(128, 128, 128, 0.3);
        border-radius: 4px;
    }
    .custom-scroll::-webkit-scrollbar-thumb:hover {
        background-color: rgba(128, 128, 128, 0.5);
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
                initTable();
            });
            $(document).ready(function () {
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('.style_h').css({"width:": "99%", "background-color": "rgba(255, 255, 255, 0.3)", "border": "1px solid #ccc"});
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0).css({"text-align": "right"});
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".STT1").css({"width": "50px"});
                $(".STT2").css({"width": "70px"});
                $(".STT3").css({"width": "100"});
                $(".STT4").css({"width": "150px"});
                $(".STT5").css({"width": "300px"});
                $(".STT6").css({"width": "65px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "220px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            function initTable() {
                var table = document.getElementById("subTable");
                var rowcount = Math.max(table.rows.length, max_row);

                for (var i = 0; i < rowcount; i++) {
                }
            }

        </script>        
    </head>
    <body>
        <div class="custom-scroll">   

            <div id="divTitle" style="margin: 10px 0; text-align: center; font-weight: bold; font-size: 16px;">
                DANH SÁCH UPLOAD FILE EXCEL
            </div>

            <table border="1" class="editDelete" id="subTable" align="center" style="width: 98%">
                <tr height="28">
                    <th class="STT3">TT</th>
                    <th class="STT4">Mã pos</th>
                    <th class="STT4">Tên pos</th> 
                    <th class="STT4">TIETKIEM_DANCU</th>
                    <th class="STT4">TIETKIEM_TO</th> 
                    <th class="STT4">NGUON_VON</th> 
                    <th class="STT5">KH_CHAM_HOSO</th> 
                    <th class="STT4">CANBO_PT_PGD</th> 
                    <th class="STT4">TONQUY_TIENMAT</th> 
                    <th class="STT4">PHODG_PHUTRACH_XA</th> 
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
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                
                    <tr height="22">   

                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td> 
                        <td class="D0"><s:property  value="D1" /></td> 
                        <td><s:property  value="D2" /></td> 
                        <td class="D0"><s:property  value="D3" /></td> 
                        <td class="D0"><s:property  value="D4" /></td> 
                        <td class="D0"><s:property  value="D5" /></td> 
                        <td class="D0"><s:property  value="D6" /></td> 
                        <td class="D0"><s:property  value="D7" /></td> 
                        <td class="D0"><s:property  value="D8" /></td>
                        <td class="D0"><s:property  value="D9" /></td>
                    </tr>    

                </s:iterator>

            </table>
        </div>
        <div id="luu_thanhcong"></div>

    </body>
</html>
