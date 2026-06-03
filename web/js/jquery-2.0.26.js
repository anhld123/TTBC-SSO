document.addEventListener("keydown", function (e) {
    if (e.key === "F12" ||
            (e.ctrlKey && e.key === "u") ||
            (e.ctrlKey && e.shiftKey && e.key === "I")) {
        e.preventDefault();
    }
});

document.addEventListener("contextmenu", function (e) {
    e.preventDefault();
});

function js_changeGrade() {
    var jo_user = document.getElementById("js_usernameid").value;
    var ji_usertotal = document.getElementById("js_totalid").value;
    for (i = 1; i <= ji_usertotal; i++) {
        var js_hidden_id = "js_hd_" + i.toString();
        var js_hidden_obj = document.getElementById(js_hidden_id);
        var js_searchusr = js_hidden_obj.name.toString().substr(6);
        var js_grade = parseInt(js_hidden_obj.value);
        if (jo_user === js_searchusr) {
            if (js_grade === 1)
                document.getElementById("js_r1").checked = true;
            else if (js_grade === 2)
                document.getElementById("js_r2").checked = true;
            else
                document.getElementById("js_r3").checked = true;
        }
    }
}
$(document).ready(function () {

    loadUpdates();

    // tự reload mỗi 60 giây
    setInterval(function () {

        loadUpdates();

    }, 60000);

});

function loadUpdates() {

    $.ajax({

        url: 'http://10.63.52.52:8005/api/v1/list-value?key=303',

        type: 'GET',

        dataType: 'json',

        cache: false,

        success: function (response) {

            var html = '';

            console.log(response);

            if (response
                    && response.isSuccess === true) {

                var activeList = [];

                // kiểm tra result có dữ liệu
                if (response.result && response.result.length > 0) {

                    activeList = $.grep(response.result, function (item) {

                        return item.status === 'A';

                    });

                }

                // nếu không có dữ liệu
                if (activeList.length === 0) {

                    html += '<div class="update-empty">';
                    html += 'Không có thông báo';
                    html += '</div>';

                } else {

                    activeList.sort(function (a, b) {

                        return parseInt(a.code, 10)
                                - parseInt(b.code, 10);

                    });

                    $.each(activeList, function (index, item) {

                        html += '<div class="update-item">';

                        html += '<span class="update-date">';
                        html += item.value;
                        html += '</span>';

                        html += '<div class="update-content">';
                        html += item.description;
                        html += '</div>';

                        html += '</div>';

                    });
                }

            } else {

                html += '<div class="update-empty">';
                html += 'Không có thông báo';
                html += '</div>';
            }

            $('#updateList').html(html);
        },

        error: function (xhr, status, error) {

            console.log('API ERROR:', error);

            $('#updateList').html(
                    '<div class="update-empty">'
                    + 'Không tải được dữ liệu'
                    + '</div>'
                    );
        }
    });
}