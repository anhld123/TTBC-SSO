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
    <head>           
        <script>
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
                }
                else
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
                bsubmit=false;
            }
            function submitloadData()
            {
//                $("#divExportReport").empty();
                if (bSearchStatus)
                {
//                    $("#divExportReport").empty();
                    if ($("#frmDataRisk input:checkbox:checked").length > 0)
                    {
                        if (validateRequiredFields())
                        {
                            $("#BrowseSubmit")[0].click();
                            $.subscribe('batdauduyet', function (event, data) {
                                $("#divMessage").show();
                            });

                            $.subscribe('ketthucduyet', function (event, data) {
                                $("#divMessage").hide();
                            });
                        }
                    }
                    else
                    {
                        alert("Bạn phải chọn khách hàng cần phê duyệt!");
                    }
                }
                else
                {
                    if (!bsubmit)
                    {
                        alert('Bạn chưa xem dữ liệu khách hàng chờ phê duyệt nên không thể phê duyệt');
                        $("#divExportReport").html('<span style="color:red"><h2><span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa xem dữ liệu khách hàng chờ phê duyệt nên không thể phê duyệt!</br>\n\
                        Bạn chọn <span style="color:blue"> Trạng thái XL: Chờ phê duyệt </span> </br>Sau đó nhấn vào <span style="color:blue"> Tải dữ liệu </span> để xem dữ liệu sau đó mới phê duyệt được</h2></span>');
                        return;
                    }
                    else
                    {
                        var trangthai_xlrr = $("#trangthai_xlrr").val();
//                    alert('Bạn đã view dữ liệu thành công '+trangthai_xlrr);
                        $("#idButtondonvi")[0].click();
                    }
                }
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
            function getposfromtreecheck()
            {
                var pos_cd = '';
                var i = document.loadFormRisk.elements.length;
                for (var k = 0; k < i; k++)
                {
                    if (document.loadFormRisk.elements[k].name == 'poscd')
                    {
                        if (document.loadFormRisk.elements[k].checked == true)
                        {
                            if (document.loadFormRisk.elements[k].value != '999999')
//                            alert(document.loadFormRisk.elements[k].value);
                                pos_cd = pos_cd + document.loadFormRisk.elements[k].value + ',';
                        }
                    }
                }
                return pos_cd;
            }
            //Disable enter key form submit            
            document.onkeypress = stopRKey;

            function dienthongtintuchoicn() {
                $("#divExportReport").empty();
                var ht1 = screen.availHeight - 100;
                var wt1 = 950;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 10;

                var vb_xlrr = $("#vb_xlrr").val();
                var nam_xlrr = $("#nam_xlrr").val();
                var dot_xlrr = $("#dot_xlrr").val();
                var nhom_xlrr = $("#nhom_xlrr").val();
                var nguon_von = $("#nguon_von").val();


                var poscd = getposfromtreecheck();
//            alert(poscd);
                if (vb_xlrr == null || vb_xlrr == '' || vb_xlrr == '-1')
                {
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn văn bản hoặc quyết định xử lý rủi ro ! </h2>");
                    alert('Bạn phải chọn văn bản hoặc quyết định xử lý rủi ro !');
                    return;
                }
                if (nam_xlrr == null || nam_xlrr == '' || nam_xlrr == '-1')
                {
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn năm rủi ro ! </h2>");
                    alert('Bạn phải chọn năm rủi ro !');
                    return;
                }
                if (dot_xlrr == null || dot_xlrr == '' || dot_xlrr == '-1')
                {
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn đợt rủi ro ! </h2>");
                    alert('Bạn phải chọn đợt rủi ro !');
                    return;
                }
                if (nhom_xlrr == null || nhom_xlrr == '' || nhom_xlrr == '-1')
                {
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn nhóm rủi ro ! </h2>");
                    alert('Bạn phải chọn nhóm rủi ro !');
                    return;
                }
                if (poscd == null || poscd == '' || poscd == '-1')
                {
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải chọn chi nhánh cần nhập dữ liệu trên cây rủi ro ! </h2>");
                    alert('Bạn phải chọn chi nhánh cần nhập dữ liệu trên cây rủi ro !');
                    return;
                }
                var url = "getRiskHeaderPos.action?vb_xlrr="+vb_xlrr+"&nam_xlrr=" + nam_xlrr + "&dot_xlrr=" + dot_xlrr
                        + "&nhom_xlrr=" + nhom_xlrr + "&nguon_von=" + nguon_von + "&poscd=" + poscd;
                //cong them chuoi doan "&namBc="+namBc de lay nam bao cao nguon_von
                var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

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
                 height: 12%;
                padding:10px;

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
                height: 50px;
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
                height: 25px;
                /*border: 1px solid;*/     
                /*width: 40%;*/
                float: left;
                padding:3px; 
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
                padding: 3px 0px 3px 3px;
                margin: 5px 1px 3px 0px;
                border: 1px solid rgba(81, 203, 238, 1);
            }
            #loadingImageDiv
            {
                height: 28px;
                /*border: 1px solid;*/  
                margin-right: 20%;
                /*width: 40%;*/
                /*float: right;*/
/*                padding:3px; 
                border: 1px solid;
                position: fixed;*/
                /*background: brown;*/
                 /*height: 28px;*/
                /*border: 1px solid;*/     
                width: 40%;
                float: right;
            }
        </style>

    </head>
    <body topmargin="0" leftmargin="5">        
        <div id="container" >
            <!--thu nhe-->
            <%--<s:url id="loadDataRisk" action="loadDataRisk" includeContext="false">--%>
            <%--<s:param name="nam_xlrr" value="nam_xlrr"/>--%>
            <%--</s:url>--%>
            <s:form id="loadFormRisk"  name="loadFormRisk" var="test" action="loadDataBrowerView" theme="simple">
                <div id="navParam" >
                    <!--name="namBc" id="namBc"-->
                    <!--value="defaultYearReport"--> 
                    <div id="navParam2">
                        <!--</p>-->
                        <s:label value="XL theo:" cssStyle="color: #029c44;" />
                        <s:select id="vb_xlrr" 
                                  name="vb_xlrr"
                                  list="lstVbXlrr" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  headerKey="-1"
                                  headerValue="-- Chọn --" cssStyle="color: red;vertical-align: middle;">                    
                        </s:select>
                        
                        <s:label value="Năm XL:" cssStyle="color: #029c44;" />
                        <s:select id="nam_xlrr" 
                                  name="nam_xlrr"
                                  list="lstNamXlrr" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  value="defaultNamxlrr" cssStyle="color: red;vertical-align: middle;">                    
                        </s:select>
                        <%--<sj:datepicker name="date_risk" value="%{new java.util.Date()}" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/>--%>
                        <!--&nbsp;-->
                        <s:label value="Đợt XL:" cssStyle="color: #029c44;" />
                        <s:select id="dot_xlrr" 
                                  name="dot_xlrr"
                                  list="lstDotXlrr" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  headerKey="-1"
                                  headerValue="-- Chọn --" cssStyle="color: red;vertical-align: middle;width: 70px;">                    
                        </s:select>
                        <!--&nbsp;-->
                        <s:label value="Nhóm nợ:" cssStyle="color: #029c44;" />
                        <s:select id="nhom_xlrr" 
                                  name="nhom_xlrr"
                                  list="lstNhomXlrr" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  headerKey="-1"
                                  headerValue="-- Chọn --" cssStyle="color: red;vertical-align: middle;width: 100px;">                    
                        </s:select>
                        <s:label value="Trạng thái XL:" cssStyle="color: #029c44;" />
                        <s:select id="trangthai_xlrr" 
                                  name="trangthai_xlrr"
                                  list="lstTrangthaiXlrr" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  value="W"
                                  cssStyle="color: red;vertical-align: middle;width: 120px;">                    
                        </s:select>
                        <!--&nbsp;-->
                        <s:label value="Nguồn vốn:" cssStyle="color: #029c44;" />
                        <s:select id="nguon_von"
                                  name="nguon_von"
                                  list="lstNguonvon" 
                                  listKey="sKey"
                                  listValue="sDesc" 
                                  cssStyle="color: red;width: 90px; vertical-align: middle;">                    
                        </s:select>
                        <!--&nbsp; value="defaultNguonvon"-->                     
                        <sj:submit id="loadsubmitform" name="loadsubmitform" value="Tải dữ liệu" targets="divExportReport" onclick="onclear()"
                                   onBeforeTopics="beforediv1"
                                   onCompleteTopics="completediv1" />
                        <s:url id="browerdonvi" action="BrowerDonvi.action"></s:url>
                        <sj:submit id="idButtondonvi" name="nameButtondonvi" href="%{browerdonvi}" value="Phê Duyệt" targets="divExportReport"
                                   onBeforeTopics="beforediv1"
                                   onCompleteTopics="completediv1" cssStyle="display: none"/>
                        <input type="button" id="idButtondonvitmp" name="nameButtondonvitmp" onclick="submitloadData()" value="Phê Duyệt"/>

                        <!--<input type="button" id="idButtondonvi" name="nameButtondonvi"  value="Phê Duyệt"/> cssStyle="display: none"-->
                        <hr>
                        <!--style="padding:8px;"-->
                        <!--</br>-->
                        <div  id="divSearch1" style="padding:3px;" >
                            <s:url id="idurlSearch" action="searchCustomer.action"></s:url>
                            <s:label value="Mã khoản vay:" id="namesoku" cssStyle="color: #029c44;"> </s:label>
                                <!--<input type="text" id="idsearch_soku" style="margin:0 auto;" value="" name="search_soku" onfocus="this.select()" readonly="true" />-->
                            <s:textfield id="idsearch_soku" name="search_soku"  onkeypress="javascript:keyPressEvent();" 
                                         style="border: 1px solid rgba(81, 203, 238, 1);margin:0 auto;width: 150px; background: white;"></s:textfield>
                            <sj:submit id="idSearch" name="nameSearch" href="%{idurlSearch}" value="Tìm kiếm" targets="divExportReport"
                                       onBeforeTopics="beforediv1"
                                       onCompleteTopics="completediv1" onclick="onFindStatus()"/>
                            <s:if test="reportGrade.equalsIgnoreCase('3')">
                                <%--<s:url id="idurlNguyennhancn" action="dienNguyennhanChinhanh.action"></s:url>--%>
                                <input type="button" id="idnguyennhancn" name="nameNguyennhanchinhanh" 
                                       value="Nguyên nhân từ chối cn" targets="divExportReport" onclick="dienthongtintuchoicn()"
                                       />
                            </s:if>
                                 <!--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;-->
                                <!--button tai du lieu-->
<!--                        <sj:submit id="loadsubmitform" name="loadsubmitform" value="Tải dữ liệu" targets="divExportReport" onclick="onclear()"
                                   cssStyle="height:28px;width:95px;color: #0000FF; background: #c5c5c5; font: bolder"
                                   onBeforeTopics="beforediv1"
                                   onCompleteTopics="completediv1"/>&nbsp;&nbsp-->
                        <!--button phe duyet-->
<!--                        <input type="button" id="idButton" name="idButton" onclick="onclickBrowseRisk()" value="Phê Duyệt"
                               style=" height:28px;width:95px;color: #0000FF; background: #c5c5c5; font: bolder"/>-->
                        <!--button quay ra-->
                            <input type="button" id="idReturn" name="nameReturn" 
                                   onclick="onReturn()" value="Quay ra" style="float: right; height:28px;width:95px;"/>
                             <div id="loadingImageDiv" style="display: none;" >
                                <img id="loadingImage" src='img/loading.gif' border='0' >
                            </div>
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

                <div id="containParm" align="center">
                    <div id="divExportReport"></div>
                </div>
            </s:form>

        </div>
    </p>
</body>
</html>
