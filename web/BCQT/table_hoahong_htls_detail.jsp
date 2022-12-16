<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<s:head/>
<sj:head/>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
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
             #idTitle{
                font: 14px Arial, Helvetica, sans-serif;
                font-weight: bold;
                color: #0077b3;
                text-align: center;
             }
             
             
        </style>
        <script src="js/jquery.number.js"></script>        
        <script type="text/javascript" src="BCQT/javascript/jquery-ui.min.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
//                $(".datepicker_month").datepicker({dateFormat: 'mm/yy'});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
//                $('.number2').number(true, 2);
                $(".TD_STT").css({"width": "30px"});
                $(".TD_MAKH").css({"width": "70px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "130px"});
                $(".TD_SOKU").css({"width": "120px"});
                $(".TD_SOTIEN").css({"width": "90px"});
                $(".TD_CHITIEU").css({"width": "250px"});
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
            function closeSelf() {
                window.close();
                return true;
            }
            
             function autoEvaluate() {

                var arrCot = [".D13", ".D22", ".D23"]; //Luu cac cot cua du lieu can tinh toan
                for (var i = 0; i < 50; i++) {
                    //8=2+4-6
                    $(".D23").eq(i).val(parseFloat($(".D13").eq(i).val()) - parseFloat($(".D22").eq(i).val()));

                }
                //                              
            }
            
//            function autoPlus(idx) {
//                $('.number').number(true, 0);
//               $('.number2').number(true, 2);
//           }
//           autoPlus(11);

        </script>
        <!--<link href="css/css/style.css" rel="stylesheet" type="text/css"/>-->
        <!--<link href="css/css/style.css" rel="stylesheet" type="text/css"/>-->
        <link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
    </head>
        <s:form id="frmHoahongDetail" action="frmHoahongDetail" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>


            <div id="idTitle">
                THÔNG TIN CHI TIẾT HẠCH TOÁN HOA HỒNG BỔ SUNG MÓN VAY HTLS
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <!--<div style="overflow: scroll;overflow-x: scroll;height:450px; width: 99%">-->

            <table border="1" class="editDelete" id="tablehoahong" align="center">
                <tr>      
                    <!--<th rowspan="2" class="TD_STT">STT</th>-->                           
                    <th rowspan="2" class="TD_TENKH">Mã nhà đầu tư</th>  
                    <th rowspan="2" class="TD_TENKH">Tên nhà đầu tư</th>  
                    <th rowspan="2" class="TD_SOKU">Mã khoản vay</th>    
                    <th rowspan="2"  class="TD_TOTIEN">Tỷ lệ chi hoa hồng (%/tháng)</th>
                    <th rowspan="2" class="TD_TRANGTHAI">Số tiền chi hoa hồng bổ sung</th> 
                    <th colspan="3" class="TD_TOTIEN">Phân bổ theo cấp</th>  

                </tr>   
                <tr>
                    <th class="TD_TOTIEN">Tổ</th>  
                    <th class="TD_TOTIEN">Huyện</th>  
                    <th class="TD_TOTIEN">Tỉnh</th>  
                </tr>



                <s:iterator value="#attr.hoahongMaster" var="modelView" status="rowstatus">

                    <tr height="22">  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="investorCode" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].investorCode" class="D0"  readonly="readonly"/>
                        </td>  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="investorCode" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].investorCode" class="D0"  readonly="readonly"/>
                        </td>  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="loanId" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].loanId" class="D0"  readonly="readonly"/>
                        </td>  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="commisionRate" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].commisionRate" class="number"  />
                        </td>  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="commisionTotalAmount" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].commisionTotalAmount" class="number"  />
                        </td>  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="commisionGroupAmount" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].commisionGroupAmount" class="number"  />
                        </td>  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="commisionDistrictAmount" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].commisionDistrictAmount" class="number" />
                        </td>  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="commisionDistrictAmount" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].commisionDistrictAmount" class="number"  />
                        </td>  


                    </tr>


                </s:iterator>
            </table>

            </br>
            <table border="1" class="editDelete" id="tablehoahong" align="center">
                <tr>      
                    <!--<th rowspan="2" class="TD_STT">STT</th>-->                           
                    <th rowspan="1" class="TD_TENKH">Cấp</th>  
                    <th rowspan="1" class="TD_TENKH">Đối tượng chi</th>  
                    <th rowspan="1" class="TD_SOKU">Tỷ lệ hưởng (%)</th>    
                    <th rowspan="1"  class="TD_TOTIEN">Số tiền hưởng</th>
                    <th rowspan="1" class="TD_TRANGTHAI">Tài khoản nợ</th> 
                    <th rowspan="1"  class="TD_TOTIEN">Tài khoản có</th>   

                </tr>   


                <s:iterator value="#attr.lstHHDetail" var="modelView" status="rowstatus">

                    <tr height="22">  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="levelFlag" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].levelFlag" class="D0"  readonly="readonly"/>
                        </td>  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="benefitName" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].benefitName" class="D0"  readonly="readonly"/>
                        </td>  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="benefitRate" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].benefitRate" class="number2"  />
                        </td>  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="benefitAmount" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].benefitAmount" class="number"  />
                        </td>  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="creditAccount" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].creditAccount" class="D0"  />
                        </td>  
                        <td align = "right" class="TD_SOKU">
                            <input type="text" value="<s:property  value="debitAccount" />" 
                                   name="lstHHDetail[<s:property  value="%{#rowstatus.index}" />].debitAccount" class="D0" />
                        </td>  


                    </tr>


                </s:iterator>


            </table>

            </br>

            <!--</div>-->

            <table  border="0px !important;" class="editDelete" align="center"> 
                <tr align="center" height="3px">                                    
                </tr>
                <tr align="center">                                       

                    <td width="35%">
                        <div id="content_div"></div>
                    </td>   

                    <td  align="center">
                        <div id="button_div" <s:property value="disabled" /> >
                            <s:url id="edit_url" action="saveHoahongDetail" escapeAmp="false"
                                   var="update_url">
                                <s:param name="proc">update</s:param>  
                            </s:url>                        
                            <sj:a id="update_button_id"  href="%{#update_url}" 
                                  targets="luu_thanhcong"
                                  formIds="frmHoahongDetail"    
                                  onBeforeTopics="before-next"
                                  button="false"
                                  cssClass="metroButtonStyle"   
                                  >     
                                Cập nhật
                            </sj:a>
                        </div>
                    </td>                                     


                    <td align="center">
                        <sj:submit id="idClose" cssClass="metroButtonStyle"  name="nameClose" value="Thoát" onclick="closeSelf()"
                                   cssStyle="height:31px;width:95px"></sj:submit>
                        </td>

                        <td width="35%"></td>                                    


                    </tr>
                </table>  
        </s:form>
        <div id="luu_thanhcong"></div>

    </body>
</html>
