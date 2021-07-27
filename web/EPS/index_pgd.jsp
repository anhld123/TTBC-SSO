<%-- 
    Document   : index
    Created on : Jul 5, 2021, 8:34:25 AM
    Author     : ITCVBSP56
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EPS</title>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="js/jquery.number.js"></script>
        <style>
            .clsTitle{
                width: 100%;
                text-align: center;
            }
            #tblData {
                font-family: Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            #tblData td, #tblData th {
                border: 1px solid #ddd;
                padding: 8px;
            }

            #tblData tr:nth-child(even){background-color: #f2f2f2;}

            #tblData tr:hover {background-color: #ddd;}

            #tblData th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: orange;
                color: white;
                text-align: center;
            }
            .clsSTT{width: 5%; text-align: center;}
            .clsCN{width: 15%;}
            .clsPGD{width: 15%;}
            .clsFILL{width: 10%; line-height: 18px;}
            .clsNGN{width: 60%;}
            .clsLoc{margin-left: 20px;}
            .clsLoc>input{
                margin: 0px 10px 5px 0px;
                background-color: transparent;
                border: 0px;
                font-weight: bold;
                cursor: pointer;
                color: blue;
            }
            .overlay {
                position: fixed;
                height: 100%; 
                width: 100%;
                top: 15%;
                right: 0;  
                bottom: 0;
                left: 0;
                background: rgba(0,0,0,0.8);
                display: none;
            }

            .popup {
                max-width: 600px;
                width: 80%;
                max-height: 300px;
                height: 80%; 
                padding: 20px;
                position: relative;
                background: #fff;
                margin: 5% auto;
            }

            .close {
                position: absolute;
                top: 10px;
                right: 10px;
                cursor: pointer;
                color: #000;
            }
            textarea{
                width: 100%;
                height: 155px;
                margin-bottom: 5px;
                border: 0px;
                outline: none;
            }
            input[type=button]{
                margin: 0px 5px;
            }
            legend, label{
                font-weight: bold;
            }
            fieldset{
                margin-bottom: 7px;
            }
            iframe{
                border: 0px;
            }
            #Showchotsl{
                display: none;   
                width: 98%;
            }
        </style>
        <script>
            //Lấy ngày hiện tại cho NgayBC
            $(function () {
                $("#datepicker").datepicker(
                        {
                            dateFormat: 'dd/mm/yy',
                            changeMonth: true,
                            changeYear: true,
                            showButtonPanel: true
                        }
                ).datepicker('setDate', '25/05/2020');
            });
        </script>
    </head>
    <body>
        <div class="clsTitle">
            <h3>
                XÁC NHẬN SỐ LIỆU
                <br>
                NHẬT KÝ QUỸ NGƯỜI LAO ĐỘNG LÀM VIỆC TẠI HÀN QUỐC THEO CHƯƠNG TRÌNH EPS
            </h3>
        </div>
        <form id="frmMain" name="frmMain">
            <div style="display:inline-flex;justify-content: space-between; width: 100%; border-top: 1px solid #ddd;">
                <div style="display:flex; align-items:center;">
                    &nbsp;&nbsp;<b>Lọc:&nbsp;</b>
                    <select id="idloc" name="loc" onchange="Filter();" style="border: 1px solid black;">
                    </select>
                </div>
                <div style="display:flex; align-items:center; padding: 7px 0px;" >
                    <div> Ngày báo cáo:<input type="text" id="datepicker" name="ngaybc" class="js-date" maxlength="10"> </div>
                    <input type="button" value = "Xem số liệu" id="cmdxemsl" style="margin-right: 5px;">
                    <input type="button" value = "Lưu số liệu" id="cmdluusl" style="margin-right: 5px;">
                    <input type="button" value = "Xác nhận với Trung ương" id="idchotsl" style="margin-right: 5px;">
                </div>
            </div>
            <div style="width: 100%; display: flex;justify-content: center;">
                <div id="ShowData">
                </div>
            </div>
        </form>
    </body>
    <script>
        $(document).ready(function () {
            $("#cmdxemsl").click({action: "xemsleps"}, SendData);
            $("#cmdxemsl").trigger('click');
            $("#cmdluusl").click({action: "luusleps"}, SendData);
            $("#idchotsl").click({action: "xacnhansleps"}, SendData);
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        });
        function SendData(event) {
            var surl, sdata, idView, idForm, method, conf, index = 0, constChk = 1;
            if (event.data.action == "xacnhansleps") {
                $('select[name^="chotsl"]').each(function (e) {
                    if ($(this).val() == '03') {
                        var val = $("textarea[name='nguyennhan']:eq(" + index + ")").val();
                        if (val.trim() == '') {
                            alert('Bạn cần nhập nguyên nhân đối với "Hoàn thanh điều chỉnh"');
                            constChk = 0;
                            return undefined;
                        }
                    }
                    index++;
                });
            }
            if (constChk == 1) {
                if (event.data.action == "xacnhansleps") {
                    conf = confirm("Bạn có chắc chắn muốn xác nhận số liệu với TW");
                    if (conf == false) {
                        return conf;
                    }
                }
                surl = event.data.action + ".action";
                idView = "#ShowData";
                idForm = "#frmMain";
                method = "POST";
                sdata = jQuery(idForm).serialize();
                $.ajax({
                    url: surl,
                    data: sdata,
                    type: method,
                    async: true,
                    beforeSend: function () {
                        $(idView).html('<img src="imgs/newloading.gif"/>');
                    },
                    success: function (result) {
                        $(idView).html(result);
                        if (event.data.action == "luusleps") {
                            alert("Lưu dữ liệu thành công !");
                        }
                        if (event.data.action == "xacnhansleps") {
                            alert("Xác nhận số liệu thành công !");
                        }
                    },
                    error: function () {
                        alert('Lỗi khi thực hiện.');
                    }
                });
            }
        }
    </script>
</html>
