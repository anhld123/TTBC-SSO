<%-- 
    Document   : edit_parameter
    Created on : Jan 28, 2016, 3:00:25 PM
    Author     : BAOANH
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            .font_lable
            {
                font-size: larger;
                font-weight: bold;
                color: #900;
            }
        </style>
        <script>
            $(document).ready(function () {
                if ('<s:property value="key_hidden"/>' == 'ORACLE_REF_CURSOR')
                {
                    $("#id_luubctmp").prop( "disabled", true );
                    $("#idparameter").prop( "disabled", true );
                    $("#idclass_name").prop( "disabled", true );
                    $("#iddescription").prop( "disabled", true );
//                    alert('da vao disable');
                }
            });
            function submitloadData()
            {
                var fileTemplate = $('#fileTemplate').val();
                if (fileTemplate == null || fileTemplate == '' || fileTemplate.length < 1)
                {
                    $('#Message').html("<h2 style='color: red'>Không thể lấy ra được tên file excel mẫu ! </h2>");
                    return;
                }
                var idparameter = $('#idparameter').val();
                if (idparameter == null || idparameter == '' || idparameter.length < 1)
                {
                    $('#Message').html("<h2 style='color: red'>Bạn phải điền tham số trước khi luu ! </h2>");
                    $('#idparameter').focus();
                    return;
                }
                var iddescription = $('#idparameter').val();
                if (iddescription == null || iddescription == '' || iddescription.length < 1)
                {
                    $('#Message').html("<h2 style='color: red'>Bạn phải điền mô tả cho tham số trước khi lưu ! </h2>");
                    $('#iddescription').focus();
                    return;
                }
                $("#id_luubc")[0].click();
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title>JSP Page</title>-->
    </head>
    <body>
        <div id="editPara" align="center">
            <s:form id="editParame" action="saveParameter.action" theme="simple" validate="true">
                <s:hidden name="fileTemplate"></s:hidden>
                <s:hidden name="key_hidden"></s:hidden>
                    <p>
                        <span style="color:blue; font-weight: bolder; font-size: larger">Thêm, chỉnh sửa tham số</span>
                    <p>
                    <table border="1" cellspacing="0px" cellpadding="1px">
                        <tr class="font_lable">
                            <td width="150"><label id="mota_lbl" class="font_lable">Tham số:</label></td>
                            <td width="350"><s:textfield id="idparameter" name="parameter" cssClass="font_lable" size="80"/></td>
                    </tr>
                    <tr class="font_lable">
                        <td width="150"> <label id="mota_lbl" class="font_lable">Kiểu dữ liệu:</label></td>
                        <td width="350"><s:select id="idclass_name"
                                  name="class_name"
                                  list="lstParameters" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  value="defaultClassName"
                                  cssStyle="vertical-align: middle;" cssClass="font_lable">                    
                            </s:select></td>
                    </tr>
                    <tr class="font_lable">
                        <td width="150"><label id="mota_lbl" class="font_lable">Mô tả tham số:</label></td>
                        <td width="350"><s:textfield id="iddescription" name="description" cssClass="font_lable" size="80"/></td>
                    </tr>                   
                    <tr class="font_lable">
                        <td colspan="2" align="center">
                            <sj:submit id="id_luubc" value="Lưu tham số" targets="Message" cssClass="font_lable" cssStyle="display: none"/>
                            <%--<s:if test="key_hidden=='ORACLE_REF_CURSOR'">--%>
                            <!--<input type="button" id="id_luubctmp" name="name_luubctmp" onclick="submitloadData()" value="Lưu dữ liệu" class="font_lable"/>-->
                            <%--</s:if>--%>
                            <%--<s:else>--%>
                            <input type="button" id="id_luubctmp" name="name_luubctmp" onclick="submitloadData()" value="Lưu dữ liệu" class="font_lable"/>
                            <%--</s:else>--%>
                        </td>
                    </tr>
                </table>
                <img id="loadingImage-next" src="img/loaderB32.gif" style="display:none"/>
                <div id="loadingImageDiv_rpt" style="display: none;">
                    <img id="loadingImage_rpt" src='img/loading.gif' border='0'>
                </div>
                <div id="Message"></div>
            </s:form>
        </div>
    </body>
</html>
