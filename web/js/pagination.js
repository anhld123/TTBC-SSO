function fnPagination(type, param) {
    var oPage_number = document.getElementById("page_number");
    var page_number = parseInt(oPage_number.value);
    switch (type)
    {
        case 1://Next
            oPage_number.value = (page_number + 1);
            break;
        case 2://Last
            oPage_number.value = param;
            break;
        case 3://Previous
            oPage_number.value = (page_number - 1);
            break;
        case 4://First
            oPage_number.value = 1;
            break;
        case 5://Page size change
            oPage_number.value = 1;
            break;
        case 6://sort
            var oSortColumn = document.getElementById("sortColumn");
            var oSortOrder = document.getElementById("sortOrder");
            
            
            if (oSortColumn.value == param && oSortOrder.value != "DESC")
            {
//                alert(oSortColumn+" if "+oSortOrder+" param "+param);
                oSortOrder.value = "DESC";
            }
            else
            {
//                alert(oSortColumn+" else "+oSortOrder+" param "+param);
                oSortOrder.value = "ASC";
            }
            oSortColumn.value = param;
            break;
    }
//             $("#idSubmit").trigger('click');

}

function hoanthanh()
{
    //Goi su kien bat dau load du lieu o trang pagination.jsp (the div)
    $.subscribe('batdauloaddata', function (event, data) {

        $("#loadingImageData_Div").show();
    });
    //Goi nut submit trong trang table_data_risk.jsp
    $("#idSubmit").trigger('click');
    $.subscribe('hoanthanhloaddata', function (event, data) {
        $("#loadingImageData_Div").hide();
    });
}
var fieldName = 'check_legacyid';

function selectall() {
    var i = document.frmDataRisk.elements.length;
    var e = document.frmDataRisk.elements;
    var name = new Array();
    var value = new Array();
    var j = 0;
    for (var k = 0; k < i; k++)
    {
        //alert(document.frmDataRisk.elements[k].value);
        if (document.frmDataRisk.elements[k].name.indexOf(fieldName)>0)
        {
            if (document.frmDataRisk.elements[k].checked == true) {
                value[j] = document.frmDataRisk.elements[k].value;
                j++;
            }
        }
    }
    checkSelect();
}
function selectCheck(obj)
{
    var i = document.frmDataRisk.elements.length;
    for (var k = 0; k < i; k++)
    {
        
        if (document.frmDataRisk.elements[k].name.indexOf(fieldName)>0)
        {
            //alert(document.frmDataRisk.elements[k].name);
            // == fieldName
            document.frmDataRisk.elements[k].checked = obj;
        }
    }
    selectall();
}

function selectallMe()
{
    if (document.frmDataRisk.allCheck.checked == true)
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
    var i = document.frmDataRisk.elements.length;
    var berror = true;
    for (var k = 0; k < i; k++)
    {
        if (document.frmDataRisk.elements[k].name.indexOf(fieldName)>0)
        {
            if (document.frmDataRisk.elements[k].checked == false)
            {
                berror = false;
                break;
            }
        }
    }
    if (berror == false)
    {
        document.frmDataRisk.allCheck.checked = false;
    }
    else
    {
        document.frmDataRisk.allCheck.checked = true;
    }
}

//----------------------------------------------------------------------------------------
function selectallMacn() {
//    alert('da vao ham selectallMacn');
    var i = document.formviewHistory.elements.length;
    var e = document.formviewHistory.elements;
    var name = new Array();
    var value = new Array();
    var j = 0;
    for (var k = 0; k < i; k++)
    {
//        alert('selectallMacn'+document.formviewHistory.elements[k].name);
        //alert(document.formviewHistory.elements[k].value);
        if (document.formviewHistory.elements[k].name=='macn')
        {
            if (document.formviewHistory.elements[k].checked == true) {
                value[j] = document.formviewHistory.elements[k].value;
                j++;
            }
        }
    }
    checkSelectMacn();
}
function selectCheckMacn(obj)
{
    var i = document.formviewHistory.elements.length;
    for (var k = 0; k < i; k++)
    {
//        alert('selectCheckMacn'+document.formviewHistory.elements[k].name);
        if (document.formviewHistory.elements[k].name=='macn')
        {
            //alert(document.formviewHistory.elements[k].name);
            // == 'macn'
            document.formviewHistory.elements[k].checked = obj;
        }
    }
    selectallMacn();
}

function selectallMeMacn()
{
    if (document.formviewHistory.allCheck.checked == true)
    {
        selectCheckMacn(true);
    }
    else
    {
        selectCheckMacn(false);
    }
}
function checkSelectMacn()
{
    var i = document.formviewHistory.elements.length;
    var berror = true;
    for (var k = 0; k < i; k++)
    {
        if (document.formviewHistory.elements[k].name=='macn')
        {
            if (document.formviewHistory.elements[k].checked == false)
            {
                berror = false;
                break;
            }
        }
    }
    if (berror == false)
    {
        document.formviewHistory.allCheck.checked = false;
    }
    else
    {
        document.formviewHistory.allCheck.checked = true;
    }
}