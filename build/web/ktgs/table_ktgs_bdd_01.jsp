<%-- 
    Document   : table_ktgs_bdd_01
    Created on : Oct 27, 2016, 9:23:15 AM
    Author     : chudv
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KTGS_01_BDD</title>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="js/new_js_bcqt.js"></script>
        <script>
            AutoEvaluate('');
            function AutoEvaluate(idx) {
                
                //Định dang dòng đang được chọn
                var congCapId = "[id=D21]";
                var capId = "[id=D20]";
                var LisVAL = ["[id=D1]","[id=D2]","[id=D3]","[id=D4]","[id=D5]","[id=D6]","[id=D7]","[id=D8]","[id=D9]","[id=D10]","[id=D11]","[id=D12]","[id=D13]","[id=D15]","[id=D16]"];
                    //Bắt đầu duyệt dòng bản ghi
                for (i = 0; i < $(congCapId).size(); i++) {
                    var Mcap = "";
                    if ($(congCapId).eq(i).val().trim() === "Y") 
                    {
                        Mcap = $(capId).eq(i).val();
                        if (Mcap.length > 0) 
                        {
                            for (m = 0; m < LisVAL.length; m++) 
                            {                                                             
                                sumtotal = 0;
                                for (k = 0; k < $(congCapId).size(); k++) 
                                {
                                    if ($(congCapId).eq(k).val().trim() === "N" 
                                      && $(capId).eq(k).val().trim().substr(0, Mcap.length) === Mcap)
                                    {
                                        //sumtotal += $(LisVAL[m]).eq(k).val();
                                        sumtotal = sumtotal + Number($(LisVAL[m]).eq(k).val());
                                    }
                                }
                                $(LisVAL[m]).eq(i).val(sumtotal);
                            }
                        }
                    }
                }
            }
        </script>
        <style>
            .clss_ttcot{
                font-style:italic;
                text-align: center;
            }
            .clss_lstdata input{
                width: 100%;
                border: 0px;
                height: 24px;
            }
            .clss_tongcong{
                font-weight: bold;
                background-color:silver;
            }
            .css_text{
                font-family: "Tahoma","Times New Roman";
                border: 0px;
                background-color: transparent;
                width: 100%;
                color: #000;
            }
        </style>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_ktgs}" action="SAVE_%{khoa_ktgs}" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:hidden name="khoa_ktgs"/>
            <div id="divTitle">
                BÁO CÁO KẾT QUẢ HOẠT ĐỘNG CỦA BAN ĐẠI DIỆN HĐQT CÁC CẤP
            </div>
            <div id="divDonvitinh" style="font-style: italic;">
                Đơn vị tính: người, kỳ họp, %, triệu đồng
            </div>
            <table name="KTGS_01_BDD" class="KTGS_01_BDD" cellspacing="0" cellspadding="0" border="1" style="border-collapse: collapse; width: 95%;" id="tablems_bdd01" align="center">
                <tr style="background-color: #e7e7e7;">
                    <th rowspan="4">STT</th>
                    <th rowspan="4">BAN ĐẠI DIỆN HĐQT</th>
                    <th rowspan="4">Tổng số Ban Đại diện</th>
                    <th colspan="4">CỦNG CỐ KIỆN TOÀN TV BAN ĐẠI DIỆN</th>
                    <th colspan="2">TỔ CHỨC HỌP</th>
                    <th colspan="6">CÔNG TÁC KIỂM TRA GIÁM SÁT</th>
                    <th colspan="2" rowspan="2">CÔNG TÁC THAM MƯU CHO CẤP ỦY, CHÍNH QUYỀN ĐỊA PHƯƠNG</th>
                </tr>
                <tr style="background-color: #e7e7e7;">
                    <th rowspan="3">Số Thành viên (TV) theo QĐ</th>
                    <th colspan="2">Số TV kỳ BC</th>
                    <th rowspan="3">Số TV được kiện toàn trong tháng</th>
                    <th rowspan="3">BĐD họp định kỳ</th>
                    <th rowspan="3">BĐD chưa họp đến kỳ BC</th>
                    <th rowspan="3">Số TV được phân công kiểm tra (Chỉ nhập khi có thay đổi trong năm)</th>
                    <th colspan="5">Kết quả kiểm tra trong tháng</th>
                </tr>
                <tr style="background-color: #e7e7e7;">
                    <th rowspan="2">Tổng số TV</th>
                    <th rowspan="2">Trong đó TV là Chủ tịch UBND cấp xã</th>
                    <th rowspan="2">Số TV thực hiện kiểm tra</th>
                    <th rowspan="2">Số huyện kiểm tra</th>
                    <th rowspan="2">Số xã kiểm tra</th>
                    <th rowspan="2">Số Tổ kiểm tra</th>
                    <th rowspan="2">Số hộ kiểm tra</th>
                    <th rowspan="2">Chuyển nguồn vốn NSĐP - Số dư đến ngày BC</th>
                    <th rowspan="2">Hỗ trợ CSVC (giá tri) - Số được hỗ trợ trong tháng</th>
                </tr>
                <tr></tr>
                <tr align="center" class="clss_ttcot" style="background-color: #e7e7e7;">
                    <td width="3%">(1)</td>
                    <td width="15%">(2)</td>
                    <td width="4%">(3)</td>
                    <td width="4%">(4)</td>
                    <td width="4%">(5)</td>
                    <td width="4%">(6)</td>
                    <td width="4%">(7)</td>
                    <td width="4%">(8)</td>
                    <td width="4%">(9)</td>
                    <td width="10%">(10)</td>
                    <td width="5%">(11)</td>
                    <td width="5%">(12)</td>
                    <td width="6%">(13)</td>
                    <td width="5%">(14)</td>
                    <td width="5%">(15)</td>
                    <td width="9%">(16)</td>
                    <td width="10%">(17)</td>
                </tr>
                
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr class="clss_lstdata" style="font-style: bold;">
                            <td align="right">    
                                <input type="hidden" value="<s:property  value="NHAPTAY" />" id="NHAPTAY" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/>
                                <input type="hidden" value="<s:property  value="CO_TONGHOP" />" id="CO_TONGHOP" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/>
                                <input type="hidden" value="<s:property  value="D22" />" id="D22" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22"/>
                                <input type="hidden" value="<s:property  value="THUTU" />" id="THUTU" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>
                                <input type="hidden" value="<s:property  value="MA" />" id="MA" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>
                                <input type="hidden" value="<s:property  value="D20" />" id="D20" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20"/>
                                <input type="hidden" value="<s:property  value="D21" />" id="D21" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21"/>
                                <s:if test="D22.equalsIgnoreCase(1)">
                                    <input style="text-align: center;font-weight: bold; color: #000;" type="text" style="font-weight: bold;" class="css_text" value="<s:property  value="TT_HIENTHI" />" id="TT_HIENTHI" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" readonly="readonly"/>
                                </s:if>
                                <s:if test="D22.equalsIgnoreCase(3)">
                                    <input style="text-align: center;" type="text" style="font-weight: bold;" class="css_text" value="<s:property  value="TT_HIENTHI" />" id="TT_HIENTHI" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" readonly="readonly"/>
                                </s:if>
                            </td>
                            <s:if test="D22.equalsIgnoreCase(1)">
                                <td  align="right">
                                    <input style="font-weight: bold; color: #000;" type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="css_text" onfocus="this.select()" readonly="readonly" />
                                </td>
                            </s:if>
                            <s:if test="D22.equalsIgnoreCase(3)">
                                <td  align="right">
                                    <input type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="css_text" onfocus="this.select()" readonly="readonly" />
                                </td>
                            </s:if>

                            <td align = "right">
                                <input type="text" style="text-align: right;"value="<s:property  value="D1" />" id="D1"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D1" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D2" />" id="D2"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D2" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D3" />" id="D3"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D3" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D4" />" id="D4"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D4" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D5" />" id="D5"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D5" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D6" />" id="D6"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D6" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D7" />" id="D7"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D7" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D8" />" id="D8"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D8" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D9" />" id="D9"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D9" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D10" />" id="D10"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D10" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D11" />" id="D11"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D11" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D12" />" id="D12"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D12" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D13" />" id="D13"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D13" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D15" />" id="D15"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D15" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;" value="<s:property  value="D16" />" id="D16"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D16" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                        </tr>
                    </s:if>
                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr class="clss_lstdata">
                            <td align="right">    
                                <input type="hidden" value="<s:property  value="NHAPTAY" />" id="NHAPTAY" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/>
                                <input type="hidden" value="<s:property  value="CO_TONGHOP" />" id="CO_TONGHOP" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/>
                                <input type="hidden" value="<s:property  value="D22" />" id="D22" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22"/>
                                <input type="hidden" value="<s:property  value="THUTU" />" id="THUTU" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/>
                                <input type="hidden" value="<s:property  value="MA" />" id="MA" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/>
                                <input type="hidden" value="<s:property  value="D20" />" id="D20" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20"/>
                                <input type="hidden" value="<s:property  value="D21" />" id="D21" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21"/>
                                <s:if test="D22.equalsIgnoreCase(1)">
                                    <input style="text-align: center;font-weight: bold; color: #000;" type="text" style="font-weight: bold;" class="css_text" value="<s:property  value="TT_HIENTHI" />" id="TT_HIENTHI" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" readonly="readonly"/>
                                </s:if>
                                <s:if test="D22.equalsIgnoreCase(3)">
                                    <input style="text-align: center;" type="text" style="font-weight: bold;" class="css_text" value="<s:property  value="TT_HIENTHI" />" id="TT_HIENTHI" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" readonly="readonly"/>
                                </s:if>
                            </td>
                            <s:if test="D22.equalsIgnoreCase(1)">
                                <td  align="right">    
                                    <input style="font-weight: bold; color: #000;" type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="css_text" onfocus="this.select()"    readonly="readonly" />
                                </td>
                            </s:if>
                            <s:if test="D22.equalsIgnoreCase(3)">
                                <td  align="right">    
                                    <input type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="css_text" onfocus="this.select()"    readonly="readonly" />
                                </td>
                            </s:if>
                            
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;"value="<s:property  value="D1" />" id="D1"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D1" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D2" />" id="D2"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D2" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D3" />" id="D3"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D3" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D4" />" id="D4"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D4" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D5" />" id="D5"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D5" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D6" />" id="D6"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D6" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D7" />" id="D7"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D7" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D8" />" id="D8"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D8" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D9" />" id="D9"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D9" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D10" />" id="D10"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D10" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D11" />" id="D11"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D11" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D12" />" id="D12"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D12" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D13" />" id="D13"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D13" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D15" />" id="D15"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D15" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D16" />" id="D16"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D16" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                        </tr>
                    </s:if>
                </s:iterator>
            </table>
            <sj:submit id="%{khoa_ktgs}_save" name="%{khoa_ktgs}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
