<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<style type="text/css">

    /*        for hiding the page banner */
    .pagebanner 
    {
/*        display: none;*/
        padding-top: 5px; 
    }
    /* for customizing page links */
    .pagelinks 
    {
        color: maroon;
        margin: 20px 5px 20px 5px;
    }   
    table.bctlsl_table_style
    {
        border: 1px solid #666;
        width: 100%;
        margin: 10px 0 10px 0px;
        font-family:  Arial;
        font-size:  13pt;
    }
    //         For odd and even row decoration 
    table.bctlsl_table_style tr.odd 
    {
        background-color: #00ffffff;
    }
    table.bctlsl_table_style tr.tableRowEven,
    table.bctlsl_table_style tr.even 
    {
        background-color: azure;
    }
    //Css for table elements 
    table.bctlsl_table_style th
    {
        padding: 2px 4px 2px 4px;
        text-align: left;
        vertical-align: top;        
        font-family:  Arial;
        font-size:  10pt;
    }
    table.bctlsl_table_style td
    {
        padding: 2px 4px 2px 4px;
        text-align: left;
        vertical-align: top;       
        font-family:  Arial;
        font-size:  10pt;
    }
    table.bctlsl_table_style thead tr 
    {
        background-color: #05B2D2;
    }

    table.bctlsl_table_style thead tr th
    {
        background-color: palegreen;
    }
    //         For changing the background colour while sorting 
    table.bctlsl_table_style th.sorted 
    {
        background-color: #1392e9;
    }
    table.bctlsl_table_style th.sorted a,
    table.bctlsl_table_style th.sortable a 
    {
        background-position: right;
        display: block;
        width: 100%;
    }
    table.bctlsl_table_style th a:hover 
    {
        text-decoration: underline;
        color: black;
    }
    table.bctlsl_table_style th a,
    table.bctlsl_table_style th a:visited 
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


<div id="display_table_div" style="padding-left: 5px;" class="main_div">
    <display:table id="row" name="reports" 
                   pagesize="15" 
                   cellpadding="5px;"
                   cellspacing="5px;"                                
                   class="bctlsl_table_style"
                   requestURI="bctlsl_list_report.action"
                   export="false">         
        <display:column title="Chọn" style="text-align:center;">
            <input type="radio" name="selectedNo" value="${row.no}"/> 
        </display:column>
        <display:column title="Stt" >
            <c:out value="${row_rowNum}"/>
        </display:column>
        <display:column property="group" title="Nhóm"/>           
        <display:column property="no" title="No"/>    
        <display:column property="name" title="Tên báo cáo"/>                                           
    </display:table>               
</div> 
