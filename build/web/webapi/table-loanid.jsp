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
<s:iterator value="#attr.loanTransactionList" var="modelView" status="rowstatus"> 
    <s:if test="%{#rowstatus.index==0}">
        <h3><span style="color: #007fff">THÔNG TIN GIAO DỊCH CỦA MÓN VAY <s:property value='loanId'/>
<!--                - Sản phẩm DIEN SAU - Đến hạn DIEN SAU-->
                <br> Mã khách hàng: <s:property value='Cif_No'/> - <s:property value='CustName'/>
                <br>Tổ <s:property value='GroupId'/> - <s:property value='GroupName'/> - <s:property value='MassOrg'/> - <s:property value='CustAddress'/>
            </span></h3>   
        </s:if>
    </s:iterator>
</div>
<table border="1" class="editDelete" align="center" style="width: 60%">
    <tr>

        <th style="width: 80px">Loại giao dịch</th>
        <th style="width: 80px">Ngày giao dịch</th>
        <th style="width: 90px">Số tiền giao dịch</th>
        <th style="width: 30px">Lãi suất</th>
        <th style="width: 80px">Tổng dư nợ</th>        
    </tr>
    <s:iterator value="#attr.loanTransactionList" var="modelView" status="rowstatus">
        <tr>  

            <td align = "center" style="width: 80px">
                <s:property value='TxnType'/>
            </td>

            <td align = "left" style="width: 80px">
                <s:property value='TxnDate'/>
            </td>


            <td align = "right" class="TD_DU_NO" style="width: 50px">
                <s:if test="DisbAmount>0">
                    <input type="text" value="<s:property value='DisbAmount'/>" name="DisbAmount" class="DU_NO number2" readonly="true"/>
                </s:if>
                <s:elseif test="PrinPaid>0">
                    <input type="text" value="<s:property value='PrinPaid'/>" name="PrinPaid" class="DU_NO number2" readonly="true"/>
                </s:elseif>
                <s:else>
                    <input type="text" value="<s:property value='IntPaid'/>" name="IntPaid" class="DU_NO number2" readonly="true"/>
                </s:else>
            </td>

            <td align = "right" class="TD_DU_NO" style="width: 50px">
                <input type="text" value="<s:property value='IntRate'/>" name="IntRate" class="DU_NO number" readonly="true"/>
            </td>
            <td align = "right" class="TD_DU_NO" style="width: 50px">
                <input type="text" value="<s:property value='PrinOS'/>" name="PrinOS" class="DU_NO number2" readonly="true"/>
            </td>
        </tr>
    </s:iterator>
</table>  