<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh mục Kểm tra kiểm toán nội bộ</title>
        <sj:head/>
        <script src="js/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" href="js/3.6.0/jquery-ui.css">
        <script src="js/3.6.0/jquery-ui.js"></script>
    </head>
    <script>
        function Callbaocao(fullname, maBC) {
            var thamso = "?quyBc=" + document.getElementById("cboquybc").value + "&namBc=" + document.getElementById("cbonam").value + "&maBC=" + maBC + "&ngayBC=" + $('#dpkReportDate').val();

            var ht = screen.availHeight;
            var wt = screen.availWidth;

            var resize = window.open(fullname + thamso, "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

            if (navigator.userAgent.indexOf('Chrome') !== -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                resize.moveTo(0, 0);
                resize.resizeBy(wt, ht);

            } else {
                resize.moveTo(0, 0);
                resize.resizeTo(wt, ht);

            }

            resize.focus();
        }
    </script>
    <style type="text/css">
        *{
            margin: 0px;
        }
        table{
            border-style: solid;
            border-collapse: collapse;
            width: 100%;
            font: 12px Arial, Helvetica, sans-serif; 
            line-height: 19px;
        }
        .tbhead{
            background-color: #5e5e55;
            font-weight: bold;
            color: #fff;
            text-align: center;
        }
        .cscontent td{
            padding-left:5px;
        }
        .cscontent:hover{
            background-color: #ffff99;
        }
    </style>
    <body>
        <form name="parentForm">
            <div style="margin: 7px 7px 7px 10px;">
                <table border="0" cellspacing="0" cellpading="0" height="100%" width="100%">
                    <tr>
                        <td>
                            <b>Quý báo cáo:</b>
                            <select name="cboquybc" id="cboquybc">
                                <option value="1">Quý I</option>
                                <option value="2">Quý II</option>
                                <option value="3">Quý III</option>
                                <option value="4">Quý IV</option>
                            </select>
                            &nbsp;&nbsp;
                            <b>Năm báo cáo:</b>
                            <!--Tungnv sua: 26-03-2015 <option duoc can trinh trong procedure-->
                            <select name="cbonam" id="cbonam">
                                <s:iterator value="lstYearReport" >
                                    <s:property escape="false"></s:property> 
                                </s:iterator>   

                            </select>

                            &nbsp;&nbsp;
                            <b>Ngày báo cáo (áp dụng mẫu biểu PCTN):</b>                            
                            <input type="text" name="dpkReportDate" id="dpkReportDate" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table border="1px">
                                <tr class="tbhead">
                                    <td>Mã KTNB</td>
                                    <td>Tên mẫu - Nhấn vào tên mẫu để nhập liệu cho biểu KTNB</td>
                                    <td>Kỳ báo cáo</td>
                                </tr>
                                <s:iterator value="DSKTNB">
                                    <tr class="cscontent">
                                        <td><s:property value="TENVT"/></td>
                                        <td><a href="javascript:Callbaocao('<s:property value="LINKBC"/>.action','<s:property value="MABC"/>')" style="text-decoration:none;"><s:property value="MOTA"/></a></td>
                                        <td><s:property value="KYBC"/><input type="hidden" value="<s:property value="KYBC"/>" id="txtkybaocao"/></td>
                                    </tr>
                                </s:iterator>
                            </table>
                    </tr>
                </table>
            </div>
        </form>

        <script>
            $(function () {
                $("#dpkReportDate").datepicker(
                        {
                            dateFormat: 'dd/mm/yy',
                            showOn: "button",
                            buttonImage: "img/icon-ui_datepicker.png",
                            buttonImageOnly: true,
                            // dateFormat: 'dd/mm/yy',
                            showButtonPanel: true,
                            buttonText: "icono",
                            changeMonth: true,
                            changeYear: true
                        });
            });


            $(document).ready(function () {
                var date = new Date();
                //var year = date.getFullYear(); //nam
                var quarter = Math.floor(date.getMonth() / 3) + 1; //quy
                //Gan quy mac dinh
                $("#cboquybc").val(quarter);
                $('#dpkReportDate').datepicker('setDate', getLastDayOfQuarter(new Date()));
            });


            function getLastDayOfQuarter(date) {
                var year = date.getFullYear();
                var quarterEndings = [[3, 31], [6, 30], [9, 30], [12, 31]];

                var toDateObj = function (dates) {
                    return new Date(year, dates[0] - 1, dates[1]);
                };

                var isBeforeEndDate = function (endDate) {
                    return endDate >= date;
                };

                date.setHours(0, 0, 0, 0);

                return quarterEndings
                        .map(toDateObj)
                        .filter(isBeforeEndDate)[0];
            }
        </script>
    </body>
</html>
