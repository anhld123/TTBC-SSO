<%-- 
    Document   : ViewData
    Created on : May 17, 2022, 9:56:22 AM
    Author     : NGUYEN PHU VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<style>
    #subTable {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
    }
    #subTable th{
        background-color: #028e07;
        color: white;
    }

    #subTable th, #subTable td {
        border: 1px solid gray;
    }

    #subTable tr:nth-child(even){background-color: #f2f2f2;}

    #subTable tr:hover {background-color: #ddd;}

    .txtPublic{
        width: 85px;
    }
    .ui-datepicker-trigger{
        height: 100%;
    }
    .txtBody{
        text-align: center;
    }
    .txtBody > .ui-datepicker-trigger{
        display: none;
    }
    td.hdtitle {
        position: sticky;
        top: 0;
        z-index: 10;
    }
    .ThanhVien{
        display: None;
    }
</style>
<script>
    $(function () {
        $(".cssDate").datepicker({dateFormat: 'dd/mm/yy', showOn: "button",
            buttonImage: "img/icon-ui_datepicker.png",
            buttonImageOnly: true,
            buttonText: "icono",
            dateFormat: 'dd/mm/yy',
            showButtonPanel: true,
            changeMonth: true,
            changeYear: true,
            showOn: "both"});
    });
    $('.autoHeight').each(function () {
        this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
    }).on('input', function () {
        this.style.height = 'auto';
        this.style.height = (this.scrollHeight) + 'px';
    });
    function funcThanhVien(maKH) {
        var url, sdata;
        url = "saveLeaveLocal.action";
        sdata = jQuery("#frmdata").serialize();
        $.ajax({
            type: "POST",
            url: url,
            data: sdata,
            success: function (data) {
                if (data != "200") {
                    alert("Lỗi: Thực hiện lưu dữ liệu.");
                }
            },
            error: function (request) {
                alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
            }
        });
        var w = 850, h = 600;
        var left = (screen.width / 2) - (w / 2);
        var top = (screen.height / 2) - (h / 2);
        window.open("/IMS_REPORTS/PopupThanhvien.action?vsbpMakh=" + maKH + "&vsbpNgayBC=" + $("#txtNgaybc").val() + "&vbsprandom=" + Math.random(), "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
    }
</script>
</head>
<body>
    <div style="overflow:scroll; width: 100%;">
        <table id="subTable" style="z-index: 1;">
            <thead>
                <tr>
                    <th class="hdtitle">STT</th>
                    <th class="hdtitle">Tên chi nhánh</th>
                    <th class="hdtitle">Tên PGD</th>
                    <th class="hdtitle">Tên xã</th>
                    <th class="hdtitle">Tên tổ trưởng</th>
                    <th class="hdtitle">Mã KH</th>
                    <th class="hdtitle">Tên KH vay vốn</th>
                    <th class="hdtitle">Năm sinh</th>
                    <th class="hdtitle">CMT/CCCD</th>
                    <th class="hdtitle">Số điện thoại</th>
                    <th class="hdtitle">Loại đối tượng</th>
                    <th class="hdtitle">Thời điểm đi</th>
                    <th class="hdtitle">Mã nhóm</th>
                    <th class="hdtitle">Mã quản lý</th>
                    <th class="hdtitle">Thông tin (100-200 ký tự)</th>
                    <th class="hdtitle">Tên chi nhánh hộ vay chuyển đến</th>
                    <th class="hdtitle">Phản hồi của chi nhánh hộ vay chuyển đến </t>
                    <th class="hdtitle">Bảo hiểm</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator value="lstData" status="idxRows">
                    <tr class="tr_clone">
                        <td class="txtBody" style="display: none;">
                            <s:property value="D3"/>
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].THUTU" value="<s:property value='THUTU'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D3" value="<s:property value='D3'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D4" value="<s:property value='D4'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D5" value="<s:property value='D5'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D6" value="<s:property value='D6'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D7" value="<s:property value='D7'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D8" value="<s:property value='D8'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D9" value="<s:property value='D9'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D10" value="<s:property value='D10'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D11" value="<s:property value='D11'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D12" value="<s:property value='D12'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D13" value="<s:property value='D13'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D14" value="<s:property value='D14'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D15" value="<s:property value='D15'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D17" value="<s:property value='D17'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D18" value="<s:property value='D18'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D19" value="<s:property value='D19'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D20" value="<s:property value='D20'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D26" value="<s:property value='D26'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D27" value="<s:property value='D27'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D29" value="<s:property value='D29'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D32" value="<s:property value='D32'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D33" value="<s:property value='D33'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D34" value="<s:property value='D34'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D35" value="<s:property value='D35'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D36" value="<s:property value='D36'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D37" value="<s:property value='D37'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D38" value="<s:property value='D38'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D39" value="<s:property value='D39'/>">

                        </td>
                        <td class="txtBody"><s:property  value='%{#idxRows.index + 1}' /></td>
                        <td class="txtBody"><div class="<s:property value="D20"/>"><s:property value="D4"/></div></td>
                        <td class="txtBody"><div class="<s:property value="D20"/>"><s:property value="D6"/></div></td>
                        <td class="txtBody"><div class="<s:property value="D20"/>"><s:property value="D8"/></div></td>
                        <td class="txtBody"><div class="<s:property value="D20"/>"><s:property value="D10"/></div></td>
                        <td class="txtBody"><div class="<s:property value="D20"/>"><s:property value="D11"/></div></td>
                        <td class="txtBody">
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                <s:property value="D12"/>
                            </s:if>
                            <s:else>
                                <a href="javascript:funcThanhVien('<s:property value="D11"/>')"><s:property value="D12"/></a>
                            </s:else>
                        </td>
                        <td class="txtBody">
                            <s:property value="D13"/>
                        </td>
                        <td class="txtBody"><s:property value="D14"/></td>
                        <td class="txtBody">
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                <s:property value="D16"/>
                            </s:if>
                            <s:else>
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D16" value="<s:property value='D16'/>" class="txtPublic">
                            </s:else>
                        </td>
                        <td class="txtBody">
                            <s:property value="D15"/>
                        </td>
                        <td class="txtBody"><input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D21" value="<s:property value='D21'/>" class="cssDate txtPublic  <s:property value="D20"/>"></td>
                        <td class="txtBody">
                            <select class="txtPublic <s:property value="D20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].D22" id="lstData<s:property  value='%{#idxRows.index}' />">
                                <option value="01" <s:if test="D22.equalsIgnoreCase('01')"> selected </s:if> <s:else></s:else>>01: Hộ vay bỏ đi khỏi nơi cư trú có thông tin địa chỉ cụ thể</option>
                                <option value="02" <s:if test="D22.equalsIgnoreCase('02')"> selected </s:if> <s:else></s:else>>02: Hộ vay bỏ đi khỏi nơi cư trú có thông tin địa chỉ không cụ thể &nbsp;</option>
                                <option value="03" <s:if test="D22.equalsIgnoreCase('03')"> selected </s:if> <s:else></s:else>>03: Hộ vay bỏ đi khỏi nơi cư trú không có thông tin địa chỉ</option>
                                </select>
                            </td>
                            <td class="txtBody">
                                    <select class="txtPublic <s:property value="D20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].D25" id="lstSubData<s:property  value='%{#idxRows.index}' />">
                                <option value="00" <s:if test="D25.equalsIgnoreCase('00')"> selected </s:if> <s:else></s:else>>00: Bỏ nơi cư trú</option>
                                <option value="01" <s:if test="D25.equalsIgnoreCase('01')"> selected </s:if> <s:else></s:else>>01: Tất toán nợ</option>
                                <option value="02" <s:if test="D25.equalsIgnoreCase('02')"> selected </s:if> <s:else></s:else>>02: Xoá nợ</option>
                                <option value="03" <s:if test="D25.equalsIgnoreCase('03')"> selected </s:if> <s:else></s:else>>03: Bàn giao</option>
                                <option value="04" <s:if test="D25.equalsIgnoreCase('04')"> selected </s:if> <s:else></s:else>>04: Trở về địa phương &nbsp;</option>
                                </select></td>
                            <td class="txtBody">
                                    <textarea id="lstData[<s:property  value='%{#idxRows.index}' />].D23" name="lstData[<s:property  value='%{#idxRows.index}' />].D23" class="autoHeight <s:property value="D20"/>"><s:property value='D23'/></textarea>
                        </td>
                        <td>
                            <select class="txtPublic <s:property value="D20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].D30" id="lstData<s:property  value='%{#idxRows.index}' />">
                                <option value="000000">Không xác định</option>
                                <s:iterator value="lstCN" status="ideRows" var="language">
                                    <s:if test="%{#language.PosCode == D30}">
                                        <option value="<s:property value="PosCode"/>" selected><s:property value="PosName"/></option>
                                    </s:if>
                                    <s:else>
                                        <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>
                                    </s:else>
                                </s:iterator>
                            </select>
                        </td>
                        <td>
                            <s:if test = "!D20.equalsIgnoreCase('ThanhVien')"> 
                                <textarea name="lstData[<s:property  value='%{#idxRows.index}' />].D31" class="autoHeight <s:property value="D20"/>"><s:property value='D31'/></textarea>
                            </s:if>
                        </td>
                        <td class="txtBody">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D24" value="<s:property value='D24'/>" class="txtPublic <s:property value="D20"/>">
                        </td>
                    </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>

