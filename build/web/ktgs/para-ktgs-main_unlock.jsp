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
                /*background: #FFA54E;*/
                padding-bottom: 0px;
                padding-top: 0px;
            }
        </style>
        <script>
            var bsubmit = false;
            $(document).ready(function () {
                $(".NGAY_SL").css({"width": "80px"});
                var khoa = $('#khoa_ktgs').val();
//                alert(khoa);
                if (khoa != 'ALL')
                {
                    $('#idtt_khoa').hide();
                    $('#lb_idtt_khoa').hide();
                }
            });
            function onLoadData()
            {
//                var grade = $.session.get('reportGrade').toString();
//                $.session.get()
//                alert(grade);
                $('#message_suc_err').empty();
                $('#divExportReport').empty();

//                if (<s:property value="Grade"/> == '2')
//                {
//                    var poscd = getposfromtreecheck();
////                alert(poscd);
//                    if (poscd == null || poscd == '')
//                    {
//                        $('#message_suc_err').html("<h2 style='color: red'>Bạn phải chọn phòng giao dịch cần xem số liệu ! </h2>");
////                        alert('Bạn phải chọn phòng giao dịch cần gửi số liệu !');
//                        return;
//                    }
//                }

                $("#loadOpenData")[0].click();
                bsubmit = true;
//                return true;
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
            function openClick()
            {
//                $('#divExportReport').empty();
                var khoa = $("#khoa").val() + "_open";

                if (!bsubmit)
                {
                    alert('Bạn phải tải dữ liệu và chọn PGD thì mới mở khóa được !');
//                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải tải dữ liệu và chọn PGD thì mới mở khóa được !</h2>");
                    return;
                }

                var idform = 'idform_open_' + '<s:property value="khoa_ktgs"/>';
                if ($('#' + idform + ' input:checkbox:checked').length > 0)
                {
                    $("#" + khoa)[0].click();
                }
                else
                {
                    // none is checked
                    alert("Bạn phải chọn phòng giao dịch cần mở khóa !");
//                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn phòng giao dịch cần mở khóa !</h2>");
                }

//                alert(khoa);
//                $('#divExportReport').empty();
            }
            function lockClick()
            {
                var khoa = $("#khoa").val() + "_lock";

                if (!bsubmit)
                {
                    alert('Bạn phải tải dữ liệu và chọn PGD thì mới mở khóa được !');
//                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải tải dữ liệu và chọn PGD thì mới mở khóa được !</h2>");
                    return;
                }

                var idform = 'idform_open_' + '<s:property value="khoa_ktgs"/>';
                if ($('#' + idform + ' input:checkbox:checked').length > 0)
                {
                    $("#" + khoa)[0].click();
                }
                else
                {
                    // none is checked
                    alert("Bạn phải chọn phòng giao dịch cần mở khóa !");
//                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn phòng giao dịch cần mở khóa !</h2>");
                }
            }
        </script>
    </head>

    <body>
        <div id="container" align="center">
            <s:form id="id_%{khoa_ktgs}" name="name_%{khoa_ktgs}" action="UNLOCK_KTGS" theme="simple">
                <s:hidden name="khoa_ktgs" id="khoa"/>
                <div id="navParam" >
                    <div id="navParam2">     
                        <table>
                            <tr style="height: 30px;">
                                <s:iterator value="lstKtgsParams">
                                    <td ><s:property value="label"></s:property>:</td>
                                        <td >
                                        <s:if test="type.equalsIgnoreCase('T')">                                     
                                            <s:textfield  name="%{fieldName}_TEXT"></s:textfield>
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
                                    <s:label name="tenhienthi" value="Chọn chi nhánh:"/>
                                    <s:select 
                                        id="idmacn"
                                        name="macn"
                                        list="lstParameters" 
                                        listKey="sKey"
                                        listValue="sDesc"           
                                        headerKey="ALL"
                                        headerValue="Toàn quốc" 
                                        cssStyle="font-weight: bold;vertical-align: middle;width: 200px;"
                                        onBeforeTopics="myBeforeHandler" 
                                        onCompleteTopics="myCompleteTopics">                    
                                    </s:select>
                                </td>
                                <td> 
                                    <%--<s:if test="isDisplayTM.equalsIgnoreCase('Y')">
                                        <s:label value="Chọn loại để gửi:" cssStyle="color: #029c44;" />
                                        <s:select id="idtype_ktgs" name="type_ktgs" list="#{'NT':'Nhập tay','TM':'Thuyết minh'}"
                                                  cssStyle="font-weight: bold;width: 100px; vertical-align: middle;"/>
                                    </s:if>--%>
                                    <%--<s:else>--%>
                                    <input type="hidden" id="idtype_ktgs" name="type_ktgs" value="NT"/>
                                    <%--</s:else>--%>
                                </td>
                                <td>
    <s:label id="lb_idtt_khoa" value="Chọn:" cssStyle="color: #029c44;" />
    <select id="idtt_khoa" name="tt_khoa" style="font-weight: bold; width: 150px; vertical-align: middle;">
        <option value="OK">Đã gửi dữ liệu</option>
        <option value="SEND">Đã xác nhận số liệu</option>
    </select>
</td>
                                <td>
                                    &nbsp;&nbsp;&nbsp;
                                    <sj:submit id="loadOpenData" name="loadOpenData" value="Tải dữ liệu" targets="divExportReport"
                                               onBeforeTopics="beforediv_data"
                                               onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                    <input type="button" id="loadOpentmp" name="nameloadOpentmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                                </td>
                                <td>
                                    <%--<sj:submit id="openloadData" name="openloadData" value="Mở khóa" targets="divExportReport"
                                             onBeforeTopics="beforediv_data"
                                             onCompleteTopics="completediv_data" onclick="openClick()" cssStyle="display:none"/>--%>
                                    <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="openClick()" value="Mở khóa"/>
                                    <!--<input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>-->
                                </td>
                                <td>
                                    <%--<sj:submit id="openloadData" name="openloadData" value="Mở khóa" targets="divExportReport"
                                             onBeforeTopics="beforediv_data"
                                             onCompleteTopics="completediv_data" onclick="openClick()" cssStyle="display:none"/>--%>
                                    <!--<input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="lockClick()" value="Khóa PGD"/>-->
                                    <!--<input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>-->
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
            </s:form>
            <div id="containParm_full" align="center">
                <div id="divExportReport"></div>
            </div>
        </div>
    </body>
</html>

