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
                $('input.number2').css({"text-align": "center"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $('.maPGD').css({"text-align": "left"});
                $('.tenPGD').css({"text-align": "right"});
//                $(".maPGD").css({"width": "150px"});
//                $(".maPGD").css({"width": "140px"});
            });</script>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <SCRIPT language="javascript">

            $(document).ready(function() {

                $('input.number').css({"text-align": "right"});
//                $('input.number2').css({"text-align": "right"});
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

                $(".TD_MAKH").css({"width": "80px"});
                $(".TD_MAKH").css({"text-align": "center"});
                $(".TD_DU_NO").css({"width": "70px"});
                $(".TD_TEN_KH").css({"width": "130px"});
                $(".TD_SOKU").css({"width": "115px"});


                $(".TD_NGUYEN_NHAN").css({"width": "200px"});
                $(".TD_NGUYEN_NHAN").css({"text-align": "center"});


                $("#idRejecttmp").click(function() {
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
                    var p1;
                    if (len_nn_kdc > 0)
                    {
                        p1 = {ngay_dcpt: ngay_dcpt,
                            totruong_dcpt: totruong_dcpt,
                            lstsaveDcno: [
                                {"sSoku": soku,
                                    "bNogoc": sNogoc_Clech,
                                    "bNolai": sNolai_Clech,
                                    "bDuCasa": sSoducasa,
                                    "sNguyennhan": sNguyennhan_kdc,
                                    "sTtDautu": "R"}
                            ]
                        };
                    }
                    else {
                        p1 = {ngay_dcpt: ngay_dcpt,
                            totruong_dcpt: totruong_dcpt,
                            lstsaveDcno: [
                                {"sSoku": soku,
                                    "bNogoc": sNogoc_Clech,
                                    "bNolai": sNolai_Clech,
                                    "bDuCasa": sSoducasa,
                                    "sNguyennhan": sNguyennhan_Lech,
                                    "sTtDautu": sThuctrang_Dtdt}
                            ]
                        };
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
                                alert("Bạn đã lưu dữ liệu thành công ")
                                self.opener.document.forms['paginationForm'].idSubmit.click();
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
                $('#divBrowseRisk').empty();
                var sNguyennhan_kdc = $("#sNguyennhan_kdc").val().length;
                if (sNguyennhan_kdc > 0)
                {
                    alert('Để nhập được dữ liệu trường này bạn phải để trắng dữ liệu nhập vào nguyên nhân không đối chiếu được-' + sNguyennhan_kdc + '-');
                    $('#divBrowseRisk').html("<h2 style='color: red'>Để nhập được dữ liệu trường này bạn phải để trắng dữ liệu nhập vào nguyên nhân không đối chiếu được ! </h2>");
                }
                else
                {
                    $("#sNguyennhan_kdc").attr("readonly", "readonly");
                    $("#sNguyennhan_kdc").css("background", "#CCCCCC");
                }
            }
            function disablecontrol()
            {
                $('#divBrowseRisk').empty();
                var sNguyennhan_Lech = $.trim($("#sNguyennhan_Lech").val()).length;
                var sThuctrang_Dtdt = $.trim($("#sThuctrang_Dtdt").val()).length;
//                alert('sNguyennhan_Lech=' + sNguyennhan_Lech + ' sThuctrang_Dtdt=' + sThuctrang_Dtdt);
                if (sNguyennhan_Lech > 0 || sThuctrang_Dtdt > 0)
                {
                    alert('Bạn đã nhập dữ liệu nguyên nhân chênh lệch và thực trạng đối tượng đầu tư rồi nên không thể nhập dữ liệu vào trường này');
                    $('#divBrowseRisk').html("<h2 style='color: red'>Bạn đã nhập dữ liệu nguyên nhân chênh lệch và thực trạng đối tượng đầu tư rồi nên không thể nhập dữ liệu vào trường này ! </h2>");
                }
                else
                {
                    $("#sNguyennhan_kdc").removeAttr("readonly");
                    $("#sNguyennhan_kdc").css("background", "#FFFFFF");
                    $("#sNguyennhan_Lech").val('');
                    $("#sThuctrang_Dtdt").val('');

                    $("#sNogoc_Clech").val('0');
                    $("#sNolai_Clech").val('0');
                    $("#sSoducasa").val('0');


                    $("#sNguyennhan_Lech").attr("readonly", "readonly");
                    $("#sThuctrang_Dtdt").attr("readonly", "readonly");
                    $("#sNogoc_Clech").attr("readonly", "readonly");
                    $("#sNolai_Clech").attr("readonly", "readonly");
                    $("#sSoducasa").attr("readonly", "readonly");


                    $("#sNguyennhan_Lech").css("background", "#CCCCCC");
                    $("#sThuctrang_Dtdt").css("background", "#CCCCCC");
                    $("#sNogoc_Clech").css("background", "#CCCCCC");
                    $("#sNolai_Clech").css("background", "#CCCCCC");
                    $("#sSoducasa").css("background", "#CCCCCC");
                }

            }
            function on_change()
            {
                $('#divBrowseRisk').empty();
                var sNguyennhan_kdc = $("#sNguyennhan_kdc").val().length;
                if (sNguyennhan_kdc < 1)
                {
                    $("#sNguyennhan_kdc").val('');
                    $("#sNguyennhan_Lech").removeAttr("readonly");
                    $("#sThuctrang_Dtdt").removeAttr("readonly");
                    $("#sNogoc_Clech").removeAttr("readonly");
                    $("#sNolai_Clech").removeAttr("readonly");
                    $("#sSoducasa").removeAttr("readonly");


                    $("#sNguyennhan_Lech").css("background", "#ffffff");
                    $("#sThuctrang_Dtdt").css("background", "#ffffff");
                    $("#sNogoc_Clech").css("background", "#FFCCBA");
                    $("#sNolai_Clech").css("background", "#FFCCBA");
                    $("#sSoducasa").css("background", "#FFCCBA");
//                    alert('thay doi roi sNguyennhan_kdc');
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
                    <span id="idTitle">Thông tin chi tiết khách hàng</span>
                    <hr/>
                    <s:iterator value="#attr.lstModelDcptNo" var="modelDcpt" status="rowstatus">
                        <table border="1" class="editDelete" align="center">
                             <tr>
                                <td>Mã khách hàng</td>
                                <td><s:property value='sMakh'/></td>		
                             </tr>
                             <tr>
                                <td>Tên khách hàng</td>
                                <td><s:property value='sTenkh'/></td>		
                             </tr>
                             <tr>
                                <td>Chương trình</td>
                                <td><s:property value='sCtrinh'/></td>		
                             </tr>
                             <tr>
                                <td>Mã sản phẩm</td>
                                <td><s:property value='sMa_Spham'/></td>		
                             </tr>
                             <tr>
                                <td>Tài khoản casa</td>
                                <td><s:property value='sTk_Casa1'/></td>		
                             </tr>
                             <tr>
                                <td>Ngày số liệu</td>
                                <td><s:property value='sNgaybc'/></td>		
                             </tr>
                             <tr>
                                <td>Tổng dư nợ</td>  
                                <td>
                                    <input value="<s:property value='sTongsotien'/>" name="sTongsotien" class="number2" readonly="true"/>
                                </td>
                                
                             </tr>
                             <tr>
                                <td>Nợ trong hạn</td>     
                                <td>
                                    <input value="<s:property value='sDno_Than'/>" name="sDno_Than" class="number2" readonly="true"/>
                                </td>
                                
                             </tr>
                             <tr>
                                <td>Nợ quá hạn</td>    \
                                <td>
                                    <input value="<s:property value='sDno_Qhan'/>" name="sDno_Qhan" class="number2" readonly="true"/>
                                </td>
                                
                             </tr>
                             <tr>
                                <td>Nợ khoanh</td>  
                                <td>
                                    <input value="<s:property value='sDno_Khoanh'/>" name="sDno_Khoanh" class="number2" readonly="true"/>
                                </td>
                                
                             </tr>
                             <tr>
                                <td>Nợ lãi</td>   
                                <td>
                                    <input value="<s:property value='sLai_Ton'/>" name="sLai_Ton" class="number2" readonly="true"/>
                                </td>
                                
                             </tr>
                             <tr>
                                <td>Số dư casa</td>
                            	
                                <td>
                                    <input value="<s:property value='sSodu_Casa'/>" name="sSodu_Casa" class="number2" readonly="true"/>
                                </td>
                             </tr>
                              
                                                                                      
                            </table>


                        <div id="divBrowseRisk"></div>
                        <!--</br>-->
                        <table class="tableKhnv" align="center">
                            
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <hr/>
                                </td>
                            </tr>
                            <tr align="center">

                                <td align="center">
                                    <td align="center">
                                    <sj:submit id="idClose" name="nameClose" value="Thoát" onclick="closeSelf()"
                                               cssStyle="margin-left:25px; float: center;height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"></sj:submit>
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

