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
        width: 85%;
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
    ::placeholder {
        color: red;
        opacity: 1; /* Firefox */
    }

    ::-ms-input-placeholder { /* Edge 12-18 */
        color: red;
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
                    $(".STT1").css({"width": "50px"});
                    $(".STT2").css({"width": "150px"});
                    $(".STT3").css({"width": "250px"});
                    $(".STT4").css({"width": "110px"});
                    $(".STT5").css({"width": "150px"});
                    $(".STT6").css({"width": "65px"});
                    $(".STT7").css({"width": "80px"});
                    $(".TD_NGUYENGIA").css({"width": "80px"});
                    $(".TD_THUTU").css({"width": "30px"});
                    $(".TD_CHITIEU").css({"width": "220px"});
                    $(".TEN_KH").css({"width": "100%"});
                });

            </script>        
        </head>
        <body>
            <div style="overflow:scroll; width: 98%;height: 400px;">    
                <div id="divTitle">
                    THUYẾT MINH CHI TIẾT CÁC KHOẢN PHẢI THU (TRÊN 365 NGÀY)<br>
                    <s:if test="chotsl.equalsIgnoreCase('1')" ><a class="color_11">(Đơn vị đã gửi dữ liệu)</a></s:if>
                    <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Đã chốt dữ liệu lên TW)</a></s:if>
                    <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
                </div>
                <div id="divDonvitinh">
                    Đơn vị tính: Đồng.
                </div>
                <table border="1" class="editDelete" id="subTable" align="center">               
                    <tr> 
                        <th class="STT1">STT</th>                           
                        <th>Danh mục các khoản phải thu</th>  
                        <th class="STT4">Tài khoản hạch toán (GL)</th>  
                        <th class="STT5">Số tiền</th>  
                        <th style="width: 30%">Nguyên nhân, biện pháp xử lý</th>
                            <th class="STT6">Trạng thái</th>  
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
                        <!-- Hidden input fields -->
                        <input type="hidden" value="<s:property value='THUTU' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].THUTU"/>
                        <input type="hidden" value="<s:property value='%{#rowstatus.index + 1}' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].TT_HIENTHI"/>
                        <input type="hidden" value="<s:property value='MA' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].MA" id="MA_<s:property value='%{#rowstatus.index}' />"/>                             
                        <input type="hidden" value="<s:property value='TEN' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].TEN"/>
                        <input type="hidden" value="<s:property value='CO_TONGHOP' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].CO_TONGHOP"/>
                        <input type="hidden" value="<s:property value='NGUOI_NHAP' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].NGUOI_NHAP"/>
                        <input type="hidden" value="<s:property value='NAMBC' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].NAMBC"/>
                        <input type="hidden" value="<s:property value='MAPGD' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].MAPGD"/>
                        <input type="hidden" value="<s:property value='MACN' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].MACN"/>
                        <input type="hidden" value="<s:property value='D1' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D1"/>
                        <input type="hidden" value="<s:property value='D10' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D10" id="D10_<s:property value='%{#rowstatus.index}' />"/>
                        <input type="hidden" value="<s:property value='KIEUIN' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].KIEUIN"/>
                        <input type="hidden" value="<s:property value='D12' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D12" id="D12_<s:property value='%{#rowstatus.index}' />"/>                             

                        <!-- Main row -->
                        <s:if test="D10.equalsIgnoreCase('1')">
                            <tr>

                                <td class="D0" style="font-weight: bold; background: #ddd;">
                                    <s:property value="D1"/>
                                </td>
                                <td style="font-weight: bold; background: #ddd;">
                                    <s:property value="D2"/>
                                </td>
                            <input type="hidden" value="<s:property value='D2' />" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D2"/>
                            <td style="background: #ddd;"></td>
                            <td style="background: #ddd;"></td>
                            <td style="background: #ddd;"></td>
                            <td style="background: #ddd;"></td>
                            </tr>

                            <!-- Sub row -->
                            <tr style="background: #ffff99">
                                    <td class="D0" ></td>
                                <input type="hidden" value="<s:property value='MA' />" name="skey1" id="skey1_<s:property value='%{#rowstatus.index}' />"/>
                                <input type="hidden" value="<s:property value='D12' />" name="skey2" id="skey2_<s:property value='%{#rowstatus.index}' />"/>
                                <td class="D0"><input type="text" style="font-size: 12px;width: 98%" placeholder="Nhập danh mục các khoản phải thu" id="sdanhmuc_<s:property value='%{#rowstatus.index}' />" 
                                                      name="sdanhmuc_<s:property value='%{#rowstatus.index}' />" maxlength="500"/>
                                </td>
                                <td class="D0"><input type="text" placeholder="Nhập tài khoản GL" maxlength="10" id="sGL_<s:property value='%{#rowstatus.index}' />" name="sGL_<s:property value='%{#rowstatus.index}' />" style="font-size: 12px;text-align: right"/></td>
                                <td class="D0"><input type="text" value="0" id="ssotien_<s:property value='%{#rowstatus.index}' />" maxlength="15" name="ssotien_<s:property value='%{#rowstatus.index}' />" style="font-size: 12px;" class="number"/></td>
                            <td class="D0"><input type="text" style="font-size: 12px;width: 98%" placeholder="Nhập nguyên nhân, biện pháp xử lý" id="snguyennhan_<s:property value='%{#rowstatus.index}' />" 
                                                  name="snguyennhan_<s:property value='%{#rowstatus.index}' />" maxlength="500"/>
                                </td>
                                <td class="D0">
                                    <a id="addline" href="#" title="Thêm GL"
                                       onclick="addRow(
                                                       document.getElementById('sdanhmuc_<s:property value='%{#rowstatus.index}' />').value,
                                                       document.getElementById('sGL_<s:property value='%{#rowstatus.index}' />').value,
                                                       document.getElementById('ssotien_<s:property value='%{#rowstatus.index}' />').value,
                                                       document.getElementById('snguyennhan_<s:property value='%{#rowstatus.index}' />').value,
                                                       document.getElementById('skey1_<s:property value='%{#rowstatus.index}' />').value,
                                                       document.getElementById('skey2_<s:property value='%{#rowstatus.index}' />').value, '1'
                                                       );">&#10004;</a>
                                </td>
                                </tr>
                            </s:if>
                            <s:else>
                                <tr>
                                    <td class="D0"><s:property value="D1"/></td>
                                    <td class="D0"><input type="text" style="width: 98%" id="D2_<s:property  value='%{#rowstatus.index}' />" 
                                                          name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D2" value="<s:property value='D2'/>"/>
                                    </td>
                                    <td>
                                        <input type="text" value="<s:property  value="D3" />" maxlength="15" id="D3_<s:property  value='%{#rowstatus.index}' />" maxlength="10"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" style="text-align: right"/>
                                    </td>
                                    <td>
                                        <input type="text" value="<s:property  value="D4" />" id="D4_<s:property  value='%{#rowstatus.index}' />" 
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="number"/>
                                    </td>
                                    <td class="D0"><input type="text" style="width: 98%" id="D11_<s:property  value='%{#rowstatus.index}' />" 
                                                          name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D11" maxlength="500" value="<s:property value='D11'/>"/>
                                    </td>
                                    <td class="D0">
                                        <a id="addline1" style="color: red" href="#" title="Xóa GL"
                                           onclick="addRow('<s:property  value="D2" />',
                                                           '<s:property  value="D3" />',
                                                           '<s:property  value="D4" />',
                                                           '<s:property  value="D11" />',
                                                           '<s:property  value="MA" />',
                                                           '<s:property  value="D12" />', '2');">&#10006;</a>
                                    </td>
                                </tr>
                            </s:else>
                        </s:iterator>

                    </table>
                </div>
                <div id="luu_thanhcong"></div>
                <script>
                    function addRow(sdanhmuc, sGL, ssotien, snguyennhan, skey1, skey2, stype) {
                        var table = document.getElementById("subTable");
                        var rows = table.querySelectorAll("td a");
                        function unlockLinks() {
                            rows.forEach(function (row) {
                                row.style.pointerEvents = "auto"; // Kích hoạt lại sự kiện chuột
                                row.style.color = "red"; // Trả về màu mặc định
                            });
                        }
                        // Khóa các liên kết trong bảng
                        rows.forEach(function (row) {
                            row.style.pointerEvents = "none";
                            row.style.color = "gray";
                        });
                        var chotsl = document.getElementById("chotsl").value;
                        if (chotsl === "1" || chotsl === "2")
                        {
                            alert("Đơn vị đã chốt số liệu, không thể thao tác!");
                            unlockLinks();
                            return;
                        }
                        var rowcount = table.rows.length;
                        rowcount = rowcount > max_row ? rowcount : max_row;
                        for (var i = 0; i < rowcount; i++)
                        {
                            try {
                                if (sdanhmuc === "") {
                                    alert("Chưa nhập DANH MỤC CÁC KHOẢN PHẢI THU!");
                                    document.getElementById("sdanhmuc_" + i).style.backgroundColor = "#EEAFA6";
                                    unlockLinks();
                                    return;
                                }
                                if (sGL.length !== 10 || isNaN(sGL)) {
                                    alert("GL không hợp lệ, số ký tự là " + sGL.length + " ký tự hợp lệ là 10!");
                                    document.getElementById("sGL_" + i).style.backgroundColor = "#EEAFA6";
                                    unlockLinks();
                                    return;
                                }

                            } catch (e) {
                                unlockLinks();
                            }
                        }
                        $.ajax({
                            type: "GET",
                            url: "addLineC1_18B.action?" + "sdanhmuc=" + sdanhmuc + "&sGL=" + sGL + "&ssotien=" + ssotien + "&snguyennhan=" + snguyennhan + "&skey1=" + skey1 + "&skey2=" + skey2 + "&stype=" + stype,
                            success: function (res) {
                                var status = parseInt(res.status);
                                //alert(status);
                                if (status === 1) {
                                    if (stype === "1") {
                                        alert("Thêm GL " + sGL + " thành công");
                                    } else {
                                        alert("Xóa GL " + sGL + " thành công");
                                    }
                                    onLoadData();
                                } else {
                                    alert('Lỗi: ' + res.message);
                                    unlockLinks();
                                }
                            },
                            error: function (res) {
                                alert("Lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                            }
                        });
                    }

                </script>
            </body>
        </html>
