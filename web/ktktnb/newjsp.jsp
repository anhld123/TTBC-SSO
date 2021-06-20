<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <s:head/>
        <sx:head/>
        <sj:head/>
        <script src="../js/jquery.number.js"></script>
        <script src="../js/format_num.js"></script>
        
        <script>            
            function zzzz(obj){
                $('#test').number(true,0);
            }
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
            
        <s:form id="abc" action="abc" theme="simple">
            Name: <input type="text" id="test" name="test" onblur="zzzz(this)"/> <br/>
            Pass: <input type="text" id="bbb" name="bbb"/>
            <sj:submit  id="ddd" targets="zzzzssss" showLoadingText="false" indicator="loadingImage"/>
       </s:form>
            
        <div id="zzzzssss">
        </div>
    </body>
</html>
