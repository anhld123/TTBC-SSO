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
            padding: 3px 0px 3px 3px;
            margin: 5px 1px 3px 0px;
            border: 1px solid rgba(81, 203, 238, 1);
        }
        .datepicker{
        }
    </style>
    <SCRIPT language="javascript">
        $.subscribe('batdauduyet', function(event, data) {
            $("#divMessage").show();
        });

        $.subscribe('ketthucduyet', function(event, data) {
            $("#divMessage").hide();
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
            $(".MAKH").css({"width": "100%"});
            $(".MAKH").css({"text-align": "center"});
            $(".DU_NO").css({"width": "100%"});
            $(".TEN_KH").css({"width": "100%"});
            $(".SOKU").css({"width": "100%"});

            $(".TD_CHON").css({"width": "30px"});
            $(".TD_MAKH").css({"width": "80px"});
            $(".TD_MAKH").css({"text-align": "center"});
            $(".TD_DU_NO").css({"width": "75px"});
            $(".TD_TEN_KH").css({"width": "130px"});
            $(".TD_SOKU").css({"width": "115px"});


            $(".TD_NGUYEN_NHAN").css({"width": "150px"});
            $(".TD_NGUYEN_NHAN").css({"text-align": "center"});

        });
        function hienthichitiet(soku) {
            var ht1 = screen.availHeight - 100;
            var wt1 = 1050;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 10;

            var ngay_dcpt = $("#ngay_dcpt").val();
            var totruong_dcpt = $("#totruong_dcpt").val();
            var dvut_dcpt = $("#dvut_dcpt").val();
            var url = "getDetialCustomerDcNo.action?soku=" + soku + "&ngay_dcpt=" + ngay_dcpt +
                    "&dvut_dcpt=" + dvut_dcpt + "&totruong_dcpt=" + totruong_dcpt;
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
                if (parseFloat(value) > 99999999999) {
                    result = false;
                    alert('Giá trị bạn nhập vượt quá giới hạn!');
                    //Dua ra canh bao
//                        $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Giá trị bạn nhập vượt quá giới hạn!');
                    focus();
                    return false;
                }
            }
        }
        
         $(document).ready(function() {
            $("#allCheck").change(function() {
                $(".checkbox1").prop('checked', $(this).prop("checked"));
            });
        });
    </script>
<!--    <script type="text/javascript" src="js/pagination.js">
       
    </script>-->

    <body width = "100%">

        <s:form id="frmDataDc" name="frmDataDc" action="SendDataPln.action" theme="simple" align="center">
            </br>
            <table border="1" class="editDelete" align="center">
                <tr>
                    <th rowspan="2">Tổng số món</th>
                    <th rowspan="2">Tổng tiền</th>
                    <th colspan="3">Dư nợ</th>
                    <th class="TD_DU_NO" rowspan="2">Nợ lãi</th>
<!--                    <th rowspan="2">Số dư casa</th>-->
                    <th colspan="2">Phân tích nợ</th>
                </tr>
                <tr>                   
                    <th>Nợ trong hạn</th>
                    <th>Nợ quá hạn</th>
                    <th>Nợ khoanh</th>
                    <th>Nợ có khả năng trả</th>
                    <th>Nợ không có khả năng trả</th>
                </tr>
                <s:iterator value="#attr.lstViewTotalCust" var="modelView" status="rowstatus">
                    <tr>                  
                        <td style="text-align: center; color: #007fff; font-weight: bold;"><s:property  value="sSoKh" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sTongtien" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sNothan" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sNoqhan" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sNokhoanh" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sNolai" /></td>
                        <!--<td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sSoduCasa" /></td>-->
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sNoNCK" /></td>
                        <td style="text-align: right; color: #007fff; font-weight: bold;"><s:property  value="sNoKCKN" /></td>
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
            </br>
            <table border="1" class="editDelete" align="center">
                <tr>
                    <th class="TD_TEN_KH" rowspan="2">Tổ</th>
                    <th class="TD_DU_NO" rowspan="2">Tổng số món</th>
                     <th class="TD_DU_NO" rowspan="2">Tổng dư nợ</th> 
                    <th class="TD_DU_NO" colspan="2">Chênh lệch</th> 

                    <th class="TD_DU_NO" colspan="2" >Phân loại nợ</th> 
                </tr>
                <tr>
                    <th class="TD_DU_NO">Nợ gốc</th>
                    <th class="TD_DU_NO" >Nợ lãi</th>                  

                    <!--<th class="TD_DU_NO">Tiết kiệm</th>-->    
                    <th class="TD_DU_NO">Nợ có khả năng</th> 

                    <th class="TD_DU_NO">Nợ không có khả năng</th> 
                </tr>
                <s:iterator value="#attr.lstsenddcpt" var="modelDcpt" status="rowstatus">
                    <s:if test="#rowstatus.even == true">
                        <tr class="ac_odd">
                        </s:if>
                        <s:else>
                        <tr class="ac_odd">
                        </s:else>
                        <td align = "left" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="sMato" />" 
                                   name="sMato" class="TEN_KH" onfocus="this.select()" />
                        </td>

                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sSoKH'/>" name="sSoKH" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true"/>
                        </td>

                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sNogoc'/>" name="sNogoc" class="DU_NO number2"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true"/>
                        </td>
                        
                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sCLGoc'/>" name="sCLGoc" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true"/>
                        </td>
                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sCLLai'/>" name="sCLLai" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true"/>
                        </td>
<!--                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sCLTietkiem'/>" name="sCLTietkiem" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true"/>
                        </td>-->
                        
                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sNoCKN'/>" name="sNoCKN" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true"/>
                        </td>
                        
                        <td align = "right" class="TD_DU_NO">
                            <input type="text" value="<s:property value='sNoKCKN'/>" name="sNoKCKN" class="DU_NO number2" 
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }" onfocus="this.select()" readonly="true"/>
                        </td>
                        
                    </tr>
                </s:iterator>
            </table>

            <sj:submit id="idSendDcPtNo" name="idSendDcPtNo" 
                       targets="divBrowseRisk" 
                       cssClass="metroButtonStyle" 
                       value="Thêm" onBeforeTopics="batdauduyet" onCompleteTopics="ketthucduyet" cssStyle="display: none"></sj:submit>
        </s:form>
        <s:form action="loadDataViewSend.action" id="paginationForm">
            <s:iterator value="poscd" status="row">
                <s:hidden name="poscd[%{#row.index}]" />
            </s:iterator>
            <s:hidden name="ngay_dcpt" id="ngay_dcpt"/>
            <s:hidden name="dvut_dcpt" id="dvut_dcpt"/>
            <s:hidden name="totruong_dcpt" id="totruong_dcpt"/>
            <s:hidden name="nguon_von" id="nguon_von"/>
            <s:hidden name="chuongtrinh" id="chuongtrinh"/>
            <%--<%@ include file="/dcpt_no/pagination.jsp" %>--%>
            <sj:submit value="submit" id="idSubmit" name="idSubmit" targets="divExportReport" cssStyle="display: none" 
                       onBeforeTopics="batdauloaddata" onCompleteTopics="hoanthanhloaddata"/>
        </s:form>
        <div id="divBrowseRisk"></div>
    </body>
</html>