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
        <style>
            *{
                font-family: tahoma;
                font-size: 13px;
            }

            table {
                width : 100%;
                border-top: 1px solid orange;
                border-left: 1px solid #c2c2c2;
                border-right: 1px solid #c2c2c2;
                border-bottom: 1px solid #c2c2c2;
                text-align : center;
                border-collapse : collapse;
            }
            table tr th, table tr td {
                border : 1px solid #c2c2c2;
            }


            table thead th {
                position: -webkit-sticky;
                position : sticky;
                top : 0;
                color: white;
                background-color : #04AA6D;
            }

            /* here is the trick */
            table tbody:nth-of-type(1) tr:nth-of-type(1) td {
                border-top: none !important;
            }
            table thead th {
                border-top: none !important;
                border-bottom: none !important;
                box-shadow: inset 0 0px 0 #c2c2c2,
                    inset 0 -1px 0 #c2c2c2;
            }

            table thead th {
                background-clip: padding-box
            }

            table thead { position: sticky; top: 0; z-index: 1; }

            th, td {
                text-align: left;
                border: 1px solid #c2c2c2;
                text-align: center;
                padding: 3px;
            }

            th{
                padding: 8px;
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
                height: 60vh;
            }
            .cmd{
                padding: 5px;
                background-image: linear-gradient(#f2f2f2,#c2c2c2);
                border: 1px solid #c2c2c2;
                border-radius: 2px;
                z-index: 99;
                margin-left: 5px;
            }
            hr{
                border-bottom: 0px;
                border-top: 1px solid lightgray;
            }
            .item {
                padding: 5px;
                text-align: right;
                border: 0px !important;
                outline: none;
            }
            .cls {
                background-color: lightgoldenrodyellow;
            }
        </style>
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/jquery.number.js"></script>
        <link href="css/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <form id="idKhnv2021" name="nameKhnv2021">
            <div class="cls-fix">
                <div>
                    <span class="clss-lable">Đơn vị:</span>
                    <select id="cboDonvi" name="cboDonvi">
                        <option value="all">----Tất cả----</option>
                        <s:iterator value="lstPos">
                            <option value="<s:property value='PosCode'/>"><s:property value='PosName'/></option>
                        </s:iterator>
                    </select>
                    &nbsp;
                    <span class="clss-lable">Kế hoạch tín dụng năm:</span>
                    <select id="cboNam" name="cboNam">
                        <script>
                            var i, varNam;
                            varNam = new Date().getFullYear();
                            var text = "";
                            for (i = (varNam - 5); i <= (varNam + 5); i++) {
                                text += '<option value="' + i.toString() + '">Năm ' + i.toString() + '</option>';
                            }
                            document.write(text);
                        </script>
                    </select>
                    &nbsp;
                    <span class="clss-lable">Đợt thực hiện:</span>
                    <select  id="cboDot" name="cboDot" onchange="toggleButton()">
                        <option value="1">Năm</option>
                        <!--                        <option value="2">3 Năm</option>
                                                <option value="5">5 Năm</option>-->
                    </select>
                    &nbsp;
                    <span class="clss-lable">Tổng hợp</span>
                    <select id="cboTonghop" name="cboTonghop">
                        <option value="Y">Tổng hợp các đơn vị trực thuộc</option>
                        <option value="N">Duyệt từng đơn vị</option>
                        <option value="W">Phản hồi từ cấp trên</option>
                        <option value="S">Kiểm soát gửi/nhận</option>
                        <!--<option value="R">Tổng hợp lại số liệu từ các đơn vị trực thuộc</option>-->
                    </select>
                    &nbsp;                                  
                    <input type="button" value="Tải dữ liệu" id="cmdTaiDL" name="nameTaiDL" class="cmd"/>
                    <s:if test="CapBC.equalsIgnoreCase('3')">
                        &nbsp;                    
                        <input style="color: red" href="#"
                               type="button" value="Khóa gửi dữ liệu" id="cmdKhoa" name="skhoaDL" class="cmd"
                               onclick="cancelAssign()" />
                    </s:if>
                </div>
                <hr/>
                <div id="idNguyenNhan">
                    <div><span class="clss-lable">Nguyên nhân</span></div>
                    <div class="clss-body-ngnhan">
                        <textarea id="strNguyennhan" name="strNguyennhan"><s:property value='strNguyennhan'/></textarea>
                    </div>
                </div>
                <div id="idButton" style="padding: 0px 0px 6px 0px; height: 30px; display: inline-flex;">
                    <input type="button" value="Gửi cấp trên" id="cmdGuiDL" name="nameGuiDL" class="cmd"/>
                    <input type="button" value="Trả lại đơn vị" id="cmdTraLaiDL" name="nameTraLaiDL" class="cmd"/>
                    <input type="button" value="Lưu dữ liệu" id="idLuuDL" name="nameLuuDL" class="cmd"/>

                </div>
                &nbsp;
                <div id="idViewMess" name="nameViewMess" style="font-weight: bold; color: red; line-height: 30px;"></div>
            </div>
            <div class="cls-over">
                <div id="idViewData"></div>
            </div>
        </form> 
        <script>
            $(function () {
                const capBC = '<s:property value="CapBC"/>';
                const $tongHop = $("#cboTonghop");
                const $donVi = $("#cboDonvi");
                const $btnGui = $("#cmdGuiDL");
                const $btnTraLai = $("#cmdTraLaiDL");
                const $btnLuu = $("#idLuuDL");
                const $btnAuthor = $("#cmdAuthor");
                const $nguyenNhan = $("#idNguyenNhan");
                const $buttonArea = $("#idButton");
                const $viewData = $("#idViewData");
// Khởi tạo
                initPage();
// Binding sự kiện
                $("#cmdTaiDL").on("click", {status: "0"}, sendData);
                $btnGui.on("click", {status: "1"}, sendData);
                $btnTraLai.on("click", {status: "2"}, sendData);
                $btnLuu.on("click", {status: "3"}, sendData);
                $donVi.on("change", handleDonViChange);
                $tongHop.on("change", handleTongHopChange);

                function initPage() {
//                    if (capBC === "3") {
//                        $('#cboTonghop option[value="W"]').remove();
//                        $btnGui.remove();
//                        $("#cmdKhoa").hide();
//                    }

                    $btnTraLai.hide();
                    $nguyenNhan.hide();
                    $buttonArea.hide();

                    $("#cboNam")
                            .val(new Date().getFullYear() + 1)
                            .change();
                }

                function setTongHopMode() {
                    $btnLuu.show();
                    $btnGui.show();

                    $btnAuthor.hide();
                    $btnTraLai.hide();
                    $nguyenNhan.hide();
                    $viewData.hide();

                    $('.cls-over').height("86vh");
                }

                function setChiTietMode() {
                    $btnLuu.hide();
                    $btnGui.hide();

                    $btnAuthor.show();
                    $btnTraLai.show();
                    $nguyenNhan.show();
                    $viewData.hide();

                    $('.cls-over').height("65vh");
                }

                function setViewMode() {
                    $btnLuu.hide();
                    $btnGui.hide();
                    $btnAuthor.hide();
                    $btnTraLai.hide();
                    $nguyenNhan.hide();
                    $viewData.hide();

                    $('.cls-over').height("86vh");
                }

                function handleDonViChange() {
                    if ($donVi.val() === "all") {
                        setTongHopMode();
                        $('#cboTonghop option')[0].selected = true;
                    } else {
                        setChiTietMode();
                        $('#cboTonghop option')[1].selected = true;
                    }
                }

                function handleTongHopChange() {

                    switch ($tongHop.val()) {

                        case "Y":
                            setTongHopMode();
                            $('#cboDonvi option')[0].selected = true;
                            break;

                        case "N":
                            setChiTietMode();
                            $('#cboDonvi option')[1].selected = true;
                            break;

                        case "W":
                            setViewMode();
                            $('#cboDonvi option')[0].selected = true;
                            break;
                    }
                }

                function sendData(event) {

                    const status = event.data.status;

                    $('.cls-over').height(
                            $tongHop.val() === "N" ? "65vh" : "85vh"
                            );

                    $.ajax({
                        url: "SendAction.action",
                        type: "POST",
                        data: $("#idKhnv2021").serialize() + "&status=" + status,

                        beforeSend: function () {
                            $("#idViewMess").html(
                                    '<img src="imgs/newloading.gif"/>'
                                    );
                        },

                        success: function (result) {

                            const messages = {
                                "01": "Lỗi: Không có dữ liệu.",
                                "11": "Lỗi: khi gửi dữ liệu lên cấp trên.",
                                "20": "<span style='color:green'>Thành công: Hoàn trả dữ liệu cho đơn vị thành công.</span>",
                                "21": "Lỗi: hoàn trả dữ liệu cho đơn vị.",
                                "30": "<span style='color:green'>Thành công: Lưu dữ liệu.</span>",
                                "31": "Lỗi: Lưu dữ liệu.",
                                "404": "Lỗi: Đơn vị trực thuộc chưa thực hiện xác nhận và gửi số liệu."
                            };

                            if (messages[result] || result === "10") {

                                if (result !== "10") {
                                    $("#idViewMess").html(messages[result]);
                                } else {
                                    $("#idViewMess").html("");
                                }

                                if (["01", "11", "21", "31", "404"].includes(result)) {
                                    $("#idViewData").html("");
                                }

                                return;
                            }

                            $("#idViewData").html(result).show();

                            if ($tongHop.val() === "W") {
                                $buttonArea.hide();
                            } else {
                                $buttonArea.show();
                                $("#idViewMess").html("");
                            }
                        },

                        error: function () {
                            alert("Lỗi khi thực hiện.");
                        }
                    });
                }

            });

            function toggleButton() {
                $("#cmdKhoa").toggle(
                        $("#cboDot").val() === "1"
                        );
            }

            function cancelAssign() {
                if (confirm("Bạn có chắc chắn muốn khóa gửi dữ liệu toàn quốc không?")) {
                    $.ajax({
                        type: "GET",
                        url: "lock_khnv_02c.action",
                        data: {
                            cboDot: $("#cboDot").val(),
                            cboNam: $("#cboNam").val()
                        },
                        success: function (res) {
                            if (parseInt(res.status, 10) === 1) {
                                alert("Khóa gửi dữ liệu toàn quốc thành công!");
                            } else {
                                alert("Lỗi! " + res.message);
                            }
                        },
                        error: function () {
                            alert("Khóa gửi dữ liệu toàn quốc lỗi. Vui lòng liên hệ quản trị viên!");
                        }
                    });
                }
            }

        </script>
        <script type="text/javascript" src="js/jquery-2.1.26.js"></script>
    </body>
</html>
