<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<link rel="stylesheet" type="text/css"  href="css/css/style.css" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<s:head/>
<sj:head/>
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css"  href="css/css/style.css" />


        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script type='text/javascript'>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });

            $.subscribe("beforediv_send", function (event, data) {
                $("#loadingImageDiv_data").show();
            });
            $.subscribe("completediv_send", function (event, data) {
                $("#loadingImageDiv_data").hide();
            });
        </script>     

        <script>


            function initTable()
            {
                var table = document.getElementById("tableCbssDetail01");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    var matmp = getMabyNumber(i);//   

                    if (matmp == 1)
                    {
                        $('input:checkbox[id=' + i + ']').attr('checked', true);
                    }
                }
            }

            function getMabyNumber(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }

            function pheduyetP001(khoa1, khoa2) {
                var ht1 = screen.availHeight - 50;
                var wt1 = screen.width - 50;
                var left1 = 25;
                var top1 = 25;

                var url = "pheduyetP001.action?khoa1=" + khoa1 + "&khoa2=" + khoa2;
                popup = window.open(url, "_blank",
                        "directories=no, status=no,width=550, height=200,top=0,left=0");
            }

            function onSaveTmp()
            {
//                $('#message_suc_err').empty();
                $('#divExportReport').empty();
//                var khoa_ktgs = $("#khoa").val();

//                var poscd = getposfromtreecheck();
//                alert(khoa_ktgs);

                $("#idSaveTmp")[0].click();
                bsubmit = false;
            }


            function onSave()
            {
//                $('#message_suc_err').empty();
                $('#divExportReport').empty();
//                var khoa_ktgs = $("#khoa").val();

//                var poscd = getposfromtreecheck();
//                alert(khoa_ktgs);

                $("#idSave")[0].click();
                bsubmit = false;
            }


        </script>
        <style>
            textarea{
                height: 20px;
                border: 1px solid #017230;
                width: 100%;
                resize: none;
                overflow: hidden;
            }
            .cls1{ width:3px;}
            .cls2{width: 0px; display: none;}
            .cls3{width: 20px;}
            .cls4{width: 80px;}
            .cls5{width: 110px;}
            .cls6{width: 110px;}
            .cls7{width: 110px;}
            .clsduyet{width: 5px;
                      font-size: 12px;
                      font-family: tahoma;}
            span{
                font-family: Tahoma;
                font-size: 12px;
                color: #333;
            }
            .TD_BUTTON1{
                text-align: center;
            }
            .cmdmorong{
                border: 0px;
                color: yellow;
                text-decoration: none;
                background-color: #017230 !important;
                cursor: pointer;
            }
        </style>
    </head>

    <s:hidden name="codeUpdate" id="update_cbss_id"/>
    <body style="font-family: ">
        <input type="text" value="" id="chkChange" style="display:none;">
        <s:form id="id_giaitrinhP001" name="NagiaitrinhP001"  theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            <s:hidden name="khoa_cbss"/>
            </br>
            <table border="0" style="width: 95%"  align="center">
                <tr>
                    <td>
                        <div id="loadingImageDiv_data" style="margin-left: 20px;display: none;" >
                            <img id="loadingImage" src='img/loading.gif' border='0' >
                        </div>

                    </td>
                    <td>
                        <div id = "divExportReport"></div>
                    </td>            
                    <td style="text-align: right;">
                        <s:if test="Grade.equalsIgnoreCase('1')">                                       
                            <s:url id="idSavedataTmp" action="saveDataTmp.action"></s:url>                                      
                            <sj:submit id="idSaveTmp" name="nameSend" href="%{idSavedataTmp}" value="        Lưu nháp        " targets="divExportReport"
                                       onBeforeTopics="beforediv_send"
                                       onCompleteTopics="completediv_send" cssStyle="display:none"/>
                            <input type="button" id="idSendtmp" name="nameidSendtmp"  onclick="onSaveTmp()" value="        Lưu nháp        " class="update_gtrinh" style="color: yellow;background-color: #017230;"/>
                        </s:if> 
                    </td>
                    <td style="color: red; font-family: Arial; font-size: 7px;">
                        <s:actionmessage escape="false"></s:actionmessage>

                            <!--<td style="text-align: right; width: 120px">-->   
                        <td style="text-align: right; width: 100px">
                        <s:if test="Grade.equalsIgnoreCase('1')">                                       
                            <s:url id="idSavedata" action="saveGiaitrinhP001.action"></s:url>                                      
                            <sj:submit id="idSave" name="nameSend1" href="%{idSavedata}" value="Cập nhật giải trình" targets="divExportReport"
                                       onBeforeTopics="beforediv_send"
                                       onCompleteTopics="completediv_send" cssStyle="display:none"/>
                            <input type="button" id="idSendtmp1" name="nameidSendtmp1"  onclick="onSave()" value="Cập nhật giải trình" class="update_gtrinh" style="color: yellow;background-color: #017230;"/>
                        </s:if> 
                    </td>
                    </td>
                </tr>
            </table>
            <table border="1" class="tbl_cbss_css" id="tableCbssDetail01" style="width: 97%"  align="center">
                <tr>
                    <th colspan="10" style="font-size: 12px; color: yellow; height: 50px;" align="left">
                        Ngày số liệu: <s:property  value="ngay_bc" /> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; Tên PGD: <span id="strPGD" style="color: yellow;"></span> &nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; Cảnh báo sai sót:  <s:property  value="strComment" />
                    </th>
                </tr>
                <tr style="font-size: 12px;">
                    <th class="cls1">Mở rộng</th>
                    <th class="cls1">TT</th>
                    <th class="cls2">Tên tỉnh</th>    
                    <th class="cls4">Tên xã</th>
                    <th class="cls5">Tên thôn</th>
                    <th class="cls6">Tên Tổ trưởng</th>
                    <th class="cls3">Mã KH</th>
                    <th class="cls7">Tên khách hàng</th>
                    <th class="cls8">Mô tả</th>
                    <th class="cls9">Giải trình
                        <input type="button" value="mở rộng/Thu nhỏ" class="cmdmorong" id="cmdmorong1">
                    </th>
                    <th>Duyệt</th>  
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">     
                    <tr>
                        <td style="text-align: center;" class="expand">&raquo;</td>
                        <td align = "center">
                            <input type="hidden"  value="<s:property  value="D17"/>"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17"
                                   readonly="true"/>
                            <span><s:property  value="THUTU" /></span>
                        </td>
                        <td align = "left" class="cls2">
                            <span><s:property  value="D7" /></span>
                        </td>
                        <td align = "left" class="cls2">
                            <span><s:property  value="D8" /></span>
                            <input type="hidden"  value="<s:property  value="D8"/>" id="idPGD"/>
                        </td>
                        <td align = "left" class="cls4" >
                            <span><s:property  value="D9" /></span>
                        </td>
                        <td align = "left" class="cls5" >
                            <span><s:property  value="D10" /></span>
                        </td>
                        <td align = "left" class="cls6" >
                            <span><s:property  value="D11" /></span>
                        </td>
                        <td align = "left" class="cls3">
                            <span><s:property  value="D6" /></span>
                        </td>
                        <td align = "left" class="cls7" >
                            <span><s:property  value="D12" /></span>
                        </td>
                        <td readonly align = "left" >
                            <textarea id="sNoiDung" class="sNoiDung" oninput="auto_grow(this)"
                                      name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"
                                      style="width: 100%;background-color: #999;color: #fff;" readonly="readonly" rows="3"><s:property value='D15'/></textarea>
                        </td>
                        <td align = "left" >
                            <textarea id="sGiaitrinh" class="sGiaitrinh" onchange="chkChange()" oninput="auto_grow(this)"
                                      name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16"
                                      rows="3" <s:property value='D29'/>><s:property value='D16'/></textarea>
                        </td>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <td class="clsduyet">
                                <s:property  value="D30"/>
                            </td>
                        </s:if>
                        <s:if test="Grade.equalsIgnoreCase('2')">
                            <td align = "center" style="font-size:12px; color: red; font-weight: bold;">
                                <s:url id="authCbssUrl" value="pheduyetP001.action">
                                    <s:param name="khoa_detail" value="khoa_detail"/>
                                    <s:param name="ngay_bc" value="ngay_bc"/>
                                    <s:param name="mapgd" value="mapgd"/>
                                    <s:param name="khoaduyet" value="D17"/>
                                </s:url>
                                <s:a href="%{authCbssUrl}" cssStyle="text-decoration: none;color: red;">
                                    <s:property  value="D30"/>
                                </s:a>
                            </td>
                        </s:if>
                    </tr>                        
                </s:iterator>
            </table>                    
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            $(document).ready(function () {
                $(".cmdmorong").click(function () {
                    if ($("textarea").height() > 20) {
                        $("textarea").css("height", "20px");
                    } else {
                        $("textarea").each(function () {
                            this.style.height = this.scrollHeight + "px";
                        });
                    }
                    ;
                });
                $("#strPGD").html($("#idPGD").val());
                $(".expand").click(function () {
                    $("textarea").css("height", "20px");
                    var set_height = $(".sNoiDung:eq(" + ($(this).closest("tr").index() - 2) + ")").prop('scrollHeight');
                    $(".sNoiDung:eq(" + ($(this).closest("tr").index() - 2) + ")").css('height',set_height);
                    $(".sGiaitrinh:eq(" + ($(this).closest("tr").index() - 2) + ")").css('height',set_height);
                });
            });
            function auto_grow(element) {
                $(element).css("height", (element.scrollHeight) + "px");
                $("#chkChange").val('change');
            }
            ;
            function chkChangVal(element) {
                var chkChange = $("#chkChange").val();
                if (element == '') {
                    if (chkChange == 'change') {
                        var chkval = confirm('Dữ liệu của bạn chưa được lưu.Bạn có muốn tiếp tục đóng cửa sổ không ?');
                        if (chkval == true) {
                            window.close();
                        }
                    }
                    ;
                } else {
                    $("#chkChange").val(null);
                    $("#btnSubmit").trigger('click');
                }
                ;
            }
            window.onbeforeunload = chkChangVal('');

        </script>
    </body>
</html>
