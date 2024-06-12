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
        width: 90%;
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
    #subTable_tmp {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 550px;
    }
</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var popWindow;
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                $(".STT1").css({"width": "5%"});
                $(".STT2").css({"width": "30%"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 1);
                //            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);

            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });

           
        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;">             
            <div id="divTitle">
                <s:hidden name="khoa_tdnn" id="khoa"/>
                BIỂU TỔNG HỢP KẾT QUẢ ĐÁNH GIÁ VIỆC CHẤP HÀNH CÁC QUY ĐỊNH VỀ GIAO DỊCH XÃ
                <s:if test="!disintctD50.equalsIgnoreCase('1')" ><a style="color: red">(Dữ liệu đã gửi)</a></s:if>
                </div>
                <div style="height:10px"></div>  
                <table border="1" class="editDelete" id="subTable_tmp" align="center">   
                    <input type="hidden" name="checkD50" id="id_D50" value="<s:property value="disintctD50"/>">
                <!--<input type="hidden" name="check_image" id="check_image" value="<s:property value="inmage"/>">-->
                <input type="hidden" value="<s:property value="check_Flag"/>">
                <tr>
                    <th>Thông tin cán bộ kiểm tra</th>
                    <th>Nội Dung</th>                
                </tr>  
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>               
                </tr>
                <tr>
                    <td class="STT2">Cán bộ kiểm tra</td>

                    <td>
                        <select id="cboCanBo<s:property value="#rowstatus.index" />" name="cboCanBo" style="border: hidden"
                                <s:if test="check_form.equalsIgnoreCase('2')">onmousedown="return false" </s:if>>

                                    <option value="000000">----Chọn cán bộ----</option>
                                <s:iterator value="lstCanBo" status="rowstatus" var="language">
                                    <s:if test="%{#language.MaCB == disintctD5}">
                                        <option value="<s:property value="MaCB"/>" selected><s:property value="TenCB"/></option>
                                    </s:if>
                                    <s:else>
                                        <option value='<s:property value="MaCB"/>'><s:property value="TenCB"/></option>  </s:else>
                                </s:iterator>
                        </select>  </td>
                </tr>
                <tr>
                    <s:if test="check_Flag.equalsIgnoreCase('S')">      
                        <td>Đơn vị công tác/ Chức vụ</td>
                        <td>
                            <select name="namedistinctD6" id="idnamedistinctD6" style="border: hidden" 
                                    <s:if test="check_form.equalsIgnoreCase('2')">onmousedown="return false" </s:if>> 
                                <option value="0" style="text-align: center" <s:if test="disintctD6.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="disintctD6.equalsIgnoreCase('1')"> selected </s:if>>Tổng hợp</option>
                                <option value="2" <s:if test="disintctD6.equalsIgnoreCase('2')"> selected </s:if>>Kế toán Ngân quỹ</option>                        
                                <option value="3" <s:if test="disintctD6.equalsIgnoreCase('3')"> selected </s:if>>Kế hoạch nghiệp vụ</option>
                                <option value="4" <s:if test="disintctD6.equalsIgnoreCase('4')"> selected </s:if>>Giám đốc</option>
                                <option value="5" <s:if test="disintctD6.equalsIgnoreCase('5')"> selected </s:if>>Kiểm tra kiểm soát nội bộ</option>

                                </select>                 
                                &nbsp;&nbsp;&nbsp; <select name="namedistinctD8" id="idnamedistinctD8" style="border: hidden"
                                <s:if test="check_form.equalsIgnoreCase('2')">onmousedown="return false" </s:if>> 
                                <option value="0" style="text-align: center" <s:if test="disintctD8.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="disintctD8.equalsIgnoreCase('1')"> selected </s:if>>Cán bộ</option>
                                <option value="2" <s:if test="disintctD8.equalsIgnoreCase('2')"> selected </s:if>>Tổ trưởng/Trưởng phòng</option>                        
                                <option value="5" <s:if test="disintctD8.equalsIgnoreCase('5')"> selected </s:if>>Phó phòng</option>
                                <option value="3" <s:if test="disintctD8.equalsIgnoreCase('3')"> selected </s:if>>Giám đốc</option>
                                <option value="4" <s:if test="disintctD8.equalsIgnoreCase('4')"> selected </s:if>>Phó giám đốc</option>
                                </select>
                            </td>

                    </s:if>
                    <s:elseif test="check_Flag.equalsIgnoreCase('M')">

                        <td>Đơn vị công tác/ Chức vụ</td>
                        <td>
                            <select name="namedistinctD6" id="idnamedistinctD6" style="border: hidden"
                                    <s:if test="check_form.equalsIgnoreCase('2')">onmousedown="return false" </s:if>> 
                                <option value="0" style="text-align: center" <s:if test="disintctD6.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="disintctD6.equalsIgnoreCase('1')"> selected </s:if>>Tin học</option>
                                <option value="2" <s:if test="disintctD6.equalsIgnoreCase('2')"> selected </s:if>>Hành chính tổ chức</option>                        
                                <option value="3" <s:if test="disintctD6.equalsIgnoreCase('3')"> selected </s:if>>Kế toán ngân quỹ</option>
                                <option value="4" <s:if test="disintctD6.equalsIgnoreCase('4')"> selected </s:if>>Kế hoạch nghiệp vụ</option>
                                <option value="5" <s:if test="disintctD6.equalsIgnoreCase('5')"> selected </s:if>>Tổng hợp</option>
                                <option value="6" <s:if test="disintctD6.equalsIgnoreCase('6')"> selected </s:if>>Giám đốc</option>
                                <option value="7" <s:if test="disintctD6.equalsIgnoreCase('7')"> selected </s:if>>Kiểm tra kiểm soát nội bộ</option>

                                </select>                 
                                &nbsp;&nbsp;&nbsp;<select name="namedistinctD8" id="idnamedistinctD8" style="border: hidden"
                                <s:if test="check_form.equalsIgnoreCase('2')">onmousedown="return false" </s:if>> 
                                <option value="0" style="text-align: center" <s:if test="disintctD8.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="disintctD8.equalsIgnoreCase('1')"> selected </s:if>>Cán bộ</option>
                                <option value="2" <s:if test="disintctD8.equalsIgnoreCase('2')"> selected </s:if>>Phó phòng</option>  
                                <option value="3" <s:if test="disintctD8.equalsIgnoreCase('3')"> selected </s:if>>Trưởng phòng</option>
                                <option value="4" <s:if test="disintctD8.equalsIgnoreCase('4')"> selected </s:if>>Phó giám đốc</option>
                                <option value="5" <s:if test="disintctD8.equalsIgnoreCase('5')"> selected </s:if>>Giám đốc</option>
                                </select>
                            </td>

                    </s:elseif>
                    <s:else>

                        <td>Đơn vị công tác/ Chức vụ</td>
                        <td>
                            <select name="namedistinctD6" id="idnamedistinctD6" style="border: hidden"
                                    <s:if test="check_form.equalsIgnoreCase('2')">onmousedown="return false" </s:if>> 
                                <option value="0" style="text-align: center" <s:if test="disintctD6.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="disintctD6.equalsIgnoreCase('1')"> selected </s:if>>Ban Tín dụng người nghèo</option>
                                <option value="2" <s:if test="disintctD6.equalsIgnoreCase('2')"> selected </s:if>>Ban Kiểm tra kiểm soát nội bộ</option>

                                </select>                 
                                &nbsp;&nbsp;&nbsp;<select name="namedistinctD8" id="idnamedistinctD8" style="border: hidden"> 
                                    <option value="0" style="text-align: center" <s:if test="disintctD8.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="disintctD8.equalsIgnoreCase('1')"> selected </s:if>>Cán bộ</option>
                                <option value="2" <s:if test="disintctD8.equalsIgnoreCase('2')"> selected </s:if>>Trưởng ban</option>  
                                <option value="3" <s:if test="disintctD8.equalsIgnoreCase('3')"> selected </s:if>>Phó ban</option>
                                </select>
                            </td>

                    </s:else>

                </tr>
            </table>
            <div style="height:10px"></div>  
            <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px">   
                <tr>
                    <th>STT</th>
                    <th>Tiêu chí</th>
                    <th>Thang điểm</th>
                    <th>Điểm</th>
                </tr>  
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>
                    <input type="hidden" value="<s:property  value="THUTU" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/> 
                    <input type="hidden" value="<s:property  value="TT_HIENTHI" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" value="<s:property  value="TT_HIENTHI"/>"/>  
                    <input type="hidden" value="<s:property  value="TEN" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" value="<s:property  value="TEN"/>"/> 
                    <input type="hidden" value="<s:property  value="NHAPTAY" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
                    <input type="hidden" value="<s:property  value="MA" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
    <!--                    <input type="hidden" value="<s:property  value="D1" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1"/>"/>  -->
                    <input type="hidden" value="<s:property  value="D3" />" id="D3<s:property  value="%{#rowstatus.index}" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D3"/>"/>
                    <input type="hidden" value="<s:property  value="D50" />" id="D50<s:property  value="%{#rowstatus.index}" />"
                           name="nameD50" value="<s:property  value="D50"/>"/>
                    <input type="hidden" value="<s:property  value="KIEUIN" />" id="KIEUIN<s:property  value="%{#rowstatus.index}" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KIEUIN" value="<s:property  value="KIEUIN"/>"/>
                    <div style="display: none">
                        <input id="check<s:property  value='%{#rowstatus.index}' />" type="checkbox" class="myCheckBox" checked
                               name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D18"/>       

                    </div>
                    <s:if test="KIEUIN != 3 || !KIEUIN.equalsIgnoreCase('3')">  
                        <td class="D0 STT1" style="background: #ddd; font-weight: bold"><s:property  value="TT_HIENTHI" /></td>
                        <td style="background: #ddd; width: 85%; font-weight: bold" 
                            id="TEN_<s:property value="%{#rowstatus.index}"/>"><s:property  value="TEN" /></td>
                        <td style="background: #ddd;">
                            <input type="text" class="number2" style="background: #ddd; font-weight: bold" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"
                                   id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D1" />" readonly>
                        </td>
                        <s:if test="check_form.equalsIgnoreCase('1')"> 
                            <td style="background: #ffffff;">
                                <input type="text" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                       id="D4_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D4"/>"
                                       class="number" onkeyup="calc(this);"  onchange="calc(this);"/>
                            </td>
                        </s:if>
                        <s:else>
                            <td style="background: #ddd;">
                                <input type="text" class="number" style="background: #ddd; font-weight: bold" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"
                                       id="D4_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D4" />" readonly>
                            </td>    
                        </s:else>

                    </s:if>
                    <s:elseif test="KIEUIN == 3 || KIEUIN.equalsIgnoreCase('3')">  
                        <td class="D0 STT1" style="background: #ddd;"><s:property  value="TT_HIENTHI" /></td>
                        <td style="background: #ddd; width: 85%;" id="TEN_<s:property value="%{#rowstatus.index}"/>"><s:property  value="TEN" /></td>
                        <td style="background: #ddd;">
                            <input type="text" class="number2" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"  readonly
                                   style="background: #ddd" id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property value="D1" />">
                        </td>
                        <s:if test="check_form.equalsIgnoreCase('1')"> 
                            <td style="background: #ffffff;">
                                <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"
                                       id="D4_<s:property value="%{#rowstatus.index}"/>" value="<s:property value="D4" />"
                                       class="number" onkeyup="calc(this);"  onchange="calc(this);"
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               check(<s:property value="%{#rowstatus.index}"/>);"/>
                            </td>
                        </s:if>
                        <s:else>
                            <td style="background: #ddd;">
                                <input type="text" class="number" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"  readonly
                                       style="background: #ddd" id="D4_<s:property value="%{#rowstatus.index}"/>" value="<s:property value="D4" />">
                            </td>  

                        </s:else>
                    </s:elseif>
                    </tr>
                </s:iterator>
                <tr style="height: 30px; background: #FFE47A">
                    <td style="font-weight: bold;text-align: center">III</td>
                    <td style="font-weight: bold;text-align: center">TỔNG ĐIỂM</td>
                    <td style="font-weight: bold;text-align: right">100</td>
                    <td> <input type="text" value="0" name="sumAll" id="sumAll" class="number2" 
                                style="font-weight: bold; background: #FFE47A; color: #000"
                                readonly/></td>

                </tr>
                <tr style="height: 30px; background: #9ad717">
                    <td style="font-weight: bold;text-align: center"></td>
                    <td style="font-weight: bold;text-align: center">XẾP LOẠI</td>
                    <td></td>
                    <td style="font-weight: bold;text-align: right">
                        <input type="text" value="" name="xeploai" id="xeploai" class="D0" 
                               style="font-weight: bold; background: #9ad717; color: #000"
                               readonly/></td>


                </tr>
            </table><div style="height:20px"></div> 
        </div>      
        <div id="luu_thanhcong"></div>
        <script>
            $(function () {
                $('#select-all').click(function (event) {
                    // Iterate each checkbox
                    $('.myCheckBox').each(function () {
                        if (!this.disabled) {
                            this.checked = $('#select-all').prop('checked');
                            this.value = this.checked ? '1' : '0';
                        }
                    });
                });
            });
            function calc() {
                const getValue = id => parseFloat(document.getElementById(id).value);

                const sum = (ids) => ids.reduce((acc, id) => acc + getValue(id), 0);

                document.getElementById("D4_1").value = sum(["D4_2", "D4_3", "D4_4", "D4_5"]);
                document.getElementById("D4_6").value = sum(["D4_7", "D4_8", "D4_9"]);
                document.getElementById("D4_10").value = sum(["D4_11", "D4_12", "D4_13"]);
                document.getElementById("D4_14").value = sum(["D4_15", "D4_16"]);
                document.getElementById("D4_17").value = getValue("D4_18");
                document.getElementById("D4_19").value = getValue("D4_20");
                document.getElementById("D4_21").value = sum(["D4_22", "D4_23", "D4_24", "D4_25"]);
                document.getElementById("D4_26").value = getValue("D4_27");
                document.getElementById("D4_28").value = getValue("D4_29");
                document.getElementById("D4_30").value = getValue("D4_31");
                document.getElementById("D4_32").value = getValue("D4_33");
                document.getElementById("D4_34").value = getValue("D4_35");

                const total = sum(["D4_1", "D4_6", "D4_10", "D4_14", "D4_17", "D4_19", "D4_21",
                    "D4_26", "D4_28", "D4_30", "D4_32", "D4_34"]);
                document.getElementById("D4_0").value = total;

                document.getElementById("D4_36").value = sum(["D4_37", "D4_39"]);
                document.getElementById("D4_37").value = getValue("D4_38");
                document.getElementById("D4_39").value = sum(["D4_40", "D4_41", "D4_42"]);

                document.getElementById("sumAll").value = total + parseInt(document.getElementById("D4_36").value);
                var xeploaiABC = document.getElementById("sumAll").value;
                if (xeploaiABC > 84) {
                    document.getElementById("xeploai").value = "Tốt";
                } else if (xeploaiABC > 64) {
                    document.getElementById("xeploai").value = "Khá";
                } else if (xeploaiABC > 49) {
                    document.getElementById("xeploai").value = "Trung bình";
                } else {
                    document.getElementById("xeploai").value = "Yếu";
                }
            }

            function check(index) {
                var D1 = parseInt(document.getElementById("D1_" + index).value.replaceAll(',', ''));
                var D4 = document.getElementById("D4_" + index).value.replaceAll(',', '');

                if (D1 < D4)
                {
                    alert("Điểm nhập không thể cao hơn thang điểm!");
                    document.getElementById("D4_" + index).style.background = "red";
                    document.getElementById("D4_" + index).value = 0;
                    calc();
                }
                if (D4 < 0)
                {
                    alert("Không được nhập số âm!");
                    document.getElementById("D4_" + index).style.background = "red";
                    document.getElementById("D4_" + index).value = 0;
                    calc();
                }
                calc();
            }
            calc();
        </script>
    </body>
</html>
