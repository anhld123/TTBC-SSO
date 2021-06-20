<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<s:head/>
<sj:head/>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <style>

            #tabledetail {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 150%;
            }

            #tabledetail td, #tabledetail th {
                border: 1px solid #ddd;
                padding: 0px;
                /*width: 30px;*/
            }

            #tabledetail tr:nth-child(even){background-color: #f2f2f2;}

            #tabledetail tr:hover {background-color: #ddd;}

            #tabledetail th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: center;
                background-color: #4CAF50;
                color: white;
            }
        </style>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>  
        <script>
            $(document).ready(function () {

                $('input.number2').css({"text-align": "right"});
                $('.number2').number(true, 2);
                $(".hideColumn").hide();
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
    </head>
    <body>
        <h2><span style=" color: blue;">Bạn đã upload file thành công chi tiết</span></h2>
        <div style=" overflow: scroll; width: 90%; height: 300px">
            <table border=1  id="tabledetail">
                <tr>
                    <th rowspan=3 style="width: 10px">TT</th>
                    <th rowspan=3>Mã CN</th>
                    <th rowspan=3>Chi nhánh</th>
                    <th rowspan=3>Tổng điểm (180)</th>
                    <th colspan=11>Chỉ tiêu huy động vốn TW cấp bù</th>						
                    <th colspan=11>Chỉ tiêu nguồn vốn UTĐP</th>						
                    <th colspan=9>Tăng trưởng dư nợ nguồn vốn trung ương </th>
                </tr>
                <tr>
                    <th rowspan=2>Kế hoạch tăng trưởng được giao cả năm</th>
                    <th rowspan=2>Số dư thực hiện đến 31/12/2018</th>
                    <th rowspan=2>Số dư thực hiện đến hết tháng báo cáo</th>
                    <th colspan=2>Lũy kế kế hoạch phải thực hiện đến tháng báo cáo</th>
                    <th rowspan=2>Lũy kế số dư tăng trưởng đến tháng báo cáo</th>
                    <th rowspan=2>Tỷ lệ hoàn thành kế hoạch </th>
                    <th rowspan=2>Điểm hoàn thành kế hoạch tháng (32đ)</th>
                    <th rowspan=2>Điểm thưởng chưa loại trừ vượt KH </th>
                    <th rowspan=2>Điểm thưởng (8đ)</th>
                    <th rowspan=2>Tổng số điểm đạt được (40đ)</th>
                    <th rowspan=2>Kế hoạch tăng trưởng được giao cả năm</th>
                    <th rowspan=2>Số dư thực hiện đến 31/12/2018</th>
                    <th rowspan=2>Số dư thực hiện đến hết tháng báo cáo</th>
                    <th colspan=2>Lũy kế kế hoạch phải thực hiện đến tháng báo cáo</th>
                    <th rowspan=2>Lũy kế số dư tăng trưởng đến tháng báo cáo</th>
                    <th rowspan=2>Tỷ lệ hoàn thành kế hoạch </th>
                    <th rowspan=2>Điểm hoàn thành kế hoạch tháng (32đ)</th>
                    <th rowspan=2>Điểm thưởng chưa loại trừ vượt KH</th>
                    <th rowspan=2>Điểm thưởng (8đ)</th>
                    <th rowspan=2>Tổng số điểm đạt được (40đ)</th>
                    <th rowspan=2>Kế hoạch tăng trưởng được giao cả năm</th>
                    <th colspan=2>Lũy kế kế hoạch phải thực hiện đến tháng báo cáo</th>
                    <th rowspan=2>Lũy kế số dư tăng trưởng đến tháng báo cáo</th>
                    <th rowspan=2>Tỷ lệ hoàn thành kế hoạch </th>
                    <th rowspan=2>Điểm hoàn thành kế hoạch tháng (80đ)</th>
                    <th rowspan=2>Điểm thưởng chưa loại trừ vượt KH</th>
                    <th rowspan=2>Điểm thưởng (8đ)</th>
                    <th rowspan=2>Tổng số điểm đạt được (40đ)</th>
                </tr>
                <tr>
                    <th>Tỷ lệ</th>
                    <th>Số tuyệt đối</th>
                    <th>Tỷ lệ</th>
                    <th>Số tuyệt đối</th>
                    <th>Tỷ lệ</th>
                    <th>Số tuyệt đối</th>
                </tr>

                <s:iterator value="#attr.lstExcel" var="modelView" status="rowstatus">   
                    <tr>
                        <td style="width: 50px"><input type="text" id="D4" value="<s:property value='C1'/>"  style="width: 100%; text-align: center;" name="lstExcel[<s:property  value="%{#rowstatus.index}" />].C1" class="" readonly="readonly"/></td>
                        <td style="width: 50px"><input type="text" id="D4" value="<s:property value='C2'/>"  style="width: 100%; text-align: center;" name="lstExcel[<s:property  value="%{#rowstatus.index}" />].C2" class="" readonly="readonly"/></td>
                        <td style="width: 150px"><input type="text" id="D4" value="<s:property value='C3'/>"  style="width: 100%;" name="lstExcel[<s:property  value="%{#rowstatus.index}" />].C3" class="" readonly="readonly"/></td>

                        <td><input type='text' id='N1' value='<s:property value='N1'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N1' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N2' value='<s:property value='N2'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N2' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N3' value='<s:property value='N3'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N3' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N4' value='<s:property value='N4'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N4' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N5' value='<s:property value='N5'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N5' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N6' value='<s:property value='N6'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N6' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N7' value='<s:property value='N7'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N7' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N8' value='<s:property value='N8'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N8' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N9' value='<s:property value='N9'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N9' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N10' value='<s:property value='N10'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N10' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N11' value='<s:property value='N11'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N11' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N12' value='<s:property value='N12'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N12' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N13' value='<s:property value='N13'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N13' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N14' value='<s:property value='N14'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N14' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N15' value='<s:property value='N15'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N15' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N16' value='<s:property value='N16'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N16' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N17' value='<s:property value='N17'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N17' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N18' value='<s:property value='N18'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N18' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N19' value='<s:property value='N19'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N19' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N20' value='<s:property value='N20'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N20' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N21' value='<s:property value='N21'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N21' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N22' value='<s:property value='N22'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N22' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N23' value='<s:property value='N23'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N23' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N24' value='<s:property value='N24'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N24' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N25' value='<s:property value='N25'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N25' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N26' value='<s:property value='N26'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N26' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N27' value='<s:property value='N27'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N27' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N28' value='<s:property value='N28'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N28' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N29' value='<s:property value='N29'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N29' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N30' value='<s:property value='N30'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N30' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N31' value='<s:property value='N31'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N31' class='number2 ' readonly='readonly'/></td>
                        <td><input type='text' id='N32' value='<s:property value='N32'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N32' class='number2 ' readonly='readonly'/></td>

                    </tr>
                </s:iterator>
            </table>
        </div>
    </body>
</html>
