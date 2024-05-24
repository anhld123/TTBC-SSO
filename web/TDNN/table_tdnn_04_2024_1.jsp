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
    @-webkit-keyframes my {
        0% { color: #C40000; } 
        50% { color: #fff;  } 
        100% { color: #C40000;  } 
    }
    @-moz-keyframes my { 
        0% { color: #C40000;  } 
        50% { color: #fff;  }
        100% { color: #C40000;  } 
    }
    @-o-keyframes my { 
        0% { color: #C40000; } 
        50% { color: #fff; } 
        100% { color: #C40000;  } 
    }
    @keyframes my { 
        0% { color: #C40000;  } 
        50% { color: #fff;  }
        100% { color: #C40000;  } 
    } 
    .test {
        background:#ddd;
        font-size:14px;
        font-weight:bold;
        -webkit-animation: my 700ms infinite;
        -moz-animation: my 700ms infinite; 
        -o-animation: my 700ms infinite; 
        animation: my 700ms infinite;
    }
    .image-container {
        display: inline-block; /* Hiển thị trên cùng một dòng */
        vertical-align: top; /* Căn về phía trên của dòng */
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
                $(".STT3").css({"width": "20%"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 2);
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
                BIỂU GIÁM SÁT HOẠT ĐỘNG GIAO DỊCH XÃ QUA CAMERA IP
                <s:if test="!disintctD50.equalsIgnoreCase('1')" ><a style="color: red">(Dữ liệu đã gửi)</a></s:if>
                </div>
                <div style="height:10px"></div>  
                <table border="1" class="editDelete" id="subTable_tmp" align="center">   
                    <input type="hidden" name="checkD50" id="id_D50" value="<s:property value="disintctD50"/>">
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
                        <select id="cboCanBo<s:property value="#rowstatus.index" />" name="cboCanBo" style="border: hidden">
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
                    <s:if test="Grade.equalsIgnoreCase('1')">      
                        <td>Đơn vị công tác/ Chức vụ</td>
                        <td>
                            <select name="namedistinctD6" id="idnamedistinctD6" style="border: hidden"> 
                                <option value="0" style="text-align: center" <s:if test="disintctD6.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="disintctD6.equalsIgnoreCase('1')"> selected </s:if>>Tổng hợp</option>
                                <option value="2" <s:if test="disintctD6.equalsIgnoreCase('2')"> selected </s:if>>Kế toán Ngân quỹ</option>                        
                                <option value="3" <s:if test="disintctD6.equalsIgnoreCase('3')"> selected </s:if>>Kế hoạch nghiệp vụ</option>
                                <option value="4" <s:if test="disintctD6.equalsIgnoreCase('4')"> selected </s:if>>Giám đốc</option>
                                </select>                 
                                &nbsp;&nbsp;&nbsp; <select name="namedistinctD8" id="idnamedistinctD8" style="border: hidden"> 
                                    <option value="0" style="text-align: center" <s:if test="disintctD8.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="disintctD8.equalsIgnoreCase('1')"> selected </s:if>>Cán bộ</option>
                                <option value="2" <s:if test="disintctD8.equalsIgnoreCase('2')"> selected </s:if>>Tổ trưởng/Trưởng phòng</option>                        
                                <option value="3" <s:if test="disintctD8.equalsIgnoreCase('3')"> selected </s:if>>Giám đốc</option>
                                <option value="4" <s:if test="disintctD8.equalsIgnoreCase('4')"> selected </s:if>>Phó giám đốc</option>
                                </select>
                            </td>

                    </s:if>
                    <s:elseif test="Grade.equalsIgnoreCase('2')">

                        <td>Đơn vị công tác/ Chức vụ</td>
                        <td>
                            <select name="namedistinctD6" id="idnamedistinctD6" style="border: hidden"> 
                                <option value="0" style="text-align: center" <s:if test="disintctD6.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="disintctD6.equalsIgnoreCase('1')"> selected </s:if>>Tin học</option>
                                <option value="2" <s:if test="disintctD6.equalsIgnoreCase('2')"> selected </s:if>>Hành chính tổ chức</option>                        
                                <option value="3" <s:if test="disintctD6.equalsIgnoreCase('3')"> selected </s:if>>Kế toán ngân quỹ</option>
                                <option value="4" <s:if test="disintctD6.equalsIgnoreCase('4')"> selected </s:if>>Kế hoạch nghiệp vụ</option>
                                <option value="5" <s:if test="disintctD6.equalsIgnoreCase('5')"> selected </s:if>>Tổng hợp</option>
                                <option value="6" <s:if test="disintctD6.equalsIgnoreCase('6')"> selected </s:if>>Giám đốc</option>
                                </select>                 
                                &nbsp;&nbsp;&nbsp;<select name="namedistinctD8" id="idnamedistinctD8" style="border: hidden"> 
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
                            <select name="namedistinctD6" id="idnamedistinctD6" style="border: hidden"> 
                                <option value="0" style="text-align: center" <s:if test="disintctD6.equalsIgnoreCase('0')"> selected </s:if>>--- Chọn ---</option>
                                <option value="1" <s:if test="disintctD6.equalsIgnoreCase('1')"> selected </s:if>>Ban Tín dụng người nghèo</option>
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
                    <th style="width: 50px">STT</th>
                    <th >Tiêu chí</th>
                    <th style="width: 5%">Thang điểm</th>
                    <th style="width: 5%">Điểm</th>
                    <th style="width: 20%">Ghi chú lỗi vi phạm</th>
                    <th style="width: 28%">Hình ảnh vi phạm <a class="test" style="color: red" title="Người dùng 'Chọn tệp' tối đa 1 ảnh, dung lượng dưới 2Mb -> 'Tải ảnh lên' sau khi báo đăng ảnh thành công -> Lưu dữ liệu để hoàn thành đăng ảnh">(Tối đa 1 ảnh, 2Mb) </a>
                    </th>
                </tr>  
                <tr>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(1)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(2)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(3)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(4)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(5)</th>
                    <th style="color: #000; font-style: italic; font-size: xx-small;">(6)</th>
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

                        <s:if test ="THUTU == 2 ">
                            <input type="hidden" 
                                   name="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   id="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   value="<s:property value='inmage1'/>">
                        </s:if>
                        <s:if test ="THUTU == 4 ">
                            <input type="hidden" 
                                   name="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   id="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   value="<s:property value='inmage3'/>">
                        </s:if>
                        <s:if test ="THUTU == 6 ">
                            <input type="hidden" 
                                   name="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   id="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   value="<s:property value='inmage5'/>">
                        </s:if>
                        <s:if test ="THUTU == 8 ">
                            <input type="hidden" 
                                   name="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   id="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   value="<s:property value='inmage7'/>">
                        </s:if><s:if test ="THUTU == 10 ">
                            <input type="hidden" 
                                   name="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   id="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   value="<s:property value='inmage9'/>">
                        </s:if><s:if test ="THUTU == 12 ">
                            <input type="hidden" 
                                   name="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   id="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   value="<s:property value='inmage11'/>">
                        </s:if><s:if test ="THUTU == 14 ">
                            <input type="hidden" 
                                   name="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   id="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   value="<s:property value='inmage13'/>">
                        </s:if><s:if test ="THUTU == 16 ">
                            <input type="hidden" 
                                   name="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   id="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   value="<s:property value='inmage15'/>">
                        </s:if><s:if test ="THUTU == 18 ">
                            <input type="hidden" 
                                   name="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   id="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   value="<s:property value='inmage17'/>">
                        </s:if><s:if test ="THUTU == 20 ">
                            <input type="hidden" 
                                   name="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   id="check_image<s:property value='%{#rowstatus.index}'/>" 
                                   value="<s:property value='inmage19'/>">
                        </s:if>

                    </div> 

                    <s:if test="KIEUIN != 3 || !KIEUIN.equalsIgnoreCase('3')">  
                        <td class="D0" style="background: #ddd; font-weight: bold; width: 50px"><s:property  value="TT_HIENTHI" /></td>
                        <td style="background: #ddd; width: 40%; font-weight: bold" 
                            id="TEN_<s:property value="%{#rowstatus.index}"/>"><s:property  value="TEN" /></td>
                        <td style="background: #ddd;">
                            <input type="text" class="number2" style="background: #ddd; font-weight: bold;" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"
                                   id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D1" />" readonly>
                        </td>
                        <s:if test="check_form.equalsIgnoreCase('1')"> 
                            <td style="background: #ffffff;">
                                <input type="text" readonly name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                       id="D4_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D4"/>"
                                       class="number2" onkeyup="calc(this);"  onchange="calc(this);"/>
                            </td>
                        </s:if>
                        <s:else>
                            <td style="background: #ddd;">
                                <input type="text" class="number2" style="background: #ddd; font-weight: bold" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"
                                       id="D4_<s:property value="%{#rowstatus.index}"/>" value="<s:property  value="D4" />" readonly>
                            </td>    
                        </s:else>
                        <td style="background: #ddd; font-weight: bold" class="STT3"
                            id="D9_<s:property value="%{#rowstatus.index}"/>"><s:property  value="D9" /></td>   
                        <td style="background: #ddd;"></td>
                    </s:if>
                    <s:elseif test="KIEUIN == 3 || KIEUIN.equalsIgnoreCase('3')">  

                        <td class="D0" style="background: #ddd; width: 50px"><s:property  value="TT_HIENTHI" /></td>
                        <td style="background: #ddd; width: 40%;" id="TEN_<s:property value="%{#rowstatus.index}"/>"><s:property  value="TEN" /></td>
                        <td style="background: #ddd;">
                            <input type="text" class="number2" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"  readonly
                                   style="background: #ddd" id="D1_<s:property value="%{#rowstatus.index}"/>" value="<s:property value="D1" />">
                        </td>
                        <s:if test="check_form.equalsIgnoreCase('1')"> 
                            <td style="background: #ffffff;">
                                <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                       id="D4_<s:property value="%{#rowstatus.index}"/>" value="<s:property value="D4" />"
                                       class="number2" onkeyup="calc(this);"  onchange="calc(this);"
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               check(<s:property value="%{#rowstatus.index}"/>);
                                       "/>
                            </td>
                            <td class="D0">
                                <textarea placeholder="Nhập tối đa 200 ký tự" id="D9_<s:property  value='%{#rowstatus.index}' />" 
                                          style="width: 99%;height: 99%" maxlength="200"
                                          name="lstDulieuNt[<s:property  value='%{#rowstatus.index}' />].D9" maxlength="200"><s:property value='D9'/></textarea>
                            </td>
                        </s:if>
                        <s:else>
                            <td style="background: #ddd;">
                                <input type="text" class="number2" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4"  readonly
                                       style="background: #ddd" id="D4_<s:property value="%{#rowstatus.index}"/>" value="<s:property value="D4" />">
                            </td>  
                            <td style="background: #ddd;" class="STT3"
                                id="D9_<s:property value="%{#rowstatus.index}"/>"><s:property  value="D9" /></td>   

                        </s:else>
                        <td> 
                            <input type="file" id="fileInput<s:property value="%{#rowstatus.index}"/>" multiple>
                            <button id="displayButton<s:property value="%{#rowstatus.index}"/>">Tải lên</button>
                            <input type="hidden" name="imageBase64<s:property value="%{#rowstatus.index}"/>"
                                   id="outputInput<s:property value="%{#rowstatus.index}"/>" >
                            <!--<div id="result"></div>-->
                            <button id="showImage<s:property value='%{#rowstatus.index}'/>">Tải ảnh vi phạm</button>
                            <div id="downloadContainer<s:property value='%{#rowstatus.index}'/>" class="image-container"></div>
                        </td>
                    </s:elseif>

                    </tr>
                </s:iterator>
                <tr style="height: 30px">
                    <td style="font-weight: bold;text-align: center"></td>
                    <td style="font-weight: bold;text-align: center">TỔNG ĐIỂM</td>
                    <td style="font-weight: bold;text-align: right">100</td>
                    <td> <input type="text" value="0" name="sumAll" id="sumAll" class="number2" 
                                style="font-weight: bold; background: #ffffff; color: #000"
                                readonly/></td>
                    <td></td><td></td>

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

                document.getElementById("D4_0").value = getValue(["D4_1"]);
                document.getElementById("D4_2").value = getValue(["D4_3"]);
                document.getElementById("D4_4").value = getValue(["D4_5"]);
                document.getElementById("D4_6").value = getValue(["D4_7"]);
                document.getElementById("D4_8").value = getValue(["D4_9"]);
                document.getElementById("D4_10").value = getValue(["D4_11"]);
                document.getElementById("D4_12").value = getValue(["D4_13"]);
                document.getElementById("D4_14").value = getValue(["D4_15"]);
                document.getElementById("D4_16").value = getValue(["D4_17"]);
                document.getElementById("D4_18").value = getValue(["D4_19"]);
                var tong = sum(["D4_0", "D4_2", "D4_4", "D4_6", "D4_8", "D4_10", "D4_12", "D4_14", "D4_16", "D4_18"]);
                document.getElementById("sumAll").value = parseInt(tong);
            }

            function check(index) {
                var D1 = parseInt(document.getElementById("D1_" + index).value);
                var D4 = parseInt(document.getElementById("D4_" + index).value);
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

            $(document).ready(function () {
                // Attach click event to displayButton dynamically
                $(document).on('click', '[id^=displayButton]', function () {
                    const index = $(this).attr('id').replace('displayButton', '');
                    const outputInput = document.getElementById('outputInput' + index);
                    const fileInput = document.getElementById('fileInput' + index);
                    const files = fileInput.files;
                    const base64Strings = [];
                    let successAlertShown = false; // Flag to track if the success alert has been shown

                    if (files.length > 5) {
                        alert("Chỉ cho phép tải tối đa 5 tệp tin");
                        return;
                    }

                    let imagesLoaded = 0;
                    const images = [];

                    for (let i = 0; i < files.length; i++) {
                        const file = files[i];

                        const reader = new FileReader();
                        const getSizeImage = file.size;

                        if (getSizeImage > 2 * 1024 * 1024) {
                            alert("Chỉ cho phép tải tệp tin nhỏ hơn 2Mb");
                            return;
                        }

                        reader.onload = function (e) {
                            const img = new Image();
                            img.onload = function () {
                                images.push(img);
                                imagesLoaded++;

                                if (imagesLoaded === files.length) {
                                    combineImages(images);
                                }
                            };
                            img.src = e.target.result;
                        };

                        reader.readAsDataURL(file);
                    }

                    function combineImages(images) {
                        const canvas = document.createElement('canvas');
                        const ctx = canvas.getContext('2d');

                        // Assuming all images are of the same size, you can adjust this if necessary
                        const width = images[0].width;
                        const height = images.reduce((sum, img) => sum + img.height, 0);

                        canvas.width = width;
                        canvas.height = height;

                        // Draw each image on the canvas
                        let yOffset = 0;
                        images.forEach(img => {
                            ctx.drawImage(img, 0, yOffset);
                            yOffset += img.height;
                        });

                        // Convert the canvas to a Base64 string
                        const combinedBase64 = canvas.toDataURL('image/jpeg').split(',')[1];
                        outputInput.value = combinedBase64;

                        // Optionally display the combined image
                        const resultImage = new Image();
                        resultImage.src = 'data:image/jpeg;base64,' + combinedBase64;
                        document.getElementById('result').appendChild(resultImage);

                        if (!successAlertShown) {
                            alert("Đăng ảnh thành công");
                            successAlertShown = true;
                        }
                    }
                });

                // Attach click event to showImage dynamically
                $(document).on('click', '[id^=showImage]', function () {
                    const index = $(this).attr('id').replace('showImage', '');
                    const checkImageValue = document.getElementById('check_image' + index).value; // Assuming this should be 'outputInput'
                    const base64Strings = checkImageValue.split(';'); // Split the Base64 string by the delimiter
                    const downloadContainer = document.getElementById('downloadContainer' + index);
                    downloadContainer.innerHTML = ''; // Clear any previous download links

                    if (base64Strings.length > 0 && base64Strings[0] !== "") {
                        base64Strings.forEach((base64String, index) => {
                            // Create a link for downloading the image
                            const downloadLink = document.createElement('a');
                            downloadLink.href = "data:image/jpeg;base64," + base64String;
                            downloadLink.download = `image_` + (index + 1) + `.jpg`; // Set the filename for the downloaded image
                            downloadLink.innerHTML = `Ảnh vi phạm `;
                            downloadContainer.appendChild(downloadLink);

                            // Add a line break for readability
                            downloadContainer.appendChild(document.createElement('br'));
                        });
                    } else {
                        alert("Lỗi tải ảnh!");
                    }
                });
            });

            function initTable() {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;

                for (var i = 0; i < rowcount; i++) {
                    try {
                        var image_on = document.getElementById("check_image" + i).value;
                        if (image_on === null || image_on === "") {
                            document.getElementById("showImage" + i).style.display = 'none';
                        }
                    } catch (e) {
//                        console.log("Lỗi " + i + ": " + e.message);
                    }
                }
            }
            calc();
            initTable();
        </script>
    </body>
</html>
