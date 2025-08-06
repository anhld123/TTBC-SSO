<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<style>
    table.editDelete,
    table.subTable {
        border-collapse: separate;
        border-spacing: 0;
        width: 80%;
        margin: 0 5px 0 5px;
        font-family: Arial, sans-serif;
        border-radius: 5px;
        overflow: hidden;
        box-shadow: 0 1px 6px rgba(0, 0, 0, 0.05);
        /*font-size: 1px;*/
    }

    table.editDelete th,
    table.subTable th,
    table.editDelete td,
    table.subTable td {
        border: 1px solid #eee;
        background-color: #fff;
    }

    /* Gộp các th riêng */
    table.editDelete th,
    table.subTable th {
        background-color: #eef6ff;
        color: #000;
        font-weight: bold;
    }

    /* Gộp hàng chẵn */
    table.editDelete tr:nth-child(even),
    table.subTable tr:nth-child(even) {
        background-color: #fafafa;
    }

    table.editDelete tr:hover,
    table.subTable tr:hover {
        background-color: #eef6ff;
    }

    table.editDelete td.number,
    table.subTable td.number {
        color: #333;
        font-weight: 500;
    }
    .custom-scroll {
        overflow: scroll;
        width: auto;
        height: 500px;
        scrollbar-width: thin; /* Firefox */
        scrollbar-color: rgba(128, 128, 128, 0.3) transparent; /* Firefox */
    }

    /* Webkit (Chrome, Edge, Safari) */
    .custom-scroll::-webkit-scrollbar {
        width: 8px;
    }

    .custom-scroll::-webkit-scrollbar-track {
        background: transparent;
    }

    .custom-scroll::-webkit-scrollbar-thumb {
        background-color: rgba(128, 128, 128, 0.3);
        border-radius: 4px;
    }
    .custom-scroll::-webkit-scrollbar-thumb:hover {
        background-color: rgba(128, 128, 128, 0.5);
    }
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var popWindow;
            var max_row = 0;
            $(document).ready(function () {
                initTable();
            });
            $(document).ready(function () {
                $('.sstyle').css({"color": "#000", "font-size": "12px"});
                $('.style_h').css({"width:": "99%", "background-color": "rgba(255, 255, 255, 0.3)", "border": "1px solid #ccc"});
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.D00').css({"text-align": "left"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0).css({"text-align": "right"});
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".STT1").css({"width": "50px"});
                $(".STT2").css({"width": "70px"});
                $(".STT3").css({"width": "100px"});
                $(".STT4").css({"width": "150px"});
                $(".STT5").css({"width": "70px"});
                $(".STT6").css({"width": "65px"});
                $(".TD_NGUYENGIA").css({"width": "80px"});
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "220px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            function initTable() {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                var lstCN = document.getElementById("lstCN").value;
                var lstPGD = document.getElementById("lstPGD");
                if (lstCN === "0")
                {
                    lstPGD.disabled = true;
                } else
                {
                    lstPGD.disabled = false;
                    onSelectChange();
                    document.getElementById("lstPGD").value = document.getElementById("D2").value
                }

            }
        </script>        
    </head>
    <body>
        <div class="custom-scroll">   

            <div id="divTitle" style="margin: 10px 0 10px 0">
                ĐỀ NGHỊ CHI NHÁNH, PHÒNG GIAO DỊCH KHÁC HỖ TRỢ
            </div>
            <table border="1" class="editDelete" align="center" style="width: 60%">
                <tr>
                    <th>Mã khách hàng</th>
                    <th>Tên khách hàng</th>
                    <th>Số món vay</th>
                    <th>Chi nhánh đề nghị hỗ trợ</th>
                    <th>Phòng giao dịch đề nghị hỗ trợ</th>
                    <th>Trạng thái</th>
                    <th>Phản hồi</th>
                </tr>
                <tr>   
                    <s:iterator value="#attr.lstDulieuNtPLN_T" status="rowstatus">
                        <s:if test="#rowstatus.first">
                        <input type="hidden" value="<s:property  value="D2" />" id="D2"/>                             

                        <td class="D0"><s:property  value="plnMakh" /></td>
                        <td class="D0"><s:property  value="plnTenkh" /></td>
                        <td class="D0"><s:property  value="tong_monvay" /></td>
                        <td> 
                            <select  name="lstDulieuNtPLN_T[<s:property  value='%{#rowstatus.index}' />].D1" style="width: 150px"
                                     onchange="onSelectChange()" id="lstCN"
                                     <s:if test="D7.equalsIgnoreCase('1')||D7.equalsIgnoreCase('2')">onmousedown="return false"</s:if>>
                                         <option value="0" style="text-align: center">----Chọn----</option>
                                     <s:iterator value="lstCN_API" status="ideRows" var="language">
                                         <s:if test="%{#language.branchCode == D1}">
                                             <option value="<s:property value="branchCode"/>" selected><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>
                                         </s:if>
                                         <s:else>
                                             <option value="<s:property value="branchCode"/>"><s:property value="provinceCode"/> - <s:property value="provinceName"/></option>
                                         </s:else>
                                     </s:iterator>

                            </select>
                        </td>
                        <td>
                            <select name="lstDulieuNtPLN_T[<s:property  value='%{#rowstatus.index}' />].D2" style="width: 200px" id="lstPGD"
                                    <s:if test="D7.equalsIgnoreCase('1')||D7.equalsIgnoreCase('2')">onmousedown="return false"</s:if>>
                                        <option value="0" style="text-align: center">----Chọn----</option>
                                    <s:iterator value="lstPGD_API" status="ideRows" var="language">
                                        <s:if test="%{#language.PosCode == D2}">
                                            <option value="<s:property value="PosCode"/>" selected><s:property value="PosCode"/> - <s:property value="PosName"/></option>
                                        </s:if>
                                        <s:else>
                                            <option value="<s:property value="PosCode"/>"><s:property value="PosCode"/> - <s:property value="PosName"/></option>
                                        </s:else>
                                    </s:iterator>                                
                            </select>
                            <select id="lstPGD_Temp" style="display: none;">
                                <s:iterator value="lstPGD_API" var="pgd">
                                    <option value="<s:property value='PosCode'/>"
                                            data-mainpos="<s:property value='mainPos'/>">
                                        <s:property value="PosCode"/> - <s:property value="PosName"/>
                                    </option>
                                </s:iterator>
                            </select> 
                        </td>
                        <td class="D0">
                            <s:if test="D7.equalsIgnoreCase('1')||D7.equalsIgnoreCase('2')">...</s:if>
                            <s:else>
                                <a style="text-decoration: underline" href="#" onclick="idSend('<s:property value="plnMapgd"/>', '<s:property value="plnMakh"/>', <s:property value="plnNgaybc"/>, document.getElementById('lstCN').value, document.getElementById('lstPGD').value)">Đề nghị hỗ trợ</a>
                            </s:else>
                        </td>
                        <td class="D0"><s:if test="D7.equalsIgnoreCase('1')">
                                Đang gửi hỗ trợ    
                            </s:if>
                            <s:elseif test="D7.equalsIgnoreCase('2')">
                                Đã hỗ trợ
                            </s:elseif>
                            <s:else>...</s:else>
                            </td>

                    </s:if>
                </s:iterator>
                </tr>
            </table>
            <div id="divTitle" style="margin: 10px 0 10px 0">
                SỐ LIỆU ĐỐI CHIẾU, PHÂN LOẠI NỢ
            </div> 

            <table border="1" class="editDelete" id="subTable" align="center">
                <tr>
                    <th rowspan="3"><input type="checkbox" id ="select-all"/></th>
                    <th rowspan="3" class="STT3">Tên khách hàng</th>
                    <th rowspan="3" class="STT3">Mã món vay</th>
                    <th rowspan="3" class="STT3">Chương trình</th>
                    <th colspan="5">Số liệu tại NHCSXH</th> 
                    <th colspan="4">Phân loại khả năng trả nợ</th> 
                </tr>
                <tr>
                    <th colspan="4" class="STT2">Nợ gốc</th> 
                    <th rowspan="2" class="STT2">Nợ lãi</th> 
                    <th rowspan="2" class="STT3">Có khả năng trả nợ</th> 
                    <th colspan="3">Không có khả năng trả nợ</th>
                </tr>
                <tr>
                    <th class="STT2">Tổng số</th>
                    <th class="STT2">Nợ trong hạn</th>
                    <th class="STT2">Nợ quá hạn</th>
                    <th class="STT2">Nợ khoanh</th>
                    <th class="STT3">Số tiền</th>
                    <th>Nguyên nhân</th>
                    <th class="STT4">Cụ thể nguyên nhân</th>

                </tr>
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(7)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(8)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(9)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(10)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(11)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(12)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(13)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNtPLN_T" var="modelView" status="rowstatus">
                    <tr>
                        <td class="D0"> <input type="checkbox" class="myCheckBox" 
                                               name="lstDulieuNtPLN_T[<s:property  value='%{#rowstatus.index}' />].checkrow"
                                               id="checkrow_<s:property value="%{#rowstatus.index}" />"
                                               value="1" checked disabled/>      
                            <input type="hidden" value="<s:property  value="plnSoku" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnSoku"/>                             
                            <input type="hidden" value="<s:property  value="plnMapgd" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnMapgd"/>  
                            <input type="hidden" value="<s:property  value="plnTenkh" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnTenkh"/>  
                            <input type="hidden" value="<s:property  value="plnMakh" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnMakh"/>  
                            <input type="hidden" value="<s:property  value="plnMato" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnMato"/>  
                            <input type="hidden" value="<s:property  value="plnTongDno" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnTongDno"/>  
                            <input type="hidden" value="<s:property  value="plnDnothan" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnDnothan"/>  
                            <input type="hidden" value="<s:property  value="plnDnoqhan" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnDnoqhan"/>  
                            <input type="hidden" value="<s:property  value="plnDnokhoanh" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnDnokhoanh"/>
                            <input type="hidden" value="<s:property  value="plnTrangthai" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnTrangthai"/>  
                            <input type="hidden" value="<s:property  value="plnTonglaiton" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnTonglaiton"/>
                            <input type="hidden" value="<s:property  value="plnNogocClech" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnNogocClech"/> 
                            <input type="hidden" value="<s:property  value="plnNolaiClech" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnNolaiClech"/>
                            <input type="hidden" value="<s:property  value="plnNgnhanClech" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnNgnhanClech"/> 
                            <input type="hidden" value="<s:property  value="plnMacn" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnMacn"/> 
                            <input type="hidden" value="<s:property  value="plnQuanheKh" />" name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].plnQuanheKh"/> 
                            <input type="hidden" value="<s:property  value="D6" />" 
                                   id="D6_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNtPLN_T[<s:property  value="%{#rowstatus.index}" />].D6"/>     
                        </td>
                        <td><s:property value="plnTenkh"/>
                        </td>
                        <td> 
                            <a href="javascript:hienthichitiet('<s:property value="plnSoku"/>','<s:property  value="plnNgaybc" />' ,'<s:property  value="plnMapgd" />' ,'<s:property  value="D6"/>-<s:property  value="D7"/>')" class="SOKU linkKh">
                                <s:property value='plnSoku'/>
                            </a>
                        </td>
                        <td><s:property value="plnChtrinhTenvt"/> </td>
                        <td class="number style_h" id="TongDno_<s:property value='%{#rowstatus.index}' />">
                            <s:property value="plnTongDno" /></td>
                        <td class="number style_h"><s:property value="plnDnothan"/> </td>
                        <td class="number style_h"><s:property value="plnDnoqhan"/> </td>
                        <td class="number style_h" id="Dnokhoanh_<s:property value='%{#rowstatus.index}' />">
                            <s:property value="plnDnokhoanh"/> </td>
                        <td class="number style_h"><s:property value="plnTonglaiton"/> </td>
                        <!--chi tieu nhap tay tu day--> 
                        <td class="number style_h"><s:property value="plnCKntnSodu"/> </td>
                        <td class="number style_h"><s:property value="D3"/> </td>
                        <td class="D0">    
                            <select id='D3_<s:property value="%{#rowstatus.index}" />' style="width: 150px"
                                    onmousedown="return false"
                                    name='lstDulieuNtPLN_T[<s:property value="%{#rowstatus.index}" />].D10'
                                    onchange="ngnhanKntn('D3', <s:property value='%{#rowstatus.index}' />)">
                                <option value="0" style="text-align: center">----Chọn----</option>
                                <s:iterator value="lstDmKhac106" status="ideRows" var="language">
                                    <option value="<s:property value="code" />"
                                            <s:if test="%{#language.code == D10}">selected</s:if>>
                                        <s:property value="code" /> - <s:property value="value" />
                                    </option>
                                </s:iterator>
                            </select>
                        </td>  
                        <td style="width: 200px"><s:property value="D5"/></td>

                    </tr>

                </s:iterator>

            </table>
        </div>
        <div id="luu_thanhcong"></div>
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
            function hienthichitiet(soku, ngay_bc, poscd, lock) {
                var ht1 = screen.availHeight - 200;
                var wt1 = 1024;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 100;
                var url = "getDetialLoanDcPLN.action?soku=" + soku + "&ngay_bc=" + ngay_bc + "&poscd=" + poscd + "&lock=" + lock;
                popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }

            function onSelectChange() {
                let selectedValue = $('#lstCN').val();
                let province = selectedValue;

                const pgdSelect = $('#lstPGD');

                pgdSelect.find('option:not(:first)').remove();

                if (province === "0") {
                    pgdSelect.prop('disabled', true).css('background-color', '#e9ecef');
                    return;
                }

                $('#lstPGD_Temp option').each(function () {
                    if ($(this).data('mainpos') === province) {
                        pgdSelect.append($('<option>', {
                            value: $(this).val(),
                            text: $(this).text()
                        }));
                    }
                });

                pgdSelect.prop('disabled', false).css('background-color', '#ffffff');
            }

            function idSend(mapgd, makh, ngaybc, macn_sp, mapgd_sp) {
                console.log(mapgd);
                if (macn_sp === "0") {
                    alert("Bạn chưa chọn Chi nhánh hỗ trợ!");
                    return;
                }
                if (mapgd_sp === "0") {
                    alert("Bạn chưa chọn Phòng giao dịch hỗ trợ!");
                    return;
                }
                if (mapgd_sp === mapgd) {
                    alert("Không thể chọn PGD cho vay để hỗ trợ!");
                    return;
                }

                var url, sdata;
                url = "sendSupportDcPln.action?" +
                        "mapgd=" + mapgd +
                        "&makh=" + makh +
                        "&ngaybc=" + ngaybc +
                        "&macn_sp=" + macn_sp +
                        "&mapgd_sp=" + mapgd_sp;

                sdata = jQuery("#frmdata").serialize();

                $("#loadingImageDiv_data").show();
                $("#viewData").html('<img src="img/loading.gif"/>');

                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        if (data === "200") {
                            alert("Đề nghị hỗ trợ thành công, chờ phản hồi từ đơn vị hỗ trợ!");
                            onLoadData();
                        } else {
                            alert("Lỗi: Đề nghị lỗi.");
                            onLoadData();
                        }
                    },
                    error: function (request) {
                        alert("Lỗi. Vui lòng liên hệ quản trị viên để được hỗ trợ!");
                        onLoadData();
                    }
                });
            }


        </script>

    </body>
</html>
