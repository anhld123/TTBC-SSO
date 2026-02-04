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
        <script src="chamdiem_tapthe/js/chamdiem_canhan.js"></script>    
        <script src="js/sweetalert.min.js"></script>
        <script>
            function onTransData()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();

                var ngay_bc = $("#ngay_bc").val();


                var lv_day = parseInt(ngay_bc.substr(0, 2));
                var lv_month = parseInt(ngay_bc.substr(3, 2));
                var lv_year = parseInt(ngay_bc.substr(6, 4));
                if (lv_day !== getDaysOfMonth(lv_month, lv_year)) {
                    alert("Bạn cần chọn ngày cuối tháng để upload file");
                    return false;
                }

                $("#idTrans")[0].click();
            }
            ;

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
            }
            ;
        </script>
    </head>
    <body>
        <div id="main_screen_div" align="center" class="main_div">   
            <s:form id="loadAllBCTHANHHOA"  theme="simple">
                <s:url id="reloadData" action="loadAllBCTHANHHOA" includeParams="post"></s:url>
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
                            <sj:datepicker name="ngaybc_DATE" value="%{new java.util.Date()}"  id = "ngay_bc"
                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/>                        
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="normal_font">Báo cáo:</p>
                        </td>
                        <td>
                            <sj:select  
                                href="%{reloadData}" 
                                id="khoa_cdtt"
                                name="khoa_cdtt"
                                list="lstAllCdtt" 
                                onChangeTopics="reloadLoaibc"
                                listKey="sKey"
                                listValue="sDesc"
                                emptyOption="true" 
                                headerKey="-1"
                                headerValue="---Chọn nhóm báo cáo---"
                                cssStyle="font-weight: bold;vertical-align: middle;width: 500px;"
                                onBeforeTopics="BeforeHandler_loaibc" 
                                onCompleteTopics="myCompleteTopics1"></sj:select>
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

                            &nbsp;&nbsp;
                            <s:url id="idTransData" action="uploadCDCNTH.action"></s:url>                                      
                            <sj:submit id="idTrans" name="nameTrans" href="%{idTransData}" value="Upload dữ liệu" targets="upload_result_div"
                                       onBeforeTopics="before-next"
                                       onCompleteTopics="after-next" cssStyle="display:none"/>
                            <input type="button" id="idSendtmp" name="nameidTranstmp"  onclick="onTransData()" value="Upload dữ liệu"/>

                            <input style="color: red" type="button" id="idUpload" value="Upload excel new" onclick="callDirectLink('uploadfile.action');">

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
            function callDirectLink(link) {
                const curentYear = new Date().getFullYear();
                PopupCenter(link, 'Upload excel', 800, 400);

            }
            function PopupCenter(pageURL, title, w, h) {
                var left = (screen.width / 2) - (w / 2);
                var top = (screen.height / 2) - (h / 2);
                var targetWin = window.open(pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
                return targetWin;
            }
        </script>
    </body>

</html>