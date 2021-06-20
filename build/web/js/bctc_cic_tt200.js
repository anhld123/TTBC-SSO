//ham nay tim cong thuc co chua ma chi tieu de lay ra cong thuc cong
function getCongthuc(ma_ct)
{
    var kh_congthuc = ".KH_CONGTHUC"; //class của input text
    try {
        var rowCount = $("#bctc_cic td").closest("tr").length; //đếm số thẻ tr của table với id=bctc_cic
        for (i = 0; i < rowCount; i++) //for tat ca du lieu cua cong thuc
        {
            if ($(kh_congthuc).eq(i).val().length > 0) //neu cong thuc khac null
            {
                //lấy ra công thức
                var congthuc = $(kh_congthuc).eq(i).val(); //lấy ra công thức 
                if (congthuc.indexOf(ma_ct) >= 0)//nếu tìm thấy chỉ tiêu được cộng trong công thức
                {
                    //console.log([congthuc, i]);
//                    spitcongthuc(congthuc);
                    return [congthuc, i]; //return ra công thức và vị trí của công thức
                }
            }
        }
        return null;
    } catch (e) {
        return null;
    }
}

//ham nay cat cong thuc thang cac ma chi tieu
function spitcongthuc(congthuc)
{
    try {
        var tmpcongthuc = congthuc;
        tmpcongthuc = tmpcongthuc.replace('-', '+');
        tmpcongthuc = tmpcongthuc.replace(' ', '');
        var arrma = congthuc.split('+');
        //console.log(arrma);
        return arrma;
    } catch (e) {
        return null;
    }

}

//lấy số liệu từ công thức ví dụ công thức CD112+CD113
function laysolieucong(congthuc,subid)
{
    try {
        congthuc=congthuc.replace(/ /g, '');//replace hết dấu ký tự space
         var tmpcongthuc = congthuc; //đưa cong thức vào temp
        tmpcongthuc = tmpcongthuc.replace(/-/g, '+'); //replate hết - thành cộng để cắt cho dễ
//        tmpcongthuc = tmpcongthuc.replace(' ', '');//replace hết dấu ký tự space
        //console.log('tmpcongthuc========'+tmpcongthuc);
        var arrma = tmpcongthuc.split('+');
        for(i=0;i<arrma.length;i++)
        {
            //console.log(arrma[i]+"="+getvalue(arrma[i]));
            congthuc=congthuc.replace(arrma[i],getvalue(subid+arrma[i])); //replace mã chỉ tiêu bằng giá trị
        }
        //console.log('eval(congthuc)='+eval(congthuc));
        return  eval(congthuc);
    } catch (e) {
        return 0;
    }

}
//lay ra gia trị của input text từ id truyền vào
function getvalue(id)
{
    try {
       
        //$('#idCD110').val($('#id'+ma_ct).val());
        var giaitri=0;
        var value=$('#' + id).val();//lấy giá trị
        if (value==null || value=='' || value==' ') giaitri=0; //nếu chưa nhập gán là 0
        else giaitri=parseInt(value);
         //console.log("id="+id+" giaitri="+giaitri);
        return giaitri;
    } catch (e) {
        //alert(e.toString());
        return 0;
    }

}

//Tính công thức ở đây tham số đầu vào là mã chỉ tiêu , subid
//ví dụ tham số là CD111, subid='D5_' là tính cho cột D5
function congcapcongthuc(ma_ct, subid)
{
    try {
        var arrchitieu = getCongthuc(ma_ct);
        if (arrchitieu == null)
            return;
        var congthuc = arrchitieu[0]; //lấy ra công thức
        var vitri = arrchitieu[1]; //lấy ra vị trí chỉ tiêu char có chứa công thức cộng

        while (arrchitieu != null)
        {
            //lấy ra mã chỉ tiêu có chứa công thức công 
            //vi dụ CD110=CD111+CD112+CD113 thì sẽ lấy ra mã chỉ tiêu là CD110
            var ma_ct_parrent = $(".CIC_MA").eq(vitri).val();
            //Công công thức
            var dulieu=laysolieucong(congthuc, subid);
            $('#'+subid+ma_ct_parrent).val(dulieu); //sét giá trị cho vị trí có chứa công thức cộng
             //console.log("ma_ct_parrent="+ma_ct_parrent+" vitri="+vitri+" congthuc="+congthuc+" arrchitieu="+arrchitieu+" dulieu="+dulieu);
            arrchitieu = getCongthuc(ma_ct_parrent); //tiếp tục tìm xem chỉ tiêu cha (chỉ tiêu chứa công thưc cộng có còn liên quan đến chỉ tiêu nào nữa không)
            congthuc = arrchitieu[0];
            vitri = arrchitieu[1];
        }
    } catch (e) {
        //alert(e.toString());
    }

}