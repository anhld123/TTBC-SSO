<%-- 
    Document   : process_risk
    Created on : Jun 18, 2014, 9:21:53 PM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
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
            function getposfromtreecheck()
            {
                var pos_cd = '';
                var i = document.formMainPt.elements.length;
                for (var k = 0; k < i; k++)
                {
                    if (document.formMainPt.elements[k].name == 'poscd')
                    {
                        if (document.formMainPt.elements[k].checked == true)
                        {
                            if (document.formMainPt.elements[k].value != '999999')
//                            alert(document.loadFormRisk.elements[k].value);
                                pos_cd = pos_cd + document.formMainPt.elements[k].value + ',';
                        }
                    }
                }
                return pos_cd;
            }

            function onReloadGroup()
            {
                $('#divExportReport').empty();
                var poscd = getposfromtreecheck();
                if (poscd == null || poscd == '')
                {
                    $('#dvut_dcpt').val("-1");
//                    $(document).ready(function () {
//                        $("#dvut_dcpt").prop("selectedIndex", 1);
//                    });
//                    alert('Bạn phải chọn xã cần list tổ trưởng');
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn xã cần liệt kê danh sách tổ trưởng ! </h2>");
                    return;
                }
                var dvut_dcpt = $("#dvut_dcpt").val();
//                alert('poscd=' + poscd + " dvut_dcpt=" + dvut_dcpt);
                if (dvut_dcpt == null || dvut_dcpt == '-1')
                {
//                    alert('Bạn phải chọn đơn vị ủy thác cần list tổ trưởng');
                    $('#dvut_dcpt').val("-1");
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn đơn vị ủy thác cần liệt kê danh sách tổ trưởng ! </h2>");
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
                var trangthai_xlrr = $("#trangthai_xlrr").val();
                if (trangthai_xlrr != 'W')
                {
//                    alert('Bạn chỉ phê duyệt được dữ liệu khi chọn trạng thái chờ phê duyệt');
                    bsubmit = false;
                } else
                {
                    bsubmit = true;
                }
//                $("#idButtondonvi").click();
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
                if ($("#frmDataPt input:checkbox:checked").length > 0)
                {
                    if (validateRequiredFields())
                    {
                        $("#idSavePtNo")[0].click();
                    }
                } else
                {
                    // none is checked
                    alert("Bạn phải chọn khách hàng cần đối chiếu trước khi lưu dữ liệu!");
                }

            }
            function validateRequiredFields() {
                var result = true; //Luu ket qua kiem tra kieu so co dung khong

                //Cac class nubmer2 phai nhap kieu so
                $(".number2").each(function (index) {
                    var value = $(this).val();
                    value = value.replace(/,/g, "");
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
            document.onkeypress = stopRKey;
            var loadData = false;
            function onLoadData()
            {
                if (!loadData)
                {
                    $("#loadsubmitform")[0].click();
                    loadData = true;
                    return true;
                }
                var r = confirm("Bạn có thật sự muốn tải lại dữ liệu không ? OK : Đồng ý, Cancel : Hủy bỏ");
                if (r == true) {
                    $("#loadsubmitform")[0].click();
                    loadData = true;
                    return true;
                } else
                    return false;
            }

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
                width: 18%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 500px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 80%;
                height: 450px;
                padding-left: 20px;
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
                height: 65px;
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

            <s:form id="formMainPt"  name="formMainPt" var="test" action="loadDataPtNo.action" theme="simple">
                <div id="navParam" >
                    <div id="navParam2">                        
                        <!--</p>-->
                        <s:url id="reloadData" action="reloadDvut" includeParams="post"></s:url>
                            <div id="date_sl">
                            <s:label value="Ngày SL:" cssStyle="color: #029c44;" />
                            <sj:datepicker name="ngay_dcpt" id="ngay_dcpt"
                                           value="%{new java.util.Date()}" onblur="validatedate(this.value)" cssClass="NGAY_SL"
                                           placeholder="DD/MM/YYYY" changeYear="true"  changeMonth="true" displayFormat="dd/mm/yy" 
                                           cssStyle="font-weight: bold;vertical-align: middle;"/>  
                        </div>
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
                        <s:label value="Trạng thái:" cssStyle="color: #029c44;" />
                        <select id="trangthai" name="trangthai" style="font-weight: bold; width: 100px; vertical-align: middle;">
                            <option value="N">Chưa phân tích</option>
                            <option value="A">Đã phân tích</option>
                        </select>
                        <s:label value="Nguồn vốn:" cssStyle="color: #029c44;" />
                        <s:select
                            id="nguon_von"
                            name="nguon_von"
                            list="lstNguonvon" 
                            listKey="sKey"
                            listValue="sDesc" 
                            headerKey=""
                            headerValue="-- Chọn --"
                            cssStyle="font-weight: bold;width: 90px; vertical-align: middle;">                    
                        </s:select>
                        <s:label value="Chương trình:" cssStyle="color: #029c44;" />
                        <s:select  
                            id="chuongtrinh"
                            name="chuongtrinh"
                            list="lstChuongtrinh" 
                            listKey="sKey"
                            listValue="sDesc" 
                            headerKey="-1"
                            headerValue="-- Chọn --"
                            cssStyle="font-weight: bold;width: 200px; vertical-align: middle;">                    
                        </s:select>

                        <sj:submit id="loadsubmitform" name="loadsubmitform" value="Tải dữ liệu" targets="divExportReport" onclick="onclear()"
                                   onBeforeTopics="beforediv1"
                                   onCompleteTopics="completediv1" cssStyle="display: none"/>

                        <input type="button" id="loaddata" name="loaddata" onclick="onLoadData()" value="Tải dữ liệu"/>
                        <sj:submit id="idButtondonvi" name="nameButtondonvi" value="Lưu dữ liệu" targets="divExportReport"
                                   onBeforeTopics="beforediv1"
                                   onCompleteTopics="completediv1" cssStyle="display: none"/>
                        <input type="button" id="idButtondonvitmp" name="nameButtondonvitmp" onclick="submitloadData()" value="Lưu dữ liệu"/>

                        <!--<input type="button" id="idButtondonvi" name="nameButtondonvi"  value="Phê Duyệt"/> cssStyle="display: none"-->
                        <hr>
                        <!--style="padding:8px;"-->
                        <!--</br>-->
                        <div  id="divSearch1" style="padding:0px;" >
                            <s:url id="idurlSearch" action="searchCustomerPt.action"></s:url>
                            <s:label value="Mã khoản vay:" id="namesoku" cssStyle="color: #029c44;"> </s:label>
                                <!--<input type="text" id="idsearch_soku" style="margin:0 auto;" value="" name="search_soku" onfocus="this.select()" readonly="true" />-->
                            <s:textfield id="idsearch_soku" name="soku"  onkeypress="javascript:keyPressEvent();" 
                                         style="border: 1px solid rgba(81, 203, 238, 1);margin:0 auto;width: 150px; background: white;"></s:textfield>
                            <sj:submit id="idSearch" name="nameSearch" href="%{idurlSearch}" value="Tìm kiếm" targets="divExportReport"
                                       onBeforeTopics="beforediv1"
                                       onCompleteTopics="completediv1" onclick="onFindStatus()"/>
                            <input type="button" id="idReturn" name="nameReturn" 
                                   onclick="onReturn()" value="Quay ra" style="float: right; height:28px;width:95px;"/>
                        </div>
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
            </s:form>

        </div>
    </p>
</body>
</html>
