<%-- 
    Document   : index
    Created on : Jul 5, 2021, 8:34:25 AM
    Author     : ITCVBSP56
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EPS</title>
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
            .clsSTT{width: 5%;}
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
            #overlay {
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

            #popup {
                max-width: 600px;
                width: 80%;
                max-height: 300px;
                height: 80%; 
                padding: 20px;
                position: relative;
                background: #fff;
                margin: 5% auto;
            }

            #close {
                position: absolute;
                top: 10px;
                right: 10px;
                cursor: pointer;
                color: #000;
            }
        </style>
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker").datepicker(
                        {
                            dateFormat: 'dd/mm/yy',
                            changeMonth: true,
                            changeYear: true,
                            showButtonPanel: true
                        }
                ).datepicker('setDate', 'today');
                ;
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
        <div style="padding: 5px; display: inline-flex;">
            <div>
                Chi nhánh
                <select>
                    <option>TP.Hà Nội</option>
                </select>
                Phòng Giao dịch
                <select>
                    <option>TP.Hà Nội</option>
                </select>
                Ngày báo cáo:<input type="text" id="datepicker">
                <input type="button" value="Xem số liệu"/>
            </div>
            <div class="clsLoc">
                <input type="button" value="Chưa chốt số liệu: 10"/>
                <input type="button" value="Đã chốt đúng: 10"/>
                <input type="button" value="Chốt sai: 10"/></div>
        </div>
        <div>
            <table id="tblData">
                <tr>
                    <th class="clsSTT">STT</th>
                    <th class="clsCN">Chi nhánh</th>
                    <th class="clsPGD">Phòng Giao dịch</th>
                    <th class="clsFILL">Trạng thái<br><span style="font-weight: normal; color: red;">(Chưa chốt số liệu)</span></th>
                    <th class="clsNGN">Nguyên nhân</th>
                </tr>
                <tr>
                    <td>Cell</td>
                    <td>Cell</td>
                    <td>Cell</td>
                    <td>Cell</td>
                    <td>
                        <span id="trigger">Nguyên nhân của sự chênh lệch là do....</span>
                        <div id="overlay">
                            <div id="popup">
                                <div id="close">X</div>
                                <h2>Phản hồi</h2>
                                <p>Không hiểu số liệu sao lại lệch</p>
                            </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $('#trigger').click(function () {
                $('#overlay').fadeIn(300);
            });

            $('#close').click(function () {
                $('#overlay').fadeOut(300);
            });
        });
    </script>
</html>
