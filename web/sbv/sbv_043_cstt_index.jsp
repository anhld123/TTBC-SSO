<%-- 
    Document   : SBV_089_SGD_INDEX
    Created on : May 29, 2016, 12:55:57 PM
    Author     : BAOANH
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SBV_043_CSTT</title>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="js/new_js_bcqt.js"></script>
        <script>
            AutoEvaluate('');
            function AutoEvaluate(idx) 
            {
                //Định dang dòng đang được chọn
                var sumtotal = 0;
                var LisYN  = ["[id=D30]"];
                var LisCAP = ["[id=D29]"];
                var LisVAL = ["[id=D1]", "[id=D2]", "[id=D3]", "[id=D4]", "[id=D5]", "[id=D6]","[id=D7]", "[id=D8]", "[id=D9]", "[id=D10]", "[id=D11]","[id=D12]","[id=D20]"];
                
                for (i = 0; i < LisYN.length; i++) {
                    for (j = 0; j < $(LisYN[i]).size(); j++) //Duyệt 23 dòng trong danh sách DMBC_QT_MAU
                    {
                        var valMucI = 0, valMucII = 0, valMucIII = 0, valMucIV = 0, valMucV = 0, valMucVINgTruoc = 0;
                        var Mcap = "";
                        if ($(LisYN[i]).eq(j).val().trim() == "Y")
                        {
                            Mcap = $(LisCAP[i]).eq(j).val();
                        }
                        //alert(Mcap);
                        if (Mcap.length > 0) 
                        {
                            for (m = 0; m < LisVAL.length; m++)
                            {
                                sumtotal = 0;
                                valMucI = 0;
                                valMucII = 0;
                                valMucIII = 0;
                                valMucIV = 0;
                                valMucV = 0;
                                valMucVINgTruoc = 0;
                                for (k = 0; k < $(LisYN[i]).size(); k++)
                                {
                                    if ($(LisYN[i]).eq(k).val().trim() == "N" 
                                            && $(LisCAP[i]).eq(k).val().trim().substr(0, Mcap.length) == Mcap) 
                                    {
                                        sumtotal += parseFloat($(LisVAL[m]).eq(k).val());
                                    }
                                    if ($(LisYN[i]).eq(k).val().trim() == "N" )
                                    {
                                        if($(LisCAP[i]).eq(k).val().trim().substr(0,1)=="1")
                                           valMucI += parseFloat($(LisVAL[m]).eq(k).val());
                                        if($(LisCAP[i]).eq(k).val().trim().substr(0,1)=="2")
                                           valMucII += parseFloat($(LisVAL[m]).eq(k).val());  
                                        if($(LisCAP[i]).eq(k).val().trim().substr(0,1)=="4")
                                           valMucIV += parseFloat($(LisVAL[m]).eq(k).val());
                                        if($(LisCAP[i]).eq(k).val().trim().substr(0,1)=="5")
                                           valMucV += parseFloat($(LisVAL[m]).eq(k).val());
                                        //Thực hiện lấy số liệu Ngày hôm trước
                                        if($(LisCAP[i]).eq(k).val().trim().substr(0,1)=="6")
                                        {
                                            if (m == 0)
                                            {
                                                valMucVINgTruoc+= parseFloat($(LisVAL[LisVAL.length-1]).eq(k).val());
                                            }
                                            else
                                            {
                                                if(m != LisVAL.length-2)
                                                {
                                                    valMucVINgTruoc+= parseFloat($(LisVAL[m-1]).eq(k).val());
                                                }
                                            }
                                        }
                                        
                                    }
                                }
                                //Tổng các mục I, II, III... khi nhập các mục con thuộc nó
                                $(LisVAL[m]).eq(j).val(sumtotal);
                                //Chênh lệch giữa nguồn vốn và sử dụng vốn (= I - II)
                                valMucIII = valMucI-valMucII;
                                $(LisVAL[m]).eq(17).val(valMucI-valMucII);
                                //Trạng thái vốn khả dụng cuối ngày (= IV+V)
                                $(LisVAL[m]).eq(22).val(valMucIV+valMucV);
                                
                                //Thiếu hụt (-), dư thừa (+) nguồn vốn VND (= VI của ngày hôm trước + III)
                                if(m != LisVAL.length-2)
                                {
                                    $(LisVAL[m]).eq(18).val(valMucVINgTruoc+valMucIII);
                                }
                            }
                        }
                    };
                }
            }
        </script>
        <style>
            .css_text{
                border: 0px;
                background-color: transparent;
                width: 100%;
            }
            .number{
                text-align: right;
            }
        </style>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_sbv}" action="SAVE_%{khoa_sbv}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:hidden name="khoa_sbv"/>
            <h3>BÁO CÁO DỰ KIẾN VỐN KHẢ DỤNG BẰNG ĐỒNG VIỆT NAM TẠI TỔ CHỨC TÍN DỤNG</h3>
            <br>
            <div style="font-style: italic; text-align: right;width: 95%;padding-bottom: 4px;">Đơn vị tính: Triệu VNĐ</div>
            <table  name="SBV_066_PHKQ" class="SBV_066_PHKQ" cellspacing="0" cellspadding="0" border="1" style="border-collapse: collapse; width: 98%;">
                <tr>
                    <th rowspan="3">STT</th>
                    <th rowspan="3" style="width:37%;">Tên chỉ tiêu</th>
                    <th rowspan="2">Tăng (+), giảm (-)</th>
                    <th colspan="11">Dự kiến vốn khả dụng</th>
                </tr>
                <tr align="center">
                    <th>Tăng (+), giảm (-)</th>
                    <td>+/-</td>
                    <td>+/-</td>
                    <td>+/-</td>
                    <td>+/-</td>
                    <td>+/-</td>
                    <td>+/-</td>
                    <td>+/-</td>
                    <td>+/-</td>
                    <s:property value="str01" escape="false"/>
                    <td>+/-</td>
                </tr>
                <tr align="center">
                    <s:property value="genhead" escape="false"/>
                </tr>
                <tr align="center" style="font-style: italic;">
                    <td>(1)</td>
                    <td>(2)</td>
                    <td>(3)</td>
                    <td>(4)</td>
                    <td>(5)</td>
                    <td>(6)</td>
                    <td>(7)</td>
                    <td>(8)</td>
                    <td>(9)</td>
                    <td>(10)</td>
                    <td>(11)</td>
                    <td>(12)</td>
                    <td>(13)</td>
                    <s:property value="str02" escape="false"/>
                </tr>
                <s:iterator value="lstData" status="rowstatus">
                    <s:if test='%{D30=="Y" || MA=="III" || MA=="VI"}'>
                        <tr style="background-color: silver;">
                            <td>
                                <input type="hidden" style="font-weight: bold;" class="css_text" value="<s:property  value="MA" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA"  readonly="readonly"/>
                                <input type="hidden" style="font-weight: bold;" class="css_text" value="<s:property  value="THUTU" />" id="THUTU" name="lstData[<s:property  value="%{#rowstatus.index}" />].THUTU"  readonly="readonly"/>
                                <input type="text" style="font-weight: bold; text-align: center;" class="css_text" value="<s:property  value="TT_HIENTHI" />" id="TT_HIENTHI" name="lstData[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"  readonly="readonly"/>
                            </td>
                            <td><input type="text" style="font-weight: bold;" class="css_text" value="<s:property  value="TEN" />" id="TEN" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN"  readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D1" />" id="D1" name="lstData[<s:property  value="%{#rowstatus.index}" />].D1"  readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D2" />" id="D2" name="lstData[<s:property  value="%{#rowstatus.index}" />].D2"  readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D3" />" id="D3" name="lstData[<s:property  value="%{#rowstatus.index}" />].D3"  readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D4" />" id="D4" name="lstData[<s:property  value="%{#rowstatus.index}" />].D4"  readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D5" />" id="D5" name="lstData[<s:property  value="%{#rowstatus.index}" />].D5"  readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D6" />" id="D6" name="lstData[<s:property  value="%{#rowstatus.index}" />].D6"  readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D7" />" id="D7" name="lstData[<s:property  value="%{#rowstatus.index}" />].D7"  readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D8" />" id="D8" name="lstData[<s:property  value="%{#rowstatus.index}" />].D8"  readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D9" />" id="D9" name="lstData[<s:property  value="%{#rowstatus.index}" />].D9"  readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D10" />" id="D10" name="lstData[<s:property  value="%{#rowstatus.index}" />].D10"  readonly="readonly"/></td>
                            <td>
                                <input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D12" />" id="D12" name="lstData[<s:property  value="%{#rowstatus.index}" />].D12"  readonly="readonly"/>
                                <input type="hidden" style="font-weight: bold;" class="css_text" value="<s:property  value="D29" />" id="D29" name="lstData[<s:property  value="%{#rowstatus.index}" />].D29"  readonly="readonly"/>
                                <input type="hidden" style="font-weight: bold;" class="css_text" value="<s:property  value="D30" />" id="D30" name="lstData[<s:property  value="%{#rowstatus.index}" />].D30"  readonly="readonly"/>
                                <input type="hidden" style="font-weight: bold;" class="css_text" value="<s:property  value="D20" />" id="D20" name="lstData[<s:property  value="%{#rowstatus.index}" />].D20"  readonly="readonly"/>
                            </td>
                        </tr>
                    </s:if>
                    <s:elseif test='%{MA=="IV"}'>
                        <tr style="background-color: silver;">
                            <td>
                                <input type="hidden" style="font-weight: bold;" class="css_text" value="<s:property  value="MA" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA"  readonly="readonly"/>
                                <input type="hidden" style="font-weight: bold;" class="css_text" value="<s:property  value="THUTU" />" id="THUTU" name="lstData[<s:property  value="%{#rowstatus.index}" />].THUTU"  readonly="readonly"/>
                                <input type="text" style="font-weight: bold; text-align: center;" class="css_text" value="<s:property  value="TT_HIENTHI" />" id="TT_HIENTHI" name="lstData[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"  readonly="readonly"/>
                            </td>
                            <td><input type="text" style="font-weight: bold;" class="css_text" value="<s:property  value="TEN" />" id="TEN" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN"  readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D1" />" id="D1" name="lstData[<s:property  value="%{#rowstatus.index}" />].D1" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D2" />" id="D2" name="lstData[<s:property  value="%{#rowstatus.index}" />].D2" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D3" />" id="D3" name="lstData[<s:property  value="%{#rowstatus.index}" />].D3" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D4" />" id="D4" name="lstData[<s:property  value="%{#rowstatus.index}" />].D4" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D5" />" id="D5" name="lstData[<s:property  value="%{#rowstatus.index}" />].D5" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D6" />" id="D6" name="lstData[<s:property  value="%{#rowstatus.index}" />].D6" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D7" />" id="D7" name="lstData[<s:property  value="%{#rowstatus.index}" />].D7" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D8" />" id="D8" name="lstData[<s:property  value="%{#rowstatus.index}" />].D8" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D9" />" id="D9" name="lstData[<s:property  value="%{#rowstatus.index}" />].D9" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/></td>
                            <td><input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D10" />" id="D10" name="lstData[<s:property  value="%{#rowstatus.index}" />].D10" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"/></td>
                            <td>
                                <input type="text" style="font-weight: bold;" class="css_text number" value="<s:property  value="D12" />" id="D12" name="lstData[<s:property  value="%{#rowstatus.index}" />].D12" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                                <input type="hidden" style="font-weight: bold;" class="css_text" value="<s:property  value="D29" />" id="D29" name="lstData[<s:property  value="%{#rowstatus.index}" />].D29"/>
                                <input type="hidden" style="font-weight: bold;" class="css_text" value="<s:property  value="D30" />" id="D30" name="lstData[<s:property  value="%{#rowstatus.index}" />].D30"/>
                                <input type="hidden" style="font-weight: bold;" class="css_text" value="<s:property  value="D20" />" id="D20" name="lstData[<s:property  value="%{#rowstatus.index}" />].D20"/>
                            </td>
                        </tr>
                    </s:elseif>
                    <s:else>
                        <tr>
                            <td>
                                <input type="hidden" class="css_text" value="<s:property  value="MA" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA"  readonly="readonly"/>
                                <input type="hidden" class="css_text" value="<s:property  value="THUTU" />" id="THUTU" name="lstData[<s:property  value="%{#rowstatus.index}" />].THUTU"  readonly="readonly"/>
                                <input type="text"  style="text-align: center;" class="css_text" value="<s:property  value="TT_HIENTHI" />" id="TT_HIENTHI" name="lstData[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"  readonly="readonly"/>
                            </td>
                            <td><input type="text" class="css_text" value="<s:property  value="TEN" />" id="TEN" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN"  readonly="readonly"/></td>
                            <td><input type="text" class="css_text number" value="<s:property  value="D1" />" id="D1" name="lstData[<s:property  value="%{#rowstatus.index}" />].D1" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" class="css_text number" value="<s:property  value="D2" />" id="D2" name="lstData[<s:property  value="%{#rowstatus.index}" />].D2" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" class="css_text number" value="<s:property  value="D3" />" id="D3" name="lstData[<s:property  value="%{#rowstatus.index}" />].D3" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" class="css_text number" value="<s:property  value="D4" />" id="D4" name="lstData[<s:property  value="%{#rowstatus.index}" />].D4" onblur="AutoEvaluate<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" class="css_text number" value="<s:property  value="D5" />" id="D5" name="lstData[<s:property  value="%{#rowstatus.index}" />].D5" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" class="css_text number" value="<s:property  value="D6" />" id="D6" name="lstData[<s:property  value="%{#rowstatus.index}" />].D6" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" class="css_text number" value="<s:property  value="D7" />" id="D7" name="lstData[<s:property  value="%{#rowstatus.index}" />].D7" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" class="css_text number" value="<s:property  value="D8" />" id="D8" name="lstData[<s:property  value="%{#rowstatus.index}" />].D8" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" class="css_text number" value="<s:property  value="D9" />" id="D9" name="lstData[<s:property  value="%{#rowstatus.index}" />].D9" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" class="css_text number" value="<s:property  value="D10" />" id="D10" name="lstData[<s:property  value="%{#rowstatus.index}" />].D10" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td>
                                <input type="text" class="css_text number" value="<s:property  value="D12" />" id="D12" name="lstData[<s:property  value="%{#rowstatus.index}" />].D12" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/>
                                <input type="hidden" class="css_text" value="<s:property  value="D29" />" id="D29" name="lstData[<s:property  value="%{#rowstatus.index}" />].D29"/>
                                <input type="hidden" class="css_text" value="<s:property  value="D30" />" id="D30" name="lstData[<s:property  value="%{#rowstatus.index}" />].D30"/>
                                <input type="hidden" class="css_text" value="<s:property  value="D20" />" id="D20" name="lstData[<s:property  value="%{#rowstatus.index}" />].D20"/>
                            </td>
                        </tr>
                    </s:else>
                </s:iterator>
            </table>
            <input type="hidden" value="<s:property  value="kybc" />" id="kybc" name="kybc"/>
            <input type="hidden" value="<s:property  value="dayofmonth" />" id="dayofmonth" name="dayofmonth"/>
            <sj:submit id="%{khoa_sbv}_save" name="%{khoa_sbv}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
    </body>
</html>
