<%-- 
    Document   : subbcn
    Created on : Jul 7, 2014, 10:57:18 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sub báo cáo nhanh</title>
    </head>
    <style type="text/css">
        th{
            background-color: #DCDCDC;
            border-color: #999;
        }
        td{
            border-color: #999;
        }
        body,div{
            font-family: Arial;
            font-size: 13px;
        }
        a.button{
            font-weight: bold;
            text-decoration:none;
            color: #018c3b;
        }
        a{
            text-decoration:none;
            color: #018c3b;
        }
        .idcot{
            font-weight: bold;

        }
        .iddong{
            font-weight: bold;
        }
        .idcot,.iddong,.idtd,.idcont{
            width: 150px;
        }
    </style>
    <script type="text/javascript">
        function gentable() {
            var stable = "<table border='0' id='tbdata'>";
            for (i = 1; i <= 10; i++) {
                stable = stable + "<tr>";
                for (j = 1; j <= 8; j++) {
                    if (j == 1 && i == 1) {
                        stable = stable + "<td><input type='text' value='Tên dòng / Tên cột' disabled  class='idtd'></td>";
                    } else if (j != 1 && i == 1) {
                        stable = stable + "<td><input type='text' value='Cột " + (j - 1) + "' class='idcot'></td>";
                    } else if (j == 1 && i != 1) {
                        stable = stable + "<td><input type='text' value='Dòng " + (i - 1) + "' class='iddong'></td>";
                    } else {
                        stable = stable + "<td><input type='text' value='' class='idcont'></td>";
                    }
                }
                stable = stable + "</tr>";
            }
            stable = stable + "</table>";
            document.getElementById("gentb").innerHTML = stable;
            document.getElementById('gentb').cellSpacing = "0";
            document.getElementById('gentb').cellPadding = "0";
        }
    </script>
    <body>
        <div>
            <table cellpacing="0px" cellpadding="3px" style="width: 100%;">
                <tr>
                    <td>ID BC:</td>
                    <td><input type="text" value="BC0001" disabled/></td>
                    <td>Nguồn BC:</td>
                    <td>
                        <select name="cbonguon" id="cbonguon">
                            <option value="all">--Tất cả--</option>
                            <option value="chitieu">Chỉ tiêu</option>
                            <option value="candoi">Cân đối</option>
                        </select>
                    </td>
                    <td>Đơn vị:</td>
                    <td>
                        <select name="cbonguon" id="cbonguon">
                            <option value="all">--Tất cả--</option>
                            <option value="chitieu">Chỉ tiêu</option>
                            <option value="candoi">Cân đối</option>
                        </select>
                    </td>
                    <td>Loại BC:</td>
                    <td>
                        <select name="cbonguon" id="cbonguon">
                            <option value="all">--Tất cả--</option>
                            <option value="chitieu">Chỉ tiêu</option>
                            <option value="candoi">Cân đối</option>
                        </select>
                    </td>
                    <td>Kỳ BC:</td>
                    <td>
                        <select name="cbonguon" id="cbonguon">
                            <option value="all">--Tất cả--</option>
                            <option value="chitieu">Chỉ tiêu</option>
                            <option value="candoi">Cân đối</option>
                        </select>
                    </td>
                    <td style="border: 1px solid; border-color: #018c3b;"><a href="#">Chọn đơn vị</a></td>
                </tr>
                <tr>
                    <td>Tiêu đề BC:</td>
                    <td colspan="9"><input type="text" style="width:99%;"/></td>
                    <td rowspan="4" style="width: 15%;border: 1px solid; border-color: #018c3b;" valign="top">
                        <div style="width: 100%;height:155px;overflow-y: scroll;">
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch<br>
                            <input type="checkbox" checked/> Sở giao dịch
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>Đơn vị tính:</td>
                    <td><input type="text" value="0"/></td>
                    <td>Phần TP:</td>
                    <td><input type="text" value="0"/></td>
                    <td>Số cột:</td>
                    <td><input type="text" value="1"/></td>
                    <td>Số dòng:</td>
                    <td><input type="text" value="1"/></td>
                    <td><input type="radio" name="chitiet"/>Chi nhánh<input type="radio" name="chitiet"/>Chỉ tiêu</td>
                </tr>
                <tr>
                    <td>Công thức</td>
                    <td colspan="9"><input type="text" value="" style="width:99%;"/></td>
                </tr>
                <tr>
                    <td colspan="10">
                        <b>Ghi chú:</b>
                        <br>Công thức chỉ tiêu (CT) : <b style="color: red;"> &lt;+/-&gt;&lt;Mã CT&gt;[*][/đơn vị] </b> Ví dụ: +1A014/1000000: CT NHCS; +1A0140*: CT NHNN
                        <br>Công thức tài khoản (TK):<b style="color: red;">&lt;+/-&gt;&lt;DDN/PSN/DCN/DDC/PSC/DCC&gt;&lt;TK&gt;[/đơn vị]</b> Ví dụ: +DCN21/1000
                        <br>Trong 1 công thức có thể nhập nhiều CT hoặc TK phân cách nhau bởi dấu phẩy. Ví dụ: +1A014,-1A0141,-1A1042</td>
                </tr>
            </table>
            <hr style="width:100%; border:1px thin;">
            <a href="javascript:gentable();" class="button">01.Tạo biểu</a>
            &nbsp;|&nbsp;
            <a href="#" class="button">02.Lưu mẫu biểu</a>
            &nbsp;|&nbsp;
            <a href="#" class="button">03.Quay lại</a>
            <hr style="width:100%; border:1px thin;">
        </div>
        <div id="gentb" style="overflow: scroll; width: 100%; height:370px;"></div>
    </body>
</html>
