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


        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;">             
            <div id="divTitle">

                <s:hidden name="khoa_tdnn" id="khoa"/>
                DANH SÁCH PGD ĐÃ GỬI DỮ LIỆU CẤP CHI NHÁNH

            </div>
            <div style="height:10px"></div>  
            <div style="height:10px"></div>  
            <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px">   
                <tr>
                    <th style="width: 50px">STT</th>
                    <th style="width: 80px">Mã PGD</th>
                    <th style="width: 150px">Tên</th>
                    <th style="width: 100px">Ngày gửi</th>
                    <th style="width: 150px">Trạng thái</th>
                    <th style="width: 100px"></th>
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

                        <td class="D0" <s:if test="D6.equalsIgnoreCase('0')"> style="color: red"</s:if>
                            <s:elseif test="D6.equalsIgnoreCase('2')"> style="color: #003eff"</s:elseif>>
                            <s:property  value="D1"/>
                            <input type="hidden" value="<s:property  value="D6" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" value="<s:property  value="D6"/>"/>
                        </td>
                        <td class="D0" <s:if test="D6.equalsIgnoreCase('0')"> style="color: red"</s:if>
                            <s:elseif test="D6.equalsIgnoreCase('2')"> style="color: #003eff"</s:elseif>>
                            <s:property  value="D2"/>
                            <input class="D0" type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                   id="D2_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D2"/>"/>
                        </td>
                        <td <s:if test="D6.equalsIgnoreCase('0')"> style="color: red"</s:if>
                            <s:elseif test="D6.equalsIgnoreCase('2')"> style="color: #003eff"</s:elseif>>
                            <s:property  value="D3"/>
                            <input type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                   id="D3_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D3"/>"/>
                        </td>

                        <td class="D0" <s:if test="D6.equalsIgnoreCase('0')"> style="color: red"</s:if>
                            <s:elseif test="D6.equalsIgnoreCase('2')"> style="color: #003eff"</s:elseif>>
                            <s:property  value="D5"/>
                            <input class="D0" type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                   id="D5_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D5"/>"/>
                        </td>
                        <td class="D0" <s:if test="D6.equalsIgnoreCase('0')"> style="color: red"</s:if>
                            <s:elseif test="D6.equalsIgnoreCase('2')"> style="color: #003eff"</s:elseif>>
                            <s:property  value="D4"/>
                            <input class="D0" type="hidden" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                   id="D4_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D4"/>"/>
                        </td>
                        <td style="width: 80px; text-align: center;">
                            <s:if test="D6.equalsIgnoreCase('2')">
                                <a href="#" onclick="cancelAssign('<s:property value="D2"/>', '<s:property value="D5"/>', '<s:property value="D6"/>');">Mở dữ liệu</a>
                            </s:if>                                                                
                        </td>
                    </tr>
                </s:iterator>

        </div>      
        <div id="luu_thanhcong"></div>
        <script>

            function cancelAssign(D2, D5, D6) {
//                alert(D2 + ' ' + D5);
                $.ajax({
                    type: "GET",
                    url: "unlock_MSTS_2024_c3?" + "madiemgd=" + D2 + "&ssngaybc=" + D5 + "&skhoa=" + D6,
                    success: function (res) {
                        var status = parseInt(res.status);
                        //alert(status);
                        if (status === 1) {
                            alert('Mở phê duyệt thành công!');
                            onLoadData_tmp();
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
