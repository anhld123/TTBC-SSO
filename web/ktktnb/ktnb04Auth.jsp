<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<sj:head jqueryui="false" jquerytheme="simple"/>
<s:head/>
<sj:head/>
<!DOCTYPE html>

<%
    session.setAttribute("startTreeGLKHTDRecursive", null);
%> 

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 04/KTNB</title>
        <sx:head/>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        
        <style type="text/css">
            *{
                font: 12px Arial, Helvetica, sans-serif;
            }
            table{
                border-style: solid;
                border-collapse: collapse;
                width: 100%;
                line-height: 19px;
            }
            .tbhead th{
                background-color: #5e5e55;
                font-weight: bold;
                color: #fff;
                text-align: center;
                padding: 5px;
            }
            .cscontent td{
                padding-left:5px;
            }
            .cscontent:hover{
                background-color: #ffff99;
            }
            
            .cscontent:hover input[type="text"]{
                background-color: #ffff99;
            }
            .tblmain tr td{
                font-weight: bold;
                color: #018c3b;
            }
            
            input{
                border: 0px;
            }
            
            .BOLD input[type="text"]
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }
            
            .ITALIC input[type="text"]
            {
                font-style: italic;
                font-size: 12px;
                width: 95%;
            }
            
            input[type="text"]
            {
                width: 95%;
            }
            
            input[type="button"]
            {
                margin-left: 3px;
            }
            
            .parameter{
                border: 1px solid black;
                width: 50%;
            }
            
            #posCD, #quyBc, #namBc, #maCn, #userId{
                width: 70px;
            }
            
            .tdtest {
                width: 20%;
            }
            
            .metroButtonStyle {
        font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
        display: block;
        color: rgb(255, 255, 255);
        text-decoration: none;
        text-align: center;
        width: 90px;
        height: 26px;
        padding: 5px;
        margin: 5px 0px 0px 5px;
        font-size: 12px;
        background: none repeat scroll 0 0 #808080;
        color: #FFF;
        border: 0px none;
        border-radius: 1px 1px 1px 1px;
        outline: 0px none;
    }
    .metroButtonStyle:hover {
        background: #018c3b;
    }
    .metroButtonStyle:active {
        background: #DCDCDC;
    }
        </style>
        
        <SCRIPT language="javascript">
           $(document).ready(function () {
            $("#ideditDelFormula").click(function () {
                $('#resultDiv').text('');
                document.getElementById("ideditDelFormulaSend").disabled = false;
//                alert('view');
            });
           
           $("#ideditDelFormulaSend").click(function () {
//                $('#resultDiv').text('');
                document.getElementById("ideditDelFormulaSend").disabled = true;
//                alert('send');
                return true;
            });
                //Format cac truong input.number can le phai
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                
                //CSS truong so thu tu (cot 1) cho width
//                $('#tableKtnb td:nth-child(1),#tableKtnb th:nth-child(1)').css({"width" : "35px"});
                
                //CSS truong so thu tu (cot 1) cho width
//                $('#tableKtnb td:nth-child(2),#tableKtnb th:nth-child(2)').css({"width" : "250px"});
                $(".KT_STT_HT").css({"width" : "35px"});
                $(".KT_LOAI_CT").css({"width" : "260px"});                
                
                //An di cac cot chuc nang
                $('.hideColumn').hide();
                
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true,0);  
                
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true,2);  
            });
            
            
            
            function clk_glkhtd() {
                $("#ideditDelFormulaSend").click(function () {
                    sleep(1000);
                });
                var lstPos = "";
                $('#treeView').jstree("get_checked", null, true).each(
                        function() {
                            lstPos = lstPos + this.id + ',';
                        });
//                alert(lstPos);
                document.getElementById("selectedPos").value = lstPos;
            }
            
            
            function sleep(milliSeconds){
                var startTime = new Date().getTime(); // get the current time
                while (new Date().getTime() < startTime + milliSeconds); // hog cpu
            }
            
            $.subscribe('before-next', function(event, data) {
                $("#contentDiv").empty();
                $("#contentDiv").hide();
        //        alert('abc');
                $("#loadingImageDiv").show();
        //        document.getElementById('loadingImage').innerHTML = "<img src='img/loading.gif' border='0'>";        
            });

            $.subscribe('after-next', function(event, data) {
        //        alert('xyz');
        //        $("#loadingImageDiv").empty();    
                $("#loadingImageDiv").hide();
                $("#contentDiv").show(); //.slideDown('slow');
            });
            
            //Xu ly tinh tong cho tung dong
            

            
           
            
            function validateRequiredFields(){
                var result = true; //Luu ket qua kiem tra kieu so co dung khong
                
                //Cac class number phai nhap kieu so
                $(".number").each(function(index){
                    //Kiem tra xem co nhap kieu so khong
                    if(isNaN(parseFloat($(this).val()))){
                        result = false; 
                        return false;
                    }
                });
                
                //Cac class nubmer2 phai nhap kieu so
                $(".number2").each(function(index){
                    //Kiem tra xem co nhap kieu so khong
                    if(isNaN(parseFloat($(this).val()))){
                        result = false;
                        return false;
                    }
                });
                
                $("#ideditDelFormula").click(function () {
                $('#resultDiv').text('');
                });
                
                if(result == false){
                    //Neu nguoi dung khong nhap dung kieu du lieu
                    //Dua ra canh bao
                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                }
                
                return result;
            }
        </SCRIPT>
    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdata" id="frmdata"  theme="simple">
            <table border="0" cellspacing="0" cellpading="0" height="11%" class="tblmain">
                <tr>
                    <td colspan="3" style="font-size: 14px;">04/KTNB: Báo cáo kết quả kiểm tra chứng từ kế toán<hr></td>                    
                </tr>
                <tr>
                    <td width="85%" >
                        <!--<b>Phòng giao dịch1: </b><input type="text" name="posCD" id="posCD" value="<s:property value="posCD"/>" readonly="readonly"/>-->
                        <b>Chi nhánh: </b><input type="text" name="maCn" id="maCn" value="<s:property value="maCn"/>" readonly="readonly"/>
                        <b>Quý báo cáo: </b><input type="text" name="quyBc" id="quyBc" value="<s:property value="quyBc"/>" readonly="readonly"/>
                        <b>Năm báo cáo: </b><input type="text" name="namBc" id="namBc" value="<s:property value="namBc"/>" readonly="readonly"/>
                        <b>Người dùng: </b><input type="text" name="userId" id="userId" value="<s:property value="userId"/>" readonly="readonly"/>
                    </td>
                    <td width = "15%">
                        <div id="resultDiv" style="color: red">                            
                        </div>
                    </td>
                    <td align="right">  

                        <s:url id="viewUrl" action="KTNB04_viewAction" />
                        <sj:submit id="ideditDelFormula" targets="contentDiv"                                 
                                   onclick="clk_glkhtd();"
                                   button="true"
                                   onBeforeTopics="before-next"
                                   onCompleteTopics="after-next"
                                   href="%{viewUrl}" indicator="loadingImage" 
                                   cssClass="metroButtonStyle" value="Xem"></sj:submit>

                        </td>

                        <td  align="right">     
                        <s:url id="viewUrlSend" action="KTNB04_viewActionAuth" />
                        <sj:submit id="ideditDelFormulaSend" 
                                   targets="resultDiv"  
                                   onclick="clk_glkhtd();"
                                   href="%{viewUrlSend}" indicator="loadingImage" 
                                   cssClass="metroButtonStyle" value="Duyệt, gửi BC"></sj:submit>                 
                        </td>
                     
                </tr>
                <tr height ="10px"
                    </tr>
            </table>
               <div  style="
                            float: left;
                            height:580px;
                            width: 20%;
                            z-index:1;
                            overflow:scroll;"
                            id="balancemainviewdiv">
                          <s:url var="echoO" action="buildPosTreeView"/>
                          <sjt:tree  
                              id="treeView"
                              jstreetheme="default"
                              rootNode="nodes"
                              nodeIdProperty="id"
                              nodeTitleProperty="name"
                              href="%{echoO}"
                              childCollectionProperty="children"
                              checkbox="true"
                              />        
                        </div>    
                        <s:hidden name="selectedPos" id="selectedPos" value=""/>
                        <%--<s:hidden name="printType" id="printType" value=""/>--%> 
                        <div  style="float: right;height:580px; width: 80%; z-index:2;overflow:scroll;" >          
                            <div id="loadingImageDiv" style="display: none;">
                                <img id="loadingImage" src='img/loading.gif' border='0' >
                            </div>
                            <div id="contentDiv"/>      
                        </div>
            </s:form>
        </div>
    </body>
</html>
