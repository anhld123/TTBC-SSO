<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<input type="text" id="message" value="<s:property value="message"/>" style="display: none;"/>

<script>
    var message = $("#message").val();
    alert(message);
    
    //tungnv: viet cho form risk
    //Load lai form tai lai du lieu
    $("#idSubmit")[0].click();
</script>

