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
        width: 200%;
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
    var popWindow;
    var max_row = 0;
    $(document).ready(function () {
        initTable();
    });
    $(function () {
        $(".cssDate").datepicker(
                {
                    dateFormat: 'dd/mm/yy', showOn: "button",
                    buttonImage: "img/icon-ui_datepicker.png",
                    buttonImageOnly: true,
                    buttonText: "icono",
                    //dateFormat: 'dd/mm/yy',
                    showButtonPanel: true,
                    changeMonth: true,
                    changeYear: true,
                    //showOn: "both"
                });
    });
    $('.autoHeight').each(function () {
        this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
    }).on('input', function () {
        this.style.height = 'auto';
        this.style.height = (this.scrollHeight) + 'px';
    });

    function funcThanhVien(maPgd, maKH, tenKH) {
//        var url, sdata;
//        url = "saveLeaveLocal.action";
//        sdata = jQuery("#frmdata").serialize();
//        $.ajax({
//            type: "POST",
//            url: url,
//            data: sdata,
//            success: function (data) {
//                if (data !== "200") {
//                    alert("Lỗi: Thực hiện lưu dữ liệu.");
//                }
//            },
//            error: function (request) {
//                alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
//            }
//        });
        var w = 900, h = 600;
        var left = (screen.width / 2) - (w / 2);
        var top = (screen.height / 2) - (h / 2);
        var urlParam = "vsbpMaPgd=" + maPgd + "&vsbpMakh=" + maKH + "&vsbpTenKh=" + encodeURIComponent(tenKH) + "&vsbpNgayBC=" + $("#txtNgayBc").val() + "&vbsprandom=" + Math.random();
        var url = "/IMS_REPORTS/popupThanhvien.action?" + urlParam;
        popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
    }

    function funcXuLyNo(maPgd, maKH, tenKH, XuLyNo, startPayment) {
        var w = 500, h = 300;
        var left = (screen.width / 2) - (w / 2);
        var top = (screen.height / 2) - (h / 2);
        var urlParam = "vsbpMaPgd=" + maPgd + "&vsbpMakh=" + maKH + "&vsbpTenKh=" + encodeURIComponent(tenKH) + "&vsbpNgayBC=" + $("#txtNgayBc").val()
                + "&XuLyNo=" + XuLyNo + startPayment + "&vbsprandom=" + Math.random();
        var url = "/IMS_REPORTS/popupXuLyNo.action?" + urlParam;
        popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
    }

    function onSelectChange(index) {



        let selectedValue = $('#lstData_D30' + index).find(":selected").val();

        //y tuong nhap thong tin bo sung
//        if (selectedValue == '000701')
//            document.getElementById('cust_sub').style.display = '';
//        else
//            document.getElementById('cust_sub').style.display = 'none';

        let province = selectedValue.substring(0, 4);

        $('#lstData_D32' + index + ' option').each(function () {
            //if (!$(this).val().startsWith('0006') ) {
            $(this).remove();
            //}
        });

        $('#lstPGD_Temp option').each(function () {
            if ($(this).val().startsWith(province)) {
                //alert($(this).text() );
                $('#lstData_D32' + index).append($('<option>',
                        {
                            value: $(this).val(),
                            text: $(this).text()
                        }));
            }
        });
    }

    function onSelectChange_dnht(value, index) {
        if (value == '1')
        {
            document.getElementById("lstData38" + index).disabled = false;
            document.getElementById("lstData39" + index).disabled = false;
            document.getElementById("lstData40" + index).disabled = false;
        }
        else
        {
            document.getElementById("lstData38" + index).disabled = true;
            document.getElementById("lstData39" + index).disabled = true;
            document.getElementById("lstData40" + index).disabled = true;
        }
    }

    function initTable()
    {
        var table = document.getElementById("subTable");
        var rowcount = table.rows.length;
        rowcount = rowcount > max_row ? rowcount : max_row;
        for (var i = 0; i < rowcount; i++)
        {
            var value = $('#lstSubData33' + i).find(":selected").val();
            if (value == '1')
            {
                document.getElementById("lstData38" + i).disabled = false;
                document.getElementById("lstData39" + i).disabled = false;
                document.getElementById("lstData40" + i).disabled = false;
            }
            else
            {
//            alert('vao')
                document.getElementById("lstData38" + i).disabled = true;
                document.getElementById("lstData39" + i).disabled = true;
                document.getElementById("lstData40" + i).disabled = true;
            }


        }
    }

</script>
</head>
<body>
    <div style="overflow:scroll; width: 99vw;">     
        <div style="display: none;">
            <select id="lstPGD_Temp">
                <option value="000000">Không xác định</option>
                <s:iterator value="lstPGD" status="ideRows" var="language">                                    
                    <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>                                    
                </s:iterator>
                <option value="999999">Nước ngoài</option>
            </select>
        </div>
        <table id="subTable" style="z-index: 1;">
            <thead>
                <tr>
                    <th  rowspan="2" class="hdtitle">
                        <input type="checkbox" id ="select-all"/>
                    </th>  
                    <th rowspan="2" class="hdtitle">STT</th>
                    <th rowspan="2" class="hdtitle">Tên chi nhánh</th>
                    <th rowspan="2" class="hdtitle">Tên PGD</th>
                    <th rowspan="2" class="hdtitle">Tên xã</th>
                    <th rowspan="2" class="hdtitle">Tên tổ trưởng</th>
                    <th rowspan="2" class="hdtitle">Mã KH <br>(Tình hình xử lý nợ)</th>
                    <th rowspan="2" class="hdtitle">Tên KH vay vốn</th>
                    <th rowspan="2" class="hdtitle">Năm sinh</th>
                    <th rowspan="2" class="hdtitle">CMT/CCCD</th>
                    <th rowspan="2" class="hdtitle">Số điện thoại</th>
                    <th rowspan="2" class="hdtitle">Loại đối tượng</th>
                    <th rowspan="2" class="hdtitle">Thời điểm đi</th>
                    <th rowspan="2" class="hdtitle">Mã nhóm</th>
                    <th rowspan="2" class="hdtitle">Mã quản lý</th>
                    <th rowspan="2" class="hdtitle">Thông tin <br>(100-200 ký tự)</th>
                    <!--                    <th rowspan="2" class="hdtitle">Chi nhánh <br>hộ vay chuyển đến</th>
                                        <th rowspan="2" class="hdtitle">PGD hộ vay chuyển đến</th>-->
                    <th colspan="6" class="hdtitle">Đề nghị hỗ trợ</th>
                    <th rowspan="2" class="hdtitle">Kết quả hỗ trợ</th>
                    <th rowspan="2" class="hdtitle">Tổ chức CT-XH rà soát</th>
                    <th rowspan="2" class="hdtitle">Đề nghị<br>cung cấp<br>thông tin</th>
                    <th rowspan="2" class="hdtitle">Thông tin hỗ trợ</th>
                    <th rowspan="2" class="hdtitle">Ngày cập nhật<br>thông tin</th>
                </tr>
                <tr>
                    <th class="hdtitle">Đề nghị hỗ trợ</th>
                    <th class="hdtitle">Chi nhánh hộ vay chuyển đến</th>
                    <th class="hdtitle">PGD hộ vay chuyển đến</th>
                    <th class="hdtitle">Ngày đề nghị</th>
                    <th class="hdtitle">Số văn bản đề nghị</th>
                    <th class="hdtitle">Ngày hết hiệu lực đề nghị</th>
                </tr>
            </thead>
            <tbody>
                <% int customerCount = 0; %>
                <s:iterator value="lstData" status="idxRows">
                    <tr class="tr_clone">
                        <td class="txtBody" style="display: none;">                            
                            <s:property value="D3"/>
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].key" value="<s:property value='key'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].orderValue" value="<s:property value='orderValue'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].code" value="<s:property value='code'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].name" value="<s:property value='name'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].posCode" value="<s:property value='posCode'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].posFlag" value="<s:property value='posFlag'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].branchCode" value="<s:property value='branchCode'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].reportDate" value="<s:property value='reportDate'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].reportYear" value="<s:property value='reportYear'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].makerId" value="<s:property value='makerId'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].makerDate" value="<s:property value='makerDate'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].authoriseId" value="<s:property value='authoriseId'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].authoriseDate" value="<s:property value='authoriseDate'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d1" value="<s:property value='d1'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d2" value="<s:property value='d2'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d3" value="<s:property value='d3'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d4" value="<s:property value='d4'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d5" value="<s:property value='d5'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d6" value="<s:property value='d6'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d7" value="<s:property value='d7'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d8" value="<s:property value='d8'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d9" value="<s:property value='d9'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d10" value="<s:property value='d10'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d11" value="<s:property value='d11'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d12" value="<s:property value='d12'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d13" value="<s:property value='d13'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d14" value="<s:property value='d14'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d15" value="<s:property value='d15'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d17" value="<s:property value='d17'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d18" value="<s:property value='d18'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d19" value="<s:property value='d19'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d20" value="<s:property value='d20'/>">  
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d35" value="<s:property value='d35'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d36" value="<s:property value='d36'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d37" value="<s:property value='d37'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d38" value="<s:property value='d38'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d39" value="<s:property value='d39'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d50" value="<s:property value='d50'/>">
                        </td>
                        <td class="txtBody">
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 

                            </s:if>
                            <s:else>                                
                                <input type="checkbox" class="myCheckBox" name="lstData[<s:property  value='%{#idxRows.index}' />].manualFlag" value="0" onclick="$(this).val(this.checked ? 1 : 0)">
                                <% customerCount += 1;%>
                            </s:else>

                        </td>                                            
                        <td class="txtBody">                            
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')">                                 
                            </s:if>
                            <s:else>                                                                
                                <%= customerCount%>

                            </s:else>                            
                        </td>
                        <td class="txtBody"><div class="<s:property value="d20"/>"><s:property value="d4"/></div></td>
                        <td class="txtBody"><div class="<s:property value="d20"/>"><s:property value="d6"/></div></td>
                        <td class="txtBody"><div class="<s:property value="d20"/>"><s:property value="d8"/></div></td>
                        <td class="txtBody"><div class="<s:property value="d20"/>"><s:property value="d10"/></div></td>
                        <td class="txtBody">
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                <s:property value="D11"/>
                            </s:if>
                            <s:else>
                                <a href="javascript:funcXuLyNo('<s:property value="d5"/>', '<s:property value="d11"/>', '<s:property value="d12"/>', '<s:property value="d35"/>', '<s:property value="d37"/>')"><s:property value="d11"/></a>
                            </s:else>
                        </td>
                        <td class="txtBody">
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                <s:property value="D12"/>
                            </s:if>
                            <s:else>
                                <a href="javascript:funcThanhVien('<s:property value="d5"/>', '<s:property value="d11"/>', '<s:property value="d12"/>')"><s:property value="d12"/></a>
                            </s:else>
                        </td>
                        <td class="txtBody">
                            <s:property value="d13"/>
                        </td>
                        <td class="txtBody"><s:property value="d14"/></td>
                        <td class="txtBody">
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                <s:property value="D16"/>
                            </s:if>
                            <s:else>
                                <input style="text-align: center" type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D16" value="<s:property value='D16'/>" class="txtPublic">
                            </s:else>
                        </td>
                        <td class="txtBody">
                            <s:property value="d15"/>
                        </td>
                        <td class="txtBody"><input type="date" name="lstData[<s:property  value='%{#idxRows.index}' />].d21" value="<s:property value='d21'/>" class="datepick <s:property value="d20"/>"></td>
                        <td class="txtBody">
                            <select class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].D22" id="lstData<s:property  value='%{#idxRows.index}' />">
                                <option value="01" <s:if test="d22.equalsIgnoreCase('01')"> selected </s:if> <s:else></s:else>>01: Có thông tin địa chỉ cụ thể</option>
                                <option value="02" <s:if test="d22.equalsIgnoreCase('02')"> selected </s:if> <s:else></s:else>>02: Không có thông tin địa chỉ cụ thể</option>
                                </select>
                            </td>
                            <td class="txtBody">
                                    <select class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d25" id="lstSubData<s:property  value='%{#idxRows.index}' />">
                                <option value="00" <s:if test="d25.equalsIgnoreCase('00')"> selected </s:if> <s:else></s:else>>00: Khách hàng bỏ đi</option>
                                <option value="01" <s:if test="d25.equalsIgnoreCase('01')"> selected </s:if> <s:else></s:else>>01: Tất toán nợ</option>
                                <option value="02" <s:if test="d25.equalsIgnoreCase('02')"> selected </s:if> <s:else></s:else>>02: Xoá nợ</option>
                                <option value="03" <s:if test="d25.equalsIgnoreCase('03')"> selected </s:if> <s:else></s:else>>03: Trở về địa phương</option>
                                </select></td>
                            <td class="txtBody">
                                    <textarea  placeholder="Nhập tối đa 200 ký tự" id="lstData[<s:property  value='%{#idxRows.index}' />].D23" name="lstData[<s:property  value='%{#idxRows.index}' />].d23" class="autoHeight <s:property value="d20"/>"><s:property value='d23'/></textarea>
                        </td>
                        <td class="txtBody">
                            <select onchange="onSelectChange_dnht(this.value, <s:property  value='%{#idxRows.index}'/>)" class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d33" id="lstSubData<s:property  value='%{#idxRows.index}' />">
                                <option value="0" <s:if test="d33.equalsIgnoreCase('0')"> selected </s:if> <s:else></s:else>>0: Không đề nghị hỗ trợ</option>
                                <option value="1" <s:if test="d33.equalsIgnoreCase('1')"> selected </s:if> <s:else></s:else>>1: Đề nghị hỗ trợ</option>

                                </select></td>
                            <td class="txtBody">
                                    <select onchange="onSelectChange(<s:property  value='%{#idxRows.index}'/>)" class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d30" id="lstData_D30<s:property  value='%{#idxRows.index}' />">
                                <option value="000000">Không xác định</option>
                                <s:iterator value="lstCN" status="ideRows" var="language">
                                    <s:if test="%{#language.PosCode == d30}">
                                        <option value="<s:property value="PosCode"/>" selected><s:property value="PosName"/></option>
                                    </s:if>
                                    <s:else>
                                        <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>
                                    </s:else>
                                </s:iterator>
                                <option value="999999">00 - Nước ngoài</option>
                            </select>
                        </td>
                        <td>
                            <select class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d32" id="lstData_D32<s:property  value='%{#idxRows.index}' />">
                                <option value="000000">Không xác định</option>
                                <s:iterator value="lstPGD" status="ideRows" var="language">
                                    <s:if test="%{#language.PosCode == d32}">
                                        <option value="<s:property value="PosCode"/>" selected><s:property value="PosName"/></option>
                                    </s:if>
                                    <s:else>
                                        <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>
                                    </s:else>
                                </s:iterator>
                                <option value="999999">00 - Nước ngoài</option>
                            </select>
                        </td>

                        <td class="txtBody">
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                <s:property value="d16"/>
                            </s:if>
                            <s:else>
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d38" value="<s:property value='d38'/>" id="lstData38<s:property  value='%{#idxRows.index}' />" class="txtPublic" disabled>
                            </s:else>
                        </td>

                        <td class="txtBody">
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                <s:property value="d16"/>
                            </s:if>
                            <s:else>
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d39" value="<s:property value='d39'/>" id="lstData39<s:property  value='%{#idxRows.index}' />" class="txtPublic" disabled>
                            </s:else>
                        </td>	
                        <td class="txtBody">
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                <s:property value="d16"/>
                            </s:if>
                            <s:else>
                                <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d40" value="<s:property value='d40'/>" id="lstData40<s:property  value='%{#idxRows.index}' />" class="txtPublic" disabled>
                            </s:else>
                        </td>	


                        <td class="txtBody">
                            <select class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d34" id="lstSubData<s:property  value='%{#idxRows.index}' />">
                                <option value="0" <s:if test="d34.equalsIgnoreCase('0')"> selected </s:if> <s:else></s:else>>00: Chưa rà soát</option>
                                <option value="1" <s:if test="d34.equalsIgnoreCase('1')"> selected </s:if> <s:else></s:else>>01: Khách hàng cam kết thực hiện nghĩa vụ trả nợ</option>
                                <option value="2" <s:if test="d34.equalsIgnoreCase('2')"> selected </s:if> <s:else></s:else>>02: Khách hàng thuộc đối tượng xử lý nợ bị rủi ro</option>
                                <option value="3" <s:if test="d34.equalsIgnoreCase('3')"> selected </s:if> <s:else></s:else>>03: Khách hàng chây ỳ</option>
                                <option value="4" <s:if test="d34.equalsIgnoreCase('4')"> selected </s:if> <s:else></s:else>>04: Không liên hệ được với khách hàng</option>
                                <option value="5" <s:if test="d34.equalsIgnoreCase('5')"> selected </s:if> <s:else></s:else>>05: Khách hàng cam kết thực hiện nghĩa vụ trả nợ</option>

                                </select></td>
                            <td class="txtBody">
                                    <select class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].D24" id="lstData<s:property  value='%{#idxRows.index}' />">
                                <option value="01" <s:if test="d24.equalsIgnoreCase('01')"> selected </s:if> <s:else></s:else>>01: Cam kết</option>
                                <option value="02" <s:if test="d24.equalsIgnoreCase('02')"> selected </s:if> <s:else></s:else>>02: Không liên hệ được</option>
                                <option value="03" <s:if test="d24.equalsIgnoreCase('03')"> selected </s:if> <s:else></s:else>>03: Liên hệ được nhưng không cam kết</option>
                                <option value="04" <s:if test="d24.equalsIgnoreCase('04')"> selected </s:if> <s:else></s:else>>04: Liên hệ được nhưng không nhận nợ</option>
                                </select>
                            </td>
                            <td class="txtBody">
                                    <select class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d31" id="lstSubData<s:property  value='%{#idxRows.index}' />">
                                <option value="0" <s:if test="d31.equalsIgnoreCase('0')"> selected </s:if> <s:else></s:else>>0: Không</option>
                                <option value="1" <s:if test="d31.equalsIgnoreCase('1')"> selected </s:if> <s:else></s:else>>1: Có</option>
                                </select></td>
                            <td class="txtBody">
                                    <textarea  placeholder="Nhập tối đa 200 ký tự" id="lstData[<s:property  value='%{#idxRows.index}' />].D27" name="lstData[<s:property  value='%{#idxRows.index}' />].d27" class="autoHeight <s:property value="d20"/>"><s:property value='d27'/></textarea>
                        </td>
                        <td class="txtBody"><input type="date" name="lstData[<s:property  value='%{#idxRows.index}' />].d26" value="<s:property value='d26'/>" class="datepick <s:property value="d20"/>">
                            <input type="hidden" name="lstData[<s:property  value='%{#idxRows.index}' />].d29" value="1" class="datepick <s:property value="d20"/>"></td>

                    </tr>
                </s:iterator>
            </tbody>
        </table>
        </br>
        <s:if test="showFlag1.equalsIgnoreCase('0')">   
            <%--<s:property value="showFlag1"/>--%>
            <div id="cust_sub">
                <table id="subTable" style="z-index: 1;">
                    <tr>

                        <th class="hdtitle">STT</th>
                        <th class="hdtitle">Tên chi nhánh</th>
                        <th class="hdtitle">Tên PGD</th>
                        <th class="hdtitle">Tên xã</th>
                        <th class="hdtitle">Tên tổ trưởng</th>
                        <th class="hdtitle">Mã KH (tình hình xử lý nợ)</th>
                        <th class="hdtitle">Tên KH vay vốn</th>
                        <th class="hdtitle">Năm sinh</th>
                        <th class="hdtitle">CMT/CCCD</th>
                        <th class="hdtitle">Số điện thoại</th>

                    </tr>

                    <s:iterator value="lstData" status="idxRows">

                    </s:iterator>
                </table>         
                </br>     
            </div>
        </s:if>
        </br>     

    </div>
</body>
<script>
    $(function () {
        $('#select-all').click(function (event) {
            if (this.checked) {
                // Iterate each checkbox
                $('.myCheckBox').each(function () {
                    this.checked = true;
                    this.value = '1';
                });
            } else {
                $('.myCheckBox').each(function () {
                    this.checked = false;
                    this.value = '0';
                });
            }
        });
    });
</script>