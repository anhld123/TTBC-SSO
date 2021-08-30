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
            function nhapdiemtru(masothue, morong) {
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
                    var url = "UploadPL02.action?masothue=" + masothue + "&ngay_bc=" + ngay_bc + "&macb=" + morong + "&pheduyet=" + pheduyet;

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

            function updateDSGiaNgan(masothue, morong) {
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
                    var url = "UploadDSGiaiNgan.action?masothue=" + masothue + "&ngay_bc=" + ngay_bc + "&macb=" + morong + "&pheduyet=" + pheduyet;

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

                var arrCot = [".D3", ".D14", ".D15"]; //Luu cac cot cua du lieu can tinh toan
                for (var i = 0; i < 10; i++) {
                    //8=2+4-6
                    $(".D13").eq(i).val(parseFloat($(".D14").eq(i).val()) + parseFloat($(".D15").eq(i).val()));

                }
                //                              
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
                height: 55vh;
                overflow-x: scroll;
            }
            .editDelete{
                border: 1px solid #999;
            }

            .editDelete td,th{
                border: 1px solid #999;
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
                TỔNG HỢP DANH SÁCH NGƯỜI SỬ DỤNG LAO ĐỘNG ĐƯỢC HƯỞNG CHÍNH SÁCH VAY VỐN ĐỂ TRẢ LƯƠNG NGỪNG VIỆC
            </div>
            <s:hidden name="khoa_nhaptaycn"/>
            <!--                <div id="divDonvitinh">
                                Đơn vị tính: Đồng
                            </div>-->
            </br>
            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 3000px; max-height:20vh">
                    <table class="editDelete cls-table" >
                        <tr height="50px">                                                          
                            <th rowspan="3" class="TD_MAKH">PGD</th>  
                            <th rowspan="3" class="TD_MAKH">Mã doanh nghiệp</th>                           
                            <th rowspan="3" class="TD_TENKH">Tên doanh nghiệp </th>  
                            <th rowspan="3" class="TD_MAKH">Mã số thuế </th>    
                            <th rowspan="3" class="TD_MAKH">CMND người đại diện</th>
                            <th rowspan="3" class="TD_TENKH">Tên người đại diện</th>
                            <th rowspan="3" class="TD_NGAY">Ngày tiếp nhận hs </th>
                            <th rowspan="3" class="TD_NGAY">Hình thức tiếp nhận </th>                            
                            <!--<th rowspan="3" class="TD_NGAY">Ngành nghề KD chính</th>--> 
                            <!--<th rowspan="3" class="TD_TENKH">Địa chỉ</th>--> 

                            <!--                            <th rowspan="3" class="TD_MAKH">Giấy đề nghị </th>
                                                        <th rowspan="3" class="TD_NGAY">Ngày đề nghị </th>-->
                            <th rowspan="3" class="TD_NGAY">Mức lương vùng </th>                            
                            <th colspan="5">Đề nghị theo hồ sơ vay vốn </th>      
                            <th  rowspan="3" class="TD_MAKH">Kế hoạch dư nợ sau giao chỉ tiêu </th>   
                            <!--<th  rowspan="3" class="TD_MAKH">Ngày lương theo HĐ </th>-->    
                            <th  rowspan="3" class="TD_MAKH">Ngày nhập kế hoạch <span style="color:red">*</span></th>   
                            <!--<th  rowspan="3" class="TD_CHITIEU">Ghi chú</th>-->     
                            <!--<th rowspan="3" class="TD_NGAY">TT Duyệt</th>-->
                            <th colspan="7">NHẬP KẾT QUẢ PHÊ DUYỆT CHO VAY (NHCSCH)</th>  
                            <th colspan="5">NHẬP KẾT QUẢ GIẢI NGÂN</th> 
                        </tr>         
                        <tr>
                            <th rowspan="2" class="TD_NGAY">Tháng vay </th>
                            <th rowspan="2"class="TD_MAKH">Đối tượng thụ hưởng </th>
                            <th rowspan="2" class="TD_NGAY">Tổng số lao động được đề nghị vay để trả lương </th>    
                            <th rowspan="2" class="TD_NGAY">Trong đó, số lao động mới (nếu có) </th>
                            <th rowspan="2" class="TD_MAKH">Số tiền đề nghị vay </th>                            


                            <th rowspan="2" class="TD_NGAY">Ngày phê duyệt</th>
                            <th rowspan="2" class="TD_MAKH">Tổng số lượt lao động được phê duyệt cho vay để trả lương</th>
                            <th  rowspan="2" class="TD_NGAY">Trong đó, số lao động mới được phê duyệt (nếu có)</th>    
                            <th rowspan="2" class="TD_NGAY">Số tiền được phê duyệt cho vay</th>  
                            <th  rowspan="2" class="TD_MAKH">Ngày nhập phê duyệt cho vay<span style="color:red">*</span></th>   
                            <th rowspan="2" class="TD_MAKH">Gấy đề nghị tái cấp vốn<span style="color:red">*</span></th>
                            <th rowspan="2" class="TD_NGAY">Ngày đề nghị tái cấp vốn<span style="color:red">*</span></th>

                            <th colspan="4"  class="TD_NGAY">Số tiền giải ngân được nhập vào theo phát sinh hàng ngày (nếu có) trước 16 giờ chiều</th>  
                            <!--<th colspan="4" class="TD_NGAY">Số tiền giải ngân trên Intellect</th>-->  
                            <th rowspan="2" class="TD_NGAY">Ngày nhập TT giải ngân <span style="color:red">*</span></th> 

                        </tr>
                        <tr>
                            <th class="TD_NGAY">Ngày giải ngân</th>
                            <th class="TD_MAKH">Tổng số lượt lao động được giải ngân</th>
                            <th  class="TD_NGAY">Trong đó, số lao động mới được giải ngân(nếu có)</th>    
                            <th  class="TD_NGAY">Số tiền giải ngân</th>

                            <!--                            
                                                        <th class="TD_NGAY">Ngày phê duyệt</th>
                                                        <th class="TD_MAKH">Tổng số lượt lao động được phê duyệt cho vay để trả lương</th>
                                                        <th  class="TD_NGAY">Trong đó, số lao động mới được phê duyệt (nếu có)</th>    
                                                        <th  class="TD_NGAY">Số tiền được phê duyệt cho vay</th>-->
                        </tr>
                        <tr>
                            <!--<td style="text-align: center"></td>-->
                            <td style="text-align: center"></td> 
                            <td style="text-align: center">1</td>
                            <td style="text-align: center">2</td>
                            <td style="text-align: center">3</td>
                            <td style="text-align: center">4</td>
                            <td style="text-align: center">5</td>
                            <td style="text-align: center">6</td>
                            <td style="text-align: center">7</td>
                            <!--<td style="text-align: center">20</td>-->
                            <!--<td style="text-align: center">21</td>-->
                            <!--                            <td style="text-align: center">8</td>
                                                        <td style="text-align: center">9</td>-->
                            <td style="text-align: center">10</td>
                            <td style="text-align: center">11</td>
                            <td style="text-align: center">15</td>
                            <td style="text-align: center">12</td>
                            <td style="text-align: center">14</td>
                            <td style="text-align: center">13</td>

                            <td style="text-align: center">17</td>
                            <!--<td style="text-align: center">16</td>-->
                            <td style="text-align: center">19</td>
                            <!--<td style="text-align: center">18</td>-->     
                            <td style="text-align: center">22</td>
                            <td style="text-align: center">23</td>
                            <td style="text-align: center">24</td>
                            <td style="text-align: center">25</td>
                            <td style="text-align: center">26</td>
                            <td style="text-align: center">43</td>
                            <td style="text-align: center">44</td>
                            <td style="text-align: center">27</td>
                            <td style="text-align: center">28</td>
                            <td style="text-align: center">29</td>
                            <td style="text-align: center">30</td>
                            <!--                            <td style="text-align: center">31</td>
                                                        <td style="text-align: center">32</td>
                                                        <td style="text-align: center">33</td>
                                                        <td style="text-align: center">34</td>-->
                            <td style="text-align: center">35</td>

                        </tr>
                        <s:iterator value="#attr.lstDulieuNt50" var="modelView" status="rowstatus">                             
                            <tr>  
                                <s:if test="THUTU.equals(1)">
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="TT_HIENTHI" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                                    </td>
                                </s:if>
                                <s:else>
                                    <td></td>
                                </s:else>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D1" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();" 
                                           readonly="true"/>
                                    <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>" />
                                     <input type="hidden" value="<s:property  value="MAPGD" />" 
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>" />
                                </td>                                
                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D2" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();" 
                                           readonly="readonly"/>
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D3" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D4" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>   value="<s:property  value="D5" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH" onfocus="this.select();" 
                                           readonly="true"/>                                                                        
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D6" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH D0" onfocus="this.select();" 
                                           readonly="true"/>                                                                        
                                </td>

                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D7" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH D0" onfocus="this.select();" 
                                           readonly="true"/>                                                                        
                                </td>

<!--                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D20" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D20" class="TEN_KH D0" onfocus="this.select();" 
                                           readonly="true"/>                                                                        
                                </td>-->
                                <!--                                    <td align = "right" class="TD_TENKH" >
                                                                        <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D21" />"
                                                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D21" class="TEN_KH" onfocus="this.select();" 
                                                                               readonly="true"/>
                                                                    </td> -->
                                <!--                                    <td align = "right" class="TD_MAKH" >
                                                                        <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D8" />"
                                                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH D0" onfocus="this.select();" 
                                                                               readonly="true"/>                                                                        
                                                                    </td>
                                                                    <td align = "right" class="TD_MAKH" >
                                                                        <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D9" />"
                                                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH D0" onfocus="this.select();" 
                                                                               readonly="true"/>                                                                        
                                                                    </td> -->
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D10" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH" onfocus="this.select();" 
                                           readonly="true"/>                                                                        
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D11" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" onfocus="this.select();" 
                                           readonly="true"/>                                                                        
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D15" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D12" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D14" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D13" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td> 


                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D18" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D18" class="D18 TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>
<!--                                <td align = "right" class="TD_NGAY" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D16" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D16" class="TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>  -->
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D19" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D19" class="TEN_KH" onfocus="this.select();" 
                                           readonly="true"/>
                                </td>  
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D22" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D22" class="D13 TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D23" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D23" class="D13 TEN_KH number" onfocus="this.select();"
                                           readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D24" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D24" class="D13 TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D25" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D25" class="D13 TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D26" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D26" class="D13 TEN_KH" onfocus="this.select();" 
                                           readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_MAKH" >
                                    <s:if test="D45.equalsIgnoreCase('3')">
                                        <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D43" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D43" class="D13 TEN_KH D0" onfocus="this.select();" 
                                               style="background: #FFCDD2 !important;"/>
                                    </td> 
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D44" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D44" class="TEN_KH D0 datepicker" placeholder="dd/MM/yyyy"  
                                               style="background: #FFCDD2 !important;"/>
                                    </td> 
                                </s:if>    
                                <s:else>
                                <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D43" />"
                                       name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D43" class="D13 TEN_KH D0" onfocus="this.select();" 
                                       readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D44" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D44" class="TEN_KH D0 "  
                                           readonly="true"/>
                                </td> 
                            </s:else>     

                            <!------------->
                            <td align = "right" class="TD_MAKH" >
                                <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D27" />"
                                       name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D27" class="D13 TEN_KH" onfocus="this.select();" 
                                       readonly="true"/>
                            </td> 
                            <td align = "right" class="TD_MAKH" >
                                <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D28" />"
                                       name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D28" class="D13 TEN_KH number" onfocus="this.select();" 
                                       readonly="true"/>
                            </td> 
                            <td align = "right" class="TD_MAKH" >
                                <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D29" />"
                                       name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D29" class="D13 TEN_KH number" onfocus="this.select();" 
                                       readonly="true"/>
                            </td> 
                            <td align = "right" class="TD_MAKH" >
                                <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D30" />"
                                       name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D30" class="D13 TEN_KH number" onfocus="this.select();" 
                                       readonly="true"/>
                            </td> 
                            <!--                                    <td align = "right" class="TD_MAKH" >
                                                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D31" />"
                                                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D31" class="D13 TEN_KH number" onfocus="this.select();" 
                                                                            readonly="true"/>
                                                                </td> 
                                                                <td align = "right" class="TD_MAKH" >
                                                                    <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D32" />"
                                                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D32" class="D13 TEN_KH number" onfocus="this.select();" 
                                                                            readonly="true"/>
                                                                </td> 
                                                                <td align = "right" class="TD_MAKH" >
                                                                    <input type="text" <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if>  value="<s:property  value="D33" />"
                                                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D33" class="D13 TEN_KH number" onfocus="this.select();" 
                                                                            readonly="true"/>
                                                                </td> 
                                                                <td align = "right" class="TD_MAKH" >
                                                                    <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D34" />"
                                                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D34" class="D13 TEN_KH number" onfocus="this.select();" 
                                                                            readonly="true"/>
                                                                </td> -->
                            <td align = "right" class="TD_MAKH" >
                                <input type="text"  <s:if test="!KHOA.equalsIgnoreCase('QD23_001')">style="color: red"</s:if> value="<s:property  value="D35" />"
                                       name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D35" class="D13 TEN_KH" onfocus="this.select();" 
                                       readonly="true"/>
                            </td> 

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
