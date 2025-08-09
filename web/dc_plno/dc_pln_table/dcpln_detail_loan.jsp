<%-- 
    Document   : risk_detail_customer
    Created on : May 27, 2015, 8:50:46 AM
    Author     : BAOANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin chi tiết Món vay - Phân loại nợ</title>
        <sx:head/>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <!--<script type="text/javascript" src="js/pagination.js"></script>-->
        <style>  
            body {
                background-image: url('img/backgroud_logo.jpg');
                background-size: 40% auto;
                background-repeat: no-repeat;
                background-position: center center;
                background-attachment: fixed;
                background-blend-mode: multiply;
                /*background-position: center 80px;*/
            }
            table {
                border-collapse: collapse;
                width: 100%;
                font-size: 13px;
                table-layout: fixed;
                line-height: 1.3; /* Giảm chiều cao dòng */
            }

            th, td {
                border: 1px solid #ccc;
                padding: 4px 6px; /* Ít padding hơn */
                text-align: center;
                vertical-align: middle;
                word-wrap: break-word;
            }

            .tbhead th {
                background-color: #DCDCDC;
                font-weight: bold;
                color: #333;
            }

            .cscontent td {
                background-color: #ffffff;
                padding: 4px 6px;
            }

            input[type="text"] {
                width: 100%;
                box-sizing: border-box;
                padding: 2px 4px;
                font-size: 13px;
            }
        </style>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script language="javascript">
            $(document).ready(function () {
                $('.number').number(true, 0).css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
            });
        </script>
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw">
            <div id="divTitle" style="text-align: center">            
                <span id="idTitle" style="color:blue;font-weight: bold">THÔNG TIN CHI TIẾT MÓN VAY ĐỐI CHIẾU - PHÂN LOẠI NỢ</span>
                <hr/>
                <s:form name="frmdata" id="frmdata" >
                    <s:iterator value="#attr.lstDulieuNtPLN" var="modelView" status="rowstatus">
                        <table id="subTable" align="center">
                            <tr>
                                <th style="text-align: left; width: 20%;">Mã KH: <span style="color:blue;"><s:property value='plnMakh'/></span></th>
                                <th style="text-align: left; width: 20%;">Tên KH: <span style="color:blue;"><s:property value='plnTenkh'/></span></th>
                                <th style="text-align: left;">Sản phẩm: <span style="color:blue;"><s:property value='plnSprdCdTen'/> (<s:property value='plnSprdCd'/>)</span></th>
                            </tr>
                            <tr>
                                <th style="text-align: left; width: 20%;">Tổ TK&VV: <span style="color:blue;"><s:property value='plnMato'/> - <s:property value='plnTentt'/></span></th>
                                <th style="text-align: left; width: 20%;">Hội đoàn thể: <span style="color:blue;"><s:property value='plnDvutTen'/></span></th>
                                <th style="text-align: left;">
                                    Trạng thái phân loại nợ: <span style="color:blue;"><s:property value='plnTrangthaiTen'/></span>
                                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Ngày số liệu: <span style="color:blue;"><s:property value='plnNgaybc'/></span>
                                </th>
                            </tr>
                        </table>
                        <hr/>
                        <table border="1" class="editDelete" id="subTable" align="center">
                            <tr>
                                <th rowspan="3" style="font-weight:bold;width: 130px">Mã món vay</th>
                                <th class="TD_DU_NO" colspan="5" style="font-weight:bold;">Số liệu tại NHCSXH</th> 
                                <th class="TD_DU_NO" colspan="2" rowspan="2" style="font-weight:bold;">Chênh lệch</th>
                                <th class="TD_SOKU" rowspan="3" style="font-weight:bold;width: 100px;">Trạng thái</th>
                            </tr>
                            <tr>
                                <th class="TD_DU_NO" colspan="4" style="font-weight:bold;">Nợ gốc</th> 
                                <th class="TD_DU_NO" rowspan="2" style="font-weight:bold;">Nợ lãi</th> 
                            </tr>
                            <tr>
                                <th class="TD_DU_NO"  style="font-weight:bold;">Tổng số</th>
                                <th class="TD_DU_NO" style="font-weight:bold;">Nợ trong hạn</th>                  

                                <th class="TD_DU_NO" style="font-weight:bold;">Nợ quá hạn</th>    
                                <th class="TD_DU_NO" style="font-weight:bold;">Nợ khoanh</th> 

                                <th class="TD_DU_NO" style="font-weight:bold;">Gốc</th> 
                                <th class="TD_DU_NO" style="font-weight:bold;">Lãi</th> 
                            </tr>

                            <tr>
                                <td align = "center" style="width: 120px"> 
                                    <s:property value='plnSoku'/>
                                    <input type="hidden" value="<s:property  value="plnSoku" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnSoku"/>                             
                                    <input type="hidden" value="<s:property  value="plnMapgd" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnMapgd"/>  
                                    <input type="hidden" value="<s:property  value="plnTenkh" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnTenkh"/>  
                                    <input type="hidden" value="<s:property  value="plnMakh" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnMakh"/>  
                                    <input type="hidden" value="<s:property  value="plnMato" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnMato"/>  
                                    <input type="hidden" value="<s:property  value="plnTongDno" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnTongDno"/>  
                                    <input type="hidden" value="<s:property  value="plnDnothan" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnDnothan"/>  
                                    <input type="hidden" value="<s:property  value="plnDnoqhan" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnDnoqhan"/>  
                                    <input type="hidden" value="<s:property  value="plnDnokhoanh" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnDnokhoanh"/>
                                    <input type="hidden" value="<s:property  value="plnTonglaiton" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnTonglaiton"/>
                                    <input type="hidden" value="<s:property  value="plnMacn" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnMacn"/> 
                                    <input type="hidden" value="<s:property  value="plnNguyennhanC2" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].PlnNguyennhanC2"/> 

                                </td>

                                <td class="number" id="TongDno_<s:property value='%{#rowstatus.index}' />">
                                    <s:property value="plnTongDno" /></td>
                                <td class="number"><s:property value="plnDnothan"/> </td>
                                <td class="number"><s:property value="plnDnoqhan"/> </td>
                                <td class="number" id="Dnokhoanh_<s:property value='%{#rowstatus.index}' />">
                                    <s:property value="plnDnokhoanh"/> </td>
                                <td class="number" id="Tonglai_<s:property value='%{#rowstatus.index}' />">
                                    <s:property value="plnTonglaiton"/> </td>

                                <!--Chi tieu nhap tay tu day--> 
                                <td>
                                    <input type="text" 
                                           value="<s:property value="plnNogocClech" />" 
                                           id="D1_<s:property value='%{#rowstatus.index}' />" 
                                           style="width: 99%; background-color: rgba(200, 200, 200, 0.3); border: 1px solid #ccc;"
                                           name="lstDulieuNtPLN_T[<s:property value="%{#rowstatus.index}" />].plnNogocClech" 
                                           oninput="onSelectChange_dnht1(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                           class="number2" />
                                </td>
                                <td>
                                    <input type="text" 
                                           value="<s:property value="plnNolaiClech" />" 
                                           id="D2_<s:property value='%{#rowstatus.index}' />" 
                                           style="width: 99%; background-color: rgba(200, 200, 200, 0.3); border: 1px solid #ccc;"
                                           name="lstDulieuNtPLN_T[<s:property value="%{#rowstatus.index}" />].plnNolaiClech" 
                                           oninput="onSelectChange_dnht2(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                           class="number2" />
                                </td>
                                <td class="D0">
                                    <select id="trangthai_<s:property value='%{#rowstatus.index}' />" 
                                            name="lstDulieuNtPLN_T[<s:property value="%{#rowstatus.index}" />].plnTrangthai" style="width: 100px">
                                        <option value="N" <s:if test="plnTrangthai.equalsIgnoreCase('N')"> selected </s:if> <s:else></s:else>>Chưa đối chiếu</option>
                                        <!--<option value="R" <s:if test="plnTrangthai.equalsIgnoreCase('R')"> selected </s:if> <s:else></s:else>>Không đối chiếu được</option>-->
                                        <option value="S" <s:if test="plnTrangthai.equalsIgnoreCase('S')"> selected </s:if> <s:else></s:else>>Đã đối chiếu</option>
                                        </select></td>
                                </tr>
                            </table>
                            <hr/>
                            <table>
                                <tr>
                                    <td colspan="2">
                                        <span id="idTitle" style="font-weight: bold; color: blue; text-align: left; display: block;">
                                            Nguyên nhân chênh lệch hoặc nguyên nhân không đối chiếu được
                                        </span>    </td>
                                </tr>

                                <tr align="center">
                                    <td colspan="2" align="center">
                                            <textarea id="sNgnhan_Clech_<s:property value='%{#rowstatus.index}' />" 
                                              value="<s:property value='plnNgnhanClech'/>"
                                              name="lstDulieuNtPLN_T[<s:property value="%{#rowstatus.index}" />].plnNgnhanClech"
                                              style="width: 99%; background-color: rgba(200, 200, 200, 0.3); border: 1px solid #ccc;" 
                                              rows="6"><s:property value='plnNgnhanClech'/></textarea>
                                </td>
                            </tr>
                        </table>

                        <table>
                            <tr>
                                <td colspan="2">
                                    <span id="idTitle" style="font-weight: bold; color: blue; text-align: left; display: block;">Quan hệ với khách hàng</span>
                                </td>
                            </tr>
                            <tr align="center">
                                <td  colspan="2" align="center" class="TD_NGUYEN_NHAN_KHOANH">
                                    <textarea id="sQuanhe_Kh_<s:property value='%{#rowstatus.index}' />"  value="<s:property value='plnQuanheKh'/>"
                                              name="lstDulieuNtPLN_T[<s:property value="%{#rowstatus.index}" />].plnQuanheKh" onfocus="this.select()"
                                              style="width: 99%; background-color: rgba(200, 200, 200, 0.3); border: 1px solid #ccc;"
                                              rows="2" ><s:property value='plnQuanheKh'/></textarea>                                              
                                </td>
                            </tr>
                            <tr align="center">
                                <td colspan="2" align="center">
                                    <hr/>
                                </td>
                            </tr>
                            <tr align="center">
                                <td align="center">
                                    <input type="button" id="idSave" value="Đồng ý"
                                           style="margin-right:25px; float: right; height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"/> 
                                </td>
                                <td align="center">
                                    <input type="button" id="cmdEnd" value="Thoát"
                                           style="margin-left:25px; float: left;height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"/> 
                                </td>
                            </tr>
                        </table>
                    </s:iterator>
                </s:form>

            </div>
    </body>
    <script>
        $("#idSave").click(function () {
            $('#message_suc_err').empty();
            $('#divExportReportLink').empty();

            let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
            if (aCheck) {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                var isValid = true;

                for (var i = 0; i < rowcount; i++) {
                    try {
                        var d1 = document.getElementById("D1_" + i).value;
                        var d2 = document.getElementById("D2_" + i).value;
                        var d3 = document.getElementById("sNgnhan_Clech_" + i).value;
                        var d4 = document.getElementById("sQuanhe_Kh_" + i).value;
                        var d5 = document.getElementById("trangthai_" + i).value;
                        if (parseFloat(d1) + parseFloat(d2) > 0 && d3.length < 1) {
                            alert('Bạn phải nhập nguyên nhân chênh lệch khi đối chiếu!');
                            document.getElementById("sNgnhan_Clech_" + i).style.backgroundColor = "#EEAFA6";
                            return;
                        }
                        if ((parseFloat(d1) + parseFloat(d2) <= 0 && d3.length > 0) && d5 !== 'R') {
                            alert('Nguyên nhân chênh lệch không hợp lệ do Không có chênh lệch nợ gốc hoặc nợ lãi. Vui lòng kiểm tra lại!');
                            document.getElementById("sNgnhan_Clech_" + i).style.backgroundColor = "#EEAFA6";
                            return;
                        }
                    } catch (e) {
                    }
                }
                if (isValid) {
                    var url, sdata;
                    url = "saveDataDcPLN_Loan.action";
                    sdata = jQuery("#frmdata").serialize();
                    console.log(sdata);
                    $("#viewData").html('<img src="img/loading.gif"/>');
                    btnDisabled(1);
                    $.ajax({
                        type: "POST",
                        url: url,
                        data: sdata,
                        success: function (data) {
                            if (data === "200") {
                                alert("Thành công: Lưu dữ liệu.");
                                idEnd();
                            } else {
                                alert("Lỗi: Lưu dữ liệu.");
                                tai_lai_trang();
                            }
                        },
                        complete: function () {
                            btnDisabled(0);
                        },
                        error: function (request) {
                            alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                            tai_lai_trang();
                        }
                    });
                }
            }
            function tai_lai_trang() {
                location.reload();
            }
        });
        function btnDisabled(status) {
            if (status === 1) {
                $("#loadDatatmp").prop('disabled', true);
                $("#idPheduyet").prop('disabled', true);
                $("#idSave").prop('disabled', true);
                $("#idSaveLock").prop('disabled', true);
                $("#idDelete").prop('disabled', true);
            } else {
                $("#idPheduyet").prop('disabled', false);
                $("#idSave").prop('disabled', false);
                $("#loadDatatmp").prop('disabled', false);
                $("#idSaveLock").prop('disabled', false);
                $("#idDelete").prop('disabled', false);
            }
        }
        ;

        $("#cmdEnd").click(function () {
            window.opener.document.getElementById('loaddata').click();
            window.close();
        });

        function idEnd() {
            window.opener.document.getElementById('loaddata').click();
            window.close();
        }
        window.onbeforeunload = function () {
            window.opener.document.getElementById('loaddata').click();
        };

        function onSelectChange_dnht1(value, index) {
            const tongdunoText = document.getElementById('TongDno_' + index).innerText;
            // Convert the 'tongdunoText' and 'value' to numeric values
            const tongduno = parseFloat(tongdunoText.replace(/,/g, '').replace(/\./g, '')) || 0;
            const value1 = parseFloat(value.replace(/,/g, '').replace(/\./g, '')) || 0;

            if (value1 > tongduno) {
                alert('Nợ gốc chênh lệch không thể lớn hơn dư nợ của món vay. Vui lòng kiểm tra lại!');
                const inputField = document.getElementById('D1_' + index);
                inputField.value = 0;

                inputField.style.backgroundColor = "#ffff99";
                const row = document.getElementById('D1_' + index);
                if (row) {
                    row.style.backgroundColor = "#ffff99";
                }
            }
        }

        function onSelectChange_dnht2(value, index) {
            const tongdunoText = document.getElementById('Tonglai_' + index).innerText;
            // Convert the 'tongdunoText' and 'value' to numeric values
            const tongduno = parseFloat(tongdunoText.replace(/,/g, '').replace(/\./g, '')) || 0;
            const value1 = parseFloat(value.replace(/,/g, '').replace(/\./g, '')) || 0;

            if (value1 > tongduno) {
                alert('Nợ gốc chênh lệch không thể lớn hơn dư nợ của món vay. Vui lòng kiểm tra lại!');
                const inputField = document.getElementById('D2_' + index);
                inputField.value = 0;

                inputField.style.backgroundColor = "#ffff99";
                const row = document.getElementById('D2_' + index);
                if (row) {
                    row.style.backgroundColor = "#ffff99";
                }
            }
        }


    </script>
</html>

