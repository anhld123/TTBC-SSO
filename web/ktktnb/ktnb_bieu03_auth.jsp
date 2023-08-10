<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<table border="1px" id="tableKtnb">
    <tr class="tbhead">
        <th rowspan="2"class="TD_BUTTON1">STT</th>
        <th rowspan="2">Tên, số, ngày, tháng, năm</th>
        <th rowspan="2">Cơ quan ban hành văn bản</th>
        <th rowspan="2">Nội dung sơ hở, dễ bị lợi dụng để tham nhũng</th>
        <th colspan="3">Kết quả khắc phục</th>
        <th rowspan="2">Ghi chú</th>
        <th rowspan="2">Trạng thái</th>

    </tr>
    <tr class="tbhead">
        <th>Đã được khắc phục theo thẩm quyền</th>
        <th>Chưa khắc phục xong</th>
        <th>Nguyên nhân của việc chưa khắc phục xong</th>
    </tr>
    <tr class="tbhead">
        <th>(1)</th>
        <th>(2)</th>
        <th>(3)</th>
        <th>(4)</th>
        <th>(5)</th>
        <th>(6)</th>
        <th>(7)</th>
        <th>(8)</th>
        <th>(9)</th>

    </tr>

    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
        <tr height="cscontent">    
            <s:if test="D9.equalsIgnoreCase('1') || D9.equalsIgnoreCase('2')">
                <td>
                    <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="SOKU1 D0 number2" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                    <input type="hidden" value="<s:property  value="THUTU" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>  
                    <input type="hidden" value="<s:property  value="NHAPTAY" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/> 
                    <input type="hidden" value="<s:property  value="D30" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30" value="<s:property  value="D30"/>"/> 
                    <input type="hidden" value="<s:property  value="D9" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D9"/>"/> 

                </td>
                <td>
                    <input type="text" value="<s:property  value="D1" />" readonly="true"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                </td>                                  
                <td>
                    <input type="text" value="<s:property  value="D2" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                </td>                                  
                <td>
                    <input type="text" value="<s:property  value="D3" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                </td>                                                                                                

                <td>
                    <input type="text" value="<s:property  value="D4" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                </td>
                <td>
                    <input type="text" value="<s:property  value="D5" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                </td>
                <td>
                    <input type="text" value="<s:property  value="D6" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                </td>
                <td>
                    <input type="text" value="<s:property  value="D10" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"
                           />
                </td>  
                <td></td>
                </s:if> 
                <s:if test="D9.equalsIgnoreCase('3') || D9.equalsIgnoreCase('4')">
                <td>
                    <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="SOKU1 D0 number2" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;" readonly="true" style="background: #E7DCDA !important;"/>
                    <input type="hidden" value="<s:property  value="THUTU" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>  
                    <input type="hidden" value="<s:property  value="NHAPTAY" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/> 
                    <input type="hidden" value="<s:property  value="D30" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30" value="<s:property  value="D30"/>"/> 
                    <input type="hidden" value="<s:property  value="D9" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D9"/>"/> 
                </td>
                <td>
                    <input type="text" value="<s:property  value="D1" />" readonly="true"  style="background: #E7DCDA !important;"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                </td>                                  
                <td>
                    <input type="text" value="<s:property  value="D2" />"  readonly="true"  style="background: #E7DCDA !important;"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                </td>                                  
                <td>
                    <input type="text" value="<s:property  value="D3" />"  readonly="true"  style="background: #E7DCDA !important;"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                </td>                                                                                                

                <td>
                    <input type="text" value="<s:property  value="D4" />"  readonly="true"  style="background: #E7DCDA !important;"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                </td>
                <td>
                    <input type="text" value="<s:property  value="D5" />"  readonly="true"  style="background: #E7DCDA !important;"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                </td>
                <td>
                    <input type="text" value="<s:property  value="D6" />"  readonly="true"  style="background: #E7DCDA !important;"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"/>
                </td>
                <td>
                    <input type="text" value="<s:property  value="D10" />" placeholder="Đã gửi"  style="background: #E7DCDA !important;"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                           onblur="if (this.value == '')
                                       ;"
                           />
                </td> 
                <td  style="background: #E7DCDA !important;"></td>
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
        $('.number').number(true, 0);
        $('.number2').number(true, 0);
        $(".SOKU").css({"width": "97%"});
        $(".TD_CHECKBOX").css({"width": "39px"});
        $(".TD_SOKU").css({"width": "80px"});
        $(".TD_TENKH123").css({"width": "110px"});
        $(".TD_TENTS").css({"width": "190px"});
        $(".TD_SOTK").css({"width": "105px"});
        $(".TD_MAKH").css({"width": "60px"});
        $(".TD_THOIGIAN").css({"width": "auto"});
        $(".TD_MAPGD").css({"width": "45px"});
        $(".TD_BUTTON1").css({"width": "40px"});
        $(".TD_SOTIEN").css({"width": "100px"});
        $(".TEN_KH").css({"width": "50%"});
        $(".SOKU1").css({"width": "90%"});
    });
    $('.TEN_KH').focus(function () {
        $(this).closest('tr').addClass('highlight_row');
    });
    $('.TEN_KH').blur(function () {
        $(this).closest('tr').removeClass('highlight_row');
    });

    $(document).ready(function () {
        $("#update").click(function () {
            document.getElementById("update").disabled = true;
            sleep(1000);
            document.getElementById("update").disabled = false;
        });

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
