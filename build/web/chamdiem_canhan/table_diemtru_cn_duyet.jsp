<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<link rel="stylesheet" type="text/css"  href="chamdiem_canhan/css/cdtt.css" />
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->

<s:head/>
<sj:head/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%--<sj:head jqueryui="true" loadAtOnce="true" jquerytheme="south-street" />--%>
        <sj:head jqueryui="true" jquerytheme="smoothness"/> 
        <script src="js/JavaScriptUtil.js"></script>
        <script src="js/InputMask.js"></script>
        <script src="js/Parsers.js"></script>
        <script src="js/Checkdate.js"></script>
        <script src="js/sweetalert.min.js"></script>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="chamdiem_canhan/js/chamdiem_canhan.js"></script>  
        <style>
            .readonly {
                background: #FFFFC0;        
            }
            .pos_edit_form {
                padding:0px;
                width:30%;    
                background:#f9f9f9;
                border:1px solid #ccc;
                text-align:left;   
                font-family: Arial;
                font-size: 12pt;
            }    

            .metroButtonStyle {
                font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
                display: block;
                color: rgb(255, 255, 255);
                text-decoration: none;
                text-align: center;
                width: 90px;
                height: 20px;
                padding: 5px;
                margin: 5px 0px 0px 5px;
                font-size: 12px;
                background: none repeat scroll 0 0 #808080;
                color: #FFF;
                border: 0px none;
                border-radius: 1px 1px 1px 1px;
                outline: 0px none;
            }
            .metroButtonStyle:hover {
                background: #018c3b;
            }
            .metroButtonStyle:active {
                background: #DCDCDC;
            }
            .metroButtonStyle:disabled {
                background: #DCDCDC;
            }

            #divTitle{
            font: 14px Arial, Helvetica, sans-serif;
            font-weight: bold;
            color: #0077b3;
            text-align: center;
        }
        #idTitle{
                        font-family: Cambria,Verdana,Arial,Tahoma,Helvetica;
                        font-size: 11pt;
                        font-weight: bold;
                        color: blue;
                    }
                    
        .BOLD {
        font-weight:bold;
      }                
        </style>
        <script>

            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
//                $('.number').number(true, 0);
//                $('input.number').css({"text-align": "right"});
//                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
//                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
//                $('#ui-datepicker-div').css('clip', 'auto');
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

            function evaluateSum_row_diemtru(ma, ma_d29) {
                try {

                    var d12 = 0;
                    var d11 = document.getElementById('D11_' + ma).value;
                    d11 = d11.replace(',', '');
                    
                    if (parseFloat(d11) < 0)
                    {
                        swal('Lỗi', 'Bạn không được nhập điểm lớn hơn 100 hoặc nhỏ hơn 0', 'warning');
                        document.getElementById('D11_' + ma).style.background = '#ff0000';
                        document.getElementById('D11_' + ma).value = 0;
                        return;
                    }
                    console.log('vap=------' + ma_d29  )
                    if (ma_d29 === 'N')
                    {
                        var D28 = getValue('D28_' + ma);
                        if (D28 === 'LOI01')
                        {
                            d9 = document.getElementById('D11_' + ma).value;
                            document.getElementById('D12_' + ma).value = d11 < 0 ? 0 : d11;
                        } else
                        {
                        }
                        var D1_CN0102 = document.getElementById('D1_CN0102').value;
                        var D12_CN0102 = document.getElementById('D12_CN0102').value;
                        if (parseFloat(D12_CN0102) > parseFloat(D1_CN0102))
                        {
                            document.getElementById('D11_' + ma).value = 0;
                            document.getElementById('D11_' + ma).focus();
                            document.getElementById('D11_' + ma).style.backgroundColor = "#DE76DF";

                        }
                    }
                } catch (e) {
                    swal('Lỗi', 'ERROR evaluateSum_row ' + e.toString());
                }
            }

            function evaluateSum_col_tru(table_id, subid, ma) {
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
                            var khoa = document.getElementById('khoa_cdtt').value;

                            var Grade = 1;
//                                    = document.getElementById('Grade').value;

                            var RULEUSER = 0;
//                                    = document.getElementById('RULEUSER').value;
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
                            
                            if (tongcong > 30)
                            {
                                swal('Lỗi', 'Tổng số điểm bạn nhập không được lớn hơn 30 điểm', 'warning');
    //                            $(this).val() = 0;  style="background: #FFE6B0" style="background: #ff0000" style="background: #FFFFFF"
//                                ma.style.background = '#ff0000';
                                document.getElementById(ma).style.background = '#ff0000';
//                                ma.value = 0;
                                document.getElementById(ma).value = 0;
//                                alert(ma.substr(0,12));
                                document.getElementById(ma.substr(0,12)).value = 0;                                
                                return;
                            }
                            
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
                console.log('Lỗi', 'ERROR evaluateSum ' + e.toString());
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
        };
        
        function  isInputMark(id_input_mark_max, id_input) {
                try {
                    var max_mark = getvalue(id_input_mark_max);
                    var mark = getvalue(id_input);
                    if (mark < 0)
                    {
                        swal('Lỗi', 'Bạn không được nhập số điểm là ' + mark.toString() + ' số điểm nhỏ nhất là 0 !', 'error');
                        document.getElementById(id_input).value = 0;
                        document.getElementById(id_input).focus();
                        document.getElementById(id_input).style.backgroundColor = "#DE76DF";
                    }

                    if (mark > max_mark)
                    {
                        swal('Lỗi', 'Bạn không được nhập số điểm lớn hơn ' + max_mark.toString(), 'error');
                        document.getElementById(id_input).value = max_mark;
                        document.getElementById(id_input).focus();
                        document.getElementById(id_input).style.backgroundColor = "#DE76DF";
                    }
                    //else
                    //{
                    //document.getElementById(id_input).style.backgroundColor ="#FFFFFF";
                    //}
                } catch (e)
                {
                    swal('Lỗi', 'ERROR isInputMark ' + e.toString(), 'error');
                }
            };
            
            function hienthichitiet(ma, stt, khoa_cdtt) {
                try
                {
                    var ngay_bc = $("#ngay_bc").val();                                
                    
                    //swal(pos_string);
                    var ht1 = screen.availHeight - 300;
                    var wt1 = 950;
                    var left1 = (screen.width / 2) - (wt1 / 2);
                    var top1 = 100;
                    var ngay_bc = $("#ngay_bc").val();
//                    var khoa_cdtt = $("#khoa_cdtt").val();
                    var url = "ChitietChamdiem_CN.action?MACT=" + ma + "&ngay_bc=" + ngay_bc +  "&khoa_cdtt=" + khoa_cdtt + "&addedit=" + stt;

                    //$.post(url,param,function(data){});
                    popup = window.open(url,'_blank', "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                } catch (e)
                {
                    swal('Lỗi', 'Lỗi: ' + e.toString(), 'error');
                }
            }
        </script>  
        
        <script>
            function closeSelf() {
                window.close();
                return true;
            }
        </script>
        
        <script>
            window.onunload = function (e) {
                opener.refreshData();
            };
        </script>
    </head>
    <body>
        <s:form name="frmDiemTru" id="frmDiemTru"  theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />"  
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>

            <div id="divTitle">
                NHẬP ĐIỂM TRỪ  
            </div>                        
            <s:hidden name="khoa_cdtt"/>
            <s:hidden name="macb"/>
            <s:hidden name="ngay_bc"/>
            <s:hidden name="ngaybc"/>
            <s:hidden name="pheduyet"/>
                 &nbsp;
            <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">                          
                <tr height="14">
                    <th rowspan="2"  class="TD_THUTU">TT</th>
                    <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>                      
                    <th rowspan="2"  class="TD_SOLUONG">Điểm trừ tối đa</th> 
                    <th colspan="2"  class="TD_SOLUONG">Hệ thống chấm</th> 
                    <th colspan="2"  class="TD_SOLUONG">Cá nhân tự đánh giá</th>                                 
                    <th colspan="3"  class="TD_SOLUONG">LĐ duyệt</th>  
                </tr>    
                <tr height="16">
                    <th   class="TD_SOLUONG">Lỗi</th>  
                    <th   class="TD_SOLUONG">Điểm</th>  
                    <th   class="TD_SOLUONG">Lỗi</th>  
                    <th   class="TD_SOLUONG">Điểm</th>  
                    <!--<th   class="TD_GHICHU">Ghi chú</th>-->  
                    <th   class="TD_SOLUONG">Lỗi</th>  
                    <th   class="TD_SOLUONG">Điểm</th>  
                    <th   class="TD_GHICHU">Ghi chú</th> 
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
                                
                                <input type="hidden" value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0"
                                       readonly="true"/>                                  
                                <a href="javascript:hienthichitiet('<s:property value="MA"/>',2,'<s:property value='KHOA'/>')" class="SOKU linkKh">
                                    <s:property value='TT_HIENTHI'/>
                                </a>
                            </td>
                            <td align = "left" class="TD_CHITIEU">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH <s:property  value="D18" />"
                                       readonly="true"/>
                            </td>
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />" id="D1_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH number" readonly="true"/>
                            </td>
                            
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D5" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" readonly="true"/>
                            </td>
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D6" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" readonly="true"/>
                            </td>
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D9" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH number" readonly="true"/>
                            </td>

                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number TEN_KH" readonly="true"/>
                            </td>
                                 
<!--                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D11" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH " readonly="true"/>
                            </td>-->
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D11" />"  id="D11_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH number" readonly="true"/>
                            </td>

                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number TEN_KH" readonly="true"/>
                            </td>
                                 
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D13" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH " readonly="true"/>
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

                                <input type="hidden"  value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH "
                                       readonly="true"/>
                                <a href="javascript:hienthichitiet('<s:property value="MA"/>',2,'<s:property value='KHOA'/>')" class="SOKU linkKh">
                                    <s:property value='TT_HIENTHI'/>
                                </a>
  
                            </td>
                            <td align = "left" class="TD_CHITIEU">
                                <input type="text" id="TEN_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH <s:property  value="D18" />"
                                       readonly="readonly" />
                            </td>                            
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />" id="D1_<s:property value='MA'/>"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH number" readonly="true"/>
                            </td>
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D5" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" readonly="true"/>
                            </td>
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D6" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number" readonly="true"/>
                            </td>

                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D9" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH number" readonly="readonly"/>
                            </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D10" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH number" readonly="readonly"/>
                            </td>
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D11" />"  id="D11_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH number" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               evaluateSum_row_diemtru('<s:property value='MA'/>', '<s:property value='D29'/>');
                                               evaluateSum_col_tru('CHAMDIEMTT_001', 'D12', 'D12_<s:property  value="MA" />');" 
                                               <s:if test="D29.equalsIgnoreCase('Y')"> readonly="readonly" </s:if>   
                                       />
                            </td>
                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number TEN_KH CONGCAP_D10"
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               isInputMark('D1_<s:property value='MA'/>', 'D12_<s:property value='MA'/>');
                                               evaluateSum_col_tru('CHAMDIEMTT_001', 'D12','D12_<s:property  value="MA" />');" 
                                       <s:if test="D29.equalsIgnoreCase('N')"> readonly="readonly" </s:if>   />
                                </td>  
                              <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D13" />" 
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
                <table  align="center"> 
                    <tr align="center" height="3px">                                    
                    </tr>
                    <tr align="center">                                       

                        <td width="35%">
                            <div id="content_div"></div>
                        </td>   

                            <td  align="center">
                            <div id="button_div" <s:property value="disabled" /> >
                                <s:url id="edit_url" action="saveDiemTru" escapeAmp="false"
                                       var="update_url">
                                    <s:param name="proc">update</s:param>  
                                </s:url>                        
                                <sj:a id="update_button_id"  href="%{#update_url}" 
                                      targets="content_div"
                                      formIds="frmDiemTru"    
                                      onBeforeTopics="before-next"
                                      button="false"
                                      cssClass="metroButtonStyle"                                         
                                      >     
                                    Cập nhật
                                </sj:a>
                            </div>
                        </td>                                     


                    <td align="center">
                            <sj:submit id="idClose" cssClass="metroButtonStyle"  name="nameClose" value="Thoát" onclick="closeSelf()"
                                       cssStyle="height:31px;width:95px"></sj:submit>
                    </td>

                    <td width="35%"></td>                                    


                </tr>
                </table>             
        </s:form>                    
        <div id="luu_thanhcong"></div>
    </body>
</html>
