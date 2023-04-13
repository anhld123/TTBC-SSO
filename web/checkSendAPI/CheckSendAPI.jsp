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
            *{
                font-family: Tahoma;
                font-size: 12px;
            }
            #ui-datepicker-div{
                z-index: 99 !important;
            }
            .ui-datepicker-trigger{
                height: auto !important;
            }
        </style>
    </head>
    <body>
        <div style="margin: 5px;">
            <h3>DANH SÁCH ĐƠN VỊ ĐÃ GỬI DỮ LIỆU QUA API</h3>
            <s:form name="frmdata" id="frmdata" theme="simple">
                <fieldset style="display: flex; align-content: space-between;justify-content: space-between;">
                    <legend><b>Báo cáo</b></legend>
                    <div>
                        Chọn
                        <select name="txtsMadv" id="txtsMadv">
                            <s:iterator value="lstDonvi">
                                <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>
                            </s:iterator>
                        </select>
                        <input type="button" id="idSearch" value="Tìm kiếm" style="background-color: gray; color: white; height: 25px; padding: 0px 20px 0px 20px;">
                    </div>
                </fieldset>
                <div id="viewData" style="margin-top: 5px;"></div>                    
            </s:form>
        </div>
        <script>
            //Tải dữ liệu
            $("#idSearch").click(function () {
                var url, sdata;
                url = "getCheckSendAPI.action";
                sdata = jQuery("#frmdata").serialize();
                $("#viewData").html('<img src="img/loading.gif"/>');
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        $("#viewData").html(data);
                    },
                    error: function (request) {
                        console.log(request);
                        alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                    }
                });
            });
        </script>
    </body>
</html>
