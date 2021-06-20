<%-- 
    Document   : balance-sheet-view
    Created on : Jun 3, 2014, 10:01:58 AM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head/>
<style>
    .pramTbl {
        margin:0px;padding:0px;
        /*        width:90%;*/
        border:1px solid #000000;
        -moz-border-radius-bottomleft:0px;
        -webkit-border-bottom-left-radius:0px;
        border-bottom-left-radius:0px;
        -moz-border-radius-bottomright:0px;
        -webkit-border-bottom-right-radius:0px;
        border-bottom-right-radius:0px;
        -moz-border-radius-topright:0px;
        -webkit-border-top-right-radius:0px;
        border-top-right-radius:0px;
        -moz-border-radius-topleft:0px;
        -webkit-border-top-left-radius:0px;
        border-top-left-radius:0px;
    }.pramTbl table{
        border-collapse: collapse;
        border-spacing: 0;
        /*        width:90%;*/
        /*        height:100%;*/
        margin:0px;padding:0px;
    }.pramTbl tr:last-child td:last-child {
        -moz-border-radius-bottomright:0px;
        -webkit-border-bottom-right-radius:0px;
        border-bottom-right-radius:0px;
    }
    .pramTbl table tr:first-child td:first-child {
        -moz-border-radius-topleft:0px;
        -webkit-border-top-left-radius:0px;
        border-top-left-radius:0px;
    }
    .pramTbl table tr:first-child td:last-child {
        -moz-border-radius-topright:0px;
        -webkit-border-top-right-radius:0px;
        border-top-right-radius:0px;
    }.pramTbl tr:last-child td:first-child{
        -moz-border-radius-bottomleft:0px;
        -webkit-border-bottom-left-radius:0px;
        border-bottom-left-radius:0px;
    }.pramTbl tr:hover td{

    }
    /*    .pramTbl tr:nth-child(odd){ background-color:#e5e5e5; }
        .pramTbl tr:nth-child(even)    { background-color:#ffffff; }*/
    .pramTbl td{
        vertical-align:middle;
        border:1px solid #000000;
        border-width:0px 1px 1px 0px;
        /*text-align:left;*/
        padding:1px;
        font-size:11px;
        font-family:Verdana, Arial, Helvetica, sans-serif;
        font-weight:normal;
        color:#000000;
        height: 25px;
    }.pramTbl tr:last-child td{
        border-width:0px 1px 0px 0px;
    }.pramTbl tr td:last-child{
        border-width:0px 0px 1px 0px;
    }.pramTbl tr:last-child td:last-child{
        border-width:0px 0px 0px 0px;
    }
    .pramTbl tr:first-child td{
        background:-o-linear-gradient(bottom, #999999 5%, #b2b2b2 100%);	
        background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #999999), color-stop(1, #b2b2b2) );
        background:-moz-linear-gradient( center top, #999999 5%, #b2b2b2 100% );
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#999999", endColorstr="#b2b2b2");	
        background: -o-linear-gradient(top,#999999,b2b2b2);
        background-color:#999999;
        border:0px solid #000000;
        text-align:center;
        border-width:0px 0px 1px 1px;
        font-size:11px;
        font-family:Verdana, Arial, Helvetica, sans-serif;
        font-weight:bold;
        color:#ffffff;
    }
    .pramTbl tr:first-child:hover td{
        /*        background:-o-linear-gradient(bottom, #999999 5%, #b2b2b2 100%);	
                background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #999999), color-stop(1, #b2b2b2) );
                background:-moz-linear-gradient( center top, #999999 5%, #b2b2b2 100% );*/
        /*        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#999999", endColorstr="#b2b2b2");	*/
        /*        background: -o-linear-gradient(top,#999999,b2b2b2);
                background-color:#999999;*/
    }
    .pramTbl tr:first-child td:first-child{
        border-width:0px 0px 1px 0px;
    }
    .pramTbl tr:first-child td:last-child{
        border-width:0px 0px 1px 1px;
    }
    .alignRight{
        text-align: right;        
    }

    .alignCenter {
        text-align: center;
    }    
    
    .readonly {
        background-color: #d0e9c6;
    }
</style>
<script>
    function getAllParamValue() {
        var rowtotal = document.getElementById("rowtotal").value;
        var rowvalue = "";
        for (i = 1; i <= rowtotal; i++) {
            for (j = 0; j <= 8; j++) {
                rowvalue = rowvalue
                        + document.getElementById("para" + i.toString() + j.toString()).value
                        + "#";
            }
        }
        alert("Bạn chắc chắn muốn lưu: " + rowvalue);
        document.getElementById("paramDataArray").value = rowvalue;
    }
</script>
<div>  
    <s:form id="paramEditForm" theme="simple" 
            action="saveParam.action">
        <table class="pramTbl" style="width: 100%;">
            <tr>
                <td>STT</td>            
                <td>Tên</td>            
                <td>Loại tham số</td>
                <td>Mô tả</td>
                <td>Ví trí hiển thị</td>
                <td>Bảng tham chiếu</td>
                <td>Cột hiển thị </td>
                <td>Cột tham số</td>
                <td>Điều kiện lọc</td>
                <td>Điều kiện sắp xếp</td>
            </tr>
            <s:set var="rowtotal" value="0"></s:set>
            <s:iterator value="reportParam" status="stat">        
                <s:set var="rowtotal" value="%{#stat.count}"/>
                <tr>          
                    <td class="alignCenter">
                        <s:property value="#stat.count"/>
                    </td>
                    <td>
                        <s:textfield key="paramName"
                                    id="para%{#stat.count}0"
                                    readonly="true"
                                    cssClass="readonly"
                                    />
                    </td>
                    <td>
                        <s:select headerKey="-1"
                                  list="#{'1':'T','2':'L','3':'D'}" 
                                  name="corespondParamType" 
                                  value="%{getMappingCode(corespondParamType,4)}" 
                                  id="para%{#stat.count}1"/>
                    </td>
                    <td>
                        <s:textfield key="paramDescript"
                                     id="para%{#stat.count}2"/>
                    </td>
                    <td>
                        <s:textfield key="order" size="8"
                                     id="para%{#stat.count}3"/>
                    </td>
                    <td>
                        <s:textfield key="refTable"
                                     id="para%{#stat.count}4"/>
                    </td>
                    <td>
                        <s:textfield key="displayColumn"
                                     id="para%{#stat.count}5"/>
                    </td>
                    <td>
                        <s:textfield key="paramColumn"
                                     id="para%{#stat.count}6"/>
                    </td>
                    <td>
                        <s:textfield key="filterCondition"
                                     id="para%{#stat.count}7"/>
                    </td>
                    <td>
                        <s:textfield key="orderCondition"
                                     id="para%{#stat.count}8"/>
                    </td>
                </tr>
            </s:iterator>
            
            <s:hidden value="%{#rowtotal}" id="rowtotal"/>
            <s:hidden id="paramDataArray" value="" name="paramDataArray"/>
            
            <s:hidden name="selectedGroup"/>
            <s:hidden name="autoGenerateRptCode"/>
            <s:hidden name="selectedReport"/>
            
            
            <s:hidden name="reportUpdateManager.reportInfor.reportCode"/>
            <s:hidden name="reportUpdateManager.reportInfor.reportGroupCode"/>
            <s:hidden name="reportUpdateManager.reportInfor.shortcutName"/>
            <s:hidden name="reportUpdateManager.reportInfor.descript"/>
            <s:hidden name="reportUpdateManager.reportInfor.reportUnit"/>
            <s:hidden name="reportUpdateManager.reportInfor.reportTerm"/>
            <s:hidden name="reportUpdateManager.reportInfor.reportType"/>
            <s:hidden name="reportUpdateManager.reportInfor.reportGrade"/>
            <s:hidden name="reportUpdateManager.reportInfor.jaserFileName"/>
            <s:hidden name="reportUpdateManager.reportInfor.generatedName"/>
            
            
            <tr><td style="border: 0;" colspan="10">&nbsp;</td></tr>
            <tr style="border: 0;">
                <td style="border: 0;" colspan="5">
                    <div id="saveParamDiv"/> 
                </td>                
                <td colspan="5" class="alignRight">
                    <sj:submit value="Lưu tham số" targets="saveParamDiv"
                               onclick="getAllParamValue();"/>
                </td>
            </tr>
        </table>
    </s:form>
</div> 