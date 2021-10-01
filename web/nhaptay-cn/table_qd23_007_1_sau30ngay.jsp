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
                    var url = "loadDieuchinhKh.action?masothue=" + masothue + "&ngay_bc=" + ngay_bc + "&tendn=" + tendn + "&thangbc=" + thangbc;

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
                KẾT QUẢ KIỂM TRA TRỰC TIẾP VIỆC SỬ DỤNG VỐN VAY CỦA KHÁCH HÀNG
                </br>
                <span style="color:red">(Sau 30 ngày)</span>
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nhaptaycn"/>
            <%--<s:hidden name="khoa_nhaptaycn"/>--%>
                            <div id="divDonvitinh">
                                Đơn vị tính: NLĐ, đồng
                            </div>
            </br>
            <div class="cls-over">
                <div id="scrolling_table_1"  style="width: 99%; max-height:45vh">
                    <table class="editDelete cls-table" >
                        <tr height="50px">      
                            <!--<th  class="TD_MAKH"></th>-->   
                            <th rowspan="3"  class="TD_MAKH">Mã doanh nghiệp</th>                           
                            <th rowspan="3" class="TD_CHITIEU">Tên doanh nghiệp</th>  
                            <th rowspan="3" class="TD_MAKH">Mã số thuế </th>  
                            <th rowspan="3" class="TD_NGAY">Số NLĐ</th>
                            <th rowspan="3" class="TD_NGAY">Số tiền</th>                            
                            <th rowspan="3" class="TD_NGAY">Ngày giải ngân</th> 
                            <th rowspan="3" class="TD_NGAY">Ngày kiểm tra<span style="color:red">*</span></th>
                            <th colspan="6" class="TD_TENKH">Kết quả kiểm tra<span style="color:red">*</span></th>
                              
                        </tr>   
                         <tr>
                             <th colspan="2"  class="TD_NGAY">Do khách quan - Sử dụng vốn đúng mục đích</th>
                            <th  colspan="2" class="TD_NGAY">Do chủ quan - Thông báo thu hồi nợ trước hạn</th> 
                            <th  colspan="2" class="TD_NGAY">Do khách quan - Chứng từ chưa đầy đủ - Kiểm tra lại</th>
                            
                        </tr>
                        <tr>
                            <th  class="TD_NGAY">Số NLĐ</th>
                            <th  class="TD_NGAY">Số tiền</th> 
                            <th  class="TD_NGAY">Số NLĐ</th>
                            <th  class="TD_NGAY">Số tiền</th> 
                            <th  class="TD_NGAY">Số NLĐ</th>
                            <th  class="TD_NGAY">Số tiền</th> 
                        </tr>

                        <tr>
                            <!--<td></td>-->                            
                            <!--<td style="text-align: center"></td>--> 
                            <!--<td style="text-align: center">1</td>-->
                            <td style="text-align: center">2</td>
                            <td style="text-align: center">3</td>
                            <td style="text-align: center">4</td>
                            
                            <td style="text-align: center">7</td>                              
                            <td style="text-align: center">8</td>   
                            <td style="text-align: center">9</td> 
                            <td style="text-align: center">5</td>
                            <!--<td style="text-align: center">6</td>-->
                            <td style="text-align: center">10</td>
                            <td style="text-align: center">11</td>                              
                            <td style="text-align: center">12</td>   
                            <td style="text-align: center">13</td> 
                            <td style="text-align: center">14</td> 
                            <td style="text-align: center">15</td> 
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt50" var="modelView" status="rowstatus">                             
                            <tr> 
                                <s:if test="NHAPTAY.equalsIgnoreCase(1)">                                     
                                    <!--<td></td>-->

                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D1" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0" onfocus="this.select();" style="background: #C0C0C0 !important;"
                                               readonly="true"/>
                                        <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>" />
                                         <input type="hidden" value="<s:property  value="D1" />" 
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1"/>" />
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
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D5" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>
                                    <td align = "right" class="TD_NGAY" >
                                        <input type="text"   value="<s:property  value="D6" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>

                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D7" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>                                                                        
                                    </td>
                                </s:if>
                                <!--Doanh nghiệp chưa duyệt-->
                                <s:else>                                    
                                    <!--<td></td>-->
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D2" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0" onfocus="this.select();"
                                               style="background:#C0C0C0 !important;" readonly="true"/>
                                        <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>" />
                                        <input type="hidden" value="<s:property  value="D1" />" 
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1"/>" />
                                    </td>                                
                                   
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D3" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/> 
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D4" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>                                                                                                          
                                    
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D7" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                               readonly="true"/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D8" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH number" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                              readonly="true"/>
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D9" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH D0" onfocus="this.select();" style="background:#C0C0C0 !important;"
                                              readonly="true"/>
                                    </td>
                                    <td align = "center" class="TD_MAKH">
                                        <input type="text" value="<s:property  value="D5" />" 
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH D0 datepicker" placeholder="dd/MM/yyyy" />
                                    </td> 
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D10" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH number" onfocus="this.select();" 
                                               />
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D11" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH number" onfocus="this.select();" 
                                              />
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D12" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number" onfocus="this.select();" 
                                               />
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D13" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH number" onfocus="this.select();" 
                                               />
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D14" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D14" class="TEN_KH number" onfocus="this.select();" 
                                              />
                                    </td>
                                    <td align = "right" class="TD_MAKH" >
                                        <input type="text"   value="<s:property  value="D15" />"
                                               name="lstDulieuNt50[<s:property  value="%{#rowstatus.index}" />].D15" class="TEN_KH number" onfocus="this.select();" 
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
