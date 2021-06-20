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
            <s:form id="id_themchitieu" action="saveChitieunhaptay.action" theme="simple">
                <s:hidden name="khoa" id="id_khoamau"/>

                <h2 style="color: green">Cấu hình mẫu biểu nhập tay</h2>
                <s:hidden name="loaimau_daluu" id="id_loaimau_daluu" />
                <s:radio label="Loại mẫu: " name="loai_chitieu" list="#{'CT':'Loại chỉ tiêu','TB':'Loại cột dữ liệu','QR':'Loại truy vấn'}"   
                         value="macdinh_chitieu" id="id_loaichitieu" onchange="$('#id_message').empty();onchangeRadio()" cssStyle="display: none;"/>
                <%--<sj:radio name="loai_chitieu" list="#{'CT':'Loại chỉ tiêu','TB':'Loại cột dữ liệu'}" value="macdinh_chitieu" id="id_loaichitieu" onchange="$('#id_message').empty();onchangeRadio()" onBeforeTopics="beforediv" onCompleteTopics="completediv"/>--%>
                <hr>
                <div id="input_chitieu">
                </div>
                <div id="input_bangdl" align="center">
                </div>
                <hr>
                <!--<div id="id_save">-->
                    <sj:submit value="Lưu chỉ tiêu" cssClass="metroButtonStyle" onclick="$('#id_message').empty();" targets="id_message" onBeforeTopics="beforediv" onCompleteTopics="completediv"/>
                <!--</div>-->
                
            </s:form>
        </div>
        <div id="id_message" style="margin: 10px;">

        </div>
    </body>
</html>
