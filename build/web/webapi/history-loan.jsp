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

<style>
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

<script>
    function renameCustid()
    {
        var value = $('#idtypeFind').val();
        if (value == '1')
        {
            $('#id_custid').text('Mã khách hàng');
//            $('#id_custid').attr('placeholder','Mã khách hàng');
            document.getElementsByName("custId")[0].placeholder = "Mã khách hàng";
        }
        if (value == '2')
        {
            $('#id_custid').text('Số chứng minh thư');
//            $('#id_custid').attr('placeholder','Số chứng minh thư');
            document.getElementsByName("custId")[0].placeholder = "Số chứng minh thư";
        }
        if (value == '3')
        {
            $('#id_custid').text('Số điện thoại');
//            $('#id_custid').attr('placeholder','Số điện thoại');
            document.getElementsByName("custId")[0].placeholder = "Số điện thoại";
        }
    }
</script>
<!DOCTYPE html>

<span id="id_custid">Mã khoản vay</span> &nbsp;
<input type="text" value="" name="loanId" id="idloanId" placeholder="Mã khoản vay" />
&nbsp;&nbsp;|
<input type="button" id="idtruyvan" name="nametruyvan"  value="Truy vấn" onclick="submittruyvan()"/>