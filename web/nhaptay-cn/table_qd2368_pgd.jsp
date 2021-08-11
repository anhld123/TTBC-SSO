<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="js/numbertoword.js"></script>

        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $(".datepicker_month").datepicker({dateFormat: 'mm/yy'});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_STT").css({"width": "30px"});
                $(".TD_MAKH").css({"width": "70px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "130px"});
                $(".TD_SOKU").css({"width": "120px"});
                $(".TD_SOTIEN").css({"width": "90px"});
                $(".TD_CHITIEU").css({"width": "300px"});
                $(".TEN_KH").css({"width": "100%"});
            });
//            $('.TEN_KH').focus(function () {
//                $(this).closest('tr').addClass('highlight_row');
//            });
//            $('.TEN_KH').blur(function () {
//                $(this).closest('tr').removeClass('highlight_row');
//            });
        </script>     

        <style>                                                
            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }       
            .cls-over{
                overflow-y: scroll;
                height: 70vh;
                overflow-x: scroll;
            }
            .editDelete{
                border: 1px solid #999;
            }

            .editDelete td,th{
                border: 1px solid #999;
            }

        </style>
        <style type="text/css">
            .tg  {border-collapse:collapse;border-spacing:0;}
            .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
                   overflow:hidden;padding:10px 5px;word-break:normal;}
            .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
                   font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
            .tg .tg-oj1e{border-color:inherit;font-family:Verdana, Geneva, sans-serif !important;;font-size:11px;font-weight:bold;
                         text-align:center;vertical-align:top}
            .tg .tg-lqtb{border-color:inherit;font-family:Verdana, Geneva, sans-serif !important;;font-size:10px;font-style:italic;
                         text-align:center;vertical-align:middle}
            .tg .tg-36iq{border-color:inherit;font-family:Verdana, Geneva, sans-serif !important;;font-size:11px;font-style:italic;
                         text-align:center;vertical-align:top}
            .tg .tg-afpd{border-color:inherit;font-family:Verdana, Geneva, sans-serif !important;;font-size:10px;font-style:italic;
                         text-align:center;vertical-align:top}
            .tg .tg-yy2q{border-color:inherit;font-family:Verdana, Geneva, sans-serif !important;;font-size:11px;font-weight:bold;
                         text-align:center;vertical-align:middle}
            .tg .tg-0pky{border-color:inherit;text-align:left;vertical-align:top}
            .tg .tg-2t8h{border-color:inherit;font-family:Verdana, Geneva, sans-serif !important;;font-size:11px;text-align:left;
                         vertical-align:top}
            .tg .tg-r0kq{border-color:inherit;font-family:Verdana, Geneva, sans-serif !important;;text-align:left;vertical-align:top}
        </style>
        <link href="css/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body style="font-family: ">
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            </br>
            <div id="divTitle">
                <p>TỔNG HỢP KẾT QUẢ</p>
                <p>TRIỂN KHAI THỰC HIỆN THEO NGHỊ QUYẾT SỐ 68/NQ-CP VÀ QUYẾT ĐỊNH SỐ 23/2021/QĐ-TTG</p>
            </div>
            <s:hidden name="khoa_nhaptaycn"/>

            </br>


            <p style="text-align: right; margin-right: 10px;">Đơn vị: người, triệu đồng</p>
            <table class="tg">
                <thead>
                    <tr>
                        <th class="tg-yy2q" rowspan="3">Đơn vị</th>
                        <th class="tg-yy2q" colspan="9">Vay vốn trả lương ngừng việc</th>
                        <th class="tg-yy2q" colspan="9">Vay vốn trả lương phục hồi sản xuất cho người sử dụng lao động <br>bị tạm dừng hoạt động</th>
                        <th class="tg-yy2q" colspan="9">Vay vốn trả lương phục hồi sản xuất cho người sử dụng lao động <br>hoạt động trong lĩnh vực vận tải, hàng không, du lịch, dịch vụ lưu trú, <br>đưa người Việt Nam đi làm việc ở nước ngoài theo hợp đồng</th>
                    </tr>
                    <tr>
                        <td class="tg-yy2q" colspan="3">Nhận hồ sơ đề nghị <br>vay vốn</td>
                        <td class="tg-yy2q" colspan="3">Phê duyệt cho vay</td>
                        <td class="tg-yy2q" colspan="3">Giải ngân</td>
                        <td class="tg-oj1e" colspan="3">Nhận hồ sơ đề nghị <br>vay vốn</td>
                        <td class="tg-oj1e" colspan="3">Phê duyệt cho vay</td>
                        <td class="tg-oj1e" colspan="3">Giải ngân</td>
                        <td class="tg-oj1e" colspan="3">Nhận hồ sơ đề nghị <br>vay vốn</td>
                        <td class="tg-oj1e" colspan="3">Phê duyệt cho vay</td>
                        <td class="tg-oj1e" colspan="3">Giải ngân</td>
                    </tr>
                    <tr>
                        <td class="tg-yy2q">Số NSDLĐ</td>
                        <td class="tg-yy2q">Số lượt NLĐ <br>được hỗ trợ</td>
                        <td class="tg-yy2q">Số tiền</td>
                        <td class="tg-yy2q">Số NSDLĐ</td>
                        <td class="tg-yy2q">Số lượt NLĐ <br>được hỗ trợ</td>
                        <td class="tg-yy2q">Số tiền</td>
                        <td class="tg-yy2q">Số NSDLĐ</td>
                        <td class="tg-yy2q">Số lượt NLĐ <br>được hỗ trợ</td>
                        <td class="tg-yy2q">Số tiền</td>
                        <td class="tg-oj1e">Số NSDLĐ</td>
                        <td class="tg-oj1e">Số lượt NLĐ <br>được hỗ trợ</td>
                        <td class="tg-oj1e">Số tiền</td>
                        <td class="tg-oj1e">Số NSDLĐ</td>
                        <td class="tg-oj1e">Số lượt NLĐ <br>được hỗ trợ</td>
                        <td class="tg-oj1e">Số tiền</td>
                        <td class="tg-oj1e">Số NSDLĐ</td>
                        <td class="tg-oj1e">Số lượt NLĐ <br>được hỗ trợ</td>
                        <td class="tg-oj1e">Số tiền</td>
                        <td class="tg-oj1e">Số NSDLĐ</td>
                        <td class="tg-oj1e">Số lượt NLĐ <br>được hỗ trợ</td>
                        <td class="tg-oj1e">Số tiền</td>
                        <td class="tg-oj1e">Số NSDLĐ</td>
                        <td class="tg-oj1e">Số lượt NLĐ <br>được hỗ trợ</td>
                        <td class="tg-oj1e">Số tiền</td>
                        <td class="tg-oj1e">Số NSDLĐ</td>
                        <td class="tg-oj1e">Số lượt NLĐ <br>được hỗ trợ</td>
                        <td class="tg-oj1e">Số tiền</td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="tg-36iq">(1)</td>
                        <td class="tg-afpd">(2)</td>
                        <td class="tg-afpd">(3)</td>
                        <td class="tg-afpd">(4)</td>
                        <td class="tg-afpd">(5)</td>
                        <td class="tg-afpd">(6)</td>
                        <td class="tg-afpd">(7)</td>
                        <td class="tg-afpd">(8)</td>
                        <td class="tg-afpd">(9)</td>
                        <td class="tg-afpd">(10)</td>
                        <td class="tg-afpd">(11)</td>
                        <td class="tg-afpd">(12)</td>
                        <td class="tg-afpd">(13)</td>
                        <td class="tg-afpd">(14)</td>
                        <td class="tg-afpd">(15)</td>
                        <td class="tg-afpd">(16)</td>
                        <td class="tg-afpd">(17)</td>
                        <td class="tg-afpd">(18)</td>
                        <td class="tg-afpd">(19)</td>
                        <td class="tg-afpd">(20)</td>
                        <td class="tg-afpd">(21)</td>
                        <td class="tg-afpd">(22)</td>
                        <td class="tg-afpd">(23)</td>
                        <td class="tg-afpd">(24)</td>
                        <td class="tg-afpd">(25)</td>
                        <td class="tg-afpd">(26)</td>
                        <td class="tg-afpd">(27)</td>
                        <td class="tg-lqtb">(28)</td>
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">  
                        <tr>
                            <td class="tg-0pky">
                                <s:property  value="TEN" />
                                <input type="hidden"  value="<s:property  value="KHOA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KHOA" />
                                <input type="hidden"  value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" />
                                <input type="hidden"  value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" />
                                <input type="hidden"  value="<s:property  value="MAPGD" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" />
                                <input type="hidden"  value="<s:property  value="CO_TONGHOP" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP" />
                                <input type="hidden"  value="<s:property  value="MACN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN" />
                                <input type="hidden"  value="<s:property  value="THUTU" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" />
                                <input type="hidden"  value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" />
                                <input type="hidden"  value="<s:property  value="NHAPTAY" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" />
                                <input type="hidden"  value="<s:property  value="KIEUIN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KIEUIN" />
                            </td>
                            <td class="tg-2t8h" align = "right" >
                                <input type="text"  value="<s:property  value="D1" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D4" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D5" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D6" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D2 TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D7" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D8" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D9" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D2 TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D10" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D11" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D12" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D2 TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D13" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D14" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D15" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D2 TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D16" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D17" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D18" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="D2 TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D19" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D20" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D21" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" class="D2 TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D22" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D23" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D24" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24" class="D2 TEN_KH number2" onfocus="this.select();"/>
                            </td>
                            <td class="tg-2t8h">
                                <input type="text"  value="<s:property  value="D25" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-r0kq">
                                <input type="text"  value="<s:property  value="D26" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D26" class="D2 TEN_KH number" onfocus="this.select();"/>
                            </td>
                            <td class="tg-r0kq">
                                <input type="text"  value="<s:property  value="D27" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27" class="D2 TEN_KH number2" onfocus="this.select();"/>
                            </td>
                        </tr>
                    </s:iterator>
                </tbody>
            </table>

            <div id="number_in_word" style="display: none; font-size: 12pt; color: red;"> </div>

            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>

    </body>


</html>



<script>

    function isBlank(str) {
        if (str === null || str === "") {
            return true;
        }
        return false;
    }

    function getValue(str) {
        if (isBlank(str)) {
            return 0;
        } else {
            return parseFloat(str);
        }
    }

// Bo sung ham vaidateData
    function validateData() {
        var key = $("#khoa_nhaptaycn").val();
        if (key === 'QD23_004') {
            for (i = 0; i < 9; i++) {
                var iOrder = i*3+1;
                var iNameOfD1 = "[name='lstDulieuNt[0].D" + iOrder + "']";
                var iNameOfD2 = "[name='lstDulieuNt[0].D" + (iOrder+1) + "']";
                var iNameOfD3 = "[name='lstDulieuNt[0].D" + (iOrder+2) + "']";
                var iD1 = getValue($(iNameOfD1).val());
                var iD2 = getValue($(iNameOfD2).val());
                var iD3 = getValue($(iNameOfD3).val());
                if ((iD1 + iD2 + iD3) !== 0 && (iD1 === 0 || iD2 === 0 || iD3 === 0)) {
                    alert('Bạn phải nhập đầy đủ thông tin: Số NSDLĐ, Số lượt NLĐ được hỗ trợ, Số tiền');
                    return false;
                }
            }
        }
        return true;
    }

    $(document).ready(function () {

        $(".number").focus(function () {
            $(this).animate({
                width: '120px'
            }, "slow");
//            if ($(this).val() === $(this).attr('title'))
//            {
//                $(this).val('');
//            }
            var amount = parseInt($(this).val());
            var words = DocTienBangChu(amount);
            $("#number_in_word").text(words);
            $("#number_in_word").show();
        });

        $(".number2").focus(function () {
            $(this).animate({
                width: '120px'
            }, "slow");
//            if ($(this).val() === $(this).attr('title'))
//            {
//                $(this).val('');
//            }
            var amount = parseInt($(this).val()) * 1000000;
            var words = DocTienBangChu(amount);
            $("#number_in_word").text(words);
            $("#number_in_word").show();
        });
        $(".number").focusout(function () {
            $(this).animate({
                width: '50px'
            }, "slow");
            $(this).prop('title', this.value);
//            $("#number_in_word").hide();
        });

        $(".number2").focusout(function () {
            $(this).animate({
                width: '50px'
            }, "slow");
            $(this).prop('title', this.value);
//            $("#number_in_word").hide();
        });

        $(".number").each(function () {
            $(this).prop('title', this.value);
        });
        $(".number2").each(function () {
            $(this).prop('title', this.value);
        });

        $(".number").keyup(function () {
            var amount = parseInt($(this).val());
            var words = DocTienBangChu(amount);
            $("#number_in_word").text(words);
        });
        $(".number2").keyup(function () {
            var amount = parseInt($(this).val()) * 1000000;
            var words = DocTienBangChu(amount);
            $("#number_in_word").text(words);
        });
    });

</script>

