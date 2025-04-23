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
    #subTable_tmp {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: auto;
    }
    .container {
        display: flex;
        justify-content: space-between;
        flex-wrap: wrap;
    }

    .box {
        flex: 1;
        margin: 10px;
        min-width: 200px; /* Đảm bảo các hộp không nhỏ hơn kích thước này */
        background-color: #f0f0f0; /* Màu nền để dễ nhìn */
        padding: 20px;
        box-sizing: border-box;
    }

    @media (max-width: 768px) {
        .box {
            flex: 1 1 100%;
            margin: 5px 0;
        }
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
        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;">              
            <div id="divTitle">DANH SÁCH CN/ PGD ĐÃ GỬI DỮ LIỆU
                <s:hidden name="khoa_ktgs" id="khoa"/>
            </div>  
            <div style="height:2px"></div>  
            <div class="container">
                <table border="1" class="editDelete so1" id="subTable" style="padding-top: 10px">   
                    <tr>
                        <th style="width: 50px">Mã CN</th>
                        <th style="width: 100px">Tên chi nhánh</th>
                        <th style="width: 70px">Số lượng PGD</th>
                        <th style="width: 70px">Số lượng PGD đã gửi</th>
                        <th style="width: 70px">Số lượng PGD chưa gửi</th>
                        <th style="width: 80px">Trạng thái</th>

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
                            <s:if test="D6.equalsIgnoreCase('1')">
                                <td style="background: #ffffff;">
                                    <input class="D0" type="text" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                           id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D1"/>"
                                           <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>
                                    <td style="background: #ffffff;">
                                        <input  type="text" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                            id="D2_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D2"/>"
                                            <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>
                                    <td style="background: #ffffff;">
                                        <input type="text" class="D0" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                           id="D3_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D3"/>"
                                           <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>

                                    <td style="background: #ffffff;">
                                        <input type="text" class="D0" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                           id="D4_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D4"/>"
                                           <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>

                                    <td style="background: #ffffff;">
                                        <input class="D0" type="text" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                           id="D5_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D5"/>"
                                           <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>
                                <s:if test="D4.equalsIgnoreCase('0')">
                                    <td class = "D0">
                                        <a style="color: red">Chưa gửi DL</a></td>
                                    </s:if>
                                    <s:else>
                                    <td style="width: 80px; text-align: center;">
                                        <a href="#" onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D7"/>', '<s:property value="D8"/>','<s:property value="D9"/>'">Mở DL</a>                          
                                    </td>
                                </s:else>
                            </s:if>
                        </tr>
                    </s:iterator>
                </table>
                <table border="1" class="editDelete so2" id="subTable"  style="padding-top: 10px">   
                    <tr>
                        <th style="width: 50px">Mã CN</th>
                        <th style="width: 100px">Tên chi nhánh</th>
                        <th style="width: 70px">Số lượng PGD</th>
                        <th style="width: 70px">Số lượng PGD đã gửi</th>
                        <th style="width: 70px">Số lượng PGD chưa gửi</th>
                        <th style="width: 80px">Trạng thái</th>
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
                            <s:if test="D6.equalsIgnoreCase('2')">
                                <td style="background: #ffffff;">
                                    <input class="D0" type="text" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                           id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D1"/>"
                                           <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>
                                    <td style="background: #ffffff;">
                                        <input  type="text" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                            id="D2_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D2"/>"
                                            <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>
                                    <td style="background: #ffffff;">
                                        <input type="text" class="D0" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                           id="D3_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D3"/>"
                                           <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>

                                    <td style="background: #ffffff;">
                                        <input type="text" class="D0" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                           id="D4_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D4"/>"
                                           <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>

                                    <td style="background: #ffffff;">
                                        <input class="D0" type="text" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                           id="D5_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D5"/>"
                                           <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red" </s:if>/>
                                    </td>
                                <s:if test="D4.equalsIgnoreCase('0')">
                                    <td class = "D0">
                                        <a style="color: red">Chưa gửi DL</a></td>
                                    </s:if>
                                    <s:else>
                                    <td style="width: 80px; text-align: center;">
                                        <a href="#" onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D7"/>', '<s:property value="D8"/>','<s:property value="D9"/>'">Mở DL</a>                          
                                    </td>
                                </s:else>
                            </s:if>
                        </tr>
                    </s:iterator>
                </table>
                <table border="1" class="editDelete so3" id="subTable"  style="padding-top: 10px">   
                    <tr>
                        <th style="width: 50px">Mã CN</th>
                        <th style="width: 100px">Tên chi nhánh</th>
                        <th style="width: 70px">Số lượng PGD</th>
                        <th style="width: 70px">Số lượng PGD đã gửi</th>
                        <th style="width: 70px">Số lượng PGD chưa gửi</th>
                        <th style="width: 80px">Trạng thái</th>    
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
                            <s:if test="D6.equalsIgnoreCase('3')">
                                <td style="background: #ffffff;">
                                    <input class="D0" type="text" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                           id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D1"/>"
                                           <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>
                                    <td style="background: #ffffff;">
                                        <input  type="text" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                            id="D2_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D2"/>"
                                            <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>
                                    <td style="background: #ffffff;">
                                        <input type="text" class="D0" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                           id="D3_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D3"/>"
                                           <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>

                                    <td style="background: #ffffff;">
                                        <input type="text" class="D0" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                           id="D4_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D4"/>"
                                           <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>/>
                                    </td>

                                    <td style="background: #ffffff;">
                                        <input class="D0" type="text" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                           id="D5_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D5"/>"
                                           <s:if test="!D5.equalsIgnoreCase('0')"> style="color: red"</s:if>>
                                    </td> 
                                <s:if test="D4.equalsIgnoreCase('0')">
                                    <td class = "D0">
                                        <a style="color: red">Chưa gửi DL</a></td>
                                    </s:if>
                                    <s:else>
                                    <td style="width: 80px; text-align: center;">
                                        <a href="#" onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D7"/>', '<s:property value="D8"/>','<s:property value="D9"/>'">Mở DL</a>                          
                                    </td>
                                </s:else>
                            </s:if>
                        </tr>
                    </s:iterator>
                </table>
            </div>
        </div>      
        <div id="luu_thanhcong"></div>
        <script>
            function cancelAssign(D1, D7, D8, D9) {
//                alert(D2 + ' ' + D5);
            $.ajax({
            type: "GET",
                    url: "Unlock_ktnb_2025?" + "macn=" + D1 + "&quybc=" + D7 + "&nambc=" + D8+ "&khoa=" + D9,
                    success: function (res) {
                    var status = parseInt(res.status);
                    //alert(status);
                    if (status === 1) {
                    alert('Mở phê duyệt thành công!');
                    onLoadData();
                    } else {
                    alert('Mở phê duyệt lỗi: ' + res.message);
                    }
                    },
                    error: function (res) {
                    alert("Mở phê duyệt lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                    }
            });
            }
        </script>
    </body>
</html>
