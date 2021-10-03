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
        <title>Thông tin chi tiết khách hàng xử lý rủi ro sẽ từ chối</title>
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
                width:500px; 
                min-height: 580px; 
                margin: 0px auto 10px auto; 
                padding: 10px;
                background-color: #E2E8C9;
            }

            #idTitle{
                font-family: Verdana,Arial,Tahoma,Helvetica;
                font-size: 13pt;
                font-weight: bold;
                color: #116600;
            }

            #divSave{
                text-align: right;
            }

            table{
                border-collapse: collapse;
                width: 100%;
                line-height: 19px;
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
                width: 95%;
            }

            .maPGD{
                width: 150px;
            }

            .tenPGD{
                width: 200px;
            }

            textarea:focus {
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                padding: 3px 0px 3px 3px;
                margin: 5px 1px 3px 0px;
                border: 1px solid rgba(81, 203, 238, 1);
            }
        </style>
        <script>
            $(document).ready(function () {
                //Format cac truong input.number can le phai
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $('.maPGD').css({"text-align": "left"});
                $('.tenPGD').css({"text-align": "left"});
                $(".maPGD").css({"width": "150px"});
//                $(".maPGD").css({"width": "140px"});
            });</script>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script type="text/JavaScript">
            $("#idRejecttmp").submit(function(event) {
            event.preventDefault();
            var $form = $(this);
            var url = $form.attr('action');
            $.post(url).done(function(data) {
            alert(data);
            });
            });
        </script>
        <SCRIPT language="javascript">

<!--
            function sendAndClose(selObj, restore) {
                if (selObj.selectedIndex != 0) {
                    self.opener.document.forms['test'].TextOutput.value = selObj.options[selObj.selectedIndex].text;
                    window.close();
                }
            }
//-->

            $(document).ready(function () {


                $("#idRejecttmp").click(function () {
                    $('#divBrowseRisk').empty();
                    var nguyennhan_tuchoi = $("#idnguyennhan_tuchoi").val();
                    nguyennhan_tuchoi = nguyennhan_tuchoi.replace(/^\s*|\s*$/g, "");
//                nguyennhan_tuchoi = trim(nguyennhan_tuchoi);
                    if (nguyennhan_tuchoi == null || nguyennhan_tuchoi.length < 4)
                    {
                        alert('Bạn phải nhập nguyên nhân từ chối trước khi nhấn đồng ý');
                        $('#divBrowseRisk').html("<h2 style='color: red'>Bạn phải nhập nguyên nhân từ chối trước khi nhấn đồng ý ! </h2>");
                        $('#idnguyennhan_tuchoi').focus();
                        //document.getElementById("myAnchor").focus();
                        return;
                    }
                    nguyennhan_tuchoi = $("#idnguyennhan_tuchoi").val();

                    var soku = $("#soku_reject").val();
//                    alert('vao ham goi submit ' + soku);
                    var nam_xlrr = $("#nam_xlrr").val();
                    var dot_xlrr = $("#dot_xlrr").val();
                    var nhom_xlrr = $("#nhom_xlrr").val();
                    var trangthai_xlrr = $("#trangthai_xlrr").val();
                    var chuongtrinh = $("#chuongtrinh").val();
                    var nguon_von = $("#nguon_von").val();
                    var vb_xlrr = $("#vb_xlrr").val();
                    var poscd = $('#poscd').val();
                    var url = "setRejectRisk62.action?soku_reject=" + soku + "&nam_xlrr=" + nam_xlrr + "&dot_xlrr=" + dot_xlrr + "&nhom_xlrr=" + nhom_xlrr
                            + "&trangthai_xlrr=" + trangthai_xlrr + "&chuongtrinh=" + chuongtrinh + "&nguon_von=" + nguon_von + "&poscd=" + poscd + "&nguyennhan_tuchoi=" + nguyennhan_tuchoi
                            + "&vb_xlrr=" + vb_xlrr;
                    var data1 = "soku_reject=" + soku + "&nam_xlrr=" + nam_xlrr + "&dot_xlrr=" + dot_xlrr + "&nhom_xlrr=" + nhom_xlrr
                            + "&trangthai_xlrr=" + trangthai_xlrr + "&chuongtrinh=" + chuongtrinh + "&nguon_von=" + nguon_von + "&poscd=" + poscd + "&nguyennhan_tuchoi=" + nguyennhan_tuchoi
                            + "&vb_xlrr=" + vb_xlrr;
                    $.ajax({
                        type: 'POST',
                        url: url,
                        data: data1,
                        dataType: 'json',
                        contentType: 'application/json',
                        type: 'POST',
                                async: true,
                        success: function (data) {
                            try {
                                alert("Bạn đã từ chối thành công khoảng vay " + soku)
                                self.opener.document.forms['paginationForm'].idSubmit.click();
                                window.close();

                            }
                            catch (e)
                            {
                                alert(e.toString());
                            }

                        },
                        error: function (data)
                        {
                            alert('Lỗi chưa từ chối được khoản vay xin liên hệ với quản trị ');
                            $('#divBrowseRisk').html("<h2 style='color: red'>Lỗi chưa từ chối được khoản vay xin liên hệ với quản trị ! </h2>");
                        }
                    });
                    return false;
                });
            });

            function closeSelf() {
                window.close();
                return true;
            }
            function onClick_reject()
            {

                var nguyennhan_tuchoi = $("#idnguyennhan_tuchoi").val();
                nguyennhan_tuchoi = nguyennhan_tuchoi.replace(/^\s*|\s*$/g, "");
//                nguyennhan_tuchoi = trim(nguyennhan_tuchoi);
                if (nguyennhan_tuchoi == null || nguyennhan_tuchoi.length < 4)
                {
                    alert('Bạn phải nhập nguyên nhân từ chối trước khi nhấn đồng ý');
                    $('#idnguyennhan_tuchoi').focus();
                    //document.getElementById("myAnchor").focus();
                    return;
                }
                else
                {
//                    $("#idReject")[0].click();
//                    $("#idReject").click(function () {
//                        alert('vao ham goi submit ');
//                        var nam_xlrr = $("#nam_xlrr").val();
//                        var dot_xlrr = $("#dot_xlrr").val();
//                        var nhom_xlrr = $("#nhom_xlrr").val();
//                        var trangthai_xlrr = $("#trangthai_xlrr").val();
//                        var chuongtrinh = $("#chuongtrinh").val();
//                        var nguon_von = $("#nguon_von").val();
//                        var poscd = $('#poscd').val();
//                        var url = "setRejectRisk.action?soku=" + soku + "&nam_xlrr=" + nam_xlrr + "&dot_xlrr=" + dot_xlrr + "&nhom_xlrr=" + nhom_xlrr
//                                + "&trangthai_xlrr=" + trangthai_xlrr + "&chuongtrinh=" + chuongtrinh + "&nguon_von=" + nguon_von + "&poscd=" + poscd;
//                        $.ajax({
//                            type: 'POST',
//                            url: url,
//                            dataType: 'json',
//                            contentType: 'application/json',
//                            success: function (data) {
//                                console.log(stringify(data));
//                                if (data.indexOf("success") == -1) {
//                                    alert("Action returned Error")
//                                } else {
//                                    alert("Action returned Success")
//                                }
//                            }
//
////                    document.getElementById('displayLog').innerHTML = s;
//                        });
//                        return false;
//                    });
//                    window.close();
//                    if (window.opener != null && !window.opener.closed) {
//                        window.opener.document.getElementById("idSubmit").submit();
//                        alert('submit form roi');
//                    }
//                    window.close();

//                    $("#paginationForm").children('input[type="submit"]').click();
//                    $("#paginationForm").attr("#idSubmit").trigger("click");
                }

            }
        </script>
    </head>
    <body>
        <div id="container" style="width: 100%;">
            <s:form name="frmdata62" id="frmdata62" action="setRejectRisk62.action" theme="simple">
                <s:hidden name="nam_xlrr" id="nam_xlrr"/>
                <s:hidden name="soku_reject" id="soku_reject"/>
                <s:hidden name="dot_xlrr" id="dot_xlrr"/>
                <s:hidden name="nhom_xlrr" id="nhom_xlrr"/>
                <s:hidden name="vb_xlrr" id="vb_xlrr"/>
                <s:hidden name="poscd" id="poscd"/>
                <s:hidden name="chuongtrinh" id="chuongtrinh"/>
                <s:hidden name="nguon_von" id="nguon_von"/>
                <div id="divChiTieu" style="">
                    <span id="idTitle">Thông tin chi tiết khách hàng từ chối xử lý rủi ro 62</span>
                    <hr/>
                    <s:iterator value="lstTableRiskObj">
                        <table border="1px" id="tableKhnv" class="tableKhnv">
                            <tr class="cscontent">
                                <td><input type="text" value="Mã khách hàng" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sMakh'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Tên khách hàng" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sTenkh'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Mã khoản vay" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sSoku'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Địa chỉ khách hàng" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sDiachi'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Chương trình vay" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sChtrinh'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Dư nợ gốc" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbDngoc'/>" name="giaoKh number2" class="giaoKh number2" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Lãi trong hạn" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbLaith'/>" name="giaoKh number2" class="giaoKh number2" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Lãi quá hạn" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbLaiqh'/>" name="giaoKh number2" class="giaoKh number2" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>

                            <tr class="cscontent">
                                <td><input type="text" value="Thời hạn vay" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbThoihanvay'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Số tháng đề nghị" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbDnghi_Tg'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Mô tả nguyên nhân" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <!--<td><input type="text" value="" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>-->
                                <td><s:property value='sMotann'/></td>
                            </tr>
                        </table>

                        <div id="divBrowseRisk"></div>
                        <!--</br>-->
                        <table class="tableKhnv" align="center">
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <span id="idTitle">Nhập nguyên nhân từ chối</span>
                                </td>
                            </tr>
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <hr/>
                                </td>
                            </tr>

                            <tr align="center">
                                <td  colspan="2" align="center">
                                    <s:textarea id="idnguyennhan_tuchoi" name="nguyennhan_tuchoi"   cols="60" rows="5" >
                                        <s:param name="value" >
                                            <%--<s:property value='sNguyennhan_tuchoi' />--%>
                                            ${sNguyennhan_tuchoi}
                                        </s:param>
                                    </s:textarea>
                                </td>
                            </tr>
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <hr/>
                                </td>
                            </tr>
                            <tr align="center">
                                <td align="center">
                                    <sj:submit id="idReject62" name="nameReject" value="Đồng ý 1"  cssStyle="display: none" targets="divBrowseRisk"></sj:submit>
                                        <input type="button"  id="idRejecttmp" name="nameRejecttmp" value="Đồng ý"  
                                               Style="height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"/>
                                    </td>
                                    <td align="center">
                                    <sj:submit id="idClose" name="nameClose" value="Hủy bỏ" onclick="closeSelf()"
                                               cssStyle="height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"></sj:submit>
                                    </td>
                                </tr>
                            </table>
                            <hr/>
                    </s:iterator>

                </div>

            </s:form>
        </div>
    </body>
</html>

