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
        <title>Thông tin chi tiết khách hàng xử lý rủi ro</title>
        <sx:head/>
        <sj:head/>
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
                width: 300px;
            }

            .tenPGD{
                width: 200px;
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
        <script>
            $(document).ready(function () {
                $("#idRejecttmp").click(function () {
                    $('#divBrowseRisk').empty();


                    var soku = $("#soku").val();

                    var sTenkh = $("#sTenkh").val();
                    sTenkh = sTenkh.replace(/^\s*|\s*$/g, "");

                    var sNgayvay = $("#sNgayvay").val();
                    sNgayvay = sNgayvay.replace(/^\s*|\s*$/g, "");

                    var dbMdthiethai = $("#dbMdthiethai").val();
                    dbMdthiethai = dbMdthiethai.replace(/^\s*|\s*$/g, "");

                    var sNgayrr = $("#sNgayrr").val();
                    sNgayrr = sNgayrr.replace(/^\s*|\s*$/g, "");

                    var dbDnghi_Tg = $("#dbDnghi_Tg").val();
                    dbDnghi_Tg = dbDnghi_Tg.replace(/^\s*|\s*$/g, "");

                    var dbPduyet_Tg = $("#dbPduyet_Tg").val();
                    dbPduyet_Tg = dbPduyet_Tg.replace(/^\s*|\s*$/g, "");

                    var dbHt_Dno = $("#dbHt_Dno").val();
                    dbHt_Dno = dbHt_Dno.replace(/^\s*|\s*$/g, "");

                    var dbHt_Lai = $("#dbHt_Lai").val();
                    dbHt_Lai = dbHt_Lai.replace(/^\s*|\s*$/g, "");

                    var dbSolanxl = $("#dbSolanxl").val();
                    dbSolanxl = dbSolanxl.replace(/^\s*|\s*$/g, "");

                    var dbDnghi_Lai = $("#dbDnghi_Lai").val();
                    dbDnghi_Lai = dbDnghi_Lai.replace(/^\s*|\s*$/g, "");

                    var nam_xlrr = $("#nam_xlrr").val();
                    var dot_xlrr = $("#dot_xlrr").val();
                    var nhom_xlrr = $("#nhom_xlrr").val();
//                    alert(nhom_xlrr);
                    var trangthai_xlrr = $("#trangthai_xlrr").val();
                    var chuongtrinh = $("#chuongtrinh").val();
                    var nguon_von = $("#nguon_von").val();
                    var vb_xlrr = $("#vb_xlrr").val();
                    var poscd = $('#poscd').val();
                    var sNguyennhan = $("#sNguyennhan").val();

                    var url = "khonglamgica.action?soku_reject=" + soku + "&nam_xlrr=" + nam_xlrr + "&dot_xlrr=" + dot_xlrr + "&nhom_xlrr=" + nhom_xlrr
                            + "&trangthai_xlrr=" + trangthai_xlrr + "&chuongtrinh=" + chuongtrinh + "&nguon_von=" + nguon_von + "&poscd=" + poscd
                            + "&vb_xlrr=" + vb_xlrr
                            + "&sTenkh=" + sTenkh + "&sNgayvay=" + sNgayvay + "&dbMdthiethai=" + dbMdthiethai
                            + "&sNgayrr=" + sNgayrr + "&dbDnghi_Tg=" + dbDnghi_Tg + "&dbPduyet_Tg=" + dbPduyet_Tg
                            + "&dbHt_Dno=" + dbHt_Dno + "&dbHt_Lai=" + dbHt_Lai
                            + "&dbSolanxl=" + dbSolanxl + "&dbDnghi_Lai=" + dbDnghi_Lai
                            + "&sNguyennhan=" + sNguyennhan
                            ;

                    var data1 = "soku_reject=" + soku + "&nam_xlrr=" + nam_xlrr + "&dot_xlrr=" + dot_xlrr + "&nhom_xlrr=" + nhom_xlrr
                            + "&trangthai_xlrr=" + trangthai_xlrr + "&chuongtrinh=" + chuongtrinh + "&nguon_von=" + nguon_von + "&poscd=" + poscd
                            + "&vb_xlrr=" + vb_xlrr
                            + "&sTenkh=" + sTenkh + "&sNgayvay=" + sNgayvay + "&dbMdthiethai=" + dbMdthiethai
                            + "&sNgayrr=" + sNgayrr + "&dbDnghi_Tg=" + dbDnghi_Tg + "&dbPduyet_Tg=" + dbPduyet_Tg
                            + "&dbHt_Dno=" + dbHt_Dno + "&dbHt_Lai=" + dbHt_Lai
                            + "&dbSolanxl=" + dbSolanxl + "&dbDnghi_Lai=" + dbDnghi_Lai
                            + "&sNguyennhan=" + sNguyennhan
                            ;

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
                                alert("Bạn đã cập nhật thành công thông tin rủi ro cho khoản vay " + soku)
                                self.opener.document.forms['paginationForm'].idSubmit.click();
                                window.close();

                            } catch (e)
                            {
                                alert(e.toString());
                            }

                        },
                        error: function (data)
                        {
                            alert('Lỗi chưa cập nhật được thông tin rủi ro khoản vay. Xin liên hệ với quản trị ');
                            $('#divBrowseRisk').html("<h2 style='color: red'>Lỗi chưa cập nhật được thông tin rủi ro khoản vay. Xin liên hệ với quản trị ! </h2>");
                        }
                    });
                    return false;
                });

            });
        </script>
        <script>
            function closeSelf() {
                window.close();
                return true;
            }
        </script>
    </head>
    <body>
        <div id="container" style="width: 100%;">
            <s:form name="frmdata" id="frmdata" action="khonglamgica.action" theme="simple">
                <s:hidden name="namBc" id="namBc"/>
                <s:hidden name="nam_xlrr" id="nam_xlrr"/>
                <s:hidden name="soku" id="soku"/>
                <s:hidden name="dot_xlrr" id="dot_xlrr"/>
                <s:hidden name="nhom_xlrr" id="nhom_xlrr"/>
                <s:hidden name="vb_xlrr" id="vb_xlrr"/>
                <s:hidden name="poscd" id="poscd"/>
                <s:hidden name="chuongtrinh" id="chuongtrinh"/>
                <s:hidden name="nguon_von" id="nguon_von"/>
                <div id="divChiTieu" style="">
                    <span id="idTitle">Thông tin chi tiết khách hàng xử lý rủi ro</span>
                    <hr/>
                    <%--<s:property value="reportGrade"/>--%>

                    <s:iterator value="lstTableRiskObj">
                        <table>

                            <tr>
                                <td>
                                    Mã khách hàng:
                                    <input type="text" name="sMakh" id="sMakh" value="<s:property value="sMakh"/>" readonly="readonly" style="width: 350px; background-color: #E2E8C9; border: 0; "/>
                                </td>
                            </tr>
                            <!--                            <tr>
                                                            <td>
                                                                Tên khách hàng:
                                                                <input type="text" name="sTenkh" id="sTenkh" value="<s:property value="sTenkh"/>" readonly="readonly" style="width: 350px; background-color: #E2E8C9; border: 0; "/>
                                                            </td>
                                                        </tr>-->
                            <tr>
                                <td>
                                    Mã khoản vay:
                                    <input type="text" name="sSoku" id="sSoku" value="<s:property value="sSoku"/>" readonly="readonly" style="width: 400px; background-color: #E2E8C9; border: 0"/>
                                </td>
                            </tr>
                            <%--</s:iterator>--%>
                        </table>
                        <hr/>



                        <table border="1px" id="tableKhnv" class="tableKhnv">    
                            <tr class="cscontent">
                                <td><input type="text" value="Tên khách hàng" name="maPGD" style="color: red" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sTenkh'/>" name="tenPGD" id ="sTenkh" style="color: red" class="tenPGD" onfocus="this.select()"/></td>
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
                                <td><input type="text" value="Sản phẩm cụ thể" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sSprd_Cd'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Trạng thái khoản vay" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sDq_Stat_Cd'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
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
                                <td><input type="text" value="Gốc đề nghị" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbDnghi_Dno'/>" name="giaoKh number2" class="giaoKh number2" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Lãi đề nghị" name="maPGD" style="color: red" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbDnghi_Lai'/>" id="dbDnghi_Lai" name="giaoKh number2" style="color: red" class="giaoKh number2" onfocus="this.select()"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Gốc xử lý rủi ro" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbXl_Duno'/>" name="giaoKh number2" class="giaoKh number2" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Lãi xử lý" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbXl_Lai'/>" name="giaoKh number2" class="giaoKh number2" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Dư nợ hạch toán" name="maPGD" class="maPGD" style="color: red" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbHt_Dno'/>" id="dbHt_Dno" name="giaoKh number2" class="giaoKh number2" onfocus="this.select()" style="color: red"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Lãi hạch toán" name="maPGD" class="maPGD" style="color: red" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbHt_Lai'/>" id="dbHt_Lai" name="giaoKh number2" class="giaoKh number2" onfocus="this.select()" style="color: red"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Ngày vay (dd/mm/yyyy)" name="maPGD" class="maPGD" style="color: red" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sNgayvay'/>" name="tenPGD" id="sNgayvay"
                                           name="lstTableRiskObj[<s:property  value="%{#rowstatus.index}" />].sNgayvay" style="color: red" onfocus="this.select()" /></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Ngày đến hạn" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sNgaydh'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Thời hạn vay" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbThoihanvay'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Mức độ thiệt hại" name="maPGD" class="maPGD" style="color: red" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbMdthiethai'/>" name="tenPGD" id="dbMdthiethai" class="tenPGD" style="color: red" onfocus="this.select()"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text"  value="Ngày rủi ro (dd/mm/yyyy)" name="tenPGD" class="maPGD" style="color: red" readonly="readonly"/></td>
                                <td><input type="text" id="sNgayrr" name="sNgayrr" value="<s:property value='sNgayrr'/>"   class="tenPGD datepicker" placeholder="dd/MM/yyyy" style="color: red"/></td>
                            </tr>
                            <s:if test="sNhomrr.equalsIgnoreCase('02')">
                                <tr class="cscontent">
                                    <td><input type="text" value="Số tháng đề nghị" name="maPGD" class="maPGD" style="color: red" readonly="readonly"/></td>
                                    <td><input type="text" value="<s:property value='dbDnghi_Tg'/>" id="dbDnghi_Tg" name="tenPGD" class="tenPGD" style="color: red" onfocus="this.select()" /></td>
                                </tr>
                                <tr class="cscontent">
                                    <td><input type="text" value="Số tháng phê duyệt" name="maPGD" class="maPGD" style="color: red" readonly="readonly"/></td>
                                    <td><input type="text" value="<s:property value='dbPduyet_Tg'/>" name="tenPGD" id="dbPduyet_Tg" class="tenPGD" style="color: red" onfocus="this.select()" /></td>
                                </tr>

                            </s:if>
                            <s:else>

                                <tr class="cscontent">
                                    <td><input type="text" value="Số tháng đề nghị" name="maPGD" class="maPGD"  readonly="readonly"/></td>
                                    <td><input type="text" value="<s:property value='dbDnghi_Tg'/>" id="dbDnghi_Tg" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                                </tr>
                                <tr class="cscontent">
                                    <td><input type="text" value="Số tháng phê duyệt" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                    <td><input type="text" value="<s:property value='dbPduyet_Tg'/>" id="dbPduyet_Tg" name="tenPGD" class="tenPGD"  onfocus="this.select()" readonly="readonly"/></td>
                                </tr>
<!--                                <tr class="cscontent">
                                    <td><input type="text" value="Nguyên nhân rủi ro" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                    <td><input type="text" value="<s:property value='sNguyennhan'/>" name="tenPGD" class="tenPGD" id="sNguyennhan" onfocus="this.select()" readonly="readonly"/></td>
                                </tr>-->

                            </s:else> 
                            <tr class="cscontent">
                                <td><input type="text" value="Nguyên nhân rủi ro" style="color: red" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><select style="color: red; border: hidden; text-align: left" value="<s:property value='sNguyennhan'/>" name="tenPGD" id="sNguyennhan">
                                        <option value="102"<s:if test="sNguyennhan.equalsIgnoreCase('102')"> selected </s:if>>102 - Thiên tai, ĐH, HH, DB (QĐ 62)</option>
                                        <option value="103"<s:if test="sNguyennhan.equalsIgnoreCase('103')"> selected </s:if>>103 - NN thay đổi chính sách (QĐ 62)</option>
                                        <option value="104"<s:if test="sNguyennhan.equalsIgnoreCase('104')"> selected </s:if>>104 - KH bị phá sản/giải thể (QĐ 62)</option>
                                        <option value="105"<s:if test="sNguyennhan.equalsIgnoreCase('105')"> selected </s:if>>105 - LĐ về nước trước hạn (QĐ 62)</option>
                                        <option value="106"<s:if test="sNguyennhan.equalsIgnoreCase('106')"> selected </s:if>>106 - KH/TV khác gặp rủi ro (QĐ 62)</option>
                                        <option value="107"<s:if test="sNguyennhan.equalsIgnoreCase('107')"> selected </s:if>>107 - Vắng mặt tại nơi CT (QĐ 62)</option>
                                        <option value="108"<s:if test="sNguyennhan.equalsIgnoreCase('108')"> selected </s:if>>108 - Nợ phải thu theo BA, QĐ của TA (QĐ 62)</option>
                                        <option value="109"<s:if test="sNguyennhan.equalsIgnoreCase('109')"> selected </s:if>>109 - Nợ TO/CD mà người CD chết, MT (QĐ 62)</option>
                                        <option value="110"<s:if test="sNguyennhan.equalsIgnoreCase('110')"> selected </s:if>>110 - KH hết thời gian khoanh nợ (QĐ 62)</option>
                                        <option value="111"<s:if test="sNguyennhan.equalsIgnoreCase('111')"> selected </s:if>>111 - RR không làm kịp thời (QĐ 62)</option>
                                        <option value="112"<s:if test="sNguyennhan.equalsIgnoreCase('112')"> selected </s:if>>112 - Nợ nhận bàn giao (QĐ 62)</option>
                                        </select></td>
                                </tr> 
                                <tr class="cscontent">
                                    <td><input type="text" value="Mô tả nguyên nhân" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                    <!--<td><input type="text" value="" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>-->
                                    <td class="maPGD" style="padding-left: 10px"><s:property value='sMotann'/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Trạng thái bản ghi" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sTrangthai'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Ngày phê duyệt rủi ro, ngày khoanh…" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sPduyet_Ngay_Cn'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Người phê duyệt thông tin" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sPduyet_Nguoi_Cn'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Ngày phê duyệt rủi ro, ngày khoanh…" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sPduyet_Ngay_Tw'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Người phê duyệt thông tin" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sPduyet_Nguoi_Tw'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Người nhập thông tin rủi ro" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sTaolap_Nguoi'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Ngày tạo lập thông tin" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sTaolap_Ngay'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Quyết định xóa nợ" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sMaqd'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Tên quyết định" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sTenqd'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="01: Gia hạn, 02: Khoanh nợ, 03: Xóa nợ" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sNhomrr'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Mã PGD" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sMapgd'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Mã chi nhánh" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sMacn'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Ngày tạo số liệu" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sNgaybc'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Ngay cập nhật trạng thái" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sCapnhat'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Ngày tạo lập" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sNgaytao'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Người tạo lập" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sNguoitao'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Cấp phê duyệt" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sPduyet_Cap'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Ngày hiệu lực" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sNgayhl'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Tài khoản hạch toán xoá nợ" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sHt_Tkxoano'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Nguồn vốn sử dụng xử lý nợ" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sNguonvon'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Đợt xử lý rủi ro" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbDotrr'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Mã địa phương" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sMadp'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Mã tổ" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sMato'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Số lần xử lý" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='dbSolanxl'/>" name="tenPGD" id="dbSolanxl" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>

                            <tr class="cscontent">
                                <td><input type="text" value="Người Phê duyệt PGD" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sNguoi_pduyet_pgd'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Ngày Phê duyệt PGD" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sNgay_pduyet_pgd'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Nguyên nhân từ chối" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sNguyennhan_tuchoi'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Người phê duyệt trên INTELLECT CN" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sInt_pduyet_nguoi_cn'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <tr class="cscontent">
                                <td><input type="text" value="Ngày phê duyệt trên INTELLECT CN" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sInt_pduyet_ngay_cn'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                            <s:if test="reportGrade.equalsIgnoreCase('3')">
                                <tr class="cscontent">
                                    <td><input type="text" value="Nguyên nhân từ chối chi nhánh" name="maPGD" class="maPGD" readonly="readonly"/></td>
                                    <td><input type="text" value="<s:property value='sNguyennhan_tc_cn'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                                </tr>
                            </s:if>
                        </table>

                        <s:if test="reportGrade.equalsIgnoreCase('3')"> 
                            <table class="tableKhnv" align="center">                                                        
                                <tr align="center">
                                    <td colspan="2" align="center">
                                        <hr/>
                                    </td>
                                </tr>
                                <tr align="center">
                                    <td align="center">
                                        <sj:submit id="idReject" name="nameReject" value="Đồng ý 1"  cssStyle="display: none" targets="divBrowseRisk"></sj:submit>
                                            <input type="button"  id="idRejecttmp" name="nameRejecttmp" value="Đồng ý"  
                                                   Style="height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"/>
                                        </td>
                                        <td align="center">
                                        <sj:submit id="idClose" name="nameClose" value="Hủy bỏ" onclick="closeSelf()"
                                                   cssStyle="height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"></sj:submit>
                                        </td>
                                    </tr>
                                </table>    
                                <div id="divBrowseRisk"></div>            
                        </s:if>                   
                    </s:iterator>
                </div>
            </s:form>
        </div>
    </body>
</html>

