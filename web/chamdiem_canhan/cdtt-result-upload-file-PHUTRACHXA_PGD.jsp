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
                width: 150%;
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
                    <th >Mã xã</th>
                    <th >Tên xã</th>
                    <th >Mã CB Phó giám đốc</th>						
                    <th >Tên CB Phó giám đốc</th>						
                    <th >Ex_code1</th>
                    <th >Ex_name1</th>
                    <th >Ex_code2</th>
                    <th >Ex_name2</th>
<!--                    <th >Mã cán bộ KSNB</th>
                    <th >Tên cán bộ KSNB</th>
                    <th >Mã cán bộ HCTC</th>
                    <th >Tên cán bộ HCTC</th>
                    <th >Mã Phó Giám đốc phụ trách</th>
                    <th >Tên Phó Giám đốc phụ trách</th>-->
                </tr>
                
                <s:iterator value="#attr.lstExcel" var="modelView" status="rowstatus">   
                    <tr>
                        <td style="width: 150px"><input type="text" id="D4" value="<s:property value='C1'/>"  style="width: 100%; text-align: center;" name="lstExcel[<s:property  value="%{#rowstatus.index}" />].C1" class="" readonly="readonly"/></td>
                        <td style="width: 50px"><input type="text" id="D4" value="<s:property value='C2'/>"  style="width: 100%; text-align: center;" name="lstExcel[<s:property  value="%{#rowstatus.index}" />].C2" class="" readonly="readonly"/></td>
                        <td style="width: 150px"><input type="text" id="D4" value="<s:property value='C3'/>"  style="width: 100%;" name="lstExcel[<s:property  value="%{#rowstatus.index}" />].C3" class="" readonly="readonly"/></td>

                        <td><input type='text' id='N1' value='<s:property value='N1'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N1'  readonly='readonly'/></td>
                        <td><input type='text' id='N2' value='<s:property value='N2'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N2'  readonly='readonly'/></td>
                        <td><input type='text' id='N3' value='<s:property value='N3'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N3'  readonly='readonly'/></td>
                        <td><input type='text' id='N4' value='<s:property value='N4'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N4'  readonly='readonly'/></td>
                        <td><input type='text' id='N5' value='<s:property value='N5'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N5'  readonly='readonly'/></td>
                        <td><input type='text' id='N6' value='<s:property value='N6'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N6'  readonly='readonly'/></td>                                                
                        <td><input type='text' id='N7' value='<s:property value='N7'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N7'  readonly='readonly'/></td>
<!--                        <td><input type='text' id='N8' value='<s:property value='N8'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N8'  readonly='readonly'/></td>
                        <td><input type='text' id='N9' value='<s:property value='N9'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N9'  readonly='readonly'/></td>
                        <td><input type='text' id='N10' value='<s:property value='N10'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N10'  readonly='readonly'/></td>
                        <td><input type='text' id='N11' value='<s:property value='N11'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N11'  readonly='readonly'/></td>
                        
                        <td><input type='text' id='N12' value='<s:property value='N12'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N12'  readonly='readonly'/></td>
                        <td><input type='text' id='N13' value='<s:property value='N13'/>'  style='width: 100%;' name='lstExcel[<s:property  value='%{#rowstatus.index}' />].N13'  readonly='readonly'/></td>-->
                        
                    </tr>
                </s:iterator>
            </table>
        </div>
    </body>
</html>
