<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.12/themes/default/style.min.css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.3.12/jstree.min.js"></script>
        <style>
            body {
                background-image: url('img/backgroud_logo.jpg');
                background-size: 40% auto;
                background-repeat: no-repeat;
                background-position: center center;
                background-attachment: fixed;
                background-blend-mode: multiply;
                background-position: center 120px;
            }
            .hidden-inline {
                display: none;
            }
            .inline-block {
                display: inline-block;
                vertical-align: middle;
            }
            body,td,th,font{ font-family:Tahoma; font-size:12px; }
            .hidden-inline {
                display: none;
            }
            .inline-block {
                display: inline-block;
                vertical-align: middle;
            }
            #container{
                width: 100%;
                height: 500px;
                border: 0px solid;
                padding-left: 0px;                
            }

            #containTree{
                width: 15%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 84%;
                height: 450px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
            }
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: 11px;
            }
            .report_group_form{
                width: 100%;
            }

            #navParam{
                height: 65px;
                padding:3px;
                border: 1px solid;                
            }
            #navParam2{
                height: 65px;
                margin-left: 10px;
                font-weight: bold;
            }
            #Input{
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                padding: 3px 0px 3px 3px;
                margin: 5px 1px 3px 0px;
                border: 1px solid rgba(81, 203, 238, 1);
            }
            #divSearch
            {
                height: 22px;
                border: 1px solid;     
                /*width: 40%;*/
                float: left;
                padding:1px; 
                border: 1px solid;
                position: fixed;
            }
            input[type="text"]
            {
                width: 100%;
                border-color: #18ab29;
                background: #F9F9F9;
                color:#666666;
            }
            input[type=text]:focus, textarea:focus {
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                border: 1px solid rgba(81, 203, 238, 1);
            }

            .buttons {
                display: flex;
                padding: 5px;
                margin-top: 15px;
            }
            .buttons input[type="button"], .buttons input[type="submit"] {
                padding: 8px 16px;
                border: none;
                background: #029c44;
                color: white;
                border-radius: 6px;
                cursor: pointer;
                transition: background 0.3s;
            }
            .buttons input:hover {
                background: #027d36;
            }
            td s\:label,
            td label {
                display: inline-block;
                vertical-align: middle;
                line-height: 28px; /* hoặc khớp với chiều cao của input/select */
                margin-right: 5px;
            }
            .ui-datepicker-trigger {
                height: auto !important;
                vertical-align: middle;
                padding: 2px;
                margin-left: 4px;
                max-height: 20px; /* hoặc điều chỉnh nhỏ hơn nếu cần */
            }

        </style>

        <script>
            $.subscribe("myBeforeHandler", function (event, data) {
                $("#loadingImageDiv").show();
            });
            $.subscribe("myCompleteTopics", function (event, data) {
                $("#loadingImageDiv").hide();
            });
            $(document).ready(function () {
                $(".NGAY_SL").css({"width": "80px"});
            });
            function onLoadData() {
                $('#message_suc_err').empty();
                $('#divExportReport').empty();
                $('#divExportReportLink').empty();
                $("#loadData")[0].click();
                bsubmit = true;
            }
            $(document).ready(function () {
                $("#idSave").click(function () {
//                    console.log("vào 1");
                    let checkedCount = countCheckedItem();
                    if (checkedCount === 0) {
                        alert('Bạn chưa chọn bản ghi để lưu!');
                    } else {
                        let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                        if (aCheck) {
                            var table = document.getElementById("subTable");
                            var rowcount = table.rows.length;
                            console.log("dong = " + rowcount);
                            var isValid = true; // Tạo biến để kiểm tra tính hợp lệ của dữ liệu

                            for (var i = 0; i < rowcount; i++) {
                                try {

                                } catch (e) {
                                }
                            }

                            if (isValid) {
                                var url, sdata;
                                url = "sendDataDcPLN.action";
                                sdata = jQuery("#frmdata").serialize();
//                                 console.log("data = " + sdata);
                                $("#viewData").html('<img src="img/loading.gif"/>');
                                btnDisabled(1);
                                $.ajax({
                                    type: "POST",
                                    url: url,
                                    data: sdata,
                                    success: function (data) {
                                        if (data === "200") {
                                            alert("Thành công: Lưu dữ liệu.");
                                            onLoadData();
                                        } else {
                                            alert("Lỗi: Lưu dữ liệu.");
                                            onLoadData();
                                        }
                                    },
                                    complete: function () {
                                        btnDisabled(0);
                                    },
                                    error: function (request) {
                                        alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                                    }
                                });
                            }
                        }
                    }
                });
            });

            function countCheckedItem() {
                let counter = 0;
                $('.myCheckBox').each(function () {
                    if (this.checked === true)
                        counter++;
                });
                return counter;
            }
            function btnDisabled(status) {
                if (status === 1) {
                    $("#idSave").prop('disabled', true);
                    $("#idSearch").prop('disabled', true);
                    $("#loaddata").prop("disabled", true);
                    $("#idSendAll").prop("disabled", true);
                } else {
                    $("#idSave").prop('disabled', false);
                    $("#idSearch").prop('disabled', false);
                    $("#loaddata").prop("disabled", false);
                    $("#idSendAll").prop("disabled", false);
                }
                ;
            }

        </script>
    </head>
    <body topmargin="0" leftmargin="5">
        <div id="container">
            <s:form id="frmdata" name="frmdata" action="loadDataViewSendPLN" theme="simple">
                <fieldset>
                    <legend><b>Tìm kiếm dữ liệu</b></legend> 
                    <table>
                        <tr>
                            <td>
                                <s:label value="Ngày báo cáo " cssStyle="color: #029c44;" />
                                <sj:datepicker name="ngay_bc_DATE" value="%{'31/12/2023'}"  id="ngay_bc_DATE" 
                                               placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL" onChangeTopics="changeTopic"/> 
                                <select id="lstPGD" name="lstPGD" style="display:none">
                                    <option value="000000">---Chọn PGD---</option>
                                    <s:iterator value="lstPGD_API">                                    
                                        <option value="<s:property value='posCode'/>|<s:property value='posName'/>"><s:property value="posCode"/> - <s:property value="posName"/></option>                                         
                                    </s:iterator>   
                                </select>
                                <select id="lstXa" name="lstXa" style="width: 150px" onchange="onXaChange(this.value)" disabled>
                                    <option value="000000">---Chọn xã---</option>
                                    <s:iterator value="lstXa_API">                                    
                                        <option 
                                            class="xa-option" 
                                            data-pos="<s:property value='posCode'/>"
                                            value="<s:property value='posCode'/>|<s:property value='communeCode'/>"
                                            >
                                            <s:property value="communeCode"/> - <s:property value="communeName"/>
                                        </option>                                         
                                    </s:iterator>   
                                </select>

                                &nbsp;<s:label value="Mã hội " cssStyle="color: #029c44;" />
                                <select id="mahoi" name="mahoi" style="width: 200px" disabled onchange="onHoiChange(this.value)" >
                                    <option value="0">-- Chọn hội đoàn thể --</option>
                                    <s:iterator value="lstDmKhac17">                                    
                                        <option value="<s:property value="code"/>"><s:property value="code"/> - <s:property value="value"/></option>                                         
                                    </s:iterator>   
                                </select>
                                &nbsp;<s:label value="Mã tổ " cssStyle="color: #029c44;" />
                                <s:select id="mato_data"
                                          name="mato_data"
                                          list="lstMato_T"
                                          listKey="sKey"
                                          listValue="sDesc"
                                          headerKey="-1"
                                          headerValue="--- Chọn ---"
                                          cssStyle="width: 200px;vertical-align: middle;"
                                          disabled="true" />
                                <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                           onBeforeTopics="beforediv_data"
                                           onCompleteTopics="completediv_data" cssStyle="display:none"/>

                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <sj:submit id="loadsubmitform" name="loadsubmitform" value="Tải dữ liệu" targets="divExportReport" onclick="onclear()"
                                           onBeforeTopics="beforediv1" onCompleteTopics="completediv1" cssStyle="display: none;"/>
                                <input type="button" id="loaddata" name="loaddata" onclick="onLoadData()" value="Tải dữ liệu"/>
                                <input type="button" id="idSave" value="Chốt dữ liệu" style="display: none"/> 
                                &nbsp;|&nbsp;<input type="button" id="idSearch" value="Danh sách tổ" style="color: red">
                                <input type="hidden" id="smapgd" name="smapgd" value="">
                                <input type="button" id="idSendAll" value="Chốt dữ liệu" style="display: none; color: red" />
                            </td> 
                            <td>
                                <div id="page-header" style="display: none; margin-left: 30px;" class="hidden-inline">

                                    Chọn trang
                                    <input style="width: 40px;border-top-style: hidden; border-left-style: hidden; border-right-style: hidden" type="number" id="pageInput" min="1" />
                                    <a onclick="goToPage()" href="#" id="btn_go">Go</a>
                                    <a onclick="prevPage()" href="#" id="btn_prev">&#8920;</a>
                                    Trang <span id="page"></span>
                                    <a onclick="nextPage()" href="#" id="btn_next">&#8921;</a>
                                </div>
                            </td>
                        </tr>
                    </table>
                </fieldset>

                <div id="containTree"></div>
                <div id="containParm" align="center">
                    <div id="divExportReport"></div>
                </div>
            </s:form>
        </div>
    </p>
    <script>
        $(document).ready(function () {
            document.getElementById('ngay_bc_DATE').value = "31/07/2025";
        });

        $(document).ready(function () {
            const pgdData = [];

            $('#lstPGD option').each(function () {
                const value = $(this).val();
                const text = $(this).text();

                if (value !== "000000") { // bỏ dòng mặc định
                    pgdData.push({
                        id: value.split("|")[0],
                        text: text,
                        parent: "#",
                    });
                }
            });

            // Khởi tạo cây jsTree
            $('#containTree').jstree({
                core: {
                    data: pgdData,
                    multiple: true,
                    themes: {
                        stripes: true
                    }
                },
                checkbox: {
                    keep_selected_style: false,
                    three_state: false
                },
                plugins: ["checkbox"]
            });

            // Bắt sự kiện chọn node
            $('#containTree').on('changed.jstree', function (e, data) {
                const tree = $('#containTree').jstree(true);
                const selected = tree.get_selected();

                if (selected.length > 1) {
                    const latest = data.node.id;

                    selected.forEach(id => {
                        if (id !== latest) {
                            tree.uncheck_node(id); // ❗ bỏ chọn
                        }
                    });
                }
                const currentSelected = tree.get_selected()[0];
                if (currentSelected) {
                    $("#smapgd").val(currentSelected);
                    onPGDChange([currentSelected]);
                }
            });
        });

        function onPGDChange(selectedPGDList) {
            const posCode = selectedPGDList[0];
            // Reset lại lstXa
            $("#lstXa").val("000000");

            // Ẩn tất cả option (ngoại trừ dòng đầu)
            $("#lstXa option.xa-option").hide();

            // Hiện những xã thuộc posCode
            $("#lstXa option.xa-option").each(function () {
                if ($(this).data("pos") === posCode) {
                    $(this).show();
                }
            });

            // Enable lstXa nếu bị disable
            $("#lstXa").prop("disabled", false);
        }


        function onXaChange(lstXa) {
            $("#mahoi").val("0");
            $("#mahoi").prop("disabled", false);

            $("#mato").children().remove();
            $("#mato").append("<option value=''>-- Chọn tổ --</option>");
            $("#mato").prop("disabled", true);
        }

        function onHoiChange(maHoi) {
            // ✅ Tách communeCode từ value của lstXa (dạng posCode|communeCode)
            var maXa = $("#lstXa").val().split("|")[1];
            var prefix = maHoi + "_" + maXa;

            $("#mato").children().remove();

            // ✅ Thêm tùy chọn mặc định
            $("#mato").append("<option value='10_000000_0000000'> -- Tất cả -- </option>");
            $("#mato").append("<option value='1_000000_NOGROUP'> NOGROUP -> Trực tiếp</option>");

            // ✅ Lọc các option từ thẻ ẩn #mato_data
            $("#mato_data option").each(function () {
                var val = $(this).val();
                if (val.indexOf(prefix) === 0) {
                    $("#mato").append($(this).clone());
                }
            });

            // ✅ Sắp xếp theo text (tên tổ)
            $("#mato").html($("#mato option").sort(function (a, b) {
                return a.text.localeCompare(b.text);
            }));

            // ✅ Đặt mặc định "Tất cả"
            $("#mato").val("10_000000_0000000");
            $("#mato").prop("disabled", false);
        }

        function loadMatoList() {
            var maxa = $('#lstXa').val(); // sửa đúng ID
            var mahoi = $('#mahoi').val();

            if (maxa && mahoi && maxa !== '-1' && mahoi !== '0') {
                $.ajax({
                    url: 'reloadMatoPln.action',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        maxa: maxa,
                        mahoi: mahoi
                    },
                    success: function (response) {
                        var select = $('#mato_data');
                        select.empty();
                        select.append('<option value="-1">--- Chọn tổ ---</option>');

                        if (response.lstMato_T && response.lstMato_T.length > 0) {
                            $.each(response.lstMato_T, function (index, item) {
                                select.append('<option value="' + item.sKey + '">' + item.sDesc + '</option>');
                            });
                            select.prop('disabled', false).show();
                        } else {
                            select.prop('disabled', true).hide();
                        }

                        // Gọi lại để fill vào #mato nếu cần
                        onHoiChange(mahoi);
                    },
                    error: function () {
                        alert('Không thể tải danh sách tổ!');
                    }
                });
            }
        }

        $('#lstXa, #mahoi').on('change', function () {
            loadMatoList();
        });
        function initPagination() {
            const iframe = document.getElementById('page-frame');
            const subDoc = iframe.contentWindow || iframe.contentDocument;

            if (subDoc.document)
                subDoc = subDoc.document;

            // Đợi iframe load xong để lấy số dòng
            setTimeout(function () {
                const l = subDoc.getElementById("subTable").rows.length;
                window.l = l; // gán ra biến toàn cục
                document.getElementById("page-header").style.display = "inline-block";
                changePage(1); // Gọi từ cha
            }, 200);
        }

        const header = document.getElementById("page-header");
        header.classList.remove("hidden-inline");
        header.classList.add("inline-block");

        $("#idSearch").click(function () {
            var url, sdata;
            url = "Mass_application.action";
            sdata = jQuery("#frmdata").serialize();
            $("#divExportReport").html('<img src="img/loading.gif"/>');
            btnDisabled(1);
            $.ajax({
                type: "POST",
                url: url,
                data: sdata,
                success: function (data) {
                    $("#divExportReport").html(data);
                    $("#idSend").prop('disabled', true);
                    $("#idSave").prop('disabled', false);
                    $("#idDelete").prop('disabled', false);
                },
                complete: function () {
                    btnDisabled(0);
                },
                error: function (request) {
                    console.log(request);
                    alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                }
            });
        });
        $(document).ready(function () {
            $("#idSendAll").click(function () {
//                    console.log("vào 1");
                let checkedCount = countCheckedItem();
                if (checkedCount === 0) {
                    alert('Bạn chưa chọn bản ghi để lưu!');
                } else {
                    let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
                    if (aCheck) {
                        var table = document.getElementById("subTable");
                        var rowcount = table.rows.length;
                        var isValid = true; // Tạo biến để kiểm tra tính hợp lệ của dữ liệu

                        for (var i = 0; i < rowcount; i++) {
                            try {

                            } catch (e) {
                            }
                        }

                        if (isValid) {
                            var url, sdata;
                            url = "sendDataPlnCn.action";
                            sdata = jQuery("#frmdata").serialize();
//                                 console.log("data = " + sdata);
                            $("#divExportReport").html('<img src="img/loading.gif"/>');
                            btnDisabled(1);
                            $.ajax({
                                type: "POST",
                                url: url,
                                data: sdata,
                                success: function (data) {
                                    if (data === "200") {
                                        alert("Thành công: Chốt dữ liệu.");
                                        $("#idSearch").click();
                                    } 
                                    else if
                                    (data === "999") {
                                        alert("Thông báo: Không có tổ cần chốt dữ liệu!");
                                        $("#idSearch").click();
                                    } 
                                    else {
                                        alert("Lỗi: Chốt dữ liệu.");
                                        $("#idSearch").click();
                                    }
                                },
                                complete: function () {
                                    btnDisabled(0);
                                },
                                error: function (request) {
                                    alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                                     $("#idSearch").click();
                                }
                            });
                        }
                    }
                }
            });
        });
    </script>

</body>
</html>
