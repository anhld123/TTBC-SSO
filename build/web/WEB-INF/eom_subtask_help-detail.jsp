<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:head/>
<sj:head/>

<style type="text/css">
    *{
        margin: 0px;
    }
    .main_div{
        padding:0px;
        width:100%;    
        /*        background:#f9f9f9;*/
        border:1px solid #ccc;
        text-align:left;   
        font-family:Arial;    
        font-size: 10pt;
    }
    table.table_1{
        border-style: solid;
        border-collapse: collapse;
        /*        background:#f9f9f9;*/
        width: 100%;
        font: 13px Arial, Helvetica, sans-serif;             
    }
    table.table_2{
        border-style: solid;
        border-collapse: collapse;
        width: 100%;
        font: 13px Arial, Helvetica, sans-serif; 
        line-height: 25px;
    }
    .tbhead{
        background-color: #5e5e55;
        font-weight: bold;
        height: 20px;
        color: #fff;
        text-align: center;            
    }
    .cscontent td{
        padding-left:5px;
        line-height: 25px;
        height: 25px;
        font: 13px Arial, Helvetica, sans-serif; 
    }
    .cscontent:hover{
        background-color: #ffff99;
    }

    .more {
/*        display: none;*/
        padding-top: 0px;
    }

    a.showLink, a.hideLink {
        text-decoration: none;
        -webkit-transition: 0.5s ease-out;
        background: transparent url('down.gif') no-repeat left; }

    a.hideLink {
        background: transparent url('up.gif') no-repeat left; 
    }


    .readonly {
        background-color: #eee; 
    }


</style>

<script>
    $.subscribe('before-next', function(event, data) {
        $("#subtask_execute_detail").empty();
        $("#subtask_execute_detail").hide();
        $("#loadingImageDiv").show();
    });

    $.subscribe('after-next', function(event, data) {
        $("#loadingImageDiv").hide();
        $("#subtask_execute_detail").show(); 
    });
</script>

<html>
    <body>           
        <h4><u>Script:</u></h4>        
        <s:form id="eom_subtask_dtls_form" theme="simple">
            <table style=" border-color: #ffffff; border-collapse: inherit;" >
                <tr>                    
                   <%----*  <td valign="top">
                        <s:url id="execute_subtask_url"
                               value="eom_execute_subtask.action"/>
                        <sj:submit href="%{execute_subtask_url}"  
                              id="execute_script_btn"
                              formIds="eom_subtask_dtls_form"
                              onBeforeTopics="before-next"
                              onCompleteTopics="after-next"
                              button="false" 
                              value="excute"
                              targets="subtask_execute_detail"
                              cssClass="eom_help_btn"></sj:submit>

                        </td>
                        <td style="width: 10px;"></td>
                        <td valign="top">
                            <s:url id="view_subtask_url"
                               value="eom_view_subtask_progress.action"/>
                            <sj:submit href="%{view_subtask_url}"  
                              id="viewlog_script_btn"
                              formIds="eom_subtask_dtls_form"
                              onBeforeTopics="before-next"
                              onCompleteTopics="after-next"
                              button="false" 
                              targets="subtask_execute_detail"
                              value="view log"
                              cssClass="eom_help_btn"></sj:submit>                                        
                        </td>    
                        <td style="width: 10px;">
                            <s:hidden name="para_subtask"/>
                            <s:hidden name="para_report_date"/>
                            <s:hidden name="para_period"/>
                        </td> ---%>
                        <td>
                            <div class="readmore">
                                <a href="#" id="excutescr-show" class="showLink" 
                                   onclick="showHide('excutescr');
                                           return false;"
                                               style="display: none;"
                                   >chi tiết ...</a>
                                <div id="excutescr" class="more">
                                    <div class="text">
                                    <s:textarea name="para_execute_script" cols="160" rows="10"
                                                readonly="true"
                                                id="para_execute_script"
                                                cssClass="readonly"
                                                />
                                </div>
                                <br/>
                                <div class="text">
                                    <s:textarea name="para_check_script" cols="160" rows="10"                                    
                                                readonly="true"
                                                cssClass="readonly"
                                                id="para_check_script"
                                                />
                                </div>
                                <p><a href="#" id="excutescr-hide" class="hideLink" 
                                      onclick="showHide('excutescr');
                                              return false;">ẩn ...</a></p>

                                <script>
                                    function showHide(shID) {                                        
                                    if (document.getElementById(shID)) {
                                            if (document.getElementById(shID + '-show').style.display !== 'none') {
                                                document.getElementById(shID + '-show').style.display = 'none';                                                 document.getElementById(shID).style.display = 'block';
                                                }
                                            else {
                                            document.getElementById(shID + '-show').style.display = 'inline';
                                                document.getElementById(shID).style.display = 'none';                                             }
                                        }
                                            }
                                </script>
                            </div>

                        </div>      
                    </td>
                </tr>
                <tr></tr>
                <%----*                    <tr>
                                        <td style="width: 250px;">Cập nhật lại trạng thái</td>
                                        <td colspan="3"> <s:property value="para_status"/> </td>
                                    </tr>--> ---%>
            </table>
        </s:form>

        <div style="float: left; width: 80%;padding-top: 20px;">
            <div id="loadingImageDiv" style="display: none;">
                <img id="loadingImage" src='img/loading.gif' border='0' >
            </div>
            <div id="subtask_execute_detail"></div>
        </div>
    </body>
</html>
