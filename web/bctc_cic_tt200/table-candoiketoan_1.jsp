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
<!--<script src="js/webapi.js"></script>-->
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

//  
//            //Cac truong bang so --> se co so truong = 0

    $(document).ready(function () {
        $('input.number').css({"text-align": "right"});
        $('input.number2').css({"text-align": "right"});
        $('.number').number(true, 3);
        $('.number2').number(true, 0);
        $(".NHAP_SO").css({"width": "100%"});
        $(".TEXT_VIEW").css({"width": "100%"});
    });

    $('.TD_DU_NO').focus(function () {
        $(this).closest('tr').addClass('highlight_row');
    });
    $('.TD_DU_NO').blur(function () {
        $(this).closest('tr').removeClass('highlight_row');
    });
    $('.NHAP_SO').focus(function () {
        $(this).closest('tr').addClass('highlight_row');
    });
    $('.NHAP_SO').blur(function () {
        $(this).closest('tr').removeClass('highlight_row');
    });
    $('.TEXT_VIEW').focus(function () {
        $(this).closest('tr').addClass('highlight_row');
    });
    $('.TEXT_VIEW').blur(function () {
        $(this).closest('tr').removeClass('highlight_row');
    });
</script>
<div id="formatdiv" style=" text-align: center;">

    <h2><span style="color: #007fff">Các chỉ tiêu trong Bảng cân đối kế toán </span></h2>
</div>
<table border="1" class="editDelete" align="center" style="width: 70%">
    <tr>
        <th style="width: 30px">Thứ tự</th>
        <th style="width: 50px">Mã chỉ tiêu</th>
        <th style="width: 250px">Tên chỉ tiêu</th> 
        <th style="width: 50px">Năm tài chính  <s:property value='nambc'/></th>
    </tr>
    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
        <tr>  
            <td align = "center" style="width: 30px" class="TD_DU_NO">
                <%--<s:property value='THUTU'/>--%>
                <s:if test="D4.equalsIgnoreCase('N') ">
                    <input type="text" style="text-align: center;font-weight:bold; background-color: #DCDCDC;" value="<s:property value='THUTU'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"  class="TEXT_VIEW" readonly="true"/>
                </s:if>
                <s:else>
                    <input type="text" style="text-align: center;" value="<s:property value='THUTU'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"  class="TEXT_VIEW" readonly="true"/>
                </s:else>

            </td>

            <td align = "left" style="width: 50px">
                <s:if test="D4.equalsIgnoreCase('N') ">
                    <input type="text" style="text-align: center;font-weight:bold; background-color: #DCDCDC;" value="<s:property value='MA'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"  class="TEXT_VIEW" readonly="true"/>
                </s:if>
                <s:else>
                    <input type="text" style="text-align: center;" value="<s:property value='MA'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"  class="TEXT_VIEW" readonly="true"/>
                </s:else>

            </td>

            <td align = "left" style="width: 250px">
                <%--<s:property value='TEN'/>--%>
                <s:if test="D4.equalsIgnoreCase('N') ">
                    <input type="text" style="font-weight:bold; background-color: #DCDCDC;" value="<s:property value='TEN'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"  class="TEXT_VIEW" readonly="true"/>
                </s:if>
                <s:else>
                    <input type="text" value="<s:property value='TEN'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"  class="TEXT_VIEW" readonly="true"/>
                </s:else>

            </td>

            <td align = "right" class="TD_DU_NO" style="width: 50px">
                <s:if test="D4.equalsIgnoreCase('N') ">
                    <input type="text" style="font-weight:bold; background-color: #DCDCDC;" value="<s:property value='D5'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"  class="NHAP_SO number2" readonly="true"/>
                </s:if>
                <s:else>
                    <input type="text"  value="<s:property value='D5'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"  class="NHAP_SO number2"/>
                </s:else>

            </td>

        </tr>
    </s:iterator>
</table>  