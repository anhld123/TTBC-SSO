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
                    d10 = getValue('D1_' + ma) * getValue('D11_' + ma) / 100;
                    document.getElementById('D12_' + ma).value = d10;
                } catch (e) {
                    swal('Lỗi', 'ERROR evaluateSum_row ' + e.toString());
                }
            }


            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
//                $('.number').number(true, 0);
//                $('input.number').css({"text-align": "right"});
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
                $(".TD_GHICHU").css({"width": "10%"});
                $(".TD_CHITIEU").css({"width": "20%"});
                $(".TD_NOIDUNG").css({"width": "12%"});
                $(".TD_NOIDUNG06A").css({"width": "35%"});
                $(".hideColumn").hide();
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });

            function evaluateSum_col(table_id, subid) {

                try {
//                                alert('vao tinh tong');
                    console.log('Bat dau evaluateSum_col');
                    var temp = 0;
                    var kh_capht = ".KH_CAPHT";
                    var kh_congthuc = ".KH_CONGTHUC";
                    var kh_ma_ct = ".MA_CT";
                    //Thu tu cua i tinh tu 0
                    var arrCapht = [6.0, 5.0, 4.0, 3.0, 2.0, 1.0]; //Luu cac cot cua du lieu can tinh toan

                    var rowCount = $("#" + table_id + " td").closest("tr").length;
//                    document.getElementById('D10_CN02').value = 20;
                    //duyệt cấp cộng tổng hợp
                    for (k = 0; k < arrCapht.length; k++)
                    {//duyệt số row của bảng để lấy ra công thức.
                        for (i = 0; i < rowCount; i++)
                        {
                            var D28_khoa = $('.D28_DIEM').eq(i).val();
                            //nếu cấp báo cáo bằng với danh sách mảng của câp báo cáo ở trên và công thức khác null hoặc rỗng
                            if ($(kh_capht).eq(i).val() == arrCapht[k] && $(kh_congthuc).eq(i).val().length > 0)
                            {
                                var ma_ct = $(kh_ma_ct).eq(i).val();
                                var congthuc = $(kh_congthuc).eq(i).val();
                                //lấy ra công thức

                                //                                var tong_dc = sum_mact(valNew, kh_dc, table_id);

                                //                                console.log('ma_ct=' + ma_ct + ' tongcong=' + tongcong);
                                //Lấy ra khóa của báo cáo
                                var khoa = document.getElementById('khoa').value;

                                var Grade = document.getElementById('Grade').value;

                                var RULEUSER = document.getElementById('RULEUSER').value;
                                //cắt công thức đưa về mảng
                                var valNew = congthuc.replace(/-/g, '+').split('+');
                                var tongcong = tongcongthuc_08TH(valNew, subid, congthuc);
                                //console.log('arrCapht[k]=' + arrCapht[k] + ' ma_ct=' + ma_ct + ' tongcong=' + tongcong + ' RULEUSER=' + RULEUSER + ' subid=' + subid);

                                if (D28_khoa === 'DIEM_HT_CV')
                                {
                                    tongcong = tongcongthuc_08TH(valNew, subid, congthuc);
                                    resetvalue(subid, ma_ct);
                                    console.log(tongcong);
                                    if (tongcong >= 71 && tongcong <= 80)
                                    {
                                        document.getElementById(subid + '_' + ma_ct).value = 20;
                                        document.getElementById(subid + '_' + ma_ct + '01').value = 20;
                                    }
                                    if (tongcong >= 66 && tongcong <= 70)
                                    {
                                        document.getElementById(subid + '_' + ma_ct).value = 15;
                                        document.getElementById(subid + '_' + ma_ct + '02').value = 15;
                                    }
                                    if (tongcong >= 61 && tongcong <= 65)
                                    {
                                        document.getElementById(subid + '_' + ma_ct).value = 10;
                                        document.getElementById(subid + '_' + ma_ct + '03').value = 10;
                                    }
                                    if (tongcong <= 60)
                                    {
                                        document.getElementById(subid + '_' + ma_ct).value = 5;
                                        document.getElementById(subid + '_' + ma_ct + '04').value = 5;
                                    }
                                } else if (D28_khoa === 'XEPLOAI')
                                {
                                    resetvalue(subid, ma_ct);
//                                   
//                                    91-100, 81-90, 71-80, dưới 70 HTXS, HTT, HT, KHT
                                    if (tongcong >= 91 && tongcong <= 100)
                                    {
                                        document.getElementById(subid + '_' + ma_ct).value = tongcong;//'HTXS';
                                        document.getElementById(subid + '_' + ma_ct + '01').value = tongcong;//'HTXS';
                                    }
                                    if (tongcong >= 81 && tongcong <= 90)
                                    {
                                        document.getElementById(subid + '_' + ma_ct).value = tongcong;//'HTT';
                                        document.getElementById(subid + '_' + ma_ct + '02').value = tongcong;//'HTT';
                                    }
                                    if (tongcong >= 71 && tongcong <= 80)
                                    {
                                        document.getElementById(subid + '_' + ma_ct).value = tongcong;//'HT';
                                        document.getElementById(subid + '_' + ma_ct + '03').value = tongcong;//'HT';
                                    }
                                    if (tongcong <= 70)
                                    {
                                        document.getElementById(subid + '_' + ma_ct).value = tongcong;//'KHT';
                                        document.getElementById(subid + '_' + ma_ct + '04').value = tongcong;//'KHT';
                                    }
                                } else {
                                    document.getElementById(subid + '_' + ma_ct).value = tongcong;
                                }
                                // document.getElementById(subid + '_' + ma_ct).value = tongcong;
                            } else
                            {
//                                if (D28_khoa === 'DIEM_HT_CV')
//                                {
//                                    tongcong = tongcongthuc_08TH(valNew, subid, congthuc);
//                                    console.log(tongcong);
//                                    if (tongcong >= 71 && tongcong <= 80)
//                                        document.getElementById(subid + '_' + ma_ct).value = 20;
//                                    if (tongcong >= 66 && tongcong <= 70)
//                                        document.getElementById(subid + '_' + ma_ct).value = 15;
//                                    if (tongcong >= 61 && tongcong <= 65)
//                                        document.getElementById(subid + '_' + ma_ct).value = 10;
//                                    if (tongcong <= 60)
//                                        document.getElementById(subid + '_' + ma_ct).value = 5;
//                                }
//                                if (giatri_D10_11 != 0 && giatri_D10_11 != 1 && giatri_D10_11 != 2 && giatri_D10_11 != 3 && giatri_D10_11 != 5
//                                        && giatri_D10_11 != 7 && giatri_D10_11 != 10)
//                                {
//                                    swal('Lỗi', 'Bạn không được nhập khác các giá trị 0,1,2,3,5,7,10 hoặc nhỏ hơn 0 cho chỉ tiêu 4', 'warning');
//                                    cur.style.background = '#ff0000';
//                                    cur.value = 0;
//                                    return;
//                                }
                            }

                        }

                    }
                    $('.number2').number(true, 2);
                } catch (e) {
                    swal('Lỗi', 'ERROR evaluateSum ' + e.toString());
                }

            }
            function resetvalue(subid, ma_ct)
            {
                try {
                    document.getElementById(subid + '_' + ma_ct + '01').value = 0;
                    document.getElementById(subid + '_' + ma_ct + '02').value = 0;
                    document.getElementById(subid + '_' + ma_ct + '03').value = 0;
                    document.getElementById(subid + '_' + ma_ct + '04').value = 0;
                    document.getElementById(subid + '_' + ma_ct + '05').value = 0;

                } catch (e) {

                }
            }
            function tongcongthuc_08TH(arrMact, subid, congthuc)
            {
                var tong = 0, giatri = 0, cong_dulieu = congthuc;
                var id = subid + '_';

                for (var j = 0; j < arrMact.length; j++)
                {
                    id = subid + '_' + arrMact[j];
                    giatri = getvalue(id);
                    tong += giatri;
                    var rep = new RegExp(arrMact[j], "g");
                    cong_dulieu = cong_dulieu.replace(rep, giatri);
                }
//                console.log('congthuc='+congthuc+' cong_dulieu='+cong_dulieu);
                var tong_congthuc = eval(cong_dulieu);
                return tong_congthuc;
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

            function OnchangeSelect(idselect, subid, ma)
            {
                try {
                    resetvalue(subid, ma);
//                    console.log('Bat dau OnchangeSelect');
                    var e = document.getElementById(idselect);
                    var value_id = e.options[e.selectedIndex].value;
                    var text = e.options[e.selectedIndex].text;
                    var id_input = subid + '_' + ma;
//                    console.log('id_input=' + id_input + ' text=' + text + ' value_id=' + value_id);
                    document.getElementById(id_input).value = text;
                    evaluateSum_col('CHAMDIEMTT_001', subid);
                    document.getElementById(subid + '_' + value_id).value = text; //thiết lập vào đúng vị trí 
                } catch (e) {
                    console.log('ERROR=' + e.toString() + " " + id_input);
                }
            }

            function nhapdiemtru(khoa_diemtru, macb) {
                try
                {
//        var pos_string = laypostreecheck(khoa_cdtt);
//        if (pos_string === '' || pos_string == null)
//        {
//            pos_string = '999999,';
//        }
                    //swal(pos_string);
                    var ht1 = screen.availHeight - 300;
                    var wt1 = 950;
                    var left1 = (screen.width / 2) - (wt1 / 2);
                    var top1 = 100;
                    var ngay_bc = $("#ngay_bc_DATE").val();
                    var khoa_cdtt = $("#khoa_cdtt").val();
                    var url = "Nhapdiemtru.action?khoa_cdtt=" + khoa_diemtru + "&ngay_bc=" + ngay_bc + "&macb=" + macb;

                    //$.post(url,param,function(data){});
                    popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                    window.refreshData = function () {
                        //alert('aaaa');
                        $("#loadDatatmp").trigger("click");
                    };
                } catch (e)
                {
                    swal('Lỗi', 'Lỗi: ' + e.toString(), 'error');
                }
            }
        </script>        
    </head>
    <body>
        <s:form id="id_sv_%{khoa_cdtt}" action="SAVE_%{khoa_cdtt}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />"  
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:iterator value="poscd" status="row">
                <s:hidden name="poscd[%{#row.index}]" />
            </s:iterator>      
            <div id="divTitle">
                PL08/ĐGXL - PHÊ DUYỆT TIÊU CHÍ ĐÁNH GIÁ MỨC ĐỘ HOÀN THÀNH NHIỆM VỤ ĐỐI VỚI CÁ NHÂN
            </div>                        
            <s:hidden name="khoa_cdtt"/>
            <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">                          
                <tr height="23">
                    <th rowspan="2" class="TD_THOIGIAN">Cán bộ</th>
                    <th rowspan="2" class="TD_THUTU">TT</th>
                    <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>  
                    <th rowspan="2"  class="TD_SOLUONG">Điểm tối đa</th>  
                    <th rowspan="2"  class="TD_SOLUONG">% điểm</th> 
                    <th rowspan="2"  class="TD_SOLUONG">Đơn vị tính</th> 
                    <!--<th rowspan="2" class="TD_SOLUONG">Mã</th>--> 
                    <th colspan="2" class="TD_SOLUONG">Cá nhân tự đánh giá</th> 
                    <th colspan="3" >Lãnh đạo phụ trách đánh giá</th>                     
                </tr>  
                <tr height="22">                        
                    <th class="TD_SOLUONG">Kết quả thực hiện nhiệm vụ trong tháng đạt: Tỷ lệ; mức độ hoàn thành công việc; các lỗi sai sót tồn tại</th>
                    <th class="TD_SOLUONG">Số điểm đạt (+); Số điểm phải trừ (-)</th>
                    <!--<th class="TD_GHICHU">Ghi chú</th>-->
                    <th class="TD_SOLUONG">Kết quả thực hiện nhiệm vụ trong tháng đạt: Tỷ lệ; mức độ hoàn thành công việc; các lỗi sai sót tồn tại</th>
                    <th class="TD_SOLUONG">Số điểm đạt (+); Số điểm phải trừ (-)</th>
                    <th class="TD_GHICHU">Ghi chú</th>                                                 
                </tr> 
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                    <tr height="16" class="<s:property  value="D30" />"> 
                        <td  align = "left" class="TD_THOIGIAN">
                            <input style="color: red; font-weight: bold;" type="text"  value="<s:property  value="D7" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH"
                                   readonly="true"/>
                        </td>
                        <td align = "center" class="TD_THUTU">
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                            <input type="hidden" value="<s:property  value="D14" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" id="D14_<s:property  value="MA" />"/>
                            <input type="hidden" value="<s:property  value="KHOA" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KHOA"/>

                            <input type="hidden" value="<s:property  value="THUTU" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/> 
                            <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/>
                            <s:if test="D28.startsWith('DT_')">
                                <a href="javascript:nhapdiemtru('<s:property value="D28"/>','<s:property value='D14'/>')" class="SOKU linkKh">
                                    <s:property value='TT_HIENTHI'/>
                                </a>
                            </s:if>  
                            <s:else>
                                <a href="javascript:hienthichitiet('<s:property value="MA"/>',2,'<s:property value='khoa_cdtt'/>')" class="SOKU linkKh">
                                    <s:property value='TT_HIENTHI'/>
                                </a>                               
                            </s:else>  
                        </td>
                        <!--style="color: #FF7E00; font-weight: bold;"-->
                        <td align = "left" class="TD_CHITIEU">
                            <input  
                                type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
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
                            <input <s:if test="MA.equalsIgnoreCase('CDTT99')"> style="color: #FF7E00; font-weight: bold;" </s:if> 
                                                                               type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH" readonly="true"/>
                        </td>
                        <s:if test="NHAPTAY.equalsIgnoreCase('N')">                          
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="MA" />_<s:property  value="D14" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH number" 
                                       readonly="true"/>
                            </td>  

                            <td align = "right" class="TD_SOLUONG">
                                <input <s:if test="MA.equalsIgnoreCase('CDTT99')"> style="color: #FF7E00; font-weight: bold;" </s:if>  
                                                                                   type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="MA" />"
                                                                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number2 TEN_KH"
                                                                                   readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D13" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH D0" 
                                       readonly="true"/>
                            </td>                                                                                                                                   
                        </s:if>

                        <s:if test="NHAPTAY.equalsIgnoreCase('Y')">                        

                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="MA"/>"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="number TEN_KH CONGCAP_D11"
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               evaluateSum_row('<s:property value='MA'/>');
                                               evaluateSum_col('CHAMDIEMTT_001', 'D12');
                                       " 
                                       <s:if test="D29.equalsIgnoreCase('N')"> readonly="readonly" </s:if>         
                                           />
                                </td>  
                                                      
                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D12" />" id="D12_<s:property value='MA'/>" value="<s:property value='D12'/>" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number2 TEN_KH CONGCAP_D12" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               isInputMark('D1_<s:property value='MA'/>', 'D12_<s:property value='MA'/>');
                                               evaluateSum_col('CHAMDIEMTT_001', 'D12');"
                                       <s:if test="D29.equalsIgnoreCase('Y')"> readonly="readonly" </s:if>  />
                                </td>                            
                                <td align = "right" class="TD_GHICHU">
                                    <input type="text" value="<s:property  value="D13" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH"/>
                            </td>                                                                                                                                                                    

                        </s:if>                   
                        <td class="hideColumn"><input type="text" value="<s:property value='D3'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                        <td class="hideColumn"><input type="text" value="<s:property value='D15'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                        <td class="hideColumn"><input type="text" value="<s:property value='MA'/>" name="MA_CT" class="MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="hideColumn"><input type="hidden" id="D28_<s:property  value="MA" />" value="<s:property  value="D28" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" class="D28_DIEM"/></td>
                    </tr>        


                </s:iterator>
            </table>                                                                    

            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
