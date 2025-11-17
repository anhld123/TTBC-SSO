<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/css2025.css" />
<!DOCTYPE html>
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
                $('.style_h').css({"width:": "99%", "background-color": "rgba(255, 255, 255, 0.3)", "border": "1px solid #ccc"});
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

            function funcTableFile(D1, D7, type) {
                var w = 1500, h = 700;
                var left = (screen.width / 2) - (w / 2);
                var top = (screen.height / 2) - (h / 2);
                var urlParam = "madiemgd=" + D1 + "&ngaybc=" + D7 + "&type=" + type;
                var url = "/IMS_REPORTS/popuptienguitovien.action?" + urlParam;
                popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }
        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 98%;">     
            <div id="divTitle">
                DANH SÁCH GỬI DỮ LIỆU 
                <s:if test="Grade.equalsIgnoreCase('3')">
                    &nbsp;|&nbsp;
                    <button type="button"
                            onclick="idSend($('#lstCN').val(), $('#ngay_bc_DATE').val(), '<s:property value="chotsl"/>', '1')">
                        <s:if test="!chotsl.equalsIgnoreCase('2')">Khóa nhập dữ liệu CN</s:if>
                        <s:else>Mở nhập dữ liệu</s:else>
                        </button>

                </s:if>
                <s:if test="chotsl.equalsIgnoreCase('2')&& Grade.equalsIgnoreCase('2')"><a class="color_11">(TW đã khóa nhập dữ liệu)</a></s:if>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
            </div>
            <div style="height:20px"></div>  
            <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px;width: 80%">   
                <tr>
                    <th style="width: 30px">STT</th>
                    <th style="width: 60px">Mã PGD</th>
                    <th style="STT3">Tên PGD</th>
                    <th>Người chốt</th>
                    <th>Ngày chốt</th>
                    <th style="width: 150px">Chốt dữ liệu PGD</th>
                    <th style="width: 150px">Chốt dữ liệu TW</th>
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
                    <tr>
                        <td class="D0" ><s:property value="%{#rowstatus.index + 1}" />
                        <td class="D0" ><s:property value="D1" /></td>
                        <td><s:property value="D2" /></td>
                        <td class="D0"><s:property value="D14" /></td>
                        <td class="D0"><s:property value="D15" /></td>
                        <!--                        <td class="D0">
                        <s:if test="D8.equalsIgnoreCase('0')">
                            <a style="text-decoration: underline" href="#" onclick="idSend('<s:property value="D1"/>', $('#ngay_bc_DATE').val(), '<s:property value="D8"/>', '3');">Chốt dữ liệu</a>
                        </s:if>
                        <s:else>
                            <a style="color: #009900">Đã chốt dữ liệu TW</a>
                        </s:else>
                    </td>-->
                        <td class="D0">
                            <s:if test="D8.equalsIgnoreCase('1')">
                                <a style="text-decoration: underline" href="#" onclick="idSend('<s:property value="D1"/>', $('#ngay_bc_DATE').val(), '<s:property value="D8"/>', '0');">Mở chốt dữ liệu PGD</a>
                            </s:if>
                            <s:elseif test="D8.equalsIgnoreCase('0')">
                                <a style="color: red">Chưa chốt dữ liệu</a>
                            </s:elseif>
                            <s:else>
                                <a style="color: #009900">Đã chốt dữ liệu TW</a>
                            </s:else>
                        </td>
                        <td class="D0">
                            <s:if test="D8.equalsIgnoreCase('1')">
                                <a style="text-decoration: underline" href="#" onclick="idSend('<s:property value="D1"/>', $('#ngay_bc_DATE').val(), '<s:property value="D8"/>', '2');">Chốt dữ liệu TW</a>
                            </s:if>
                            <s:elseif test="D8.equalsIgnoreCase('0')">
                                <a style="color: red">Chưa chốt dữ liệu</a>
                            </s:elseif>
                            <s:else>
                                <a style="color: #009900">Đã chốt dữ liệu TW</a>
                            </s:else>
                        </td>

                    </tr>
                </s:iterator>
            </table>
        </div>      
        <div id="luu_thanhcong"></div>

    </body>
    <script>
        function idSend(D1, D2, D3, D4) {
            var table = document.getElementById("subTable");
            var rows = table.querySelectorAll("td a");

            rows.forEach(function (row) {
                row.style.pointerEvents = "none";
                row.style.color = "gray";
            });

            var url, sdata;
            url = "lock_htls_nguondp.action?" + "smapgd=" + D1 + "&sngaybc=" + D2 + "&schotsl=" + D3 + "&sstype=" + D4;
            sdata = jQuery("#frmdata").serialize();
            $("#loadingImageDiv_data").show();
            $("#viewData").html('<img src="img/loading.gif"/>');

            $.ajax({
                type: "POST",
                url: url,
                data: sdata,
                success: function (data) {
                    if (data === "200") {
                        if (D4 === "0")
                        {
                            alert("Mở chốt dữ liệu PGD thành công!");
                        } else {
                            alert("Chốt dữ liệu gửi TW thành công!");
                        }
                        onLoadData();
                    } else {
                        alert("Lỗi: Mở/Chốt dữ liệu.");
                        onLoadData();
                    }
                },
                error: function (request) {
                    alert("Lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                    onLoadData();
                }
            });
        }
    </script>
</html>
