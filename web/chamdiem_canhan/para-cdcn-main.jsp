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
        <script src="js/jquery.number.js"></script>
        <!--        <link  rel="stylesheet" type="text/css" href="chamdiem_tapthe/css/cdtt.css"/>-->
        <style>

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
                height: 45px;
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
                $(".hideColumn").hide();
                $('input.number2').css({"text-align": "right"});
                $('.number2').number(true, 2);
            });
            function onLoadData()
            {
//                var grade = $.session.get('reportGrade').toString();
//                $.session.get()
//                alert(grade);
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_cdtt = $("#khoa_cdtt").val();
//                alert(khoa_cdtt);

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
                var khoa_cdtt = $("#khoa_cdtt").val() + "_reload";

                if (!bsubmit)
                {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !');
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới reset được dữ liệu !</h2>");
                    return;
                }
                //alert(khoa_cdtt);


                //$("#idreloadData")[0].click();
                $('#divExportReport').load(khoa_cdtt + '.action');
                bsubmit = false;
//                return true;
            }

            function onSaveData()
            {
                $('#message_suc_err').empty();
                var khoa = $("#khoa").val() + "_save";
                var khoa_cdtt = $("#khoa_cdtt").val();
                var macb_old = $("#macb_old").val();
                if (khoa_cdtt == 'CDTT_CN01PL')
                {
                    var r = confirm("Bạn chắc chắn muốn gán các sổ cho cán bộ " + macb_old + " ? OK : Đồng ý, Cancel : Hủy bỏ");
                    if (r == false)
                        return false;
                }

                var poscd = getposfromtreecheck();
                if (!bsubmit)
                {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !');
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !</h2>");
                    return;
                }
                if (validateRequiredFields())
                    $("#" + khoa)[0].click();
                //onLoadData();
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
                var idform = 'id_' + '<s:property value="khoa_cdtt"/>';
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
                var khoa_cdtt = $("#khoa").val();

                var poscd = getposfromtreecheck();
//                alert(khoa_cdtt);
                if (khoa_cdtt != 'BC00230032' && khoa_cdtt != 'BC00230033' && khoa_cdtt != 'CDTT_CN')
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

            function onViewData()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_cdtt = $("#khoa").val();

                var poscd = getposfromtreecheck();
//                alert(khoa_cdtt);


                $("#idView")[0].click();
                bsubmit = false;
            }

            function onMokhoaData()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_cdtt = $("#khoa").val();

                var poscd = getposfromtreecheck();
//                alert(khoa_cdtt);


                $("#idmokhoa")[0].click();
                bsubmit = false;
            }

            function onChot()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_cdtt = $("#khoa").val();

                var poscd = getposfromtreecheck();
//                alert(khoa_cdtt);


                $("#idTV")[0].click();
                bsubmit = false;
            }

            function onHoiDongDuyet()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_cdtt = $("#khoa").val();

                var poscd = getposfromtreecheck();
//                alert(khoa_cdtt);


                $("#idHoiDongDuyetTV")[0].click();
                bsubmit = false;
            }

            function onReLoadData()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_cdtt = $("#khoa").val();

                var poscd = getposfromtreecheck();
//                alert(khoa_cdtt);
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
            <s:form id="id_%{khoa_cdtt}" name="name_%{khoa_cdtt}" action="%{khoa_cdtt}" theme="simple">
                <s:hidden name="khoa_cdtt" id="khoa"/>
                <s:hidden name="Grade" id="Grade"/>                
                <s:hidden name="RULEUSER"/>
                <s:hidden name="macb_old"/>
                <div id="navParam" >
                    <div id="navParam3">     
                        <table>
                            <tr style="height: 30px;">
                                <s:iterator value="lstCdttParams">
                                    <td class="<s:property  value="action"/>"> <label> <s:property value="label"></s:property></label></td>
                                    <td class="<s:property  value="action"/>">
                                        <s:if test="type.equalsIgnoreCase('T')">  
                                            <s:if test="khoa_cdtt.equalsIgnoreCase('CDTT_CN08TH')">
                                                <input type="text" style="text-align:right;width: 170px" value="1.00" id="<s:property value="fieldName"/>" name="<s:property value="fieldName"/>" class="" placeholder="<s:property value="label"/>"/>
                                            </s:if>
                                            <s:else>
                                                <input type="text" style="text-align:right;width: 170px" value="" id="<s:property value="fieldName"/>" name="<s:property value="fieldName"/>" class="" placeholder="<s:property value="label"/>"/>
                                            </s:else>    

                                        </s:if>
                                        <!-- Tungnv Neu: la L thi gen List -->
                                        <s:if test="type.equalsIgnoreCase('L')">
                                            <s:select  list="comboList" name="%{fieldName}" listKey="key" listValue="value" id="%{fieldName}"></s:select>
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
                                    <s:if test="RULEUSER.equalsIgnoreCase('9')">
                                        &nbsp;&nbsp;&nbsp;
                                        <sj:submit id="loadData" name="loadData" value="Xét duyệt" targets="divExportReport"
                                                   onBeforeTopics="beforediv_data"
                                                   onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                        <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Xét duyệt" class="metroButtonStyle"/>
                                        &nbsp;&nbsp;&nbsp;
                                        <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Phê duyệt" class="metroButtonStyle"/>                                            
                                        &nbsp;&nbsp;&nbsp;

                                        <!--                                        <s:url id="idViewStatus" action="viewStatusCDCN.action"></s:url>                                      
                                        <sj:submit id="idView" name="nameView" href="%{idViewStatus}" value="Kiểm soát" targets="divExportReport"
                                                   onBeforeTopics="beforediv_send"
                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                        <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onViewData()" value="Kiểm soát" class="metroButtonStyle"/>
                                            &nbsp;&nbsp;&nbsp;-->


                                        <s:if test="!Grade.equalsIgnoreCase('3') && khoa_cdtt.equalsIgnoreCase('CDTT_CN')">
                                            <s:url id="idSendData" action="sendCDTT.action"></s:url>                                      
                                            <sj:submit id="idSend" name="nameSend" href="%{idSendData}" value="Gửi dữ liệu" targets="divExportReport"
                                                       onBeforeTopics="beforediv_send"
                                                       onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                            <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu" class="metroButtonStyle"/>
                                            &nbsp;&nbsp;&nbsp;  
                                        </s:if>   

                                        <s:if test="khoa_cdtt.equalsIgnoreCase('CDTT_CN08TH')">
                                            <s:url id="idMokhoa" action="unlockCDTT_CN08TH.action"></s:url>                                      
                                            <sj:submit id="idmokhoa" name="namemokhoa" href="%{idMokhoa}" value="Mở duyệt" targets="divExportReport"
                                                       onBeforeTopics="beforediv_send"
                                                       onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                            <input type="button" id="idmkhoatmp" name="nameidmokhoatmp"  onclick="onMokhoaData()" value="Mở duyệt" class="metroButtonStyle"/>
                                            &nbsp;&nbsp;&nbsp;  
                                        </s:if>          
                                    </s:if>  

                                    <s:else>  
                                        &nbsp;&nbsp;&nbsp;
                                        <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                                   onBeforeTopics="beforediv_data"
                                                   onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                        <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu" class="metroButtonStyle"/>
                                        &nbsp;&nbsp;&nbsp;


                                        <s:if test="Grade.equalsIgnoreCase('1')">
                                            <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu" class="metroButtonStyle"/>                                            
                                            &nbsp;&nbsp;&nbsp;
                                            <s:if test="khoa_cdtt.equalsIgnoreCase('CDTT_PGD')">
                                                <s:url id="idTraVe" action="ChotCDTT.action"></s:url>                                      
                                                <sj:submit id="idTV" name="nameSend" href="%{idTraVe}" value="Phê duyệt" targets="divExportReport"
                                                           onBeforeTidSendopics="beforediv_send"
                                                           onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                <input type="button" id="idChottmp" name="nameidSendtmp"  onclick="onChot()" value="Phê duyệt" class="metroButtonStyle"/>
                                            </s:if>
                                        </s:if>
                                        <s:else>                                                     
                                            <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu" class="metroButtonStyle"/>                                            
                                            &nbsp;&nbsp;&nbsp;
                                        </s:else>                                        
                                    </s:else>                                                 
                                    &nbsp;&nbsp;&nbsp;                                                                        
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
                <s:if test="khoa_cdtt.equalsIgnoreCase('CDTT_CN08TH')">

                </s:if>
                <s:else>                        
                    <div id="containTree" align="left">
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

            </s:form>
            <s:if test="khoa_cdtt.equalsIgnoreCase('CDTT_CN08TH')">
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


                var month = date.getMonth();
                var year = date.getFullYear(); //nam
                if (month === 0)
                {
                    month = 12;
                    year = year - 1;
                }
//                var day = getDaysOfMonth(month, year)
//
//                var daynow = day + "/" + month + "/" + year;
//
//                document.getElementById("ngay_bc_DATE").value = '28/02/2023' //daynow;
                //Gan quy mac dinh
//            $("#ngay_bc_DATE").val(day + "/" + month + "/" + year);

                var day = new Date(year, month, 0).getDate(); // Get the last day of the specified month and year
                var daynow = day + "/" + month + "/" + year;

                document.getElementById("ngay_bc_DATE").value = daynow;

            </script>
    </body>
</html>