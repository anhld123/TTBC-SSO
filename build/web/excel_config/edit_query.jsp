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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title>JSP Page</title>-->
        <style>
            .font_lable
            {
                font-size: larger;
                font-weight: bold;
                color: #900;
            }
        </style>
        <script>
            function submitloadData()
            {
                $('#Message').empty();
                var fileTemplate = $('#fileTemplate').val();
                if (fileTemplate == null || fileTemplate == '' || fileTemplate.length < 1)
                {
                    $('#Message').html("<h2 style='color: red'>Không thể lấy ra được tên file excel mẫu ! </h2>");
                    alert('Không thể lấy ra được tên file excel mẫu !');
                    return;
                }
                var idid = $('#idid').val();
                if (idid == null || idid == '' || idid.length < 1)
                {
                    $('#Message').html("<h2 style='color: red'>Bạn phải điền số thứ tự của truy vấn ! </h2>");
                    $('#idid').focus();
                    alert('Bạn phải điền số thứ tự của truy vấn !');
                    return;
                }
                var idmapdata = $('#idmapdata').val();
                if (idmapdata == null || idmapdata == '' || idmapdata.length < 1)
                {
                    $('#Message').html("<h2 style='color: red'>Bạn phải điền biến mapping dữ liệu ! </h2>");
                    $('#idmapdata').focus();
                    alert('Bạn phải điền biến mapping dữ liệu !');
                    return;
                }
                var iddescription = $('#iddescription').val();
                if (iddescription == null || iddescription == '' || iddescription.length < 1)
                {
                    $('#Message').html("<h2 style='color: red'>Bạn phải điền mô tả cho truy vấn trước khi lưu ! </h2>");
                    $('#iddescription').focus();
                    alert('Bạn phải điền mô tả cho truy vấn trước khi lưu !');
                    return;
                }
                var idquery = $('#idquery').val();
                if (idquery == null || idquery == '' || idquery.length < 1)
                {
                    $('#Message').html("<h2 style='color: red'>Bạn phải điền cho truy vấn trước khi lưu ! </h2>");
                    $('#idquery').focus();
                    alert('Bạn phải điền cho truy vấn trước khi lưu !');
                    return;
                }
                $("#id_luubc")[0].click();
            }
            //loadColumnQuery
            function onReloadColumn()
            {
                 $('#Message').empty();
                $.subscribe("myBeforeHandler", function (event, data) {
                    $("#loadingImageDiv_rpt").show();
                });
                $.subscribe("myBeforeTopics", function (event, data) {
                    $("#loadingImageDiv_rpt").show();
                    $("#messageDiv").empty();
                });
                try
                {
                    //lay ra ten file excel
                    var fileTemplate = $('#fileTemplate').val();
                    //lay ra truy van da dien tren giao dien
                    var query = $('#idquery').val();
                    if (query == null || query.length < 5)
                    {
                        alert('Bạn phải điền truy vấn trước khi load các cột để group');
                        return;
                    }
                    //goi action load cac column tu truy van
                    $.getJSON('loadColumnQuery', {
                        fileTemplate: fileTemplate,
                        query: query
                    }, function (jsonResponse) {
                        //reload lai du lieu cho select option
                        var group_key = $('#group_key');
                        group_key.find('option').remove();
                        $('<option>').val('1').text('---Chọn khóa cho group---').appendTo(group_key);
                        $('<option>').val('').text('').appendTo(group_key);
                        $.each(jsonResponse.columnNameMap, function (key, value) {
                            $('<option>').val(key).text(value).appendTo(group_key);
                        });

                        var value_group = $('#value_group');
                        value_group.find('option').remove();
                        $('<option>').val('1').text('---Chọn giá trị cho group---').appendTo(value_group);
                          $('<option>').val('').text('').appendTo(value_group);
                        $.each(jsonResponse.columnNameMap, function (key, value) {
                            $('<option>').val(key).text(value).appendTo(value_group);
                        });
                        if (jsonResponse.dummyMsg != null)
                        {
                            alert(jsonResponse.dummyMsg);
                            $('#Message').text(jsonResponse.dummyMsg);
                        }
                    });
                }
                catch (e)
                {
                    alert(e.toString());
                }
                $.subscribe("myCompleteTopics", function (event, data) {
                    $("#loadingImageDiv_rpt").hide();
                });
            }
        </script>
    </head>
    <body>
        <div id="editPara" align="center">
            <s:form id="editQry" action="saveQuery.action" theme="simple">

                <s:hidden name="fileTemplate"></s:hidden>
                <s:hidden name="key_hidden"></s:hidden>
                    <p>
                        <span style="color:blue; font-weight: bolder; font-size: larger">Thêm, chỉnh sửa truy vấn dữ liệu</span>
                    <table border="1" cellspacing="0px" cellpadding="1px">
                        <tr class="font_lable">
                            <td width="150"><label id="mota_lbl" class="font_lable">Số thứ tự:</label></td>
                            <td width="350"><s:textfield id="idid" name="id" cssClass="font_lable" size="10" /></td>
                    </tr>
                    <tr class="font_lable">
                        <td width="150"><label id="mota_lbl" class="font_lable">Biến lưu dữ liệu:</label></td>
                        <td width="350"><s:textfield id="idmapdata" name="mapdata" cssClass="font_lable" size="10" /></td>
                    </tr>
                    <tr class="font_lable">
                        <td width="150"><label id="mota_lbl" class="font_lable">Truy vấn:</label></td>
                        <td width="350"><s:textarea id="idquery" name="query" cssClass="font_lable" cols="100" rows="13" /></td>
                    </tr>    
                    <tr>
                        <td width="150"><label id="mota_lbl" class="font_lable">Khóa của group:</label> </td>
                        <td width="350">                    
                            <s:select  value="defaultKeyGroup"
                                id="group_key"
                                name="key_group"
                                list="lstObjField" 
                                onChangeTopics="reloadGroupList"
                                listKey="sKey"
                                listValue="sDesc"
                                emptyOption="true" 
                                headerKey="1"
                                headerValue="---Chọn khóa cho group---"
                                onBeforeTopics="myBeforeHandler" 
                                onCompleteTopics="myCompleteTopics1"
                                cssClass="font_lable"></s:select>
                            
                                <input type="button" id="idReloaddata" name="nameReloaddata" 
                                       onclick="onReloadColumn()" value="Tải dữ liệu" style="height:22px;width:85px; color: red; font-weight: bold;"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="150"><label id="mota_lbl" class="font_lable">Giá trị của group:</label></td>
                            <td width="350">        
                            <s:select  value="defaultValueGroup"
                                id="value_group"
                                formIds="editQry" 
                                reloadTopics="reloadGroupList" 
                                name="value_group"
                                list="lstObjField" 
                                listKey="sKey"
                                listValue="sDesc"
                                emptyOption="true" 
                                headerKey="1"
                                headerValue="---Chọn giá trị cho group---"
                                onBeforeTopics="myBeforeTopics"
                                onCompleteTopics="myCompleteTopics" 
                                cssClass="font_lable"></s:select>
                            </td>
                        </tr>
                        <tr class="font_lable">
                            <td width="150"><label id="mota_lbl" class="font_lable">Mô tả tham số:</label></td>
                            <td width="350"><s:textfield id="iddescription" name="description" cssClass="font_lable" size="80"/></td>
                    </tr>    
                    <tr class="font_lable">
                        <td colspan="2" align="center">                       
                            <sj:submit id="id_luubc" value="Lưu Truy vấn" targets="Message" cssClass="font_lable" cssStyle="display: none"/>
                            <input type="button" id="id_luubctmp" name="name_luubctmp" onclick="submitloadData()" value="Lưu dữ liệu" class="font_lable"/>
                    </tr>

                </table>
                <img id="loadingImage-next" src="img/loaderB32.gif" style="display:none"/>
                <div id="loadingImageDiv_rpt" style="display: none;">
                    <img id="loadingImage_rpt" src='img/loading.gif' border='0'>
                </div>
                <div id="Message" style="font: bold; font-size: large; color: red "><s:property value="message"/></div>
            </s:form>
        </div>
    </body>
</html>
