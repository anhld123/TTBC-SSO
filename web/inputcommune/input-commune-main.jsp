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
    <head>
        <s:head/>
        <sj:head/>
        <script>
            $.subscribe('beforediv1', function (event, data) {
                $("#divExportReport").empty();
                $("#divExportReport").hide();
                $("#loadingImageDiv").show();
                $("#divMessage").empty();
            });

            $.subscribe('completediv1', function (event, data) {
                $("#loadingImageDiv").hide();
                $("#divExportReport").show();
                //$("#contentDiv").slideDown('slow');
            });

            function onclickSave()
            {
                $("#divMessage").empty();
                //$("#divBrowseRisk").text('');

                // alert(validateRequiredFields());
                // $("#BrowseSubmit").trigger('click');
                if (validateRequiredFields())
                {
                    $("#idSave")[0].click();
                }

            }
//            function onchangedisableBrowseRisk()
//            {
//                //Lay ra trang thai khi 1, cho phe duyet, 2 da phe duyet, 3 chua phe duyet
//
//                var statusrisk = document.loadFormRisk.status_risk.value;
//                //Neu trang thai la 1 (cho phe duyet) thi enable button phe duyet
//                if (statusrisk == '1')
//                {
//                    $("#idButton").removeAttr("disabled");
//
//                }
//                else
//                    $("#idButton").attr("disabled", "disabled");
//                //goi button tai du lieu de dua du lieu len table
//                $("#loadsubmitform").trigger('click');
//            }
            function validateRequiredFields() {
                var result = true; //Luu ket qua kiem tra kieu so co dung khong

                //Cac class nubmer2 phai nhap kieu so
                $(".number2").each(function (index) {
                    var value = $(this).val();
                    value = value.replace(/,/g, "");
                    //Kiem tra xem co nhap kieu so khong
                    if (isNaN(parseFloat(value))) {
                        result = false;
                        //Neu nguoi dung khong nhap dung kieu du lieu
                        //Dua ra canh bao
                        $("#divMessage").html('<span style="color:red"><h2><span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!</h2></span>');
                        alert('Bạn chưa nhập đầy đủ dữ liệu!');
                        return false;
                    }
                    else {
                        //Neu la kieu so --> Kiem tra xem kieu nhap co > 0 
//                        if(parseFloat(value) < 0){
//                            result = false;
//                            
//                            //Dua ra canh bao
//                            $("#divBrowseRisk").html('<span style="color:red"><h2><span style="font-weight: bold; color">Thông báo:</span>  Bạn không được nhập giá trị < 0!</h2></span>');
//                            alert('Bạn không được nhập giá trị < 0!');
//                            return false;
//                        }

                        //Neu la kieu so --> Kiem tra xem kieu nhap co < 9999999999
                        if (parseFloat(value) > 9999999999) {
                            result = false;

                            //Dua ra canh bao
                            $("#divMessage").html('<span style="color:red"><h2><span style="font-weight: bold; color">Thông báo:</span>  Giá trị bạn nhập vượt quá giới hạn!</h2></span>');
                            alert('Giá trị bạn nhập vượt quá giới hạn!');
                            return false;
                        }
                    }
                });
                return result;
            }
        </script>

        <style>
            body,td,th,font{ font-family:Tahoma; font-size:12px; }

            #container{
                width: 100%;
                height: 450px;
                border: 0px solid;
                padding-left: 0px;                
            }

            #containTree{
                width: 18%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 480px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 80%;
                height: 450px;
                padding-left: 20px;
                border: 0px solid;
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

            #divMessage{
                height: 28px;
                /*border: 1px solid;*/     
                width: 40%;
                float: right;
            }
            #navParam{
                height: 30px;
                border: 1px solid;  
                border-radius: 10px; 
                -moz-border-radius: 10px;
                margin:5px;
                padding:5px;
            }
            #navParam2{
                height: 28px;
                border: 1px solid;
                margin-left: 10px;
                font-weight: bold;
            }
        </style>

    </head>
    <body topmargin="0" leftmargin="5">        
        <div id="container" >
            <!--thu nhe-->
            <%--<s:url id="loadDataRisk" action="loadDataRisk" includeContext="false">--%>
            <%--<s:param name="nam_xlrr" value="nam_xlrr"/>--%>
            <%--</s:url>--%>
            <s:form id="idloadDataCommune"  name="nameloadDataCommune" var="test" action="loadDataCommune" theme="simple">

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
                <div id="navParam" >
                    <!--<table  border="1" id="title"><tr>-->
                    &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;
                    <s:label value="Ngày báo cáo:" cssStyle="color: #029c44;" />
                    &nbsp;
                    <sj:datepicker id="idreport_dt" name="report_dt" value="%{new java.util.Date()}" 
                                   placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"
                                   cssStyle="width: 100px"/>
                    &nbsp; &nbsp; &nbsp;
                    <sj:submit id="loadsubmitform" name="loadsubmitform" value="Tải dữ liệu" targets="divExportReport"
                               onBeforeTopics="beforediv1"
                               onCompleteTopics="completediv1"/>
                    &nbsp;|&nbsp;
                    <input type="button" id="idButton" name="idButton" onclick="onclickSave()" value="Lưu dữ liệu"/>

                    <div id="divMessage"></div>

                </div>
                <div id="loadingImageDiv" style="display: none;">
                    <img id="loadingImage" src='img/loading.gif' border='0' >
                </div>
            </s:form>
            <div id="containParm" align="center">
                <div id="divExportReport"></div>
            </div>
        </div>
    </p>
</body>
</html>
