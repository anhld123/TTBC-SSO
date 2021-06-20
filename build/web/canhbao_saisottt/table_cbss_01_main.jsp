<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<link rel="stylesheet" type="text/css"  href="css/css/style.css" />

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
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
            });
            $(".TD_STT").css({"width": "5%"});
            $(".TD_SOLIEU").css({"width": "10%"});
            $(".TD_SO").css({"width": "6%"});
            $(".TD_MOTA").css({"width": "20%"});
                
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>     
        
        <script>
    
        
        function initTable()
            {
//                var table = document.getElementById("tableCbssMaim01");
//                var rowcount = table.rows.length;    
//                rowcount = rowcount > max_row ? rowcount : max_row;                
//                for (var i = 0; i < rowcount; i++)
//                {                    
//                    var matmp = getMabyNumber(i);//   
//                    
//                    if(matmp == 1)
//                    {
//                        $('input:checkbox[id='+i+']').attr('checked',true);
//                    }
//                }
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
            
            function hienthichitiet(khoa1, khoa2,comment,chkVal) {
            var ht1 = screen.availHeight - 50;
            var wt1 = screen.width - 50;
            var left1 = 25;
            var top1 = 25;        
            var mapgd = $("#mapgd").val();
            var ngay_bc = $("#ngay_bc_DATE").val();
            
            var dateParts = ngay_bc.split("/");
            var date = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]); 
            //const date = new Date();
            const formattedDate = date.toLocaleDateString('en-GB', {
              day: 'numeric', month: 'short', year: 'numeric'
            }).replace(/ /g, '-');
//            alert(formattedDate)formattedDate
            var numError = parseInt(document.getElementById(chkVal).value.replace(',', ''));
                if (numError > 50) {
                    alert('Vui lòng giải trình bằng file Excel');
                } else {
            var url = "getGiaitrinh.action?khoa_detail=" + khoa1 + khoa2 + "&ngay_bc=" + formattedDate+ "&mapgd=" + mapgd + "&strComment=" + comment;
            popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
             }
        }
        
        function giaitrinhfile(khoa1, khoa2, tenfilexls) {
            var ht1 = 350;
            var wt1 = 1000;
            var left1 = screen.availHeight/3;
            var top1 = screen.width/6;        
            var mapgd = $("#mapgd").val();
            var ngay_bc = $("#ngay_bc_DATE").val();
            
            var dateParts = ngay_bc.split("/");
            var date = new Date(+dateParts[2], dateParts[1] - 1, +dateParts[0]); 
            //const date = new Date();
            const formattedDate = date.toLocaleDateString('en-GB', {
              day: 'numeric', month: 'short', year: 'numeric'
            }).replace(/ /g, '-');
//            alert(formattedDate)formattedDate

            var url = "giaitrinhfile.action?khoa_detail=" + khoa1 + khoa2 + "&ngay_bc=" + formattedDate+ "&mapgd=" + mapgd + "&maxls="+tenfilexls;
            popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        }
            
        </script>
        
        <style>                                                
            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }
            .D0, .number{
                border: 0px !important ;
                background-color: #f2f2f2 !important;
            }
           
        </style>

    </head>
    <body style="font-family: ">
        <s:form id="id_sv_%{khoa_cbss}" action="SAVE_P000" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
                </br>
                <div  id="divTitle">
                    TỔNG HỢP SỐ LIỆU CẢNH BÁO SAI SÓT
                </div>
                <s:hidden name="khoa_cbss"/>
               
                </br>
                <table border="1" class="tbl_cbss_css" id="tableCbssMaim01" style="width: 95%"  align="center">
                    <tr>                                            
                        <th rowspan="2" class="TD_STT">STT</th>
                        <th rowspan="2">Mẫu biểu</th>   
                        <th rowspan="2" class="TD_MOTA">Cảnh báo</th>    
                        <th colspan="3" class="TD_SOLIEU">Số trường hợp cảnh báo</th>
                        <th colspan="3" class="TD_SOLIEU">Dư nợ cảnh báo</th>                                                       
                        <th  rowspan="2" class="TD_STT">Tải Sao kê chi tiết</th> 
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <th colspan="2" class="TD_STT">Giải trình</th>                             
                        </s:if>
                        <s:else>
                            <th colspan="2" class="TD_STT">Phê duyệt</th> 
                        </s:else>    
                          
                    </tr>    
                    <tr >                                            
                        <th class="TD_SO">Số trường hợp cảnh báo</th>                  
                        <th class="TD_SO">Số trường hợp cảnh báo chưa giải trình</th>    
                        <th class="TD_SO">Tăng/giảm số trường hợp cảnh báo chưa giải trình trong kỳ</th>
                        <th class="TD_SOLIEU">Dư nợ cảnh báo</th>     
                        <th class="TD_SOLIEU">Dư nợ cảnh báo chưa giải trình</th>                  
                        <th class="TD_SOLIEU">Tăng/giảm dư nợ cảnh báo chưa giải trình trong kỳ</th> 
                        <th class="TD_STT">Thủ công</th> 
                        <th class="TD_STT">Upload file</th> 
                    </tr>   
                      <tr >                                            
                        <th class="TD_STT">(1)</th>                  
                        <th>(2)</th>    
                        <th class="TD_SO">(3)</th>
                        <th class="TD_SO">(4)</th>     
                        <th class="TD_SO">(5)</th>                  
                        <th class="TD_SOLIEU">(6)</th> 
                        <th class="TD_SOLIEU" >(7)</th>     
                        <th class="TD_SOLIEU">(8)</th>                  
                        <th class="TD_STT">(9)</th> 
                        <th class="TD_STT">(10)</th> 
                        <th class="TD_STT">(11)</th>
                        <th class="TD_STT">(12)</th> 
                    </tr>   
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">     
                        <%--<s:if test="Grade.equalsIgnoreCase('1')">--%>
                            <tr>                                      
                                <td align = "center" class="TD_STT">
                                    <input type="text"  value="<s:property  value="THUTU" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" onfocus="this.select();" class="D0"
                                           readonly="true"/>
                                </td>
                                
                                <td align = "center" style="width: 20px;">
                                    <input type="text"  value="<s:property  value="KHOA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KHOA" onfocus="this.select();" class="D0"
                                           readonly="true"/>
                                </td>
                                
                                <td align = "left" class="TD_MOTA">
                                    <textarea style="border: 0px !important ; background-color: transparent !important; resize: none;" readonly="readonly"><s:property  value="D4" />"</textarea>
                                </td>
                                
                                <td align = "left" class="TD_SO">
                                    <input type="text"  value="<s:property  value="D7" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" onfocus="this.select();" class="number"
                                           readonly="true"/>
                                    <input type="hidden" value="<s:property  value="D1" />"  
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1"/>"/>
                                    <input type="hidden" value="<s:property  value="D2" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>"/>
                                </td>
                                 <td align = "left" class="TD_SO" >
                                    <input type="text"  value="<s:property  value="D8" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" onfocus="this.select();" class="number"
                                           readonly="true"/>
                                </td>
                                
                                <td align = "left" class="TD_SO">
                                    <input type="text"  value="<s:property  value="D9" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" id="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="number"
                                           readonly="true"/>
                                </td>
                                 <td align = "left" class="TD_SOLIEU">
                                    <input type="text"  value="<s:property  value="D10" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" onfocus="this.select();" class="number"
                                           readonly="true"/>
                                </td>
                                
                                <td align = "left" class="TD_SOLIEU">
                                    <input type="text"  value="<s:property  value="D11" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" onfocus="this.select();" class="number"
                                           readonly="true"/>
                                </td>
                                
                                <td align = "left" class="TD_SOLIEU">
                                    <input type="text"  value="<s:property  value="D12" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" onfocus="this.select();" class="number"
                                           readonly="readonly"/>
                                </td>
                                  
                                <td  align="center" class="TD_STT">    
                                    <input type="checkbox" id ="<s:property  value="%{#rowstatus.index}" />"  class="checkboxdat" name="lstsaveNT_DAT[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA" />"/>
                                </td>      
                                <s:if test="Grade.equalsIgnoreCase('1')">
                                    <s:if test="D15.equalsIgnoreCase('1')">
                                        <td align = "center" class="TD_STT">
                                           <a href="javascript:hienthichitiet('<s:property value="D1"/>','<s:property value="D3"/>','<s:property value="D4"/>','lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9')">
                                               Giải trình
                                           </a>
                                        </td>
                                    </s:if>
                                    <s:else>
                                        <td align = "center" class="TD_STT"></td>
                                    </s:else>    
                                </s:if>                                
                                <s:else>
                                    <td align = "center" class="TD_STT">
                                           <a href="javascript:hienthichitiet('<s:property value="D1"/>','<s:property value="D3"/>','<s:property value="D4"/>')">
                                               Chi tiết
                                           </a>
                                        </td>
                                </s:else>  
                                        
                                 <s:if test="Grade.equalsIgnoreCase('1')">
                                    <s:if test="D15.equalsIgnoreCase('1')">
                                        <td align = "center" class="TD_STT">
                                           <a href="javascript:giaitrinhfile('<s:property value="D1"/>','<s:property value="D3"/>','<s:property value="MA"/>')">
                                               Giải trình
                                           </a>
                                        </td>
                                    </s:if>
                                    <s:else>
                                        <td align = "center" class="TD_STT"></td>
                                    </s:else>    
                                </s:if>    
                                        
                                <s:if test="Grade.equalsIgnoreCase('2')">
                                    <s:if test="D15.equalsIgnoreCase('1')">
                                        <td align = "center" class="TD_STT">
<!--                                           <a href="javascript:giaitrinhfile('<s:property value="D1"/>','<s:property value="D3"/>','<s:property value="MA"/>')">
                                               Upload file
                                           </a>-->
                                            Upload file
                                        </td>
                                    </s:if>
                                    <s:else>
                                        <td align = "center" class="TD_STT"></td>
                                    </s:else>    
                                </s:if>            
                              
                            </tr>
                        <%--</s:if>--%>
                                                                                                                                                           
                    </s:iterator>
                </table>                    
                
            <sj:submit id="P000_save" name="P000_save" value="save" targets="divExportReportLink" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initTable();
        </script>
    </body>
    
    
</html>
