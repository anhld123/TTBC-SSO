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
            width: 100%;
            border-color: #999;
        }
        table.editDelete tr:hover{
            /*background-color:#FFE47A;*/
            /*cursor: pointer;*/
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
            $(".TD_DU_NO").css({"width": "70px"});
            $(".TD_TEN_KH").css({"width": "160px"});
            $(".TD_SOKU").css({"width": "115px"});


            $(".TD_NGUYEN_NHAN").css({"width": "150px"});
            $(".TD_NGUYEN_NHAN").css({"text-align": "center"});
            $("#idsearch_soku").val('');
        });
        function hienthichitiet(soku) {
            var ht1 = screen.availHeight - 100;
            var wt1 = 1050;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 10;

            var ngay_dcpt = $("#ngay_dcpt").val();
            var totruong_dcpt = $("#totruong_dcpt").val();
             var dvut_dcpt=$("#dvut_dcpt").val();
//            var url = "getDetialCustomerDcNo.action?soku=" + soku + "&ngay_dcpt=" + ngay_dcpt + "&totruong_dcpt=" + totruong_dcpt;
//            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
//            var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            var url = "getDetialCustomerPtNo.action?soku=" + soku + "&ngay_dcpt=" + ngay_dcpt + 
                    "&dvut_dcpt=" + dvut_dcpt+ "&totruong_dcpt=" + totruong_dcpt;
            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
            var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
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
                if (parseFloat(value) < 0) {
                    result = false;
                    alert('Bạn không được nhập giá trị < 0!');
                    //Dua ra canh bao
//                        $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn không được nhập giá trị < 0!');
                    focus();
                    return false;
                }

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
        
        function initSelectOption()
            {
                var pos_cd = '';
                var i = document.frmDataPt.elements.length;
                for (var k = 0; k < i; k++)
                {
                    if (document.frmDataPt.elements[k].name.indexOf('sMaNN')>0)
                    {
                        var id=document.frmDataPt.elements[k].id;
//                        $('#'+id).val(id);
                        document.frmDataPt.elements[k].value=id;
//                       console.log('id='+document.frmDataPt.elements[k].value);
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
    <script>
        function js_set_total(value, obj) {
            try
            {
                var tongtien_tmp = document.getElementById("lstsavePtno_" + value + "_sTongsotien").value;                              
                var khoanh_tmp = document.getElementById("lstsavePtno_" + value + "_sKhoanh").value;
                var khoanh = parseFloat(khoanh_tmp.replace(/,/g, ""));
                var hieuso = parseFloat(tongtien_tmp.replace(/,/g, "") - khoanh_tmp.replace(/,/g, ""));
//                alert(hieuso);
                if(hieuso == 0 || khoanh == 0)
                { 
                    var element_id_1 = "lstsavePtno_" + value + "_sTongsotien";
                    var input_object1 = document.getElementById(element_id_1).value;
                    $(obj).val(input_object1);
    //                alert(obj.id);

                    var element_id_2 = "lstsavePtno_" + value + "_bCol8";
                    var element_id_3 = "lstsavePtno_" + value + "_bSotien";

                    var id_in = obj.id;
                    if (id_in == element_id_2)
                        document.getElementById(element_id_3).value = 0;
                    if (id_in == element_id_3)
                        document.getElementById(element_id_2).value = 0;
                }
    //                $("#" + element_id_1).focusin(function() {
//
//                    alert("I am in Focus");
//
//                });
//                $("#" + element_id_1).
//                if ($("#" + element_id_1).is(":focus"))
//                    alert('Dang thai dang o ' + element_id_1);
//                var element_id_2 = "lstsavePtno_" + value + "_bCol8";
//                var input_object2 = document.getElementById(element_id_2);
////                 alert(element_id_2+' '+$('#'+element_id_2).select().is()+' '+obj);
//                input_object2.value = input_object1;
//
//                var element_id_3 = "lstsavePtno_" + value + "_bSotien";
//                var input_object3 = document.getElementById(element_id_3);
//
//                input_object3.value = 0;
//                alert( $(obj).val())
            }
            catch (e)
            {
                alert(e.toString());
            }
        }

        function js_set_total1(value) {
            try
            {
                var tongtien_tmp = document.getElementById("lstsavePtno_" + value + "_sTongsotien").value;                              
                var khoanh_tmp = document.getElementById("lstsavePtno_" + value + "_sKhoanh").value;
                var khoanh = parseFloat(khoanh_tmp.replace(/,/g, ""));
                var hieuso = parseFloat(tongtien_tmp.replace(/,/g, "") - khoanh_tmp.replace(/,/g, ""));
//                alert(hieuso);
                if(hieuso == 0 || khoanh == 0)
                {    
                    var element_id_1 = "lstsavePtno_" + value + "_sTongsotien";
                    var input_object1 = document.getElementById(element_id_1).value;

                    var element_id_2 = "lstsavePtno_" + value + "_bSotien";
                    var input_object2 = document.getElementById(element_id_2);
                    input_object2.value = input_object1;

                    var element_id_3 = "lstsavePtno_" + value + "_bCol8";
                    var input_object3 = document.getElementById(element_id_3);

                    input_object3.value = 0;
                }
            }
            catch (e)
            {
                alert(e.toString());
            }
        }
        function on_valib(value)
        {
            try
            {
                var tongtien_tmp = document.getElementById("lstsavePtno_" + value + "_sTongsotien").value;
                var tongtien = parseFloat(tongtien_tmp.replace(/,/g, ""));
                
                var khoanh_tmp = document.getElementById("lstsavePtno_" + value + "_sKhoanh").value;
                var khoanh = parseFloat(khoanh_tmp.replace(/,/g, ""));

                var sotien_tmp = document.getElementById("lstsavePtno_" + value + "_bSotien").value;
                var sotien = parseFloat(sotien_tmp.replace(/,/g, ""));
                var sotien_cknt_tmp = document.getElementById("lstsavePtno_" + value + "_bCol8").value;
                var sotien_cknt = parseFloat(sotien_cknt_tmp.replace(/,/g, ""));
                var hieuso = parseFloat(tongtien_tmp.replace(/,/g, "") - khoanh_tmp.replace(/,/g, ""));
                var tongso = parseFloat(sotien_cknt_tmp.replace(/,/g, "")) + parseFloat(sotien_tmp.replace(/,/g, ""));
//                alert(tongso);
                if(hieuso == 0 || khoanh == 0)
                {
                    if (sotien_cknt > 0 && (tongtien < sotien_cknt || tongtien > sotien_cknt))
                    {
                        alert('Bạn không thể điền số tiền Nợ có khả năng thu hồi lớn hơn hoặc nhỏ hơn tổng dư nợ !');
                        document.getElementById("lstsavePtno_" + value + "_bCol8").value = document.getElementById("lstsavePtno_" + value + "_sTongsotien").value;
                        return;
                    }

                    if (sotien > 0 && (tongtien < sotien || tongtien > sotien))
                    {
                        alert('Bạn không thể điền số tiền Không có khả năng thu hồi lớn hơn hoặc nhỏ hơn tổng dư nợ !');
                        document.getElementById("lstsavePtno_" + value + "_bSotien").value = document.getElementById("lstsavePtno_" + value + "_sTongsotien").value;
                        return;
                    }
                }
                else
                {
                    if (tongso != tongtien)
                    {
                        alert('Bạn không thể điền số tiền Không có khả năng thu hồi lớn hơn hoặc nhỏ hơn tổng dư nợ !');
//                        document.getElementById("lstsavePtno_" + value + "_bCol8").value = document.getElementById("lstsavePtno_" + value + "_sTongsotien").value;
//                        document.getElementById("lstsavePtno_" + value + "_bSotien").value = 0;
                        return;
                    }
                }
                
            }
            catch (e)
            {
                alert(e.toString());
            }
        }
//         function js_update_zero(value){
////                alert(value);
//                
//
//                var element_id_3 = "lstsavePtno_" + value + "_bSotien";
//                var input_object3 = document.getElementById(element_id_3); 
//                
//                input_object3.value = 0;
//
//            };

//            function js_update_zero2(value){
////                alert(value);
//                var element_id_1 = "lstsavePtno_" + value + "_bCol8";
////                var element_id_2 = "lstsavePtno_" + value + "_bCol9";
//                var input_object1 = document.getElementById(element_id_1);    
////                var input_object2 = document.getElementById(element_id_2);   
//                input_object1.value = 0;
////                input_object2.value = 0;
////                alert(input_object2.value);
//            }

    </script>
    <script type="text/javascript" src="js/pagination.js">
    </script>

    <body width = "100%">

        <s:form id="frmDataPt" name="frmDataPt" action="saveDataPt.action" theme="simple" align="center">
            </br>
            <table border="1" class="editDelete" align="center">
                <tr>
                    <th rowspan="2">Tổng số món</th>
                    <th rowspan="2">Tổng tiền</th>
                    <th colspan="3">Dư nợ</th>
                </tr>
                <tr>                   
                    <th>Nợ trong hạn</th>
                    <th>Nợ quá hạn</th>
                    <th>Nợ khoanh</th>
                </tr>
                <s:iterator value="#attr.lstViewTotalCust" var="modelView" status="rowstatus">
                    <tr>                  
                        <td style="text-align: center; color: #007fff; font-weight: bold;"><s:property  value="sSoKh" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sTongtien" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sNothan" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sNoqhan" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sNokhoanh" /></td>
                    </tr>
                </s:iterator>
            </table>     
            <s:iterator value="poscd" status="row">
                <s:hidden name="poscd[%{#row.index}]" />
            </s:iterator>
            <s:hidden name="ngay_dcpt" id="ngay_dcpt"/>
            <s:hidden name="dvut_dcpt" id="dvut_dcpt"/>
            <s:hidden name="totruong_dcpt" id="totruong_dcpt"/>
            <s:hidden name="nguon_von" id="nguon_von"/>
            <s:hidden name="chuongtrinh" id="chuongtrinh"/>
            <s:hidden name="trangthai" id="trangthai"/>
            </br>
            <table border="1" class="editDelete" align="center">
                <tr>
                    <th width="15" class="TD_CHON" rowspan="3">
                        <s:checkbox id ="allCheck" name="allCheck"/></th>
                    
                    <th class="TD_TEN_KH" rowspan="3">Tên khách hàng</th>
                    <th class="TD_SOKU" rowspan="3">Mã món vay</th>                    
                    <th class="TD_DU_NO" colspan="4">Tổng dư nợ</th>                       
                    <th class="TD_DU_NO" colspan="7" >Phân tích nợ</th> 
                </tr>
                <tr>
                    <th class="TD_DU_NO" rowspan="2">Tổng số</th> 
                    <th class="TD_DU_NO" colspan="3">Trong đó</th> 
                    <th class="TD_DU_NO" rowspan="2">Nợ có khả năng thu hồi</th> 
                    <th class="TD_DU_NO" colspan="6">Nợ không có khả năng thu hồi</th> 
                </tr>
                <tr>
                    <th class="TD_DU_NO"  >Nợ trong hạn</th>
                    <th class="TD_DU_NO" >Nợ quá hạn</th>                  
                    <th class="TD_DU_NO">Nợ khoanh</th>                        
                    <th class="TD_DU_NO">Nguyên nhân</th> 
                    <th class="TD_DU_NO">Số tiền</th> 
                </tr>
                <s:iterator value="#attr.lstModelDcptNo" var="modelDcpt" status="rowstatus">
                    <s:if test="#rowstatus.even == true">
                        <tr class="ac_odd">
                        </s:if>
                        <s:else>
                        <tr class="ac_odd">
                        </s:else>
<!--                        <td align = "center" class="TD_CHON"> 
                            <%--<s:checkbox id ="check_legacyid" cssClass="checkbox1" name="lstsavePtno[%{#rowstatus.index}].sSoku" fieldValue="%{sSoku}"/>--%>
                        </td>-->
                        <td align = "center" class="TD_CHON"> 
                        <s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="lstsavePtno[%{#rowstatus.index}].sSoku" fieldValue="%{sSoku}"/>
                        </td>
                        
                        <td align = "left" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="sTenkh" />" 
                                   name="sTenkh" class="TEN_KH" onfocus="this.select()" />
                        </td>

                        <td align = "center" class="TD_SOKU"> 
                            <a href="javascript:hienthichitiet('<s:property value="sSoku"/>')" class="SOKU linkKh">
                                <s:property value='sSoku'/>
                            </a> 
                            <!--lstsaveDcno[%{#rowstatus.index}].sSoku-->
                            <%--<s:hidden name="lstsaveDcno[%{#rowstatus.index}].sSoku" value="sSoku"/>--%>
                            <!--<input type="hidden" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].sSoku" value="<s:property value='sSoku'/>" id="<s:property value='sSoku'/>"/>-->
                        </td>
                        
                        

                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sTongsotien'/>" name="lstsavePtno[<s:property  value="%{#rowstatus.index}" />].sTongsotien" class="DU_NO number2"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true"
                                   id="lstsavePtno_<s:property  value="%{#rowstatus.index}" />_sTongsotien"/>
                        </td>
                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sDno_Than'/>" name="sDno_Than" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true"/>
                        </td>

                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sDno_Qhan'/>" name="sDno_Qhan" class="DU_NO number2"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true"/>
                        </td>
                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sDno_Khoanh'/>" name="sDno_Khoanh" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true"
                                           id="lstsavePtno_<s:property  value="%{#rowstatus.index}" />_sKhoanh"/>
                        </td>

                        <!--chi tieu nhap tay tu day--> 

                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sDno_Chayy'/>" name="lstsavePtno[<s:property  value="%{#rowstatus.index}" />].bCol8" class="DU_NO number2" 
                                   onblur="if (this.value === '') {
                                               this.value = 0
                                           }
                                           isNumber(this.value);
                                           on_valib(<s:property  value="%{#rowstatus.index}" />)" 
                                   onfocus="this.select();
                                           js_set_total(<s:property  value="%{#rowstatus.index}" />, this);" 
                                   style="background-color: #FFCCBA"    
                                   onclick = "js_set_total(<s:property  value="%{#rowstatus.index}" />, this);"                                                                                                   
                                   id="lstsavePtno_<s:property  value="%{#rowstatus.index}" />_bCol8"
                                   />
                        </td>

                        <!--onselect="alert('onselect Tungnv test thu');"-->

                        <td align = "center" class="TD_MAKH">
                            <select name="lstsavePtno[<s:property  value="%{#rowstatus.index}" />].sMaNN" class="TD_MAKH" 
                                    id="<s:property value='sMaNN'/>" >  
                                <option value="0" selected>.... </option>
                                <option value="1">1 - Theo QĐ 15...</option>
                                <option value="2">2 - SXKD thua lỗ,...</option>
                                <option value="3">3 - Hộ vay bỏ nơi cư trú</option>
                                <option value="4">4 - Người vay đi tù</option>
                                <option value="5">5 - Không có người nhận nợ</option>
                                <option value="6">6 - Nguyên nhân khác</option>                          
                            </select>

                        </td>

                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sSotien'/>" name="lstsavePtno[<s:property  value="%{#rowstatus.index}" />].bSotien" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           isNumber(this.value);
                                           on_valib(<s:property  value="%{#rowstatus.index}" />)" 
                                   onfocus="this.select();
                                           js_set_total(<s:property  value="%{#rowstatus.index}" />, this);" style="background-color: #FFCCBA"
                                   onclick = "js_set_total1(<s:property  value="%{#rowstatus.index}" />);" 
                                   id="lstsavePtno_<s:property  value="%{#rowstatus.index}" />_bSotien"/>
                        </td>
                    </tr>
                </s:iterator>
            </table>


            <sj:submit id="idSavePtNo" name="idSavePtNo" 
                       targets="divBrowseRisk" 
                       cssClass="metroButtonStyle" 
                       value="Thêm" onBeforeTopics="batdauduyet" onCompleteTopics="ketthucduyet" cssStyle="display: none"></sj:submit>
        </s:form>
        <s:form action="loadDataPtNo.action" id="paginationForm">
            <s:iterator value="poscd" status="row">
                <s:hidden name="poscd[%{#row.index}]" />
            </s:iterator>
            <s:hidden name="ngay_dcpt" id="ngay_dcpt"/>
            <s:hidden name="dvut_dcpt" id="dvut_dcpt"/>
            <s:hidden name="totruong_dcpt" id="totruong_dcpt"/>
            <s:hidden name="nguon_von" id="nguon_von"/>
            <s:hidden name="chuongtrinh" id="chuongtrinh"/>
            <s:hidden name="trangthai" id="trangthai"/>
            <%@ include file="/dcpt_no/pagination.jsp" %>
            <sj:submit value="submit" id="idSubmit" name="idSubmit" targets="divExportReport" cssStyle="display: none" 
                       onBeforeTopics="batdauloaddata" onCompleteTopics="hoanthanhloaddata"/>
        </s:form>
        <div id="divBrowseRisk"></div>
        <script>
            initSelectOption();
        </script>
    </body>
</html