<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<s:head/>
<sj:head/>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <link  rel="stylesheet" type="text/css" href="chamdiem_canhan/css/cdtt.css"/>
        <style>
            .readonly {
                background: #FFFFC0;        
            }
            .pos_edit_form {
                padding:0px;
                width:30%;    
                background:#f9f9f9;
                border:1px solid #ccc;
                text-align:left;   
                font-family: Arial;
                font-size: 12pt;
            }    

            #divTitle{
                font: 14px Arial, Helvetica, sans-serif;
                font-weight: bold;
                color: #0077b3;
                text-align: center;
            }
            #idTitle{
                font-family: Cambria,Verdana,Arial,Tahoma,Helvetica;
                font-size: 11pt;
                font-weight: bold;
                color: blue;
            }



            #tabledetail {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            #tabledetail td, #tabledetail th {
                border: 1px solid #ddd;
                padding: 8px;
            }

            #tabledetail tr:nth-child(even){background-color: #f2f2f2;}

            #tabledetail tr:hover {background-color: #ddd;}

            #tabledetail th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #4CAF50;
                color: white;
            }

            .green {
                /*position: absolute;*/ 
                bottom:  10px;
                float: left;
                width: 100%;
                /*margin: auto;*/
                background-color: #FFCCCC;
                text-align: left;
                margin-top: 10px;
                /*margin-right: 10px;*/
                /*margin-bottom: 10px;*/
                margin-left: 0px;
            }


            div p {
                /*background: #66CC00;*/
                margin-left: 10px;
            }
        </style>

        <script>
            var max_row = 0;
            $(document).ready(function () {
            $('.D0').css({"text-align": "center"});
            $(".TD_TEN_KH").css({"width": "280px"});
            $(".TD_CHUCVU").css({"width": "30px"});
            $(".TD_CMND").css({"width": "80px"});
            $(".TD_MAIL").css({"width": "110px"});
            $(".TD_M").css({"width": "140px"});
            $(".TD_TVTT").css({"width": "1110px"});
            $(".TD_THEMXOA").css({"width": "10px"});
            $(".TEN_KH").css({"width": "100%"});
            $(".hideColumn").hide();
            $("#allCheck_pgd").change(function () {
            $(".checkboxpgd").prop('checked', $(this).prop("checked"));
            });
            $("#allCheck_sp").change(function () {
            $(".checkboxsp").prop('checked', $(this).prop("checked"));
            });
            });
            $('.TEN_KH').focus(function () {
            $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
            $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
                    <script type="text/javascript">             var tableToExcel = (function () {
            var uri = 'data:application/vnd.ms-excel;base64,'
                    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
        , base64 = function (s) {
 console.log(window.btoa(unescape(encodeURIComponent(s))));
return window.btoa(unescape(encodeURIComponent(s)))
}
, format = function (s, c) {
return s.replace(/{(\w+)}/g, function (m, p) {
return c[p];
})
}
return function (table, name) {
if (!table.nodeType)
table = document.getElementById(table)
var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
window.location.href = uri + base64(format(template, ctx))
}
})()
        </script>
        <script src="chamdiem_canhan/js/tableToExcel.js"></script>
        <script>
            function exportExcel() {
            TableToExcel.convert(document.getElementById("tabledetail"));
            }

            var button = document.querySelector("#toExcel");
            button.addEventListener("click", function (e) {
            var table = document.querySelector("#tabledetail");
            TableToExcel.convert(table);
            });
        </script>
    </head>
    <body>
        <!--<div id="container_popup"> tableToExcel('tabledetail', 'Chi tiết chỉ tiêu') id="toExcel"-->
        <s:form name="frmChitietCdtt" id="frmChitietCdtt"  theme="simple">              
            <div id="divChiTieu" style="text-align: center;">   
                <s:hidden name="khoa_cdtt"/>
                <s:hidden name="ngay_bc"/>                    

                <span id="idTitle" >Chi tiết: <s:property value="Message" escape="false" /></span>                                                       
                <hr/>    
            </div>
            <div style="float: right;margin-bottom: 10px; width: 100%;text-align:right;">
                <input type="button"  onclick="exportExcel();" value="Xuất excel" class="metroButtonStyle">
                <hr/> 
            </div>

            <div>

                <table id="tabledetail" align="center">
                    <tr class="hideColumn">
                        <td colspan="5" data-f-bold="true"> <H2>Chi tiết: <s:property value="Message" escape="false" /></H2></td>
                    </tr>
                    <!--<caption  hidden="yes"><H2>Chi tiết: <s:property value="Message" escape="false" /></H2></caption>-->
                            <s:property value="tableDetail" escape="false" />
                </table>

                <!--<div class="content">-->
                <div class="green" >
                    <p>
                        <s:property value="thuyetminh" escape="false" />
                </div>
                <!--</div>-->
            </div>

        </s:form>
        <!--</div>-->

    </body>
</html>
