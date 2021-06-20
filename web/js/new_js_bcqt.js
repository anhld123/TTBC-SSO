// Hàm định độ dộng cột theo tên class
var num = 0;
var clname = "";
$("[name=head]").each(function () {
    num = $(this).attr("class").match(/\d+$/);
    clname = "." + $(this).attr("class");
    $(clname).css({
        "width": num + "%"
    });
});
// Hàm định dạng số
$(function () {
    $('.number').number(true, 0);
    $('.number2').number(true, 2);
});
// Xử lý trường hợp khi nhận được con trỏ
function clickme(idx) {
    var tbl = document.getElementById("tblmain");
    tbl.rows[idx].style.backgroundColor = "#ebffff";
}
function outme(idx) {
    var tbl = document.getElementById("tblmain");
    tbl.rows[idx].style.backgroundColor = "#ffffff";
}