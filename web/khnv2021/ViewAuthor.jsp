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
                font-size: 12px;
            }
            table {
                border-collapse: collapse;
                width: 100%;
                height: 1000px;
            }

            table thead { position: sticky; top: 0; z-index: 1; }

            th, td {
                text-align: left;
                padding: 8px;
                border: 1PX solid #f2f2f2;
                text-align: center;
            }

            tr:nth-child(even){background-color: #f2f2f2}

            th {
                background-color: #04AA6D;
                color: white;
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
                height: 69vh;
            }
            .cmd{
                padding: 5px;
                background-image: linear-gradient(#f2f2f2,#c2c2c2);
                border: 1px solid #c2c2c2;
                border-radius: 2px;
                z-index: 99;
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
                            for (i = (varNam - 10); i <= (varNam + 50); i++) {
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
                        <option value="Y">Tất cả các đơn vị trực thuộc</option>
                        <option value="N">Từng đơn vị</option>
                    </select>
                    &nbsp;                                  
                    <input type="button" value="Tải dữ liệu" id="cmdTaiDL" name="nameTaiDL" class="cmd"/>
                </div>
                <hr/>
                <div><span class="clss-lable">Nguyên nhân</span></div>
                <div class="clss-body-ngnhan">
                    <textarea id="strNguyennhan" name="strNguyennhan"></textarea>
                </div>
                <hr/>
                <div style="display: inline-flex; height: 30px;">
                    <input type="button" value="<s:property value='btnSend'/>" id="cmdGuiDL" name="nameGuiDL" class="cmd"/>
                    &nbsp; 
                    <input type="button" value="Trả lại đơn vị" id="cmdTraLaiDL" name="nameTraLaiDL" class="cmd"/>
                    &nbsp; &nbsp;
                    <div id="idViewMess" name="nameViewMess" style="height: 100%;display: flex; align-items: center;font-weight: bold; color: red;"></div>
                </div>
                <hr/>
            </div>
            <div class="cls-over">
                <div id="idViewData"></div>
            </div>
        </form> 
        <script src="js/jquery-1.4.4.min.js" type="text/javascript"></script>
        <script>
                            $(document).ready(function () {

                                $("#cboNam").val(new Date().getFullYear()).change();

                                //Tải dữ liệu
                                $("#cmdTaiDL").click({status: "0"}, SendData);
                                //Gửi dữ liệu
                                $("#cmdGuiDL").click({status: "1"}, SendData);
                                //Trả lại đơn vị
                                $("#cmdTraLaiDL").click({status: "2"}, SendData);
                                $("#cboDonvi").change(function () {
                                    if ($("#cboDonvi").val().trim() === "all") {
                                        $('#cboTonghop option')[0].selected = true;
                                    } else {
                                        $('#cboTonghop option')[1].selected = true;
                                    }
                                });
                                $("#cboTonghop").change(function () {
                                    if ($("#cboTonghop").val().trim() === "Y") {
                                        $('#cboDonvi option')[0].selected = true;
                                    } else {
                                        $('#cboDonvi option')[1].selected = true;
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
                                        $(idMess).html('<img src="imgs/newloading.gif" class="ViewMess"/>');
                                    },
                                    success: function (result) {
                                        if (["10", "11", "20", "21", "01"].includes(result)) {
                                            switch (result) {
                                                case "01":
                                                    strMess = 'Lỗi: Tải dữ liệu không thành công.';
                                                    $(idView).html('');
                                                    break;
                                                case "10":
                                                    strMess = '<span style="color:green">Thành công: Gửi dữ liệu lên cấp trên thành công.</span>';
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
                                            }
                                            $(idMess).html(strMess);
                                        } else {
                                            $(idView).html(result);
                                            $(idMess).html('<span style="color:green">Thành công: Tải dữ liệu.</span>');
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
