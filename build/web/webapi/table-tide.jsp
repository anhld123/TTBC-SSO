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
<script src="js/jquery.number.js"></script>
<script src="js/format_num.js"></script>
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
        /*width: 100%;*/
        border-color: #999;
    }
    table.editDelete tr:focus{
        background-color:#FFE47A;
        /*cursor: pointer; hover*/
    }

</style>
<script>
    $('.number').number(true, 0);
//  
//            //Cac truong bang so --> se co so truong = 0

    $(document).ready(function () {
        $('input.number').css({"text-align": "right"});
        $('input.number2').css({"text-align": "right"});
        $('.number2').number(true, 0);
        $('.number').number(true, 2);
        $(".DU_NO").css({"width": "100%"});
    });

    $('.TD_DU_NO').focus(function () {
        $(this).closest('tr').addClass('highlight_row');
    });
    $('.TD_DU_NO').blur(function () {
        $(this).closest('tr').removeClass('highlight_row');
    });
    $('.DU_NO').focus(function () {
        $(this).closest('tr').addClass('highlight_row');
    });
    $('.DU_NO').blur(function () {
        $(this).closest('tr').removeClass('highlight_row');
    });

</script>
<h2><span style="color: #0000FF">Thông tin chi tiết truy vấn tài khoản TIDE <s:property value='groupId'/> </span></h2>
<table border="1" class="editDelete" align="center">
    <tr>

        <th style="width: 30px">Mã PGD</th>
        <th style="width: 50px">Mã KH</th>
        <th style="width: 120px">Tên KH</th>        
        <th style="width: 40px">Số CMT</th>
        <th style="width: 150px">Địa chỉ</th> 
        <th style="width: 30px">Mã SP</th> 
        <!--<th style="width: 50px">Mã thôn</th>-->

        <th style="width: 30px">Kỳ hạn</th>
        <th style="width: 30px">Trạng thái </th>
        <th style="width: 50px">Ngày GD</th>
        <th style="width: 70px">Số tiền GD</th>
        <th style="width: 30px">Lãi suất</th>
    </tr>
    <s:iterator value="#attr.tideInfoList" var="modelView" status="rowstatus">
        <tr>  
            <td align = "center" style="width: 30px" class="TD_DU_NO">
                <s:property value='MaPGD'/>
            </td>

            <td align = "center"  style="width: 50px" class="TD_DU_NO">
                <s:property value='MaKH'/>
            </td>

            <td align = "left" style="width: 120px" class="TD_DU_NO">
                <s:property value='TenKH'/>
            </td>
            <td align = "left" style="width: 40px">
                <s:property value='SoCMT'/>
            </td>

            <td align = "left" style="width: 150px">
                <s:property value='DiaChi'/>
            </td>
            <td align = "center" style="width: 30px">
                <s:property value='MaSP'/>
            </td>
             <td align = "center" class="TD_DU_NO" style="width: 30px">
                <s:property value='KyHan'/>
            </td>
            <td align = "center" style="width: 30px">
                <s:property value='TrangThai'/>
            </td>

            <td align = "left" style="width: 50px">
                <s:property value='NgayGD'/>
            </td>
             <td align = "right" class="TD_DU_NO" style="width: 50px">
                <input type="text" value="<s:property value='SoTien_GD'/>" name="SoTien_GD" class="DU_NO number2" readonly="true"/>
            </td>
              <td align = "right" class="TD_DU_NO" style="width: 30px">
                  <s:property value='LaiSuat'/>
                <!--<input type="text" value="<s:property value='LaiSuat'/>" name="LaiSuat" class="DU_NO number" readonly="true"/>-->
            </td>
        </tr>
    </s:iterator>
</table>  