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
                var khoa_bcqt = $("#khoa_bcqt").val();
                //alert(khoa_bcqt);
                if ('<s:property value="Grade"/>' == '1' && (khoa_bcqt == 'BCQT_M26A' || khoa_bcqt == 'BCQT_M26B'))
                {
                    $('#message_suc_err').html("<h2 style='color: red'>Mẫu báo cáo này chỉ được phép gửi số liệu, không được nhập tay ! </h2>");
                    return;
                }

                if (<s:property value="Grade"/> == '2')
                {
                    if (khoa_bcqt == 'BCQT_M26A' || khoa_bcqt == 'BCQT_M26B')
                    {
                        $('#message_suc_err').html("<h2 style='color: red'>Mẫu báo cáo này chỉ được phép gửi số liệu, không xem được dữ liệu! </h2>");
                        return;
                    }
                    var poscd = getposfromtreecheck();
//                alert(poscd);
                    if ((poscd == null || poscd == '') && (khoa_bcqt != 'KHOANTC001' && khoa_bcqt != 'BCQT_26A' && khoa_bcqt != 'BCQT_26B'))
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
                var poscd = getposfromtreecheck();
                if (poscd == null ||  poscd == "")
                {
                    
                }
                else
                {
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn không được tích chọn PGD khi lưu dữ liệu cho chi nhánh !</h2>");
                    bsubmit = false;
                    return;
                }
                
                if (validateRequiredFields())
                    $("#" + khoa)[0].click();
//                alert(khoa);
            }
            // TRUNG BO SUNG PHAN THUYET MINH
            function onTMData() {
                $('#message_suc_err').empty();
                $("#idTMDatatmp")[0].click();
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
                
                
                var khoa_bcqt = $("#khoa_bcqt").val();
                if (khoa_bcqt=='KHOANTC001')
                     $("#loadData")[0].click();
                
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
                var idform = 'id_' + '<s:property value="khoa_bcqt"/>';
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
                var khoa_bcqt = $("#khoa").val();
                if (khoa_bcqt != 'BCQT_M26A' && khoa_bcqt != 'BCQT_M26B'&& khoa_bcqt != 'KHOANTC001')
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

                var khoa_bcqt = $("#khoa").val();
                if (khoa_bcqt != 'BCQT_M26A' && khoa_bcqt != 'BCQT_M26B')
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
            function onThuyetminh()
            {
                bsubmit = true;
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
        <div id="container" align="center">
            <s:form id="id_%{khoa_bcqt}" name="name_%{khoa_bcqt}" action="%{khoa_bcqt}" theme="simple">
                <s:hidden name="khoa_bcqt" id="khoa"/>
                <div id="navParam" >
                    <div id="navParam2">     
                        <table>
                            <tr style="height: 30px;">
                                <s:iterator value="lstBcqtParams">
                                    <td ><s:property value="label"></s:property>:</td>
                                        <td >
                                        <s:if test="type.equalsIgnoreCase('T')">                           
                                            <s:if test="Grade.equalsIgnoreCase('1') && fieldName.equalsIgnoreCase('so_ku')">
                                                <input type="text" value="" id="D_<s:property  value="%{#rowstatus.index}"/>" name="<s:property value="fieldName"/>_TEXT" placeholder="<s:property value="label"/>"/>
                                            </s:if>                                           
                                        </s:if>
                                        <!-- Tungnv Neu: la L thi gen List -->
                                        <s:if test="type.equalsIgnoreCase('L')">
                                            <s:select  list="comboList" name="%{fieldName}_LIST" listKey="key" listValue="value" id="%{fieldName}"></s:select>
                                        </s:if>
                                        <!-- Tungnv: Neu la D thi gen Date -->
                                        <s:if test="type.equalsIgnoreCase('D')">                                             
                                            <sj:datepicker name="%{fieldName}_DATE" value="%{new java.util.Date()}" 
                                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/>

                                            <!--                                            <sj:datepicker name="%{fieldName}" id="%{fieldName}"
                                                           value="%{new java.util.Date()}" onblur="validatedate(this.value)" cssClass="NGAY_SL"
                                                           placeholder="DD/MM/YYYY" changeYear="true"  changeMonth="true" displayFormat="dd/mm/yy" 
                                                           cssStyle="font-weight: bold;vertical-align: middle;"/> -->
                                        </s:if>
                                    </td>

                                </s:iterator>     
                                <td>
                                    &nbsp;&nbsp;&nbsp;
                                    <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                               onBeforeTopics="beforediv_data"
                                               onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                    <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu" style="background-color: #DCDCDC;"/>
                                    &nbsp;&nbsp;&nbsp;
                                    <s:if test="Grade.equalsIgnoreCase('1')">
                                        <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>
                                    </s:if>
                                    <s:elseif test="Grade.equalsIgnoreCase('2') && (khoa_bcqt.equalsIgnoreCase('KHOANTC001') || khoa_bcqt.equalsIgnoreCase('BCQT_26A') || khoa_bcqt.equalsIgnoreCase('BCQT_26B'))" >
                                        <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>
                                        &nbsp;&nbsp;&nbsp;
                                        <s:url id="idSendData" action="sendBCQT.action"></s:url>                                      
                                        <sj:submit id="idSend" name="nameSend" href="%{idSendData}" value="Gửi dữ liệu" targets="divExportReport"
                                                   onBeforeTopics="beforediv_send"
                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                        <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu"/>                                    
                                        <!--<input type="hidden" id="idtype_bcqt" name="type_bcqt" value="NT"/>-->
                                        <s:url id="idxacnhan" action="sendLockBCQT.action"></s:url>                                      
                                        <sj:submit id="idSendLock" name="nameSend" href="%{idxacnhan}" value="Gửi dữ liệu" targets="divExportReport"
                                                   onBeforeTopics="beforediv_send"
                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                        <input type="button" id="idSendLocktmp" name="nameidSendLocktmp"  onclick="onSentLockData()" value="Xác nhận số liệu"/>
                                        
                                        <s:if test="isDisplayTM.equalsIgnoreCase('Y')">
                                            <s:label value="Chọn loại để gửi:" cssStyle="color: #029c44;" />
                                            <s:select id="idtype_bcqt" name="type_bcqt" list="#{'NT':'Nhập tay','TM':'Thuyết minh'}"
                                                      cssStyle="font-weight: bold;width: 100px; vertical-align: middle;"/>
                                        </s:if>
                                        <s:else>
                                            <input type="hidden" id="idtype_bcqt" name="type_bcqt" value="NT"/>
                                        </s:else>
                                            
                                    </s:elseif>
                                    <s:else>
                                        
                                        <s:if test="isDisplayTM.equalsIgnoreCase('Y')">
                                            <s:label value="Chọn loại để gửi:" cssStyle="color: #029c44;" />
                                            <s:select id="idtype_bcqt" name="type_bcqt" list="#{'NT':'Nhập tay','TM':'Thuyết minh'}"
                                                      cssStyle="font-weight: bold;width: 100px; vertical-align: middle;"/>
                                        </s:if>
                                        <s:else>
                                            <input type="hidden" id="idtype_bcqt" name="type_bcqt" value="NT"/>
                                        </s:else>
                                        <s:url id="idSendData" action="sendBCQT.action"></s:url>                                      
                                        <sj:submit id="idSend" name="nameSend" href="%{idSendData}" value="Gửi dữ liệu" targets="divExportReport"
                                                   onBeforeTopics="beforediv_send"
                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                        <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu"/>

                                        <s:url id="idxacnhan" action="sendLockBCQT.action"></s:url>                                      
                                        <sj:submit id="idSendLock" name="nameSend" href="%{idxacnhan}" value="Gửi dữ liệu" targets="divExportReport"
                                                   onBeforeTopics="beforediv_send"
                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                        <input type="button" id="idSendLocktmp" name="nameidSendLocktmp"  onclick="onSentLockData()" value="Xác nhận số liệu"/>
                                    </s:else>
                                    &nbsp;&nbsp;&nbsp;
                                    <s:url id="idTMData" action="GET_BCQT_THUYETMINH.action"></s:url>                                      
                                    <sj:a id="idTMDatatmp" 
                                          name="nameTMDatatmp"  
                                          href="%{idTMData}"
                                          formIds="id_%{khoa_bcqt}"
                                          targets="divExportReport"
                                          onBeforeTopics="beforediv_data"
                                          onCompleteTopics="completediv_data"
                                          onclick="onThuyetminh();"
                                          ></sj:a>
                                    <s:if test="isDisplayTM.equalsIgnoreCase('Y')">
                                        <input type="button" id="idTMtmp" name="nameTMtmp"  onclick="onTMData()" value="Thuyết minh"/>      
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
    </body>
</html>