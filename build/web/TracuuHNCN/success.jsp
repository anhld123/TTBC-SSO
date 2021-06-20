<%-- 
    Document   : success
    Created on : Oct 26, 2015, 1:06:32 PM
    Author     : LION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<style type="text/css">
    .errors {
        background-color:#FFCCCC;
        border:1px solid #CC0000;
        width:600px;
        /*height: 20px;*/
        margin-bottom:8px;
    }
    .errors li{ 
        list-style: none; 
    }
    .success {
        background-color:#DDFFDD;
        border:1px solid #009900;
        width:600px;
        /*height: 20px;*/
    }
    .success li{ 
        list-style: none; 
    }
</style>
<html>
    <body>
         <s:if test="hasActionMessages()">
                <div class="success">
                    <s:actionmessage/>
                </div>
            </s:if>
            <br>
            <s:if test="hasActionErrors()">
                <div class="errors">
                    <s:actionerror/>
                </div>
            </s:if>
    </body>
</html>
