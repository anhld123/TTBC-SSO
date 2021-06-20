<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:head/>
<sj:head jqueryui="false" jquerytheme="simple"/>





<style type="text/css">
    .auto-style1 {
        margin-top: 0;
        margin-bottom: 0;
        font-family: Arial, Helvetica, sans-serif;
        padding-top: 0px;
    }
    .auto-style2 {
        text-align: right;
        padding-right: 10px;
    }
    .auto-style3 {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11pt;        
    }
    .auto-style4 {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11pt;
    }
    .auto-style5 {
        font-family: Arial, Helvetica, sans-serif;
        font-size: large;
    }

    .accTable td input {
        width: 100%;   
    }

    .number {
        text-align: right;
        background-color: #d6e9c6;
    }

    .number_EDIT {
        text-align: right;
        font-weight: bold;
        color: red;
        /*        background-color: #d6e9c6;*/
    }
</style>

<script src="js/jquery.number.js"></script>
<script src="js/format_num.js"></script>


<s:form id="balance_adjust_form1" theme="simple">                

    <table style="width: 100%" class="accTable">
        <tr bgcolor="#66FF99">
            <th style="height: 23px" class="auto-style3">Nợ/Có</th>
            <th style="height: 23px" class="auto-style3">Số dư hiện tại</th>
            <th style="height: 23px" class="auto-style3">Chênh lệch</th>
            <th style="height: 23px" class="auto-style3">Số sư sau đ/c</th>
        </tr>
        <tr>
            <td class="auto-style3">Dư đầu nợ</td>
            <td class="auto-style3"><s:textfield name="adjustAcc.ddno"
                         cssClass="number"
                         id="ID_DDNO_1"
                         readonly="true"></s:textfield></td>
            <td class="auto-style3"><s:textfield
                    cssClass="number_EDIT"
                    value="0"
                    id="ID_DDNO_2"
                    onblur="tinhtoan('ddno');"></s:textfield></td>
            <td class="auto-style3"><s:textfield name="adjustAcc.ddno_dc"
                         cssClass="number"
                         id="ID_DDNO_3"
                         readonly="true"></s:textfield></td>
            </tr>
            <tr>
                <td class="auto-style3">Dư đầu có</td>
                <td class="auto-style3"><s:textfield name="adjustAcc.ddco"
                         cssClass="number"
                         id="ID_DDCO_1"
                         readonly="true"></s:textfield></td>
            <td class="auto-style3"><s:textfield
                    cssClass="number_EDIT"
                    value="0"
                    id="ID_DDCO_2"
                    onblur="tinhtoan('ddco');"></s:textfield></td>
            <td class="auto-style3"><s:textfield name="adjustAcc.ddco_dc"
                         cssClass="number"
                         id="ID_DDCO_3"
                         readonly="true"></s:textfield></td>
            </tr>
            <tr>
                <td class="auto-style3">Phát sinh nợ</td>
                <td class="auto-style3"><s:textfield name="adjustAcc.psno"
                         cssClass="number"
                         id="ID_PSNO_1"
                         readonly="true"></s:textfield></td>
            <td class="auto-style3"><s:textfield
                    cssClass="number_EDIT"
                    value="0"
                    id="ID_PSNO_2"
                    onblur="tinhtoan('psno');"></s:textfield></td>
            <td class="auto-style3"><s:textfield name="adjustAcc.psno_dc"
                         cssClass="number"
                         id="ID_PSNO_3"
                         readonly="true"></s:textfield></td>
            </tr>
            <tr>
                <td class="auto-style3">Phát sinh có</td>
                <td class="auto-style3"><s:textfield name="adjustAcc.psco"
                         cssClass="number"
                         id="ID_PSCO_1"
                         readonly="true"></s:textfield></td>
            <td class="auto-style3"><s:textfield
                    cssClass="number_EDIT"
                    value="0"
                    id="ID_PSCO_2"
                    onblur="tinhtoan('psco');"></s:textfield></td>
            <td class="auto-style3"><s:textfield name="adjustAcc.psco_dc"
                         cssClass="number"
                         id="ID_PSCO_3"
                         readonly="true"></s:textfield></td>
            </tr>
            <tr>
                <td class="auto-style3">Dư cuối nợ</td>
                <td class="auto-style3"><s:textfield name="adjustAcc.dcno"
                         cssClass="number"
                         id="ID_DCNO_1"
                         readonly="true"></s:textfield></td>
            <td class="auto-style3"><s:textfield
                    cssClass="number_EDIT"
                    value="0"
                    id="ID_DCNO_2"
                    onblur="tinhtoan('dcno');"></s:textfield></td>
            <td class="auto-style3"><s:textfield name="adjustAcc.dcno_dc"
                         cssClass="number"
                         id="ID_DCNO_3"
                         readonly="true"></s:textfield></td>
            </tr>
            <tr>
                <td class="auto-style3">Dư cuối có</td>
                <td class="auto-style3"><s:textfield name="adjustAcc.dcco"
                         cssClass="number"
                         id="ID_DCCO_1"
                         readonly="true"></s:textfield></td>
            <td class="auto-style3"><s:textfield
                    cssClass="number_EDIT"
                    value="0"
                    id="ID_DCCO_2"
                    onblur="tinhtoan('dcco');"></s:textfield></td>
            <td class="auto-style3"><s:textfield name="adjustAcc.dcco_dc"
                         cssClass="number"
                         id="ID_DCCO_3"
                         readonly="true"></s:textfield></td>
            </tr>
        </table>
        <hr />
        <div class="auto-style2">       
            <span class="auto-style3">
            <s:url id="adjustDataUrlID" 
                   value="Acc_Adjust_Data.action">                                
            </s:url>
            <sj:a 
                id="adjustDataBtnID"
                href="%{adjustDataUrlID}"
                formIds="balance_adjust_form1"
                targets="messageDIV"           
                onBeforeTopics="clearData_1"
                onCompleteTopics="showData_1"            
                button="false">[3]Cập nhật </sj:a> 

                <script>                    
                    $.subscribe('clearData_1', function(event, data) {
                        $("#messageDIV").empty();
                        $("#messageDIV").hide();
                    });

                    $.subscribe('showData_1', function(event, data) {
                        $("#messageDIV").show(); //.slideDown('slow');
                    });
                </script>
            </span>
            <span class="auto-style4"><a href="javascript:closeWindow();">[4]Đóng</a></span>
        </div>



    <s:hidden name="adjustAcc.mapos"
              />
    <s:hidden name="adjustAcc.ngaybc"
              />
    <s:hidden name="adjustAcc.kybc"
              />
    <s:hidden name="adjustAcc.tonghop"
              />

    <s:hidden name="adjustAcc.tk"
              />
    
    <s:hidden name="userName"
              />
    <div id="messageDIV"></div>
</s:form>

<!--<script src="js/jquery.numberformatter-1.2.4.jsmin"></script>-->
<script language="javascript" type="text/javascript">
    function closeWindow() {
        
        if (navigator.userAgent.indexOf('Chrome') !== -1
                && parseFloat(navigator.userAgent.substring(
                navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
            window.open('', '_self', '');
            self.close();
            return false;
        } else {
            window.open('', '_parent', '');
            window.close();
        }
    }

    function tinhtoan(tentruong) {
//        alert(tentruong);
        switch (tentruong) {
            case "ddno":
                var giatri1 = parseFloat($("#ID_DDNO_1").val());
                var giatri2 = parseFloat($("#ID_DDNO_2").val());

                $("#ID_DDNO_3").val(giatri1 + giatri2);

                $('#ID_DDNO_3').number(true, 0);
                $('#ID_DDNO_2').number(true, 0);
                $('#ID_DDNO_1').number(true, 0);
                break;
            case "ddco":
                var giatri1 = parseFloat($("#ID_DDCO_1").val());
                var giatri2 = parseFloat($("#ID_DDCO_2").val());
                $("#ID_DDCO_3").val(giatri1 + giatri2);

                $('#ID_DDCO_3').number(true, 0);
                $('#ID_DDCO_2').number(true, 0);
                $('#ID_DDCO_1').number(true, 0);
                break;
            case "psno":
                var giatri1 = parseFloat($("#ID_PSNO_1").val());
                var giatri2 = parseFloat($("#ID_PSNO_2").val());
                $("#ID_PSNO_3").val(giatri1 + giatri2);

                $('#ID_PSNO_3').number(true, 0);
                $('#ID_PSNO_2').number(true, 0);
                $('#ID_PSNO_1').number(true, 0);
                break;
            case "psco":
                var giatri1 = parseFloat($("#ID_PSCO_1").val());
                var giatri2 = parseFloat($("#ID_PSCO_2").val());
                $("#ID_PSCO_3").val(giatri1 + giatri2);

                $('#ID_PSCO_3').number(true, 0);
                $('#ID_PSCO_2').number(true, 0);
                $('#ID_PSCO_1').number(true, 0);
                break;
            case "dcno":
                var giatri1 = parseFloat($("#ID_DCNO_1").val());
                var giatri2 = parseFloat($("#ID_DCNO_2").val());
                $("#ID_DCNO_3").val(giatri1 + giatri2);

                $('#ID_DCNO_3').number(true, 0);
                $('#ID_DCNO_2').number(true, 0);
                $('#ID_DCNO_1').number(true, 0);
                break;
            case "dcco":
                var giatri1 = parseFloat($("#ID_DCCO_1").val());
                var giatri2 = parseFloat($("#ID_DCCO_2").val());
                $("#ID_DCCO_3").val(giatri1 + giatri2);

                $('#ID_DCCO_3').number(true, 0);
                $('#ID_DCCO_2').number(true, 0);
                $('#ID_DCCO_1').number(true, 0);
                break;
        }
    }

    $(function() {
        $('.number').number(true, 0);
    });
</script>

