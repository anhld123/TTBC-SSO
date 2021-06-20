<%-- 
    Document   : table_data_input
    Created on : Oct 26, 2015, 2:52:18 PM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<sj:head/>
<style>
    .errors {
        background-color:#FFCCCC;
        border:1px solid #CC0000;
        width:600px;
        /*height: 20px;*/
        margin-bottom:8px;
    }
    .errors li{ 
        list-style: none; 
    }
    .success {
        background-color:#DDFFDD;
        border:1px solid #009900;
        width:600px;
        /*height: 20px;*/
    }
    .success li{ 
        list-style: none; 
    }
    th{
        background-color: #DCDCDC;
        border-color: #999;
        height: 18px;
    }
    td{
        border-color: #999;
        height: 20px;
    }
    table.editDelete{
        border-collapse: collapse;
        width: 100%;
        border-color: #999;
    }
    table.editDelete tr:focus{
        background-color:#FFE47A;
        /*cursor: pointer; hover*/
    }

</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script>
            $.subscribe('savebeforediv', function (event, data) {
                $("#Content_data").empty();
                $("#message").hide();
                $("#DivSave").show();
                $("loadingImage_save").show();

            });

            $.subscribe('savecompletediv', function (event, data) {
                $("#DivSave").hide();
                $("#message").show();
                $("#loadingImage_save").hide();
            });
        </script>
    </head>
    <body>
        <s:form align="center" action="saveDsHongheo" theme="simple" >
            <div id="convent" align="center">
                <s:if test="hasActionMessages()">
                    <div class="success">
                        <s:actionmessage/>
                    </div>
                </s:if>
                <br>
                <s:if test="hasActionErrors()">
                    <div class="errors">
                        <s:actionerror/>
                    </div>
                </s:if>
            </div>
            <h3>Thông tin chi tiết về 10 dòng dữ liệu trong file excel</h3>
            <s:hidden name="fileUploadFileName"/>
            <table border="1" class="editDelete" align="center">
                <tr>
                    <th>Mã Tỉnh</th>
                    <th>Mã huyện</th>
                    <th>Mã xã</th>
                    <th>Mã thôn</th>
                    <th>Tên Chủ hộ</th>
                    <th>Giới tính</th>
                    <th>Ngày sinh</th>
                    <th>Số CMND</th>
                    <th>Ngày cấp CMND</th>
                    <th>Nơi cấp</th>
                    <th>Dân tộc</th>
                    <th>Phân loại</th>
                    <th>Ngày phân loại</th>
                </tr>
                <s:iterator value="#attr.lstDsHongheo" var="modeldsHongheo" status="rowstatus">
                    <tr>
                        <td><s:property  value="Matinh"/></td>
                        <td><s:property  value="Mahuyen"/></td>
                        <td><s:property  value="Maxa"/></td>
                        <td><s:property  value="Mathon"/></td>
                        <td><s:property  value="Tenkh"/></td>
                        <td><s:property  value="Gioitinh"/></td>
                        <td><s:property  value="Ngaysinh"/></td>
                        <td><s:property  value="Socmt"/></td>
                        <td><s:property  value="Ngaycap"/></td>
                        <td><s:property  value="Noicap"/></td>
                        <td><s:property  value="Dantoc"/></td>
                        <td><s:property  value="Loai_Kh"/></td>
                        <td><s:property  value="Ngayloai"/></td>
                    </tr>
                </s:iterator>
            </table>
            <br>
        </s:form>
    </body>
</html>
