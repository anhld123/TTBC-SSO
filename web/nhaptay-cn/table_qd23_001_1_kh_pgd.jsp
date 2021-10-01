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
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $(".datepicker_month").datepicker({dateFormat: 'mm/yy'});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_STT").css({"width": "30px"});
                $(".TD_MAKH").css({"width": "70px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "130px"});
                $(".TD_SOKU").css({"width": "120px"});
                $(".TD_SOTIEN").css({"width": "90px"});
                $(".TD_CHITIEU").css({"width": "300px"});
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
            function nhapDieuchinh(masothue, tendn, thangbc) {
                try
                {
                    if (masothue.length < 3)
                    {
                        alert('Doanh nghiệp chưa có mã số thuế !');
                        return;
                    }
                    var pheduyet = 'N';
                    var ht1 = screen.height - 100;
                    var wt1 = screen.width - 100;
                    var left1 = 50;//(screen.width / 2) - (wt1 / 2);
                    var top1 = 50;
                    var ngay_bc = $("#ngay_bc_DATE").val();
                    var khoa_cdtt = $("#khoa_cdtt").val();
                    var url = "loadDieuchinhKh.action?masothue=" + masothue + "&ngay_bc=" + ngay_bc + "&tendn=" + tendn+ "&thangbc=" + thangbc;

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
            
            function nhapDieuchinhPheduyet(khoadc, masothue, tendn, thangbc) {
                try
                {
                    if (masothue.length < 3)
                    {
                        alert('Doanh nghiệp chưa có mã số thuế !');
                        return;
                    }
                    var pheduyet = 'N';
                    var ht1 = screen.height -100;
                    var wt1 = screen.width - 100;
                    var left1 = 50;//(screen.width / 2) - (wt1 / 2);
                    var top1 = 50;
                    var ngay_bc = $("#ngay_bc_DATE").val();
                    var khoa_cdtt = $("#khoa_cdtt").val();
                    var url = "loadDieuchinhPheduyetChovay.action?masothue=" + masothue + "&ngay_bc=" + ngay_bc + "&tendn=" + tendn + "&khoadc=" + khoadc + "&thangbc=" + thangbc;

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

            function updateDsNguoiLD_QD23(masothue, tendn,thangbc) {
                try
                {
                    if (masothue.length < 3)
                    {
                        alert('Doanh nghiệp chưa có mã số thuế !');
                        return;
                    }
                    var pheduyet = 'N';
                    var ht1 = screen.height;
                    var wt1 = screen.width;
                    var left1 = 0;//(screen.width / 2) - (wt1 / 2);
                    var top1 = 0;
                    var ngay_bc = $("#ngay_bc_DATE").val();
                    var khoa_cdtt = $("#khoa_cdtt").val();
                    var url = "updateDsNguoiLD_QD23.action?masothue=" + masothue + "&ngay_bc=" + ngay_bc + "&tendn=" + tendn + "&thangbc=" + thangbc;

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

            function initTable()
            {
                var table = document.getElementById("tablekyquy04");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    var matmp = getMabyNumber(i);//   

                    if (matmp == 1)
                    {
                        $('input:checkbox[id=' + i + ']').attr('checked', true);
                    }
                }
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

            function autoEvaluate() {

                var arrCot = [".D13", ".D14", ".D15"]; //Luu cac cot cua du lieu can tinh toan
                for (var i = 0; i < 50; i++) {
                    //8=2+4-6
                    $(".D13").eq(i).val(parseFloat($(".D14").eq(i).val()) + parseFloat($(".D15").eq(i).val()));

                }
                //                              
            }
            
                function js_confirmDelete() {
                    var r = confirm('(Msg)Bạn chắc chắn muốn xoá nhóm người dùng này?');
                    if (r === false) {
                        event.preventDefault();
                    }
                }

        </script>

        <style>     

            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }       
            .cls-over{
                overflow-y: scroll;
                height: 70vh;
                overflow-x: scroll;
            }
            .editDelete{
                border: 1px solid #999;
            }

            .editDelete td,th{
                border: 1px solid #999;
            }
            .editDelete           input:readonly {
                background-color: red;
            }



        </style>
        <link href="css/css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body style="font-family: ">
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
            </br>
            <div id="divTitle">
                DANH SÁCH NGƯỜI SỬ DỤNG LAO ĐỘNG ĐƯỢC HƯỞNG CHÍNH SÁCH VAY VỐN ĐỂ TRẢ LƯƠNG NGỪNG VIỆC
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nhaptaycn"/>
            <!--                <div id="divDonvitinh">
                                Đơn vị tính: Đồng
                            </div>-->
            </br>
            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 2100px; max-height:45vh">
                    <table class="editDelete cls-table" >
                        <tr height="50px">      
                            <th  rowspan="2"</th> 
                            <th  rowspan="2" class="TD_MAKH">Điều chỉnh đề nghị</th> 
                            <th  rowspan="2" class="TD_MAKH">DS người lao động <span style="color:red">(số lao động đã upload)</span></th>
                            <th rowspan="2" class="TD_MAKH">Mã doanh nghiệp</th>                           
                            <th rowspan="2" class="TD_CHITIEU">Tên doanh nghiệp <span style="color:red">*</span></th>  
                            <th rowspan="2" class="TD_MAKH">Mã số thuế <span style="color:red">*</span></th>    
                            <th rowspan="2" class="TD_MAKH">CMND người đại diện</th>
                            <th rowspan="2" class="TD_TENKH">Tên người đại diện</th>
                            <th rowspan="2" class="TD_NGAY">Ngày tiếp nhận hs <span style="color:red">*</span></th>
                            <th rowspan="2" class="TD_NGAY">Hình thức tiếp nhận <span style="color:red">*</span></th>                            
                            <!--<th rowspan="2" class="TD_NGAY">Ngành nghề KD chính</th>--> 
                            <th rowspan="2" class="TD_TENKH">Địa chỉ</th> 

                            
                            <th rowspan="2" class="TD_NGAY">Mức lương vùng <span style="color:red">*</span></th>                            
                            <th colspan="5">Đề nghị theo hồ sơ vay vốn <span style="color:red">*</span></th>      
                            <!--<th  rowspan="2" class="TD_MAKH">Kế hoạch dư nợ sau giao chỉ tiêu <span style="color:red">*</span></th>-->   
<!--                            <th  rowspan="2" class="TD_MAKH">Ngày lương theo HĐ <span style="color:red">*</span></th> 
                            <th rowspan="2" class="TD_MAKH">Gấy đề nghị tái cấp vốn</th>
                            <th rowspan="2" class="TD_NGAY">Ngày đề nghị tái cấp vốn</th>-->
                            <th  rowspan="2" class="TD_TENKH">Ghi chú</th>     
                            <th rowspan="2" class="TD_NGAY">TT Duyệt</th>
                        </tr>         
                        <tr>
                            <th class="TD_NGAY">Tháng vay <span style="color:red">*</span></th>
                            <th class="TD_MAKH">Đối tượng thụ hưởng <span style="color:red">*</span></th>
                            <th  class="TD_NGAY">Tổng số lao động được đề nghị vay để trả lương <span style="color:red">*</span></th>    
                            <th  class="TD_NGAY">Trong đó, số lao động mới (nếu có) <span style="color:red">*</span></th>
                            <th class="TD_MAKH">Số tiền đề nghị vay <span style="color:red">*</span></th>

                        </tr>
                        <tr>
                            <td></td>
                            <td style="text-align: center"></td>
                            <td style="text-align: center"></td> 
                            <td style="text-align: center">1</td>
                            <td style="text-align: center">2</td>
                            <td style="text-align: center">3</td>
                            <td style="text-align: center">4</td>
                            <td style="text-align: center">5</td>
                            <td style="text-align: center">6</td>
                            <td style="text-align: center">7</td>
                            <!--<td style="text-align: center">20</td>-->
                            <td style="text-align: center">21</td>
                            
                            <td style="text-align: center">10</td>
                            <td style="text-align: center">11</td>
                            <td style="text-align: center">15</td>
                            <td style="text-align: center">12</td>
                            <td style="text-align: center">14</td>
                            <td style="text-align: center">13</td>

                            <!--<td style="text-align: center">18</td>-->
<!--                            <td style="text-align: center">16</td>
                            <td style="text-align: center">8</td>
                            <td style="text-align: center">9</td>-->
                            <td style="text-align: center">17</td>     

                        </tr>
                        <s:iterator value="#attr.lstDulieuNt50" var="modelView" status="rowstatus">                             
                            <tr> 
                                <!--Doanh nghiệp đã được duyệt-->
                                <td align = "center" class="TD_STT">
                                    <s:url id="deleteUrlQD23" value="UserGroup_deleteQD23.action">
                                        <s:param name="masothue">
                                         ${D3}${D11}${D15}
                                        </s:param> 
                                    </s:url>
                                    <sj:a href="%{deleteUrlQD23}" targets="luu_thanhcong_del" onclick="js_confirmDelete();"><u>Xóa</u></sj:a>
                                 </td> 
                                <s:if test="NHAPTAY.equalsIgnoreCase(1)"> 
                                    <s:if test="!D45.equalsIgnoreCase(1)">
                                        <td align = "center" class="TD_MAKH">
                                            <a href="javascript:nhapDieuchinhPheduyet('QD23_006','<s:property value="D3"/>','<s:property value='D2'/>','<s:property value='D11'/>_<s:property value='D15'/>_<s:property value='D6'/>')" class="SOKU linkKh">
                                                Điều chỉnh
                                            </a>
                                        </td> 
                                    </s:if>
                                    <s:else>
                                        <td></td>
                                    </s:else>

                                     <s:if test="!D45.equalsIgnoreCase(9) && !D45.equalsIgnoreCase(0) && !D45.equalsIgnoreCase(5) && !D45.equalsIgnoreCase(6) && !D45.equalsIgnoreCase(7)">
                                        <td align = "center" class="TD_THOIGIAN">
                                            <a href="javascript:updateDsNguoiLD_QD23('<s:property value="D3"/>','<s:property value='D2'/>','<s:property value='D11'/>_<s:property value='D15'/>_<s:property value='D6'/>')" class="SOKU linkKh">
                                                Upload (<span style="color:red"><s:property value='TEN'/></span>)
                                            </a>
                                        </td>
                                    </s:if>    
                                    <s:else>
                                        <td></td>
                                    </s:else>


                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D1" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();" style="background: #C0C0C0 !important;"
                                               readonly="true"/>
                                        <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>" />
                                    </td>                                
                                    <td align = "right" class="TD_TENKH" >
                                        <input type="text"   value="<s:property  value="D2" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();" style="background: 	#C0C0C0 !important;" 
                                               readonly="readonly"/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D3" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D4" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>
                                    <td align = "right" class="TD_TENKH" >
                                        <input type="text"   value="<s:property  value="D5" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D6" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>
<!--                                    <td align = "center" class="TD_NGAY">
                                        <input type="text" value="<s:property  value="D6" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH D0 datepicker" placeholder="dd/MM/yyyy" />
                                    </td> -->

                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D7" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>

<!--                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D20" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D20" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>-->
                                    <td align = "right" class="TD_TENKH" >
                                        <input type="text"   value="<s:property  value="D21" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D21" class="TEN_KH" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td> 
                                    
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D10" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D11" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"  value="<s:property  value="D15" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D12" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td> 
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D14" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D13" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td> 


<!--                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"  value="<s:property  value="D18" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D18" class="D18 TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>-->
<!--                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D16" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D16" class="TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td> 
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D8" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D9" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td> -->

                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"  value="<s:property  value="D17" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D17" class="TEN_KH" onfocus="this.select();" style="background:#C0C0C0 !important;" 
                                               readonly="true"/>
                                    </td>
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D37" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D37" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>  
                                </s:if>
                                <!--Doanh nghiệp chưa duyệt-->
                                <s:else>
                                    <td>

                                    </td> 
                                    <s:if test="!D45.equalsIgnoreCase(9) && !D45.equalsIgnoreCase(0) && !D45.equalsIgnoreCase(5) && !D45.equalsIgnoreCase(6) && !D45.equalsIgnoreCase(7)">
                                        <td align = "center" class="TD_THOIGIAN">
                                            <a href="javascript:updateDsNguoiLD_QD23('<s:property value="D3"/>','<s:property value='D2'/>','<s:property value='D11'/>_<s:property value='D15'/>_<s:property value='D6'/>')" class="SOKU linkKh">
                                                Upload (<span style="color:red"><s:property value='TEN'/></span>)
                                            </a>
                                        </td>
                                    </s:if>    
                                    <s:else>
                                        <td></td>
                                    </s:else>

                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D1" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();"
                                                style="background: 	#ABEBC6 !important;"/>
                                        <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>" />
                                    </td>                                
                                    <td align = "right" class="TD_TENKH" >
                                        <input type="text"   value="<s:property  value="D2" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();"/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D3" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();" style="background: #FFBF00 !important;" /> 
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D4" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" onfocus="this.select();"/>
                                    </td>
                                    <td align = "right" class="TD_TENKH" >
                                        <input type="text"   value="<s:property  value="D5" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH" onfocus="this.select();"/>                                                                        
                                    </td>
                                    <td align = "center" class="TD_NGAY">
                                        <input type="text" value="<s:property  value="D6" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH D0 datepicker" placeholder="dd/MM/yyyy" />
                                    </td> 

                                    <td align = "left" class="TD_NGAY">                                        
                                        <s:select  
                                            id="lstDulieuNt50[%{#rowstatus.index}].D7"
                                            name="lstDulieuNt50[%{#rowstatus.index}].D7"
                                            list="lstHinhthucTNHS" 
                                            listKey="sKey"
                                            listValue="sDesc"
                                            headerKey="-1"
                                            headerValue="--- Chọn ---"                                    
                                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                        </s:select>
                                    </td>  
<!--                                    <td align = "left" class="TD_NGAY">                                        
                                        <s:select  
                                            id="lstDulieuNt50[%{#rowstatus.index}].D20"
                                            name="lstDulieuNt50[%{#rowstatus.index}].D20"
                                            list="lstPLKT" 
                                            listKey="sKey"
                                            listValue="sDesc"
                                            headerKey="-1"
                                            headerValue="--- Chọn ---"                                    
                                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                        </s:select>
                                    </td> -->
                                    <td align = "right" class="TD_TENKH" >
                                        <input type="text"   value="<s:property  value="D21" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D21" class="TEN_KH" onfocus="this.select();"/>
                                    </td>
                                      
                                    <td align = "left" class="TD_NGAY">                                        
                                        <s:select  
                                            id="lstDulieuNt50[%{#rowstatus.index}].D10"
                                            name="lstDulieuNt50[%{#rowstatus.index}].D10"
                                            list="lstLuongVung" 
                                            listKey="sKey"
                                            listValue="sDesc"
                                            headerKey="-1"
                                            headerValue="--- Chọn ---"                                    
                                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                        </s:select>
                                    </td>  
<!--                                    <td align = "center" class="TD_NGAY">
                                        <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0"  readonly="true"/>
                                    </td> -->
                                     <td align = "left" class="TD_NGAY">                                        
                                        <s:select  
                                            id="lstDulieuNt50[%{#rowstatus.index}].D11"
                                            name="lstDulieuNt50[%{#rowstatus.index}].D11"
                                            list="lstThangvay" 
                                            listKey="sKey"
                                            listValue="sDesc"
                                            headerKey="-1"
                                            headerValue="--- Chọn ---"                                    
                                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                        </s:select>
                                    </td>  
                                    <td align = "left" class="TD_NGAY">                                        
                                        <s:select  
                                            id="lstDulieuNt50[%{#rowstatus.index}].D15"
                                            name="lstDulieuNt50[%{#rowstatus.index}].D15"
                                            list="lstDTTH" 
                                            listKey="sKey"
                                            listValue="sDesc"
                                            headerKey="-1"
                                            headerValue="--- Chọn ---"                                    
                                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                        </s:select>
                                    </td> 
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D12" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number" onfocus="this.select();"/>
                                    </td> 
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D14" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 TEN_KH number" onfocus="this.select();"
                                               />
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D13" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 TEN_KH number" onfocus="this.select();" 
                                               />
                                    </td> 


<!--                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"  value="<s:property  value="D18" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D18" class="D18 TEN_KH number" onfocus="this.select();"/>
                                    </td>-->
<!--                                    <td align = "left" class="TD_MAKH">                                        
                                        <s:select  
                                            id="lstDulieuNt50[%{#rowstatus.index}].D16"
                                            name="lstDulieuNt50[%{#rowstatus.index}].D16"
                                            list="lstNgayluongHD" 
                                            listKey="sKey"
                                            listValue="sDesc"
                                            headerKey="-1"
                                            headerValue="--- Chọn ---"                                    
                                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                        </s:select>
                                    </td>    
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D8" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH D0" onfocus="this.select();"/>
                                    </td> 
                                    <td align = "center" class="TD_NGAY">
                                        <input type="text" value="<s:property  value="D9" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH D0 datepicker" placeholder="dd/MM/yyyy" />
                                    </td>-->
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"  value="<s:property  value="D17" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D17" class="TEN_KH" onfocus="this.select();"/>
                                    </td>
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D37" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D37" class="D14 TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"
                                               />
                                    </td>
                                </s:else>



                            </tr>                                                                                                                                                                                   
                        </s:iterator>
                    </table>        
                </div>
            </div>

            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <!--        <script>
                    initTable();
                </script>-->
    </body>


</html>
