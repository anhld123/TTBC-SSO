<%-- 
    Document   : exp_excel
    Created on : Jul 14, 2014, 4:17:12 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <sj:head/>
    
        <style>
            .underline {
                text-decoration: underline;
            }
            
            h3 {
                text-align: center;
            }
            
            #divTitlePhts{
                color: blue; 
                font-weight: bolder; 
                font-size: x-large;
                text-align: center;
            }
        </style>
    </head>
    <body>         
        <div style="width:97%;height:200px;overflow-y: scroll;">
        <s:form id="main_phts_result" action="main_report_phts_result" theme="simple"> 

            <table border="1" class="editDelete" style="width: 80%; height: 30%" id="tablepl01" align="center">
                        <tr>

                            <!--<th align = "center"  style="width: 50px;">Mã Chi nhánh</th>-->                            
                            <th style="width: 90px;">Nơi gửi</th>  
                            <th style="width: 90px;">Nơi đến</th>
                            <th style="width: 90px;">Ngày nhập</th>
                            <th style="width: 90px;">Trạng thái</th>
                            <!--<th style="width: 90px;">Trạng thái gửi</th>-->
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                           
                                <tr style="text-align: center; color: #0000FF; font-weight: bold;" onmouseover="mover(this);"  onmouseout="mout(this);">

                                    <!--<td align = "center"  style="width: 30px;"><s:property  value="MA" /></td>-->
                                    <td align = "center"  style="width: 30px;"><s:property  value="MAPGD" /></td>                           
                                    <td align = "center"  style="width: 30px;"><s:property  value="D9" /></td>
                                    <td align = "center"  style="width: 30px;"><s:property  value="NGAY_NHAP" /></td>
                                    <td align = "center"  style="width: 30px;"><s:property  value="D8" /></td>
                        </s:iterator>
                    </table>              
        </s:form>          
            </div>
    </body>
</html>
