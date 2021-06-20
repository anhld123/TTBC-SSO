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
            $(".TD_DU_NO").css({"width": "75px"});
            $(".TD_TEN_KH").css({"width": "160px"});
            $(".TD_SOKU").css({"width": "115px"});


            $(".TD_NGUYEN_NHAN").css({"width": "150px"});
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
        <s:form id="frmDataDc" name="frmDataDc" action="saveDataDc.action" theme="simple" align="center">
            </br>
            <table border="1" class="editDelete" align="center">
                <tr>
                    <th rowspan="2">Tổng số món</th>
                    <th rowspan="2">Tổng tiền</th>
                    <th colspan="3">Dư nợ</th>
                    <th rowspan="2">Nợ lãi</th>
                    <th rowspan="2">Số dư casa</th>
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
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sNolai" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sSoduCasa" /></td>
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

                    <th class="TD_DU_NO" colspan="6">Số liệu tại NHCSXH</th> 

                    <th class="TD_DU_NO" colspan="3" rowspan="2">Chênh lệch</th> 

                    <th class="TD_NGUYEN_NHAN" rowspan="3">Nguyên nhân chênh lệch</th>

                    <th class="TD_SOKU" rowspan="3">Thực trạng đối tượng đầu tư</th>
                </tr>
                <tr>
                    <th class="TD_DU_NO" colspan="4">Nợ gốc</th> 
                    <th class="TD_DU_NO" rowspan="2">Nợ lãi</th> 
                    <th class="TD_DU_NO" rowspan="2">Dư TG</th> 
                </tr>
                <tr>
                    <th class="TD_DU_NO">Tổng số</th>
                    <th class="TD_DU_NO" >Nợ trong hạn</th>                  

                    <th class="TD_DU_NO">Nợ quá hạn</th>    
                    <th class="TD_DU_NO">Nợ khoanh</th> 

                    <th class="TD_DU_NO">Nợ gốc</th> 
                    <th class="TD_DU_NO">Nợ lãi</th> 
                    <th class="TD_DU_NO">Tiền gửi</th> 
                </tr>

                <s:iterator value="#attr.lstModelDcptNo" var="modelDcpt" status="rowstatus">

                    <%--<sj:div id="div_id_%{#rowstatus.index}">--%>
                    <%--<s:if test="#rowstatus.even == true">--%>
                    <!--<input type="hidden" id="s" name="sTk_Casa1" value="id_tr_<s:property  value="%{#rowstatus.index}" />"/>-->
                    <tr class="ac_odd" id="id_tr_<s:property  value="%{#rowstatus.index}" />">
                    <input type="hidden" id="sTk_Casa1_<s:property  value="%{#rowstatus.index}" />" name="sTk_Casa1" value="<s:property  value="sTk_Casa1"/>"/>
                    <input type="hidden" id="sTk_Casa2_<s:property  value="%{#rowstatus.index}" />" name="sTk_Casa2" value="<s:property  value="sTk_Casa2"/>"/>
                    <%--</s:if>--%>
                    <%--<s:else>--%>
                    <!--<tr class="ac_odd">-->
                    <%--</s:else>--%>
                    <td align = "center" class="TD_CHON"> 
                        <s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="lstsaveDcno[%{#rowstatus.index}].sSoku" fieldValue="%{sSoku}"/>
                    </td>
                    <td align = "left" class="TD_TEN_KH">
                        <input type="text" value="<s:property  value="sTenkh" />" 
                               name="sTenkh" class="TEN_KH" onfocus="this.select()" readonly="true"/>
                    </td>

                    <td align = "center" class="TD_SOKU"> 
                        <a href="javascript:hienthichitiet('<s:property value="sSoku"/>','<s:property  value="%{#rowstatus.index}" />' )" class="SOKU linkKh">
                            <s:property value='sSoku'/>
                        </a> 
                        <!--lstsaveDcno[%{#rowstatus.index}].sSoku-->
                        <%--<s:hidden name="lstsaveDcno[%{#rowstatus.index}].sSoku" value="sSoku"/>--%>
                        <!--<input type="hidden" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].sSoku" value="<s:property value='sSoku'/>" id="<s:property value='sSoku'/>"/>-->
                    </td>

                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sTongsotien'/>" name="sTongsotien" class="DU_NO number2"
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
                        <input type="text" value="<s:property value='sNogoc_Clech'/>" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].bNogoc" class="DU_NO number2" 
                               onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           isNumber(this.value)" onfocus="this.select()" style="background-color: #FFCCBA"
                               id="duno_lech_<s:property  value="%{#rowstatus.index}" />"/>
                    </td>

                    <td align = "right" class="TD_DU_NO">
                        <input type="text" value="<s:property value='sNolai_Clech'/>" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].bNolai" class="DU_NO number2" 
                               onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           isNumber(this.value)" onfocus="this.select()" style="background-color: #FFCCBA"
                               id="lai_lech_<s:property  value="%{#rowstatus.index}" />"/>
                    </td>

                    <s:if test="sTk_Casa1.equalsIgnoreCase(' ') || sTk_Casa2.equalsIgnoreCase(' ')">
                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sSoducasa_Clech'/>" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].bDuCasa" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               isNumber(this.value)" onfocus="this.select()" style="background-color: #CCCCCC" readonly="true"
                                   id="casa_lech_<s:property  value="%{#rowstatus.index}" />"/>
                        </td>
                    </s:if>
                    <s:else>
                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sSoducasa_Clech'/>" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].bDuCasa" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               isNumber(this.value)" onfocus="this.select()" style="background-color: #FFCCBA"
                                   id="casa_lech_<s:property  value="%{#rowstatus.index}" />"/>
                        </td>
                    </s:else>
                    <td align = "right" class="TD_NGUYEN_NHAN">
                        <input type="text" value="<s:property value='sNguyennhan_Lech'/>" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].sNguyennhan" class="DU_NO" 
                               onfocus="this.select()" style="background-color: #FFCCBA" 
                               id="nn_lech_<s:property  value="%{#rowstatus.index}" />"/>
                    </td>

                    <td align = "right" class="TD_SOKU">
                        <input type="text" value="<s:property value='sThuctrang_Dtdt'/>" name="lstsaveDcno[<s:property  value="%{#rowstatus.index}" />].sTtDautu" class="DU_NO" 
                               onfocus="this.select()" style="background-color: #FFCCBA"
                               id="tt_dautu_<s:property  value="%{#rowstatus.index}" />"/>
                    </td>
                </tr>
                <%--</sj:div >--%>
            </s:iterator>

        </table>

        <sj:submit id="idSaveDcNo" name="idSaveDcNo" 
                   targets="divBrowseRisk" 
                   cssClass="metroButtonStyle" 
                   value="Thêm" onBeforeTopics="batdauduyet" onCompleteTopics="ketthucduyet" cssStyle="display: none"></sj:submit>
    </s:form>
    <s:form action="loadDataDcNo.action" id="paginationForm">
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
</body>
</html>