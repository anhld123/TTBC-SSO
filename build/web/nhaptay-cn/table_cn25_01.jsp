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
            <style>
        th{
            background-color: #DCDCDC;
            border-color: #999;
            height: 20px;
        }
        td{
            border-color: #999;
            height: 22px;
        }
        table.editDelete{
            border-collapse: collapse;
            width: 100%;
            border-color: #999;
        }
        table.editDelete tr:focus{
            background-color:#FFE47A;
        }
    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <!--<script src="nhaptay-cn/js/chamdiem_canhan.js"></script>-->  
        <style>
            .BOLD {
                font-weight:bold;
            }
        </style>
        <script>

            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
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
                $(".TEN_KH").css({"height": "100%"});
//                evaluateSum_col('CHAMDIEMTT_001', 'D10');
//                catcongthuc("CN010101+CN010102+CN0102+CN02+CN03");
//                evaluateSum_col('CHAMDIEMTT_001', 'D10');

            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
           
            function evaluateSum_col(table_id, subid) {

                try {                                
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
                            //nếu cấp báo cáo bằng với danh sách mảng của câp báo cáo ở trên và công thức khác null hoặc rỗng
                            if ($(kh_capht).eq(i).val() == arrCapht[k] && $(kh_congthuc).eq(i).val().length > 0)
                            {
                                var ma_ct = $(kh_ma_ct).eq(i).val();
                                var congthuc = $(kh_congthuc).eq(i).val();
//                                console.log('congthuc=' + congthuc);
                                //Lấy ra khóa của báo cáo
//                                var khoa = document.getElementById('khoa').value;

//                                var Grade = document.getElementById('Grade').value;
                                
                                //cắt công thức đưa về mảng
                                //var valNew = congthuc.replace(/-/g, '+').split('+');
                                
                                var valNew = catcongthuc(congthuc);
//                                console.log('valNew=' + valNew);
                                var tongcong = tongcongthuc_08TH(valNew, subid, congthuc);
                                
//                                console.log('valNew=' + valNew + ' ma_ct=' + ma_ct + ' tongcong=' + tongcong + ' subid=' + subid);                                
//                                console.log(subid + '_' + ma_ct);                                
                                document.getElementById(subid + '_' + ma_ct).value = tongcong;                                
                                // document.getElementById(subid + '_' + ma_ct).value = tongcong;
                            } else
                            {
                            }
                        }
                    }                                        
//                    $('.number2').number(true, 2);
                } catch (e) {
//                    swal('Lỗi', 'ERROR evaluateSum ' + e.toString());
                    console.log('ERROR evaluateSum ' + e.toString());
                }

            } 
            
            function catcongthuc(congthuc)
            {
                //var s = '(CN01020101+CN01020102-CN01020103+CN01020104)*20/30';
                var arrMact = congthuc.split(/[\/\*\-\+\()]/g);
            //    console.log(arrMact);

                /* Remove unwanted "" */
                for (var i = 0; i < arrMact.length; i++) {
                    if (arrMact[i].trim().length == 0) {
                        arrMact.splice(i, 1);
                        i--;
                    } else if (arrMact[i].indexOf("CDTT") == -1)
                    {
                        arrMact.splice(i, 1);
                        i--;
                    }
                }
//                console.log(arrMact);
                return arrMact;
            //    console.log(arr);
            //
            //    var delimiter = s;
            //    var separators = [' ', '\\\+', '-', '\\\(', '\\\)', '\\*', '/', ':', '\\\?'];
            //    console.log(separators.join('|'));
            //    var result =
            //            delimiter.split(new RegExp(separators.join('|'), 'g'));
            //    console.log(result);
            }
            
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
//        var pos_string = laypostreecheck(khoa_nhaptaycn);
//        if (pos_string === '' || pos_string == null)
//        {
//            pos_string = '999999,';
//        }
                    //swal(pos_string);
                    var pheduyet = 'N';
                    var ht1 = screen.availHeight - 300;
                    var wt1 = 950;
                    var left1 = (screen.width / 2) - (wt1 / 2);
                    var top1 = 100;
                    var ngay_bc = $("#ngay_bc_DATE").val();
                    var khoa_nhaptaycn = $("#khoa_nhaptaycn").val();
                    var url = "Nhapdiemtru.action?khoa_nhaptaycn=" + khoa_diemtru + "&ngay_bc=" + ngay_bc + "&macb=" + macb + "&pheduyet=" + pheduyet;

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
            function laypostreecheck(khoa_nhaptaycn)
            {

                var pos_cd = '';
                var idform = 'id_' + khoa_nhaptaycn;//'<s:property value="khoa_nhaptaycn"/>';
                var element = document.forms[idform].elements;
//                 alert('bat dau goi submit idform='+element.length);
                var i = element.length;
                for (var k = 0; k < i; k++)
                {
//                    console.log(element[k].name );
                    if (element[k].name == 'poscd')
                    {
                        if (element[k].checked == true)
                        {
                            if (element[k].value != '999999')
                            {
//                                alert(element[k].value);
                                pos_cd = pos_cd + element[k].value + ',';
                            }
                        }
                    }
                }
//                alert('bat dau goi submit pos_cd='+pos_cd);
                return pos_cd;
            }
            function hienthichitiet(ma, stt, khoa_nhaptaycn) {
                try
                {
                    var khoa_nhaptaycn_action = $("#khoa_nhaptaycn").val();
                    var pos_string = laypostreecheck(khoa_nhaptaycn_action);
                    if (pos_string === '' || pos_string == null)
                    {
                        pos_string = '999999,';
                    }
                    //swal(pos_string);
                    var ht1 = screen.availHeight - 300;
                    var wt1 = 950;
                    var left1 = (screen.width / 2) - (wt1 / 2);
                    var top1 = 100;
                    var ngay_bc = $("#ngay_bc_DATE").val();
                    var macb = $("#tt_cdtt").val();
                    var url = "ChitietChamdiem_CN.action?MACT=" + ma + "&ngay_bc=" + ngay_bc + "&pos_string=" + pos_string + "&khoa_nhaptaycn=" + khoa_nhaptaycn +"&macb="+macb+ "&addedit=" + stt;

                    //$.post(url,param,function(data){});
                    popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                } catch (e)
                {
                    swal('Lỗi', 'Lỗi: ' + e.toString(), 'error');
                }
            }

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
            }
        </script>       
    </head>
    <body>
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />"  
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>

            <div id="divTitle">
                HÀ GIANG - BÁO CÁO THỐNG KÊ SỐ LƯỢNG KIỂM TRA GIÁM SÁT
            </div>                        
            <s:hidden name="khoa_nhaptaycn"/>            
            
            

            <s:iterator value="poscd" status="row">
                <s:hidden name="poscd[%{#row.index}]" />
            </s:iterator>       
            <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">                          
                <tr height="40">
                    <th rowspan="1" class="TD_THUTU">TT</th>
                    <th rowspan="1" class="TD_CHITIEU">Chỉ tiêu</th>  
                    <!--<th rowspan="1"  class="TD_SOLUONG">Cột 1</th>-->  
                    <!--<th rowspan="1"  class="TD_SOLUONG">Cột 1</th>--> 
                    <!--<th rowspan="1"  class="TD_SOLUONG">Cột 1</th>-->   
                    <th rowspan="1"  class="TD_SOLUONG">Số lượt tỉnh</th>      
                    <th rowspan="1"  class="TD_SOLUONG">Số lượt huyện</th>      
                    <th rowspan="1"  class="TD_SOLUONG">Số lượt điểm giao dịch</th>      
                    <th rowspan="1"  class="TD_SOLUONG">Số lượt tổ TK&VV</th>      
                </tr>                  
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                    <!--D30: dòng ẩn hoặc hiện-->
                    <tr height="25" > 
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
                            <input type="hidden" value="<s:property  value="D15" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/>                             

                            <input type="text"  value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH <s:property value='D19'/> D0"
                                       readonly="true"/>                                                       
                        </td>
                        <s:if test="NHAPTAY.equalsIgnoreCase('N')">                        

                            <td align = "left" class="TD_CHITIEU">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH <s:property value='D19'/>"
                                       readonly="true"/>
                            </td>
<!--                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0 <s:property value='D19'/>"
                                       readonly="true"/>-->
<!--                            </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0 <s:property value='D19'/>" readonly="true"/>
                            </td>  -->
<!--                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0 <s:property value='D19'/>" readonly="true"/>
                            </td>-->
                            <td align = "left" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number <s:property value='D19'/>" readonly="true"/>
                            </td>
                            <td align = "left" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH number <s:property value='D19'/>" readonly="true"/>
                            </td>   
                            <td align = "left" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH number <s:property value='D19'/>" readonly="true"/>
                            </td> 
                            <td align = "left" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH number <s:property value='D19'/>" readonly="true"/>
                            </td>  
                        </s:if>

                        <s:if test="NHAPTAY.equalsIgnoreCase('Y')">                         
                            <td align = "left" class="TD_CHITIEU">
                                <input type="text" id="TEN_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH <s:property value='D19'/>"
                                       readonly="readonly" />
                            </td>
<!--                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />" id="D1_<s:property value='MA'/>"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0 <s:property value='D19'/>"
                                       readonly="readonly"
                            </td>

                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0 <s:property value='D19'/>" readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH <s:property value='D19'/> D0" readonly="true"/>
                            </td> -->

                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="number TEN_KH CONGCAP_D5 <s:property value='D19'/>"
                                       onblur="if (this.value == '') {
                                                   this.value = 0};                                               
                                               evaluateSum_col('CHAMDIEMTT_001', 'D7')"/>
                            </td>
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="number TEN_KH CONGCAP_D5 <s:property value='D19'/>"
                                       onblur="if (this.value == '') {
                                                   this.value = 0};                                               
                                               evaluateSum_col('CHAMDIEMTT_001', 'D8')"/>
                            </td>
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="number TEN_KH CONGCAP_D5 <s:property value='D19'/>"
                                       onblur="if (this.value == '') {
                                                   this.value = 0};                                               
                                               evaluateSum_col('CHAMDIEMTT_001', 'D9')"/>
                            </td>
                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number TEN_KH CONGCAP_D5 <s:property value='D19'/>"
                                       onblur="if (this.value == '') {
                                                   this.value = 0};                                               
                                               evaluateSum_col('CHAMDIEMTT_001', 'D10')"/>
                            </td>
                            
                                                                                                                                                                                                                      
                        </s:if>                   
                        <td class="hideColumn"><input type="text" value="<s:property value='D3'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                        <td class="hideColumn"><input type="text" value="<s:property value='D15'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                        <td class="hideColumn"><input type="text" value="<s:property value='MA'/>" name="MA_CT" class="MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                        <td class="hideColumn"><input type="hidden" id="D28_<s:property  value="MA" />" value="<s:property  value="D28" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" class="D28_DIEM"/></td>

                    </tr>        
                </s:iterator>
            </table>                                                                    

            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>                    
        <div id="luu_thanhcong"></div>
    </body>
</html>
