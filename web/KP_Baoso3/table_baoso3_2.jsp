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
        width: 35%;
    }
    #subTable th{
        background-color: #ddd;
        color: #0000FF;
    }

    #subTable th, #subTable td {
        border: 1px solid gray;
        /*font-weight: bold;*/

    }
    #subTable td {color: red;}
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
    .table11 {
        float: left;
        width:80%;
        margin: 0 auto;
        margin-left: 10%; 
    }

    .table22 {
        float:right;
        width:80%;
        margin: 0 auto;
        margin-right:  10%; 
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

            function funcTableFile(D1, D7, type) {
                var w = 900, h = 500;
                var left = (screen.width / 2) - (w / 2);
                var top = (screen.height / 2) - (h / 2);
                var urlParam = "madiemgd=" + D1 + "&ngaybc=" + D7 + "&type=" + type;
                var url = "/IMS_REPORTS/popupTableGroup.action?" + urlParam;
                popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }

        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;">     
            <s:if test="txtGetData.equalsIgnoreCase('1')">
                <div id="divTitle">
                    <s:hidden name="khoa_tdnn" id="khoa"/>
                    DANH SÁCH PGD ĐÃ GỬI DỮ LIỆU
                </div>
                <div style="height:20px"></div>  
                <table border="1" class="editDelete table11" id="subTable" align="center" style="padding-top: 10px">   
                    <tr>
                        <th style="width: 30px">STT</th>
                        <th style="width: 60px">Mã PGD</th>
                        <th style="width: 150px">Tên PGD</th>
                        <th style="width: 80px">Số xã đã gửi/Tổng</th>
                        <th style="width: 80px">Số tổ TK&VV/Tổng</th>
                    </tr>  
                    <tr>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                        <tr>
                            <s:if test="D8.equalsIgnoreCase('1')">
                                <s:if test="!D6.equalsIgnoreCase('0')">
                                    <td class="D0" style="color: #0000FF"><s:property value="%{#rowstatus.index + 1}" />
                                    <td class="D0" style="color: #0000FF"><s:property value="D1" /></td>
                                    <td style="color: #0000FF"><s:property value="D2" /></td>
                                    <!--<td class="D0" style="color: #0000FF"><s:property value="D4" />/<s:property value="D3" /></td>-->    
                                </s:if>
                                <s:else>
                                    <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                                    <td class="D0"><s:property value="D1" /></td>
                                    <td><s:property value="D2" /></td>
                                    <!--<td class="D0"><s:property value="D4" />/<s:property value="D3" /></td>-->
                                </s:else>
                                <s:if test="!D4.equalsIgnoreCase('0')">
                                    <td class="D0"> 
                                        <a style="text-decoration: underline" 
                                           href="javascript:funcTableFile('<s:property value="D1"/>', '<s:property value="D7"/>', '1')">
                                            <s:property value="D4" />/<s:property value="D3" /></a>
                                    </td>
                                    <td class="D0"> 
                                        <a style="text-decoration: underline" 
                                           href="javascript:funcTableFile('<s:property value="D1"/>', '<s:property value="D7"/>', '2')">
                                            <s:property value="D6" />/<s:property value="D5" /></a>
                                    </td>
                                </s:if>
                                <s:else>
                                    <td class="D0"><s:property value="D4" />/<s:property value="D3" /></td>
                                    <td class="D0"><s:property value="D6" />/<s:property value="D5" /></td>
                                </s:else>
                            </s:if>
                        </tr>
                    </s:iterator>
                </table>
                <table border="1" class="editDelete table22" id="subTable" align="center" style="padding-top: 10px">   
                    <tr>
                        <th style="width: 30px">STT</th>
                        <th style="width: 60px">Mã PGD</th>
                        <th style="width: 150px">Tên PGD</th>
                        <th style="width: 80px">Số xã đã gửi/Tổng</th>
                        <th style="width: 80px">Số tổ TK&VV/Tổng</th>
                    </tr>  
                    <tr>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                        <tr>
                            <s:if test="!D8.equalsIgnoreCase('1')">
                                <s:if test="!D6.equalsIgnoreCase('0')">
                                    <td class="D0" style="color: #0000FF"><s:property value="%{#rowstatus.index + 1}" />
                                    <td class="D0" style="color: #0000FF"><s:property value="D1" /></td>
                                    <td style="color: #0000FF"><s:property value="D2" /></td>
                                    <!--<td class="D0" style="color: #0000FF"><s:property value="D4" />/<s:property value="D3" /></td>-->    
                                </s:if>
                                <s:else>
                                    <td class="D0"><s:property value="%{#rowstatus.index + 1}" /></td>
                                    <td class="D0"><s:property value="D1" /></td>
                                    <td><s:property value="D2" /></td>
                                    <!--<td class="D0"><s:property value="D4" />/<s:property value="D3" /></td>-->
                                </s:else>
                                <s:if test="!D4.equalsIgnoreCase('0')">
                                    <td class="D0"> 
                                        <a style="text-decoration: underline" 
                                           href="javascript:funcTableFile('<s:property value="D1"/>', '<s:property value="D7"/>', '1')">
                                            <s:property value="D4" />/<s:property value="D3" /></a>
                                    </td>
                                    <td class="D0"> 
                                        <a style="text-decoration: underline" 
                                           href="javascript:funcTableFile('<s:property value="D1"/>', '<s:property value="D7"/>', '2')">
                                            <s:property value="D6" />/<s:property value="D5" /></a>
                                    </td>
                                </s:if>
                                <s:else>
                                    <td class="D0"><s:property value="D4" />/<s:property value="D3" /></td>
                                    <td class="D0"><s:property value="D6" />/<s:property value="D5" /></td>
                                </s:else>
                            </s:if>
                        </tr>
                    </s:iterator>
                </table>
            </s:if>
            <s:elseif test="txtGetData.equalsIgnoreCase('2')">
                <div id="divTitle">
                    <s:hidden name="khoa_tdnn" id="khoa"/>
                    DANH SÁCH PGD CHỐT GỬI XL IỆU TRÊN TW
                </div>
                <div style="height:20px"></div>  
                <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px">   
                    <tr>
                        <th style="width: 30px">STT</th>
                        <th style="width: 60px">Mã PGD</th>
                        <th style="width: 150px">Tên PGD</th>
                        <th style="width: 150px">Ngày gửi dữ liệu</th>
                        <th style="width: 80px">Gửi dữ liệu</th>
                    </tr>  
                    <tr>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                        <tr>
                            <s:if test="D4.equalsIgnoreCase('2')">
                                <td class="D0" style="color: #0000FF"><s:property value="%{#rowstatus.index + 1}" />
                                <td class="D0" style="color: #0000FF"><s:property value="D1" /></td>
                                <td style="color: #0000FF"><s:property value="D2" /></td>
                                <td style="color: #0000FF" class="D0"><s:property value="D3" /></td> 
                                <td class="D0" style="color: #0000FF">Đã gửi dữ liệu</td>
                            </s:if>
                            <s:else>
                                <td class="D0"><s:property value="%{#rowstatus.index + 1}" />
                                    <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                           id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D1"/>"/>
                                    <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                           id="D7_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D7"/>"/>
                                </td>
                                <td class="D0"><s:property value="D1" /></td>
                                <td><s:property value="D2" /></td>
                                <td class="D0"><s:property value="D3" /></td> 
                                <td class="D0"> <a style="text-decoration: underline" href="#" onclick="sendData('<s:property value="D1"/>', '<s:property value="D7"/>');">Chốt dữ liệu</a></td>
                            </s:else>


                        </tr>
                    </s:iterator>
                </table>
            </s:elseif>
        </div>      
        <div id="luu_thanhcong"></div>
        <script>

            function sendData(D1, D7) {
                var rows = document.querySelectorAll("td a");
                rows.forEach(function (row) {
                    row.style.pointerEvents = "none"; // Vô hiệu hóa click
                    row.style.color = "gray";         // Thay đổi màu để trông như bị khóa
                });

                var url, sdata;
                url = "send_KPBL_2024_C2.action?" + "madiemgd=" + D1 + "&ngaybc=" + D7,
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
