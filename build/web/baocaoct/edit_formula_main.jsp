<%-- 
    Document   : main_bccongthuc
    Created on : Jul 18, 2014, 8:58:04 AM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib uri="/struts-dojo-tags" prefix="sx" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script>
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
    </script>
    <script type="text/javascript">
        $('#messageDiv').text('');
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
    </script>

    <head>
        <%--<sj:head/>--%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <script>

//            $(document).ready(function(){
////                $("#create_report").trigger("click");
//                $("#create_report")[0].click();
//                // jQuery methods go here...
//
//             });
//             function initPage(){
//                 alert("abc");
////            $("#create_report")[0].click();
//        }
        </script>
    </head>
    <style type="text/css">
        th{
            background-color: #DCDCDC;
            border-color: #999;
        }
        td{
            border-color: #999;
        }
        body,div{
            font-family: Arial;
            font-size: 13px;
        }
        a.button{
            font-weight: bold;
            text-decoration:none;
            color: #018c3b;
        }
        a{
            text-decoration:none;
            color: #018c3b;
        }
        .idcot{
            font-weight: bold;

        }
        .iddong{
            font-weight: bold;
        }
        .idcot,.iddong,.idtd,.idcont{
            width: 150px;
        }
        .cbonguon{
            width:140px;
        }
    </style>
    <body onload="initPage()">
        <div align="center">
            <s:form align="center" id="formula_create"  name="formula_create" action="ReDrawEditFormula" theme="simple">
                <s:hidden name="save_id"/>
                <%--<s:url id="editDelQuery" action="loadallquery" />--%>
                <%--<sj:submit id="idEditDelQuery" targets="containBcttv"  href="%{editDelQuery}" indicator="loadingImage" cssClass="metroButtonStyle" value="Sửa/Xóa"></sj:submit>--%>
                <%--<sj:submit id="loadQuery" targets="containBcttv"  href="bctheotruyvan/exp_query.jsp" indicator="loadingImage" cssStyle="display: none;" value="Load BC"></sj:submit>--%>
                <table border="1" cellspacing="0px" cellpadding="3px" style="width: 80%;" align="center">
                    <tr>
                        <td style="width: 20%; height: 10px;">                    
                            <s:label value="Nguồn số liệu:" id="labelsource" />
                            <s:select id="sourcedata" 
                                      name="source_data"      
                                      value = "%{defaultSourceData}"
                                      list="lstSourceData" 
                                      listKey="sKey"
                                      listValue="sDesc"
                                      headerKey="-1"
                                      headerValue="--- Chọn Nguồn SL ---"
                                      cssClass="cbonguon" >                    
                            </s:select>
                        </td>
                        <td style="width: 20%; height: 10px;">
                            <s:label value="Đơn vị tính:" id="labeluntil" />
                            <s:select id="untildata" 
                                      name="until_data"                                   
                                      list="lstUntilData" 
                                      listKey="sKey"
                                      listValue="sDesc"
                                      cssClass="cbonguon" value="%{defaultUntilData}">                     
                            </s:select>
                        </td>
                        <td style="height: 10px;">

                        </td>
                        <td rowspan="5" colspan="3" style="width: 25%;border: 1px solid; border-color: #018c3b;"  valign="top">
                            <div align="left" id="showandhidden_main" style="visibility: hidden;"> 
                                <s:checkbox id ="allCheck" name="allCheck" onclick="selectallMe()" value="false"/>
                                <s:label value="Chọn tất cả" cssStyle="font-weight: bold" id="labelselect_all"/> 
                            </div>

                            <div align="left" id="showandhidden_table" style="width: 100%;height:155px;overflow-y: scroll; visibility: hidden;">
                                <s:iterator value="lstBranchObj" var="ObjBranch">
                                    <s:checkbox id ="check_branch" name="check_branch" fieldValue="%{sKey}"
                                                onclick="selectall()" value="%{sStt}" cssClass = "checkbox"/> 
                                    <s:property  value="sDesc" /></br>
                                </s:iterator>
                            </div>
                        </td>
                    </tr>
                    <tr>

                        <td style="width: 15%; height: 10px;">
                            <s:label value="Loại báo cáo:"  id="labelreport_type"/>
                            <s:select id="report_type" 
                                      name="report_type"
                                      list="lstReportType" 
                                      listKey="sKey"
                                      listValue="sDesc"
                                      value="%{defaultReportType}" onchange="showandhidden()" onselect="showandhidden()">                    
                            </s:select>
                        </td>
                        <td style="width: 15%; height: 10px;">
                            <s:label value="Kỳ báo cáo:" id="label_times"/>
                            <s:select id="report_times" 
                                      name="report_times"
                                      list="lstReportTimes" 
                                      listKey="sKey"
                                      listValue="sDesc"
                                      headerKey="-1"
                                      headerValue="--- Chọn kỳ BC ---"
                                      cssClass="cbonguon" value="%{defaultReportTimes}">                    
                            </s:select>
                        </td>
                        <td style="height: 10px;">

                        </td>

                    </tr>
                    <tr>
                        <td colspan="3" style="height: 10px;">
                            <s:label value="Tiêu đề báo cáo:" id="labelrpt_title"/>
                            <s:textfield name="title_name"  size="100" id="title_name"/>
                        </td>


                    </tr>
                    <tr>
                        <td style="width: 15%; height: 10px;">
                            <s:label value="Số dòng: " id="labelrow_number"/>
                            <s:textfield name="number_row" id="number_row" />
                        </td>
                        <td>
                            <s:label value="Số cột: "/>
                            <s:textfield name="number_column" id="number_column" />
                        </td>
                        <td>

                        </td>

                    </tr>
                    <tr>
                        <td style="height: 10px;">
                            <div id="showandhidden_rowcolumn" style="visibility: hidden;"> 
                                <s:label value="Hiển thị theo dòng/cột: " id="labeldisplay"/>
                                <s:radio id="row_column"
                                         name="row_column" 
                                         list="lstRowColumn" 
                                         listKey="sKey"
                                         listValue="sDesc" 
                                         value="%{defaultDisplayRowColumn}"
                                         onclick="DisableEnable()"
                                         />     
                            </div>
                        </td>
                        <td  align="center" style="height: 10px;">
                            <s:label value="Cấp xuất BC: " id="labeldisplay"/>
                            <s:checkboxlist list="lstGrade" value="defaultGrade" listKey="sKey" listValue="sDesc"
                                            name="rptGrade"></s:checkboxlist>   
                            </td>    
                            <td>

                            </td>

                        </tr>
                    </table>
                    </br>
                    <div  align="right" id="Main_report_formula" style="width: 90%;">
                    <sj:submit id="create_report" name="create_report" value="Tạo bảng dữ liệu" targets="divShowPage" /> 
                    <!--cssStyle="display: none;"-->
                </div>

            </s:form>
            <script>showandhidden()</script>
        </div>
        <div id="containParm" align="center">
            <div id="divShowPage" align="center"></div>
        </div>
    </body>



</html>
