<%-- 
    Document   : uploadfile
    Created on : Oct 26, 2015, 10:05:27 AM
    Author     : LION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<!DOCTYPE html>
<sj:head jqueryui="true" loadAtOnce="true"
         jquerytheme="south-street" />
<script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>
<style type="text/css">
    .errors {
        background-color:#FFCCCC;
        border:1px solid #CC0000;
        width:600px;
        /*height: 20px;*/
        margin-bottom:8px;
    }
    .errors li{ 
        list-style: none; 
    }
    .success {
        background-color:#DDFFDD;
        border:1px solid #009900;
        width:600px;
        /*height: 20px;*/
    }
    .success li{ 
        list-style: none; 
    }
    #container{
        width: 100%;
        height: 500px;
        border: 0px solid;
        padding-left: 0px;   
        height:auto;
        align:center;
        /*color: #FFE6B0 */
    }
    #containParm{
        width: 90%;
        height: 430px;
        padding-left: 5px;
        margin-top: 8px;
        /*border: 1px solid;*/
        display: block;
        margin-left: auto;
        margin-right: auto;     
        align:center;
        overflow: scroll;
        /*background: #FFCCCC;*/
    }
</style>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title>Upload file danh sách hộ nghèo</title>-->
        <script>
            function OnClear()
            {
                $("#Content_data").empty();
                $("#Content_data").text('');
            }
            $.subscribe('beforediv', function (event, data) {
                $("#Content_data").empty();
                $("#Content_data").hide();
                $("#loadingUploadFileDiv").show();
            });

            $.subscribe('completediv', function (event, data) {
                $("#loadingUploadFileDiv").hide();
                $("#Content_data").show();
            });


        </script>
    </head>
    <body topmargin="0" leftmargin="5">
        <br>
        <div id="container">
            <s:form  name="upload" id="uploadfilengheo" var="test" action="UploadFileHongheo" theme="simple" align="center">

                <s:file name="fileUpload" label="Chọn file" placeholder="fileUpload" onclick="OnClear()"></s:file>
                <sj:submit  id="uploadfile" name="uploadfile" value="Upload" targets="Content_data"  
                            onBeforeTopics="beforediv" onCompleteTopics="completediv" ></sj:submit>
                <div id="loadingUploadFileDiv" style="display: none;">
                    <h2 style='color: red'>Xin chờ đang tải dữ liệu ! </h2>
                    </br>
                    <img id="loadingImageUpFile" src='img/loading.gif' border='0' >
                </div>

                <div id="containParm" align="center">
                    <div id="Content_data">
                    </div>
                </div>
            </s:form>
        </div>

    </body>
</html>
