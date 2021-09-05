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
       <script type="text/javascript" src="DMChitieu/js/jquery-ui.js"></script>
        <script src="js/jquery.number.js"></script>
        <script type="text/javascript" src="DMChitieu/js/jquery.session.js"></script>
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
                ).datepicker('setDate', getMonday(new Date()));
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
            <div style="display:inline-flex;justify-content: end; align-items: end; width: 100%; border-top: 1px solid #ddd;">
<!--                <div style="display:flex; align-items:center;">
                    &nbsp;<b>Lọc danh sách: &nbsp;</b>
                    <select id="idloc" name="loc" onchange="Filter();">
                    </select>
                </div>-->
                <div style="display:flex; align-items:center; padding: 7px 0px;" >
                    <div id="idcapbc">
                        <b>Chi nhánh</b>
                        <select id="idtinh" name="matinh">
                            <s:iterator value="lstCN">
                                <option value="<s:property value='PosCode'/>"><s:property value='PosName'/></option>
                            </s:iterator>
                        </select>
                    </div>
                    <b>Đơn vị trực thuộc</b>
                    <select id="iddonvi" name="madv" style="width: 200px;">
                        <s:iterator value="lstPos">
                            <option value="<s:property value='PosCode'/>"><s:property value='PosName'/></option>
                        </s:iterator>
                    </select>
                    <b>Ngày báo cáo:</b><input type="text" id="datepicker" name="ngaybc" class="js-date" maxlength="10" style="width: 100px;">
                    <input type="button" id="btnXem" value="Xem số liệu"/>
                    <input type="button" id="btnTonghop" value="Tổng hợp báo cáo PGD"/>
                </div>
            </div>
        </div>
        <div style="width: 100%;">
            <div id="ShowData">
            </div>
        </div>
    </form>
</body>
<script>
    $(document).ready(function () {
        $("#btnTonghop").click({status: "", url: "TopngHopBaoCao.action"}, SendData);
        $("#btnXem").click({status: "", url: "loadIndex.action"}, SendData);
        $("#btnXem").trigger('click');
        $("#idtinh").change({status: "00", url: "getpgdfromcn.action"}, SendData);
        $("#xemphanhoichung").click(() => {
            $("#phanhoidonvi").toggle();
        });
    });
    function SendData(event) {
        $("#btnXem").val("Đang tải dữ liệu");
        $("#btnXem").prop('disabled', true);
        $("#btnTonghop").prop('disabled', true);
        var surl, sdata, idView, idForm, method;
        surl = event.data.url + "?status=" + event.data.status;
        idView = "#ShowData";
        idForm = "#frmMain";
        method = "POST";
        sdata = jQuery(idForm).serialize();
        $.ajax({
            url: surl,
            data: sdata,
            type: method,
            async: true,
            success: function (result) {
                if (event.data.status == "00") {
                    $('#iddonvi').children().remove().end();
                    $.each(result.lstPGD, function (key, val) {
                        $('#iddonvi').append('<option value="' + key + '">' + val + '</option>');
                    });
                    alphabetizeList('#iddonvi');
                } else {
                    $(idView).html(result);
                }
            },
            complete: function () {
                $("#btnXem").val("Xem dữ liệu");
                $("#btnXem").prop('disabled', false);
                $("#btnTonghop").prop('disabled', false);
            }
            ,
            error: function () {
                alert('Lỗi khi thực hiện.');
            }
        });
    }
    function alphabetizeList(listField) {
        var sel = $(listField);
        var opts_list = sel.find('option');
        opts_list.sort(function (a, b) {
            return $(a).text() > $(b).text() ? 1 : -1;
        });
        sel.html('').append(opts_list);
        sel.val("00"); // set cached selected value
    }

    function getMonday(d) {
        d = new Date(d);
        var day = d.getDay(),
                diff = d.getDate() - day + (day == 0 ? -6 : 1);
        return new Date(d.setDate(diff));
    }
</script>
</html>
