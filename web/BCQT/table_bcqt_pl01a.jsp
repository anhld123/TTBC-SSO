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
                $('input.number3').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);

                $('.number3').number(true, 3);

                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TD_TEN_KH").css({"height": "22px"});
                $(".TEN_KH").css({"width": "100%"});
                $(".TEN_KH").css({"height": "22px"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        <script>
            //<editor-fold defaultstate="collapsed" desc="Thêm/xóa row cho bảng">

            function deleteRow(indx) {
                var table = document.getElementById("tablepl01");
                var rowCount = table.rows.length - 3; //Dem so dong cua bang

                if (max_row < rowCount)
                    max_row = rowCount;

                //                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
            }

            function addRow(indx, ma) {
                var flag = 0;
                //                $('input.number3').number(false, 2);
                $('.number3').removeClass();
                //                sleep(1000);

                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("tablepl01");
                var rowCount = table.rows.length - 3; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
                else
                {
                    max_row++;
                    rowCount = max_row;
                }
                for (var i = 0; i < rowCount; i++)
                {
                    //                    alert(getMabyNumber(i));
                    if (getMabyNumber(i) === ma)
                        flag++;
                }
                if (flag > 1)
                    index++;
                var code = (ma + indx).toString();
//                alert('code=' + code + 'rowCount=' + rowCount );
                //                alert('Tong so dong ' + rowCount);
                var newTr = '<tr>\n\
                                <td><input type="hidden" id="id_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].MA" value="' + code + '"/></td>\n\
                                <td></td>\n\
                                <td></td>\n\
                                <td></td>\n\
                                <td></td>\n\
                                <td></td>\n\
                                <td></td>\n\
                                <td></td>\n\
                                <td></td>\n\
\n\                             <td></td>\n\
                                <td></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D12_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D12" class="TEN_KH number3" onfocus="this.select();sumColumn( ' + code + ');" /></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D13_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D13" class="TEN_KH number3" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D14_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D14" class="TEN_KH number3" onfocus="this.select();sumColumn(' + code + ');" readonly="true"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D15_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D15" class="TEN_KH number3" onfocus="this.select();sumColumn(' + code + ');" readonly="true"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D16_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D16" class="TEN_KH number3" onfocus="this.select();sumColumn(' + code + ');" readonly="true"/></td>\n\
                                \n\<td></td>\n\
                                \n\<td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D18_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D18" class="TEN_KH number3" onfocus="this.select();sumColumn(' + code + ');" readonly="true"/></td>\n\
\n\                             <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D19_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D19" class="TEN_KH number3" onfocus="this.select();sumColumn(' + code + ');" readonly="true"/></td>\n\
                                <td><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                $($('table#tablepl01 tr')[index]).after(newTr);



                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                //                $('.number').number(true, 0);
                //            //Cac truong bang so --> se co so truong = 0

                $(".TD_TEN_KH").css({"width": "30px"});
                $(".TD_TEN_KH").css({"height": "22px"});
                $(".TEN_KH").css({"width": "100%"});
                $(".TEN_KH").css({"height": "22px"});
                $('.TEN_KH').focus(function () {
                    $(this).closest('tr').addClass('highlight_row');
                });
                $('.TEN_KH').blur(function () {
                    $(this).closest('tr').removeClass('highlight_row');
                });
                $('input.number').css({"text-align": "right"});
                $('input.number3').css({"text-align": "right"});
                //$('input.number3').css({"text-align": "right"});
                //$('.number3').number(true, 2);
                $('.number3').number(true, 3);
            }

            //</editor-fold>

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
            function getValue(id)
            {
                var value = 0;
                try {
                    value = document.getElementById(id).value;
                    value = value.replace(/,/g, "");
                    if (value == '-1')
                        value = 0.0;
                } catch (e)
                {
                    value = 0.0;
                }
                return parseFloat(value);
            }
            function setValue(id, value)
            {
                try {
                    document.getElementById(id).value = value;
                } catch (e)
                {
//                    alert(e);
                }
            }
            
            function round3(num, decimalPlaces = 0) {
                if (num < 0)
                    return -round(-num, decimalPlaces);
                var p = Math.pow(10, decimalPlaces);
                var n = num * p;
                var f = n - Math.floor(n);
                var e = Number.EPSILON * n;
                // Determine whether this fraction is a midpoint value.
                return (f >= .5 - e) ? Math.ceil(n) / p : Math.floor(n) / p;
            }

            function sumColumn(mainput_tmp)
            {
//                alert('vao');
                var mainput = $.trim(mainput_tmp.toString());
                try {
//                    alert('\'' + mainput+ '\'');
                    var table = document.getElementById("tablepl01");
                    var rowcount = table.rows.length;
                    rowcount = rowcount > max_row ? rowcount : max_row;
                    var D2 = 0, D3 = 0.000, D4 = 0.000, D5 = 0.000, D6 = 0.000, D6_1 = 0.000, D7 = 0.000, D8 = 0.000,
                            D9 = 0.000, D9_1 = 0.000, D10 = 0.000, D10_1 = 0.000, D11 = 0.000, D11_1 = 0.000, D12 = 0.000, D13 = 0.000, D14 = 0.000, D15 = 0.000, D15_1 = 0.000, D16 = 0.000,
                             D17= 0.000,
                            D18 = 0.000, D19 = 0.000;;
                    var pos = -1;
                     console.log('rowcount=' + rowcount);
                     
                     var index=1;
                    for (var i = 0; i < rowcount; i++)
                    {
                        var matmp = getMabyNumber(i); //Lấy ra mã
                        if (mainput_tmp == '999999')
                            continue;
                        if (<s:property value="Grade"/> == '1') //nếu cấp báo cáo là PGD: cấp 1
                        {
//                            alert('mainput='+mainput+' matmp='+matmp+ ' -> i='+i+' -> Gia tri='+getValue('D2_' + i)+' -> rowcount='+rowcount);
                            if (mainput.substr(0, 2) == matmp.substr(0, 2) && matmp.length == mainput.length)
                            {
                                //Lay gia tri cho cac truong tu D2->d6
                                D2 = D2 + getValue('D2_' + i);
                                D3 = D3 + getValue('D3_' + i);
                                D4 = D4 + getValue('D4_' + i);
                                D5 = D5 + getValue('D5_' + i);
                                D6 = D6 + getValue('D6_' + i);
                                D7 = D7 + getValue('D7_' + i);
                                D8 = D8 + getValue('D8_' + i);
                                D17 = D17 + getValue('D17_' + i);
                                //neu ky tu cuoi cung cua ma la '1' Vi du voi ma 
                                //'100001,100030... thi se chi lam voi ma khac '1' o cuoi
                                if (matmp.substr(matmp.length - 2, matmp.length) != '01')
                                {
                                    //lay ra gia tri cua truong D8,D9,D10                                  

                                    //D12 = (getValue('D12_' + i) * 1000) / 1000;
                                    D12 = D12 + getValue('D12_' + i);

                                    D13 = getValue('D13_' + i) * 1.000; 
                                    //Do khi làm tròn không lấy được 3 ký tự sau dấu chấm nên phải làm cách này
                                    D15 = D15 + Math.round(getValue('D12_' + i) * getValue('D13_' + i) * 1000) / 1000;
                                    
//                                     D18 = D18 + Math.round(parseFloat((getValue('D3_' + i) + getValue('D4_' + i)) ) * parseFloat(getValue('D17_' + i))
//                                            + parseFloat(getValue('D7_' + i))
//                                            + parseFloat(getValue('D8_' + i))
//                                            + parseFloat((getValue('D3_' + i) + getValue('D4_' + i)) )  * parseFloat(getValue('D17_' + i)) *0.8
//                                            );
                                    console.log( i+ '-- 1--------- D2=' + getValue('D2_' + i) +  ' --------- D3=' + getValue('D3_' + i) +  ' --------- D7=' + getValue('D7_' + i)
                                            +  ' --------- D8=' + getValue('D8_' + i)+  ' --------- D17=' + getValue('D17_' + i)+  ' --------- D5=' + getValue('D5_' + i))
                                     setValue('D18_' + pos, Math.round((D18) * 1000) / 1000);
                                    //D15 = D14 + (Math.round(D12 * D13) * 1000 / 1000);
                                    //console.log(' --------- D12=' + D12 + ' D13=' + D13 + ' D14=' + D14 + ' D15=' + D15+' aaa='+parseFloat((Math.round(D12 * D13) * 1000) / 1000));
                                    //setValue('D13_' + i,Math.round((getValue('D10_' + i) * getValue('D11_' + i)+getValue('D12_' + i)) * 1000) / 1000);
                                }

//                                setValue('D6_' + i, Math.round(parseFloat((getValue('D3_' + i) + getValue('D4_' + i))*getValue('D5_' + i)) * 1000) / 1000);

                                if (matmp.substr(matmp.length - 2, matmp.length) == '01')
                                {
                                    D15 = 0;
                                    pos = i;
                                    D12 = 0.0;
                                    D13 = 0.0;
                                    D6 = round3(parseFloat((getValue('D3_' + i) + getValue('D4_' + i)) * getValue('D5_' + i)),3);
//                                   console.log('not round=' + round3(parseFloat((getValue('D3_' + i) + getValue('D4_' + i)) * getValue('D5_' + i)),3));   
//                                console.log( i+ 'D6='+D6 + 'D3=' + getValue('D3_' + i) + 'D4=' + getValue('D4_' + i) + 'D5=' + getValue('D5_' + i));
                                    
                                    D6_1 = round3(parseFloat((getValue('D3_' + i) + getValue('D4_' + i)) * getValue('D17_' + i)),3);
                                    setValue('D6_' + i, parseFloat(D6));
                                    D10=Math.round(D6*0.8*1000)/1000;
                                    D10_1=Math.round(D6_1*0.8*1000)/1000;
                                    setValue('D10_' + i, parseFloat(D10));
                                    D9 = Math.round((D6 + getValue('D7_' + i) + getValue('D8_' + i)) * 1000) / 1000;
                                    D9_1 = Math.round((D6_1 + getValue('D7_' + i) + getValue('D8_' + i)) * 1000) / 1000;
//                                alert('mainput='+mainput+' -> matmp='+matmp+' -> D2='+getValue('D2_' + i)+' -> D3='+getValue('D3_' + i)+' -> D5='+getValue('D5_' + i)+' -> D6='+getValue('D6_' + i));
                                    setValue('D9_' + pos, parseFloat(D9));
                                    D14 = 0.0;

                                    D14 = Math.round(getValue('D14_' + i) * 1000) / 1000;
                                    

                                    D11 = Math.round((getValue('D9_' + i) + getValue('D10_' + i)) * 1000) / 1000;
                                    D11_1 = Math.round((D9_1 + D10_1) * 1000) / 1000;
                                    setValue('D11_' + pos, parseFloat(D11));

                                    D15 = D14 + Math.round(getValue('D12_' + i) * getValue('D13_' + i) * 1000) / 1000;
                                    D15_1 = D15;

                                    console.log('======================> D15_' + pos +' D12=' + getValue('D12_' + i)+ ' D13=' + getValue('D13_' + i) + ' D14=' + D14 + ' D15=' + D15);
                                    //setValue('D15_' + pos, parseFloat(D15));
                                    setValue('D15_' + pos, Math.round((D15) * 1000) / 1000);

                                    D16 = D16 + Math.round((getValue('D11_' + i) + getValue('D15_' + i)) * 1000) / 1000;
                                     console.log( 'D11_1+ ='+ D11_1+ '--D15_1='+D15_1 + '--D6_1='+D6_1 + '--D9_1='+D9_1 + '--D10_1='+D10_1) 
                                    D18 = D18 + (D11_1 * 1000) / 1000;
                                    
//                                    D3 = Math.round(getValue('D3_' + i) * 1000) ;
//                                    D4 = Math.round(getValue('D4_' + i) * 1000) ;
//                                    D17 = getValue('D17_' + i) ;
//                                    D7 = Math.round(getValue('D7_' + i) * 1000) ;
//                                    D8 = Math.round(getValue('D8_' + i) * 1000) ;
//                                    D18 = 0.0;
//                                    
//                                    var iD34 = parseFloat(D3 +D4 );
//                                    var iD17 = parseFloat(D17 );
//                                    var iD7 = parseFloat(D7 );
//                                    var iD8 = parseFloat(D8 );
//                                    var iD18 = iD34*iD17*1.8 + iD7 + iD8;
//                                    
//                                    console.log('aaaa'+ i + '-' + iD34 + '-' +iD17 +'-' + iD7 + '-' +iD8 +'-' + 'iD18='+ iD18);
//                                    
//                                    D18 = D18 + Math.round(iD18)/1000;
//                                    
////                                    D18 = D18 + Math.round(parseFloat((D3 +D4) ) * parseFloat(D17)
////                                            + parseFloat(D7)
////                                            + parseFloat(D8)
////                                            + parseFloat((D3 + D4) )  * parseFloat(D17) *0.8
////                                            )/1000;
//                                    console.log( i+ '-- 2--------- D2=' + getValue('D2_' + i) +  ' --------- D3=' + getValue('D3_' + i) +  ' --------- D7=' + getValue('D7_' + i)
//                                            +  ' --------- D8=' + getValue('D8_' + i)+  ' --------- D17=' + getValue('D17_' + i)+  ' --------- D5=' + getValue('D5_' + i));
                                    
                                    setValue('D18_' + pos, Math.round((D18) * 1000) / 1000);
                                    D19 = D19 + (getValue('D18_' + i) + getValue('D15_' + i));

                                }

                                //set gia tri
                                if (mainput.substr(0, 2) == matmp.substr(0, 2) && matmp.length == mainput.length)
                                {
                                    //setValue('D10_' + pos, D10);
                                    //setValue('D12_' + pos, D12);
                                    //setValue('D15_' + pos, D15);
                                    setValue('D15_' + pos, Math.round((D15) * 1000) / 1000);
                                    //D15 = getValue('D15_' + pos)
                                    setValue('D16_' + pos, Math.round((D11 + D15) * 1000) / 1000);
//                                    
//                                     D18 = D18 + Math.round(parseFloat((getValue('D3_' + i) + getValue('D4_' + i)) ) * parseFloat(getValue('D17_' + i))
//                                            + parseFloat(getValue('D7_' + i))
//                                            + parseFloat(getValue('D8_' + i))
//                                            + parseFloat((getValue('D3_' + i) + getValue('D4_' + i)) )  * parseFloat(getValue('D17_' + i)) *0.8
//                                            );
                                    console.log( i+ '-- 3--------- D2=' + getValue('D2_' + i) +  ' --------- D3=' + getValue('D3_' + i) +  ' --------- D7=' + getValue('D7_' + i)
                                            +  ' --------- D8=' + getValue('D8_' + i)+  ' --------- D17=' + getValue('D17_' + i)+  ' --------- D5=' + getValue('D5_' + i))
                                     setValue('D18_' + pos, Math.round((D18) * 1000) / 1000);

                                    setValue('D19_' + pos, Math.round((D18 + D15) * 1000) / 1000);
                                }
                            }
                        } else //nếu cấp báo cáo là Chi nhánh: cấp 2
                        {
//                            console.log('Vao thiet lap');
                            if (mainput.substr(0, 2) == matmp.substr(0, 2) && matmp.length == mainput.length)
                            {
                                D2 = Math.round(D2*1000 + getValue('D2_' + i)*1000)/1000;
                                D3 = Math.round(D3*1000 + getValue('D3_' + i)*1000)/1000;
                                D4 = Math.round(D4*1000 + getValue('D4_' + i)*1000)/1000;
                                D5 = Math.round(D5*1000 + getValue('D5_' + i)*1000)/1000;
                                D6 = Math.round(D6*1000 + getValue('D6_' + i)*1000)/1000;
                                D7 = Math.round(D7*1000 + getValue('D7_' + i)*1000)/1000;
                                D8 = Math.round(D8*1000 + getValue('D8_' + i)*1000)/1000;
                                D9 = Math.round(D9*1000 + getValue('D9_' + i)*1000)/1000;
                                D10 = Math.round(D10*1000 + getValue('D10_' + i)*1000)/1000;
                                D11 = Math.round(D9*1000 + D10*1000)/1000;
                                D12 = Math.round(D12*1000 + getValue('D12_' + i)*1000)/1000;
                                D13 = Math.round(D13*1000 + getValue('D13_' + i)*1000)/1000;
                                D14 = Math.round(D14*1000 + getValue('D14_' + i)*1000)/1000;
                                D15 = Math.round(D15*1000 + getValue('D15_' + i)*1000)/1000;

                                D16 = Math.round(D11*1000 + D15*1000)/1000;
//                                D18 = D18 + Math.round(parseFloat((getValue('D3_' + i) + getValue('D4_' + i)) ) * parseFloat(getValue('D17_' + i))
//                                            + parseFloat(getValue('D7_' + i))
//                                            + parseFloat(getValue('D8_' + i))
//                                            + parseFloat((getValue('D3_' + i) + getValue('D4_' + i)) ) * parseFloat(getValue('D17_' + i)) *0.8
//                                            );
//                                D19 = Math.round(D18*1000 + D15*1000)/1000;
                                if (matmp.substr(matmp.length - 2, matmp.length) == '01')
                                {
                                    D2 = 0.0;
                                    D3 = 0.0;
                                    D4 = 0.0;
                                    D5 = 0.0;
                                    D6 = 0.0;
                                    D7 = 0.0;
                                    D8 = 0.0;
                                    D9 = 0.0;
                                    D10 = 0.0;
                                    D11 = 0.0;
                                    D12 = 0.0;
                                    D13 = 0.0;
                                    D14 = 0.0;
                                    D16 = 0.0;
                                    D15 = 0.0;
                                     D17 = 0.0;
                                      D18 = 0.0;
                                       D19 = 0.0;
                                    pos = i;
                                }
                            }

                            setValue('D2_' + pos, Math.round(D2 * 1000) / 1000);
                            setValue('D3_' + pos, Math.round(D3 * 1000) / 1000);
                            setValue('D4_' + pos, Math.round(D4 * 1000) / 1000);
                            //setValue('D5_' + pos, Math.round(D5 * 1000) / 1000);
                            setValue('D6_' + pos, Math.round(D6 * 1000) / 1000);

                            setValue('D7_' + pos, Math.round(D7 * 1000) / 1000);
                            setValue('D8_' + pos, Math.round(D8 * 1000) / 1000);
                            setValue('D9_' + pos, Math.round(D9 * 1000) / 1000);
                            setValue('D10_' + pos, Math.round(D10 * 1000) / 1000);
                            setValue('D11_' + pos, Math.round((D11) * 1000) / 1000);
                            setValue('D12_' + pos, Math.round(D12 * 1000) / 1000);
                            setValue('D13_' + pos, Math.round((D13) * 1000) / 1000);
                            setValue('D14_' + pos, Math.round((D14) * 1000) / 1000);
                            setValue('D15_' + pos, Math.round((D15) * 1000) / 1000);
                            setValue('D16_' + pos, Math.round((D16) * 1000) / 1000);
                            
                            setValue('D17_' + pos, Math.round((D17) * 1000) / 1000)
                            setValue('D18_' + pos, Math.round((D18) * 1000) / 1000)
                            setValue('D19_' + pos, Math.round((D19) * 1000) / 1000)
//                            console.log(D7);
                        }
                    }
                    
                } catch (e)
                {
                    alert(e);
                }
            }
            function initTable()
            {
                //SET GIA TRI CHO SELECT 
                var table = document.getElementById("tablepl01");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    var matmp = getMabyNumber(i);

                    if (<s:property value="Grade"/> == '1')
                    {
//                        alert(matmp.substr(0, 2));
                        if (matmp.substr(matmp.length - 2, matmp.length) == '01')
                        {
                            var bflag = false;
                            var value = '';
                            var d5 = getValue('TMP_D5_' + i);
//                            alert('TMP_D5_' + i+' -> '+Math.round(getValue('TMP_D5_' + i) * 100 / 100));
                            if (d5 > 0)
                            {
                                $('#D5_' + i).find('option').each(function () {
                                    var nn_select = parseFloat($.trim($(this).text()));

                                    if (d5 == nn_select)
                                    {
                                        value = $.trim($(this).val());
//                                        alert('i='+i+' matmp='+matmp+' d5='+d5+' nn_select='+nn_select);
                                        //                                $(this).val($.trim($(this).text()));
                                        bflag = true;
                                    }
                                });
                                if (bflag)
                                {
                                    bflag = false;
                                    setValue('D5_' + i, value);
                                }
                            }
                        }
                        if (matmp != '999999')
                        {
//                            alert(matmp);
                            sumColumn(matmp);
                        }
                    } else
                    {
                        if (matmp.substr(matmp.length - 1, matmp.length) == '1')
                        {
//                            console.log('matmp=' + matmp);
                            sumColumn(matmp);
                        }

                    }
                }
            }
        </script>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_bcqt}" action="SAVE_%{khoa_bcqt}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="id_<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
               PL/01A - BÁO CÁO TÌNH HÌNH LAO ĐỘNG TIỀN LƯƠNG NĂM <s:property  value="nambc"/>
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Người/triệu đồng
            </div>
            <table border="1" class="editDelete" id="tablepl01" align="center">
                <tr>
                    <th rowspan="2" style="width: 30px;" class="TD_TEN_KH">Tháng</th>
                    <th colspan="10">Lương cán bộ làm CMNV</th>
                    <th colspan="4">Tiền công, phụ cấp bảo vệ, lao công, tạp vụ</th>
                    <th rowspan="2" style="width: 30px;" class="TD_TEN_KH">Tổng tiền lương tiền công kế hoạch</th>
                    <th rowspan="2" style="width: 40px;" class="TD_TEN_KH">Lương cơ sở quy đổi</th>
                    <th rowspan="2" style="width: 40px;" class="TD_TEN_KH">Tổng tiền lương CB làm CMNV (V1KH + V2KH) tính theo mức lương cơ sở quy đổi</th>
                    <th rowspan="2" style="width: 40px;" class="TD_TEN_KH">Quỹ tiền lương V </th>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                        <th rowspan="2" style="width: 30px;" class="TD_TEN_KH">Thêm/Xóa</th>
                        </s:if>
                </tr>
                <tr>     
                    <th style="width: 40px;" class="TD_TEN_KH">Số lao động</th>
                    <th style="width: 20px;" class="TD_TEN_KH">Hệ số lương cấp bậc</th>
                    <th style="width: 20px;" class="TD_TEN_KH">Hệ số phụ cấp các loại</th>
                    <!--                    <th style="width: 20px;" class="TD_TEN_KH">Tổng HSL+PC</th>-->
                    <th style="width: 40px;" class="TD_TEN_KH">Mức lương tối thiểu vùng</th>
                    <!--<th style="width: 30px;" class="TD_TEN_KH">Hệ số K điều chỉnh</th>-->
                    <th style="width: 40px;" class="TD_TEN_KH">Lương và phụ cấp (không gồm phụ cấp thu hút, phụ cấp khu vực)</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Số tiền phụ cấp khu vực</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Số tiền phụ cấp thu hút</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Tiền lương cố định theo hợp đồng lao động (V1KH)</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Tiền lương gắn với kết quả thực hiện công việc (V2KH; Htlns=0,8)</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Tổng tiền lương cán bộ làm CMNV (V1KH+V2KH)</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Số lao động định biên</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Mức lương</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Số tiền phụ cấp làm đêm của bảo vệ</th>
                    <th style="width: 40px;" class="TD_TEN_KH">Tổng tiền công phụ cấp bảo vệ, lao công, tạp vụ (VB)</th>
                </tr>
                <tr>         
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(1)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(2)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(3)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(4)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(5)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(6)=((3)+(4))x(5)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(7)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(8)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(9)=(6)+(7)+(8)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(10)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(11)=(9)+(10)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(12)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(13)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(14)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(15)=(12)x(13)+(14)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(16)=(11)+(15)</th>
                    <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(17)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(18)</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(19)=(18)+(15)</th>
                        <s:if test="Grade.equalsIgnoreCase('1')">
                        <th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH"></th>
                        </s:if>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>  
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <s:if test="TT_HIENTHI.equalsIgnoreCase('1')">
                                <td align = "left" class="TD_TEN_KH">
                                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                    <input type="text" value="<s:property  value="TEN" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH number" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D3" />"  id="D3_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="hidden" id="TMP_D5_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D5" />" 
                                           name="d55" class="TEN_KH number3" onfocus="this.select()"/>
                                    <s:select 
                                        id="D5_%{#rowstatus.index}"
                                        name="lstDulieuNt[%{#rowstatus.index}].D5"
                                        list="lstAllBcqt" 
                                        listKey="sKey"
                                        listValue="sDesc"           
                                        headerKey="-1"
                                        headerValue="-- Chọn --" 
                                        cssStyle="font-weight: bold;vertical-align: middle;width: 30px;"
                                        onBeforeTopics="myBeforeHandler" 
                                        onCompleteTopics="myCompleteTopics" cssClass="TEN_KH"
                                        >                    
                                    </s:select>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number2" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>

                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');"  readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D13" />" id="D13_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D14" />" id="D14_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D15" />" id="D15_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D16" />" id="D16_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="hidden" id="TMP_D517_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D17" />" 
                                           name="d5517" class="TEN_KH number3" onfocus="this.select()"/>
                                    <s:select 
                                        id="D17_%{#rowstatus.index}"
                                        name="lstDulieuNt[%{#rowstatus.index}].D17"
                                        list="lstAllBcqt" 
                                        listKey="sKey"
                                        listValue="sDesc"           
                                        headerKey="-1"
                                        headerValue="-- Chọn --" 
                                        cssStyle="font-weight: bold;vertical-align: middle;width: 30px;"
                                        onBeforeTopics="myBeforeHandler" 
                                        onCompleteTopics="myCompleteTopics" cssClass="TEN_KH"
                                        >                    
                                    </s:select>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D18" />" id="D18_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');"  readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D19" />" id="D19_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                
                            </s:if>
                            <s:else>
                                <td><input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                </td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');"/>

                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D13" />" id="D13_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" />
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D14" />" id="D14_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D15" />" id="D15_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D16" />" id="D16_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="hidden" id="TMP_D517_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D17" />" 
                                           name="d5517" class="TEN_KH number3" onfocus="this.select()"/>
                                    <s:select 
                                        id="D17_%{#rowstatus.index}"
                                        name="lstDulieuNt[%{#rowstatus.index}].D17"
                                        list="lstAllBcqt" 
                                        listKey="sKey"
                                        listValue="sDesc"           
                                        headerKey="-1"
                                        headerValue="-- Chọn --" 
                                        cssStyle="font-weight: bold;vertical-align: middle;width: 30px;"
                                        onBeforeTopics="myBeforeHandler" 
                                        onCompleteTopics="myCompleteTopics" cssClass="TEN_KH"
                                        >                    
                                    </s:select>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D18" />" id="D18_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');"  readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D19" />" id="D19_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>

                            </s:else>
                        </s:if>
                        <s:else> <!-- cấp bằng 2-->
                            <s:if test="!TT_HIENTHI.equalsIgnoreCase('0')">
                                <td align = "left" class="TD_TEN_KH">
                                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                    <input type="text" value="<s:property  value="TEN" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH" onfocus="this.select()" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D3" />"  id="D3_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                           name="sTenkh" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" id="D5_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D5" />" 
                                           name="d55" class="TEN_KH number3" onfocus="this.select()" readonly="true"/>

                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH number2" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                           name="sTenkh" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D8" />" id="D8_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D9" />" id="D9_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>

                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D13" />" id="D13_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D14" />" id="D14_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D15" />" id="D15_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D16" />" id="D16_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" id="D17_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D17" />" 
                                           name="D175" class="TEN_KH number3" onfocus="this.select()" readonly="true"/>

                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D18" />" id="D18_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D19" />" id="D19_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                            </s:if>
                            <s:else>
                                <td><input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                </td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D12" />" id="D12_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>

                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D13" />" id="D13_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D14" />" id="D14_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D15" />" id="D15_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D16" />" id="D16_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D18" />" id="D18_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="D19" />" id="D19_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="TEN_KH number3" onfocus="this.select();
                                                   sumColumn('<s:property  value="MA"/>');" readonly="true"/>
                                </td>

                            </s:else>
                        </s:else>


                        <!--Them nut them, xoa khi o cap bao cao la pgd-->
                        <s:if test="Grade.equalsIgnoreCase('1')">
                            <td align = "center" class="TD_TEN_KH">

                                <s:if test="TT_HIENTHI.equalsIgnoreCase('1')">
                                    <s:if test="THUTU==1">
                                        <input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex,<s:property  value="MA"/>)" class="TEN_KH"/>
                                    </s:if>
                                </s:if>
                                <s:else>
                                    <input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                                </s:else> 

                            </td>
                        </s:if>

                    </tr>
                </s:iterator>
            </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initTable();
        </script>
    </body>
</html>
