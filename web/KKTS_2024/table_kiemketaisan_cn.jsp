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
    #subTable {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 98%;
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
    }
    .txtBody > .ui-datepicker-trigger{
        display: none;
    }
    td.hdtitle {
        position: static;
        top: 0;
        z-index: 10;
    }
    @-webkit-keyframes my {
        0% { color: red; } 
        50% { color: #fff;  } 
        100% { color: red;  } 
    }
    @-moz-keyframes my { 
        0% { color: red;  } 
        50% { color: #fff;  }
        100% { color: red;  } 
    }
    @-o-keyframes my { 
        0% { color: red; } 
        50% { color: #fff; } 
        100% { color: red;  } 
    }
    @keyframes my { 
        0% { color: red;  } 
        50% { color: #fff;  }
        100% { color: red;  } 
    } 
    .color_11 {
        background:#fff;
        font-size:14px;
        font-weight:bold;
        -webkit-animation: my 700ms infinite;
        -moz-animation: my 700ms infinite; 
        -o-animation: my 700ms infinite; 
        animation: my 700ms infinite;
    }
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script type="text/javascript" src="KKTS_2024/js.js"></script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 98%;height: 400px;">    
            <div id="divTitle" style="text-align: left">
                <a style="text-decoration: underline; color: #3dc21b" 
                   href="javascript:funcTableFile('KKTS_01_HDKT_<s:property value="ssduan1"/>','<s:property value="pos_cd_username"/>','<s:property value="main_pos_username"/>','<s:property value="ssduan1"/>','<s:property value="ssngay1"/>')">DANH SÁCH HỘI ĐỒNG KIỂM KÊ</a>

            </div>
            <div id="divTitle">
                DANH SÁCH TÀI SẢN KIỂM KÊ<br> 
                <s:if test="chotsl.equalsIgnoreCase('2')" ><a class="color_11">(Đơn vị đã gửi dữ liệu)</a></s:if>
                <input type="hidden" value="<s:property value="chotsl"/>" name="chotsl" id="chotsl"/> 
            </div>
            <table border="1" class="editDelete" id="subTable" align="center">               
                <tr> 
                    <th class="STT1" rowspan="2">STT</th>                           
                    <th class="STT3" rowspan="2">Đơn vị</th>  
                    <th class="STT2" rowspan="2">Tên thiết bị</th>  
                    <th class="STT2" rowspan="2">Nhóm thiết bị</th>
                    <th class="STT2" rowspan="2">Nơi sử dụng</th>
                    <th class="STT2" rowspan="2">ĐVT</th>
                    <th class="STT3" colspan="3">Số lượng</th>
                    <th class="STT4" rowspan="2">Ký hiệu hàng hóa</th>   
                    <th class="STT4" rowspan="2">Serial Number</th>
                    <th class="STT4" rowspan="2">Tình trạng tài sản</th> 
                    <th class="STT2" rowspan="2">Trạng thái</th>  
                </tr>
                <tr>
                    <th class="STT2">Sổ sách</th>      
                    <th class="STT2">Kiểm kê</th> 
                    <th class="STT2">Chênh lệch</th> 
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
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr id="tablefix"> 
                        <td class="D0"><s:property value="%{#rowstatus.index + 1}" />
                            <input type="hidden" value="<s:property  value="THUTU" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>                             
                            <input type="hidden" value="<s:property value="%{#rowstatus.index + 1}" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                            <input type="hidden" value="<s:property  value="MA" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>                             
                            <input type="hidden" value="<s:property  value="TEN" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"/>
                            <input type="hidden" value="<s:property  value="D12" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"/>
                            <input type="hidden" value="<s:property  value="D13" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13"/>

                        </td>
                        <td class="D0">
                            <s:if test="%{#rowstatus.index == 0 || lstDulieuNt[#rowstatus.index].D1 != lstDulieuNt[#rowstatus.index - 1].D1}">
                                <!-- Dropdown for visible PGD selection -->
                                <select id="lstPGD_<s:property value='%{#rowstatus.index}' />" style="width: 150px;border: hidden; background: khaki"
                                        name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D1">
                                    <option value="000000">----Chọn đơn vị----</option>
                                    <s:iterator value="lstPGD_API" status="ideRows" var="language">
                                        <option value="<s:property value='posCode'/>"
                                                <s:if test='%{#language.posCode == D1}'>selected</s:if>>
                                            <s:property value="posCode"/> - <s:property value="posName"/>
                                        </option>
                                    </s:iterator>
                                </select>
                            </s:if>
                            <s:else>
                                <!-- Hidden input to save PGD value when dropdown is not displayed -->
                                <input type="hidden" name="lstDulieuNt[<s:property value='%{#rowstatus.index}' />].D1"
                                       value="<s:property value='D1'/>" />
                            </s:else>
                        </td>
                        <td class="D0">
                            <select  id="lstDm111_<s:property  value='%{#rowstatus.index}' />" style="width: 150px;border: hidden"
                                     name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                     onchange="updateD3Selection(this)">
                                <option value="000000">---Tên thiết bị---</option>
                                <s:iterator value="lstDmKhac111" status="ideRows" var="language">
                                    <option value="<s:property value="sortOrder"/>" 
                                            <s:if test='%{#language.sortOrder == D2}'>selected</s:if>>
                                        <s:property value="sortOrder"/> - <s:property value="value"/>
                                    </option>        
                                </s:iterator>
                            </select>
                        </td>

                        <td class="D0">
                            <select  id="lstDm112_<s:property  value='%{#rowstatus.index}' />" style="width: 150px;border: hidden; background: #f2f2f2"
                                     onmousedown="return false"
                                     name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3">
                                <option value="000000">---Nhóm thiết bị---</option>
                                <s:iterator value="lstDmKhac112" status="ideRows" var="language">
                                    <option value="<s:property value="description"/>" 
                                            <s:if test='%{#language.description == D3}'>selected</s:if>>
                                        <s:property value="description"/> - <s:property value="value"/>
                                    </option>        
                                </s:iterator>
                            </select>
                        </td>

                        <td class="D0">
                            <select  id="lstDm113_<s:property  value='%{#rowstatus.index}' />" style="width: 150px;border: hidden"
                                     name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4">
                                <option value="000000">---Nơi sử dụng---</option>
                                <s:iterator value="lstDmKhac113" status="ideRows" var="language">
                                    <option value="<s:property value="code"/>" 
                                            <s:if test='%{#language.code == D4}'>selected</s:if>>
                                        <s:property value="code"/> - <s:property value="value"/>
                                    </option>        
                                </s:iterator>
                            </select>
                        </td>

                        <td class="D0">
                            <select  id="lstDm114_<s:property  value='%{#rowstatus.index}' />" style="width: 80px;border: hidden; background: #f2f2f2"
                                     onmousedown="return false"
                                     name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5">
                                <option value="000000">---ĐVT---</option>
                                <s:iterator value="lstDmKhac114" status="ideRows" var="language">
                                    <option value="<s:property value="code"/>" 
                                            <s:if test='%{#language.code == D5}'>selected</s:if>>
                                        <s:property value="code"/> - <s:property value="value"/>
                                    </option>        
                                </s:iterator>
                            </select>
                        </td>

                        <td> <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value='%{#rowstatus.index}' />" readonly="true"
                                    name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number" onchange="calc(this);"/>
                        </td> 

                        <td> <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value='%{#rowstatus.index}' />" 
                                    name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="number" onchange="calc(this);"/>
                        </td> 
                        <td> <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value='%{#rowstatus.index}' />" 
                                    name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="number" readonly/>
                        </td> 
<!--                        <td class="D0"><textarea style="width: 98%" placeholder="Nhập tối đa 500 ký tự" id="D9_<s:property  value='%{#rowstatus.index}' />" 
                                                 name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D9" maxlength="500"><s:property value='D9'/></textarea>
                        </td>-->

                        <td class="D0">
                            <select  id="lstDm111b_<s:property  value='%{#rowstatus.index}' />" style="width: 150px;border: hidden; background: #f2f2f2"
                                     onmousedown="return false"
                                     name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9"
                                <option value="000000">---Ký hiệu---</option>
                                <s:iterator value="lstDmKhac111" status="ideRows" var="language">
                                    <option value="<s:property value="sortOrder"/>" 
                                            <s:if test='%{#language.sortOrder == D9}'>selected</s:if>>
                                        <s:property value="sortOrder"/> - <s:property value="description"/>
                                    </option>        
                                </s:iterator>
                            </select>
                        </td>

                        <td class="D0"><textarea style="width: 98%" placeholder="Nhập tối đa 500 ký tự" id="D10_<s:property  value='%{#rowstatus.index}' />" 
                                                 name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D10" maxlength="500"><s:property value='D10'/></textarea>
                        </td>
                        <td class="D0">
                            <select  id="lstDm116_<s:property  value='%{#rowstatus.index}' />" style="width: 150px;border: hidden"
                                     name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11">
                                <option value="000000">---Tình trạng---</option>
                                <s:iterator value="lstDmKhac116" status="ideRows" var="language">
                                    <option value="<s:property value="code"/>" 
                                            <s:if test='%{#language.code == D11}'>selected</s:if>>
                                        <s:property value="code"/> - <s:property value="value"/>
                                    </option>        
                                </s:iterator>
                            </select>
                        </td>
                        <td class="D0"><input type="button" style="color: red;width: 50px"
                                              onclick="cancelAssign('<s:property value="D1"/>', '<s:property value="D13"/>', '<s:property value="D12"/>');" value="Xóa"/>

                        </td>
                    </s:iterator>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td class="D0"><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="D0 SOKU"/></td>
                </tr>  
            </table>
        </div>
        <div id="luu_thanhcong"></div>
    </body>
</html>
