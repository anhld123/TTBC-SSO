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
            #tabledetail {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 150%;
            }

            #tabledetail td, #tabledetail th {
                border: 1px solid #ddd;
                padding: 0px;
                /*width: 30px;*/
            }

            #tabledetail tr:nth-child(even){background-color: #f2f2f2;}

            #tabledetail tr:hover {background-color: #ddd;}

            #tabledetail th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: center;
                background-color: #4CAF50;
                color: white;
            }
        </style>
        <script src="chamdiem_tapthe/js/chamdiem_canhan.js"></script>    
        <script src="js/sweetalert.min.js"></script>
        <script src="js/jquery.number.js"></script>
        <script>
            $(document).ready(function () {
                $('.number').css({"text-align": "right"});
                $('.number2').css({"text-align": "right"});                
                $('.number').number(true, 0);
                $('.number2').number(true, 2);
            });
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
        <link href="css/css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
        <link href="css/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="main_screen_div" align="center" class="main_div">   
            <s:form id="loadUploadDsNguoild" action="saveUploadDataCovid"  theme="simple">

                <input type="hidden" name="startrow" value="10" id="id_startrow">
                <input type="hidden" name="endcell" value="35" id="id_endcell">
                <s:hidden name="ngay_bc" id="ngay_bc"/>
                <s:hidden name="masothue" id="masothue"/>
                <s:hidden name="thangbc" id="thangbc"/>
                </br>
                <div id="divTitle">
                    DANH SÁCH NGƯỜI LAO ĐỘNG
                </div>
                <p align="" style="line-height: 100%; margin-top: 10;margin-left: 10; margin-bottom: 10"><b>
                        <font size="1"> </font></b></p>                                      
                <table cellspacing="0">        
                    <tr>
                        <td>
                            <p class="normal_font">File báo cáo:</p>
                        </td>
                        <td>
                            <s:file label="File báo cáo" name="fileUpload" size="65" theme="simple"/> 
                            <input type="button" id="idSendtmp" name="nameidTransGNtmp"  onclick="onTransData()" value="Upload dữ liệu"/>
                            <s:url id="idTransGNDataGN_QD23" action="saveUploadDsNguoiLD_QD23.action"></s:url>                                      
                            <sj:submit id="idTransGN" name="nameTrans" href="%{idTransGNDataGN_QD23}" value="Upload dữ liệu" targets="upload_result_div"
                                       onBeforeTopics="before-next"
                                       onCompleteTopics="after-next" cssStyle="display:none"/>
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
            <div style="width: 100%; height: 500px">
                <table border=1  id="editDelete" class="cls-table">
                    <tr>                    
                        <th rowspan="2" class="TD_CHECKBOX">TT</th>
                        <th rowspan="2" class="TD_THOIGIAN">Họ và tên người lao động</th>					
                        <th rowspan="2" class="TD_THOIGIAN">Mã số bảo hiểm xã hội</th>						
                        <th colspan="2">Thời gian ngừng việc (nếu có)</th>

                        <th rowspan="2" class="TD_THOIGIAN">Tổng số tiền lương phải trả </th>					
                        <th rowspan="2" class="TD_THOIGIAN">Số tiền đề nghị vay để trả lương (đồng)</th>	
                        <th rowspan="2" class="TD_THOIGIAN">Tháng vay</th>	

                    </tr>
                    <tr>
                        <th class="TD_SOKU">Từ ngày/tháng đến ngày/tháng</th>
                        <th class="TD_THOIGIAN">Tháng</th>                    
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                        <tr> 
                            <td align = "right" class="TD_CHECKBOX" >
                                <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_MAKH" >
                                <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D1" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN" >
                                <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN" >
                                <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class=" D0" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_SOKU" >
                                <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D4" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH number2 D0" onfocus="this.select();"
                                       readonly="true"/>                                                                        
                            </td>
                            <td align = "right" class="TD_SOKU" >
                                <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D5" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();"
                                       readonly="true"/>
                            </td>  
                            <td align = "right" class="TD_SOKU" >
                                <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D6" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number" onfocus="this.select();"
                                       readonly="true"/>
                            </td> 
                            <td align = "right" class="TD_THOIGIAN" >
                                <input type="text"  value="<s:property  value="D7" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH D0" onfocus="this.select();"
                                       readonly="true"/>
                            </td> 

                        </tr>                                                                                                                                                                                   
                    </s:iterator>
                </table>
            </div>

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
