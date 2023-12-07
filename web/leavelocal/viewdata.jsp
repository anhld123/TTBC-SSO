<%-- 
    Document   : ViewData
    Created on : May 17, 2022, 9:56:22 AM
    Author     : NGUYEN PHU VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<style>
    #subTable {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 250%;
    }
    #subTable th{
        background-color: #ddd;
        color: #0000FF;
    }

    #subTable th, #subTable td {
        border: 1px solid gray;
    }

    #subTable tr:nth-child(even){background-color: #f2f2f2;}

    #subTable tr:hover {background-color: #ddd;}

    .txtPublic{
        width: 85px;
    }
    .ui-datepicker-trigger{
        height: 100%;
    }
    .txtBody{
        text-align: center;
    }
    .txtBody > .ui-datepicker-trigger{
        display: none;
    }
    td.hdtitle {
        position: sticky;
        top: 0;
        z-index: 10;
    }
    .ThanhVien{
        display: None;
    }
</style>
<script>
    var popWindow;
    var max_row = 0;
    $(document).ready(function () {
        initTable();
    });
    $(document).ready(function () {
        initTable1();
    });
    $(function () {
        setCssStyle();
    });
    function setCssStyle() {
        $(".cssDate").datepicker({
            dateFormat: 'dd/mm/yy',
            showOn: "button",
            buttonImage: "img/icon-ui_datepicker.png",
            buttonImageOnly: true,
            showButtonPanel: true,
            buttonText: "icono",
            changeMonth: true,
            changeYear: true,
            beforeShow: function (input, inst) {
                if ($(input).is(':disabled')) {
                    return false; // Ngăn chặn datepicker hiển thị nếu input bị disabled
                }
            }
        });
    }

    $(function () {
        setCssStyle1();
    });
    function setCssStyle1() {
        var date = new Date();
        var maxDate = "-" + date.getDate() + "D" + "-2M";
        $(".cssDate1").datepicker({
            dateFormat: 'dd/mm/yy',
            showOn: "button",
            buttonImage: "img/icon-ui_datepicker.png",
            buttonImageOnly: true,
            showButtonPanel: true,
            buttonText: "icono",
            maxDate: maxDate,
            changeMonth: true,
            changeYear: true
        });
    }

    $('.autoHeight').each(function () {
        this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
    }).on('input', function () {
        this.style.height = 'auto';
        this.style.height = (this.scrollHeight) + 'px';
    });
    function funcThanhVien(maPgd, maKH, tenKH, flagPos) {
        var w = 900, h = 600;
        var left = (screen.width / 2) - (w / 2);
        var top = (screen.height / 2) - (h / 2);
        var urlParam = "vsbpMaPgd=" + maPgd + "&vsbpMakh=" + maKH + "&vsbpTenKh=" + encodeURIComponent(tenKH) + "&vsbpNgayBC=" + $("#txtNgayBc").val() + "&vbsprandom=" + Math.random();
        var url;
        if (flagPos == '0')
        {
            url = "/IMS_REPORTS/popupThanhvien_author.action?" + urlParam;
        } else
        {
            url = "/IMS_REPORTS/popupThanhvien.action?" + urlParam;
        }
        popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
    }

    function funcXuLyNo(maPgd, maKH, tenKH, XuLyNo, startPayment, flagPos) {
        var w = 500, h = 300;
        var left = (screen.width / 2) - (w / 2);
        var top = (screen.height / 2) - (h / 2);
        var urlParam = "vsbpMaPgd=" + maPgd + "&vsbpMakh=" + maKH + "&vsbpTenKh=" + encodeURIComponent(tenKH) + "&vsbpNgayBC=" + $("#txtNgayBc").val()
                + "&XuLyNo=" + XuLyNo + startPayment + "&flagPos=" + flagPos + "&vbsprandom=" + Math.random();
        var url = "/IMS_REPORTS/popupXuLyNo.action?" + urlParam;
        popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
    }

    function funcDeNghiHT(maPgd, maKH, tenKH, flagPos, ngayDNHT, vbDNHT) {
        var w = 500, h = 300;
        var left = (screen.width / 2) - (w / 2);
        var top = (screen.height / 2) - (h / 2);
        var urlParam = "vsbpMaPgd=" + maPgd + "&vsbpMakh=" + maKH + "&vsbpTenKh=" + encodeURIComponent(tenKH)
                + "&flagPos=" + flagPos + "&ngaydenghi=" + ngayDNHT + "&sovbdenghi=" + vbDNHT + "&vbsprandom=" + Math.random();
        if (flagPos == '0')
        {
            url = "/IMS_REPORTS/popupDeNghiHT1.action?" + urlParam;
        } else
        {
            url = "/IMS_REPORTS/popupDeNghiHT.action?" + urlParam;
        }

        popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
    }

    function onSelectChange(index) {
        let selectedValue = $('#lstData_D30' + index).find(":selected").val();
        let province = selectedValue.substring(0, 4);
        let province1 = selectedValue.substring(2, 4);

        $('#lstData_D32' + index + ' option').each(function () {
            //if (!$(this).val().startsWith('0006') ) {
            $(this).remove();
            //}
        });
        $('#lstPGD_Temp option').each(function () {
            if ($(this).val().startsWith(province)) {
                //alert($(this).text() );
                $('#lstData_D32' + index).append($('<option>',
                        {
                            value: $(this).val(),
                            text: $(this).text()
                        }));
            }
        });
        $('#lstData_D28' + index + ' option').each(function () {
            //if (!$(this).val().startsWith('0006') ) {
            $(this).remove();
            //}
        });
        $('#lstXa_Temp option').each(function () {
            if ($(this).val().startsWith(province1)) {
                //alert($(this).text() );
                $('#lstData_D28' + index).append($('<option>',
                        {
                            value: $(this).val(),
                            text: $(this).text()
                        }));
            }
        });
//            document.getElementById("lstData_D32" + index).disabled = false;
        var selectElement = document.getElementById("lstData_D32" + index);
        selectElement.setAttribute("onmousedown", "return true;");
        document.getElementById("lstData_D32" + index).style.backgroundColor = "#ffffff";
    }

    function onSelectChangeXa(index) {
        let selectedValue = $('#lstData_D32' + index).find(":selected").val();
        let province = selectedValue.substring(2, 6);
//        let province1 = selectedValue.substring(0, 6);
//        alert(province1 + "  " + province);
        $('#lstData_D28' + index + ' option').each(function () {
            //if (!$(this).val().startsWith('0006') ) {
            $(this).remove();
            //}
        });
        $('#lstXa_Temp option').each(function () {
            if ($(this).val().startsWith(province)) {
                //alert($(this).text() );
                $('#lstData_D28' + index).append($('<option>',
                        {
                            value: $(this).val(),
                            text: $(this).text()
                        }));
            }
        });
        var selectElement = document.getElementById("lstData_D28" + index);
        selectElement.setAttribute("onmousedown", "return true;");
        document.getElementById("lstData_D28" + index).style.backgroundColor = "#ffffff";
    }

    function onSelectChange_dnht(value, index) {
        if (value == '1')
        {
            document.getElementById("lstDNHT_D33" + index).style.visibility = "visible";
        } else
        {
            document.getElementById("lstDNHT_D33" + index).style.visibility = "hidden";
        }
    }

    function onSelectChange_dcct(value, index) {
        try {
            if (value == '01')
            {
//                $('#lstSubData31' + index).find(":selected").val() = '0'
                document.getElementById("lstSubData31" + index).value = "0";
                document.getElementById("lstData_D44" + index).value = "";
                document.getElementById("lstSubData31" + index).disabled = true;
                document.getElementById("lstData_D44" + index).disabled = true;
                var var2, vartxt, selected;
                document.getElementById("lstDataD23" + index).style.backgroundColor = "#C7C0BF";
//                 $("#lstData_D30" + index).children().remove().end();
//                //$("#mato").prepend("<option value='000000_0000000' " + selected + "> -- Tất cả -- </option>");
//                $("#lstData_D30_tmp" + index + " > option").each(function () {
//                    var tmp = $(this).val();
//                    if (tmp != '000000' && tmp != '999999')
////                alert(tmp);
//                        $("#lstData_D30" + index).prepend("<option value='" + $(this).val() + "' " + selected + "> " + $(this).text() + " </option>");
//                });
//                $("#lstData_D30" + index).html($("#lstData_D30" + index + " option").sort(function (a, b) {
//                    return a.text == b.text ? 0 : a.text < b.text ? -1 : 1;
//                }));
//                onSelectChange(index);
                //------------------------
                $("#lstSubData_D33" + index).children().remove().end();
                $("#lstSubData_D33" + index).prepend("<option value='0' " + selected + "> 0: Không đề nghị hỗ trợ </option>");
                $("#lstSubData_D33" + index).prepend("<option value='1' " + selected + "> 1: Đề nghị hỗ trợ </option>");
            } else
            {
                document.getElementById("lstSubData31" + index).disabled = false;
                document.getElementById("lstDataD23" + index).removeAttribute("style");
                document.getElementById("lstDNHT_D33" + index).style.visibility = "hidden";

//                $("#lstData_D30" + index).children().remove().end()
//                $("#lstData_D30_tmp" + index + " > option").each(function () {
//                    $("#lstData_D30" + index).prepend("<option value='" + $(this).val() + "' " + selected + "> " + $(this).text() + " </option>");
//                });
//                $("#lstData_D30" + index).html($("#lstData_D30" + index + " option").sort(function (a, b) {
//                    return a.text == b.text ? 0 : a.text < b.text ? -1 : 1;
//                }));
//                onSelectChange(index);
//
//                $("#lstData_D28" + index).children().remove().end(); > ");
//                        $("#lstData_D28_tmp" + index + " > option").each(function () {
//                    $("#lstData_D28" + index).prepend("<option value='" + $(this).val() + "' " + selected + "> " + $(this).text() + " </option>");
//                });
//                $("#lstData_D28" + index).html($("#lstData_D28" + index + " option").sort(function (a, b) {
//                    return a.text == b.text ? 0 : a.text < b.text ? -1 : 1;
//                }));
//
//                $("#lstData_D32" + index).children().remove().end();
//                $("#lstData_D32_tmp" + index + " > option").each(function () {
//                    $("#lstData_D32" + index).prepend("<option value='" + $(this).val() + "' " + selected + "> " + $(this).text() + " </option>");
//                });
//                $("#lstData_D32" + index).html($("#lstData_D32" + index + " option").sort(function (a, b) {
//                    return a.text == b.text ? 0 : a.text < b.text ? -1 : 1;
//                }));

                //------------------------
//                $("#lstSubData_D33" + index).children().remove().end();
//                $("#lstSubData_D33" + index).prepend("<option value='0' " + selected + "> 0: Không đề nghị hỗ trợ </option>");
                //----------Đề nghị cung cấp thông tin

                $("#lstSubData31" + index).children().remove().end();
                $("#lstSubData31" + index).prepend("<option value='0' " + selected + "> 0: Không </option>");
                $("#lstSubData31" + index).prepend("<option value='1' " + selected + "> 1: Có </option>");

            }
        } catch (e) {
        }
    }

    function initTable()
    {
        var selected;
        var table = document.getElementById("subTable");
        var rowcount = table.rows.length;
        rowcount = rowcount > max_row ? rowcount : max_row;
//        alert('row=' + rowcount)
        for (var i = 0; i < rowcount; i++)
        {
            try {
                var flagPos = document.getElementById('lstData42' + i).value;
//                document.getElementById("lstData_D32" + i).disabled = true;
//                 alert (flagPos +  '---'+ i)
                if (flagPos = '1')
                {
                    var value = $('#lstSubData_D33' + i).find(":selected").val();
//                alert (value +  '---'+ i)
                    if (value == '1')
                    {
                        document.getElementById("lstDNHT_D33" + i).style.visibility = "visible";
                        var lstData39 = document.getElementById('lstData39' + i).value;
                        if (lstData39.length < 3)
                        {
                            document.getElementById("lstDNHT_D33" + i).style.color = "red";
                        }
                    } else
                    {
                        document.getElementById("lstDNHT_D33" + i).style.visibility = "hidden";
                    }
                    var D22 = $('#lstData_D22' + i).find(":selected").val();
                    if (D22 == '01')
                    {
                        document.getElementById("lstSubData31" + i).disabled = true;
                    } else
                    {
                        var D21 = document.getElementById("lstSubData_D33" + i).value;
                        document.getElementById("lstSubData31" + i).disabled = false;
                        //------------------------De nghị hỗ trợ
                        $("#lstSubData_D33" + i).children().remove().end();

//                        alert(D21);
                        if (D21 === '1') {
                            $("#lstSubData_D33" + i).prepend("<option value='1' " + selected + "> 1: Đề nghị hỗ trợ </option>");
                        } else {
                            $("#lstSubData_D33" + i).prepend("<option value='0' " + selected + "> 0: Không đề nghị hỗ trợ </option>");
                            document.getElementById("lstDNHT_D33" + i).style.visibility = "hidden";
                        }

                        //------------------------Đề nghị cung cấp thông tin

                    }
                }

                var lstData_D22 = document.getElementById('lstData_D22' + i).value;


                var lstSubData31 = document.getElementById('lstSubData31' + i).value;
                if (lstData_D22 = '02' && lstSubData31 == '1')
                {
//                    alert('1');
//                    document.getElementById("lstData_D30" + i).style.visibility = "visible";
                    document.getElementById("lstData_D30" + i).disabled = false;
                    document.getElementById("lstData_D32" + i).disabled = false;
                    document.getElementById("lstData_D28" + i).disabled = false;
                }

            } catch (e) {
            }
        }
    }

    function onSelectChange_dnht1(value, index) {
        if (value == '5')
        {
            document.getElementById("lstData41" + index).disabled = false;
        } else
        {
            document.getElementById("lstData41" + index).disabled = true;
        }
    }

    function onSelectChange_tccc_rasoat(value, index) {
        var selected;
        if (value == '01')
        {
            $("#lstSubData_D33" + index).children().remove().end();
            $("#lstSubData_D33" + index).prepend("<option value='0' " + selected + "> 0: Không đề nghị hỗ trợ </option>");
            document.getElementById("lstDNHT_D33" + index).style.visibility = "hidden";
        } else
        {
            $("#lstSubData_D33" + index).children().remove().end();
            $("#lstSubData_D33" + index).prepend("<option value='0' " + selected + "> 0: Không đề nghị hỗ trợ </option>");
            $("#lstSubData_D33" + index).prepend("<option value='1' " + selected + "> 1: Đề nghị hỗ trợ </option>");
        }
    }

    function initTable1()
    {
        var table = document.getElementById("subTable");
        var rowcount = table.rows.length;
        rowcount = rowcount > max_row ? rowcount : max_row;
        for (var i = 0; i < rowcount; i++)
        {
            try {
                var flagPos = document.getElementById('lstData42' + i).value
                if (flagPos != '1')
                {
                    var value = $('#lstSubData34' + i).find(":selected").val();
                    if (value == '5')
                    {
                        document.getElementById("lstData41" + i).disabled = false;
                    } else
                    {
                        document.getElementById("lstData41" + i).disabled = true;
                    }
                }
            } catch (e) {
            }
        }
    }

    function Check_var30(index) {
        var countDisplay = document.getElementById("countDisplay" + index);
        var selectElement = document.getElementById("lstData_D30" + index);
        if (selectElement.value === "0")
        {
            countDisplay.innerText = 0;
        } else {
            countDisplay.innerText = 1;
        }
    }

    function Check_var32(index) {
        var countDisplay1 = document.getElementById("countDisplay1" + index);
        var selectElement = document.getElementById("lstData_D32" + index);
        if (selectElement.value === "0")
        {
            countDisplay1.innerText = 0;
        } else {
            countDisplay1.innerText = 1;
        }
    }

    function Check_var28(index) {
        var countDisplay2 = document.getElementById("countDisplay2" + index);
        var selectElement = document.getElementById("lstData_D28" + index);
        if (selectElement.value === "0")
        {
            countDisplay2.innerText = 0;
        } else {
            countDisplay2.innerText = 1;
        }
    }

</script>
</head>
<body>
    <div style="overflow:scroll; width: 99vw; height: 400px;">     
        <div style="display: none;">
            <select id="lstPGD_Temp">
                <option value="000000">Không xác định</option>
                <s:iterator value="lstPGD_API" status="ideRows" var="language">                                    
                    <option value="<s:property value="PosCode"/>"><s:property value="PosCode"/> - <s:property value="PosName"/></option>                                    
                </s:iterator>
                <option value="999999">Nước ngoài</option>
            </select>
            <select id="lstXa_Temp">
                <option value="000000">Không xác định</option>
                <option value="999999">Nước ngoài</option>
                <s:iterator value="lstXa_API" status="ideRows" var="language">                                    
                    <option value="<s:property value="communeCode"/>"><s:property value="communeCode"/> - <s:property value="communeName"/></option>                                    
                </s:iterator>
            </select>
        </div>
        <table id="subTable" style="z-index: 1">   
            <thead>
                <tr>
                    <th  rowspan="2" class="hdtitle">
                        <input type="checkbox" id ="select-all"/>
                    </th>  
                    <th rowspan="2" class="hdtitle">STT</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Tên chi nhánh</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Tên PGD</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Tên xã</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Tên tổ trưởng</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Mã KH</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Tên KH vay vốn</th>
                    <th rowspan="2" class="hdtitle" style="width: 80px">Năm sinh</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">CMT/CCCD</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Số điện thoại</th>
                    <th rowspan="2" class="hdtitle">Loại đối tượng</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Thời điểm đi</th>
                    <th rowspan="2" class="hdtitle">Mã nhóm</th>
                    <th rowspan="2" class="hdtitle">Đề nghị<br>cung cấp<br>thông tin</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Ngày đề nghị <br>cung cấp<br>thông tin</th>
                    <th rowspan="2" class="hdtitle">Thông tin hỗ trợ <br>(địa chỉ cụ thể)</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Ngày cập nhật<br>thông tin</th>
                    <th rowspan="2" class="hdtitle">Thông tin <br>(địa chỉ cụ thể)</th>
                    <th rowspan="2"class="hdtitle">Chi nhánh hộ vay <br>chuyển đến</th>
                    <th rowspan="2" class="hdtitle">PGD hộ vay <br>chuyển đến</th>
                    <th rowspan="2" class="hdtitle">Xã hộ vay <br>chuyển đến</th>
                    <th rowspan="2" class="hdtitle" style="width: 200px" >Đề nghị hỗ trợ</th>
                    <th colspan="2" class="hdtitle">Kết quả hỗ trợ</th>
                    <th rowspan="2" class="hdtitle">Tổ chức CT-XH rà soát</th>
                    <th rowspan="2" class="hdtitle">Mã quản lý</th>
                </tr>
                <tr>
                    <th class="hdtitle">Kết quả hỗ trợ</th>
                    <th class="hdtitle">Ghi chú trường hợp 05</th>
                </tr>

            </thead>
            <tr class="txtBody">
                <th style="color: #000; font-style: italic; font-size: xx-small;"></th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(8)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(9)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(10)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(11)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(12)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(13)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(14)</th>
                <!--<th style="color: #000; font: italic; font-size: xx-small;">()</th>-->
                <th style="color: #000; font-style: italic; font-size: xx-small;">(15)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(16)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(17)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(18)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(19)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(20)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(21)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(22)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(23)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(24)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(25)</th>
                <th style="color: #000; font-style: italic; font-size: xx-small;">(26)</th>
            </tr>
            <tbody>
                <% int customerCount = 0; %>
                <s:iterator value="lstData" status="idxRows">
                    <tr class="tr_clone">
                        <td class="txtBody" style="display: none;">                            
                            <s:property value="D3"/>
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].key" value="<s:property value='key'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].orderValue" value="<s:property value='orderValue'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].code" value="<s:property value='code'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].name" value="<s:property value='name'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].posCode" value="<s:property value='posCode'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].posFlag" value="<s:property value='posFlag'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].branchCode" value="<s:property value='branchCode'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].reportDate" value="<s:property value='reportDate'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].reportYear" value="<s:property value='reportYear'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].makerId" value="<s:property value='makerId'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].makerDate" value="<s:property value='makerDate'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].authoriseId" value="<s:property value='authoriseId'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].authoriseDate" value="<s:property value='authoriseDate'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d1" value="<s:property value='d1'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d2" value="<s:property value='d2'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d3" value="<s:property value='d3'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d4" value="<s:property value='d4'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d5" value="<s:property value='d5'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d6" value="<s:property value='d6'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d7" value="<s:property value='d7'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d8" value="<s:property value='d8'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d9" value="<s:property value='d9'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d10" value="<s:property value='d10'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d11" value="<s:property value='d11'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d12" value="<s:property value='d12'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d13" value="<s:property value='d13'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d14" value="<s:property value='d14'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d15" value="<s:property value='d15'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d17" value="<s:property value='d17'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d18" value="<s:property value='d18'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d19" value="<s:property value='d19'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d20" value="<s:property value='d20'/>">  
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d50" value="<s:property value='d50'/>">
                            <s:if test="D42.equalsIgnoreCase('0') && !(D22.equalsIgnoreCase('02') && D31.equalsIgnoreCase('1'))"> 
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d32" value="<s:property value='d32'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d30" value="<s:property value='d30'/>">
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d28" value="<s:property value='d30'/>">
                            </s:if>
                        </td>
                        <td class="txtBody" >
                            <s:if test="!D50.toString().equalsIgnoreCase('2')">
                                <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                </s:if>
                                <s:else> 
                                    <input id="STT_1<s:property  value='%{#idxRows.index}' />"
                                           type="checkbox" class="myCheckBox" name="lstData[<s:property  value='%{#idxRows.index}' />].manualFlag" value="0" onclick="$(this).val(this.checked ? 1 : 0)">
                                    <% customerCount += 1;%>
                                </s:else>
                            </s:if>
                            <s:else>
                                <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                </s:if>
                                <s:else> 
                                    <a style="color: #0000FF" title="Món đã phê duyệt">&#10003;</a>
                                    <% customerCount += 1;%>
                                </s:else>
                            </s:else>
                        </td>                                            
                        <td class="txtBody">                            
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')">                                 
                            </s:if>
                            <s:else>                                                                
                                <%= customerCount%>

                            </s:else>                            
                        </td>
                        <td class="txtBody"><div class="<s:property value="d20"/>"><s:property value="d4"/></div></td>
                        <td class="txtBody"><div class="<s:property value="d20"/>"><s:property value="d6"/></div></td>
                        <td class="txtBody"><div class="<s:property value="d20"/>"><s:property value="d8"/></div></td>
                        <td class="txtBody"><div class="<s:property value="d20"/>"><s:property value="d10"/></div></td>
                        <td class="txtBody">
                            <s:property value="D11"/>

                        </td>
                        <td class="txtBody">
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                <s:property value="D12"/>
                            </s:if>
                            <s:else>
                                <s:if test="!d50.equalsIgnoreCase('2')">
                                    <s:if test="d45 == null">
                                        <a href="javascript:funcThanhVien('<s:property value="d5"/>', '<s:property value="d11"/>', '<s:property value="d12"/>', '<s:property value="D42"/>')"><s:property value="d12"/></a>
                                    </s:if>
                                    <s:else>
                                        <s:property value="D12"/> 
                                    </s:else>
                                </s:if> 
                                <s:else>
                                    <s:property value="D12"/>
                                </s:else>

                            </s:else>
                        </td>
                        <td class="txtBody">
                            <s:property value="d13"/>
                        </td>
                        <td class="txtBody"><s:property value="d14"/></td>
                        <td class="txtBody">
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                <s:property value="D16"/>
                            </s:if>
                            <s:else>
                                <input style="text-align: center" type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D16" value="<s:property value='D16'/>" id="lstData_D16<s:property  value='%{#idxRows.index}' />" class="txtPublic" <s:if test="D42.equalsIgnoreCase('0') || D50.equalsIgnoreCase('2')"> disabled </s:if> >
                            </s:else>
                        </td>
                        <td class="txtBody">
                            <s:property value="d15"/>
                        </td>
                        <td>
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                            </s:if>
                            <s:else>
                                <s:if test="!D50.equalsIgnoreCase('2') ">
                                    <input style="width: 75px; text-align: center" type="text" readonly="readonly" class="cssDate1 <s:property value="d20"/>" id="lstData_D21<s:property  value='%{#idxRows.index}' />"  name="lstData[<s:property  value='%{#idxRows.index}' />].d21" value="<s:property value='d21'/>"
                                           <s:if test="D42.equalsIgnoreCase('0') || D50.equalsIgnoreCase('2')"> disabled </s:if> >                           
                                </s:if>
                                <s:else>
                                    <s:property value="d21"/>
                                </s:else>
                            </s:else>
                        </td>
                        <s:if test="D42.toString().equalsIgnoreCase('1')">
                            <td class="txtBody">
                                <select 
                                    onchange="onSelectChange_dcct(this.value, <s:property  value='%{#idxRows.index}'/>)"
                                    class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].D22"
                                    id="lstData_D22<s:property  value='%{#idxRows.index}' />" <s:if test="D42.equalsIgnoreCase('0') || D50.equalsIgnoreCase('2')"> disabled </s:if> >                                
                                    <option value="02" <s:if test="d22.equalsIgnoreCase('02')"> selected </s:if> <s:else></s:else>>02: Không có thông tin địa chỉ cụ thể</option>
                                    <option value="01" <s:if test="d22.equalsIgnoreCase('01')"> selected </s:if> <s:else></s:else>>01: Có thông tin địa chỉ cụ thể</option>
                                    </select>
                                </td>

                                <td class="txtBody">
                                        <select onchange="onSelectChange_time(this.value, <s:property  value='%{#idxRows.index}'/>)" class="<s:property value="d20"/>"
                                        name="lstData[<s:property  value='%{#idxRows.index}' />].d31" 
                                        id="lstSubData31<s:property  value='%{#idxRows.index}' />" <s:if test="D42.equalsIgnoreCase('0') || D50.equalsIgnoreCase('2')"> disabled </s:if>> 
                                    <option value="0" <s:if test="d31.equalsIgnoreCase('0')"> selected </s:if> <s:else></s:else>>0: Không</option>
                                    <option value="1" <s:if test="d31.equalsIgnoreCase('1')"> selected </s:if> <s:else></s:else>>1: Có</option>
                                    </select>
                                </td>
                                <td>
                                <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                </s:if>
                                <s:else>
                                    <input style="width: 75px; text-align: center" type="text" readonly="readonly" class="cssDate <s:property value="d20"/>" 
                                           id="lstData_D44<s:property  value='%{#idxRows.index}' />"  name="lstData[<s:property  value='%{#idxRows.index}' />].d44" value="<s:property value='d44'/>">                           

                                </s:else>
                            </td>
                        </s:if>
                        <s:else>
                            <td class="txtBody <s:property value="d20"/>" style="background: #f2f2f2" 
                                <s:if test="d22.equalsIgnoreCase('01')"><a>01: Có thông tin địa chỉ cụ thể</a></s:if>
                                <s:elseif test="d22.equalsIgnoreCase('02')"><a>02: Không có thông tin địa chỉ cụ thể</a></s:elseif>
                                <input style="width: 95px" type="hidden" name="lstData[<s:property  value='%{#idxRows.index}' />].d22" id="lstData_D22<s:property  value='%{#idxRows.index}' />" 
                                       value="<s:property  value="D22" />" >
                            </td>
                            <td class="txtBody <s:property value="d20"/>" style="background: #f2f2f2"                                 
                                <s:if test="d31.equalsIgnoreCase('0')"><a>0 :Không</a></s:if>
                                <s:elseif test="d31.equalsIgnoreCase('1')"><a>1: Có</a></s:elseif>
                                <s:else><a>0: Không</a></s:else>
                                <input style="width: 95px" type="hidden" name="lstData[<s:property  value='%{#idxRows.index}' />].d31" id="lstSubData31<s:property  value='%{#idxRows.index}' />" 
                                       value="<s:property  value="D31" />" >
                            </td>
                            <td class="txtBody" style="background: #f2f2f2"><s:property value="d44"/></td>      
                        </s:else>
                        <td class="txtBody">
                            <textarea  placeholder="Nhập tối đa 200 ký tự" id="lstDataD27<s:property  value='%{#idxRows.index}' />" name="lstData[<s:property  value='%{#idxRows.index}' />].d27" class="autoHeight <s:property value="d20"/>" <s:if test="D42.equalsIgnoreCase('1') || D50.equalsIgnoreCase('2')"> disabled </s:if> ><s:property value='d27'/></textarea>
                            <input style="width: 95px" type="hidden" name="lstData[<s:property  value='%{#idxRows.index}' />].d42" id="lstData42<s:property  value='%{#idxRows.index}' />" 
                                   value="<s:property  value="D42" />" >
                            <input style="width: 95px" type="hidden" name="lstData[<s:property  value='%{#idxRows.index}' />].d39" id="lstData39<s:property  value='%{#idxRows.index}' />" 
                                   value="<s:property  value="D39" />" >
                        </td>

                        <td>
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                            </s:if>
                            <s:else>
                                <s:if test="!D42.toString().equalsIgnoreCase('1') ">
                                    <input  style="width: 75px; text-align: center" type="text" readonly="readonly" class="cssDate <s:property value="d20"/>"  id="lstData_D26<s:property  value='%{#idxRows.index}' />"  name="lstData[<s:property  value='%{#idxRows.index}' />].d26" <s:if test="D42.equalsIgnoreCase('1') || D50.equalsIgnoreCase('2')"> disabled </s:if> value="<s:property value='d26 != null ? d26 : new java.util.Date()'/>">   
                                </s:if>
                                <s:else>
                                    <input  style="width: 75px; text-align: center" type="text" readonly="readonly" class="cssDate <s:property value="d20"/>"  id="lstData_D26<s:property  value='%{#idxRows.index}' />"  name="lstData[<s:property  value='%{#idxRows.index}' />].d26" <s:if test="D42.equalsIgnoreCase('1') || D50.equalsIgnoreCase('2')"> disabled </s:if> value="<s:property value='d26'/>">                                    
                                </s:else>
                            </s:else>
                        </td>

                        <td class="txtBody">
                            <textarea  placeholder="Nhập tối đa 200 ký tự" id="lstDataD23<s:property  value='%{#idxRows.index}' />" name="lstData[<s:property  value='%{#idxRows.index}' />].d23" class="autoHeight <s:property value="d20"/>" <s:if test="D42.equalsIgnoreCase('0') || D50.equalsIgnoreCase('2')"> disabled </s:if> ><s:property value='d23'/></textarea>
                            </td>
                            <td> 
                                <select  onclick="Check_var30(<s:property  value='%{#idxRows.index}'/>)" onchange="onSelectChange(<s:property  value='%{#idxRows.index}'/>)" class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d30" id="lstData_D30<s:property  value='%{#idxRows.index}' />" <s:if test="D42.equalsIgnoreCase('0') || D50.equalsIgnoreCase('2')"> disabled </s:if> >
                                    <option value="000000">Không xác định</option>
                                    <option value="999999">00 - Nước ngoài</option>
                                <s:iterator value="lstCN_API" status="ideRows" var="language">
                                    <s:if test="%{#language.branchCode == d30}">
                                        <option value="<s:property value="branchCode"/>" selected><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>
                                    </s:if>
                                    <s:else>
                                        <option value="<s:property value="branchCode"/>"><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>
                                    </s:else>
                                </s:iterator>

                            </select>
                            <!--Ẩn-->   
                            <select class="THANHVIEN"  id="lstData_D30_tmp<s:property  value='%{#idxRows.index}' />"  cssStyle="display:none;">
                                <option value="000000">Không xác định</option>
                                <option value="999999">00 - Nước ngoài</option>
                                <s:iterator value="lstCN_API" status="ideRows" var="language">
                                    <s:if test="%{#language.branchCode == d30}">
                                        <option value="<s:property value="branchCode"/>" selected><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>
                                    </s:if>
                                    <s:else>
                                        <option value="<s:property value="branchCode"/>"><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>
                                    </s:else>
                                </s:iterator>
                            </select>   
                            <a id="countDisplay<s:property  value='%{#idxRows.index}' />" style="color: #ffffff; font-size: 1">0</a>
                        </td>
                        <td>
                            <select onmousedown="return false" onclick="Check_var32(<s:property  value='%{#idxRows.index}'/>)" 
                                    title="Chọn chi nhánh để mở chọn PGD"
                                    onchange="onSelectChangeXa(<s:property  value='%{#idxRows.index}'/>)"
                                    style="width: 200px;background: #f2f2f2"  class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d32" id="lstData_D32<s:property  value='%{#idxRows.index}' />" <s:if test="D42.equalsIgnoreCase('0') || D50.equalsIgnoreCase('2')"> disabled </s:if> >
                                        <option value="000000">Không xác định</option>
                                        <option value="999999">00 - Nước ngoài</option>
                                    <s:iterator value="lstPGD_API" status="ideRows" var="language">
                                        <s:if test="%{#language.PosCode == d32}">
                                            <option value="<s:property value="PosCode"/>" selected><s:property value="PosCode"/> - <s:property value="PosName"/></option>
                                        </s:if>
                                        <s:else>
                                            <option value="<s:property value="PosCode"/>"><s:property value="PosCode"/> - <s:property value="PosName"/></option>
                                        </s:else>
                                    </s:iterator>                                
                            </select>
                            <!--Ẩn-->   
                            <select class="THANHVIEN"  id="lstData_D32_tmp<s:property  value='%{#idxRows.index}' />"  cssStyle="display:none;">
                                <option value="000000">Không xác định</option>
                                <option value="999999">00 - Nước ngoài</option>
                                <s:iterator value="lstPGD_API" status="ideRows" var="language">
                                    <s:if test="%{#language.PosCode == d32}">
                                        <option value="<s:property value="PosCode"/>" selected><s:property value="PosCode"/> - <s:property value="PosName"/></option>
                                    </s:if>
                                    <s:else>
                                        <option value="<s:property value="PosCode"/>"><s:property value="PosCode"/> - <s:property value="PosName"/></option>
                                    </s:else>
                                </s:iterator>
                            </select>    
                            <a id="countDisplay1<s:property  value='%{#idxRows.index}' />" style="color: #ffffff; font-size: 1">0</a>
                        </td>
                        <td class="<s:property value="d20"/>">
                            <select onmousedown="return false" onclick="Check_var28(<s:property  value='%{#idxRows.index}'/>)" 
                                    title="Chọn phòng giao dịch để mở chọn xã"
                                    style="width: 200px; background: #f2f2f2"  class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d28" id="lstData_D28<s:property  value='%{#idxRows.index}' />" <s:if test="D42.equalsIgnoreCase('0') || D50.equalsIgnoreCase('2')"> disabled </s:if> >
                                        <option value="000000">Không xác định</option>
                                        <option value="999999">Nước ngoài</option>
                                    <s:iterator value="lstXa_API" status="ideRows" var="language">
                                        <s:if test="%{#language.communeCode == d28}">
                                            <option value="<s:property value="communeCode"/>" selected><s:property value="communeCode"/> - <s:property value="communeName"/></option>
                                        </s:if>
                                        <s:else>
                                            <option value="<s:property value="communeCode"/>"><s:property value="communeCode"/> - <s:property value="communeName"/></option>
                                        </s:else>
                                    </s:iterator>                                
                            </select>
                            <select class="THANHVIEN"  id="lstData_D28_tmp<s:property  value='%{#idxRows.index}' />"  cssStyle="display:none;">
                                <option value="000000">Không xác định</option>
                                <option value="999999">Nước ngoài</option>
                                <s:iterator value="lstXa_API" status="ideRows" var="language">
                                    <s:if test="%{#language.communeCode == d28}">
                                        <option value="<s:property value="communeCode"/>" selected><s:property value="communeCode"/> - <s:property value="communeName"/></option>
                                    </s:if>
                                    <s:else>
                                        <option value="<s:property value="communeCode"/>"><s:property value="communeCode"/> - <s:property value="communeName"/></option>
                                    </s:else>
                                </s:iterator>
                            </select>   
                            <a id="countDisplay2<s:property  value='%{#idxRows.index}' />" style="color: #ffffff; font-size: 1">0</a>
                        </td>

                        <td class="txtBody" >
                            <select onchange="onSelectChange_dnht(this.value, <s:property  value='%{#idxRows.index}'/>)" class=" <s:property value="d20"/>" <s:if test="!D42.equalsIgnoreCase('1')"> disabled </s:if>
                                    name="lstData[<s:property  value='%{#idxRows.index}' />].d33" id="lstSubData_D33<s:property  value='%{#idxRows.index}' />" >
                                <option value="0" <s:if test="d33.equalsIgnoreCase('0')"> selected </s:if> <s:else></s:else>>0: Không đề nghị hỗ trợ</option>
                                <option value="1" <s:if test="d33.equalsIgnoreCase('1')"> selected </s:if> <s:else></s:else>>1: Đề nghị hỗ trợ</option>
                                </select>
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                            </s:if>
                            <s:else>
                                <a href="javascript:funcDeNghiHT('<s:property value="d5"/>', '<s:property value="d11"/>', '<s:property value="d12"/>', '<s:property value="D42"/>', '<s:property value="D38"/>', '<s:property value="D39"/>')"  
                                   id="lstDNHT_D33<s:property  value='%{#idxRows.index}' />"

                                   >link</a>
                            </s:else>

                        </td>

                            <td class="txtBody">
                                <select onchange="onSelectChange_dnht1(this.value, <s:property  value='%{#idxRows.index}'/>)" <s:if test="D42.equalsIgnoreCase('1')"> disabled </s:if>
                                        class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d34" id="lstSubData34<s:property  value='%{#idxRows.index}' />">
                                    <option value="0" <s:if test="d34.equalsIgnoreCase('0')"> selected </s:if> <s:else></s:else>>00: Chưa rà soát</option>
                                    <option value="1" <s:if test="d34.equalsIgnoreCase('1')"> selected </s:if> <s:else></s:else>>01: Khách hàng cam kết thực hiện nghĩa vụ trả nợ</option>
                                    <option value="2" <s:if test="d34.equalsIgnoreCase('2')"> selected </s:if> <s:else></s:else>>02: Khách hàng thuộc đối tượng xử lý nợ bị rủi ro</option>
                                    <option value="3" <s:if test="d34.equalsIgnoreCase('3')"> selected </s:if> <s:else></s:else>>03: Khách hàng chây ỳ</option>
                                    <option value="4" <s:if test="d34.equalsIgnoreCase('4')"> selected </s:if> <s:else></s:else>>04: Không liên hệ được với khách hàng</option>
                                    <option value="5" <s:if test="d34.equalsIgnoreCase('5')"> selected </s:if> <s:else></s:else>>05: Trường hợp khác</option>
                                    </select></td>

                        <td class="txtBody">
                                <textarea  placeholder="Nhập tối đa 200 ký tự" id="lstData41<s:property  value='%{#idxRows.index}' />" name="lstData[<s:property  value='%{#idxRows.index}' />].d41" class="autoHeight <s:property value="d20"/>" <s:if test="D42.equalsIgnoreCase('1') || D50.equalsIgnoreCase('2')"> disabled </s:if>><s:property value='d41'/></textarea>
                        </td>
    
                     
                            <td class="txtBody">
                                <select onchange="onSelectChange_tccc_rasoat(this.value, <s:property  value='%{#idxRows.index}'/>)" class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].D24" id="lstData24<s:property  value='%{#idxRows.index}' />" <s:if test="D42.equalsIgnoreCase('0') || D50.equalsIgnoreCase('2')"> disabled </s:if> > 
                                    <option value="00" <s:if test="d24.equalsIgnoreCase('00')"> selected </s:if> <s:else></s:else>>00: Không rà soát</option>
                                    <option value="01" <s:if test="d24.equalsIgnoreCase('01')"> selected </s:if> <s:else></s:else>>01: Cam kết</option>
                                    <option value="02" <s:if test="d24.equalsIgnoreCase('02')"> selected </s:if> <s:else></s:else>>02: Không liên hệ được</option>
                                    <option value="03" <s:if test="d24.equalsIgnoreCase('03')"> selected </s:if> <s:else></s:else>>03: Liên hệ được nhưng không cam kết</option>
                                    <option value="04" <s:if test="d24.equalsIgnoreCase('04')"> selected </s:if> <s:else></s:else>>04: Liên hệ được nhưng không nhận nợ</option>
                                    <option value="05" <s:if test="d24.equalsIgnoreCase('05')"> selected </s:if> <s:else></s:else>>05: Không thực hiện cam kết</option>

                                    </select>
                                </td>
                   
                        <s:if test="D20.equalsIgnoreCase('ThanhVien')">
                            <td></td><td></td> <td></td> <td></td>                                        
                        </s:if>
                        <s:else>
                            <td class="txtBody">
                                <select class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d25" id="lstSubData_D25<s:property  value='%{#idxRows.index}' />" <s:if test="D42.equalsIgnoreCase('0') || D50.equalsIgnoreCase('2')"> disabled </s:if> >
                                    <option value="00" <s:if test="d25.equalsIgnoreCase('00')"> selected </s:if> <s:else></s:else>>00: Khách hàng bỏ đi</option>
                                    <option value="01" <s:if test="d25.equalsIgnoreCase('01')"> selected </s:if> <s:else></s:else>>01: Tất toán nợ</option>
                                    <option value="02" <s:if test="d25.equalsIgnoreCase('02')"> selected </s:if> <s:else></s:else>>02: Xoá nợ</option>
                                    <option value="03" <s:if test="d25.equalsIgnoreCase('03')"> selected </s:if> <s:else></s:else>>03: Trở về địa phương</option>
                                    </select></td>
                            </s:else>       
                    </tr>
                </s:iterator>
            </tbody>
        </table>
    </div>
</body>
<script>
    $(function () {
        $('#select-all').click(function (event) {
            if (this.checked) {
                // Iterate each checkbox
                $('.myCheckBox').each(function () {
                    this.checked = true;
                    this.value = '1';
                });
            } else {
                $('.myCheckBox').each(function () {
                    this.checked = false;
                    this.value = '0';
                });
            }
        });
    });

    function onSelectChange_time(value, index) {
        if (value == '1') {
            document.getElementById("lstData_D44" + index).disabled = false;
            var today = new Date();
            var dd = String(today.getDate()).padStart(2, '0');
            var mm = String(today.getMonth() + 1).padStart(2, '0');
            var yyyy = today.getFullYear();
            var currentDate = dd + '/' + mm + '/' + yyyy;
            document.getElementById("lstData_D44" + index).value = currentDate;
        } else {
            document.getElementById("lstData_D44" + index).disabled = true;
        }
    }

    function initTable2() {
        var table = document.getElementById("subTable");
        var rowcount = table.rows.length;
        var max_row = 0; // Assuming max_row is defined somewhere
        rowcount = rowcount > max_row ? rowcount : max_row;
        for (var i = 0; i < rowcount; i++) {
            try {
                var value = $('#lstSubData31' + i).find(":selected").val();
                var value1 = $('#lstData_D22' + i).find(":selected").val();
                if (value === '1' && value1 === '02') {
                    document.getElementById("lstData_D44" + i).disabled = false;
                } else {
                    document.getElementById("lstData_D44" + i).disabled = true;
                }
            } catch (e) {
            }
        }
    }

    initTable2();
</script>