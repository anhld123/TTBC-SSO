<%-- 
    Document   : edit_ds_hongheo
    Created on : Nov 28, 2015, 2:54:59 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm mới thông tin khách hàng</title>
        <script type="text/javascript" src="../DMChitieu/js/jquery-1.4.4.min.js"></script>
        <script type="text/javascript">
            function add_ds_hongheo(){
                var url, sdata;
                url = "add_ds_hongheo.action";
                sdata = jQuery("#frmadd").serialize();
                $.post(url, sdata, function (data) {
                    if (data == "failed") {
                        document.getElementById("viewcontent").value = "Cập nhật thất bại.";
                        document.getElementById("viewcontent").style.color = "red";
                    } else {
                        document.getElementById("viewcontent").value = "Cập nhật thành công.";
                        document.getElementById("viewcontent").style.color = "green";
                    }
                });
            }
        </script>
    </head>
    <body>
        <form id="frmadd" name="frmadd">
            <input type="button" name="cmdsave" value="Lưu dữ liệu" onclick="add_ds_hongheo()">
            <input type="text" id="viewcontent" style="font-size: 14px; border: 0px; background: transparent; width: 55%;" readonly/>
            <hr>
            <TABLE id="dataTable" border="1" width="100%">
                    <tr>
                        <td>Mã khách hàng</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_MAKH"></td>
                        <td>Mã Tỉnh</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_MATINH"></td>
                    </tr><tr>
                        <td>Mã huyện</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_MAHUYEN"></td>
                        <td>Mã xã</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_MAXA"></td>
                    </tr><tr>
                        <td>Mã thôn</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_MATHON"></td>
                        <td>Tên khách hàng</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_TENKH"></td>
                    </tr><tr>
                        <td>Giới tính</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_GIOITINH"></td>
                        <td>Ngày sinh</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_NGAYSINH"></td>
                    </tr><tr>
                        <td>Dân tộc</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_DANTOC"></td>
                        <td>Số CMND</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_SOCMT"></td>
                    </tr><tr>
                        <td>Ngày cấp</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_NGAYCAP"></td>
                        <td>Nơi cấp</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_NOICAP"></td>
                    </tr><tr>
                        <td>Trình độ học vấn</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_TD_HOCVAN"></td>
                        <td>Chủ hộ</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_FLAG_CH"></td>
                    </tr><tr>
                        <td>Quan hệ</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_QUANHE"></td>
                        <td>Đối tượng chính sách</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_DT_CHINHSACH"></td>
                    </tr><tr>
                        <td>Thu nhập bình quân</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_TN_BINHQUAN"></td>
                        <td>Loại khách hàng</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_LOAI_KH"></td>
                    </tr><tr>
                        <td>Ngày loại</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_NGAYLOAI"></td>
                        <td>Năm xử lý</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_NAMSL"></td>
                    </tr><tr>
                        <td>Nguyên nhân</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_NGUYENNHAN"></td>
                        <td>Ghi chú</td>
                        <td><input type="text" value="" name="lst_edit_dshn[0].DS_GHICHU"></td>
                    </tr>
            </TABLE>
        </form>
    </body>
</html>
