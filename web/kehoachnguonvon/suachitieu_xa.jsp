<%-- 
    Document   : themchitieu_xa
    Created on : Sep 22, 2016, 1:37:17 PM
    Author     : BAOANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<script>
    function checkmachitieu() {
        var ma_chitieu = $(idma_chitieu).val();
        var loai_nv = $(idloai_nv).val();
        var p1 = {ma_chitieu: ma_chitieu,
            loai_nv: loai_nv};
        var data1 = JSON.stringify(p1);
//        alert('idloai_nv=' + loai_nv + ' ma_chitieu=' + ma_chitieu);
        if (ma_chitieu.length >= 1) {
            $(".status").html("Đang kiểm tra mã chỉ tiêu...");
            $(loadingImage_check).show();
            $.ajax({
                url: "machitieuCheck.action?ma_chitieu=" + ma_chitieu + "&loai_nv=" + loai_nv,
                data: data1,
                dataType: 'json',
                contentType: 'application/json',
                type: 'POST',
                async: true,
                success: function (data) {
//                     alert(data.message);
                    $(loadingImage_check).hide();
                    $(".status").html(data.message);
//                    $(".status").ajaxComplete(
//                            function (event, request, settings) {
//                                $(".status").html(data.message);
//                                alert(data.message);
//                            });
                }, error: function (data)
                {

                }
            });
        } else {
            $(".status").html("username should be at least 0 characters");
        }
    }
</script>
<label id="idtitle" style="font-size: 14px; color: #18ab29; font-weight: bold">
    Sửa chỉ tiêu kế hoạch tín dụng xã
</label>
<p></p>
<table border="0">
    <tr>
        <td >Mã chỉ tiêu</td>
        <td><s:textfield id="idma_chitieu" name="ma_chitieu" value="%{ma_chitieu}" cssStyle="background: #CCCCCC;" theme="simple" onchange="checkmachitieu()" readonly="true"/>
            <img id="loadingImage_check" src="img/loading.gif" style="display:none"/><span style="color: red;font: bold" class="status"></span></td>
    </tr>
    <tr>
        <td>Ký tự hiển thị</td>
        <td><s:textfield id="idtt_ht" name="kytu_hienthi" value="%{kytu_hienthi}" theme="simple"/></td>
    </tr>
    <tr>
        <td>Tên chỉ tiêu</td>
        <td><s:textfield id="idtenct" name="ten_chitieu" value="%{ten_chitieu}" size="100" theme="simple"/></td>
    </tr>
    <tr>
        <td>Loại chỉ tiêu</td> 
        <!--,'2':'Chỉ tiêu cha'-->
        <td><s:select id="loaict" name="loai_ct" list="#{'1':'Chỉ tiêu con'}" value="1" theme="simple"/></td>
    </tr>
    <tr>
        <td colspan="2">
            <hr>
        </td>
    </tr>
    <tr>   
        <td colspan="2" align="center">
            <s:url id="idsaveSuaChitieu" action="saveSuaChitieu.action"/>
            <sj:submit id="idsaveAdd" formIds="idchitieuxa" value="Lưu chỉ tiêu"
                       targets="message_suc_err" indicator="loadingImage_next" href="%{idsaveSuaChitieu}" onBeforeTopics="before-next" 
                       onCompleteTopics="after-next"/>
        </td>

    </tr>
    <tr>
        <td colspan="2">
            <div id="message_suc_err"></div>
        </td>
    </tr>
</table>