<%-- 
    Document   : table_data_risk
    Created on : Jun 20, 2014, 3:55:34 PM
    Author     : LION
--%>

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css"  href="css/styles-xlrr.css" />
<script src="js/jquery.number.js"></script>
<script src="js/format_num.js"></script>
<html>
    <style>
        th{
            background-color: #DCDCDC;
            border-color: #999;
            height: 18px;
        }
        td{
            border-color: #999;
            height: 20px;
        }
        table.editDelete{
            border-collapse: collapse;
            width: 100%;
            border-color: #999;
        }
        table.editDelete tr:focus{
            background-color:#FFE47A;
            /*cursor: pointer; hover*/
        }

    </style>
    <style type="text/css">

        input[type="text"]
        {
            width: 100%;
            border: 0px;
            /*color: #000000*/
            border-color: #18ab29;
            background: #F9F9F9;
            color:#666666;
        }
        input[type=text]:focus, textarea:focus {
            box-shadow: 0 0 5px rgba(81, 203, 238, 1);
            /*            padding: 3px 0px 3px 3px;
                        margin: 5px 1px 3px 0px;*/
            border: 1px solid rgba(81, 203, 238, 1);
        }
        .datepicker{
        }

        .highlight_row {
            background-color: #FFB951; 
            color:#000;
        }
    </style>
    <SCRIPT language="javascript">
        $.subscribe('batdauduyet', function (event, data) {
            $("#divMessage").show();
        });

        $.subscribe('ketthucduyet', function (event, data) {
            $("#divMessage").hide();
        });
        $(document).ready(function () {
            $('input.number').css({"text-align": "right"});
            $('input.number2').css({"text-align": "right"});
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            $('#ui-datepicker-div').css('clip', 'auto');
            //Cac truong bang so --> se co so truong = 0
            $('.number').number(true, 0);
//  
//            //Cac truong bang so --> se co so truong = 0
            $('.number2').number(true, 0);
            $(".MAKH").css({"width": "100%"});
            $(".MAKH").css({"text-align": "center"});
            $(".DU_NO").css({"width": "100%"});
            $(".TEN_KH").css({"width": "100%"});
            $(".SOKU").css({"width": "100%"});

            $(".TD_CHON").css({"width": "30px"});
            $(".TD_MAKH").css({"width": "80px"});
            $(".TD_MAKH").css({"text-align": "center"});
            $(".TD_DU_NO").css({"width": "50px"});
            $(".TD_TEN_KH").css({"width": "100px"});
            $(".TD_TRANGTHAI").css({"width": "70px"});
            $(".TD_SOKU").css({"width": "115px"});


            $(".TD_NGUYEN_NHAN").css({"width": "200px"});
            $(".TD_NGUYEN_NHAN").css({"text-align": "center"});

        });
//        var popup;
//        function hienthichitiet(soku) {
//            var ht1 = screen.availHeight - 100;
//            var wt1 = 1050;
//            var left1 = (screen.width / 2) - (wt1 / 2);
//            var top1 = 10;
//
//            var ngay_dcpt = $("#ngay_dcpt").val();
//            var totruong_dcpt = $("#totruong_dcpt").val();
//            var dvut_dcpt = $("#dvut_dcpt").val();
//            var url = "getDetialCustomerDcNo.action?soku=" + soku + "&ngay_dcpt=" + ngay_dcpt +
//                    "&dvut_dcpt=" + dvut_dcpt + "&totruong_dcpt=" + totruong_dcpt;
//            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
//            popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
//        }
        //Disable enter key form submit
        function stopRKey(evt) {
            var evt = (evt) ? evt : ((event) ? event : null);
            var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
            if ((evt.keyCode == 13) && (node.type == "text")) {
                return false;
            }
        }

        function click_on(value)
        {
            try
            {
//                alert(value);
                var element_id_1 = "sComment_" + value;
                var input_object1 = document.getElementById(element_id_1).value;
//                alert(input_object1);
                if (input_object1.length > 0)
                {
                    document.getElementById(value).checked = true;
//                    var input_object2 = document.getElementById(value).value;
//                    input_object2.getElementById("checkbox").checked = true;
                }
                else
                {
                    document.getElementById(value).checked = false;
                }

            }
            catch (e)
            {
                alert(e.toString());
            }
        }

        //Disable enter key form submit            
        document.onkeypress = stopRKey;

        function isNumber(value)
        {
            if (value == null)
            {
                alert('Bạn phải nhập dữ liệu cho trường này');
                return false;
            }
            value = value.replace(/,/g, "");
            var result = true; //Luu ket qua kiem tra kieu so co dung khong
            //Kiem tra xem co nhap kieu so khong
            if (isNaN(parseFloat(value))) {
                result = false;
                //Neu nguoi dung khong nhap dung kieu du lieu
                //Dua ra canh bao
                alert('Bạn nhập không đúng kiểu số xin nhập lại dữ liệu');
//                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                focus();
                return false;
            }
            else {
                //Neu la kieu so --> Kiem tra xem kieu nhap co < 9999999999
                if (parseFloat(value) > 9999999999) {
                    result = false;
                    alert('Giá trị bạn nhập vượt quá giới hạn!');
                    //Dua ra canh bao
//                        $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Giá trị bạn nhập vượt quá giới hạn!');
                    focus();
                    return false;
                }
            }
        }

        $(document).ready(function () {
            $("#allCheck").change(function () {
                $(".checkbox1").prop('checked', $(this).prop("checked"));
            });
        });

        $('.MAKH').focus(function () {
            $(this).closest('tr').addClass('highlight_row');
        });
        $('.MAKH').blur(function () {
            $(this).closest('tr').removeClass('highlight_row');
        });
        $('.DU_NO').focus(function () {
            $(this).closest('tr').addClass('highlight_row');
        });
        $('.DU_NO').blur(function () {
            $(this).closest('tr').removeClass('highlight_row');
        });
        $('.TEN_KH').focus(function () {
            $(this).closest('tr').addClass('highlight_row');
        });
        $('.TEN_KH').blur(function () {
            $(this).closest('tr').removeClass('highlight_row');
        });

        $('.checkbox1').focus(function () {
            $(this).closest('tr').addClass('highlight_row');
        });
        $('.checkbox1').blur(function () {
            $(this).closest('tr').removeClass('highlight_row');
        });
        $('.SOKU').focus(function () {
            $(this).closest('tr').addClass('highlight_row');
        });
        $('.SOKU').blur(function () {
            $(this).closest('tr').removeClass('highlight_row');
        });
    </script>
    <script type="text/javascript" src="js/pagination.js">

    </script>

    <body width = "100%">
        <s:form id="frmDataLove" name="frmDataLove" action="saveDataLoveLeaf.action" theme="simple" align="center">
            </br>           
            <s:iterator value="poscd" status="row">
                <s:hidden name="poscd[%{#row.index}]" />
            </s:iterator>
            <s:hidden name="ngay_dcpt" id="ngay_dcpt"/>
            </br>
            <table border="1" class="editDelete" align="center">
                <tr height="30">
<!--                    <th width="15" class="TD_CHON" >
                        <%--<s:checkbox id ="allCheck" name="allCheck"/>--%>
                    </th>-->
                    <th class="TD_DU_NO" >Mã PGD</th>
                    <th class="TD_TEN_KH" >Số tham chiếu</th>

                    <th class="TD_DU_NO" >Mã lá chưa lành</th> 

                    <th class="TD_TEN_KH" >Tên lá chưa lành</th> 

                    <th class="TD_TEN_KH" >Số tiền</th>

                    <th class="TD_DU_NO" >Ngày giao dịch</th>
                    <th class="TD_DU_NO" >Mã xã</th>
                    <th class="TD_TRANGTHAI" >Trạng thái</th>
                    <th class="TD_NGUYEN_NHAN" >Ghi chú</th>
                    <th style="display:none;"class="TD_DU_NO" >Poor</th>

                </tr>


                <s:iterator value="#attr.lstModelLoveLeaf" var="modelDcpt" status="rowstatus">

<!--                    <td align = "center" class="TD_CHON"> 
                        
                        <s:if test="#modelDcpt.sComment != null ">
                            <s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="lstModelSaveLove[%{#rowstatus.index}].sPoorID" fieldValue="%{sPoorID}" checked="checked"/>
                        </s:if>
                        <s:else>
                            <s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="lstModelSaveLove[%{#rowstatus.index}].sPoorID" fieldValue="%{sPoorID}"/>
                        </s:else>
                    </td>-->
                    <td align = "center" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sPosCd'/>" name="sPosCd" class="SOKU" 
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }" onfocus="this.select()" readonly="true"/>
                    </td>  

                    <td align = "center" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sRefNo'/>" name="sRefNo" class="SOKU" 
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }" onfocus="this.select()" readonly="true"/>
                    </td>                                                         

                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sPoorID'/>" name="sPoor" class="SOKU" 
                               onfocus="this.select()" 
                               readonly="true"/>
                    </td>

                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sPoorName'/>" name="sPoorName" class="SOKU" 
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }" onfocus="this.select()" readonly="true"/>
                    </td>

                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sAmount'/>" name="sAmount" class="DU_NO number2"
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }" onfocus="this.select()" readonly="true"/>
                    </td>


                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sTranDT'/>" name="lstModelSaveLove[<s:property  value="%{#rowstatus.index}" />].sTranDT" class="SOKU" 
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }
                                       isNumber(this.value)" onfocus="this.select()"
                               id="sTranDT_<s:property  value="%{#rowstatus.index}" />" readonly="true"/>
                    </td>


                    <!--chi tieu nhap tay tu day--> 

                    <td align = "center" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sCommnue'/>" name="sCommnue" class="SOKU" 
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }" onfocus="this.select()" readonly="true"/>
                    </td>
                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sStatus'/>" name="sStatus" class="SOKU" 
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }" onfocus="this.select()" readonly="true"/>
                    </td>

                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sComment'/>" name="lstModelSaveLove[<s:property  value="%{#rowstatus.index}" />].sComment" class="SOKU"                               
                               onfocus="this.select()"
                               id="sComment_<s:property  value="%{#rowstatus.index}" />"/>
                    </td>
                    <td style="display:none;" align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sPoor_Hidden'/>" name="lstModelSaveLove[<s:property  value="%{#rowstatus.index}" />].sPoor_Hidden" class="SOKU" 
                               onfocus="this.select()"
                               id="sPoor_Hidden_<s:property  value="%{#rowstatus.index}" />"/>
                    </td>

                </tr>
                <%--</sj:div >--%>
                
                 <!--onkeyup="click_on('<s:property  value="%{#rowstatus.index}" />')"-->
            </s:iterator>

        </table>

        <sj:submit id="idSaveLove" name="idSaveLove" 
                   targets="divBrowseRisk" 
                   cssClass="metroButtonStyle" 
                   value="Thêm" onBeforeTopics="batdauduyet" onCompleteTopics="ketthucduyet" cssStyle="display: none"></sj:submit>
    </s:form>
    <s:form action="loadLoveLeaf.action" id="paginationForm">
        <s:iterator value="poscd" status="row">
            <s:hidden name="poscd[%{#row.index}]" />
        </s:iterator>
        <s:hidden name="ngay_dcpt" id="ngay_dcpt"/>
        <%@ include file="/dcpt_no/pagination.jsp" %>
        <sj:submit value="submit" id="idSubmit" name="idSubmit" targets="divExportReport" cssStyle="display: none" 
                   onBeforeTopics="batdauloaddata" onCompleteTopics="hoanthanhloaddata"/>
    </s:form>
    <div id="divBrowseRisk"></div>
</body>
</html>