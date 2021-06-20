<%-- 
    Document   : process_risk
    Created on : Jun 18, 2014, 9:21:53 PM
    Author     : LION
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>
    <sj:head jqueryui="true" loadAtOnce="true"
             jquerytheme="south-street" />
    <!--bat du lieu cho datepicker de hien thi ngay trong form table_data_risk.jsp-->
    <script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>
    <script type="text/javascript" src="js/Checkdate.js"></script>
    <head>           
        <script>
            $.subscribe("myBeforeHandler", function (event, data) {
//            alert("I'm raised before the loading of the SelectBox ! Data is : " + data);
//                $('#loadingImageDiv').html("<h2 style='color: red'>Xin chờ để lấy danh sách tổ trưởng ! </h2>");
//                $("#loadingImageDiv").val("Xin chờ để lấy danh sách tổ trưởng ")
                $("#loadingImageDiv").show();
            });

            $.subscribe("myCompleteTopics", function (event, data) {
//                 alert("I'm raised before the loading of the SelectBox ! Data is : myCompleteTopics" );
                $("#loadingImageDiv").hide();
            });

            $(document).ready(function () {
                $(".NGAY_SL").css({"width": "80px"});
                 $("#date_sl").css("display", "none");
            });

            //list tat ca cac pos khi check tren treeview
            function getposfromtreecheck()
            {
                var pos_cd = '';
                var i = document.formMainSendPLN.elements.length;
                for (var k = 0; k < i; k++)
                {
                    if (document.formMainSendPLN.elements[k].name == 'poscd')
                    {
                        if (document.formMainSendPLN.elements[k].checked == true)
                        {
                            if (document.formMainSendPLN.elements[k].value != '999999')
//                            alert(document.loadFormRisk.elements[k].value);
                                pos_cd = pos_cd + document.formMainSend.elements[k].value + ',';
                        }
                    }
                }
                return pos_cd;
            }

            //Kiem tra khi chon to chuc hoi xem da chon xa chua
            function onReloadGroup()
            {
                $('#divExportReport').empty();

                var dvut_dcpt = $("#dvut_dcpt").val();
//                alert('poscd=' + poscd + " dvut_dcpt=" + dvut_dcpt);
                if (dvut_dcpt == null || dvut_dcpt == '-1')
                {
//                    alert('Bạn phải chọn đơn vị ủy thác cần list tổ trưởng');
                    $('#dvut_dcpt').val("-1");
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn đơn vị ủy thác cần liệt kê danh sách tổ trưởng ! </h2>");
                    return;
                }
                var poscd = getposfromtreecheck();
                if ((poscd == null || poscd == '') && dvut_dcpt != '1')
                {
                    $('#dvut_dcpt').val("-1");
//                    $(document).ready(function () {
//                        $("#dvut_dcpt").prop("selectedIndex", 1);
//                    });
//                    alert('Bạn phải chọn xã cần list tổ trưởng');
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn xã cần liệt kê danh sách tổ trưởng ! </h2>");
                    return;
                }
            }

            var bsubmit = false;
            $.subscribe('beforediv1', function (event, data) {
                $("#divExportReport").empty();
                $("#divExportReport").hide();
                $("#loadingImageDiv").show();
            });

            $.subscribe('completediv1', function (event, data) {
                $("#loadingImageDiv").hide();
                $("#divExportReport").show();
                //$("#contentDiv").slideDown('slow');
            });


            function onclear()
            {
                $("#idsearch_soku").val('');
                bsubmit = true;
                bSearchStatus = false;
            }

            var bSearchStatus = false;
            function onFindStatus()
            {
                bSearchStatus = true;
                bsubmit = false;
            }
            function submitloadData()
            {
                $("#idSendDcPtNo")[0].click();
            }
            function submitloadDataLock()
            {
                alert('Giá trị bạn nhập vượt quá giới hạn!');
                $("#idLockSend")[0].click();
            }
            
            
            function validateRequiredFields() {
                var result = true; //Luu ket qua kiem tra kieu so co dung khong

                //Cac class nubmer2 phai nhap kieu so
                $(".number2").each(function (index) {
                    var value = $(this).val();
                    value = value.replace(/,/g, "");
                    //Kiem tra xem co nhap kieu so khong
//                    if (isNaN(parseFloat(value))) {
//                        result = false;
//                        //Neu nguoi dung khong nhap dung kieu du lieu
//                        //Dua ra canh bao
//                        $("#divExportReport").html('<span style="color:red"><h2><span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!</h2></span>');
//                        alert('Bạn chưa nhập đầy đủ dữ liệu!');
//                        return false;
//                    }
//                    else {
                    //Neu la kieu so --> Kiem tra xem kieu nhap co > 0 
                    if (parseFloat(value) < 0) {
                        result = false;

                        //Dua ra canh bao
                        $("#divExportReport").html('<span style="color:red"><h2><span style="font-weight: bold; color">Thông báo:</span>  Bạn không được nhập giá trị < 0!</h2></span>');
                        alert('Bạn không được nhập giá trị < 0!');
                        return false;
                    }

                    //Neu la kieu so --> Kiem tra xem kieu nhap co < 9999999999
                    if (parseFloat(value) > 9999999999) {
                        result = false;

                        //Dua ra canh bao
                        $("#divExportReport").html('<span style="color:red"><h2><span style="font-weight: bold; color">Thông báo:</span>  Giá trị bạn nhập vượt quá giới hạn!</h2></span>');
                        alert('Giá trị bạn nhập vượt quá giới hạn!');
                        return false;
                    }
                    // }
                });
                return result;
            }
            function onReturn()
            {
                $("#container").empty();
                $("#container").text('');
            }

            function keyPressEvent() {
                var evt = window.event;
//                alert('vao han nay');
                var keyPressed = evt.which || evt.keyCode;
                if (keyPressed == 13) {
                    document.getElementById('idSearch').click();
//                    $('#idSearch').click();
                    evt.cancel = true;
                }
            }
            function stopRKey(evt) {
                var evt = (evt) ? evt : ((event) ? event : null);
                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
                if ((evt.keyCode == 13) && (node.type == "text")) {
                    return false;
                }
            }

            //Disable enter key form submit            
            document.onkeypress = stopRKey;

        </script>

        <style>
            body,td,th,font{ font-family:Tahoma; font-size:12px; }

            #container{
                width: 100%;
                height: 500px;
                border: 0px solid;
                padding-left: 0px;                
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

            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: 12px;
            }

            .report_group_form{
                width: 100%;
            }

            #navParam{
                height: 45px;
                padding:3px;

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
                height: 60px;
                border: 0px solid;
                margin-left: 10px;
                font-weight: bold;
            }
            #Input{
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                padding: 3px 0px 3px 3px;
                margin: 5px 1px 3px 0px;
                border: 1px solid rgba(81, 203, 238, 1);

            }
            #divSearch
            {
                height: 22px;
                /*border: 1px solid;*/     
                /*width: 40%;*/
                float: left;
                padding:1px; 
                border: 1px solid;
                position: fixed;
            }
            input[type="text"]
            {
                width: 100%;
                /*border: 0px;*/
                /*color: #000000*/
                border-color: #18ab29;
                background: #F9F9F9;
                color:#666666;
            }
            input[type=text]:focus, textarea:focus {
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
/*                padding: 3px 0px 3px 3px;
                margin: 5px 1px 3px 0px;*/
                border: 1px solid rgba(81, 203, 238, 1);
            }
            .datepicker{
            }
        </style>

    </head>
    <body topmargin="0" leftmargin="5">        
        <div id="container" >
            <s:form id="formMainSendPLN"  name="formMainSendPLN" var="test" action="loadDataViewSendPLN.action" theme="simple">
                <!--<fieldset>-->
                    <div id="navParam" >
                        <div id="navParam2">                        
                            <!--</p>-->
                            <s:url id="reloadData" action="reloadDvutSendPln" includeParams="post"></s:url>
                            <s:label value="Ngày SL:" cssStyle="color: #029c44;" />
                            <sj:datepicker name="ngay_dcpt" id="ngay_dcpt"
                                           value="%{new java.util.Date()}" onblur="validatedate(this.value)" cssClass="NGAY_SL"
                                           placeholder="DD/MM/YYYY" changeYear="true"  changeMonth="true" displayFormat="dd/mm/yy" 
                                           cssStyle="font-weight: bold;vertical-align: middle;"/>                             
                            <s:label value="Tổ chức hội:" cssStyle="color: #029c44;" />

                            <sj:select href="%{reloadData}" 
                                       onChangeTopics="reloadTotruong"                                                     
                                       onchange="onReloadGroup()"
                                       id="dvut_dcpt" 
                                       name="dvut_dcpt"
                                       list="lstDvutDcpt" 
                                       listKey="sKey"
                                       listValue="sDesc"           
                                       headerKey="-1"
                                       headerValue="-- Chọn --" 
                                       cssStyle="font-weight: bold;vertical-align: middle;width: 100px;"
                                       onBeforeTopics="myBeforeHandler_dvut" 
                                       onCompleteTopics="myCompleteTopics__dvut">                    
                            </sj:select>
                            <!--&nbsp;-->
                            <s:label value="Tổ trưởng:" cssStyle="color: #029c44;" />
                            <sj:select href="%{reloadData}" 
                                       reloadTopics="reloadTotruong"
                                       id="totruong_dcpt" 
                                       name="totruong_dcpt"
                                       list="lstTotruongDcpt" 
                                       listKey="sKey"
                                       listValue="sDesc" 
                                       headerKey="-1"
                                       headerValue="-- Chọn --" 
                                       cssStyle="font-weight: bold;vertical-align: middle;width: 130px;"
                                       onBeforeTopics="myBeforeHandler" 
                                       onCompleteTopics="myCompleteTopics" >                    
                            </sj:select>

                           &nbsp;
                                    <s:label value="Trạng thái " cssStyle="color: #029c44;" />
                                    <s:select id="trangthai" name="trangthai" list="#{'0':'Chưa gửi','1':'Đã gửi'}"
                                              cssStyle="width: 103px; vertical-align: middle;"/>
                                    &nbsp;

                            <sj:submit id="loadsubmitform" name="loadsubmitform" value="Tải dữ liệu" targets="divExportReport" onclick="onclear()"
                                       onBeforeTopics="beforediv1"
                                       onCompleteTopics="completediv1" />
                            <sj:submit id="idButtondonvi" name="nameButtondonvi" value="Gửi dữ liệu" targets="divExportReport"
                                       onBeforeTopics="beforediv1"
                                       onCompleteTopics="completediv1" 
                                       cssStyle="display: none"/>
                            <input type="button" id="idButtondonvitmp" name="nameButtondonvitmp" onclick="submitloadData()" value="Gửi dữ liệu"/>
                            <s:url id="idurlLock" action="LockDataDcpt.action"></s:url>
                            <sj:submit style="display:none;" id="idLock" name="nameLock" href="%{idurlLock}" value="Chốt số liệu" targets="divExportReport" onclick="onclear()"
                                       />
                            
                        

                            <!--<input type="button" id="idButtondonvi" name="nameButtondonvi"  value="Phê Duyệt"/> cssStyle="display: none"-->
                            <hr>
                            <!--style="padding:8px;"-->
                            <!--</br>-->
                          
                        </div>

                    </div>
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
                    <%--<sj:submit onClickTopics="checkAllNodesTopic" value="Check all Nodes" button="true" onclick="onReloadGroup()" />--%>
                    <div id="loadingImageDiv" style="display: none;">
                        <h2 style='color: red'>Xin chờ đang tải dữ liệu ! </h2>
                        </br>
                        <img id="loadingImage" src='img/loading.gif' border='0' >
                    </div>

                    <div id="containParm" align="center">
                        <div id="divExportReport"></div>
                    </div>
                     <!--</fieldset>-->
                </s:form>

        </div>
   
</p>
</body>
</html>
