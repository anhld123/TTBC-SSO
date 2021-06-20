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

//  
//            //Cac truong bang so --> se co so truong = 0

    $(document).ready(function () {
        $('input.number').css({"text-align": "right"});
        $('input.number2').css({"text-align": "right"});
        $('.number').number(true, 3);
        $('.number2').number(true, 0);
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

  <h2><span style="color: #007fff">Các chỉ tiêu trong báo cáo lưu chuyển tiền tệ (Gián tiếp) </span></h2>
</div>
<table border="1" class="editDelete" align="center">
    <tr>
        <th style="width: 30px">Mã POS</th>
        <th style="width: 80px">Mã món vay</th>
        <th style="width: 50px">Ngày vay</th> 
        <th style="width: 50px">Ngày đến hạn</th>
        <th style="width: 40px">Lãi suất</th>
        <th style="width: 50px">Mã SP</th>
        <th style="width: 30px">Tình trạng</th>
        <th style="width: 100px">Giải ngân</th>
        <th style="width: 80px">Nợ TH</th>
        <th style="width: 80px">Nợ QH</th>
        <th style="width: 80px">Nợ KH</th>
        <th style="width: 80px">Tổng Dư nợ</th> 
        <th style="width: 80px">Lãi dự thu</th>
        <th style="width: 80px">Lãi thực thu</th>
        <th style="width: 40px">Lãi tồn TH</th>
        <th style="width: 40px">Lãi tồn QH</th>
    </tr>
    <s:iterator value="#attr.loanList" var="modelView" status="rowstatus">
        <tr>  
            <td align = "center" style="width: 30px" class="TD_DU_NO">
                <s:property value='MaPGD'/>
            </td>
            <td align = "center"> 
                <a href="javascript:hienthichitiet('02','','','', '<s:property value="SoKU"/>', '<s:property value="MaPGD"/>' )" class="SOKU linkKh">
                    <s:property value='SoKU'/>
                </a> 
                <%--<s:property value='SoKU'/>--%>
            </td>

            <td align = "left">
                <s:property value='NgayVay'/>
            </td>

            <td align = "left">
                <s:if test="MaSP.equalsIgnoreCase('105')"></s:if>
                <s:else>
                    <s:property value='Ngay_DH_GDX'/>
                </s:else>
            </td>
            <td align = "right" class="TD_DU_NO" style="width: 40px">
                <s:property value='LaiSuat'/>
               <!--<input type="text" value="<s:property value='LaiSuat'/>" name="LaiSuat" class="DU_NO number" readonly="true"/>-->
            </td>
            <td align = "left">
                <s:property value='MaSP'/>
            </td>

            <td align = "center">
                <s:property value='TTMonVay'/>
            </td>
            <td align = "right" class="TD_DU_NO" style="width: 50px">
                <input type="text" value="<s:property value='GiaiNgan'/>" name="GiaiNgan" class="DU_NO number2" readonly="true"/>
            </td>

            <td align = "right" class="TD_DU_NO" style="width: 80px">
                <input type="text" value="<s:property value='DNTH'/>" name="DNTH" class="DU_NO number2" readonly="true"/>
            </td>

            <td align = "right" class="TD_DU_NO" style="width: 50px">
                <input type="text" value="<s:property value='DNQH'/>" name="DNQH" class="DU_NO number2" readonly="true"/>
            </td>

            <td align = "right" class="TD_DU_NO" style="width: 50px">
                <input type="text" value="<s:property value='DNKH'/>" name="DNKH" class="DU_NO number2" readonly="true"/>
            </td>

            <td align = "right" class="TD_DU_NO" style="width: 80px">
                <input type="text" value="<s:property value='TongDN'/>" name="TongDN" class="DU_NO number2" readonly="true"/>
            </td>           

            <td align = "right" class="TD_DU_NO" style="width: 40px">
                <input type="text" value="<s:property value='Lai_DT'/>" name="Lai_DT" class="DU_NO number2" readonly="true"/>
            </td>

            <td align = "right" class="TD_DU_NO" style="width: 40px">
                <input type="text" value="<s:property value='Lai_TT'/>" name="Lai_TT" class="DU_NO number2" readonly="true"/>
            </td>

            <td align = "right" class="TD_DU_NO" style="width: 40px">
                <input type="text" value="<s:property value='LaiTon_TH'/>" name="LaiTon_TH" class="DU_NO number2" readonly="true"/>
            </td>
            <td align = "right" class="TD_DU_NO" style="width: 40px">
                <input type="text" value="<s:property value='LaiTon_QH'/>" name="LaiTon_QH" class="DU_NO number2" readonly="true"/>
            </td>
        </tr>
    </s:iterator>
</table>  