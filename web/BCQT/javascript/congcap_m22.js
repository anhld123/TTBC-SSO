/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// KHAI BAO HAM DU LIEU
//            var data = new Array();

// HAM TINH GIA TRI THEO CAP KHI NHAP
function sumColumn() {    
    //alert('a');
    // KHOI TAO MANG GIA TRI
    if (!data || data.length === 0) {
        
        var rowTotal = getValue('row_total_ID');
        //alert(rowTotal);
        var data = new Array();
        
        for (i = 0; i < rowTotal; i++) {
            data[i] = new Array();
            //data[i][0] = getValue('CAP_' + i.toString());
            //data[i][1] = getNum('D1_' + i.toString());
            //data[i][2] = getNum('D2_' + i.toString());
            //data[i][3] = getNum('D3_' + i.toString());
            data[i][4] = getNum('D4_' + i.toString());
            data[i][5] = getNum('D5_' + i.toString());
            data[i][6] = getNum('D6_' + i.toString());
            data[i][7] = getNum('D7_' + i.toString());
            data[i][8] = getNum('D8_' + i.toString());
            data[i][9] = getNum('D9_' + i.toString());
            data[i][10] = getNum('D10_' + i.toString());
            data[i][11] = getNum('D11_' + i.toString());
            data[i][12] = getNum('D12_' + i.toString());
            data[i][13] = getNum('D13_' + i.toString());
            data[i][14] = getNum('D14_' + i.toString());
            data[i][15] = getNum('D15_' + i.toString());
            data[i][16] = getNum('D16_' + i.toString());
            //data[i][17] = getValue('CO_CONGCAP_' + i.toString());
        }

        // KHOI TAO  MANG CHUA GIA TRI CAP
//        var level_arr = new Array();
//        var level_i_arr = new Array();
//        var level_arr_size = 0;
//        for (i = 0; i < rowTotal; i++) {
//            if (data[i][10] === 'Y') {
//                if (level_arr.length === 0) {
////                                level_arr[0] = new Array();
//                    level_arr[0] = data[i][0];
//                    level_i_arr[0] = i;
//                    level_arr_size++;
//                }
//                else {
//                    // KIEM TRA XEM DA CO TRONG MANG
//                    var isExisted = false;
//                    for (j = 0; j < level_arr.length; j++) {
//                        if (level_arr[j] === data[i][0]) {
//                            isExisted = true;
//                        }
//                        if (!isExisted) {
//                            level_arr[level_arr_size] = data[i][0];
//                            level_i_arr[level_arr_size] = i;
//                            level_arr_size++;
//                        }
//                    }
//                }
//            }
//        }
//        level_arr.reverse();
    }        
    // SAU KHI HOAN THANH TINH LAI GIA TRI COT
    for (var i = 0; i < rowTotal; i++) {
        var col_newval = data[i][9] - data[i][10] - 
                data[i][11] - data[i][12] - data[i][13] - data[i][14]- data[i][15];                
        setValue('D16_' + i.toString(), col_newval);
    }
    
    $('.number').number(true, 0);
    $('.number2').number(true, 0);
}
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
