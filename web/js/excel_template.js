//<editor-fold defaultstate="collapsed" desc="Cho form main_bccongthuc.jsp">
function insertParameterToQuery(obj)
{
    try
    {
        var id = obj.id.toString(); //lấy ra id của tham số
        var parameter = id.substring(10, id.length)//lấy ra tham số
        var dropText;
        dropText = " $P{" + parameter + "}"; //tạo cấu trúc của tham số
        var droparea = document.getElementById('idquery'); //lấy ra text trong vung area
        var range1 = droparea.selectionStart; //lấy ra vị trí bắt đầu (từ vị trí 0 tới vị trí đặt chuột)
        var range2 = droparea.selectionEnd; //Láy ra vị trí từ vị trí đặt chuột đến hết đoạn text
        var val = droparea.value; //lấy ra text của vùng area
        var str1 = val.substring(0, range1); //cắt chuỗi text từ vị trí 0 đến vị trí đặt chuột
        var str3 = val.substring(range1, val.length); //text từ vị trí đặt chuộ tới hết chuỗi
        droparea.value = str1 + dropText + str3; //ghép chuỗi và cộng thêm tham số vào vịt rí đặt chuột
        //                alert( str1 + dropText + str3);
    }
    catch (e)
    {
    }
}

function createTreeNode(obj)
{
    try
    {
        var action;
        var fileTemplate = $('#fileTemplate').val(); //lấy ra tên file excel template đã luu
        var type_id = obj[0].id; //id của điều kiển
        //neu la tham so
        if (type_id == 'parameter') //nếu là tham số thì gọi hàm thêm tham số
        {
            action = 'configEditParameter.action';
            $("#result2").load(action + "?parameter=new&fileTemplate=" + fileTemplate);
        }
        else if (type_id == 'query') //neu la truy van
        {
            action = 'configEditQuery.action';
            $("#result2").load(action + "?id=new&fileTemplate=" + fileTemplate);
        }
        else //truong hop khac
        {
            $('#result2').html("<h2 style='color: red'>Bạn không thể thêm vào treeitem này được  ! </h2>");
        }
    } catch (e) {
    }

}

function deleteTreeNode(obj)
{
    try
    {
        var action = '';
        var fileTemplate = $('#fileTemplate').val(); //lấy ra tên file excel template đã luu
        var id = obj[0].id.toString();
        $("#result2").empty();
        if (id == null || id.length < 1)
            return true;
        if (fileTemplate == null || fileTemplate == '' || fileTemplate.length < 1)
        {
            $('#result2').html("<h2 style='color: red'>Không thể lấy ra được tên file excel  ! </h2>");
            return false;
        }

        var r = confirm("Bạn có chắc chắn muốn xóa tham số này không, Khi xóa bạn sẽ không thể lấy lại được ? OK : Đồng ý, Cancel : Hủy bỏ");
        if (r != true) {
            // $("#divParams").load(action);
            return false;
        }

        if (id == 'parameter' || id == 'query')
        {
            alert('Bạn không được phép xóa treeitem này');
            $('#result2').html("<h2 style='color: red'>Bạn không được phép xóa treeitem này  ! </h2>");
            return false;
        }
        //                alert(id.toLowerCase().indexOf('parameter'));
        if (id.toLowerCase().indexOf('parameter') >= 0)
        {
            var parameter = id.substring(10, id.length);
            if (parameter == 'ORACLE_REF_CURSOR')
            {
                alert('Bạn không được phép xóa tham số này !');
                $('#result2').html("<h2 style='color: red'>Bạn không được phép xóa tham số này ! </h2>");
                return false;
            }
            //                     alert('parameter='+parameter);
            $("#result2").load("deleteParameter.action?parameter=" + parameter + "&fileTemplate=" + fileTemplate);
        }
        else if (id.toLowerCase().indexOf('query') >= 0)
        {
            var query = id.substring(6, id.length);
            //                     alert('query='+query);
            $("#result2").load("deleteQuery.action?id=" + query + "&fileTemplate=" + fileTemplate);
        }
        else
        {
            alert('Bạn không được phép xóa treeitem này');
            return false;
        }
        return true;
    }
    catch (e)
    {
        return false;
    }
}
function modifyTreeNode(obj)
{
    try
    {
        var action = '';
        var fileTemplate = $('#fileTemplate').val();
        var id = obj[0].id.toString();
        $("#result2").empty();

        if (fileTemplate == null || fileTemplate == '' || fileTemplate.length < 1)
        {
            $('#result2').html("<h2 style='color: red'>Không thể lấy ra được tên file excel  ! </h2>");
            return false;
        }

        if (id == 'parameter' || id == 'query')
        {
            alert('Bạn không được phép sửa treeitem này');
            $('#result2').html("<h2 style='color: red'>Bạn không được phép sửa treeitem này  ! </h2>");
            return false;
        }
        //                alert(id.toLowerCase().indexOf('parameter'));
        if (id.toLowerCase().indexOf('parameter') >= 0)
        {
            var parameter = id.substring(10, id.length);
            //                     alert('parameter='+parameter);
            $("#result2").load("configEditParameter.action?parameter=" + parameter + "&fileTemplate=" + fileTemplate);
        }
        else if (id.toLowerCase().indexOf('query') >= 0)
        {
            var query = id.substring(6, id.length);
            //                     alert('query='+query);
            $("#result2").load("configEditQuery.action?id=" + query + "&fileTemplate=" + fileTemplate);
        }
        else
        {
            alert('Bạn không được phép sửa treeitem này');
            return false;
        }

        return true;
    }
    catch (e)
    {
        return false;
    }
}


$(function () {
    $.subscribe('treeBefore', function (event, data) {
        $("#loadingImageDiv_rpt").show();
    });
});
$(function () {
    $.subscribe('treeComplete', function (event, data) {
        $("#loadingImageDiv_rpt").hide();
    });
});
//</editor-fold>
