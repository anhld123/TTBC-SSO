<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <input type="text" id="message" value="<s:property value="message"/>" style="display: none;"/>

    <head>
        <sj:head/>
        <script>
//            $("#containBcttv").load("loadallquery.action");
//            window.location.href="rpt-bc-theo-tvan.jsp";
            var message = $("#message").val();
            alert(message);

            //Load lai form sua xoa
            $("#idEditDelQuery")[0].click();
//            $("#containBcttv").load('bctheotruyvan/exp_query.jsp');
        </script>
    </head>
</html>

