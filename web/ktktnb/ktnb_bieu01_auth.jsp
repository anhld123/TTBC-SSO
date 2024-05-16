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
<table  id="tableKtnb" border="1">
    <tr class="tbhead">
        <th class="TD_BUTTON1">MS</th>
        <th class="TD_THOIGIAN">Nội dung</th>
        <th class="TD_TENKH123">Đơn vị tính</th>
        <th class="SOKU1">Kết quả</th>
        <th class="SOKU">Ghi chú</th>
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
        <input type="hidden" value="<s:property  value="TEN" />"
               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" value="<s:property  value="TEN"/>"/> 
        <input type="hidden" value="<s:property  value="NHAPTAY" />"
               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
        <input type="hidden" value="<s:property  value="MA" />"
               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
        <input type="hidden" value="<s:property  value="D7" />"
               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" value="<s:property  value="D7"/>"/>  
        <input type="hidden" value="<s:property  value="D8" />"
               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" value="<s:property  value="D8"/>"/>
        <input type="hidden" value="<s:property  value="D9" />"
               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D9"/>">
        <input type="hidden" value="<s:property  value="D2" />"
               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>">
        <input type="hidden" value="<s:property  value="D3" />"
               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D3"/>">
        <input type="hidden" value="<s:property  value="D50" />"
               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D50" value="<s:property  value="D50"/>"/>
        <s:if test="D3.equalsIgnoreCase('1')">
            <td class="D0 D1 D2 D4"><s:property value="TT_HIENTHI"/></td> 
            <td class="D1 D2 D4"><s:property value="TEN"/></td>
            <td class="D0 D1 D2 D4"><s:property  value="D2"/></td>
        </s:if>
        <s:elseif test="D3.equalsIgnoreCase('2')">
            <td class="D0 D1 D2 D3 D5"> <s:property value="TT_HIENTHI"/></td> 
            <td class="D1 D2 D3 D5"> <s:property value="TEN"/> </td>
            <td class="D0 D1 D2 D3 D5"> <s:property  value="D2"/></td>
        </s:elseif>
        <s:elseif test="D3.equalsIgnoreCase('3')">
            <td class="D0 D1 "> <s:property value="TT_HIENTHI"/></td> 
            <td class="D1 "> <s:property value="TEN"/> </td>
            <td class="D0 D1 "> <s:property  value="D2"/></td>
        </s:elseif>
        <s:else>
            <td class="D0 D1 D3"> <s:property value="TT_HIENTHI"/></td> 
            <td class="D1 D3"> <s:property value="TEN"/> </td>
            <td class="D0 D1 D3"> <s:property  value="D2"/></td>
        </s:else>
        <s:if test="D3.equalsIgnoreCase('1')">
            <td style="height: 30px" class="D4"></td><td style="height: 30px" class="D4"></td>
            </s:if>
            <s:elseif test="D3.equalsIgnoreCase('2')">
            <td style="height: 30px" class="D5"></td><td style="height: 30px" class="D5"></td>
            </s:elseif>
            <s:elseif test="D3.equalsIgnoreCase('3') || D3.equalsIgnoreCase('4')">
            <td>
                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"
                       class="number" onfocus="this.select()"
                       onfocus="this.select()" onblur="if (this.value == '') {
                                   this.value = 0
                               }
                               ;" style="height: 30px;width: 95%"
                       <s:if test="D7.equalsIgnoreCase('2') || D7.equalsIgnoreCase('1')"> readonly</s:if>
                           />

                </td>
            <s:if test="D7.equalsIgnoreCase('2') || D7.equalsIgnoreCase('1')"> <td></td></s:if>
            <s:else>
                <td class="D0">
                    <textarea  placeholder="Nhập tối đa 200 ký tự" id="D10_<s:property  value='%{#rowstatus.index}' />" 
                               name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D10" 
                               style="width: 98%;height: 98%" maxlength="200"><s:property value='D10'/></textarea>
                </td>
            </s:else>
        </s:elseif>
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
        $('.number2').number(true, 2);
        $(".SOKU").css({"width": "20%"});
        $(".SOKU1").css({"width": "50px"});
        $(".TD_SOKU").css({"width": "80px"});
        $(".TD_TENKH123").css({"width": "110px"});
        $(".TD_TENKH1234").css({"width": "70px"});
        $(".TD_THOIGIAN").css({"width": "60%"});
        $(".TD_BUTTON1").css({"width": "40px"});
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
        $("#update").click(function () {
            document.getElementById("update").disabled = true;
            sleep(1000);
            document.getElementById("update").disabled = false;
        });
        $(".KT_STT_HT").css({"width": "35px"});
        $(".KT_DT").css({"width": "240px"});
        $('.hideColumn').hide();
        $('.number').number(true, 0);
        $('.number2').number(true, 0);
    });
    function doClick(id, e)
    {
        var key;
        if (window.event)
            key = window.event.keyCode;     //IE
        else
            key = e.which;     //firefox

        if (key == 13)
        {
            //Get the button the user wants to have clicked
            e.preventDefault();
            e.stopPropagation();
        }
    }
    function fnResetVal() {

    }
    //Check xem du lieu da ok chua
    //Neu ok roi thi goi su kien submit du lieu
    function fnCheckThenSubmit() {
        $("#update").click(function () {
            sleep(1000);
        });
        if (validateRequiredFields()) {
            $("#update").trigger('click');
        }
    }

    function tai_lai_trang() {
        location.reload();
    }

    function sleep(milliSeconds) {
        var startTime = new Date().getTime(); // get the current time
        while (new Date().getTime() < startTime + milliSeconds)
            ; // hog cpu
    }

    function validateRequiredFields() {
        var result = true; //Luu ket qua kiem tra kieu so co dung khong

        $(".number2").each(function (index) {
            //Kiem tra xem co nhap kieu so khong
            if (isNaN(parseFloat($(this).val())) || parseFloat($(this).val()) === 0) {
                result = false;
                alert('Trường nhập bắt buộc khác 0')
                return false;
            }
        });

        if (result == false) {
            $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
        }
        return result;
    }

    function evaluateSum(input) {

        var arrCot = [".D5"]; //Luu cac cot cua du lieu can tinh toan
        for (i = 0; i < arrCot.length; i++) {
            $(arrCot[i]).eq(51).val(parseInt($(arrCot[i]).eq(52).val()) + parseInt($(arrCot[i]).eq(53).val()));
        }

        $(input).css({"border": "1px"});

    }
</script>