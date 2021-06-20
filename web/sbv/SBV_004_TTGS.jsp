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
                var table = document.getElementById("tablems04ttgs");
                var rowCount = table.rows.length - 2; //Dem so dong cua bang

                if (max_row < rowCount)
                    max_row = rowCount;

//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
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
                    var table = document.getElementById("tablems04ttgs");
                    var rowcount = table.rows.length;
                    rowcount = rowcount > max_row ? rowcount : max_row;
                    var D7 = 0,D19 = 0,D13 = 0 ;
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
                                D7 = D7 + getValue('D7_' + i);
                                D13 = D13 + getValue('D13_' + i);
                                D19 = D19 + getValue('D19_' + i);                                
                            } 
                            if (matmp.substr(matmp.length - 2, matmp.length) == '01')
                            {
                                pos = i;
                            }
                            
                        }                          
                        
                    }                    
                    //set gia tri
//                    alert('D8_' + pos)                    
                    setValue('D7_' + pos, D7);
                    setValue('D13_' + pos, D13);
                    setValue('D19_' + pos, D19);                    
                    
//                    var data73=$('.D7_300001').val();
//                    var data74=$('.D7_400001').val();
//                    
//                    var data83=$('.D13_300001').val();
//                    var data84=$('.D13_400001').val();
//                    
//                    var data93=$('.D19_300001').val();
//                    var data94=$('.D19_400001').val();
//         
//                    $('.D7_200001').val(parseFloat(data73) + parseFloat(data74));
//                    $('.D13_200001').val(parseFloat(data83) + parseFloat(data84));
//                    $('.D19_200001').val(parseFloat(data93) + parseFloat(data94));                    
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
                var table = document.getElementById("tablems04ttgs");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    var bflag = false;
                    var value = '';
                    var d7 = Math.round(getValue('TMP_D7_' + i) * 100 / 100);

                    if (d7 > 0)
                    {
                        $('#D7_' + i).find('option').each(function () {
                            var nn_select = parseFloat($.trim($(this).text()));

                            if (d7 == nn_select)
                            {
                                value = $.trim($(this).val());
//                                alert('d5='+d5+' nn_select='+nn_select);
//                                $(this).val($.trim($(this).text()));
                                bflag = true;
                            }
                        });
                        if (bflag)
                            setValue('D7_' + i, value);
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
        <s:form id="id_sv_%{khoa_sbv}" action="SAVE_%{khoa_sbv}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                BÁO CÁO NỢ XẤU THEO NGHỊ QUYẾT SỐ 42 CÓ NHIỀU VƯỚNG MẮC KHI XỬ LÝ, KHÓ CÓ KHẢ NĂNG THU HỒI CỦA KHÁCH HÀNG
            </div>
            <s:hidden name="khoa_sbv"/>
            <div id="divDonvitinh">
                Đơn vị tính: triệu đồng
            </div>            
            <table border="1" class="editDelete" id="tablems04ttgs" align="center">
                <tr>
                    <s:if test="Grade.equalsIgnoreCase('1')">
                        <th class="TD_THOIGIAN">STT</th>
                        <th class="TD_CHITIEU">Tên khách hàng vay</th>
                        <th class="TD_MATS">Mã số thuế</th>
                        <th class="TD_MATS">CMND/ Hộ chiếu/Thẻ căn cước</th>
                        <th class="TD_SOTIEN">Tổng nợ xấu tại TCTD</th> 
                        <th class="TD_SOTIEN">Tổng giá trị tài sản bảo đảm</th>                      
                        <th class="TD_SOTIEN">Dự kiến số nợ xấu không có khả năng thu hồi</th>  
                        <th class="TD_CHITIEU">Nguyên nhân không thu hồi được nợ</th>
                        <th class="TD_CHITIEU">Biện pháp xử lý</th>
                        <th class="TD_CHITIEU">Đề xuất, kiến nghị (nếu có)</th>   
                    </s:if>
                    <s:else>
                        <th class="TD_MATS">Mã PGD</th>
                        <!--<th class="TD_CHITIEU">Tên khách hàng vay</th>-->                       
                        <th class="TD_SOTIEN">Tổng nợ xấu tại TCTD</th> 
                        <th class="TD_SOTIEN">Tổng giá trị tài sản bảo đảm</th>                      
                        <th class="TD_SOTIEN">Dự kiến số nợ xấu không có khả năng thu hồi</th>                           
                    </s:else>
                                                          
                </tr>               
                <tr>        
                    <s:if test="Grade.equalsIgnoreCase('1')">
                        <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(1)</th>
                        <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_CHITIEU">(2)</th>
                        <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_MATS">(4)</th>
                        <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_MATS">(5)</th>
                        <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_SOTIEN">(7)</th>
                        <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_SOTIEN">(13)</th>
                        <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_SOTIEN">(19)</th>
                        <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_CHITIEU">(20)</th>
                        <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_CHITIEU">(21)</th>
                        <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_CHITIEU">(22)</th> 
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
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
                                
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D26" value="<s:property  value="D26"/>"/>
                                
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH " onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_MATS">
                                <input type="text" id="D4_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D4" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_MATS">
                                <input type="text" value="<s:property  value="D5" />"  id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7_<s:property  value="MA"/> TEN_KH number" onfocus="this.select();
                                               " readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D13" />" id="D13_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13_<s:property  value="MA"/> TEN_KH number" onfocus="this.select();
                                               " readonly="true"/>
                            </td>  
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D19" />" id="D19_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="D19_<s:property  value="MA"/> TEN_KH number" onfocus="this.select();" 
                                               readonly="true"/>
                            </td>

                            <td align = "right" class="TD_CHITIEU">
                                <input type="text" value="<s:property  value="D20" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="TEN_KH" onfocus="this.select();" 
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_CHITIEU">
                                <input type="text" value="<s:property  value="D21" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" class="TEN_KH" onfocus="this.select();" 
                                       readonly="true"/>
                            </td>
                      
                            <td align = "right" class="TD_CHITIEU">
                                <input type="text" value="<s:property  value="D22" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22"  class="TEN_KH" onfocus="this.select();" 
                                       readonly="true"/>
                            </td>                       
                    </tr>
                    </s:if>
                    
                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr>  
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" id="TT_HIENTHI_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td align = "left" class="TD_CHITIEU">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D26" value="<s:property  value="D26"/>"/>
                                
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH " onfocus="this.select();" readonly="true"
                                       />
                            </td>
                            <td align = "right" class="TD_MATS">
                                <input type="text" id="D4_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D4" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td align = "right" class="TD_MATS">
                                <input type="text" value="<s:property  value="D5" />"  id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH D0" readonly="true"/>
                            </td>
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7_<s:property  value="MA"/> TEN_KH number"  onfocus="this.select();" readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D13" />" id="D13_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13_<s:property  value="MA"/> TEN_KH number" onfocus="this.select();" readonly="true"/>
                            </td>
                            <td align = "right" class="TD_SOTIEN">
                                <input type="text" value="<s:property  value="D19" />" id="D19_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="D19_<s:property  value="MA"/> TEN_KH number" onfocus="this.select();" 
                                           />
                            </td>

                            <td align = "right" class="TD_CHITIEU">
                                <input type="text" value="<s:property  value="D20" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20"  class="TEN_KH" onfocus="this.select();" />
                            </td>
                            <td align = "right" class="TD_CHITIEU">
                                <input type="text" value="<s:property  value="D21" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21"  class="TEN_KH" onfocus="this.select();" />
                            </td>     
                      
                        <td align = "right" class="TD_CHITIEU">
                                <input type="text" value="<s:property  value="D22" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" class="TEN_KH" onfocus="this.select();" />
                            </td>                       
                    </tr>
                    </s:if>
                    </s:if>
                    
                    <s:if test="Grade.equalsIgnoreCase('2')">
                        <tr>                              
                             <td align = "right" class="TD_MATS">
                                <input type="text" value="<s:property  value="MAPGD" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"  class="TEN_KH" onfocus="this.select();" 
                                       readonly="true"/>
                            </td>                                                                                    
                            
                            <td align = "right" class="TD_CHITIEU">
                                <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D7_<s:property  value="MA"/> TEN_KH number" onfocus="this.select();" readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_CHITIEU">
                                <input type="text" value="<s:property  value="D13" />" id="D13_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13_<s:property  value="MA"/> TEN_KH number" onfocus="this.select();" readonly="true"/>
                            </td>  
                            <td align = "right" class="TD_CHITIEU">
                                <input type="text" value="<s:property  value="D19" />" id="D19_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="D19_<s:property  value="MA"/> TEN_KH number" onfocus="this.select();" 
                                               readonly="true"/>
                            </td>
                                                                             
                        </tr>                                       
                    </s:if>
                    
                    
                    
                </s:iterator>
            </table>
            <sj:submit id="%{khoa_sbv}_save" name="%{khoa_sbv}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
<!--        <script>
            initTable();
        </script>-->
    </body>
</html>
