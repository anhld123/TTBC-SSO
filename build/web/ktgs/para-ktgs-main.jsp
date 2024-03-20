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

<%--<s:head/>
<sj:head/>--%>

<!DOCTYPE html>
<html>
    <head>
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
                width: 15%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 84%;
                height: 450px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
            }
            #containParm_full{
                width: 100%;
                height: 450px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
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
                padding-top: 0px;
                /*margin:5px;*/
                /*border-radius: 10px; //bo tron goc*/
                border: 1px solid;                
                /*                height: 50px;
                                border: 1px solid;  
                                border-radius: 10px; //bo tron goc
                                -moz-border-radius: 10px;
                                margin:5px;
                                padding:5px;*/
            }
            #navParam3{
                height: 35px;
                border: 0px solid;
                margin-left: 10px;
                font-weight: bold;
                border-left: 40px;
                float: left;
                padding-bottom: 0px;
                padding-top: 0px;
            }
            #message_suc_err
            {
                height: 30px;
                border: 0px solid;
                padding-bottom: 0px;
                padding-top: 0px;
            }
        </style>
        <script>
            var bsubmit = false;
            $(document).ready(function () {
                $(".NGAY_SL").css({"width": "80px"});
            });
            function onLoadData()
            {
//                var grade = $.session.get('reportGrade').toString();
//                $.session.get()
//                alert(grade);
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_ktgs = $("#khoa_ktgs").val();
//                alert(khoa_ktgs);

                $("#loadData")[0].click();
                bsubmit = true;
//                return true;
            }
            function fnResetVal()
            {

            }
            function onReLoadData1()
            {
//                var grade = $.session.get('reportGrade').toString();
//                $.session.get()
//                alert(grade);
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_ktgs = $("#khoa_ktgs").val() + "_reload";

                if (!bsubmit)
                {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !');
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới reset được dữ liệu !</h2>");
                    return;
                }
                //alert(khoa_ktgs);


                //$("#idreloadData")[0].click();
                $('#divExportReport').load(khoa_ktgs + '.action');
                bsubmit = false;
//                return true;
            }

            function onSaveData()
            {
                $('#message_suc_err').empty();
                var khoa = $("#khoa").val() + "_save";
                if (!bsubmit)
                {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !');
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !</h2>");
                    return;
                }
                if (validateRequiredFields())
                    $("#" + khoa)[0].click();
//                alert(khoa);
            }
            // TRUNG BO SUNG PHAN THUYET MINH

            $.subscribe("beforediv_data", function (event, data) {
                $("#loadingImageDiv_data").show();
            });
            $.subscribe("completediv_data", function (event, data) {
                $("#loadingImageDiv_data").hide();
            });
            $.subscribe("beforediv_ss", function (event, data) {
                $("#loadingImageDiv_data").show();
            });
            $.subscribe("completediv_ss", function (event, data) {
                $("#loadingImageDiv_data").hide();
            });
            $.subscribe("beforediv_send", function (event, data) {
                $("#loadingImageDiv_data").show();
            });
            $.subscribe("completediv_send", function (event, data) {
                $("#loadingImageDiv_data").hide();
            });
            //Disable enter key form submit
            function stopRKey(evt) {
                var evt = (evt) ? evt : ((event) ? event : null);
                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
                if ((evt.keyCode == 13) && (node.type == "text")) {
                    return false;
                }
            }

            //Disable enter key form submit            
            document.onkeypress = stopRKey;

            function getposfromtreecheck()
            {

                var pos_cd = '';
                var idform = 'id_' + '<s:property value="khoa_ktgs"/>';
                var element = document.forms[idform].elements;
//                 alert('bat dau goi submit idform='+idform);
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
                $('#divExportReport').empty();
                var khoa_ktgs = $("#khoa").val();

                var poscd = getposfromtreecheck();
//                alert(khoa_ktgs);
                if (khoa_ktgs != 'BC00230032' && khoa_ktgs != 'BC00230033' && khoa_ktgs != 'BC00230034')
                {
                    if (poscd == null || poscd == '')
                    {
                        $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn phòng giao dịch cần gửi số liệu ! </h2>");
                        //                    alert('Bạn phải chọn phòng giao dịch cần gửi số liệu !');
                        return;
                    }
                }

                $("#idSend")[0].click();
                bsubmit = false;
            }
            
            function onMo()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
//                var khoa_cdtt = $("#khoa_muasamts").val();

                var poscd = getposfromtreecheck();
//                alert(khoa_cdtt);

                
                $("#idMoKtgs")[0].click();
                bsubmit = false;
            }

            function onChot()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
//                var khoa_cdtt = $("#khoa_muasamts").val();

//                var poscd = getposfromtreecheck();
//                alert(khoa_cdtt);


                $("#idKtgs")[0].click();
                bsubmit = false;
            }

            function onReLoadData()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_ktgs = $("#khoa").val();

                var poscd = getposfromtreecheck();
//                alert(khoa_ktgs);
                if (!bsubmit)
                {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !');
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !</h2>");
                    return;
                }

                $("#idReLoad")[0].click();
//                bsubmit = false;
            }


            function validateRequiredFields() {
                var result = true; //Luu ket qua kiem tra kieu so co dung khong
                var arrCot = [".number", ".number2", ".number3"]; //Luu cac cot cua du lieu can tinh toan

                //Tinh toan tong cho ca 2 cot KH_UOC_TH, KH_KH_NAM
                for (k = 0; k < arrCot.length; k++) {
                    //Cac class nubmer2 phai nhap kieu so
                    $(arrCot[k]).each(function (index) {
                        if (!result)
                        {
                            return false;
                        }
                        var value = $(this).val();
                        value = value.replace(/,/g, "");
                        //value = '1.34.5';
                        if (isNaN(value)) {
                            result = false;
                            //Neu nguoi dung khong nhap dung kieu du lieu
                            //Dua ra canh bao
                            alert('Bạn nhập không đúng kiểu số xin nhập lại dữ liệu');
                            $("#message_suc_err").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn nhập không đúng kiểu số xin nhập lại dữ liệu!');
                            return false;
                        }
                        //Neu la kieu so --> Kiem tra xem kieu nhap co < 9999999999
                        if (parseFloat(value) > 999999999999) {
                            result = false;
                            //Dua ra canh bao
                            $("#message_suc_err").html('<span style="color:red"><h2><span style="font-weight: bold; color">Thông báo:</span>  Giá trị bạn nhập vượt quá giới hạn!</h2></span>');
                            alert('Giá trị bạn nhập vượt quá giới hạn!');
                            return false;
                        }
                        // }
                    });
                }
                return result;
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
        <div id="container" align="center">
            <s:form id="id_%{khoa_ktgs}" name="name_%{khoa_ktgs}" action="%{khoa_ktgs}" theme="simple">
                <s:hidden name="khoa_ktgs" id="khoa"/>
                <div id="navParam" >
                    <div id="navParam3">     
                        <table>
                            <tr style="height: 30px;">
                                <s:iterator value="lstKtgsParams">
                                    <td ><s:property value="label"></s:property>:</td>
                                        <td >
                                        <s:if test="type.equalsIgnoreCase('T')">                                     
                                            <%--<s:textfield  name="%{fieldName}_TEXT"></s:textfield>--%>
                                            <input type="text" value="" id="D_<s:property  value="%{#rowstatus.index}"/>" name="<s:property value="fieldName"/>_TEXT" placeholder="<s:property value="label"/>"/>
                                        </s:if>
                                        <!-- Tungnv Neu: la L thi gen List -->
                                        <s:if test="type.equalsIgnoreCase('L')">
                                            <s:select  list="comboList" name="%{fieldName}_LIST" listKey="key" listValue="value" id="%{fieldName}"></s:select>
                                        </s:if>
                                        <!-- Tungnv: Neu la D thi gen Date -->
                                        <s:if test="type.equalsIgnoreCase('D')">                                             
                                            <sj:datepicker name="%{fieldName}_DATE" value="%{new java.util.Date()}" id="%{fieldName}_DATE"
                                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/>

                                            <!--                                            <sj:datepicker name="%{fieldName}" id="%{fieldName}"
                                                           value="%{new java.util.Date()}" onblur="validatedate(this.value)" cssClass="NGAY_SL"
                                                           placeholder="DD/MM/YYYY" changeYear="true"  changeMonth="true" displayFormat="dd/mm/yy" 
                                                           cssStyle="font-weight: bold;vertical-align: middle;"/> -->
                                        </s:if>
                                    </td>

                                </s:iterator>     
                                <td >
                                    &nbsp;&nbsp;&nbsp;
                                    <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                               onBeforeTopics="beforediv_data"
                                               onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                    <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                                    &nbsp;&nbsp;&nbsp;
                                    <s:if test="khoa_ktgs.equalsIgnoreCase('BC00230032') || khoa_ktgs.equalsIgnoreCase('BC00230033') || khoa_ktgs.equalsIgnoreCase('BC00230034')">
                                        <s:if test="!khoa_ktgs.equalsIgnoreCase('BC00230034')">
                                            <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>
                                            <s:if test="Grade.equalsIgnoreCase('2')">                                       
                                                <s:url id="idSendData" action="sendKTGS.action"></s:url>                                      
                                                <sj:submit id="idSend" name="nameSend" href="%{idSendData}" value="Gửi dữ liệu" targets="divExportReport"
                                                           onBeforeTopics="beforediv_send"
                                                           onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu"/>
                                            </s:if>


                                        </s:if>  
                                        <s:else>  
                                            &nbsp;&nbsp;&nbsp;
                                            <s:if test="Grade.equalsIgnoreCase('2')">                                       
                                                <s:url id="idSendData" action="sendKTGS.action"></s:url>                                      
                                                <sj:submit id="idSend" name="nameSend" href="%{idSendData}" value="Gửi dữ liệu" targets="divExportReport"
                                                           onBeforeTopics="beforediv_send"
                                                           onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu"/>
                                                &nbsp;&nbsp;&nbsp;
                                                <s:url id="idReloadData" action="%{khoa_ktgs}_reload.action"></s:url>                                      
                                                <sj:submit id="idReLoad" name="nameSend" href="%{idReloadData}" value="Lấy DL tháng trước" targets="divExportReport"
                                                           onBeforeTopics="beforediv_send"
                                                           onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                <input type="button" id="idReLoadtmp" name="nameidReLoadtmp"  onclick="onReLoadData()" value="Lấy DL tháng trước" style="width:122px;height:25px;color: red;"/>

                                            </s:if>
                                            <s:else>
                                                <s:url id="idReloadData" action="%{khoa_ktgs}_reload.action"></s:url>                                      
                                                <sj:submit id="idReLoad" name="nameSend" href="%{idReloadData}" value="Lấy DL tháng trước" targets="divExportReport"
                                                           onBeforeTopics="beforediv_send"
                                                           onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                <input type="button" id="idReLoadtmp" name="nameidReLoadtmp"  onclick="onReLoadData()" value="Lấy DL tháng trước" style="width:122px;height:25px;color: red;"/>
                                            </s:else>    
                                        </s:else>      

                                    </s:if>
                                    <s:else>
                                        <s:if test="Grade.equalsIgnoreCase('1')">
                                            <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>

                                            <!--<input type="button" id="idreloadData" name="namereloadDatatmp"  onclick="onReLoadData()" value="Reset dữ liệu"/>-->


                                            <s:url id="idReloadData" action="%{khoa_ktgs}_reload.action"></s:url>                                      
                                            <sj:submit id="idReLoad" name="nameSend" href="%{idReloadData}" value="Reset dữ liệu" targets="divExportReport"
                                                       onBeforeTopics="beforediv_send"
                                                       onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                            <input type="button" id="idReLoadtmp" name="nameidReLoadtmp"  onclick="onReLoadData()" value="Reset dữ liệu" style="width:122px;height:25px;color: red;"/>



                                        </s:if>
                                        <s:else>
                                            <s:url id="idSendData" action="sendKTGS.action"></s:url>                                      
                                            <sj:submit id="idSend" name="nameSend" href="%{idSendData}" value="Gửi dữ liệu" targets="divExportReport"
                                                       onBeforeTopics="beforediv_send"
                                                       onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                            <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu"/>

                                        </s:else>
                                    </s:else>        




                                    &nbsp;&nbsp;&nbsp;                                                                        
                                </td>
                                <td>
                                    <s:if test="Grade.equalsIgnoreCase('1') && (khoa_ktgs.equalsIgnoreCase('BC00230033') || khoa_ktgs.equalsIgnoreCase('BC00230034'))">
                                        <s:url id="idChotKtgs" action="ChotKtgs.action"></s:url>                                      
                                        <sj:submit id="idKtgs" name="nameSend" href="%{idChotKtgs}" value="Chốt số liệu" targets="divExportReport"
                                                   onBeforeTidSendopics="beforediv_send"
                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                        <input type="button" id="idChottmp" name="nameidSendtmp"  onclick="onChot()" value="Chốt số liệu" />
                                    </s:if>
                                </td> 
                                
                                <td>
                                        <s:if test="Grade.equalsIgnoreCase('2') && (khoa_ktgs.equalsIgnoreCase('BC00230033') || khoa_ktgs.equalsIgnoreCase('BC00230034'))">
                                        <s:url id="idMoKtgs" action="MoChotKTGS.action"></s:url>                                      
                                        <sj:submit id="idMoKtgs" name="nameSend" href="%{idMoKtgs}" value="  Mở chốt  " targets="divExportReport"
                                                   onBeforeTidSendopics="beforediv_send"
                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                        <input type="button" id="idChottmp" name="nameidSendtmp"  onclick="onMo()" value="  Mở chốt  " />
                                    </s:if>
                                </td>
                                
                                <td>
                                    <div id="loadingImageDiv_data" style="margin-left: 20px;display: none;" >
                                        <img id="loadingImage" src='img/loading.gif' border='0' >
                                    </div>

                                </td>
                                <td>
                                    <div id="message_suc_err">
                                    </div>
                                </td>
                            </tr>            
                        </table>
                    </div>
                </div>
                <s:if test="!Grade.equalsIgnoreCase('1')">
                    <s:if test="Grade.equalsIgnoreCase('2') && khoa_ktgs.equalsIgnoreCase('BC00230032')
                          ">

                    </s:if>
                    <s:else>
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
                    </s:else>

                </s:if>
            </s:form>
            <s:if test="Grade.equalsIgnoreCase('1') || (Grade.equalsIgnoreCase('2') && khoa_ktgs.equalsIgnoreCase('BC00230032'))
                  ">
                <div id="containParm_full" align="center">
                </s:if>
                <s:else>
                    <div id="containParm" align="center">
                    </s:else>
                    <div id="divExportReport"></div>
                </div>
            </div>

            <script>
                //CuongBM: 31Jul14
                //Desc: Xu truong hop dat gia tri mac dich cho combox Quy (Quater), la quy hien tai
                //      Cac bao cao Quy phai co id la PARA_QUY           
                // TrungNT88 sua

                var date = new Date();
//                alert(date)

                var month = date.getMonth();
                var year = date.getFullYear(); //nam
                month = month == 0 ? 1: month;
                var day = getDaysOfMonth(month, year)

                var daynow = day + "/" + month  + "/" + year;
            
                document.getElementById('ngay_bc_DATE').value = daynow;
//                Gan quy mac dinh
//            $("#ngay_bc_DATE").val(day + "/" + month + "/" + year);



            </script>
    </body>
</html>