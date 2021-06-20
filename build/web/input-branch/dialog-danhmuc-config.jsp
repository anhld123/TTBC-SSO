<%-- 
    Document   : dialog-danhmuc-config
    Created on : May 30, 2020, 2:45:10 PM
    Author     : BAOANH
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cấu hình tham số danh mục cho cột dữ liệu</title>
        <%--<sj:head/>--%>
        <!--<link  rel="stylesheet" type="text/css" href="input-branch/css/inputbranch.css"/>-->
        <script type="text/javascript" src="js/sweetalert.min.js"></script>
        <script>
            function puthidenValue()
            {
                try {
                    var BANGSL = $('#BANGSL_ID_0').val();
                    if (BANGSL == null || BANGSL == '')
                    {
                        swal('Cảnh báo', 'Bạn phải nhập bảng dữ liệu !', 'error');
                        return;
                    }
                    var COTHIENTHI = $('#COTHIENTHI_ID_0').val();
                    if (COTHIENTHI == null || COTHIENTHI == '')
                    {
                        swal('Cảnh báo', 'Bạn phải nhập cột hiển thị !', 'error');
                        return;
                    }
                    var COTTSO = $('#COTTSO_ID_0').val();
                    if (COTTSO == null || COTTSO == '')
                    {
                        swal('Cảnh báo', 'Bạn phải nhập cột tham số !', 'error');
                        return;
                    }
                    var DKLOC = $('#DKLOC_ID_0').val();
                    var DKSAPXEP = $('#DKSAPXEP_ID_0').val();

                    var index = $('#id_index').val();

                    var queryString = BANGSL + '#' + COTHIENTHI + '#' + COTTSO + '#' + DKLOC + '#' + DKSAPXEP;
                    var khoa = $('#id_khoa').val();

                    var sdata = {
                        "khoa": khoa,
                        "BANGSL": BANGSL,
                        "COTHIENTHI": COTHIENTHI,
                        "COTTSO": COTTSO,
                        "DKLOC": DKLOC,
                        "DKSAPXEP": DKSAPXEP,
                        "queryString": queryString
                    };
                    var data1 = JSON.stringify(sdata);
                    //console.log(data1);
                    //Kiểm tra thông tin điền để lấy dữ liệu cho danh mục có đúng không
                    $.ajax({
                        url: "checkDulieuDanhmucCotDulieu.action?khoa=" + khoa + "&queryString=" + encodeURIComponent(queryString),
                        data: data1,
                        dataType: 'json',
                        contentType: 'application/json',
                        type: 'POST',
                        async: true,
                        success: function (data) {
                            // alert(JSON.stringify(data));
                            var mass = data.msg;
                            if (mass == '' || mass == null)
                            {
                                $('#PARAMETER_LIST_' + index).val(queryString);
                                //alert(StringConfig);
                                swal('Lưu thành công', 'Bạn đã lưu dữ liệu thành công !', 'success');

                                $('#remoteformdialog').dialog('close');
                            } else
                            {
                                swal('Lỗi', 'Bạn kiểm tra lại bảng, các trường dữ liệu của tham số ' + data.msg, 'error');
                                //alert(data.msg);
                                $('#message_suc_err').append('<h3 style="color: red">Bạn kiểm tra lại bảng, các trường dữ liệu của tham số <p style="color: blue">' + mass + '</h3>');
                            }
                        },
                        error: function (data) {
                            var mass = data.responseText;
                            swal('Lỗi', 'Bạn kiểm tra lại bảng, các trường dữ liệu của tham số ' + mass, 'error');
                            //var dl=JSON.parse(mass);
                            //alert(dl.msg);
                            //alert('Lỗi: '+mass);
                            $('#message_suc_err').append('<h3 style="color: red">Bạn kiểm tra lại bảng, các trường dữ liệu của tham số <p style="color: blue">' + mass + '</h3>');
                        }
                    });
                } catch (e) {
                    console.log(e.toString());
                    swal('Lỗi', 'Bạn liên hệ với quản trị để được khắc phục. ' + e.toString(), 'error');
                }
            }
            
            function closeDialog()
            {
                $('#remoteformdialog').dialog('close');
            }
        </script>
        <style>
            body {
                background-color: rgba(201, 76, 76, 0.3);
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% 100%;
            }
        </style>
    </head>
    <body style="background: #E2E8C9" >
        <div style="background: #E2E8C9" align="center">
            <h3 style="color: green">Thêm tham số cho cột dữ liệu kiểu danh mục</h3>

            <table>
                <tr >
                    <td>
                        <input type="hidden" name="" value="<s:property  value="index" />" id="id_index">
                        <input type="hidden" name="" value="<s:property  value="khoa" />" id="id_khoa">
                        <label>Tên bảng dữ liệu: </label>
                    </td>
                    <td>
                        <textarea name="BANGSL_0" id="BANGSL_ID_0" rows="4" cols="20" style="margin: 2px 2px; height: 85px; width: 300px;" placeholder="DMPOS"><s:property value="cottruyvan.BANGSL"/></textarea>
                        <!--<input type="text" name="BANGSL_0" value="<s:property value="cottruyvan.BANGSL"/>" id="BANGSL_ID_0" style="width:180px" placeholder="DMPOS">-->
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Cột hiển thị: </label>
                    </td>
                    <td>
                        <input type="text" name="COTHIENTHI_0" value="<s:property value="cottruyvan.COTHIENTHI"/>" id="COTHIENTHI_ID_0" style="width:300px"  placeholder="PO_MA||\' -> \'||PO_TEN">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Cột tham số: </label>                    
                    </td>
                    <td>
                        <input type="text" name="COTTSO_0" value="<s:property value="cottruyvan.COTTSO"/>" id="COTTSO_ID_0" style="width: 300px" placeholder="PO_MA">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Điều kiện lọc: </label>
                    </td>
                    <td>                        
                        <input type="text" name="DKLOC_0" value="<s:property value="cottruyvan.DKLOC"/>" id="DKLOC_ID_0" style="width: 300px" placeholder="PO_MACN=\'002721\'">

                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Cột sắp xếp: </label>
                    </td>
                    <td>
                        <input type="text" name="DKSAPXEP_0" value="<s:property value="cottruyvan.DKSAPXEP"/>" id="DKSAPXEP_ID_0" style="width: 300px" placeholder="PO_MA">
                    </td>
                </tr>
                <tr>
                    <td>

                    </td>
                    <td>

                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div align="center">
                            <input type="button" value="Lưu dữ liệu" style="margin: 0px;" onclick="puthidenValue();" class="metroButtonStyle">
                            |
                            <input type="button" value="Thoát" style="margin: 0px;" onclick="closeDialog();" class="metroButtonStyle">
                        </div>

                    </td>
                </tr>
            </table>
            <div id="message_suc_err">
            </div>
        </div>
    </body>
</html>
