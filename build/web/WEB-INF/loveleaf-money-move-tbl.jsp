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
    /*//         For odd and even row decoration*/ 
    table.loveleaf_table_style tr.odd 
    {
        background-color: #00ffffff;
    }
    table.loveleaf_table_style tr.tableRowEven,
    table.loveleaf_table_style tr.even 
    {
        background-color: azure;
    }
    /*//Css for table elements*/ 
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
    /*//         For changing the background colour while sorting*/ 
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


<div id="display_table_div">
    <s:form id="money_transaction_ID" theme="simple" 
            action="loveleaf_pos_update_status" >
        <display:table id="row" name="moneyTransactions" 
                       pagesize="10" 
                       cellpadding="5px;"
                       cellspacing="5px;"                                
                       class="loveleaf_table_style"
                       requestURI="loveleaf_money_move.action"
                       export="false">            
            <c:set var="pos_cd_VAR" value="${row.pos_cd}" />        
                <c:set var="val_dt_VAR" value="${row.val_dt}" />  
                <c:set var="status_desc_VAR" value="${row.status_desc}" />     
            <display:column title="Stt" >
                <c:out value="${row_rowNum}"/>
            </display:column>
            <display:column property="pos_cd" title="POS_CD"/>                   
            <display:column property="name" title="Tên"/>    
            <display:column property="amount" title="Số tiền" format="{0,number,#,###}"
                            style="text-align:right;"/>   
            <display:column property="poor_total" title="Cặp lá" format="{0,number,#,###}"
                            style="text-align:right;"/>   
            <display:column property="val_dt" title="Ngày giá trị"
                            style="text-align:right;"/>            
            <display:column title="Trạng thái hiện tại"                            
                            style="text-align:center;">    
                <div id="div_${pos_cd_VAR}_ID">
                       <c:out value='${status_desc_VAR}'/>
                </div>                
            </display:column>
            <display:column title="Chuyển/Bỏ chuyển"
                            style="text-align:center;">                                                      
                <a                    
                    href="#"
                    onclick="call_submit('<c:out value='${pos_cd_VAR}'/>', '<c:out value='${val_dt_VAR}'/>')"
                    >chuyển</a>  
            </display:column>
        </display:table>             
        <s:hidden name="submit_POS" id="submit_pos_ID" value=""/>
        <s:hidden name="submit_DATE" id="submit_date_ID"  value=""/>
        <div style="display: none;" >
            <sj:submit id="update_ID" 
                       name="update_NAME"                           
                       formIds="money_transaction_ID"
                       targets="update_result_div"                                                                      
                       button="false"
                       onBeforeTopics="before-next"
                       onCompleteTopics="after-next"
                       ></sj:submit> 
            </div>
    </s:form>
    <div id="update_result_div"></div>
    <script>
        var click_link_id = '';
        var update_div_ID = '';
        
        $.subscribe('before-next',
                function(event, data) {
                    $("#update_result_div").empty();
                    $("#update_result_div").hide();
                });

        $.subscribe('after-next',
                function(event, data) {
                    $("#update_result_div").show();
                    var status_DESC = document.getElementById('status_ID').value;
                    if (status_DESC === 'D')
                        $('#' + update_div_ID).html('Đã chuyển');
                    else
                        if (status_DESC === 'N')
                            $('#' + update_div_ID).html('Chưa chuyển');
                        else
                            if (status_DESC === 'E')
                                $('#' + update_div_ID).html('Lỗi cập nhật');
                            else
                                $('#' + update_div_ID).html('Đang chờ xử lý');
//                    if (click_link_id !== null)
//                        $('#' + click_link_id).load(
//                                location.href + ' #' + click_link_id
//                                );
                });

        function call_submit(object_1, object_2) {
            document.getElementById('submit_pos_ID').value = object_1;
            document.getElementById('submit_date_ID').value = object_2;
            click_link_id = 'div_'+object_1+'_ID';   
            update_div_ID = 'div_' +object_1 +'_ID';
            $("#update_ID").trigger("click");            
            return true;
        }
    </script>
</div> 
