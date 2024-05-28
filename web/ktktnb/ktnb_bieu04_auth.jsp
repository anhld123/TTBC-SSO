<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<style type="text/css">
    *{
        font: 12px Arial, Helvetica, sans-serif;
    }
    table{
        border-style: solid;
        border-collapse: collapse;
        width: 100%;
        line-height: 19px;
    }
    .tbhead th{
        background-color: #5e5e55;
        font-weight: bold;
        color: #fff;
        text-align: center;
        padding: 5px;
    }
    .cscontent td{
        padding-left:5px;
    }
    .cscontent:hover{
        background-color: #ffff99;
    }

    .cscontent:hover input[type="text"]{
        background-color: #ffff99;
    }
    .tblmain tr td{
        font-weight: bold;
        color: #018c3b;
        word-wrap: break-word;
    }

    input{
        border: 0px;
    }

    .BOLD input[type="text"]
    {
        font-weight: bold;
        font-size: 13px;
        width: 95%;
    }

    .ITALIC input[type="text"]
    {
        font-style: italic;
        font-size: 12px;
        width: 95%;
    }

    input[type="text"]
    {
        width: 95%;
    }

    input[type="button"]
    {
        margin-left: 3px;
    }

    .parameter{
        border: 1px solid black;
        width: 50%;
    }

    #posCD, #quyBc, #namBc, #maCn, #userId{
        width: 70px;
    }
    input[readonly] {
        background-color: #f2f2f2;
        color: #666;
        cursor: not-allowed;
    }
</style>
<table border="1px" id="tableKtnb" style="width: 80%; margin: auto">
                                <tr class="tbhead">
                                    <th style="width: 3%">MS</th>
                                    <th style="width: 60%">Nội dung</th>
                                    <th style="width: 10%">Đơn vị tính</th>
                                    <th style="width: 10%">Kết quả</th>
                                    <th style="width: 10%">Ghi chú</th>
                                </tr>

                                <tr class="tbhead">
                                    <th>(1)</th>
                                    <th>(2)</th>
                                    <th>(3)</th>
                                    <th>(4)</th>
                                    <th>(5)</th>
                                </tr>

                                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                                    <tr height="cscontent">    
                                    <input type="hidden" value="<s:property  value="TT_HIENTHI" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" value="<s:property  value="TT_HIENTHI"/>"/>
                                    <input type="hidden" value="<s:property  value="THUTU" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/> 
                                    <input type="hidden" value="<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                                    <input type="hidden" value="<s:property  value="FONTFORMAT" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].FONTFORMAT" value="<s:property  value="FONTFORMAT"/>"/> 
                                    <input type="hidden" value="<s:property  value="CO_TONGHOP" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP" value="<s:property  value="CO_TONGHOP"/>"/> 
                                    <input type="hidden" value="<s:property  value="D7" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" value="<s:property  value="D7"/>"/>  
                                    <input type="hidden" value="<s:property  value="D4" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" value="<s:property  value="D4"/>"/>  
                                    <input type="hidden" value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D4"/>"/>  

                                    <input type="hidden" value="<s:property  value="D8" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" value="<s:property  value="D8"/>"/>
                                    <input type="hidden" value="<s:property  value="D9" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D9"/>"/>
                                    <input type="hidden" value="<s:property  value="TEN" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" value="<s:property  value="TEN"/>"/> 
                                    <input type="hidden" value="<s:property  value="D50" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D50" value="<s:property  value="D50"/>"/>
                                    <s:if test="D4.equalsIgnoreCase('1')">
                                        <td class="D0 D1 D2 D4"><s:property  value="TT_HIENTHI" /></td>
                                        <td class="D1 D2 D4"><s:property  value="D1" /></td>
                                        <td class="D0 D1 D2 D4"><s:property  value="D2" /></td>
                                        <td style="height: 30px" class="D4"></td><td style="height: 30px" class="D4"></td>
                                        </s:if>
                                        <s:elseif test="D4.equalsIgnoreCase('2')">
                                        <td class="D0 D1 D3"><s:property  value="TT_HIENTHI" /></td>
                                        <td class="D1 D3"><s:property  value="D1" /></td>
                                        <td class="D0 D1 D3"><s:property  value="D2" /></td>
                                    </s:elseif>
                                    <s:elseif test="D4.equalsIgnoreCase('3')">
                                        <td class="D0 D1 "><s:property  value="TT_HIENTHI" /></td>
                                        <td class="D1"><s:property  value="D1" /></td>
                                        <td class="D0 D1"><s:property  value="D2" /></td>
                                    </s:elseif>
                                    <s:if test="D4.equalsIgnoreCase('3')||D4.equalsIgnoreCase('2')">
                                        <s:if test="D5.equalsIgnoreCase('1')">
                                            <td>
                                                <input type="text" value="<s:property  value="D3" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"
                                                       class="number" onfocus="this.select()" onblur="if (this.value == '') {
                                                                   this.value = 0
                                                               }
                                                               ;" 
                                                       style="height: 30px;width: 95%"
                                                       <s:if test="TT_HIENTHI.equalsIgnoreCase('5') || TT_HIENTHI.equalsIgnoreCase('6')|| TT_HIENTHI.equalsIgnoreCase('7')"> readonly</s:if>
                                                           />
                                                </td>
                                        </s:if>
                                        <s:else>
                                            <td>
                                                <input type="text" value="<s:property  value="D3" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"
                                                       onfocus="this.select()"
                                                       style="height: 30px;width: 95%;text-align: right"
                                                       <s:if test="TT_HIENTHI.equalsIgnoreCase('5') || TT_HIENTHI.equalsIgnoreCase('6')|| TT_HIENTHI.equalsIgnoreCase('7')"> readonly</s:if>
                                                           />
                                                </td>
                                        </s:else>
                                        <s:if test="TT_HIENTHI.equalsIgnoreCase('5') || TT_HIENTHI.equalsIgnoreCase('6')|| TT_HIENTHI.equalsIgnoreCase('7')"> <td td style="background: #cccccc; color: #000">Lưu ý không nhập</td></s:if>
                                        <s:else><td style="background: #cccccc;" ></td></s:else>      
                                    </s:if>
                        </tr>

                    </s:iterator>

                </table>       

<script>
    var max_row = 0;
    $(document).ready(function () {
        $('input.number').css({"text-align": "right"});
        $('input.number2').css({"text-align": "right"});
        $('.D0').css({"text-align": "center"});
        $('.D1').css({"color": "#000", "font": "13px Arial, Helvetica, sans-serif"});
        $('.D2').css({"font-weight": "bold"});
        $('.D3').css({"font-style": "italic"});
        $('.D4').css({"background": "#ffff99"});
        $('.D5').css({"background": "#50D4FD"});
        $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
        $('.number2').number(true, 0);
        $(".SOKU").css({"width": "99%%"});
        $(".SOKU1").css({"width": "50px"});
        $(".SOKU2").css({"width": "98%"});
        $(".TD_CHECKBOX").css({"width": "38px"});
        $(".TD_SOKU").css({"width": "80px"});
        $(".TD_TENKH123").css({"width": "110px"});
        $(".TD_TENKH1234").css({"width": "70px"});
        $(".TD_TENTS").css({"width": "190px"});
        $(".TD_SOTK").css({"width": "105px"});
        $(".TD_MAKH").css({"width": "60px"});
        $(".TD_THOIGIAN").css({"width": "auto"});
        $(".TD_MAPGD").css({"width": "99%"});
        $(".TD_BUTTON1").css({"width": "40px"});
        $(".TD_SOTIEN").css({"width": "100px"});
        $(".TEN_KH").css({"width": "50%"});
        $(".TEN_KH1").css({"width": "30%"});
    });
    $('.TEN_KH').focus(function () {
        $(this).closest('tr').addClass('highlight_row');
    });
    $('.TEN_KH').blur(function () {
        $(this).closest('tr').removeClass('highlight_row');
    });

    $(document).ready(function () {
        $(".KT_STT_HT").css({"width": "35px"});
        $(".KT_DT").css({"width": "240px"});

        //An di cac cot chuc nang
        $('.hideColumn').hide();

        //Cac truong bang so --> se co so truong = 0
        $('.number').number(true, 0);

        //Cac truong bang so --> se co so truong = 0
        $('.number2').number(true, 0);
    });

</script>       