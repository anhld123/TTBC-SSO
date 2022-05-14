<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head/>

<style>
    .main_form_STYLE {
        font-family:  Arial;
        font-size: 11pt;
    }
</style>

<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/Checkdate.js"></script>



<script>
    function callDirectLink(link) {
        var ht = screen.availHeight / 5 + 35;
        var wt = screen.availWidth / 5 + 20;

        var resize = window.open(link
                + "random=" + Math.random(),
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
        resize.moveTo(wt, ht);
        resize.focus();
    }

</script>

<div id="main_screen_div" align="left">   
    <h3><u>Cập nhật thông tin cặp lá/Quỹ thiện tâm/Nối vòng tay thương</u></h3>
            <s:form id="dtw_upload_form" 
                    theme="simple"            
                    action="#">     
        <table style="border: 0pt;" >
            <tr>
                <td valign="top">
       <p style="font-family: Arial; font-size: 11pt; color: blue;">      
            Truy vấn thông tin:  

            <sj:datepicker name="query_dt" value=""                                     
                           onblur="validatedate(this.value)"
                           placeholder="DD/MM/YYYY" 
                           changeYear="true" 
                           changeMonth="true" 
                           displayFormat="dd/mm/yy"
                           id="selectedrptDate" 
                           size="8"/>
            <script>
                var lj_curDate = new Date();
                var lj_setDate = (lj_curDate.getDate()) + "/" +
                        (lj_curDate.getMonth() + 1) + "/" + lj_curDate.getFullYear();
                document.getElementById("selectedrptDate").value = lj_setDate;
            </script>
            </p> 
            </td>
            <td valign="middle" >
                <p style="font-family: Arial; font-size: 11pt; color: blue;padding-left: 20px;">      
            <s:url action="loveleaf_view_poor_infor.action" id="view_url_ID" />            
        <u> <sj:a id="loadPoor_ID" 
              formIds="dtw_upload_form" 
              targets="upload_result_div"                                                   
              onBeforeTopics="before-next"                                                         
              onCompleteTopics="after-next"
              href="%{view_url_ID}"
              cssClass="metroButtonStyle"
              button="false"> 
                <b>          
                    
                    [1]Truy vấn
                    
                </b>
            </sj:a>      </u> 

        &nbsp;&nbsp;
        <sj:a href="#" onclick="callDirectLink('loveleaf_open_upload?');" cssClass="metroButtonStyle">
            <b> <u>[2]Upload</u>  </b> </sj:a>

            &nbsp;&nbsp;
            <s:url action="loveleaf_money_move.action" id="money_move_url_ID" />            
        <sj:a id="money_move_ID"
            href="%{money_move_url_ID}" 
              formIds="dtw_upload_form" 
              targets="upload_result_div"                                                   
              onBeforeTopics="before-next"                                                         
              onCompleteTopics="after-next"              
              cssClass="metroButtonStyle"
              button="false">
            <b> <u>[3]Chuyển tiền</u>  </b> </sj:a>

            &nbsp;&nbsp;
        <sj:a href="#" onclick="callDirectLink('Menu_redirect.action?menuUrl=rptmanager_pure&menuId=99&');" 
              cssClass="metroButtonStyle">
            <b> <u>[4]Báo cáo</u>  </b> </sj:a>
            
            &nbsp;&nbsp;
            <s:url action="loveleaf_list_wrong_poor.action" id="list_wrong_url_ID" />            
        <u> <sj:a id="listWrongPoor_ID" 
              formIds="dtw_upload_form" 
              targets="upload_result_div"                                                   
              onBeforeTopics="before-next"                                                         
              onCompleteTopics="after-next"
              href="%{list_wrong_url_ID}"
              cssClass="metroButtonStyle"
              button="false"> 
                <b>          
                    
                    [5]Liệt kê DS sai
                    
                </b>
            </sj:a>      </u> 
        
        &nbsp;&nbsp;
         <s:url action="loveleaf_downloadFileUpload.action" id="downloadFileurl_ID" />            
        <u> <sj:a id="fileUpload_ID" 
              formIds="dtw_upload_form" 
              targets="upload_result_div"                                                   
              onBeforeTopics="before-next"                                                         
              onCompleteTopics="after-next"
              href="%{downloadFileurl_ID}"
              cssClass="metroButtonStyle"
              button="false"> 
                <b>          
                    
                    [6]Tải File upload
                    
                </b>
            </sj:a>      
            
        </u> 
        
        &nbsp;&nbsp;
         <s:url action="qtt_downloadFileUpload.action" id="downloadQttFileurl_ID" />            
        <u> <sj:a id="fileQTTUpload_ID" 
              formIds="dtw_upload_form" 
              targets="upload_result_div"                                                   
              onBeforeTopics="before-next"                                                         
              onCompleteTopics="after-next"
              href="%{downloadQttFileurl_ID}"
              cssClass="metroButtonStyle"
              button="false"> 
                <b>          
                    
                    [8]Tải File QTT upload
                    
                </b>
            </sj:a>      
        </u> 
        
        
        &nbsp;&nbsp;
         <s:url action="vvc_downloadFileUpload.action" id="downloadVVCFileurl_ID" />            
        <u> <sj:a id="fileVVCUpload_ID" 
              formIds="dtw_upload_form" 
              targets="upload_result_div"                                                   
              onBeforeTopics="before-next"                                                         
              onCompleteTopics="after-next"
              href="%{downloadVVCFileurl_ID}"
              cssClass="metroButtonStyle"
              button="false"> 
                <b>          
                    
                    [9]Tải File VVC upload
                    
                </b>
            </sj:a>      
        </u> 
        </p>  


    <s:hidden name="pass_dt_NAME" value="" id="pass_dt_ID"/>     
    </td>
</tr>
<td></td>
<td>
    <p style="font-family: Arial; font-size: 11pt; color: red; padding-left: 20px; padding-top: 5px;">
        <s:checkbox name="regenerate_FLG">[3]Tạo lại số liệu chuyển tiền</s:checkbox>
    </p>
    </td>
</table>
</s:form>
<hr/>
<center>
    <div id="loadingImageDiv" style="display: none;">
        <img id="loadingImage" src='img/ajax-loader_1.gif' 
             style="max-height: 80px; max-width: 80px;"
             border='0' >
    </div>
</center>
<div id="upload_result_div"/>    
<script>
    $.subscribe('before-next',
            function(event, data) {
                $("#upload_result_div").empty();
                $("#upload_result_div").hide();
                $("#loadingImageDiv").show();
            });



    $.subscribe('after-next', function(event, data) {
        com_1.loadpage.onTableLoad();
        $("#upload_result_div").show();
        $("#loadingImageDiv").hide();
    });

    if (!com_1)
        var com_1 = {};
    com_1.loadpage = {
        onTableLoad: function() {
            // Gets called when the data loads
            $("#search_table th.sortable").each(function() {
                $(this).click(function() {
                    var link = $(this).find("a").attr("href");
                    $("#upload_result_div").load(link, {},
                            com_1.loadpage.onTableLoad);
                    return false;
                });
            });

            $("#upload_result_div .pagelinks a").each(function() {
                $(this).click(function() {
                    var link = $(this).attr("href");
                    var rplink = link.replace("gennew=Y", "gennew=N");
                    $("#upload_result_div").load(rplink, {},
                            com_1.loadpage.onTableLoad);
                    return false;
                });
            });

//                                    $("#divListDonator .pagelinks strong").each(function () {
//                                        var htmlString = $(this).html();
//                                        $(this).text("trang " + htmlString);
//                                    });
        }
    };
</script>
</div>    
