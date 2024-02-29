<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            a.downloadlink:link,a.downloadlink:visited {
                color: #0000A0;
                background-color: #FFFFFF;
                text-decoration: none;
                target-new: none;
                font-size: 14px;
                font-family: Arial;
            }
            a.downloadlink:hover {
                color: #0000FF;
                background-color: #FFFFC0;
                text-decoration: underline;
                target-new: none;
                font-size: 14px;
                font-family: Arial;
            }
            p.downloadlink {
                font-size: 14px;
                font-family: Arial;
            }
        </style>        
    </head>
    <body>        
        <s:hidden id="filereport" name="filereport"/>        
        <script>
            var fileDir = $("#filereport").val();
             //alert(fileDir);
            if (fileDir == "ERROR_JASPER_REPORT.PDF" )
            {
               
                var ext = fileDir.substr(fileDir.lastIndexOf('.') + 1);
                if (ext == "PDF") {
                    window.open('REPORTS/ERROR_JASPER_REPORT.pdf', 'Mở file báo cáo cho xem', 'location=no');
                }
            }
            else
            {
                var ext = fileDir.substr(fileDir.lastIndexOf('.') + 1);
                if (ext == "PDF") {
                    window.open('EXPORT_REPORT/PDF/' + fileDir, 'Mở file báo cáo cho xem', 'location=no');
                }
            }
            function maxWindow()
            {
                window.moveTo(0, 0);
                if (document.all)
                {
                    top.window.resizeTo(screen.availWidth, screen.availHeight);
                }
                else if (document.layers || document.getElementById)
                {
                    if (top.window.outerHeight < screen.availHeight || top.window.outerWidth < screen.availWidth)
                    {
                        top.window.outerHeight = screen.availHeight;
                        top.window.outerWidth = screen.availWidth;
                    }
                }
            }
        </script>                
        <div class="report_group_form">
            <s:form  id="formdownload" name="formdownload" action="download" method="post" theme="simple">
                <s:hidden name="fileNamelocal"/>
                <p class="downloadlink">
                    Tải file báo cáo: 
                    <a href="#" name="file1" class="downloadlink"
                       onclick="document.forms['formdownload'].submit();"><s:property value="filereport" /></a>                
                </p>
            </s:form>
        </div>
    </body>
</html>
