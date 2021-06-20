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
    }
    /* for customizing page links */
    .pagelinks 
    {
        color: maroon;
        margin: 20px 5px 20px 5px;
    }   
    table.loveleaf_table_style
    {
        border: 1px solid #666;
        width: 100%;
        margin: 10px 0 10px 0px;
        font-family:  Arial;
        font-size:  10pt;
    }
    //         For odd and even row decoration 
    table.loveleaf_table_style tr.odd 
    {
        background-color: #00ffffff;
    }
    table.loveleaf_table_style tr.tableRowEven,
    table.loveleaf_table_style tr.even 
    {
        background-color: azure;
    }
    //Css for table elements 
    table.loveleaf_table_style th
    {
        padding: 2px 4px 2px 4px;
        text-align: left;
        vertical-align: top;        
    }
    table.loveleaf_table_style td
    {
        padding: 2px 4px 2px 4px;
        text-align: left;
        vertical-align: top;       
    }
    table.loveleaf_table_style thead tr 
    {
        background-color: #05B2D2;
    }

    table.loveleaf_table_style thead tr th
    {
        background-color: palegreen;
    }
    //         For changing the background colour while sorting 
    table.loveleaf_table_style th.sorted 
    {
        background-color: #1392e9;
    }
    table.loveleaf_table_style th.sorted a,
    table.loveleaf_table_style th.sortable a 
    {
        background-position: right;
        display: block;
        width: 100%;
    }
    table.loveleaf_table_style th a:hover 
    {
        text-decoration: underline;
        color: black;
    }
    table.loveleaf_table_style th a,
    table.loveleaf_table_style th a:visited 
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
    function callDirectLink(fullname) {
        var ht = screen.availHeight / 5 + 35;
        var wt = screen.availWidth / 5 + 20;

        var v_report_dt = document.getElementById(
                'selectedrptDate').value.toLocaleString();
        var period = document.getElementById('period_id').value;
        var all_month = document.getElementById(
                'gendata_FLG_ID'
                ).checked;
        
        var all_month_FLG = '';
        if (all_month) {
            all_month_FLG = 'Y';
        } else {
            all_month_FLG = 'N';
        }
        var permit = document.getElementById('permit_ID').value;

        var resize = window.open("loveleaf_load_donator?"
                + "donator_id=" + fullname
                + "&report_dt=" + v_report_dt
                + "&period="+period
                + "&gendata_FLG=" + all_month_FLG
                + "&permit=" + permit
                + "&random=" + Math.random(),
                "IMS_REPORTS_FRM1", "height=" + ht + ",width=" + wt
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

<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    </head>
    <div id="display_table_div">
        <display:table id="row" name="donateTransaction" 
                       pagesize="10" 
                       cellpadding="5px;"
                       cellspacing="5px;"                                
                       class="loveleaf_table_style"
                       requestURI="loveleaf_list_donator.action"    
                       export="false">
            <%-- <display:setProperty name="export.pdf" value="true" />
            <display:setProperty name="export.csv.filename" value="example.csv"/>
            <display:setProperty name="export.pdf.filename" value="example.pdf"/>
            <display:setProperty name="export.excel.filename" value="example.xls"/> --%>
            <display:column title="Stt" >
                <c:out value="${row_rowNum}"/>
            </display:column>
            <display:column property="ref_no" title="Số TC"/>
            <display:column  title="Mã nhà HT">
                <c:set var="id" value="${row.donator_id}" />        
                <a href="javascript:callDirectLink('${id}')" 
                   style="text-decoration:none;"                                  
                   ><c:out value="${id}" /> </a>
            </display:column>
            <display:column property="donator_name" title="Tên nhà HT"/>    
            <display:column property="donate_dt" title="Ngày CT"/>    
            <display:column property="donator_ac" title="Tài khoản"/>    
            <display:column property="donator_bank" title="Tại NH"/>             
            <display:column property="amount" title="Số tiền" format="{0,number,#,###}"/>   
            <display:column property="cust_remark" title="Ghi chú"/>    
            <display:column property="status_desc" title="Trạng thái"/>                                    
        </display:table>        

        <div class="exportlinks">Export options: 
            <s:url id="excel_download_ID" 
                   action="loveleaf_download_donator" 
                   escapeAmp="false" >
                <s:param name="export_dt" value="tran_dt"/>
                <s:param name="period" value="period"/>
                <s:param name="gendata_FLG" value="gendata_FLG"/>
            </s:url>
            <s:a href="%{excel_download_ID}">
                <span class="export excel">Excel </span></s:a>|
            <a href="#">
                <span class="export pdf">PDF </span></a></div>

        <!--                    <script>                            
                                    if (!loveleaf_com)
                                        var loveleaf_com = {};
                                    loveleaf_com.loadpage = {
                                        onTableLoad: function () {
                                            // Gets called when the data loads
                                            $("#search_table th.sortable").each(function () {
                                                $(this).click(function () {
                                                    var link = $(this).find("a").attr("href");
                                                    $("#display_table_div").load(link, {},
                                                            loveleaf_com.loadpage.onTableLoad);
                                                    return false;
                                                });
                                            });
        
                                            $("#display_table_div .pagelinks a").each(function () {
                                                $(this).click(function () {
                                                    var link = $(this).attr("href");
                                                    var rplink = link.replace("gennew=Y", "gennew=N");
                                                    $("#display_table_div").load(rplink, {},
                                                            loveleaf_com.loadpage.onTableLoad);
                                                    return false;
                                                });
                                            });
        
                                            $("#display_table_div .pagelinks strong").each(function () {
                                                var htmlString = $(this).html();
                                                $(this).text("trang " + htmlString);
                                            });
                                        }
                                    };
                                </script>-->
    </div> 
</html>