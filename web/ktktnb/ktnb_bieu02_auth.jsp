<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<table border="1px" id="tableKtnb">
    <tr class="tbhead">
        <th class="TD_BUTTON1">STT</th>
        <th>Tên vụ</th>
        <th>Tên cơ quan, tổ chức, đơn vị xảy ra sự việc</th>
        <th>Cơ quan thụ lý, giải quyết vụ việc</th>
        <th>Tóm tắt nội dung vụ việc</th>
        <th>Ghi chú</th>
        <th>Trạng thái</th>

    </tr>
    <tr class="tbhead">
        <th>(1)</th>
        <th>(2)</th>
        <th>(3)</th>
        <th>(4)</th>
        <th>(5)</th>
        <th>(6)</th>
        <th>(7)</th>
    </tr>
    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
        <tr height="cscontent">    
            <td>
                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 number SOKU1" onfocus="this.select()"
                       onblur="if (this.value === '');" />
                <input type="hidden" value="<s:property  value="THUTU" />"
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/> 
                <input type="hidden" value="<s:property  value="MA" />"
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                <input type="hidden" value="<s:property  value="NHAPTAY" />"
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/> 
                <input type="hidden" value="<s:property  value="CO_TONGHOP" />"
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP" value="<s:property  value="CO_TONGHOP"/>"/> 

            </td>
            <td>
                <input type="text" value="<s:property  value="D1" />" 
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="SOKU" onfocus="this.select()"
                       onblur="if (this.value === '');"/>
            </td>                                  
            <td>
                <input type="text" value="<s:property  value="D2" />" 
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="SOKU" onfocus="this.select()"
                       onblur="if (this.value === '');"/>
            </td>                                  
            <td>
                <input type="text" value="<s:property  value="D3" />" 
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="SOKU" onfocus="this.select()"
                       onblur="if (this.value === '');"/>
            </td>                                                                                                

            <td>
                <input type="text" value="<s:property  value="D4" />" 
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="SOKU" onfocus="this.select()"
                       onblur="if (this.value === '');"/>
            </td>

            <td>
                <input type="text" value="<s:property  value="D10" />" 
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                       onblur="if (this.value === '');"
                       />
            </td>  
            <td></td>
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

        $('.number2').number(true, 2);
        $(".SOKU").css({"width": "97%"});
        $(".SOKU1").css({"width": "90%"});
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

        //Cac truong bang so  se co so truong = 0
        $('.number').number(true, 0);

        //Cac truong bang so --> se co so truong = 0
        $('.number2').number(true, 0);
    });        
</script>