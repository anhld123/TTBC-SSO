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

                <s:hidden name="seach_MSTS" id="khoa"/>
                TÌNH TRẠNG GỬI DỮ LIỆU CÁC PGD

            </div>
            <div style="height:10px"></div>  
            <div style="height:10px"></div>  
            <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px">   
                <tr>
                    <th style="width: 50px">STT</th>
                    <th style="width: 80px">Mã PGD</th>
                    <th style="width: 150px">Tên PGD</th>
                    <th style="width: 100px">Ngày gửi số liệu</th>
                    <th style="width: 100px">Trạng thái</th>
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
                        <td class="D0" <s:if test="D6.equalsIgnoreCase(0)"> style="color: red"</s:if>><s:property  value="D1"/> </td>
                        <td class="D0" <s:if test="D6.equalsIgnoreCase(0)"> style="color: red"</s:if>><s:property  value="D2"/> </td>
                        <td class="D0" <s:if test="D6.equalsIgnoreCase(0)"> style="color: red"</s:if>><s:property  value="D3"/> </td>
                        <td class="D0" <s:if test="D6.equalsIgnoreCase(0)"> style="color: red"</s:if>><s:property  value="D5"/> </td>
                        <td class="D0" <s:if test="D6.equalsIgnoreCase(0)"> style="color: red"</s:if>><s:property  value="D4"/> </td>
                        </tr>
                </s:iterator>
            </table>
        </div>      
        <div id="luu_thanhcong"></div>
        <!--        <script>
                    $(function () {
                        $('#select-all').click(function (event) {
                            // Iterate each checkbox
                            $('.myCheckBox').each(function () {
                                if (!this.disabled) {
                                    this.checked = $('#select-all').prop('checked');
                                    this.value = this.checked ? '1' : '0';
                                }
                            });
                        });
                    });
                     function cancelAssign(D2, D5,D6) {
        //                alert(D2 + ' ' + D5);
                        $.ajax({
                            type: "GET",
                            url: "cancelAssign_unlock_gdx?" + "madiemgd=" + D2 + "&ssngaybc=" + D5 + "&skhoa=" + D6,
                            success: function (res) {
                                var status = parseInt(res.status);
                                //alert(status);
                                if (status === 1) {
                                    alert('Mở phê duyệt thành công!');
                                    onLoadData();
                                } else {
                                    alert('Mở phê duyệt lỗi: ' + res.message );
                                }
                            },
                            error: function (res) {
                                alert("Mở phê duyệt lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                            }
                        });
                    }
                </script>-->
    </body>
</html>
