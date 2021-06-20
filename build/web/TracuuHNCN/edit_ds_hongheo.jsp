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
        <title>Chỉnh sửa thông tin khách hàng</title>
        <script type="text/javascript" src="DMChitieu/js/jquery-1.4.4.min.js"></script>
        <script type="text/javascript">
            function closePopup() {
                window.opener.submitvalue(<%= request.getParameter("pagenum")%>);
                window.close();
            }
            function savedshongheo() {
                var url, sdata;
                url = "save_ds_hongheo.action?ds_makh=" + <%= request.getParameter("ds_makh")%>;
                sdata = jQuery("#frmsave").serialize();
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
        <form id="frmsave" name="frmsave">
            <input type="button" name="cmdclose" value="Lưu dữ liệu" onclick="savedshongheo()">
            <input type="button" name="cmdclose" value=" Quay ra" onclick="closePopup()">
            <input type="text" id="viewcontent" style="font-size: 14px; border: 0px; background: transparent; width: 55%;" readonly/>
            <hr>
            <TABLE id="dataTable" border="1" width="100%">
                <s:iterator value="lst_edit_dshn" status="rowstatus">
                    <tr>
                        <td>Mã khách hàng</td>
                        <td><input type="text" readonly value="<s:property value="DS_MAKH"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_MAKH"></td>
                        <td>Mã Tỉnh</td>
                        <td><input type="text" value="<s:property value="DS_MATINH"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_MATINH"></td>
                    </tr><tr>
                        <td>Mã huyện</td>
                        <td><input type="text" value="<s:property value="DS_MAHUYEN"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_MAHUYEN"></td>
                        <td>Mã xã</td>
                        <td><input type="text" value="<s:property value="DS_MAXA"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_MAXA"></td>
                    </tr><tr>
                        <td>Mã thôn</td>
                        <td><input type="text" value="<s:property value="DS_MATHON"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_MATHON"></td>
                        <td>Tên khách hàng</td>
                        <td><input type="text" value="<s:property value="DS_TENKH"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_TENKH"></td>
                    </tr><tr>
                        <td>Giới tính</td>
                        <td><input type="text" value="<s:property value="DS_GIOITINH"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_GIOITINH"></td>
                        <td>Ngày sinh</td>
                        <td><input type="text" value="<s:property value="DS_NGAYSINH"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_NGAYSINH"></td>
                    </tr><tr>
                        <td>Dân tộc</td>
                        <td><input type="text" value="<s:property value="DS_DANTOC"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_DANTOC"></td>
                        <td>Số CMND</td>
                        <td><input type="text" value="<s:property value="DS_SOCMT"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_SOCMT"></td>
                    </tr><tr>
                        <td>Ngày cấp</td>
                        <td><input type="text" value="<s:property value="DS_NGAYCAP"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_NGAYCAP"></td>
                        <td>Nơi cấp</td>
                        <td><input type="text" value="<s:property value="DS_NOICAP"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_NOICAP"></td>
                    </tr><tr>
                        <td>Trình độ học vấn</td>
                        <td><input type="text" value="<s:property value="DS_TD_HOCVAN"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_TD_HOCVAN"></td>
                        <td>Chủ hộ</td>
                        <td><input type="text" value="<s:property value="DS_FLAG_CH"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_FLAG_CH"></td>
                    </tr><tr>
                        <td>Quan hệ</td>
                        <td><input type="text" value="<s:property value="DS_QUANHE"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_QUANHE"></td>
                        <td>Đối tượng chính sách</td>
                        <td><input type="text" value="<s:property value="DS_DT_CHINHSACH"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_DT_CHINHSACH"></td>
                    </tr><tr>
                        <td>Thu nhập bình quân</td>
                        <td><input type="text" value="<s:property value="DS_TN_BINHQUAN"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_TN_BINHQUAN"></td>
                        <td>Loại khách hàng</td>
                        <td><input type="text" value="<s:property value="DS_LOAI_KH"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_LOAI_KH"></td>
                    </tr><tr>
                        <td>Ngày loại</td>
                        <td><input type="text" value="<s:property value="DS_NGAYLOAI"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_NGAYLOAI"></td>
                        <td>Năm xử lý</td>
                        <td><input type="text" value="<s:property value="DS_NAMSL"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_NAMSL"></td>
                    </tr><tr>
                        <td>Nguyên nhân</td>
                        <td><input type="text" value="<s:property value="DS_NGUYENNHAN"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_NGUYENNHAN"></td>
                        <td>Ghi chú</td>
                        <td><input type="text" value="<s:property value="DS_GHICHU"/> " name="lst_edit_dshn[<s:property  value="%{#rowstatus.index}" />].DS_GHICHU"></td>
                    </tr>
                </s:iterator>
            </TABLE>
        </form>
    </body>
</html>
