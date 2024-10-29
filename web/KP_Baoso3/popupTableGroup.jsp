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
        <script src="js/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" href="js/3.6.0/jquery-ui.css">
        <script src="js/3.6.0/jquery-ui.js"></script>
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
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw">   
            <s:if test="txtGetData.equalsIgnoreCase('2')">
                <div id="divTitle" style="text-align: center">
                    DANH SÁCH TỔ TK&VV ĐÃ GỬI DỮ LIỆU
                    <input type="hidden" value="<s:property value="chotsl_tw"/>" name="chotsl_tw" id="chotsl_tw"/> 
                </div>
                <div style="height:10px"></div> 
                <table border="1" class="editDelete" id="subTable" align="center">               
                    <tr> 
                        <th class="STT1">STT</th>                           
                        <th class="STT6">Mã xã</th>  
                        <th class="STT6">Tên xã</th>  
                        <th class="STT6">Mã tổ</th>
                        <th class="STT6">Tên tổ</th>
                        <th class="STT2">Ngày gửi dữ liệu</th>      
                        <th class="STT6">Mở khóa dữ liệu</th> 
                    </tr>         

                    <tr>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                        <tr id="tablefix"> 
                            <td class="D0"><s:property value="%{#rowstatus.index + 1}" />
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                       id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D1"/>"/>
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                       id="D3_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D3"/>"/>
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                       id="D7_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D7"/>"/>
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" 
                                       id="D8_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D8"/>"/></td>
                            <td class="D0" ><s:property value="D1" /></td>
                            <td class="D0" ><s:property value="D2" /></td>
                            <td class="D0" > <a style="text-decoration: underline" 
                                                href="javascript:funcTableFile('<s:property value="D8"/>-<s:property value="D1"/>', '<s:property value="D3"/>','<s:property value="D7"/>', '3')">
                                    <s:property value="D3" /></a></td>
                            <td><s:property value="D4" /></td>
                            <td class="D0"><s:property value="D5" /></td>
                            <s:if test="D6.equalsIgnoreCase('1')">
                                <td class="D0"> <a href="#" onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D3"/>', '<s:property value="D7"/>', '2');">Mở dữ liệu</a></td>
                            </s:if>
                            <s:else><td class="D0" style="color: red">Chưa gửi dữ liệu</td></s:else>
                            </tr>
                    </s:iterator>

                </table>
            </s:if>
            <s:elseif test="txtGetData.equalsIgnoreCase('1')">
                <div id="divTitle" style="text-align: center">
                    DANH SÁCH XÃ ĐÃ GỬI DỮ LIỆU
                    <input type="hidden" value="<s:property value="chotsl_tw"/>" name="chotsl_tw" id="chotsl_tw"/> 
                </div>
                <div style="height:10px"></div> 
                <table border="1" class="editDelete" id="subTable" align="center">               
                    <tr> 
                        <th class="STT1">STT</th>                           
                        <th class="STT6">Mã xã</th>  
                        <th class="STT6">Tên xã</th>     
                        <th class="STT3">Mở khóa dữ liệu</th> 
                    </tr>         

                    <tr>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                        <tr id="tablefix"> 
                            <td class="D0"><s:property value="%{#rowstatus.index + 1}" />
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                       id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D1"/>"/>
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                       id="D3_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D3"/>"/>
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                       id="D7_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D7"/>"/>
                                <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" 
                                       id="D8_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D8"/>"/></td>
                            <td class="D0" ><s:property value="D1" /></td>
                            <td><s:property value="D2" /></td>
                            <s:if test="D6.equalsIgnoreCase('1')">
                                <td class="D0"> <a href="#" onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D3"/>', '<s:property value="D7"/>', '1');">Mở dữ liệu</a></td>
                            </s:if>
                            <s:else><td class="D0" style="color: red">Chưa gửi dữ liệu</td></s:else>
                            </tr>
                    </s:iterator>

                </table>
            </s:elseif>

            <div style="text-align: center">
                <br>
                <input type="button" value="Thoát" name="cmdLuu" id="cmdLuu"/></div>
        </div>
        <div id="luu_thanhcong"></div>
    </body>
    <script>
        $("#cmdLuu").click(function () {

            window.opener.document.getElementById('loadDatatmp').click();
            window.close();
        });

        function cancelAssign(D1, D3, D7, type) {
            var chotsl_tw = $('#chotsl_tw').val(); // Lấy giá trị của input ẩn

            if (chotsl_tw === "2") {
                alert('Dữ liệu đã được chốt lên TW, không thể thực hiện thao tác này!');
                return; // Dừng hàm nếu chotsl_tw bằng 2
            }

            $.ajax({
                type: "GET",
                url: "unlock_Baoso3_c2.action?" + "smaxa=" + D1 + "&smato=" + D3 + "&sngaybc=" + D7 + "&type=" + type,
                success: function (res) {
                    var status = parseInt(res.status);
                    //alert(status);
                    if (status === 1) {
                        alert('Mở phê duyệt thành công!');
                        location.reload();
                    } else {
                        alert('Mở phê duyệt lỗi: ' + res.message);
                    }
                },
                error: function (res) {
                    alert("Mở phê duyệt lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                }
            });
        }

        function funcTableFile(D1, D3, D7, type) {
            var w = 1000, h = 400;
            var left = (screen.width / 2) - (w / 2);
            var top = (screen.height / 2) - (h / 2);
            var urlParam = "madiemgd=" + D1 + "&smato=" + D3 + "&ngaybc=" + D7 + "&type=" + type;
            var url = "/IMS_REPORTS/popupTableGroup.action?" + urlParam;
            var childPopupName = "IMS_REPORTS_CHILD_" + new Date().getTime();
            popWindow = window.open(url, childPopupName, "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        }

    </script>
</html>
