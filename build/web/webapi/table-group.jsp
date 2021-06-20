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
<sj:head/>
<script src="js/jquery.number.js"></script>
<script src="js/webapi.js"></script>
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
        $('.number').number(true, 3);
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

<s:iterator value="#attr.groupInfoList" var="modelView" status="rowstatus"> 
    <s:if test="%{#rowstatus.index==0}">
        <h3><span style="color: #007fff">THÔNG TIN TỔ TIẾT KIỆM VAY VỐN TẠI POS <s:property value='Pos_Cd'/>           
            </span></h3>   
        </s:if>
    </s:iterator>
<table border="1" class="editDelete" align="center">
    <tr>

        <th style="width: 50px">Mã tổ</th>
         <th style="width: 50px">Cif TT</th>
        <th style="width: 120px">Tên TT</th>        
        <th style="width: 80px">Tên DVUT</th>
        <th style="width: 50px">Tài khoản casa</th> 
        <th style="width: 50px">Số thành viên</th> 
        
        <th style="width: 70px">Tỉnh</th>
        <th style="width: 70px">Huyện </th>
        <th style="width: 70px">Xã</th>
        <th style="width: 120px">Thôn</th>
    </tr>
    <s:iterator value="#attr.groupInfoList" var="modelView" status="rowstatus">
        <tr>  
            <td align = "center" style="width: 30px" class="TD_DU_NO">
                <a href="javascript:hienthichitiet('04','','', '<s:property value="Group_Id"/>','', '<s:property value="posCode"/>'  )" class="SOKU linkKh">
                            <s:property value='Group_Id'/>
                        </a> 
            </td>

            <td align = "center"  style="width: 50px" class="TD_DU_NO">
                <s:property value='Leader_Cif'/>
            </td>

            <td align = "left" style="width: 120px" class="TD_DU_NO">
                <s:property value='Leader_Cif_Name'/>
            </td>
            <td align = "left" style="width: 100px">
                <s:property value='Dvut_Name'/>
            </td>

            <td align = "left" style="width: 50px">
                <s:property value='Ac_No'/>
            </td>
            <td align = "right" style="width: 30px">
                <s:property value='SoTV'/>
            </td>
            
            <td align = "left" style="width: 70px">
                <s:property value='Tinh'/>
            </td>

            <td align = "left" style="width: 70px">
                <s:property value='Huyen'/>
            </td>
            <td align = "left" style="width: 70px">
                <s:property value='Xa'/>
            </td>
             <td align = "left" style="width: 120px">
                <s:property value='Thon'/>
            </td>
           
        </tr>
    </s:iterator>
</table>  