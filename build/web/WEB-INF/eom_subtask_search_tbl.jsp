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
    /*         For Table css */

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
</style>

<script>
    $(document).ready(function(){
        // your code
        var message = document.getElementById('message_ID').innerHTML;
        var dem = 0;        
        // xu ly phan dam
        while(true){
//            alert(message);
            var vitri = message.search('&lt;dam&gt;');    
            if (vitri === -1)
                break;     
            if (dem%2===0){
                message = message.substr(0,vitri) 
                        + '<b>' + message.substr(vitri+11) ;
//                alert(message);
            }else {
                message = message.substr(0,vitri) 
                        + '</b>' + message.substr(vitri+11) ;
            }
            dem++;                   
        }
        // xy ly phan nghieng
        dem = 0;
        while(true){
            var vitri = message.search('&lt;nghieng&gt;');     
            if (vitri === -1)
                break;      
            if (dem%2===0){
                message = message.substr(0,vitri) 
                        + '<i>' + message.substr(vitri+15) ;
//                alert(message);
            }else {
                message = message.substr(0,vitri) 
                        + '</i>' + message.substr(vitri+15) ;
            }
            dem++;                  
        }
        //Mau
        dem = 0;
        while(true){
//            alert(message);
            var vitri = message.search('&lt;do&gt;');    
            if (vitri === -1)
                break;     
            if (dem%2===0){
                message = message.substr(0,vitri) 
                        + '<font color="red">' + message.substr(vitri+10) ;
//                alert(message);
            }else {
                message = message.substr(0,vitri) 
                        + '</font>' + message.substr(vitri+10) ;
            }
            dem++;                   
        } 
        dem = 0;
        while(true){
//            alert(message);
            var vitri = message.search('&lt;xanh&gt;');    
            if (vitri === -1)
                break;     
            if (dem%2===0){
                message = message.substr(0,vitri) 
                        + '<font color="blue">' + message.substr(vitri+12) ;
//                alert(message);
            }else {
                message = message.substr(0,vitri) 
                        + '</font>' + message.substr(vitri+12) ;
            }
            dem++;                   
        } 
//        alert(message);
        document.getElementById('message_ID').innerHTML = message;
        });
</script>

<html>
    <body>        
        <div class="main_div">
            <p style="margin-left: 2px; color: #006dcc; margin-bottom: 2px;"
               id="message_ID">
                <s:property value="message"/></p>
            <hr/>            
            <div id="search_table_div"                  >
                <display:table id="row" name="tasklogs" 
                               pagesize="10" 
                               cellpadding="5px;"
                               cellspacing="5px;" 
                               style="margin-left:0px;margin-top:10px;border-collapse: true;"                        
                               requestURI="eom_search_subtask.action"    
                               class="eom_table_style"
                               export="true">
                    <display:column title="Stt" >
                        <c:out value="${row_rowNum}"/>
                    </display:column>
                    <display:column property="pos_cd" title="POS_CD"/>
                    <display:column property="subtask_descript" title="SUBTASK_DESCRIPT"/>    
                    <display:column property="record_total" title="RECORD_TOTAL" 
                                    format="{0,number,#,###}"/>    
                    <display:column property="error_msg" title="ERROR_MSG"/>    
                    <display:column property="status" title="STATUS"/>    
                </display:table>
            </div>              
        </div>
    </body>
</html>