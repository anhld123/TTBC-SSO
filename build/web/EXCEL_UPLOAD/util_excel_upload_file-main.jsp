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
    <body>
        <div id="main_screen_div" align="center" class="main_div">   
            <s:form id="dtw_upload_form" 
                    theme="simple"
                    enctype="multipart/form-data"
                    action="dtw_upload_excel.action">
                <p align="" style="line-height: 150%; margin-top: 30;margin-left: 20; margin-bottom: 20"><b>
                        <font size="4"> </font></b></p>
                <table border="1" cellspacing="0" style="border-collapse: collapse" 
                       bordercolor="#CCCCCC" width="60%" cellpadding="10" bgcolor="honeydew">                    
                    <tr>
                        <td>
                            <p class="normal_font">File báo cáo:</p>
                        </td>
                        <td>
                            <s:file label="File báo cáo" name="fileUpload" size="65" theme="simple"/>                        
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="normal_font">Định dạng font:</p>
                        </td>
                        <td>
                            <p class="normal_font" >
                            <input type="radio" name="font_type" id="font_type_ID" value="TCVN"
                                   >
                            <font style="font-family: Tahoma;font-size: 10pt;color:blue;"> TCVN</font></input>                                                        
                            <input type="radio" name="font_type" id="font_type_ID" value="UTF8"     checked="true"                              >Unicode</input>
                            </p>
                        </td>
                    </tr>
                    <tr>                        
                        <td colspan="2" align="right"> 
                            <sj:submit value="Upload" 
                                       targets="upload_result_div" 
                                       onBeforeTopics="before-next"
                                       onCompleteTopics="after-next"
                                       theme="simple"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <span style="color: red; font-size: 8pt;">(*) Báo cáo Quyết toán kế hoạch khi upload Excel dữ liệu sẽ được gửi về TW</span>
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
                    function(event, data) {
                        $("#upload_result_div").empty();
                        $("#upload_result_div").hide();
                        $("#loadingImageDiv").show();
                    });

            $.subscribe('after-next',
                    function(event, data) {
                        $("#upload_result_div").show();
                        $("#loadingImageDiv").hide();
                    });
        </script>
    </body>

</html>