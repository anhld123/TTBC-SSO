<%@taglib prefix="s" uri="/struts-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông báo!</title>

        <style>
            *{
                font: 16px Arial, Helvetica, sans-serif;
            }
        </style>
    </head>
    <body>
        <s:if test="reportGrade.equalsIgnoreCase('1')">        
            <div style="color: #116600">Bạn chưa nhận được kế hoạch giao từ chi nhánh xuống cho chỉ tiêu: <s:property value="maCt"/> - <s:property value="tenCt"/></div>            
        </s:if>
        <s:hidden name="reportGrade"/>
        <s:else>
            <div style="color: #116600">Bạn chưa nhận được kế hoạch giao từ trung ương cho chỉ tiêu: <s:property value="maCt"/> - <s:property value="tenCt"/></div>
        </s:else>
        <div style="text-align: center; margin-top: 20px">
            <input type="button" onclick="window.close()" value="Đóng" style="width:122px;height:25px;font-size: 14"/>
        </div>


    </body>
</html>
