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




<!DOCTYPE html>
<html>
    <head>
        <sj:head/>
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
                /*overflow: scroll;*/
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

            #navParam{
                height: 35px;
                padding:0px;
                padding-bottom: 0px;
                padding-top: 0px;
                /*margin:5px;*/
                /*border-radius: 10px; //bo tron goc*/
                /*border: 1px solid;*/                
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

            #divTitle{
                font: 14px Arial, Helvetica, sans-serif;
                font-weight: bold;
                color: #0077b3;
                /*                text-align: center;*/
            }

            .field_set1{
                /*border-color: #9999FF;*/
                border-style: solid;
                background: #f7f7f7;
                border-radius: 5px;
            }
            .field_set{
                /*border-color: #9999FF;*/
                border-style: solid;
                border-radius: 5px;
                overflow:scroll;
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
//                var khoa_nhaptaycn = $("#khoa_nhaptaycn").val();
//                var poscd = getposfromtreecheck();
//                alert(khoa_nhaptaycn);

                $("#loadDataKyquy")[0].click();
                bsubmit = true;
//                return true;
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
        </script>
    </head>

    <body>
        <div id="container" >
            <s:form id="id_tracuu_kyquy" name="name_tracuu_kyquy" action="load_tracuu_kyquy" theme="simple">
                <s:hidden name="khoa_nhaptaycn" id="khoa"/>
                <fieldset class="field_set1">
                    <legend id="divTitle">
                        <a class="tooltipIcon" href="">
                            <img src="img/report_in.png"/>
                        </a>
                        Báo cáo danh sách ký quỹ
                    </legend>
                    <div id="navParam" >      
                        <!--<div id="navParam3">-->     
                        <table>
                            <tr style="height: 30px;">                                
                                <td >
                                    Từ ngày: 
                                </td>
                                <td >
                                    <sj:datepicker name="tungay" value="%{new java.util.Date()}"  id="tungay"
                                                   placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/>                                        
                                </td>
                                <td style="width: 5px">

                                </td>
                                <td >
                                    Đến ngày: 
                                </td>
                                <td >
                                    <sj:datepicker name="denngay" value="%{new java.util.Date()}"  id="denngay"
                                                   placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/>                                        
                                </td>
                                <td style="width: 5px">

                                </td>
                                <td style="width: 5px">

                                </td>
                                <td>
                                    Số điện thoại: 
                                </td>
                                <td colspan="2">
                                    <input type="text" style="text-align:right;width: 100px;  border-radius: 5px" value="" id="sodienthoai" name="sodienthoai" class="" placeholder="<s:property value="label"/>"/>
                                </td>

                                <td >
                                    Mã khách hàng: 
                                </td>
                                <td colspan="2">
                                    <input type="text" style="text-align:right;width: 100px ;  border-radius: 5px" value="" id="makh" name="makh" class="" placeholder="<s:property value="label"/>"/>
                                </td>
                                <td style="width: 5px">

                                </td>
                                <td >
                                    Trạng thái: 
                                </td>
                                <td >
                                    <select id="trangthai" name="trangthai" style="width: 130px; vertical-align: middle;">
                                        <option value="">Tất cả</option>
                                        <option value="1">Thành công</option>
                                        <option value="2">Không thành công</option>
                                    </select>
                                </td>

                                <td style="width: 10px">

                                </td>
                                <td >                                       
                                    <sj:submit id="loadDataKyquy" name="loadDataKyquy" value="Tra cứu" targets="divExportReport"
                                               onBeforeTopics="beforediv_data"
                                               onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                    <input type="button" style="background: #C0C0C0; width: 80px" id="loadDataKyquytmp" name="nameloadDataKyquytmp"  onclick="onLoadData()" value="Tra cứu"/>                                                                    
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
                        <!--</div>-->
                    </div>
                </fieldset>

                <s:if test="(Grade.equalsIgnoreCase('1'))">
                    <div id="containParm_full" align="center">
                        <div id="divExportReport"></div>                        
                    </div>
                </s:if>
                <s:else>
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
                    <div id="containParm" >
                        <fieldset style="height: 99%" class="field_set">
                            <legend id="divTitle">
                                <a class="tooltipIcon" href="">
                                    <img src="img/report_icon.png"/>
                                </a>
                                Chi tiết
                            </legend>
                            <div id="divExportReport"></div>  
                        </fieldset>
                    </div>
                </s:else>                     

            </s:form>
        </div>        
    </body>
</html>