<%-- 
    Document   : input_main
    Created on : Oct 26, 2015, 1:45:53 PM
    Author     : LION
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <sj:head/>
        <script src="js/bctc_cic_tt200.js"></script>
        <style>
            #menuBcttv_para{
                width: 100%;
                height: 25px;                
                border: 1px solid; 
                padding-bottom: 0px;
                padding-top: 0px;
            }

            #containBcttv_para{
                width: 100%;
                min-height:390px;
                border: 1px solid;
                margin-top: 2px;
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

            #container{
                width: 100%;
                height: 500px;
                border: 0px solid;
                padding-left: 0px;        
                /*color: #FFE6B0*/
            }

            #containTree{
                width: 24%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow: scroll;
                /*background-color: #600;*/
            }


            #containParm{
                width: 75%;
                height: 440px;
                padding-left: 5px;
                border: 1px solid;
                float: right;
                text-align: center;
                position: relative;
                overflow: scroll;
                /*background-color: #4f4a41;*/ 

            }

            #containParm_full{
                width: 100%;
                height: 450px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
                /*background-color: #600;*/ 
            }
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: 12px;
            }

            .report_group_form{
                width: 100%;
            }

            #navParam{
                height: 35px;
                padding:0px;
                padding-bottom: 0px;
                padding-top: 5px;
                vertical-align: middle;
                border: 1px solid;                
                /*background-color: #FFB951;*/ 
            }
            #navParam2{
                /*height: 35px;*/
                border: 0px solid;
                /*margin-left: 10px;*/
                font-weight: bold;
                /*border-left: 40px;*/
                /*position: relative;*/
                float: left;
                padding-bottom: 0px;
                padding-top: 0px;
                background: #FBE3E4;
                /*background-color: #018c3b*/
            }
            #message_suc_err
            {
                height: 30px;
                border: 0px solid;
                padding-bottom: 0px;
                padding-top: 0px;
            }


            th{
                background-color: #DCDCDC;
                border-color: #999;
                height: 18px;
            }
            td{
                border-color: #999;
                height: 20px;
            }
            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }
            table.editDelete tr:focus{
                background-color:#FFE47A;
                /*cursor: pointer; hover*/
            }
            .highlight_row {
                background-color: #FFB951; 
                color:#000;
            }
            .DU_NO
            {
                width: 100%;
                border: 0px;
                color: #000000;
                border-color: #18ab29;
                background: #F9F9F9;
                color:#666666;
            }

        </style>
        <script src="js/webapi.js"></script>
        <script>
            $(document).ready(function () {


            <%
                String username = session.getAttribute("username").toString();
                String reportGrade = session.getAttribute("reportGrade").toString();
            %>
//                console.log('username= <%= username%> -> reportGrade=<%=reportGrade%>');
                if (<%=reportGrade%> == '1')
                {
//                        console.log('dung cap =2');
                    $('#containTree').hide();
                    $('#containParm').hide();

                }
                if (<%=reportGrade%> == '2')
                {
//                        console.log('dung cap =2');
//                        $('#containTree').hide();
                    $('#containParm_full').hide();

                }

            });
            
             $.subscribe('beforediv', function (event, data) {
                $("#table_data").empty();
                $("#table_data").hide();
                $("#loadingImageDiv").show();
            });
            

            $.subscribe('completediv', function (event, data) {
                $("#loadingImageDiv").hide();
                $("#table_data").show();
            });
            
            $.subscribe("beforediv_send", function (event, data) {
                $("#loadingImageDiv").show();
            });
            $.subscribe("completediv_send", function (event, data) {
                $("#loadingImageDiv").hide();
            });

            $.subscribe('beforedivsave', function (event, data) {
//                alert('abc');
                $("#para_api").empty();
                $("#para_api").hide();
                $("#loadingImageDivSave").show();
            });

            $.subscribe('completedivsave', function (event, data) {
//                alert('abc');
                $("#loadingImageDivSave").hide();
                $("#para_api").show();
            });
            var bflag=false;
            function loaddata()
            {
                bflag=true;
            }
            function submitLuudulieu()
            {
//                alert('vao submit');
                $("#para_api").empty();
                $("#para_api").text('');
                if(!bflag)
                {
                    alert('Bạn chưa tải dữ liệu nên không thể lưu dữ liệu');
                    return;
                }
                //Kiem tra chi tieu CD270 và CD440 phải bằng nhau
                var loai_module = $("#loaimodule").val();
                if(loai_module=='CIC001')
                {
                    var nambc = $("#idnambc").val();
                    var D5_cd270=getvalue('D5_CD270');
                    var D5_cd440=getvalue('D5_CD440');
                    
                    if(D5_cd270!=D5_cd440)
                    {
                        alert('Số liệu chỉ tiêu CD270 và chỉ tiêu CD440 năm báo cáo '+(nambc-1).toString()+' đang lệch ! CD270='+(D5_cd270).toString()+' CD440='+D5_cd440.toString());
                        return;
                    }
                    var D6_cd270=getvalue('D6_CD270');
                    var D6_cd440=getvalue('D6_CD440');
                    
                    if(D6_cd270!=D6_cd440)
                    {
                        alert('Số liệu chỉ tiêu CD270 và chỉ tiêu CD440 năm báo cáo '+(nambc).toString()+' đang lệch ! CD270='+(D6_cd270).toString()+' CD440='+D6_cd440.toString());
                        return;
                    }
                }
                $("#idluudulieu")[0].click();
            }
            
            function getposfromtreecheck()
            {

                var pos_cd = '';
                var loai_module = $("#loaimodule").val();
                var idform = 'id_' + loai_module;
                
                var element = document.forms['id_cic'].elements;
//                alert('bat dau goi submit idform='+element); 
                var i = element.length;
                for (var k = 0; k < i; k++)
                {
                    if (element[k].name == 'poscd')
                    {
                        if (element[k].checked == true)
                        {
                            if (element[k].value != '999999')
//                            alert(document.loadFormRisk.elements[k].value);
                                pos_cd = pos_cd + element[k].value + ',';
                        }
                    }
                }
//                alert('bat dau goi submit pos_cd='+pos_cd);
                return pos_cd;
            }
            
            function onSentData()
            {
                $('#message_suc_err').empty();
                $('#table_data').empty();
                

                var poscd = getposfromtreecheck();
//                alert(poscd);                
                
                $("#idSend")[0].click();
                bsubmit = false;
            }
            
            function onchange_ab()
            {
                try {

                    $("#table_data").empty();
                    $("#table_data").text('');
                    var loai_module = $("#loaimodule").val();
                    var ma_dn = $("#idma_dn").val();
                    var nambc = $("#idnambc").val();
//                    alert("thay doi gia tri loai_module=" + loai_module+" ma_dn= "+ma_dn);
                    if (loai_module == '-1' || ma_dn == '')
                    {
                        alert('Bạn phải chọn loại chỉ tiêu cần nhập !');
                        return;
                    } else
                    {
                        $("#table_data").load("/IMS_REPORTS/loadDataDoanhnghiepTT200.action?loai_module=" + loai_module + "&ma_dn=" + ma_dn + "&nambc=" + nambc);

                    }

                } catch (e) {
                    alert(e.toString());
                }


            }

           


        </script>
    </head>

    <body>
        <div id="container" >
            <s:form id="id_cic" name="name_cic_save" action="loadDataDoanhnghiepTT200.action" theme="simple">
                <div id="navParam" >
                    <table border="0">
                        <tr style="width: 100%">
                            <td style="width: 70%">
                                <div id="navParam2">
                                        Loại chỉ tiêu:
                                    <s:select
                                        id="loaimodule"
                                        name="loai_module"
                                        list="moduleList" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        emptyOption="true" 
                                        headerKey="-1"
                                        headerValue="---Chọn Module CIC---"
                                        cssStyle="font-weight: bold;vertical-align: middle;width: 200px;"
                                        onBeforeTopics="BeforeHandler_loaibc" 
                                        onCompleteTopics="myCompleteTopics1"></s:select> 
                                        <!--<div id="pgdtruyvan">-->
                                        <s:if test="!Grade.equalsIgnoreCase('3')">
                                            <label id="lbldv" > Đơn vị:
                                            <s:select
                                                id="idma_dn"
                                                name="ma_dn"
                                                list="doanhNghiepList" 
                                                listKey="sKey"
                                                listValue="sDesc"                              
                                                cssStyle="font-weight: bold;vertical-align: middle;width: 170px;"
                                                onBeforeTopics="BeforeHandler_loaibc" 
                                                onCompleteTopics="myCompleteTopics1"></s:select> 
                                            </label> 
                                        </s:if>
                                        Năm bc:
                                    <s:select
                                        id="idnambc"
                                        name="nambc"
                                        value="defaultNambc"
                                        list="nambcList" 
                                        listKey="sKey"
                                        listValue="sDesc"                              
                                        cssStyle="font-weight: bold;vertical-align: middle;width: 70px;"
                                        onBeforeTopics="BeforeHandler_loaibc" 
                                        onCompleteTopics="myCompleteTopics1"></s:select> 
                                        <!--</div>-->
                                        <!--<input type="button" id="idload" name="nameloadap"  onclick="onchange_ab()" value="Tải dữ liệu"/>-->
                                    <sj:submit id="idtruyvan" name="nametruyvan" value="Tải dữ liệu" targets="table_data"
                                               onBeforeTopics="beforediv" onCompleteTopics="completediv" onclick="loaddata()"/>
                                    <%--<sj:submit id="idluudulieu" name="savedata" value="Lưu dữ liệu" targets="para_api"--%> 
                                    <!--onBeforeTopics="beforediv" onCompleteTopics="completediv"/> submitLuudulieu-->
                                    <s:if test="Grade.equalsIgnoreCase('1')">
                                        <input type="button" id="idload" name="nameloadap"  onclick="submitLuudulieu()" value="Lưu dữ liệu"/>
                                    </s:if>
                                    <s:if test="Grade.equalsIgnoreCase('2')">                                       
                                            <s:url id="idSendData" action="sendCICTT200.action"></s:url>                                      
                                            <sj:submit id="idSend" name="nameSend" href="%{idSendData}" value="Gửi dữ liệu" targets="table_data"
                                                       onBeforeTopics="beforediv_send"
                                                       onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                            <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu"/>
                                    </s:if> 
                                          
                                    
                                </div>
                            </td>
                            <td style="width: 30%">
                                <div id="loadingImageDivSave" style="display: none;">
                                        <img id="loadingImage" src='img/loading.gif' border='0' >
                                    </div>
                                <div id="para_api">
                                    
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
                                                             
                    <s:if test="!Grade.equalsIgnoreCase('1')">
                    <div id="containTree">
                        <sjt:tree
                            name="poscd"
                            id="treeDynamicCheckboxes"
                            jstreetheme="apple"
                            rootNode="nodes_pos"
                            childCollectionProperty="children"
                            nodeTitleProperty="title"
                            nodeIdProperty="id"
                            openAllOnLoad="true"
                            checkbox="true"
                            showThemeDots="false"
                            showThemeIcons="true" 
                            />
                    </div>
                    </s:if>

                </s:form>               

                 <s:if test="Grade.equalsIgnoreCase('1')">
                <div id="containParm_full" align="center">
                </s:if>
                <s:else>
                    <div id="containParm" align="center">
                    </s:else>
                    <div id="loadingImageDiv" style="display: none;">
                            <h2 style='color: red'>Xin chờ đang tải dữ liệu!</h2>
                            </br>
                            <img id="loadingImage" src='img/loading.gif' border='0' >
                        </div>
                        <div id="table_data"></div>
                </div>
            </div>
            </div>
        </div>
    </div>
</body>
</html>