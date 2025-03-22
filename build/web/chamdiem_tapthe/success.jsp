<%-- 
    Document   : success
    Created on : Oct 26, 2015, 1:06:32 PM
    Author     : LION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 <script src="js/sweetalert.min.js"></script>
<!DOCTYPE html>
<s:if test="hasActionMessages()">
    <script>
        var actionMessages = '';
        <s:iterator value="actionMessages">
        actionMessages += '<s:property escape="false"/>' + '\n';
        </s:iterator>
        swal('Thành công', actionMessages, 'success');
        $("#loadData").click();
    </script>
</s:if>
<s:if test="hasActionErrors()">
    <script>
        var actionMessages = '';
        <s:iterator value="actionErrors">
        actionMessages += '<s:property escape="false"/>' + '\n';
        </s:iterator>

//        actionMessages = actionMessages.replace(/"/g, '');
        actionMessages = actionMessages.replace(/<p>/g, '\n');
        actionMessages = actionMessages.replace(/<strong>/g, '\n');
        swal('Lỗi', actionMessages, 'error');

    </script>
</s:if>