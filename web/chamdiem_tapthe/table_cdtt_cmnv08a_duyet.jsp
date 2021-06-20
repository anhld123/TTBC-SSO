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
        <script src="chamdiem_tapthe/js/chamdiem_tapthe.js"></script>  
        <script>
            function sumColumn(Class_name, cur, macb)
                {
                    try
                    {
                        var tong_I = 0, tong_B = 0;
                        var id_D12 = '';
                        $(Class_name).each(function () {
                                        id_D12 = $(this).attr("id"); //this.id

        //                        $(this).css({'background-color': '#FFFFFF'});                        
                        });

                        if (Class_name === '.CONGCAP_D12')
                        {
                            var giatri_D12_1 = parseFloat(document.getElementById("D12_CDTT01_"+macb).value);                            
                            var giatri_D12_2 = parseFloat(document.getElementById("D12_CDTT02_"+macb).value);
                            var giatri_D12_3 = parseFloat(document.getElementById("D12_CDTT03_"+macb).value);
                            var giatri_D12_4 = parseFloat(document.getElementById("D12_CDTT04_"+macb).value);
                            var giatri_D12_5 = parseFloat(document.getElementById("D12_CDTT05_"+macb).value);
                            var giatri_D12_6 = parseFloat(document.getElementById("D12_CDTT06_"+macb).value);
                            var giatri_D12_7 = parseFloat(document.getElementById("D12_CDTT07_"+macb).value);
                            var giatri_D12_8 = parseFloat(document.getElementById("D12_CDTT08_"+macb).value);
                            var giatri_D12_9 = parseFloat(document.getElementById("D12_CDTT09_"+macb).value);
                            var giatri_D12_10 = parseFloat(document.getElementById("D12_CDTT10_"+macb).value);
                            var giatri_D12_11 = parseFloat(document.getElementById("D12_CDTT11_"+macb).value);
                            var giatri_D12_12 = parseFloat(document.getElementById("D12_CDTT12_"+macb).value);

                            console.log(giatri_D12_1)
                            if (giatri_D12_5 != 1 && giatri_D12_5 != 2 && giatri_D12_5 != 3 && giatri_D12_5 != 4 && giatri_D12_5 != 5
                                                            && giatri_D12_5 != 10 && giatri_D12_5 != 15 && giatri_D12_5 != 20 && giatri_D12_5 != 0)
                            {
                                            swal('Lỗi', 'Bạn không được nhập khác các giá trị 0,1,2,3,4,5,10,15,20 hoặc nhỏ hơn 0 cho chỉ tiêu 1.1.c', 'warning');
                                            cur.style.background = '#ff0000';
                                            cur.value = 0;
                                            return;
                            }

                            if (giatri_D12_9 != 0 && giatri_D12_9 != 3 && giatri_D12_9 != 5 && giatri_D12_9 != 7 && giatri_D12_9 != 10)
                            {
                                            swal('Lỗi', 'Bạn không được nhập khác các giá trị 0,3,5,7,10 hoặc nhỏ hơn 0 cho chỉ tiêu 2', 'warning');
                                            cur.style.background = '#ff0000';
                                            cur.value = 0;
                                            return;
                            }
                            if (giatri_D12_10 != 0 && giatri_D12_10 != 4 && giatri_D12_10 != 6 && giatri_D12_10 != 8 && giatri_D12_10 != 10)
                            {
                                            swal('Lỗi', 'Bạn không được nhập khác các giá trị 0,4,6,8,10 hoặc nhỏ hơn 0 cho chỉ tiêu 3', 'warning');
                                            cur.style.background = '#ff0000';
                                            cur.value = 0;
                                            return;
                            }
                            if (giatri_D12_11 != 0 && giatri_D12_11 != 1 && giatri_D12_11 != 2 && giatri_D12_11 != 3 && giatri_D12_11 != 5
                                                            && giatri_D12_11 != 7 && giatri_D12_11 != 10)
                            {
                                            swal('Lỗi', 'Bạn không được nhập khác các giá trị 0,1,2,3,5,7,10 hoặc nhỏ hơn 0 cho chỉ tiêu 4', 'warning');
                                            cur.style.background = '#ff0000';
                                            cur.value = 0;
                                            return;
                            }

                            var giatri_D1_9 = parseFloat(document.getElementById("D1_CDTT09_"+macb).value);
                            var giatri_D1_10 = parseFloat(document.getElementById("D1_CDTT10_"+macb).value);
                            var giatri_D1_11 = parseFloat(document.getElementById("D1_CDTT11_"+macb).value);
                            var giatri_D1_12 = parseFloat(document.getElementById("D1_CDTT12_"+macb).value);

                            if (giatri_D12_9 > giatri_D1_9 || giatri_D12_9 < 0)
                            {
                                            swal('Lỗi', 'Bạn không được nhập điểm lớn hơn ' + giatri_D12_9 + ' hoặc nhỏ hơn 0', 'warning');
                                            cur.style.background = '#ff0000';
                                            cur.value = 0;
                                            return;
                            }
                            if (giatri_D12_10 > giatri_D1_10 || giatri_D12_10 < 0)
                            {
                                            swal('Lỗi', 'Bạn không được nhập điểm lớn hơn ' + giatri_D12_10 + ' hoặc nhỏ hơn 0', 'warning');
                                            cur.style.background = '#ff0000';
                                            cur.value = 0;
                                            return;
                            }
                            if (giatri_D12_11 > giatri_D1_11 || giatri_D12_11 < 0)
                            {
                                            swal('Lỗi', 'Bạn không được nhập điểm lớn hơn ' + giatri_D12_11 + ' hoặc nhỏ hơn 0', 'warning');
                                            cur.style.background = '#ff0000';
                                            cur.value = 0;
                                            return;
                            }


                            document.getElementById("D12_CDTT02_"+macb).value = giatri_D12_3 + giatri_D12_4 + giatri_D12_5;
                            document.getElementById("D12_CDTT06_"+macb).value = giatri_D12_7 + giatri_D12_8;
                            document.getElementById("D12_CDTT01_"+macb).value = giatri_D12_3 + giatri_D12_4 + giatri_D12_5 - (giatri_D12_7 + giatri_D12_8);
                            document.getElementById("D12_CDTT99_"+macb).value = giatri_D12_3 + giatri_D12_4 + giatri_D12_5 - (giatri_D12_7 + giatri_D12_8)+
                                                            giatri_D12_9 + giatri_D12_10 + giatri_D12_11 + giatri_D12_12;

                        } else if (Class_name === '.CONGCAP_D11')
                        {                        
                                var giatri_D11_3 = parseFloat(document.getElementById("D11_CDTT03_"+macb).value);
                                var giatri_D11_4 = parseFloat(document.getElementById("D11_CDTT04_"+macb).value);                        
                                var giatri_D11_8 = parseFloat(document.getElementById("D11_CDTT08_"+macb).value);                        
                                var giatri_D11_12 = parseFloat(document.getElementById("D11_CDTT12_"+macb).value);

                                if (giatri_D11_3 > 100)
                                {
                                                swal('Lỗi', 'Bạn không được nhập điểm lớn hơn 100% hoặc nhỏ hơn 0', 'warning');
                                                cur.style.background = '#ff0000';
                                                cur.value = 0;
                                                return;
                                }
                                if (giatri_D11_4 > 100)
                                {
                                                swal('Lỗi', 'Bạn không được nhập điểm lớn hơn 100% hoặc nhỏ hơn 0', 'warning');
                                                cur.style.background = '#ff0000';
                                                cur.value = 0;
                                                return;
                                }

                                var giatri_D1_3 = parseFloat(document.getElementById("D1_CDTT03_"+macb).value);
                                var giatri_D1_4 = parseFloat(document.getElementById("D1_CDTT04_"+macb).value);
                                var giatri_D1_8 = parseFloat(document.getElementById("D1_CDTT08_"+macb).value);
                                var giatri_D1_12 = parseFloat(document.getElementById("D1_CDTT12_"+macb).value);

//                        console.log("giatri_D1_3" + giatri_D1_3 + " giatri_D11_3 " + giatri_D11_3)
                                document.getElementById("D12_CDTT03_"+macb).value = giatri_D1_3*giatri_D11_3/100;
                                document.getElementById("D12_CDTT04_"+macb).value = giatri_D1_4*giatri_D11_4/100;

                                if (giatri_D11_8 > 30)
                                {
                                                document.getElementById("D12_CDTT08_"+macb).value = 0;
                                }
                                else
                                                document.getElementById("D12_CDTT08_"+macb).value = giatri_D11_8;

                                if (giatri_D11_12 > 10)
                                {
                                                document.getElementById("D12_CDTT12_"+macb).value = 0;
                                }
                                else
                                                document.getElementById("D12_CDTT12_"+macb).value = 10 - giatri_D11_12;

                                var giatri_D12_1 = parseFloat(document.getElementById("D12_CDTT01_"+macb).value);
                                var giatri_D12_2 = parseFloat(document.getElementById("D12_CDTT02_"+macb).value);
                                var giatri_D12_3 = parseFloat(document.getElementById("D12_CDTT03_"+macb).value);
                                var giatri_D12_4 = parseFloat(document.getElementById("D12_CDTT04_"+macb).value);
                                var giatri_D12_5 = parseFloat(document.getElementById("D12_CDTT05_"+macb).value);
                                var giatri_D12_6 = parseFloat(document.getElementById("D12_CDTT06_"+macb).value);
                                var giatri_D12_7 = parseFloat(document.getElementById("D12_CDTT07_"+macb).value);
                                var giatri_D12_8 = parseFloat(document.getElementById("D12_CDTT08_"+macb).value);
                                var giatri_D12_9 = parseFloat(document.getElementById("D12_CDTT09_"+macb).value);
                                var giatri_D12_10 = parseFloat(document.getElementById("D12_CDTT10_"+macb).value);
                                var giatri_D12_11 = parseFloat(document.getElementById("D12_CDTT11_"+macb).value);
                                var giatri_D12_12 = parseFloat(document.getElementById("D12_CDTT12_"+macb).value);

                                document.getElementById("D12_CDTT02_"+macb).value = giatri_D12_3 + giatri_D12_4 + giatri_D12_5;
                                document.getElementById("D12_CDTT06_"+macb).value = giatri_D12_7 + giatri_D12_8;
                                document.getElementById("D12_CDTT01_"+macb).value = giatri_D12_3 + giatri_D12_4 + giatri_D12_5 - (giatri_D12_7 + giatri_D12_8);
                                document.getElementById("D12_CDTT99_"+macb).value = giatri_D12_3 + giatri_D12_4 + giatri_D12_5 - (giatri_D12_7 + giatri_D12_8)+
                                                                giatri_D12_9 + giatri_D12_10 + giatri_D12_11 + giatri_D12_12;   
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
                $(".TD_GHICHU").css({"width": "10%"});
                $(".TD_CHITIEU").css({"width": "20%"});
                $(".TD_NOIDUNG").css({"width": "12%"});
                $(".TD_NOIDUNG06A").css({"width": "35%"});
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
                PL08A/ĐGXL - TIÊU CHÍ ĐÁNH GIÁ MỨC ĐỘ HOÀN THÀNH NHIỆM VỤ ĐỐI VỚI </br>
                    CHỨC DANH PHÓ GIÁM ĐỐC BAN, PHÓ TRƯỞNG PHÒNG CMNV
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
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr>  
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
                                <input type="text"  value="<s:property  value="TT_HIENTHI" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0"
                                       readonly="true"/>
                            </td>
                            <td align = "left" class="TD_CHITIEU">
                                <input <s:if test="MA.equalsIgnoreCase('CDTT99')"> style="color: #FF7E00; font-weight: bold;" </s:if>  
                                    type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property  value="MA" />_<s:property  value="D14" />"
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
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number TEN_KH" readonly="true"/>
                            </td>
<!--                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="number TEN_KH" readonly="true"/>
                            </td>-->
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="MA" />_<s:property  value="D14" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH number" 
                                       readonly="true"/>
                            </td>  

                            <td align = "right" class="TD_SOLUONG">
                                <input <s:if test="MA.equalsIgnoreCase('CDTT99')"> style="color: #FF7E00; font-weight: bold;" </s:if>  
                                    type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="MA" />_<s:property  value="D14" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number TEN_KH"
                                       readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D13" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH D0" 
                                       readonly="true"/>
                            </td>                                                                                                           
                        </tr>
                    </s:if>

                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr>  
                            <td align = "left" class="TD_THOIGIAN">
                                <input style="color: red; font-weight: bold;" type="text"  value="<s:property  value="D7" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH"
                                       readonly="true"/>
                            </td>
                            <td align = "center" class="TD_THUTU">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" value="<s:property  value="D14" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" id="D14_<s:property  value="MA" />"/>
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
                                       <s:if test="!FONTFORMAT.equalsIgnoreCase('2')">readonly="readonly"</s:if> />
                                </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />" id="D1_<s:property  value="MA" />_<s:property  value="D14" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="number TEN_KH CONGCAP_D1 D0"
                                       <s:if test="FONTFORMAT.equalsIgnoreCase('2')">onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;"</s:if>                                            
                                        <s:if test="!FONTFORMAT.equalsIgnoreCase('2')">readonly="readonly"</s:if>
                                               />
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
<!--                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class=" TEN_KH"/>
                            </td> -->
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="MA"/>_<s:property  value="D14"/>"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="number TEN_KH CONGCAP_D11"
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               sumColumn('.CONGCAP_D11', this,'<s:property  value="D14"/>');" 
                                    <s:if test="MA.equalsIgnoreCase('CDTT05')  || MA.equalsIgnoreCase('CDTT07') 
                                          || MA.equalsIgnoreCase('CDTT09')|| MA.equalsIgnoreCase('CDTT10')
                                          || MA.equalsIgnoreCase('CDTT11')"> readonly="readonly" </s:if>         
                                               />
                            </td>  

                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="MA" />_<s:property  value="D14"/>"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="number TEN_KH CONGCAP_D12" 
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               sumColumn('.CONGCAP_D12', this,'<s:property  value="D14"/>');"
                                                       <s:if test="MA.equalsIgnoreCase('CDTT03') || MA.equalsIgnoreCase('CDTT04')
                                          || MA.equalsIgnoreCase('CDTT08') || MA.equalsIgnoreCase('CDTT12')"> readonly="readonly" </s:if>   />
                            </td>                            
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D13" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH"/>
                            </td>                                                                                                                                                                    
                        </tr>
                    </s:if>                   

                    </s:iterator>
                </table>                                                                    
            
            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
