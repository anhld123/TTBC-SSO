var popWindow;
var max_row = 0;

$(document).ready(function () {
    $('.sstyle').css({"color": "#000", "font-size": "12px"});
    $('input.number').css({"text-align": "right"});
    $('.D0').css({"text-align": "center"});
    $('.D00').css({"text-align": "left"});
    $('input.number2').css({"text-align": "right"});
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    $('#ui-datepicker-div').css('clip', 'auto');
    //Cac truong bang so --> se co so truong = 0
    $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
    $('.number2').number(true, 0);
    $(".STT1").css({"width": "50px"});
    $(".STT2").css({"width": "80px"});
    $(".STT3").css({"width": "150"});
    $(".STT4").css({"width": "200px"});
    $(".STT5").css({"width": "70px"});
    $(".STT6").css({"width": "65px"});
    $(".TD_NGUYENGIA").css({"width": "80px"});
    $(".TD_THUTU").css({"width": "30px"});
    $(".TD_CHITIEU").css({"width": "220px"});
    $(".TEN_KH").css({"width": "100%"});
});
function addRow(indx) {
    var index = parseInt(indx);
    var table = document.getElementById("subTable");
    var rowCount = table.rows.length - 5;
    if (max_row < rowCount) {
        max_row = rowCount + 1;
    } else {
        max_row++;
        rowCount = max_row;
    }
    var idPGD = "lstPGD_" + max_row;
    var idTenTb = "lstDm111_" + max_row;
    var idNsd = "lstDm112_" + max_row;
    var idNhomTb = "lstDm113_" + max_row;
    var idDvt = "lstDm114_" + max_row;
    var idThuctrang = "lstDm116_" + max_row;
    var idKyhieu = "lstDm111b_" + max_row;
    var newTr = '<tr>' +
            '<td ><input type="text" value="' + (max_row + 1) + '" id="TT_HIENTHI" name="lstDulieuNt[' + max_row + '].TT_HIENTHI" class="D0 number" onfocus="this.select();" /></td>' +
            '<td><select style="width: 150px;border: hidden" name="lstDulieuNt[' + max_row + '].D1" id="' + idPGD + '"></select></td>' +
            '<td><select style="width: 150px;border: hidden" ' +
            'name="lstDulieuNt[' + max_row + '].D2" ' +
            'id="' + idTenTb + '" ' +
            'onchange="updateD3Selection(this)"></select></td>' +
            '<td><select style="width: 150px;border: hidden; background: #f2f2f2" onmousedown="return false" name="lstDulieuNt[' + max_row + '].D3" id="' + idNhomTb + '"></select></td>' +
            '<td><select style="width: 150px;border: hidden" name="lstDulieuNt[' + max_row + '].D4" id="' + idNsd + '"></select></td>' +
            '<td><select style="width: 80px;border: hidden; background: #f2f2f2" onmousedown="return false" name="lstDulieuNt[' + max_row + '].D5" id="' + idDvt + '"></select></td>' +
            '<td><input type="text" value="1" id="D6' + max_row + '" name="lstDulieuNt[' + max_row + '].D6" class="number" onfocus="this.select();" onchange="calc(this);"/></td>' +
            '<td><input type="text" value="0" id="D7' + max_row + '" name="lstDulieuNt[' + max_row + '].D7" class="number" onfocus="this.select();" onchange="calc(this);"/></td>' +
            '<td><input readonly type="text" value="0" id="D8' + max_row + '" name="lstDulieuNt[' + max_row + '].D8" class="number" onfocus="this.select();"/></td>' +
            '<td class="D0"><select style="width: 150px;border: hidden; background: #f2f2f2" onmousedown="return false" name="lstDulieuNt[' + max_row + '].D9" id="' + idKyhieu + '"></select></td>' +
            '<td class="D0"><textarea type="text" value="" id="D10' + max_row + '" name="lstDulieuNt[' + max_row + '].D10" placeholder="Nhập tối đa 500 ký tự" maxlength="500" onfocus="this.select();" style="width: 98%"></textarea></td>' +
            '<td class="D0"><select style="width: 150px;border: hidden" name="lstDulieuNt[' + max_row + '].D11" id="' + idThuctrang + '"></select></td>' +
            '<td class="D0"><input type="button" style="color: red" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)"/></td>' +
            '</tr>';

    $(newTr).insertBefore($('table#subTable tr').eq(index));


    // Thiết lập lại các kiểu CSS
    $('.sstyle').css({"color": "#000", "font-size": "12px"});
    $('input.number').css({"text-align": "right"});
    $('.D0').css({"text-align": "center"});
    $('.D00').css({"text-align": "left"});
    $('input.number2').css({"text-align": "right"});
    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    $('#ui-datepicker-div').css('clip', 'auto');

    // Cài đặt định dạng số
    $('.number').number(true, 0);
    $('.number2').number(true, 0);

    $(".STT1").css({"width": "50px"});
    $(".STT2").css({"width": "80px"});
    $(".STT3").css({"width": "150px"});
    $(".STT4").css({"width": "200px"});
    $(".STT5").css({"width": "70px"});
    $(".STT6").css({"width": "65px"});
    $(".TD_NGUYENGIA").css({"width": "80px"});
    $(".TD_THUTU").css({"width": "30px"});
    $(".TD_CHITIEU").css({"width": "220px"});
    $(".TEN_KH").css({"width": "100%"});

    // Fetch data and populate the new select elements
    $.getJSON('loadDmKhac111', {
        Message: 'fileTemplate',
        khoa_nhaptaycn: 'KKTS_01'
    }, function (jsonResponse) {
        try {
            var dm_khac = '<option value="000000">---Tên thiết bị---</option>';
            $.each(jsonResponse.lstDmKhac111, function () {
                dm_khac += '<option value="' + this.sortOrder + '">' + this.sortOrder + ' - ' + this.value + '</option>';
            });
            $('#' + idTenTb).html(dm_khac);

            var dm_khacb = '<option value="000000">---Ký hiệu---</option>';
            $.each(jsonResponse.lstDmKhac111, function () {
                dm_khacb += '<option value="' + this.sortOrder + '">' + this.sortOrder + ' - ' + this.description + '</option>';
            });
            $('#' + idKyhieu).html(dm_khacb);

            if (jsonResponse.msgError !== null) {
                $('#message_suc_err').text(jsonResponse.msgError);
            }
        } catch (e) {
            alert(e.toString());
        }
    });
    $.getJSON('loadDmKhac112', {
        Message: 'fileTemplate',
        khoa_nhaptaycn: 'KKTS_01'
    }, function (jsonResponse) {
        try {
            var dm_khac = '<option value="000000">---Nhóm thiết bị---</option>';
            $.each(jsonResponse.lstDmKhac112, function () {
                dm_khac += '<option value="' + this.description + '">' + this.description + ' - ' + this.value + '</option>';
            });
            $('#' + idNhomTb).html(dm_khac);

            if (jsonResponse.msgError !== null) {
                $('#message_suc_err').text(jsonResponse.msgError);
            }
        } catch (e) {
            alert(e.toString());
        }
    });
    $.getJSON('loadPGD', {
        Message: 'fileTemplate',
        khoa_nhaptaycn: 'KKTS_01'
    }, function (jsonResponse) {
        try {
            var dm_pgd = '<option value="000000">---Chọn đơn vị---</option>';
            $.each(jsonResponse.lstPGD_API, function () {
                dm_pgd += '<option value="' + this.posCode + '">' + this.posCode + ' - ' + this.posName + '</option>';
            });
            $('#' + idPGD).html(dm_pgd);

            if (jsonResponse.msgError !== null) {
                $('#message_suc_err').text(jsonResponse.msgError);
            }
        } catch (e) {
            alert(e.toString());
        }
    });
    $.getJSON('loadDmKhac113', {
        Message: 'fileTemplate',
        khoa_nhaptaycn: 'KKTS_01'
    }, function (jsonResponse) {
        try {
            var dm_khac = '<option value="000000">---Nơi sử dụng---</option>';
            $.each(jsonResponse.lstDmKhac113, function () {
                dm_khac += '<option value="' + this.code + '">' + this.code + ' - ' + this.value + '</option>';
            });
            $('#' + idNsd).html(dm_khac);

            if (jsonResponse.msgError !== null) {
                $('#message_suc_err').text(jsonResponse.msgError);
            }
        } catch (e) {
            alert(e.toString());
        }
    });
    $.getJSON('loadDmKhac114', {
        Message: 'fileTemplate',
        khoa_nhaptaycn: 'KKTS_01'
    }, function (jsonResponse) {
        try {
            var dm_khac = '<option value="000000">---ĐVT---</option>';
            $.each(jsonResponse.lstDmKhac114, function () {
                dm_khac += '<option value="' + this.code + '">' + this.code + ' - ' + this.value + '</option>';
            });
            $('#' + idDvt).html(dm_khac);

            if (jsonResponse.msgError !== null) {
                $('#message_suc_err').text(jsonResponse.msgError);
            }
        } catch (e) {
            alert(e.toString());
        }
    });
    $.getJSON('loadDmKhac116', {
        Message: 'fileTemplate',
        khoa_nhaptaycn: 'KKTS_01'
    }, function (jsonResponse) {
        try {
            var dm_khac = '<option value="000000">---Tình trạng---</option>';
            $.each(jsonResponse.lstDmKhac116, function () {
                dm_khac += '<option value="' + this.code + '">' + this.code + ' - ' + this.value + '</option>';
            });
            $('#' + idThuctrang).html(dm_khac);

            if (jsonResponse.msgError !== null) {
                $('#message_suc_err').text(jsonResponse.msgError);
            }
        } catch (e) {
            alert(e.toString());
        }
    });
}

function deleteRow(indx) {
    var table = document.getElementById("subTable");
    table.deleteRow(indx);
    onLoadData();
}
function cancelAssign(D1, D13, D12) {
    var url, sdata;

    url = "delete_KKTS_2024.action?" + "sD1" + D1 + "&sMA=" + D13 + "&sD12=" + D12,
            sdata = jQuery("#frmdata").serialize();
    $("#viewData").html('<img src="img/loading.gif"/>');
    $.ajax({
        type: "POST",
        url: url,
        data: sdata,
        success: function (data) {
            if (data === "200") {
                alert("Xóa dữ liệu thành công!");
            } else if (data === "100") {
                alert("Lỗi: Đơn vị đã gửi dữ liệu không thể thao tác!");
            } else {
                alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
            }
            onLoadData();
        },
        error: function (request) {
            alert("Lỗi: Vui lòng liên hệ quản trị viên để được hỗ trợ!");
            onLoadData();
        }
    });
}
function calc(id) {
    var row = id.parentNode.parentNode;
    var CT_D3 = row.cells[6].getElementsByTagName('input')[0].value;
    var CT_D9 = row.cells[7].getElementsByTagName('input')[0].value;
    var res = parseFloat(CT_D3.replace(/,/g, '')) - parseFloat(CT_D9.replace(/,/g, ''));
    row.cells[8].getElementsByTagName('input')[0].value = res.toLocaleString('en-US');
    row.cells[8].getElementsByTagName('input')[0].style.color = 'red';
}
function updateD3Selection(d2Select) {
    const selectedValue = d2Select.value;
    const tr = d2Select.closest('tr');

    const d3Select = tr.querySelector('select[name$=".D3"]');
    const d9Select = tr.querySelector('select[name$=".D9"]');
    const d5Select = tr.querySelector('select[name$=".D5"]');

    if (d3Select)
        d3Select.value = selectedValue;
    if (d9Select)
        d9Select.value = selectedValue;

    if (d5Select) {
        if (selectedValue === "6") {
            d5Select.value = "2"; 
        } else {
            d5Select.value = "1";  
        }
    }
}



function funcTableFile(skhoa, smapgd, smacn, sduan, sngaybc) {
    var screenWidth = screen.width, screenHeight = screen.height;
    var w = screenWidth / 1.5;
    var h = screenHeight / 2;
    var left = (screenWidth - w) / 2;
    var top = (screenHeight - h) / 2;
    var urlParam = "skhoa=" + skhoa + "&smapgd=" + smapgd + "&smacn=" + smacn + "&sduan=" + sduan + "& sngaybc=" + sngaybc;
    var url = "/IMS_REPORTS/popupTableHoidong.action?" + urlParam;
    popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ", directories=no, status=no, menubar=no, personalbar=no, resizable=yes, location=no, scrollbars=yes, toolbar=no, border=no");
    popWindow.focus();
}