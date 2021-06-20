<%-- 
    Document   : risk_detail_customer
    Created on : May 27, 2015, 8:50:46 AM
    Author     : BAOANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin chi tiết khách hàng xử lý rủi ro từ chối của chi nhánh</title>
        <sx:head/>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script type="text/javascript" src="js/pagination.js"></script>
        <style>      
            *{
                font: 14px Arial, Helvetica, sans-serif;
            }

            #divChiTieu{
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width:99%; 
                height: 99%; 
                margin: 0px auto 10px auto; 
                /*padding: 10px;*/
                background-color: #E2E8C9;
                /*overflow: scroll;*/
                /*border: 1px solid;*/
            }
            #container_popup{
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width: 100%;
                height:96vh; 
                /*border: 1px solid;*/
                padding-left: 0px;     
                background-color: #E2E8C9;
            }
            #idTitle{
                font-family: Verdana,Arial,Tahoma,Helvetica;
                font-size: 13pt;
                font-weight: bold;
                color: #116600;
                /*border: 1px solid;*/
            }

            #divSave{
                text-align: right;
            }

            table{
                border-collapse: collapse;
                width: 100%;
                line-height: 24px;
                /*border: 1px solid;*/
            }
            .tbhead th{
                background-color: #DCDCDC;
                text-align: center;
                font-weight: normal;
                padding: 2px;
            }
            .cscontent td{
                background-color: white;
                text-align: center;
                padding-left:5px;
                padding-top:5px;
                padding-bottom: 5px;
            }

            input{
                border: 0px;
            }

            input[type="text"]
            {
                width: 98%;
            }

            .maPGD{
                width: 150px;
            }

            .tenPGD{
                width: 200px;
            }

            textarea:focus {
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                /*                padding: 3px 0px 3px 3px;
                                margin: 5px 1px 3px 0px;*/
                border: 1px solid rgba(81, 203, 238, 1);
            }
        </style>
        <script>
            $(document).ready(function() {
                //Format cac truong input.number can le phai
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $('.maPGD').css({"text-align": "left"});
                $('.tenPGD').css({"text-align": "right"});
//                $(".maPGD").css({"width": "150px"});
//                $(".maPGD").css({"width": "140px"});
//                $('#sNguyennhan_kdc').hide();
            });</script>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script language="javascript">
            $(document).ready(function() {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                
                $(".MAKH").css({"width": "100%"});
                $(".MAKH").css({"text-align": "center"});
                $(".DU_NO").css({"width": "100%"});
                $(".TEN_KH").css({"width": "100%"});
                $(".SOKU").css({"width": "100%"});

                $(".TD_MAKH").css({"width": "80px"});
                $(".TD_MAKH").css({"text-align": "center"});
                $(".TD_DU_NO").css({"width": "70px"});
                $(".TD_TEN_KH").css({"width": "130px"});
                $(".TD_SOKU").css({"width": "115px"});


                $(".TD_NGUYEN_NHAN").css({"width": "200px"});
                $(".TD_NGUYEN_NHAN").css({"text-align": "center"});


                $("#idRejecttmp").click(function()
                {
                    $('#divBrowseRisk').empty();
                    var params = {};
                    var arr = [];

                    var soku = $("#soku").val();
                    var sNogoc_Clech = $("#sNogoc_Clech").val();
                    var sNolai_Clech = $("#sNolai_Clech").val();
                    var sSoducasa = $("#sSoducasa").val();
                    var sNguyennhan_Lech = $("#sNguyennhan_Lech").val();
                    var sThuctrang_Dtdt = $("#sThuctrang_Dtdt").val();
                    var ngay_dcpt = $("#ngay_dcpt").val();
                    var totruong_dcpt = $("#totruong_dcpt").val();
                    var sNguyennhan_kdc = $.trim($("#sNguyennhan_kdc").val());
                    var len_nn_kdc = $.trim($("#sNguyennhan_kdc").val()).length;

                    if ($.trim($("#sThuctrang_Dtdt").val()).length < 1)
                    {
                        alert('Bạn phải nhập thực trạng đầu tư trước khi lưu dữ liệu !');
                        $("#sThuctrang_Dtdt").focus();
                        return;
                    }



                    var ma_nguyennhan = $("#ma_nguyennhan").val();
                    if (ma_nguyennhan == '06')
                    {
                        if (len_nn_kdc < 1)
                        {
                            alert('Bạn phải nhập nguyên nhân không đối chiếu được !');
                            $("#sNguyennhan_kdc").focus();
                            return;
                        }
                    }
                    var p1;
                    if (ma_nguyennhan == '-1')
                    {
                        if (parseFloat($('#sNogoc_Clech').val().replace(/,/g, "")) +
                                parseFloat($('#sNolai_Clech').val().replace(/,/g, "")) +
                                parseFloat($('#sSoducasa').val().replace(/,/g, "")) > 0 && $.trim($("#sNguyennhan_Lech").val()).length < 1)
                        {
                            alert('Bạn phải nhập nguyên nhân chênh lệch trước khi lưu dữ liệu !');
                            $("#sNguyennhan_Lech").focus();
                            return;
                        }
                        p1 = {ngay_dcpt: ngay_dcpt,
                            totruong_dcpt: totruong_dcpt,
                            lstsaveDcno: [
                                {"sSoku": soku,
                                    "bNogoc": sNogoc_Clech,
                                    "bNolai": sNolai_Clech,
                                    "bDuCasa": sSoducasa,
                                    "sNguyennhan": sNguyennhan_Lech,
                                    "sTtDautu": sThuctrang_Dtdt,
                                    "sTrangthai_dc": "Y"}
                            ]
                        };
                    }
                    else {
                        if (ma_nguyennhan == '06')
                        {
                            p1 = {ngay_dcpt: ngay_dcpt,
                                totruong_dcpt: totruong_dcpt,
                                lstsaveDcno: [
                                    {"sSoku": soku,
                                        "bNogoc": sNogoc_Clech,
                                        "bNolai": sNolai_Clech,
                                        "bDuCasa": sSoducasa,
                                        "sNguyennhan": sNguyennhan_kdc,
                                        "sTtDautu": sThuctrang_Dtdt,
                                        "sTrangthai_dc": "R"}
                                ]
                            };
                        }
                        else
                        {
                            var ngnhan = $("#ma_nguyennhan option:selected").text();

                            //$("#ma_nguyennhan").text();
//                            alert(ngnhan);
                            p1 = {ngay_dcpt: ngay_dcpt,
                                totruong_dcpt: totruong_dcpt,
                                lstsaveDcno: [
                                    {"sSoku": soku,
                                        "bNogoc": sNogoc_Clech,
                                        "bNolai": sNolai_Clech,
                                        "bDuCasa": sSoducasa,
                                        "sNguyennhan": ngnhan,
                                        "sTtDautu": sThuctrang_Dtdt,
                                        "sTrangthai_dc": "R"}
                                ]
                            };
                        }
                    }

//                    console.log(p1);
                    var url = "saveDataDcDetail.action?soku=" + soku + "&ngay_dcpt=" + ngay_dcpt + "&totruong_dcpt=" + totruong_dcpt;
                    var data1 = JSON.stringify(p1);//"soku=" + soku + "&ngay_dcpt=" + ngay_dcpt + "&totruong_dcpt=" + totruong_dcpt;
                    $.ajax({
                        url: url,
                        data: data1,
                        dataType: 'json',
                        contentType: 'application/json',
                        type: 'POST',
                        async: true,
                        success: function(data) {

                            try {
                                alert("Bạn đã lưu dữ liệu thành công ");
//                                self.opener.document.forms['paginationForm'].idSubmit.click();
                                window.onunload = function(e) {
                                    opener.reLoadForm('Dang thur thoi');
                                };
                                window.close();

                            }
                            catch (e)
                            {
                                alert(e.toString());
                            }

                        },
                        error: function(data)
                        {
//                            alert(data.error);
                            alert('Bạn lưu dữ liệu lỗi xin liên hệ với quản trị để được hỗ trợ ');
                            $('#divBrowseRisk').html("<h2 style='color: red'>Bạn lưu dữ liệu lỗi xin liên hệ với quản trị để được hỗ trợ ! </h2>");
                        }
                    });
                    return false;
                });
            });

            function closeSelf() {
                window.close();
                return true;
            }

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
//                    if (parseFloat(value) < 0) {
//                        result = false;
//                        alert('Bạn không được nhập giá trị < 0!');
//                        //Dua ra canh bao
////                        $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn không được nhập giá trị < 0!');
//                        focus();
//                        return false;
//                    }

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

            function disable_nn()
            {
//                alert('vao ham disable_nn');
                $('#divBrowseRisk').empty();
                var ma_nguyennhan = $("#ma_nguyennhan").val();
                if (ma_nguyennhan != '-1')
                {
                    $("#ma_nguyennhan").val('-1');
                    $('#sNguyennhan_kdc').hide();
                    $("#sNguyennhan_Lech").removeAttr("readonly");
                    $("#sNguyennhan_Lech").css("background", "#ffffff");
                    $("#sNguyennhan_kdc").val('');
                    $("#sThuctrang_Dtdt").removeAttr("readonly");
                    $("#sNogoc_Clech").removeAttr("readonly");
                    $("#sNolai_Clech").removeAttr("readonly");
//                    $("#sSoducasa").removeAttr("readonly");

                    $("#sThuctrang_Dtdt").css("background", "#ffffff");
                    $("#sNogoc_Clech").css("background", "#FFCCBA");
                    $("#sNolai_Clech").css("background", "#FFCCBA");
//                    $("#sSoducasa").css("background", "#FFCCBA");
                    if ($.trim($("#sTk_Casa1").val()).length > 0 || $.trim($("#sTk_Casa2").val()).length > 0)
                    {
                        $("#sSoducasa").removeAttr("readonly");
                        $("#sSoducasa").css("background", "#FFCCBA");
                    }
//                    alert('Để nhập được dữ liệu trường này bạn phải để trắng dữ liệu nhập vào nguyên nhân không đối chiếu được-' + sNguyennhan_kdc + '-');
//                    $('#divBrowseRisk').html("<h2 style='color: red'>Để nhập được dữ liệu trường này bạn phải để trắng dữ liệu nhập vào nguyên nhân không đối chiếu được ! </h2>");
                }
//                else
//                {
//                    $("#sNguyennhan_kdc").attr("readonly", "readonly");
//                    $("#sNguyennhan_kdc").css("background", "#CCCCCC");
//                }
            }

            function onchan_sel()
            {
//                alert('vao ham onchan_sel');
                $('#divBrowseRisk').empty();

                var sNguyennhan_Lech = $.trim($("#sNguyennhan_Lech").val()).length;
                if (sNguyennhan_Lech > 0)
                {
//                    alert('Bạn đã nhập dữ liệu nguyên nhân chênh lệch nên không thể nhập dữ liệu cho trường này');
                    $('#divBrowseRisk').html("<h2 style='color: red'>Bạn đã nhập dữ liệu nguyên nhân chênh lệch nên không thể nhập dữ liệu cho trường này ! </h2>");
                    $("#ma_nguyennhan").val('-1');
                    return;
                }

                var ma_nguyennhan = $("#ma_nguyennhan").val();
                if (ma_nguyennhan != '-1')
                {

                    $("#sNogoc_Clech").val('0');
                    $("#sNolai_Clech").val('0');
                    $("#sSoducasa").val('0');

                    $("#sNguyennhan_Lech").attr("readonly", "readonly");
                    $("#sNogoc_Clech").attr("readonly", "readonly");
                    $("#sNolai_Clech").attr("readonly", "readonly");
                    $("#sSoducasa").attr("readonly", "readonly");

                    $("#sNguyennhan_Lech").css("background", "#CCCCCC");
                    $("#sNogoc_Clech").css("background", "#CCCCCC");
                    $("#sNolai_Clech").css("background", "#CCCCCC");
                    $("#sSoducasa").css("background", "#CCCCCC");

                    if (ma_nguyennhan == '06')
                    {
                        $('#sNguyennhan_kdc').show();
                    }
                    else
                        $('#sNguyennhan_kdc').hide();
                }
                if (ma_nguyennhan == '-1')
                {
                    $('#sNguyennhan_kdc').hide();
                    if ($.trim($("#sTk_Casa1").val()).length > 0 || $.trim($("#sTk_Casa2").val()).length > 0)
                    {
                        $("#sSoducasa").removeAttr("readonly");
                        $("#sSoducasa").css("background", "#FFCCBA");
                    }
                    $("#sNguyennhan_kdc").val('');
                    $("#sNguyennhan_Lech").removeAttr("readonly");
                    $("#sThuctrang_Dtdt").removeAttr("readonly");
                    $("#sNogoc_Clech").removeAttr("readonly");
                    $("#sNolai_Clech").removeAttr("readonly");

                    $("#sNguyennhan_Lech").css("background", "#ffffff");
                    $("#sThuctrang_Dtdt").css("background", "#ffffff");
                    $("#sNogoc_Clech").css("background", "#FFCCBA");
                    $("#sNolai_Clech").css("background", "#FFCCBA");
                }
            }
        </script>
    </head>
    <body>
        <div id="container_popup" style="">
            <s:form name="frmdataDcNo" id="frmdataDcNo" action="saveDataDcDetail.action" theme="simple">
                <s:hidden name="soku" id="soku"/>
                <s:hidden name="ngay_dcpt" id="ngay_dcpt"/>
                <s:hidden name="totruong_dcpt" id="totruong_dcpt"/>
                <div id="divChiTieu" style="text-align: center;">
                    <span id="idTitle">Thông tin chi tiết khách hàng đối chiếu nợ</span>
                    <hr/>
                    <s:iterator value="#attr.lstModelDcptNo" var="modelDcpt" status="rowstatus">
                        <input type="hidden" id="sTk_Casa1" name="sTk_Casa1" value="<s:property  value="sTk_Casa1"/>"/>
                        <input type="hidden" id="sTk_Casa2" name="sTk_Casa2" value="<s:property  value="sTk_Casa2"/>"/>
                        <input type="hidden" id="sTrangthai" name="sTrangthai" value="<s:property  value="sTrangthai_Dc"/>"/>
                         <input type="hidden" id="thunhe" name="thunhe" value="thunhe"/>
                        <table border="1" class="editDelete" align="center">
                            <tr>
                                <!--<th class="TD_MAKH" rowspan="3">Mã KH</th>-->
                                <th class="TD_TEN_KH" rowspan="3" style="font-weight:bold;">Tên khách hàng</th>
                                <th class="TD_SOKU" rowspan="3" style="font-weight:bold;">Mã món vay</th>

                                <th class="TD_DU_NO" colspan="6" style="font-weight:bold;">Số liệu tại NHCSXH</th> 

                                <th class="TD_DU_NO" colspan="3" rowspan="2" style="font-weight:bold;">Chênh lệch</th> 

                                <!--<th class="TD_NGUYEN_NHAN" rowspan="3" style="font-weight:bold;">Nguyên nhân chênh lệch</th>-->

                                <!--<th class="TD_SOKU" rowspan="3" style="font-weight:bold;">Thực trạng đối tượng đầu tư</th>-->
                            </tr>
                            <tr>
                                <th class="TD_DU_NO" colspan="4" style="font-weight:bold;">Nợ gốc</th> 
                                <th class="TD_DU_NO" rowspan="2" style="font-weight:bold;">Nợ lãi</th> 
                                <th class="TD_DU_NO" rowspan="2" style="font-weight:bold;">Dư TG</th> 
                            </tr>
                            <tr>
                                <th class="TD_DU_NO"  style="font-weight:bold;">Tổng số</th>
                                <th class="TD_DU_NO" style="font-weight:bold;">Nợ trong hạn</th>                  

                                <th class="TD_DU_NO" style="font-weight:bold;">Nợ quá hạn</th>    
                                <th class="TD_DU_NO" style="font-weight:bold;">Nợ khoanh</th> 

                                <th class="TD_DU_NO" style="font-weight:bold;">Nợ gốc</th> 
                                <th class="TD_DU_NO" style="font-weight:bold;">Nợ lãi</th> 
                                <th class="TD_DU_NO" style="font-weight:bold;">Tiền gửi</th>
                            </tr>

                            <s:if test="#rowstatus.even == true">
                                <tr class="ac_odd">
                                </s:if>
                                <s:else>
                                <tr class="ac_odd">
                                </s:else>
                                <!--                            <td align = "center" class="TD_MAKH">
                                                            <input type="text" value="<s:property  value="sMakh" />" name="sMakh" class="MAKH" onfocus="this.select()" readonly="true" />
                                                        </td>-->
                                <!--thay doi ve gia tri khong co de ngay vao else-->

                                <td align = "left" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="sTenkh" />" 
                                           name="sTenkh" class="TEN_KH" onfocus="this.select()" />
                                </td>

                                <td align = "center" class="TD_SOKU"> 
                                    <input type="text" id="sSoku" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].sSoku" value="<s:property value='sSoku'/>" id="<s:property value='sSoku'/>" class="SOKU linkKh" readonly="true"/>
                                </td>

                                <td align = "right" class="TD_DU_NO">
                                    <input type="text"  value="<s:property value='sTongsotien'/>" name="sTongsotien" class="DU_NO number2"
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }" onfocus="this.select()" readonly="true"/>
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
                                                   }" onfocus="this.select()" readonly="true"/>
                                </td>

                                <td align = "right" class="TD_DU_NO">
                                    <input type="text" value="<s:property value='sLai_Ton'/>" name="sLai_Ton" class="DU_NO number2" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }" onfocus="this.select()" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_DU_NO">
                                    <input type="text" value="<s:property value='sSodu_Casa'/>" name="sSodu_Casa" class="DU_NO number2" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }" onfocus="this.select()" readonly="true"/>
                                </td>

                                <!--chi tieu nhap tay tu day--> 

                                <td align = "right" class="TD_DU_NO">
                                    <input type="text" id="sNogoc_Clech" value="<s:property value='sNogoc_Clech'/>" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].bNogoc" class="DU_NO number2" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   isNumber(this.value)" onfocus="this.select()" style="background-color: #FFCCBA"/>
                                </td>

                                <td align = "right" class="TD_DU_NO">
                                    <input type="text" id="sNolai_Clech" value="<s:property value='sNolai_Clech'/>" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].bNolai" class="DU_NO number2" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   isNumber(this.value)" onfocus="this.select()" style="background-color: #FFCCBA"/>
                                </td>

                                <s:if test="sTk_Casa1.equalsIgnoreCase(' ') || sTk_Casa2.equalsIgnoreCase(' ')">
                                    <td align = "right" class="TD_DU_NO">
                                        <input type="text" id="sSoducasa" value="<s:property value='sSoducasa_Clech'/>" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].bDuCasa" class="DU_NO number2" 
                                               onblur="if (this.value == '') {
                                                           this.value = 0
                                                       }
                                                       isNumber(this.value)" onfocus="this.select()" style="background-color: #CCCCCC" readonly="true"/>
                                    </td>
                                </s:if>
                                <s:else>
                                    <td align = "right" class="TD_DU_NO">
                                        <input type="text" id="sSoducasa" value="<s:property value='sSoducasa_Clech'/>" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].bDuCasa" class="DU_NO number2" 
                                               onblur="if (this.value == '') {
                                                           this.value = 0
                                                       }
                                                       isNumber(this.value)" onfocus="this.select()" style="background-color: #FFCCBA"/>
                                    </td>
                                </s:else>
                            </tr>

                        </table>
                        <hr/>
                        <table border="1">
                            <tr>
                                <!--background: #FFFFFF-->
                                <td style="font-weight:bold;color: blueviolet;">Mã khách hàng</td>
                                <td style="font-weight:bold;color: blueviolet;"><s:property value='sMakh'/></td>
                                <td style="font-weight:bold;color: blueviolet;">Chương trình</td>
                                <td style="font-weight:bold;color: blueviolet;"> <s:property value='sCtrinh'/></td>
                                <td style="font-weight:bold;color: blueviolet;">Mã sản phẩm</td>
                                <td style="font-weight:bold;color: blueviolet;"> <s:property value='sMa_Spham'/></td>
                                <td style="font-weight:bold;color: blueviolet;">Tài khoản casa</td>
                                <td style="font-weight:bold;color: blueviolet;"> <s:property value='sTk_Casa1'/></td>
                                <td style="font-weight:bold;color: blueviolet;">Ngày số liệu</td>
                                <td style="font-weight:bold;color: blueviolet;"><s:property value='sNgaybc'/></td>
                            </tr>
                        </table>
                        <hr/>
                        <div id="divBrowseRisk"></div>
                        <!--</br>-->
                        <table class="tableKhnv" align="center">
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <span id="idTitle">Nguyên nhân chênh lệch</span>
                                </td>
                            </tr>
                            <tr align="center">
                                <td  colspan="2" align="center">
                                    <s:if test="sTrangthai_Dc.equalsIgnoreCase('R') || sTrangthai_Dc.equalsIgnoreCase('S')">
                                        <textarea id="sNguyennhan_Lech" form="frmdataDcNo"
                                                  name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].sNguyennhan" 
                                                  style="width: 98%" rows="3" onclick="disable_nn()"></textarea>
                                    </s:if>
                                    <s:else>
                                        <textarea id="sNguyennhan_Lech" form="frmdataDcNo"
                                                  name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].sNguyennhan" 
                                                  style="width: 98%" rows="3" onclick="disable_nn()"><s:property value='sNguyennhan_Lech'/></textarea>
                                    </s:else>
                                </td>
                            </tr>
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <hr/>
                                </td>
                            </tr>
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <span id="idTitle">Thực trạng đối tượng đầu tư</span>
                                </td>
                            </tr>
                            <tr align="center">
                                <td  colspan="2" align="center">
                                    <textarea id="sThuctrang_Dtdt" form="frmdataDcNo"
                                              name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].sTtDautu" 
                                              style="width: 98%" rows="3"><s:property value='sThuctrang_Dtdt'/></textarea>

                                    <!--onclick="disable_nn()"-->
                                </td>
                            </tr>
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <hr/>
                                </td>
                            </tr>
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <hr/>
                                </td>
                            </tr>

                            <tr align="center">
                                <td colspan="2" align="center">
                                    <span id="idTitle">Nhập nguyên nhân Không đối chiếu được</span>
                                </td>
                            </tr>
                            <tr align="center">
                                <td  colspan="2" align="center">
                                    <s:select
                                        id="ma_nguyennhan"
                                        name="ma_nguyennhan"
                                        list="lstNgnhanDm" 
                                        listKey="sKey"
                                        listValue="sDesc" 
                                        headerKey="-1"
                                        headerValue="-- Chọn --"
                                        cssStyle="font-weight: bold;width: 500px; vertical-align: middle;"
                                        onchange="onchan_sel()">                    
                                    </s:select>
                                </td>
                            </tr>

                            <tr align="center">
                                <td colspan="2" align="center">
                                    <hr/>
                                </td>
                            </tr>

                            <tr align="center">
                            <div id="in_nguyennhan" style="border: salmon">
                                <s:if test="sTrangthai_Dc.equalsIgnoreCase('R')">
                                    <td  colspan="2" align="center">
                                        <textarea id="sNguyennhan_kdc" form="frmdataDcNo"
                                                  name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].sNguyennhan" 
                                                  style="width: 98%" rows="3" ><s:property value='sNguyennhan_Lech'/></textarea>
                                    </td>
                                </s:if>
                                <s:elseif test="sTrangthai_Dc.equalsIgnoreCase('S')">
                                    <td  colspan="2" align="center">
                                        <textarea id="sNguyennhan_kdc" form="frmdataDcNo"
                                                  name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].sNguyennhan" 
                                                  style="width: 98%" rows="3" ><s:property value='sNguyennhan_Lech'/></textarea>
                                    </td>
                                </s:elseif>
                                <s:else>
                                    <td  colspan="2" align="center">
                                        <textarea id="sNguyennhan_kdc" form="frmdataDcNo"
                                                  name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].sNguyennhan" 
                                                  style="width: 98%" rows="3" ></textarea>
                                    </td>
                                </s:else>
                            </div>
                            </tr>

                            <tr align="center">
                                <td colspan="2" align="center">
                                    <hr/>
                                </td>
                            </tr>
                            <tr align="center">
                                <td align="center">
                                    <sj:submit id="idReject" name="nameReject" value="Đồng ý 1"  cssStyle="display: none" targets="divBrowseRisk"></sj:submit>
                                        <input type="button"  id="idRejecttmp" name="nameRejecttmp" value="Cập nhật"  
                                               Style="margin-right:25px; float: right; height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"/>
                                    </td>
                                    <td align="center">
                                    <sj:submit id="idClose" name="nameClose" value="Thoát" onclick="closeSelf()"
                                               cssStyle="margin-left:25px; float: left;height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"></sj:submit>
                                    </td>

                                </tr>
                            </table>
                            <hr/>
                    </s:iterator>

                </div>

            </s:form>
        </div>
    </body>
    <script>
//        Viet cho phan reload form khi khoi tao
        var sTrangthai = $("#sTrangthai").val();
        var value_nn = '';
        var bflag = false;
        if (sTrangthai == 'R' || sTrangthai == 'S')
        {
            var nguyennhan = $.trim($("#sNguyennhan_kdc").val());
            $('#ma_nguyennhan').find('option').each(function() {
//                alert($(this).text()+' - '+nguyennhan);
                var nn_select = $.trim($(this).text());
                if (nguyennhan == nn_select)
                {
//                    $(this).val(value_nn);
                    value_nn = $.trim($(this).val());
                    bflag = true;
//                    return;
                }
            });

            if (bflag)
            {
                $('#ma_nguyennhan').val(value_nn);
                onchan_sel();
            }
            else
            {
                $('#ma_nguyennhan').val('06');
                onchan_sel();
            }
        }
        else
        {
            $("#sNguyennhan_kdc").hide();
        }
    </script>
</html>

