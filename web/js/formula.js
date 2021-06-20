//<editor-fold defaultstate="collapsed" desc="Cho form main_bccongthuc.jsp">

var fieldName = 'check_branch';
function selectall() {
    //            alert('Vao chon tat cả ');
    var i = document.formula_create.elements.length;

    var e = document.formula_create.elements;
    var name = new Array();
    var value = new Array();
    var j = 0;
    for (var k = 0; k < i; k++)
    {

        if (document.formula_create.elements[k].name == fieldName)
        {
            //                    alert('Gia tri la checkbox ' + k);
            if (document.formula_create.elements[k].checked == true) {
                value[j] = document.formula_create.elements[k].value;
                j++;
            }
        }
    }
    checkSelect();
}
function selectCheck(obj)
{
    var i = document.formula_create.elements.length;
    for (var k = 0; k < i; k++)
    {
        if (document.formula_create.elements[k].name == fieldName)
        {
            document.formula_create.elements[k].checked = obj;
        }
    }
    selectall();
}

function selectallMe()
{
    if (document.formula_create.allCheck.checked == true)
    {
        selectCheck(true);
    }
    else
    {
        selectCheck(false);
    }
}
function checkSelect()
{
    var i = document.formula_create.elements.length;
    var berror = true;
    for (var k = 0; k < i; k++)
    {
        if (document.formula_create.elements[k].name == fieldName)
        {
            if (document.formula_create.elements[k].checked == false)
            {
                berror = false;
                break;
            }
        }
    }
    if (berror == false)
    {
        document.formula_create.allCheck.checked = false;
    }
    else
    {
        document.formula_create.allCheck.checked = true;
    }
}
window.onload = showandhidden;
function ShowHide(status, controlId) {
    var lblShowHide = document.getElementById(controlId);
    //alert(status);
    if (status == 1) {
        lblShowHide.style.visibility = 'visible';
    }
    else {
        lblShowHide.style.visibility = 'hidden';
    }
}
function showandhidden()
{
    var val = $("#report_type").val();
    var val_rad = $('input[name=row_column]:checked', '#formula_create').val();
    //alert(val);
    //Nếu báo cáo là chi tiết
    if (val == "01")
    {
        //Thi ẩn hết các control của bảng chọn chi nhánh
        //alert(val);
        ShowHide(0, "showandhidden_table");
        ShowHide(0, "showandhidden_main");
        ShowHide(0, "showandhidden_rowcolumn");
        //Thiết lập chọn số dòng là enable
        $("#number_row").attr('disabled', false);
        $("#number_column").attr('disabled', false);
    }
    else
    {
        //Nếu báo cáo là tổng hợp thì hiển thị các điều khiển
        //alert(val);
        ShowHide(1, "showandhidden_table");
        ShowHide(1, "showandhidden_main");
        ShowHide(1, "showandhidden_rowcolumn");
        //Khởi tạo cho so dòng là disable
        if (val_rad == 1)
        {
            $("#number_row").attr('disabled', true);
            $("#number_column").attr('disabled', false);
        }
        else
        {
            $("#number_row").attr('disabled', false);
            $("#number_column").attr('disabled', true);
        }

    }
}
function DisableEnable()
{
    var val = $('input[name=row_column]:checked', '#formula_create').val();//$("#row_column").val();
    //            var val=document.getElementById('row_column').value;
    //            alert(val);
    if (val == 1)
    {
        $("#number_row").attr('disabled', true);
        $("#number_column").attr('disabled', false);
    }
    else
    {
        $("#number_column").attr('disabled', true);
        $("#number_row").attr('disabled', false);
    }
}
//</editor-fold>
