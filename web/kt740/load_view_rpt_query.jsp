<%-- 
    Document   : load_view_rpt_query
    Created on : Jul 24, 2014, 9:31:49 AM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <sj:head/>
    <script>
        function updateParent() {
//            alert('thu xem nhe');
            opener.document.paraviewquery.title.value = document.nameaddquery.title.value;
            opener.document.paraviewquery.query.value = document.nameaddquery.query.value;
            self.close();
            return false;
        }
        window.onload = function() {
//            alert('thu nhe xem co len ko nao');
            var pw = window.opener;
            if (pw) {
                var inputFrm = pw.document.forms['nameaddquery'];
                var outputFrm = document.forms['paraviewquery'];

                outputFrm.elements['title'].value = inputFrm.elements['title'].value;
                outputFrm.elements['query'].value = inputFrm.elements['query'].value;
        }
         $("#loadparaview").trigger('click');
    }
    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang xem trước báo cáo bằng truy vấn</title>
    </head>
    <body>
        <strong><h2>Trang view báo cáo!</h2></strong>
        <s:form id="paraviewquery" name="paraviewquery" action="LoadParaviewdatakt740">
            <s:hidden name="query"/>
            <s:hidden name="title"/>
            <sj:submit id="loadparaview" name="loadparaview" targets="divExportReportQueryView" cssStyle="display: none" ></sj:submit>
        </s:form>
        <div id="containParm">
            <div id="divExportReportQueryView"></div>
        </div>
    </body>
</html>
