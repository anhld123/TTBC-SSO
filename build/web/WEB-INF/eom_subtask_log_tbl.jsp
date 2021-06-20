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
    //         For odd and even row decoration 
    table.eom_table_style tr.odd 
    {
        background-color: #00ffffff;
    }
    table.eom_table_style tr.tableRowEven,
    table.eom_table_style tr.even 
    {
        background-color: azure;
    }
    //Css for table elements 
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
    //         For changing the background colour while sorting 
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
        <p style="margin-left: 2px; color: #00c; margin-bottom: 2px;" 
           class="main_div"
           id="message_ID">
            <s:property value="%{message}"/></p>
        <hr/>
        <div class="main_div">            
            <s:form id="search_form" action="#">
                <p style="margin-left: 5px; margin-bottom: 5px; padding-top: 2px;"  >Tìm kiếm: 
                    <input type="text" id="search_txt" name="search_key" 
                           width="200" placeholder="từ khoá...">  
                    &nbsp;
                    <input type="radio" name="search_type" value="1" checked> pos </input>
                    &nbsp;
                    <input type="radio" name="search_type" value="2"> status </input>
                    &nbsp;
                    <s:url id="search_subtask_url" 
                           value="eom_search_subtask.action" escapeAmp="false">
                        <s:param name="subtask" value="para_subtask" />
                        <s:param name="report_dt" value="para_report_date" />
                        <s:param name="period" value="para_period" />
                    </s:url>
                    <sj:a href="%{search_subtask_url}" 
                          targets="search_result_div" 
                          button="false" 
                          formIds="search_form"
                          id="search_key_btn"
                          cssClass="styled-link"
                          onBeforeTopics="before-next-2"
                          onCompleteTopics="after-next-2"                          
                          > Xác nhận </sj:a>          
                        <script>
                            $.subscribe('before-next-2', function(event, data) {
                                $("#display_table_div").empty();
                                $("#display_table_div").hide();
                                $("#search_result_div").empty();
                            });

                            $.subscribe('after-next-2', function(event, data) {
                                com.loadpage.onTableLoad();
                                $("#search_result_div").show();
                            });

                            if (!com)
                                var com = {};
                            com.loadpage = {
                                onTableLoad: function() {
                                    // Gets called when the data loads
                                    $("#search_table th.sortable").each(function() {
                                        $(this).click(function() {
                                            var link = $(this).find("a").attr("href");
                                            $("#search_result_div").load(link, {},
                                                    com.loadpage.onTableLoad);
                                            return false;
                                        });
                                    });

                                    $("#search_result_div .pagelinks a").each(function() {
                                        $(this).click(function() {
                                            var link = $(this).attr("href");
                                            var rplink = link.replace("gennew=Y", "gennew=N");
                                            $("#search_result_div").load(rplink, {},
                                                    com.loadpage.onTableLoad);
                                            return false;
                                        });
                                    });

                                    $("#search_result_div .pagelinks strong").each(function() {
                                        var htmlString = $(this).html();
                                        $(this).text("trang " + htmlString);
                                    });
                                }
                            };
                        </script>
                    </p>        

            </s:form>
            <hr/>
            <div id="display_table_div">
                <display:table id="row" name="tasklogs" 
                               pagesize="10" 
                               cellpadding="5px;"
                               cellspacing="5px;" 
                               style="margin-left:0px;margin-top:10px;border-collapse: true;"                        
                               requestURI="eom_view_subtask_progress.action"
                               class="eom_table_style"
                               export="true"
                               >
                    <display:setProperty name="export.pdf.filename" value="logfile.pdf"/>
                    <display:setProperty name="export.excel.filename" value="logfile.xls"/>
                    <display:column title="Stt" >
                        <c:out value="${row_rowNum}"/>
                    </display:column>
                    <display:column property="pos_cd" title="POS_CD"/>
                    <display:column property="subtask_descript" title="SUBTASK_DESCRIPT"/>    
                    <display:column property="record_total" title="RECORD_TOTAL" format="{0,number,#,###}"/>    
                    <display:column property="error_msg" title="ERROR_MSG"/>    
                    <display:column property="status" title="STATUS"/>    
                </display:table>
            </div>      
            <div id="search_result_div"></div>
        </div>
    </body>
</html>