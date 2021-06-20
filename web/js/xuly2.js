// Hàm lấy danh sách chương trình, loại hình đào tạo,... khi chọn báo cáo
            var request;
            function getLoaddata(machitieu) {
                if(machitieu != -1){
                    var e = document.getElementById("genview");
                    document.getElementById("collect").value = "Loadcom";
                    e.innerHTML = "Xem báo cáo";
                    e.href = "javascript:genandview();";
                    var url = "Loaddata.action?idReport=" + escape(machitieu);
                    if (window.XMLHttpRequest) {
                        request = new XMLHttpRequest();
                        request.onreadystatechange = viewdata;
                        try {
                            request.open("POST", url, true);
                        } catch (e) {
                            alert("Không có kết quả trả về từ Server.");
                        }
                        request.send(null);
                    } else if (window.ActiveXObject) {
                        request = new ActiveXObject("Microsoft.XMLHTTP");
                        if (request) {
                            request.onreadystatechange = viewdata;
                            request.open("GET", url, true);
                            request.send();
                        }
                    }
                }else{
                    document.getElementById('loaddata').innerHTML="Vui lòng chọn báo cáo";
                }
            }
            function viewdata() {
                document.getElementById('loaddata').innerHTML = "<img src='img/loading.gif' border='0'>";
                if (request.readyState == 4) {
                    if (request.status == 200) {
                        var count = request.responseText;
                        document.getElementById('loaddata').innerHTML = count;
                    } else {
                        alert("Không có kết quả trả về từ Server.");
                    }
                }
            }
            function genandview() {
                
                var mabc = document.getElementById("idbaocao").name + "=" + document.getElementById("idbaocao").value;
                var mapgd = document.getElementById("idmapgd").name + "=" + document.getElementById("idmapgd").value;
                var ngaybc = document.getElementById("idngbaocao").name + "=" + document.getElementById("idngbaocao").value;
                var checkngbc = document.getElementById("idngbaocao").value;
                if(checkngbc=="" || checkngbc==null){
                    document.getElementById("idngbaocao").focus();
                    alert("Vui lòng chọn ngày báo cáo.");
                    return;
                }
                var e = document.getElementById("genview");
                e.href = "javascript:void(0);";
                e.innerHTML = "Đang thực hiện...";
                // Lấy các giá trị của List 1 và List 2
                var arr = new Array();
                var x = document.getElementById("para1");
                
                if (x == null) {
                    getLoaddata(document.getElementById('idbaocao').value);
                }
                for (i = 0; i < x.length; i++) {
                    if (x[i].checked) {
                        arr.push(x[i].name + "=" + x[i].value);
                    }
                }
                
                var paraurl1 = arr.join("&");
                var parr = new Array();
                var y = document.getElementById("para2");
                for (i = 0; i < y.length; i++) {
                    if (y[i].checked) {
                        parr.push(y[i].name + "=" + y[i].value);
                    }
                }
                var paraurl2 = parr.join("&");
                // Thực hiện gửi dữ liệu về Action để thực hiện câu QR và gọi file View

                var xmlhttp;
                if (window.XMLHttpRequest)
                {// code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp = new XMLHttpRequest();
                }
                else
                {// code for IE6, IE5
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
               
                xmlhttp.onreadystatechange = function()
                {
                    
                    if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
                    {
                        e.innerHTML = "Quay trở lại";
                        e.href = "javascript:genandview();";
                        document.getElementById("loaddata").innerHTML = xmlhttp.responseText;
                    }
                }
                xmlhttp.open("POST", "gendataview.action", true);
                xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                var purl = "";
                if (mabc != "") {
                    purl = purl + "&" + mabc;
                }
                if (mapgd != "") {
                    purl = purl + "&" + mapgd;
                }
                if (ngaybc != "") {
                    purl = purl + "&" + ngaybc;
                }
                if (paraurl1 != "") {
                    purl = purl + "&" + paraurl1;
                }
                if (paraurl2 != "") {
                    purl = purl + "&" + paraurl2;
                }
                document.getElementById("collect").value = purl.substr(1);
                xmlhttp.send(purl.substr(1));
            }

            function download() {
                var lurl = document.getElementById("collect").value;
                if (lurl == "Loadcom") {
                    var mabc = document.getElementById("idbaocao").name + "=" + document.getElementById("idbaocao").value;
                    var mapgd = document.getElementById("idmapgd").name + "=" + document.getElementById("idmapgd").value;
                    var ngaybc = document.getElementById("idngbaocao").name + "=" + document.getElementById("idngbaocao").value;
                    var e = document.getElementById("collect");
                    e.href = "javascript:void(0);";
                    e.innerHTML = "Đang thực hiện...";
                    // Lấy các giá trị của List 1 và List 2
                    var arr = new Array();
                    var x = document.getElementById("para1");
                    if (x == null) {
                        getLoaddata(document.getElementById('idbaocao').value);
                    }
                    for (i = 0; i < x.length; i++) {
                        if (x[i].checked) {
                            arr.push(x[i].name + "=" + x[i].value);
                        }
                    }
                    var paraurl1 = arr.join("&");

                    var parr = new Array();
                    var y = document.getElementById("para2");
                    for (i = 0; i < y.length; i++) {
                        if (y[i].checked) {
                            parr.push(y[i].name + "=" + y[i].value);
                        }
                    }
                    var paraurl2 = parr.join("&");
                    var purl = "";
                    if (mabc != "") {
                        purl = purl + "&" + mabc;
                    }
                    if (mapgd != "") {
                        purl = purl + "&" + mapgd;
                    }
                    if (ngaybc != "") {
                        purl = purl + "&" + ngaybc;
                    }
                    if (paraurl1 != "") {
                        purl = purl + "&" + paraurl1;
                    }
                    if (paraurl2 != "") {
                        purl = purl + "&" + paraurl2;
                    }
                    document.getElementById("collect").value = purl.substr(1);
                }
                var lurl = document.getElementById("collect").value;
                location.href = "DownExcel.action?" + lurl;
            }
       
