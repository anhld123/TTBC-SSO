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
            $(".status").html("Bạn phải điền mã chỉ tiêu để kiểm tra");
        }
    }
    
    function onChangeChitieu()
    {
        try
        {
            var loai_nv=$("#idloai_nv").val();
            var ma_chitieu_cha=$("#idchitieucha").val();
//            alert(loai_nv+' '+ma_chitieu_cha);
            
            $.getJSON('load_Chitieu_chacon', {
                        loai_nv: loai_nv,
                        ma_chitieu_cha: ma_chitieu_cha
                    }, function (jsonResponse) {
                        //reload lai du lieu cho select option
                        var idchitieutruoc = $('#idchitieutruoc');
                        idchitieutruoc.find('option').remove();
                        $('<option>').val('1').text('-- Chỉ tiêu --').appendTo(idchitieutruoc);
                        $('<option>').val('').text('').appendTo(idchitieutruoc);
                        $.each(jsonResponse.chitieuconmap, function (key, value) {
                            $('<option>').val(key).text(value).appendTo(idchitieutruoc);
//                            alert(value);
                        });

                       
                        if (jsonResponse.message != null)
                        {
//                            alert(jsonResponse.message);
                            $('#message_suc_err').text(jsonResponse.message);
                        }
                    });
        }
        catch(e)
        {
            alert(e.toString());
        }
    }
</script>
<label id="idtitle" style="font-size: 14px; color: #18ab29; font-weight: bold">
    Thêm mới chỉ tiêu kế hoạch tín dụng xã
</label>
<p></p>
<table border="0"> 
    <tr>
        <td style="width: 130px">
            Chỉ tiêu cha:
        </td>
        <td style="width: 220px">
            <s:select theme="simple"
                       id="idchitieucha"
                       name="ma_chitieu_cha"
                       list="lstDmChitieu" 
                       listKey="sKey"
                       listValue="sDesc"
                       emptyOption="true" 
                       headerKey="-1"
                       headerValue="-- Chọn chỉ tiêu gốc --"
                       cssStyle="font-weight: bold;width: 300px; vertical-align: middle;"
                       onchange="onChangeChitieu()"></s:select>
        </td>
        </tr>
        <tr>
            <td>Thêm trước chỉ tiêu</td>
            <td>                
                     <s:select theme="simple"
                       id="idchitieutruoc"
                       name="ma_ct_truoc"
                       list="lstDmChitieu_truoc" 
                       listKey="sKey"
                       listValue="sDesc"
                       emptyOption="true" 
                       headerKey="-1"
                       headerValue="-- Chỉ tiêu --"
                       cssStyle="font-weight: bold;width: 300px; vertical-align: middle;"></s:select>
            </td>
        </tr>
         <tr>
            <td>Quyết định</td>
            <td>                
                     <s:select theme="simple"
                       id="idMaquyetdinh"
                       name="ma_quyetdinh"
                       list="lstQuyetdinh" 
                       listKey="sKey"
                       listValue="sDesc"
                       emptyOption="true" 
                       headerKey=""
                       headerValue="-- Quyết định --"
                       cssStyle="font-weight: bold;width: 300px; vertical-align: middle;"></s:select>
            </td>
        </tr>
        <tr>
            <td>Mã chỉ tiêu</td>
            <td><s:textfield id="idma_chitieu" name="ma_chitieu" theme="simple" onchange="checkmachitieu()"/>
                <img id="loadingImage_check" src="img/loading.gif" style="display:none"/><span style="color: red;font: bold" class="status"></span></td>
        </tr>
        <tr>
            <td>Ký tự hiển thị</td>
            <td><s:textfield id="idtt_ht" name="kytu_hienthi" theme="simple"/></td>
        </tr>
        <tr>
            <td>Tên chỉ tiêu</td>
            <td><s:textfield id="idtenct" name="ten_chitieu" theme="simple" size="100"/></td>
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
                <s:url id="saveChitieuAdd" action="saveChitieuAdd.action"/>
                <sj:submit id="idsaveAdd" formIds="idchitieuxa" value="Lưu chỉ tiêu"
                           targets="message_suc_err" indicator="loadingImage_next" href="%{saveChitieuAdd}" onBeforeTopics="before-next" 
                           onCompleteTopics="after-next"/>
            </td>

        </tr>
        <tr>
            <td colspan="2">
                <div id="message_suc_err"></div>
            </td>
        </tr>
    </table>