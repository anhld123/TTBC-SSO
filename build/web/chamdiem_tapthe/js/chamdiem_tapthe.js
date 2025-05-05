/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//<editor-fold defaultstate="collapsed" desc="Cong cấp QUYENNV">


function calculatorMark_0712() {
    try {
        var max_D12I = getvalue("D12_CDTT02");
        var max_D21I = getvalue("D21_CDTT02");
        var max_D22I = getvalue("D22_CDTT02");
        //            console.log('max_D10I=' + max_D10I + 'max_D19I=' + max_D19I);
        var giatri1 = (400 - max_D12I) * 0.4 * max_D21I / 100
        var giatri2 = (400 - max_D12I) * 0.6 * max_D22I / 100
        document.getElementById("D12_CDTT04").value = giatri1;
        document.getElementById("D12_CDTT05").value = giatri2;
        $('.number2').number(true, 2);
    } catch (e) {
        swal('Lỗi', 'ERROR evaluateSum ' + e.toString());
    }

}

function calculatorMark_0717() {
    try {
        var max_D17I = getvalue("D17_CDTT02");
        var max_D23I = getvalue("D23_CDTT02");
        var max_D24I = getvalue("D24_CDTT02");
        //            console.log('max_D10I=' + max_D10I + 'max_D19I=' + max_D19I);
        var giatri1 = (400 - max_D17I) * 0.4 * max_D23I / 100
        var giatri2 = (400 - max_D17I) * 0.6 * max_D24I / 100
        document.getElementById("D17_CDTT04").value = giatri1;
        document.getElementById("D17_CDTT05").value = giatri2;
        $('.number2').number(true, 2);
    } catch (e) {
        swal('Lỗi', 'ERROR evaluateSum ' + e.toString());
    }

}
//</editor-fold>


//<editor-fold defaultstate="collapsed" desc="Hàm cộng cấp cho chỉ tiêu">

/**
 * 
 * @param {type} table_id ID của bảng trong html
 * @param {type} subid D4, D5 của id input cần cộng tổng
 * @returns {undefined}
 */


function evaluateSum(table_id, subid) {
    try {
        //                alert('vao tinh tong');
        var temp = 0;
        var kh_capht = ".KH_CAPHT";
        var kh_congthuc = ".KH_CONGTHUC";
        var kh_ma_ct = ".MA_CT";
        //Thu tu cua i tinh tu 0
        var arrCapht = [6.0, 5.0, 4.0, 3.0, 2.0, 1.0]; //Luu cac cot cua du lieu can tinh toan
        var rowCount = $("#" + table_id + " td").closest("tr").length;
        //                    alert('rowCount='+rowCount);
        //duyệt cấp cộng tổng hợp
        for (k = 0; k < arrCapht.length; k++)
        {//duyệt số row của bảng để lấy ra công thức.

            for (i = 0; i < rowCount; i++)
            {//nếu cấp báo cáo bằng với danh sách mảng của câp báo cáo ở trên và công thức khác null hoặc rỗng
                if ($(kh_capht).eq(i).val() == arrCapht[k] && $(kh_congthuc).eq(i).val().length > 0)
                {
                    //lấy ra công thức

                    //                                var tong_dc = sum_mact(valNew, kh_dc, table_id);
                    var ma_ct = $(kh_ma_ct).eq(i).val();
                    //                                console.log('ma_ct=' + ma_ct + ' tongcong=' + tongcong);
                    //Lấy ra khóa của báo cáo
                    var khoa = document.getElementById('khoa').value;

                    var Grade = document.getElementById('Grade').value;

                    var RULEUSER = document.getElementById('RULEUSER').value;
                    if (khoa === 'CDTT_PGD' && (ma_ct.indexOf('CDTT10') >= 0 || ma_ct.indexOf('CDTT11') >= 0) && Grade === '1')
                        continue;
                    if (khoa === 'CDTT_CN' && (ma_ct.indexOf('CDTT10') >= 0 || ma_ct.indexOf('CDTT11') >= 0) && Grade === '2' && (subid === "D10" || subid === "D12" || subid === "D16"))
                        continue;
//                    if (khoa === 'CDTT_CN' && ma_ct.indexOf('CDTT10') >= 0 && Grade === '3' && (subid === "D10" || subid === "D12" || subid === "D16"))
//                        continue;


                    var congthuc = $(kh_congthuc).eq(i).val();
//                    console.log('-------------- congthuc = ' + congthuc);
                    //cắt công thức đưa về mảng
                    var valNew = congthuc.split('+');
                    var tongcong = tongcongthuc(valNew, subid);
                    console.log('arrCapht[k]=' + arrCapht[k] + ' ma_ct=' + ma_ct + ' tongcong=' + tongcong + ' RULEUSER=' + RULEUSER + ' subid=' + subid);

                    if (ma_ct === 'CDTT99' && (subid === "D4" || subid === "D5" || subid === "D11" || subid === "D16"))
                        continue;
                    else if (ma_ct === 'CDTT13' && (subid === "D5" || subid === "D11" || subid === "D16"))
                        continue;
                    else if (Grade === '3' && (ma_ct === 'CDTT1001' || ma_ct === 'CDTT1002') && RULEUSER != 9 && (subid === "D10" || subid === "D12" || subid === "D16"))
                    {
                        console.log(' ma_ct=' + ma_ct);
                        continue;
                    } else
                        document.getElementById(subid + '_' + ma_ct).value = tongcong;

                }
            }

        }
        $('.number2').number(true, 2);
        $('.number3').number(true, 3);
        $('.number5').number(true, 5);
    } catch (e) {
        swal('Lỗi', 'ERROR evaluateSum ' + e.toString());
    }

}
function evaluateSum_Mapgd(table_id, subid, Mapgd) {
    try {
        //                alert('vao tinh tong');
        var temp = 0;
        var kh_capht = ".KH_CAPHT";
        var kh_congthuc = ".KH_CONGTHUC";
        var kh_ma_ct = ".MA_CT";
        //Thu tu cua i tinh tu 0
        var arrCapht = [6.0, 5.0, 4.0, 3.0, 2.0, 1.0]; //Luu cac cot cua du lieu can tinh toan
        var rowCount = $("#" + table_id + " td").closest("tr").length;
        //                    alert('rowCount='+rowCount);
        //duyệt cấp cộng tổng hợp
        for (k = 0; k < arrCapht.length; k++)
        {//duyệt số row của bảng để lấy ra công thức.

            for (i = 0; i < rowCount; i++)
            {//nếu cấp báo cáo bằng với danh sách mảng của câp báo cáo ở trên và công thức khác null hoặc rỗng
                if ($(kh_capht).eq(i).val() == arrCapht[k] && $(kh_congthuc).eq(i).val().length > 0)
                {
                    //lấy ra công thức

                    //                                var tong_dc = sum_mact(valNew, kh_dc, table_id);
                    var ma_ct = $(kh_ma_ct).eq(i).val();
                    //                                console.log('ma_ct=' + ma_ct + ' tongcong=' + tongcong);
                    //Lấy ra khóa của báo cáo
                    var khoa = document.getElementById('khoa').value;

                    var Grade = document.getElementById('Grade').value;

                    var RULEUSER = document.getElementById('RULEUSER').value;
//                    console.log('arrCapht[k]=' + arrCapht[k] + ' ma_ct=' + ma_ct + ' tongcong=' + tongcong);
                    if (khoa === 'CDTT_PGD' && ma_ct.indexOf('CDTT10') >= 0 && Grade === '1')
                        continue;
                    if (khoa === 'CDTT_CN' && ma_ct.indexOf('CDTT10') >= 0 && Grade === '2' && (subid === "D10" || subid === "D12" || subid === "D16"))
                        continue;
//                    if (khoa === 'CDTT_CN' && ma_ct.indexOf('CDTT10') >= 0 && Grade === '3' && (subid === "D10" || subid === "D12" || subid === "D16"))
//                        continue;


                    var congthuc = $(kh_congthuc).eq(i).val();
//                    console.log('-------------- congthuc = ' + congthuc);
                    //cắt công thức đưa về mảng
                    var valNew = congthuc.split('+');

                    valNew = addMapgdToMACT(valNew, Mapgd);

                    var tongcong = tongcongthuc(valNew, subid);
//                    console.log('arrCapht[k]=' + arrCapht[k] + ' ma_ct=' + ma_ct + ' tongcong=' + tongcong + ' RULEUSER=' + RULEUSER + ' subid=' + subid);

                    if (ma_ct === 'CDTT99' && (subid === "D4" || subid === "D5" || subid === "D11" || subid === "D16"))
                        continue;
                    else if (Grade === '3' && (ma_ct === 'CDTT1001' || ma_ct === 'CDTT1002') && RULEUSER != 9 && (subid === "D10" || subid === "D12" || subid === "D16"))
                    {
                        console.log(' ma_ct=' + ma_ct);
                        continue;
                    } else
                    {
                        if (subid + '_' + ma_ct === 'D11_CDTT11')
                        {
                            document.getElementById(subid + '_' + ma_ct + '_' + Mapgd).value = tongcong / 3;
                        } else {
                            document.getElementById(subid + '_' + ma_ct + '_' + Mapgd).value = tongcong;
                        }

                    }
                }
            }

        }
        $('.number2').number(true, 2);
    } catch (e) {
        swal('Lỗi', 'ERROR evaluateSum ' + e.toString());
    }

}
function addMapgdToMACT(arrMact, mapgd)
{
    for (var j = 0; j < arrMact.length; j++)
    {
        arrMact[j] = arrMact[j] + '_' + mapgd;
    }
    return arrMact;
}
function tongcongthuc(arrMact, subid)
{
    var tong = 0;
    var id = subid + '_';
    for (var j = 0; j < arrMact.length; j++)
    {
        id = subid + '_' + arrMact[j];
        tong += getvalue(id);
    }
    return tong;
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
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Hàm kiểm tra số điểm nhập không được lớn hơn số điểm quy định">

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
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Cho hiển thị chi tiết chỉ tiêu">
function hienthichitiet(ma, stt, khoa_cdtt) {
    try
    {

        var pos_string = laypostreecheck(khoa_cdtt);
        if (pos_string === '' || pos_string == null)
        {
            pos_string = '999999,';
        }
        //swal(pos_string);
        var ht1 = screen.availHeight - 300;
        var wt1 = 1100;
        var left1 = (screen.width / 2) - (wt1 / 2);
        var top1 = 100;
        var ngay_bc = $("#ngay_bc_DATE").val();
        var khoa_cdtt = $("#khoa_cdtt").val();
        var url = "ChitietChamdiem.action?MACT=" + ma + "&ngay_bc=" + ngay_bc + "&pos_string=" + pos_string + "&khoa_cdtt=" + khoa_cdtt + "&addedit=" + stt;

        window.dataChange = false;
        //$.post(url,param,function(data){});
        var popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        window.refreshData = function () {
            try {
                //alert(dataChange);
                if (dataChange === true) {
                    $("#loadDatatmp").trigger("click");
                }
            } catch (err) {
                alert(err);
                $("#loadDatatmp").trigger("click");
            }
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

//</editor-fold>

