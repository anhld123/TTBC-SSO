<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>
        <script type="text/javascript" src="js/Checkdate.js"></script>

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
                height: 500px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 98%;
                height: 500px;
                /*padding-left: 20px;*/
                float: left;
                /*overflow: scroll;*/
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
                            var isValid = true; // Tạo biến để kiểm tra tính hợp lệ của dữ liệu

                            for (var i = 0; i < rowcount; i++) {
                                try {
                                    var plnKKntnSodu = document.getElementById("D2_" + i).value;
                                    var ngnhanKntn = document.getElementById("D3_" + i).value;
                                    var checkrow = document.getElementById("checkrow_" + i).value;
                                    if (checkrow === "1" && plnKKntnSodu !== "0" && ngnhanKntn === "0")
                                    {
                                        alert("Bạn chưa chọn nguyên nhân!");
                                        document.getElementById("D3_" + i).style.backgroundColor = "#EEAFA6";
                                        isValid = false;
                                        break;
                                    }
                                } catch (e) {
                                }
                            }

                            if (isValid) {
                                var url, sdata;
                                url = "saveDataDcPLN.action";
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
                } else {
                    $("#idSave").prop('disabled', false);
                }
                ;
            }
        </script>
    </head>
    <body topmargin="0" leftmargin="5">
        <div id="container">
            <s:form id="frmdata" name="frmdata" action="loadDataDcPLN" theme="simple">
                <fieldset>
                    <legend><b>Tìm kiếm dữ liệu</b></legend> 
                    <table>
                        <tr>
                            <td>
                                <s:label value="Ngày báo cáo " cssStyle="color: #029c44;" />
                                <sj:datepicker name="ngay_bc_DATE" value="%{'31/12/2023'}"  id="ngay_bc_DATE" 
                                               placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL" onChangeTopics="changeTopic"/> 
                                <sj:submit id="loadData" name="loadData" value="Tải dữ liệu" targets="divExportReport"
                                           onBeforeTopics="beforediv_data"
                                           onCompleteTopics="completediv_data" cssStyle="display:none"/>
                                &nbsp;<s:label value="Món vay " cssStyle="color: #029c44;" />
                                <input type="text" name="soku" id="soku" placeholder="Nhập mã món vay" value="" style="width: 130px">
                                <s:if test="Grade.equalsIgnoreCase('1')">
                                    &nbsp;<s:label value="Mã xã " cssStyle="color: #029c44;" />
                                    <s:select  style="width: 180px;"  list="lstMaxa" id="maxa" name="maxa" listKey="sKey" listValue="sDesc"
                                               onchange="onXaChange(this.value)"></s:select>
                                    &nbsp;<s:label value="Mã hội " cssStyle="color: #029c44;" />
                                    <select id="mahoi" name="mahoi" style="width: 200px" disabled onchange="onHoiChange(this.value)" >
                                        <option value="0">-- Chọn hội đoàn thể --</option>
                                        <s:iterator value="lstDmKhac17">                                    
                                            <option value="<s:property value="code"/>"><s:property value="code"/> - <s:property value="value"/></option>                                         
                                        </s:iterator>   
                                    </select>
                                    &nbsp;<s:label value="Mã tổ " cssStyle="color: #029c44;" />
                                    <s:select style="width: 180px;"
                                              list="lstMato"
                                              id="mato"
                                              name="mato"
                                              listKey="sKey"
                                              listValue="sDesc"
                                              disabled="true" />
                                    <s:select id="mato_data"
                                              list="lstMato"
                                              listKey="sKey"
                                              listValue="sDesc"
                                              headerKey="-1"
                                              headerValue="--- Chọn ---"
                                              cssStyle="display:none;"
                                              disabled="true" />
                                </s:if>
                                &nbsp;
                                <s:label value="Trạng thái " cssStyle="color: #029c44;" />
                                <select id="trangthai" name="trangthai">
                                    <option value="0">-- Tất cả --</option>
                                    <option value="N">Chưa đối chiếu</option>
                                    <option value="R">Không đối chiếu được</option>
                                    <option value="S">Đã đối chiếu</option>
                                </select>
                                <br>

                                <s:label value="Chương trình " cssStyle="color: #029c44;" />
                                <select id="chtrinh" name="chtrinh" style="width: 200px">
                                    <option value="0">-- Tất cả --</option>
                                    <s:iterator value="lstDmKhac197">                                    
                                        <option value="<s:property value="code"/>"><s:property value="code"/> - <s:property value="value"/></option>                                         
                                    </s:iterator>   
                                </select>
                                &nbsp;
                                <s:label value="Nguồn vốn " cssStyle="color: #029c44;" />
                                <select id="nguonvon" name="nguonvon">
                                    <option value="0">-- Tất cả --</option>
                                    <option value="1">Nguồn TW</option>
                                    <option value="2">Nguồn ĐP</option>
                                </select>

                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <sj:submit id="loadsubmitform" name="loadsubmitform" value="Tải dữ liệu" targets="divExportReport" onclick="onclear()"
                                           onBeforeTopics="beforediv1" onCompleteTopics="completediv1" cssStyle="display: none;"/>
                                <input type="button" id="loaddata" name="loaddata" onclick="onLoadData()" value="Tải dữ liệu"/>
                                <input type="button" id="idSave" value="Lưu dữ liệu"/>  
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <div id="page-header" style="display: none" class="hidden-inline">
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

                <!--                <div id="containTree">
                <sjt:tree
                    name="poscd"
                    id="treeDynamicCheckboxes"
                    jstreetheme="apple"
                    rootNode="nodes_pos"
                    childCollectionProperty="children"
                    nodeTitleProperty="title"
                    nodeIdProperty="id"
                    openAllOnLoad="true"
                    checkbox="true"
                    showThemeDots="false"
                    showThemeIcons="true" 
                    />
            </div>-->
                <%--<sj:submit onClickTopics="checkAllNodesTopic" value="Check all Nodes" button="true" onclick="onReloadGroup()" />--%>
                <div id="loadingImageDiv" style="display: none;">
                    <h2 style='color: red'> Xin chờ đang tải dữ liệu ...</h2>
                    </br>
                    <img id="loadingImage" src='img/loading.gif' border='0' >
                </div>

                <div id="containParm" align="center">
                    <div id="divExportReport"></div>
                </div>
            </s:form>
        </div>
    </p>
    <script>
        $(document).ready(function () {
//            $("#ngay_dcpln").val("31/12/2021");
            document.getElementById('ngay_bc_DATE').value = "31/07/2025";
        })

        function onXaChange(maXa) {
            $("#mahoi").val("0");
            $("#mahoi").prop("disabled", false);

            $("#mato").children().remove();
            $("#mato").append("<option value=''>-- Chọn tổ --</option>");
            $("#mato").prop("disabled", true);
        }
        function onHoiChange(maHoi) {
            var maXa = $("#maxa").val();
            var prefix = maHoi + "_" + maXa;

            $("#mato").children().remove();

            // Thêm mặc định
            $("#mato").append("<option value='10_000000_0000000'> -- Tất cả -- </option>");
            $("#mato").append("<option value='1_000000_NOGROUP'> NOGROUP -> Trực tiếp</option>");

            // Lọc danh sách tổ theo hội + xã
            $("#mato_data option").each(function () {
                var val = $(this).val();
                if (val.indexOf(prefix) === 0) {
                    $("#mato").append($(this).clone());
                }
            });

            // Sắp xếp
            $("#mato").html($("#mato option").sort(function (a, b) {
                return a.text.localeCompare(b.text);
            }));

            $("#mato").val("10_000000_0000000");
            $("#mato").prop("disabled", false);
        }

        // Khi trang sẵn sàng
        $(function () {
            $("#soku").on("input", function () {
                const hasValue = this.value.trim() !== "";   // đã nhập hay chưa

                if (hasValue) {
                    // Đặt lại lựa chọn rồi khóa ngay
                    $("#maxa").prop("selectedIndex", 0).prop("disabled", true);
                    $("#trangthai").prop("selectedIndex", 0).prop("disabled", true);
                    $("#nguonvon").prop("selectedIndex", 0).prop("disabled", true);
                    $("#chtrinh").prop("selectedIndex", 0).prop("disabled", true);
                    $("#mato").prop("selectedIndex", 0).prop("disabled", true);
                    $("#mahoi").prop("selectedIndex", 0).prop("disabled", true);
                } else {
                    // Mở khóa nếu người dùng xoá sạch
                    $("#maxa").prop("disabled", false);
                    $("#trangthai").prop("disabled", false);
                    $("#nguonvon").prop("disabled", false);
                    $("#chtrinh").prop("disabled", false);
                }
            });
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
    </script>
</body>
</html>
