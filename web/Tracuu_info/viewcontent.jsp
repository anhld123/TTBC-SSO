<%-- 
    Document   : viewcontent
    Created on : Oct 8, 2015, 10:00:23 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

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
<script type="text/javascript">
    var pager = new Pager('customers', 100);
    pager.init();
    pager.showPageNav('pager', 'pageNavPosition');
    pager.showPage(1);
</script>
<script src="Tracuu_info/sorttable.js" type="text/javascript"></script>
