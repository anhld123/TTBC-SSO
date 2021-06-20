<%-- 
    Document   : sbv_066_phkq_index
    Created on : Jun 7, 2016, 10:21:45 AM
    Author     : Administrator
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="js/new_js_bcqt.js"></script>
        <script>
		jQuery( function(){
			jQuery( document ).trigger( "enhance" );
		});
	</script>
        <script>
            AutoEvaluate('');
            function AutoEvaluate(idx) {
                //Định dang dòng đang được chọn
                var sumtotal = 0, sumtotalCol = 0, rD1=0,rD2=0,rD3=0,rD4=0,rD5=0,rD6=0;
                var LisYN = ["[id=D9]"];
                var LisCAP = ["[id=D8]"];
                var LisVAL = ["[id=D1]", "[id=D2]", "[id=D3]", "[id=D4]", "[id=D5]", "[id=D6]"];
                
                rD1 = parseFloat($("[id=D1]").eq(idx).val());
                rD2 = parseFloat($("[id=D2]").eq(idx).val());
                rD3 = parseFloat($("[id=D3]").eq(idx).val());
                rD4 = parseFloat($("[id=D4]").eq(idx).val());
                rD5 = parseFloat($("[id=D5]").eq(idx).val());
                sumtotalCol = rD1 + rD2 + rD3 - rD4 - rD5;
                $("[id=D6]").eq(idx).val(sumtotalCol);                 //$(LisVAL[m]).eq(j).val(sumtotal);
                 
                for (i = 0; i < LisYN.length; i++) 
                {
                    for (j = 0; j < $(LisYN[i]).size(); j++) {
                        var Mcap = "";
                        if ($(LisYN[i]).eq(j).val().trim() === "Y") {
                            Mcap = $(LisCAP[i]).eq(j).val();
                        }
                        
                        if (Mcap.length > 0) 
                        {
                            for (m = 0; m < LisVAL.length; m++) 
                            {
                                sumtotal = 0;
                                for (k = 0; k < $(LisYN[i]).size(); k++) 
                                {
                                    if ($(LisYN[i]).eq(k).val().trim() === "N" && $(LisCAP[i]).eq(k).val().trim().substr(0, Mcap.length) === Mcap) {
                                        sumtotal += parseFloat($(LisVAL[m]).eq(k).val());
                                    }
                                }
                                $(LisVAL[m]).eq(j).val(sumtotal);
                            }
                        }
                    }
                }
                CheckInput(idx);
            }
            function Uppertext(idx){
                $("[id=D7]").eq(idx).val($("[id=D7]").eq(idx).val().toUpperCase());
                CheckSplit(idx);
            }
            // Hàm kiểm tra nhập trong kỳ. D2+D3 > 0
            function CheckInput(idx){
                var ValD2 = $("[id=D2]").eq(idx).val();
                var ValD3 = $("[id=D3]").eq(idx).val();
                if((ValD2 + ValD3) > 0){
                    $("[id=D7]").eq(idx).removeAttr("readonly");
                }else{
                    $("[id=D7]").eq(idx).val('');
                    $("[id=D7]").eq(idx).attr("readonly", "readonly");
                }
            }
            function CheckSplit(idx){
                var vals = $("[id=D7]").eq(idx).val();
                var arrlist = vals.split(";");
                for (i = 0; i < arrlist.length; i++){
                    if ((arrlist[i].length != 2 && arrlist[i].length > 0)){
                        alert("Seri: " + arrlist[i] + " không đúng chuẩn. Vui lòng kiểm tra lại");
                        $("[id=D7]").eq(idx).focus();
                    };
                }
            }
        </script>
        
        <!--<script src="js/jquery.js" type="text/javascript"></script>-->
        <script src="js/jquery.maskedinput.js" type="text/javascript"></script>
        <script>
            
            $('document').ready(function() {
//                alert("1");
                $("#D7").mask("99/99/9999");
//                alert("2");
            });
        </script>
        
        

        <style>
            .css_text{
                border: 0px;
                background-color: transparent;
                width: 100%;
            }
        </style>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_sbv}" action="SAVE_%{khoa_sbv}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:hidden name="khoa_sbv"/>
            <h3>BÁO CÁO THU GIỮ TIỀN GIẢ</h3>
            </br>
            <div style="font-style: italic; text-align: right;width: 95%;padding-bottom: 4px;">Đơn vị tính: Tờ/Miếng</div>
            <table  id="SBV_066_PHKQ" name="SBV_066_PHKQ" 
                    class="SBV_066_PHKQ" cellspacing="0" 
                    cellspadding="0" border="1" 
                    style="border-collapse: collapse; width: 98%;">
                <tr>
                    <th rowspan="2">STT</th>
                    <th rowspan="2">Loại tiền giả</th>
                    <th rowspan="2">Tồn kho đầu kỳ</th>
                    <th colspan="2">Nhập trong kỳ</th>
                    <th colspan="2">Xuất trong kỳ</th>
                    <th rowspan="2">Tồn kho cuối kỳ</th>
                    <th rowspan="2">Vần sêri (02 chữ cái đầu)<br>tiền giả thu từ khách hàng</th>
                </tr>
                <tr align="center">
                    <td>Từ khách hàng</td>
                    <td>Từ đơn vị thành viên</td>
                    <td>Nộp về NHNN chi nhánh</td>
                    <td>Nộp về đơn vị đầu mối</td>
                </tr>

                <!-- Thực hiện load dữ liệu tại đâu -->
                <s:iterator value="lstData" status="rowstatus">
                    <tr <s:if test='%{D30 == "Y"}'> style="background-color: silver;"</s:if>>
                        <s:if test='%{D30 == "Y"}'>
                            <td colspan="2">
                                <input type="hidden" style="text-align: right; font-weight: bold;" class="css_text" value="<s:property  value="MA" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA"  readonly="readonly"/>
                                <input type="text" style="text-align:left; font-weight: bold;" class="css_text" value="<s:property  value="TEN" />" id="TEN" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN" readonly="readonly"/>
                            </td>
                            <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text number" value="<s:property  value="D1" />" id="D1" name="lstData[<s:property  value="%{#rowstatus.index}" />].D1" readonly="readonly"/></td>
                            <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text number" value="<s:property  value="D2" />" id="D2" name="lstData[<s:property  value="%{#rowstatus.index}" />].D2" readonly="readonly"/></td>
                            <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text number" value="<s:property  value="D3" />" id="D3" name="lstData[<s:property  value="%{#rowstatus.index}" />].D3" readonly="readonly"/></td>
                            <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text number" value="<s:property  value="D4" />" id="D4" name="lstData[<s:property  value="%{#rowstatus.index}" />].D4" readonly="readonly"/></td>
                            <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text number" value="<s:property  value="D5" />" id="D5" name="lstData[<s:property  value="%{#rowstatus.index}" />].D5" readonly="readonly"/></td>
                            <td><input type="text" style="text-align: right; font-weight: bold;" class="css_text number" value="<s:property  value="D6" />" id="D6" name="lstData[<s:property  value="%{#rowstatus.index}" />].D6" readonly="readonly"/></td>
                            <td>
                                <input type="text" style="font-weight: bold;" class="css_text" value="<s:property  value="D7" />" id="D7" name="lstData[<s:property  value="%{#rowstatus.index}" />].D7" readonly="readonly"/>
                                <input type="hidden" value="<s:property  value="D29" />" id="D8" name="lstData[<s:property  value="%{#rowstatus.index}" />].D29"/>
                                <input type="hidden" value="<s:property  value="D30" />" id="D9" name="lstData[<s:property  value="%{#rowstatus.index}" />].D30"/>
                                <input type="hidden" value="<s:property  value="TT_HIENTHI" />" id="D10" name="lstData[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                            </td>
                        </s:if>
                        <s:else>
                            <td></td>
                            <td>
                                <input type="hidden" style="text-align: right;" class="css_text" value="<s:property  value="MA" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].MA"  readonly="readonly"/>
                                <input type="hidden" style="text-align: right;" class="css_text" value="<s:property  value="KIEUIN" />" id="MA" name="lstData[<s:property  value="%{#rowstatus.index}" />].KIEUIN"  readonly="readonly"/>
                                <input type="text" style="text-align: right;" class="css_text" value="<s:property  value="TEN" />" id="TEN" name="lstData[<s:property  value="%{#rowstatus.index}" />].TEN"  readonly="readonly"/>
                            </td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D1" />" id="D1" name="lstData[<s:property  value="%{#rowstatus.index}" />].D1" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D2" />" id="D2" name="lstData[<s:property  value="%{#rowstatus.index}" />].D2" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D3" />" id="D3" name="lstData[<s:property  value="%{#rowstatus.index}" />].D3" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D4" />" id="D4" name="lstData[<s:property  value="%{#rowstatus.index}" />].D4" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D5" />" id="D5" name="lstData[<s:property  value="%{#rowstatus.index}" />].D5" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);"/></td>
                            <td><input type="text" style="text-align: right;" class="css_text number" value="<s:property  value="D6" />" id="D6" name="lstData[<s:property  value="%{#rowstatus.index}" />].D6" onblur="AutoEvaluate(<s:property value="%{#rowstatus.index}" />);" readonly="readonly"></td>
                            <td>
                                <input type="text" class="css_text" value="<s:property  value="D7" />" id="D7" name="lstData[<s:property  value="%{#rowstatus.index}" />].D7" onblur="Uppertext(<s:property  value="%{#rowstatus.index}" />);" readonly="readonly"/>
                                <input type="hidden" value="<s:property  value="D29" />" id="D8" name="lstData[<s:property  value="%{#rowstatus.index}" />].D29"/>
                                <input type="hidden" value="<s:property  value="D30" />" id="D9" name="lstData[<s:property  value="%{#rowstatus.index}" />].D30"/>
                                <input type="hidden" value="<s:property  value="TT_HIENTHI" />" id="D10" name="lstData[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI"/>
                            </td>
                        </s:else>
                    </tr>
                    </s:iterator>
            </table>
            <sj:submit id="%{khoa_sbv}_save" name="%{khoa_sbv}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
    </body>
</html>	