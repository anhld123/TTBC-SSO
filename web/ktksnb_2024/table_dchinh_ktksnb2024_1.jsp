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
                initTable();
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
            function initTable()
            {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    try {
                        var D6 = document.getElementById("D6_" + i).value;
                        var KIEUIN = document.getElementById("KIEUIN_" + i).value;
                        if (D6 === "" && KIEUIN === "3")
                        {
                            document.getElementById("D6_" + i).value = 0;
                        }
                    } catch (e) {
                    }
                }

            }
        </script>        
    </head>
    <body>
        <div style="overflow:scroll; width: 80%;height: 400px;">             
            <div id="divTitle">
                ĐIỀU CHỈNH KẾ HOẠCH<br>
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Chi nhánh đã chốt số liệu)</a></s:if>
                <s:elseif test="chotsl.equalsIgnoreCase('1')" ><a class="color_11">(Phòng giao dịch đã gửi dữ liệu)</a></s:elseif>
                    <div style="height:10px"></div>
                    <div style="color: red; background: yellow; text-align: left; font-weight: bold; width: 98%; font-size: 14px">
                    <s:property value="title1" /> 
                    <s:if test="check_cn.equalsIgnoreCase('0')">
                        <s:iterator value="#attr.lstDulieuNt" status="rowStatus">
                            <s:if test="#rowStatus.first">
                                &raquo;&raquo; <a id="deletePlanLink" style="text-decoration: underline" href="#" 
                                                  onclick="cancelAssign('<s:property value="chotsl"/>', '<s:property value="MAPGD"/>', '<s:property value="D3"/>', '<s:property value="D4"/>', '<s:property value="D5"/>', '<s:property value="NGAYBC"/>', '<s:property value="KHOA"/>', '<s:property value="D5"/>', '1');">
                                    Xóa điều chỉnh tháng <s:property value="D5"/>
                                </a></s:if></s:iterator>
                                &nbsp;- Đổi tháng k.tra 
                                <select id="select1" name="select1" onchange="updateMonthText()">
                                    <option value="0">--Chọn--</option>
                                    <option value="1">Tháng 1</option>
                                    <option value="2">Tháng 2</option>
                                    <option value="3">Tháng 3</option>
                                    <option value="4">Tháng 4</option>
                                    <option value="5">Tháng 5</option>
                                    <option value="6">Tháng 6</option>
                                    <option value="7">Tháng 7</option>
                                    <option value="8">Tháng 8</option>
                                    <option value="9">Tháng 9</option>
                                    <option value="10">Tháng 10</option>
                                    <option value="11">Tháng 11</option>
                                    <option value="12">Tháng 12</option>
                                </select>

                        <s:iterator value="#attr.lstDulieuNt" status="rowStatus">
                            <s:if test="#rowStatus.first">

                                &nbsp; &raquo;&raquo; 
                                <a id="selectLink" style="text-decoration: underline" href="#" 
                                   onclick="cancelAssign('<s:property value="chotsl"/>', '<s:property value="MAPGD"/>', '<s:property value="D3"/>', '<s:property value="D4"/>', document.getElementById('select1').value, '<s:property value="NGAYBC"/>', '<s:property value="KHOA"/>', '<s:property value="D5"/>', '2');">
                                    Chuyển sang tháng
                                </a>
                            </s:if>
                        </s:iterator>
                    </s:if>
                </div>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
                <input type="hidden" value="<s:property value="chotsl_tw"/>" name="chotsl_tw" id="chotsl_tw"/> 
            </div>
            <div style="height:5px"></div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th class="STT1" >STT</th>                           
                    <th class="STT4" >Nội dung</th>  
                    <th class="STT2" >Đơn vị</th>  
                    <th class="STT6" >Số liệu đăng ký</th>
                    <th class="STT6" style="color: red">Số liệu điều chỉnh</th>
                </tr>
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                    <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>    
                    <input type="hidden" value="<s:property  value="KHOA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KHOA"/>  
                    <input type="hidden" value="<s:property value="TT_HIENTHI" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                    <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
                    <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                    <input type="hidden" value="<s:property  value="CO_TONGHOP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/>
                    <input type="hidden" value="<s:property  value="NGUOI_NHAP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_NHAP"/>
                    <input type="hidden" value="<s:property  value="NAMBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NAMBC"/>
                    <input type="hidden" value="<s:property  value="NGAYBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGAYBC"/>
                    <input type="hidden" value="<s:property  value="MAPGD" />"name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                    <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>
                    <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>
                    <input type="hidden" value="<s:property  value="D3" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                    <input type="hidden" value="<s:property  value="D4" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"/>
                    <input type="hidden" value="<s:property  value="D5" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"/>
                    <input type="hidden" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7"/>
                    <input type="hidden" value="<s:property  value="D8" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"/>
                    <input type="hidden" value="<s:property  value="D9" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/>
                    <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                    <input type="hidden" value="<s:property  value="KIEUIN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KIEUIN" id="KIEUIN_<s:property  value='%{#rowstatus.index}' />"/>
                    <input type="hidden" value="<s:property  value="NHAPTAY" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/>

                    <td class="D0" <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="TT_HIENTHI" /></td>
                    <td <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="TEN" /></td>
                    <td class="D0" <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="D1" /></td>
                        <td style="background:  #E5E5E5">
                            <input type="text" value="<s:property  value="D2" />"
                               id="D2_<s:property  value='%{#rowstatus.index}' />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="number" readonly
                               <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold"</s:if>/>
                        </td>    
                        <td style="background:  #E5E5E5">
                            <input type="text" value="<s:property  value="D6" />"
                               id="D6_<s:property  value='%{#rowstatus.index}' />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number"
                               <s:if test="KIEUIN.toString().equalsIgnoreCase('1')"> style="font-weight: bold" readonly</s:if>/>
                        </td>
                        </tr>
                </s:iterator>
            </table><div style="height:20px"></div> 
        </div>
        <div id="luu_thanhcong"></div>
        <script>
            function updateMonthText() {
                var monthSelect = document.getElementById("select1").value;
//                alert(monthSelect);
//                var deletePlanButton = document.getElementById("deletePlanLink");
                var selectLink = document.getElementById("selectLink");

                if (monthSelect.value !== "0") {
//                    deletePlanButton.innerText = "Xóa điều chỉnh tháng " + monthSelect;
                    selectLink.innerText = "Chuyển sang tháng " + monthSelect;
                } else {
//                    deletePlanButton.innerText = "Xóa điều chỉnh tháng";
                    selectLink.innerText = "Chuyển sang tháng ";
                }
            }
            function cancelAssign(chotsl, mapgd, D3, D4, monthSelect, nambc, skhoa, D5, type) {
                var url, sdata;
//                alert(chotsl);
                url = "status_KTKSNB_02_C1.action?" + "chotsl" + chotsl + "&madiemgd=" + mapgd + "&smaxa=" + D3 + "&sCanbo=" + D4 + "&sThang=" + monthSelect + "&sNam=" + nambc + "&skhoa=" + skhoa + "&ssThang=" + D5 + "&type=" + type,
                        sdata = jQuery("#frmdata").serialize();
                $("#viewData").html('<img src="img/loading.gif"/>');
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        if (data === "200") {
                            if (type === "2") {
                                alert("Điều chỉnh tháng kiểm tra từ tháng " + D5 + " sang tháng " + monthSelect + " thành công!");
                                onLoadData();
                            } else {
                                alert("Xóa điều chỉnh tháng " + D5 + " thành công!");
                                onLoadData();
                            }
                            onLoadData();
                        } else if (data === "1") {
                            alert("Lỗi: Xã " + D3 + " - Tháng " + monthSelect + " đã có kế hoạch kiểm tra, không thể điều chỉnh tiếp!");
                            onLoadData();
                        } else if (data === "100") {
                            alert("Lỗi: Đơn vị đã gửi dữ liệu không thể thao tác!");
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
        </script>
    </body>
</html>
