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
                $("#loadingImageDiv").show();
            });

            $.subscribe("myCompleteTopics", function (event, data) {
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
                var i = document.formMainDcpt.elements.length;
                for (var k = 0; k < i; k++)
                {
                    if (document.formMainDcpt.elements[k].name == 'poscd')
                    {
                        if (document.formMainDcpt.elements[k].checked == true)
                        {
                            if (document.formMainDcpt.elements[k].value != '999999')
//                            alert(document.loadFormRisk.elements[k].value);
                                pos_cd = pos_cd + document.formMainDcpt.elements[k].value + ',';
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
//                $('#id_tr_1').hide();
                var flag_data = false;
                var flag_data_dt = false;
                var flag_data_nn = false;

                $("#frmDataDc input[type=checkbox]").each(function () {
                    var num_id = this.id;
                    var soku = $.trim($(this).val());
                    var sTk_Casa1 = $.trim($('#sTk_Casa1_' + num_id).val());
//                    alert($(this).val());
//                    && ( $.trim($(this).val())) !=

                    if (this.checked && ($.trim($(this).val())) != 'true') {
                        if (soku != sTk_Casa1)
                        {
//                            alert(soku + ' -> ' + this.checked + ' sTk_Casa1 =' + sTk_Casa1);
                            if ($.trim($('#tt_dautu_' + num_id).val()).length < 1)
                            {
                                //                            alert('Bạn phải nhập liệu cho trường Thực trạng đối tượng đầu tư '+'#tt_dautu_' + num_id);
                                $('#tt_dautu_' + num_id).focus();
                                $('#tt_dautu_' + num_id).css({"background-color": "#ffff99"});
                                flag_data_dt = true;
                            } else
                                $('#tt_dautu_' + num_id).css({"background-color": "#FFCCBA"});
                        }

                        var num_duno_lech = parseFloat($('#duno_lech_' + num_id).val().replace(/,/g, ""));
                        var num_lai_lech = parseFloat($('#lai_lech_' + num_id).val().replace(/,/g, ""));
                        var num_casa_lech = parseFloat($('#casa_lech_' + num_id).val().replace(/,/g, ""));

                        if (num_duno_lech + num_lai_lech + num_casa_lech > 0 && $.trim($('#nn_lech_' + num_id).val()).length < 1)
                        {
//                            alert('Bạn phải nhập liệu cho trường nguyên nhân chênh lệch '+'#tt_dautu_' + num_id);
                            $('#nn_lech_' + num_id).focus();
                            $('#nn_lech_' + num_id).css({"background-color": "#ffff99"});
                            flag_data_nn = true;
                        } else
                            $('#nn_lech_' + num_id).css({"background-color": "#FFCCBA"});
//                      
//                        //duno_lech_0, lai_lech_0,casa_lech_0,nn_lech_0,tt_dautu_0
//                        console.log($(this).val());
                    } else
                    {
                        $('#nn_lech_' + num_id).css({"background-color": "#FFCCBA"});
                        $('#tt_dautu_' + num_id).css({"background-color": "#FFCCBA"});
                    }
                });

                if (flag_data_dt || flag_data_nn)
                {
                    alert('Bạn phải nhập đầy đủ dữ liệu trước khi lưu  ');
                    flag_data = true;
                    return;
                }
                if ($("#frmDataDc input:checkbox:checked").length > 0)
                {
                    if (validateRequiredFields())
                    {
                        $("#idSaveDcNo")[0].click();
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

                    //Neu la kieu so --> Kiem tra xem kieu nhap co < 9999999999
                    if (parseFloat(value) > 9999999999) {
                        result = false;

                        //Dua ra canh bao
//                        $("#divExportReport").html('<span style="color:red"><h2><span style="font-weight: bold; color">Thông báo:</span>  Giá trị bạn nhập vượt quá giới hạn!</h2></span>');
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
                $('#idsearch_soku').keypress(function (e) {
                    if (e.keyCode == 13) {  // detect the enter key
                        document.getElementById('idSearch').click();
//                          alert('Da nhan phim enter');
//                        $('#myButton').click(); // fire a sample click,  you can do anything
                    }
                });
                var evt = window.event;
//                alert('vao han nay');
//                var keyPressed = evt.which || evt.keyCode;
//                if (keyPressed == 13) {
//                    alert('Da nhan phim enter');
////                     $("#idSubmit")[0].click();
//                    document.getElementById('idSearch').click();
                evt.cancel = true;
//                }
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
            var vitri_tr = '';
            function hienthichitiet(soku, value) {
                vitri_tr = value;

                var ht1 = screen.availHeight - 100;
                var wt1 = 1050;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 10;

                var ngay_dcpt = $("#ngay_dcpt").val();
                var totruong_dcpt = $("#totruong_dcpt").val();
                var dvut_dcpt = $("#dvut_dcpt").val();
                var url = "getDetialCustomerDcNo.action?soku=" + soku + "&ngay_dcpt=" + ngay_dcpt +
                        "&dvut_dcpt=" + dvut_dcpt + "&totruong_dcpt=" + totruong_dcpt;
                //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
                var popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }
            function reLoadForm(data)
            {
//                if(vitri_tr=='aa')
                $("#idSubmit")[0].click();
//                else
//                    $('#id_tr_'+vitri_tr).hide();
//                alert(vitri_tr);
            }
//            function onClickTd(num_id)
//            {
//                 $('#id_tr_' + num_id).css({"background-color": "#FFE6B0"});
//                 
//            }




        </script>

        <style>
            body,td,th,font{ font-family:Tahoma; font-size:12px; }

            #container{
                width: 100%;
                height: 500px;
                border: 0px solid;
                padding-left: 0px;        
                /*color: #FFE6B0*/
            }

            #containTree{
                width: 12%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 85%;
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
            <s:form id="formMainDcpt"  name="formMainDcpt" var="test" action="loadDataDcNo.action" theme="simple">
                <!--<fieldset>-->
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
                            <option value="N">Chưa đối chiếu</option>
                            <option value="A">Đã đối chiếu</option>
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
                            <s:url id="idurlSearch" action="searchCustomerDcpt.action"></s:url>
                            <s:label value="Mã khoản vay:" id="namesoku" cssStyle="color: #029c44;"> </s:label>                                
                            <s:textfield id="idsearch_soku" name="soku"  onkeypress="javascript:keyPressEvent();" 
                                         style="border: 1px solid rgba(81, 203, 238, 1);margin:0 auto;width: 150px; background: white;"></s:textfield>
                            <sj:submit id="idSearch" name="nameSearch" href="%{idurlSearch}" value="Tìm kiếm" targets="divExportReport"
                                       onBeforeTopics="beforediv1"
                                       onCompleteTopics="completediv1" onclick="onFindStatus();"/>
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
                    <h2 style='color: red'>Xin chờ đang tải dữ liệu!</h2>
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
