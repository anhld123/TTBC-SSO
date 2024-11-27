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
        width: 98%;
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
    @-webkit-keyframes my {
        0% { color: red; } 
        50% { color: #fff;  } 
        100% { color: red;  } 
    }
    @-moz-keyframes my { 
        0% { color: red;  } 
        50% { color: #fff;  }
        100% { color: red;  } 
    }
    @-o-keyframes my { 
        0% { color: red; } 
        50% { color: #fff; } 
        100% { color: red;  } 
    }
    @keyframes my { 
        0% { color: red;  } 
        50% { color: #fff;  }
        100% { color: red;  } 
    } 
    .color_11 {
        background:#fff;
        font-size:14px;
        font-weight:bold;
        -webkit-animation: my 700ms infinite;
        -moz-animation: my 700ms infinite; 
        -o-animation: my 700ms infinite; 
        animation: my 700ms infinite;
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
//                initTable();
            });
            $(document).ready(function () {
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('input.number2').css({"text-align": "right"});
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
        <div style="overflow:scroll; width: 98%;height: 400px;">             
            <div id="divTitle">
                DANH SÁCH KẾ HOẠCH ĐÃ ĐĂNG KÝ<br>
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Chi nhánh đã chốt số liệu)</a></s:if>
                <s:elseif test="chotsl.equalsIgnoreCase('1')" ><a class="color_11">(Phòng giao dịch đã gửi dữ liệu)</a></s:elseif>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
                <input type="hidden" value="<s:property value="chotsl_tw"/>" name="chotsl_tw" id="chotsl_tw"/> 
            </div>
            <div style="height:5px"></div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th class="STT2" >STT</th>                           
                    <th >Kế hoạch đăng ký & điều chỉnh</th>  
                    <th class="STT2" >Ghi chú</th> 
                    <th class="STT3" >Trạng thái</th>  
                </tr>
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix" > 
                        <td class="D0" ><s:property value="%{#rowstatus.index + 1}" /></td>
                        <td><a style="text-decoration: underline; color: #3dc21b" 
                               href="javascript:funcTableFile('<s:property value="KHOA"/>','<s:property value="D4"/>', '<s:property value="D5"/>','<s:property value="D6"/>','<s:property value="MAPGD"/>-<s:property value="MACN"/>','<s:property value="D7"/>','<s:property value="D9"/>','<s:property value="chotsl"/>', '1')">
                                <s:property value="D1"/><s:property value="D2"/><s:property value="D3"/></a> 
                        </td>
                        <td class="D0">
                            <s:if test="KHOA.equalsIgnoreCase('KH_HUYEN') || KHOA.equalsIgnoreCase('KH_TINH')">Kế hoạch gốc</s:if>
                            <s:else>Điều chỉnh</s:else>
                            </td>
                            <td class="D0">
                            <s:if test="KHOA.equalsIgnoreCase('KH_HUYEN')|| KHOA.equalsIgnoreCase('KH_TINH')">
                                <a id="linkaa" style="text-decoration: underline" href="#" 
                                   onclick="cancelAssignList('<s:property value="KHOA"/>', '<s:property value="D4"/>', '<s:property value="D5"/>', '<s:property value="D6"/>', '<s:property value="MAPGD"/>', '<s:property value="D7"/>', '<s:property value="D9"/>', '<s:property value="chotsl"/>', '2')">
                                    <s:if test="D10.equalsIgnoreCase('1')">Thực hiện</s:if>
                                    <s:else>Không thực hiện</s:else>
                                    </a>
                            </s:if>
                            <s:else>
                                <a id="deletePlanLink" style="text-decoration: underline" href="#" 
                                   onclick="cancelAssignList('<s:property value="KHOA"/>', '<s:property value="D4"/>', '<s:property value="D5"/>', '<s:property value="D6"/>', '<s:property value="MAPGD"/>', '<s:property value="D7"/>', '<s:property value="D9"/>', '<s:property value="chotsl"/>', '1')">
                                    Xóa
                                </a></td>
                            </s:else>
                    </tr>
                </s:iterator>
            </table><div style="height:20px"></div> 
        </div>
        <div id="luu_thanhcong"></div>
        <script>
            function funcTableFile(dc_khoa, dc_maxa, dc_macb, dc_thang, dc_mapgd, dc_nam, dc_cap, dc_chot, type) {
                var screenWidth = screen.width, screenHeight = screen.height;
                var w = screenWidth / 2;
                var h = screen.height;
                var left = (screenWidth - w) / 2;
                var top = (screenHeight - h) / 2;
                var urlParam = "dc_khoa=" + dc_khoa + "&dc_maxa=" + dc_maxa + "&dc_macb=" + dc_macb + "&dc_thang=" + dc_thang + "&dc_mapgd=" + dc_mapgd + "&dc_nam=" + dc_nam + "&dc_cap=" + dc_cap + "&dc_chot=" + dc_chot + "&type=" + type;
                var url = "/IMS_REPORTS/popupTableDchinh.action?" + urlParam;
                popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ", directories=no, status=no, menubar=no, personalbar=no, resizable=yes, location=no, scrollbars=yes, toolbar=no, border=no");
                popWindow.focus();
            }


            function cancelAssignList(dc_khoa, dc_maxa, dc_macb, dc_thang, dc_mapgd, dc_nam, dc_cap, dc_chot, type) {
                var url, sdata;
                url = "delete_KTKSNB_02_list.action?" + "dc_khoa=" + dc_khoa + "&dc_maxa=" + dc_maxa + "&dc_macb=" + dc_macb + "&dc_thang=" + dc_thang + "&dc_mapgd=" + dc_mapgd + "&dc_nam=" + dc_nam + "&dc_cap=" + dc_cap + "&dc_chot=" + dc_chot + "&type=" + type,
                        sdata = jQuery("#frmdata").serialize();
                $("#viewData").html('<img src="img/loading.gif"/>');
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        if (data === "200") {
                            if (type === "1")
                            {
                                alert("Xóa điều chỉnh thành công!");
                            } else {
                                alert("Thao tác thành công!");
                            }
                        } else if (data === "100") {
                            alert("Lỗi: Đơn vị đã gửi dữ liệu không thể thao tác!");
                        } else {
                            alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                        }
                        onLoadData();
                    }
                    ,
                    error: function (request) {
                        alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                        onLoadData();
                    }
                });
            }
        </script>
    </body>
</html>
