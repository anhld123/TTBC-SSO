<%-- 
    Document   : process_risk
    Created on : Jan 25, 2016, 13:30:00 PM
    Author     : Sr. Chữ
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <sj:head />
    <script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>
    <script type="text/javascript" src="js/Checkdate.js"></script>
    <head>           
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
                height: 500px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 83%;
                height: 500px;
                padding-left: 20px;
                float: left;
                overflow: scroll;
            }
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: 11px;
            }
            .report_group_form{
                width: 100%;
            }

            #navParam{
                height: 65px;
                padding:3px;
                border: 1px solid;                
            }
            #navParam2{
                height: 65px;
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
                border: 1px solid;     
                /*width: 40%;*/
                float: left;
                padding:1px; 
                border: 1px solid;
                position: fixed;
            }
            input[type="text"]
            {
                width: 100%;
                border-color: #18ab29;
                background: #F9F9F9;
                color:#666666;
            }
            input[type=text]:focus, textarea:focus {
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                border: 1px solid rgba(81, 203, 238, 1);
            }
            .datepicker{
            }
        </style>

        <script>
            $.subscribe("myBeforeHandler", function(event, data) {
                $("#loadingImageDiv").show();
            });
            $.subscribe("myCompleteTopics", function(event, data) {
                $("#loadingImageDiv").hide();
            });
            $(document).ready(function() {
                $(".NGAY_SL").css({"width": "80px"});
            });
            function getposfromtreecheck()
            {
                var pos_cd = '';
                var i = document.formMainPLN.elements.length;
                for (var k = 0; k < i; k++)
                {
                    if (document.formMainPLN.elements[k].name == 'poscd')
                    {
                        if (document.formMainPLN.elements[k].checked == true)
                        {
                            if (document.formMainPLN.elements[k].value != '999999')
                                pos_cd = pos_cd + document.formMainPLN.elements[k].value + ',';
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
                    $('#dvut_dcpln').val("-1");
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn xã cần liệt kê danh sách tổ trưởng ! </h2>");
                    return;
                }
                var dvut_dcpt = $("#dvut_dcpln").val();
                if (dvut_dcpt == null || dvut_dcpt == '-1')
                {
                    $('#dvut_dcpln').val("-1");
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn đơn vị ủy thác cần liệt kê danh sách tổ trưởng ! </h2>");
                    return;
                }
            }
            var bsubmit = false;
            $.subscribe('beforediv1', function(event, data) {
                $("#divExportReport").empty();
                $("#divExportReport").hide();
                $("#loadingImageDiv").show();
            });

            $.subscribe('completediv1', function(event, data) {
                $("#loadingImageDiv").hide();
                $("#divExportReport").show();
            });

            var bsubmit = false;
            var bSearchStatus = false;
            function onFindStatus()
            {
                bSearchStatus = true;
                bsubmit = false;
            }
            function onclear()
            {
                $("#idsearch_soku").val('');
                bsubmit = true;
                bSearchStatus = false;
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
                var r = confirm("Bạn có thật sự muốn tải lại dữ liệu không? (OK : Đồng ý, Cancel : Hủy bỏ)");
                if (r == true) {
                    $("#loadsubmitform")[0].click();
                    loadData = true;
                    return true;
                }
                else
                    return false;
            }

            function submitloadData()
            {
                var iSuccess = 0;
                $("#containParm").hide();
                $("#loadingImageDiv").show();
                $("#frmDataDc input[type=checkbox]").each(function()
                {
                    var num_id = this.id;
                    var soku = $.trim($(this).val());
                    if (this.checked && ($.trim($(this).val())) != 'true')
                    {
                        var tongdn = $('#id_TongDN_' + num_id).val();
                        var duno_khoanh = $('#id_Dnokhoanh_' + num_id).val();
                        var duno_cokntn = $('#duco_kntn_' + num_id).val();
                        var duno_khongkntn = $('#dukhong_kntn_' + num_id).val();
                        var ngnhan_khoanh = $('#ngnhan_kh_' + num_id).val();
                        var ngnhan_KCKNTN = $.trim($('#nguyennhan_' + num_id).val().toString());

                        if (parseFloat(duno_cokntn) > 0)
                        {
                            if ((ngnhan_KCKNTN == '01' || ngnhan_KCKNTN == '02' || ngnhan_KCKNTN == '03' || ngnhan_KCKNTN == '04' || ngnhan_KCKNTN == '05' || ngnhan_KCKNTN == '06' || ngnhan_KCKNTN == '07' || ngnhan_KCKNTN == '08' || ngnhan_KCKNTN == '09' || ngnhan_KCKNTN == '10' || ngnhan_KCKNTN == '11')) {
                                $('#nguyennhan_' + num_id).focus();
                                $('#nguyennhan_' + num_id).css({"background-color": "#ffff99"});
                                iSuccess = 1;
                            }
                        }
                        if (parseFloat(duno_khongkntn) > 0 && parseFloat(duno_khoanh) <= 0)
                        {
                            if (!(ngnhan_KCKNTN == '01' || ngnhan_KCKNTN == '02' || ngnhan_KCKNTN == '03' || ngnhan_KCKNTN == '04' || ngnhan_KCKNTN == '05' || ngnhan_KCKNTN == '06' || ngnhan_KCKNTN == '07' || ngnhan_KCKNTN == '08' || ngnhan_KCKNTN == '09' || ngnhan_KCKNTN == '10' || ngnhan_KCKNTN == '11')) {
                                $('#nguyennhan_' + num_id).focus();
                                $('#nguyennhan_' + num_id).css({"background-color": "#ffff99"});
                                iSuccess = 2;
                            }
                        }
                        if (parseFloat(duno_khoanh) > 0 && parseFloat(duno_cokntn) == 0 && ngnhan_khoanh.length < 2)
                        {
                            $('#ngnhan_kh_' + num_id).focus();
                            $('#ngnhan_kh_' + num_id).css({"background-color": "#ffff99"});
                            iSuccess = 3;
                        }
                        if (parseFloat(duno_khoanh) > 0 && parseFloat(duno_cokntn) != 0 && ngnhan_khoanh.length > 2)
                        {
                            $('#duco_kntn_' + num_id).focus();
                            $('#duco_kntn_' + num_id).css({"background-color": "#ffff99"});
                            iSuccess = 4;
                        }
                        if (parseFloat(duno_khoanh) > 0 && parseFloat(duno_khongkntn) != 0)
                        {
                            $('#dukhong_kntn_' + num_id.toString()).val('0');
                            $('#duco_kntn_' + num_id).focus();
                            $('#duco_kntn_' + num_id).css({"background-color": "#ffff99"});
                            iSuccess = 5;
                        }
                        if (parseFloat(duno_khoanh) <= 0 && ngnhan_khoanh.length > 2)
                        {
                            $('#ngnhan_kh_' + num_id).focus();
                            $('#ngnhan_kh_' + num_id).css({"background-color": "#ffff99"});
                            iSuccess = 6;
                        }
                    }
                });
                if (iSuccess != 0)
                {
                    alert('Nhập dữ liệu phân loại khả năng trả nợ chưa hợp lệ. Vui lòng kiểm tra lại!');
                    //alert('Nhập dữ liệu phân loại khả năng trả nợ chưa hợp lệ. Vui lòng kiểm tra lại!' + '\n' + 'Chưa hợp lệ trong các trường hợp sau:' + '\n' + ' 1. Chưa chọn nguyên nhân không có khả năng trả nợ cho món vay bạn chọn PLN' + '\n' + ' 2. Chưa nhập nguyên nhân khoanh cho món vay không có khả năng trả nợ (Món vay nợ khoanh)' + '\n' + ' 3. Món vay khoanh có khả năng trả nợ, nhưng lại nhập nguyên nhân khoanh không có khả năng trả nợ' + '\n' + ' 4. Món vay khoanh không có khả năng trả nợ thì nhập vào cột Có khả năng trả nợ số tiền là 0' + '\n' + ' 5. Món vay không phải khoanh, không cần nhập nguyên nhân khoanh');
                    iSuccess = 0;
                    return false;
                }
                if ($("#frmDataDc input:checkbox:checked").length > 0) {
                    if (validateRequiredFields()) {
                        $("#idSaveDcNo")[0].click();
                    }
                }
                else {
                    alert("Bạn phải chọn khách hàng cần đối chiếu trước khi lưu dữ liệu!");
                }
                setTimeout(setTime,2000);   
            }
            function setTime(){
                //Đoạn này chỉ để chứng minh đã xử lý xong phần load Image loading
                $("#loadingImageDiv").hide();
                $("#containParm").show();
            }
            function validateRequiredFields() {
                var result = true; //Luu ket qua kiem tra kieu so co dung khong
                $(".number2").each(function(index) {
                    var value = $(this).val();
                    value = value.replace(/,/g, "");
                    if (parseFloat(value) > 9999999999) {
                        result = false;
                        alert('Giá trị bạn nhập vượt quá giới hạn!');
                        return false;
                    }
                });
                return result;
            }
            function keyPressEvent() {
                $('#idsearch_soku').keypress(function(e) {
                    if (e.keyCode == 13) {  // Detect the enter key
                        document.getElementById('idSearch').click();
                    }
                });
                var evt = window.event;
                evt.cancel = true;
            }
            function reLoadForm(data)
            {
                $("#idSubmit")[0].click();
            }
            function onReturn()
            {
                $("#container").empty();
                $("#container").text('');
            }
        </script>
    </head>
    <body topmargin="0" leftmargin="5">
        <div id="container">
            <s:form id="formMainPLN"  name="formMainPLN" var="test" action="loadDataDcPLN.action" theme="simple">
                <div id="navParam" >
                    <div id="navParam2">   
                        <table border="0">
                            <tr>
                                <td>
                                    <s:label value="Ngày báo cáo " cssStyle="color: #029c44;"/>
                                    <sj:datepicker name="ngay_dcpln" id="ngay_dcpln"
                                                   value="%{new java.util.Date()}" onblur="validatedate(this.value)" cssClass="NGAY_SL"
                                                   placeholder="DD/MM/YYYY" changeYear="true"  changeMonth="true" displayFormat="dd/mm/yy" 
                                                   cssStyle="vertical-align: middle;"/> 
                                    &nbsp;
                                    <s:url id="reloadData" action="reloadDvut_PLN" includeParams="post"></s:url>
                                    <s:label value="Tổ chức hội " cssStyle="color: #029c44;" />
                                    <sj:select href="%{reloadData}" 
                                               onChangeTopics="reloadTotruong"                                                     
                                               onchange="onReloadGroup()"
                                               id="dvut_dcpln" 
                                               name="dvut_dcpln"
                                               list="lstDvutDcpln" 
                                               listKey="sKey"
                                               listValue="sDesc"           
                                               headerKey="-1"
                                               headerValue="-- Chọn --" 
                                               cssStyle="width: 120px;vertical-align: middle;"
                                               onBeforeTopics="myBeforeHandler_dvut" 
                                               onCompleteTopics="myCompleteTopics_dvut">                    
                                    </sj:select>
                                    &nbsp;
                                    <s:label value="Tổ trưởng " cssStyle="color: #029c44;" />
                                    <sj:select href="%{reloadData}" 
                                               reloadTopics="reloadTotruong"
                                               id="totruong_dcpln" 
                                               name="totruong_dcpln"
                                               list="lstTotruongDcpln" 
                                               listKey="sKey"
                                               listValue="sDesc" 
                                               headerKey="-1"
                                               headerValue="-- Chọn --" 
                                               cssStyle="width: 170px;vertical-align: middle;"
                                               onBeforeTopics="myBeforeHandler" 
                                               onCompleteTopics="myCompleteTopics">                    
                                    </sj:select>
                                    &nbsp;
                                    <s:label value="Trạng thái " cssStyle="color: #029c44;" />
                                    <s:select id="trangthai" name="trangthai" list="#{'N':'Chưa đối chiếu','R':'Không đối chiếu được','S':'Đã đối chiếu'}"
                                              cssStyle="width: 103px; vertical-align: middle;"/>
                                    &nbsp;
                                    <s:label value="Nguồn vốn " cssStyle="color: #029c44;" />
                                    <s:select
                                        id="ngvon_dcpln"
                                        name="ngvon_dcpln"
                                        list="lstNguonvon" 
                                        listKey="sKey"
                                        listValue="sDesc" 
                                        headerKey=""
                                        headerValue="-- Chọn --"
                                        cssStyle="width: 90px; vertical-align: middle;">                    
                                    </s:select>
                                    &nbsp;
                                    <s:label value="Chương trình " cssStyle="color: #029c44;" />
                                    <s:select  
                                        id="chtrinh_dcpln"
                                        name="chtrinh_dcpln"
                                        list="lstChuongtrinh" 
                                        listKey="sKey"
                                        listValue="sDesc" 
                                        headerKey="-1"
                                        headerValue="-- Chọn --"
                                        cssStyle="width: 220px; vertical-align: middle;">                    
                                    </s:select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <s:url id="idurlSearch" action="searchSearchLoanPLN.action"></s:url>
                                    <s:label value="Mã khoản vay " id="namesoku" cssStyle="color: #029c44;"> </s:label>
                                    <s:textfield id="idsearch_soku" name="soku_dcpln"  onkeypress="javascript:keyPressEvent();" 
                                                 style="border: 1px solid rgba(81, 203, 238, 1);margin:0 auto;width: 140px; background: white;"></s:textfield>
                                    <sj:submit id="idSearch" name="nameSearch" href="%{idurlSearch}" value="Tìm kiếm" targets="divExportReport"
                                               onBeforeTopics="beforediv1"
                                               onCompleteTopics="completediv1" onclick="onFindStatus()"/>
                                    <input type="button" id="idReturn" name="nameReturn" 
                                           onclick="onReturn()" value="Quay ra" style="float: right; width:81px;"/>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    <sj:submit id="loadsubmitform" name="loadsubmitform" value="Tải dữ liệu" targets="divExportReport" onclick="onclear()"
                                               onBeforeTopics="beforediv1" onCompleteTopics="completediv1" cssStyle="display: none;"/>
                                    <input type="button" id="loaddata" name="loaddata" onclick="onLoadData()" value="Tải dữ liệu"/>
                                    <sj:submit id="idButtondonvi" name="nameButtondonvi" value="Lưu dữ liệu" targets="divExportReport"
                                               onBeforeTopics="myBeforeHandler"
                                               onCompleteTopics="completediv1" cssStyle="display: none"/>
                                    <input type="button" id="idButtondonvitmp" name="nameButtondonvitmp" onclick="submitloadData()" value="Lưu dữ liệu"/>
                                </td>
                            </tr>
                        </table>
                    </div>  <!--Hết div của navParam2-->
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
                    <h2 style='color: red'> Xin chờ đang tải dữ liệu ...</h2>
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
