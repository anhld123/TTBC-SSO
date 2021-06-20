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
        <!--<script src="js/bctc_cic.js"></script>-->
        <script src="js/JavaScriptUtil.js"></script>
        <script src="js/InputMask.js"></script>
        <script src="js/Parsers.js"></script>
        <script src="js/Checkdate.js"></script>
        <script src="js/sweetalert.min.js"></script>
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

            /*            .metroButtonStyle {
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
                        }*/

            #container{
                width: 100%;
                height: 500px;
                border: 0px solid;
                padding-left: 0px;        
                /*color: #FFE6B0*/
            }

            #containTree{
                width: 15%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow: scroll;
                /*background-color: #600;*/
            }


            #containParm{
                width: 84%;
                height: 440px;
                padding-left: 5px;
                border: 0px solid;
                float: right;
                /*                text-align: center;
                                position: relative;*/
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
                height: 45px;
                padding:0px;
                padding-bottom: 0px;
                padding-top: 0px;
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
                /*                background: #FBE3E4;*/
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
        <link  rel="stylesheet" type="text/css" href="chamdiem_tapthe/css/cdtt.css"/>
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
                $("#loadingImageDivSave").show();
            });
            $.subscribe("completediv_send", function (event, data) {
                $("#loadingImageDivSave").hide();
            });
            $.subscribe("beforediv_ss", function (event, data) {
                $("#loadingImageDivSave").show();
            });
            $.subscribe("completediv_ss", function (event, data) {
                $("#loadingImageDivSave").hide();
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
            var bflag = false;
            function loaddata()
            {
                var pos = getposfromtreecheck();


//                var arrPos = pos.split(',');
//
//                alert('pos=' + pos + ' arrPos=' + arrPos.length);
//                if (arrPos.length > 1)
//                {
//                    alert('Ban chi duoc chon 1 pos de tai du lieu');
//                    return;
//                }
                bflag = true;
            }
            function submitLuudulieu()
            {
//                alert('vao submit');
                $("#para_api").empty();
                $("#para_api").text('');
                $('#message_suc_err').empty();
                if (!bflag)
                {
                    swal('Lỗi', 'Bạn chưa tải dữ liệu nên không thể lưu dữ liệu !', 'error');
//                    alert('Bạn chưa tải dữ liệu nên không thể lưu dữ liệu');
                    return;
                }
                //Kiem tra chi tieu CD270 và CD440 phải bằng nhau
                //var loai_module = $("#loaimodule").val();

                $("#idctlt_save")[0].click();
            }

            function getposfromtreecheck()
            {

                var pos_cd = '';

                var element = document.forms['id_chtrinhLoaitru'].elements;
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
                if (poscd == null || poscd.length < 6)
                {
                    swal('Lỗi', 'Bạn phảo chọn Phòng giao dịch để gửi dữ liệu !', 'error');
//                    alert('Bạn chưa tải dữ liệu nên không thể lưu dữ liệu');
                    return;
                }
                $("#idSend")[0].click();
                bsubmit = false;
            }
            
        </script>
    </head>

    <body>
        <div id="container" >
            <s:form id="id_chtrinhLoaitru" name="name_load_chtrinh" action="loadFormChtrinhLoaitru.action" theme="simple">
                <div id="navParam" >
                    <table border="0">
                        <tr style="width: 100%">
                            <td style="width: 70%">
                                <div id="navParam2">                                     
                                    <table>
                                        <tr>
                                            <td>
                                                <sj:datepicker name="ngay_bc_DATE" value="%{new java.util.Date()}"  id="ngay_bc_DATE"
                                                               placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/>
                                            </td>
                                            <td>
                                                <sj:submit id="idtruyvan" name="nametruyvan" value="Tải dữ liệu" targets="table_data"
                                                           onBeforeTopics="beforediv" onCompleteTopics="completediv" onclick="loaddata()" cssClass="metroButtonStyle"/>
                                            </td>
                                            <td>
                                                <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="submitLuudulieu()" value="Lưu dữ liệu" class="metroButtonStyle"/>


                                            </td>
                                            <td>
                                                <s:if test="Grade.equalsIgnoreCase('2')">                                       
                                                    <s:url id="idSendData" action="sendChuongtrinhLoaitru.action"></s:url>                                      
                                                    <sj:submit id="idSend" name="nameSend" href="%{idSendData}" value="Gửi dữ liệu" targets="table_data"
                                                               onBeforeTopics="beforediv_send"
                                                               onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                    <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu" class="metroButtonStyle"/>
                                                </s:if> 
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                            <td style="width: 30%">
                                <div id="loadingImageDivSave" style="display: none;">
                                    <img id="loadingImage" src='img/loading.gif' border='0' >
                                </div>
                                <div id="para_api">

                                </div>
                            </td>
                            <td>
                                <div id="message_suc_err">
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
                        <div align="left" style="padding-left: 30px">
                            <span style="color:red; font-size: 13px; font: bolder">Để cấu hình loại trừ các chương trình cho vay không có khả tăng trưởng dư nợ cho chỉ tiêu </span>
                            <s:if test="Grade.equalsIgnoreCase('3')">
                                <span style="color:blue; font-size: 13px; font: bold">"3. Tăng trưởng tín dụng nguồn TW quy định tại VB3936/NHCS-TCCB"</span> 
                                <span style="color:red; font-size: 14px; font: bold">Tại Trung ương Ban KHNV thực hiện cấu hình như sau:</span>
                                <br><span style="color: #18ab29; font-size: 14px">- Nếu không chọn chi nhánh để cấu hình. Sau đó, chọn "Tải dữ liệu" sẽ thực hiện cấu hình áp dụng loại bỏ cho TOÀN QUỐC</span>
                                <br><span style="color: #00c; font-size: 14px">- Nếu chọn chi nhánh để cấu hình loại trừ. Sau đó, chọn "Tải dữ liệu" sẽ thực hiện áp dụng cho CHI NHÁNH được chọn, chương trình đã được loại trừ TOÀN QUỐC sẽ không hiện khi chi nhánh có chương trình đó</span>
                            </s:if>
                            <s:else>
                                <span style="color:red; font-size: 13px; font: bold">Chi nhánh thực hiện cấu hình như sau:</span>
                                <br><span style="color:red; font-size: 12px">- Chọn PGD cần cấu hình loại trừ chương trình cho vay không có khả năng tăng trưởng dư nợ. Sau đó, chọn "Tải dữ liệu";</span>
                                <br><span style="color:red; font-size: 12px">- Đối với các chương trình cho vay không có khả năng tăng trưởng dư nợ, người dùng được phân quyền thực hiện tích chọn các tháng liên quan trong năm rồi chọn "Lưu dữ liệu";</span>
                                <br><span style="color:red; font-size: 12px">- Sau khi cấu hình xong các PGD liên quan, Phòng KHNVTD của Chi nhánh gửi dữ liệu về HSC. Thời gian gửi dữ liệu cấu hình trước 17h00 ngày cuối tháng.</span>
                            </s:else>
                        </div>

                        <div id="loadingImageDiv" style="display: none;">
                            <h2 style='color: red'>Xin chờ đang tải dữ liệu!</h2>
                            </br>
                            <img id="loadingImage" src='img/loading.gif' border='0' >
                        </div>
                        <div id="table_data"></div>
                    </div>
                </s:else>
            </div>
        </div>
    </div>
</div>
</body>
</html>