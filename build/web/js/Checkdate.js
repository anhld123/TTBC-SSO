function validatedate(giatri)
{
    /* -----------------           
     - Ngày tạo          :   12/05/2014
     - Người tạo         :   Nguyễn Phú Vinh
     - Mục đích          :   Kiểm tra giá trị ngày ngập vào có đúng là dạng ngày tháng không (định dang DD/MM/YYYY)
     - Cách dùng         :   VD:javascript:validatedate('20/02/1985')
    -------------------*/
    if (giatri.trim().length != 0) {
        var arraydate = giatri.split('/');
        var ngay = parseInt(arraydate[0]);
        var thang = parseInt(arraydate[1]);
        var nam = parseInt(arraydate[2]);
        var trangthai = false;
        var arrayday = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]; //Tương ứng các ngày cuối tháng của năm không nhuận
        if (thang == 1 || thang > 2)
        {
            if (ngay > arrayday[thang - 1])
            {
                alert('Không có ngày: ' + giatri.trim());
                return false;
            }
        }
        if (thang == 2)
        {
            if ((!(nam % 4) && nam % 100) || !(nam % 400))
            {
                trangthai = true;
            }
            if ((trangthai == false) && (ngay >= 29))
            {
                alert('Không có ngày: ' + giatri.trim());
                return false;
            }
            if ((trangthai == true) && (ngay > 29))
            {
                alert('Không có ngày: ' + giatri.trim());
                return false;
            }
        }
    }
}