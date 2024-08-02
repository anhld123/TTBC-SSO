<%-- 
    Document   : ViewData
    Created on : May 17, 2022, 9:56:22 AM
    Author     : ANH
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<head>
    <script src="js/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="js/3.6.0/jquery-ui.css">
    <script src="js/3.6.0/jquery-ui.js"></script>
    <script src="js/jquery.number.js"></script>
    <script src="js/format_num.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<html>
    <script>
        var popWindow;
        var max_row = 0;
        $(document).ready(function () {
            initTable();
        });
        $(document).ready(function () {
            $('.sstyle').css({"color": "#000", "font-size": "12px"});
            $('input.number').css({"text-align": "right"});
            $('.D0').css({"text-align": "center"});
            $('.D00').css({"text-align": "left"});
            $('input.number2').css({"text-align": "right"});
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            $('#ui-datepicker-div').css('clip', 'auto');
            //Cac truong bang so --> se co so truong = 0
            $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
            $('.number2').number(true, 0);
            $(".STT1").css({"width": "50px"});
            $(".STT2").css({"width": "100px"});
            $(".STT3").css({"width": "150"});
            $(".STT4").css({"width": "200px"});
            $(".STT5").css({"width": "70px"});
            $(".STT6").css({"width": "65px"});
            $(".TD_NGUYENGIA").css({"width": "80px"});
            $(".TD_THUTU").css({"width": "30px"});
            $(".TD_CHITIEU").css({"width": "220px"});
            $(".TEN_KH").css({"width": "100%"});
        });
        $('.TEN_KH').focus(function () {
            $(this).closest('tr').addClass('highlight_row');
        });
        $('.TEN_KH').blur(function () {
            $(this).closest('tr').removeClass('highlight_row');
        });

    </script>
    <style>
        #subTable {
            font-size: 16px;
            font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            border-spacing: 0;
            width: auto;
        }
        #subTable th{
            background-color: #ddd;
            color: #0000FF;
        }

        #subTable th, #subTable td {
            border: 1px solid gray;
        }

        #subTable tr:nth-child(even){background-color: #f2f2f2;}

        #subTable tr:hover {background-color: #ddd;}

        .txtPublic{
            width: 85px;
        }
        .ui-datepicker-trigger{
            height: 100%;
        }
        .txtBody{
            text-align: center;
        }
        .txtBody > .ui-datepicker-trigger{
            display: none;
        }
        td.hdtitle {
            position: static;
            top: 0;
            z-index: 10;
        }
    </style>

    <body>
        <form id="frmXuLyNo">        
            <div id="divTitle" style="text-align: center">
                XUẤT DỮ LIỆU FILE CIC
            </div>
            <div style="height: 10px">
            </div>
            <table border="1" class="editDelete" id="subTable" align="center"> 
                <tr>
                    <th class="STT2" style="color: #ff6600">Tên mô tả</th> 
                    <th class="STT2" style="color: #ff6600">Tên file</th> 
                    <th class="STT3" style="color: #ff6600">Download</th>
                </tr>
                <tr style="font-style: italic;">
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr> 
                        <td><s:property  value="D10" />
                            <input type="hidden" id="base64" name="base64" value="<s:property  value="D9" />">
                            <input type="hidden" id="fileName" name="fileName" value="<s:property  value="D11" />">
                        </td> 
                        <td> <s:property  value="D11" /></td>
                        <td class="D0"> 
                            <a id="download-link" href="#" onclick="DownloadExcel()">Tải File</a>
                        </td>
                    </tr>
                </s:iterator>
            </table>
        </form>

    </body>

    <script >
        var responseData = document.getElementById('base64').value;
        var fileName = document.getElementById('fileName').value;
//        var trimmedFileName = fileName.substring(0, fileName.length - 5);
        var bindata = window.atob(responseData);

        function DownloadExcel() {
            var responseData = document.getElementById('base64').value;
//            var bindata = window.atob(responseData);
//
//            // Chuyển đổi dữ liệu base64 thành dạng nhị phân
//            var bytes = new Uint8Array(bindata.length);
//            for (var i = 0; i < bindata.length; i++) {
//                bytes[i] = bindata.charCodeAt(i);
//            }
//
//            // Tạo Blob từ dữ liệu nhị phân
//            var blob = new Blob([bytes], {type: 'application/vnd.ms-excel'});
//
//            // Tạo liên kết và tải xuống tệp
//            var link = document.createElement('a');
//            link.href = URL.createObjectURL(blob);
//            link.download = trimmedFileName + '.xls'; // Cung cấp tên mặc định cho tệp
//            document.body.appendChild(link);
//            link.click();
//            document.body.removeChild(link);
// Tạo Blob từ chuỗi JSON
            var jsonStr = decodeURIComponent(escape(atob(responseData)));

            // Tạo Blob từ chuỗi JSON
            var blob = new Blob([jsonStr], {type: 'application/json'}
            );

            // Tạo liên kết và tải xuống tệp
            var link = document.createElement('a');
            link.href = URL.createObjectURL(blob);
            link.download = fileName; // Cung cấp tên mặc định cho tệp
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }
    </script>
