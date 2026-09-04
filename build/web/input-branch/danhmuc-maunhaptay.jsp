<%-- 
    Document   : danhmuc-maunhaptay
    Created on : Dec 11, 2018, 10:27:34 AM
    Author     : BAOANH
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style>
    #input_chitieu{
        width: 98%;              
        /*border: 1px solid;*/ 
        padding-bottom: 2px;
        /*background: #b6f6ec;*/
    }
    #input_bangdl{
        width: 100%;              
        /*border: 1px solid;*/ 
        /*padding-bottom: 2px;*/
        height: 350px;
        background: #c1e2b3;
        margin: 5px 5px 10px 10px;
        /*overflow: scroll;*/
        /*display: block;*/
        /*position: absolute;*/

    }
</style>
<script>
    $(document).ready(function () {
        onchangeRadio();
    });
</script>
<html>

    <body>
        <div align="center">
            <form id="id_themchitieu" action="saveChitieunhaptay.action">
                <input type="hidden" name="khoa" id="id_khoamau" value="<s:property value="khoa"/>" />

                <h2 style="color: green">Cấu hình mẫu biểu nhập tay</h2>
                <input type="hidden" name="loaimau_daluu" id="id_loaimau_daluu" value="<s:property value="loaimau_daluu"/>" />
                <div id="id_loaichitieu" style="display: none;">
                    <label>Loại mẫu: </label>
                    <input type="radio" name="loai_chitieu" value="CT" <s:if test="macdinh_chitieu == 'CT'">checked="checked"</s:if> onchange="$('#id_message').empty();onchangeRadio()" /> Loại chỉ tiêu
                    <input type="radio" name="loai_chitieu" value="TB" <s:if test="macdinh_chitieu == 'TB'">checked="checked"</s:if> onchange="$('#id_message').empty();onchangeRadio()" /> Loại cột dữ liệu
                    <input type="radio" name="loai_chitieu" value="QR" <s:if test="macdinh_chitieu == 'QR'">checked="checked"</s:if> onchange="$('#id_message').empty();onchangeRadio()" /> Loại truy vấn
                </div>
                <br/>
                <div id="input_chitieu">
                </div>
                <div id="input_bangdl" align="center">
                </div>
                <br/>
                <!--<div id="id_save">-->
                <button type="submit" class="metroButtonStyle" onclick="$('#id_message').empty();">Lưu chỉ tiêu</button>
                <!--</div>-->

            </form>
        </div>
        <div id="id_message" style="margin: 10px;">

        </div>
    </body>
</html>
