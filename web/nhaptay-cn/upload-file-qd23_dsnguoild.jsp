<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
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
            #divTitle{
                font: 14px Arial, Helvetica, sans-serif;
                font-weight: bold;
                color: #0077b3;
                text-align: center;
            }
        </style>
        <script src="chamdiem_tapthe/js/chamdiem_canhan.js"></script>    
        <script src="js/sweetalert.min.js"></script>
        <script>
            function onTransData()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                
//                var ngay_bc = $("#ngay_bc").val();
//                
//                
//                var lv_day = parseInt(ngay_bc.substr(0, 2));                
//                var lv_month = parseInt(ngay_bc.substr(3, 2));                
//                var lv_year = parseInt(ngay_bc.substr(6, 4));                   
//                if (lv_day !== getDaysOfMonth(lv_month, lv_year)) {
//                    alert("Bạn cần chọn ngày cuối tháng để upload file");                    
//                        return false;
//                }   
                
                $("#idTransGN")[0].click();
            };
            
            function getDaysOfMonth(month, year) {
        switch (month) {
            case 1:
                return 31;
            case 2:
                if (year % 4 === 0)
                    return 29;
                else
                    return 28;
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            case 12:
                return 31;
        }
    };
        </script>
    </head>
    <body>
        <div id="main_screen_div" align="center" class="main_div">   
            <s:form id="loadUploadDsNguoild" action="saveUploadDataCovid"  theme="simple">
               
                <input type="hidden" name="startrow" value="10" id="id_startrow">
                <input type="hidden" name="endcell" value="35" id="id_endcell">
                <s:hidden name="ngay_bc" id="ngay_bc"/>
                <s:hidden name="masothue" id="masothue"/>
                </br>
                <div id="divTitle">
                    DANH SÁCH NGƯỜI LAO ĐỘNG
                </div>
                <p align="" style="line-height: 100%; margin-top: 10;margin-left: 10; margin-bottom: 10"><b>
                        <font size="1"> </font></b></p>                                      
                <table border="1" cellspacing="0" style="border-collapse: collapse" 
                       bordercolor="#CCCCCC" width="80%" cellpadding="10" bgcolor="honeydew">        
<!--                    <tr>
                        <td>
                            <p class="normal_font">Lần giải ngân:</p>
                        </td>
                        <td>
                             <s:select id="langiangan" name="langiangan" list="#{'1':'Lần 1','2':'Lần 2', '3':'Lần 3'}"
                                                      cssStyle="font-weight: bold;width: 100px; vertical-align: middle;"/>
                        </td>
                    </tr>-->
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
                            
                            &nbsp;&nbsp;
                            <s:url id="idTransGNDataGN" action="saveUploadDsNguoiLD.action"></s:url>                                      
                            <sj:submit id="idTransGN" name="nameTrans" href="%{idTransGNDataGN}" value="Upload dữ liệu" targets="upload_result_div"
                                       onBeforeTopics="before-next"
                                       onCompleteTopics="after-next" cssStyle="display:none"/>
                            <input type="button" id="idSendtmp" name="nameidTransGNtmp"  onclick="onTransData()" value="Upload dữ liệu"/>


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