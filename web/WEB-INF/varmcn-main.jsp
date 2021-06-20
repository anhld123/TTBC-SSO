<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head jqueryui="true" jquerytheme="flick"/>


<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/Checkdate.js"></script>
<script>
    $(function() {
        new DateMask("dd/MM/yyyy", "app_effectDate");
    });

    function formatDate(value)
    {
        return value.getDate() + "/" + (value.getMonth() + 1) + "/" + value.getFullYear();
    }
</script>

<div id="maindiv">
    <s:form id="varmcnform_01" theme="simple">
        <p style="text-decoration: underline;padding-left: 5px;font-size: 12pt;font-weight: bold;"> 
            Quản lý Biến hệ thống sử dụng tại đơn vị
        </p>
        <s:url id="remoteurl" action="listVariable.action" />
        <s:url id="editurl" action="editVariable.action" />

        <sjg:grid id="gridtable"
                  caption="Biến hệ thống ..."
                  dataType="json"
                  href="%{remoteurl}" 
                  editurl="%{editurl}"
                  pager="true" 
                  gridModel="variableList"
                  rowNum="15" 
                  navigator="true"
                  navigatorAdd="false"
                  navigatorDelete="false"
                  navigatorEdit="true"
                  navigatorEditOptions="{height:150, width:425,closeAfterEdit:true}"
                  navigatorRefresh="true"
                  navigatorSearch="false"
                  navigatorView="true"          
                  rowList="15,30,45"                   
                  autowidth="true"                 
                  multiselect="true"
                  rownumbers="true"
                  onSelectRowTopics="rowselect"
                  >    
            <sjg:gridColumn name="varName" index="varName" title="Tên biến" sortable="true" width="60"/>
            <sjg:gridColumn name="varDesc" index="varDesc" title="Mô tả" sortable="false" editable="false"/>
            <sjg:gridColumn name="varValue" index="varValue" title="Giá trị" sortable="false" editable="true"/>
            <sjg:gridColumn name="varType" index="varType" title="Kiếu giá trị" sortable="false" editable="true" width="40"/>
            <sjg:gridColumn name="updQry" index="updQry" title="Query" sortable="false" editable="true"
                            hidden="true"/>
            <sjg:gridColumn name="status" index="status" title="Trạng thái" sortable="false" width="35"/>
        </sjg:grid>

        <table cellspacing = "10">
            <tr>
                <td>
        <p> <font color="red">(*) Ngày hiệu lực: <sj:datepicker name="app_effectDate" value=""  
                       onblur="validatedate(this.value)"
                       placeholder="DD/MM/YYYY" changeYear="true" 
                       changeMonth="true" displayFormat="dd/mm/yy"                                       
                       id="selectedrptDate" size="15"/>
                        </font>
                    </p>
                    </td>
            <script>
                var lj_curDate = new Date();
                var lj_setDate = (lj_curDate.getDate()) + "/" +
                        (lj_curDate.getMonth() + 1) + "/" + lj_curDate.getFullYear();
                document.getElementById("selectedrptDate").value = lj_setDate;
            </script>    
            <td>
                <s:url id="url_reloadData" action="var_reloadData.action" />
            <s:a                            
                id="sja_update01"
                            href="%{url_reloadData}"                                 
                            formIds="varmcnform_01"
                            >
            <u><b>Tải lại dữ liệu</b></u>
                        </s:a>
            &nbsp;
            <s:url id="url_updateMainTbl" action="var_updateMainTable.action" />
            <sj:a                            
                id="sja_update02"
                            href="%{url_updateMainTbl}"     
                            targets="messageDiv"
                            formIds="varmcnform_01"
                            >
            <u><b>Cập nhật</b></u>
                        </sj:a>
        </td>
        <td>
                <div id="messageDiv"/>
                </td>
                </tr>
                
        </table>
    </s:form>
</div>