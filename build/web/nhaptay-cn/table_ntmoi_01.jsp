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
        <style>
        a {
            color: #0000FF;
        }
        .BOLD
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }
        </style>
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "150px"});
                $(".TD_CAPKT").css({"width": "100px"});
                $(".TD_SOTIEN").css({"width": "55px"});                                
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            
            $("#allCheck_1").change(function () {
                $(".checkbox1").prop('checked', $(this).prop("checked"));
            });
            
            $("#allCheck_2").change(function () {
                $(".checkbox2").prop('checked', $(this).prop("checked"));
            });
        </script>
        
                <script>
                function autoEvaluate(){
    //                alert('vao doClick');
                    var arrCot = [".D2",".D3",".D4",".D5",".D6",".D7",".D8",".D9",".D10",".D11",".D12",".D13",".D14",".D15"]; //Luu cac cot cua du lieu can tinh toan                    
    //                  Tinh toan cho 7 dong
                    for(var i=0; i<30; i++){                        
                        //8=2+4-6
                        $(".D15").eq(i).val(parseFloat($(".D2").eq(i).val()) + parseFloat($(".D3").eq(i).val()) +
                                            parseFloat($(".D4").eq(i).val()) + parseFloat($(".D5").eq(i).val()) +
                                            parseFloat($(".D6").eq(i).val()) + parseFloat($(".D7").eq(i).val()) +   
                                            parseFloat($(".D8").eq(i).val()) + parseFloat($(".D9").eq(i).val()) +
                                            parseFloat($(".D10").eq(i).val()) + parseFloat($(".D11").eq(i).val()) +
                                            parseFloat($(".D12").eq(i).val()) + parseFloat($(".D13").eq(i).val()) +  
                                            parseFloat($(".D14").eq(i).val())        
                        );

                    }
    //                              
                }
                

            function changeCheck2(b0,b1,b2,b3) {
                if(b3 == '1')  //Cho lần nhật đầu tiên 31/10/2020
                {
                    document.getElementById("idc11"+b1).checked = false;
                    document.getElementById("idc11"+b1).disabled = true;
                    document.getElementById("idc12"+b1).checked = false;
                    document.getElementById("idc12"+b1).disabled = true;
                    
//                    document.getElementById("idc18"+b1).checked = false;
//                    document.getElementById("idc18"+b1).disabled = true;
//                    document.getElementById("idc19"+b1).checked = false;
//                    document.getElementById("idc19"+b1).disabled = true;
                    switch(b0) {
                        case 13:
                        if (b2.checked) {
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc17"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc19"+b1).checked = false;
                          } else {
                          }
                      break;  
                     case 14:
                        if (b2.checked) {
                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc17"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc19"+b1).checked = false;
                          } else {
                          }
                      break;  
                      case 15:
                        if (b2.checked) {
                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc17"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc19"+b1).checked = false;
                          } else {
                          }
                      break;  
                      case 16:
                        if (b2.checked) {
                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc17"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc19"+b1).checked = false;
                          } else {
                          }
                      break;  
                      case 17:
                        if (b2.checked) {
                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc19"+b1).checked = false;
                          } else {
                          }
                      break;                       
                      case 18:
                        if (b2.checked) {                            
                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc17"+b1).checked = false;
                            document.getElementById("idc19"+b1).checked = false;
                            
//                            document.getElementById("idc13"+b1).checked = false;
//                            document.getElementById("idc13"+b1).disabled = true;
//                            document.getElementById("idc14"+b1).checked = false;
//                            document.getElementById("idc14"+b1).disabled = true;
//                            document.getElementById("idc15"+b1).checked = false;
//                            document.getElementById("idc15"+b1).disabled = true;
//                            document.getElementById("idc16"+b1).checked = false;
//                            document.getElementById("idc16"+b1).disabled = true;
//                            document.getElementById("idc17"+b1).checked = false;
//                            document.getElementById("idc17"+b1).disabled = true;
                          } else {
                          }
                      break;  
                      case 19:
                        if (b2.checked) {                            
                            
                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            
//                            document.getElementById("idc13"+b1).checked = false;
//                            document.getElementById("idc13"+b1).disabled = true;
//                            document.getElementById("idc14"+b1).checked = false;
//                            document.getElementById("idc14"+b1).disabled = true;
//                            document.getElementById("idc15"+b1).checked = false;
//                            document.getElementById("idc15"+b1).disabled = true;
//                            document.getElementById("idc16"+b1).checked = false;
//                            document.getElementById("idc16"+b1).disabled = true;
//                            document.getElementById("idc17"+b1).checked = false;
//                            document.getElementById("idc17"+b1).disabled = true;
                          } else {
                          }
                      break;  
                      
                      default:
                      // code block
                    }
                }
                else  //Cho lần khác 31/10/2020
                {
                    switch(b0) {
                    case 11:
                        if (b2.checked) {
                            document.getElementById("idc12"+b1).checked = false;
    //                        document.getElementById("idc12"+b1).disabled = true;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc18"+b1).disabled = true;
                            document.getElementById("idc19"+b1).checked = false;
                            document.getElementById("idc19"+b1).disabled = true;

                            document.getElementById("idc13"+b1).disabled = false;
                            document.getElementById("idc14"+b1).disabled = false;
                            document.getElementById("idc15"+b1).disabled = false;
                            document.getElementById("idc16"+b1).disabled = false;
                            document.getElementById("idc17"+b1).disabled = false;
                          } else {
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc18"+b1).disabled = false;
                            document.getElementById("idc19"+b1).checked = false;
                            document.getElementById("idc19"+b1).disabled = false;  
                          }
                      break;
                    case 12:
                        if (b2.checked) {
                            document.getElementById("idc11"+b1).checked = false;

                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc13"+b1).disabled = true;
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc14"+b1).disabled = true;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc15"+b1).disabled = true;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc16"+b1).disabled = true;
                            document.getElementById("idc17"+b1).checked = false;
                            document.getElementById("idc17"+b1).disabled = true;

    //                        document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc18"+b1).disabled = false;
    //                        document.getElementById("idc19"+b1).checked = false;
                            document.getElementById("idc19"+b1).disabled = false;
                          } else {
                                document.getElementById("idc13"+b1).checked = false;
                                document.getElementById("idc13"+b1).disabled = false;
                                document.getElementById("idc14"+b1).checked = false;
                                document.getElementById("idc14"+b1).disabled = false;
                                document.getElementById("idc15"+b1).checked = false;
                                document.getElementById("idc15"+b1).disabled = false;
                                document.getElementById("idc16"+b1).checked = false;
                                document.getElementById("idc16"+b1).disabled = false;
                                document.getElementById("idc17"+b1).checked = false;
                                document.getElementById("idc17"+b1).disabled = false;
                          }
                      break;
                    case 13:
                        if (b2.checked) {
//                            document.getElementById("idc11"+b1).checked = true;
                            
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc17"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc19"+b1).checked = false;
                            
//                            document.getElementById("idc18"+b1).checked = false;
//                            document.getElementById("idc18"+b1).disabled = true;
//                            document.getElementById("idc19"+b1).checked = false;
//                            document.getElementById("idc19"+b1).disabled = true;
                            
                          } else {
                          }
                      break;  
                     case 14:
                        if (b2.checked) {
//                            document.getElementById("idc11"+b1).checked = true;
                            
                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc17"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc19"+b1).checked = false;
                            
//                            document.getElementById("idc18"+b1).checked = false;
//                            document.getElementById("idc18"+b1).disabled = true;
//                            document.getElementById("idc19"+b1).checked = false;
//                            document.getElementById("idc19"+b1).disabled = true;
                          } else {
                          }
                      break;  
                      case 15:
                        if (b2.checked) {
//                            document.getElementById("idc11"+b1).checked = true;
                            
                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc17"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc19"+b1).checked = false;
                            
//                            document.getElementById("idc18"+b1).checked = false;
//                            document.getElementById("idc18"+b1).disabled = true;
//                            document.getElementById("idc19"+b1).checked = false;
//                            document.getElementById("idc19"+b1).disabled = true;
                          } else {
                          }
                      break;  
                      case 16:
                        if (b2.checked) {
//                            document.getElementById("idc11"+b1).checked = true;
                            
                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc17"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc19"+b1).checked = false;
                            
//                            document.getElementById("idc18"+b1).checked = false;
//                            document.getElementById("idc18"+b1).disabled = true;
//                            document.getElementById("idc19"+b1).checked = false;
//                            document.getElementById("idc19"+b1).disabled = true;
                          } else {
                          }
                      break;  
                      case 17:
                        if (b2.checked) {
//                            document.getElementById("idc11"+b1).checked = true;
                            
                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc19"+b1).checked = false;
                            
//                            document.getElementById("idc18"+b1).checked = false;
//                            document.getElementById("idc18"+b1).disabled = true;
//                            document.getElementById("idc19"+b1).checked = false;
//                            document.getElementById("idc19"+b1).disabled = true;
                          } else {
                          }
                      break;  
                      case 18:
                        if (b2.checked) {
//                            document.getElementById("idc12"+b1).checked = true;
                            
                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc17"+b1).checked = false;
                            document.getElementById("idc19"+b1).checked = false;
                            
//                            document.getElementById("idc13"+b1).checked = false;
//                            document.getElementById("idc13"+b1).disabled = true;
//                            document.getElementById("idc14"+b1).checked = false;
//                            document.getElementById("idc14"+b1).disabled = true;
//                            document.getElementById("idc15"+b1).checked = false;
//                            document.getElementById("idc15"+b1).disabled = true;
//                            document.getElementById("idc16"+b1).checked = false;
//                            document.getElementById("idc16"+b1).disabled = true;
//                            document.getElementById("idc17"+b1).checked = false;
//                            document.getElementById("idc17"+b1).disabled = true;
                          } else {
                          }
                      break;  
                      case 19:
                        if (b2.checked) {
//                            document.getElementById("idc12"+b1).checked = true;
                            
                            document.getElementById("idc13"+b1).checked = false;
                            document.getElementById("idc14"+b1).checked = false;
                            document.getElementById("idc15"+b1).checked = false;
                            document.getElementById("idc16"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            document.getElementById("idc18"+b1).checked = false;
                            
//                            document.getElementById("idc13"+b1).checked = false;
//                            document.getElementById("idc13"+b1).disabled = true;
//                            document.getElementById("idc14"+b1).checked = false;
//                            document.getElementById("idc14"+b1).disabled = true;
//                            document.getElementById("idc15"+b1).checked = false;
//                            document.getElementById("idc15"+b1).disabled = true;
//                            document.getElementById("idc16"+b1).checked = false;
//                            document.getElementById("idc16"+b1).disabled = true;
//                            document.getElementById("idc17"+b1).checked = false;
//                            document.getElementById("idc17"+b1).disabled = true;
                          } else {
                          }
                      break;  
                    default:
                      // code block
                  }
              }
                
              }
              
              
              function initTable()
            {
                var table = document.getElementById("tablentmoi001");
                var rowcount = table.rows.length;    
                rowcount = rowcount > max_row ? rowcount : max_row;                
                for (var i = 0; i < rowcount; i++)
                {    
                    //cho combox 1
                    var matmp1 = getMabyNumber1(i);//                       
                    if(matmp1 == 1)
                    {
                        $('input:checkbox[id=idc11'+i+']').attr('checked',true);
                    }
                    //cho combox 2
                    var matmp2 = getMabyNumber2(i);//                       
                    if(matmp2 == 1)
                    {
                        $('input:checkbox[id=idc12'+i+']').attr('checked',true);
                    }
                    //cho combox 3
                    var matmp3 = getMabyNumber3(i);//                       
                    if(matmp3 == 1)
                    {
                        $('input:checkbox[id=idc13'+i+']').attr('checked',true);
                    }
                    //cho combox 4
                    var matmp4 = getMabyNumber4(i);//                       
                    if(matmp4 == 1)
                    {
                        $('input:checkbox[id=idc14'+i+']').attr('checked',true);
                    }
                    //cho combox 5
                    var matmp5 = getMabyNumber5(i);//                       
                    if(matmp5 == 1)
                    {
                        $('input:checkbox[id=idc15'+i+']').attr('checked',true);
                    }
                    //cho combox 6
                    var matmp6 = getMabyNumber6(i);//                       
                    if(matmp6 == 1)
                    {
                        $('input:checkbox[id=idc16'+i+']').attr('checked',true);
                    }
                    //cho combox 7
                    var matmp7 = getMabyNumber7(i);//                       
                    if(matmp7 == 1)
                    {
                        $('input:checkbox[id=idc17'+i+']').attr('checked',true);
                    }
                    //cho combox 8
                    var matmp8 = getMabyNumber8(i);//                       
                    if(matmp8 == 1)
                    {
                        $('input:checkbox[id=idc18'+i+']').attr('checked',true);
                    }
                    //cho combox 9
                    var matmp9 = getMabyNumber9(i);//                       
                    if(matmp9 == 1)
                    {
                        $('input:checkbox[id=idc19'+i+']').attr('checked',true);
                    }
                    //cho combox 10
                    
                    var matmp10 = getMabyNumber10(i);//                       
                    if(matmp10 == 1)
                    {
                        $('input:checkbox[id=idc20'+i+']').attr('checked',true);
                    }
                    
                    var matmp14 = getMabyNumber14(i);//                       
                    if(matmp14 == 1)
                    {
                        $('input:checkbox[id=idc4'+i+']').attr('checked',true);
                    }
                    
                }
            }
            
                function getMabyNumber14(idx)
                {
                    var ma = '';
                    try {
                        var ma_id = 'id4_' + idx;
                        ma = document.getElementById(ma_id).value;
                    } catch (e)
                    {
                        ma = '999999';
                    }
                    return ma;
                }
            
                function getMabyNumber1(idx)
                {
                    var ma = '';
                    try {
                        var ma_id = 'id11_' + idx;
                        ma = document.getElementById(ma_id).value;
                    } catch (e)
                    {
                        ma = '999999';
                    }
                    return ma;
                }
                
                function getMabyNumber2(idx)
                {
                    var ma = '';
                    try {
                        var ma_id = 'id12_' + idx;
                        ma = document.getElementById(ma_id).value;
                    } catch (e)
                    {
                        ma = '999999';
                    }
                    return ma;
                }
                
                function getMabyNumber3(idx)
                {
                    var ma = '';
                    try {
                        var ma_id = 'id13_' + idx;
                        ma = document.getElementById(ma_id).value;
                    } catch (e)
                    {
                        ma = '999999';
                    }
                    return ma;
                }
                
                function getMabyNumber4(idx)
                {
                    var ma = '';
                    try {
                        var ma_id = 'id14_' + idx;
                        ma = document.getElementById(ma_id).value;
                    } catch (e)
                    {
                        ma = '999999';
                    }
                    return ma;
                }
                
                function getMabyNumber5(idx)
                {
                    var ma = '';
                    try {
                        var ma_id = 'id15_' + idx;
                        ma = document.getElementById(ma_id).value;
                    } catch (e)
                    {
                        ma = '999999';
                    }
                    return ma;
                }
                
                function getMabyNumber6(idx)
                {
                    var ma = '';
                    try {
                        var ma_id = 'id16_' + idx;
                        ma = document.getElementById(ma_id).value;
                    } catch (e)
                    {
                        ma = '999999';
                    }
                    return ma;
                }
                
                function getMabyNumber7(idx)
                {
                    var ma = '';
                    try {
                        var ma_id = 'id17_' + idx;
                        ma = document.getElementById(ma_id).value;
                    } catch (e)
                    {
                        ma = '999999';
                    }
                    return ma;
                }
                
                function getMabyNumber8(idx)
                {
                    var ma = '';
                    try {
                        var ma_id = 'id18_' + idx;
                        ma = document.getElementById(ma_id).value;
                    } catch (e)
                    {
                        ma = '999999';
                    }
                    return ma;
                }
                
                function getMabyNumber9(idx)
                {
                    var ma = '';
                    try {
                        var ma_id = 'id19_' + idx;
                        ma = document.getElementById(ma_id).value;
                    } catch (e)
                    {
                        ma = '999999';
                    }
                    return ma;
                }
                
                function getMabyNumber10(idx)
                {
                    var ma = '';
                    try {
                        var ma_id = 'id20_' + idx;
                        ma = document.getElementById(ma_id).value;
                    } catch (e)
                    {
                        ma = '999999';
                    }
                    return ma;
                }
                </script>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">              
        <s:if test="Grade.equalsIgnoreCase('1')"> 
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                THÔNG TIN XÃ NÔNG THÔN MỚI VÀ XÃ VAY CHƯƠNG TRÌNH CHO VAY VÙNG KHÓ KHĂN
            </div>
                &nbsp;
            <s:hidden name="khoa_nhaptaycn"/>
<!--            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>-->
            <table border="1" class="editDelete" id="tablentmoi001" style="width: 95%" align="center">
                <tr height="35">
                    <th rowspan="3" class="TD_THUTU">TT</th>                    
                    <th rowspan="3"  class="TD_CHITIEU">Tên xã</th>                                            
                    <th rowspan="3" class="TD_SOTIEN">Số hộ thoát nghèo</th>                       
                    <th rowspan="3" class="TD_SOTIEN">Hoàn thành 19CT</th>
                    <th colspan="2"  class="TD_SOTIEN">Xã VKK phát sinh tăng trong tháng</th>
                    <th colspan="2" class="TD_SOTIEN">Xã VKK phát sinh giảm trong tháng</th>
                    <th colspan="5" class="TD_SOTIEN">Xã thuộc VKK có dư nợ tại NHCSXH </th>
                    <th colspan="2" class="TD_SOTIEN">Xã không còn thuộc VKK nhưng còn dư nợ</th>                                        
                </tr>
                
                <tr height="35">
                    <th rowspan="2"  class="TD_SOTIEN"></th>                                            
                    <th rowspan="2" class="TD_CHITIEU">Ghi chú (xã được bổ sung theo QĐ hoặc Xã đã thuộc VKK nhưng tháng này mới phát sinh cho vay...)</th>                       
                    
                    <th rowspan="2" class="TD_SOTIEN"></th>
                    <th rowspan="2" class="TD_CHITIEU">Ghi chú (xã  bị loại khỏi VKK theo QĐ...)</th>
                    
                    <th colspan="2" class="TD_SOTIEN">Xã thuộc QĐ 582/QĐ/TTg</th>
                    
                    <th rowspan="2" class="TD_SOTIEN">Xã thuộc QĐ 900</th>
                    <th rowspan="2" class="TD_SOTIEN">Xã thuộc NĐ 34</th>
                    <th rowspan="2" class="TD_SOTIEN">Xã thuộc QĐ 131 chưa lên phường, TT, NTM</th>
                    
                    <th rowspan="2" class="TD_SOTIEN">Xã KV2 QĐ 582 đã đạt chuẩn NTM, có người DTTS vay vốn (hàng tháng vẫn phát sinh cho vay mới)</th>
                    <th rowspan="2" class="TD_SOTIEN">Xã ra khỏi VKK, chỉ còn theo dõi dư nợ</th>
                    
<!--                    <th  class="TD_CHECKBOX">
                            <input type="checkbox" id ="allCheck_1" name="allCheck_1"  />
                        </th> 
                        <th  class="TD_CHECKBOX">
                            <input type="checkbox" id ="allCheck_2" name="allCheck_2"  />
                        </th> -->
                </tr>
                <tr height="35">
                    <th class="TD_SOTIEN">Xã KV3 QĐ 582</th>
                    <th class="TD_SOTIEN">Xã KV2 QĐ 582 chưa đạt chuẩn NTM</th>
                </tr>
                <tr height="35">
                    <th>(1)</th>
                    <th>(2)</th>
                    <th>(3)</th>
                    <th>(4)</th>
                    <th>(5)</th>
                    <th>(6)</th>
                    <th>(7)</th>
                    <th>(8)</th>
                    <th>(9)</th>
                    <th>(10)</th>
                    <th>(11)</th>
                    <th>(12)</th>
                    <th>(13)</th>
                    <th>(14)</th>
                    <th>(15)</th>
                </tr>
                            
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                        
                        <td align ="center" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                            <input type="text" style="text-align:center" value="<s:property  value="THUTU" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" readonly/>
                        </td>                        
                        
                        <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_SOTIEN">
                            <input type="text" value="<s:property  value="TEN" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly/>
                            <input type="hidden" value="<s:property  value="D1" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="MA"/>"/> 
                                                       
                            <input type="hidden" value="<s:property  value="D4" />"  id="id4_<s:property  value="%{#rowstatus.index}" />" 
                                        value="<s:property  value="D4"/>"/>
                            <input type="hidden" value="<s:property  value="D11" />"  id="id11_<s:property  value="%{#rowstatus.index}" />" 
                                        value="<s:property  value="D11"/>"/>
                            <input type="hidden" value="<s:property  value="D12" />"  id="id12_<s:property  value="%{#rowstatus.index}" />" 
                                        value="<s:property  value="D12"/>"/>
                            <input type="hidden" value="<s:property  value="D13" />"  id="id13_<s:property  value="%{#rowstatus.index}" />" 
                                        value="<s:property  value="D13"/>"/>
                            <input type="hidden" value="<s:property  value="D14" />"  id="id14_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="D14"/>"/>
                            <input type="hidden" value="<s:property  value="D15" />"  id="id15_<s:property  value="%{#rowstatus.index}" />" 
                                        value="<s:property  value="D15"/>"/>
                            <input type="hidden" value="<s:property  value="D16" />"  id="id16_<s:property  value="%{#rowstatus.index}" />" 
                                        value="<s:property  value="D16"/>"/>
                            <input type="hidden" value="<s:property  value="D17" />"  id="id17_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="D17"/>"/>
                            <input type="hidden" value="<s:property  value="D18" />"  id="id18_<s:property  value="%{#rowstatus.index}" />" 
                                        value="<s:property  value="D18"/>"/>
                            <input type="hidden" value="<s:property  value="D19" />"  id="id19_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="D19"/>"/>                            
                            <input type="hidden" value="<s:property  value="D20" />"  id="id20_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="D20"/>"/>
                        
                        </td>
                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="number <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"   
                                       />                                  
                        </td>                                                                                                                       
                        <td  align="center" class="TD_CHECKBOX">    
                            <input type="checkbox" id ="idc4<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].D14" value="<s:property  value="D1" />"
                                    onchange="changeCheck2(4,<s:property  value="%{#rowstatus.index}" />,this)"/>
                        </td>    
                        
                        <td  align="center" class="TD_CHECKBOX">    
                            <input type="checkbox" id ="idc11<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1" />"
                                    onchange="changeCheck2(11,<s:property  value="%{#rowstatus.index}" />,this,<s:property value="D21"/>)"/>
                        </td> 
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D7" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH" onfocus="this.select()"
                                    />
                        </td> 
                        <td  align="center" class="TD_CHECKBOX">    
                            <input type="checkbox" id ="idc12<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D1" />"
                                    onchange="changeCheck2(12,<s:property  value="%{#rowstatus.index}" />,this,<s:property value="D21"/>)"/>
                        </td> 
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D9" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="D4  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   />
                        </td> 
                        <td  align="center" class="TD_CHECKBOX">    
                            <input type="checkbox" id ="idc13<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D1" />"
                                    onchange="changeCheck2(13,<s:property  value="%{#rowstatus.index}" />,this,<s:property value="D21"/>)"/>
                        </td>
                        <td  align="center" class="TD_CHECKBOX">    
                            <input type="checkbox" id ="idc14<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].D4" value="<s:property  value="D1" />"
                                    onchange="changeCheck2(14,<s:property  value="%{#rowstatus.index}" />,this,<s:property value="D21"/>)"/>
                        </td>
                        <td  align="center" class="TD_CHECKBOX">    
                            <input type="checkbox" id ="idc15<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].D5" value="<s:property  value="D1" />"
                                    onchange="changeCheck2(15,<s:property  value="%{#rowstatus.index}" />,this,<s:property value="D21"/>)"/>
                        </td>
                        <td  align="center" class="TD_CHECKBOX">    
                            <input type="checkbox" id ="idc16<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].D6" value="<s:property  value="D1" />"
                                    onchange="changeCheck2(16,<s:property  value="%{#rowstatus.index}" />,this,<s:property value="D21"/>)"/>
                        </td>
                        <td  align="center" class="TD_CHECKBOX">    
                            <input type="checkbox" id ="idc17<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].D7" value="<s:property  value="D1" />"
                                    onchange="changeCheck2(17,<s:property  value="%{#rowstatus.index}" />,this,<s:property value="D21"/>)"/>
                        </td>
                        <td  align="center" class="TD_CHECKBOX">    
                            <input type="checkbox" id ="idc18<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].D8" value="<s:property  value="D1" />"
                                    onchange="changeCheck2(18,<s:property  value="%{#rowstatus.index}" />,this,<s:property value="D21"/>)"/>
                        </td>
                        <td  align="center" class="TD_CHECKBOX">    
                            <input type="checkbox" id ="idc19<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D1" />"
                                    onchange="changeCheck2(19,<s:property  value="%{#rowstatus.index}" />,this,<s:property value="D21"/>)"/>
                        </td>                        
                                                                      
                    </tr>                    
                    
                </s:iterator>
            </table>
            <p></p>          
            
            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        
        </s:if>
        <s:if test="Grade.equalsIgnoreCase('2')">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                BIỂU TỔNG HỢP KẾT QUẢ CHO VAY TẠI VÙNG NÔNG THÔN MỚI
            </div>
                &nbsp;
            <s:hidden name="khoa_nhaptaycn"/>
<!--            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>-->
            <table border="1" class="editDelete" id="tablentmoi001" style="width: 97%" align="center">
                <tr height="35">
                    <th rowspan="3" class="TD_THUTU">TT</th>                    
                    <th rowspan="3"  class="TD_CHITIEU">Tên xã</th>                                            
                    <th rowspan="3" class="TD_SOTIEN">Số hộ thoát nghèo</th>                       
                    <th rowspan="3" class="TD_SOTIEN">Hoàn thành 19CT</th>
                    <th rowspan="3"  class="TD_SOTIEN">Xã VKK phát sinh tăng dư nợ trong tháng</th>
                    <th rowspan="3" class="TD_SOTIEN">Xã VKK phát sinh giảm dư nợ trong tháng</th>
                    <th colspan="5" class="TD_SOTIEN">Xã thuộc VKK có dư nợ tại NHCSXH </th>
                    <th colspan="2" class="TD_SOTIEN">Xã không còn thuộc VKK nhưng còn dư nợ</th>                                        
                </tr>
                
                <tr height="35">
                    <!--<th rowspan="2"  class="TD_SOTIEN"></th>-->                                            
                    <!--<th rowspan="2" class="TD_CHITIEU">Ghi chú (xã được bổ sung theo QĐ hoặc Xã đã thuộc VKK nhưng tháng này mới phát sinh cho vay...)</th>-->                       
                    
                    <!--<th rowspan="2" class="TD_SOTIEN"></th>-->
                    <!--<th rowspan="2" class="TD_CHITIEU">Ghi chú (xã  bị loại khỏi VKK theo QĐ...)</th>-->
                    
                    <th colspan="2" class="TD_SOTIEN">Xã thuộc QĐ 582/QĐ/TTg</th>
                    
                    <th rowspan="2" class="TD_SOTIEN">Xã thuộc QĐ 900</th>
                    <th rowspan="2" class="TD_SOTIEN">Xã thuộc NĐ 34</th>
                    <th rowspan="2" class="TD_SOTIEN">Xã thuộc QĐ 131 chưa lên phường, TT, NTM</th>
                    
                    <th rowspan="2" class="TD_SOTIEN">Xã KV2 QĐ 582 đã đạt chuẩn NTM, có người DTTS vay vốn (hàng tháng vẫn phát sinh cho vay mới)</th>
                    <th rowspan="2" class="TD_SOTIEN">Xã ra khỏi VKK, chỉ còn theo dõi dư nợ</th>

                </tr>
                <tr height="35">
                    <th class="TD_SOTIEN">Xã KV3 QĐ 582</th>
                    <th class="TD_SOTIEN">Xã KV2 QĐ 582 chưa đạt chuẩn NTM</th>
                </tr>
                              
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                      
                        <td align ="center" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                            <input type="text" style="text-align:center" value="<s:property  value="THUTU" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" readonly/>
                        </td>
                        
                        <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_CHITIEU">
                            <input type="text" value="<s:property  value="TEN" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly/>
                        </td>
<!--                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D1" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"   
                                       readonly="readonly"/>                                  
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                        </td>                                               -->
                                                
                        <td align = "right" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly/>
                        </td>                        
                        
                        <td align = "center" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td>   
                        
                        <td align = "center" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D11" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td> 
                        
                        <td align = "center" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D12" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td> 
                        <td align = "center" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D13" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td> 
                        <td align = "center" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D14" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td> 
                        <td align = "center" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D15" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td> 
                        <td align = "center" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D16" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td> 
                        <td align = "center" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D17" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td> 
                        <td align = "center" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D18" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td> 
                        <td align = "center" class="TD_SOTIEN">
                            <input type="text" value="<s:property  value="D19" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td> 
                    </tr>                    
                    
                </s:iterator>
            </table>
            <p></p>    
        </s:if>   
        <s:if test="Grade.equalsIgnoreCase('3')">
            <div id="divTitle">
                        TRẠNG THÁI GỬI SỐ LIỆU CỦA CHI NHÁNH
                    </div>
                    <p></p>
                    <table border="1" class="editDelete" id="tablepl01" align="center">
                        <tr>
                            <th align = "center"  style="width: 50px;">Mã PGD</th>
                            <th style="width: 100px;">Tên PGD</th>
                            <th style="width: 60px;">Ngày gửi</th>
                            <th style="width: 50px;">User gửi</th>
                            <th style="width: 90px;">Trạng thái xử lý</th>
                            <th style="width: 90px;">Trạng thái gửi</th>
                        </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                
                        <s:if test="D1.equalsIgnoreCase('true')">
                            <tr style="text-align: center; color: #0000FF" >                                
                                <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                <td style="width: 60px;"><s:property  value="D8" /></td>
                                <td style="width: 50px;"><s:property  value="D7" /></td>
                                <td style="width: 90px;"><s:property  value="D9" /></td>
                                <td style="width: 90px;"><s:property  value="D11" /></td>
                            </tr>
                        </s:if>
                        <s:else>
                            <tr style="text-align: center; color: red" >                               
                                <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                <td style="width: 60px;"><s:property  value="D8" /></td>
                                <td style="width: 50px;"><s:property  value="D7" /></td>
                                <td style="width: 90px;"><s:property  value="D9" /></td>
                                <td style="width: 90px;"><s:property  value="D11" /></td>
                            </tr>
                        </s:else>
                    </s:iterator>
        </s:if>    
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            <s:if test="Grade.equalsIgnoreCase('1')">
                initTable();
            </s:if>
            
        </script>
    </body>
</html>
