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
                    <table class="editDelete" >
                        <tr height="50px">                              
                            <!--<th rowspan="2" class="TD_MAKH">Mã doanh nghiệp</th>-->  
                            <th rowspan="2" class="TD_MAKH">Tỉnh</th>  
                            <!--<th rowspan="2" class="TD_SOTIEN">Huyện</th>-->  
                            <th rowspan="2" class="TD_TENKH">Tên doanh nghiệp</th>  
                            <!--<th rowspan="2" class="TD_MAKH">Mã số thuế</th>-->    
                            <th rowspan="2" class="TD_MAKH">CMND người đại diện</th>
                            <th rowspan="2" class="TD_TENKH">Tên người đại diện</th>
                            <!--<th rowspan="2" class="TD_NGAY">Ngày tiếp nhận hs</th>-->
                            <!--<th rowspan="2" class="TD_NGAY">Hình thức tiếp nhận</th>-->                            
                            <th rowspan="2" class="TD_MAKH">Giấy đề nghị</th>
                            <th rowspan="2" class="TD_NGAY">Ngày đề nghị</th>
                            <!--<th rowspan="2" class="TD_NGAY">Mức lương vùng</th>-->
                            <!--<th rowspan="2" class="TD_NGAY">Tháng vay</th>-->
                            <!--<th rowspan="2" class="TD_NGAY">Tổng số lao động được đề nghị vay để trả lương</th>-->    
                            <th rowspan="2" class="TD_SOTIEN">Số tiền đề nghị vay</th>
                            <th colspan="2">Trong đó:</th>                                                                                                             
                            <!--<th  rowspan="2" class="TD_MAKH">Ngày lương theo HĐ</th>-->    
                            <!--<th  rowspan="2" class="TD_CHITIEU">Ghi chú</th>-->      
                            <th colspan="5" class="TD_NGAY">Duyệt kế hoạch</th>
                            <th rowspan="2" class="TD_SOTIEN">Giao kế hoạch</th>
                        </tr>         
                        <tr>
                            <th class="TD_SOTIEN">Để trả lương ngừng việc</th>
                            <th class="TD_SOTIEN">Để trả lương khi phục hồi SXKD</th>
                            <th class="TD_MAKH">Tính chất nguồn vốn</th>
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
                            <td>2</td>
                            <!--<td>6</td>-->
                            <!--<td>7</td>-->
                            <td>4</td>
                            <td>5</td>
                            <!--<td>10</td>-->
                            <!--<td>11</td>-->
                            <!--<td>12</td>-->
                            <td>8</td>
                            <td>9</td>
                            <td>13</td>
                            <td>14</td>
                            <td>15</td>                            
                            <td>20</td>                            
                            <td>21</td>  
                            <td>22</td>
                            <td>23</td>
                            <td></td>
                            <td>25</td>
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>  
                                <s:if test="THUTU.equals(1)">
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D29" />"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D29" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                                    </td>
                                </s:if>
                                <s:else>
                                    <td></td>
                                </s:else>
<!--                                <td align = "right" class="TD_SOTIEN" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D30" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                                </td>        -->
                                <!--                                <td align = "right" class="TD_MAKH" >
                                                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D1" />"
                                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();"
                                                                           readonly="true"/>
                                                                </td>                                -->
                                <td align = "right" class="TD_TENKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                                    <input type="hidden" value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D3"/>"/> 
                                    <input type="hidden" value="<s:property  value="MAPGD" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>"/> 
                                </td>
                                <!--                                <td align = "right" class="TD_MAKH" >
                                                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D3" />"
                                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();"/>
                                                                </td>-->
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D4" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TENKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D5" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH" onfocus="this.select();" readonly="true"/>                                                                        
                                </td>
                                <!--                                <td align = "center" class="TD_NGAY">
                                                                    <input type="text" value="<s:property  value="D6" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH D0 datepicker" placeholder="dd/MM/yyyy" />
                                                                </td> 
                                
                                                                <td align = "left" class="TD_NGAY">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D7"
                                    name="lstDulieuNt[%{#rowstatus.index}].D7"
                                    list="lstHinhthucTNHS" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"                                    
                                    cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td>  -->
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D8" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D9" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
                                </td> 
                                <!--                                <td align = "center" class="TD_NGAY">
                                                                    <input type="text" value="<s:property  value="D9" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH D0 datepicker" placeholder="dd/MM/yyyy" /> 
                                                                </td>  -->
                                <!--                                <td align = "left" class="TD_NGAY">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D10"
                                    name="lstDulieuNt[%{#rowstatus.index}].D10"
                                    list="lstLuongVung" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"                                    
                                    cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td>  
                            <td align = "center" class="TD_NGAY">
                                <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0 datepicker_month" placeholder="MM/yyyy" />
                            </td> -->
                                <!--                                <td align = "right" class="TD_NGAY" >
                                                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D12" />"
                                                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number" onfocus="this.select();"/>
                                                                </td> -->
                                <td align = "right" class="TD_SOTIEN" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D13" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_SOTIEN" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D14" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 TEN_KH number" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_SOTIEN" >
                                    <input type="text"  value="<s:property  value="D15" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 TEN_KH number" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "left" class="TD_MAKH">                                        
                                    <s:select  
                                        id="lstDulieuNt[%{#rowstatus.index}].D20"
                                        name="lstDulieuNt[%{#rowstatus.index}].D20"
                                        list="lstTinhchatNV" 
                                        listKey="sKey"
                                        listValue="sDesc"
                                        headerKey="-1"
                                        headerValue="--- Chọn ---"                                    
                                        cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                    </s:select>
                                </td>    

                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D21" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" class="TEN_KH D0" onfocus="this.select();"/>
                                </td>
                                <td align = "center" class="TD_NGAY">
                                    <input type="text" value="<s:property  value="D22" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" class="TEN_KH D0 datepicker" placeholder="dd/MM/yyyy" />
                                </td>   
                                <td align = "right" class="TD_STT" >
                                    <input type="text"  value="<s:property  value="D23" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23" class="TEN_KH D0" onfocus="this.select();"/>
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D24" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_SOTIEN" >
                                    <input type="text"  value="<s:property  value="D25" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25" class="TEN_KH number" onfocus="this.select();"/>
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
