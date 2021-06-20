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
                $(".TD_TEN_KH").css({"width": "110px"});
                $(".TD_TENTS").css({"width": "120px"});
                $(".TD_GHICHU").css({"width": "150px"});
                $(".TD_MATS").css({"width": "85px"});
                $(".TD_THOIGIAN").css({"width": "30px"});
                $(".TD_MAPOS").css({"width": "50px"});
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
                var table = document.getElementById("tablems05pl");
                var rowCount = table.rows.length - 2; //Dem so dong cua bang

                if (max_row < rowCount)
                    max_row = rowCount;

//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
            }

            function addRow(indx, ma) {
                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("tablems05pl");
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
                                <td align = "right" class="TD_THOIGIAN"><input type="text" value="" id="TT_HIENTHI_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].TT_HIENTHI" class="TEN_KH  onfocus="this.select();"/></td>\n\
\n\                             <td align = "right" class="TD_CHITIEU"><input type="text" value="" id="D2' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D2" class="TEN_KH " onfocus="this.select();sumColumn( ' + code + ');" /><input type="hidden" id="id_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].MA" value="' + code + '"/></td>\n\
                                <td align = "right" class="TD_MAPOS"><input type="text" value="" id="D3_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D3" class="TEN_KH " onfocus="this.select();sumColumn( ' + code + ');" /></td>\n\
                                <td align = "right" class="TD_THOIGIAN"><input type="text" value="" id="D4_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D4" class="TEN_KH  onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_MAPOS"><input type="text" value="" id="D5_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D5" class="D0" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_MAPOS"><input type="text" value="0" id="D6_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D6" class="TEN_KH number D0" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D7_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D7" class="TEN_KH number" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D8_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D8" class="TEN_KH number" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D9_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D9" class="TEN_KH number" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D10_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D10" class="TEN_KH number" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_GHICHU"><input type="text" value="" id="D11_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D11" class="TEN_KH" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                \n\
                                <td><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                $($('table#tablems05pl tr')[index]).after(newTr);

                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_TEN_KH").css({"width": "110px"});
                $(".TD_TENTS").css({"width": "120px"});
                $(".TD_GHICHU").css({"width": "150px"});
                $(".TD_MATS").css({"width": "85px"});
                $(".TD_THOIGIAN").css({"width": "30px"});
                $(".TD_MAPOS").css({"width": "50px"});
                $(".TD_SOTIEN").css({"width": "100px"});
                $(".TEN_KH").css({"width": "100%"});
                $('.TEN_KH').focus(function () {
                    $(this).closest('tr').addClass('highlight_row');
                });
                $('.TEN_KH').blur(function () {
                    $(this).closest('tr').removeClass('highlight_row');
                });
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
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
                    value = document.getElementById(id).value;
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
//                 alert(mainput_tmp);   
                var mainput = $.trim(mainput_tmp.toString());
                try {
//                    alert('\'' + mainput+ '\'');
                    var table = document.getElementById("tablems05pl");
                    var rowcount = table.rows.length;
                    rowcount = rowcount > max_row ? rowcount : max_row;
                    var D6 = 0,D7 = 0,D8 = 0,D9 = 0,D10 = 0 ;
                    var pos = -1;
                    
//                    alert('D8_300001');
                    for (var i = 0; i < rowcount; i++)
                    {
                        var matmp = getMabyNumber(i);
//                        alert(matmp);
       
                        if (mainput.substr(0, 3) == matmp.substr(0, 3) && matmp.length == mainput.length)
                        {
//                            alert(mainput + '-' + matmp);
//                            alert(getValue('D5_' + i));
//Lay gia tri cho cac truong tu D2->d6
                            //neu ky tu cuoi cung cua ma la '1' Vi du voi ma '100001,100030... thi se chi lam voi ma khac '1' o cuoi
                            if (matmp.substr(matmp.length - 2, matmp.length) != '01')
                            {
                                //lay ra gia tri cua truong D7,D8,D9
                                D6 = D6 + getValue('D6_' + i);
                                D7 = D7 + getValue('D7_' + i);
                                D8 = D8 + getValue('D8_' + i);
                                D9 = D9 + getValue('D9_' + i);
                                D10 = D10 + getValue('D10_' + i);
                            } 
                            if (matmp.substr(matmp.length - 2, matmp.length) == '01')
                            {
                                pos = i;
                            }
                            
                        }                          
                        
                    }                    
                    //set gia tri
//                    alert('D8_' + pos)
                    setValue('D6_' + pos, D6);
                    setValue('D7_' + pos, D7);
                    setValue('D8_' + pos, D8);
                    setValue('D9_' + pos, D9);
                    setValue('D10_' + pos, D10);                    
                    
                    var data73=$('.D7_300001').val();
                    var data74=$('.D7_400001').val();
                    
                    var data83=$('.D8_300001').val();
                    var data84=$('.D8_400001').val();
                    
                    var data93=$('.D9_300001').val();
                    var data94=$('.D9_400001').val();
                    
                    var data103=$('.D10_300001').val();
                    var data104=$('.D10_400001').val();
                    $('.D7_200001').val(parseFloat(data73) + parseFloat(data74));
                    $('.D8_200001').val(parseFloat(data83) + parseFloat(data84));
                    $('.D9_200001').val(parseFloat(data93) + parseFloat(data94));
                    $('.D10_200001').val(parseFloat(data103) + parseFloat(data104));
                }
                catch (e)
                {
                    alert(e);
                    console.log(e.toString());
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
                var table = document.getElementById("tablems05pl");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    var bflag = false;
                    var value = '';
                    var d5 = Math.round(getValue('TMP_D5_' + i) * 100 / 100);

                    if (d5 > 0)
                    {
                        $('#D5_' + i).find('option').each(function () {
                            var nn_select = parseFloat($.trim($(this).text()));

                            if (d5 == nn_select)
                            {
                                value = $.trim($(this).val());
//                                alert('d5='+d5+' nn_select='+nn_select);
//                                $(this).val($.trim($(this).text()));
                                bflag = true;
                            }
                        });
                        if (bflag)
                            setValue('D5_' + i, value);
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
    </head>
    <body>
        <s:form id="id_sv_%{khoa_bcqt}" action="SAVE_%{khoa_bcqt}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                BÁO CÁO KIỂM KÊ CHI TIẾT TÀI SẢN CỐ ĐỊNH
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: đồng
            </div>            
            <table border="1" class="editDelete" id="tablems05pl" align="center">
                <tr>
                    <th rowspan="2"  class="TD_THOIGIAN">TT</th>
                    <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>
                    <th rowspan="2"  class="TD_MAPOS">Mã Pos</th>
                    <th rowspan="2">Đặc tả tài sản</th>
                    <th rowspan="2" class="TD_MAPOS">Đơn bị tính</th> 
                    <th colspan="3" >TỔNG CỘNG</th>  
                    <th colspan="2" >TR.ĐÓ: VỐN ĐP, CHO, TẶNG; VỐN KHÁC</th>  
                    <th rowspan="2" class="TD_GHICHU">Ghi chú</th>
                    
                    <s:if test="Grade.equalsIgnoreCase('1')">
                        <th rowspan="2">Thêm/xóa</th>
                    </s:if>
                </tr>
                <tr>                                  
                    <th   class="TD_MATS">Số lượng</th>
                    <th   class="TD_TEN_KH">Nguyên giá</th>
                    <th   class="TD_TEN_KH">GTCL</th>
                    <th   class="TD_TEN_KH">Nguyên giá</th>
                    <th   class="TD_TEN_KH">GTCL</th>
                </tr>
                <tr>         
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(1)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_CHITIEU">(2)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_MAPOS">(3)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_SOLUONG">(4)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_MAPOS">(5)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_MATS">(6)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(7)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(8)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(9)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(10)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_GHICHU">(11)</th>
                     <s:if test="Grade.equalsIgnoreCase('1')">
                        <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_MAPOS">(12)</th>
                    </s:if>
                </tr>              
               
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <s:if test="Grade.equalsIgnoreCase('1')">
                        <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr>  
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" id="TT_HIENTHI_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            
                            <td align = "left" class="TD_CHITIEU">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH " onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_MAPOS">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D4" />"  id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_MAPOS">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH  D0" onfocus="this.select();
                                               " readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_MATS">
                                <input type="text" value="" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number D0" onfocus="this.select();
                                       sumColumn('<s:property  value="MA"/>');"
                                       readonly="true"
                                       />
                            </td>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7_<s:property  value="MA"/> number" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');
                                               " 
                                               readonly="true"/>
                            </td>

                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8_<s:property  value="MA"/> number" onfocus="this.select();
                                       sumColumn('<s:property  value="MA"/>');        
                                       " 
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9_<s:property  value="MA"/> number" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');" 
                                               readonly="true"/>
                            </td>
                      
                        <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10_<s:property  value="MA"/> number" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');
                                               " readonly="true" />
                            </td>
                        <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');
                                               " readonly="true"/>
                            </td>
                        
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <td align = "center" class="TD_MAPOS">
                            <s:if test="TT_HIENTHI.equalsIgnoreCase('1')||TT_HIENTHI.equalsIgnoreCase('2.1')||TT_HIENTHI.equalsIgnoreCase('2.2')">
                                <input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex,<s:property  value="MA"/>)" class="TEN_KH"/>
                            </s:if>
                            <s:else>
                                <s:if test="!TT_HIENTHI.equalsIgnoreCase('2')">
                                    <input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                                </s:if>
                                
                            </s:else> 
                        </td>
                        </s:if>
                        

                    </tr>
                    </s:if>
                    
                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr>  
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" id="TT_HIENTHI_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0" onfocus="this.select();"/>
                            </td>
                            <td align = "left" class="TD_CHITIEU">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH " onfocus="this.select();"
                                       />
                            </td>
                            <td align = "right" class="TD_MAPOS">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();
                                       sumColumn('<s:property  value="MA"/>');"
                                       />
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D4" />"  id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH"
                                       />
                            </td>
                            <td align = "right" class="TD_MAPOS">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH "  onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');
                                               " />
                            </td>                            
                            <td align = "right" class="TD_MATS">
                                <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number D0" onfocus="this.select();
                                       sumColumn('<s:property  value="MA"/>');"
                                       />
                            </td>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7_<s:property  value="MA"/> number" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');
                                               " />
                            </td>

                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8_<s:property  value="MA"/> number" onfocus="this.select();
                                       sumColumn('<s:property  value="MA"/>');        
                                       " />
                            </td>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9_<s:property  value="MA"/> number" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');
                                               " />
                            </td>
                      
                        <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10_<s:property  value="MA"/> number" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');
                                               " />
                            </td>
                        <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');
                                               " />
                            </td>                       
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <td align = "center" class="TD_MAPOS">
                            <s:if test="TEN.equalsIgnoreCase('')">
                                <input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex,<s:property  value="MA"/>)" class="TEN_KH"/>
                            </s:if>
                            <s:else>
                                <input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                            </s:else> 
                        </td>
                        </s:if>
                        

                    </tr>
                    </s:if>
                    </s:if>
                    
                    <s:if test="Grade.equalsIgnoreCase('2')">
                        <tr>  
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" id="TT_HIENTHI_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            
                            <td align = "left" class="TD_CHITIEU">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH " onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_MAPOS">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D4" />"  id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_MAPOS">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH  D0" onfocus="this.select();
                                               " readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_MATS">
                                <input type="text" value="" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number D0" onfocus="this.select();
                                       sumColumn('<s:property  value="MA"/>');"
                                       readonly="true"
                                       />
                            </td>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7_<s:property  value="MA"/> number" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');
                                               " 
                                               readonly="true"/>
                            </td>

                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D8_<s:property  value="MA"/> number" onfocus="this.select();
                                       sumColumn('<s:property  value="MA"/>');        
                                       " 
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9_<s:property  value="MA"/> number" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');" 
                                               readonly="true"/>
                            </td>
                      
                        <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10_<s:property  value="MA"/> number" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');
                                               " readonly="true" />
                            </td>
                        <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" onfocus="this.select();
                                           sumColumn('<s:property  value="MA"/>');
                                               " readonly="true"/>
                            </td>
                        
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <td align = "center" class="TD_MAPOS">
                            <s:if test="TT_HIENTHI.equalsIgnoreCase('1')||TT_HIENTHI.equalsIgnoreCase('2.1')||TT_HIENTHI.equalsIgnoreCase('2.2')">
                                <input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex,<s:property  value="MA"/>)" class="TEN_KH"/>
                            </s:if>
                            <s:else>
                                <s:if test="!TT_HIENTHI.equalsIgnoreCase('2')">
                                    <input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                                </s:if>
                                
                            </s:else> 
                        </td>
                        </s:if>
                        
                        

                    </tr>                                       
                    </s:if>
                    
                    
                    
                </s:iterator>
            </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initTable();
        </script>
    </body>
</html>
