<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/css2025.css" />
<link rel="stylesheet" type="text/css"  href="htls_nguondp2025/css.css" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="htls_nguondp2025/jquery.js"></script>     
    </head>
    <body>
        <div style="overflow:scroll; width: 98%;height: 40vw;">    
            <div style="height: 10px"></div>
            <div id="divTitle">
                XÁC NHẬN KHOẢN VAY CÓ THAY ĐỔI LÃI SUẤT
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(TW đã khóa nhập dữ liệu)</a></s:if>
                <s:elseif test="chotCic.equalsIgnoreCase('2')" ><a class="color_11">(CN đã gửi dữ liệu)</a></s:elseif>
                <s:elseif test="chotCic.equalsIgnoreCase('1')" ><a class="color_11">(PGD đã chốt dữ liệu)</a></s:elseif>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
                <input type="hidden" value="<s:property value="chotCic"/>" name="chotcic" id="chotcic"/> 
            </div>
            <div style="height: 10px"></div>
            <s:if test="stype.equalsIgnoreCase('0')||stype.equalsIgnoreCase('1')">
                <table border="1" class="editDelete" align="center" style="width: 60%">
                    <tr>
                        <th rowspan="3" class="STT2">Tổng cộng</th>
                        <th rowspan="2">Tổng số KH</th>
                        <th rowspan="2">Tổng số món vay</th>
                        <th rowspan="2">Tổng dư nợ</th>
                        <th colspan="3">Dư nợ</th>
                    </tr>

                    <tr>
                        <th>Trong hạn</th>
                        <th>Quá hạn</th>
                        <th>Khoanh</th>

                    </tr>
                    <tr>   
                        <td class="number STT2"><s:property value="tong_kh"/> </td>
                        <td class="number STT2"><s:property value="tong_monvay"/> </td>
                        <td class="number STT3 style_h"><s:property value="tong_duno"/> </td>
                        <td class="number STT3 style_h"><s:property value="tong_than"/> </td>
                        <td class="number STT3 style_h"><s:property value="tong_qhan"/> </td>
                        <td class="number STT3"><s:property value="tong_khoanh"/> </td>
                    </tr>
                </table> 
            </s:if>
            <s:else>
                <table border="1" class="editDelete" align="center" style="width: 60%">
                    <tr>
                        <th rowspan="3" class="STT2">Tổng cộng</th>
                        <th rowspan="2">Tổng số KH</th>
                        <th rowspan="2">Tổng số món vay</th>
                        <th rowspan="2">Tổng dư nợ</th>
                        <th colspan="3">Dư nợ</th>
                        <th colspan="2">Đơn vị nhập lãi giảm</th>
                    </tr>

                    <tr>
                        <th>Trong hạn</th>
                        <th>Quá hạn</th>
                        <th>Khoanh</th>
                        <th style="color: red">Số món chưa tích lãi giảm</th>
                        <th style="color: blue">Số món dã tích lãi giảm</th>

                    </tr>
                    <tr>   
                        <td class="number STT2"><s:property value="tong_kh"/> </td>
                        <td class="number STT2"><s:property value="tong_monvay"/> </td>
                        <td class="number STT3 style_h"><s:property value="tong_duno"/> </td>
                        <td class="number STT3 style_h"><s:property value="tong_than"/> </td>
                        <td class="number STT3 style_h"><s:property value="tong_qhan"/> </td>
                        <td class="number STT3"><s:property value="tong_khoanh"/> </td>
                        <td class="number STT3"><s:property value="lai_nhap"/> </td>
                        <td class="number STT3"><s:property value="lai_xnhan"/> </td>
                    </tr>
                </table>
            </s:else>
            <br>
            <div class="myTableWrapper" id="divDonvitinh">
                <button id="btn_prev" class="pagination-btn" onclick="prevPage()">Trước</button>
                <span id="page">1/1</span>

                <button id="btn_next" class="pagination-btn" onclick="nextPage()">Sau</button>

                | Tới trang: 
                <input type="text" id="pageInput" />
                <button class="pagination-btn" onclick="goToPage()">Đi</button>
                <span style="margin-left: 30px">Tra cứu: </span>
                <input type="text" id="search" placeholder="Tìm kiếm ..." style="width: 200px" />
                <br>
                Đơn vị tính: Đồng,%.
            </div>

            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr>
                    <th rowspan="2" style="width: 50px">STT</th>
                    <th rowspan="2" class="STT2">Mã khách hàng</th>
                    <th rowspan="2" class="STT3">Họ và tên khách hàng</th>
                    <th rowspan="2" class="STT3">Mã khoản vay</th>
                    <th rowspan="2" class="STT2">Chương trình tín dụng</th>

                    <th rowspan="2" class="STT2">Tình trạng món vay</th>
                    <th colspan="3" >Dư nợ</th>
                    <th rowspan="2" class="STT2" style="color: red">Lãi suất ban đầu</th>
                    <th rowspan="2" class="STT2">Đơn vị xác nhận(Có/Không)</th>
                    <th rowspan="2" class="STT3" style="color: blue">Lãi suất giảm</th>

                    <th rowspan="2" class="STT2">Cập nhật</th>
                </tr>
                <tr>
                    <th class="STT3">Trong hạn</th>
                    <th class="STT3">Quá hạn</th>
                    <th class="STT3">Khoanh</th>
                </tr>
                <tr>
                    <th class="D99">(1)</th>
                    <th class="D99">(2)</th>
                    <th class="D99">(3)</th>
                    <th class="D99">(4)</th>
                    <th class="D99">(5)</th>
                    <th class="D99">(6)</th>
                    <th class="D99">(7)</th>
                    <th class="D99">(8)</th>
                    <th class="D99">(9)</th>
                    <th class="D99">(10)</th>
                    <th><input type="checkbox" id ="select-all1"/></th>
                    <th class="D99">(11)</th>
                    <!--<th style="color: #000; font-style: italic; font-size: xx-small;">(12)</th>-->

                    <th><input type="checkbox" id ="select-all"/></th>  
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" />
                            <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                            <input type="hidden" value="<s:property value="%{#rowstatus.index + 1}" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                            <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>  
                            <input type="hidden" value="<s:property  value="MAPGD" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>
                            <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>
                            <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                            <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>
                            <input type="hidden" value="<s:property  value="D2" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2"/>
                            <input type="hidden" value="<s:property  value="D3" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                            <input type="hidden" value="<s:property  value="D4" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"/>
                            <input type="hidden" value="<s:property  value="D5" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"/>
                            <input type="hidden" value="<s:property  value="D6" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6"/>
                            <input type="hidden" value="<s:property  value="D7" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7"/>
                            <input type="hidden" value="<s:property  value="D8" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"/>
                            <input type="hidden" value="<s:property  value="D15" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/>
                            <input type="hidden" value="<s:property  value="D17" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17"/>
                            <input type="hidden" value="<s:property  value="D21" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21"/>
                            <input type="hidden" value="<s:property  value="D22" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22"/>
                        </td>
                        <td class="D0"><s:property  value="D1" /></td>
                        <td><s:property  value="D2" /></td>

                        <td class="D0"><s:property  value="D3" /></td>
                        <td><s:property  value="D10" /> - <s:property  value="D23" /></td>

                        <td class="D0"><s:property  value="D13" /></td>
                        <td class="number"><s:property  value="D14" /></td>
                        <td class="number"><s:property  value="D15" /></td>
                        <td class="number"><s:property  value="D16" /></td>
                        <!--                        <td>
                                                    <input type="text" value="<s:property value="D21" />" 
                                                           id="D21_<s:property value='%{#rowstatus.index}' />" 
                                                           name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D21" 
                                                           class="number2" style="background: yellow"/>
                                                </td>-->
                        <td style="color: red; text-align: right" id="D12_<s:property value='%{#rowstatus.index}' />"><s:property  value="D12" /></td>
                        <td class="D0">
                            <input type="checkbox" id ="D19_<s:property value="%{#rowstatus.index}" />" 
                                   onclick="$(this).val(this.checked ? 1 : 0)" class="myCheckBox1"
                                   oninput="onSelectChange(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                   name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D19" value="<s:property  value="D19" />"/>      
                        </td>
                        <td>
                            <input type="text" value="<s:property value="D18" />"  
                                   id="D18_<s:property value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D18" 
                                   class="number2" style="color: blue;background: #ffcdbb"
                                   onblur="if (this.value == '') {
                                               this.value = 0
                                           }
                                           ;
                                           check(<s:property value="%{#rowstatus.index}"/>);"/>
                        </td>
                        <td class="D0">
                            <input type="checkbox" class="myCheckBox"
                                   name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D30"
                                   id="D30_<s:property value="%{#rowstatus.index}" />"
                                   value="0" onclick="$(this).val(this.checked ? 1 : 0)"/>
                        </td>
                    </s:iterator>

            </table>
        </div>
        <div id="luu_thanhcong"></div>
    </body>
</html>
