<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%--<sj:head jqueryui="true" loadAtOnce="true" jquerytheme="south-street" />--%>
        <sj:head jqueryui="true" jquerytheme="smoothness"/> 

        <style>
            .normal_font {
                font-family: Tahoma;
                font-size: 10pt;   
                color: blue;
            }
            .main_div {
                font-family: Tahoma;
                font-size: 10pt;   
            }
        </style>
        <script src="chamdiem_tapthe/js/chamdiem_tapthe.js"></script>    
        <script src="js/sweetalert.min.js"></script>
        <script>
            function onTransData()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                $("#idTrans")[0].click();
            }
        </script>
    </head>
    <body>
        <div id="main_screen_div" align="center" class="main_div">   
            <s:form id="UploadFile_Form" 
                    theme="simple"
                    enctype="multipart/form-data"
                    action="UploadFile.action">
                <input type="hidden" name="startrow" value="10" id="id_startrow">
                <input type="hidden" name="endcell" value="35" id="id_endcell">
                <p align="" style="line-height: 150%; margin-top: 30;margin-left: 20; margin-bottom: 20"><b>
                        <font size="4"> </font></b></p>
                <table border="1" cellspacing="0" style="border-collapse: collapse" 
                       bordercolor="#CCCCCC" width="60%" cellpadding="10" bgcolor="honeydew">        
                    <tr>
                        <td>
                            <p class="normal_font">Ngày báo cáo:</p>
                        </td>
                        <td>
                            <sj:datepicker name="ngaybc_DATE" value="%{new java.util.Date()}" 
                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/>                        
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="normal_font">File báo cáo:</p>
                        </td>
                        <td>
                            <s:file label="File báo cáo" name="fileUpload" size="65" theme="simple"/>                        
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" align="right">
                            <sj:submit value="Upload dữ liệu" 
                                       targets="upload_result_div" 
                                       onBeforeTopics="before-next"
                                       onCompleteTopics="after-next"
                                       theme="simple"/>
                            &nbsp;&nbsp;
                            <!--                            <s:url id="idTransData" action="TransKHNV_CDTT.action"></s:url>                                      
                            <sj:submit id="idTrans" name="nameTrans" href="%{idTransData}" value="Chuyển chính thức" targets="upload_result_div"
                                       onBeforeTopics="beforediv_send"
                                       onCompleteTopics="completediv_send" cssStyle="display:none"/>
                            <input type="button" id="idSendtmp" name="nameidTranstmp"  onclick="onTransData()" value="Chuyển chính thức"/>-->


                        </td>

                    </tr>
                </table>
            </s:form>
            <div id="loadingImageDiv" style="display: none;">
                <div>
                    <span style="font-family: Arial; font-size: 11pt;color: blue;">Đang xử lý file. Xin chờ ...</span>
                    <img id="loadingImage" src='img/loading-3.gif' border='0'
                         style="width:32px;height:32px;vertical-align:middle">                    
                </div>                
            </div>
            <div id="upload_result_div"/>
        </div>    

        <script>
            $.subscribe('before-next',
                    function (event, data) {
                        $("#upload_result_div").empty();
                        $("#upload_result_div").hide();
                        $("#loadingImageDiv").show();
                    });

            $.subscribe('after-next',
                    function (event, data) {
                        $("#upload_result_div").show();
                        $("#loadingImageDiv").hide();
                    });
        </script>
    </body>

</html>