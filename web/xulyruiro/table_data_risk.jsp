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
            height: 22px;
        }
        td{
            border-color: #999;
            height: 20px;
        }
        table.editDelete{
            border-collapse: collapse;
            width: 70%;
            border-color: #999;
        }
        table.editDelete tr:hover{
            background-color:#FFE47A;
            cursor: pointer;
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
            padding: 3px 0px 3px 3px;
            margin: 5px 1px 3px 0px;
            border: 1px solid rgba(81, 203, 238, 1);
        }
        .datepicker{
        }
    </style>
    <SCRIPT language="javascript">
//         $(function () {
//            $(".datepicker").datepicker({ dateFormat: 'dd/mm/yy' });
//        });

        $.subscribe('batdauduyet', function(event, data) {
            $("#divMessage").show();
            //                $("#divExportReport").show();
            //$("#contentDiv").slideDown('slow');
        });

        $.subscribe('ketthucduyet', function(event, data) {
            $("#divMessage").hide();
//                $("#divExportReport").show();
            //$("#contentDiv").slideDown('slow');
        });
        $(document).ready(function() {
            $('input.number').css({"text-align": "right"});
            $('input.number2').css({"text-align": "right"});
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            $('#ui-datepicker-div').css('clip', 'auto');
            //Cac truong bang so --> se co so truong = 0
            $('.number').number(true, 0);
//  
//            //Cac truong bang so --> se co so truong = 0
            $('.number2').number(true, 0);
            $(".MA_PGD_DP").css({"width": "60px"});
            $(".TEN_KH").css({"width": "130px"});
            $(".SOKU").css({"width": "115px"});
            $(".GOC_RR").css({"width": "70px"});
//            $(".GOC_RR").css({"border": "1px solid #18ab29"});
            $(".GOC_RR_EDIT").css({"width": "70px"});
            $(".NGUYEN_NHAN_EDIT").css({"width": "40px"});
//            $(".NGUYEN_NHAN_EDIT").css({"border": "1px solid #DDDDDD"});
//             $(".GOC_RR_EDIT").css({"border": "1px solid #DDDDDD"});
            $(".CHUONG_TRINH").css({"width": "40px"});
            $(".CHUONG_TRINH").css({"text-align": "center"});
            $(".NGAY_RR").css({"width": "70px"});
            $(".NGUYEN_NHAN").css({"width": "40px"});
            $(".NGUYEN_NHAN").css({"text-align": "center"});
            $(".MOTA_NN").css({"width": "350px"});
            $(".TUCHOI").css({"width": "50px"});
            var trangthai_xlrr = $("#trangthai_xlrr").val();
//            var soku_search = $("#idsearch_soku").val();
            if (trangthai_xlrr != 'W')
                $(".TUCHOI").hide();
            if (trangthai_xlrr == 'A')
                $(".TUCHOI").show();
            var nhom_xlrr = $("#nhom_xlrr").val();
            if (nhom_xlrr == '03')
                $(".NGUYEN_NHAN_EDIT").hide();
//            $("#idsearch_soku").removeAttr("readonly");
        });
        function showandhide(soku)
        {
//            var soku_search = $("#search_soku").val();
            if (soku != null)
                $(".TUCHOI").show();
        }
        function hienthichitiet(soku) {
            var ht1 = screen.availHeight - 100;
            var wt1 = 600;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 10;

            var nam_xlrr = $("#nam_xlrr").val();
            var trangthai_xlrr = $("#trangthai_xlrr").val();
            var dot_xlrr = $("#dot_xlrr").val();
            var nhom_xlrr = $("#nhom_xlrr").val();
            var chuongtrinh = $("#chuongtrinh").val();
            var vb_xlrr = $("#vb_xlrr").val();
//            $('#paginationForm').submit(function () {
//                var btn = $(this).find("input[type=submit]:focus").val();
//                alert(poscd);
//
//            }
//            var poscd = $("#frmDataRisk :poscd").val();
            var poscd = $('#paginationForm #poscd').val();
//                    alert(poscd);

            var url = "getDetialCustomer.action?soku=" + soku + "&nam_xlrr=" + nam_xlrr + "&dot_xlrr=" + dot_xlrr
                    + "&trangthai_xlrr=" + trangthai_xlrr + "&nhom_xlrr=" + nhom_xlrr + "&chuongtrinh=" + chuongtrinh + "&poscd=" + poscd
                    + "&vb_xlrr=" + vb_xlrr;
            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
            var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        }
        function diennoidung_tuchoi(soku)
        {
            var ht1 = screen.availHeight - 100;
            var wt1 = 600;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 10;

            var nam_xlrr = $("#nam_xlrr").val();
            var dot_xlrr = $("#dot_xlrr").val();
            var nhom_xlrr = $("#nhom_xlrr").val();
            var trangthai_xlrr = $("#trangthai_xlrr").val();
            var chuongtrinh = $("#chuongtrinh").val();
            var nguon_von = $("#nguon_von").val();
            var vb_xlrr = $("#vb_xlrr").val();
//            $('#paginationForm').submit(function () {
//                var btn = $(this).find("input[type=submit]:focus").val();
//                alert(poscd);
//
//            }
//            var poscd = $("#frmDataRisk :poscd").val();
            var poscd = $('#paginationForm #poscd').val();
//                    alert(poscd);

            var url = "getDetialCustomerReject.action?soku=" + soku + "&nam_xlrr=" + nam_xlrr + "&dot_xlrr=" + dot_xlrr + "&nhom_xlrr=" + nhom_xlrr
                    + "&trangthai_xlrr=" + trangthai_xlrr + "&chuongtrinh=" + chuongtrinh + "&nguon_von=" + nguon_von + "&poscd=" + poscd+ "&vb_xlrr=" + vb_xlrr;
            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
            var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        }
        function rejectrisk(soku) {
            var ht1 = screen.availHeight - 100;
            var wt1 = 600;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 10;

            var nam_xlrr = $("#nam_xlrr").val();
            var trangthai_xlrr = $("#trangthai_xlrr").val();
            var dot_xlrr = $("#dot_xlrr").val();
            var nhom_xlrr = $("#nhom_xlrr").val();
            var chuongtrinh = $("#chuongtrinh").val();
            var vb_xlrr = $("#vb_xlrr").val();
//            $('#paginationForm').submit(function () {
//                var btn = $(this).find("input[type=submit]:focus").val();
//                alert(poscd);
//
//            }
//            var poscd = $("#frmDataRisk :poscd").val();
            var poscd = $('#paginationForm #poscd').val();
//                    alert(poscd);

            var url = "${pageContext.request.contextPath}/setRejectRisk.action?random=" + Math.round((Math.random() * 1000) + 1);
//                 "?soku=" + soku + "&nam_xlrr=" + nam_xlrr + "&dot_xlrr=" + dot_xlrr
//                    + "&nhom_xlrr=" + nhom_xlrr + "&chuongtrinh=" + chuongtrinh + "&poscd=" + poscd;
            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
//            var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
//            window.location=url;
            $("#idRejectRisk").click();
        }
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

        function isNumber(value)
        {
            if (value == null)
            {
                alert('Bạn phải nhập dữ liệu cho trường này');
//                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');

                return false;
            }
            value = value.replace(/,/g, "");
//            alert(value.replace(/,/g, ""));
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
                //Neu la kieu so --> Kiem tra xem kieu nhap co > 0 
//                if (parseFloat(value) < 0) {
//                    result = false;
//                    alert('Bạn không được nhập giá trị < 0!');
//                    //Dua ra canh bao
////                        $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn không được nhập giá trị < 0!');
//                    focus();
//                    return false;
//                }

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
    </script>
    <script>
        function handleChange(index, value) {
//            alert(index+ value);
            var id = "DNN_"+ index;
            $("#"+id).val(value);
        }
        </script>
    <script type="text/javascript" src="js/pagination.js">
    </script>

    <body width = "100%">
        <s:form id="frmDataRisk" name="frmDataRisk" action="xacNhanSoLieu" theme="simple" align="center">
            <s:hidden name="nam_xlrr" id="nam_xlrr"/>
            <s:hidden name="trangthai_xlrr" id="trangthai_xlrr"/>
            <s:hidden name="dot_xlrr" id="dot_xlrr"/>
            <s:hidden name="nhom_xlrr" id="nhom_xlrr"/>
            <s:hidden name="poscd" id="poscd"/>
            <s:hidden name="chuongtrinh" id="chuongtrinh"/>
            <s:hidden name="nguon_von" id="nguon_von"/>
            <s:hidden name="vb_xlrr" id="vb_xlrr"/>
            <!--<div style="margin: 7px 7px 7px 7px;" align="center">-->
            </br>
            <table border="1" class="editDelete" align="center" style="padding: 3px 0px 3px 3px;">
                <tr>
                    <th>Mô tả</th>
                    <th>Tổng số món</th>
                    <th> Tổng tiền </th>
                    <th>Tổng gốc</th>
                    <th>Tổng lãi</th>
                </tr>
                <!--            <tr>
                                <th rowspan="2">STT</th>
                                <th rowspan="2">Mã Đơn vị</th>
                                <th rowspan="2">Tên Đơn vị</th>
                                <th rowspan="2">Tổng số món</th>
                                <th rowspan="2"> Tổng tiền </th>
                                <th colspan="2">Trong đó</th> 
                            </tr>
                            <tr>
                                <th>Tổng gốc</th>
                                <th>Tổng lãi</th>
                            </tr>-->
                <s:iterator value="#attr.lstBrowerView" var="modelRiskView" status="rowstatus">
                    <tr>
                        <!--<td>aaaaaaaaaaaaaaaaaaa</td>-->
                        <s:if test="sPoscd.equalsIgnoreCase('999999')">
                            <td style="text-align: center; color: #007fff; font-weight: bold;"><s:property  value="sPosDesc" /></td>
                            <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sSoKh" /></td>
                            <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sTongtien" /></td>
                            <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sTongDuno" /></td>
                            <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sTongLai" /></td>
                            <%--<sj:datepicker name="date" id="date" value="%{new java.util.Date()}" placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"></sj:datepicker>--%>
                        </s:if>

                        <!-- <s:else>
                                <td style="text-align: center;"><s:property  value="nStt" /></td>
                                <td style="text-align: center;"><s:property  value="sPoscd" /></td>
                                <td><s:property  value="sPosDesc" /></td>
                                <td style="text-align: right;"><s:property  value="sSoKh" /></td>
                                <td style="text-align: right;"><s:property  value="sTongtien" /></td>
                                <td style="text-align: right;"><s:property  value="sTongDuno" /></td>
                                <td style="text-align: right;"><s:property  value="sTongLai" /></td>
                        </s:else>-->
                    </tr>
                </s:iterator>
            </table>
            </br>
            <table cellpadding="0" cellspacing="0" class="my-table" align="center" style="width: 98%">
                <tr>     
                    <th width="15" class="sortable"><s:checkbox id ="allCheck" name="allCheck" onclick="selectallMe()"/></th>                    
                        <s:if test="reportGrade.equalsIgnoreCase('1')"> 
                        <th class="MA_PGD_DP">Mã thôn</th>
                        </s:if>
                        <s:else> 
                        <th class="MA_PGD_DP">Mã PGD</th>
                        </s:else>
                    <th class="TEN_KH">Tên khách hàng</th>
                    <th class="SOKU">Số khế ước</th>
                        <s:if test="reportGrade.equalsIgnoreCase('3')">
                        <!--                        <th class="GOC_RR1"  >Gốc đề nghị</th>
                                                <th class="GOC_RR1"  >Lãi đề nghị</th>-->
                        <th class="GOC_RR_EDIT"  >Gốc xử lý</th>
                        <th class="GOC_RR_EDIT"  >Lãi xử lý</th>
                        </s:if>
                        <s:else>
                        <!--                        <th class="GOC_RR1" >Gốc rủi ro</th>
                                                <th class="GOC_RR1"  >Lãi rủi ro</th>-->
                        <th class="GOC_RR_EDIT"  >Gốc đề nghị</th>
                        <th class="GOC_RR_EDIT" >Lãi đề nghị</th>
                        </s:else>                   

                    <th class="CHUONG_TRINH">C.trình</th>    
                    <!--<th class="sortable">Tên sản phẩm</th>--> 
                    <th class="NGAY_RR">Ngày vay</th> 
                    <th class="NGAY_RR">Ngày đ.hạn</th> 
                    <th class="NGAY_RR">Ngày rủi ro</th> 
                    <th class="NGUYEN_NHAN">Thiệt hại</th>   
                    <th class="NGUYEN_NHAN_EDIT">Tháng DN</th> 
                    <th class="TEN_KH">Nguyên nhân</th> 
                    <th class="TUCHOI">Từ chối</th> 
                </tr>
                <s:iterator value="#attr.lstTableRiskObj" var="modelRisk" status="rowstatus">
                    <s:if test="#rowstatus.even == true">
                        <tr class="ac_odd">
                        </s:if>
                        <s:else>
                        <tr class="ac_odd">
                        </s:else>
                        <td align = "center"> 
                            <s:checkbox id ="check_legacyid" name="lstRisk[%{#rowstatus.index}].check_legacyid" fieldValue="%{sSoku}" onclick="selectall()"/>
                        </td>
                        <td align = "center">
                            <s:if test="reportGrade.equalsIgnoreCase('1')"> 
                                <input type="text" value="<s:property  value="sMadp" />" name="sMadp" class="MA_PGD_DP" onfocus="this.select()" readonly="true" />
                            </s:if>
                            <s:else>
                                <input type="text" value="<s:property  value="sMapgd" />" name="sMapgd" class="MA_PGD_DP" onfocus="this.select()" readonly="true" />
                            </s:else>
                        </td>
                        <!--thay doi ve gia tri khong co de ngay vao else-->
                        <s:if test="reportGrade<'4'">
                            <td align = "left" >
                                <input type="text" value="<s:property  value="sTenkh" />" 
                                       name="lstRisk[<s:property  value="%{#rowstatus.index}" />].sTenkh" class="TEN_KH" onfocus="this.select()" readonly="true" />
                            </td>
                        </s:if>
                        <s:else>
                            <td align = "left" >
                                <input type="text" value="<s:property  value="sTenkh" />" 
                                       name="lstRisk[<s:property  value="%{#rowstatus.index}" />].sTenkh" class="TEN_KH" onfocus="this.select()" />
                            </td>
                        </s:else>

                        <td align = "center">
                            <a href="javascript:hienthichitiet('<s:property value="sSoku"/>')" class="SOKU linkKh">
                                <s:property value='sSoku'/>
                            </a>
                        </td>
                        <s:if test="reportGrade.equalsIgnoreCase('3')">
                            <td align = "right">
                                <input type="text" value="<s:property value='dbXl_Duno'/>" name="lstRisk[<s:property  value="%{#rowstatus.index}" />].duno_rr" class="GOC_RR_EDIT number2"
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               isNumber(this.value)" onfocus="this.select()" readonly="true"/>
                            </td>
                            <td align = "right">
                                <input type="text" value="<s:property value='dbXl_Lai'/>" name="lstRisk[<s:property  value="%{#rowstatus.index}" />].lai_rr" class="GOC_RR_EDIT number2" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               isNumber(this.value)" onfocus="this.select()" readonly="true"/>
                            </td>
                        </s:if>
                        <s:else>
                            <td align = "right">
                                <input type="text" value="<s:property value='dbDnghi_Dno'/>" name="lstRisk[<s:property  value="%{#rowstatus.index}" />].duno_rr" class="GOC_RR_EDIT number2"
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               isNumber(this.value)" onfocus="this.select()" readonly="true"/>
                            </td>
                            <td align = "right">
                                <input type="text" value="<s:property value='dbDnghi_Lai'/>" name="lstRisk[<s:property  value="%{#rowstatus.index}" />].lai_rr" class="GOC_RR_EDIT number2" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               isNumber(this.value)" onfocus="this.select()" readonly="true"/>
                            </td>
                        </s:else>                   
                        <td align = "center">
                            <input type="text" value="<s:property  value="sChtrinh" />" name="sChtrinh" class="CHUONG_TRINH" onfocus="this.select()" readonly="true"/>
                        </td>
                        <!--voi cap pgd do khong cho sua ngay nen khong de datepicker-->
                        <!--thay doi ve gia tri khong co de ngay vao else-->
                        <s:if test="reportGrade<'4'">
                            <td align = "center">
                                <input type="text" value="<s:property  value="sNgayvay" />" 
                                       name="lstRisk[<s:property  value="%{#rowstatus.index}" />].sNgayvay" placeholder="dd/MM/yyyy" 
                                       class="NGAY_RR" onfocus="this.select()" readonly="true"/>
                            </td>
                            <td align = "center">
                                <input type="text" value="<s:property  value="sNgaydh" />" 
                                       name="lstRisk[<s:property  value="%{#rowstatus.index}" />].sNgaydh" placeholder="dd/MM/yyyy" 
                                       class="NGAY_RR" onfocus="this.select()" readonly="true"/>
                            </td>    
                            <td align = "center">
                                <input type="text" value="<s:property  value="sNgayrr" />" 
                                       name="lstRisk[<s:property  value="%{#rowstatus.index}" />].sNgayrr" placeholder="dd/MM/yyyy" 
                                       class="NGAY_RR" onfocus="this.select()" readonly="true"/>
                            </td>
                            <td align = "center">
                                <input type="text" value="<s:property  value="dbMdthiethai" />" 
                                       name="lstRisk[<s:property  value="%{#rowstatus.index}" />].sThiethai" 
                                       class="NGUYEN_NHAN number2" onfocus="this.select()" onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               isNumber(this.value)" readonly="true"/>                        
                            </td>
                            </td>
                        </s:if>

                        <!--voi cap cn va tw do cho sua ngay nen de datepicker-->
                        <!--khong cho dien ngay truc tiep ma cho chon tren datepicker-->
                        <s:else>
                            <td align = "center">
                                <input type="text" value="<s:property  value="sNgayvay" />" 
                                       name="lstRisk[<s:property  value="%{#rowstatus.index}" />].sNgayvay" placeholder="dd/MM/yyyy" 
                                       class="NGAY_RR datepicker" onfocus="this.select()" readonly="true"/>
                            </td>
                            <td align = "center">
                                <input type="text" value="<s:property  value="sNgaydh" />" 
                                       name="lstRisk[<s:property  value="%{#rowstatus.index}" />].sNgaydh" placeholder="dd/MM/yyyy" 
                                       class="NGAY_RR datepicker" onfocus="this.select()" readonly="true"/>
                            </td>    
                            <td align = "center">
                                <input type="text" value="<s:property  value="sNgayrr" />" 
                                       name="lstRisk[<s:property  value="%{#rowstatus.index}" />].sNgayrr" placeholder="dd/MM/yyyy" 
                                       class="NGAY_RR datepicker" onfocus="this.select()" readonly="true"/>
                            </td>
                            <td align = "center">
                                <input type="text" value="<s:property  value="dbMdthiethai" />"
                                       name="lstRisk[<s:property  value="%{#rowstatus.index}" />].sThiethai" 
                                       class="NGUYEN_NHAN number2" onfocus="this.select()" onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               isNumber(this.value)"/>                        
                            </td>
                            </td>
                        </s:else>
                        <s:if test="reportGrade.equalsIgnoreCase('3')">
                            <td align = "center" class="NGUYEN_NHAN_EDIT">
                                <input type="text" value="<s:property  value="dbPduyet_Tg" />" name="lstRisk[<s:property  value="%{#rowstatus.index}" />].thang" class="NGUYEN_NHAN_EDIT number2" onfocus="this.select()"  
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               isNumber(this.value)" readonly="true"/>
                            </td>
                        </s:if>
                        <s:else>
                            <td align = "center" class="NGUYEN_NHAN_EDIT">
                                <input type="text" value="<s:property  value="dbDnghi_Tg" />" name="lstRisk[<s:property  value="%{#rowstatus.index}" />].thang" class="NGUYEN_NHAN_EDIT number2" onfocus="this.select()"  
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               isNumber(this.value)" readonly="true"/>
                            </td>
                        </s:else>
                        <!--thay doi ve gia tri khong co de ngay vao else-->
                        <s:if test="reportGrade<'4'">
<!--                            <td align = "center">
                                <input type="text" value="<s:property  value="sNguyennhan" />" 
                                       name="lstRisk[<s:property  value="%{#rowstatus.index}" />].sNguyennhan" class="NGUYEN_NHAN" onfocus="this.select()" readonly="true" size="2" maxlength="2"/>
                            </td>-->
                            <td align = "left" class="TD_TENKH">                                        
                                    <s:select   
                                            id="lstTableRiskObj[%{#rowstatus.index}].sNguyennhan"
                                            name="lstTableRiskObj[%{#rowstatus.index}].sNguyennhan"
                                            list="lstNguyenNhanRR"                                             
                                            listKey="sKey"
                                            listValue="sDesc"
                                            headerKey="-1"
                                            headerValue="--- Chọn ---"              
                                            onchange="handleChange(%{#rowstatus.index},this.value)"
                                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                        
                                        <input type="hidden" value="<s:property  value="sNguyennhan" />" id="DNN_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstRisk[<s:property  value="%{#rowstatus.index}" />].sNguyennhan" class="TEN_KH"/>
                                    </s:select>
                            </td>    
                        </s:if>
                        <s:else>
                            <td align = "left" class="TD_TENKH">                                        
                                    <s:select  
                                            id="lstTableRiskObj[%{#rowstatus.index}].sNguyennhan"
                                            name="lstRisk[%{#rowstatus.index}].sNguyennhan"
                                            list="lstNguyenNhanRR" 
                                            listKey="sKey"
                                            listValue="sDesc"
                                            headerKey="-1"
                                            headerValue="--- Chọn ---"                                    
                                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                    </s:select>
                            </td>    
                        </s:else>
                        <td style="text-align: center;" class="TUCHOI">
                            <a href="javascript:diennoidung_tuchoi('<s:property value="sSoku"/>')" class="SOKU linkKh">
                                <%--<s:property value='sSoku'/>--%>
                                Từ chối
                            </a>

                            <%--<s:url id="idRejectRisk" value="setRejectRisk.action">--%>
                            <%--<s:param name="soku_reject" value="sSoku"/>--%>                            
                            <%--</s:url>--%>
                            <%--<sj:a targets="divBrowseRisk" href="%{idRejectRisk}">Từ chối</sj:a>--%>
                        </td>
                    </tr>
                </s:iterator>
            </table>

            <sj:submit id="BrowseSubmit" name="BrowseSubmit" 
                       targets="divBrowseRisk" 
                       cssClass="metroButtonStyle" 
                       value="Thêm" onBeforeTopics="batdauduyet" onCompleteTopics="ketthucduyet" cssStyle="display: none"></sj:submit>
        </s:form>
        <s:form action="loadDataRisk.action" id="paginationForm">
            <s:hidden name="nam_xlrr" id="nam_xlrr"/>
            <s:hidden name="trangthai_xlrr" id="trangthai_xlrr"/>
            <s:hidden name="dot_xlrr" id="dot_xlrr"/>
            <s:hidden name="nhom_xlrr" id="nhom_xlrr"/>
            <s:hidden name="poscd" id="poscd"/>
            <s:hidden name="chuongtrinh" id="chuongtrinh"/>
            <s:hidden name="nguon_von" id="nguon_von"/>
            <s:hidden name="vb_xlrr" id="vb_xlrr"/>
            <%@ include file="/xulyruiro/pagination.jsp" %>

            <sj:submit value="submit" id="idSubmit" name="idSubmit" targets="divExportReport" cssStyle="display: none" 
                       onBeforeTopics="batdauloaddata" onCompleteTopics="hoanthanhloaddata"/>
        </s:form>
        <div id="divBrowseRisk"></div>
    </body>
</html>