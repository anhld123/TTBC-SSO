<%-- 
    Document   : epsDetails
    Created on : Jul 12, 2021, 2:10:30 PM
    Author     : ITCVBSP56
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            textarea{
                height: 100%;
                width: 100%;
                border: 1px solid orange;
                background-color: transparent;
                min-width: 200px;
                min-height: 60px;
            }
            select{
                background-color: transparent;
            }
            .number{
                text-align: right;
                padding-right: 3px;
            }
            .daguitw{
                text-align: center;
                font-style: initial;
                color: green;
            }
        </style>
    </head>
    <body>
        <div></div>
        <table id="tblData">
            <tr>
                <th rowspan="2">Đơn vị/Tên khách hàng</th>
                <th rowspan="2">Ngày tháng năm sinh</th>
                <th rowspan="2">Giới tính</th>
                <th colspan="3">CMND, Hộ chiếu, Căn cước công dân</th>
                <th rowspan="2">Đăng ký thường trú (thôn, xã)</th>
                <th rowspan="2">Phòng giao dịch nơi nhận ký quỹ</th>
                <th rowspan="2">Ngày ký quỹ</th>
                <th rowspan="2">Số tiền vay NHCSXH để ký quỹ (nếu có)</th>
                <th rowspan="2">Số tiền ký quỹ</th>
                <th rowspan="2">Trạng thái</th>
                <th rowspan="2">Nguyên nhân</th>
                <th rowspan="2" class="xacnhantw">Xác nhận</th>
                <th rowspan="2" class="mokhoa">Mở khoá</th>
            </tr>
            <tr>
                <th>Số</th>
                <th>Ngày cấp</th>
                <th>Nơi cấp</th>
            </tr>
            <s:iterator value="lstDetail" status="idxRows">
                <s:if test="D1.equalsIgnoreCase('PHANHOI')">
                    <tr id="phanhoi">
                        <td colspan="14">
                            <span style="font-weight:bold;">Phản hồi chung của đơn vị: <s:property value='D6'/></span>
                            <textarea id="phanhoichung" name="phanhoichung" style="margin-top: 5px;" readonly="readonly"><s:property value='D3'/></textarea>
                        </td>
                    </tr>
                </s:if>
                <s:elseif test="D1.equalsIgnoreCase('99999999')">
                    <tr>
                        <td style="display: none;"><s:property value='D4'/></td>
                        <td colspan="10"><s:property value='TENKH'/></td>
                        <td>
                        <td>
                            <select name="chotsl" disabled="disabled" style="border: 0px;">
                                <option value="01" <s:if test="D4.equalsIgnoreCase('01')"> selected</s:if>>Đúng</option>
                                <option value="02" <s:if test="D4.equalsIgnoreCase('02')"> selected</s:if>>Đang điều chỉnh</option>
                                <option value="03" <s:if test="D4.equalsIgnoreCase('03')"> selected</s:if>>Hoành thành điều chỉnh</option>
                                <option value="04" <s:if test="D4.equalsIgnoreCase('04')"> selected</s:if>>Chưa xử lý</option>
                                <option value="05" <s:if test="D4.equalsIgnoreCase('05')"> selected</s:if>>Loại trừ</option>
                                </select>
                            </td>
                            <td style="text-align: center;">
                                <a href="javascript:fnc_show_ngnh('<s:property value='MAKH'/>');">Xem nguyên nhân</a>
                        </td>
                        <td style="text-align: center;" class="xacnhantw">
                            <s:property value='D10' escape="false"/>
                        </td>
                        <td style="text-align: center;" class="mokhoa">
                            <s:if test="D5.equalsIgnoreCase('LOCK')">
                                <input type="button" id="<s:property  value="%{#idxRows.index}" />" value="Mở khoá" style="background-color: transparent;border: 0px; color: blue;" onclick="<script>alert('đã mở khoá')</script>">
                            </s:if>
                        </td>
                    </tr>
                    <tr style="color: red; display: none;" id="<s:property value='MAKH'/>" class="giaitrinh">
                        <td colspan="20">
                            <span style="font-weight: bold;">Nguyên nhân: </span><s:property value='D3'/>
                        </td>
                    </tr>
                </s:elseif>
                <s:else>
                    <tr>
                        <td style="display: none;"><s:property value='D4'/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='MACN'/>" name="macn"/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='MAPGD'/>" name="mapgd"/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='MAKH'/>" name="makh"/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='D5'/>"/></td>
                        <td><s:property value='TENKH'/></td>
                        <td><s:property value='NGAYSINH'/></td>
                        <td><s:property value='GIOITINH'/></td>
                        <td><s:property value='CMT_SO'/></td>
                        <td><s:property value='CMT_NGAYCAP'/></td>
                        <td><s:property value='CMT_NOICAP'/></td>
                        <td><s:property value='DIACHI'/></td>
                        <td><s:property value='D6'/></td>
                        <td><s:property value='NGAYKYQUY'/></td>
                        <td class="number"><s:property value='D7'/></td>
                        <td class="number"><s:property value='SOTIENKYQUY'/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='NGAYBC'/>" name="ngaysl"/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='SOKU'/>" name="soku"/></td>
                        <td>
                            <select name="chotsl" disabled="disabled" style="border: 0px;">
                                <option value="01" <s:if test="D4.equalsIgnoreCase('01')"> selected</s:if>>Đúng</option>
                                <option value="02" <s:if test="D4.equalsIgnoreCase('02')"> selected</s:if>>Đang điều chỉnh</option>
                                <option value="03" <s:if test="D4.equalsIgnoreCase('03')"> selected</s:if>>Hoành thành điều chỉnh</option>
                                <option value="04" <s:if test="D4.equalsIgnoreCase('04')"> selected</s:if>>Chưa xử lý</option>
                                <option value="05" <s:if test="D4.equalsIgnoreCase('05')"> selected</s:if>>Loại trừ</option>
                                </select>
                            </td>
                            <td style="text-align: center;">
                                <a href="javascript:fnc_show_ngnh('<s:property value='MAKH'/>');">Xem nguyên nhân</a>
                        </td>
                        <td style="text-align: center;" class="xacnhantw">
                            <s:property value='D10' escape="false"/>
                        </td>
                        <td style="text-align: center;" class="mokhoa">
                            <s:if test="D5.equalsIgnoreCase('LOCK')">
                                <input type="button" id="<s:property  value="%{#idxRows.index}" />" class="unlock" value="Mở khoá" style="background-color: transparent;border: 0px; color: blue;">
                                <s:if test="D4.equalsIgnoreCase('02') || D4.equalsIgnoreCase('03')">
                                    <input type="button" id="tsl<s:property  value="%{#idxRows.index}" />" class="taolaisolieu" value="Tạo số liêu" title="Tạo số liệu từ Intellect" style="background-color: transparent;border: 0px; color: blue;">
                                </s:if>
                            </s:if>
                        </td>
                    </tr>
                    <tr style="color: red; display: none;" id="<s:property value='MAKH'/>" class="giaitrinh">
                        <td colspan="20">
                            <span style="font-weight: bold;">Nguyên nhân: </span><s:property value='D3'/>
                        </td>
                    </tr>
                </s:else>
            </s:iterator>
        </table>
        <script>
            $(document).ready(function () {
                $('.number').number(true, 0);//
            <% if (session.getAttribute("reportGrade").equals("2")) { %>
                $("#idcapbc").hide();
                $(".mokhoa").hide();
            <%}%>
            <% if (session.getAttribute("reportGrade").equals("3")) { %>
                $(".xacnhantw").hide();
            <%}%>
                $('.unlock').each(function () {
                    $(this).click(function () {
                        var surl, sdata, idView, idForm, method;
                        surl = "openlock?status=" + $(this).attr('id');
                        idView = "#ShowData";
                        idForm = "#frmMain";
                        method = "POST";
                        sdata = jQuery(idForm).serialize();
                        $.ajax({
                            url: surl,
                            data: sdata,
                            type: method,
                            async: true,
                            success: function (result) {
                                alert('Hoàn trả đơn vị thành công');
                                $("#btnXem").trigger('click');
                            },
                            error: function () {
                                alert('Lỗi: Hoàn trả không thành công.');
                            }
                        });
                    });
                });
                $('.taolaisolieu').each(function () {
                    $(this).click(function () {
                        var surl, sdata, idView, idForm, method;
                        surl = "updateintellect?status=" + $(this).attr('id');
                        idView = "#ShowData";
                        idForm = "#frmMain";
                        method = "POST";
                        sdata = jQuery(idForm).serialize();
                        $.ajax({
                            url: surl,
                            data: sdata,
                            type: method,
                            async: true,
                            success: function (result) {
                                if (result > 0) {
                                    alert('Tạo dữ liệu thành công từ Intellect (' + result + ')');
                                } else {
                                    alert('Lỗi: Tạo dữ liệu từ Intellect (' + result + ')');
                                }
                            },
                            error: function () {
                                alert('Lỗi: Tạo dữ liệu từ Intellect không thành công.');
                            }
                        });
                    });
                });
            });
            function fnc_show_ngnh(id) {
                $(".giaitrinh").fadeOut();
                $("#" + id).fadeIn();
            }
        </script>
        <script src="js/datemask.js"></script>
        <script>
            function Filter() {
                var filter, table, tr, td, i, txtValue;
                filter = $("#idloc").val();
                table = document.getElementById("tblData");
                tr = table.getElementsByTagName("tr");
                for (i = 0; i < tr.length; i++) {
                    td = tr[i].getElementsByTagName("td")[0];
                    if (td) {
                        txtValue = td.textContent || td.innerText;
                        if (txtValue.toUpperCase().indexOf(filter) > -1) {
                            tr[i].style.display = "";
                        } else {
                            tr[i].style.display = "none";
                        }
                    }
                }
                $('.giaitrinh').css("display", "none");
            }
            function CoutFilter() {
                $('#idloc').children().remove().end();
                var table, tr, td, i, txtValue, all = 0, loai01 = 0, loai02 = 0, loai03 = 0, loai04 = 0, loai05 = 0;
                table = document.getElementById("tblData");
                tr = table.getElementsByTagName("tr");
                const numbers = ['01', '02', '03', '04', '05'];
                $.each(numbers, function (index, value) {
                    for (i = 0; i < tr.length; i++) {
                        td = tr[i].getElementsByTagName("td")[0];
                        if (td) {
                            txtValue = td.textContent || td.innerText;
                            if (txtValue.indexOf(value) > -1) {
                                switch (value) {
                                    case "01":
                                        loai01++;
                                        break;
                                    case "02":
                                        loai02++;
                                        break;
                                    case "03":
                                        loai03++;
                                        break;
                                    case "04":
                                        loai04++;
                                        break;
                                    case "05":
                                        loai05++;
                                        break;
                                }

                            }
                        }
                    }
                });
                all = loai01 + loai02 + loai03 + loai04 + loai05;
                $('#idloc').append('<option value="">Tất cả (' + all + ')</option>');
                $('#idloc').append('<option value="01">Đúng (' + loai01 + ')</option>');
                $('#idloc').append('<option value="02">Đang điều chỉnh (' + loai02 + ')</option>');
                $('#idloc').append('<option value="03">Hoành thành điều chỉnh (' + loai03 + ')</option>');
                $('#idloc').append('<option value="04">Chưa xử lý (' + loai04 + ')</option>');
                $('#idloc').append('<option value="05">Loại trừ (' + loai05 + ')</option>');
                $('.giaitrinh').css("display", "none");
            }
            CoutFilter();
        </script>
    </body>
</html>
