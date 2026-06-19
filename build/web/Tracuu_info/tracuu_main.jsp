<%-- 
    Document   : Tra_cuu_hncn
    Created on : Oct 1, 2015, 10:49:00 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tra cứu thông tin</title>
        <script type="text/javascript" src="DMChitieu/js/jquery-1.4.4.min.js"></script>
        <style type="text/css">
            .head{
                font-size: 12px; font-weight: bold; height: 25px; padding-left: 10px;
            }
            .gentable{
                background-color: #DDFFDD;
                width: 100%;
                border:0px;
            }
            #listten{
                border: 0px;
                background: transparent;
                width: 90px;
                color: #000;
            }
            #customers {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            #customers td, #customers th {
                border: 1px solid #ddd;
                padding: 8px;
            }

            #customers tr:nth-child(even){background-color: #f2f2f2;}

            #customers tr:hover {background-color: #ddd;}

            #customers th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #4CAF50;
                color: white;
            }
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
                    // i starts from 1 to skip table header row
                    for (var i = 1; i < rows.length; i++) {
                        if (i < from || i > to)
                            rows[i].style.display = 'none';
                        else
                            rows[i].style.display = '';
                    }
                }

                this.showPage = function (pageNumber) {
                    if (!this.inited) {
                        alert("not inited");
                        return;
                    }

                    var oldPageAnchor = document.getElementById('pg' + this.currentPage);
                    oldPageAnchor.className = 'pg-normal';

                    this.currentPage = pageNumber;
                    var newPageAnchor = document.getElementById('pg' + this.currentPage);
                    newPageAnchor.className = 'pg-selected';

                    var from = (pageNumber - 1) * itemsPerPage + 1;
                    var to = from + itemsPerPage - 1;
                    this.showRecords(from, to);
                }

                this.prev = function () {
                    if (this.currentPage > 1)
                        this.showPage(this.currentPage - 1);
                }

                this.next = function () {
                    if (this.currentPage < this.pages) {
                        this.showPage(this.currentPage + 1);
                    }
                }

                this.init = function () {
                    var rows = document.getElementById(tableName).rows;
                    var records = (rows.length - 1);
                    this.pages = Math.ceil(records / itemsPerPage);
                    this.inited = true;
                }

                this.showPageNav = function (pagerName, positionId) {
                    if (!this.inited) {
                        alert("not inited");
                        return;
                    }
                    var element = document.getElementById(positionId);

                    var pagerHtml = '<span onclick="' + pagerName + '.prev();" class="pg-normal"> &#171 Trước </span> | ';
                    for (var page = 1; page <= this.pages; page++)
                        pagerHtml += '<span id="pg' + page + '" class="pg-normal" onclick="' + pagerName + '.showPage(' + page + ');">' + page + '</span> | ';
                    pagerHtml += '<span onclick="' + pagerName + '.next();" class="pg-normal"> Sau &#187;</span>';

                    element.innerHTML = pagerHtml;
                }
            }
        </script>
        <style type="text/css">
            .pg-normal {
                color: #fff;;
                font-weight: normal;
                text-decoration: none;  
                cursor: pointer;  
            }
            .pg-selected {
                color: red;
                font-weight: bold;      
                text-decoration: underline;
                cursor: pointer;
            }

        </style>
    </head>
    <body style="font-family: tahoma; font-size: 13px;">
        <form name="frmmain" id="frmmain">
            <TABLE border="1"  width="100%">
                <tr style="background-color: #F4F3F2;">
                    <td colspan="2">

                        <span class="head"> TRA CỨU THÔNG TIN:</span>
                        <select name="loaitc" onchange="fn_get_dk_info(this);">
                            <option value="all">00.Chọn thông tin cần tra cứu</option>
                            <s:iterator value="lsinfo">
                                <option value="<s:property value="GIATRI"/>"><s:property value="HIENTHI"/></option>
                            </s:iterator>
                        </select>
                        &nbsp;
                        <INPUT type="button" value="Ẩn/Hiện điều kiện" id="btnhide" onclick="funcandk()" disabled="true"/>
                        <INPUT type="button" value="Thực hiện" id="btnthuchien" onclick="funsubmitdata()" disabled="true"/>
                        <span style="display: none;" id="chkKH"><input type="checkbox" id="chkexcel" name="chkexcel"/><label for="chkexcel">Kiểm tra theo file excel đã upload</label></span>
                    </td>
                </tr>
                <tr id="dieukien">
                    <td>
                        <div id="viewdk">
                            &nbsp;
                        </div>
                    </td>
                </tr>
                <tr id="viewdata">
                    <td>
                        <div id="viewcontent" style="width: 200vh;overflow-x: scroll;">&nbsp;</div>
                    </td>
                </tr>
            </table>
        </form>

        <SCRIPT language="javascript">
            $("#dieukien").hide();
            $("#viewdata").hide();

            function fn_get_dk_info(val) {
                var btnarr = ["btnhide", "btnthuchien"];
                if (val.value === "all") {
                    for (i = 0; i < btnarr.length; i++) {
                        document.getElementById(btnarr[i]).setAttribute('disabled', 'disabled');
                    }
                    $("#dieukien").hide();
                    $("#viewdata").hide();
                } else {
                    for (i = 0; i < btnarr.length; i++) {
                        document.getElementById(btnarr[i]).removeAttribute("disabled");
                    }
                    $("#dieukien").show();
                    $("#viewdata").show();
                    $("#viewcontent").html("&nbsp;");
                    // Thực hiện load điều kiên
                    var url, sdata;
                    url = "GenTable.action?loaitc=" + val.value;
                    $.post(url, function (data) {
                        $("#viewdk").html(data);
                    });
                }
                if (val.value === "KH") {
                    $("#chkKH").show();
                } else {
                    $("#chkKH").hide();
                }
            }
            function funcandk() {
                $("#dieukien").toggle();
            }
//            function funsubmitdata() {
//                var url, sdata,flat;
//                //Kiem tra du lieu truoc khi Submit
//                var x = document.getElementById("frmmain").elements.length;
//                for(i=0;i<x;i++){
//                    flat = document.getElementById("frmmain").elements[i].required;
//                    if(flat===true){
//                        var getVL = document.getElementById("frmmain").elements[i].value;
//                        if(getVL === ""){
//                            alert("Bạn cần nhập đẩy đủ những trường bắt buộc (*).");
//                            return null;
//                        }
//                    }
//                }
//                url = "ShowList.action";
//                sdata = jQuery("#frmmain").serialize();
//                $("#viewcontent").html("<img src='imgs/Preloader_3.gif' border='0'><strong> Đang tải dữ liệu...</strong>");
//                $.post(url, sdata, function (data) {
//                    $("#viewcontent").html(data);
//                });
//            }

            function funsubmitdata() {

                var x = document.getElementById("frmmain").elements.length;

                for (var i = 0; i < x; i++) {
                    if (document.getElementById("frmmain").elements[i].required &&
                            document.getElementById("frmmain").elements[i].value === "") {

                        alert("Bạn cần nhập đầy đủ những trường bắt buộc (*).");
                        return;
                    }
                }

                $("#viewcontent").html("<img src='imgs/Preloader_3.gif'><strong> Đang tải dữ liệu...</strong>");

                $.ajax({
                    url: "ShowList.action",
                    type: "POST",
                    data: $("#frmmain").serialize(),
                    success: function (data) {
                        $("#viewcontent").html(data);
                    },
                    error: function (xhr, error) {

                        alert("Có lỗi xảy ra trong quá trình xử lý dữ liệu!");

                        $("#viewcontent").html(
                                "<div style='color:red'>Lỗi xử lý dữ liệu (" +
                                xhr.status + " - " + error + ")</div>"
                                );
                    }
                });
            }
        </SCRIPT>
    </body>
</html>
