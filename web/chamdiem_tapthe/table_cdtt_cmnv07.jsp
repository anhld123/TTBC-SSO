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
        <script>
            function sumColumn(Class_name, cur)
            {
                try
                {
                    var tong_I = 0, tong_B = 0;
                    var id_d10 = '';
                    $(Class_name).each(function () {
                        id_d10 = $(this).attr("id"); //this.id
                        
//                        $(this).css({'background-color': '#FFFFFF'});
                        if (id_d10.indexOf('D10_20') >= 0 || id_d10.indexOf('D12_20') >= 0 || id_d10.indexOf('D1_20') >= 0)
                        {
                            tong_I += parseFloat($(this).val());  
                            
                                console.log("Vao day");
                                var id_d1 = Class_name === '.CONGCAP_D10' ? id_d10.replace("D10_", "D1_") : id_d10.replace("D12_", "D1_");
    //                            console.log(id_d1);
                                var giatri = parseFloat($(this).val());
                                var giatri_D1 = parseFloat(document.getElementById(id_d1).value);

                                if (giatri > giatri_D1 || giatri < 0)
                                {
                                    swal('Lỗi', 'Bạn không được nhập điểm lớn hơn ' + giatri_D1 + ' hoặc nhỏ hơn 0', 'warning');
                                    cur.style.background = '#ff0000';
                                    cur.value = 0;
                                    return;
                                }
//                                tong_B += parseFloat($(this).val());                            
                        }                              
                        else
                        {
                            var giatri_D10_700007 = parseFloat(document.getElementById("D10_700007").value);
                            console.log(giatri_D10_700007);
                            if (giatri_D10_700007 !=30 && giatri_D10_700007 != 25 && giatri_D10_700007 != 15 && giatri_D10_700007 != 0)
                            {
                                swal('Lỗi', 'Bạn chỉ được phép nhập các giá trị 0,15,25,30 cho chỉ tiêu này', 'warning');
                                cur.style.background = '#ff0000';
                                cur.value = 0;
                                return;
                            }
                            
                            var giatri_D10_800008 = parseFloat(document.getElementById("D10_800008").value);
                            console.log(giatri_D10_700007);
                            if (giatri_D10_800008 !=30 && giatri_D10_800008 != 20 && giatri_D10_800008 != 10 && giatri_D10_800008 != 0)
                            {
                                swal('Lỗi', 'Bạn chỉ được phép nhập các giá trị 0,10,20,30 cho chỉ tiêu này', 'warning');
                                cur.style.background = '#ff0000';
                                cur.value = 0;
                                return;
                            }
                            
                            							var giatri_D12_700007 = parseFloat(document.getElementById("D12_700007").value);
                            console.log(giatri_D12_700007);
                            if (giatri_D12_700007 !=30 && giatri_D12_700007 != 25 && giatri_D12_700007 != 15 && giatri_D12_700007 != 0)
                            {
                                swal('Lỗi', 'Bạn chỉ được phép nhập các giá trị 0,15,25,30 cho chỉ tiêu này', 'warning');
                                cur.style.background = '#ff0000';
                                cur.value = 0;
                                return;
                            }
                            
                            var giatri_D12_800008 = parseFloat(document.getElementById("D12_800008").value);
                            console.log(giatri_D12_700007);
                            if (giatri_D12_800008 !=30 && giatri_D12_800008 != 20 && giatri_D12_800008 != 10 && giatri_D12_800008 != 0)
                            {
                                swal('Lỗi', 'Bạn chỉ được phép nhập các giá trị 0,10,20,30 cho chỉ tiêu này', 'warning');
                                cur.style.background = '#ff0000';
                                cur.value = 0;
                                return;
                            }
            
                            var id_d1 = Class_name === '.CONGCAP_D10' ? id_d10.replace("D10_", "D1_") : id_d10.replace("D12_", "D1_");
//                            console.log(id_d1);
                            var giatri = parseFloat($(this).val());
                            var giatri_D1 = parseFloat(document.getElementById(id_d1).value);
                            
                            if (giatri > giatri_D1 || giatri < 0)
                            {
                                swal('Lỗi', 'Bạn không được nhập điểm lớn hơn ' + giatri_D1 + ' hoặc nhỏ hơn 0', 'warning');
                                cur.style.background = '#ff0000';
                                cur.value = 0;
                                return;
                            }
                            tong_B += parseFloat($(this).val());
                        }

                        if (tong_I > 400)
                        {
                            swal('Lỗi', 'Tổng số điểm bạn nhập không được lớn hơn 400 điểm', 'warning');
//                            $(this).val() = 0;  style="background: #FFE6B0" style="background: #ff0000" style="background: #FFFFFF"
                            cur.style.background = '#ff0000';
                            cur.value = 0;
                            return;
                        }
                    });
                    
                    
                    
                    
                    if (Class_name === '.CONGCAP_D10')
                    {
                        var giatri_D1_tong = parseFloat(document.getElementById("D1_200001").value);
                        
                        
                        document.getElementById("D10_200001").value = tong_I;
                        document.getElementById("D10_600006").value = tong_B;
                        var D19_GIATRI_TUCHAM = parseFloat(document.getElementById('D19_GIATRI_TUCHAM').value);
                        D19_GIATRI_TUCHAM = isNaN(D19_GIATRI_TUCHAM) ? 0 : D19_GIATRI_TUCHAM;
                        var D20_GIATRI_TUCHAM = parseFloat(document.getElementById('D20_GIATRI_TUCHAM').value);
//                        console.log('D20_GIATRI_TUCHAM1='+D20_GIATRI_TUCHAM);
                        D20_GIATRI_TUCHAM = isNaN(D20_GIATRI_TUCHAM) ? 0 : D20_GIATRI_TUCHAM;
                      
                        var giatri_diemX = ((400 - giatri_D1_tong) * 0.4 * D19_GIATRI_TUCHAM) / 100;
                        document.getElementById("D10_400004").value = giatri_diemX;
                        var giatri_diemY = ((400 - giatri_D1_tong) * 0.6 * D20_GIATRI_TUCHAM) / 100;
                        document.getElementById("D10_500005").value = giatri_diemY;
//                          console.log('D20_GIATRI_TUCHAM=' + D20_GIATRI_TUCHAM + ' giatri_diemX=' + giatri_diemX);
//                        console.log('D19_GIATRI_TUCHAM=' + D19_GIATRI_TUCHAM + ' giatri_diemY=' + giatri_diemY);
                        var tong_II = giatri_diemX + giatri_diemY;
                         document.getElementById("D10_300003").value = tong_II;
                        document.getElementById("D10_100002").value = tong_II + tong_I;
                        
                        var tong_A = parseFloat(document.getElementById("D10_100002").value);
                        
                        document.getElementById("D10_600006").value = parseFloat(document.getElementById("D10_700007").value) +
                                parseFloat(document.getElementById("D10_800008").value) +
                                parseFloat(document.getElementById("D10_900009").value);
                        
                        tong_B = parseFloat(document.getElementById("D10_600006").value);
                        
                        document.getElementById("D10_990010").value = tong_A + tong_B;
                    } else if (Class_name === '.CONGCAP_D12')
                    {
                        var giatri_D1_tong = parseFloat(document.getElementById("D1_200001").value);
                        document.getElementById("D12_200001").value = tong_I;
                        document.getElementById("D12_600006").value = tong_B;

                        var D21_LANHDAO_CHAM = parseFloat(document.getElementById('D21_LANHDAO_CHAM').value);
                        var D22_LANHDAO_CHAM = parseFloat(document.getElementById('D22_LANHDAO_CHAM').value);
                        D21_LANHDAO_CHAM = isNaN(D21_LANHDAO_CHAM) ? 0 : D21_LANHDAO_CHAM;
                        D22_LANHDAO_CHAM = isNaN(D22_LANHDAO_CHAM) ? 0 : D22_LANHDAO_CHAM;
                        var giatri_diemX = ((400 - giatri_D1_tong) * 0.4 * D21_LANHDAO_CHAM) / 100;
                        document.getElementById("D12_400004").value = giatri_diemX;
                        var giatri_diemY = ((400 - giatri_D1_tong) * 0.6 * D22_LANHDAO_CHAM) / 100;
                        document.getElementById("D12_500005").value = giatri_diemY;
                        var tong_II = giatri_diemX + giatri_diemY;
                        document.getElementById("D12_300003").value = tong_II;
                        document.getElementById("D12_100002").value = tong_II + tong_I;

                        var tong_A = parseFloat(document.getElementById("D12_100002").value);
                        
                        document.getElementById("D12_600006").value = parseFloat(document.getElementById("D12_700007").value) +
                                parseFloat(document.getElementById("D12_800008").value) +
                                parseFloat(document.getElementById("D12_900009").value);
                        
                        tong_B = parseFloat(document.getElementById("D12_600006").value);
                        document.getElementById("D12_990010").value = tong_A + tong_B;
//                        console.log('tong_A=' + tong_A);
                    }
                    else if (Class_name === '.CONGCAP_D1')
                    {
                        document.getElementById("D1_200001").value = tong_I;                        
                    }
                } catch (e)
                {
                    swal('Lỗi', 'ERROR sumColumn ' + e.toString(), 'error');
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
//                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
//                $('.number2').number(true, 2);
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
                if ('<s:property value="RULEUSER"/>' != '9')
                {
                    sumColumn('.CONGCAP_D10', document.getElementById("D10_200001"));
                    sumColumn('.CONGCAP_D12', document.getElementById("D12_200001"));
                    
//                    sumColumn('.CONGCAP_D10', document.getElementById("D10_600006"));
//                    sumColumn('.CONGCAP_D12', document.getElementById("D12_600006"));
                }
                
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
                                <td align = "right" class="TD_THUTU"><input type="text" value="" id="TT_HIENTHI' + rowCount + '" name="lstDulieuNt[' + rowCount + '].TT_HIENTHI" class="TEN_KH " readonly="readonly" /><input type="hidden" id="id_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].MA" value="' + code + '"/></td>\n\
                                <td align = "right" class="TD_CHITIEU"><input type="text" value="" id="TEN_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].TEN" class="TEN_KH " /></td>                                \n\
                                <td align = "right" class="TD_NGUYENGIA"><input type="text" value="0" id="D1_' + code + '" name="lstDulieuNt[' + rowCount + '].D1" class="number TEN_KH CONGCAP_D1"  onblur="if (this.value == \'\') {this.value = 0} ;sumColumn(\'.CONGCAP_D1\',this);"/></td>\n\    n\
\n\                             <td align = "right" class="TD_NGUYENGIA"><input type="text" value="" id="D2_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D2" class="TEN_KH D0"  onblur="if (this.value == \'\') {this.value = 0} ;" readonly="readonly"/></td>\n\    n\
                                <td align = "right" class="TD_SOLUONG"><input type="text" value="0" id="D10_' + code + '" name="lstDulieuNt[' + rowCount + '].D10" class="number TEN_KH CONGCAP_D10"  onblur="if (this.value == \'\') {this.value = 0} ;sumColumn(\'.CONGCAP_D10\',this);"/></td>\n\    n\
\n\                             <td align = "right" class="TD_GHICHU"><input type="text" value="" id="D11_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D11" class="TEN_KH D0"/></td>\n\    n\
                                <td align = "right" class="TD_SOLUONG"><input type="text" value="0" id="D12_' + code + '" name="lstDulieuNt[' + rowCount + '].D12" class="number TEN_KH CONGCAP_D12"  onblur="if (this.value == \'\') {this.value = 0} ;sumColumn(\'.CONGCAP_D12\',this);"/></td>\n\    n\\    n\
                                <td align = "right" class="TD_GHICHU"><input type="text" value="" id="D13_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D13" class="TEN_KH  D0"/></td>\n\
                                <td class="TD_TEN_KH"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                $($('table#tablecdtt07 tr')[index]).after(newTr);
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
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.TEN_KH').focus(function () {
                    $(this).closest('tr').addClass('highlight_row');
                });
                $('.TEN_KH').blur(function () {
                    $(this).closest('tr').removeClass('highlight_row');
                });
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
                PL07/ĐGXL - CÔNG VIỆC ĐỊNH TÍNH ĐỐI VỚI TẬP THỂ PHÒNG/BAN <font color="red">(Trạng thái: <s:property  value="TT_DUYET"/>)</font> 
            </div>                        
            <s:hidden name="khoa_cdtt"/>
            <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">    
                <s:if test="%{#rowstatus.first == true}">
                    <input type="hidden" value="<s:property  value="D19" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" id="D19_GIATRI_TUCHAM"/>

                    <input type="hidden" value="<s:property  value="D20" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" id="D20_GIATRI_TUCHAM"/> 

                    <input type="hidden" value="<s:property  value="D21" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" id="D21_LANHDAO_CHAM"/> 
                    <input type="hidden" value="<s:property  value="D22" />" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" id="D22_LANHDAO_CHAM"/> 
                </s:if>
            </s:iterator>
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
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" id="D19_<s:property  value="MA" />"/>

                                    <input type="hidden" value="<s:property  value="D20" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" id="D20_<s:property  value="MA" />"/> 

                                    <input type="hidden" value="<s:property  value="D21" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" id="D21_<s:property  value="MA" />"/> 
                                    <input type="hidden" value="<s:property  value="D22" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" id="D22_<s:property  value="MA" />"/> 
                                    <input type="text"  value="<s:property  value="TT_HIENTHI" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0"
                                           readonly="true"/>
                                </td>
                                <td align = "left" class="TD_CHITIEU">
                                    <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" readonly="true"/>
                                </td>                            

                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number TEN_KH" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_GHICHU">
                                    <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" 
                                           readonly="true"/>
                                </td>  

                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number TEN_KH"
                                           readonly="true"/>
                                </td>                            
                                <td align = "right" class="TD_GHICHU">
                                    <input type="text" value="<s:property  value="D13" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH D0" 
                                           readonly="true"/>
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
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" id="D19_<s:property  value="MA" />"/>
                                    <input type="hidden" value="<s:property  value="D20" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" id="D20_<s:property  value="MA" />"/> 
                                    <input type="hidden" value="<s:property  value="D21" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" id="D21_<s:property  value="MA" />"/> 
                                    <input type="hidden" value="<s:property  value="D22" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" id="D22_<s:property  value="MA" />"/> 
                                    <input type="text"  value="<s:property  value="TT_HIENTHI" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TEN_KH "
                                           readonly="true"/>
                                </td>
                                <td align = "left" class="TD_CHITIEU">
                                    <input type="text" id="TEN_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH"
                                           <s:if test="!FONTFORMAT.equalsIgnoreCase('2')">readonly="readonly"</s:if> />
                                    </td>
                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D1" />" id="D1_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="number TEN_KH CONGCAP_D1"
                                           <s:if test="FONTFORMAT.equalsIgnoreCase('2')">onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   sumColumn('.CONGCAP_D1', this);"</s:if>                                            
                                            <s:if test="!FONTFORMAT.equalsIgnoreCase('2')">readonly="readonly"</s:if>
                                                   />
                                </td>
                                
<!--                                <td align = "right" class="TD_THOIGIAN">
                                        <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0"
                                           <s:if test="!FONTFORMAT.equalsIgnoreCase('2')">readonly="readonly"</s:if>/>
                                </td>-->
                                <td align = "right" class="TD_THOIGIAN">
                                    <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" readonly="true"/>
                                </td>                            

                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number TEN_KH CONGCAP_D10"
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   sumColumn('.CONGCAP_D10', this);" />
                                </td>
                                <td align = "right" class="TD_GHICHU">
                                    <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH" />
                                </td>  

                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number TEN_KH CONGCAP_D12" 
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   sumColumn('.CONGCAP_D12', this);"/>
                                </td>                            
                                <td align = "right" class="TD_GHICHU">
                                    <input type="text" value="<s:property  value="D13" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH"/>
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
                <table border="1" class="editDelete" id="tablecdtt07" align="center">
                    <tr height="23">
                        <th rowspan="2" class="TD_THUTU">TT</th>
                        <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>  
                        <th rowspan="2"  class="TD_NGUYENGIA">Điểm tối đa</th>  
                        <th rowspan="2"  class="TD_SOLUONG">% điểm</th> 
                        <!--<th rowspan="2" class="TD_SOLUONG">Mã</th>--> 
                        <th colspan="2" class="TD_SOLUONG">Tập thể tự đánh giá</th> 
                        <th colspan="2" >Lãnh đạo phụ trách đánh giá</th> 
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
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" id="D19_<s:property  value="MA" />"/>

                                    <input type="hidden" value="<s:property  value="D20" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" id="D20_<s:property  value="MA" />"/> 

                                    <input type="hidden" value="<s:property  value="D21" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" id="D21_<s:property  value="MA" />"/> 
                                    <input type="hidden" value="<s:property  value="D22" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" id="D22_<s:property  value="MA" />"/> 
                                    <input type="text"  value="<s:property  value="TT_HIENTHI" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0"
                                           readonly="true"/>
                                </td>
                                <td align = "left" class="TD_CHITIEU">
                                    <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_NGUYENGIA">
                                    <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" readonly="true"/>
                                </td>                            

                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number TEN_KH"  readonly="true"/>
                                </td>
                                <td align = "right" class="TD_GHICHU">
                                    <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" 
                                           readonly="true"/>
                                </td>  

                                <td align = "right" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number TEN_KH"
                                           readonly="true"/>
                                </td>                            
                                <td align = "right" class="TD_GHICHU">
                                    <input type="text" value="<s:property  value="D13" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH D0" 
                                           readonly="true"/>
                                </td>                                                    

                            </tr>                        
                    </s:iterator>
                </table> 
            </s:else>
            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
