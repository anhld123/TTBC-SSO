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
        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
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
                height: 76vh;
            }
            .cmd{
                padding: 5px;
                background-image: linear-gradient(#f2f2f2,#c2c2c2);
                border: 1px solid #c2c2c2;
                border-radius: 2px;
            }
        </style>
        <script>
            $(document).ready(function () {
                $('#cboNam').change()(function () {
                    alert($('#cboNam :selected').text());
                });
            });
        </script>    

    </head>
    <body>
        <div class="cls-fix">
            <div>
                <span class="clss-lable" id="cboDonvi" name="cboDonvi">Đơn vị:</span>
                <select>
                    <s:iterator value="lstPos">
                        <option value="<s:property value='PosCode'/>"><s:property value='PosName'/></option>
                    </s:iterator>
                </select>
                &nbsp;
                <span class="clss-lable">Kế hoạch tín dụng năm:</span>
                <select id="cboNam" name="cboNam">
                    <script>
                        var i;
                        var text = "";
                        for (i = 2003; i <= 2099; i++) {
                            text += '<option value="' + i.toString() + '">Năm ' + i.toString() + '</option>';
                        }
                        document.write(text);
                    </script>
                </select>
                &nbsp;
                <span class="clss-lable">Tổng hợp</span>
                <select id="cboTonghop" name="cboTonghop">
                        <option value="N">Từng đơn vị</option>
                        <option value="Y">Tất cả các đơn vị trực thuộc</option>
                </select>
                &nbsp;
                <input type="button" id="cmdTai" name="cmdTai" value="Tải dữ liệu" class="cmd">
            </div>
            <hr/>
            <div><span class="clss-lable">Nguyên nhân</span></div>
            <div class="clss-body-ngnhan">
                <textarea id="strNguyennhan" name="strNguyennhan"></textarea>
            </div>
            <hr/>
            <div>
                <input type="button" id="cmdGui" name="cmdGui" value="Gửi Trung ương" class="cmd">&nbsp;<input type="button" id="cmdTuChoi" name="cmdTuChoi" value="Trả lại PGD" class="cmd">
            </div>
            <hr/>
        </div>
        <div class="cls-over">
            <table>
                <thead>
                    <tr>
                        <td colspan="3" style="text-align: left; border: 0px; font-weight: bold;">KẾ HOẠCH TÍN DỤNG NĂM 2022</td>
                        <td colspan="4" style="text-align: right; border: 0px;font-style: italic;">Đơn vị: triệu đồng, %, hộ, người</td>
                    </tr>
                    <tr>
                        <th rowspan="3">STT</th>
                        <th rowspan="3">CHỈ TIÊU</th>
                        <th rowspan="3">Thực hiện đến 31/12/2020</th>
                        <th rowspan="3">Ước thực hiện đến 31/12/2021</th>
                        <th colspan="3" ="3">Kế hoạch tín dụng năm 2022</th>
                    </tr>
                    <tr>
                        <th rowspan="2">Tổng số</th>
                        <th colspan="2">Tăng, giảm so với 31/12/2021</th>
                    </tr>
                    <tr>
                        <th>Số tuyệt đối (+/-)</th>
                        <th>Số tương đối (%)</th>
                    </tr>
                    <tr class="sttCol">
                        <td>1</td>
                        <td>2</td>
                        <td>3</td>
                        <td>4</td>
                        <td>5</td>
                        <td>6</td>
                        <td>7</td>
                    </tr>
                </thead>
                <tbody>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
