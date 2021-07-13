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
                ).datepicker('setDate', '31/05/2021');
            });
        </script>
    </head>
    <body>
        <div class="clsTitle">
            <h3>DANH SÁCH ĐƠN VỊ PHÀN HỒI VỀ DỮ LIỆU
                <br>
                NHẬT KÝ QUỸ NGƯỜI LAO ĐỘNG LÀM VIỆC TẠI HÀN QUỐC THEO CHƯƠNG TRÌNH EPS
            </h3>
        </div>
        <form id="frmMain" name="frmMain">
            <div style="padding: 5px; display: inline-flex;">
                <div>
                    Đơn vị
                    <select id="iddonvi" name="madv">
                        <s:iterator value="lstPos">
                            <option value="<s:property value='PosCode'/>"><s:property value='PosName'/></option>
                        </s:iterator>
                    </select>
                    Ngày báo cáo:<input type="text" id="datepicker" name="ngaybc">
                    <input type="button" id="btnXem" value="Xem số liệu"/>
                </div>
                <div class="clsLoc">
                    <input type="button" id="btnChuaChot" value="" style="display: none;"/>
                    <input type="button" id="btnDaChot" value="" style="display: none;"/>
                    <input type="button" id="btnChotSai" value="" style="display: none;"/></div>
            </div>
        </form>
        <div id="ShowData"></div>
    </body>
    <script>
        $(document).ready(function () {
            $("#btnXem").click({status: "00"}, SendData);
            $("#btnChuaChot").click({status: "01"}, SendData);
            $("#btnDaChot").click({status: "02"}, SendData);
            $("#btnChotSai").click({status: "03"}, SendData);
        });
        function SendData(event) {
            var surl, sdata, idView, idForm, method, mess;
            switch (event.data.status) {
                case '01':
                    mess = "(Chưa chốt số liệu)";
                    break;
                case '02':
                    mess = "(Đã chốt số liệu)";
                    break;
                case '03':
                    mess = "(Chốt sai số liệu)";
                    break;
                default:
                    mess = '';
            }
            surl = "loadIndex.action?status=" + event.data.status;
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
                    $("#status").html(mess);
                    
                },
                error: function () {
                    alert('Lỗi khi thực hiện.');
                }
            });
        }
    </script>
</html>
