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
    .styled-button {
        height: auto; /* Let the height adjust automatically */
        padding: 0;
        background-color: transparent; /* Remove background color */
        color: #003eff;
        border: none; /* Remove border */
        cursor: pointer;
        font-size: 14px;
        text-decoration: underline; /* Add underline to resemble a link */
    }

    .styled-button:hover {
        color: red; /* Change color on hover */
        text-decoration: none; /* Remove underline on hover */
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
            $(function () {
                setCssStyle();
            });
            function addMonths(date, months) {
                date.setMonth(date.getMonth() + months);
                return date;
            }

            function setCssStyle() {
                $(".cssDate").datepicker({
                    dateFormat: 'dd/mm/yy',
//                    showOn: "button",
//                    buttonImage: "img/icon-ui_datepicker.png",
//                    buttonImageOnly: true,
//                    showButtonPanel: true,
//                    buttonText: "icono",
                    changeMonth: true,
                    changeYear: true,
                    yearRange: "c-50:c+50",
                    beforeShow: function (input, inst) {
                        if ($(input).is(':disabled')) {
                            return false; // Ngăn chặn datepicker hiển thị nếu input bị disabled
                        }
                    },
                    // Thêm CSS cho ngày tháng
                    onSelect: function (dateText, inst) {
                        $(this).css({
                            'font-size': '13px', // Cỡ chữ
                            'color': 'red' // Màu chữ
                        });
                        var _freezeDateName = this.name;
                        var _expireDateName = _freezeDateName.replace('D11', 'D13');
                        var _freezeMonthName = _freezeDateName.replace('D11', 'D12');
                        var _freezeMonthValue = parseInt($("input[name='" + _freezeMonthName + "']").val());
                        var toDate = new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay); //Date one month after selected date
                        var oneDay = new addMonths(toDate, _freezeMonthValue);
                        $("input[name='" + _expireDateName + "']").val($.datepicker.formatDate('dd/mm/yy', oneDay));
                        $("input[name='" + _expireDateName + "']").css({
                            'font-size': '13px', // Cỡ chữ
                            'color': 'red' // Màu chữ
                        });
                    }
                }).on('change', function (event) {
                    event.preventDefault();
                    $(this).css({
                        'font-size': '13px', // Cỡ chữ
                        'color': 'red' // Màu chữ
                    });
                    var _freezeDateName = this.name;
                    var _expireDateName = _freezeDateName.replace('D11', 'D13');
                    var _freezeMonthName = _freezeDateName.replace('D11', 'D12');
                    var _freezeMonthValue = parseInt($("input[name='" + _freezeMonthName + "']").val());
                    let [day, month, year] = this.value.split('/');
                    const toDate = new Date(+year, +month - 1, +day);
                    var oneDay = new addMonths(toDate, _freezeMonthValue);
                    $("input[name='" + _expireDateName + "']").val($.datepicker.formatDate('dd/mm/yy', oneDay));
                    $("input[name='" + _expireDateName + "']").css({
                        'font-size': '13px', // Cỡ chữ
                        'color': 'red' // Màu chữ
                    });
                });
                $(".cssDate2").datepicker({
                    dateFormat: 'dd/mm/yy',
                    //showOn: "button",
                    //buttonImage: "img/icon-ui_datepicker.png",
                    //buttonImageOnly: false,
                    //showButtonPanel: false,
                    //buttonText: "icono",
                    changeMonth: true,
                    changeYear: true,
                    yearRange: "c-50:c+50",
                    beforeShow: function (input, inst) {
                        if ($(input).is(':disabled')) {
                            return false; // Ngăn chặn datepicker hiển thị nếu input bị disabled
                        }
                    },
                    // Thêm CSS cho ngày tháng
                    onSelect: function (dateText, inst) {
                        $(this).css({
                            'font-size': '13px', // Cỡ chữ
                            'color': 'red' // Màu chữ
                        });
                    }
                });
            }

            function initTable()
            {

            }

            function funcTableFile(D8) {
                var w = 500, h = 300;
                var left = (screen.width / 2) - (w / 2);
                var top = (screen.height / 2) - (h / 2);
                var urlParam = "vbspId=" + D8;
                var url = "/IMS_REPORTS/popupTableFile.action?" + urlParam;
                popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }
        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;height: 400px;">    
            <div id="divTitle">
                CÁC CHỈ TIÊU CIC
            </div>
            <div style="height: 10px">
            </div>
            <table border="1" class="editDelete" id="subTable" align="center"> 
                <tr>
                    <th  class="STT1" style="color: #ff6600">STT</th>  
                    <th  class="STT3" style="color: #ff6600">Tên phòng giao dịch</th>
                    <th  class="STT2" style="color: #ff6600">SbvCode</th> 
                    <th  class="STT2" style="color: #ff6600">Tải file</th>
                    <th  class="STT3" style="color: #ff6600">Chỉ tiêu</th> 
                </tr>
                <!--                <tr>
                                    <th  class="STT2" style="color: #ff6600">Upload file</th>  
                                    <th class="STT2" style="color: #ff6600">Tải file</th>
                                </tr> -->
                <tr style="font-style: italic;">
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    <!--<th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>-->
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr> 
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" /> </td>      
                        <td> <s:property  value="D2" /> </td>                                  
                        <td class="D0"> <s:property  value="D5" /> </td>  
                        <!--<td class="D0">-->
                        <!--<input class="styled-button" type="button" id="idUpload" value="Upload excel" onclick="callDirectLink('khvn_open_upload_qt_kh?');">-->
                        <!--</td>-->
                        <td class="D0"> 
                            <a href="javascript:funcTableFile('<s:property value="D8"/>')"><s:property value="D8"/> </a>
                        </td>        
                        <td class="D0"> <s:property  value="D6" /> </td>  
                    </tr>
                </s:iterator>
            </table>
        </div>
        <div id="luu_thanhcong"></div>
    </body>
    <script>
        function callDirectLink(link) {
            //var ht = screen.availHeight / 6;
            //var wt = screen.availWidth / 5;
            PopupCenter(link, 'Upload excel', 800, 400);

        }
        function PopupCenter(pageURL, title, w, h) {
            var left = (screen.width / 2) - (w / 2);
            var top = (screen.height / 2) - (h / 2);
            var targetWin = window.open(pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
            return targetWin;
        }
    </script>
</html>
