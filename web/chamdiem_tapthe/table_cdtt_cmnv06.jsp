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
                $(".TD_TEN_KH").css({"width": "50px"});
                $(".TD_TTHIENTHI").css({"width": "2%"});
                $(".TD_TENTS").css({"width": "120px"});
                $(".TD_MATS").css({"width": "85px"});
                $(".TD_THOIGIAN").css({"width": "6%"});
                $(".TD_TYLE").css({"width": "4%"});
                $(".TD_THEMXOA").css({"width": "5%"});
                $(".TD_CBTH").css({"width": "12%"});
                $(".TD_GHICHU").css({"width": "20%"});
                $(".TD_CHITIEU").css({"width": "30%"});
                $(".TD_NOIDUNG").css({"width": "12%"});
                $(".TD_NOIDUNG06A").css({"width": "35%"});
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
                var table = document.getElementById("tablecdtt06");
                var rowCount = table.rows.length - 2; //Dem so dong cua bang

                if (max_row < rowCount)
                    max_row = rowCount;

//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
                
            }

            function addRow(indx, ma) {
                try
                {
    //                sleep(1000);                
                    var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                    var table = document.getElementById("tablecdtt06");
                    var rowCount = table.rows.length - 2; //Dem so dong cua bang
                    var ngay_bc = $("#ngay_bc_DATE").val();
                    var ngaybatdau = "01/"+ ngay_bc.substr(3,ngay_bc.length);
                    //console.log(ngaybatdau);

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
                                    \n\<td align = "right" class="TD_TTHIENTHI"><input type="text" value=""  name="lstDulieuNt[' + rowCount + '].TT_HIENTHI" class="TEN_KH" onfocus="this.select();"/></td>\n\
                                    <td align = "right" class="TD_NOIDUNG"><input type="text" value="" id="D2' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D2" class="TEN_KH " onfocus="this.select();sumColumn( ' + code + ');" /><input type="hidden" id="id_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].MA" value="' + code + '"/></td>\n\
\n\                                 <td align = "right" class="TD_TYLE"><input type="text" value="0" id="D16_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D16" class="D16 number"  onblur="if (this.value == \'\') {this.value = 0} ;sumColumn06(' + code + ');" onfocus="this.select();sumColumn06(' + code + ');"/></td>\n\    n\
                                    <td align = "right" class="TD_CBTH"><input type="text" value="" id="D3_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D3" class="TEN_KH " onfocus="this.select();sumColumn( ' + code + ');" /></td>\n\
                                    <td align = "right" class="TD_THOIGIAN"><select style="width:100%" name="lstDulieuNt[' + rowCount + '].D4" id="id_kieudulieu">\n\
                                        <option value="B">LĐ Ban</option>\n\
                                        <option value="P">Phòng CĐ</option>\n\\n\
                                        </select></td>\n\
                                    <td align = "right" class="TD_THOIGIAN"><input type="text"  value="'+ngaybatdau+'" id="D5_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D5" class="D0 datepicker" placeholder="dd/MM/yyyy" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                    <td align = "right" class="TD_THOIGIAN"><input type="text" value="'+ngay_bc+'" id="D6_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D6" class="D0 datepicker" placeholder="dd/MM/yyyy" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                    <td align = "right" class="TD_THOIGIAN">\n\
                                        <select style="width:100%" class="D7_NT'+ma+'" onchange="calculatorMark(\'.D7_NT'+ma+'\',\'D9_'+ma+'\');" name="lstDulieuNt[' + rowCount + '].D7" id="id_kieudulieu">\n\
                                        <option value="Y">Đạt tiến độ</option>\n\
                                        <option value="N">Chậm tiến độ</option>\n\\n\
                                        </select></td>\n\
    \n\                             <td align = "right" class="TD_TYLE"><input type="text" value="0" id="D19_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D19" class="D19 number2"  onblur="if (this.value == \'\') {this.value = 0} ;sumColumn06(' + code + ');" onfocus="this.select();sumColumn06(' + code + ');"/></td>\n\    n\
                                    <td align = "right" class="TD_THOIGIAN">\n\
                                        <select style="width:100%" class="D8_NT'+ma+'" onchange="calculatorMark(\'.D8_NT'+ma+'\',\'D10_'+ma+'\');" name="lstDulieuNt[' + rowCount + '].D8" id="id_kieudulieu">\n\
                                        <option value="Y">Đảm báo</option>\n\
                                        <option value="N">Không đảm bảo</option>\n\\n\
                                        </select></td>\n\
    \n\                             <td align = "right" class="TD_TYLE"><input type="text" value="0" id="D20_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D20" class="D20 number2"  onblur="if (this.value == \'\') {this.value = 0} ;sumColumn06(' + code + ');" onfocus="this.select();sumColumn06(' + code + ');"/></td>\n\    n\
                                    <td align = "right" class="TD_TYLE"><input type="text" value="0" id="D9_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D9" class="D9 number2"  onblur="if (this.value == \'\') {this.value = 0} ;sumColumn06(' + code + ');" onfocus="this.select();sumColumn06(' + code + ');"/></td>\n\    n\\n\
                                    <td align = "right" class="TD_TYLE"><input type="text" value="0" id="D10_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D10" class="D10 number2"  onblur="if (this.value == \'\') {this.value = 0} ;sumColumn06(' + code + ');" onfocus="this.select();sumColumn06(' + code + ');"/></td>\n\    n\\n\
                                    \n\
                                    <td align = "right" class="TD_NOIDUNG"><input type="text" value="" id="D11_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D11" class="TEN_KH" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                    <td class="TD_THEMXOA"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                    </tr>';
                    $($('table#tablecdtt06 tr')[index]).after(newTr);
                    $(".TD_TTHIENTHI").css({"width": "2%"});
                    $(".TD_TEN_KH").css({"width": "50px"});
                    $(".TD_TENTS").css({"width": "120px"});
                    $(".TD_MATS").css({"width": "85px"});
                    $(".TD_THOIGIAN").css({"width": "6%"});
                     $(".TD_TYLE").css({"width": "4%"});
                    $(".TD_GHICHU").css({"width": "20%"});
                    $(".TD_NOIDUNG").css({"width": "12%"});
                    $(".TD_CBTH").css({"width": "12%"});
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
                    $('.number').number(true, 0);
    //            //Cac truong bang so --> se co so truong = 0
                    $('.number2').number(true, 2);

                    calculatorMark('.D7_NT'+ma,'D9_'+ma);
                    calculatorMark('.D8_NT'+ma,'D10_'+ma);
                    } catch (e) {
                swal('Lỗi', 'ERROR evaluateSum ' + e.toString());
            }
            }
            function calculatorMark(class_ctl,id_update)
            {
//                tam bo de nhap tay
//                try
//                {
//                    var dem = $(class_ctl).length;
//                    var y = 0;
//                    for (var i = 0; i < dem; i++)
//                    {
//                        var giatri = $(class_ctl).eq(i).val();
//                         console.log('dem='+dem+' giatri='+giatri+' y='+y);
//                        if (giatri === 'Y')
//                            y++;
//                    }
//                    var diem = Math.round(100 * y / dem);
//                    document.getElementById(id_update).value = diem;
//                    //return diem;
//                    console.log(dem);
//                } catch (e)
//                {
//
//                }
            }
            function sumColumn(mainput_tmp)
            {
            }
            //                alert('sorown='+$('#tablepl01 tr').length+' socot='+$('#tablepl01 td').length);
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
//                    alert(value);
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
            
            function sumColumn06(mainput_tmp)
            {

                var mainput = $.trim(mainput_tmp.toString());
//                console.log(mainput)
                try {
//                    alert('\'' + mainput+ '\'');
                    var table = document.getElementById("tablecdtt06");
                    var rowcount = table.rows.length;
                    rowcount = rowcount > max_row ? rowcount : max_row;
                    var D19 = 0,D20 = 0, D16 = 0 , D9 = 0, D10 = 0;
                    var pos = -1;
//                    console.log('test_' +mainput);
                    for (var i = 0; i < rowcount; i++)
                    {
                        var matmp = getMabyNumber(i);
                        if (mainput.substr(0, 3) == matmp.substr(0, 3) && matmp.length == mainput.length)
                        {
//                            alert(getValue('D5_' + i));
//Lay gia tri cho cac truong tu D2->d6
                            //neu ky tu cuoi cung cua ma la '1' Vi du voi ma '100001,100030... thi se chi lam voi ma khac '1' o cuoi
                            if (matmp.substr(matmp.length - 2, matmp.length) != '01')
                            {
                                //lay ra gia tri cua truong D7,D8,D9
                                D16 = D16 + getValue('D16_' + i);
                                D9 = D9 + getValue('D9_' + i)*getValue('D16_' + i)/100;
                                D10 = D10 + getValue('D10_' + i)*getValue('D16_' + i)/100;
                                
                                D19 = D19 + getValue('D19_' + i)*getValue('D16_' + i)/100;
                                D20 = D20 + getValue('D20_' + i)*getValue('D16_' + i)/100;
                                
                                if(D16 > 100)
                                {
//                                    console.log('D19_' +matmp);
                                    swal('Lỗi', 'Tổng tỷ các công việc của bạn trong tháng không được lớn hơn > 100%', 'error');
                                    document.getElementById('D16_' + i).value = 0;                                   
                                    document.getElementById('D16_' + i).focus();
                                    document.getElementById('D16_' + i).style.backgroundColor = "#DE76DF";
                                     return;
                                }    
                                if(getValue('D19_' + i) > 100)
                                {
//                                    console.log('D19_' +matmp);
                                    swal('Lỗi', 'Tỷ lệ tiến độ/chất lượng công việc của bạn trong tháng không được lớn hơn > 100%', 'error');
                                    document.getElementById('D19_' + i).value = 0;                                   
                                    document.getElementById('D19_' + i).focus();
                                    document.getElementById('D19_' + i).style.backgroundColor = "#DE76DF";
                                     return;
                                }                                
                                if(getValue('D20_' + i) > 100)
                                {
//                                    console.log('D19_' +matmp);
                                    swal('Lỗi', 'Tỷ lệ tiến độ/chất lượng công việc của bạn trong tháng không được lớn hơn > 100%', 'error');
                                    document.getElementById('D20_' + i).value = 0;                                   
                                    document.getElementById('D20_' + i).focus();
                                    document.getElementById('D20_' + i).style.backgroundColor = "#DE76DF";
                                     return;
                                }
                                
                                if(getValue('D9_' + i) > 100)
                                {
//                                    console.log('D19_' +matmp);
                                    swal('Lỗi', 'Tỷ lệ tiến độ/chất lượng công việc của bạn trong tháng không được lớn hơn > 100%', 'error');
                                    document.getElementById('D9_' + i).value = 0;                                   
                                    document.getElementById('D9_' + i).focus();
                                    document.getElementById('D9_' + i).style.backgroundColor = "#DE76DF";
                                     return;
                                }                                
                                if(getValue('D10_' + i) > 100)
                                {
//                                    console.log('D19_' +matmp);
                                    swal('Lỗi', 'Tỷ lệ tiến độ/chất lượng công việc của bạn trong tháng không được lớn hơn > 100%', 'error');
                                    document.getElementById('D10_' + i).value = 0;                                   
                                    document.getElementById('D10_' + i).focus();
                                    document.getElementById('D10_' + i).style.backgroundColor = "#DE76DF";
                                     return;
                                }
                            } 
                            if (matmp.substr(matmp.length - 2, matmp.length) == '01')
                            {
//                                console.log(getValue('D2_' + i));
                                pos = i;
                            }
                        }
                        
                    }
                    //set gia tri
//                    alert('D8_' + pos)
                    setValue('D16_' + pos, D16);
                    
                    setValue('D19_' + pos, D19);
                    if (mainput_tmp.toString().substr(0,1) == '2')
                    {
                        console.log(mainput_tmp.toString().substr(0,1));
                        document.getElementById("D9_200001").value = D19;
                    }
                    else
                    {
                        document.getElementById("D9_300001").value = D19;
                    }
                    
                    setValue('D20_' + pos, D20);
                    if (mainput_tmp.toString().substr(0,1) == '2')
                    {
                        console.log(mainput_tmp.toString().substr(0,1));
                        document.getElementById("D10_200001").value = D20;
                    }
                    else
                    {
                        document.getElementById("D10_300001").value = D20;
                    }
                    
                    setValue('D9_' + pos, D9);
                    if (mainput_tmp.toString().substr(0,1) == '2')
                    {
                        console.log(mainput_tmp.toString().substr(0,1));
                        document.getElementById("D11_200001").value = D9;
                    }
                    else
                    {
                        document.getElementById("D11_300001").value = D9;
                    }
                    
                    setValue('D10_' + pos, D10);
                    if (mainput_tmp.toString().substr(0,1) == '2')
                    {
                        console.log(mainput_tmp.toString().substr(0,1));
                        document.getElementById("D12_200001").value = D10;
                    }
                    else
                    {
                        document.getElementById("D12_300001").value = D10;
                    }
                }
                catch (e)
                {
                    alert(e);
                }
                  $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
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
                06/ĐGXL - CÔNG VIỆC ĐỊNH TÍNH ĐỐI VỚI TẬP THỂ PHÒNG/BAN <font color="red">(Trạng thái: <s:property  value="TT_DUYET"/>)</font> 
            </div>
            <s:hidden name="khoa_cdtt"/>
            <!--            <div id="divDonvitinh">
                            Đơn vị tính: Triệu đồng
                        </div>-->
            <table border="1" class="editDelete" id="tablecdtt06" align="center">
            <s:if test="!RULEUSER.equalsIgnoreCase('9')">            
                <tr>
                    <th rowspan="2" class="TD_TTHIENTHI">TT</th>
                    <th rowspan="2" class="TD_NOIDUNG">Nội dung</th>
                    <th rowspan="2" class="TD_TYLE">Tỷ trọng công việc</th>
                    <th rowspan="2" class="TD_CBTH">Cán bộ thực hiện</th>
                    <th rowspan="2" style="width: 30px;" class="TD_THOIGIAN">LĐ Ban/ Phòng chỉ đạo(Giao)</th>
                    <th colspan="4" style="width: 30px;" class="TD_THOIGIAN">Tự đánh giá tiến độ thực hiện công việc</th>
                    <th rowspan="2" style="width: 30px;" class="TD_THOIGIAN">Tự đánh giá Chất lượng (Đảm bảo/ Không đảm bảo)</th>
                    <th rowspan="2" style="width: 30px;" class="TD_TYLE">Tự đánh giá tỷ lệ (%) Chất lượng</th>

                    <th colspan="2" class="TD_THOIGIAN">Lãnh đạo phụ trách đánh giá</th>
                    <th rowspan="2" class="TD_GHICHU">Ghi chú(nguyên nhân chậm tiến độ/không đảm bảo chất lượng)</th>                                            
                    <th  rowspan="2" style="width: 30px;" class="TD_THEMXOA">Thêm/Xóa</th>                                    
                </tr>  
                <tr>
                    <th  class="TD_THOIGIAN">Thời gian đăng ký</th>
                    <th  class="TD_THOIGIAN">Thời gian hoàn thành</th>
                    <th  class="TD_TENTS">Đánh giá về tiến độ (Đạt/ Chậm tiến độ)</th>
                    <th  class="TD_TYLE">Tỷ lệ (%) về tiến độ</th>
                    <th  class="TD_TENTS">Đánh giá về tiến độ</th>
                    <th  class="TD_TENTS">Đánh giá chất lượng</th                    
                </tr>
                <tr>         
                    <th style="width: 50px; font: italic; font-size: xx-small;" class="TD_TTHIENTHI">(1)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_NOIDUNG">(2)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(3)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_CBTH">(4)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(5)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(6)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(7)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(8)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(9)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(10)</th>     
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(11)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(12)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(13)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(14)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(15)</th>
                    <!--<th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(13)</th>-->
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr>  
                            <td align = "right" class="TD_TTHIENTHI">
                                <input type="text" value="<s:property  value="TT_HIENTHI" />"  
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0"
                                       readonly="true"/>
                            </td>    
                            <td align = "left" class="TD_NOIDUNG">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
                                
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH " onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D16" />" id="D16_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D16 number TEN_KH" 
                                       onblur="if (this.value == '') {this.value = 0} ;sumColumn06('<s:property  value="MA"/>');"
                                       onfocus="this.select();
                                               sumColumn06('<s:property  value="MA"/>');" readonly="true"/>
                            </td>
                            <td align = "right" class="TD_CBTH">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D4" />"  id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH D0" onfocus="this.select();
                                       " readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH " onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');"
                                       readonly="true"
                                       />
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " 
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D19" />" id="D19_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="number2 TEN_KH" onfocus="this.select();
                                               sumColumn06('<s:property  value="MA"/>');" readonly="true"/>
                            </td>

                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " 
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D20" />" id="D20_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="number2 TEN_KH" onfocus="this.select();" 
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class=" number2 TEN_KH" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');" 
                                       readonly="true"/>
                            </td>

                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " readonly="true" />
                            </td>
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" onfocus="this.select();
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
                            <td align = "right" class="TD_TTHIENTHI">
                                <input type="text" value="<s:property  value="TT_HIENTHI" />"  
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0"
                                       readonly="true"/>
                            </td>  
                            <td align = "left" class="TD_NOIDUNG">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
                                
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH " onfocus="this.select();"
                                       />
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D16" />" id="D16_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D16 number TEN_KH" 
                                       onblur="if (this.value == '') {this.value = 0} ;sumColumn06('<s:property  value="MA"/>');"
                                       onfocus="this.select();
                                               sumColumn06('<s:property  value="MA"/>');" />
                            </td>
                            <td align = "right" class="TD_CBTH">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');"
                                       />
                            </td>
                            <td align = "left" class="TD_THOIGIAN">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D4"
                                    name="lstDulieuNt[%{#rowstatus.index}].D4"
                                    list="lstPhongBan" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="vertical-align: middle;width:100%;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 datepicker" placeholder="dd/MM/yyyy"  onfocus="this.select();" />
                            </td>                            
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D0 datepicker" placeholder="dd/MM/yyyy"  class="TEN_KH" onfocus="this.select();"/>
                            </td>
                            <td align = "left" class="TD_THOIGIAN">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D7"
                                    name="lstDulieuNt[%{#rowstatus.index}].D7"
                                    list="lstDatKhong" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="vertical-align: middle;width:100%;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D19" />" id="D19_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="D19 number2 TEN_KH" onfocus="this.select();"
                                       onblur="sumColumn06('<s:property  value="MA"/>');"
                            </td>
                            <td align = "left" class="TD_THOIGIAN">                                        
                                <s:select 
                                    id="lstDulieuNt[%{#rowstatus.index}].D8"
                                    name="lstDulieuNt[%{#rowstatus.index}].D8"
                                    list="lstDambao" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="vertical-align: middle;width:100%;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D20" />" id="D20_<s:property  value="%{#rowstatus.index}" />"                                                                                        
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="D20 number2 TEN_KH" onfocus="this.select();
                                           sumColumn06('<s:property  value="MA"/>');" onblur="sumColumn06('<s:property  value="MA"/>');" />
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D9 number2 TEN_KH" onfocus="this.select();"
                                       onblur="sumColumn06('<s:property  value="MA"/>');"
                            </td>

                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number2 TEN_KH" onfocus="this.select();"
                                       onblur="sumColumn06('<s:property  value="MA"/>');"
                            </td>
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH " onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " />
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
            &emsp;
            <div id="divTitle">
                PL06/ĐGXL - TỶ LỆ CÔNG VIỆC ĐỊNH TÍNH ĐỐI VỚI TẬP THỂ PHÒNG/BAN 
            </div>
             &emsp;                             
                <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                    <tr height="23">
                        <th rowspan="2" class="TD_TTHIENTHI">TT</th>
                        <th rowspan="2" class="TD_NOIDUNG06A">Nội dung</th>                                         
                        <th colspan="2" class="TD_SOLUONG">Tập thể tự đánh giá</th> 
                        <th colspan="3" class="TD_SOLUONG">Lãnh đạo phụ trách đánh giá</th>   
                            <s:if test="RULEUSER.equalsIgnoreCase('9')">
                            <th colspan="2" class="TD_SOLUONG">Hội đồng đánh giá</th>                          
                            </s:if>    
                    </tr>                
                    <tr height="22">         
                        <th class="TD_SOLUONG">% Hoàn thành tiến độ</th>  
                        <th class="TD_SOLUONG">% Hoàn thành đảm bảo chất lượng</th>  
                        <th class="TD_SOLUONG">% Hoàn thành tiến độ</th>  
                        <th class="TD_SOLUONG">% Hoàn thành đảm bảo chất lượng</th> 
                        <th class="TD_SOLUONG">Ghi chú</th> 
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt_TH" var="modelView" status="rowstatus">                    
                        <tr height="16">    
                            <td align = "right" class="TD_TTHIENTHI">
                                <input type="text" value="<s:property  value="TT_HIENTHI" />"  
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0"
                                       readonly="true"/>
                            </td> 
                            <td  align="right" class="TD_NOIDUNG06A">    
                                <input type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                                                    
                                <input type="hidden" value="<s:property  value="THUTU" />" 
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].THUTU"/> 
                                <input type="hidden" value="<s:property  value="MA" />" 
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].MA"/> 
                                <input type="hidden" value="<s:property  value="D15" />" 
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D15"/>                                 
                                <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/> 
                                <input type="hidden" value="<s:property  value="CO_TONGHOP" />" 
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/> 
                            </td>                                                        
                                <td align="center" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="MA" />"
                                           name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D9" class="number2 TEN_KH" onblur="if (this.value == '') {this.value = 0}" onfocus="this.select()"
                                           readonly="readonly" />  
                                </td>                            
                                <td align="center" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D10" />"  id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH" onblur="if (this.value == '') {this.value = 0}" onfocus="this.select()"  
                                           readonly="readonly" />  
                            </td>   
                            </td>                                                        
                                <td align="center" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="MA" />"
                                           name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D11" class="number2 TEN_KH" onblur="if (this.value == '') {this.value = 0}" onfocus="this.select()"
                                           <s:if test="NHAPTAY.equalsIgnoreCase('N')">readonly="readonly"</s:if>/>                                  
                                </td>                            
                            <td align="center" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D12" />"  id="D12_<s:property  value="MA" />"
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D12" class="number2 TEN_KH" onblur="if (this.value == '') {this.value = 0}" onfocus="this.select()" 
                                       <s:if test="NHAPTAY.equalsIgnoreCase('N')">readonly="readonly"</s:if>/>                                  
                            </td>
                            <td align="center" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D13" />"  id="D13_<s:property  value="MA" />"
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D13" class=" TEN_KH" onfocus="this.select()"
                                       <s:if test="NHAPTAY.equalsIgnoreCase('N')">readonly="readonly"</s:if>	/>                                  
                            </td>
                        </tr>                    

                    </s:iterator>
                </table>     
            </s:if>
            
            <s:if test="RULEUSER.equalsIgnoreCase('9')">            
                <tr>
                    <!--<th  style="width: 30px;" class="TD_TENTS">TT</th>-->
                    <th rowspan="2" class="TD_NOIDUNG">Nội dung</th>
                    <th rowspan="2" class="TD_TYLE">Tỷ trọng công việc</th>  
                    <th rowspan="2" class="TD_CBTH">Cán bộ thực hiện</th>
                    <th rowspan="2" style="width: 30px;" class="TD_THOIGIAN">LĐ Ban/ Phòng chỉ đạo(Giao)</th>
                    <th colspan="4" style="width: 30px;" class="TD_THOIGIAN">Tự đánh giá tiến độ thực hiện công việc</th>
                    <th rowspan="2" style="width: 30px;" class="TD_THOIGIAN">Tự đánh giá Chất lượng (Đảm bảo/ Không đảm bảo)</th>
                    <th rowspan="2" style="width: 30px;" class="TD_TYLE">Tự đánh giá tỷ lệ (%) Chất lượng</th>

                    <th colspan="2" class="TD_THOIGIAN">Lãnh đạo phụ trách đánh giá</th>
                    <th rowspan="2" class="TD_GHICHU">Ghi chú(nguyên nhân chậm tiến độ/không đảm bảo chất lượng)</th>                                            
                    <!--<th  rowspan="2" style="width: 30px;" class="TD_THEMXOA">Thêm/Xóa</th>-->                                    
                </tr>  
                <tr>
                    <th  class="TD_THOIGIAN">Thời gian đăng ký</th>
                    <th  class="TD_THOIGIAN">Thời gian hoàn thành</th>
                    <th  class="TD_TENTS">Đánh giá về tiến độ (Đạt/ Chậm tiến độ)</th>
                    <th  class="TD_TYLE">Tỷ lệ về tiến độ (Đạt/ Chậm tiến độ)</th>
                    <th  class="TD_TENTS">Đánh giá về tiến độ</th>
                    <th  class="TD_TENTS">Đánh giá chất lượng</th                    
                </tr>
                <tr>         
                    <!--<th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TENTS">(1)</th>-->
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_NOIDUNG">(2)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TYLE">(3)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_CBTH">(4)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(5)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(6)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(7)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(8)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(9)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(10)</th>     
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(11)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(12)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_THOIGIAN">(13)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(14)</th>
                    <!--<th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(13)</th>-->
                    <!--<th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(13)</th>-->
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr>  

                            <td align = "left" class="TD_NOIDUNG">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" value="<s:property  value="MAPGD" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/> 
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH " onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_CBTH">
                                <input type="text" id="D16_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D16" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="number2 TEN_KH" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_CBTH">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D4" />"  id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH D0" onfocus="this.select();
                                       " readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH " onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');"
                                       readonly="true"
                                       />
                            </td>                            
                            
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " 
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D19" />" id="D19_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="number2 TEN_KH" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " 
                                       readonly="true"/>
                            </td>

                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " 
                                       readonly="true"/>
                            </td>
                            
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D20" />" id="D20_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="number2 TEN_KH" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " 
                                       readonly="true"/>
                            </td>
                            
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="number2 TEN_KH" 
                                       readonly="true"/>
                            </td>

                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " readonly="true" />
                            </td>
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " readonly="true"/>
                            </td>                                                    
                                                      
                        </tr>
                    </s:if>

                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr>  
                            <td align = "left" class="TD_NOIDUNG">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" value="<s:property  value="MAPGD" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD"/>                     
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH " onfocus="this.select();" readonly="readonly"
                                       />
                            </td>
                            <td align = "right" class="TD_CBTH">
                                <input type="text" id="D16_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D16" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="number2 TEN_KH" onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_CBTH">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');" readonly="readonly"
                                       />
                            </td>
                            <td align = "left" class="TD_THOIGIAN">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D4"
                                    name="lstDulieuNt[%{#rowstatus.index}].D4"
                                    list="lstPhongBan" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="vertical-align: middle;background-color: #FFCCBA; TEN_KH" disabled="true" > 
                                </s:select>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 datepicker" placeholder="dd/MM/yyyy"  onfocus="this.select();" readonly="readonly"/>
                            </td>                            
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D0 datepicker" placeholder="dd/MM/yyyy"  class="TEN_KH" onfocus="this.select();" readonly="readonly"/>
                            </td>
                            <td align = "left" class="TD_THOIGIAN">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D7"
                                    name="lstDulieuNt[%{#rowstatus.index}].D7"
                                    list="lstDatKhong" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="vertical-align: middle;background-color: #FFCCBA; TEN_KH" disabled="true" >
                                </s:select>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D19" />" id="D19_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="TEN_KH number2" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " 
                                       readonly="true"/>
                            </td>

                            <td align = "left" class="TD_THOIGIAN">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D8"
                                    name="lstDulieuNt[%{#rowstatus.index}].D8"
                                    list="lstDambao" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="vertical-align: middle;background-color: #FFCCBA; TEN_KH" disabled="true" >
                                </s:select>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D20" />" id="D20_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="TEN_KH number2" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " 
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH number2" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " 
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_THOIGIAN">
                                <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH number2" onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');
                                       " 
                                       readonly="true"/>
                            </td>  
                            
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D18" />" id="D18_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH " onfocus="this.select();" readonly="true"/>
                            </td>                                                                                                                                                                    
                        </tr>
                    </s:if>                   


                </s:iterator>
            </table>
            &emsp;
            <div id="divTitle">
                PL06/ĐGXL - TỶ LỆ CÔNG VIỆC ĐỊNH TÍNH ĐỐI VỚI TẬP THỂ PHÒNG/BAN
            </div>
              &emsp;                            
                <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                    <tr height="23">
                        <!--<th class="TD_THUTU">TT</th>-->
                        <th rowspan="2" class="TD_NOIDUNG06A">Nội dung</th>                                         
                        <th colspan="2" class="TD_SOLUONG">Tập thể tự đánh giá</th>                             
                        <th colspan="3" class="TD_SOLUONG">Lãnh đạo phụ trách đánh giá</th>                          
                        <!--<th colspan="3" class="TD_SOLUONG">Hội đồng đánh giá</th>-->                          
                            <%--</s:if>--%>    
                    </tr>                
                    <tr height="22">         
                        <th class="TD_SOLUONG">% Hoàn thành tiến độ</th>  
                        <th class="TD_SOLUONG">% Hoàn thành đảm bảo chất lượng</th>   
                        <th class="TD_SOLUONG">% Hoàn thành tiến độ</th>  
                        <th class="TD_SOLUONG">% Hoàn thành đảm bảo chất lượng</th>  
<!--                        <th class="TD_SOLUONG">% Hoàn thành tiến độ</th>  
                        <th class="TD_SOLUONG">% Hoàn thành đảm bảo chất lượng</th>  -->
                        <th class="TD_GHICHU">Ghi chú</th>  
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt_TH" var="modelView" status="rowstatus">                    
                        <tr height="16">                      
                            <td  align="right" class="TD_NOIDUNG06A">    
                                <input type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()"    readonly="readonly" />                                                                    
                                <input type="hidden" value="<s:property  value="THUTU" />" 
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].THUTU"/> 
                                <input type="hidden" value="<s:property  value="MAPGD" />" 
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].MAPGD"/> 
                                <input type="hidden" value="<s:property  value="MA" />" 
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].MA"/>                                 
                                <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/> 
                                <input type="hidden" value="<s:property  value="CO_TONGHOP" />" 
                                       name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP"/> 
                            </td>                                                        
                                <td align="center" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="MA" />"
                                           name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D9" class="number2 TEN_KH" onfocus="this.select()"  readonly="true"/>                                  
                                </td>                            
                                <td align="center" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D10" />"  id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH" onfocus="this.select()" readonly="true"/>                                  
                                </td>    
                                
                                <td align="center" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D11" />"  id="D11_<s:property  value="MA" />"
                                           name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D11" class="number2 TEN_KH" onfocus="this.select()" readonly="true"/>                                  
                                </td> 
                                <td align="center" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D12" />"  id="D12_<s:property  value="MA" />"
                                           name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D12" class="number2 TEN_KH" onfocus="this.select()" readonly="true"/>                                  
                                </td> 
<!--                                <td align="center" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D16" />"  id="D16_<s:property  value="MA" />"
                                           name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D16" class="number2 TEN_KH" onblur="if (this.value == '') {this.value = 0}" onfocus="this.select()" />                                  
                                </td> 
                                <td align="center" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D17" />"  id="D17_<s:property  value="MA" />"
                                           name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D17" class="number2 TEN_KH" onblur="if (this.value == '') {this.value = 0}" onfocus="this.select()" />                                  -->
                                </td>
                                <td align="center" class="TD_SOLUONG">
                                    <input type="text" value="<s:property  value="D13" />"  id="D13_<s:property  value="MA" />"
                                           name="lstDulieuNt_TH[<s:property  value="%{#rowstatus.index}" />].D13" class=" TEN_KH" onfocus="this.select()" readonly="true"/>                                  
                                </td>
                        </tr>                    

                    </s:iterator>
                </table>     
            </s:if>
            

            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <!--        <script>
                    initTable();
                </script>-->
    </body>
</html>
