<%-- 
    Document   : balance-sheet-view
    Created on : Jun 3, 2014, 10:01:58 AM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<style>
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
    .ui-dialog{
        font-size: 11px;
    } 
    label.editdialog
    {
        font-weight:normal;
        color:#000000;
        letter-spacing:1pt;
        word-spacing:2pt;
        font-size:12px;
        text-align:left;
        font-family:arial, helvetica, sans-serif;
        line-height:1;
    }
    input.editdialog 
    {
        font-weight:normal;
        font-size:12px;
        text-align:left;
        font-family:arial, helvetica, sans-serif;
    }
    p.editdialog {
        font-weight:normal;
        color:#000000;
        letter-spacing:1pt;
        word-spacing:2pt;
        font-size:12px;
        text-align:left;
        font-family:arial, helvetica, sans-serif;
        line-height:1;
    }
</style>

<script>
    function testabc(obj) {           
        var number = obj.value;        
        alert(number);      
    }
</script>
<s:form id="editAccountForm" theme="simple">  
    <table>
        <tr>
            <td><s:label value="Tham số" cssClass="editdialog"/></td>   
            <td><p class="editdialog"><s:property value="editParam"/></p></td>
        </tr>     
        <tr>
            <td><s:label value="Dư đầu nợ" cssClass="editdialog"/></td>
            <td><s:textfield id="editOpenDebitTxt"
                         name="editOpenDebit" cssClass="editdialog" size="30"/></td>
        </tr>
        <tr>
            <td><s:label value="Dư đầu có" cssClass="editdialog"/></td>
            <td><s:textfield name="editOpenCredit" cssClass="editdialog" size="30"/></td>
        </tr>
        <tr>
            <td><s:label value="Phát sinh nợ" cssClass="editdialog"/></td>
            <td><s:textfield name="editTurnDebit" cssClass="editdialog" size="30"/></td>
        </tr>
        <tr>
            <td><s:label value="Phát sinh có" cssClass="editdialog"/></td>
            <td><s:textfield name="editTurnCredit" cssClass="editdialog" size="30"/></td>
        </tr>
        <tr>
            <td><s:label value="Dư cuối nợ" cssClass="editdialog"/></td>
            <td><s:textfield name="editCloseDebit" cssClass="editdialog" size="30"/></td>
        </tr>
        <tr>
            <td><s:label value="Dư cuối có" cssClass="editdialog"/></td>
            <td><s:textfield name="editCloseCreditTxt" cssClass="editdialog" size="30"/></td>
        </tr>
    </table>
</s:form>