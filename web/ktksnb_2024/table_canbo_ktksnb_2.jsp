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
        width: 50%;
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
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var popWindow;
            var max_row = 0;
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
                $(".STT1").css({"width": "30px"});
                $(".STT2").css({"width": "90px"});
                $(".STT3").css({"width": "150"});
                $(".STT4").css({"width": "70%"});
                $(".STT5").css({"width": "70px"});
                $(".STT6").css({"width": "80px"});
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
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;">     

            <div id="divTitle">
                DANH SÁCH PGD CHỐT CÁN BỘ CHUYÊN TRÁCH
            </div>
            <div style="height:20px"></div>  
            <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px;width: 70%">   
                <tr>
                    <th style="width: 30px">STT</th>
                    <th style="width: 60px">Mã PGD</th>
                    <th style="width: 100px">Tên PGD</th>
                    <th style="width: 100px">Người gửi dữ liệu</th>
                    <th style="width: 100px">Ngày gửi dữ liệu</th>
                    <th style="width: 120px">Mở dữ liệu</th>
                    <!--<th style="width: 120px">Gửi dữ liệu</th>-->
                </tr>  
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                    <!--<th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>-->
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>
                        <s:if test="D4.equalsIgnoreCase('0')">
                            <td class="D0" style="color: #ff0000"><s:property value="%{#rowstatus.index + 1}" />
                            <td class="D0" style="color: #ff0000"><s:property value="D1" /></td>
                            <td style="color: #ff0000"><s:property value="D2" /></td>
                            <td class="D0" style="color: #ff0000"><s:property value="D8" /></td>
                            <td style="color: #ff0000" class="D0"><s:property value="D3" /></td> 
                            <td class="D0" style="color: #ff0000">PGD chưa gửi dữ liệu</td>
                        </s:if>
                        <s:else> 
                            <td class="D0" style="color: #0000FF"><s:property value="%{#rowstatus.index + 1}" />
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                       id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D1"/>"/>
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                       id="D7_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D7"/>"/>
                            </td>
                            <td class="D0" style="color: #0000FF">
                                <a style="text-decoration: underline" 
                                   href="javascript:funcTableFile('<s:property value="D1"/>', '<s:property value="D7"/>', '1')">
                                    <s:property value="D1" /></a></td>
                            <td style="color: #0000FF"><s:property value="D2" /></td>
                            <td class="D0" style="color: #0000FF"><s:property value="D8" /></td>
                            <td class="D0" style="color: #0000FF"><s:property value="D3" /></td> 
                            <td class="D0"> <a style="text-decoration: underline" href="#" onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D7"/>', '1');">Mở dữ liệu</a></td>
                        </s:else>
                    </tr>
                </s:iterator>
            </table>
        </div>      
        <div id="luu_thanhcong"></div>
        <script>

            function cancelAssign(D1, D7, type) {
                var url, sdata;
                url = "status_KTKSNB_2024_00.action?" + "madiemgd=" + D1 + "&ngaybc=" + D7 + "&type=" + type,
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
                            alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                            onLoadData();
                        }
                    },
                    error: function (request) {
                        alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                        onLoadData();
                    }
                });
            }

            function funcTableFile(D1, D7, type) {
                var w = 900, h = 500;
                var left = (screen.width / 2) - (w / 2);
                var top = (screen.height / 2) - (h / 2);
                var urlParam = "madiemgd=" + D1 + "&ngaybc=" + D7 + "&type=" + type;
                var url = "/IMS_REPORTS/popupTableCanbo.action?" + urlParam;
                popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }
        </script>
    </body>
</html>
