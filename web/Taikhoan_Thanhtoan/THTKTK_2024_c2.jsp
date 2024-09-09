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
        width: 40%;
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
    #subTable_tmp {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 550px;
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
                $('.number').number(true, 1);
                //            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);

            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });

            function funcTableFile(D1, D5, D6) {
                var w = 900, h = 500;
                var left = (screen.width / 2) - (w / 2);
                var top = (screen.height / 2) - (h / 2);
                var urlParam = "madiemgd=" + D1 + "&ngaybc=" + D5 + "&pos_flag=" + D6;
                var url = "/IMS_REPORTS/popupTablePos.action?" + urlParam;
                popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }
        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;">             
            <div id="divTitle">
                <s:hidden name="khoa_tdnn" id="khoa"/>
                DANH SÁCH PGD ĐÃ GỬI DỮ LIỆU
                <s:if test="txtGetData.equalsIgnoreCase('1')"><a style="color: #009900"> (Kỳ báo cáo tuần)</a></s:if>
                <s:elseif test="txtGetData.equalsIgnoreCase('2')"><a style="color: #009900"> (Kỳ báo cáo tháng)</a></s:elseif>
                <s:elseif test="txtGetData.equalsIgnoreCase('3')"><a style="color: #009900"> (Kỳ báo cáo năm)</a></s:elseif>
                </div>
                <div style="height:10px"></div>  
                <div style="height:10px"></div>  
                <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px">   
                    <tr>
                        <th style="width: 50px">STT</th>
                        <th style="width: 80px">Mã PGD</th>
                        <th style="width: 150px">Tên</th>
                        <th style="width: 150px">Trạng thái</th>
                        <th style="width: 100px">Mở</th>
                        <th style="width: 100px">Gửi TW</th>
                    </tr>  
                    <tr>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                    </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>
                        <s:if test="D4.equalsIgnoreCase('0')">
                            <td class="D0" style="color: red"><s:property value="%{#rowstatus.index + 1}" /></td>
                            <td class="D0" ><a style="color: red;text-decoration: underline; font-size: 12px" href="javascript:funcTableFile('<s:property value="D1"/>', '<s:property value="D5"/>', '<s:property value="D6"/>')"><s:property  value="D1" /></a></td>
                            <td style="color: red"> <s:property value="D2"/></td>
                            <!--<td class="D0" style="color: red"><s:property  value="D3" /></td>-->
                            <td class = "D0">
                                <a style="color: red">Chưa gửi dữ liệu</a></td>
                            </s:if>
                            <s:elseif test="!D4.equalsIgnoreCase('0')">
                            <td class="D0" style="color: #003eff"><s:property value="%{#rowstatus.index + 1}" /></td>
                            <td class="D0" ><a style="color: #003eff;text-decoration: underline; font-size: 12px" href="javascript:funcTableFile('<s:property value="D1"/>', '<s:property value="D5"/>', '<s:property value="D6"/>')"><s:property  value="D1" /></a>
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                       id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D1"/>"/>
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                       id="D5_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D5"/>"/>
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                       id="D6_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D6"/>"/></td>

                            <td style="color: #003eff"><s:property value="D2"/></td>
                            <td class = "D0"><s:if test="D4.equalsIgnoreCase('1')">
                                    <a style="color: #003eff">Đã gửi dữ liệu CN</a></s:if>
                                <s:if test="D4.equalsIgnoreCase('2')">
                                    <a style="color: #003eff">Đã gửi dữ liệu TW</a></s:if>
                                </td>
                        </s:elseif>
                        <s:else>
                            <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                            <td class="D0"><s:property  value="D1" /></td>
                            <td><s:property  value="D2" /></td>
                            <!--<td class="D0" style="color: red"><s:property  value="D3" /></td>-->
                            <td class = "D0">
                                <a>Chưa nhập dữ liệu</a></td>
                            </s:else>
                        <td style="width: 80px; text-align: center;">
                            <s:if test="D4.equalsIgnoreCase('1')">
                                <a href="#" onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D5"/>', '<s:property value="D6"/>');">Mở dữ liệu</a>
                            </s:if>                                                                
                        </td>
                        <td style="width: 80px; text-align: center;">
                            <s:if test="D4.equalsIgnoreCase('1')">
                                <a href="#" onclick="sendData('<s:property value="D1"/>', '<s:property value="D5"/>', '<s:property value="D6"/>');">Chốt dữ liệu</a>
                            </s:if>  
                                <s:if test="D4.equalsIgnoreCase('2')"><a style="color: #003eff">Đã gửi</a></s:if>    
                        </td>
                    </tr>
                </s:iterator>

        </div>      
        <div id="luu_thanhcong"></div>
        <script>

            function cancelAssign(D1, D5, D6) {
                var url, sdata;
                url = "unlock_THTK_2024_c2.action?" + "madiemgd=" + D1 + "&ngaybc=" + D5 + "&pos_flag=" + D6,
                        sdata = jQuery("#frmdata").serialize();
                $("#viewData").html('<img src="img/loading.gif"/>');
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        if (data === "200") {
                            alert("Mở phê duyệt thành công!");
                            $('#message_suc_err').html("<h style='color: green; font-size: 13px ; font-weight: bold'>Bạn đã mở dữ liệu thành công!</h>");
                            onLoadData();
                        } else {
                            alert("Lỗi: Mở dữ liệu.");
                            onLoadData();
                        }
                    },

                    error: function (request) {
                        alert("Mở phê duyệt lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                    }
                });
            }

            function sendData(D1, D5, D6) {
                var url, sdata;
                url = "lock_THTK_2024_c2.action?" + "madiemgd=" + D1 + "&ngaybc=" + D5 + "&pos_flag=" + D6,
                        sdata = jQuery("#frmdata").serialize();
                $("#viewData").html('<img src="img/loading.gif"/>');
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        if (data === "200") {
                            alert("Chốt dữ liệu thành công!");
                            $('#message_suc_err').html("<h style='color: green; font-size: 13px ; font-weight: bold'>Bạn đã chốt dữ liệu thành công!</h>");
                            onLoadData();
                        } else {
                            alert("Lỗi: Chốt dữ liệu.");
                            onLoadData();
                        }
                    },

                    error: function (request) {
                        alert("Lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                    }
                });
            }

        </script>
    </body>
</html>
