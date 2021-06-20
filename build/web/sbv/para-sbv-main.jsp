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
            #navParam2{
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
                var khoa_sbv = $("#khoa_sbv").val();
//                alert(khoa_sbv);     
                if (khoa_sbv == 'SBV_089_SGD' || khoa_sbv == 'SBV_162_TTGS' || khoa_sbv == 'SBV_165_TTGS'
                        || khoa_sbv == 'SBV_168_TTGS' || khoa_sbv == 'SBV_016N_TTGS' || khoa_sbv == 'SBV_043_CSTT')
                {
                    var poscd = getposfromtreecheck();
//                     alert(poscd); 
                    if (poscd.length >0 )
                    {
                        $('#message_suc_err').html("<h2 style='color: red'>Mẫu này nhập liệu toàn quốc, hãy bỏ tích cây chi nhánh ! </h2>");
//                        alert('Bạn phải chọn phòng giao dịch cần gửi số liệu !');
                        return;
                    }
                }

                if (<s:property value="Grade"/> == '2')
                {

                    var poscd = getposfromtreecheck();
//                alert(poscd);
                    if (poscd == null || poscd == '')
                    {
                        $('#message_suc_err').html("<h2 style='color: red'>Bạn phải chọn phòng giao dịch cần xem số liệu ! </h2>");
//                        alert('Bạn phải chọn phòng giao dịch cần gửi số liệu !');
                        return;
                    }
                }

                $("#loadData")[0].click();
                bsubmit = true;
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

            function onLoadDataTM()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_sbv = $("#khoa").val();
                var poscd = getposfromtreecheck();
//                alert(poscd);
                if (poscd.length > 7)
                {
                    alert('Khi bạn chọn nhiều pos, tải thuyết minh sẽ load dữ liệu pos đầu tiên trong ds bạn chọn')
//                    $('#message_suc_err').html("<h2 style='color: red'>Khi bạn chọn nhiều pos, tải thuyết minh sẽ load dữ liệu pos đầu tiên trong ds bạn chọn</h2>");
                }
                $("#idtmsbv1")[0].click();
                bsubmit = true;
            }

            function onSaveDataTM()
            {
                $('#message_suc_err').empty();
                var khoa = $("#khoa").val() + "_save";
                if (!bsubmit)
                {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !');
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !</h2>");
                    return;
                }
                var khoa_sbv = $("#khoa").val();
                $("#idtmsbvsave1")[0].click();
                bsubmit = false;
            }


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
                var idform = 'id_' + '<s:property value="khoa_sbv"/>';
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
                var khoa_sbv = $("#khoa").val();
                if (khoa_sbv != 'BCQT_M26A' && khoa_sbv != 'BCQT_M26B')
                {
                    if (!bsubmit)
                    {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !'); idSendLock
                        $('#divExportReport').html("<h2 style='color: red'>Bạn phải tải dữ liệu để xem mới gửi dữ liệu được !</h2>");
                        return;
                    }
                }
                var poscd = getposfromtreecheck();
//                alert(poscd);
                if (poscd == null || poscd == '')
                {
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn phòng giao dịch cần gửi số liệu ! </h2>");
//                    alert('Bạn phải chọn phòng giao dịch cần gửi số liệu !');
                    return;
                }
                $("#idSend")[0].click();
                bsubmit = false;
            }



            function onSentLockData()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();

                var khoa_sbv = $("#khoa").val();
                if (khoa_sbv != 'BCQT_M26A' && khoa_sbv != 'BCQT_M26B')
                {
                    if (!bsubmit)
                    {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !'); idSendLock
                        $('#divExportReport').html("<h2 style='color: red'>Bạn phải tải dữ liệu để xem mới gửi dữ liệu được !</h2>");
                        return;
                    }
                }
                var poscd = getposfromtreecheck();
//                alert(poscd);
                if (poscd == null || poscd == '')
                {
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn phòng giao dịch cần gửi số liệu ! </h2>");
//                    alert('Bạn phải chọn phòng giao dịch cần gửi số liệu !');
                    return;
                }
                var r = confirm("Khi chốt số liệu bạn sẽ không gửi dữ liệu về TW thêm lần nữa, Vậy bạn có thật sự muốn chốt số liệu không ? OK : Đồng ý, Cancel : Hủy bỏ");
                if (r != true) {
                    return;
                }
                $("#idSendLock")[0].click();
                bsubmit = false;
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
        </script>
    </head>

    <body>
        <div id="container" >
            <s:form id="id_%{khoa_sbv}" name="name_%{khoa_sbv}" action="%{khoa_sbv}" theme="simple">
                <s:hidden name="khoa_sbv" id="khoa"/>
                <div id="navParam" >
                    <div id="navParam2">     
                        <table>
                            <tr style="height: 30px;">
                                <s:iterator value="lstSbvParams">
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
                                            <sj:datepicker name="%{fieldName}_DATE" value="%{new java.util.Date()}" 
                                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/>

                                        </s:if>
                                    </td>

                                </s:iterator>     
                                <td>
                                    &nbsp;&nbsp;&nbsp;
                                    <s:if test="Grade.equalsIgnoreCase('3')">
                                        <s:if test="khoa_sbv.equalsIgnoreCase('SBV_089_SGD') || khoa_sbv.equalsIgnoreCase('SBV_162_TTGS') 
                                          || khoa_sbv.equalsIgnoreCase('SBV_165_TTGS') || khoa_sbv.equalsIgnoreCase('SBV_168_TTGS') 
                                          || khoa_sbv.equalsIgnoreCase('SBV_016N_TTGS') || khoa_sbv.equalsIgnoreCase('SBV_043_CSTT') || khoa_sbv.equalsIgnoreCase('SBV_170_TTGS')">

                                            <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                               onBeforeTopics="beforediv_data"
                                               onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                                <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                                                &nbsp;&nbsp;&nbsp;
                                                <s:if test="!Grade.equalsIgnoreCase('2')">
                                                    <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>
                                                </s:if>
                                        </s:if>
                                    </s:if>
                                                    <s:else>
                                                        <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                                                   onBeforeTopics="beforediv_data"
                                                                   onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                                        <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                                                        &nbsp;&nbsp;&nbsp;
                                                        <s:if test="!Grade.equalsIgnoreCase('2')">
                                                            <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>
                                                        </s:if>
                                                    </s:else>
                                    
                                    
                                    &nbsp;&nbsp;&nbsp;
                                    <s:if test="Grade.equalsIgnoreCase('2')">                                        
                                        
                                        <s:url id="idSendData" action="sendSBV.action"></s:url>                                      
                                        <sj:submit id="idSend" name="nameSend" href="%{idSendData}" value="Gửi dữ liệu" targets="divExportReport"
                                                   onBeforeTopics="beforediv_send"
                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                        <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu"/>
                                        &nbsp;&nbsp;&nbsp;
                                        <s:url id="idxacnhan" action="sendLockBCQT.action"></s:url>                                      
                                        <sj:submit id="idSendLock" name="nameSend" href="%{idxacnhan}" value="Gửi dữ liệu" targets="divExportReport"
                                                   onBeforeTopics="beforediv_send"
                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                        <s:if test="Grade.equalsIgnoreCase('4')">
                                            <input type="button" id="idSendLocktmp" name="nameidSendLocktmp"  onclick="onSentLockData()" value="Xác nhận số liệu"/>
                                        </s:if>

                                    </s:if>  

                                    <s:if test="Grade.equalsIgnoreCase('3')">
                                        <s:url id="idTMsbv" action="GET_THUYETMINH_SBV.action"></s:url>                                      
                                        <sj:submit id="idtmsbv1" name="nametmsbv" href="%{idTMsbv}" value="Tải thuyết minh" targets="divExportReport"
                                                   onBeforeTopics="beforediv_data"
                                                   onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                        <input type="button" id="idTmtmp" name="nameidTmtmp"  onclick="onLoadDataTM()" value="Tải thuyết minh"/> 
                                        &nbsp;&nbsp;&nbsp;
                                        <s:url id="idTMsbvSave" action="SAVE_THUYETMINH_SBV.action"></s:url>                                      
                                        <sj:submit id="idtmsbvsave1" name="nametmsbv" 
                                                   href="%{idTMsbvSave}" 
                                                   value="Lưu thuyết minh" 
                                                   formIds="id_sv_%{khoa_sbv}"
                                                   targets="message_suc_err"
                                                   onBeforeTopics="beforediv_data"
                                                   onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                        <input type="button" id="idTmtmpsave" name="nameidTmtmpsave"  onclick="onSaveDataTM()" value="Lưu thuyết minh"/> 
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
                    <div id="divExportReport"></div>
                </div>
            </div>
        </div>
    </body>
</html>