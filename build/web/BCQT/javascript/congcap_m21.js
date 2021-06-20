/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// KHAI BAO HAM DU LIEU
//            var data = new Array();

// HAM TINH GIA TRI THEO CAP KHI NHAP
function sumColumn_byRow(column_name) {
    // KHOI TAO MANG GIA TRI
    if (!data || data.length === 0) {
        var rowTotal = getValue('row_total_ID');
        var data = new Array();
        for (i = 0; i < rowTotal; i++) {
            data[i] = new Array();
            data[i][0] = getValue('CAP_' + i.toString());
            data[i][1] = getNum('D1_' + i.toString());
            data[i][2] = getNum('D2_' + i.toString());
            data[i][3] = getNum('D3_' + i.toString());
            data[i][4] = getNum('D4_' + i.toString());
            data[i][5] = getNum('D5_' + i.toString());
//            data[i][6] = getNum('D6_' + i.toString());
//            data[i][7] = getNum('D7_' + i.toString());
            data[i][8] = getValue('CO_CONGCAP_' + i.toString());
        }

        // KHOI TAO  MANG CHUA GIA TRI CAP
        var level_arr = new Array();
        var level_i_arr = new Array();
        var level_arr_size = 0;
        for (i = 0; i < rowTotal; i++) {
            if (data[i][8] === 'Y') {
                if (level_arr.length === 0) {
//                                level_arr[0] = new Array();
                    level_arr[0] = data[i][0];
                    level_i_arr[0] = i;
                    level_arr_size++;
                }
                else {
                    // KIEM TRA XEM DA CO TRONG MANG
                    var isExisted = false;
                    for (j = 0; j < level_arr.length; j++) {
                        if (level_arr[j] === data[i][0]) {
                            isExisted = true;
                        }
                        if (!isExisted) {
                            level_arr[level_arr_size] = data[i][0];
                            level_i_arr[level_arr_size] = i;
                            level_arr_size++;
                        }
                    }
                }
            }
        }
        level_arr.reverse();
    }
    // PHAN DUYET DU LIEU DE CONG CAP
    var total_value = 0;
    var level_cur = '';
    var level_id = 0;
    for (i = 0; i < level_arr.length; i++) {
        total_value = 0;
        level_cur = level_arr[i];
        for (j = 0; j < rowTotal; j++) {
            var level_j = data[j][0];
            switch (column_name) {
                case 'D1':
                    if (level_j.substr(0, level_cur.length) === level_cur) {
                        if (level_j.length === (level_cur.length + 1))
                            total_value += data[j][1];
                        if (level_j === level_cur)
                            level_id = j;
                    }
                    break;
                case 'D2':
                    if (level_j.substr(0, level_cur.length) === level_cur) {
                        if (level_j.length === (level_cur.length + 1))
                            total_value += data[j][2];
                        if (level_j === level_cur)
                            level_id = j;
                    }
                    break;
                case 'D3':
                    if (level_j.substr(0, level_cur.length) === level_cur) {
                        if (level_j.length === (level_cur.length + 1))
                            total_value += data[j][3];
                        if (level_j === level_cur)
                            level_id = j;
                    }
                    break;
                case 'D4':
                    if (level_j.substr(0, level_cur.length) === level_cur) {
                        if (level_j.length === (level_cur.length + 1))
                            total_value += data[j][4];
                        if (level_j === level_cur)
                            level_id = j;
                    }
                    break;
                case 'D5':
                    if (level_j.substr(0, level_cur.length) === level_cur) {
                        if (level_j.length === (level_cur.length + 1))
                            total_value += data[j][5];
                        if (level_j === level_cur)
                            level_id = j;
                    }
                    break;
                case 'D6':
                    if (level_j.substr(0, level_cur.length) === level_cur) {
                        if (level_j.length === (level_cur.length + 1))
                            total_value += data[j][6];
                        if (level_j === level_cur)
                            level_id = j;
                    }
                    break;
                case 'D7':
                    if (level_j.substr(0, level_cur.length) === level_cur) {
                        if (level_j.length === (level_cur.length + 1))
                            total_value += data[j][7];
                        if (level_j === level_cur)
                            level_id = j;
                    }
                    break;
            }
        }
//                    alert('Tinh toan:' + level_cur + '~' + total_value);
//                    alert('D1_' + level_id);
        // PHAN XET GIA TRI
        setValue(column_name + '_' + level_id, total_value);
//                    setArray_value(column_name, level_id, total_value);
        // PHAN XET LAI GIA TRI CHO MANG
        var col_index = getMappingCol_byName(column_name);
        data[level_id][col_index] = total_value;
    }
    $('.number').number(true, 0);
    $('.number2').number(true, 0);
    // SAU KHI HOAN THANH TINH LAI GIA TRI COT
//                sumColumn_byCol();

//    for (i = 0; i < rowTotal; i++) {
//        var col_newval = data[i][2] - data[i][3];
//        setValue('D4_' + i.toString(), col_newval);
//        col_newval = data[i][3] - data[i][2];
//        setValue('D5_' + i.toString(), col_newval);
//    }
}
// HAM KHAI BAO CAC COT SE DUOC CONG,TRU TU COT KHAC
//            function sumColumn_byCol() {                
//                var rowTotal = getValue('row_total_ID');
//                for (i = 0; i < rowTotal; i++) {
//                    var col_newval = data[i][2] - data[i][3];
//                    setValue('D4_' + i.toString(), col_newval);
//                    col_newval = data[i][3] - data[i][2];
//                    setValue('D5_' + i.toString(), col_newval);
//                }
//            }
// --- HAM DUNG CHUNG
function getNum(id)
{
    var value = 0;
    try {
        value = document.getElementById(id).value;
        value = value.replace(/,/g, "");
        if (value === '-1'
                || value.length === 0)
            value = 0.0;
    } catch (e)
    {
        value = 0.0;
    }
    return parseFloat(value);
}

function getValue(id)
{
    var value = document.getElementById(id).value;
    return value;
}

function setValue(id, value)
{
    try {
        document.getElementById(id).value = value;
    } catch (e)
    {
    }
}

// HAM SET NGUOC LAI GIA TRI CUA MANG
//            function setArray_value(column_name, index, value) {
////                alert('setArray_value');                
//                var col_index = getMappingCol_byName(column_name);
////                alert('aaaa'+index+'~'+col_index+'~'+value);
////                data[index][col_index] = value;                
////                alert('bbb');
//            }

// HAM LAY ANH XA TU TEN COT THANH VI TRI COT TRONG MANG
function getMappingCol_byName(column_name) {
    var index = column_name.substr(1, 2);
//                alert('getMappingCol_byName'+index);
    return parseInt(index);
}

// HAM LAY ANH XA TU TEN COT THANH VI TRI COT TRONG MANG
function getMappingCol_byId(id) {
    return 'D' + id.toString();
}
