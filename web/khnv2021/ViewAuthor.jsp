<%-- 
    Document   : ViewAuthor
    Created on : Jun 17, 2021, 10:08:30 AM
    Author     : Nguyễn Phú Vinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kiểm duyệt KHTD</title>
        <style>
            *{
                font-family: tahoma;
                font-size: 13px;
            }

            table {
                width : 100%;
                border-top: 1px solid orange;
                border-left: 1px solid #c2c2c2;
                border-right: 1px solid #c2c2c2;
                border-bottom: 1px solid #c2c2c2;
                text-align : center;
                border-collapse : collapse;
            }
            table tr th, table tr td {
                border : 1px solid #c2c2c2;
            }


            table thead th {
                position: -webkit-sticky;
                position : sticky;
                top : 0;
                color: white;
                background-color : #04AA6D;
            }

            /* here is the trick */
            table tbody:nth-of-type(1) tr:nth-of-type(1) td {
                border-top: none !important;
            }
            table thead th {
                border-top: none !important;
                border-bottom: none !important;
                box-shadow: inset 0 0px 0 #c2c2c2,
                    inset 0 -1px 0 #c2c2c2;
            }

            table thead th {
                background-clip: padding-box
            }

            table thead { position: sticky; top: 0; z-index: 1; }

            th, td {
                text-align: left;
                border: 1px solid #c2c2c2;
                text-align: center;
                padding: 3px;
            }

            th{
                padding: 8px;
            }
            .sttCol>td{
                font-style: italic;
            }
            .clss-body-ngnhan{
                box-sizing: content-box;
                padding: 5px;
            }
            textarea
            {
                border:1px solid #000;
                width:100%;
                height: 100px;
            }
            .clss-lable{
                font-weight: bold;
            }
            .cls-over{
                overflow-y: scroll;
                height: 60vh;
            }
            .cmd{
                padding: 5px;
                background-image: linear-gradient(#f2f2f2,#c2c2c2);
                border: 1px solid #c2c2c2;
                border-radius: 2px;
                z-index: 99;
                margin-left: 5px;
            }
            hr{
                border-bottom: 0px;
                border-top: 1px solid lightgray;
            }
            .item {
                padding: 5px;
                text-align: right;
                border: 0px !important;
                outline: none;
            }
            .cls {
                background-color: lightgoldenrodyellow;
            }
        </style>
        <link href="css/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form id="idKhnv2021" name="nameKhnv2021">
            <div class="cls-fix">
                <div>
                    <span class="clss-lable">Đơn vị:</span>
                    <select id="cboDonvi" name="cboDonvi">
                        <option value="all">----Tất cả----</option>
                        <s:iterator value="lstPos">
                            <option value="<s:property value='PosCode'/>"><s:property value='PosName'/></option>
                        </s:iterator>
                    </select>
                    &nbsp;
                    <span class="clss-lable">Kế hoạch tín dụng năm:</span>
                    <select id="cboNam" name="cboNam">
                        <script>
                            var i, varNam;
                            varNam = new Date().getFullYear();
                            var text = "";
                            for (i = (varNam - 5); i <= (varNam + 5); i++) {
                                text += '<option value="' + i.toString() + '">Năm ' + i.toString() + '</option>';
                            }
                            document.write(text);
                        </script>
                    </select>
                    &nbsp;
                    <span class="clss-lable">Đợt thực hiện:</span>
                    <select  id="cboDot" name="cboDot">
                        <option value="1">Đợt I</option>
                        <option value="2">Đợt II</option>
                        <option value="3">Đợt III</option>
                        <option value="4">Đợt IV</option>
                    </select>
                    &nbsp;
                    <span class="clss-lable">Tổng hợp</span>
                    <select id="cboTonghop" name="cboTonghop">
                        <option value="Y">Tổng hợp các đơn vị trực thuộc</option>
                        <option value="N">Duyệt từng đơn vị</option>
                        <option value="R">Tổng hợp lại từ các đơn vị trực thuộc</option>
                        <option value="W">Phản hồi từ cấp trên</option>
                        <option value="S">Kiểm soát gửi/nhận</option>
                    </select>
                    &nbsp;                                  
                    <input type="button" value="Tải dữ liệu" id="cmdTaiDL" name="nameTaiDL" class="cmd"/>
                </div>
                <hr/>
                <div><span class="clss-lable">Nguyên nhân</span></div>
                <div class="clss-body-ngnhan">
                    <textarea id="strNguyennhan" name="strNguyennhan"><s:property value='strNguyennhan'/></textarea>
                </div>                
                <div id="idButton" style="padding: 0px 0px 6px 0px; height: 30px; display: inline-flex;">
                    <input type="button" value="Gửi cấp trên" id="cmdGuiDL" name="nameGuiDL" class="cmd"/>
                    <input type="button" value="Trả lại đơn vị" id="cmdTraLaiDL" name="nameTraLaiDL" class="cmd"/>
                    <input type="button" value="Lưu dữ liệu" id="idLuuDL" name="nameLuuDL" class="cmd"/>
                    &nbsp;
                    <div id="idViewMess" name="nameViewMess" style="font-weight: bold; color: red;"></div>
                </div>
            </div>
            <div class="cls-over">
                <div id="idViewData"></div>
            </div>
        </form> 
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script src="js/jquery.number.js"></script>
        <script>
                            $(document).ready(function () {
                                if (('<s:property value="CapBC"/>') == '3') {
                                    $('#cboTonghop option[value="W"]').remove();
                                    $('#cboTonghop option[value="R"]').remove();
                                    $('#cmdGuiDL').remove();
                                }
                                ;
                                $('#cmdTraLaiDL').hide();
                                $("#cboNam").val(new Date().getFullYear()).change();

                                //Tải dữ liệu
                                $("#cmdTaiDL").click({status: "0"}, SendData);
                                //Gửi dữ liệu
                                $("#cmdGuiDL").click({status: "1"}, SendData);
                                //Trả lại đơn vị
                                $("#cmdTraLaiDL").click({status: "2"}, SendData);
                                //Tải Lưu dữ liệu cấp CN
                                $("#idLuuDL").click({status: "3"}, SendData);
                                //Xử lý trạng thái các Element
                                $("#cboDonvi").change(function () {
                                    if ($("#cboDonvi").val().trim() == "all") {
                                        $('#idLuuDL').show();
                                        $('#cmdGuiDL').show();
                                        $('#cmdAuthor').hide();
                                        $('#cmdTraLaiDL').hide();
                                        $('#cboTonghop option')[0].selected = true;
                                    } else {
                                        $('#idLuuDL').hide();
                                        $('#cmdGuiDL').hide();
                                        $('#cmdAuthor').show();
                                        $('#cmdTraLaiDL').show();
                                        $('#cboTonghop option')[1].selected = true;
                                    }
                                });
                                $("#cboTonghop").change(function () {
                                    if ($("#cboTonghop").val().trim() == "Y") {
                                        $('#idLuuDL').show();
                                        $('#cmdGuiDL').show();
                                        $('#cmdAuthor').hide();
                                        $('#cmdTraLaiDL').hide();
                                        $('#cboDonvi option')[0].selected = true;
                                    }
                                    if ($("#cboTonghop").val().trim() == "N") {
                                        $('#idLuuDL').hide();
                                        $('#cmdGuiDL').hide();
                                        $('#cmdAuthor').show();
                                        $('#cmdTraLaiDL').show();
                                        $('#cboDonvi option')[1].selected = true;
                                    }
                                    if ($("#cboTonghop").val().trim() == "R") {
                                        $('#idLuuDL').show();
                                        $('#cmdGuiDL').show();
                                        $('#cmdAuthor').hide();
                                        $('#cmdTraLaiDL').hide();
                                        $('#cboDonvi option')[0].selected = true;
                                    }
                                    if ($("#cboTonghop").val().trim() == "W") {
                                        $('#idLuuDL').hide();
                                        $('#cmdGuiDL').hide();
                                        $('#cmdAuthor').hide();
                                        $('#cmdTraLaiDL').hide();
                                        $('#cboDonvi option')[0].selected = true;
                                    }
                                });
                            });
                            function SendData(event) {
                                var surl, sdata, idView, idMess, idForm, method, strMess;
                                surl = "SendAction.action?status=" + event.data.status;
                                idView = "#idViewData";
                                idMess = "#idViewMess";
                                idForm = "#idKhnv2021";
                                method = "POST";
                                sdata = jQuery(idForm).serialize();
                                $.ajax({
                                    url: surl,
                                    data: sdata,
                                    type: method,
                                    async: true,
                                    beforeSend: function () {
                                        $(idMess).html('<img src="imgs/newloading.gif"/>');
                                    },
                                    success: function (result) {
                                        if (["10", "11", "20", "21", "01", "30", "31"].includes(result)) {
                                            switch (result) {
                                                case "01":
                                                    strMess = 'Lỗi: Tải dữ liệu không thành công.';
                                                    $(idView).html('');
                                                    break;
                                                case "10":
                                                    strMess = '';
                                                    break;
                                                case "11":
                                                    strMess = 'Lỗi: khi gửi dữ liệu lên cấp trên.';
                                                    $(idView).html('');
                                                    break;
                                                case "20":
                                                    strMess = '<span style="color:green">Thành công: Hoàn trả dữ liệu cho đơn vị thành công.</span>';
                                                    break;
                                                case "21":
                                                    strMess = 'Lỗi: hoàn trả dữ liệu cho đơn vị.';
                                                    $(idView).html('');
                                                    break;
                                                case "30":
                                                    strMess = '<span style="color:green">Thành công: Lưu dữ liệu.</span>';
                                                    break;
                                                case "31":
                                                    strMess = 'Lỗi: Lưu dữ liệu.';
                                                    $(idView).html('');
                                                    break;
                                            }
                                            $(idMess).html(strMess);
                                        } else {
                                            if ($("#cboTonghop").val().trim() == "W") {
                                                $(idView).html(result);
                                                $('#idButton').hide();
                                            } else {
                                                $('#idButton').show();
                                                $(idView).html(result);
                                                $(idMess).html('');
                                            }
                                        }
                                    },
                                    error: function (result) {
                                        alert('Lỗi khi thực hiện.');
                                    }
                                });
                            }
        </script>
    </body>
</html>
