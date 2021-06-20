<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="chamdiem_tapthe/js/chamdiem_canhan.js"></script>  
        <script>
            function evaluateSum_row(ma) {
            try { 
                    var d10 = 0;                                    
                    d10 = getValue('D1_' + ma)*getValue('D5_' + ma)/100;
                    document.getElementById('D10_' + ma).value = d10; 
                } catch (e) {
                swal('Lỗi', 'ERROR evaluateSum_row ' + e.toString());            
            }
        }
            function evaluateSum_col(table_id, subid) {
            try {
//                                alert('vao tinh tong');
                var temp = 0;
                var kh_capht = ".KH_CAPHT";
                var kh_congthuc = ".KH_CONGTHUC";
                var kh_ma_ct = ".MA_CT";
                //Thu tu cua i tinh tu 0
                var arrCapht = [6.0, 5.0, 4.0, 3.0, 2.0, 1.0]; //Luu cac cot cua du lieu can tinh toan
                
                var rowCount = $("#" + table_id + " td").closest("tr").length;                                    
                //duyệt cấp cộng tổng hợp
                for (k = 0; k < arrCapht.length; k++)
                {//duyệt số row của bảng để lấy ra công thức.

                    for (i = 0; i < rowCount; i++)
                    {//nếu cấp báo cáo bằng với danh sách mảng của câp báo cáo ở trên và công thức khác null hoặc rỗng
                        if ($(kh_capht).eq(i).val() == arrCapht[k] && $(kh_congthuc).eq(i).val().length > 0)
                        {
                            //lấy ra công thức

                            //                                var tong_dc = sum_mact(valNew, kh_dc, table_id);
                            var ma_ct = $(kh_ma_ct).eq(i).val();
                            //                                console.log('ma_ct=' + ma_ct + ' tongcong=' + tongcong);
                            //Lấy ra khóa của báo cáo
                            var khoa = document.getElementById('khoa').value;

                            var Grade = document.getElementById('Grade').value;

                            var RULEUSER = document.getElementById('RULEUSER').value;
        //                    console.log('arrCapht[k]=' + arrCapht[k] + ' ma_ct=' + ma_ct + ' tongcong=' + tongcong);
                            if (khoa === 'CDTT_PGD' && ma_ct.indexOf('CDTT10') >= 0 && Grade === '1')
                                continue;
                            if (khoa === 'CDTT_CN' && ma_ct.indexOf('CDTT10') >= 0 && Grade === '2' && (subid === "D10" || subid === "D12" || subid === "D16"))
                                continue;
        //                    if (khoa === 'CDTT_CN' && ma_ct.indexOf('CDTT10') >= 0 && Grade === '3' && (subid === "D10" || subid === "D12" || subid === "D16"))
        //                        continue;


                            var congthuc = $(kh_congthuc).eq(i).val();
        //                    console.log('-------------- congthuc = ' + congthuc);
                            //cắt công thức đưa về mảng
                            var valNew = congthuc.split('+');
                            var tongcong = tongcongthuc(valNew, subid);
                            console.log('arrCapht[k]=' + arrCapht[k] + ' ma_ct=' + ma_ct + ' tongcong=' + tongcong + ' RULEUSER=' + RULEUSER + ' subid=' + subid);

                            if (ma_ct === 'CDTT99' && (subid === "D4" || subid === "D5" || subid === "D11" || subid === "D16"))
                                continue;
                            else if (Grade === '3' && (ma_ct === 'CDTT1001' || ma_ct === 'CDTT1002') && RULEUSER != 9 && (subid === "D10" || subid === "D12" || subid === "D16"))
                            {
                                console.log(' ma_ct=' + ma_ct);
                                continue;
                            } else
                                document.getElementById(subid + '_' + ma_ct).value = tongcong;

                        }
                    }

                }
                $('.number2').number(true, 2);
            } catch (e) {
                swal('Lỗi', 'ERROR evaluateSum ' + e.toString());
            }

        }
                
        function getvalue(id_input)
        {
            var value = 0;
            var outvalue = 0;
            try
            {
                value = document.getElementById(id_input).value;
                //                    console.log('id_input=' + id_input + ' value=' + value);
                value = value.replace(/,/g, "");
                return parseFloat(value);
            } catch (e) {
                console.log('ERROR=' + e.toString() + " " + id_input);
                return 0;
            }
        }
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
//                $('.number').number(true, 0);
//                $('input.number').css({"text-align": "right"});
//                $('input.number2').css({"text-align": "right"});
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
                $(".TD_GHICHU").css({"width": "10%"});
                $(".TD_CHITIEU").css({"width": "20%"});
                $(".TD_NOIDUNG").css({"width": "12%"});
                $(".TD_NOIDUNG06A").css({"width": "35%"});
                $(".hideColumn").hide();
                $(".TEN_KH").css({"width": "100%"});
//                if ('<s:property value="RULEUSER"/>' != '9')
//                {
//                    sumColumn('.CONGCAP_D10', document.getElementById("D10_200001"));
//                }
                
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
        <s:form id="id_sv_%{khoa_cdtt}" action="SAVE_%{khoa_cdtt}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />"  
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
               
            <div id="divTitle">
                PL08A/ĐGXL - TIÊU CHÍ ĐÁNH GIÁ MỨC ĐỘ HOÀN THÀNH NHIỆM VỤ ĐỐI VỚI CÁ NHÂN
            </div>                        
            <s:hidden name="khoa_cdtt"/>
            
              <s:iterator value="poscd" status="row">
                <s:hidden name="poscd[%{#row.index}]" />
            </s:iterator>       
            <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">                          
                <tr height="23">
                    <th rowspan="2" class="TD_THUTU">TT</th>
                    <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>  
                    <th rowspan="2"  class="TD_SOLUONG">Điểm tối đa</th>  
                    <th rowspan="2"  class="TD_SOLUONG">% điểm</th> 
                    <th rowspan="2"  class="TD_SOLUONG">Đơn vị tính</th> 
                    <!--<th rowspan="2" class="TD_SOLUONG">Mã</th>--> 
                    <th colspan="3" class="TD_SOLUONG">Cá nhân tự đánh giá</th> 
                    <!--<th colspan="3" >Lãnh đạo phụ trách đánh giá</th>-->                     
                </tr>  
                <tr height="22">                        
                    <th class="TD_SOLUONG">Kết quả thực hiện nhiệm vụ trong tháng đạt: Tỷ lệ; mức độ hoàn thành công việc; các lỗi sai sót tồn tại</th>
                    <th class="TD_SOLUONG">Số điểm đạt (+); Số điểm phải trừ (-)</th>
                    <th class="TD_GHICHU">Ghi chú</th>
<!--                    <th class="TD_SOLUONG">Kết quả thực hiện nhiệm vụ trong tháng đạt: Tỷ lệ; mức độ hoàn thành công việc; các lỗi sai sót tồn tại</th>
                    <th class="TD_SOLUONG">Số điểm đạt (+); Số điểm phải trừ (-)</th>
                    <th class="TD_GHICHU">Ghi chú</th>                                                 -->
                </tr> 
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                    <tr height="16" class="<s:property  value="D30" />"> 
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">                        
                            <td align = "center" class="TD_THUTU">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" value="<s:property  value="KHOA" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KHOA"/>

                                <input type="hidden" value="<s:property  value="THUTU" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/> 
                                <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/> 
                                <input type="hidden" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                                <input type="hidden" value="<s:property  value="D14" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" />  
                                <input type="hidden" value="<s:property  value="D15" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/> 
                                <input type="hidden" value="<s:property  value="D17" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" /> 
                                <input type="hidden" value="<s:property  value="D16" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" /> 
                                <input type="text" type="hidden"  value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0"
                                       readonly="true"/>
                            </td>
                            <td align = "left" class="TD_CHITIEU">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" readonly="true"/>
                            </td>  
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" readonly="true"/>
                            </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" readonly="true"/>
                            </td>

                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number TEN_KH" readonly="true"/>
                            </td>
                            <td align = "left" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH" readonly="true"/>
                            </td>                                                                                                        
                        
                    </s:if>

                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">                         
                            <td align = "center" class="TD_THUTU">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" value="<s:property  value="KHOA" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KHOA"/>

                                <input type="hidden" value="<s:property  value="THUTU" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/> 
                                <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/> 
                                <input type="hidden" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                                <input type="hidden" value="<s:property  value="D14" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14"/> 
                                <input type="hidden" value="<s:property  value="D15" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/> 
                                <input type="hidden" value="<s:property  value="D17" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" /> 
                                <input type="hidden" value="<s:property  value="D16" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" /> 
                                
                                <s:if test="MA.equalsIgnoreCase('CDTT05')  || MA.equalsIgnoreCase('CDTT09') 
                                          || MA.equalsIgnoreCase('CDTT10')|| MA.equalsIgnoreCase('CDTT11')">
                                    <input type="hidden"  value="<s:property  value="TT_HIENTHI" />"
                                            name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH "
                                            readonly="true"/>
                                     <a href="javascript:hienthichitiet('<s:property value="MA"/>',2,'<s:property value='khoa_cdtt'/>')" class="SOKU linkKh">
                                         <s:property value='TT_HIENTHI'/>
                                     </a>
                                </s:if>  
                                <s:else>
                                     <input type="text"  value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH "
                                       readonly="true"/>                               
                                </s:else>    
                                          
                                
                            </td>
                            <td align = "left" class="TD_CHITIEU">
                                <input type="text" id="TEN_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH"
                                       readonly="readonly" />
                                </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />" id="D1_<s:property value='MA'/>"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0"
                                       readonly="readonly"
                            </td>

                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" readonly="true"/>
                            </td> 
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number TEN_KH CONGCAP_D5"
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }; evaluateSum_row('<s:property value='MA'/>');
                                               evaluateSum_col('CHAMDIEMTT_001', 'D10');
                                               " 
                                    <s:if test="D29.equalsIgnoreCase('N')"> readonly="readonly" </s:if>         
                                               />
                            </td>
                           
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number TEN_KH CONGCAP_D10"
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;isInputMark('D1_<s:property value='MA'/>', 'D10_<s:property value='MA'/>');
                                               evaluateSum_col('CHAMDIEMTT_001', 'D10');" 
                                    <s:if test="D29.equalsIgnoreCase('Y')"> readonly="readonly" </s:if>   />
                            </td>
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class=" TEN_KH"/>
                            </td>                                                                                                                                                                                           
                        </s:if>                   
                        <td class="hideColumn"><input type="text" value="<s:property value='D3'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                         <td class="hideColumn"><input type="text" value="<s:property value='D15'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                            <td class="hideColumn"><input type="text" value="<s:property value='MA'/>" name="MA_CT" class="MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                    </tr>        
                    </s:iterator>
                </table>                                                                    
            
            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>                    
        <div id="luu_thanhcong"></div>
    </body>
</html>
