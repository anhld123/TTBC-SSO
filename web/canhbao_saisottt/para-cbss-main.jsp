<%-- 
    Document   : input_main
    Created on : Oct 26, 2015, 1:45:53 PM
    Author     : LION
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                /*height: 450px;*/
                /*padding-left: 5px;*/
                float: left;
                /*overflow: scroll;*/
            }
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: 12px;
            }

            .report_group_form{
                width: 100%;
            }

            #navParamUp{
                height: 65px;
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
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                $('#divExportReportLink').empty();
                var khoa_cbss = $("#khoa_cbss").val();
//                alert(khoa_cbss);

                $("#loadData")[0].click();
                bsubmit = true;
//                return true;
            }
            function onSaveData()
            {
                $('#message_suc_err').empty();
                $('#divExportReportLink').empty();
                var poscd = getposfromtreecheck();
//                alert(poscd);
                var khoa =  "P000_save";
//                alert(khoa);
                if (!bsubmit)
                {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !');
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !</h2>");
                    return;
                }
// alert(khoa);khoa
//                if (validateRequiredFields())
                $("#" + khoa)[0].click();

            }

            function onChot()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
//                var khoa_cdtt = $("#khoa_cbss").val();

//                var poscd = getposfromtreecheck();
//                alert(khoa_cdtt);


                $("#idMuasamts")[0].click();
                bsubmit = false;
            }

            function onMo()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
//                var khoa_cdtt = $("#khoa_cbss").val();

                var poscd = getposfromtreecheck();
//                alert(khoa_cdtt);


                $("#idMoMuasamts")[0].click();
                bsubmit = false;
            }

            function wait(ms) {
                var start = new Date().getTime();
                var end = start;
                while (end < start + ms) {
                    end = new Date().getTime();
                }
            }

            function onUpExcel()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();

                $("#idUpExcel")[0].click();
                bsubmit = true;
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
                var idform = 'id_' + '<s:property value="khoa_cbss"/>';
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

            function onSentData()
            {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                var khoa_ktgs = $("#khoa").val();

                var poscd = getposfromtreecheck();
//                alert(khoa_ktgs);

                $("#idSendCbss")[0].click();
                bsubmit = false;
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
        <div id="container" >
            <s:form id="id_%{khoa_cbss}" name="name_%{khoa_cbss}" action="%{khoa_cbss}" theme="simple">
                <s:hidden name="khoa_cbss" id="khoa"/>
                <div id="navParam" >     

                    <div id="navParam3">     
                        <table>
                            <tr style="height: 30px;">
                                <s:iterator value="lstCbssParams">
                                    <td ><s:property value="label"></s:property>:</td>
                                        <td >
                                        <s:if test="type.equalsIgnoreCase('T')">  
                                            <input type="text" value="" id="D_<s:property  value="%{fieldName}"/>" name="<s:property value="%{fieldName}"/>_TEXT" placeholder="<s:property value="label"/>"/>
                                        </s:if>                                              

                                        <!-- Tungnv Neu: la L thi gen List -->
                                        <s:if test="type.equalsIgnoreCase('L')">                                          
                                            <s:select  list="comboList" name="%{fieldName}_LIST" listKey="key" listValue="value" id="%{fieldName}"></s:select>                                                                                       
                                        </s:if>
                                        <!-- Tungnv: Neu la D thi gen Date -->
                                        <s:if test="type.equalsIgnoreCase('D')">  
                                            <sj:datepicker name="%{fieldName}_DATE" value="%{new java.util.Date()}"  id="%{fieldName}_DATE"
                                                           placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/>                                           
                                        </s:if>
                                    </td>
                                </s:iterator>     


                                <td >
                                    &nbsp;&nbsp;&nbsp;
                                    <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                               onBeforeTopics="beforediv_data"
                                               onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                    <input type="button" id="loadDatatmp" name="nameloadDatatmp"  onclick="onLoadData()" value="Tải dữ liệu"/>
                                </td>


                                <td >
                                    &nbsp;&nbsp;&nbsp;

                                    <s:if test="Grade.equalsIgnoreCase('1') || Grade.equalsIgnoreCase('2')">
                                        <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Tải file về"/>                                            
                                    </s:if> 
                                    <s:if test="Grade.equalsIgnoreCase('3') && (khoa_cbss.equalsIgnoreCase('LOAITRU_01') || khoa_cbss.equalsIgnoreCase('LOAITRU_3502'))">
                                        <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Tải file về"/>                                            
                                    </s:if>   
                                    &nbsp;&nbsp;&nbsp;                                                                   


                                </td>
                                <td>
                                    <s:if test="Grade.equalsIgnoreCase('2') && !khoa_cbss.equalsIgnoreCase('SMS_001')">                                       
                                        <s:url id="idSendDataCbss" action="sendDataCbss.action"></s:url>                                      
                                        <sj:submit id="idSendCbss" name="nameSend" href="%{idSendDataCbss}" value="Gửi dữ liệu" targets="divExportReport"
                                                   onBeforeTopics="beforediv_send"
                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                        <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu"/>
                                    </s:if> 
                                </td>

<!--                                <td>
                                    <s:if test="Grade.equalsIgnoreCase('1')">
                                        <s:url id="idChotMuasamts" action="ChotMuaSamTS.action"></s:url>                                      
                                        <sj:submit id="idMuasamts" name="nameSend" href="%{idChotMuasamts}" value="Chốt số liệu" targets="divExportReport"
                                                   onBeforeTidSendopics="beforediv_send"
                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                        <input type="button" id="idChottmp" name="nameidSendtmp"  onclick="onChot()" value="Chốt số liệu" />
                                    </s:if>
                                </td>

                                <td>
                                    <s:if test="Grade.equalsIgnoreCase('2') || Grade.equalsIgnoreCase('3')">
                                        <s:url id="idMoMuasamts" action="MoChotMuaSamTS.action"></s:url>                                      
                                        <sj:submit id="idMoMuasamts" name="nameSend" href="%{idMoMuasamts}" value="Mở duyệt" targets="divExportReport"
                                                   onBeforeTidSendopics="beforediv_send"
                                                   onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                        <input type="button" id="idChottmp" name="nameidSendtmp"  onclick="onMo()" value="Mở duyệt" />
                                    </s:if>
                                </td>-->


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

                <s:if test="(Grade.equalsIgnoreCase('1') && !khoa_cbss.equalsIgnoreCase('MUASAMTS_001'))">
                    <div id="containParm_full" align="center">
                        <div id="divExportReport"></div>
                        <div align="right"  id="divExportReportLink"></div>
                    </div>
                </s:if>
                <s:else>
<!--                    <div id="containTree">
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
                    <div id="containParm" align="center">
                        <div id="divExportReport"></div>
                        <div id="divExportReport"></div>
                    </div>-->
                        <div id="containParm_full" align="center">
                        <div id="divExportReport"></div>
                        <div align="right"  id="divExportReportLink"></div>
                    </div>
                </s:else>                     

            </s:form>
        </div>
        <script>
//            //CuongBM: 31Jul14
//            //Desc: Xu truong hop dat gia tri mac dich cho combox Quy (Quater), la quy hien tai
//            //      Cac bao cao Quy phai co id la PARA_QUY           
//            // TrungNT88 sua

            var date = new Date();
            var month = date.getMonth();
            var year = date.getFullYear(); //nam
            var day = getDaysOfMonth(month, year)
            
            var daynow = day + "/" + month + "/" + year;
            document.getElementById('ngay_bc_DATE').value = daynow;

        </script>
    </body>
</html>