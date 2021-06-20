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

<div id="main_screen_div" align="left">   
    <h3><u>Cập nhật cân đối sau kiểm toán:</u></h3>
            <s:form id="dtw_upload_form" 
                    theme="simple"
                    enctype="multipart/form-data"
                    action="Acc_Upload_File.action">                 
        <table border="1" cellspacing="0" style="border-collapse: collapse" 
               bordercolor="#CCCCCC" width="60%" cellpadding="10" bgcolor="#F8F8F8"
               class="main_form_STYLE"
               >                    
            <tr>
                <td>
                    File Excel:
                </td>
                <td>
                    <s:file label="File báo cáo" name="fileUpload" size="65" theme="simple"/>                        
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <sj:submit value="Upload" targets="upload_result_div" theme="simple"
                               onBeforeTopics="before-next"
                               onCompleteTopics="after-next"/>
                </td>
            </tr>                                                                   
        </table>                               
    </s:form>
    <div id="loadingImageDiv" style="display: none;">
        <img id="loadingImage" src='img/loading.gif' border='0' >
    </div>
    <div id="upload_result_div"/>    
    <script>
        $.subscribe('before-next',
                function(event, data) {
//                var pass_date = document.getElementById("selectedrptDate").value;
//                document.getElementById("pass_dt_ID").value = pass_date;    
                    $("#loadingImageDiv").show();
                    $("#upload_result_div").empty();
                    $("#upload_result_div").hide();
                });



        $.subscribe('after-next', function(event, data) {
            $("#loadingImageDiv").hide();
//            table_tag.loadpage.onTableLoad();
            $("#upload_result_div").show();
        });
        
    </script>
</div>    
