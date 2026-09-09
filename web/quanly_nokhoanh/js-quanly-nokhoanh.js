var popWindow;
var max_row = 0;
$(document).ready(function () {
    initTable();

    $('.editDelete, .editDelete th, .editDelete td, .editDelete input, .editDelete select, .editDelete textarea, .sstyle')
            .css({"font-size": "11px"});

    $('input.number, input.number2').css({"text-align": "right"});
    $('.D0').css({"text-align": "center"});
    $('.D00').css({"text-align": "left"});

    $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
    $('#ui-datepicker-div').css('clip', 'auto');

    // Định dạng số
    $('.number').number(true, 0);
    $('.number2').number(true, 0);

    // Thiết lập độ rộng các cột
    $(".STT1").css({"width": "50px"});
    $(".STT2").css({"width": "100px"});
    $(".STT3").css({"width": "150px"});
    $(".STT4").css({"width": "100px"});
    $(".STT5").css({"width": "60px"});
    $(".STT6").css({"width": "65px"});
    $(".TD_NGUYENGIA").css({"width": "80px"});
    $(".TD_THUTU").css({"width": "30px"});
    $(".TD_CHITIEU").css({"width": "220px"});
    $(".TEN_KH").css({"width": "100%"});
});

$(document).on('focus', '.TEN_KH', function () {
    $(this).closest('tr').addClass('highlight_row');
});

$(document).on('blur', '.TEN_KH', function () {
    $(this).closest('tr').removeClass('highlight_row');
});

$(function () {
    setCssStyle();

    $('#select-all').click(function (event) {
        $('.myCheckBox').each(function () {
            if (!this.disabled) {
                this.checked = $('#select-all').prop('checked');
                this.value = this.checked ? '1' : '0';
            }
        });
    });

    $('#select-all1').click(function (event) {
        $('.myCheckBox1').each(function () {
            if (!this.disabled) {
                this.checked = $('#select-all1').prop('checked');
                this.value = this.checked ? '1' : '0';
            }
        });
    });

    initTable1();
});

function addMonths(date, months) {
    date.setMonth(date.getMonth() + months);
    return date;
}

function setCssStyle() {
    $(".cssDate").datepicker({
        dateFormat: 'dd/mm/yy',
        changeMonth: true,
        changeYear: true,
        yearRange: "c-50:c+50",
        beforeShow: function (input, inst) {
            if ($(input).is(':disabled')) {
                return false;
            }
        },
        onSelect: function (dateText, inst) {
            $(this).css({'font-size': '13px', 'color': 'red'});
            var _freezeDateName = this.name;
            var _expireDateName = _freezeDateName.replace('D11', 'D13');
            var _freezeMonthName = _freezeDateName.replace('D11', 'D12');
            var _freezeMonthValue = parseInt($("input[name='" + _freezeMonthName + "']").val());
            var toDate = new Date(inst.selectedYear, inst.selectedMonth, inst.selectedDay);
            var oneDay = new addMonths(toDate, _freezeMonthValue);
            $("input[name='" + _expireDateName + "']").val($.datepicker.formatDate('dd/mm/yy', oneDay));
            $("input[name='" + _expireDateName + "']").css({'font-size': '13px', 'color': 'red'});
        }
    }).on('change', function (event) {
        event.preventDefault();
        $(this).css({'font-size': '13px', 'color': 'red'});
        var _freezeDateName = this.name;
        var _expireDateName = _freezeDateName.replace('D11', 'D13');
        var _freezeMonthName = _freezeDateName.replace('D11', 'D12');
        var _freezeMonthValue = parseInt($("input[name='" + _freezeMonthName + "']").val());
        let [day, month, year] = this.value.split('/');
        const toDate = new Date(+year, +month - 1, +day);
        var oneDay = new addMonths(toDate, _freezeMonthValue);
        $("input[name='" + _expireDateName + "']").val($.datepicker.formatDate('dd/mm/yy', oneDay));
        $("input[name='" + _expireDateName + "']").css({'font-size': '13px', 'color': 'red'});
    });

    $(".cssDate2").datepicker({
        dateFormat: 'dd/mm/yy',
        changeMonth: true,
        changeYear: true,
        yearRange: "c-50:c+50",
        beforeShow: function (input, inst) {
            if ($(input).is(':disabled')) {
                return false;
            }
        },
        onSelect: function (dateText, inst) {
            $(this).css({'font-size': '13px', 'color': 'red'});
        }
    });
}

var current_page = 1;
var records_per_page = 20;
var l = 0;

initPagination();

function initPagination() {
    var tableObj = document.getElementById("subTable");
    l = tableObj ? tableObj.rows.length : 0;
    
    // Cập nhật giá trị max của ô input theo tổng số trang thực tế
    var pageInput = document.getElementById("pageInput");
    if (pageInput) {
        pageInput.max = numPages();
    }

    // Gọi phân trang lần đầu tiên khi trang vừa load xong
    if (l > 0) {
        changePage(current_page);
    }
}

function prevPage() {
    if (current_page > 1) {
        current_page--;
        changePage(current_page);
    }
}

function nextPage() {
    if (current_page < numPages()) {
        current_page++;
        changePage(current_page);
    }
}

function goToPage() {
    var inputElement = document.getElementById("pageInput");
    var inputPage = parseInt(inputElement.value);
    
    if (inputPage >= 1 && inputPage <= numPages()) {
        current_page = inputPage;
        changePage(current_page);
    } else {
        alert("Trang không tồn tại (Vui lòng nhập từ 1 đến " + numPages() + ")");
        inputElement.value = current_page; // Reset lại ô input về trang hiện tại
    }
}

function changePage(page) {
    var btn_next = document.getElementById("btn_next");
    var btn_prev = document.getElementById("btn_prev");
    var listing_table = document.getElementById("subTable");
    var page_span = document.getElementById("page");
    var inputElement = document.getElementById("pageInput");
    
    if (!listing_table) return;

    if (page < 1) page = 1;
    if (page > numPages()) page = numPages();

    // Ẩn tất cả các dòng trước
    [...listing_table.getElementsByTagName('tr')].forEach((tr) => {
        tr.style.display = 'none';
    });

    // Hiển thị các dòng tiêu đề cố định (từ hàng 0 đến 3)
    for (var h = 0; h <= 3; h++) {
        if (listing_table.rows[h]) {
            listing_table.rows[h].style.display = "";
        }
    }

    // Hiển thị dữ liệu theo trang hiện tại
    for (var i = (page - 1) * records_per_page + 1; i < (page * records_per_page) + 1; i++) {
        if (listing_table.rows[i]) {
            listing_table.rows[i].style.display = "";
        }
    }

    if (page_span) {
        page_span.innerHTML = page + "/" + numPages();
    }
    
    // Đồng bộ giá trị trong ô input với trang hiện tại
    if (inputElement) {
        inputElement.value = page;
    }

    if (btn_prev) {
        btn_prev.style.visibility = (page === 1) ? "hidden" : "visible";
    }
    if (btn_next) {
        btn_next.style.visibility = (page === numPages()) ? "hidden" : "visible";
    }
}

function numPages() {
    return Math.max(1, Math.ceil((l - 1) / records_per_page));
}

function initTable() {
    var table = document.getElementById("subTable");
    var txtGetDataElem = document.getElementById('txtGetData');
    if (!table)
        return;
    var txtGetData = txtGetDataElem ? txtGetDataElem.value : "";
    var rowcount = table.rows.length;
    rowcount = rowcount > max_row ? rowcount : max_row;

    for (var i = 0; i < rowcount; i++) {
        try {
            if (txtGetData === "1") {
                for (let idNum of [8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 27, 28]) {
                    let elem = document.getElementById("D" + idNum + "_" + i);
                    if (elem)
                        elem.disabled = true;
                }
            }
            var D13Elem = $('#D13_' + i);
            var D13 = D13Elem.length ? D13Elem.find(":selected").val() : "";
            var savedValueElem = document.getElementById('D29_' + i);
            var savedValue = savedValueElem ? savedValueElem.value : "";

            $("#D29_" + i).empty();
            if (D13 === '1') {
                $("#D29_" + i).append("<option value='1' selected>Không cam kết</option>");
            } else {
                $("#D29_" + i).append("<option value='0'>--- Chọn ---</option>");
                $("#D29_" + i).append("<option value='2'>Thực hiện cam kết</option>");
                $("#D29_" + i).append("<option value='3'>Không thực hiện cam kết</option>");
            }
            $("#D29_" + i).val(savedValue);

            var D8 = document.getElementById('D8_' + i);
            var D3 = document.getElementById('D3_' + i);
            if (D8 && D3 && (D8.value === "0" || D8.value === null || D8.value === "")) {
                D8.value = D3.value;
            }

            var D12 = document.getElementById('D12_' + i);
            if (D12 && D12.value === "3") {
                document.getElementById("D13_" + i).disabled = false;
                document.getElementById("D29_" + i).disabled = false;
            } else if (D12) {
                document.getElementById("D13_" + i).disabled = true;
                document.getElementById("D29_" + i).disabled = true;
            }

            var D9 = document.getElementById('D9_' + i);
            var D5 = document.getElementById('D5_' + i);
            if (D9 && D5 && (D9.value === "0" || D9.value === null || D9.value === "")) {
                D9.value = D5.value;
            }

            var D27 = document.getElementById('D27_' + i);
            var D4 = document.getElementById('D4_' + i);
            if (D27 && D4 && (D27.value === "0" || D27.value === null || D27.value === "")) {
                D27.value = D4.value;
            }

            var D14 = document.getElementById('D14_' + i);
            var D15 = document.getElementById('D15_' + i);
            var D28 = document.getElementById('D28_' + i);
            if (D14 && D15 && D28 && D14.value === "0" && D15.value === "0" && D28.value === "0") {
                document.getElementById('D16_' + i).disabled = true;
            }

            var D11 = document.getElementById('D11_' + i);
            if (D11 && D11.value.length < 5) {
                document.getElementById('D12_' + i).disabled = true;
                document.getElementById('D13_' + i).disabled = true;
                document.getElementById('D29_' + i).disabled = true;
            }

            if (D3 && D8 && D14) {
                D14.value = parseInt(D3.value.replaceAll(',', '')) - parseInt(D8.value.replaceAll(',', ''));
            }
            if (D5 && D9 && D15) {
                D15.value = parseInt(D5.value.replaceAll(',', '')) - parseInt(D9.value.replaceAll(',', ''));
            }
            if (D4 && D27 && D28) {
                D28.value = parseInt(D4.value.replaceAll(',', '')) - parseInt(D27.value.replaceAll(',', ''));
            }

            var D19 = document.getElementById('D19_' + i);
            if (D19) {
                var valD19 = D19.value;
                var text = "";
                if (valD19 === "06" || valD19 === "07" || valD19 === "02") {
                    if (valD19 === "06")
                        text = "*Cho vay nước sạch và vệ sinh môi trường nông thôn*";
                    else if (valD19 === "07")
                        text = "*Cho vay hộ nghèo về nhà ở*";
                    else
                        text = "*Cho vay học sinh, sinh viên có hoàn cảnh khó khăn*";

                    var d10Elem = document.getElementById("D10_" + i);
                    if (d10Elem) {
                        d10Elem.disabled = true;
                        d10Elem.style.color = "#ddd";
                        d10Elem.placeholder = "";
                        d10Elem.title = "Món vay " + text + " không phải nhập phần này";
                    }
                } else if (txtGetData === "1") {
                    let d10 = document.getElementById("D10_" + i);
                    if (d10)
                        d10.disabled = true;
                } else {
                    let d10 = document.getElementById("D10_" + i);
                    if (d10)
                        d10.disabled = false;
                }
            }
        } catch (e) {
        }
    }
}

function onSelectChange_dnht1(value, index) {
    document.getElementById("D12_" + index).disabled = !(value.length > 5);
}

function onSelectChange_dnht2(value, index) {
    if (value === '3') {
        document.getElementById("D13_" + index).disabled = false;
    } else {
        document.getElementById("D13_" + index).disabled = true;
        document.getElementById("D29_" + index).disabled = true;
    }
}

function onSelectChange_dnht3(value, index) {
    document.getElementById("D29_" + index).disabled = (value === '0');
}

function onSelectChange_s1(value, index) {
    var selected = "selected";
    if (value === '1') {
        $("#D29_" + index).children().remove().end();
        $("#D29_" + index).prepend("<option value='1' " + selected + ">Không cam kết</option>");
    } else {
        $("#D29_" + index).children().remove().end();
        $("#D29_" + index).prepend("<option value='3' " + selected + ">Không thực hiện cam kết</option>");
        $("#D29_" + index).prepend("<option value='2' " + selected + ">Thực hiện cam kết</option>");
        $("#D29_" + index).prepend("<option value='0' " + selected + ">--- Chọn ---</option>");
    }
}

function onSelectChange_dnht4(value, index) {
    var d4 = document.getElementById('D4_' + index).value;
    var d5 = document.getElementById('D5_' + index).value;
    var d9 = document.getElementById('D9_' + index).value;
    var d3 = document.getElementById('D3_' + index).value;
    var d8 = document.getElementById('D8_' + index).value;

    var condition = (value !== d4) || (d5 !== d9) || (d3 !== d8);
    document.getElementById("D16_" + index).disabled = !condition;
}

function onSelectChange_dnht5(value, index) {
    var d5 = document.getElementById('D5_' + index).value;
    var d3 = document.getElementById('D3_' + index).value;
    var d8 = document.getElementById('D8_' + index).value;
    var d4 = document.getElementById('D4_' + index).value;
    var d27 = document.getElementById('D27_' + index).value;

    var condition = (value !== d5) || (d3 !== d8) || (d4 !== d27);
    document.getElementById("D16_" + index).disabled = !condition;
}

function onSelectChange_dnht6(value, index) {
    var d3 = document.getElementById('D3_' + index).value;
    var d5 = document.getElementById('D5_' + index).value;
    var d9 = document.getElementById('D9_' + index).value;
    var d4 = document.getElementById('D4_' + index).value;
    var d27 = document.getElementById('D27_' + index).value;

    var condition = (value !== d3) || (d5 !== d9) || (d4 !== d27);
    document.getElementById("D16_" + index).disabled = !condition;
}

function calc(id) {
    var row = id.parentNode.parentNode;
    var CT_D3 = row.cells[4].getElementsByTagName('input')[0].value;
    var CT_D9 = row.cells[9].getElementsByTagName('input')[0].value;
    var res = parseFloat(CT_D3.replace(/,/g, '')) - parseFloat(CT_D9.replace(/,/g, ''));
    row.cells[16].getElementsByTagName('input')[0].value = res.toLocaleString('en-US');
    row.cells[16].getElementsByTagName('input')[0].style.color = 'red';
}

function calc1(id) {
    var row = id.parentNode.parentNode;
    var CT_D4 = row.cells[5].getElementsByTagName('input')[0].value;
    var CT_D11 = row.cells[10].getElementsByTagName('input')[0].value;
    var res = parseFloat(CT_D4.replace(/,/g, '')) - parseFloat(CT_D11.replace(/,/g, ''));
    row.cells[17].getElementsByTagName('input')[0].value = res.toLocaleString('en-US');
    row.cells[17].getElementsByTagName('input')[0].style.color = 'red';
}

function calc2(id) {
    var row = id.parentNode.parentNode;
    var CT_D5 = row.cells[6].getElementsByTagName('input')[0].value;
    var CT_D12 = row.cells[11].getElementsByTagName('input')[0].value;
    var res = parseFloat(CT_D5.replace(/,/g, '')) - parseFloat(CT_D12.replace(/,/g, ''));
    row.cells[18].getElementsByTagName('input')[0].value = res.toLocaleString('en-US');
    row.cells[18].getElementsByTagName('input')[0].style.color = 'red';
}

function initTable1() {
    nextPage();
    prevPage();
}

window.onload = function () {
    changePage(current_page);
};