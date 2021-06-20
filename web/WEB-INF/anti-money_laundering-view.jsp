<%-- 
    Document   : anti-money_laundering-view
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
<div id="balanceview_div" style="padding-left: 5px;">
    <div style="float:left; " id="mainview_div">     
    </div>    
    <div id="valueview_div" class="BalanceSheetStyle" >                        
        <table>                                    
            <tr>
                <td>Loại GD</td>
                <td>Số tham chiếu</td>
                <td>Ngày gdịch</td>
                <td>Mã PGD</td>
                <td>Lệnh CT</td>
                <td>Số tiền</td>
                <!--<td>Số tiền QĐ</td>-->
                <td>Nội dung CT</td>
                <td>Tên KH</td>
                <!--<td>Địa chỉ</td>-->
                <td>Kiểu KH</td>
                <td>CMTND</td>
                <td>Số HC</td>
                <td>Mã số thuế</td>
                <td>Số TK</td>
                <td>Loại TK</td>
                <td>TT tài khoản</td>
                <td>Mã ngân hàng</td>
                <td>TK thụ hưởng</td>
            </tr>
            <s:iterator value="antiMoneyList" status="stat">
                <tr>
                    <td><s:property value="loaibc"/></td>
                    <td><s:property value="ma_gd"/></td>
                    <td><s:property value="ngaygd"/></td>                   
                    <td><s:property value="ma_pgd"/></td>    
                    <td><s:property value="lenh_ct"/></td>
                    <td class="alignRight">                   
                        <fmt:formatNumber type="number" 
                                          maxFractionDigits="3" value="${sotien}" />
                    </td>
                    <td><s:property value="noidung_ct"/></td>
                    <td><s:property value="ten_ta"/></td>
                    <td><s:property value="kieukh"/></td>
                    <td><s:property value="cmt"/></td>
                    <td><s:property value="sohc"/></td>
                    <td><s:property value="mst"/></td>
                    <td><s:property value="sotk"/></td>
                    <td><s:property value="loai_tk"/></td>
                    <td><s:property value="tt_tk"/></td>
                    <td><s:property value="ma_nh"/></td>
                    <td><s:property value="sotk_th"/></td>
                </tr>
            </s:iterator>
        </table>        
    </div>            
</div>
