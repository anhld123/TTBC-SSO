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
            var max_row = 0;
            $(document).ready(function () {
                $('.number').css({"text-align": "right"});
                $('.number2').css({"text-align": "right"});                
                $('.D0').css({"text-align": "center"});               
                $('.number').number(true, 0);
                $('.number2').number(true, 2);
                $(".SOKU").css({"width": "100%"});
                $(".TD_CHECKBOX").css({"width": "2%"});
                $(".TD_SOKU").css({"width": "7%"});
                $(".TD_TENKH").css({"width": "20%"});
                $(".TD_TENTS").css({"width": "15%"});
                $(".TD_MAKH").css({"width": "10%"});
                $(".TD_THOIGIAN").css({"width": "5%"});
                $(".TD_MAPGD").css({"width": "45px"});
                $(".TD_BUTTON1").css({"width": "10%"});
                $(".TD_SOTIEN").css({"width": "100px"});
                $(".TEN_KH").css({"width": "100%"});
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
        <div style="width: 100%; height: 500px">
            <table border=1  id="editDelete" class="cls-table">
                <tr>                    
                    <th  class="TD_CHECKBOX">TT</th>
                    <th  class="TD_THOIGIAN">Mã KH</th>					
                    <th  class="TD_THOIGIAN">Tên KH</th>						
                                                                                
                    <th  class="TD_SOKU">Số KU</th>
                    <th  class="TD_THOIGIAN">Dư nợ</th>					
                    <th  class="TD_THOIGIAN">Ngày vay</th>						
                    <th  class="TD_THOIGIAN">Ngày đến hạn</th>                 

                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr> 
                                <td align = "right" class="TD_CHECKBOX" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="TT_HIENTHI" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_MAKH" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D6" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_THOIGIAN" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D7" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_THOIGIAN" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D2" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class=" D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>
                                <td align = "right" class="TD_SOKU" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D12" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number " onfocus="this.select();"
                                           readonly="true"/>                                                                        
                                </td>
                                <td align = "right" class="TD_SOKU" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D9" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                </td>  
                                <td align = "right" class="TD_THOIGIAN" >
                                    <input type="text" <s:if test="D19.equalsIgnoreCase('1')">style="color: red"</s:if> value="<s:property  value="D10" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH" onfocus="this.select();"
                                           readonly="true"/>
                                </td> 
                                
                            </tr>                                                                                                                                                                                   
                    </s:iterator>
            </table>
        </div>
    </body>
</html>
