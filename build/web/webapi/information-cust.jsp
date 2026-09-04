<%-- 
    Document   : input_main
    Created on : Oct 26, 2015, 1:45:53 PM
    Author     : LION
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<script>
    function renameCustid()
    {
        var value = $('#idtypeFind').val();
        if (value == '1')
        {
            $('#id_custid').text('Mã khách hàng');
            $('#idcustId').val('');
            $("#table_data").empty();
            $("#table_data").text('');
            document.getElementsByName("custId")[0].placeholder = "Mã khách hàng";
        }
        if (value == '2')
        {
            $('#id_custid').text('Số chứng minh thư');
            $('#idcustId').val('');
            $("#table_data").empty();
            $("#table_data").text('');
            document.getElementsByName("custId")[0].placeholder = "Số chứng minh thư";
        }
        if (value == '3')
        {
            $('#id_custid').text('Số điện thoại');
            $('#idcustId').val('');
            $("#table_data").empty();
            $("#table_data").text('');
            document.getElementsByName("custId")[0].placeholder = "Số điện thoại";
        }
    }
</script>
<!DOCTYPE html>
<!--<table border="1" class="editDelete">
    <tr>
        <td>-->
Loại truy vấn
<select id="idtypeFind" name="typeFind" onchange="renameCustid()">
    <option value="1">Truy vấn theo mã khách hàng</option>
    <option value="2">Truy vấn theo chứng minh thư</option>
    <option value="3">Truy vấn theo số điện thoại</option>
</select>
&nbsp;&nbsp;|
<span id="id_custid">Mã khách hàng</span> &nbsp;
<input type="text" value="" name="custId" id="idcustId" placeholder="Mã khách hàng" />
&nbsp;&nbsp;|
<input type="button" id="idtruyvan" name="nametruyvan"  value="Truy vấn" onclick="submittruyvan()"/>

<!--        </td>
    </tr>
</table>-->