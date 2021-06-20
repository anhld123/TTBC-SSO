<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<style>
    input[readonly] {
        /*styling info here*/
        background-color: #99ffff;
    }
</style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>        
        <script src="BCQT/javascript/congcap_m22.js"></script>        
        <script>
            var max_row = 0;
            
            $(document).ready(function() {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function() {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function() {
                $(this).closest('tr').removeClass('highlight_row');
            });


            function deleteRow(indx) {
                
                var r = confirm("Bạn chắc chắn muốn xóa dữ liệu?");
                if (r === true) {
                    var table = document.getElementById("tablem22");
                    var rowCount = table.rows.length - 1; //Dem so dong cua bang
                    if (max_row < rowCount)
                        max_row = rowCount;
                    table.deleteRow(indx);
                    UpdateRowTotal(false);
                } else {
                    return;
                }                
            }

            function addRow(indx) {
                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("tablem22");
                var rowCount = table.rows.length - 1; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
                else
                {
                    max_row++;
                    rowCount = max_row;
                }
                
                var maNDT_Id = "D1_" + (indx-4);    
                var tenNDT_Id = "D2_" + (indx-4);    
                
                if (indx-4 < 0) {
                    var maNDT = "";
                    var tenNDT = "";
                } else {
                    var maNDT = $('#'+maNDT_Id).val();
                    var tenNDT = $('#'+tenNDT_Id).val();
                }
                var newIndex = rowCount-3;
                                
                if (index === 3)
                {
                    var newTr = '<tr>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="'+maNDT+'" id="D1_' + newIndex + '" name="lstDulieuNt[' + newIndex + '].D1" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="'+ tenNDT+'" id="D2_' + newIndex + '" name="lstDulieuNt[' + newIndex + '].D2" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="" id="D3_' + newIndex + '" name="lstDulieuNt[' + newIndex + '].D3" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D4_' + newIndex +'" name="lstDulieuNt[' + newIndex + '].D4" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();" /></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D5_' + newIndex + '"name="lstDulieuNt[' + newIndex + '].D5" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D6_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D6" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D7_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D7" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();" /></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D8_' + newIndex + '"name="lstDulieuNt[' + newIndex + '].D8" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D9_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D9" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D10_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D10" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D11_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D11" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D12_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D12" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D13_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D13" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D14_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D14" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D15_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D15" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D16_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D16" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();" readonly="true"/></td>\n\
                                <td class="TD_THEMXOA"><input type="button" value="Thêm" disabled="true" class="TEN_KH"/></td>\n\
                                <td class="TD_THEMXOA"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                } else {
                    var newTr = '<tr>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="'+maNDT+'" id="D1_' + newIndex + '" name="lstDulieuNt[' + newIndex + '].D1" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="'+ tenNDT+'" id="D2_' + newIndex + '" name="lstDulieuNt[' + newIndex + '].D2" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="" id="D3_' + newIndex + '" name="lstDulieuNt[' + newIndex + '].D3" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D4_' + newIndex +'" name="lstDulieuNt[' + newIndex + '].D4" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();" /></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D5_' + newIndex + '"name="lstDulieuNt[' + newIndex + '].D5" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D6_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D6" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D7_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D7" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();" /></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D8_' + newIndex + '"name="lstDulieuNt[' + newIndex + '].D8" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D9_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D9" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D10_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D10" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D11_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D11" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D12_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D12" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D13_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D13" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D14_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D14" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D15_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D15" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn();"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D16_'+ newIndex + '"name="lstDulieuNt[' + newIndex + '].D16" class="TEN_KH number2" onfocus="this.select();" onblur="sumColumn(); " readonly="true"/></td>\n\
                                <td class="TD_THEMXOA"><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                <td class="TD_THEMXOA"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                }
                $($('table#tablem22 tr')[index]).before(newTr);
                $('.D0').css({"text-align": "center"});
                $(".TD_TEN_KH").css({"width": "80px"});
                $(".TD_CHUCVU").css({"width": "40px"});
                $(".TD_SOTIEN").css({"width": "50px"});
                $(".TD_THEMXOA").css({"width": "10px"});

                $(".TEN_KH").css({"width": "100%"});
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('.TEN_KH').focus(function () {
                    $(this).closest('tr').addClass('highlight_row');
                });
                $('.TEN_KH').blur(function () {
                    $(this).closest('tr').removeClass('highlight_row');
                });
                $('.number').number(true, 0);
                $('.number2').number(true, 0);
                
                UpdateRowTotal(true);
            }
            
            function UpdateRowTotal(status)
            {
                var totalStr = $("#row_total_ID").val();
                //alert(totalStr);
                if (totalStr === '')
                {
                    var oldTotal = 0;
                } else 
                {
                    var oldTotal = parseInt($("#row_total_ID").val());
                }
                
                if (status === true)
                    var newTotal = oldTotal+1;
                else 
                    var newTotal = oldTotal-1;
                
                $("#row_total_ID").val(newTotal);
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
                BÁO CÁO TÌNH HÌNH NGUỒN VỐN, SỬ DỤNG VỐN NHẬN ỦY THÁC, VỐN GÓP CỦA ĐỊA PHƯƠNG
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                <a href="table_bcqt_pl01.jsp"></a>
                Đơn vị tính: Nghìn Đồng
            </div>
            <table border="1" class="editDelete" id="tablem22" align="center">
                <colgroup>
                    <col style="mso-width-source:userset;mso-width-alt:3296;width:77pt" width="103" />
                    <col style="mso-width-source:userset;mso-width-alt:3520;width:83pt" width="110" />
                    <col style="width:54pt" width="72" />
                    <col style="mso-width-source:userset;mso-width-alt:2272;width:53pt" width="71" />
                    <col style="mso-width-source:userset;mso-width-alt:2336;width:55pt" width="73" />
<!--                    <col style="mso-width-source:userset;mso-width-alt:2336;width:55pt" width="73" />
                    <col style="mso-width-source:userset;mso-width-alt:2336;width:55pt" width="73" />
                    <col style="mso-width-source:userset;mso-width-alt:2336;width:55pt" width="73" />-->
                    <col span="2" style="mso-width-source:userset;mso-width-alt:1952;
                         width:46pt" width="61" /><col style="width:54pt" width="72" />
                    <col style="mso-width-source:userset;mso-width-alt:1952;width:46pt" width="61" />
                    <col style="width:54pt" width="72" />
                    <col style="mso-width-source:userset;mso-width-alt:3968;width:93pt" width="124" />
                    <col style="mso-width-source:userset;mso-width-alt:1920;width:45pt" width="60" />
                    <col style="mso-width-source:userset;mso-width-alt:3744;width:88pt" width="117" />                    
                    
                </colgroup>
                <tr height="21" style="mso-height-source:userset;height:15.75pt">
                    <th class="TD_TEN_KH" height="92" rowspan="2" style="height: 69.0pt; width: 77pt" width="103">
                        Mã nhà đầu tư/ POS</th>
                    <th class="TD_TEN_KH" rowspan="2" style="width: 83pt" width="110">Tên 
                        nhà đầu tư</th>
                    <th class="TD_TEN_KH" rowspan="2" style="width: 54pt" width="72">Tên 
                        chương trình<span style="mso-spacerun:yes">&nbsp;</span></th>
                    <th class="TD_TEN_KH" rowspan="2" style="width: 53pt" width="71">Tổng 
                        nguồn vốn đầu tư KHB</th>
                    <th class="TD_TEN_KH" rowspan="2" style="width: 55pt" width="73">Dư nợ 
                        cho vay nguồn vốn KHB</th>
<!--                    <th class="TD_TEN_KH" rowspan="2" style="width: 55pt" width="73">Dư nợ 
                        trong hạn cho vay nguồn vốn KHB</th>
                    <th class="TD_TEN_KH" rowspan="2" style="width: 55pt" width="73">Dư nợ 
                        quá hạn và khoanh cho vay nguồn vốn KHB</th>
                    <th class="TD_TEN_KH" rowspan="2" style="width: 55pt" width="73">Số dư 
                        quỹ dự phòng cho vay nguồn vốn KHB</th>-->
                    <th class="TD_TEN_KH" rowspan="2" style="width: 46pt" width="61">Tổng 
                        thu lãi (gồm cả thu cấp bù)</th>
                    <th class="TD_TEN_KH" colspan="6" style="width: 338pt" width="450">
                        Tổng chi</th>
                    <th class="TD_TEN_KH" rowspan="2" style="width: 88pt" width="117">
                        Chênh lệch <br />
                        Thu - 
                        Chi</th>
                    <s:if test="Grade.equalsIgnoreCase('1')">
                    <th style="width: 30px;" class="TD_THEMXOA" rowspan="3" colspan="2">Thêm/Xóa</th>                        
                    </s:if>
                </tr>
                <tr height="71" style="mso-height-source:userset;height:53.25pt">
                    <th class="TD_TEN_KH" height="71" style="height: 53.25pt; width: 46pt" width="61">
                        Trả lãi vay chủ đầu tư</th>
                    <th class="TD_TEN_KH" style="width: 54pt" width="72">Chi trả phí dịch 
                        vụ UT, HH</th>
                    <th class="TD_TEN_KH" style="width: 46pt" width="61">Dự phòng rủi ro</th>
                    <th class="TD_TEN_KH" style="width: 54pt" width="72">Bổ sung nguồn vốn 
                        KHB</th>
                    <th class="TD_TEN_KH" style="width: 93pt" width="124">Kinh phí hoạt 
                        động và khen thưởng Ban đại diện HĐQT</th>
                    <th class="TD_TEN_KH" style="width: 45pt" width="60">Chi khác</th>
                </tr>
                <tr height="16" style="height:12.0pt">
                    <th class="TD_TEN_KH" height="16" style="height: 12.0pt; width: 77pt" width="103">
                        1</th>
                    <th class="TD_TEN_KH" style="width: 83pt" width="110">2</th>
                    <th class="TD_TEN_KH" style="width: 54pt" width="72">3</th>
                    <th class="TD_TEN_KH" style="width: 53pt" width="71">4</th>
                    <th class="TD_TEN_KH" style="width: 55pt" width="73">5</th>
<!--                    <th class="TD_TEN_KH" style="width: 46pt" width="61">6</th>
                    <th class="TD_TEN_KH" style="width: 46pt" width="61">7</th>
                    <th class="TD_TEN_KH" style="width: 46pt" width="61">8</th>-->
                    <th class="TD_TEN_KH" style="width: 46pt" width="61">6</th>
                    <th class="TD_TEN_KH" style="width: 46pt" width="61">7</th>
                    <th class="TD_TEN_KH" style="width: 54pt" width="72">8</th>
                    <th class="TD_TEN_KH" style="width: 46pt" width="61">9</th>
                    <th class="TD_TEN_KH" style="width: 54pt" width="72">10</th>
                    <th class="TD_TEN_KH" style="width: 93pt" width="124">11</th>
                    <th class="TD_TEN_KH" style="width: 45pt" width="60">12</th>
                    <th class="TD_TEN_KH" style="width: 88pt" width="117">
                        13=6-7-8-9-10-11-12</th>                     
                </tr>            

                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>  

                        <td>
                            <input type="text" id="D1_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D1" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                   class="TEN_KH" onfocus="this.select();"                                                                                                         
                                   />
                            </td>
                            <td>
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D2" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                   class="TEN_KH" 
                                   onfocus="this.select();"                                          
                                   />
                            </td>
                            <td>
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D3" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                   class="TEN_KH" 
                                   onfocus="this.select();"                                                                        
                                   />
                            </td>
                            <td>
                                <input type="text" id="D4_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D4" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"  
                                   onblur="sumColumn();"                                   
                                   />                        
                            </td>
                            <td>
                                <input type="text" id="D5_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D5" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"                                   
                                   onblur="sumColumn();"                                   
                                   />
                            </td>
<!--                            <td>
                                <input type="text" id="D6_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D6" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"                                   
                                   onblur="sumColumn();"                                   
                                   />
                            </td>
                            <td>
                                <input type="text" id="D7_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D7" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"                                   
                                   onblur="sumColumn();"                                   
                                   />
                            </td>       

                            <td>
                                <input type="text" id="D8_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D8" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"                                   
                                   onblur="sumColumn();"                                   
                                   />
                            </td>       -->

                            <td>
                                <input type="text" id="D9_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D9" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"                                   
                                   onblur="sumColumn();"                                   
                                   />
                            </td>    

                            <td>
                                <input type="text" id="D10_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D10" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"                                   
                                   onblur="sumColumn();"                                   
                                   />
                            </td>    

                            <td>
                                <input type="text" id="D11_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D11" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"                                   
                                   onblur="sumColumn();"                                   
                                   />
                            </td>    

                            <td>
                                <input type="text" id="D12_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D12" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"                                   
                                   onblur="sumColumn();"                                   
                                   />
                            </td>    

                            <td>
                                <input type="text" id="D13_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D13" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"        
                                   onblur="sumColumn();"
                                   />
                            </td>  
                            
                            <td>
                                <input type="text" id="D14_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D14" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"  
                                   onblur="sumColumn();"
                                   />
                            </td>  
                            
                            <td>
                                <input type="text" id="D15_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D15" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"      
                                   onblur="sumColumn();"
                                   />
                            </td>  
                            
                            <td>
                                <input type="text" id="D16_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="D16" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" 
                                   class="TEN_KH number2" 
                                   onfocus="this.select();"                                       
                                   readonly="true"
                                   />
                            </td>  
                            
                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <s:if test="%{#rowstatus.index > 0}">
                                <td class="TD_THEMXOA"><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>
                                  </s:if>
                                <s:if test="%{#rowstatus.index == 0}">
                                <td class="TD_THEMXOA"><input type="button" value="Thêm" class="TEN_KH" disabled="true"/></td>
                                  </s:if>
                                <td class="TD_THEMXOA"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                               
                            </td>
                        </s:if>
                            <!-- CAC TRUONG HIDDEN DUNG DE CAP NHAT    -->
                        <input type="hidden" id="CAP_<s:property  value="%{#rowstatus.index}" />"      
                           value="<s:property  value="CAP" />"/>
                    <input type="hidden" id="CO_CONGCAP_<s:property  value="%{#rowstatus.index}" />"      
                           value="<s:property  value="CO_CONGCAP" />"/>
                    <input type="hidden" value="<s:property  value="MA" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" />
                </tr>
                <s:set var="st_total" value = "lstDulieuNt.size()" />

            </s:iterator>
                
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
<!--                    <td></td>
                    <td></td>
                    <td></td>-->
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                        <td class="TD_THEMXOA"><input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>
                        <td class="TD_THEMXOA"><input type="button" value="Xóa" disabled="true" class="TEN_KH"/></td>
                    
                    
                </tr>

        </table>
        <input type="hidden" name="row_total" 
               value="<s:property value='%{#st_total}'/>" id="row_total_ID"/>    

        <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                   onCompleteTopics="completediv_ss" cssStyle="display: none"/>
    </s:form>
</body>
</html>
