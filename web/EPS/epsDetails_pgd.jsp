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
                min-height: 50px;
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
                <th rowspan="2">STT</th>
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
                <tr>
                    <td><s:property value='KHOA'/></td>
                    <td style="display: none;"><input type="hidden" value="<s:property value='MACN'/>" name="macn"/></td>
                    <td style="display: none;"><input type="hidden" value="<s:property value='MAPGD'/>" name="mapgd"/></td>
                    <td style="display: none;"><input type="hidden" value="<s:property value='MAKH'/>" name="makh"/></td>
                    <td style="display: none;"><input type="hidden" value="<s:property value='KHOASL'/>" id="chkLock"/></td>
                    <td><s:property value='TENKH'/></td>
                    <td><s:property value='NGAYSINH'/></td>
                    <td><s:property value='GIOITINH'/></td>
                    <td><s:property value='CMT_SO'/></td>
                    <td><s:property value='CMT_NOICAP'/></td>
                    <td><s:property value='CMT_NGAYCAP'/></td>
                    <td><s:property value='DIACHI'/></td>
                    <td><s:property value='D4'/></td>
                    <td><s:property value='NGAYKYQUY'/></td>
                    <td class="number"><s:property value='D3'/></td>
                    <td class="number"><s:property value='SOTIENKYQUY'/></td>
                    <td style="display: none;"><input type="hidden" value="<s:property value='NGAYBC'/>" name="ngaysl" id="ngaysl"/></td>
                    <td style="display: none;"><input type="hidden" value="<s:property value='SOKU'/>" name="soku"/></td>
                    <td>
                        <select name="chotsl" <s:property value='D2'/>>
                            <option value="00" <s:if test="CHOTSL.equalsIgnoreCase('00')"> selected</s:if>>Không xác định</option>
                            <option value="01" <s:if test="CHOTSL.equalsIgnoreCase('01')"> selected</s:if>>Đúng và đủ</option>
                            <option value="02" <s:if test="CHOTSL.equalsIgnoreCase('02')"> selected</s:if>>Sai</option>
                            </select>
                        </td>
                        <td style="text-align: center;">
                            <a href="javascript:fnc_show_ngnh(<s:property value='MAKH'/>);">Nguyên nhân</a>
                    </td>
                </tr>
                <tr style="color: red;">
                    <td colspan="20" style="display:none;" id="<s:property value='MAKH'/>" class="giaitrinh">
                        <span style="font-weight: bold;">Nguyên nhân:</span><textarea name="nguyennhan" <s:property value='D1'/>><s:property value='NGUYENNHAN'/></textarea>
                    </td>
                </tr>
            </s:iterator>
        </table>
        <script>
            $(document).ready(function () {
                $('.number').number(true, 0);
                if ($("#chkLock").val() == "LOCK") {
                    $("#cmdluusl").hide();
                    $("#idchotsl").hide();
                }

                $('select[name=chotsl]').change(function () {
                    var idx, valu;
                    idx = $('select[name=chotsl]').index(this);
                    valu = $(this).val();
                    if (['00','01'].includes(valu)) {
                        $('textarea[name=nguyennhan]').eq(idx).val('');
                    }
                });

                $("#mess").html("&nbsp;<b> Số liệu báo cáo ngày: " + $("#ngaysl").eq(0).val() + "</b>");
            });
            function fnc_show_ngnh(id) {
                $(".giaitrinh").fadeOut();
                $("#" + id).fadeIn();
            }
        </script>
    </body>
</html>
