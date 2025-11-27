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
    $('.number2').number(true, 1);
    $('input.number3').css({"text-align": "right"});
    $('.number3').number(true, 4);
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
    $(".D99").css({"text-align": "center", "color": "#000", "font-style": "italic", "font-size": "xx-small"});
    initTable();
});
function initTable()
{
    var table = document.getElementById("subTable");
    var rowcount = table.rows.length;
    rowcount = rowcount > max_row ? rowcount : max_row;
    for (var i = 0; i < rowcount; i++)
    {
        try {
            var D19 = document.getElementById("D19_" + i).value;
            document.getElementById("D18_" + i).disabled = true;
            if (D19 === "1")
            {
                document.getElementById("D19_" + i).checked = true;
                document.getElementById("D18_" + i).disabled = false;
            } else {
                document.getElementById("D18_" + i).disabled = true;
                document.getElementById("D18_" + i).style.background = "#E5E5E5";
            }
        } catch (e) {
        }
    }

}
var current_page = 1;
var records_per_page = 50;
var $rows = $('#subTable tbody tr');
var searchResults = []; // mảng các dòng sau khi search

function changePage(page) {
    var btn_next = document.getElementById("btn_next");
    var btn_prev = document.getElementById("btn_prev");
    var listing_table = document.getElementById("subTable");
    var page_span = document.getElementById("page");

    if (page < 1)
        page = 1;
    if (page > numPages())
        page = numPages();

    // Ẩn tất cả
    $rows.hide();

    // Hiển thị 3 dòng đầu
    $rows.slice(0, 3).show();

    // Hiển thị các dòng của trang hiện tại
    var start = (page - 1) * records_per_page;
    var end = start + records_per_page;
    for (var i = start; i < end; i++) {
        if (searchResults[i]) {
            $(searchResults[i]).show();
        }
    }

    page_span.innerHTML = page + "/" + numPages();

    btn_prev.style.visibility = (page === 1) ? "hidden" : "visible";
    btn_next.style.visibility = (page === numPages()) ? "hidden" : "visible";
}

function numPages() {
    return Math.ceil(searchResults.length / records_per_page);
}

// các nút
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
    var inputPage = document.getElementById("pageInput").value;
    if (inputPage >= 1 && inputPage <= numPages()) {
        current_page = inputPage;
        changePage(current_page);
    } else {
        alert("Trang không tồn tại");
    }
}

$(function () {
    $('#search').on('keyup', function () {
        var val = $(this).val().toLowerCase();

        // Lọc các dòng từ thứ 4 trở đi
        searchResults = [];
        $rows.slice(3).each(function () {
            var col2 = $(this).find('td:eq(1)').text().toLowerCase(); // cột 2 (index 1)
            var col4 = $(this).find('td:eq(3)').text().toLowerCase(); // cột 4 (index 3)

            if (col2.includes(val) || col4.includes(val)) {
                searchResults.push(this);
            }
        });

        current_page = 1;
        changePage(current_page);
    });

    // Khởi tạo searchResults khi chưa tìm kiếm (hiển thị tất cả các dòng từ thứ 4)
    searchResults = $rows.slice(3).toArray();
    changePage(current_page);
});



window.onload = function () {
    changePage(current_page);
};

function initTable1()
{
    nextPage();
    prevPage();
}
initTable1();


$(function () {
    $('#select-all1').click(function () {
        const isChecked = $('#select-all1').prop('checked');
        // Lặp qua các checkbox và cập nhật trạng thái
        $('.myCheckBox1').each(function (index) {
            if (!this.disabled) {
                this.checked = isChecked;
                this.value = isChecked ? '1' : '0';
//                            onSelectChange_dnht1(this.value, index);
            }
        });
    });
});
$(function () {
    $('#select-all').click(function () {
        if (this.checked) {
            let count = 0;
            $('.myCheckBox').each(function () {
                if (count < 100) {
                    this.checked = true;
                    this.value = '1';
                    count++;
                } else {
                    this.checked = false;
                    this.value = '0';
                }
            });
            if ($('.myCheckBox').length > 100) {
                alert("Bạn chỉ được chọn tối đa 100 mục!");
            }
        } else {
            $('.myCheckBox').prop('checked', false).val('0');
        }
    });

    // Nếu user tick thủ công
    $('.myCheckBox').on('change', function () {
        let selected = $('.myCheckBox:checked').length;
        if (selected > 100) {
            this.checked = false;
            this.value = '0';
            alert("Chỉ được chọn tối đa 100 mục!");
        }
    });
});
//
//function check(index) {
//    // D12 là text hiển thị
//    let D12_text = document.getElementById("D12_" + index).innerText;
//    // D18 là input người nhập
//    let D18_text = document.getElementById("D18_" + index).value;
//
//    // jQuery Number dùng dấu chấm --> chỉ cần bỏ %
//    let D12 = parseFloat(D12_text.replace('%', ''));
//    let D18 = parseFloat(D18_text.replace('%', ''));
//
//    if (isNaN(D18))
//        return; // tránh lỗi
//
//    if (D18 > D12) {
//        alert("Lãi giảm không thể cao hơn lãi ban đầu!");
//        document.getElementById("D18_" + index).style.background = "red";
//        document.getElementById("D18_" + index).value = "0.0000";
//        return;
//    }
//
//    if (D18 < 0) {
//        alert("Không được phép nhập số âm!");
//        document.getElementById("D18_" + index).style.background = "red";
//        document.getElementById("D18_" + index).value = "0.0000";
//        return;
//    }
//
//
//    document.getElementById("D18_" + index).style.background = "white";
//}
function onSelectChange(value, index) {
    if (value === '1')
    {
        let D7_text = document.getElementById("D7_" + index).value;
        console.log("d7= " + D7_text);
        const allowed = ["032SC1", "032MC1", "032LC1", "033SC1", "033MC1", "033LC1", "034SC1", "034MC1", "034LC1", "252SC1", "252MC1"
        ];

        if (allowed.includes(D7_text)) {
            document.getElementById("D18_" + index).disabled = false;
            document.getElementById("D18_" + index).value = "3.744";
        } else if (D7_text === "161SC1" || D7_text === "161MC1" || D7_text === "161LC1") {
            document.getElementById("D18_" + index).disabled = false;
            document.getElementById("D18_" + index).value = "3.12";
        } else {
            document.getElementById("D18_" + index).disabled = false;
            document.getElementById("D18_" + index).style.background = "#ffcdbb";
            document.getElementById("D18_" + index).value = "0.0000";
        }
    } else
    {
        document.getElementById("D18_" + index).disabled = true;
        document.getElementById("D18_" + index).value = "";
        document.getElementById("D18_" + index).style.background = "#E5E5E5";
        document.getElementById("D18_" + index).value = "0.0000";
    }
}
function check(index) {

    let D7 = document.getElementById("D7_" + index).value;
    let D18_input = document.getElementById("D18_" + index);
    let val = parseFloat(D18_input.value);

    const allowed = ["032SC1", "032MC1", "032LC1", "033SC1", "033MC1", "033LC1",
        "034SC1", "034MC1", "034LC1", "252SC1", "252MC1"];

    if (allowed.includes(D7)) {
        if (val !== 3.744) {
            alert("Mã " + D7 + " chỉ được phép nhập 3.744!");
            D18_input.value = "3.744";
        }
        return;
    }
    if (D7 === "161SC1" || D7 === "161MC1" || D7 === "161LC1") {
        if (val !== 3.12) {
            alert("Mã " + D7 + " chỉ được phép nhập 3.12!");
            D18_input.value = "3.12";
        }
        return;
    }
    let D12_text = document.getElementById("D12_" + index).innerText;
    let D12 = parseFloat(D12_text.replace('%', ''));

    if (isNaN(val))
        return;

    if (val > D12) {
        alert("Lãi giảm không thể cao hơn lãi ban đầu!");
        D18_input.style.background = "red";
        D18_input.value = "0.0000";
        return;
    }

    if (val < 0) {
        alert("Không được phép nhập số âm!");
        D18_input.style.background = "red";
        D18_input.value = "0.0000";
        return;
    }

    D18_input.style.background = "white";
}

