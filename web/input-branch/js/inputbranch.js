/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//<editor-fold defaultstate="collapsed" desc="Cho nút lưu cấu hình mẫu và tham số đi kèm">

/**
 * Cho nut lưu dữ liệu khi tạo mẫu mới
 * @returns {undefined}
 */
function isCheckInput()
{
    $("#loadingImageDiv").show();
    try
    {
        var gradeid = '';
        $('input:checkbox[name=rptGrade]:checked').each(function () {
            //                    allVals.push($(this).val());
            gradeid = gradeid + $(this).val() + ',';
            //                    alert(gradeid);
        });
        //                alert(gradeid);

        var khoa = $.trim($("#khoa").val());
        if (khoa == '' || khoa == null)
        {
            //alert('Bạn phải nhập khóa lưu dữ liệu cho mẫu này');
            swal('Lỗi khi lưu', 'Bạn phải nhập khóa lưu dữ liệu cho mẫu này !', 'error');
            return;
        }

        var tenmau = $.trim($("#tenmau").val());
        if (tenmau == '' || tenmau == null)
        {
//            alert('Bạn phải điền tên cho mẫu cần khai báo');
            swal('Lỗi khi lưu', 'Bạn phải điền tên cho mẫu cần khai báo !', 'error');
            return;
        }

        var donvitinh = $.trim($("#id_donvitinh").val());
        if (donvitinh == '' || donvitinh == null)
        {
//            alert('Bạn phải điền tên cho mẫu cần khai báo');
            swal('Lỗi khi lưu', 'Bạn phải chọn đơn vị tính !', 'error');
            return;
        }
        var copydl = $.trim($("#copydl_id").val());
//        if (copydl == '' || copydl == null)
//        {
////            alert('Bạn phải điền tên cho mẫu cần khai báo');
//            swal('Lỗi khi lưu', 'Bạn phải chọn đơn vị tính !', 'error');
//            return;
//        }
        //alert('copydl='+copydl);
        if (gradeid == '-1' || gradeid == null || gradeid == '' || gradeid == ' ')
        {
//            alert('Bạn chọn cấp nhập dữ liệu');
            swal('Lỗi khi lưu', 'Bạn chọn cấp nhập dữ liệu !', 'error');
            return;
        }
        $('#divExportReportQuery').text('');
        //$("#idAbc").click();
        var sdata = {
            "khoa": khoa,
            "tenmau": tenmau,
            "donvitinh": donvitinh,
            "grade_id": gradeid,
            "copydl": copydl
        };
        var data1 = JSON.stringify(sdata);
        $.ajax({
            url: 'saveFormName1.action',
            data: $("#addform").serialize(),
            dataType: 'json',
//            contentType: 'application/json',
            type: 'POST',
            async: true,
            success: function (data) {
                // alert(JSON.stringify(data));
                var mass = data.msg;

                if (mass == '' || mass == null)
                {
//                    alert('Bạn đã lưu mẫu thành công ! ');
                    swal('Lưu thành công', 'Bạn đã lưu mẫu thành công !', 'success');
                    $('#id_showAllMau').trigger('click');
                    //redirect đến trang cấu hình mẫu luôn
                    $("#containBcttv").text('');
                } else
                {
                    swal('Lỗi', data.msg, 'error');
                    //alert(data.msg);
                    $('#divExportReportQuery').append('<h3 style="color: red">Lỗi bạn chưa lưu được mẫu!<p style="color: blue">' + mass + '</h3>');
                }
            },
            error: function (data) {
                var mass = data.responseText;
                swal('Lỗi', mass, 'error');
                //var dl=JSON.parse(mass);
                //alert(dl.msg);
                //alert('Lỗi: '+mass);
                $('#divExportReportQuery').append('<h3 style="color: red">Lỗi bạn chưa lưu được mẫu!<p style="color: blue">' + mass + '</h3>');
            }
        });
        $("#loadingImageDiv").hide();
    } catch (e)
    {
        $("#loadingImageDiv").hide();
        swal('Lỗi', 'Xin liên hệ với quản trị để được khắc phục !. ' + e.toString(), 'error');
        //alert(e.toString());
    }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Cho thêm và xóa tham số">

var max_row = 0;

function addRow(indx) {
    try {
        //                sleep(1000);
        var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
        //
        var collection = $(".motathamsoclass");
        var demthamso = collection.length;
        //alert('Vào thêm row '+indx);
        var table = document.getElementById("ThemthamsoTable");
        var rowCount = table.rows.length - 1; //Dem so dong cua bang
        //console.log('indem=' + demthamso);
        if (demthamso > 9)
        {
            swal('Lỗi thêm tham số', 'Bạn chỉ thêm được 10 tham số !', 'error');
            return;
        }

        rowCount = index;
        //console.log('1.  max_row=' + max_row + ' rowCount=' + rowCount);
        if (max_row < rowCount)
            max_row = rowCount;
        else
        {
            max_row++;
            rowCount = max_row;
        }
        //console.log('2.  rowCount='+rowCount+' indx='+index);
        index = rowCount;

        //console.log('3.  rowCount='+rowCount+' indx='+index);
        //console.log('2.  max_row=' + max_row + ' rowCount=' + rowCount);
        var newTr = '<tr align="center"  style="background: #c5dbec">\n\
            <td colspan="4">\n\
            <label>Mô tả tham số:</label>\n\
                    &nbsp;&nbsp; \n\
            <input type="text" name="MOTA_THAMSO_' + index + '" size="50" value="" id="MOTA_ID_' + index + '" placeholder="Nhập tên hiển thị cho tham số" class="motathamsoclass" >\n\
            <label>Chọn loại tham số:</label>\n\
                    &nbsp;&nbsp; \n\
            <select name="LOAITSO_' + index + '" id="LOAITSO_ID_' + index + '" \n\
            onchange="addRowPara(\'LOAITSO_ID_' + index + '\',' + index + ',this.parentNode.parentNode.rowIndex,\'HIDDEN_ID_' + index + '\')">\n\
                    <option value="D">D -&gt; Ngày tháng năm</option>\n\
                    <option value="L">L -&gt; Danh mục</option>\n\
                    <option value="N">N -&gt; Kiểu số</option>\n\
                    <option value="T">T -&gt; Kiểu text</option>\n\
            </select>\n\
            </td>\n\
            <td><input type="button" value="Xóa tham số" onclick="deleteRow(this.parentNode.parentNode.rowIndex,\'HIDDEN_ID_' + index + '\')" class="metroButtonStyle"/>\n\
            </td>\n\
        </tr>';
        $($('table#ThemthamsoTable tr')[indx]).before(newTr);
    } catch (e) {
        swal('Lỗi', 'Xin liên hệ với quản trị để được khắc phục !. ' + e.toString(), 'error');
    }
}

function deleteRow(indx, id_hidden) {
    try {
        var table = document.getElementById("ThemthamsoTable");
        var rowCount = table.rows.length - 1; //Dem so dong cua bang
        if (max_row < rowCount)
            max_row = rowCount;
        //                alert('max_row='+max_row+' rowCount='+rowCount);

        //Kiểm tra nếu là kiểu L thì xóa dữ liệu bên dưới dòng cần xóa
        var giatri_list = $.trim($("#" + id_hidden).val());
//    alert('HIDDEN_ID='+id_hidden+' giatri_list='+giatri_list);
        if (giatri_list != null && giatri_list == 'L')
        {
            table.deleteRow(indx + 1);
        }
        table.deleteRow(indx);
    } catch (e) {
        swal('Lỗi', 'Xin liên hệ với quản trị để được khắc phục !. ' + e.toString(), 'error');
    }
}

/**
 * 
 * @param {type} id tham số của list các tham số D, L, N, T
 * @param {type} indx index row (số thứ tự của row trong bảng)
 * @param {type} id_hidden (ID giá trị ẩn)
 * @returns {undefined}
 */
function addRowPara(id, indx, vitrithem, id_hidden)
{
    try {
        var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
        //alert('Vào thêm row '+indx);
        var table = document.getElementById("ThemthamsoTable");
        var rowCount = table.rows.length - 1; //Dem so dong cua bang

        if (max_row < rowCount)
            max_row = rowCount;
        else
        {
            max_row++;
            rowCount = max_row;
        }

        var giatri_para = $.trim($("#" + id).val());
        var giatri_list = $.trim($("#" + id_hidden).val());
        if (giatri_para === 'L')//thêm tham số
        {
            if (giatri_list != null && giatri_list == 'L')
            {
                return;
            }
            var idtmp = index > 0 ? index - 1 : index;
//        alert('vao thay doi kiểu dữ liệu là List ' + giatri_para);
            var newTr = '<tr style="background: #E2E8C9" align="center">\n\
                    <td colspan="5">\n\
                        <input type="hidden" name="" value="' + giatri_para + '" id="' + id_hidden + '">\n\
                        <label>Tên bảng: </label>\n\
                        <input type="text" name="BANGSL_' + indx + '" value="" id="BANGSL_ID_' + indx + '" style="width:180px" placeholder="DMPOS"> &nbsp;&nbsp;\n\
                        <label>Cột hiển thị: </label>\n\
                        <input type="text" name="COTHIENTHI_' + indx + '" value="" id="COTHIENTHI_ID_' + indx + '" style="width:100px"  placeholder="PO_MA||\' -> \'||PO_TEN">&nbsp;&nbsp;\n\
                        <label>Cột tham số: </label>\n\
                        <input type="text" name="COTTSO_' + indx + '" value="" id="COTTSO_ID_' + indx + '" style="width:100px" placeholder="PO_MA">&nbsp;&nbsp;\n\
                        <label>Điều kiện lọc: </label>\n\
                        <input type="text" name="DKLOC_' + indx + '" value="" id="DKLOC_ID_' + indx + '" style="width:100px" placeholder="PO_MACN=\'002721\'">&nbsp;&nbsp;\n\
                        <label>Cột sắp xếp: </label>\n\
                        <input type="text" name="DKSAPXEP_' + indx + '" value="" id="DKSAPXEP_ID_' + indx + '" style="width:100px" placeholder="PO_MA">&nbsp;&nbsp;\n\
                    </td>\n\
                </tr>';
            $($('table#ThemthamsoTable tr')[vitrithem]).after(newTr);
        } else
        {
            //xóa tham số HIDDEN_DEL_ID_
            //var HIDDEN_ID='HIDDEN_ID_'+indx;

//        alert('HIDDEN_ID='+id_hidden+' giatri_list='+giatri_list);
            if (giatri_list != null && giatri_list == 'L')
            {
                table.deleteRow(vitrithem + 1);
            }
        }
    } catch (e) {
        swal('Lỗi', 'Xin liên hệ với quản trị để được khắc phục !. ' + e.toString(), 'error');
    }
}

function addRowPara1(id, indx, id_hidden)
{
    try {
        var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
        //alert('Vào thêm row '+indx);
        var table = document.getElementById("ThemthamsoTable");
        var rowCount = table.rows.length - 1; //Dem so dong cua bang

        if (max_row < rowCount)
            max_row = rowCount;
        else
        {
            max_row++;
            rowCount = max_row;
        }

        var giatri_para = $.trim($("#" + id).val());
        var giatri_list = $.trim($("#" + id_hidden).val());
        if (giatri_para === 'L')//thêm tham số
        {
            if (giatri_list != null && giatri_list == 'L')
            {
                return;
            }
            var idtmp = index > 0 ? index - 1 : index;
//        alert('vao thay doi kiểu dữ liệu là List ' + giatri_para);
            var newTr = '<tr style="background: #E2E8C9" align="center">\n\
                    <td colspan="5">\n\
                        <input type="hidden" name="" value="' + giatri_para + '" id="' + id_hidden + '">\n\
                        <label>Tên bảng: </label>\n\
                        <input type="text" name="BANGSL_' + indx + '" value="" id="BANGSL_ID_' + indx + '" style="width:180px" placeholder="DMPOS"> &nbsp;&nbsp;\n\
                        <label>Cột hiển thị: </label>\n\
                        <input type="text" name="COTHIENTHI_' + indx + '" value="" id="COTHIENTHI_ID_' + indx + '" style="width:100px"  placeholder="PO_MA||\' -> \'||PO_TEN">&nbsp;&nbsp;\n\
                        <label>Cột tham số: </label>\n\
                        <input type="text" name="COTTSO_' + indx + '" value="" id="COTTSO_ID_' + indx + '" style="width:100px" placeholder="PO_MA">&nbsp;&nbsp;\n\
                        <label>Điều kiện lọc: </label>\n\
                        <input type="text" name="DKLOC_' + indx + '" value="" id="DKLOC_ID_' + indx + '" style="width:100px" placeholder="PO_MACN=\'002721\'">&nbsp;&nbsp;\n\
                        <label>Cột sắp xếp: </label>\n\
                        <input type="text" name="DKSAPXEP_' + indx + '" value="" id="DKSAPXEP_ID_' + indx + '" style="width:100px" placeholder="PO_MA">&nbsp;&nbsp;\n\
                    </td>\n\
                </tr>';
            $($('table#ThemthamsoTable tr')[index]).after(newTr);
        } else
        {
            //xóa tham số HIDDEN_DEL_ID_
            //var HIDDEN_ID='HIDDEN_ID_'+indx;

//        alert('HIDDEN_ID='+id_hidden+' giatri_list='+giatri_list);
            if (giatri_list != null && giatri_list == 'L')
            {
                table.deleteRow(index + 1);
            }
        }
    } catch (e) {
        swal('Lỗi', 'Xin liên hệ với quản trị để được khắc phục !. ' + e.toString(), 'error');
    }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Cho sự kiện load các mẫu của Select Options">
function onReloadChonmau()
{
    try
    {
        /*
         //goi action load cac báo cáo đã lưu
         $.getJSON('Themmoichitieu.action', function (jsonResponse) {
         //reload lai du lieu cho select option
         var mau_id = $('#themmau_id');
         //                        alert(jsonResponse.lstDmucTso[0].sKey);
         mau_id.find('option').remove();
         $('<option>').val('-1').text('-- Chọn --').appendTo(mau_id);
         $('<option>').val('').text('').appendTo(mau_id);
         $.each(jsonResponse.lstAllMau, function (index, value) {
         //                            alert(val.sKey);
         $('<option>').val(value.sKey).text(value.sDesc).appendTo(mau_id);
         });
         if (jsonResponse.msg != null)
         {
         //                alert(jsonResponse.msg);
         $('#containBcttv').append('<h2 style="color: red">Lỗi khi lấy mẫu báo cáo !<p style="color: blue">' + jsonResponse.msg + '</h2>');
         }
         });
         */

        $.getJSON('Themmoichitieu.action')
                .done(function (jsonResponse) {
                    var mau_id = $('#themmau_id');
                    //                        alert(jsonResponse.lstDmucTso[0].sKey);
                    mau_id.find('option').remove();
                    $('<option>').val('-1').text('-- Chọn --').appendTo(mau_id);
                    $('<option>').val('').text('').appendTo(mau_id);
                    $.each(jsonResponse.lstAllMau, function (index, value) {
                        //                            alert(val.sKey);
                        $('<option>').val(value.sKey).text(value.sDesc).appendTo(mau_id);
                    });
                    if (jsonResponse.msg != null)
                    {
                        //                alert(jsonResponse.msg);
                        $('#containBcttv').append('<h2 style="color: red">Lỗi khi lấy mẫu báo cáo !<p style="color: blue">' + jsonResponse.msg + '</h2>');
                    }
                })
                .fail(function (jqxhr, textStatus, error) {
                    var err = textStatus + ", " + error;
                    console.log("Request Failed: " + err);
                });
    } catch (e) {
        swal('Lỗi', 'Xin liên hệ với quản trị để được khắc phục !. ' + e.toString(), 'error');
    }
}

function loadFormChitieu()
{
    try {

    } catch (e) {
        alert(e.toString());
    }

}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Sự kiện cho button">

function cleardiv()
{
    $("#containBcttv").empty();
}

$.subscribe("beforediv", function (event, data) {
    $("#loadingImageDiv").show();
});

$.subscribe("completediv", function (event, data) {
    $("#loadingImageDiv").hide();
});

//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Onchange radio">

/**
 * Hàm này cho sự kiện onchan của radio trong form danhmuc-maunhaptay.jsp
 * 
 * @returns {undefined}
 */
function onchangeRadio()
{
    try
    {
        var id_loaichitieu = null;
        try {
            id_loaichitieu = $("input[name='loai_chitieu']:checked").val();
        } catch (e) {
            id_loaichitieu = '';
            swal('Lỗi', 'Lỗi gọi hàm loai_chitieu' + e.toString(), 'error');
        }



        var loaimau_daluu = null;
        try {
            loaimau_daluu = $.trim($("#id_loaimau_daluu").val());
        } catch (e) {
            loaimau_daluu = '';
            swal('Lỗi', 'Lỗi gọi hàm loaimau_daluu' + e.toString(), 'error');
        }

        //swal('Lỗi', 'Thử nhé '+id_loaichitieu+' loaimau_daluu='+loaimau_daluu, 'error');
        if (id_loaichitieu === '-1')
        {
            swal('Lỗi', 'Bạn phải chọn mẫu cần thêm chỉ tiêu !', 'error');
            return;
        }

        var khoa = $.trim($("#id_khoamau").val());

        console.log('khoa=' + khoa + 'loaimau_daluu=' + loaimau_daluu);

        //max_row = 0;
        if (loaimau_daluu != id_loaichitieu && loaimau_daluu != 'NEW')
        {
            swal('Lỗi', 'Bạn đã lưu dữ liệu nên không thể thay đổi kiểu dữ liệu nhập', 'error');
            //$('input:radio[name="loai_chitieu"]').filter('[value="'+loaimau_daluu+'"]').attr('checked', true);
            document.querySelector('input[name=loai_chitieu][value=' + loaimau_daluu + ']').checked = true;
            return;
        }
        id_loaichitieu = 'TB';
//            alert(id_loaichitieu+' khoa='+khoa);  //$("#containBcttv").load("loadallquery.action");
        if (id_loaichitieu === 'CT')
        {
            $("#input_bangdl").empty();
            $("#input_chitieu").show();
            $("#input_bangdl").hide();
//        $("#input_chitieu").load('input-branch/input-branch-chitieu.jsp');
            $("#input_chitieu").load("loadCauhinhChieuCotDulieu.action?khoa=" + khoa + "&loai_chitieu=" + id_loaichitieu);
        } else if (id_loaichitieu === 'TB')
        {
            console.log('id_loaichitieu=' + id_loaichitieu);
            $("#input_bangdl").empty();
            $("#input_chitieu").empty();
            $("#input_chitieu").hide();
            $("#input_bangdl").show();
//        $("#input_bangdl").load('input-branch/input-branch-bang.jsp');
            $("#input_bangdl").load("loadCauhinhChieuCotDulieu.action?khoa=" + khoa + "&loai_chitieu=" + id_loaichitieu);
        } else if (id_loaichitieu === 'QR')
        {
            $("#input_bangdl").empty();
            $("#input_chitieu").empty();
            $("#input_chitieu").hide();
            $("#input_bangdl").show();
//        $("#input_bangdl").load('input-branch/input-branch-bang.jsp'); loadDulieuMauTruyvan
            $("#input_bangdl").load("loadCauhinhChieuCotDulieu.action?khoa=" + khoa + "&loai_chitieu=" + id_loaichitieu);
        } else
            swal('Lỗi', 'Không đúng loại mẫu cần nhập', 'error');
    } catch (err)
    {
        swal('Lỗi', 'Lỗi gọi hàm redirect onchangeRadio' + err.toString(), 'error');
        console.log('Loi tai day ne');
    }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Thê và xóa chỉ tiêu cho bảng html input-branch-chitieu.jsp">

function addChitieuTable(indx)
{
    try {
        var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
        //        alert('Vào thêm row ' + indx);
        var table = document.getElementById("id_table_ct");
        var rowCount = table.rows.length - 1; //Dem so dong cua bang
        if (max_row < rowCount)
            max_row = rowCount; //nếu số row_max nhỏ hơn tổng số row thì gán lại
        else
        {
            rowCount = max_row; //gán ngược lại mục đích gán để tăng mảng cho dữ liệu lstDulieu
            max_row++;
        }

        var newTr = '<tr style="background: #E2E8C9" align="center">\n\
                        <td style=>\n\
                            <input type="text" name="lstDulieuChitieu[' + rowCount + '].MA" id="id_machitieu" size="10" placeholder="MACTxxxxx"/>\n\
                        </td>\n\
                        <td style="width: 45%">\n\
                            <input type="text" name="lstDulieuChitieu[' + rowCount + '].TEN" id="id_tenchitieu" size="40" placeholder="Nhập tên mô tả chỉ tiêu">\n\
                        </td>\n\
                        <td style=>\n\
                            <input type="button" value="Xóa chỉ tiêu" style="margin: 0px;" onclick="deleteChitieuTable(this.parentNode.parentNode.rowIndex)" class="metroButtonStyle">\n\
                        </td>\n\
                    </tr>';
        $($('table#id_table_ct tr')[index]).before(newTr);
    } catch (e) {
        swal('Lỗi', 'Xin liên hệ với quản trị để được khắc phục !. ' + e.toString(), 'error');
    }
}

function deleteChitieuTable(indx)
{
    try {
        var table = document.getElementById("id_table_ct");
        var rowCount = table.rows.length - 1; //Dem so dong cua bang 
        //Do khi xóa nếu mảng đã có rồi ví dụ xóa tại vị trí lstDulieu là 5 khi thêm vào dễ bị trùng phần tử lstDulieu 5 nên phải gán
        if (max_row < rowCount)
            max_row = rowCount;
        table.deleteRow(indx);
    } catch (e) {
        swal('Lỗi', 'Xin liên hệ với quản trị để được khắc phục !. ' + e.toString(), 'error');
    }
}
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="Thêm và xóa cột dữ liệu input-branch-bang.jsp">

function addColumn()
{
    //        alert('vao ham addcolumn');
    try {
        var table = document.getElementById("id_table_bang");

        var rowcount = table.rows[0].cells.length - 1;
        if (rowcount > 49)
        {
            //alert('Số cột nhập liệu không thể lớn hơn 50 cột');
            swal('Lỗi thêm cột', 'Số cột nhập liệu không thể lớn hơn 50 cột !', 'error');
            return;
        }
        if (max_row < rowcount)
            max_row = rowcount;
        else
        {
            rowcount = max_row;
            max_row++;
        }

        var col = table.rows[0].cells.length - 1;
        //alert('so1='+table.rows[0].cells.length);
        var cell0 = table.rows[0].insertCell(col);
        //rowcount=rowcount-1;
        cell0.innerHTML = '<label class="Classcotdulieu">Cột ' + (rowcount + 1) + ' </label><br>\n\
                        <textarea name="lstDulieuCot[' + rowcount + '].TEN" id="id_tenchitieu' + rowcount + '" rows="4" cols="10" style="margin: 2px 2px; height: 60px; width: 100px;" placeholder="Nhập tên cột dữ liệu"></textarea>';

        var cell1 = table.rows[1].insertCell(col);
        cell1.innerHTML = '<label>Loại dữ liệu: </label><br>\n\
                        <select name="lstDulieuCot[' + rowcount + '].KIEUDULIEU" id="id_kieudulieu_' + rowcount + '"  style="width: 100px" onchange="showButtonList(' + rowcount + ');">\n\
                        <option value="T">T -&gt; Kiểu text</option>\n\
                        <option value="N">N -&gt; Kiểu số</option>\n\
                        <option value="D">D -&gt; Ngày tháng năm</option>\n\
                        <option value="L">L -&gt; Danh mục</option>\n\
                        </select>';
        var cell2 = table.rows[2].insertCell(col);
        cell2.innerHTML = '<input type="button" id="id_showparameter_' + rowcount + '" value="Tham số List" style="margin: 0px;display: none;" onclick="calldilog(' + rowcount + ');" class="metroButtonStyle">\n\
                           <input type="hidden" name="lstDulieuCot[' + rowcount + '].MA" value="" id="PARAMETER_LIST_' + rowcount + '">';
        var cell3 = table.rows[3].insertCell(col);
        cell3.innerHTML = '<input type="button" value="Xóa cột dữ liệu" style="margin: 0px;" onclick="deleteCColumnTable(this.parentNode)" class="metroButtonStyle">';

        //table.rows[0].cells[col].focus();
        //document.getElementById("themcotduliu_add").focus();

        //Danh lai thu tu ten cot
        var index = 1;
        var collection = $(".Classcotdulieu");
        collection.each(function () {
            $(this).text('Cột ' + index);
            index++;
            // You can access `collection.length` here.
        });

        var objDiv = document.getElementById("landing_id");

        objDiv.scrollIntoView();
        objDiv.scrollLeft = objDiv.scrollWidth;

//        $("html, body").animate({ 
//                    scrollTop: $( 
//                      'html, body').get(0).scrollHeight 
//                }, 2000); 
    } catch (e) {
        swal('Lỗi', 'Xin liên hệ với quản trị để được khắc phục !. ' + e.toString(), 'error');
    }
}

function deleteCColumnTable(indx)
{
    try {
        var table = document.getElementById("id_table_bang");
        var cell = indx.cellIndex; //vi tri cua cell
        var max_cell = table.rows[0].cells.length; //số cột
        if (max_row < max_cell)
            max_row = max_cell;
        //alert('cell='+cell);
        //cell=cell+2;
        for (var row = 0; row < table.rows.length; row++)
        {
            if (table.rows.length - 1 == row)
                table.rows[row].deleteCell(cell - 1);
            else
                table.rows[row].deleteCell(cell);
        }
        //alert(indx.cellIndex);
        var index = 1;
//    $("label").each(function () {
//        $(this).text('Cột ' + index);
//        index++;
//    });
        //Đoạn này dùng để replace tên cột dữ liệu khi xóa
        var collection = $(".Classcotdulieu");
        collection.each(function () {
            $(this).text('Cột ' + index);
            index++;
            // You can access `collection.length` here.
        });
    } catch (e) {
        swal('Lỗi', 'Xin liên hệ với quản trị để được khắc phục !. ' + e.toString(), 'error');
    }
}
//</editor-fold>