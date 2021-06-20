<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <% String Paravl = (String) request.getAttribute("LControl"); %>
        <!-- Định nghĩ Control là Text box-->
        <%if ("TEXT".equals(Paravl)) {%>
        <input type="text" name="listgt" id="listgt" value="" style="width:155px">
        <%}%>
        <!-- Định nghĩ Control là Select Combo-->
        <%if ("COMBO".equals(Paravl)) {%>
        <select name="listgt" id="listgt" style="width:155px">      
            <OPTION value="CHON">(chọn giá trị)</OPTION>
                <s:iterator value="lstcontrol">
                <OPTION value="<s:property value="TENTRUONG"/>"><s:property value="GIATRI"/></OPTION>
                </s:iterator>
        </Select>
        <%}%>
        <!-- Định nghĩ Control là Date-->
        <%if ("DATE".equals(Paravl)) {%>
        <s:iterator value="lstcontrol">
            <input type="text" name="listgt" id="listgt" style="width:155px" value="<s:property value="GIATRI"/>">
        </s:iterator>
        <%}%>
    </body>
</html>
