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
                <div id="scrolling_table_1"  style="width: 1700px; max-height:20vh">
                    <table class="editDelete cls-table" >
                        <tr height="50px">                                                          
                            <th rowspan="2" class="TD_MAKH">PGD</th>  
                            <th rowspan="2" class="TD_MAKH">Mã doanh nghiệp</th>                           
                            <th rowspan="2" class="TD_TENKH">Tên doanh nghiệp</th>  
                            <th rowspan="2" class="TD_MAKH">Mã số thuế</th>    
                            <th rowspan="2" class="TD_MAKH">CMND người đại diện</th>
                            <th rowspan="2" class="TD_TENKH">Tên người đại diện</th>
                            <th rowspan="2" class="TD_NGAY">Ngày tiếp nhận hs</th>
                            <th rowspan="2" class="TD_NGAY">Hình thức tiếp nhận</th>                            
                            <th rowspan="2" class="TD_MAKH">Giấy đề nghị</th>
                            <th rowspan="2" class="TD_NGAY">Ngày đề nghị</th>
                            <th rowspan="2" class="TD_NGAY">Mức lương vùng</th>
                            <!--<th rowspan="2" class="TD_NGAY">Tháng vay</th>-->
                            <!--<th rowspan="2" class="TD_NGAY">Tổng số lao động được đề nghị vay để trả lương</th>-->    
                            <th rowspan="2" class="TD_MAKH">Số tiền đề nghị vay</th>
                            <th colspan="2">Trong đó:</th>                                                                                                             
                            <th  rowspan="2" class="TD_STT">Ngày lương theo HĐ</th>    
                            <th  rowspan="2" class="TD_TENKH">Ghi chú</th> 
                        </tr>         
                        <tr>
                             <th class="TD_MAKH">Để trả lương ngừng việc</th>
                            <th class="TD_MAKH">Để trả lương khi phục hồi SXKD</th>
                        </tr>
                        <tr>
                            <td></td> 
                            <td style="text-align: center">1</td>
                            <td style="text-align: center">2</td>
                            <td style="text-align: center">3</td>
                            <td style="text-align: center">4</td>
                            <td style="text-align: center">5</td>
                            <td style="text-align: center">6</td>
                            <td style="text-align: center">7</td>
                            <td style="text-align: center">8</td>
                            <td style="text-align: center">9</td>
                            <td style="text-align: center">10</td>
                            <td v>11</td>
                            <td style="text-align: center">12</td>
                            <td style="text-align: center">13</td>
                            <td style="text-align: center">14</td>
                            <td style="text-align: center">15</td>
<!--                            <td>16</td>
                            <td>17</td>    -->
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>  
                                <s:if test="THUTU.equals(1)">
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D30" />"
                                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                                    </td>
                                </s:if>
                                <s:else>
                                    <td></td>
                                </s:else>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D1" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>                                
                                <td align = "right" class="TD_TENKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D3" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D4" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TENKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D5" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH" onfocus="this.select();" readonly="true"/>                                                                        
                                </td>
                                <td align = "center" class="TD_NGAY">
                                    <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH D0" readonly="true" />
                                </td> 

                                <td align = "center" class="TD_NGAY">
                                    <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH D0"  readonly="true"/>
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D8" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
                                </td> 
                                <td align = "center" class="TD_NGAY">
                                    <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH D0"  readonly="true"/>
                                </td>  
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D10" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH" onfocus="this.select();" readonly="true"/>
                                </td>
<!--                                <td align = "center" class="TD_NGAY">
                                    <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0 " placeholder="MM/yyyy" readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_NGAY" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D12" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number" onfocus="this.select();"/>
                                </td> -->
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D13" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 TEN_KH number" onfocus="this.select();" 
                                           readonly="true"/>
                                </td> 
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if>  value="<s:property  value="D14" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 TEN_KH number" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text"  value="<s:property  value="D15" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 TEN_KH number" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_STT" >
                                    <input type="text"  value="<s:property  value="D16" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="TEN_KH D0" onfocus="this.select();" readonly="true"/>
                                </td> 

                                <td align = "right" class="TD_TENKH" >
                                    <input type="text"  value="<s:property  value="D17" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="TEN_KH" onfocus="this.select();" readonly="true"/>
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
