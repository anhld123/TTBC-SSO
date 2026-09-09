<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
        <link href="quanly_nokhoanh/css-quanly-nokhoanh.css" type="text/css" rel="stylesheet" />
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script type="text/javascript" src="quanly_nokhoanh/js-quanly-nokhoanh.js"></script>
    </head>

    <body>
        <div style="overflow:scroll; width: 98vw;height: 40vw;">             
            <div id="divTitle">
                <s:if test="txtGetData.equalsIgnoreCase('0')">
                    QUẢN LÝ NỢ KHOANH 
                    <!--<font id="totalRowsFont" style="color: red"></font><font style="color: red">/<s:property value="messagePage"/>)</font>-->
                </s:if>
                <s:else>
                    DANH SÁCH MÓN NỢ KHOANH 
                    <!--<font id="totalRowsFont" style="color: red"></font><font style="color: red">/<s:property value="messagePage"/>)</font>-->    
                </s:else>
            </div>
            Chọn trang <input style="border-top-style: hidden; border-left-style: hidden; border-right-style: hidden " class="STT1" type="number" id="pageInput" min="1" max="numPages()"/>
            <a onclick="goToPage()" href='#' id ="btn_go">Go</a>
            <a onclick="prevPage()" href='#' id="btn_prev">&#8920;</a> 
            Trang <span id="page"></span>
            <a onclick="nextPage()" href='#' id="btn_next">&#8921;</a>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th  rowspan="3" class="D0 STT1 ">
                        <input type="checkbox" id ="select-all"/>
                    </th> 
                    <!--<th rowspan="3" class="STT1">Phê duyệt</th>--> 
                    <th rowspan="3" class="STT1">S<br>T<br>T</th>                           
                    <th rowspan="3" class="STT4">Họ và tên</th>  
                    <th rowspan="3" class="STT2">Mã món vay</th>  
                    <th colspan="5">PHẦN THEO DÕI TẠI NGÂN HÀNG</th>
                    <th colspan="10" style="color: #ff6600">PHẦN KIỂM TRA THỰC TẾ TẠI KHÁCH HÀNG</th>
                    <th rowspan="3" class="STT2">Nguyên nhân chênh lệch</th>      
                    <th rowspan="3" class="STT5">Ký xác nhận của khách hàng</th> 
                </tr>         
                <tr >
                    <th rowspan="2" class="STT5">Dư nợ gốc</th>  
                    <th rowspan="2" class="STT5">Dư gốc khoanh</th>    
                    <th rowspan="2" class="STT5">Số tiền lãi <br>còn nợ NH</th>                             
                    <th rowspan="2" class="STT5">Ngày <br>bắt đầu khoanh nợ</th>   
                    <th rowspan="2" class="STT5">Ngày <br>hết hạn khoanh nợ</th> 
                    <th rowspan="2" class="STT5" style="color: #ff6600">Dư nợ gốc</th>  
                    <th rowspan="2" class="STT5" style="color: #ff6600">Dư gốc khoanh</th>  
                    <th rowspan="2" class="STT5" style="color: #ff6600">Số tiền lãi <br>còn nợ NH</th>     
                    <th rowspan="2" class="STT5" style="color: #ff6600">Thực trạng dự án phương án vay vốn</th>                             
                    <th rowspan="2" class="STT5" style="color: #ff6600">Tình hình thực tế của khách hàng</th>   
                    <th rowspan="2" class="STT5" style="color: #ff6600">Khả năng trả nợ của khách hàng</th> 
                    <th rowspan="2" class="STT5" style="color: #ff6600">Khách hàng cam kết trả nợ</th>
                    <th colspan="3" class="STT5" style="color: #ff6600">Chênh lệch</th>
                </tr>
                <tr>
                    <th rowspan="1" class="STT5" style="color: #ff6600">Dư nợ gốc</th>  
                    <th rowspan="1" class="STT5" style="color: #ff6600">Dư gốc khoanh</th>
                    <th rowspan="1" class="STT5" style="color: #ff6600">Số tiền lãi <br>còn nợ NH</th> 
                </tr>
                <tr class="stt-header-row">
                    <th></th>
                        <% for (int i = 1; i <= 20; i++) {%>
                    <th>(<%=i%>)</th>
                        <% }%>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0">
                            <s:if test="!txtGetData.equalsIgnoreCase('1')"> 
                                <input id="check<s:property  value='%{#rowstatus.index}' />" type="checkbox" class="myCheckBox sstyle"
                                       title="Ngày nhập <s:property  value="NGAYBC" />, chọn về tháng của ngày nhập nếu muốn xoá món vay"
                                       name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D18"/>   
                            </s:if>
                            <s:else>
                                <input id="check<s:property  value='%{#rowstatus.index}' />" type="checkbox" class="myCheckBox sstyle"
                                       name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D18"/>   
                            </s:else>
                        </td>

                        <td class="D0 STT1 sstyle"> <s:property value="%{#rowstatus.index + 1}" /> 
                            <input type="hidden" value="<s:property  value="MACN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN"/>                             
                            <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                            <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>
                            <input type="hidden" value="<s:property  value="D19" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19"
                                   id="D19_<s:property  value='%{#rowstatus.index}' />"/>
                            <input type="hidden" value="<s:property  value="D20" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20"/>
                            <input type="hidden" value="<s:property  value="D21" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21"/>
                            <input type="hidden" value="<s:property  value="D22" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22"/>
                            <input type="hidden" value="<s:property  value="D23" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23"/>
                            <input type="hidden" value="<s:property  value="D24" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24"/>
                            <input type="hidden" value="<s:property  value="D25" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25"/>
                            <!--<input type="hidden" value="<s:property  value="D29" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D29"/>-->
                            <input type="hidden" value="<s:property  value="D30" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30"/>
                            <input type="hidden" value="<s:property  value="NGAYBC" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGAYBC"/>
                            <input type="hidden" value="<s:property  value="D1" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"/>
                            <input type="hidden" value="<s:property  value="D2" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2"/>

                        </td>

                        <td> <s:property  value="D1" /> </td>                                  
                        <td> <s:property  value="D2" /> </td>                                  
                        <td>
                            <input type="text" value="<s:property  value="D3" />" readonly="true"
                                   id="D3_<s:property  value='%{#rowstatus.index}' />"
                                   style=" background: #ddd"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="STT5 number sstyle"/>
                        </td> 
                        <td>
                            <input type="text" value="<s:property  value="D5" />" readonly="true" style="background: #ddd"
                                   id="D5_<s:property  value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="STT5 number sstyle"/>
                        </td>
                        <td>
                            <input type="text" value="<s:property  value="D4" />" readonly="true" style="background: #ddd"
                                   id="D4_<s:property  value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="STT5 number sstyle"/>
                        </td>
                        <td class="D0">
                            <input type="text" value="<s:property  value="D6" />" readonly="true" style="background: #ddd"
                                   id="D6_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="STT6 D0 sstyle"/>
                        </td>
                        <td class="D0">
                            <input type="text" value="<s:property  value="D7" />" readonly="true" style="background: #ddd"
                                   id="D7_<s:property  value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="STT6 D0 sstyle"/>
                        </td>
                        <td class="D0">
                            <input type="text" value="<s:property  value="D8" />" style="width: 80px; background: gold"
                                   oninput="onSelectChange_dnht6(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                   id="D8_<s:property  value='%{#rowstatus.index}' />" onchange="calc(this);"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="number sstyle STT5"/>
                        </td>
                        <td class="D0">
                            <input type="text" value="<s:property  value="D9" />" style="width: 80px;background: gold"
                                   oninput="onSelectChange_dnht5(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                   id="D9_<s:property  value='%{#rowstatus.index}' />" onchange="calc1(this);" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="number sstyle STT5"/>
                        </td>
                        <td class="D0">
                            <input type="text" value="<s:property  value="D27" />" style="width: 80px;background: gold"
                                   oninput="onSelectChange_dnht4(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                   id="D27_<s:property  value='%{#rowstatus.index}' />" onchange="calc2(this);"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27" class="number sstyle STT5"/>
                        </td>
                        <td class="D0">
                            <textarea  class="STT3 sstyle" placeholder="Nhập tối đa 200 ký tự" id="D10_<s:property  value='%{#rowstatus.index}' />" 
                                       name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D10" maxlength="200"><s:property value='D10'/></textarea>
                        </td>
                        <td class="D0">
                            <textarea oninput="onSelectChange_dnht1(this.value, <s:property  value='%{#rowstatus.index}'/>)"
                                      class="STT3 sstyle" placeholder="Nhập tối đa 200 ký tự" id="D11_<s:property  value='%{#rowstatus.index}' />" 
                                      name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D11" maxlength="200"><s:property value='D11'/></textarea>
                        </td>
                        <td class="D0">
                            <select oninput="onSelectChange_dnht2(this.value, <s:property  value='%{#rowstatus.index}'/>)" style="border: hidden"
                                    class="sstyle" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D12" id="D12_<s:property  value='%{#rowstatus.index}' />" > 
                                <option value="0" style="text-align: center" <s:if test="D12.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="D12.equalsIgnoreCase('1')"> selected </s:if>>Không có khả năng trả nợ</option>
                                <option value="2" <s:if test="D12.equalsIgnoreCase('2')"> selected </s:if>>Chưa có khả năng trả nợ</option>                        
                                <option value="3" <s:if test="D12.equalsIgnoreCase('3')"> selected </s:if>>Có khả năng trả nợ</option>
                                </select>
                            </td>
                            <td class="D00">
                                <select style="border: hidden" class="sstyle" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D13" id="D13_<s:property  value='%{#rowstatus.index}' />"
                                    oninput="onSelectChange_s1(this.value, <s:property  value='%{#rowstatus.index}'/>); onSelectChange_dnht3(this.value, <s:property  value='%{#rowstatus.index}'/>)"> 
                                <option value="0" style="text-align: center" <s:if test="D13.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="D13.equalsIgnoreCase('1')"> selected </s:if>>Không cam kết</option>
                                <option value="2" <s:if test="D13.equalsIgnoreCase('2')"> selected </s:if>>Có cam kết</option>
                                </select>
                                <select style="border: hidden"
                                        class="sstyle" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D29" id="D29_<s:property  value='%{#rowstatus.index}' />" > 
                                <option value="0" style="text-align: center" <s:if test="D29.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="D29.equalsIgnoreCase('1')"> selected </s:if>>Không cam kết</option>
                                <option value="2" <s:if test="D29.equalsIgnoreCase('2')"> selected </s:if>>Thực hiện cam kết</option>
                                <option value="3" <s:if test="D29.equalsIgnoreCase('3')"> selected </s:if>>Không thực hiện cam kết</option>
                                </select>
                            </td>
                            <td class="D0">
                                <input type="text" value="<s:property  value="D14" />" id="D14_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="number sstyle STT5"
                                   readonly="true" style="background: #ddd"/>
                        </td>
                        <td class="D0">
                            <input type="text" value="<s:property  value="D15" />" id="D15_<s:property  value='%{#rowstatus.index}' />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="number sstyle STT5"
                                   readonly="true" style="background: #ddd"/>
                        </td>
                        <td class="D0">
                            <input type="text" value="<s:property  value="D28" />" id="D28_<s:property  value='%{#rowstatus.index}' />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" class="number sstyle STT5"
                                   readonly="true" style="background: #ddd"/>
                        </td>
                        <td class="D0">
                            <textarea class="STT3 sstyle"  placeholder="Nhập tối đa 200 ký tự" id="D16_<s:property  value='%{#rowstatus.index}' />" 
                                      name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D16" maxlength="200"><s:property value='D16'/></textarea>
                        </td>
                        <td class="D0">
                            <select style="border: hidden" class="sstyle" name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D17" id="D17_<s:property  value='%{#rowstatus.index}' />" > 
                                <option value="2" <s:if test="D17.equalsIgnoreCase('2')"> selected </s:if>>Có</option>
                                <option value="1" <s:if test="D17.equalsIgnoreCase('1')"> selected </s:if>>Không</option>
                                </select>
                            </td>  
                        </tr>
                </s:iterator>
            </table>
        </div>
        <div id="luu_thanhcong"></div>
    </body>
</html>
