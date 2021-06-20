<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib uri="/struts-dojo-tags" prefix="sx" %>

<script>
    window.onload = function() {
//            alert('thu nhe xem co len ko nao');
        var pw = window.opener;
        if (pw) {
            var inputFrm = pw.document.forms['idViewReportFast'];
            var outputFrm = document.forms['paraviewbc'];
            outputFrm.elements['titlereport'].value = inputFrm.elements['titlereport'].value;
            outputFrm.elements['rightColumnList'].value = inputFrm.elements['rightColumnList'].value;
            outputFrm.elements['rightDateList'].value = inputFrm.elements['rightDateList'].value;
        }
    }
</script>
<s:form  id="paraviewbc" name="paraviewbc" action="viewbc">
    <div align="center"><h1><s:property value="titlereport"></s:property> </h1></br></div> 
        <s:hidden name="rightColumnList" id="idrightColumnList"/>
        <s:hidden name="rightDateList" id="idrightDateList"/>
        <s:hidden name="titlereport" id="titlereport"/>
    <table align="center" border="1" CELLSPACING="0">
        <s:iterator value="lstViewReport" var="test">               
            <s:property escape="false"></s:property>
        </s:iterator>
    </table>
</s:form>