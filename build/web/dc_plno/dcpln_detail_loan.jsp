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
        <title>Thông tin chi tiết Món vay - Phân loại nợ</title>
        <sx:head/>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <!--<script type="text/javascript" src="js/pagination.js"></script>-->
        <style>      
            *{
                font: 13px Cambria, Helvetica, sans-serif;
            }

            #divChiTieu{
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width:99%; 
                height: 99%; 
                margin: 0px auto 10px auto; 
                background-color: #E2E8C9;
            }
            #container_popup{
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width: 100%;
                height:96vh; 
                padding-left: 0px;     
                background-color: #E2E8C9;
            }
            #idTitle{
                font-family: Cambria,Verdana,Arial,Tahoma,Helvetica;
                font-size: 11pt;
                font-weight: bold;
                color: blue;
            }

            #divSave{
                text-align: right;
            }

            table{
                border-collapse: collapse;
                width: 100%;
                line-height: 25px;
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
                width: 99%;
            }

            .maPGD{
                width: 150px;
            }

            .tenPGD{
                width: 200px;
            }

            textarea:focus {
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                border: 1px solid rgba(81, 203, 238, 1);
            }
        </style>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script language="javascript">
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);

                $("#idRejecttmp").click(function ()
                {
                    $('#divBrowseRisk').empty();
                    var params = {};
                    var arr = [];
                    var p1;
                    var sSoku = $("#sSoku").val();
                    var bNogoc_Clech = $("#sNogoc_Clech").val();
                    var bNolai_Clech = $("#sNolai_Clech").val();
                    var sNgnhan_Clech = $.trim($("#sNgnhan_Clech").val());
                    var sQuanhe_Kh = $.trim($("#sQuanhe_Kh").val());
                    var sTrangthai = $("#trangthai").val();          //Giá trị bằng: document.getElementById("trangthai").value;
                    // Giá trị biến sTrangthai có giá trị bằng biến matrangthai
                    var ngay_dcpln = $("#ngay_dcpln").val();
                    var totruong_dcpln = $("#totruong_dcpln").val();
                    var bTongDN = $("#sTongDN").val();
                    var bTonglaiton = $("#sTonglaiton").val();
                    if (parseFloat(bNogoc_Clech) > parseFloat(bTongDN)) {
                        alert('Nợ gốc chênh lệch không thể lớn hơn dư nợ của món vay. Vui lòng kiểm tra lại!');
                        $("#sNogoc_Clech").focus();
                        $("#sNogoc_Clech").css({"background-color": "#ffff99"});
                        return;
                    }
                    if (parseFloat(bNolai_Clech) > parseFloat(bTonglaiton) && parseFloat(bTonglaiton) > 0) {
                        alert('Nợ lãi chênh lệch không thể lớn hơn lãi tồn của món vay. Vui lòng kiểm tra lại!');
                        $("#sNolai_Clech").focus();
                        $("#sNolai_Clech").css({"background-color": "#ffff99"});
                        return;
                    }
                    if (parseFloat(bNogoc_Clech) + parseFloat(bNolai_Clech) > 0 && sNgnhan_Clech.length < 1) {
                        alert('Bạn phải nhập nguyên nhân chênh lệch khi đối chiếu!');
                        $("#sNgnhan_Clech").focus();
                        $("#sNgnhan_Clech").css({"background-color": "#ffff99"});
                        return;
                    }
                    if ((parseFloat(bNogoc_Clech) + parseFloat(bNolai_Clech) <= 0 && sNgnhan_Clech.length > 0) && sTrangthai != 'R') {
                        alert('Nguyên nhân chênh lệch không hợp lệ do Không có chênh lệch nợ gốc hoặc nợ lãi. Vui lòng kiểm tra lại!');
                        $("#sNgnhan_Clech").val('');
                        $("#sNgnhan_Clech").focus();
                        $("#sNgnhan_Clech").css({"background-color": "#ffff99"});
                        return;
                    }
                    p1 = {ngay_dcpln: ngay_dcpln,
                        totruong_dcpln: totruong_dcpln,
                        lstSavePln: [
                            {"sSoku": sSoku,
                                "bC_Kntn_Sodu": 0,
                                "bK_Kntn_Sodu": 0,
                                "sK_Ma_Ngnhan": "",
                                "sK_Ngnhan_Kh": "",
                                "sQuanhe_Kh": sQuanhe_Kh,
                                "sTrangthai": sTrangthai,
                                "bNogoc_Clech": bNogoc_Clech,
                                "bNolai_Clech": bNolai_Clech,
                                "sNgnhan_Clech": sNgnhan_Clech}
                        ]
                    };
                    var url = "saveDataDcDetailPLN.action?soku_dcpln=" + sSoku + "&ngay_dcpln=" + ngay_dcpln + "&totruong_dcpln=" + totruong_dcpln;
                    var data1 = JSON.stringify(p1);
                    $.ajax({
                        url: url,
                        data: data1,
                        dataType: 'json',
                        contentType: 'application/json',
                        type: 'POST',
                        async: true,
                        success: function (data) {
                            try {
                                alert("Bạn đã lưu dữ liệu về thông tin đối chiếu, phân loại nợ thành công!");
                                window.onunload = function (e) {
                                    opener.reLoadForm('Tham số không cần thiết khi ReLoad lại giao diện chính!');
                                };
                                window.close();
                            } catch (e) {
                                alert(e.toString());
                            }
                        },
                        error: function (data) {
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

            function isNumber(value) {
                if (value == null) {
                    alert('Bạn phải nhập dữ liệu cho trường này');
                    return false;
                }
                value = value.replace(/,/g, "");
                var result = true; //Luu ket qua kiem tra kieu so co dung khong
                if (isNaN(parseFloat(value))) {
                    result = false;
                    alert('Bạn nhập không đúng kiểu số xin nhập lại dữ liệu');
                    focus();
                    return false;
                } else {
                    //Neu la kieu so --> Kiem tra xem kieu nhap co < 9999999999
                    if (parseFloat(value) > 999999999999) {
                        result = false;
                        alert('Giá trị bạn nhập vượt quá giới hạn!');
                        focus();
                        return false;
                    }
                }
            }

            function SelectOption_Status()
            {
                var trangthai_val = $('#id_trangthai').val(); //Lấy giá trị trạng thái ('S','N','R') từ form bên mang sang
                var len = document.getElementById('trangthai').options.length;
                for (var i = 0; i <= len; i++)
                {
                    if (document.getElementById('trangthai').options[i].value == trangthai_val)
                    {
                        document.getElementById('trangthai').selectedIndex = i;
                        break;
                    }
                }
            }

            // Thực hiện kiểm tra hợp lệ sau:
            function on_valib()
            {
                try {

                    var bTongDN = $("#sTongDN").val();
                    var bTonglaiton = $("#sTonglaiton").val();
                    var bNogoc_Clech = $("#sNogoc_Clech").val();
                    var bNolai_Clech = $("#sNolai_Clech").val();

                    var sNgnhan_Clech = $.trim($("#sNgnhan_Clech").val());
                    var sQuanhe_Kh = $.trim($("#sQuanhe_Kh").val());
                    var sTrangthai = $("#trangthai").val();


                    if (bNogoc_Clech > 0 && (bTongDN < bNogoc_Clech || bTongDN > bNogoc_Clech))
                    {
                        alert('Bạn không thể điền số tiền Nợ gốc chênh lệch lớn hơn hoặc nhỏ hơn tổng dư nợ!');
                        $("#sNogoc_Clech").css({"background-color": "#ffff99"});
                        document.getElementById("sNogoc_Clech").value = bTongDN;
                        $('.number2').number(true, 0);
                        $('.number2').number(true, 0);
                        $("#sNogoc_Clech").focus();
                        return;
                    }
                    if (bNolai_Clech > 0 && (bTonglaiton < bNolai_Clech || bTonglaiton > bNolai_Clech))
                    {
                        alert('Bạn không thể điền số tiền Nợ lãi chênh lệch lớn hơn hoặc nhỏ hơn tổng lãi tồn!');
                        $("#sNolai_Clech").css({"background-color": "#ffff99"});
                        document.getElementById("sNolai_Clech").value = bTonglaiton;
                        $('.number2').number(true, 0);
                        $('.number2').number(true, 0);
                        $("#sNolai_Clech").focus();
                        return;
                    }


//                    
//                    
//                    
//                    var tongduno = $('#sTongDN' + id.toString()).val();
//                    if (parseFloat(tongduno) <= 0) {
//                        tongduno = $('#id_Tonglaiton_' + id.toString()).val();
//                    }
//                    var duno_khoanh = $('#id_Dnokhoanh_' + id.toString()).val();
//                    var duno_kntn_khong = $('#dukhong_kntn_' + id.toString()).val();
//                    var duno_kntn_co = $('#duco_kntn_' + id.toString()).val();
//
//                    if (duno_khoanh > 0)
//                    {
//                        $('#dukhong_kntn_' + id.toString()).attr("disabled", true);
//                        $('#ngnhan_kh_' + id.toString()).attr("disabled", false);
//                        $('#nguyennhan_' + id.toString()).attr("disabled", true);
//                    }
//                    else
//                    {
//                        $('#dukhong_kntn_' + +id.toString()).attr("disabled", false);
//                        $('#ngnhan_kh_' + id.toString()).attr("disabled", true);
//                        document.getElementById("ngnhan_kh_" + id.toString()).value = '';
//                        $('#nguyennhan_' + id.toString()).attr("disabled", false);
//                    }
//                    if (parseFloat(duno_kntn_co) > 0)
//                    {
//                        $('#nguyennhan_' + id.toString()).attr("disabled", true);
//                        document.getElementById("nguyennhan_" + id.toString()).value = '-1';
//                    }
//                    else {
//                        $('#nguyennhan_' + id.toString()).attr("disabled", false);
//                    }
//
//                    if (parseFloat(duno_kntn_khong) > 0)
//                    {
//                        $('#nguyennhan_' + +id.toString()).attr("disabled", false);
//                    }
//                    else {
//                        $('#nguyennhan_' + +id.toString()).attr("disabled", true);
//                        document.getElementById("nguyennhan_" + id.toString()).value = '-1';
//                    }
//
//                    if (duno_kntn_co > 0 && (tongduno < duno_kntn_co || tongduno > duno_kntn_co))
//                    {
//                        alert('Bạn không thể điền số tiền Nợ có khả năng trả nợ lớn hơn hoặc nhỏ hơn tổng dư nợ!');
//                        document.getElementById("duco_kntn_" + id.toString()).value = tongduno;
//                        return;
//                    }
//                    if (duno_kntn_khong > 0 && (tongduno < duno_kntn_khong || tongduno > duno_kntn_khong))
//                    {
//                        alert('Bạn không thể điền số tiền Nợ không có khả năng trả nợ lớn hơn hoặc nhỏ hơn tổng dư nợ!');
//                        document.getElementById("dukhong_kntn_" + id.toString()).value = tongduno;
//                        return;
//                    }
                } catch (e) {
                    alert('Lỗi thực hiện gán giá trị khi Phân loại khả năng trả nợ của Khách hàng: ' + e.toString());
                }
            }
        </script>
    </head>
    <body>
        <div id="container_popup">
            <s:form name="frmdataDcNo" id="frmdataDcNo" action="saveDataDcDetailPLN.action" theme="simple">
                <s:hidden name="soku_dcpln" id="soku_dcpln"/>
                <s:hidden name="ngay_dcpln" id="ngay_dcpln"/>
                <s:hidden name="totruong_dcpln" id="totruong_dcpln"/>                
                <div id="divChiTieu" style="text-align: center;">
                    <br>
                    <span id="idTitle">THÔNG TIN CHI TIẾT MÓN VAY ĐỐI CHIẾU - PHÂN LOẠI NỢ</span>
                    <hr/>
                    <s:iterator value="#attr.lstDcplnModel" var="modelDcpt" status="rowstatus">
                        <s:hidden name="name_trangthai" value="%{sTrangthai}" id="id_trangthai"/>  
                        <table border="0" align="center">
                            <tr>
                                <th style="text-align: left;color:#666666;">Mã KH: <span style="color:blue;"><s:property value='sMakh'/></span></th>
                                <th style="text-align: left;">Tên KH: <span style="color:blue;"><s:property value='sTenkh'/></span></th>
                                <th style="text-align: left;">Sản phẩm: <span style="color:blue;"><s:property value='sSprd_Cd_Ten'/> (<s:property value='sSprd_Cd'/>)</span></th>
                            </tr>
                            <tr>
                                <th style="text-align: left;color:#666666;">Tổ TK&VV: <span style="color:blue;"><s:property value='sMato'/>-<s:property value='sTentt'/></span></th>
                                <th style="text-align: left;">Hội đoàn thể: <span style="color:blue;"><s:property value='sDvut_Ten'/></span></th>
                                <th style="text-align: left;">Trạng thái phân loại nợ: <span style="color:blue;"><s:property value='sTrangthai_Ten'/></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Ngày số liệu: <span style="color:blue;"><s:property value='sNgaybc'/></span></th>
                            </tr>
                        </table>
                        <hr/>
                        <table border="1" class="editDelete" align="center">
                            <tr>
                                <th class="TD_SOKU" rowspan="3" style="font-weight:bold;">Mã món vay</th>
                                <th class="TD_DU_NO" colspan="5" style="font-weight:bold;">Số liệu tại NHCSXH</th> 
                                <th class="TD_DU_NO" colspan="2" rowspan="2" style="font-weight:bold;">Chênh lệch</th>
                                <th class="TD_SOKU" rowspan="3" style="font-weight:bold;width: 190px;">Trạng thái</th>
                            </tr>
                            <tr>
                                <th class="TD_DU_NO" colspan="4" style="font-weight:bold;">Nợ gốc</th> 
                                <th class="TD_DU_NO" rowspan="2" style="font-weight:bold;">Nợ lãi</th> 
                            </tr>
                            <tr>
                                <th class="TD_DU_NO"  style="font-weight:bold;">Tổng số</th>
                                <th class="TD_DU_NO" style="font-weight:bold;">Nợ trong hạn</th>                  

                                <th class="TD_DU_NO" style="font-weight:bold;">Nợ quá hạn</th>    
                                <th class="TD_DU_NO" style="font-weight:bold;">Nợ khoanh</th> 

                                <th class="TD_DU_NO" style="font-weight:bold;">Gốc</th> 
                                <th class="TD_DU_NO" style="font-weight:bold;">Lãi</th> 
                            </tr>

                            <tr>
                                <td align = "center" style="width: 116px"> 
                                    <input type="text" id="sSoku" name="lstSavePln[<s:property  value="%{#rowstatus.index}" />].sSoku" value="<s:property value='sSoku'/>" id="<s:property value='sSoku'/>" class="SOKU linkKh" readonly="true"/>
                                </td>

                                <td style="width: 116px">
                                    <input type="text" id="sTongDN" value="<s:property value='sTongDN'/>" name="sTongDN" class="DU_NO number2"
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }" onfocus="this.select()" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_DU_NO">
                                    <input type="text" id="sDnothan" value="<s:property value='sDnothan'/>" name="sDnothan" class="DU_NO number2" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }" onfocus="this.select()" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_DU_NO">
                                    <input type="text" id="sDnoqhan" value="<s:property value='sDnoqhan'/>" name="sDnoqhan" class="DU_NO number2"
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }" onfocus="this.select()" readonly="true"/>
                                </td>

                                <td align = "right" class="TD_DU_NO">
                                    <input type="text" id="sDnokhoanh" value="<s:property value='sDnokhoanh'/>" name="sDnokhoanh" class="DU_NO number2" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }" onfocus="this.select()" readonly="true"/>
                                </td>

                                <td align = "right" class="TD_DU_NO">
                                    <input type="text" id="sTonglaiton" value="<s:property value='sTonglaiton'/>" name="sTonglaiton" class="DU_NO number2" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }" onfocus="this.select()" readonly="true"/>
                                </td>

                                <!--Chi tieu nhap tay tu day--> 
                                <td align = "right" class="TD_DU_NO">
                                    <input type="text" id="sNogoc_Clech" value="<s:property value='sNogoc_Clech'/>" name="lstSavePln[<s:property  value="%{#rowstatus.index}" />].bNogoc_Clech" class="DU_NO number2" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   isNumber(this.value);
                                                   on_valib()" onfocus="this.select()" style="background-color: #FFCCBA"/>
                                </td>

                                <td align = "right" class="TD_DU_NO">
                                    <input type="text" id="sNolai_Clech" value="<s:property value='sNolai_Clech'/>" name="lstSavePln[<s:property  value="%{#rowstatus.index}" />].bNolai_Clech" class="DU_NO number2" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   isNumber(this.value);
                                                   on_valib()" onfocus="this.select()" style="background-color: #FFCCBA"/>
                                </td>
                                <td align = "left" class="TD_SOKU">
                                    <select id="trangthai" name="lstSavePln[%{#rowstatus.index}].sTrangthai">
                                        <option value="N">N - Chưa đối chiếu</option>
                                        <option value="R">R - Không đối chiếu được</option>
                                        <option value="S">S - Đã đối chiếu</option>
                                    </select> />
                                </td>
                            </tr>
                        </table>
                        <hr/>
                        <table class="tableNguyenNhan" align="center">
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <span id="idTitle">Nguyên nhân chênh lệch hoặc nguyên nhân không đối chiếu được</span>
                                </td>
                            </tr>

                            <tr align="center">
                                <td  colspan="2" align="center" class="TD_NGUYEN_NHAN_KHOANH">
                                    <textarea id="sNgnhan_Clech" form="frmdataDcNo" value="<s:property value='sNgnhan_Clech'/>"
                                              name="lstSavePln[<s:property  value="%{#rowstatus.index}" />].sNgnhan_Clech"
                                              style="width: 99%;background-color: #FFCCBA;" rows="6"><s:property value='sNgnhan_Clech'/></textarea>
                                </td>
                            </tr>
                        </table>

                        <table class="tableQuanHe" align="center">
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <span id="idTitle">Quan hệ với khách hàng</span>
                                </td>
                            </tr>
                            <tr align="center">
                                <td  colspan="2" align="center" class="TD_NGUYEN_NHAN_KHOANH">
                                    <textarea id="sQuanhe_Kh" form="frmdataDcNo" value="<s:property value='sQuanhe_Kh'/>"
                                              name="lstSavePln[<s:property value="%{#rowstatus.index}" />].sQuanhe_Kh" onfocus="this.select()"
                                              style="width: 99%;background-color: #FFCCBA;" rows="2" ><s:property value='sQuanhe_Kh'/></textarea>                                              
                                </td>
                            </tr>
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <hr/>
                                </td>
                            </tr>
                            <tr align="center">
                                <td align="center">
                                    <sj:submit id="idReject" name="nameReject" value="Đồng ý"  cssStyle="display: none" targets="divBrowseRisk"></sj:submit>
                                        <input type="button" id="idRejecttmp" name="nameRejecttmp" value="Cập nhật"  
                                               Style="margin-right:25px; float: right; height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"/>
                                    </td>
                                    <td align="center">
                                    <sj:submit id="idClose" name="nameClose" value="Thoát" onclick="closeSelf()"
                                               cssStyle="margin-left:25px; float: left;height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"></sj:submit>
                                    </td>
                                </tr>
                            </table>
                    </s:iterator>
                    <div id="divBrowseRisk"></div>
                </div>

            </s:form>
        </div>
    </body>
    <script>
        SelectOption_Status();
    </script>
</html>

