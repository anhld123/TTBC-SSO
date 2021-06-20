<%-- 
    Document   : success
    Created on : Oct 26, 2015, 1:06:32 PM
    Author     : LION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<style type="text/css">
    .errors {
        color: red;
        /*        background-color:#FFCCCC;
                border:1px solid #CC0000;
                 height: 30px;*/
    }
    .success {
        color: green;
        /*        background-color:#DDFFDD;
                border:1px solid #009900;
                height: 30px;*/
    }
    .blink{
        font-weight: bold;
        font-size: 12px;
        width:100%;
        padding-bottom: 0px;
        padding-top: 0px;
        text-align: left;
    }
</style>
<script type="text/javascript">
    $(document).ready(function() {
        $('.blink').each(function() {
            var elem = $(this);
            var i = 0;
            var inter = setInterval(function() {
                i++;
                if (elem.css('visibility') == 'hidden') {
                    elem.css('visibility', 'visible');
                } else {
                    elem.css('visibility', 'hidden');
                }
                if (i == 8) {
                    clearInterval(inter);
                }
            }, 300);
        });
    });
</script>
<html>
    <body>
        <div style="height: 7px;"/>
        <div class="bgcolor">
            <s:if test="hasActionMessages()">
                <div class="success">
                    <s:iterator value="actionMessages">  
                        <span class="blink"><s:property escape="false" /></span>
                    </s:iterator> 
                </div>
            </s:if>
            <s:if test="hasActionErrors()">
                <div class="errors" >
                    <s:iterator value="actionErrors">  
                        <span class="blink"><s:property escape="false" /></span>
                    </s:iterator> 
                </div>
            </s:if>
        </p>
        <s:property escape="false" value="message"></s:property>
    </div>
</body>    
</html>
