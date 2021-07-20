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
            select, input{
                margin: 3px 5px;
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
            <h3>DANH SÁCH ĐƠN VỊ PHẢN HỒI VỀ DỮ LIỆU
                <br>
                NHẬT KÝ QUỸ NGƯỜI LAO ĐỘNG LÀM VIỆC TẠI HÀN QUỐC THEO CHƯƠNG TRÌNH EPS
            </h3>
        </div>
        <form id="frmMain" name="frmMain" style="margin-bottom: 0px;">
            <div style="display:inline-flex;justify-content: space-between; width: 100%; border-top: 1px solid #ddd;">
                <div id="mess" style="display:flex; align-items:center;"></div>
                <div style="display:flex; align-items:center; padding: 7px 0px;" >
                    Đơn vị
                    <select id="iddonvi" name="madv">
                        <s:iterator value="lstPos">
                            <option value="<s:property value='PosCode'/>"><s:property value='PosName'/></option>
                        </s:iterator>
                    </select>
                    <div> Ngày báo cáo:<input type="text" id="datepicker" name="ngaybc"> </div>
                    <input type="button" id="btnXem" value="Xem số liệu"/>
                    <input type="button" id="btnChuaChot" value="" style="display: none;"/>
                    <input type="button" id="btnDaChot" value="" style="display: none;"/>
                    <input type="button" id="btnChotSai" value="" style="display: none;"/>
                </div>
            </div>
        </div>
    </form>
    <div style="width: 100%;">
        <div id="ShowData">
        </div>
    </div>
</body>
<script>
    $(document).ready(function () {
        $("#btnXem").click({status: "04"}, SendData);
        $("#btnChuaChot").click({status: "00"}, SendData);
        $("#btnDaChot").click({status: "01"}, SendData);
        $("#btnChotSai").click({status: "02"}, SendData);
        $("#btnXem").trigger('click');
    });
    function SendData(event) {
        var surl, sdata, idView, idForm, method, mess;
        switch (event.data.status) {
            case '00':
                mess = "(Chưa chốt số liệu)";
                break;
            case '01':
                mess = "(Đã chốt số liệu)";
                break;
            case '02':
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
            },
            error: function () {
                alert('Lỗi khi thực hiện.');
            }
        });
    }
</script>
</html>
