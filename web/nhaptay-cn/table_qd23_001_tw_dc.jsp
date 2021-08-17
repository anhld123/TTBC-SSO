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
                $(".TD_CHITIEU").css({"width": "200px"});
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
                PHÊ DUYỆT ĐIỀU CHỈNH KẾ HOẠCH
            </div>
            <s:hidden name="khoa_nhaptaycn"/>
            <!--                <div id="divDonvitinh">
                                Đơn vị tính: Đồng
                            </div>-->
            </br>
            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 1500px; max-height:20vh">
                    <table class="editDelete cls-table" >
                        <tr height="50px">      
                            <th rowspan="2" class="TD_MAKH">Tỉnh</th>  
                            <!--<th rowspan="2" class="TD_SOTIEN">Huyện</th>--> 
                            <th  rowspan="2"  class="TD_MAKH">Mã số thuế doanh nghiệp</th>                           
                            <th rowspan="2"  class="TD_TENKH">Tên doanh nghiệp</th>
                            <th rowspan="2"  class="TD_MAKH">Tháng đề nghị</th>
                            <th rowspan="2"  class="TD_MAKH">Giấy đề nghị</th>
                            <th rowspan="2" class="TD_MAKH">Ngày đề nghị</th>
                            <th rowspan="2"  class="TD_STT">Lần điều chỉnh</th>
                            <!--<th  class="TD_NGAY">Tổng số lao động được đề nghị vay để trả lương</th>-->    
                            <th rowspan="2" class="TD_MAKH">Tổng số tiền được phê duyệt cho vay</th>
                            <th rowspan="2" class="TD_MAKH">Số tiền đã giải ngân</th>
                            <th  rowspan="2" class="TD_MAKH">Số tiền tồn không giải ngân hết</th>
                            <th  rowspan="2" class="TD_CHITIEU">Nguyên nhân không giải ngân hết</th>
                            <th  colspan="5" class="TD_CHITIEU">Duyệt kế hoạch</th>
                        </tr>         
                        <tr>
                            <th class="TD_MAKH">Duyệt</th>
                            <th class="TD_MAKH">Số QĐ</th>
                            <th class="TD_MAKH">Ngày QĐ</th>
                            <th class="TD_STT">Thông báo lần</th>
                            <th class="TD_MAKH">TT duyệt</th>
                        </tr>
                        <tr>
                            <!--<td>1</td>-->
                            <td></td>
                            <!--<td>3</td>-->
                            <!--<td></td>-->
                            <td>3</td>
                            <td>2</td>
                            <td>11</td>
                            <td>8</td>
                            <td>9</td>
                            <td>5</td>
                            <td>13</td>
                            <td>22</td>                            
                            <!--<td>9</td>-->                            
                            <td>23</td>  
                            <td>24</td>
                            <td>38</td>
                            <td>39</td>
                            <td>40</td>
                            <td>41</td>
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt50" var="modelView" status="rowstatus">                             
                            <tr>  
                                <s:if test="THUTU.equals(1)">
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D29" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D29" class="TEN_KH" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>
                                </s:if>
                                <s:else>
                                    <td></td>
                                </s:else>

                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D3" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH" onfocus="this.select();"style="background:#C0C0C0 !important;"
                                           readonly="true"/>
                                    <input type="hidden" value="<s:property  value="D45" />"
                                       name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D45" value="<s:property  value="D45"/>"/> 
                                    <input type="hidden" value="<s:property  value="MAPGD" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>"/> 
                                </td>
                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"   value="<s:property  value="D2" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                           readonly="true"/>
                                </td>

                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D11" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"   value="<s:property  value="D8" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                           readonly="true"/>                                                                        
                                </td>
                                
                                <td align = "right" class="TD_STT" >
                                    <input type="text"   value="<s:property  value="D9" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                           readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_SOTIEN" >
                                    <input type="text"   value="<s:property  value="D5" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                           readonly="true"/>
                                </td> 
                                
                                <td align = "right" class="TD_SOTIEN" >
                                    <input type="text"   value="<s:property  value="D13" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                           readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_SOTIEN" >
                                    <input type="text"   value="<s:property  value="D22" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D22" class="D14 TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_SOTIEN" >
                                    <input type="text"  value="<s:property  value="D23" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D23" class="D15 TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_SOTIEN" >
                                    <input type="text"  value="<s:property  value="D24" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D24" class="D15 TEN_KH" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                           readonly="true"/>
                                </td>
                                 <td align = "left" class="TD_MAKH">                                        
                                    <s:select  
                                        id="lstDulieuNt50[%{#rowstatus.index}].D36"
                                        name="lstDulieuNt50[%{#rowstatus.index}].D36"
                                        list="lstTinhchatNV" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        headerKey="-1"
                                        headerValue="--- Chọn ---"                                    
                                        cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                    </s:select>
                                </td>    

                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D37" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D37" class="TEN_KH D0" onfocus="this.select();"/>
                                </td>
                                <td align = "center" class="TD_NGAY">
                                    <input type="text" value="<s:property  value="D38" />" 
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D38" class="TEN_KH D0 datepicker" placeholder="dd/MM/yyyy" />
                                </td>   
                                <td align = "right" class="TD_STT" >
                                    <input type="text"  value="<s:property  value="D39" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D39" class="TEN_KH D0" onfocus="this.select();"/>
                                </td>
                                <td align = "right" class="TD_STT" >
                                    <input type="text"  value="<s:property  value="D40" />"
                                           name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D40" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
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
