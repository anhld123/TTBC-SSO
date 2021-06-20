<%-- 
    Document   : input-branch-parameter
    Created on : Dec 26, 2018, 10:36:41 AM
    Author     : BAOANH
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%--<sj:head/>--%>
    <script>
        $(document).ready(function () {
            $('input.number').css({"text-align": "right"});
            $('input.number3').css({"text-align": "right"});
            $('input.number2').css({"text-align": "right"});
//                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
//                $('#ui-datepicker-div').css('clip', 'auto');
            //Cac truong bang so --> se co so truong = 0
            $('.number').number(true, 0);
            $('.number2').number(true, 2);
            $('.number3').number(true, 3);
            $("#idsaveDatatmp").hide();
        });
//            $('.TEN_KH').focus(function() {
//                $(this).closest('tr').addClass('highlight_row');
//            });
//            $('.TEN_KH').blur(function() {
//                $(this).closest('tr').removeClass('highlight_row');
//            });
        function onLaydulieu()
        {
            $("#idsaveDatatmp").hide();
//            alert('vao click');
            var khoa = $.trim($("#khoa_bc").val());
//            alert('da chon khoa '+khoa);
            $('#containBcttv').empty();
            $('#laydulieu_id')[0].click();
        }
        function onSaveData()
        {
            $('#id_savedlNhaptay')[0].click();
        }
        $.subscribe("beforediv", function (event, data) {
            $("#loadingImageDiv_data").show();
        });
        $.subscribe("completediv", function (event, data) {
            $("#loadingImageDiv_data").hide();
        });


        function onSentDataDLNhapTaySync()
        {
            $('#message_suc_err').empty();
            $('#containBcttv').empty();
            $("#idSend_DLNT")[0].click();
        }
        function onLockDataDLNhapTaySync()
        {
            $('#message_suc_err').empty();
            $('#containBcttv').empty();
            $("#idLock_DLNT")[0].click();
        }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div id="menuinputbranch">
            <s:form id="formnhaplieu" action="loadDulieuMau.action" theme="simple">
                <s:hidden name="khoa" id="khoa_bc"/>
                <div id="parameter_branch">
                    <table>
                        <tr style="height: 30px;">
                            <s:iterator value="lstParameterChomau">
                                <td >
                                    &nbsp;&nbsp;<s:property value="label"></s:property>:
                                    </td>
                                    <td >
                                    <s:if test="type.equalsIgnoreCase('T')">                            
                                        <input type="text" value="" id="D_<s:property  value="%{#rowstatus.index}"/>" name="<s:property value="fieldName"/>_TEXT" placeholder="<s:property value="label"/>" cssStyle="width: 120px;"/>
                                    </s:if>
                                    <s:if test="type.equalsIgnoreCase('N')">                            
                                        <input type="text" value="" id="D_<s:property  value="%{#rowstatus.index}"/>" name="<s:property value="fieldName"/>_NUMB" placeholder="<s:property value="label"/>" class="number2" cssStyle="width: 50px;"/>
                                    </s:if>
                                    <!-- Tungnv Neu: la L thi gen List -->
                                    <s:if test="type.equalsIgnoreCase('L')">
                                        <s:select  list="comboList" name="%{fieldName}_LIST" listKey="key" listValue="value" id="%{fieldName}" cssStyle="width: 160px;"></s:select>
                                    </s:if>
                                    <!-- Tungnv: Neu la D thi gen Date -->
                                    <s:if test="type.equalsIgnoreCase('D')">                                             
                                        <sj:datepicker name="%{fieldName}_DATE" value="%{new java.util.Date()}"  cssStyle="width: 85px;"
                                                       placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/>
                                    </s:if>
                                </td>
                            </s:iterator>     
                            <td>
                                &nbsp;&nbsp;&nbsp;
                                <sj:submit id="laydulieu_id" name="laydulieu_name" value="Tải dữ liệu" targets="containBcttv" 
                                           onBeforeTopics="beforediv"
                                           onCompleteTopics="completediv" formIds="formnhaplieu" cssStyle="display:none"/>
                                <input type="button" id="laydulieutmp_id" name="laydulieutmp_name"  onclick="onLaydulieu();" value="Tải dữ liệu" class="metroButtonStyle"/>
                                &nbsp;&nbsp;&nbsp;
                                <%--<s:if test="Grade.equalsIgnoreCase('1')">--%>
                                <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu" class="metroButtonStyle"/>
                                <%--</s:if>--%>
                                &nbsp;&nbsp;&nbsp;
                                <s:if test="!Grade.equalsIgnoreCase('1') && isDongbo_dl()"> 
                                    <s:url id="idSendDataDLNhaptay" action="sendDulieuNhapTaySync.action"></s:url>                                      
                                    <sj:submit id="idSend_DLNT" name="NameSend" href="%{idSendDataDLNhaptay}?Send=SEND" value="SEND" targets="containBcttv"
                                               onBeforeTopics="beforediv"
                                               onCompleteTopics="completediv" formIds="formnhaplieu" cssStyle="display:none"/>
                                    <input type="button" id="idSend_DLNTtmp" name="nameidSendtmp"  onclick="onSentDataDLNhapTaySync()" value="Gửi dữ liệu" class="metroButtonStyle"/>
                                    &nbsp;&nbsp;&nbsp;  

                                    <!--                                    <sj:submit id="idLock_DLNT" name="NameSend" href="%{idSendDataDLNhaptay}?Send=LOCK" value="LOCK" targets="containBcttv"
                                               onBeforeTopics="beforediv"
                                               onCompleteTopics="completediv" formIds="formnhaplieu" cssStyle="display:none"/>
                                    <input type="button" id="idLock_DLNTtmp" name="nameidSendtmp"  onclick="onLockDataDLNhapTaySync()" value="Chốt số liệu" class="metroButtonStyle"/>-->
                                </s:if>  
                            </td>
                            <!--                            <td>
                                                            <div id="loadingImageDiv_data" style="margin-left: 20px;display: none;" >
                                                                <img id="loadingImage" src='img/loading.gif' border='0' >
                                                            </div>
                            
                                                        </td>-->
                            <td>
                                <div id="message_suc_err">
                                </div>
                            </td>
                        </tr>            
                    </table>
                </div>
            </s:form>
        </div>

        <div id="containBcttv" >
        </div>
    </body>
</html>
