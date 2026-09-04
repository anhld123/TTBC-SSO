<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head/>

<title></title>
<style type="text/css">

    /*        for hiding the page banner */
    .pagebanner 
    {
        display: none;
        padding-top: 5px; 
    }
    /* for customizing page links */
    .pagelinks 
    {
        color: maroon;
        margin: 20px 5px 20px 5px;
    }   
    table.eom_table_style
    {
        border: 1px solid #666;
        width: 100%;
        margin: 10px 0 10px 0px;
        font-family:  Arial;
        font-size:  10pt;
    }
    /*//         For odd and even row decoration*/ 
    table.eom_table_style tr.odd 
    {
        background-color: #00ffffff;
    }
    table.eom_table_style tr.tableRowEven,
    table.eom_table_style tr.even 
    {
        background-color: azure;
    }
    /*//Css for table elements*/ 
    table.eom_table_style th
    {
        padding: 2px 4px 2px 4px;
        text-align: left;
        vertical-align: top;        
    }
    table.eom_table_style td
    {
        padding: 2px 4px 2px 4px;
        text-align: left;
        vertical-align: top;       
    }
    table.eom_table_style thead tr 
    {
        background-color: #05B2D2;
    }

    table.eom_table_style thead tr th
    {
        background-color: palegreen;
    }
    /*//         For changing the background colour while sorting*/ 
    table.eom_table_style th.sorted 
    {
        background-color: #1392e9;
    }
    table.eom_table_style th.sorted a,
    table.eom_table_style th.sortable a 
    {
        background-position: right;
        display: block;
        width: 100%;
    }
    table.eom_table_style th a:hover 
    {
        text-decoration: underline;
        color: black;
    }
    table.eom_table_style th a,
    table.eom_table_style th a:visited 
    {
        color: black;
    }

    .main_div {
        font-family:  Arial;
        font-size:  11pt;
    }

    input[type="radio"] {
        -webkit-appearance: checkbox; /* Chrome, Safari, Opera */
        -moz-appearance: checkbox;    /* Firefox */
        -ms-appearance: checkbox;     /* not currently supported */
    }



    a.styled-link:link    {        
        background-color: #ccc;
        background-image: url(img/search-icon.png);
        background-position: left;
        padding-left: 20px;
        margin-right: 5px;
        background-repeat:no-repeat;        
    }  /* unvisited links */
    a.styled-link:visited { 
        color: #0c0 ;
        /*        font-weight: bold;*/

    }  /* visited links   */
    a.styled-link:hover   { 
        color: #00c ;
        font-weight: bold;         
    }  /* user hovers     */
    a.styled-link:active  { 
        color: #ccc ;
    }  /* active links    */
</style>
<html>
    <body>                
        <div class="main_div">                        
            <hr/>
            <div id="display_table_div">
                <display:table id="row" name="customers" 
                               pagesize="15" 
                               cellpadding="5px;"
                               cellspacing="5px;" 
                               style="margin-left:0px;margin-top:10px;border-collapse: true;"                        
                               requestURI="SMSListCustomer.action"
                               class="eom_table_style"
                               export="true"
                               >
                    <display:setProperty name="export.pdf.filename" value="logfile.pdf"/>
                    <display:setProperty name="export.excel.filename" value="logfile.xls"/>
                    <display:column title="TT" style="text-align:center;">
                        <c:out value="${row_rowNum}"/>
                    </display:column>
                    <display:column property="posCode" title="POS"
                                    style="text-align:center;"/>
                    <display:column title="CifNo" style="text-align:center;">
                        <c:set var="id0" value="${row.cifNo}" />                                       
                        <c:set var="id" value="${row.cifNo}','${row.bankAccount}" />                                       
                        <u><a href="javascript:callDirectLink('${id}')" 
                           style="text-decoration:none;"                                  
                           ><c:out value="${id0}" /> </a></u>
                    </display:column>
                    <display:column property="name" title="Name"/>    
                    <display:column property="status" title="Status"
                                    style="text-align:center;"/>  
                    <display:column property="bankAccount" title="BankAccount"
                                    style="text-align:right;"/>    
                    <display:column property="phoneNumber" title="PhoneNumber"
                                    style="text-align:right;"/>                        
                    <display:column property="customerType" title="CustomerType" 
                                    style="text-align:center;"/> 
                </display:table>
            </div>                  
        </div>        


    </body>
</html>


<script>
    function callDirectLink(cifNo,bankAccount) {
        
        var ht = screen.availHeight / 5 + 25;
        var wt = screen.availWidth / 5 + 15;
       
        var resize = window.open("SMSViewCustomer?"
                + "cifNo=" + cifNo                
                + "&bankAccount=" + bankAccount                
                + "&random=" + Math.random(),
                "IMS_REPORTS_0", "height=" + ht + ",width=" + wt
                + ",left=0,top=0,directories=no,status=no,menubar=no,\n\
        personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        if (navigator.userAgent.indexOf('Chrome') !== -1
                && parseFloat(
                        navigator.userAgent.substring(
                                navigator.userAgent.indexOf('Chrome') + 7
                                ).split(' ')[0]) >= 15) {
            resize.resizeBy(wt, ht);
        } else {
            resize.resizeTo(wt, ht);
        }
        resize.moveTo(wt, ht);
        resize.focus();
    }

</script>