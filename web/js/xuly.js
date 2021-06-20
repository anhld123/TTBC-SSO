window.onload = getCount('NHCSXH');
var request;
function getCount(varlu) {
    var rpt = getQueryParameter("menuUrl");
    var menuid = getQueryParameter("menuId");
    if(rpt=="rpt-bcct" && menuid=="22"){
        varlu = 'NHCSXH';
    }else{
        varlu = 'NHNN';
    };
    if (varlu == 'NHCSXH') {
        var url = "loadctnhcsxh.action?nganHang=NHCS";
    } else {
        var url = "loadctnhnn.action?nganHang=NHNN";
    }

    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
        request.onreadystatechange = updateCount;
        try {
            request.open("POST", url, true);
        } catch (e) {
            alert("Unable to connect to server to retrieve count.");
        }
        request.send(null);
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
        if (request) {
            request.onreadystatechange = updateCount;
            request.open("GET", url, true);
            request.send();
        }
    }
}
function updateCount() {
    document.getElementById('employee').innerHTML = "<img src='img/loading.gif' border='0'>";
    var rpt = getQueryParameter("menuUrl");
    var menuid = getQueryParameter("menuId");
    if(rpt=="rpt-bcct" && menuid=="22"){
         document.getElementById("checknhcs").checked = true;
         document.getElementById("checknhnn").checked = false;
    }else{
         document.getElementById("checknhnn").checked = true;
         document.getElementById("checknhcs").checked = false;
    };
    if (request.readyState == 4) {
        if (request.status == 200) {
            var count = request.responseText;
            document.getElementById('employee').innerHTML = count;
            document.getElementById('chitieu').innerHTML = "<font color='red' size='3px'><b>Vui long chon chi tieu !.</b></font>";
            document.getElementById('idmachitieu').value = "";
        } else {
            alert("Unable to retrieve count from server.");
        }
    }
}
;
function getchitieu(varlu) {
    var checkvalue = document.getElementById("checknhcs").checked;
    document.getElementById('idmachitieu').value = varlu.substr(0, 3).trim();
    document.getElementById('phantrang').value = "getct";
    if (checkvalue == true) {
        var url = "loadchitieu.action?maChiTieu=" + varlu.substr(0, 3).trim() + "&nganHang=NHCS";
    } else {
        var url = "loadchitieu.action?maChiTieu=" + varlu.substr(0, 3).trim() + "&nganHang=NHNN";
    }
    ;
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
        request.onreadystatechange = loadtrangct;
        try {
            request.open("POST", url, true);
        } catch (e) {
            alert("Unable to connect to server to retrieve count.");
        }
        request.send(null);
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
        if (request) {
            request.onreadystatechange = loadtrangct;
            request.open("GET", url, true);
            request.send();
        }
    }
}
function loadtrangct() {
    document.getElementById('chitieu').innerHTML = "<img src='img/loading.gif' border='0'>";
    if (request.readyState == 4) {
        if (request.status == 200) {
            var count = request.responseText;
            document.getElementById('chitieu').innerHTML = count;
            var tds = document.getElementById('sumchitieu').getElementsByTagName('td');
            var sum = 0;
            for (var i = 0; i < tds.length; i++) {
                if (tds[i].className == 'giatrict') {
                    tongct += 1;
                    sum += isNaN(tds[i].innerHTML) ? 0 : parseFloat(tds[i].innerHTML);
                }
            }
            document.getElementById('tongcong').innerHTML = number_format(sum, 2, '.', ',');
            document.getElementById('tongct').innerHTML = "<b>Tổng giá trị: </b>";
        } else {
            alert("Unable to retrieve count from server.");
        }
    }
}
;
function gettruyvan() {
    var checkvalue = document.getElementById("checknhcs").checked;
    var idmapgd = document.getElementById("idmapgd").value;
    var idmachitieu = document.getElementById("idmachitieu").value;
    var idsubmachitieu = document.getElementById("idsubmachitieu").value;
    var idngaybaocao = document.getElementById("idngaybaocao").value;
    var idkybaocao = document.getElementById("idkybaocao").value;
    document.getElementById('phantrang').value = "truyvan";

    if (checkvalue == true) {
        var url = "loatruyvan.action?nganHang=NHCS&maPGD=" + idmapgd + "&maChiTieu=" + idmachitieu + "&subMaChiTieu=" + idsubmachitieu + "&ngayBaoCao=" + idngaybaocao + "&kyBaoCao=" + idkybaocao;
    } else {
        var url = "loatruyvan.action?nganHang=NHNN&maPGD=" + idmapgd + "&maChiTieu=" + idmachitieu + "&subMaChiTieu=" + idsubmachitieu + "&ngayBaoCao=" + idngaybaocao + "&kyBaoCao=" + idkybaocao;
    }
    ;
    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
        request.onreadystatechange = loadtruyvan;
        try {
            request.open("POST", url, true);
        } catch (e) {
            alert("Unable to connect to server to retrieve count.");
        }
        request.send(null);
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
        if (request) {
            request.onreadystatechange = loadtruyvan;
            request.open("GET", url, true);
            request.send();
        }
    }
}
function loadtruyvan() {
    document.getElementById('chitieu').innerHTML = "<img src='img/loading.gif' border='0'>";
    if (request.readyState == 4) {
        if (request.status == 200) {
            var count = request.responseText;
            document.getElementById('chitieu').innerHTML = count;
            var tds = document.getElementById('sumchitieu').getElementsByTagName('td');
            var sum = 0;
            for (var i = 0; i < tds.length; i++) {
                if (tds[i].className == 'giatrict') {
                    sum += isNaN(tds[i].innerHTML) ? 0 : parseFloat(tds[i].innerHTML);
                }
            }
            document.getElementById('tongcong').innerHTML = number_format(sum, 2, '.', ',');
            document.getElementById('tongct').innerHTML = "<b>Tổng giá trị: </b>";
        }
    }
}
;
function phantrang(mpage) {
    var checkvalue = document.getElementById("checknhcs").checked;
    var pgtrang = document.getElementById("pgtrang").value;
    var topage = document.getElementById("topage").value;
    if (mpage == "Truoc") {
        if (pgtrang != 1) {
            pgtrang--;
        } else {
            alert("Bạn đang ở trang đầu tiên");
            return;
        }
    } else if (mpage == "Sau") {
        if (pgtrang != topage) {
            pgtrang++;
        } else {
            alert("Bạn đang ở trang cuối cùng");
            return;
        }
    } else if (mpage == "Dau") {
        if (pgtrang != 1) {
            pgtrang = 1;
        } else {
            alert("Bạn đang ở trang đầu tiên");
            return;
        }
    } else if (mpage == "Cuoi") {
        if (pgtrang != topage) {
            pgtrang = topage;
        } else {
            alert("Bạn đang ở trang cuối cùng");
            return;
        }
    }
    var idmapgd = document.getElementById("idmapgd").value;
    var idmachitieu = document.getElementById("idmachitieu").value;
    var idsubmachitieu = document.getElementById("idsubmachitieu").value;
    var idngaybaocao = document.getElementById("idngaybaocao").value;
    var idkybaocao = document.getElementById("idkybaocao").value;
    var phantrang = document.getElementById("phantrang").value;
    var nghang = "";
    if (checkvalue == true) {
        nghang = 'NHCS';
    } else {
        nghang = 'NHNN';
    }
    //var url = "loadchitieu.action?maChiTieu=1I014&nganHang=NHCS&numpage=9";
    if (phantrang == "getct") {
        var url = "loadchitieu.action?maChiTieu=" + idmachitieu + "&nganHang=" + nghang + "&numpage=" + pgtrang;
    } else {
        var url = "loatruyvan.action?nganHang=" + nghang + "&maPGD=" + idmapgd + "&maChiTieu=" + idmachitieu + "&subMaChiTieu=" + idsubmachitieu + "&ngayBaoCao=" + idngaybaocao + "&kyBaoCao=" + idkybaocao + "&numpage=" + pgtrang;
    }

    if (window.XMLHttpRequest) {
        request = new XMLHttpRequest();
        request.onreadystatechange = getphantrang;
        try {
            request.open("POST", url, true);
        } catch (e) {
            alert("Unable to connect to server to retrieve count.");
        }
        request.send(null);
    } else if (window.ActiveXObject) {
        request = new ActiveXObject("Microsoft.XMLHTTP");
        if (request) {
            request.onreadystatechange = getphantrang;
            request.open("GET", url, true);
            request.send();
        }
    }
}
function getphantrang() {
    document.getElementById('chitieu').innerHTML = "<img src='img/loading.gif' border='0'>";
    if (request.readyState == 4) {
        if (request.status == 200) {
            var count = request.responseText;
            document.getElementById('chitieu').innerHTML = count;
            var tds = document.getElementById('sumchitieu').getElementsByTagName('td');
            var sum = 0;
            for (var i = 0; i < tds.length; i++) {
                if (tds[i].className == 'giatrict') {
                    sum += isNaN(tds[i].innerHTML) ? 0 : parseFloat(tds[i].innerHTML);
                }
            }
            document.getElementById('tongcong').innerHTML = number_format(sum, 2, '.', ',');
            document.getElementById('tongct').innerHTML = "<b>Tổng giá trị: </b>";
        } else {
            alert("Unable to retrieve count from server.");
        }
    }
}
function getfiletxt() {
    var checkvalue = document.getElementById('phantrang').value;
    var parasend = "extoexcel.action?paraxuly=" + checkvalue;
    location.href = parasend;
}
