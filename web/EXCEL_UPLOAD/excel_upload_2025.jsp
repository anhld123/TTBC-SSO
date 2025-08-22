<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:head/>
<sj:head/>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Upload Excel</title>
        <style>
            body {
                font-family: "Segoe UI", Tahoma, Arial, sans-serif;
                font-size: 11pt;
                background: #f8f9fa;
                margin: 0;
                padding: 0;
            }

            .main-container {
                margin: 40px auto;
                width: 70%;
                border-radius: 12px;
                padding: 25px;
                box-shadow: 0 3px 10px rgba(0,0,0,0.1);
                border: 1px solid #e0e0e0;

                background: url('img/backgroud_logo.jpg') no-repeat center center;
                background-size: contain; /* hoặc cover nếu muốn phủ kín */
            }
            h2 {
                color: #2e7d32;
                text-align: center;
                margin-bottom: 25px;
                font-size: 20px;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            td {
                padding: 10px;
                vertical-align: middle;
                white-space: nowrap; /* Không cho text tự xuống dòng */
            }

            .label {
                font-weight: bold;
                color: #388e3c;
                width: 180px;
            }

            input[type="file"], select {
                padding: 5px;
                border: 1px solid #ccc;
                border-radius: 6px;
                width: 250px;
            }

            input[type="radio"] {
                margin-right: 5px;
            }

            /* Nút cơ bản xanh lá */
            .btn-green {
                background-color: #4caf50;
                border: none;
                padding: 7px 16px;
                border-radius: 6px;
                color: white;
                font-weight: bold;
                cursor: pointer;
                transition: 0.2s ease;
            }
            .btn-green:hover {
                background-color: #43a047;
            }

            /* Nút cơ bản hồng */
            .btn-pink {
                background-color: #e91e63;
                border: none;
                padding: 7px 16px;
                border-radius: 6px;
                color: white;
                font-weight: bold;
                cursor: pointer;
                transition: 0.2s ease;
            }
            .btn-pink:hover {
                background-color: #d81b60;
            }

            .note {
                font-size: 9pt;
                color: #c62828;
                margin-top: 10px;
                font-style: italic;
            }

            #upload_result_div {
                margin-top: 15px;
                padding: 12px;
                border-radius: 6px;
                background: #e8f5e9;
                border-left: 5px solid #4caf50;
                color: #1b5e20;
                display: none;
            }

            #loadingImageDiv {
                margin-top: 15px;
                display: none;
                text-align: center;
                color: #2e7d32;
            }
        </style>
    </head>
    <body>
        <div class="main-container">
            <h2>Upload File Excel</h2>

            <s:form id="dtw_upload_form"
                    theme="simple"
                    enctype="multipart/form-data"
                    action="upload_excel_2025.action">

                <table>
                    <tr>
                        <td class="label">Chọn file Excel:</td>
                        <td colspan="3">
                            <s:file name="fileUpload" cssStyle="width:100%;" theme="simple"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">Định dạng font:</td>
                        <td colspan="3">
                            <label style="margin-right: 20px;">
                                <input type="radio" name="font_type" value="TCVN">
                                TCVN
                            </label>
                            <label>
                                <input type="radio" name="font_type" value="UTF8" checked="true">
                                Unicode (UTF-8)
                            </label>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">Lấy file mẫu:</td>
                        <td><s:select 
                            name="mauBc" 
                            list="lstDmKhac" 
                            listKey="code" 
                            listValue="%{code + ' - ' + description}" 
                            headerKey="" 
                            headerValue="-- Chọn --" 
                            cssStyle="width: 100%; padding:5px; background: transparent; border: 1px solid #ccc; color:#2c3e50;"/>
                        </td>
                    </tr> 
                    <tr>
                        <td colspan="4" style="text-align: center;">
                            <button type="button" class="btn-green" onclick="downloadTemplate()">Tải file mẫu</button>

                            <sj:submit value="Upload"
                                       cssClass="btn-pink"
                                       targets="upload_result_div"
                                       onBeforeTopics="before-upload"
                                       onCompleteTopics="after-upload"
                                       cssStyle="margin-left:15px;"
                                       theme="simple"/>
                        </td>
                    </tr>
                </table>
                <p class="note">(*) Lưu ý: File Excel đúng mẫu mới được xử lý. Sau khi upload dữ liệu sẽ được ghi vào hệ thống.</p>
            </s:form>

            <!-- Loading -->
            <div id="loadingImageDiv">
                <span>Đang xử lý file. Vui lòng chờ...</span><br/>
                <img src="img/loading-3.gif" style="width:32px;height:32px;margin-top:5px;">
            </div>

            <!-- Kết quả -->
            <div id="upload_result_div"></div>
        </div>

        <script>
            $.subscribe('before-upload', function () {
                $("#upload_result_div").hide().empty();
                $("#loadingImageDiv").show();
            });

            $.subscribe('after-upload', function () {
                $("#loadingImageDiv").hide();
                $("#upload_result_div").show();

                // Reload trang cha nếu có
                if (window.opener && !window.opener.closed) {
                    window.opener.location.reload();
                }
            });

            window.onunload = function () {
                if (window.opener && !window.opener.closed) {
                    window.opener.location.reload();
                }
            };

            function downloadTemplate() {
                var mauBc = document.querySelector("[name='mauBc']").value;
                if (!mauBc) {
                    alert("Bạn chưa chọn loại file mẫu!");
                    return;
                }
                window.location.href = "download_template.action?mauBc=" + encodeURIComponent(mauBc);
            }
        </script>
    </body>
</html>
