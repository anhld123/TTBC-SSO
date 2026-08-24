<%-- 
    Document   : viewcontent anhld
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tra cứu thông tin</title>
        <!--<script type="text/javascript" src="DMChitieu/js/jquery-1.4.4.min.js"></script>-->
        <style type="text/css">
            body { font-family: tahoma; font-size: 13px; margin: 0; padding: 0; }
            .head { font-size: 12px; font-weight: bold; height: 25px; padding-left: 10px; }
            .gentable { background-color: #DDFFDD; width: 100%; border: 0px; }
            #listten { border: 0px; background: transparent; width: 90px; color: #000; }
            #customers { font-family: "Trebuchet MS", Arial, Helvetica, sans-serif; border-collapse: collapse; width: 100%; }
            #customers td, #customers th { border: 1px solid #ddd; padding: 8px; }
            #customers tr:nth-child(even) { background-color: #f2f2f2; }
            #customers tr:hover { background-color: #ddd; }
            #customers th { padding-top: 12px; padding-bottom: 12px; text-align: left; background-color: #4CAF50; color: white; }
            .pg-normal { color: #fff; font-weight: normal; text-decoration: none; cursor: pointer; }
            .pg-selected { color: red; font-weight: bold; text-decoration: underline; cursor: pointer; }
            .multi-person-wrapper { width: 100%; padding: 8px 0 10px 0; }
            .multi-person-title { font-weight: bold; color: #333; margin-bottom: 5px; }
            .multi-person-box { width: 600px; min-height: 42px; border: 1px solid #bdbdbd; background-color: #fff; padding: 5px 7px; box-sizing: border-box; cursor: text; line-height: 25px; }
            .multi-person-box:hover { border-color: #999; }
            .multi-person-box:focus-within { border-color: #4CAF50; box-shadow: 0 0 3px rgba(76, 175, 80, 0.35); }
            .person-tag { display: inline-block; background-color: #e8f5e9; border: 1px solid #a5d6a7; color: #2e7d32; border-radius: 14px; padding: 2px 8px; margin: 2px 4px 2px 0; font-size: 12px; line-height: 20px; white-space: nowrap; }
            .person-tag:hover { background-color: #c8e6c9; }
            .remove-person { margin-left: 7px; color: #d32f2f; font-weight: bold; cursor: pointer; font-size: 14px; }
            .remove-person:hover { color: #b71c1c; }
            .person-input { border: 0px; outline: none; background: transparent; min-width: 250px; height: 27px; padding: 2px 4px; font-family: tahoma; font-size: 13px; }
            .person-input::-webkit-input-placeholder { color: #999; }
            .person-info { width: 600px; margin-top: 5px; color: #777; font-size: 11px; }
            .person-count { color: #2e7d32; font-weight: bold; }
            .clear-person { float: right; color: #d32f2f; cursor: pointer; font-size: 11px; text-decoration: none; }
            .clear-person:hover { text-decoration: underline; }
            .person-note { margin-top: 4px; color: #888; font-size: 11px; }
            .btn-tra-cuu { cursor: pointer; }
        </style>
        <script type="text/javascript">
            function Pager(tableName, itemsPerPage) {
                this.tableName = tableName;
                this.itemsPerPage = itemsPerPage;
                this.currentPage = 1;
                this.pages = 0;
                this.inited = false;
                this.showRecords = function (from, to) {
                    var rows = document.getElementById(tableName).rows;
                    for (var i = 1; i < rows.length; i++) {
                        if (i < from || i > to) {
                            rows[i].style.display = 'none';
                        } else {
                            rows[i].style.display = '';
                        }
                    }
                };
                this.showPage = function (pageNumber) {
                    if (!this.inited) {
                        alert("not inited");
                        return;
                    }
                    var oldPageAnchor = document.getElementById('pg' + this.currentPage);
                    if (oldPageAnchor != null) {
                        oldPageAnchor.className = 'pg-normal';
                    }
                    this.currentPage = pageNumber;
                    var newPageAnchor = document.getElementById('pg' + this.currentPage);
                    if (newPageAnchor != null) {
                        newPageAnchor.className = 'pg-selected';
                    }
                    var from = (pageNumber - 1) * this.itemsPerPage + 1;
                    var to = from + this.itemsPerPage - 1;
                    this.showRecords(from, to);
                };
                this.prev = function () {
                    if (this.currentPage > 1) {
                        this.showPage(this.currentPage - 1);
                    }
                };
                this.next = function () {
                    if (this.currentPage < this.pages) {
                        this.showPage(this.currentPage + 1);
                    }
                };
                this.init = function () {
                    var rows = document.getElementById(tableName).rows;
                    var records = rows.length - 1;
                    this.pages = Math.ceil(records / this.itemsPerPage);
                    this.inited = true;
                };
                this.showPageNav = function (pagerName, positionId) {
                    if (!this.inited) {
                        alert("not inited");
                        return;
                    }
                    var element = document.getElementById(positionId);
                    var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> &#171 Trước </span> | ';
                    for (var page = 1; page <= this.pages; page++) {
                        pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> | ';
                    }
                    pagerHtml += '<span onclick="' + pagerName + '.next();" class="pg-normal"> Sau &#187;</span>';
                    element.innerHTML = pagerHtml;
                };
            }
        </script>
    </head>
    <body>
        <form name="frmmain" id="frmmain">
            <table border="1" width="100%">
                <tr style="background-color: #F4F3F2;">
                    <td colspan="2">
                        <span class="head">TRA CỨU THÔNG TIN:</span>
                        <select name="loaitc" id="loaitc" onchange="fn_get_dk_info(this);">
                            <option value="all">00.Chọn thông tin cần tra cứu </option>
                            <s:iterator value="lsinfo">
                                <option value="<s:property value="GIATRI"/>"><s:property value="HIENTHI"/></option>
                            </s:iterator>
                        </select>
                        &nbsp;
                        <input type="button" value="Ẩn/Hiện điều kiện" id="btnhide" onclick="funcandk()" disabled="true"/>
                        <input type="button" value="Thực hiện" id="btnthuchien" class="btn-tra-cuu" onclick="funsubmitdata()" disabled="true"/>
                        <span style="display:none;" id="chkKH">
                            <input type="checkbox" id="chkexcel" name="chkexcel"/>
                            <label for="chkexcel">Kiểm tra theo file excel đã upload</label>
                        </span>
                    </td>
                </tr>
                <tr id="dieukien">
                    <td>
                        <div id="viewdk">&nbsp;</div>
                    </td>
                </tr>
                <tr id="viewdata">
                    <td>
                        <div id="viewcontent" style="width: 200vh; overflow-x: scroll;">&nbsp;</div>
                    </td>
                </tr>
            </table>
        </form>
        <script type="text/javascript">
            var personNames = [];
            var MAX_PERSON = 10;

            $(document).ready(function () {
                $("#dieukien").hide();
                $("#viewdata").hide();
            });

            function fn_get_dk_info(val) {
                var btnarr = ["btnhide", "btnthuchien"];
                if (val.value === "all") {
                    for (var i = 0; i < btnarr.length; i++) {
                        document.getElementById(btnarr[i]).setAttribute('disabled', 'disabled');
                    }
                    $("#dieukien").hide();
                    $("#viewdata").hide();
                    $("#viewdk").html("&nbsp;");
                    $("#viewcontent").html("&nbsp;");
                    clearPersons(false);
                    return;
                }
                for (var i = 0; i < btnarr.length; i++) {
                    document.getElementById(btnarr[i]).removeAttribute("disabled");
                }
                $("#dieukien").show();
                $("#viewdata").show();
                $("#viewcontent").html("&nbsp;");
                clearPersons(false);
                var url = "GenTable.action?loaitc=" + encodeURIComponent(val.value);
                $.post(url, function (data) {
                    if (val.value === "DTTN" || val.value === "KHUQ" || val.value === "KHVV") {
                        renderMultiPersonForm();
                    } else {
                        $("#viewdk").html(data);
                    }
                });
                if (val.value === "KH") {
                    $("#chkKH").show();
                } else {
                    $("#chkKH").hide();
                }
            }

            function renderMultiPersonForm() {
                var html = "";
                html += '<div class="multi-person-wrapper">';
                var loaitc = $("#loaitc").val();
                if (loaitc === "DTTN") {
                    html += '<div class="multi-person-title">Thông tin tra cứu</div>';
                } else if (loaitc === "KHVV") {
                    html += '<div class="multi-person-title">Nhập CMT/CCCD</div>';
                } else {
                    html += '<div class="multi-person-title">Nhập CMT/CCCD người uỷ quyền</div>';
                }
                html += '<div id="multiPersonBox" class="multi-person-box">';
                html += '<input type="text" id="personInput" class="person-input" placeholder="Nhập thông tin rồi nhấn Enter...">';
                html += '</div>';
                html += '<div class="person-info">';
                html += 'Đã nhập: <span id="personCount" class="person-count">0</span> người';
                html += '<span class="clear-person" onclick="clearPersons(true);">Xóa tất cả</span>';
                html += '<div class="person-note">Nhập thông tin và nhấn Enter để thêm. Tối đa ' + MAX_PERSON + ' Thông tin/lần.</div>';
                html += '</div>';
                html += '</div>';
                $("#viewdk").html(html);
                bindPersonInput();
                focusPersonInput();
            }

            function focusPersonInput() {
                setTimeout(function () {
                    $("#personInput").focus();
                }, 50);
            }

            function addPerson(name) {
                if (personNames.length >= MAX_PERSON) {
                    alert("Chỉ được nhập tối đa " + MAX_PERSON + " Thông tin/lần!");
                    return;
                }
                name = name.replace(/\s+/g, " ").replace(/^\s+|\s+$/g, "");
                if (name === "") {
                    return;
                }
                for (var i = 0; i < personNames.length; i++) {
                    if (personNames[i].toLowerCase() === name.toLowerCase()) {
                        alert("Thông tin \"" + name + "\" đã được nhập!");
                        $("#personInput").val("");
                        return;
                    }
                }
                personNames.push(name);
                renderPersonTags();
            }

            function renderPersonTags() {
                var html = "";
                for (var i = 0; i < personNames.length; i++) {
                    var safeName = escapeHtml(personNames[i]);
                    html += '<span class="person-tag">' + safeName + '<span class="remove-person" title="Xóa" onclick="removePerson(' + i + ');">&times;</span></span>';
                    html += '<input type="hidden" name="ListVal" value="' + safeName + '">';
                }
                html += '<input type="text" id="personInput" class="person-input" placeholder="Nhập thông tin rồi nhấn Enter...">';
                $("#multiPersonBox").html(html);
                $("#personCount").text(personNames.length);
                bindPersonInput();
                focusPersonInput();
            }

            function removePerson(index) {
                if (index < 0 || index >= personNames.length) {
                    return;
                }
                personNames.splice(index, 1);
                renderPersonTags();
            }

            function clearPersons(focusInput) {
                personNames = [];
                if ($("#multiPersonBox").length > 0) {
                    renderPersonTags();
                }
                if (focusInput === true) {
                    focusPersonInput();
                }
            }

            function escapeHtml(text) {
                return String(text).replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;").replace(/'/g, "&#039;");
            }

            function funcandk() {
                $("#dieukien").toggle();
            }

            function funsubmitdata() {
                var loaitc = $("#loaitc").val();
                if (loaitc === "DTTN" || loaitc === "KHUQ" || loaitc === "KHVV") {
                    if (personNames.length === 0) {
                        alert("Bạn cần nhập ít nhất một thông tin để tra cứu!");
                        focusPersonInput();
                        return;
                    }
                }
                var x = document.getElementById("frmmain").elements.length;
                for (var i = 0; i < x; i++) {
                    var element = document.getElementById("frmmain").elements[i];
                    if (element.required && element.value === "") {
                        alert("Bạn cần nhập đầy đủ những trường bắt buộc (*).");
                        return;
                    }
                }
                $("#viewcontent").html("<img src='imgs/Preloader_3.gif'>" + "<strong> Đang tải dữ liệu...</strong>");
                $.ajax({
                    url: "ShowList.action",
                    type: "POST",
                    data: $("#frmmain").serialize(),
                    success: function (data) {
                        $("#viewcontent").html(data);
                    },
                    error: function (xhr, error) {
                        alert("Có lỗi xảy ra trong quá trình xử lý dữ liệu!");
                        $("#viewcontent").html("<div style='color:red'>" + "Lỗi xử lý dữ liệu (" + xhr.status + " - " + error + ")" + "</div>");
                    }
                });
            }

            function bindPersonInput() {
                $("#personInput").unbind("keydown");
                $("#personInput").bind("keydown", function (e) {
                    if (e.keyCode === 13) {
                        e.preventDefault();
                        var name = $.trim($(this).val());
                        if (name === "") {
                            return;
                        }
                        addPerson(name);
                    }
                });
            }
        </script>
    </body>
</html>