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
                overflow-x: scroll;
            }
            
            #containTreeQD23{
                width: 12%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow-x: scroll;
            }

            #containParm{
                width: 84%;
                height: 450px;
                padding-left: 5px;
                float: left;
                overflow-x: scroll;
            }
            
             #containParmQD23{
                width: 87%;
                height: 450px;
                padding-left: 5px;
                float: left;
                /*overflow-x: scroll;*/
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
//                var grade = $.session.get('reportGrade').toString();
//                $.session.get()
//                alert(grade);
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                $('#divExportReportLink').empty();
                var khoa_nhaptaycn = $("#khoa_nhaptaycn").val();
//                alert(khoa_nhaptaycn);

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
                var khoa = $("#khoa_nhaptaycn").val() + "_save";
                if (!bsubmit)
                {
//                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !');
                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !</h2>");
                    return;
                }
                
                if (validateRequiredFields())
                    $("#" + khoa)[0].click();  
                
                if(khoa == 'COVID_03_save')
                {                    
                    wait(2000);
                    onLoadData();
                }
            }
            
            function wait(ms){
                var start = new Date().getTime();
                var end = start;
                while(end < start + ms) {
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
                var idform = 'id_' + '<s:property value="khoa_nhaptaycn"/>';
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
                if(khoa_ktgs != 'PHIUT_001')
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
            };
            
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
            };
        </script>
    </head>

    <body>
        <div id="container" >
            <s:form id="id_%{khoa_nhaptaycn}" name="name_%{khoa_nhaptaycn}" action="%{khoa_nhaptaycn}" theme="simple">
                <s:hidden name="khoa_nhaptaycn" id="khoa"/>
                <s:if test="khoa_nhaptaycn.equalsIgnoreCase('HANOI_003')">
                    <div id="navParamUp" >                       
                </s:if>                 
                <s:else>
                    <div id="navParam" >     
                </s:else>         
                    <div id="navParam3">     
                        <table>
                            <tr style="height: 30px;">
                                <s:iterator value="lstNhaptaycnParams">
                                    <td ><s:property value="label"></s:property>:</td>
                                        <td >
                                        
                                            <s:if test="type.equalsIgnoreCase('T')">  
                                                <s:if test="khoa_nhaptaycn.equalsIgnoreCase('LOAITRU_3502')">
                                                    <input type="text" style="text-align:right;width: 100px" value="10" id="<s:property value="fieldName"/>" name="<s:property value="fieldName"/>" class="" placeholder="<s:property value="label"/>" readonly="readonly"/>
                                                </s:if>
                                                <s:else>
                                                    <input type="text" value="" id="D_<s:property  value="%{fieldName}"/>" name="<s:property value="%{fieldName}"/>_TEXT" placeholder="<s:property value="label"/>"/>
                                                </s:else>    
                                                                                                
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
<!--                                    <td>
                                            <s:if test="Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('CN23_01')">                                       
                                            <td>
                                               <p class="normal_font">File báo cáo:</p>
                                            </td> 
                                            <td>
                                               <s:file label="File báo cáo" name="fileUploadCN32" size="65" theme="simple"/>  
                                            </td>  

                                        </s:if>   
                                    </td>-->
                                    
                                    <td >
                                    &nbsp;&nbsp;&nbsp;
                                    <s:if test="((khoa_nhaptaycn.equalsIgnoreCase('NTMOI_001') || khoa_nhaptaycn.equalsIgnoreCase('HSSV_001')
                                          || khoa_nhaptaycn.equalsIgnoreCase('BDP_001')                                          
                                          || khoa_nhaptaycn.equalsIgnoreCase('QLDB_001')
                                          || khoa_nhaptaycn.equalsIgnoreCase('COVID_03')
                                          || khoa_nhaptaycn.equalsIgnoreCase('NHAPTAYCN_01')) && 
                                          Grade.equalsIgnoreCase('2')) or ( khoa_nhaptaycn.equalsIgnoreCase('CN23_01') && 
                                          Grade.equalsIgnoreCase('1'))"> 
                                        <!--<input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>-->                                           
                                    </s:if>                                       
                                    <s:else>
                                        <s:if test="!Grade.equalsIgnoreCase('3') ||(Grade.equalsIgnoreCase('3') && khoa_nhaptaycn.equalsIgnoreCase('LSTP_001'))||(Grade.equalsIgnoreCase('3') && khoa_nhaptaycn.equalsIgnoreCase('QD23_001'))">
                                           <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>                                            
                                        </s:if> 
                                        <s:if test="Grade.equalsIgnoreCase('3') && (khoa_nhaptaycn.equalsIgnoreCase('LOAITRU_01') || khoa_nhaptaycn.equalsIgnoreCase('LOAITRU_3502'))">
                                           <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Lưu dữ liệu"/>                                            
                                        </s:if>   
                                    </s:else>
                                    &nbsp;&nbsp;&nbsp;
                                       
                                        <s:if test="Grade.equalsIgnoreCase('2') && !khoa_nhaptaycn.equalsIgnoreCase('SMS_001')">                                       
                                            <s:url id="idSendData" action="sendPhiUT.action"></s:url>                                      
                                            <sj:submit id="idSend" name="nameSend" href="%{idSendData}" value="Gửi dữ liệu" targets="divExportReport"
                                                       onBeforeTopics="beforediv_send"
                                                       onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                            <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSentData()" value="Gửi dữ liệu"/>
                                        </s:if>    
                                        <s:if test="Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('COVID_GIAINGAN')">  
                                            <s:url id="idExpEcel" action="%{khoa_nhaptaycn}_ExpExcel.action"></s:url>                                      
                                                <sj:submit id="idExpEceltmp" name="nameSend" href="%{idExpEcel}" value="Xuất Excel" targets="divExportReport"
                                                           onBeforeTopics="beforediv_send"
                                                           onCompleteTopics="completediv_send" cssStyle="display:none"/>
                                                <input type="button" id="idReLoadtmp" name="nameidReLoadtmp"  onclick="ExpEcel()" value="Xuất Excel" style="width:122px;height:25px;color: red;"/>
                                            
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
                            <s:if test="khoa_nhaptaycn.equalsIgnoreCase('HANOI_003')">                               
                                <tr>
                                    <td>
                                        <p class="normal_font">File báo cáo:</p>
                                    </td>
                                    <td colspan="2">
                                        <s:file label="File báo cáo" name="fileUpload" size="45" theme="simple"/>                        
                                    </td>
                                    <td colspan="2" align="right">

                                        &nbsp;&nbsp;
                                        <s:url id="idUpData" action="uploadExcelKyQuy.action"></s:url>                                      
                                        <sj:submit id="idUpExcel" name="nameTrans" href="%{idUpData}" value="Upload dữ liệu" targets="divExportReport"
                                                   onBeforeTopics="before-next"
                                                   onCompleteTopics="after-next" cssStyle="display:none"/>
                                        <input type="button" id="idSendtmp" name="nameidTranstmp"  onclick="onUpExcel()" value="Upload dữ liệu"/>

                                    </td>
                                </tr>    
                            </s:if> 
                        </table>    
                    </div>
                </div>
                                    
                    <s:if test="(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('NTMOI_001')) ||
                                    (Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('USER_001'))||
                                    (Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('NHAPTAYCN_03'))
                                    ||(Grade.equalsIgnoreCase('3') && khoa_nhaptaycn.equalsIgnoreCase('LSTP_001'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('BDP_001'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('SMS_001'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('HANOI_001'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('HANOI_002'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('HANOI_003'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('CN25_KTNB'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('KYQUY_04'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('KYQUY_05'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('COVID_03'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('COVID_GIAINGAN'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('DGHC_01'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('COVID_04'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('VUNGKK_01'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('CN23_01'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('QD23_001'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('QD23_004'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('DIEUCHUYENTO_01'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('KSNB_01'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('QLDB_001'))
                                    ||(Grade.equalsIgnoreCase('1') && khoa_nhaptaycn.equalsIgnoreCase('QD23_004'))"
                                    >
                        <div id="containParm_full" align="center">
                            <div id="divExportReport"></div>
                            <div align="right"  id="divExportReportLink"></div>
                        </div>
                    </s:if>
                    <s:else>
                        <s:if test="khoa_nhaptaycn.equalsIgnoreCase('QD23_001')">
                            <div id="containTreeQD23">
                        </s:if>
                        <s:else>
                            <div id="containTree">
                        </s:else>
                        
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
                        <s:if test="khoa_nhaptaycn.equalsIgnoreCase('QD23_001')">
                            <div id="containParmQD23" align="center">
                                <div id="divExportReport"></div>
                                <div id="divExportReport"></div>
                            </div>
                        </s:if>
                        <s:else>
                             <div id="containParm" align="center">
                                <div id="divExportReport"></div>
                                <div id="divExportReport"></div>
                            </div>
                        </s:else>
                       
                    </s:else>                     
                
            </s:form>
            </div>
        <script>
            //CuongBM: 31Jul14
            //Desc: Xu truong hop dat gia tri mac dich cho combox Quy (Quater), la quy hien tai
            //      Cac bao cao Quy phai co id la PARA_QUY           
            // TrungNT88 sua
           
            var date = new Date(); 
            
                                   
            var month = date.getMonth();
            var year = date.getFullYear(); //nam
            var day = getDaysOfMonth(month,year)
            
            var daynow = day + "/" + month + "/" + year;
//            alert(daynow)
            document.getElementById('ngay_bc_DATE').value = daynow;
            //Gan quy mac dinh
//            $("#ngay_bc_DATE").val(day + "/" + month + "/" + year);
            
            
            
        </script>
    </body>
</html>