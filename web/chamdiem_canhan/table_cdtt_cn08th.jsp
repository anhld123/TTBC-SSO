<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="chamdiem_canhan/js/chamdiem_canhan.js"></script>  
        <style>
            .BOLD {
                font-weight:bold;
            }
        </style>
        <script>

            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_THUTU").css({"width": "3%"});
                $(".TD_TEN_KH").css({"width": "50px"});
                $(".TD_TENTS").css({"width": "120px"});
                $(".TD_MATS").css({"width": "85px"});
                $(".TD_NGUYENGIA").css({"width": "8%"});
                $(".TD_THOIGIAN").css({"width": "6%"});
                $(".TD_SOLUONG").css({"width": "4%"});
                $(".TD_TYLE").css({"width": "4%"});
                $(".TD_THEMXOA").css({"width": "5%"});
                $(".TD_CBTH").css({"width": "12%"});
                $(".TD_GHICHU").css({"width": "10%"});
                $(".TD_CHITIEU").css({"width": "20%"});
                $(".TD_NOIDUNG").css({"width": "12%"});
                $(".TD_NOIDUNG06A").css({"width": "35%"});
                $(".hideColumn").hide();
                $(".TEN_KH").css({"width": "100%"});
                evaluateSum_col('CHAMDIEMTT_001', 'D10');
                //catcongthuc("CN010101+CN010102+CN0102+CN02+CN03");
                //evaluateSum_col('CHAMDIEMTT_001', 'D10');

            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });

            function evaluateSum_row(ma, ma_d29) {
                try {
                    var d10 = 0;
                    var d5 = document.getElementById('D5_' + ma).value;
                    d5 = d5.replace(',', '');


                    if (parseFloat(d5) > 100 || parseFloat(d5) < 0)
                    {
                        swal('Lỗi', 'Bạn không được nhập điểm lớn hơn 100 hoặc nhỏ hơn 0', 'warning');
                        document.getElementById('D5_' + ma).style.background = '#ff0000';
                        document.getElementById('D5_' + ma).value = 0;
                        return;
                    }
                    if (ma_d29 === 'Y')
                    {
//                        if (parseFloat(d5) > 100)
//                        {
//                            swal('Lỗi', 'Bạn không được nhập điểm lớn hơn 100 hoặc nhỏ hơn 0', 'warning');
//                            document.getElementById('D5_' + ma).style.background = '#ff0000';
//                            document.getElementById('D5_' + ma).value = 0;
//                            return;
//                        }
                        var D28 = getValue('D28_' + ma);
                        if (D28 === 'LOI50')
                        {
                            document.getElementById('D5_' + ma).value = Math.round(d5)
                            d10 = getValue('D1_' + ma) - getValue('D5_' + ma) * 50 / 100 * getValue('D1_' + ma);
                            document.getElementById('D10_' + ma).value = d10 < 0 ? 0 : d10;
                        } else if (D28 === 'LOI20')
                        {
                            document.getElementById('D5_' + ma).value = Math.round(d5)
                            d10 = getValue('D1_' + ma) - getValue('D5_' + ma) * 20 / 100 * getValue('D1_' + ma);
                            document.getElementById('D10_' + ma).value = d10 < 0 ? 0 : d10;
                        } else if (D28 === 'LOI02')
                        {
                            document.getElementById('D5_' + ma).value = d5 > 5 ? 5 : d5;
                            d10 = getValue('D1_' + ma) - (getValue('D5_' + ma) * 2);
                            document.getElementById('D10_' + ma).value = d10;
                        } else
                        {
                            d10 = getValue('D1_' + ma) * getValue('D5_' + ma) / 100;
                            document.getElementById('D10_' + ma).value = d10;
                        }
                    }
                } catch (e) {
                    swal('Lỗi', 'ERROR evaluateSum_row ' + e.toString());
                }
            }
            function evaluateSum_col(table_id, subid) {

                try {
//                                alert('vao tinh tong');
                    console.log('Bat dau evaluateSum_col');
                    var temp = 0;
                    var kh_capht = ".KH_CAPHT";
                    var kh_congthuc = ".KH_CONGTHUC";
                    var kh_ma_ct = ".MA_CT";
                    var tinhkhac = "AAA";
                    var trungbinh = "AAA";
                    var trungbinh = "AAA";
                    //Thu tu cua i tinh tu 0
                    var arrCapht = [7.0, 6.0, 5.0, 4.0, 3.0, 2.0, 1.0]; //Luu cac cot cua du lieu can tinh toan

                    var rowCount = $("#" + table_id + " td").closest("tr").length;
//                    document.getElementById('D10_CN02').value = 20;
//                   
                    var D28_CN010102 = document.getElementById('D28_CN010102').value;
                    if (D28_CN010102 === 'CN010102_NEW')
                    {
                        console.log('------------day');
                        var riengD5 = document.getElementById('D5_CN010102').value;
                        if (riengD5 < 0)
                        {
                            document.getElementById('D11_CN010102').value = 0;
                            riengD5 = document.getElementById('D5_CN010102').value;
                        }
                        document.getElementById('D5_CN010102').value = Math.round(riengD5)
                        var riengD1 = document.getElementById('D1_CN010102').value;
                        document.getElementById('D10_CN010102').value = riengD1 - riengD5 * 5 * 10 / 100 < 0 ? 0 : riengD1 - riengD5 * 5 * 10 / 100

                    }
                    //duyệt cấp cộng tổng hợp
                    for (k = 0; k < arrCapht.length; k++)
                    {//duyệt số row của bảng để lấy ra công thức.
//                        console.log('START #################################### ' + arrCapht[k] + "#########################################");

                        for (i = 0; i < rowCount; i++)
                        {
                            var D28_khoa = $('.D28_DIEM').eq(i).val();
                            //nếu cấp báo cáo bằng với danh sách mảng của câp báo cáo ở trên và công thức khác null hoặc rỗng
                            if ($(kh_capht).eq(i).val() == arrCapht[k] && $(kh_congthuc).eq(i).val().length > 0)
                            {
                                var ma_ct = $(kh_ma_ct).eq(i).val();
                                var congthuc = $(kh_congthuc).eq(i).val();
                                //lấy ra công thức

                                //                                var tong_dc = sum_mact(valNew, kh_dc, table_id);

                                //                                console.log('ma_ct=' + ma_ct + ' tongcong=' + tongcong);
                                //Lấy ra khóa của báo cáo
                                var khoa = document.getElementById('khoa').value;

                                var Grade = document.getElementById('Grade').value;
                                var D_khoa_nhiemvu = $('.KHOA_CHUCNANG_NHIEMVU').eq(i).val();
                                var RULEUSER = document.getElementById('RULEUSER').value;
                                //cắt công thức đưa về mảng
                                //var valNew = congthuc.replace(/-/g, '+').split('+');

                                var valNew = catcongthuc(congthuc);
                                var tongcong = tongcongthuc_08TH(valNew, subid, congthuc);
                                console.log("tongcongthuc_08TH Bat dau ================================================= D28_khoa=" + D28_khoa);
//                                console.log('valNew=' + valNew + ' ma_ct=' + ma_ct + ' tongcong=' + tongcong + ' RULEUSER=' + RULEUSER + ' subid=' + subid);
//                                console.log('>>>>>>>>>>>>>>> D_khoa_nhiemvu='+D_khoa_nhiemvu+' ~ D28_khoa='+D28_khoa);
                                if (D28_khoa === 'DIEM_HT_CV')
                                {
                                    tongcong = tongcongthuc_08TH(valNew, subid, congthuc);
                                    resetvalue(subid, ma_ct);
//                                    console.log('DIEM_HT_CV=' + tongcong);
//                                    console.log('valNew=' + valNew + ' subid=' + subid + ' congthuc=' + congthuc + ' ma_ct=' + ma_ct);
                                    laydiemHoanthanh(subid, ma_ct, tongcong);
                                } else if (D28_khoa === 'DIEM_HT_CV_NEW')
                                {
                                    tongcong = tongcongthuc_08TH(valNew, subid, congthuc);
                                    resetvalue(subid, ma_ct);
//                                    console.log('DIEM_HT_CV=' + tongcong);
//                                    console.log('valNew=' + valNew + ' subid=' + subid + ' congthuc=' + congthuc + ' ma_ct=' + ma_ct);
                                    var D1_CN010103 = document.getElementById('D1_CN010103').value
                                    console.log('D1_CN010103=' + D1_CN010103 + '---tongcong=' + tongcong);
                                    laydiemHoanthanh(subid, ma_ct, tongcong + parseFloat(D1_CN010103));
                                } else if (D28_khoa === 'TINHKHAC')
                                {
                                    tinhkhac = "TINHKHAC";
                                    document.getElementById(subid + '_' + ma_ct).value = tongcong;
                                } else if (D28_khoa === 'TRUNGBINH')
                                {
                                    console.log("tongcong=TRUNGBINH");
                                    trungbinh = "TRUNGBINH";
                                    document.getElementById(subid + '_' + ma_ct).value = tongcong;
                                } else if (D28_khoa === 'PGD_TD_TTDX')
                                {
                                    trungbinh = "PGD_TD_TTDX";
                                    document.getElementById(subid + '_' + ma_ct).value = tongcong;

                                } else if (D28_khoa === 'TB_' + D_khoa_nhiemvu)
                                {
//                                    alert(D28_khoa);
                                    trungbinh = D28_khoa;
                                    document.getElementById(subid + '_' + ma_ct).value = tongcong;

                                } else if (D28_khoa === 'XEPLOAI')
                                {
                                    try
                                    {
                                        resetvalue(subid, ma_ct);
//                                    console.log("tongcong=" + tongcong);
//                                    91-100, 81-90, 71-80, dưới 70 HTXS, HTT, HT, KHT
                                        if (tongcong >= 90 && tongcong <= 100)
                                        {
                                            document.getElementById(subid + '_' + ma_ct).value = tongcong;//'HTXS';
                                            document.getElementById(subid + '_' + ma_ct + '01').value = tongcong;//'HTXS';
                                        }
                                        if (tongcong >= 80 && tongcong < 90)
                                        {
                                            document.getElementById(subid + '_' + ma_ct).value = tongcong;//'HTT';
                                            document.getElementById(subid + '_' + ma_ct + '02').value = tongcong;//'HTT';
                                        }
                                        if (tongcong >= 70 && tongcong < 80)
                                        {
                                            document.getElementById(subid + '_' + ma_ct).value = tongcong;//'HT';
                                            document.getElementById(subid + '_' + ma_ct + '03').value = tongcong;//'HT';
                                        }
                                        if (tongcong < 70)
                                        {
                                            document.getElementById(subid + '_' + ma_ct).value = tongcong;//'KHT';
                                            document.getElementById(subid + '_' + ma_ct + '04').value = tongcong;//'KHT';
                                        }
                                    } catch (e) {
                                        console.log('ERROR D28_khoa= ' + D28_khoa + ' ~ ' + e.toString());
                                    }

                                } else {
                                    document.getElementById(subid + '_' + ma_ct).value = tongcong;
                                }
                                // document.getElementById(subid + '_' + ma_ct).value = tongcong;
                            } else
                            {
//                                if (D28_khoa === 'DIEM_HT_CV')
//                                {
//                                    tongcong = tongcongthuc_08TH(valNew, subid, congthuc);
//                                    console.log(tongcong);
//                                    if (tongcong >= 71 && tongcong <= 80)
//                                        document.getElementById(subid + '_' + ma_ct).value = 20;
//                                    if (tongcong >= 66 && tongcong <= 70)
//                                        document.getElementById(subid + '_' + ma_ct).value = 15;
//                                    if (tongcong >= 61 && tongcong <= 65)
//                                        document.getElementById(subid + '_' + ma_ct).value = 10;
//                                    if (tongcong <= 60)
//                                        document.getElementById(subid + '_' + ma_ct).value = 5;
//                                }
//                                if (giatri_D10_11 != 0 && giatri_D10_11 != 1 && giatri_D10_11 != 2 && giatri_D10_11 != 3 && giatri_D10_11 != 5
//                                        && giatri_D10_11 != 7 && giatri_D10_11 != 10)
//                                {
//                                    swal('Lỗi', 'Bạn không được nhập khác các giá trị 0,1,2,3,5,7,10 hoặc nhỏ hơn 0 cho chỉ tiêu 4', 'warning');
//                                    cur.style.background = '#ff0000';
//                                    cur.value = 0;
//                                    return;
//                                }
                            }

                        }
//                        console.log('END #################################### ' + arrCapht[k] + "#########################################");
                    }

//                    console.log("bat dau tinh toan tiep =================================================");
                    var diemct_CN0102 = 0, id_mact_diem = subid + '_CN010101';
                    var giatri = getvalue(id_mact_diem);
                    var D_khoa = $('.KHOA').eq(i).val();
                    if (D_khoa === 'PGD_TD_TTDX')
                    {
                        giatri = giatri / 2;
                    }
                    diemct_CN0102 = Math.round((giatri / 30) * 10 * 100) / 100;
                    var khoa_main = document.getElementById('khoa').value;
//                    console.log('tinhkhac=' + tinhkhac);

                    if (tinhkhac === 'TINHKHAC')
                    {
//                        document.getElementById('D10_CN010102').value = giatri;
                    } else
                    {
                        console.log('trungbinh=' + trungbinh);
                        if (trungbinh != 'TB_PGD_KS_CB')
                        {
                            console.log('D_khoa_nhiemvu=' + D_khoa_nhiemvu);
                            var D28_CN010102 = document.getElementById('D28_CN010102').value;
                            if (D28_CN010102 === 'CN010102_NEW')
                            {
                                var riengD5 = document.getElementById('D5_CN010102').value;
                                if (riengD5 < 0)
                                {
                                    document.getElementById('D5_CN010102').value = 0;
                                    riengD5 = document.getElementById('D5_CN010102').value;
                                }
                                document.getElementById('D5_CN010102').value = Math.round(riengD5)
                                var riengD1 = document.getElementById('D1_CN010102').value;
//                                console.log('riengD5=' + riengD5 + "riengD1=" +riengD1+ "---"+riengD5*5*10/100);
                                document.getElementById('D10_CN010102').value = riengD1 - riengD5 * 5 * 10 / 100 < 0 ? 0 : riengD1 - riengD5 * 5 * 10 / 100

                            } else
                            {
                                document.getElementById(subid + '_CN010102').value = diemct_CN0102;
                                document.getElementById('D5_CN010102').value = Math.round((giatri / 30) * 100 * 100) / 100;
                            }
                        } else
                        {
                            console.log('D_khoa_nhiemvu=' + D_khoa_nhiemvu);
                            var D28_CN010102 = document.getElementById('D28_CN010102').value;
                            if (D28_CN010102 === 'CN010102_NEW')
                            {
                                var riengD5 = document.getElementById('D5_CN010102').value;
                                if (riengD5 < 0)
                                {
                                    document.getElementById('D5_CN010102').value = 0;
                                    riengD5 = document.getElementById('D5_CN010102').value;
                                }
                                document.getElementById('D5_CN010102').value = Math.round(riengD5)
                                var riengD1 = document.getElementById('D1_CN010102').value;
//                                console.log('riengD5=' + riengD5 + "riengD1=" +riengD1+ "---"+riengD5*5*10/100);
                                document.getElementById('D10_CN010102').value = riengD1 - riengD5 * 5 * 10 / 100 < 0 ? 0 : riengD1 - riengD5 * 5 * 10 / 100

                            } else
                            {
                                id_mact_diem = subid + '_CN010101';
                                var giatri = getvalue(id_mact_diem);
                                diemct_CN0102 = Math.round((giatri / 30) * 10 * 100) / 100;
                                console.log('diemct_CN0102=' + diemct_CN0102 + ' ~ giatri=' + giatri);
                                document.getElementById(subid + '_CN010102').value = diemct_CN0102;
                                document.getElementById('D5_CN010102').value = Math.round((giatri / 30) * 100 * 100) / 100;
                            }
                        }
                    }

//                    console.log("Het loai khac =================================================");
                    if (trungbinh === 'TRUNGBINH')
                    {
//                        console.log('Vao!!    diemct_CN0102');
                        var CN01 = document.getElementById(subid + '_CN0101010501').value;
                        var CN02 = document.getElementById(subid + '_CN0101010502').value;
                        var CN03 = document.getElementById(subid + '_CN0101010503').value;
//                        console.log('CN01='+CN01+'=--CN02'+CN02+'--CN03='+CN03);
                        if (parseFloat(CN01) > 0 && parseFloat(CN02) > 0 && parseFloat(CN03) > 0)
                        {
                            document.getElementById(subid + '_CN01010105').value = (parseFloat(CN01) + parseFloat(CN02) + parseFloat(CN03)) / 3;
                        } else if ((parseFloat(CN01) > 0 && parseFloat(CN02) > 0 && parseFloat(CN03) === 0)
                                || (parseFloat(CN01) > 0 && parseFloat(CN02) === 0 && parseFloat(CN03) > 0)
                                || (parseFloat(CN01) === 0 && parseFloat(CN02) > 0 && parseFloat(CN03) > 0))
                        {
                            document.getElementById(subid + '_CN01010105').value = (parseFloat(CN01) + parseFloat(CN02) + parseFloat(CN03)) / 2;
                        } else
                        {
                            document.getElementById(subid + '_CN01010105').value = (parseFloat(CN01) + parseFloat(CN02) + parseFloat(CN03));
                        }

                    } else if (trungbinh === 'PGD_TD_TTDX')
                    {
                        console.log('Vao!!');
                        var CN01 = document.getElementById(subid + '_CN01010101').value;
                        var CN02 = document.getElementById(subid + '_CN01010102').value;
                        document.getElementById(subid + '_CN010101').value = (parseFloat(CN01) + parseFloat(CN02)) / 2;

                    } else if (trungbinh === 'TB_PGD_KS_CB')
                    {
                        var CN01 = document.getElementById(subid + '_CN0101010201').value;
                        var CN02 = document.getElementById(subid + '_CN0101010202').value;
                        console.log(" ----------------------------------------" + subid + '_CN0101010201=' + CN01.toString() + ' ~ ' + subid + '_CN0101010202=' + CN02.toString());
                        if (parseFloat(CN01) > 0 && parseFloat(CN02) > 0)
                            document.getElementById(subid + '_CN01010102').value = (parseFloat(CN01) + parseFloat(CN02)) / 2;
                        else
                            document.getElementById(subid + '_CN01010102').value = parseFloat(CN01) > 0 ? parseFloat(CN01) : parseFloat(CN02);
                    }
                    $('.number2').number(true, 2);
                } catch (e) {
//                    swal('Lỗi', 'ERROR evaluateSum ' + e.toString());
                    console.log('ERROR evaluateSum ' + e.toString());
                }

            }

            function laydiemHoanthanh(subid, ma_ct, tongcong)
            {
                console.log('tongcong----------=' + tongcong);
                var diem_max = getvalue('D1_' + ma_ct);
                console.log('111diem_max=' + diem_max + ' ' + subid + '_' + ma_ct + '_' + tongcong);
                if (diem_max == 30)
                {
                    if (tongcong >= 61 && tongcong <= 70)
                    {
                        document.getElementById(subid + '_' + ma_ct).value = 30;
                        document.getElementById(subid + '_' + ma_ct + '01').value = 30;
                    }
                    if (tongcong >= 56 && tongcong < 61)
                    {
                        document.getElementById(subid + '_' + ma_ct).value = 25;
                        document.getElementById(subid + '_' + ma_ct + '02').value = 25;
                    }
                    if (tongcong >= 51 && tongcong < 56)
                    {
                        document.getElementById(subid + '_' + ma_ct).value = 20;
                        document.getElementById(subid + '_' + ma_ct + '03').value = 20;
                    }
                    if (tongcong < 51)
                    {
                        document.getElementById(subid + '_' + ma_ct).value = 15;
                        document.getElementById(subid + '_' + ma_ct + '04').value = 15;
                    }
                } else
                {
                    if (tongcong >= 71 && tongcong <= 80)
                    {
                        document.getElementById(subid + '_' + ma_ct).value = 20;
                        document.getElementById(subid + '_' + ma_ct + '01').value = 20;
                    }
                    if (tongcong >= 66 && tongcong < 71)
                    {
                        document.getElementById(subid + '_' + ma_ct).value = 15;
                        document.getElementById(subid + '_' + ma_ct + '02').value = 15;
                    }
                    if (tongcong >= 61 && tongcong < 66)
                    {
                        document.getElementById(subid + '_' + ma_ct).value = 10;
                        document.getElementById(subid + '_' + ma_ct + '03').value = 10;
                    }
                    if (tongcong < 61)
                    {
                        document.getElementById(subid + '_' + ma_ct).value = 5;
                        document.getElementById(subid + '_' + ma_ct + '04').value = 5;
                    }
                }
            }

            function  isInputMark(id_input_mark_max, id_input) {
                try {
                    var max_mark = getvalue(id_input_mark_max);
                    var mark = getvalue(id_input);
                    if (mark < 0)
                    {
                        swal('Lỗi', 'Bạn không được nhập số điểm là ' + mark.toString() + ' số điểm nhỏ nhất là 0 !', 'error');
                        document.getElementById(id_input).value = 0;
                        document.getElementById(id_input).focus();
                        document.getElementById(id_input).style.backgroundColor = "#DE76DF";
                    }

                    if (mark > max_mark)
                    {
                        swal('Lỗi', 'Bạn không được nhập số điểm lớn hơn ' + max_mark.toString(), 'error');
                        document.getElementById(id_input).value = max_mark;
                        document.getElementById(id_input).focus();
                        document.getElementById(id_input).style.backgroundColor = "#DE76DF";
                    }
                    //else
                    //{
                    //document.getElementById(id_input).style.backgroundColor ="#FFFFFF";
                    //}
                } catch (e)
                {
                    swal('Lỗi', 'ERROR isInputMark ' + e.toString(), 'error');
                }
            }

            function resetvalue(subid, ma_ct)
            {
                try {
                    document.getElementById(subid + '_' + ma_ct + '01').value = 0;
                    document.getElementById(subid + '_' + ma_ct + '02').value = 0;
                    document.getElementById(subid + '_' + ma_ct + '03').value = 0;
                    document.getElementById(subid + '_' + ma_ct + '04').value = 0;
                    document.getElementById(subid + '_' + ma_ct + '05').value = 0;

                } catch (e) {

                }
            }
            function tongcongthuc_08TH(arrMact, subid, congthuc)
            {
                var tong = 0, giatri = 0, cong_dulieu = congthuc;
                var id = subid + '_';

                for (var j = 0; j < arrMact.length; j++)
                {
                    id = subid + '_' + arrMact[j];
                    giatri = getvalue(id);
                    tong += giatri;
                    var rep = new RegExp(arrMact[j], "g");
                    cong_dulieu = cong_dulieu.replace(rep, giatri);
                }
                //console.log('congthuc='+congthuc+' cong_dulieu='+cong_dulieu);
                var tong_congthuc = eval(cong_dulieu);
                return tong_congthuc;
            }
            function getvalue(id_input)
            {
                var value = 0;
                var outvalue = 0;
                try
                {
                    value = document.getElementById(id_input).value;
                    //                    console.log('id_input=' + id_input + ' value=' + value);
                    value = value.replace(/,/g, "");
                    return parseFloat(value);
                } catch (e) {
                    console.log('ERROR=' + e.toString() + " " + id_input);
                    return 0;
                }
            }

            function OnchangeSelect(idselect, subid, ma)
            {
                try {
                    resetvalue(subid, ma);
//                    console.log('Bat dau OnchangeSelect');
                    var e = document.getElementById(idselect);
                    var value_id = e.options[e.selectedIndex].value;
                    var text = e.options[e.selectedIndex].text;
                    var id_input = subid + '_' + ma;
//                    console.log('id_input=' + id_input + ' text=' + text + ' value_id=' + value_id);
                    document.getElementById(id_input).value = text;

                    evaluateSum_col('CHAMDIEMTT_001', subid);

                    document.getElementById(subid + '_' + value_id).value = text; //thiết lập vào đúng vị trí 

                    var giatrid10 = document.getElementById(id_input).value;
                    console.log('id_input=' + id_input + ' ~ text=' + text + '~ value_id=' + value_id + ' ~ giatrid10=' + giatrid10);
                } catch (e) {
                    console.log('ERROR=' + e.toString() + " " + id_input);
                }
            }

            function nhapdiemtru(khoa_diemtru, macb) {
                try
                {
//        var pos_string = laypostreecheck(khoa_cdtt);
//        if (pos_string === '' || pos_string == null)
//        {
//            pos_string = '999999,';
//        }
                    //swal(pos_string);
                    var pheduyet = 'N';
                    var ht1 = screen.availHeight - 300;
                    var wt1 = 950;
                    var left1 = (screen.width / 2) - (wt1 / 2);
                    var top1 = 100;
                    var ngay_bc = $("#ngay_bc_DATE").val();
                    var khoa_cdtt = $("#khoa_cdtt").val();
                    var url = "Nhapdiemtru.action?khoa_cdtt=" + khoa_diemtru + "&ngay_bc=" + ngay_bc + "&macb=" + macb + "&pheduyet=" + pheduyet;

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
            function laypostreecheck(khoa_cdtt)
            {

                var pos_cd = '';
                var idform = 'id_' + khoa_cdtt;//'<s:property value="khoa_cdtt"/>';
                var element = document.forms[idform].elements;
//                 alert('bat dau goi submit idform='+element.length);
                var i = element.length;
                for (var k = 0; k < i; k++)
                {
//                    console.log(element[k].name );
                    if (element[k].name == 'poscd')
                    {
                        if (element[k].checked == true)
                        {
                            if (element[k].value != '999999')
                            {
//                                alert(element[k].value);
                                pos_cd = pos_cd + element[k].value + ',';
                            }
                        }
                    }
                }
//                alert('bat dau goi submit pos_cd='+pos_cd);
                return pos_cd;
            }
            function hienthichitiet(ma, stt, khoa_cdtt) {
                try
                {
                    var khoa_cdtt_action = $("#khoa_cdtt").val();
                    var pos_string = laypostreecheck(khoa_cdtt_action);
                    if (pos_string === '' || pos_string == null)
                    {
                        pos_string = '999999,';
                    }
                    //swal(pos_string);
                    var ht1 = screen.availHeight - 300;
                    var wt1 = 950;
                    var left1 = (screen.width / 2) - (wt1 / 2);
                    var top1 = 100;
                    var ngay_bc = $("#ngay_bc_DATE").val();
                    var macb = $("#tt_cdtt").val();
                    var url = "ChitietChamdiem_CN.action?MACT=" + ma + "&ngay_bc=" + ngay_bc + "&pos_string=" + pos_string + "&khoa_cdtt=" + khoa_cdtt + "&macb=" + macb + "&addedit=" + stt;

                    //$.post(url,param,function(data){});
                    popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                } catch (e)
                {
                    swal('Lỗi', 'Lỗi: ' + e.toString(), 'error');
                }
            }

            function  isInputMark(id_input_mark_max, id_input) {
                try {
                    var max_mark = getvalue(id_input_mark_max);
                    var mark = getvalue(id_input);
                    if (mark < 0)
                    {
                        swal('Lỗi', 'Bạn không được nhập số điểm là ' + mark.toString() + ' số điểm nhỏ nhất là 0 !', 'error');
                        document.getElementById(id_input).value = 0;
                        document.getElementById(id_input).focus();
                        document.getElementById(id_input).style.backgroundColor = "#DE76DF";
                    }

                    if (mark > max_mark)
                    {
                        swal('Lỗi', 'Bạn không được nhập số điểm lớn hơn ' + max_mark.toString(), 'error');
                        document.getElementById(id_input).value = max_mark;
                        document.getElementById(id_input).focus();
                        document.getElementById(id_input).style.backgroundColor = "#DE76DF";
                    }
                    //else
                    //{
                    //document.getElementById(id_input).style.backgroundColor ="#FFFFFF";
                    //}
                } catch (e)
                {
                    swal('Lỗi', 'ERROR isInputMark ' + e.toString(), 'error');
                }
            }
        </script>       
    </head>
    <body>
        <s:form id="id_sv_%{khoa_cdtt}" action="SAVE_%{khoa_cdtt}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />"  
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>

            <div id="divTitle">
                <font color="red">(Cá nhân tự đánh giá)</font> PL08A/ĐGXL - TIÊU CHÍ ĐÁNH GIÁ MỨC ĐỘ HOÀN THÀNH NHIỆM VỤ ĐỐI VỚI CÁ NHÂN <font color="red">(Trạng thái: <s:property  value="TT_DUYET"/>)</font>
            </div>                        
            <s:hidden name="khoa_cdtt"/>
            <s:hidden name="tt_cdtt"/>



            <s:iterator value="poscd" status="row">
                <s:hidden name="poscd[%{#row.index}]" />
            </s:iterator>       
            <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">                          
                <tr height="23">
                    <th rowspan="2" class="TD_THUTU">TT</th>
                    <th rowspan="2" class="TD_CHITIEU">Chỉ tiêu</th>  
                    <th rowspan="2"  class="TD_SOLUONG">Điểm tối đa</th>  
                    <th rowspan="2"  class="TD_SOLUONG">% điểm</th> 
                    <th rowspan="2"  class="TD_SOLUONG">Đơn vị tính</th> 
                    <!--<th rowspan="2" class="TD_SOLUONG">Mã</th>--> 
                    <th colspan="3" class="TD_SOLUONG">Cá nhân tự đánh giá</th> 
                    <!--<th colspan="3" >Lãnh đạo phụ trách đánh giá</th>-->                     
                </tr>  
                <tr height="22">                        
                    <th class="TD_SOLUONG">Kết quả thực hiện nhiệm vụ trong tháng đạt: Tỷ lệ; mức độ hoàn thành công việc; các lỗi sai sót tồn tại</th>
                    <th class="TD_SOLUONG">Số điểm đạt (+); Số điểm phải trừ (-)</th>
                    <th class="TD_GHICHU">Ghi chú</th>
                    <!--                    <th class="TD_SOLUONG">Kết quả thực hiện nhiệm vụ trong tháng đạt: Tỷ lệ; mức độ hoàn thành công việc; các lỗi sai sót tồn tại</th>
                                        <th class="TD_SOLUONG">Số điểm đạt (+); Số điểm phải trừ (-)</th>
                                        <th class="TD_GHICHU">Ghi chú</th>                                                 -->
                </tr> 
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
                    <tr height="16" class="<s:property  value="D30" />"> 
                        <td align = "center" class="TD_THUTU">
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                            <input type="hidden" value="<s:property  value="KHOA" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].KHOA" class="KHOA_CHUCNANG_NHIEMVU"/>

                            <input type="hidden" value="<s:property  value="THUTU" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU"/> 
                            <input type="hidden" value="<s:property  value="NHAPTAY" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY"/> 
                            <input type="hidden" value="<s:property  value="D3" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"/>
                            <input type="hidden" value="<s:property  value="D14" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" />  
                            <input type="hidden" value="<s:property  value="D15" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"/> 
                            <input type="hidden" value="<s:property  value="D17" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" /> 
                            <input type="hidden" value="<s:property  value="D16" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" /> 

                            <input type="hidden"  value="<s:property  value="TT_HIENTHI" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0"
                                   readonly="true"/>
                            <s:if test="D28.startsWith('DT_')">
                                <a href="javascript:nhapdiemtru('<s:property value="D28"/>','<s:property value='D14'/>')" class="SOKU linkKh">
                                    <s:property value='TT_HIENTHI'/>
                                </a>
                            </s:if>  
                            <s:else>
                                <a href="javascript:hienthichitiet('<s:property value="MA"/>',2,'<s:property value='KHOA'/>')" class="SOKU linkKh <s:property value='D19'/>">
                                    <s:property value='TT_HIENTHI'/>
                                </a>                               
                            </s:else>  

                        </td>
                        <s:if test="NHAPTAY.equalsIgnoreCase('N')">                        
                            <td align = "left" class="TD_CHITIEU">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />" title="<s:property  value="D32" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH <s:property value='D19'/>"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />"  id="D1_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0 <s:property value='D19'/>"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0 <s:property value='D19'/>" readonly="true"/>
                            </td>  
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0 <s:property value='D19'/>" readonly="true"/>
                            </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number2 <s:property value='D19'/>" readonly="true"/>
                            </td>
                            <td align = "right" class="TD_SOLUONG">
                                <s:if test="D28.equalsIgnoreCase('TINH_DONG_LUU')">
                                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D40" id="D40_<s:property  value="MA" />" 
                                            class="TEN_KH " onchange="OnchangeSelect('D40_<s:property  value="MA" />', 'D10', '<s:property  value="MA" />');" disabled="true">
                                        <option value="<s:property  value="MA" />01" <s:if test="D10.equalsIgnoreCase('10')"> selected </s:if> >10</option>
                                        <option value="<s:property  value="MA" />02" <s:elseif test="D10.equalsIgnoreCase('7')"> selected </s:elseif>>7</option>
                                        <option value="<s:property  value="MA" />03" <s:elseif test="D10.equalsIgnoreCase('5')"> selected </s:elseif>>5</option>     
                                        <option value="<s:property  value="MA" />04" <s:elseif test="D10.equalsIgnoreCase('3')"> selected </s:elseif>>3</option>   
                                        <option value="<s:property  value="MA" />05" <s:elseif test="D10.equalsIgnoreCase('0')"> selected </s:elseif>>0</option>   	
                                        </select>
                                        <input type="hidden" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH <s:property value='D19'/>"/>
                                </s:if>
                                <s:elseif test="D28.equalsIgnoreCase('TINH_DONG_LUU1')">
                                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D40" id="D40_<s:property  value="MA" />"
                                            class="TEN_KH " onchange="OnchangeSelect('D40_<s:property  value="MA" />', 'D10', '<s:property  value="MA" />');" disabled="true">
                                        <option value="<s:property  value="MA" />01" <s:if test="D10.equalsIgnoreCase('10')"> selected </s:if> >10</option>
                                        <option value="<s:property  value="MA" />02" <s:elseif test="D10.equalsIgnoreCase('8')"> selected </s:elseif>>8</option>
                                        <option value="<s:property  value="MA" />03" <s:elseif test="D10.equalsIgnoreCase('6')"> selected </s:elseif>>6</option>     
                                        <option value="<s:property  value="MA" />04" <s:elseif test="D10.equalsIgnoreCase('4')"> selected </s:elseif>>4</option>   
                                        <option value="<s:property  value="MA" />05" <s:elseif test="D10.equalsIgnoreCase('0')"> selected </s:elseif>>0</option>   	
                                        </select>
                                        <input type="hidden" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH <s:property value='D19'/>"/>
                                </s:elseif>
                                <s:elseif test="D28.equalsIgnoreCase('TINH_DONG_LUU2')">
                                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D40" id="D40_<s:property  value="MA" />"  
                                            class="TEN_KH " onchange="OnchangeSelect('D40_<s:property  value="MA" />', 'D10', '<s:property  value="MA" />');" disabled="true">
                                        <option value="<s:property  value="MA" />01" <s:if test="D10.equalsIgnoreCase('10')"> selected </s:if> >10</option>
                                        <option value="<s:property  value="MA" />02" <s:elseif test="D10.equalsIgnoreCase('7')"> selected </s:elseif>>7</option>
                                        <option value="<s:property  value="MA" />03" <s:elseif test="D10.equalsIgnoreCase('5')"> selected </s:elseif>>5</option>     
                                        <option value="<s:property  value="MA" />04" <s:elseif test="D10.equalsIgnoreCase('3')"> selected </s:elseif>>3</option>   
                                        <option value="<s:property  value="MA" />05" <s:elseif test="D10.equalsIgnoreCase('2')"> selected </s:elseif>>2</option>   
                                        <option value="<s:property  value="MA" />06" <s:elseif test="D10.equalsIgnoreCase('1')"> selected </s:elseif>>1</option> 
                                        <option value="<s:property  value="MA" />07" <s:elseif test="D10.equalsIgnoreCase('0')"> selected </s:elseif>>0</option> 	
                                        </select>
                                        <input type="hidden" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH <s:property value='D19'/>"/>
                                </s:elseif>
                                <%--<s:property  value="TINH_DONG_LUU20" />--%>
                                <s:elseif test="D28.equalsIgnoreCase('TINH_DONG_LUU20')">
                                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D40" id="D40_<s:property  value="MA" />"  
                                            class="TEN_KH " onchange="OnchangeSelect('D40_<s:property  value="MA" />', 'D10', '<s:property  value="MA" />');" disabled="true">
                                        <option value="<s:property  value="MA" />01" <s:if test="D10.equalsIgnoreCase('2')"> selected </s:if> >2</option>
                                        <option value="<s:property  value="MA" />02" <s:elseif test="D10.equalsIgnoreCase('1')"> selected </s:elseif>>1</option>
                                        <option value="<s:property  value="MA" />03" <s:elseif test="D10.equalsIgnoreCase('0')"> selected </s:elseif>>0</option> 	
                                        </select>
                                        <input type="hidden" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH <s:property value='D19'/>"/>
                                </s:elseif>
                                <s:else>
                                    <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH <s:property value='D19'/>" readonly="true"/>
                                </s:else>
                            </td>
                            <td align = "left" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH <s:property value='D19'/>" readonly="true"/>
                            </td>                                                                                                        

                        </s:if>

                        <s:if test="NHAPTAY.equalsIgnoreCase('Y')">                         

                            <td align = "left" class="TD_CHITIEU">
                                <input type="text" id="TEN_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="TEN" />" title="<s:property  value="D32" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH <s:property value='D19'/>"
                                       readonly="readonly" />
                            </td>
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D1" />" id="D1_<s:property value='MA'/>"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH D0 <s:property value='D19'/>"
                                       readonly="readonly"
                            </td>

                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0 <s:property value='D19'/>" readonly="true"/>
                            </td>                            
                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D4" />" id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH D0" readonly="true"/>
                            </td> 

                            <td align = "right" class="TD_SOLUONG">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="number2 TEN_KH CONGCAP_D5 <s:property value='D19'/>"
                                       onblur="if (this.value == '') {
                                                   this.value = 0
                                               }
                                               ;
                                               evaluateSum_row('<s:property value='MA'/>', '<s:property value='D29'/>');
                                               evaluateSum_col('CHAMDIEMTT_001', 'D10');

                                       " 
                                       <s:if test="D29.equalsIgnoreCase('N')"> readonly="readonly" </s:if>         
                                           />

                                </td>

                                <td align = "right" class="TD_SOLUONG">
                                <s:if test="D28.equalsIgnoreCase('TINH_DONG_LUU')">
                                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D40" id="D40_<s:property  value="MA" />" 
                                            class="TEN_KH " onchange="OnchangeSelect('D40_<s:property  value="MA" />', 'D10', '<s:property  value="MA" />');">
                                        <option value="<s:property  value="MA" />01" <s:if test="D10.equalsIgnoreCase('10')"> selected </s:if> >10</option>
                                        <option value="<s:property  value="MA" />02" <s:elseif test="D10.equalsIgnoreCase('7')"> selected </s:elseif>>7</option>
                                        <option value="<s:property  value="MA" />03" <s:elseif test="D10.equalsIgnoreCase('5')"> selected </s:elseif>>5</option>     
                                        <option value="<s:property  value="MA" />04" <s:elseif test="D10.equalsIgnoreCase('3')"> selected </s:elseif>>3</option>   
                                        <option value="<s:property  value="MA" />05" <s:elseif test="D10.equalsIgnoreCase('0')"> selected </s:elseif>>0</option>   	
                                        </select>
                                        <input type="hidden" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH <s:property value='D19'/>"/>
                                </s:if>
                                <s:elseif test="D28.equalsIgnoreCase('TINH_DONG_LUU1')">
                                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D40" id="D40_<s:property  value="MA" />"
                                            class="TEN_KH " onchange="OnchangeSelect('D40_<s:property  value="MA" />', 'D10', '<s:property  value="MA" />');">
                                        <option value="<s:property  value="MA" />01" <s:if test="D10.equalsIgnoreCase('10')"> selected </s:if> >10</option>
                                        <option value="<s:property  value="MA" />02" <s:elseif test="D10.equalsIgnoreCase('8')"> selected </s:elseif>>8</option>
                                        <option value="<s:property  value="MA" />03" <s:elseif test="D10.equalsIgnoreCase('6')"> selected </s:elseif>>6</option>     
                                        <option value="<s:property  value="MA" />04" <s:elseif test="D10.equalsIgnoreCase('4')"> selected </s:elseif>>4</option>   
                                        <option value="<s:property  value="MA" />05" <s:elseif test="D10.equalsIgnoreCase('0')"> selected </s:elseif>>0</option>   	
                                        </select>
                                        <input type="hidden" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH <s:property value='D19'/>"/>
                                </s:elseif>
                                <s:elseif test="D28.equalsIgnoreCase('TINH_DONG_LUU2')">
                                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D40" id="D40_<s:property  value="MA" />"  
                                            class="TEN_KH " onchange="OnchangeSelect('D40_<s:property  value="MA" />', 'D10', '<s:property  value="MA" />');">
                                        <option value="<s:property  value="MA" />01" <s:if test="D10.equalsIgnoreCase('10')"> selected </s:if> >10</option>
                                        <option value="<s:property  value="MA" />02" <s:elseif test="D10.equalsIgnoreCase('7')"> selected </s:elseif>>7</option>
                                        <option value="<s:property  value="MA" />03" <s:elseif test="D10.equalsIgnoreCase('5')"> selected </s:elseif>>5</option>     
                                        <option value="<s:property  value="MA" />04" <s:elseif test="D10.equalsIgnoreCase('3')"> selected </s:elseif>>3</option>   
                                        <option value="<s:property  value="MA" />04" <s:elseif test="D10.equalsIgnoreCase('2')"> selected </s:elseif>>2</option>   
                                        <option value="<s:property  value="MA" />04" <s:elseif test="D10.equalsIgnoreCase('1')"> selected </s:elseif>>1</option> 
                                        <option value="<s:property  value="MA" />04" <s:elseif test="D10.equalsIgnoreCase('0')"> selected </s:elseif>>0</option> 	
                                        </select>
                                        <input type="hidden" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH <s:property value='D19'/>"/>
                                </s:elseif>
                                <s:elseif test="D28.equalsIgnoreCase('TINH_DONG_LUU20')">
                                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D40" id="D40_<s:property  value="MA" />"  
                                            class="TEN_KH " onchange="OnchangeSelect('D40_<s:property  value="MA" />', 'D10', '<s:property  value="MA" />');">
                                        <option value="<s:property  value="MA" />01" <s:if test="D10.equalsIgnoreCase('2')"> selected </s:if> >2</option>
                                        <option value="<s:property  value="MA" />02" <s:elseif test="D10.equalsIgnoreCase('1')"> selected </s:elseif>>1</option>
                                        <option value="<s:property  value="MA" />03" <s:elseif test="D10.equalsIgnoreCase('0')"> selected </s:elseif>>0</option> 	
                                        </select>
                                        <input type="hidden" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH <s:property value='D19'/>"/>
                                </s:elseif>
                                <s:else>
                                    <input type="text" value="<s:property  value="D10" />" id="D10_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="number2 TEN_KH CONGCAP_D10 <s:property value='D19'/>"
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   isInputMark('D1_<s:property value='MA'/>', 'D10_<s:property value='MA'/>');
                                                   evaluateSum_col('CHAMDIEMTT_001', 'D10');" 
                                           <s:if test="D29.equalsIgnoreCase('Y')"> readonly="readonly" </s:if>   />
                                </s:else>
                            </td>
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class=" TEN_KH <s:property value='D19'/>"/>
                            </td>                                                                                                                                                                                           
                        </s:if>                   
                        <td class="hideColumn"><input type="text" value="<s:property value='D3'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                        <td class="hideColumn"><input type="text" value="<s:property value='D15'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                        <td class="hideColumn"><input type="text" value="<s:property value='MA'/>" name="MA_CT" class="MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                        <td class="hideColumn"><input type="hidden" id="D28_<s:property  value="MA" />" value="<s:property  value="D28" />" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" class="D28_DIEM"/></td>

                    </tr>        
                </s:iterator>
            </table>                                                                    

            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>                    
        <div id="luu_thanhcong"></div>
    </body>
</html>
