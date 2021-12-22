<%-- 
    Document   : table_data_risk
    Created on : Jun 20, 2014, 3:55:34 PM
    Author     : LION
--%>

<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/styles-xlrr.css" />
<script src="js/jquery.number.js"></script>
<script src="js/format_num.js"></script>
<html>
    <style>
        th{
            background-color: #DCDCDC;
            border-color: #999;
            height: 20px;
        }
        td{
            border-color: #999;
            height: 22px;
        }
        table.editDelete{
            border-collapse: collapse;
            width: 100%;
            border-color: #999;
        }
        table.editDelete tr:focus{
            background-color:#FFE47A;
        }
    </style>
    <style type="text/css">
        input[type="text"]
        {
            width: 100%;
            border: 0px;
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
            //Cac truong bang so --> se co so truong = 0
            $('.number2').number(true, 0);
            $(".MAKH").css({"width": "100%"});
            $(".MAKH").css({"text-align": "center"});
            $(".DU_NO").css({"width": "100%"});
            $(".TEN_KH").css({"width": "100%"});
            $(".SOKU").css({"width": "100%"});
            $(".TD_CHON").css({"width": "30px"});
            $(".TD_MAKH").css({"width": "80px"});
            $(".TD_MAKH").css({"text-align": "center"});
            $(".TD_DU_NO").css({"width": "95px"});
            $(".TD_LAITON").css({"width": "89px"});
            $(".TD_TEN_KH").css({"width": "170px"});
            $(".TD_SOKU").css({"width": "115px"});
            $(".TD_NGUYEN_NHAN").css({"width": "150px"});
            $(".TD_NGUYEN_NHAN").css({"text-align": "center"});
            $(".TD_NGUYEN_NHAN_KHOANH").css({"width": "120px"});
            $(".TD_CHTRINH").css({"width": "80px"});
        });
        function hienthichitiet(soku, stt) {
            var ht1 = screen.availHeight - 260;
            var wt1 = 1024;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 100;
            var ngay_dcpln = $("#ngay_dcpln").val();
            var totruong_dcpln = $("#totruong_dcpln").val();
            var dvut_dcpln = $("#dvut_dcpln").val();
            var url = "getDetialLoanDcPLN.action?soku_dcpln=" + soku + "&ngay_dcpln=" + ngay_dcpln +
                    "&dvut_dcpln=" + dvut_dcpln + "&totruong_dcpln=" + totruong_dcpln;
            popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        }
        //Disable enter key form submit
        function stopRKey(evt)
        {
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
            if (value == null) {
                alert('Bạn phải nhập dữ liệu cho trường này. Vui lòng kiểm tra lại!');
                return false;
            }
            value = value.replace(/,/g, "");
            var result = true; //Luu ket qua kiem tra kieu so co dung khong
            //Kiem tra xem co nhap kieu so khong
            if (isNaN(parseFloat(value))) {
                result = false;
                //Neu nguoi dung khong nhap dung kieu du lieu. Dua ra canh bao
                alert('Bạn nhập không đúng kiểu số xin nhập lại dữ liệu. Vui lòng kiểm tra lại!');
                focus();
                return false;
            } else {
                //Neu la kieu so --> Kiem tra xem kieu nhap co < 9999999999
                if (parseFloat(value) > 9999999999) {
                    result = false;
                    alert('Giá trị bạn nhập vượt quá giới hạn. Vui lòng kiểm tra lại!');
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

        // Hàm thực hiện Load trường Nguyên nhân không có khả năng trả nợ của Khách hàng nguyennhan_5
        function initSelectOption()
        {
            var pos_cd = '';
            var i = document.frmDataDc.elements.length;
            for (var k = 0; k < i; k++) {
                if (document.frmDataDc.elements[k].name.indexOf('sK_Ma_Ngnhan') > 0) {
                    var id_select = document.frmDataDc.elements[k].id.toString(); //Lay ra id cua select option
                    //Lay ra thu tu cua select option (Value code)
                    var number_id = document.frmDataDc.elements[k].id.toString().substring(10, id_select.toString().length);
                    var id = $("#sNgnhan_Kckntn" + number_id).val();
                    if (id == '01' || id == '02' || id == '03' || id == '04' || id == '05' || id == '06' || id == '07' || id == '08' || id == '09' || id == '10' || id == '11') {
                        document.frmDataDc.elements[k].value = id;
                    } else {
                        document.frmDataDc.elements[k].value = "-1";
                    }
                }
            }
        }
        function ChangeValue_KCKNTN(id, obj) {
            try {
                var tongduno = $('#id_TongDN_' + id.toString()).val();
                if (parseFloat(tongduno) <= 0) {
                    tongduno = $('#id_Tonglaiton_' + id.toString()).val();
                }
                //Kiểm tra trường hợp dư nợ khoanh. Không có KNTN thì vẫn nhập tại cột có khả năng trả nợ
                var duno_khoanh = $('#id_Dnokhoanh_' + id.toString()).val();
                if (parseFloat(duno_khoanh) <= 0)
                {
                    $('#duco_kntn_' + id.toString()).val('0');
                    obj.value = tongduno;
                    $('.number2').number(true, 0);
                    $('.number2').number(true, 0);
                }
            } catch (e) {
                alert('Lỗi thực hiện gán giá trị khi Phân loại khả năng trả nợ của Khách hàng: ' + e.toString());
            }
        }
        function ChangeValue_CKNTN(id, obj) {
            try {
                var tongduno = $('#id_TongDN_' + id.toString()).val();
                var duno_khoanh = $('#id_Dnokhoanh_' + id.toString()).val();
                if (parseFloat(tongduno) <= 0) {
                    tongduno = $('#id_Tonglaiton_' + id.toString()).val();
                }
                if (parseFloat(duno_khoanh) <= 0) {
                    $('#dukhong_kntn_' + id.toString()).val('0');
                    obj.value = tongduno;
                    $('.number2').number(true, 0);
                    $('.number2').number(true, 0);
                }
            } catch (e) {
                alert('Lỗi thực hiện gán giá trị khi Phân loại khả năng trả nợ của Khách hàng: ' + e.toString());
            }
        }
        // Thực hiện kiểm tra hợp lệ sau:
        // 1. Nếu có khả năng trả nợ > 0 hoặc Nếu dư nợ khoanh > 0 ==> Không cho chọn nguyên nhân;
        // 2. Nếu nhập số liều có khả năng hoặc không có khả năng > tổng dư nợ ==> Thông báo và sét lại giá trị nguyên bản
        function on_valib(id, obj)
        {
            try {
                var tongduno = $('#id_TongDN_' + id.toString()).val();
                if (parseFloat(tongduno) <= 0) {
                    tongduno = $('#id_Tonglaiton_' + id.toString()).val();
                }
                var duno_khoanh = $('#id_Dnokhoanh_' + id.toString()).val();
                var duno_kntn_khong = $('#dukhong_kntn_' + id.toString()).val();
                var duno_kntn_co = $('#duco_kntn_' + id.toString()).val();

                if (duno_khoanh > 0)
                {
                    $('#dukhong_kntn_' + id.toString()).attr("disabled", true);
                    $('#ngnhan_kh_' + id.toString()).attr("disabled", false);
                    $('#nguyennhan_' + id.toString()).attr("disabled", true);
                } else
                {
                    $('#dukhong_kntn_' + +id.toString()).attr("disabled", false);
                    $('#ngnhan_kh_' + id.toString()).attr("disabled", true);
                    document.getElementById("ngnhan_kh_" + id.toString()).value = '';
                    $('#nguyennhan_' + id.toString()).attr("disabled", false);
                }
                if (parseFloat(duno_kntn_co) > 0)
                {
                    $('#nguyennhan_' + id.toString()).attr("disabled", true);
                    document.getElementById("nguyennhan_" + id.toString()).value = '-1';
                } else {
                    $('#nguyennhan_' + id.toString()).attr("disabled", false);
                }

                if (parseFloat(duno_kntn_khong) > 0)
                {
                    $('#nguyennhan_' + +id.toString()).attr("disabled", false);
                } else {
                    $('#nguyennhan_' + +id.toString()).attr("disabled", true);
                    document.getElementById("nguyennhan_" + id.toString()).value = '-1';
                }

                if (duno_kntn_co > 0 && (tongduno < duno_kntn_co || tongduno > duno_kntn_co))
                {
                    alert('Bạn không thể điền số tiền Nợ có khả năng trả nợ lớn hơn hoặc nhỏ hơn tổng dư nợ!');
                    document.getElementById("duco_kntn_" + id.toString()).value = tongduno;
                    return;
                }
                if (duno_kntn_khong > 0 && (tongduno < duno_kntn_khong || tongduno > duno_kntn_khong))
                {
                    alert('Bạn không thể điền số tiền Nợ không có khả năng trả nợ lớn hơn hoặc nhỏ hơn tổng dư nợ!');
                    document.getElementById("dukhong_kntn_" + id.toString()).value = tongduno;
                    return;
                }
            } catch (e) {
                alert('Lỗi thực hiện gán giá trị khi Phân loại khả năng trả nợ của Khách hàng: ' + e.toString());
            }
        }
    </script>
    <script type="text/javascript" src="js/pagination.js"></script>
    <body width = "100%">
        <s:form id="frmDataDc" name="frmDataDc" action="saveDataDcPLN.action" theme="simple" align="center">
            </br>
            <table border="1" class="editDelete" align="center">
                <tr>
                    <th rowspan="2">Tổng số KH</th>
                    <th rowspan="2">Tổng số món vay</th>
                    <th rowspan="2">Tổng dư nợ</th>
                    <th colspan="3">Dư nợ</th>
                    <th rowspan="2">Nợ lãi</th>
                </tr>
                <tr>                   
                    <th>Nợ trong hạn</th>
                    <th>Nợ quá hạn</th>
                    <th>Nợ khoanh</th>
                </tr>
                <s:iterator value="#attr.lstViewTotal" var="modelView" status="rowstatus">
                    <tr>                  
                        <td style="text-align: center; color: #007fff; font-weight: bold;"><s:property  value="sSlg_KH" /></td>
                        <td style="text-align: center; color: #007fff; font-weight: bold;"><s:property  value="sSlg_KU" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sTongDN" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sDnothan" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sDnoqhan" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sDnokhoanh" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sTonglaiton" /></td>
                    </tr>
                </s:iterator>
            </table>     
            <s:iterator value="poscd" status="row">
                <s:hidden name="poscd[%{#row.index}]" />
            </s:iterator>
            <s:hidden name="ngay_dcpln" id="ngay_dcpln"/>
            <s:hidden name="dvut_dcpln" id="dvut_dcpln"/>
            <s:hidden name="totruong_dcpln" id="totruong_dcpln"/>
            <s:hidden name="ngvon_dcpln" id="ngvon_dcpln"/>
            <s:hidden name="chtrinh_dcpln" id="chtrinh_dcpln"/>
            <s:hidden name="trangthai" id="trangthai"/>
            </br>
            <table border="1" class="editDelete" align="center">
                <tr>
                    <th width="15" class="TD_CHON" rowspan="3">
                        <s:checkbox id ="allCheck" name="allCheck"/></th>
                    <th class="TD_TEN_KH" rowspan="3">Tên khách hàng</th>
                    <th class="TD_SOKU" rowspan="3">Mã món vay</th>
                    <th class="TD_CHTRINH" rowspan="3">Chương trình</th>
                    <th class="TD_DU_NO" colspan="5">Số liệu tại NHCSXH</th> 
                    <th class="TD_DU_NO" colspan="4">Phân loại khả năng trả nợ</th> 
                    <th class="TD_NGUYEN_NHAN_KHOANH" rowspan="3">Nguyên nhân nợ khoanh (Không có khả năng trả nợ)</th>
                </tr>
                <tr>
                    <th class="TD_DU_NO" colspan="4">Nợ gốc</th> 
                    <th class="TD_LAITON" rowspan="2">Nợ lãi</th> 
                    <th class="TD_DU_NO" rowspan="2">Có khả năng trả nợ</th> 
                    <th class="TD_DU_NO" colspan="3">Không có khả năng trả nợ</th>
                </tr>
                <tr>
                    <th class="TD_DU_NO">Tổng số</th>
                    <th class="TD_DU_NO">Nợ trong hạn</th>
                    <th class="TD_DU_NO">Nợ quá hạn</th>
                    <th class="TD_DU_NO">Nợ khoanh</th>
                    <th class="TD_DU_NO">Số tiền</th>
                    <th class="TD_NGUYEN_NHAN_KHOANH">Nguyên nhân cấp 1</th>
                    <th class="TD_NGUYEN_NHAN_KHOANH">Nguyên nhân cấp 2</th>
                </tr>
                <tr>

                </tr>
                <s:iterator value="#attr.lstDcplnModel" var="modelDcpt" status="rowstatus">
                    <tr class="ac_odd" id="id_tr_<s:property  value="%{#rowstatus.index}" />">
                    <input type="hidden" id="sTk_Casa1_<s:property  value="%{#rowstatus.index}" />" name="sTk_Casa1" value="<s:property  value="sTk_Casa1"/>"/>
                    <input type="hidden" id="sTk_Casa2_<s:property  value="%{#rowstatus.index}" />" name="sTk_Casa2" value="<s:property  value="sTk_Casa2"/>"/>
                    <td align = "center" class="TD_CHON"> 
                        <s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="lstSavePln[%{#rowstatus.index}].sSoku" fieldValue="%{sSoku}"/>
                    </td>
                    <td align = "left" class="TD_TEN_KH">
                        <input type="text" value="<s:property  value="sTenkh" />" 
                               name="sTenkh" class="TEN_KH" onfocus="this.select()" readonly="true"/>
                    </td>
                    <td align = "center" class="TD_SOKU"> 
                        <a href="javascript:hienthichitiet('<s:property value="sSoku"/>','<s:property  value="%{#rowstatus.index}" />' )" class="SOKU linkKh">
                            <s:property value='sSoku'/>
                        </a>
                    </td>
                    <td align = "left" class="TD_CHTRINH">
                        <input type="text" value="<s:property  value="sChtrinh_Tenvt" />" 
                               name="sChtrinh_Tenvt" class="TD_CHTRINH" onfocus="this.select()" readonly="true"/>
                    </td>
                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sTongDN'/>" name="sTongDN" class="DU_NO number2"
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }" onfocus="this.select()" readonly="true" id="id_TongDN_<s:property  value="%{#rowstatus.index}" />"/>
                    </td>
                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sDnothan'/>" name="sDnothan" class="DU_NO number2" 
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }" onfocus="this.select()" readonly="true" id="id_Dnohan_<s:property  value="%{#rowstatus.index}" />"/>
                    </td>

                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sDnoqhan'/>" name="sDnoqhan" class="DU_NO number2"
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }" onfocus="this.select()" readonly="true" id="id_Dnoqhan_<s:property  value="%{#rowstatus.index}" />"/>
                    </td>
                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sDnokhoanh'/>" name="sDnokhoanh" class="DU_NO number2" 
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }" onfocus="this.select()" readonly="true" id="id_Dnokhoanh_<s:property  value="%{#rowstatus.index}" />"/>
                    </td>
                    <td align = "right" class="TD_LAITON">
                        <input type="text" value="<s:property value='sTonglaiton'/>" name="sTonglaiton" class="DU_NO number2" 
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }" onfocus="this.select()" readonly="true" id="id_Tonglaiton_<s:property  value="%{#rowstatus.index}" />"/>
                    </td>

                    <!--chi tieu nhap tay tu day--> 
                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sC_Kntn_Sodu'/>" name="lstSavePln[<s:property  value="%{#rowstatus.index}" />].bC_Kntn_Sodu" class="DU_NO number2" 
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }
                                       isNumber(this.value);
                                       on_valib(<s:property  value="%{#rowstatus.index}" />, this)" onfocus="this.select()" style="background-color: #FFCCBA"
                               id="duco_kntn_<s:property  value="%{#rowstatus.index}" />" onclick="ChangeValue_CKNTN(<s:property  value="%{#rowstatus.index}" />, this);
                                       on_valib(<s:property  value="%{#rowstatus.index}" />, this)"/>
                    </td>

                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sK_Kntn_Sodu'/>" name="lstSavePln[<s:property  value="%{#rowstatus.index}" />].bK_Kntn_Sodu" class="DU_NO number2" 
                               onblur="if (this.value == '') {
                                           this.value = 0
                                       }
                                       isNumber(this.value);
                                       on_valib(<s:property  value="%{#rowstatus.index}" />, this)" onfocus="this.select()" style="background-color: #FFCCBA"
                               id="dukhong_kntn_<s:property  value="%{#rowstatus.index}" />" onclick="ChangeValue_KCKNTN(<s:property  value="%{#rowstatus.index}" />, this); on_valib(<s:property  value="%{#rowstatus.index}" />, this)"/>
                    </td>

                    <td align = "left" class="TD_NGUYEN_NHAN_KHOANH">
                        <input type="hidden" id="sNgnhan_Kckntn_<s:property  value="%{#rowstatus.index}" />" 
                               name="Ngnhan_Kckntn_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="sNgnhan_Kckntn"/>"/>
                        <s:select  
                            id="nguyennhan_%{#rowstatus.index}"
                            name="lstSavePln[%{#rowstatus.index}].sK_Ma_Ngnhan"
                            list="lstDMNgNhan" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            onchange="reLoadValue(this.value,'nguyennhanc2_%{#rowstatus.index}')"
                            headerValue="--- Chọn ---"
                            cssStyle="width: 120px;vertical-align: middle;background-color: #FFCCBA;">
                        </s:select>
                    </td>   
                    <td align = "left" class="TD_NGUYEN_NHAN_KHOANH">
                        <input type="hidden" id="sNgnhan_Kckntn_<s:property  value="%{#rowstatus.index}" />" 
                               name="Ngnhan_Kckntn_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="sNgnhan_Kckntn"/>"/>
                        <s:select  
                            id="nguyennhanc2_%{#rowstatus.index}"
                            name="lstSavePln[%{#rowstatus.index}].sK_Ma_NgnhanC2"
                            list="lstDMNgNhanC2" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            headerValue="--- Chọn ---"
                            cssStyle="width: 120px;vertical-align: middle;background-color: #FFCCBA;">
                        </s:select>
                        <s:select  
                            id="nguyennhanc2_%{#rowstatus.index}_data"
                            list="lstDMNgNhanC2" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            headerValue="--- Chọn ---"
                            cssStyle="display:none;">
                        </s:select>
                    </td>       

                    <td align = "left" class="TD_NGUYEN_NHAN_KHOANH">
                        <input type="text" value="<s:property value='sK_Ngnhan_Kh'/>" name="lstSavePln[<s:property  value="%{#rowstatus.index}" />].sK_Ngnhan_Kh" class="DU_NO" 
                               onfocus="this.select()" style="background-color: #FFCCBA" 
                               id="ngnhan_kh_<s:property  value="%{#rowstatus.index}" />"/>
                    </td>
                </tr>
            </s:iterator>
        </table>
        <sj:submit id="idSaveDcNo" name="idSaveDcNo" 
                   targets="divBrowseRisk" 
                   cssClass="metroButtonStyle" 
                   value="Thêm" onBeforeTopics="batdauduyet" onCompleteTopics="ketthucduyet" cssStyle="display: none"></sj:submit>
    </s:form>
    <s:form action="loadDataDcPLN.action" id="paginationForm">
        <s:iterator value="poscd" status="row">
            <s:hidden name="poscd[%{#row.index}]" />
        </s:iterator>
        <s:hidden name="ngay_dcpln" id="ngay_dcpln"/>
        <s:hidden name="dvut_dcpln" id="dvut_dcpln"/>
        <s:hidden name="totruong_dcpln" id="totruong_dcpln"/>
        <s:hidden name="ngvon_dcpln" id="ngvon_dcpln"/>
        <s:hidden name="chtrinh_dcpln" id="chtrinh_dcpln"/>
        <s:hidden name="trangthai" id="trangthai"/>
        <%@ include file="/dc_plno/pagination.jsp" %>
        <sj:submit value="submit" id="idSubmit" name="idSubmit" targets="divExportReport" cssStyle="display: none" 
                   onBeforeTopics="batdauloaddata" onCompleteTopics="hoanthanhloaddata"/>
    </s:form>
    <div id="divBrowseRisk"></div>
</body>
<script>
    initSelectOption();

    function reLoadValue(val,idNgnhan) {
         var var2;
         $("[id=" + idNgnhan + "]").children().remove().end();
         $("[id=" + idNgnhan + "_data] > option").each(function() {
            var2 = $(this).text().substr(0, 2);
            if(val.trim() == var2.trim()){
                $("[id=" + idNgnhan + "]").prepend("<option value='" + $(this).val() + "'> " + $(this).text() + " </option>");
            }
         });
         $("[id=" + idNgnhan + "]").prepend("<option value='-1' selected='selected'>--- Chọn ---</option>");
    }
</script>
</html>
