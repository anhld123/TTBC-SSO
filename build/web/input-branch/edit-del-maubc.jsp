<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link  rel="stylesheet" type="text/css" href="input-branch/css/inputbranch.css"/>
<script>
   $.subscribe("completedelete", function (event, data) {
           $("#idEditDelQuery").click();
        });
    </script>
<div  align="center">
     <p></p>
        <div id="divTitle" align="center">
            Sửa xóa mẫu biểu
        </div>
        <br>
    <table id="id_table_ct" border="1" class="id_table_bang">
        <tr style="height: 30px">
            <th style="width: 10%">Số TT</th>
            <th style="width: 40%">Mô Tả</th>
            <th style="width: 10%">Sửa mẫu</th>
            <th style="width: 10%">Xóa mẫu</th>
        </tr>
        <s:iterator value="lstAllMau">                
            <tr style="height: 30px">
                <td style="text-align: center;">
                    <s:property value="sStt"></s:property>
                    </td>
                    <td>
                    <s:property value="sDesc"></s:property>
                    </td>
                    <td style="text-align: center;">
                    <s:url id="Edit" value="LoadAddNewForm.action" var="urlTag" escapeAmp="false">                        
                        <s:param name="loaimau_daluu">EDIT</s:param>
                        <s:param name="khoa" value="sKey"/>
                    </s:url>
                    <sj:a targets="containBcttv"  href="%{urlTag}" onBeforeTopics="myBeforeHandler" 
                                       onCompleteTopics="myCompleteTopics1">Sửa mẫu biểu</sj:a>
                    </td>

                    <td style="text-align: center;">
                    <s:url id="Delete" value="deleteMaubc.action">
                        <s:param name="khoa" value="sKey"/>
                    </s:url>
                    <sj:a targets="containBcttv"  href="%{Delete}" onCompleteTopics="completedelete" onClickTopics="onClickDel">Xóa mẫu biểu</sj:a>
                    </td>
                </tr>
        </s:iterator>
    </table>
</div>

