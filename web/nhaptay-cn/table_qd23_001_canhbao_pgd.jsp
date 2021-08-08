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
            function nhapDieuchinh(masothue, tendn) {
                try
                {
                    if (masothue.length < 3)
                    {
                        alert('Doanh nghiệp chưa có mã số thuế !');
                        return;
                    }
                    var pheduyet = 'N';
                    var ht1 = screen.height -200;
                    var wt1 = screen.width - 200;
                    var left1 = 50;//(screen.width / 2) - (wt1 / 2);
                    var top1 = 50;
                    var ngay_bc = $("#ngay_bc_DATE").val();
                    var khoa_cdtt = $("#khoa_cdtt").val();
                    var url = "loadDieuchinhKh.action?masothue=" + masothue + "&ngay_bc=" + ngay_bc + "&tendn=" + tendn;

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

            function updateDsNguoiLD_QD23(masothue, tendn) {
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
                    var url = "updateDsNguoiLD_QD23.action?masothue=" + masothue + "&ngay_bc=" + ngay_bc + "&tendn=" + tendn;

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
                CẢNH BÁO CHO VAY VỐN ĐỂ TRẢ LƯƠNG NGỪNG VIỆC
            </div>
            <s:hidden name="khoa_nhaptaycn"/>
            <!--                <div id="divDonvitinh">
                                Đơn vị tính: Đồng
                            </div>-->
            </br>
            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 1400px; max-height:45vh">
                    <table class="editDelete cls-table" >
                        <tr height="50px">      
                            <th rowspan="2" class="TD_MAKH">Mã doanh nghiệp</th>                           
                            <th rowspan="2" class="TD_TENKH">Tên doanh nghiệp</th>  
                            <th rowspan="2" class="TD_MAKH">Mã số thuế</th>    
                            <th rowspan="2" class="TD_MAKH">CMND người đại diện</th>
                            <th rowspan="2" class="TD_TENKH">Tên người đại diện</th>
                            <th rowspan="2" class="TD_TENKH">Số người lao động đủ điều kiện</th>
                            <th colspan="3">Số người lao động cảnh báo</th>                                                                    
                        </tr>         
                        <tr>
                            <th class="TD_MAKH">Số NLĐ vay "Vượt thời gian thụ hưởng"</th>
                            <th class="TD_MAKH">Số NLĐ vay "Vượt mức lương tối thiểu vùng"</th>
                            <th class="TD_MAKH">Số NLĐ vay "Vượt số tiền phải trả"</th>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>2</td>
                            <td>3</td>
                            <td>4</td>
                            <td>5</td>
                            <td>6</td>
                            <td>7</td>
                            <td>8</td>
                            <td>9</td>                            
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr> 
                                   
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
                                    <td align = "right" class="TD_MAKH" >
                                       <a href="javascript:updateDsNguoiLD_QD23('<s:property value="D3"/>','<s:property value='D20'/>')" class="SOKU linkKh D0">
                                            <s:property value='D20'/>
                                        </a>                                                                      
                                    </td>

                                    <td align = "right" class="TD_MAKH" >
                                        <a href="javascript:updateDsNguoiLD_QD23('<s:property value="D3"/>','<s:property value='D20'/>')" class="SOKU linkKh D0">
                                            <s:property value='D21'/>
                                        </a>                                                                       
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                       <a href="javascript:updateDsNguoiLD_QD23('<s:property value="D3"/>','<s:property value='D20'/>')" class="SOKU linkKh D0">
                                            <s:property value='D22'/>
                                        </a> 
                                    </td> 
                                     <td align = "right" class="TD_MAKH" >
                                       <a href="javascript:updateDsNguoiLD_QD23('<s:property value="D3"/>','<s:property value='D20'/>')" class="SOKU linkKh D0">
                                            <s:property value='D23'/>
                                        </a>                                                                       
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
