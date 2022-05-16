<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/mousetrap/1.6.3/mousetrap.min.js"></script>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <style>
            *{
                font-family: Tahoma;
                font-size: 12px;
            }
            #tableKtnb {
                border-collapse: collapse;
                width: 98%;
            }

            #tableKtnb td, #tableKtnb th {
                border: 1px solid #ddd;
            }

            #tableKtnb th {
                padding: 5px;
                text-align: center;
                background-color: lightslategray;
                color: white;
            }
            .cssItem{
                width: 50px;
                border: none;
                background-color: transparent;
                outline-style: none;
            }
        </style>
    </head>
    <body>
        <s:form name="frmdata" id="frmdata" action="save_data_ktnb01.action" theme="simple">
            <div style="padding: 3px 3px 3px 3px;">
                <table border="0" cellspacing="0" cellpading="0" height="100%">
                    <tr>
                        <td colspan="2" style="font-size: 14px; font-weight: bold; padding-bottom: 10px; text-transform: uppercase; color: red;" id="tenbc"></td>                    
                    </tr>
                    <tr>
                        <td width="70%" >
                            <input type="button" id="idSave" value="Cập nhật" style="width:122px;height:25px;color: red; font-size: 12px;"/>
                            <b style="padding-right: 3px;">Ngày báo cáo</b><input type="text" name="datepicker" id="datepicker" readonly="readonly"/>    
                        </td>
                    </tr>
                </table>

                <hr>
            </div>
            <div style="overflow: scroll; width: 100%; height: 83vh;">
                <table border="1px" id="tableKtnb">
                    <tr>
                        <th rowspan="4">Đơn vị</th>
                        <th rowspan="4">Tổng số lượt tiếp</th>
                        <th rowspan="4">Tổng số người được tiếp</th>
                        <th rowspan="4">Tổng số vụ được tiếp</th>
                        <th colspan="8">Tiếp thường xuyên</th>
                        <th colspan="18">Tiếp định kỳ và đột xuất của Thủ trưởng</th>
                        <th rowspan="4">Chức năng</th>
                    </tr>
                    <tr>
                        <th rowspan="3">Số lượt tiếp</th>
                        <th rowspan="3">Số người được tiếp</th>
                        <th colspan="2">Số vụ việc</th>
                        <th colspan="4">Trong đó đoàn đông người</th>
                        <th colspan="9">Thủ trưởng tiếp</th>
                        <th colspan="9">Uỷ quyền tiếp</th>
                    </tr>
                    <tr>
                        <th rowspan="2">Tiếp lần đầu</th>
                        <th rowspan="2">Tiếp nhiều lần</th>
                        <th rowspan="2">Số đoàn được tiếp</th>
                        <th rowspan="2">Số người được tiếp</th>
                        <th rowspan="2">Tiếp lần đầu</th>
                        <th rowspan="2">Tiếp nhiều lần</th>
                        <th rowspan="2">Số kỳ tiếp</th>
                        <th rowspan="2">Số lượt tiếp</th>
                        <th rowspan="2">Số người được tiếp</th>
                        <th colspan="2">Số vụ việc</th>
                        <th colspan="4">Trong đó đoàn đông người</th>
                        <th rowspan="2">Số kỳ tiếp</th>
                        <th rowspan="2">Số lượt tiếp</th>
                        <th rowspan="2">Số người được tiếp</th>
                        <th colspan="2">Số vụ việc</th>
                        <th colspan="4">Trong đó đoàn đông người</th>
                    </tr>
                    <tr>
                        <th>Tiếp lần đầu</th>
                        <th>Tiếp nhiều lần</th>
                        <th>Số đoàn được tiếp</th>
                        <th>Số người được tiếp</th>
                        <th>Tiếp lần đầu</th>
                        <th>Tiếp nhiều lần</th>
                        <th>Tiếp lần đầu</th>
                        <th>Tiếp nhiều lần</th>
                        <th>Số đoàn được tiếp</th>
                        <th>Số người được tiếp</th>
                        <th>Tiếp lần đầu</th>
                        <th>Tiếp nhiều lần</th>
                    </tr>
                    <tr style="font-style: italic; text-align: center;">
                        <td>MS</td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td><td>12</td><td>13</td><td>14</td><td>15</td><td>16</td><td>17</td><td>18</td><td>19</td><td>20</td><td>21</td><td>22</td><td>23</td><td>24</td><td>25</td><td>26</td><td>27</td><td>28</td><td>29</td><td>30</td>
                    </tr>
                    <s:iterator value="ModelList" status="status">     
                        <tr>
                            <td>
                                <input type="text" value="<s:property value=''/>" name="lstModelList[<s:property  value="%{#status.index}" />].D1"/>
                            </td>
                        </tr>
                    </s:iterator>
                    <tr>
                        <th id="tsms">Tổng</th><th id="ts1"></th><th id="ts2"></th><th id="ts3"></th><th id="ts4"></th><th id="ts5"></th><th id="ts6"></th><th id="ts7"></th><th id="ts8"></th><th id="ts9"></th><th id="ts10"></th><th id="ts11"></th><th id="ts12"></th><th id="ts13"></th><th id="ts14"></th><th id="ts15"></th><th id="ts16"></th><th id="ts17"></th><th id="ts18"></th><th id="ts19"></th><th id="ts20"></th><th id="ts21"></th><th id="ts22"></th><th id="ts23"></th><th id="ts24"></th><th id="ts25"></th><th id="ts26"></th><th id="ts27"></th><th id="ts28"></th><th id="ts29"></th>
                        <th>
                            <input type="button" value="Thêm dòng" name="AddRow" id="AddRow" style="width: 100%;"/>
                        </th>
                    </tr>
                </table>
            </div>
        </s:form>
        <i style="font-size: 11px; color: red;">Nhấp chuột ra vùng ngoải bảng biểu để sư dụng HostKey: Ctrl+A: Thêm dòng</i>
        <script>
            const queryString = window.location.search;
            const urlParams = new URLSearchParams(queryString);
            $("#tenbc").text(urlParams.get('textlink'));

            $(function () {
                $("#datepicker").datepicker({dateFormat: 'dd/mm/yy'}).val(new Date().toLocaleDateString("zh-HK", {year: 'numeric', month: '2-digit', day: '2-digit'}));
            });

            $(function () {
                $("#AddRow").click(function () {
                    let maxIndex = 0, gRows;
                    let fArray = ["D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "D11", "D12", "D13", "D14", "D15", "D16", "D17", "D18", "D19", "D20", "D21", "D22", "D23", "D24", "D25", "D26", "D27", "D28", "D29", "D30"];
                    if (typeof lstModelList !== 'undefined') {
                        maxIndex = lstModelList.length
                    }
                    ;
                    for (var i = 0; i < fArray.length; i++) {
                        gRows += '<td><input type="text" name="lstModelList[' + maxIndex + '].' + fArray[i] + '" class="cssItem" value=""></td>';
                    }
                    gRows += '<td><input type="button" value="x" style="width:100%;" class="btnRemove"/></td>'
                    $(this).closest('table').find('tr:last').prev().after('<tr>' + gRows + '</tr>');
                    return false;
                });
            });

            Mousetrap.bind(['ctrl+a'], function () {
                $("#AddRow").trigger("click");
                return false;
            });

            $(document).ready(function () {
                $("#tableKtnb").on('click', '.btnRemove', function () {
                    let chk = confirm("Bạn có chắc chắn muốn xoá không ?");
                    if (chk) {
                        $(this).closest('tr').remove();
                    }
                });
            });
        </script>
    </body>
</html>
