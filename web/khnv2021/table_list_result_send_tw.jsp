<%-- 
    Document   : success
    Created on : Oct 26, 2015, 1:06:32 PM
    Author     : LION
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->
<style>
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
        /*width:100%;*/
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
        <table border="1" class="editDelete" id="tableplsuccess" align="center">
            <s:if test="%{lstViewSend.isEmpty()}">

            </s:if>
            <s:else>
                <tr style="height: 25px !important;">
                    <th align = "center"  style="width: 50px;">Mã PGD</th>
                    <th style="width: 100px;">Tên PGD</th>
                    <th style="width: 90px;">Trạng thái gửi</th>
                </tr>
            </s:else>
            <s:iterator value="#attr.lstViewSend" var="modelView" status="rowstatus">
                <s:if test="key==1">
                    <tr style="text-align: center; color: yellow; font-weight: bold; height: 25px !important;">
                    </s:if>
                    <s:elseif test="key==2">
                    <tr style="text-align: center; color: #9ad717; font-weight: bold">
                    </s:elseif>
                    <s:elseif test="key==3">
                    <tr style="text-align: center; color: red; font-weight: bold">
                    </s:elseif>
                    <s:elseif test="key==4">
                    <tr style="text-align: center; color: #00B83F; font-weight: bold">
                    </s:elseif>
                    <s:elseif test="key==5">
                    <tr style="text-align: center; color: #FF7E00; font-weight: bold">
                    </s:elseif>
                    <s:else>
                    <tr style="text-align: center; color: deeppink; font-weight: bold">
                    </s:else>

                    <td align = "center"  style="width: 50px;"><s:property  value="mapgd" /></td>
                    <td align = "left" style="width: 100px;"><s:property  value="tenpgd" /></td>
                    <td style="width: 90px;"><s:property  value="mota_loi" /></td>
                </tr>
            </s:iterator>

        </table>
        <!--            <table border="1">
        <s:property escape="false" value="message"></s:property>
    </table>-->
    </div>
</body>    
</html>
