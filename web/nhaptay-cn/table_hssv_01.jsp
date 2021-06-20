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
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_CHECKBOX").css({"width": "20px"});
                $(".TD_TEN_KH").css({"width": "90px"});
                $(".TD_TENTS").css({"width": "120px"});
                $(".TD_SOTK").css({"width": "105px"});
                $(".TD_MATS").css({"width": "65px"});
                $(".TD_THOIGIAN").css({"width": "55px"});
                $(".TD_MAPGD").css({"width": "45px"});
                $(".TD_BUTTON1").css({"width": "10px"});
                $(".TD_SOTIEN").css({"width": "100px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        <script>
            function deleteRow(indx) {
                var table = document.getElementById("tablehssv01");
                var rowCount = table.rows.length - 2; //Dem so dong cua bang

                if (max_row < rowCount)
                    max_row = rowCount;

//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
            }

            function addRow(indx, ma) {
//                sleep(1000);
                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("tablehssv01");
                var rowCount = table.rows.length - 2; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
                else
                {
                    max_row++;
                    rowCount = max_row;
                }
                var code = (ma + rowCount).toString();
//                alert('Tong so dong ' + rowCount);
                var newTr = '<tr>\n\\n\\n\
                                <td value="A"></td>\n\
\n\                             <td align = "right" class="TD_SOTK"><input type="text" value="" id="D1_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D1" class="TEN_KH " onfocus="this.select();" /></td>\n\
                                <input type="hidden" id="id_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].MA" value="100999"/></td>\n\
\n\                             <td align = "right" class="TD_TENTS"><input type="text" value="" id="D2_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D2" class="TEN_KH " onfocus="this.select();" /></td>\n\
                                <td align = "right" class="TD_MATS"><input type="text" value="" id="D3_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D3" class="TEN_KH " onfocus="this.select();" /></td>\n\
                                <td align = "right" class="TD_TENTS"><input type="text" value="" id="D4_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D4" class="TEN_KH  onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_MATS"><input type="text" value="" id="D5_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D5" class="TEN_KH  onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_MATS"><input type="text" value="0" id="D6_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D6" class="TEN_KH number" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_MATS"><input type="text" value="0" id="D7_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D7" class="TEN_KH number" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_SOTK"><input type="text" value="" id="D8_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D8" class="TEN_KH " onfocus="this.select(); sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_THOIGIAN"><input type="text" value="" id="D9_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D9" class="D0 datepicker" placeholder="dd/MM/yyyy" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_THOIGIAN"><input type="text" value="" id="D10_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D10" class="D0 datepicker" placeholder="dd/MM/yyyy" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                $($('table#tablehssv01 tr')[index]).after(newTr);

                
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_CHECKBOX").css({"width": "25px"});
                $(".TD_TEN_KH").css({"width": "90px"});
                $(".TD_TENTS").css({"width": "120px"});
                $(".TD_SOTK").css({"width": "105px"});
                $(".TD_MATS").css({"width": "65px"});
                $(".TD_THOIGIAN").css({"width": "55px"});
                $(".TD_MAPGD").css({"width": "45px"});
                $(".TD_BUTTON1").css({"width": "10px"});
                $(".TD_SOTIEN").css({"width": "100px"});
                $(".TEN_KH").css({"width": "100%"});
            }
            function getMabyNumber(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
            function getValue(id)
            {                
                var value = 0;
                try {
//                    alert(id);
                    value = document.getElementById(id).value;
//                    alert(value);
                    value = value.replace(/,/g, "");
                    if (value == '-1')
                        value = 0.0;
                } catch (e)
                {
                    value = 0.0;
                }
                return parseFloat(value);
            }
            function setValue(id, value)
            {
                try {
                    document.getElementById(id).value = value;
                } catch (e)
                {
//                    alert(e);
                }
            }
            function sumColumn(mainput_tmp)
            {
                
                var mainput = $.trim(mainput_tmp.toString());
                
                try {
//                    alert('\'' + mainput+ '\'');
                    var table = document.getElementById("tablehssv01");
                    var rowcount = table.rows.length;
                    rowcount = rowcount > max_row ? rowcount : max_row;
                    var D6 = 0,D7 = 0 ;
                    var pos = -1;
                    for (var i = 0; i < rowcount; i++)
                    {
                        var matmp = getMabyNumber(i);
                        if (mainput.substr(0, 3) == matmp.substr(0, 3) && matmp.length == mainput.length)
                        {
//                            alert(getValue('D5_' + i));
//Lay gia tri cho cac truong tu D2->d6
                            //neu ky tu cuoi cung cua ma la '1' Vi du voi ma '100001,100030... thi se chi lam voi ma khac '1' o cuoi
                            if (matmp.substr(matmp.length - 2, matmp.length) != '01')
                            {
                                //lay ra gia tri cua truong D7,D8,D9
                                D6 = D6 + getValue('D6_' + i);
                                D7 = D7 + getValue('D7_' + i);
                            } 
                            if (matmp.substr(matmp.length - 2, matmp.length) == '01')
                            {
//                                console.log(getValue('D2_' + i));
                                pos = i;
                            }
                        }
                        
                    }
                    //set gia tri
//                    alert('D6_' + pos)
                    setValue('D6_' + pos, D6);
                    setValue('D7_' + pos, D7);
                }
                catch (e)
                {
                    alert(e);
                }
                  $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
            }
                //                alert('sorown='+$('#tablepl01 tr').length+' socot='+$('#tablepl01 td').length);
            
            function initTable()
            {
                
                <s:if test="Grade.equalsIgnoreCase('1')">
                        //SET GIA TRI CHO SELECT 
                var table = document.getElementById("tablehssv01");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    var bflag = false;
                    var value = '';
                    var d6 = Math.round(getValue('TMP_D6_' + i) * 100 / 100);

                    if (d6 > 0)
                    {
                        $('#D6_' + i).find('option').each(function () {
                            var nn_select = parseFloat($.trim($(this).text()));

                            if (d6 == nn_select)
                            {
                                value = $.trim($(this).val());
//                                alert('d5='+d5+' nn_select='+nn_select);
//                                $(this).val($.trim($(this).text()));
                                bflag = true;
                            }
                        });
                        if (bflag)
                            setValue('D6_' + i, value);
                    }
                    var matmp = getMabyNumber(i);
                    if (matmp.substr(matmp.length - 1, matmp.length) != '1')
                    {
                        sumColumn(matmp);
                    }
                }
                </s:if>
                
            }
        </script>
        
        <style>                                                
            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }            
        </style>

    </head>
    <body>
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:if test="Grade.equalsIgnoreCase('1')">      
                <div id="divTitle">
                    HSSV ĐƯỢC HỖ TRỢ LÃI SUẤT
                </div>
                <s:hidden name="khoa_nhaptaycn"/>
                <div id="divDonvitinh">
                    Đơn vị tính: Đồng
                </div>
                <table border="1" class="editDelete" id="tablehssv01" align="center">
                    <tr>                    
                        <th  class="TD_CHECKBOX"></th> 
                        <th  class="TD_SOTK">Mã món vay</th>                  
                        <th  class="TD_TENTS">Tên khách hàng</th> 
                        <th class="TD_MATS">Mã sinh viên</th>
                        <th class="TD_TENTS">Tên sinh viên</th>  
                        <th class="TD_MATS">CMT HSSV</th>                    
                        <th  class="TD_MATS">Dư nợ</th>
                        <th  class="TD_MATS">Lãi phát sinh</th>
                        <th  class="TD_SOTK">Số tài khoản</th>                                      
                        <th  class="TD_THOIGIAN">Ngày bắt đầu trả nợ</th>
                        <th  class="TD_THOIGIAN">Ngày kết thúc hỗ trợ</th>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <th  class="TD_BUTTON1">Thêm/Xóa</th>
                        </s:if>                    
                    </tr>                
                    <tr>             
                        <th  class="TD_CHECKBOX"></th>
                        <th  class="TD_SOTK">(1)</th>
                        <th  class="TD_TENTS">(2)</th>
                        <th  class="TD_MATS">(3)</th>
                        <th  class="TD_TENTS">(4)</th>
                        <th  class="TD_MATS">(5)</th>
                        <th  class="TD_MATS">(6)</th>

                        <th  class="TD_MATS">(7)</th>
                        <th  class="TD_SOTK">(8)</th>
                        <th  class="TD_THOIGIAN">(9)</th>                    
                        <th  class="TD_THOIGIAN">(10)</th>                    
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <th  class="TD_BUTTON1">(11)</th>
                        </s:if>                    
                    </tr> 
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                        
                            <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                            <tr>
                                <td class="TD_CHECKBOX" >
                                    <input type="text"  readonly="true"/>
                                </td>
                                <td align = "right" class="TD_SOTK" >
                                    <input type="text" id="D1_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D1" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "left" class="TD_TENTS">
                                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                    <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH " onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_MATS">
                                    <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D3" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>

                                <td align = "right" class="TD_MATS">
                                    <input type="text" id="D4_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D4" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>

                                <td align = "right" class="TD_MATS">
                                    <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH D0" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>                                                        
                                <td align = "right" class="TD_THOIGIAN">
                                    <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');" 
                                                   readonly="true"/>
                                </td>
                                <td align = "right" class="TD_THOIGIAN">
                                    <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');" 
                                                   readonly="true"/>
                                </td>

                            <td align = "right" class="TD_SOTK">
                                    <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH number" onfocus="this.select();                                               
                                                   " readonly="true" />
                                </td>
                            <td align = "right" class="TD_THOIGIAN">
                                    <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D0 datepicker" placeholder="dd/MM/yyyy" class="TEN_KH number2" onfocus="this.select();
                                                   " readonly="true"/>
                            </td>                        
                            <td align = "right" class="TD_THOIGIAN">
                                    <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D0 datepicker" placeholder="dd/MM/yyyy" class="TEN_KH number2" onfocus="this.select();
                                                   " readonly="true"/>
                            </td> 

                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <td align = "center" class="TD_BUTTON1">
                                   <s:if test="TT_HIENTHI.equalsIgnoreCase('1')">
                                        <input type="button" value="Thêm" style="height:20px; width:10px"  onclick="addRow(this.parentNode.parentNode.rowIndex,<s:property  value="MA"/>)" class="TEN_KH"/>
                                    </s:if>
                                    <s:else>
                                        <input type="button" value="Xóa" style="height:20px; width:10px"  onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                                    </s:else> 
                                </td>
                            </s:if>


                        </tr>
                        </s:if>

                        <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                            <tr style="font-size:18px;">  
                                <td align = "center" class="TD_CHECKBOX">                                
                                    <input type="checkbox" id ="<s:property  value="%{#rowstatus.index}" />"  class="checkboxpgd" name="lstsaveNT_PGD[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1" />" />
                                </td> 
                                <td align = "right" class="TD_SOTK">
                                    <input type="text" id="D1_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D1" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "left" class="TD_TENTS">
                                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                    <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();"
                                           />
                                </td>
                                <td align = "right" class="TD_MATS">
                                    <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D3" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH " onfocus="this.select();"
                                           />
                                </td> 

                                <td align = "right" class="TD_MATS">
                                    <input type="text" id="D4_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D4" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH " onfocus="this.select();"
                                           />
                                </td> 

                                <td align = "right" class="TD_MATS">
                                    <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH" onfocus="this.select();"
                                           />
                                </td>                            
                                <td align = "right" class="TD_MATS">
                                    <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');"
                                           />
                                </td>                            
                                <td align = "right" class="TD_THOIGIAN">
                                    <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                                   " />
                                </td>

                            <td align = "right" class="TD_SOTK">
                                    <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH" onfocus="this.select();" />
                                </td>
                            <td align = "right" class="TD_THOIGIAN">
                                    <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D0 datepicker" placeholder="dd/MM/yyyy" class="TEN_KH D0" onfocus="this.select();
                                    "/>
                            </td>                    

                            <td align = "right" class="TD_THOIGIAN">
                                    <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D0 datepicker" placeholder="dd/MM/yyyy" class="TEN_KH D0" onfocus="this.select();
                                    "/>
                            </td> 


                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <td align = "center" class="TD_BUTTON1">
                                    <s:if test="TT_HIENTHI.equalsIgnoreCase('1')">
                                        <input type="button" value="Thêm" style="height:20px; width:10px"  onclick="addRow(this.parentNode.parentNode.rowIndex,<s:property  value="MA"/>)" class="TEN_KH"/>
                                    </s:if>
                                    <s:else>
                                        <input type="button" value="Xóa" style="height:20px; width:10px"  onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                                    </s:else> 
                                </td>
                            </s:if>


                        </tr>                        
                        </s:if>                                                            
                    </s:iterator>
                </table>
            </s:if>
            <s:if test="Grade.equalsIgnoreCase('2')">
                <div id="divTitle">
                    TỔNG HỢP HSSV ĐƯỢC HỖ TRỢ LÃI SUẤT
                </div>
                <s:hidden name="khoa_nhaptaycn"/>
                <div id="divDonvitinh">
                    Đơn vị tính: Đồng
                </div>
                <table border="1" class="editDelete" id="tablehssv01" style="width:85%; "  align="center">
                    <tr>                                            
                        <th  class="TD_TENTS">PGD</th>                  
                        <th  class="TD_MATS">Số món</th> 
                        <th class="TD_MATS">Dư nợ</th>
                        <th class="TD_MATS">Lãi phát sinh</th>                                            
                    </tr>                                    
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                                   
                        <tr>
                            <td align = "right" class="TD_TENTS" >
                                <input type="text" id="D1_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D1" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_MATS">
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH number" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_MATS">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH number" onfocus="this.select();"
                                       readonly="true"/>
                            </td>

                            <td align = "right" class="TD_MATS">
                                <input type="text" id="D4_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D4" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH number" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                        </tr>                                                                                                      
                    </s:iterator>
                </table>
            </s:if>    
            <s:if test="Grade.equalsIgnoreCase('3')">
            <div id="divTitle">
                        TRẠNG THÁI GỬI SỐ LIỆU CỦA CHI NHÁNH
                    </div>
                    <p></p>
                    <table border="1" class="editDelete" id="tablepl01" align="center">
                        <tr>
<!--                            <th align = "center"  style="width: 30px;">
                                <s:checkbox id ="allCheck" name="allCheck"/></th>-->
                            <th align = "center"  style="width: 50px;">Mã PGD</th>
                            <th style="width: 100px;">Tên PGD</th>
                            <th style="width: 60px;">Ngày gửi</th>
                            <th style="width: 50px;">User gửi</th>
                            <th style="width: 90px;">Trạng thái xử lý</th>
                            <th style="width: 90px;">Trạng thái gửi</th>
                        </tr>
            <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                
                        <s:if test="D1.equalsIgnoreCase('true')">
                            <tr style="text-align: center; color: #0000FF" onmouseover="mover(this);"  onmouseout="mout(this);">
                                <!--<td align = "center"  style="width: 20px;"><s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="poscd" fieldValue="%{D3}"/></td>-->
                                <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                <td style="width: 60px;"><s:property  value="D8" /></td>
                                <td style="width: 50px;"><s:property  value="D7" /></td>
                                <td style="width: 90px;"><s:property  value="D9" /></td>
                                <td style="width: 90px;"><s:property  value="D11" /></td>
                            </tr>
                        </s:if>
                        <s:else>
                            <tr style="text-align: center; color: red" onmouseover="mover(this);"  onmouseout="mout(this);">
                                <!--<td align = "center"  style="width: 20px;"><s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="poscd" fieldValue="%{D3}"/></td>-->
                                <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                <td style="width: 60px;"><s:property  value="D8" /></td>
                                <td style="width: 50px;"><s:property  value="D7" /></td>
                                <td style="width: 90px;"><s:property  value="D9" /></td>
                                <td style="width: 90px;"><s:property  value="D11" /></td>
                            </tr>
                        </s:else>
                    </s:iterator>
        </s:if>  
                
            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initTable();
        </script>
    </body>
</html>
