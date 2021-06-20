<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:head/>
<sj:head/>

<style type="text/css">
    *{
        margin: 0px;
    }
    table.table_1{
        border-style: solid;
        border-collapse: collapse;
        /*        background:#f9f9f9;*/
        width: 100%;
        font: 11pt Arial, Helvetica, sans-serif;   
        height: 20px;
    }
    table.table_2{
        border-style: solid;
        border-collapse: collapse;
        width: 100%;
        font: 11pt Arial, Helvetica, sans-serif; 
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
        padding-top: 10px;
    }

    a.showLink, a.hideLink {
        text-decoration: none;
        -webkit-transition: 0.5s ease-out;
        background: transparent url('down.gif') no-repeat left; }

    a.hideLink {
        background: transparent url('up.gif') no-repeat left; 
    }


    a.play_link:hover{
        background: #ffff99;
        /*        font-weight: bold;*/
    }

    a.play_link:active {   
    }

    a.play_link:link {        
        background-image: url(img/media_play.png);
        background-position: left;
        padding-left: 20px;
        margin-right: 5px;
        background-repeat:no-repeat;     
        background-color: #ccc;
    }


    a.view_link:link    {        
        background-color: #ccc;
        background-image: url(img/search-icon.png);
        background-position: left;
        padding-left: 20px;
        margin-right: 5px;
        background-repeat:no-repeat;        
    }  /* unvisited links */
    a.view_link:visited { 
        color: #0c0 ;
        /*        font-weight: bold;*/

    }  /* visited links   */
    a.view_link:hover   { 
        color: #00c ;
        /*        font-weight: bold;         */
    }  /* user hovers     */
    a.view_link:active  { 
        color: #ccc ;
    }  /* active links    */


    textarea {
        /*        -webkit-box-sizing: border-box;
                -moz-box-sizing: border-box;*/
        /*        box-sizing: border-box;*/
        width: 100%;
    }
    .mytextbox {
        background-color: #82FF9C;
        border-width: 1px;
        text-align: center;
    }
</style>

<script>
    function reload_page() {        
        location.reload(true);
    }
    
    var refresh_timer;
    
    function startTimer() {        
        var wait_time = 1*60*1000; // phut * giay * 1000
        refresh_timer = setInterval(reload_page, wait_time);        
    }
    
    function stopTimer() {
        clearInterval(refresh_timer);        
    }
    
    function onOffTimer(chkAuto){        
        if (chkAuto.checked) {            
            startTimer();
        }else {
            var r = confirm("Bỏ chọn chức năng tải lại trang tự động sẽ bị dừng. Bạn có chắc chắn muốn dừng?");
            if (r === true) {
              stopTimer();            
              alert('Đã dừng chức năng tải trang tự động. Tích chọn để bật lại.');
            } 
        }
    }
</script>

<html>
    <head>        
        <link rel="stylesheet" type="text/css" media="all" href="css/tooltip_1.css">
    </head>     
    <body>
    <div style="padding-left: 5px; padding-top: 10px;">
        <div id="banner" style="float: top;">
            <table style="width: 100%; padding-bottom: 10px;">
                <tr >
                    <td style="width: 40%;">
                        <font style="font-size: 12pt; font-weight: bold;" >Công việc thực hiện: </font>
                        <input type='text' name='para_task' value='<s:property value="para_task"/>' style="width:100px; font-size: 12px; font-weight: bold;" class="mytextbox" readonly="true">~
                        <input type='text' name="para_report_date" id="report_dt_ID" value='<s:property value="para_report_date"/>' style="width:100px;font-size: 12px; font-weight: bold;" class="mytextbox" readonly="true"/>~
                        <input type="text" name="para_period" id="period_ID" value="<s:property value='para_period'/>" style="width:100px;font-size: 12px; font-weight: bold;" class="mytextbox" readonly="true"/>                    
                    </td>
                    <td style="width: 60%;">          
                        <div id="container" style="margin-right: 5%; float: right;">                        
                            <input type="checkbox" id="automatic_reload_chk" onclick="onOffTimer(this)"> Tự động </input> |
                            <a href="#" onclick="reload_page();" style="float: right;" id='refresh_page_link'>[Tải lại trang]</a>                                                        
                        </div>
                    </td>                
                </tr>
            </table>
        </div>                       
        <div style="overflow: scroll; height: 400px;" 
             id="main_div"> 
            <s:form id="eom_subtask_form" 
                    theme="simple">
                <table border="1" class="table_2">
                    <tr class="tbhead">
                        <td>Thứ tự</td>
                        <td>Sub Task</td>
                        <td>Mô tả </td>
                        <td>Chi tiết</td>
                        <td>Thời gian</td>                                    
                        <td>Server</td>
                        <td>Trạng thái</td>                           
                        <td>Thực thi</td>
                        <td>Xem KQ</td>
                        <td>script</td>
                        <td>Ghi chú</td>
                    </tr>
                    <s:iterator value="eomSubTasks" status="eodSubTask">
                        <tr class="cscontent">
                            <td align="center"><s:property value="#eodSubTask.count"/></td>
                            <td>
                                <a href="javascript:callDirectLink('<s:property value="subtask"/>')" 
                                   style="text-decoration:none;"  
                                   class="tooltip right"
                                   data-tool="Thông tin chi tiết"
                                   >
                                    <font style="color: #802420">
                                    <u><s:property value="subtask"/></u></font>
                                </a>
                            </td>
                            <td><s:property value="short_descript"/></td>
                            <td>
                                <font style="color: blue; font: 13px Arial, Helvetica, sans-serif; "> 
                                <s:property value="descript"/> </font>
                            </td>
                            <td align="left">
                                <s:property value="expected_time"/>
                            </td>
                            <td align="center">                                
                                <s:url id="view_server_ID" 
                                       value="eom_SERVER_INFOR.action" escapeAmp="false">  
                                    <s:param name="subtask" value="subtask" />
                                    <s:param name="server" value="server"/>
                                </s:url>
                                <sj:a 
                                    href="%{view_server_ID}"
                                    formIds="eom_subtask_form"
                                    targets="subtask_detail_div"    
                                    onBeforeTopics="before-next"
                                    onCompleteTopics="after-next"
                                    button="false"          
                                    cssClass="tooltip right"
                                    data-tool="Kiểm tra thông tin Server"
                                    > <u><s:property value="server"/> </u> </sj:a>                         
                            </td>
                            <td align="center">                                                        
                                <div id="status_div_<s:property value="#eodSubTask.count"/>">                                  
                                <s:set name="st_string_ID" value="%{status}" />
                                <s:hidden value="%{#st_string_ID}"></s:hidden>

                                <s:if test="%{#st_string_ID.equalsIgnoreCase('D')}">
                                    <p style="background-color: #a4ecab;
                                       font-family: arial; font-size: 11pt;">
                                        <s:property value="%{get_status_descript(status)}"/>   
                                    </p>
                                </s:if>
                                <s:elseif test="%{#st_string_ID.equalsIgnoreCase('E')}">
                                    <p style="background-color: #d14; font-weight: bold;
                                       font-family: arial; font-size: 11pt; "
                                       >
                                        <s:property value="%{get_status_descript(status)}"/>   
                                    </p>
                                </s:elseif >
                                <s:elseif test="%{#st_string_ID.equalsIgnoreCase('P')}">
                                    <p style="background-color: #aaffff;
                                       font-family: arial; font-size: 11pt; "
                                       >
                                        <s:property value="%{get_status_descript(status)}"/>   
                                    </p>
                                </s:elseif >
                                <s:else>
                                    <p style="background-color: #ffff99; font-weight: bold;
                                       font-family: arial; font-size: 11pt;">
                                        <s:property value="%{get_status_descript(status)}"/>   
                                    </p>
                                </s:else>

                            </div>
                        </td>                                                
                        <td style="text-align: center;">
                            <s:set name="st_execute_permission" value="%{execute_permission}" />
                            <s:if test="%{#st_execute_permission.equalsIgnoreCase('1')}">
                                <s:url id="execute_subtask_detail" 
                                       value="eom_execute_subtask.action" escapeAmp="false">
                                    <s:param name="subtask" value="subtask" />
                                    <s:param name="report_dt" value="para_report_date" />
                                    <s:param name="period" value="para_period" />
                                    <s:param name="username" value="username"/>
                                </s:url>                            
                                <sj:a 
                                    href="%{execute_subtask_detail}"
                                    formIds="eom_subtask_form"
                                    targets="subtask_detail_div"                                  
                                    button="false"   
                                    onclick="callme('%{#eodSubTask.count}')"
                                    onBeforeTopics="before-next"
                                    onCompleteTopics="after-next"
                                    id="link1_%{#eodSubTask.count}"
                                    cssClass="play_link"
                                    >đồng ý                                          
                                </sj:a>                                           
                            </s:if>
                                    <s:else>
                                    <p style="background-color: #82FF9C; font-weight: bold;
                                       font-family: arial; font-size: 11pt;">
                                        [ODI]  
                                    </p>
                                </s:else>
                        <td style="text-align: center;">
                            <s:url id="view_subtask_progress" 
                                   value="eom_view_subtask_progress.action" escapeAmp="false">
                                <s:param name="subtask" value="subtask" />
                                <s:param name="report_dt" value="para_report_date" />
                                <s:param name="period" value="para_period" />
                                <s:param name="username" value="username"/>
                            </s:url>
                            <sj:a 
                                href="%{view_subtask_progress}"
                                formIds="eom_subtask_form"
                                targets="subtask_detail_div"    
                                onclick="callme('%{#eodSubTask.count}')"
                                onBeforeTopics="before-next"
                                onCompleteTopics="after-next"
                                cssClass="view_link"
                                button="false"    
                                id="link2_%{#eodSubTask.count}"
                                >xem</sj:a>          

                            <td style="text-align: center;">
                            <s:url id="view_script_detail" 
                                   value="eom_view_subtask_detail.action" escapeAmp="false">
                                <s:param name="subtask" value="subtask" />
                                <s:param name="report_dt" value="para_report_date" />
                                <s:param name="period" value="para_period" />
                                <s:param name="username" value="username"/>
                            </s:url>
                            <sj:a 
                                href="%{view_script_detail}"
                                formIds="eom_subtask_form"
                                targets="subtask_detail_div"      
                                onBeforeTopics="before-next"
                                onCompleteTopics="after-next"
                                cssClass="view_link"
                                button="false"                                  
                                >chi tiết</sj:a>           
                            </td>
                            <td>
                                <textarea name="remark" cols="36" rows="1" 
                                          id="script_type"                                        
                                          readonly="true"
                                          style="font-family: arial; font-size: 11pt;
                                          color: #116600;"
                                          class="tooltip right"
                                          data-tool="<s:property value="remark"/>"
                                ><s:property value="remark"/></textarea> 

                            <!--                            <a style="font-family: arial; font-size: 11pt;
                                                           color: #116600"/>-->
                        </td>
                        </tr>
                    </s:iterator>
                    <script>

                        var click_link_id = '';

                        function callme(obj) {
                            click_link_id = 'status_div_' + obj;
                        }

                        $.subscribe('before-next',
                                function(event, data) {
                                    $("#subtask_detail_div").empty();
                                    $("#subtask_detail_div").hide();
                                    $("#loadingImageDiv").show();
                                });

                        $.subscribe('after-next',
                                function(event, data) {
                                    com.mudrick.onPeopleTableLoad();
                                    $("#subtask_detail_div").show();
                                    $("#loadingImageDiv").hide();
                                    if (click_link_id !== null)
                                        $('#' + click_link_id).load(
                                                location.href + ' #' + click_link_id
                                                );
                                });

                        if (!com)
                            var com = {};
                        com.mudrick = {
                            onPeopleTableLoad: function() {
                                // Gets called when the data loads
                                $("#studentTable th.sortable").each(function() {
                                    $(this).click(function() {
                                        var link = $(this).find("a").attr("href");
                                        $("#subtask_detail_div").load(link, {},
                                                com.mudrick.onPeopleTableLoad);
                                        return false;
                                    });
                                });

                                $("#subtask_detail_div .pagelinks a").each(function() {
                                    $(this).click(function() {
                                        var link = $(this).attr("href");
                                        var rplink = link.replace("gennew=Y", "gennew=N");
                                        $("#subtask_detail_div").load(rplink, {},
                                                com.mudrick.onPeopleTableLoad);
                                        return false;
                                    });
                                });

                                $("#subtask_detail_div .pagelinks strong").each(function() {
                                    var htmlString = $(this).html();
                                    $(this).text("trang " + htmlString);
                                });
                            }
                        };

                    </script>
                </s:form>
            </table>
        </div>

        <div style="float: left; width: 100%;padding-top: 10px;"> 
            <center>
                <div id="loadingImageDiv" style="display: none;">
                    <img id="loadingImage" src='img/ajax-loader_1.gif' 
                         style="max-height: 80px; max-width: 80px;"
                         border='0' >
                </div>
            </center>
            <div id="subtask_detail_div"></div>

        </div>


        <s:hidden name="username" id="role_ID"  />                         
</div>    
    </body>
</html>

<script>
    function callDirectLink(fullname) {

        var ht = screen.availHeight / 5 + 60;
        var wt = screen.availWidth / 5 - 30;

        var xpos = screen.availHeight / 3 - 40;
        var ypos = screen.availWidth / 3;

        var v_report_dt = $('#report_dt_ID').val();
        var v_period = $('#period_ID').val();

        var username = document.getElementById('role_ID').value;
        var resize = window.open("eom_edit_sub_task?subtask=" + fullname + "&report_dt=" + v_report_dt
                + "&period=" + v_period
                + "&username=" + username
                + "&random=" + Math.random(),
                "IMS_REPORTS_FRM2", "height=" + ht + ",width=" + wt
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
        resize.moveTo(ypos, xpos);
        resize.focus();
    }
    
    
    $(document).ready(function () {
        //alert('refresh...');
        $('#automatic_reload_chk').click();
    });
</script>    