<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>


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
                                        <td>
                                            <s:if test="D7.equalsIgnoreCase('N')">  
                                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TD_MAPGD" onfocus="this.select()"
                                                       onblur="if (this.value == '')
                                                                   ;" readonly="true" style="background: #E7DCDA !important; font-weight: bold; text-align:left ;"/>
                                            </s:if>
                                            <s:if test="D7.equalsIgnoreCase('Y')"> 
                                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TD_MAPGD" onfocus="this.select()"
                                                       onblur="if (this.value == '')
                                                                   ;" readonly="true"/>
                                            </s:if>
                                            <input type="hidden" value="<s:property  value="THUTU" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>  
                                            <input type="hidden" value="<s:property  value="TEN" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" value="<s:property  value="TEN"/>"/> 
                                            <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
                                            <input type="hidden" value="<s:property  value="D7" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" value="<s:property  value="D7"/>"/>  
                                            <input type="hidden" value="<s:property  value="D8" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" value="<s:property  value="D8"/>"/>
                                            <input type="hidden" value="<s:property  value="D9" />"
                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D9"/>">
                                        </td>
                                        <s:if test="D7.equalsIgnoreCase('N')"> 
                                            <td style="width: 50%; color: black ; padding-left: 2px; background: #E7DCDA !important;  font-weight: bold;" readonly="true"><s:property value="D1"/></td>                         
                                        </s:if>
                                        <s:if test="D7.equalsIgnoreCase('Y')"> 
                                            <td style="width: 50%; color: black ; padding-left: 2px; font-weight: normal;" readonly="true"><s:property value="D1"/></td>                         
                                        </s:if>
                                        <td style="color: black; font-weight: normal; background: #E7DCDA !important; text-align: center;" readonly="true">
                                            <s:property value="D2"/>
                                        </td>   

                                        <s:if test="D8.equalsIgnoreCase('Y')">
                                            <s:if test="THUTU.toString().equalsIgnoreCase('39')">
                                                <td>
                                                    <input type="text" value="<s:property  value="D5" />" style="background: #df8505 !important;" step="0.01"
                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU" onfocus="this.select();
                                                           "/>
                                                </td>
                                            </s:if>
                                            <s:if test="!THUTU.toString().equalsIgnoreCase('39')">
                                                <td>
                                                    <input type="text" value="<s:property  value="D5" />" style="background: #df8505 !important;"
                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU number" onfocus="this.select();
                                                           "/>
                                                </td>
                                            </s:if>
                                            <td>
                                                <input type="text" value="<s:property  value="D10" />" placeholder="Lưu ý nhập" readonly="true"
                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                                                       onblur="if (this.value == '')
                                                                   ;"/>
                                            </td>
                                        </s:if>
                                        <s:if test="D8.equalsIgnoreCase('N')" >   
                                            <s:if test="D7.equalsIgnoreCase('Y') && D9.equalsIgnoreCase('N')" >   
                                                <td>
                                                    <input type="text" value="<s:property  value="D5" />"
                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU number" onfocus="this.select()"
                                                           onfocus="this.select()" onblur="if (this.value == '') {
                                                                       this.value = 0
                                                                   }
                                                                   ;"/>
                                                </td>
                                                <td>
                                                    <input type="text" value="<s:property  value="D10" />"
                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                                                           onblur="if (this.value == '')
                                                                       ;"/>
                                                </td>
                                            </s:if>
                                            <s:if test="D7.equalsIgnoreCase('N') && D9.equalsIgnoreCase('N')" >  
                                                <td>
                                                    <input type="text" value="<s:property  value="D5" />" style="background: #E7DCDA !important;" readonly="true"
                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU number2" onfocus="this.select();
                                                           "/>
                                                </td>
                                                <td>
                                                    <input type="text" value="<s:property  value="D10" />" style="background: #E7DCDA !important;" readonly="true"
                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D0 SOKU number" onfocus="this.select();
                                                           "/>
                                                </td>
                                            </s:if>

                                            <s:if test="D7.equalsIgnoreCase('Y') && D9.equalsIgnoreCase('Y')" >
                                                <td>
                                                    <input type="text" value="<s:property  value="D5" />" 
                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU number" onfocus="this.select()"
                                                           onfocus="this.select()" onblur="if (this.value == '') {
                                                                       this.value = 0
                                                                   }
                                                                   ;"/>
                                                </td>
                                                <td>
                                                    <input type="text" value="<s:property  value="D10" />" placeholder="Không nhập nếu không phát sinh" readonly="true"
                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                                                           onblur="if (this.value == '')
                                                                       ;"/>
                                                </td>
                                            </s:if>
                                        </s:if>                                     
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
        $(".SOKU").css({"width": "99%%"});
        $(".SOKU1").css({"width": "50px"});
        $(".TD_CHECKBOX").css({"width": "38px"});
        $(".TD_SOKU").css({"width": "80px"});
        $(".TD_TENKH123").css({"width": "150px"});
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