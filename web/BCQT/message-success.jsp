<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<input type="text" id="message" value="<s:property value="Message"/>" style="width: 200px;display: none;"/>

<script>
    var message = $("#message").val();
    alert(message);
       
    // KIEM TRA TRONG TRUONG HOP CO THI TAI LAI DU LIEU
    if ($('#idTMtmp').length > 0
            && message.toUpperCase().indexOf('THÀNH CÔNG') > 0 ) { 
        // it exists 
        $('#idTMtmp').click();
    }    
</script>

