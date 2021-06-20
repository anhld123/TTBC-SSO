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
<script src="js/webapi.js"></script>
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
    .DU_NO
    {
        width: 100%;
        border: 0px;
        color: #000000;
        border-color: #18ab29;
        background: #F9F9F9;
        color:#666666;
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
<div id="formatdiv" style=" text-align: center;">
    <s:iterator value="#attr.groupInfoList" var="modelView" status="rowstatus"> 
        <s:if test="%{#rowstatus.index==0}">
            <h3><span style="color: #007fff; text-align: center">THÔNG TIN TỔ TIẾT KIỆM VAY VỐN TỔ</span><span style="color: #942a25"> <s:property value='Group_Id'/> - <s:property value='Leader_Cif_Name'/> - 
                    Thuộc <s:property value='Dvut_Name'/> 
                    <br>Xã <s:property value='Xa'/> - Tài khoản casa: <s:property value='Ac_No'/>                 
                </span></h3>   
            </s:if>
        </s:iterator>
</div>
<table border="1" class="editDelete" align="center" style="width: 70%">
    <tr>
        <th style="width: 50px">Mã khách hàng</th>
        <th style="width: 90px">Tên Khách hàng </th>

        <!--<th style="width: 50px">Tài khoản</th>-->
        <th style="width: 50px">Tình trạng</th>    

        <th style="width: 60px">Tổng dư nợ</th>

    </tr>
    <s:iterator value="#attr.groupInfoList" var="modelView" status="rowstatus">
        <tr>  


            <td align = "center" style="width: 50px">
                <a href="javascript:hienthichitiet('01','1', '<s:property value="MaKH"/>','' ,'', '<s:property value="posCode"/>' )" class="SOKU linkKh">
                    <s:property value='MaKH'/>
                </a> 
            </td>

            <td align = "left" style="width: 90px">
                <s:property value='TenKH'/>
            </td>
            <!--            <td align = "left" style="width: 70px">
            <s:property value='Ac_No'/>
        </td>-->
            <td align = "center" style="width:30px">
                <s:property value='Status'/>
            </td>
            <td align = "right" class="TD_DU_NO" style="width: 50px">
                <input type="text" value="<s:property value='DuNo'/>" name="DuNo" class="DU_NO number2" readonly="true"/>
            </td>

        </tr>
    </s:iterator>
</table>  