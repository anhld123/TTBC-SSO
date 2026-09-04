<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head/>

<style type="text/css">

    /*        for hiding the page banner */
    .pagebanner 
    {
/*        display: none;*/
        padding-top: 5px; 
        font-family:  Arial;
        font-size:  10pt;
    }
    /* for customizing page links */
    .pagelinks 
    {
        color: maroon;
        margin: 20px 5px 20px 5px;
    }   
    table.dtw_table_style
    {
        border: 1px solid #666;
        width: 100%;
        margin: 10px 0 10px 0px;
        font-family:  Arial;
        font-size:  11pt;
    }
    /*//         For odd and even row decoration*/ 
    table.dtw_table_style tr.odd 
    {
        background-color: #00ffffff;
    }
    table.dtw_table_style tr.tableRowEven,
    table.dtw_table_style tr.even 
    {
        background-color: azure;
    }
    /*//Css for table elements*/ 
    table.dtw_table_style th
    {
        padding: 2px 4px 2px 4px;
        text-align: left;
        vertical-align: top;        
        font-family:  Arial;
        font-size:  10pt;
    }
    table.dtw_table_style td
    {
        padding: 2px 4px 2px 4px;
        text-align: left;
        vertical-align: top;       
        font-family:  Arial;
        font-size:  10pt;
    }
    table.dtw_table_style thead tr 
    {
        background-color: #05B2D2;
    }

    table.dtw_table_style thead tr th
    {
        background-color: palegreen;
    }
    /*//         For changing the background colour while sorting*/ 
    table.dtw_table_style th.sorted 
    {
        background-color: #1392e9;
    }
    table.dtw_table_style th.sorted a,
    table.dtw_table_style th.sortable a 
    {
        background-position: right;
        display: block;
        width: 100%;
    }
    table.dtw_table_style th a:hover 
    {
        text-decoration: underline;
        color: black;
    }
    table.dtw_table_style th a,
    table.dtw_table_style th a:visited 
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


<div id="display_table_div" style="width: 60%;">
    <display:table id="row" name="logObj" 
                   pagesize="10" 
                   cellpadding="5px;"
                   cellspacing="5px;"                                
                   class="dtw_table_style"                   
                   requestURI="dtw_view_upload_log.action"
                   export="false">            
        <display:column title="Thứ tự" >
            <c:out value="${row_rowNum}"/>
        </display:column>
        <display:column property="dwh_filename" title="Tên file"/>           
        <display:column property="dwh_filesize" title="Kích thước"
                        style="text-align:right;"/>    
        <display:column property="dwh_upload_time" title="Thời gian xử lý"
                        style="text-align:right;"/>    
        <display:column property="dwh_upload_status" title="Trạng thái"
                        style="text-align:center;"/>    
        <display:column property="row_total" title="Tổng số dòng"
                        style="text-align:right;"/>    
        <display:column property="processed_row" title="Số dòng xử lý"
                        style="text-align:right;"/>            
    </display:table>               
</div> 
