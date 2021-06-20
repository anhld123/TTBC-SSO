<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%--<jsp:include page="/WEB-INF/templates/template.jsp">            
    <jsp:param name="body" value="/dcpt_no/doichieu-no-main.jsp"/>            
</jsp:include>--%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<META HTTP-EQUIV="Refresh" CONTENT='0;URL=/IMS_REPORTS/loadXuLyRuiRo.action?random=this.random()'>-->
        <script type="text/javascript">
            /*
             * Tungnv sua doan nay vi:
             * Khi 2 user dang nhap 2 la lien tiep o cung 1 may
             * se bị lay session cua lần đăng nhập trước đó.
             */
            function Refresher()
            {
                window.location.href = '/IMS_REPORTS/loadFormMain.action?random='+Math.round((Math.random() * 1000) + 1);
            }
            
        </script>
    </head>
    <BODY onLoad="Refresher()">
    </body>

</html>
