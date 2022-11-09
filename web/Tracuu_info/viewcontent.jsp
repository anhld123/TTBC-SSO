<%-- 
    Document   : viewcontent
    Created on : Oct 8, 2015, 10:00:23 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<button id="btnExport" onclick="fnExcelReport();" style="margin: 5px;"> Xuất excel </button>

<table id="tblcontent" cellspacing="0" cellpadding="0" border="0" style="width:2300px; height: 500px;">
    <tr><td>
            <table id="customers" class="sortable">
            <s:iterator value="listgt">
                <s:property value="listgt" escape="false"/>
            </s:iterator>
        </table>
    </td></tr>
    <tr style="background-color: #555;">
        <td>
            <div id="pageNavPosition"></div>
        </td>
    </tr>
</table>

<table id="tblexpcontent" cellspacing="0" cellpadding="0" border="0" style="display: none;">
    <tr><td>
            <table id="customers" class="sortable">
            <s:iterator value="listgt">
                <s:property value="listgt" escape="false"/>
            </s:iterator>
        </table>
    </td>
</table>

<script src="Tracuu_info/sorttable.js" type="text/javascript"></script>

<script type="text/javascript">
    var pager = new Pager('customers', 100);
    pager.init();
    pager.showPageNav('pager', 'pageNavPosition');
    pager.showPage(1);

    function fnExcelReport()
    {
        var tab_text="";
        var textRange; var j=0;
        tab = document.getElementById('tblexpcontent'); 

        for(j = 0 ; j < tab.rows.length ; j++) 
        {     
            tab_text =tab_text+tab.rows[j].innerHTML+"</tr>";
        }

        var ua = window.navigator.userAgent;
        var msie = ua.indexOf("MSIE "); 
        
        download('Export.xls',tab_text);
    }
    
    function download(filename, text) {
        var element = document.createElement('a');
        element.setAttribute('href', 'data:application/vnd.ms-excel;charset=utf-8,' + encodeURIComponent(text));
        element.setAttribute('download', filename);

        element.style.display = 'none';
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);
    }

</script>