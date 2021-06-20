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
        <style>

            #tabledetail {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 99%;
            }

            #tabledetail td, #tabledetail th {
                border: 1px solid #ddd;
                padding: 0px;
                /*width: 30px;*/
            }

            #tabledetail tr:nth-child(even){background-color: #f2f2f2;}

            #tabledetail tr:hover {background-color: #ddd;}

            #tabledetail th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: center;
                background-color: #4CAF50;
                color: white;
            }
        </style>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>  
        <script>
            $(document).ready(function () {

                $('input.number2').css({"text-align": "right"});
                $('.number2').number(true, 2);
                $(".hideColumn").hide();
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
    </head>
    <body>
        <h2><span style=" color: blue;">Bạn đã upload file thành công chi tiết</span></h2>
        <div style=" overflow: scroll; width: 90%; height: 300px">
            <table border=1  id="tabledetail">
                <tr>
                    <th>Khóa</th>
                    <th style="width: 10px">TT</th>                   
                    <th >Mã cán bộ</th>						
                    <th >Tên CB</th>						
                    <th >Kế hoạch giao</th>
                </tr>
                
                <s:iterator value="#attr.lstExcel" var="modelView" status="rowstatus">   
                    <tr>
                        <td style="width: 200px"><input type="text" id="D4" value="<s:property value='C1'/>"  style="width: 100%; text-align: center;" name="lstExcel[<s:property  value="%{#rowstatus.index}" />].C1" class="" readonly="readonly"/></td>
                        <td style="width: 100px"><input type="text" id="D4" value="<s:property value='C2'/>"  style="width: 100%; text-align: center;" name="lstExcel[<s:property  value="%{#rowstatus.index}" />].C2" class="" readonly="readonly"/></td>
                        <td style="width: 200px"><input type="text" id="D4" value="<s:property value='C3'/>"  style="width: 100%;" name="lstExcel[<s:property  value="%{#rowstatus.index}" />].C3" class="" readonly="readonly"/></td>

                        <td><input type='text' id='N1' value='<s:property value='N1'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N1'  readonly='readonly'/></td>
                        <td><input type='text' id='N2' value='<s:property value='N2'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N2'  readonly='readonly'/></td>
                        
                    </tr>
                </s:iterator>
            </table>
        </div>
    </body>
</html>
