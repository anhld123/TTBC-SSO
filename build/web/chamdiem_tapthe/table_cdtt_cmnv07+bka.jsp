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
                $(".TD_THUTU").css({"width": "3%"});
                $(".TD_TEN_KH").css({"width": "50px"});
                $(".TD_TENTS").css({"width": "120px"});
                $(".TD_MATS").css({"width": "85px"});
                $(".TD_NGUYENGIA").css({"width": "8%"});
                $(".TD_THOIGIAN").css({"width": "6%"});
                $(".TD_SOLUONG").css({"width": "4%"});
                $(".TD_TYLE").css({"width": "4%"});
                $(".TD_THEMXOA").css({"width": "5%"}); 
                $(".TD_CBTH").css({"width": "12%"});
                $(".TD_GHICHU").css({"width": "15%"});
                $(".TD_CHITIEU").css({"width": "20%"});
                $(".TD_NOIDUNG").css({"width": "12%"});
                $(".TD_NOIDUNG06A").css({"width": "35%"});
                $(".TEN_KH").css({"width": "100%"});
            });
//            $('.TEN_KH').focus(function () {
//                $(this).closest('tr').addClass('highlight_row');
//            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        <script>
            function deleteRow(indx) {
                var table = document.getElementById("tablecdtt07");
                var rowCount = table.rows.length - 2; //Dem so dong cua bang

                if (max_row < rowCount)
                    max_row = rowCount;

//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
                
            }

            function addRow(indx, ma) {
//                sleep(1000);                
                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("tablecdtt07");
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
                                <td align = "right" class="TD_THUTU"><input type="text" value="" id="TT_HIENTHI' + rowCount + '" name="lstDulieuNt[' + rowCount + '].TT_HIENTHI" class="TEN_KH " onblur="this.select();sumColumn( ' + code + ');" readonly="readonly" /><input type="hidden" id="id_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].MA" value="' + code + '"/></td>\n\
                                <td align = "right" class="TD_CHITIEU"><input type="text" value="" id="TEN_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].TEN" class="TEN_KH " onblur="this.select();sumColumn( ' + code + ');" /></td>                                \n\
                                <td align = "right" class="TD_NGUYENGIA"><input type="text" value="" id="D1_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D1" class="D1 "  onblur="if (this.value == \'\') {this.value = 0} ;sumColumn07(' + code + ');" onblur="this.select();sumColumn07(' + code + ');" readonly="readonly"/></td>\n\    n\
\n\                             <td align = "right" class="TD_SOLUONG"><input type="text" value="" id="D2_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D2" class="D2 "  onblur="if (this.value == \'\') {this.value = 0} ;sumColumn07(' + code + ');" onblur="this.select();sumColumn07(' + code + ');" readonly="readonly"/></td>\n\    n\
                                <td align = "right" class="TD_SOLUONG"><input type="text" value="0" id="D10_' + rowCount +'_'+ code + '" name="lstDulieuNt[' + rowCount + '].D10" class="D10 number"  onblur="if (this.value == \'\') {this.value = 0} ;sumColumn07(' + code + ');" onblur="this.select();"/></td>\n\    n\
\n\                             <td align = "right" class="TD_GHICHU"><input type="text" value="" id="D11_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D11" class="D11 "  onblur="sumColumn07(' + code + ');" onblur="this.select();sumColumn07(' + code + ');"/></td>\n\    n\
                                <td align = "right" class="TD_SOLUONG"><input type="text" value="0" id="D12_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D12" class="D12 number"  onblur="if (this.value == \'\') {this.value = 0} ;sumColumn07(' + code + ');" onblur="this.select();sumColumn07(' + code + ');"/></td>\n\    n\\    n\
                                <td align = "right" class="TD_GHICHU"><input type="text" value="" id="D13_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D13" class="TEN_KH" onblur="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td class="TD_THEMXOA"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                $($('table#tablecdtt07 tr')[index]).after(newTr);
                $(".TD_THUTU").css({"width": "3%"});
                $(".TD_TEN_KH").css({"width": "50px"});
                $(".TD_TENTS").css({"width": "120px"});
                $(".TD_MATS").css({"width": "85px"});
                $(".TD_THOIGIAN").css({"width": "6%"});
                $(".TD_SOLUONG").css({"width": "4%"});
                $(".TD_NGUYENGIA").css({"width": "8%"});
                 $(".TD_TYLE").css({"width": "4%"});
                $(".TD_GHICHU").css({"width": "15%"});
                $(".TD_CHITIEU").css({"width": "20%"});
                $(".TD_NOIDUNG").css({"width": "12%"});
                $(".TD_CBTH").css({"width": "12%"});
                $(".TEN_KH").css({"width": "100%"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
//                $('.TEN_KH').focus(function () {
//                    $(this).closest('tr').addClass('highlight_row');
//                });
                $('.TEN_KH').blur(function () {
                    $(this).closest('tr').removeClass('highlight_row');
                });
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);

                calculatorMark('.D7_NT'+ma,'D9_'+ma);
                calculatorMark('.D8_NT'+ma,'D10_'+ma);

            }
            
        </script>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_cdtt}" action="SAVE_%{khoa_cdtt}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                PL07/ĐGXL - CÔNG VIỆC ĐỊNH TÍNH ĐỐI VỚI TẬP THỂ PHÒNG/BAN  <font color="red">(Trạng thái: <s:property  value="TT_DUYET"/>)</font> 
            </div>
            &emsp;   
            <s:hidden name="khoa_cdtt"/>
            <!--            <div id="divDonvitinh">
                            Đơn vị tính: Triệu đồng
                        </div>-->
            <table border="1" class="editDelete" id="tablecdtt07" align="center">
            <s:if test="!RULEUSER.equalsIgnoreCase('9')">            
                <tr height="23">
                        <th rowspan="2" class="TD_THUTU">TT</th>
                        <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>  
                        <th rowspan="2"  class="TD_NGUYENGIA">Điểm tối đa</th>  
                        <th rowspan="2"  class="TD_SOLUONG">% điểm</th> 
                        <!--<th rowspan="2" class="TD_SOLUONG">Mã</th>--> 
                        <th colspan="2" class="TD_SOLUONG">Tập thể tự đánh giá</th> 
                        <th colspan="2" >Lãnh đạo phụ trách đánh giá</th> 
                        <th  rowspan="2" style="width: 30px;" class="TD_THEMXOA">Thêm/Xóa</th> 
                    </tr>  
                    <tr height="22">                        
                        <th class="TD_SOLUONG">Điểm</th>
                        <th class="TD_GHICHU">Ghi chú</th>
                        <th class="TD_SOLUONG">Điểm</th>
                        <th class="TD_GHICHU">Ghi chú</th>                                                 
                    </tr> 
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr>  

                            <td align = "center" class="TD_THUTU">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" value="<s:property  value="D19" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" id="D19_<s:property  value="%{#rowstatus.index}" />_<s:property  value="MA" />"
                                <input type="hidden" value="<s:property  value="D20" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" id="D20_<s:property  value="MA" />"/> 
                                <input type="hidden" value="<s:property  value="D21" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" id="D21_<s:property  value="MA" />"/> 
                                <input type="hidden" value="<s:property  value="D22" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" id="D22_<s:property  value="MA" />"/> 
                                <input type="text"  value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0" onblur="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "left" class="TD_CHITIEU">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onblur="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" onblur="this.select();
                                       " readonly="true"/>
                            </td>                            
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number TEN_KH" onblur="this.select();
                                               sumColumn07('<s:property  value="MA"/>');" readonly="true"/>
                            </td>
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" onblur="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " readonly="true"/>
                            </td>  
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number TEN_KH" onblur="this.select();" 
                                       readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D13" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH D0" onblur="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " readonly="true"/>
                            </td>                                                    
                            <td align = "center" class="TD_TEN_KH">
                                <s:if test="FONTFORMAT.equalsIgnoreCase('1')">
                                    <input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex,<s:property  value="MA"/>)" class="TEN_KH"/>
                                </s:if>
                                <s:elseif   test="FONTFORMAT.equalsIgnoreCase('2')">
                                    <input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                                </s:elseif>  
                                <s:else>
                                    
                                </s:else> 
                            </td>                            
                        </tr>
                    </s:if>

                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr>  
                            <td align = "center" class="TD_THUTU">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" value="<s:property  value="D19" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" id="D19_<s:property  value="%{#rowstatus.index}" />_<s:property  value="MA" />"
                                <input type="hidden" value="<s:property  value="D20" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" id="D20_<s:property  value="MA" />"/> 
                                <input type="hidden" value="<s:property  value="D21" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" id="D21_<s:property  value="MA" />"/> 
                                <input type="hidden" value="<s:property  value="D22" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" id="D22_<s:property  value="MA" />"/> 
                                <input type="text"  value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH " onblur="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "left" class="TD_CHITIEU">
                                <input type="text" id="TEN_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onblur="this.select();"
                                       <s:if test="!FONTFORMAT.equalsIgnoreCase('2')">readonly="readonly"</s:if> />
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" onblur="this.select();
                                       " readonly="true"/>
                            </td>                            
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number TEN_KH" onblur="this.select();
                                               sumColumn07('<s:property  value="MA"/>');" />
                            </td>
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH" onblur="this.select();
                                               sumColumn('<s:property  value="MA"/>');"/>
                            </td>  
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number TEN_KH" onblur="this.select();" />
                            </td>                            
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D13" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onblur="this.select();
                                               sumColumn('<s:property  value="MA"/>');"/>
                            </td>                                                                          
                            <td align = "center" class="TD_TEN_KH">
                                <s:if test="FONTFORMAT.equalsIgnoreCase('1')">
                                    <input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex,<s:property  value="MA"/>)" class="TEN_KH"/>
                                </s:if>
                                <s:elseif   test="FONTFORMAT.equalsIgnoreCase('2')">
                                    <input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                                </s:elseif>  
                                <s:else>
                                    
                                </s:else> 
                            </td>                                                                 
                        </tr>
                    </s:if>                   


                </s:iterator>
            </table>                                                        
            </s:if>   
            <s:else>
                <tr height="23">
                        <th rowspan="2" class="TD_THUTU">TT</th>
                        <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>  
                        <th rowspan="2"  class="TD_NGUYENGIA">Điểm tối đa</th>  
                        <th rowspan="2"  class="TD_SOLUONG">% điểm</th> 
                        <!--<th rowspan="2" class="TD_SOLUONG">Mã</th>--> 
                        <th colspan="2" class="TD_SOLUONG">Tập thể tự đánh giá</th> 
                        <th colspan="2" >Lãnh đạo phụ trách đánh giá</th> 
                        <!--<th  rowspan="2" style="width: 30px;" class="TD_THEMXOA">Thêm/Xóa</th>--> 
                    </tr>  
                    <tr height="22">                        
                        <th class="TD_SOLUONG">Điểm</th>
                        <th class="TD_GHICHU">Ghi chú</th>
                        <th class="TD_SOLUONG">Điểm</th>
                        <th class="TD_GHICHU">Ghi chú</th>                                                 
                    </tr> 
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                        
                        <tr>  

                            <td align = "center" class="TD_THUTU">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" value="<s:property  value="D19" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" id="D19_<s:property  value="%{#rowstatus.index}" />_<s:property  value="MA" />"
                                <input type="hidden" value="<s:property  value="D20" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" id="D20_<s:property  value="MA" />"/> 
                                <input type="hidden" value="<s:property  value="D21" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" id="D21_<s:property  value="MA" />"/> 
                                <input type="hidden" value="<s:property  value="D22" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" id="D22_<s:property  value="MA" />"/> 
                                <input type="text"  value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "left" class="TD_CHITIEU">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_NGUYENGIA">
                                <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" onfocus="this.select();
                                       " readonly="true"/>
                            </td>                            
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number TEN_KH" onblur="this.select();
                                               sumColumn07('<s:property  value="MA"/>');" readonly="true"/>
                            </td>
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " readonly="true"/>
                            </td>  
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number TEN_KH" onfocus="this.select();" 
                                       readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D13" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH D0" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " readonly="true"/>
                            </td>                                                    
<!--                            <td align = "center" class="TD_TEN_KH">
                                <s:if test="FONTFORMAT.equalsIgnoreCase('1')">
                                    <input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex,<s:property  value="MA"/>)" class="TEN_KH"/>
                                </s:if>
                                <s:elseif   test="FONTFORMAT.equalsIgnoreCase('2')">
                                    <input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                                </s:elseif>  
                                <s:else>
                                    
                                </s:else> 
                            </td>                            -->
                        </tr>                                                      
                </s:iterator>
            </table>      
            </s:else>
            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <!--        <script>
                    initTable();
                </script>-->
    </body>
</html>
