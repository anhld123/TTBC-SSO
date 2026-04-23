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