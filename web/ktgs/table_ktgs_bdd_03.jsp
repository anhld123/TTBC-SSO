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
                var LisVAL = ["[id=D4]","[id=D5]","[id=D6]","[id=D7]","[id=D8]","[id=D9]","[id=D10]","[id=D11]","[id=D12]","[id=D13]","[id=D14]","[id=D15]","[id=D16]","[id=D17]","[id=D18]","[id=D19]","[id=D26]","[id=D27]"];
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
                
                for (var i = 0; i < 99; i++) {
                    //8=2+4-6
                    $("[id=D18]").eq(i).val(parseFloat($("[id=D8]").eq(i).val()) + parseFloat($("[id=D9]").eq(i).val()));
                    $("[id=D19]").eq(i).val(parseFloat($("[id=D10]").eq(i).val()) + parseFloat($("[id=D14]").eq(i).val()));

                }
            }
        </script>
        
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('.D0').css({"text-align": "center"});
                $(".TD_TEN_KH").css({"width": "280px"});
                $(".TD_CHUCVU").css({"width": "55px"});
                $(".TD_COMBOBOX").css({"width": "75px"});
                $(".TD_CMND").css({"width": "4%"});
                $(".TD_MAIL").css({"width": "110px"});
                $(".TD_M").css({"width": "140px"});
                $(".TD_TVTT").css({"width": "1110px"});
                $(".TD_THEMXOA").css({"width": "10px"});
                $(".TEN_KH").css({"width": "100%"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
            });

            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            
            
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
            <table name="KTGS_01_BDD" class="KTGS_01_BDD" cellspacing="0" cellspadding="0" border="1" style="border-collapse: collapse; width: 97%;" id="tablems_bdd01" align="center">
                <s:if test="Grade.equalsIgnoreCase('1')">
                    <tr style="background-color: #e7e7e7;">
                        <th rowspan="5">STT</th>
                        <th rowspan="5">BAN ĐẠI DIỆN HĐQT</th>
                        <th colspan="6">HỌP BAN ĐẠI DIỆN</th>
                        <th rowspan="2" colspan="3">Số thành viên được phân công kiểm tra giám sát</th>
                        <th rowspan="2"  colspan="9">Kết quả thực hiện kiểm tra giám sát trong kỳ báo cáo</th>                                       
                    </tr>
                    <tr style="background-color: #e7e7e7;">
                        <th colspan="2">Số Thành viên theo Quyết định</th>
                        <th colspan="1" class="TD_CHUCVU">TỔ CHỨC HỌP</th>
                        <th rowspan="4" class="TD_COMBOBOX">Hình thức họp</th>
                        <th colspan="2">Số thành viên dự họp</th>                    
                    </tr>
                    <tr style="background-color: #e7e7e7;">
                        <th rowspan="3" class="TD_CHUCVU">Thành viên là lãnh đạo cấp huyện</th>
                        <th rowspan="3" class="TD_CHUCVU">Thành viên là chủ tịch UBND cấp xã</th>
                        <th rowspan="3" class="TD_CHUCVU">Ngày họp</th>
                        <!--<th rowspan="3" class="TD_COMBOBOX">Ngày họp đúng thời gian qui định</th>-->                        
                        
                        <th rowspan="3" class="TD_CHUCVU">Thành viên là lãnh đạo cấp huyện</th>
                        <th rowspan="3" class="TD_CHUCVU">Thành viên là chủ tịch UBND cấp xã</th>
                        <th rowspan="3" class="TD_CHUCVU">Tổng số</th>
                        <th colspan="2">Trong đó: </th>
                        <th rowspan="3" class="TD_CHUCVU">Tổng số</th>
                        <th colspan="8">Trong đó</th>
                    </tr>
                    <tr style="background-color: #e7e7e7;">
                        <th rowspan="2" class="TD_CHUCVU">Thành viên là lãnh đạo cấp huyện</th>
                        <th rowspan="2" class="TD_CHUCVU">Thành viên là chủ tịch UBND cấp xã</th>
                        <th colspan="4">Thành viên là lãnh đạo cấp huyện thực hiện kiểm tra, giám sát</th>
                        <th colspan="4">Thành viên là chủ tịch UBND cấp xã thực hiện kiểm tra, giám sát</th>                    
                    </tr>
                    <tr style="background-color: #e7e7e7;">
                        <th class="TD_CHUCVU">Số thành viên thực hiện</th>
                        <th class="TD_CHUCVU">Xã</th>
                        <th class="TD_CHUCVU">Tổ</th>
                        <th class="TD_CHUCVU">Hộ</th>   
                        <th class="TD_CHUCVU">Số thành viên thực hiện</th>
                        <th class="TD_CHUCVU">Xã</th>
                        <th class="TD_CHUCVU">Tổ</th>
                        <th class="TD_CHUCVU">Hộ</th>  
                    </tr>
                    <tr></tr>
                    <tr align="center" class="clss_ttcot" style="background-color: #e7e7e7;">
                        <td width="3%">(1)</td>
                        <td width="15%">(2)</td>
                        <td class="TD_CHUCVU">(3)</td>
                        <td class="TD_CHUCVU">(4)</td>
                        <td class="TD_CHUCVU">(5)</td>
                        <td class="TD_COMBOBOX">(6)</td>
                        <td class="TD_COMBOBOX">(7)</td>
                        <td class="TD_CHUCVU">(8)</td>
                        <td class="TD_CHUCVU">(9=10+11)</td>
                        <td class="TD_CHUCVU">(10)</td>
                        <td class="TD_CHUCVU">(11)</td>
                        <td class="TD_CHUCVU">(12=13+17)</td>
                        <td class="TD_CHUCVU">(13)</td>
                        <td class="TD_CHUCVU">(14)</td>
                        <td class="TD_CHUCVU">(15)</td>
                        <td class="TD_CHUCVU">(16)</td>
                        <td class="TD_CHUCVU">(17)</td>
                        <td class="TD_CHUCVU">(18)</td>
                        <td class="TD_CHUCVU">(19)</td>
                        <td class="TD_CHUCVU">(20)</td>
                        <!--<td class="TD_CHUCVU">(21)</td>-->
                        <!--<td class="TD_CHUCVU">(20)</td>-->
                         <!--<td class="TD_CHUCVU">(21)</td>-->
                    </tr>
                </s:if>
                <s:else>
                        <tr style="background-color: #e7e7e7;">
                        <th rowspan="5">STT</th>
                        <th rowspan="5">BAN ĐẠI DIỆN HĐQT</th>
                        <th colspan="6">HỌP BAN ĐẠI DIỆN</th>
                        <th rowspan="2" colspan="3">Số thành viên được phân công kiểm tra giám sát</th>
                        <th rowspan="2"  colspan="10">Kết quả thực hiện kiểm tra giám sát trong kỳ báo cáo</th>                                       
                    </tr>
                    <tr style="background-color: #e7e7e7;">
                        <th colspan="2">Số Thành viên theo Quyết định</th>
                        <th colspan="1" class="TD_CHUCVU">TỔ CHỨC HỌP</th>
                        <th rowspan="4" class="TD_COMBOBOX">Hình thức họp</th>
                        <th colspan="2">Số thành viên dự họp</th>                    
                    </tr>
                    <tr style="background-color: #e7e7e7;">
                        <th rowspan="3" class="TD_CHUCVU">Thành viên là lãnh đạo cấp tỉnh/ huyện </th>
                        <th rowspan="3" class="TD_CHUCVU">Thành viên là chủ tịch UBND cấp xã</th>
                        <th rowspan="3" class="TD_CHUCVU">Ngày họp</th>
                        <!--<th rowspan="3" class="TD_COMBOBOX">Ngày họp đúng thời gian qui định</th>-->                        
                        
                        <th rowspan="3" class="TD_CHUCVU">Thành viên là lãnh đạo cấp tỉnh/ huyện</th>
                        <th rowspan="3" class="TD_CHUCVU">Thành viên là chủ tịch UBND cấp xã</th>
                        <th rowspan="3" class="TD_CHUCVU">Tổng số</th>
                        <th colspan="2">Trong đó: </th>
                        <th rowspan="3" class="TD_CHUCVU">Tổng số</th>
                        <th colspan="9">Trong đó</th>
                    </tr>
                    <tr style="background-color: #e7e7e7;">
                        <th rowspan="2" class="TD_CHUCVU">Thành viên là lãnh đạo cấp tỉnh/ huyện</th>
                        <th rowspan="2" >Thành viên là chủ tịch UBND cấp xã</th>
                        <th colspan="5">Thành viên là lãnh đạo cấp tỉnh/huyện thực hiện kiểm tra, giám sát</th>
                        <th colspan="4">Thành viên là chủ tịch UBND cấp xã thực hiện kiểm tra, giám sát</th>                    
                    </tr>
                    <tr style="background-color: #e7e7e7;">
                        <th class="TD_CHUCVU">Số thành viên thực hiện</th>
                        <th class="TD_CHUCVU">Huyện</th>
                        <th class="TD_CHUCVU">Xã</th>
                        <th class="TD_CHUCVU">Tổ</th>
                        <th class="TD_CHUCVU">Hộ</th>   
                        <th class="TD_CHUCVU">Số thành viên thực hiện</th>
                        <th class="TD_CHUCVU">Xã</th>
                        <th class="TD_CHUCVU">Tổ</th>
                        <th class="TD_CHUCVU">Hộ</th>  
                    </tr>
                    <tr></tr>
                    <tr align="center" class="clss_ttcot" style="background-color: #e7e7e7;">
                        <td width="3%">(1)</td>
                        <td width="15%">(2)</td>
                        <td class="TD_CHUCVU">(3)</td>
                        <td class="TD_CHUCVU">(4)</td>
                        <td class="TD_CHUCVU">(5)</td>
                        <td class="TD_COMBOBOX">(6)</td>
                        <td class="TD_COMBOBOX">(7)</td>
                        <td class="TD_CHUCVU">(8)</td>
                        <td class="TD_CHUCVU">(9=10+11)</td>
                        <td class="TD_CHUCVU">(10)</td>
                        <td class="TD_CHUCVU">(11)</td>
                        <td class="TD_CHUCVU">(12=13+18)</td>
                        <td class="TD_CHUCVU">(13)</td>
                        <td class="TD_CHUCVU">(14)</td>
                        <td class="TD_CHUCVU">(15)</td>
                        <td class="TD_CHUCVU">(16)</td>
                        <td class="TD_CHUCVU">(17)</td>
                        <td class="TD_CHUCVU">(18)</td>
                        <td class="TD_CHUCVU">(19)</td>
                        <td class="TD_CHUCVU">(20)</td>
                        <td class="TD_CHUCVU">(21)</td>
                        
                    </tr>
                </s:else>
                
                
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
                                
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D26" />" id="D26"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D26" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D27" />" id="D27"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D27" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>

                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;"value="<s:property  value="D1" />" id="D1" 
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D1" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
<!--                            <td align = "right" class="TD_COMBOBOX">
                                <input type="text" style="text-align: right;" value="<s:property  value="D2" />" id="D2"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D2" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>-->
                            
                            <td align = "right" class="TD_COMBOBOX">
                                <input type="text" style="text-align: right;" value="<s:property  value="D4" />" id="D4"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D4" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                           
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D6" />" id="D6"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D6" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D7" />" id="D7"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D7" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D18" />" id="D18"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D18" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D8" />" id="D8"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D8" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D9" />" id="D9"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D9" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D19" />" id="D19"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D19" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D10" />" id="D10"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D10" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D11" />" id="D11"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D11" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D12" />" id="D12"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D12" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D13" />" id="D13"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D13" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D14" />" id="D14"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D14" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            
                            <s:if test="!Grade.equalsIgnoreCase('1')">
                                 <td align = "right" class="TD_CHUCVU">
                                    <input type="text" style="text-align: right;" value="<s:property  value="D25" />" id="D25"
                                           name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D25" 
                                           class="css_text number" onfocus="this.select();" readonly="readonly"
                                           onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                                </td>   
                            </s:if>
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D15" />" id="D15"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D15" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D16" />" id="D16"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D16" 
                                       class="css_text number" onfocus="this.select();" readonly="readonly"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;" value="<s:property  value="D17" />" id="D17"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D17" 
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
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D26" />" id="D26"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D26" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            
                            <s:if test="Grade.equalsIgnoreCase('1')">
                                    <td align = "right" class="TD_CHUCVU">
                                    <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D27" />" id="D27"
                                           name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D27" 
                                           class="css_text number" onfocus="this.select();"
                                           onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                                </td>
                            </s:if>
                                <s:else>
                                        <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D27" />" id="D27"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D27" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/>
                            </td>
                                </s:else>    
                            
                            <td align = "center" class="TD_CHUCVU">
                                <input type="text" value="<s:property  value="D1" />" id="D1_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 datepicker" placeholder="dd/MM/yyyy" />
                            </td>
<!--                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;"value="<s:property  value="D1" />" id="D1" placeholder="01->31"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D1" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="if (this.value <0 || this.value >31) {
                                                       this.value = 1
                                                   }"
                                                   />-->
                            </td>

                            
                            <td align="right" class="TD_COMBOBOX">
    <select id="hinhthuchop" name="lstDulieuNt[<s:property value="#rowstatus.index"/>].D4" style="width: 100%; height: 100%; vertical-align: middle; background-color: #fdf5ce;">
        <option value="2" <s:if test="D4 == '2'">selected="selected"</s:if>>--Chọn--</option>
        <option value="1" <s:if test="D4 == '1'">selected="selected"</s:if>>Xin ý kiến</option>
        <option value="0" <s:if test="D4 == '0'">selected="selected"</s:if>>Tổ chức họp</option>
    </select>
</td>
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D6" />" id="D6"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D6" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            
                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D7" />" id="D7"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D7" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            </s:if>
                            <s:else>
                                <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D7" />" id="D7"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D7" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/>
                            </td>
                            </s:else>
                            
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D18" />" id="D18" readonly="readonly"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D18" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D8" />" id="D8"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D8" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            
                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D9" />" id="D9"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D9" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            </s:if>
                            <s:else>
                                <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D9" />" id="D9"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D9" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/>
                            </td>
                            </s:else>
                            
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D19" />" id="D19" readonly="readonly"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D19" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D10" />" id="D10"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D10" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            
                            <s:if test="!Grade.equalsIgnoreCase('1')">
                                <td align = "right" class="TD_CHUCVU">
                                    <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D25" />" id="D25"
                                           name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D25" 
                                           class="css_text number" onfocus="this.select();"
                                           onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                                </td>
                            </s:if>
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D11" />" id="D11"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D11" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D12" />" id="D12"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D12" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D13" />" id="D13"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D13" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            
                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D14" />" id="D14"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D14" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D15" />" id="D15"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D15" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D16" />" id="D16"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D16" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D17" />" id="D17"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D17" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                            </td>
                            </s:if>
                            <s:else>
                                <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D14" />" id="D14"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D14" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/>
                            </td>
                            
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D15" />" id="D15"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D15" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D16" />" id="D16"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D16" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_CHUCVU">
                                <input type="text" style="text-align: right;color: #000;" value="<s:property  value="D17" />" id="D17"
                                       name="lstDulieuNt[<s:property value="%{#rowstatus.index}" />].D17" 
                                       class="css_text number" onfocus="this.select();"
                                       onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/>
                            </td>
                            </s:else>
                            
                            
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



