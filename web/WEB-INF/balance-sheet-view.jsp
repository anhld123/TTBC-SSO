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

<style>
    .BalanceSheetStyle {
        margin:0px;padding:0px;
        width:100%;
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
    }.BalanceSheetStyle table{
        border-collapse: collapse;
        border-spacing: 0;
        width:100%;
        /*        height:100%;*/
        margin:0px;padding:0px;
    }.BalanceSheetStyle tr:last-child td:last-child {
        -moz-border-radius-bottomright:0px;
        -webkit-border-bottom-right-radius:0px;
        border-bottom-right-radius:0px;
    }
    .BalanceSheetStyle table tr:first-child td:first-child {
        -moz-border-radius-topleft:0px;
        -webkit-border-top-left-radius:0px;
        border-top-left-radius:0px;
    }
    .BalanceSheetStyle table tr:first-child td:last-child {
        -moz-border-radius-topright:0px;
        -webkit-border-top-right-radius:0px;
        border-top-right-radius:0px;
    }.BalanceSheetStyle tr:last-child td:first-child{
        -moz-border-radius-bottomleft:0px;
        -webkit-border-bottom-left-radius:0px;
        border-bottom-left-radius:0px;
    }.BalanceSheetStyle tr:hover td{

    }
    .BalanceSheetStyle tr:nth-child(odd){ background-color:#e5e5e5; }
    .BalanceSheetStyle tr:nth-child(even)    { background-color:#ffffff; }.BalanceSheetStyle td{
        vertical-align:middle;
        border:1px solid #000000;
        border-width:0px 1px 1px 0px;
        /*text-align:left;*/
        padding:7px;
        font-size:11px;
        font-family:Verdana, Arial, Helvetica, sans-serif;
        font-weight:normal;
        color:#000000;
    }.BalanceSheetStyle tr:last-child td{
        border-width:0px 1px 0px 0px;
    }.BalanceSheetStyle tr td:last-child{
        border-width:0px 0px 1px 0px;
    }.BalanceSheetStyle tr:last-child td:last-child{
        border-width:0px 0px 0px 0px;
    }
    .BalanceSheetStyle tr:first-child td{
        background:-o-linear-gradient(bottom, #999999 5%, #b2b2b2 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #999999), color-stop(1, #b2b2b2) );
        background:-moz-linear-gradient( center top, #999999 5%, #b2b2b2 100% );
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#999999", endColorstr="#b2b2b2");	background: -o-linear-gradient(top,#999999,b2b2b2);

        background-color:#999999;
        border:0px solid #000000;
        text-align:center;
        border-width:0px 0px 1px 1px;
        font-size:12px;
        font-family:Verdana, Arial, Helvetica, sans-serif;
        font-weight:bold;
        color:#ffffff;
    }
    .BalanceSheetStyle tr:first-child:hover td{
        background:-o-linear-gradient(bottom, #999999 5%, #b2b2b2 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #999999), color-stop(1, #b2b2b2) );
        background:-moz-linear-gradient( center top, #999999 5%, #b2b2b2 100% );
        filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#999999", endColorstr="#b2b2b2");	background: -o-linear-gradient(top,#999999,b2b2b2);

        background-color:#999999;
    }
    .BalanceSheetStyle tr:first-child td:first-child{
        border-width:0px 0px 1px 0px;
    }
    .BalanceSheetStyle tr:first-child td:last-child{
        border-width:0px 0px 1px 1px;
    }

    .ui-dialog{
        font-size: 12px;
    } 

    .alignRight{
        text-align: right;        
    }

    .alignCenter {
        text-align: center;
    }
    .metroButtonStyle {
        font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
        display: block;
        color: rgb(255, 255, 255);
        text-decoration: none;
        text-align: center;
        width: 90px;
        height: 26px;
        padding: 5px;
        margin: 5px 0px 0px 5px;
        font-size: 12px;
        background: none repeat scroll 0 0 #808080;
        color: #FFF;
        border: 0px none;
        border-radius: 1px 1px 1px 1px;
        outline: 0px none;
    }
    .metroButtonStyle:hover {
        background: #018c3b;
    }
    .metroButtonStyle:active {
        background: #DCDCDC;
    }
    input.NumberDisplayStyle {                               
        /*        height: 20px;*/
        font-weight:bold;
        text-align: right;        
        padding: 0px 0px 0px 0px;
        border-style: solid;
        border-width: 1px;
        font-size: 9pt;
        height: 22px;
        width: 140px;
        /*        font-family:Arial,Georgia,Serif;*/
    }
    input.StringTextDisplay {
        background: #DCDCDC;   
        padding: 0px;        
        font-family:Arial,Georgia,Serif;
        font-size: 9pt;
        font-weight:bold;
        border-style: solid;
        border-width: 1px;
        height: 22px;
    }
</style>
<script>
</script>
<div id="balanceview_div">
    <div style="float:left; " id="mainview_div">    
        <table style="width: 100%;padding-top: 5px;">
            <tr>
                <td colspan="2">
                    <s:textfield name="totalAccount" value="Tổng cộng"  
                                 theme="simple" readonly="true" cssClass="StringTextDisplay"/>
                </td>
                <td>
                    <s:textfield name="open_debit_total" value="%{getText(account.open_debit,'format.Number')}" 
                                 theme="simple" cssClass="NumberDisplayStyle" readonly="true"/>
                </td>
                <td>
                    <s:textfield name="open_credit_total" value="%{getText(account.open_credit,'format.Number')}" 
                                 theme="simple" cssClass="NumberDisplayStyle" readonly="true"/>                                   
                </td>
                <td>
                    <s:textfield name="turn_debit_total" value="%{getText(account.turn_debit,'format.Number')}" 
                                 theme="simple" cssClass="NumberDisplayStyle" readonly="true"/>
                </td>
                <td>
                    <s:textfield name="turn_credit_total" value="%{getText(account.turn_credit,'format.Number')}"  
                                 theme="simple" cssClass="NumberDisplayStyle" readonly="true"/>
                </td>
                <td>
                    <s:textfield name="close_debit_total" value="%{getText(account.close_debit,'format.Number')}" 
                                 theme="simple" cssClass="NumberDisplayStyle" readonly="true"/>
                </td>
                <td>
                    <s:textfield name="close_credit_total" value="%{getText(account.close_credit,'format.Number')}"  
                                 theme="simple" cssClass="NumberDisplayStyle" readonly="true"/>   
                </td>            
            </tr>
        </table>    
    </div> 
    <div id="valueview_div" class="BalanceSheetStyle" >                        
        <table>                                    
            <tr>
                <td>Edit</td>
                <td>CN</td>
                <td>Tài khoản</td>
                <td>Dư đầu nợ</td>
                <td>Dư đầu có</td>
                <td>Phát sinh nợ</td>
                <td>Phát sinh có</td>
                <td>Dư cuối nợ</td>
                <td>Dư cuối có</td>
            </tr>
            <s:iterator value="accountList" status="stat">
                <tr>
                    <td class="alignCenter">                    
                        <s:url id="remoteurl1" action="editAccountBalance">
                            <s:param name="editParam">
                                ${pos_code}#${account_code}#${open_debit}#${open_credit}#${turn_debit}#${turn_credit}#${close_debit}#${close_credit}
                            </s:param>                                           
                        </s:url>
                        <sj:a 
                            openDialog="editAccountDialog"
                            href="%{remoteurl1}"     
                            targets="editAccountDialog"
                            >
                            Edit
                        </sj:a>                
                    </td>
                    <td><s:property value="pos_code"/></td>
                    <td><s:property value="account_code"/></td>
                    <td class="alignRight">                   
                        <fmt:formatNumber type="number" 
                                          maxFractionDigits="3" value="${open_debit}" />
                    </td>
                    <td class="alignRight">
                        <fmt:formatNumber type="number" 
                                          maxFractionDigits="3" value="${open_credit}"/>
                    </td>
                    <td class="alignRight">
                        <fmt:formatNumber type="number" 
                                          maxFractionDigits="3" value="${turn_debit}"/>
                    </td>
                    <td class="alignRight">
                        <fmt:formatNumber type="number" 
                                          maxFractionDigits="3" value="${turn_credit}"/>
                    </td>         
                    <td class="alignRight">
                        <fmt:formatNumber type="number" 
                                          maxFractionDigits="3" value="${close_debit}"/>
                    </td>
                    <td class="alignRight">
                        <fmt:formatNumber type="number" 
                                          maxFractionDigits="3" value="${close_credit}"/>
                    </td>  
                </tr>
            </s:iterator>
        </table>        
    </div>      
</div>

<sj:dialog 
    id="editAccountDialog" 
    buttons="{ 
        'Đóng':function() { 
            $( this ).dialog( 'close' ); 
        } 
    }"     
    autoOpen="false" 
    modal="true" 
    title="Thay đổi giá trị..."
    position="{my:'top', at:'top', of:$('#valueview_div')}"
    >    
</sj:dialog>
