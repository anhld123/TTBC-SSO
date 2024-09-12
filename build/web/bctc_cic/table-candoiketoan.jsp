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
<%--<sj:head/>--%>
<script src="js/bctc_cic_tt200.js"></script>
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
    .NHAP_SO, .TEXT_VIEW
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
        $(".hideColumn").hide();
        //getCongthuc('CD225');
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
<s:form id="id_cicsave" name="name_cicsave" action="saveBctcCicTT200.action" theme="simple">
    <s:if test="Grade.equalsIgnoreCase('2')">
        <div id="formatdiv" style=" text-align: center;">
            <h3><span style="color: #007fff">Tổng số doanh nghiệp được đã nhập(Mã PGD - số doanh nghiệp) </span></h3>
        </div>

        <h3 style="color: #18ab29">
            <s:property value="totalDataView" escape="true"/>  
        </h3>       
    </s:if>

    <s:hidden name="ma_dn" id="madn"/>
    <s:hidden name="nambc" id="nam_bc"/>
    <s:hidden name="loai_module" id="loaimodule"/>
    <div id="formatdiv" style=" text-align: center;">
        <h2><span style="color: #007fff">
                <s:if test="loai_module.equalsIgnoreCase('CIC001') ">
                    Các chỉ tiêu trong Bảng cân đối kế toán  
                </s:if>
                <s:elseif test="loai_module.equalsIgnoreCase('CIC002')">
                    Các chỉ tiêu trong Báo cáo kết quả kinh doanh
                </s:elseif>
                <s:elseif test="loai_module.equalsIgnoreCase('CIC003')">
                    Các chỉ tiêu trong báo cáo lưu chuyển tiền tệ (Trực tiếp)
                </s:elseif>
                <s:else>
                    Các chỉ tiêu trong báo cáo lưu chuyển tiền tệ (Gián tiếp)
                </s:else>
            </span></h2>
    </div>
    <table border="1" class="editDelete" id="bctc_cic" align="center" style="width: 70%">
        <tr>
            <th style="width: 30px">Thứ tự</th>
            <th style="width: 50px">Mã chỉ tiêu</th>
            <th style="width: 250px">Tên chỉ tiêu</th> 
            <th style="width: 50px">Năm tài chính  <s:property value='new java.lang.Integer(nambc-1)'/></th>
            <th style="width: 50px">Năm tài chính  <s:property value='new java.lang.Integer(nambc)'/></th>
        </tr>
        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
            <tr>  
                <td align = "center" style="width: 30px" class="TD_DU_NO">
                    <%--<s:property value='THUTU'/>--%>
                    <s:if test="NHAPTAY.equalsIgnoreCase('N') ">
                        <input type="text" style="text-align: center;font-weight:bold; background-color: #e1edf7;" value="<s:property value='THUTU'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"  class="TEXT_VIEW" readonly="true"/>
                    </s:if>
                    <s:else>
                        <input type="text" style="text-align: center;" value="<s:property value='THUTU'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"  class="TEXT_VIEW" readonly="true"/>
                    </s:else>

                </td>

                <td align = "left" style="width: 50px">
                    <s:if test="NHAPTAY.equalsIgnoreCase('N') ">
                        <input type="text" style="text-align: center;font-weight:bold; background-color: #e1edf7;" value="<s:property value='MA'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"  class="TEXT_VIEW" readonly="true"/>
                    </s:if>
                    <s:else>
                        <input type="text" style="text-align: center;" value="<s:property value='MA'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"  class="TEXT_VIEW" readonly="true"/>
                    </s:else>

                </td>
            <input type="hidden" value="<s:property value='D9'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"/>
            <input type="hidden" value="<s:property value='D10'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10"/>
            <td align = "left" style="width: 250px">
                <%--<s:property value='TEN'/>--%>
                <s:if test="NHAPTAY.equalsIgnoreCase('N') ">
                    <input type="text" style="font-weight:bold; background-color: #e1edf7;" value="<s:property value='TEN'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"  class="TEXT_VIEW" readonly="true"/>
                </s:if>
                <s:else>
                    <input type="text" value="<s:property value='TEN'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"  class="TEXT_VIEW" readonly="true"/>
                </s:else>

            </td>

            <td align = "right" class="TD_DU_NO" style="width: 50px">
                <s:if test="NHAPTAY.equalsIgnoreCase('N') ">
                    <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" onblur="congcapcongthuc('<s:property value='MA'/>', 'D5_')" class="NHAP_SO number2" readonly="true"  style="font-weight:bold; background-color: #e1edf7;"/>
                </s:if>
                <s:else>
                    <input type="text" id="D5_<s:property value='MA'/>" value="<s:property value='D5'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" onblur="congcapcongthuc('<s:property value='MA'/>', 'D5_')"  class="NHAP_SO number2" />
                </s:else>

            </td>

            <td align = "right" class="TD_DU_NO" style="width: 50px">
                <s:if test="NHAPTAY.equalsIgnoreCase('N') ">
                    <input type="text" id="D6_<s:property value='MA'/>" value="<s:property value='D6'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6"  onblur="congcapcongthuc('<s:property value='MA'/>', 'D6_')" class="NHAP_SO number2" readonly="true" style="font-weight:bold; background-color: #e1edf7;"/>
                </s:if>
                <s:else>
                    <input type="text" id="D6_<s:property value='MA'/>" value="<s:property value='D6'/>" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" onblur="congcapcongthuc('<s:property value='MA'/>', 'D6_')"   class="NHAP_SO number2"/>
                </s:else>

            </td>               


            <td class="hideColumn">
                <input type="text" value="<s:property value='D7'/>" name="D7" class="KH_CONGTHUC"/>
            </td>
            <td class="hideColumn">
                <input type="text" value="<s:property value='MA'/>" name="<s:property value='MA'/>" class="CIC_MA"/>
            </td>
        </tr>
    </s:iterator>
</table>  
<sj:submit id="idluudulieu" name="savedata" value="Lưu dữ liệu" targets="para_api" 
           onBeforeTopics="beforedivsave" onCompleteTopics="completedivsave" cssStyle="display: none;"/>
</s:form>