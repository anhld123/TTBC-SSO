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
                SAO KÊ KHOẢN VAY THU HỒI HỖ TRỢ LÃI SUẤT<br>
                <s:if test="chotsl.equalsIgnoreCase('1')" ><a class="color_11">(Đơn vị đã gửi dữ liệu)</a></s:if>
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Đã chốt dữ liệu lên TW)</a></s:if>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
            </div>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng.
            </div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th class="STT1" rowspan="2">STT</th>                           
                    <th class="STT2" rowspan="2">Tên khách hàng</th>  
                    <th class="STT2" rowspan="2">Mã món vay</th>  
                    <th class="STT6" rowspan="2">Trạng thái</th>  
                    <th class="STT7" rowspan="2">Chương trình vay vốn</th>
                    <th class="STT2" rowspan="2">Nguồn vốn</th>
                    <th class="STT4" rowspan="2">Ngày giải ngân</th>
                    <th class="STT2" rowspan="2">Lãi suất cho vay</th>
                    <th class="STT2" rowspan="2">Dư nợ giải ngân HTLS</th>   
                    <th class="STT2" colspan="2">Số tiền HTLS phải thu hồi, trong đó</th>
                    <th class="STT2" colspan="3">Thông tin bút toán thu hồi</th> 
                    <th class="STT3" rowspan="2">Nguyên nhân thu hồi HTLS</th>  

                </tr>
                <tr>
                    <th class="STT2">Tổng số đã HTLs phải thu hồi</th>      
                    <th class="STT2">Số tiền đã thu hồi</th> 
                    <th class="STT2">Số bút toán hạch toán</th> 
                    <th class="STT4">Ngày hạch toán</th> 
                    <th class="STT2">Số tiền</th> 
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
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(13)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(14)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(15)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" />
                            <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                            <input type="hidden" value="<s:property value="%{#rowstatus.index + 1}" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                            <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
                            <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                            <input type="hidden" value="<s:property  value="CO_TONGHOP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/>
                            <input type="hidden" value="<s:property  value="NGUOI_NHAP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_NHAP"/>
                            <input type="hidden" value="<s:property  value="NAMBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NAMBC"/>
                            <input type="hidden" value="<s:property  value="MAPGD" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                            <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>
                            <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>
                            <input type="hidden" value="<s:property  value="D2" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" id="D2_<s:property  value='%{#rowstatus.index}' />"/>
                            <input type="hidden" value="<s:property  value="D3" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                            <input type="hidden" value="<s:property  value="D4" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"/>
                            <input type="hidden" value="<s:property  value="D5" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"/>
                            <input type="hidden" value="<s:property  value="D6" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6"/>
                            <input type="hidden" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7"/>
                            <input type="hidden" value="<s:property  value="D8" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"/>
                            <input type="hidden" value="<s:property  value="D9" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/>
                            <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                            <input type="hidden" value="<s:property  value="D13" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13"/>

                        </td>
                        <td><s:property  value="TEN" /></td>
                        <td class="D0"><s:property  value="D2" /></td>
                        <td class="D0">
                            <a id="addline1" style="text-decoration: underline" href="#" 
                               onclick="addline('<s:property  value="D2" />', '2');">Xóa</a>
                        </td>
                        <td style="width: 150px"><s:property  value="D12" /> - <s:property  value="D13" /></td>

                        <td class="D0">
                            <select style="border: hidden" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D14" id="D14_<s:property  value='%{#rowstatus.index}' />" > 
                                <option value="0" style="text-align: center" <s:if test="D14.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="D14.equalsIgnoreCase('1')"> selected </s:if>>Trung ương</option>
                                <option value="2" <s:if test="D14.equalsIgnoreCase('2')"> selected </s:if>>Địa phương</option>     
                                </select>
                            </td>
                            <td class="D0"><s:property value='D7'/></td>
                        <td class="D0"><s:property  value="D9" /></td>
                        <td> <input type="text" value="<s:property  value="D15  != null ? D15 : 0" />" id="D15_<s:property  value='%{#rowstatus.index}' />" 
                                    name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="number"/>
                        </td> 
                        <td> <input type="text" value="<s:property  value="D16 != null ? D16 : 0" />" id="D16_<s:property  value='%{#rowstatus.index}' />" 
                                    name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="number"/>
                        </td> 
                        <td> <input type="text" value="<s:property  value="D17 != null ? D17 : 0" />" id="D17_<s:property  value='%{#rowstatus.index}' />" 
                                    name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="number"/>
                        </td>  
                        <td> <input type="text" value="<s:property  value="D18 != null ? D18 : 0" />" id="D18_<s:property  value='%{#rowstatus.index}' />" 
                                    name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" />
                        </td> 
                        <td class="D0">
                            <input style="width: 75px; text-align: center" type="text" readonly="readonly" class="cssDate" 
                                   id="D19_<s:property value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D19" 
                                   value="<s:property value='D19'/>">   
                        </td>
                        <td class="D0">
                            <input type="text" value="<s:property  value="D21 != null ? D20 : 0" />" id="D20_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="number"/>
                        </td>
                        <td class="D0"><textarea style="width: 98%" placeholder="Nhập tối đa 500 ký tự" id="D21_<s:property  value='%{#rowstatus.index}' />" 
                                                 name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D21" maxlength="500"><s:property value='D21'/></textarea>
                        </td>

                    </s:iterator>
                <tr>
                    <td></td>
                    <td></td>
                    <td class="D0">
                        <input type="text" id="sSoku" name="sSoku" placeholder="Mã món vay" style="font-size: 12px"/>
                    </td>
                    <td class="D0">
                        <a id="addline" style="text-decoration: underline" href="#" 
                           onclick="addline(document.getElementById('sSoku').value, '1');">Thêm</a>
                    </td>

                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>  
            </table>
        </div>
        <div id="luu_thanhcong"></div>
        <script>

            $(function () {
                setCssStyle();
            });
            function setCssStyle() {
                $(".cssDate").datepicker(
                        {
                            dateFormat: 'dd/mm/yy',
                            showOn: "button",
                            buttonImage: "img/icon-ui_datepicker.png",
                            buttonImageOnly: true,
                            // dateFormat: 'dd/mm/yy',
                            showButtonPanel: true,
                            buttonText: "icono",
                            changeMonth: true,
                            changeYear: true,
                            yearRange: "c-10:c+10"
                        });
            }

            function addline(sSoku, stype) {
                var table = document.getElementById("subTable");
                var chotsl = document.getElementById("chotsl").value;
//                alert(chotsl);
                if (chotsl === "1" || chotsl === "2")
                {
                    alert("Đơn vị đã chốt số liệu, không thể thao tác!");
                    return;
                }
                if (sSoku.length < 16 || isNaN(sSoku)) {
                    alert("Mã món vay không hợp lệ! Mã phải có độ dài tối thiểu 16 ký tự và chỉ chứa số.");
                    return;
                }

                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    try {
                        var D2 = document.getElementById("D2_" + i).value;
                        if (sSoku === D2 && stype === '1')
                        {
                            alert("Mã món vay " + sSoku + " đã tồn tại, không thể thêm!");
                            return;
                        }
                    } catch (e) {
                    }
                }
                $.ajax({
                    type: "GET",
                    url: "idAddline_BCQT_MS11C.action?" + "sSoku=" + sSoku + "&stype=" + stype,
                    success: function (res) {
                        var status = parseInt(res.status);
                        if (status === 1) {
                            if (stype === "1") {
                                alert('Thêm mã món vay ' + sSoku + ' thành công.');
                            } else if (stype === "2") {
                                alert('Xóa mã món vay ' + sSoku + ' thành công.');
                            }
                            onLoadData();
                        } else {
                            alert('Thao tác lỗi: ' + res.message);
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
