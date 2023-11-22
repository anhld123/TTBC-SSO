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
        width: 170%;
    }
    #subTable th{
        background-color: #ddd;
        color: #0000FF;
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
        width: 100px;
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
        var w = 800, h = 400;
        var left = (screen.width / 2) - (w / 2);
        var top = (screen.height / 2) - (h / 2);
        var urlParam = "vsbpMaPgd=" + maPgd + "&vsbpMakh=" + maKH + "&vsbpTenKh=" + encodeURIComponent(tenKH) + "&vsbpNgayBC=" + $("#txtNgayBc").val() + "&vbsprandom=" + Math.random();
        var url = "/IMS_REPORTS/popupThanhvien_author.action?" + urlParam;
        popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
    }

    function funcXuLyNo(maPgd, maKH, tenKH, XuLyNo) {
        var w = 1200, h = 600;
        var left = (screen.width / 2) - (w / 2);
        var top = (screen.height / 2) - (h / 2);
        var urlParam = "vsbpMaPgd=" + maPgd + "&vsbpMakh=" + maKH + "&vsbpTenKh=" + encodeURIComponent(tenKH) + "&vsbpNgayBC=" + $("#txtNgayBc").val()
                + "&XuLyNo=" + XuLyNo + "&vbsprandom=" + Math.random();
        var url = "/IMS_REPORTS/popupXuLyNo.action?" + urlParam;
        popWindow = window.open(url, "IMS_REPORTS", "width=" + w + ", height=" + h + ", top=" + top + ", left=" + left + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
    }

    function onSelectChange(index) {

        let selectedValue = $('#lstData_D30' + index).find(":selected").val();
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
                    <th rowspan="2" class="hdtitle" style="width: 100px">Mã KH</th>
                    <th rowspan="2" class="hdtitle">Tên KH vay vốn</th>
                    <th rowspan="2" class="hdtitle" style="width: 80px">Năm sinh</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">CMT/CCCD</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Số điện thoại</th>
                    <th rowspan="2" class="hdtitle">Loại đối tượng</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Thời điểm đi</th>
                    <th rowspan="2" class="hdtitle" style="width: 150px">Mã nhóm</th>
                    <th rowspan="2" class="hdtitle">Đề nghị <br>cung cấp thông tin</th>
                   
                    <th rowspan="2" class="hdtitle" style="width: 250px">Thông tin <br>(100-200 ký tự)</th>
                    <th rowspan="2"class="hdtitle">Chi nhánh hộ vay <br>chuyển đến</th>
                    <th rowspan="2" class="hdtitle">PGD hộ vay <br>chuyển đến</th>
                    <th rowspan="2" class="hdtitle" style="width: 200px" >Đề nghị hỗ trợ</th>
                    <th colspan="2" class="hdtitle" >Kết quả hỗ trợ</th>
                    <th rowspan="2" class="hdtitle">Tổ chức CT-XH rà soát</th>

                    <th rowspan="2" class="hdtitle" style="width: 250px">Thông tin hỗ trợ</th>
                    <th rowspan="2" class="hdtitle" style="width: 100px">Ngày cập nhật<br>thông tin</th>
                     <th rowspan="2" class="hdtitle"style="width: 150px" >Mã quản lý</th>
                </tr>
                <tr>
                    <th class="hdtitle" style="width: 200px">Kết quả hỗ trợ</th>
                    <th class="hdtitle" style="width: 250px">Kết quả hỗ trợ<br>(Trường hợp 5)</th>
                </tr>

            </thead>
            <tr class="txtBody">
                <th style="color: #000; font: italic; font-size: xx-small;"></th>
                <th style="color: #000; font: italic; font-size: xx-small;">(1)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(2)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(3)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(4)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(5)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(6)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(7)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(8)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(9)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(10)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(11)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(12)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(13)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(14)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(15)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(16)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(17)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(18)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(19)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(20)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(21)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(22)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(23)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(24)</th>
            </tr>
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
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d26" value="<s:property value='d26'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d27" value="<s:property value='d27'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d29" value="<s:property value='d29'/>">                            
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d36" value="<s:property value='d36'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d37" value="<s:property value='d37'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d38" value="<s:property value='d38'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d39" value="<s:property value='d39'/>">
                            <input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].d50" value="<s:property value='d50'/>">
                        </td>
                        <td class="txtBody" >
                            <s:if test="!D50.toString().equalsIgnoreCase('2')">
                                <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                </s:if>
                                <s:else> 
                                    <input type="checkbox" class="myCheckBox" name="lstData[<s:property  value='%{#idxRows.index}' />].manualFlag" value="0" onclick="$(this).val(this.checked ? 1 : 0)">
                                    <% customerCount += 1;%>
                                </s:else>
                            </s:if>
                            <s:else>
                                <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                </s:if>
                                <s:else> 
                                    <a style="color: #0000FF" title="Món đã phê duyệt">&#10003;</a>
                                    <% customerCount += 1;%>
                                </s:else>
                            </s:else>
                        </td>                                            
                        <td class="txtBody">                            
                            <s:if test="D20.equalsIgnoreCase('ThanhVien')">                                 
                            </s:if>
                            <s:else>                                                                
                                <%= customerCount%>

                            </s:else>                            
                        </td>
                        <td class="txtBody"><s:property value="d4"/></td>
                        <td class="txtBody"><s:property value="d6"/></td>
                        <td class="txtBody"><s:property value="d8"/></td>
                        <td class="txtBody"><s:property value="d10"/></td>
                        <td class="txtBody"><s:property value="d11"/></td>
                        <s:if test="D50.toString().equalsIgnoreCase('1')">
                            <td class="txtBody">
                                <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                    <s:property value="D12"/>
                                </s:if>
                                <s:else>
                                    <a href="javascript:funcThanhVien('<s:property value="d5"/>', '<s:property value="d11"/>', '<s:property value="d12"/>')"><s:property value="d12"/></a>
                                </s:else>
                            </s:if>
                            <s:else>
                                <s:if test="D20.equalsIgnoreCase('ThanhVien')"> 
                                    <s:property value="D12"/>
                                </s:if>
                                <s:else>
                                <td class="txtBody"><s:property value="d12"/></td>
                            </s:else>
                        </s:else>
                        </td>
                        <td class="txtBody"><s:property value="d13"/></td>
                        <td class="txtBody"><s:property value="d14"/></td>
                        <td class="txtBody"><s:property value="d16"/></td>
                        <td class="txtBody"><s:property value="d15"/></td>
                        <td class="txtBody"><s:property value="d21"/></td>
                        <td class="txtBody">
                            <s:if test="d22.equalsIgnoreCase('01')"><a>Có thông tin địa chỉ cụ thể</a></s:if>
                            <s:elseif test="d22.equalsIgnoreCase('02')"><a>Không có thông tin địa chỉ cụ thể</a></s:elseif>
                            </td>
                            <td class="txtBody">
                            <s:if test="!d31.equalsIgnoreCase('1')"><a>Không</a></s:if>
                            <s:elseif test="d31.equalsIgnoreCase('1')"><a>Có</a></s:elseif>
                            </td>
                            <td class="txtBody"><s:property value="d23"/></td>                       
                        <td class="txtBody">
                            <select onmousedown="return false" onchange="onSelectChange(<s:property  value='%{#idxRows.index}'/>)" class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d30" id="lstData_D30<s:property  value='%{#idxRows.index}' />">
                                <option value="000000">Không xác định</option>
                                <s:iterator value="lstCN" status="ideRows" var="language">
                                    <s:if test="%{#language.PosCode == d30}">
                                        <option value="<s:property value="PosCode"/>" selected><s:property value="PosName"/></option>
                                    </s:if>
                                    <s:else>
                                        <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>
                                    </s:else>
                                </s:iterator>
                                <option value="999999">Nước ngoài</option>
                            </select>
                        </td>
                        <td>
                            <select onmousedown="return false" class=" <s:property value="d20"/>" name="lstData[<s:property  value='%{#idxRows.index}' />].d32" id="lstData_D32<s:property  value='%{#idxRows.index}' />">
                                <option value="000000">Không xác định</option>
                                <s:iterator value="lstPGD" status="ideRows" var="language">
                                    <s:if test="%{#language.PosCode == d32}">
                                        <option value="<s:property value="PosCode"/>" selected><s:property value="PosName"/></option>
                                    </s:if>
                                    <s:else>
                                        <option value="<s:property value="PosCode"/>"><s:property value="PosName"/></option>
                                    </s:else>
                                </s:iterator>
                                <option value="999999">Nước ngoài</option>
                            </select>
                        </td>  
                        <td class="txtBody">
                            <s:if test="!d33.equalsIgnoreCase('1')"><a>Không đề nghị hỗ trợ</a></s:if>
                            <s:elseif test="d33.equalsIgnoreCase('1')"><a>Đề nghị hỗ trợ</a></s:elseif>
                            </td>
                            <td class="txtBody">
                            <s:if test="d34.equalsIgnoreCase('0')"><a style="color: red">Chưa rà soát</a></s:if>
                            <s:elseif test="d34.equalsIgnoreCase('1')"><a>Khách hàng cam kết thực hiện nghĩa vụ trả nợ</a></s:elseif>
                            <s:elseif test="d34.equalsIgnoreCase('2')"><a>Khách hàng thuộc đối tượng xử lý nợ bị rủi ro</a></s:elseif>
                            <s:elseif test="d34.equalsIgnoreCase('3')"><a>Khách hàng chây ỳ</a></s:elseif>
                            <s:elseif test="d34.equalsIgnoreCase('4')"><a>Không liên hệ được với khách hàng</a></s:elseif>
                            <s:elseif test="d34.equalsIgnoreCase('5')"><a>Khách hàng không nhận nợ, không nhận nợ một phần, không/chưa cam kết trả nợ hoặc trường hợp khác...</a></s:elseif> 
                            </td>
                            <td class="txtBody"><s:property value="d41"/></td>        
                        <td class="txtBody">
                            <s:if test="d24.equalsIgnoreCase('00')"><a style="color: red">Không rà soát</a></s:if>
                            <s:elseif test="d24.equalsIgnoreCase('01')"><a>Cam kết</a></s:elseif>
                            <s:elseif test="d24.equalsIgnoreCase('02')"><a>Không liên hệ được</a></s:elseif>
                            <s:elseif test="d24.equalsIgnoreCase('03')"><a>Liên hệ được nhưng không cam kết</a></s:elseif>
                            <s:elseif test="d24.equalsIgnoreCase('04')"><a>Liên hệ được nhưng không nhận nợ</a></s:elseif>
                            </td>
                        <td class="txtBody"><s:property value="d27"/></td> 
                        <td class="txtBody"><s:property value="d26"/></td>
                        <td class="txtBody">
                            <s:if test="d25.equalsIgnoreCase('00')"><a>Khách hàng bỏ đi</a></s:if>
                            <s:elseif test="d25.equalsIgnoreCase('01')"><a>Tất toán nợ</a></s:elseif>
                            <s:elseif test="d25.equalsIgnoreCase('02')"><a>Xóa nợ</a></s:elseif>
                            <s:elseif test="d25.equalsIgnoreCase('03')"><a>Trở về địa phương</a></s:elseif>
                            </td>
                </s:iterator>
            </tbody>
        </table>
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