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
                border: 1px solid orange;
                background-color: transparent;
                margin-top: 4px;
            }
            select{
                border: 0px;
                background-color: transparent;
            }
            .number{
                text-align: right;
                padding-right: 3px;
            }
        </style>
    </head>
    <body>
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
            </tr>
            <tr>
                <th>Số</th>
                <th>Ngày cấp</th>
                <th>Nơi cấp</th>
            </tr>
            <s:iterator value="lstDetail" status="idxRows">
                <s:if test="D1.equalsIgnoreCase('99999999')">
                    <tr>
                        <td style="display: none;"><s:property value='D4'/></td>
                        <td colspan="11"><s:property value='TENKH'/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='MACN'/>" name="macn"/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='MAPGD'/>" name="mapgd"/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='MAKH'/>" name="makh"/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='D5'/>" id="chkLock"/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='NGAYBC'/>" name="ngaysl" id="ngaysl"/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='SOKU'/>" name="soku"/></td>
                        <td>
                            <select name="chotsl" <s:property value='D9'/>>
                                <option value="04" <s:if test="D4.equalsIgnoreCase('04')"> selected</s:if>>Chưa xử lý</option>
                                <option value="01" <s:if test="D4.equalsIgnoreCase('01')"> selected</s:if>>Đúng</option>
                                <option value="02" <s:if test="D4.equalsIgnoreCase('02')"> selected</s:if>>Đang điều chỉnh</option>
                                <option value="03" <s:if test="D4.equalsIgnoreCase('03')"> selected</s:if>>Hoành thành điều chỉnh</option>
                                <option value="05" <s:if test="D4.equalsIgnoreCase('05')"> selected</s:if>>Loại trừ</option>
                                </select>
                            </td>
                            <td style="text-align: center;">
                                <a href="javascript:fnc_show_ngnh(<s:property value='MAKH'/>);">Nguyên nhân</a>
                        </td>
                    </tr>
                    <tr style="color: red; display: none;" id="<s:property value='MAKH'/>" class="giaitrinh">
                        <td colspan="20">
                            <span style="font-weight: bold;">Nguyên nhân:</span><textarea name="nguyennhan" <s:property value='D8'/>><s:property value='D3'/></textarea>
                        </td>
                    </tr>
                </s:if>
                <s:else>
                    <tr>
                        <td style="display: none;"><s:property value='D4'/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='MACN'/>" name="macn"/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='MAPGD'/>" name="mapgd"/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='MAKH'/>" name="makh"/></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='D5'/>" class="checkLock"/></td>
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
                        <td style="display: none;"><input type="hidden" value="<s:property value='NGAYBC'/>" name="ngaysl" /></td>
                        <td style="display: none;"><input type="hidden" value="<s:property value='SOKU'/>" name="soku"/></td>
                        <td>
                            <select name="chotsl" <s:property value='D9'/>>
                                <option value="04" <s:if test="D4.equalsIgnoreCase('04')"> selected</s:if>>Chưa xử lý</option>
                                <option value="01" <s:if test="D4.equalsIgnoreCase('01')"> selected</s:if>>Đúng</option>
                                <option value="02" <s:if test="D4.equalsIgnoreCase('02')"> selected</s:if>>Đang điều chỉnh</option>
                                    <!--Chỉ hiện ra khi trạng thái là 02 + Unlock + Người duyệt (Khi thực hiện trả lại của TW)-->
                                    <option value="03" <s:property value='D10'/> <s:if test="D4.equalsIgnoreCase('03')"> selected</s:if>>Hoành thành điều chỉnh</option>
                                <option value="05" <s:if test="D4.equalsIgnoreCase('05')"> selected</s:if>>Loại trừ</option>
                                </select>
                            </td>
                            <td style="text-align: center;">
                                <a href="javascript:fnc_show_ngnh(<s:property value='MAKH'/>);">Nguyên nhân</a>
                        </td>
                    </tr>
                    <tr style="color: red; display: none;" id="<s:property value='MAKH'/>" class="giaitrinh">
                        <td colspan="20">
                            <span style="font-weight: bold;">Nguyên nhân:</span><textarea name="nguyennhan" <s:property value='D8'/>><s:property value='D3'/></textarea>
                        </td>
                    </tr>
                </s:else>
            </s:iterator>
        </table>
        <script src="js/datemask.js"></script>
        <script>
            $(document).ready(function () {
                $('.number').number(true, 0);
                var chkLock = 1;
                $(".checkLock").each(function () {
                    if ($(this).val() == "UNLOCK") {
                        chkLock = 0;
                    }
                });
                if (chkLock == 1) {
                    $("#cmdluusl").hide();
                    $("#idchotsl").hide();
                } else {
                    $("#cmdluusl").show();
                    $("#idchotsl").show();
                }

                $("#mess").html("&nbsp;<b> Số liệu báo cáo ngày: " + $("#ngaysl").eq(0).val() + "</b>");
            });
            function fnc_show_ngnh(id) {
                $(".giaitrinh").fadeOut();
                $("#" + id).fadeIn();
            }
        </script>
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
                var filter, table, tr, td, i, txtValue, all = 0, loai01 = 0, loai02 = 0, loai03 = 0, loai04 = 0, loai05 = 0;
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
