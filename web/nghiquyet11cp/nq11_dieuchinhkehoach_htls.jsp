<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/3.6.0/jquery.min.js"></script>
        <link rel="stylesheet" href="js/3.6.0/jquery-ui.css">
        <script src="js/3.6.0/jquery-ui.js"></script>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <style>
            #tableKtnb{
                width: 100%;
            }
            #tableKtnb th{
                padding: 3px;
            }
        </style>
    </head>
    <body>
        <s:form name="frmdata" id="frmdata" theme="simple">        
            <table border="1px" id="tableKtnb">
                <thead>
                    <tr>
                        <th style="text-align: left;">
                            <b style="padding-right: 3px;">Năm báo cáo</b><span id="txtNamBC"></span>
                            <input type="button" name="txtLoad" id="idLoad" value="Xem dữ liệu"/>
                            <input type="button" name="txtSave" id="idSave" value="Lưu dữ liệu" style="display: none;"/>
                        </th>
                    </tr>
                </thead>
                <tbody id="tbody">    
                        <tr>
                            <td id="viewData"></td>
                        </tr>
                </tbody>
                </table>
        </s:form>
        <script>
            $(function () {
                let selectNamBC, yearNow;
                yearNow = new Date().getFullYear();
                selectNamBC = '<select name="NamBc" id="slNamBc">';
                for (var i = (yearNow - 10); i < (yearNow + 10); i++) {
                    if(i==yearNow){
                        selectNamBC += '<option value="' + i + '" selected>' + i + '</option>'
                    }else{
                        selectNamBC += '<option value="' + i + '">' + i + '</option>'
                    }
                    
                }
                selectNamBC += '</select>';
                $("#txtNamBC").html(selectNamBC);
            });
            //Tải dữ liệu
            $("#idLoad").click(function () {
                var url, sdata;
                url = "loadDCHTLS.action";
                sdata = jQuery("#frmdata").serialize();
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        $("#viewData").html(data);
                        $("#idSave").show();
                    },
                    error: function (request) {
                        alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                    }
                });
            });

            //Lưu dữ liệu
            $("#idSave").click(function () {
                let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                if (aCheck) {
                    var url, sdata;
                    url = "saveDCHTLS.action";
                    sdata = jQuery("#frmdata").serialize();
                    $.ajax({
                        type: "POST",
                        url: url,
                        data: sdata,
                        success: function (data) {
                            if (data === "200") {
                                $("#idLoad").trigger("click");
                                alert("Thành công: Lưu dữ liệu.");
                            } else {
                                alert("Lỗi: Lưu dữ liệu.");
                            }
                        },
                        error: function (request) {
                            alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                        }
                    });
                    $("#idSave").hide()();
                }
            });
        </script>
    </body>
</html>

