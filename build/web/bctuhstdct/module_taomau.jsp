<%@taglib prefix="s" uri="/struts-tags" %>
<%--<%@taglib prefix="sx" uri="/struts-dojo-tags" %>--%>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<s:head/>
<sj:head/>

<script type="text/javascript">
    $(function() {
        $.subscribe("myBeforeHandler", function(event, data) {
            $("#loadingImageDiv").show();
        });
    });
    $(function() {
        $.subscribe("myCompleteTopics", function(event, data) {
            $("#loadingImageDiv").hide();
        });
    });


        $.subscribe("batdauload", function(event, data) {
//            alert('Bat dau hien thi');
            $("#divExportReport").empty();
            $("#divExportReport").show();
        });
        $.subscribe("ketthucload", function(event, data) {
//            alert('Ket thuc hien thi');
            $("#divExportReport").hide();
//             $("#divExportReport").show();
        });
</script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


    </head>
    <body>
        <!--<h3>Tạo mẫu cho báo cáo nhanh !</h3>-->
        <hr/>
        <div class="report_group_form" style="height:auto; margin: 0 auto; align:center;">
            <s:form  id="loadColumnId"  action="loadColumnReportFast" theme="simple" align="center">            
                <s:url var="remoteurl" action="createRptFast"></s:url>
                    <table align="center">
                        <tr>
                            <td width="150">Chọn module báo cáo:</td>
                            <td width="400">
                            <sj:select href="%{remoteurl}" 
                                       name="module_id"
                                       list="lstModule_id" 
                                       listKey="sKey"
                                       listValue="sDesc"
                                       emptyOption="true" 
                                       headerKey="-1"
                                       headerValue="---Chọn Module tạo số liệu---"
                                       onBeforeTopics="myBeforeHandler" 
                                       onCompleteTopics="myCompleteTopics"></sj:select>
                            </td>
                            <td>
                                <div id="loadingImageDiv" style="display: none;">
                                    <img id="loadingImage" src='img/loading.gif' border='0'>
                                </div>
                            </td>
                            <td style="float: right;">
                            <sj:submit  targets="divExportReport" id="btnSubmit" value="Tiếp Theo"  onBeforeTopics="batdauload"
                            onCompleteTopics="ketthucload" cssStyle="float: right;"></sj:submit>
                            </td>
                        </tr>
                    </table> 

            </s:form>

        </div>
        <hr/>   
<!--        <div id="ImageDiv" style="display: none;">
           <img id="loadingImage" src='img/loading.gif' border='0' >
        </div>-->
        
        <div id="divExportReport" style="display: none;"><img id="loadingImage" src='img/loading.gif' border='0' ></div>
    </body>
</html>
