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
        <div style="overflow:scroll; width: 90%;">     
            <s:if test="txtGetData.equalsIgnoreCase('1')">
                <div id="divTitle">
                    ĐĂNG KÝ KẾ HOẠCH TỈNH<br>
                    <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Chi nhánh đã chốt số liệu)</a></s:if>
                    <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
                    <input type="hidden" value="<s:property value="chotsl_tw"/>" name="chotsl_tw" id="chotsl_tw"/> 
                </div>
                <div style="height:10px"></div>  
                <div style="color: red; background: yellow; text-align: left; font-weight: bold; width: 98%; font-size: 14px">
                    <s:property value="title1" /></div>
                <table border="1" class="editDelete table11" id="subTable" align="center" style="padding-top: 10px">   
                    <tr> 
                        <th class="STT1" >STT</th>                           
                        <th class="STT4" >Nội dung</th>  
                        <th class="STT2" >Đơn vị</th>  
                        <th class="STT6" >Mô tả</th>
                    </tr>


                    <tr>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                        <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                        <tr id="tablefix"> 
                        <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                        <input type="hidden" value="<s:property value="TT_HIENTHI" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                        <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
                        <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                        <input type="hidden" value="<s:property  value="CO_TONGHOP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/>
                        <input type="hidden" value="<s:property  value="NGUOI_NHAP" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGUOI_NHAP"/>
                        <input type="hidden" value="<s:property  value="NAMBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NAMBC"/>
                        <input type="hidden" value="<s:property  value="MAPGD" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                        <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>
                        <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>
                        <input type="hidden" value="<s:property  value="D3" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                        <input type="hidden" value="<s:property  value="D4" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"/>
                        <input type="hidden" value="<s:property  value="D5" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"/>
                        <input type="hidden" value="<s:property  value="D6" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6"/>
                        <input type="hidden" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7"/>
                        <input type="hidden" value="<s:property  value="D8" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"/>
                        <input type="hidden" value="<s:property  value="D9" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/>
                        <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                        <input type="hidden" value="<s:property  value="KIEUIN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KIEUIN"/>
                        <input type="hidden" value="<s:property  value="NHAPTAY" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/>

                        <td class="D0" <s:if test="(THUTU.toString().equalsIgnoreCase('1')
                              || THUTU.toString().equalsIgnoreCase('2') || THUTU.toString().equalsIgnoreCase('10')
                              || THUTU.toString().equalsIgnoreCase('17'))"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="TT_HIENTHI" /></td>
                        <td <s:if test="(THUTU.toString().equalsIgnoreCase('1')
                              || THUTU.toString().equalsIgnoreCase('2') || THUTU.toString().equalsIgnoreCase('10')
                              || THUTU.toString().equalsIgnoreCase('17'))"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="TEN" /></td>
                        <td class="D0" <s:if test="(THUTU.toString().equalsIgnoreCase('1')
                              || THUTU.toString().equalsIgnoreCase('2') || THUTU.toString().equalsIgnoreCase('10')
                              || THUTU.toString().equalsIgnoreCase('17'))"> style="font-weight: bold;background:  #E5E5E5" </s:if>><s:property value="D1" /></td>
                            <td style="background:  #E5E5E5">
                                <input type="text" value="<s:property  value="D2" />"
                                   id="D2_<s:property  value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="number"
                                   <s:if test="(THUTU.toString().equalsIgnoreCase('1')
                                         || THUTU.toString().equalsIgnoreCase('2') || THUTU.toString().equalsIgnoreCase('10')
                                         || THUTU.toString().equalsIgnoreCase('17'))"> style="font-weight: bold" readonly</s:if>/>
                            </td>
                            </tr>
                    </s:iterator>
                </table><div style="height:20px"></div> 
            </s:if>
            <s:elseif test="!txtGetData.equalsIgnoreCase('1')">
                <div id="divTitle">
                    DANH SÁCH PGD CHỐT/GỬI DỮ LIỆU
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
                            <s:if test="D4.equalsIgnoreCase('2')">
                                <td class="D0" style="color: #3dc21b"><s:property value="%{#rowstatus.index + 1}" />
                                    <!--                                <td class="D0" style="color: #3dc21b">
                                                                        <a style="text-decoration: underline" 
                                                                           href="javascript:funcTableFile('<s:property value="D1"/>', '<s:property value="D7"/>', '1')">
                                    <s:property value="D1" /></a></td>-->
                                <td class="D0" style="color: #3dc21b"><s:property value="D1" /></td>        
                                <td style="color: #3dc21b"><s:property value="D2" /></td>
                                <td class="D0" style="color: #3dc21b"><s:property value="D8" /></td>
                                <td style="color: #3dc21b" class="D0"><s:property value="D3" /></td> 
                                <td class="D0" style="color: #3dc21b">Đã gửi dữ liệu</td>
                                <!--<td class="D0" style="color: #3dc21b">Đã gửi dữ liệu lên TW</td>-->
                            </s:if>
                            <s:elseif test="D4.equalsIgnoreCase('0')">
                                <td class="D0" style="color: #ff0000"><s:property value="%{#rowstatus.index + 1}" />
                                <td class="D0" style="color: #ff0000"><s:property value="D1" /></td>
                                <td style="color: #ff0000"><s:property value="D2" /></td>
                                <td class="D0" style="color: #ff0000"><s:property value="D8" /></td>
                                <td style="color: #ff0000" class="D0"><s:property value="D3" /></td> 
                                <td class="D0" style="color: #ff0000">Chưa gửi dữ liệu</td>
                                <!--<td class="D0" style="color: #ff0000">Chưa gửi dữ liệu</td>-->
                            </s:elseif>
                            <s:else> 
                                <td class="D0" style="color: #0000FF"><s:property value="%{#rowstatus.index + 1}" />
                                    <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                           id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D1"/>"/>
                                    <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                           id="D7_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D7"/>"/>
                                </td>
                                <!--                                <td class="D0" style="color: #0000FF">
                                                                    <a style="text-decoration: underline" 
                                                                       href="javascript:funcTableFile('<s:property value="D1"/>', '<s:property value="D7"/>', '1')">
                                <s:property value="D1" /></a></td>-->
                                <td class="D0" style="color: #0000FF"><s:property value="D1" /></td>
                                <td style="color: #0000FF"><s:property value="D2" /></td>
                                <td class="D0" style="color: #0000FF"><s:property value="D8" /></td>
                                <td class="D0" style="color: #0000FF"><s:property value="D3" /></td> 
                                <td class="D0"> <a style="text-decoration: underline" href="#" onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D7"/>', '1');">Mở dữ liệu</a></td>
                                <!--<td class="D0"> <a style="text-decoration: underline" href="#" onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D7"/>', '2');">Gửi dữ liệu lên TW</a></td>-->
                            </s:else>
                        </tr>
                    </s:iterator>
                </table>
            </s:elseif>
        </div>      
        <div id="luu_thanhcong"></div>
        <script>

            function cancelAssign(D1, D7, type) {
                var url, sdata;
                url = "status_KTKSNB_2024_C2.action?" + "madiemgd=" + D1 + "&ngaybc=" + D7 + "&type=" + type,
                        sdata = jQuery("#frmdata").serialize();
                $("#viewData").html('<img src="img/loading.gif"/>');
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        if (data === "200") {
                            if (type === "1") {
                                alert("Mở phê duyệt thành công!");
                                $('#message_suc_err').html("<h style='color: green; font-size: 13px ; font-weight: bold'>Bạn đã mở dữ liệu thành công!</h>");
                            } else {
                                alert("Gửi dữ liệu thành công!");
                                $('#message_suc_err').html("<h style='color: green; font-size: 13px ; font-weight: bold'>Bạn đã gửi dữ liệu thành công!</h>");
                            }
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
                var url = "/IMS_REPORTS/popupTablePos.action?" + urlParam;
                popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }
        </script>
    </body>
</html>
